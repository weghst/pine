package com.weghst.pine.web.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.weghst.pine.web.Restful;

import java.io.IOException;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class RestfulSerializer extends JsonSerializer<Restful> {

    @Override
    public void serialize(Restful value, JsonGenerator gen, SerializerProvider serializers) throws IOException,
            JsonProcessingException {
        gen.writeStartObject();
        if (value.getErrorCode() != 0) {
            gen.writeNumberField("errorCode", value.getErrorCode());
            gen.writeStringField("errorMessage", value.getErrorMessage());
        }

        if (value.getData() != null) {
            gen.writeObjectField("data", value.getData());
        }
        gen.writeEndObject();
    }
}
