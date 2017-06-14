package com.digrabok.crx.rainyhills.logic.persistence.producers;

import com.digrabok.crx.rainyhills.logic.bo.Surface;
import com.digrabok.crx.rainyhills.logic.persistence.converters.BoConverter;
import com.digrabok.crx.rainyhills.logic.persistence.entities.SurfaceEntity;

import javax.enterprise.inject.Produces;

public class ConverterProducers {
    @Produces
    private BoConverter<SurfaceEntity, Surface> surfaceBoConverter = new BoConverter<>(SurfaceEntity::new, Surface::new);
}
