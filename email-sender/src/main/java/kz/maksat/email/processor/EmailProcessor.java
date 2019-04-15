package kz.maksat.email.processor;

import kz.maksat.common.parser.MessageParser;
import kz.maksat.common.processor.NotificationProcessor;
import kz.maksat.email.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class EmailProcessor extends NotificationProcessor<EmailDto> {

    @Autowired
    @Qualifier("emailMessageParser")
    private MessageParser<EmailDto> messageParser;

    @Override
    protected void sendToProcessing(EmailDto dto) {
        System.out.println(dto);
    }

    @Override
    protected MessageParser<EmailDto> getMessageParser() {
        return messageParser;
    }
}
