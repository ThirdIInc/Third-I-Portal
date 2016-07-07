/**
 * @author Mangesh Jadhav
 */
package org.paradyne.model.reimbursement;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.reimbursement.ReimbAdminApproval;
import org.paradyne.bean.reimbursement.ReimbursementClmaimApplication;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author AA0554
 *
 */
public class ReimbAdminApprovalModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ReimbAdminApprovalModel.class);
	public void getAdminPendingList(ReimbAdminApproval reimbAdminAppr,HttpServletRequest request) {
		String pendingQuery="SELECT DISTINCT REIMB_APPL_CODE,EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY')," 
					+" CREDIT_NAME,REIMB_TOTAL_AMOUNT,HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID FROM HRMS_REIMB_APPLICATION "
					+" INNER JOIN HRMS_REIMB_APPL_DTL ON(HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
					+" INNER JOIN HRMS_EMP_OFFC ON (EMP_ID=REIMB_EMP_ID)"
					+" INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE)"
					+" INNER JOIN HRMS_REIMB_CONFIG ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_REIMB_CONFIG.REIMB_CREDIT_HEAD)"
					+" WHERE REIMB_APPL_STATUS='A' AND REIMB_ADMIN_STATUS='P' AND REIMB_ADMIN="+reimbAdminAppr.getUserEmpId();
		String filterQuery=getFilterString(reimbAdminAppr);
		pendingQuery+=filterQuery;
		Object [][]pendingObj=getSqlModel().getSingleResult(pendingQuery);
		if(pendingObj!=null && pendingObj.length>0){
		String[] pageIndex = Utility.doPaging(reimbAdminAppr.getMyPage(),pendingObj.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			reimbAdminAppr.setMyPage("1");
			ArrayList tableList=new ArrayList();
			for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {
				ReimbAdminApproval beanList=new ReimbAdminApproval();
				beanList.setApplRefNoList(Utility.checkNull(String.valueOf(pendingObj[i][0])));
				beanList.setEmpNameList(Utility.checkNull(String.valueOf(pendingObj[i][1])));
				beanList.setClaimDateList(Utility.checkNull(String.valueOf(pendingObj[i][2])));
				beanList.setReimbHeadList(Utility.checkNull(String.valueOf(pendingObj[i][3])));
				beanList.setClaimAmt(Utility.checkNull(String.valueOf(pendingObj[i][4])));
				beanList.setApplIdList(Utility.checkNull(String.valueOf(pendingObj[i][5])));
				
				tableList.add(beanList);
			}
			reimbAdminAppr.setPendingList(tableList);
			reimbAdminAppr.setPendingLength("true");
			reimbAdminAppr.setListType("pending");
		}
	}

	public void getProcessedList(ReimbAdminApproval reimbAdminAppr,HttpServletRequest request) {

		String pendingQuery="SELECT DISTINCT REIMB_APPL_CODE,EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'),"
						+" CREDIT_NAME,REIMB_TOTAL_AMOUNT,HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID," 
						+" CASE WHEN REIMB_APPL_STATUS='D' THEN 'Pending For Disbursement' "  
						 +" WHEN REIMB_APPL_STATUS='C' THEN 'Closed' " 
						 +" WHEN REIMB_ADMIN_STATUS='R' THEN 'Rejected by Admin' "
						 +" WHEN REIMB_ADMIN_STATUS='B' THEN 'Sent back by Admin' ELSE '' END FROM HRMS_REIMB_APPLICATION" 
						+" INNER JOIN HRMS_EMP_OFFC ON (EMP_ID=REIMB_EMP_ID)"
						+" INNER JOIN HRMS_REIMB_APPL_DTL ON(HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
						+" INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE)"
						+" WHERE (REIMB_APPL_STATUS IN('D','C') OR REIMB_ADMIN_STATUS IN('A','R','B'))  AND REIMB_ADMIN_ID="+reimbAdminAppr.getUserEmpId();
		
		/*SELECT REIMB_APPL_CODE,EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'),
		CREDIT_NAME,REIMB_TOTAL_AMOUNT,REIMB_CLAIM_ID,decode(REIMB_APPL_STATUS,'D','Pending for disbursement','C','Closed','B','Sent Back') FROM HRMS_REIMB_APPLICATION 
		INNER JOIN HRMS_EMP_OFFC ON (EMP_ID=REIMB_EMP_ID)
		INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=REIMB_CREDIT_CODE)
		WHERE REIMB_APPL_STATUS in('D','C') AND REIMB_ADMIN_ID=26*/
		String filterQuery=getFilterString(reimbAdminAppr);
		pendingQuery+=filterQuery;
		Object [][]pendingObj=getSqlModel().getSingleResult(pendingQuery);
		if(pendingObj!=null && pendingObj.length>0){
		String[] pageIndex = Utility.doPaging(reimbAdminAppr.getMyPageProcessed(),pendingObj.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		request.setAttribute("totalPageProcessed", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("PageNoProcessed", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			reimbAdminAppr.setMyPageProcessed("1");
			ArrayList tableList=new ArrayList();
			for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {
				ReimbAdminApproval beanList=new ReimbAdminApproval();
				beanList.setApplRefNoList(Utility.checkNull(String.valueOf(pendingObj[i][0])));
				beanList.setEmpNameList(Utility.checkNull(String.valueOf(pendingObj[i][1])));
				beanList.setClaimDateList(Utility.checkNull(String.valueOf(pendingObj[i][2])));
				beanList.setReimbHeadList(Utility.checkNull(String.valueOf(pendingObj[i][3])));
				beanList.setClaimAmt(Utility.checkNull(String.valueOf(pendingObj[i][4])));
				beanList.setApplIdList(Utility.checkNull(String.valueOf(pendingObj[i][5])));
				beanList.setApplStatus(Utility.checkNull(String.valueOf(pendingObj[i][6])));
				
				tableList.add(beanList);
			}
			reimbAdminAppr.setProcessedList(tableList);
			reimbAdminAppr.setProcessedLength("true");
		}
		
		reimbAdminAppr.setListType("processed");
	
		
	}

	public void getClaimDetails(ReimbursementClmaimApplication bean,
			HttpServletRequest request) {
		logger.info("cliam id=="+bean.getApplId());
		String empDetailsQuery="SELECT DISTINCT REIMB_APPL_CODE,EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,RANK_NAME,CENTER_NAME,CADRE_NAME,DIV_NAME,"
						+" TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'),REIMB_CLAIM_PARTICULARS,"
						+" CREDIT_NAME,REIMB_APPL_STATUS,REIMB_EMP_ID,HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE ,REIMB_CLAIM_APPROVER FROM HRMS_REIMB_APPLICATION "
						+" INNER JOIN HRMS_EMP_OFFC ON (EMP_ID=REIMB_EMP_ID)"
						+" INNER JOIN HRMS_REIMB_APPL_DTL ON(HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
						+" INNER JOIN HRMS_DIVISION ON(EMP_DIV=DIV_ID)"
						+" INNER JOIN HRMS_CENTER ON(EMP_CENTER=CENTER_ID)"
						+" INNER JOIN HRMS_RANK ON(EMP_RANK=RANK_ID)"
						+" LEFT JOIN HRMS_CADRE ON(CADRE_ID=EMP_CADRE)"
						+" INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE)"
						+" WHERE REIMB_CLAIM_ID="+bean.getApplId();
		Object [][]empDetailsObj=getSqlModel().getSingleResult(empDetailsQuery);
		
		bean.setApplRefNo(Utility.checkNull(String.valueOf(empDetailsObj[0][0])));
		bean.setEmpName(Utility.checkNull(String.valueOf(empDetailsObj[0][1])));
		bean.setDesignation(Utility.checkNull(String.valueOf(empDetailsObj[0][2])));
		bean.setBranch(Utility.checkNull(String.valueOf(empDetailsObj[0][3])));
		bean.setGrade(Utility.checkNull(String.valueOf(empDetailsObj[0][4])));
		bean.setDivision(Utility.checkNull(String.valueOf(empDetailsObj[0][5])));
		bean.setClaimDate(Utility.checkNull(String.valueOf(empDetailsObj[0][6])));
		bean.setClaimParticular(Utility.checkNull(String.valueOf(empDetailsObj[0][7])));
		bean.setReimbHeadList(Utility.checkNull(String.valueOf(empDetailsObj[0][8])));
		bean.setApplStatus(Utility.checkNull(String.valueOf(empDetailsObj[0][9])));
		bean.setEmpId(Utility.checkNull(String.valueOf(empDetailsObj[0][10])));
		bean.setReimbHead(Utility.checkNull(String.valueOf(empDetailsObj[0][11])));
		
		bean.setApproverId(Utility.checkNull(String.valueOf(empDetailsObj[0][12])));
		
		/*
		 * Display expense details
		 */
		String expenseQuery="SELECT TO_CHAR(REIMB_EXPENSE_DATE,'DD-MM-YYYY'), NVL(REIMB_CLAIM_AMOUNT,0),NVL(REIMB_APPROVED_AMOUNT,REIMB_CLAIM_AMOUNT), REIMB_PROOF,REIMB_REFERENCE_ID, "
			+" NVL(REIMB_BILL_AMOUNT,0), NVL(NVL(REIMB_APPROVED_BILL_AMOUNT,REIMB_BILL_AMOUNT),0),REIMB_CREDIT_CODE,CREDIT_NAME FROM HRMS_REIMB_APPL_DTL"
			+" INNER JOIN HRMS_CREDIT_HEAD ON (CREDIT_CODE=REIMB_CREDIT_CODE)"
			+" WHERE REIMB_CLAIM_ID="+bean.getApplId();
		Object [][]expenseObj=getSqlModel().getSingleResult(expenseQuery);
		double totalClaimAmt=0,totalApprovedBillAmt=0,totalBillAmt=0;
		double totalApprovedAmt=0;
		if(expenseObj!=null && expenseObj.length>0){
			ArrayList expenseList=new ArrayList();
			for (int i = 0; i < expenseObj.length; i++) {
				ReimbAdminApproval expenseBean=new ReimbAdminApproval();
				expenseBean.setReimbHeadList(Utility.checkNull(String.valueOf(expenseObj[0][8])));
				expenseBean.setReimbHeadCodeList(Utility.checkNull(String.valueOf(expenseObj[0][7])));
				expenseBean.setExpenseDate(Utility.checkNull(String.valueOf(expenseObj[i][0])));
				expenseBean.setClaimAmtHead(Utility.checkNull(String.valueOf(expenseObj[i][1])));
				expenseBean.setApprovedAmt(Utility.checkNull(String.valueOf(expenseObj[i][2])));
				expenseBean.setBillAmt(Utility.checkNull(String.valueOf(expenseObj[i][5])));
				expenseBean.setApprovedBillAmt(Utility.checkNull(String.valueOf(expenseObj[i][6])));
				expenseBean.setProofHidden(Utility.checkNull(String.valueOf(expenseObj[i][3])));		
				expenseBean.setProofRefNoHidden(Utility.checkNull(String.valueOf(expenseObj[i][4])));
				totalApprovedAmt=totalApprovedAmt+Double.parseDouble(String.valueOf(expenseObj[i][2]));
				totalClaimAmt=totalClaimAmt+Double.parseDouble(String.valueOf(expenseObj[i][1]));
				totalApprovedBillAmt=totalApprovedBillAmt+Double.parseDouble(String.valueOf(expenseObj[i][6]));
				totalBillAmt=totalBillAmt+Double.parseDouble(String.valueOf(expenseObj[i][5]));
				if(!Utility.checkNull(String.valueOf(expenseObj[i][3])).equals("")){
					String []proofs=String.valueOf(expenseObj[i][3]).split(",");
					String []refNo=String.valueOf(expenseObj[i][4]).split(",");
					ArrayList proofList=new ArrayList();
					for (int j = 0; j < refNo.length; j++) {
						ReimbAdminApproval proofBean=new ReimbAdminApproval();
						proofBean.setProof(proofs[j]);
						proofBean.setProofRefNo(refNo[j]);
						proofList.add(proofBean);
					}
					expenseBean.setProofList(proofList);
				}
				expenseList.add(expenseBean);
			}
			bean.setExpenseList(expenseList);
			bean.setTotalApprovedAmt(String.valueOf(totalApprovedAmt));
			bean.setTotalClaimAmt(String.valueOf(totalClaimAmt));
			bean.setTotalApprovedAmt(String.valueOf(totalApprovedAmt));
			bean.setTotalClaimAmt(String.valueOf(totalClaimAmt));
			bean.setTotalApprovedBillAmt(String.valueOf(totalApprovedBillAmt));
			bean.setTotalBillAmt(String.valueOf(totalBillAmt));
		}
		displayPreApprovedBills(bean);
	}

	public boolean approveClaimAppl(ReimbursementClmaimApplication reimbAdminAppr,HttpServletRequest request) {
		String chkAccountantRequiredQuery = "SELECT IS_ACCOUNTANT_APPROVAL FROM HRMS_REIMB_CONFIG WHERE REIMB_CREDIT_HEAD = "
			+reimbAdminAppr.getReimbHead();
		Object [][]accountantReqObj=getSqlModel().getSingleResult(chkAccountantRequiredQuery);
		
		boolean result=false;
		String applStatus="D";
		if(accountantReqObj !=null && accountantReqObj.length>0){
			if(String.valueOf(accountantReqObj[0][0]).equals("N")){
				applStatus="C";
				
			}
		}
		result = getSqlModel().singleExecute("UPDATE HRMS_REIMB_APPLICATION SET REIMB_ADMIN_STATUS ='A', REIMB_APPL_STATUS='D', REIMB_ADMIN_ID="+reimbAdminAppr.getUserEmpId()+" WHERE REIMB_CLAIM_ID="+reimbAdminAppr.getApplId());
		
		if(result){
			Object [][]pathObj=new Object[1][3];
			String pathQuery="INSERT INTO HRMS_REIMB_APPL_PATH ( REIMB_PATH_ID, REIMB_CLAIM_ID, REIMB_APPROVER,REIMB_APPROVAL_DATE, REIMB_APPROVER_COMMENTS, REIMB_APPROVAL_STATUS ) VALUES ( "
						+" (SELECT NVL(MAX(REIMB_PATH_ID),0)+1 FROM HRMS_REIMB_APPL_PATH) , ?, ?, SYSDATE, ?, 'A') ";
			pathObj[0][0]=reimbAdminAppr.getApplId();
			pathObj[0][1]=reimbAdminAppr.getUserEmpId();
			pathObj[0][2]=reimbAdminAppr.getApproverComments();
			result = getSqlModel().singleExecute(pathQuery,pathObj);
		}
		if(result){
			/**
			 * update approved amount from HRMS_REIMB_APPL_DTL
			 */
			String deleteQuery="DELETE FROM HRMS_REIMB_APPL_DTL WHERE REIMB_CLAIM_ID=?";
			String insertQuery="INSERT INTO HRMS_REIMB_APPL_DTL ( REIMB_CLAIM_ID, REIMB_REFERENCE_ID, REIMB_EXPENSE_DATE,"
						+" REIMB_CLAIM_AMOUNT, REIMB_PROOF, REIMB_APPROVED_AMOUNT,REIMB_BILL_AMOUNT, REIMB_APPROVED_BILL_AMOUNT,REIMB_CREDIT_CODE ) VALUES ( ?, ?,  TO_DATE( ?, 'DD-MM-YYYY'), ?, ?, ?, ?, ?, ?)";
			
			String []proofRefNo=request.getParameterValues("proofRefNoHidden");
			String []proof=request.getParameterValues("proofHidden");
			String []expenseDate=request.getParameterValues("expenseDate");
			String []claimAmt=request.getParameterValues("claimAmtHead");
			String []approvedAmt=request.getParameterValues("approvedAmt");
			String []billAmt=request.getParameterValues("billAmt");
			String []approvedBillAmt=request.getParameterValues("approvedBillAmt");
			String []creditHead=request.getParameterValues("reimbHeadCodeList");
			String []sqlNumberArr={deleteQuery,insertQuery};
			Object [][]deleteObj=new Object[1][1];
			Object [][]insertObj=new Object[claimAmt.length][9];
			deleteObj[0][0]=reimbAdminAppr.getApplId();
			for (int i = 0; i < insertObj.length; i++) {
				insertObj[i][0]=reimbAdminAppr.getApplId();
				insertObj[i][1]=proofRefNo[i];
				insertObj[i][2]=expenseDate[i];
				insertObj[i][3]=claimAmt[i];
				insertObj[i][4]=proof[i];
				insertObj[i][5]=approvedAmt[i];
				insertObj[i][6]=billAmt[i];
				insertObj[i][7]=approvedBillAmt[i];
				insertObj[i][8]=creditHead[i];
			}
			Vector dataVector= new Vector();
			dataVector.add(deleteObj);
			dataVector.add(insertObj);
			result = getSqlModel().multiExecute(sqlNumberArr, dataVector);
			
			if(result){
				/**
				 * update balance amount from HRMS_REIMB_BALANCE
				 */
				String preApprovedDtlQuery= "SELECT REIMB_PRE_APPPROVED_AMT, NVL(REIMB_PRE_APPPROVED_AMT_DTL,'') FROM HRMS_REIMB_BALANCE "
					+" WHERE REIMB_CREDIT_CODE="+reimbAdminAppr.getReimbHead()+" AND REIMB_EMP_ID="+reimbAdminAppr.getEmpId();
				
				Object [][]preApprovedDtlObj=getSqlModel().getSingleResult(preApprovedDtlQuery) ;
				
				String updateBalanceQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_ONHOLD_AMT=REIMB_ONHOLD_AMT-?+?,REIMB_BALANCE_AMT=REIMB_BALANCE_AMT+?-?,REIMB_PRE_APPPROVED_AMT=REIMB_PRE_APPPROVED_AMT+?-? WHERE REIMB_CREDIT_CODE=? AND REIMB_EMP_ID=?";
				Object updateBalanceObj[][]=new Object [1][8];
				updateBalanceObj[0][0]= reimbAdminAppr.getTotalClaimAmt();
				updateBalanceObj[0][1]= reimbAdminAppr.getTotalApprovedAmt();
				updateBalanceObj[0][2]= reimbAdminAppr.getTotalClaimAmt();
				updateBalanceObj[0][3]= reimbAdminAppr.getTotalApprovedAmt();
				updateBalanceObj[0][4]= reimbAdminAppr.getTotalApprovedBillAmt();				//approved bill amount
				updateBalanceObj[0][5]= reimbAdminAppr.getTotalApprovedAmt();						
				updateBalanceObj[0][6]= reimbAdminAppr.getReimbHead();
				updateBalanceObj[0][7]= reimbAdminAppr.getEmpId();
				
				result =  getSqlModel().singleExecute(updateBalanceQuery,updateBalanceObj);
				/**
				 * update pre-approved bill amount from HRMS_REIMB_BALANCE
				 */
				
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
				
				totalBillAmt=Double.parseDouble(reimbAdminAppr.getTotalApprovedBillAmt())+
							preApprovedAmt;
				diffAmt=totalBillAmt-
						Double.parseDouble(reimbAdminAppr.getTotalApprovedAmt())-preApprovedAmt;
				
				if(diffAmt>Double.parseDouble(reimbAdminAppr.getTotalApprovedBillAmt())){
					if(diffAmt>preApprovedAmt){
					if(preApprovedDtl.equals("")){
						preApprovedDtl+=reimbAdminAppr.getApplId()+"#"+diffAmt;
					}else{
						preApprovedDtl+=","+reimbAdminAppr.getApplId()+"#"+diffAmt;
					}
					}
				}else if(diffAmt<Double.parseDouble(reimbAdminAppr.getTotalApprovedBillAmt())){
					
						preApprovedDtl+=reimbAdminAppr.getApplId()+"#"+diffAmt;
					
				}
				else if(diffAmt==0){
					preApprovedDtl ="";
				}
				
				String updatePreApprovedDtlQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_PRE_APPPROVED_AMT_DTL=? WHERE REIMB_CREDIT_CODE=? AND REIMB_EMP_ID=?";
				Object updatePreApprovedDtlObj[][]=new Object [1][3];
				updatePreApprovedDtlObj[0][0]= preApprovedDtl;
				updatePreApprovedDtlObj[0][1]= reimbAdminAppr.getReimbHead();
				updatePreApprovedDtlObj[0][2]= reimbAdminAppr.getEmpId();
				
				result =  getSqlModel().singleExecute(updatePreApprovedDtlQuery,updatePreApprovedDtlObj);
				
				if(result && applStatus.equals("C")){				// if accountant is not required
				/**
				 * Update onhold amount from HRMS_REIMB_BALANCE
				 */
				String updateOnholdQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_ONHOLD_AMT=REIMB_ONHOLD_AMT-? WHERE REIMB_CREDIT_CODE=? AND REIMB_EMP_ID=?";
				Object updateOnholdObj[][]=new Object[1][3];
				updateOnholdObj[0][0]= reimbAdminAppr.getTotalApprovedAmt();						
				updateOnholdObj[0][1]= reimbAdminAppr.getReimbHead();
				updateOnholdObj[0][2]= reimbAdminAppr.getEmpId();
					getSqlModel().singleExecute(updateOnholdQuery,updateOnholdObj);
				}
				/**
				 * end Update onhold amount
				 */
			}
			
		}
		/**
		 * UPDATE THE REIMB_PRE_APPPROVED_AMT DETAILS IN THE 
		 */
		if(result){
			
		}
		return result;
		
	}
	public boolean rejectClaimAppl(ReimbursementClmaimApplication bean) {
		boolean result=false;
		result = getSqlModel().singleExecute("UPDATE HRMS_REIMB_APPLICATION SET REIMB_ADMIN_STATUS ='R', REIMB_APPL_STATUS='R', REIMB_ADMIN_ID="+bean.getUserEmpId()+" WHERE REIMB_CLAIM_ID="+bean.getApplId());
		getSqlModel().singleExecute("UPDATE HRMS_REIMB_APPL_DTL SET REIMB_APPROVED_AMOUNT =0,REIMB_APPROVED_BILL_AMOUNT=0 WHERE REIMB_CLAIM_ID="+bean.getApplId());
		if(result){
			Object [][]pathObj=new Object[1][3];
			String pathQuery="INSERT INTO HRMS_REIMB_APPL_PATH ( REIMB_PATH_ID, REIMB_CLAIM_ID, REIMB_APPROVER,REIMB_APPROVAL_DATE, REIMB_APPROVER_COMMENTS, REIMB_APPROVAL_STATUS ) VALUES ( "
						+" (SELECT NVL(MAX(REIMB_PATH_ID),0)+1 FROM HRMS_REIMB_APPL_PATH) , ?, ?,  SYSDATE, ?, 'R') ";
			pathObj[0][0]=bean.getApplId();
			pathObj[0][1]=bean.getUserEmpId();
			pathObj[0][2]=bean.getApproverComments();
			result = getSqlModel().singleExecute(pathQuery,pathObj);
		}
		
		if(result){
			/**
			 * update balance amount from HRMS_REIMB_BALANCE
			 */
			
			String updateBalanceQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_ONHOLD_AMT=REIMB_ONHOLD_AMT-?,REIMB_BALANCE_AMT=REIMB_BALANCE_AMT+? WHERE REIMB_CREDIT_CODE=? AND REIMB_EMP_ID=?";
			Object updateBalanceObj[][]=new Object [1][4];
			updateBalanceObj[0][0]= bean.getTotalClaimAmt();
			updateBalanceObj[0][1]= bean.getTotalClaimAmt();
			updateBalanceObj[0][2]= bean.getReimbHead();
			updateBalanceObj[0][3]= bean.getEmpId();
			
			result =  getSqlModel().singleExecute(updateBalanceQuery,updateBalanceObj);
		}
		return result;
		
	}
	public boolean sendBackClaimAppl(ReimbursementClmaimApplication reimbAdminAppr) {
		boolean result=false;
		result = getSqlModel().singleExecute("UPDATE HRMS_REIMB_APPLICATION SET REIMB_ADMIN_STATUS ='B', REIMB_APPL_STATUS='B',REIMB_ADMIN_ID="+reimbAdminAppr.getUserEmpId()+" WHERE REIMB_CLAIM_ID="+reimbAdminAppr.getApplId());
		getSqlModel().singleExecute("UPDATE HRMS_REIMB_APPL_DTL SET REIMB_APPROVED_AMOUNT =0 WHERE REIMB_CLAIM_ID="+reimbAdminAppr.getApplId());
		if(result){
			Object [][]pathObj=new Object[1][3];
			String pathQuery="INSERT INTO HRMS_REIMB_APPL_PATH ( REIMB_PATH_ID, REIMB_CLAIM_ID, REIMB_APPROVER,REIMB_APPROVAL_DATE, REIMB_APPROVER_COMMENTS, REIMB_APPROVAL_STATUS ) VALUES ( "
						+" (SELECT NVL(MAX(REIMB_PATH_ID),0)+1 FROM HRMS_REIMB_APPL_PATH) , ?, ?,  SYSDATE, ?, 'B') ";
			pathObj[0][0]=reimbAdminAppr.getApplId();
			pathObj[0][1]=reimbAdminAppr.getUserEmpId();
			pathObj[0][2]=reimbAdminAppr.getApproverComments();
			result = getSqlModel().singleExecute(pathQuery,pathObj);
		}
		
		
		return result;
		
	}
	public void getApproverComments(ReimbursementClmaimApplication bean) {
		String commentsQuery="SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,NVL(REIMB_APPROVER_COMMENTS,' '),TO_CHAR(REIMB_APPROVAL_DATE,'DD-MM-YYYY HH12:MI PM')"
						+" FROM HRMS_REIMB_APPL_PATH "
						+" INNER JOIN HRMS_EMP_OFFC ON(EMP_ID=REIMB_APPROVER)"
						+" WHERE REIMB_CLAIM_ID="+bean.getApplId();
		Object [][]commentsObj=getSqlModel().getSingleResult(commentsQuery);
		
		if(commentsObj!=null && commentsObj.length>0){
			ArrayList tableList= new ArrayList();
			for (int j = 0; j < commentsObj.length; j++) {
				ReimbursementClmaimApplication beanList=new ReimbursementClmaimApplication();
				beanList.setApproverNameList(Utility.checkNull(String.valueOf(commentsObj[j][0])));
				beanList.setApproverCommentsList(Utility.checkNull(String.valueOf(commentsObj[j][1])));
				beanList.setApproverDateList(Utility.checkNull(String.valueOf(commentsObj[j][2])));
				tableList.add(beanList);
			}
			bean.setApproverCommentList(tableList);
			bean.setApproverListFlag("true");
		}else{
			bean.setApproverListFlag("false");
		}
		
		
	}
	public void displayPreApprovedBills(ReimbursementClmaimApplication bean){
		String billDetQuery="SELECT REIMB_PRE_APPPROVED_AMT_DTL,NVL(REIMB_PRE_APPPROVED_AMT,0),NVL(REIMB_BALANCE_AMT,0) FROM HRMS_REIMB_BALANCE WHERE REIMB_CREDIT_CODE="+bean.getReimbHead()+
		" AND REIMB_EMP_ID="+bean.getEmpId();
		
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
	
	public String getFilterString(ReimbAdminApproval reimbAdminAppr){
		
		String filterQuery="";
		try {
			if (!reimbAdminAppr.getApplIdSearch().equals("")) {
				filterQuery += " AND UPPER(REIMB_APPL_CODE) LIKE UPPER('%"
						+ reimbAdminAppr.getApplIdSearch() + "%')";
			}
			if (!reimbAdminAppr.getEmpIdSearch().equals("")) {
				filterQuery += " AND REIMB_EMP_ID= "
						+ reimbAdminAppr.getEmpIdSearch();
			}
			if (!reimbAdminAppr.getReimbHeadSearch().equals("")) {
				filterQuery += " AND HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE="
						+ reimbAdminAppr.getReimbHeadSearch();
			}
			if (!reimbAdminAppr.getClaimDateSearch().equals("")) {
				filterQuery += " AND TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY')='"
						+ reimbAdminAppr.getClaimDateSearch()+"'";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return filterQuery;
	}
	
	public Object[][] getAcountantDetails(String creditCode)
	{
		Object[][] accountantIdObj=null;
		String query="SELECT REIMB_ACCOUNTANT FROM HRMS_REIMB_CONFIG WHERE REIMB_CREDIT_HEAD = "+creditCode;
		
		accountantIdObj=getSqlModel().getSingleResult(query);
		
		return accountantIdObj;
	}

}
