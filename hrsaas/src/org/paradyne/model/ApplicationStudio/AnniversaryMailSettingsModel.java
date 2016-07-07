/**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.AnniversaryMailSettings;
import org.paradyne.lib.ModelBase;

/**
 * @author Reeba_Joseph
 * 
 */
public class AnniversaryMailSettingsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(BirthdayMailSettingsModel.class);

	/**
	 * Save settings for checked/unchecked parameters as Y/N
	 * 
	 * @param anniversaryMailSettings
	 * @return boolean
	 */
	public boolean saveSettings(AnniversaryMailSettings anniversaryMailSettings) {
		// TODO Auto-generated method stub
		logger.info("branch flag======="
				+ anniversaryMailSettings.getIndBrnFlag());
		boolean result;
		Object[][] addObj = new Object[1][14];

		if (anniversaryMailSettings.getAutoFlag().equals("true")) {
			addObj[0][0] = "Y";
		} // if Auto mails checked
		else
			addObj[0][0] = "N";

		if (anniversaryMailSettings.getIndDptFlag().equals("true")) {
			addObj[0][1] = "Y";
		} // if individual dept checked
		else
			addObj[0][1] = "N";

		if (anniversaryMailSettings.getAllDptFlag().equals("true")) {
			addObj[0][2] = "Y";
		} // if all dpts checked
		else
			addObj[0][2] = "N";

		if (anniversaryMailSettings.getIndBrnFlag().equals("true")) {
			addObj[0][3] = "Y";
		} // if individual brnch checked
		else
			addObj[0][3] = "N";

		if (anniversaryMailSettings.getAllBrnFlag().equals("true")) {
			addObj[0][4] = "Y";
		} // if all branches checked
		else
			addObj[0][4] = "N";

		if (anniversaryMailSettings.getIndDesgFlag().equals("true")) {
			addObj[0][5] = "Y";
		} // if individual desg checked
		else
			addObj[0][5] = "N";

		if (anniversaryMailSettings.getAllDesgFlag().equals("true")) {
			addObj[0][6] = "Y";
		} // if all designations checked
		else
			addObj[0][6] = "N";

		if (anniversaryMailSettings.getIndDivFlag().equals("true")) {
			addObj[0][7] = "Y";
		} // if individual division checked
		else
			addObj[0][7] = "N";

		if (anniversaryMailSettings.getAllDivFlag().equals("true")) {
			addObj[0][8] = "Y";
		} // if all divisions checked
		else
			addObj[0][8] = "N";

		addObj[0][9] = anniversaryMailSettings.getTempCode();
		
		
		addObj[0][10] = checkNull(anniversaryMailSettings.getGroupMailId());
		
	 
		if (anniversaryMailSettings.getAllEmpFlag().equals("true")) {
			addObj[0][11] = "Y";
		} 
		else{
			addObj[0][11] = "N";
		}
		
		
		if (anniversaryMailSettings.getGroupId().equals("true")) {
			addObj[0][12] = "Y";
		} 
		else{
			addObj[0][12] = "N";
		}
		
		addObj[0][13] = anniversaryMailSettings.getMailSentOnTiming().trim();
		// delete and then insert
		String delQuery = " DELETE FROM HRMS_ANNIVERSARYMAIL_SETTINGS ";
		result = getSqlModel().singleExecute(delQuery);
		if (result) {// if result
			String addQuery = " INSERT INTO HRMS_ANNIVERSARYMAIL_SETTINGS (AUTO_SEND, INDIV_DEPT, ALL_DEPT, INDIV_BRANCH, "
					+ " ALL_BRANCH, INDIV_DESG, ALL_DESG, INDIV_DIV, ALL_DIV,TEMPLATE_ID,GROUP_MAILID, INDIV_EMP,GROUP_ID,MAIL_SENT_ON) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			result = getSqlModel().singleExecute(addQuery, addObj);
		}// end of if result

		return result;
	}// end of saveSettings method

	/**
	 * Setting values after save and on load
	 * 
	 * @param anniversaryMailSettings
	 */
	public void displaySettings(AnniversaryMailSettings anniversaryMailSettings) {
		// TODO Auto-generated method stub
		String setQuery = " SELECT AUTO_SEND, INDIV_DEPT, ALL_DEPT, INDIV_BRANCH, ALL_BRANCH, INDIV_DESG, ALL_DESG, INDIV_DIV, ALL_DIV,NVL(TEMPLATE_NAME,' '),NVL(HRMS_ANNIVERSARYMAIL_SETTINGS.TEMPLATE_ID,'') "
				+ " ,GROUP_MAILID, INDIV_EMP, GROUP_ID,NVL(MAIL_SENT_ON,'') FROM HRMS_ANNIVERSARYMAIL_SETTINGS "
				+ " LEFT JOIN HRMS_ANNIVERSARY_TEMPLATE ON(HRMS_ANNIVERSARY_TEMPLATE.TEMPLATE_ID = HRMS_ANNIVERSARYMAIL_SETTINGS.TEMPLATE_ID) ";
		Object[][] setData = getSqlModel().getSingleResult(setQuery);

		if (setData != null && setData.length > 0) {// if rows returned from
			// query
			if (String.valueOf(setData[0][0]).equals("Y")) {
				anniversaryMailSettings.setAutoFlag("true");
			} // if auto send checked, auto flag true
			else
				anniversaryMailSettings.setAutoFlag("false");

			if (String.valueOf(setData[0][1]).equals("Y")) {
				anniversaryMailSettings.setIndDptFlag("true");
			} // if indiv dept checked, indDpt flag true
			else
				anniversaryMailSettings.setIndDptFlag("false");

			if (String.valueOf(setData[0][2]).equals("Y")) {
				anniversaryMailSettings.setAllDptFlag("true");
			}// if all dept checked, allDpt flag true
			else
				anniversaryMailSettings.setAllDptFlag("false");

			if (String.valueOf(setData[0][3]).equals("Y")) {
				anniversaryMailSettings.setIndBrnFlag("true");
			} // if indiv brnch checked, indBrn flag true
			else
				anniversaryMailSettings.setIndBrnFlag("false");

			if (String.valueOf(setData[0][4]).equals("Y")) {
				anniversaryMailSettings.setAllBrnFlag("true");
			}// if all branches checked, allBrn flag true
			else
				anniversaryMailSettings.setAllBrnFlag("false");

			if (String.valueOf(setData[0][5]).equals("Y")) {
				anniversaryMailSettings.setIndDesgFlag("true");
			} // if indiv desg checked, indDesg flag true
			else
				anniversaryMailSettings.setIndDesgFlag("false");

			if (String.valueOf(setData[0][6]).equals("Y")) {
				anniversaryMailSettings.setAllDesgFlag("true");
			} // if all desg checked, allDesg flag true
			else
				anniversaryMailSettings.setAllDesgFlag("false");

			if (String.valueOf(setData[0][7]).equals("Y")) {
				anniversaryMailSettings.setIndDivFlag("true");
			} // if indiv div checked, indDiv flag true
			else 
				anniversaryMailSettings.setIndDivFlag("false");

			if (String.valueOf(setData[0][8]).equals("Y")) {
				anniversaryMailSettings.setAllDivFlag("true");
			} else {
				anniversaryMailSettings.setAllDivFlag("false");
			}
				
			anniversaryMailSettings.setTempName(String.valueOf(setData[0][9]));
			anniversaryMailSettings.setTempCode(String.valueOf(setData[0][10]));
			
			anniversaryMailSettings.setGroupMailId(checkNull(String.valueOf(setData[0][11])));
			
			anniversaryMailSettings.setAllEmpFlag(String.valueOf(setData[0][12]).equals("Y")?"true":"false");
			
			anniversaryMailSettings.setGroupId(String.valueOf(setData[0][13]).equals("Y")?"true":"false");
			
			anniversaryMailSettings.setMailSentOnTiming(checkNull(String.valueOf(setData[0][14])));

		} else {// if no rows returned from query
			anniversaryMailSettings.setAutoFlag("false");
			anniversaryMailSettings.setIndDptFlag("false");
			anniversaryMailSettings.setAllDptFlag("false");
			anniversaryMailSettings.setIndBrnFlag("false");
			anniversaryMailSettings.setAllBrnFlag("false");
			anniversaryMailSettings.setIndDesgFlag("false");
			anniversaryMailSettings.setAllDesgFlag("false");
			anniversaryMailSettings.setIndDivFlag("false");
			anniversaryMailSettings.setAllDivFlag("false");
			anniversaryMailSettings.setMailSentOnTiming("");
		}// end of else
	}// end of displaySettings method
	
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}
}// end of class
