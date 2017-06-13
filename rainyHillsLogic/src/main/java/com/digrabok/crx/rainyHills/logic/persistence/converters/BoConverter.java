package com.digrabok.crx.rainyHills.logic.persistence.converters;
import com.digrabok.crx.rainyHills.logic.bo.base.IBusinessObject;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BoConverter<Entity, BusinessObject extends IBusinessObject> {
    private final Supplier<Entity> entityFactory;
    private final Supplier<BusinessObject> boFactory;

    public BoConverter(Supplier<Entity> entityFactory, Supplier<BusinessObject> boFactory) {
        this.entityFactory = entityFactory;
        this.boFactory = boFactory;
    }

    public BusinessObject fromEntity(Entity entity) {
        BusinessObject bo = boFactory.get();
        try {
            BeanUtils.copyProperties(bo, entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return bo;
    }

    public List<BusinessObject> fromEntity(List<Entity> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream().map(this::fromEntity).collect(Collectors.toList());
    }

    public Entity toEntity(BusinessObject bo) {
        Entity entity = entityFactory.get();
        try {
            BeanUtils.copyProperties(entity, bo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public List<Entity> toEntity(List<BusinessObject> boList) {
        if (boList != null) {
            return null;
        } else {
            return boList.stream().map(this::toEntity).collect(Collectors.toList());
        }
    }
}
