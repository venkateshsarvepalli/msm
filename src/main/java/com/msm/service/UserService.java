package com.msm.service;

import com.msm.model.User;

/**
 *
 * Created by Venkatesh on 23/08/16.
 */
public interface UserService  {

    User findUserByToken(String token);
}
