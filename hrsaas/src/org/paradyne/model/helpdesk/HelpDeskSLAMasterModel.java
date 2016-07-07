package org.paradyne.model.helpdesk;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.helpdesk.HelpDeskSLAMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class HelpDeskSLAMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(HelpDeskSLAMasterModel.class);

	public void getRecords(HelpDeskSLAMaster slaMaster,
			HttpServletRequest request) {
		try {
			int length=0;	
			String query = " SELECT SLA_ID, NVL(SLA_NAME,' '), NVL(SLA_DESC,' '), DECODE (HELPDESK_SLA_HDR.IS_ACTIVE,'Y','Yes','N','No','No')  FROM HELPDESK_SLA_HDR "
				+ "ORDER BY SLA_ID";

			Object[][] data = getSqlModel().getSingleResult(query);
			if(data!=null && data.length>0){
				slaMaster.setModeLength("true");
				slaMaster.setTotalRecords(String.valueOf(data.length));  //   to  display the total number of record in  the list 
			
			String[] pageIndex = Utility.doPaging(slaMaster.getMyPage(),data.length, 20);	
			if(pageIndex==null){
				pageIndex[0] = "0";
				pageIndex[1] ="20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if(pageIndex[4].equals("1"))
				slaMaster.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 	
				HelpDeskSLAMaster slaMaster1 = new HelpDeskSLAMaster();
				
				slaMaster1.setSlaCodeItr(String.valueOf(data[i][0]));   
				slaMaster1.setSlaNameItr(String.valueOf(data[i][1]));
				slaMaster1.setSlaDescItr(String.valueOf(data[i][2]));  
				slaMaster1.setIsActiveItr(String.valueOf(data[i][3]));  
				List.add(slaMaster1);
			}
			
			slaMaster.setSlaList(List);
			length=data.length;
			slaMaster.setTotalRecords(String.valueOf(length));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void getSLACategories(HelpDeskSLAMaster slaMaster) {
		int slaCounter = 0;
		try {
			String query = " SELECT STATUS_CATAGORY_ID, NVL(STATUS_CATAGORY_NAME,' ')  FROM HELPDESK_SLA_STATUS_CATAGORY "
					+ "ORDER BY STATUS_CATAGORY_ID";
			
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				slaMaster.setModeLengthList("true");
				ArrayList<Object> statusList = new ArrayList<Object>();
				for (int i = 0; i < data.length; i++) {
					slaCounter++;
					HelpDeskSLAMaster slaMaster1 = new HelpDeskSLAMaster();

					slaMaster1.setSlaCategId(String.valueOf(data[i][0]));
					slaMaster1.setSlaStatus(String.valueOf(data[i][1]));
					statusList.add(slaMaster1);
				}
				slaMaster.setSlaCategoryCounter(slaCounter);
				slaMaster.setSlaStatusList(statusList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String addSlaHeader(HelpDeskSLAMaster slaMaster, String[] id,
			String[] duration, String[] durType, String[] escalateIdOne, 
			String[] durationOne, String[] durationTypeOne, String[] escalateIdTwo, String[] durationTwo, 
			String[] durationTypeTwo, String[] escalateIdThree, String[] durationThree, String[] durationTypeThree, int slaCategories) {
		
		Object[][] add = new Object[1][3];
		String flag = "";
		boolean result = false;
		try {
			add[0][0] = slaMaster.getSlaName().trim(); 
			add[0][1] = slaMaster.getSlaDesc();
			if( slaMaster.getIsActive().equals("true")){
				add[0][2] = "Y"; //is active
			}else{
				add[0][2] = "N";
			}

			if (!checkDuplicate(slaMaster)) {
				String query = " INSERT INTO HELPDESK_SLA_HDR (SLA_ID,SLA_NAME,SLA_DESC, IS_ACTIVE) " 
					+"VALUES((SELECT NVL(MAX(SLA_ID),0)+1 FROM HELPDESK_SLA_HDR ),?,?,?)";
				result = getSqlModel().singleExecute(query, add);
				if (result) {

					String maxQuery = " SELECT MAX(SLA_ID) FROM HELPDESK_SLA_HDR";
					// to get the  designation/rank id  from table
					Object[][] data = getSqlModel().getSingleResult(maxQuery);
					slaMaster.setSlaCode(String.valueOf(data[0][0]));
					
					addSlaDetails(slaMaster,id,duration,durType, escalateIdOne,
							durationOne, durationTypeOne, escalateIdTwo, durationTwo, durationTypeTwo, escalateIdThree
							,durationThree,durationTypeThree, slaCategories);
				
					flag = "saved";
				} else {
					flag = "error";
				}
			} else {
				flag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was raised-->");
		}
		return flag;
	}

	private boolean addSlaDetails(HelpDeskSLAMaster slaMaster, String[] id,
			String[] duration, String[] durType, String[] escalateIdOne, 
			String[] durationOne, String[] durationTypeOne, String[] escalateIdTwo, 
			String[] durationTwo, String[] durationTypeTwo, String[] escalateIdThree, String[] durationThree, String[] durationTypeThree, int slaCategories) {
		boolean result = false;
		Object addObj[][] = new Object[1][14];
		try {
			if (slaCategories > 1) {
				logger.info("slaCategories  : " +slaCategories);
				for (int i = 0; i < slaCategories-1; i++) {
						addObj[0][0] = slaMaster.getSlaCode();
						addObj[0][1] = id[i+1];
						addObj[0][2] = duration[i];
						addObj[0][3] = durType[i];
						if(String.valueOf(durType[i]).equals("H")){
							addObj[0][4] = Integer.parseInt(String.valueOf(duration[i]))*60;
						}else if(String.valueOf(durType[i]).equals("D")){
							addObj[0][4] = Integer.parseInt(String.valueOf(duration[i]))*24*60;
						}else{
							addObj[0][4] = duration[i];
						}
						addObj[0][5] = durationOne[i];
						addObj[0][6] = Utility.checkNull(escalateIdOne[i]);
						addObj[0][7] = durationTwo[i];
						addObj[0][8] = Utility.checkNull(escalateIdTwo[i]);
						addObj[0][9] = durationThree[i];
						addObj[0][10] = Utility.checkNull(escalateIdThree[i]);
						addObj[0][11] = durationTypeOne[i];
						addObj[0][12] = durationTypeTwo[i]; 
						addObj[0][13] = durationTypeThree[i];
						for (int j = 0; j < addObj.length; j++) {
							for (int j2 = 0; j2 < addObj[0].length; j2++) {
								logger.info("addObj["+j+"]["+j2+"]...."+addObj[j][j2]);
							}
						}
						
						
						String query = " INSERT INTO HELPDESK_SLA_DTL ( SLA_ID, STATUS_CATAGORY_ID,SLA_STATUS_DURATION, SLA_STATUS_DURATION_TYPE, SLA_STATUS_DURATION_MINUTES, SLA_ESCALATION_TIME_1, SLA_ESCALATION_TO_1, SLA_ESCALATION_TIME_2, SLA_ESCALATION_TO_2, SLA_ESCALATION_TIME_3, SLA_ESCALATION_TO_3, SLA_ESCALATION_DURN_TYPE1, SLA_ESCALATION_DURN_TYPE2, SLA_ESCALATION_DURN_TYPE3) "
								+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						result = getSqlModel().singleExecute(query, addObj);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	private boolean checkDuplicate(HelpDeskSLAMaster slaMaster) {
		boolean result = false;
		String query = "SELECT * FROM HELPDESK_SLA_HDR WHERE UPPER(SLA_NAME) LIKE '"
				+ slaMaster.getSlaName().trim().toUpperCase() + "'";

		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}

	public String modSlaHeader(HelpDeskSLAMaster slaMaster, String[] id,
			String[] duration, 	String[] durType, String[] escalateIdOne, String[] durationOne, String[] durationTypeOne, String[] escalateIdTwo, String[] durationTwo, String[] durationTypeTwo, String[] escalateIdThree, String[] durationThree, String[] durationTypeThree, int slaCategories) {
		// to get the data for  update the record
		Object[][] data = new Object[1][4];
		String editFlag = "";
		boolean result,result1 = false;
		try {
			data[0][0] = slaMaster.getSlaName().trim();   
			data[0][1] = slaMaster.getSlaDesc(); 
			if( slaMaster.getIsActive().equals("true")){
				data[0][2] = "Y"; //is active
			}else{
				data[0][2] = "N";
			}
			data[0][3] = slaMaster.getSlaCode();
			
			if (!checkDuplicateMod(slaMaster)) {
				// to get the data  for modifying  the record
				String query = "UPDATE HELPDESK_SLA_HDR SET SLA_NAME=?,SLA_DESC=?, IS_ACTIVE=? WHERE SLA_ID=?";
				result = getSqlModel().singleExecute(query, data);
				if (result) {
					/*slaMaster.getReqTypeCode(checkNull(String.valueOf(
							data[0][4]).trim())); */  
					String queryDel = "DELETE FROM HELPDESK_SLA_DTL WHERE SLA_ID= "
						+ slaMaster.getSlaCode();
					result1 = getSqlModel().singleExecute(queryDel);
					if (result1){
						addSlaDetails(slaMaster, id, duration, durType, escalateIdOne,
								durationOne, durationTypeOne, escalateIdTwo, durationTwo, durationTypeTwo, escalateIdThree
								,durationThree,durationTypeThree, slaCategories);
					
						editFlag = "modified";
					}else{
						editFlag = "error";
					}
				} else {
					editFlag = "error";
				}
			} else {
				editFlag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was rised--->");
		}
		return editFlag;
	}


	private boolean checkDuplicateMod(HelpDeskSLAMaster slaMaster) {
		boolean result = false;
		Object[][] data = null;
		Object[] value = new Object[1];
		try {

			value[0] = slaMaster.getSlaName().trim().toUpperCase();
		} catch (Exception e) {
			
		}
		try {
			String query = "SELECT * FROM HELPDESK_SLA_HDR WHERE UPPER(SLA_NAME) LIKE '"
					+ slaMaster.getSlaName().trim().toUpperCase()
					+ "' AND SLA_ID not in(" + slaMaster.getSlaCode() + ")";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			
		}
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}
	
	public void getSavedSLACategories(HelpDeskSLAMaster slaMaster) {
		try {
			String categoryQuery = " SELECT STATUS_CATAGORY_ID, NVL(STATUS_CATAGORY_NAME,' ')  FROM HELPDESK_SLA_STATUS_CATAGORY "
				+ "ORDER BY STATUS_CATAGORY_ID";
			Object[][] categoryData = getSqlModel().getSingleResult(categoryQuery);
			
			String query = " SELECT HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_ID, NVL(STATUS_CATAGORY_NAME,' '),SLA_STATUS_DURATION,"
				+ " NVL(SLA_STATUS_DURATION_TYPE,' '),"
				+ " SLA_ESCALATION_TO_1,A.EMP_TOKEN, NVL(A.EMP_FNAME,' ')||' '||NVL(A.EMP_LNAME,' ') ESC_1,NVL(SLA_ESCALATION_TIME_1,0),NVL(SLA_ESCALATION_DURN_TYPE1,' '),"
				+ " SLA_ESCALATION_TO_2,B.EMP_TOKEN, NVL(B.EMP_FNAME,' ')||' '||NVL(B.EMP_LNAME,' ') ESC_2,NVL(SLA_ESCALATION_TIME_2,0),NVL(SLA_ESCALATION_DURN_TYPE2,' '),"
				+ " SLA_ESCALATION_TO_3,C.EMP_TOKEN, NVL(C.EMP_FNAME,' ')||' '||NVL(C.EMP_LNAME,' ') ESC_3,NVL(SLA_ESCALATION_TIME_3,0),NVL(SLA_ESCALATION_DURN_TYPE3,' ')"
				+ " FROM HELPDESK_SLA_DTL "
				+ " INNER JOIN HELPDESK_SLA_STATUS_CATAGORY ON (HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_ID = HELPDESK_SLA_DTL.STATUS_CATAGORY_ID ) "
				+ " LEFT JOIN HRMS_EMP_OFFC A ON(A.EMP_ID = HELPDESK_SLA_DTL.SLA_ESCALATION_TO_1)"
				+ " LEFT JOIN HRMS_EMP_OFFC B ON(B.EMP_ID = HELPDESK_SLA_DTL.SLA_ESCALATION_TO_2)"
				+ " LEFT JOIN HRMS_EMP_OFFC C ON(C.EMP_ID = HELPDESK_SLA_DTL.SLA_ESCALATION_TO_3)"
				+ " WHERE SLA_ID="+slaMaster.getSlaCode()
				+ " ORDER BY HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_ID";

			Object[][] data = getSqlModel().getSingleResult(query);
			if (categoryData != null && categoryData.length > 0) {
				if (data != null && data.length > 0) {
					slaMaster.setModeLengthList("true");
					ArrayList<Object> statusList = new ArrayList<Object>();
					for (int i = 0; i < data.length+1; i++) {
						HelpDeskSLAMaster slaMaster1 = new HelpDeskSLAMaster();
						
						slaMaster1.setSlaCategId(String.valueOf(categoryData[i][0]));
						slaMaster1.setSlaStatus(String.valueOf(categoryData[i][1]));
						if(String.valueOf(categoryData[i][0]).equals("1")){
							slaMaster1.setSlaDuration("");
							slaMaster1.setDurType("");
							slaMaster1.setEscalateOneEmpId("");
							slaMaster1.setEscalateOneEmpToken("");
							slaMaster1.setEscalateOneEmpName("");
							slaMaster1.setEscDurationOne("");
							slaMaster1.setDurTypeOne("");
							slaMaster1.setEscalateTwoEmpId("");
							slaMaster1.setEscalateTwoEmpToken("");
							slaMaster1.setEscalateTwoEmpName("");
							slaMaster1.setEscDurationTwo("");
							slaMaster1.setDurTypeTwo("");
							slaMaster1.setEscalateThreeEmpId("");
							slaMaster1.setEscalateThreeEmpToken("");
							slaMaster1.setEscalateThreeEmpName("");
							slaMaster1.setEscDurationThree("");
							slaMaster1.setDurTypeThree("");
							if (!checkNull(String.valueOf(data[i][2])).equals("")) {
								slaMaster1.setConfChkHid("true");
							} else {
								slaMaster1.setConfChkHid("");
							}
						}else{
						slaMaster1.setSlaDuration(checkNull(String
								.valueOf(data[i-1][2])));
						slaMaster1.setDurType(String.valueOf(data[i-1][3]));
						slaMaster1.setEscalateOneEmpId(String
								.valueOf(data[i-1][4]));// esc to Id 1
						slaMaster1.setEscalateOneEmpToken(String
								.valueOf(data[i-1][5]));// esc to Token 1
						slaMaster1.setEscalateOneEmpName(String
								.valueOf(data[i-1][6]));// esc to Name 1
						slaMaster1
								.setEscDurationOne(String.valueOf(data[i-1][7]));// esc dur 1
						slaMaster1.setDurTypeOne(String.valueOf(data[i-1][8]));// esc dur type 1
						slaMaster1.setEscalateTwoEmpId(String
								.valueOf(data[i-1][9]));// esc to Id 2
						slaMaster1.setEscalateTwoEmpToken(String
								.valueOf(data[i-1][10]));// esc to Token 2
						slaMaster1.setEscalateTwoEmpName(String
								.valueOf(data[i-1][11]));// esc to Name 2
						slaMaster1.setEscDurationTwo(String
								.valueOf(data[i-1][12]));// esc dur 2
						slaMaster1.setDurTypeTwo(String.valueOf(data[i-1][13]));// esc dur type 2
						slaMaster1.setEscalateThreeEmpId(String
								.valueOf(data[i-1][14]));// esc to Id 3
						slaMaster1.setEscalateThreeEmpToken(String
								.valueOf(data[i-1][15]));// esc to Token 3
						slaMaster1.setEscalateThreeEmpName(String
								.valueOf(data[i-1][16]));// esc to Name 3
						slaMaster1.setEscDurationThree(String
								.valueOf(data[i-1][17]));// esc dur 3
						slaMaster1.setDurTypeThree(String.valueOf(data[i-1][18]));// esc dur type 3
						if (!checkNull(String.valueOf(data[i-1][2])).equals("")) {
							slaMaster1.setConfChkHid("true");
						} else {
							slaMaster1.setConfChkHid("");
						}
						}
						statusList.add(slaMaster1);
					}
					slaMaster.setSlaStatusList(statusList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getSLADtl(HelpDeskSLAMaster slaMaster) {
		try {
			String query = " SELECT SLA_ID, NVL(SLA_NAME,' '), NVL(SLA_DESC,' '), IS_ACTIVE  FROM HELPDESK_SLA_HDR "
				+ "WHERE SLA_ID="+slaMaster.getHiddencode();

			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
					slaMaster.setSlaCode(String.valueOf(data[0][0]));
					slaMaster.setSlaName(String.valueOf(data[0][1]));
					slaMaster.setSlaDesc(String.valueOf(data[0][2]));
					if (String.valueOf(data[0][3]).equals("Y")) {
						slaMaster.setIsActive("true");
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean delChkdRec(HelpDeskSLAMaster slaMaster, String[] code) {
		int count = 0;
		boolean result = false;
		logger.info("code.length   : "+code.length);
		for (int i = 0; i < code.length; i++) {
			logger.info("code[i]   : "+code[i]);
			if (!code[i].equals("")) {

				Object[][] delete = new Object[1][1];
				delete[0][0] = code[i];
				String query= " DELETE FROM HELPDESK_SLA_DTL WHERE SLA_ID=?";
				result = getSqlModel().singleExecute(query, delete);
				if (result) {
					String query1 = " DELETE FROM HELPDESK_SLA_HDR WHERE SLA_ID=?";
					result = getSqlModel().singleExecute(query1, delete);
				}
				if (!result) {
					count++;
				}
			}
		}

		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	public boolean deleteSLA(HelpDeskSLAMaster slaMaster) {
		boolean result = false;
		Object del[][] = new Object[1][1];
		// to delete the single record after clicking on saving or searching button
		del[0][0] = slaMaster.getSlaCode();
		String query= " DELETE FROM HELPDESK_SLA_DTL WHERE SLA_ID=?";
		result = getSqlModel().singleExecute(query, del);
		if (result) {
			String query1 = " DELETE FROM HELPDESK_SLA_HDR WHERE SLA_ID=?";
			result = getSqlModel().singleExecute(query1, del);
		}
		
		return result;
	}
	
	/**
	 *  to check the null value
	 * @param result
	 * @return
	 */

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

}
