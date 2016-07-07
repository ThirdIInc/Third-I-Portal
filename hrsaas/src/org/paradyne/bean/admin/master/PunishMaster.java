/**
 * 
 */
package org.paradyne.bean.admin.master;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author riteshr
 *
 */
public class PunishMaster extends BeanBase implements Serializable {
	public String modeLength="false";
	public String totalRecords="";
	public String punishName="";
	public String punishId="";
	public String fImplication="";
	public String isMajor ="";
	
	private String myPage;
	private String show;
	private String  hiddencode;
	private ArrayList punishList = null;
	private String hdeleteCode;
	private String selectname="";
	private String isCredit="";
	private String creditChk="";
	private String creditCode="";
	private String creditHead="";
	private String creditPercent="";
	private ArrayList creditList=null;
	private boolean flag=false;
	ArrayList punishmentList ;
	
	public ArrayList getPunishmentList() {
		return punishmentList;
	}
	public void setPunishmentList(ArrayList punishmentList) {
		this.punishmentList = punishmentList;
	}
	public String getFImplication() {
		return fImplication;
	}
	public void setFImplication(String implication) {
		fImplication = implication;
	}
	public String getIsMajor() {
		return isMajor;
	}
	public void setIsMajor(String isMajor) {
		this.isMajor = isMajor;
	}
	public String getPunishId() {
		return punishId;
	}
	public void setPunishId(String punishId) {
		this.punishId = punishId;
	}
	public ArrayList getPunishList() {
		return punishList;
	}
	public void setPunishList(ArrayList punishList) {
		this.punishList = punishList;
	}
	public String getPunishName() {
		return punishName;
	}
	public void setPunishName(String punishName) {
		this.punishName = punishName;
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
	public String getSelectname() {
		return selectname;
	}
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}
	public String getIsCredit() {
		return isCredit;
	}
	public void setIsCredit(String isCredit) {
		this.isCredit = isCredit;
	}
	public String getCreditChk() {
		return creditChk;
	}
	public void setCreditChk(String creditChk) {
		this.creditChk = creditChk;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getCreditHead() {
		return creditHead;
	}
	public void setCreditHead(String creditHead) {
		this.creditHead = creditHead;
	}
	public String getCreditPercent() {
		return creditPercent;
	}
	public void setCreditPercent(String creditPercent) {
		this.creditPercent = creditPercent;
	}
	public ArrayList getCreditList() {
		return creditList;
	}
	public void setCreditList(ArrayList creditList) {
		this.creditList = creditList;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
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
	
	
}
