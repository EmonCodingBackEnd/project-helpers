package com.coding.helpers.plugin.gray.annotation;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface EnableMvcGrayFilter {}
