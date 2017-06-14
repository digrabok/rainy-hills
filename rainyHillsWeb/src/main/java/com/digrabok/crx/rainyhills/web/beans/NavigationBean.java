package com.digrabok.crx.rainyhills.web.beans;

import com.digrabok.crx.rainyhills.web.entities.Surface;
import com.digrabok.crx.rainyhills.web.events.Selected;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class NavigationBean {
    public String goToList() {
        return NavigationEnum.LIST.name();
    }

    public String goToDetail(Surface surface) {
        selected.fire(surface);
        return NavigationEnum.DETAIL.name();
    }

    public String goToAdd() {
        return NavigationEnum.ADD.name();
    }

    private Event<Surface> selected;

    @Inject
    public void setSelected(@Selected Event<Surface> selected) {
        this.selected = selected;
    }
}
