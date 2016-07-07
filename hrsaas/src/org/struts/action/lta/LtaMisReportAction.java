/**
 * 
 */
package org.struts.action.lta;

import org.paradyne.bean.lta.LtaMisReport;
import org.paradyne.model.admin.srd.MISReportModel;
import org.paradyne.model.lta.LtaMisReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0554
 *
 */
public class LtaMisReportAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LtaMisReportAction.class);
	LtaMisReport ltaMis;
	@Override
	public void prepare_local() throws Exception {
		ltaMis=new LtaMisReport();
		ltaMis.setMenuCode(1014);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return ltaMis;
	}
	
	public String f9applDiv(){
		String query ="SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION ORDER BY UPPER (DIV_NAME)";
		String header =getMessage("division");
		String textAreaId ="paraFrm_applDivisionName";
				
		String hiddenFieldId ="paraFrm_applDivisionCode";
		
		String submitFlag ="";
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	public String f9applDept(){
		String query ="SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY UPPER (DEPT_NAME)";

		String header =getMessage("department");

		String textAreaId ="paraFrm_applDeptName";
		
		String hiddenFieldId ="paraFrm_applDeptCode";
		
		String submitFlag ="";
		
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	public String f9applBranch(){
		String query ="SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER ORDER BY UPPER (CENTER_NAME)";
		
		String header =getMessage("branch");
		
		String textAreaId ="paraFrm_applBranchName";
		
		String hiddenFieldId ="paraFrm_applBranchCode";
		
		String submitFlag ="";
		
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	public String f9applDesg(){
		String query = " SELECT RANK_ID,RANK_NAME  FROM HRMS_RANK ORDER BY UPPER(RANK_NAME)";
		
		String header =getMessage("designation");
		
		String textAreaId ="paraFrm_applDesgName";
		
		String hiddenFieldId ="paraFrm_applDesgCode";
		
		String submitFlag ="";
		
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	public String f9applGrade(){
		String query = " SELECT CADRE_ID,CADRE_NAME  FROM HRMS_CADRE ORDER BY UPPER(CADRE_NAME)";
		
		String header =getMessage("grade");
		
		String textAreaId ="paraFrm_applGradeName";
		
		String hiddenFieldId ="paraFrm_applGradeCode";
		
		
		String submitFlag ="";
		
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	
	public String f9applEType(){
		String query = " SELECT TYPE_ID,TYPE_NAME  FROM HRMS_EMP_TYPE ORDER BY UPPER(TYPE_NAME)";
		
		String header =getMessage("employee.type");
		
		String textAreaId ="paraFrm_applETypeName";
		
		String hiddenFieldId ="paraFrm_applETypeCode";
		
		String submitFlag ="";
		
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	public String f9applEmp(){
		String query = " SELECT EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ "HRMS_EMP_OFFC.EMP_LNAME) AS NAME  FROM HRMS_EMP_OFFC WHERE EMP_STATUS ='S' " 
				+" ORDER BY UPPER(NAME)";
		
		String header =getMessage("employee");
		
		String textAreaId ="paraFrm_applEmpName";
		
		String hiddenFieldId ="paraFrm_applEmpCode";
		
		String submitFlag ="";
		
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	
	public String viewOnScreen() {
		ltaMis.setBackFlag("");
		try {
			LtaMisReportModel model = new LtaMisReportModel();
			model.initiate(context, session);
			//PASSING LABEL NAMES
			String[] labelNames = { getMessage("employee.id"), getMessage("employee"),
					getMessage("branch"), getMessage("blockYearLabel"),
					getMessage("yearOfVisitLabel"), getMessage("claimDateLabel"),
					getMessage("claimTypeLabel"), getMessage("claimAmtLabel"),
					getMessage("isTaxExemptedLabel"), getMessage("yearOfExemptionLabel")};
			//CALL TO MODEL
			model.callViewScreen(ltaMis, request, labelNames);
			logger.info("viewOnScreen ended");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : "+e);
		}
		return "viewOnScreen";
	}
	
	public String generateReport(){
		logger.info("in generateReport");
		try {
			ltaMis.setExportAll("on");
			LtaMisReportModel model = new LtaMisReportModel();
			model.initiate(context, session);
			ltaMis.setBackFlag("");
			String[] labelNames = { getMessage("employee.id"), getMessage("employee"),
					getMessage("branch"), getMessage("blockYearLabel"),
					getMessage("yearOfVisitLabel"), getMessage("claimDateLabel"),
					getMessage("claimTypeLabel"), getMessage("claimAmtLabel"),
					getMessage("isTaxExemptedLabel"), getMessage("yearOfExemptionLabel")};
			model.getReport(ltaMis, response, labelNames, request);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : "+e);
		}
		return null;
	}
	public String export(){
		logger.info("in generateReport");
		try {
			LtaMisReportModel model = new LtaMisReportModel();
			model.initiate(context, session);
			ltaMis.setBackFlag("");
			String[] labelNames = { getMessage("employee.id"), getMessage("employee"),
					getMessage("branch"), getMessage("blockYearLabel"),
					getMessage("yearOfVisitLabel"), getMessage("claimDateLabel"),
					getMessage("claimTypeLabel"), getMessage("claimAmtLabel"),
					getMessage("isTaxExemptedLabel"), getMessage("yearOfExemptionLabel")};
			model.getReport(ltaMis, response, labelNames, request);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in viewOnScreen() method : "+e);
		}
		return null;
	}
	
	public String back(){
		logger.info("in generateReport");
		return SUCCESS;
	}


}
