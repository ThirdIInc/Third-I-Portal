package org.paradyne.bean.ApplicationStudio;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class ButtonMaster extends BeanBase {
	
	private String btnCode="";
	private String btnName="";
	private String btnOrder="";
	private String btnFlag="";
	private String enableField="";
	
	
	private String chkBtn="";
	private String checkMe="";
	private String btnSelected="";
	private String buttonCode="";
	private String buttonOrder="";
	private String buttonFlag="";
	private String buttonName;
	private String confChk="";
	private String enabledisable="";    
	private ArrayList buttonList;
	
	private String hiddenEdit="";
	TreeMap map;
	public TreeMap getMap() {
		return map;
	}
	public void setMap(TreeMap map) {
		this.map = map;
	}
	public String getBtnSelected() {
		return btnSelected;
	}
	public void setBtnSelected(String btnSelected) {
		this.btnSelected = btnSelected;
	}
	public String getBtnCode() {
		return btnCode;
	}
	public void setBtnCode(String btnCode) {
		this.btnCode = btnCode;
	}
	public String getChkBtn() {
		return chkBtn;
	}
	public void setChkBtn(String chkBtn) {
		this.chkBtn = chkBtn;
	}
	public String getCheckMe() {
		return checkMe;
	}
	public void setCheckMe(String checkMe) {
		this.checkMe = checkMe;
	}
	public String getButtonCode() {
		return buttonCode;
	}
	public void setButtonCode(String buttonCode) {
		this.buttonCode = buttonCode;
	}
	public String getButtonOrder() {
		return buttonOrder;
	}
	public void setButtonOrder(String buttonOrder) {
		this.buttonOrder = buttonOrder;
	}
	public String getButtonFlag() {
		return buttonFlag;
	}
	public void setButtonFlag(String buttonFlag) {
		this.buttonFlag = buttonFlag;
	}
	public String getButtonName() {
		return buttonName;
	}
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	public ArrayList getButtonList() {
		return buttonList;
	}
	public void setButtonList(ArrayList buttonList) {
		this.buttonList = buttonList;
	}
	public String getConfChk() {
		return confChk;
	}
	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}
	public String getEnabledisable() {
		return enabledisable;
	}
	public void setEnabledisable(String enabledisable) {
		this.enabledisable = enabledisable;
	}
	public String getBtnName() {
		return btnName;
	}
	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}
	public String getBtnOrder() {
		return btnOrder;
	}
	public void setBtnOrder(String btnOrder) {
		this.btnOrder = btnOrder;
	}
	public String getBtnFlag() {
		return btnFlag;
	}
	public void setBtnFlag(String btnFlag) {
		this.btnFlag = btnFlag;
	}
	public String getEnableField() {
		return enableField;
	}
	public void setEnableField(String enableField) {
		this.enableField = enableField;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}

}
