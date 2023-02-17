package com.test.demo.global.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StompChatController {

	private final SimpMessagingTemplate template;

	@MessageMapping(value = "/chat/enter")
	public void enter(ChatMessage message) {
		message.setMessage(message.getSender() + "님이 채팅방에 참여하였습니다.");
		template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
	}

	@MessageMapping(value = "/chat/message")
	public void message(ChatMessage message) {
		template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
	}
}
