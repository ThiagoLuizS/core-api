package br.com.coreapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_status_pedido")
@AllArgsConstructor
@NoArgsConstructor
public class StatusPedido extends AbstractBaseId implements BasePersistencia<Long> {

    @Column(name = "label", nullable = false, length = 50)
    private String label;

    @Column(name = "ordem", nullable = false, length = 50)
    private String ordem;

    @Column(name = "ativo")
    private Boolean ativo;

}