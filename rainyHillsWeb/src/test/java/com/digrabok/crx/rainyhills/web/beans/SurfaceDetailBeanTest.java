package com.digrabok.crx.rainyhills.web.beans;

import com.digrabok.crx.rainyhills.web.actions.ISurfaceActions;
import com.digrabok.crx.rainyhills.web.entities.Surface;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

public class SurfaceDetailBeanTest {
    @Mock
    private ISurfaceActions actions;
    private SurfaceDetailBean pageBean;

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        pageBean = new SurfaceDetailBean();
        pageBean.setActions(actions);
    }

    @Test
    public void shouldHaveEmptySurfaceAsDefaultValue() {
        assertNotNull("Should have empty surface as default value", pageBean.getSurface());
    }

    @Test
    public void shouldApplySelectedSurface() {
        Surface surface = new Surface();
        pageBean.onSurfaceSelected(surface);
        assertEquals("Should apply selected surface", surface, pageBean.getSurface());
    }

    @Test
    public void shouldApplyCreatedSurface() {
        Surface surface = new Surface();
        pageBean.onSurfaceCreated(surface);
        assertEquals("Should apply created surface", surface, pageBean.getSurface());
    }

    @Test
    public void shouldDeleteSurface() {
        Surface surface = new Surface();
        pageBean.onSurfaceSelected(surface);


        assertEquals("Should forward to the list view after surface was deleted",
                NavigationEnum.LIST.name().toLowerCase(), pageBean.delete());

        ArgumentCaptor<Surface> captor = ArgumentCaptor.forClass(Surface.class);
        verify(actions).delete(captor.capture());

        assertEquals("Should delete surface", surface, captor.getValue());
    }
}
