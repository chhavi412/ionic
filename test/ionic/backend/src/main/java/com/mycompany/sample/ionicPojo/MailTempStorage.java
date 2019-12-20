/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sample.ionicPojo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author chhavi
 */
@Entity
@Table(name = "mail_temp_storage")
public class MailTempStorage {

    private static final int EXPIRATION = 5 * 1;
    
    @Column(name = "temp_id")
    String id;
    
    @Id
    @Column(name = "email")
    String email;
    
    @Column(name = "sent_mail_at", nullable = false)
    Timestamp sentAt;
    
    @Column(name = "expire_at", nullable = false)
    Timestamp expireAt;
    
    public Timestamp getSentAt() {
        return sentAt;
    }
    
    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
//        this.setExpireAt(sentAt+EXPIRATION);
    }
    
    public String getId() {
        return id;
    }
    
    public void setId() {
        this.id = UUID.randomUUID().toString();
//        this.sentAt= new LocalDateTime.now();
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Timestamp getExpireAt() {
        return expireAt;
    }
    
    public void setExpireAt(Timestamp expireAt) {
        this.expireAt = expireAt;
    }
    
}
