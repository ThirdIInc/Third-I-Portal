/**
 * 
 */
package org.struts.action.payroll.salary;

import org.paradyne.bean.payroll.salary.CashBalance;
import org.paradyne.bean.payroll.salary.CashClaimNew;
import org.paradyne.model.payroll.salary.CashClaimModel;
import org.paradyne.model.payroll.salary.CashClaimModelNew;
import org.struts.lib.ParaActionSupport;

/**
 * @author REEBA JOSEPH
 *
 */
public class CashClaimActionNew extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CashClaimActionNew.class);
		
	CashClaimNew claim = null;
	
	public CashClaimNew getClaim() {
		return claim;
	}

	public void setClaim(CashClaimNew claim) {
		this.claim = claim;
	}

	@Override
	public void prepare_local() throws Exception {
		claim = new CashClaimNew();
		//claim.setMenuCode(1109);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return claim;
	}
	
	@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		CashClaimModelNew model = new CashClaimModelNew();
		model.initiate(context, session);
		
		String poolName = String.valueOf(session
				.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}
		// for getting server path where configuration files are saved.
		String dataPath = getText("data_path") + "/upload" + poolName
				+ "/claimProof/";
		// logger.info("data path " + dataPath);
		claim.setDataPath(dataPath);
		
	 	
		if (claim.isGeneralFlag()) {
			claim.setEmpId(claim.getUserEmpId());
			claim = model.getEmployeeDetails(claim
					.getUserEmpId(), claim);
		}// end of if
		else {
			model.getEmployeeDetails(claim.getUserEmpId(),
					claim);
		}// end else
		model.terminate();
		
	}
	
	@Override
	public String input() throws Exception {
		CashClaimModelNew model = new CashClaimModelNew();
		model.initiate(context, session);
		model.displayPendingList(claim, request);
		model.getBalance(claim);
		model.terminate();
		claim.setListType("pending");
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String getApprovedList() throws Exception {
		CashClaimModelNew model = new CashClaimModelNew();
		model.initiate(context, session);
		model.displayApprovedList(claim, request);
		model.terminate();
		claim.setListType("approved");
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String getRejectedList() throws Exception {
		CashClaimModelNew model = new CashClaimModelNew();
		model.initiate(context, session);
		model.displayRejectedList(claim, request);
		model.terminate();
		claim.setListType("rejected");
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String addNew() throws Exception{
		reset();
		prepare_withLoginProfileDetails();
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String reset() {
		claim.setEmpId("");
		claim.setEmpName("");
		claim.setEmpToken("");
		claim.setEmpRank("");
		claim.setEmpCenter("");
		claim.setParticulars("");
		claim.setTotalAmt("");
		claim.setClaimId("");
		claim.setClaimStatus("PENDING");
		claim.setClaimDate("");
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String f9actionEmpId() {

		String sql = "SELECT   HRMS_EMP_OFFC.EMP_TOKEN  ,  "
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ " HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME, "
				+ " HRMS_EMP_OFFC.EMP_ID,EMP_DIV,DIV_NAME  FROM HRMS_EMP_OFFC   INNER JOIN HRMS_RANK ON  "
				+ " (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "
				+ " LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT  "
				+ " LEFT JOIN HRMS_DIVISION ON DIV_ID = EMP_DIV  ";

		sql += getprofileQuery(claim);

		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };

		String[] headersWidth = { "20", "20", "20", "20" };

		String[] fieldName = { "empToken", "empName", "empCenter", "empRank",
				"empId" };

		String submitFlag = "true";

		int[] columnIndex = { 0, 1, 2, 3, 4 };
		String submitToMethod = "CashClaimNew_resetEmpDetails.action";
		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	public String resetEmpDetails() throws Exception{
		try{	
			claim.setParticulars("");
			claim.setTotalAmt("");
			claim.setClaimId("");
			claim.setIsDataAvailable("");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String view() throws Exception {
		try {
			CashClaimModelNew model = new CashClaimModelNew();
			model.initiate(context, session);
			model.viewCashBalance(claim);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String save() throws Exception {
		try {
			CashClaimModelNew model = new CashClaimModelNew();
			model.initiate(context, session);
			boolean flag = false;
			String[] claimAmt = request.getParameterValues("claimAmt");
			String[] creditId = request.getParameterValues("creditId");
			String[] claimId = request.getParameterValues("claimId");
			String[] onHldAmt = request.getParameterValues("onHoldAmt");
			String[] isProof = request.getParameterValues("hproof");
			String[] balanceAmt = request.getParameterValues("balanceAmt");
			
			String status = request.getParameter("status");

			Object[][] empFlow = generateEmpFlow(claim.getEmpId(), "Cash", 1);
			boolean isNewAppln = false;
			if (claim.getClaimId().equals("")
					|| claim.getClaimId().equals("null")) {
				isNewAppln = true;
			}
			try {
				if (empFlow == null || empFlow.equals("null")
						|| empFlow.length == 0) {
					addActionMessage("Reporting structure not defined for the employee\n"
							+ claim.getEmpName());
					addActionMessage("Please contact HR manager !");
					// showItem();
				} else {
					flag = model.saveClaim(claimAmt, creditId, claimId,
							empFlow, onHldAmt, isProof, balanceAmt, claim, status);

					if (flag) {
						if (isNewAppln) {
							model.generateApplNo(claim);
						}
						addActionMessage(getMessage("save"));
					} else {
						addActionMessage(getMessage("update"));
					}
					reset();
				}

			} catch (Exception e) {
				addActionMessage("Reporting structure not defined for the employee\n"
						+ claim.getEmpName());
				addActionMessage("Please contact HR manager !");
				e.printStackTrace();
			}
			model.terminate();

		} catch (Exception e) {
			logger.info("Exception in save" + e);
		}
		getNavigationPanel(1);
		input();
		return INPUT;
	}
	
	public String retrieveDetails() throws Exception{
		String claimId = request.getParameter("claimId");
		String empCode = request.getParameter("empCode");
		String status = request.getParameter("status");
		CashClaimModelNew model = new CashClaimModelNew();
		model.initiate(context, session);
		model.getClaimData(claim, claimId, empCode, status);
		model.terminate();
		logger.info("Status======"+status);
		if(status.trim().equals("Draft")||status.trim().equals("Pending")){
			getNavigationPanel(2);
			claim.setEnableAll("Y");
		}else{
			getNavigationPanel(3);
			claim.setEnableAll("N");
		}
		return SUCCESS;
	}
	
	public String addMultipleProof() {
		
		try {
			String[] proofFileName = request
					.getParameterValues("proofFileName");
			if(proofFileName!=null && proofFileName.length > 0){
				for (int i = 0; i < proofFileName.length; i++) {
				logger.info("######### FILE NAME ############"+String.valueOf(proofFileName[i]));
				}
			}
			logger.info("######### IN ADD PROOF METHOD ############");
			CashClaimModelNew model = new CashClaimModelNew();
			model.initiate(context, session);
			model.displayIteratorValueForUploadProof(claim, proofFileName);
			model.setProofList(claim, proofFileName);
			model.viewCashBalance(claim);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		getNavigationPanel(2);
		return SUCCESS;
	}

}
