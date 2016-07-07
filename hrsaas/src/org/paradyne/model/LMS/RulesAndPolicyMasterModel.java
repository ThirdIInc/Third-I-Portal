package org.paradyne.model.LMS;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.LMS.RulesAndPolicyMasterBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class RulesAndPolicyMasterModel extends ModelBase {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(RulesAndPolicyMasterModel.class);

	public void getList(RulesAndPolicyMasterBean bean,
			HttpServletRequest request) {

		{
			Object[][] selData = null;
			ArrayList list = new ArrayList();
			try {
				String selQuery = " SELECT  DECODE(RULE_POLICY_CATEGORY,'R','Rule','P','Policy') , DECODE(RULE_POLICY_TYPE,'H','Health& Safety','S','Sexual Harassment') , RULE_POLICY_NAME "
						+ ", RULE_POLICY_CODE FROM HRMS_RULES_POLICY  "
						+ "ORDER BY RULE_POLICY_CODE";

				selData = getSqlModel().getSingleResult(selQuery);
			} catch (Exception e) {
				logger.error("exception in due query", e);
			}
			String[] pageIndex = new org.paradyne.lib.Utility().doPaging(bean
					.getMyPage(), selData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");
			if (selData == null) {

			} else if (selData.length == 0) {

			} else {
				bean.setTotalRecords("" + selData.length);
				bean.setModeLength("true");
				try {
					for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
							.parseInt(String.valueOf(pageIndex[1])); i++) {
						bean.setModeLength("true");
						RulesAndPolicyMasterBean beanItt = new RulesAndPolicyMasterBean();
						beanItt.setCategoryType(checkNull(String
								.valueOf(selData[i][0])));
						beanItt.setCommitteeType(checkNull(String
								.valueOf(selData[i][1])));
						beanItt.setName(checkNull(String
								.valueOf(selData[i][2])));
						
						beanItt.setRulesAndPolicyID(checkNull(String
								.valueOf(selData[i][3])));

						list.add(beanItt);
					}
				} catch (Exception e) {
					logger.error("exception in for loop dueData", e);
				}
				bean.setRulesAndPolicyList(list);
			}
		}

	}

	public boolean save(RulesAndPolicyMasterBean bean,
			HttpServletRequest request) {

		boolean result = false;
		try {
			System.out.println("IN SAVE ");
			
			Object insertObj[][] = new Object[1][9];

			insertObj[0][0] = bean.getCategoryType();
			insertObj[0][1] = bean.getCommitteeType();
			insertObj[0][2] = bean.getName();
			insertObj[0][3] = bean.getDetails();
			
			String langauge="";
			
			if(!bean.getEngFlag().equals("N"))
				langauge=bean.getEng()+",";
			if(!bean.getHindiFlag().equals("N"))
				langauge += bean.getHin()+",";
			if(!bean.getMarathiFlag().equals("N"))
				langauge += bean.getMar()+",";
			if(!bean.getOthersFlag().equals("N"))
				langauge += bean.getOth()+",";
			
			
			langauge = langauge.substring(0, langauge
					.length() - 1);
			
			insertObj[0][4] = langauge;
			
			
			insertObj[0][5] = checkNull(bean.getOthersLanguage());
			
			String medium="";
			
			if(!bean.getNoticeBoardFlag().equals("N"))
				medium += bean.getNotice()+",";
			if(!bean.getCircularFlag().equals("N"))
				medium += bean.getCircular()+",";
			if(!bean.getOthersMediumFlag().equals("N"))
				medium += bean.getOther()+",";
			
			medium = medium.substring(0, medium
					.length() - 1);
			
			insertObj[0][6] = medium;
			
						
			if(bean.getOthersMediumFlag().equals("true"))
				insertObj[0][7]	= checkNull(bean.getOthersMedium());
			
			insertObj[0][8] = bean.getProofName();
			

			String insertQuery = "INSERT INTO HRMS_RULES_POLICY"
					+ "(RULE_POLICY_CODE, RULE_POLICY_CATEGORY,  RULE_POLICY_TYPE,RULE_POLICY_NAME, RULE_POLICY_DTL,RULE_POLICY_COMM_LANG,RULE_POLICY_OTH_COMM_LANG,RULE_POLICY_COMM_MEDIUM,RULE_POLICY_OTH_COMM_MED,RULE_POLICY_DOC)"
					+ " VALUES((SELECT NVL(MAX(RULE_POLICY_CODE),0)+1 FROM HRMS_RULES_POLICY),?,?,?,?,?,?,?,?,?)";

			result = getSqlModel().singleExecute(insertQuery, insertObj);
			
			
			for (int i = 0; i < 9; i++) {
				System.out.println(" insertObj RUles And Policy::::::"
						+ insertObj[0][i]);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public void displayIteratorValueForUploadProof(String[] srNo,
			String[] proofName, RulesAndPolicyMasterBean bean) {
		try {
			ArrayList<RulesAndPolicyMasterBean> proofList = new ArrayList<RulesAndPolicyMasterBean>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					RulesAndPolicyMasterBean beanList = new RulesAndPolicyMasterBean();
					beanList.setProofName(proofName[i]);
					// tmsclaimapp.setProofFileName(proofFileName[i]);
					proofList.add(beanList);
				}
				bean.setProofList(proofList);
			}
		} catch (Exception e) {
			logger
					.error("Exception in displayIteratorValueForUploadProof------"
							+ e);
		}

		
	}

	public void setProofList(String[] srNo, String[] proofName,
			RulesAndPolicyMasterBean bean) {
		try {
			RulesAndPolicyMasterBean beanProof = new RulesAndPolicyMasterBean();
			beanProof.setProofName(bean.getUploadLocFileName());

			ArrayList<RulesAndPolicyMasterBean> proofList = displayNewValueProofList(
					srNo, proofName, bean);
			beanProof.setProofSrNo(String.valueOf(proofList.size() + 1));// sr no
			proofList.add(beanProof);
			bean.setProofList(proofList);
		} catch (Exception e) {
			logger.error("Exception in setProofList------" + e);
		}
		
	}
	
	private ArrayList<RulesAndPolicyMasterBean> displayNewValueProofList(
			String[] srNo, String[] proofName, RulesAndPolicyMasterBean bean) {
		ArrayList<RulesAndPolicyMasterBean> proofList = null;
		try {
			proofList = new ArrayList<RulesAndPolicyMasterBean>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					RulesAndPolicyMasterBean claimApp = new RulesAndPolicyMasterBean();
					claimApp.setProofName(proofName[i]);
					claimApp.setProofSrNo(srNo[i]);
					// claimApp.setProofFileName(proofFileName[i]);
					proofList.add(claimApp);
				}

			}
		} catch (Exception e) {
			logger.error("Exception in displayNewValueProofList------" + e);
		}
		return proofList;
	}

	/**
	 * following function is called to delete a record
	 * 
	 * @param custID
	 * 
	 * @param cbean
	 * @return
	 */
	/*public boolean delRecord(TradeUnionWorkCouncilBean councilBean,
			String tradeID) {
		boolean result = false;

		Object del[][] = new Object[1][1];
		// to delete the single record after clicking on saving or searching
		// button
		del[0][0] = tradeID;

		System.out.println("councilBean.getTradeUnionWorkCouncilID() = "
				+ councilBean.getTradeUnionWorkCouncilID());
		String deleteAgrGrivQuery = "DELETE FROM HRMS_COMM_AGRMNT_GRVNCE WHERE COMMITTEE_ID=? ";

		result = getSqlModel().singleExecute(deleteAgrGrivQuery, del);
		System.out.println("deleteAgrGrivQuery = "
				+ deleteAgrGrivQuery.length());
		String deleteBargAgreeQuery = "DELETE FROM HRMS_COMMITEE_MEMBERS WHERE COMMITTEE_ID=? ";

		result = getSqlModel().singleExecute(deleteBargAgreeQuery, del);
		System.out.println("deleteBargAgreeQuery = "
				+ deleteBargAgreeQuery.length());
		String deleteQuery = "DELETE FROM HRMS_COMMITEE_HDR WHERE COMMITTEE_ID=? ";

		result = getSqlModel().singleExecute(deleteQuery, del);

		return result;
	}// End of delRecord method */

	public boolean deleteCheckedRecords(RulesAndPolicyMasterBean bean,
			String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];

					String deleteCommHdrQuery = "DELETE FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CODE=? ";
					result = getSqlModel().singleExecute(deleteCommHdrQuery,
							delete);

					if (!result)
						count++;
				}// end of if
			} // end of for loop
		} // end of if
		if (count != 0) {
			result = false;
			return result;
		} // end of if
		else {
			result = true;
			return result;
		} // end of else
	} // end of deleteCheckedRecords

	/*
	 * for selecting the data from list and setting those data in respective
	 * fields
	 */
	public void calforedit(RulesAndPolicyMasterBean bean, String custID,
			HttpServletRequest request) {
		{

			try {

				String headerquery = " SELECT  RULE_POLICY_CATEGORY , RULE_POLICY_TYPE , RULE_POLICY_NAME,RULE_POLICY_DTL,RULE_POLICY_COMM_LANG,RULE_POLICY_OTH_COMM_LANG" +
						",RULE_POLICY_COMM_MEDIUM,RULE_POLICY_OTH_COMM_MED,RULE_POLICY_DOC,RULE_POLICY_CODE "
						+ "  FROM HRMS_RULES_POLICY  "
						+ " WHERE  RULE_POLICY_CODE = "
						+ custID
						+ " ORDER BY RULE_POLICY_CODE ";

				Object[][] data = getSqlModel().getSingleResult(headerquery);

				bean.setCategoryType(checkNull(String.valueOf(data[0][0])));
				bean.setCommitteeType(checkNull(String.valueOf(data[0][1])));
				
				bean.setName(checkNull(String.valueOf(data[0][2])));
				bean.setDetails(checkNull(String.valueOf(data[0][3])));

				
				
				
				if (String.valueOf(data[0][4]).equals("E")) {
					bean.setEng("true");
				}

				if (String.valueOf(data[0][5]).equals("O")) {
					bean.setOthersLanguage(checkNull(String.valueOf(data[0][5])));
				}
				
				if (String.valueOf(data[0][6]).equals("N")) {
					bean.setNoticeBoardFlag("true");
				}
				
				if (String.valueOf(data[0][7]).equals("O")) {
					bean.setOthersMedium(checkNull(String.valueOf(data[0][7])));
				}
				
				String proofNameValue = String.valueOf(data[0][8]);
				bean.setProofName(proofNameValue);

				if (proofNameValue != null && proofNameValue.length() > 0) {
					String[] innerproofName = proofNameValue.split(",");
					ArrayList innerlist = new ArrayList();
					for (int k = 0; k < innerproofName.length; k++) {
						RulesAndPolicyMasterBean innerbean = new RulesAndPolicyMasterBean();
						innerbean.setProofName(checkNull(String
								.valueOf(innerproofName[k])));
						innerlist.add(innerbean);
					}
					bean.setProofList(innerlist);
				}
				
				
				
				//bean.setProofName(String.valueOf(data[0][8]));
				
								
				bean.setRulesAndPolicyID(String.valueOf(data[0][9]));

				for (int i = 0; i < 10; i++) {
					System.out.println(" EDIT Rukles & Policy Details::::::"
							+ data[0][i]);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}
	
	public void setRulesAndPolicyRecord(RulesAndPolicyMasterBean bean, String custID) {
		System.out.println("Rules & Policy list here----"
				+ custID);

		String query = " SELECT  RULE_POLICY_CATEGORY , RULE_POLICY_TYPE , RULE_POLICY_NAME,RULE_POLICY_DTL,RULE_POLICY_COMM_LANG,RULE_POLICY_OTH_COMM_LANG" +
						",RULE_POLICY_COMM_MEDIUM,RULE_POLICY_OTH_COMM_MED,RULE_POLICY_DOC,RULE_POLICY_CODE "
						+ "  FROM HRMS_RULES_POLICY  "
						+ " WHERE  RULE_POLICY_CODE = "
						+ custID;
			

		Object[][] data = getSqlModel().getSingleResult(query);

		bean.setCategoryType(checkNull(String.valueOf(data[0][0])));
		bean.setCommitteeType(checkNull(String.valueOf(data[0][1])));
		bean.setName(checkNull(String.valueOf(data[0][2])));
		bean.setDetails(checkNull(String.valueOf(data[0][3])));

		if (String.valueOf(data[0][4]).equals("E")) {
			bean.setEngFlag("true");
		}

		bean.setOthersLanguage(String.valueOf(data[0][5]));
		
		if (String.valueOf(data[0][6]).equals("E")) {
			bean.setNoticeBoardFlag("true");
		}


		bean.setOthersMediumFlag(String.valueOf(data[0][7]));
		String proofNameValue = String.valueOf(data[0][8]);
		bean.setProofName(proofNameValue);

		if (proofNameValue != null && proofNameValue.length() > 0) {
			String[] innerproofName = proofNameValue.split(",");
			ArrayList innerlist = new ArrayList();
			for (int k = 0; k < innerproofName.length; k++) {
				RulesAndPolicyMasterBean innerbean = new RulesAndPolicyMasterBean();
				innerbean.setProofName(checkNull(String
						.valueOf(innerproofName[k])));
				innerlist.add(innerbean);
			}
			bean.setProofList(innerlist);
		}
	}

	public boolean update(RulesAndPolicyMasterBean bean,
			HttpServletRequest request) {
		boolean result = false;
		try {

			Object updateObj[][] = new Object[1][9];

			updateObj[0][0] = bean.getCategoryType();
			updateObj[0][1] = bean.getCommitteeType();
			updateObj[0][2] = bean.getName();
			updateObj[0][3] = bean.getDetails();
			
			String langauge="";
			
			if(!bean.getEngFlag().equals("N"))
				langauge=bean.getEng()+",";
			if(!bean.getHindiFlag().equals("N"))
				langauge += bean.getHin()+",";
			if(!bean.getMarathiFlag().equals("N"))
				langauge += bean.getMar()+",";
			if(!bean.getOthersFlag().equals("N"))
				langauge += bean.getOth()+",";
			
			
			langauge = langauge.substring(0, langauge
					.length() - 1);
			
			updateObj[0][4] = langauge;
			
			
			updateObj[0][5] = checkNull(bean.getOthersLanguage());
			
			String medium="";
			
			if(!bean.getNoticeBoardFlag().equals("N"))
				medium += bean.getNotice()+",";
			if(!bean.getCircularFlag().equals("N"))
				medium += bean.getCircular()+",";
			if(!bean.getOthersMediumFlag().equals("N"))
				medium += bean.getOther()+",";
			
			medium = medium.substring(0, medium
					.length() - 1);
			
			updateObj[0][6] = medium;
			
						
			if(bean.getOthersMediumFlag().equals("true"))
				updateObj[0][7]	= checkNull(bean.getOthersMedium());
			
			updateObj[0][8] = bean.getProofName();

			String insertQuery = "UPDATE HRMS_RULES_POLICY SET "
					+ "  RULE_POLICY_CATEGORY = ?,  RULE_POLICY_TYPE = ?,RULE_POLICY_NAME = ?, RULE_POLICY_DTL = ?,RULE_POLICY_COMM_LANG = ?,RULE_POLICY_OTH_COMM_LANG = ?," +
							"RULE_POLICY_COMM_MEDIUM = ?,RULE_POLICY_OTH_COMM_MED = ?,RULE_POLICY_DOC = ? WHERE RULE_POLICY_CODE = "
					+ bean.getRulesAndPolicyID();

			result = getSqlModel().singleExecute(insertQuery, updateObj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean delRecord(RulesAndPolicyMasterBean bean) {
		Object del[][] = null;
		try {
			del = new Object[1][1];
			// to delete the single record after clicking on saving or searching
			// button
			del[0][0] = bean.getRulesAndPolicyID();
		} catch (Exception e) {
			// TODO: handle exception
		}
		String deleteQuery = "DELETE FROM HRMS_RULES_POLICY WHERE RULE_POLICY_CODE=? ";

		return getSqlModel().singleExecute(deleteQuery, del);
	}

	

	/*public void getRecord(SafetyCommitteeMasterBean bean) {
		try {
			System.out.println("Hidden code =========> "
					+ bean.getSafetyCommitteeID());
			String query = " SELECT SAFETY_COMM_TYPE, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS MEMBER_NAME,SAFETY_TRAINING_STATUS, SAFETY_MEMBER_ROLE "
					+ ",EMP_TOKEN ,SAFETY_COMM_CODE FROM HRMS_SAFETY_COMMITTEE  "
					+ "INNER JOIN HRMS_EMP_OFFC on (HRMS_EMP_OFFC.EMP_ID=HRMS_SAFETY_COMMITTEE.SAFETY_COMM_MEMBER)"
					+ " WHERE  HRMS_SAFETY_COMMITTEE.SAFETY_COMM_CODE = "
					+ bean.getSafetyCommitteeID();

			Object[][] data = getSqlModel().getSingleResult(query);

			bean.setCommitteeType(checkNull(String.valueOf(data[0][0])));
			bean.setEmpToken(checkNull(String.valueOf(data[0][4])));
			bean.setEmpName(checkNull(String.valueOf(data[0][1])));

			if (String.valueOf(data[0][2]).equals("Y")) {
				bean.setTrainingReceivedFlag("true");
			}

			bean.setRoleType(String.valueOf(data[0][3]));

			bean.setSafetyCommitteeID(String.valueOf(data[0][5]));

		} catch (Exception e) {
			System.out.println("Exception in getRecord -- " + e);
		}

	}// End of getRecord
*/
	public void removeUploadFile(String[] srNo, String[] proofName,
			RulesAndPolicyMasterBean bean) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					RulesAndPolicyMasterBean bean1 = new RulesAndPolicyMasterBean();
					bean1.setProofSrNo(String.valueOf(i + 1));
					bean1.setProofName(proofName[i]);
					// bean.setProofFileName(proofFileName[i]);
					tableList.add(bean1);
				}
				tableList.remove(Integer.parseInt(bean
						.getCheckRemoveUpload()) - 1);

			}

			bean.setProofList(tableList);
		} catch (Exception e) {
			logger.error("Exception in removeUploadFile------" + e);
		}

	}
}
