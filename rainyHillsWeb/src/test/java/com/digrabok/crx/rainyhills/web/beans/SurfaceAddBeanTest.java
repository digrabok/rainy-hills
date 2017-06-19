package com.digrabok.crx.rainyhills.web.beans;

import com.digrabok.crx.rainyhills.web.actions.ISurfaceActions;
import com.digrabok.crx.rainyhills.web.entities.Surface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.enterprise.event.Event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class SurfaceAddBeanTest {
    @Mock
    private ISurfaceActions actions;
    @Mock
    private Event<Surface> createdEvent;

    private SurfaceAddBean pageBean;

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);

        pageBean = new SurfaceAddBean();
        pageBean.setSurfaceActions(actions);
        pageBean.setCreated(createdEvent);
    }

    @Test
    public void shouldHaveEmptySurfaceAsDefaultValue() {
        assertNotNull("Should have empty surface as default value", pageBean.getSurface());
    }

    @Test
    public void shouldCreateSurfaceOnSubmit() {
        String expectedName = "expected name";
        Surface surfaceForCreation = pageBean.getSurface();
        surfaceForCreation.setName(expectedName);

        when(actions.create(any(Surface.class))).thenReturn(surfaceForCreation);
        pageBean.submit();

        ArgumentCaptor<Surface> captor = ArgumentCaptor.forClass(Surface.class);
        verify(actions).create(captor.capture());
        assertEquals("Should create surface on submit",
                expectedName, captor.getValue().getName());
    }

    @Test
    public void shouldFireEventAfterSurfaceWasCreated() {
        Surface createdSurface = new Surface();

        when(actions.create(any(Surface.class))).thenReturn(createdSurface);
        pageBean.submit();

        ArgumentCaptor<Surface> captor = ArgumentCaptor.forClass(Surface.class);
        verify(createdEvent).fire(captor.capture());
        assertEquals("Should fire event after surface creation",
                createdSurface, captor.getValue());
    }

    @Test
    public void shouldForwardToTheDetailViewOnSurfaceCreated() {
        Surface createdSurface = new Surface();

        when(actions.create(any(Surface.class))).thenReturn(createdSurface);

        assertEquals("Should forward to the detail view on surface created",
                NavigationEnum.DETAIL.name().toLowerCase(), pageBean.submit());
    }
}
