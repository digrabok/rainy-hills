package com.digrabok.crx.rainyHills.logic.api.messages.responses.base;

import com.digrabok.crx.rainyHills.logic.api.dto.base.IDto;

public abstract class ObjectResponse<DTO extends IDto> implements IResponse {
    private final DTO payload;

    protected ObjectResponse(DTO payload) {
        this.payload = payload;
    }

    public DTO getPayload() {
        return payload;
    }
}
