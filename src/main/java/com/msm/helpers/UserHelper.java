package com.msm.helpers;

import com.msm.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class UserHelper {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserHelper.class);

    private static User userAccount;

    /**
     * Get current logged user if exists
     * @return User|null
     */
    public static User getCurrentUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userAccount = null;
        try{
            userAccount = (User) principal;
        }catch(Exception e) {
            LOGGER.debug("Critical Exception - Cannot cast principal to UserAccount", e);
        }
        return userAccount;
    }


    /**
     * Logout the user
     */
    public static void logout() {
        try {
            userAccount = null;
        }catch(Exception e) {
            LOGGER.debug(e.toString());
        }
    }
}
