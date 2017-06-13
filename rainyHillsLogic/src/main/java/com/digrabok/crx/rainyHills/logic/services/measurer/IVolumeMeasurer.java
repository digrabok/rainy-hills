package com.digrabok.crx.rainyHills.logic.services.measurer;

import java.util.List;

public interface IVolumeMeasurer {
    long calculate(List<Long> surfaceProfile);
}
