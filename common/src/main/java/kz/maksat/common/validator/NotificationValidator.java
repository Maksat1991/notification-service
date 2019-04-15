package kz.maksat.common.validator;

import kz.maksat.common.dto.NotificationDto;

public abstract class NotificationValidator<T extends NotificationDto> {

    public abstract boolean isValid(T notificationDto);
}
