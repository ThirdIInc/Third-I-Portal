package org.paradyne.model.common;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.common.ProfileBean;
import org.paradyne.lib.Menu;
import org.paradyne.lib.MenuList;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author prajakta.bhandare
 * @date 21 June 2013
 */

public class ProfileModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	private Vector vect=new Vector();
	/**
	 * get All the menus whose parent code is 0; set it on the jsp tabs form
	 * 
	 * @param bean
	 * @return String
	 */
	public String[][] getMenuList(ProfileBean bean) {
		String query = "SELECT HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME),MENU_ISHOME,MENU_GROUP_ABBR"
				+" FROM HRMS_MENU " 
				+" LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE) " 
				+" WHERE HRMS_MENU.MENU_PARENT_CODE=0 AND MENU_ISRELEASED='Y'" 
				+" AND HRMS_MENU.MENU_CODE NOT IN(35,408,411,2317,906,2316,884,1118)"
				+" ORDER BY HRMS_MENU.MENU_TABORDER,HRMS_MENU.MENU_CODE ";
		Object[][] obj = getSqlModel().getSingleResult(query);
		String[][] strObj = null;
		if (obj != null) {
			if (obj.length > 0) {
				strObj = new String[obj.length][4];

				logger.info("length1---------" + obj.length);

				for (int i = 0; i < strObj.length; i++) {// no.of rows
					for (int j = 0; j < strObj[0].length; j++) {// no.of columns
						strObj[i][j] = String.valueOf(obj[i][j]);
					}// end of j loop
				}// end of i loop
			}// end of if
			else {
				strObj = new String[0][0];
			}// end of else
		}// end of nested if
		return strObj;

	}

	/**
	 * Setting the flags for corresponding menus
	 * 
	 * @param menuCode
	 * @param bean
	 * @return String
	 */
	public String[][] getMenuItems(String menuCode, ProfileBean bean) {
		/**
		 * if profile selected set all the values of check boxes related to
		 * child menus not set those as blanks
		 */

		String query = "SELECT  HRMS_MENU.MENU_CODE,HRMS_MENU.MENU_PARENT_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME) "
				+ " FROM HRMS_MENU  "
				+ " LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
				+ " WHERE  MENU_ISRELEASED='Y'"
				+ "  START WITH HRMS_MENU.MENU_CODE ="
				+ menuCode
				+ "  CONNECT BY PRIOR  HRMS_MENU.MENU_CODE=HRMS_MENU.MENU_PARENT_CODE "
				+ "  ORDER BY HRMS_MENU.MENU_PLACEINMENU,HRMS_MENU.MENU_CODE ";

		Object[][] obj = getSqlModel().getSingleResultInsensitive(query);
		String[][] strObj = null;
		if (obj.length > 0) {
			strObj = new String[obj.length][3];

			logger.info("length1---------" + obj.length);

			for (int i = 0; i < strObj.length; i++) {// no.of rows
				for (int j = 0; j < strObj[0].length; j++) {// no.of columns
					strObj[i][j] = String.valueOf(obj[i][j]);
				}// end of j loop
			}// end of i loop
		}// end of if
		else {
			strObj = new String[0][0];
		}// end of else
		return strObj;
	}

	
	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(ProfileBean bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_PROFILE_HDR WHERE UPPER(PROFILE_NAME) LIKE '"
				+ bean.getProfile().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/* for checking duplicate entry of record during modification */

	public boolean checkDuplicateMod(ProfileBean bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_PROFILE_HDR WHERE UPPER(PROFILE_NAME) LIKE '"
				+ bean.getProfile().trim().toUpperCase()
				+ "' AND PROFILE_CODE not in(" + bean.getProfileId() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}


	/**
	 * @param String
	 *            menuCode
	 * @return boolean value of result
	 * 
	 * save the true false values of all the flags related to the profileCode if
	 * profileCode is "" then create new profile and then save the true false
	 * values of all the flags related to the profileCode
	 * 
	 */

	public boolean saveProfile(ProfileBean bean) {
		boolean result = false; // result->isProfileAdd

		if (bean.getProfileId().equals("") || bean.getProfileId() == null) {
			if (!checkDuplicate(bean)) {
			Object[][] profObj = new Object[1][1];
			profObj[0][0] = bean.getProfile();
			result = getSqlModel().singleExecute(getQuery(4), profObj);
			}else {
				return false;
			}
			String sss = " SELECT MAX(PROFILE_CODE) FROM HRMS_PROFILE_HDR ";
			Object maxcode[][] = getSqlModel().getSingleResult(sss);
			bean.setProfileId(String.valueOf(maxcode[0][0]));

		}// end of if
		else {
			/**
			 * UPDATE RECORD
			 */
			result = true;
			result = updateProfile(bean);
			return result;
		}// end of else
		return result;

	}

	/**
	 * METHOD FOR UPDATE PROFILE DETAILS
	 * 
	 * @param bean
	 * @param cent
	 * @param paybill
	 * @param empType
	 * @return
	 */

	public boolean updateProfile(ProfileBean bean) {

		boolean result = true;

		/**
		 * UPDATE PROFILE HEADER
		 */
		if (!checkDuplicateMod(bean)) {
		Object[][] updateProf = new Object[1][2];
		updateProf[0][0] = bean.getProfile();
		updateProf[0][1] = bean.getProfileId();

		getSqlModel().singleExecute(getQuery(21), updateProf);
		} else
			return false;

		/**
		 * DELETE FROM PROFILE DTL
		 */

		return result;
	}

	
	/* for checking duplicate entry of record during insertion */
	public boolean checkCopyDuplicate(ProfileBean bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_PROFILE_HDR WHERE UPPER(PROFILE_NAME) LIKE '"
				+ bean.getCopyProfile().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}
	
	/* for checking duplicate entry of record during modification */

	public boolean checkCopyDuplicateMod(ProfileBean bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_PROFILE_HDR WHERE UPPER(PROFILE_NAME) LIKE '"
				+ bean.getCopyProName().trim().toUpperCase()
				+ "' AND PROFILE_CODE not in(" + bean.getCopyProId() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}
	
	

	/**
	 * @param String
	 *            menuCode
	 * @return boolean value of result insert the same with new profileCode in
	 *         the HDR and related HDRCode in the DTL also...
	 * 
	 */

	public boolean copyProfile(ProfileBean bean) {

		boolean result = false;
		boolean isCopy = true;
		if (bean.getCopyProfileId().equals("")
				|| bean.getCopyProfileId() == null) { 
			// check for add or update profile
			/**
			 * INSERT INTO PROFILE HEADER
			 */
			if (!checkCopyDuplicate(bean)) {
			Object[][] profObj = new Object[1][1];
			profObj[0][0] = bean.getCopyProfile();
			logger.info("copy profile ######------" + profObj[0][0]);
			result = getSqlModel().singleExecute(getQuery(4), profObj);
			}else {
				isCopy=false;
				return isCopy;
			}
			String sss = " SELECT MAX(PROFILE_CODE) FROM HRMS_PROFILE_HDR ";
			Object maxcode[][] = getSqlModel().getSingleResult(sss);
			bean.setCopyProfileId(String.valueOf(maxcode[0][0]));

			bean.setCopyName(true);
		} // end of if
		else {
			result = true;
			//if (!checkCopyDuplicateMod(bean)) {
			Object[][] updateProf = new Object[1][2];
			updateProf[0][0] = bean.getCopyProName();
			updateProf[0][1] = bean.getCopyProId();
			logger.info("copy profile to------" + updateProf[0][0]);
			result = getSqlModel().singleExecute(getQuery(21), updateProf);
			
		}// end of else
		if (result) {
			Object[][] delObject = new Object[1][1];
			delObject[0][0] = bean.getCopyProfileId();
			/**
			 * DELETE FROM PROFILE DTL
			 */
			getSqlModel()
					.singleExecute(getQuery(23), delObject);
		
				isCopy = copyFlags(bean);
			}// end of if

		return isCopy;

		// return result;
	}

	/**
	 * Copy flags of one profile to another
	 * 
	 * @param bean
	 * @return
	 */
	public boolean copyFlags(ProfileBean bean) {
		// Select menus of profile to be copied
		String query = " SELECT MENU_CODE,PROFILE_INSERT_FLAG,PROFILE_UPDATE_FLAG,PROFILE_DELETE_FLAG, "
				+ " PROFILE_VIEW_FLAG,PROFILE_GENERAL_FLAG "
				+ " FROM HRMS_PROFILE_DTL WHERE PROFILE_CODE= "
				+ bean.getProfileId();
		Object[][] menuObj = getSqlModel().getSingleResult(query);
		Object[][] menuInsertObj = new Object[menuObj.length][6];
		// Setting values
		logger.info("Length-----"+menuInsertObj.length);
		
		for (int i = 0; i < menuObj.length; i++) {

			menuInsertObj[i][0] = String.valueOf(menuObj[i][0]);
			menuInsertObj[i][1] = String.valueOf(menuObj[i][1]);
			menuInsertObj[i][2] = String.valueOf(menuObj[i][2]);
			menuInsertObj[i][3] = String.valueOf(menuObj[i][3]);
			menuInsertObj[i][4] = String.valueOf(menuObj[i][4]);
			menuInsertObj[i][5] = String.valueOf(menuObj[i][5]);

		}
		// insert values into new profile
		String insertQuery = "INSERT INTO HRMS_PROFILE_DTL(PROFILE_CODE,MENU_CODE,PROFILE_INSERT_FLAG, "
				+ " PROFILE_UPDATE_FLAG,PROFILE_DELETE_FLAG,PROFILE_VIEW_FLAG,PROFILE_GENERAL_FLAG) "
				+ " VALUES (" + bean.getCopyProfileId() + ",?,?,?,?,?,?)";
	 getSqlModel().singleExecute(insertQuery, menuInsertObj);
	 return true;
	}

	/** *********FOR FLAG SETTING****** */
	public boolean createProfileFlag(ProfileBean bean) {

		return true;
	}

	public boolean callGoModel(ProfileBean bean) {
		bean.setGoFlag(true);
		return true;
	}

	// *****************************************************************************
	// FOR REPORT GENERATION
	// *****************************************************************************//

	/**
	 * Generating all profile menus and their flags Sets into list
	 */
/*	public void getProfileMenusReport(ProfileBean bean) {
		String query = "SELECT HRMS_MENU.MENU_CODE,HRMS_MENU.MENU_NAME,HRMS_PROFILE_DTL.PROFILE_INSERT_FLAG ,HRMS_PROFILE_DTL.PROFILE_UPDATE_FLAG, "
				+ " HRMS_PROFILE_DTL.PROFILE_DELETE_FLAG,HRMS_PROFILE_DTL.PROFILE_VIEW_FLAG,HRMS_PROFILE_DTL.PROFILE_GENERAL_FLAG "
				+ " FROM HRMS_MENU "
				+ " LEFT JOIN HRMS_PROFILE_DTL ON HRMS_MENU.MENU_CODE =HRMS_PROFILE_DTL.MENU_CODE "
				+ " WHERE HRMS_MENU.MENU_CODE !=0 AND HRMS_PROFILE_DTL.PROFILE_CODE ="
				+ bean.getProfileId() + " ORDER BY MENU_CODE ";

		Object[][] obj = getSqlModel().getSingleResult(query);
		ArrayList<Object> menuList = new ArrayList<Object>();
		for (int i = 0; i < obj.length; i++) {

			ProfileBean prfBean = new ProfileBean();
			if (String.valueOf(obj[i][2]).equals("Y")
					|| String.valueOf(obj[i][3]).equals("Y")
					|| String.valueOf(obj[i][4]).equals("Y")
					|| String.valueOf(obj[i][5]).equals("Y")
					|| String.valueOf(obj[i][6]).equals("Y")) {

				prfBean.setMenuName(String.valueOf(obj[i][1]));
				prfBean.setInsert(String.valueOf(obj[i][2]));
				prfBean.setUpdate(String.valueOf(obj[i][3]));
				prfBean.setDelete(String.valueOf(obj[i][4]));
				prfBean.setView(String.valueOf(obj[i][5]));
				prfBean.setGeneral(String.valueOf(obj[i][6]));
				menuList.add(prfBean);
			}

		}
		bean.setMenuList(menuList);

	}*/
	
	public void getProfileMenusReport(ProfileBean bean,HttpServletRequest request) {
		String query = "SELECT HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME),HRMS_MENU.MENU_NAME, "
			+ " HRMS_MENU.MENU_PARENT_CODE,HRMS_PROFILE_DTL.PROFILE_INSERT_FLAG ,HRMS_PROFILE_DTL.PROFILE_UPDATE_FLAG, "
			+ " HRMS_PROFILE_DTL.PROFILE_DELETE_FLAG,HRMS_PROFILE_DTL.PROFILE_VIEW_FLAG,HRMS_PROFILE_DTL.PROFILE_GENERAL_FLAG "
			+ " ,2*(level-1)/2,HRMS_MENU.MENU_PLACEINMENU "
			+ " FROM HRMS_MENU "
			+ " LEFT JOIN HRMS_PROFILE_DTL ON HRMS_MENU.MENU_CODE =HRMS_PROFILE_DTL.MENU_CODE "
			+ " left join  HRMS_MENU_CLIENT ON(HRMS_MENU_CLIENT.MENU_CODE=HRMS_MENU.MENU_CODE) "
			+ " WHERE HRMS_MENU.MENU_CODE !=0 AND HRMS_PROFILE_DTL.PROFILE_CODE ="
			+ bean.getProfileId() + " ORDER BY HRMS_MENU.MENU_PLACEINMENU,MENU_PARENT_CODE, HRMS_MENU.MENU_CODE  ";
		
		Object[][] menudata = getSqlModel().getSingleResult(query);
		System.out.println("length of menu data is"+menudata.length);
		request.setAttribute("tot", menudata.length);
		request.setAttribute("menudata", menudata);
		MenuList menuList = new MenuList(menudata);
		Menu menu = (Menu) menuList.getMenuList();
		LinkedList list = menu.getChild();
		System.out.println("list.size()...." + list.size());
		for (int i = 0; i < list.size(); i++) {
			Menu menuChild = (Menu) list.get(i);
			int count=0;
			createMenuBar5(menuChild,bean,count);
			
		}
		
		//////////vector...... forloop......size/7
		int count=0;
		String[][] obj=new String[vect.size()/8][8];
		ArrayList  al=new ArrayList();
		for (int i = 0; i < vect.size()/8; i++) {
			ProfileBean  bean1=new ProfileBean();
			obj[i][0]=(String.valueOf(vect.get(count++)));
			obj[i][1]=(String.valueOf(vect.get(count++)));
			obj[i][2]=(String.valueOf(vect.get(count++)));
			obj[i][3]=(String.valueOf(vect.get(count++)));
			obj[i][4]=(String.valueOf(vect.get(count++)));
			obj[i][5]=(String.valueOf(vect.get(count++)));
			obj[i][6]=(String.valueOf(vect.get(count++)));
			obj[i][7]=(String.valueOf(vect.get(count++)));
	
			
		}
		request.setAttribute("menu", obj);
		
	}

	 public void createMenuBar5(Menu menuBean,ProfileBean bean,int countLevel){
	        int count = menuBean.getMenuCount();
	        countLevel++;
	        ProfileBean  bean1 = new ProfileBean ();
			
	        System.out.println("menuBean.getMenuCount()"+menuBean.getMenuCount());
	        System.out.println("menuBean.getMenuDesc()"+menuBean.getMenuDesc());
	        System.out.println("MENU LEVEL....."+countLevel);
	        String spaces="";
	        String image="";
	        if(menuBean.getMenuCount()==0){
	        	image="<img src='../pages/common/dtree/img/file.png'/>";
	        }
	        else{
	        	image="<img src='../pages/common/dtree/img/folder_open.png'/>";
	        }
	        switch (countLevel) {
			case 1:
				spaces="&nbsp;&nbsp;"+image;
				break;
			case 2:
				spaces="&nbsp;&nbsp;&nbsp;&nbsp;"+image;
				break;

			case 3:
				spaces="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+image;
				break;

			case 4:
				spaces="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+image;
				break;


			default:
				break;
			}
	        menuBean.setMenuDesc(spaces+menuBean.getMenuDesc());
	        vect.add(menuBean.getMenuId());
	        vect.add(menuBean.getMenuDesc());
	        vect.add(menuBean.getInsertFlag());
	        vect.add(menuBean.getUpdateFlag());
	        vect.add(menuBean.getDeleteFlag());
	        vect.add(menuBean.getSelectFlag());
	        vect.add(menuBean.getShowAllFlag());
	        vect.add(menuBean.getMenuCount());
	        
	        if(count == 0)
	        {
	        } else
	        {
	            LinkedList list = menuBean.getChild();
	            for(int i = 0; i < list.size(); i++)
	            {
	                createMenuBar5((Menu)list.get(i),bean,countLevel);
	                
	            }

	        }
	       
	    }
	 
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}	 
		
	

	/**
	 * Generating list of users
	 * 
	 * @param bean
	 */
	public void getProfileUsers(ProfileBean bean) {
		String query = "SELECT HRMS_LOGIN.LOGIN_CODE,HRMS_LOGIN.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' ' ||HRMS_EMP_OFFC.EMP_LNAME,NVL(HRMS_RANK.RANK_NAME,' ') ,HRMS_LOGIN.LOGIN_NAME	"
				+ " FROM HRMS_LOGIN "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LOGIN.EMP_ID) "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) "
				+ " LEFT JOIN HRMS_RANK  ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
				+ " WHERE HRMS_LOGIN.PROFILE_CODE=	"
				+ bean.getProfileId()
				+ " ORDER BY HRMS_LOGIN.LOGIN_CODE";

		Object[][] obj = getSqlModel().getSingleResult(query);
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < obj.length; i++) {

			ProfileBean prfBean = new ProfileBean();
			if (obj.length > 0) {

				prfBean.setLoginID(String.valueOf(obj[i][0]));
				prfBean.setEmpID(String.valueOf(obj[i][1]));
				prfBean.setEmpToken(String.valueOf(obj[i][2]));
				prfBean.setEmpName(String.valueOf(obj[i][3]));
				prfBean.setEmpDesg(String.valueOf(obj[i][4]));
				prfBean.setLoginName(String.valueOf(obj[i][5]));
				list.add(prfBean);
			}

		}
		bean.setEmployeeList(list);

	}
	
	/** @author prajakta.bhandare
	 * @date 26 March 2013
	 * @param bean
	 */
	public void getProfileList(ProfileBean bean,HttpServletRequest request) {
		Object dataObj[][]=getSqlModel().getSingleResult(getQuery(24));
		ArrayList<Object> list = new ArrayList<Object>();
		if(dataObj!=null && dataObj.length > 0){//if data present
			bean.setProfileListLength("true");
			bean.setTotalNoOfRecords(String.valueOf(dataObj.length));
			final String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					dataObj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
			}
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if ("1".equals(pageIndex[4])) {
				bean.setMyPage("1");
			}
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {//i for loop
				ProfileBean innerbean =new ProfileBean();
				innerbean.setHiddenProfileId(checkNull(String.valueOf(dataObj[i][0])));
				innerbean.setHiddenProfile(checkNull(String.valueOf(dataObj[i][1])));
				list.add(innerbean);
			}//end of i for loop
		}//end if data present
		bean.setProfileList(list);
	}
	
	/** @author prajakta.bhandare
	 * @param moduleCode
	 * @param bean
	 * @return String
	 * @throws Exception
	 */
	public void callConfigMenu(String moduleCode,ProfileBean bean,HttpServletRequest request,String[]typeName) throws Exception{
		try {
			HashMap<String, Object> newMap= new HashMap<String, Object>();
			if (bean.getNoConf().equals("true")) {
				 String dataQuery="SELECT DISTINCT HRMS_MENU.MENU_CODE, NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME),MENU_GROUP,"
					 +" NVL(HRMS_PROFILE_DTL.PROFILE_INSERT_FLAG,'N') ,NVL(HRMS_PROFILE_DTL.PROFILE_UPDATE_FLAG,'N'),"
					 +" NVL(HRMS_PROFILE_DTL.PROFILE_DELETE_FLAG,'N'),NVL(HRMS_PROFILE_DTL.PROFILE_VIEW_FLAG,'N'),"
					 +" NVL(HRMS_PROFILE_DTL.PROFILE_GENERAL_FLAG,'N'),"
					 +" MENU_GROUP,HRMS_MENU.MENU_PLACEINMENU"
					 +" FROM HRMS_MENU"
					 +" LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
					 +"  LEFT JOIN HRMS_PROFILE_DTL ON(HRMS_MENU.MENU_CODE =HRMS_PROFILE_DTL.MENU_CODE"
					 +" AND HRMS_MENU.MENU_CODE !=0 AND HRMS_PROFILE_DTL.PROFILE_CODE ='"+bean.getProfileId()+"' AND MENU_ISRELEASED='Y')";
					if (bean.getParaId().equals("My Services")) {
						dataQuery+=" WHERE MENU_TYPE='ES'";
					}else if (bean.getParaId().equals("My Analytics")) {
						dataQuery+=" WHERE MENU_TYPE='RT'";
					}
					dataQuery+=" ORDER BY HRMS_MENU.MENU_PLACEINMENU";
					newMap = getSqlModel().getSingleResultMap(dataQuery, 9, 2);
			}else{
				 String query = "SELECT DISTINCT MENU_GROUP,MENU_GROUP_ORDER,MENU_IMAGE FROM HRMS_MENU" 
				 +" WHERE MENU_TYPE=";
				 if(bean.getListType().equals("Config")){
					 bean.setListType("Config");
				 query+="'"+typeName[0]+"'";
				 }else if (bean.getListType().equals("Process")) {
					 bean.setListType("Process");
					 query+="'"+typeName[1]+"'";
				}else if (bean.getListType().equals("Report")) {
					 bean.setListType("Report");
					 query+="'"+typeName[2]+"'";
				}else{
					 bean.setListType("Config");
					query+="'"+typeName[0]+"'";
				}
				 query+=" AND MENU_ISRELEASED='Y' "
				 +" ORDER BY MENU_GROUP_ORDER ";
				 Object[][] newObj= getSqlModel().getSingleResult(query);
			 	
			
			 if(newObj.length > 0){
				 bean.setCheck("true");
				 for (int i = 0; i < newObj.length; i++) {
					 String menuQuery="SELECT DISTINCT HRMS_MENU.MENU_CODE, NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), MENU_GROUP,"
					+" NVL(HRMS_PROFILE_DTL.PROFILE_INSERT_FLAG,'N') ,NVL(HRMS_PROFILE_DTL.PROFILE_UPDATE_FLAG,'N'),"
					+" NVL(HRMS_PROFILE_DTL.PROFILE_DELETE_FLAG,'N'),NVL(HRMS_PROFILE_DTL.PROFILE_VIEW_FLAG,'N'),NVL(HRMS_PROFILE_DTL.PROFILE_GENERAL_FLAG,'N'), "
					+" MENU_GROUP_ORDER,HRMS_MENU.MENU_PLACEINMENU"
					+" FROM HRMS_MENU"
					+" LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
					+" LEFT JOIN HRMS_PROFILE_DTL ON(HRMS_MENU.MENU_CODE =HRMS_PROFILE_DTL.MENU_CODE"
					+" AND HRMS_MENU.MENU_CODE !=0 AND HRMS_PROFILE_DTL.PROFILE_CODE ='"+bean.getProfileId()+"' )"
					+" WHERE MENU_TYPE=";
					 
					 if(bean.getListType().equals("Config")){
						 menuQuery+="'"+typeName[0]+"'";
						 }else if (bean.getListType().equals("Process")) {
							 menuQuery+="'"+typeName[1]+"'";
						}else if (bean.getListType().equals("Report")) {
							menuQuery+="'"+typeName[2]+"'";
						}else{
							menuQuery+="'"+typeName[0]+"'";
						}
					
					menuQuery+=" AND MENU_GROUP='"+newObj[i][0]+"' AND MENU_ISRELEASED='Y'"
					+" ORDER BY MENU_GROUP_ORDER,HRMS_MENU.MENU_PLACEINMENU";
					 Object[][] menuObj= getSqlModel().getSingleResult(menuQuery);
					 newMap.put(String.valueOf(newObj[i][0]), menuObj);
					}
				
			 }
			 request.setAttribute("image", newObj);
			
			}
			 request.setAttribute("menuForm", newMap);
			 
		 } catch (Exception e) {
			 e.printStackTrace();
			}
	}
	
	public boolean saveProfileFlags(ProfileBean bean, String[]insertFlg,
			String[]updateFlg, String[]deleteFlg, String[]viewFlg, String[]generalFlg, String[]menuCode){
		boolean result=false;
		boolean result1=false;
		boolean res=false;
		boolean flag=false;
		String code="";
		try {
			System.out.println("My parent Code................"+bean.getParaId());
			for (int i = 0; i < menuCode.length; i++) {
				if(i==menuCode.length-1){
					code+=menuCode[i];
				}else{
					code+=menuCode[i];
					code+=",";
				}
			}
			String deleteQuery="";
			if(bean.getParaId().equals("My Services") || bean.getParaId().equals("My Analytics")){
				 
				deleteQuery="DELETE FROM HRMS_PROFILE_DTL WHERE MENU_CODE IN("+code+") AND PROFILE_CODE="+bean.getProfileId();
			
			}else{
				deleteQuery="DELETE FROM HRMS_PROFILE_DTL WHERE MENU_CODE IN("+code+","+bean.getParaId()+") AND PROFILE_CODE="+bean.getProfileId();
				
			}
			
			result = getSqlModel().singleExecute(deleteQuery);
			if(result)
			{
				Object[][] menuInsertObj = new Object[menuCode.length][6];
				for (int i = 0; i < menuCode.length; i++) {
					menuInsertObj[i][0]=menuCode[i];
					menuInsertObj[i][1]=insertFlg[i];
					menuInsertObj[i][2]=updateFlg[i];
					menuInsertObj[i][3]=deleteFlg[i];
					menuInsertObj[i][4]=viewFlg[i];
					menuInsertObj[i][5]=generalFlg[i];
					if (insertFlg[i].equals("Y")|| updateFlg[i].equals("Y") || deleteFlg[i].equals("Y") || viewFlg[i].equals("Y")|| generalFlg[i].equals("Y")) {
						flag=true;
					}
				}
				if (flag) {
						String insertMenuQuery="INSERT INTO HRMS_PROFILE_DTL(PROFILE_CODE,MENU_CODE,PROFILE_INSERT_FLAG, "
							+ " PROFILE_UPDATE_FLAG,PROFILE_DELETE_FLAG,PROFILE_VIEW_FLAG,PROFILE_GENERAL_FLAG) "
							+ " VALUES (" + bean.getProfileId() + ","+bean.getParaId()+",'Y','Y','Y','Y','N')";
								 result1= getSqlModel().singleExecute(insertMenuQuery);
				}		
								 String insertQuery = "INSERT INTO HRMS_PROFILE_DTL(PROFILE_CODE,MENU_CODE,PROFILE_INSERT_FLAG, "
										+ " PROFILE_UPDATE_FLAG,PROFILE_DELETE_FLAG,PROFILE_VIEW_FLAG,PROFILE_GENERAL_FLAG) "
										+ " VALUES (" + bean.getProfileId() + ",?,?,?,?,?,?)";
										res= getSqlModel().singleExecute(insertQuery, menuInsertObj);
					
				
			
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return res;
		
	}

	public boolean changeMenu(String labelId, String labelVal,
			String menuCode, String path){
		boolean flag1=false;
		try {
			String deleteQuery="DELETE FROM  HRMS_MENU_CLIENT WHERE MENU_CODE="+menuCode;
			boolean flag =getSqlModel().singleExecute(deleteQuery);
			if(flag){
				String insertQuery="INSERT INTO HRMS_MENU_CLIENT(MENU_NAME,MENU_CODE)VALUES('"+labelVal+"',"+menuCode+")";
				 flag1 =getSqlModel().singleExecute(insertQuery);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return flag1;
	}
	
	public String restoreMenu(String labelId, 
			String menuCode, String path){
		String resLabel="";
		String deleteQuery="DELETE FROM  HRMS_MENU_CLIENT WHERE MENU_CODE="+menuCode;
		boolean flag =getSqlModel().singleExecute(deleteQuery);
		if(flag){
			String selectQuery="SELECT MENU_NAME,MENU_CODE FROM HRMS_MENU WHERE MENU_CODE="+menuCode;
			Object[][] data= getSqlModel().getSingleResult(selectQuery);
			resLabel= String.valueOf(data[0][0]);
		}
		return resLabel;	
	}
}
