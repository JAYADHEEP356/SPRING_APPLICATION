package com.example.quiz.Configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class SecurityConfig {

 private final DataSource dataSource;

 public SecurityConfig(DataSource dataSource){

     this.dataSource = dataSource;
 }

 @Bean
  public JdbcUserDetailsManager userDetailsManager(){

     return new JdbcUserDetailsManager(dataSource);

  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

         security.authorizeHttpRequests(configurer -> configurer.
                         requestMatchers("/signUp").permitAll()
                         .requestMatchers("/AddUser").permitAll()
                         .requestMatchers("/success")
                         .permitAll().anyRequest().authenticated())
                 .formLogin(form ->form.loginPage("/login").loginProcessingUrl("/authenticate").permitAll())
                 .csrf(AbstractHttpConfigurer::disable);

         return security.build();


  }


}
