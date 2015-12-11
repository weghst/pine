package com.weghst.pine.web.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.weghst.pine.web.resource.Restful;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class PineModule extends SimpleModule {

    public PineModule() {
        addSerializer(boolean.class, new MyBooleanSerializer(true));
        addSerializer(Boolean.class, new MyBooleanSerializer(false));

        addSerializer(Restful.class, new RestfulSerializer());
    }

}
