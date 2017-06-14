package com.digrabok.crx.rainyhills.logic.api.messages.responses.base;

import com.digrabok.crx.rainyhills.logic.api.dto.base.IDto;

import java.util.List;

public abstract class ObjectListResponse<D extends IDto> implements IResponse {
    private final List<D> objectList;

    protected ObjectListResponse(List<D> objectList) {
        this.objectList = objectList;
    }

    public List<D> getObjectList() {
        return objectList;
    }
}
