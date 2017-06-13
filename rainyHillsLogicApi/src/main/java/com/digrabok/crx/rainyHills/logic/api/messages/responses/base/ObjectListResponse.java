package com.digrabok.crx.rainyHills.logic.api.messages.responses.base;

import com.digrabok.crx.rainyHills.logic.api.dto.base.IDto;

import java.util.List;

public abstract class ObjectListResponse<DTO extends IDto> implements IResponse {
    private final List<? extends DTO> objectList;

    protected ObjectListResponse(List<? extends DTO> objectList) {
        this.objectList = objectList;
    }

    public List<? extends DTO> getObjectList() {
        return objectList;
    }
}
