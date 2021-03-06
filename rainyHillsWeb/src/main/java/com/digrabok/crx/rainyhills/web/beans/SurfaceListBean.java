package com.digrabok.crx.rainyhills.web.beans;

import com.digrabok.crx.rainyhills.web.actions.ISurfaceActions;
import com.digrabok.crx.rainyhills.web.entities.Surface;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("listBean")
@RequestScoped
public class SurfaceListBean {
    private List<Surface> surfaces;

    @PostConstruct
    @Inject
    public void postConstruct(ISurfaceActions actions) {
        surfaces = actions.fetchSurfaces();
    }

    public List<Surface> getSurfaces() {
        return surfaces;
    }
}
