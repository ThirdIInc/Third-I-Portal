package org.paradyne.model.payroll;
/*
 * Date: 30/11/2010
 * Ganesh
 */
import org.paradyne.bean.payroll.LTCConfigBean;
import org.paradyne.lib.ModelBase;

public class LTCConfigModel extends ModelBase {

	public boolean saveValues(LTCConfigBean configBean) {

		boolean result = false;

		try {

			String attendanceDaysCheck = "N";
			String leaveDaysCheck = "N";
			String travelDaysCheck = "N";
			Object[][] obj = new Object[1][4];
			obj[0][0] = configBean.getCreditCode();
			if (configBean.getAttnDays().equals("true")) {
				attendanceDaysCheck = "Y";
			}
			if (configBean.getLeaveDays().equals("true")) {
				leaveDaysCheck = "Y";
			}
			if (configBean.getTrvlDays().equals("true")) {
				travelDaysCheck = "Y";
			}
			obj[0][1] = attendanceDaysCheck;
			obj[0][2] = leaveDaysCheck;
			obj[0][3] = travelDaysCheck;
			String Query1 = "DELETE FROM HRMS_LTC_CONFIG  ";
			result = getSqlModel().singleExecute(Query1);
			if (result) {
				String Query = "INSERT INTO HRMS_LTC_CONFIG ( LTC_CREDIT_HEAD, ENABLE_ATTN_DAYS, ENABLE_LEAVE_DAYS, ENABLE_TRAVEL_DAYS ) VALUES ( ?, ?, ?, ?) ";
				result = getSqlModel().singleExecute(Query, obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public void showSetting(LTCConfigBean configBean) {
		try {
			String Query = " SELECT LTC_CREDIT_HEAD,CREDIT_NAME, ENABLE_ATTN_DAYS, ENABLE_LEAVE_DAYS, ENABLE_TRAVEL_DAYS  "
					+ "   FROM  HRMS_LTC_CONFIG  "
					+ "   INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_LTC_CONFIG.LTC_CREDIT_HEAD )";
			Object[][] data = getSqlModel().getSingleResult(Query);
			if (data != null && data.length > 0) {

				configBean.setCreditCode(String.valueOf(data[0][0]));
				configBean.setCreditName(checkNull(String.valueOf(data[0][1])));
				if (String.valueOf(data[0][2]).equals("Y")) {
					configBean.setAttnDays("true");
				}
				if (String.valueOf(data[0][3]).equals("Y")) {
					configBean.setLeaveDays("true");
				}
				if (String.valueOf(data[0][4]).equals("Y")) {
					configBean.setTrvlDays("true");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

}
