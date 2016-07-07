package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author riteshr
 * Updated By
 * @author Prajakta.Bhandare
 * @date 09 Jan 2013
 */
public class PromotionHistory extends BeanBase implements Serializable {

	public String promoId = "";
	public String empId = "";
	public String post = "";
	private String rank = "";
	public String postId = "";
	public String paracode = "";
	public String dceListDate = "";
	public String deptId = "";
	public String dept = "";
	public String centerName = "";
	public String centerNo = "";
	public String ctc = "";
	public String oldCtc = "";
	public String newCtc = "";
	private ArrayList promoList;
	private ArrayList historyList;
	private String promotionDate = "";
	private String efectiveDate;
	private String prmlength;
	private String profileEmpName = "";
	private String uploadFileName = "";
	private String isNotGeneralUser = "";
	private String noData = "";
	private boolean editDetail = false;
	private boolean editFlag = false;

	/**
	 * @return the promoList
	 */
	public ArrayList getPromoList() {
		return promoList;
	}

	/**
	 * @param promoList
	 *            the promoList to set
	 */
	public void setPromoList(ArrayList promoList) {
		this.promoList = promoList;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return the post
	 */
	public String getPost() {
		return post;
	}

	/**
	 * @param post
	 *            the post to set
	 */
	public void setPost(String post) {
		this.post = post;
	}

	/**
	 * @return the promoId
	 */
	public String getPromoId() {
		return promoId;
	}

	/**
	 * @param promoId
	 *            the promoId to set
	 */
	public void setPromoId(String promoId) {
		this.promoId = promoId;
	}

	/**
	 * @return the postId
	 */
	public String getPostId() {
		return postId;
	}

	/**
	 * @param postId
	 *            the postId to set
	 */
	public void setPostId(String postId) {
		this.postId = postId;
	}

	/**
	 * @return the paracode
	 */
	public String getParacode() {
		return paracode;
	}

	/**
	 * @param paracode
	 *            the paracode to set
	 */
	public void setParacode(String paracode) {
		this.paracode = paracode;
	}

	/**
	 * @return the dceListDate
	 */
	public String getDceListDate() {
		return dceListDate;
	}

	/**
	 * @param dceListDate
	 *            the dceListDate to set
	 */
	public void setDceListDate(String dceListDate) {
		this.dceListDate = dceListDate;
	}

	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId
	 *            the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * @param dept
	 *            the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}

	/**
	 * @return the centerName
	 */
	public String getCenterName() {
		return centerName;
	}

	/**
	 * @param centerName
	 *            the centerName to set
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	/**
	 * @return the centerNo
	 */
	public String getCenterNo() {
		return centerNo;
	}

	/**
	 * @param centerNo
	 *            the centerNo to set
	 */
	public void setCenterNo(String centerNo) {
		this.centerNo = centerNo;
	}

	/**
	 * @return the ctc
	 */
	public String getCtc() {
		return ctc;
	}

	/**
	 * @param ctc the ctc to set
	 */
	public void setCtc(String ctc) {
		this.ctc = ctc;
	}

	/**
	 * @return oldCtc
	 */
	public String getOldCtc() {
		return oldCtc;
	}

	/**
	 * @param oldCtc 
	 * the oldCtc to set
	 */
	public void setOldCtc(String oldCtc) {
		this.oldCtc = oldCtc;
	}

	/**
	 * @return newCtc
	 */
	public String getNewCtc() {
		return newCtc;
	}

	/**
	 * @param newCtc 
	 * the newCtc to set
	 */
	public void setNewCtc(String newCtc) {
		this.newCtc = newCtc;
	}

	/**
	 * @return historyList
	 */
	public ArrayList getHistoryList() {
		return historyList;
	}

	/**
	 * @param historyList
	 * the historyList to set
	 */
	public void setHistoryList(ArrayList historyList) {
		this.historyList = historyList;
	}

	/**
	 * @return promotionDate
	 */
	public String getPromotionDate() {
		return promotionDate;
	}

	/**
	 * @param promotionDate
	 * the promotionDate to set
	 */
	public void setPromotionDate(String promotionDate) {
		this.promotionDate = promotionDate;
	}

	/**
	 * @return efectiveDate
	 */ 
	public String getEfectiveDate() {
		return efectiveDate;
	}

	/**
	 * @param efectiveDate
	 * the efectiveDate to set
	 */
	public void setEfectiveDate(String efectiveDate) {
		this.efectiveDate = efectiveDate;
	}

	/**
	 * @return prmlength
	 */
	public String getPrmlength() {
		return prmlength;
	}

	/**
	 * @param prmlength
	 * the prmlength to set
	 */
	public void setPrmlength(String prmlength) {
		this.prmlength = prmlength;
	}

	/**
	 * @return profileEmpName
	 */
	public String getProfileEmpName() {
		return profileEmpName;
	}

	/**
	 * @param profileEmpName
	 * the profileEmpName to set
	 */
	public void setProfileEmpName(String profileEmpName) {
		this.profileEmpName = profileEmpName;
	}

	/**
	 * @return uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 * the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return editDetail
	 */
	public boolean isEditDetail() {
		return editDetail;
	}

	/**
	 * @param editDetail
	 * the editDetail to set
	 */
	public void setEditDetail(boolean editDetail) {
		this.editDetail = editDetail;
	}

	/**
	 * @return editFlag
	 */
	public boolean isEditFlag() {
		return editFlag;
	}

	/**
	 * @param editFlag
	 * the editFlag to set
	 */
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	/**
	 * @return isNotGeneralUser
	 */
	public String getIsNotGeneralUser() {
		return isNotGeneralUser;
	}

	/**
	 * @param isNotGeneralUser
	 * the isNotGeneralUser to set
	 */
	public void setIsNotGeneralUser(String isNotGeneralUser) {
		this.isNotGeneralUser = isNotGeneralUser;
	}

	/**
	 * @return noData
	 */
	public String getNoData() {
		return noData;
	}

	/**
	 * @param noData
	 * the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}

	/**
	 * @return rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @param rank
	 * the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

}
