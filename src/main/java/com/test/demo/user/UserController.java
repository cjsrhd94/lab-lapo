package com.test.demo.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

	private final UserService userService;

	@PostMapping
	public Long signUp(@RequestBody UserReqDto userReqDto) {
		return userService.create(userReqDto);
	}

	@GetMapping
	public List<User> findAll() {
		return userService.findAllCustom();
	}
}
