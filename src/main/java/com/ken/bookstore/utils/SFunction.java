package com.ken.bookstore.utils;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @author ken
 */
@FunctionalInterface
public interface SFunction<T, R> extends Function<T, R>, Serializable {
}
