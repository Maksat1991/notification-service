package kz.infin.it.email.dto;

import kz.infin.it.common.dto.NotificationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto extends NotificationDto {
    private String name;
    private String body;
}
