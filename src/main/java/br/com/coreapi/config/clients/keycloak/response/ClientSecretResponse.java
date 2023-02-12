package br.com.coreapi.config.clients.keycloak.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientSecretResponse {
    private String clientId;
    private String secret;
}
