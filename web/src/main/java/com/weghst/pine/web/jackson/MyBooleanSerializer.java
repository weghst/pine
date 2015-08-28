package com.weghst.pine.web.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.std.NonTypedScalarSerializerBase;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author Kevin Zou
 */
public class MyBooleanSerializer extends NonTypedScalarSerializerBase<Boolean> {

    public MyBooleanSerializer() {
        super(Boolean.class);
    }

    @Override
    public void serialize(Boolean value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        jgen.writeNumber(value ? 1 : 0);
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return createSchemaNode("boolean", false);
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        if (visitor != null) {
            visitor.expectBooleanFormat(typeHint);
        }
    }
}
