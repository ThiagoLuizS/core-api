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
@Entity(name = "tb_unidade")
public class Unidade extends AbstractBaseId implements BasePersistencia<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", nullable=false)
    private Cliente clienteId;

    @Column(name = "nome_unidade")
    private String nomUnidade;

    @Column(name = "desc_unidade_resumida")
    private String descUnidadeResumida;

    @Column(name = "mne_responsavel")
    private String mneResponsavel;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "mne_usuario")
    private String mneUsuario;
}
