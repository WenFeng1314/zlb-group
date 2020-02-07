package com.zlb.authority.config;

import com.zlb.authority.model.UserInfo;
import com.zlb.authority.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

     @Autowired
     private UserDetailsService userDetailsService;
     @Autowired
     private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

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

        endpoints. // token存储
                tokenStore(jwtTokenStore()).
               // tokenStore(tokenStore()).
                // 自定义token生成方案
                accessTokenConverter(accessTokenConverter()).
                //tokenEnhancer(jwtTokenEnhancer()).
                //身份认证管理器, 主要用于"password"授权模式
                authenticationManager(authenticationManager).
                userDetailsService(userDetailsService);
        super.configure(endpoints);
    }
    @Bean
    public TokenStore tokenStore() {

        return  new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public AccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                Map<String, Object> additionalInformation = new HashMap<>();
                UserDetails userDetails = (UserDetails) authentication.getUserAuthentication().getPrincipal();
                UserInfo userInfo = new UserInfo();
                userInfo.setId(Constant.ID);
                userInfo.setCompanyId(Constant.COMPANY_ID);
                userInfo.setUsername(Constant.USER_NAME);
                userInfo.setCompanyName(Constant.COMPANY_NAME);
                userInfo.setPhone(Constant.USER_NAME_PHONE);
                userInfo.setType(Constant.TYPE);
                userInfo.setServiceCompanyId(Constant.SERVICE_COMPANY_ID);
                userInfo.setServiceCompanyName(Constant.SERVICE_COMPANY_NAME);
                try {
                    additionalInformation.put("userInfo", JSONUtil.obj2json(userInfo));
                    additionalInformation.put("code", 200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                return super.enhance(accessToken, authentication);

            }
        };

        // 设置签名
        jwtAccessTokenConverter.setSigningKey("WWF");
        return jwtAccessTokenConverter;

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
