package com.digrabok.crx.rainyhills.logic.api.messages.responses;

import com.digrabok.crx.rainyhills.logic.api.dto.ISurfaceDto;
import com.digrabok.crx.rainyhills.logic.api.messages.responses.base.ObjectResponse;

public class SurfaceCreateResponse extends ObjectResponse<ISurfaceDto> {
    public SurfaceCreateResponse(ISurfaceDto object) {
        super(object);
    }
}
