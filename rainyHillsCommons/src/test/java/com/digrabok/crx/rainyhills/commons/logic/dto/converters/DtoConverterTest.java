package com.digrabok.crx.rainyhills.commons.logic.dto.converters;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class DtoConverterTest {
    private DtoConverter<BusinessObject, Dto> converter;

    @Before
    public void beforeEach() {
        converter = new DtoConverter<>(BusinessObject::new);
    }

    @Test
    public void shouldConvertDtoToBusinessObject() {
        String expectedValue = "test";

        Dto dto = new Dto(expectedValue);

        BusinessObject businessObject = converter.fromDto(dto);

        assertEquals("Should convert dto to business object", expectedValue, businessObject.getValue());
    }

    @Test
    public void shouldConvertDtoListToBusinessObjectList() {
        List<String> expectedValues = Arrays.asList("test #1", "test #2");
        List<Dto> dtoList = expectedValues.stream().map(Dto::new).collect(Collectors.toList());

        List<BusinessObject> businessObjectList = converter.fromDto(dtoList);
        assertThat("Should convert dto list to business object list",
                expectedValues, Is.is(businessObjectList.stream().map(BusinessObject::getValue).collect(Collectors.toList())));
    }

    @Test
    public void shouldReturnEmptyListIfSourceIsNull() {
        List<BusinessObject> businessObjectList = converter.fromDto((List<? extends Dto>) null);
        assertTrue("Should return empty list if source is null", businessObjectList.isEmpty());
    }
}
