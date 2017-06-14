package com.digrabok.crx.rainyhills.logic.api.dto;

import com.digrabok.crx.rainyhills.logic.api.dto.base.IDto;

import java.util.List;

public interface ISurfaceDto extends IDto {
    long getId();
    void setId(long id);
    String getName();
    void setName(String name);
    long getVolume();
    void setVolume(long volume);
    List<Long> getProfile();
    void setProfile(List<Long> profile);
}
