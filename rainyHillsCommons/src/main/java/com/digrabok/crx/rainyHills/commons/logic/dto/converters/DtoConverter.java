package com.digrabok.crx.rainyHills.commons.logic.dto.converters;
import com.digrabok.crx.rainyHills.logic.api.dto.base.IDto;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DtoConverter<DomainObject extends Dto, Dto extends IDto> {
    private final Supplier<DomainObject> domainObjectFactory;

    public DtoConverter(Supplier<DomainObject> domainObjectFactory) {
        this.domainObjectFactory = domainObjectFactory;
    }

    public DomainObject fromDto(Dto dto) {
        DomainObject domain = this.domainObjectFactory.get();
        try {
            BeanUtils.copyProperties(domain, dto);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return domain;
    }

    public List<DomainObject> fromDto(List<? extends Dto> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::fromDto).collect(Collectors.toList());
    }
}
