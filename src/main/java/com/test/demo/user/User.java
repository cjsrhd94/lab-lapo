package com.test.demo.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import com.test.demo.comment.Comment;
import com.test.demo.post.Post;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FilterDef(name = "deletedUserFilter", parameters = @ParamDef(name = "deleted", type = "boolean"))
@Filter(name = "deletedUserFilter", condition = "deleted = :isDeleted")
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;
	private String password;

	private final boolean deleted = false;

	@OneToMany(mappedBy = "user")
	private final List<Post> posts = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private final List<Comment> comments = new ArrayList<>();

	@Builder
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
