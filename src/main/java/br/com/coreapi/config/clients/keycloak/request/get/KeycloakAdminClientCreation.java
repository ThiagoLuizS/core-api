package br.com.coreapi.config.clients.keycloak.request.get;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "KeycloakAdminClientCreation", url = "${coreapi.keycloak.root-url}")
public interface KeycloakAdminClientCreation {

    @PostMapping("{realm}/clients")
    ResponseEntity<?> createClient(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,
                                  @RequestBody Object requestDTO, @PathVariable("realm") String realm);
}
