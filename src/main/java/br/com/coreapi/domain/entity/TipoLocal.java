package br.com.coreapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_tipo_local")
@AllArgsConstructor
@NoArgsConstructor
public class TipoLocal extends AbstractBaseId implements BasePersistencia<Long> {

    @Column(name = "des_tipo_local")
    private String desTipoLocal;

    @Column(name = "mne_usuario")
    private String mneUsuario;

}