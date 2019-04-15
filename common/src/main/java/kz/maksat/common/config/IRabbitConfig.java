package kz.maksat.common.config;

import kz.maksat.common.dto.NotificationDto;
import kz.maksat.common.parser.MessageParser;
import kz.maksat.common.sender.NotificationSender;
import kz.maksat.common.validator.NotificationValidator;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;

import java.io.Serializable;

public interface IRabbitConfig {

    Queue queue();

    DirectExchange exchange();

    MessageParser<? extends Serializable> messageParser();

    Binding binding();

    MessageListenerContainer messageListenerContainer();

    MessageListener reciever();

    NotificationValidator validator();

    NotificationSender<? extends NotificationDto> sender();
}
