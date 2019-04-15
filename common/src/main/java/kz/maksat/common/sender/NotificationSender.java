package kz.maksat.common.sender;

import kz.maksat.common.dto.NotificationDto;
import kz.maksat.common.validator.NotificationValidator;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class NotificationSender<T extends NotificationDto> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    protected abstract Exchange getExchange();

    protected abstract Binding getBinding();

    protected abstract NotificationValidator<T> getValidator();

    public void send(T notificationDto) {
        if (!getValidator().isValid(notificationDto)) {
            sendToDeathQueue(notificationDto);
            return;
        }

        sendToQueue(notificationDto);
    }

    private void sendToQueue(T notificationDto) {
        rabbitTemplate.convertAndSend(getExchange().getName(), getBinding().getRoutingKey(), notificationDto);
    }

    //todo - implement
    private void sendToDeathQueue(T notificationDto) {

    }
}
