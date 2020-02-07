package com.zlb.authority.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class Constant implements UserDetails, Serializable {

    public static final Integer ID=1;
    public static final Integer COMPANY_ID=10;
    public static final String USER_NAME="张三";
    public static final String COMPANY_NAME="武汉找劳保网科技有限公司";
    public static final String USER_NAME_PHONE="17507229950";
    public static final Integer TYPE=2;
    public static final Integer SERVICE_COMPANY_ID=110;
    public static final String SERVICE_COMPANY_NAME="CC投资";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
