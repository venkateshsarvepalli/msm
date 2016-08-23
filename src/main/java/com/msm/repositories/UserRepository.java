package com.msm.repositories;

import com.msm.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * Created by Venkatesh on 23/08/16.
 */
public interface UserRepository extends CrudRepository<User, String> {

    User findUserByEmail(@Param("email") String email);

    @Query("select user from User user " +
            "where user.email = :email AND email.password = :password")
    User authenticate(@Param("email")String email,@Param("password") String password);
}
