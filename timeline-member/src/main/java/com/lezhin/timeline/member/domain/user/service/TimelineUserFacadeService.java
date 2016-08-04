package com.lezhin.timeline.member.domain.user.service;

import com.lezhin.timeline.member.domain.base.assembler.SmartAssembler;
import com.lezhin.timeline.member.domain.user.dto.TimelineUserInsertForm;
import com.lezhin.timeline.member.domain.user.model.TimelineUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TimelineUserFacadeService {

	@Autowired
	private TimelineUserQueryService timelineUserQueryService;

	@Autowired
	private TimelineUserCommandService timelineUserCommandService;

	@Autowired
	private SmartAssembler assembler;

	@Transactional(readOnly = true)
	public TimelineUserEntity getTimelineUser(String loginId) {
		return timelineUserQueryService.findOneByLoginId(loginId).orElse(null);
	}

	public void insert(TimelineUserInsertForm insertForm) {
		TimelineUserEntity entity = assembler.assemble(insertForm, TimelineUserEntity.class);
		timelineUserCommandService.insert(entity);
	}

}
