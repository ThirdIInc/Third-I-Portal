package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.paradyne.bean.Recruitment.EmployeeRequisition;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.recruitment.EmployeeRequisitionModel;
import org.struts.lib.ParaActionSupport;

public class EmployeeReqAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeeReqAction.class);
	EmployeeRequisition manPowerReqs;

	public void prepare_local() throws Exception {
		manPowerReqs = new EmployeeRequisition();
		manPowerReqs.setMenuCode(355);
	}

	public Object getModel() {
		return manPowerReqs;
	}

	public EmployeeRequisition getManPowerReqs() {
		return manPowerReqs;
	}

	public void setManPowerReqs(EmployeeRequisition manPowerReqs) {
		this.manPowerReqs = manPowerReqs;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		EmployeeRequisitionModel model = new EmployeeRequisitionModel();
		model.initiate(context, session);
		manPowerReqs.setCalCulayedRequiredByDate(model
				.getCalculatedRequiedByDate());
		model.terminate();
	}

	/**
	 * following function is called to go to the next page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String next() throws Exception {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			// model.addBlankSkill(manPowerReqs,request);
			// model.addBlankDom(manPowerReqs, request);
			// addQuali();
			// addSkill();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";

	}

	/**
	 * following function is called when cancel is clicked in the first page.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancelFirst() throws Exception {
		try {
			getNavigationPanel(1);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String listType = "";
			if (!(manPowerReqs.getReqCode().equals("") || manPowerReqs
					.getReqCode().equals("null"))) {
				Object[][] status = model.checkStatus(manPowerReqs,
						manPowerReqs.getReqCode());

				if (String.valueOf(status[0][2]).equals("A")) {
					listType = "Approved List";
				} else if (String.valueOf(status[0][2]).equals("H")) {
					listType = "On Hold List";
				} else if (String.valueOf(status[0][2]).equals("R")) {
					listType = "Rejected List";
				} else if (String.valueOf(status[0][2]).equals("P")) {
					listType = "Pending List";
				} else if (String.valueOf(status[0][2]).equals("Q")) {
					listType = "Quick Requisition";
				} else if (String.valueOf(status[0][2]).equals("S")) {
					listType = "Send Back";
				} else {
					listType = "New Requisition";
				}
				request.setAttribute("listType", listType);
				model.showRecords(manPowerReqs, String.valueOf(status[0][2]),
						request);
			} else {
				listType = "New Requisition";
				request.setAttribute("listType", listType);
				model.showRecords(manPowerReqs, "B", request);
			}
			// model.showRecords(manPowerReqs, "B",request);
			manPowerReqs.setListFlag("true");
			manPowerReqs.setReqCode("");
			manPowerReqs.setUpdateFirstFlag("false");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * following function is called when cancel is clicked in the first page.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancelSecond() throws Exception {
		try {
			getNavigationPanel(1);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			Object[][] status = model.checkStatus(manPowerReqs, manPowerReqs
					.getReqCode());
			String listType = "";
			if (String.valueOf(status[0][2]).equals("A")) {
				listType = "Approved List";
			} else if (String.valueOf(status[0][2]).equals("H")) {
				listType = "On Hold List";
			} else if (String.valueOf(status[0][2]).equals("R")) {
				listType = "Rejected List";
			} else if (String.valueOf(status[0][2]).equals("P")) {
				listType = "Pending List";
			} else if (String.valueOf(status[0][2]).equals("Q")) {
				listType = "Quick Requisition";
			} else if (String.valueOf(status[0][2]).equals("S")) {
				listType = "Send Back List";
			} else {
				listType = "New Requisition";
			}
			request.setAttribute("listType", listType);
			model.showRecords(manPowerReqs, String.valueOf(status[0][2]),
					request);
			manPowerReqs.setListFlag("true");
			manPowerReqs.setReqCode("");
			manPowerReqs.setUpdateFirstFlag("false");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * following function is called when the second page is in update mode and
	 * the cancel button is clicked page will appear in Edit,Delete,cancel,send
	 * for approval and previous mode.
	 * 
	 * @return
	 */
	public String cancel1() {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String code = manPowerReqs.getReqCode();
			model.searchQualiDtlInView(manPowerReqs, code, request);
			model.searchSkillDtlInView(manPowerReqs, code, request);
			model.searchDomainDtlInView(manPowerReqs, code, request);
			model.searchCertDtlInView(manPowerReqs, code, request);
			model.terminate();
			manPowerReqs.setFlagView("true");
			getNavigationPanel(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	/**
	 * following function is called in the jsp page when a record is about to be
	 * deleted.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		try {
			getNavigationPanel(1);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			boolean result = model.deleteRequisition(manPowerReqs);
			if (result) {
				addActionMessage("Record deleted successfully.");
				manPowerReqs.setReqCode("");
				model.showRecords(manPowerReqs, "B", request);
				manPowerReqs.setListFlag("true");
			} else {
				addActionMessage("This record is referenced in other resources.\nSo can't be deleted.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (manPowerReqs.getCreateNewFlag().equals("Offer Detail")) {
			return "offerDetail";
		} else if (manPowerReqs.getCreateNewFlag().equals("Appointment Detail")) {
			return "appointmentDetail";
		} else {
			return "success";
		}
	}

	/**
	 * following function is called when the division search window button is
	 * clicked
	 * 
	 * @return
	 * @throws Exception
	 */

	public String f9actionDiv() throws Exception {
		try {
			String query = " SELECT HRMS_DIVISION.DIV_NAME, HRMS_DIVISION.DIV_ID, HRMS_DIVISION.DIV_ID FROM HRMS_DIVISION ";
			if (manPowerReqs.getUserProfileDivision() != null
					&& manPowerReqs.getUserProfileDivision().length() > 0) {
				query += " WHERE HRMS_DIVISION.DIV_ID IN ("
						+ manPowerReqs.getUserProfileDivision() + ")";
			}
			query += " ORDER BY  UPPER(HRMS_DIVISION.DIV_NAME) ";
			String[] headers = { getMessage("division") };
			String[] headerWidth = { "50" };
			String[] fieldNames = { "reqDiv", "reqDivCode", "divisionCode" };
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

	/**
	 * following function is called when the department search window button is
	 * clicked
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9actionDept() throws Exception {
		try {
			String query = " SELECT HRMS_DEPT.DEPT_NAME, HRMS_DEPT.DEPT_ID, HRMS_DEPT.DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(HRMS_DEPT.DEPT_NAME)";
			String[] headers = { getMessage("department") };
			String[] headerWidth = { "50" };
			String[] fieldNames = { "reqDept", "reqDeptCode", "deptCode" };
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

	/**
	 * following function is called when position seacrh window is clicked.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9actionDesg() throws Exception {
		try {
			String query = " SELECT  NVL(HRMS_RANK.RANK_NAME,' '), HRMS_RANK.RANK_ID FROM HRMS_RANK WHERE IS_ACTIVE='Y' ORDER BY HRMS_RANK.DESG_ID ";
			String[] headers = { getMessage("desghead") };
			String[] headerWidth = { "50" };
			String[] fieldNames = { "reqDesg", "reqDesgCode" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	public String apprf9action() throws Exception {
		String query = " SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_EMP_OFFC ORDER BY HRMS_EMP_OFFC.EMP_ID ";
		String[] headers = { getMessage("empidhead"), getMessage("apprvhead") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "reqAprCode", "reqApprover" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * following function is called when the branch search window button is
	 * clicked
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9actionBrn() throws Exception {
		String query = " SELECT DISTINCT NVL(HRMS_CENTER.CENTER_NAME,' '), HRMS_CENTER.CENTER_ID, HRMS_CENTER.CENTER_ID FROM HRMS_CENTER ";

		String[] headers = { getMessage("branch") };
		String[] headerWidth = { "50" };
		String[] fieldNames = { "reqBrn", "reqBrnCode", "branchCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9actionAppliedFor() throws Exception {
		try {
			String query = " SELECT HRMS_POST_APP.POST_CODE, HRMS_POST_APP.POST_NAME FROM HRMS_POST_APP ORDER BY HRMS_POST_APP.POST_CODE ";
			String[] headers = { "Post Code", "Post  Name" };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "appliedCode", "appliedFor" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "flase";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/**
	 * following function is called when the Hiring manager search window is
	 * clicked
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9actionHireMan() throws Exception {
		try {
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , HRMS_EMP_OFFC.EMP_ID "
					+ " FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS='S' ORDER BY HRMS_EMP_OFFC.EMP_ID ";
			String[] headers = { getMessage("hiringidhead"),
					getMessage("hiring.mgr") };
			String[] headerwidth = { "20", "60" };
			String[] fieldNames = { "hiringToken", "hiringManager",
					"hiringcode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlage = "false";
			String submitToMethod = " ";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";

	}

	/**
	 * following function is called when the domain selected
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9Dom() throws Exception {
		try {
			String query = " SELECT HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_NAME, HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_CODE FROM HRMS_FUNC_DOMAIN_MASTER WHERE HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_STATUS='A' ORDER BY HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_CODE  ";
			String[] headers = { getMessage("domhead") };
			String[] headerwidth = { "50" };
			String[] fieldNames = { "domName" + manPowerReqs.getRowId(),
					"domId" + manPowerReqs.getRowId() };
			int[] columnIndex = { 0, 1 };
			String submitFlage = "false";
			String submitToMethod = " ";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";

	}

	/**
	 * following function is called when the domain selected
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9Domain() throws Exception {
		try {
			String query = " SELECT HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_NAME, HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_CODE FROM HRMS_FUNC_DOMAIN_MASTER WHERE HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_STATUS='A' ORDER BY HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_CODE";
			String[] headers = { getMessage("dname") };
			String[] headerwidth = { "50" };
			String fieldNames[] = { "domName" + manPowerReqs.getRowId(),
					"domId" + manPowerReqs.getRowId() };
			// String fieldNames[]={request.getParameter("field")};
			int[] columnIndex = { 0, 1 };
			String submitFlage = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/**
	 * following function is called when the skill selected
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9actionForSkill() throws Exception {
		try {
			String query = " SELECT  NVL(HRMS_SKILL.SKILL_NAME,' '), HRMS_SKILL.SKILL_ID  FROM HRMS_SKILL WHERE HRMS_SKILL.SKILL_STATUS='A' ORDER BY HRMS_SKILL.SKILL_ID";
			String[] headers = { getMessage("sname") };
			String[] headerwidth = { "40" };
			String[] fieldNames = { "hskillName", "hskillCode" };
			int[] columnIndex = { 0, 1 };
			String submitFlage = "false";
			String submitToMethod = " ";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/**
	 * following function is called when the specialisation selected
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9Speci() throws Exception {
		try {
			String query = " SELECT NVL(SPEC_NAME,' '),NVL(SPEC_ABBR,' '),SPEC_ID FROM HRMS_SPECIALIZATION where SPEC_STATUS='A' ORDER BY SPEC_ID";
			String[] headers = { getMessage("spechead"), getMessage("spabbr") };
			String[] headerwidth = { "40", "30" };
			String[] fieldNames = { "spname" + manPowerReqs.getRowId(),
					"hsplName" + manPowerReqs.getRowId(),
					"hsplId" + manPowerReqs.getRowId() };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlage = "false";
			String submitToMethod = " ";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/**
	 * following function is called when the domain selected
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9Specialization() throws Exception {
		try {
			String query = " SELECT NVL(SPEC_NAME,' '),NVL(SPEC_ABBR,' '),SPEC_ID FROM HRMS_SPECIALIZATION where SPEC_STATUS='A' ORDER BY SPEC_ID";
			String[] headers = { getMessage("spechead"), getMessage("spabbr") };
			String[] headerwidth = { "40", "30" };
			String[] fieldNames = { "spname" + manPowerReqs.getRowId(),
					"hsplName" + manPowerReqs.getRowId(),
					"hsplId" + manPowerReqs.getRowId() };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlage = "false";
			String submitToMethod = " ";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	//

	public String f9actionDesig() throws Exception {
		try {
			String query = " SELECT NVL(RANK_NAME,' '),RANK_ID,RANK_ID FROM HRMS_RANK WHERE IS_ACTIVE='Y' "
					+ " ORDER BY UPPER(RANK_NAME)";
			String[] headers = { getMessage("position") };
			String[] headerwidth = { "40" };
			String[] fieldNames = { "reqPositionName", "reqPositionCode",
					"positionCode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlage = "false";
			String submitToMethod = " ";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	// ----add record for JD

	/**
	 * following function is called when delete button is clicked for deleteting
	 * th selected dynamically rows in skill details table.
	 */
	public String deleteSkill() throws Exception {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			/**
			 * this if condidtion is checked because if it is called from Offer
			 * or Appointment detail By Varun Khetan on 28/04/2009
			 */
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(13);
			} else {
				getNavigationPanel(4);
			}
			String name[] = null;
			String code[] = null;
			String sn[] = request.getParameterValues("hssrlNo");
			String stype[] = request.getParameterValues("skillType");
			String[] exp = request.getParameterValues("skillExp");
			String[] sel = request.getParameterValues("skillSel");
			String[] detCode = request.getParameterValues("skillDetCode");
			String sdel[] = null;
			if (stype != null) {
				name = new String[stype.length];
				code = new String[stype.length];
				sdel = new String[stype.length];
				int j = 1;
				for (int i = 0; i < stype.length; i++) {
					name[i] = (String) request.getParameter("skillName" + j);
					code[i] = (String) request.getParameter("skillId" + j);
					sdel[i] = (String) request.getParameter("hdeleteSkill" + j);
					j++;
				}
				model.initiate(context, session);
				/*
				 * Object[][] data=model.checkStatus(manPowerReqs,
				 * manPowerReqs.getReqCode()); if(data!=null && data.length>0){
				 * if(!(String.valueOf(data[0][0]).equals("B"))){
				 * addActionMessage("Record can't be deleted as the application
				 * has been approved."); getQuaList(); getCert(); getDomain();
				 * getSkill(); } else{
				 */
				model.delSkill(manPowerReqs, request, sn, stype, name, code,
						exp, sel, sdel, detCode);
				getCert();
				getDomain();
				getQuaList();
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// getSkill();
		return "next";

	}

	/**
	 * following function is called to set the skill table values when any row
	 * is being deleted dynamically
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getSkill() throws Exception {
		try {
			ArrayList<Object> skill = new ArrayList<Object>();
			String name[] = null;
			String code[] = null;
			String sn[] = request.getParameterValues("hssrlNo");
			String stype[] = request.getParameterValues("skillType");
			String[] exp = request.getParameterValues("skillExp");
			String[] sel = request.getParameterValues("skillSel");
			String[] detCode = request.getParameterValues("skillDetCode");
			String sdel[] = null;
			if (stype != null) {
				name = new String[stype.length];
				code = new String[stype.length];
				sdel = new String[stype.length];
				int j = 1;
				for (int i = 0; i < stype.length; i++) {
					EmployeeRequisition bean = new EmployeeRequisition();
					name[i] = (String) request.getParameter("skillName" + j);
					code[i] = (String) request.getParameter("skillId" + j);
					sdel[i] = (String) request.getParameter("hdeleteSkill" + j);
					request.setAttribute("skillName" + j, name[i]);
					request.setAttribute("skillId" + j, code[i]);
					request.setAttribute("hdeleteSkill" + j, sdel[i]);
					bean.setSkillType(stype[i]);
					bean.setSkillExp(exp[i]);
					bean.setSkillSel(sel[i]);
					bean.setSkillDetCode(detCode[i]);
					skill.add(bean);
					j++;
				}
			}
			manPowerReqs.setSkill("true");
			manPowerReqs.setSkillList(skill);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	/**
	 * following function is called when delete button is clicked in
	 * Certification Details
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteCertification() throws Exception {
		try {
			/**
			 * this if condidtion is checked because if it is called from Offer
			 * or Appointment detail By Varun Khetan on 28/04/2009
			 */
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(13);
			} else {
				getNavigationPanel(4);
			}
			String certType[] = request.getParameterValues("certType");
			String certName[] = request.getParameterValues("certName");
			String issue[] = request.getParameterValues("certIssueBy");
			String[] valid = request.getParameterValues("certValid");
			String[] option = request.getParameterValues("certOption");
			String[] detCode = request.getParameterValues("certDetCode");
			String[] delCert = null;
			String[] cert = null;
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			if (certType != null) {
				delCert = new String[certType.length];
				cert = new String[certType.length];
				int j = 1;
				for (int i = 0; i < certType.length; i++) {
					delCert[i] = (String) request.getParameter("certChk" + j);
					cert[i] = (String) request.getParameter("hdeleteCert" + j);
					j++;
				}
				model.initiate(context, session);
				// Object[][] data=model.checkStatus(manPowerReqs,
				// manPowerReqs.getReqCode());
				// if(data!=null && data.length>0){
				// if(!(String.valueOf(data[0][0]).equals("B"))){
				// addActionMessage("Record can't be deleted as the application
				// has
				// been approved.");
				// getQuaList();
				// getSkill();
				// getDomain();
				// getCert();
				// }else{
				model.delCert(manPowerReqs, certType, certName, issue, valid,
						option, delCert, cert, request, detCode);
				getQuaList();
				getSkill();
				getDomain();
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	/**
	 * following function is called to set the certification table values when
	 * any row is being deleted dynamically
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCert() throws Exception {
		try {
			ArrayList<Object> certList = new ArrayList<Object>();
			String certType[] = request.getParameterValues("certType");
			String certName[] = request.getParameterValues("certName");
			String issue[] = request.getParameterValues("certIssueBy");
			String[] valid = request.getParameterValues("certValid");
			String[] option = request.getParameterValues("certOption");
			String[] detCode = request.getParameterValues("certDetCode");
			String[] delCert = null;
			String[] cert = null;
			if (certType != null) {
				delCert = new String[certType.length];
				cert = new String[certType.length];
				int j = 1;
				for (int i = 0; i < certType.length; i++) {
					EmployeeRequisition bean = new EmployeeRequisition();
					delCert[i] = (String) request.getParameter("certChk" + j);
					cert[i] = (String) request.getParameter("hdeleteCert" + j);
					request.setAttribute("hdeleteCert" + j, cert[i]);
					bean.setCertType(certType[i]);
					bean.setCertName(certName[i]);
					bean.setCertIssueBy(issue[i]);
					bean.setCertValid(valid[i]);
					bean.setCertOption(option[i]);
					bean.setCertDetCode(detCode[i]);
					certList.add(bean);
					j++;
				}
			}
			manPowerReqs.setCertFlag("true");
			manPowerReqs.setCertList(certList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	/**
	 * following function is called when delete button is clicked in Domain
	 * Details
	 * 
	 * @return
	 * @throws Exception
	 */

	public String deleteDomain() throws Exception {
		try {
			/**
			 * this if condidtion is checked because if it is called from Offer
			 * or Appointment detail By Varun Khetan on 28/04/2009
			 */
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(13);
			} else {
				getNavigationPanel(4);
			}
			String domType[] = request.getParameterValues("domType");
			String domName[] = null;
			String exp[] = request.getParameterValues("domExp");
			String[] option = request.getParameterValues("domSel");
			String[] detCode = request.getParameterValues("domDetCode");
			String[] delDom = null;
			String[] domChk = null;
			String[] domId = null;
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			if (domType != null) {
				delDom = new String[domType.length];
				domId = new String[domType.length];
				domChk = new String[domType.length];
				domName = new String[domType.length];
				int j = 1;
				for (int i = 0; i < domType.length; i++) {
					delDom[i] = (String) request.getParameter("domChk" + j);
					domChk[i] = (String) request.getParameter("hdeleteDom" + j);
					domId[i] = (String) request.getParameter("domId" + j);
					domName[i] = (String) request.getParameter("domName" + j);
					j++;
				}
				model.initiate(context, session);
				// Object[][] data=model.checkStatus(manPowerReqs,
				// manPowerReqs.getReqCode());
				// if(data!=null && data.length>0){
				// if(!(String.valueOf(data[0][0]).equals("B"))){
				// addActionMessage("Record can't be deleted as the application
				// has
				// been approved.");
				// getQuaList();
				// // getCert();
				// getSkill();
				// getDomain();
				// }else{
				model.delDom(manPowerReqs, domType, domName, exp, option,
						delDom, domChk, domId, request, detCode);
				getQuaList();
				getCert();
				getSkill();
				// }
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	/**
	 * following function is called to set the domain table values when any row
	 * is being deleted dynamically
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getDomain() throws Exception {
		try {
			ArrayList<Object> domainList = new ArrayList<Object>();
			String domType[] = request.getParameterValues("domType");
			String domName[] = null;
			String exp[] = request.getParameterValues("domExp");
			String[] option = request.getParameterValues("domSel");
			String[] detCode = request.getParameterValues("domDetCode");
			String[] delDom = null;
			String[] domChk = null;
			String[] domId = null;
			if (domType != null) {
				delDom = new String[domType.length];
				domId = new String[domType.length];
				domChk = new String[domType.length];
				domName = new String[domType.length];
				int j = 1;
				for (int i = 0; i < domType.length; i++) {
					EmployeeRequisition bean = new EmployeeRequisition();
					delDom[i] = (String) request.getParameter("domChk" + j);
					domChk[i] = (String) request.getParameter("hdeleteDom" + j);
					domId[i] = (String) request.getParameter("domId" + j);
					domName[i] = (String) request.getParameter("domName" + j);
					request.setAttribute("domId" + j, domId[i]);
					request.setAttribute("domName" + j, domName[i]);
					request.setAttribute("hdeleteDom" + j, domChk[i]);
					bean.setDomType(domType[i]);
					bean.setDomExp(exp[i]);
					bean.setDomSel(option[i]);
					bean.setDomDetCode(detCode[i]);
					domainList.add(bean);
					j++;
				}
			}
			manPowerReqs.setDomFlag("true");
			manPowerReqs.setDomList(domainList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	/**
	 * following function is called to set the qualification table values when
	 * any row is being deleted dynamically
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getQuaList() throws Exception {
		try {
			ArrayList<Object> quaList = new ArrayList<Object>();
			String name[] = null;
			String code[] = null;
			String[] lvl = null;
			String spN[] = null;
			String spCode[] = null;
			String hdel[] = null;
			String sn[] = request.getParameterValues("hqsrlNo");
			String qtype[] = request.getParameterValues("hqualType");
			String cut[] = request.getParameterValues("hcutOff");
			String[] sel = request.getParameterValues("sel");
			String[] detCode = request.getParameterValues("quaDetCode");
			if (qtype != null) {
				name = new String[qtype.length];
				code = new String[qtype.length];
				lvl = new String[qtype.length];
				spN = new String[qtype.length];
				spCode = new String[qtype.length];
				hdel = new String[qtype.length];
				int j = 1;
				for (int i = 0; i < qtype.length; i++) {
					EmployeeRequisition bean = new EmployeeRequisition();
					name[i] = (String) request.getParameter("qualificationName"
							+ j);
					code[i] = (String) request.getParameter("qualificationId"
							+ j);
					lvl[i] = (String) request.getParameter("hqualiLevelName"
							+ j);
					spN[i] = (String) request.getParameter("hsplName" + j);
					spCode[i] = (String) request.getParameter("hsplId" + j);
					hdel[i] = (String) request.getParameter("hdeleteQuali" + j);
					request.setAttribute("qualificationName" + j, name[i]);
					request.setAttribute("qualificationId" + j, code[i]);
					request.setAttribute("hqualiLevelName" + j, lvl[i]);
					request.setAttribute("hsplName" + j, spN[i]);
					request.setAttribute("hsplId" + j, spCode[i]);
					// request.setAttribute("paraFrm_hdeleteQuali"+j, hdel[i]);
					bean.setHqualType(qtype[i]);
					bean.setHcutOff(cut[i]);
					bean.setSel(sel[i]);
					bean.setQuaDetCode(detCode[i]);
					quaList.add(bean);
					j++;
				}
			}
			manPowerReqs.setQualList(quaList);
			manPowerReqs.setQuaFlag("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	public void getVacList() throws Exception {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			String[] noofVac = request.getParameterValues("noOfVac");
			String[] vacDate = request.getParameterValues("vacDate");
			String[] vacDet = request.getParameterValues("vacDetCode");
			for (int i = 0; i < noofVac.length; i++) {
				EmployeeRequisition bean = new EmployeeRequisition();
				bean.setVacDetCode(String.valueOf(String.valueOf(vacDet[i])));
				bean.setNoOfVac(String.valueOf(String.valueOf(noofVac[i]))
						.trim());
				bean.setVacDate(String.valueOf(String.valueOf(vacDate[i]))
						.trim());
				list.add(bean);
			}
			manPowerReqs.setVacList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called when delete button is clicked for deleting
	 * rows in Qualification details
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteQualification() throws Exception {
		try {
			/**
			 * this if condidtion is checked because if it is called from Offer
			 * or Appointment detail By Varun Khetan on 28/04/2009
			 */
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(13);
			} else {
				getNavigationPanel(4);
			}
			String name[] = null;
			String code[] = null;
			String[] lvl = null;
			String spN[] = null;
			String spCode[] = null;
			String hdel[] = null;
			String sn[] = request.getParameterValues("hqsrlNo");
			String qtype[] = request.getParameterValues("hqualType");
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			if (qtype != null) {
				name = new String[qtype.length];
				code = new String[qtype.length];
				lvl = new String[qtype.length];
				spN = new String[qtype.length];
				spCode = new String[qtype.length];
				hdel = new String[qtype.length];
				int j = 1;
				for (int i = 0; i < qtype.length; i++) {
					name[i] = (String) request.getParameter("qualificationName"
							+ j);
					code[i] = (String) request.getParameter("qualificationId"
							+ j);
					lvl[i] = (String) request.getParameter("hqualiLevelName"
							+ j);
					spN[i] = (String) request.getParameter("hsplName" + j);
					spCode[i] = (String) request.getParameter("hsplId" + j);
					hdel[i] = (String) request
							.getParameter("paraFrm_hdeleteQuali" + j);
					j++;
				}
				String cut[] = request.getParameterValues("hcutOff");
				String[] sel = request.getParameterValues("sel");
				String[] detCode = request.getParameterValues("quaDetCode");
				model.initiate(context, session);

				// Object[][] data=model.checkStatus(manPowerReqs,
				// manPowerReqs.getReqCode());
				// if(data!=null && data.length>0){
				// if(!(String.valueOf(data[0][0]).equals("B"))){
				// addActionMessage("Record can't be deleted as the application
				// has
				// been approved.");
				// getQuaList();
				// // getDomain();
				// getSkill();
				// getCert();
				// }else{
				model.delQual(manPowerReqs, sn, qtype, name, lvl, spN, cut,
						sel, request, code, spCode, hdel, detCode);
				getDomain();
				getSkill();
				getCert();
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	/**
	 * follwoing function is called for deleting a record in Vacancy Records
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteVacancy() {
		try {
			/**
			 * this if condidtion is checked because if it is called from Offer
			 * or Appointment detail By Varun Khetan on 28/04/2009
			 */
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(10);
			} // end of if
			else {
				getNavigationPanel(2);
			} // end of else if

			String[] vacancy = request.getParameterValues("noOfVac");

			String dt[] = request.getParameterValues("vacDate");
			String[] vacCode = request.getParameterValues("vacDetCode");
			String code[] = null;// request.getParameterValues("hdeleteVac");
			int j = 1;

			if (dt != null) {
				code = new String[dt.length];
				for (int i = 0; i < dt.length; i++) {

					code[i] = (String) request.getParameter("hdeleteVac" + j);

					j++;
				}

			}

			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String[] serialNo = request.getParameterValues("serialNo"); // serial
			// no.
			String[] empCode = request.getParameterValues("empid");// keep
			// informed
			// empid
			String[] empName = request
					.getParameterValues("keepInformedEmpNameItt");// keep
			// informed
			// employee name

			// Object[][] data=model.checkStatus(manPowerReqs,
			// manPowerReqs.getReqCode());
			// if(data!=null && data.length>0){
			// if(!(String.valueOf(data[0][0]).equals("B")))
			// addActionMessage("Record can't be deleted as the application has
			// been approved.");
			// getVacList();
			// }else{
			model.delVacancy(manPowerReqs, vacancy, dt, code, vacCode);
			// }
			setDefaultManagerName();// Added by Nilesh.
			setApproverList(manPowerReqs.getHiringcode());

			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, manPowerReqs);// Added by Nilesh.

			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void getVacancyDet() {
		try {
			String[] vacancy = request.getParameterValues("noOfVac");
			String dt[] = request.getParameterValues("vacDate");
			String[] vacCode = request.getParameterValues("vacDetCode");
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (vacancy != null) {
				for (int i = 0; i < vacancy.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();
					bean1.setVacDate(dt[i]);
					bean1.setNoOfVac(vacancy[i]);
					bean1.setVacDetCode(vacCode[i]);
					tableList.add(bean1);
				}
			}
			manPowerReqs.setVacList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called when the requisition approval status is
	 * "Rejected " from the empReqsnBothPage.jsp and the Send button is clicked.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showRejectedList() throws Exception {
		try {
			getNavigationPanel(1);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model.showRecords(manPowerReqs, "R", request);
			request.setAttribute("listType", "Rejected List");
			manPowerReqs.setListFlag("true");
			// request.setAttribute("listType","B");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * @method input
	 * @purpose to display saved list of requisitions on Load and to set default
	 *          button navigation panel
	 * @return type : String
	 */
	public String input() throws Exception {
		try {
			getNavigationPanel(1);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model.showRecords(manPowerReqs, "B", request);
			manPowerReqs.setListFlag("true");
			// request.setAttribute("listType","New Requisition");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * @method showApplicationList
	 * @purpose to display the applications in tabular format as per the
	 *          selected status
	 * @return type : String
	 */
	public String showApplicationList() throws Exception {
		try {
			getNavigationPanel(1);
			String listType = "";
			String status = request.getParameter("status");
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			if (status.equals("B") || status.equals(" ")) {
				listType = "New Requisition";
			} else if (status.equals("P") || status.equals(" ")) {
				listType = "Pending List";
			} else if (status.equals("A")) {
				listType = "Approved List";
			} else if (status.equals("R")) {
				listType = "Rejected List";
			} else if (status.equals("H")) {
				listType = "On Hold List";
			} else if (status.equals("Q")) {
				listType = "Quick Requisition";
			} else if (status.equals("S")) {
				listType = "Send Back";
			}

			request.setAttribute("listType", listType);

			/**
			 * call showRecords(requisitionApproval, status) method of
			 * RequisitionApprovalModel class to retrieve the pending
			 * application details from HRMS_REQS_HDR table
			 */
			model.showRecords(manPowerReqs, status, request);
			manPowerReqs.setListFlag("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * method addNew
	 * 
	 * @purpose : to set next button panel on clicking addNew button
	 * @return String
	 */
	public String addNew() {
		try {
			/**
			 * this if condidtion is checked if this method is called from Offer
			 * detail Create New Requisition Created by Varun P Khetan on
			 * 27/04/2009
			 */
			
			String source = request.getParameter("src");
			
			manPowerReqs.setSource(source);

			if (String.valueOf(request.getParameter("flag"))
					.equals("offerFlag")) {
				getNavigationPanel(10);
				manPowerReqs.setCreateNewFlag("Offer Detail");
				manPowerReqs.setOfferAppointFlag("true");
			} else if (String.valueOf(request.getParameter("flag")).equals(
					"appointmentFlag")) {
				getNavigationPanel(10);
				manPowerReqs.setCreateNewFlag("Appointment Detail");
				manPowerReqs.setOfferAppointFlag("true");
			} else if (String.valueOf(request.getParameter("flag")).equals(
					"candEval")) {
				getNavigationPanel(10);
				manPowerReqs.setCreateNewFlag("Candidate Evaluation");
				manPowerReqs.setOfferAppointFlag("true");
			} else {
				getNavigationPanel(2);
			}
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model.getCurrentDate(manPowerReqs);
			manPowerReqs.setReqStatus("Open");
			manPowerReqs.setReqApprStatus("New Requisition");
			/**
			 * added by varun p khetan to return back to interview details page
			 * if we navigated from there.
			 */
			manPowerReqs.setAddNewFlag("true");
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());
			model.terminate();
			addRowVac();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setDefaultManagerName() {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			Object[][] empFlow = null;
			empFlow = generateEmpFlow(manPowerReqs.getHiringcode(), "Recruitment", 1);

			if (empFlow != null && empFlow.length > 0) {
				String query = " SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME ,EMP_ID FROM HRMS_EMP_OFFC "
						+ " WHERE EMP_ID=" + String.valueOf(empFlow[0][0]);
				Object data[][] = model.getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					manPowerReqs.setApproverName(String.valueOf(data[0][1]));
					manPowerReqs.setFirstApproverCode(String
							.valueOf(data[0][2]));
					manPowerReqs.setFirstApproverToken(String
							.valueOf(data[0][0]));
				}
			} else {
				manPowerReqs.setFirstApproverCode("");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to display the row by default in Vacancy
	 * details.
	 * 
	 * @return
	 */
	public String addRowVac() {
		try {
			/**
			 * this if condidtion is checked if this method is called from Offer
			 * detail Create New Requisition Created by Varun P Khetan on
			 * 27/04/2009
			 */
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String[] noofVacan = request.getParameterValues("noOfVac");
			String[] vacDate = request.getParameterValues("vacDate");
			model.addRowVac(noofVacan, vacDate, manPowerReqs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void reset() {
		manPowerReqs.setReqCode("");
		manPowerReqs.setQualificationId("");
		manPowerReqs.setHiddenStatus("");
		manPowerReqs.setDomDetCode("");
		manPowerReqs.setSkillId("");
		manPowerReqs.setSkillDetCode("");
		manPowerReqs.setCertDetCode("");
		manPowerReqs.setDomainCode("");
		manPowerReqs.setDomId("");
		manPowerReqs.setQualCode("");
		manPowerReqs.setQuaDetCode("");
		manPowerReqs.setHiddenApproveStatus("");
		manPowerReqs.setHiddenStatus("");
		manPowerReqs.setHsplId("");
		manPowerReqs.setVacDetCode("");
	}

	/**
	 * following function is called when send for approval button is called
	 * 
	 * @return
	 * @throws Exception
	 */
	/*
	 * public String sendForApprove() throws Exception { getNavigationPanel(1);
	 * EmployeeRequisitionModel model = new EmployeeRequisitionModel();
	 * model.initiate(context, session); Object
	 * [][]empflow=generateEmpFlow(manPowerReqs.getUserEmpId(),"Recruitment",
	 * 1);
	 * 
	 * if(empflow==null){ addActionMessage("Reporting Structure has not been
	 * defined."); addActionMessage("Please Contact HR Manager !"); }else{
	 * boolean result=model.saveForAprove(manPowerReqs,empflow); if(result){
	 * 
	 * addActionMessage("Record send successfully.");
	 * 
	 * }else{
	 * 
	 * addActionMessage("Please save the record first."); } }
	 * 
	 * model.showRecords(manPowerReqs, "B",request);
	 * manPowerReqs.setListFlag("true"); return SUCCESS; }
	 */
	public String sendForApprovalInViewSecond() throws Exception {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			Object[][] empflow = generateEmpFlow(manPowerReqs.getUserEmpId(), "Recruitment", 1);
			if (empflow == null) {
				addActionMessage("Reporting Structure has not been defined.");
				addActionMessage("Please Contact HR Manager !");
			} else {
				boolean result = model.saveForAprove(manPowerReqs, empflow,
						manPowerReqs.getReqCode());
				if (result) {
					addActionMessage("Record send successfully.");
				}
			}
			input();
			model.terminate();
			reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * following function is called when Send For Approval button is clicked in
	 * the empReqnBothPage.jsp page First system will check whether Reporting
	 * person has been defined for the user or not. If reporting person has not
	 * been defined system will display the message "Reporting person has not
	 * been defined .Please contact HR Manager." Otherwise system will display
	 * the message Record send successfully
	 * 
	 */
	public String sendForApproval() throws Exception {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String[] keepInformEmpCode = request.getParameterValues("empid");
			String applCode = manPowerReqs.getReqCode();
			Object[][] empflow = generateEmpFlow(manPowerReqs.getUserEmpId(), "Recruitment", 1);
			if (empflow == null) {
				addActionMessage("Reporting Structure has not been defined.");
				addActionMessage("Please Contact HR Manager !");
			} else {
				boolean result = model.saveForAprove(manPowerReqs, empflow,
						manPowerReqs.getReqCode());
				if (result) {
					addActionMessage("Record send successfully.");
					//if (manPowerReqs.getHiddenApproveStatus().equals("New Requisition")) {
						ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
						processAlerts.initiate(context, session);
						processAlerts.removeProcessAlert(String.valueOf(manPowerReqs.getReqCode()), "Resource Requisition");
						processAlerts.terminate();
				//	} 
					model.sendMailToApprover(manPowerReqs, String
							.valueOf(empflow[0][0]), keepInformEmpCode, String
							.valueOf(empflow[0][3]), request, applCode);
					model.sendMailToApplicant(manPowerReqs, String
							.valueOf(empflow[0][0]), applCode,keepInformEmpCode);
				}
			}
			manPowerReqs.setUpdateFirstFlag("false");
			if (manPowerReqs.getReqApprStatus().equals("Rejected")) {
				showRejectedList();
			} else {
				input();
			}
			model.terminate();
			reset();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (manPowerReqs.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (manPowerReqs.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return SUCCESS;
		}
	}

	public void sendApplicationAlert(String applnID, String module,
			String applicant, String approver) {
		try {
			String msgType = "A";
			String level = "1";

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template
					.setEmailTemplate("MANPOWER REQUISITION MAIL FROM APPLICANT TO APPROVER");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			templateQuery1.setParameter(1, applicant);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			templateQuery2.setParameter(1, approver);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applnID);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, approver);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applnID);

			template.configMailAlert();
			template.sendProcessManagerAlert(approver, module, msgType,
					applnID, level);
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called when send for approval button is clicked in
	 * employeeReqView.jsp page.This function will set the viewFirstCancel flgag
	 * to true so that when cancel button is clicked in the page
	 * empReqsnBothPage.jsp page employeeReqView.jsp page will be called and the
	 * page will appear in Edit,Delete,Send for approval,Cancel and Previous
	 * buttons will appear on that page.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendForApprove() throws Exception {
		try {
			getNavigationPanel(9);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setFlagView("true");
			manPowerReqs.setViewFirstCancel("true");
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.searchQualiDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchSkillDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchDomainDtlInView(manPowerReqs,
					manPowerReqs.getReqCode(), request);
			model.searchCertDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			setDefaultManagerName();
			System.out
					.println("SENDFORAPPROVE  manPowerReqs.getHiringcode() >>>>>>>>>"
							+ manPowerReqs.getHiringcode());
			setApproverList(manPowerReqs.getHiringcode());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewBoth";
	}

	/**
	 * following function is called when send for approval button is clicked in
	 * employeeReqView1.jsp page.This function will set the viewSecondCancel
	 * flgag to true so that when cancel button is clicked in the page
	 * empReqsnBothPage.jsp page employeeReqView1.jsp page will be called and
	 * the page will appear in Edit,Delete,Send for approval,Cancel and Previous
	 * buttons will appear on that page.
	 * 
	 * @return
	 * @throws Exception
	 */

	public String sendForApproveInSecView() throws Exception {
		try {
			getNavigationPanel(9);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setFlagView("true");
			manPowerReqs.setViewSecondCancel("true");
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.searchQualiDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchSkillDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchDomainDtlInView(manPowerReqs,
					manPowerReqs.getReqCode(), request);
			model.searchCertDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewBoth";

	}

	/**
	 * following function is called when cancel button is clicked in
	 * empReqsBothPage.jsp.This function will redirect to employeeReqView.jsp
	 * page.Thie page will display the Edit,Delete,Send for Approval,Cancel and
	 * Next button
	 * 
	 * @return
	 */
	public String showfirstView() {
		try {
			getNavigationPanel(3);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setFlagView("true");
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.searchQualiDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchSkillDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchDomainDtlInView(manPowerReqs,
					manPowerReqs.getReqCode(), request);
			model.searchCertDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewFirst";

	}

	/**
	 * following function is called when cancel button is clicked in
	 * empReqsBothPage.jsp.This function will redirect to employeeReqView1.jsp
	 * page.This page will display the Edit,Delete,Send for Approval,Cancel and
	 * Next button
	 * 
	 * @return
	 */
	public String showSecondView() {
		try {
			getNavigationPanel(5);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setFlagView("true");
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.searchQualiDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchSkillDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchDomainDtlInView(manPowerReqs,
					manPowerReqs.getReqCode(), request);
			model.searchCertDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";

	}

	public void cancelView() {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setUpdateFirstFlag("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String updateNewFirst() {
		String str = null;
		try {
			String actionFlag = request.getParameter("actionFlag");
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String[] serialNo = request.getParameterValues("serialNo");
			String[] empCode = request.getParameterValues("empid");
			String[] empName = request.getParameterValues("keepInformedEmpNameItt");

			Object[][] addObj = new Object[1][24];
			addObj[0][0] = manPowerReqs.getReqName();
			addObj[0][1] = manPowerReqs.getReqDt();
			addObj[0][2] = manPowerReqs.getReqPositionCode();
			addObj[0][3] = manPowerReqs.getReqDivCode();
			addObj[0][4] = manPowerReqs.getReqBrnCode();
			addObj[0][5] = manPowerReqs.getReqDeptCode();
			addObj[0][6] = manPowerReqs.getHiringcode();
			if (manPowerReqs.getInternal().equals("true")) {
				addObj[0][7] = "Y";
			} else {
				addObj[0][7] = "N";
			}

			if (manPowerReqs.getExternal().equals("true")) {
				addObj[0][8] = "Y";
			} else {
				addObj[0][8] = "N";
			}
			addObj[0][9] = manPowerReqs.getUserEmpId();
			addObj[0][10] = manPowerReqs.getReqCompensation();
			addObj[0][11] = manPowerReqs.getMinExp();
			addObj[0][12] = manPowerReqs.getMaxExp();
			addObj[0][13] = manPowerReqs.getVacType();
			addObj[0][14] = manPowerReqs.getReqConType();
			addObj[0][15] = manPowerReqs.getReqPartFull();
			addObj[0][16] = manPowerReqs.getJdDescName();
			addObj[0][17] = manPowerReqs.getJdDesc();
			addObj[0][18] = manPowerReqs.getJdRoleDesc();
			addObj[0][19] = manPowerReqs.getSpecialReq();
			addObj[0][20] = manPowerReqs.getPersoanlReq();
			if (manPowerReqs.getVacType().equals("N")) {
				addObj[0][21] = manPowerReqs.getNewPostComment();
			} else if (manPowerReqs.getVacType().equals("R")) {
				addObj[0][21] = manPowerReqs.getReplaceEmpId();
			}
			addObj[0][22] = manPowerReqs.getJdCode();
			addObj[0][23] = manPowerReqs.getReqCode();

			String[] noofVac = request.getParameterValues("noOfVac");
			String[] vacDate = request.getParameterValues("vacDate");
			String[] code = request.getParameterValues("vacDetCode");

			Object[][] data = model.checkStatus(manPowerReqs, manPowerReqs
					.getReqCode());

			boolean result = model.updateFirst(addObj, manPowerReqs);
			if (result) {
				model.addVacDet(noofVac, vacDate, manPowerReqs.getReqCode());
				manPowerReqs.setFlagView("true");
				model.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
						request);
				model.searchVacDtl(manPowerReqs);
				model.getApprovalDtls(manPowerReqs);
				String apprStatus = manPowerReqs.getReqApprStatus();
				addActionMessage("Record updated successfully.");
				model.updateKeepInfoData(request, manPowerReqs);
				model.displayIteratorValueForKeepInformed(serialNo, empCode,
						empName, manPowerReqs);
				str = "viewFirst";
				/**
				 * changed by Varun Khetan on 28/04/2009
				 */
				if (manPowerReqs.getOfferAppointFlag().equals("true")) {
					getNavigationPanel(11);
				} else {
					getNavigationPanel(3);
				}

			} else {
				addActionMessage("Requisition code already exists.");
				str = "success";
				getVacancyDet();
				if (manPowerReqs.getOfferAppointFlag().equals("true")) {
					getNavigationPanel(10);
				} else {
					getNavigationPanel(2);
				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/*
	 * following function is called when the send for approval button is clicked
	 * in employeeReq.jsp page. First system will check the requisition code. If
	 * it is blank or null then system will first save the record otherwise
	 * system will update the record then will display the details entered by
	 * the user to cross check the details
	 */
	public String sendForApproveInFirst() {
		String str = null;
		String msg = null;
		int i = 2;
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String[] empCode = request.getParameterValues("empid");

			if (manPowerReqs.getReqCode().equals("")
					|| manPowerReqs.getReqCode().equals("null")) {
				str = saveFirst();
				if (str.equals("success")) {
					msg = "success";
					i = 2;
				} else {
					msg = "viewBoth";
					i = 9;
					manPowerReqs.setUpdateFirstFlag("true");
					manPowerReqs.setEnableFirst("true");
					manPowerReqs.setFlagView("true");
					model.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
					model.searchVacDtl(manPowerReqs);
					model.searchQualiDtlInView(manPowerReqs, manPowerReqs
							.getReqCode(), request);
					model.searchSkillDtlInView(manPowerReqs, manPowerReqs
							.getReqCode(), request);
					model.searchDomainDtlInView(manPowerReqs, manPowerReqs
							.getReqCode(), request);
					model.searchCertDtlInView(manPowerReqs, manPowerReqs
							.getReqCode(), request);
					setDefaultManagerName();
					setApproverList(manPowerReqs.getHiringcode());
					// model.updateKeepInfoData(request, manPowerReqs);
					// model.updateStatus(manPowerReqs);

				}
			} else {
				str = updateNewFirst();
				if (str.equals("success")) {
					msg = "success";
					i = 2;
				} else {
					msg = "viewBoth";
					manPowerReqs.setUpdateFirstFlag("true");
					manPowerReqs.setEnableFirst("true");
					manPowerReqs.setFlagView("true");
					model.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
					model.searchVacDtl(manPowerReqs);
					model.searchQualiDtlInView(manPowerReqs, manPowerReqs
							.getReqCode(), request);
					model.searchSkillDtlInView(manPowerReqs, manPowerReqs
							.getReqCode(), request);
					model.searchDomainDtlInView(manPowerReqs, manPowerReqs
							.getReqCode(), request);
					model.searchCertDtlInView(manPowerReqs, manPowerReqs
							.getReqCode(), request);
					setDefaultManagerName();
					setApproverList(manPowerReqs.getHiringcode());
					// model.updateKeepInfoData(request, manPowerReqs);
					// model.updateStatus(manPowerReqs);
					i = 9;
				}
			}
			getNavigationPanel(i);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * following function is called when the cancel button in
	 * empReqsnBothPage.jsp page is clicked. It will display the
	 * Save,Cancel,Send For Approval and Save and Next buttons in
	 * employeeReqsn.jsp page.
	 * 
	 * @return
	 */
	public String showFirstEnabledPage() {
		try {
			getNavigationPanel(2);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * following function is called when the cancel button in
	 * empReqsnBothPage.jsp page is clicked. It will display the
	 * Save,Cancel,Send For Approval and Save and Previous buttons in
	 * employeeReqsn1.jsp page.
	 * 
	 * @return
	 */
	public String showSecondEnabledPage() {
		try {
			getNavigationPanel(4);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model.searchQualiDtl(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchSkillDtl(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchDomainDtl(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchCertDtl(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	/**
	 * following function is called when the send for approval button is clicked
	 * in employeeReq1.jsp page First system will check the requisition code. If
	 * it is blank or null then system will first save the record otherwise
	 * system will update the record then will display the details entered by
	 * the user to cross check the details
	 * 
	 * @return
	 */
	public String sendForApproveInSecond() {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			Object[][] reqCode = model.getReqCode();
			String reqsCode = manPowerReqs.getReqCode();
			model.checkStatus(manPowerReqs, reqsCode);
			model.deleteData(manPowerReqs);
			// For adding Qualification details
			String code[] = null;
			String spCode[] = null;
			String cut[] = request.getParameterValues("hcutOff");
			String[] sel = request.getParameterValues("sel");
			String qtype[] = request.getParameterValues("hqualType");
			if (qtype != null) {
				code = new String[qtype.length];
				spCode = new String[qtype.length];
				int j = 1;
				for (int i = 0; i < qtype.length; i++) {
					code[i] = (String) request.getParameter("qualificationId"
							+ j);
					spCode[i] = (String) request.getParameter("hsplId" + j);
					j++;
				}
			}
			model.addQualiDet(code, spCode, cut, sel, qtype, reqsCode);
			// SKILL DETAILS
			String skillCode[] = null;
			String stype[] = request.getParameterValues("skillType");
			String[] skillExp = request.getParameterValues("skillExp");
			String[] skillSel = request.getParameterValues("skillSel");
			if (stype != null) {
				skillCode = new String[stype.length];
				int s = 1;
				for (int i = 0; i < stype.length; i++) {
					skillCode[i] = (String) request.getParameter("skillId" + s);
					s++;
				}
			}
			model.addSkillDet(skillCode, stype, skillExp, skillSel, reqsCode);
			// Domain Details
			String domType[] = request.getParameterValues("domType");
			String domExp[] = request.getParameterValues("domExp");
			String[] option = request.getParameterValues("domSel");
			String[] domId = null;
			if (domType != null) {
				domId = new String[domType.length];
				int d = 1;
				for (int i = 0; i < domType.length; i++) {
					domId[i] = (String) request.getParameter("domId" + d);
					d++;
				}
			}
			model.addDomainDet(domId, domType, domExp, option, reqsCode);
			String certType[] = request.getParameterValues("certType");
			String certName[] = request.getParameterValues("certName");
			String issue[] = request.getParameterValues("certIssueBy");
			String[] valid = request.getParameterValues("certValid");
			String[] certOption = request.getParameterValues("certOption");
			boolean result = model.addCertDet(certType, certName, issue, valid,
					certOption, reqsCode);
			if (result) {
				addActionMessage("Record updated successfully.");
			}
			manPowerReqs.setEnableSecond("true");
			manPowerReqs.setFlagView("true");
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.searchQualiDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchSkillDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchDomainDtlInView(manPowerReqs,
					manPowerReqs.getReqCode(), request);
			model.searchCertDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());
			getNavigationPanel(9);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewBoth";

	}

	/**
	 * following function is called the save button is clicked in the first page
	 * to save th record
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveFirst() throws Exception {
		String retType = null;
		try {
			String actionFlag = request.getParameter("actionFlag");
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			Object[][] addObj = new Object[1][23];
			addObj[0][0] = manPowerReqs.getReqName();
			addObj[0][1] = manPowerReqs.getReqDt();
			addObj[0][2] = manPowerReqs.getReqPositionCode();
			addObj[0][3] = manPowerReqs.getReqDivCode();
			addObj[0][4] = manPowerReqs.getReqBrnCode();
			addObj[0][5] = manPowerReqs.getReqDeptCode();
			addObj[0][6] = manPowerReqs.getHiringcode();
			if (manPowerReqs.getInternal().equals("true")) {
				addObj[0][7] = "Y";
			} else {
				addObj[0][7] = "N";
			}

			if (manPowerReqs.getExternal().equals("true")) {
				addObj[0][8] = "Y";
			} else {
				addObj[0][8] = "N";
			}

			addObj[0][9] = manPowerReqs.getUserEmpId();
			addObj[0][10] = manPowerReqs.getReqCompensation();
			addObj[0][11] = manPowerReqs.getMinExp();
			addObj[0][12] = manPowerReqs.getMaxExp();
			addObj[0][13] = manPowerReqs.getVacType();
			addObj[0][14] = manPowerReqs.getReqConType();
			addObj[0][15] = manPowerReqs.getReqPartFull();
			addObj[0][16] = manPowerReqs.getJdDescName();
			addObj[0][17] = manPowerReqs.getJdDesc();
			addObj[0][18] = manPowerReqs.getJdRoleDesc();
			addObj[0][19] = manPowerReqs.getSpecialReq();
			addObj[0][20] = manPowerReqs.getPersoanlReq();
			if (manPowerReqs.getVacType().equals("N")) {
				addObj[0][21] = checkNull(manPowerReqs.getNewPostComment());
			} else if (manPowerReqs.getVacType().equals("R")) {
				addObj[0][21] = checkNull(manPowerReqs.getReplaceEmpId());
			}
			addObj[0][22] = manPowerReqs.getJdCode();
			// addObj[0][23] = manPowerReqs.getEmpid();

			// String [] keepInformedEmpNameItt =
			// request.getParameterValues("keepInformedEmpNameItt");

			Object[][] reqname = model.chkReqsn(manPowerReqs);
			if (reqname != null && reqname.length > 0) {
				if (String.valueOf(reqname[0][0]).toUpperCase().equals(
						manPowerReqs.getReqName().toUpperCase())) {
					// manPowerReqs.setReqStatus(String.valueOf(reqname[0][1]));
					addActionMessage("Requisition code already exists.");
					retType = "success";
					getVacancyDet();
					/**
					 * this if condidtion is checked because if it is called
					 * from Offer or Appointment detail By Varun Khetan on
					 * 28/04/2009
					 */
					if (manPowerReqs.getOfferAppointFlag().equals("true")) {
						getNavigationPanel(10);
						manPowerReqs.setPositionCode(manPowerReqs
								.getReqPositionCode());
					} // end of if
					else {
						getNavigationPanel(2);
					} // end of else if
				}
			} else {
				String reqsCode = model.saveFirst(addObj);

				manPowerReqs.setReqCode(reqsCode);
				if (!reqsCode.trim().equals("")) {
					addActionMessage("Record saved successfully.");
					sendProcessManagerAlertDraft();
					String[] noofVac = request.getParameterValues("noOfVac");
					String[] vacDate = request.getParameterValues("vacDate");
					model.addVacDet(noofVac, vacDate, reqsCode);
					manPowerReqs.setFlagView("true");
					model.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
					model.searchVacDtl(manPowerReqs);
					model.getApprovalDtls(manPowerReqs);
					model.updateKeepInfoData(request, manPowerReqs);

					// model.changeStatus(request, manPowerReqs);
					String[] serialNo = request.getParameterValues("serialNo");
					String[] empCode = request.getParameterValues("empid");
					String[] empName = request
							.getParameterValues("keepInformedEmpNameItt");
					model.displayIteratorValueForKeepInformed(serialNo,
							empCode, empName, manPowerReqs);
					model.setKeepInformed(serialNo, empCode, empName,
							manPowerReqs);
					setDefaultManagerName();
					setApproverList(manPowerReqs.getHiringcode());
					retType = "viewFirst";
					if (manPowerReqs.getOfferAppointFlag().equals("true")) {
						getNavigationPanel(11);
					} else {
						getNavigationPanel(3);
					}
				}
			}
			/*
			 * else{
			 * 
			 * addActionMessage("Requisition code already exists.");
			 * retType="success"; getVacancyDet(); getNavigationPanel(2); }
			 */
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retType;
	}

	public String cancelFrth() throws Exception {
		try {
			getNavigationPanel(3);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewFirst";
	}

	public String cancelThird() {
		try {
			getNavigationPanel(3);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setFlagView("true");
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			manPowerReqs.setUpdateFirstFlag("false");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewFirst";
	}

	public String getNextPage() {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			updateFirst();
			manPowerReqs.setUpdateSecondFlag("true");
			String code = manPowerReqs.getReqCode();
			model.searchQualiDtl(manPowerReqs, code, request);
			model.searchSkillDtl(manPowerReqs, code, request);
			model.searchDomainDtl(manPowerReqs, code, request);
			model.searchCertDtl(manPowerReqs, code, request);
			Object[][] data = model.checkStatus(manPowerReqs, code);
			if (data != null && data.length > 0) {
				manPowerReqs.setReqApprStatus(String.valueOf(data[0][0]));
				manPowerReqs.setReqName(String.valueOf(data[0][1]));
			}
			model.terminate();
			getNavigationPanel(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	public String getPreviousPage() {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			model.terminate();
			getNavigationPanel(6);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "previous";
	}

	public String edit() {
		try {
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(10);
			} else {
				getNavigationPanel(2);
			}
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);

			manPowerReqs.setUpdateFirstFlag("true");
			manPowerReqs.setNextFlag("true");
			manPowerReqs.setCancelThrd("false");
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());
			manPowerReqs.setLeadTimeFlag(model.leadTimeCheck());
			// model.getRemoveList(manPowerReqs);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";

	}

	/**
	 * following function is called when any record is double clicked in the
	 * list. The record will appear in save,Send for approval,canlce and Save &
	 * Next mode.
	 * 
	 * @return
	 */
	public String callForEdit() {
		String str = null;
		try {
			
			String source = request.getParameter("src");
			
			manPowerReqs.setSource(source);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String requisitionCode = request.getParameter("requisitionCode");
			if (requisitionCode != null) {
				manPowerReqs.setReqCode(requisitionCode);
			} else {
				manPowerReqs.setReqCode(manPowerReqs.getHiddenCode());
			}
			
			// setApproverList(manPowerReqs.getEmployeeId());

			String[] serialNo = request.getParameterValues("serialNo"); // serial
			// no.
			String[] empCode = request.getParameterValues("empid");// keep
			// informed
			// empid
			String[] empName = request
					.getParameterValues("keepInformedEmpNameItt");// keep
			// informed
			// employee name
			boolean isApproverLogin=false;
			Object[][] status = model.checkStatus(manPowerReqs, manPowerReqs.getReqCode());
			if (status != null && status.length > 0) {
				manPowerReqs.setHiddenStatus(Utility.checkNull(String.valueOf(status[0][2])));
				
				model.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
						request);
				model.searchVacDtl(manPowerReqs);
				model.getApprovalDtls(manPowerReqs);
				setDefaultManagerName();
				isApproverLogin=setApproverList(manPowerReqs.getHiringcode());
				model.displayIteratorValueForKeepInformed(serialNo,
						empCode, empName, manPowerReqs);
				
				if (String.valueOf(status[0][2]).equals("P")
						|| String.valueOf(status[0][2]).equals("R")
						|| String.valueOf(status[0][2]).equals("A")
						|| String.valueOf(status[0][2]).equals("H")
						|| String.valueOf(status[0][2]).equals("Q")||isApproverLogin) {
					getNavigationPanel(19);
					manPowerReqs.setEnableAll("N");
					manPowerReqs.setFlagView("true");
					
					// model.updateStatus(manPowerReqs);

					str = "viewFirst";
				} else {
					getNavigationPanel(2);
					manPowerReqs.setUpdateFirstFlag("true");
					manPowerReqs.setNextFlag("true");
					manPowerReqs.setCancelThrd("false");
					
					// model.updateStatus(manPowerReqs);
					str = "success";
				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;

	}

	public String editSec() {
		try {
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(13);
			} else {
				getNavigationPanel(4);
			}
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setEnableSecond("true");
			manPowerReqs.setCancel1("true");
			manPowerReqs.setUpdateSecondFlag("true");
			manPowerReqs.setCancelFrth("true");
			String code = manPowerReqs.getReqCode();
			model.searchQualiDtl(manPowerReqs, code, request);
			model.searchSkillDtl(manPowerReqs, code, request);
			model.searchDomainDtl(manPowerReqs, code, request);
			model.searchCertDtl(manPowerReqs, code, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";

	}

	public String updateFirst() {
		String str = null;
		try {
			String actionFlag = request.getParameter("actionFlag");
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			Object[][] addObj = new Object[1][24];
			addObj[0][0] = manPowerReqs.getReqName();
			addObj[0][1] = manPowerReqs.getReqDt();
			addObj[0][2] = manPowerReqs.getReqPositionCode();
			addObj[0][3] = manPowerReqs.getReqDivCode();
			addObj[0][4] = manPowerReqs.getReqBrnCode();
			addObj[0][5] = manPowerReqs.getReqDeptCode();
			addObj[0][6] = manPowerReqs.getHiringcode();
			if (manPowerReqs.getInternal().equals("true")) {
				addObj[0][7] = "Y";
			} else {
				addObj[0][7] = "N";
			}

			if (manPowerReqs.getExternal().equals("true")) {
				addObj[0][8] = "Y";
			} else {
				addObj[0][8] = "N";
			}
			addObj[0][9] = manPowerReqs.getUserEmpId();
			addObj[0][10] = manPowerReqs.getReqCompensation();
			addObj[0][11] = manPowerReqs.getMinExp();
			addObj[0][12] = manPowerReqs.getMaxExp();
			addObj[0][13] = manPowerReqs.getVacType();
			addObj[0][14] = manPowerReqs.getReqConType();
			addObj[0][15] = manPowerReqs.getReqPartFull();
			addObj[0][16] = manPowerReqs.getJdDescName();
			addObj[0][17] = manPowerReqs.getJdDesc();
			addObj[0][18] = manPowerReqs.getJdRoleDesc();
			addObj[0][19] = manPowerReqs.getSpecialReq();
			addObj[0][20] = manPowerReqs.getPersoanlReq();
			if (manPowerReqs.getVacType().equals("N")) {
				addObj[0][21] = manPowerReqs.getNewPostComment();
			} else if (manPowerReqs.getVacType().equals("R")) {
				addObj[0][21] = manPowerReqs.getReplaceEmpId();
			}
			addObj[0][22] = manPowerReqs.getJdCode();
			addObj[0][23] = manPowerReqs.getReqCode();

			/*
			 * String reqsCode = model.saveFirst(addObj);
			 * manPowerReqs.setReqCode(reqsCode);
			 */

			String[] noofVac = request.getParameterValues("noOfVac");
			String[] vacDate = request.getParameterValues("vacDate");
			String[] code = request.getParameterValues("vacDetCode");

			Object[][] data = model.checkStatus(manPowerReqs, manPowerReqs
					.getReqCode());
			// if(!(String.valueOf(data[0][0]).equals("B") ||
			// String.valueOf(data[0][0]).equals("R"))){

			// addActionMessage("Application has been already approved.So can't
			// be updated.");
			// }else{
			boolean result = model.updateFirst(addObj, manPowerReqs);
			if (result) {
				model.addVacDet(noofVac, vacDate, manPowerReqs.getReqCode());
				// model.updateVacDet(noofVac,vacDate,code,manPowerReqs);
				manPowerReqs.setFlagView("true");
				model.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
						request);
				model.searchVacDtl(manPowerReqs);
				model.getApprovalDtls(manPowerReqs);
				String apprStatus = manPowerReqs.getReqApprStatus();
				if (apprStatus.equals("Send Back")) {
					model.updateStatus(manPowerReqs);
				}

				addActionMessage("Record updated successfully.");
				model.updateKeepInfoData(request, manPowerReqs);
				setDefaultManagerName();
				setApproverList(manPowerReqs.getHiringcode());
				String[] serialNo = request.getParameterValues("serialNo");
				String[] empCode = request.getParameterValues("empid");
				String[] empName = request
						.getParameterValues("keepInformedEmpNameItt");
				model.displayIteratorValueForKeepInformed(serialNo, empCode,
						empName, manPowerReqs);

				// model.setKeepInformed(serialNo, empCode, empName,
				// manPowerReqs);

				str = "viewFirst";
				/**
				 * changed by Varun Khetan on 28/04/2009
				 */
				if (manPowerReqs.getOfferAppointFlag().equals("true")) {
					getNavigationPanel(11);
				} else {
					getNavigationPanel(3);
				}

			} else {
				addActionMessage("Requisition code already exists.");
				str = "success";
				// // Object[][] data1=model.checkStatus(manPowerReqs,
				// manPowerReqs.getReqCode());
				// if(data1!=null && data1.length >0){
				// manPowerReqs.setReqStatus(String.valueOf(data1[0][0]));
				// }
				// model.searchHdrRec(manPowerReqs,request);
				// model.searchVacDtl(manPowerReqs);
				getVacancyDet();
				/**
				 * changed by Varun Khetan on 28/04/2009
				 */
				if (manPowerReqs.getOfferAppointFlag().equals("true")) {
					getNavigationPanel(10);
				} else {
					getNavigationPanel(2);
				}
			}
			// }
			// /
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;

	}

	public String updateSecond() {
		try {
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(14);
			} else {
				getNavigationPanel(5);
			}
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model.checkStatus(manPowerReqs, manPowerReqs.getReqCode());
			String req = manPowerReqs.getReqCode();
			// For adding Qualification details
			String code[] = null;
			String spCode[] = null;
			String cut[] = request.getParameterValues("hcutOff");
			String[] sel = request.getParameterValues("sel");
			String qtype[] = request.getParameterValues("hqualType");
			String[] quaDet = request.getParameterValues("quaDetCode");
			if (qtype != null) {
				code = new String[qtype.length];
				spCode = new String[qtype.length];
				int j = 1;
				for (int i = 0; i < qtype.length; i++) {
					code[i] = (String) request.getParameter("qualificationId"
							+ j);

					spCode[i] = (String) request.getParameter("hsplId" + j);

					j++;
				}
			}
			// SKILL DETAILS
			String skillCode[] = null;
			String stype[] = request.getParameterValues("skillType");
			String[] skillExp = request.getParameterValues("skillExp");
			String[] skillSel = request.getParameterValues("skillSel");
			String[] skillDet = request.getParameterValues("skillDetCode");
			if (stype != null) {
				skillCode = new String[stype.length];
				int s = 1;
				for (int i = 0; i < stype.length; i++) {
					skillCode[i] = (String) request.getParameter("skillId" + s);
					s++;
				}
			}
			// Domain Details
			String domType[] = request.getParameterValues("domType");
			String domExp[] = request.getParameterValues("domExp");
			String[] option = request.getParameterValues("domSel");
			String[] domDet = request.getParameterValues("domDetCode");
			String[] domId = null;
			if (domType != null) {
				domId = new String[domType.length];
				int d = 1;
				for (int i = 0; i < domType.length; i++) {
					domId[i] = (String) request.getParameter("domId" + d);
					d++;
				}
			}
			String certType[] = request.getParameterValues("certType");
			String certName[] = request.getParameterValues("certName");
			String issue[] = request.getParameterValues("certIssueBy");
			String[] valid = request.getParameterValues("certValid");
			String[] certOption = request.getParameterValues("certOption");
			String certDet[] = request.getParameterValues("certDetCode");
			Object[][] data = model.checkStatus(manPowerReqs, manPowerReqs
					.getReqCode());
			model.deleteData(manPowerReqs);
			model.addQualiDet(code, spCode, cut, sel, qtype, req);
			model.addSkillDet(skillCode, stype, skillExp, skillSel, req);
			model.addDomainDet(domId, domType, domExp, option, req);
			model.addCertDet(certType, certName, issue, valid, certOption, req);
			addActionMessage("Record updated successfully.");
			manPowerReqs.setFlagView("true");
			model.searchQualiDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchSkillDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchDomainDtlInView(manPowerReqs,
					manPowerReqs.getReqCode(), request);
			model.searchCertDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";

	}

	public String updateSecondPage() throws Exception {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String[] serialNo = request.getParameterValues("serialNo"); // serial
			// no.
			String[] empCode = request.getParameterValues("empid");// keep
			// informed
			// empid
			String[] empName = request
					.getParameterValues("keepInformedEmpNameItt");// keep
			// informed
			// employee name

			model.checkStatus(manPowerReqs, manPowerReqs.getReqCode());
			updateSecond();
			manPowerReqs.setFlagView("false");
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			// model.getApprovalDtls(manPowerReqs);
			manPowerReqs.setUpdateFirstFlag("true");
			manPowerReqs.setUpdateSecondFlag("true");
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());
			model.updateKeepInfoData(request, manPowerReqs);
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, manPowerReqs);
			String status1 = manPowerReqs.getReqApprStatus().trim();
			if (status1.equals("Send Back")) {
				manPowerReqs.setApprvFlag("true");
			}
			model.getApprovalDtls(manPowerReqs);
			model.terminate();
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(10);
			} else {
				getNavigationPanel(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * this method is called when Create New requisition is from Offer Detail or
	 * Appointment Detail this method Created by Varun Khetan on 28/04/2009
	 * 
	 * @return
	 * @throws Exception
	 */
	public String backToOfferAppoint() throws Exception {
		EmployeeRequisitionModel model = new EmployeeRequisitionModel();
		model.initiate(context, session);
		String reqCode = request.getParameter("reqCode");
		if (reqCode.equals("") || reqCode.equals("null")) {
			if (manPowerReqs.getCreateNewFlag().equals("Offer Detail")) {
				return "offerDetails";
			} else if (manPowerReqs.getCreateNewFlag().equals(
					"Appointment Detail")) {
				return "appointmentDetails";
			} else {
				model.terminate();
				return "candEval";
			}
		} else {
			if (manPowerReqs.getCreateNewFlag().equals("Offer Detail")) {
				model.updApprStatFrmOfferAppoint(manPowerReqs, reqCode);
				manPowerReqs.setRequisitionName(manPowerReqs.getReqName());
				manPowerReqs.setRequisitionCode(manPowerReqs.getReqCode());
				manPowerReqs.setPosition(manPowerReqs.getReqPositionName());
				manPowerReqs.setDivision(manPowerReqs.getReqDiv());
				manPowerReqs.setBranch(manPowerReqs.getReqBrn());
				manPowerReqs.setDepartment(manPowerReqs.getReqDept());
				manPowerReqs.setPositionCode(manPowerReqs.getReqPositionCode());
				manPowerReqs.setDivisionCode(manPowerReqs.getReqDivCode());
				manPowerReqs.setDeptCode(manPowerReqs.getReqDeptCode());
				manPowerReqs.setBranchCode(manPowerReqs.getReqBrnCode());
				model.terminate();
				return "offerDetail";
			} else if (manPowerReqs.getCreateNewFlag().equals(
					"Appointment Detail")) {
				model.updApprStatFrmOfferAppoint(manPowerReqs, reqCode);
				manPowerReqs.setRequisitionName(manPowerReqs.getReqName());
				manPowerReqs.setRequisitionCode(manPowerReqs.getReqCode());
				manPowerReqs.setPosition(manPowerReqs.getReqPositionName());
				manPowerReqs.setDivision(manPowerReqs.getReqDiv());
				manPowerReqs.setBranch(manPowerReqs.getReqBrn());
				manPowerReqs.setDepartment(manPowerReqs.getReqDept());
				manPowerReqs.setPositionCode(manPowerReqs.getReqPositionCode());
				manPowerReqs.setDivisionCode(manPowerReqs.getReqDivCode());
				manPowerReqs.setDeptCode(manPowerReqs.getReqDeptCode());
				manPowerReqs.setBranchCode(manPowerReqs.getReqBrnCode());
				model.terminate();
				return "appointmentDetail";
			} else {
				model.updApprStatFrmOfferAppoint(manPowerReqs, reqCode);
				manPowerReqs.setRequisitionName(manPowerReqs.getReqName());
				manPowerReqs.setRequisitionCode(manPowerReqs.getReqCode());
				manPowerReqs.setPosition(manPowerReqs.getReqPositionName());
				manPowerReqs.setDivision(manPowerReqs.getReqDiv());
				manPowerReqs.setBranch(manPowerReqs.getReqBrn());
				manPowerReqs.setDepartment(manPowerReqs.getReqDept());
				model.terminate();
				return "candEvals";
			}
		}
	}

	/**
	 * Following function is called when the save and previous button is
	 * clicked. This function will first save the second page and then will go
	 * to the first page.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveAndPrevious() throws Exception {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String[] serialNo = request.getParameterValues("serialNo");
			String[] empCode = request.getParameterValues("empid");
			String[] empName = request
					.getParameterValues("keepInformedEmpNameItt");

			saveSecond();
			model.checkStatus(manPowerReqs, manPowerReqs.getReqCode());
			if (manPowerReqs.getInternal().equals("true")) {
				request.setAttribute("int", manPowerReqs.getInternal());
			}
			if (manPowerReqs.getExternal().equals("true")) {
				request.setAttribute("ext", manPowerReqs.getExternal());
			}
			manPowerReqs.setFlagView("false");
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.updateKeepInfoData(request, manPowerReqs);
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, manPowerReqs);
			// model.setKeepInformed(serialNo, empCode, empName, manPowerReqs);
			manPowerReqs.setUpdateFirstFlag("true");
			manPowerReqs.setUpdateSecondFlag("true");
			model.getApprovalDtls(manPowerReqs);
			model.terminate();
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(10);
			} else {
				getNavigationPanel(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * following function is called when save button is clicked in the next page
	 * 
	 * @return
	 */

	public String saveSecond() {
		try {
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(14);
			} else {
				getNavigationPanel(5);
			}
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			// String.valueOf(reqCode[0][0]);
			Object[][] reqCode = model.getReqCode();
			String reqsCode = manPowerReqs.getReqCode();
			model.checkStatus(manPowerReqs, reqsCode);
			String code[] = null;
			String spCode[] = null;
			String cut[] = request.getParameterValues("hcutOff");
			String[] sel = request.getParameterValues("sel");
			String qtype[] = request.getParameterValues("hqualType");
			if (qtype != null) {
				code = new String[qtype.length];
				spCode = new String[qtype.length];
				int j = 1;
				for (int i = 0; i < qtype.length; i++) {
					code[i] = (String) request.getParameter("qualificationId"
							+ j);
					spCode[i] = (String) request.getParameter("hsplId" + j);
					j++;
				}
			}
			model.addQualiDet(code, spCode, cut, sel, qtype, reqsCode);
			// SKILL DETAILS
			String skillCode[] = null;
			String stype[] = request.getParameterValues("skillType");
			String[] skillExp = request.getParameterValues("skillExp");
			String[] skillSel = request.getParameterValues("skillSel");
			if (stype != null) {
				skillCode = new String[stype.length];
				int s = 1;
				for (int i = 0; i < stype.length; i++) {
					skillCode[i] = (String) request.getParameter("skillId" + s);
					s++;
				}
			}
			model.addSkillDet(skillCode, stype, skillExp, skillSel, reqsCode);
			// Domain Details
			String domType[] = request.getParameterValues("domType");
			String domExp[] = request.getParameterValues("domExp");
			String[] option = request.getParameterValues("domSel");
			String[] domId = null;
			if (domType != null) {
				domId = new String[domType.length];
				int d = 1;
				for (int i = 0; i < domType.length; i++) {
					domId[i] = (String) request.getParameter("domId" + d);
					d++;
				}
			}
			model.addDomainDet(domId, domType, domExp, option, reqsCode);
			String certType[] = request.getParameterValues("certType");
			String certName[] = request.getParameterValues("certName");
			String issue[] = request.getParameterValues("certIssueBy");
			String[] valid = request.getParameterValues("certValid");
			String[] certOption = request.getParameterValues("certOption");
			boolean result = model.addCertDet(certType, certName, issue, valid,
					certOption, reqsCode);
			if (result) {
				addActionMessage("Record updated successfully.");
			}
			manPowerReqs.setFlagView("true");
			model.searchQualiDtlInView(manPowerReqs, reqsCode, request);
			model.searchSkillDtlInView(manPowerReqs, reqsCode, request);
			model.searchDomainDtlInView(manPowerReqs, reqsCode, request);
			model.searchCertDtlInView(manPowerReqs, reqsCode, request);
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	/**
	 * following function is called when the save and next page is clicked .This
	 * function will save the first page and then will go to the next page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String nextPage() throws Exception {
		String retType = "";
		try {
			retType = null;
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);

			String[] serialNo = request.getParameterValues("serialNo"); // serial
			// no.
			String[] empCode = request.getParameterValues("empid");// keep
			// informed
			// empid
			String[] empName = request
					.getParameterValues("keepInformedEmpNameItt");// keep
			// informed
			// employee name

			String rcode = manPowerReqs.getReqCode();
			if (!(manPowerReqs.getReqCode().equals(""))) {
				String code = manPowerReqs.getReqCode();
				model.checkStatus(manPowerReqs, code);
				updateFirst();
				model.searchQualiDtl(manPowerReqs, code, request);
				model.searchSkillDtl(manPowerReqs, code, request);
				model.searchDomainDtl(manPowerReqs, code, request);
				model.searchCertDtl(manPowerReqs, code, request);
				retType = "next";
				getNavigationPanel(4);
			} else {
				manPowerReqs.setSaveFirstFlag("false");
				manPowerReqs.setSaveSecondFlag("true");
				Object[][] addObj = new Object[1][23];
				addObj[0][0] = manPowerReqs.getReqName();
				addObj[0][1] = manPowerReqs.getReqDt();
				addObj[0][2] = manPowerReqs.getReqPositionCode();
				addObj[0][3] = manPowerReqs.getReqDivCode();
				addObj[0][4] = manPowerReqs.getReqBrnCode();
				addObj[0][5] = manPowerReqs.getReqDeptCode();
				addObj[0][6] = manPowerReqs.getHiringcode();
				if (manPowerReqs.getInternal().equals("true")) {
					addObj[0][7] = "Y";
				} else {
					addObj[0][7] = "N";
				}

				if (manPowerReqs.getExternal().equals("true")) {
					addObj[0][8] = "Y";
				} else {
					addObj[0][8] = "N";
				}
				addObj[0][9] = manPowerReqs.getUserEmpId();
				addObj[0][10] = manPowerReqs.getReqCompensation();
				addObj[0][11] = manPowerReqs.getMinExp();
				addObj[0][12] = manPowerReqs.getMaxExp();
				addObj[0][13] = manPowerReqs.getVacType();
				addObj[0][14] = manPowerReqs.getReqConType();
				addObj[0][15] = manPowerReqs.getReqPartFull();
				addObj[0][16] = manPowerReqs.getJdDescName();
				addObj[0][17] = manPowerReqs.getJdDesc();
				addObj[0][18] = manPowerReqs.getJdRoleDesc();
				addObj[0][19] = manPowerReqs.getSpecialReq();
				addObj[0][20] = manPowerReqs.getPersoanlReq();
				if (manPowerReqs.getVacType().equals("N")) {
					addObj[0][21] = manPowerReqs.getNewPostComment();
				} else if (manPowerReqs.getVacType().equals("R")) {
					addObj[0][21] = manPowerReqs.getReplaceEmpId();
				}

				addObj[0][22] = manPowerReqs.getJdCode();
				Object[][] reqname = model.chkReqsn(manPowerReqs);
				if (reqname != null && reqname.length > 0) {
					if (String.valueOf(reqname[0][0]).toUpperCase().equals(
							manPowerReqs.getReqName().toUpperCase())) {
						// manPowerReqs.setReqStatus(String.valueOf(reqname[0][1]));
						addActionMessage("Requisition code already exists.");
						retType = "success";
						getVacancyDet();
						/**
						 * Changed by Varun Khetan
						 */
						if (manPowerReqs.getOfferAppointFlag().equals("true")) {
							getNavigationPanel(10);
						} else {
							getNavigationPanel(2);
						}
					}
				} else {
					String reqsCode = model.saveFirst(addObj);
					manPowerReqs.setReqCode(reqsCode);
					Object[][] data = model.checkStatus(manPowerReqs,
							manPowerReqs.getReqCode());
					if (data != null && data.length > 0) {
						manPowerReqs.setReqApprStatus(String
								.valueOf(data[0][0]));
						manPowerReqs.setReqName(String.valueOf(data[0][1]));
					}
					if (!reqsCode.equals("")) {
						addActionMessage("Record saved successfully.");
						String[] noofVac = request
								.getParameterValues("noOfVac");
						String[] vacDate = request
								.getParameterValues("vacDate");
						model.addVacDet(noofVac, vacDate, reqsCode);
						manPowerReqs.setFlagView("true");
						model.searchHdrRec(manPowerReqs, manPowerReqs
								.getReqCode(), request);
						model.searchVacDtl(manPowerReqs);
						model.getApprovalDtls(manPowerReqs);
						model.updateKeepInfoData(request, manPowerReqs);
						setDefaultManagerName();
						setApproverList(manPowerReqs.getHiringcode());
						model.displayIteratorValueForKeepInformed(serialNo,
								empCode, empName, manPowerReqs);

						retType = "next";
						if (manPowerReqs.getOfferAppointFlag().equals("true")) {
							getNavigationPanel(13);
						} else {
							getNavigationPanel(4);
						}
					}/*
						 * else{
						 * 
						 * addActionMessage("Requisition code already exists.");
						 * retType="success"; getVacancyDet();
						 * getNavigationPanel(2); }
						 */
				}
			}
			// manPowerReqs.setUpdateSecondFlag("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retType;
	}

	/**
	 * when first page is in update mode and sec page is going to be updated
	 * 
	 * @return
	 * @throws Exception
	 */
	public String nextUpdatePage() throws Exception {
		String str = "";
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String[] serialNo = request.getParameterValues("serialNo"); // serial
			// no.
			String[] empCode = request.getParameterValues("empid");// keep
			// informed
			// empid
			String[] empName = request
					.getParameterValues("keepInformedEmpNameItt");// keep
			// informed
			// employee name

			String status1 = manPowerReqs.getReqApprStatus().trim();
			String status2 = manPowerReqs.getReqStatus();

			String code = manPowerReqs.getReqCode();
			model.checkStatus(manPowerReqs, code);
			if (manPowerReqs.getInternal().equals("true")) {
				request.setAttribute("int", manPowerReqs.getInternal());
			}
			if (manPowerReqs.getExternal().equals("true")) {
				request.setAttribute("ext", manPowerReqs.getExternal());
			}
			Object[][] addObj = new Object[1][24];
			addObj[0][0] = manPowerReqs.getReqName();
			addObj[0][1] = manPowerReqs.getReqDt();
			addObj[0][2] = manPowerReqs.getReqPositionCode();
			addObj[0][3] = manPowerReqs.getReqDivCode();
			addObj[0][4] = manPowerReqs.getReqBrnCode();
			addObj[0][5] = manPowerReqs.getReqDeptCode();
			addObj[0][6] = manPowerReqs.getHiringcode();
			if (manPowerReqs.getInternal().equals("true")) {
				addObj[0][7] = "Y";
			} else {
				addObj[0][7] = "N";
			}
			if (manPowerReqs.getExternal().equals("true")) {
				addObj[0][8] = "Y";
			} else {
				addObj[0][8] = "N";
			}
			addObj[0][9] = manPowerReqs.getUserEmpId();
			addObj[0][10] = manPowerReqs.getReqCompensation();
			addObj[0][11] = manPowerReqs.getMinExp();
			addObj[0][12] = manPowerReqs.getMaxExp();
			addObj[0][13] = manPowerReqs.getVacType();
			addObj[0][14] = manPowerReqs.getReqConType();
			addObj[0][15] = manPowerReqs.getReqPartFull();
			addObj[0][16] = manPowerReqs.getJdDescName();
			addObj[0][17] = manPowerReqs.getJdDesc();
			addObj[0][18] = manPowerReqs.getJdRoleDesc();
			addObj[0][19] = manPowerReqs.getSpecialReq();
			addObj[0][20] = manPowerReqs.getPersoanlReq();
			if (manPowerReqs.getVacType().equals("N")) {
				addObj[0][21] = manPowerReqs.getNewPostComment();
			} else if (manPowerReqs.getVacType().equals("R")) {
				addObj[0][21] = manPowerReqs.getReplaceEmpId();
			}
			addObj[0][22] = manPowerReqs.getJdCode();
			addObj[0][23] = manPowerReqs.getReqCode();
			/*
			 * String reqsCode = model.saveFirst(addObj);
			 * manPowerReqs.setReqCode(reqsCode);
			 */
			String[] noofVac = request.getParameterValues("noOfVac");
			String[] vacDate = request.getParameterValues("vacDate");
			String[] detcode = request.getParameterValues("vacDetCode");
			// addActionMessage("Application has been already approved.So can't
			// be
			// updated.");
			// }else{
			boolean result = model.updateFirst(addObj, manPowerReqs);
			if (result) {
				setDefaultManagerName();
				setApproverList(manPowerReqs.getHiringcode());
				String reqsnCode = manPowerReqs.getReqCode();
				model.addVacDet(noofVac, vacDate, reqsnCode);
				// model.updateVacDet(noofVac,vacDate,detcode,manPowerReqs);
				model.searchQualiDtl(manPowerReqs, code, request);
				model.searchSkillDtl(manPowerReqs, code, request);
				model.searchDomainDtl(manPowerReqs, code, request);
				model.searchCertDtl(manPowerReqs, code, request);
				manPowerReqs.setUpdateSecondFlag("true");
				addActionMessage("Record updated successfully.");
				model.updateStatus(manPowerReqs);
				model.updateKeepInfoData(request, manPowerReqs);
				model.displayIteratorValueForKeepInformed(serialNo, empCode,
						empName, manPowerReqs);

				if (status1.equals("Send Back")) {
					manPowerReqs.setCommentsFlag("true");
					model.getApprovalDtls(manPowerReqs);

				}

				if (manPowerReqs.getOfferAppointFlag().equals("true")) {
					getNavigationPanel(13);
				} else {
					getNavigationPanel(4);
				}

				model.getApprovalDtls(manPowerReqs);
				str = "next";
			} else {
				addActionMessage("Requisition code already exists.");
				str = "success";
				// Object[][] data1=model.checkStatus(manPowerReqs,
				// manPowerReqs.getReqCode());
				// if(data1!=null && data1.length >0){
				// manPowerReqs.setReqStatus(String.valueOf(data1[0][0]));
				// }
				getVacancyDet();
				if (manPowerReqs.getOfferAppointFlag().equals("true")) {
					getNavigationPanel(10);
				} else {
					getNavigationPanel(2);
				}
			}
			// }
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("STRING RETURN >>>>>>>>>>>>>>" + str);
		return str;
	}

	public String firstPageView() throws Exception {
		try {
			// if(manPowerReqs.getFormFlag().equals("true"))
			// getNavigationPanel(7);
			// else

			if (manPowerReqs.getStatusKey().equals("P")) {
				getNavigationPanel(15);
			} else if (manPowerReqs.getStatusKey().equals("R")
					|| manPowerReqs.getStatusKey().equals("A")) {
				getNavigationPanel(7);
			}
			if (manPowerReqs.getStatusKey().equals("S")) {
				getNavigationPanel(7);
			}
			if (manPowerReqs.getStatusKey().equals("H")) {
				getNavigationPanel(22);
			}
			if (manPowerReqs.getStatusKey().equals("Q")) {
				getNavigationPanel(7);
			}
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setFlagView("true");
			model.checkStatus(manPowerReqs, manPowerReqs.getReqCode());
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());
			// model.displayIteratorValueForKeepInformed(serialNo, empCode,
			// empName, manPowerReqs);
			// model.setKeepInformed(serialNo, empCode, empName, manPowerReqs);

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "prevPageView";
	}

	/*
	 * following function is called when the previous button is clicked .The
	 * control goes to the firstpage
	 */
	public String previousPageView() throws Exception {
		try {
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(11);
			} else {
				if (manPowerReqs.getFormFlag().equals("true")) {
					getNavigationPanel(7);
				} else {
					// getNavigationPanel(3);
					getNavigationPanel(19);
				}
			}

			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setFlagView("true");
			model.checkStatus(manPowerReqs, manPowerReqs.getReqCode());
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "prevPageView";
	}

	public String previousPage() throws Exception {
		try {
			getNavigationPanel(3);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setFlagView("true");
			model.checkStatus(manPowerReqs, manPowerReqs.getReqCode());
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "prevPageView";
	}

	public String previousPage1() throws Exception {
		try {
			getNavigationPanel(7);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setFlagView("true");
			model.checkStatus(manPowerReqs, manPowerReqs.getReqCode());
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "prevPageView";
	}

	/**
	 * following function is called when any record is selected from the search
	 * windiow. The record will appear in view mode.
	 * 
	 * @return
	 */
	public String searchRec() {
		String str = "";
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			str = null;
			Object[][] status = model.checkStatus(manPowerReqs, manPowerReqs
					.getReqCode());
			if (status != null && status.length > 0) {
				if (String.valueOf(status[0][2]).equals("P")
						|| String.valueOf(status[0][2]).equals("A")
						|| String.valueOf(status[0][2]).equals("H")
						|| String.valueOf(status[0][2]).equals("Q")
						|| String.valueOf(status[0][2]).equals("R")) {

					manPowerReqs.setFlagView("true");
					model.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
					model.searchVacDtl(manPowerReqs);
					setDefaultManagerName();
					setApproverList(manPowerReqs.getHiringcode());
					model.getApprovalDtls(manPowerReqs);
					// model.updateStatus(manPowerReqs);
					getNavigationPanel(19);
					str = "viewFirst";
				} else {
					manPowerReqs.setUpdateFirstFlag("true");
					manPowerReqs.setNextFlag("true");
					manPowerReqs.setCancelThrd("false");
					model.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
					model.searchVacDtl(manPowerReqs);
					model.getApprovalDtls(manPowerReqs);
					setDefaultManagerName();
					setApproverList(manPowerReqs.getHiringcode());
					// model.updateStatus(manPowerReqs);
					getNavigationPanel(2);
					str = "success";
				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public String viewOnlineReqDetailsFromApproval() {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String status = request.getParameter("statusKey") != null ? request
					.getParameter("statusKey") : "P";
			String reqCode = request.getParameter("reqCode");

			Object[][] approverIdObj = null;
			String query = "SELECT REQS_APPROVAL_STATUS,REQS_APPR_CODE,REQS_ALTER_APPROVER FROM HRMS_REC_REQS_HDR WHERE REQS_CODE ="
					+ reqCode;
			approverIdObj = model.getSqlModel().getSingleResult(query);

			if (approverIdObj != null && approverIdObj.length > 0) {
				
				if (String.valueOf(approverIdObj[0][1]).equals(manPowerReqs.getUserEmpId())
						&& String.valueOf(approverIdObj[0][0]).equals("P") || String.valueOf(approverIdObj[0][2]).equals(manPowerReqs.getUserEmpId())
						&& String.valueOf(approverIdObj[0][0]).equals("P"))  {
					
				} else {
					addActionMessage("Application Already Processed");
					return "applicationProcess";
				}
				
			/*	
				if (!String.valueOf(statusObj[0][2]).equals("0")) {
					if (!String.valueOf(statusObj[0][1]).equals(
							manPowerReqs.getUserEmpId())
							|| !String.valueOf(statusObj[0][2]).equals(
									manPowerReqs.getUserEmpId())) {
						addActionMessage("Application Already Processed");
						return "applicationProcess";
					}
				} else if (!String.valueOf(statusObj[0][1]).equals(
						manPowerReqs.getUserEmpId())) {
					addActionMessage("Application Already Processed");
					return "applicationProcess";
				}

				if (String.valueOf(statusObj[0][0]).equals("A")) {
					addActionMessage("Application Already Processed");
					return "applicationProcess";
				}*/
			}

			if (status.equals("P")) {
				manPowerReqs.setCommentFlag("true");
				getNavigationPanel(15);
			} else if (status.equals("H")) {
				getNavigationPanel(22);
				manPowerReqs.setCommentFlag("true");
			} else {
				getNavigationPanel(7);
				manPowerReqs.setCommentFlag("false");
			}
			manPowerReqs.setFlagView("true");
			manPowerReqs.setReqsLevel(request.getParameter("level"));
			manPowerReqs.setReqFlag(request.getParameter("flag"));
			manPowerReqs.setReqFlag(request.getParameter("flag"));
			manPowerReqs.setReqCode(request.getParameter("reqCode"));
			manPowerReqs.setFormAction(request.getParameter("formAction"));
			manPowerReqs.setStatusKey(status);
			manPowerReqs.setFormFlag("true");
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			model.checkStatus(manPowerReqs, manPowerReqs.getReqCode());
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewFirst";
	}

	/**
	 * following function is called when any record is double clicked from the
	 * Requisition Approval form for viewing requisition. The record will appear
	 * in view mode.
	 * 
	 * @return
	 */
	public String viewReqDetailsFromApproval() {
		try {
			String source = request.getParameter("src");
			manPowerReqs.setSource(source);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String status = request.getParameter("statusKey") != null ? request.getParameter("statusKey") : "P";
			String reqCode = request.getParameter("reqCode");
			
			if (status.equals("P")) {
				manPowerReqs.setCommentFlag("true");
				getNavigationPanel(15);
			} else if (status.equals("H")) {
				getNavigationPanel(22);
				manPowerReqs.setCommentFlag("true");
			} else {
				getNavigationPanel(7);
				manPowerReqs.setCommentFlag("false");
			}
			manPowerReqs.setFlagView("true");
			manPowerReqs.setReqsLevel(request.getParameter("level"));
			manPowerReqs.setReqFlag(request.getParameter("flag"));// Added new for previous
			manPowerReqs.setReqCode(request.getParameter("reqCode"));
			manPowerReqs.setFormAction(request.getParameter("formAction"));
			manPowerReqs.setStatusKey(status);
			manPowerReqs.setFormFlag("true");

			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			model.checkStatus(manPowerReqs, manPowerReqs.getReqCode());
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewFirst";
	}

	/**
	 * following function is called when any record is selected from the another
	 * form for viewing requisition. The record will appear in view mode.
	 * 
	 * @return
	 */
	public String viewReqDetails() {
		try {
			getNavigationPanel(7);
			manPowerReqs.setFlagView("true");
			manPowerReqs.setPageNumber(request.getParameter("page"));
			manPowerReqs.setReqCode(request.getParameter("reqCode"));
			manPowerReqs.setFormAction(request.getParameter("formAction"));
			manPowerReqs.setStatusKey(request.getParameter("statusKey"));
			manPowerReqs.setFormFlag("true");
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String[] serialNo = request.getParameterValues("serialNo"); // serial
			// no.
			String[] empCode = request.getParameterValues("empid");// keep
			// informed
			// empid
			String[] empName = request
					.getParameterValues("keepInformedEmpNameItt");// keep
			// informed
			// employee name
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());
			model.checkStatus(manPowerReqs, manPowerReqs.getReqCode());
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, manPowerReqs);
			// model.setKeepInformed(serialNo, empCode, empName, manPowerReqs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewFirst";
	}

	public String dashLetviewReqDetails() { // for Dashlet view Requisition
		try {
			getNavigationPanel(7);
			manPowerReqs.setFlagView("true");
			manPowerReqs.setReqCode(request.getParameter("reqCode"));
			// manPowerReqs.setFormAction(request.getParameter("formAction"));
			// manPowerReqs.setStatusKey(request.getParameter("statusKey"));
			manPowerReqs.setFormFlag("true");
			manPowerReqs.setDashletFlag("true");
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			model.checkStatus(manPowerReqs, manPowerReqs.getReqCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewFirst";
	}

	public String dashLetviewReqDetails1() { // for Dashlet view Requisition
		try {
			getNavigationPanel(7);
			manPowerReqs.setFlagView("true");
			manPowerReqs.setReqCode(request.getParameter("reqCode"));
			// manPowerReqs.setFormAction(request.getParameter("formAction"));
			// manPowerReqs.setStatusKey(request.getParameter("statusKey"));
			manPowerReqs.setFormFlag("true");
			manPowerReqs.setDashletFlag("true");
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model
					.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
							request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			model.checkStatus(manPowerReqs, manPowerReqs.getReqCode());
			model.searchQualiDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchSkillDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
			model.searchDomainDtlInView(manPowerReqs,
					manPowerReqs.getReqCode(), request);
			model.searchCertDtlInView(manPowerReqs, manPowerReqs.getReqCode(),
					request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewBoth";

	}

	public String viewNextPage() {
		try {
			manPowerReqs.setBackFlag("true");
			// if(manPowerReqs.getFormFlag().equals("true"))
			// getNavigationPanel(8);
			if (manPowerReqs.getStatusKey().equals("P")) {
				getNavigationPanel(16);
			} else if (manPowerReqs.getStatusKey().equals("H")) {
				getNavigationPanel(18);
			} else if (manPowerReqs.getStatusKey().equals("R")
					|| manPowerReqs.getStatusKey().equals("A")) {
				getNavigationPanel(8);
			} else if (manPowerReqs.getStatusKey().equals("S")) {
				getNavigationPanel(8);
			} else if (manPowerReqs.getStatusKey().equals("Q")) {
				getNavigationPanel(8);
			}

			else {
				getNavigationPanel(5);
			}
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setFlagView("true");
			String code = manPowerReqs.getReqCode();
			model.checkStatus(manPowerReqs, code);
			model.searchQualiDtlInView(manPowerReqs, code, request);
			model.searchSkillDtlInView(manPowerReqs, code, request);
			model.searchDomainDtlInView(manPowerReqs, code, request);
			model.searchCertDtlInView(manPowerReqs, code, request);
			model.getApprovalDtls(manPowerReqs);
			Object[][] data = model.checkStatus(manPowerReqs, code);
			if (data != null && data.length > 0) {
				manPowerReqs.setReqApprStatus(String.valueOf(data[0][0]));
				manPowerReqs.setReqName(String.valueOf(data[0][1]));
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	/**
	 * following function is called when the next button is clicked when the
	 * first page is in view mode. The second page will appear in view mode.
	 * 
	 * @return
	 */
	public String viewNext() {
		try {
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(12);
			} else {
				if (manPowerReqs.getFormFlag().equals("true")) {
					getNavigationPanel(8);
				} else {
					// getNavigationPanel(5);
					getNavigationPanel(20);
				}
			}
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setFlagView("true");
			String code = manPowerReqs.getReqCode();
			model.checkStatus(manPowerReqs, code);
			model.searchQualiDtlInView(manPowerReqs, code, request);
			model.searchSkillDtlInView(manPowerReqs, code, request);
			model.searchDomainDtlInView(manPowerReqs, code, request);
			model.searchCertDtlInView(manPowerReqs, code, request);
			model.getApprovalDtls(manPowerReqs);
			Object[][] data = model.checkStatus(manPowerReqs, code);
			if (data != null && data.length > 0) {
				manPowerReqs.setReqApprStatus(String.valueOf(data[0][0]));
				manPowerReqs.setReqName(String.valueOf(data[0][1]));
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	public String viewNextPageFirst() {
		try {
			getNavigationPanel(21);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setFlagView("true");
			String code = manPowerReqs.getReqCode();
			model.checkStatus(manPowerReqs, code);
			model.searchQualiDtlInView(manPowerReqs, code, request);
			model.searchSkillDtlInView(manPowerReqs, code, request);
			model.searchDomainDtlInView(manPowerReqs, code, request);
			model.searchCertDtlInView(manPowerReqs, code, request);
			model.getApprovalDtls(manPowerReqs);
			Object[][] data = model.checkStatus(manPowerReqs, code);
			if (data != null && data.length > 0) {
				manPowerReqs.setReqApprStatus(String.valueOf(data[0][0]));
				manPowerReqs.setReqName(String.valueOf(data[0][1]));
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	public String viewNextPageSecond() {
		try {
			getNavigationPanel(8);
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			manPowerReqs.setFlagView("true");
			String code = manPowerReqs.getReqCode();
			model.checkStatus(manPowerReqs, code);
			model.searchQualiDtlInView(manPowerReqs, code, request);
			model.searchSkillDtlInView(manPowerReqs, code, request);
			model.searchDomainDtlInView(manPowerReqs, code, request);
			model.searchCertDtlInView(manPowerReqs, code, request);
			model.getApprovalDtls(manPowerReqs);
			Object[][] data = model.checkStatus(manPowerReqs, code);
			if (data != null && data.length > 0) {
				manPowerReqs.setReqApprStatus(String.valueOf(data[0][0]));
				manPowerReqs.setReqName(String.valueOf(data[0][1]));
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	public String f9Search() throws Exception {
		String query = " SELECT	NVL(REQS_NAME,''),TO_CHAR(REQS_DATE ,'DD-MM-YYYY'),HRMS_RANK.RANK_NAME,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME "
				+
				// " ,OFF1.EMP_FNAME||' '||OFF1.EMP_MNAME||' '||OFF1.EMP_LNAME
				// ,DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On
				// Hold','B','New Requisition'),REQS_CODE"+
				" ,DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','S','Send Back','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),REQS_CODE"
				+ " FROM HRMS_REC_REQS_HDR INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER "
				+ " INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION "
				+
				// " INNER JOIN HRMS_EMP_OFFC OFF1
				// ON(OFF1.EMP_ID=HRMS_REC_REQS_HDR.REQS_APPLIED_BY) "+
				" WHERE REQS_APPLIED_BY = "
				+ manPowerReqs.getUserEmpId()
				+ " ORDER BY REQS_DATE DESC";

		String[] headers = { getMessage("reqs.code"), getMessage("reqs.date"),
				getMessage("position"), getMessage("hiring.mgr"),
				getMessage("appstatus") };
		String[] headerwidth = { "15", "15", "20", "25", "25" };
		String[] fieldNames = { "reqNameSer", "reqDateSer", "reqPosSer",
				"reqHireSer", "reqApprStatusSer", "reqCode" };
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };
		String submitFlage = "true";
		String submitToMethod = "EmployeeRequi_searchRec.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9Quali() throws Exception {

		String query = " SELECT NVL(QUA_NAME,' '),NVL(QUA_ABBR,' '),CASE WHEN QUA_LEVEL='UG' THEN 'Under Graduate' "
				+ " WHEN QUA_LEVEL='DI' THEN 'Diploma' WHEN QUA_LEVEL='GR' THEN 'Graduate' WHEN QUA_LEVEL='PG' THEN 'Post Graduate'"
				+ " WHEN QUA_LEVEL='PH' THEN 'Phd' WHEN QUA_LEVEL='DO' THEN 'Doctorate' ELSE ' ' END ,QUA_ID FROM HRMS_QUA WHERE QUA_STATUS='A' ORDER BY QUA_ID";

		String[] headers = { getMessage("qualihead"), getMessage("qabbr"),
				getMessage("qlvl"), };
		String[] headerwidth = { "30", "30", "30" };
		String[] fieldNames = { "qname" + manPowerReqs.getRowId(),
				"qualificationName" + manPowerReqs.getRowId(),
				"hqualiLevelName" + manPowerReqs.getRowId(),
				"qualificationId" + manPowerReqs.getRowId() };
		int[] columnIndex = { 0, 1, 2, 3 };
		String submitFlage = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9Qualification() throws Exception {

		String query = " SELECT NVL(QUA_NAME,' '),NVL(QUA_ABBR,' '),CASE WHEN QUA_LEVEL='UG' THEN 'Under Graduate' "
				+ " WHEN QUA_LEVEL='DI' THEN 'Diploma' WHEN QUA_LEVEL='GR' THEN 'Graduate' WHEN QUA_LEVEL='PG' THEN 'Post Graduate'"
				+ " WHEN QUA_LEVEL='PH' THEN 'Phd' WHEN QUA_LEVEL='DO' THEN 'Doctorate' ELSE ' ' END ,QUA_ID FROM HRMS_QUA WHERE QUA_STATUS='A' ORDER BY QUA_ID";

		String[] headers = { getMessage("qualihead"), getMessage("qabbr"),
				getMessage("qlvl") };
		String[] headerwidth = { "30", "30", "30" };

		String[] fieldNames = { "qname" + manPowerReqs.getRowId(),
				"qualificationName" + manPowerReqs.getRowId(),
				"hqualiLevelName" + manPowerReqs.getRowId(),
				"qualificationId" + manPowerReqs.getRowId() };
		int[] columnIndex = { 0, 1, 2, 3 };
		String submitFlage = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9SkillAction() throws Exception {

		String query = " SELECT NVL(SKILL_NAME,' '),SKILL_ID FROM HRMS_SKILL WHERE SKILL_STATUS='A' ORDER BY SKILL_ID";

		String[] headers = { getMessage("skillhead") };
		String[] headerwidth = { "40" };

		String[] fieldNames = { "skillName" + manPowerReqs.getRowId(),
				"skillId" + manPowerReqs.getRowId() };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9Skill() throws Exception {

		String query = " SELECT NVL(SKILL_NAME,' '),SKILL_ID FROM HRMS_SKILL WHERE SKILL_STATUS='A' ORDER BY SKILL_ID";

		String[] headers = { getMessage("skillhead") };
		String[] headerwidth = { "40" };

		String[] fieldNames = { "skillName" + manPowerReqs.getRowId(),
				"skillId" + manPowerReqs.getRowId() };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9jobDesc() throws Exception {

		String query = " SELECT NVL(JOB_DESC_NAME,' '),TO_CHAR(JOB_DESC_EFFC_DATE,'DD-MM-YYYY'),JOB_DESC_CODE FROM HRMS_JOB_DESCRIPTION  WHERE JOB_DESC_STATUS='A' "
				+ "  AND JOB_DESC_EFFC_DATE <=TO_DATE("
				+ "'"
				+ manPowerReqs.getReqDt()
				+ "'"
				+ ",'DD-MM-YYYY') ORDER BY JOB_DESC_CODE";

		String[] headers = { getMessage("job.name"),
				getMessage("effectiveDate") };
		String[] headerWidth = { "20", "20" };
		String[] fieldNames = { "jdDescName", "jdEffDate", "jdCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlage = "true";
		String submitToMethod = "EmployeeRequi_jobDetails.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	/**
	 * This method was called at the time of selecting record from the search
	 * window
	 * 
	 * @return
	 */
	public String jobDetails() {
		try {
			/**
			 * added by varun p khetan if we navigated from any other location
			 */

			String[] serialNo = request.getParameterValues("serialNo");
			String[] empCode = request.getParameterValues("empid");
			String[] empName = request
					.getParameterValues("keepInformedEmpNameItt");

			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(10);
			} else {
				getNavigationPanel(2);
			}
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model.getJobDetails(manPowerReqs);
			getVacancyDet();

			setDefaultManagerName();// Added by Nilesh.
			setApproverList(manPowerReqs.getHiringcode());

			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, manPowerReqs);

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/*
	 * Generate the report for particular application
	 */
	public String reportFun() {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model.getReport(request, response, manPowerReqs, "Pdf");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} // end of report

	public String reportFunText() {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model.getReport(request, response, manPowerReqs, "Txt");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String f9replacementEmp() throws Exception {
		String query = "";
		if (manPowerReqs.getReplaceEmpId().equals("")
				|| manPowerReqs.getReplaceEmpId().equals("null")
				|| manPowerReqs.getReplaceEmpId().equals(null)) {
			query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC WHERE 1=1 ";
		} else {
			query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC"
					+ " WHERE EMP_ID NOT IN ("
					+ manPowerReqs.getReplaceEmpId()
					+ ")";
		}

		if (manPowerReqs.getUserProfileDivision() != null
				&& manPowerReqs.getUserProfileDivision().length() > 0) {
			query += " AND EMP_DIV IN ("
					+ manPowerReqs.getUserProfileDivision() + ")";
		}

		String[] headers = { "Employee Token", "Employee Name" };
		String[] headerWidth = { "10", "30" };
		String[] fieldNames = { "dummyTokenForReplace", "hiddenEmpName",
				"hiddenEmpId" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlage = "true";
		String submitToMethod = "EmployeeRequi_replaceEmpList.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	} // end of f9replacementEmp method

	public String replaceEmpList() throws Exception {
		try {
			if (manPowerReqs.getReplaceEmpId().equals("")
					|| manPowerReqs.getReplaceEmpId().equals("null")
					|| manPowerReqs.getReplaceEmpId().equals(null)) {
				manPowerReqs.setReplaceEmpId(manPowerReqs.getHiddenEmpId());
				manPowerReqs.setReplaceEmpName(manPowerReqs
						.getDummyTokenForReplace()
						+ "-" + manPowerReqs.getHiddenEmpName());
			} else {
				String hiddenEmpId = manPowerReqs.getHiddenEmpId();
				String finalEmpId = manPowerReqs.getReplaceEmpId();
				finalEmpId = finalEmpId + "," + hiddenEmpId;
				manPowerReqs.setReplaceEmpId(finalEmpId);
				String hiddenEmpName = manPowerReqs.getDummyTokenForReplace()
						+ "-" + manPowerReqs.getHiddenEmpName();
				String finalEmpName = manPowerReqs.getReplaceEmpName();
				finalEmpName = finalEmpName + ",\n" + hiddenEmpName;
				manPowerReqs.setReplaceEmpName(finalEmpName);
			}
			/**
			 * added by varun p khetan if we navigated from any other location
			 */
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(10);
			} else {
				getNavigationPanel(2);
			}
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			String[] serialNo = request.getParameterValues("serialNo");
			String[] empCode = request.getParameterValues("empid");
			String[] empName = request
					.getParameterValues("keepInformedEmpNameItt");
			model.initiate(context, session);
			model.getJobDetails(manPowerReqs);
			getVacancyDet();
			setApproverList(manPowerReqs.getHiringcode());
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, manPowerReqs);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String removeEmployee() throws Exception {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model.removeEmpList(manPowerReqs);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "removeEmp";
	}

	public String removeEmpFrmList() throws Exception {
		try {
			if (manPowerReqs.getOfferAppointFlag().equals("true")) {
				getNavigationPanel(10);
			} else {
				getNavigationPanel(2);
			}
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String[] empId = request.getParameterValues("removeEmpId");
			String[] deleteCode = request.getParameterValues("hdeleteCode");
			String[] checkBox = request.getParameterValues("check");

			ArrayList<String> list = new ArrayList<String>();
			replaceEmpList();
			if (empId != null) {
				for (int i = 0; i < empId.length; i++) {
					list.add(String.valueOf(empId[i]));
				}
			}

			if (deleteCode != null) {
				for (int i = 0; i < deleteCode.length; i++) {
					if (list.contains(String.valueOf(deleteCode[i]))) {
						list.remove(String.valueOf(deleteCode[i]));
					}
				}
			}

			String replaceEmpStr = "";
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.size() == 1) {
						replaceEmpStr = String.valueOf(list.get(i));
					} else {
						replaceEmpStr += String.valueOf(list.get(i)) + ",";
					}
				}
				replaceEmpStr = replaceEmpStr.substring(0, replaceEmpStr
						.length() - 1);
			}

			if (manPowerReqs.getReqCode().trim().equals("")) {
				replaceEmpList();
				// manPowerReqs.setReplaceEmpName(replaceEmpStr);
				addActionMessage("Employee removed successfully.");
			} else {
				if (!replaceEmpStr.equals("")) {
					String updateQuery = " UPDATE HRMS_REC_REQS_HDR SET REQS_REPLACE_EMP='"
							+ replaceEmpStr
							+ "' WHERE REQS_CODE="
							+ manPowerReqs.getReqCode().trim();
					model.getSqlModel().singleExecute(updateQuery);
					addActionMessage("Employee removed successfully.");
				}

				model.getJobDetails(manPowerReqs);
				model.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),
						request);
				model.searchVacDtl(manPowerReqs);
				model.searchQualiDtlInView(manPowerReqs, manPowerReqs
						.getReqCode(), request);
				model.searchSkillDtlInView(manPowerReqs, manPowerReqs
						.getReqCode(), request);
				model.searchDomainDtlInView(manPowerReqs, manPowerReqs
						.getReqCode(), request);
				model.searchCertDtlInView(manPowerReqs, manPowerReqs
						.getReqCode(), request);
				setApproverList(manPowerReqs.getHiringcode());
				model.terminate();
			}

			/*
			 * String[] formEmpId = manPowerReqs.getReplaceEmpId().split(",");
			 * String finalEmpId = ""; if (empId.length > 1) { for (int i = 0; i <
			 * checkBox.length; i++) { for (int j = 0; j < formEmpId.length;
			 * j++) { if (formEmpId[j].equals(empId[i])) { } else { finalEmpId +=
			 * formEmpId[j] + ","; } } } finalEmpId = finalEmpId.substring(0,
			 * finalEmpId.length() - 1);
			 * manPowerReqs.setReplaceEmpId(finalEmpId);
			 * model.getRemoveList(manPowerReqs); addActionMessage("Employee
			 * removed successfully."); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String addDataToMaster() throws Exception {
		boolean res = false;
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String jbDescName = request.getParameter("JbDescName");
			String jdCode = request.getParameter("jdCode");
			String jbDesc = request.getParameter("JbDesc");
			String roleDesc = request.getParameter("RoleDesc");
			String code1 = manPowerReqs.getJdCode();
			// String speReq=request.getParameter("SpecialReq");
			// String perReq=request.getParameter("PersoanlReq");
			res = model.updateMasterData(jbDescName, jbDesc, roleDesc,
					manPowerReqs, jdCode, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public boolean setApproverList(String empCode) {
		boolean isApproverLogin =false;
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			ReportingModel model1 = new ReportingModel();
			model1.initiate(context, session);
			Object[][] empFlow = model1.generateEmpFlow(empCode, "Recruitment");
			isApproverLogin=model.setApproverData(manPowerReqs, empFlow);
			model1.terminate();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isApproverLogin;
	}

	public String f9KeepInformedEmployee() {
		String[] eId = request.getParameterValues("empid");
		String str = "0";
		if (eId != null) {
			for (int i = 0; i < eId.length; i++) {
				str += "," + eId[i];
			}
		}
		try {
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
					+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
					+ "	FROM HRMS_EMP_OFFC ";
			query += " WHERE EMP_STATUS='S' ";
			if (manPowerReqs.getUserProfileDivision() != null
					&& manPowerReqs.getUserProfileDivision().length() > 0)
				query += "AND EMP_DIV IN ("
						+ manPowerReqs.getUserProfileDivision() + ")";
			query += " AND EMP_ID NOT IN(" + str + ") ";
			query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

			String[] headers = { getMessage("employee.id"),
					getMessage("employee") };

			String[] headerWidth = { "20", "80" };

			String[] fieldNames = { "employeeToken", "employeeName",
					"employeeId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "";
		}
	}

	public String deleteKeepInfoTo() {
		try {
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			String[] keepInformedEmpTokenItt = request
					.getParameterValues("keepInformedEmpTokenItt");
			String[] keepInformedEmpNameItt = request
					.getParameterValues("keepInformedEmpNameItt");
			String[] empid = request.getParameterValues("empid");
			try {
				model.deleteDivisionRecord(manPowerReqs,
						keepInformedEmpTokenItt, keepInformedEmpNameItt, empid);
				setDefaultManagerName();
				setApproverList(manPowerReqs.getHiringcode());
			} catch (RuntimeException e) {

				e.printStackTrace();
			}
			model.terminate();
			getNavigationPanel(2);
			manPowerReqs.setEnableAll("Y");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	public String removeKeepInformed() {
		try {
			// setApproverList(manPowerReqs.getEmployeeId());
			String[] serialNo = request.getParameterValues("serialNo");
			String[] empCode = request.getParameterValues("empid");
			String[] empName = request
					.getParameterValues("keepInformedEmpNameItt");
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model.removeKeepInformedData(serialNo, empCode, empName,
					manPowerReqs);
			addActionMessage("Employee removed successfully.");
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());
			getVacancyDet();
			// model.searchHdrRec(manPowerReqs, request);
			model.searchVacDtl(manPowerReqs);
			manPowerReqs.setEmployeeName("");
			manPowerReqs.setEmployeeId("");
			manPowerReqs.setEmployeeToken("");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return SUCCESS;

	}

	public String addKeepInformedEmpList() {
		try {
			setApproverList(manPowerReqs.getEmployeeId());
			String[] serialNo = request.getParameterValues("serialNo");
			String[] empCode = request.getParameterValues("empid");
			String[] empName = request
					.getParameterValues("keepInformedEmpNameItt");
			String[] noofVacan = request.getParameterValues("noOfVac");
			String[] vacDate = request.getParameterValues("vacDate");

			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model.displayIteratorValueForKeepInformed(serialNo, empCode,
					empName, manPowerReqs);
			model.setKeepInformed(serialNo, empCode, empName, manPowerReqs);
			setDefaultManagerName();
			setApproverList(manPowerReqs.getHiringcode());

			getVacancyDet();
			manPowerReqs.setEmployeeName("");
			manPowerReqs.setEmployeeId("");
			manPowerReqs.setEmployeeToken("");

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (manPowerReqs.getOfferAppointFlag().equals("true")) {
			getNavigationPanel(10);
		} else {
			getNavigationPanel(2);
		}
		return SUCCESS;
	}

	public void sendApplicationAlertToHiringManager(String applnID,
			String hiringCode, String approver) {
		try {
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template
					.setEmailTemplate("MANPOWER REQ MAIL FROM LAST APPROVER TO HIRING MANAGER");
			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1);
			templateQuery1.setParameter(1, approver);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2);
			templateQuery2.setParameter(1, hiringCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applnID);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, approver);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, applnID);
			template.configMailAlert();

			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
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
	 * This method is used for sending process manager alert for draft status
	 */

	public void sendProcessManagerAlertDraft() {
		try {
			Properties alertProp;
			FileInputStream alertFin;
			alertFin = new FileInputStream(getText("data_path") + "/Alerts/alertLinks.properties");
			alertProp = new Properties();
			alertProp.load(alertFin);
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String msgType = "A";
			String empID = manPowerReqs.getHiringcode();
			String module = "Resource Requisition";
			String applnID =manPowerReqs.getReqCode();
			String level = "1";
			String link = "/recruit/EmployeeRequi_callForEdit.action";
			String linkParam = "requisitionCode=" + applnID + "&applStatus=B";
			String message = alertProp.getProperty("draftAlertMessage");
			message = message.replace("<#EMP_NAME#>", manPowerReqs.getHiringManager().trim());
			message = message.replace("<#APPL_TYPE#>", module);
			template.sendProcessManagerAlertDraft(empID, module, msgType, applnID, level, linkParam, link, message, "Draft",
					empID,empID);
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
}
