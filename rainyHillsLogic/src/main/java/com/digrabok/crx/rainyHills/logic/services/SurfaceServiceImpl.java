package com.digrabok.crx.rainyHills.logic.services;

import com.digrabok.crx.rainyHills.commons.events.Created;
import com.digrabok.crx.rainyHills.commons.logic.dto.converters.DtoConverter;
import com.digrabok.crx.rainyHills.logic.api.dto.ISurfaceDto;
import com.digrabok.crx.rainyHills.logic.api.messages.requests.FetchSurfaceByIdRequest;
import com.digrabok.crx.rainyHills.logic.api.messages.requests.FetchSurfaceListRequest;
import com.digrabok.crx.rainyHills.logic.api.messages.requests.SurfaceCreateRequest;
import com.digrabok.crx.rainyHills.logic.api.messages.requests.SurfaceDeleteRequest;
import com.digrabok.crx.rainyHills.logic.api.messages.responses.FetchRequestByIdResponse;
import com.digrabok.crx.rainyHills.logic.api.messages.responses.FetchSurfaceListResponse;
import com.digrabok.crx.rainyHills.logic.api.messages.responses.SurfaceCreateResponse;
import com.digrabok.crx.rainyHills.logic.api.messages.responses.SurfaceDeleteResponse;
import com.digrabok.crx.rainyHills.logic.api.services.ISurfaceService;
import com.digrabok.crx.rainyHills.logic.bo.Surface;
import com.digrabok.crx.rainyHills.logic.persistence.dao.SurfaceDao;
import com.digrabok.crx.rainyHills.logic.services.measurer.IVolumeMeasurer;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Local({ISurfaceService.class})
public class SurfaceServiceImpl implements ISurfaceService {
    @Override
    public SurfaceCreateResponse create(SurfaceCreateRequest request) {
        ISurfaceDto dto = request.getPayload();
        Surface surface = dtoConverter.fromDto(dto);
        surface.setVolume(measurer.calculate(surface.getProfile()));

        surface = surfaceDao.create(surface);
        created.fire(surface);

        return new SurfaceCreateResponse(surface);
    }

    @Override
    public SurfaceDeleteResponse delete(SurfaceDeleteRequest request) {
        ISurfaceDto dto = request.getPayload();
        Surface surface = dtoConverter.fromDto(dto);

        surfaceDao.delete(surface);

        return new SurfaceDeleteResponse();
    }

    @Override
    public FetchRequestByIdResponse fetchSurface(FetchSurfaceByIdRequest request) {
        long id = request.getId();
        Surface surface = surfaceDao.fetch(id);
        return new FetchRequestByIdResponse(surface);
    }

    @Override
    public FetchSurfaceListResponse fetchSurfaces(FetchSurfaceListRequest request) {
        List<Surface> surfaces = surfaceDao.fetchSurfaceList();
        return new FetchSurfaceListResponse(surfaces);
    }

    private SurfaceDao surfaceDao;
    private DtoConverter<Surface, ISurfaceDto> dtoConverter;
    private IVolumeMeasurer measurer;
    private Event<Surface> created;

    @Inject
    public void setSurfaceDao(SurfaceDao surfaceDao) {
        this.surfaceDao = surfaceDao;
    }

    @Inject
    public void setDtoConverter(DtoConverter<Surface, ISurfaceDto> dtoConverter) {
        this.dtoConverter = dtoConverter;
    }

    @Inject
    public void setMeasurer(IVolumeMeasurer measurer) {
        this.measurer = measurer;
    }

    @Inject
    public void setCreated(@Created Event<Surface> created) {
        this.created = created;
    }
}
