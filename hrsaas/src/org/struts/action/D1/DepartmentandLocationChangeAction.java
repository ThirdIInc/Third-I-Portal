package org.struts.action.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.paradyne.bean.D1.DepartmentandLocationChangeBean;
import org.paradyne.bean.D1.PersonalDataChange;
import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.DepartmentChangeApprovalModel;
import org.paradyne.model.D1.DepartmentandLocationChangeModel;
import org.paradyne.model.D1.PersonalDataChangeModel;
import org.paradyne.model.admin.srd.AddressDetailsModel;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.leave.LeaveApplicationModel;
import org.struts.lib.ParaActionSupport;

public class DepartmentandLocationChangeAction extends ParaActionSupport {

	DepartmentandLocationChangeBean deptlocChangeBean;

	public void prepare_local() throws Exception {
		deptlocChangeBean = new DepartmentandLocationChangeBean();
		deptlocChangeBean.setMenuCode(1174);
	}

	public DepartmentandLocationChangeBean getDeptlocChangeBean() {
		return deptlocChangeBean;
	}

	public void setDeptlocChangeBean(
			DepartmentandLocationChangeBean deptlocChangeBean) {
		this.deptlocChangeBean = deptlocChangeBean;
	}

	public Object getModel() {

		return deptlocChangeBean;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		model.initiate(context, session);
		//model.editData(deptlocChangeBean, request);
		
		/*
		 * model.initialData(deptlocChangeBean, request);
		 * model.pendingData(deptlocChangeBean, request);
		 * model.sendBack(deptlocChangeBean, request);
		 */
		// input();
		model.terminate();
	}

	public String input() throws Exception {
		System.out.println("Inside Input method-------->");
		String userId = deptlocChangeBean.getUserEmpId();
		try{
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		model.initiate(context, session);
		model.initialData(deptlocChangeBean, request, userId);
		model.pendingData(deptlocChangeBean, request, userId);
		model.sendBack(deptlocChangeBean, request, userId);
		model.approvedData(deptlocChangeBean, request, userId);
		model.rejectedData(deptlocChangeBean, request, userId);
		model.approvedCancelData(deptlocChangeBean, request, userId);
		model.approvedRejectedData(deptlocChangeBean, request, userId);
		model.cancel(deptlocChangeBean, request, userId);
		model.terminate();
		getNavigationPanel(1);
		deptlocChangeBean.setEnableAll("Y");
		}catch(Exception e){
			//e.printStackTrace();
		}
		return "input";
	}

	/*
	 * public String getApprovedList() throws Exception { getNavigationPanel(1);
	 * try { DepartmentandLocationChangeModel model = new
	 * DepartmentandLocationChangeModel(); model.initiate(context, session);
	 * model.getApprovedList(leaveApplication, request, leaveApplication
	 * .getUserEmpId()); leaveApplication.setListType("approved");
	 * model.terminate(); } catch (Exception e) { logger.error("Exception in
	 * getApprovedList------" + e); } return SUCCESS; }
	 */

	/*
	 * public String getCancelledList() throws Exception {
	 * getNavigationPanel(1); try { DepartmentandLocationChangeModel model = new
	 * DepartmentandLocationChangeModel(); model.initiate(context, session);
	 * model.getCancelledList(leaveApplication, request, leaveApplication
	 * .getUserEmpId()); leaveApplication.setListType("cancelled");
	 * model.terminate(); } catch (Exception e) { logger.error("Exception in
	 * getCancelledList------" + e); } return SUCCESS; }
	 */

	/*
	 * public String getRejectedList() throws Exception { getNavigationPanel(1);
	 * try { DepartmentandLocationChangeModel model = new
	 * DepartmentandLocationChangeModel(); model.initiate(context, session);
	 * model.getRejectedList(leaveApplication, request, leaveApplication
	 * .getUserEmpId()); leaveApplication.setListType("rejected");
	 * model.terminate(); } catch (Exception e) { logger.error("Exception in
	 * getRejectedList------" + e); } return SUCCESS; }
	 */

	/** Add New Function* */
	public String addNew() {
		try {
			DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
			model.initiate(context, session);
			reset();
			model.getEmployeeDetails(deptlocChangeBean.getUserEmpId(),
					deptlocChangeBean);
			 setApproverName();
			int level = 1;
			// Second Approver
			String userId = deptlocChangeBean.getUserEmpId();
			String employeeCode = deptlocChangeBean.getEmpId();
			String nextApprover = userId;
			Object[][] empFlow = generateEmpFlow(employeeCode, "D1", level + 1);

			if (empFlow != null && empFlow.length > 0) {
				nextApprover = String.valueOf(empFlow[0][0]);
				setApproverName(nextApprover);
				deptlocChangeBean.setSecondAppFlag(true);
			}
			getNavigationPanel(6);
			
			setApproverName();
			//setApproverList(deptlocChangeBean.getEmpId()); // setting approver list
			//
			deptlocChangeBean.setEnableAll("Y");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Second Default Manager from reporting structure
	public void setApproverName(String nextApprover) {
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		try {
			model.initiate(context, session);

			String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id, EMP_TOKEN from hrms_emp_offc "
					+ " where emp_id=" + nextApprover;
			Object data[][] = model.getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				deptlocChangeBean.setSecondApproverName(String
						.valueOf(data[0][0]));
				deptlocChangeBean.setSecondApproverId(String
						.valueOf(data[0][1]));
				deptlocChangeBean.setSecondApproverToken(String
						.valueOf(data[0][2]));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		model.terminate();
	}

	public void setApproverNameFromCity(String nextApprover) {
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		try {
			model.initiate(context, session);

			String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id, EMP_TOKEN from hrms_emp_offc "
					+ " where emp_id=" + nextApprover;
			Object data[][] = model.getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				// deptlocChangeBean.setSecondApproverName(String.valueOf(data[0][0]));
				// deptlocChangeBean.setSecondApproverId(String.valueOf(data[0][1]));
				// deptlocChangeBean.setSecondApproverToken(String.valueOf(data[0][2]));
				deptlocChangeBean.setFirstName(String.valueOf(data[0][0]));
				deptlocChangeBean.setFirstApproverCode(String
						.valueOf(data[0][1]));
				// deptlocChangeBean.setFir(String.valueOf(data[0][2]));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		model.terminate();
	}

	public void setApproverName() {
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		try {
			// String claimId = request.getParameter("claimId");
			model.initiate(context, session);
			Object[][] empFlow = generateEmpFlow(deptlocChangeBean
					.getUserEmpId(), "D1", 1);
			if (empFlow != null && empFlow.length > 0) {
				String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id, EMP_TOKEN from hrms_emp_offc "
						+ " where emp_id=" + String.valueOf(empFlow[0][0]);
				Object data[][] = model.getSqlModel().getSingleResult(query);

				if (data != null && data.length > 0) {

					

					deptlocChangeBean.setApproverName(String
							.valueOf(data[0][0]));
					deptlocChangeBean.setFirstApproverCode(String
							.valueOf(data[0][1]));
					deptlocChangeBean.setAppToken(String.valueOf(data[0][2]));
				}
			} else {

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.terminate();
	}

	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {

		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		model.initiate(context, session);
		input();
		// model.initialData(deptlocChangeBean, request);
		getNavigationPanel(1);
		model.terminate();

		return "input";
	}

	/** Method call for Saving Data* */
	public String draft() throws Exception {
		try {

			DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
			model.initiate(context, session);
			boolean result;
			
			// String status = request.getParameter("status");
			String status = deptlocChangeBean.getApplnActualStatus();
			System.out.println("status---" + status);
			if (deptlocChangeBean.getDeptCode().equals("")) {
				result = model.addDeptData(deptlocChangeBean, request, "D");
				
				if (result) {

					addActionMessage(getMessage("save"));

				} else {
					addActionMessage(getMessage("duplicate"));
					reset();
					// new added
				}
			} else {

				result = model.update(deptlocChangeBean, request, status);

				if (result) {
					addActionMessage(getMessage("update"));

				} else {
					addActionMessage(getMessage("duplicate"));
					reset();// new added

				}

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		// reset();
		prepare_withLoginProfileDetails();
		input();
		// getNavigationPanel(2);
		getNavigationPanel(1);

		return "input";

	}

	/** This method is called on reset button */
	public String reset() {
		
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		model.initiate(context, session);
		
		
		deptlocChangeBean.setDraftFlag(false);
		deptlocChangeBean.setDeptCode("");
		deptlocChangeBean.setEmpName("");
		deptlocChangeBean.setEmpNum("");
		deptlocChangeBean.setEmpId("");
		deptlocChangeBean.setFname("");
		deptlocChangeBean.setMname("");
		deptlocChangeBean.setLname("");
		deptlocChangeBean.setExecutive("");
		deptlocChangeBean.setRegion("");
		deptlocChangeBean.setArea("");
		deptlocChangeBean.setDeptNum("");
		deptlocChangeBean.setEffectivedateofChange("");
		deptlocChangeBean.setFrom_department_Num("");
		deptlocChangeBean.setTo_department_Num("");
		deptlocChangeBean.setFrom_workPhone("");
		deptlocChangeBean.setTo_workPhone("");
		deptlocChangeBean.setFrom_managerName("");
		deptlocChangeBean.setTo_managerName("");
		deptlocChangeBean.setPhysicaLocation("");
		deptlocChangeBean.setApproName("");
		deptlocChangeBean.setFirstName("");
		deptlocChangeBean.setAddress1("");
		deptlocChangeBean.setAddress2("");
		deptlocChangeBean.setAddress3("");
		deptlocChangeBean.setCity("");
		deptlocChangeBean.setState("");
		deptlocChangeBean.setCountry("");
		deptlocChangeBean.setPhone1("");
		deptlocChangeBean.setPhone2("");
		deptlocChangeBean.setWorkHome("");
		deptlocChangeBean.setApproName("");
		deptlocChangeBean.setApproverCode("");
		deptlocChangeBean.setSelectapproverName("");
		deptlocChangeBean.setApproverToken("");
		model.getEmployeeDetails(deptlocChangeBean.getUserEmpId(),
				deptlocChangeBean);
		setApproverName();
		getNavigationPanel(2);
		
		model.terminate();
		return "success";

	}

	public String approve() throws Exception {
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		model.initiate(context, session);
		boolean result;
		
		// String status = request.getParameter("status");
		String status = "P";

		if (deptlocChangeBean.getDeptCode().equals("")) {
			result = model.addDeptData(deptlocChangeBean, request, status);
			if (result) {

				addActionMessage("Application sent for approver. \n Tracking Number"+ " "+deptlocChangeBean.getAuthorizedToken());

			} else {
				addActionMessage("Application can not be sent for approver.");
				reset();
				// new added
			}
		} else {

			result = model.update(deptlocChangeBean, request, status);

			if (result) {
				addActionMessage("Application sent for approver. \n Tracking Number"+ " "+deptlocChangeBean.getAuthorizedToken());
			} else {
				addActionMessage("Application can not be sent for approver.");
				reset();// new added
			}
		}
		
		// Mail fire
		Object[][] empFlow = generateEmpFlow(deptlocChangeBean.getUserEmpId(),
				"D1", 1);
		if (empFlow != null && empFlow.length > 0) {
			String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id from hrms_emp_offc "
					+ " where emp_id=" + String.valueOf(empFlow[0][1]);
			Object data[][] = model.getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				deptlocChangeBean.setApproverName(String.valueOf(data[0][0]));
				deptlocChangeBean.setFirstApproverCode(String
						.valueOf(data[0][1]));

				String approver = String.valueOf(data[0][1]);
			}

		} else {

		}
		model.terminate();
		
		deptlocChangeBean.setDeptCode(deptlocChangeBean.getDeptCode());
		sendMail(empFlow);
		input();
		return "input";
	}

	public void sendMail(Object[][] empFlow) {

		try {
			String applicationID = deptlocChangeBean.getDeptCode();

			// Mail From Applicant to Approver

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);

			template
					.setEmailTemplate("D1-DEPARTMENT/LOCATION  DATA CHANGE APPLICATION DETAILS TO APPROVER");

			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery1.setParameter(1, deptlocChangeBean.getEmpId());

			
			// EMAIL

			String approverCode = "";
			if (!deptlocChangeBean.getFirstApproverCode().equals("") && deptlocChangeBean.getFirstApproverCode()!=null){
				approverCode=deptlocChangeBean.getFirstApproverCode();
			}else if (!deptlocChangeBean.getApproverCode().equals("") && deptlocChangeBean.getApproverCode() != null) {
				approverCode = deptlocChangeBean.getApproverCode();
			}  
			/* * else { approverCode =
				 * deptlocChangeBean.getFirstApproverCode(); }
				 */
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			templateQuery2.setParameter(1, approverCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationID);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationID);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, applicationID);

			template.configMailAlert();
			// template.sendProcessManagerAlert(approver, module, msgType,
			// applnID, level);
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();

			// Mail From Applicant to Approver End

		} catch (Exception e) {
			// logger.error(e);
		}

	}

	/*
	 * This function called when edit button clicked on jsp after record get
	 * saved
	 */
	public String edit() throws Exception {
		try {
			DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
			model.initiate(context, session);
			// model.calforedit(deptlocChangeBean);
			model.terminate();
			getNavigationPanel(2);
			deptlocChangeBean.setEnableAll("Y");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/** This method is called on back button */
	public String cancel() {
		try {
			deptlocChangeBean.setDeptCode("");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	/*
	 * This function called when edit button clicked on jsp after record get
	 * saved
	 */
	public String retriveDetails() throws Exception {
		try {
			DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
			model.initiate(context, session);
			setApproverName();
			model.editData(deptlocChangeBean, request);
			request.setAttribute("workLocation", deptlocChangeBean.getRadioYNValue());
			if(deptlocChangeBean.getDeptCode()!=null){
				deptlocChangeBean.setDraftFlag(true);
			}else{
				deptlocChangeBean.setDraftFlag(false);
			}
			String status = deptlocChangeBean.getApplnStatus();
			System.out.println("deptlocChangeBean.getApplnStatus()=" + deptlocChangeBean.getApplnStatus());
			if(status.equals("B")){
				System.out.println("deptlocChangeBean.getApplnStatus() IFFFFFFFF=" + deptlocChangeBean.getApplnStatus());
				deptlocChangeBean.setForApproval(true);
			}else{
				System.out.println("deptlocChangeBean.getApplnStatus() IFF ELSEEEEEE=" + deptlocChangeBean.getApplnStatus());
				deptlocChangeBean.setForApproval(false);
			}
			Object[][] apprCommentListObj = model.getApproverCommentList(deptlocChangeBean.getDeptCode());
			populateApproverComments(apprCommentListObj);
			
			model.terminate();
			getNavigationPanel(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String retrivePendingDetails() throws Exception {
		try {
			DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
			model.initiate(context, session);
			String deptDataId = request.getParameter("appno");
			
			
			/*
			 * FOR SUPER USER
			 */
			String applCode=request.getParameter("applCode");
			if(applCode !=null &&applCode.length()>0){
				deptDataId=applCode;
			}
			
			model.view(deptlocChangeBean, request, deptDataId);
			Object[][] apprCommentListObj = model.getApproverCommentList(deptDataId);
			populateApproverComments(apprCommentListObj);
			request.setAttribute("workLocation", deptlocChangeBean
					.getRadioYNValue());
			model.terminate();
			getDefaultManagerName();
			getNavigationPanel(4);
			deptlocChangeBean.setEnableAll("N");
			deptlocChangeBean.setForApproval(true);
			
			if(applCode !=null &&applCode.length()>0){
				getNavigationPanel(0);
				deptlocChangeBean.setEnableAll("N");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	
	public String retriveApprovalDetails() throws Exception {
		try {
			DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
			model.initiate(context, session);
			String id = request.getParameter("appno");
			model.view(deptlocChangeBean, request, id);
			Object[][] apprCommentListObj = model.getApproverCommentList(id);
			populateApproverComments(apprCommentListObj);
			request.setAttribute("workLocation", deptlocChangeBean
					.getRadioYNValue());
			model.terminate();
			getDefaultManagerName();
			getNavigationPanel(3);
			deptlocChangeBean.setEnableAll("N");
			deptlocChangeBean.setForApproval(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String getDefaultManagerName() throws Exception {
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		model.initiate(context, session);
		model.getDefaultManagerName(deptlocChangeBean);
		// addDetRecord();
		getNavigationPanel(2);
		model.terminate();
		return SUCCESS;
	}

	// Only view

	public String viewDepartmentDetails() throws Exception {
		try {
			DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
			model.initiate(context, session);
			//String id = request.getParameter("levApplicationCode");
			

			
			
			deptlocChangeBean.setApproverComments("");
			String deptDataId = request.getParameter("deptDataId");
			model.view(deptlocChangeBean, request, deptDataId);
			
			Object[][] apprCommentListObj = model.getApproverCommentList(deptDataId);
			populateApproverComments(apprCommentListObj);
			
			
			request.setAttribute("physicalLocation", deptlocChangeBean
					.getRadioValue());
			request.setAttribute("workLocation", deptlocChangeBean
					.getRadioYNValue());
			model.terminate();
			
			deptlocChangeBean.setEnableAll("N");
			deptlocChangeBean.setForApproval(true);
			
		    getNavigationPanel(4);
//			/getNavigationPanel(2);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "viewDetails";
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		model.initiate(context, session);
		boolean result;
		result = model.delete(deptlocChangeBean, request);
		if (result) {
			addActionMessage("Record Deleted successfully.");
		}
		reset();
		model.terminate();

		getNavigationPanel(1);
		input();
		return "input";
	}

	/** This f9action is for Search pop up window */
	public String f9empaction() throws Exception {

		try {
			/*
			 * String query = " SELECT EMP_TOKEN,to_char(EMP_FNAME ||' '
			 * ||EMP_MNAME||' ' ||EMP_LNAME),RANK_NAME,REGION_NAME, " +
			 * "DEPT_NAME,HRMS_D1_DEPARTMENT.DEPT_NUMBER,HRMS_D1_DEPARTMENT.DEPT_NUMBER
			 * ,ADD_PH1,to_char(HRMS_EMP_OFFC.EMP_FNAME||' '||
			 * HRMS_EMP_OFFC.EMP_MNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME)
			 * ,HRMS_EMP_OFFC.EMP_ID ,EMP_REPORTING_OFFICER" + " FROM
			 * HRMS_EMP_OFFC" + " LEFT JOIN HRMS_EMP_ADDRESS
			 * ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) " + " LEFT JOIN
			 * hrms_rank ON(hrms_rank.RANK_ID = HRMS_EMP_OFFC.EMP_EXECUTIVE_ID) " + "
			 * LEFT JOIN hrms_d1_region ON(hrms_d1_region.REGION_ID =
			 * HRMS_EMP_OFFC.EMP_REGION_ID) " + " LEFT JOIN hrms_dept
			 * ON(hrms_dept.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) " + " Left join
			 * HRMS_D1_DEPARTMENT
			 * on(HRMS_D1_DEPARTMENT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT_NO) " + "
			 * Left join HRMS_D1_DEPT_LOC_CHANGE on
			 * (HRMS_EMP_OFFC.emp_id=HRMS_D1_DEPT_LOC_CHANGE.DEPT_LOC_MANAGER) ";
			 */

			/*String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,to_char(HRMS_EMP_OFFC.EMP_FNAME ||' ' ||HRMS_EMP_OFFC.EMP_MNAME||' ' ||HRMS_EMP_OFFC.EMP_LNAME),RANK_NAME,REGION_NAME, "
					+ "  hrms_dept.DEPT_NAME,HRMS_D1_DEPARTMENT.DEPT_NUMBER,HRMS_D1_DEPARTMENT.DEPT_NUMBER ,HRMS_EMP_ADDRESS.ADD_PH1,HRMS_EMP_OFFC.EMP_REPORTING_OFFICER ,HRMS_EMP_OFFC.EMP_ID "
					+ "  FROM HRMS_EMP_OFFC "
					+ " inner JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN hrms_rank ON(hrms_rank.RANK_ID = HRMS_EMP_OFFC.EMP_EXECUTIVE_ID) "
					+ "  LEFT JOIN hrms_d1_region ON(hrms_d1_region.REGION_ID = HRMS_EMP_OFFC.EMP_REGION_ID) "
					+ " LEFT JOIN hrms_dept  ON(hrms_dept.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) "
					+ "  Left join HRMS_D1_DEPARTMENT on(HRMS_D1_DEPARTMENT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT_NO) ";*/
			
			
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,to_char(HRMS_EMP_OFFC.EMP_FNAME ||' ' ||HRMS_EMP_OFFC.EMP_MNAME||' ' ||HRMS_EMP_OFFC.EMP_LNAME),RANK_NAME,REGION_NAME, " 
				+ "  hrms_dept.DEPT_NAME,hrms_dept.DEPT_NAME,hrms_dept.DEPT_NAME ,HRMS_EMP_ADDRESS.ADD_PH1,HRMS_EMP_OFFC.EMP_REPORTING_OFFICER ,HRMS_EMP_OFFC.EMP_ID "
				+ "  FROM HRMS_EMP_OFFC "
				+ "  inner JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
				+ "  LEFT JOIN hrms_rank ON(hrms_rank.RANK_ID = HRMS_EMP_OFFC.EMP_EXECUTIVE_ID) "
				+ "  LEFT JOIN hrms_d1_region ON(hrms_d1_region.REGION_ID = HRMS_EMP_OFFC.EMP_REGION_ID) "
				+ "  LEFT JOIN hrms_dept  ON(hrms_dept.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT_NO) "
				+ "  LEFT JOIN hrms_dept dept ON(dept.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
				+ "  Left join HRMS_D1_DEPARTMENT on(HRMS_D1_DEPARTMENT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT_NO) " ;
			
			query += getprofileQuery(deptlocChangeBean);
			query += " AND EMP_STATUS='S'";
			query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

			/*
			 * String[] headers = { getMessage("emp_no"),
			 * getMessage("first_name"), getMessage("middle_name"),
			 * getMessage("last_name"),getMessage("executive"),getMessage("region"),getMessage("area"),
			 * getMessage("dept_no"), getMessage("dept_no"),
			 * getMessage("work_phone"), getMessage("manager_name"), "emp_id" };
			 */

			/*
			 * String[] headerWidth = { "10", "15", "15","15","15","15","15",
			 * "5", "5", "10", "30", "10" };
			 */

			/*
			 * String[] headers = { "Token", getMessage("first_name"),
			 * getMessage("executive"),getMessage("region"),getMessage("area"),
			 * getMessage("dept_no"), getMessage("dept_no"),
			 * getMessage("work_phone"), getMessage("manager_name"), "emp_id" };
			 */

			String[] headers = {"Employee Id", "Employee Name" };

			String[] headerWidth = { "10", "35"};
			/*
			 * String[] fieldNames = { "empNum", "fname", "mname",
			 * "lname","executive","region","area", "deptNum",
			 * "from_department_Num", "from_workPhone", "from_managerName",
			 * "empId","firstApproverCode" };
			 */
			String[] fieldNames = { "empNum", "empName", "executive",
					"region", "area", "deptNum", "from_department_Num",
					"from_workPhone", "firstApproverCode","empId" };

			int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

			String submitFlag = "true";

			String submitToMethod = "DepartmentLocationChange_getManagerName.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (RuntimeException e) {

			e.printStackTrace();
		}

		return "f9page";

	}

	public String getManagerName() throws Exception {
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		model.initiate(context, session);
		model.getManagerName(deptlocChangeBean.getFirstApproverCode(),
				deptlocChangeBean);
		
		getNavigationPanel(2);
		model.terminate();
		return SUCCESS;
	}

	/** This f9action is for Search pop up window */
	public String f9dept() throws Exception {

		//String query = "SELECT DEPT_ID,DEPT_NUMBER FROM HRMS_D1_DEPARTMENT";
		String query = "SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID DESC";
		/*if (deptlocChangeBean.getUserProfileDivision()!=null && deptlocChangeBean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_DEPT.DEPT_DIV_CODE IN ("
					+ deptlocChangeBean.getUserProfileDivision() + ")";
		}
		else
		{
			query += " WHERE 1=1 ";
		}
*/
		
		String[] headers = { "Department ID", "Department Name" };

		String[] headerWidth = { "20", "50" };

		String[] fieldNames = { "to_departmentID", "to_department_Num" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/** This f9action is for Search pop up window */
	public String f9manager() throws Exception {

		String query = "SELECT EMP_TOKEN,to_char(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME),EMP_ID from HRMS_EMP_OFFC";
		//query += getprofileQuery(deptlocChangeBean);
		
		if (deptlocChangeBean.getUserProfileDivision()!=null && deptlocChangeBean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ deptlocChangeBean.getUserProfileDivision() + ")";
		}
		else
		{
			query += " WHERE 1=1 ";
		}

		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN( " + deptlocChangeBean.getEmpId() + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		
		

		String[] headers = { "Manager ID", "Manager Name"};

		String[] headerWidth = { "30", "50" };

		String[] fieldNames = {"managerToken" , "to_managerName","to_managerID" };

		int[] columnIndex = { 0, 1 ,2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/** This f9action is for Search pop up window */
	public String f9city() throws Exception {

		String query = "SELECT LOCATION_CODE,LOCATION_NAME FROM HRMS_LOCATION";

		String[] headers = { "CityId", getMessage("city") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "cityId", "city" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "true";

		String submitToMethod = "DepartmentLocationChange_getState.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String getState() throws Exception {
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		model.initiate(context, session);
		model.getStateCountry(deptlocChangeBean);
		getManagerName();
		// setApproverNameFromCity(deptlocChangeBean.getFirstApproverCode());
		// addDetRecord();

		/*
		 * int level=1; //Second Approver String userId =
		 * deptlocChangeBean.getUserEmpId(); String employeeCode =
		 * deptlocChangeBean.getEmpId(); String nextApprover = userId;
		 * Object[][] empFlow = generateEmpFlow(employeeCode, "D1", level+1);
		 * 
		 * if(empFlow != null && empFlow.length > 0) { nextApprover =
		 * String.valueOf(empFlow[0][0]); setApproverName(nextApprover);
		 * deptlocChangeBean.setSecondAppFlag(true); }
		 */
		getNavigationPanel(2);
		model.terminate();

		return SUCCESS;
	}

	/** This f9action is for Search pop up window */
	public String f9approver() throws Exception {
		String str = "0";

		if (deptlocChangeBean.getFirstApproverCode() != null
				&& !deptlocChangeBean.getFirstApproverCode().equals("")
				&& !deptlocChangeBean.getFirstApproverCode().equals("null")) {
			str = deptlocChangeBean.getFirstApproverCode().trim();
		} else {
			str = "0";
		}
		String query = " select EMP_TOKEN,to_char(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME),EMP_ID from HRMS_EMP_OFFC";
		// + " where EMP_STATUS='S' ORDER BY EMP_FNAME";

		//query += getprofileQuery(deptlocChangeBean);
		if (deptlocChangeBean.getUserProfileDivision()!=null && deptlocChangeBean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ deptlocChangeBean.getUserProfileDivision() + ")";
		}
		else
		{
			query += " WHERE 1=1 ";
		}

		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + str + ","
				+ deptlocChangeBean.getEmpId() + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { "Employee Id", "Employee Name" };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "approverToken", "selectapproverName",
				"approverCode" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	// Added by janisha for Approval
	private void populatedeptDataChangeDetails(Object[][] persDataObj) {
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		deptlocChangeBean.setApproverComments(model.checkNull(String
				.valueOf(persDataObj[0][0])));
	}

	private void populatedeptDataHistoryChangeDetails(
			Object[][] deptHistoryDataObj) {
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		deptlocChangeBean.setComment(model.checkNull(String
				.valueOf(deptHistoryDataObj[0][0])));
	}

	public String callView() {
		DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
		// String claimId = request.getParameter("claimId");
		model.initiate(context, session);
		String deptDataId = request.getParameter("deptDataId");
		// bean.setHiddenCode(personalDataId);

		model.view(deptlocChangeBean, request, deptDataId);

		model.terminate();
		// logger.info("claimId end==" + claimId);
		return SUCCESS;

	}

	private void populateApproverComments(Object[][] apprCommentListObj) {
		List<Object> approverCommentList = new ArrayList<Object>(
				apprCommentListObj.length);

		for (int i = 0; i < apprCommentListObj.length; i++) {
			DepartmentandLocationChangeBean deptDataChange = new DepartmentandLocationChangeBean();
			deptDataChange
					.setApprName(String.valueOf(apprCommentListObj[i][0]));
			if(!String.valueOf(apprCommentListObj[i][1]).equals("") && String.valueOf(apprCommentListObj[i][1])!=null && !String.valueOf(apprCommentListObj[i][1]).equals("null")){
				deptDataChange.setApprComments(String.valueOf(apprCommentListObj[i][1]));
			}else{
				deptDataChange.setApprComments("");
			}
			deptDataChange
					.setApprDate(String.valueOf(apprCommentListObj[i][2]));
			deptDataChange.setApprStatus(String
					.valueOf(apprCommentListObj[i][3]));
			approverCommentList.add(deptDataChange);
		}

		deptlocChangeBean.setApproverCommentList(approverCommentList);
	}

	public String viewDetails() {
		try {
			String deptDataId = request.getParameter("deptDataId");
			deptlocChangeBean.setForApproval(true);
			deptlocChangeBean.setDeptCode(deptDataId);

			DepartmentChangeApprovalModel model = new DepartmentChangeApprovalModel();
			model.initiate(context, session);
			Object[][] persDataObj = model.getDeptDataChangeDetails(deptDataId);

			Object[][] apprCommentListObj = model
					.getApproverCommentList(deptDataId);
			populateApproverComments(apprCommentListObj);
			model.terminate();

			callView();
			getDefaultManagerName();

			String status = String.valueOf(persDataObj[0][2]);

			if (status.equals("P") || status.equals("C")) {
				getNavigationPanel(5);
				deptlocChangeBean.setForApproval(true);
				deptlocChangeBean.setEnableAll("N");
			} else if (status.equals("F")) {
				String userId = deptlocChangeBean.getUserEmpId();
				boolean isUserAHRApprover = model.isUserAHRApprover(userId);

				if (isUserAHRApprover) {
					getNavigationPanel(5);
					deptlocChangeBean.setEnableAll("N");
				} else {
					// readOnlyDetails = true;
					deptlocChangeBean.setForApproval(false);
					deptlocChangeBean.setEnableAll("N");
					getNavigationPanel(4);
				}
				// getNavigationPanel(2);
			} else {
				// readOnlyDetails = true;
				deptlocChangeBean.setForApproval(false);
				deptlocChangeBean.setEnableAll("N");
				getNavigationPanel(4);
			}
			
			/*if(status.equals("A") && status.equals("R")){
				deptlocChangeBean.setEnableAll("N");
				deptlocChangeBean.setForApproval(false);
				getNavigationPanel(4);
			}*/
			// return "viewDetails";
			return "viewApprove";
		} catch (Exception e) {
			// logger.error(e);
			return "";
		}
	}

	public String approveData() {
		try{
		String deptDataChangeId = deptlocChangeBean.getDeptCode();
		String approverComments = deptlocChangeBean.getApproverComments();
		String userId = deptlocChangeBean.getUserEmpId();
		String employeeCode = deptlocChangeBean.getEmpId();
		DepartmentChangeApprovalModel model = new DepartmentChangeApprovalModel();
		model.initiate(context, session);
		String status = deptlocChangeBean.getApplnStatus();
		String test = "";
		
		Object[][] persDataObj = model.getDeptDataChangeDetails(deptDataChangeId);
		status = String.valueOf(persDataObj[0][2]);
		
		int level = Integer.parseInt(deptlocChangeBean.getLevel());

		String nextApprover = userId;
		Object[][] empFlow = generateEmpFlow(employeeCode, "D1", level + 1);

		if (empFlow != null && empFlow.length > 0) {
			nextApprover = String.valueOf(empFlow[0][0]);
			level += 1;
		}


		// test=model.approve(deptDataChangeId, approverComments,
		// userId,status,nextApprover,level);
		test = model.approve(deptDataChangeId, approverComments, userId,
				status, nextApprover, level);

		String str="Application approved successfully.";
		 request.setAttribute("messageStr", str);
		// Mail
		if (model.isUserAMagaer(deptDataChangeId, userId) && test.equals("F")) {
			sendApprovalMail(deptDataChangeId, userId, employeeCode, test);
		} else if(test.equals("A")){
			sendApprovalMailToApplicantFinal(deptDataChangeId, userId,
					employeeCode, test);
		}else if(test.equals("X")){
			
			sendCancelApproveMail(deptDataChangeId, userId,
					employeeCode, test);
		}
		//Mail to Inititor
		sendApprovalMailToInititor(deptDataChangeId, userId, employeeCode, test);
		//End Mail to Inititor
		
		// End mail

		Object[][] apprCommentListObj = model
				.getApproverCommentList(deptDataChangeId);
		// populatedeptDataChangeDetails(persDataObj);
		populateApproverComments(apprCommentListObj);

		deptlocChangeBean.setApplnStatus(test);
		deptlocChangeBean.setReadOnlyDetails(true);
		deptlocChangeBean.setApproverComments("");

		request.setAttribute("physicalLocation", deptlocChangeBean
				.getRadioValue());
		request.setAttribute("workLocation", deptlocChangeBean
				.getRadioYNValue());

		getDefaultManagerName();
		model.terminate();

		deptlocChangeBean.setForApproval(true);
		// deptlocChangeBean.setForApproval(false);
		getNavigationPanel(5);
		}catch(Exception e){
			e.printStackTrace();
		}
		// return "viewDetails";
		return "approve";
		// return "viewApprove";
	}

	private void sendApprovalMailToApplicantFinal(String deptDataChangeId,
			String userId, String employeeCode, String status) {
		try {
			DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
			// MAIL FROM APPROVER TO MANAGER
			model.initiate(context, session);
			String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID  "
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " WHERE APP_SETTINGS_TYPE = 'H' AND APP_EMAIL_ID IS NOT NULL ";
			Object data[][] = model.getSqlModel().getSingleResult(query);
			
			String managerQuery="SELECT DEPT_LOC_APPROVER from HRMS_D1_DEPT_LOC_CHANGE where DEPT_LOC_ID="+deptDataChangeId;
			Object managerObj[][] = model.getSqlModel().getSingleResult(managerQuery);
			
			
			
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp
					.setEmailTemplate("D1-DEPARTMENT/LOCATION  DATA CHANGE APPLICATION DETAILS APPROVE/REJECTED/SENDBACK FROM  HR");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp
					.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userId);
			EmailTemplateQuery templateQueryApp2 = templateApp
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, employeeCode);
			EmailTemplateQuery templateQueryApp3 = templateApp
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, deptDataChangeId);
			EmailTemplateQuery templateQueryApp4 = templateApp
					.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, deptDataChangeId);

			EmailTemplateQuery templateQueryApp5 = templateApp
					.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, userId);

			EmailTemplateQuery templateQueryApp6 = templateApp
					.getTemplateQuery(6);
			//templateQueryApp6.setParameter(1, String.valueOf(data[0][0]));
			templateQueryApp6.setParameter(1, deptDataChangeId);
			
			EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, deptDataChangeId);
			
			String[] empData = null;
			if (managerObj != null && managerObj.length > 0) {
				empData = new String[managerObj.length];
				for (int i = 0; i < empData.length; i++) {
					empData[i] = String.valueOf(managerObj[i][0]);
				}
			}
			
			
			templateApp.configMailAlert();
			templateApp.sendApplicationMailToKeepInfo(empData);
			if(data != null && data.length > 0) {
				
				templateApp.sendApplicationMailToGroups(data);
			}
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void sendApprovalMail(String deptDataChangeId, String userId,
			String employeeCode, String status) {

		try {
			// if(status.equals("approved")){

			// MAIL FROM APPROVER TO APPLICANT

			try {
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template
						.setEmailTemplate("D1-DEPARTMENT/LOCATION CHANGE APPLICATION DETAILS FROM  APPROVER TO APPLICANT");
				template.getTemplateQueries();
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, userId);
				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, employeeCode);
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, deptDataChangeId);

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, deptDataChangeId);

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, deptDataChangeId);
				
				EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
				templateQuery6.setParameter(1, deptDataChangeId);

				template.configMailAlert();
				// template.sendProcessManagerAlert(approver, module, msgType,
				// applnID, level);
				template.sendApplicationMail();
				template.clearParameters();
				template.getTemplateQueries();
				template.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
				// MAIL FROM APPROVER TO MANAGER
				model.initiate(context, session);

				String query = " SELECT NVL(APP_EMAIL_ID,'') AS MANAGER_ID "
						+ " FROM HRMS_D1_APPROVAL_SETTINGS "
						+ " WHERE APP_SETTINGS_TYPE = 'H' AND APP_EMAIL_ID IS NOT NULL ";
				Object data[][] = model.getSqlModel().getSingleResult(query);
				EmailTemplateBody templateApp = new EmailTemplateBody();
				templateApp.initiate(context, session);
				templateApp
						.setEmailTemplate("D1-DEPARTMENT/LOCATION DATA CHANGE APPLICATION DETAILS FROM  APPROVER TO MANAGER");
				templateApp.getTemplateQueries();
				EmailTemplateQuery templateQueryApp1 = templateApp
						.getTemplateQuery(1); // FROM EMAIL
				templateQueryApp1.setParameter(1, userId);
				EmailTemplateQuery templateQueryApp2 = templateApp
						.getTemplateQuery(2); // TO EMAIL
				templateQueryApp2.setParameter(1, "0");
				EmailTemplateQuery templateQueryApp3 = templateApp
						.getTemplateQuery(3);
				templateQueryApp3.setParameter(1, deptDataChangeId);
				EmailTemplateQuery templateQueryApp4 = templateApp
						.getTemplateQuery(4);
				templateQueryApp4.setParameter(1, deptDataChangeId);

				EmailTemplateQuery templateQueryApp5 = templateApp
						.getTemplateQuery(5);
				templateQueryApp5.setParameter(1, "0");
				// templateQueryApp5.setParameter(1, nextApprover);

				EmailTemplateQuery templateQueryApp6 = templateApp
						.getTemplateQuery(6);
				templateQueryApp6.setParameter(1, deptDataChangeId);
				
				EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
				templateQueryApp7.setParameter(1, deptDataChangeId);

				/*String[] empData = null;
				if (data != null && data.length > 0) {
					empData = new String[data.length];
					for (int i = 0; i < empData.length; i++) {
						empData[i] = String.valueOf(data[i][0]);
					}
				}*/
				templateApp.configMailAlert();
				
				//templateApp.sendApplicationMailToKeepInfo(empData);
				if(data != null && data.length > 0) {
					templateApp.configMailAlert();
					templateApp.sendApplicationMailToGroups(data);
				}

				//templateApp.sendApplicationMail();
				templateApp.clearParameters();
				templateApp.terminate();

				model.terminate();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// logger.error(e);
		}

	}

	public String reject() {
		
		try {
			String deptDataChangeId = deptlocChangeBean.getDeptCode();
			String approverComments = deptlocChangeBean.getApproverComments();
			String userId = deptlocChangeBean.getUserEmpId();
			String employeeCode = deptlocChangeBean.getEmpId();
			DepartmentChangeApprovalModel model = new DepartmentChangeApprovalModel();
			model.initiate(context, session);
			Object[][] persDataObj = model
					.getDeptDataChangeDetails(deptDataChangeId);
			String updateStatus = "";
			updateStatus = String.valueOf(persDataObj[0][2]);
			String status = model.reject(deptDataChangeId, approverComments,
					userId, updateStatus);
			/*if(status.equals("R")){
				addActionMessage("Application rejected successfully.");
			}else{
				addActionMessage("Application not rejected .");
			}*/
			Object[][] apprCommentListObj = model
					.getApproverCommentList(deptDataChangeId);
			populateApproverComments(apprCommentListObj);
			model.terminate();
			
			
			String str="Application rejected successfully.";
			 request.setAttribute("messageStr", str);
			// Mail
			//sendRejectSenBackMailToApplicant(deptDataChangeId, userId,employeeCode, "R");
			 if(model.isUserAMagaer(deptDataChangeId, userId)) {
					sendRejectSenBackMailToApplicant(deptDataChangeId, userId, employeeCode, "R");
				} else {
					sendApprovalMailToApplicantFinal(deptDataChangeId, userId, employeeCode, "R");
				}
			//Mail to Inititor
			sendApprovalMailToInititor(deptDataChangeId, userId, employeeCode, "R");
			//End Mail to Inititor
			// End Mail
			deptlocChangeBean.setForApproval(true);
			deptlocChangeBean.setApplnStatus(status);
			getNavigationPanel(5);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "approve";
	}

	public String sendBack() {
		try{
		String Status = "";
		String deptDataChangeId = deptlocChangeBean.getDeptCode();
		String approverComments = deptlocChangeBean.getApproverComments();
		String userId = deptlocChangeBean.getUserEmpId();
		String employeeCode = deptlocChangeBean.getEmpId();
		DepartmentChangeApprovalModel model = new DepartmentChangeApprovalModel();
		model.initiate(context, session);

		Object[][] empFlow = generateEmpFlow(employeeCode, "D1", 1);
		
		String nextApprover = userId;
		//String nextApprover = deptlocChangeBean.getApproverCode();
		addActionMessage("Application send back .");
		// model.sendBack(deptDataChangeId, approverComments);
		Status = model.sendBack(deptDataChangeId, approverComments, userId,
				nextApprover);
/*if(Status!=null){
	addActionMessage("if Application send back successfully.");
}else{
	addActionMessage("else Application not send back successfully.");
}*/
		
		String str="Application send back successfully.";
		 request.setAttribute("messageStr", str);
		 
		Object[][] apprCommentListObj = model
				.getApproverCommentList(deptDataChangeId);
		populateApproverComments(apprCommentListObj);
		deptlocChangeBean.setApplnStatus(Status);
		deptlocChangeBean.setReadOnlyDetails(true);
		deptlocChangeBean.setApproverComments("");

		model.terminate();
		
		addActionMessage("last Application send back successfully.");
		// deptlocChangeBean.setForApproval(true);
		// Mail
		//sendRejectSenBackMailToApplicant(deptDataChangeId, userId,employeeCode, Status);
		
		if(model.isUserAMagaer(deptDataChangeId, userId)) {
			sendRejectSenBackMailToApplicant(deptDataChangeId, userId, employeeCode, Status);
		} else {
			sendApprovalMailToApplicantFinal(deptDataChangeId, userId, employeeCode, Status);
		}
		
		//Mail to Inititor
		sendApprovalMailToInititor(deptDataChangeId, userId, employeeCode, Status);
		//End Mail to Inititor
		// End Mail
		getNavigationPanel(5);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return "approve";
	}

	private void sendRejectSenBackMailToApplicant(String deptDataChangeId,
			String userId, String employeeCode, String status) {
		try {
			
			DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
			model.initiate(context, session);
			
			String query="SELECT DEPT_APPROVER from HRMS_D1_DEPT_DATA_PATH where DEPT_LOC_ID="+deptDataChangeId;

			Object data[][] = model.getSqlModel().getSingleResult(query);
			
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template
					.setEmailTemplate("D1-DEPARTMENT/LOCATION DATA CHANGE APPLICATION DETAILS REJECTED/SENDBACK FROM  APPROVER");
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery1.setParameter(1, userId);
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery2.setParameter(1, employeeCode);
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, deptDataChangeId);
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, deptDataChangeId);

			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, deptDataChangeId);

			EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
			templateQuery6.setParameter(1, deptDataChangeId);
			
			String[] empData = null;
			if (data != null && data.length > 0) {
				empData = new String[data.length];
				for (int i = 0; i < empData.length; i++) {
					empData[i] = String.valueOf(data[i][0]);
				}
			}
			
			template.configMailAlert();
			
			template.sendApplicationMailToKeepInfo(empData);
			// template.sendProcessManagerAlert(approver, module, msgType,
			// applnID, level);
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// End Added by janisha for Approval

	public String updateStatus() {
		String deptDataChangeId = deptlocChangeBean.getDeptCode();
		String approverComments = deptlocChangeBean.getApproverComments();
		String userId = deptlocChangeBean.getUserEmpId();

		DepartmentChangeApprovalModel model = new DepartmentChangeApprovalModel();
		model.initiate(context, session);
		String status = "";
		String approver="";
		if(!deptlocChangeBean.getApproverCode().equals("") && deptlocChangeBean.getApproverCode()!=null){
			approver=deptlocChangeBean.getApproverCode().trim();
		}else{
			approver=deptlocChangeBean.getFirstApproverCode().trim();
		}
		model.updateStatus(deptDataChangeId, approverComments, userId, status,approver);
		model.terminate();
		addActionMessage("Application cancelled successfully.");
		deptlocChangeBean.setForApproval(true);
		try{
			input();	
		}catch(Exception e){
			e.printStackTrace();
		}
		String employeeCode = deptlocChangeBean.getEmpId();
		String test="C";
		sendCancelMail(deptDataChangeId, userId, employeeCode, test);
	
		
		return "input";
	}
	
	private void sendCancelMail(String deptDataChangeId, String userId,
			String employeeCode, String status) {

		try {
			// if(status.equals("approved")){

			// MAIL FROM APPROVER TO APPLICANT

			try {
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template
						.setEmailTemplate("D1-DEPARTMENT/LOCATION DATA CHANGE APPLICATION CANCELLED BY APPLICANT");
				template.getTemplateQueries();
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, userId);
				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				String approverCode = "0";
				
				if (!deptlocChangeBean.getApproverCode().equals("")
						&& deptlocChangeBean.getApproverCode() != null) {
					approverCode = deptlocChangeBean.getApproverCode();
				} /*
					 * else { approverCode =
					 * deptlocChangeBean.getFirstApproverCode(); }
					 */
				templateQuery2.setParameter(1, approverCode);
				
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, deptDataChangeId);

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, deptDataChangeId);

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, deptDataChangeId);
				
				EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
				templateQuery6.setParameter(1, deptDataChangeId);
				
				EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
				templateQuery7.setParameter(1, deptDataChangeId);


				template.configMailAlert();
				// template.sendProcessManagerAlert(approver, module, msgType,
				// applnID, level);
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
				template.getTemplateQueries();
				template.clearParameters();
				template.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}

		
		} catch (Exception e) {
			// logger.error(e);
		}

	}
	//Cancel Approve Mail
	private void sendCancelApproveMail(String deptDataChangeId,
			String userId, String employeeCode, String status) {
		try {
			DepartmentandLocationChangeModel model = new DepartmentandLocationChangeModel();
			// MAIL FROM APPROVER TO MANAGER
			model.initiate(context, session);
		
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp
					.setEmailTemplate("D1-DEPARTMENT/LOCATION DATA CHANGE APPLICATION CANCEL DETAILS  FROM APPROVER");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp
					.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userId);
			EmailTemplateQuery templateQueryApp2 = templateApp
					.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, employeeCode);
			EmailTemplateQuery templateQueryApp3 = templateApp
					.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, deptDataChangeId);
			EmailTemplateQuery templateQueryApp4 = templateApp
					.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, deptDataChangeId);

			EmailTemplateQuery templateQueryApp5 = templateApp
					.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, deptDataChangeId);

			EmailTemplateQuery templateQueryApp6 = templateApp
					.getTemplateQuery(6);
			//templateQueryApp6.setParameter(1, String.valueOf(data[0][0]));
			templateQueryApp6.setParameter(1, deptDataChangeId);
			
			EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
			templateQueryApp7.setParameter(1, deptDataChangeId);
			
			templateApp.configMailAlert();

			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//Inititor Mail
	private void sendApprovalMailToInititor(String deptDataChangeId, String userId,
			String employeeCode, String status) {

		try {
			//System.out.println("inside inititor---"+deptDataChangeId);
			//System.out.println("inside inititor user---"+userId);
			//System.out.println("inside inititor---"+deptlocChangeBean.getCompletedByCode());
			String inititor=deptlocChangeBean.getCompletedByCode().trim();
			// MAIL FROM APPROVER TO APPLICANT

			try {
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template
						.setEmailTemplate("D1-DEPARTMENT/LOCATION CHANGE APPLICATION DETAILS FROM  APPROVER TO INITITOR");
				template.getTemplateQueries();
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, userId);
				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, inititor);
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, deptDataChangeId);

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, deptDataChangeId);

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, deptDataChangeId);
				
				EmailTemplateQuery templateQuery6 = template.getTemplateQuery(6);
				templateQuery6.setParameter(1, deptDataChangeId);
				
				
				EmailTemplateQuery templateQuery7 = template.getTemplateQuery(7);
				templateQuery7.setParameter(1, userId);

				template.configMailAlert();
				// template.sendProcessManagerAlert(approver, module, msgType,
				// applnID, level);
				template.sendApplicationMail();
				template.clearParameters();
				template.getTemplateQueries();
				template.terminate();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			// logger.error(e);
		}

	}
	/*
	 * For Selecting Approver
	 * 
	 * @return String
	 */
	public String f9deptNumber() {
		

		String query = "SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT ORDER BY  DEPT_ID DESC ";

		//query += getprofileQuery(bean);
	
		//query += "	ORDER BY HRMS_D1_DEPARTMENT.DEPT_ID";

		String[] headers = { "Department ID","Department Name"};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {  "deptCodeSelect","deptNum"};

		int[] columnIndex = { 0,1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}

}
