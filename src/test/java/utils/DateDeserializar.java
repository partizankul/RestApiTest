package utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Класс для правильной сериализации даты
public class DateDeserializar extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        DateTimeFormatter formatter = DateTimeFormatter.
                ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); //отображение даты в нужном нам формате
        return LocalDateTime.parse(jsonParser.getText(), formatter);
    }
}
