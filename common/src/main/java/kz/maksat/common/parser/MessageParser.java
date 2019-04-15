package kz.maksat.common.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Serializable;

public class MessageParser<T extends Serializable> {

    @Autowired
    private ObjectMapper mapper;

    private Class<T> clazzOfT;

    public MessageParser(Class<T> typeParameterClass) {
        clazzOfT = typeParameterClass;
    }

    public T deserrialize(Message message) throws IOException {
        String value = new String(message.getBody());
        return mapper.readValue(value, clazzOfT);
    }

}
