package kz.maksat.smssender.dto;

import kz.maksat.common.dto.NotificationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsDto extends NotificationDto {
    private String name;
    private String body;
}
