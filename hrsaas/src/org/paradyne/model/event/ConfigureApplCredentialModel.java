package org.paradyne.model.event;

import org.paradyne.bean.event.ConfigureApplCredential;
import org.paradyne.lib.ModelBase;

public class ConfigureApplCredentialModel extends ModelBase {

	public boolean saveData(ConfigureApplCredential bean) {
		// TODO Auto-generated method stub
		boolean result = false;

		try {
			
			String delQuery = "  delete from HRMS_APPL_CREDENTIAL "  
					+" where HRMS_APPL_EMPCODE="+bean.getEmpCode().trim()+" AND HRMS_APPL_CODE="+bean.getApplicationCode().trim();
			result = getSqlModel().singleExecute(delQuery);
			
			if(result)
			{
				Object insertObj[][] = new Object[1][4];
				insertObj[0][0] = bean.getEmpCode().trim();
				insertObj[0][1] = bean.getApplicationCode().trim();
				insertObj[0][2] = bean.getUserName().trim();
				insertObj[0][3] = bean.getUserPassword().trim();

				String insertQuery = " insert into HRMS_APPL_CREDENTIAL(HRMS_APPL_EMPCODE, HRMS_APPL_CODE, HRMS_APPL_USERNAME, HRMS_APPL_PASSWORD ) "
						+ " values(?,?,?,?) ";

				result = getSqlModel().singleExecute(insertQuery, insertObj);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setEmployeeInfo(ConfigureApplCredential bean) {
		String query = " SELECT EMP_ID,EMP_TOKEN ,HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME "
				+ " FROM HRMS_EMP_OFFC WHERE EMP_ID=" + bean.getUserEmpId();

		Object data[][] = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			bean.setEmpCode(checkNull(String.valueOf(data[0][0])));
			bean.setEmpToken(checkNull(String.valueOf(data[0][1])));
			bean.setEmpName(checkNull(String.valueOf(data[0][2])));

		}

	}

	public boolean updateData(ConfigureApplCredential bean) {
		// TODO Auto-generated method stub
		boolean result = false;

		try {

			Object updateObj[][] = new Object[1][4];

			updateObj[0][0] = bean.getApplicationCode().trim();
			updateObj[0][1] = bean.getUserName().trim();
			updateObj[0][2] = bean.getUserPassword().trim();
			updateObj[0][3] = bean.getEmpCode().trim();

			String updateQuery = " UPDATE HRMS_APPL_CREDENTIAL SET "
					+ " HRMS_APPL_CODE =? , HRMS_APPL_USERNAME=? , HRMS_APPL_PASSWORD=? "
					+ "  WHERE HRMS_APPL_EMPCODE=? ";

			result = getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void displayData(ConfigureApplCredential bean) {
		String query = " SELECT HRMS_APPL_EMPCODE ,HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME "
				+ " ,EMP_TOKEN ,HRMS_APPL_CODE , HRMS_APPL_USERNAME,HRMS_APPL_PASSWORD "
				+ " FROM HRMS_APPL_CREDENTIAL "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_APPL_CREDENTIAL.HRMS_APPL_EMPCODE) "
				+ " WHERE HRMS_APPL_EMPCODE=" + bean.getEmpCode();

		Object data[][] = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			bean.setEmpCode(checkNull(String.valueOf(data[0][0])));
			bean.setEmpName(checkNull(String.valueOf(data[0][1])));
			bean.setEmpToken(checkNull(String.valueOf(data[0][2])));
		/*	bean.setApplicationCode(checkNull(String.valueOf(data[0][3])));
			bean.setUserName(checkNull(String.valueOf(data[0][4])));
			bean.setUserPassword(checkNull(String.valueOf(data[0][5])));*/
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
