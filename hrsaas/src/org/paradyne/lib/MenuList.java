/**
 * 
 */
package org.paradyne.lib;



import java.util.LinkedList;

public class MenuList {

	Object[][] rawObj;
	public MenuList() {
	}

	public MenuList(Object[][] obj) {
		this.rawObj = obj;
	}

	public Object getMenuList() {
		System.out.println(" Inside the get menu list");
		Menu mainMenu = new Menu();
		try{
			LinkedList baseVector = getBaseMenu();
			for(int i = 0 ; i < baseVector.size() ; i++) {
				Menu menu = (Menu)baseVector.get(i);
				createTreeObj(menu);
			}
			mainMenu.setMenuId(0);
			mainMenu.setMenuDesc("Menu");
			mainMenu.setMenuClass("");
			mainMenu.setParentId(0);
			mainMenu.setMnemonic("M");
			mainMenu.setInsertFlag("N");
			mainMenu.setUpdateFlag("N");
			mainMenu.setDeleteFlag("N");
			mainMenu.setSelectFlag("N");
			mainMenu.setShowAllFlag("N");
			mainMenu.setChild(baseVector);
		} catch(Exception e) {
			System.out.println("error while creating menu  :"+e);
			e.printStackTrace();
		}
		return mainMenu;
	}

	public void createTreeObj(Menu menu) {
//		System.out.println("create tree count ");
		LinkedList child = getChildren(menu);
		menu.setChild(child);
			for (int z = 0; z < child.size(); z++) {
				createTreeObj((Menu)child.get(z));
			}
	}

	public LinkedList getChildren(Menu parentMenu) {
//		System.out.println("get children :");
		LinkedList llist = new LinkedList();
		try {
			for (int i = 0; i < rawObj.length; i++) {
				//System.out.println("parentMenu.getMenuId()"+parentMenu.getMenuId());
				if ((rawObj[i][3].toString()).equals(String.valueOf(parentMenu
						.getMenuId()))) { //check parent form code

					Menu menu = new Menu();
					menu.setMenuId(Integer.parseInt(rawObj[i][0].toString())); //form code
					menu.setMenuDesc(rawObj[i][1].toString()); //form desc
					menu.setMenuClass(rawObj[i][2].toString()); // from name is class name
					menu.setParentId(Integer.parseInt(String
							.valueOf(rawObj[i][3]))); // parent menu form code
					menu.setParent(parentMenu);
					menu.setMnemonic(rawObj[i][4].toString()); //Mnemonic
					menu.setInsertFlag(rawObj[i][5].toString()); //Falg for insert
					menu.setUpdateFlag(rawObj[i][6].toString()); //Flag for udate
					menu.setDeleteFlag(rawObj[i][7].toString()); // Flag for Delete
					menu.setSelectFlag(rawObj[i][8].toString()); //Flag for View
					menu.setShowAllFlag(rawObj[i][9].toString());//Flag for General 
					menu.setLevel(rawObj[i][10].toString());
					llist.add(menu);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error while getChildren  :"+e);
			e.printStackTrace();
		}
		return llist;
	}

	public LinkedList getBaseMenu() {
		LinkedList baseMenu = new LinkedList();
		try {
			for (int i = 0; i < rawObj.length; i++) {
				if (String.valueOf(rawObj[i][3]).equals("0")) { //base menu
					Menu menu = new Menu();
					menu.setMenuId(Integer.parseInt(String
							.valueOf(rawObj[i][0]))); //formcode
					menu.setMenuDesc(String.valueOf(rawObj[i][1])); //client
					menu.setMenuClass(String.valueOf(rawObj[i][2])); //original
					menu.setParentId(0); //parentformcode
					menu.setMnemonic(String.valueOf(rawObj[i][4])); //Mnemonic
					menu.setInsertFlag(String.valueOf(rawObj[i][5])); //Flags for identifying selection
					menu.setUpdateFlag(String.valueOf(rawObj[i][6]));
					menu.setDeleteFlag(String.valueOf(rawObj[i][7]));
					menu.setSelectFlag(String.valueOf(rawObj[i][8]));
					menu.setShowAllFlag(String.valueOf(rawObj[i][9]));
					menu.setLevel(String.valueOf(rawObj[i][9]));
					baseMenu.add(menu);
				}
			}
			System.out.println("BAse menu :" + baseMenu.size());
		} catch (Exception e) {
			System.out.println("Erron in getBaseMenu  "+e);
			e.printStackTrace();
		}
		return baseMenu;
	}

	int menuCount = 0;
	public int getMenuCount(Menu sampleMenu) {
		LinkedList list = sampleMenu.getChild();
		for(int i =0 ; i< list.size(); i++) {
			menuCount++;
			Menu childMenu = (Menu)list.get(i);
			getMenuCount(childMenu);
			System.out.println("menu count:" +menuCount);
		}
		System.out.println("menu count:" +menuCount);
		return menuCount;
	}
}