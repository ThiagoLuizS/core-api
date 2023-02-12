package br.com.coreapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true, of = {})
@ToString(callSuper = true, of = {})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_historico_usuario")
public class HistoricoUsuario extends AbstractBaseId {

    @ManyToOne
    @JoinColumn(name = "usuario_id", updatable = false)
    private Usuario usuario;

    @Column(name = "atividade", length = 4096, updatable = false)
    private String atividade;

}
