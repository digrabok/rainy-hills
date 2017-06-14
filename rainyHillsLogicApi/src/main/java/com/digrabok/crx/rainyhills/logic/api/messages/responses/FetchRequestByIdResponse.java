package com.digrabok.crx.rainyhills.logic.api.messages.responses;

import com.digrabok.crx.rainyhills.logic.api.dto.ISurfaceDto;
import com.digrabok.crx.rainyhills.logic.api.messages.responses.base.ObjectResponse;

public class FetchRequestByIdResponse extends ObjectResponse<ISurfaceDto> {
    public FetchRequestByIdResponse(ISurfaceDto payload) {
        super(payload);
    }
}
