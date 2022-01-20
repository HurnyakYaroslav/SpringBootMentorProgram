package com.mentorship.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider provider;

    @Autowired
    public JwtConfigurer(JwtTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        JwtTokenFilter filter = new JwtTokenFilter(provider);
        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

    }
}
