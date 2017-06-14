package com.digrabok.crx.rainyhills.logic.services.measurer;

import java.util.List;

public interface IVolumeMeasurer {
    long calculate(List<Long> surfaceProfile);
}
