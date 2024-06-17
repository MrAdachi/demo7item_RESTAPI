package com.udemy.demo7item.model;

public class HelloMessage {
	
	public HelloMessage(String inMsg) {
		this.strMessage = inMsg;
	}
	
	private String strMessage;

	public String getStrMessage() {
		return strMessage;
	}

	public void setStrMessage(String strMessage) {
		this.strMessage = strMessage;
	}

}
