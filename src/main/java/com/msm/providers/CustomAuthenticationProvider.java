package com.msm.providers;

import com.msm.model.User;
import com.msm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


/**
 *
 * Created by Venkatesh on 23/08/16.
 */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static Logger logger= Logger.getLogger(CustomAuthenticationProvider.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName().toLowerCase();
        String password = authentication.getCredentials().toString();

        User user = userRepository.authenticate(name, password);
        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
