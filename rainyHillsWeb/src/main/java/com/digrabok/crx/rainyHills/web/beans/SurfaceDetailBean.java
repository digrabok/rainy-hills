package com.digrabok.crx.rainyHills.web.beans;

import com.digrabok.crx.rainyHills.commons.events.Created;
import com.digrabok.crx.rainyHills.web.actions.ISurfaceActions;
import com.digrabok.crx.rainyHills.web.entities.Surface;
import com.digrabok.crx.rainyHills.web.events.Selected;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

@Named("detailBean")
@RequestScoped
public class SurfaceDetailBean {
    private Surface surface = new Surface();

    public void onSurfaceSelected(@Observes @Selected Surface surface) {
        this.surface = surface;
    }

    public void onSurfaceCreated(@Observes @Created Surface surface) {
        this.surface = surface;
    }

    public String delete() {
        actions.delete(surface);
        return NavigationEnum.list.toString();
    }

    private ISurfaceActions actions;

    @Inject
    public void setActions(ISurfaceActions actions) {
        this.actions = actions;
    }

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }
}
