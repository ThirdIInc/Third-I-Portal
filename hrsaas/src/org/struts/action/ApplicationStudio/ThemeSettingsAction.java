/**
 * Date:- 25/12/2008 
 */
package org.struts.action.ApplicationStudio;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import org.struts.action.common.EmployeePortalAction;
import org.struts.action.common.MenuAction;
import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.ApplicationStudio.ThemeSettings;
import org.paradyne.bean.common.LoginBean;

import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.ApplicationStudio.ThemeSettingsModel;
import org.paradyne.model.common.LoginModel;
import org.paradyne.model.common.MenuModel;
/**
 * @author Pankaj_Jain 
 * Class defined to apply theme, fonts and font size on application as 
 * selected by the user
 */
public class ThemeSettingsAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ThemeSettingsAction.class);
	ThemeSettings themeSettings = null;
	String poolDir = "";
	String fileName = "";
	public void prepare_local() throws Exception {
		themeSettings = new ThemeSettings();
		themeSettings.setMenuCode(738);
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return themeSettings;
	}
	
	/**
	 * method gets call on form loading
	 * and sets the value according and as selected by the user
	 * if user has not selected default values are assigned
	 */
	public String input()
	{
		System.out.println("input........");
		ThemeSettingsModel model = new ThemeSettingsModel();
		model.initiate(context, session);
		Object [][]obj=model.getTheme(themeSettings);
		
		/*
		 * if user has selected the theme previously
		 * all the values are displayed else
		 * default values are displayed 
		 */
		
		if(obj!=null && obj.length >0){
			System.out.println("String.valueOf(obj[0][0]"+String.valueOf(obj[0][0]));
			themeSettings.setMyTheme(String.valueOf(obj[0][0]));
			themeSettings.setFontName(String.valueOf(obj[0][1]));
			themeSettings.setFontSize(String.valueOf(obj[0][2]));
		}
		else{
			themeSettings.setMyTheme("peoplePower");
			themeSettings.setFontName("Arial");
			//themeSettings.setFontSize(String.valueOf(obj[0][2]));
		}
		model.terminate();
		
		return INPUT;
	} // end of method
	
	/**
	 * This method gets call when user applies settings
	 * on theme settings form
	 * @return
	 */
	public String applyTheme()
	{
		ThemeSettingsModel model = new ThemeSettingsModel();
		model.initiate(context, session);
		String str="apply";
		// model method call to save the changes
		model.applyTheme(themeSettings, fileName);
		Object [][]theme=model.getTheme(themeSettings);
		if (theme != null && theme.length >0) {
			logger.info("in if    themeSettings");
			session.setAttribute("themeName", String.valueOf(theme[0][0]));
			session.setAttribute("fontName", String.valueOf(theme[0][1]));
			session.setAttribute("fontSize", String.valueOf(theme[0][2]));
			session.setAttribute("homePage", String.valueOf(theme[0][3]));
			request.setAttribute("homeCode", String.valueOf(theme[0][4]));
			}
			else{
				logger.info("in else themeSettings");
				session.setAttribute("themeName", "peoplePower");
				session.setAttribute("fontName", "Arial");
				session.setAttribute("fontSize", "11");
				session.setAttribute("homePage", "/common/HomePage_input.action");
				session.setAttribute("homeCode", "");
			}
		
		model.terminate();
		try {
		createMainMenu();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("request.getRequestURL().toString()  "+request.getRequestURL().toString());
		if(request.getRequestURL().toString().contains("http://www.myglodyne"))
		{
						str="applyPortal";
		}
		else{
			str="apply";
		}
	 	
		return str;
	} // End of method
	
	public ThemeSettings getThemeSettings() {
		return themeSettings;
	}
	public void setThemeSettings(ThemeSettings themeSettings) {
		this.themeSettings = themeSettings;
	}
	
	
	/**
	 * After applying theme this action is called
	 * to take the user to hoe page of application
	 * @return
	 * @throws Exception
	 */
	public String createMainMenu() throws Exception {
		LoginBean lb=(LoginBean)request.getSession().getAttribute("loginBeanData");
		String contextPath = request.getContextPath();
		int random1 = 0;
		Random random = new Random();
		
		/*
		 * query to set menu according to the profile assigned
		 * to the user
		 */
		//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE CODE
		String query = "SELECT distinct HRMS_MENU.MENU_CODE,HRMS_MENU.MENU_NAME, "
				+ " CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL THEN  '"
				+ contextPath
				+ "'||HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK,"
				+ " NVL(HRMS_MENU.MENU_ALT_MESSAGE,'Click'),HRMS_MENU.MENU_TARGET,MENU_PLACEINMENU,MENU_TABORDER "
				+ " FROM HRMS_MENU "
				+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' "
				+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
				+ " OR PROFILE_GENERAL_FLAG ='Y'))"
				+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE IN ("
				+ lb.getMultipleProfileCode()
				+ ")"
				+ " AND MENU_PARENT_CODE=0 "//AND MENU_ISRELEASED='Y'"
				+ " ORDER BY MENU_TABORDER,HRMS_MENU.MENU_CODE";
		MenuModel model = new MenuModel();
		model.initiate(context, request.getSession());
		String[][] menuList = model.getMenuList(query);
		String poolName="";
		try {
			poolName = String.valueOf(request.getSession().getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null || poolName
					.equals(null))) {
				poolName = "\\" + poolName;
			}
			String path = getText("data_path") + "\\datafiles\\" + poolName
					+ "\\xml\\thought\\thought.xml";

			String[][] thoughtObj;
			String thought = "";
			/*
			 * method to read thoughts which are displayed on header page,
			 * from XML files
			 */
			try {

				thoughtObj = model.processThought(new XMLReaderWriter()
						.parse(new File(path)));
				String []thouthId=new String[thoughtObj.length];
				for(int i=0;i< thouthId.length ;i++){
					thouthId[i]= thoughtObj [i][1];
				}
				
				/*
				 * code to randomly display the thoughts
				 */
				int randomValue = Integer.parseInt(String.valueOf(thoughtObj[random.nextInt(thouthId.length)][1]));
				
				for (int k = 0; k < thoughtObj.length; k++) {
					if(randomValue == Integer.parseInt(String.valueOf(thoughtObj[k][1])))
					{
						thought = String.valueOf(thoughtObj[k][2]);
					}
				}
			} catch (Exception e) {
				thoughtObj = new String[0][0];
				e.printStackTrace();
			}
			request.setAttribute("thoughtObj", thoughtObj);
			Object[][] cmpName = model.getCmpName();
			String cmpanyName = null;
			try {
				cmpanyName = String.valueOf(cmpName[0][0]);
			} catch (Exception e) {
			}
			
			LoginModel loginModel = new LoginModel(lb);
			loginModel.initiate(context, request.getSession());
			Object[][] obj=loginModel.setLoginInfo(lb.getEmpId());
			if(obj!=null && obj.length>0){
			logger.info("UserID----"+String.valueOf(obj[0][0]));
			logger.info("UserName----"+String.valueOf(obj[0][1]));
			request.setAttribute("UserID", String.valueOf(obj[0][0]));
			request.setAttribute("UserName", String.valueOf(obj[0][1]));
			request.setAttribute("thought", thought);
			}
			
			request.setAttribute("menuList", menuList);
			request.setAttribute("compName", cmpanyName);
			model.terminate();
		} catch (Exception e) {
		} // outer try-catch end
		return "success";
	} //end of method

} // end of class
