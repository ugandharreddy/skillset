package com.attra.pms.model;

import java.util.List;

public class SMEMyTask {
	
	private static final long serialVersionUID = 1L;

	private List<SmeMyTaskView> listOfReview;

	private List<SmeMyTaskView> listofNoReview;

	public List<SmeMyTaskView> getListOfReview() {
		return listOfReview;
	}

	public void setListOfReview(List<SmeMyTaskView> listOfReview) {
		this.listOfReview = listOfReview;
	}

	public List<SmeMyTaskView> getListofNoReview() {
		return listofNoReview;
	}

	public void setListofNoReview(List<SmeMyTaskView> listofNoReview) {
		this.listofNoReview = listofNoReview;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
