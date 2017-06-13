package com.digrabok.crx.rainyHills.logic.services.measurer.impl;

/**
 * Helper for {@link VolumeMeasurerImpl}.
 */
class SurfacePoint {
    /**
     * Height of point on surface
     */
    private final long pointHeight;
    /**
     * Maximal height between current point height and previous point maxHeightFromBeginning property. De facto
     * maximal height of all previous points on surface.
     */
    private final long maxHeightFromBeginning;

    SurfacePoint(long pointHeight, SurfacePoint prevPoint) {
        this.pointHeight = pointHeight;
        if (prevPoint == null) {
            this.maxHeightFromBeginning = pointHeight;
        } else {
            this.maxHeightFromBeginning = Math.max(pointHeight, prevPoint.getMaxHeightFromBeginning());
        }
    }

    long getPointHeight() {
        return pointHeight;
    }

    long getMaxHeightFromBeginning() {
        return maxHeightFromBeginning;
    }
}
