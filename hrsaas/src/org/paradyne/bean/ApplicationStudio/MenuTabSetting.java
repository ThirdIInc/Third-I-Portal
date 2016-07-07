/**
 * 
 */
package org.paradyne.bean.ApplicationStudio;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0563
 *
 */
public class MenuTabSetting extends BeanBase {
	private String  menuName="";
	private int  menuCode;
	private String deleteOp="";
	private String editmenu="";
	private String orgmenuname="";
	private String editmenucode="";
	ArrayList menusetlist;
	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}
	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * @return the menuCode
	 */
	/**
	 * @return the menuCode
	 */
	public int getMenuCode() {
		return menuCode;
	}
	/**
	 * @param de the menuCode to set
	 */
	/**
	 * @return the menusetlist
	 */
	public ArrayList getMenusetlist() {
		return menusetlist;
	}
	/**
	 * @param menusetlist the menusetlist to set
	 */
	public void setMenusetlist(ArrayList menusetlist) {
		this.menusetlist = menusetlist;
	}
	/**
	 * @param menuCode the menuCode to set
	 */
	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}
	/**
	 * @return the editmenu
	 */
	public String getEditmenu() {
		return editmenu;
	}
	/**
	 * @param editmenu the editmenu to set
	 */
	public void setEditmenu(String editmenu) {
		this.editmenu = editmenu;
	}
	/**
	 * @return the editmenucode
	 */
	public String getEditmenucode() {
		return editmenucode;
	}
	/**
	 * @param editmenucode the editmenucode to set
	 */
	public void setEditmenucode(String editmenucode) {
		this.editmenucode = editmenucode;
	}
	public String getDeleteOp() {
		return deleteOp;
	}
	public void setDeleteOp(String deleteOp) {
		this.deleteOp = deleteOp;
	}
	public String getOrgmenuname() {
		return orgmenuname;
	}
	public void setOrgmenuname(String orgmenuname) {
		this.orgmenuname = orgmenuname;
	}
	
}