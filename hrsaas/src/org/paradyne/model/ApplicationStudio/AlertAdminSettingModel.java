/**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.AlertAdminSetting;
import org.paradyne.lib.ModelBase;

/**
 * @author AA0491
 * 
 */
public class AlertAdminSettingModel extends ModelBase {

	public boolean saveAlert(AlertAdminSetting alertAdminSettingInstance) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			Object[][] saveObj = new Object[1][13];
			saveObj[0][0] = alertAdminSettingInstance.getSubject().trim();
			saveObj[0][1] = alertAdminSettingInstance.getQuery().trim();
			saveObj[0][2] = alertAdminSettingInstance.getModuleName().trim();
			saveObj[0][3] = alertAdminSettingInstance.getQuerytype().trim();
			saveObj[0][4] = alertAdminSettingInstance.getAlerttype().trim();

			saveObj[0][5] = alertAdminSettingInstance.getEmailCheck().equals(
					"true") ? "Y" : "N";
			saveObj[0][6] = alertAdminSettingInstance.getAlertCheck().equals(
					"true") ? "Y" : "N";
			
			saveObj[0][7] = alertAdminSettingInstance.getTemplateCode();
			
			saveObj[0][8] = alertAdminSettingInstance.getMethodName().trim();
			
			saveObj[0][9] = alertAdminSettingInstance.getLink().trim();
			
			saveObj[0][10] = alertAdminSettingInstance.getLinkParameter().trim();
			
			saveObj[0][11] = alertAdminSettingInstance.getLinkParameterValue().trim();
			
			saveObj[0][12] = alertAdminSettingInstance.getNoOflinkParameter().trim();

			String inserQuery = " INSERT INTO HRMS_JOB(JOB_ID, JOB_DATE, JOB_SUBJECT, JOB_QUERY, JOB_MODULE_NAME, JOB_QUERY_TYPE, JOB_MESSAGE_TYPE,JOB_MESSAGE_EMAIL, JOB_MESSAGE_ALERT ,JOB_TEMPLATE_CODE,JOB_FUNCTION_NAME,JOB_EMAIL_LINK, JOB_LINK_PARAMS, JOB_LINK_PARAMVALUE, JOB_LINK_NO_PARAMS) "
					+ " VALUES((SELECT NVL(MAX(JOB_ID),0) FROM HRMS_JOB)+1,SYSDATE,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			result = getSqlModel().singleExecute(inserQuery, saveObj);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean updateAlert(AlertAdminSetting alertAdminSettingInstance) {
		// TODO Auto-generated method stub
		boolean result = false;

		try {

			Object[][] updateObj = new Object[1][14];

			updateObj[0][0] = alertAdminSettingInstance.getSubject().trim();
			updateObj[0][1] = alertAdminSettingInstance.getQuery().trim();
			updateObj[0][2] = alertAdminSettingInstance.getModuleName().trim();
			updateObj[0][3] = alertAdminSettingInstance.getQuerytype().trim();
			updateObj[0][4] = alertAdminSettingInstance.getAlerttype().trim();
			
			updateObj[0][5] = alertAdminSettingInstance.getEmailCheck().equals(
			"true") ? "Y" : "N";
			updateObj[0][6] = alertAdminSettingInstance.getAlertCheck().equals(
			"true") ? "Y" : "N";
	
			
			updateObj[0][7] = alertAdminSettingInstance.getTemplateCode();
			
			updateObj[0][8] = alertAdminSettingInstance.getMethodName().trim();
			
			updateObj[0][9] = alertAdminSettingInstance.getLink().trim();
			updateObj[0][10] = alertAdminSettingInstance.getLinkParameter().trim();
			updateObj[0][11] = alertAdminSettingInstance.getLinkParameterValue().trim();
			updateObj[0][12] = alertAdminSettingInstance.getNoOflinkParameter().trim();
			
			
			updateObj[0][13] = alertAdminSettingInstance.getJobCode().trim();

			String updateQuery = " UPDATE HRMS_JOB SET JOB_DATE=SYSDATE ,JOB_SUBJECT=?, JOB_QUERY=?,JOB_MODULE_NAME=?  "
					+ " , JOB_QUERY_TYPE=? , JOB_MESSAGE_TYPE=? "
					+ " , JOB_MESSAGE_EMAIL=? ,JOB_MESSAGE_ALERT=? , JOB_TEMPLATE_CODE=? ,JOB_FUNCTION_NAME=? , JOB_EMAIL_LINK=? , JOB_LINK_PARAMS=? , JOB_LINK_PARAMVALUE =? , JOB_LINK_NO_PARAMS =? WHERE JOB_ID=? ";

			result = getSqlModel().singleExecute(updateQuery, updateObj);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public void setData(AlertAdminSetting alertAdminSettingInstance) {
		try {
			// TODO Auto-generated method stub
			String selectQuery = " SELECT JOB_QUERY ,JOB_MESSAGE_EMAIL ,JOB_MESSAGE_ALERT ,JOB_FUNCTION_NAME,JOB_EMAIL_LINK, JOB_LINK_PARAMS, JOB_LINK_PARAMVALUE, JOB_LINK_NO_PARAMS FROM HRMS_JOB WHERE JOB_ID="
					+ alertAdminSettingInstance.getJobCode().trim();

			Object[][] dataObj = getSqlModel().getSingleResult(selectQuery);
			if (dataObj != null && dataObj.length > 0) {
				alertAdminSettingInstance.setQuery(checkNull(String.valueOf(dataObj[0][0])));
			}

			String emailCheck = "false";
			String alertCheck = "false";
			if (dataObj != null && dataObj.length > 0) {
				emailCheck = String.valueOf(dataObj[0][1]).equals("Y") ? "true"
						: "false";
				alertCheck = String.valueOf(dataObj[0][2]).equals("Y") ? "true"
						: "false";
				alertAdminSettingInstance.setEmailCheck(emailCheck);
				alertAdminSettingInstance.setAlertCheck(alertCheck);
				alertAdminSettingInstance.setMethodName(checkNull(String.valueOf(dataObj[0][3])));
				
				alertAdminSettingInstance.setLink(checkNull(String.valueOf(dataObj[0][4])));
				alertAdminSettingInstance.setLinkParameter(checkNull(String.valueOf(dataObj[0][5])));
				alertAdminSettingInstance.setLinkParameterValue(checkNull(String.valueOf(dataObj[0][6])));
				alertAdminSettingInstance.setNoOflinkParameter(checkNull(String.valueOf(dataObj[0][7])));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

	private String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean delete(AlertAdminSetting alertAdminSettingInstance) {
		boolean result = false;

		try {
			String query = " DELETE FROM HRMS_JOB WHERE JOB_ID="
					+ alertAdminSettingInstance.getJobCode();
			result = getSqlModel().singleExecute(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
