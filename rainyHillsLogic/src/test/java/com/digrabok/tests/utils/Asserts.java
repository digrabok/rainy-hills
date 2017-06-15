package com.digrabok.tests.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class Asserts {
    private static Set<String> excludedProperties = new HashSet<>();
    static {
        excludedProperties.add("class");
    }

    public static void reflectEqualsList(String message, List a, List b) {
        assertEquals(buildMessage(message, " Collections size comparing."),
                a.size(), b.size());

        for(int idx = 0; idx < a.size(); idx++) {
            reflectEquals(buildMessage(message, " Item # " + idx), a.get(idx), b.get(idx));
        }
    }

    public static void reflectEquals(String message, Object a, Object b) {
        refEq(message, a, b);
        refEq(message, b, a);
    }

    private static void refEq(String msg, Object a, Object b) {
        assertNotNull(a);
        assertNotNull(b);

        Map<String, Method> mapA = getGettersMap(a);
        Map<String, Method> mapB = getGettersMap(b);

        mapA.entrySet().stream()
                .filter(entry -> !excludedProperties.contains(entry.getKey()))
                .forEach(entry -> {
                    String name = entry.getKey();
                    Method getter = entry.getValue();

                    if (!mapB.containsKey(name)) {
                        fail(buildMessage(msg, " Object \"" + b.toString() + "\" doesn't contain property \"" + name + "\""));
                    }

                    Object valueA, valueB;
                    try {
                        valueA = getter.invoke(a);
                        valueB = mapB.get(name).invoke(b);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                    assertEquals(buildMessage(msg ," Method \"" + name + "\"."), valueA, valueB);
                });
    }

    private static Map<String, Method> getGettersMap(Object obj) {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass());
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        return Arrays.stream(propertyDescriptors).collect(Collectors.toMap(
                PropertyDescriptor::getDisplayName, PropertyDescriptor::getReadMethod
        ));
    }

    private static String buildMessage(String baseMessage, String additionalMessage) {
        if (baseMessage == null) {
            return additionalMessage;
        } else {
            if (!baseMessage.endsWith(".")) {
                baseMessage += ".";
            }
            return baseMessage + additionalMessage;
        }
    }
}
