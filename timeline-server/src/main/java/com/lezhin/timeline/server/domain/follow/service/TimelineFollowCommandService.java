package com.lezhin.timeline.server.domain.follow.service;

import com.lezhin.timeline.server.domain.follow.model.TimelineFollowEntity;
import com.lezhin.timeline.server.domain.follow.repository.TimelineFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TimelineFollowCommandService {

	@Autowired
	private TimelineFollowRepository timelineFollowRepository;

	public void insert(TimelineFollowEntity timelineFollowEntity) {
		timelineFollowRepository.save(timelineFollowEntity);
	}

	@Transactional
	public void delete(String loginId, String followingLoginId) {
		timelineFollowRepository.findOneByFollowerLoginIdAndFollowingLoginId(loginId, followingLoginId)
			.ifPresent(timelineFollowEntity -> timelineFollowRepository.delete(timelineFollowEntity));
	}
}
