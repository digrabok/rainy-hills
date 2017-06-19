package com.digrabok.crx.rainyhills.web.converters;

import com.digrabok.crx.rainyhills.web.actions.ISurfaceActions;
import com.digrabok.crx.rainyhills.web.entities.Surface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.enterprise.inject.spi.CDI;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CDI.class})
public class SurfaceConverterTest {
    private SurfaceConverter converter;
    @Mock
    private ISurfaceActions actions;
    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);

        CDI cdi = mock(CDI.class);

        PowerMockito.mockStatic(CDI.class);
        when(CDI.current()).thenReturn(cdi);
        when(cdi.select(any(Class.class))).thenReturn(cdi);
        when(cdi.get()).thenReturn(actions);

        converter = new SurfaceConverter();
    }

    @Test
    public void shouldReturnSurfaceIdAsString() {
        long expectedId = 6;
        Surface surface = new Surface();
        surface.setId(expectedId);

        assertEquals("Should return surface id as string",
                Long.toString(expectedId), converter.getAsString(null, null, surface));
    }

    @Test
    public void shouldReturnEmptyStringIfSurfaceIsNull() {
        Surface surface = null;
        assertEquals("Should return empty string if surface is null",
                0, converter.getAsString(null, null, surface).length());
    }

    @Test
    public void shouldReturnEmptyStringIfSurfaceIdIsNull() {
        Surface surface = new Surface();
        assertEquals("Should return empty string if surface ID is null",
                0, converter.getAsString(null, null, surface).length());
    }

    @Test
    public void shouldFetchSurfaceByProvidedId() {
        Surface expectedSurface = new Surface();
        long id = 8;

        when(actions.fetchSurfaceById(id)).thenReturn(expectedSurface);

        Assert.assertThat("Should fetch surface by provided id",
                expectedSurface, is(converter.getAsObject(null, null, Long.toString(id))));
    }

    @Test
    public void shouldReturnNullIfIdInIncorrectFormat() {
        assertNull("Should return null if ID in incorrect format",
                converter.getAsObject(null, null, ""));
    }
}
