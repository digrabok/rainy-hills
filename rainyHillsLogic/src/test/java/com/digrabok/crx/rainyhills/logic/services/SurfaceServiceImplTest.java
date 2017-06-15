package com.digrabok.crx.rainyhills.logic.services;

import com.digrabok.crx.rainyhills.commons.logic.dto.converters.DtoConverter;
import com.digrabok.crx.rainyhills.logic.api.dto.ISurfaceDto;
import com.digrabok.crx.rainyhills.logic.api.messages.requests.FetchSurfaceByIdRequest;
import com.digrabok.crx.rainyhills.logic.api.messages.requests.FetchSurfaceListRequest;
import com.digrabok.crx.rainyhills.logic.api.messages.requests.SurfaceCreateRequest;
import com.digrabok.crx.rainyhills.logic.api.messages.requests.SurfaceDeleteRequest;
import com.digrabok.crx.rainyhills.logic.api.messages.responses.FetchRequestByIdResponse;
import com.digrabok.crx.rainyhills.logic.api.messages.responses.FetchSurfaceListResponse;
import com.digrabok.crx.rainyhills.logic.api.messages.responses.SurfaceCreateResponse;
import com.digrabok.crx.rainyhills.logic.bo.Surface;
import com.digrabok.crx.rainyhills.logic.persistence.dao.SurfaceDao;
import com.digrabok.crx.rainyhills.logic.services.measurer.IVolumeMeasurer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoAssertionError;

import javax.enterprise.event.Event;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class SurfaceServiceImplTest {
    private final List<Long> surface = buildSurface(4, 3, 2, 4);
    private final long expectedVolume = 3;
    private final Surface surfaceStub = new Surface();
    private final SurfaceCreateRequest createRequest = new SurfaceCreateRequest(null);

    private SurfaceServiceImpl service;

    @Mock private SurfaceDao surfaceDao;
    @Mock private DtoConverter<Surface, ISurfaceDto> dtoConverter;
    @Mock private IVolumeMeasurer measurer;
    @Mock private Event<Surface> created;

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);

        when(dtoConverter.fromDto(Matchers.any(ISurfaceDto.class))).thenReturn(surfaceStub);
        when(surfaceDao.create(any(Surface.class))).thenReturn(surfaceStub);
        when(measurer.calculate(any())).thenReturn(expectedVolume);

        service = new SurfaceServiceImpl();
        service.setSurfaceDao(surfaceDao);
        service.setDtoConverter(dtoConverter);
        service.setMeasurer(measurer);
        service.setCreated(created);
    }

    @Test
    public void shouldCalculateVolumeDuringSurfaceCreation() {
        SurfaceCreateResponse response = service.create(createRequest);

        assertEquals("Should calculate volume during surface creation",
                expectedVolume, response.getPayload().getVolume());
    }

    @Test
    public void shouldCreateSurface() {
        service.create(createRequest);

        try {
            verify(surfaceDao).create(surfaceStub);
        } catch (MockitoAssertionError e) {
            throw new AssertionError("Should create surface", e);
        }

        try {
            verify(created).fire(any());
        } catch (MockitoAssertionError e) {
            throw new AssertionError("Should fire event after create", e);
        }
    }

    @Test
    public void shouldReturnCreatedSurface() {
        SurfaceCreateResponse response = service.create(createRequest);

        assertEquals("Should return created surface", surfaceStub, response.getPayload());
    }

    @Test
    public void shouldDeleteSurface() {
        SurfaceDeleteRequest deleteRequest = new SurfaceDeleteRequest(null);

        service.delete(deleteRequest);

        try {
            verify(surfaceDao).delete(surfaceStub);
        } catch (MockitoAssertionError e) {
            throw new AssertionError("Should delete surface", e);
        }
    }

    @Test
    public void shouldFetchSurfaceById() {
        Long expectedId = 1L;
        FetchSurfaceByIdRequest request = new FetchSurfaceByIdRequest(expectedId);

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        when(surfaceDao.fetch(captor.capture())).thenReturn(surfaceStub);

        FetchRequestByIdResponse response = service.fetchSurface(request);

        assertEquals("Should return fetched surface", surfaceStub, response.getPayload());
        assertEquals("Should use provided ID for fetch", expectedId, captor.getValue());
    }

    @Test
    public void shouldFetchSurfacesList() {
        List<Surface> expectedList = Arrays.asList(new Surface(), new Surface());
        when(surfaceDao.fetchSurfaceList()).thenReturn(expectedList);

        FetchSurfaceListResponse response = service.fetchSurfaces(new FetchSurfaceListRequest());

        Assert.assertThat("Should fetch surfaces list",
                expectedList, is(response.getObjectList()));
    }

    private List<Long> buildSurface(long ... points) {
        List<Long> result = new LinkedList<>();
        Arrays.stream(points).forEach(result::add);
        return result;
    }
}
