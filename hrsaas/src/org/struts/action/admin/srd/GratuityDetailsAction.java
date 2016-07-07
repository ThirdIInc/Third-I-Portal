package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.GratuityDetails;
import org.paradyne.model.admin.srd.GratuityDetailsModel;
import org.struts.lib.ParaActionSupport;

public class GratuityDetailsAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(GratuityDetailsAction.class);
	GratuityDetails gratuityBean = null;
	
	public void prepare_local() throws Exception {
		gratuityBean = new GratuityDetails();
		gratuityBean.setMenuCode(1152);
	}

	public Object getModel() {
		return gratuityBean;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		GratuityDetailsModel model = null;
		try {
			model = new GratuityDetailsModel();
			model.initiate(context, session);
			if (gratuityBean.isGeneralFlag()) {
				model.getEmployeeDetails(gratuityBean.getUserEmpId(), gratuityBean);
			}// end of if
			else {
				String offcEmp = (String) request.getSession().getAttribute("srdEmpCode");
				gratuityBean.setEmpId(offcEmp);
				model.getEmployeeDetails(gratuityBean.getEmpId(), gratuityBean);
			}// end of else
		} //end of try block
		catch (Exception e) {
			logger.error(e);
		}//end of catch block
		model.getGratuityDetails(gratuityBean);
		request.setAttribute("applicationStatus", gratuityBean.getAppStatus());
		model.terminate();
	}
	
	public String save() {
		boolean result = false;
		String str = null;
		try {
			GratuityDetailsModel model = new GratuityDetailsModel();
			model.initiate(context, session);
			if (gratuityBean.getGratuityId().equals("")) {
				result = model.saveGratuityDetails(request, gratuityBean);
				str = getMessage("save");
			} else {
				result = model.updateGratuityDetails(request, gratuityBean);
				str = getMessage("update");
			}
			model.getGratuityDetails(gratuityBean);
			model.terminate();
			if (result) {
				addActionMessage(str);
			} else {
				addActionMessage(getMessage("save.error"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("applicationStatus", gratuityBean.getAppStatus());
		return "success";
	}
	
	public String f9empaction() throws Exception {

		String query = "SELECT EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	TO_CHAR(CENTER_NAME),HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID, "
				+ " NVL(TITLE_NAME,' ')||' ' ||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ' )|| ' ' || NVL(HRMS_EMP_OFFC.EMP_MNAME,' ') || ' ' || NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')	FROM HRMS_EMP_OFFC "
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ";

		query += getprofileQuery(gratuityBean);
		query += " ORDER BY EMP_ID  ";

		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };

		String[] headerWidth = { "15", "30", "27", "28" };

		String[] fieldNames = { "empToken", "empName", "centerName",
				"rankName", "empID" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String calculateGratuity(){
		try {
			GratuityDetailsModel model = new GratuityDetailsModel();
			model.initiate(context, session);
			model.calculateGratuityAmount(gratuityBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("applicationStatus", gratuityBean.getAppStatus());
		return "success";
	}
	
	public String reset(){
		try {
			gratuityBean.setGratuity("");
			gratuityBean.setClaimDate("");
			gratuityBean.setAppStatus("");
			gratuityBean.setAmountPayable("");
			gratuityBean.setPaymentMode("");
			gratuityBean.setApprovalRemark("");
			gratuityBean.setRejectionReason("");
			prepare_withLoginProfileDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public GratuityDetails getGratuityBean() {
		return gratuityBean;
	}

	public void setGratuityBean(GratuityDetails gratuityBean) {
		this.gratuityBean = gratuityBean;
	}
}
