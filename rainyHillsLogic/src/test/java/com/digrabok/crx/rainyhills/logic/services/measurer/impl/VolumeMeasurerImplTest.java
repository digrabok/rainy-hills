package com.digrabok.crx.rainyhills.logic.services.measurer.impl;

import com.digrabok.crx.rainyhills.logic.services.measurer.IVolumeMeasurer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class VolumeMeasurerImplTest {
    private IVolumeMeasurer measurer;

    @Before
    public void beforeEach() {
        measurer = new VolumeMeasurerImpl();
    }

    @Test
    public void shouldCalculateVolumeWhenMaximumAtTheBeginningOfSurface() {
        List<Long> surface = buildSurface(3, 2, 1, 3, 1);
        long expected = 3;

        assertEquals("Should calculate volume when maximum at the beginning of surface",
                expected, measurer.calculate(surface));
    }

    @Test
    public void shouldCalculateVolumeWhenMaximumAtTheEndOfSurface() {
        List<Long> surface = buildSurface(1, 3, 1, 2, 3);
        long expected = 3;

        assertEquals("should calculate volume when maximum at the end of surface",
                expected, measurer.calculate(surface));
    }

    @Test
    public void shouldCalculateVolumeWhenNestedHolsExisted() {
        List<Long> surface = buildSurface(1, 5, 3, 1, 2, 3, 2, 1, 3, 5, 1);
        long expected = 20;

        assertEquals("Should calculate volume when nested hols existed",
                expected, measurer.calculate(surface));
    }

    private List<Long> buildSurface(long ... points) {
        List<Long> result = new LinkedList<>();
        Arrays.stream(points).forEach(result::add);
        return result;
    }
}
