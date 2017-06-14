package com.digrabok.crx.rainyhills.commons;

import com.digrabok.crx.rainyhills.commons.exceptions.CopyPropertiesException;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class Utils {
    private Utils() {}

    public static void copyProperties(Object dest, Object orig) {
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new CopyPropertiesException(e);
        }
    }
}
