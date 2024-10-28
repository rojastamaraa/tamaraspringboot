package com.example.demo.service;

import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class ChatHandler extends TextWebSocketHandler{
	private static final Logger LOGGER = LoggerFactory.getLogger(ChatHandler.class);
	private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		sessions.add(session);
		
		 String welcomeMessage = "Bienvenido al chat!";
		    session.sendMessage(new TextMessage(welcomeMessage));
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		sessions.remove(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	    String clientmessage = message.getPayload();
	    
	    if(clientmessage.contains("2")) {
	    	String msj = clientmessage + "<span style = 'color:green;'>Correcto</span>";
	    	
	    	for(WebSocketSession webSocketSession: sessions) {
	    		webSocketSession.sendMessage(new TextMessage(msj));
	    	}
	    } else {
	    	for(WebSocketSession webSocketSession: sessions) {
	    		webSocketSession.sendMessage(message);
	    	}
	    }
	}


}
