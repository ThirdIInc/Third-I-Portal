/**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.ApplicationStudio.DashletSettings;
import org.paradyne.bean.TravelManagement.Master.RoomType;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.XMLReaderWriter;

/**
 * @author Reeba_Joesph
 * modified by Pankaj_Jain
 *
 */
public class DashletSettingsModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(DashletSettingsModel.class);
	
	/**
	 * Method that checks for duplicate entry 
	 * @param dashletSettings
	 * @return : if that name already exist it returns true
	 * else false thus record can be added
	 */
	public boolean checkDuplicate(DashletSettings dashletSettings) {
		boolean result = false;
		String query = "SELECT * FROM  HRMS_DASHLET_SETTINGS WHERE UPPER(DASHLET_NAME) LIKE '"
				+ dashletSettings.getName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;
	}
	
	/**
	 * when the record is modified it checks for 
	 * duplicate whether the new name is already assigned to
	 * selected dashlet
	 * @param dashletSettings
	 * @return
	 */
	public boolean checkDuplicateMod(DashletSettings dashletSettings) {
		boolean result = false;
		String query = "SELECT * FROM  HRMS_DASHLET_SETTINGS WHERE UPPER(DASHLET_NAME) LIKE '"
				+ dashletSettings.getName().trim().toUpperCase()
				+ "' AND DASHLET_CODE not in(" + dashletSettings.getDashletId() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}
	
	/**
	 * Method use to insert the new record in to database
	 * when the record is saved corresponding XML is updated
	 * @param dashletSettings
	 * @param path
	 * @return
	 */
	public boolean addDashletSettings(DashletSettings dashletSettings, String path) {
		// TODO Auto-generated method stub
		if(!checkDuplicate(dashletSettings)){
			//Object to save the data
			Object[][] addObj=new Object[1][5];
			try{		
				addObj[0][0]=dashletSettings.getName().trim();	
				addObj[0][1]=dashletSettings.getLink();	
				addObj[0][2]=dashletSettings.getPosition();
				addObj[0][3]=dashletSettings.getType();
				addObj[0][4]=dashletSettings.getDashletImageName().trim();
			}catch(Exception e){
				e.printStackTrace();
			}	
			String insertQuery = " INSERT INTO HRMS_DASHLET_SETTINGS (DASHLET_CODE, DASHLET_NAME, DASHLET_LINK, DASHLET_POSITION, DASHLET_TYPE,DASHLET_HDR_IMAGE)" 
						 + " VALUES ((SELECT NVL(MAX(DASHLET_CODE),0)+1 from HRMS_DASHLET_SETTINGS), ?, ?, ?, ?,?)";
			boolean result=getSqlModel().singleExecute(insertQuery,addObj);		
			
			if(result){
				// write the corresponding entry to xml
				xml_dashletsettings(path);
				
				// after adding record select dashlets from database
				// and set the list and display again 
				String query = " SELECT MAX(DASHLET_CODE) FROM HRMS_DASHLET_SETTINGS" ;
				Object[][] data = getSqlModel().getSingleResult(query);
				
				String query1 = " SELECT  DASHLET_NAME,DASHLET_LINK,"
							  + " DECODE(DASHLET_POSITION,'F','Fixed','M','Movable'), "
							  + " DECODE(DASHLET_TYPE,'L','List','G','Graph','O','Others'), "
							  + " DASHLET_CODE ,DASHLET_HDR_IMAGE FROM HRMS_DASHLET_SETTINGS WHERE DASHLET_CODE="+String.valueOf(data[0][0]);
				
				Object[][] Data = getSqlModel().getSingleResult(query1);
				
				dashletSettings.setName(checkNull(String.valueOf(Data[0][0])).trim());				
				dashletSettings.setLink(checkNull(String.valueOf(Data[0][1])).trim());			
				dashletSettings.setPosition(checkNull(String.valueOf(Data[0][2])).trim());			
				dashletSettings.setType(checkNull(String.valueOf(Data[0][3])).trim());
				dashletSettings.setDashletId(checkNull(String.valueOf(Data[0][4])).trim());
				dashletSettings.setDashletImageNameItt(checkNull(String.valueOf(Data[0][5])).trim());
				
			}
			return result;
			}else{
				return false;
			}
	}

	/**
	 * to write the dasheltSettings.xml
	 * @param path
	 */
	private void xml_dashletsettings(String path) {
		// TODO Auto-generated method stub
		if (!(path == null || path.equals("") || path.equals(null)))
			path = "\\" + path;
		
		try {
			new XMLReaderWriter().write(buildDashletSettings("APPLICATIONSTUDIO", "DASHLET SETTING"),
					path);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * when the record is saved/modified the xml is rewrite
	 * @param head
	 * @param subhead
	 * @return
	 */
	private Document buildDashletSettings(String head, String subhead) {
		// TODO Auto-generated method stub
		String query = "SELECT  DASHLET_CODE, DASHLET_NAME,DASHLET_LINK,"
					 + " DECODE(DASHLET_POSITION,'F','Fixed','M','Movable'), "
					 + " DECODE(DASHLET_TYPE,'L','List','G','Graph','O','Others') "
					 + " , DASHLET_HDR_IMAGE FROM HRMS_DASHLET_SETTINGS"
					 + " ORDER BY  DASHLET_CODE";
		Object data[][] = getSqlModel().getSingleResult(query);
		Document document = DocumentHelper.createDocument();
		Element header;
		Element element;
		Element root = document.addElement(head);
		if(data != null && data.length > 0){
			// loop that creates nodes and add the data to 
			// xml file
			for (int i = 0; i < data.length; i++) {
				header = root.addElement("DASHLET").addAttribute("code",
						String.valueOf(data[i][0])).addAttribute("name",
						String.valueOf(data[i][1])).addAttribute("link",
						String.valueOf(data[i][2])).addAttribute("position",
						String.valueOf(data[i][3])).addAttribute("type",
						String.valueOf(data[i][4])).addAttribute("headerImage",
								String.valueOf(data[i][5]));
			}
		}
		return document;
	}

	/**
	 * when dashlet is modified
	 * @param dashletSettings
	 * @param path
	 * @return
	 */
	public boolean modDashletSettings(DashletSettings dashletSettings, String path) {
		// TODO Auto-generated method stub
		if(!checkDuplicateMod(dashletSettings)){
			// Object containing modified data
			// to be updated for selected dashlet
			Object modObj[][] = new Object[1][6];
			modObj[0][0]=dashletSettings.getName().trim();			
			modObj[0][1]=dashletSettings.getLink();
			modObj[0][2]=dashletSettings.getPosition();
			modObj[0][3]=dashletSettings.getType();
			modObj[0][4]=dashletSettings.getDashletImageName().trim();
			modObj[0][5]=dashletSettings.getDashletId();
			
			String updateQuery = " UPDATE HRMS_DASHLET_SETTINGS SET DASHLET_NAME=?,DASHLET_LINK=?,DASHLET_POSITION=?,DASHLET_TYPE=? "
								+" ,DASHLET_HDR_IMAGE=? WHERE DASHLET_CODE= ?";
			boolean result= getSqlModel().singleExecute(updateQuery, modObj);
			
			if(result){
				// xml is rewrite
				xml_dashletsettings(path);
				
				String query = "SELECT  DASHLET_NAME,DASHLET_LINK,"
					  + " DECODE(DASHLET_POSITION,'F','Fixed','M','Movable'), "
					  + " DECODE(DASHLET_TYPE,'L','List','G','Graph','O','Others'), "
					  + " DASHLET_CODE ,DASHLET_HDR_IMAGE FROM HRMS_DASHLET_SETTINGS WHERE DASHLET_CODE= "+dashletSettings.getDashletId();
				
				Object[][] Data = getSqlModel().getSingleResult(query);
				
				dashletSettings.setName(checkNull(String.valueOf(Data[0][0])).trim());			
				dashletSettings.setLink(checkNull(String.valueOf(Data[0][1])).trim());
				dashletSettings.setPosition(checkNull(String.valueOf(Data[0][2])).trim());
				dashletSettings.setType(checkNull(String.valueOf(Data[0][3])).trim());
				dashletSettings.setDashletId(checkNull(String.valueOf(Data[0][4])).trim());
				dashletSettings.setDashletImageNameItt(checkNull(String.valueOf(Data[0][5])).trim());
			}
			return result;
		}else{
			return false;
		}
	}
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * common method use for paging
	 * @param dashletSettings
	 * @param request
	 */
	public void reqData(DashletSettings dashletSettings,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {

			String query = "SELECT  DASHLET_CODE, DASHLET_NAME,DASHLET_LINK,"
					+ " DECODE(DASHLET_POSITION,'F','Fixed','M','Movable'), "
					+ " DECODE(DASHLET_TYPE,'L','List','G','Graph','O','Others'),DASHLET_HDR_IMAGE "
					+ " FROM HRMS_DASHLET_SETTINGS"
					+ " ORDER BY  DASHLET_CODE";

			Object[][] data = getSqlModel().getSingleResult(query);
			int REC_TOTAL = 0;
			int To_TOT = 20;
			int From_TOT = 0;
			int pg1 = 0;
			int PageNo1 = 1;// ----------
			REC_TOTAL = data.length;
			int no_of_pages = Math.round(REC_TOTAL / 20);
			double row = (double) data.length / 20.0;

			java.math.BigDecimal value1 = new java.math.BigDecimal(row);

			int rowCount1 = Integer.parseInt(value1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());

			ArrayList<Object> obj = new ArrayList<Object>();
			System.out.println("val of riwC" + rowCount1);
			request.setAttribute("abc", rowCount1);

			// PageNo
			if (String.valueOf(dashletSettings.getMyPage()).equals("null")
					|| String.valueOf(dashletSettings.getMyPage()).equals(null)
					|| String.valueOf(dashletSettings.getMyPage()).equals(" ")) {
				PageNo1 = 1;
				From_TOT = 0;
				To_TOT = 20;

				if (To_TOT > data.length) {
					To_TOT = data.length;
				}

				dashletSettings.setMyPage("1");
			}

			else {

				pg1 = Integer.parseInt(dashletSettings.getMyPage());
				PageNo1 = pg1;

				if (pg1 == 1) {
					From_TOT = 0;
					To_TOT = 20;
				} else {
					// From_TOTAL=To_TOTAL+1;
					To_TOT = To_TOT * pg1;
					From_TOT = (To_TOT - 20);
				}
				if (To_TOT > data.length) {
					To_TOT = data.length;
				}
			}
			request.setAttribute("xyz", PageNo1);

			for (int i = From_TOT; i < To_TOT; i++) {
				// setting
				DashletSettings bean1 = new DashletSettings();

				bean1.setDashletId(String.valueOf(data[i][0]).trim());
				bean1.setName(String.valueOf(data[i][1]));
				bean1.setLink(String.valueOf(data[i][2]));
				bean1.setPosition(String.valueOf(data[i][3]));
				bean1.setType(String.valueOf(data[i][4]));
				bean1.setDashletImageNameItt(checkNull(String.valueOf(data[i][5])));
				obj.add(bean1);
			}

			dashletSettings.setTypeList(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * method that sets all the dashlet
	 * @param dashletSettings
	 */
	public void getDashletSettings(DashletSettings dashletSettings) {
		// TODO Auto-generated method stub
		try{
			String query = "SELECT  DASHLET_NAME,DASHLET_LINK,"
				  + " DASHLET_POSITION,"
				  + " DASHLET_TYPE,DASHLET_HDR_IMAGE, "
				  + " DASHLET_CODE FROM HRMS_DASHLET_SETTINGS WHERE DASHLET_CODE= "+dashletSettings.getDashletId();
			
			Object[][] data = getSqlModel().getSingleResult(query);
			if(data!=null && data.length>0){
				
				dashletSettings.setName(checkNull(String.valueOf(data[0][0])).trim());			
				dashletSettings.setLink(checkNull(String.valueOf(data[0][1])).trim());
				dashletSettings.setPosition(checkNull(String.valueOf(data[0][2])).trim());
				dashletSettings.setType(checkNull(String.valueOf(data[0][3])).trim());
				dashletSettings.setDashletImageName(checkNull(String.valueOf(data[0][4])).trim());
				dashletSettings.setDashletId(checkNull(String.valueOf(data[0][4])).trim());
		     }
		 }catch(Exception e){
			e.printStackTrace();
		 }
		
	}
	

	/**
	 * Method to delete the selected dashlet
	 * @param dashletSettings
	 * @param poolDir
	 * @return
	 */
	public boolean deleteDashletSettings(DashletSettings dashletSettings, String poolDir) {
		// TODO Auto-generated method stub
		Object delObj[][] = new Object[1][1];		
		delObj[0][0] = dashletSettings.getDashletId();	
		String delQuery = " DELETE FROM HRMS_DASHLET_SETTINGS WHERE DASHLET_CODE=?";
		boolean result =  getSqlModel().singleExecute(delQuery, delObj);
		
		// write the XML after deleting the entry
		if(result){
			xml_dashletsettings(poolDir);
		}
		
		return result;
		
	}

	/**
	 * get the dashlet from databse
	 * and set it in bean
	 * @param dashletSettings
	 */
	public void getSettingsOnDoubleClick(DashletSettings dashletSettings) {
		try{
			
			String query = "SELECT  DASHLET_NAME,DASHLET_LINK,"
				  + " DASHLET_POSITION, "
				  + " DASHLET_TYPE, "
				  + " DASHLET_CODE ,DASHLET_HDR_IMAGE FROM HRMS_DASHLET_SETTINGS WHERE DASHLET_CODE= "+dashletSettings.getHiddencode();

			Object[][] data = getSqlModel().getSingleResult(query);
		
			if(data!=null && data.length>0){
				
				dashletSettings.setName(checkNull(String.valueOf(data[0][0])).trim());			
				dashletSettings.setLink(checkNull(String.valueOf(data[0][1])).trim());
				dashletSettings.setPosition(checkNull(String.valueOf(data[0][2])).trim());
				dashletSettings.setType(checkNull(String.valueOf(data[0][3])).trim());
				dashletSettings.setDashletId(checkNull(String.valueOf(data[0][4])).trim());
				dashletSettings.setDashletImageName(checkNull(String.valueOf(data[0][5])).trim());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * delete the records from list
	 * and update the database
	 * @param dashletSettings
	 * @param code
	 * @param poolDir
	 * @return
	 */
	public boolean delChkdRec(DashletSettings dashletSettings, String[] code, String poolDir) {
		int count = 0;
		boolean result = false;
		for (int i = 0; i < code.length; i++) {
			if (!code[i].equals("")) {
				Object[][] delete = new Object[1][1];
				delete[0][0] = code[i];
				String delQuery = " DELETE FROM HRMS_DASHLET_SETTINGS WHERE DASHLET_CODE=?";
				result = getSqlModel().singleExecute(delQuery, delete);
				if (!result) {
					count++;
				}
			}
		}
		//write the xml after deleting dashlets
		if(result){
			xml_dashletsettings(poolDir);
		}
		
		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

}
