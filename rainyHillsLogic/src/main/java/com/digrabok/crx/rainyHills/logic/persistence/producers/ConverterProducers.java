package com.digrabok.crx.rainyHills.logic.persistence.producers;

import com.digrabok.crx.rainyHills.logic.bo.Surface;
import com.digrabok.crx.rainyHills.logic.persistence.converters.BoConverter;
import com.digrabok.crx.rainyHills.logic.persistence.entities.SurfaceEntity;

import javax.enterprise.inject.Produces;

public class ConverterProducers {
    @Produces
    private BoConverter<SurfaceEntity, Surface> surfaceBoConverter = new BoConverter<>(SurfaceEntity::new, Surface::new);
}
