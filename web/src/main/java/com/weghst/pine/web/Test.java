package com.weghst.pine.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weghst.pine.util.Query;
import com.weghst.pine.util.SimpleQuery;
import com.weghst.pine.web.resource.Restful;

import java.io.IOException;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-09-30 14:08
 */
public class Test {
    public static void main(String[] args) throws IOException {

        SimpleQuery simpleQuery = new SimpleQuery();
        simpleQuery.setQ("HELLO");
        simpleQuery.setCount(20);
        simpleQuery.setOffset(15);
        simpleQuery.setSort("name");
        simpleQuery.setOrder(Query.Order.asc);

        Restful restful = new Restful(simpleQuery);

        ObjectMapper objectMapper = ViewObjectMapperFactory.getObjectMapper();
        objectMapper.writeValue(System.out, restful);
    }
}
