package com.msm.repositories;

import com.msm.model.UserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * Created by Venkatesh on 23/08/16.
 */
public interface UserRepository extends CrudRepository<UserAccount, String> {

    UserAccount findUserByEmail(@Param("email") String email);

    @Query("select user from UserAccount user " +
            "where user.email = :email AND email.password = :password")
    UserAccount authenticate(@Param("email")String email, @Param("password") String password);
}
