package br.com.coreapi.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String guid;
    private String guidEstablishment;
    private String clientId;
    private String username;
    private String password;
    private String name;
    private String email;
}
