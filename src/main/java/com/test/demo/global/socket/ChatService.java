package com.test.demo.global.socket;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
	private final ObjectMapper objectMapper;
	private final Map<String, ChatRoom> chatRooms = new LinkedHashMap<>();

	public List<ChatRoom> findAllRoom() {
		return new LinkedList<>(chatRooms.values());
	}

	public ChatRoom findRoomById(String roomId) {
		return chatRooms.get(roomId);
	}

	public ChatRoom createRoom(String name) {
		ChatRoom chatRoom = ChatRoom.create(name);
		chatRooms.put(chatRoom.getRoomId(), chatRoom);
		return chatRoom;
	}

	public <T> void sendMessage(WebSocketSession session, T message) {
		try{
			session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void deleteRoom(String roomId) {
		chatRooms.remove(roomId);
	}
}
