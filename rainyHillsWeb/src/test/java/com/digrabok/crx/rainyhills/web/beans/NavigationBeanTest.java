package com.digrabok.crx.rainyhills.web.beans;

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
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class NavigationBeanTest {
    @Mock
    private Event<Surface> selectedEvent;
    private NavigationBean pageBean;

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        pageBean = new NavigationBean();
        pageBean.setSelected(selectedEvent);
    }

    @Test
    public void shouldForwardToTheList() {
        assertEquals("Should forward to the list page",
                NavigationEnum.LIST.name().toLowerCase(), pageBean.goToList());
    }

    @Test
    public void shouldForwardToAdd() {
        assertEquals("Should forward to add page",
                NavigationEnum.ADD.name().toLowerCase(), pageBean.goToAdd());
    }

    @Test
    public void shouldForwardToTheDetail() {
        assertEquals("Should forward to the detail page",
                NavigationEnum.DETAIL.name().toLowerCase(), pageBean.goToDetail(new Surface()));
    }

    @Test
    public void shouldFireSurfaceSelectedEventOnForward() {
        Surface surface = new Surface();

        pageBean.goToDetail(surface);

        ArgumentCaptor<Surface> captor = ArgumentCaptor.forClass(Surface.class);
        verify(selectedEvent).fire(captor.capture());

        assertEquals("Should fire surface selected event on forward",
                surface, captor.getValue());
    }
}
