package com.attra.pms.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

	@Component
	public class Proficiency implements Serializable {
		
		private String emailID;

		private List<ProficiencyTechnology> listOfPTechnology;

		private List<ProficiencyMethodology> listofPMethodology;

		private List<ProficiencyDomain> listofPDomain;

		private List<ProficiencyTool> listOfPTool;
		
		private String noDataAvailable;

		
	
		public String getNoDataAvailable() {
			return noDataAvailable;
		}

		public void setNoDataAvailable(String noDataAvailable) {
			this.noDataAvailable = noDataAvailable;
		}

		public String getEmailID() {
			return emailID;
		}

		public void setEmailID(String emailID) {
			this.emailID = emailID;
		}

	

		public List<ProficiencyTechnology> getListOfPTechnology() {
			return listOfPTechnology;
		}

		public void setListOfPTechnology(List<ProficiencyTechnology> listOfPTechnology) {
			this.listOfPTechnology = listOfPTechnology;
		}

		public List<ProficiencyMethodology> getListofPMethodology() {
			return listofPMethodology;
		}

		public void setListofPMethodology(List<ProficiencyMethodology> listofPMethodology) {
			this.listofPMethodology = listofPMethodology;
		}

		public List<ProficiencyDomain> getListofPDomain() {
			return listofPDomain;
		}

		public void setListofPDomain(List<ProficiencyDomain> listofPDomain) {
			this.listofPDomain = listofPDomain;
		}

		public List<ProficiencyTool> getListOfPTool() {
			return listOfPTool;
		}

		public void setListOfPTool(List<ProficiencyTool> listOfPTool) {
			this.listOfPTool = listOfPTool;
		}
	
}
	
