package com.digrabok.crx.rainyhills.logic.api.messages.requests;

import com.digrabok.crx.rainyhills.logic.api.dto.ISurfaceDto;
import com.digrabok.crx.rainyhills.logic.api.messages.requests.base.ObjectRequest;

public class SurfaceCreateRequest extends ObjectRequest<ISurfaceDto> {
    public SurfaceCreateRequest(ISurfaceDto objectForCreate) {
        super(objectForCreate);
    }
}
