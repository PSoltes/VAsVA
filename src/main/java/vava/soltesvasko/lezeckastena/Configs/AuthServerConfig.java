package vava.soltesvasko.lezeckastena.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.token.DefaultToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
@Order(100)
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Bean
    public CustomTokenEnhancer customTokenEnhancer()
    {
        return new CustomTokenEnhancer();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer ASEC)
    {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter(), customTokenEnhancer()));
        ASEC.tokenStore(JWTTokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain)
                .userDetailsService(myUserDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer ASCA)
    {
        ASCA.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer CDSC) throws Exception
    {
        CDSC.inMemory()
                .withClient("myClientPassword")
                .authorizedGrantTypes("password", "refresh_token")
                .secret("{noop}secret")
                .scopes("read")
                .resourceIds("ids");
    }


    @Bean
    public TokenStore JWTTokenStore()
    {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter()
    {
        JwtAccessTokenConverter JWTconverter = new JwtAccessTokenConverter();
        JWTconverter.setSigningKey("LOLOLO");
        return JWTconverter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices()
    {
        DefaultTokenServices DTS = new DefaultTokenServices();
        DTS.setTokenStore(JWTTokenStore());
        return DTS;
    }
}
