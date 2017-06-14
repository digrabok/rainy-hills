package com.digrabok.crx.rainyhills.logic.api.messages.responses.base;

import com.digrabok.crx.rainyhills.logic.api.dto.base.IDto;

public abstract class ObjectResponse<D extends IDto> implements IResponse {
    private final D payload;

    protected ObjectResponse(D payload) {
        this.payload = payload;
    }

    public D getPayload() {
        return payload;
    }
}
