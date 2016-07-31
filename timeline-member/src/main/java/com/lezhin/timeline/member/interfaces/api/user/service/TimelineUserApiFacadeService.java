package com.lezhin.timeline.member.interfaces.api.user.service;

import com.lezhin.timeline.member.domain.base.assembler.SmartAssembler;
import com.lezhin.timeline.member.domain.user.model.TimelineUserEntity;
import com.lezhin.timeline.member.domain.user.service.TimelineUserFacadeService;
import com.lezhin.timeline.member.interfaces.api.user.dto.TimelineUserDto;
import com.lezhin.timeline.member.interfaces.api.user.dto.TimelineUserInsertForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimelineUserApiFacadeService {

	@Autowired
	private TimelineUserFacadeService timelineUserFacadeService;

	@Autowired
	private SmartAssembler assembler;

	public TimelineUserDto getTimelineUser(String loginId) {
		TimelineUserEntity timelineUserEntity = timelineUserFacadeService.getTimelineUser(loginId);
		return assembler.assemble(timelineUserEntity.getUser(), TimelineUserDto.class);
	}

	public void register(TimelineUserInsertForm insertForm) {
		timelineUserFacadeService.insert(insertForm);
	}

}
