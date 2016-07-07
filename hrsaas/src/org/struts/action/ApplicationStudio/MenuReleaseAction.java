/**
 * 
 */
package org.struts.action.ApplicationStudio;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.ApplicationStudio.*;
import org.paradyne.model.ApplicationStudio.MenuReleaseModel;
import org.paradyne.model.admin.master.CadreModel;
import org.paradyne.model.common.ProfileModel;

/**
 * @author AA0563
 *
 */
public class MenuReleaseAction extends ParaActionSupport {
	MenuRelease menuRelease = null;

	

	/**
	 * @return the menuRelease
	 */
	public MenuRelease getMenuRelease() {
		return menuRelease;
	}



	/**
	 * @param menuRelease the menuRelease to set
	 */
	public void setMenuRelease(MenuRelease menuRelease) {
		this.menuRelease = menuRelease;
	}



	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		menuRelease=new MenuRelease();
		menuRelease.setMenuCode(735);

	}
	
	

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return menuRelease;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		MenuReleaseModel model = new MenuReleaseModel();

		model.initiate(context, session);

		String[][] menuName1 = model.getMenuList(menuRelease);
		request.setAttribute("menuName1", menuName1);
		menuRelease.setIsTotal("false");
		model.terminate();
	}
	public String getMenuReleaseItems()
	{
		String menuCode = String.valueOf(menuRelease.getSelMenuCode());
		
		MenuReleaseModel model = new MenuReleaseModel();
		model.initiate(context, session);
		String[][] menuItems = model.getMenuItems(menuCode, menuRelease);
		String[][] menuName1 = model.getMenuList(menuRelease);

		request.setAttribute("menuName1", menuName1);
		
			String[][] menuFlags = model.getProfileMenus(menuRelease);
			request.setAttribute("menuFlags", menuFlags);
		
		request.setAttribute("menuItems", menuItems);
		menuRelease.setIsTotal("true");
		model.terminate();
		
		return "success";
	}
	
	public String create() throws Exception {
		try {
			

			
			String insertFlg[] = null;
			
			try {
				insertFlg = (String[]) request.getParameterValues("insertChk");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				//logger.error("Exception in insert");
			}

			MenuReleaseModel model = new MenuReleaseModel();

			model.initiate(context, session);

			

			boolean isCreate = model.saveMenuFlags(menuRelease, insertFlg);
			
			if (isCreate) {
				addActionMessage("Profile Saved Successfully");
				return getMenuReleaseItems();
			} // end of if
			else {
				addActionMessage("Error in profile creation");
			}// end of else

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//logger.error("Exception in Create");
			return getMenuReleaseItems();
		}
		return getMenuReleaseItems();
	}

	
}
