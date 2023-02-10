package com.test.demo.global.mail;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.demo.user.User;

@Service
@Transactional
public class MailService {

	public String sendMockMail(User user) {
		throw new EntityNotFoundException();
	}
}
