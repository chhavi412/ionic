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
import com.mycompany.sample.ionicPojo.Test;
import com.mycompany.sample.service.mail.SendMail;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author chhavi
 */
@Service
public class LoginService extends SendMail {

    @Autowired
    private SendMail sendMail;

//    @Autowired
//    private MailTempStorage mailTempStorage;
//
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
                mailTempStorage.setSentAt(Timestamp.valueOf(LocalDateTime.now()));
                mailTempStorage.setExpireAt(Timestamp.valueOf(LocalDateTime.now().plusMinutes(5)));
                mailTempStorageDao.save(mailTempStorage);
                sendMail.sendMail("chhavi.gupta412@gmail.com", db.get(0).getEmail(), "mail confirmation to change password", "<p><a>click here</a> to change password</p>", mailTempStorage.getId());
                return true;
            } else {
//                throw new UserNotFoundException();
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public String changepassword(Test newPassword) throws Exception{
        List<MailTempStorage> db = new ArrayList<>();
        db = mailTempStorageDao.findById(newPassword.getToken());
        List<Login> db1 = new ArrayList<>();
        db1 = loginDao.findEmail(db.get(0).getEmail());
        System.out.println("email found in temp " + db.get(0).getEmail());

        try {
            if(db.isEmpty()){
                return "generated token doesn't exist anymore";
            }
            if (!Timestamp.valueOf(LocalDateTime.now()).after(db.get(0).getExpireAt())) {
                if (db.get(0).getEmail().equals(db1.get(0).getEmail())) {
                    System.out.println("changing password");
                    newPassword.setNewPassword(bCryptPasswordEncoder.encode(newPassword.getNewPassword()));
                    loginDao.setPasswordByEmail(db1.get(0).getEmail(), newPassword.getNewPassword());
                    mailTempStorageDao.delete(db);
                    return "password changed successfully";
                } else {
                    mailTempStorageDao.delete(db);
                    return "some error occurred";
                }
            } else {
                mailTempStorageDao.delete(db);
                return "link got expired";
            }
            

        } catch (Exception e) {
            System.out.println(e);
        }
        return "something unexpected happen please try again after some time";
    }

}
