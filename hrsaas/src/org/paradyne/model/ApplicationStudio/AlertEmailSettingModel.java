package org.paradyne.model.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.AlertEmailSetting;
import org.paradyne.lib.ModelBase;

public class AlertEmailSettingModel extends ModelBase {

	public boolean save(AlertEmailSetting alertEmail) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			Object[][] addParam = new Object[1][6];
			addParam[0][0] = alertEmail.getModCode();
			addParam[0][1] = alertEmail.getAlertType();
			addParam[0][2] = alertEmail.getNoOfDays();
			addParam[0][3] = getValue(alertEmail.getApplicantToapprover());
			addParam[0][4] = getValue(alertEmail.getApproverToapprover());
			addParam[0][5] = getValue(alertEmail.getApproverToapplicant());

			for (int i = 0; i < addParam.length; i++) {
				for (int j = 0; j < addParam[0].length; j++) {
					System.out.println("vishu" + addParam[i][j]);
				}

			}
			String query = " INSERT INTO HRMS_ALERTEMAIL_SETTINGS(MODULENAME, TYPE, NOOFDAYS, APPTOAPR, APRTOAPR, APRTOAPP)"
					+ " VALUES(?,?,?,?,?,? )";
			result = getSqlModel().singleExecute(query, addParam);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public String getValue(String value) {
		String str = "N";
		System.out.println("value-------" + value);
		if (value.equals("true")) {
			str = "Y";
		}
		return str;
	}
}
