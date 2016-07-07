package org.paradyne.bean.portal;

import org.paradyne.lib.BeanBase;

public class MyNotes extends BeanBase {
	
	private String hiddenNoteId ="";
	private String hiddenEditCode ="";
		
	private String description ="";
 
	 
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHiddenNoteId() {
		return hiddenNoteId;
	}
	public void setHiddenNoteId(String hiddenNoteId) {
		this.hiddenNoteId = hiddenNoteId;
	}
	public String getHiddenEditCode() {
		return hiddenEditCode;
	}
	public void setHiddenEditCode(String hiddenEditCode) {
		this.hiddenEditCode = hiddenEditCode;
	}

}
