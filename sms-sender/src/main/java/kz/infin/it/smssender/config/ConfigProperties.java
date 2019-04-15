package kz.infin.it.smssender.config;

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

}
