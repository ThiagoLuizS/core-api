package br.com.coreapi.domain.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id", "datInclusao"})
@MappedSuperclass
public abstract class AbstractBaseId {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime datInclusao = LocalDateTime.now();

    @Column(name = "data_alteracao")
    private LocalDateTime datAlteracao;

}
