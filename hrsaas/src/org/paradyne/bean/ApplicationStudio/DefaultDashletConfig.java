/**
 * 
 */
package org.paradyne.bean.ApplicationStudio;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0623
 *
 */
public class DefaultDashletConfig extends BeanBase {
	/**
	 * Flags For Cancel Button
	 */
	
	private boolean loadFlag;
	private boolean addFlag;
	private boolean modFlag;
	private boolean dbFlag;
	
	/**
	 * Flags Required  for Default Dashlet Configuration
	 */
	
	private boolean onLoadFlag=false;
	private boolean saveFlag=false;
	private boolean flag=false;
	private boolean editFlag=false;
	private boolean pageFlag=false;
	private boolean dblFlag=false;
	
	/** Fields **/
	private String profileID = "";
	private String profileName = "";
	private String hiddenDivId = "";
	private boolean divFlag=false;
	private String gridRows = "";
	private String gridCols = "";
	private String widthCol1 = "";
	private String widthCol2 = "";
	private String colShowFlag= "";
	private String dashCode = "";
	private String dashName = "";
	private String frmId = "";
	private String hMenuCode = ""; 
	private String menuHome = "";
	private String saveChkFlag="";
	
	ArrayList list;
	
	/**
	 * @return the list
	 */
	public ArrayList getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList list) {
		this.list = list;
	}
	/**
	 * @return the colShowFlag
	 */
	public String getColShowFlag() {
		return colShowFlag;
	}
	/**
	 * @param colShowFlag the colShowFlag to set
	 */
	public void setColShowFlag(String colShowFlag) {
		this.colShowFlag = colShowFlag;
	}
	/**
	 * @return the gridRows
	 */
	public String getGridRows() {
		return gridRows;
	}
	/**
	 * @param gridRows the gridRows to set
	 */
	public void setGridRows(String gridRows) {
		this.gridRows = gridRows;
	}
	/**
	 * @return the gridCols
	 */
	public String getGridCols() {
		return gridCols;
	}
	/**
	 * @param gridCols the gridCols to set
	 */
	public void setGridCols(String gridCols) {
		this.gridCols = gridCols;
	}
	/**
	 * @return the widthCol1
	 */
	public String getWidthCol1() {
		return widthCol1;
	}
	/**
	 * @param widthCol1 the widthCol1 to set
	 */
	public void setWidthCol1(String widthCol1) {
		this.widthCol1 = widthCol1;
	}
	/**
	 * @return the widthCol2
	 */
	public String getWidthCol2() {
		return widthCol2;
	}
	/**
	 * @param widthCol2 the widthCol2 to set
	 */
	public void setWidthCol2(String widthCol2) {
		this.widthCol2 = widthCol2;
	}
	/**
	 * @return the hiddenDivId
	 */
	public String getHiddenDivId() {
		return hiddenDivId;
	}
	/**
	 * @param hiddenDivId the hiddenDivId to set
	 */
	public void setHiddenDivId(String hiddenDivId) {
		this.hiddenDivId = hiddenDivId;
	}
	/**
	 * @return the loadFlag
	 */
	public boolean isLoadFlag() {
		return loadFlag;
	}
	/**
	 * @param loadFlag the loadFlag to set
	 */
	public void setLoadFlag(boolean loadFlag) {
		this.loadFlag = loadFlag;
	}
	/**
	 * @return the addFlag
	 */
	public boolean isAddFlag() {
		return addFlag;
	}
	/**
	 * @param addFlag the addFlag to set
	 */
	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}
	/**
	 * @return the modFlag
	 */
	public boolean isModFlag() {
		return modFlag;
	}
	/**
	 * @param modFlag the modFlag to set
	 */
	public void setModFlag(boolean modFlag) {
		this.modFlag = modFlag;
	}
	/**
	 * @return the dbFlag
	 */
	public boolean isDbFlag() {
		return dbFlag;
	}
	/**
	 * @param dbFlag the dbFlag to set
	 */
	public void setDbFlag(boolean dbFlag) {
		this.dbFlag = dbFlag;
	}
	/**
	 * @return the onLoadFlag
	 */
	public boolean isOnLoadFlag() {
		return onLoadFlag;
	}
	/**
	 * @param onLoadFlag the onLoadFlag to set
	 */
	public void setOnLoadFlag(boolean onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}
	/**
	 * @return the saveFlag
	 */
	public boolean isSaveFlag() {
		return saveFlag;
	}
	/**
	 * @param saveFlag the saveFlag to set
	 */
	public void setSaveFlag(boolean saveFlag) {
		this.saveFlag = saveFlag;
	}
	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	/**
	 * @return the editFlag
	 */
	public boolean isEditFlag() {
		return editFlag;
	}
	/**
	 * @param editFlag the editFlag to set
	 */
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	/**
	 * @return the pageFlag
	 */
	public boolean isPageFlag() {
		return pageFlag;
	}
	/**
	 * @param pageFlag the pageFlag to set
	 */
	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}
	/**
	 * @return the dblFlag
	 */
	public boolean isDblFlag() {
		return dblFlag;
	}
	/**
	 * @param dblFlag the dblFlag to set
	 */
	public void setDblFlag(boolean dblFlag) {
		this.dblFlag = dblFlag;
	}
	/**
	 * @return the profileID
	 */
	public String getProfileID() {
		return profileID;
	}
	/**
	 * @param profileID the profileID to set
	 */
	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}
	/**
	 * @return the profileName
	 */
	public String getProfileName() {
		return profileName;
	}
	/**
	 * @param profileName the profileName to set
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	/**
	 * @return the divFlag
	 */
	public boolean isDivFlag() {
		return divFlag;
	}
	/**
	 * @param divFlag the divFlag to set
	 */
	public void setDivFlag(boolean divFlag) {
		this.divFlag = divFlag;
	}
	/**
	 * @return the dashCode
	 */
	public String getDashCode() {
		return dashCode;
	}
	/**
	 * @param dashCode the dashCode to set
	 */
	public void setDashCode(String dashCode) {
		this.dashCode = dashCode;
	}
	/**
	 * @return the dashName
	 */
	public String getDashName() {
		return dashName;
	}
	/**
	 * @param dashName the dashName to set
	 */
	public void setDashName(String dashName) {
		this.dashName = dashName;
	}
	/**
	 * @return the frmId
	 */
	public String getFrmId() {
		return frmId;
	}
	/**
	 * @param frmId the frmId to set
	 */
	public void setFrmId(String frmId) {
		this.frmId = frmId;
	}
	public String getHMenuCode() {
		return hMenuCode;
	}
	public void setHMenuCode(String menuCode) {
		hMenuCode = menuCode;
	}
	public String getMenuHome() {
		return menuHome;
	}
	public void setMenuHome(String menuHome) {
		this.menuHome = menuHome;
	}
	public String getSaveChkFlag() {
		return saveChkFlag;
	}
	public void setSaveChkFlag(String saveChkFlag) {
		this.saveChkFlag = saveChkFlag;
	}
	
	

}
