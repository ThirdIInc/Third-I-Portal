/**
 * 
 */
package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.RehireProcess;
import org.paradyne.model.admin.srd.RehireProcessModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0517
 *
 */
public class RehireProcessAction extends ParaActionSupport {

	RehireProcess rehireProcess;
	
	public RehireProcess getRehireProcess() {
		return rehireProcess;
	}

	public void setRehireProcess(RehireProcess rehireProcess) {
		this.rehireProcess = rehireProcess;
	}
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public void prepare_local() throws Exception {
		rehireProcess = new RehireProcess();
		rehireProcess.setMenuCode(1007);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return rehireProcess;
	}
	
	public String getEmployeeList() {
		RehireProcessModel model = new RehireProcessModel();
		model.initiate(context, session);
		model.getEmploeeList(rehireProcess, request);
		rehireProcess.setViewEmpList("true");
		model.terminate();
		return "success";
	} //end of getEmployeeList method

	public String rehireProcess()throws Exception {
		RehireProcessModel model = new RehireProcessModel();
		model.initiate(context, session);
		String employeeIds = request.getParameter("employeeIds");
		model.getReocordToProcess(rehireProcess,employeeIds);
		model.terminate();
		return "rehirePage";
	} //end of rehireProcess method
	
	public String saveProcess()throws Exception{
		try {
			RehireProcessModel model = new RehireProcessModel();
			model.initiate(context, session);
			String empIds[] = request.getParameterValues("processEmpId");
			//System.out.println("empIds : length : "+empIds.length);
			String newToken[] = request.getParameterValues("newEmpToken");
			String newTokenHidden[] = request.getParameterValues("newEmpTokenHidden");
			//System.out.println("newToken : length : "+newToken.length);
			String doj[] = request.getParameterValues("processDoj");
			String updOrInsertFlag[] = request
					.getParameterValues("updOrInsert");
			String oldToken[] = request.getParameterValues("oldEmpToken");
			String name[] = request.getParameterValues("processEmpName");
			String value = model.saveProcess(rehireProcess, empIds, newToken,
					doj, updOrInsertFlag, oldToken, name,newTokenHidden);
			model.terminate();
			if (value.equals("1")) {
				addActionMessage(" Rehire Process not completed as entered\n Employee Id already "
						+ "exists  \n Please enter different Employee Id");
				return "rehirePage";
			} //end of if
			else {
				addActionMessage("Rehire process completed successfully");
				return "success";
			} //end of if
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	} //end of saveProcess method
	
	public String f9applDiv() {
		String query = "SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION WHERE IS_ACTIVE='Y' ";
		
		if(rehireProcess.getUserProfileDivision() !=null && rehireProcess.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+rehireProcess.getUserProfileDivision() +")"; 
			query+= " ORDER BY  UPPER (DIV_NAME) ";
		
		String header = getMessage("division");
		String textAreaId = "paraFrm_applDivisionName";

		String hiddenFieldId = "paraFrm_applDivisionCode";

		String submitFlag = "";
		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}
	
	public String f9applBranch() {
		String query = "SELECT CENTER_ID, CENTER_NAME FROM HRMS_CENTER WHERE IS_ACTIVE='Y' ORDER BY UPPER (CENTER_NAME)";

		String header = getMessage("branch");

		String textAreaId = "paraFrm_applBranchName";

		String hiddenFieldId = "paraFrm_applBranchCode";

		String submitFlag = "";

		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}
	
	public String f9applDept() {
		String query = "SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT WHERE IS_ACTIVE='Y' ORDER BY UPPER (DEPT_NAME)";

		String header = getMessage("department");

		String textAreaId = "paraFrm_applDeptName";

		String hiddenFieldId = "paraFrm_applDeptCode";

		String submitFlag = "";

		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}
	
	public String f9applDesg() {
		String query = " SELECT RANK_ID,RANK_NAME  FROM HRMS_RANK WHERE IS_ACTIVE='Y' ORDER BY UPPER(RANK_NAME)";

		String header = getMessage("designation");

		String textAreaId = "paraFrm_applDesgName";

		String hiddenFieldId = "paraFrm_applDesgCode";

		String submitFlag = "";

		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	
	public String f9applEmp() {
		/*String query = " SELECT EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ "HRMS_EMP_OFFC.EMP_LNAME) AS NAME  FROM HRMS_EMP_OFFC WHERE EMP_STATUS ='N' ORDER BY UPPER(NAME)";
*/
		String query = " SELECT EMP_ID,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
			+ "HRMS_EMP_OFFC.EMP_LNAME) AS NAME  FROM HRMS_EMP_OFFC WHERE EMP_LEAVE_DATE IS NOT NULL AND ( 1=0 OR 1=1) ORDER BY UPPER(NAME)";

		String header = getMessage("employee");

		String textAreaId = "paraFrm_applEmpName";

		String hiddenFieldId = "paraFrm_applEmpCode";

		String submitFlag = "";

		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}
	
	public String f9applEmpType() {
		String query = " SELECT TYPE_ID,TYPE_NAME  FROM HRMS_EMP_TYPE WHERE IS_ACTIVE='Y' ORDER BY UPPER(TYPE_NAME)";

		String header = getMessage("employee.type");

		String textAreaId = "paraFrm_applEmpTypeName";

		String hiddenFieldId = "paraFrm_applEmpTypeCode";

		String submitFlag = "";

		String submitToMethod = "";

		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

}
