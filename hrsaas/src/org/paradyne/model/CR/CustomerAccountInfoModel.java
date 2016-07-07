package org.paradyne.model.CR;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.paradyne.bean.CR.CustomerAccountInfo;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
/**Created on 17th Jan 2012.
 * @author aa0476
 */
public class CustomerAccountInfoModel extends ModelBase {
	/** logger. */
	private Logger logger = Logger.getLogger(CustomerAccountInfoModel.class);
	/**
	 * Purpose : This method is used to get customer account list
	 * @param bean
	 */
	public void getCustomerAccList(CustomerAccountInfo bean) {
		/**
		 * SET ACCOUNT INFORMATION
		 */
			String accQuery="SELECT ACCOUNT_CODE,NVL(ACCOUNT_ID,' '),NVL(ACCOUNT_NAME,' ') ,NVL(CONTACT_NAME,' '),NVL(EMAIL_ID,' ') " +
					" FROM CR_CLIENT_MASTER WHERE PARENT_FLAG='Y' AND (ACCOUNT_CODE IN(SELECT ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE="+bean.getCustomerUserEmpId()+")" +
					"  OR PARENT_CODE  IN(SELECT ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE="+bean.getCustomerUserEmpId()+"))";
			//GET ACCOUNT INFORMATION FROM CLIENT MASTER FOR PARENT FLAG='Y'
			accQuery="SELECT ACCOUNT_CODE,NVL(ACCOUNT_ID,' '),NVL(ACCOUNT_NAME,' ') ,NVL(CONTACT_NAME,' '),NVL(EMAIL_ID,' ') " +
					" FROM CR_CLIENT_MASTER WHERE PARENT_FLAG='Y' " +
					" AND (ACCOUNT_CODE IN(SELECT ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE="+bean.getCustomerUserEmpId()+"))";
			Object[][]accObj=getSqlModel().getSingleResult(accQuery);
			//GET ACCOUNT INFORMATION FROM CLIENT MASTER FOR PARENT FLAG='N'
			String reportQuery="SELECT ACCOUNT_CODE,NVL(ACCOUNT_ID,' '),NVL(ACCOUNT_NAME,' ') ,NVL(CONTACT_NAME,' '),NVL(EMAIL_ID,' ') " +
					" FROM CR_CLIENT_MASTER WHERE PARENT_FLAG='N' " +
					" AND (PARENT_CODE IN(SELECT ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE="+bean.getCustomerUserEmpId()+"))";
			Object[][]reportObj=getSqlModel().getSingleResult(reportQuery);
			bean.setAccountList(null);
			if(accObj!=null && accObj.length>0){
				ArrayList list=new ArrayList();
				for (int i = 0; i < accObj.length; i++) {
					CustomerAccountInfo subBean=new CustomerAccountInfo();
					subBean.setListAccountCode(String.valueOf(accObj[i][0]));
					subBean.setListAccountID(String.valueOf(accObj[i][1]));
					subBean.setListAccountName(String.valueOf(accObj[i][2]));
					subBean.setListContactName(String.valueOf(accObj[i][3]));
					subBean.setListEmailID(String.valueOf(accObj[i][4]));
					ArrayList reportlist=new ArrayList();
					
					for (int j = 0; j < reportObj.length; j++) {
						CustomerAccountInfo reportBean=new CustomerAccountInfo();
						reportBean.setListAccountCode(String.valueOf(reportObj[i][0]));
						reportBean.setListAccountID(String.valueOf(reportObj[i][1]));
						reportBean.setListAccountName(String.valueOf(reportObj[i][2]));
						reportBean.setListContactName(String.valueOf(reportObj[i][3]));
						reportBean.setListEmailID(String.valueOf(reportObj[i][4]));
						reportlist.add(reportBean);
					}
					subBean.setAccountReportList(reportlist);
					
					list.add(subBean);
				}		
				bean.setAccountList(list);
			}
		
	}//end of method
	/**
	 * Purpose : This method is used to get account report
	 * @param bean
	 * @param accountCode
	 * @param request
	 */ 
	public void getAccReport(CustomerAccountInfo bean, String accountCode, HttpServletRequest request) {
		/*String query=" SELECT CR_ACC_REPORT_MAPP.ACCOUNT_CODE,CR_ACC_REPORT_MAPP.REPORT_CODE,CR_REPORT_MASTER.REPORT_NAME,CR_REPORT_MASTER.GROUP_NAME "
					+"  ,CR_REPORT_MASTER.REPORT_ICON,CR_REPORT_MASTER.REPORT_DESC FROM CR_ACC_REPORT_MAPP "
					+"  INNER JOIN CR_REPORT_MASTER ON(CR_REPORT_MASTER.REPORT_CODE=CR_ACC_REPORT_MAPP.REPORT_CODE)"
					+"  WHERE ACCOUNT_CODE="+accountCode+" ORDER BY CR_ACC_REPORT_MAPP.REPORT_CODE";*/
		
		String query = "SELECT DISTINCT CR_ACC_REPORT_MAPP.REPORT_CODE,CR_REPORT_MASTER.REPORT_NAME," +
				"CR_REPORT_MASTER.GROUP_NAME   ,CR_REPORT_MASTER.REPORT_ICON,CR_REPORT_MASTER.REPORT_DESC " +
				"FROM CR_ACC_REPORT_MAPP   " +
				"INNER JOIN CR_REPORT_MASTER ON(CR_REPORT_MASTER.REPORT_CODE=CR_ACC_REPORT_MAPP.REPORT_CODE) "
				+" WHERE ACCOUNT_CODE IN (SELECT ACCOUNT_CODE FROM CR_CLIENT_MASTER CONNECT BY ACCOUNT_CODE=PRIOR CASE WHEN PARENT_CODE=ACCOUNT_CODE THEN NULL ELSE PARENT_CODE END " 
				+" START WITH ACCOUNT_CODE="+accountCode+") ORDER BY CR_ACC_REPORT_MAPP.REPORT_CODE";
		 
		Object[][]obj=getSqlModel().getSingleResult(query);
		//QUERY TO GET ACCOUNT INFO FROM HISTORY
		/*String runByQuery="SELECT MAX(HISTORY_CODE),REPORT_CODE ,MAX(TO_CHAR(GENEARTE_DATE,'DD-MM-YYYY HH24:MI')) "
						+"	 FROM CR_REPORT_HISTORY WHERE CUSTOMER_CODE="+bean.getCustomerUserEmpId()+" AND REPORT_CODE IS NOT NULL"
						+"	   GROUP BY REPORT_CODE  ";*/
		
		String runByQuery="SELECT MAX(HISTORY_CODE),REPORT_CODE ,TO_CHAR(MAX(GENEARTE_DATE),'DD-MM-YYYY HH24:MI') "
			+"	 FROM CR_REPORT_HISTORY WHERE CUSTOMER_CODE="+accountCode+" AND REPORT_CODE IS NOT NULL"
			+"	   GROUP BY REPORT_CODE  ";
		
		
		HashMap<String, Object[][]> runByMap = getSqlModel().getSingleResultMap(runByQuery,1,2);
		
		bean.setReportList(null);
		if(obj!=null && obj.length>0){
			ArrayList list=new ArrayList();
			for (int i = 0; i < obj.length; i++) {
				CustomerAccountInfo reportBean=new CustomerAccountInfo(); 
				
				String code1 = String.valueOf(obj[i][0]);
				
				if(!(code1.equals(""))){
					code1 = String.valueOf(obj[i][0]);
				}else{
					code1 = bean.getCustomerUserEmpId();
				}	
				 
				reportBean.setListAccountCode(String.valueOf(code1));
				reportBean.setListReportCode(String.valueOf(obj[i][0]));
				reportBean.setListReportName(String.valueOf(obj[i][1]));
				reportBean.setListGroupName(String.valueOf(obj[i][2]));
				reportBean.setListAccountID(bean.getAccountID());
				reportBean.setListIcon(String.valueOf(obj[i][3]));
				reportBean.setListReportDesc(String.valueOf(obj[i][4]));
				reportBean.setHCustomerCode(bean.getCustomerUserEmpId());
				reportBean.setListLastRun("");
				if(runByMap!=null && runByMap.size()>0){
					Object[][]mapObj=runByMap.get(String.valueOf(obj[i][1]));
					if(mapObj!=null && mapObj.length>0){
						reportBean.setListLastRun(String.valueOf(mapObj[0][2]));
					}
				}
				
				reportBean.setHPublishDate(String.valueOf(bean.getPublishDate()));
				
				
				//SET DATA INTO LIST
				list.add(reportBean);
			}
			//SET DATA INTO BAIN REPORT
			bean.setReportList(list);
		}
		else{
			//GET CHILD CODE FROM CLIENT USER
			
			String code = bean.getAccountCode();
			System.out.println("ACC=CODE++++"+bean.getAccountCode());
			System.out.println("getCustomerUserEmpId=CODE++++"+bean.getCustomerUserEmpId());
			try {
				if (!(code.equals(""))) {
					code = bean.getAccountCode(); 
					
				} else { 
					code = bean.getCustomerUserEmpId();
				}
				
				/*if ((code.equals(""))) {
						code = bean.getCustomerUserEmpId();
				}*/ 
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			String selQuery="SELECT NVL(ACCOUNT_CHILD_CODE,0) FROM CR_CLIENT_USERS WHERE CRUSER_CODE="+code;
			
			Object[][]child_obj=getSqlModel().getSingleResult(selQuery);
			
			String accChildCode="";
			
			try {
				if (String.valueOf(child_obj[0][0]).equals("")) {
					accChildCode = "0";
				} else {
					accChildCode = String.valueOf(child_obj[0][0]);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			for (int i = 0; i < child_obj.length; i++) {
				try {
					///String aa = String.valueOf(child_obj[i][0]);
					String[] str = accChildCode.split(",");
					if (str != null && str.length > 0) {
						for (int j = 0; j < str.length; j++) {
							accChildCode = String.valueOf(str[j]);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			/*
			
			if(child_obj!=null&&child_obj.length>0){
				if(child_obj!=null&&child_obj.length>0){
						accChildCode+=","+String.valueOf(child_obj[0][0]);
				}
			}*/
			query=" SELECT DISTINCT CR_ACC_REPORT_MAPP.REPORT_CODE,CR_REPORT_MASTER.REPORT_NAME,CR_REPORT_MASTER.GROUP_NAME "
				+"  ,CR_REPORT_MASTER.REPORT_ICON,CR_REPORT_MASTER.REPORT_DESC FROM CR_ACC_REPORT_MAPP "
				+"  INNER JOIN CR_REPORT_MASTER ON(CR_REPORT_MASTER.REPORT_CODE=CR_ACC_REPORT_MAPP.REPORT_CODE)"
				+"  WHERE ACCOUNT_CODE IN(SELECT ACCOUNT_CODE FROM CR_CLIENT_MASTER CONNECT BY PRIOR DECODE(PARENT_CODE,ACCOUNT_CODE,NULL,PARENT_CODE)=ACCOUNT_CODE START WITH ACCOUNT_CODE ="+accChildCode+"  ) ORDER BY CR_ACC_REPORT_MAPP.REPORT_CODE";
			obj=getSqlModel().getSingleResult(query);
			if(obj!=null && obj.length>0){
				ArrayList list=new ArrayList();
				for (int i = 0; i < obj.length; i++) {
					try {
						CustomerAccountInfo reportBean = new CustomerAccountInfo();
						
						String code1 = bean.getCustCode();
						if(!(code1.equals(""))){
							code1 = bean.getCustCode();
						}else{
							code1 = bean.getCustomerUserEmpId();
						}
						
						reportBean.setListAccountCode(String.valueOf(code1));
						reportBean.setListReportCode(String.valueOf(obj[i][0]));
						reportBean.setListReportName(String.valueOf(obj[i][1]));
						reportBean.setListGroupName(String.valueOf(obj[i][2]));
						reportBean.setListAccountID(bean.getAccountID());
						reportBean.setListIcon(String.valueOf(obj[i][3]));
						reportBean.setListReportDesc(String.valueOf(obj[i][4]));
						reportBean
								.setHCustomerCode(bean.getCustomerUserEmpId());
						reportBean.setListLastRun("");
						if (runByMap != null && runByMap.size() > 0) {
							Object[][] mapObj = runByMap.get(String
									.valueOf(obj[i][0]));
							if (mapObj != null && mapObj.length > 0) {
								reportBean.setListLastRun(String
										.valueOf(mapObj[0][2]));
							}
						}
						
						reportBean.setHPublishDate(String.valueOf(bean.getPublishDate()));
						
						
						list.add(reportBean);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				bean.setReportList(list);
			}
		}//end of method
		
		/**
		 * AET ACCOUNT LOGO IF NULL
		 * bean.setAccountLogo
		 */
		if(bean.getAccountLogo().trim().equals("")){
			String logoQUery="SELECT NVL(ACCOUNT_LOGO,' ') FROM CR_CLIENT_MASTER WHERE ACCOUNT_CODE IN(SELECT PARENT_CODE FROM CR_CLIENT_MASTER WHERE ACCOUNT_CODE="+accountCode+" )";
			Object[][]logoObj=getSqlModel().getSingleResult(logoQUery);
			if(logoObj!=null && logoObj.length>0){
				bean.setAccountLogo(String.valueOf(logoObj[0][0]));
			}
		}
		
		/**
		 * 
		 * 
		 * SET DEFERRED CALL STATISTICS
		 */
		String fromWeekQuery="SELECT TO_CHAR((SYSDATE -7),'dd-mm-yyyy') startdayofweek, TO_CHAR(SYSDATE,'dd-mm-yyyy') endofweek FROM dual";
		Object[][]weekObj=getSqlModel().getSingleResult(fromWeekQuery);
		String fromDate="";
		String toDate="";
		if(weekObj!=null&& weekObj.length>0){
			fromDate=String.valueOf(weekObj[0][0]);
			toDate=String.valueOf(weekObj[0][1]);
		}
		
		String performanceQuery=" SELECT  NVL(SUM(BACKD_CUST_RESCH),0), NVL(SUM(BACKD_CUST_NA),0), NVL(SUM(BACKD_ENGG_NA),0), NVL(SUM(BACKD_PART_NA),0) "
								+"	FROM CR_PERFORMANCE_SUMMARY WHERE PS_DATE>=TO_DATE('"+fromDate+"' ,'DD-MM-YYYY') AND PS_DATE<=TO_DATE('"+toDate+"' ,'DD-MM-YYYY') AND ACCOUNT_ID='"+bean.getAccountCode().trim()+"'";
		Object[][]perFormanceObj=getSqlModel().getSingleResult(performanceQuery);
		String bachCutResh="0";
		String bachCustNA="0";
		String bachEnggCut="0";
		String bachPartNA="0";
		if(perFormanceObj!=null&&perFormanceObj.length>0){
			bachCutResh=String.valueOf(perFormanceObj[0][0]);
			bachCustNA=String.valueOf(perFormanceObj[0][1]);
			bachEnggCut=String.valueOf(perFormanceObj[0][2]);
			bachPartNA=String.valueOf(perFormanceObj[0][3]);
		}
		
		String graphString="<chart caption='Deferred call statistics' palette='4' pieRadius='100' slicingDistance='5' decimals='0' animation='1' showPercentValues='1' use3DLighting='1' enableSmartLabels='1' enableRotation='1'  bgAngle='360' showBorder='0' startingAngle='70' skipOverlapLabels='1'>";
		graphString+="<set label='Cust Resch' value='"+bachCutResh+"'  isSliced='1'/>";
		graphString+="<set label='Cust unavail' value='"+bachCustNA+"'/>";
		graphString+="<set label='Eng unavail' value='"+bachEnggCut+"'  isSliced='1'/>";
		graphString+="<set label='Part unavail' value='"+bachPartNA+"'/>";
		graphString+="</chart>";
		request.setAttribute("graphString", graphString);
		String[]DATE_FORMATE=new String[7];
		try {
			//String fromDate = "27-06-2012";
			String date = fromDate;
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"dd-MM-yyyy");
			java.util.Date dt = df.parse(date);
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(dt);
			int DAY_OF_MONTH = cal
					.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
			
			String str_date[]=fromDate.split("-");
			String DAY=str_date[0];
			String MONTH=str_date[1];
			String YEAR=str_date[2];
			//System.out.println("DAY_OF_MONTH________::"+DAY_OF_MONTH);
			for (int i = 0; i < 7; i++) {
				DATE_FORMATE[i]=setDayFormate(DAY)+"-"+setDayFormate(MONTH)+"-"+YEAR;
			//	System.out.println("DATE_FORMATE[i]________::"+DATE_FORMATE[i]);
				if(Integer.parseInt(DAY)<DAY_OF_MONTH){
					DAY=String.valueOf(Integer.parseInt(DAY)+1);
				}else{
					/*DAY="1";
					MONTH=String.valueOf(Integer.parseInt(MONTH)+1);*/
					DAY = "1";
					if (Integer.parseInt(MONTH)<12){
					MONTH = String.valueOf(Integer.parseInt(MONTH) + 1);
					}
					else
					{
						MONTH="1";
						YEAR=String.valueOf(Integer.parseInt(YEAR)+1);
					}
				}		
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		/**
		 * SET DAILY PERFORMANCE STATISTICS
		 */
		String column3DQuery=" SELECT TO_CHAR(PS_DATE,'DD-MM-YYYY'), NVL(SUM(VOL_OPEN_CB),0), NVL(SUM(VOL_TOT_CC),0) "
							+"	FROM CR_PERFORMANCE_SUMMARY WHERE PS_DATE>=TO_DATE('"+fromDate+"' ,'DD-MM-YYYY')"
							+"	AND PS_DATE<=TO_DATE('"+toDate+"' ,'DD-MM-YYYY') AND ACCOUNT_ID='"+bean.getAccountCode().trim()+"' GROUP BY PS_DATE";
		HashMap<String, Object[][]> column3DMap = getSqlModel().getSingleResultMap(column3DQuery,0,2);
		
		String first="Open Calls";
		String second="Closed Calls";
		//if()
		String column3DCharts="<chart caption='Daily call statistics' shownames='1' showvalues='0' decimals='0' numberPrefix=''><categories>";
		column3DCharts+="<category label='"+String.valueOf(DATE_FORMATE[0])+"'/>" +
				"<category label='"+String.valueOf(DATE_FORMATE[1])+"'/>" +
				"<category label='"+String.valueOf(DATE_FORMATE[2])+"'/>" +
				"<category label='"+String.valueOf(DATE_FORMATE[3])+"'/>" +
				"<category label='"+String.valueOf(DATE_FORMATE[4])+"'/>" +
				"<category label='"+String.valueOf(DATE_FORMATE[5])+"'/>" +
				"<category label='"+String.valueOf(DATE_FORMATE[6])+"'/>" +
				"</categories>";

		column3DCharts+="<dataset seriesName='"+first+"' color='F6BD0F' showValues='0'>";			
		String column3DCharts_1="<dataset seriesName='"+second+"' color='8BBA00' showValues='0'>";
		if(DATE_FORMATE!=null && DATE_FORMATE.length>0){
				for (int i = 0; i <  DATE_FORMATE.length; i++) {
					if(column3DMap!=null&&column3DMap.size()>0){
						Object[][]columnobj=column3DMap.get(String.valueOf(DATE_FORMATE[i]));
						if(columnobj!=null&&columnobj.length>0){
							column3DCharts+="<set value='"+String.valueOf(columnobj[0][1])+"'/>";	
							column3DCharts_1+="<set value='"+String.valueOf(columnobj[0][2])+"'/>";	
						}
						else{
							column3DCharts+="<set value='"+String.valueOf(0)+"'/>";
							column3DCharts_1+="<set value='"+String.valueOf(0)+"'/>";
						}
					}
					else{
						column3DCharts+="<set value='"+String.valueOf(0)+"'/>";
						column3DCharts_1+="<set value='"+String.valueOf(0)+"'/>";
					}
				}
			}
		else{			
			column3DCharts+="<set value='"+String.valueOf(20)+"'/>" +
						"<set value='"+String.valueOf(0)+"'/>" +
						"<set value='"+String.valueOf(0)+"'/>" +
						"<set value='"+String.valueOf(0)+"'/>" +
						"<set value='"+String.valueOf(0)+"'/>" +
						"<set value='"+String.valueOf(0)+"'/>" +
						"<set value='"+String.valueOf(0)+"'/>" ;
			column3DCharts_1+="<set value='"+String.valueOf(20)+"'/>" +
						"<set value='"+String.valueOf(0)+"'/>" +
						"<set value='"+String.valueOf(0)+"'/>" +
						"<set value='"+String.valueOf(0)+"'/>" +
						"<set value='"+String.valueOf(0)+"'/>" +
						"<set value='"+String.valueOf(0)+"'/>" +
						"<set value='"+String.valueOf(0)+"'/>" ;
		}
		column3DCharts+="</dataset>";
		column3DCharts_1+="</dataset>";	
		column3DCharts=column3DCharts+column3DCharts_1+"</chart>";
		request.setAttribute("column3DCharts", column3DCharts);
	}//end of method
	/**
	 * Purpose : This method is used to get date in format
	 * @param input
	 * @return string
	 */
	public static String setDayFormate(String input){
		String result=""+input;
		if(Integer.parseInt(input)<10){
			result="0"+Integer.parseInt(input);
		}		
		return result;
	}//end of method
	/**
	 * Purpose : This method is used to insert data into history table 
	 * @param bean
	 */
	public void insertReportHistory(CustomerAccountInfo bean) {
		String insertQuery="INSERT INTO CR_REPORT_HISTORY(HISTORY_CODE, CUSTOMER_CODE, GENEARTE_DATE, REPORT_CODE,ACCOUNT_CODE) "
							+"	VALUES((SELECT NVL(MAX(HISTORY_CODE),0)+1 FROM CR_REPORT_HISTORY),?,SYSDATE,?,?)";
		Object[][]obj=new Object[1][3];
		System.out.println("user code--g---"+bean.getCustCode());
		
		String code = bean.getCustCode();
		if(!(code.equals(""))){
			obj[0][0]=code;
		} else {
			obj[0][0]=bean.getCustomerUserEmpId();
		}
		
		
		obj[0][1]=bean.getReportCode();
		obj[0][2]=bean.getAccountCode();
		getSqlModel().singleExecute(insertQuery,obj);
		
		/*for (int i = 0; i < obj.length; i++) {
		for (int j = 0; j < obj[i].length; j++) {
			logger.info("insertReportHistory---[" + i + "][" + j
					+ "]  " + obj[i][j]);
		}
	}*/
		
	}//end of method
	/**
	 * Purpose : This method is used to get account code from history 
	 * @param customerUserEmpId
	 * @param bean
	 * @return
	 */
	public String getAccountCodeFromHistory(String customerUserEmpId,CustomerAccountInfo bean) {
		
		String selConfQuery="SELECT NVL(ACCOUNT_CHILD_CODE,0) FROM CR_CLIENT_USERS WHERE CRUSER_CODE="+customerUserEmpId;
		Object[][]conf_obj=getSqlModel().getSingleResult(selConfQuery);
		String accChildCode="0";
		if(conf_obj!=null&&conf_obj.length>0){
			if(conf_obj!=null&&conf_obj.length>0){
					accChildCode+=","+String.valueOf(conf_obj[0][0]);
			}
		}
		//GET DATA INFORMATION FROM CLIENT MASTER
		String query="SELECT CR_REPORT_HISTORY.HISTORY_CODE,CR_REPORT_HISTORY.ACCOUNT_CODE,CR_CLIENT_MASTER.ACCOUNT_NAME,CR_CLIENT_MASTER.ACCOUNT_ID,NVL(CR_CLIENT_MASTER.ACCOUNT_LOGO,' ') FROM CR_REPORT_HISTORY " 
					+"  INNER JOIN CR_CLIENT_MASTER ON(CR_CLIENT_MASTER.ACCOUNT_CODE=CR_REPORT_HISTORY.ACCOUNT_CODE) "
					+" WHERE CR_REPORT_HISTORY.CUSTOMER_CODE="+customerUserEmpId+" AND CR_REPORT_HISTORY.ACCOUNT_CODE IN("+accChildCode+")  ORDER BY CR_REPORT_HISTORY.HISTORY_CODE DESC ";
		Object[][]obj=getSqlModel().getSingleResult(query);
		String result="";
		if(obj!=null&& obj.length>0){
			bean.setAccountCode(String.valueOf(obj[0][1]));
			bean.setAccountName(String.valueOf(obj[0][2]));
			bean.setAccountID(String.valueOf(obj[0][3]));
			bean.setAccountLogo(String.valueOf(obj[0][4]).trim());
			result=String.valueOf(obj[0][1]);
			
		}
		else{
			/*String selQuery="SELECT NVL(ACCOUNT_CHILD_CODE,0) FROM CR_CLIENT_USERS WHERE CRUSER_CODE="+bean.getCustomerUserEmpId();
			obj=getSqlModel().getSingleResult(selQuery);
			String accChildCode="0";
			if(obj!=null&&obj.length>0){
				accChildCode=String.valueOf(obj[0][0]);
			}*/
			query=" SELECT NVL(ACCOUNT_ID,' '),ACCOUNT_CODE,NVL(ACCOUNT_NAME,' '),NVL(ACCOUNT_ID,' ') ,NVL(ACCOUNT_LOGO,' ')  FROM CR_CLIENT_MASTER  "
						 +" WHERE IS_ACTIVE='Y' AND PARENT_FLAG='N' AND (PARENT_CODE IN("+accChildCode+")) ";

			query+=" UNION SELECT NVL(ACCOUNT_ID,' '),ACCOUNT_CODE,NVL(ACCOUNT_NAME,' '),NVL(ACCOUNT_ID,' ') ,NVL(ACCOUNT_LOGO,' ')  FROM CR_CLIENT_MASTER  "
				 +" WHERE IS_ACTIVE='Y' AND (ACCOUNT_CODE IN("+accChildCode+")) ";
			Object[][]accObj=getSqlModel().getSingleResult(query);
			if(accObj!=null&& accObj.length>0){
				bean.setAccountCode(String.valueOf(accObj[0][1]));
				bean.setAccountName(String.valueOf(accObj[0][2]));
				bean.setAccountID(String.valueOf(accObj[0][3]));
				bean.setAccountLogo(String.valueOf(accObj[0][4]));
				result=String.valueOf(accObj[0][1]);				
			}
		}
		return result;
	}//end of method
	/**
	 * Purpose : This method is used to change password
	 * @param bean
	 * @return boolean
	 */
	public boolean changePassWord(CustomerAccountInfo bean) {
		// TODO Auto-generated method stub
		boolean result =false;
		String customerCode=bean.getCustomerUserEmpId();
		String password=bean.getCustomerNewPassword();
		String oldPassword=bean.getCustomerOldPassword();
		String encryptPwd="";
		String oldEncryptPwd="";
		try {
			encryptPwd = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(password
					.trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			oldEncryptPwd = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(oldPassword
					.trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String selectQuery="SELECT CRUSER_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE=? AND PASSWORD=?";
			Object[]data=new Object[2];
			data[0]=customerCode;
			data[1]=oldEncryptPwd;
			
		Object[][]passwordObj=getSqlModel().getSingleResult(selectQuery,data);
		if(passwordObj!=null && passwordObj.length>0){
			String updateQuery="UPDATE CR_CLIENT_USERS SET PASSWORD=? WHERE CRUSER_CODE=?";
			Object[][]obj=new Object[1][2];
			obj[0][0]=encryptPwd;
			obj[0][1]=customerCode;
			result=getSqlModel().singleExecute(updateQuery,obj);
		}else{
			return false;
		}		
		return result;
	}//end of method
	/**This method is used to get MultipleAccReport List.
	 * @param bean : CustomerAccountInfo
	 * @param request
	 * @param loginName
	 */
	public void getMultipleAccReportList(CustomerAccountInfo bean,
			HttpServletRequest request, String loginName) {
		try {
			//String loginName=bean.getLoginName();
			String query = "SELECT CR_CLIENT_USERS.ACCOUNT_CODE , CR_CLIENT_MASTER.ACCOUNT_NAME,CR_CLIENT_MASTER.ACCOUNT_ID,CR_CLIENT_USERS.CRUSER_CODE "
							+" FROM CR_CLIENT_USERS "
							+" INNER JOIN CR_CLIENT_MASTER ON(CR_CLIENT_MASTER.ACCOUNT_CODE=CR_CLIENT_USERS.ACCOUNT_CODE)"
							+" WHERE CR_CLIENT_USERS.EMAIL_ID= '"+loginName+"' AND CR_CLIENT_USERS.IS_ACTIVE='Y' ";
			Object[][] obj = getSqlModel().getSingleResult(query);
			String result = "";
			if (obj != null && obj.length > 0) {
				ArrayList list=new ArrayList();
				for (int i = 0; i < obj.length; i++) {
					CustomerAccountInfo reportBean=new CustomerAccountInfo();
					
					reportBean.setAccountCode(String.valueOf(obj[i][0]));
					reportBean.setAccountName(String.valueOf(obj[i][1]));
					reportBean.setAccountID(String.valueOf(obj[i][2]));
					reportBean.setCustomerCode(String.valueOf(obj[i][3]));
					reportBean.setCustomerUserEmpId(reportBean.getCustomerUserEmpId());
					//SET DATA INTO LIST
					list.add(reportBean);
				}
				//SET DATA INTO BAIN REPORT
				bean.setReportList(list);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**This method is used to call PublishDate.
	 * @param bean : CustomerAccountInfo
	 * @param request
	 */
	public void callPublishDate(CustomerAccountInfo bean,
			HttpServletRequest request) {
		try {
			
			String code = bean.getAccountCode(); 
			if((code.equals(""))){
				code = bean.getCustomerUserEmpId();
			}
			System.out.println("bean.getAccCode()==="+bean.getAccCode());
			
			System.out.println("bean.getAccountCode()==="+bean.getAccountCode());
			
			System.out.println("bean.getCustomerUserEmpId()==="+bean.getCustomerUserEmpId());
			
			String accCode = "SELECT CRUSER_CODE, ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE = "+bean.getCustomerUserEmpId();
			Object[][]obj=getSqlModel().getSingleResult(accCode);
			
			String publishDataQuery = "SELECT MAX(TO_CHAR(PS_DATE,'DD-MM-YYYY')) FROM CR_PERFORMANCE_SUMMARY WHERE ACCOUNT_ID = "
					+ String.valueOf(obj[0][1]) + " AND IS_PUBLISHED='Y'";
			
			Object[][] publishDataObj = getSqlModel().getSingleResult(
					publishDataQuery);
			if(String.valueOf(publishDataObj[0][0]).equals("")){
				
				bean.setPublishDate("");
			}else{
				
				bean.setPublishDate(checkNull(String.valueOf(publishDataObj[0][0])));
			}
			/*if (publishDataObj != null && publishDataObj.length > 0) {
				System.out.println("PU&BLISH DATE IF");
				bean.setPublishDate(checkNull(String.valueOf(publishDataObj[0][0])));

			}else{
				System.out.println("PU&BLISH DATE ELSE");
				bean.setPublishDate("");
			}*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	/**This method is used to call Multiple List PublishDate.
	 * @param bean : CustomerAccountInfo
	 * @param request
	 */
	public void callMultiplePublishDate(CustomerAccountInfo bean,
			HttpServletRequest request) {
		try {
			
			String accCode = "SELECT CRUSER_CODE, ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE = "+bean.getCustCode();
			Object[][]obj=getSqlModel().getSingleResult(accCode);
			
			String publishDataQuery = "SELECT MAX(TO_CHAR(PS_DATE,'DD-MM-YYYY')) FROM CR_PERFORMANCE_SUMMARY WHERE ACCOUNT_ID = "
					+  String.valueOf(obj[0][1]) + " AND IS_PUBLISHED='Y'";
			
			Object[][] publishDataObj = getSqlModel().getSingleResult(
					publishDataQuery);
			if(String.valueOf(publishDataObj[0][0]).equals("")){
				
				bean.setPublishDate("");
			}else{
				
				bean.setPublishDate(checkNull(String.valueOf(publishDataObj[0][0])));
			}
			/*if (publishDataObj != null && publishDataObj.length > 0) {
				System.out.println("PU&BLISH DATE IF");
				bean.setPublishDate(checkNull(String.valueOf(publishDataObj[0][0])));

			}else{
				System.out.println("PU&BLISH DATE ELSE");
				bean.setPublishDate("");
			}*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
