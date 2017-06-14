package com.digrabok.crx.rainyhills.logic.services.measurer.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SurfacePointTest {
    @Test
    public void shouldSelectPointHeightAsMaxHeightFromBeginningIfNoPrevPointProvided() {
        long expected = 8;
        SurfacePoint currentPoint = new SurfacePoint(8, null);

        assertEquals("Should select point height as maxHeightFromBeginning if no prev point provided",
                expected, currentPoint.getMaxHeightFromBeginning());
    }

    @Test
    public void shouldSelectPointHeightAsMaxHeightFromBeginningIfMaxHeightOfPrevPointIsLower() {
        long expected = 8;
        SurfacePoint prevPoint = new SurfacePoint(1, null);
        SurfacePoint currentPoint = new SurfacePoint(expected, prevPoint);

        assertEquals("Should select point height as maxHeightFromBeginning if max height of prev point is lower",
                expected, currentPoint.getMaxHeightFromBeginning());
    }

    @Test
    public void shouldSelectPrevPointHeightAsMaxHeightFromBeginningIfMaxHeightOfPrevPointIsHigher() {
        long expected = 8;
        SurfacePoint prevPoint = new SurfacePoint(expected, null);
        SurfacePoint currentPoint = new SurfacePoint(1, prevPoint);

        assertEquals("Should select prev point height as maxHeightFromBeginning if max height of prev point is higher",
                expected, currentPoint.getMaxHeightFromBeginning());
    }
}
