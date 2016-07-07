package org.paradyne.model.CR;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.CR.AccountMaster;
import org.paradyne.bean.payroll.ot.ShiftRoster;
import org.paradyne.lib.ModelBase; 
import org.paradyne.lib.StringEncrypter;

import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.Utility;

import com.lowagie.text.Font;


public class AccountMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	AccountMaster tm = null;
	

	/**
	 *  to check null value
	 *  
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} //end of if
		else {
			return result;
		}//end of else
	}

	/**
	 * Method to select the State.
	 * 
	 * @param addDet
	 */
	public void getStateCountry(AccountMaster bean) {
		String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION "
				+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE ="
				+ bean.getCityId() + ") ";
		Object[][] stateCode = getSqlModel().getSingleResult(query);

		if (stateCode.length > 0) {
			bean.setStateName(checkNull(String.valueOf(stateCode[0][1])));
			String coutryQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = "
					+ stateCode[0][2] + " ";
			Object[][] countryName = getSqlModel().getSingleResult(coutryQuery);
			if (countryName.length > 0) {
				bean.setCountryName(checkNull(String.valueOf(countryName[0][1])));
			}// end of nested if
			else
				bean.setCountryName("");
		}// end of if
	}

	/* for inserting the data */
	public boolean addAccount(AccountMaster bean) {
		boolean result = false;
		if (!checkDuplicate(bean)) {
			
			Object[][] saveObj = new Object[1][15];
			saveObj[0][0] = bean.getBusinessName();
			saveObj[0][1] = bean.getAccountId();
			
			if (bean.getIsParent().equals("true")) {
				saveObj[0][2] = "Y";
			} else {
				saveObj[0][2] = "N";
			}
			
			saveObj[0][3] = bean.getParentCode();
			saveObj[0][4] = bean.getContactName();
			saveObj[0][5] = bean.getEmailAddress();
			saveObj[0][6] = bean.getAddress().trim();
			saveObj[0][7] = bean.getCityName();
			saveObj[0][8] = bean.getCountryName();
			saveObj[0][9] = bean.getStateName();
			saveObj[0][10] = bean.getZipCode();
		
			if (bean.getIsActive().equals("true")) {
				saveObj[0][11] = "Y";
			} else {
				saveObj[0][11] = "N";
			}
			saveObj[0][12] = bean.getUserEmpId();
			saveObj[0][13] = bean.getUploadFileName();
			
			if (bean.getIsPartWaitTimeChecked().equals("true")) {
				saveObj[0][14] = "Y";
			} else {
				saveObj[0][14] = "N";
			}
			
				
			String insertRecordsQuery = "INSERT INTO CR_CLIENT_MASTER(ACCOUNT_CODE, ACCOUNT_NAME, ACCOUNT_ID, " +
					"PARENT_FLAG, PARENT_CODE, CONTACT_NAME, EMAIL_ID, ADDRESS, CITY, COUNTRY, " +
					"STATE, ZIP, IS_ACTIVE, RECORD_CREATEDBY, RECORD_CREATEDON,ACCOUNT_LOGO,PART_WAIT_TIME_FLAG)"
				+ " VALUES((SELECT isnull(MAX(ACCOUNT_CODE),0)+1 FROM CR_CLIENT_MASTER),?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)";

		result = getSqlModel("POOL_D1").singleExecute(insertRecordsQuery, saveObj);
			
		if (result) {
			String autoCodeQuery = " SELECT isnull(MAX(ACCOUNT_CODE),0) FROM CR_CLIENT_MASTER ";
			Object[][] idData = getSqlModel("POOL_D1").getSingleResult(autoCodeQuery);
			if (idData != null && idData.length > 0) {
				bean.setAccountCode(String.valueOf(idData[0][0]));
			}
		}
		}//end of if
		else {
			return false;

		}//end of else
		return result;
	}

	public void accountDetails(AccountMaster bean, HttpServletRequest request, String searchMessage, String searchCRMMessage) {

		try {
			
			Object[][] listParentData = null;
		
			ArrayList<AccountMaster> list = new ArrayList<AccountMaster>();
			/*String selParentQuery = " SELECT ACCOUNT_CODE, ACCOUNT_NAME,ACCOUNT_ID, DECODE(IS_ACTIVE,'Y','Yes','N','No')," +
					"RECORD_CREATEDBY, TO_CHAR(RECORD_CREATEDON,'DD-MM-YYYY HH:MM AM')," +
					"TO_CHAR(RECORD_MODIFIEDON,'DD-MM-YYYY HH:MM AM') ,'Y'||PARENT_FLAG  " +
					"FROM CR_CLIENT_MASTER WHERE PARENT_FLAG = 'Y' ";*/
			
			
			String selParentQuery="";
			if(!(searchCRMMessage.equals(""))){
				 selParentQuery="SELECT  A.ACCOUNT_CODE,A.ACCOUNT_NAME+' '+case when a.parent_flag='Y' then '(P)' else ' ' end,A.ACCOUNT_ID, "
					+" case when a.is_active='Y' then 'Yes' else 'No' end,convert(varchar(10),A.RECORD_CREATEDON,101) + ' '+ convert(varchar(5),A.RECORD_CREATEDON,108),convert(varchar(10),A.RECORD_MODIFIEDON,101) + ' '+ convert(varchar(5),A.RECORD_MODIFIEDON,108),'Y'+A.PARENT_FLAG,B.ACCOUNT_CODE "
					+" FROM CR_ClIENT_MASTER A,CR_CLIENT_MASTER B ,CR_ACC_CRM_MAPP C "
					+" WHERE isnull(A.PARENT_CODE,A.ACCOUNT_CODE)=B.ACCOUNT_CODE ";
					
				if (searchCRMMessage != null && !searchCRMMessage.trim().equals("")) {
					selParentQuery += " AND C.ACCOUNT_CODE=B.ACCOUNT_CODE AND C.CRM_CODE IN (('"+ searchCRMMessage.trim() + "'))   ";
				}
				selParentQuery+= " ORDER BY B.ACCOUNT_CODE*1000+case when A.PARENT_FLAG='Y' then 0 else a.account_code end";
			}else{
				selParentQuery="SELECT  A.ACCOUNT_CODE,A.ACCOUNT_NAME+' '+case when a.parent_flag='Y' then '(P)' else ' ' end,A.ACCOUNT_ID, "
					+" case when a.is_active='Y' then 'Yes' else 'No' end,convert(varchar(10),A.RECORD_CREATEDON,101) + ' '+ convert(varchar(5),A.RECORD_CREATEDON,108),convert(varchar(10),A.RECORD_MODIFIEDON,101) + ' '+ convert(varchar(5),A.RECORD_MODIFIEDON,108),'Y'+A.PARENT_FLAG,B.ACCOUNT_CODE "
					+" FROM CR_ClIENT_MASTER A,CR_CLIENT_MASTER B "
					+" WHERE isnull(A.PARENT_CODE,A.ACCOUNT_CODE)=B.ACCOUNT_CODE ";
			if (searchMessage != null && !searchMessage.trim().equals("")) {
				selParentQuery += " AND UPPER(A.ACCOUNT_NAME +A.ACCOUNT_ID) LIKE UPPER(rtrim (ltrim('%"+ searchMessage.trim() + "%')))   ";
			}
			selParentQuery+= " ORDER BY B.ACCOUNT_CODE*1000+case when A.PARENT_FLAG='Y' then 0 else a.account_code end";
			}
			
			
			
			listParentData = getSqlModel("POOL_D1").getSingleResult(selParentQuery);
			
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					listParentData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
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
			bean.setIteratorList(null);
			
			
			if (listParentData != null && listParentData.length > 0) {
				bean.setListLength(true);
				
				int count = 0;
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
				.parseInt(pageIndex[1]); i++) {
					AccountMaster beanItt = new AccountMaster();
					beanItt.setAccountCode(checkNull(String
							.valueOf(listParentData[i][0])));
					beanItt.setBusinessName(checkNull(String
							.valueOf(listParentData[i][1])));
					beanItt.setAccountId(checkNull(String
							.valueOf(listParentData[i][2])));
					beanItt.setIsActive(checkNull(String
							.valueOf(listParentData[i][3])));
					/*beanItt.setRecordCreatedBy(checkNull(String
							.valueOf(listParentData[i][4])));*/
					beanItt.setRecordCreatedOn(checkNull(String
							.valueOf(listParentData[i][4])));
					beanItt.setRecordModifiedOn(checkNull(String
							.valueOf(listParentData[i][5])));
					beanItt.setIsParent(checkNull(String
							.valueOf(listParentData[i][6])));
					
					
					/*Object[][] listData = null;
					String selQuery = " SELECT ACCOUNT_CODE, ACCOUNT_NAME, ACCOUNT_ID, DECODE(IS_ACTIVE,'Y','Yes','N','No'), " +
										" RECORD_CREATEDBY, TO_CHAR(RECORD_CREATEDON,'DD-MM-YYYY HH:MM AM'),TO_CHAR(RECORD_MODIFIEDON,'DD-MM-YYYY HH:MM AM') " +
										" ,'Y'||PARENT_FLAG FROM CR_CLIENT_MASTER WHERE PARENT_CODE="+listParentData[i][0];
					listData = getSqlModel().getSingleResult(selQuery);
					
					if(listData != null && listData.length > 0 ) {
						final ArrayList<AccountMaster> uploadDocList = new ArrayList<AccountMaster>();
						for (int j = 0; j < listData.length; j++) {
							final AccountMaster innerBean = new AccountMaster();
							innerBean.setAccountCode(checkNull(String
									.valueOf(listData[j][0])));
							innerBean.setBusinessName(checkNull(String
									.valueOf(listData[j][1])));
							innerBean.setAccountId(checkNull(String
									.valueOf(listData[j][2])));
							innerBean.setIsActive(checkNull(String
									.valueOf(listData[j][3])));
							innerBean.setRecordCreatedBy(checkNull(String
									.valueOf(listData[j][4])));
							innerBean.setRecordCreatedOn(checkNull(String
									.valueOf(listData[j][5])));
							innerBean.setRecordModifiedOn(checkNull(String
									.valueOf(listData[j][6])));
							innerBean.setIsParent(checkNull(String
									.valueOf(listData[j][7])));
							uploadDocList.add(innerBean);
						}
						beanItt.setActivityLogIterator(uploadDocList);
					}*/
					
					
					list.add(beanItt);
				}
				bean.setIteratorList(list);
			}
			
			
			
			
			
			// For drafted application Ends
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

	/* for selecting the record from list */
	public void calForEdit(AccountMaster bean, String requestID) {

		String query = " SELECT C1.ACCOUNT_CODE, C1.ACCOUNT_NAME, C1.ACCOUNT_ID,C1.PARENT_FLAG,  C1.PARENT_CODE, "
					+"	 C1.CONTACT_NAME,  C1.EMAIL_ID,  C1.ADDRESS,  C1.CITY,  " +
							" C1.COUNTRY,  C1.STATE,  C1.ZIP, "
					+"	 	 C1.IS_ACTIVE  , C2.ACCOUNT_NAME ,C1.ACCOUNT_LOGO , C1.PART_WAIT_TIME_FLAG "
					+"	 	FROM CR_CLIENT_MASTER  C1"
				///	+"	 	LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = C1.CITY) "
					+"	 	LEFT JOIN CR_CLIENT_MASTER C2 ON(C2.ACCOUNT_CODE=C1.PARENT_CODE)"
					+"	 	WHERE C1.ACCOUNT_CODE ="+requestID;
		
		Object[][] data = getSqlModel("POOL_D1").getSingleResult(query);
		if (data != null && data.length > 0) {
			bean.setAccountCode(String.valueOf(data[0][0]));
			bean.setBusinessName(String.valueOf(data[0][1]));
			bean.setAccountId(String.valueOf(data[0][2]));
			if (String.valueOf(data[0][3]).equals("Y")) {
				bean.setIsParent("true");
				bean.setHiddenCheckBoxFlag("S");
			} else {
				bean.setIsParent("false");
				bean.setHiddenCheckBoxFlag("L");
			}
			bean.setParentCode(String.valueOf(data[0][4]));
			bean.setContactName(checkNull(String.valueOf(data[0][5])));
			bean.setEmailAddress(checkNull(String.valueOf(data[0][6])));
			bean.setAddress(checkNull(String.valueOf(data[0][7])));
		///	bean.setCityId(checkNull(String.valueOf(data[0][8])));
			bean.setCityName(checkNull(String.valueOf(data[0][8])));
			bean.setCountryName(checkNull(String.valueOf(data[0][9])));
			bean.setStateName(checkNull(String.valueOf(data[0][10])));
			bean.setZipCode(checkNull(String.valueOf(data[0][11])));
			if (String.valueOf(data[0][12]).equals("Y")) {
				bean.setIsActive("true");
				bean.setHiddenActiveFlag("S");
			} else {
				bean.setIsActive("false");
				bean.setHiddenActiveFlag("L");
			}
			bean.setParentName(checkNull(String.valueOf(data[0][13])));
			bean.setUploadFileName(checkNull(String.valueOf(data[0][14])));
			
			if(String.valueOf(data[0][15]).equals("Y")){
				bean.setIsPartWaitTimeChecked("true");
				bean.setHiddenPartWaitTimeFlag("S");
			} else {
				bean.setIsPartWaitTimeChecked("false");
				bean.setHiddenPartWaitTimeFlag("L");
			}
			
		}
	}

	
	public boolean modAccount(AccountMaster bean,
			HttpServletRequest request) {

		boolean result = false;
		try {
		if (!checkDuplicateMod(bean)) {
			
			Object updateObj[][] = new Object[1][16];
			updateObj[0][0] = bean.getBusinessName();
			updateObj[0][1] = bean.getAccountId();

			if (bean.getIsParent().equals("true")) {
				updateObj[0][2] = "Y";
				
				
			} else {
				
				String parQuery = "select ACCOUNT_CODE	FROM CR_CLIENT_MASTER where PARENT_CODE ="+bean.getAccountCode();
				Object[][] parentData = getSqlModel("POOL_D1").getSingleResult(parQuery);
				if(parentData!=null && parentData.length>0 ){
					String updateQ="UPDATE CR_CLIENT_MASTER SET PARENT_CODE ="+bean.getParentCode()+"  WHERE ACCOUNT_CODE=?";
					getSqlModel().singleExecute(updateQ, parentData);
				}
				
				
				
				updateObj[0][2] = "N";
			}
			
			updateObj[0][3] = checkNull(bean.getParentCode());
			updateObj[0][4] = bean.getContactName();
			updateObj[0][5] = bean.getEmailAddress();
			updateObj[0][6] = bean.getAddress();
			updateObj[0][7] = bean.getCityName();
			updateObj[0][8] = bean.getCountryName();
			updateObj[0][9] = bean.getStateName();
			updateObj[0][10] = bean.getZipCode();
			
			if (bean.getIsActive().equals("true")) {
				updateObj[0][11] = "Y";
			} else {
				updateObj[0][11] = "N";
			}
			
			updateObj[0][12] = bean.getUserEmpId();
			updateObj[0][13] = bean.getUploadFileName();
			
			if (bean.getIsPartWaitTimeChecked().equals("true")) {
				updateObj[0][14] = "Y";
			} else {
				updateObj[0][14] = "N";
			}
			
			
			updateObj[0][15] = bean.getAccountCode();
			

			String updateRecordsQuery = "UPDATE CR_CLIENT_MASTER SET ACCOUNT_NAME =? , " +
					"ACCOUNT_ID =? , PARENT_FLAG =? , PARENT_CODE =? , CONTACT_NAME =? , EMAIL_ID =? , " +
					"ADDRESS =? , CITY =? , COUNTRY =? , STATE =? , ZIP =? , IS_ACTIVE =? , RECORD_MODIFIEDBY =? , " +
					"RECORD_MODIFIEDON =SYSDATE ,ACCOUNT_LOGO = ? , PART_WAIT_TIME_FLAG = ?  WHERE ACCOUNT_CODE=?";

			result = getSqlModel("POOL_D1").singleExecute(updateRecordsQuery, updateObj);
			}// end of if
			else {
				return false;
			}// end of else
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void gerSelectRecord(AccountMaster bean, HttpServletRequest request) {
		
		String query = " SELECT C1.ACCOUNT_CODE, C1.ACCOUNT_NAME, C1.ACCOUNT_ID,C1.PARENT_FLAG,  C1.PARENT_CODE, "
					+"	 C1.CONTACT_NAME,  C1.EMAIL_ID,  C1.ADDRESS,  C1.CITY, null, " +
							" C1.COUNTRY,  C1.STATE,  C1.ZIP, "
					+"	 	 C1.IS_ACTIVE  , C2.ACCOUNT_NAME ,C1.ACCOUNT_LOGO "
					+"	 	FROM CR_CLIENT_MASTER  C1"
					+"	 	LEFT JOIN CR_CLIENT_MASTER C2 ON(C2.ACCOUNT_CODE=C1.PARENT_CODE)"
					+"	 	WHERE C1.ACCOUNT_CODE ="+bean.getAccountCode();
		
		Object[][] data = getSqlModel("POOL_D1").getSingleResult(query);
		bean.setAccountCode(String.valueOf(data[0][0]));
		bean.setBusinessName(String.valueOf(data[0][1]));
		bean.setAccountId(String.valueOf(data[0][2]));
		if (String.valueOf(data[0][3]).equals("Y")) {
			bean.setIsParent("true");
		} else {
			bean.setIsParent("false");
		}
		bean.setParentCode(checkNull(String.valueOf(data[0][4])));
		bean.setContactName(checkNull(String.valueOf(data[0][5])));
		bean.setEmailAddress(checkNull(String.valueOf(data[0][6])));
		bean.setAddress(checkNull(String.valueOf(data[0][7])));
		bean.setCityId(checkNull(String.valueOf(data[0][8])));
		bean.setCityName(checkNull(String.valueOf(data[0][9])));
		bean.setCountryName(checkNull(String.valueOf(data[0][10])));
		bean.setStateName(checkNull(String.valueOf(data[0][11])));
		bean.setZipCode(checkNull(String.valueOf(data[0][12])));
		if (String.valueOf(data[0][13]).equals("Y")) {
			bean.setIsActive("true");
		} else {
			bean.setIsActive("false");
		}
		bean.setParentName(checkNull(String.valueOf(data[0][14])));
		bean.setUploadFileName(String.valueOf(data[0][15]));
	}
	
	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(AccountMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM CR_CLIENT_MASTER WHERE UPPER(ACCOUNT_ID) LIKE '"
				+ bean.getAccountId().trim() + "'";
		Object[][] data = getSqlModel("POOL_D1").getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}
	
	/* for checking duplicate entry of record during insertion */
	public boolean checkEmailDuplicate(AccountMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM CR_CLIENT_USERS WHERE UPPER(EMAIL_ID) LIKE '"
				+ bean.getEmailClientAddress().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel("POOL_D1").getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}
		
	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicateMod(AccountMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM CR_CLIENT_MASTER WHERE UPPER(ACCOUNT_ID) LIKE '"
				+ bean.getAccountId().trim() 
					+ "' AND ACCOUNT_CODE not in(" + bean.getAccountCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}
	
	public void callForCRMMapping(AccountMaster bean, String requestID) {
		String initiatorQuery = "SELECT ACCOUNT_CODE, ACCOUNT_NAME , ACCOUNT_ID  FROM CR_CLIENT_MASTER WHERE ACCOUNT_CODE ="
			+ requestID;

		Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
		if (initiatorObj != null && initiatorObj.length > 0) {
			bean.setAccountCode(String.valueOf(initiatorObj[0][0]));
			bean.setBusinessName(String.valueOf(initiatorObj[0][1]));
			bean.setAccountId(String.valueOf(initiatorObj[0][2]));
		}
		
		//to set report name start
		String reportQuery = "SELECT MAPP_CODE, ACCOUNT_CODE, CR_ACC_REPORT_MAPP.REPORT_CODE ,REPORT_NAME "
							+" FROM CR_ACC_REPORT_MAPP "
							+" INNER JOIN CR_REPORT_MASTER ON (CR_REPORT_MASTER.REPORT_CODE = CR_ACC_REPORT_MAPP.REPORT_CODE)"
							+" where ACCOUNT_CODE="+requestID;
		Object[][] reportObj = getSqlModel().getSingleResult(reportQuery);
		String rptCode = "";
		String rptName = "";
		if(reportObj != null && reportObj.length > 0){
			for(int i=0; i<reportObj.length; i++){
				rptCode = rptCode + reportObj[i][2] + ",";
				rptName = rptName + reportObj[i][3] + ",";
			}
			
			rptCode = rptCode.substring(0, (rptCode.length() - 1));
			rptName = rptName.substring(0, (rptName.length() - 1));
			
		}
		
		if (reportObj != null && reportObj.length > 0) {
			bean.setReportCode(String.valueOf(rptCode));
			bean.setReportName(String.valueOf(rptName));
		}
		
		//to set report name end
		
		//to set CRM list start
		String query = "SELECT CRM_CODE FROM CR_ACC_CRM_MAPP "
					+" where ACCOUNT_CODE=" + requestID;
		Object keepInformData[][] = getSqlModel().getSingleResult(query);
				
		String str = "";
		String queryA = "";
		if (keepInformData != null && keepInformData.length > 0) {
			str = String.valueOf(keepInformData[0][0]);

			if (str.length() > 0) {
				queryA = "  SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID ,EMP_TOKEN "
						+ " FROM HRMS_EMP_OFFC "
						+ "  WHERE  EMP_ID IN(SELECT CRM_CODE FROM CR_ACC_CRM_MAPP  where ACCOUNT_CODE="+requestID+" )";
						
			}
			Object result[][] = getSqlModel().getSingleResult(queryA);

			ArrayList<AccountMaster> crmList = new ArrayList<AccountMaster>();
			if (result != null) {

				for (int i = 0; i < result.length; i++) {
					AccountMaster bean1 = new AccountMaster();
					bean1.setKeepInformToCode(String.valueOf(result[i][1]));
					bean1.setKeepInformToName(String
							.valueOf(result[i][0]));
					bean1.setKeepInformToToken(String
							.valueOf(result[i][2]));
					
					crmList.add(bean1);
				}
				bean.setKeepInformedList(crmList);
			}
		}
		//to set CRM list end
		
	}
	public void callForBusinessUser(AccountMaster bean, String requestID, HttpServletRequest request) {
		
		System.out.println("requestID=111333=="+bean.getAccountCode());
		String initiatorQuery = "SELECT ACCOUNT_CODE, ACCOUNT_NAME,ACCOUNT_ID FROM CR_CLIENT_MASTER WHERE ACCOUNT_CODE ="
			+ requestID;

		Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
		if (initiatorObj != null && initiatorObj.length > 0) {
			bean.setAccountCode(String.valueOf(initiatorObj[0][0]));
			bean.setBusinessName(String.valueOf(initiatorObj[0][1]));
			bean.setAccountId(String.valueOf(initiatorObj[0][2]));
		}
		
		String clientUserQuery = "SELECT CRUSER_CODE, CR_CLIENT_USERS.ACCOUNT_CODE,ACCOUNT_NAME, " +
				"FIRST_NAME, LAST_NAME, CR_CLIENT_USERS.EMAIL_ID,  PHONE_NO, DECODE(CR_CLIENT_USERS.IS_ACTIVE,'Y','Yes','N','No'),  "
				+"convert(varchar(10),CR_CLIENT_USERS.RECORD_CREATEDON,101) + ' '+ convert(varchar(5),CR_CLIENT_USERS.RECORD_CREATEDON,108),ACCOUNT_CHILD_CODE ,convert(varchar(10),CR_CLIENT_USERS.RECORD_MODIFIEDON,101) + ' '+ convert(varchar(5),CR_CLIENT_USERS.RECORD_MODIFIEDON,108) "
				+" FROM CR_CLIENT_USERS "
				+" INNER JOIN CR_CLIENT_MASTER ON (CR_CLIENT_MASTER.ACCOUNT_CODE = CR_CLIENT_USERS.ACCOUNT_CODE)"
				+" WHERE CR_CLIENT_USERS.ACCOUNT_CODE = "+requestID;
		Object[][] clientUserObj = getSqlModel().getSingleResult(clientUserQuery);
		if (clientUserObj != null && clientUserObj.length > 0) {
			System.out.println("clientUserObj.length==INSIDE IFFFF==" + clientUserObj.length);
			bean.setModeLength("true");
			bean.setTotalRecords(String.valueOf(clientUserObj.length));
			String[] pageIndex = Utility.doPaging(bean.getMyPageInProcess(),
					clientUserObj.length, 5);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "5";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPageInProcess("1");
			int k = 0;
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
			.parseInt(pageIndex[1]); i++) {
				AccountMaster bean1 = new AccountMaster();
				bean1.setIttClientUserId(checkNull((String 
						.valueOf(clientUserObj[i][0]))));
				bean1
						.setIttAccountCode(checkNull((String
								.valueOf(clientUserObj[i][1]))));
				bean1.setIttAccountName(checkNull((String
						.valueOf(clientUserObj[i][2]))));
				bean1.setIttFirstName(checkNull((String
						.valueOf(clientUserObj[i][3]))));
				bean1.setIttLastName(checkNull((String
						.valueOf(clientUserObj[i][4]))));
				bean1.setIttEmailId(checkNull((String
						.valueOf(clientUserObj[i][5]))));
				bean1.setIttCellNumber(checkNull((String
						.valueOf(clientUserObj[i][6]))));
				bean1.setIttIsActive(checkNull((String
						.valueOf(clientUserObj[i][7]))));
				bean1.setIttCreatedOn(checkNull((String.valueOf(clientUserObj[i][8]))));
				bean1.setIttSubAccountId(checkNull((String.valueOf(clientUserObj[i][9]))));
				bean1.setIttSubAccountName(getAccountName(String.valueOf(clientUserObj[i][9])));
				bean1.setIttModifiedOn(checkNull((String.valueOf(clientUserObj[i][10]))));
				
				List.add(bean1);
				//k++;
			}

			bean.setClientUserList(List);
		}
		
	}
	
	public boolean saveCrmMappingDtl(AccountMaster bean, HttpServletRequest request, String[] keepInformToCode) {

		boolean result = false;
		try {
			System.out.println("IN SAVE ");
			
			Object maxCode[][] = getSqlModel()
			.getSingleResult(
					"SELECT isnull(MAX(MAPP_CODE),0) +1 FROM CR_ACC_CRM_MAPP");
			String code="";
			if(maxCode!=null && maxCode.length>0){
				code=String.valueOf(maxCode[0][0]);
			}
			Object[][] crmMappingDtl = new Object[keepInformToCode.length][4];
			if(keepInformToCode != null && keepInformToCode.length > 0){
				for(int i=0; i<keepInformToCode.length; i++){
					System.out.println(""+keepInformToCode[i]);
					crmMappingDtl[i][0] = String.valueOf(code); // Mapp Code
					crmMappingDtl[i][1] = bean.getAccountCode(); // Account Code
					crmMappingDtl[i][2] = keepInformToCode[i]; // keepInformToCode
					crmMappingDtl[i][3] = bean.getUserEmpId(); // User EMP ID
					code=String.valueOf(Integer.parseInt(code)+1);
				}
			}
			

			String Query1 = "DELETE FROM CR_ACC_CRM_MAPP WHERE ACCOUNT_CODE ="+bean.getAccountCode();
			result = getSqlModel().singleExecute(Query1);
			if (result) {
				String Query = "INSERT INTO CR_ACC_CRM_MAPP ( MAPP_CODE, ACCOUNT_CODE, CRM_CODE,RECORD_CREATEDBY, RECORD_CREATEDON) VALUES ( ?, ?,?,?, SYSDATE) ";
				result = getSqlModel().singleExecute(Query, crmMappingDtl);
			}
			
			
			Object maxReportCode[][] = getSqlModel()
			.getSingleResult(
					"SELECT NVL(MAX(MAPP_CODE),0) +1 FROM CR_ACC_REPORT_MAPP");
			
			String reptCode="";
			if(maxReportCode!=null && maxReportCode.length>0){
				reptCode=String.valueOf(maxReportCode[0][0]);
			}
			
			String reportCode[]=bean.getReportCode().split(",");
			Object[][] rptMappingDtl = new Object[reportCode.length][4];
			
			if(reportCode != null && reportCode.length > 0){
				for(int i=0;i<reportCode.length;i++){
					rptMappingDtl[i][0] = String.valueOf(reptCode); // Report Code
					rptMappingDtl[i][1] = bean.getAccountCode(); // Account Code
					rptMappingDtl[i][2] = reportCode[i]; // Report Code
					rptMappingDtl[i][3] = bean.getUserEmpId(); // USER EMP ID
					reptCode=String.valueOf(Integer.parseInt(reptCode)+1);
				}
			
			}
			
			String rptMappingDtlQuery = "DELETE FROM CR_ACC_REPORT_MAPP WHERE ACCOUNT_CODE ="+bean.getAccountCode();
			result = getSqlModel().singleExecute(rptMappingDtlQuery);
			if (result) {
				String rptMappingQuery = "INSERT INTO CR_ACC_REPORT_MAPP ( MAPP_CODE, ACCOUNT_CODE, REPORT_CODE,RECORD_CREATEDBY, RECORD_CREATEDON) VALUES ( ?, ?, ?,?,getdate()) ";
				result = getSqlModel().singleExecute(rptMappingQuery, rptMappingDtl);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	public boolean saveClientUserLstDtl(AccountMaster bean,
			HttpServletRequest request) {
		boolean result = false;
		
		try {
			
			///if (!checkEmailDuplicate(bean)) {
			Object addObj[][] = new Object[1][9];
			//addObj[0][0] = bean.getHiddenBankId();
			addObj[0][0] = bean.getAccountCode();
			addObj[0][1] = bean.getFirstName();
			addObj[0][2] = bean.getLastName();
			addObj[0][3] = bean.getEmailClientAddress();
			addObj[0][4] = bean.getCellNumber();
			
			if (bean.getIsClientActive().equals("true")) {
				addObj[0][5] = "Y";
			} else {
				addObj[0][5] = "N";
			}
			addObj[0][6] = bean.getUserEmpId();
			addObj[0][7] = bean.getParentClientCode();
			
			 String encryptPwd;
			try {
				encryptPwd = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean
						.getPassword().trim());
			
				addObj[0][8] = encryptPwd;
				
			} catch (Exception e) {
				e.printStackTrace(); 
			}
			String insertQuery = "INSERT INTO CR_CLIENT_USERS"
				+ "(CRUSER_CODE, ACCOUNT_CODE, FIRST_NAME, LAST_NAME, EMAIL_ID, PHONE_NO," +
						" IS_ACTIVE, RECORD_CREATEDBY, RECORD_CREATEDON,ACCOUNT_CHILD_CODE,PASSWORD,RECORD_MODIFIEDON)"
				+ " VALUES((SELECT  isnull(MAX(CRUSER_CODE),0)+1 FROM CR_CLIENT_USERS),?,?,?,?,?,?,?,getdate(),?,?,getdate())";
			
			result = getSqlModel().singleExecute(insertQuery, addObj);
			if (result) {
				String autoCodeQuery = " SELECT isnull(MAX(CRUSER_CODE),0) FROM CR_CLIENT_USERS ";
				Object[][] data = getSqlModel().getSingleResult(autoCodeQuery);
				if (data != null && data.length > 0) {
					bean.setHiddencode(String.valueOf(data[0][0]));
				}
			}
			//}//end of if
			///else {
			///	return false;

			///}//end of else
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	public boolean delClientUserLstDtl(AccountMaster bean,
			HttpServletRequest request, String clientUserID) {
		String deleteQuery="";
		try {
			 deleteQuery = "DELETE FROM CR_CLIENT_USERS  WHERE CRUSER_CODE="+clientUserID;

			
		} catch(Exception e) {
			// TODO: handle exception
		}
		return getSqlModel().singleExecute(deleteQuery);
	}
	
	public void applyFilters(AccountMaster bean, HttpServletRequest request,
			String searchMessage, String searchCRMMessage) {
		try {
			Object[][] listParentData = null;
			ArrayList<AccountMaster> list = new ArrayList<AccountMaster>();
			System.out.println("searchCRMMessage===="+searchCRMMessage);
			String selParentQuery="";
			if(!(searchCRMMessage.equals(""))){
				System.out.println("searchCRMMessage==if=="+searchCRMMessage);
				 selParentQuery="SELECT  A.ACCOUNT_CODE,A.ACCOUNT_NAME||' '||DECODE(A.PARENT_FLAG,'Y','(P)','N',' '),A.ACCOUNT_ID, "
					+" DECODE(A.IS_ACTIVE,'Y','Yes','N','No'),TO_CHAR(A.RECORD_CREATEDON,'DD-MM-YYYY HH:MM AM'),TO_CHAR(A.RECORD_MODIFIEDON,'DD-MM-YYYY HH:MM AM') ,'Y'||A.PARENT_FLAG,B.ACCOUNT_CODE "
					+" FROM CR_ClIENT_MASTER A,CR_CLIENT_MASTER B ,CR_ACC_CRM_MAPP C "
					+" WHERE NVL(A.PARENT_CODE,A.ACCOUNT_CODE)=B.ACCOUNT_CODE ";
					
				if (searchCRMMessage != null && !searchCRMMessage.trim().equals("")) {
					selParentQuery += " AND C.ACCOUNT_CODE=B.ACCOUNT_CODE AND C.CRM_CODE IN (('"+ searchCRMMessage.trim() + "'))   ";
				}
				selParentQuery+= " ORDER BY B.ACCOUNT_CODE*1000+(DECODE(A.PARENT_FLAG,'Y',0,A.ACCOUNT_CODE))  ";
			}else{
				System.out.println("searchCRMMessage==else=="+searchCRMMessage);
				selParentQuery="SELECT  A.ACCOUNT_CODE,A.ACCOUNT_NAME||' '||DECODE(A.PARENT_FLAG,'Y','(P)','N',' '),A.ACCOUNT_ID, "
					+" DECODE(A.IS_ACTIVE,'Y','Yes','N','No'),TO_CHAR(A.RECORD_CREATEDON,'DD-MM-YYYY HH:MM AM'),TO_CHAR(A.RECORD_MODIFIEDON,'DD-MM-YYYY HH:MM AM') ,'Y'||A.PARENT_FLAG,B.ACCOUNT_CODE "
					+" FROM CR_ClIENT_MASTER A,CR_CLIENT_MASTER B "
					+" WHERE NVL(A.PARENT_CODE,A.ACCOUNT_CODE)=B.ACCOUNT_CODE ";
					
				if (searchMessage != null && !searchMessage.trim().equals("")) {
					selParentQuery += " AND UPPER(A.ACCOUNT_NAME ||A.ACCOUNT_ID) LIKE UPPER (trim('%"+ searchMessage.trim() + "%'))   ";
				}
				selParentQuery+= " ORDER BY B.ACCOUNT_CODE*1000+(DECODE(A.PARENT_FLAG,'Y',0,A.ACCOUNT_CODE))  ";
			}
			 
			
			listParentData = getSqlModel().getSingleResult(selParentQuery);
			
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					listParentData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
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
			bean.setIteratorList(null);
			
			
			if (listParentData != null && listParentData.length > 0) {
				bean.setListLength(true);
				
				int count = 0;
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
				.parseInt(pageIndex[1]); i++) {
					AccountMaster beanItt = new AccountMaster();
					beanItt.setAccountCode(checkNull(String
							.valueOf(listParentData[i][0])));
					beanItt.setBusinessName(checkNull(String
							.valueOf(listParentData[i][1])));
					beanItt.setAccountId(checkNull(String
							.valueOf(listParentData[i][2])));
					beanItt.setIsActive(checkNull(String
							.valueOf(listParentData[i][3])));
					/*beanItt.setRecordCreatedBy(checkNull(String
							.valueOf(listParentData[i][4])));*/
					beanItt.setRecordCreatedOn(checkNull(String
							.valueOf(listParentData[i][4])));
					beanItt.setRecordModifiedOn(checkNull(String
							.valueOf(listParentData[i][5])));
					beanItt.setIsParent(checkNull(String
							.valueOf(listParentData[i][6])));
					
					list.add(beanItt);
				}
				bean.setIteratorList(list);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**Method : getActualPayBillName.
	 * Purpose : this method is used to get PayBill names based on supplied pay bill ID
	 * @param payBillID : payBillID
	 * @return String
	 */
	private String getAccountName(final String accountID) {
		String accountName = "";
		try {
			if (accountID == null || accountID.equals("null")) {
				accountName = "";
			} else {
				String payBillNameQuery = "SELECT CR_CLIENT_MASTER.ACCOUNT_NAME FROM CR_CLIENT_MASTER WHERE CR_CLIENT_MASTER.ACCOUNT_CODE IN ("+ accountID + ")";
				Object[][] payBillNameObj = getSqlModel().getSingleResult(payBillNameQuery);
				if (payBillNameObj != null && payBillNameObj.length > 0) {
					for (int i = 0; i < payBillNameObj.length; i++) {
						accountName += String.valueOf(payBillNameObj[i][0])+", ";
						
					}
				}
				accountName = accountName.substring(0, accountName.length()-2);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return accountName;
	}
	public void editClientUserLstDtl(AccountMaster bean,
			HttpServletRequest request, String clientUserID) {
			try {
				String query = "SELECT CRUSER_CODE,ACCOUNT_CODE, FIRST_NAME, LAST_NAME, EMAIL_ID, " +
						"PHONE_NO, IS_ACTIVE, ACCOUNT_CHILD_CODE, PASSWORD  FROM CR_CLIENT_USERS "
						+" WHERE  CRUSER_CODE= "+ clientUserID;
				Object[][] data = getSqlModel().getSingleResult(query);
				bean.setParacode(checkNull((String.valueOf(data[0][0]))));
				bean.setAccountCode(checkNull((String.valueOf(data[0][1]))));
				bean.setFirstName(checkNull((String.valueOf(data[0][2]))));
				bean.setLastName(checkNull((String.valueOf(data[0][3]))));
				bean.setEmailClientAddress(checkNull((String.valueOf(data[0][4]))));
				bean.setCellNumber(checkNull((String.valueOf(data[0][5]))));
				
				
				if (String.valueOf(data[0][6]).equals("Y")) {
					bean.setIsClientActive("true");
					bean.setHiddenClientActive("S");
				} else {
					bean.setIsClientActive("false");
					bean.setHiddenClientActive("L");
				}
				
				
				bean.setParentClientCode(checkNull(String.valueOf(data[0][7])));
				bean.setParentClientName(getAccountName(String.valueOf(String.valueOf(data[0][7]))));
				bean.setPassword(String.valueOf(String.valueOf(data[0][8])));
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		
	}
	public boolean modClientUserLstDtl(AccountMaster bean,
			HttpServletRequest request) {
		boolean result = false;
			
		try {
			
		
			Object updateObj[][] = new Object[1][9];
			
			updateObj[0][0] = bean.getAccountCode();
			updateObj[0][1] = bean.getFirstName();
			updateObj[0][2] = bean.getLastName();
			updateObj[0][3] = bean.getEmailClientAddress();
			updateObj[0][4] = bean.getCellNumber();
			updateObj[0][5] = bean.getParentClientCode();
			
			
			if (bean.getIsClientActive().equals("true")) {
				updateObj[0][6] = "Y";
			} else {
				updateObj[0][6] = "N";
			}
			
			updateObj[0][7] = bean.getUserEmpId();
			
			String encryptPwd;
			try {
				encryptPwd = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean
						.getPassword().trim());
			
			updateObj[0][8] = encryptPwd;
			} catch (Exception e) {
				e.printStackTrace();
			}
			String insertQuery = "UPDATE CR_CLIENT_USERS SET "
					+ " ACCOUNT_CODE = ? , FIRST_NAME = ? , LAST_NAME = ?  , EMAIL_ID  = ? , PHONE_NO = ? ,ACCOUNT_CHILD_CODE = ? , IS_ACTIVE = ?  ,RECORD_MODIFIEDBY = ?  ,RECORD_MODIFIEDON = SYSDATE "
					+ " ,PASSWORD = ? WHERE CRUSER_CODE = "
					+ bean.getParacode() ;
			
			result = getSqlModel().singleExecute(insertQuery, updateObj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	/**This is used to get Report.
	 * @param bean : AccountMaster
	 * @param response
	 * @param request
	 * @param reportPath
	 */
	public void getReport(AccountMaster bean, HttpServletResponse response,
			HttpServletRequest request, String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getRptType();
			rds.setReportType(type);
			String fileName = "Account Master List  " + Utility.getRandomNumber(1000);
			String reportPathName = reportPath+fileName + "." + type;
			rds.setFileName(fileName);
			rds.setReportName("Account Master List Report ");
			rds.setPageSize("A4");
			rds.setPageOrientation("portrait");
			rds.setUserEmpId(bean.getUserEmpId());
			
			/*rds.setMarginBottom(25);
			rds.setMarginLeft(25);
			rds.setMarginRight(25);*/
			rds.setShowPageNo(true);
			rds.setTotalColumns(10);
			//rds.setMarginTop(25);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath , session, context, request);
				request.setAttribute("reportPath", reportPath+fileName + "." + type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "AccountMaster_input.action");
			}
			rg.SPLIT_LATE=true;
			rg = getReport(rg, bean);
			rg.process();
			if (reportPath.equals("")) {
				rg.createReport(response);
			} else {
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**This is used to Report.
	 * @param rg
	 * @param bean : ShiftRoster
	 * @return
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, AccountMaster bean) {
		try {
			String ledgerQuery = " SELECT  A.ACCOUNT_CODE,A.ACCOUNT_ID,A.ACCOUNT_NAME , "
						+" DECODE(A.PARENT_FLAG,'Y',' ',B.ACCOUNT_NAME ),DECODE(A.IS_ACTIVE,'Y','Yes','N','No'),TO_CHAR(A.RECORD_CREATEDON,'DD-MM-YYYY HH:MM AM'),B.ACCOUNT_CODE "
						+" FROM CR_ClIENT_MASTER A,CR_CLIENT_MASTER B "
						+" WHERE NVL(A.PARENT_CODE,A.ACCOUNT_CODE)=B.ACCOUNT_CODE "
						+" ORDER BY B.ACCOUNT_CODE,A.ACCOUNT_CODE ";
		
		Object[][] expDetail = getSqlModel().getSingleResult(
				ledgerQuery);
		
		String[] header = null;
		int creditCount = 0;
		int debitCount = 0;

		header = new String[6];

			header[0] = "Sr No.";
			header[1] = "Account ID ";
			header[2] = "Account Name ";
			header[3] = "Parent Name ";
			header[4] = "Is Active ";
			header[5] = "Created On ";
			
		int[] bcellAlign = new int[header.length];
		int[] bcellWidth = new int[header.length];
		boolean[] bcellwrap = new boolean[header.length];
		for (int i = 0; i < header.length; i++) {
			///bcellAlign[i] = 1;
			/// bcellWidth[i] = 40;
			if (i == 0) {
				bcellAlign[i] = 2;
				bcellWidth[i] = 10;
				///bcellwrap[i]=true;
			} else if (i == 1) {
				bcellAlign[i] = 0;
				bcellWidth[i] = 25;
				bcellwrap[i]=true;
			} else if (i == 2) {
				bcellAlign[i] = 0;
				bcellWidth[i] = 25;
				///bcellwrap[i]=true;
			} else if (i == 3) {
				bcellAlign[i] = 0;
				bcellWidth[i] = 28;
				///bcellwrap[i]=true;
			}else if (i == 4) {
				bcellAlign[i] = 0;
				bcellWidth[i] = 12;
				///bcellwrap[i]=true;
			}
			else  {
				bcellAlign[i] = 0;
				bcellWidth[i] = 25;
				///bcellwrap[i]=true;
			}
		}
		
		
		if (expDetail != null && expDetail.length > 0) {
			
			int j = 1;
			for (int i = 0; i < expDetail.length; i++) {
				expDetail[i][0] = j;
				expDetail[i][1] = expDetail[i][1];
				expDetail[i][2] = expDetail[i][2];
				expDetail[i][3] = expDetail[i][3];
				expDetail[i][4] = expDetail[i][4];
				expDetail[i][5] = expDetail[i][5];
				
				j++;
			}
			
			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(header);
			tdstable.setData(expDetail);
			tdstable.setHeaderBorderDetail(3);
			tdstable.setCellAlignment(bcellAlign);
			tdstable.setCellWidth(bcellWidth);
			///tdstable.setCellNoWrap(bcellwrap);
			tdstable.setBorderDetail(3);
			tdstable.setHeaderTable(true);
			rg.addTableToDoc(tdstable);
			
		}else {
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "Account Master List : No records available";

			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 0 });
			noData.setCellWidth(new int[] { 100 });
			noData.setBorder(false);
			rg.addTableToDoc(noData);
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
		}

	
	/**This is used to get Report.
	 * @param bean : AccountMaster
	 * @param response
	 * @param request
	 * @param reportPath
	 */
	public void getClientReport(AccountMaster bean, HttpServletResponse response,
			HttpServletRequest request, String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getRptType();
			rds.setReportType(type);
			String fileName = "Client Mapping Report  " + Utility.getRandomNumber(1000);
			String reportPathName = reportPath+fileName + "." + type;
			rds.setFileName(fileName);
			rds.setReportName("Client Mapping Report ");
			rds.setPageSize("A4");
			rds.setPageOrientation("portrait");
			rds.setUserEmpId(bean.getUserEmpId());
			
			/*rds.setMarginBottom(25);
			rds.setMarginLeft(25);
			rds.setMarginRight(25);*/
			rds.setShowPageNo(true);
			rds.setTotalColumns(10);
			//rds.setMarginTop(25);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath , session, context, request);
				request.setAttribute("reportPath", reportPath+fileName + "." + type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "AccountMaster_input.action");
			}
			rg.SPLIT_LATE=true;
			rg = getClientUserReport(rg, bean);
			rg.process();
			if (reportPath.equals("")) {
				rg.createReport(response);
			} else {
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**This is used to Report.
	 * @param rg
	 * @param bean : ShiftRoster
	 * @return
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getClientUserReport(org.paradyne.lib.ireportV2.ReportGenerator rg, AccountMaster bean) {
		try {
			
			java.util.Date d = new java.util.Date();
			logger.info("Date : - " + d);
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"dd-MM-yyyy");
			String strDate = sdf.format(d);

			Object[][] dateData = new Object[1][1];
			dateData[0][0] = "Date: - " + strDate;
			int[] cellWidthDateHeader = { 100 };
			int[] cellAlignDateHeader = { 2 };
			TableDataSet tableheadingDateData = new TableDataSet();
			tableheadingDateData.setData(dateData);
			tableheadingDateData.setCellWidth(cellWidthDateHeader);
			tableheadingDateData.setCellAlignment(cellAlignDateHeader);
			tableheadingDateData.setBorder(false);
			// tableheadingDateData.setHeaderTable(true);
			tableheadingDateData.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingDateData);

			String parentNameQuery = " SELECT ACCOUNT_CODE,ACCOUNT_ID,ACCOUNT_NAME FROM CR_CLIENT_MASTER WHERE PARENT_FLAG ='Y' ORDER BY ACCOUNT_CODE ASC ";
			Object[][] processDetail = getSqlModel().getSingleResult(
					parentNameQuery);
			if (processDetail != null && processDetail.length > 0) {
				
				for (int i = 0; i < processDetail.length; i++) {
					Object[][] parentNameDetails = new Object[1][4];// new-------------->
					parentNameDetails[0][0] = "Account ID :";
					parentNameDetails[0][1] = String.valueOf(processDetail[i][1]);
					parentNameDetails[0][2] = "Account Name :";
					parentNameDetails[0][3] = String.valueOf(processDetail[i][2]);

					TableDataSet parentInfoTable = new TableDataSet();
					parentInfoTable.setData(parentNameDetails);
					parentInfoTable.setCellWidth(new int[] { 25, 25, 25, 25 });
					parentInfoTable.setCellAlignment(new int[] { 0, 0, 0, 0 });
					parentInfoTable.setBodyFontStyle(1);  
					parentInfoTable.setBorderDetail(2);
					rg.addTableToDoc(parentInfoTable);

					Object[][] childData = null;
					try {

						String childDataQuery = "SELECT ROWNUM AS SNo , FIRST_NAME||' '|| LAST_NAME ," +
								"  EMAIL_ID, ACCOUNT_CHILD_CODE , TO_CHAR(CR_CLIENT_USERS.RECORD_CREATEDON,'DD-MM-YYYY HH:MM AM') 	" +
								" FROM CR_CLIENT_USERS 	"
								+" WHERE CR_CLIENT_USERS.ACCOUNT_CODE = "
								+ processDetail[i][0];

						childData = getSqlModel().getSingleResult(
								childDataQuery);

						String[] columns = new String[] { "S.No.",
								"CRM Name","User ID","Sub Account","Added On" };

						int[] cellWidthChallan = { 7, 22,26, 26,18 };
						int[] cellAlignChallan = { 0, 0, 0, 0, 0 };
						Object[][]obj=new Object[childData.length][5];
						
						
						if (childData != null && childData.length > 0) {

							for (int j = 0; j < childData.length; j++) {
								obj[j][0]=childData[j][0];
								obj[j][1]=childData[j][1];
								obj[j][2]=childData[j][2];
								
								String accName = getAccountName(String.valueOf(childData[j][3]));
								obj[j][3]=accName;
								obj[j][4]=childData[j][4];
								
							}
							
							
							TableDataSet branchDetails = new TableDataSet();

							branchDetails.setHeader(columns);
							branchDetails.setHeaderBorderDetail(3);
							branchDetails.setData(obj);
							branchDetails.setCellWidth(cellWidthChallan);
							branchDetails.setCellAlignment(cellAlignChallan);
							branchDetails.setBorder(true);
							branchDetails.setBorderDetail(3);
							branchDetails.setBlankRowsBelow(1);
							rg.addTableToDoc(branchDetails);
						} else {
							TableDataSet noData = new TableDataSet();
							Object[][] noDataObj = new Object[1][1];
							noDataObj[0][0] = "No records available";
							// noData.setHeader(columns);
							noData.setData(noDataObj);
							noData.setCellAlignment(new int[] { 1 });
							noData.setCellWidth(new int[] { 100 });
							noData.setBorderDetail(3);
							noData.setBorder(true);
							noData.setBlankRowsBelow(1);
							rg.addTableToDoc(noData);
						}
					} catch (Exception e) {
						logger
								.error("exception in account master object",
										e);
					} // end of catch
					
				}

			} else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				// noData.setHeader(columns);
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				
				noData.setBorder(true);
				noData.setBlankRowsBelow(1);
				rg.addTableToDoc(noData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
		}
	
	/**This is used to get getCRMReport.
	 * @param bean : AccountMaster
	 * @param response
	 * @param request
	 * @param reportPath
	 */
	public void getCRMReport(AccountMaster bean, HttpServletResponse response,
			HttpServletRequest request, String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getRptType();
			rds.setReportType(type);
			String fileName = "CRM/Reports Mapping  " + Utility.getRandomNumber(1000);
			String reportPathName = reportPath+fileName + "." + type;
			rds.setFileName(fileName);
			rds.setReportName("CRM/Reports Mapping ");
			rds.setPageSize("A4");
			rds.setPageOrientation("portrait");
			rds.setUserEmpId(bean.getUserEmpId());
			
			/*rds.setMarginBottom(25);
			rds.setMarginLeft(25);
			rds.setMarginRight(25);*/
			rds.setShowPageNo(true);
			rds.setTotalColumns(10);
			//rds.setMarginTop(25);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath , session, context, request);
				request.setAttribute("reportPath", reportPath+fileName + "." + type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "AccountMaster_input.action");
			}
			rg.SPLIT_LATE=true;
			rg = getCRMMapReport(rg, bean);
			rg.process();
			if (reportPath.equals("")) {
				rg.createReport(response);
			} else {
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**This is used to Report.
	 * @param rg
	 * @param bean : ShiftRoster
	 * @return
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getCRMMapReport(org.paradyne.lib.ireportV2.ReportGenerator rg, AccountMaster bean) {
		try {
			
			java.util.Date d = new java.util.Date();
			logger.info("Date : - " + d);
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"dd-MM-yyyy");
			String strDate = sdf.format(d);

			Object[][] dateData = new Object[1][1];
			dateData[0][0] = "Date: - " + strDate;
			int[] cellWidthDateHeader = { 100 };
			int[] cellAlignDateHeader = { 2 };
			TableDataSet tableheadingDateData = new TableDataSet();
			tableheadingDateData.setData(dateData);
			tableheadingDateData.setCellWidth(cellWidthDateHeader);
			tableheadingDateData.setCellAlignment(cellAlignDateHeader);
			tableheadingDateData.setBorder(false);
			// tableheadingDateData.setHeaderTable(true);
			tableheadingDateData.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingDateData);

			String parentNameQuery = " SELECT ACCOUNT_CODE,ACCOUNT_ID,ACCOUNT_NAME FROM CR_CLIENT_MASTER WHERE PARENT_FLAG ='Y' ORDER BY ACCOUNT_CODE ASC ";
			Object[][] processDetail = getSqlModel().getSingleResult(
					parentNameQuery);
			if (processDetail != null && processDetail.length > 0) {
				
				for (int i = 0; i < processDetail.length; i++) {
					
					Object[][] reportData = null;
					String reportQuery = "SELECT MAPP_CODE, ACCOUNT_CODE, CR_ACC_REPORT_MAPP.REPORT_CODE ,REPORT_NAME "
						+" FROM CR_ACC_REPORT_MAPP "
						+"  INNER JOIN CR_REPORT_MASTER ON (CR_REPORT_MASTER.REPORT_CODE = CR_ACC_REPORT_MAPP.REPORT_CODE) " +
						"WHERE ACCOUNT_CODE = "
						+ processDetail[i][0];
					reportData = getSqlModel().getSingleResult(
							reportQuery);
					
					String rptName = "";
					if(reportData != null && reportData.length > 0){
						for(int k=0; k<reportData.length; k++){
							rptName = rptName + reportData[k][3] + ",";
						}
						rptName = rptName.substring(0, (rptName.length() - 1));
						
					}
					
					Object[][] parentNameDetails = new Object[2][4];// new-------------->
					parentNameDetails[0][0] = "Account ID :";
					parentNameDetails[0][1] = String.valueOf(processDetail[i][1]);
					parentNameDetails[0][2] = "Account Name :";
					parentNameDetails[0][3] = String.valueOf(processDetail[i][2]);
					
					parentNameDetails[1][0] = "Map report :";
					parentNameDetails[1][1] = String.valueOf(rptName);
					parentNameDetails[1][2] = "";
					parentNameDetails[1][3] = "";

					TableDataSet parentInfoTable = new TableDataSet();
					parentInfoTable.setData(parentNameDetails);
					parentInfoTable.setCellWidth(new int[] { 25, 25, 25, 25 });
					parentInfoTable.setCellAlignment(new int[] { 0, 0, 0, 0 });
					parentInfoTable.setBodyFontStyle(1);  
					parentInfoTable.setBorderDetail(2);
					rg.addTableToDoc(parentInfoTable);

					Object[][] childData = null;
					try {

						String childDataQuery = "SELECT  ROWNUM AS SNo , EMP_TOKEN,EMP_FNAME||' '|| EMP_MNAME," +
								" TO_CHAR(CR_ACC_CRM_MAPP.RECORD_CREATEDON,'DD-MM-YYYY HH:MM AM') "
										+"	FROM CR_ACC_CRM_MAPP "
										+"	LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = CR_ACC_CRM_MAPP.CRM_CODE) " +
												"WHERE ACCOUNT_CODE = "
								+ processDetail[i][0];

						childData = getSqlModel().getSingleResult(
								childDataQuery);

						String[] columns = new String[] { "S.No.",
								"Emp Id","CRM Name","Added On" };

						int[] cellWidthChallan = { 7,15,26,18 };
						int[] cellAlignChallan = { 0, 0,0, 0 };
						Object[][]obj=new Object[childData.length][4];
						
						
						if (childData != null && childData.length > 0) {

							for (int j = 0; j < childData.length; j++) {
								obj[j][0]=childData[j][0];
								obj[j][1]=childData[j][1];
								obj[j][2]=childData[j][2];
								obj[j][3]=childData[j][3];
								
							}
							
							TableDataSet branchDetails = new TableDataSet();

							branchDetails.setHeader(columns);
							branchDetails.setHeaderBorderDetail(3);
							branchDetails.setData(obj);
							branchDetails.setCellWidth(cellWidthChallan);
							branchDetails.setCellAlignment(cellAlignChallan);
							branchDetails.setBorder(true);
							branchDetails.setBorderDetail(3);
							branchDetails.setBlankRowsBelow(1);
							rg.addTableToDoc(branchDetails);
						} else {
							TableDataSet noData = new TableDataSet();
							Object[][] noDataObj = new Object[1][1];
							noDataObj[0][0] = "No records available";
							// noData.setHeader(columns);
							noData.setData(noDataObj);
							noData.setCellAlignment(new int[] { 1 });
							noData.setCellWidth(new int[] { 100 });
							noData.setBorderDetail(3);
							noData.setBorder(true);
							noData.setBlankRowsBelow(1);
							rg.addTableToDoc(noData);
						}
					} catch (Exception e) {
						logger
								.error("exception in account master object",
										e);
					} // end of catch
					
				}

			} else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				// noData.setHeader(columns);
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				
				noData.setBorder(true);
				noData.setBlankRowsBelow(1);
				rg.addTableToDoc(noData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
		}

	
	
}
