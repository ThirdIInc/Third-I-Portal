package org.paradyne.model.helpdesk;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.helpdesk.HelpDeskSLAMaster;
import org.paradyne.bean.helpdesk.HelpDeskTechRep;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class HelpDeskTechRepModel extends ModelBase {

	public boolean saveTechnicians(HelpDeskTechRep techRepBean,	String[] technianCode) {
		boolean result = false;
		try {
			String deleteQuery = "DELETE FROM HELPDESK_TECHNICIANS WHERE MANAGER_CODE = "
					+ techRepBean.getManagerCode();
			getSqlModel().singleExecute(deleteQuery);
			for (int i = 0; i < technianCode.length; i++) {
				Object insertObj[][] = new Object[1][2];
				
				insertObj[0][0] = String.valueOf(technianCode[i]); 
				insertObj[0][1] = techRepBean.getManagerCode();
				String insertQuery = "INSERT INTO HELPDESK_TECHNICIANS(TECH_CODE, MANAGER_CODE) VALUES (?,?)";
				
				result = getSqlModel().singleExecute(insertQuery, insertObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getTechniciansByManagerId(HelpDeskTechRep techRepBean, String managerCode) {
		
		try {
			String query = " SELECT DISTINCT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,MANAGER_CODE "
					+ " FROM HELPDESK_TECHNICIANS "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HELPDESK_TECHNICIANS.MANAGER_CODE)"
					+ " WHERE MANAGER_CODE = " + managerCode;
			
			Object[][] dataObj = getSqlModel().getSingleResult(query);
			if (dataObj != null && dataObj.length > 0) {
				techRepBean.setManagerToken(String.valueOf(dataObj[0][0]));
				techRepBean.setManagerName(String.valueOf(dataObj[0][1]));
				techRepBean.setManagerCode(String.valueOf(dataObj[0][2]));
			}
			
			String technicianQuery = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, TECH_CODE "
					+ " FROM HELPDESK_TECHNICIANS "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HELPDESK_TECHNICIANS.TECH_CODE)"
					+ " WHERE MANAGER_CODE = " + managerCode;
			Object[][] techniciansObj = getSqlModel().getSingleResult(
					technicianQuery);
			if (techniciansObj != null && techniciansObj.length > 0) {
				ArrayList<Object> techList = new ArrayList<Object>();
				for (int i = 0; i < techniciansObj.length; i++) {
					HelpDeskTechRep bean1 = new HelpDeskTechRep();
					bean1.setTechnicianTokenItt(String
							.valueOf(techniciansObj[i][0]));
					bean1.setTechnicianNameItt(String
							.valueOf(techniciansObj[i][1]));
					bean1.setTechnicianIdItt(String
							.valueOf(techniciansObj[i][2]));
					techList.add(bean1);
				}
				techRepBean.setTechniciansList(techList);
				techRepBean.setTechnicianLength(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAllManagers(HttpServletRequest request,
			HelpDeskTechRep techRepBean) {
		try {
			int length=0;	
			String query = " SELECT DISTINCT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " 
				+ " MANAGER_CODE  FROM HELPDESK_TECHNICIANS "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HELPDESK_TECHNICIANS.MANAGER_CODE)";

			Object[][] data = getSqlModel().getSingleResult(query);
			if(data!=null && data.length>0){
				techRepBean.setModeLength(true);
				techRepBean.setTotalRecords(String.valueOf(data.length));  //   to  display the total number of record in  the list 
			
			String[] pageIndex = Utility.doPaging(techRepBean.getMyPage(),data.length, 20);	
			if(pageIndex==null){
				pageIndex[0] = "0";
				pageIndex[1] ="20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if(pageIndex[4].equals("1"))
				techRepBean.setMyPage("1");
			ArrayList<Object> list = new ArrayList<Object>();
			for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 	
				HelpDeskTechRep bean1 = new HelpDeskTechRep();
				
				bean1.setManagerTokenItt(String.valueOf(data[i][0]));
				bean1.setManagerNameItt(String.valueOf(data[i][1]));
				bean1.setManagerIdItt(String.valueOf(data[i][2]));   
				list.add(bean1);
			}
			techRepBean.setManagerList(list);
			length=data.length;
			techRepBean.setTotalRecords(String.valueOf(length));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean delCheckedRecords(HelpDeskTechRep techRepBean, String[] code) {
		int count = 0;
		boolean result = false;
		for (int i = 0; i < code.length; i++) {
			if (!code[i].equals("")) {
				Object[][] delete = new Object[1][1];
				delete[0][0] = code[i];
				String query= " DELETE FROM HELPDESK_TECHNICIANS WHERE MANAGER_CODE=?";
				result = getSqlModel().singleExecute(query, delete);
				if (!result) {
					count++;
				}
			}
		}
		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	public boolean deleteManager(HelpDeskTechRep techRepBean, String managerCode) {
		boolean result = false;
		try {
			String query= " DELETE FROM HELPDESK_TECHNICIANS WHERE MANAGER_CODE= "
				+managerCode;
			result = getSqlModel().singleExecute(query);
			if (result) {
				result = true;
			}else{
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
