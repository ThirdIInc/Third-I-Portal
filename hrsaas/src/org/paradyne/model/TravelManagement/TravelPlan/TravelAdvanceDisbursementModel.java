package org.paradyne.model.TravelManagement.TravelPlan;

/**
 * Dilip
 * 15-08-2008
 **/
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.TravelPlan.TravelAdvanceDisbursement;

import org.paradyne.lib.ModelBase;

import sun.security.krb5.Checksum;

import com.crystaldecisions.report.web.event.bo;

public class TravelAdvanceDisbursementModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/*
	 * This method is used for show employee details and change the status of
	 * schedule
	 */
	/**
	 * @param bean
	 * @param status
	 */
	public void callStatus(TravelAdvanceDisbursement bean,
			HttpServletRequest request) {
		try {
			
			// check the status here
			String chkSql = "SELECT MANG_AUTH_BRANCH FROM HRMS_TMS_MANG_AUTH WHERE MANG_AUTH_SCHEDULER ="
					+ bean.getUserEmpId()
					+ " AND MANG_AUTH_STATUS ='A' AND MANG_AUTH_DEFAULT_FLAG ='Y'";
			Object[][] chkData = getSqlModel().getSingleResult(chkSql);
			
			String query = " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME  , NVL(TRAVEL_APP_REQUEST,'') , TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY')"
					+ "	,NVL(SCH_APPR_ADVANCE_SAN,0) ,TRAVEL_APP_ID, TRAVEL_APP_EMPID"
					+ "  FROM HRMS_TMS_ADVANCE"
					+ "  LEFT JOIN HRMS_TMS_TRAVEL_APP   ON (HRMS_TMS_ADVANCE.ADVANCE_TRAPP_ID = HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID) "
					+"   LEFT JOIN HRMS_TMS_SCH_DTL  ON (HRMS_TMS_ADVANCE.ADVANCE_TRAPP_ID = HRMS_TMS_SCH_DTL.SCH_DTL_TRAVEL_APP_ID) "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID)";
			if (bean.getPayStatus().equals("pend")) {
				query += "  WHERE ADVANCE_PAYMENT_DATE is  null AND TRAVEL_APLT_CONF_STATUS ='A'";
			} else {
				query += "  WHERE ADVANCE_PAYMENT_DATE is not null AND TRAVEL_APLT_CONF_STATUS ='A'";
			}   
			if (chkData != null && chkData.length > 0) {
			} else {
				query += " AND HRMS_EMP_OFFC.EMP_CENTER IN(SELECT MANG_AUTH_BRANCH FROM HRMS_TMS_MANG_AUTH WHERE MANG_AUTH_ACCOUNT_PER ="
						+ bean.getUserEmpId() + " AND MANG_AUTH_STATUS ='A') ";
			}
			
			query += " ORDER BY TRAVEL_APP_APPDATE DESC";
			
			Object[][] result = getSqlModel().getSingleResult(query);

			 doPaging(bean, result.length, new Object[][]{}, request);
			 int fromTotRec = Integer.parseInt(bean.getFromTotRec());
			 int toTotRec = Integer.parseInt(bean.getToTotRec());

			ArrayList<Object> travelList = new ArrayList<Object>();

			for(int i =fromTotRec;i<toTotRec;i++)
			{
			TravelAdvanceDisbursement bean1 = new TravelAdvanceDisbursement();

				bean1.setEmpName(checkNull(String.valueOf(result[i][0])));
				bean1.setReqName(checkNull(String.valueOf(result[i][1])));
				bean1.setAppDate(checkNull(String.valueOf(result[i][2])));
				
				bean1.setAdvAmount(checkNull(String.valueOf(result[i][3])));
				bean1.setEmpId(checkNull(String.valueOf(result[i][5])));
				bean1.setAppId(String.valueOf(result[i][4]));
				if (bean.getPayStatus().equals("pend"))
				{
					bean1.setFlag("true");
				}
				else
				{
					bean1.setFlag("false");
				}
				
				
				travelList.add(bean1);
			} // end of for loop
			
			bean.setAdvanceList(travelList);
			 if(travelList.size()==0) { bean.setListLength("0");
			  bean.setNoData("true"); }else { bean.setListLength("1");
			  bean.setNoData("false"); }
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	} // end of callStatus

	// }

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public void doPaging(TravelAdvanceDisbursement bean, int empLength,
			Object[][] attnObj, HttpServletRequest request) {
		try {
			/*
			 * totalRec -: No. of records per page fromTotRec -: Starting No. of
			 * record to show on a current page toTotRec -: Last No. of record
			 * to show on a current page pageNo -: Current page to show
			 * totalPage -: Total No. of Pages
			 */

			String pagingSql = " SELECT CONF_RECORDS_PER_PAGE FROM HRMS_SALARY_CONF ";
			Object[][] pagingObj = getSqlModel().getSingleResult(pagingSql);
			int totalRec = Integer.parseInt(String.valueOf(pagingObj[0][0]));

			int pageNo = 1;
			int fromTotRec = 0;
			int toTotRec = totalRec;
			int searchCount = 0;
			int totalPage = 0;
			String empExists = "false";

			java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(
					(double) empLength / totalRec);
			totalPage = Integer.parseInt(bigDecRow1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());

			if (String.valueOf(bean.getHdPage()).equals("null")
					|| String.valueOf(bean.getHdPage()).equals(null)
					|| String.valueOf(bean.getHdPage()).equals("")) {
				pageNo = 1;
				fromTotRec = 0;
				toTotRec = totalRec;

				if (toTotRec > empLength) {
					toTotRec = empLength;
				}
				bean.setHdPage("1");
			} else {
				pageNo = Integer.parseInt(bean.getHdPage());

				if (pageNo == 1) {
					fromTotRec = 0;
					toTotRec = totalRec;
				} else {
					toTotRec = toTotRec * pageNo;
					fromTotRec = toTotRec - totalRec;
				}
				if (toTotRec > empLength) {
					toTotRec = empLength;
				}

			}
			bean.setFromTotRec(String.valueOf(fromTotRec));
			bean.setToTotRec(String.valueOf(toTotRec));

			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("fromTotRec", fromTotRec);
			request.setAttribute("toTotRec", toTotRec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void callDtl(TravelAdvanceDisbursement travelDisburs,
			HttpServletRequest request) {

		try {
			
			String Query = "SELECT  distinct EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
					+ " CENTER_NAME,DEPT_NAME,RANK_NAME,TO_CHAR(SYSDATE,'DD-MM-YYYY'),NVL(CADRE_NAME,' ')FROM HRMS_EMP_OFFC "
					+ "	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
					+ "  LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
					+ "  left join HRMS_TMS_TRAVEL_APP  on(HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID=HRMS_EMP_OFFC.EMP_ID)" 
			        +"  WHERE  TRAVEL_APP_EMPID ="+travelDisburs.getEmpId()+" and TRAVEL_APP_ID="+travelDisburs.getAppId()+"";
			
			System.out.println("Query-----------------------"+Query);
			Object[][] data = getSqlModel().getSingleResult(Query);
			
			if(data!=null && data.length >0)
				
			{
				travelDisburs.setEmpToken(String.valueOf(data[0][0]));
				travelDisburs.setEmppName(String.valueOf(data[0][1]));
				travelDisburs.setBrName(String.valueOf(data[0][2]));
				travelDisburs.setDept(String.valueOf(data[0][3]));
				travelDisburs.setDesg(String.valueOf(data[0][4]));
				travelDisburs.setApplDate(String.valueOf(data[0][5]));
				travelDisburs.setApplicationDate(String.valueOf(data[0][5]));
				// travelDisburs.setStatus(String.valueOf(data[0][6]));
				travelDisburs.setGradeName(String.valueOf(data[0][6]));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

	public boolean getAddPayDetails(TravelAdvanceDisbursement travelDisburs) {
		
		

		boolean flag = false;
		Object[][] obj = new Object[1][9];
		try {
			if(obj!=null && obj.length >0){
			obj[0][0] = travelDisburs.getIssueAmount();
			obj[0][1] = travelDisburs.getPaymentDate();
			obj[0][2] = travelDisburs.getModPayment();
			if (travelDisburs.getModPayment().equals("Q")) {
				obj[0][3] = travelDisburs.getBank();
				obj[0][4] = travelDisburs.getCheckNo();
				obj[0][5] = travelDisburs.getCheckDate();
			} 
			else if(travelDisburs.getModPayment().equals("T")){
				obj[0][3] = travelDisburs.getBank1();
				obj[0][4] = travelDisburs.getAccNo();
				}
			obj[0][6] = travelDisburs.getComment();
			obj[0][7] = travelDisburs.getApplDate();
			//obj[0][8] = travelDisburs.getAccNo();
			obj[0][8] = travelDisburs.getEmpId();
						
			}
			String Query="";
			if (travelDisburs.getModPayment().equals("Q")) {
			  Query = " UPDATE HRMS_TMS_ADVANCE SET ADVANCE_ID =(SELECT NVL(MAX(ADVANCE_ID),0)+1 FROM HRMS_TMS_ADVANCE ), ADVANCE_ISSUE_AMT="+obj[0][0]+" ,"
			+ " ADVANCE_SYSDATE=TO_DATE(SYSDATE,'DD-MM-YYYY'), ADVANCE_PAYMENT_DATE=TO_DATE('"+obj[0][1]+"','DD-MM-YYYY'), ADVANCE_PAYMENT_MODE='"+obj[0][2]+"', "
			+ "  ADVANCE_CHEQUE_NO ="+obj[0][4]+", ADVANCE_CHEQUE_DATE=TO_DATE('"+obj[0][5]+"','DD-MM-YYYY'), "
			+ " ADVANCE_BANK_NAME='"+obj[0][3]+"', ADVANCE_COMMENTS='"+obj[0][6]+"', ADVANCE_EMP_ID ="+obj[0][8]+" WHERE ADVANCE_TRAPP_ID ="+travelDisburs.getAppId()+" ";
			
			}
			else if(travelDisburs.getModPayment().equals("T"))
			{
				Query = " UPDATE HRMS_TMS_ADVANCE SET ADVANCE_ID =(SELECT NVL(MAX(ADVANCE_ID),0)+1 FROM HRMS_TMS_ADVANCE ), ADVANCE_ISSUE_AMT="+obj[0][0]+" ,"
				+ " ADVANCE_SYSDATE=TO_DATE(SYSDATE,'DD-MM-YYYY'), ADVANCE_PAYMENT_DATE=TO_DATE('"+obj[0][1]+"','DD-MM-YYYY'), ADVANCE_PAYMENT_MODE='"+obj[0][2]+"', "
				+ "  ADVANCE_BANK_NAME='"+obj[0][3]+"' , ADVANCE_TRANSFER_ACC_NO ="+obj[0][4]+",  ADVANCE_COMMENTS='"+obj[0][6]+"', ADVANCE_EMP_ID ="+obj[0][8]+" WHERE ADVANCE_TRAPP_ID ="+travelDisburs.getAppId()+" ";
			}
		else
			{
				Query = " UPDATE HRMS_TMS_ADVANCE SET ADVANCE_ID =(SELECT NVL(MAX(ADVANCE_ID),0)+1 FROM HRMS_TMS_ADVANCE ), ADVANCE_ISSUE_AMT="+obj[0][0]+" ,"
				+ " ADVANCE_SYSDATE=TO_DATE(SYSDATE,'DD-MM-YYYY'), ADVANCE_PAYMENT_DATE=TO_DATE('"+obj[0][1]+"','DD-MM-YYYY'), ADVANCE_PAYMENT_MODE='"+obj[0][2]+"', "
				+ " ADVANCE_COMMENTS='"+obj[0][6]+"', ADVANCE_EMP_ID ="+obj[0][8]+" WHERE ADVANCE_TRAPP_ID ="+travelDisburs.getAppId()+" ";
				
			}
			flag = getSqlModel().singleExecute(Query);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return flag;
	}

	public void callMod(TravelAdvanceDisbursement travelDisburs,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String query="SELECT nvl(SCH_APPR_ADVANCE_SAN,0),DECODE(TRAVEL_APP_PAYMODE,'C','CASH','T','Transfer','Q','Cheque') FROM HRMS_TMS_TRAVEL_APP" 
			    +"  LEFT JOIN HRMS_TMS_SCH_DTL  ON (HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID = HRMS_TMS_SCH_DTL.SCH_DTL_TRAVEL_APP_ID) "
				+" WHERE TRAVEL_APP_EMPID ="+travelDisburs.getEmpId()+" and TRAVEL_APP_ID="+travelDisburs.getAppId()+"";
		logger.info(" ==========call Modi========== "+query);
		Object[][] data = getSqlModel().getSingleResult(query);

		travelDisburs.setReqAdvName(checkNull(String.valueOf(data[0][0])));
		travelDisburs.setPaymentMode(checkNull(String.valueOf(data[0][1])));
        }

	
	public void paymentDtl(TravelAdvanceDisbursement travelDisburs,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		 try {
			String query=" SELECT  ADVANCE_ISSUE_AMT, TO_CHAR(ADVANCE_SYSDATE,'DD-MM-YYYY'),TO_CHAR(ADVANCE_PAYMENT_DATE,'DD-MM-YYYY'),ADVANCE_PAYMENT_MODE, "
						   +"  ADVANCE_TRANSFER_ACC_NO, ADVANCE_CHEQUE_NO, TO_CHAR(ADVANCE_CHEQUE_DATE,'DD-MM-YYYY')," 
						   +"  ADVANCE_BANK_NAME, ADVANCE_COMMENTS,decode(ADVANCE_PAYMENT_MODE,'T','Transfer','C','Cash','Q','Cheque'),DECODE(ADVANCE_BANK_NAME,'S','SBI','H','HDFC','I','ICICI')   FROM HRMS_TMS_ADVANCE"
						   +" WHERE ADVANCE_TRAPP_ID="+travelDisburs.getAppId()+" and ADVANCE_EMP_ID="+travelDisburs.getEmpId()+" ";
		logger.info("Payment Dtl+++++++++++++++++++++++="+query);
		  Object[][]data   =getSqlModel().getSingleResult(query);
		  travelDisburs.setIssueAmount(checkNull(String.valueOf(data[0][0])));
		  travelDisburs.setPaymentDate(checkNull(String.valueOf(data[0][2])));
		  travelDisburs.setModPayment(checkNull(String.valueOf(data[0][3])));
		  if(travelDisburs.getModPayment().equals("Q")){
			  travelDisburs.setBank(checkNull(String.valueOf(data[0][7])));
			  travelDisburs.setCheckNo(checkNull(String.valueOf(data[0][5])));
			  travelDisburs.setCheckDate(checkNull(String.valueOf(data[0][6])));
		  }
		  else if(travelDisburs.getModPayment().equals("T")){
			  travelDisburs.setBank1(checkNull(String.valueOf(data[0][7])));
			  travelDisburs.setAccNo(checkNull(String.valueOf(data[0][4])));
		  }
		  travelDisburs.setComment(checkNull(String.valueOf(data[0][8])));
		  logger.info("hhh"+ String.valueOf(data[0][9]));
		  travelDisburs.setBanker(checkNull(String.valueOf(data[0][9])));
		  travelDisburs.setBanker1(checkNull(String.valueOf(data[0][10])));
	}
		 catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		   
	}

}
