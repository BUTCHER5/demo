package com.jingyx.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLogAnno {

	/**业务模块*/
	String module()  default "";
	/**动作*/
	String action()  default "";



}
