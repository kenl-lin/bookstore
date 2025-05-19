package com.ken.bookstore.utils;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * @author ken
 */
public class LambdaUtil {
    public static <T> String getFieldName(SFunction<T, ?> fn) {
        try {
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            SerializedLambda lambda = (SerializedLambda) method.invoke(fn);

            String methodName = lambda.getImplMethodName(); // getTitle
            if (methodName.startsWith("get") && methodName.length() > 3) {
                return Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
            } else if (methodName.startsWith("is") && methodName.length() > 2) {
                return Character.toLowerCase(methodName.charAt(2)) + methodName.substring(3);
            }
            return methodName;
        } catch (Exception e) {
            throw new RuntimeException("error", e);
        }
    }
}
