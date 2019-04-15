package kz.maksat.common.processor;

import kz.maksat.common.dto.NotificationDto;
import kz.maksat.common.parser.MessageParser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.IOException;

public abstract class NotificationProcessor<T extends NotificationDto> implements MessageListener {

    public void onMessage(Message message) {

        try {
            T dto = getMessageParser().deserrialize(message);
            sendToProcessing(dto);
        } catch (IOException e) {
            e.printStackTrace();
            handleInvalidMessage();
        }

    }

    protected abstract void sendToProcessing(T dto);

    protected abstract MessageParser<T> getMessageParser();

    private void handleInvalidMessage() {
        //todo - save to db
    }



}
