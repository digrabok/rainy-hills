package com.digrabok.crx.rainyHills.web.actions;

import com.digrabok.crx.rainyHills.web.entities.Surface;

import java.util.List;

public interface ISurfaceActions {
    Surface create(Surface surface);
    void delete(Surface surface);
    Surface fetchSurfaceById(long id);
    List<Surface> fetchSurfaces();
}
