package com.test.demo.global.socket;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class RoomController {
	private final ChatService chatService;

	@PostMapping(value = "/room")
	public ChatRoom createRoom(@RequestBody Map<String, String> name) {
		return chatService.createRoom(name.get("name"));
	}

	@GetMapping(value = "/rooms")
	public List<ChatRoom> findAllRoom() {
		return chatService.findAllRoom();
	}

	@DeleteMapping(value = "/room")
	public void deleteRoom(@RequestBody Map<String, String> roomId) {
		chatService.deleteRoom(roomId.get("roomId"));
	}
}
