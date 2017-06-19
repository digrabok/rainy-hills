package com.digrabok.crx.rainyhills.web.actions.impl;

import com.digrabok.crx.rainyhills.commons.logic.dto.converters.DtoConverter;
import com.digrabok.crx.rainyhills.logic.api.dto.ISurfaceDto;
import com.digrabok.crx.rainyhills.logic.api.messages.requests.FetchSurfaceByIdRequest;
import com.digrabok.crx.rainyhills.logic.api.messages.requests.FetchSurfaceListRequest;
import com.digrabok.crx.rainyhills.logic.api.messages.requests.SurfaceCreateRequest;
import com.digrabok.crx.rainyhills.logic.api.messages.requests.SurfaceDeleteRequest;
import com.digrabok.crx.rainyhills.logic.api.messages.responses.FetchRequestByIdResponse;
import com.digrabok.crx.rainyhills.logic.api.messages.responses.FetchSurfaceListResponse;
import com.digrabok.crx.rainyhills.logic.api.messages.responses.SurfaceCreateResponse;
import com.digrabok.crx.rainyhills.logic.api.services.ISurfaceService;
import com.digrabok.crx.rainyhills.web.actions.ISurfaceActions;
import com.digrabok.crx.rainyhills.web.entities.Surface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class SurfaceActionsImplTest {
    @Mock
    private DtoConverter<Surface, ISurfaceDto> dtoConverter;
    @Mock
    private ISurfaceService surfaceService;
    private ISurfaceActions actions;

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);

        actions = new SurfaceActionsImpl(surfaceService, dtoConverter);
    }

    @Test
    public void shouldCreateSurface() {
        Surface surfaceForCreate = new Surface();

        when(surfaceService.create(any(SurfaceCreateRequest.class))).thenReturn(new SurfaceCreateResponse(null));

        actions.create(surfaceForCreate);

        ArgumentCaptor<SurfaceCreateRequest> captor = ArgumentCaptor.forClass(SurfaceCreateRequest.class);
        verify(surfaceService).create(captor.capture());

        assertEquals("Should create surface",
                surfaceForCreate, captor.getValue().getPayload());
    }

    @Test
    public void shouldReturnCreatedSurface() {
        Surface expectedSurface = new Surface();

        when(surfaceService.create(any(SurfaceCreateRequest.class))).thenReturn(new SurfaceCreateResponse(null));
        when(dtoConverter.fromDto(any(ISurfaceDto.class))).thenReturn(expectedSurface);

        assertThat("Should return created surface",
                expectedSurface, is(actions.create(null)));
    }

    @Test
    public void shouldDeleteSurface() {
        Surface surfaceForDelete = new Surface();

        actions.delete(surfaceForDelete);

        ArgumentCaptor<SurfaceDeleteRequest> captor = ArgumentCaptor.forClass(SurfaceDeleteRequest.class);
        verify(surfaceService).delete(captor.capture());

        assertEquals("Should delete surface",
                surfaceForDelete, captor.getValue().getPayload());
    }

    @Test
    public void shouldFetchSurfaceById() {
        long surfaceId = 8;
        Surface fetchedSurface = new Surface();

        when(surfaceService.fetchSurface(any(FetchSurfaceByIdRequest.class))).thenReturn(new FetchRequestByIdResponse(null));
        when(dtoConverter.fromDto(any(ISurfaceDto.class))).thenReturn(fetchedSurface);

        assertEquals("Should return fetched surface",
                fetchedSurface, actions.fetchSurfaceById(surfaceId));

        ArgumentCaptor<FetchSurfaceByIdRequest> captor = ArgumentCaptor.forClass(FetchSurfaceByIdRequest.class);
        verify(surfaceService).fetchSurface(captor.capture());
        assertEquals("Should fetch Surface by id", surfaceId, captor.getValue().getId());
    }

    @Test
    public void shouldFetchSurfaceList() {
        List<Surface> fetchedList = Arrays.asList(new Surface(), new Surface());

        when(surfaceService.fetchSurfaces(any(FetchSurfaceListRequest.class))).thenReturn(new FetchSurfaceListResponse(null));
        when(dtoConverter.fromDto(any(List.class))).thenReturn(fetchedList);

        assertThat("Should return fetched surfaces",
                fetchedList, is(actions.fetchSurfaces()));
    }
}
