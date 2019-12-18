/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sample.dao;

import com.mycompany.sample.ionicPojo.Login;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chhavi
 */
@Repository
public interface LoginDao extends JpaRepository<Login,Long>{
    
    @Query("SELECT l FROM Login l WHERE l.email=:email")
    public List<Login> findEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("update Login l set l.password=?2 where l.email= ?1")
    public void setPasswordByEmail(@Param("email") String email, @Param("password") String password);

    
}
