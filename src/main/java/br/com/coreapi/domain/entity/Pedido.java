package br.com.coreapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_pedido")
public class Pedido extends AbstractBaseId implements BasePersistencia<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cliente_id", nullable=false)
    private Cliente clienteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="produto_id", nullable=false)
    private Produto produtoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="status_pedido_id", nullable=false)
    private StatusPedido statusPedido;

    @Column(name = "numero_pedido")
    private Integer numPedido;

    @Column(name = "numero_item")
    private Integer numItem;

    @Column(name = "quantidade_produto")
    private Integer qtdProduto;

    @Column(name = "mne_usuario")
    private String mneUsuario;

}
