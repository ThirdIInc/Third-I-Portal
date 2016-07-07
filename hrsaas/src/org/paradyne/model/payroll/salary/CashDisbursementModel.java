package org.paradyne.model.payroll.salary;
import java.util.ArrayList;
import java.util.HashMap;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.bean.payroll.salary.CashDisburseMent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class CashDisbursementModel extends ModelBase {
	
	public boolean getDisburseDetails(CashDisburseMent cashDis,HttpServletRequest request){
		
		String query="SELECT CLAIM_ID,EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||''||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),NVL(CLAIM_PARTICULARS,' '),"
					+" NVL(CLAIM_TOTAL_AMOUNT,0),CASE WHEN CLAIM_STATUS='A' THEN 'Approved' WHEN CLAIM_STATUS='D' THEN 'Disbursed' END  FROM HRMS_CASH_CLAIM_HDR "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_CASH_CLAIM_HDR.CLAIM_EMPID)"
					+" LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)"
					+" WHERE CLAIM_STATUS='A' ORDER BY CLAIM_ID";
		
		Object[][] values = getSqlModel().getSingleResult(query);
		if(!(values==null || values.length <1)){
			
		HashMap afdata=new HashMap();
		ArrayList<Object> arrList = new ArrayList<Object>();
		for(int i=0;i<values.length;i++) {
			CashDisburseMent bean=new CashDisburseMent();
			bean.setEmpId(String.valueOf(values[i][0]));
			bean.setEmpToken(String.valueOf(values[i][1]));
			bean.setEmpName(String.valueOf(values[i][2]));
			bean.setClaimDate(String.valueOf(values[i][3]));
			if(String.valueOf(values[i][4]).equals("null"))
				bean.setClaimParticulars("");
			else
			bean.setClaimParticulars(String.valueOf(values[i][4]));
			
			bean.setClaimAmt(String.valueOf(values[i][5]));
			System.out.println("val of status of"+String.valueOf(values[i][6]));
			bean.setStatus(String.valueOf(values[i][6]));
			if(String.valueOf(values[i][6]).equals("Disbursed"))
				bean.setFlag("true");
			afdata.put(""+i,String.valueOf("A"));
			
			
			
			
			arrList.add(bean);
		}
		cashDis.setDisburseList(arrList);
		request.setAttribute("data",afdata);
		return true;
		}else {
			return false;
		}
		
	}
	
	
public boolean getDisburseDetailsOnFilter(CashDisburseMent cashDis,HttpServletRequest request){
		
		String query="SELECT CLAIM_ID,EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||''||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),NVL(CLAIM_PARTICULARS,' '),"
					+" NVL(CLAIM_TOTAL_AMOUNT,0),CASE WHEN CLAIM_STATUS='A' THEN 'Approved' WHEN CLAIM_STATUS='D' THEN 'Disbursed' END FROM HRMS_CASH_CLAIM_HDR "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_CASH_CLAIM_HDR.CLAIM_EMPID)"
					+" LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) WHERE 1=1";//AND CLAIM_STATUS='A' OR CLAIM_STATUS='D'";
		
		if(cashDis.getStatus().equals("") || cashDis.getStatus().length()==0){
			query+=" AND CLAIM_STATUS='A' OR CLAIM_STATUS='D'" ;
		}
		
		if(!cashDis.getFromDate().equals("") || cashDis.getFromDate().length()!=0){
			
			query=query+"  AND TO_CHAR(CLAIM_DATE,'DD-MM-YYYY') >='"+cashDis.getFromDate()+"'";  
			
		}
		
		if(!cashDis.getToDate().equals("") || cashDis.getToDate().length()!=0){
			
			query=query+" AND TO_CHAR(CLAIM_DATE,'DD-MM-YYYY') <='"+cashDis.getToDate()+"'";  
			
		}
		
		if(!cashDis.getStatus().equals("") || cashDis.getStatus().length()!=0 ){
			
			query=query+" AND CLAIM_STATUS="+"'"+cashDis.getStatus()+"'";
			
		}
		
		query=query+" ORDER BY CLAIM_ID";
		
		
		
		
				
		
		Object[][] values = getSqlModel().getSingleResult(query);
		if(!(values==null || values.length <1)){
		HashMap afdata=new HashMap();
		ArrayList<Object> arrList = new ArrayList<Object>();
		for(int i=0;i<values.length;i++) {
			CashDisburseMent bean=new CashDisburseMent();
			bean.setEmpId(String.valueOf(values[i][0]));
			bean.setEmpToken(String.valueOf(values[i][1]));
			bean.setEmpName(String.valueOf(values[i][2]));
			bean.setClaimDate(String.valueOf(values[i][3]));
			if(String.valueOf(values[i][4]).equals("null"))
				bean.setClaimParticulars("");
			else
			bean.setClaimParticulars(String.valueOf(values[i][4]));
			
			
			bean.setClaimAmt(String.valueOf(values[i][5]));
			bean.setStatus(String.valueOf(values[i][6]));
			if(String.valueOf(values[i][6]).equals("Disbursed"))
				bean.setFlag("true");
			afdata.put(""+i,String.valueOf("A"));
			
			
			
			
			arrList.add(bean);
		}
		cashDis.setDisburseList(arrList);
		request.setAttribute("data",afdata);
		return true;
		}	else {
			return false;
		}
		
	}
	
	public boolean updateStatus(CashDisburseMent cashDis,String claimCode)
	{
		
		
		
		 String sqlUpdate="UPDATE HRMS_CASH_CLAIM_HDR SET CLAIM_STATUS= ? WHERE CLAIM_ID=? ";
		 Object update[][] = new Object[1][2];
			
		 update[0][0]=String.valueOf("D");
		 update[0][1]=claimCode;
		
		return getSqlModel().singleExecute(sqlUpdate, update);
	
	
	}


	public void getPendingDisbursements(CashDisburseMent cashDis,
			HttpServletRequest request) {
		String query = " SELECT HRMS_CASH_CLAIM_HDR.CLAIM_EMPID,EMP_TOKEN, EMP_FNAME||''||EMP_MNAME||' '||EMP_LNAME, "
			+ " TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),NVL(CLAIM_PARTICULARS,' '),NVL(CLAIM_TOTAL_AMOUNT,0), CLAIM_STATUS,CLAIM_ID"
			+ " FROM HRMS_CASH_CLAIM_HDR "
			+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) "
			+ " WHERE CLAIM_STATUS='A' AND EMP_DIV IN (SELECT ACCOUNTANT_DIV_ID FROM HRMS_CASH_CONFIG WHERE "
			+ " ACCOUNTANT_EMP_ID = "+cashDis.getUserEmpId()+")"
			+ " ORDER BY CLAIM_ID";
		
		Object[][] pendingObj = getSqlModel().getSingleResult(query);
		if(pendingObj !=null && pendingObj.length > 0){
			ArrayList<Object> arrList = new ArrayList<Object>();
			for (int i = 0; i < pendingObj.length; i++) {
				CashDisburseMent bean=new CashDisburseMent();
				bean.setEmpId(String.valueOf(pendingObj[i][0]));
				bean.setEmpToken(String.valueOf(pendingObj[i][1]));
				bean.setEmpName(String.valueOf(pendingObj[i][2]));
				bean.setClaimDate(String.valueOf(pendingObj[i][3]));
				if(String.valueOf(pendingObj[i][4]).equals("null"))
					bean.setClaimParticulars("");
				else
				bean.setClaimParticulars(String.valueOf(pendingObj[i][4]));
				bean.setClaimAmt(String.valueOf(pendingObj[i][5]));
				bean.setStatus(String.valueOf(pendingObj[i][6]));
				bean.setFlag("false");
				bean.setItClmAppId(String.valueOf(pendingObj[i][7]));
				arrList.add(bean);
			}
			cashDis.setDisburseList(arrList);
		}
		
	}


	public void getClosedDisbursements(CashDisburseMent cashDis,
			HttpServletRequest request) {
		String query = " SELECT HRMS_CASH_CLAIM_HDR.CLAIM_EMPID,EMP_TOKEN, EMP_FNAME||''||EMP_MNAME||' '||EMP_LNAME, "
			+ " TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),NVL(CLAIM_PARTICULARS,' '),NVL(CLAIM_TOTAL_AMOUNT,0), CLAIM_STATUS,CLAIM_ID"
			+ " FROM HRMS_CASH_CLAIM_HDR "
			+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) "
			+ " WHERE CLAIM_STATUS='D' "
			+ " ORDER BY CLAIM_ID";
		Object[][] closedObj = getSqlModel().getSingleResult(query);
		if(closedObj !=null && closedObj.length > 0){
			ArrayList<Object> arrList = new ArrayList<Object>();
			cashDis.setClosedLength("true");
			String[] pageIndexClosed = Utility.doPaging(cashDis.getMyPage(),
					closedObj.length, 20);
			if (pageIndexClosed == null) {
				pageIndexClosed[0] = "0";
				pageIndexClosed[1] = "20";
				pageIndexClosed[2] = "1";
				pageIndexClosed[3] = "1";
				pageIndexClosed[4] = "";
			}// END IF

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndexClosed[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndexClosed[3])));
			if (pageIndexClosed[4].equals("1"))
				cashDis.setMyPage("1");
			for (int i = Integer.parseInt(pageIndexClosed[0]); i < Integer
					.parseInt(pageIndexClosed[1]); i++) {
				CashDisburseMent bean=new CashDisburseMent();
				bean.setClosedEmpId(String.valueOf(closedObj[i][0]));
				bean.setEmpToken(String.valueOf(closedObj[i][1]));
				bean.setEmpName(String.valueOf(closedObj[i][2]));
				bean.setClaimDate(String.valueOf(closedObj[i][3]));
				if(String.valueOf(closedObj[i][4]).equals("null"))
					bean.setClaimParticulars("");
				else
				bean.setClaimParticulars(String.valueOf(closedObj[i][4]));
				bean.setClaimAmt(String.valueOf(closedObj[i][5]));
				bean.setStatus(String.valueOf(closedObj[i][6]));
				bean.setFlag("true");
				bean.setClosedClmAppId(String.valueOf(closedObj[i][7]));
				arrList.add(bean);
			}
			cashDis.setClosedList(arrList);
		}
		
	}


	public void setDisburseDetails(CashDisburseMent cashDis, String claimId,
			String empCode, String status) {
		String disbQuery = " SELECT CLAIM_ID,EMP_TOKEN, EMP_FNAME||''||EMP_MNAME||' '||EMP_LNAME, CLAIM_EMPID, NVL(CENTER_NAME,' '), "
			+ " NVL(DEPT_NAME,' '), NVL(DIV_NAME,' '), NVL(RANK_NAME,' '), TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'), "
			+ " DECODE(CLAIM_STATUS,'A','Pending for Disbursement','D','Disbursed'),TO_CHAR(CLAIM_DISBURSE_DATE,'DD-MM-YYYY'), "
			+ " NVL(CLAIM_DISBURSE_PAYMODE,'CA'), NVL(CLAIM_DISBURSE_AMOUNT,CLAIM_TOTAL_AMOUNT), NVL(CLAIM_DISBURSE_COMMENTS,' '), "
			+ " NVL(BANK_NAME,' '),NVL(CLAIM_DISBURSE_BANKID,SAL_REIMBANK), NVL(CLAIM_DISBURSE_ACCOUNTNO,SAL_REIMBMENT) "
			+ " FROM HRMS_CASH_CLAIM_HDR " 
			+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) "
			+ " INNER JOIN HRMS_CENTER ON (CENTER_ID = EMP_CENTER) "
			+ " INNER JOIN HRMS_DEPT ON (DEPT_ID = EMP_DEPT) "
			+ " INNER JOIN HRMS_DIVISION ON(DIV_ID = EMP_DIV) "
			+ " INNER JOIN HRMS_RANK ON (RANK_ID = EMP_RANK) "
			//+ " LEFT JOIN HRMS_BANK ON(BANK_MICR_CODE = CLAIM_DISBURSE_BANKID) "
			+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
			+ " LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_REIMBANK)"
			+ " WHERE CLAIM_ID = "+claimId+" AND CLAIM_STATUS = '"+status+"' AND CLAIM_EMPID = "+empCode;
		Object[][] disbObj = getSqlModel().getSingleResult(disbQuery);
		
		if(disbObj != null && disbObj.length > 0){
			cashDis.setClaimId(Utility.checkNull(String.valueOf(disbObj[0][0])));
			cashDis.setViewEmpToken(Utility.checkNull(String.valueOf(disbObj[0][1])));
			cashDis.setViewEmpName(Utility.checkNull(String.valueOf(disbObj[0][2])));
			cashDis.setViewEmpId(Utility.checkNull(String.valueOf(disbObj[0][3])));
			cashDis.setBranchName(Utility.checkNull(String.valueOf(disbObj[0][4])));
			cashDis.setDepartmentName(Utility.checkNull(String.valueOf(disbObj[0][5])));
			cashDis.setDivisionName(Utility.checkNull(String.valueOf(disbObj[0][6])));
			cashDis.setDesignationName(Utility.checkNull(String.valueOf(disbObj[0][7])));
			cashDis.setDisburseDate(Utility.checkNull(String.valueOf(disbObj[0][8])));
			cashDis.setDisburseStatus(Utility.checkNull(String.valueOf(disbObj[0][9])));
			cashDis.setPaymentDate(Utility.checkNull(String.valueOf(disbObj[0][10])));
			cashDis.setPaymentmode(Utility.checkNull(String.valueOf(disbObj[0][11])));
			cashDis.setDisburseAmount(Utility.checkNull(String.valueOf(disbObj[0][12])));
			cashDis.setComment(Utility.checkNull(String.valueOf(disbObj[0][13])));
			cashDis.setBank(String.valueOf(disbObj[0][14]));
			cashDis.setBankid(String.valueOf(disbObj[0][15]));
			cashDis.setAccountNo(String.valueOf(disbObj[0][16]));
		}
		
	}


	public boolean updateHeader(CashDisburseMent cashDis, String status) {
		Object[][] updateObj = new Object[1][8];
		
		if(status.equals("D")){
			updateObj[0][1] = cashDis.getPaymentDate();
			updateObj[0][2] = cashDis.getPaymentmode();
			updateObj[0][3] = cashDis.getDisburseAmount();
			updateObj[0][4] = cashDis.getComment();
			if(cashDis.getPaymentmode().trim().equals("TR")){
				updateObj[0][5] = cashDis.getBankid();
				updateObj[0][6] = cashDis.getAccountNo();
			}else{
				updateObj[0][5] = "";
				updateObj[0][6] = "";
			}
			
		}else{
			for (int i = 0; i < updateObj[0].length; i++) {
				updateObj[0][i] = "";
			}
		}
		updateObj[0][0] = status;
		updateObj[0][7] = cashDis.getClaimId();
		
		for (int i = 0; i < updateObj[0].length; i++) {
			System.out.println("updateObj[0]["+i+"]"+updateObj[0][i]); 
		}
		
		String sqlUpdate="UPDATE HRMS_CASH_CLAIM_HDR SET CLAIM_STATUS= ?, CLAIM_DISBURSE_DATE=TO_DATE(?,'DD-MM-YYYY'), "
			+ " CLAIM_DISBURSE_PAYMODE=?, CLAIM_DISBURSE_AMOUNT=?, CLAIM_DISBURSE_COMMENTS=?, CLAIM_DISBURSE_BANKID=?, "
			+ " CLAIM_DISBURSE_ACCOUNTNO=? WHERE CLAIM_ID=? ";
		//CLAIM_DISBURSE_DATE, CLAIM_DISBURSE_PAYMODE, CLAIM_DISBURSE_AMOUNT, CLAIM_DISBURSE_COMMENTS, CLAIM_DISBURSE_BANKID, CLAIM_DISBURSE_ACCOUNTNO
		return getSqlModel().singleExecute(sqlUpdate, updateObj);
	}
	

}
