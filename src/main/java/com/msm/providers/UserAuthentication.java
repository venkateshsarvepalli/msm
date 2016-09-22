package com.msm.providers;

import com.msm.model.UserAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 *
 * Created by Venkatesh on 23/08/16.
 */
public class UserAuthentication implements Authentication {

    private final UserAccount userAccount;
    private boolean authenticated = true;

    public UserAuthentication(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String getName() {
        return userAccount.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userAccount.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return userAccount.getPassword();
    }

    @Override
    public UserAccount getDetails() {
        return userAccount;
    }

    @Override
    public Object getPrincipal() {
        return userAccount.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
