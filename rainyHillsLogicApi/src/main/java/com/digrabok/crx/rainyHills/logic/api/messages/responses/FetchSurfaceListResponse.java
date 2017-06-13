package com.digrabok.crx.rainyHills.logic.api.messages.responses;

import com.digrabok.crx.rainyHills.logic.api.dto.ISurfaceDto;
import com.digrabok.crx.rainyHills.logic.api.messages.responses.base.ObjectListResponse;

import java.util.List;

public class FetchSurfaceListResponse extends ObjectListResponse<ISurfaceDto> {
    public FetchSurfaceListResponse(List<? extends ISurfaceDto> objectList) {
        super(objectList);
    }
}
