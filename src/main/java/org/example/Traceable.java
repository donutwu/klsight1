package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 在運行時可讀取
@Target(ElementType.METHOD)         // 用於方法
public @interface Traceable {
}