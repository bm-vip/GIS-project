package se.dzm.electricvehiclechargingstationmanagement.mapping;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<M,E> {
    M toModel(final E entity);
    List<M> toModel(final List<E> entities);

    E toEntity(final M model);
    List<E> toEntity(final List<M> models);

    E updateEntity(M dto, @MappingTarget E entity);

    List<E> updateEntityList(List<M> dtoList, @MappingTarget List<E> entityList);
}
