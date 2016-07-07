 /**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.ApplicationStudio.DefaultDashletConfig;
import org.paradyne.bean.common.HomePage;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.XMLReaderWriter;

/**
 * @author Reeba_Joseph
 * modified by  : Pankaj_Jain
 *
 */
public class DefaultDashletConfigModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(DefaultDashletConfigModel.class);
	NumberFormat formatter = new DecimalFormat("#0");

	/**
	 * return menu list according to the profile 
	 * selected
	 * @param dashletConfig
	 * @return
	 */
	public String[][] getMenuList(DefaultDashletConfig dashletConfig) {
		String query = " SELECT HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME, HRMS_MENU.MENU_NAME) FROM HRMS_MENU "
					  +" LEFT JOIN HRMS_MENU_CLIENT ON(HRMS_MENU_CLIENT.MENU_CODE=HRMS_MENU.MENU_CODE)"
					  +" INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' " 
					  +" OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' OR PROFILE_GENERAL_FLAG ='Y')) "
					  +" WHERE HRMS_PROFILE_DTL.PROFILE_CODE = "+dashletConfig.getProfileID()+" AND MENU_PARENT_CODE= 0  AND HRMS_MENU.MENU_CODE NOT IN(35,408,414) "
					  +" ORDER BY MENU_TABORDER,HRMS_MENU.MENU_CODE ";
		Object[][] obj = getSqlModel().getSingleResult(query);
		String[][] strObj = null;
		if (obj != null) {
			if (obj.length > 0) {
				strObj = new String[obj.length][2];
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

	public Object[][] setProfileDashlets(Document document,
			DefaultDashletConfig dashletConfig) {
		String[][] list = null;
		
		try{
			List node1 = document.selectNodes("//APPLICATIONSTUDIO/DASHLET");
			list = new String[node1.size()][2];
			int count = 0;
			for (Iterator itert = node1.iterator(); itert.hasNext();) {
				Element node2 = (Element) itert.next();
				list[count][0] = node2.attributeValue("code");
				list[count][1] = node2.attributeValue("name");
				count++;
			}// end of loop
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {

		}
		return list;
	}

	public void saveDashletConfig(DefaultDashletConfig bean, String[] dashCode, String[] rows, String[] cols) {
		try {
			
			String []dashCodeObj = null;
			String []rowObj = null;
			String []colObj = null;
			String[] rowColWidth = new String[3];
			String configPath = ResourceBundle.getBundle("globalMessages").getString("data_path")		
								+"\\dashLet\\"+session.getAttribute("session_pool")+"\\profile\\"
								+bean.getProfileID()+"\\"+bean.getHMenuCode()+".xml";
			int i = 0;
			int rowCount = 0;
			int colCount = 0;
			for (i = 0; i < dashCode.length; i++) {
				if(dashCode[i].equals(""))
					break;
				else
				{
					if(rowCount < Integer.parseInt(rows[i]))
						rowCount = Integer.parseInt(rows[i]);
					if(colCount < Integer.parseInt(cols[i]))
						colCount = Integer.parseInt(cols[i]);
				}
			}
			rowColWidth[0] = ""+rowCount;
			rowColWidth[1] = ""+colCount;
			rowColWidth[2] = bean.getWidthCol1();
			if(!bean.getWidthCol1().equals(""))
				rowColWidth[2]+=","+bean.getWidthCol2();
			dashCodeObj = new String[i];
			rowObj = new String[i];
			colObj = new String[i];
			for (int j = 0; j < i; j++) {
				dashCodeObj[j] = dashCode[j];
				//logger.info("dashCodeObj["+j+"] : "+dashCodeObj[j]);
				rowObj[j] = rows[j];
				//logger.info("rows["+j+"] : "+rows[j]);
				colObj[j] = cols[j];
				//logger.info("cols["+j+"] : "+cols[j]);
			}
			new XMLReaderWriter().write(buildDefaultDashletConfig("APPLICATIONSTUDIO",dashCodeObj,
					rowObj,colObj,rowColWidth),configPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Document buildDefaultDashletConfig(String head,String[] dashCode, String []rows, String cols[],
			String []rowColWidth) {
		Document document = DocumentHelper.createDocument();
		Element header;
		Element element;
		Element root = document.addElement(head);
		if(dashCode != null && dashCode.length > 0){
			header = root.addElement("DASHLET").addAttribute("name", "HOME").addAttribute("rows",
					rowColWidth[0]).addAttribute("columns",
					rowColWidth[1]).addAttribute("width",rowColWidth[2]);
			for(int i=0;i<dashCode.length;i++){
					element = header.addElement("HOME").addAttribute("code",
							dashCode[i]).addAttribute("rowId",rows[i]).addAttribute("columnId",cols[i]);
			}
		}
		return document;
	}
	
	public String[][] processDashletConfig(Document document, HttpServletRequest req) throws Exception {
		String[][] config = null;
		String[][] dashlet = null;
		try{
			List nodes = document
			.selectNodes("//APPLICATIONSTUDIO/DASHLET[@name='HOME']");
			dashlet = new String[nodes.size()][5];
			for (Iterator iter = nodes.iterator(); iter.hasNext();) {
				Element node = (Element) iter.next();
				dashlet[0][1] = node.attributeValue("rows");
				dashlet[0][2] = node.attributeValue("columns");
				dashlet[0][3] = node.attributeValue("width");
			}// end of loop
			
			String []widths=String.valueOf(dashlet[0][3]).split(",");			
			List node1 = document.selectNodes("//APPLICATIONSTUDIO/DASHLET[@name='HOME']/HOME");
			config = new String[node1.size()][5];
			int count = 0;
			int blnkCnt = 0;
			
			for (Iterator iter = node1.iterator(); iter.hasNext();) {
				Element node2 = (Element) iter.next();
				config[count][0] = node2.attributeValue("code");
				config[count][1] = node2.attributeValue("rowId");
				config[count][2] = node2.attributeValue("columnId");
				config[count][3] = String.valueOf(node1.size() - count);			// Dashlet Id
				config[count][4] = formatter.format(Double.parseDouble(widths[Integer.parseInt(String.valueOf(config[count][2]))-1])*7.0);				
				if(!(""+config[count][0]).equals(""))
					blnkCnt++;
				count++;
			}// end of loop
			// homepage.setTotdashs(""+blnkCnt);
			if(dashlet.length > 0)
				dashlet[0][4] = ""+blnkCnt;
			req.setAttribute("dashletSet",dashlet);
		}catch(Exception e){
			e.printStackTrace();
		} finally {

		}
		return config; // returns result 1
	}
	
	public Vector getDashletConfig(String menuCode, 
			String profileCode, String loginCode,HttpServletRequest request){		
		String path = ResourceBundle.getBundle("globalMessages").getString("data_path");
		try {
			String poolName = "" + session.getAttribute("session_pool");
			String configPath = "";
			Document document = null;
			Object[][] result = null;
			Object[][] result1 = null;
			Object [][] profileDashletList = null;
			File f = null;
			try {
				if(request.getParameter("menuCode") == null 
						|| request.getParameter("menuCode").equals(null)){
					configPath = path + "\\dashlet\\"+poolName
					+ "\\User\\"+loginCode+"\\"+menuCode+".xml";
					f = new File(configPath);
				}
				if((request.getParameter("menuCode") != null 
						&& !request.getParameter("menuCode").equals(null)) || !f.exists()){
					configPath = path + "\\dashlet\\"+poolName
					+ "\\profile\\"+profileCode+"\\"+menuCode+".xml";
					f = new File(configPath);
					//logger.info("Config "+configPath);
					if(!f.exists()){
						configPath = path + "\\dashlet\\default\\"
						+ "profile\\"+profileCode+"\\"+menuCode+".xml";
						f = new File(configPath);
						if(!f.exists())
						{
							configPath = path + "\\dashlet\\"+poolName+"\\"
							+ "profile\\2\\"+menuCode+".xml";
							f = new File(configPath);
							
							if(!f.exists())
							{
								configPath = path + "\\dashlet\\default\\"
								+ "profile\\2\\"+menuCode+".xml";
							}
						}
					}
				}
				document = new XMLReaderWriter().parse(new File(configPath));
				//logger.info("------------2------------- "+configPath);
				result = processDashletConfig(document, request);
			}catch(Exception e){				
				e.printStackTrace();
			}
			try{
				configPath = path + "\\dashlet\\"+poolName+"\\"
				+ "profile\\"+profileCode+"\\profileDashletList.xml";
				f = new File(configPath);
				if(!f.exists()){
					configPath = path + "\\dashlet\\default\\"
					+ "profile\\"+profileCode+"\\profileDashletList.xml";
					f = new File(configPath);
					if(!f.exists()){
						configPath = path + "\\dashlet\\"+poolName+"\\"
						+ "profile\\2\\profileDashletList.xml";
						f = new File(configPath);
						if(!f.exists())
						{
							configPath = path + "\\dashlet\\default\\"
							+ "profile\\2\\profileDashletList.xml";
						}
					}
				}
				document = new XMLReaderWriter().parse(new File(configPath));
				DashletProfileSettingsModel model = new DashletProfileSettingsModel();
				model.initiate(context, session);
				profileDashletList = model.processDashletList(document);
				configPath = path + "\\Dashlet\\default\\dashletSettings.xml";		
				document = new XMLReaderWriter().parse(new File(configPath));
				result1 = model.processDashletList(document);
				model.terminate();
			} catch (Exception e) {
				//logger.error("Error while reading XML in result path = "+configPath);
				e.printStackTrace();
			}
			String[][] tempObj = null;
			Vector v = new Vector();
			if(result != null && result.length > 0
					&& profileDashletList != null && profileDashletList.length > 0){
				if(profileDashletList.length > result.length){
					for (int i = 0; i < result1.length; i++) {
						for (int k = 0; k < profileDashletList.length; k++) {
							for (int j = 0; j < result.length; j++) {
								if (result[j][0].equals(profileDashletList[k][0])
										&& profileDashletList[k][0].equals(result1[i][0])) {
										tempObj = new String[1][8];
										tempObj[0][0] = "" + result[j][0];//code
										tempObj[0][1] = "" + result1[i][1];//name
										tempObj[0][2] = "" + result1[i][2];//link
										tempObj[0][3] = "" + result[j][1];//rows
										tempObj[0][4] = "" + result[j][2];//columns
										tempObj[0][5] = "" + result[j][3];// dashlet id
										tempObj[0][6] = "" + result[j][4];// dashlet width
										tempObj[0][7] = "" + result1[i][5];// Image									 
										v.add(tempObj);
								}
							}
						}
					}
				}
				else{
					for (int i = 0; i < result1.length; i++) {
						for (int k = 0; k < result.length; k++) {
							for (int j = 0; j < profileDashletList.length; j++) {
								if (result[k][0].equals(profileDashletList[j][0])
										&& profileDashletList[j][0].equals(result1[i][0])) {
										tempObj = new String[1][8];
										tempObj[0][0] = "" + result[k][0];//code
										tempObj[0][1] = "" + result1[i][1];//name
										tempObj[0][2] = "" + result1[i][2];//link
										tempObj[0][3] = "" + result[k][1];//rows
										tempObj[0][4] = "" + result[k][2];//columns
										tempObj[0][5] = "" + result[k][3];//dashlet id
										tempObj[0][6] = "" + result[j][4];// dashlet width
										tempObj[0][7] = "" +result1[i][5];//Image							
										v.add(tempObj);
								}
							}
						}
					}
				}
			}
			//final object containing all the dashlets 
			String[][] finalObject = new String[v.size()][8];
			for (int i = 0; i < v.size(); i++) {
				tempObj = (String [][])v.get(i);
				finalObject[i] = tempObj[0];
			}
			/* following code sorts the dashlets row and columns wise */
			String[][] sorted = sort(finalObject);
			int cols = 0;
			int rows=0;
			for (int i = 0; i < sorted.length; i++) {
				if(cols != 2){
					if(Integer.parseInt(sorted[i][4]) == 1)
						cols=1;
					else if(Integer.parseInt(sorted[i][4]) == 2 && cols == 1)
						cols=2;
					else
						cols=1;
				}
			}
			for (int i = 0; i < sorted.length; i++) {
				if(cols==1){
					sorted[i][3]=String.valueOf(rows+1);
					sorted[i][4]=String.valueOf(1);
					rows++;
				}
				else if(cols==2){
					if(i%2 == 0){
						sorted[i][3]=String.valueOf(rows+1);
						sorted[i][4]="1";
					}
					else{
						sorted[i][3]=String.valueOf(rows+1);
						sorted[i][4]="2";
						rows++;
					}
				}
			}
			v = new Vector();
			if(sorted != null && sorted.length > 0)
			{
				if(sorted[sorted.length-1][4].equals("1") && cols==2)
					rows++;
			}
			v.add(sorted);
			v.add(rows);
			v.add(cols);
		/*	
	 	System.out.println("sorted.length------------------------------------------------------"+sorted.length);
			 for (int i = 0; i < sorted.length; i++) {
				for (int j = 0; j < sorted[0].length; j++) {
					System.out.println("sorted[i][j]-----------------"+sorted[i][j]);
				}
			}  */
			
			request.setAttribute("configObj", sorted);
			request.setAttribute("dashletmenuCode", menuCode);
			return v;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String[][] sort(String[][] finalObject) {
		try {
			String obj[][] = new String[1][7];
			for (int i = 0; i < finalObject.length; i++) {
					for (int j = i+1; j < finalObject.length; j++) {
						int a = Integer.parseInt(String.valueOf(finalObject[i][3]));//row
						int b = Integer.parseInt(String.valueOf(finalObject[j][3]));//row+1
						int x = Integer.parseInt(String.valueOf(finalObject[i][4]));//col
						int y = Integer.parseInt(String.valueOf(finalObject[j][4]));//col+1
						if (b < a) 
						{
							obj[0] = finalObject[j];
							finalObject[j]= finalObject[i];
							finalObject[i] = obj[0];
						}
						if (y < x && b == a) 
						{
							obj[0] = finalObject[j];
							finalObject[j] = finalObject[i];
							finalObject[i] = obj[0];
						}
					}
				}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return finalObject;
	}

	public Vector getMypageDashletConfig(String menuCode, 
			String profileCode, String loginCode,HttpServletRequest request) {

		//logger.info("------------1------------- "+menuCode);
		//logger.info("-----request.getParameter(menuCode)------- "+request.getParameter("menuCode"));
		String path = ResourceBundle.getBundle("globalMessages").getString("data_path");
		//logger.info("String path value "+path);
		try {
			String poolName = "" + session.getAttribute("session_pool");
			String configPath = "";
			Document document = null;
			Object[][] result = null;
			Object[][] result1 = null;
			//Object [][] profileDashletList = null;
			File f = null;

			try{
				configPath = path + "\\dashlet\\"+poolName+"\\"
				+ "profile\\"+profileCode+"\\profileDashletList.xml";
				f = new File(configPath);
				if(!f.exists())
				{
					configPath = path + "\\dashlet\\default\\"
					+ "profile\\"+profileCode+"\\profileDashletList.xml";
					f = new File(configPath);
					if(!f.exists())
					{
						configPath = path + "\\dashlet\\"+poolName+"\\"
						+ "profile\\2\\profileDashletList.xml";
						f = new File(configPath);
						if(!f.exists())
						{
							configPath = path + "\\dashlet\\default\\"
							+ "profile\\2\\profileDashletList.xml";
						}
					}
				}
				
				System.out.println("configPath-----------------------------"+configPath);
				
				document = new XMLReaderWriter().parse(new File(configPath));
				DashletProfileSettingsModel model = new DashletProfileSettingsModel();
				model.initiate(context, session);
				result1 = model.processDashletList(document);
				/*configPath = path + "\\Dashlet\\default\\dashletSettings.xml";
				
				logger.info("------------3------------- "+configPath);
				
				document = new XMLReaderWriter().parse(new File(configPath));
				result1 = model.processDashletList(document);*/
				model.terminate();
			} catch (Exception e) {
				//logger.error("Error while reading XML in result path = "+configPath);
				e.printStackTrace();
			}
			////////////////
		/*	
			for (int i = 0; i < result1.length; i++) {
				for (int j = 0; j < result1[0].length; j++) {
					System.out.println("result1[i][j]              "+result1[i][j]);
				}
			}*/
			
			String dashletSettingQuery = " SELECT  DASHLET_CODE,DASHLET_NAME,DASHLET_LINK,DASHLET_TYPE,DASHLET_HDR_IMAGE "
							+" FROM HRMS_DASHLET_SETTINGS "
							+" WHERE IS_RELEASED='Y' "
							+"ORDER BY DASHLET_CODE ";
			
			result =getSqlModel().getSingleResult(dashletSettingQuery);
			
			
			String[][] tempObj = null;
			Vector v = new Vector();
			if(result != null && result.length > 0
					&& result1 != null && result1.length > 0)
			{
				 
					//System.out.println("In if loop--------------");
					for (int i = 0; i < result.length; i++) {
						for (int j = 0; j < result1.length; j++) {
							 
							/*	System.out.println("result[i][0]  "+result[i][0]);
								System.out.println("result1[j][0]  "+result1[j][0]);*/
								
								 if (String.valueOf(result[i][0]).trim().equals(String.valueOf(result1[j][0]).trim())) {  
									// System.out.println("inner if loop--------------------");
										tempObj = new String[1][8];
										tempObj[0][0] = "" + result[i][0];//code
										tempObj[0][1] = "" + result[i][1];//name
										tempObj[0][2] = "" + result[i][2];//link
										tempObj[0][3] = "" + 3;//rows
										tempObj[0][4] = "3" ;//columns
										tempObj[0][5] = "" + result[i][0];// dashlet id
										tempObj[0][6] = "33,33,33" ;//+ result1[i][4];// dashlet width
										tempObj[0][7] = "" + result[i][4];// Image
									 
										v.add(tempObj);
								  }  
							 
						}
					}
				 
			 
			}
			
			
			
			//final object containing all the dashlets 
			String[][] finalObject = new String[v.size()][8];
			for (int i = 0; i < v.size(); i++) {
				tempObj = (String [][])v.get(i);
				finalObject[i] = tempObj[0];
				
			}
		/*	
			System.out.println("finalObject.length-----------"+finalObject.length);
			
			for (int i = 0; i < finalObject.length; i++) {
				
				for (int j = 0; j < finalObject[0].length; j++) {
					System.out.println("finalObject[i][j]-------------"+finalObject[i][j]);
				}
			}*/
			
			
			/*
			 * following code sorts the dashlets row and columns wise
			 */ 
			 
			/*String[][] sorted = sort(finalObject);
			int cols = 0;
			int rows=0;
			for (int i = 0; i < sorted.length; i++) {
				if(cols != 2)
				{
					if(Integer.parseInt(sorted[i][4]) == 1)
						cols=1;
					else if(Integer.parseInt(sorted[i][4]) == 2 && cols == 1)
						cols=2;
					else
						cols=1;
				}
			}
			for (int i = 0; i < sorted.length; i++) {
				if(cols==1)
				{
					sorted[i][3]=String.valueOf(rows+1);
					sorted[i][4]=String.valueOf(1);
					rows++;
				}
				else if(cols==2)
				{
					if(i%2 == 0)
					{
						sorted[i][3]=String.valueOf(rows+1);
						sorted[i][4]="1";
					}
					else
					{
						sorted[i][3]=String.valueOf(rows+1);
						sorted[i][4]="2";
						rows++;
					}
				}
			}
			v = new Vector();
			if(sorted != null && sorted.length > 0)
			{
				if(sorted[sorted.length-1][4].equals("1") && cols==2)
					rows++;
			}
			v.add(sorted);
			v.add(rows);
			v.add(cols);
			
			System.out.println("sorted.length------------------------------------------------------"+sorted.length);
			for (int i = 0; i < sorted.length; i++) {
				for (int j = 0; j < sorted[0].length; j++) {
					System.out.println("sorted[i][j]-----------------"+sorted[i][j]);
				}
			}*/
			 
			request.setAttribute("configObj", finalObject);
			
			return v;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**Method Name: getD1PortalLink()
	 * @purpose Gives values from HRMS_PORTAL_APPS Table 
	 * 			Which shows Application Name on Left Menu
	 * @param request
	 * @return String [][]
	 */
	public String[][] getD1PortalLink(HttpServletRequest request, HomePage home) {
		String[][] strObj=null;
		try {
			String divQuery="SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="+home.getUserEmpId();
			
			Object [][] empDivObj= getSqlModel().getSingleResult(divQuery);
			String query = "SELECT LINK_ID,LINK_NAME,LINK_IMAGE, LINK_URL FROM HRMS_PORTAL_APPS "
							+ " WHERE IS_ACTIVE='Y' AND"
							+ " (','||LINK_FOR_DIVISION||',' LIKE '%,"+empDivObj[0][0]+",%' OR LINK_FOR_DIVISION IS NULL)"
							+ " ORDER BY LINK_SEQUENCE";
			Object[][] d1LinkObj = getSqlModel().getSingleResult(query);
			if (d1LinkObj != null && d1LinkObj.length > 0) {
				strObj = new String[d1LinkObj.length][4];
				for (int i = 0; i < d1LinkObj.length; i++) {
					strObj[i][0] = String.valueOf(d1LinkObj[i][0]); //APPL_Code
					strObj[i][1] = String.valueOf(d1LinkObj[i][1]); //APPL_Name
					strObj[i][2] = String.valueOf(d1LinkObj[i][2]); //APPL_Image
					strObj[i][3] = String.valueOf(d1LinkObj[i][3]); //APPL_URL
				}//end for
			}//end if
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return strObj;
	}
	
}
