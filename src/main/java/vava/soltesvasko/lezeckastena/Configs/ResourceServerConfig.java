package vava.soltesvasko.lezeckastena.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private DefaultTokenServices tokenServices;

    @Override
    public void configure(ResourceServerSecurityConfigurer RSSC)
    {
        RSSC.resourceId("ids").tokenServices(tokenServices);
    }
    public void configure(HttpSecurity http) throws Exception
    {
        http.requestMatchers().and().authorizeRequests().antMatchers("/climber/*").authenticated();
    }
}
