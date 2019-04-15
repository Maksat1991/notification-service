package kz.infin.it.smssender.config;

import kz.infin.it.common.config.IRabbitConfig;
import kz.infin.it.common.parser.MessageParser;
import kz.infin.it.common.sender.NotificationSender;
import kz.infin.it.common.validator.NotificationValidator;
import kz.infin.it.smssender.dto.SmsDto;
import kz.infin.it.smssender.processor.SmsProcessor;
import kz.infin.it.smssender.sender.SmsSender;
import kz.infin.it.smssender.validator.SmsValidator;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("smsRabbitConfig")
public class RabbitConfig implements IRabbitConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private ConfigProperties configProperties;

    @Bean("smsQueue")
    public Queue queue() {
        return new Queue(configProperties.getQueue());
    }

    @Bean("smsExchange")
    public DirectExchange exchange() {
        return new DirectExchange(configProperties.getExchange());
    }

    @Bean("smsMessageParser")
    public MessageParser<SmsDto> messageParser() {
        return new MessageParser<>(SmsDto.class);
    }

    @Bean
    @Qualifier("smsBinding")
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(configProperties.getRoutingKey());
    }

    @Bean("smsMessageListenerContainer")
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

    @Bean("smsProcessor")
    public MessageListener reciever() {
        return new SmsProcessor();
    }

    @Bean("smsValidator")
    public NotificationValidator validator() {
        return new SmsValidator();
    }

    @Bean("smsSender")
    public NotificationSender<SmsDto> sender() {
        return new SmsSender();
    }

}
