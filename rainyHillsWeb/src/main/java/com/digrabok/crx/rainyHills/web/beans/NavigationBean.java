package com.digrabok.crx.rainyHills.web.beans;

import com.digrabok.crx.rainyHills.web.entities.Surface;
import com.digrabok.crx.rainyHills.web.events.Selected;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class NavigationBean {
    public String goToList() {
        return NavigationEnum.list.name();
    }

    public String goToDetail(Surface surface) {
        selected.fire(surface);
        return NavigationEnum.detail.name();
    }

    public String goToAdd() {
        return NavigationEnum.add.name();
    }

    private Event<Surface> selected;

    @Inject
    public void setSelected(@Selected  Event<Surface> selected) {
        this.selected = selected;
    }
}
