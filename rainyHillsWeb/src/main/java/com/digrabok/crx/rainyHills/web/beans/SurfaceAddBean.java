package com.digrabok.crx.rainyHills.web.beans;

import com.digrabok.crx.rainyHills.commons.events.Created;
import com.digrabok.crx.rainyHills.web.actions.ISurfaceActions;
import com.digrabok.crx.rainyHills.web.entities.Surface;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

@Named("addBean")
@RequestScoped
public class SurfaceAddBean {
    private Surface surface = new Surface();

    public String submit() {
        surface = surfaceActions.create(surface);
        created.fire(surface);
        return NavigationEnum.detail.toString();
    }

    private ISurfaceActions surfaceActions;
    private Event<Surface> created;

    @Inject
    public void setSurfaceActions(ISurfaceActions surfaceActions) {
        this.surfaceActions = surfaceActions;
    }

    @Inject
    public void setCreated(@Created Event<Surface> created) {
        this.created = created;
    }

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }

}
