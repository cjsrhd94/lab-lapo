package com.test.demo.global.mail;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MailEventHandler {

	private final MailService mailService;

	@TransactionalEventListener(classes = MailEvent.class)
	public void sendMail(MailEvent mailEvent) {
		System.out.println("Pass Handler");
		mailService.sendMockMail(mailEvent.getUser());
	}
}
