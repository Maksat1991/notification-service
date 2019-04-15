package kz.maksat.email.validator;

import kz.maksat.common.validator.NotificationValidator;
import kz.maksat.email.dto.EmailDto;

public class EmailValidator extends NotificationValidator<EmailDto> {
    @Override
    public boolean isValid(EmailDto notificationDto) {
        return true;
    }
}
