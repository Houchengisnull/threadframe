package com.houc.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflections {

    /**
     * invoke protected/private method
     */
    public static <T> T invokeUnaccessible(Class<?> clazz, String methodName, Object instance, Class<T> returnType) {
        Method method = null;
        T obj = null;
        try {
            method = clazz.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        method.setAccessible(true);
        try {
            obj = returnType.cast(method.invoke(instance));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
