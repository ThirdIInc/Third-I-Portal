/**
 * 
 */
package org.paradyne.model.TravelManagement.ExpenseClaim;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.Request;
import org.paradyne.bean.Asset.AssetEmployee;
import org.paradyne.bean.TravelManagement.ExpenseClaim.ExpenseClaimApp;
import org.paradyne.lib.ModelBase;

/**
 * @author AA0651
 *
 */
public class ExpenseClaimAppModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	public void callStatus(ExpenseClaimApp bean, String status,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		
		String query="";
		logger.info("---------------status----------"+status);
		if(!status.equals("N")){
			logger.info("=======Inside callStatus if==========");
		 query="SELECT HRMS_EMP_OFFC.EMP_FNAME || ' ' || EMP_MNAME || ' ' ||EMP_LNAME,  EXP_TRAVEL_REQUEST, TO_CHAR(EXP_APP_APPDATE,'DD-MM-YYYY') ,EXP_APP_EMPID,EXP_APP_TRAVEL_APPID,EXP_APP_ID,EXP_APP_EXPAMT "
			+ " FROM HRMS_TMS_EXP_APP" 
			+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_EXP_APP.EXP_APP_EMPID)"  
			+ " WHERE EXP_APP_STATUS ='"+status+"'"
			+ " AND  EXP_APP_EMPID="+bean.getUserEmpId();
		//Object[][] penQueryData=getSqlModel().getSingleResult(penQuery);
		}else{
			logger.info("=========Inside callStatus else============");
		query="SELECT HRMS_EMP_OFFC.EMP_FNAME || ' ' || EMP_MNAME || ' ' ||EMP_LNAME,  TRAVEL_APP_REQUEST, TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY') ,"
				+ " TRAVEL_APP_EMPID,TRAVEL_APP_ID,0" 
				+ " FROM HRMS_TMS_TRAVEL_APP LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID)  LEFT JOIN HRMS_TMS_EXP_APP ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_EXP_APP.EXP_APP_EMPID) WHERE TRAVEL_APLT_CONF_STATUS ='A'"
				+  " AND TRAVEL_APP_ID NOT IN(SELECT EXP_APP_TRAVEL_APPID FROM HRMS_TMS_EXP_APP WHERE EXP_APP_TRAVEL_APPID IS NOT NULL)"
				+ " AND TO_DATE(TRAVEL_APP_END_DATE,'DD-MM-YYYY')<= TO_DATE(SYSDATE,'DD-MM-YYYY') "
				+ " AND  TRAVEL_APP_EMPID="+bean.getUserEmpId()
				+ " UNION "
				+ " (SELECT HRMS_EMP_OFFC.EMP_FNAME || ' ' || EMP_MNAME || ' ' ||EMP_LNAME,  EXP_TRAVEL_REQUEST, TO_CHAR(EXP_APP_APPDATE,'DD-MM-YYYY') ,"
				+ " EXP_APP_EMPID,EXP_APP_TRAVEL_APPID ,EXP_APP_ID"
				+ " FROM HRMS_TMS_EXP_APP   LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_EXP_APP.EXP_APP_EMPID)  "
				+ " WHERE EXP_APP_STATUS ='N'  AND EXP_APP_EMPID="+bean.getUserEmpId()+")";
			
			
		}
		Object[][] queryData=getSqlModel().getSingleResult(query);
		//Object[][] totalData=new Object[queryData.length+penQueryData.length][5];
		
		
		doPaging(bean, queryData.length, new Object[][]{}, request);
		int fromTotRec = Integer.parseInt(bean.getFromTotRec());
		int toTotRec = Integer.parseInt(bean.getToTotRec());
		ArrayList<Object> obj=new ArrayList<Object>();
		
		if(queryData!=null && queryData.length>0 ){
		for(int i = fromTotRec; i < toTotRec; i++)
		{	
			ExpenseClaimApp bean1=new ExpenseClaimApp();
			bean1.setEmpName(checkNull(String.valueOf(queryData[i][0])));
			bean1.setReqName(checkNull(String.valueOf(queryData[i][1])));
			bean1.setTrvDate(checkNull(String.valueOf(queryData[i][2])));
			bean1.setEmpId(checkNull(String.valueOf(queryData[i][3])));
			bean1.setTravelAppId(checkNull(String.valueOf(queryData[i][4])));	
			logger.info("==========TravelAppId============"+bean.getTravelAppId());
			logger.info("==========ExAppId============"+bean.getExAppId());
			bean1.setItExpId(checkNull(String.valueOf(queryData[i][5])));
			if(status.equals("A") || status.equals("R")){
			bean1.setExpAmount(checkNull(String.valueOf(queryData[i][6])));
			}
			
			if (status.equals("N")) {
				bean1.setNewReqFlag(true);
			} else if (status.equals("P")) {
				bean1.setPenFlag(true);
			} else if (status.equals("A")) {
				bean1.setAppFlag(true);
			} else if(status.equals("R")){
				bean1.setRejFlag(true);
			}else{
				bean1.setNewReqFlag(true);
			}
			obj.add(bean1);
		}
		}
		
		//For status P,A,R
		/*if(penQueryData!=null && penQueryData.length>0 && !status.equals("N")){
			for(int i =0;i<penQueryData.length;i++)
			{	
				ExpenseClaimApp bean1=new ExpenseClaimApp();
				bean1.setEmpName(checkNull(String.valueOf(penQueryData[i][0])));
				bean1.setReqName(checkNull(String.valueOf(penQueryData[i][1])));
				bean1.setTrvDate(checkNull(String.valueOf(penQueryData[i][2])));
				bean1.setEmpId(checkNull(String.valueOf(penQueryData[i][3])));
				bean1.setTravelAppId(checkNull(String.valueOf(penQueryData[i][4])));
				logger.info("==========TravelAppId============"+bean.getTravelAppId());
				bean1.setExAppId(checkNull(String.valueOf(penQueryData[i][5])));
				if (status.equals("N")) {
					bean1.setNewReqFlag(true);
				} else if (status.equals("P")) {
					bean1.setPenFlag(true);
				} else if (status.equals("A")) {
					bean1.setAppFlag(true);
				} else if(status.equals("R")){
					bean1.setRejFlag(true);
				}else{
					bean1.setNewReqFlag(true);
				}
				obj.add(bean1);
			}
			}*/
		bean.setTravelList(obj);		
		 if(obj.size()==0)
		 {
			  bean.setListLength("0");
			  bean.setNoData("true"); 
		 }else
		 {
			 bean.setListLength("1"); 
			 bean.setNoData("false"); 
		 }
		
		
	}
	
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}




public void doPaging(ExpenseClaimApp bean, int empLength, Object[][] attnObj, HttpServletRequest request)
{
	try
	{
		/*
		 * totalRec -: No. of records per page
		 * fromTotRec -: Starting No. of record to show on a current page
		 * toTotRec -: Last No. of record to show on a current page
		 * pageNo -: Current page to show
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

		java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal((double)empLength / totalRec);
		totalPage = Integer.parseInt(bigDecRow1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
		
		if(String.valueOf(bean.getHdPage()).equals("null")||String.valueOf(bean.getHdPage()).equals(null)||String.valueOf(bean.getHdPage()).equals(""))
		{
			pageNo = 1;
			fromTotRec = 0;
			toTotRec = totalRec;

			if(toTotRec > empLength)
			{
				toTotRec = empLength;
			}
			bean.setHdPage("1");
		}
		else
		{   	pageNo = Integer.parseInt(bean.getHdPage());
					  
				if(pageNo == 1)
				{
					fromTotRec = 0;
					toTotRec = totalRec;
				}
				else
				{
					toTotRec = toTotRec * pageNo;
					fromTotRec = toTotRec - totalRec;
				}
				if(toTotRec > empLength)
				{
					toTotRec = empLength;
				}
							
		}
		
		bean.setFromTotRec(String.valueOf(fromTotRec));
		bean.setToTotRec(String.valueOf(toTotRec));
		
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("fromTotRec", fromTotRec);
		request.setAttribute("toTotRec", toTotRec);
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
}



public void setApplication(ExpenseClaimApp bean ,String travelAppId)
{
   try
   { 
	   bean.setTravelAppId(travelAppId); 
	   String sql="SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
			+ " DEPT_NAME,RANK_NAME,TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY') ,TRAVEL_APLT_CONF_STATUS "
			+ " ,CADRE_NAME , CENTER_NAME ,EMP_CADRE,TRAVEL_APP_EMPID,HRMS_BANK.BANK_NAME,"
			+ " HRMS_SALARY_MISC.SAL_ACCNO_REGULAR ,SAL_MICR_REGULAR "  			
			+ " FROM HRMS_TMS_TRAVEL_APP "
			+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID ) " 				
			+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "  
			+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) " 
			+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "  
			+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)  "
			+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_SALARY_MISC.EMP_ID ) " 
			+ " LEFT JOIN HRMS_BANK ON(HRMS_SALARY_MISC.SAL_MICR_REGULAR = HRMS_BANK.BANK_MICR_CODE ) " 		
			+ " WHERE TRAVEL_APP_ID="+bean.getTravelAppId();
		
		Object [][] result = getSqlModel().getSingleResult(sql);
		if(result.length>0 && result!=null)
		{
		bean.setEmpToken(checkNull(String.valueOf(result[0][0])));
		bean.setEmployeeName(checkNull(String.valueOf(result[0][1])));
		bean.setDeptName(checkNull(String.valueOf(result[0][2])));
		bean.setDesgName(checkNull(String.valueOf(result[0][3])));
		bean.setApplDate(checkNull(String.valueOf(result[0][4])));
		bean.setStatusFld("New Requisition");
		bean.setHidSts("N");
		bean.setGrdName(checkNull(String.valueOf(result[0][6])));
		bean.setBranchName(checkNull(String.valueOf(result[0][7]))); 
		bean.setGrdId(checkNull(String.valueOf(result[0][8])));
		bean.setEmployeeId(checkNull(String.valueOf(result[0][9])));
		bean.setBankName(checkNull(String.valueOf(result[0][10])));
		bean.setAccNo(checkNull(String.valueOf(result[0][11])));
		bean.setTrvAppIdDtl(checkNull(bean.getTravelAppId()));
		logger.info("+++++++++++++++++++TrvAppId+++++++++++++++"+bean.getTrvAppIdDtl());
		logger.info("+++++++++++++++++++TrvAppId+++++++++++++++"+bean.getStatusFld());
		
		}  // end of if
   }catch (Exception e) {
	logger.error(e.getMessage());
} 
	 
}  // end of setApplication


public void getPolicy(ExpenseClaimApp bean, String travelAppId) {
	// TODO Auto-generated method stub
	logger.info("=======Inside getPolicy====bean======"+bean.getTrvAppIdDtl());
	/*String polQuery="SELECT POLICY_ID  FROM HRMS_TMS_TRAVEL_POLICY "
		+ " LEFT JOIN HRMS_TMS_POLICY_GRADE ON(HRMS_TMS_POLICY_GRADE.PG_POLICY_ID=HRMS_TMS_TRAVEL_POLICY.POLICY_ID)" 
		+ " WHERE PG_EMP_GRADE ="+bean.getGrdId()
		+ " AND (TO_DATE(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY')<= TO_DATE(SYSDATE,'DD-MM-YYYY')) ORDER BY POLICY_EFFECTIVE_DATE DESC ";*/
	String polQuery="select TRAVEL_APP_POLICY_ID from HRMS_TMS_TRAVEL_APP where TRAVEL_APP_ID="+bean.getTravelAppId();
	Object[][] polQueryData=getSqlModel().getSingleResult(polQuery);
	if(polQueryData!=null && polQueryData.length>0){
	logger.info("--------polQueryData----------"+String.valueOf(polQueryData[0][0]));	
	bean.setPolicyId(checkNull(String.valueOf(polQueryData[0][0])));
	}
	String expQuery="SELECT EXP_APP_ID FROM HRMS_TMS_EXP_APP WHERE EXP_APP_TRAVEL_APPID="+bean.getTravelAppId();
	Object[][] expQueryData=getSqlModel().getSingleResult(expQuery);
	
	if (expQueryData!=null && expQueryData.length>0 ) 
		
	{
		String query="SELECT EXP_APP_ID,CASE WHEN EXP_APP_STATUS='N'THEN 'New Requisition' WHEN  EXP_APP_STATUS='P'"
			+" THEN 'Pending' WHEN EXP_APP_STATUS='A' THEN 'Approved' WHEN EXP_APP_STATUS='R' THEN 'Rejected' ELSE 'New Requisition' END,EXP_APP_STATUS,"
			+ " EXP_APP_PREF_PAYMODE, EXP_APP_COMMENT, EXP_APP_ADVANCE_AMT,"
			+ " EXP_APP_SAL_MON, EXP_APP_SAL_YEAR, EXP_TRAVEL_REQUEST,EXP_APP_EXPAMT,DECODE(EXP_APP_PREF_PAYMODE,'C','Cash','S','Salary','Q','Cheque','T','Transfer'),"
			+ " DECODE(EXP_APP_SAL_MON,'1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September' ,'10','October','11','November','12','December') FROM HRMS_TMS_EXP_APP"
			+ " WHERE EXP_APP_TRAVEL_APPID="+bean.getTravelAppId();
		Object[][] queryData=getSqlModel().getSingleResult(query);
		if(queryData.length>0 && queryData!=null)
		{
		bean.setExAppId(checkNull(String.valueOf(queryData[0][0])));
		bean.setStatusFld(checkNull(String.valueOf(queryData[0][1])));
		bean.setHidSts(checkNull(String.valueOf(queryData[0][2])));
		bean.setMode(checkNull(String.valueOf(queryData[0][3])));
		bean.setComment(checkNull(String.valueOf(queryData[0][4])));
		bean.setAdvAmt(checkNull(String.valueOf(queryData[0][5])));
		logger.info("==========month====1==="+queryData[0][6]);
		bean.setSalMonth(checkNull(String.valueOf(queryData[0][6])).trim());
		logger.info("==========month====2==="+bean.getSalMonth());
		bean.setYear(checkNull(String.valueOf(queryData[0][7])));
		bean.setRequestName(checkNull(String.valueOf(queryData[0][8])));
		bean.setExpAmt(checkNull(String.valueOf(queryData[0][9])));
		bean.setHidMode(checkNull(String.valueOf(queryData[0][10])));//added on 08-04-09
		bean.setHidMonth(checkNull(String.valueOf(queryData[0][11])).trim());//added on 08-04-09
		if(bean.getExpAmt().equals("0")){
			 bean.setExpLength("false");
		}else{
			bean.setExpLength("true");
		}
		}
	}else{
			String queryClaim="SELECT TRAVEL_APP_REQUEST,ADVANCE_ISSUE_AMT FROM HRMS_TMS_TRAVEL_APP"  
				+ " LEFT JOIN HRMS_TMS_ADVANCE ON(HRMS_TMS_ADVANCE.ADVANCE_TRAPP_ID=HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID)" 
				+ " WHERE TRAVEL_APLT_CONF_STATUS ='A'"
				+ " AND  TRAVEL_APP_ID ="+bean.getTravelAppId();
			Object[][] queryClaimData=getSqlModel().getSingleResult(queryClaim);
			if(queryClaimData!=null && queryClaimData.length>0){
			bean.setRequestName(checkNull(String.valueOf(queryClaimData[0][0])));
			bean.setAdvAmt(checkNull(String.valueOf(queryClaimData[0][1])));
			}
			//bean.setStatusFld("N");	
		}
		
	}
	






public void addExpenseDtl(ExpenseClaimApp exClaim, HttpServletRequest request) {
	// TODO Auto-generated method stub
	String[] voucherHead = request.getParameterValues("itVoucher");
	String[] voucherAmt = request.getParameterValues("itvoucherAmt");
	String[] particulars = request.getParameterValues("itParticulars");
	String[] ExpenseDate = request.getParameterValues("itExpenseDate");
	String[] amount = request.getParameterValues("itAmount");
	String[] itValAmount = request.getParameterValues("itValAmount");
	String[] uploadFileName = request.getParameterValues("itUpload");
	String[] itVoucherHeadCode = request.getParameterValues("itVoucherHeadCode");
	String[] isProof = request.getParameterValues("isProof");
	ArrayList list = new ArrayList();
	double totalValidAmt=0.0;
	double totalValidAmt_Valid=0.0;
	double validAmt=Double.parseDouble(exClaim.getAmount());//valid
	double maxValidAmt=Double.parseDouble(exClaim.getVoucherAmt());//max
	double totalAmount=0.0;
	
	
	
	if(itValAmount != null && itValAmount.length > 0 )
	{
		for(int i=0;i<itValAmount.length;i++)
		{
			if(itVoucherHeadCode[i].equals(""+exClaim.getVoucherHeadCode())){
				totalValidAmt+=Double.parseDouble(itValAmount[i]);
			}
			
			totalAmount+=Double.parseDouble(amount[i]);
			logger.info("===========totalAmount=====1===="+totalAmount);
			
		}
		
		logger.info("===========totalAmount=====2===="+totalAmount);
		totalValidAmt_Valid=totalValidAmt+validAmt;
		
		if(totalValidAmt_Valid>maxValidAmt){
			validAmt=maxValidAmt-totalValidAmt;
		}
	}
	totalAmount=totalAmount+Double.parseDouble(exClaim.getAmount());
	
	
	if(voucherHead != null && voucherHead.length > 0)
	{
		for(int i=0;i<voucherHead.length;i++)
		{
			ExpenseClaimApp bean = new ExpenseClaimApp();
			bean.setItVoucher(voucherHead[i]);
			bean.setItvoucherAmt(voucherAmt[i]);
			bean.setItParticulars(particulars[i]);
			bean.setItExpenseDate(ExpenseDate[i]);
			bean.setItAmount(amount[i]);	
			bean.setItValAmount(itValAmount[i]);
			logger.info("----------valid amount--------"+itValAmount[i]);
			bean.setItUpload(uploadFileName[i]);
			bean.setItVoucherHeadCode(itVoucherHeadCode[i]);
			bean.setIsProof(isProof[i]);
			list.add(bean);
			}
	}
	ExpenseClaimApp bean = new ExpenseClaimApp();
	
		bean.setItVoucher(checkNull(String.valueOf(exClaim.getVoucherHead())));
		bean.setItvoucherAmt(checkNull(String.valueOf(exClaim.getVoucherAmt())));
		bean.setItParticulars(checkNull(String.valueOf(exClaim.getParticulars())));
		bean.setItExpenseDate(checkNull(String.valueOf(exClaim.getExpenseDate())));
		bean.setItAmount(checkNull(String.valueOf(exClaim.getAmount())));
		//bean.setItValAmount(checkNull(String.valueOf(validAmt)));	
		if( voucherHead == null ){
			if(validAmt>maxValidAmt){
			bean.setItValAmount(checkNull(String.valueOf(maxValidAmt)));	
		
		}else{
			bean.setItValAmount(checkNull(String.valueOf(validAmt)));	
		}
		}else{
			bean.setItValAmount(checkNull(String.valueOf(validAmt)));	
		}
		bean.setItUpload(checkNull(String.valueOf(exClaim.getUploadFileName())));	
		bean.setItVoucherHeadCode(checkNull(String.valueOf(exClaim.getVoucherHeadCode())));	
		bean.setIsProof(checkNull(String.valueOf(exClaim.getProof())));
		exClaim.setExpAmt(checkNull(String.valueOf(totalAmount)));		
		list.add(bean);
		exClaim.setExpList(list);
		 if(list.size()==0){
			 bean.setExpListLength("0"); 
			 exClaim.setExpLength("false");
		 }else{
			 bean.setExpListLength("1"); 
			 exClaim.setExpLength("true");
		 }
		 if(Double.parseDouble(exClaim.getExpAmt())>maxValidAmt){
			 exClaim.setMaxLimit("true");
		 }else{
			 exClaim.setMaxLimit("false");
		 }
	
}



public boolean delExp(ExpenseClaimApp exClaim, String[] code,
		String[] itVoucher, String[] itParticulars, String[] itExpenseDate,
		String[] itAmount, String[] itValAmount, String[] itVoucherHeadCode) {
	// TODO Auto-generated method stub
	
	try{
		ArrayList<Object> list=new ArrayList<Object>();		
		double totalAmount=0.0;
		double totalAmount_valid=0.0;
		if(itVoucher!=null)
		{   
			for(int i=0;i<itVoucher.length;i++){
				ExpenseClaimApp bean=new ExpenseClaimApp();
				if((String.valueOf(code[i]).equals(""))){
					bean.setItVoucher(itVoucher[i]);
					bean.setItParticulars(itParticulars[i]);
					bean.setItExpenseDate(itExpenseDate[i]);
					bean.setItAmount(itAmount[i]);
					bean.setItValAmount(itValAmount[i]);
					bean.setItVoucherHeadCode(itVoucherHeadCode[i]);
					list.add(bean);
					totalAmount_valid+=Double.parseDouble(itValAmount[i]);
					
				}
				
				}
		}
		totalAmount=totalAmount+totalAmount_valid;		
		exClaim.setExpList(list);
		exClaim.setExpAmt(checkNull(String.valueOf(totalAmount)));
		/*double validAmt=Double.parseDouble(exClaim.getAmount());//valid
		double totalAmount=0.0;
		
		if(itValAmount != null && itValAmount.length > 0 )
		{
			for(int i=0;i<itValAmount.length;i++)
			{				
				totalAmount+=Double.parseDouble(itAmount[i]);
				logger.info("===========totalAmount=====1===="+totalAmount);
				
			}
		}
		totalAmount=totalAmount+validAmt;
		exClaim.setExpAmt(checkNull(String.valueOf(totalAmount)));		*/
		
		logger.info("----delExp------------"+exClaim.getExAppId());
		}		
	
		catch (Exception e) {
			e.printStackTrace();
			
			// TODO: handle exception
		}
		return true;
}


public boolean saveForSendApproval(ExpenseClaimApp bean,HttpServletRequest request, Object[][] empFlow) {
	// TODO Auto-generated method stub
	 
	boolean res=false;
	boolean result=false;
	String[] itVoucher = request.getParameterValues("itVoucher");
	String[] itVoucherHeadCode = request.getParameterValues("itVoucherHeadCode");
	String[] itParticulars = request.getParameterValues("itParticulars");	
	String[] itAmount = request.getParameterValues("itAmount");
	String[] itUpload = request.getParameterValues("itUpload");	
	String[] isProof = request.getParameterValues("isProof");
	String[] itExpenseDate = request.getParameterValues("itExpenseDate");
	String[] itValAmount = request.getParameterValues("itValAmount");
	
	Object hdrObj[][] = new Object[1][17];
	Object dtlObj[][] = new Object[1][8];	
	hdrObj[0][0]=bean.getEmployeeId();
	hdrObj[0][1]=bean.getApplDate();
	hdrObj[0][2]="P";
	hdrObj[0][3]=bean.getExpAmt();
	hdrObj[0][4]=bean.getMode();
	hdrObj[0][5]=bean.getComment();
	hdrObj[0][6]="1";	 
	hdrObj[0][7]=String.valueOf(empFlow[0][0]);
	hdrObj[0][8]=String.valueOf(empFlow[0][3]);
	hdrObj[0][9]=bean.getTrvAppIdDtl();
	hdrObj[0][10]=bean.getAdvAmt();	
	if(bean.getMode().equals("T")){
	hdrObj[0][11]=bean.getBankName();
	hdrObj[0][12]=bean.getAccNo();
	}else{
		hdrObj[0][11]="";
		hdrObj[0][12]="";
	}
	if(bean.getMode().equals("S")){
	hdrObj[0][13]=bean.getSalMonth();
	hdrObj[0][14]=bean.getYear();
	}else{
		hdrObj[0][13]="";
		hdrObj[0][14]="";
	}	
	hdrObj[0][15]=bean.getRequestName();
	hdrObj[0][16]=bean.getPolicyId(); 
	
	res = getSqlModel().singleExecute(getQuery(8), hdrObj);		
	
	if(res){
		String query="SELECT MAX(EXP_APP_ID) FROM HRMS_TMS_EXP_APP";
		Object[][] data = getSqlModel().getSingleResult(query);
		bean.setExAppId(String.valueOf(data[0][0]));
		
		for (int i = 0; i < itVoucher.length; i++) {
			dtlObj[0][0]=data[0][0];						
			dtlObj[0][1]=String.valueOf(isProof[i]);
			dtlObj[0][2]=String.valueOf(itParticulars[i]);
			dtlObj[0][3]=String.valueOf(itUpload[i]);
			dtlObj[0][4]=String.valueOf(itAmount[i]);
			dtlObj[0][5]=String.valueOf(itVoucherHeadCode[i]);
			dtlObj[0][6]=String.valueOf(itExpenseDate[i]);			
			dtlObj[0][7]=String.valueOf(itValAmount[i]);		
			
			result=getSqlModel().singleExecute(getQuery(2), dtlObj);	
		}
	}		
	return result;
	}

public boolean saveHdr(ExpenseClaimApp bean,HttpServletRequest request) {
	// TODO Auto-generated method stub
	 
	boolean res=false;
	boolean result=false;
	String[] itVoucher = request.getParameterValues("itVoucher");
	String[] itVoucherHeadCode = request.getParameterValues("itVoucherHeadCode");
	String[] itParticulars = request.getParameterValues("itParticulars");	
	String[] itAmount = request.getParameterValues("itAmount");
	String[] itUpload = request.getParameterValues("itUpload");	
	String[] isProof = request.getParameterValues("isProof");
	String[] itExpenseDate = request.getParameterValues("itExpenseDate");
	String[] itValAmount = request.getParameterValues("itValAmount");
	
	Object hdrObj[][] = new Object[1][14];
	Object dtlObj[][] = new Object[1][8];	
	hdrObj[0][0]=bean.getEmployeeId();
	hdrObj[0][1]=bean.getApplDate();
	hdrObj[0][2]="N";
	hdrObj[0][3]=bean.getExpAmt();
	hdrObj[0][4]=bean.getMode();
	hdrObj[0][5]=bean.getComment();
	//hdrObj[0][6]="1";	 
	//hdrObj[0][7]="";
	//hdrObj[0][8]="";
	logger.info("+++++++++++save+++++++++getTrvAppIdDtl+++++"+bean.getTrvAppIdDtl());
	hdrObj[0][6]=bean.getTrvAppIdDtl();
	hdrObj[0][7]=bean.getAdvAmt();	
	if(bean.getMode().equals("T")){
	hdrObj[0][8]=bean.getBankName();
	hdrObj[0][9]=bean.getAccNo();
	}else{
		hdrObj[0][8]="";
		hdrObj[0][9]="";
	}
	if(bean.getMode().equals("S")){
	hdrObj[0][10]=bean.getSalMonth();
	logger.info("=======bean.getMonth()========="+bean.getSalMonth());
	hdrObj[0][11]=bean.getYear();
	}else{
		hdrObj[0][10]="";
		hdrObj[0][11]="";
	}	
	hdrObj[0][12]=bean.getRequestName();
	hdrObj[0][13]=bean.getPolicyId().equals("")?"0":bean.getPolicyId();
	//bean.setMonth(String.valueOf(hdrObj[0][13]));
	
	res = getSqlModel().singleExecute(getQuery(1), hdrObj);		
	
	if(res){
		String query="SELECT MAX(EXP_APP_ID) FROM HRMS_TMS_EXP_APP";
		Object[][] data = getSqlModel().getSingleResult(query);
		bean.setExAppId(String.valueOf(data[0][0]));
		//String appQuery="SELECT EXP_APP_TRAVEL_APPID FROM HRMS_TMS_EXP_APP WHERE EXP_APP_ID="+bean.getExAppId();
		//Object[][] appQueryData = getSqlModel().getSingleResult(appQuery);
		//bean.setTrvAppIdDtl(String.valueOf(appQueryData[0][0]));
		for (int i = 0; i < itVoucher.length; i++) {
			dtlObj[0][0]=data[0][0];						
			dtlObj[0][1]=String.valueOf(isProof[i]);
			dtlObj[0][2]=String.valueOf(itParticulars[i]);
			dtlObj[0][3]=String.valueOf(itUpload[i]);
			dtlObj[0][4]=String.valueOf(itAmount[i]);
			dtlObj[0][5]=String.valueOf(itVoucherHeadCode[i]);
			dtlObj[0][6]=String.valueOf(itExpenseDate[i]);			
			dtlObj[0][7]=String.valueOf(itValAmount[i]);		
			
			result=getSqlModel().singleExecute(getQuery(2), dtlObj);	
		}
		
	}		
	return result;
	}

	
public void getEmployeeDetails(String empCd, ExpenseClaimApp bean) {
	// TODO Auto-generated method stub
	 try
	   { 		  
		 String nowQuery="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] nowQueryData=getSqlModel().getSingleResult(nowQuery);
		
		 String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
			 +"	CENTER_NAME,DEPT_NAME,RANK_NAME,EMP_ID ,CADRE_NAME,EMP_CADRE  FROM HRMS_EMP_OFFC "
			 +"	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
			 +"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
			 +"	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
			 +"	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
			 +" LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) " 
			 +" WHERE EMP_ID = "+empCd;
				Object[][] empdata = getSqlModel().getSingleResult(query);
				bean.setEmpToken(String.valueOf(empdata[0][0]));
				bean.setEmployeeName(String.valueOf(empdata[0][1]));
				bean.setBranchName(String.valueOf(empdata[0][2]));
				bean.setDeptName(String.valueOf(empdata[0][3]));
				bean.setDesgName(String.valueOf(empdata[0][4]));
				bean.setEmployeeId(empCd);
				bean.setGrdName(String.valueOf(empdata[0][6]));
				bean.setGrdId(String.valueOf(empdata[0][7]));
				bean.setApplDate(String.valueOf(nowQueryData[0][0]));
				bean.setStatusFld("New Requisition");
				//bean.setContactNo(String.valueOf(empdata[0][8]));
				//bean.setEmpId(empId);			
			} 
	   catch (Exception e) {
		logger.error(e.getMessage());
	} 
	
}


public void getPolicyHdr(String empCd, ExpenseClaimApp exClaim) {
	// TODO Auto-generated method stub
	logger.info("=======Inside getPolicyHdr=========="+exClaim.getTravelAppId());
	
	String polQuery="SELECT POLICY_ID  FROM HRMS_TMS_TRAVEL_POLICY "
		+ " LEFT JOIN HRMS_TMS_POLICY_GRADE ON(HRMS_TMS_POLICY_GRADE.PG_POLICY_ID=HRMS_TMS_TRAVEL_POLICY.POLICY_ID)" 
		+ " WHERE PG_EMP_GRADE ="+exClaim.getGrdId()
		+ " AND (TO_DATE(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY')<= TO_DATE(SYSDATE,'DD-MM-YYYY')) ORDER BY POLICY_EFFECTIVE_DATE DESC ";
	
	Object[][] polQueryData=getSqlModel().getSingleResult(polQuery);
	
	if(polQueryData!=null && polQueryData.length>0){
		logger.info("====================================="+String.valueOf(polQueryData[0][0]));
	exClaim.setPolicyId(checkNull(String.valueOf(polQueryData[0][0])));
	}
	 String nowQuery="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object[][] nowQueryData=getSqlModel().getSingleResult(nowQuery);
		exClaim.setApplDate(String.valueOf(nowQueryData[0][0]));
}

public void getPolicy(String empCd, ExpenseClaimApp exClaim) {
	// TODO Auto-generated method stub
	logger.info("=======Inside getPolicy=========="+exClaim.getTrvAppIdDtl());
	
	/*String polQuery="SELECT POLICY_ID  FROM HRMS_TMS_TRAVEL_POLICY "
		+ " LEFT JOIN HRMS_TMS_POLICY_GRADE ON(HRMS_TMS_POLICY_GRADE.PG_POLICY_ID=HRMS_TMS_TRAVEL_POLICY.POLICY_ID)" 
		+ " WHERE PG_EMP_GRADE ="+exClaim.getGrdId()
		+ " AND (TO_DATE(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY')<= TO_DATE(SYSDATE,'DD-MM-YYYY')) ORDER BY POLICY_EFFECTIVE_DATE DESC ";
	*/
	String polQuery="select TRAVEL_APP_POLICY_ID from HRMS_TMS_TRAVEL_APP where TRAVEL_APP_ID="+exClaim.getTrvAppIdDtl();
	Object[][] polQueryData=getSqlModel().getSingleResult(polQuery);
	
	if(polQueryData!=null && polQueryData.length>0){
		logger.info("====================================="+String.valueOf(polQueryData[0][0]));
	exClaim.setPolicyId(checkNull(String.valueOf(polQueryData[0][0])));
	}
	 
}


public void getEmpDtl(ExpenseClaimApp bean) {
	// TODO Auto-generated method stub
	 try
	   { 		  
		 String nowQuery="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] nowQueryData=getSqlModel().getSingleResult(nowQuery);
		
		 String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
			 +"	CENTER_NAME,DEPT_NAME,RANK_NAME,EMP_ID ,CADRE_NAME,EMP_CADRE  FROM HRMS_EMP_OFFC "
			 +"	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
			 +"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
			 +"	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
			 +"	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
			 +" LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) " 
			 +" WHERE EMP_ID = "+bean.getEmployeeId();
				Object[][] empdata = getSqlModel().getSingleResult(query);
				bean.setEmpToken(String.valueOf(empdata[0][0]));
				bean.setEmployeeName(String.valueOf(empdata[0][1]));
				bean.setBranchName(String.valueOf(empdata[0][2]));
				bean.setDeptName(String.valueOf(empdata[0][3]));
				bean.setDesgName(String.valueOf(empdata[0][4]));
				bean.setEmployeeId(String.valueOf(bean.getEmployeeId()));
				bean.setGrdName(String.valueOf(empdata[0][6]));
				bean.setGrdId(String.valueOf(empdata[0][7]));
				bean.setApplDate(String.valueOf(nowQueryData[0][0]));
				bean.setStatusFld("A");
				//bean.setContactNo(String.valueOf(empdata[0][8]));
				//bean.setEmpId(empId);			
			} 
	   catch (Exception e) {
		logger.error(e.getMessage());
	} 
	
}
public void getDetails(ExpenseClaimApp bean) {
	
	String query="SELECT nvl(HRMS_EMP_OFFC.EMP_FNAME || ' ' || EMP_MNAME || ' ' ||EMP_LNAME,' '),nvl(EXP_APP_STATUS,' '),"
		+ "CASE WHEN EXP_APP_STATUS='N' THEN 'New Requisition' WHEN  EXP_APP_STATUS='P' THEN 'Pending' WHEN EXP_APP_STATUS='A'"
		+ " THEN 'Approved' WHEN EXP_APP_STATUS='R' THEN 'Rejected' ELSE 'New Requisition' END ,"
		+ " nvl(EXP_APP_EMPID,0), TO_CHAR(EXP_APP_APPDATE,'DD-MM-YYYY'), "
		+ " nvl(EXP_APP_EXPAMT,0), nvl(EXP_APP_PREF_PAYMODE,' '), nvl(EXP_APP_COMMENT,' '),"
		+ " nvl(EXP_APP_TRAVEL_APPID,0), nvl(EXP_APP_ADVANCE_AMT,0), nvl(EXP_APP_BANK_NAME,' '), nvl(EXP_APP_ACCOUNT_NO,' '),"
		+ " nvl(EXP_APP_SAL_MON,'0'),"
		+ " nvl(EXP_APP_SAL_YEAR,' '), nvl(EXP_TRAVEL_REQUEST,' '),EXP_APP_ID,nvl(DEPT_NAME,' '),nvl(RANK_NAME,' '),nvl(EMP_TOKEN,' '),nvl(CADRE_NAME,' '),CADRE_ID,nvl(CENTER_NAME,'') "
		+ " ,DECODE(EXP_APP_PREF_PAYMODE,'C','Cash','S','Salary','Q','Cheque','T','Transfer'),"
		+ " DECODE(EXP_APP_SAL_MON,'1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September' ,'10','October','11','November','12','December')"
		+ " FROM HRMS_TMS_EXP_APP"				
		+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_EXP_APP.EXP_APP_EMPID)"
		+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )"   
		+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )"  
		+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER )"
		+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)" 
		+ " WHERE EXP_APP_ID="+bean.getExAppId();
	Object[][] empdata = getSqlModel().getSingleResult(query);
	bean.setEmployeeName(String.valueOf(empdata[0][0]));
	bean.setHidSts(String.valueOf(empdata[0][1]));
	bean.setStatusFld(String.valueOf(empdata[0][2]));
	bean.setEmployeeId(String.valueOf(empdata[0][3]));
	bean.setApplDate(String.valueOf(empdata[0][4]));
	bean.setExpAmt(String.valueOf(empdata[0][5]));
	bean.setMode(String.valueOf(empdata[0][6]));
	bean.setComment(String.valueOf(empdata[0][7]));
	bean.setTrvAppIdDtl(String.valueOf(empdata[0][8]));
	
	bean.setAdvAmt(String.valueOf(empdata[0][9]));
	bean.setBankName(String.valueOf(empdata[0][10]));
	bean.setAccNo(String.valueOf(empdata[0][11]));
	bean.setSalMonth(String.valueOf(empdata[0][12]).trim());
	logger.info("=============bean.getMonth()============="+bean.getSalMonth());
	bean.setYear(String.valueOf(empdata[0][13]));
	bean.setRequestName(String.valueOf(empdata[0][14]));
	bean.setExAppId(String.valueOf(empdata[0][15]));
	bean.setDeptName(String.valueOf(empdata[0][16]));
	
	bean.setDesgName(String.valueOf(empdata[0][17]));
	bean.setEmpToken(String.valueOf(empdata[0][18]));
	bean.setGrdName(String.valueOf(empdata[0][19]));
	bean.setGrdId(String.valueOf(empdata[0][20]));
	bean.setBranchName(String.valueOf(empdata[0][21]));
	bean.setHidMode(String.valueOf(empdata[0][22]));
	bean.setHidMonth(String.valueOf(empdata[0][23]).trim());
	//bean.setApplDate(String.valueOf(nowQueryData[0][0]));
	//bean.setStatusFld("A");
	
}



public void getExpense(ExpenseClaimApp exClaim) {
	logger.info("Inside getExpense method-------------"+exClaim.getPolicyId());
	// TODO Auto-generated method stub
	String poQlquery="SELECT  HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_NAME ,PE_ID,PE_EXP_CAT_VALUE"
		+ " FROM HRMS_TMS_POLICY_EXPENSE"
		+ " LEFT JOIN HRMS_TMS_EXP_CATEGORY ON(HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID=HRMS_TMS_POLICY_EXPENSE.PE_EXP_CAT_ID)" 
		+ " LEFT JOIN HRMS_TMS_TRAVEL_POLICY  ON(HRMS_TMS_TRAVEL_POLICY.POLICY_ID=HRMS_TMS_POLICY_EXPENSE.PE_POLICY_ID) "
		+ " WHERE POLICY_ID ="+exClaim.getPolicyId();
	Object[][] querydata = getSqlModel().getSingleResult(poQlquery);
	/*
	String query="SELECT MAX(EXP_APP_ID) FROM HRMS_TMS_EXP_APP";
	Object[][] data = getSqlModel().getSingleResult(query);
	exClaim.setExpHdr(String.valueOf(data[0][0]));*/
	String queryDtl="SELECT EXP_DTL_VCH_HEAD, EXP_DTL_VCH_AMT, EXP_DTL_ISPROOF, EXP_DTL_PARTICULAR,"
		+ " EXP_DTL_UPLOAD_FILENAME,TO_CHAR(EXP_DTL_EXP_DATE,'DD-MM-YYYY'), EXP_DTL_VALID_AMT FROM HRMS_TMS_EXP_DTL WHERE  EXP_DTL_EXP_APPID="+exClaim.getExAppId();
	Object[][] queryDtlData = getSqlModel().getSingleResult(queryDtl);
	ArrayList<Object> obj=new ArrayList<Object>();

	 for(int i=0;i<queryDtlData.length;i++){        
		 ExpenseClaimApp  bean1 = new ExpenseClaimApp();
		 
		 //bean1.setItVoucher(bean1.getExAppId());		
		bean1.setItVoucher(checkNull(String.valueOf(querydata[0][0])));		
		 bean1.setItAmount(checkNull(String.valueOf(queryDtlData[i][1])));
		 bean1.setIsProof(checkNull(String.valueOf(queryDtlData[i][2])));
		 bean1.setItParticulars((checkNull(String.valueOf(queryDtlData[i][3]))));
		 bean1.setItUpload(checkNull(String.valueOf(queryDtlData[i][4])));
		 bean1.setItExpenseDate(String.valueOf(queryDtlData[i][5]));
		 bean1.setItValAmount(checkNull(String.valueOf(queryDtlData[i][6])));
		 
		 obj.add(bean1);

	
}
	 exClaim.setExpList(obj);
		
}


public void getExpenseSearch(ExpenseClaimApp exClaim) {
	// TODO Auto-generated method stub
	String poQlquery="SELECT  HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_NAME ,PE_ID,PE_EXP_CAT_VALUE"
		+ " FROM HRMS_TMS_POLICY_EXPENSE"
		+ " LEFT JOIN HRMS_TMS_EXP_CATEGORY ON(HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID=HRMS_TMS_POLICY_EXPENSE.PE_EXP_CAT_ID)" 
		+ " LEFT JOIN HRMS_TMS_TRAVEL_POLICY  ON(HRMS_TMS_TRAVEL_POLICY.POLICY_ID=HRMS_TMS_POLICY_EXPENSE.PE_POLICY_ID) "
		+ " WHERE POLICY_ID ="+exClaim.getPolicyId();
	Object[][] querydata = getSqlModel().getSingleResult(poQlquery);
	logger.info("============exAppId==========="+exClaim.getExAppId());
	String expQuery="SELECT EXP_APP_ID FROM HRMS_TMS_EXP_APP WHERE EXP_APP_TRAVEL_APPID="+exClaim.getTrvAppIdDtl();
	Object[][] expQueryData=getSqlModel().getSingleResult(expQuery);	
	if (expQueryData!=null && expQueryData.length>0 ) 		
	{
	String queryDtl="SELECT EXP_DTL_VCH_HEAD, EXP_DTL_VCH_AMT, EXP_DTL_ISPROOF, EXP_DTL_PARTICULAR,"
		+ " EXP_DTL_UPLOAD_FILENAME,TO_CHAR(EXP_DTL_EXP_DATE,'DD-MM-YYYY'), EXP_DTL_VALID_AMT FROM HRMS_TMS_EXP_DTL WHERE  EXP_DTL_EXP_APPID="+exClaim.getExAppId();
	Object[][] queryDtlData = getSqlModel().getSingleResult(queryDtl);
	ArrayList<Object> obj=new ArrayList<Object>();

	 for(int i=0;i<queryDtlData.length;i++){        
		 ExpenseClaimApp  bean1 = new ExpenseClaimApp();
		 
			
		bean1.setItVoucher(checkNull(String.valueOf(querydata[0][0])));		
		 bean1.setItAmount(checkNull(String.valueOf(queryDtlData[i][1])));
		 bean1.setIsProof(checkNull(String.valueOf(queryDtlData[i][2])));
		 bean1.setItParticulars((checkNull(String.valueOf(queryDtlData[i][3]))));
		 bean1.setItUpload(checkNull(String.valueOf(queryDtlData[i][4])));
		 bean1.setItExpenseDate(String.valueOf(queryDtlData[i][5]));
		 bean1.setItValAmount(checkNull(String.valueOf(queryDtlData[i][6])));
		 
		 obj.add(bean1);

	
}
	 exClaim.setExpList(obj);
	}else if (!exClaim.getExAppId().equals("") && !exClaim.getExAppId().equals(null)) {
		logger.info("Inside else if");
		String queryDtl="SELECT EXP_DTL_VCH_HEAD, EXP_DTL_VCH_AMT, EXP_DTL_ISPROOF, EXP_DTL_PARTICULAR,"
			+ " EXP_DTL_UPLOAD_FILENAME,TO_CHAR(EXP_DTL_EXP_DATE,'DD-MM-YYYY'), EXP_DTL_VALID_AMT FROM HRMS_TMS_EXP_DTL WHERE  EXP_DTL_EXP_APPID="+exClaim.getExAppId();
		Object[][] queryDtlData = getSqlModel().getSingleResult(queryDtl);
		ArrayList<Object> obj=new ArrayList<Object>();

		 for(int i=0;i<queryDtlData.length;i++){        
			 ExpenseClaimApp  bean1 = new ExpenseClaimApp();
			 
				
			bean1.setItVoucher(checkNull(String.valueOf(querydata[0][0])));		
			 bean1.setItAmount(checkNull(String.valueOf(queryDtlData[i][1])));
			 bean1.setIsProof(checkNull(String.valueOf(queryDtlData[i][2])));
			 bean1.setItParticulars((checkNull(String.valueOf(queryDtlData[i][3]))));
			 bean1.setItUpload(checkNull(String.valueOf(queryDtlData[i][4])));
			 bean1.setItExpenseDate(String.valueOf(queryDtlData[i][5]));
			 bean1.setItValAmount(checkNull(String.valueOf(queryDtlData[i][6])));
			 
			 obj.add(bean1);

		
	}
		 exClaim.setExpList(obj);
		
	}
		
}

public boolean delete(ExpenseClaimApp exClaim) {
	// TODO Auto-generated method stub
	boolean res=false;
	boolean result=false;
	
	String delDtl="DELETE FROM HRMS_TMS_EXP_DTL WHERE EXP_DTL_EXP_APPID="+exClaim.getExAppId();
	res=getSqlModel().singleExecute(delDtl);
	if(res){
		String delHdr="DELETE FROM HRMS_TMS_EXP_APP WHERE EXP_APP_ID="+exClaim.getExAppId();	
		result=getSqlModel().singleExecute(delHdr);
		
	}
	return result;
}


public boolean modHdr(ExpenseClaimApp bean,HttpServletRequest request) {
	// TODO Auto-generated method stub
	
	
	/*String delDtl="DELETE FROM HRMS_TMS_EXP_APP WHERE EXP_APP_ID="+bean.getExAppId();
	getSqlModel().singleExecute(delDtl);
	 */
	
	boolean result=false;
	String[] itVoucher = request.getParameterValues("itVoucher");
	String[] itVoucherHeadCode = request.getParameterValues("itVoucherHeadCode");
	String[] itParticulars = request.getParameterValues("itParticulars");	
	String[] itAmount = request.getParameterValues("itAmount");
	String[] itUpload = request.getParameterValues("itUpload");	
	String[] isProof = request.getParameterValues("isProof");
	String[] itExpenseDate = request.getParameterValues("itExpenseDate");
	String[] itValAmount = request.getParameterValues("itValAmount");
	
	Object hdrObj[][] = new Object[1][15];
	Object dtlObj[][] = new Object[1][8];	
	hdrObj[0][0]=bean.getEmployeeId();
	hdrObj[0][1]=bean.getApplDate();
	logger.info("=======bean.getHidSts()========"+bean.getHidSts());
	hdrObj[0][2]=bean.getHidSts();
	hdrObj[0][3]=bean.getExpAmt();
	hdrObj[0][4]=bean.getMode();
	hdrObj[0][5]=bean.getComment();
	//hdrObj[0][6]="1";	
	//hdrObj[0][7]="";  		
	//hdrObj[0][8]="";
	hdrObj[0][6]=bean.getTrvAppIdDtl();
	hdrObj[0][7]=bean.getAdvAmt();	
	if(bean.getMode().equals("T")){
	hdrObj[0][8]=bean.getBankName();
	hdrObj[0][9]=bean.getAccNo();
	}else{
		hdrObj[0][8]="";
		hdrObj[0][9]="";
	}
	if(bean.getMode().equals("S")){
	hdrObj[0][10]=bean.getSalMonth();
	logger.info("============bean.getMonth()========="+bean.getSalMonth());
	hdrObj[0][11]=bean.getYear();
	}else{
		hdrObj[0][10]="";
		hdrObj[0][11]="";
	}	
	hdrObj[0][12]=bean.getRequestName();
	hdrObj[0][13]=bean.getPolicyId();
	hdrObj[0][14]=bean.getExAppId();
	//bean.setMonth(String.valueOf(hdrObj[0][13]));
	result = getSqlModel().singleExecute(getQuery(5), hdrObj);		
	
	if(result){
		
		String delHdr="DELETE FROM HRMS_TMS_EXP_DTL WHERE EXP_DTL_EXP_APPID="+bean.getExAppId();
		getSqlModel().singleExecute(delHdr);
		
		/*String query="SELECT MAX(EXP_APP_ID) FROM HRMS_TMS_EXP_APP";
		Object[][] data = getSqlModel().getSingleResult(query);
		bean.setExAppId(String.valueOf(data[0][0]));*/
		if(itVoucher!=null && itVoucher.length>0){
		for (int i = 0; i < itVoucher.length; i++) {
			dtlObj[0][0]=bean.getExAppId();						
			dtlObj[0][1]=String.valueOf(isProof[i]);
			dtlObj[0][2]=String.valueOf(itParticulars[i]);
			dtlObj[0][3]=String.valueOf(itUpload[i]);
			dtlObj[0][4]=String.valueOf(itAmount[i]);
			dtlObj[0][5]=String.valueOf(itVoucherHeadCode[i]);
			dtlObj[0][6]=String.valueOf(itExpenseDate[i]);			
			dtlObj[0][7]=String.valueOf(itValAmount[i]);		
			
			result=getSqlModel().singleExecute(getQuery(2), dtlObj);	
		}
		}
	}		
	return result;	

}
public boolean forward(ExpenseClaimApp bean,HttpServletRequest request, Object[][] empFlow) {
	// TODO Auto-generated method stub
	
	
	/*String delDtl="DELETE FROM HRMS_TMS_EXP_APP WHERE EXP_APP_ID="+bean.getExAppId();
	getSqlModel().singleExecute(delDtl);
	 */
	
	boolean result=false;
	String[] itVoucher = request.getParameterValues("itVoucher");
	String[] itVoucherHeadCode = request.getParameterValues("itVoucherHeadCode");
	String[] itParticulars = request.getParameterValues("itParticulars");	
	String[] itAmount = request.getParameterValues("itAmount");
	String[] itUpload = request.getParameterValues("itUpload");	
	String[] isProof = request.getParameterValues("isProof");
	String[] itExpenseDate = request.getParameterValues("itExpenseDate");
	String[] itValAmount = request.getParameterValues("itValAmount");
	
	Object hdrObj[][] = new Object[1][18];
	Object dtlObj[][] = new Object[1][8];	
	
	hdrObj[0][0]=bean.getEmployeeId();
	hdrObj[0][1]=bean.getApplDate();
	hdrObj[0][2]="P";
	hdrObj[0][3]=bean.getExpAmt();
	hdrObj[0][4]=bean.getMode();
	hdrObj[0][5]=bean.getComment();
	hdrObj[0][6]="1";	
	hdrObj[0][7]=String.valueOf(empFlow[0][0]);  		
	hdrObj[0][8]=String.valueOf(empFlow[0][3]);
	hdrObj[0][9]=bean.getTrvAppIdDtl();
	hdrObj[0][10]=bean.getAdvAmt();	
	if(bean.getMode().equals("T")){
	hdrObj[0][11]=bean.getBankName();
	hdrObj[0][12]=bean.getAccNo();
	}else{
		hdrObj[0][11]="";
		hdrObj[0][12]="";
	}
	if(bean.getMode().equals("S")){
	hdrObj[0][13]=bean.getSalMonth();
	hdrObj[0][14]=bean.getYear();
	}else{
		hdrObj[0][13]="";
		hdrObj[0][14]="";
	}	
	hdrObj[0][15]=bean.getRequestName();
	hdrObj[0][16]=bean.getPolicyId();
	hdrObj[0][17]=bean.getExAppId();
	logger.info("==========getEmployeeId============"+hdrObj[0][0]);
	logger.info("==========getApplDate============"+hdrObj[0][1]);
	logger.info("==========getStatusFld============"+hdrObj[0][2]);
	logger.info("==========1000============"+hdrObj[0][3]);
	logger.info("==========getMode============"+hdrObj[0][4]);
	logger.info("==========getComment============"+hdrObj[0][5]);
	logger.info("==========level============"+hdrObj[0][6]);
	logger.info("==========empFlow[0][0]============"+hdrObj[0][7]);
	logger.info("==========empFlow[0][3]============"+hdrObj[0][8]);
	logger.info("==========getTrvAppIdDtl============"+hdrObj[0][9]);
	logger.info("==========getAdvAmt============"+hdrObj[0][10]);
	logger.info("==========getBankName============"+hdrObj[0][11]);
	logger.info("==========getAccNo============"+hdrObj[0][12]);
	logger.info("==========getMonth============"+hdrObj[0][13]);
	logger.info("==========getYear============"+hdrObj[0][14]);
	logger.info("==========getRequestName============"+hdrObj[0][15]);
	logger.info("==========getExAppId============"+hdrObj[0][16]);

	
	
	
	result = getSqlModel().singleExecute(getQuery(9), hdrObj);		
	
	if(result){
		
		String delHdr="DELETE FROM HRMS_TMS_EXP_DTL WHERE EXP_DTL_EXP_APPID="+bean.getExAppId();
		getSqlModel().singleExecute(delHdr);
		
		/*String query="SELECT MAX(EXP_APP_ID) FROM HRMS_TMS_EXP_APP";
		Object[][] data = getSqlModel().getSingleResult(query);
		bean.setExAppId(String.valueOf(data[0][0]));*/
		if(itVoucher!=null && itVoucher.length>0){
		for (int i = 0; i < itVoucher.length; i++) {
			dtlObj[0][0]=bean.getExAppId();						
			dtlObj[0][1]=String.valueOf(isProof[i]);
			dtlObj[0][2]=String.valueOf(itParticulars[i]);
			dtlObj[0][3]=String.valueOf(itUpload[i]);
			dtlObj[0][4]=String.valueOf(itAmount[i]);
			dtlObj[0][5]=String.valueOf(itVoucherHeadCode[i]);
			dtlObj[0][6]=String.valueOf(itExpenseDate[i]);			
			dtlObj[0][7]=String.valueOf(itValAmount[i]);		
			
			result=getSqlModel().singleExecute(getQuery(2), dtlObj);	
		}
		}
	}		
	return result;	

}


public void getExpenseOnDoubleClick(ExpenseClaimApp 	bean, String travelAppId) {
	// TODO Auto-generated method stub
	 bean.setTravelAppId(travelAppId); 
	logger.info("===============bean.getTravelViewNo()============"+bean.getTravelViewNo());
	String query="SELECT nvl(HRMS_EMP_OFFC.EMP_FNAME || ' ' || EMP_MNAME || ' ' ||EMP_LNAME,' '),CASE WHEN EXP_APP_STATUS='N' THEN 'New Requisition' WHEN EXP_APP_STATUS='P' THEN 'Pending'"
		+ " WHEN EXP_APP_STATUS='A' THEN 'Rejected'  WHEN EXP_APP_STATUS='A' THEN 'Approved'"
		+ " ELSE 'New Requisition' END,nvl(EXP_APP_EMPID,0), TO_CHAR(EXP_APP_APPDATE,'DD-MM-YYYY'), "
		+ " nvl(EXP_APP_EXPAMT,0), nvl(EXP_APP_PREF_PAYMODE,' '), nvl(EXP_APP_COMMENT,' '),"
		+ " nvl(EXP_APP_TRAVEL_APPID,0), nvl(EXP_APP_ADVANCE_AMT,0), nvl(EXP_APP_BANK_NAME,' '), nvl(EXP_APP_ACCOUNT_NO,' '), nvl(EXP_APP_SAL_MON,' '),"
		+ " nvl(EXP_APP_SAL_YEAR,' '), nvl(EXP_TRAVEL_REQUEST,' '),EXP_APP_ID,nvl(DEPT_NAME,' '),nvl(RANK_NAME,' '),nvl(EMP_TOKEN,' '),nvl(CADRE_NAME,' '),CADRE_ID,nvl(CENTER_NAME,'') "
		+ " ,DECODE(EXP_APP_PREF_PAYMODE,'C','Cash','S','Salary','Q','Cheque','T','Transfer'),"
		+ " DECODE(EXP_APP_SAL_MON,'1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September' ,'10','October','11','November','12','December')"
		+ " FROM HRMS_TMS_EXP_APP"				
		+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_EXP_APP.EXP_APP_EMPID)"
		+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )"   
		+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )"  
		+ " LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER )"
		+ " LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)" 
		+ " WHERE EXP_APP_TRAVEL_APPID="+bean.getTravelAppId();
	Object[][] result=getSqlModel().getSingleResult(query);
	if(result.length>0 && result!=null)
		//"employeeName","statusFld","employeeId","applDate","totAmt","mode","comment","trvAppIdDtl","advAmt","bankName","accNo","month","year","requestName","exAppId","deptName","desgName","empToken","grdName","grdId"
	{
	bean.setEmployeeName(checkNull(String.valueOf(result[0][0])));
	bean.setStatusFld(checkNull(String.valueOf(result[0][1])));
	bean.setEmpId(checkNull(String.valueOf(result[0][2])));
	bean.setApplDate(checkNull(String.valueOf(result[0][3])));
	bean.setExpAmt(checkNull(String.valueOf(result[0][4])));
	bean.setMode(checkNull(String.valueOf(result[0][5])));
	bean.setComment(checkNull(String.valueOf(result[0][6])));
	bean.setTrvAppIdDtl(checkNull(String.valueOf(result[0][7]))); 
	bean.setAdvAmt(checkNull(String.valueOf(result[0][8])));
	bean.setBankName(checkNull(String.valueOf(result[0][9])));
	bean.setAccNo(checkNull(String.valueOf(result[0][10])));
	bean.setSalMonth(checkNull(String.valueOf(result[0][11])).trim());
	bean.setYear(checkNull(String.valueOf(result[0][12])));	
	bean.setRequestName(checkNull(String.valueOf(result[0][13])));
	bean.setExAppId(checkNull(String.valueOf(result[0][14])));
	bean.setDeptName(checkNull(String.valueOf(result[0][15])));
	bean.setDesgName(checkNull(String.valueOf(result[0][17])));
	bean.setEmpToken(checkNull(String.valueOf(result[0][17])));
	bean.setGrdName(checkNull(String.valueOf(result[0][18])));
	bean.setGrdId(checkNull(String.valueOf(result[0][19]))); 
	bean.setBranchName(checkNull(String.valueOf(result[0][20]))); 
	bean.setHidMode(checkNull(String.valueOf(result[0][21]))); 
	bean.setHidMonth(checkNull(String.valueOf(result[0][22])).trim()); 
	
	
	
	}  // end of if
	
}

public boolean callRequistionStatus(ExpenseClaimApp bean)
{   
	    String reqSql ="SELECT PROCESS_MANAGER_CLAIM_REQ  FROM HRMS_TMS_PROCESS_MANAGER " ;
	    Object [][] settleData = getSqlModel().getSingleResult(reqSql);
	    
	    if((""+settleData[0][0]).equals("Y"))
	    {
	    	return true;
	    }else
	    {
	    	return false;
	    }
}





public boolean rejectEmp(ExpenseClaimApp exClaim) {
	boolean result =false;
	
	
	String updateApplStatus ="UPDATE HRMS_TMS_EXP_APP SET EXP_APP_STATUS = 'R' WHERE EXP_APP_ID = "+exClaim.getExAppId();
		
			result = getSqlModel().singleExecute(updateApplStatus);				// update the status to 'Rejected'
		
		if(result){
			Object [][] pathObject = new Object [1][4];
			pathObject [0][0] = exClaim.getExAppId();
			pathObject [0][1] = exClaim.getUserEmpId();
			pathObject [0][2] = exClaim.getApproverComments();
			pathObject [0][3] = "R";
			
			result = getSqlModel().singleExecute(getQuery(6),pathObject);
		}
	
	return result;
}


public void setApproverComments(ExpenseClaimApp bean) {
	// TODO Auto-generated method stub
	Object []applCode = new Object [1];
	applCode [0] = bean.getExAppId();
	/*
	 * get the approver details from database & sets it in the list
	 * 
	 */
	try{
		Object [][] approverData = getSqlModel().getSingleResult(getQuery(7), applCode);
		ArrayList<Object> list=new ArrayList<Object>();
		if(approverData != null && approverData.length !=0){
			for (int i = 0; i < approverData.length; i++) {
				ExpenseClaimApp bean1 = new ExpenseClaimApp();
				bean1.setApproverName(checkNull(String.valueOf(approverData[i][0])));
				bean1.setApproverComment(checkNull(String.valueOf(approverData[i][1])));
				bean1.setApprovedDate(checkNull(String.valueOf(approverData[i][2])));
				bean1.setApproveStatus(checkNull(String.valueOf(approverData[i][3])));
				list.add(bean1);

			}
		} // end of if
		else bean.setCommentFlag("true");
		logger.info("comment flag=="+bean.getCommentFlag());

		bean.setApprList(list);
	}catch (Exception e) {
		e.printStackTrace();
		logger.info("exception in setApproverComments()=="+e);
	}
	
}


public void modExpenseDtl(ExpenseClaimApp exClaim, HttpServletRequest request) {
	// TODO Auto-generated method stub
	String[] voucherHead = request.getParameterValues("itVoucher");
	String[] voucherAmt = request.getParameterValues("itvoucherAmt");
	String[] particulars = request.getParameterValues("itParticulars");
	String[] ExpenseDate = request.getParameterValues("itExpenseDate");
	String[] amount = request.getParameterValues("itAmount");
	String[] itValAmount = request.getParameterValues("itValAmount");
	String[] uploadFileName = request.getParameterValues("itUpload");
	String[] itVoucherHeadCode = request.getParameterValues("itVoucherHeadCode");
	String[] isProof = request.getParameterValues("isProof");
	ArrayList list = new ArrayList();
	double totalValidAmt=0.0;
	double totalValidAmt_Valid=0.0;
	double validAmt=Double.parseDouble(exClaim.getAmount());//valid
	double maxValidAmt=Double.parseDouble(exClaim.getVoucherAmt());//max
	double totalAmount=0.0;
	
	
	
	if(itValAmount != null && itValAmount.length > 0 )
	{
		for(int i=0;i<itValAmount.length;i++)
		{
			if(itVoucherHeadCode[i].equals(""+exClaim.getVoucherHeadCode())){
				totalValidAmt+=Double.parseDouble(itValAmount[i]);
			}
			
			totalAmount+=Double.parseDouble(amount[i]);
			logger.info("===========totalAmount=====1===="+totalAmount);
			
		}
		
		logger.info("===========totalAmount=====2===="+totalAmount);
		totalValidAmt_Valid=totalValidAmt+validAmt;
		
		if(totalValidAmt_Valid>maxValidAmt){
			validAmt=maxValidAmt-totalValidAmt;
		}
	}
	totalAmount=totalAmount+Double.parseDouble(exClaim.getAmount())-Double.parseDouble(exClaim.getHidAmt());
	
	
	if(voucherHead != null && voucherHead.length > 0)
	{
		for(int i=0;i<voucherHead.length;i++)
		{		
			ExpenseClaimApp bean = new ExpenseClaimApp();
			 if (i == Integer.parseInt(exClaim.getHiddenEdit())-1) {
				 		bean.setItVoucher(checkNull(String.valueOf(exClaim.getVoucherHead())));
						bean.setItvoucherAmt(checkNull(String.valueOf(exClaim.getVoucherAmt())));
						bean.setItParticulars(checkNull(String.valueOf(exClaim.getParticulars())));
						bean.setItExpenseDate(checkNull(String.valueOf(exClaim.getExpenseDate())));
						bean.setItAmount(checkNull(String.valueOf(exClaim.getAmount())));
						bean.setItValAmount(checkNull(String.valueOf(validAmt)));		
						bean.setItUpload(checkNull(String.valueOf(exClaim.getUploadFileName())));	
						bean.setItVoucherHeadCode(checkNull(String.valueOf(exClaim.getVoucherHeadCode())));	
						bean.setIsProof(checkNull(String.valueOf(exClaim.getProof())));
						exClaim.setExpAmt(checkNull(String.valueOf(totalAmount)));	
				 
			 }else{
			bean.setItVoucher(voucherHead[i]);
			bean.setItvoucherAmt(voucherAmt[i]);
			bean.setItParticulars(particulars[i]);
			bean.setItExpenseDate(ExpenseDate[i]);
			bean.setItAmount(amount[i]);	
			bean.setItValAmount(itValAmount[i]);
			logger.info("----------valid amount--------"+itValAmount[i]);
			bean.setItUpload(uploadFileName[i]);
			bean.setItVoucherHeadCode(itVoucherHeadCode[i]);
			bean.setIsProof(isProof[i]);
			 }
			list.add(bean);
			}
	}
	ExpenseClaimApp bean = new ExpenseClaimApp();
	
	
		logger.info("----------list----------"+list.size());
		exClaim.setExpList(list);
		logger.info("----------exClaim.getExAppId()----------"+exClaim.getExAppId());
		 if(list.size()==0){
			 bean.setExpListLength("0"); 
			 exClaim.setExpLength("false");
		 }else{
			 bean.setExpListLength("1"); 
			 exClaim.setExpLength("true");
		 }
		 if(Double.parseDouble(exClaim.getExpAmt())>maxValidAmt){
			 exClaim.setMaxLimit("true");
		 }else{
			 exClaim.setMaxLimit("false");
		 }
	
}

	public void  viewExistingRecord(ExpenseClaimApp bean)
	{
		String sql =" SELECT nvl(HRMS_EMP_OFFC.EMP_FNAME || ' ' || EMP_MNAME || ' ' ||EMP_LNAME,' '), " 
			+" CASE WHEN EXP_APP_STATUS='N' THEN 'New Requisition' WHEN  EXP_APP_STATUS='P' THEN 'Pending' WHEN EXP_APP_STATUS='A' THEN 'Approved'  WHEN EXP_APP_STATUS='R' THEN 'Rejected' ELSE 'New Requisition' END," 
			+" EXP_APP_STATUS   ,nvl(EXP_APP_EMPID,0), TO_CHAR(EXP_APP_APPDATE,'DD-MM-YYYY')," 
			+" nvl(EXP_APP_EXPAMT,0), nvl(EXP_APP_PREF_PAYMODE,' '), nvl(EXP_APP_COMMENT,' ')," 
			+" nvl(EXP_APP_TRAVEL_APPID,0), nvl(EXP_APP_ADVANCE_AMT,0), nvl(EXP_APP_BANK_NAME,' ')," 
			+" nvl(EXP_APP_ACCOUNT_NO,' '), nvl(EXP_APP_SAL_MON,' '), nvl(EXP_APP_SAL_YEAR,' '), nvl(EXP_TRAVEL_REQUEST,' ')," 
			+" EXP_APP_ID,nvl(DEPT_NAME,' '),nvl(RANK_NAME,' '),nvl(EMP_TOKEN,' '),nvl(CADRE_NAME,' '),CADRE_ID ,nvl(CENTER_NAME,'') FROM HRMS_TMS_EXP_APP  "
			+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_EXP_APP.EXP_APP_EMPID) "
			+" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
			+" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
			+" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
			+" LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE)  "
			+" WHERE  EXP_APP_ID ="+bean.getExAppId();
		Object[][] data = getSqlModel().getSingleResult(sql); 
		
		if(data!=null && data.length>0)
		{
			bean.setEmployeeName(""+data[0][0]);
			bean.setStatusFld(""+data[0][1]);
			bean.setHidSts(""+data[0][2]);
			bean.setEmployeeId(""+data[0][3]); 
			bean.setApplDate(""+data[0][4]);
			bean.setExpAmt(""+data[0][5]);
			bean.setMode(""+data[0][6]);
			bean.setComment(""+data[0][7]); 
			bean.setTrvAppIdDtl(""+data[0][8]);
			bean.setAdvAmt(""+data[0][9]);
			bean.setBankName(""+data[0][10]);
			bean.setAccNo(""+data[0][11]); 
			bean.setMonth(""+data[0][12]);
			bean.setYear(""+data[0][13]);
			bean.setRequestName(""+data[0][14]);
			bean.setExAppId(""+data[0][15]); 
			bean.setDeptName(""+data[0][16]);
			bean.setDesgName(""+data[0][17]);
			bean.setEmpToken(""+data[0][18]);
			bean.setGrdName(""+data[0][19]); 
			bean.setGrdId(""+data[0][20]);
			bean.setBranchName(""+data[0][21]); 
		}
	} 
	
	public void getPolicyForExist(ExpenseClaimApp bean)
	{
		String sql =" SELECT EXP_APP_POLICY_ID  FROM HRMS_TMS_EXP_APP WHERE  EXP_APP_ID ="+bean.getExAppId();
		Object [][] data = getSqlModel().getSingleResult(sql); 
		if(data!=null && data.length>0)
		{
			bean.setPolicyId(""+data[0][0]);
		}
	}
	
} // end of class
	



