package al.timeline.server.domain.activity.dto;

import al.timeline.server.interfaces.api.user.dto.TimelineUserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FollowCreatedEventForm {

	private TimelineUserDto follower;
	private TimelineUserDto following;

}
