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
@Table(name = "tb_projeto")
@AllArgsConstructor
@NoArgsConstructor
public class Projeto extends AbstractBaseId implements BasePersistencia<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="unidade_id", nullable=false)
    private Unidade unidadeId;

    @Column(name = "nome_projeto", nullable = false, length = 50)
    private String nomProjeto;

    @Column(name = "numero_projeto", nullable = false, length = 50)
    private String numProjeto;

    @Column(name = "descricao_projeto_resumido", nullable = false, length = 50)
    private String desProjetoResumido;

    @Column(name = "descricao_especificacoes", length = 50)
    private String desEspecificacoes;

    @Column(name = "descricao_projeto", length = 50)
    private String desProjeto;

    @Column(name = "mne_responsavel", length = 50)
    private String mneResponsavel;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "mne_usuario")
    private String mneUsuario;

}