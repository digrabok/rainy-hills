package com.digrabok.crx.rainyhills.web.producers;

import com.digrabok.crx.rainyhills.commons.logic.dto.converters.DtoConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class DtoProducersTest {
    @Test
    public void shouldProduceConverter() {
        DtoConverter converter = new DtoProducers().getDtoSurfaceConverter();
        assertNotNull("Should produce converter", converter);
    }
}
