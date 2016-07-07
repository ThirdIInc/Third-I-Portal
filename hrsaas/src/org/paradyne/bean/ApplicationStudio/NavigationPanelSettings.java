package org.paradyne.bean.ApplicationStudio;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author Bhushan Dasare
 * @date Dec 22, 2008
 */

public class NavigationPanelSettings extends BeanBase {
	private String setCode;
	private String tempCode;
	private String modCode;
	private String modName;
	private String formCode;
	private String formName;
	private boolean listButtons = false;
	private ArrayList<NavigationPanelSettings> btnList;
	private String btnCode;
	private String btnName;
	private String btnDescription;
	private String btnSelected;
	private String levelCode;
	private String modeSelected;
	private String imgFlag="true";
	private String svFlag = "false";
	
	public String getModeSelected() {
		return modeSelected;
	}
	public void setModeSelected(String modeSelected) {
		this.modeSelected = modeSelected;
	}
	public String getSetCode() {
		return setCode;
	}
	public void setSetCode(String setCode) {
		this.setCode = setCode;
	}
	public String getTempCode() {
		return tempCode;
	}
	public void setTempCode(String tempCode) {
		this.tempCode = tempCode;
	}
	public String getModCode() {
		return modCode;
	}
	public void setModCode(String modCode) {
		this.modCode = modCode;
	}
	public String getModName() {
		return modName;
	}
	public void setModName(String modName) {
		this.modName = modName;
	}
	public String getFormCode() {
		return formCode;
	}
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public boolean isListButtons() {
		return listButtons;
	}
	public void setListButtons(boolean listButtons) {
		this.listButtons = listButtons;
	}
	public ArrayList<NavigationPanelSettings> getBtnList() {
		return btnList;
	}
	public void setBtnList(ArrayList<NavigationPanelSettings> btnList) {
		this.btnList = btnList;
	}
	public String getBtnCode() {
		return btnCode;
	}
	public void setBtnCode(String btnCode) {
		this.btnCode = btnCode;
	}
	public String getBtnName() {
		return btnName;
	}
	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}
	public String getBtnDescription() {
		return btnDescription;
	}
	public void setBtnDescription(String btnDescription) {
		this.btnDescription = btnDescription;
	}
	public String getBtnSelected() {
		return btnSelected;
	}
	public void setBtnSelected(String btnSelected) {
		this.btnSelected = btnSelected;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getImgFlag() {
		return imgFlag;
	}
	public void setImgFlag(String imgFlag) {
		this.imgFlag = imgFlag;
	}
	/**
	 * @return the svFlag
	 */
	public String getSvFlag() {
		return svFlag;
	}
	/**
	 * @param svFlag the svFlag to set
	 */
	public void setSvFlag(String svFlag) {
		this.svFlag = svFlag;
	}
}