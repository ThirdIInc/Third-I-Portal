package org.struts.action.PAS.Competency;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.PAS.Competency.CompetencyMonitoring;
import org.paradyne.model.PAS.Competency.CompetencyMonitoringModel;

public class CompetencyMonitoringAction extends ParaActionSupport {
	
	CompetencyMonitoring compMontbean;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);

	public CompetencyMonitoring getCompMontbean() {
		return compMontbean;
	}

	public void setCompMontbean(CompetencyMonitoring compMontbean) {
		this.compMontbean = compMontbean;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		compMontbean=new CompetencyMonitoring();
		compMontbean.setMenuCode(5046);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return compMontbean;
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 */
	public String input()throws Exception {
		CompetencyMonitoringModel model = new CompetencyMonitoringModel();
		model.initiate(context, session);		
		model.terminate();
		return INPUT;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String competencyStatus()throws Exception{
		CompetencyMonitoringModel model = new CompetencyMonitoringModel();
		model.initiate(context, session);	
		model.setCompApproverList(compMontbean,request);
		model.terminate();
		return INPUT;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public String sendbackCompetency() throws Exception{
		CompetencyMonitoringModel model = new CompetencyMonitoringModel();
		model.initiate(context, session);
		boolean result=false;
		result=model.sendbackCompetency(compMontbean,request);
		if(result)
		{
			addActionMessage("Competency sent back successfully");
			reset();
		}
		else
		{
			addActionMessage("Error in competency sent back ");
		}
		model.terminate();
		return INPUT;
	}
	/**
	 * use for reset the field value
	 * @return
	 * @throws Exception
	 */
	public String reset() throws Exception{
		try {
			compMontbean.setCompId("");
			compMontbean.setCompName("");
			compMontbean.setCompFrmDate("");
			compMontbean.setCompToDate("");
			compMontbean.setAssessmentDate("");
			compMontbean.setAssessmentId("");
			compMontbean.setCompEmpId("");
			compMontbean.setCompEmpName("");
			compMontbean.setCompEmpToken("");
			compMontbean.setSendbackEmpId("");
			compMontbean.setSendbackEmpName("");
			compMontbean.setSendbackEmpToken("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public String f9CompAction() throws Exception {

		String query = "SELECT COMP_NAME,TO_CHAR(COMP_FROM_DATE,'DD-MM-YYYY'), TO_CHAR(COMP_TO_DATE,'DD-MM-YYYY'),COMP_ID FROM HRMS_COMP_CONFIG "
				+ " WHERE COMP_PUBLISH_STATUS =2 ";

		query += " ORDER BY COMP_ID ";
		String[] headers = { getMessage("compName"),
				 getMessage("compFromDate"), getMessage("compToDate") };
		String[] headerWidth = { "40", "30", "30" };

		String[] fieldNames = { "compName", "compFrmDate", "compToDate",
				"compId" };

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String f9AssessmentDate() throws Exception {

		String query = "SELECT TO_CHAR(COMP_ASSESSMENT_DATE,'dd-mm-yyyy') AS ASSESSMENT_DATE,COMP_ASSESSMENT_ID " +
				"FROM HRMS_COMP_ASSESSMENT_CONFIG WHERE COMP_ID = "+compMontbean.getCompId();

		query += " ORDER BY COMP_ASSESSMENT_ID ";
		String[] headers = { "Assesment Date" };
		String[] headerWidth = { "100" };

		String[] fieldNames = { "assessmentDate", "assessmentId" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String f9CompEmpAction() throws Exception {

		String query = "SELECT EMP_TOKEN, TRIM(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ENAME,  EMP_ID FROM HRMS_COMP_EMP "+ 
						"INNER JOIN HRMS_EMP_OFFC ON (HRMS_COMP_EMP.COMP_EMP_ID = HRMS_EMP_OFFC.EMP_ID ) "+
						"WHERE COMP_ID= "+compMontbean.getCompId();

		
		String[] headers = { "Employee Token","Employee Name"};
		String[] headerWidth = { "30","70" };

		String[] fieldNames = { "compEmpToken", "compEmpName","compEmpId" };

		int[] columnIndex = { 0, 1,2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public String f9SendbackEmpAction() throws Exception {

		String query = "SELECT  HRMS_EMP_OFFC.EMP_TOKEN,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME NAME,HRMS_EMP_OFFC.EMP_ID FROM HRMS_COMP_EMP_ASSESSMENT_HDR "+ 
						"INNER JOIN HRMS_EMP_OFFC ON (HRMS_COMP_EMP_ASSESSMENT_HDR.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "+ 
						"WHERE HRMS_COMP_EMP_ASSESSMENT_HDR.COMP_ASSESSMENT_ID="+compMontbean.getAssessmentId()+" AND HRMS_EMP_OFFC.EMP_ID= "+compMontbean.getCompEmpId()+
						"UNION ALL "+
						"SELECT DISTINCT  HRMS_EMP_OFFC.EMP_TOKEN,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME NAME,HRMS_EMP_OFFC.EMP_ID FROM HRMS_COMP_EMP_ASSESSMENT_DTL "+ 
						"INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_COMP_EMP_ASSESSMENT_DTL.COMP_ASSESER_ID) "+
						"WHERE COMP_ASSESSMENT_ID="+compMontbean.getAssessmentId()+" AND COMP_EMP_ID="+compMontbean.getCompEmpId();

		
		String[] headers = { "Employee Token","Employee Name"};
		String[] headerWidth = { "30","70" };

		String[] fieldNames = { "sendbackEmpToken", "sendbackEmpName","sendbackEmpId" };

		int[] columnIndex = { 0, 1,2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
