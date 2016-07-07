package org.struts.action.CR;

import org.paradyne.bean.CR.CustomerAccountInfo;
import org.paradyne.lib.report.CrystalReportSQL;
import org.paradyne.model.CR.CustomerAccountInfoModel;
import org.struts.lib.CustomerActionSupport;
/**Created on 17th Jan 2012.
 * @author aa0476
 */
public class CustomerAccountInfoAction extends CustomerActionSupport {

	CustomerAccountInfo bean;
	/** prepare_local. * */
	public void prepare_local() throws Exception {
		bean = new CustomerAccountInfo();
		bean.setMenuCode(5034);
	}
	/** getModel. * */
	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	/**
	 * @return the bean
	 */
	public CustomerAccountInfo getBean() {
		return bean;
	}

	/**
	 * @param bean
	 *            the bean to set
	 */
	public void setBean(CustomerAccountInfo bean) {
		this.bean = bean;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		CustomerAccountInfoModel model = new CustomerAccountInfoModel();
		model.initiate(context, session);
		model.terminate();
	}
	/**
	 * Purpose : This method is used to get report list
	 * @return string
	 * @throws Exception
	 */
	public String getReportList() throws Exception {
		CustomerAccountInfoModel model = new CustomerAccountInfoModel();
		model.initiate(context, session);
		String customerCode = bean.getCustomerUserEmpId();// request.getParameter("accountCode");
		
		
		
		/*
		 * SET ACCOUNT CODE FROM HISTORY
		 */
		
		bean.setLoginName("");
		String requestID = request.getParameter("accountId");
		
		String reportCode = request.getParameter("reportCode");
		
		String cUserCode = request.getParameter("accountId");
		
		bean.setLoginMultipleName(reportCode);
		if(requestID !=null ){
			
			bean.setCustCode(cUserCode);
			model.callMultiplePublishDate(bean,request);
		}else{
			requestID = bean.getCustomerUserEmpId();
			bean.setCustCode(cUserCode);
			model.callPublishDate(bean,request);
		}
		String getAccountCode = model.getAccountCodeFromHistory(requestID, bean);
		
		if (!getAccountCode.equals("")) {
			model.getAccReport(bean, getAccountCode, request);
		}
		model.terminate();
		return "getAccReport";
	}
	
	
	
	/**
	 * Purpose : This method is used to get report list
	 * @return string
	 * @throws Exception
	 */
	public String getMultipleAccountList() throws Exception {
		CustomerAccountInfoModel model = new CustomerAccountInfoModel();
		model.initiate(context, session);
		String accountCode = bean.getCustomerUserEmpId();// request.getParameter("accountCode");
		model.callMultiplePublishDate(bean,request);
		
		String loginName = request.getParameter("loginName");
		///loginName = loginName.substring(1, loginName.length() - 1);
		model.getMultipleAccReportList(bean, request,loginName);
		
		model.terminate();
		return "getMultipleAccount";
	}
	
	
	

	/**.
	 * Purpose : This method is used to get account report list
	 * @return string
	 * @throws Exception
	 */
	public String getAccReportList() throws Exception {
		CustomerAccountInfoModel model = new CustomerAccountInfoModel();
		model.initiate(context, session);
		String accountCode = bean.getAccountCode();// request.getParameter("accountCode");
		model.insertReportHistory(bean);
		model.callPublishDate(bean,request);
		model.getAccReport(bean, accountCode, request);
		
		model.terminate();
		return "getAccReport";
	}
	/**.
	 * Purpose : This method is used to generate report
	 * @return string
	 * @throws Exception
	 */
	public String generateReport() throws Exception {
		CustomerAccountInfoModel model = new CustomerAccountInfoModel();
		model.initiate(context, session);
		String reportCode = request.getParameter("reportCode");
		String accountvalue = request.getParameter("accountvalue");
		String listGroupNameValue = request.getParameter("listGroupNameValue");
		String listAccountIDValue = request.getParameter("listAccountIDValue");
		String listhCustomerCode = request.getParameter("listhCustomerCode");
		String publishDatevalue = request.getParameter("publishDatevalue");
		
		bean.setHCustomerCode(listhCustomerCode);
		bean.setReportCode(reportCode);
		bean.setAccountCode(accountvalue);
		bean.setAccountID(listAccountIDValue);
		bean.setHPublishDate(publishDatevalue);
		String result = "generateReport";
		model.terminate();
		if (listGroupNameValue != null && listGroupNameValue.length() > 0) {
			if (listGroupNameValue.equals("Daily_Performance_summary")) {
				result = "Daily_Performance_summary";
				// return "Daily_Performance_summary";
			} else if (listGroupNameValue.equals("Weekly_Performance_Summary")) {
				result = "Weekly_Performance_Summary";
				// return "Weekly_Performance_Summary";
			} else if (listGroupNameValue.equals("Week_Stoplight_Summary")) {
				result = "Week_Stoplight_Summary";
				// return "Week_Stoplight_Summary";
			} else if (listGroupNameValue
					.equals("Daily_Performance_summary_Group")) {
				result = "Daily_Performance_summary_Group";
				// return "Daily_Performance_summary_Group";
			}
		} else {
			result = "generateReport";
		}
		return result;
	}
	/**
	 * Purpose : This method is used to generate report
	 * @return string
	 * @throws Exception
	 */
	public String report() throws Exception {
		CustomerAccountInfoModel model = new CustomerAccountInfoModel();
		model.initiate(context, session);
		String reportcodeValue = request.getParameter("reportcodeValue12");
		String[] str = reportcodeValue.split("_");
		bean.setReportCode(str[0]);
		bean.setAccountCode(str[1]);
		model.insertReportHistory(bean);
		CrystalReportSQL cr = new CrystalReportSQL();
		cr.initiate(context, session);
		String path = "/Sample Reports/ORCLWeeklyPerformanceSummary.rpt";
		String parameter[] = { "fromweek", "toweek", "account_id" };
		String values[] = { "1", "52", "LEXMARK999" };
		cr.createReportNew(request, response, context, session, path, "",
				parameter, values, null, null);
		return null;
	}
	/**..
	 * Purpose : This method is used to generate daily performance ssummary report
	 * @return string
	 * @throws Exception
	 */
	public String dailyPerformanceSummaryReport() throws Exception {
		CustomerAccountInfoModel model = new CustomerAccountInfoModel();
		model.initiate(context, session);
		
		String reportcodeValue = request.getParameter("reportcodeValue12");
		String[] str = reportcodeValue.split("_");
		bean.setReportCode(str[0]);
		
		bean.setAccountCode(str[1]);
		String fromDate = str[2];
		String toDate = str[3]; 
		////String toDate = bean.getPublishDate();
		String listAccountID = str[4];
		
		String[] dateSplit = fromDate.split("-");
		fromDate = dateSplit[1] + "/" + dateSplit[0] + "/" + dateSplit[2];
		dateSplit = toDate.split("-");
		toDate = dateSplit[1] + "/" + dateSplit[0] + "/" + dateSplit[2];
		model.insertReportHistory(bean);
		CrystalReportSQL cr = new CrystalReportSQL();
		cr.initiate(context, session);
		
		
		String query = " SELECT A.CRM_FLAG, A.CLIENT_FLAG,B.METRICS_ABBR,B.SRNO " +
				" FROM CR_PERFORMANCE_METRICS A,CR_PERFORMANCE_METRICS_MASTER B" +
				" WHERE A.METRICS_CODE=B.METRICS_CODE AND A.ACCOUNT_ID IN (SELECT ACCOUNT_CODE FROM CR_CLIENT_MASTER WHERE ACCOUNT_ID ='"+listAccountID+"') "+
				" ORDER BY A.CLIENT_FLAG DESC,B.SRNO";
		
		Object crmObj[][] = model.getSqlModel().getSingleResult(query);
		String col[]=new String[36];
		int i=0; 
		
		for (int k = 0; k < crmObj.length; k++) {
		int j=Integer.parseInt(String.valueOf(crmObj[k][3]));
		if (String.valueOf(crmObj[k][1]).equals("Y")) 
		{
		if (j==13)
		{
			col[i]="NBD_CONT_TGT";
			i++; 
		}
		if (j==14) 
		{
			col[i]="SAMED_CONT_TGT";
			i++;
		}
		if (j==15)
		{
			col[i]="HR4_CONT_TGT";
			i++;
		}
		if (j==16)
		{
			col[i]="NBD_RESP_TGT";
			i++;
		}
		if (j==17)
		{
			col[i]="SAMED_RESP_TGT";
			i++;
		}
		if (j==18)
		{
			col[i]="HR4_RESP_TGT";
			i++;
		}
		if (j==20)
		{
			col[i]="FCC_TGT";
			i++;
		}
		if (j==22)
		{
			col[i]="PPC_TGT";
			i++;
		}
		if (j==24)
		{
			col[i]="CB_TGT";
			i++;
		} 
		
		if (j==26)
		{
			col[i]="RESTORE_TGT";
			i++;
		} 
		
		col[i]=String.valueOf(crmObj[k][2]);
		} 
		else 
		col[i]="NONE";    
		i++; 
		} 
			
		while (i<36)
		{
		col[i]="NONE";  
		i++; 
		}
		String path = "/Sample Reports/PerformanceSummary-dynamic.rpt";
		//String parameter[] = { "fromdate", "todate", "account_id","col1","col2","col3","col4","col5","col6","col7","col8","col9","col10","col11","col12","col13","col14","col15","col16","col17","col18","col19","col20","col21","col22","col23","col24","col25","col26","col27","col28","col29","col30","col31","col32","col33","col34"};
		//String values[] = { fromDate, toDate, listAccountID,col[0],col[1],col[2],col[3],col[4],col[5],col[6],col[7],col[8],col[9],col[10],col[11],col[12],col[13],col[14],col[15],col[16],col[17],col[18],col[19],col[20],col[21],col[22],col[23],col[24],col[25],col[26],col[27],col[28],col[29],col[30],col[31],col[32],col[33]};
		String parameter[] = { "fromdate", "todate", "account_id","col1","col2","col3","col4","col5","col6","col7","col8","col9","col10","col11","col12","col13","col14","col15","col16","col17","col18","col19","col20","col21","col22","col23","col24","col25","col26","col27","col28","col29","col30","col31","col32","col33","col34","col35","col36"};
		String values[] = { fromDate, toDate, listAccountID,col[0],col[1],col[2],col[3],col[4],col[5],col[6],col[7],col[8],col[9],col[10],col[11],col[12],col[13],col[14],col[15],col[16],col[17],col[18],col[19],col[20],col[21],col[22],col[23],col[24],col[25],col[26],col[27],col[28],col[29],col[30],col[31],col[32],col[33],col[34],col[35]};

		cr.createReportNew(request, response, context, session, path, "",
				parameter, values, null, null);
		return null;
	}
	/**.
	 * Purpose : This method is used to generate daily performance summary group report
	 * @return string
	 * @throws Exception
	 */
	public String dailyPerformanceSummaryGroupReport() throws Exception {
		CustomerAccountInfoModel model = new CustomerAccountInfoModel();
		model.initiate(context, session);
		String reportcodeValue = request.getParameter("reportcodeValue12");
		System.out.println("accCodeValue:::::" + reportcodeValue);
		String[] str = reportcodeValue.split("_");
		bean.setReportCode(str[0]);
		bean.setAccountCode(str[1]);
		String fromDate = str[2];
		String toDate = str[3];
		String listAccountID = str[4]; 
		String[] dateSplit = fromDate.split("-");
		fromDate = dateSplit[1] + "/" + dateSplit[0] + "/" + dateSplit[2];
		dateSplit = toDate.split("-");
		toDate = dateSplit[1] + "/" + dateSplit[0] + "/" + dateSplit[2];
		model.insertReportHistory(bean);
		CrystalReportSQL cr = new CrystalReportSQL();
		cr.initiate(context, session);
		String path = "/Sample Reports/ORCLDailyPerformanceSummaryGroup.rpt";
		String parameter[] = { "fromdate", "todate", "account_id" };
		String values[] = { fromDate, toDate, listAccountID };
		cr.createReportNew(request, response, context, session, path, "",
				parameter, values, null, null);
		return null;
	}
	/**.
	 * Purpose : This method is used to generate weekly performance summary report.
	 * @return string
	 * @throws Exception
	 */
	public String weeklyPerformanceSummaryReport() throws Exception {
		CustomerAccountInfoModel model = new CustomerAccountInfoModel();
		model.initiate(context, session);
		String reportcodeValue = request.getParameter("reportcodeValue12");
		String[] str = reportcodeValue.split("_");
		bean.setReportCode(str[0]);
		bean.setAccountCode(str[1]);
		String fromWeek = str[2];
	
	String toWeek = str[3]; 
		/*String pubDate = bean.getPublishDate();
		if(toWeek > pubDate){
			toWeek = bean.getPublishDate();
		}*/
	
		
		String listAccountID = str[4];
		String query = "select To_char(to_date('" + fromWeek
				+ "','dd-mm-yyyy'),'ww'),To_char(to_date('" + toWeek
				+ "','dd-mm-yyyy'),'ww') from dual ";
		Object[][] obj = model.getSqlModel().getSingleResult(query);
		if (obj != null && obj.length > 0) {
			fromWeek = String.valueOf(obj[0][0]);
			toWeek = String.valueOf(obj[0][1]);
		}
		model.insertReportHistory(bean);
		CrystalReportSQL cr = new CrystalReportSQL();
		cr.initiate(context, session);
		
		String crmQuery = " SELECT A.CRM_FLAG, A.CLIENT_FLAG,B.METRICS_ABBR,B.SRNO " +
				" FROM CR_PERFORMANCE_METRICS A,CR_PERFORMANCE_METRICS_MASTER B" +
				" WHERE A.METRICS_CODE=B.METRICS_CODE AND A.ACCOUNT_ID IN (SELECT ACCOUNT_CODE FROM CR_CLIENT_MASTER WHERE ACCOUNT_ID ='"+listAccountID+"') "+
				" ORDER BY A.CLIENT_FLAG DESC,B.SRNO";

		Object crmObj[][] = model.getSqlModel().getSingleResult(crmQuery);
		String col[]=new String[36];
		int i=0; 
		
		for (int k = 0; k < crmObj.length; k++) {
		int j=Integer.parseInt(String.valueOf(crmObj[k][3]));
		if (String.valueOf(crmObj[k][1]).equals("Y"))
		{
			if (j==13)
			{
				col[i]="NBD_CONT_TGT";
				i++; 
			}
			if (j==14) 
			{
				col[i]="SAMED_CONT_TGT";
				i++;
			}
			if (j==15)
			{
				col[i]="HR4_CONT_TGT";
				i++;
			}
			if (j==16)
			{
				col[i]="NBD_RESP_TGT";
				i++;
			}
			if (j==17)
			{
				col[i]="SAMED_RESP_TGT";
				i++;
			}
			if (j==18)
			{
				col[i]="HR4_RESP_TGT";
				i++;
			}
			if (j==20)
			{
				col[i]="FCC_TGT";
				i++;
			}
			if (j==22)
			{
				col[i]="PPC_TGT";
				i++;
			}
			if (j==24)
			{
				col[i]="CB_TGT";
				i++;
			} 
			if (j==26)
			{
				col[i]="RESTORE_TGT";
				i++;
			} 
			
			col[i]=String.valueOf(crmObj[k][2]);
		} 
		else 
			col[i]="NONE";    
			i++; 
		} 
		
		while (i<36)
		{
			col[i]="NONE";  
			i++; 
		}
		
		String path = "/Sample Reports/WklyPerformanceSummary-dynamic.rpt";
		/*String parameter[] = { "fromweek", "toweek", "account_id" };
		String values[] = { fromWeek, toWeek, listAccountID };*/
		
		String parameter[] = { "fromweek", "toweek", "account_id","col1","col2","col3","col4","col5","col6","col7","col8","col9","col10","col11","col12","col13","col14","col15","col16","col17","col18","col19","col20","col21","col22","col23","col24","col25","col26","col27","col28","col29","col30","col31","col32","col33","col34","col35","col36"};
		String values[] = { fromWeek, toWeek, listAccountID,col[0],col[1],col[2],col[3],col[4],col[5],col[6],col[7],col[8],col[9],col[10],col[11],col[12],col[13],col[14],col[15],col[16],col[17],col[18],col[19],col[20],col[21],col[22],col[23],col[24],col[25],col[26],col[27],col[28],col[29],col[30],col[31],col[32],col[33],col[34],col[35]};
		
		cr.createReportNew(request, response, context, session, path, "",
				parameter, values, null, null);
		return null;
	}
	/**.
	 * Purpose : This method is used to generate weekly stop light summary report
	 * @return string
	 * @throws Exception
	 */
	public String weeklyStopLoghtSummaryReport() throws Exception {
		CustomerAccountInfoModel model = new CustomerAccountInfoModel();
		model.initiate(context, session);
		String reportcodeValue = request.getParameter("reportcodeValue12");
		String[] str = reportcodeValue.split("_");
		bean.setReportCode(str[0]);
		bean.setAccountCode(str[1]);
		String fromWeek = str[2];
	String toWeek = str[3]; 
	////String toWeek = bean.getPublishDate();
		String listAccountID = str[4];
		String query = "select To_char(to_date('" + fromWeek
				+ "','dd-mm-yyyy'),'ww'),To_char(to_date('" + toWeek
				+ "','dd-mm-yyyy'),'ww') from dual ";
		Object[][] obj = model.getSqlModel().getSingleResult(query);
		if (obj != null && obj.length > 0) {
			fromWeek = String.valueOf(obj[0][0]);
			toWeek = String.valueOf(obj[0][1]);
		}
		model.insertReportHistory(bean);
		CrystalReportSQL cr = new CrystalReportSQL();
		cr.initiate(context, session);
		
		String crmQuery = " SELECT A.CRM_FLAG, A.CLIENT_FLAG,B.METRICS_ABBR,B.SRNO " +
				" FROM CR_PERFORMANCE_METRICS A,CR_PERFORMANCE_METRICS_MASTER B" +
				" WHERE (B.METRICS_CODE<=16 OR B.METRICS_CODE=26) AND A.METRICS_CODE=B.METRICS_CODE AND A.ACCOUNT_ID IN (SELECT ACCOUNT_CODE FROM CR_CLIENT_MASTER WHERE ACCOUNT_ID ='"+listAccountID+"') "+
				" ORDER BY A.CLIENT_FLAG DESC,B.SRNO";
		
		Object crmObj[][] = model.getSqlModel().getSingleResult(crmQuery);
		String col[]=new String[17];
		int i=0; 
		
		for (int k = 0; k < crmObj.length; k++) {
		int j=Integer.parseInt(String.valueOf(crmObj[k][3]));
		if(j==26) j=17;
		if (String.valueOf(crmObj[k][1]).equals("Y"))
		{
		
				col[i]=String.valueOf(crmObj[k][2]);
		
				//Columns value correction as names mismatched in report.
				if (String.valueOf(crmObj[k][2]).equals("VOL_ACT_NC")) 
					col[i]="VOL_PLN_NC";
				if (String.valueOf(crmObj[k][2]).equals("VOL_CLOSE_BL"))
					col[i]="BACK_LONG_BA";
				if (String.valueOf(crmObj[k][2]).equals("PPC_PARTS_UT"))
					col[i]="PPC_PARTS_APC";
		} 
		else 
			col[i]="NONE";    
			i++; 
		} 
		
		while (i<17)
		{
			col[i]="NONE";  
			i++; 
		}
		
		
		String path = "/Sample Reports/StopLightPerformanceSummary-dynamic.rpt";
		/*String parameter[] = { "fromweek", "toweek", "account_id" };
		String values[] = { fromWeek, toWeek, listAccountID };*/
		
		String parameter[] = { "fromweek", "toweek", "account_id","col1","col2","col3","col4","col5","col6","col7","col8","col9","col10","col11","col12","col13","col14","col15","col16","col17"};
		String values[] = { fromWeek, toWeek, listAccountID,col[0],col[1],col[2],col[3],col[4],col[5],col[6],col[7],col[8],col[9],col[10],col[11],col[12],col[13],col[14],col[15],col[16]};

		cr.createReportNew(request, response, context, session, path, "",
				parameter, values, null, null);
		return null;
	}

	/**
	 * Purpose : This method is used to display list of account
	 * @return string
	 */
	public String f9Account() {
		try {
			CustomerAccountInfoModel model = new CustomerAccountInfoModel();
			model.initiate(context, session);
			
			String code = bean.getCustCode();
			if(!(code.equals(""))){
				code = bean.getCustCode();
			}else{
				code = bean.getCustomerUserEmpId();
			}
			String selQuery = "SELECT NVL(ACCOUNT_CHILD_CODE,0) FROM CR_CLIENT_USERS WHERE CRUSER_CODE="
					+ code;
			Object[][] obj = model.getSqlModel().getSingleResult(selQuery);
			String accChildCode = "0";
			if (obj != null && obj.length > 0) {
				if (obj != null && obj.length > 0) {
					for (int i = 0; i < obj.length; i++) {
						accChildCode += "," + String.valueOf(obj[i][0]); 
					}
				}
			}
			String query = " SELECT NVL(ACCOUNT_ID,' '),NVL(ACCOUNT_NAME,' ') ,ACCOUNT_CODE,NVL(ACCOUNT_LOGO,' ')  FROM CR_CLIENT_MASTER  "
					+ " WHERE IS_ACTIVE='Y' AND PARENT_FLAG='N' AND (PARENT_CODE IN("
					+ accChildCode + ")) ";

			query += " UNION SELECT NVL(ACCOUNT_ID,' '),NVL(ACCOUNT_NAME,' ') ,ACCOUNT_CODE,NVL(ACCOUNT_LOGO,' ')  FROM CR_CLIENT_MASTER  "
					+ " WHERE IS_ACTIVE='Y' AND (ACCOUNT_CODE IN("
					+ accChildCode + ")) ";

			String[] headers = { "Account ID", "Account Name" };
			String[] headerWidth = { "30", "70" };
			String[] fieldNames = { "accountID", "accountName", "accountCode",
					"accountLogo" };
			int[] columnIndex = { 0, 1, 2, 3 };
			String submitFlag = "true";
			String submitToMethod = "CustomerAccountInfo_getAccReportList.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			return "";
		}
	}

	/**.
	 * Purpose : This method is used to call change password
	 * @return
	 * @throws Exception
	 */
	public String callChangePassword() throws Exception {
		CustomerAccountInfoModel model = new CustomerAccountInfoModel();
		model.initiate(context, session);
		String query = "SELECT FIRST_NAME||' '||LAST_NAME FROM CR_CLIENT_USERS WHERE CRUSER_CODE="
				+ bean.getCustomerUserEmpId();
		Object[][] obj = model.getSqlModel().getSingleResult(query);
		if (obj != null && obj.length > 0) {
			bean.setCustomerName(String.valueOf(obj[0][0]));
		}
		return "customerChangePass";
	}

	/**.
	 * Purpose : This method is used to change password.
	 * @return string
	 */
	public String changePassWord() {
		CustomerAccountInfoModel model = new CustomerAccountInfoModel();
		model.initiate(context, session);
		boolean result = model.changePassWord(bean);
		if (result) {
			addActionMessage("Password changed successfully");
		} else {
			addActionMessage("Invalid Old Password");
		}
		return "customerChangePass";
	}
	
	


}
