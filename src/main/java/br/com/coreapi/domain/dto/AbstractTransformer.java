package br.com.coreapi.domain.dto;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("squid:S1610")
public abstract class AbstractTransformer {

    private AbstractTransformer() {
    }

    public static Object transformEntity(Object dto, Class<?> clazz) {
        return new ModelMapper().map(dto, clazz);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List transformList(List list, Class<?> clazz) {
        return (List) list.stream().map(entity -> AbstractTransformer.transformEntity(entity, clazz))
                .collect(Collectors.toList());
    }

}
