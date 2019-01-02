package com.attra.pms.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class AreaOfWork implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int areaId;
	private String areaWork;
	//private List aowList;
	
	
	
	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getAreaWork() {
		return areaWork;
	}
	public void setAreaWork(String areaWork) {
		this.areaWork = areaWork;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*public List getAowList() {
		return aowList;
	}

	public void setAowList(List aowList) {
		//this.aowList = aowList;
		for(int i=0;i<2;i++)
		{
			setAreaId((int)aowList.get(0));
			setAreaWork((String)aowList.get(1));
		}
	}*/
	
}
