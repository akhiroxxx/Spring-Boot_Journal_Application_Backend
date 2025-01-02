package com.akhilesh.journal_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.akhilesh.journal_app.service.CustomUserDetailsServiceIMPL;

@Configuration
public class SecurityConfig {
  
  @Autowired
  private CustomUserDetailsServiceIMPL customUserDetailsServiceIMPL;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
    return httpSecurity.csrf(x->x.disable()).authorizeRequests(auth->
    auth
    .requestMatchers("/public/**").permitAll()
    .requestMatchers("/journal/**", "/user").authenticated()
    .anyRequest().authenticated())
    .httpBasic(Customizer.withDefaults()).formLogin(login->login.permitAll()).cors(x->x.disable()).build();
  }
  
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(customUserDetailsServiceIMPL);
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }
}
