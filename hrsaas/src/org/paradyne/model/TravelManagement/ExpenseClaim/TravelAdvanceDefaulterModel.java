package org.paradyne.model.TravelManagement.ExpenseClaim;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.TravelManagement.ExpenseClaim.TravelAdvanceDefaulter;
import org.paradyne.lib.ModelBase;

public class TravelAdvanceDefaulterModel extends ModelBase {
	
	public void goForAdavnce(TravelAdvanceDefaulter bean)
	{
String sql =" SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TRAVEL_APP_REQUEST ," 
		+" TO_CHAR(ADVANCE_PAYMENT_DATE,'DD-MM-YYYY'), ADVANCE_ISSUE_AMT,TO_CHAR(TRAVEL_APP_SETTLE_DATE,'DD-MM-YYYY') , "
		+"	TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(TRAVEL_APP_SETTLE_DATE,'DD-MM-YYYY')  AS PENDDAYS, "
		+"	TRAVEL_APP_ID, TRAVEL_APP_EMPID FROM HRMS_TMS_TRAVEL_APP "
		+"	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID) "
		+"	LEFT JOIN HRMS_TMS_ADVANCE ON (HRMS_TMS_ADVANCE.ADVANCE_TRAPP_ID =  HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID) "
		+"	WHERE TO_DATE(SYSDATE,'DD-MM-YYYY')-TO_DATE(TRAVEL_APP_SETTLE_DATE,'DD-MM-YYYY')  > 0 AND "
		+"	TRAVEL_APP_ID  IN (SELECT   ADVANCE_DFT_DTL_TRAPP_ID  FROM HRMS_TMS_ADVANCE_DFT_DTL) AND  "
		+"	TRAVEL_APP_ID NOT IN (SELECT   EXP_APP_TRAVEL_APPID  FROM  HRMS_TMS_EXP_APP)  "
		+" AND ADVANCE_PAYMENT_DATE BETWEEN TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY')  AND TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') "
        +" AND ( ADVANCE_ISSUE_AMT >0 )";
 
		try {
			 
			Object[][] data = getSqlModel().getSingleResult(sql);
			System.out.println("data.length=========== " + data.length);
			ArrayList advDftList = new ArrayList();
			if (data != null && data.length > 0) {
				System.out.println("In if======== ");
				for (int i = 0; i < data.length; i++) {
					System.out.println("In data[i][0]======== " + data[i][0]);
					TravelAdvanceDefaulter bean1 = new TravelAdvanceDefaulter();
					bean1.setEmpName("" + data[i][0]);
					bean1.setTrRequest("" + data[i][1]);
					bean1.setAdvanceDate("" + data[i][2]);
					bean1.setAdvanceAmt("" + data[i][3]);
					bean1.setExpSettleDate("" + data[i][4]);
					bean1.setPendingDays("" + data[i][5]);
					bean1.setTrAppId("" + data[i][6]);
					bean1.setTrEmpId("" + data[i][7]);  
					bean1.setHidDefaultChk("N"); 
					advDftList.add(bean1); 
				} 
			}
			
			if(!(bean.getAdvHeaderId().equals("")&& bean.getAdvHeaderId().equals("null")))
			{
				callDtlForGo(bean,advDftList);
				if(data!=null && data.length>0)
				{
					 bean.setSelectAllDefaulter("unchecked"); 
				  }else
				{
					bean.setSelectAllDefaulter("checked");
				}
				
			}
			bean.setAdvDftList(advDftList);
			if(bean.getAdvDftList().size()>0) {
				bean.setDispDataFlag("true");
			}else{
				bean.setDispDataFlag("false"); }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		 
	}
	
	public boolean process(TravelAdvanceDefaulter bean,HttpServletRequest request)
	{
		Object [][] addObj = new Object[1][4];
		addObj[0][0]=bean.getFromDate();
		addObj[0][1]=bean.getToDate();
		addObj[0][2]=bean.getSalMonth();
		addObj[0][3]=bean.getSalYear(); 
		
		boolean flag =false;		
	String 	sql = " INSERT INTO HRMS_TMS_ADVANCE_DFT_HDR ( ADVANCE_DFT_HDR_ID, ADVANCE_DFT_HDR_FROM_DATE, ADVANCE_DFT_HDR_TO_DATE, "
            +" ADVANCE_DFT_HDR_SAL_MONTH, ADVANCE_DFT_HDR_SAL_YEAR, ADVANCE_DFT_HDR_SYSDATE) VALUES (" 
            +"(SELECT NVL(MAX(ADVANCE_DFT_HDR_ID),0)+1 FROM HRMS_TMS_ADVANCE_DFT_HDR) ,TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY') ,TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') , "
            +" '"+bean.getSalMonth()+"', '"+bean.getSalYear()+"', TO_DATE(SYSDATE,'DD-MM-YYYY'))";
	flag =getSqlModel().singleExecute(sql);
     
	  if(flag)
	   {
			 String [] trAppId = request.getParameterValues("trAppId");
			 String [] trEmpId = request.getParameterValues("trEmpId");
			 String [] advanceDate = request.getParameterValues("advanceDate");
			 String [] advanceAmt = request.getParameterValues("advanceAmt");
			 String [] expSettleDate = request.getParameterValues("expSettleDate");
			 String [] pendingDays = request.getParameterValues("pendingDays");
			 String [] hidDefaultChk = request.getParameterValues("hidDefaultChk");
			 
			 
		 if(trAppId!=null && trAppId.length>0){ 
			 for(int i=0;i<trAppId.length;i++)
			 { 
				 System.out.println("hidDefaultChk[i]========== "+hidDefaultChk[i]);
				 if(hidDefaultChk[i].equals("Y"))
				 {
			  String sqlDtl =" INSERT INTO HRMS_TMS_ADVANCE_DFT_DTL ( ADVANCE_DFT_DTL_ID, ADVANCE_DFT_DTL_HDR_ID, ADVANCE_DFT_DTL_TRAPP_ID, "
		                  +" ADVANCE_DFT_DTL_EMP_ID, ADVANCE_DFT_DTL_ADV_PAY_DATE, ADVANCE_DFT_DTL_ADV_AMT,ADVANCE_DFT_DTL_EXP_SET_DATE, ADVANCE_DFT_DTL_PEND_DAYS) "
		                  +" VALUES ((SELECT  NVL(MAX(ADVANCE_DFT_DTL_ID),0)+1 FROM HRMS_TMS_ADVANCE_DFT_DTL) ,(SELECT NVL(MAX(ADVANCE_DFT_HDR_ID),0) FROM HRMS_TMS_ADVANCE_DFT_HDR) ," 
		                  +""+trAppId[i]+", "+trEmpId[i]+",TO_DATE('"+advanceDate[i]+"','DD-MM-YYYY') ,"+advanceAmt[i]+",TO_DATE('"+expSettleDate[i]+"','DD-MM-YYYY') ,"+pendingDays[i]+" )";
			  flag= getSqlModel().singleExecute(sqlDtl);
				 }
		  }
		 }
	    }
	  return flag;
	} // end of method
 
	
	public boolean reProcess(TravelAdvanceDefaulter bean,HttpServletRequest request)
	{ 
		Object [][] addObj = new Object[1][5];
		addObj[0][0]=bean.getFromDate();
		addObj[0][1]=bean.getToDate();
		addObj[0][2]=bean.getSalMonth();
		addObj[0][3]=bean.getSalYear();  
		addObj[0][4]=bean.getAdvHeaderId();   
		
		boolean flag =false;		
	String 	sql = " UPDATE HRMS_TMS_ADVANCE_DFT_HDR SET  ADVANCE_DFT_HDR_FROM_DATE =TO_DATE(?,'DD-MM-YYYY'), ADVANCE_DFT_HDR_TO_DATE =TO_DATE(?,'DD-MM-YYYY'), "
            +" ADVANCE_DFT_HDR_SAL_MONTH = ?, ADVANCE_DFT_HDR_SAL_YEAR = ?, ADVANCE_DFT_HDR_SYSDATE =TO_DATE(SYSDATE,'DD-MM-YYYY')  "
            +" WHERE ADVANCE_DFT_HDR_ID = ?";
	flag =getSqlModel().singleExecute(sql,addObj);
     
	  if(flag)
	   {
			 String [] trAppId = request.getParameterValues("trAppId");
			 String [] trEmpId = request.getParameterValues("trEmpId");
			 String [] advanceDate = request.getParameterValues("advanceDate");
			 String [] advanceAmt = request.getParameterValues("advanceAmt");
			 String [] expSettleDate = request.getParameterValues("expSettleDate");
			 String [] pendingDays = request.getParameterValues("pendingDays");
			 String [] hidDefaultChk = request.getParameterValues("hidDefaultChk");
			 
			 String delSql ="DELETE FROM HRMS_TMS_ADVANCE_DFT_DTL WHERE ADVANCE_DFT_DTL_HDR_ID ="+bean.getAdvHeaderId();
			  flag= getSqlModel().singleExecute(delSql);
			  
		 if(trAppId!=null && trAppId.length>0){ 
			 for(int i=0;i<trAppId.length;i++)
			 {   
				 if(hidDefaultChk[i].equals("Y"))
				 {
			  String sqlDtl =" INSERT INTO HRMS_TMS_ADVANCE_DFT_DTL ( ADVANCE_DFT_DTL_ID, ADVANCE_DFT_DTL_HDR_ID, ADVANCE_DFT_DTL_TRAPP_ID, "
		                  +" ADVANCE_DFT_DTL_EMP_ID, ADVANCE_DFT_DTL_ADV_PAY_DATE, ADVANCE_DFT_DTL_ADV_AMT,ADVANCE_DFT_DTL_EXP_SET_DATE, ADVANCE_DFT_DTL_PEND_DAYS) "
		                  +" VALUES ((SELECT  NVL(MAX(ADVANCE_DFT_DTL_ID),0)+1 FROM HRMS_TMS_ADVANCE_DFT_DTL) ,(SELECT NVL(MAX(ADVANCE_DFT_HDR_ID),0) FROM HRMS_TMS_ADVANCE_DFT_HDR) ," 
		                  +""+trAppId[i]+", "+trEmpId[i]+",TO_DATE('"+advanceDate[i]+"','DD-MM-YYYY') ,"+advanceAmt[i]+",TO_DATE('"+expSettleDate[i]+"','DD-MM-YYYY') ,"+pendingDays[i]+" )";
			  flag= getSqlModel().singleExecute(sqlDtl);
				 }
		  }
		 }
	    }
	  return flag;
	} // end of method
 
	
   public void callDtlForGo(TravelAdvanceDefaulter bean,ArrayList advDftList)
   {  
String sql =" SELECT  EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TRAVEL_APP_REQUEST, TO_CHAR(ADVANCE_DFT_DTL_ADV_PAY_DATE,'DD-MM-YYYY'), " 
		+" ADVANCE_DFT_DTL_ADV_AMT,   TO_CHAR(ADVANCE_DFT_DTL_EXP_SET_DATE,'DD-MM-YYYY'), ADVANCE_DFT_DTL_PEND_DAYS  , " 
		+" ADVANCE_DFT_DTL_EMP_ID ,ADVANCE_DFT_DTL_TRAPP_ID FROM HRMS_TMS_ADVANCE_DFT_DTL	"
		+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_ADVANCE_DFT_DTL.ADVANCE_DFT_DTL_EMP_ID) "
		+" LEFT JOIN HRMS_TMS_TRAVEL_APP ON(HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID= ADVANCE_DFT_DTL_TRAPP_ID) "
		+" WHERE ADVANCE_DFT_DTL_HDR_ID ="+bean.getAdvHeaderId(); 
Object [][] data = getSqlModel().getSingleResult(sql);
	if(data != null && data.length >0)
	{
			//ArrayList advDftList = new ArrayList();
			for (int i = 0;i< data.length; i++) { 
				TravelAdvanceDefaulter bean1 = new TravelAdvanceDefaulter();
				bean1.setEmpName("" + data[i][0]);
				bean1.setTrRequest("" + data[i][1]);
				bean1.setAdvanceDate("" + data[i][2]);
				bean1.setAdvanceAmt("" + data[i][3]);
				bean1.setExpSettleDate("" + data[i][4]);
				bean1.setPendingDays("" + data[i][5]);
				bean1.setTrEmpId("" + data[i][6]);
				bean1.setTrAppId("" + data[i][7]);
				bean1.setItDefaultChk("checked");
				bean1.setHidDefaultChk("Y"); 
				advDftList.add(bean1);
			} 
			bean.setSelectAllDefaulter("checked");
			bean.setAdvDftList(advDftList); 
	}
  
  }
   
   public void callDtl(TravelAdvanceDefaulter bean)
   {
		bean.setProcessFlag("true");
String sql =" SELECT  EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TRAVEL_APP_REQUEST, TO_CHAR(ADVANCE_DFT_DTL_ADV_PAY_DATE,'DD-MM-YYYY'), " 
		+" ADVANCE_DFT_DTL_ADV_AMT,   TO_CHAR(ADVANCE_DFT_DTL_EXP_SET_DATE,'DD-MM-YYYY'), ADVANCE_DFT_DTL_PEND_DAYS  , " 
		+" ADVANCE_DFT_DTL_EMP_ID ,ADVANCE_DFT_DTL_TRAPP_ID FROM HRMS_TMS_ADVANCE_DFT_DTL	"
		+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_ADVANCE_DFT_DTL.ADVANCE_DFT_DTL_EMP_ID) "
		+" LEFT JOIN HRMS_TMS_TRAVEL_APP ON(HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID= ADVANCE_DFT_DTL_TRAPP_ID) "
		+" WHERE ADVANCE_DFT_DTL_HDR_ID ="+bean.getAdvHeaderId(); 
Object [][] data = getSqlModel().getSingleResult(sql);
	if(data != null && data.length >0)
	{
			ArrayList advDftList = new ArrayList();
			for (int i = 0;i< data.length; i++) { 
				TravelAdvanceDefaulter bean1 = new TravelAdvanceDefaulter();
				bean1.setEmpName("" + data[i][0]);
				bean1.setTrRequest("" + data[i][1]);
				bean1.setAdvanceDate("" + data[i][2]);
				bean1.setAdvanceAmt("" + data[i][3]);
				bean1.setExpSettleDate("" + data[i][4]);
				bean1.setPendingDays("" + data[i][5]);
				bean1.setTrEmpId("" + data[i][6]);
				bean1.setTrAppId("" + data[i][7]);
				bean1.setItDefaultChk("checked");
				bean1.setHidDefaultChk("Y"); 
				advDftList.add(bean1);
			} 
			bean.setSelectAllDefaulter("checked");
			bean.setAdvDftList(advDftList); 
			if(bean.getAdvDftList().size()>0) {
				bean.setDispDataFlag("true");
			}else{
				bean.setDispDataFlag("false"); }
	}
  
  }
    
}
