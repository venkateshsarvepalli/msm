package com.msm.service;

import com.msm.model.UserAccount;

/**
 *
 * Created by Venkatesh on 23/08/16.
 */
public interface UserService  {

    UserAccount findUserByToken(String token);
}
