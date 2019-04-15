package kz.infin.it.smssender.sender;

import kz.infin.it.common.sender.NotificationSender;
import kz.infin.it.common.validator.NotificationValidator;
import kz.infin.it.smssender.dto.SmsDto;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SmsSender extends NotificationSender<SmsDto> {

    @Autowired
    @Qualifier("smsExchange")
    private Exchange exchange;

    @Autowired
    @Qualifier("smsBinding")
    private Binding binding;

    @Autowired
    @Qualifier("smsValidator")
    private NotificationValidator<SmsDto> validator;

    @Override
    protected Exchange getExchange() {
        return exchange;
    }

    @Override
    protected Binding getBinding() {
        return binding;
    }

    @Override
    protected NotificationValidator<SmsDto> getValidator() {
        return validator;
    }
}
