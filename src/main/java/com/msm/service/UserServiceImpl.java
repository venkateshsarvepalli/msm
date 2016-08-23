package com.msm.service;

import com.msm.model.User;
import com.msm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Created by Venkatesh on 23/08/16.
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl() {
        super();
    }

    @Override
    public User findUserByToken(String token) {
        return null;
    }


}
