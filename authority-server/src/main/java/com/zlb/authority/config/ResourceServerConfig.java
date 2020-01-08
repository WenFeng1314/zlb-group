package com.zlb.authority.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        /**
         * 自定义权限保护规则
         */
                // 禁用csrf保护
           http .csrf().disable()
                // 授权请求
                .authorizeRequests().antMatchers( "resource/findByRoleCode/500").permitAll()
                .anyRequest().authenticated();    }
}
