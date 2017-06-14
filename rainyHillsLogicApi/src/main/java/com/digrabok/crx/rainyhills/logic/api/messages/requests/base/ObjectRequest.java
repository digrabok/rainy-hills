package com.digrabok.crx.rainyhills.logic.api.messages.requests.base;

import com.digrabok.crx.rainyhills.logic.api.dto.base.IDto;

public abstract class ObjectRequest<D extends IDto> implements IRequest {
    private final D payload;

    protected ObjectRequest(D payload) {
        this.payload = payload;
    }

    public D getPayload() {
        return payload;
    }
}
