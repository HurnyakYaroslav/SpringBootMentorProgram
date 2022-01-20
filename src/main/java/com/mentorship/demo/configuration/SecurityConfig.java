package com.mentorship.demo.configuration;

import com.mentorship.demo.security.JwtConfigurer;
import com.mentorship.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider provider;

//    private static final String ADMIN_ENDPOINT = "/api/faculties/**";
//    private static final String ADMIN_ENDPOINT = "/swagger-ui.html#/authentication-controller/**";
    private static final String LOGIN_ENDPOINT = "/api/auth/login";
    private static final String ABITURIENTS_ENDPOINT = "/api/abiturients/**";


    @Autowired
    public SecurityConfig(JwtTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
              .httpBasic().disable()
              .csrf().disable()
              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
              .authorizeRequests()
              .antMatchers(ABITURIENTS_ENDPOINT).permitAll()
              .antMatchers(LOGIN_ENDPOINT).permitAll()
//              .antMatchers("/api/auth/login").permitAll()
              .antMatchers("/v2/api-docs",
                      "/configuration/ui",
                      "/swagger-resources/**",
                      "/configuration/security",
                      "/swagger-ui.html",
                      "/webjars/**").permitAll()
              .anyRequest().authenticated()
              .and()
              .apply(new JwtConfigurer(provider));

    }
}
