package com.msm.helpers;

import java.lang.annotation.*;


/**
 *
 * Created by Venkatesh on 23/08/16.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Layout {

    String value() default "";

}
