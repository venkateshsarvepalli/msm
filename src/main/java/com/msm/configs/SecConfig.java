package com.msm.configs;

import com.msm.providers.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 *
 * Created by Venkatesh on 23/08/16.
 */
@Order(1)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CsrfSecurityRequestMatcher csrfSecurityRequestMatcher;

    @Autowired
    HibernateConfig hibernateConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .csrf().requireCsrfProtectionMatcher(csrfSecurityRequestMatcher);
        http
                .authorizeRequests()
                .antMatchers("/**","/index","/checkEmail/**", "/main","/forgotPassword","/getEmailForPassword","/registerWineEvent/**", "/css", "/js", "/images", "/fonts").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN_ROLE")
                .anyRequest().authenticated()
        ;
        http
                .formLogin()
                .defaultSuccessUrl("/hello")
                .failureUrl("/loginFailure#login_form")
                .loginPage("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSION")
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .portMapper()
                .http(9090).mapsTo(9443)
                .http(80).mapsTo(443)
                .and()
                .rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(1209600)
                .and()
                .exceptionHandling().accessDeniedPage("/error/accessDenied")
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/public/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    public AuthenticationProvider createCustomAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(hibernateConfig.dataSource());
        return db;
    }

}
