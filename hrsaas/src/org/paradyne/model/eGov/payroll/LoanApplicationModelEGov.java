package org.paradyne.model.eGov.payroll;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.nfunk.jep.function.Str;
import org.paradyne.bean.Asset.AssetSubTypes;
import org.paradyne.bean.eGov.payroll.LoanApplicactionEGov;
import org.paradyne.lib.ModelBase;

public class LoanApplicationModelEGov extends ModelBase {
	public void loanDetails(LoanApplicactionEGov loanApp){
		String query=" select nvl(LOAN_AMOUNT,0), nvl(PENDING_LOAN_AMT,0), nvl(EMI_AMOUNT,0) from  HRMS_LOAN_NEW_APPL where emp_id="+loanApp.getEmpId();
		Object loanData[][] = getSqlModel().getSingleResult(query);
		if(loanData!=null && loanData.length>0){
			loanApp.setLoanAmount(String.valueOf(loanData[0][0]));
			loanApp.setPendingLoanAmount(String.valueOf(loanData[0][1]));
			loanApp.setEmiAmount(String.valueOf(loanData[0][2]));
		}else{
			loanApp.setLoanAmount("0");
			loanApp.setPendingLoanAmount("0");
			loanApp.setEmiAmount("0");
		}
		ArrayList<Object> tableList = new ArrayList<Object>();
		String strQuery="SELECT TO_CHAR(LOAN_DATE,'DD-MM-YYYY'),nvl(LOAN_AMOUNT,0),DECODE(LOAN_IS_REFUNDABLE,'Y','Yes','N','No'),nvl(EMI_AMOUNT,0),NVL(LOAN_ID,0) FROM  HRMS_LOAN_SUPPL_APPL WHERE EMP_ID="+loanApp.getEmpId()+" ORDER BY LOAN_DATE";
		Object loanSupplData[][] = getSqlModel().getSingleResult(strQuery);
		for (int i = 0; i < loanSupplData.length; i++) {
			LoanApplicactionEGov loanAppBean = new LoanApplicactionEGov();
				loanAppBean.setSrNo(String.valueOf(i + 1));
				loanAppBean.setLoanDateIt(checkNull(String.valueOf(loanSupplData[i][0])));
				loanAppBean.setLoanAmt1It(String.valueOf(loanSupplData[i][1]));
				loanAppBean.setEmiAmount1It(String.valueOf(loanSupplData[i][3]));
				loanAppBean.setIsRefundableIt(String.valueOf(loanSupplData[i][2]));
				loanAppBean.setLoanIDList(String.valueOf(loanSupplData[i][4]));
				tableList.add(loanAppBean);
		}
		
		loanApp.setList(tableList);
	}
	public boolean addItem(LoanApplicactionEGov loanApp,String[] srNo,String[] loanDate,String[]loanAmount,String[] emiAmt1,String[] isRefundable,int check) {
		ArrayList<Object> tableList = new ArrayList<Object>();
				if (loanApp.getIsRefundable().equals("Y")) {
					System.out.println("--------YES---------");
					int strAmt=0;int lstAmt=0;int finalAmt=0;
					int strEmi=0;int lstEmi=0;int finalEmi=0;
					int strPend=0;int lstPend=0;int finalPend=0;
					    strAmt = (String.valueOf(loanApp.getLoanAmount()) != null && !(String.valueOf(loanApp.getLoanAmount()).equals(""))) ? Integer.parseInt(String.valueOf(loanApp.getLoanAmount())) : 0;			
						lstAmt=	String.valueOf(loanApp.getLoanAmt1()) != null && !(String.valueOf(loanApp.getLoanAmt1()).equals(""))? Integer.parseInt(String.valueOf(loanApp.getLoanAmt1())) : 0;  
					    finalAmt=strAmt+lstAmt;
					    loanApp.setLoanAmount(String.valueOf(finalAmt));
					    
					    strEmi=String.valueOf(loanApp.getEmiAmount()) != null && !(String.valueOf(loanApp.getEmiAmount()).equals("")) ? Integer.parseInt(String.valueOf(loanApp.getEmiAmount())) : 0;
						lstEmi=	String.valueOf(loanApp.getEmiAmount1()) != null	&& !(String.valueOf(loanApp.getEmiAmount1()).equals(""))? Integer.parseInt(String.valueOf(loanApp.getEmiAmount1())) : 0;  
						finalEmi=strEmi+lstEmi;
						loanApp.setEmiAmount(String.valueOf(finalEmi));
						
						strPend=String.valueOf(loanApp.getPendingLoanAmount()) != null && !(String.valueOf(loanApp.getPendingLoanAmount()).equals("")) ? Integer.parseInt(String.valueOf(loanApp.getPendingLoanAmount())) : 0;
						finalPend=strPend+lstAmt;
						loanApp.setPendingLoanAmount(String.valueOf(finalPend)); 
				}
				else{
					System.out.println("--------NO---------");
					int strAmt=0;int lstAmt=0;int finalAmt=0;
					    strAmt = (String.valueOf(loanApp.getLoanAmount()) != null && !(String.valueOf(loanApp.getLoanAmount()).equals(""))) ? Integer.parseInt(String.valueOf(loanApp.getLoanAmount())) : 0;
						lstAmt=	String.valueOf(loanApp.getLoanAmt1()) != null && !(String.valueOf(loanApp.getLoanAmt1()).equals(""))? Integer.parseInt(String.valueOf(loanApp.getLoanAmt1())) : 0;  
					    finalAmt=strAmt+lstAmt;
					    loanApp.setLoanAmount(String.valueOf(finalAmt));
				} 
		Object[][] InsertObj=new Object[1][5];
		for (int i = 0; i < InsertObj.length; i++) {
			InsertObj[i][0] = Integer.parseInt(loanApp.getEmpId());
			InsertObj[i][1] = loanApp.getLoanDate();
			InsertObj[i][2] = loanApp.getLoanAmt1();
			InsertObj[i][3] = loanApp.getEmiAmount1();
			if(loanApp.getIsRefundable().equals("Y"))
			{
				InsertObj[i][4]=String.valueOf("Yes");
			}
			else{
				InsertObj[i][4]=String.valueOf("No");
			}
		}
		String strQuery="INSERT INTO HRMS_LOAN_SUPPL_APPL(EMP_ID,LOAN_DATE,LOAN_AMOUNT,EMI_AMOUNT,LOAN_IS_REFUNDABLE,LOAN_ID) VALUES(?,TO_DATE(?,'DD-MM-YYYY'),?,?,DECODE(?,'Yes','Y','No','N'),(SELECT nvl(MAX(LOAN_ID),0)+1 FROM HRMS_LOAN_SUPPL_APPL WHERE EMP_ID="+loanApp.getEmpId()+"))";
		boolean result = getSqlModel().singleExecute(strQuery, InsertObj);
		result=saveLoanData(loanApp,loanDate,loanAmount,emiAmt1,isRefundable);
		loanDetails(loanApp);
		return result;
		
	}

	
	public boolean saveLoanData(LoanApplicactionEGov loanApp,String[] loanDate,String[] loanAmount,String[] emiAmt1,String[] isRefundable){
		boolean result;
		Object insertObj1[][] = new Object[1][4];
		for (int i = 0; i < insertObj1.length; i++) {
			insertObj1[i][0] = loanApp.getLoanAmount();
			insertObj1[i][1] = loanApp.getPendingLoanAmount();
			insertObj1[i][2] = loanApp.getEmiAmount();
			insertObj1[i][3] = loanApp.getEmpId();
		}
		String query=" select nvl(LOAN_AMOUNT,0), nvl(PENDING_LOAN_AMT,0), nvl(EMI_AMOUNT,0) from  HRMS_LOAN_NEW_APPL where emp_id="+loanApp.getEmpId();
		Object loanData[][] = getSqlModel().getSingleResult(query);
		if(loanData!=null && loanData.length > 0){
			String strQueryUpdate="UPDATE  HRMS_LOAN_NEW_APPL  SET  LOAN_AMOUNT=?, PENDING_LOAN_AMT=?, EMI_AMOUNT=? WHERE  EMP_ID=?";
			result = getSqlModel().singleExecute(strQueryUpdate, insertObj1);
		}else{
			String strQuery="INSERT INTO HRMS_LOAN_NEW_APPL(LOAN_AMOUNT, PENDING_LOAN_AMT, EMI_AMOUNT,EMP_ID) VALUES(?,?,?,?)";
			result = getSqlModel().singleExecute(strQuery, insertObj1);
		}
		return result;
	}
	
	public void deleteLoanData(LoanApplicactionEGov bean, String[] loanDate,String[] loanAmount,
			String[] emiAmt1,String[] isRefundable,HttpServletRequest request){
		
		/*String queryNewSuppl=" select nvl(LOAN_AMOUNT,0), nvl(PENDING_LOAN_AMT,0), nvl(EMI_AMOUNT,0) from  HRMS_LOAN_NEW_APPL where emp_id="+bean.getEmpId() ;
		Object loanData[][] = getSqlModel().getSingleResult(queryNewSuppl);*/
		
		/*String strQuerySuppl="SELECT nvl(LOAN_AMOUNT,0),nvl(EMI_AMOUNT,0) FROM  HRMS_LOAN_SUPPL_APPL WHERE EMP_ID="+bean.getEmpId()+" AND LOAN_ID="+bean.getLoanID();
		Object loanSupplData[][] = getSqlModel().getSingleResult(strQuerySuppl);*/
		
		int strAmt=Integer.parseInt(bean.getLoanAmount());
		int pendAmt=Integer.parseInt(bean.getPendingLoanAmount());
		int emiAmt=Integer.parseInt(bean.getEmiAmount());
		int lstAmt=Integer.parseInt(bean.getParaLoanAmt());
		int lstEmiAmt=Integer.parseInt(bean.getParaEmiAmt());
		String isRefund=bean.getParaId();
		/* if(loanData!=null && loanData.length>0){
			 strAmt = (String.valueOf(loanData[0][0]) != null && !(String.valueOf(loanData[0][0]).equals(""))) ? Integer.parseInt(String.valueOf(loanData[0][0])) : 0;			
			 pendAmt= (String.valueOf(loanData[0][1]) != null && !(String.valueOf(loanData[0][1]).equals("")))? Integer.parseInt(String.valueOf(loanData[0][1])) : 0;
			 emiAmt = (String.valueOf(loanData[0][2]) != null && !(String.valueOf(loanData[0][2]).equals("")))? Integer.parseInt(String.valueOf(loanData[0][2])) : 0; 			 
		 }*/
		 
		 /*if(loanSupplData!=null &&loanSupplData.length>0){
			 lstAmt = (String.valueOf(loanSupplData[0][0]) != null && !(String.valueOf(loanSupplData[0][0]).equals(""))) ? Integer.parseInt(String.valueOf(loanSupplData[0][0])) : 0;			
			 lstEmiAmt=	String.valueOf(loanSupplData[0][1]) != null && !(String.valueOf(loanSupplData[0][1]).equals(""))? Integer.parseInt(String.valueOf(loanSupplData[0][1])) : 0;
	     }*/
		 strAmt=strAmt-lstAmt;
		 if(isRefund.equalsIgnoreCase("Yes")){
			 pendAmt=pendAmt-lstAmt;
			 emiAmt=emiAmt-lstEmiAmt;
		 }
		 System.out.println("FINAL DATA----------"+strAmt);
		 System.out.println("PEND DATA----------"+pendAmt);
		 System.out.println("EMI DATA----------"+emiAmt);
		 
		 Object[][] InsertObj=new Object[1][3];
		 for(int i=0;i<InsertObj.length;i++){
			 InsertObj[i][0]=String.valueOf(strAmt);
			 InsertObj[i][1]=String.valueOf(pendAmt);
			 InsertObj[i][2]=String.valueOf(emiAmt);
		 }	
		String strQueryUpdate="UPDATE  HRMS_LOAN_NEW_APPL  SET  LOAN_AMOUNT=?, PENDING_LOAN_AMT=?, EMI_AMOUNT=? WHERE  EMP_ID="+bean.getEmpId(); 
	    boolean result = getSqlModel().singleExecute(strQueryUpdate,InsertObj);
	    
	    String strQueryDelete="DELETE FROM HRMS_LOAN_SUPPL_APPL WHERE EMP_ID="+bean.getEmpId() +" AND LOAN_ID="+bean.getLoanID();
		result = getSqlModel().singleExecute(strQueryDelete);
		
	}
	
	public String checkNull(final String result) {
		if (result == null || result.equals("null")) { 
			return ""; 
		} else {
			return result;
		}
	
	}
}
