package br.com.coreapi.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Audited
@Data
@EqualsAndHashCode(callSuper = true, of = {})
@ToString(callSuper = true, of = { "username" })
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario extends AbstractBaseId implements BasePersistencia<Long> {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perfil_usuario_id")
    private PerfilUsuario perfil;

    @Column(name = "username", length = 128, nullable = false, unique = true)
    private String username;

    @Column(name = "mascara_permissao_base64")
    private String permissaoMascaraBase64 = "";

    @Column(name = "nome", length = 128)
    private String nome;

    @Column(name = "email", length = 128)
    private String email;

    @Column(name = "hash_senha")
    private String hashSenha;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = Boolean.TRUE;

    @Column(name = "data_senha_alterada")
    private LocalDateTime dataSenhaAlterada;

    @Column(name = "token_senha_redefinida")
    private String tokenSenhaRedefinida;

    @Column(name = "bloqueado")
    private Boolean bloqueado;

    @Column(name = "senha_expirada")
    private boolean senhaExpirada;

}
