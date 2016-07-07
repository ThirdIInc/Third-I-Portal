package org.paradyne.model.ApplicationStudio;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.ApplicationStudio.NavigationPanelSettings;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.XMLReaderWriter;

/**
 * @author Bhushan Dasare
 * @date Dec 22, 2008
 */

/**
 * To develop business logic To design navigation panel for every form
 */

public class NavigationPanelSettingsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NavigationPanelSettingsModel.class);
	int setCode = 0;

	public Document buildDocument(String modCode, String formCode,String tempCode, String btnSelected) {
		Document document = null;
		Element root = null, formH = null, data = null, tempH = null, modeH = null, btnH = null;

		try {			
			document = DocumentHelper.createDocument();
			root = document.addElement("NAVIGATION_PANEL");
			tempH = root.addElement("TEMPLATE").addAttribute("tempCode", tempCode);
			
			HashMap map = new HashMap();
			map.put("1", "");
			map.put("2", "");
			map.put("3", "");
			map.put("4", "");
			map.put("5", "");
			map.put("6", "");
			map.put("7", "");
			map.put("8", "");
			map.put("9", "");
			map.put("10", "");
			
			Object[] btnObj = btnSelected.split("@");
			
			for(int i = 0; i < btnObj.length; i++) {
				Object[] btns = String.valueOf(btnObj[i]).split(",");			
				String val = (String)map.get(String.valueOf(btns[0]));			
				map.put(String.valueOf(btns[0]), val+","+String.valueOf(btns[1]));
			}
			
			for(int j = 1; j <= map.size(); j++) {
				if(!map.get(String.valueOf(j)).equals("")) 
				{
					modeH = tempH.addElement("MODE").addAttribute("modeCode", String.valueOf(j));				
					String btn[] = String.valueOf(map.get(String.valueOf(j))).split(",");

					for(int i = 0; i < btn.length; i++) {					
						if(!String.valueOf(btn[i]).equals("")) {							
							btnH = modeH.addElement("BUTTON").addAttribute("btnCode", btn[i]);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return document;
	}
	
	public Object[][] getButtonMaster(String fileName, NavigationPanelSettings bean, HttpServletRequest request) {
		Object[][] btnMastList = null;
		
		try {
			String btnFileName = fileName + "buttons.xml";
			File btnFile = new File(btnFileName);
			
			if (btnFile.exists()) {
				Document document = new XMLReaderWriter().parse(btnFile);

				List btnNodes = document.selectNodes("//BUTTON_MASTER/BUTTON");
				btnMastList = new Object[btnNodes.size()][4];
				int cnt = 0;
				
				for (Iterator<Element> it = btnNodes.iterator(); it.hasNext();) {
					Element btnEl = (Element) it.next();
					btnMastList[cnt][0] = String.valueOf(btnEl.attributeValue("btnCode"));
					btnMastList[cnt][1] = "N"; // Selected
					btnMastList[cnt][2] = String.valueOf(btnEl.attributeValue("btnName"));
					btnMastList[cnt][3] = String.valueOf(btnEl.attributeValue("btnOrder"));
					cnt++;
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return btnMastList;
	}
	
	public String getButtons(String fileName, NavigationPanelSettings bean, HttpServletRequest request) {
		String message = "";
				
		try {
			String fileCode = bean.getFormCode();
			String navFileName = fileName + fileCode + ".xml";
			File navFile = new File(navFileName);
			
			if (navFile.exists()) {
				if(navFile.length() == 0) {
					message = getRecordFromDb(fileName, bean, request);
				} else {
					getRecordFromNavXml(fileName, bean, request);
				}
			} else {
				message = getRecordFromDb(fileName, bean, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "File not found.";
		}
		return message;
	}
	
	public Object[][] getTemplate(NavigationPanelSettings bean, String fileName) {
		Object[][] templateObj = null;
		Object[][] templateDetailsObj = null;
		
		try {
			String fileCode = bean.getFormCode();
			String navFileName = fileName + fileCode + ".xml";
			File navFile = new File(navFileName);
			
			if (navFile.exists() && navFile.length() > 0) {
				Document document = null;
				try {
					document = new XMLReaderWriter().parse(navFile);
				} catch (Exception e) {
					logger.error(e);
				}
				
				List tempNodes = document.selectNodes("//NAVIGATION_PANEL/TEMPLATE");
				
				for (Iterator<Element> tempIterator = tempNodes.iterator(); tempIterator.hasNext();) {
					String tempCode = tempIterator.next().attributeValue("tempCode");
					bean.setTempCode(tempCode);
				}
			}
			
			String templateSql = " SELECT TEMPLATE_MODE_CODE, TEMPLATE_BUTTON FROM HRMS_TEMPLATE_DTL WHERE TEMPLATE_CODE = " + bean.getTempCode();
			templateObj = getSqlModel().getSingleResult(templateSql);
			int cnt = 0;
			
			for (int i = 0; i < templateObj.length; i++) {
				String str = String.valueOf(templateObj[i][1]);
				String str1[] = str.split(",");
				for (int j = 0; j < str1.length; j++) {
					cnt++;
				}
			}
			
			templateDetailsObj = new Object[cnt][2];
			int count = 0;
			
			for (int i = 0; i < templateObj.length; i++) {
				String str = String.valueOf(templateObj[i][1]);
				String str1[] = str.split(",");
				for (int j = 0; j < str1.length; j++) {
					templateDetailsObj[count][0] = str1[j];
					templateDetailsObj[count][1] = String.valueOf(templateObj[i][0]);
					count++;
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return templateDetailsObj;
	}
	
	public Object[][] getNavigation(String fileName, NavigationPanelSettings bean, HttpServletRequest request, int btnLenth) {
		Object[][] navPanList = null;
		
		try {
			String fileCode = bean.getFormCode();
			String tempCode = bean.getTempCode();
			String navFileName = fileName + fileCode + ".xml";
			File navFile = new File(navFileName);

			if (navFile.exists()) {
				Document document = null;
				try {
					document = new XMLReaderWriter().parse(navFile);
				} catch (Exception e) {
					logger.error(e);
				}
				
				List modeNodes = document.selectNodes("//NAVIGATION_PANEL/TEMPLATE[@tempCode='" + tempCode + "']/MODE");

				navPanList = new Object[btnLenth][2];
				int cnt = 0;
				
				for (Iterator<Element> modeIterator = modeNodes.iterator(); modeIterator.hasNext();) {
					String modeCode = modeIterator.next().attributeValue("modeCode");						
					List btnNodes = document.selectNodes("//NAVIGATION_PANEL/TEMPLATE[@tempCode='" + tempCode + "']/MODE[@modeCode='" + modeCode + "']/BUTTON");
					
					for (Iterator<Element> btnIterator = btnNodes.iterator(); btnIterator.hasNext();) {
						navPanList[cnt][0] = btnIterator.next().attributeValue("btnCode");
						navPanList[cnt][1] = modeCode;
						cnt++;
					}
				}				
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return navPanList;
	}
	
	public String getRecordFromDb(String fileName, NavigationPanelSettings bean, HttpServletRequest request) {
		String tempCode = bean.getTempCode();
		String message = "";
		
		if(tempCode == null || tempCode.equals("0")) {
			message = "Please select a template to assign navigation buttons";
		} else {
			Object[][] btnList = null;
			Object[][] templateList = getTemplate(bean, fileName);
			Object[][] btnMastList = getButtonMaster(fileName, bean, request);
			
			HashMap hm_btnName = new HashMap();
			HashMap hm_btnSeq = new HashMap();
			
			for (int i = 0; i < btnMastList.length; i++) {
				hm_btnName.put(btnMastList[i][0], btnMastList[i][2]);
				hm_btnSeq.put(btnMastList[i][0], btnMastList[i][3]);
			}
			
			if (templateList != null && templateList.length > 0) {
				if (btnMastList != null && btnMastList.length > 0) {
				Object[][] btnListObj = new Object[templateList.length][5];
					for (int i = 0; i < templateList.length; i++) {
						String btnCode = String.valueOf(templateList[i][0]);
						for (int j = 0; j < btnMastList.length; j++) {
							if (btnCode.equals(String.valueOf(btnMastList[j][0]))) {
								btnListObj[i][0] = btnCode;
								btnListObj[i][1] = "N"; // Selected
								btnListObj[i][2] = String.valueOf(btnMastList[j][2]); // btnName
								btnListObj[i][3] = String.valueOf(btnMastList[j][3]); // btnOrder
								btnListObj[i][4] = String.valueOf(templateList[i][1]); // btnOrder
								break;
							} else {
								btnListObj[i][0] = btnCode;
								btnListObj[i][1] = "N"; // Selected
								btnListObj[i][2] = String.valueOf(btnMastList[j][2]); // btnName
								btnListObj[i][3] = String.valueOf(btnMastList[j][3]); // btnOrder
								btnListObj[i][4] = String.valueOf(templateList[i][1]); // btnOrder
							}
						}
					}
					btnList = btnListObj;
				} 
				bean.setListButtons(true);
			}
			request.setAttribute("btnList", btnList);
		}
		return message;
	}
	
	public void getRecordFromNavXml(String fileName, NavigationPanelSettings bean, HttpServletRequest request) {
		Object[][] btnList = null;
		Object templateList[][] = getTemplate(bean, fileName);
		Object[][] btnMastList = getButtonMaster(fileName, bean, request);
		Object[][] navPanList = getNavigation(fileName, bean, request,templateList.length);
		
		HashMap hm_btnName = new HashMap();
		HashMap hm_btnSeq = new HashMap();
		
		for (int i = 0; i < btnMastList.length; i++) {
			hm_btnName.put(btnMastList[i][0], btnMastList[i][2]);
			hm_btnSeq.put(btnMastList[i][0], btnMastList[i][3]);
		}
		
		if(templateList != null && templateList.length > 0) {
			if (btnMastList != null && btnMastList.length > 0) {
				Object[][] btnListObj = new Object[templateList.length][5];
				
				for (int i = 0; i < templateList.length; i++) {
					String btnCode = String.valueOf(templateList[i][0]);
					String modCode = String.valueOf(templateList[i][1]);
					
					for (int j = 0; j < navPanList.length; j++) {
						if( btnCode.equals(String.valueOf(navPanList[j][0])) && modCode.equals(String.valueOf(navPanList[j][1]))) {
							btnListObj[i][0] = btnCode;
							btnListObj[i][1] = "Y"; // Selected
							btnListObj[i][2] = hm_btnName.get(btnCode); // btnName
							btnListObj[i][3] = hm_btnSeq.get(btnCode); // btnOrder
							btnListObj[i][4] = String.valueOf(templateList[i][1]); // btnOrder
							break;
						} else {
							btnListObj[i][0] = btnCode;
							btnListObj[i][1] = "N"; // Selected
							btnListObj[i][2] = hm_btnName.get(btnCode);// btnName
							btnListObj[i][3] = hm_btnSeq.get(btnCode); // btnOrder
							btnListObj[i][4] = String.valueOf(templateList[i][1]); // btnOrder
						}
					}
				}
				btnList = btnListObj;
			}
			bean.setListButtons(true);
		}
		request.setAttribute("btnList", btnList);
	}

	public String save(NavigationPanelSettings bean, String fileName, HttpServletRequest request) {
		String message = "";
		
		try {
			String modCode = bean.getModCode();
			String formCode = bean.getFormCode();
			String tempCode = bean.getTempCode();
			String btnSelected = bean.getBtnSelected();
			
			Document document = buildDocument(modCode, formCode,tempCode, btnSelected);
			new XMLReaderWriter().write(document, fileName);
			message = "Record saved successfully.";
		} catch (Exception e) {
			logger.error(e);
			message = "Record cannot be saved.";
		}
		return message;
	}
}