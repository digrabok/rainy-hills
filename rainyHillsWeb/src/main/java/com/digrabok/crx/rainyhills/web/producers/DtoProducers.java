package com.digrabok.crx.rainyhills.web.producers;

import com.digrabok.crx.rainyhills.commons.logic.dto.converters.DtoConverter;
import com.digrabok.crx.rainyhills.logic.api.dto.ISurfaceDto;
import com.digrabok.crx.rainyhills.web.entities.Surface;

import javax.enterprise.inject.Produces;

public class DtoProducers {
    private DtoConverter<Surface, ISurfaceDto> dtoSurfaceConverter = new DtoConverter<>(Surface::new);

    @Produces
    public DtoConverter<Surface, ISurfaceDto> getDtoSurfaceConverter() {
        return dtoSurfaceConverter;
    }
}
