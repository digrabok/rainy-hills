package com.digrabok.crx.rainyHills.web.producers;

import com.digrabok.crx.rainyHills.commons.logic.dto.converters.DtoConverter;
import com.digrabok.crx.rainyHills.logic.api.dto.ISurfaceDto;
import com.digrabok.crx.rainyHills.web.entities.Surface;

import javax.enterprise.inject.Produces;

public class DtoProducers {
    @Produces
    private DtoConverter<Surface, ISurfaceDto> dtoSurfaceConverter = new DtoConverter<>(Surface::new);
}
