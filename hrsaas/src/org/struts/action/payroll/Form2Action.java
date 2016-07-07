package org.struts.action.payroll;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.Form1;
import org.paradyne.model.payroll.Form2Model;
import org.struts.lib.ParaActionSupport;

public class Form2Action extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(Form1Action.class);

	
	Form1 form1;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		form1 = new Form1();
		form1.setMenuCode(901);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return form1;
	}

	public Form1 getForm1() {
		return form1;
	}

	public void setForm1(Form1 form1) {
		this.form1 = form1;
	}
	
	public String report(){
		try {
			Form2Model model = new Form2Model();
			String logoPath = getText("data_path")+"/logos/eGov/PFSmall.bmp";
			model.initiate(context, session);
			model.report(request, response, form1, "", logoPath);
			model.terminate();		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public final String mailReport(){
		try {
			Form2Model model = new Form2Model();
			model.initiate(context, session);
			String logoPath = getText("data_path")+"/logos/eGov/PFSmall.bmp";
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.report(request, response, form1, reportPath, logoPath);			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	

	public String reset() throws Exception {
		form1.setEmpName("");
		form1.setEmpId("");
		form1.setEmpToken("");
		form1.setFromYear("");
		form1.setToYear("");
		form1.setRptType("");
		form1.setCenterName("");
		form1.setRankName("");
		prepare_withLoginProfileDetails();
		return SUCCESS;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		Form2Model model = new Form2Model();
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
		model.terminate();		
		form1.setFromYear(String.valueOf(year));
		form1.setToYear(String.valueOf(year + 1));
	}
	

	
	public String f9Employee() {
			String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN ,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " 
					+ " HRMS_DIVISION.DIV_NAME, HRMS_EMP_OFFC.EMP_ID,"
					+ " HRMS_CENTER.CENTER_NAME,HRMS_RANK.RANK_NAME "
					+ " FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV )"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID)"
					+ " LEFT JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK= HRMS_RANK.RANK_ID)"
					+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_OFFC.EMP_TYPE= HRMS_EMP_TYPE.TYPE_ID)"
					+ "	WHERE HRMS_EMP_OFFC.EMP_STATUS ='S' "
					+ " AND  HRMS_CENTER.CENTER_ID IN(SELECT HRMS_CENTER.CENTER_ID FROM HRMS_CENTER WHERE HRMS_CENTER.CENTER_PF='Y')"
					+ " AND  HRMS_EMP_TYPE.TYPE_ID IN(SELECT HRMS_EMP_TYPE.TYPE_ID FROM HRMS_EMP_TYPE WHERE HRMS_EMP_TYPE.TYPE_PF='Y')"
					+ " ORDER BY HRMS_EMP_OFFC.EMP_ID ";

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

	}
}
