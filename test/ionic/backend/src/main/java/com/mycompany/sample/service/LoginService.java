/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sample.service;

import com.mycompany.sample.dao.LoginDao;
import com.mycompany.sample.dao.MailTempStorageDao;
import com.mycompany.sample.ionicPojo.Login;
import com.mycompany.sample.ionicPojo.MailTempStorage;
import com.mycompany.sample.service.mail.SendMail;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author chhavi
 */
@Service
public class LoginService {

    @Autowired
    private SendMail sendMail;
    
    @Autowired
    private MailTempStorage mailTempStorage;
    
    @Autowired
    private MailTempStorageDao mailTempStorageDao;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private LoginDao loginDao;

    public Boolean isAuthenticated(Login theUser) {
        List<Login> db = new ArrayList<>();
        db = loginDao.findEmail(theUser.getEmail());
        Boolean isAuth = bCryptPasswordEncoder.matches(theUser.getPassword(), db.get(0).getPassword());

        if (isAuth) {
            return true;
        } else {
            return false;
        }
    }

    public Login save(Login theUser) {
        theUser.setId();
        theUser.setPassword(bCryptPasswordEncoder.encode(theUser.getPassword()));
        return loginDao.save(theUser);
    }

    public boolean check(Login theUser) {
        List<Login> db = new ArrayList<>();
        db = loginDao.findEmail(theUser.getEmail());
        try {
            if (db.get(0).getEmail().equals(theUser.getEmail())) {
                
                MailTempStorage mailTempStorage = new MailTempStorage();
                mailTempStorage.setId();
                mailTempStorage.setEmail(theUser.getEmail());
                mailTempStorage.setSentAt(LocalDateTime.now());
                mailTempStorageDao.save(mailTempStorage);
                sendMail.sendMail("chhavi.gupta412@gmail.com", db.get(0).getEmail(), "mail confirmation to change password", "<p><a href='http://192.1.200.146:8100/changepassword'>click here</a> to change password</p>", mailTempStorage.getId());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public boolean changepassword(Login theUser) {
        List<Login> db = new ArrayList<>();
        db = loginDao.findEmail(theUser.getEmail());
        try {
            if (db.get(0).getEmail().equals(theUser.getEmail())) {
                theUser.setPassword(bCryptPasswordEncoder.encode(theUser.getPassword()));
                loginDao.setPasswordByEmail(theUser.getEmail(),theUser.getPassword());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
