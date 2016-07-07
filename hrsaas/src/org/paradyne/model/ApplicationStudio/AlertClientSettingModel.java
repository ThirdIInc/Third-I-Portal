/**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.bean.ApplicationStudio.AlertClientSetting;
import org.paradyne.lib.ModelBase;

/**
 * @author AA0491
 * 
 */
public class AlertClientSettingModel extends ModelBase {

	public boolean saveAlert(AlertClientSetting alertClientSettingInstance,
			String[] employeeId) {
		// TODO Auto-generated method stub
		boolean result = false;

		try {
			String empCode = "";
			if (employeeId != null && employeeId.length > 0) {
				for (int i = 0; i < employeeId.length; i++) {
					if (i == 0) {
						empCode = employeeId[i];
					} else {
						empCode += "," + employeeId[i];
					}
				}
			}
			Object[][] updateObj = new Object[1][1];
			/*updateObj[0][0] = alertClientSettingInstance.getEmailCheck()
					.equals("true") ? "Y" : "N";
			updateObj[0][1] = alertClientSettingInstance.getAlertCheck()
					.equals("true") ? "Y" : "N";*/
			updateObj[0][0] = alertClientSettingInstance.getHiddenJobCode()
					.trim();
			/*String updateQuery = " UPDATE HRMS_JOB SET JOB_MESSAGE_EMAIL =? , "
					+ " JOB_MESSAGE_ALERT=? ,JOB_ALERT_EMPID='" + empCode
					+ "'  WHERE JOB_ID=? ";*/
			
			 String updateQuery = " UPDATE HRMS_JOB SET   "
				+ " JOB_ALERT_EMPID='" + empCode
				+ "'  WHERE JOB_ID=? ";
				
			result = getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public void setData(AlertClientSetting alertClientSettingInstance,
			HashMap<String, String> jobDetails) {
		// TODO Auto-generated method stub
		try {

			ArrayList<Object> list = new ArrayList<Object>();

			/*String selectQuery = " SELECT JOB_MESSAGE_EMAIL ,JOB_MESSAGE_ALERT ,JOB_ALERT_EMPID FROM HRMS_JOB "
					+ " WHERE JOB_ID="
					+ alertClientSettingInstance.getHiddenJobCode().trim();
			 */
			
			String selectQuery = " SELECT JOB_ALERT_EMPID FROM HRMS_JOB "
				+ " WHERE JOB_ID="
				+ alertClientSettingInstance.getHiddenJobCode().trim();
			
			Object[][] dataObj = getSqlModel().getSingleResult(selectQuery);
		/*	String emailCheck = "false";
			String alertCheck = "false";
			if (dataObj != null && dataObj.length > 0) {
				emailCheck = String.valueOf(dataObj[0][0]).equals("Y") ? "true"
						: "false";
				alertCheck = String.valueOf(dataObj[0][1]).equals("Y") ? "true"
						: "false";
				alertClientSettingInstance.setEmailCheck(emailCheck);
				alertClientSettingInstance.setAlertCheck(alertCheck);
			}*/

			String empIdQuery = " SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,EMP_ID "
					+ " FROM HRMS_EMP_OFFC  WHERE EMP_ID IN("
					+ String.valueOf(dataObj[0][0]) + ")";

			Object[][] empIdObj = getSqlModel().getSingleResult(empIdQuery);

			if (empIdObj != null && empIdObj.length > 0) {
				for (int i = 0; i < empIdObj.length; i++) {
					alertClientSettingInstance.setAddFlag("true");
					AlertClientSetting innerBean = new AlertClientSetting();
					innerBean.setEmpToken(String.valueOf(empIdObj[i][0]));
					innerBean.setEmpName(String.valueOf(empIdObj[i][1]));
					innerBean.setEmployeeId(String.valueOf(empIdObj[i][2]));
					list.add(innerBean);
				}
			}

			alertClientSettingInstance.setList(list);

			if (jobDetails.size() > 0) {
				alertClientSettingInstance.setJobDuration(jobDetails
						.get("DURATION"));
				alertClientSettingInstance.setJobDayOfWeek(jobDetails
						.get("DAY_OF_WEEK"));
				alertClientSettingInstance.setJobDayOfMonth(jobDetails
						.get("DAY_OF_MONTH"));
				alertClientSettingInstance.setJobStartTime(jobDetails
						.get("START_TIME"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean addEmployee(String[] srNo, String[] empToken,
			String[] employeeId, String[] empName,
			AlertClientSetting alertClientSettingInstance) {
		boolean result = false;
		try {
			ArrayList<Object> list = new ArrayList<Object>();

			if (employeeId != null && employeeId.length > 0) {
				for (int i = 0; i < empName.length; i++) {
					AlertClientSetting innerBean = new AlertClientSetting();
					innerBean.setEmpToken(empToken[i]);
					innerBean.setEmployeeId(employeeId[i]);
					innerBean.setEmpName(empName[i]);
					list.add(innerBean);

				}
			}
			AlertClientSetting outerBean = new AlertClientSetting();
			outerBean
					.setEmpToken(alertClientSettingInstance.getEmployeeToken());
			outerBean.setEmployeeId(alertClientSettingInstance
					.getEmployeeCode());
			outerBean.setEmpName(alertClientSettingInstance.getEmployeeName());
			list.add(outerBean);
			alertClientSettingInstance.setList(list);

			if (list.size() > 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public void deleteData(String[] srNo, String[] empToken,
			String[] employeeId, String[] empName,
			AlertClientSetting alertClientSettingInstance) {
		try {
			// TODO Auto-generated method stub
			ArrayList<Object> list = new ArrayList<Object>();
			if (employeeId != null && employeeId.length > 0) {
				for (int i = 0; i < empName.length; i++) {
					AlertClientSetting innerBean = new AlertClientSetting();
					innerBean.setEmpToken(empToken[i]);
					innerBean.setEmployeeId(employeeId[i]);
					innerBean.setEmpName(empName[i]);
					list.add(innerBean);
				}

				list.remove(Integer.parseInt(alertClientSettingInstance
						.getHiddenEdit()) - 1);

			}
			alertClientSettingInstance.setList(list);
			alertClientSettingInstance.setAddFlag("true");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean setItteratorData(String[] srNo, String[] empToken,
			String[] employeeId, String[] empName,
			AlertClientSetting alertClientSettingInstance) {
		boolean result = false ;
		
		try {
			ArrayList<Object> list = new ArrayList<Object>();

			if (employeeId != null && employeeId.length > 0) {
				for (int i = 0; i < empName.length; i++) {
					result =true;
					AlertClientSetting innerBean = new AlertClientSetting();
					innerBean.setEmpToken(empToken[i]);
					innerBean.setEmployeeId(employeeId[i]);
					innerBean.setEmpName(empName[i]);
					list.add(innerBean);

				}
			}
			alertClientSettingInstance.setList(list);
			
			 
 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
