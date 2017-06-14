package com.digrabok.crx.rainyhills.web.entities;

import com.digrabok.crx.rainyhills.logic.api.dto.ISurfaceDto;

import java.util.Collections;
import java.util.List;

public class Surface implements ISurfaceDto {
    private long id;
    private String name;
    private long volume;
    private List<Long> profile;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getVolume() {
        return volume;
    }

    @Override
    public void setVolume(long volume) {
        this.volume = volume;
    }

    @Override
    public List<Long> getProfile() {
        return profile;
    }

    @Override
    public void setProfile(List<Long> profile) {
        this.profile = profile != null ? Collections.unmodifiableList(profile) : null;
    }
}
