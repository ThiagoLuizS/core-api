package br.com.coreapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_cliente")
public class Cliente extends AbstractBaseId implements BasePersistencia<Long> {

    @Column(name = "cnpj")
    private String numCnpjCliente;

    @Column(name = "nome")
    private String nome;

    @Column(name = "apelido")
    private String apelido;

    @Column(name = "descricao_endereco")
    private String desEndereco;

    @Column(name = "numero_endereco")
    private String numEndereco;

    @Column(name = "descricao_complemento")
    private String desComplemento;

    @Column(name = "nome_bairro")
    private String nomBairro;

    @Column(name = "nome_cidade")
    private String nomCidade;

    @Column(name = "codigo_uf")
    private String codUF;

    @Column(name = "numero_cep")
    private Integer numCep;

    @Column(name = "ddd_telefone")
    private Integer infDDDtelCliente;

    @Column(name = "numero_telefone")
    private Integer telCliente;

    @Column(name = "ddd_celular")
    private Integer infDDDcelCliente;

    @Column(name = "numero_celular")
    private Integer celCliente;

    @Column(name = "fax_cliente")
    private String faxCliente;

    @Column(name = "nome_contato")
    private String nomContato;

    @Column(name = "ddd_telefone_contato")
    private Integer infDDDtelContato;

    @Column(name = "numero_telefone_contato")
    private Integer telContato;

    @Column(name = "ddd_celular_contato")
    private Integer infDDDcelContato;

    @Column(name = "numero_celular_contato")
    private Integer celContato;

    @Column(name = "email_contato")
    private String emlContato;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "mne_usuario")
    private String mneUsuario;

}
