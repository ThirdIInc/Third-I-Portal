package org.paradyne.model.reimbursement;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.reimbursement.ReimbManagerApproval;
import org.paradyne.bean.reimbursement.ReimbursementClmaimApplication;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;

public class ReimbManagerApprovalModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ReimbManagerApprovalModel.class);


	public void getReimbursements(ReimbManagerApproval managerBean,
			HttpServletRequest request, String status) {
		
		ArrayList<Object> reimbursementList;
		
		try {
			String reimbursementStatusQuery = "  SELECT DISTINCT NVL(REIMB_CLAIM_ID,''), NVL(REIMB_APPL_CODE,' '), TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'), NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,' ') AS EMPLOYEE,  NVL(CREDIT_NAME,''), REIMB_TOTAL_AMOUNT, TRIM(REIMB_APPL_STATUS),"  
					+" DECODE(TRIM(REIMB_APPL_STATUS),'P','PENDING','A','APPROVED','R','REJECTED','B','SENT BACK','D','PENDING FOR DISBURSEMENT','C','CLOSED'), NVL(REIMB_EMP_ID,''), NVL(REIMB_CLAIM_APPROVER,'')  "
					+" FROM HRMS_REIMB_APPLICATION  INNER JOIN HRMS_EMP_OFFC ON (HRMS_REIMB_APPLICATION.REIMB_EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
					+" INNER JOIN HRMS_REIMB_APPL_DTL ON(HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
					+"  INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE)"
					+ " WHERE 1=1 AND REIMB_CLAIM_APPROVER="+managerBean.getUserEmpId();
			if (status.equals("processed")) {
				reimbursementStatusQuery += " AND TRIM(REIMB_APPL_STATUS) IN ('A','R','D','C')";
				managerBean.setListType("processed");
			} else if (status.equals("pending")) {
				reimbursementStatusQuery += " AND TRIM(REIMB_APPL_STATUS) IN ('P')";
				managerBean.setListType("pending");
			}
			reimbursementStatusQuery += "  ORDER BY TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY') ";
			Object reimbursementDataObj[][] = getSqlModel().getSingleResult(
					reimbursementStatusQuery);
			String[] pageIndex = Utility.doPaging(managerBean.getMyPage(),
					reimbursementDataObj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			reimbursementList = new ArrayList<Object>();
			
			if (reimbursementDataObj != null && reimbursementDataObj.length > 0) {

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {

					ReimbManagerApproval bean = new ReimbManagerApproval();

					bean.setClaimIdItt(String.valueOf(reimbursementDataObj[i][0]));// claim Id
					bean.setApplicationIdItt(String.valueOf(reimbursementDataObj[i][1]));// Application Id
					bean.setApplicationDateItt(String.valueOf(reimbursementDataObj[i][2]));// Application Date
					bean.setEmployeeNameItt(String.valueOf(reimbursementDataObj[i][3]));//Employee Name
					bean.setHeadItt(String.valueOf(reimbursementDataObj[i][4]));//Head
					bean.setClaimAmountItt(String.valueOf(reimbursementDataObj[i][5]));//Claim amount
					bean.setStatusItt(String.valueOf(reimbursementDataObj[i][6]));//status
					bean.setDecodedStatusItt(String.valueOf(reimbursementDataObj[i][7]));//decoded status
					bean.setEmployeeId(String.valueOf(reimbursementDataObj[i][8]));//employee Id
					bean.setApproverId(String.valueOf(reimbursementDataObj[i][9]));//approver Id
					
					reimbursementList.add(bean);

				}
				managerBean.setReimbursementIteratorlist(reimbursementList);
			}else{
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1")) {
				managerBean.setMyPage("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void getReimbursementDetailsByClaimId(
			ReimbursementClmaimApplication managerBean, HttpServletRequest request,
			String claimId) {
		
		String reimbursementDetailsQuery = "  SELECT DISTINCT NVL(REIMB_CLAIM_ID,''), NVL(EMP_TOKEN,''), EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, NVL(RANK_NAME,' '), NVL(CENTER_NAME,' ') AS BRANCH,  NVL(CADRE_NAME,''), TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'), NVL(DIV_NAME,''), NVL(CREDIT_NAME,''), "
				 +" NVL(REIMB_CLAIM_PARTICULARS,' '), NVL(REIMB_APPL_CODE,' '),NVL(REIMB_EMP_ID,''),  NVL(REIMB_CLAIM_APPROVER,''),HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE, TRIM(REIMB_APPL_STATUS), NVL(REIMB_APPROVAL_LEVEL,'')" 
				 +" ,REIMB_ADMIN_STATUS FROM HRMS_REIMB_APPLICATION  INNER JOIN HRMS_EMP_OFFC ON (HRMS_REIMB_APPLICATION.REIMB_EMP_ID = HRMS_EMP_OFFC.EMP_ID)  "
				 +" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)  "
				 +" LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)  "
				 +" INNER JOIN HRMS_REIMB_APPL_DTL on(HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
				 +" INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE)"
				 +" INNER JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID) "
				 +" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
				 +" WHERE REIMB_CLAIM_ID = "
				+claimId;
		
		Object reimbursementDetailObj[][] = getSqlModel().getSingleResult(
				reimbursementDetailsQuery);
		
		managerBean.setClaimId(String.valueOf(reimbursementDetailObj[0][0]));//claim Id
		managerBean.setEmployeeToken(String.valueOf(reimbursementDetailObj[0][1]));//Employee Token
		managerBean.setEmployeeName(String.valueOf(reimbursementDetailObj[0][2]));//Employee Name
		managerBean.setEmployeeDesignation(String.valueOf(reimbursementDetailObj[0][3]));//Designation
		managerBean.setEmployeeBranch(String.valueOf(reimbursementDetailObj[0][4]));//Branch
		managerBean.setEmployeeGrade(String.valueOf(reimbursementDetailObj[0][5]));//Grade
		managerBean.setClaimDate(String.valueOf(reimbursementDetailObj[0][6]));//Claim date
		managerBean.setEmployeeDivision(String.valueOf(reimbursementDetailObj[0][7]));//Division
		managerBean.setReimbHead(String.valueOf(reimbursementDetailObj[0][8]));//Head
		managerBean.setClaimParticulars(String.valueOf(reimbursementDetailObj[0][9]));//Particulars
		managerBean.setApplicationId(String.valueOf(reimbursementDetailObj[0][10]));//Application Id
		managerBean.setEmployeeId(String.valueOf(reimbursementDetailObj[0][11]));//Employee Id
		managerBean.setApproverId(String.valueOf(reimbursementDetailObj[0][12]));//Approver Id
		managerBean.setCreditCode(String.valueOf(reimbursementDetailObj[0][13]));//credit code
		managerBean.setStatus(String.valueOf(reimbursementDetailObj[0][14]));//Status
		managerBean.setLevel(String.valueOf(reimbursementDetailObj[0][15]));//level
		
		managerBean.setAdminStatus(String.valueOf(reimbursementDetailObj[0][16]));//Admin Status
		
		if(String.valueOf(reimbursementDetailObj[0][14]).equals("A") || String.valueOf(reimbursementDetailObj[0][14]).equals("R")||String.valueOf(reimbursementDetailObj[0][14]).equals("D")){
			managerBean.setCommentListFlag(false);
			
		}else{
			managerBean.setCommentListFlag(true);
		}
		
		
		ArrayList<Object> previousCommentList = new ArrayList<Object>();
		String previousApproverCommentsQuery = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS APPROVER, TO_CHAR(REIMB_APPROVAL_DATE,'DD-MM-YYYY HH:MM AM'), NVL(REIMB_APPROVER_COMMENTS,'')"
				+ " FROM HRMS_REIMB_APPL_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_REIMB_APPL_PATH.REIMB_APPROVER = HRMS_EMP_OFFC.EMP_ID) "
				+ " WHERE REIMB_CLAIM_ID= "
				+claimId;
		
		/*Previous approver comments block starts */
		Object previousApproverCommentsDetailObj[][] = getSqlModel().getSingleResult(
				previousApproverCommentsQuery);
		if(previousApproverCommentsDetailObj != null && previousApproverCommentsDetailObj.length > 0){
			for (int i = 0; i < previousApproverCommentsDetailObj.length; i++) {
				ReimbManagerApproval bean = new ReimbManagerApproval();
				bean.setPrevApproverNameItt(String.valueOf(previousApproverCommentsDetailObj[i][0]));
				bean.setPrevApproverDateItt(String.valueOf(previousApproverCommentsDetailObj[i][1]));
				bean.setPrevApproverCommentItt(String.valueOf(previousApproverCommentsDetailObj[i][2]));
				
				previousCommentList.add(bean);
			}
			managerBean.setApproverCommentListFlag(true);
			managerBean.setApproverCommentList(previousCommentList);
		}else{
			managerBean.setApproverCommentListFlag(false);
		}
		
		/*Previous approver comments block ends */
		
		/* Expense block starts */
		String expenseDetailsQuery = " SELECT NVL(REIMB_REFERENCE_ID,''), TO_CHAR(REIMB_EXPENSE_DATE,'DD-MM-YYYY'), NVL(REIMB_CLAIM_AMOUNT,''), NVL(REIMB_PROOF,''),NVL(REIMB_BILL_AMOUNT,0), "
			+" REIMB_CREDIT_CODE,CREDIT_NAME "
			+ " FROM HRMS_REIMB_APPL_DTL "
			+" INNER JOIN HRMS_CREDIT_HEAD ON (CREDIT_CODE=REIMB_CREDIT_CODE)"
			+ " WHERE REIMB_CLAIM_ID="
			+claimId
			+ " ORDER BY REIMB_EXPENSE_DATE ";
		
		Object expenseDetailsObj[][] = getSqlModel().getSingleResult(
				expenseDetailsQuery);
		double claimTotal = 0; 
		double billTotal = 0; 
		ArrayList<Object> expenseDetailsList = new ArrayList<Object>();
		if (expenseDetailsObj != null && expenseDetailsObj.length > 0) {
			for (int i = 0; i < expenseDetailsObj.length; i++) {
				ReimbManagerApproval expenseBean = new ReimbManagerApproval();
				String[] splitedReferenceIds = null;
				String[] splitedProofs = null;
				if (!Utility.checkNull(String.valueOf(expenseDetailsObj[i][3])).equals("")) {
					splitedReferenceIds = String.valueOf(expenseDetailsObj[i][0]).split(",");
					splitedProofs = String.valueOf(expenseDetailsObj[i][3]).split(",");

					ArrayList<Object> proofList = new ArrayList<Object>();
					for (int j = 0; j < splitedReferenceIds.length; j++) {
						ReimbManagerApproval proofBean = new ReimbManagerApproval();
						proofBean.setProofReferenceIdItt(String
								.valueOf(splitedReferenceIds[j]));
						proofBean.setProofDocItt(String
								.valueOf(splitedProofs[j]));
						proofList.add(proofBean);
					}
					expenseBean.setListProof(proofList);
				}

				expenseBean.setExpenseDateItt(String
						.valueOf(expenseDetailsObj[i][1]));// expense date
				expenseBean.setExpenseClaimAmountItt(String
						.valueOf(expenseDetailsObj[i][2]));// claim Amount
				expenseBean.setExpenseBillAmountItt(String
						.valueOf(expenseDetailsObj[i][4]));// bill Amount
				expenseBean.setHeadItt(String
						.valueOf(expenseDetailsObj[i][6]));// reimb head
				expenseBean.setHeadCodeItt(String
						.valueOf(expenseDetailsObj[i][5]));// reimb head code
				claimTotal=claimTotal+Double.parseDouble(String.valueOf(expenseDetailsObj[i][2]));
				billTotal=billTotal+Double.parseDouble(String.valueOf(expenseDetailsObj[i][4]));
				expenseDetailsList.add(expenseBean);
			}
			
			managerBean.setClaimAmountTotal(String.valueOf(claimTotal));
			managerBean.setBillAmountTotal(String.valueOf(billTotal));
			managerBean.setExpenseList(expenseDetailsList);
		}
		
		/* Expense block ends */
		
		displayPreApprovedBills(managerBean);
	}

	public void saveApproverComments(ReimbManagerApproval managerBean) {
		
		try {
			String commentInsertQuery = "UPDATE HRMS_REIMB_APPL_PATH SET REIMB_APPROVER_COMMENTS = '"
				+managerBean.getApproverComments()+"'"
				+ " WHERE REIMB_CLAIM_ID ="
				+managerBean.getClaimId();
			
				getSqlModel().singleExecute(commentInsertQuery);
				
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		Object result[][] = null;
		try {
			ReportingModel reporting = new ReportingModel();
			reporting.initiate(context, session);
			result = reporting.generateEmpFlow(empCode, type, order);
			reporting.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public String approveRejectSendBackReimbursement( String userEmpId, HttpServletRequest request,
			String btnStatus, String claimId, String level, String empCode, String approverId, String approverComments, String creditId) {
		
		String reimbStatus = "pending";
		Object claimAmtObj[][]=getSqlModel().getSingleResult("SELECT SUM(NVL(REIMB_CLAIM_AMOUNT,0)),SUM(NVL(REIMB_BILL_AMOUNT,0)) FROM HRMS_REIMB_APPL_DTL WHERE REIMB_CLAIM_ID="+claimId);
		String chkAdminRequiredQuery = "SELECT IS_ADMIN_APPROVAL,REIMB_ADMIN,IS_ACCOUNTANT_APPROVAL,REIMB_ACCOUNTANT FROM HRMS_REIMB_CONFIG WHERE REIMB_CREDIT_HEAD = "
			+creditId;
		
		Object chkAdminRequiredObj[][] = getSqlModel().getSingleResult(
				chkAdminRequiredQuery);
		String accountantReq=String.valueOf(chkAdminRequiredObj[0][2]);
		String accountantID=String.valueOf(chkAdminRequiredObj[0][3]);
		try {
			Object[][] empFlow = null;
			
			if (String.valueOf(btnStatus).equals("A")) {
				empFlow = generateEmpFlow(empCode, "REIMB", Integer
						.parseInt(level) + 1);
			
				if (empFlow != null && empFlow.length != 0) {
					Object[][] updateApprover = new Object[1][4];
					updateApprover[0][0] = empFlow[0][2]; // level
					updateApprover[0][1] = empFlow[0][0]; // approver id
					updateApprover[0][2] = "P"; // status
					updateApprover[0][3] = claimId; // claimId

					String updateQuery = "  UPDATE HRMS_REIMB_APPLICATION  SET REIMB_APPROVAL_LEVEL=? ,REIMB_CLAIM_APPROVER=?, REIMB_APPL_STATUS=? WHERE REIMB_CLAIM_ID=?  ";

					getSqlModel().singleExecute(updateQuery, updateApprover);
					String insertApproverDtlQuery = " INSERT INTO HRMS_REIMB_APPL_PATH(REIMB_PATH_ID, REIMB_CLAIM_ID, REIMB_APPROVER, REIMB_APPROVAL_DATE, REIMB_APPROVER_COMMENTS, REIMB_APPROVAL_STATUS) "
						+ " VALUES((SELECT NVL(MAX(REIMB_PATH_ID),0)+1 FROM HRMS_REIMB_APPL_PATH),?,?,SYSDATE,?,?)";
					
					Object insertApproverDtlQueryObj[][] = new Object[1][4];
					insertApproverDtlQueryObj[0][0] = claimId;
					insertApproverDtlQueryObj[0][1] = approverId;
					insertApproverDtlQueryObj[0][2] = approverComments;
					insertApproverDtlQueryObj[0][3] = btnStatus;

					getSqlModel().singleExecute(insertApproverDtlQuery,
							insertApproverDtlQueryObj);
					/*
					 * Send mail to next approver
					 */
					sendApplicationMailToNextLevel(userEmpId,request, String.valueOf(empFlow[0][0]), approverId, empCode, 
							"MANAGER", String.valueOf(empFlow[0][2]),claimId,String.valueOf(claimAmtObj[0][0]),creditId);
					/*
					 * Send mail to next approver ends
					 */
				}else{
					
					
					Object[][] updateApprover = new Object[1][5];
					updateApprover[0][0] = level; // level
					updateApprover[0][1] = approverId; // approver id
					if(chkAdminRequiredObj != null && chkAdminRequiredObj.length > 0){
						if(String.valueOf(chkAdminRequiredObj[0][0]).equals("Y")){
							updateApprover[0][2] = "A"; // status
							updateApprover[0][3] = "P"; // admin status
							
						}else{
							if(accountantReq.equals("N"))
								updateApprover[0][2] = "C"; // status
							else 
								updateApprover[0][2] = "D"; // status
							updateApprover[0][3] = "A"; // admin status
						}
					}
					updateApprover[0][4] = claimId; // claimId
					
					String updateQuery = "  UPDATE HRMS_REIMB_APPLICATION  SET REIMB_APPROVAL_LEVEL=? ,REIMB_CLAIM_APPROVER=?, REIMB_APPL_STATUS=?,REIMB_ADMIN_STATUS=? WHERE REIMB_CLAIM_ID=?  ";
					getSqlModel().singleExecute(updateQuery, updateApprover);
					String insertApproverDtlQuery = " INSERT INTO HRMS_REIMB_APPL_PATH(REIMB_PATH_ID, REIMB_CLAIM_ID, REIMB_APPROVER, REIMB_APPROVAL_DATE, REIMB_APPROVER_COMMENTS, REIMB_APPROVAL_STATUS) "
						+ " VALUES((SELECT NVL(MAX(REIMB_PATH_ID),0)+1 FROM HRMS_REIMB_APPL_PATH),?,?,SYSDATE,?,?)";
					
					Object insertApproverDtlQueryObj[][] = new Object[1][4];
					insertApproverDtlQueryObj[0][0] = claimId;
					insertApproverDtlQueryObj[0][1] = approverId;
					insertApproverDtlQueryObj[0][2] = approverComments;
					insertApproverDtlQueryObj[0][3] = btnStatus;

					getSqlModel().singleExecute(insertApproverDtlQuery,
							insertApproverDtlQueryObj);
					
					if(String.valueOf(chkAdminRequiredObj[0][0]).equals("Y")){
						/*
						 * Send mail to ADMIN
						 */
						sendApplicationMailToNextLevel(userEmpId,request, String.valueOf(chkAdminRequiredObj[0][1]), approverId, empCode, "ADMIN", level,claimId,String.valueOf(claimAmtObj[0][0]),creditId);
						/*
						 * Send mail to ADMIN ends
						 */
					}else{
						/*
						 * Send mail to EMPLOYEE regarding claim approver
						 */
						sendApplicationMailToEmp(userEmpId,claimId, empCode, approverId,btnStatus);
						/*
						 * Send mail to EMPLOYEE regarding claim approver ends
						 */
						
						/**
						 * Update pre-approved bills
						 */
						String preApprovedDtlQuery= "SELECT REIMB_PRE_APPPROVED_AMT, NVL(REIMB_PRE_APPPROVED_AMT_DTL,'') FROM HRMS_REIMB_BALANCE "
							+" WHERE REIMB_CREDIT_CODE="+creditId+" AND REIMB_EMP_ID="+empCode;
						
						Object [][]preApprovedDtlObj=getSqlModel().getSingleResult(preApprovedDtlQuery) ;
						String preApprovedDtl="";
						double preApprovedAmt=0;
						preApprovedDtl = Utility.checkNull(String.valueOf(preApprovedDtlObj[0][1]));
						try {
							preApprovedAmt = Double
									.parseDouble(Utility.checkNull(String
											.valueOf(preApprovedDtlObj[0][0])));
						} catch (Exception e) {
							// TODO: handle exception
						}
						double diffAmt=0;
						double totalBillAmt=0;
						
						totalBillAmt=Double.parseDouble(String.valueOf(claimAmtObj[0][1]))+
									preApprovedAmt;
						diffAmt=totalBillAmt-
								Double.parseDouble(String.valueOf(claimAmtObj[0][0]))-preApprovedAmt;
						
						if(diffAmt>Double.parseDouble(String.valueOf(claimAmtObj[0][1]))){
							if(diffAmt>preApprovedAmt){
							if(preApprovedDtl.equals("")){
								preApprovedDtl+=claimId+"#"+diffAmt;
							}else{
								preApprovedDtl+=","+claimId+"#"+diffAmt;
							}
							}
						}else if(diffAmt<Double.parseDouble(String.valueOf(claimAmtObj[0][1]))){
							
								preApprovedDtl+=claimId+"#"+diffAmt;
							
						}
						else if(diffAmt==0){
							preApprovedDtl ="";
						}
						
						String updatePreApprovedDtlQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_PRE_APPPROVED_AMT_DTL=? WHERE REIMB_CREDIT_CODE=? AND REIMB_EMP_ID=?";
						Object updatePreApprovedDtlObj[][]=new Object [1][3];
						updatePreApprovedDtlObj[0][0]= preApprovedDtl;
						updatePreApprovedDtlObj[0][1]= creditId;
						updatePreApprovedDtlObj[0][2]= empCode;
						
						getSqlModel().singleExecute(updatePreApprovedDtlQuery,updatePreApprovedDtlObj);
						/**
						 * End Update pre-approved bills
						 */
						
						if(accountantReq.equals("N")){				// if accountant is not required
							/**
							 * Update onhold amount from HRMS_REIMB_BALANCE
							 */
							String updateOnholdQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_ONHOLD_AMT=REIMB_ONHOLD_AMT-? WHERE REIMB_CREDIT_CODE=? AND REIMB_EMP_ID=?";
							Object updateOnholdObj[][]=new Object[1][3];
							updateOnholdObj[0][0]= String.valueOf(claimAmtObj[0][0]);						
							updateOnholdObj[0][1]= creditId;
							updateOnholdObj[0][2]= empCode;
								getSqlModel().singleExecute(updateOnholdQuery,updateOnholdObj);
							}
							/**
							 * end Update onhold amount
							 */
					}
				}
				reimbStatus = "approved";
				////sendApplicationMailToEmp(userEmpId,claimId, empCode, approverId,btnStatus);
			}else{
			if (String.valueOf(btnStatus).equals("B")) {
				String updateStatusQuery = " UPDATE HRMS_REIMB_APPLICATION SET REIMB_APPL_STATUS='B',REIMB_CLAIM_APPROVER="
					+ approverId
					+ ",REIMB_APPROVAL_LEVEL=1 WHERE REIMB_CLAIM_ID= "
					+ claimId ;
			getSqlModel().singleExecute(updateStatusQuery);
			
			reimbStatus = "sendback";
			
			}
			if (String.valueOf(btnStatus).equals("R")) {
				
				String updateBalanceQuery = " UPDATE HRMS_REIMB_BALANCE SET REIMB_ONHOLD_AMT=REIMB_ONHOLD_AMT-?,REIMB_BALANCE_AMT=REIMB_BALANCE_AMT+? WHERE REIMB_CREDIT_CODE=? AND REIMB_EMP_ID=?";
				
				Object updateBalanceObj[][]=new Object [1][4];
				updateBalanceObj[0][0]= String.valueOf(claimAmtObj[0][0]);
				updateBalanceObj[0][1]= String.valueOf(claimAmtObj[0][0]);
				updateBalanceObj[0][2]= creditId;
				updateBalanceObj[0][3]= empCode;
				
				getSqlModel().singleExecute(updateBalanceQuery, updateBalanceObj);
				
				String updateStatusQuery = " UPDATE HRMS_REIMB_APPLICATION SET REIMB_APPL_STATUS='R',REIMB_CLAIM_APPROVER="
					+ approverId
					+ ",REIMB_APPROVAL_LEVEL=1 WHERE REIMB_CLAIM_ID= "
					+ claimId ;
			getSqlModel().singleExecute(updateStatusQuery);
			reimbStatus = "rejected";
			
			}
			getSqlModel().singleExecute("UPDATE HRMS_REIMB_APPL_DTL SET REIMB_APPROVED_AMOUNT =0 WHERE REIMB_CLAIM_ID="+claimId);
			String insertApproverDtlQuery = " INSERT INTO HRMS_REIMB_APPL_PATH(REIMB_PATH_ID, REIMB_CLAIM_ID, REIMB_APPROVER, REIMB_APPROVAL_DATE, REIMB_APPROVER_COMMENTS, REIMB_APPROVAL_STATUS) "
				+ " VALUES((SELECT NVL(MAX(REIMB_PATH_ID),0)+1 FROM HRMS_REIMB_APPL_PATH),?,?,SYSDATE,?,?)";
			
			Object insertApproverDtlQueryObj[][] = new Object[1][4];
			insertApproverDtlQueryObj[0][0] = claimId;
			insertApproverDtlQueryObj[0][1] = approverId;
			insertApproverDtlQueryObj[0][2] = approverComments;
			insertApproverDtlQueryObj[0][3] = btnStatus;

			getSqlModel().singleExecute(insertApproverDtlQuery,
					insertApproverDtlQueryObj);
			/*
			 * Send mail to EMPLOYEE regarding claim send back
			 */
			sendApplicationMailToEmp(userEmpId,claimId, empCode, approverId,btnStatus);
			/*
			 * Send mail to EMPLOYEE regarding claim send back
			 */
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reimbStatus;
	}
	public void sendApplicationMailToEmp(String userEmpId, String applicationCode, String applicantID, String approverId, String btnStatus) {

		logger
				.info("######### send application mail aplicationCode #############"
						+ applicationCode);
		try {
			ReimbManagerApprovalModel model= new ReimbManagerApprovalModel();
			model.initiate(context, session);
			
			String keepInformedId = " SELECT DISTINCT  REIMB_APPROVER FROM HRMS_REIMB_APPL_PATH WHERE REIMB_CLAIM_ID ="
				+ applicationCode
				+ " AND REIMB_APPROVER NOT IN ("
				+ userEmpId + ")";
			
			Object keepInformedObj[][] = model.getSqlModel().getSingleResult(
					keepInformedId);
			String module = "Claim Reimbursement";
			/**
			 * Remove process manager entry from mypage.
			 */
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String prmodule = "Claim Reimbursement";
			processAlerts.removeProcessAlert(String.valueOf(applicationCode), prmodule);

			/* SEND MAIL EMPLOYEE  */

			EmailTemplateBody template = new EmailTemplateBody();
			// Initiate template
			template.initiate(context, session);

			// Set respective template name
			template.setEmailTemplate("REIMBURSEMENT_MAIL TO EMPLOYEE REGARDING CLAIM APPROVER");

			// call compulsory for set the queries of template
			template.getTemplateQueries();

			try {
				// get the query as per number
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				templateQuery1.setParameter(1, approverId);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				// And so on
				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2);// To EMAIL
				templateQuery2.setParameter(1, applicantID);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, applicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, applicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, applicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, applicationCode);
				templateQuery7.setParameter(2, applicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				// configure the actual contents of the template
				template.configMailAlert();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			String link = "";
			String linkParam = "";
			
			try {
				link = "/Reimbursement/ReimbursementClmAppl_getApplicationDetails.action";
				linkParam = "hiddenApplicationCode=" + applicationCode;
				
				String ccId = "";

				if (keepInformedObj != null && keepInformedObj.length > 0) {

					for (int i = 0; i < keepInformedObj.length; i++) {
						ccId += String.valueOf(keepInformedObj[i][0]) + ",";
					}
					ccId = ccId.substring(0, ccId.length() - 1);
				}
				
				
				if(btnStatus.equals("B")){
					
					template.sendProcessManagerAlert(applicantID, module, "A",
							applicationCode, "", linkParam, link, "Sendback",
							applicantID, "", "", applicantID, userEmpId);
					
					template.sendProcessManagerAlert(approverId, module, "I",
							applicationCode, "", linkParam, link, "Pending",
							applicantID, "", ccId, "", userEmpId);
					
					
				} else if(btnStatus.equals("R")){
					template.sendProcessManagerAlert(userEmpId, module, "I",
							applicationCode, "", linkParam, link, "Reject",
							applicantID, "", ccId, applicantID, userEmpId);
				}else {
					template.sendProcessManagerAlert(userEmpId, module, "I",
							applicationCode, "", linkParam, link, "Pending",
							applicantID, "", ccId, applicantID, userEmpId);
					
					
				}

				
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			///template.sendApplicationMailToKeepInfo(applicantID);
			
			// call for sending the email
			template.sendApplicationMail();

			// clear the template
			template.clearParameters();

			// terminate template
			template.terminate();



		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendApplicationMailToNextLevel( String userEmpId, HttpServletRequest request,String toID, String fromID,String applicantID,
			String mailType,String level,String applicationCode,String claimAmountTotal,String creditCode) {

		
		try {
			
			
			ReimbManagerApprovalModel model= new ReimbManagerApprovalModel();
			model.initiate(context, session);
			
			String keepInformedId = " SELECT DISTINCT  REIMB_APPROVER FROM HRMS_REIMB_APPL_PATH WHERE REIMB_CLAIM_ID ="
				+ applicationCode
				+ " AND REIMB_APPROVER NOT IN ("
				+ userEmpId + ")";
			
			Object keepInformedObj[][] = model.getSqlModel().getSingleResult(
					keepInformedId);
			
			
			String module = "Claim Reimbursement";
			/**
			 * Remove process manager entry from mypage.
			 */
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String prmodule = "Claim Reimbursement";
			processAlerts.removeProcessAlert(String.valueOf(applicationCode), prmodule);
			
			String link = "";
			String linkParam = "";
			String appllink = "";
			String appllinkParam = "";
			
			logger
			.info("######### send application mail aplicationCode #############"
					+ applicationCode);
			/* SEND MAIL EMPLOYEE  */

			EmailTemplateBody template = new EmailTemplateBody();
			// Initiate template
			template.initiate(context, session);

			// Set respective template name
			template.setEmailTemplate("REIMBURSEMENT_MAIL TO SECOND LEVEL MANAGER/ADMIN REGARDING CLAIM APPLICATION");

			// call compulsory for set the queries of template
			template.getTemplateQueries();

			try {
				// get the query as per number
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				templateQuery1.setParameter(1, fromID);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				// And so on
				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2);// To EMAIL
				templateQuery2.setParameter(1, toID);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, applicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, applicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, applicationCode);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, fromID);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);
				templateQuery8.setParameter(1, toID);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				template.configMailAlert();
			} catch (Exception e) {
				// TODO: handle exception
			}
			String[] link_param = new String[3];
			String[] link_label = new String[3];
			if(mailType.equals("MANAGER")){
			// Add approval link -pass parameters to the link
			
			String applicationType = "ReimbursementMgr";
			link_param[0] = applicationType + "#" + "A" + "#" + applicationCode + "#"
					+ level + "#" + applicantID + "#" + toID + "#"
					+ "..." + "#" + creditCode +"#"+claimAmountTotal+"#"+ userEmpId;
			link_param[1] = applicationType + "#" + "R" + "#" + applicationCode + "#"
					+ level + "#" + applicantID + "#" + toID + "#"
					+ "..." + "#" + creditCode +"#"+ claimAmountTotal+"#"+ userEmpId;
			link_param[2] = applicationType + "#" + "B" + "#" + applicationCode + "#"
					+ level + "#" + applicantID + "#" + toID + "#"
					+ "..." + "#" + creditCode +"#"+ claimAmountTotal+"#"+ userEmpId;
			link_label[0] = "Approve";
			link_label[1] = "Reject";
			link_label[2] = "Send Back";
			template.addOnlineLink(request, link_param, link_label);
			}
			else{
				/*String[] link_parameter = new String[1];
				String[] link_labels = new String[1];
				String applicationType = "ReimbursementAdmin";
				link_parameter[0] = applicationType + "#" + toID
						+ "#applicationDtls#";

				String link = "/Reimbursement/ReimbAdminApproval_retriveForApproval.action?claimId="
						+ applicationCode;
				// link= PPEncrypter.encrypt(link);
				System.out.println("applicationDtls  ..." + link);
				link_parameter[0] += link;
				link_labels[0] = "Admin Approval";
				template.addOnlineLink(request, link_parameter,
						link_labels);*/
			}
			
			// configure the actual contents of the template
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			ResourceBundle boundle = ResourceBundle.getBundle("globalMessages");
			String path = boundle.getString("data_path") + "/upload/"
					+ poolName + "/Reimbursement/";

			int counter = 0;
			String[] attachPath = null;
			String attachmentQuery="SELECT REIMB_PROOF FROM HRMS_REIMB_APPL_DTL WHERE REIMB_CLAIM_ID="+applicationCode;
			Object [][]attachmentObj=getSqlModel().getSingleResult(attachmentQuery);
			//
			Vector tempVect = new Vector();
			if (attachmentObj != null && attachmentObj.length > 0) {
				logger.info("fileName.length : " + attachmentObj.length);
				for (int i = 0; i < attachmentObj.length; i++) {
					if (String.valueOf(attachmentObj[i][0]) != null && !String.valueOf(attachmentObj[i][0]).equals("null")
							&& !String.valueOf(attachmentObj[i][0]).equals("")) {
						String[] splitFile = String.valueOf(attachmentObj[i][0]).split(",");
						
						for (int j = 0; j < splitFile.length; j++) {
							//attachPath[counter] = path + splitFile[j];
							tempVect.add(path + splitFile[j]);
						}

					}
				}
				attachPath = new String[tempVect.size()];

				for (int i = 0; i < attachPath.length; i++) {
					attachPath[i] = String.valueOf(tempVect.get(i));
				}
			}
			// call for sending the email
		//	template.sendApplicationMail();
			
			String ccId = "";

			if (keepInformedObj != null && keepInformedObj.length > 0) {

				for (int i = 0; i < keepInformedObj.length; i++) {
					ccId += String.valueOf(keepInformedObj[i][0]) + ",";
				}
				ccId = ccId.substring(0, ccId.length() - 1);
			}
			
			
			if(mailType.equals("MANAGER")){
				
				link = "/Reimbursement/ReimbursementClmAppl_viewReimbursement.action";
				linkParam = "claimId=" + applicationCode;
				
				template.sendProcessManagerAlert(toID, module,
						"A", applicationCode, level, linkParam, link,
						"Pending", applicantID, "", "", "", userEmpId);
			
			try {
				appllink = "/Reimbursement/ReimbursementClmAppl_getApplicationDetails.action";
				appllinkParam = "hiddenApplicationCode=" + applicationCode;
				
				template.sendProcessManagerAlert(userEmpId, module, "I",
						applicationCode, level, appllinkParam, appllink, "Pending",
						applicantID, "", ccId, applicantID, userEmpId);
				
			} catch (Exception e) {
				logger.error(e);
			}
			
			}else{
				
				link = "/Reimbursement/ReimbursementClmAppl_retriveForApproval.action";
				linkParam = "claimId=" + applicationCode;
								
				template.sendProcessManagerAlert(toID, module,
						"A", applicationCode, level, linkParam, link,
						"Pending", applicantID, "", "", "", userEmpId);
				
				try {
					
					appllink = "/Reimbursement/ReimbursementClmAppl_getApplicationDetails.action";
					appllinkParam = "hiddenApplicationCode=" + applicationCode;
					
					template.sendProcessManagerAlert(fromID, module, "I",
							applicationCode, level, appllinkParam, appllink, "Pending",
							applicantID, "", ccId, applicantID, fromID);
					
				} catch (Exception e) {
					logger.error(e);
				}
				
			}
			if (keepInformedObj != null && keepInformedObj.length > 0) {
				// keepInfo = String.valueOf(keepInformedObj[0][0]);
				template.sendApplicationMailToKeepInfo(ccId);
			}

			//template.sendApplicationMailToKeepInfo(applicantID);
			template.sendApplMailWithAttachment(attachPath);
			// clear the template
			template.clearParameters();

			// terminate template
			template.terminate();



		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public String onlineApproveReject(HttpServletRequest request,String btnStatus,String claimId,String level,String empCode,
			String approverId,String approverComments,String creditId,String claimAmountTotal,String userEmpId) {
		String result = "";
		String appStatus = ""; 
		String query = " SELECT REIMB_CLAIM_APPROVER,REIMB_APPL_STATUS FROM HRMS_REIMB_APPLICATION WHERE REIMB_EMP_ID="
				+ empCode + " AND REIMB_CLAIM_ID=" + claimId;

		Object approverIdObj[][] = getSqlModel().getSingleResult(query);

		if (approverIdObj != null && approverIdObj.length > 0) {
			if (String.valueOf(approverIdObj[0][0]).equals(approverId)
					&& String.valueOf(approverIdObj[0][1]).equals("P")) {
				appStatus = approveRejectSendBackReimbursement(userEmpId,request, btnStatus, claimId, level, empCode, approverId, 
						approverComments, creditId);

				logger.info("appStatus....." + appStatus);
				if (appStatus.equals("rejected")) {
					result="Application rejected.";
				} else if (appStatus.equals("sendback")) {
					result="Application sent back.";
				} else if (appStatus.equals("approved")){
					result="Application approved.";
				}
				else
					result = "Error Occured.";
			} else {
				result = "Reimbursement Application has already been processed.";
			}
		}

		return result;

	}
	public void displayPreApprovedBills(ReimbursementClmaimApplication bean){
		String billDetQuery="SELECT REIMB_PRE_APPPROVED_AMT_DTL,NVL(REIMB_PRE_APPPROVED_AMT,0),NVL(REIMB_BALANCE_AMT,0) FROM HRMS_REIMB_BALANCE WHERE REIMB_CREDIT_CODE="+bean.getCreditCode()+
		" AND REIMB_EMP_ID="+bean.getEmployeeId();
		
		Object [][]billDetObj=getSqlModel().getSingleResult(billDetQuery);
		ArrayList tableList=new ArrayList();
		if(billDetObj !=null || billDetObj.length>0){
			bean.setBillsCarriedForward(Utility.checkNull(String.valueOf(billDetObj[0][1])));
			bean.setReimburseBalance(Utility.checkNull(String.valueOf(billDetObj[0][2])));
			String billsDtlString=Utility.checkNull(String.valueOf(billDetObj[0][0]));
			if(billsDtlString.equals("")){
				bean.setPreApprovedListFlag("false");
			}else{
				String prevAppl[]=billsDtlString.split(",");
				for (int i = 0; i < prevAppl.length; i++) {
					String amtSplit[]=prevAppl[i].split("#");
					String applId=amtSplit[0];
					String prevAmt=amtSplit[1];
					String proofNameQuery="SELECT NVL(REIMB_REFERENCE_ID,''), NVL(REIMB_PROOF,'') FROM HRMS_REIMB_APPL_DTL WHERE REIMB_PROOF IS NOT NULL AND REIMB_CLAIM_ID="+applId;
					Object [][]proofObj=getSqlModel().getSingleResult(proofNameQuery);
					for (int j = 0; j < proofObj.length; j++) {
						ReimbursementClmaimApplication amountBean=new ReimbursementClmaimApplication();
						String []proofs=String.valueOf(proofObj[j][1]).split(",");
						String []refNo=String.valueOf(proofObj[j][0]).split(",");
						amountBean.setPrevProofAmt(prevAmt);
						ArrayList proofList=new ArrayList();
						for (int k = 0; k < refNo.length; k++) {
							ReimbursementClmaimApplication proofBean=new ReimbursementClmaimApplication();
							proofBean.setPrevProofFileName(proofs[k]);
							proofBean.setPrevProofRefNo(refNo[k]);
							proofList.add(proofBean);
						}
						amountBean.setPreApprovedProofList(proofList);
						tableList.add(amountBean);
					}
					
				}
				bean.setPreApprovedList(tableList);
				bean.setPreApprovedListFlag("true");
			}
			
		}else{
			bean.setPreApprovedListFlag("false");
		}
	}
}
