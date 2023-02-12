package br.com.coreapi.config.clients.keycloak.request.get;

import br.com.coreapi.config.clients.keycloak.response.AuthenticationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "keycloakAdminAuthentication", url = "${coreapi.keycloak.oauth-url}")
public interface KeycloakAdminAuthenticationService {
    @PostMapping(consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    AuthenticationResponse auth(@RequestBody Object param);

    @PostMapping()
    ResponseEntity<?> createRealm(@RequestBody Object requestDTO);
}
