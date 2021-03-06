package al.timeline.server.domain.message.model;

import al.timeline.server.domain.message.service.TimelineMessageFacadeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class TimelineUserMessagesSearchContext {

	private TimelineMessageFacadeService timelineMessageFacadeService;

}
