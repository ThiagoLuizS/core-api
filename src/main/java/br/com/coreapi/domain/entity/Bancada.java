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
@Table(name = "tb_bancada")
@AllArgsConstructor
@NoArgsConstructor
public class Bancada extends AbstractBaseId implements BasePersistencia<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="local_id", nullable = false)
    private Local localId;

    @Column(name = "descricao_bancada")
    private String desBancada;

    @Column(name = "total_posicoes")
    private String totPosicoes;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "mne_usuario")
    private String mneUsuario;

}