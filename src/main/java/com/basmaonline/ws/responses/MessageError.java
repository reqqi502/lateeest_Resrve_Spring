package com.basmaonline.ws.responses;

import java.util.Date;

public class MessageError {

	private String timestamp;
	private Date date;
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public MessageError(Date date, String timestamp) {
		super();
		this.timestamp = timestamp;
		this.date = date;
	}
}
