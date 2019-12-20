/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sample.dao;

import com.mycompany.sample.ionicPojo.MailTempStorage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author chhavi
 */
public interface MailTempStorageDao extends JpaRepository<MailTempStorage,Long>{

//    @Query("Select l from MailTempStorage l where l.temp_id=token")
    public List<MailTempStorage> findById( String token);

//    public void delete(String token);
    
}
