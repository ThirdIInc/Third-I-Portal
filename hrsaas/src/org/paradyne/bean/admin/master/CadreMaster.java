/**
 * 
 */
package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class CadreMaster extends BeanBase implements Serializable {
	
	String cadreID = "";
	String cadreName = "";
	String cadreDesc = "";
	String cadreAbbr = "";
	String cadreParID = "";
	String cadreParName="";
	String cadreIsActive="";
	
	private String myPage;
	private String show;
	private String  hiddencode;
	private String hdeleteCode;
	private String modeLength="false";
	private String totalRecords="";
	/* updated by Anantha Lakshmi*/
	private String hiddenCompetency;
	private String srNo;
	private String competency;
	private String competency1;
	private String competencyCode;
	private String tableLength="0";
	private ArrayList list;
	private String Value;
	private String subCode;
	private String subcode;
	private String hiddenEdit;
	private String hiddenCode;
	private String hdeleteOp;
	private String confChkop="";
	
	private ArrayList cardeList;
	private ArrayList cadrelist;
	
	/**
	 * @param cadreID
	 * @param cadreName
	 * @param cadreDesc
	 * @param cadreAbbr
	 * @param cadreParID
	 */
	public CadreMaster(String cadreID, String cadreName, String cadreDesc, String cadreAbbr, String cadreParID,String competency,String competencyCode,String srNo,ArrayList list) {
		this.cadreID = cadreID;
		this.cadreName = cadreName;
		this.cadreDesc = cadreDesc;
		this.cadreAbbr = cadreAbbr;
		this.cadreParID = cadreParID;
		this.competencyCode=competencyCode;
		this.competency=competency;
		
	}
	
	public ArrayList getCardeList() {
		return cardeList;
	}

	public void setCardeList(ArrayList cardeList) {
		this.cardeList = cardeList;
	}

	public CadreMaster() {
		
	}

	/**
	 * @param cadreID
	 * @param cadreName
	 * @param cadreDesc
	 * @param cadreAbbr
	 * @param cadreParID
	 */
	/*public CadreMaster(String cadreID, String cadreName, String cadreDesc, String cadreAbbr, String cadreParID,String competency,String competencyCode,String srNo,ArrayList list) {
		this.cadreID = cadreID;
		this.cadreName = cadreName;
		this.cadreDesc = cadreDesc;
		this.cadreAbbr = cadreAbbr;
		this.cadreParID = cadreParID;
		this.competencyCode=competencyCode;
		this.competency=competency;
		
	}*/

	/**
	 * @return the cadreAbbr
	 */
	public String getCadreAbbr() {
		return cadreAbbr;
	}

	/**
	 * @param cadreAbbr the cadreAbbr to set
	 */
	public void setCadreAbbr(String cadreAbbr) {
		this.cadreAbbr = cadreAbbr;
	}

	/**
	 * @return the cadreDesc
	 */
	public String getCadreDesc() {
		return cadreDesc;
	}

	/**
	 * @param cadreDesc the cadreDesc to set
	 */
	public void setCadreDesc(String cadreDesc) {
		this.cadreDesc = cadreDesc;
	}

	/**
	 * @return the cadreID
	 */
	public String getCadreID() {
		return cadreID;
	}

	/**
	 * @param cadreID the cadreID to set
	 */
	public void setCadreID(String cadreID) {
		this.cadreID = cadreID;
	}

	/**
	 * @return the cadreName
	 */
	public String getCadreName() {
		return cadreName;
	}

	/**
	 * @param cadreName the cadreName to set
	 */
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}

	/**
	 * @return the cadreParID
	 */
	public String getCadreParID() {
		return cadreParID;
	}

	/**
	 * @param cadreParID the cadreParID to set
	 */
	public void setCadreParID(String cadreParID) {
		this.cadreParID = cadreParID;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
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

	public String getCadreParName() {
		return cadreParName;
	}

	public void setCadreParName(String cadreParName) {
		this.cadreParName = cadreParName;
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

	public String getCadreIsActive() {
		return cadreIsActive;
	}

	public void setCadreIsActive(String cadreIsActive) {
		this.cadreIsActive = cadreIsActive;
	}

	public String getCompetency() {
		return competency;
	}

	public void setCompetency(String competency) {
		this.competency = competency;
	}

	public String getCompetencyCode() {
		return competencyCode;
	}

	public void setCompetencyCode(String competencyCode) {
		this.competencyCode = competencyCode;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getTableLength() {
		return tableLength;
	}

	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}

	public ArrayList getCadrelist() {
		return cadrelist;
	}

	public void setCadrelist(ArrayList cadrelist) {
		this.cadrelist = cadrelist;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getSubcode() {
		return subcode;
	}

	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}

	public String getHiddenEdit() {
		return hiddenEdit;
	}

	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}

	public String getHiddenCode() {
		return hiddenCode;
	}

	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}

	public String getHdeleteOp() {
		return hdeleteOp;
	}

	public void setHdeleteOp(String hdeleteOp) {
		this.hdeleteOp = hdeleteOp;
	}

	public String getConfChkop() {
		return confChkop;
	}

	public void setConfChkop(String confChkop) {
		this.confChkop = confChkop;
	}

	public String getCompetency1() {
		return competency1;
	}

	public void setCompetency1(String competency1) {
		this.competency1 = competency1;
	}

	public String getHiddenCompetency() {
		return hiddenCompetency;
	}

	public void setHiddenCompetency(String hiddenCompetency) {
		this.hiddenCompetency = hiddenCompetency;
	}
	
}