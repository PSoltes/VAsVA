package vava.soltesvasko.lezeckastena.Configs;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import vava.soltesvasko.lezeckastena.Data.Climber;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken token, OAuth2Authentication auth)
    {
        ClimberDetails cl = (ClimberDetails) auth.getPrincipal();
        Map<String, Object> id = new HashMap();
        id.put("id", cl.getId());
        id.put("profilePic", cl.getProfilePicPath());
        ((DefaultOAuth2AccessToken) token).setAdditionalInformation(id);

        return token;
    }
}
