package org.paradyne.model.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.MyContacts;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class MyContactsModel extends ModelBase {

	public boolean addMyContacts(MyContacts bean, String note) {
		boolean result = false;
		try {
			Object[][] saveObj = new Object[1][5];
			saveObj[0][0] = bean.getDeptName().trim();
			saveObj[0][1] = bean.getContactName().trim();
			saveObj[0][2] = bean.getContactNo().trim();
			saveObj[0][3] = bean.getEmailID().trim();
			saveObj[0][4] = bean.getContactDivCode().trim(); 

			String query = " INSERT INTO HRMS_MYCONTACTS (HRMS_MYCONTACTS.CONTACT_ID, HRMS_MYCONTACTS.MYCONTACT_DEPT_NAME,"
							+ " HRMS_MYCONTACTS.MYCONTACT_CONTACT_NAME, HRMS_MYCONTACTS.MYCONTACT_CONTACT_NUMBER, "
							+ " HRMS_MYCONTACTS.MYCONTACT_CONTACT_EMAIL,MYCONTACT_DIVISION) "
							+ " VALUES((SELECT NVL(MAX(CONTACT_ID),0)+1 FROM HRMS_MYCONTACTS),?,?,?,?,?)";
			result = getSqlModel().singleExecute(query, saveObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Object[][] setData(MyContacts bean) {
		Object[][] dataObj = null;
		Object [][] finalDataObj=null;
		String getName="";
		String code="";
		try {
			String query = "SELECT HRMS_MYCONTACTS.CONTACT_ID, HRMS_MYCONTACTS.MYCONTACT_DEPT_NAME, HRMS_MYCONTACTS.MYCONTACT_CONTACT_NAME,  "
					+ " HRMS_MYCONTACTS.MYCONTACT_CONTACT_NUMBER, HRMS_MYCONTACTS.MYCONTACT_CONTACT_EMAIL,MYCONTACT_DIVISION"
					+ " FROM HRMS_MYCONTACTS  ORDER BY HRMS_MYCONTACTS.CONTACT_ID  DESC";
			dataObj = getSqlModel().getSingleResult(query);
			if(dataObj!= null &&  dataObj.length >0){
				finalDataObj= new Object [dataObj.length][7];
				for(int i=0;i<dataObj.length ;i++){					
					finalDataObj[i][0]=dataObj[i][0];
					finalDataObj[i][1]=dataObj[i][1];
					finalDataObj[i][2]=dataObj[i][2];
					finalDataObj[i][3]=dataObj[i][3];
					finalDataObj[i][4]=dataObj[i][4];
					finalDataObj[i][5]= dataObj[i][5];
					if(!(dataObj[i][5]==null || String.valueOf(dataObj[i][5]).equals("") || String.valueOf(dataObj[i][5]).equals("null"))){
						String divQuery="SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID IN("+String.valueOf(dataObj[i][5])+")";
						Object [][]obj= getSqlModel().getSingleResult(divQuery);
						code= String.valueOf(dataObj[i][5]);
						getName=Utility.getNameByKey(obj, code);						
						finalDataObj[i][6]=getName;
					}
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalDataObj;
	}

	public boolean updateMyContactsData(MyContacts bean, String contactId) {
		boolean result = false;
		try {
			Object updateObj[][] = new Object[1][6];
			updateObj[0][0] = bean.getDeptName().trim();
			updateObj[0][1] = bean.getContactName().trim();
			updateObj[0][2] = bean.getContactNo().trim();
			updateObj[0][3] = bean.getEmailID().trim();
			updateObj[0][4] = bean.getContactDivCode().trim();
			updateObj[0][5] = contactId;

			String updateQUery = "  UPDATE HRMS_MYCONTACTS SET  HRMS_MYCONTACTS.MYCONTACT_DEPT_NAME =?, "
					+ "  HRMS_MYCONTACTS.MYCONTACT_CONTACT_NAME=?, HRMS_MYCONTACTS.MYCONTACT_CONTACT_NUMBER =?, "
					+ " HRMS_MYCONTACTS.MYCONTACT_CONTACT_EMAIL =?, MYCONTACT_DIVISION =?"
				    + " WHERE HRMS_MYCONTACTS.CONTACT_ID=? ";
			result = getSqlModel().singleExecute(updateQUery, updateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteRecord(MyContacts bean) {
		boolean result = false;
		try {
			String delQuery = "  DELETE FROM  HRMS_MYCONTACTS  WHERE CONTACT_ID =" + bean.getHiddenEditCode().trim();
			result = getSqlModel().singleExecute(delQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
