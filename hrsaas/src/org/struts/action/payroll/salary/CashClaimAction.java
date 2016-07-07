/**
 * 
 */
package org.struts.action.payroll.salary;

import java.util.ArrayList;

import org.paradyne.bean.payroll.salary.CashClaim;
import org.paradyne.model.admin.transfer.TransferModel;
import org.paradyne.model.payroll.salary.CashClaimModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author varunk
 *
 */
public class CashClaimAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	CashClaim claim = new CashClaim();
	
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		claim = new CashClaim();
		// TODO Auto-generated method stub
		claim.setMenuCode(560);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return claim;
	}

	public CashClaim getClaim() {
		return claim;
	}

	public void setClaim(CashClaim claim) {
		this.claim = claim;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		CashClaimModel model = new CashClaimModel();
		model.initiate(context,session);
		model.sysDate(claim);
		
		if(claim.isGeneralFlag()){
			model.getGeneralDetails(claim);
			
		}
		
		model.terminate();
	}
	
	public String view() throws Exception {
		try {
			CashClaimModel model = new CashClaimModel();

			model.initiate(context,session);
			
			claim=model.viewCashBalance(getClaim());
			model.getVoucherMasterList(claim);
			ArrayList<Object> list=new ArrayList<Object>();
			list=claim.getAtt();
			 try
			 {
			 if(list.size()==0)
			 {
				// addActionMessage(getText("noData",""));
			 }
			 }
			 catch(Exception e)
			 {
				 //addActionMessage(getText("noData",""));
			 }
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	
	public String  report(){
		
		
		CashClaimModel model = new CashClaimModel();
		model.initiate(context,session);
		model.generateReport(claim,response);
		model.terminate();
		return null;
		
	}
	
	
	public String save() throws Exception {
		try
		{
			CashClaimModel model = new CashClaimModel();

			model.initiate(context,session);
			boolean flag=false;
			String [] claimAmt=request.getParameterValues("claimAmt");
			String [] creditId=request.getParameterValues("creditId");
			String [] claimId=request.getParameterValues("claimId");
			String [] onHldAmt=request.getParameterValues("onHoldAmt");
			String [] isProof=request.getParameterValues("hproof");
			String [] balanceAmt=request.getParameterValues("balanceAmt");
			
			Object[][]empFlow = generateEmpFlow(claim.getEmpId(), "Cash", 1);
			boolean isNewAppln=false;
			if(claim.getClaimId().equals("") || claim.getClaimId().equals("null") ){
				isNewAppln=true;
			}
			try {
				if (empFlow == null ||empFlow.equals("null")
						|| empFlow.length == 0) {
					addActionMessage("Reporting structure not defined for the employee\n"
							+ claim.getEmpName());
					addActionMessage("Please contact HR manager !");
					//showItem();
				} else{
					flag=model.saveClaim(claimAmt,creditId,claimId,empFlow,onHldAmt,isProof,balanceAmt,claim);
					model.saveVoucher(request,claim);
					
					if(flag)
					{
						if(isNewAppln){
							model.generateApplNo(claim);
						}
						addActionMessage(getMessage("save"));
					}
					else {
						addActionMessage(getMessage("update"));
					}
					reset();
				}
					
			} catch (Exception e) {
				addActionMessage("Reporting structure not defined for the employee\n"
						+ claim.getEmpName());
				addActionMessage("Please contact HR manager !");
				e.printStackTrace();
				//showItem();

			}
		model.terminate();
		
	} catch (Exception e) {
		logger.info("Exception in save"+e);
	}
	return "success";
}
	
	public String getClaimRecord()
	{
		try{
		CashClaimModel model = new CashClaimModel();
		model.initiate(context, session);
		claim=model.getClaimData(getClaim());
		ArrayList<Object> list=new ArrayList<Object>();
		list=claim.getAtt();
		model.getVoucherMasterList(claim);
		model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String reset() throws Exception{
		try{	
			claim.setEmpId("");
			claim.setEmpName("");
			claim.setEmpToken("");
			claim.setEmpRank("");
			claim.setEmpCenter("");
			claim.setParticulars("");
			claim.setTotalAmt("");
			claim.setTotalVchAmt("");
			claim.setClaimId("");
			claim.setStatus("PENDING");
			claim.setClaimDate("");
			claim.setApplForDivision("");
			claim.setApplForDivisionCode("");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		} 
		return "success";
	}
	public String resetEmpDetails() throws Exception{
		try{	
			claim.setParticulars("");
			claim.setTotalAmt("");
			claim.setTotalVchAmt("");
			claim.setClaimId("");
			claim.setIsDataAvailable("");
			claim.setIsSaveButton("");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		} 
		return "success";
	}
	
	public String callByApprover() {
		CashClaimModel model = new CashClaimModel();
		model.initiate(context, session);
		String claimCode = request.getParameter("hiddenClaimCode");
		model.showDetails(claim,claimCode);
		if(claim.getStatus().equals("A")){
			claim.setStatusFlag("true");
		}
		claim=model.showRecord(claim, request,claimCode);
		claim.setClaimId(claimCode);
		model.getVoucherMasterList(claim);
		ArrayList<Object> list=new ArrayList<Object>();
		list=claim.getAtt();
		claim.setIsApprove("true");
		logger.info("status===="+claim.getStatus());

		model.terminate();
		return SUCCESS;
	}
	
	
	public String f9actionEmpId() {

		String sql = "SELECT   HRMS_EMP_OFFC.EMP_TOKEN  ,  "+
		" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "+ 
		" HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME, "+
		" HRMS_EMP_OFFC.EMP_ID,EMP_DIV,DIV_NAME  FROM HRMS_EMP_OFFC   INNER JOIN HRMS_RANK ON  "+
		" (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "+
		" INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "+
		" LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT  "+
		" LEFT JOIN HRMS_DIVISION ON DIV_ID = EMP_DIV  ";
		
		sql += getprofileQuery(claim);
		

		String[] headers = { getMessage("employee.id") , getMessage("employee"),getMessage("branch"),
				getMessage("designation") };

		String[] headersWidth = { "20", "20", "20", "20" };

		String[] fieldName = { "empToken" , 
				"empName", "empCenter", "empRank", "empId","applForDivisionCode","applForDivision" };

		String submitFlag = "true";
		
		int[] columnIndex = {0,1,2,3,4,5,6};
		String submitToMethod = "CashClaim_resetEmpDetails.action";
		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	public String f9viewSavedClaim() {
		String sql ="";
		if(claim.isGeneralFlag()){
			sql = "SELECT  HRMS_EMP_OFFC.EMP_TOKEN  ,   HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "  
				+" HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,  HRMS_EMP_OFFC.EMP_ID,CLAIM_ID  "
				+" FROM HRMS_CASH_CLAIM_HDR "
				+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) "
				+" INNER JOIN HRMS_RANK ON   (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
				+" INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) " 
				+" LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT where HRMS_EMP_OFFC.EMP_ID="+claim.getUserEmpId()+" ";
			
		}else{
		 sql = "SELECT  HRMS_EMP_OFFC.EMP_TOKEN  ,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "  
					+" HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,  HRMS_EMP_OFFC.EMP_ID,CLAIM_ID  "
					+" FROM HRMS_CASH_CLAIM_HDR "
					+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) "
					+" INNER JOIN HRMS_RANK ON   (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
					+" INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) " 
					+" LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT  ";
		}
		
		String[] headers = { getMessage ("employee.id") , getMessage("employee"),getMessage("branch"),
				getMessage("designation") };

		String[] headersWidth = { "20", "20", "20", "20" };

		String[] fieldName = { "empToken" , 
				"empName", "empCenter", "empRank", "empId","claimId" };

		String submitFlag = "true";
		
		int[] columnIndex = {0,1,2,3,4,5};
		String submitToMethod = "CashClaim_getClaimRecord.action";
		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	public String f9actionApplDivision() {
		String query = " SELECT DIV_NAME,DIV_ID FROM HRMS_DIVISION ";
		 
		if(claim.getUserProfileDivision() !=null && claim.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+claim.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		String[] headers = {"Division" };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "applForDivision","applForDivisionCode"};

		int[] columnIndex = { 0, 1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
