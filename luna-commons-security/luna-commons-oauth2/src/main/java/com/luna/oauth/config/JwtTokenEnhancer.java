package com.luna.oauth.config;

import com.google.common.collect.Maps;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;

/**
 * @Package: com.luna.oauth.config
 * @ClassName: JwtTokenEnhancer
 * @Author: luna
 * @CreateTime: 2020/10/1 16:32
 * @Description:
 */
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        HashMap<String, Object> map = Maps.newHashMap();
        // 添加增强参数
        map.put("enhance", "enhancer info");
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(map);
        return accessToken;
    }
}
