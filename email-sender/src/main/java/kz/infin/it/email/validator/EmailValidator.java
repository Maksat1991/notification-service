package kz.infin.it.email.validator;

import kz.infin.it.common.validator.NotificationValidator;
import kz.infin.it.email.dto.EmailDto;

public class EmailValidator extends NotificationValidator<EmailDto> {
    @Override
    public boolean isValid(EmailDto notificationDto) {
        return true;
    }
}
