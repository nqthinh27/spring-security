package com.nodo.springverifyemail.controller;

import com.nodo.springverifyemail.entity.User;
import com.nodo.springverifyemail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public String register(@RequestBody User user,  HttpServletRequest request) {
        return userService.register(user, getSiteURL(request));
    }
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
    @GetMapping("")
    public String a() {
        return "abc";
    }

    @GetMapping("verify")
    public String verifyUser(@Param("key") String key) {
        return userService.verify(key);
    }
}
