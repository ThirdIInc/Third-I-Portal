/**
 * 
 */
package org.paradyne.bean.admin.master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author diptip
 *
 */
public class Journey extends BeanBase {
	private String journeyMode="";
	private String journeyClass="";
	
	private String jClass="";
	private String hiddenEdit="";
	private String srNo="";
	private ArrayList list;
	private ArrayList journeyList;
	private String subcode="";
	private String tableLength="0";
	private String hiddenMode="";
	private String hdeleteOp="";
	public String getHdeleteOp() {
		return hdeleteOp;
	}
	public void setHdeleteOp(String hdeleteOp) {
		this.hdeleteOp = hdeleteOp;
	}
	public String getHiddenMode() {
		return hiddenMode;
	}
	public void setHiddenMode(String hiddenMode) {
		this.hiddenMode = hiddenMode;
	}
	public String getSubcode() {
		return subcode;
	}
	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}
	public String getTableLength() {
		return tableLength;
	}
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
	public String getJourneyMode() {
		return journeyMode;
	}
	public void setJourneyMode(String journeyMode) {
		this.journeyMode = journeyMode;
	}
	public String getJourneyClass() {
		return journeyClass;
	}
	public void setJourneyClass(String journeyClass) {
		this.journeyClass = journeyClass;
	}
	
	public String getJClass() {
		return jClass;
	}
	public void setJClass(String class1) {
		jClass = class1;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public ArrayList getJourneyList() {
		return journeyList;
	}
	public void setJourneyList(ArrayList journeyList) {
		this.journeyList = journeyList;
	}

}
