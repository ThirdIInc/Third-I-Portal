package org.paradyne.bean.admin.master;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;
/**
 * 
 * @author Krishna
 * Date:07-10-2008
 */
public class TaskProject extends BeanBase {

	
	private String modeLength="false";
	private String showFlag="false";
	private String totalRecords="";
	private String proCode;
	private String projName;
	private String hiddenisActive="";
	
	private String isActive=""; //Status is added by Abhijit
	
	private String myPage;
	private String hiddencode;
	private String show;
	ArrayList iteratorlist;
	private String hdeleteCode;
	private String empToken="";
	private String empName="";
	private String empCode="";
	private String ittrempToken="";
	private String ittrempempCode="";
	private String ittrempName="";
	ArrayList stakeholderlist;
	private String paraId="";
	private String editFlag="false";
	private String projStatus="";
	private String ittrProjStatus="";
	ArrayList statuslist;
	

	public String getProjStatus() {
		return projStatus;
	}
	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}
	public String getIttrProjStatus() {
		return ittrProjStatus;
	}
	public void setIttrProjStatus(String ittrProjStatus) {
		this.ittrProjStatus = ittrProjStatus;
	}
	public ArrayList getStatuslist() {
		return statuslist;
	}
	public void setStatuslist(ArrayList statuslist) {
		this.statuslist = statuslist;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public ArrayList getStakeholderlist() {
		return stakeholderlist;
	}
	public void setStakeholderlist(ArrayList stakeholderlist) {
		this.stakeholderlist = stakeholderlist;
	}
	public String getIttrempToken() {
		return ittrempToken;
	}
	public void setIttrempToken(String ittrempToken) {
		this.ittrempToken = ittrempToken;
	}
	public String getIttrempempCode() {
		return ittrempempCode;
	}
	public void setIttrempempCode(String ittrempempCode) {
		this.ittrempempCode = ittrempempCode;
	}
	public String getIttrempName() {
		return ittrempName;
	}
	public void setIttrempName(String ittrempName) {
		this.ittrempName = ittrempName;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	/**
	 * @return the projName
	 */
	public String getProjName() {
		return projName;
	}
	/**
	 * @param projName the projName to set
	 */
	public void setProjName(String projName) {
		this.projName = projName;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the hiddencode
	 */
	public String getHiddencode() {
		return hiddencode;
	}
	/**
	 * @param hiddencode the hiddencode to set
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}
	/**
	 * @param show the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}
	/**
	 * @return the iteratorlist
	 */
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	/**
	 * @param iteratorlist the iteratorlist to set
	 */
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	/**
	 * @return the proCode
	 */
	public String getProCode() {
		return proCode;
	}
	/**
	 * @param proCode the proCode to set
	 */
	public void setProCode(String proCode) {
		this.proCode = proCode;
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
	public String getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getHiddenisActive() {
		return hiddenisActive;
	}
	public void setHiddenisActive(String hiddenisActive) {
		this.hiddenisActive = hiddenisActive;
	}
	
	
	

	
}
