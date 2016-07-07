/**
 * Created on (2nd Feb 2011)
 */

package org.paradyne.bean.LMS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class DisputeManagementSystem extends BeanBase {
	ArrayList<Object> disputeManagementList=null;
	private String disputeID;
	private String disputeRaisedBy;
	private String typeOfDispute;
	private String underAct;
	private String disputeStatement;
	private String description;
	private String loggedOnDate;
	private String resolutionStatement;
	private String resolutionMethod;
	private String committeeName;
	private String committeeType;
	private String otherInvolvedParties;
	private String representativeName;
	private String disputeRaisedID;
	private String underActID;
	private String committeeID;	
	private String hiddendisputeListID;
	
	private boolean modeLength = false;
	
	public boolean isModeLength() {
		return modeLength;
	}
	public void setModeLength(boolean modeLength) {
		this.modeLength = modeLength;
	}
	public String getDisputeID() {
		return disputeID;
	}
	public void setDisputeID(String disputeID) {
		this.disputeID = disputeID;
	}
	public String getDisputeRaisedBy() {
		return disputeRaisedBy;
	}
	public void setDisputeRaisedBy(String disputeRaisedBy) {
		this.disputeRaisedBy = disputeRaisedBy;
	}
	public String getTypeOfDispute() {
		return typeOfDispute;
	}
	public void setTypeOfDispute(String typeOfDispute) {
		this.typeOfDispute = typeOfDispute;
	}
	public String getUnderAct() {
		return underAct;
	}
	public void setUnderAct(String underAct) {
		this.underAct = underAct;
	}
	public String getDisputeStatement() {
		return disputeStatement;
	}
	public void setDisputeStatement(String disputeStatement) {
		this.disputeStatement = disputeStatement;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLoggedOnDate() {
		return loggedOnDate;
	}
	public void setLoggedOnDate(String loggedOnDate) {
		this.loggedOnDate = loggedOnDate;
	}
	public String getResolutionStatement() {
		return resolutionStatement;
	}
	public void setResolutionStatement(String resolutionStatement) {
		this.resolutionStatement = resolutionStatement;
	}
	public String getResolutionMethod() {
		return resolutionMethod;
	}
	public void setResolutionMethod(String resolutionMethod) {
		this.resolutionMethod = resolutionMethod;
	}
	public String getCommitteeName() {
		return committeeName;
	}
	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}
	public String getCommitteeType() {
		return committeeType;
	}
	public void setCommitteeType(String committeeType) {
		this.committeeType = committeeType;
	}
	public String getDisputeRaisedID() {
		return disputeRaisedID;
	}
	public void setDisputeRaisedID(String disputeRaisedID) {
		this.disputeRaisedID = disputeRaisedID;
	}
	public String getUnderActID() {
		return underActID;
	}
	public void setUnderActID(String underActID) {
		this.underActID = underActID;
	}
	public ArrayList<Object> getDisputeManagementList() {
		return disputeManagementList;
	}
	public void setDisputeManagementList(ArrayList<Object> disputeManagementList) {
		this.disputeManagementList = disputeManagementList;
	}
	public String getCommitteeID() {
		return committeeID;
	}
	public void setCommitteeID(String committeeID) {
		this.committeeID = committeeID;
	}	
	public String getOtherInvolvedParties() {
		return otherInvolvedParties;
	}
	public void setOtherInvolvedParties(String otherInvolvedParties) {
		this.otherInvolvedParties = otherInvolvedParties;
	}
	public String getHiddendisputeListID() {
		return hiddendisputeListID;
	}
	public void setHiddendisputeListID(String hiddendisputeListID) {
		this.hiddendisputeListID = hiddendisputeListID;
	}
	public String getRepresentativeName() {
		return representativeName;
	}
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	} 
}
