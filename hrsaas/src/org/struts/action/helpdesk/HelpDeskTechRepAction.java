
package org.struts.action.helpdesk;

import java.util.ArrayList;

import org.paradyne.bean.helpdesk.HelpDeskTechRep;
import org.paradyne.model.helpdesk.HelpDeskTechRepModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1383
 *
 */
public class HelpDeskTechRepAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(HelpDeskTechRepAction.class);
	HelpDeskTechRep techRepBean;

	public void prepare_local() throws Exception {
		techRepBean = new HelpDeskTechRep();
		techRepBean.setMenuCode(1049);
	}

	public Object getModel() {
		return techRepBean;
	}
	
	@Override
	public String input(){
		try {
			HelpDeskTechRepModel model = new HelpDeskTechRepModel();
			model.initiate(context, session);
			model.getAllManagers(request, techRepBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String addNew() {
		try {
			reset();
			getNavigationPanel(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String f9manageraction(){
		try {
			String query = " SELECT DISTINCT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, MANAGER_CODE "
				+ " FROM HELPDESK_MGRREPORTING_HDR "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HELPDESK_MGRREPORTING_HDR.MANAGER_CODE)";
			String[] headers = { "Employee Id", "Employee Name" };
			String[] headerWidth = { "15", "30" };
			String[] fieldNames = { "managerToken", "managerName", "managerCode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "true";
			String submitToMethod = "HelpDeskTechReporting_getTechniciansForManager.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
		
	}
	
	public String getTechniciansForManager(){
		try {
			HelpDeskTechRepModel model = new HelpDeskTechRepModel();
			model.initiate(context, session);
			String technicianQuery = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, TECH_CODE "
					+ " FROM HELPDESK_TECHNICIANS "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HELPDESK_TECHNICIANS.TECH_CODE)"
					+ " WHERE MANAGER_CODE = " + techRepBean.getManagerCode();
			Object[][] techniciansObj = model.getSqlModel().getSingleResult(
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
		getNavigationPanel(2);
		techRepBean.setEnableAll("Y");
		return "success";
	}
	
	public String f9Technician(){
		try {
			String[] eId = request.getParameterValues("technicianIdItt");
			String str = "0";
			if (eId != null && eId.length>0 ) {
				for (int i = 0; i < eId.length; i++) {
					if(i == eId.length){
						str += eId[i];
					}else{
						str += "," + eId[i];
					}
				}
			}
			//String  id = techRepBean.getRowId();
			String query = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC";
			query += " WHERE 1=1 AND EMP_STATUS='S'";
			query +=" AND EMP_ID NOT IN(" + str + ") ";
			query += "	ORDER BY UPPER(EMP_FNAME) ASC ";
			String[] headers = { "Employee Id", "Employee Name" };
			String[] headerWidth = { "15", "30" };
			String[] fieldNames = { "technicianToken" ,"technicianName", "technicianCode"};
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String save(){
		boolean result=false;
		try {
			HelpDeskTechRepModel model = new HelpDeskTechRepModel();
			model.initiate(context, session);
			String[] technianCode = request.getParameterValues("technicianIdItt");
			result = model.saveTechnicians(techRepBean, technianCode);
			if (result) {
				addActionMessage("Record saved successfully.");
			}else{
				addActionMessage("Record could not be saved");
			}
			model.getTechniciansByManagerId(techRepBean, techRepBean.getManagerCode());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		techRepBean.setEnableAll("N");
		return "success";
	}
	
	public String reset() {
		techRepBean.setManagerCode("");
		techRepBean.setManagerName("");
		techRepBean.setManagerToken("");
		techRepBean.setTechnicianCode("");
		techRepBean.setTechnicianName("");
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String calforedit() {
		
		try {
			HelpDeskTechRepModel model = new HelpDeskTechRepModel();
			model.initiate(context, session);
			model.getTechniciansByManagerId(techRepBean, techRepBean.getHiddenManagerId());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		techRepBean.setEnableAll("N");
		return SUCCESS;
	}
	
	public String deleteList() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		HelpDeskTechRepModel model = new HelpDeskTechRepModel();
		model.initiate(context, session);
		boolean result = model.delCheckedRecords(techRepBean, code);
		if (result) {
			addActionMessage(getText("delMessage", ""));
		} else {
			addActionMessage("One or more records can not be deleted \n as they are associated with some other records.");
		}
		input();
		model.terminate();
		return INPUT;
	}
	
	public String f9Action(){
		try {
			String query = " SELECT DISTINCT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " 
				+ " MANAGER_CODE  FROM HELPDESK_TECHNICIANS "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HELPDESK_TECHNICIANS.MANAGER_CODE)";
			String[] headers = { "Employee Id", "Employee Name" };
			String[] headerWidth = { "15", "30" };
			String[] fieldNames = { "managerToken", "managerName", "managerCode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "true";
			String submitToMethod = "HelpDeskTechReporting_getDetails.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	public String getDetails(){
		try {
			HelpDeskTechRepModel model = new HelpDeskTechRepModel();
			model.initiate(context, session);
			String technicianQuery = " SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, TECH_CODE "
					+ " FROM HELPDESK_TECHNICIANS "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HELPDESK_TECHNICIANS.TECH_CODE)"
					+ " WHERE MANAGER_CODE = " + techRepBean.getManagerCode();
			Object[][] techniciansObj = model.getSqlModel().getSingleResult(
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
		getNavigationPanel(3);
		techRepBean.setEnableAll("N");
		return "success";
	}
	
	public String delete() {
		boolean result = false;
		try {
			HelpDeskTechRepModel model = new HelpDeskTechRepModel();
			model.initiate(context, session);
			result = model.deleteManager(techRepBean, techRepBean.getManagerCode());
			if (result) {
				addActionMessage(getText("Record deleted successfully!"));
			} else {
				addActionMessage("Record can not be deleted");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return INPUT;
	}
	
	public String back() {
		input();
		techRepBean.setEnableAll("Y");
		return INPUT;
	}
}
