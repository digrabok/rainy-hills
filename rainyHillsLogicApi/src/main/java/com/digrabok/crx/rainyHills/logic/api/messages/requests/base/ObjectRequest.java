package com.digrabok.crx.rainyHills.logic.api.messages.requests.base;

import com.digrabok.crx.rainyHills.logic.api.dto.base.IDto;

public abstract class ObjectRequest<DTO extends IDto> implements IRequest {
    private final DTO payload;

    protected ObjectRequest(DTO payload) {
        this.payload = payload;
    }

    public DTO getPayload() {
        return payload;
    }
}
