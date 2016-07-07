//BY VIJAY SONAWANE

package org.struts.action.admin.srd;
import java.util.TreeMap;
import org.paradyne.bean.admin.srd.IncrementHistoryMisReport;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.model.admin.srd.IncrementHistoryMisReportModel;

import org.struts.lib.ParaActionSupport;

public class IncrementHistoryMisReportAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AddressDetailsMisAction.class);

	IncrementHistoryMisReport incrementDetailMis;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		incrementDetailMis = new IncrementHistoryMisReport();
		incrementDetailMis.setMenuCode(2287);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return incrementDetailMis;
	}

	public IncrementHistoryMisReport getIncrementDetailMis() {
		return incrementDetailMis;
	}

	public void setIncrementDetailMis(IncrementHistoryMisReport incrementDetailMis) {
		this.incrementDetailMis = incrementDetailMis;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		IncrementHistoryMisReportModel model = null;
		try {

			model = new IncrementHistoryMisReportModel();
			model.initiate(context, session);
			DataModificatonUtil dmu = new DataModificatonUtil();
			dmu.initiate(context, session);
			TreeMap map = dmu.getGenderXml("Address");
			incrementDetailMis.setAssetmap(map);
			dmu.terminate();
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in prepare_withLoginProfileDetails" + e);
		}
	}

	

	public String clear() {
		incrementDetailMis.setCenterNo("");
		incrementDetailMis.setCenterName("");
		incrementDetailMis.setDesgCode("");
		incrementDetailMis.setDesgName("");
		incrementDetailMis.setDeptCode("");
		incrementDetailMis.setDeptName("");
		incrementDetailMis.setDesgCode("");
		incrementDetailMis.setDesgName("");
		incrementDetailMis.setEmpToken("");
		incrementDetailMis.setEmployeeId("");
		incrementDetailMis.setEmpName("");
		incrementDetailMis.setDivCode("");
		incrementDetailMis.setDivisionName("");
		incrementDetailMis.setReportType("");
		incrementDetailMis.setEmployeeStatus("");
		incrementDetailMis.setAddressType("");
		return "input";
	}

	public String f9action() throws Exception {

		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME , EMP_ID"
				+ " FROM HRMS_EMP_OFFC " + " ORDER BY EMP_ID ";
		String[] headers = { "Employee ID", getMessage("employee") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "empToken", "empName", "employeeId" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9div() throws Exception {
		/*
		 * String query = " SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION ORDER BY
		 * DIV_ID";
		 * 
		 * String header=getMessage("division"); String textAreaId =
		 * "paraFrm_addressDetailsMis_divisionName"; String hiddenFieldId =
		 * "paraFrm_addressDetailsMis_divCode"; String submitFlag = ""; String
		 * submitToMethod = ""; setMultiSelectF9(query, header, textAreaId,
		 * hiddenFieldId, submitFlag, submitToMethod); return "f9multiSelect";
		 */

		try {
			String query = " SELECT DISTINCT HRMS_DIVISION.DIV_ID,NVL(HRMS_DIVISION.DIV_NAME,' ') FROM HRMS_DIVISION ";
			if (!(incrementDetailMis.getUserProfileDivision().equals(""))) {
				query += " WHERE HRMS_DIVISION.DIV_ID IN ("
						+ incrementDetailMis.getUserProfileDivision() + ")";
			}
			query += " ORDER BY  HRMS_DIVISION.DIV_ID ";

			String textAreaId = "paraFrm_divisionName";
			String hiddenFieldId = "paraFrm_divCode";
			// String[] headers = { getMessage("division")};
			// String[] headerWidth = { "20", "80" };
			// String[] fieldNames = { "divisionId", "divisionName",
			// "divisionAbbrevation" };
			// int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, getMessage("Division"), textAreaId,
					hiddenFieldId, submitFlag, submitToMethod);
			// setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			// submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	public String f9dept() throws Exception {
		/*
		 * String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "; String[]
		 * headers = { "Department Id", getMessage("department") }; String[]
		 * headerWidth = { "20", "80" }; String[] fieldNames = {
		 * "addressDetailMis.deptCode", "addressDetailMis.deptName" }; int[]
		 * columnIndex = { 0, 1 }; String submitFlag = "false"; String
		 * submitToMethod = "";
		 * 
		 * setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
		 * submitFlag, submitToMethod);
		 * 
		 * return "f9page";
		 */
		try {
			String query = " SELECT DISTINCT  HRMS_DEPT.DEPT_ID, NVL(HRMS_DEPT.DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY HRMS_DEPT.DEPT_ID";

			String header = getMessage("dept.name");

			String textAreaId = "paraFrm_deptName";
			String hiddenFieldId = "paraFrm_deptCode";

			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
		
		
	}

	public String f9desg() throws Exception {

		/*
		 * String query = " SELECT RANK_ID,RANK_NAME" + " FROM HRMS_RANK ";
		 * String[] headers = { "Designation Id", getMessage("designation") };
		 * String[] headerWidth = { "15", "35" }; String[] fieldNames = {
		 * "addressDetailMis.desgCode", "addressDetailMis.desgName" }; int[]
		 * columnIndex = { 0, 1 }; String submitFlag = "false"; String
		 * submitToMethod = "";
		 * 
		 * setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
		 * submitFlag, submitToMethod);
		 * 
		 * return "f9page";
		 */
		try {
			String query = " SELECT DISTINCT  HRMS_RANK.RANK_ID, NVL(HRMS_RANK.RANK_NAME,' ') FROM HRMS_RANK ORDER BY HRMS_RANK.RANK_ID";

			String header = getMessage("dept.name");

			String textAreaId = "paraFrm_desgName";
			String hiddenFieldId = "paraFrm_desgCode";

			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	public String f9center() throws Exception {

		/*
		 * String query = " SELECT CENTER_ID , center_name FROM HRMS_CENTER
		 * ORDER BY CENTER_ID "; String[] headers = { "Branch Id",
		 * getMessage("branch") }; String[] headerWidth = { "20", "80" };
		 * String[] fieldNames = { "addressDetailMis.centerNo",
		 * "addressDetailMis.centerName" };
		 * 
		 * int[] columnIndex = { 0, 1 }; String submitFlag = "false"; String
		 * submitToMethod = "";
		 * 
		 * setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
		 * submitFlag, submitToMethod);
		 * 
		 * return "f9page";
		 */
		try {
			String query = " SELECT DISTINCT HRMS_CENTER.CENTER_ID, NVL(HRMS_CENTER.CENTER_NAME,' ') FROM HRMS_CENTER ";
			query += " ORDER BY HRMS_CENTER.CENTER_ID";

			String header = getMessage("branch.name");

			String textAreaId = "paraFrm_centerName";
			String hiddenFieldId = "paraFrm_centerNo";

			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	public String report2() throws Exception {
		System.out.println("in Rep");
		IncrementHistoryMisReportModel model = new IncrementHistoryMisReportModel();
		model.initiate(context, session);
		String reportPath = "";

		model.generateReport(incrementDetailMis, request, response, reportPath);
		model.terminate();
		System.out.println("in Repppppp");
		return null;
	}

	public final String mailReport() {
		try {
			final IncrementHistoryMisReportModel model = new IncrementHistoryMisReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "\\" + poolName;
			}
			String reportPath = getText("data_path") + "\\Report\\Master"
					+ poolName + "\\";

			// model.generateReport(bean, request, response, reportPath);
			model.generateReport(incrementDetailMis, request, response, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

	

}
