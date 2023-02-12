package br.com.coreapi.config.clients.keycloak.response;

import lombok.Data;

@Data
public class SecretResponse {
    private String type;
    private String value;
}
