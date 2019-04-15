package kz.infin.it;

import kz.infin.it.common.sender.NotificationSender;
import kz.infin.it.email.dto.EmailDto;
import kz.infin.it.smssender.dto.SmsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    @Qualifier("smsSender")
    private NotificationSender<SmsDto> smsSender;

    @Autowired
    @Qualifier("emailSender")
    private NotificationSender<EmailDto> emailSender;

    @GetMapping("/sms")
    public ResponseEntity<String> sendSms() {
        smsSender.send(SmsDto.builder().name("sms name").body("sms body").build());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/email")
    public ResponseEntity<String> sendEmail() {
        emailSender.send(EmailDto.builder().name("email name").body("email body").build());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
