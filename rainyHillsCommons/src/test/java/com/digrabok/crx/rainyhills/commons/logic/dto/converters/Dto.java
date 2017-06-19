package com.digrabok.crx.rainyhills.commons.logic.dto.converters;

import com.digrabok.crx.rainyhills.logic.api.dto.base.IDto;

public class Dto implements IDto {
    private String value;

    Dto() {}

    Dto(String value) {
        this.value = value;
    }

    String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

