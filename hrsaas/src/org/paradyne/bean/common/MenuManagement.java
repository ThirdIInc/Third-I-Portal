/**
 * 
 */
package org.paradyne.bean.common;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author debajanid
 *
 */
public class MenuManagement extends BeanBase {

	String menuID= "";
	String menuName = "";
	String menuValue = "";
	String menuLink = "";
	String target = "";
	String menuParntId = "";
	String menuParValue = "";
	String message = "";
	String placement = "";
	String order = "";
	String totalpath="";
	
	ArrayList<Object> menuList;
	
	public ArrayList<Object> getMenuList() {
		return menuList;
	}
	public void setMenuList(ArrayList<Object> menuList) {
		this.menuList = menuList;
	}
	public String getMenuID() {
		return menuID;
	}
	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuValue() {
		return menuValue;
	}
	public void setMenuValue(String menuValue) {
		this.menuValue = menuValue;
	}
	public String getMenuLink() {
		return menuLink;
	}
	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getMenuParntId() {
		return menuParntId;
	}
	public void setMenuParntId(String menuParntId) {
		this.menuParntId = menuParntId;
	}
	public String getMenuParValue() {
		return menuParValue;
	}
	public void setMenuParValue(String menuParValue) {
		this.menuParValue = menuParValue;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPlacement() {
		return placement;
	}
	public void setPlacement(String placement) {
		this.placement = placement;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getTotalpath() {
		return totalpath;
	}
	public void setTotalpath(String totalpath) {
		this.totalpath = totalpath;
	}
	
	
	
	
}



	