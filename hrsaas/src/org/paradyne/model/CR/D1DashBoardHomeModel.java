package org.paradyne.model.CR;

import java.util.HashMap;
import org.paradyne.lib.Utility;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.CR.D1DashBoardHome;
import org.paradyne.bean.CR.DashBoard;
import org.paradyne.lib.ModelBase;

/**@purpose : Create Home page for DashBoard Module
 * @author AA1711
 * @Date : 21-Jan-2013
 */
public class D1DashBoardHomeModel extends ModelBase{

	/**Method Name :getD1DashPortalLink()
	 * @purpose Used to display Left Menu
	 * @param request
	 * @return String [][]
	 */
	public String[][] getD1DashPortalLink(HttpServletRequest request, D1DashBoardHome dashBean) {
		String[][] strObj=null;
		try {
			String divQuery="SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="+dashBean.getUserEmpId();			
			Object [][] empDivObj= getSqlModel().getSingleResult(divQuery);
			String query = "SELECT LINK_ID,LINK_NAME,LINK_IMAGE, LINK_URL FROM HRMS_PORTAL_APPS "
							+ " WHERE IS_ACTIVE='Y' AND"
							+ " (','||LINK_FOR_DIVISION||',' LIKE '%,"+empDivObj[0][0]+",%' OR LINK_FOR_DIVISION IS NULL)"
							+ " ORDER BY LINK_SEQUENCE";
			Object[][] d1LinkObj = getSqlModel().getSingleResult(query);
			if (d1LinkObj != null && d1LinkObj.length > 0) { //Check for null
				strObj = new String[d1LinkObj.length][4];
				for (int i = 0; i < d1LinkObj.length; i++) {
					strObj[i][0] = String.valueOf(d1LinkObj[i][0]); //APPL_CODE
					strObj[i][1] = String.valueOf(d1LinkObj[i][1]); //APPL_NAEM
					strObj[i][2] = String.valueOf(d1LinkObj[i][2]); //APPL_IMAGE
					strObj[i][3] = String.valueOf(d1LinkObj[i][3]); //APPL_URL
				}
			}//End if
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return strObj;
	}

	/**Method Name :getD1DashMenuList()
	 * @purpose Used to display Menus
	 * @param request
	 * @param dashBean
	 * @param menuType
	 */
	public void getD1DashMenuList(HttpServletRequest request,
			D1DashBoardHome dashBean, String menuType) {
		  try {			 
			    if (menuType == null) {
					menuType = "MN";
				}
				String d1AnalyticMenuQuery = " SELECT * FROM HRMS_MENU" 
											+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND "
											+ "( PROFILE_INSERT_FLAG='Y'" 
											+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y'" 
											+ " OR PROFILE_GENERAL_FLAG ='Y'))"
											+ " LEFT JOIN HRMS_MENU_CLIENT ON(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
											+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE ="+dashBean.getUserProfileId()
											+ " AND MENU_ISRELEASED='Y' AND MENU_TYPE='"+menuType+"' "
											+ " AND MENU_ISHOME NOT LIKE 'Y'"
											+ " AND (HRMS_MENU.MENU_TYPE NOT IN ('RT','ES'))";
				Object menuDataObj[][] = getSqlModel().getSingleResult(d1AnalyticMenuQuery);
				if(menuDataObj!= null && menuDataObj.length >0){
					 HashMap <String, String> dashMenuMap= new HashMap <String, String>(menuDataObj.length);
					for(int i=0;i< menuDataObj.length;i++){
						dashMenuMap.put(String.valueOf(menuDataObj[i][2]), String.valueOf(menuDataObj[i][1]));						
					}
				   dashBean.setDashMenuDropMap(dashMenuMap);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		

		}

}
