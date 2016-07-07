package org.paradyne.model.vas;

import org.paradyne.lib.ModelBase;

public class VASHomeModel extends ModelBase {

	public void saveClientLogs(String serviceCode, String clientCode,
			String employeeCode) {
		String insertQuery="INSERT INTO HRSAAS_SHARED.VAS_ACCESS_LOGS(SERVICE_CODE, CLIENT_CODE, EMPLOYEE_CODE, ACCESS_DATE) VALUES(?,?,?,SYSDATE)";
		Object[][] data=new Object[1][3];
		data[0][0]=serviceCode;
		data[0][2]=clientCode;
		data[0][1]=employeeCode;
		getSqlModel().singleExecute(insertQuery,data);
	}

}
