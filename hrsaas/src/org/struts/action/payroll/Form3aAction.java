package org.struts.action.payroll;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.Form3a;
import org.paradyne.model.payroll.Form3aModel;
import org.struts.lib.ParaActionSupport;

public class Form3aAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(Form3aModel.class);

	Form3a formRt;

	public void prepare_local() throws Exception {
		formRt = new Form3a();
		formRt.setMenuCode(671);
	}

	public Form3a getFormRt() {
		return formRt;
	}

	public void setFormRt(Form3a formRt) {
		this.formRt = formRt;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return formRt;
	}

	public String reset() throws Exception {

		formRt.setEmpName("");
		formRt.setEmpId("");
		formRt.setEmpToken("");
		formRt.setFromYear("");
		formRt.setToYear("");
		formRt.setRptType("");
		formRt.setCenterName("");
		formRt.setRankName("");
		prepare_withLoginProfileDetails();
		return SUCCESS;
	}
	
	
	public void prepare_withLoginProfileDetails() throws Exception {
		Form3aModel model = new Form3aModel();
		model.initiate(context, session);
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysDate = formater.format(date);
		String split[] = sysDate.split("/");
		int currentMonth =  Integer.parseInt((split[1]));
		int year =  Integer.parseInt((split[2]));
		if(currentMonth<4){
			year = year -1;
		}
		
		double remianMonth=0;
		double month =  Double.parseDouble(split[1]);
		if(month>3 && month<=12){
			remianMonth = 15 - month;
		}//end of if
		else if(month<=3){
			remianMonth = 3 - month;
		}//end of else if
		model.terminate();
		
		formRt.setFromYear(String.valueOf(year));
		formRt.setToYear(String.valueOf(year + 1));
	}
	

	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @return String
	 */
	public String report() {
		Form3aModel model = new Form3aModel();
		model.initiate(context, session);
		String logoPath = getText("data_path")+"/logos/eGov/PFSmall.bmp";
		model.report(formRt, request, response,"",logoPath);
		model.terminate();
		return null;
	}

	/**
	 * To select an employee
	 * 
	 * @return f9page
	 */
	public String f9Employee() {
		try {
			String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,DIV_NAME,EMP_ID,"
					+ "HRMS_CENTER.CENTER_NAME,HRMS_RANK.RANK_NAME "
					+ "  FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV )"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID)"
					+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK= HRMS_RANK.RANK_ID)"
					+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_OFFC.EMP_TYPE= HRMS_EMP_TYPE.TYPE_ID)";
			query += getprofileQuery(formRt);
			query += "	AND EMP_STATUS ='S' "
					+ " AND  HRMS_CENTER.CENTER_ID IN(SELECT CENTER_ID FROM HRMS_CENTER WHERE CENTER_PF='Y')"
					+ " AND  HRMS_EMP_TYPE.TYPE_ID IN(SELECT TYPE_ID FROM HRMS_EMP_TYPE WHERE TYPE_PF='Y')";
			query += " ORDER BY EMP_ID ";

			String[] headers = { getMessage("employee.id"),getMessage("employee"),getMessage("division") };

			String[] headerWidth = { "15", "50", "35" };

			String[] fieldNames = { "empToken", "empName", "division", "empId",
					"centerName", "rankName" };

			int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		}// end of try
		catch (Exception e) {
			logger.error("" + e);
			e.printStackTrace();
			return null;
		} // end of catch
	}

	public final String f9reportType() {
		try {
			//String query = " SELECT 'Pdf', 'Excel', 'Html', 'Word' FROM DUAL";
			String[][] type = new String[][]{{"PDF"},{"Excel"},{"Doc"},{"Html"}};
			String[] headers = {getMessage("report.type")};
			String[] headerWidth = {"100"};
			String[] fieldNames = {"reportType"};
			int[] columnIndex = {0};
			String submitFlag = "true"; 
			String submitToMethod = "Form3a_mailReport.action";
			//setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			setF9Window(type, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	
	public final String mailReport(){
		try {
			Form3aModel model = new Form3aModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			String logoPath = getText("data_path")+"/logos/eGov/PFSmall.bmp";
			model.report(formRt, request, response, reportPath, logoPath);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
}
