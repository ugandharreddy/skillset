package com.attra.pms.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class SuggestSkillName implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int skillId;
	private String streamId;
	private String skillName;
	
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getStreamId() {
		return streamId;
	}
	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
}
