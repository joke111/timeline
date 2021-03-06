package al.timeline.server.interfaces.api.message.dto;

import al.timeline.server.interfaces.api.user.dto.TimelineUserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimelineMessageDto {

	private String messageId;
	private TimelineUserDto author;
	private String contents;
	private String parentMessageId;
	private LocalDateTime messageDate;

}
