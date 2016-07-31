package com.lezhin.timeline.server.domain.follow.dto;

import com.lezhin.timeline.server.interfaces.api.user.dto.TimelineUserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimelineFollowingInsertForm {

	private TimelineUserDto follower;
	private TimelineUserDto following;

}
