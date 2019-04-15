package kz.maksat.email.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("EmailSenderConfigProperties")
@ConfigurationProperties(prefix = "spring.rabbitmq.email-sender")
@Data
public class ConfigProperties {

    private String queue;

    private String exchange;

    private String routingKey;

}
