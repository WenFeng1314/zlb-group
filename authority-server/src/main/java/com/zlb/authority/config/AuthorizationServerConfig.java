package com.zlb.authority.config;

import com.zlb.authority.domain.BaseUser;
import com.zlb.authority.domain.BaseUserDetail;
import com.zlb.authority.domain.SecurityConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecurityConfigProperties properties;
    /*
    * 用来配置客户端详情服务（ClientDetailsService），
    * 客户端详情信息在这里进行初始化，
    * 你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
    * */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
      security.allowFormAuthenticationForClients()
              // 开启/oauth/token_key验证端口无权限访问
              .tokenKeyAccess("permitAll()")
              // 开启/oauth/check_token验证端口认证权限访问
              .checkTokenAccess("isAuthenticated()");
    }
    /*
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     * */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /**
         * 配置客户端详情信息(Client Details)
         * clientId：（必须的）用来标识客户的Id。
         * secret：（需要值得信任的客户端）客户端安全码，如果有的话。
         * scope：用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
         * authorizedGrantTypes：此客户端可以使用的授权类型，默认为空。
         * authorities：此客户端可以使用的权限（基于Spring Security authorities）。
         */
        clients.inMemory()
                .withClient(properties.getClientId())
                .secret(properties.getClientSecret())
                .scopes(properties.getScope())
                // 支持的授权模式, 共4种, 这里配置了最常用的3种
                .authorizedGrantTypes("password", "refresh_token", "authorization_code");
    }
    /*
    * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
    * */

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置JwtAccessToken转换器
        endpoints.authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter())
                .reuseRefreshTokens(false)
                .userDetailsService(userDetailsService);

        super.configure(endpoints);
    }
    /**
     * 使用非对称加密算法来对Token进行签名
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
         JwtAccessTokenConverter converter = new JwtAccessTokenConverter(){
             @Override
             public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                 DefaultOAuth2AccessToken defaultOAuth2AccessToken= new DefaultOAuth2AccessToken(accessToken);
                 // 设置额外用户信息
                 BaseUser baseUser =((BaseUserDetail)authentication.getPrincipal()).getBaseUser();
                 // 将用户信息添加到token额外信息中
                 defaultOAuth2AccessToken.getAdditionalInformation().put("userInfo",baseUser);

                 return super.enhance(defaultOAuth2AccessToken, authentication);
             }
         };
//        // 导入证书
//        KeyStoreKeyFactory keyStoreKeyFactory =
//                new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "mypass".toCharArray());
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));

        return converter;
    }
}
