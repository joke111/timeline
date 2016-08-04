package com.lezhin.timeline.member.interfaces.api.user.controller;

import com.lezhin.timeline.member.domain.user.dto.TimelineUserInsertForm;
import com.lezhin.timeline.member.interfaces.api.user.dto.TimelineUserDetailsDto;
import com.lezhin.timeline.member.interfaces.api.user.dto.TimelineUserDto;
import com.lezhin.timeline.member.interfaces.api.user.service.TimelineUserApiFacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class TimelineUserController {

	@Autowired
	private TimelineUserApiFacadeService timelineUserApiFacadeService;

	@RequestMapping(path = "/{loginId}", method = RequestMethod.GET)
	public TimelineUserDto getTimelineUser(@PathVariable("loginId") String loginId) {
		return timelineUserApiFacadeService.getTimelineUser(loginId);
	}

	@RequestMapping(path = "/{loginId}/details", method = RequestMethod.GET)
	public TimelineUserDetailsDto getTimelineUserDetails(@PathVariable("loginId") String loginId) {
		return timelineUserApiFacadeService.getTimelineUserDetails(loginId);
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public void register(@RequestBody TimelineUserInsertForm insertForm) {
		timelineUserApiFacadeService.register(insertForm);
	}

}
