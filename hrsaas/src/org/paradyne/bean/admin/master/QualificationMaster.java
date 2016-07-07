package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author Prasad
 */

public class QualificationMaster extends BeanBase implements Serializable{
	private String modeLength="false";
	private String quaID="";
	private String quaName="";
	private String quaAbbr="";
	private String qualevel="";
	private String hidIsactive="";
	private String desciption="";
	private String isactive="";
	private String hdomainCode="";
	private String hidLevel="";
	private ArrayList qualificationList=null;
	private String confChk="";
	private String hdeleteCode="";
	private String myPage="";
	private String show="";
	private String selectname="";
	private String hiddencode="";
	private String ittSrN0="";
	private String report="";
	/**
	 * Flags Required  for Industry Master
	 */
	
	private boolean onLoadFlag=false;
	private boolean saveFlag=false;
	private boolean flag=false;
	private boolean editFlag=false;
	private boolean pageFlag=false;
	private boolean dblFlag=false;
	private String qualificationID="";
	/**
	 * Flags For Cancel Button
	 */
	
	private boolean loadFlag;
	private boolean addFlag;
	private boolean modFlag;
	private boolean dbFlag;
	
	private String totalRecords="";
	private boolean qualiView=false;
	private String checkAll="";
	
	public String getQuaID() {
		return quaID;
	}
	public void setQuaID(String quaID) {
		this.quaID = quaID;
	}
	public String getQuaName() {
		return quaName;
	}
	public void setQuaName(String quaName) {
		this.quaName = quaName;
	}
	public String getQuaAbbr() {
		return quaAbbr;
	}
	public void setQuaAbbr(String quaAbbr) {
		this.quaAbbr = quaAbbr;
	}
	public String getQualevel() {
		return qualevel;
	}
	public void setQualevel(String qualevel) {
		this.qualevel = qualevel;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public String getHdomainCode() {
		return hdomainCode;
	}
	public void setHdomainCode(String hdomainCode) {
		this.hdomainCode = hdomainCode;
	}
	public ArrayList getQualificationList() {
		return qualificationList;
	}
	public void setQualificationList(ArrayList qualificationList) {
		this.qualificationList = qualificationList;
	}
	public String getConfChk() {
		return confChk;
	}
	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
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
	public String getSelectname() {
		return selectname;
	}
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public boolean getOnLoadFlag() {
		return onLoadFlag;
	}
	public void setOnLoadFlag(boolean onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}
	public boolean getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(boolean saveFlag) {
		this.saveFlag = saveFlag;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	public boolean getPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}
	public boolean getDblFlag() {
		return dblFlag;
	}
	public void setDblFlag(boolean dblFlag) {
		this.dblFlag = dblFlag;
	}
	public boolean getLoadFlag() {
		return loadFlag;
	}
	public void setLoadFlag(boolean loadFlag) {
		this.loadFlag = loadFlag;
	}
	public boolean getAddFlag() {
		return addFlag;
	}
	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}
	public boolean getModFlag() {
		return modFlag;
	}
	public void setModFlag(boolean modFlag) {
		this.modFlag = modFlag;
	}
	public boolean getDbFlag() {
		return dbFlag;
	}
	public void setDbFlag(boolean dbFlag) {
		this.dbFlag = dbFlag;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public boolean isQualiView() {
		return qualiView;
	}
	public void setQualiView(boolean qualiView) {
		this.qualiView = qualiView;
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
	public String getHidLevel() {
		return hidLevel;
	}
	public void setHidLevel(String hidLevel) {
		this.hidLevel = hidLevel;
	}
	public String getHidIsactive() {
		return hidIsactive;
	}
	public void setHidIsactive(String hidIsactive) {
		this.hidIsactive = hidIsactive;
	}
	public String getIttSrN0() {
		return ittSrN0;
	}
	public void setIttSrN0(String ittSrN0) {
		this.ittSrN0 = ittSrN0;
	}
	public String getQualificationID() {
		return qualificationID;
	}
	public void setQualificationID(String qualificationID) {
		this.qualificationID = qualificationID;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	
}