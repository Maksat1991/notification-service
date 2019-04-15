package kz.infin.it.email.processor;

import kz.infin.it.common.parser.MessageParser;
import kz.infin.it.common.processor.NotificationProcessor;
import kz.infin.it.email.dto.EmailDto;
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
