package com.mike.SimpleChat.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.mike.SimpleChat.domain.Chat;
import com.mike.SimpleChat.enums.MessageType;

@Component
public class WebSocketEventListener {

	private static final Logger log = LoggerFactory.getLogger(EventListener.class);
	
	@Autowired
	private SimpMessageSendingOperations messageTemplate;
	
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		log.info("New web socket connection");
	}
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		
		String username = (String) headerAccessor.getSessionAttributes().get("username");
		
		if(username != null) {
			log.info("User Disconnected: " + username);
			
			Chat chatMessage = new Chat();
			chatMessage.setType(MessageType.LEFT);
			chatMessage.setSender(username);
			
			messageTemplate.convertAndSend("/topic/publicChatRoom", chatMessage);
		}
	}
	
}
