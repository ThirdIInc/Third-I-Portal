/**
 * 
 */
package org.paradyne.model.employeeSurvey;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.employeeSurvey.EmployeeSurveyConfiguration;
import org.paradyne.lib.MailModel;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SendEmail;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.model.autoBirthday.AutoBirthday;

/**
 * @author VISHWAMBHAR DESHPANDE
 * 
 */
public class EmployeeSurveyConfigurationModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeeSurveyConfigurationModel.class);

	public boolean setFilterList(String[] employeeId,
			EmployeeSurveyConfiguration empSurveyConfig) {

		boolean result = false;
		try {

			String empDtlQuery = " SELECT  NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') NAME ,EMP_ID FROM HRMS_EMP_OFFC WHERE 1=1 ";

			if (!empSurveyConfig.getDivCode().equals("")) {
				empDtlQuery += " AND HRMS_EMP_OFFC.EMP_DIV="
						+ empSurveyConfig.getDivCode();
			}

			if (!empSurveyConfig.getDeptCode().equals("")) {
				empDtlQuery += " AND HRMS_EMP_OFFC.EMP_DEPT="
						+ empSurveyConfig.getDeptCode();
			}
			if (!empSurveyConfig.getBranchCode().equals("")) {
				empDtlQuery += " AND HRMS_EMP_OFFC.EMP_CENTER="
						+ empSurveyConfig.getBranchCode();
			}
			if (empSurveyConfig.getDesgCode() != null
					&& empSurveyConfig.getDesgCode().length() > 0) {
				empDtlQuery += " AND HRMS_EMP_OFFC.EMP_RANK="
						+ empSurveyConfig.getDesgCode();
			}// end of if

			String str = "0";
			if (employeeId != null) {
				for (int i = 0; i < employeeId.length; i++) {
					str += "," + employeeId[i];
				}
			}

			empDtlQuery += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + str
					+ ") ";

			Object empDataObj[][] = getSqlModel().getSingleResult(empDtlQuery);

			String insertQuery = " INSERT INTO HRMS_EMPSURVEY_CONFIG(SURVEY_CODE,SURVEY_EMP_CODE) "
					+ "  VALUES(?,?)";
			if (empDataObj != null && empDataObj.length > 0) {
				Object saveObj[][] = new Object[empDataObj.length][2];
				for (int i = 0; i < empDataObj.length; i++) {
					saveObj[i][0] = empSurveyConfig.getSurveyCode().trim();
					saveObj[i][1] = empDataObj[i][2];
					System.out.println("saveObj[i][0]  " + saveObj[i][0]);
					System.out.println("saveObj[i][2]  " + saveObj[i][1]);
				}
				result = getSqlModel().singleExecute(insertQuery, saveObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public boolean addEmployee(String[] srNo, String[] empToken,
			String[] employeeId, String[] empName,
			EmployeeSurveyConfiguration empSurveyConfig) {

		try {
			EmployeeSurveyConfiguration bean = new EmployeeSurveyConfiguration();
			ArrayList<EmployeeSurveyConfiguration> List = displayNewValue(srNo,
					empToken, employeeId, empName, empSurveyConfig);
			empSurveyConfig.setSrNo(String.valueOf(List.size() + 1));// sr no
			List.add(bean);
			// leaveList.add(uploadLinks);
			empSurveyConfig.setList(List);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public void displayIttValues(String[] srNo, String[] empToken,
			String[] employeeId, String[] empName,
			EmployeeSurveyConfiguration empSurveyConfig) {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		try {
			if (empToken != null && empToken.length > 0) {
				for (int i = 0; i < empToken.length; i++) {
					EmployeeSurveyConfiguration bean = new EmployeeSurveyConfiguration();
					bean.setSrNo(srNo[i]);
					bean.setEmpToken(empToken[i]);
					bean.setEmpName(empName[i]);
					bean.setEmployeeId(employeeId[i]);
					list.add(bean);
				}
				empSurveyConfig.setList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean setNewAddedEmployee(String[] srNo, String[] empToken,
			String[] employeeId, String[] empName,
			EmployeeSurveyConfiguration empSurveyConfig) {
		try {
			EmployeeSurveyConfiguration bean = new EmployeeSurveyConfiguration();
			bean.setEmpToken(empSurveyConfig.getEmployeeToken());
			bean.setEmpName(empSurveyConfig.getEmployeeName());
			bean.setEmployeeId(empSurveyConfig.getEmployeeCode());
			ArrayList<EmployeeSurveyConfiguration> list = displayNewValue(srNo,
					employeeId, empToken, empName, empSurveyConfig);

			bean.setSrNo(String.valueOf(list.size() + 1));// sr no
			list.add(bean);
		} catch (Exception e) {

		}
		return true;
	}

	private ArrayList<EmployeeSurveyConfiguration> displayNewValue(
			String[] srNo, String[] employeeId, String[] empToken,
			String[] empName, EmployeeSurveyConfiguration empSurveyConfig) {
		// TODO Auto-generated method stub
		ArrayList<EmployeeSurveyConfiguration> list = null;
		try {
			list = new ArrayList<EmployeeSurveyConfiguration>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					EmployeeSurveyConfiguration bean = new EmployeeSurveyConfiguration();
					bean.setSrNo(srNo[i]);
					bean.setEmpToken(empToken[i]);
					bean.setEmpName(empName[i]);
					bean.setEmployeeId(employeeId[i]);
					list.add(bean);

				}

			}
			empSurveyConfig.setList(list);
		} catch (Exception e) {

		}
		return list;
	}

	public boolean deleteData(EmployeeSurveyConfiguration empSurveyConfig) {
		boolean result = false;
		try {

			String delQuery = "  DELETE FROM HRMS_EMPSURVEY_CONFIG  "
					+ "  WHERE SURVEY_CODE=" + empSurveyConfig.getSurveyCode()
					+ " AND SURVEY_EMP_CODE=" + empSurveyConfig.getHiddenEdit()
					+ " ";
			result = getSqlModel().singleExecute(delQuery);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean save(EmployeeSurveyConfiguration empSurveyConfig,
			String[] empCode) {
		boolean result = false;

		try {
			String insertQuery = " INSERT INTO HRMS_EMPSURVEY_CONFIG(SURVEY_CODE,SURVEY_EMP_CODE) "
					+ "  VALUES(?,?)";

			if (empCode != null && empCode.length > 0) {
				Object[][] saveDtlRecordObj = new Object[empCode.length][2];
				for (int i = 0; i < empCode.length; i++) {
					saveDtlRecordObj[i][0] = empSurveyConfig.getSurveyCode()
							.trim();
					saveDtlRecordObj[i][1] = empCode[i];
				}
				result = getSqlModel().singleExecute(insertQuery,
						saveDtlRecordObj);
				if (result) {
					String query = " SELECT NVL(MAX(SURVEY_CODE),0) FROM HRMS_EMPSURVEY_CONFIG ";
					Object autoCodeObj[][] = getSqlModel().getSingleResult(
							query);
					empSurveyConfig.setSurveyCode(String
							.valueOf(autoCodeObj[0][0]));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public void setSavedDtlRecord(EmployeeSurveyConfiguration empSurveyConfig,
			HttpServletRequest request) {
		try {
			String selectQuery = " SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')NAME "
					+ " ,SURVEY_EMP_CODE,SURVEY_CODE,NVL(SURVEY_NAME,'') "
					+ " FROM HRMS_EMPSURVEY_CONFIG "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMPSURVEY_CONFIG.SURVEY_EMP_CODE) "
					+ "  INNER JOIN HRMS_EMPSURVEY_MASTER ON(HRMS_EMPSURVEY_MASTER.SURVEY_CODE=HRMS_EMPSURVEY_CONFIG.SURVEY_CODE)"
					+ " WHERE SURVEY_CODE="
					+ empSurveyConfig.getSurveyCode()
					+ " ORDER BY NAME ";

			Object empDataObj[][] = getSqlModel().getSingleResult(selectQuery);
			boolean result = false;
			ArrayList list = new ArrayList();

			if (empDataObj != null && empDataObj.length > 0)
				empSurveyConfig.setModeLengthEmpFlag("true");
			else
				empSurveyConfig.setModeLengthEmpFlag("false");
			String[] pageIndex = Utility.doPaging(empSurveyConfig
					.getMyPageEmp(), empDataObj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPageEmp", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNoEmp", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				empSurveyConfig.setMyPageEmp("1");

			if (empDataObj != null && empDataObj.length > 0) {

				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					result = true;
					EmployeeSurveyConfiguration bean = new EmployeeSurveyConfiguration();
					bean
							.setEmpToken(checkNull(String
									.valueOf(empDataObj[i][0])));
					bean
							.setEmpName(checkNull(String
									.valueOf(empDataObj[i][1])));

					bean.setEmployeeId(checkNull(String
							.valueOf(empDataObj[i][2])));

					empSurveyConfig.setSurveyCode(checkNull(String
							.valueOf(empDataObj[i][3])));
					empSurveyConfig.setSurveyName(checkNull(String
							.valueOf(empDataObj[i][4])));

					empSurveyConfig.setShowFlag("true");

					list.add(bean);
				}
			}

			empSurveyConfig.setList(list);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean saveRecord(EmployeeSurveyConfiguration empSurveyConfig,
			String[] empCode) {
		boolean result = false;
		try {
			String delQuery = " DELETE FROM HRMS_EMPSURVEY_CONFIG WHERE  SURVEY_CODE="
					+ empSurveyConfig.getSurveyCode();

			boolean res = getSqlModel().singleExecute(delQuery);
			if (res) {
				String insertQuery = " INSERT INTO HRMS_EMPSURVEY_CONFIG(SURVEY_CODE,SURVEY_EMP_CODE) "
						+ "  VALUES(?,?)";

				if (empCode != null && empCode.length > 0) {
					Object[][] saveDtlRecordObj = new Object[empCode.length][2];
					for (int i = 0; i < empCode.length; i++) {
						saveDtlRecordObj[i][0] = empSurveyConfig
								.getSurveyCode().trim();
						saveDtlRecordObj[i][1] = empCode[i];

					}
					result = getSqlModel().singleExecute(insertQuery,
							saveDtlRecordObj);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public void callList(EmployeeSurveyConfiguration empSurveyConfig,
			HttpServletRequest request) {
		try {
			String selectQuery = " SELECT distinct nvl(SURVEY_NAME,''),HRMS_EMPSURVEY_CONFIG.SURVEY_CODE "
					+ " FROM HRMS_EMPSURVEY_CONFIG "
					+ "  inner join HRMS_EMPSURVEY_MASTER on(HRMS_EMPSURVEY_MASTER.SURVEY_CODE=HRMS_EMPSURVEY_CONFIG.SURVEY_CODE)"
					+" ORDER BY HRMS_EMPSURVEY_CONFIG.SURVEY_CODE ";	
			Object obj[][] = getSqlModel().getSingleResult(selectQuery);

			if (obj != null && obj.length > 0)
				empSurveyConfig.setModeLength("true");
			else
				empSurveyConfig.setModeLength("false");
			String[] pageIndex = Utility.doPaging(empSurveyConfig.getMyPage(),
					obj.length, 20);
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
				empSurveyConfig.setMyPage("1");

			boolean result = false;
			ArrayList list = new ArrayList();
			if (obj != null && obj.length > 0) {

				for (int i = 0; i < obj.length; i++) {

					result = true;
					EmployeeSurveyConfiguration bean = new EmployeeSurveyConfiguration();
					bean.setSurveyNameItt(checkNull(String.valueOf(obj[i][0])));
					bean.setHiddenAutoCodeItt(checkNull(String
							.valueOf(obj[i][1])));

					list.add(bean);
				}

				empSurveyConfig.setTableList(list);
				empSurveyConfig.setTotalRecords(String.valueOf(obj.length));
				empSurveyConfig.setListFlag("true");
			} else {
				empSurveyConfig.setNoData("true");// No data to display
				// message shown
				empSurveyConfig.setTableList(null);
				empSurveyConfig.setTotalRecords(String.valueOf(obj.length));
				empSurveyConfig.setListFlag("true");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean delete(EmployeeSurveyConfiguration empSurveyConfig) {
		boolean result = false;

		try {

			String delQuery = "   DELETE FROM HRMS_EMPSURVEY_CONFIG WHERE SURVEY_CODE="
					+ empSurveyConfig.getSurveyCode() + " ";

			result = getSqlModel().singleExecute(delQuery);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean publish(EmployeeSurveyConfiguration empSurveyConfig) {
		boolean result = false;

		try {
			String updateQuery = " UPDATE HRMS_EMPSURVEY_MASTER SET SURVEY_PUBLISH='Y' WHERE SURVEY_CODE="
					+ empSurveyConfig.getSurveyCode() + " ";
			result = getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean publishWithMailAlert(
			EmployeeSurveyConfiguration empSurveyConfig) {
		// TODO Auto-generated method stub
		boolean result = false;

		try {

			String empIdQuery = " SELECT SURVEY_EMP_CODE FROM HRMS_EMPSURVEY_CONFIG WHERE SURVEY_CODE="
					+ empSurveyConfig.getSurveyCode();

			Object empCode[][] = getSqlModel().getSingleResult(empIdQuery);

			System.out.println("empSurveyConfig.getSurveyCode()     "
					+ empSurveyConfig.getSurveyCode());
			String updateQuery = " UPDATE HRMS_EMPSURVEY_MASTER SET SURVEY_PUBLISH='Y' WHERE SURVEY_CODE="
					+  empSurveyConfig.getSurveyCode() + " ";
			result = getSqlModel().singleExecute(updateQuery);

			String emp = "";
			
			if(empCode!=null && empCode.length >0)
			{
				for (int i = 0; i < empCode.length; i++) {
					for (int j = 0; j < empCode[0].length; j++) {

						emp += String.valueOf(empCode[i][j]) + ",";
					}

				}
				
				emp = emp.substring(0, emp.length() - 1);
			}
			
			
			String query = "SELECT ADD_EMAIL AS TO_EMAIL_ID FROM HRMS_EMP_ADDRESS WHERE 1=1 ";

			query += new Utility().getConcatenatedIds("EMP_ID", emp);

			query += " AND ADD_TYPE='P' and  ADD_EMAIL IS NOT NULL";

			Object[][] data = getSqlModel().getSingleResult(query);

			

			if (data != null && data.length > 0) {
				System.out.println("data.l    " + data.length);
				
				String[] empCodeObj = new String[data.length];
				for (int i = 0; i < empCodeObj.length; i++) {
					empCodeObj[i] = String.valueOf(data[i][0]);
				}

				MailModel mail_model = new MailModel();
				mail_model.initiate(context, session);
				Object[][] mailBoxData = mail_model.getServerMailBox("", String
						.valueOf(data[0][0]));
				mail_model.terminate();

				MailUtility mod = new MailUtility();
				mod.initiate(context, session);
				
				
				String[] toEmpAddArr = new String[data.length];
				if (data != null && data.length > 0) {
					for (int j = 0; j < data.length; j++) {
						toEmpAddArr[j] = String
								.valueOf(data[j][0]);
					} // end of loop
				} // end of if
			 
				sendMail(toEmpAddArr, getDefaultMailIds(),
						empSurveyConfig.getSurveyMailSubject().trim(), empSurveyConfig.getHtmlcode(),
						mailBoxData);
		
			/*	
				
				
				
				mod.sendMail(empCodeObj, mod.getDefaultMailIds(),
						empSurveyConfig.getSurveyMailSubject().trim(),  empSurveyConfig.getHtmlcode(), "", "", true);
*/
				mod.terminate();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public String[][] getDefaultMailIds() {
		String fromQuery = " SELECT SYSMAIL_EMAIL_ID, SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID ORDER BY SYSMAIL_CODE ";
		Object fromEmp[][] = getSqlModel().getSingleResult(fromQuery);
		String[][] mailIds = new String[fromEmp.length][2];
		for (int i = 0; i < fromEmp.length; i++) {
			mailIds[i][0] = String.valueOf(fromEmp[i][0]);
			mailIds[i][1] = String.valueOf(fromEmp[i][1]);
		}
		return mailIds;
	}

	
	private void sendMail(String[] toMailId, String[][] fromMailIds,
			String subject, String textBody, Object[][] mailBoxData){
		try {
			// HtmlEmail email=null;
			int patch = 80;
			int count = toMailId.length / patch;
			int rem = toMailId.length % patch;
			if (rem > 0) {
				count = count + 1;
			}
			int k = 0;
			if (patch > toMailId.length) {
				patch = toMailId.length;
			}
			 
			for (int i = 0; i < count; i++) {
				String[] tomailIds = null;
				if (i == count - 1) {
					if (rem > 0) {
						tomailIds = new String[rem];
					} else {
						tomailIds = new String[patch];
					}
				} else {
					tomailIds = new String[patch];
				}
				 
				for (int j = 0; j < tomailIds.length; j++) {
					tomailIds[j] = toMailId[k];
					k++;
				}
			 
				fireEmail(mailBoxData, subject, textBody, tomailIds);

			}
		} catch (Exception e) {
			 
		}
		
	}

	private void fireEmail(Object[][] mailBoxData, String subject,
			String textBody, String[] tomailIds) {
		
		SendEmail email = new SendEmail();
		
		String[][] fromEmail =getDefaultMailIds();
		
		email.sendMailToKeepInfo(tomailIds, fromEmail, subject,
				textBody, mailBoxData);  
		
	}

	public String getValue(String[] empCode) {
		String str = "";
		try {
			for (int i = 0; i < empCode.length; i++) {
				if (!empCode[i].equals("")) {
					str += String.valueOf(empCode[i]) + ",";
				}

			}// end of loop

			str = str.substring(0, str.length() - 1);
		} catch (RuntimeException e) {
			logger.error("Exception in getValue method : " + e);
		}

		return str;

	}

	public boolean saveAddEmployee(EmployeeSurveyConfiguration empSurveyConfig) {
		// TODO Auto-generated method stub

		boolean flag = false;
		try {

			Object saveObj[][] = new Object[1][2];
			saveObj[0][0] = empSurveyConfig.getSurveyCode().trim();
			saveObj[0][1] = empSurveyConfig.getEmployeeCode().trim();

			String insertQuery = " INSERT INTO HRMS_EMPSURVEY_CONFIG(SURVEY_CODE,SURVEY_EMP_CODE) "
					+ "  VALUES(?,?)";
			flag = getSqlModel().singleExecute(insertQuery, saveObj);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;

	}
}
