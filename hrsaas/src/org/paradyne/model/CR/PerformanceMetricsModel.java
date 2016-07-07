package org.paradyne.model.CR;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.CR.PerformanceMetrics;
import org.paradyne.lib.ModelBase; 
/*
 * Date:12.06.2012
 */
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;


public class PerformanceMetricsModel extends ModelBase {
	/** logger. */
	private Logger logger = Logger.getLogger(PerformanceMetricsModel.class);
	PerformanceMetrics tm = null;

	/**
	 * to check null value
	 * 
	 * @param result 
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}

	
	/** for inserting the data in CR_PERFORMANCE_METRICS
	 * @param bean PerformanceMetrics
	 * @param request HttpServletRequest
	 * @return result
	 */ 
	public boolean savePerformanceMetrics(PerformanceMetrics bean,
			HttpServletRequest request,String userEmpID) {
		boolean result = false;

		try {
			
			String Query1 = "DELETE FROM CR_PERFORMANCE_METRICS WHERE ACCOUNT_ID ="+bean.getAccountCode();
			//result = getSqlModel("POOL_D1").singleExecute(Query1);
			result = getSqlModel("POOL_D1").singleExecute(Query1);

			Object invDecIDObj[][] = //getSqlModel("POOL_D1")
				getSqlModel("POOL_D1")
					.getSingleResult(
							"SELECT isnull(MAX(CR_PERFORMANCE_METRICS.PM_ID),0) FROM CR_PERFORMANCE_METRICS");
			int invDecIncrementedID = Integer.parseInt("" + invDecIDObj[0][0]);

			String[] colName = request.getParameterValues("metricAbbr");
			String[] greenValStart = request
					.getParameterValues("greenValStart");
			String[] greenValEnd = request.getParameterValues("greenValEnd");
			String[] yellowValueStart = request
					.getParameterValues("yellowValueStart");
			String[] yellowValueEnd = request
					.getParameterValues("yellowValueEnd");
			String[] redValueStart = request
					.getParameterValues("redValueStart");
			String[] redValueEnd = request.getParameterValues("redValueEnd");
			String[] metricCode = request.getParameterValues("metricCode");
			//25 Sept 2012
			String[] crmFlag = request.getParameterValues("crmFlagHidden");
			String[] clientFlag = request.getParameterValues("clientFlagHidden");
			int k = 0;
			Object performanceMetricsDtlObj[][] = new Object[colName.length][13];
			
			for (int i = 0; i < colName.length; i++) {

				performanceMetricsDtlObj[k][0] = ++invDecIncrementedID;
				performanceMetricsDtlObj[k][1] = bean.getAccountCode();

				/*
				 * if (bean.getIsApplyChild().equals("true")) {
				 * performanceMetricsDtlObj[k][2] = "Y"; } else {
				 * performanceMetricsDtlObj[k][2] = "N"; }
				 */

				performanceMetricsDtlObj[k][2] = colName[i];
				performanceMetricsDtlObj[k][3] = greenValStart[i];
				performanceMetricsDtlObj[k][4] = checkNull(String
						.valueOf(greenValEnd[i]));
				performanceMetricsDtlObj[k][5] = yellowValueStart[i];
				performanceMetricsDtlObj[k][6] = yellowValueEnd[i];
				performanceMetricsDtlObj[k][7] = redValueStart[i];
				performanceMetricsDtlObj[k][8] = redValueEnd[i];
				performanceMetricsDtlObj[k][9] = userEmpID;
				performanceMetricsDtlObj[k][10] = metricCode[i];
				
				performanceMetricsDtlObj[k][11] = crmFlag[i];
				performanceMetricsDtlObj[k][12] = clientFlag[i];

				k++;
			}
			String insertRecordsQuery = "INSERT INTO CR_PERFORMANCE_METRICS(PM_ID, ACCOUNT_ID, "
					+ "COLUMN_NAME, GREEN_VAL_START, GREEN_VAL_END, YELLOW_VAL_START, "
					+ "YELLOW_VAL_END, RED_VAL_START, RED_VAL_END,RECORD_CREATEBY, RECORD_CREATEON,METRICS_CODE,CRM_FLAG, CLIENT_FLAG)"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,getDate(),?,?,?)";
			result = getSqlModel("POOL_D1").singleExecute(insertRecordsQuery,
					performanceMetricsDtlObj);

			
			/*for (int i = 0; i < performanceMetricsDtlObj.length; i++) {
			for (int j = 0; j < performanceMetricsDtlObj[i].length; j++) {
				logger.info("performanceMetricsDtlObj[" + i + "][" + j
						+ "]  " + performanceMetricsDtlObj[i][j]);
			}
		}*/
			
			
			
			// If Apply All child is selected start
			if (bean.getIsApplyChild().equals("true")) {
				String childCode = bean.getChildCode();
				
				Object applyChildIDObj[][] = getSqlModel("POOL_D1")
						.getSingleResult(
								"SELECT isnull(MAX(CR_PERFORMANCE_METRICS.PM_ID),0) FROM CR_PERFORMANCE_METRICS");
				int applyChildIncrementedID = Integer.parseInt("" + applyChildIDObj[0][0]);

				String[] applyChildcolName = request.getParameterValues("metricAbbr");
				String[] applyChildgreenValStart = request
						.getParameterValues("greenValStart");
				String[] applyChildgreenValEnd = request.getParameterValues("greenValEnd");
				String[] applyChildyellowValueStart = request
						.getParameterValues("yellowValueStart");
				String[] applyChildyellowValueEnd = request
						.getParameterValues("yellowValueEnd");
				String[] applyChildredValueStart = request
						.getParameterValues("redValueStart");
				String[] applyChildredValueEnd = request.getParameterValues("redValueEnd");
				String[] applyChildmetricCode = request.getParameterValues("metricCode");
				
				String[] applyCrmFlag = request.getParameterValues("crmFlagHidden");
				String[] applyClientFlag = request.getParameterValues("clientFlagHidden");
				
				
				int m = 0;
				Object applyChildDtlObj[][] = new Object[colName.length][12];
				
				for (int i = 0; i < colName.length; i++) {

					applyChildDtlObj[m][0] = ++applyChildIncrementedID;

					/*
					 * if (bean.getIsApplyChild().equals("true")) {
					 * performanceMetricsDtlObj[k][2] = "Y"; } else {
					 * performanceMetricsDtlObj[k][2] = "N"; }
					 */

					applyChildDtlObj[m][1] = applyChildcolName[i];
					applyChildDtlObj[m][2] = applyChildgreenValStart[i];
					applyChildDtlObj[m][3] = checkNull(String.valueOf(applyChildgreenValEnd[i]));
					applyChildDtlObj[m][4] = applyChildyellowValueStart[i];
					applyChildDtlObj[m][5] = applyChildyellowValueEnd[i];
					applyChildDtlObj[m][6] = applyChildredValueStart[i];
					applyChildDtlObj[m][7] = applyChildredValueEnd[i];
					applyChildDtlObj[m][8] = userEmpID;
					applyChildDtlObj[m][9] = applyChildmetricCode[i];
					applyChildDtlObj[m][10] = applyCrmFlag[i];
					applyChildDtlObj[m][11] = applyClientFlag[i];

					m++;
				}
				String deleteChildQuery = "DELETE FROM CR_PERFORMANCE_METRICS WHERE ACCOUNT_ID ="+bean.getAccountCode();
				result = getSqlModel("POOL_D1").singleExecute(deleteChildQuery);
				
				
				String insertapplyChildRecordsQuery = "INSERT INTO CR_PERFORMANCE_METRICS(PM_ID, ACCOUNT_ID, "
						+ "COLUMN_NAME, GREEN_VAL_START, GREEN_VAL_END, YELLOW_VAL_START, "
						+ "YELLOW_VAL_END, RED_VAL_START, RED_VAL_END,RECORD_CREATEBY, RECORD_CREATEON,METRICS_CODE,CRM_FLAG, CLIENT_FLAG)"
						+ " VALUES(?,"+bean.getAccountCode()+",?,?,?,?,?,?,?,?,getDate(),?,?,?)";
				result = getSqlModel("POOL_D1").singleExecute(insertapplyChildRecordsQuery,
						applyChildDtlObj);

				
				String[]str=childCode.split(",");
				if(str!=null && str.length>0){
					for (int j = 0; j < str.length; j++) {
						String code=str[j];
						
						 Query1 = "DELETE FROM CR_PERFORMANCE_METRICS WHERE ACCOUNT_ID ="+code;
						result = getSqlModel("POOL_D1").singleExecute(Query1);
						
						insertRecordsQuery = "INSERT INTO CR_PERFORMANCE_METRICS(PM_ID, ACCOUNT_ID, "
							+ "COLUMN_NAME, GREEN_VAL_START, GREEN_VAL_END, YELLOW_VAL_START, "
							+ "YELLOW_VAL_END, RED_VAL_START, RED_VAL_END,RECORD_CREATEBY, RECORD_CREATEON,METRICS_CODE,CRM_FLAG, CLIENT_FLAG)"
							+ " VALUES(?,"+code+",?,?,?,?,?,?,?,?,getDate(),?,?,?)";
						
						result = getSqlModel("POOL_D1").singleExecute(insertRecordsQuery,
								applyChildDtlObj);
					}
				}
			}else{
				System.out.println("Apply ALL child records: not selected");
			}
			// If Apply All child is selected end
			
			// If selected child start
			
			String selectchildCode = bean.getSelectChildCode();
			if(!selectchildCode.equals("")){
				
				Object selectChildIDObj[][] = getSqlModel("POOL_D1")
						.getSingleResult(
								"SELECT isnull(MAX(CR_PERFORMANCE_METRICS.PM_ID),0) FROM CR_PERFORMANCE_METRICS");
				int selectChildIncrementedID = Integer.parseInt("" + selectChildIDObj[0][0]);
		
				String[] selectChildcolName = request.getParameterValues("metricAbbr");
				String[] selectChildgreenValStart = request
						.getParameterValues("greenValStart");
				String[] selectChildgreenValEnd = request.getParameterValues("greenValEnd");
				String[] selectChildyellowValueStart = request
						.getParameterValues("yellowValueStart");
				String[] selectChildyellowValueEnd = request
						.getParameterValues("yellowValueEnd");
				String[] selectChildredValueStart = request
						.getParameterValues("redValueStart");
				String[] selectChildredValueEnd = request.getParameterValues("redValueEnd");
				String[] selectChildmetricCode = request.getParameterValues("metricCode");
				
				String[] selectCrmFlag = request.getParameterValues("crmFlagHidden");
				String[] selectClientFlag = request.getParameterValues("clientFlagHidden");
				
				
				int m = 0;
				Object selectChildDtlObj[][] = new Object[colName.length][12];
				
				for (int i = 0; i < colName.length; i++) {
					selectChildDtlObj[m][0] = ++selectChildIncrementedID;
		
					/*
					 * if (bean.getIsselectChild().equals("true")) {
					 * performanceMetricsDtlObj[k][2] = "Y"; } else {
					 * performanceMetricsDtlObj[k][2] = "N"; }
					 */
		
					selectChildDtlObj[m][1] = selectChildcolName[i];
					selectChildDtlObj[m][2] = selectChildgreenValStart[i];
					selectChildDtlObj[m][3] = checkNull(String
							.valueOf(selectChildgreenValEnd[i]));
					selectChildDtlObj[m][4] = selectChildyellowValueStart[i];
					selectChildDtlObj[m][5] = selectChildyellowValueEnd[i];
					selectChildDtlObj[m][6] = selectChildredValueStart[i];
					selectChildDtlObj[m][7] = selectChildredValueEnd[i];
					selectChildDtlObj[m][8] = userEmpID;
					selectChildDtlObj[m][9] = selectChildmetricCode[i];
		
					selectChildDtlObj[m][10] = selectCrmFlag[i];
					selectChildDtlObj[m][11] = selectClientFlag[i];
					m++;
				}
				String deleteChildQuery = "DELETE FROM CR_PERFORMANCE_METRICS WHERE ACCOUNT_ID ="+bean.getAccountCode();
				result = getSqlModel("POOL_D1").singleExecute(deleteChildQuery);
				
				
				String insertselectChildRecordsQuery = "INSERT INTO CR_PERFORMANCE_METRICS(PM_ID, ACCOUNT_ID, "
						+ "COLUMN_NAME, GREEN_VAL_START, GREEN_VAL_END, YELLOW_VAL_START, "
						+ "YELLOW_VAL_END, RED_VAL_START, RED_VAL_END,RECORD_CREATEBY, RECORD_CREATEON,METRICS_CODE,CRM_FLAG, CLIENT_FLAG)"
						+ " VALUES(?,"+bean.getAccountCode()+",?,?,?,?,?,?,?,?,getDate(),?,?,?)";
				result = getSqlModel("POOL_D1").singleExecute(insertselectChildRecordsQuery,
						selectChildDtlObj);
		
				
				String[]str=selectchildCode.split(",");
				if(str!=null && str.length>0){
					for (int j = 0; j < str.length; j++) {
						String code=str[j];
						 Query1 = "DELETE FROM CR_PERFORMANCE_METRICS WHERE ACCOUNT_ID ="+code;
						result = getSqlModel("POOL_D1").singleExecute(Query1);
						
						insertRecordsQuery = "INSERT INTO CR_PERFORMANCE_METRICS(PM_ID, ACCOUNT_ID, "
							+ "COLUMN_NAME, GREEN_VAL_START, GREEN_VAL_END, YELLOW_VAL_START, "
							+ "YELLOW_VAL_END, RED_VAL_START, RED_VAL_END,RECORD_CREATEBY, RECORD_CREATEON,METRICS_CODE,CRM_FLAG, CLIENT_FLAG)"
							+ " VALUES(?,"+code+",?,?,?,?,?,?,?,?,getDate(),?,?,?)";
						
						result = getSqlModel("POOL_D1").singleExecute(insertRecordsQuery,
								selectChildDtlObj);
					}
				}
			}
			// If selected child end
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**This is used to set perticular performance metrics details clicked from list page.
	 * @param bean : PerformanceMetrics
	 * @param requestID
	 */
	public void setperformanceMetricsDtl(PerformanceMetrics bean,
			String requestID) {
		String invQuery = "SELECT PM_ID, CR_PERFORMANCE_METRICS.ACCOUNT_ID,CR_CLIENT_MASTER.ACCOUNT_NAME, "
				+ "COLUMN_NAME, GREEN_VAL_START, GREEN_VAL_END, YELLOW_VAL_START, YELLOW_VAL_END, "
				+ "	RED_VAL_START, RED_VAL_END "
				+ "	FROM CR_PERFORMANCE_METRICS "
				+ "	INNER JOIN CR_CLIENT_MASTER ON (CR_CLIENT_MASTER.ACCOUNT_CODE = CR_PERFORMANCE_METRICS.ACCOUNT_ID)"
				+ "	WHERE CR_PERFORMANCE_METRICS.ACCOUNT_ID= " + requestID;

		Object[][] repData = getSqlModel("POOL_D1").getSingleResult(invQuery);
		if (repData != null && repData.length > 0) {
			for (int i = 0; i < repData.length; i++) {
				PerformanceMetrics bean1 = new PerformanceMetrics();
				bean1.setParentCode(checkNull((String.valueOf(repData[i][1]))));
				bean1.setParentName(checkNull((String.valueOf(repData[i][2]))));
				bean1
						.setHiddenColName(checkNull((String
								.valueOf(repData[i][3]))));
				bean1
						.setGreenValStart(checkNull((String
								.valueOf(repData[i][4]))));
				bean1
						.setGreenValEnd(checkNull((String
								.valueOf(repData[i][5]))));

			}
		}
	}

	/**This function is used to set Performance metircs details for perticular id from list page.
	 * @param bean : PerformanceMetrics
	 * @param request
	 */
	public void getSelectRecord(PerformanceMetrics bean,
			HttpServletRequest request) {
		try {
			String invQuery = "SELECT PM_ID, CR_PERFORMANCE_METRICS.ACCOUNT_ID, "
					+ " COLUMN_NAME, GREEN_VAL_START, GREEN_VAL_END, YELLOW_VAL_START, YELLOW_VAL_END, "
					+ " RED_VAL_START, RED_VAL_END ,CR_PERFORMANCE_METRICS.METRICS_CODE,CR_PERFORMANCE_METRICS_MASTER.METRICS_NAME "
					+ "  FROM CR_PERFORMANCE_METRICS "
					+ " LEFT JOIN CR_PERFORMANCE_METRICS_MASTER ON (CR_PERFORMANCE_METRICS_MASTER.METRICS_CODE = CR_PERFORMANCE_METRICS.METRICS_CODE)"
					+ " WHERE CR_PERFORMANCE_METRICS.ACCOUNT_ID= "
					+ bean.getParentCode();
			Object[][] repData = getSqlModel("POOL_D1").getSingleResult(invQuery);
			if (repData != null && repData.length > 0) {

				ArrayList<PerformanceMetrics> list = new ArrayList<PerformanceMetrics>();

				for (int i = 0; i < repData.length; i++) {
					PerformanceMetrics bean1 = new PerformanceMetrics();
					// bean1.setParentCode(checkNull((String
					// .valueOf(repData[i][1]))));
					// bean1.setParentName(checkNull((String
					// .valueOf(repData[i][2]))));
					bean1.setMetricAbbr(checkNull((String
							.valueOf(repData[i][2]))));
					bean1.setGreenValStart(checkNull((String
							.valueOf(repData[i][3]))));
					bean1.setGreenValEnd(checkNull((String
							.valueOf(repData[i][4]))));

					bean1.setYellowValueStart(checkNull((String
							.valueOf(repData[i][5]))));
					bean1.setYellowValueEnd(checkNull((String
							.valueOf(repData[i][6]))));

					bean1.setYellowValueStart(checkNull((String
							.valueOf(repData[i][7]))));
					bean1.setYellowValueEnd(checkNull((String
							.valueOf(repData[i][8]))));

					bean1.setMetricCode(checkNull((String
							.valueOf(repData[i][9]))));
					bean1.setMetricName(checkNull((String
							.valueOf(repData[i][10]))));

					list.add(bean1);

				}
				bean.setMetricsList(list);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**This function is used to set list page.
	 * @param bean : PerformanceMetrics
	 * @param request
	 * @param searchMessage 
	 * @param string 
	 */
	public String performanceMetricsDetails(PerformanceMetrics bean,
			HttpServletRequest request, String searchMessage, String dashBoardID,String userEmpID, String userType, String parentFlag) {

		Object[][] listData = null;
		Object[][] accountData = null;
		ArrayList list = new ArrayList();

		 
		String accountCode= "SELECT ACCOUNT_CODE FROM CR_EMP_CLIENT_MAPP WHERE USER_ID="+userEmpID+" AND DASHBOARD_ID ="+dashBoardID+" and USER_TYPE='"+userType+"'";
		accountData = getSqlModel().getSingleResult(accountCode);
		
		
		String accountNumber="";
		for (int i = 0; i < accountData.length; i++) {
			accountNumber+= accountData[i][0] +",";
		}
		accountNumber=accountNumber.substring(0, accountNumber.length()-1);
		String selQuery="";
		if(parentFlag.equals("true")){ 
		
		selQuery="SELECT A.ACCOUNT_CODE,A.ACCOUNT_ID,A.ACCOUNT_NAME,'Y'||A.PARENT_FLAG,B.ACCOUNT_CODE "
					+" FROM CR_CLIENT_MASTER A,CR_CLIENT_MASTER B "
					+" WHERE NVL(A.PARENT_CODE,A.ACCOUNT_CODE)=B.ACCOUNT_CODE "
					+" AND (A.ACCOUNT_CODE IN( "+accountNumber+") "  
					+"  OR A.PARENT_CODE IN( "+accountNumber+") )AND A.IS_ACTIVE='Y' and A.PARENT_FLAG='Y' ";
		bean.setParentFlagHiddenChk("true");
		}
		else{
			selQuery="SELECT A.ACCOUNT_CODE,A.ACCOUNT_ID,A.ACCOUNT_NAME,'Y'||A.PARENT_FLAG,B.ACCOUNT_CODE "
				+" FROM CR_CLIENT_MASTER A,CR_CLIENT_MASTER B "
				+" WHERE NVL(A.PARENT_CODE,A.ACCOUNT_CODE)=B.ACCOUNT_CODE "
				+" AND (A.ACCOUNT_CODE IN( "+accountNumber+") "  
				+"  OR A.PARENT_CODE IN( "+accountNumber+") )AND A.IS_ACTIVE='Y'";
			bean.setParentFlagHiddenChk("false");
		}
		
		if (searchMessage != null && !searchMessage.trim().equals("")) {
			selQuery += " AND UPPER(A.ACCOUNT_NAME ||A.ACCOUNT_ID) LIKE UPPER (ltrim(rtrim('%"+ searchMessage.trim() + "%')))   ";
		}
		selQuery +=" ORDER BY B.ACCOUNT_CODE*10000+(case when A.PARENT_FLAG='Y' then 0 else A.ACCOUNT_CODE end)";
		
		listData = getSqlModel().getSingleResult(selQuery);

		String[] pageIndexApproved = Utility.doPaging(bean.getMyPage(),
				listData.length, 20);
		if (pageIndexApproved == null) {
			pageIndexApproved[0] = "0";
			pageIndexApproved[1] = "20";
			pageIndexApproved[2] = "1";
			pageIndexApproved[3] = "1";
			pageIndexApproved[4] = "";
		}

		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndexApproved[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndexApproved[3])));
		if (pageIndexApproved[4].equals("1"))
			bean.setMyPage("1");
		
		
		
		bean.setIteratorList(null);
		if (listData != null && listData.length > 0) {
			bean.setListLength(true);
			bean.setTotalRecords(String.valueOf(listData.length));// display the total record in the list
			for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
					.parseInt(pageIndexApproved[1]); i++) {
				PerformanceMetrics beanItt = new PerformanceMetrics();
				beanItt
						.setAccountCode(checkNull(String
								.valueOf(listData[i][0])));
				beanItt.setAccountId(checkNull(String.valueOf(listData[i][1])));
				beanItt
						.setAccountName(checkNull(String
								.valueOf(listData[i][2])));
				beanItt
				.setParentFlag(checkNull(String
						.valueOf(listData[i][3])));
				list.add(beanItt);
			}
			bean.setIteratorList(list);
		}
		return accountNumber;

	}

	/**This function is used to set Performance metircs details for perticular id from list page.
	 * @param bean : PerformanceMetrics
	 * @param requestID
	 */
	public void callForPerformanceMetrics(PerformanceMetrics bean,
			String requestID) {
		try {
			
			String accountQuery = "SELECT ACCOUNT_CODE, ACCOUNT_NAME , 'Y'+PARENT_FLAG FROM CR_CLIENT_MASTER WHERE ACCOUNT_CODE ="
				+ requestID;

			Object[][] accountObj = getSqlModel("POOL_D1").getSingleResult(accountQuery);
			if (accountObj != null && accountObj.length > 0) {
				bean.setAccountCode(String.valueOf(accountObj[0][0]));
				bean.setAccountName(String.valueOf(accountObj[0][1]));
				bean.setSelectParentFlag(String.valueOf(accountObj[0][2]));
			}
			
			String childQuery = "SELECT ACCOUNT_CODE  FROM CR_CLIENT_MASTER WHERE PARENT_CODE = "+ requestID;
			Object[][] childObj = getSqlModel("POOL_D1").getSingleResult(childQuery);
			String rptCode = "";
			if (childObj != null && childObj.length > 0) {
				for (int i = 0; i < childObj.length; i++) {
					rptCode = rptCode + childObj[i][0] + ",";
				}
				rptCode = rptCode.substring(0, (rptCode.length() - 1));
			}
			if (childObj != null && childObj.length > 0) {
				bean.setChildCode(String.valueOf(rptCode));
			
			}
			
//			String query = "SELECT METRICS_CODE, METRICS_NAME, METRICS_ABBR FROM CR_PERFORMANCE_METRICS_MASTER ORDER BY METRICS_CODE ASC";
//			Object[][] metData = getSqlModel("POOL_D1").getSingleResult(query);
//			if (metData != null && metData.length > 0) {
//				
//				ArrayList<PerformanceMetrics> list = new ArrayList<PerformanceMetrics>();
//				int count = 0;
//				for (int i = 0; i < metData.length; i++) {
//					PerformanceMetrics beanItt = new PerformanceMetrics();
//					beanItt.setMetricCode(checkNull(String.valueOf(metData[i][0]))); 
//					beanItt.setMetricName(checkNull(String.valueOf(metData[i][1]))); 
//					beanItt.setMetricAbbr(checkNull(String.valueOf(metData[i][2]))); 
//			
//					list.add(beanItt);
//				}
//				bean.setMetricsList(list);
//			}
			 
			/*String invQuery = "SELECT PM_ID, CR_PERFORMANCE_METRICS.ACCOUNT_ID, "
				+ " COLUMN_NAME, GREEN_VAL_START, GREEN_VAL_END, YELLOW_VAL_START, YELLOW_VAL_END, "
				+ " RED_VAL_START, RED_VAL_END ,CR_PERFORMANCE_METRICS.METRICS_CODE,CR_PERFORMANCE_METRICS_MASTER.METRICS_NAME "
				+ "  , CR_PERFORMANCE_METRICS.CRM_FLAG, CR_PERFORMANCE_METRICS.CLIENT_FLAG FROM CR_PERFORMANCE_METRICS "
				+ " LEFT JOIN CR_PERFORMANCE_METRICS_MASTER ON (CR_PERFORMANCE_METRICS_MASTER.METRICS_CODE = CR_PERFORMANCE_METRICS.METRICS_CODE)"
				+ " WHERE CR_PERFORMANCE_METRICS.ACCOUNT_ID= "
				+ requestID +" ORDER BY CR_PERFORMANCE_METRICS_MASTER.SRNO ";*/
			
			
			String invQuery = "SELECT PM_ID,CR_PERFORMANCE_METRICS.ACCOUNT_ID,COLUMN_NAME,GREEN_VAL_START," +
					"GREEN_VAL_END,YELLOW_VAL_START,YELLOW_VAL_END,RED_VAL_START, RED_VAL_END ," +
					"CR_PERFORMANCE_METRICS_MASTER.METRICS_CODE,CR_PERFORMANCE_METRICS_MASTER.METRICS_NAME," +
					"CR_PERFORMANCE_METRICS.CRM_FLAG, CR_PERFORMANCE_METRICS.CLIENT_FLAG "
					+"FROM CR_PERFORMANCE_METRICS  "
					+"RIGHT JOIN CR_PERFORMANCE_METRICS_MASTER ON " +
					"(CR_PERFORMANCE_METRICS_MASTER.METRICS_CODE = CR_PERFORMANCE_METRICS.METRICS_CODE and CR_PERFORMANCE_METRICS.ACCOUNT_ID= "+ requestID +" ) " 
					+"ORDER BY CR_PERFORMANCE_METRICS_MASTER.SRNO";
			
			
		Object[][] repData = getSqlModel("POOL_D1").getSingleResult(invQuery);
		if (repData != null && repData.length > 0) {
			ArrayList<PerformanceMetrics> list = new ArrayList<PerformanceMetrics>();
			for (int i = 0; i < repData.length; i++) {
				PerformanceMetrics bean1 = new PerformanceMetrics();
				// bean1.setParentCode(checkNull((String
				// .valueOf(repData[i][1]))));
				// bean1.setParentName(checkNull((String
				// .valueOf(repData[i][2]))));
				bean1.setMetricAbbr(checkNull((String
						.valueOf(repData[i][2]))));
				bean1.setGreenValStart(checkNull((String
						.valueOf(repData[i][3]))));
				bean1.setGreenValEnd(checkNull((String
						.valueOf(repData[i][4]))));

				bean1.setYellowValueStart(checkNull((String
						.valueOf(repData[i][5]))));
				bean1.setYellowValueEnd(checkNull((String
						.valueOf(repData[i][6]))));

				bean1.setRedValueStart(checkNull((String
						.valueOf(repData[i][7]))));
				bean1.setRedValueEnd(checkNull((String
						.valueOf(repData[i][8]))));

				bean1.setMetricCode(checkNull((String
						.valueOf(repData[i][9]))));
				bean1.setMetricName(checkNull((String
						.valueOf(repData[i][10]))));
				
				if (String.valueOf(repData[i][11]).equals("Y")) {
					
					bean1.setCrmFlagHidden("Y");
					///bean1.setCrmFlag("true");
				} else {
					
					bean1.setCrmFlagHidden("N");
					///bean1.setCrmFlag("false");
				}
				
				if (String.valueOf(repData[i][12]).equals("Y")) {
					
					bean1.setClientFlagHidden("Y");
					///bean1.setClientFlag("true");
				} else {
					
					bean1.setClientFlagHidden("N");
					///bean1.setClientFlag("false");
				}

				list.add(bean1); 

			}
			bean.setMetricsList(list);
		}else{
			String query = "SELECT METRICS_CODE, METRICS_NAME, METRICS_ABBR FROM CR_PERFORMANCE_METRICS_MASTER ORDER BY SRNO ASC";
			Object[][] metData = getSqlModel("POOL_D1").getSingleResult(query);
			if (metData != null && metData.length > 0) {
				
				ArrayList<PerformanceMetrics> list = new ArrayList<PerformanceMetrics>();
				int count = 0;
				for (int i = 0; i < metData.length; i++) {
					PerformanceMetrics beanItt = new PerformanceMetrics();
					beanItt.setMetricCode(checkNull(String.valueOf(metData[i][0]))); 
					beanItt.setMetricName(checkNull(String.valueOf(metData[i][1]))); 
					beanItt.setMetricAbbr(checkNull(String.valueOf(metData[i][2]))); 
			
					list.add(beanItt);
				}
				bean.setMetricsList(list);
			}
		}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**This function is used when child check box is selected.
	 * @param bean : PerformanceMetrics
	 * @param request : HttpServletRequest
	 * @return : result
	 */
	public boolean applyChildPerformanceMetrics(PerformanceMetrics bean,
			HttpServletRequest request,String userEmpID) {
		boolean result = false;
		try {
			String childCode = bean.getChildCode();
			
			Object invDecIDObj[][] = getSqlModel("POOL_D1")
					.getSingleResult(
							"SELECT isnull(MAX(CR_PERFORMANCE_METRICS.PM_ID),0) FROM CR_PERFORMANCE_METRICS");
			int invDecIncrementedID = Integer.parseInt("" + invDecIDObj[0][0]);

			String[] colName = request.getParameterValues("metricAbbr");
			String[] greenValStart = request
					.getParameterValues("greenValStart");
			String[] greenValEnd = request.getParameterValues("greenValEnd");
			String[] yellowValueStart = request
					.getParameterValues("yellowValueStart");
			String[] yellowValueEnd = request
					.getParameterValues("yellowValueEnd");
			String[] redValueStart = request
					.getParameterValues("redValueStart");
			String[] redValueEnd = request.getParameterValues("redValueEnd");
			String[] metricCode = request.getParameterValues("metricCode");
			int k = 0;
			Object performanceMetricsDtlObj[][] = new Object[colName.length][10];
			int fileCount = 0;
			for (int i = 0; i < colName.length; i++) {
				performanceMetricsDtlObj[k][0] = ++invDecIncrementedID;
				/*
				 * if (bean.getIsApplyChild().equals("true")) {
				 * performanceMetricsDtlObj[k][2] = "Y"; } else {
				 * performanceMetricsDtlObj[k][2] = "N"; }
				 */
				performanceMetricsDtlObj[k][1] = colName[i];
				performanceMetricsDtlObj[k][2] = greenValStart[i];
				performanceMetricsDtlObj[k][3] = checkNull(String
						.valueOf(greenValEnd[i]));
				performanceMetricsDtlObj[k][4] = yellowValueStart[i];
				performanceMetricsDtlObj[k][5] = yellowValueEnd[i];
				performanceMetricsDtlObj[k][6] = redValueStart[i];
				performanceMetricsDtlObj[k][7] = redValueEnd[i];
				performanceMetricsDtlObj[k][8] = userEmpID;
				performanceMetricsDtlObj[k][9] = metricCode[i];

				k++;
			}
			String Query1 = "DELETE FROM CR_PERFORMANCE_METRICS WHERE ACCOUNT_ID ="+bean.getAccountCode();
			result = getSqlModel("POOL_D1").singleExecute(Query1);
			
			
			String insertRecordsQuery = "INSERT INTO CR_PERFORMANCE_METRICS(PM_ID, ACCOUNT_ID, "
					+ "COLUMN_NAME, GREEN_VAL_START, GREEN_VAL_END, YELLOW_VAL_START, "
					+ "YELLOW_VAL_END, RED_VAL_START, RED_VAL_END,RECORD_CREATEBY, RECORD_CREATEON,METRICS_CODE)"
					+ " VALUES(?,"+bean.getAccountCode()+",?,?,?,?,?,?,?,?,getdate(),?)";
			result = getSqlModel("POOL_D1").singleExecute(insertRecordsQuery,
					performanceMetricsDtlObj);
			
			String[]str=childCode.split(",");
			if(str!=null && str.length>0){
				for (int j = 0; j < str.length; j++) {
					String code=str[j];
					 Query1 = "DELETE FROM CR_PERFORMANCE_METRICS WHERE ACCOUNT_ID ="+code;
					result = getSqlModel("POOL_D1").singleExecute(Query1);
					
					insertRecordsQuery = "INSERT INTO CR_PERFORMANCE_METRICS(PM_ID, ACCOUNT_ID, "
						+ "COLUMN_NAME, GREEN_VAL_START, GREEN_VAL_END, YELLOW_VAL_START, "
						+ "YELLOW_VAL_END, RED_VAL_START, RED_VAL_END,RECORD_CREATEBY, RECORD_CREATEON,METRICS_CODE)"
						+ " VALUES(?,"+code+",?,?,?,?,?,?,?,?,getdate(),?)";
					
					result = getSqlModel("POOL_D1").singleExecute(insertRecordsQuery,
							performanceMetricsDtlObj);
				}
			}
			
			/*if (result) {
				String autoCodeQuery = " SELECT isnull(MAX(PM_ID),0) FROM CR_PERFORMANCE_METRICS ";
				Object[][] idData = getSqlModel("POOL_D1")
						.getSingleResult(autoCodeQuery);
				if (idData != null && idData.length > 0) {
					bean.setPerformanceMetricsId(String.valueOf(idData[0][0]));
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**This is used to set Account Name and Account Code in jsp.
	 * @param bean : PerformanceMetrics
	 * @param requestID
	 */
	public void callForEscalations(PerformanceMetrics bean, String requestID) {
		String accountQuery = "SELECT ACCOUNT_CODE, ACCOUNT_NAME FROM CR_CLIENT_MASTER WHERE ACCOUNT_CODE ="
			+ requestID;

		Object[][] accountObj = getSqlModel("POOL_D1").getSingleResult(accountQuery);
		if (accountObj != null && accountObj.length > 0) {
			bean.setAccountCode(String.valueOf(accountObj[0][0]));
			bean.setAccountName(String.valueOf(accountObj[0][1]));
		}
		
	}

	/**This is used to set next date upto given to date.
	 * @param bean : PerformanceMetrics
	 * @param request : HttpServletRequest
	 * @param fromDate : From Date
	 * @param toDate 
	 */
	public void generateDtlList(PerformanceMetrics bean,
			HttpServletRequest request, String fromDate, String toDate) {

		try {
			String date = fromDate;
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"dd-MM-yyyy");
			java.util.Date dt = df.parse(date);
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(dt);
			int day_of_month = cal
					.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
			ArrayList<Object> dateList = new ArrayList<Object>();
			int cnt = 0;
			int count = 7;
			String frmDate = bean.getFromDate();
			String todate = bean.getToDate();
			
			String[] str1 = frmDate.split("-");
			String[] str2 = todate.split("-");
			
			int frmDay = (Integer.parseInt(str1[0]));
			int toDay = (Integer.parseInt(str2[0]));
			
			int totalDay = (toDay-frmDay)+1;
			
			String escaDateDtl = "SELECT ESCA_ACT FROM CR_PERFORMANCE_SUMMARY " +
					"WHERE  ACCOUNT_ID="+bean.getAccountCode()+"  " +
					"AND PS_DATE >= convert(date,'"+fromDate+"',105)" + 
					" AND PS_DATE <= convert(date,'"+toDate+"',105)";
	
	Object[][] escaDateDtlObj = getSqlModel("POOL_D1").getSingleResult(escaDateDtl);
	if(escaDateDtlObj!=null && escaDateDtlObj.length > 0){
		bean.setDateListLength(true);
		
			
			for (int i = 0; i < totalDay; i++) {
				PerformanceMetrics bean2 = new PerformanceMetrics();
				String[] str = fromDate.split("-");
				int nextDay = (Integer.parseInt(str[0]) + (i));
				int oiginalNextDay = (Integer.parseInt(str[0]) + (i));
				int nextMonth = Integer.parseInt(str[1]);
				if (oiginalNextDay > day_of_month) {
					nextDay = 1 + cnt;
					nextMonth = Integer.parseInt(str[1]) + 1;
					cnt++;
				}
				String newdate = (nextDay < 10 ? "0" + nextDay : nextDay) + "-"
						+ (nextMonth < 10 ? "0" + nextMonth : nextMonth) + "-"
						+ str[2];
				bean2.setDayName(newdate);
				
				String escaDtl = "SELECT ESCA_ACT FROM CR_PERFORMANCE_SUMMARY " +
						"WHERE  ACCOUNT_ID="+bean.getAccountCode()+"  " +
						"AND PS_DATE = convert(date,'"+newdate+"',105)" ;
				
				Object[][] escaDtlObj = getSqlModel("POOL_D1").getSingleResult(escaDtl);
				if(escaDtlObj!=null && escaDtlObj.length > 0){
					bean2.setEscalation(checkNull(String.valueOf(escaDtlObj[0][0])));
				}else{
					bean2.setEscalation("");
				}
				
				dateList.add(bean2);
			}
			
	}else{
		bean.setDateListLength(false);
	}
			bean.setDateList(dateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**This is used to save escalation dtls in CR_PERFORMANCE_SUMMARY.
	 * @param bean : PerformanceMetrics
	 * @param request : HttpServletRequest
	 * @return result
	 */
	public boolean saveEscalationDtl(PerformanceMetrics bean,
			HttpServletRequest request) {
		boolean result = false;
		try {
			String[] escalationValues = request.getParameterValues("escalation");
			String[] date = request.getParameterValues("dayName");
			
			int k = 0;
			Object performanceMetricsDtlObj[][] = new Object[date.length][2];
			
			for (int i = 0; i < date.length; i++) {
				performanceMetricsDtlObj[k][0] = escalationValues[i];
				performanceMetricsDtlObj[k][1] = date[i];
				k++;
			}
			
			/*for (int i = 0; i < performanceMetricsDtlObj.length; i++) {
			for (int j = 0; j < performanceMetricsDtlObj[i].length; j++) {
				logger.info("Escalation DTLS[" + i + "][" + j
						+ "]  " + performanceMetricsDtlObj[i][j]);
			}
		}*/
			
			
			String updateQuery = "UPDATE CR_PERFORMANCE_SUMMARY SET ESCA_ACT =? WHERE PS_DATE=convert(date,?,105)  AND ACCOUNT_ID="+bean.getAccountCode()+" ";
			result = getSqlModel("POOL_D1").singleExecute(updateQuery,
				performanceMetricsDtlObj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**This is used to set Misc Details form CR_MISC_DETAILS in list view.
	 * @param bean : PerformanceMetrics
	 * @param requestID : requestID
	 * @param request : HttpServletRequest
	 */
	public void callForPlanned(PerformanceMetrics bean, String requestID, HttpServletRequest request) {
		String accountQuery = "SELECT ACCOUNT_CODE , ACCOUNT_NAME  FROM CR_CLIENT_MASTER WHERE ACCOUNT_CODE ="
			+ requestID;

		Object[][] accountObj = getSqlModel("POOL_D1").getSingleResult(accountQuery);
		if (accountObj != null && accountObj.length > 0) {
			bean.setAccountCode(String.valueOf(accountObj[0][0]));
			bean.setAccountName(String.valueOf(accountObj[0][1]));
		
		}
		
		String plnQuery = "SELECT  CR_MISC_DETAILS.MISC_ID, convert(varchar(10),EFF_FROM_DATE,105) , "
					+"		convert(varchar(10),EFF_TO_DATE,105), MISC_VAL, MISC_WKND_VAL, CR_MISC_ID ,MISC_NAME "
					+"		FROM CR_MISC_DETAILS "
					+"		INNER JOIN CR_MISC_MASTER ON (CR_MISC_MASTER.MISC_ID = CR_MISC_DETAILS.CR_MISC_ID)"
					+"		WHERE ACCOUNT_CODE  = "+requestID;
			
		Object[][] clientUserObj = getSqlModel("POOL_D1").getSingleResult(plnQuery);
			if (clientUserObj != null && clientUserObj.length > 0) {
				bean.setPlannedListLength(true);
				bean.setTotalRecords(String.valueOf(clientUserObj.length));
				String[] pageIndex = Utility.doPaging(bean.getMyPage(),
						clientUserObj.length, 10);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "10";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					bean.setMyPage("1");
				int k = 0;
				ArrayList<Object> List = new ArrayList<Object>();
				bean.setViewPlannedDtlFlag(true);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
				.parseInt(pageIndex[1]); i++) {
					PerformanceMetrics bean1 = new PerformanceMetrics();
					bean1.setIttMiscId(checkNull((String
							.valueOf(clientUserObj[k][0]))));
					bean1.setIttMiscCode(checkNull((String
							.valueOf(clientUserObj[k][5]))));
					bean1
							.setIttMiscName(checkNull((String
									.valueOf(clientUserObj[k][6]))));
					bean1.setIttFromDate(checkNull((String
							.valueOf(clientUserObj[k][1]))));
					bean1.setIttToDate(checkNull((String
							.valueOf(clientUserObj[k][2]))));
					bean1.setIttMiscValue(checkNull((String
							.valueOf(clientUserObj[k][3]))));
					bean1.setIttMiscWeekendValue(checkNull((String
							.valueOf(clientUserObj[k][4]))));
					
					List.add(bean1);
					k++;
				}
			
				bean.setPlannedList(List);
			}
		
	}

	/**This is used to save misc details in CR_MISC_DETAILS.
	 * @param bean : PerformanceMetrics
	 * @param request : HttpServletRequest
	 * @param fromDate : From Date
	 * @return result
	 */
	public boolean generatePlannedListDtl(PerformanceMetrics bean,
			HttpServletRequest request, String fromDate) {
		boolean result = false;
		try {
			Object addObj[][] = new Object[1][5];
			//addObj[0][0] = bean.getHiddenBankId();
			addObj[0][0] = bean.getAccountCode();
			addObj[0][1] = bean.getMiscCode();
			addObj[0][2] = bean.getFromDate();
			///addObj[0][3] = bean.getToDate();
			addObj[0][3] = bean.getMiscValue();
			addObj[0][4] = bean.getMiscWeekendValue();
			
			String insertQuery = "INSERT INTO CR_MISC_DETAILS"
					+ "(MISC_ID, ACCOUNT_CODE,CR_MISC_ID, EFF_FROM_DATE, MISC_VAL, MISC_WKND_VAL)"
					+ " VALUES((SELECT isnull(MAX(MISC_ID),0)+1 FROM CR_MISC_DETAILS),?,?,convert(date,?,105),?,?)";
			
			result = getSqlModel("POOL_D1").singleExecute(insertQuery, addObj);
			
			if (result) {
				String autoCodeQuery = " SELECT isnull(MAX(MISC_ID),0) FROM CR_MISC_DETAILS ";
				Object[][] data = getSqlModel("POOL_D1").getSingleResult(autoCodeQuery);
				if (data != null && data.length > 0) {
					bean.setHiddencode(String.valueOf(data[0][0]));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

		
	/**This is used to set perticular details when click from list view.
	 * @param bean : PerformanceMetrics
	 * @param request : HttpServletRequest
	 * @param plannedID
	 */
	public void editPlannedLstDtl(PerformanceMetrics bean,
			HttpServletRequest request, String plannedID) {
			try {
				String query = "SELECT CR_MISC_DETAILS.MISC_ID, ACCOUNT_CODE, convert(varchar(10),EFF_FROM_DATE,105), " +
								" MISC_VAL, MISC_WKND_VAL, CR_MISC_ID ,MISC_NAME "
								+" FROm CR_MISC_DETAILS "
								+" INNER JOIN CR_MISC_MASTER ON (CR_MISC_MASTER.MISC_ID = CR_MISC_DETAILS.CR_MISC_ID)"
								+" WHERE CR_MISC_DETAILS.MISC_ID = "+ plannedID;
				
				Object[][] data = getSqlModel("POOL_D1").getSingleResult(query);
				
				bean.setParacode(checkNull((String.valueOf(data[0][0]))));
				bean.setMiscCode(checkNull((String.valueOf(data[0][5]))));
				bean.setMiscName(checkNull((String.valueOf(data[0][6]))));
				bean.setFromDate(checkNull((String.valueOf(data[0][2]))));
			///	bean.setToDate(checkNull((String.valueOf(data[0][3]))));
				bean.setMiscValue(checkNull(String.valueOf(data[0][3])));
				bean.setMiscWeekendValue(String.valueOf(String.valueOf(data[0][4])));
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		
	}
	
	/**This is used to delete perticular details when click from list view.
	 * @param bean : PerformanceMetrics
	 * @param request : HttpServletRequest
	 * @return ID
	 */
	public boolean delPlannedDtl(PerformanceMetrics bean,
			HttpServletRequest request) {
		Object del[][] = null;
		try {
			del = new Object[1][1];
			// to delete the single record after clicking on saving or searching
			// button
			del[0][0] = bean.getParacode();
		} catch(Exception e) {
			// TODO: handle exception
		}
		String deleteQuery = "DELETE FROM CR_MISC_DETAILS WHERE MISC_ID=? ";

		return getSqlModel("POOL_D1").singleExecute(deleteQuery, del);
	}
	
	/**This is used to update perticular record when click from list view.
	 * @param bean : PerformanceMetrics
	 * @param request : HttpServletRequest
	 * @return result
	 */
	public boolean modPlannedListDtl(PerformanceMetrics bean,
			HttpServletRequest request) {
		boolean result = false;
			
		try {
			Object updateObj[][] = new Object[1][5];
			updateObj[0][0] = bean.getAccountCode();
			updateObj[0][1] = bean.getMiscCode();
			updateObj[0][2] = bean.getFromDate();
			updateObj[0][3] = bean.getMiscValue();
			updateObj[0][4] = bean.getMiscWeekendValue();
			
			String insertQuery = "UPDATE CR_MISC_DETAILS SET "
					+ " ACCOUNT_CODE = ? , CR_MISC_ID = ? , EFF_FROM_DATE = convert(date,?,105)  , MISC_VAL  = ? , MISC_WKND_VAL = ?  "
					+ " WHERE MISC_ID = "
					+ bean.getParacode() ;
			
			result = getSqlModel("POOL_D1").singleExecute(insertQuery, updateObj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**This is used to set list of call type from master table CR_CALL_TYPE.
	 * @param bean : PerformanceMetrics
	 */
	public void setCallTypeList(PerformanceMetrics bean) {
		try {
			
			/*String queryDiv = " SELECT CALL_TYPE_ID, CALL_TYPE_DESC ||' - '|| CR_CALL_TYPE.DESCR  FROM CR_CALL_TYPE  " +
					" WHERE CR_CALL_TYPE.CALL_TYPE_ID NOT IN (SELECT CR_CLIENT_CALLTYPE.CALL_TYPE_ID FROM CR_CLIENT_CALLTYPE  WHERE CLIENT_ID ="+bean.getAccountCode() +") " +
					" ORDER BY UPPER(CALL_TYPE_ID)";*/
			
			String queryDiv = " SELECT CR_CLIENT_CALLTYPE.CALL_TYPE_ID , CR_CALL_TYPE.CALL_TYPE_DESC +' - '+ CR_CALL_TYPE.DESCR  FROM CR_CLIENT_CALLTYPE  "
			+" INNER JOIN CR_CALL_TYPE  ON  (CR_CALL_TYPE.CALL_TYPE_ID = CR_CLIENT_CALLTYPE.CALL_TYPE_ID) " 
			+" WHERE CR_CLIENT_CALLTYPE.CLIENT_ID ="+bean.getAccountCode() ;

			
			
			
			Object[][] empDiv = getSqlModel("POOL_D1").getSingleResult(queryDiv);
			String selDivId = "0";
			HashMap mpDiv = new HashMap();
			if(empDiv!=null && empDiv.length > 0){
			for (int i = 0; i < empDiv.length; i++) {
				mpDiv.put(String.valueOf(empDiv[i][0]), String
						.valueOf(empDiv[i][1]));
				selDivId += "," + empDiv[i][0];
			}
			mpDiv = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(mpDiv, null, true);
			bean.setHashmapDivSel(mpDiv);
			}
			else{
				/*mpDiv.put("","");
				bean.setHashmapDivSel(mpDiv);*/

			}
			
			String querDivAvail = "SELECT CALL_TYPE_ID, CALL_TYPE_DESC +' - '+ CR_CALL_TYPE.DESCR  FROM CR_CALL_TYPE "
					+ "WHERE CALL_TYPE_ID NOT IN ("
					+ selDivId
					+ ")ORDER BY CALL_TYPE_ID ";
			Object[][] empDivMaster = getSqlModel("POOL_D1").getSingleResult(
					querDivAvail);
			HashMap mpDivMaster = new HashMap();
			if(empDivMaster!=null && empDivMaster.length > 0){
			
			for (int i = 0; i < empDivMaster.length; i++) {
				mpDivMaster.put(String.valueOf(empDivMaster[i][0]), String
						.valueOf(empDivMaster[i][1]));

			}
			mpDivMaster = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(mpDivMaster, null, true);
			bean.setHashmapDiv(mpDivMaster);
			}
			else{
				/*mpDivMaster.put("","");
				bean.setHashmapDiv(mpDivMaster);*/

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/* for inserting the data. 
	 * @param bean : PerformanceMetrics
	 * @return result
	 */
	public boolean addNewCallType(PerformanceMetrics bean,String userEmpID) {
		boolean result = false;
		String query = "SELECT * FROM CR_CALL_TYPE WHERE UPPER(CR_CALL_TYPE.CALL_TYPE_DESC) LIKE '"
				+ bean.getAddNewCall().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel("POOL_D1").getSingleResult(query);
		if (data.length ==0) {
			Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = bean.getAddNewCall();
			saveObj[0][1] = userEmpID;
			saveObj[0][2] = bean.getAddNewCallDesc();
			
			String insertRecordsQuery = "INSERT INTO CR_CALL_TYPE(CALL_TYPE_ID, CALL_TYPE_DESC,RECORD_CREATEDBY, RECORD_CREATEDON,DESCR)"
		+ " VALUES((SELECT isnull(MAX(CALL_TYPE_ID),0)+1 FROM CR_CALL_TYPE),?,?,getDATE(),?)";
			
			result = getSqlModel("POOL_D1").singleExecute(insertRecordsQuery, saveObj);
			
		}//end of if
		else {
			return false;
		}//end of else
		return result;
	}

	/**This is used to save client call type and also part wait time.
	 * @param bean : PerformanceMetrics
	 * @return
	 */
	public boolean saveClientCallType(PerformanceMetrics bean,String userEmpID) {
		boolean result = false;
		try{
		Object[][] saveObj = new Object[1][2];
		///saveObj[0][0] = bean.getAccountId();
		if (bean.getIsPartWaitTimeChecked().equals("true")) {
			saveObj[0][0] = "Y";
		} else {
			saveObj[0][0] = "N";
		}
		saveObj[0][1] = bean.getAccountCode();
		
		/*for (int i = 0; i < saveObj.length; i++) {
			for (int j = 0; j < saveObj[i].length; j++) {
				logger.info("saveObj[" + i + "][" + j
						+ "]  " + saveObj[i][j]);
			}
		}*/
		
		String updateQ="UPDATE CR_CLIENT_MASTER SET PART_WAIT_TIME_FLAG = ? , CALL_TYPE_FLAG = 'Y'  WHERE ACCOUNT_CODE=?";
		getSqlModel("POOL_D1").singleExecute(updateQ, saveObj);
		// Entries for call type start
		String Query1 = "DELETE FROM CR_CLIENT_CALLTYPE WHERE CLIENT_ID ="+bean.getAccountCode();
		result = getSqlModel("POOL_D1").singleExecute(Query1);
		
		Object maxCode[][] = getSqlModel("POOL_D1")
		.getSingleResult(
				"SELECT ISNULL(MAX(CLIENT_CALLTYPE_ID),0) +1 FROM CR_CLIENT_CALLTYPE");
			
		int code = Integer.parseInt("" + maxCode[0][0]);
		
		String selectedDiv[] = bean.getSelDiv().split(",");
			Object divParam[][] = new Object[selectedDiv.length][4];
			if(divParam != null && divParam.length > 0) {
				for (int j = 0; j < selectedDiv.length; j++) {
					divParam[j][0] = ++code; // Mapp Code
					divParam[j][1] = bean.getAccountCode();
					divParam[j][2] = selectedDiv[j].trim();
					divParam[j][3] = userEmpID;
				}
				
				String insertRecordsQuery = "INSERT INTO CR_CLIENT_CALLTYPE(CLIENT_CALLTYPE_ID, CLIENT_ID, CALL_TYPE_ID,RECORD_CREATEDBY, RECORD_CREATEDON)"
					+ " VALUES(?,?,?,?,getdate())";
	
				result = getSqlModel("POOL_D1").singleExecute(insertRecordsQuery, divParam);
			}
			// Entries for call type end
			
			// Entries for Repair Code start
			String queryRepairCode = "DELETE FROM CR_CLIENT_REPAIR WHERE ACCOUNT_CODE ="+bean.getAccountCode();
			result = getSqlModel("POOL_D1").singleExecute(queryRepairCode);
			
			Object repairCodeObj[][] = getSqlModel("POOL_D1")
			.getSingleResult(
					"SELECT ISNULL(MAX(CLIENT_REPAIR_ID),0) +1 FROM CR_CLIENT_REPAIR");
				
			int repCode = Integer.parseInt("" + repairCodeObj[0][0]);
			
			String selrepairCode[] = bean.getSelRepairCode().split(",");
				Object repCodeParam[][] = new Object[selrepairCode.length][4];
				if(repCodeParam != null && repCodeParam.length > 0) {
					for (int j = 0; j < selrepairCode.length; j++) {
						repCodeParam[j][0] = ++repCode; // repair Code
						repCodeParam[j][1] = bean.getAccountCode();
						repCodeParam[j][2] = selrepairCode[j].trim();
						repCodeParam[j][3] = userEmpID;
					}
					
					String insertRepairCodeQuery = "INSERT INTO CR_CLIENT_REPAIR(CLIENT_REPAIR_ID, ACCOUNT_CODE, REPAIR_CODE,RECORD_CREATEDBY, RECORD_CREATEDON)"
						+ " VALUES(?,?,?,?,GETDATE())";
		
					result = getSqlModel("POOL_D1").singleExecute(insertRepairCodeQuery, repCodeParam);
				}
				// Entries for call type end
				
				// Entries for Cause Code start
				String queryCauseCode = "DELETE FROM CR_CLIENT_CAUSE WHERE ACCOUNT_CODE ="+bean.getAccountCode();
				result = getSqlModel("POOL_D1").singleExecute(queryCauseCode);
				
				Object causeCodeObj[][] = getSqlModel("POOL_D1")
				.getSingleResult(
						"SELECT ISNULL(MAX(CLIENT_CAUSE_ID),0) +1 FROM CR_CLIENT_CAUSE");
					
				int causeCode = Integer.parseInt("" + causeCodeObj[0][0]);
				String selCauseCode[] = bean.getSelclosureCode().split(",");
					Object causeCodeParam[][] = new Object[selCauseCode.length][4];
					if(causeCodeParam != null && causeCodeParam.length > 0) {
						for (int j = 0; j < selCauseCode.length; j++) {
							causeCodeParam[j][0] = ++causeCode; // cause Code
							causeCodeParam[j][1] = bean.getAccountCode();
							causeCodeParam[j][2] = selCauseCode[j].trim();
							causeCodeParam[j][3] = userEmpID;
						}
						
						String insertCauseCodeQuery = "INSERT INTO CR_CLIENT_CAUSE(CLIENT_CAUSE_ID, ACCOUNT_CODE, CAUSE_CODE,RECORD_CREATEDBY, RECORD_CREATEDON)"
							+ " VALUES(?,?,?,?,GETDATE())";
			
						result = getSqlModel("POOL_D1").singleExecute(insertCauseCodeQuery, causeCodeParam);
					}
					// Entries for Cause Code end
					
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	}
	
	/**This function is used to get records from CR_CLIENT_CALLTYPE.
	 * @param response : HttpServletResponse
	 * @param bean : PerformanceMetrics
	 * @return : result
	 */
	public boolean setCallSelList(HttpServletResponse response,
			PerformanceMetrics bean) {
		boolean result = false;
		
		String accountQuery = "SELECT PART_WAIT_TIME_FLAG FROM CR_CLIENT_MASTER WHERE ACCOUNT_CODE ="
			+ bean.getAccountCode();

		Object[][] accountObj = getSqlModel("POOL_D1").getSingleResult(accountQuery);
		if (accountObj != null && accountObj.length > 0) {
			if (String.valueOf(accountObj[0][0]).equals("Y")) {
				bean.setIsPartWaitTimeChecked("true");
				bean.setHiddenPartWaitTimeFlag("S");
			} else {
				bean.setIsPartWaitTimeChecked("false");
				bean.setHiddenPartWaitTimeFlag("L");
			}

		}
		
		
		
		return result ;
	}

	/**This is used to filter list page using search string.
	 * @param bean : PerformanceMetrics
	 * @param request : HttpServletRequest
	 * @param searchMessage : string
	 */
	public void applyFilters(PerformanceMetrics bean,
			HttpServletRequest request, String searchMessage, String dashBoardID,String userEmpID ) {
		try {
			Object[][] listData = null;
			Object[][] accountData=null;
			String accountCode= "SELECT ACCOUNT_CODE FROM CR_EMP_CLIENT_MAPP WHERE USER_ID="+userEmpID+" AND DASHBOARD_ID ="+dashBoardID+"";
			accountData = getSqlModel().getSingleResult(accountCode);
			String accountNumber="";
			for (int i = 0; i < accountData.length; i++) {
				accountNumber+= accountData[i][0] +",";
			}
			accountNumber=accountNumber.substring(0, accountNumber.length()-1);
			
			
			
			
			
			ArrayList list = new ArrayList();
			String selQuery="SELECT A.ACCOUNT_CODE,A.ACCOUNT_ID,A.ACCOUNT_NAME,'Y'||A.PARENT_FLAG,B.ACCOUNT_CODE "
				+" FROM CR_CLIENT_MASTER A,CR_CLIENT_MASTER B "
				+" WHERE NVL(A.PARENT_CODE,A.ACCOUNT_CODE)=B.ACCOUNT_CODE "
				+" AND (A.ACCOUNT_CODE IN( "+accountNumber+") "  
				+"  OR A.PARENT_CODE IN( "+accountNumber+") )AND A.IS_ACTIVE='Y'";
			
	
	if (searchMessage != null && !searchMessage.trim().equals("")) {
		selQuery += " AND UPPER(A.ACCOUNT_NAME ||A.ACCOUNT_ID) LIKE UPPER (ltrim(rtrim('%"+ searchMessage.trim() + "%')))   ";
	}
	selQuery +=" ORDER BY B.ACCOUNT_CODE*10000+(case when A.PARENT_FLAG='Y' then 0 else A.ACCOUNT_CODE end)";
	
	listData = getSqlModel().getSingleResult(selQuery);
			String[] pageIndexApproved = Utility.doPaging(bean.getMyPage(),
					listData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndexApproved[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals("1"))
				bean.setMyPage("1");
			bean.setIteratorList(null);
			if (listData != null && listData.length > 0) {
				bean.setListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					PerformanceMetrics beanItt = new PerformanceMetrics();
					beanItt.setAccountCode(checkNull(String
							.valueOf(listData[i][0])));
					beanItt.setAccountId(checkNull(String
							.valueOf(listData[i][1])));
					beanItt.setAccountName(checkNull(String
							.valueOf(listData[i][2])));
					beanItt.setParentFlag(checkNull(String
							.valueOf(listData[i][3])));
					list.add(beanItt);
				}
				bean.setIteratorList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**This is used to set list of call type from master table CR_CALL_TYPE.
	 * @param bean : PerformanceMetrics
	 */
	public void setRepairTypeList(PerformanceMetrics bean) {
		try {
			
			/*String queryRepairMaster = " SELECT REPAIR_CODE, REPAIR_ID ||' - '|| CR_REPAIR_MASTER.DESCR  FROM CR_REPAIR_MASTER  " +
					" WHERE CR_REPAIR_MASTER.REPAIR_CODE NOT IN (SELECT CR_CLIENT_REPAIR.REPAIR_CODE FROM CR_CLIENT_REPAIR  WHERE ACCOUNT_CODE ="+bean.getAccountCode() +") " +
					" ORDER BY UPPER(REPAIR_CODE)";*/
			
			
			String queryRepairMaster = " SELECT CR_CLIENT_REPAIR.REPAIR_CODE , CR_REPAIR_MASTER.REPAIR_ID +' - '+ CR_REPAIR_MASTER.DESCR  FROM CR_CLIENT_REPAIR  "
			+" INNER JOIN CR_REPAIR_MASTER  ON  (CR_REPAIR_MASTER.REPAIR_CODE = CR_CLIENT_REPAIR.REPAIR_CODE) " 
			+" WHERE CR_CLIENT_REPAIR.ACCOUNT_CODE ="+bean.getAccountCode() ;

			
			
			Object[][] repairObj = getSqlModel("POOL_D1").getSingleResult(queryRepairMaster);
			String selRepairId = "0";
			HashMap mpRepair = new HashMap();
			if(repairObj!=null && repairObj.length > 0){
			for (int i = 0; i < repairObj.length; i++) {
				mpRepair.put(String.valueOf(repairObj[i][0]), String
						.valueOf(repairObj[i][1]));
				selRepairId += "," + repairObj[i][0];
			}
			mpRepair = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(mpRepair, null, true);
			bean.setHashmapRepairCodeSel(mpRepair);
			}
			else{
				/*mpRepair.put("","");
				bean.setHashmapRepairCodeSel(mpRepair);*/

			}
			
			String querRepairAvail = "SELECT REPAIR_CODE, REPAIR_ID +' - '+ CR_REPAIR_MASTER.DESCR  FROM CR_REPAIR_MASTER "
					+ "WHERE REPAIR_CODE NOT IN ("
					+ selRepairId
					+ ")ORDER BY REPAIR_CODE ";
			Object[][] repairMasterObj = getSqlModel("POOL_D1").getSingleResult(
					querRepairAvail);
			HashMap mpRepairMaster = new HashMap();
			if(repairMasterObj!=null && repairMasterObj.length > 0){
			for (int i = 0; i < repairMasterObj.length; i++) {
				mpRepairMaster.put(String.valueOf(repairMasterObj[i][0]), String
						.valueOf(repairMasterObj[i][1]));

			}
			mpRepairMaster = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(mpRepairMaster, null, true);
			bean.setHashmapRepairCode(mpRepairMaster);
			
			}
			else{
				/*mpRepairMaster.put("","");
				bean.setHashmapRepairCode(mpRepairMaster);*/

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	/* for inserting the data. 
	 * @param bean : PerformanceMetrics
	 * @return result
	 */
	public boolean addNewRepairCode(PerformanceMetrics bean,String userEmpID) {
		boolean result = false;
		String query = "SELECT * FROM CR_REPAIR_MASTER WHERE UPPER(CR_REPAIR_MASTER.REPAIR_ID) LIKE '"
				+ bean.getAddRepairCode().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel("POOL_D1").getSingleResult(query);
		if (data.length ==0) {
			Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = bean.getAddRepairCode();
			saveObj[0][1] = userEmpID;
			saveObj[0][2] = bean.getAddRepairCodeDesc();
			
			String insertRecordsQuery = "INSERT INTO CR_REPAIR_MASTER(REPAIR_CODE, REPAIR_ID,RECORD_CREATEDBY, RECORD_CREATEDON,DESCR)"
		+ " VALUES((SELECT ISNULL(MAX(REPAIR_CODE),0)+1 FROM CR_REPAIR_MASTER),?,?,GETDATE(),?)";
			
			result = getSqlModel("POOL_D1").singleExecute(insertRecordsQuery, saveObj);
			
		}//end of if
		else {
			return false;
		}//end of else
		return result;
	}
	
	/**This is used to set Misc Details form CR_MISC_DETAILS in list view.
	 * @param String : Call Id : The Id whose details are being pulled.
	 * 
	 */
	public Object[][] getCallDetail(String callId) {
		String callDtlQuery="SELECT ORDER_ID,convert(varchar(10),OPEN_DT,101)+' '+convert(varchar(8),OPEN_TIME,108) open_dt_tm,convert(varchar(10),ESTIMATED_DT,101) ESTIMATED_DT,convert(varchar(10),CONTACT_CUST_DT_TM,101)+' '+convert(varchar(8),CONTACT_CUST_DT_TM,108) CONTACT_CUST_FIRST_DT_TM,convert(varchar(10),ARRIVE_DT,101)+' '+convert(varchar(8),ARRIVE_TM,108) ARRIVE_DT_TM,convert(varchar(10),SERVICE_ACT_FIRST_DT_TM,101)+' '+convert(varchar(8),SERVICE_ACT_FIRST_DT_TM,108) SERVICE_ACT_FIRST_DT_TM,convert(varchar(10),RESOLVE_DT,101)+' '+convert(varchar(8),RESOLVE_TM,108) RESOLVE_DT_TM,convert(varchar(10),UNAVAIL_FIRST_DT_TM,101)+' '+convert(varchar(8),UNAVAIL_FIRST_DT_TM,108) UNAVAIL_FIRST_DT_TM,convert(varchar(10),UNAVAIL_LAST_DT_TM,101)+' '+convert(varchar(8),UNAVAIL_LAST_DT_TM,108) UNAVAIL_LAST_DT_TM,convert(varchar(10),SERVICE_COMPLETE_DT_TM,101)+' '+convert(varchar(8),SERVICE_COMPLETE_DT_TM,108) SERVICE_COMPLETE_DT_TM,convert(varchar(10),INVOICED_DTM,101)+' '+convert(varchar(8),INVOICED_DTM,108)  as INVOICED_DT_TM,convert(varchar(10),LAST_ACTION_DT,101)+' '+convert(varchar(8), LAST_ACTION_DT,108)  LAST_ACTION_DT ,ACTGR_ID,CALLER_NAME,CALLT_ID,CAUSE_ID,COMPANY_GRP_ID,CUST_COMPANY_DESCR,CUST_COMPANY_ID,HISTORY_LOAD, LABOR_HOURS,LOAD_DT,MANAGER_ID,MANUFACTURER,MAX_ENTERED_ETA,MGR_FNAME,MGR_LNAME,ORDER_STAT_ID,ORDER_STATUS,PARENT_COMPANY_ID,PCODE_ID,PHONE,PO_NUMBER,PROBLEM_DESC,REFNO,REPAIR_CODE,REQUEST_ID,RESOLVE_TEXT,SA_FNAME,SA_LNAME,SA_PERSON_ID,SERIAL_NO,SERVICE_ADDRESS,SERVICE_CITY,SERVICE_ID,SERVICE_STATE,SERVICE_ZIP,SITE_ADDRESS,SITE_CITY,SITE_DESCR,SITE_ID,SITE_STATE, SITE_ZIPCODE,TAGNO,TRAVEL_HOURS,TRAVEL_MILES,CONTACT_CUST_CNT,CONTACT_CUST_FIRST_DAYS_GROSS,CONTACT_CUST_FIRST_DAYS_NET, CONTACT_CUST_FIRST_HRS_GROSS,CONTACT_CUST_FIRST_HRS_NET,NODE_ID,ONSITE_DAYS_GROSS,ONSITE_DAYS_NET,ONSITE_HRS_GROSS, ONSITE_HRS_NET,OPEN_AFTER3,OPEN2_SERVCOMP_DAYS_GROSS,OPEN2_SERVCOMP_DAYS_NET,OPEN2_SERVCOMP_HRS_GROSS,OPEN2_SERVCOMP_HRS_NET, RESOLVE_DAYS_GROSS,RESOLVE_DAYS_NET,RESOLVE_HRS_GROSS,RESOLVE_HRS_NET,SERVICE_ACT_CNT,SERVICE_ACT_FIRST_DAYS_GROSS, SERVICE_ACT_FIRST_DAYS_NET,SERVICE_ACT_FIRST_HRS_GROSS,SERVICE_ACT_FIRST_HRS_NET,UNAVAIL_CNT, UNAVAIL_FIRST_DAYS_GROSS,UNAVAIL_FIRST_DAYS_NET,UNAVAIL_FIRST_DEFR_CODE,UNAVAIL_FIRST_HRS_GROSS,UNAVAIL_FIRST_HRS_NET, UNAVAIL_LAST_DAYS_GROSS,UNAVAIL_LAST_DAYS_NET,UNAVAIL_LAST_DEFR_CODE,UNAVAIL_LAST_HRS_GROSS,UNAVAIL_LAST_HRS_NET, USED_PARTS_COST,USED_PARTS_QTY,ACCOUNT_CODE,SLA,GROSS_CUSTOMER_WAIT,GROSS_PART_WAIT,CUSTOMER_WAIT,PART_WAIT,COMMENTS,EXCLUDE_FLAG,ORG_CONT_SLA_FLAG,CRM_CONT_SLA_FLAG,CONTACT_SLA_HR as [Contact SLA],CUSTOMER_CONTACT_SLA_HOURS as [Actual Contact hrs],ORG_RESP_SLA_FLAG,CRM_RESP_SLA_FLAG,SLA_HRS as [Response SLA],ARRIVE_ONSITE_SLA_HOURS as [Actual Response Hrs],ORG_REST_SLA_FLAG,CRM_REST_SLA_FLAG,RESTOREHRS as [Restore SLA],act_restore_hr as [Actual Restore Hrs],ORG_FCC_FLAG,CRM_FCC_FLAG FROM CR_V_CLOSED_CALL_DAILY where order_id=?";
		/*String callDtlQuery = "select ORDER_ID,CONVERT(VARCHAR(10),OPEN_DT_TM,101)+' '+CONVERT(VARCHAR(5),OPEN_DT_TM,108) OPEN_DT_TM, "
				+" CONVERT(VARCHAR(10),ESTIMATED_DT,101)+' '+CONVERT(VARCHAR(5),ESTIMATED_TM,108) ESTIMATED_DT_TM,"
				+" CONVERT(VARCHAR(10),CONTACT_CUST_FIRST_DT_TM,101)+' '+CONVERT(VARCHAR(5),CONTACT_CUST_FIRST_DT_TM,108) CONTACT_CUST_FIRST_DT_TM,"
				+" CONVERT(VARCHAR(10),ARRIVE_DT_TM,101)+' '+CONVERT(VARCHAR(5),ARRIVE_DT_TM,108) ARRIVE_DT_TM,"
				+" CONVERT(VARCHAR(10),SERVICE_ACT_FIRST_DT_TM,101)+' '+CONVERT(VARCHAR(5),SERVICE_ACT_FIRST_DT_TM,108) SERVICE_ACT_FIRST_DT_TM,"
				+" CONVERT(VARCHAR(10),RESOLVE_DT_TM,101)+' '+CONVERT(VARCHAR(5),RESOLVE_DT_TM,108) RESOLVE_DT_TM,"
				+" CONVERT(VARCHAR(10),UNAVAIL_FIRST_DT_TM,101)+' '+CONVERT(VARCHAR(5),UNAVAIL_FIRST_DT_TM,108) UNAVAIL_FIRST_DT_TM,"
				+" CONVERT(VARCHAR(10),UNAVAIL_LAST_DT_TM,101)+' '+CONVERT(VARCHAR(5),UNAVAIL_LAST_DT_TM,108) UNAVAIL_LAST_DT_TM,"
				+" CONVERT(VARCHAR(10),SERVICE_COMPLETE_DT_TM,101)+' '+CONVERT(VARCHAR(5),SERVICE_COMPLETE_DT_TM,108) SERVICE_COMPLETE_DT_TM,"
				+" CONVERT(VARCHAR(10),INVOICED_DTM,101)+' '+CONVERT(VARCHAR(5),INVOICED_DTM,108) INVOICED_DT_TM,"
				+" CONVERT(VARCHAR(10),LAST_ACTION_DT,101)+' '+CONVERT(VARCHAR(5),LAST_ACTION_DT,108) LAST_ACTION_DT_TM,"
				+" ACTGR_ID,CALLER_NAME,CALLT_ID,CAUSE_ID,COMPANY_GRP_ID,CUST_COMPANY_DESCR,CUST_COMPANY_ID,HISTORY_LOAD,"
				+" LABOR_HOURS,LOAD_DT,MANAGER_ID,MANUFACTURER,MAX_ENTERED_ETA,MGR_FNAME,MGR_LNAME,ORDER_STAT_ID,ORDER_STATUS,"
				+" PARENT_COMPANY_ID,PCODE_ID,PHONE,PO_NUMBER,PROBLEM_DESC,REFNO,REPAIR_CODE,REQUEST_ID,RESOLVE_TEXT,SA_FNAME,SA_LNAME,"
				+" SA_PERSON_ID,SERIAL_NO,SERVICE_ADDRESS,SERVICE_CITY,SERVICE_ID,SERVICE_STATE,SERVICE_ZIP,SITE_ADDRESS,SITE_CITY,SITE_DESCR,SITE_ID,SITE_STATE,"
				+" SITE_ZIPCODE,TAGNO,TRAVEL_HOURS,TRAVEL_MILES,CONTACT_CUST_CNT,CONTACT_CUST_FIRST_DAYS_GROSS,CONTACT_CUST_FIRST_DAYS_NET,"
				+" CONTACT_CUST_FIRST_HRS_GROSS,CONTACT_CUST_FIRST_HRS_NET,NODE_ID,ONSITE_DAYS_GROSS,ONSITE_DAYS_NET,ONSITE_HRS_GROSS,"
				+" ONSITE_HRS_NET,OPEN_AFTER3,OPEN2_SERVCOMP_DAYS_GROSS,OPEN2_SERVCOMP_DAYS_NET,OPEN2_SERVCOMP_HRS_GROSS,OPEN2_SERVCOMP_HRS_NET,"
				+" RESOLVE_DAYS_GROSS,RESOLVE_DAYS_NET,RESOLVE_HRS_GROSS,RESOLVE_HRS_NET,SERVICE_ACT_CNT,SERVICE_ACT_FIRST_DAYS_GROSS,"
				+" SERVICE_ACT_FIRST_DAYS_NET,SERVICE_ACT_FIRST_HRS_GROSS,SERVICE_ACT_FIRST_HRS_NET,UNAVAIL_CNT,"
				+" UNAVAIL_FIRST_DAYS_GROSS,UNAVAIL_FIRST_DAYS_NET,UNAVAIL_FIRST_DEFR_CODE,UNAVAIL_FIRST_HRS_GROSS,UNAVAIL_FIRST_HRS_NET,"
				+" UNAVAIL_LAST_DAYS_GROSS,UNAVAIL_LAST_DAYS_NET,UNAVAIL_LAST_DEFR_CODE,UNAVAIL_LAST_HRS_GROSS,UNAVAIL_LAST_HRS_NET,"
				+" USED_PARTS_COST,USED_PARTS_QTY,ACCOUNT_CODE,SLA,SLA_HRS,ORG_CONT_SLA_FLAG,ORG_RESP_SLA_FLAG,ORG_REST_SLA_FLAG,ORG_FCC_FLAG,"
				+" CUSTOMER_CONTACT_SLA_HOURS,ARRIVE_ONSITE_SLA_HOURS,GROSS_CUSTOMER_WAIT,GROSS_PART_WAIT, "
				+" CUSTOMER_WAIT,PART_WAIT,RESTOREHRS from CR_V_CLOSED_CALL_DAILY "
				+" where order_id=?";*/
		return getSqlModel("POOL_D1").getSingleResultWithCol(callDtlQuery, new Object[]{callId});
	}
	
	/**This is used to set Misc Details form CR_MISC_DETAILS in list view.
	 * @param bean : PerformanceMetrics
	 * @param requestID : requestID
	 * @param request : HttpServletRequest
	 * @param callType 
	 */
	public void callForDataReconcilation(PerformanceMetrics bean, String requestID, HttpServletRequest request, String callType) {
		/*String accountQuery = "SELECT ACCOUNT_CODE , ACCOUNT_NAME  FROM CR_CLIENT_MASTER WHERE ACCOUNT_CODE ="
			+ requestID;

		Object[][] accountObj = getSqlModel("POOL_D1").getSingleResult(accountQuery); 
		if (accountObj != null && accountObj.length > 0) {
			bean.setAccountCode(String.valueOf(accountObj[0][0]));
			bean.setAccountName(String.valueOf(accountObj[0][1]));
		
		}*/ 
		//query for restore
		////String restoreFlagQuery = "SELECT METRICS_CODE, CRM_FLAG FROM CR_PERFORMANCE_METRICS WHERE ACCOUNT_ID = "+ requestID +" AND METRICS_CODE = 26 ";
		String restoreCount="";
		
		String restoreFlagQuery = "SELECT COUNT(*) FROM CR_PERFORMANCE_SLA WHERE ACCOUNT_CODE = "+ requestID +" AND SLA_TYPE='RESTORE' AND CRM_FLAG ='Y' ";
		Object[][] restoreFlagObj = getSqlModel("POOL_D1").getSingleResult(restoreFlagQuery);
		if (restoreFlagObj != null && restoreFlagObj.length > 0) {
			restoreCount = String.valueOf(restoreFlagObj[0][0]);
			if(String.valueOf(restoreCount).equals("0")){
				bean.setRestoreFlagCheck(false);
			}else{
				bean.setRestoreFlagCheck(true);
			}
		} 
		
		//query for eta
		String etaCount="";
		String etaFlagQuery = "SELECT COUNT(*) FROM CR_PERFORMANCE_METRICS WHERE ACCOUNT_ID = "+ requestID +" AND METRICS_CODE = 27 AND CRM_FLAG ='Y' ";
		Object[][] etaFlagObj = getSqlModel("POOL_D1").getSingleResult(etaFlagQuery);
		if (etaFlagObj != null && etaFlagObj.length > 0) {
			etaCount = String.valueOf(etaFlagObj[0][0]);
			if(String.valueOf(etaCount).equals("0")){
				bean.setEtaReportFlagCheck(false);
			}else{
				bean.setEtaReportFlagCheck(true);
			}
		} 
		
		
		//query for contact
		String contactCount="";
		String responseCount="";
		String fccCount=""; 
		String contactFlagQuery = "SELECT COUNT(*) FROM CR_PERFORMANCE_SLA WHERE ACCOUNT_CODE = "+ requestID +" AND SLA_TYPE='CONTACT' AND CRM_FLAG ='Y' ";
		Object[][] contactFlagObj = getSqlModel("POOL_D1").getSingleResult(contactFlagQuery);
		if (contactFlagObj != null && contactFlagObj.length > 0) {
			contactCount = String.valueOf(contactFlagObj[0][0]);
			
			if(String.valueOf(contactCount).equals("0")){
				bean.setContactFlagCheckedCount(false);
			}else{
				bean.setContactFlagCheckedCount(true);
			}
		}
		
		// query for response
		String responseFlagQuery = "SELECT COUNT(*) FROM CR_PERFORMANCE_SLA WHERE ACCOUNT_CODE = "+ requestID +" AND SLA_TYPE='RESPONSE'  AND CRM_FLAG ='Y' ";
		Object[][] responseFlagObj = getSqlModel("POOL_D1").getSingleResult(responseFlagQuery);
		if (responseFlagObj != null && responseFlagObj.length > 0) {
			responseCount = String.valueOf(responseFlagObj[0][0]);
			if(String.valueOf(responseCount).equals("0")){
				bean.setResponseFlagCheckedCount(false);
			}else{
				bean.setResponseFlagCheckedCount(true);
			}
		}
		//query for first call closure
		String fccFlagQuery = "SELECT COUNT(*) FROM CR_PERFORMANCE_METRICS WHERE ACCOUNT_ID = "+ requestID +" AND METRICS_CODE IN (13,22) AND CRM_FLAG ='Y' ";
		Object[][] fccFlagObj = getSqlModel("POOL_D1").getSingleResult(fccFlagQuery);
		if (fccFlagObj != null && fccFlagObj.length > 0) {
			fccCount = String.valueOf(fccFlagObj[0][0]);
			if(String.valueOf(fccCount).equals("0")){
				bean.setFccFlagCheck(false);
			}else{
				bean.setFccFlagCheck(true);
			}
		}
		String condition="";
		condition=(String.valueOf(contactCount).equals("0")?"(1=0 ":"(ORG_CONT_SLA_FLAG='N'") + " OR " +  (String.valueOf(responseCount).equals("0")?" 1=0 ":"ORG_RESP_SLA_FLAG='N'") + " OR "  +  (String.valueOf(etaCount).equals("0")?" 1=0 ":"ORG_ETA_FLAG='N'") + " OR " +  (String.valueOf(fccCount).equals("0")?" 1=0 ":"ORG_FCC_FLAG='N'") + " OR " +  (String.valueOf(restoreCount).equals("0")?" 1=0) ":"ORG_REST_SLA_FLAG='N')");   
			
		
		String fromDate = bean.getFromDate();
	    String toDate = bean.getToDate();
	    String parentFlag = bean.getParentFlag();
	    String dataReconciliationQuery = "";
	    if(parentFlag.equals("Y")){
	    	System.out.println("Parent"); 
	    	if(callType.equals("MC")){
	    		dataReconciliationQuery = " SELECT CR_SO_MISSED_SLA.ORDER_ID, CR_SO_MISSED_SLA.CUSTOMER_NAME, CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.CALL_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.CALL_DATE,108), CR_SO_MISSED_SLA.SLA, CR_SO_MISSED_SLA.RESPONSE, " +
				"	CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.DISP_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.DISP_DATE,108), CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.ONSITE_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.ONSITE_DATE,105), " +
				"   CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.CLOSED_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.CLOSED_DATE,108) ," +
				"	CR_SO_MISSED_SLA.CRM_CONT_SLA_FLAG, CR_SO_MISSED_SLA.CRM_RESP_SLA_FLAG, CR_SO_MISSED_SLA.CRM_REST_SLA_FLAG"
				+"	, CR_SO_MISSED_SLA.ORG_CONT_SLA_FLAG, CR_SO_MISSED_SLA.ORG_RESP_SLA_FLAG, CR_SO_MISSED_SLA.ORG_REST_SLA_FLAG, " +
				" CR_SO_MISSED_SLA.SLA_HRS , CR_SO_MISSED_SLA.COMMENTS ,CR_SO_MISSED_SLA.CRM_ETA_FLAG , CR_SO_MISSED_SLA.CRM_FCC_FLAG , CR_SO_MISSED_SLA.ACCOUNT_CODE ,CR_SO_MISSED_SLA.ORG_ETA_FLAG,CR_SO_MISSED_SLA.ORG_FCC_FLAG,CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.PS_DATE,105),EXCLUDE_FLAG,CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.Contact_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.Contact_DATE,108) FROM CR_SO_MISSED_SLA "
				+"  INNER JOIN CR_CLIENT_MASTER ON 	(CR_CLIENT_MASTER.ACCOUNT_CODE = CR_SO_MISSED_SLA.ACCOUNT_CODE)"
				+"	WHERE CR_CLIENT_MASTER.PARENT_CODE  = "+requestID+" AND "
//				+" (ORG_CONT_SLA_FLAG='N' OR  ORG_RESP_SLA_FLAG='N' OR   ORG_REST_SLA_FLAG='N' OR   ORG_FCC_FLAG='N')"
				+ condition
				+"	AND CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) >=CONVERT(DATE,'" + fromDate + "',105) "	
				+"	AND CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) <=CONVERT(DATE,'" + toDate + "',105)";
	    	}else if(callType.equals("AC")){
	    		dataReconciliationQuery = " SELECT CR_SO_MISSED_SLA.ORDER_ID, CR_SO_MISSED_SLA.CUSTOMER_NAME, CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.CALL_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.CALL_DATE,108), CR_SO_MISSED_SLA.SLA, CR_SO_MISSED_SLA.RESPONSE, " +
				"	CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.DISP_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.DISP_DATE,108), CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.ONSITE_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.ONSITE_DATE,108), " +
				"   CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.CLOSED_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.CLOSED_DATE,108) ," +
				"	CR_SO_MISSED_SLA.CRM_CONT_SLA_FLAG, CR_SO_MISSED_SLA.CRM_RESP_SLA_FLAG, CR_SO_MISSED_SLA.CRM_REST_SLA_FLAG"
				+"	, CR_SO_MISSED_SLA.ORG_CONT_SLA_FLAG, CR_SO_MISSED_SLA.ORG_RESP_SLA_FLAG, CR_SO_MISSED_SLA.ORG_REST_SLA_FLAG, " +
				" CR_SO_MISSED_SLA.SLA_HRS , CR_SO_MISSED_SLA.COMMENTS ,CR_SO_MISSED_SLA.CRM_ETA_FLAG , CR_SO_MISSED_SLA.CRM_FCC_FLAG , CR_SO_MISSED_SLA.ACCOUNT_CODE ,CR_SO_MISSED_SLA.ORG_ETA_FLAG,CR_SO_MISSED_SLA.ORG_FCC_FLAG,CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.PS_DATE,105),EXCLUDE_FLAG,CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.Contact_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.Contact_DATE,108) FROM CR_SO_MISSED_SLA "
				+"  INNER JOIN CR_CLIENT_MASTER ON 	(CR_CLIENT_MASTER.ACCOUNT_CODE = CR_SO_MISSED_SLA.ACCOUNT_CODE)"
				+"	WHERE CR_CLIENT_MASTER.PARENT_CODE  = "+requestID+"  "
			///	+" (ORG_CONT_SLA_FLAG='N' OR  ORG_RESP_SLA_FLAG='N' OR   ORG_REST_SLA_FLAG='N' OR   ORG_FCC_FLAG='N')"
				///+ condition
				+"	AND CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) >=CONVERT(DATE,'" + fromDate + "',105) "	
				+"	AND CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) <=CONVERT(DATE,'" + toDate + "',105)";
	    	}else{
	    		System.out.println("EXCLUDE FLAG");
	    		dataReconciliationQuery = " SELECT CR_SO_MISSED_SLA.ORDER_ID, CR_SO_MISSED_SLA.CUSTOMER_NAME, CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.CALL_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.CALL_DATE,108), CR_SO_MISSED_SLA.SLA, CR_SO_MISSED_SLA.RESPONSE, " +
	    		"	CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.DISP_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.DISP_DATE,108), CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.ONSITE_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.ONSITE_DATE,108), " +
	    		"   CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.CLOSED_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.CLOSED_DATE,108) ," +
					"	CR_SO_MISSED_SLA.CRM_CONT_SLA_FLAG, CR_SO_MISSED_SLA.CRM_RESP_SLA_FLAG, CR_SO_MISSED_SLA.CRM_REST_SLA_FLAG"
					+"	, CR_SO_MISSED_SLA.ORG_CONT_SLA_FLAG, CR_SO_MISSED_SLA.ORG_RESP_SLA_FLAG, CR_SO_MISSED_SLA.ORG_REST_SLA_FLAG, " +
					" CR_SO_MISSED_SLA.SLA_HRS , CR_SO_MISSED_SLA.COMMENTS ,CR_SO_MISSED_SLA.CRM_ETA_FLAG , CR_SO_MISSED_SLA.CRM_FCC_FLAG , CR_SO_MISSED_SLA.ACCOUNT_CODE ,CR_SO_MISSED_SLA.ORG_ETA_FLAG,CR_SO_MISSED_SLA.ORG_FCC_FLAG,CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.PS_DATE,105),EXCLUDE_FLAG,CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.Contact_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.Contact_DATE,108) FROM CR_SO_MISSED_SLA "
					+"  INNER JOIN CR_CLIENT_MASTER ON 	(CR_CLIENT_MASTER.ACCOUNT_CODE = CR_SO_MISSED_SLA.ACCOUNT_CODE)"
					+"	WHERE CR_CLIENT_MASTER.PARENT_CODE  = "+requestID+" AND EXCLUDE_FLAG='Y' "
				///	+" (ORG_CONT_SLA_FLAG='N' OR  ORG_RESP_SLA_FLAG='N' OR   ORG_REST_SLA_FLAG='N' OR   ORG_FCC_FLAG='N')"
					///+ condition
					+"	AND CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) >=CONVERT(DATE,'" + fromDate + "',105) "	
					+"	AND CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) <=CONVERT(DATE,'" + toDate + "',105)";
	    	}
	    	 
	    }else {
	    	System.out.println("child");
	    	if(callType.equals("MC")){
	    	 dataReconciliationQuery =  " SELECT CR_SO_MISSED_SLA.ORDER_ID, CR_SO_MISSED_SLA.CUSTOMER_NAME, CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.CALL_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.CALL_DATE,108), CR_SO_MISSED_SLA.SLA, CR_SO_MISSED_SLA.RESPONSE, " +
	    		"	CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.DISP_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.DISP_DATE,108), CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.ONSITE_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.ONSITE_DATE,108), " +
				"   CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.CLOSED_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.CLOSED_DATE,108) ," +
				"	CR_SO_MISSED_SLA.CRM_CONT_SLA_FLAG, CR_SO_MISSED_SLA.CRM_RESP_SLA_FLAG, CR_SO_MISSED_SLA.CRM_REST_SLA_FLAG"
				+"	, CR_SO_MISSED_SLA.ORG_CONT_SLA_FLAG, CR_SO_MISSED_SLA.ORG_RESP_SLA_FLAG, CR_SO_MISSED_SLA.ORG_REST_SLA_FLAG, " +
				" CR_SO_MISSED_SLA.SLA_HRS , CR_SO_MISSED_SLA.COMMENTS ,CR_SO_MISSED_SLA.CRM_ETA_FLAG , CR_SO_MISSED_SLA.CRM_FCC_FLAG , CR_SO_MISSED_SLA.ACCOUNT_CODE ,CR_SO_MISSED_SLA.ORG_ETA_FLAG,CR_SO_MISSED_SLA.ORG_FCC_FLAG,CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.PS_DATE,105),EXCLUDE_FLAG,CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.Contact_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.Contact_DATE,108) FROM CR_SO_MISSED_SLA "
					///	+"  INNER JOIN CR_CLIENT_MASTER ON 	(CR_CLIENT_MASTER.ACCOUNT_CODE = CR_SO_MISSED_SLA.ACCOUNT_CODE)"
						+"	WHERE CR_SO_MISSED_SLA.ACCOUNT_CODE  = "+requestID+" AND "
//						+" (ORG_CONT_SLA_FLAG='N' OR  ORG_RESP_SLA_FLAG='N' OR   ORG_REST_SLA_FLAG='N' OR   ORG_FCC_FLAG='N')"
						+ condition
						+"	AND CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) >=CONVERT(DATE,'" + fromDate + "',105) "	
						+"	AND CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) <=CONVERT(DATE,'" + toDate + "',105)";
	    	}else if(callType.equals("AC")){
	    		dataReconciliationQuery = " SELECT CR_SO_MISSED_SLA.ORDER_ID, CR_SO_MISSED_SLA.CUSTOMER_NAME, CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.CALL_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.CALL_DATE,108), CR_SO_MISSED_SLA.SLA, CR_SO_MISSED_SLA.RESPONSE, " +
				"	CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.DISP_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.DISP_DATE,108), CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.ONSITE_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.ONSITE_DATE,108), " +
				"   CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.CLOSED_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.CLOSED_DATE,105) ," +
				"	CR_SO_MISSED_SLA.CRM_CONT_SLA_FLAG, CR_SO_MISSED_SLA.CRM_RESP_SLA_FLAG, CR_SO_MISSED_SLA.CRM_REST_SLA_FLAG"
				+"	, CR_SO_MISSED_SLA.ORG_CONT_SLA_FLAG, CR_SO_MISSED_SLA.ORG_RESP_SLA_FLAG, CR_SO_MISSED_SLA.ORG_REST_SLA_FLAG, " +
				" CR_SO_MISSED_SLA.SLA_HRS , CR_SO_MISSED_SLA.COMMENTS ,CR_SO_MISSED_SLA.CRM_ETA_FLAG , CR_SO_MISSED_SLA.CRM_FCC_FLAG , CR_SO_MISSED_SLA.ACCOUNT_CODE ,CR_SO_MISSED_SLA.ORG_ETA_FLAG,CR_SO_MISSED_SLA.ORG_FCC_FLAG,CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.PS_DATE,105),EXCLUDE_FLAG,CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.Contact_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.Contact_DATE,108) FROM CR_SO_MISSED_SLA "
				
					///	+"  INNER JOIN CR_CLIENT_MASTER ON 	(CR_CLIENT_MASTER.ACCOUNT_CODE = CR_SO_MISSED_SLA.ACCOUNT_CODE)"
						+"	WHERE CR_SO_MISSED_SLA.ACCOUNT_CODE  = "+requestID+"  "
					///	+" (ORG_CONT_SLA_FLAG='N' OR  ORG_RESP_SLA_FLAG='N' OR   ORG_REST_SLA_FLAG='N' OR   ORG_FCC_FLAG='N')"
	//					+ condition
						+"	AND CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) >=CONVERT(DATE,'" + fromDate + "',105) "	
						+"	AND CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) <=CONVERT(DATE,'" + toDate + "',105)";
	    	}else{
	    		System.out.println("EXCLUDE FLAG");
	    		dataReconciliationQuery = " SELECT CR_SO_MISSED_SLA.ORDER_ID, CR_SO_MISSED_SLA.CUSTOMER_NAME, CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.CALL_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.CALL_DATE,108), CR_SO_MISSED_SLA.SLA, CR_SO_MISSED_SLA.RESPONSE, " +
					"	CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.DISP_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.DISP_DATE,108), CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.ONSITE_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.ONSITE_DATE,108), " +
					"   CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.CLOSED_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.CLOSED_DATE,108) ," +
					"	CR_SO_MISSED_SLA.CRM_CONT_SLA_FLAG, CR_SO_MISSED_SLA.CRM_RESP_SLA_FLAG, CR_SO_MISSED_SLA.CRM_REST_SLA_FLAG"
					+"	, CR_SO_MISSED_SLA.ORG_CONT_SLA_FLAG, CR_SO_MISSED_SLA.ORG_RESP_SLA_FLAG, CR_SO_MISSED_SLA.ORG_REST_SLA_FLAG, " +
					" CR_SO_MISSED_SLA.SLA_HRS , CR_SO_MISSED_SLA.COMMENTS ,CR_SO_MISSED_SLA.CRM_ETA_FLAG , CR_SO_MISSED_SLA.CRM_FCC_FLAG , CR_SO_MISSED_SLA.ACCOUNT_CODE ,CR_SO_MISSED_SLA.ORG_ETA_FLAG,CR_SO_MISSED_SLA.ORG_FCC_FLAG,CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.PS_DATE,105),EXCLUDE_FLAG,CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.Contact_DATE,105), CONVERT(VARCHAR(5),CR_SO_MISSED_SLA.Contact_DATE,108) FROM CR_SO_MISSED_SLA "
						///	+"  INNER JOIN CR_CLIENT_MASTER ON 	(CR_CLIENT_MASTER.ACCOUNT_CODE = CR_SO_MISSED_SLA.ACCOUNT_CODE)"
						+"	WHERE CR_SO_MISSED_SLA.ACCOUNT_CODE  = "+requestID+" AND EXCLUDE_FLAG='Y' "
					///	+" (ORG_CONT_SLA_FLAG='N' OR  ORG_RESP_SLA_FLAG='N' OR   ORG_REST_SLA_FLAG='N' OR   ORG_FCC_FLAG='N')"
	//					+ condition
						+"	AND CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) >=CONVERT(DATE,'" + fromDate + "',105) "	
						+"	AND CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) <=CONVERT(DATE,'" + toDate + "',105)";
	    	}
	    }
	    
		
		
		Object[][] dataReconciliationObj = getSqlModel("POOL_D1").getSingleResult(dataReconciliationQuery);
		
		String[] pageIndexApproved = Utility.doPaging(bean.getMyPageDataRecon(),
				dataReconciliationObj.length, 20);
		if (pageIndexApproved == null) {
			pageIndexApproved[0] = "0";
			pageIndexApproved[1] = "20";
			pageIndexApproved[2] = "1";
			pageIndexApproved[3] = "1";
			pageIndexApproved[4] = ""; 
		}

		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndexApproved[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndexApproved[3])));
		if (pageIndexApproved[4].equals("1"))
			bean.setMyPageDataRecon("1");
		
		bean.setDataReconciliationListLength(false);
		bean.setViewPublishedFlag(false);
		bean.setClosedCallSummaryReportFlag(true);
		if (dataReconciliationObj != null && dataReconciliationObj.length > 0) {
			bean.setDataReconciliationListLength(true);
			bean.setViewPublishedFlag(true);
			bean.setTotalDataReconRecords(String.valueOf(dataReconciliationObj.length));
			ArrayList<PerformanceMetrics> list = new ArrayList<PerformanceMetrics>();
			
			for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
				.parseInt(pageIndexApproved[1]); i++) {
				PerformanceMetrics bean1 = new PerformanceMetrics();
				bean1.setOrderId(checkNull((String
							.valueOf(dataReconciliationObj[i][0]))));
				bean1.setCustomerName(checkNull((String
							.valueOf(dataReconciliationObj[i][1]))));
				bean1.setCallDate(checkNull((String
						.valueOf(dataReconciliationObj[i][2]))));
				bean1.setCallTime(checkNull((String
						.valueOf(dataReconciliationObj[i][3]))));
				bean1.setSla(checkNull((String
						.valueOf(dataReconciliationObj[i][4]))));
				bean1.setResponseDate(checkNull((String
						.valueOf(dataReconciliationObj[i][27]))));
				bean1.setResponseTime(checkNull((String
						.valueOf(dataReconciliationObj[i][28]))));
				bean1.setDispatchDate(checkNull((String
						.valueOf(dataReconciliationObj[i][6]))));
				bean1.setDispatchTime(checkNull((String
						.valueOf(dataReconciliationObj[i][7]))));
				bean1.setOnsiteDate(checkNull((String
						.valueOf(dataReconciliationObj[i][8]))));
				bean1.setOnsiteTime(checkNull((String
						.valueOf(dataReconciliationObj[i][9]))));
				bean1.setClosedDate(checkNull((String
						.valueOf(dataReconciliationObj[i][10]))));
				bean1.setClosedTime(checkNull((String
						.valueOf(dataReconciliationObj[i][11]))));
				
				if (String.valueOf(dataReconciliationObj[i][12]).equals("Y")) {
					bean1.setContactFlagHidden("Y");
				} 
				// check whether contact Flage set as "E"
				else if(String.valueOf(dataReconciliationObj[i][12]).equals("E")){
					bean1.setContactFlagHidden("E");
				}
				else {
					bean1.setContactFlagHidden("N");
				}
				if (String.valueOf(dataReconciliationObj[i][13]).equals("Y")) {
					bean1.setResponseFlagHidden("Y");
				} 
				else if(String.valueOf(dataReconciliationObj[i][13]).equals("E")){
					bean1.setResponseFlagHidden("E");
				}
				else {
					bean1.setResponseFlagHidden("N");
				}
				if (String.valueOf(dataReconciliationObj[i][14]).equals("Y")) {
					bean1.setRestoreFlagHidden("Y");
				} 
				else if(String.valueOf(dataReconciliationObj[i][14]).equals("E")){
					bean1.setRestoreFlagHidden("E");
				}
				else {
					bean1.setRestoreFlagHidden("N");
				}
				
				if (String.valueOf(dataReconciliationObj[i][15]).equals("Y")) {
					bean1.setOrgContactFlagHidden("Y");
				} else {
					bean1.setOrgContactFlagHidden("N");
				}
				if (String.valueOf(dataReconciliationObj[i][16]).equals("Y")) {
					bean1.setOrgResponseFlagHidden("Y");
				} else {
					bean1.setOrgResponseFlagHidden("N");
				}
				if (String.valueOf(dataReconciliationObj[i][17]).equals("Y")) {
					bean1.setOrgRestoreFlagHidden("Y");
				} else {
					bean1.setOrgRestoreFlagHidden("N");
				}
				bean1.setSlaHrs(checkNull((String
						.valueOf(dataReconciliationObj[i][18]))));
				bean1.setCommentsDataRecon(checkNull((String
						.valueOf(dataReconciliationObj[i][19]))));
				
				if (String.valueOf(dataReconciliationObj[i][20]).equals("Y")) {
					bean1.setEtaFlagHidden("Y");
				} 
				else if(String.valueOf(dataReconciliationObj[i][20]).equals("E")){
					bean1.setEtaFlagHidden("E");
				}
				else {
					bean1.setEtaFlagHidden("N");
				}
				
				if (String.valueOf(dataReconciliationObj[i][21]).equals("Y")) {
					bean1.setFccFlagHidden("Y");
				}else if(String.valueOf(dataReconciliationObj[i][21]).equals("E")){
					bean1.setFccFlagHidden("E");
				}
				else {
					bean1.setFccFlagHidden("N");
				}
				bean1.setAccountChildCode(checkNull((String
						.valueOf(dataReconciliationObj[i][22]))));
				
				if (String.valueOf(dataReconciliationObj[i][23]).equals("Y")) {
					bean1.setOrgEtaFlagHidden("Y");
				} else {
					bean1.setOrgEtaFlagHidden("N");
				}
				
				if (String.valueOf(dataReconciliationObj[i][24]).equals("Y")) {
					bean1.setOrgFccFlagHidden("Y");
				} else {
					bean1.setOrgFccFlagHidden("N");
				}
				
				bean1.setPerSummDate(checkNull((String
						.valueOf(dataReconciliationObj[i][25]))));
				
				if (String.valueOf(dataReconciliationObj[i][26]).equals("Y")) {
					bean1.setExcludeCallFlagHidden("Y");
				} else {
					bean1.setExcludeCallFlagHidden("N");
				}
				
				
				list.add(bean1); 

			}
			bean.setDataReconciliationList(list);
			bean.setOtherLengthVar(String.valueOf(dataReconciliationObj.length));
		}
		
		
	}

	
	/**This is used to save Data Reconciliation Details.
	 * @param bean : PerformanceMetrics
	 * @return
	 */
	public boolean saveDataReconciliation(PerformanceMetrics bean, HttpServletRequest request) {
		boolean result = false;
		try{ 
		// Entries to save details in CR_SO_MISSED_SLA Start
				String[] orderId = request.getParameterValues("orderId");
				String[] contactFlag = request.getParameterValues("contactFlagHidden");
				String[] responseFlag = request.getParameterValues("responseFlagHidden");
				String[] restoreFlag = request.getParameterValues("restoreFlagHidden");
				String[] comments = request.getParameterValues("commentsDataRecon");
				
				String[] fccFlag = request.getParameterValues("fccFlagHidden");
				String[] etaFlag = request.getParameterValues("etaFlagHidden");
				String[] psDate = request.getParameterValues("perSummDate");
				String[] excludeFlag = request.getParameterValues("excludeCallFlagHidden");
				
				int k = 0;
				Object dataReconDtlObj[][] = new Object[orderId.length][9];
				
				for (int i = 0; i < orderId.length; i++) {
					
					if(bean.getContactFlagHidden().equals("") || bean.getContactFlagHidden().equals(null)){
						///dataReconDtlObj[k][0] = "N";
					}else{
						dataReconDtlObj[k][0] = contactFlag[i];
					}
					
					if(bean.getResponseFlagHidden().equals("")||bean.getResponseFlagHidden().equals(null)){
						///dataReconDtlObj[k][1] = "N";
					}else{
						dataReconDtlObj[k][1] = checkNull(String.valueOf(responseFlag[i]));
					}
					
					if(bean.getRestoreFlagHidden().equals("")||bean.getRestoreFlagHidden().equals(null)){
						///dataReconDtlObj[k][2] = "N";
						
					}else{
						dataReconDtlObj[k][2] = checkNull(String.valueOf(restoreFlag[i]));
						
					}
					
					dataReconDtlObj[k][3] = checkNull(String.valueOf(comments[i]));
					
					if(bean.getFccFlagHidden().equals("")||bean.getFccFlagHidden().equals(null)){
						///dataReconDtlObj[k][4] = "N";
					}else{
						dataReconDtlObj[k][4] = checkNull(String.valueOf(fccFlag[i]));
					}
					
					if(bean.getExcludeCallFlagHidden().equals("")||bean.getExcludeCallFlagHidden().equals(null)){
						///dataReconDtlObj[k][4] = "N";
					}else{
						dataReconDtlObj[k][5] = checkNull(String.valueOf(excludeFlag[i]));
					}
					
					if(bean.getEtaFlagHidden().equals("")||bean.getEtaFlagHidden().equals(null)){
						///dataReconDtlObj[k][4] = "N";
					}else{
						dataReconDtlObj[k][6] = checkNull(String.valueOf(etaFlag[i]));
					}
					
					dataReconDtlObj[k][7] = orderId[i];
					dataReconDtlObj[k][8] = psDate[i];
					
					
					k++;
				}
				
				String updateQ="UPDATE CR_SO_MISSED_SLA SET CRM_CONT_SLA_FLAG = ? , CRM_RESP_SLA_FLAG = ? , CRM_REST_SLA_FLAG=? , COMMENTS=? , CRM_FCC_FLAG = ? ,EXCLUDE_FLAG=?,CRM_ETA_FLAG = ?  WHERE  ORDER_ID = ? AND PS_DATE = CONVERT(DATE,?,105)";
				result = getSqlModel("POOL_D1").singleExecute(updateQ,dataReconDtlObj);
				// Entries to save details in CR_SO_MISSED_SLA end
				
				// Entries to save details in CR_PERFORMANCE_SUMMARY Start
				String[] accountChildCode = request
						.getParameterValues("accountChildCode");
				String[] closedDate = request.getParameterValues("closedDate");
				String fromDate = bean.getFromDate();
				String toDate = bean.getToDate();
				/*String dataReconCountQuery = " SELECT TO_CHAR(CR_SO_MISSED_SLA.PS_DATE,'DD-MM-YYYY'), ACCOUNT_CODE, "
						+ "	SUM(CASE WHEN SLA_HRS=24 AND CRM_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=24 AND ORG_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END) NBD_CONT_NUM, "
						+ "	SUM(CASE WHEN SLA_HRS=8 AND CRM_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=8 AND ORG_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END) SAMED_CONT_NUM, "
						+ "	SUM(CASE WHEN SLA_HRS=4 AND CRM_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=4 AND ORG_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END)HR4_CONT_NUM, "
						+ "	SUM(CASE WHEN SLA_HRS=24 AND CRM_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=24 AND ORG_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END) NBD_RESP_NUM,  "
						+ "	SUM(CASE WHEN SLA_HRS=8 AND CRM_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END) -SUM(CASE WHEN SLA_HRS=8 AND ORG_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END) SAMED_RESP_NUM, "
						+ "	SUM(CASE WHEN SLA_HRS=4 AND CRM_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=4 AND ORG_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END) HR4_RESP_NUM, "
						+ "	SUM(CASE WHEN CRM_REST_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN ORG_REST_SLA_FLAG='Y' THEN 1 ELSE 0 END) REST_NUM, "
						+ "	SUM(CASE WHEN CRM_FCC_FLAG='Y' THEN 1 ELSE 0 END) - SUM(CASE WHEN ORG_FCC_FLAG='Y' THEN 1 ELSE 0 END) FCC_NUM "
						+ "	FROM CR_SO_MISSED_SLA "
						+ "	WHERE "
						+ "	TRUNC(CR_SO_MISSED_SLA.PS_DATE) >=TO_DATE('"+ fromDate+ "','DD-MM-YYYY') "
						+ "	AND TRUNC(CR_SO_MISSED_SLA.PS_DATE) <=TO_DATE('"+ toDate + "','DD-MM-YYYY')"
						+ "	GROUP BY CR_SO_MISSED_SLA.PS_DATE , ACCOUNT_CODE  ";*/
				
				
				String dataReconCountQuery = "SELECT CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.PS_DATE,105), CR_CLIENT_MASTER.ACCOUNT_CODE, 	 "
					+ "	SUM(CASE WHEN SLA_HRS=24 AND CRM_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=24 AND ORG_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END) NBD_CONT_NUM, 	 "
					+ "	SUM(CASE WHEN SLA_HRS=8 AND CRM_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=8 AND ORG_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END) SAMED_CONT_NUM,  "	
					+ "	SUM(CASE WHEN SLA_HRS=4 AND CRM_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=4 AND ORG_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END)HR4_CONT_NUM, 	 "
					+ "	SUM(CASE WHEN SLA_HRS=24 AND CRM_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=24 AND ORG_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END) NBD_RESP_NUM,  " 	
					+ "	SUM(CASE WHEN SLA_HRS=8 AND CRM_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END) -SUM(CASE WHEN SLA_HRS=8 AND ORG_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END) SAMED_RESP_NUM,  "	
					+ "	SUM(CASE WHEN SLA_HRS=4 AND CRM_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=4 AND ORG_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END) HR4_RESP_NUM, " +
					"	SUM(CASE WHEN CRM_REST_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN ORG_REST_SLA_FLAG='Y' THEN 1 ELSE 0 END) REST_NUM,  "	
					+ "	SUM(CASE WHEN CRM_FCC_FLAG='Y' THEN 1 ELSE 0 END) - SUM(CASE WHEN ORG_FCC_FLAG='Y' THEN 1 ELSE 0 END) FCC_NUM, 	 "
					+ "	SUM(CASE WHEN EXCLUDE_FLAG='Y' THEN 1 ELSE 0 END)  EXCLUDE_NUM ,	 "
					+ "	SUM(CASE WHEN EXCLUDE_FLAG='Y' AND SLA_HRS=24 THEN 1 ELSE 0 END)  EXCLUDE_NBD_NUM ,	 "
					+ "	SUM(CASE WHEN EXCLUDE_FLAG='Y' AND SLA_HRS=8 THEN 1 ELSE 0 END)  EXCLUDE_SAMED_NUM 	, "
					+ "	SUM(CASE WHEN EXCLUDE_FLAG='Y' AND SLA_HRS=4 THEN 1 ELSE 0 END)  EXCLUDE_HR4_NUM, 	 "
					+ "	SUM(CASE WHEN isnull(CRM_ETA_FLAG,'N')='Y' THEN 1 ELSE 0 END) - SUM(CASE WHEN isnull(ORG_ETA_FLAG,'N')='Y' THEN 1 ELSE 0 END) ETA_NUM 	 "
					+ "	FROM CR_SO_MISSED_SLA,CR_CLIENT_MASTER   "	
					+ "	WHERE 	CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) >=CONVERT(DATE,'"+ fromDate+ "',105)  "	
					+ "	AND CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) <=CONVERT(DATE,'"+ toDate + "',105)  "
					+ "	AND CR_SO_MISSED_SLA.ACCOUNT_CODE=CR_CLIENT_MASTER.ACCOUNT_CODE AND CR_CLIENT_MASTER.PARENT_CODE='"+bean.getAccountCode()+"' "
					+ "	GROUP BY CR_SO_MISSED_SLA.PS_DATE , CR_CLIENT_MASTER.ACCOUNT_CODE "
					+ "	UNION ALL "
					+ "	SELECT CONVERT(VARCHAR(10),CR_SO_MISSED_SLA.PS_DATE,105), CR_CLIENT_MASTER.PARENT_CODE, 	 "
					+ "	SUM(CASE WHEN SLA_HRS=24 AND CRM_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=24 AND ORG_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END) NBD_CONT_NUM,  "	
					+ "	SUM(CASE WHEN SLA_HRS=8 AND CRM_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=8 AND ORG_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END) SAMED_CONT_NUM,  "	
					+ "	SUM(CASE WHEN SLA_HRS=4 AND CRM_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=4 AND ORG_CONT_SLA_FLAG='Y' THEN 1 ELSE 0 END)HR4_CONT_NUM, 	 "
					+ "	SUM(CASE WHEN SLA_HRS=24 AND CRM_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=24 AND ORG_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END) NBD_RESP_NUM,  " 	
					+ "	SUM(CASE WHEN SLA_HRS=8 AND CRM_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END) -SUM(CASE WHEN SLA_HRS=8 AND ORG_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END) SAMED_RESP_NUM,  "	
					+ "	SUM(CASE WHEN SLA_HRS=4 AND CRM_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN SLA_HRS=4 AND ORG_RESP_SLA_FLAG='Y' THEN 1 ELSE 0 END) HR4_RESP_NUM, " +
					"	SUM(CASE WHEN CRM_REST_SLA_FLAG='Y' THEN 1 ELSE 0 END)-SUM(CASE WHEN ORG_REST_SLA_FLAG='Y' THEN 1 ELSE 0 END) REST_NUM,  "	
					+ "	SUM(CASE WHEN CRM_FCC_FLAG='Y' THEN 1 ELSE 0 END) - SUM(CASE WHEN ORG_FCC_FLAG='Y' THEN 1 ELSE 0 END) FCC_NUM, 	 "
					+ "	SUM(CASE WHEN EXCLUDE_FLAG='Y' THEN 1 ELSE 0 END)  EXCLUDE_NUM ,	 "
					+ "	SUM(CASE WHEN EXCLUDE_FLAG='Y' AND SLA_HRS=24 THEN 1 ELSE 0 END)  EXCLUDE_NBD_NUM ,	 "
					+ "	SUM(CASE WHEN EXCLUDE_FLAG='Y' AND SLA_HRS=8 THEN 1 ELSE 0 END)  EXCLUDE_SAMED_NUM ,	 "
					+ "	SUM(CASE WHEN EXCLUDE_FLAG='Y' AND SLA_HRS=4 THEN 1 ELSE 0 END)  EXCLUDE_HR4_NUM, 	 "
					+"	SUM(CASE WHEN isnull(CRM_ETA_FLAG,'N')='Y' THEN 1 ELSE 0 END) - SUM(CASE WHEN isnull(ORG_ETA_FLAG,'N')='Y' THEN 1 ELSE 0 END) ETA_NUM 	 "
					+ "	FROM CR_SO_MISSED_SLA,CR_CLIENT_MASTER  	 "
					+ "	WHERE 	CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) >=CONVERT(DATE,'"+ fromDate+ "',105)  "	
					+ "	AND CONVERT(DATE,CR_SO_MISSED_SLA.PS_DATE,105) <=CONVERT(DATE,'"+ toDate + "',105)  "
					+ "	AND CR_SO_MISSED_SLA.ACCOUNT_CODE=CR_CLIENT_MASTER.ACCOUNT_CODE AND CR_CLIENT_MASTER.PARENT_CODE='"+bean.getAccountCode()+"' "
					+ "	GROUP BY CR_SO_MISSED_SLA.PS_DATE , CR_CLIENT_MASTER.PARENT_CODE ";
				
				
				Object[][] dataReconciliationObj = getSqlModel("POOL_D1").getSingleResult(
						dataReconCountQuery);
				int n = 0;
				Object updateApprover[][] = new Object[dataReconciliationObj.length][15];
				for (int i = 0; i < dataReconciliationObj.length; i++) {

					updateApprover[n][0] = dataReconciliationObj[i][2];
					updateApprover[n][1] = dataReconciliationObj[i][3];
					updateApprover[n][2] = dataReconciliationObj[i][4];
					updateApprover[n][3] = dataReconciliationObj[i][5];
					updateApprover[n][4] = dataReconciliationObj[i][6];
					updateApprover[n][5] = dataReconciliationObj[i][7];
					updateApprover[n][6] = dataReconciliationObj[i][8];
					updateApprover[n][7] = dataReconciliationObj[i][9];
					updateApprover[n][8] = dataReconciliationObj[i][10];
					updateApprover[n][9] = dataReconciliationObj[i][11];
					updateApprover[n][10] = dataReconciliationObj[i][12];
					updateApprover[n][11] = dataReconciliationObj[i][13];
					updateApprover[n][12] = dataReconciliationObj[i][14];
					
					updateApprover[n][13] = dataReconciliationObj[i][0];
					updateApprover[n][14] = dataReconciliationObj[i][1];

					n++;
				}
				
				
				/*for (int i = 0; i < updateApprover.length; i++) {
				for (int j = 0; j < updateApprover[i].length; j++) {
					logger.info("updateApprover[" + i + "][" + j
							+ "]  " + updateApprover[i][j]);
				}
			}*/
				
				String updateSummaryQ = "UPDATE CR_PERFORMANCE_SUMMARY SET RECONCILE_NBD_CONT_NUM =? , RECONCILE_SAMED_CONT_NUM =? ,RECONCILE_HR4_CONT_NUM =? ,RECONCILE_NBD_RESP_NUM =? ,RECONCILE_SAMED_RESP_NUM =? ,RECONCILE_HR4_RESP_NUM =? ,RECONCILE_REST_NUM =? , RECONCILE_FCC_NUM =?,RECONCILE_EXCLUDE_NUM=?, RECONCILE_EXCLUDE_NBD =?, RECONCILE_EXCLUDE_SAMED =?, RECONCILE_EXCLUDE_HR4 =? ,RECONCILE_ETA_NUM =?  WHERE  PS_DATE = convert(date,?,105) AND ACCOUNT_ID =? ";
				getSqlModel("POOL_D1").singleExecute(updateSummaryQ, updateApprover);
				// 24 Count end
			
			// Entries to save details in CR_PERFORMANCE_SUMMARY end
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	}


	/* for inserting the data in closure table. 
	 * @param bean : PerformanceMetrics
	 * @return result
	 */
	public boolean addNewClosureCode(PerformanceMetrics bean,String userEmpID) {
		boolean result = false;
		String query = "SELECT * FROM CR_CAUSE_MASTER WHERE UPPER(CR_CAUSE_MASTER.CAUSE_ID) LIKE '"
				+ bean.getAddClosureCode().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel("POOL_D1").getSingleResult(query);
		if (data.length ==0) {
			Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = bean.getAddClosureCode();
			saveObj[0][1] = userEmpID;
			saveObj[0][2] = bean.getAddClosureCodeDesc();
			
			String insertRecordsQuery = "INSERT INTO CR_CAUSE_MASTER(CAUSE_CODE, CAUSE_ID,RECORD_CREATEDBY, RECORD_CREATEDON,DESCR)"
		+ " VALUES((SELECT ISNULL(MAX(CAUSE_CODE),0)+1 FROM CR_CAUSE_MASTER),?,?,GETDATE(),?)";
			
			result = getSqlModel("POOL_D1").singleExecute(insertRecordsQuery, saveObj);
			
		}//end of if
		else {
			return false;
		}//end of else
		return result;
	}
	
	/**This is used to set list of call type from master table CR_CAUSE_MASTER.
	 * @param bean : PerformanceMetrics
	 */
	public void setClosureTypeList(PerformanceMetrics bean) {
		try {
			
			/*String queryRepairMaster = " SELECT CAUSE_CODE, CAUSE_ID ||' - '|| CR_CAUSE_MASTER.DESCR  FROM CR_CAUSE_MASTER  " +
					" WHERE CR_CAUSE_MASTER.CAUSE_CODE NOT IN (SELECT CR_CLIENT_CAUSE.CAUSE_CODE FROM CR_CLIENT_CAUSE  WHERE ACCOUNT_CODE ="+bean.getAccountCode() +") " +
					" ORDER BY UPPER(CAUSE_CODE)";*/
			
			String queryRepairMaster = " SELECT CR_CLIENT_CAUSE.CAUSE_CODE , CR_CAUSE_MASTER.CAUSE_ID +' - '+ CR_CAUSE_MASTER.DESCR  FROM CR_CLIENT_CAUSE  "
			+" INNER JOIN CR_CAUSE_MASTER  ON  (CR_CAUSE_MASTER.CAUSE_CODE = CR_CLIENT_CAUSE.CAUSE_CODE) " 
			+" WHERE CR_CLIENT_CAUSE.ACCOUNT_CODE ="+bean.getAccountCode() ;

			
			
			Object[][] closureObj = getSqlModel("POOL_D1").getSingleResult(queryRepairMaster);
			String selClosureId = "0";
			HashMap mpClosure = new HashMap();
			if(closureObj!=null && closureObj.length > 0){
			for (int i = 0; i < closureObj.length; i++) {
				mpClosure.put(String.valueOf(closureObj[i][0]), String
						.valueOf(closureObj[i][1]));
				selClosureId += "," + closureObj[i][0];
			}
			mpClosure = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(mpClosure, null, true);
			bean.setHashmapClosureCodeSel(mpClosure);
			}
			else{
				/*mpClosure.put("","");
				bean.setHashmapClosureCodeSel(mpClosure);*/
	
			}
			
			String querClosureAvail = "SELECT CAUSE_CODE, CAUSE_ID +' - '+ CR_CAUSE_MASTER.DESCR  FROM CR_CAUSE_MASTER "
					+ "WHERE CAUSE_CODE NOT IN ("
					+ selClosureId
					+ ")ORDER BY CAUSE_CODE ";
			Object[][] closureMasterObj = getSqlModel("POOL_D1").getSingleResult(
					querClosureAvail);
			HashMap mpClosureMaster = new HashMap();
			if(closureMasterObj!=null && closureMasterObj.length > 0){
				
				for (int i = 0; i < closureMasterObj.length; i++) {
					mpClosureMaster.put(String.valueOf(closureMasterObj[i][0]), String
							.valueOf(closureMasterObj[i][1]));
	
				}
				mpClosureMaster = (HashMap<Object, Object>) org.paradyne.lib.Utility
						.sortMapByValue(mpClosureMaster, null, true);
				bean.setHashmapClosureCode(mpClosureMaster);
			}
			else{
				/*mpClosureMaster.put("","");
				bean.setHashmapClosureCode(mpClosureMaster);*/
	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void callAccountName(PerformanceMetrics bean, String requestID,
			HttpServletRequest request) {
		try {
			String accountQuery = "SELECT ACCOUNT_CODE , ACCOUNT_NAME,PARENT_FLAG,ACCOUNT_ID  FROM CR_CLIENT_MASTER WHERE ACCOUNT_CODE ="
					+ requestID;
			Object[][] accountObj = getSqlModel("POOL_D1").getSingleResult(accountQuery);
			if (accountObj != null && accountObj.length > 0) {
				bean.setAccountCode(String.valueOf(accountObj[0][0]));
				bean.setAccountName(String.valueOf(accountObj[0][1]));
				bean.setParentFlag(String.valueOf(accountObj[0][2]));
				bean.setAccountId(String.valueOf(accountObj[0][3]));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}


	/**This method is used to publish data and save in CR_PERFORMANCE_SUMMARY.
	 * @param bean : PerformanceMetrics
	 * @return : result
	 */
	public boolean publishReconData(PerformanceMetrics bean) {
		boolean result = false;
		try {
			String fromDate = bean.getFromDate();
			String toDate = bean.getToDate();
			
			String selChildQuery = "SELECT ACCOUNT_CODE FROM CR_CLIENT_MASTER " 
						+" WHERE PARENT_CODE="+ bean.getAccountCode()  
						+" UNION SELECT "  + bean.getAccountCode() + " ACCOUNT_CODE"; 
			
			Object dataReconDtlObj[][] = getSqlModel("POOL_D1").getSingleResult(selChildQuery);
			int k = 0;
			if (dataReconDtlObj != null && dataReconDtlObj.length > 0) {
				for (int i = 0; i < dataReconDtlObj.length; i++) {
					dataReconDtlObj[k][0] = dataReconDtlObj[i][0];
					k++;
				}
				
				String updateSummaryQ = "UPDATE CR_PERFORMANCE_SUMMARY SET IS_PUBLISHED ='Y'  WHERE  PS_DATE >= convert(date,'"+fromDate+"',105) AND PS_DATE <= convert(date,'"+toDate+"',105) AND ACCOUNT_ID =? ";
				result = getSqlModel("POOL_D1").singleExecute(updateSummaryQ, dataReconDtlObj);
			
				/*for (int i = 0; i < dataReconDtlObj.length; i++) {
				for (int j = 0; j < dataReconDtlObj[i].length; j++) {
					logger.info("dataReconDtlObj[" + i + "][" + j
							+ "]  " + dataReconDtlObj[i][j]);
				}
			}*/
				
			}//end of if
			else {
				return false;
			}//end of else
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	
	/**This is used to show report.
	 * @param bean
	 * @param response
	 * @param request
	 * @param reportPath
	 */
	public void getReport(PerformanceMetrics bean,
			HttpServletResponse response, HttpServletRequest request,
			String reportPath,String userEmpID) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getReport();
			rds.setReportType(type);
			String fileName = "Closed Call Summary Report " + Utility.getRandomNumber(1000);
			String reportPathName = reportPath + fileName + "." + type;
			rds.setFileName(fileName);
			rds.setReportName("Closed Call Summary Report");
			rds.setUserEmpId(userEmpID);
			rds.setPageSize("A4");
			///rds.setPageSize("TABLOID");
			rds.setPageOrientation("portrait");
			rds.setTotalColumns(80);
			/*
			 * rds.setMarginBottom(25f); rds.setMarginLeft(25f);
			 * rds.setMarginRight(25f);
			 */
			rds.setShowPageNo(true);
			// rds.setMarginTop(25f);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context, request);
			} else {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "."
						+ type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action",
						"OtCalculations_input.action");
			}
			rg = getReport(rg, bean);
			rg.process();
			if (reportPath.equals("")) {
				rg.createReport(response);
			} else {
				/* Generates the report as attachment */
				rg.saveReport(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**This is used to show report.
	 * @param rg
	 * @param bean : OtRegister
	 * @return
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getReport(final 
			org.paradyne.lib.ireportV2.ReportGenerator rg,
			final PerformanceMetrics bean) {
		try {
		      String fromDate = bean.getFromDate();
		      String toDate = bean.getToDate();

		      String filters = "Period: " + fromDate + " to " + toDate;

		      TableDataSet filterData = new TableDataSet();
		      filterData.setData(new Object[][] { { filters } });
		      filterData.setCellAlignment(new int[1]);
		      filterData.setCellWidth(new int[] { 100 });
		      filterData.setCellColSpan(new int[] { 80 });
		      filterData.setBorder(Boolean.valueOf(false));
		      rg.addTableToDoc(filterData);
		      
		      /*String bankNameQuery = "SELECT TO_CHAR(FIRST_NAME||' '||LAST_NAME),ACCOUNT_ID,REPORT_NAME,  TO_CHAR(GENEARTE_DATE,'DD-MM-YYYY  HH24: MM')  FROM CR_REPORT_HISTORY  INNER JOIN CR_CLIENT_MASTER ON(CR_CLIENT_MASTER.ACCOUNT_CODE=CR_REPORT_HISTORY.ACCOUNT_CODE) INNER JOIN CR_REPORT_MASTER ON(CR_REPORT_MASTER.REPORT_CODE=CR_REPORT_HISTORY.REPORT_CODE) INNER JOIN CR_CLIENT_USERS ON (CR_CLIENT_USERS.CRUSER_CODE = CR_REPORT_HISTORY.CUSTOMER_CODE)    WHERE  CR_REPORT_HISTORY.GENEARTE_DATE >=TO_DATE('" + 
		        fromDate + "','DD-MM-YYYY')  " + 
		        "\tAND CR_REPORT_HISTORY.GENEARTE_DATE <=TO_DATE('" + toDate + "','DD-MM-YYYY')";*/
		      
		     
		      Object[][] closedCallData = (Object[][])null;
		      try {
		        /*String branchDataQuery = "SELECT rownum,TO_CHAR(FIRST_NAME||' '||LAST_NAME),ACCOUNT_ID,REPORT_NAME,  TO_CHAR(GENEARTE_DATE,'DD-MM-YYYY  HH24: MM')  FROM CR_REPORT_HISTORY  INNER JOIN CR_CLIENT_MASTER ON(CR_CLIENT_MASTER.ACCOUNT_CODE=CR_REPORT_HISTORY.ACCOUNT_CODE) INNER JOIN CR_REPORT_MASTER ON(CR_REPORT_MASTER.REPORT_CODE=CR_REPORT_HISTORY.REPORT_CODE) INNER JOIN CR_CLIENT_USERS ON (CR_CLIENT_USERS.CRUSER_CODE = CR_REPORT_HISTORY.CUSTOMER_CODE)    " +
		        		"WHERE  CR_REPORT_HISTORY.GENEARTE_DATE >=TO_DATE('" + fromDate + "','DD-MM-YYYY')  " + 
		          "\tAND CR_REPORT_HISTORY.GENEARTE_DATE <=TO_DATE('" + toDate + "','DD-MM-YYYY')";*/
		        
		        
		        String closedCallQuery = "SELECT ORDER_ID,convert(varchar(10),OPEN_DT_TM,101)+' '+convert(varchar(5),OPEN_DT_TM,108) OPEN_DT_TM," +
		        		"  convert(varchar(10),ESTIMATED_DT,101)+' '+convert(varchar(5),ESTIMATED_TM,108) ESTIMATED_DT_TM, " +
		        		"convert(varchar(10),CONTACT_CUST_FIRST_DT_TM,101)+' '+convert(varchar(5),CONTACT_CUST_FIRST_DT_TM,108) CONTACT_CUST_FIRST_DT_TM, " +
		        		"convert(varchar(10),ARRIVE_DT_TM,101)+' '+convert(varchar(5),ARRIVE_DT_TM,108) ARRIVE_DT_TM, " +
		        		"convert(varchar(10),SERVICE_ACT_FIRST_DT_TM,101)+' '+convert(varchar(5),SERVICE_ACT_FIRST_DT_TM,108) SERVICE_ACT_FIRST_DT_TM," +
		        		" convert(varchar(10),RESOLVE_DT_TM,101)+' '+convert(varchar(5),RESOLVE_DT_TM,108) RESOLVE_DT_TM, " +
		        		"convert(varchar(10),UNAVAIL_FIRST_DT_TM,101)+' '+convert(varchar(5),UNAVAIL_FIRST_DT_TM,108) UNAVAIL_FIRST_DT_TM," +
		        		" convert(varchar(10),UNAVAIL_LAST_DT_TM,101)+' '+convert(varchar(5),UNAVAIL_LAST_DT_TM,108) UNAVAIL_LAST_DT_TM, " +
		        		"convert(varchar(10),SERVICE_COMPLETE_DT_TM,101)+' '+convert(varchar(5),SERVICE_COMPLETE_DT_TM,108) SERVICE_COMPLETE_DT_TM, " +
		        		"convert(varchar(10),INVOICED_DTM,101)+' '+convert(varchar(5),INVOICED_DTM,108) INVOICED_DT_TM, " +
		        		"convert(varchar(10),LAST_ACTION_DT,101)+' '+convert(varchar(5),LAST_ACTION_DT,108) LAST_ACTION_DT_TM, " +
		        		"ACTGR_ID,CALLER_NAME,CALLT_ID,CAUSE_ID,COMPANY_GRP_ID,CUST_COMPANY_DESCR,CUST_COMPANY_ID,HISTORY_LOAD, " +
		        		"LABOR_HOURS,LOAD_DT,MANAGER_ID,MANUFACTURER,MAX_ENTERED_ETA,MGR_FNAME,MGR_LNAME,ORDER_STAT_ID,ORDER_STATUS, " +
		        		"PARENT_COMPANY_ID,PCODE_ID,PHONE,PO_NUMBER,PROBLEM_DESC,REFNO,REPAIR_CODE,REQUEST_ID,RESOLVE_TEXT,SA_FNAME,SA_LNAME, " +
		        		"SA_PERSON_ID,SERIAL_NO,SERVICE_ADDRESS,SERVICE_CITY,SERVICE_ID,SERVICE_STATE,SERVICE_ZIP,SITE_ADDRESS,SITE_CITY," +
		        		"SITE_DESCR,SITE_ID,SITE_STATE, SITE_ZIPCODE,TAGNO,TRAVEL_HOURS,TRAVEL_MILES,CONTACT_CUST_CNT,CONTACT_CUST_FIRST_DAYS_GROSS," +
		        		"CONTACT_CUST_FIRST_DAYS_NET, CONTACT_CUST_FIRST_HRS_GROSS,CONTACT_CUST_FIRST_HRS_NET,NODE_ID,ONSITE_DAYS_GROSS,ONSITE_DAYS_NET," +
		        		"ONSITE_HRS_GROSS, ONSITE_HRS_NET,OPEN_AFTER3,OPEN2_SERVCOMP_DAYS_GROSS,OPEN2_SERVCOMP_DAYS_NET,OPEN2_SERVCOMP_HRS_GROSS," +
		        		"OPEN2_SERVCOMP_HRS_NET, RESOLVE_DAYS_GROSS,RESOLVE_DAYS_NET,RESOLVE_HRS_GROSS,RESOLVE_HRS_NET,SERVICE_ACT_CNT," +
		        		"SERVICE_ACT_FIRST_DAYS_GROSS, SERVICE_ACT_FIRST_DAYS_NET,SERVICE_ACT_FIRST_HRS_GROSS,SERVICE_ACT_FIRST_HRS_NET," +
		        		"UNAVAIL_CNT, UNAVAIL_FIRST_DAYS_GROSS,UNAVAIL_FIRST_DAYS_NET,UNAVAIL_FIRST_DEFR_CODE,UNAVAIL_FIRST_HRS_GROSS," +
		        		"UNAVAIL_FIRST_HRS_NET, UNAVAIL_LAST_DAYS_GROSS,UNAVAIL_LAST_DAYS_NET,UNAVAIL_LAST_DEFR_CODE,UNAVAIL_LAST_HRS_GROSS," +
		        		"UNAVAIL_LAST_HRS_NET, USED_PARTS_COST,USED_PARTS_QTY,ACCOUNT_CODE,SLA,SLA_HRS,ORG_CONT_SLA_FLAG,ORG_RESP_SLA_FLAG," +
		        		"ORG_REST_SLA_FLAG,ORG_FCC_FLAG, CUSTOMER_CONTACT_SLA_HOURS,ARRIVE_ONSITE_SLA_HOURS,GROSS_CUSTOMER_WAIT,GROSS_PART_WAIT," +
		        		"  CUSTOMER_WAIT,PART_WAIT,RESTOREHRS,COMMENTS, DECODE(EXCLUDE_FLAG,'Y','Yes','N','No','','No') AS EXCLUDED_CALLS FROM CR_V_CLOSED_CALL_DAILY  "
						+" WHERE  convert(date,RESOLVE_DT,105) >=convert(varchar(10),'" + fromDate + "',105) " + 
							"\tAND convert(date,RESOLVE_DT,105) <=convert(varchar(10),'" + toDate + "',105) AND  PARENT_COMPANY_ID ='"+bean.getAccountId()+"' ";
		        

		        closedCallData = getSqlModel("POOL_D1").getSingleResultWithCol(
		        		closedCallQuery);
		     ///   getSqlModel("POOL_D1").getSingleResultWithCol(callDtlQuery, new Object[]{callId});

		        Object[][] objTabularData = new Object[closedCallData.length][closedCallData[0].length];
		       

		        int[] bcellAlignDebit = new int[closedCallData[0].length];
				int[] bcellWidthDebit = new int[closedCallData[0].length];
				
				for (int i = 0; i < closedCallData[0].length; i++) {
					// bcellAlign[i] = 1;
					// bcellWidth[i] = 40;
					if (i == 0) {
						bcellAlignDebit[i] = 0;
						bcellWidthDebit[i] = 10;
						
					//} else if (i == 1) {
					}else if (i == 1) {
						bcellAlignDebit[i] = 0;
						bcellWidthDebit[i] = 40;
						
					}
					
					
				}
		        if ((closedCallData[0] != null) && (closedCallData[0].length > 0))
		        { 
		        	
		        	for (int i = 0; i < objTabularData.length; i++) {
		        		for (int j = 0; j <objTabularData[0].length; j++) {
		        			 objTabularData[i][j] = String.valueOf(closedCallData[i][j]);
						}
		               
		             //   objTabularData[i][1] = String.valueOf(closedCallData[i][1]);
		                
		              }
		        	
		          

		          TableDataSet tdstable = new TableDataSet();

		          //tdstable.setHeaderFontDetails(Font.FontFamily.HELVETICA, 18, Font.NORMAL, new BaseColor(200,200,200));
		        ///  tdstable.setHeaderBGColor(new BaseColor(200,200,200));
		        /// tdstable.setBodyBGColor(new BaseColor(200,200,200));
		        
		          tdstable.setData(objTabularData);
		          tdstable.setHeaderBorderDetail(3);
		          tdstable.setCellAlignment(bcellAlignDebit);
		          tdstable.setCellWidth(bcellWidthDebit);
		          tdstable.setHeaderTable(true);
		          tdstable.setBorderDetail(3);
		          tdstable.setBlankRowsAbove(1);
		          rg.addTableToDoc(tdstable);
		        }
		        else {
		          TableDataSet noData = new TableDataSet();
		          Object[][] noDataObj = new Object[1][1];
		          noDataObj[0][0] = "Closed Call Summary Report : No records available";

		          noData.setData(noDataObj);
		          noData.setCellAlignment(new int[1]);
		          noData.setCellWidth(new int[] { 100 });
		          noData.setBlankRowsAbove(1);
		          noData.setBorder(Boolean.valueOf(false));
		          rg.addTableToDoc(noData);
		        }
		      }
		      catch (Exception e) {
		        logger
		          .error("exception in closedCallSummaryReport object", 
		          e);
		      }
		    } catch (final Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
	
	
	public boolean checkTypeAndHrs(PerformanceMetrics bean,HttpServletRequest request)
	{
		boolean result= false;
		String  slaType=(String)request.getParameter("slaType");
		String  hrs=(String)request.getParameter("hrs");
		String accountCode=(String)request.getParameter("accountCode");
		String autoId="";
		String query="";
		try {
			autoId = (String) request.getParameter("autoId");
			
		} catch (Exception e) {
			
		}
			if(autoId.equals("")){
		 query="SELECT AUTO_ID,ACCOUNT_CODE,SLA_TYPE," +
				" SLA_HRS,GREEN_VAL_START,GREEN_VAL_END,YELLOW_VAL_START,YELLOW_VAL_END," +
				" RED_VAL_START,RED_VAL_END,DISPLAY_CAPTION,CRM_FLAG,CLIENT_FLAG FROM" +
				" CR_PERFORMANCE_SLA WHERE SLA_TYPE= '"+slaType+"' AND SLA_HRS = '"+hrs+"' AND ACCOUNT_CODE = '"+accountCode+"'";
		
		 Object[][] data = getSqlModel("POOL_D1").getSingleResult(query);
			if(data != null && data.length>0 )
				{
				result= true;
				}
			}
		 
		
		return result;
		
	}
	
	
	public boolean saveSLA(PerformanceMetrics bean,
		HttpServletRequest request,String userEmpID,String accountCode){
		boolean result= false;
		
		
		if(!checkTypeAndHrs(bean, request)){
		String  captionName = (String)request.getParameter("captionName");
		String  slaCrmFlagHidden=(String)request.getParameter("slaCrmFlagHidden");
		String  slaClientFlagHidden=(String)request.getParameter("slaClientFlagHidden");
		String  slaType=(String)request.getParameter("slaType");
		String  hrs=(String)request.getParameter("hrs");
		String  slaGreenValStart=(String)request.getParameter("slaGreenValStart");
		String  slaGreenValEnd=(String)request.getParameter("slaGreenValEnd");
		String  slaYellowValStart=(String)request.getParameter("slaYellowValStart");
		String  slaYellowValEnd=(String)request.getParameter("slaYellowValEnd");
		String  slaRedValStart=(String)request.getParameter("slaRedValStart");
		String  slaRedValEnd=(String)request.getParameter("slaRedValEnd");
		
		Object[][] saveSlaObj = new Object[1][12];;

		saveSlaObj[0][0]= accountCode;
		saveSlaObj[0][1]= slaType;
		saveSlaObj[0][2]= hrs;
		saveSlaObj[0][3]= slaGreenValStart;
		saveSlaObj[0][4]= slaGreenValEnd;
		saveSlaObj[0][5]= slaYellowValStart;
		saveSlaObj[0][6]= slaYellowValEnd;
		saveSlaObj[0][7]= slaRedValStart;
		saveSlaObj[0][8]= slaRedValEnd;
		saveSlaObj[0][9]= captionName;
		saveSlaObj[0][10]= slaCrmFlagHidden;
		saveSlaObj[0][11]= slaClientFlagHidden;
		
		
		
		String autoId="";
		try {
			autoId = (String) request.getParameter("autoId");
			
		} catch (Exception e) {
			
		}
		String query="";
		if(autoId.equals(""))
		{
			 query = " INSERT INTO CR_PERFORMANCE_SLA(AUTO_ID,ACCOUNT_CODE,SLA_TYPE," +
				"SLA_HRS,GREEN_VAL_START,GREEN_VAL_END,YELLOW_VAL_START,YELLOW_VAL_END," +
				"RED_VAL_START,RED_VAL_END,DISPLAY_CAPTION,CRM_FLAG,CLIENT_FLAG) " +
				"VALUES((SELECT isnull(max(auto_id),0)+1 FROM CR_PERFORMANCE_SLA)," +
				"?,?,?,?,?,?,?,?,?,?,?,?);";		
		}
		else{
			 query = " UPDATE CR_PERFORMANCE_SLA SET AUTO_ID="+autoId+", ACCOUNT_CODE= ?, SLA_TYPE=?, " +
			"SLA_HRS = ?, GREEN_VAL_START=? ,GREEN_VAL_END= ?, YELLOW_VAL_START=?, YELLOW_VAL_END=?, " +
			" RED_VAL_START=?, RED_VAL_END=?, DISPLAY_CAPTION =?, CRM_FLAG=? , CLIENT_FLAG=? WHERE AUTO_ID="+autoId+" "; 
			
			bean.setAutoId("");
		}
		
		result = getSqlModel("POOL_D1").singleExecute(query,
				saveSlaObj);
		}
		return result;
	}
	
	public boolean editSla(PerformanceMetrics bean,HttpServletRequest request, String autoId, String accountCode) {
		
		String query= "SELECT DISPLAY_CAPTION,CRM_FLAG,CLIENT_FLAG,SLA_TYPE,SLA_HRS,GREEN_VAL_START," +
		"GREEN_VAL_END,YELLOW_VAL_START,YELLOW_VAL_END,RED_VAL_START,RED_VAL_END,AUTO_ID FROM CR_PERFORMANCE_SLA WHERE ACCOUNT_CODE= "+accountCode+" AND AUTO_ID = "+autoId+"" ;
		boolean result= false;
Object[][] data = getSqlModel("POOL_D1").getSingleResult(query);
if (data != null && data.length > 0) {
	result =true;
	ArrayList<PerformanceMetrics> list = new ArrayList<PerformanceMetrics>();
	for (int i = 0; i < data.length; i++) {
		
		// bean1.setParentCode(checkNull((String
		// .valueOf(repData[i][1]))));
		// bean1.setParentName(checkNull((String
		// .valueOf(repData[i][2]))));
		bean.setCaptionName(checkNull((String
				.valueOf(data[i][0]))));
		if (String.valueOf(data[i][1]).equals("Y")) {

			bean.setSlaCrmFlagHidden("Yes");
			// /bean1.setCrmFlag("true");
		} else {

			bean.setSlaCrmFlagHidden("No");
			// /bean1.setCrmFlag("false");
		}

		if (String.valueOf(data[i][2]).equals("Y")) {

			bean.setSlaClientFlagHidden("Yes");
			// /bean1.setClientFlag("true");
		} else {

			bean.setSlaClientFlagHidden("No");
			// /bean1.setClientFlag("false");
		}
		
		bean.setSlaType(checkNull((String
				.valueOf(data[i][3]))));
		bean.setHrs(checkNull((String
				.valueOf(data[i][4]))));
		bean.setSlaGreenValStart(checkNull((String
				.valueOf(data[i][5]))));
		bean.setSlaGreenValEnd(checkNull((String
						.valueOf(data[i][6]))));
		bean.setSlaYellowValStart(checkNull((String
				.valueOf(data[i][7]))));
		bean.setSlaYellowValEnd(checkNull((String
				.valueOf(data[i][8]))));
		bean.setSlaRedValStart(checkNull((String
				.valueOf(data[i][9]))));
		bean.setSlaRedValEnd(checkNull((String
				.valueOf(data[i][10]))));
		bean.setAutoId(checkNull(String.valueOf(data[i][11])));
		
		
		}
		
	}

	return result;
}
	
	
	public boolean deleteSla(PerformanceMetrics bean,HttpServletRequest request, String autoId) {
		
		String query="DELETE FROM CR_PERFORMANCE_SLA WHERE AUTO_ID="+autoId+"";
		boolean result = getSqlModel("POOL_D1").singleExecute(query);
				
		bean.setAutoId("");
		return result;
	}
	
	
	public void callForSla(PerformanceMetrics bean, HttpServletRequest request, String accountCode)
	{
		
		String query= "SELECT DISPLAY_CAPTION,CRM_FLAG,CLIENT_FLAG,SLA_TYPE,SLA_HRS,GREEN_VAL_START," +
				"GREEN_VAL_END,YELLOW_VAL_START,YELLOW_VAL_END,RED_VAL_START,RED_VAL_END,AUTO_ID FROM CR_PERFORMANCE_SLA WHERE ACCOUNT_CODE= "+accountCode+"" ;
		
		Object[][] data = getSqlModel("POOL_D1").getSingleResult(query);
		if (data != null && data.length > 0) {
			ArrayList<PerformanceMetrics> list = new ArrayList<PerformanceMetrics>();
			for (int i = 0; i < data.length; i++) {
				PerformanceMetrics bean1 = new PerformanceMetrics();
				// bean1.setParentCode(checkNull((String
				// .valueOf(repData[i][1]))));
				// bean1.setParentName(checkNull((String
				// .valueOf(repData[i][2]))));
				bean1.setCaptionName(checkNull((String
						.valueOf(data[i][0]))));
				if (String.valueOf(data[i][1]).equals("Y")) {

					bean1.setSlaCrmFlagHidden("Yes");
					// /bean1.setCrmFlag("true");
				} else {

					bean1.setSlaCrmFlagHidden("No");
					// /bean1.setCrmFlag("false");
				}

				if (String.valueOf(data[i][2]).equals("Y")) {

					bean1.setSlaClientFlagHidden("Yes");
					// /bean1.setClientFlag("true");
				} else {

					bean1.setSlaClientFlagHidden("No");
					// /bean1.setClientFlag("false");
				}
				
				bean1.setSlaType(checkNull((String
						.valueOf(data[i][3]))));
				bean1.setHrs(checkNull((String
						.valueOf(data[i][4]))));
				bean1.setSlaGreenValStart(checkNull((String
						.valueOf(data[i][5]))));
				bean1.setSlaGreenValEnd(checkNull((String
								.valueOf(data[i][6]))));
				bean1.setSlaYellowValStart(checkNull((String
						.valueOf(data[i][7]))));
				bean1.setSlaYellowValEnd(checkNull((String
						.valueOf(data[i][8]))));
				bean1.setSlaRedValStart(checkNull((String
						.valueOf(data[i][9]))));
				bean1.setSlaRedValEnd(checkNull((String
						.valueOf(data[i][10]))));
				bean1.setAutoId(checkNull(String.valueOf(data[i][11])));
				list.add(bean1);
				
				}
				bean.setSlaList(list);
			}
		
		
	}
}
