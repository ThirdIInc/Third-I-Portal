package org.paradyne.model.CR;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.CR.CRMAccountInfo;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
/**Created on 17th Jan 2012.
 * @author aa0476
 */
public class CRMAccountInfoModel extends ModelBase {
	/**
	 * Purpose : This method is used to get customer account list
	 * @param bean
	 */
	public void getCustomerAccList(final CRMAccountInfo bean) {
		/**
		 * SET ACCOUNT INFORMATION
		 */

		String accQuery = "SELECT ACCOUNT_CODE,NVL(ACCOUNT_ID,' '),NVL(ACCOUNT_NAME,' ') ,NVL(CONTACT_NAME,' '),NVL(EMAIL_ID,' ') "
				+ " FROM CR_CLIENT_MASTER WHERE PARENT_FLAG='Y' AND (ACCOUNT_CODE IN(SELECT ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE="
				+ bean.getUserEmpId()
				+ ")"
				+ "  OR PARENT_CODE  IN(SELECT ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE="
				+ bean.getUserEmpId() + "))";

		accQuery = "SELECT ACCOUNT_CODE,NVL(ACCOUNT_ID,' '),NVL(ACCOUNT_NAME,' ') ,NVL(CONTACT_NAME,' '),NVL(EMAIL_ID,' ') "
				+ " FROM CR_CLIENT_MASTER WHERE PARENT_FLAG='Y' "
				+ " AND (ACCOUNT_CODE IN(SELECT ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE="
				+ bean.getUserEmpId() + "))";
		Object[][] accObj = getSqlModel().getSingleResult(accQuery);

		String reportQuery = "SELECT ACCOUNT_CODE,NVL(ACCOUNT_ID,' '),NVL(ACCOUNT_NAME,' ') ,NVL(CONTACT_NAME,' '),NVL(EMAIL_ID,' ') "
				+ " FROM CR_CLIENT_MASTER WHERE PARENT_FLAG='N' "
				+ " AND (PARENT_CODE IN(SELECT ACCOUNT_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE="
				+ bean.getUserEmpId() + "))";
		Object[][] reportObj = getSqlModel().getSingleResult(reportQuery);

		bean.setAccountList(null);
		if (accObj != null && accObj.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < accObj.length; i++) {
				CRMAccountInfo subBean = new CRMAccountInfo();
				//SET ACCOUNT CODE
				subBean.setListAccountCode(String.valueOf(accObj[i][0]));
				//SET ACCOUNT ID
				subBean.setListAccountID(String.valueOf(accObj[i][1]));
				//SET ACCOUNT NAME
				subBean.setListAccountName(String.valueOf(accObj[i][2]));
				//SET CONTACT NAME
				subBean.setListContactName(String.valueOf(accObj[i][3]));
				//SET EMAIL ID
				subBean.setListEmailID(String.valueOf(accObj[i][4]));
				ArrayList reportlist = new ArrayList();
				for (int j = 0; j < reportObj.length; j++) {
					CRMAccountInfo reportBean = new CRMAccountInfo();
					//SET LIST ACCOUNT CODE
					reportBean.setListAccountCode(String
							.valueOf(reportObj[i][0]));
					reportBean
							.setListAccountID(String.valueOf(reportObj[i][1]));
					//SET ACCOUNT LIST NAME
					reportBean.setListAccountName(String
							.valueOf(reportObj[i][2]));
					reportBean.setListContactName(String
							.valueOf(reportObj[i][3]));
					reportBean.setListEmailID(String.valueOf(reportObj[i][4]));
					reportlist.add(reportBean);
				}
				subBean.setAccountReportList(reportlist);
				list.add(subBean);
			}
			bean.setAccountList(list);
		}

	}
	/**
	 * Purpose : This method is used to get account report details information
	 * @param bean
	 * @param accountCode
	 * @param request
	 */
	public void getAccReport(final CRMAccountInfo bean, final String accountCode,
			HttpServletRequest request) { 
		//GET THE ACCOUNT DETAILS
		/*String query = " SELECT CR_ACC_REPORT_MAPP.ACCOUNT_CODE,CR_ACC_REPORT_MAPP.REPORT_CODE,CR_REPORT_MASTER.REPORT_NAME,CR_REPORT_MASTER.GROUP_NAME "
				+ "  ,CR_REPORT_MASTER.REPORT_ICON,CR_REPORT_MASTER.REPORT_DESC FROM CR_ACC_REPORT_MAPP "
				+ "  INNER JOIN CR_REPORT_MASTER ON(CR_REPORT_MASTER.REPORT_CODE=CR_ACC_REPORT_MAPP.REPORT_CODE)"
				+ "  WHERE ACCOUNT_CODE="
				+ accountCode
				+ " ORDER BY CR_ACC_REPORT_MAPP.REPORT_CODE";*/
		 
		String query = "SELECT DISTINCT CR_ACC_REPORT_MAPP.REPORT_CODE,CR_REPORT_MASTER.REPORT_NAME," +
		"CR_REPORT_MASTER.GROUP_NAME   ,CR_REPORT_MASTER.REPORT_ICON,CR_REPORT_MASTER.REPORT_DESC " +
		"FROM CR_ACC_REPORT_MAPP   " +
		"INNER JOIN CR_REPORT_MASTER ON(CR_REPORT_MASTER.REPORT_CODE=CR_ACC_REPORT_MAPP.REPORT_CODE) "
		+" WHERE ACCOUNT_CODE IN (SELECT ACCOUNT_CODE FROM CR_CLIENT_MASTER CONNECT BY ACCOUNT_CODE=PRIOR CASE WHEN PARENT_CODE=ACCOUNT_CODE THEN NULL ELSE PARENT_CODE END  " 
		+" START WITH ACCOUNT_CODE="+accountCode+") ORDER BY CR_ACC_REPORT_MAPP.REPORT_CODE";
		
		Object[][] obj = getSqlModel().getSingleResult(query);
		//GET ACCOUNT HISTORY DETAILS
		String runByQuery = "SELECT MAX(HISTORY_CODE),REPORT_CODE ,TO_CHAR(MAX(GENEARTE_DATE),'DD-MM-YYYY HH24:MI') "
				+ "	 FROM CR_REPORT_HISTORY WHERE CUSTOMER_CODE="
				+ bean.getUserEmpId()
				+ " AND REPORT_CODE IS NOT NULL"
				+ "	   GROUP BY REPORT_CODE  ";

		HashMap<String, Object[][]> runByMap = getSqlModel()
				.getSingleResultMap(runByQuery, 1, 2);
		bean.setReportList(null);
		if (obj != null && obj.length > 0) {
			ArrayList list = new ArrayList();
			for (int i = 0; i < obj.length; i++) {
				CRMAccountInfo reportBean = new CRMAccountInfo();
				//SET LIST ACCOUNT CODE
				
				String code1 = String.valueOf(obj[i][0]);
				
				if(!(code1.equals(""))){
					code1 = String.valueOf(obj[i][0]);
				}else{
					code1 = bean.getCustomerUserEmpId();
				}
				
				reportBean.setListAccountCode(String.valueOf(code1));
				//SET LIST REPORT CODE
				reportBean.setListReportCode(String.valueOf(obj[i][0]));
				//SET LIST REPORT NAME
				reportBean.setListReportName(String.valueOf(obj[i][1]));
				//SET LIST GROUP NAME
				reportBean.setListGroupName(String.valueOf(obj[i][2]));
				//SET LIST ACCOUNT ID
				reportBean.setListAccountID(bean.getAccountID());
				//SET LIST ICON
				reportBean.setListIcon(String.valueOf(obj[i][3]));
				//SET LIST REPORT DESC
				reportBean.setListReportDesc(String.valueOf(obj[i][4]));
				//SET CUSTOMER CODE
				reportBean.sethCustomerCode(bean.getUserEmpId());
				//SET LAST RUN BY
				reportBean.setListLastRun("");
				if (runByMap != null && runByMap.size() > 0) {
					Object[][] mapObj = runByMap.get(String.valueOf(obj[i][1]));
					if (mapObj != null && mapObj.length > 0) {
						//SET LAST RUN BY
						reportBean.setListLastRun(String.valueOf(mapObj[0][2]));
					}
				}//end if 
				list.add(reportBean);
			}//end for loop

			bean.setReportList(list);
		}

		/**
		 * SET ACCOUNT LOGO IF NULL bean.setAccountLogo
		 */
		if (bean.getAccountLogo().trim().equals("")) {
			String logoQUery = "SELECT NVL(ACCOUNT_LOGO,' ') FROM CR_CLIENT_MASTER WHERE ACCOUNT_CODE IN(SELECT PARENT_CODE FROM CR_CLIENT_MASTER WHERE ACCOUNT_CODE="
					+ accountCode + " )";
			Object[][] logoObj = getSqlModel().getSingleResult(logoQUery);
			if (logoObj != null && logoObj.length > 0) {
				bean.setAccountLogo(String.valueOf(logoObj[0][0]));
			}
		}

		/**
		 * SET Deferred Call Statistics
		 */
		String fromWeekQuery = "SELECT TO_CHAR((SYSDATE -7),'dd-mm-yyyy') startdayofweek, TO_CHAR(SYSDATE,'dd-mm-yyyy') endofweek FROM dual";
		Object[][] weekObj = getSqlModel().getSingleResult(fromWeekQuery);
		String fromDate = "";
		String toDate = "";
		if (weekObj != null && weekObj.length > 0) {
			fromDate = String.valueOf(weekObj[0][0]);
			toDate = String.valueOf(weekObj[0][1]);
		} 

		String performanceQuery = " SELECT  NVL(SUM(BACKD_CUST_RESCH),0), NVL(SUM(BACKD_CUST_NA),0), NVL(SUM(BACKD_ENGG_NA),0), NVL(SUM(BACKD_PART_NA),0) "
				+ "	FROM CR_PERFORMANCE_SUMMARY WHERE PS_DATE>=TO_DATE('"
				+ fromDate
				+ "' ,'DD-MM-YYYY') AND PS_DATE<=TO_DATE('"
				+ toDate
				+ "' ,'DD-MM-YYYY') AND ACCOUNT_ID='"
				+ bean.getAccountCode().trim() + "'";
		Object[][] perFormanceObj = getSqlModel().getSingleResult(
				performanceQuery);
		String bachCutResh = "0";
		String bachCustNA = "0";
		String bachEnggCut = "0";
		String bachPartNA = "0";
		if (perFormanceObj != null && perFormanceObj.length > 0) {
			bachCutResh = String.valueOf(perFormanceObj[0][0]);
			bachCustNA = String.valueOf(perFormanceObj[0][1]);
			bachEnggCut = String.valueOf(perFormanceObj[0][2]);
			bachPartNA = String.valueOf(perFormanceObj[0][3]);
		}

		String graphString = "<chart caption='Deferred call statistics' palette='4' pieRadius='100' slicingDistance='5' decimals='0' animation='1' showPercentValues='1' use3DLighting='1' enableSmartLabels='1' enableRotation='1'  bgAngle='360' showBorder='0' startingAngle='70' skipOverlapLabels='1'>";
		graphString += "<set label='Cust Resch' value='" + bachCutResh
				+ "'  isSliced='1'/>";
		graphString += "<set label='Cust unavail' value='" + bachCustNA
				+ "'/>";
		graphString += "<set label='Eng unavail' value='" + bachEnggCut
				+ "'  isSliced='1'/>";
		graphString += "<set label='Part unavail' value='" + bachPartNA
				+ "'/>";

		graphString += "</chart>";
		request.setAttribute("graphString", graphString);

		String[] DATE_FORMATE = new String[7];
		try {
			// String fromDate = "27-06-2012";
			String date = fromDate;
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"dd-MM-yyyy");
			java.util.Date dt = df.parse(date);
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(dt);
			int DAY_OF_MONTH = cal
					.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); 

			String str_date[] = fromDate.split("-");
			String DAY = str_date[0];
			String MONTH = str_date[1];
			String YEAR = str_date[2];
			 System.out.println("YEAR________::"+YEAR);
			for (int i = 0; i < 7; i++) {
				DATE_FORMATE[i] = setDayFormate(DAY) + "-"
						+ setDayFormate(MONTH) + "-" + YEAR;
			//	 System.out.println("DATE_FORMATE[i]________::"+DATE_FORMATE[i]);
				if (Integer.parseInt(DAY) < DAY_OF_MONTH) {
					DAY = String.valueOf(Integer.parseInt(DAY) + 1);
				} else {
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
		 * SET DAILY CALL STATISTICS
		 */
		String column3DQuery = " SELECT TO_CHAR(PS_DATE,'DD-MM-YYYY'), NVL(SUM(VOL_OPEN_CB),0), NVL(SUM(VOL_TOT_CC),0) "
				+ "	FROM CR_PERFORMANCE_SUMMARY WHERE PS_DATE>=TO_DATE('"
				+ fromDate
				+ "' ,'DD-MM-YYYY')"
				+ "	AND PS_DATE<=TO_DATE('"
				+ toDate
				+ "' ,'DD-MM-YYYY') AND ACCOUNT_ID='"
				+ bean.getAccountCode().trim() + "' GROUP BY PS_DATE";
		HashMap<String, Object[][]> column3DMap = getSqlModel()
				.getSingleResultMap(column3DQuery, 0, 2);

		String first = "Open Calls";
		String second = "Closed Calls";
		// if()
		String column3DCharts = "<chart caption='Daily call statistics' shownames='1' showvalues='0' decimals='0' numberPrefix=''><categories>";
		column3DCharts += "<category label='" + String.valueOf(DATE_FORMATE[0])
				+ "'/>" + "<category label='" + String.valueOf(DATE_FORMATE[1])
				+ "'/>" + "<category label='" + String.valueOf(DATE_FORMATE[2])
				+ "'/>" + "<category label='" + String.valueOf(DATE_FORMATE[3])
				+ "'/>" + "<category label='" + String.valueOf(DATE_FORMATE[4])
				+ "'/>" + "<category label='" + String.valueOf(DATE_FORMATE[5])
				+ "'/>" + "<category label='" + String.valueOf(DATE_FORMATE[6])
				+ "'/>" + "</categories>";
		column3DCharts += "<dataset seriesName='" + first
				+ "' color='F6BD0F' showValues='0'>";
		String column3DCharts_1 = "<dataset seriesName='" + second
				+ "' color='8BBA00' showValues='0'>";
		if (DATE_FORMATE != null && DATE_FORMATE.length > 0) {
			for (int i = 0; i < DATE_FORMATE.length; i++) {
				if (column3DMap != null && column3DMap.size() > 0) {
					Object[][] columnobj = column3DMap.get(String
							.valueOf(DATE_FORMATE[i]));
					if (columnobj != null && columnobj.length > 0) {
						column3DCharts += "<set value='"
								+ String.valueOf(columnobj[0][1]) + "'/>";
						column3DCharts_1 += "<set value='"
								+ String.valueOf(columnobj[0][2]) + "'/>";
					} else {
						column3DCharts += "<set value='" + String.valueOf(0)
								+ "'/>";
						column3DCharts_1 += "<set value='" + String.valueOf(0)
								+ "'/>";
					}
				} else {
					column3DCharts += "<set value='" + String.valueOf(0)
							+ "'/>";
					column3DCharts_1 += "<set value='" + String.valueOf(0)
							+ "'/>";
				}
			}
		} else {
			column3DCharts += "<set value='" + String.valueOf(20) + "'/>"
					+ "<set value='" + String.valueOf(0) + "'/>"
					+ "<set value='" + String.valueOf(0) + "'/>"
					+ "<set value='" + String.valueOf(0) + "'/>"
					+ "<set value='" + String.valueOf(0) + "'/>"
					+ "<set value='" + String.valueOf(0) + "'/>"
					+ "<set value='" + String.valueOf(0) + "'/>";
			column3DCharts_1 += "<set value='" + String.valueOf(20) + "'/>"
					+ "<set value='" + String.valueOf(0) + "'/>"
					+ "<set value='" + String.valueOf(0) + "'/>"
					+ "<set value='" + String.valueOf(0) + "'/>"
					+ "<set value='" + String.valueOf(0) + "'/>"
					+ "<set value='" + String.valueOf(0) + "'/>"
					+ "<set value='" + String.valueOf(0) + "'/>";
		}

		column3DCharts += "</dataset>";
		column3DCharts_1 += "</dataset>";
		column3DCharts = column3DCharts + column3DCharts_1 + "</chart>";

		request.setAttribute("column3DCharts", column3DCharts);
	}
	/**. 
	 * Purpose : This method is used to set day format 
	 * @param input
	 * @return string
	 */ 
	public static String setDayFormate(String input) {
		String result = "" + input;
		if (Integer.parseInt(input) < 10) {
			result = "0" + Integer.parseInt(input);
		}
		return result;
	}
	/**.
	 * Purpose : This method is used to insert report history data
	 * @param bean
	 */
	public void insertReportHistory(final CRMAccountInfo bean) {
		String insertQuery = "INSERT INTO CR_REPORT_HISTORY(HISTORY_CODE, CUSTOMER_CODE, GENEARTE_DATE, REPORT_CODE,ACCOUNT_CODE,CRM_FLAG) "
				+ "	VALUES((SELECT NVL(MAX(HISTORY_CODE),0)+1 FROM CR_REPORT_HISTORY),?,SYSDATE,?,?,'Y')";
		Object[][] obj = new Object[1][3];
		obj[0][0] = bean.getUserEmpId();
		obj[0][1] = bean.getReportCode();
		obj[0][2] = bean.getAccountCode();
		//INSERT QUERY CR_REPORT_HISTORY TABLE
		getSqlModel().singleExecute(insertQuery, obj);
	}
	/**.
	 * Purpose : This method is used to get account history
	 * @param requestAccountCode
	 * @param bean
	 * @return string
	 */
	public String getAccountCodeFromHistory(final String requestAccountCode,
			final CRMAccountInfo bean) {
		
		//GET ACCOUNT MAPPING DATA
		String query = "SELECT CR_ACC_CRM_MAPP.MAPP_CODE,CR_ACC_CRM_MAPP.ACCOUNT_CODE,CR_CLIENT_MASTER.ACCOUNT_NAME,CR_CLIENT_MASTER.ACCOUNT_ID,CR_CLIENT_MASTER.ACCOUNT_LOGO "
				+ "	 FROM CR_ACC_CRM_MAPP "
				+ "	 INNER JOIN CR_CLIENT_MASTER ON(CR_CLIENT_MASTER.ACCOUNT_CODE=CR_ACC_CRM_MAPP .ACCOUNT_CODE) "
				+ "	 WHERE CR_ACC_CRM_MAPP.ACCOUNT_CODE=" + requestAccountCode;
		Object[][] obj = getSqlModel().getSingleResult(query);
		String result = "";
		if (obj != null && obj.length > 0) {
			//SET ACCOUNT CODE
			bean.setAccountCode(String.valueOf(obj[0][1]));
			//SET ACCOUNT NAME
			bean.setAccountName(String.valueOf(obj[0][2]));
			//SET ACCOUNT ID
			bean.setAccountID(String.valueOf(obj[0][3]));
			//SET ACCOUNT LOGO
			bean.setAccountLogo(String.valueOf(obj[0][4]));
			result = String.valueOf(obj[0][1]);
		} else {
			query = "SELECT CR_ACC_CRM_MAPP.MAPP_CODE,CR_ACC_CRM_MAPP.ACCOUNT_CODE,CR_CLIENT_MASTER.ACCOUNT_NAME,CR_CLIENT_MASTER.ACCOUNT_ID,CR_CLIENT_MASTER.ACCOUNT_LOGO "
					+ "	 FROM CR_ACC_CRM_MAPP "
					+ "	 INNER JOIN CR_CLIENT_MASTER ON(CR_CLIENT_MASTER.ACCOUNT_CODE=CR_ACC_CRM_MAPP .ACCOUNT_CODE) "
					+ "	 WHERE    CR_ACC_CRM_MAPP.ACCOUNT_CODE IN(SELECT PARENT_CODE FROM CR_CLIENT_MASTER WHERE ACCOUNT_CODE="
					+ requestAccountCode + " )";
			obj = getSqlModel().getSingleResult(query);
			if (obj != null && obj.length > 0) {
				bean.setAccountLogo(String.valueOf(obj[0][4]));
				result = String.valueOf(obj[0][1]);
			}
			/**
			 *GET AACOUNT REPORT MAPPING
			 */
			String clientquery = "SELECT CR_CLIENT_MASTER.ACCOUNT_LOGO,CR_CLIENT_MASTER.ACCOUNT_CODE,CR_CLIENT_MASTER.ACCOUNT_NAME, "
					+ "  CR_CLIENT_MASTER.ACCOUNT_ID,CR_CLIENT_MASTER.ACCOUNT_LOGO  "
					+ "  FROM CR_CLIENT_MASTER WHERE CR_CLIENT_MASTER.ACCOUNT_CODE="
					+ requestAccountCode;
			Object[][] client_obj = getSqlModel().getSingleResult(clientquery);
			if (client_obj != null && client_obj.length > 0) {
				//SET ACCOUNT CODE
				bean.setAccountCode(String.valueOf(client_obj[0][1]));
				//SET ACCOUNT NAME
				bean.setAccountName(String.valueOf(client_obj[0][2]));
				//SET ACCOUNT ID
				bean.setAccountID(String.valueOf(client_obj[0][3]));
			}
		}
		return result;
	}
	/**
	 * Purpose : This method is used to change password
	 * @param bean
	 * @return boolean
	 */
	public boolean changePassWord(CRMAccountInfo bean) {
		// TODO Auto-generated method stub
		boolean result = false;
		String customerCode = bean.getUserEmpId();
		String password = bean.getCustomerNewPassword();
		String oldPassword = bean.getCustomerOldPassword();
		String encryptPwd = "";
		String oldEncryptPwd = "";
		try {
			//GET INCRYPT FORL
			encryptPwd = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(password
					.trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			//GET ENCRYPT FORM OLD PASSWORD
			oldEncryptPwd = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
					.encrypt(oldPassword.trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
		//GET CLIENT CODE FROM CLIENT MASTER
		String selectQuery = "SELECT CRUSER_CODE FROM CR_CLIENT_USERS WHERE CRUSER_CODE=? AND PASSWORD=?";
		Object[] data = new Object[2];
		data[0] = customerCode;
		data[1] = oldEncryptPwd;

		Object[][] passwordObj = getSqlModel().getSingleResult(selectQuery,
				data);
		if (passwordObj != null && passwordObj.length > 0) {
			String updateQuery = "UPDATE CR_CLIENT_USERS SET PASSWORD=? WHERE CRUSER_CODE=?";
			Object[][] obj = new Object[1][2];
			obj[0][0] = encryptPwd;
			obj[0][1] = customerCode;
			result = getSqlModel().singleExecute(updateQuery, obj);
		} else {
			return false;
		}
		return result;
	}

}
