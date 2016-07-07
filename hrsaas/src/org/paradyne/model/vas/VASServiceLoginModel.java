package org.paradyne.model.vas;

import java.util.ArrayList;

import org.paradyne.bean.vas.VASServiceLogin;
import org.paradyne.lib.ModelBase;

public class VASServiceLoginModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(VASServiceLoginModel.class);

	public String getAccountData(VASServiceLogin vasServLog) {
		String result = "";
		String query = "SELECT ACCOUNT_CODE, ACCOUNT_SERVICE_CODE, ACCOUNT_CLIENT_CODE,"
				+ " ACCOUNT_EMP_ID, ACCOUNT_USERNAME, ACCOUNT_PASSWORD "
				+ "  FROM VAS_ACCOUNT_INFO "
				+ "	WHERE ACCOUNT_EMP_ID = "
				+ vasServLog.getUserEmpId();
		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {
			result = "accountsAdded";
			ArrayList list = new ArrayList();
			for (int i = 0; i < data.length; i++) {
				VASServiceLogin bean = new VASServiceLogin();
				bean.setServAcctCode(String.valueOf(data[i][0]));
				bean.setServCode(String.valueOf(data[i][1]));
				bean.setServClientCode(String.valueOf(data[i][2]));
				bean.setServEmpId(String.valueOf(data[i][3]));
				bean.setServUsrName(String.valueOf(data[i][4]));
				bean.setServPwd(String.valueOf(data[i][5]));
				list.add(bean);
			}

			vasServLog.setServloginList(list);

		} else {
			vasServLog.setServloginList(null);
		}

		return result;
	}

}
