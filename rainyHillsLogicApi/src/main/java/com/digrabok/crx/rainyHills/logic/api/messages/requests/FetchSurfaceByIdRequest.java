package com.digrabok.crx.rainyHills.logic.api.messages.requests;

import com.digrabok.crx.rainyHills.logic.api.messages.requests.base.IRequest;

public class FetchSurfaceByIdRequest implements IRequest {
    private final long id;

    public FetchSurfaceByIdRequest(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
