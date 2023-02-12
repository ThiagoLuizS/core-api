package br.com.coreapi.config.clients.keycloak.response;

import lombok.Data;

@Data
public class RoleResponse {
    private String id;
    private String name;
    private String description;
    private Boolean composite;
    private Boolean clientRole;
    private String containerId;
}
