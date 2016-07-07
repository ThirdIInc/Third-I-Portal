package org.paradyne.model.ApplicationStudio;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.ApplicationStudio.BirthdayMailSettings;
import org.paradyne.lib.ModelBase;

/**
 * @author Reeba
 * @Modified by ANANTHA LAKSHMI
 */
public class BirthdayMailSettingsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(BirthdayMailSettingsModel.class);

	public boolean saveSettings(BirthdayMailSettings birthdayMailSettings,
			HttpServletRequest request) {
		boolean result = false;
		try {
			Object[][] addObj = new Object[1][21];
			if (birthdayMailSettings.getAutoFlag().equals("true")) {
				addObj[0][0] = "Y";
			} else
				addObj[0][0] = "N";
			if (birthdayMailSettings.getIndDptFlag().equals("true")) {
				addObj[0][1] = "Y";
			} else
				addObj[0][1] = "N";
			if (birthdayMailSettings.getAllDptFlag().equals("true")) {
				addObj[0][2] = "Y";
			} else
				addObj[0][2] = "N";
			if (birthdayMailSettings.getIndBrnFlag().equals("true")) {
				addObj[0][3] = "Y";
			} else
				addObj[0][3] = "N";
			if (birthdayMailSettings.getAllBrnFlag().equals("true")) {
				addObj[0][4] = "Y";
			} else
				addObj[0][4] = "N";
			if (birthdayMailSettings.getIndDesgFlag().equals("true")) {
				addObj[0][5] = "Y";
			} else
				addObj[0][5] = "N";
			if (birthdayMailSettings.getAllDesgFlag().equals("true")) {
				addObj[0][6] = "Y";
			} else
				addObj[0][6] = "N";
			if (birthdayMailSettings.getIndDivFlag().equals("true")) {
				addObj[0][7] = "Y";
			} else {
				addObj[0][7] = "N";
			}
			if (birthdayMailSettings.getAllDivFlag().equals("true")) {
				addObj[0][8] = "Y";
			} else {
				addObj[0][8] = "N";
			}
			addObj[0][9] = birthdayMailSettings.getTempCode();
			addObj[0][10] = "S";
			if (!birthdayMailSettings.getHiddRandomCode().equals("")) {
				String str = birthdayMailSettings.getHiddRandomCode();
				addObj[0][9] = str;
				addObj[0][10] = "M";
			} else {
				addObj[0][9] = birthdayMailSettings.getTempCode();
				addObj[0][10] = "S";
			}
			if (birthdayMailSettings.getAllEmpFlag().equals("true")) {
				addObj[0][11] = "Y";
			} else {
				addObj[0][11] = "N";
			}
			if (birthdayMailSettings.getDesignationFlag().equals("true")) {
				addObj[0][12] = "Y";
			} else {
				addObj[0][12] = "N";
			}
			if (birthdayMailSettings.getDepartmentFlag().equals("true")) {
				addObj[0][13] = "Y";
			} else {
				addObj[0][13] = "N";
			}
			if (birthdayMailSettings.getEmailIDFlag().equals("true")) {
				addObj[0][14] = "Y";
			} else {
				addObj[0][14] = "N";
			}
			if (birthdayMailSettings.getPhoneNumberFlag().equals("true")) {
				addObj[0][15] = "Y";
			} else {
				addObj[0][15] = "N";
			}
			String[] empCode = request.getParameterValues("empCodeIt");
			String employeeCode = "";
			if (empCode != null) {
				for (int cnt = 0; cnt < empCode.length; cnt++) {
					if (cnt == 0) {
						employeeCode = String.valueOf(empCode[cnt]);
					} else {
						employeeCode += "," + String.valueOf(empCode[cnt]);
					}
				}
				addObj[0][16] = employeeCode;
			} else {
				addObj[0][16] = "";
			}
			if (birthdayMailSettings.getGroupId().equals("true")) {
				addObj[0][17] = "Y";
			} else {
				addObj[0][17] = "N";
			}
			if (!birthdayMailSettings.getGroupMailId().equals("")) {
				addObj[0][18] = checkNull(String.valueOf(birthdayMailSettings
						.getGroupMailId()));
			} else {
				addObj[0][18] = "";
			}
			addObj[0][19] = birthdayMailSettings.getMailSentOnTiming().trim();
			
			if(birthdayMailSettings.getTitleFlag().equals("true")) {
				addObj[0][20]= "Y";				
			} else {
				addObj[0][20] ="N";
			}
			
			String delQuery = " DELETE FROM HRMS_BIRTHDAYMAIL_SETTINGS ";
			result = getSqlModel().singleExecute(delQuery);
			if (result) {
				String addQuery = " INSERT INTO HRMS_BIRTHDAYMAIL_SETTINGS (AUTO_SEND, INDIV_DEPT, ALL_DEPT, INDIV_BRANCH, "
						+ " ALL_BRANCH, INDIV_DESG, ALL_DESG, INDIV_DIV, ALL_DIV,TEMPLATE_ID,TEMP_FLAG,INDIV_EMP,INCLUDE_DESG,INCLUDE_DEPT,"
						+ " INCLUDE_EMAIL,INCLUDE_PHONE,EXCEPTION_EMP,GROUP_ID,GROUP_MAILID, MAIL_SENT_ON,INCLUDE_TITLE) " 
						+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				result = getSqlModel().singleExecute(addQuery, addObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void displaySettings(BirthdayMailSettings birthdayMailSettings) {
		try {
			ArrayList randItt = new ArrayList();
			String query = "SELECT  TEMPLATE_ID,TEMPLATE_NAME FROM HRMS_BIRTHDAYTEMPLATE_HDR WHERE TEMPLATE_BODY IS NOT NULL ORDER BY TEMPLATE_ID";
			Object[][] tempData = getSqlModel().getSingleResult(query);
			String setQuery = " SELECT AUTO_SEND, INDIV_DEPT, ALL_DEPT, INDIV_BRANCH, ALL_BRANCH, INDIV_DESG, ALL_DESG, INDIV_DIV, ALL_DIV,NVL(TEMPLATE_NAME,' '),NVL(HRMS_BIRTHDAYMAIL_SETTINGS.TEMPLATE_ID,''),NVL(TEMP_FLAG,'S'),NVL(INDIV_EMP,'N') "
					+ " ,NVL(INCLUDE_DESG,'N'),NVL(INCLUDE_DEPT,'N'),NVL(INCLUDE_EMAIL,'N'),NVL(INCLUDE_PHONE,'N'),EXCEPTION_EMP, GROUP_MAILID,NVL(GROUP_ID,'N'), NVL(MAIL_SENT_ON,''), NVL(INCLUDE_TITLE,'N') FROM HRMS_BIRTHDAYMAIL_SETTINGS "
					+ " LEFT JOIN HRMS_BIRTHDAYTEMPLATE_HDR ON(HRMS_BIRTHDAYTEMPLATE_HDR.TEMPLATE_ID = HRMS_BIRTHDAYMAIL_SETTINGS.TEMPLATE_ID) ";
			String setQuery_1 = " SELECT AUTO_SEND, INDIV_DEPT, ALL_DEPT, INDIV_BRANCH, ALL_BRANCH, INDIV_DESG, ALL_DESG, INDIV_DIV, ALL_DIV,'',NVL(HRMS_BIRTHDAYMAIL_SETTINGS.TEMPLATE_ID,''),NVL(TEMP_FLAG,'S') "
					+ " FROM HRMS_BIRTHDAYMAIL_SETTINGS " + "  ";
			Object[][] textData = getSqlModel().getSingleResult(setQuery_1);
			if (textData != null && String.valueOf(textData[0][11]).equals("M")) {
				setQuery = "SELECT AUTO_SEND, INDIV_DEPT, ALL_DEPT, INDIV_BRANCH, ALL_BRANCH, INDIV_DESG, ALL_DESG, " 
						+ " INDIV_DIV, ALL_DIV,'',NVL(HRMS_BIRTHDAYMAIL_SETTINGS.TEMPLATE_ID,''),NVL(TEMP_FLAG,'S'), " 
						+ " NVL(INDIV_EMP,'N'), NVL(INCLUDE_DESG,'N'),NVL(INCLUDE_DEPT,'N'),NVL(INCLUDE_EMAIL,'N'),NVL(INCLUDE_PHONE,'N'),"
						+ " EXCEPTION_EMP, GROUP_MAILID,NVL(GROUP_ID,'N'), NVL(MAIL_SENT_ON,''),NVL(INCLUDE_TITLE,'N') "
						+ " FROM HRMS_BIRTHDAYMAIL_SETTINGS ";
			}
			Object[][] setData = getSqlModel().getSingleResult(setQuery);
			if (setData != null && setData.length > 0) {
				if (String.valueOf(setData[0][0]).equals("Y")) {
					birthdayMailSettings.setAutoFlag("true");
				} else {
					birthdayMailSettings.setAutoFlag("false");
				}

				if (String.valueOf(setData[0][1]).equals("Y")) {
					birthdayMailSettings.setIndDptFlag("true");
				} else {
					birthdayMailSettings.setIndDptFlag("false");
				}

				if (String.valueOf(setData[0][2]).equals("Y")) {
					birthdayMailSettings.setAllDptFlag("true");
				} else {
					birthdayMailSettings.setAllDptFlag("false");
				}

				if (String.valueOf(setData[0][3]).equals("Y")) {
					birthdayMailSettings.setIndBrnFlag("true");
				} else {
					birthdayMailSettings.setIndBrnFlag("false");
				}

				if (String.valueOf(setData[0][4]).equals("Y")) {
					birthdayMailSettings.setAllBrnFlag("true");
				} else {
					birthdayMailSettings.setAllBrnFlag("false");
				}

				if (String.valueOf(setData[0][5]).equals("Y")) {
					birthdayMailSettings.setIndDesgFlag("true");
				} else {
					birthdayMailSettings.setIndDesgFlag("false");
				}

				if (String.valueOf(setData[0][6]).equals("Y")) {
					birthdayMailSettings.setAllDesgFlag("true");
				} else {
					birthdayMailSettings.setAllDesgFlag("false");
				}

				if (String.valueOf(setData[0][7]).equals("Y")) {
					birthdayMailSettings.setIndDivFlag("true");
				} else {
					birthdayMailSettings.setIndDivFlag("false");
				}

				if (String.valueOf(setData[0][8]).equals("Y")) {
					birthdayMailSettings.setAllDivFlag("true");
				} else {
					birthdayMailSettings.setAllDivFlag("false");
				}

				if (String.valueOf(setData[0][12]).equals("Y")) {
					birthdayMailSettings.setAllEmpFlag("true");
				} else {
					birthdayMailSettings.setAllEmpFlag("false");
				}

				if (String.valueOf(setData[0][13]).equals("Y")) {
					birthdayMailSettings.setDesignationFlag("true");
				} else {
					birthdayMailSettings.setDesignationFlag("false");
				}

				if (String.valueOf(setData[0][14]).equals("Y")) {
					birthdayMailSettings.setDepartmentFlag("true");
				} else {
					birthdayMailSettings.setDepartmentFlag("false");
				}

				if (String.valueOf(setData[0][15]).equals("Y")) {
					birthdayMailSettings.setEmailIDFlag("true");
				} else {
					birthdayMailSettings.setEmailIDFlag("false");
				}

				if (String.valueOf(setData[0][16]).equals("Y")) {
					birthdayMailSettings.setPhoneNumberFlag("true");
				} else {
					birthdayMailSettings.setPhoneNumberFlag("false");
				}
				// Updated By Anantha Lakshmi
				if (setData[0][17] != null) {
					ArrayList<Object> list = new ArrayList<Object>();
					Object[][] empData = null;
					String strQuery = "SELECT EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME, EMP_ID FROM HRMS_EMP_OFFC WHERE  EMP_ID IN("
							+ String.valueOf(setData[0][17]) + ")";
					empData = getSqlModel().getSingleResult(strQuery);
					if (empData != null) {
						for (int i = 0; i < empData.length; i++) {
							BirthdayMailSettings bean = new BirthdayMailSettings();
							bean.setEmpTokenNoIt(String.valueOf(empData[i][0]));
							bean.setEmpNameIt(String.valueOf(empData[i][1]));
							bean.setEmpCodeIt(String.valueOf(empData[i][2]));
							list.add(bean);
						}
						birthdayMailSettings.setBirthdayMailList(list);
					}
				}

				birthdayMailSettings.setGroupMailId(checkNull(String
						.valueOf(setData[0][18])));

				if (String.valueOf(setData[0][19]).equals("Y")) {
					birthdayMailSettings.setGroupId("true");
				} else {
					birthdayMailSettings.setGroupId("false");
				}

				if (String.valueOf(setData[0][11]).equals("S")) {
					birthdayMailSettings.setTempCode(String
							.valueOf(setData[0][10]));
					birthdayMailSettings.setTempName(String
							.valueOf(setData[0][9]));
					birthdayMailSettings.setTempCheckBox("true");
				} else {
					birthdayMailSettings.setHiddRandomCode(String
							.valueOf(setData[0][10]));
					birthdayMailSettings.setRandCheckBox("true");
				}

				birthdayMailSettings.setMailSentOnTiming(checkNull(String.valueOf(setData[0][20])));
				

				if (String.valueOf(setData[0][21]).equals("Y")) {
					birthdayMailSettings.setTitleFlag("true");
				} else {
					birthdayMailSettings.setTitleFlag("false");
				}

			} else {
				birthdayMailSettings.setAutoFlag("false");
				birthdayMailSettings.setIndDptFlag("false");
				birthdayMailSettings.setAllDptFlag("false");
				birthdayMailSettings.setIndBrnFlag("false");
				birthdayMailSettings.setAllBrnFlag("false");
				birthdayMailSettings.setIndDesgFlag("false");
				birthdayMailSettings.setAllDesgFlag("false");
				birthdayMailSettings.setIndDivFlag("false");
				birthdayMailSettings.setAllDivFlag("false");
				birthdayMailSettings.setAllEmpFlag("false");
				birthdayMailSettings.setDesignationFlag("false");
				birthdayMailSettings.setDepartmentFlag("false");
				birthdayMailSettings.setEmailIDFlag("false");
				birthdayMailSettings.setPhoneNumberFlag("false");
				birthdayMailSettings.setTitleFlag("false");
				birthdayMailSettings.setEmpName("");
				birthdayMailSettings.setMailSentOnTiming("");
			}
			if (tempData != null && tempData.length > 0) {
				for (int i = 0; i < tempData.length; i++) {
					BirthdayMailSettings bean = new BirthdayMailSettings();
					bean.setIttTempCode(String.valueOf(tempData[i][0]));
					bean.setIttRandomTemp(String.valueOf(tempData[i][1]));
					bean.setIttCheckBox("false");
					if (!birthdayMailSettings.getHiddRandomCode().equals("")) {
						String str = birthdayMailSettings.getHiddRandomCode();
						String data[] = str.split(",");
						for (int j = 0; j < data.length; j++) {
							if (String.valueOf(tempData[i][0]).equals(
									String.valueOf(data[j]))) {
								bean.setIttCheckBox("true");
							}
						}
					} else {
						bean.setIttCheckBox("false");
					}
					randItt.add(bean);
				}
				birthdayMailSettings.setRandItt(randItt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Updated Anantha Lakshmi
	public void addEmp(BirthdayMailSettings birthdayMailSettings,
			String[] empCode, String[] empToken, String[] empName) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			if (empCode != null && empCode.length > 0) {
				for (int cnt = 0; cnt < empCode.length; cnt++) {
					BirthdayMailSettings bean = new BirthdayMailSettings();
					bean.setEmpCodeIt(empCode[cnt]);
					bean.setEmpNameIt(empName[cnt]);
					bean.setEmpTokenNoIt(empToken[cnt]);
					list.add(bean);
				}
			}
			String[] name = birthdayMailSettings.getEmpName().split(",");
			String StrEmpName = "";
			for (int i = 0; i < name.length; i++) {
				if (!name[i].equals(" ")) {
					StrEmpName = name[i];
				}
			}
			BirthdayMailSettings bean = new BirthdayMailSettings();
			bean.setEmpCodeIt(birthdayMailSettings.getEmpCode());
			bean.setEmpNameIt(StrEmpName);
			bean.setEmpTokenNoIt(birthdayMailSettings.getEmpTokenNo());
			list.add(bean);
			birthdayMailSettings.setBirthdayMailList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteEmp(BirthdayMailSettings birthdayMailSettings,
			String[] empCode, String[] empToken, String[] empName, String paraID) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			if (empCode != null && empCode.length > 0) {
				for (int cnt = 0; cnt < empCode.length; cnt++) {
					BirthdayMailSettings bean = new BirthdayMailSettings();
					if (!String.valueOf(paraID).equals(String.valueOf(cnt + 1))) {
						bean.setEmpCodeIt(empCode[cnt]);
						bean.setEmpNameIt(empName[cnt]);
						bean.setEmpTokenNoIt(empToken[cnt]);
						list.add(bean);
					}
				}
			}
			birthdayMailSettings.setBirthdayMailList(list);
			birthdayMailSettings.setTableLength(String.valueOf(list.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
}
