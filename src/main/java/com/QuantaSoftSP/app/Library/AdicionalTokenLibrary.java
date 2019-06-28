package com.QuantaSoftSP.app.Library;

import com.QuantaSoftSP.app.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import com.QuantaSoftSP.app.Entity.Usuario;
import java.util.HashMap;
import java.util.Map;
@Component
public class AdicionalTokenLibrary implements TokenEnhancer {

    @Autowired
    private IUserService usuarioService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Usuario usuario = usuarioService.findByNombre(oAuth2Authentication.getName());
        Map<String, Object> info = new HashMap<>();
        info.put("Bienvenido : ", usuario.getNombre()+" "+ usuario.getApellidop() +" "+ usuario.getApellidom());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
