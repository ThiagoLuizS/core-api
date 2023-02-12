package br.com.coreapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_tipo_produto")
@AllArgsConstructor
@NoArgsConstructor
public class TipoProduto extends AbstractBaseId implements BasePersistencia<Long> {

    @Column(name = "des_tipo_produto", nullable = false, length = 50)
    private String desTipoProduto;

    @Column(name = "mne_usuario")
    private String mneUsuario;
}