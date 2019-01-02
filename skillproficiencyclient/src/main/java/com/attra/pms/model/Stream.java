package com.attra.pms.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class Stream implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private byte streamId;
	private String streamName;
	

	public byte getStreamId() {
		return streamId;
	}


	public void setStreamId(byte streamId) {
		this.streamId = streamId;
	}


	public String getStreamName() {
		return streamName;
	}


	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
