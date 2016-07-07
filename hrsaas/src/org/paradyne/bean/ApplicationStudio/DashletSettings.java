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
public class DashletSettings extends BeanBase {
	
	/**
	 * Flags For Cancel Button
	 */
	private String dashletImageName ="";
	private String dashletImageNameItt ="";
	private boolean loadFlag;
	private boolean addFlag;
	private boolean modFlag;
	private boolean dbFlag;
	
	/** Fields **/
	private String  dashletId ="";
	private String  name="";
	private String link = "";
	private String position = "";
	private String type= "";
	
	/**
	 * Flags Required  for Dashlet Settings
	 */
	
	private boolean onLoadFlag=false;
	private boolean saveFlag=false;
	private boolean flag=false;
	private boolean editFlag=false;
	private boolean pageFlag=false;
	private boolean dblFlag=false;
	
	
	private ArrayList typeList=null;
	private String confChk;
	private String hdeleteCode;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddencode;
	
	
	/**
	 * @return the typeList
	 */
	public ArrayList getTypeList() {
		return typeList;
	}
	/**
	 * @param typeList the typeList to set
	 */
	public void setTypeList(ArrayList typeList) {
		this.typeList = typeList;
	}
	/**
	 * @return the confChk
	 */
	public String getConfChk() {
		return confChk;
	}
	/**
	 * @param confChk the confChk to set
	 */
	public void setConfChk(String confChk) {
		this.confChk = confChk;
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
	 * @return the selectname
	 */
	public String getSelectname() {
		return selectname;
	}
	/**
	 * @param selectname the selectname to set
	 */
	public void setSelectname(String selectname) {
		this.selectname = selectname;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the dashletId
	 */
	public String getDashletId() {
		return dashletId;
	}
	/**
	 * @param dashletId the dashletId to set
	 */
	public void setDashletId(String dashletId) {
		this.dashletId = dashletId;
	}
	public String getDashletImageName() {
		return dashletImageName;
	}
	public void setDashletImageName(String dashletImageName) {
		this.dashletImageName = dashletImageName;
	}
	public String getDashletImageNameItt() {
		return dashletImageNameItt;
	}
	public void setDashletImageNameItt(String dashletImageNameItt) {
		this.dashletImageNameItt = dashletImageNameItt;
	}

}
