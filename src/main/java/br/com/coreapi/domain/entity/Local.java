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
@Table(name = "tb_local")
@AllArgsConstructor
@NoArgsConstructor
public class Local extends AbstractBaseId implements BasePersistencia<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="projeto_id", nullable = false)
    private Projeto projetoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tipo_local_id", nullable = false)
    private TipoLocal tipoLocalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tipo_administracao_id", nullable=false)
    private TipoAdministracao tipoAdministracaoId;

    @Column(name = "mne_local", nullable = false, length = 50)
    private String mneLocal;

    @Column(name = "mne_local_resumido", nullable = false, length = 50)
    private String desLocalResumido;

    @Column(name = "des_local", nullable = false, length = 50)
    private String desLocal;

    @Column(name = "des_especificacoes_local", length = 50)
    private String desEspecificacoesLocal;

    @Column(name = "possui_bancadas")
    private Boolean flgPossuiBancadas;

    @Column(name = "armazenagem")
    private Boolean flgArmazenagem;

    @Column(name = "ativo")
    private Boolean flgAtivo;

    @Column(name = "mne_responsavel")
    private String mneResponsavel;

    @Column(name = "mne_usuario")
    private String mneUsuario;

}