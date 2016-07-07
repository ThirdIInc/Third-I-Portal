package org.paradyne.model.common;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.common.UserBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

public class UserModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	boolean flag = false;

	/**
	 * Insert new record
	 * @param bean
	 * @return boolean - insert values
	 */
	public boolean addUser(UserBean bean) {
		try {
			Object addObj[][] = new Object[1][9];
			addObj[0][0] = bean.getUserEmpID();
			//addObj[0][1] = bean.getUserProfileID();
			addObj[0][1] = bean.getLogin_profile_ID();
			addObj[0][2] = bean.getUserName();
			addObj[0][3] = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean
					.getUserPassword());
			// addObj[0][4] = bean.getUserActive();
			if (bean.getUserActive().equals("YES")) {
				addObj[0][4] = "Y";
			} else {
				addObj[0][4] = "N";
			}
			addObj[0][5] = "N";
			if (bean.getLockFlag() != null) {
				if (bean.getLockFlag().equals("true")) {
					addObj[0][5] = "Y";
				}
			}
			addObj[0][6] = bean.getLogin_access_profile_ID();
			addObj [0][7]= bean.getValidUpto();
			//Added by Prajakta B(4 June 2013)- If multiple profile codes are added assign one of them to PROFILE_CODE column in HRMS_LOGIN
			if(bean.getLogin_profile_ID().length() == 1){
				addObj[0][8] = bean.getLogin_profile_ID();
			}//end of if
			else{
			String[] profCode = bean.getLogin_profile_ID().split(",");
			addObj[0][8] = profCode[0];
			}//end of else
			//Ends added by Prajakta B(4 june 2013)
			String insertSql = " INSERT INTO HRMS_LOGIN(HRMS_LOGIN.LOGIN_CODE, HRMS_LOGIN.EMP_ID, HRMS_LOGIN.LOGIN_PROFILE, HRMS_LOGIN.LOGIN_NAME, HRMS_LOGIN.LOGIN_PASSWORD," 
				               +" HRMS_LOGIN.EMP_ACTIVE, HRMS_LOGIN.LOGIN_CREATE_DATE, HRMS_LOGIN.LOGIN_PWD_CHANGE_DATE," 
					           +" HRMS_LOGIN.LOCK_FLAG, HRMS_LOGIN.LOGIN_ACCESS_PROFILE,LOGIN_VALID_UPTO,PROFILE_CODE) "
					           +" VALUES ((SELECT NVL(MAX(LOGIN_CODE)+1,1) FROM HRMS_LOGIN),?,?,?,?,?,SYSDATE,SYSDATE,?,?,TO_DATE(?, 'DD-MM-YYYY'),?)  ";
			flag = getSqlModel().singleExecute(insertSql, addObj);
		} catch (Exception e) {
			logger.error("Exception Caught in AddUser: " + e);
		}
		return flag;
	}

	/**
	 * To check if user already exists Checking user-name 
	 * @param bean
	 * @return String
	 */
	public String checkUserExist(UserBean bean) {
		try {
			Object[][] empId = empId(bean.getUserToken());
			if (empId != null & empId.length > 0) {
				String query = " SELECT LOGIN_NAME FROM HRMS_LOGIN WHERE LOGIN_PROFILE = "
						+bean.getLogin_profile_ID()
						+ " AND EMP_ID = "
						+ String.valueOf(empId[0][0]);
				Object checkData[][] = getSqlModel().getSingleResult(query);
				if (checkData != null & checkData.length > 0)
					return "exist";
				return "notExist";
			}// END if
			else {
				return "notExist";
			}// END else
		} catch (Exception e) {
			logger.error("Exception is " + e);
			return "exist";
		}
	}

	/**
	 * To retrieve Employee ID from Employee token 
	 * @param token
	 * @return Object
	 */
	public Object[][] empId(String token) {
		Object tokenData[] = new Object[1];
		tokenData[0] = token;
		// logger.info("tokenData[0] in empId method--- "+tokenData[0]);
		String sqlSelect = " SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE UPPER(EMP_TOKEN)=UPPER('"
				+ token + "') ";
		logger.info("sqlSelect--- " + sqlSelect);
		if (context == null) {
			logger.info("  ActionContext.getContext() is null ");
		}// END if
		return getSqlModel().getSingleResult(sqlSelect);
	}

	/**
	 * To check after how many attempts a password can be reused 
	 * @param bean
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean checkReusePassword(UserBean bean, HttpServletRequest request)
			throws Exception {
		try {
			String reuseQuery = " SELECT PWD_REUSE_FLAG,NVL(PWD_REUSE_PERIODICITY,0) FROM HRMS_SETTINGS_PWD ";
			Object[][] reuseFlag = getSqlModel().getSingleResult(reuseQuery);
			/* Setting reuse count */
			if (reuseFlag != null && reuseFlag.length > 0) {

				int reuseCount = Integer.parseInt(String
						.valueOf(reuseFlag[0][1]));
				if (reuseFlag.length > 0
						&& String.valueOf(reuseFlag[0][0])
								.equalsIgnoreCase("Y")) {
					request.setAttribute("reusePeriodicity", reuseCount);
					String psswdHistoryQuery = "SELECT NEW_PASSWORD FROM HRMS_PASSWORD_HIST WHERE LOGIN_CODE = "
							+ bean.getLoginCode()
							+ " ORDER BY LOGIN_CHANGEDATE DESC";
					Object[][] psswdHistory = getSqlModel().getSingleResult(
							psswdHistoryQuery);
					/* Comparing with password history */
					if (psswdHistory != null & psswdHistory.length > 0) {
						for (int i = 0; i < reuseCount; i++) {
							String newEncriptPwd = new StringEncrypter(
									StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
									.encrypt(bean.getUserPassword());
							if (newEncriptPwd.equals(String
									.valueOf(psswdHistory[i][0]))) {
								logger.info("password is repeating....!!  "
										+ psswdHistory[i][0] + "----- "
										+ newEncriptPwd);
								return false;
							}// END nested if (password history)
						}
					}// END nested if (password history)
				}// END nested if length>0 (reuse flag)
			}// END if reuse flag
			return true;
		} catch (Exception e) {
			logger.error("Exception in password changing or user management "
					+ e);
			e.printStackTrace();
			return true;
		}
	}

	/**
	 * Modify the record - Updates table 
	 * @param bean
	 * @param request
	 * @return int
	 * @throws Exception
	 */
	public int modUser(UserBean bean, HttpServletRequest request)
			throws Exception {
		try {
			logger.info("I m in mod==================================");
			String query = "SELECT LOGIN_PASSWORD,LOGIN_NAME FROM  HRMS_LOGIN WHERE LOGIN_CODE ='"
					+ bean.getLoginCode() + "' ";
			Object[][] data = getSqlModel().getSingleResult(query);
			/* Changing Password */
			String oldPswd = "";
			if (bean.getUserPassword().equals(String.valueOf(data[0][0]))) {
				oldPswd = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME).decrypt(bean
						.getUserPassword());
				logger.info("In if ------------------------------------------ "
						+ oldPswd);
			}// END if
			else {
				logger.info("In Else----------------------------------------- "
						+ oldPswd);
				oldPswd = bean.getUserPassword();
			}// END else
			// Password re-usablity
			if (!checkReusePassword(bean, request))
				return 3;
			Object addObj[][] = new Object[1][8];
			//addObj[0][0] = bean.getUserProfileID();
			addObj[0][0] = bean.getLogin_profile_ID();
			addObj[0][1] = bean.getUserName();
			if (!bean.getUserPassword().equals("")) {
				addObj[0][2] = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
						.encrypt(oldPswd);
			}
			if (bean.getUserActive().equals("YES")) {
				addObj[0][3] = "Y";
			} else {
				addObj[0][3] = "N";
			}
			addObj[0][4] = "N";
			// Password Lock
			if (bean.getLockFlag() != null)
				if (bean.getLockFlag().equals("true"))
					addObj[0][4] = "Y";
			//addObj[0][5] = bean.getAccessProfileId();
			addObj[0][5] = bean.getLogin_access_profile_ID();
			addObj[0][6] = bean.getValidUpto();
			addObj[0][7] = bean.getLoginCode();
			String[] profCode = bean.getLogin_profile_ID().split(",");
			System.out.println("@@@@@@@@@@@@@@@"+profCode.length);
			for (int i = 0; i < addObj.length; i++) {
				for (int j = 0; j < addObj[i].length; j++) {
					System.out.println("addObj[" + i + "][" + j + "] "
							+ addObj[i][j]);
				}
			}
			String backUpQuery = " INSERT INTO HRMS_PASSWORD_HIST(LOGIN_CODE,OLD_PASSWORD,NEW_PASSWORD,LOGIN_CHANGEDATE) "
					+ " VALUES (?,?,?,SYSDATE) ";

			String updateQuery = " UPDATE HRMS_LOGIN SET LOGIN_PROFILE=?, LOGIN_NAME = ?, LOGIN_PASSWORD=?, EMP_ACTIVE=?,  "
					+ " LOGIN_PWD_CHANGE_DATE = SYSDATE, LOCK_FLAG=? ,LOGIN_ACCESS_PROFILE=? , LOGIN_VALID_UPTO= TO_DATE(?,'DD-MM-YYYY') WHERE LOGIN_CODE=? ";
			Object backUpObj[][] = new Object[1][3];
			backUpObj[0][0] = bean.getLoginCode();
			backUpObj[0][1] = data[0][0]; // old password
			backUpObj[0][2] = addObj[0][2]; // new password with encrypt
			getSqlModel().singleExecute(backUpQuery, backUpObj);
			for(int i = 0; i < addObj.length; i++) {
			for(int j = 0; j < addObj[i].length; j++) {
				logger.info("addObj[" + i + "][" + j + "]  " + addObj[i][j]);
			}
		}		
			boolean result = getSqlModel().singleExecute(updateQuery, addObj);
			if (result)
				return 1;
			return 2;
		} catch (Exception e) {
			logger.error("Exception in user modify " + e);
			return 2;
		}
	}

	/**
	 * To delete a user
	 * @param bean
	 * @return boolean Inserts record in the table
	 */
	public boolean deleteUser(UserBean bean) {
		String sqlDelete = " DELETE FROM HRMS_LOGIN WHERE LOGIN_CODE=? ";
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getLoginCode();
		return getSqlModel().singleExecute(sqlDelete, addObj);
	}

	/**
	 * Set the fields 
	 * @param bean
	 * @return String password
	 * @throws Exception
	 */
	public String getData(UserBean bean) throws Exception {
		String query = " SELECT EMP_TOKEN,LOGIN_NAME,LOGIN_PASSWORD ,LOGIN_VALID_UPTO, DECODE(EMP_ACTIVE,'Y','YES','N','NO'),HRMS_LOGIN.EMP_ID, "
						+ " HRMS_LOGIN.LOGIN_PROFILE, HRMS_PROFILE_HDR.PROFILE_NAME, " 
						+ " DECODE(LOCK_FLAG,'Y','true','false') FROM HRMS_LOGIN "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_LOGIN.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
						+ " LEFT JOIN HRMS_PROFILE_HDR ON (HRMS_PROFILE_HDR.PROFILE_CODE = HRMS_LOGIN.LOGIN_PROFILE) WHERE LOGIN_CODE ="
						+ bean.getLoginCode();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data.length > 0) {
			bean.setUserToken(String.valueOf(data[0][0]));
			bean.setUserName(String.valueOf(data[0][1]));
			bean.setUserPassword(new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).decrypt(String
					.valueOf(data[0][2])));
			bean.setValidUpto(String.valueOf(data[0][3]));
			bean.setUserActive(String.valueOf(data[0][4]));
			bean.setUserEmpID(String.valueOf(data[0][5]));
			bean.setUserProfileID(String.valueOf(data[0][6]));
			bean.setUserProfile(String.valueOf(data[0][7]));
			bean.setLogin_profile_ID(String.valueOf(data[0][8]));
			bean.setLogin_profile(String.valueOf(data[0][9]));
			bean.setLockFlag(String.valueOf(data[0][10]));
		}// END if
		return bean.getUserPassword();
	}

	/**
	 * Obtaining Password length
	 * @param request
	 */
	public void getPasswordLength(HttpServletRequest request) {
		String query = "SELECT NVL(PWD_MIN_LENGTH,'0'), NVL(PWD_MAX_LENGTH,'0'),PWD_INCLUDE_ALPHA,PWD_INCLUDE_SPCHAR,PWD_INCLUDE_NUM,PWD_INCLUDE_UPCASE  FROM HRMS_SETTINGS_PWD";
		Object[][] lengthObj = getSqlModel().getSingleResult(query);
		if (lengthObj != null & lengthObj.length > 0) {
			request.setAttribute("passInfoObj", lengthObj);
		}// END if
	}

	/**
	 * Checking filters for password with respect to Password Settings 
	 * @param password
	 * @return String
	 */
	public String checkPasswordSettings(String password) {
		try {
			logger.info("Password >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "
					+ password);
			/* Password Type */
			String checkQuery = "SELECT PWD_INCLUDE_ALPHA,PWD_INCLUDE_SPCHAR,PWD_INCLUDE_NUM,PWD_INCLUDE_UPCASE FROM HRMS_SETTINGS_PWD";
			Object chkObj[][] = getSqlModel().getSingleResult(checkQuery);
			String alphaFlag = "";
			String spCharFlag = "";
			String numFlag = "";
			String upCaseFlag = "";
			char tempPasswd[] = null;
			String passArray[] = null;
			// checking password - converts to a new character array
			tempPasswd = password.toCharArray();
			passArray = new String[tempPasswd.length];
			for (int i = 0; i < tempPasswd.length; i++)
				// set password
				passArray[i] = "" + tempPasswd[i];
			logger.info("Example ------------------------ "
					+ "abcdefghijklmnopqrstuvwxyz".indexOf("c"));
			logger.info("password >>>>>>>>>>>>> " + password.indexOf("a"));
			if (chkObj != null && chkObj.length > 0) {// chkObj
				for (int j = 0; j < passArray.length; j++) {// J loop

					if (String.valueOf(chkObj[0][0]).equals("Y")
							&& !alphaFlag.equals("Y")) {// Alphabets
						logger.info("Inside If Y index is " + j);
						alphaFlag = "N";
						if ("abcdefghijklmnopqrstuvwxyz".indexOf(passArray[j]) >= 0
								|| "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
										.indexOf(passArray[j]) >= 0)
							alphaFlag = "Y";
					}// END nested if Alphabets
					if (String.valueOf(chkObj[0][1]).equals("Y")
							&& !spCharFlag.equals("Y")) {// Special Character
						spCharFlag = "N";
						if ("`~!@#$%^&*()_-".indexOf(passArray[j]) >= 0)
							spCharFlag = "Y";
					}// END nested if Special Characters
					if (String.valueOf(chkObj[0][2]).equals("Y")
							&& !numFlag.equals("Y")) {// Numbers
						numFlag = "N";
						if ("0123456789".indexOf(passArray[j]) >= 0)
							numFlag = "Y";
					}// END nested if Numbers
					if (String.valueOf(chkObj[0][3]).equals("Y")
							&& !upCaseFlag.equals("Y")) {// Upper case
						upCaseFlag = "N";
						if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(passArray[j]) >= 0)
							upCaseFlag = "Y";
					}// END nested if Upper Case
				}// END J loop
				logger.info("Alpha Flag >>>>>>>>>>>>>>>>>>>>>>>> " + alphaFlag);
				if (alphaFlag.equals("N"))
					return "Password should contain atleast one alphabet";
				if (spCharFlag.equals("N"))
					return "Password should contain atleast one special character";
				if (numFlag.equals("N"))
					return "Password should contain atleast one number";
				if (upCaseFlag.equals("N"))
					return "Password should contain atleast one alphabet in upper case";
			}// END if chkObj
		} catch (Exception e) {
			logger
					.error("Exception in checking password settings for user management or my config "
							+ e);
		}
		return "";
	}

	// Added by Nilesh

	/**
	 * for selecting the data from list and setting those data in respective
	 * fields
	 */
	public void editBusinessData(UserBean userBean) {
		try {
			String query = " SELECT EMP_TOKEN,TRIM(EMP_FNAME)||' '||TRIM(EMP_MNAME)||' '||TRIM(EMP_LNAME) AS NAME,LOGIN_NAME," 
					      + " HRMS_PROFILE_HDR.PROFILE_NAME,DECODE(LOCK_FLAG,'Y','true','false'),LOGIN_CODE," 
					      + " DECODE(EMP_ACTIVE,'Y','YES','N','NO'),HRMS_LOGIN.LOGIN_PROFILE, HRMS_LOGIN.EMP_ID,"
					      + " NVL(HRMS_PROFILE_ACC_HDR.PROFILE_NAME,' '),HRMS_LOGIN.LOGIN_ACCESS_PROFILE, TO_CHAR(LOGIN_VALID_UPTO,'DD-MM-YYYY' )"
					      + " FROM HRMS_LOGIN "
					      + " INNER JOIN HRMS_EMP_OFFC ON (HRMS_LOGIN.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					      + " LEFT JOIN HRMS_PROFILE_HDR ON (HRMS_PROFILE_HDR.PROFILE_CODE = HRMS_LOGIN.LOGIN_PROFILE)"
					      + " LEFT JOIN HRMS_PROFILE_ACC_HDR ON (HRMS_PROFILE_ACC_HDR.PROFILE_CODE = HRMS_LOGIN.LOGIN_ACCESS_PROFILE)"
					      + " WHERE LOGIN_CODE= " + userBean.getLoginCode();
			Object[][] data = getSqlModel().getSingleResult(query);
			userBean.setUserToken(checkNull(String.valueOf(data[0][0])));
			userBean.setUserEmpName(checkNull(String.valueOf(data[0][1])));
			userBean.setUserName(checkNull(String.valueOf(data[0][2])));		
			userBean.setUserProfile(checkNull(String.valueOf(data[0][3])));
			userBean.setLockFlag(checkNull(String.valueOf(data[0][4])));
			userBean.setLoginCode(checkNull(String.valueOf(data[0][5])));		
			userBean.setUserActive(checkNull(String.valueOf(data[0][6])));
			userBean.setUserProfileID(checkNull(String.valueOf(data[0][7])));
			userBean.setUserEmpID(checkNull(String.valueOf(data[0][8])));		
			userBean.setAccessProfile(checkNull(String.valueOf(data[0][9])));
			userBean.setAccessProfileId(checkNull(String.valueOf(data[0][10])));		
			userBean.setLogin_access_profile(checkNull(String.valueOf(data[0][9])));
			userBean.setLogin_access_profile_ID(checkNull(String.valueOf(data[0][10])));
			userBean.setLogin_profile(checkNull(String.valueOf(data[0][3])));
			userBean.setLogin_profile_ID(checkNull(String.valueOf(data[0][7])));
			userBean.setValidUpto(checkNull(String.valueOf(data[0][11])));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean deleteCheckedRecords(UserBean bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(1), delete);
					if (!result)
						count++;
				}// end of if
			}// end of for loop
		}// end of if
		if (count != 0) {
			result = false;
			return result;
		}// end of if
		else {
			result = true;
			return result;
		}// end of else
	}

	/** Generating the list Onload */
	public void initialData(UserBean userBean, HttpServletRequest request) {
		try {
			String query = " SELECT LOGIN_CODE,EMP_TOKEN,TRIM(EMP_FNAME)||' '||TRIM(EMP_MNAME)||' '||TRIM(EMP_LNAME) AS NAME," 
					+ " LOGIN_NAME,DECODE(EMP_ACTIVE,'Y','YES','N','NO')," 
				    + " DECODE(LOCK_FLAG,'Y','true','false'), TO_CHAR(HRMS_LOGIN.LOGIN_VALID_UPTO,'DD-MM-YYYY' ),HRMS_LOGIN.EMP_ID "
					+ " FROM HRMS_LOGIN "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_LOGIN.EMP_ID = HRMS_EMP_OFFC.EMP_ID) ";
					
			if (userBean.isGeneralFlag()) {
				query += " WHERE HRMS_EMP_OFFC.EMP_ID="
						+ userBean.getUserEmpId();
			} else {
				if (userBean.getUserProfileDivision() != null
						&& userBean.getUserProfileDivision().length() > 0) {
					query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
							+ userBean.getUserProfileDivision() + ")";
				} else {
					query += " WHERE 1=1 ";
				}
			}
			query += " AND EMP_STATUS='S'";
			query += "	ORDER BY EMP_ACTIVE DESC ,LOGIN_NAME ASC";
			Object[][] regData = getSqlModel().getSingleResult(query);

			if (regData != null && regData.length > 0) {
				userBean.setModeLength("true");
				userBean.setTotalNoOfRecords(String.valueOf(regData.length));
				String[] pageIndex = Utility.doPaging(userBean.getMyPage(),
						regData.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					userBean.setMyPage("1");
				ArrayList<Object> List = new ArrayList<Object>();

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {

					UserBean bean = new UserBean();
					bean.setLoginCode(checkNull(String.valueOf(regData[i][0])));
					bean.setUserToken(checkNull(String.valueOf(regData[i][1])));
					bean.setUserEmpName(checkNull(String.valueOf(regData[i][2])));
					bean.setUserName(checkNull(String.valueOf(regData[i][3])));
					//bean.setUserProfile(checkNull(String.valueOf(regData[i][4])));				
					bean.setUserActive(checkNull(String.valueOf(regData[i][4])));
					bean.setValidUpto(checkNull(String.valueOf(regData[i][6])));
					List.add(bean);
				}// end of loop
				userBean.setUserList(List);
			}
			else {
				userBean.setUserList(null);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	// Check Null Function//

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public void getReport(UserBean userBean, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		ReportDataSet rds = new ReportDataSet();
		String type = "Xls"; 
		rds.setReportType(type);
		String fileName = "User Management Report"
				+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("User Management Report");
		rds.setShowPageNo(true);

		rds.setGeneratedByText(userBean.getUserEmpId());
		rds.setUserEmpId(userBean.getUserEmpId());
		rds.setPageSize("TABLOID");
		rds.setPageOrientation("portrait");
		rds.setTotalColumns(9);

		org.paradyne.lib.ireportV2.ReportGenerator rg = null;
		
		rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
		
		String query=" SELECT DISTINCT ROWNUM,HRMS_EMP_OFFC.EMP_TOKEN , TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+" HRMS_LOGIN.LOGIN_NAME,HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_NAME , DEPT_NAME, DIV_NAME, PROFILE_NAME "
					+" FROM HRMS_EMP_OFFC   "
					+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
					+" INNER JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK=HRMS_RANK.RANK_ID)    "
					+" INNER JOIN HRMS_CENTER  ON(HRMS_EMP_OFFC.EMP_CENTER=HRMS_CENTER.CENTER_ID)  "
					+" INNER JOIN HRMS_DEPT ON(HRMS_EMP_OFFC.EMP_DEPT=HRMS_DEPT.DEPT_ID) "
					+" INNER JOIN HRMS_LOGIN ON (HRMS_LOGIN.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+" INNER JOIN HRMS_PROFILE_HDR ON (HRMS_LOGIN.PROFILE_CODE=HRMS_PROFILE_HDR.PROFILE_CODE) " 
					+" INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) "
					+" WHERE EMP_STATUS='S' AND EMP_ACTIVE='Y' AND  LOCK_FLAG='N'";
		
		 Object[][] queryData = getSqlModel().getSingleResult(query);
		 
		if(queryData!=null){
			TableDataSet tableData = new TableDataSet();
			
			String[] header = { "Sr No.","Employee Id","Employee Name","Login Name","Designation","Branch"," Department","Division","Profile Name"};
					 
			tableData.setHeader(header);
			
			
			int[] cellWidth  = { 10,15, 20, 20, 20, 20, 20, 20, 20};
			tableData.setCellWidth(cellWidth);
			
			int[] cellAlign = { 1,0, 0,  0, 0,  0, 0, 0, 0};
			tableData.setCellAlignment(cellAlign);

			tableData.setData(queryData);
			//tableData.setCellColSpan(new int[] { 9});
			tableData.setBorderDetail(3);
			tableData.setHeaderBorderDetail(3);
			rg.addTableToDoc(tableData);
		}else{
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "\nNo records available\n\n";

			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });

			noData.setBorder(false);
			rg.addTableToDoc(noData);
		}
		
		rg.process();
		rg.createReport(response);
	}

}
