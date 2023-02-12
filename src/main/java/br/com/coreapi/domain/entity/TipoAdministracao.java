package br.com.coreapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_tipo_administracao")
@AllArgsConstructor
@NoArgsConstructor
public class TipoAdministracao extends AbstractBaseId implements BasePersistencia<Long> {

    @Column(name = "label")
    private String label;

    @Column(name = "ordem")
    private String ordem;

    @Column(name = "ativo")
    private Boolean ativo;

}