/**
 * 
 */
package org.paradyne.lib;


import java.util.LinkedList;

public class Menu implements java.io.Serializable {
	private int menuId;
	private int parentId;
	private Menu parent;
	private String menuDesc;
	private String menuClass;
	private String mnemonic;
	public boolean insertFlag;
	public boolean updateFlag;
	public boolean deleteFlag;
	public boolean selectFlag;
	public boolean showAllFlag;
	public String level;
	
	private LinkedList child;

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getMenuId() {
		return menuId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getParentId() {
		return parentId;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public Menu getParent() {
		return parent;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}
	public String getMenuClass() {
		return menuClass;
	}

	public void setMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}
	public String getMnemonic() {
		return mnemonic;
	}

	public void setInsertFlag(String Flag) {
		if(Flag.equals("Y")) this.insertFlag = true;
		else this.insertFlag = false;
	}
	public boolean getInsertFlag() {
		return insertFlag;
	}

	public void setUpdateFlag(String Flag) {
		if(Flag.equals("Y")) this.updateFlag = true;
		else this.updateFlag= false;
	}
	public boolean getUpdateFlag() {
		return updateFlag;
	}

	public void setDeleteFlag(String Flag) {
		if(Flag.equals("Y")) this.deleteFlag = true;
		else this.deleteFlag= false;
	}
	public boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setSelectFlag(String Flag) {
		if(Flag.equals("Y")) this.selectFlag = true;
		else this.selectFlag= false;
	}
	public boolean getSelectFlag() {
		return selectFlag;
	}

	public void setShowAllFlag(String Flag) {
		if(Flag.equals("Y")) this.showAllFlag= true;
		else this.showAllFlag= false;
	}
	public boolean getShowAllFlag() {
		return showAllFlag;
	}

	public void setChild(LinkedList child) {
		this.child = child;
	}
	public LinkedList getChild() {
		return child;
	}

	public int getMenuCount() {
		if(child != null) return child.size();
		else return 0;
	}

	public String toString() {
		return menuDesc;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
}