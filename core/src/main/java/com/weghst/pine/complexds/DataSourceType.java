package com.weghst.pine.complexds;

import java.lang.annotation.*;

/**
 * @author zouyong (zouyong@mychebao.com)
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceType {

    /**
     * @return
     */
    String value();
}
