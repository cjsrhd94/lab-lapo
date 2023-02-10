package com.test.demo.user;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.demo.global.mail.MailEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private final UserRepository userRepository;
	private final UserQueryRepository userQueryRepository;
	private final ApplicationEventPublisher applicationEventPublisher;
	private final EntityManager em;

	public Long create(UserReqDto dto) {
		User user = userRepository.save(User.builder()
				.email(dto.getEmail())
				.password(dto.getPassword())
				.build());
		applicationEventPublisher.publishEvent(MailEvent.of(user));
		return user.getId();
	}

	public List<User> findAllCustom() {
		Session session = em.unwrap(Session.class);
		Filter filter = session.enableFilter("deletedUserFilter");
		filter.setParameter("deleted", false);
		List<User> users = userQueryRepository.findAll();
		session.disableFilter("deletedUserFilter");

		return users;
	}
}
