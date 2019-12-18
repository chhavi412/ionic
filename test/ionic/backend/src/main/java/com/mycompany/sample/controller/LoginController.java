/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sample.controller;

//import com.mycompany.sample.ionicPojo.Login;

import com.mycompany.sample.ionicPojo.Login;
import com.mycompany.sample.service.LoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chhavi
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private LoginService theService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Boolean isAuthenticated(@RequestBody Login theUser) {
        return theService.isAuthenticated(theUser);

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Boolean create(@RequestBody Login theUser) {
        theService.save(theUser);
        return true;
    }
    
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public boolean check(@RequestBody Login theUser) {
        return theService.check(theUser);
    }
    
    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public boolean changepassword(@RequestBody Login theUser){
        return theService.changepassword(theUser);
    }
}
