package com.unicorn.zuul.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Create by xuantang
 * @date on 1/1/18
 */
@Configuration
@EnableZuulProxy
//@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .csrf().disable();
    }


}
