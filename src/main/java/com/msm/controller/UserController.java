package com.msm.controller;

import com.msm.helpers.Layout;
import com.msm.helpers.UserHelper;
import com.msm.model.UserAccount;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @PreAuthorize("hasRole('USER_ROLE')")
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

    @PreAuthorize("hasRole('USER_ROLE')")
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String products(Model model, HttpServletRequest request) {
        return "product/products";
    }

    @PreAuthorize("hasRole('USER_ROLE')")
    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public String invoice(Model model, HttpServletRequest request) {
        return "invoice/invoice";
    }

    @PreAuthorize("hasRole('USER_ROLE')")
    @RequestMapping(value = "/vendors", method = RequestMethod.GET)
    public String vendors(Model model, HttpServletRequest request) {
        return "vendor/vendors";
    }
}
