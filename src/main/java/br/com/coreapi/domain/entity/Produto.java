package br.com.coreapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_produto")
@AllArgsConstructor
@NoArgsConstructor
public class Produto extends AbstractBaseId implements BasePersistencia<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tipo_produto_id", nullable = false)
    private TipoProduto tipoProdutoId;

    @Column(name = "nome_produto", nullable = false, length = 50)
    private String nomProduto;

    @Column(name = "descricao_comprimento", nullable = false, length = 50)
    private String desComprimento;

    @Column(name = "descricao_largura", nullable = false, length = 50)
    private String desLargura;

    @Column(name = "descricao_altura", length = 50)
    private String desAltura;

    @Column(name = "descricao_capacidade", length = 50)
    private String desCapacidade;

    @Column(name = "descricao_peso", length = 50)
    private String desPeso;

    @Column(name = "mne_usuario")
    private String mneUsuario;

}