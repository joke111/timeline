package al.timeline.server.domain.message.service;

import al.timeline.server.domain.message.model.TimelineMessageEntity;
import al.timeline.server.domain.message.repository.TimelineMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TimelineMessageCommandService {

	@Autowired
	private TimelineMessageRepository timelineMessageRepository;

	@Transactional
	public void insert(TimelineMessageEntity message) {
		timelineMessageRepository.save(message);
	}

}
