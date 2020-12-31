package com.bwcx.lambda;


@FunctionalInterface
public interface ChangeAge<T> {

    boolean changeAgeByParam(T t);
}
