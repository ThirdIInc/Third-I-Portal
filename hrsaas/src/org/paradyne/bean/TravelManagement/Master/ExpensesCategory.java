/**
 * 
 */
package org.paradyne.bean.TravelManagement.Master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0651
 *
 */
public class ExpensesCategory extends BeanBase {
	private String  srNo="";
	private String expenseId= "";
	private String expenseName = "";
	private String expenseUnit = "";
	private String description;	
	private String status;
	
	private String hdomainCode;
	
	private ArrayList expenseList=null;
	private String expenseId1= "";
	private String confChk;
	private String hdeleteCode;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddencode;
	
	/**
	 * Flags Required  for Expense Category
	 */
	
	private boolean onLoadFlag=false;
	private boolean saveFlag=false;
	private boolean flag=false;
	private boolean editFlag=false;
	private boolean dblFlag=false;
	
	private String pageFlag="false";
	
	/**
	 * Flags For Cancel Button
	 */
	
	private boolean loadFlag;
	private boolean addFlag;
	private boolean modFlag;
	private boolean dbFlag;
	
	private String descCnt;
	
	/**
	 * Flags Required  for Travel Purpose
	 */
	
	
	private boolean editModeFlag=true;
	
	private String cancelFlg="false";
	
	private String panelFlag="";
	private String retrnFlag="";
	
	

	
	public String getDescCnt() {
		return descCnt;
	}
	public String getPanelFlag() {
		return panelFlag;
	}
	public void setPanelFlag(String panelFlag) {
		this.panelFlag = panelFlag;
	}
	public String getRetrnFlag() {
		return retrnFlag;
	}
	public void setRetrnFlag(String retrnFlag) {
		this.retrnFlag = retrnFlag;
	}
	public void setDescCnt(String descCnt) {
		this.descCnt = descCnt;
	}
	public boolean isEditModeFlag() {
		return editModeFlag;
	}
	public void setEditModeFlag(boolean editModeFlag) {
		this.editModeFlag = editModeFlag;
	}
	public String getCancelFlg() {
		return cancelFlg;
	}
	public void setCancelFlg(String cancelFlg) {
		this.cancelFlg = cancelFlg;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}
	public String getExpenseName() {
		return expenseName;
	}
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}
	public String getExpenseUnit() {
		return expenseUnit;
	}
	public void setExpenseUnit(String expenseUnit) {
		this.expenseUnit = expenseUnit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHdomainCode() {
		return hdomainCode;
	}
	public void setHdomainCode(String hdomainCode) {
		this.hdomainCode = hdomainCode;
	}
	
	public ArrayList getExpenseList() {
		return expenseList;
	}
	public void setExpenseList(ArrayList expenseList) {
		this.expenseList = expenseList;
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
	public boolean isOnLoadFlag() {
		return onLoadFlag;
	}
	public void setOnLoadFlag(boolean onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}
	public boolean isSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(boolean saveFlag) {
		this.saveFlag = saveFlag;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isEditFlag() {
		return editFlag;
	}
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	
	public String getPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}
	public boolean isDblFlag() {
		return dblFlag;
	}
	public void setDblFlag(boolean dblFlag) {
		this.dblFlag = dblFlag;
	}
	public boolean isLoadFlag() {
		return loadFlag;
	}
	public void setLoadFlag(boolean loadFlag) {
		this.loadFlag = loadFlag;
	}
	public boolean isAddFlag() {
		return addFlag;
	}
	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}
	public boolean isModFlag() {
		return modFlag;
	}
	public void setModFlag(boolean modFlag) {
		this.modFlag = modFlag;
	}
	public boolean isDbFlag() {
		return dbFlag;
	}
	public void setDbFlag(boolean dbFlag) {
		this.dbFlag = dbFlag;
	}
	public String getExpenseId1() {
		return expenseId1;
	}
	public void setExpenseId1(String expenseId1) {
		this.expenseId1 = expenseId1;
	}

}
