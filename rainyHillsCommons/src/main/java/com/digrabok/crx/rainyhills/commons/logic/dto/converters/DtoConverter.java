package com.digrabok.crx.rainyhills.commons.logic.dto.converters;

import com.digrabok.crx.rainyhills.commons.Utils;
import com.digrabok.crx.rainyhills.logic.api.dto.base.IDto;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DtoConverter<D extends E, E extends IDto> {
    private final Supplier<D> domainObjectFactory;

    public DtoConverter(Supplier<D> domainObjectFactory) {
        this.domainObjectFactory = domainObjectFactory;
    }

    public D fromDto(E dto) {
        D domain = this.domainObjectFactory.get();

        Utils.copyProperties(domain, dto);

        return domain;
    }

    public List<D> fromDto(List<? extends E> dtoList) {
        if (dtoList == null) {
            return Collections.emptyList();
        }
        return dtoList.stream().map(this::fromDto).collect(Collectors.toList());
    }
}
