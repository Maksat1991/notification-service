package kz.maksat.smssender.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("SmsSenderConfigProperties")
@ConfigurationProperties(prefix = "spring.rabbitmq.sms-sender")
@Data
public class ConfigProperties {

    private String queue;

    private String exchange;

    private String routingKey;

    private String deadQueue;

    private String deadExchange;

    private String deadRoutingKey;

    private int retryMaxAttempts;

    private int retryInitialInterval;

    private int retryMultiplier;

    private int retryMaxInterval;

}
