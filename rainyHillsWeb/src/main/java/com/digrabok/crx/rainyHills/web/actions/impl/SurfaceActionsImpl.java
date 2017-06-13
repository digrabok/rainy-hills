package com.digrabok.crx.rainyHills.web.actions.impl;

import com.digrabok.crx.rainyHills.commons.logic.dto.converters.DtoConverter;
import com.digrabok.crx.rainyHills.logic.api.dto.ISurfaceDto;
import com.digrabok.crx.rainyHills.logic.api.messages.requests.FetchSurfaceByIdRequest;
import com.digrabok.crx.rainyHills.logic.api.messages.requests.FetchSurfaceListRequest;
import com.digrabok.crx.rainyHills.logic.api.messages.requests.SurfaceCreateRequest;
import com.digrabok.crx.rainyHills.logic.api.messages.requests.SurfaceDeleteRequest;
import com.digrabok.crx.rainyHills.logic.api.messages.responses.FetchRequestByIdResponse;
import com.digrabok.crx.rainyHills.logic.api.messages.responses.FetchSurfaceListResponse;
import com.digrabok.crx.rainyHills.logic.api.messages.responses.SurfaceCreateResponse;
import com.digrabok.crx.rainyHills.logic.api.services.ISurfaceService;
import com.digrabok.crx.rainyHills.web.actions.ISurfaceActions;
import com.digrabok.crx.rainyHills.web.entities.Surface;

import javax.inject.Inject;
import java.util.List;

public class SurfaceActionsImpl implements ISurfaceActions {
    @Override
    public Surface create(Surface surface) {
        SurfaceCreateRequest request = new SurfaceCreateRequest(surface);
        SurfaceCreateResponse response = surfaceService.create(request);
        return dtoDtoConverter.fromDto(response.getPayload());
    }

    @Override
    public void delete(Surface surface) {
        SurfaceDeleteRequest request = new SurfaceDeleteRequest(surface);
        surfaceService.delete(request);
    }

    @Override
    public Surface fetchSurfaceById(long id) {
        FetchSurfaceByIdRequest request = new FetchSurfaceByIdRequest(id);
        FetchRequestByIdResponse response = surfaceService.fetchSurface(request);
        return dtoDtoConverter.fromDto(response.getPayload());
    }

    @Override
    public List<Surface> fetchSurfaces() {
        FetchSurfaceListRequest request = new FetchSurfaceListRequest();
        FetchSurfaceListResponse response = surfaceService.fetchSurfaces(request);
        return dtoDtoConverter.fromDto(response.getObjectList());
    }

    private final ISurfaceService surfaceService;
    private final DtoConverter<Surface, ISurfaceDto> dtoDtoConverter;

    @Inject
    public SurfaceActionsImpl(ISurfaceService surfaceService, DtoConverter<Surface, ISurfaceDto> dtoDtoConverter) {
        this.surfaceService = surfaceService;
        this.dtoDtoConverter = dtoDtoConverter;
    }
}
