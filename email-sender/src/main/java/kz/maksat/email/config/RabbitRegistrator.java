package kz.maksat.email.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("emailRabbitRegistrator")
public class RabbitRegistrator {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    @Qualifier("emailQueue")
    private Queue queue;

    @Autowired
    @Qualifier("emailExchange")
    private DirectExchange directExchange;

    @Autowired
    @Qualifier("emailBinding")
    private Binding binding;

    @PostConstruct
    public void init() {
        rabbitAdmin.declareExchange(directExchange);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);
    }

}
