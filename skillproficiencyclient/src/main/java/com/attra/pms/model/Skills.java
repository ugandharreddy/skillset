package com.attra.pms.model;

import java.io.Serializable;


public class Skills implements Serializable {

	
	
	private int skillId;
	
	private int streamId;
	
	private String skill;

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public int getStreamId() {
		return streamId;
	}

	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

}
