package com.yogovi.core.service;

public interface MessageService {

	void sendMessage(String exchange, String routingKey, Object msg);
	
}
