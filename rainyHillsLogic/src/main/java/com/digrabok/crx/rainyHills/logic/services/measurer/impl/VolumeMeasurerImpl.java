package com.digrabok.crx.rainyHills.logic.services.measurer.impl;

import com.digrabok.crx.rainyHills.logic.services.measurer.IVolumeMeasurer;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;

/**
 * Algorithm for total volume calculation of surface.
 *
 * First step - all heights of surface will be stored in the LinkedList of {@link SurfacePoint} in left-to-right
 * order.
 *
 * Second step - created surface points will be iterated in right-to-left order. Volume will be calculated during this
 * iteration. Each point will be touched only once, after it point will be deleted from the list.
 *
 * Time complexity of algorithm: O(N).
 * Space complexity of algorithm: O(N).
 */
@Stateless
@Local({IVolumeMeasurer.class})
public class VolumeMeasurerImpl implements IVolumeMeasurer {
    @Override
    public long calculate(List<Long> surfaceProfile) {
        LinkedList<SurfacePoint> surfacePoints = new LinkedList<>();

        // left 2 right walk
        SurfacePoint prevPoint = null;
        for (Long pointHeight : surfaceProfile) {
            SurfacePoint currentPoint = new SurfacePoint(pointHeight, prevPoint);
            surfacePoints.add(currentPoint);
            prevPoint = currentPoint;
        }

        // right 2 left walk
        SurfacePoint current = surfacePoints.removeLast();
        long volumeTotal = 0;
        long currentPointVolume = 0;
        while (current != null && !surfacePoints.isEmpty()) {
            SurfacePoint latest = surfacePoints.removeLast();

            if (latest == null || latest.getPointHeight() >= current.getPointHeight()) {
                volumeTotal += currentPointVolume;
                currentPointVolume = 0;
                current = latest;
            } else {
                long volumeLimit = Math.min(current.getPointHeight(), latest.getMaxHeightFromBeginning());
                currentPointVolume += volumeLimit - latest.getPointHeight();
            }
        }
        volumeTotal += currentPointVolume;

        return volumeTotal;
    }
}
