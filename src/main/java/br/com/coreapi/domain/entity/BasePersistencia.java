package br.com.coreapi.domain.entity;

import org.springframework.data.domain.Persistable;

public interface BasePersistencia<T> extends Persistable<T> {

    @Override
    default boolean isNew() {
        return this.getId() == null;
    }
}
