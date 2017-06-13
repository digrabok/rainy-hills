package com.digrabok.crx.rainyHills.logic.api.messages.requests;

import com.digrabok.crx.rainyHills.logic.api.dto.ISurfaceDto;
import com.digrabok.crx.rainyHills.logic.api.messages.requests.base.ObjectRequest;

public class SurfaceCreateRequest extends ObjectRequest<ISurfaceDto> {
    public SurfaceCreateRequest(ISurfaceDto objectForCreate) {
        super(objectForCreate);
    }
}
