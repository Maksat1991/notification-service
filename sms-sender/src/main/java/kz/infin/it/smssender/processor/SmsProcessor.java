package kz.infin.it.smssender.processor;

import kz.infin.it.common.parser.MessageParser;
import kz.infin.it.common.processor.NotificationProcessor;
import kz.infin.it.smssender.dto.SmsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SmsProcessor extends NotificationProcessor<SmsDto> {

    @Autowired
    @Qualifier("smsMessageParser")
    private MessageParser<SmsDto> messageParser;

    @Override
    protected void sendToProcessing(SmsDto dto) {
        System.out.println(dto);
    }

    @Override
    protected MessageParser<SmsDto> getMessageParser() {
        return messageParser;
    }
}
