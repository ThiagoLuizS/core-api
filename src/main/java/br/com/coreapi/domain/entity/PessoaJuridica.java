package br.com.coreapi.domain.entity;


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
@EqualsAndHashCode(callSuper = true, of = { "cnpj" })
@ToString(callSuper = true, of = { "cnpj" })
@NoArgsConstructor
@Table(name = "tb_pessoa_juridica")
@Entity
public class PessoaJuridica extends AbstractBaseId implements BasePersistencia<Long>  {

    public static final String EMAIL_SEPARATOR = ";";

    @Column(name = "cnpj", nullable = false, length = 24)
    private String cnpj;

    @Column(name = "nome_social", nullable = false)
    private String nomeSocial;

    @Column(name = "cep", nullable = false, length = 16)
    private String cep;

    @Column(name = "desc_endereco", nullable = false)
    private String desEndereco;

    @Column(name = "numero", nullable = false, length = 10)
    private String numero;

    @Column(name = "nome_bairro", nullable = false)
    private String nomBairro;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "ddd", length = 5)
    private Integer ddd;

    @Column(name = "numero_telefone")
    private Integer numeroTelefone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "email_financeiro")
    private String emailFinanceiro;

    @Column(name = "email_adicional")
    private String emailAdicional;

    @Column(name = "email_fatura")
    private String emailFatura;

}
