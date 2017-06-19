package com.digrabok.crx.rainyhills.commons.logic.dto.converters;

import com.digrabok.crx.rainyhills.logic.api.dto.base.IDto;

public class Dto implements IDto {
    private String value;

    public Dto() {}

    public Dto(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

