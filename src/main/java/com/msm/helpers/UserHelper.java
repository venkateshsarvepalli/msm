package com.msm.helpers;

import com.msm.model.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class UserHelper {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserHelper.class);

    private static UserAccount userAccountAccount;

    /**
     * Get current logged user if exists
     * @return User|null
     */
    public static UserAccount getCurrentUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserAccount userAccountAccount = null;
        try{
            userAccountAccount = (UserAccount) principal;
        }catch(Exception e) {
            LOGGER.debug("Critical Exception - Cannot cast principal to UserAccount", e);
        }
        return userAccountAccount;
    }


    /**
     * Logout the user
     */
    public static void logout() {
        try {
            userAccountAccount = null;
        }catch(Exception e) {
            LOGGER.debug(e.toString());
        }
    }
}
