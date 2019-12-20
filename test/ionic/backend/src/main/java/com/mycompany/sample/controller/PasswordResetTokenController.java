///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.sample.controller;
//
//import com.mycompany.sample.ionicPojo.Login;
//import com.mycompany.sample.service.LoginService;
//import java.util.UUID;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// *
// * @author chhavi
// */
//@RestController
//@RequestMapping("/api")
//public class PasswordResetTokenController {
//    @RequestMapping(value = "/check", method = RequestMethod.POST)
//@ResponseBody
//public GenericResponse resetPassword(HttpServletRequest request, 
//  @RequestParam("email") String userEmail) {
//    Login user = LoginService.findEmail(userEmail);
//    if (user == null) {
//        throw new UserNotFoundException();
//    }
//    String token = UUID.randomUUID().toString();
//    LoginService.createPasswordResetTokenForUser(user, token);
//    mailSender.send(constructResetTokenEmail(getAppUrl(request), 
//      request.getLocale(), token, user));
//    return new GenericResponse(
//      messages.getMessage("message.resetPasswordEmail", null, 
//      request.getLocale()));
//}
//}
