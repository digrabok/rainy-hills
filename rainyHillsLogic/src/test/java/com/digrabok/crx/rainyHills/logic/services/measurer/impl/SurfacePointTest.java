package com.digrabok.crx.rainyHills.logic.services.measurer.impl;

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

        assertEquals(expected, currentPoint.getMaxHeightFromBeginning());
    }

    @Test
    public void shouldSelectPointHeightAsMaxHeightFromBeginningIfMaxHeightOfPrevPointIsLower() {
        long expected = 8;
        SurfacePoint prevPoint = new SurfacePoint(1, null);
        SurfacePoint currentPoint = new SurfacePoint(expected, prevPoint);

        assertEquals(expected, currentPoint.getMaxHeightFromBeginning());
    }

    @Test
    public void shouldSelectPrevPointHeightAsMaxHeightFromBeginningIfMaxHeightOfPrevPointIsHigher() {
        long expected = 8;
        SurfacePoint prevPoint = new SurfacePoint(expected, null);
        SurfacePoint currentPoint = new SurfacePoint(1, prevPoint);

        assertEquals(expected, currentPoint.getMaxHeightFromBeginning());
    }
}
