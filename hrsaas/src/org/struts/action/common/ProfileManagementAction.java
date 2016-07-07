
package org.struts.action.common;

import java.io.PrintWriter;

import org.paradyne.bean.common.ProfileBean;
import org.paradyne.model.common.ProfileModel;
import org.struts.lib.ParaActionSupport;


/**
 * @author prajakta.bhandare
 * @date 21 June 2013
 */
public class ProfileManagementAction extends ParaActionSupport {
	ProfileBean profileBean;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);

	/**
	 * @return profileBean
	 */
	public ProfileBean getProfileBean() {
		return profileBean;
	}

	/**
	 * @param profileBean
	 * the profileBean to set
	 */
	public void setProfileBean(ProfileBean profileBean) {
		this.profileBean = profileBean;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return profileBean;
	}

	/**
	 * Method to create instance of bean and set automatically to every method
	 */
	public void prepare_local() throws Exception {
		profileBean = new ProfileBean();
		profileBean.setMenuCode(6);
	}

	/**
	 * OnLoad
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		
	}
	
	public String input() throws Exception{
		System.out.println("@@@@@@@@@@--------------In Input");
		profileBean.setOnloadChk(true);
		ProfileModel model = new ProfileModel();
		model.initiate(context, session);
		model.getProfileList(profileBean,request);
		model.terminate();
		getNavigationPanel(1);
		profileBean.setEnableAll("Y");
		return "input";
	}
	
	/** Method to create new profile
	 * @return String
	 */
	public String addNew(){
		ProfileModel model = new ProfileModel();
		model.initiate(context, session);
		profileBean.setCreateFlag(true);
		profileBean.setCopyFlag(false);
		model.terminate();
		getNavigationPanel(2);
		profileBean.setEnableAll("Y");
		return "profileData";
	}
	/** Retrieving list of menus and their flags
	 * @return String
	 */
	public String getMenuItems() {
		if (String.valueOf(profileBean.getCheck()).equals("U")) {
			profileBean.setChk(true);
		}// end of if
		String menuCode = String.valueOf(profileBean.getSelMenuCode());
		logger.info("menucode-------" + menuCode);
		ProfileModel model = new ProfileModel();
		model.initiate(context, session);
		//String[][] menuItems = model.getMenuItems(menuCode, profileBean);
		String[][] menuName1 = model.getMenuList(profileBean);
		request.setAttribute("menuName1", menuName1);
		model.terminate();
		profileBean.setGoFlag(true);
		return "profileMenuList";
	}

	
	/**
	 * To copy flags of one profile to another
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String copyProfileItems() throws Exception {
		try {
			profileBean.setCopyFlag(true);
			profileBean.setGoFlag(true);
			if (String.valueOf(profileBean.getCheck()).equals("C")) {
				profileBean.setCopyChk(true);
			}// end of if
			ProfileModel model = new ProfileModel();

			model.initiate(context, session);

			model.initiate(context, session);
			boolean isCopy = model.copyProfile(profileBean);
			
			
			if (isCopy) {
				addActionMessage("Profile copied Successfully");
			} // end of if
			else {
				
				addActionMessage("New profile already exists.\n Please enter different profile name.");
				return copyProfile();
			}// end of else
			model.getProfileList(profileBean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in Copy");
		}
		getNavigationPanel(1);
		return "input";
	}

	/**
	 * f9 action - search method
	 * 
	 * @return
	 * @throws Exception
	 */

	public String f9action() throws Exception {
		try {
			
			String query = "SELECT PROFILE_CODE,PROFILE_NAME FROM HRMS_PROFILE_HDR ORDER BY PROFILE_CODE";
			String[] headers = { getMessage("profile.code"), getMessage("profile") };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "profileId","profile"};
			int[] columnIndex = { 0, 1 };
			String submitFlag = "true";
			String submitToMethod = "profileAction_updateProfile.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in Search");
		}
		return "f9page";
	}

	/**
	 * Saerch (f9) for copy profile
	 * @return
	 * @throws Exception
	 */
	public String f9Copy() throws Exception {
		try {
			
			String query = "SELECT PROFILE_CODE,PROFILE_NAME,PROFILE_CODE,PROFILE_NAME FROM HRMS_PROFILE_HDR"
							+" WHERE PROFILE_CODE NOT IN("+profileBean.getProfileId()+")";
			String[] headers = { getMessage("profile.code"), getMessage("profile") };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "copyProfileId",
					"copyProfile", "copyProId",
					"copyProName" };
			int[] columnIndex = { 0, 1, 2, 3 };
			String submitFlag = "false";

			String submitToMethod = "profileAction_getMenuItems.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in Search----copy");
		}
		return "f9page";
	}

	/**
	 * reset the fields
	 * 
	 * @return
	 * @throws Exception
	 */
	public String reset() throws Exception {
		profileBean.setProfileId("");
		profileBean.setProfile("");
		profileBean.setCopyProfileId("");
		profileBean.setCopyProfile("");
		profileBean.setHiddenProfile("");
		profileBean.setHiddenProfileId(""); 
		profileBean.setParaId("");
		return input();
	}


	/**
	 * Setting flags on update profile action
	 * @return String
	 * @throws Exception
	 */
	public String updateProfile() throws Exception {
		ProfileModel model = new ProfileModel();
		model.initiate(context, session);
		profileBean.setUpdFlag(true);
		profileBean.setCheck("U");
		model.terminate();
		getNavigationPanel(2);
		return getMenuItems();
	}

	/**
	 * Setting flags on copy profile action
	 * @return String
	 * @throws Exception
	 */
	public String copyProfile() throws Exception {
		profileBean.setCopyFlag(true);
		profileBean.setCreateFlag(false);
		getNavigationPanel(2);
		return "profileData";
	}

	/**
	 * To create report for Menu Profile
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String report() throws Exception {

		try {
			ProfileModel model = new ProfileModel();
			model.initiate(context, session);
			model.getProfileMenusReport(profileBean,request);
			model.getProfileUsers(profileBean);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in Report");
		}
		return "report";
	}
	
	/** @author prajakta.bhandare
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		final ProfileModel model = new ProfileModel();
		model.initiate(context, session);
		model.getProfileList(profileBean, request);
		getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}
	
	/** @author prajakta.bhandare
	 * Method to get module list on Go Action
	 * @return String
	 * @throws Exception
	 */
	public String callGo() throws Exception {
		ProfileModel model = new ProfileModel();
		model.initiate(context, session);
		boolean result=false;
		try {
			String[][] menuName1 = model.getMenuList(profileBean);
			request.setAttribute("menuName1", menuName1);
			result = model.saveProfile(profileBean);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.getProfileList(profileBean, request);
		model.terminate();
			if (result) {
				getNavigationPanel(2);
				return "profileMenuList";
			}//end of if
			else {
				addActionMessage(getMessage("duplicate"));
				getNavigationPanel(1);
				return "input";
			}//end of else 
		
	}

	/** @author prajakta.bhandare
	 * Method to get child forms of selected module name 
	 * @return String
	 * @throws Exception
	 */
	public String callConfigMenu() throws Exception {
	 ProfileModel model;
	 model = new ProfileModel();
	 model.initiate(context, session);
	try {
		String[] typeName=null;
		System.out.println("@@@@@@@@@@--------------In callConfigMenu");
		 
		 if(profileBean.getParaId().equals("My Services")|| profileBean.getParaId().equals("My Analytics")){
			 profileBean.setNoConf("true");
			 model.callConfigMenu(profileBean.getParaId(),profileBean,request,typeName);
		 }//end of if
		 else{
			 String listType=request.getParameter("lstType");
			 profileBean.setListType(listType);
			 if (profileBean.getListType()==null || profileBean.getListType().equals("") || profileBean.getListType().equals("null")) {
				 profileBean.setListType("Config");
			}
			 String query="SELECT MENU_GROUP_ABBR FROM HRMS_MENU WHERE MENU_CODE="+profileBean.getParaId();
			 Object[][] data=model.getSqlModel().getSingleResult(query);
			 try {
				 if (data!=null && data.length > 0) {
					 String type=String.valueOf(data[0][0]);
					  typeName=type.split(",");
					  System.out.println("length............."+typeName.length);
					  if(typeName.length > 0){
					  }
				 }
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			  request.setAttribute("type", typeName);
			 model.callConfigMenu(profileBean.getParaId(),profileBean,request,typeName);
		 }
	} catch (RuntimeException e) {
		e.printStackTrace();
	}
	
	 model.terminate();
	 getNavigationPanel(3);
		return "profileMenuForm";
	}
	

	public String saveProfile() throws Exception{
		try {
			ProfileModel model= new ProfileModel();
			model.initiate(context, session);
			String insertFlg[] = null;
			String updateFlg[] = null;
			String deleteFlg[] = null;
			String viewFlg[] = null;
			String generalFlg[] = null;
			String menuCode[] = null;
			boolean flag=false;
			 menuCode= (String[]) request.getParameterValues("menuCodeId");
			try {
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception in insert");
			}
			try {
				insertFlg = (String[]) request.getParameterValues("hidInsertCheck");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception in insert");
			}

			try {
				updateFlg = (String[]) request.getParameterValues("hidUpdateCheck");
			} catch (Exception e) {
				logger.error("Exception in update");
			}
			try {
				deleteFlg = (String[]) request.getParameterValues("hidDeleteCheck");
			} catch (Exception e) {
				logger.error("Exception in delete");
			}
			try {
				viewFlg = (String[]) request.getParameterValues("hidViewCheck");
			} catch (Exception e) {
				logger.error("Exception in view");
			}
			try {
				generalFlg = (String[]) request
						.getParameterValues("hidGeneralCheck");
			} catch (Exception e) {
				logger.error("Exception in general");
			}
			boolean isCreate = model.saveProfileFlags(profileBean, insertFlg,
					updateFlg, deleteFlg, viewFlg, generalFlg,menuCode);
			if (isCreate) {
				addActionMessage(getMessage("save"));
			} // end of if
			else {
				addActionMessage("Choose any of the Menus for Saving Profile");
			}// end of else
		model.getProfileList(profileBean, request);
		model.terminate();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getNavigationPanel(3);
		return callConfigMenu();
	}
	
	public String backProfile() throws Exception{
		try {
			ProfileModel model = new ProfileModel();
			model.initiate(context, session);
			reset();
			getNavigationPanel(1);
			model.getProfileList(profileBean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String changeMenu()throws Exception{
		String menuCode = request.getParameter("menuCode");
		String labelId = request.getParameter("nameId");
		String labelVal = request.getParameter("nameVal");
		ProfileModel model = new ProfileModel();
		model.initiate(context, session);
		//model method call to change the label
		boolean res = model.changeMenu(labelId, labelVal,menuCode,getText("data_path"));
		
		// code to send back response to AJAX method
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(!res)
				out.println("false");
			else
				out.println("true");
		} // try end
		catch(Exception e)
		{
			logger.error("Exception in restore label while setting response content : "+e);
		}
		model.terminate();
		return null;
	}
	
	
	public String restoreMenu() throws Exception{
		
		try {
			System.out.println("IN restoreMenu....");
			String menuCode=request.getParameter("menuCode");
			String labelId=request.getParameter("nameId");
			ProfileModel model = new ProfileModel();
			model.initiate(context, session);
			String responseLabel = model.restoreMenu(labelId, menuCode, getText("data_path"));
			try {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println(responseLabel);
			}//try block end
			catch(Exception e)
			{
				logger.error("Exception in restore name while setting response content : "+e);
			} // catch block end
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String backProfileList() throws Exception{
		try {
			ProfileModel model = new ProfileModel();
			model.initiate(context, session);
			getNavigationPanel(2);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getMenuItems();
	}
}
