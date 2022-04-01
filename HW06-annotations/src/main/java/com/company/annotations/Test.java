package com.company.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @Test} is used to signal that the annotated method is a
 * <em>test</em> method.
 * <p>Methods annotated with {@code @Test} <b>must be public</b> and <b>must not have any arguments</b>.</p>
 * Otherwise, they will be silently ignored.
 */
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
}
