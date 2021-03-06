package al.timeline.client.domain.user.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class TimelineUser extends User {

	private String loginId;
	private String name;

	public TimelineUser(String username, String password, String name, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.loginId = username;
		this.name = name;
	}

}
