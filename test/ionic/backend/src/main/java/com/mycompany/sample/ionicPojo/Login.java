/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sample.ionicPojo;

import java.util.UUID;
import javax.persistence.CascadeType;
//import javax.jws.Oneway;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author chhavi
 */
@Entity
@Table(name= "user_details")
public class Login {
    
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email", unique = true)
    private String email;
    
    @Column(name ="password", nullable = false)
    private String password;
    
    @Column(name ="user_role")
    private String userRole;
    
    public Login(){
    
}

    public Login(String name, String email, String password, String userRole) {
        this.email=email;
        this.name=name;
        this.password=password;
        this.userRole=userRole;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    
}
