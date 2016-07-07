package org.struts.action.settlement;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jaxen.function.SubstringAfterFunction;
import org.paradyne.bean.settlement.DepartmentClearance;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.settlement.DepartmentClearanceModel;
import org.paradyne.model.settlement.SettlementDetailsReportModel;
import org.struts.lib.ParaActionSupport;

public class DepartmentClearanceAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DepartmentClearanceAction.class);

	DepartmentClearance deptClearance;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		deptClearance = new DepartmentClearance();
		deptClearance.setMenuCode(1027);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return deptClearance;
	}

	public DepartmentClearance getDeptClearance() {
		return deptClearance;
	}

	public void setDeptClearance(DepartmentClearance deptClearance) {
		this.deptClearance = deptClearance;
	}

	public String callstatus() {

		try {
			DepartmentClearanceModel model = new DepartmentClearanceModel();
			model.initiate(context, session);
			System.out.println("hi------------");
			Object deptCodeObj[][] = getDepartmentCodes();
			if (deptCodeObj != null && deptCodeObj.length > 0) {
				/*model.getAllTypeOfApplications(deptClearance, request,
						deptClearance.getUserEmpId(), deptCodeObj);*/
				model.getAllTypeOfApplications(deptClearance, request,
						deptClearance.getUserEmpId());
			}
			deptClearance.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	
	public Object[][] getupdateDeptCodes() {

		Object[][] deptCodeObj = null;
		try {
			DepartmentClearanceModel model = new DepartmentClearanceModel();
			model.initiate(context, session);
			deptCodeObj = model.getupdateDeptCodes(deptClearance);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return deptCodeObj;
	}
	
	public Object[][] getDepartmentCodes() {

		Object[][] deptCodeObj = null;
		try {
			DepartmentClearanceModel model = new DepartmentClearanceModel();
			model.initiate(context, session);
			deptCodeObj = model
					.getDepartmentCodes(deptClearance.getUserEmpId());
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return deptCodeObj;
	}

	public String retriveForApproval() {
		try {
			DepartmentClearanceModel model = new DepartmentClearanceModel();
			model.initiate(context, session);

			String resignAppCode = request.getParameter("resignApplicationNo");

			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			deptClearance.setApplicationDate(sysDate);

			String status = request.getParameter("applicationStatus");// status
			deptClearance.setResignCode(resignAppCode);
			model.getemployeeDetails(deptClearance);

			Object deptCodeObj[][] = getDepartmentCodes();

			String departmentCode = model.getDepartmentId(deptClearance
					.getUserEmpId());

			Object departmentCheck[][] = model.getDepartmentCheck(
					resignAppCode, departmentCode);

			if (departmentCheck != null && departmentCheck.length > 0) {
				if (status.equals("N") || status.equals("C")) {
					System.out.println("h------------eelo");
					model.getComments(deptClearance,departmentCode);
					model.getSavedChecklistDetails(deptClearance, request);
				}

			} else {
				System.out.println("h----888****--------eelo");
				model.getChecklistDetails(deptClearance, request, deptCodeObj);
			}
			if(status.equals("C"))
			{
				deptClearance.setClearListFlag("true");
				deptClearance.setEnableAll("N");
			}

			/*if (status.equals("P")) {
				deptClearance.setClearFlag("true");
				model.getChecklistDetails(deptClearance, request,deptCodeObj);
			}
			if (status.equals("N") || status.equals("C")) {
				deptClearance.setClearFlag("false");
				model.getComments(deptClearance);
				model.getSavedChecklistDetails(deptClearance, request);
				if (status.equals("C"))
					deptClearance.setClearListFlag("true");
			}*/

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "departmentClearanceForm";
	}

	public String getClearedList() throws Exception {

		try {
			DepartmentClearanceModel model = new DepartmentClearanceModel();
			model.initiate(context, session);

			model.getClearedList(deptClearance, request, deptClearance
					.getUserEmpId());
			deptClearance.setListType("cleared");
			deptClearance.setClearListFlag("true");
			model.terminate();
		} catch (Exception e) {

		}
		return SUCCESS;
	}

	public String report() {
		try {
			DepartmentClearanceModel model = new DepartmentClearanceModel();
			model.initiate(context, session);
			String resignCode = request.getParameter("resignCode");
			logger.info("resignCode code===="
					+ request.getParameter("resignCode"));
			if (resignCode != null
					&& !(resignCode.equals("") && !resignCode.equals("null"))) {
				deptClearance.setResignCode(resignCode);
			}
			model.getReport(request, response, deptClearance);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public String backToList() {
		try {
			DepartmentClearanceModel model = new DepartmentClearanceModel();
			model.initiate(context, session);
			Object deptCodeObj[][] = getDepartmentCodes();
			/*model.getAllTypeOfApplications(deptClearance, request,
					deptClearance.getUserEmpId(), deptCodeObj);*/
			model.getAllTypeOfApplications(deptClearance, request,
					deptClearance.getUserEmpId());
			deptClearance.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return SUCCESS;
	}

	public String saveApplication() {
		try {
			DepartmentClearanceModel model = new DepartmentClearanceModel();
			model.initiate(context, session);

			String[] checkListCodes = request
					.getParameterValues("checkListCode");
			String[] chkListComments = request
					.getParameterValues("chkListComments");
			String[] chkSubmit = request.getParameterValues("chkSubmitFlag");

			String[] departmentCode = request
					.getParameterValues("departmentCode");

			/*String query = " SELECT * FROM HRMS_DEPT_CLEARANCE_HDR WHERE RESIGN_CODE="
					+ deptClearance.getResignCode();

			Object data[][] = model.getSqlModel().getSingleResult(query);*/
			String departCode = model.getDepartmentId(deptClearance
					.getUserEmpId());

			Object data[][] = model.getDepartmentCheck(deptClearance
					.getResignCode(), departCode);

			if (data != null && data.length > 0) {
				boolean result = model.updateApplication(deptClearance,
						checkListCodes, chkListComments, chkSubmit,
						departmentCode,departCode);

				if (result) {
					addActionMessage("Record saved successfully");
				} else {
					addActionMessage("Error while saving record");
				}
			} else {
				/*boolean result = model.saveApplication(deptClearance,
						checkListCodes, chkListComments, chkSubmit,
						departmentCode);*/
				boolean result = model.updateApplication(deptClearance,
						checkListCodes, chkListComments, chkSubmit,
						departmentCode,departCode);

				if (result) {
					addActionMessage("Record saved successfully");
				} else {
					addActionMessage("Error while saving record");
				}
			}
			Object deptCodeObj[][] = getDepartmentCodes();
			/*model.getAllTypeOfApplications(deptClearance, request,
					deptClearance.getUserEmpId(), deptCodeObj);*/
			model.getAllTypeOfApplications(deptClearance, request,
					deptClearance.getUserEmpId());
			deptClearance.setListType("pending");

			model.terminate();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return SUCCESS;

	}

	public String clear() {
		String code = deptClearance.getUserEmpId();
		DepartmentClearanceModel model = new DepartmentClearanceModel();
		model.initiate(context, session);
		//	String empCode = deptClearance.getEmployeeCode();
		String hrempCode = model.getHRempCode(deptClearance.getEmployeeCode());
		//	String resignCode = deptClearance.getResignCode();
		//	String deptCode1 = deptClearance.getDeptCode();
		String[] checkListCodes = request.getParameterValues("checkListCode");
		String[] chkListComments = request
				.getParameterValues("chkListComments");
		String[] chkSubmit = request.getParameterValues("chkSubmitFlag");
		String[] departmentCode = request.getParameterValues("departmentCode");
		//model.saveApplication(deptClearance, checkListCodes,chkListComments, chkSubmit, departmentCode);

		deptClearance.setStatus("C");
		String deptCode = model.getDepartmentId(deptClearance.getUserEmpId());
		String query = " SELECT * FROM HRMS_DEPT_CLEARANCE_HDR WHERE RESIGN_CODE="
				+ deptClearance.getResignCode()+" AND DEPT_CODE =  "+deptCode;

		Object data[][] = model.getSqlModel().getSingleResult(query);
		boolean result = false;
		if (data != null && data.length > 0) {
			
			result = model.updateApplication(deptClearance, checkListCodes,
					chkListComments, chkSubmit, departmentCode,deptCode);

			if (result) {

			} else {
				addActionMessage("Error while clearing record");
			}
		} else {
			result = model.saveApplication(deptClearance, checkListCodes,
					chkListComments, chkSubmit, departmentCode);

			if (result) {

			} else {
				addActionMessage("Error while clearing record");
			}

		}

		//Here get code to display table data in template.
		String deptCodeNew = model.getdeptCode(deptClearance);
		if (hrempCode.equals("")) {
			addActionMessage("HR Manager not defined. Please configure");
			try {
				getClearedList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			if (result) {
				String tomailId = "";
				String keepInfoId = "";
				String hrempcodes[] = hrempCode.split(",");
				if (hrempcodes != null && hrempcodes.length > 0) {

					for (int i = 0; i < hrempcodes.length; i++) {
						if (i == 0) {
							tomailId += hrempcodes[i];
						} else if (i == 1) {
							keepInfoId += hrempcodes[i];
						} else {
							keepInfoId += "," + hrempcodes[i];
						}
					}

				}
				EmailTemplateBody template1 = new EmailTemplateBody();
				template1.initiate(context, session);
				template1.setEmailTemplate("DEPARTMENT CLEARANCE MAIL TO HR");
				template1.getTemplateQueries();

				EmailTemplateQuery templateQuery11 = template1
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery11.setParameter(1, code);

				EmailTemplateQuery templateQuery12 = template1
						.getTemplateQuery(2); // TO EMAIL
				templateQuery12.setParameter(1, tomailId);

				EmailTemplateQuery templateQuery13 = template1
						.getTemplateQuery(3);
				templateQuery13
						.setParameter(1, deptClearance.getEmployeeCode());

				EmailTemplateQuery templateQuery14 = template1
						.getTemplateQuery(4);
				templateQuery14
						.setParameter(1, deptClearance.getEmployeeCode());

				EmailTemplateQuery templateQuery15 = template1
						.getTemplateQuery(5);
				templateQuery15.setParameter(1, deptClearance.getResignCode());
				templateQuery15.setParameter(2, deptCodeNew);

				template1.configMailAlert();
				template1.sendApplicationMailToKeepInfo(keepInfoId);
				template1.sendApplicationMail();
				template1.clearParameters();
				template1.terminate();
				model.terminate();

				try {
					//
					addActionMessage("Mail send successfully.");
					// model.updateStatus(deptClearance, resignCode);
					callstatus();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return SUCCESS;
	}

}
