/**
 * 
 */
package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class RelationMaster extends BeanBase implements Serializable {
	private String modeLength="false";
	private String totalRecords="";
	private String relationCode= "";
	private String relationName = "";
	
	private String isActive=""; // Status of RelationMaster
	
	private ArrayList relationList=null;
	private String myPage;
	private String show;
	private String hiddencode;
	private String hdeleteCode;
	
	public RelationMaster() {
		
	}

	/**
	 * @param relationCode
	 * @param relationName
	 
	 */
	public RelationMaster(String relationCode, String relationName, String isActive ) {
		this.relationCode = relationCode;
		this.relationName = relationName;
		this.isActive=isActive;
				
	}
	
	/**
	 * @return the relationCode
	 */
	public String getRelationCode() {
		return relationCode;
	}

	/**
	 * @param relationCode the relationCode to set
	 */
	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}

	/**
	 * @return the relationName
	 */
	public String getRelationName() {
		return relationName;
	}

	/**
	 * @param relationName the relationName to set
	 */
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public ArrayList getRelationList() {
		return relationList;
	}

	public void setRelationList(ArrayList relationList) {
		this.relationList = relationList;
	}

	public String getMyPage() {
		// TODO Auto-generated method stub
		return myPage;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	public String getModeLength() {
		return modeLength;
	}

	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	
			
}