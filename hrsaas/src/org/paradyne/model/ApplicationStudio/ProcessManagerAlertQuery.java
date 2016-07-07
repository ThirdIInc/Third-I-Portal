package org.paradyne.model.ApplicationStudio;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.struts.action.ApplicationStudio.jobScheduling.JobLogger;

/**
 * 
 * @author VISHWAMBHAR DESHPANDE
 * 
 */
public class ProcessManagerAlertQuery extends ModelBase implements Job {
	/** logger. */
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ProcessManagerAlertQuery.class);
	/** request. */
	public HttpServletRequest request;
	/** client. */
	private String client = "";
	/** dbConn. */
	private Connection dbConn = null;
	/** autoUploadID. */
	private String autoUploadID = "";
	/** dbDriver. */
	private static String dbDriver = null;
	/** dbUrl. */
	private static String dbUrl = null;
	/** dbUsername. */
	private static String dbUsername = null;
	/** dbPassword. */
	private static String dbPassword = null;

	/** This method is used for setting httpServletRequest. */
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	/** This method is used for getting request. */
	public HttpServletRequest getRequest() {
		return request;
	}

	/** This method is used for setting httpServletRequest. */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/** This method is used for getting dbDriver */
	public static String getDbDriver() {
		return dbDriver;
	}

	/** This method is used for setting dbDriver. */
	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	/** This method is used for getting dbUrl. */
	public String getDbUrl() {
		return dbUrl;
	}

	/** This method is used for setting dbUrl. */
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	/** This method is used for getting dbUsername. */
	public String getDbUsername() {
		return dbUsername;
	}

	/** This method is used for setting dbUsername. */
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	/** This method is used for getting dbPassword. */
	public String getDbPassword() {
		return dbPassword;
	}

	/** This method is used for setting dbPassword. */
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	/**
	 * This method is called by scheduler to execute the defined Job.
	 */
	public void execute(JobExecutionContext jobExecutionContext) {
		try {
			dbConn = getConnection(jobExecutionContext);
			autoUploadID = getAutoUploadId(jobExecutionContext);
			executeAlert(jobExecutionContext, dbConn, autoUploadID);
		} catch (Exception e) {
			JobLogger.error(client, "Exception in execute-" + e);
			e.printStackTrace();
			JobLogger.printStackTrace(client, e);
		}
	}

	/**
	 * This method is used for getting Job id.
	 * 
	 * @param jobExecutionContext
	 * @return String
	 */
	private String getAutoUploadId(JobExecutionContext jobExecutionContext) {
		String autoUploadID = jobExecutionContext.getJobDetail()
				.getJobDataMap().getString("NAME").split("_")[1];
		return autoUploadID;
	}

	/**
	 * This method is used for getting connection of client defined in the job
	 * property file
	 * 
	 * @param jobExecutionContext
	 * @return Connection
	 */
	private Connection getConnection(JobExecutionContext jobExecutionContext) {
		try {
			client = jobExecutionContext.getJobDetail().getJobDataMap()
					.getString("CLIENT");
			final String autoUploadID = jobExecutionContext.getJobDetail()
					.getJobDataMap().getString("NAME").split("_")[1];
			if (!(client == null || client.equals("null") || client
					.equals(null))) {
				ResourceBundle bundle = ResourceBundle
						.getBundle("org.paradyne.lib.ConnectionModel");
				dbDriver = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(bundle
						.getString(client + ".driver"));
				dbUrl = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(bundle
						.getString(client + ".url"));
				dbUsername = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(bundle
						.getString(client + ".username"));
				dbPassword = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
						StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(bundle
						.getString(client + ".password"));
				setDbDriver(dbDriver);
				setDbUrl(dbUrl);
				setDbUsername(dbUsername);
				setDbPassword(dbPassword);
				setClient(client);
				try {
					if (dbDriver != null) {
						Class.forName(dbDriver);
						dbConn = java.sql.DriverManager.getConnection(dbUrl,
								dbUsername, dbPassword);
					}
				} catch (Exception e) {
					JobLogger.error(client, "Exception in execute-" + e);
					JobLogger.printStackTrace(client, e);
					e.printStackTrace();
				} finally {
					// if(dbConn != null) { dbConn.close(); }
				}
			}
		} catch (Exception e) {
			JobLogger.error(client, "Exception in execute-" + e);
			e.printStackTrace();
			JobLogger.printStackTrace(client, e);
		}
		return dbConn;
	}

	/**
	 * This method is used for executing alert defined in alert admin setting.
	 * 
	 * @param jobExecutionContext
	 * @param conn
	 * @param autoUploadID
	 */

	private void executeAlert(JobExecutionContext jobExecutionContext,
			Connection conn, String autoUploadID) {
		try {
			final  Object[][] getAlertSettingObj = getAlertSetting(autoUploadID, conn);
			if (getAlertSettingObj != null && getAlertSettingObj.length > 0) {
				String sqlQuery = "";
				for (int i = 0; i < getAlertSettingObj.length; i++) {
					sqlQuery = checkNull(String
							.valueOf(getAlertSettingObj[i][1]));
					if (!sqlQuery.equals("")) {
						if (conn != null) {
							getSqlModel().singleExecute(sqlQuery, conn);
						}
					}
					if (String.valueOf(getAlertSettingObj[i][7]).equals("Y")) {
						getEmailAlerts(conn, String
								.valueOf(getAlertSettingObj[i][10]), String
								.valueOf(getAlertSettingObj[i][0]), String
								.valueOf(getAlertSettingObj[i][13]), String
								.valueOf(getAlertSettingObj[i][14]), String
								.valueOf(getAlertSettingObj[i][15]), Integer
								.parseInt(String
										.valueOf(getAlertSettingObj[i][16])),
								request);
					}
					if (String.valueOf(getAlertSettingObj[i][8]).equals("Y")) {
						saveAlert(String.valueOf(getAlertSettingObj[i][10]),
								conn, getAlertSettingObj);
					}
					if (!String.valueOf(getAlertSettingObj[i][12]).equals("NA")) {
						executeMethod(
								String.valueOf(getAlertSettingObj[i][12]), conn);
					}

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * This method is used for executing method defined in the alert admin
	 * setting.
	 * 
	 * @param methodName
	 * @param dbconn
	 */
	private void executeMethod(String methodName, Connection dbconn) {
		try {
			String methodNameArr[] = null;
			String fullyQualifiedNameStr = "";
			String aClass = "";
			  String aMethod = "";
			fullyQualifiedNameStr = methodName.replace('.', '#');
			methodNameArr = fullyQualifiedNameStr.split("#");
			if (methodNameArr != null && methodNameArr.length > 0) {
				for (int i = 0; i < methodNameArr.length; i++) {
					if (i == methodNameArr.length - 1) {
						aMethod += methodNameArr[i];
					}
				}
				for (int j = 0; j < methodNameArr.length - 1; j++) {
					if (j == methodNameArr.length - 2) {
						aClass += methodNameArr[j];
					} else {
						aClass += methodNameArr[j] + ".";
					}
				}
			}
			invoke(aClass, aMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used for dynamic invoking method of class.
	 * 
	 * @param aClass
	 * @param aMethod
	 */
	private void invoke(String aClass, String aMethod) {
		try {
			if (!aClass.equals("") && !aMethod.equals("")) {
				Class modelClass = Class.forName(aClass);
				Method modelMethod = modelClass.getDeclaredMethod(aMethod);
				ModelBase modelInstance = (ModelBase) modelClass.newInstance();
				modelMethod.invoke(modelInstance);
				modelInstance.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used for sending email alert
	 * 
	 * @param conn
	 * @param templateName
	 * @param keepInformIds
	 * @param isLinkEmail
	 * @param linkParams
	 * @param linkParamValue
	 * @param noOfLinkParameters
	 * @param request
	 */
	private void getEmailAlerts(Connection conn, String templateName,
			String keepInformIds, String isLinkEmail, String linkParams,
			String linkParamValue, int noOfLinkParameters,
			HttpServletRequest request) {
		try {
			   Object[][] data = null;
			Object[][] noOfQueryObj = null;
			int noOfParams = 0;
			String parameter = "";
			String[] parameterValue = null;
			String[] moreThanOneParameter = null;
			String value = "";
			String[] moreThanOneParameterValue = null;
			String link = isLinkEmail;
			final String query = "SELECT  QUERY_STRING,QUERY_TYPE "
					+ " FROM HRMS_EMAILTEMPLATE_DTL "
					+ " WHERE HRMS_EMAILTEMPLATE_DTL.TEMPLATE_ID ="
					+ getTemplateCode(templateName, conn)
					+ " AND HRMS_EMAILTEMPLATE_DTL.QUERY_ID = 1 ";
			data = getSqlModel().getSingleResult(query, conn);
			   Object[][] queryData = null;
			queryData = getSqlModel().getSingleResult(
					String.valueOf(data[0][0]), conn);
			final String noOfQuery = "   SELECT  QUERY_ID, QUERY_STRING, TEMPLATE_ID, QUERY_TYPE,QUERY_PARAM_NO, QUERY_PARAMETERS "
					+ " FROM HRMS_EMAILTEMPLATE_DTL "
					+ " WHERE HRMS_EMAILTEMPLATE_DTL.TEMPLATE_ID ="
					+ getTemplateCode(templateName, conn)
					+ "  ORDER BY QUERY_ID ";
			noOfQueryObj = getSqlModel().getSingleResult(noOfQuery, conn);
			if (queryData != null && queryData.length > 0) {
				for (int i = 0; i < queryData.length; i++) {
					EmailTemplateBody template_applicant = new EmailTemplateBody();
					template_applicant.setEmailTemplate(templateName, conn);
					template_applicant.getTemplateQueries(conn);
					if (noOfQueryObj != null && noOfQueryObj.length > 0) {
						for (int j = 1; j <= (noOfQueryObj.length - 1); j++) {
							EmailTemplateQuery templateQueryApp2 = template_applicant
									.getTemplateQuery(j + 1);
							templateQueryApp2 = template_applicant
									.getTemplateQuery(j + 1);
							noOfParams = Integer.parseInt(String
									.valueOf(noOfQueryObj[j][4]));
							if (!parameter.equals("null")
									|| !parameter.equals("")) {
								if (noOfParams == 1) {
									parameter = String
											.valueOf(noOfQueryObj[j][5]);
									parameterValue = parameter.split("-");
									value = String.valueOf(queryData[i][Integer
											.parseInt(Character
													.toString(parameterValue[2]
															.charAt(1)))]);
								}
								if (noOfParams > 1) {
									moreThanOneParameter = String.valueOf(
											noOfQueryObj[j][5]).split(",");
									moreThanOneParameterValue = new String[noOfParams];
									for (int k = 0; k < moreThanOneParameter.length; k++) {
										parameterValue = moreThanOneParameter[k]
												.split("-");
										moreThanOneParameterValue[k] = String
												.valueOf(queryData[i][Integer
														.parseInt(Character
																.toString(parameterValue[2]
																		.charAt(1)))]);
									}
								}
								for (int k = 1; k <= noOfParams; k++) {
									if (noOfParams == 1) {
										templateQueryApp2
												.setParameter(k, value);
									} else {
										int count = 1;
										for (int k2 = 0; k2 < moreThanOneParameterValue.length; k2++) {
											templateQueryApp2
													.setParameter(
															count,
															String
																	.valueOf(moreThanOneParameterValue[k2]));
											count++;
										}
									}
								}
							}
						}
					}
					template_applicant.configMailAlert(conn);

					template_applicant.sendApplicationMailToKeepInfo(
							keepInformIds, conn);
					if (!isLinkEmail.equals("NA")) {
						// String applicationType = "TYD";
						final String applicationType = "applicationType";

						final String[] link_parameter = new String[1];

						link_parameter[0] = applicationType + "#" + ""
								+ String.valueOf(queryData[i][0]) + ""
								+ "#applicationDtls#";
						if (noOfLinkParameters > 0) {
							link += "?" + linkParams;
							String str = "";

							if (noOfLinkParameters == 1) {
								str = "P1";
								parameterValue = linkParamValue.split("-");
								value = String.valueOf(queryData[i][Integer
										.parseInt(Character
												.toString(parameterValue[2]
														.charAt(1)))]);
								if (link.contains(str)) {
									link = link.replaceAll(str, value);

								}
							}
							int count = 0;
							if (noOfLinkParameters > 1) {
								for (int j = 0; j < noOfLinkParameters; j++) {
									str = "P" + String.valueOf(j + 1);
									moreThanOneParameter = linkParamValue
											.split(",");
									moreThanOneParameterValue = new String[noOfLinkParameters];
									for (int k = 0; k < moreThanOneParameter.length; k++) {
										parameterValue = moreThanOneParameter[k]
												.split("-");
										moreThanOneParameterValue[k] = String
												.valueOf(queryData[i][Integer
														.parseInt(Character
																.toString(parameterValue[2]
																		.charAt(1)))]);
									}

									for (int val = 0; val < moreThanOneParameterValue.length; val++) {
										if (link.contains(str)) {
											link = link
													.replaceAll(
															str,
															moreThanOneParameterValue[count]);
										}
									}
									count++;
								}
							}

						}
						link_parameter[0] += link;
						final String[] link_label = new String[1];
						link_label[0] = "here";
						template_applicant.addOnlineLinkForALert(getClient()
								.trim(), link_parameter, link_label);
					}
					template_applicant.sendApplicationMail(conn);
					template_applicant.clearParameters();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used for getting alert setting defined in the alert admnin
	 * setting
	 * 
	 * @param autoUploadID
	 * @param dbconn
	 * @return
	 */
	private Object[][] getAlertSetting(String autoUploadID, Connection dbconn) {
		Object[][] getAlertSettingObj = null;
		try {
			getAlertSettingObj = null;
			final String sqlQuery = "  SELECT JOB_ALERT_EMPID ,JOB_QUERY ,JOB_MESSAGE_TYPE,JOB_DATE, JOB_SUBJECT, JOB_MODULE_NAME, JOB_QUERY_TYPE,JOB_MESSAGE_EMAIL, JOB_MESSAGE_ALERT ,JOB_TEMPLATE_CODE ,TEMPLATE_NAME , JOB_ID "
					+ " ,NVL(JOB_FUNCTION_NAME ,'NA'),NVL(JOB_EMAIL_LINK,'NA'),NVL(JOB_LINK_PARAMS,'NA') ,NVL(JOB_LINK_PARAMVALUE,'NA'),NVL(JOB_LINK_NO_PARAMS,0) FROM HRMS_JOB LEFT JOIN HRMS_EMAILTEMPLATE_HDR ON (HRMS_EMAILTEMPLATE_HDR.TEMPLATE_ID = HRMS_JOB.JOB_TEMPLATE_CODE) WHERE JOB_ID="
					+ autoUploadID;
			getAlertSettingObj = getSqlModel()
					.getSingleResult(sqlQuery, dbconn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAlertSettingObj;
	}

	/**
	 * This method is used for saving alert data into the HRMS_ALERT_MESSAGE
	 * tabler
	 * 
	 * @param templateName
	 * @param conn
	 * @param getAlertSettingObj
	 */
	public void saveAlert(String templateName, Connection conn,
			Object[][] getAlertSettingObj) {
		try {

			final String query = "SELECT  QUERY_STRING,QUERY_TYPE "
					+ " FROM HRMS_EMAILTEMPLATE_DTL "
					+ " WHERE HRMS_EMAILTEMPLATE_DTL.TEMPLATE_ID ="
					+ getTemplateCode(templateName, conn)
					+ " AND HRMS_EMAILTEMPLATE_DTL.QUERY_ID = 1 ";
			final  Object[][] data = getSqlModel().getSingleResult(query, conn);
			Object[][] queryData = null;
			queryData = getSqlModel().getSingleResult(
					String.valueOf(data[0][0]), conn);
			if (queryData != null && queryData.length > 0) {
				for (int i = 0; i < queryData.length; i++) {
					final String empID = String.valueOf(queryData[i][0]);
					final String subject = String.valueOf(getAlertSettingObj[0][4]);
					final String module = String.valueOf(getAlertSettingObj[0][5]);
					final String msgType = String.valueOf(getAlertSettingObj[0][2]);
					final String addAlertSql = " INSERT INTO HRMS_ALERT_MESSAGE(ALERT_ID, ALERT_SUBJECT,ALERT_EMP_ID, ALERT_DATE,  ALERT_MODULE, ALERT_MESSAGE_TYPE,ALERT_ACTIVE)"
							+ " VALUES((SELECT NVL(MAX(ALERT_ID), 0) + 1 FROM HRMS_ALERT_MESSAGE),'"
							+ subject
							+ "',"
							+ empID
							+ ",SYSDATE,'"
							+ module
							+ "','" + msgType + "','Y')";
					getSqlModel().singleExecute(addAlertSql, conn);
				}
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * This method is used for checking null value.If null value is getting it
	 * returns blank.
	 * 
	 * @param result
	 * @return
	 */
	private String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * This method is used for getting email template code.
	 * 
	 * @param templateName
	 * @param dbconn
	 * @return
	 */
	public String getTemplateCode(String templateName, Connection dbconn) {
		String templateID = "";
		try {
			final String templateIDSql = " SELECT TEMPLATE_ID FROM HRMS_EMAILTEMPLATE_HDR WHERE TEMPLATE_NAME LIKE '"
					+ templateName + "'";
			final  Object[][] templateIDQbj = getSqlModel().getSingleResult(
					templateIDSql, dbconn);
			templateID = String.valueOf(templateIDQbj[0][0]);
		} catch (Exception e) {
			logger.info(e);
		}
		return templateID;
	}

	/**
	 * This method is used for getting email address for keep informed to
	 * employees defined in the alert client setting form.
	 * 
	 * @param empCode
	 * @param dbconn
	 * @return
	 */
	private Object[][] getEmailAddressForKeepInfoEmp(String empCode,
			Connection dbconn) {
		Object[][] emailIdsObj = null;
		try {
			final String emailIdQuery = " SELECT ADD_EMAIL AS TO_EMAIL_ID FROM HRMS_EMP_ADDRESS WHERE EMP_ID IN("
					+ empCode
					+ ") "
					+ " AND ADD_TYPE='P' and  ADD_EMAIL IS NOT NULL ";
			emailIdsObj = getSqlModel().getSingleResult(emailIdQuery, dbconn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailIdsObj;

	}

	/**
	 * This method is used for updating leave balance of employees who has
	 * completed 1 Year.
	 */
	private void oneYearCompleteEmpBalanceUpdate() {
		try {
			Class.forName(getDbDriver());
			Connection conn = java.sql.DriverManager.getConnection(getDbUrl(),
					getDbUsername(), getDbPassword());

			final String sqlQuery = "  SELECT EMP_ID, MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE),12-TO_NUMBER(TO_CHAR(SYSDATE,'MM')) "
					+ " ,1.25*(12-TO_NUMBER(TO_CHAR(SYSDATE,'MM')) ) AS LEAVE_BALANCE "
					+ " FROM HRMS_EMP_OFFC "
					+ " WHERE MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)=12 ";

			final  Object[][] empIdObj = getSqlModel().getSingleResult(sqlQuery, conn);

			if (empIdObj != null && empIdObj.length > 0) {

				for (int i = 0; i < empIdObj.length; i++) {
					final String selectQuery = " SELECT LEAVE_OPENING_BALANCE,LEAVE_CLOSING_BALANCE FROM HRMS_LEAVE_BALANCE "
							+ " WHERE EMP_ID="
							+ String.valueOf(empIdObj[i][0])
							+ " AND LEAVE_CODE=1 ";

					final  Object[][] empPresentObj = getSqlModel().getSingleResult(
							selectQuery, conn);
					if (empPresentObj != null && empPresentObj.length > 0) {
						final String updateLeaveBalQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_OPENING_BALANCE=LEAVE_OPENING_BALANCE+"
								+ String.valueOf(empIdObj[i][3])
								+ " ,LEAVE_CLOSING_BALANCE=LEAVE_CLOSING_BALANCE+"
								+ String.valueOf(empIdObj[i][3])
								+ " ,LEAVE_ONHOLD =LEAVE_ONHOLD+0"
								+ " WHERE LEAVE_CODE=1 AND EMP_ID IN("
								+ String.valueOf(empIdObj[i][0]) + ")";

						getSqlModel().singleExecute(updateLeaveBalQuery, conn);
					} else {
						final String insertQuery = " INSERT INTO HRMS_LEAVE_BALANCE(EMP_ID, LEAVE_CODE, LEAVE_OPENING_BALANCE, LEAVE_CLOSING_BALANCE, LEAVE_ONHOLD) "
								+ " VALUES("
								+ String.valueOf(empIdObj[i][0])
								+ ",1,"
								+ String.valueOf(empIdObj[i][3])
								+ ","
								+ String.valueOf(empIdObj[i][3]) + ",0) ";
						getSqlModel().singleExecute(insertQuery, conn);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used for updating leave balance of employees who has
	 * completed 1 Year.
	 */
	private void oneYearCompleteEmpBalanceUpdate_Old() {
		try {
			Class.forName(getDbDriver());
			Connection conn = java.sql.DriverManager.getConnection(getDbUrl(),
					getDbUsername(), getDbPassword());

			final String sqlQuery = "  SELECT EMP_ID, MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE),12-TO_NUMBER(TO_CHAR(SYSDATE,'MM')) "
					+ " ,1.25*(12-TO_NUMBER(TO_CHAR(SYSDATE,'MM')) ) AS LEAVE_BALANCE "
					+ " FROM HRMS_EMP_OFFC "
					+ " WHERE MONTHS_BETWEEN(SYSDATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)=12 AND EMP_STATUS='S' ";

			final  Object[][] empIdObj = getSqlModel().getSingleResult(sqlQuery, conn);

			if (empIdObj != null && empIdObj.length > 0) {

				for (int i = 0; i < empIdObj.length; i++) {
					final String selectQuery = " SELECT LEAVE_OPENING_BALANCE,LEAVE_CLOSING_BALANCE FROM HRMS_LEAVE_BALANCE "
							+ " WHERE EMP_ID="
							+ String.valueOf(empIdObj[i][0])
							+ " AND LEAVE_CODE=1 ";

					final  Object[][] empPresentObj = getSqlModel().getSingleResult(
							selectQuery, conn);
					if (empPresentObj != null && empPresentObj.length > 0) {
						final String updateLeaveBalQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_OPENING_BALANCE=LEAVE_OPENING_BALANCE+2 ,LEAVE_CLOSING_BALANCE=LEAVE_CLOSING_BALANCE+2 ,LEAVE_ONHOLD =0"
								+ " WHERE LEAVE_CODE=1 AND EMP_ID IN("
								+ String.valueOf(empIdObj[i][0]) + ")";

						getSqlModel().singleExecute(updateLeaveBalQuery, conn);
					} else {
						final String insertQuery = " INSERT INTO HRMS_LEAVE_BALANCE(EMP_ID, LEAVE_CODE, LEAVE_OPENING_BALANCE, LEAVE_CLOSING_BALANCE, LEAVE_ONHOLD) "
								+ " VALUES("
								+ String.valueOf(empIdObj[i][0])
								+ ",1,2,2,0) ";
						getSqlModel().singleExecute(insertQuery, conn);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used for updating balance for new joinees
	 */
	private void newJoinEmeBalanceUpdate() {
		try {

			Class.forName(getDbDriver());
			Connection conn = java.sql.DriverManager.getConnection(getDbUrl(),
					getDbUsername(), getDbPassword());

			final String sqlQuery = " SELECT EMP_ID FROM HRMS_EMP_OFFC "
					+ " WHERE  "
					+ " TO_CHAR(SYSDATE,'DD-MM-YYYY')=TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') AND EMP_STATUS='S' ";

			final  Object[][] empIdObj = getSqlModel().getSingleResult(sqlQuery, conn);

			if (empIdObj != null && empIdObj.length > 0) {

				for (int i = 0; i < empIdObj.length; i++) {
					final String selectQuery = " SELECT LEAVE_OPENING_BALANCE,LEAVE_CLOSING_BALANCE FROM HRMS_LEAVE_BALANCE "
							+ " WHERE EMP_ID="
							+ String.valueOf(empIdObj[i][0])
							+ " AND LEAVE_CODE=2 ";

					final  Object[][] empPresentObj = getSqlModel().getSingleResult(
							selectQuery, conn);
					if (empPresentObj != null && empPresentObj.length > 0) {
						final String updateLeaveBalQuery = " UPDATE HRMS_LEAVE_BALANCE SET LEAVE_OPENING_BALANCE=3 ,LEAVE_CLOSING_BALANCE=3 ,LEAVE_ONHOLD =0"
								+ " WHERE LEAVE_CODE=2 AND EMP_ID IN("
								+ String.valueOf(empIdObj[i][0]) + ")";

						getSqlModel().singleExecute(updateLeaveBalQuery, conn);
					} else {
						final String insertQuery = " INSERT INTO HRMS_LEAVE_BALANCE(EMP_ID, LEAVE_CODE, LEAVE_OPENING_BALANCE, LEAVE_CLOSING_BALANCE, LEAVE_ONHOLD) "
								+ " VALUES("
								+ String.valueOf(empIdObj[i][0])
								+ ",2,3,3,0) ";
						getSqlModel().singleExecute(insertQuery, conn);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** This method is used for getting client */
	public String getClient() {
		return client;
	}

	/** This method is used for setting client */
	public void setClient(String client) {
		this.client = client;
	}

}
