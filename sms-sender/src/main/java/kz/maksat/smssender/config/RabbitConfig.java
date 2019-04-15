package kz.maksat.smssender.config;

import java.util.HashMap;
import java.util.Map;
import kz.maksat.common.config.IRabbitConfig;
import kz.maksat.common.parser.MessageParser;
import kz.maksat.common.sender.NotificationSender;
import kz.maksat.common.validator.NotificationValidator;
import kz.maksat.smssender.dto.SmsDto;
import kz.maksat.smssender.processor.SmsProcessor;
import kz.maksat.smssender.sender.SmsSender;
import kz.maksat.smssender.validator.SmsValidator;
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
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.retry.policy.SimpleRetryPolicy;

@Configuration("smsRabbitConfig")
public class RabbitConfig implements IRabbitConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private ConfigProperties configProperties;

    @Bean("smsQueue")
    public Queue queue() {

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", configProperties.getDeadExchange());
        arguments.put("x-dead-letter-routing-key", configProperties.getDeadRoutingKey());

        return new Queue(configProperties.getQueue(), true, false, false, arguments);
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

        container.setDefaultRequeueRejected(false);
        container.setAdviceChain(workMessagesRetryInterceptor());

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

    @Bean
    public SimpleRetryPolicy rejectionRetryPolicy(){
        return new SimpleRetryPolicy(configProperties.getRetryMaxAttempts());
    }

    @Bean
    public RetryOperationsInterceptor workMessagesRetryInterceptor() {
        return RetryInterceptorBuilder
            .stateless()
            .retryPolicy(rejectionRetryPolicy())
            .backOffOptions(
                configProperties.getRetryInitialInterval(),
                configProperties.getRetryMultiplier(),
                configProperties.getRetryMaxInterval())
            .build();
    }

    @Bean("deadQueue")
    public Queue deadQueue() {
        return new Queue(configProperties.getDeadQueue());
    }

    @Bean("deadExchange")
    public DirectExchange deadExchange() {
        return new DirectExchange(configProperties.getDeadExchange());
    }

    @Bean
    @Qualifier("deadBinding")
    public Binding deadBinding() {
        return BindingBuilder
            .bind(deadQueue())
            .to(deadExchange())
            .with(configProperties.getDeadRoutingKey());
    }

}
