/**
 * 
 */
package org.paradyne.model.helpdesk;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.helpdesk.HelpDeskMgrRep;
import org.paradyne.bean.helpdesk.HelpDeskSLAMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.struts.action.helpdesk.HelpDeskMgrRepAction;

/**
 * @author aa0623
 *
 */
public class HelpDeskMgrRepModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(HelpDeskMgrRepModel.class);
	
	public void addItem(HelpDeskMgrRep mgrRep, String[] srNo,
			String[] managerCode, String[] managerName, String[] reqTypeCode,
			String[] reqType, String[] deptCode, String[] deptName,
			String[] brnCode, String[] brnName, int check) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				HelpDeskMgrRep bean = new HelpDeskMgrRep();
				bean.setSrNo(String.valueOf(i + 1));
				/*bean.setDeptCodeItr(deptCode[i]);
				if (!(deptName[i].equals("null") || deptName[i].equals("")))
					bean.setDeptNameItr(deptName[i]);
				else
					bean.setDeptNameItr("-");*/
				bean.setManagerCodeItr(managerCode[i]);
				if (!(managerName[i].equals("null") || managerName[i].equals("")))
					bean.setManagerNameItr(managerName[i]);
				else
					bean.setManagerNameItr("-");
				bean.setBranchCodeItr(brnCode[i]);
				if (!(brnName[i].equals("null") || brnName[i].equals("")))
					bean.setBranchNameItr(brnName[i]);
				else
					bean.setBranchNameItr("-");
				bean.setReqTypeCodeItr(reqTypeCode[i]);
				if (!(reqType[i].equals("null") || reqType[i].equals("")))
					bean.setReqTypeItr(reqType[i]);
				else
					bean.setReqTypeItr("-");
				
				tableList.add(bean);
			}
		}
		if (check == 1) {
			mgrRep.setSrNo(String.valueOf(tableList.size() + 1));

			mgrRep.setDeptCodeItr(mgrRep.getDeptCode());
			if (!(mgrRep.getDeptName().equals("null") || mgrRep
					.getDeptName().equals("")))
				mgrRep.setDeptNameItr(mgrRep.getDeptName());
			else
				mgrRep.setDeptNameItr("-");
			mgrRep.setManagerCodeItr(mgrRep
					.getManagerCode());
			if (!(mgrRep.getManagerName().equals("null") || mgrRep
					.getManagerName().equals("")))
				mgrRep.setManagerNameItr(mgrRep
						.getManagerName());
			else
				mgrRep.setManagerNameItr("-");
			mgrRep.setBranchCodeItr(mgrRep.getBranchCode());
			if (!(mgrRep.getBranchName().equals("null") || mgrRep
					.getBranchName().equals("")))
				mgrRep.setBranchNameItr(mgrRep
						.getBranchName());
			else
				mgrRep.setBranchNameItr("-");
			mgrRep.setReqTypeCodeItr(mgrRep.getReqTypeCode());
			if (!(mgrRep.getReqType().equals("null") || mgrRep
					.getReqType().equals("")))
				mgrRep.setReqTypeItr(mgrRep
						.getReqType());
			else
				mgrRep.setReqTypeItr("-");
			tableList.add(mgrRep);

		} else if (check == 0) {
			tableList
					.remove(Integer.parseInt(mgrRep.getSubcode()) - 1);
		}
		if (tableList.size() != 0)
			mgrRep.setListLength("1");
		else
			mgrRep.setListLength("0");
		mgrRep.setFilterTableList(tableList);
	}

	public void moditem(HelpDeskMgrRep mgrRep, String[] srNo,
			String[] managerCode, String[] managerName, String[] reqTypeCode,
			String[] reqType, String[] deptCode, String[] deptName,
			String[] brnCode, String[] brnName, int check) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {

				if (i == Integer.parseInt(mgrRep.getHiddenEdit()) - 1) {
					mgrRep.setSrNo(String.valueOf(i + 1));
					
					mgrRep.setDeptCodeItr(mgrRep
							.getDeptCode());
					if (!(mgrRep.getDeptName().equals("null") || mgrRep
							.getDeptName().equals("")))
						mgrRep.setDeptNameItr(mgrRep
								.getDeptName());
					else
						mgrRep.setDeptNameItr("-");
					mgrRep.setManagerCodeItr(mgrRep
							.getManagerCode());
					if (!(mgrRep.getManagerName().equals("null") || mgrRep
							.getManagerName().equals(""))) {
						mgrRep.setManagerNameItr(mgrRep
								.getManagerName());
					} else {
						mgrRep.setManagerNameItr("-");
					}
					mgrRep.setBranchCodeItr(mgrRep
							.getBranchCode());
					if (!(mgrRep.getBranchName().equals("null") || mgrRep
							.getBranchName().equals("")))
						mgrRep.setBranchNameItr(mgrRep
								.getBranchName());
					else
						mgrRep.setBranchNameItr("-");
					mgrRep.setReqTypeCodeItr(mgrRep
							.getReqTypeCode());
					if (!(mgrRep.getReqType().equals("null") || mgrRep
							.getReqType().equals("")))
						mgrRep.setReqTypeItr(mgrRep
								.getReqType());
					else
						mgrRep.setReqTypeItr("-");

					tableList.add(mgrRep);

				} else {
					HelpDeskMgrRep bean = new HelpDeskMgrRep();
					bean.setSrNo(String.valueOf(i + 1));
					
					bean.setDeptCodeItr(deptCode[i]);
					if (!(deptName[i].equals("null") || deptName[i].equals("")))
						bean.setDeptNameItr(deptName[i]);
					else
						bean.setDeptNameItr("-");
					bean.setManagerCodeItr(managerCode[i]);
					if (!(managerName[i].equals("null") || managerName[i].equals("")))
						bean.setManagerNameItr(managerName[i]);
					else
						bean.setManagerNameItr("-");
					bean.setBranchCodeItr(brnCode[i]);
					if (!(brnName[i].equals("null") || brnName[i].equals("")))
						bean.setBranchNameItr(brnName[i]);
					else
						bean.setBranchNameItr("-");
					bean.setReqTypeCodeItr(reqTypeCode[i]);
					if (!(reqType[i].equals("null") || reqType[i].equals("")))
						bean.setReqTypeItr(reqType[i]);
					else
						bean.setReqTypeItr("-");

					tableList.add(bean);
				}

			}
		}
		if (tableList.size() != 0)
			mgrRep.setListLength("1");
		else
			mgrRep.setListLength("0");
		mgrRep.setFilterTableList(tableList);
		
	}

	public boolean checkDuplicate(HelpDeskMgrRep mgrRep, String[] srNo,
			String[] managerCode, String[] managerName, String[] reqTypeCode,
			String[] reqType, String[] deptCode, String[] deptName,
			String[] brnCode, String[] brnName, int check) {
		boolean result = false;
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				/*
				 * if (!workingBenefits.getHiddenEdit().equals("") &&
				 * !workingBenefits.getHiddenEdit().equals(srNo[i])) {
				 */
					if (mgrRep.getManagerCode().equals(managerCode[i])
						&& mgrRep.getReqTypeCode().equals(reqTypeCode[i])
						&& mgrRep.getDeptCode().equals(deptCode[i])
						&& mgrRep.getBranchCode().equals(brnCode[i])
						&& !mgrRep.getHiddenEdit().equals(srNo[i])) {
						result = true;
					} else
						result = false;
				/*
				 * } else result = false;
				 */
			}
		}
		
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				HelpDeskMgrRep bean = new HelpDeskMgrRep();
				bean.setSrNo(String.valueOf(i + 1));

				/*bean.setDeptCodeItr(deptCode[i]);
				if (!(deptName[i].equals("null") || deptName[i].equals("")))
					bean.setDeptNameItr(deptName[i]);
				else
					bean.setDeptNameItr("-");*/
				bean.setManagerCodeItr(managerCode[i]);
				if (!(managerName[i].equals("null") || managerName[i].equals("")))
					bean.setManagerNameItr(managerName[i]);
				else
					bean.setManagerNameItr("-");
				bean.setBranchCodeItr(brnCode[i]);
				if (!(brnName[i].equals("null") || brnName[i].equals("")))
					bean.setBranchNameItr(brnName[i]);
				else
					bean.setBranchNameItr("-");
				bean.setReqTypeCodeItr(reqTypeCode[i]);
				if (!(reqType[i].equals("null") || reqType[i].equals("")))
					bean.setReqTypeItr(reqType[i]);
				else
					bean.setReqTypeItr("-");
				tableList.add(bean);
			}
			if (tableList.size() != 0)
				mgrRep.setListLength("1");
			else
				mgrRep.setListLength("0");
			mgrRep.setFilterTableList(tableList);
		}

		return result;
	}// end checkDuplicate method

	public void getAllBranches(HelpDeskMgrRep mgrRep) {
		try {
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER "
				+ " WHERE IS_ACTIVE='Y'"	
				+ " ORDER BY  CENTER_ID ";
			Object[][] data = getSqlModel().getSingleResult(query);
			
			if (data != null && data.length > 0) {
				ArrayList<Object> allBranchList = new ArrayList<Object>();
				for (int i = 0; i < data.length; i++) {
					HelpDeskMgrRep branch1 = new HelpDeskMgrRep();

					branch1.setBranchNameItt(String.valueOf(data[i][0]));
					branch1.setBranchCodeItt(String.valueOf(data[i][1]));
					allBranchList.add(branch1);
				}
				mgrRep.setBranchList(allBranchList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void getList(HelpDeskMgrRep mgrRep, HttpServletRequest request) {
		try {
			String query = " SELECT DISTINCT MANAGER_CODE, EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
						+ " REQ_TYPE_CODE, REQUEST_TYPE_NAME, DEPT_CODE, NVL(DEPT_NAME,' ')"
						+ " FROM HELPDESK_MGRREPORTING_HDR "
						+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HELPDESK_MGRREPORTING_HDR.MANAGER_CODE)"
						+ " INNER JOIN HELPDESK_REQUEST_TYPE ON (HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID = HELPDESK_MGRREPORTING_HDR.REQ_TYPE_CODE)"
						+ " INNER JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE= HELPDESK_MGRREPORTING_HDR.DEPT_CODE)";
			
			Object[][] listDataObj = getSqlModel().getSingleResult(query);
			String[] pageIndex = Utility.doPaging(mgrRep.getMyPage(), listDataObj.length, 20);	
			
			if(pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			
			if(pageIndex[4].equals("1")) {
				mgrRep.setMyPage("1");
			}
			
			ArrayList<Object> recordsList = new ArrayList<Object>();
			
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				HelpDeskMgrRep bean1 = new HelpDeskMgrRep();
				bean1.setManagerCodeItr(String.valueOf(listDataObj[i][0])); //mgr Id
				bean1.setManagerNameItr(String.valueOf(listDataObj[i][2])); //name
				bean1.setReqTypeCodeItr(String.valueOf(listDataObj[i][3]));//req type code 
				bean1.setReqTypeItr(String.valueOf(listDataObj[i][4]));// req type name
				bean1.setDeptCodeItr(String.valueOf(listDataObj[i][5]));// dept code
				bean1.setDeptNameItr(String.valueOf(listDataObj[i][6]));// dept name
				recordsList.add(bean1);
			}
			mgrRep.setAllRecordsList(recordsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void getRecordByManagerCode(HelpDeskMgrRep mgrRep, String managerId, String deptId, String reqCode) {
		try {
			String query = " SELECT MANAGER_CODE, NVL(EMP_TOKEN,' '), HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ " NVL(CENTER_NAME,' '), BRANCH_CODE, REQ_TYPE_CODE, NVL(REQUEST_TYPE_NAME,' '),HELPDESK_MGRREPORTING_HDR.DEPT_CODE, NVL(HELPDESK_DEPT.DEPT_NAME,' ')"
					+ " FROM HELPDESK_MGRREPORTING_HDR"
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HELPDESK_MGRREPORTING_HDR.MANAGER_CODE)"
					+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HELPDESK_MGRREPORTING_HDR.BRANCH_CODE)"
					+ " INNER JOIN HELPDESK_REQUEST_TYPE ON (HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID = HELPDESK_MGRREPORTING_HDR.REQ_TYPE_CODE)"
					+ "  INNER JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_MGRREPORTING_HDR.DEPT_CODE) "
					+ " WHERE MANAGER_CODE = " 
					+ managerId
					+ " AND HELPDESK_MGRREPORTING_HDR.DEPT_CODE = "+deptId
					+ " AND REQ_TYPE_CODE = "+reqCode;
					
					
			Object[][] dataObj = getSqlModel().getSingleResult(query);
			if(dataObj != null && dataObj.length > 0){
				mgrRep.setManagerCode(String.valueOf(dataObj[0][0])); //mgr Id
				mgrRep.setManagerName(String.valueOf(dataObj[0][2])); //name
				mgrRep.setBranchName(String.valueOf(dataObj[0][3]));//branch name
				mgrRep.setBranchCode(String.valueOf(dataObj[0][4]));//branch code
				mgrRep.setReqTypeCode(String.valueOf(dataObj[0][5]));//req type code 
				mgrRep.setReqType(String.valueOf(dataObj[0][6]));//req type name
				mgrRep.setDeptCode(String.valueOf(dataObj[0][7]));// dept code
				mgrRep.setDeptName(String.valueOf(dataObj[0][8]));// dept name
			}
			
			String allBranchQuery = " SELECT CENTER_NAME,CENTER_ID FROM HRMS_CENTER "
				+ " WHERE IS_ACTIVE='Y'"	
				+ " ORDER BY  CENTER_ID ";
			
			Object[][] allData = getSqlModel().getSingleResult(allBranchQuery);
			
			String branchQuery = " SELECT CENTER_NAME, BRANCH_CODE FROM HELPDESK_MGRREPORTING_HDR "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HELPDESK_MGRREPORTING_HDR.BRANCH_CODE) " 
				+ " WHERE MANAGER_CODE = "
				+managerId
				+ " AND REQ_TYPE_CODE = "
				+reqCode
				+ " AND DEPT_CODE = "
				+deptId
				+ " ORDER BY  BRANCH_CODE ";
			
			Object[][] data = getSqlModel().getSingleResult(branchQuery);
		
		if (allData != null && allData.length > 0) {
			ArrayList<Object> allBranchList = new ArrayList<Object>();
				for (int i = 0; i < allData.length; i++) {
					HelpDeskMgrRep branch1 = new HelpDeskMgrRep();

					branch1.setBranchNameItt(String.valueOf(allData[i][0]));
					branch1.setBranchCodeItt(String.valueOf(allData[i][1]));
					//branch1.setBranchNameItt(String.valueOf(data[i][0]));
					//branch1.setBranchCodeItt(String.valueOf(data[i][1]));
					if (data != null && data.length > 0) {
						for (int j = 0; j < data.length; j++) {
							if (String.valueOf(data[j][1]).equals(String.valueOf(allData[i][1]))) {
								branch1.setSavedBranchItt("Y");
								branch1.setSelectedCode(String.valueOf(allData[i][1]));
							} 
						}
					}
					allBranchList.add(branch1);
			}
			mgrRep.setBranchList(allBranchList);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean saveRecord(HelpDeskMgrRep mgrRep, String[] branchCodeValues) {
		boolean result = false;
		try {
			String queryDel = "DELETE FROM HELPDESK_MGRREPORTING_HDR WHERE DEPT_CODE = "
				+mgrRep.getDeptCode() 
				+ " AND REQ_TYPE_CODE = "
				+ mgrRep.getReqTypeCode();
			getSqlModel().singleExecute(queryDel);
		
			if(branchCodeValues != null && branchCodeValues.length > 0){
				
				for (int i = 0; i < branchCodeValues.length; i++) {
					Object insertObj[][] = new Object[1][4];
					insertObj[0][0] = mgrRep.getDeptCode();
					insertObj[0][1] = mgrRep.getManagerCode();
					insertObj[0][2] = String.valueOf(branchCodeValues[i]);
					insertObj[0][3] = mgrRep.getReqTypeCode();
					
					String insertQuery = "INSERT INTO HELPDESK_MGRREPORTING_HDR(DEPT_CODE, MANAGER_CODE, BRANCH_CODE, REQ_TYPE_CODE) "
							+ " VALUES (?,?,?,?)";
						result = getSqlModel().singleExecute(insertQuery, insertObj);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return result;
	}

	public boolean deleteCheckedRecords(HelpDeskMgrRep mgrRep, String[] code,
			String[] deptId, String[] reqTypeId) {
		boolean result=false;
		try {
			
			for (int i = 0; i < code.length; i++) {
				String queryDel = "DELETE FROM HELPDESK_MGRREPORTING_HDR WHERE MANAGER_CODE = "
					+String.valueOf(code[i])
					+ " AND DEPT_CODE = "
					+String.valueOf(deptId[i])
					+ " AND REQ_TYPE_CODE = "
					+String.valueOf(reqTypeId[i]);
				result=getSqlModel().singleExecute(queryDel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteRecord(HelpDeskMgrRep mgrRep) {
		boolean result=false;
		Object[][] mgrAssReq=null;
		try {
			String selQuery= "SELECT REQUEST_PENDING_WITH from HELPDESK_REQUEST_HDR WHERE " +  
							 " REQUEST_PENDING_WITH in (SELECT MANAGER_CODE FROM HELPDESK_MGRREPORTING_HDR where MANAGER_CODE ="+mgrRep.getManagerCode()+")";
			 mgrAssReq = getSqlModel().getSingleResult(selQuery);
			if(mgrAssReq!=null && mgrAssReq.length>0){
				String queryDel = "DELETE FROM HELPDESK_MGRREPORTING_HDR WHERE MANAGER_CODE = "
					+mgrRep.getManagerCode()
					+ " AND DEPT_CODE = "
					+mgrRep.getDeptCode()
					+ " AND REQ_TYPE_CODE = "
					+mgrRep.getReqTypeCode();
				result=getSqlModel().singleExecute(queryDel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

