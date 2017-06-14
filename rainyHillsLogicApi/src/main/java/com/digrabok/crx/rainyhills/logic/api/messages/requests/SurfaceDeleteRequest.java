package com.digrabok.crx.rainyhills.logic.api.messages.requests;

import com.digrabok.crx.rainyhills.logic.api.dto.ISurfaceDto;
import com.digrabok.crx.rainyhills.logic.api.messages.requests.base.ObjectRequest;

public class SurfaceDeleteRequest extends ObjectRequest<ISurfaceDto> {
    public SurfaceDeleteRequest(ISurfaceDto payload) {
        super(payload);
    }
}
