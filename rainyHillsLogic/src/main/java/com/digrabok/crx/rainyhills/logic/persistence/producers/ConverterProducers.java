package com.digrabok.crx.rainyhills.logic.persistence.producers;

import com.digrabok.crx.rainyhills.logic.bo.Surface;
import com.digrabok.crx.rainyhills.logic.persistence.converters.BoConverter;
import com.digrabok.crx.rainyhills.logic.persistence.entities.SurfaceEntity;

import javax.enterprise.inject.Produces;

public class ConverterProducers {
    private final BoConverter<SurfaceEntity, Surface> surfaceBoConverter = new BoConverter<>(SurfaceEntity::new, Surface::new);

    @Produces
    public BoConverter<SurfaceEntity, Surface> getSurfaceBoConverter() {
        return surfaceBoConverter;
    }
}
