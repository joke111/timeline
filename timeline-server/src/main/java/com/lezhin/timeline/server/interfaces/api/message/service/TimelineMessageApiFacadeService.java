package com.lezhin.timeline.server.interfaces.api.message.service;

import com.lezhin.timeline.server.domain.base.assembler.SmartAssembler;
import com.lezhin.timeline.server.domain.message.dto.TimelineMessageInsertForm;
import com.lezhin.timeline.server.domain.message.model.TimelineMessageEntity;
import com.lezhin.timeline.server.domain.message.service.TimelineMessageFacadeService;
import com.lezhin.timeline.server.domain.user.model.TimelineUser;
import com.lezhin.timeline.server.domain.user.service.TimelineUserFacadeService;
import com.lezhin.timeline.server.interfaces.api.message.dto.TimelineMessageDto;
import com.lezhin.timeline.server.interfaces.api.message.dto.TimelineMessageInsertApiForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimelineMessageApiFacadeService {

	@Autowired
	private TimelineMessageFacadeService timelineMessageFacadeService;

	@Autowired
	private TimelineUserFacadeService timelineUserFacadeService;

	@Autowired
	private SmartAssembler assembler;

	public void postMessage(String loginId, TimelineMessageInsertApiForm insertApiForm) {
		TimelineMessageInsertForm insertForm = assembler.assemble(insertApiForm, TimelineMessageInsertForm.class);
		insertForm.setLoginId(loginId);
		timelineMessageFacadeService.insert(insertForm);
	}

	public List<TimelineMessageDto> listMessages(String loginId) {
		TimelineUser user = timelineUserFacadeService.getTimelineUser(loginId).getUser();
		List<TimelineMessageEntity> timelineMessages = timelineMessageFacadeService.getTimelineMessages(user);
		return assembler.assemble(timelineMessages, TimelineMessageDto.class);
	}
}