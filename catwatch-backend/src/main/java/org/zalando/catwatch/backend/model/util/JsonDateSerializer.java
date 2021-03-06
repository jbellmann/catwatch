package org.zalando.catwatch.backend.model.util;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Created by mkunz on 7/28/15.
 */
public class JsonDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(final Date date, final JsonGenerator jgen, final SerializerProvider provider)
        throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = format.format(date);
        jgen.writeString(formattedDate);
    }

}
