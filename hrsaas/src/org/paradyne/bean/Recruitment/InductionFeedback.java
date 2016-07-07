/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author varunk
 *
 */
public class InductionFeedback extends BeanBase {
	
	private String inductionName;
	private String cntPersonId;
	private String cntPersonToken;
	private String contactPerson;
	private String inductionFrom;
	private String inductionTo;
	private String inducDesc;
	private String inducVenue;
	private String inductionCode;
	private String quesName;
	private String comments;
	private String quesCode;
	private String rating;
	private String inductionDtlCode;
	private String suggestion;
	private String attachFile="";
	private String dataPath = "";
	TreeMap tmap;
	private boolean showUploadButton = false;
	private boolean showAttachedFileLink = false;
	
	private ArrayList questionList=null;

	public String getInductionName() {
		return inductionName;
	}

	public void setInductionName(String inductionName) {
		this.inductionName = inductionName;
	}

	public String getCntPersonId() {
		return cntPersonId;
	}

	public void setCntPersonId(String cntPersonId) {
		this.cntPersonId = cntPersonId;
	}

	public String getCntPersonToken() {
		return cntPersonToken;
	}

	public void setCntPersonToken(String cntPersonToken) {
		this.cntPersonToken = cntPersonToken;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getInductionFrom() {
		return inductionFrom;
	}

	public void setInductionFrom(String inductionFrom) {
		this.inductionFrom = inductionFrom;
	}

	public String getInductionTo() {
		return inductionTo;
	}

	public void setInductionTo(String inductionTo) {
		this.inductionTo = inductionTo;
	}

	public String getInducDesc() {
		return inducDesc;
	}

	public void setInducDesc(String inducDesc) {
		this.inducDesc = inducDesc;
	}

	public String getInducVenue() {
		return inducVenue;
	}

	public void setInducVenue(String inducVenue) {
		this.inducVenue = inducVenue;
	}

	public String getInductionCode() {
		return inductionCode;
	}

	public void setInductionCode(String inductionCode) {
		this.inductionCode = inductionCode;
	}

	public String getQuesName() {
		return quesName;
	}

	public void setQuesName(String quesName) {
		this.quesName = quesName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getQuesCode() {
		return quesCode;
	}

	public void setQuesCode(String quesCode) {
		this.quesCode = quesCode;
	}

	

	public ArrayList getQuestionList() {
		return questionList;
	}

	public void setQuestionList(ArrayList questionList) {
		this.questionList = questionList;
	}

	public String getInductionDtlCode() {
		return inductionDtlCode;
	}

	public void setInductionDtlCode(String inductionDtlCode) {
		this.inductionDtlCode = inductionDtlCode;
	}

	public TreeMap getTmap() {
		return tmap;
	}

	public void setTmap(TreeMap tmap) {
		this.tmap = tmap;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public boolean isShowUploadButton() {
		return showUploadButton;
	}

	public void setShowUploadButton(boolean showUploadButton) {
		this.showUploadButton = showUploadButton;
	}

	public boolean isShowAttachedFileLink() {
		return showAttachedFileLink;
	}

	public void setShowAttachedFileLink(boolean showAttachedFileLink) {
		this.showAttachedFileLink = showAttachedFileLink;
	}

}
