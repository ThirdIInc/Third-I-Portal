 /**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.ApplicationStudio.DashletProfileSettings;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.XMLReaderWriter;

import com.crystaldecisions.xml.serialization.XMLObjectSerializer;

/**
 * @author Reeba_Joseph
 * Modified by Pankaj_Jain
 *
 */
public class DashletProfileSettingsModel extends ModelBase { 
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(DashletProfileSettingsModel.class);

	public Object[][] processDashletList(Document document) {
		// TODO Auto-generated method stub
		String[][] list = null;
		try{
			List node = document.selectNodes("//APPLICATIONSTUDIO//DASHLET");
			list = new String[node.size()][6];
			int count = 0;
			
			for (Iterator iter = node.iterator(); iter.hasNext();) {
				Element node2 = (Element) iter.next();
				list[count][0] = node2.attributeValue("code");
				list[count][1] = node2.attributeValue("name");
				list[count][2] = node2.attributeValue("link");
				list[count][3] = node2.attributeValue("position");
				list[count][4] = node2.attributeValue("type");
				list[count][5] = node2.attributeValue("headerImage");
				count++;
			}// end of loop
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {

		}
		return list; // Return result 1
	}

	public void saveDashletProSettings(DashletProfileSettings profileSettings,
			String path, Object[][] dashObj) {
		// TODO Auto-generated method stub
		logger.info("profile name ====== "+profileSettings.getProfileName());
		if (!(path == null || path.equals("") || path.equals(null)))
			path = "\\" + path;
		
		try {
			new XMLReaderWriter().write(buildDashletProSettings("APPLICATIONSTUDIO", profileSettings.getProfileName(),  
					profileSettings, dashObj), path);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private Document buildDashletProSettings(String head, String subhead,  
			DashletProfileSettings profileSettings, Object[][] dashcode) {
		// TODO Auto-generated method stub
		Document document = DocumentHelper.createDocument();
		Element header;
		Element element;
		Element root = document.addElement(head);
		if(dashcode != null && dashcode.length > 0){
			for (int i = 0; i < dashcode.length; i++) {
			if(String.valueOf(dashcode[i][2]).equals("Y"))
			header = root.addElement("DASHLET").addAttribute("name", ""+dashcode[i][1]).addAttribute("code",
					""+dashcode[i][0]).addAttribute("link", "")
					.addAttribute("position", "").addAttribute("type", "");
			}
		}
		return document;
	}

	public String[][] setProfileSettings(Document document, DashletProfileSettings profileSettings) {
		// TODO Auto-generated method stub
		String[][] list = null;
		try{
			List nodes = document
			.selectNodes("//APPLICATIONSTUDIO/DASHLET");
			list = new String[nodes.size()][3];
			for (Iterator iter = nodes.iterator(); iter.hasNext();) {
				Element node = (Element) iter.next();
				list[0][1] = node.attributeValue("name");
				list[0][2] = node.attributeValue("code");
			}// end of loop
		}catch(Exception e){
			e.printStackTrace();
		} finally {

		}
		return list;
	}

}
