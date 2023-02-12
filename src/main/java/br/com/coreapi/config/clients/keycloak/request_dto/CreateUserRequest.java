package br.com.coreapi.config.clients.keycloak.request_dto;

import br.com.coreapi.domain.dto.request.UserRequestDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CreateUserRequest {
    private String id;
    private String username;
    private String email;
    private boolean enabled;
    private List<CredentialRepresentationRequest> credentials = new ArrayList<>();
    private List<String> groups = new ArrayList<>();
    private String firstName;
    private String lastName;

    public CreateUserRequest(UserRequestDTO usuarioDTO, String group) {
        this.username = usuarioDTO.getUsername();
        this.groups.add(group);
        this.enabled = true;
        this.credentials.add(new CredentialRepresentationRequest("password", usuarioDTO.getPassword(), false));
    }

    public CreateUserRequest(String id, UserRequestDTO usuarioDTO) {
        this(usuarioDTO);
        this.id = id;
    }

    public CreateUserRequest(UserRequestDTO usuarioDTO) {
        this();
        this.username = usuarioDTO.getUsername();
        this.email = usuarioDTO.getEmail();
        this.groups.add(usuarioDTO.getClientId());
        this.enabled = true;
        this.credentials.add(new CredentialRepresentationRequest("password", usuarioDTO.getPassword(), false));
        if (StringUtils.isNotEmpty(usuarioDTO.getName())) {
            String[] names = usuarioDTO.getName().split(" ");
            String firstNameTemp = names[0];
            StringBuilder lastNameTemp = new StringBuilder();
            for (int i = 1; i < names.length; i++) {
                lastNameTemp.append(" " + names[i]);
            }
            this.firstName = firstNameTemp;
            this.lastName = lastNameTemp.toString().trim();
        }
    }
}
