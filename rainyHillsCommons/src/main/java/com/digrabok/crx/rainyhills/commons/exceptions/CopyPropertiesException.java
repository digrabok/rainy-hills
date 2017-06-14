package com.digrabok.crx.rainyhills.commons.exceptions;

/**
 * Should be used as wrapper for exceptions from org.apache.commons.beanutils.BeanUtils#copyProperties
 */
public class CopyPropertiesException extends RuntimeException {
    public CopyPropertiesException(Throwable cause) {
        super(cause);
    }
}
