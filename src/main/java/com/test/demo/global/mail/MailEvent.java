package com.test.demo.global.mail;

import com.test.demo.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MailEvent {

	private User user;

	public MailEvent(User user) {
		this.user = user;
	}

	public static MailEvent of(User user) {
		System.out.println("Pass Event");
		return new MailEvent(user);
	}
}
