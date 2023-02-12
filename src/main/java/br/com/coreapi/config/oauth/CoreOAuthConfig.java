package br.com.coreapi.config.oauth;

import br.com.coreapi.config.clients.keycloak.request.get.KeycloakAdminAuthenticationService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;

//@Configuration
@RequiredArgsConstructor
public class CoreOAuthConfig {
    @Value("${coreapi.keycloak.username}")
    private String username;
    @Value("${coreapi.keycloak.password}")
    private String password;
    @Value("${coreapi.keycloak.grant-type}")
    private String grantType;
    @Value("${coreapi.keycloak.client-id}")
    private String clientId;
    @Value("${coreapi.keycloak.root-url}")
    private String keycloakUrl;
    @Value("${coreapi.keycloak.realm}")
    private String realm;

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String KEYCLOAK_USER = "username=";
    private static final String KEYCLOAK_PWD = "&password=";
    private static final String GRANT_TYPE = "&grant_type=";
    private static final String CLIENT_ID = "&client_id=";

    private final KeycloakAdminAuthenticationService authenticationRequest;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            var destination = DestinationApiURL.valueOf(requestTemplate.feignTarget().url().replace("http://", ""));
            if (DestinationApiURL.KEYCLOAK_ADM_API.equals(destination)) {
                requestInterceptorKeycloakAdm(requestTemplate);
            }
        };
    }

    private void requestInterceptorKeycloakAdm(RequestTemplate requestTemplate) {
        var response = authenticationRequest.auth(getParametersToLogin());
        requestTemplate.header(HEADER_AUTHORIZATION, "Bearer " + response.getAccessToken());
        requestTemplate.target(getURL(keycloakUrl, realm));
    }

    private String getURL(String url, String realm) {
        return new StringBuilder().append(url).append(realm).toString();
    }

    public byte[] getParametersToLogin() {
        var builder = new StringBuilder();
        builder.append(KEYCLOAK_USER).append(username);
        builder.append(KEYCLOAK_PWD).append(password);
        builder.append(GRANT_TYPE).append(grantType);
        builder.append(CLIENT_ID).append(clientId);

        return builder.toString().getBytes(StandardCharsets.UTF_8);
    }

}
