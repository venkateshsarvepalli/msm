package com.msm.controller;

import com.msm.helpers.Layout;
import com.msm.helpers.UserHelper;
import com.msm.model.UserAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Venkatesh on 23/08/16.
 */
@Controller
public class UserController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model, HttpServletRequest request) {
        UserAccount userAccount = UserHelper.getCurrentUser();
        model.addAttribute("userAccount", userAccount);
        return "home";
    }

    @Layout("layouts/default/index")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        return "hello";
    }
}
