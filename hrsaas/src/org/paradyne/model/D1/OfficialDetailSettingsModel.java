package org.paradyne.model.D1;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.DepartmentNoMasterBean;
import org.paradyne.bean.D1.OfficialDetailSettingsBean;
import org.paradyne.bean.settings.SuperSettings;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.struts.action.common.LoginAction;

public class OfficialDetailSettingsModel extends ModelBase
{

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(LoginAction.class);
	
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}//end of if
		else {
			return result;
		}//end of else
	}
	
	public void hasData(OfficialDetailSettingsBean dptMaster, HttpServletRequest request) {
		Object[][] repData = getSqlModel().getSingleResult(getQuery(2));
		
		
		if(repData!=null  && repData.length>0){
			dptMaster.setModeLength("true");
			dptMaster.setTotalRecords(String.valueOf(repData.length));
		
	String[] pageIndex = Utility.doPaging(dptMaster.getMyPage(), repData.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			//pageIndex[4] = "1";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			dptMaster.setMyPage("1");
		ArrayList<OfficialDetailSettingsBean> List = new ArrayList<OfficialDetailSettingsBean>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
			
			OfficialDetailSettingsBean bean1 = new OfficialDetailSettingsBean();
			bean1.setConfigId(checkNull(String.valueOf(repData[i][8])));
			bean1.setDivName(checkNull(String.valueOf(repData[i][6])));
		
			List.add(bean1);
		}//end of loop
System.out.println("here size---"+List.size());
		
		if(List.size()>0 && List!=null){
			System.out.println("in if");
		dptMaster.setConfigList(List);
		}else{
			dptMaster.setConfigList(null);
		}
		}
	}
	
	
	/**
	 * Saving Official Details Settings
	 * 
	 * @param setting
	 * @return boolean inserts data
	 * Added by Janisha on 11 Feb 2011 for D1 Official Details Configuraton
	 */
	
	public boolean checkDuplicate(OfficialDetailSettingsBean bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_D1_OFFICE_CONF WHERE CONF_DIV_CODE LIKE '"
				+ bean.getDeptDivCode().trim() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}
	
	public boolean checkDuplicateMod(OfficialDetailSettingsBean bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_D1_OFFICE_CONF WHERE CONF_DIV_CODE LIKE '"
				+ bean.getDeptDivCode() + "' AND CONFIG_ID not in(" + bean.getConfigId()+ ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	
	
	public boolean saveOfficialDetailSetting(OfficialDetailSettingsBean setting) {
	

		// TODO Auto-generated method stub

		Object[][] addPass = new Object[1][8];
		boolean result=false;
		System.out.println("-------------" + setting.getDeptDivCode());
		System.out.println("-------------" + setting.getSsnFlag());
		if (!checkDuplicate(setting)) {
		if (!setting.getDeptDivCode().equals("") && setting.getDeptDivCode()!=null) {
			addPass[0][0] = setting.getDeptDivCode().trim();
			addPass[0][6] = "Y";
		}// ENd if
		else{
			addPass[0][0] = 0;
			addPass[0][6] = "N";
		}

		if (setting.getSsnFlag().equals("true")) {
			addPass[0][1] = "Y";
		}// ENd if
		else{
			addPass[0][1] = "N";
		}
		if (setting.getSinFlag().equals("true")) {
			addPass[0][2] = "Y";
		}// ENd if
		else{
			addPass[0][2] = "N";
		}
		if (setting.getDeptNoFlag().equals("true")) {
			addPass[0][3] = "Y";
		}// ENd if
		else{
			addPass[0][3] = "N";
		}
		
		
		if (setting.getRegionFlag().equals("true")) {
			addPass[0][4] = "Y";
		}// ENd if
		else{
			addPass[0][4] = "N";
		}
		if (setting.getExeFlag().equals("true")) {
			addPass[0][5] = "Y";
		}// ENd if
		else{
			addPass[0][5] = "N";
		}
		
		//if (setting.getEmergencyFlag().equals("true") && setting.getEmergencyFlag()!=null) {
			//addPass[0][7] = "Y";
		//}// ENd if
		//else{
			addPass[0][7] = "N";
		//}
		//getSqlModel().singleExecute(getQuery(50));
		getSqlModel().singleExecute(getQuery(1), addPass);
		 result =true;
		}else{
			 result =false;
		}
		return result;
	
	}
	/**
	 * Set fields under Officail Details Settings After Save
	 * 
	 * @param setting
	 */
	
	public void showOfficialDetailSetting(OfficialDetailSettingsBean setting) {
		// TODO Auto-generated method stub
		String Query = "select CONF_DIV_CODE,CONF_SSN_FLAG,CONF_SIN_FLAG,CONF_DEPTNO_FLAG,"
			+" CONF_REG_FLAG,CONF_EXE_FLAG,HRMS_DIVISION.DIV_NAME,CONF_EMERGENCY_FLAG,CONFIG_ID from HRMS_D1_OFFICE_CONF"
			+" INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_D1_OFFICE_CONF.CONF_DIV_CODE) ";

		Object[][] Data = getSqlModel().getSingleResult(Query);

		if (Data != null && Data.length > 0) {
			/*if (Data[0][0].equals("Y")) {
				setting.setDivD1Flag("true");
			} // END nested if
			else{
				setting.setDivD1Flag("false");
			}*/

			if(Data[0][0]!=null){
				//setting.setHiddenCode(String.valueOf(Data[0][0]));
				setting.setDeptDivCode(String.valueOf(Data[0][0]));
				setting.setDivName(String.valueOf(Data[0][6]));
			}
			
			if (Data[0][1].equals("Y")) {
				setting.setSsnFlag("true");
			}// END nested if
			else{
				setting.setSsnFlag("false");
			}

			if (Data[0][2].equals("Y")) {
				setting.setSinFlag("true");
			} // END nested if
			else{
				setting.setSinFlag("false");
			}

			if (Data[0][3].equals("Y")) {
				setting.setDeptNoFlag("true");

			} // END nested if
			else{
				setting.setDeptNoFlag("false");
			}

			if (Data[0][4].equals("Y")) {
				setting.setRegionFlag("true");

			} // END nested if
			else{
				setting.setRegionFlag("false");
			}

			if (Data[0][5].equals("Y")) {
				setting.setExeFlag("true");

			} // END nested if
			else{
				setting.setExeFlag("false");
			}
			
			if (Data[0][7].equals("Y")) {
				setting.setEmergencyFlag("true");

			} // END nested if
			else{
				setting.setEmergencyFlag("false");
			}
			
			setting.setHiddenCode(String.valueOf(Data[0][8]));
		}// END if "length"
		else
		{
			setting.setDivD1Flag("false");
			setting.setSsnFlag("false");
			setting.setSinFlag("true");
			setting.setDeptNoFlag("false");
			setting.setRegionFlag("false");
			setting.setExeFlag("false");
		}
	}
	//End Added by Janisha on 11 Feb 2011 for D1 Official Details Configuraton
	
	
	public boolean updateOfficialDetailSetting(OfficialDetailSettingsBean setting) {
		

		// TODO Auto-generated method stub

		Object[][] addPass = new Object[1][9];
		boolean result=false;
		if (!checkDuplicateMod(setting)) {
		if (setting.getSsnFlag().equals("true")) {
			addPass[0][0] = "Y";
		}// ENd if
		else{
			addPass[0][0] = "N";
		}
		if (setting.getSinFlag().equals("true")) {
			addPass[0][1] = "Y";
		}// ENd if
		else{
			addPass[0][1] = "N";
		}
		if (setting.getDeptNoFlag().equals("true")) {
			addPass[0][2] = "Y";
		}// ENd if
		else{
			addPass[0][2] = "N";
		}
		
		
		if (setting.getRegionFlag().equals("true")) {
			addPass[0][3] = "Y";
		}// ENd if
		else{
			addPass[0][3] = "N";
		}
		if (setting.getExeFlag().equals("true")) {
			addPass[0][4] = "Y";
		}// ENd if
		else{
			addPass[0][4] = "N";
		}
		
		
		if (!setting.getDeptDivCode().equals("") && setting.getDeptDivCode()!=null) {
			//addPass[0][0] = setting.getDeptDivCode().trim();
			addPass[0][5] = "Y";
		}// ENd if
		else{
			//addPass[0][0] = 0;
			addPass[0][5] = "N";
		}
		
			addPass[0][6] = "N";
			addPass[0][7] = setting.getDeptDivCode().trim();
			addPass[0][8] = setting.getConfigId().trim();
			
			String update="Update HRMS_D1_OFFICE_CONF set CONF_SSN_FLAG=?,CONF_SIN_FLAG=?,CONF_DEPTNO_FLAG=?,CONF_REG_FLAG=?,CONF_EXE_FLAG=?,CONF_DIV_FLAG=?,CONF_EMERGENCY_FLAG=?,CONF_DIV_CODE=? "
				+" where CONFIG_ID=? ";
			
		getSqlModel().singleExecute(update, addPass);
		result=true;
		}else{
			result=false;
		}
		return result;
	
	}
	
	/* for deleting the record after selecting */
	public boolean deleteDept(OfficialDetailSettingsBean bean) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getConfigId();
		return getSqlModel().singleExecute(getQuery(3), addObj);
	}
	/* for deleting the one or more records from list */
	public boolean deleteDept(OfficialDetailSettingsBean bean, String[] code) {

		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if (code != null) {

			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(getQuery(3), delete);
					if (!flag) {
						cnt++;
					}// end of if
					// result=true;
				}// end of if
			}// end of loop
		}
		if (cnt > 0) {
			result = false;
		}// end of if
		else
			result = true;
		return result;

	}
	
	/*
	 * for selecting the data from list and setting those data in respective
	 * fields
	 */
	public void calforedit(OfficialDetailSettingsBean dptMaster) {

		

		String query = "select CONFIG_ID,CONF_DIV_CODE,HRMS_DIVISION.DIV_NAME from HRMS_D1_OFFICE_CONF"
			+" INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_D1_OFFICE_CONF.CONF_DIV_CODE) "
			+" WHERE  CONFIG_ID= "+ dptMaster.getHiddencode() + " ORDER BY CONFIG_ID ";


		
		Object[][] data = getSqlModel().getSingleResult(query);
		dptMaster.setConfigId(String.valueOf(data[0][0]));
		dptMaster.setDeptDivCode(String.valueOf(data[0][1]));
		dptMaster.setDivName(String.valueOf(data[0][1]));

	}
	
	public void getDeptRecord(OfficialDetailSettingsBean bean) {
		Object addObj[] = new Object[1];
		addObj[0] = bean.getConfigId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);

		for (int i = 0; i < data.length; i++) {
			
			
			

				if(data[0][0]!=null){
					//setting.setHiddenCode(String.valueOf(Data[0][0]));
					bean.setDeptDivCode(String.valueOf(data[0][0]));
					bean.setDivName(String.valueOf(data[0][6]));
				}
				
				if (data[0][1].equals("Y")) {
					bean.setSsnFlag("true");
				}// END nested if
				else{
					bean.setSsnFlag("false");
				}

				if (data[0][2].equals("Y")) {
					bean.setSinFlag("true");
				} // END nested if
				else{
					bean.setSinFlag("false");
				}

				if (data[0][3].equals("Y")) {
					bean.setDeptNoFlag("true");

				} // END nested if
				else{
					bean.setDeptNoFlag("false");
				}

				if (data[0][4].equals("Y")) {
					bean.setRegionFlag("true");

				} // END nested if
				else{
					bean.setRegionFlag("false");
				}

				if (data[0][5].equals("Y")) {
					bean.setExeFlag("true");

				} // END nested if
				else{
					bean.setExeFlag("false");
				}
				
				if (data[0][7].equals("Y")) {
					bean.setEmergencyFlag("true");

				} // END nested if
				else{
					bean.setEmergencyFlag("false");
				}
				
				bean.setConfigId(String.valueOf(data[0][8]));
			
			
		}// end of loop
	}
}
