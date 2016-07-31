package com.lezhin.timeline.client.config;

import com.lezhin.timeline.client.domain.user.service.TimelineUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService())
			.passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/scripts/**/*.{js,html}")
				.antMatchers("/bower_components/**")
				.antMatchers("/i18n/**")
				.antMatchers("/assets/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/login")
			.and()
				.formLogin()
					.loginPage("/login")
					.usernameParameter("username")
					.passwordParameter("password")
					.permitAll()
			.and()
				.authorizeRequests()
					.antMatchers("/hello", "/signup").permitAll()
					.anyRequest().hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")


		;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new TimelineUserDetailsService();
	}

}