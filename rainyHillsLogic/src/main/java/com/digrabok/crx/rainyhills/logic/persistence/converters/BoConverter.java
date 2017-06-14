package com.digrabok.crx.rainyhills.logic.persistence.converters;

import com.digrabok.crx.rainyhills.commons.Utils;
import com.digrabok.crx.rainyhills.logic.bo.base.IBusinessObject;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BoConverter<E, B extends IBusinessObject> {
    private final Supplier<E> entityFactory;
    private final Supplier<B> boFactory;

    public BoConverter(Supplier<E> entityFactory, Supplier<B> boFactory) {
        this.entityFactory = entityFactory;
        this.boFactory = boFactory;
    }

    public B fromEntity(E entity) {
        B bo = boFactory.get();

        Utils.copyProperties(bo, entity);

        return bo;
    }

    public List<B> fromEntity(List<E> entityList) {
        if (entityList == null) {
            return Collections.emptyList();
        }
        return entityList.stream().map(this::fromEntity).collect(Collectors.toList());
    }

    public E toEntity(B bo) {
        E entity = entityFactory.get();

        Utils.copyProperties(entity, bo);

        return entity;
    }

    public List<E> toEntity(List<B> boList) {
        if (boList == null) {
            return Collections.emptyList();
        } else {
            return boList.stream().map(this::toEntity).collect(Collectors.toList());
        }
    }
}
