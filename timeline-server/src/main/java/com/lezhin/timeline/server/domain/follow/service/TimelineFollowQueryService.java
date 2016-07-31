package com.lezhin.timeline.server.domain.follow.service;

import com.lezhin.timeline.server.domain.follow.model.TimelineFollowEntity;
import com.lezhin.timeline.server.domain.follow.repository.TimelineFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimelineFollowQueryService {

	@Autowired
	private TimelineFollowRepository timelineFollowRepository;

	public List<TimelineFollowEntity> findAllByFollowerLoginId(String loginId) {
		return timelineFollowRepository.findAllByFollowerLoginId(loginId);
	}

}
