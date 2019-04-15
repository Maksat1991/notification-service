package kz.maksat.smssender.processor;

import kz.maksat.common.parser.MessageParser;
import kz.maksat.common.processor.NotificationProcessor;
import kz.maksat.smssender.dto.SmsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SmsProcessor extends NotificationProcessor<SmsDto> {

    @Autowired
    @Qualifier("smsMessageParser")
    private MessageParser<SmsDto> messageParser;

    @Override
    protected void sendToProcessing(SmsDto dto) {
        System.out.println(dto);
        throw new RuntimeException("runtime exception");
    }

    @Override
    protected MessageParser<SmsDto> getMessageParser() {
        return messageParser;
    }
}
