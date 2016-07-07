package org.paradyne.model.recruitment;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.bean.Recruitment.CandidateScreeningApproval;
import org.paradyne.bean.Recruitment.OfferApproval;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
public class OfferApprovalModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OfferApprovalModel .class);
	/**
	 * following function is called when any button is clicked in Appointment Approval Section 
	 * @param bean
	 * @param offStatus
	 * @param appStatus
	 */
	
	public void getAppointmentRecords(OfferApproval bean,String offStatus,String appStatus,HttpServletRequest request){
		try{
	
		ArrayList<Object> tableList=new ArrayList<Object>();
		String query="SELECT APPOINT_CODE , APPOINT_REQS_CODE, APPOINT_CAND_CODE, REQS_NAME,"
						+" CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,"
						+" RANK_NAME,TO_DATE(SYSDATE, 'DD-MM-YYYY')-TO_DATE(APPOINT_DUE_DATE, 'DD-MM-YYYY'), CAND_RESUME  "
						+" ,APPOINT_APPR_STATUS,NVL(TO_CHAR(APPOINT_APPR_DATE,'DD-MM-YYYY'),' '),APPOINT_TEMPLATE FROM HRMS_REC_APPOINT "
						+" INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_APPOINT.APPOINT_REQS_CODE)"
						+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
						+" INNER JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE=HRMS_REC_APPOINT.APPOINT_CAND_CODE)"
						+" WHERE APPOINT_SIGN_AUTH="+bean.getUserEmpId()+" AND APPOINT_STATUS IN ("+offStatus+") AND APPOINT_APPR_STATUS='"+appStatus+"'";
		
					
		Object value[][] = getSqlModel().getSingleResult(query);
		String[] pageIndex = Utility.doPaging(bean.getMyPage1(),value.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPageAppt", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("PageNoAppt", Integer.parseInt(String.valueOf(pageIndex[3])));
		
		if(pageIndex[4].equals("1"))
			bean.setMyPage1("1");			
						
			if(value!=null && value.length>0) {
			
				      for(int i=Integer.parseInt(String.valueOf(pageIndex[0]));i<Integer.parseInt(String.valueOf(pageIndex[1]));i++){
				    	  OfferApproval bean1=new OfferApproval();
				    	  bean1.setAptId(String.valueOf(value[i][0]));
				    	  bean1.setAptReqnCode(String.valueOf(value[i][1]));
				    	  bean1.setAprCandCode(String.valueOf(value[i][2]));
				    	  bean1.setAptReqName(String.valueOf(value[i][3]));
				    	  bean1.setAptCandName(String.valueOf(value[i][4]));
				    	  if(String.valueOf(value[i][5]).equals("null") || String.valueOf(value[i][5]).equals("")){
								bean1.setAptPos("");
							}else{
								bean1.setAptPos(String.valueOf(value[i][5]));
							}
				    	  
				    	  if(appStatus.equals("P")){
				    		  if(String.valueOf(value[i][6]).equals("null") || String.valueOf(value[i][6]).equals("")){
									bean1.setAptDays("0");
								}else{
									bean1.setAptDays(String.valueOf(value[i][6]));
								}
				    		  
				    		  
				    	  }else if(appStatus.equals("A")){
				    		  
				    		  bean1.setAptDays(String.valueOf(value[i][9]));
				    		
				    	  }else if(appStatus.equals("R")){
				    		  bean1.setAptDays(String.valueOf(value[i][9]));
				    		  bean1.setRejectedFlag("true");
				    	  }
				    	  
				    	  
				    	  
				    	
				    	  
				    		if(appStatus.equals("P")){
								bean1.setAptStatusFlag("true");
							}else {
								bean1.setAptStatusFlag("false");
							}
				    	  
				    		
				    		
				    	  bean1.setAppointment(String.valueOf(value[i][8]));
				    	  
				    	  bean1.setAptResume(String.valueOf(value[i][7]));
				    	  
				    	  bean1.setTemplateCode(String.valueOf(value[i][10]));
				    	  
				    	  tableList.add(bean1);
											
			      }
				         bean.setAptmtList(tableList);
				         
		}else{
			bean.setNoApt("true");
		}
		}catch(Exception e){
			logger.error("exception in getAppointmentRecords method", e);
		}
	}
	
	
	
	
	/**
	 * following function is called to generate the offer details 
	 * @param bean
	 * @param offStatus
	 * @param appStatus
	 */
	public void getOfferRecords(OfferApproval bean,String offStatus,String appStatus,HttpServletRequest request){
		ArrayList<Object> tableList=new ArrayList<Object>();
		
		try {
			String query="SELECT OFFER_REQS_CODE,REQS_NAME,OFFER_CAND_CODE,CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,"
						+" RANK_NAME,NVL(TO_DATE(SYSDATE, 'DD-MM-YYYY')-TO_DATE(OFFER_DUE_DATE, 'DD-MM-YYYY'),0),CAND_RESUME,OFFER_CODE,OFFER_APPR_STATUS,NVL(TO_CHAR(OFFER_APPR_DATE,'DD-MM-YYYY'),' ') ,OFFER_TEMPLATE FROM HRMS_REC_OFFER "
						+" INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_OFFER.OFFER_REQS_CODE)"
						+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
						+" INNER JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE=HRMS_REC_OFFER.OFFER_CAND_CODE)"
						+" WHERE OFFER_SIGN_AUTH="+bean.getUserEmpId()+" AND OFFER_STATUS IN ("+offStatus+") AND OFFER_APPR_STATUS='"+appStatus+"'";
			
			Object[][] data = getSqlModel().getSingleResult(query);
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),data.length, 20);	
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
				bean.setMyPage("1");
			
			
			
					if(data!=null && data.length>0){
						for(int i=Integer.parseInt(String.valueOf(pageIndex[0]));i<Integer.parseInt(String.valueOf(pageIndex[1]));i++){
							OfferApproval bean1=new OfferApproval();
							bean1.setReqCode(String.valueOf(data[i][0]));
							bean1.setReqName(String.valueOf(data[i][1]));
							bean1.setCandCode(String.valueOf(data[i][2]));
							bean1.setCandName(String.valueOf(data[i][3]));
							bean1.setPosition(String.valueOf(data[i][4]));
							//if(String.valueOf(data[i][5]).equals("null") || String.valueOf(data[i][5]).equals("")){
							//	bean1.setDays("");
							//}else{
							//	bean1.setDays(String.valueOf(data[i][5]));
						//	}
						
							bean1.setResume(String.valueOf(data[i][6]));
							bean1.setOfferCode(String.valueOf(data[i][7]));
							if(appStatus.equals("P")){
								bean1.setStatusFlag("true");
							}else {
								bean1.setStatusFlag("false");
							}
							
							if(appStatus.equals("P")){
								bean1.setPending("true");
								if(String.valueOf(data[i][5]).equals("null") || String.valueOf(data[i][5]).equals("")){
									bean1.setDays("0");
								}else{
									bean1.setDays(String.valueOf(data[i][5]));
								}
								
							}else if(appStatus.equals("A")){
								bean1.setApproved("true");
								bean1.setDays(String.valueOf(data[i][9]));
							}else if(appStatus.equals("R")){
								bean1.setRejected("true");
								bean1.setDays(String.valueOf(data[i][9]));
							}
							
							bean1.setTemplateCode(String.valueOf(data[i][10]));
							
							
							tableList.add(bean1);
							
							
						 }//End of for loop
						
						bean.setList(tableList);
						
					}//End of if condition		
					else{
						bean.setNoData("true");
					}
		} catch (Exception e) {
			logger.error("exception in getOfferRecords method", e);
		}
				
	 }//End of method
	
/**
 * following function is called to update the offer details
 * @param bean
 * @param reqnCode
 * @param offerId
 * @param status
 * @param candCode
 * @param request
 * @return
 */
	public boolean updateOffer(OfferApproval bean,String reqnCode[],String []offerId,String[] status,String[] candCode,HttpServletRequest request) {
		boolean flag=false;
		try{	
		
			String query1="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] data2 = getSqlModel().getSingleResult(query1);
			
			if(reqnCode!=null && reqnCode.length>0){
				Object[][] data=new Object[reqnCode.length][6];
				for(int i=0;i<reqnCode.length;i++){
					if(!(status[i].equals("") || status[i].equals("null"))){
						
						data[i][0]=status[i];//Offer_appr_status
						data[i][1]=String.valueOf(data2[0][0]);//Sys date
						
						if(status[i].equals("A")){
							data[i][2]=String.valueOf("I");	//Offer Status
						}else{	
							data[i][2]=String.valueOf("S");
						}
						
						data[i][3]=reqnCode[i];//Requisition Code
						
						data[i][4]=offerId[i];//Offer Code
						
						data[i][5]=candCode[i];//Candidate Code
						
					  }
				    }
				flag=getSqlModel().singleExecute(getQuery(1),data);
				 }
		}catch(Exception e){
			
		}
			return flag;	
		}//End of method	
	
	
	public boolean updateAppointment(OfferApproval bean,String reqnCode[],String []apptId,String[] status,String[] candCode,HttpServletRequest request) {
		boolean flag=false;
		try{	
		
			String query1="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] data2 = getSqlModel().getSingleResult(query1);
			
			if(reqnCode!=null && reqnCode.length>0){
				Object[][] data=new Object[reqnCode.length][6];
				for(int i=0;i<reqnCode.length;i++){
					if(!(status[i].equals("") || status[i].equals("null"))){
						
						data[i][0]=status[i];//Apptmt appr status
					
						data[i][1]=String.valueOf(data2[0][0]);//Sys date
						
						if(status[i].equals("A")){
							data[i][2]=String.valueOf("I");	//Offer Status
						}else{	
							data[i][2]=String.valueOf("S");
						}
						data[i][3]=reqnCode[i];//Requisition Code
						data[i][4]=apptId[i];//Appointment code
						data[i][5]=candCode[i];//Candidate Code
						
					  }
				    }
				flag=getSqlModel().singleExecute(getQuery(2),data);
				 }
		}catch(Exception e){
			
		}
			return flag;	
		}//End of method	


	public String getTotalAmt(String candCode, String reqCode,
			String operationType, boolean esicFlag) {
		String totalAmt = "0";

		String totalCreditQry = " SELECT ROUND(NVL(SUM(OFFER_CREDIT_AMOUNT),0)) FROM HRMS_REC_OFFER_SALARY "
				+ " LEFT JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=OFFER_CREDIT_CODE) WHERE HRMS_REC_OFFER_SALARY.OFFER_CAND_CODE= "
				+ candCode
				+ " AND HRMS_REC_OFFER_SALARY.OFFER_REQS_CODE="
				+ reqCode + " AND CREDIT_PERIODICITY='M'";
		Object[][] totCredit = getSqlModel().getSingleResult(totalCreditQry);
		String totalDebitQry = "";
		if (esicFlag) {
			totalDebitQry = "SELECT DISTINCT ROUND(OFFER_CREDIT_AMOUNT*.12) + ROUND(CASE WHEN SUM(OFFER_CREDIT_AMOUNT) <10000 THEN ((OFFER_CREDIT_AMOUNT/0.40)*0.0175) ELSE 0 END), "
					+ " (ROUND(OFFER_CREDIT_AMOUNT*.12) + ROUND(CASE WHEN SUM(OFFER_CREDIT_AMOUNT) <10000 THEN ((OFFER_CREDIT_AMOUNT/0.40)*0.0475) ELSE 0 END))"
					+ " FROM HRMS_REC_OFFER_SALARY "
					+ " WHERE HRMS_REC_OFFER_SALARY.OFFER_CAND_CODE="
					+ candCode
					+ " AND HRMS_REC_OFFER_SALARY.OFFER_REQS_CODE="
					+ reqCode
					+ " AND OFFER_CREDIT_CODE=1 GROUP BY OFFER_CREDIT_AMOUNT";
		} else {
			totalDebitQry = "SELECT DISTINCT ROUND(OFFER_CREDIT_AMOUNT*.12)  "
					+ " FROM HRMS_REC_OFFER_SALARY "
					+ " WHERE HRMS_REC_OFFER_SALARY.OFFER_CAND_CODE="
					+ candCode + " AND HRMS_REC_OFFER_SALARY.OFFER_REQS_CODE="
					+ reqCode
					+ " AND OFFER_CREDIT_CODE=1 GROUP BY OFFER_CREDIT_AMOUNT";
		}

		Object[][] totDebit = getSqlModel().getSingleResult(totalDebitQry);
		if (operationType.equals("takeHome")) {

			totalAmt = String.valueOf(Double.parseDouble(String
					.valueOf(totCredit[0][0]))
					- Double.parseDouble(String.valueOf(totDebit[0][0])));
		} else {
			totalAmt = String.valueOf(Double.parseDouble(String
					.valueOf(totCredit[0][0]))
					+ Double.parseDouble(String.valueOf(totDebit[0][1])));
			if (operationType.equals("ctcPerYear")) {
				totalAmt = String.valueOf(Math.round(Double
						.parseDouble(totalAmt) * 12));
			}
		}

		return totalAmt;

	}
	
	
	public String onlineApproveRejectOffer(HttpServletRequest request,
			String candidateCode, String offerCode, String status,
			String remarks, String signingAuthorityCode, String level) {
		String result = "";
		
		return result;

	}
	
	 
	public String onlineApproveRejectAppointment(HttpServletRequest request,
			String candidateCode, String offerCode, String status,
			String remarks, String signingAuthorityCode, String level) {
		String result = "";
		
		return result;

	}
}
	
	

