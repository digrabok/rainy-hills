package com.digrabok.crx.rainyhills.web.converters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class SpaceSeparatedListOfLongsConverterTest {
    private SpaceSeparatedListOfLongsConverter converter;

    @Before
    public void beforeEach() {
        converter = new SpaceSeparatedListOfLongsConverter();
    }

    @Test
    public void shouldConvertStringToTheListOfLong() {
        List<Long> expected = Arrays.asList(3L, 2L, 4L, 15L);
        String source = " 3 2 4 15";

        assertThat("Should convert string to the list of long",
                converter.getAsObject(null, null, source), is(expected));
    }

    @Test
    public void shouldReturnEmptyListIfEmptyStringProvided() {
        assertTrue("Should returne empty list if empty string provided",
                converter.getAsObject(null, null, "").isEmpty());
    }

    @Test
    public void shouldConvertListToStringOfSpaceSeparatedNumbers() {
        String expected = "3 2 4 15";
        List<Long> source = Arrays.asList(3L, 2L, 4L, 15L);

        assertEquals("Should convert list to string of space separated numbers",
                expected, converter.getAsString(null, null, source));
    }

    @Test
    public void shouldConvertEmptyListToEmptyString() {
        int expectedLength = 0;

        assertEquals("Should convert empty list to empty string",
                expectedLength, converter.getAsString(null, null, new ArrayList<>()).length());
    }
}
