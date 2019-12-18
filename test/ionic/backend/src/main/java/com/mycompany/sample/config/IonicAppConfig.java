/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sample.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author chhavi
 */
@Configuration
@EnableWebMvc
@EnableWebSecurity
@ComponentScan("com.mycompany.sample")
public class IonicAppConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Enable jdbc authentication
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**");
    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests().antMatchers("/register").permitAll().antMatchers("/login").permitAll().antMatchers("/resetpassword").permitAll().antMatchers("/home")
                .hasAnyRole("USER", "ADMIN").antMatchers("/newForm").hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin()
                .loginPage("/login").permitAll().and().logout().permitAll();

        http.csrf().disable();
    }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder authenticationMgr)
    // throws Exception {
    // authenticationMgr.inMemoryAuthentication().withUser("admin").password("admin").authorities("ROLE_USER").and()
    // .withUser("javainuse").password("javainuse").authorities("ROLE_USER",
    // "ROLE_ADMIN");
    // }    
}
