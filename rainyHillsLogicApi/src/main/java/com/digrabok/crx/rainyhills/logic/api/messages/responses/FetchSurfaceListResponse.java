package com.digrabok.crx.rainyhills.logic.api.messages.responses;

import com.digrabok.crx.rainyhills.logic.api.dto.ISurfaceDto;
import com.digrabok.crx.rainyhills.logic.api.messages.responses.base.ObjectListResponse;

import java.util.List;

public class FetchSurfaceListResponse extends ObjectListResponse<ISurfaceDto> {
    public FetchSurfaceListResponse(List<ISurfaceDto> objectList) {
        super(objectList);
    }
}
