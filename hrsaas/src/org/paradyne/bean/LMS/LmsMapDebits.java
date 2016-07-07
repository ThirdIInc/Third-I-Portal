package org.paradyne.bean.LMS;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class LmsMapDebits extends BeanBase{
	
	ArrayList mapDebitList;
	private String debitID ="";
	private String debitName = "";
	private String debitType = "";
	
	public String getDebitID() {
		return debitID;
	}
	public void setDebitID(String debitID) {
		this.debitID = debitID;
	}
	public String getDebitName() {
		return debitName;
	}
	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}
	public String getDebitType() {
		return debitType;
	}
	public void setDebitType(String debitType) {
		this.debitType = debitType;
	}
	public ArrayList getMapDebitList() {
		return mapDebitList;
	}
	public void setMapDebitList(ArrayList mapDebitList) {
		this.mapDebitList = mapDebitList;
	}
	

}
