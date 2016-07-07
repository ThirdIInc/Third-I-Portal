package org.paradyne.model.CR;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.CR.CustomerAccountInfo;
import org.paradyne.bean.CR.D1AnalyticHome;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;

/**@purpose : Create Home page for Analytics Module
 * @author AA1711
 * @Date : 17-Jan-2013
 */
public class D1AnalyticHomeModel extends ModelBase {

	/**Method Name: getD1MenuList()
	 * @purpose Used to display Menus
 	 * @param request
	 * @param home
	 * @param menuType
	 */
	public void getD1MenuList(HttpServletRequest request, D1AnalyticHome home,
			String menuType,HttpServletResponse response) {
	  try {
		    if (menuType == null) {
				menuType = "MN";
			}
			/*String d1AnalyticMenuQuery = " SELECT HRMS_MENU.MENU_CODE,HRMS_MENU.MENU_NAME,MENU_LINK,MENU_IMAGE,MENU_KEYWORDS ,MENU_PARENT_CODE,MENU_IMAGE"
										+ " FROM HRMS_MENU" 
										+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND "
										+ "( PROFILE_INSERT_FLAG='Y'" 
										+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y'" 
										+ " OR PROFILE_GENERAL_FLAG ='Y'))"
										+ " LEFT JOIN HRMS_MENU_CLIENT ON(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
										+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE ="+home.getUserProfileId()
										+ " AND MENU_ISRELEASED='Y' AND MENU_TYPE='"+menuType+"' "
										+ " AND MENU_ISHOME NOT LIKE 'Y'"
<<<<<<< D1AnalyticHomeModel.java
										+ " AND (HRMS_MENU.MENU_TYPE NOT IN ('RT','ES'))";*/
		    
		    HttpSession session = request.getSession();
		    String userEmpID=(String) session.getAttribute("customerUserEmpIdSession");
		    String userType=(String) session.getAttribute("userType");
		    
		    String d1AnalyticMenuQuery = "SELECT DISTINCT DASHBOARD_ID FROM CR_EMP_CLIENT_MAPP WHERE USER_ID="+userEmpID+" AND USER_TYPE='"+userType+"'";
			Object menuDataObjs[][] = getSqlModel().getSingleResult(d1AnalyticMenuQuery);
			String dashboardName="";
			for (int i = 0; i < menuDataObjs.length; i++) {
				dashboardName+=menuDataObjs[i][0]+",";
				
			}
			if(!dashboardName.equals("")){
			dashboardName = dashboardName.substring(0, dashboardName.length()-1);
			}
			 
			 
			String d1AnalyticDashboardName = "SELECT DASHBOARD_ID,DASHBOARD_CAPTION,IS_ACCOUNTAPPLICABLE,MENU_LINK,MENU_IMAGE FROM CR_DASHBOARD WHERE DASHBOARD_ID IN ("+dashboardName+")";
			Object menuDataObj[][] = getSqlModel().getSingleResult(d1AnalyticDashboardName);
			
			String noOfDashboards="";
			int noOfDashboard =0;
			if(userType.equals("E")){
				noOfDashboards = (String)session.getAttribute("noOfDashboard");
				noOfDashboard=Integer.parseInt(noOfDashboards);
			}

			request.setAttribute("d1MenuData", menuDataObj);
			request.setAttribute("userType", userType);
			request.setAttribute("noOfDashboard", noOfDashboard);
			
			if (userType.equals("E") && noOfDashboard==1){
				String contextPath = request.getContextPath();
				String action = (String.valueOf(menuDataObj[0][3]));
				String dashBoardId = "?dashBoardID="+(String.valueOf(menuDataObj[0][0]));;
				
				String redirectLink = contextPath+action+dashBoardId;
				response.sendRedirect(redirectLink);
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
 
	}

	/**Method Name : getD1PortalLink()
	 * @purpose Used to display Left Menu
	 * @param request
	 * @return String [][]
	 */
	public String[][] getD1PortalLink(HttpServletRequest request,D1AnalyticHome d1Home) {
		String[][] strObj=null;
		try {
			
			 
			String divQuery="SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="+d1Home.getUserEmpId();
			
			Object [][] empDivObj= getSqlModel().getSingleResult(divQuery);
			String query = "SELECT LINK_ID,LINK_NAME,LINK_IMAGE, LINK_URL FROM HRMS_PORTAL_APPS "
							+ " WHERE IS_ACTIVE='Y' AND"
							+ " (','||LINK_FOR_DIVISION||',' LIKE '%,"+empDivObj[0][0]+",%' OR LINK_FOR_DIVISION IS NULL)"
							+ " ORDER BY LINK_SEQUENCE";
			Object[][] d1LinkObj = getSqlModel().getSingleResult(query);
			if (d1LinkObj != null && d1LinkObj.length > 0) {

				strObj = new String[d1LinkObj.length][4];

				for (int i = 0; i < d1LinkObj.length; i++) {
					strObj[i][0] = String.valueOf(d1LinkObj[i][0]);
					strObj[i][1] = String.valueOf(d1LinkObj[i][1]);
					strObj[i][2] = String.valueOf(d1LinkObj[i][2]);
					strObj[i][3] = String.valueOf(d1LinkObj[i][3]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return strObj;
	}
	
	
	/**
	 * Purpose : This method is used to change password
	 * @param bean
	 * @return boolean
	 */
	public boolean changePassWord(D1AnalyticHome bean) {
		// TODO Auto-generated method stub
		boolean result =false;
		String customerCode = (String)session.getAttribute("customerUserEmpIdSession");
		//String customerCode=bean.getCustomerUserEmpId();
		String password=bean.getCustomerNewPassword();
		String oldPassword=bean.getCustomerOldPassword();
		String emailId= bean.getChatLogin();
		String encryptPwd="";
		String oldEncryptPwd="";
		try {
			encryptPwd = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(password
					.trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			oldEncryptPwd = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(oldPassword
					.trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String selectQuery="SELECT CRUSER_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE=? AND PASSWORD=?";
			Object[]data=new Object[2];
			data[0]=customerCode;
			data[1]=oldEncryptPwd;
			
		Object[][]passwordObj=getSqlModel().getSingleResult(selectQuery,data);
		if(passwordObj!=null && passwordObj.length>0){
			String updateQuery="UPDATE CR_CLIENT_USERS SET PASSWORD=? WHERE EMAIL_ID=?";
			Object[][]obj=new Object[1][2];
			obj[0][0]=encryptPwd;
			obj[0][1]=emailId;
			result=getSqlModel().singleExecute(updateQuery,obj);
		}else{
			return false;
		}		
		return result;
	}//end of method
}
