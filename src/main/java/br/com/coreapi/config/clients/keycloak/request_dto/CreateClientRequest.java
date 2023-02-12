package br.com.coreapi.config.clients.keycloak.request_dto;


import br.com.coreapi.domain.entity.PessoaJuridica;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class CreateClientRequest {
    private String name;
    private String description;
    private String protocol;
    private Boolean publicClient;
    private Boolean bearerOnly;
    private String rootUrl;
    private List<String> redirectUris;
    private List<String> webOrigins;

    public CreateClientRequest(PessoaJuridica pessoaJuridica, String description, String protocol, String url) {
        name = pessoaJuridica.getNomeSocial();
        this.description = description;
        this.protocol = protocol;
        publicClient = false;
        bearerOnly = false;
        rootUrl = url;
        redirectUris = Arrays.asList(url + "*");
        webOrigins = Arrays.asList(url, "*");
    }
}
