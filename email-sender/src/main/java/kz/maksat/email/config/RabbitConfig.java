package kz.maksat.email.config;

import kz.maksat.common.config.IRabbitConfig;
import kz.maksat.common.parser.MessageParser;
import kz.maksat.common.sender.NotificationSender;
import kz.maksat.common.validator.NotificationValidator;
import kz.maksat.email.processor.EmailProcessor;
import kz.maksat.email.sender.EmailSender;
import kz.maksat.email.validator.EmailValidator;
import kz.maksat.email.dto.EmailDto;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("emailRabbitConfig")
public class RabbitConfig implements IRabbitConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private ConfigProperties configProperties;

    @Bean("emailQueue")
    public Queue queue() {
        return new Queue(configProperties.getQueue());
    }

    @Bean("emailExchange")
    public DirectExchange exchange() {
        return new DirectExchange(configProperties.getExchange());
    }

    @Bean("emailMessageParser")
    public MessageParser<EmailDto> messageParser() {
        return new MessageParser<>(EmailDto.class);
    }

    @Bean("emailBinding")
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(configProperties.getRoutingKey());
    }

    @Bean("emailMessageListenerContainer")
    public MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setAutoStartup(true);
        container.setQueues(queue());
        container.setConcurrentConsumers(4);
        container.setMaxConcurrentConsumers(10);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setMessageListener(new MessageListenerAdapter(reciever()));
        return container;
    }

    @Bean("emailProcessor")
    public MessageListener reciever() {
        return new EmailProcessor();
    }

    @Bean("emailValidator")
    public NotificationValidator validator() {
        return new EmailValidator();
    }

    @Bean("emailSender")
    public NotificationSender<EmailDto> sender() {
        return new EmailSender();
    }

}
