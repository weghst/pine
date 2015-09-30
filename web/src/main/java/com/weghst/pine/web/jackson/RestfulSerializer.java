package com.weghst.pine.web.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.weghst.pine.web.resource.Restful;

import java.io.IOException;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class RestfulSerializer extends JsonSerializer<Restful> {

    @Override
    public void serialize(Restful value, JsonGenerator gen, SerializerProvider serializers) throws IOException,
            JsonProcessingException {
        if (value.getErrorCode() != 0) {
            gen.writeStartObject();
            gen.writeNumberField("errorCode", value.getErrorCode());
            gen.writeStringField("errorMessage", value.getErrorMessage());
            gen.writeEndObject();
        }

        if (value.getResult() != null) {
            // gen.writeObjectField("data", value.getData());
            gen.writeObject(value.getResult());
        }
    }
}
