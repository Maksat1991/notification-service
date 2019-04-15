package kz.infin.it.common.validator;

import kz.infin.it.common.dto.NotificationDto;

public abstract class NotificationValidator<T extends NotificationDto> {

    public abstract boolean isValid(T notificationDto);
}
