package com.digrabok.crx.rainyhills.web.converters;

import com.digrabok.crx.rainyhills.web.actions.ISurfaceActions;
import com.digrabok.crx.rainyhills.web.entities.Surface;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("surfaceConverter")
public class SurfaceConverter implements Converter {
    @Override
    public Surface getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        long id = Long.parseLong(s);
        return actions.fetchSurfaceById(id);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Surface surface = (Surface) o;
        return Long.toString(surface.getId());
    }

    private final ISurfaceActions actions;

    public SurfaceConverter() {
        this.actions = CDI.current().select(ISurfaceActions.class).get();
    }
}
