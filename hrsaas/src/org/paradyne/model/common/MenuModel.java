/**
 * 
 */
package org.paradyne.model.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.paradyne.bean.common.MenuBean;
import org.paradyne.lib.ModelBase;

/**
 * @author Sunil
 * @since 06-04-2007
 *
 */
public class MenuModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
		
	public String[][] getMenuData(String query) {
		Object[][] obj =  getSqlModel().getSingleResult(query);
		String[][] strObj;
		if(obj.length>0) {
			strObj=new String[obj.length][obj[0].length];
			for(int i=0;i < strObj.length; i++ ) {
				for(int j =0;j < strObj[0].length ;j++ ){
					strObj[i][j]=String.valueOf(obj[i][j]);
				}
			}
		}else {						
			strObj=new String[0][0];			
		}		
		return strObj;	
	}
	
	public void setLoginInfo(MenuBean menuBean){
		try{
		String loginString = " SELECT EMP_TOKEN ,HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||' '||EMP_LNAME,EMP_TOKEN  FROM HRMS_EMP_OFFC "
							+" LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
							+" WHERE EMP_ID ='"+menuBean.getUserEmpId()+"'";
							
		Object[][] objEmp =  getSqlModel().getSingleResult(loginString);
		menuBean.setEmpTokenNo(String.valueOf(objEmp[0][0]).trim());
		menuBean.setLoginEmpName(String.valueOf(objEmp[0][1]));
		}catch (Exception e) {
			
		}
	}
	
	public String[][] getMenuList(String query){
		String[][] strObj=null;
		Object[][] obj =  getSqlModel().getSingleResult(query);
		if(obj!=null){
		strObj=new String[obj.length][5];
		
		for (int i = 0; i < obj.length; i++) {
			strObj[i][0] = String.valueOf(obj[i][0]);
			strObj[i][1] = String.valueOf(obj[i][1]);
			strObj[i][2] = String.valueOf(obj[i][2]);
			strObj[i][3] = String.valueOf(obj[i][3]);
			strObj[i][4] = String.valueOf(obj[i][4]);
							
		}
		}
		
		return strObj;
	}
	
	public String[][]  getQuickLink(MenuBean menuBean) {
		String quickQuery = "SELECT QUICK_ADMIN_LINK	,QUICK_ADMIN_LINKNAME FROM HRMS_SETTINGS_QUICKLINK_ADMIN "
					+" UNION ALL "
					+" SELECT QUICK_EMP_LINK,QUICK_EMP_LINKNAME FROM HRMS_SETTINGS_QUICKLINK_EMP";
		Object[][] quickObj = getSqlModel().getSingleResult(quickQuery);
		HashMap hash=new HashMap();
		int count=1;
		for (int i = 0; i < quickObj.length; i++) {
			hash.put(""+count, String.valueOf(quickObj[i][1]));
			count++;
		}
		menuBean.setQuickMap(hash);
		String[][] strObj=null;
		if (quickObj != null) {
			strObj = new String[quickObj.length][2];

			for (int i = 0; i < quickObj.length; i++) {
				strObj[i][0] = String.valueOf(quickObj[i][0]);
				strObj[i][1] = String.valueOf(quickObj[i][1]);

			}
		}

		return strObj;
	}
	
	public String[][] processQuickLink(Document document) throws Exception {
		  String[][] link =null;
		  
	        List fonts = document.selectNodes("//HRHOME/COMMUNICATION[@name='QUICK']/Link");
	        link = new String[fonts.size()][2];
	        int count=0;
	        for (Iterator iter = fonts.iterator(); iter.hasNext();) {
	        	Element font = (Element) iter.next();
	            link[count][0]=font.attributeValue("name");
	            link[count][1]=font.attributeValue("value");
	            count++;
	        }
	        return link;
	  }
	
	
	public String getThought(MenuBean menuBean){
		String query ="SELECT * FROM HRMS_SETTINGS_THOUGHT WHERE THOUGHT_CODE	=(SELECT MAX(THOUGHT_CODE) FROM HRMS_SETTINGS_THOUGHT ) ";
		Object[][] thought = getSqlModel().getSingleResult(query);
		String str="";
		try {
			str=String.valueOf(thought[0][1]);
			menuBean.setThought(String.valueOf(thought[0][1]));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return str;
	}
	public String[][] processThought(Document document) throws Exception {
		  String[][] link =null;
		  
	        List fonts = document.selectNodes("//HRHOME/COMMUNICATION[@name='THOUGHT']/Link");
	        link = new String[fonts.size()][3];
	        int count=0;
	        for (Iterator iter = fonts.iterator(); iter.hasNext();) {
	        	Element font = (Element) iter.next();
	            link[count][1]=font.attributeValue("id");
	            link[count][2]=font.attributeValue("name");
	            count++;
	        }
	        return link;
	  }
	
public Object[][] getCmpName(){
		
		String cmpName = "SELECT COMPANY_LOGO FROM HRMS_COMPANY";
		//logger.info("in getCompName"+cmpName);
		return getSqlModel().getSingleResult(cmpName);
		
	}

	/** Method Name : getDecisionMenu()
	 * @Description Used to display menu between MyPage and Module For Decision One 
	 * @param contextPath
	 * @param profileCode
	 * @return
	 */
	public String[][] getDecisionMenu(String contextPath, String profileCode) {

		String[][] strDecisionObj = null;
		//QUERY UPDATED BY PRAJAKTA B(4 JUNE 2013) FOR MULTIPLE PROFILE CODE
		String query = "SELECT DISTINCT HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME),"
				+ " CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL THEN  '"+contextPath+"' ||"
				+ " HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK,"
				+ " NVL(HRMS_MENU.MENU_ALT_MESSAGE,'Click'),HRMS_MENU.MENU_TARGET,HRMS_MENU.MENU_PLACEINMENU,HRMS_MENU.MENU_TABORDER"
				+ " FROM HRMS_MENU"
				+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y'"
				+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y'"
				+ " OR PROFILE_GENERAL_FLAG ='Y'))"
				+ " LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
				+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE IN ("+ profileCode+ ")"
				+ " AND MENU_PARENT_CODE=0"
				+ " AND MENU_ISRELEASED='Y' AND HRMS_MENU.MENU_CODE NOT IN (35,408,2316,2314,2310,5031,1169,906,825,944,414)"
				+ " AND MENU_ISHOME LIKE 'Y'"
				+ "AND (HRMS_MENU.MENU_TYPE NOT IN ('RT','ES'))"
				+ "ORDER BY HRMS_MENU.MENU_TABORDER,HRMS_MENU.MENU_CODE ";
		Object[][] obj = getSqlModel().getSingleResult(query);
		if (obj != null) {
			strDecisionObj = new String[obj.length][5];

			for (int i = 0; i < obj.length; i++) {
				strDecisionObj[i][0] = String.valueOf(obj[i][0]);
				strDecisionObj[i][1] = String.valueOf(obj[i][1]);
				strDecisionObj[i][2] = String.valueOf(obj[i][2]);
				strDecisionObj[i][3] = String.valueOf(obj[i][3]);
				strDecisionObj[i][4] = String.valueOf(obj[i][4]);
			}
		}
		return strDecisionObj;
}
	
}

