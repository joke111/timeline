package al.timeline.server.interfaces.api.message.service;

import al.timeline.server.domain.base.assembler.SmartAssembler;
import al.timeline.server.interfaces.api.message.dto.TimelineMessageDto;
import al.timeline.server.interfaces.api.message.dto.TimelineMessagePostForm;
import al.timeline.server.domain.message.dto.TimelineMessageInsertForm;
import al.timeline.server.domain.message.dto.TimelineUserMessageSearchConditions;
import al.timeline.server.domain.message.dto.TimelineUserMessagesSearchConditions;
import al.timeline.server.domain.message.model.TimelineMessageEntity;
import al.timeline.server.domain.message.service.TimelineMessageFacadeService;
import al.timeline.server.interfaces.api.message.dto.TimelineUserMessageSearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimelineMessageApiFacadeService {

	@Autowired
	private TimelineMessageFacadeService timelineMessageFacadeService;

	@Autowired
	private SmartAssembler assembler;

	public void postMessage(TimelineMessagePostForm postForm) {
		TimelineMessageInsertForm insertForm = assembler.assemble(postForm, TimelineMessageInsertForm.class);
		timelineMessageFacadeService.insert(insertForm);
	}

	public List<TimelineMessageDto> listMessages(TimelineUserMessageSearchParam searchParam) {
		TimelineUserMessagesSearchConditions searchConditions = assembler.assemble(searchParam, TimelineUserMessagesSearchConditions.class);
		List<TimelineMessageEntity> timelineMessages = timelineMessageFacadeService.getTimelineUserMessages(searchConditions);
		return assembler.assemble(timelineMessages, TimelineMessageDto.class);
	}

	public List<TimelineMessageDto> getNewsFeed(TimelineUserMessageSearchParam searchParam) {
		TimelineUserMessagesSearchConditions searchConditions = assembler.assemble(searchParam, TimelineUserMessagesSearchConditions.class);
		List<TimelineMessageEntity> timelineMessages = timelineMessageFacadeService.getNewsFeed(searchConditions);
		return assembler.assemble(timelineMessages, TimelineMessageDto.class);
	}

	public TimelineMessageDto getMessage(String loginId, String messageId) {
		TimelineUserMessageSearchConditions searchConditions = TimelineUserMessageSearchConditions.of(loginId, messageId);
		TimelineMessageEntity timelineMessage = timelineMessageFacadeService.getTimelineMessage(searchConditions);
		return assembler.assemble(timelineMessage, TimelineMessageDto.class);
	}

}
