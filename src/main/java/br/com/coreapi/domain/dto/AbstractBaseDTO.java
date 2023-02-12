package br.com.coreapi.domain.dto;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class AbstractBaseDTO {
    private Long id;
    private String guid;
    private LocalDateTime createdAt;
}
