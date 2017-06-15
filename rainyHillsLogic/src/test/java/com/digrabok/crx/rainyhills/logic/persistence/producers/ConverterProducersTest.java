package com.digrabok.crx.rainyhills.logic.persistence.producers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class ConverterProducersTest {
    @Test
    public void shouldProduceConverter() {
        assertNotNull("Should produce converter", new ConverterProducers().getSurfaceBoConverter());
    }
}
