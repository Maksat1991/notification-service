package kz.maksat.email.sender;

import kz.maksat.common.sender.NotificationSender;
import kz.maksat.common.validator.NotificationValidator;
import kz.maksat.email.dto.EmailDto;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class EmailSender extends NotificationSender<EmailDto> {

    @Autowired
    @Qualifier("emailExchange")
    private Exchange exchange;

    @Autowired
    @Qualifier("emailBinding")
    private Binding binding;

    @Autowired
    @Qualifier("emailValidator")
    private NotificationValidator<EmailDto> validator;

    @Override
    protected Exchange getExchange() {
        return exchange;
    }

    @Override
    protected Binding getBinding() {
        return binding;
    }

    @Override
    protected NotificationValidator<EmailDto> getValidator() {
        return validator;
    }
}
