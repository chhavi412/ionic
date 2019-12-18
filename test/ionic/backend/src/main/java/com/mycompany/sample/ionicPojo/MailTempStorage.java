/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sample.ionicPojo;

import java.time.LocalDateTime;
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

    @Column(name = "temp_id")
    String id;

    @Id
    @Column(name = "email")
    String email;
    
    @Column(name = "sent_mail_at" ,nullable=false)
    LocalDateTime sentAt;

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
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

}
