package com.digrabok.crx.rainyhills.logic.services;

import com.digrabok.crx.rainyhills.commons.logic.dto.converters.DtoConverter;
import com.digrabok.crx.rainyhills.logic.api.dto.ISurfaceDto;
import com.digrabok.crx.rainyhills.logic.api.messages.requests.SurfaceCreateRequest;
import com.digrabok.crx.rainyhills.logic.api.messages.requests.SurfaceDeleteRequest;
import com.digrabok.crx.rainyhills.logic.api.messages.responses.SurfaceCreateResponse;
import com.digrabok.crx.rainyhills.logic.bo.Surface;
import com.digrabok.crx.rainyhills.logic.persistence.dao.SurfaceDao;
import com.digrabok.crx.rainyhills.logic.services.measurer.IVolumeMeasurer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.enterprise.event.Event;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

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
    public void shouldCalculateVolumeDuringCreate() {
        SurfaceCreateResponse response = service.create(createRequest);

        assertEquals(expectedVolume, response.getPayload().getVolume());
    }

    @Test
    public void shouldCreateSurface() {
        service.create(createRequest);

        verify(surfaceDao, times(1)).create(surfaceStub);
    }

    @Test
    public void shouldFireEventAfterCreate() {
        service.create(createRequest);

        verify(created, times(1)).fire(any());
    }

    @Test
    public void shouldReturnCreatedSurface() {
        SurfaceCreateResponse response = service.create(createRequest);

        assertEquals(surfaceStub, response.getPayload());
    }

    @Test
    public void shouldDeleteSurface() {
        SurfaceDeleteRequest deleteRequest = new SurfaceDeleteRequest(null);

        service.delete(deleteRequest);

        verify(surfaceDao, times(1)).delete(surfaceStub);
    }

    private List<Long> buildSurface(long ... points) {
        List<Long> result = new LinkedList<>();
        Arrays.stream(points).forEach(result::add);
        return result;
    }
}
