package al.timeline.server.domain.message.model;

import al.timeline.server.domain.base.entity.AuditEntity;
import al.timeline.server.domain.common.user.TimelineUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "timeline_messages")
public class TimelineMessageEntity extends AuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String messageId;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "loginId", column = @Column(name = "author_login_id")),
		@AttributeOverride(name = "name", column = @Column(name = "author_name")),
	})
	private TimelineUser author;

	private String contents;

	private String parentMessageId;

	private LocalDateTime messageDate = LocalDateTime.now();

}
