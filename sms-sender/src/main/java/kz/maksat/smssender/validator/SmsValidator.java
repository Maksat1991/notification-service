package kz.maksat.smssender.validator;

import kz.maksat.common.validator.NotificationValidator;
import kz.maksat.smssender.dto.SmsDto;

public class SmsValidator extends NotificationValidator<SmsDto> {
    @Override
    public boolean isValid(SmsDto notificationDto) {
        return true;
    }
}
