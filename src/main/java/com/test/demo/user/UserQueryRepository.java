package com.test.demo.user;

import static com.test.demo.entity.QUser.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

	private final JPAQueryFactory queryFactory;

	public List<User> findAll() {
		return queryFactory
			.selectFrom(user)
			.fetch();
	}
}
