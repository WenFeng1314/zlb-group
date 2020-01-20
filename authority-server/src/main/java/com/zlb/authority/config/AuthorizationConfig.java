package com.zlb.authority.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

     @Autowired
     private UserDetailsService userDetailsService;
     @Autowired
     private AuthenticationManager authenticationManager;

    /**
     * token的获取和检查
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");
        security.checkTokenAccess("isAuthorizated()");
    }

    /**
     * 配置第三方平台信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("web")
                .secret("web-secret")
                .authorizedGrantTypes("password")
                .scopes("all");
        super.configure(clients);
    }
    /**
     * 验证管理器
     * 配置token怎么产生,token 怎么存储
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.tokenStore(jwtTokenStore()).tokenEnhancer(jwtTokenEnhancer()).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
        super.configure(endpoints);
    }

    private JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 将ltd-jks.jks 放在classpath 里面就ok
        Resource resource = new ClassPathResource("ltd-jwt.jks");
        KeyStoreKeyFactory keyStore = new KeyStoreKeyFactory(resource, "ltd123".toCharArray());
        jwtAccessTokenConverter.setKeyPair(keyStore.getKeyPair("ltd-jwt"));
        return jwtAccessTokenConverter;
    }

    private TokenStore jwtTokenStore() {
          return new JwtTokenStore(jwtTokenEnhancer());
    }
//    @Bean
//    public UserDetailsService inMem() {
//        Collection<UserDetails> users = new ArrayList<UserDetails>();
//        HashSet<GrantedAuthority> ltdRoles = new HashSet<GrantedAuthority>();
//        ltdRoles.add(new SimpleGrantedAuthority("role-admin")); // admin role
//        User user = new User("ltd", "123456",ltdRoles);
//        users.add(user);
//        HashSet<GrantedAuthority> mjmRoles = new HashSet<GrantedAuthority>();
//        mjmRoles.add(new SimpleGrantedAuthority("role-user")); // user 角色
//        User user1 = new User("mjm", "123456",mjmRoles);
//        users.add(user1);
//        return new InMemoryUserDetailsManager(users );
//    }

}
