package br.com.coreapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Audited
@Data
@EqualsAndHashCode(callSuper = true, of = {})
@ToString(callSuper = true, of = {"name"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_perfil_usuario")
public class PerfilUsuario extends AbstractBaseId {

    @Column(name = "nome", nullable = false, length = 128)
    private String name;

    @Column(name = "descricao", length = 512)
    private String description;

    @Column(name = "mascara_permissao_base64", nullable = false)
    private String permissionMaskBase64 = "";

}
