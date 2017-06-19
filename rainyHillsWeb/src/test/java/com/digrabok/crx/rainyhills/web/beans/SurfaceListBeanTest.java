package com.digrabok.crx.rainyhills.web.beans;

import com.digrabok.crx.rainyhills.web.actions.ISurfaceActions;
import com.digrabok.crx.rainyhills.web.entities.Surface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class SurfaceListBeanTest {
    @Mock
    private ISurfaceActions actions;
    private SurfaceListBean pageBean;

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        pageBean = new SurfaceListBean();
        pageBean.postConstruct(actions);
    }

    @Test
    public void shouldLoadSurfacesDuringInitialisation() {
        List<Surface> surfaces = Collections.emptyList();

        when(actions.fetchSurfaces()).thenReturn(surfaces);

        assertEquals("",
                surfaces, pageBean.getSurfaces());
    }
}
