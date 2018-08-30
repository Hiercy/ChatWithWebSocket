package com.mike.SimpleChat.domain;

import com.mike.SimpleChat.enums.MessageType;

public class Chat {

	private MessageType type;
	private String text;
	private String sender;
	
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
}
