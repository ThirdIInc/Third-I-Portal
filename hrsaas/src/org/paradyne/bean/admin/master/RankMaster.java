package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author tinshuk.banafar
 *
 */
public class RankMaster extends BeanBase implements Serializable{
	private String modeLength="false";
	private String totalRecords="";
	private String designationCode="";
	private String designationName;
	private String designationDesc;
	private String designationAbbr;
	private String isActive;
	private String isActiveItt;
	private String hdomainCode;
	
	//Added by Prashant
	private String salaryRange="";
	private String salaryRangeItt="";
	
	private ArrayList designationList=null;
	private String confChk;
	private String hdeleteCode;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddencode;
	private String editFlag;
	private String cancelFlag;
	private String pageStatus;
	private String designationCodeItt;
	
	private String checkAll="";
	private boolean designationView=false;
	
	//added by TSB
	private String report="";
	private String hiddenChechfrmId="";
	
	
	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getHiddenChechfrmId() {
		return hiddenChechfrmId;
	}

	public void setHiddenChechfrmId(String hiddenChechfrmId) {
		this.hiddenChechfrmId = hiddenChechfrmId;
	}

	/**
	 * @return the designationCode
	 */
	public String getDesignationCode() {
		return designationCode;
	}

	/**
	 * @return the designationName
	 */
	public String getDesignationName() {
		return designationName;
	}

	/**
	 * @return the designationDesc
	 */
	public String getDesignationDesc() {
		return designationDesc;
	}

	/**
	 * @return the designationAbbr
	 */
	public String getDesignationAbbr() {
		return designationAbbr;
	}

	/**
	 * @return the designationStatus
	 */
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the hdomainCode
	 */
	public String getHdomainCode() {
		return hdomainCode;
	}

	/**
	 * @return the designationList
	 */
	public ArrayList getDesignationList() {
		return designationList;
	}

	/**
	 * @return the confChk
	 */
	public String getConfChk() {
		return confChk;
	}

	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}

	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}

	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}

	/**
	 * @return the selectname
	 */
	public String getSelectname() {
		return selectname;
	}

	/**
	 * @return the hiddencode
	 */
	public String getHiddencode() {
		return hiddencode;
	}

	/**
	 * @param designationCode the designationCode to set
	 */
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}

	/**
	 * @param designationName the designationName to set
	 */
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	/**
	 * @param designationDesc the designationDesc to set
	 */
	public void setDesignationDesc(String designationDesc) {
		this.designationDesc = designationDesc;
	}

	/**
	 * @param designationAbbr the designationAbbr to set
	 */
	public void setDesignationAbbr(String designationAbbr) {
		this.designationAbbr = designationAbbr;
	}

	/**
	 * @param designationStatus the designationStatus to set
	 */
	

	/**
	 * @param hdomainCode the hdomainCode to set
	 */
	public void setHdomainCode(String hdomainCode) {
		this.hdomainCode = hdomainCode;
	}

	/**
	 * @param designationList the designationList to set
	 */
	public void setDesignationList(ArrayList designationList) {
		this.designationList = designationList;
	}

	/**
	 * @param confChk the confChk to set
	 */
	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}

	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @param show the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}

	/**
	 * @param selectname the selectname to set
	 */
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}

	/**
	 * @param hiddencode the hiddencode to set
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public boolean isDesignationView() {
		return designationView;
	}

	public void setDesignationView(boolean designationView) {
		this.designationView = designationView;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getCheckAll() {
		return checkAll;
	}

	public void setCheckAll(String checkAll) {
		this.checkAll = checkAll;
	}

	public String getModeLength() {
		return modeLength;
	}

	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	public String getSalaryRange() {
		return salaryRange;
	}

	public void setSalaryRange(String salaryRange) {
		this.salaryRange = salaryRange;
	}

	public String getSalaryRangeItt() {
		return salaryRangeItt;
	}

	public void setSalaryRangeItt(String salaryRangeItt) {
		this.salaryRangeItt = salaryRangeItt;
	}

	public String getDesignationCodeItt() {
		return designationCodeItt;
	}

	public void setDesignationCodeItt(String designationCodeItt) {
		this.designationCodeItt = designationCodeItt;
	}

	public String getIsActiveItt() {
		return isActiveItt;
	}

	public void setIsActiveItt(String isActiveItt) {
		this.isActiveItt = isActiveItt;
	}
}
