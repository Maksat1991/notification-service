package kz.infin.it.common.config;

import kz.infin.it.common.dto.NotificationDto;
import kz.infin.it.common.parser.MessageParser;
import kz.infin.it.common.sender.NotificationSender;
import kz.infin.it.common.validator.NotificationValidator;
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
