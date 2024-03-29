package com.test.demo.global.socket;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;

@Getter
public class ChatRoom {
	private String roomId;
	private String name;
	private Set<WebSocketSession> sessions = new HashSet<>();

	public static ChatRoom create(String name) {
		ChatRoom chatRoom = new ChatRoom();

		chatRoom.roomId = UUID.randomUUID().toString();
		chatRoom.name = name;
		return chatRoom;
	}

	public void handlerActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
		if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
			sessions.add(session);
			chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
			sendMessage(chatMessage, chatService);
		} else if (chatMessage.getType().equals(ChatMessage.MessageType.EXIT)) {
			chatMessage.setMessage(chatMessage.getSender() + "님이 나가셨습니다.");
			sendMessage(chatMessage, chatService);
			sessions.remove(session);
		} else {
			sendMessage(chatMessage, chatService);
		}
	}

	private <T> void sendMessage(T message, ChatService chatService) {
		sessions
			.forEach(session -> chatService.sendMessage(session, message));
	}
}
