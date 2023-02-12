package br.com.coreapi.config.clients.keycloak.request.get;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "KeycloakAdminRealmCreation", url = "${coreapi.keycloak.root-url}")
public interface KeycloakAdminRealmCreation {

    @PostMapping()
    ResponseEntity<?> createRealm(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,
                                  @RequestBody Object requestDTO);
    @PostMapping()
    ResponseEntity<?> createClient(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,
                                  @RequestBody Object requestDTO);
}
