package org.paradyne.bean.admin.srd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author riteshr
 * updated by: 
 * @author Prajakta.Bhandare
 * @date 07 Jan 2013
 */
public class PayHistory extends BeanBase implements Serializable {

	private String empId = "";
	private String empName = "";
	private String tokenNo = "";
	private String rank = "";
	private String center = "";
	private String post = "";
	private String postId = "";
	private String payType = "";
	private String centerNo = "";
	private String centerName = "";
	private String octc = "";
	private String nctc = "";
	private String incrementDate = "";
	private String dceList = "";
	private String dceDate = "";
	private String payId = "";
	private ArrayList payList;
	private String paracode = "";
	private String serialNo = "";
	private String dept = "";
	private String deptId = "";
	private boolean editDetail=false;
	private boolean editFlag=false;
	private String noData="";
	private String isNotGeneralUser="";
	private String profileEmpName="";
	private String uploadFileName="";
	TreeMap assetmap;

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
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the tokenNo
	 */
	public String getTokenNo() {
		return tokenNo;
	}

	/**
	 * @param tokenNo
	 *            the tokenNo to set
	 */
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * @return the center
	 */
	public String getCenter() {
		return center;
	}

	/**
	 * @param center
	 *            the center to set
	 */
	public void setCenter(String center) {
		this.center = center;
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
	 * @return the payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param payType
	 *            the payType to set
	 */
	public void setPayType(String payType) {
		this.payType = payType;
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
	 * @return the octc
	 */
	public String getOctc() {
		return octc;
	}

	/**
	 * @param octc
	 *            the octc to set
	 */
	public void setOctc(String octc) {
		this.octc = octc;
	}

	/**
	 * @return the nctc
	 */
	public String getNctc() {
		return nctc;
	}

	/**
	 * @param nctc
	 *            the nctc to set
	 */
	public void setNctc(String nctc) {
		this.nctc = nctc;
	}

	/**
	 * @return the incrementDate
	 */
	public String getIncrementDate() {
		return incrementDate;
	}

	/**
	 * @param incrementDate
	 *            the incrementDate to set
	 */
	public void setIncrementDate(String incrementDate) {
		this.incrementDate = incrementDate;
	}

	/**
	 * @return the dceList
	 */
	public String getDceList() {
		return dceList;
	}

	/**
	 * @param dceList
	 *            the dceList to set
	 */
	public void setDceList(String dceList) {
		this.dceList = dceList;
	}

	/**
	 * @return the dceDate
	 */
	public String getDceDate() {
		return dceDate;
	}

	/**
	 * @param dceDate
	 *            the dceDate to set
	 */
	public void setDceDate(String dceDate) {
		this.dceDate = dceDate;
	}

	/**
	 * @return the payId
	 */
	public String getPayId() {
		return payId;
	}

	/**
	 * @param payId
	 *            the payId to set
	 */
	public void setPayId(String payId) {
		this.payId = payId;
	}

	/**
	 * @return the payList
	 */
	public ArrayList getPayList() {
		return payList;
	}

	/**
	 * @param payList
	 *            the payList to set
	 */
	public void setPayList(ArrayList payList) {
		this.payList = payList;
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
	 * @return the serialNo
	 */
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo
	 *            the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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
	 * @return the assetmap
	 */
	public TreeMap getAssetmap() {
		return assetmap;
	}

	/**
	 * @param assetmap
	 *            the assetmap to set
	 */
	public void setAssetmap(TreeMap assetmap) {
		this.assetmap = assetmap;
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
	 * @return the noData
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
	 * @return the isNotGeneralUser
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
	 * @return the profileEmpName
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
	 * @return the uploadFileName
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

}
