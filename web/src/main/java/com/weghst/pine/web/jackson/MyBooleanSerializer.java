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
 * Jackson Boolean 序列化, 如果 {@code Boolean} 值为 {@code true} 则序列化为 {@code 1}, 反之序列化为 {@code 0}.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class MyBooleanSerializer extends NonTypedScalarSerializerBase<Boolean> {

    private final boolean _forPrimitive;

    public MyBooleanSerializer(boolean forPrimitive) {
        super(Boolean.class);
        _forPrimitive = forPrimitive;
    }

    @Override
    public void serialize(Boolean value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        jgen.writeNumber(value ? 1 : 0);
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return createSchemaNode("boolean", !_forPrimitive);
    }

    @Override
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        if (visitor != null) {
            visitor.expectBooleanFormat(typeHint);
        }
    }
}
