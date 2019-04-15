package kz.infin.it.smssender.validator;

import kz.infin.it.common.validator.NotificationValidator;
import kz.infin.it.smssender.dto.SmsDto;

public class SmsValidator extends NotificationValidator<SmsDto> {
    @Override
    public boolean isValid(SmsDto notificationDto) {
        return true;
    }
}
