/**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.ApplicationStudio.DefaultDashletConfig;
import org.paradyne.bean.ApplicationStudio.UserDashletConfig;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.XMLReaderWriter;

/**
 * @author Reeba_Joseph
 * 
 */
public class UserDashletConfigModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DefaultDashletConfigModel.class);

	/**
	 * To generate the menu tabs for homepages
	 * 
	 * @param dashletConfig
	 * @return String
	 */
	public String[][] getMenuList(UserDashletConfig dashletConfig) {
		// TODO Auto-generated method stub
		String query = " SELECT HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME, HRMS_MENU.MENU_NAME) FROM HRMS_MENU "
				+ " LEFT JOIN HRMS_MENU_CLIENT ON(HRMS_MENU_CLIENT.MENU_CODE=HRMS_MENU.MENU_CODE)"
				+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' "
				+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' OR PROFILE_GENERAL_FLAG ='Y')) "
				+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE = "
				+ dashletConfig.getUserProfileId()
				+ " AND MENU_PARENT_CODE= 0 AND HRMS_MENU.MENU_CODE NOT IN(35,408,414) "
				+ " ORDER BY MENU_TABORDER,HRMS_MENU.MENU_CODE ";
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
	}// end of getMenuList method

	/**
	 * To retrieve dashlets available for the selected profile
	 * 
	 * @param document
	 * @param dashletConfig
	 * @return Object
	 */
	public Object[][] setProfileDashlets(Document document,
			UserDashletConfig dashletConfig) {
		// TODO Auto-generated method stub
		String[][] list = null;

		try {
			List node1 = document.selectNodes("//APPLICATIONSTUDIO/DASHLET");
			list = new String[node1.size()][2];
			int count = 0;
			for (Iterator itert = node1.iterator(); itert.hasNext();) {
				Element node2 = (Element) itert.next();
				list[count][0] = node2.attributeValue("code");
				list[count][1] = node2.attributeValue("name");
				count++;
			}// end of loop

		} catch (Exception e) {
			logger.error("Error in setProfileDashlets method: " + e);
			e.printStackTrace();
		} finally {

		}
		return list;
	}// end of setProfileDashlets method

	/**
	 * Saving the set configuration for each homepage.
	 * 
	 * @param dashletConfig
	 * @param dashCode
	 * @param rows
	 * @param cols
	 */
	public void saveDashletConfig(UserDashletConfig dashletConfig,
			String[] dashCode, String[] rows, String[] cols) {
		// TODO Auto-generated method stub
		try {

			String[] dashCodeObj = null;
			String[] rowObj = null;
			String[] colObj = null;
			String[] rowColWidth = new String[3];
			String configPath = ResourceBundle.getBundle("globalMessages")
					.getString("data_path")
					+ "\\dashLet\\"
					+ session.getAttribute("session_pool")
					+ "\\User\\"
					+ dashletConfig.getUserLoginCode()
					+ "\\"
					+ dashletConfig.getHMenuCode() + ".xml";
			int i = 0;
			int rowCount = 0;
			int colCount = 0;
			for (i = 0; i < dashCode.length; i++) {// loop x
				if (dashCode[i].equals(""))
					break;
				else {
					if (rowCount < Integer.parseInt(rows[i]))
						rowCount = Integer.parseInt(rows[i]);
					if (colCount < Integer.parseInt(cols[i]))
						colCount = Integer.parseInt(cols[i]);
				}// end of else
			}// end of loop x
			rowColWidth[0] = "" + rowCount;
			rowColWidth[1] = "" + colCount;
			rowColWidth[2] = dashletConfig.getWidthCol1();
			if (!dashletConfig.getWidthCol1().equals(""))
				rowColWidth[2] += "," + dashletConfig.getWidthCol2();
			dashCodeObj = new String[i];
			rowObj = new String[i];
			colObj = new String[i];
			for (int j = 0; j < i; j++) {// loop y
				dashCodeObj[j] = dashCode[j];
				rowObj[j] = rows[j];
				colObj[j] = cols[j];
			}// end of loop y
			new XMLReaderWriter().write(buildUserDashletConfig(
					"APPLICATIONSTUDIO", dashCodeObj, rowObj, colObj,
					rowColWidth), configPath);
		} catch (Exception e) {
			logger.error("Error in saveDashletConfig method: " + e);
			e.printStackTrace();
		}
	}// end of saveDashletConfig method

	/**
	 * Writing XMl with saved configuration
	 * 
	 * @param head
	 * @param dashCode
	 * @param rows
	 * @param cols
	 * @param rowColWidth
	 * @return document:xml
	 */
	private Document buildUserDashletConfig(String head, String[] dashCode,
			String[] rows, String cols[], String[] rowColWidth) {
		// TODO Auto-generated method stub
		Document document =null;
		try {
			document = DocumentHelper.createDocument();
			Element header;
			Element element;
			Element root = document.addElement(head);
			if (dashCode != null && dashCode.length > 0) {
				header = root.addElement("DASHLET")
						.addAttribute("name", "HOME").addAttribute("rows",
								rowColWidth[0]).addAttribute("columns",
								rowColWidth[1]).addAttribute("width",
								rowColWidth[2]);
				for (int i = 0; i < dashCode.length; i++) {// loop x
					element = header.addElement("HOME").addAttribute("code",
							dashCode[i]).addAttribute("rowId", rows[i])
							.addAttribute("columnId", cols[i]);
				}// end of loop x
			}// end of if
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}// end of buildUserDashletConfig method

	/**
	 * 
	 * @param document
	 * @param req
	 * @return String
	 * @throws Exception
	 */
	public String[][] processDashletConfig(Document document,
			HttpServletRequest req) throws Exception {
		// TODO Auto-generated method stub
		String[][] config = null;
		String[][] dashlet = null;
		try {
			DefaultDashletConfig bean = new DefaultDashletConfig();
			List nodes = document
					.selectNodes("//APPLICATIONSTUDIO/DASHLET[@name='HOME']");
			dashlet = new String[nodes.size()][5];
			for (Iterator iter = nodes.iterator(); iter.hasNext();) {// loop
				// x
				Element node = (Element) iter.next();
				dashlet[0][1] = node.attributeValue("rows");
				dashlet[0][2] = node.attributeValue("columns");
				dashlet[0][3] = node.attributeValue("width");
			}// end of loop x

			List node1 = document
					.selectNodes("//APPLICATIONSTUDIO/DASHLET[@name='HOME']/HOME");
			config = new String[node1.size()][3];
			int count = 0;
			int blnkCnt = 0;

			for (Iterator iter = node1.iterator(); iter.hasNext();) {// loop
				// y
				Element node2 = (Element) iter.next();
				config[count][0] = node2.attributeValue("code");
				config[count][1] = node2.attributeValue("rowId");
				config[count][2] = node2.attributeValue("columnId");
				if (!("" + config[count][0]).equals(""))
					blnkCnt++;
				count++;
			}// end of loop y
			if (dashlet.length > 0) {
				dashlet[0][4] = "" + blnkCnt;

			}// end of if
			req.setAttribute("dashletSet", dashlet);
		} catch (Exception e) {
			logger.error("Error in processDashletConfig method: " + e);
			e.printStackTrace();
		} finally {

		}
		return config; // returns result 1
	}// end of processDashletConfig method

	/**
	 * Setting values on the page after saving onload
	 * 
	 * @param menuCode
	 * @param profileCode
	 * @param loginCode
	 * @param request
	 * @return Vector
	 */
	public Vector getDashletConfig(String menuCode, String profileCode,
			String loginCode, HttpServletRequest request) {
		logger.info("-----menuCode------ " + menuCode);
		String path = ResourceBundle.getBundle("globalMessages").getString(
				"data_path");
		try {
			String poolName = "" + session.getAttribute("session_pool");
			String configPath = "";
			Document document = null;
			Object[][] result = null;
			Object[][] result1 = null;
			Object[][] profileDashletList = null;
			File f = null;
			try {
				configPath = path + "\\dashlet\\" + poolName + "\\User\\"
						+ loginCode + "\\" + menuCode + ".xml";
				f = new File(configPath);
				if (f.exists())
					document = new XMLReaderWriter()
							.parse(new File(configPath));
				else {
					configPath = path + "\\dashlet\\" + poolName
							+ "\\profile\\" + profileCode + "\\" + menuCode
							+ ".xml";
					f = new File(configPath);
					if (f.exists())
						document = new XMLReaderWriter().parse(new File(
								configPath));
					else {
						configPath = path + "\\dashlet\\default\\"
								+ "profile\\" + profileCode + "\\" + menuCode
								+ ".xml";
						f = new File(configPath);
						if(!f.exists())
						{
							if(!f.exists())
							{
								configPath = path + "\\dashlet\\" + poolName
								+ "\\profile\\2\\" + menuCode
								+ ".xml";
								f=new File(configPath);
								if(!f.exists())
								{
									configPath = path + "\\dashlet\\default\\"
									+ "profile\\2\\" + menuCode
									+ ".xml";
								}
							}
						}
						document = new XMLReaderWriter().parse(new File(
								configPath));
					}// end else

				}// end else
				logger.info("----configPath------ " + configPath);
				result = processDashletConfig(document, request);
			} catch (Exception e) {
				logger.error("Error while reading XML in result path = "
						+ configPath);
				e.printStackTrace();
			}
			try {
				configPath = path + "\\dashlet\\" + poolName + "\\"
						+ "profile\\" + profileCode
						+ "\\profileDashletList.xml";
				f = new File(configPath);
				if(!f.exists())
				{
					configPath = path + "\\dashlet\\default\\" + "profile\\"
					+ profileCode + "\\profileDashletList.xml";
					
					f = new File(configPath);
					if (!f.exists()) {
						configPath = path + "\\dashlet\\" + poolName + "\\"
						+ "profile\\2\\profileDashletList.xml";
						f = new File(configPath);
						if(!f.exists())
							configPath = path + "\\dashlet\\default\\profile\\2\\profileDashletList.xml";
					}// end of if
				}
				document = new XMLReaderWriter().parse(new File(configPath));
				DashletProfileSettingsModel model = new DashletProfileSettingsModel();
				model.initiate(context, session);
				profileDashletList = model.processDashletList(document);
				configPath = path + "\\Dashlet\\default\\dashletSettings.xml";
				logger.info("----configPath------ " + configPath);
				document = new XMLReaderWriter().parse(new File(configPath));
				result1 = model.processDashletList(document);
				model.terminate();
			} catch (Exception e) {
				logger.error("Error while reading XML in result path = "
						+ configPath);
				e.printStackTrace();
			}
			String[][] tempObj = null;
			Vector v = new Vector();
			if (result != null && result.length > 0
					&& profileDashletList != null
					&& profileDashletList.length > 0) {
				if (profileDashletList.length > result.length) {
					for (int i = 0; i < result1.length; i++) {
						for (int k = 0; k < profileDashletList.length; k++) {
							for (int j = 0; j < result.length; j++) {
								if (result[j][0]
										.equals(profileDashletList[k][0])
										&& profileDashletList[k][0]
												.equals(result1[i][0])) {
									tempObj = new String[1][5];
									tempObj[0][0] = "" + result[j][0];// code
									tempObj[0][1] = "" + result1[i][1];// name
									tempObj[0][2] = "" + result1[i][2];// link
									tempObj[0][3] = "" + result[j][1];// rows
									tempObj[0][4] = "" + result[j][2];// columns
								 	v.add(tempObj);
								}
							}
						}
					}
				} else {
					for (int i = 0; i < result1.length; i++) {
						for (int k = 0; k < result.length; k++) {
							for (int j = 0; j < profileDashletList.length; j++) {
								if (result[k][0]
										.equals(profileDashletList[j][0])
										&& profileDashletList[j][0]
												.equals(result1[i][0])) {
									tempObj = new String[1][5];
									tempObj[0][0] = "" + result[k][0];// code
									tempObj[0][1] = "" + result1[i][1];// name
									tempObj[0][2] = "" + result1[i][2];// link
									tempObj[0][3] = "" + result[k][1];// rows
									tempObj[0][4] = "" + result[k][2];// columns
								
									v.add(tempObj);
								}
							}
						}
					}
				}
			}

			String[][] finalObject = new String[v.size()][5];
			for (int i = 0; i < v.size(); i++) {
				tempObj = (String[][]) v.get(i);
				finalObject[i] = tempObj[0];

			}

			String[][] sorted = sort(finalObject);// call sort method

			int cols = 0;
			int rows = 0;
			for (int i = 0; i < sorted.length; i++) {
				if (cols != 2) {
					if (Integer.parseInt(sorted[i][4]) == 1)
						cols = 1;
					else if (Integer.parseInt(sorted[i][4]) == 2 && cols == 1)
						cols = 2;
					else
						cols = 1;
				}
			}
			for (int i = 0; i < sorted.length; i++) {
				if (cols == 1) {
					sorted[i][3] = String.valueOf(rows + 1);
					sorted[i][4] = String.valueOf(1);
					rows++;
				} else if (cols == 2) {
					if (i % 2 == 0) {
						sorted[i][3] = String.valueOf(rows + 1);
						sorted[i][4] = "1";
					} else {
						sorted[i][3] = String.valueOf(rows + 1);
						sorted[i][4] = "2";
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
			request.setAttribute("configObj", sorted);
			return v;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String[][] sort(String[][] finalObject) {
		// TODO Auto-generated method stub
		try {
			String obj[][] = new String[1][5];
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalObject;	}

}
