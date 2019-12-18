/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sample.dao;

import com.mycompany.sample.ionicPojo.MailTempStorage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author chhavi
 */
public interface MailTempStorageDao extends JpaRepository<MailTempStorage,Long>{
    
}
