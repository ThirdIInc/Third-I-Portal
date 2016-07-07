package org.paradyne.model.common;

import java.awt.Color;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.common.ProfileAccess;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * @author reebaj
 * @since 02/06/2008
 * 
 */

public class ProfileAccessModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	public ProfileAccessModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * GENERATE LIST FOR CENTER
	 * 
	 * @param profileAccess
	 * @param chk
	 * @return String List generated
	 */

	public String[][] getCenterList(ProfileAccess profileAccess, String chk) {
		// TODO Auto-generated method stub
		Object[][] centObj = getSqlModel().getSingleResult(getQuery(1));
		Object[] profCode = new Object[1];
		profCode[0] = String.valueOf(profileAccess.getProfileId());
		Object[][] centProfObj = getSqlModel().getSingleResult(getQuery(15),
				profCode);
		String[][] centList;
		if (centObj.length > 0) {
			centList = new String[centObj.length][4];
			for (int i = 0; i < centObj.length; i++) {
				centList[i][0] = String.valueOf(centObj[i][0]);
				centList[i][1] = String.valueOf(centObj[i][1]);
				centList[i][2] = "false";
				logger.info("profile id-----------------"
						+ String.valueOf(profileAccess.getProfileId()));
				/** FLAG SETTINGS _ CHECKING ALL FLAGS UNDER CENTER */
				if (chk.equals("A")) {
					centList[i][2] = "true";
					centList[i][3] = "true";
				}// end of nested if
				else {
					if (!String.valueOf(profileAccess.getProfileId())
							.equals("")) {
						for (int j = 0; j < centProfObj.length; j++) {
							if (String.valueOf(centProfObj[j][1]).equals(
									centList[i][0])) {
								centList[i][2] = "true";
							}// end of if
						}// end of FOR loop
					} // end of nested if
					else {
						centList[i][2] = "false";
					}// end of else
					centList[i][3] = "false";
				}// end of else
			}// end of loop
		}// end of if
		else {
			return centList = new String[0][0];
		}// end of else
		return centList;

	}

	/**
	 * GENERATE LIST FOR DEPARTMENT
	 * 
	 * @param profileAccess
	 * @param chk
	 * @return String List generated
	 */

	public String[][] generateDeptList(ProfileAccess profileAccess, String chk) {
		// TODO Auto-generated method stub
		Object[][] deptObj = getSqlModel().getSingleResult(getQuery(2));
		Object[] profCode = new Object[1];
		profCode[0] = String.valueOf(profileAccess.getProfileId());
		Object[][] deptProfObj = getSqlModel().getSingleResult(getQuery(16),
				profCode);
		String[][] deptList;
		if (deptObj.length > 0) {
			deptList = new String[deptObj.length][4];
			for (int i = 0; i < deptObj.length; i++) {
				deptList[i][0] = String.valueOf(deptObj[i][0]);
				deptList[i][1] = String.valueOf(deptObj[i][1]);
				deptList[i][2] = "false";
				logger.info("profile id-----------------"
						+ String.valueOf(profileAccess.getProfileId()));
				/** FLAG SETTINGS _ CHECKING ALL FLAGS UNDER DEPARTMENT */
				if (chk.equals("A")) {
					deptList[i][2] = "true";
					deptList[i][3] = "true";
				}// end of nested if
				else {
					if (!String.valueOf(profileAccess.getProfileId())
							.equals("")) {
						for (int j = 0; j < deptProfObj.length; j++) {
							if (String.valueOf(deptProfObj[j][1]).equals(
									deptList[i][0])) {
								deptList[i][2] = "true";
							}// end of if
						}// end of FOR loop
					} // end of nested if
					else {
						deptList[i][2] = "false";
					}// end of else
					deptList[i][3] = "false";
				}// end of else
			}// end of loop
		} // end of if
		else {
			return deptList = new String[0][0];
		}// end of else
		return deptList;
	}

	/**
	 * GENERATE LIST FOR DIVISION
	 * 
	 * @param profileAccess
	 * @param chk
	 * @return String List generated
	 */

	public String[][] generateDivList(ProfileAccess profileAccess, String chk) {
		// TODO Auto-generated method stub
		Object[][] divObj = getSqlModel().getSingleResult(getQuery(3));
		Object[] profCode = new Object[1];
		profCode[0] = String.valueOf(profileAccess.getProfileId());
		Object[][] divProfObj = getSqlModel().getSingleResult(getQuery(17),
				profCode);
		String[][] divList;
		if (divObj.length > 0) {
			divList = new String[divObj.length][4];
			for (int i = 0; i < divObj.length; i++) {
				divList[i][0] = String.valueOf(divObj[i][0]);
				divList[i][1] = String.valueOf(divObj[i][1]);
				divList[i][2] = "false";
				logger.info("profile id-----------------"
						+ String.valueOf(profileAccess.getProfileId()));

				/** FLAG SETTINGS _ CHECKING ALL FLAGS UNDER DIVISION */
				if (chk.equals("A")) {
					divList[i][2] = "true";
					divList[i][3] = "true";
				}// end of nested if
				else {
					if (!String.valueOf(profileAccess.getProfileId())
							.equals("")) {
						for (int j = 0; j < divProfObj.length; j++) {
							if (String.valueOf(divProfObj[j][1]).equals(
									divList[i][0])) {
								divList[i][2] = "true";
							}// end of if
						}// end of FOR loop
					}// end of nested if
					else {
						divList[i][2] = "false";
					}// end of else
					divList[i][3] = "false";
				}// end of else

			}// end of loop
		} // end of if
		else {
			return divList = new String[0][0];
		}// end of else

		return divList;
	}

	/**
	 * GENERATE LIST FOR EMPLOYEE TYPE
	 * 
	 * @param profileAccess
	 * @param chk
	 * @return String List generated
	 */

	public String[][] generateEmpTypeList(ProfileAccess profileAccess,
			String chk) {
		// TODO Auto-generated method stub
		Object[][] empTypeObj = getSqlModel().getSingleResult(getQuery(4));
		Object[] profCode = new Object[1];
		profCode[0] = String.valueOf(profileAccess.getProfileId());
		Object[][] empTypeProfObj = getSqlModel().getSingleResult(getQuery(18),
				profCode);
		String[][] empTypeList;
		if (empTypeObj.length > 0) {
			empTypeList = new String[empTypeObj.length][4];
			for (int i = 0; i < empTypeObj.length; i++) {
				empTypeList[i][0] = String.valueOf(empTypeObj[i][0]);
				empTypeList[i][1] = String.valueOf(empTypeObj[i][1]);
				empTypeList[i][2] = "false";
				logger.info("profile id-----------------"
						+ String.valueOf(profileAccess.getProfileId()));

				/** FLAG SETTINGS _ CHECKING ALL FLAGS UNDER EMPLOYEE TYPE */
				if (chk.equals("A")) {
					empTypeList[i][2] = "true";
					empTypeList[i][3] = "true";
				}// end of nested if
				else {
					if (!String.valueOf(profileAccess.getProfileId())
							.equals("")) {
						for (int j = 0; j < empTypeProfObj.length; j++) {
							if (String.valueOf(empTypeProfObj[j][1]).equals(
									empTypeList[i][0])) {
								empTypeList[i][2] = "true";
							}// end of nested if
						}// end of FOR loop
					}// end of nested if
					else {
						empTypeList[i][2] = "false";
					}// end of else
					empTypeList[i][3] = "false";
				}// end of else
			}// end of loop
		}// end of if
		else {
			return empTypeList = new String[0][0];
		}// end of else
		return empTypeList;
	}

	/**
	 * FOR CREATE NEW PROFILE or UPDATE PROFILE
	 * 
	 * @param OBJECT
	 *            OF ProfileAccess
	 */
	public boolean addProfile(ProfileAccess profileAccess) {
		// TODO Auto-generated method stub
		boolean isProfileAdd = false;
		/* FOR ALL FLAGS CONDITION */
		String divflag = "N";
		String cntflag = "N";
		String dptflag = "N";
		String empflag = "N";

		if (profileAccess.getHiddenDivChk().equals("divOn")) {
			divflag = "Y";

		}// end of if
		if (profileAccess.getHiddenCntChk().equals("cntOn")) {
			cntflag = "Y";

		}// end of if
		if (profileAccess.getHiddenDptChk().equals("dptOn")) {
			dptflag = "Y";

		}// end of if
		if (profileAccess.getHiddenChk().equals("empOn")) {
			empflag = "Y";

		}// end of if

		/* UPDATE QUERY */
		String query = " UPDATE HRMS_PROFILE_ACC_HDR SET PROFILE_NAME =?, UPDATION_DATE=SYSDATE , "
				+ " PROFILE_DIV_FLAG='"
				+ divflag
				+ "', PROFILE_CNT_FLAG='"
				+ cntflag
				+ "', PROFILE_DEPT_FLAG='"
				+ dptflag
				+ "',PROFILE_EMPTYPE_FLAG='"
				+ empflag
				+ "' WHERE PROFILE_CODE = ?";

		if (profileAccess.getProfileId().equals("")
				|| profileAccess.getProfileId() == null) { // check for add or
			// update profile
			/**
			 * INSERT INTO PROFILE HEADER
			 */

			Object[][] profObj = new Object[1][5];
			profObj[0][0] = profileAccess.getProfile();
			profObj[0][1] = divflag;
			profObj[0][2] = dptflag;
			profObj[0][3] = cntflag;
			profObj[0][4] = empflag;
			isProfileAdd = getSqlModel().singleExecute(getQuery(5), profObj);
			String code = " SELECT MAX(PROFILE_CODE) FROM HRMS_PROFILE_ACC_HDR ";
			Object maxcode[][] = getSqlModel().getSingleResult(code);
			profileAccess.setProfileId(String.valueOf(maxcode[0][0]));
		} // end of if
		else {
			isProfileAdd = true;
			/**
			 * UPDATE PROFILE HEADER
			 */
			Object[][] updateProf = new Object[1][2];
			updateProf[0][0] = profileAccess.getProfile();
			updateProf[0][1] = profileAccess.getProfileId();
			getSqlModel().singleExecute(query, updateProf);
			return isProfileAdd;
		}// end of else
		return isProfileAdd;

	}

	/**
	 * CREATE PROFILE FOR CENTER
	 * 
	 * @param profileAccess
	 * @param cntFlag
	 * @return boolean
	 */
	public boolean createCntProfile(ProfileAccess profileAccess,
			String[] cntFlag) {
		// TODO Auto-generated method stub

		boolean isCenterAdd = true;
		Object[][] delObject = new Object[1][1];
		delObject[0][0] = profileAccess.getProfileId();
		/**
		 * INSERT RECORD INTO CENTER PROFILE
		 */

		if (cntFlag.length > 0 && cntFlag != null) {
			Object[][] centObj = new Object[cntFlag.length][1];
			for (int i = 0; i < centObj.length; i++) {
				centObj[i][0] = cntFlag[i];

			}// end of loop
			/**
			 * DELETE FROM CENTER PROFILE
			 */
			getSqlModel().singleExecute(getQuery(23), delObject);
			isCenterAdd = getSqlModel().singleExecute(getQuery(6), centObj);

		}// end of if
		return isCenterAdd;
	}

	/**
	 * CREATE PROFILE FOR DEPARTMENT
	 * 
	 * @param profileAccess
	 * @param deptFlag
	 * @return
	 */
	public boolean createDeptProfile(ProfileAccess profileAccess,
			String[] deptFlag) {
		// TODO Auto-generated method stub
		boolean isDeptAdd = true;
		Object[][] delObject = new Object[1][1];
		delObject[0][0] = profileAccess.getProfileId();
		/**
		 * INSERT INTO DEPARTMENT PROFILE
		 */
		if (deptFlag.length > 0 && deptFlag != null) {
			Object[][] deptObj = new Object[deptFlag.length][1];
			for (int i = 0; i < deptObj.length; i++) {
				deptObj[i][0] = deptFlag[i];

			}// end of loop
			/**
			 * DELETE FROM DEPARTMENT PROFILE
			 */
			getSqlModel().singleExecute(getQuery(24), delObject);
			isDeptAdd = getSqlModel().singleExecute(getQuery(7), deptObj);

		}// end of if
		return isDeptAdd;
	}

	/**
	 * CREATE PROFILE FOR DIVISION
	 * 
	 * @param profileAccess
	 * @param divisionFlag
	 * @return
	 */
	public boolean createDivProfile(ProfileAccess profileAccess,
			String[] divisionFlag) {
		// TODO Auto-generated method stub
		boolean isDivAdd = true;
		Object[][] delObject = new Object[1][1];
		delObject[0][0] = profileAccess.getProfileId();
		/**
		 * INSERT INTO DIVISION PROFILE
		 */
		if (divisionFlag.length > 0 && divisionFlag != null) {
			Object[][] divObj = new Object[divisionFlag.length][2];
			for (int i = 0; i < divObj.length; i++) {
				divObj[i][0] = profileAccess.getProfileId();
				divObj[i][1] = divisionFlag[i];

			}// end of loop
			/**
			 * DELETE FROM DIVISION PROFILE
			 */
			getSqlModel().singleExecute(getQuery(25), delObject);
			isDivAdd = getSqlModel().singleExecute(getQuery(8), divObj);

		}// end of if
		return isDivAdd;
	}

	/**
	 * CREATE PROFILE FOR EMPLOYEE TYPE
	 * 
	 * @param profileAccess
	 * @param emptypeFlag
	 * @return
	 */
	public boolean createEmpProfile(ProfileAccess profileAccess,
			String[] emptypeFlag) {
		// TODO Auto-generated method stub
		boolean isEmptype = true;
		Object[][] delObject = new Object[1][1];
		delObject[0][0] = profileAccess.getProfileId();
		/**
		 * INSERT INTO EMPTYPE PROFILE
		 */
		if (emptypeFlag.length > 0 && emptypeFlag != null) {
			Object[][] emptypeObj = new Object[emptypeFlag.length][1];
			for (int i = 0; i < emptypeObj.length; i++) {
				emptypeObj[i][0] = emptypeFlag[i];

			}// end of loop
			/**
			 * DELETE FROM EMPLOYEE TYPE PROFILE
			 */
			getSqlModel().singleExecute(getQuery(26), delObject);
			isEmptype = getSqlModel().singleExecute(getQuery(9), emptypeObj);
		}// end of if

		return isEmptype;
	}

	/**
	 * SELECTING ALL FLAGS FROM HEADER (F9/GENERATING LIST)
	 * 
	 * @param profileAccess
	 * @return Object flagObj
	 */
	public Object[][] getFlags(ProfileAccess profileAccess) {
		// TODO Auto-generated method stub
		Object[][] flagObj = null;
		String query = " SELECT PROFILE_DIV_FLAG, PROFILE_DEPT_FLAG, PROFILE_CNT_FLAG, PROFILE_EMPTYPE_FLAG "
				+ " FROM HRMS_PROFILE_ACC_HDR WHERE PROFILE_CODE = "
				+ profileAccess.getProfileId();
		flagObj = getSqlModel().getSingleResult(query);
		return flagObj;
	}

	/* ************* REPORT GENERATION ************* */

	/**
	 * FOR CENTER
	 * 
	 * @param profileAccess
	 */
	public void getProfileCenters(ProfileAccess profileAccess) {
		// TODO Auto-generated method stub
		logger.info("cnttttttt________________");
		String centQuery = " SELECT PROFILE_CNT_FLAG FROM HRMS_PROFILE_ACC_HDR WHERE PROFILE_CODE = "
				+ profileAccess.getProfileId();
		Object[][] centObj = getSqlModel().getSingleResult(centQuery);
		logger.info("cnttttttt" + String.valueOf(centObj[0][0]));
		String query = " SELECT HRMS_CENTER.CENTER_ID,HRMS_CENTER.CENTER_NAME "
				+ " FROM HRMS_CENTER "
				+ " LEFT JOIN HRMS_PROFILE_CENTER ON(HRMS_PROFILE_CENTER.CENTER_CODE=HRMS_CENTER.CENTER_ID) ";

		if (String.valueOf(centObj[0][0]).equals("N")) {
			query += " WHERE HRMS_PROFILE_CENTER.PROFILE_CODE= "
					+ profileAccess.getProfileId()
					+ " ORDER BY HRMS_CENTER.CENTER_ID ";
		}// end of if

		Object[][] obj = getSqlModel().getSingleResult(query);
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < obj.length; i++) {

			ProfileAccess prfBean = new ProfileAccess();
			if (obj.length > 0) {

				prfBean.setCenterId(String.valueOf(obj[i][0]));
				prfBean.setCenterName(String.valueOf(obj[i][1]));
				list.add(prfBean);
			}// end of if

		}// end of loop
		profileAccess.setCenterList(list);
		if (list.size() == 0) {
			profileAccess.setNoData("true");
		}// end of if
	}

	/**
	 * FOR DEPARTMENT
	 * 
	 * @param profileAccess
	 */
	public void getProfileDeparts(ProfileAccess profileAccess) {
		// TODO Auto-generated method stub
		String deptQuery = " SELECT PROFILE_DEPT_FLAG FROM HRMS_PROFILE_ACC_HDR WHERE PROFILE_CODE = "
				+ profileAccess.getProfileId();
		Object[][] deptObj = getSqlModel().getSingleResult(deptQuery);

		String query = "SELECT DISTINCT HRMS_DEPT.DEPT_ID,HRMS_DEPT.DEPT_NAME "
				+ " FROM HRMS_DEPT "
				+ " LEFT JOIN HRMS_PROFILE_DEPT ON(HRMS_PROFILE_DEPT.DEPT_CODE=HRMS_DEPT.DEPT_ID) ";
		if (String.valueOf(deptObj[0][0]).equals("N")) {
			query += " WHERE HRMS_PROFILE_DEPT.PROFILE_CODE= "
					+ profileAccess.getProfileId()
					+ " ORDER BY HRMS_DEPT.DEPT_ID ";
		}// end of if

		Object[][] obj = getSqlModel().getSingleResult(query);
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < obj.length; i++) {

			ProfileAccess prfBean = new ProfileAccess();
			if (obj.length > 0) {

				prfBean.setDeptId(String.valueOf(obj[i][0]));
				prfBean.setDeptName(String.valueOf(obj[i][1]));
				list.add(prfBean);
			}// end of if

		}// end of loop
		profileAccess.setDeptList(list);
		if (list.size() == 0) {
			profileAccess.setNodptData("true");
		}// end of if
	}

	/**
	 * FOR DIVISION
	 * 
	 * @param profileAccess
	 */
	public void getProfileDivisions(ProfileAccess profileAccess) {
		// TODO Auto-generated method stub
		String divQuery = " SELECT PROFILE_DIV_FLAG FROM HRMS_PROFILE_ACC_HDR WHERE PROFILE_CODE = "
				+ profileAccess.getProfileId();
		Object[][] divObj = getSqlModel().getSingleResult(divQuery);

		String query = " SELECT DISTINCT HRMS_DIVISION.DIV_ID,HRMS_DIVISION.DIV_NAME "
				+ " FROM HRMS_DIVISION "
				+ " LEFT JOIN HRMS_PROFILE_DIVISION ON(HRMS_PROFILE_DIVISION.DIV_CODE=HRMS_DIVISION.DIV_ID) ";

		if (String.valueOf(divObj[0][0]).equals("N")) {
			query += " WHERE HRMS_PROFILE_DIVISION.PROFILE_CODE= "
					+ profileAccess.getProfileId()
					+ " ORDER BY HRMS_DIVISION.DIV_ID  ";
		}// end of if
		Object[][] obj = getSqlModel().getSingleResult(query);
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < obj.length; i++) {

			ProfileAccess prfBean = new ProfileAccess();
			if (obj.length > 0) {

				prfBean.setDivId(String.valueOf(obj[i][0]));
				prfBean.setDivName(String.valueOf(obj[i][1]));
				list.add(prfBean);
			}// end of if

		}// end of loop
		profileAccess.setDivList(list);
		if (list.size() == 0) {
			profileAccess.setNodivData("true");
		}// end of if
	}

	/**
	 * FOR EMPLOYEE TYPE
	 * 
	 * @param profileAccess
	 */
	public void getProfileEmpTypes(ProfileAccess profileAccess) {
		// TODO Auto-generated method stub
		String empQuery = " SELECT PROFILE_EMPTYPE_FLAG FROM HRMS_PROFILE_ACC_HDR WHERE PROFILE_CODE = "
				+ profileAccess.getProfileId();
		Object[][] empObj = getSqlModel().getSingleResult(empQuery);

		String query = "SELECT DISTINCT HRMS_EMP_TYPE.TYPE_ID	,HRMS_EMP_TYPE.TYPE_NAME "
				+ " FROM HRMS_EMP_TYPE "
				+ " LEFT JOIN HRMS_PROFILE_EMPTYPE ON(HRMS_PROFILE_EMPTYPE.PROFILE_EMPTYPE=HRMS_EMP_TYPE.TYPE_ID) ";
		if (String.valueOf(empObj[0][0]).equals("N")) {
			query += " WHERE HRMS_PROFILE_EMPTYPE.PROFILE_CODE= "
					+ profileAccess.getProfileId()
					+ " ORDER BY HRMS_EMP_TYPE.TYPE_ID ";
		}// end of if
		Object[][] obj = getSqlModel().getSingleResult(query);
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < obj.length; i++) {

			ProfileAccess prfBean = new ProfileAccess();
			if (obj.length > 0) {

				prfBean.setEmptypeId(String.valueOf(obj[i][0]));
				prfBean.setEmptype(String.valueOf(obj[i][1]));
				list.add(prfBean);
			}// end of if

		}// end of loop
		profileAccess.setEmpTypeList(list);
		if (list.size() == 0) {
			profileAccess.setNoempData("true");
		}// end of if
	}

	public void getAccessProfileList(ProfileAccess profileAccess,
			HttpServletRequest request) {

		String query = "SELECT PROFILE_CODE, PROFILE_NAME FROM HRMS_PROFILE_ACC_HDR "
				+ "	ORDER BY PROFILE_CODE DESC";

		Object[][] repData = getSqlModel().getSingleResult(query);
		if (repData != null && repData.length > 0) {
			profileAccess.setModeLength("true");
			profileAccess.setTotalRecords(String.valueOf(repData.length));
			String[] pageIndex = Utility.doPaging(profileAccess.getMyPage(),
					repData.length, 20);
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
				profileAccess.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				ProfileAccess bean1 = new ProfileAccess();
				bean1.setProfileId(checkNull(String.valueOf(repData[i][0])));
				bean1.setProfileName(checkNull(String.valueOf(repData[i][1])));

				// Query to get division details start
				String divNameQuery = " SELECT PROFILE_CODE, DIV_CODE ,NVL(DIV_NAME,' ')"
						+ " FROM HRMS_PROFILE_DIVISION "
						+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_PROFILE_DIVISION.DIV_CODE)"
						+ " WHERE PROFILE_CODE=" + repData[i][0];

				Object[][] divisionDetail = getSqlModel().getSingleResult(
						divNameQuery);
				int count = 1;
				String div = "";
				if (divisionDetail.length > 0) {
					for (int j = 0; j < divisionDetail.length; j++) {

						div += count+")"+String.valueOf(divisionDetail[j][2]) + " \n";
						count++;
					}
					div = div.substring(0, div.length() - 1);
				}
				bean1.setDivisionName(String.valueOf(div)); // Division
				// Query to get division details end
				
				// Query to get Branch details start
				String branchNameQuery = " SELECT PROFILE_CODE, CENTER_CODE ,NVL(CENTER_NAME,' ')"
						+ " FROM HRMS_PROFILE_CENTER "
						+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_PROFILE_CENTER.CENTER_CODE)"
						+ " WHERE PROFILE_CODE=" + repData[i][0];

				Object[][] branchDetail = getSqlModel().getSingleResult(
						branchNameQuery);
				int countBr = 1;
				String branchName = "";
				if (branchDetail.length > 0) {
					for (int j = 0; j < branchDetail.length; j++) {

						branchName += countBr+")"+String.valueOf(branchDetail[j][2])
								+  " \n";
						countBr++;
					}
					branchName = branchName.substring(0, branchName
							.length() - 1);
				}
				bean1.setBranchName(String.valueOf(branchName)); // Division
				// Query to get Branch details end

				// Query to get Paybill details start
				String paybillNameQuery = " SELECT PROFILE_CODE, PAYBILL_NO ,NVL(PAYBILL_GROUP,' ')"
						+ " FROM HRMS_PROFILE_PAYBILL "
						+ " INNER JOIN HRMS_PAYBILL ON (HRMS_PAYBILL.PAYBILL_ID = HRMS_PROFILE_PAYBILL.PAYBILL_NO)"
						+ " WHERE PROFILE_CODE=" + repData[i][0];

				Object[][] paybillDetail = getSqlModel().getSingleResult(
						paybillNameQuery);
				int countPb = 1;
				String paybillName = "";
				if (paybillDetail.length > 0) {
					for (int j = 0; j < paybillDetail.length; j++) {

						paybillName +=  countPb+")"+String.valueOf(paybillDetail[j][2])
								+  " \n";
						countPb++;
					}
					paybillName = paybillName.substring(0, paybillName
							.length() - 1);
				}
				bean1.setPaybillName(String.valueOf(paybillName)); // Division
				
				
				/*
				 * //Get Division Details From HRMS_PROFILE_DIVISION Start
				 * String divQuery = "SELECT DIV_CODE,DIV_NAME,PROFILE_CODE FROM
				 * HRMS_PROFILE_DIVISION " + "INNER JOIN HRMS_DIVISION
				 * ON(HRMS_DIVISION.DIV_ID = HRMS_PROFILE_DIVISION.DIV_CODE)";
				 * Object[][] divObj = getSqlModel().getSingleResult(divQuery);
				 * 
				 * String divCode = ""; String divName = "";
				 * 
				 * 
				 * System.out.println("divObj.length==" + divObj.length);
				 * String[] abbr = String.valueOf(repData[i][0]).split(",");
				 * System.out.println("abbr===" + abbr.length); if(abbr.length
				 * >0 ){
				 * 
				 * for (int j = 0; j < abbr.length; j++) { for (int j2 = 0; j2 <
				 * divObj.length; j2++) { if (String.valueOf(abbr[j]).equals(
				 * String.valueOf(divObj[j2][2]))) {
				 * System.out.println("String.valueOf(obj[j2][1])=" +
				 * String.valueOf(divObj[j2][0])); divCode +=
				 * String.valueOf(divObj[j2][0]) + ",";
				 * System.out.println("divCode=" + divCode);
				 * 
				 * divName += String.valueOf(divObj[j2][1]) + ",";
				 * System.out.println("divName=" + divName);
				 *  }
				 *  }
				 *  } divCode = divCode.substring(0, divCode.length() - 1);
				 * System.out.println("divCode==== " + divCode);
				 * 
				 * divName = divName.substring(0, divName.length() - 1);
				 * System.out.println("divName==== " + divName); }
				 * bean1.setDivisionCode(divCode); // division Code
				 * bean1.setDivisionName(divName); // division Name
				 * 
				 * //Get Division Details From HRMS_PROFILE_DIVISION end
				 * 
				 * //Get Branch Details From HRMS_PROFILE_CENTER Start String
				 * branchQuery = "SELECT CENTER_CODE,CENTER_NAME,PROFILE_CODE
				 * FROM HRMS_PROFILE_CENTER " + "INNER JOIN HRMS_CENTER
				 * ON(HRMS_CENTER.CENTER_ID = HRMS_PROFILE_CENTER.CENTER_CODE)";
				 * Object[][] branchObj =
				 * getSqlModel().getSingleResult(branchQuery);
				 * 
				 * String branchCode = ""; String branchName = "";
				 * 
				 * String[] branch = String.valueOf(repData[i][0]).split(",");
				 * System.out.println("branch===" + branch.length);
				 * 
				 * if(branchObj.length >0 ){ for (int j = 0; j < branch.length;
				 * j++) { for (int j2 = 0; j2 < branchObj.length; j2++) { if
				 * (String.valueOf(branch[j]).equals(
				 * String.valueOf(branchObj[j2][2]))) {
				 * 
				 * System.out.println("String.valueOf(obj[j2][1])=" +
				 * String.valueOf(branchObj[j2][0])); branchCode +=
				 * String.valueOf(branchObj[j2][0]) + ",";
				 * System.out.println("branchCode=" + branchCode);
				 * 
				 * branchName += String.valueOf(branchObj[j2][1]) + ",";
				 * System.out.println("branchName=" + branchName);
				 *  }
				 *  }
				 *  } branchCode = branchCode.substring(0, branchCode.length() -
				 * 1); System.out.println("branchCode==== " + branchCode);
				 * 
				 * branchName = branchName.substring(0, branchName.length() -
				 * 1); System.out.println("branchName==== " + branchName); }
				 * bean1.setBranchCode(branchCode); // Branch Code
				 * bean1.setBranchName(branchName); // Branch Name
				 * 
				 * //Get Branch Details From HRMS_PROFILE_CENTER end
				 */

				List.add(bean1);
			}// end of loop
			profileAccess.setIteratorlist(List);
		}
	}

	/**
	 * to check null value
	 * 
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

	public void calforedit(ProfileAccess profileAccess,
			HttpServletRequest request) {
		try {
			/* Get data from 'HRMS_LWF_SLAB_HDR' */
			String sQuery = " SELECT PROFILE_CODE, PROFILE_NAME, PROFILE_DIV_FLAG, PROFILE_CNT_FLAG ,PROFILE_PAYBILL_FLAG FROM HRMS_PROFILE_ACC_HDR   "
					+ "WHERE  PROFILE_CODE = " +profileAccess.getHiddencode();
			Object[][] objData = getSqlModel().getSingleResult(sQuery);
			if (objData != null) {
				profileAccess.setProfileId(String.valueOf(objData[0][0]));
				profileAccess.setProfile(String.valueOf(objData[0][1]));

				if (String.valueOf(objData[0][2]).equals("Y")) {
					System.out.println("objData[0][2]=====" + objData[0][2]);
					profileAccess.setDivisionFlag("true");
				} else {
					profileAccess.setDivisionFlag("false");
				}

				if (String.valueOf(objData[0][3]).equals("Y")) {
					profileAccess.setBranchFlag("true");
				} else {
					profileAccess.setBranchFlag("false");
				}

				if (String.valueOf(objData[0][4]).equals("Y")) {
					profileAccess.setPaybillFlag("true");
				} else {
					profileAccess.setPaybillFlag("false");
				}

				/* Set Division Details From HRMS_PROFILE_DIVISION start */
				String divQuery = "SELECT DIV_CODE,NVL(DIV_NAME,' '),PROFILE_CODE FROM HRMS_PROFILE_DIVISION "
						+ "INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_PROFILE_DIVISION.DIV_CODE) WHERE PROFILE_CODE = "
						+ profileAccess.getProfileId();
				Object[][] divObj = getSqlModel().getSingleResult(divQuery);

				String divCode = "";
				String divName = "";

				String[] abbr = String.valueOf(objData[0][0]).split(",");
				if (divObj != null && divObj.length > 0) {

					for (int j = 0; j < abbr.length; j++) {
						for (int j2 = 0; j2 < divObj.length; j2++) {
							if (String.valueOf(abbr[j]).equals(
									String.valueOf(divObj[j2][2]))) {
								
								divCode += String.valueOf(divObj[j2][0]) + ",";
								

								divName += String.valueOf(divObj[j2][1]) + ",";
								

							}

						}

					}
					divCode = divCode.substring(0, divCode.length() - 1);
					

					divName = divName.substring(0, divName.length() - 1);
					

				}
				profileAccess.setSDivCode(divCode);
				profileAccess.setSDivName(divName);
				/* Set Division Details From HRMS_PROFILE_DIVISION end */

				/* Set Branch Details From HRMS_PROFILE_CENTER start */
				String branchQuery = "SELECT CENTER_CODE,NVL(CENTER_NAME,' '),PROFILE_CODE  FROM HRMS_PROFILE_CENTER "
						+ "INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_PROFILE_CENTER.CENTER_CODE) WHERE PROFILE_CODE = "
						+ profileAccess.getProfileId();
				Object[][] branchObj = getSqlModel().getSingleResult(
						branchQuery);

				String branchCode = "";
				String branchName = "";

				String[] branch = String.valueOf(objData[0][0]).split(",");
				System.out.println("branch===" + branch.length);
				if (branchObj.length > 0) {
					for (int j = 0; j < branch.length; j++) {
						for (int j2 = 0; j2 < branchObj.length; j2++) {
							if (String.valueOf(abbr[j]).equals(
									String.valueOf(branchObj[j2][2]))) {
								
								branchCode += String.valueOf(branchObj[j2][0])
										+ ",";
								
								branchName += String.valueOf(branchObj[j2][1])
										+ ",";
								
							}

						}

					}
					branchCode = branchCode.substring(0,
							branchCode.length() - 1);
					

					branchName = branchName.substring(0,
							branchName.length() - 1);
				

				}
				profileAccess.setSBranch(branchCode);
				profileAccess.setSBranchName(branchName);
				/* Set Division Details From HRMS_PROFILE_CENTER end */

				/* Set Paybill Details From HRMS_PROFILE_PAYBILL start */
				String paybillQuery = "SELECT  PAYBILL_NO,NVL(PAYBILL_GROUP,' ' ),PROFILE_CODE  FROM HRMS_PROFILE_PAYBILL "
						+ "INNER JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_PROFILE_PAYBILL.PAYBILL_NO) WHERE PROFILE_CODE = "
						+ profileAccess.getProfileId();
				Object[][] paybillObj = getSqlModel().getSingleResult(
						paybillQuery);

				String paybillCode = "";
				String paybillName = "";

				String[] paybill = String.valueOf(objData[0][0]).split(",");
				
				if (paybillObj.length > 0) {
					for (int j = 0; j < paybill.length; j++) {
						for (int j2 = 0; j2 < paybillObj.length; j2++) {
							if (String.valueOf(abbr[j]).equals(
									String.valueOf(paybillObj[j2][2]))) {
								
								paybillCode += String
										.valueOf(paybillObj[j2][0])
										+ ",";
								
								paybillName += String
										.valueOf(paybillObj[j2][1])
										+ ",";
								
							}

						}

					}
					paybillCode = paybillCode.substring(0,
							paybillCode.length() - 1);
					

					paybillName = paybillName.substring(0,
							paybillName.length() - 1);
				

				}
				profileAccess.setSPaybill(paybillCode);
				profileAccess.setSPaybillName(paybillName);
				/* Set Paybill Details From HRMS_PROFILE_PAYBILL end */

				profileAccess.setHiddencode(String.valueOf(objData[0][0]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean saveFunction(ProfileAccess bean, HttpServletRequest request) {
		try {
			if (!checkDuplicate(bean)) {
				int profileId = 0;
				boolean result = false;
				/* Get Max for LWF_CODE */
				String sQuery = "SELECT NVL(MAX(PROFILE_CODE),0) + 1 FROM HRMS_PROFILE_ACC_HDR";
				Object[][] objData = getSqlModel().getSingleResult(sQuery);
				if (objData != null) {
					profileId = Integer.parseInt(String.valueOf(objData[0][0]));
					bean.setProfileId(String.valueOf(profileId));
				}
				/* Insert Header Table Information */
				String sHeaderQuery = " INSERT INTO HRMS_PROFILE_ACC_HDR (PROFILE_CODE, PROFILE_NAME, PROFILE_DIV_FLAG, PROFILE_CNT_FLAG, PROFILE_PAYBILL_FLAG,CREATION_DATE) "
						+ " VALUES (?, ?, ?, ?,?,SYSDATE) ";
				Object[][] headerData = new Object[1][5];
				headerData[0][0] = profileId; /* Lwf Id */
				headerData[0][1] = bean.getProfile(); /* Profile Name */
				/* Division Flag */
				
				if (bean.getDivisionFlag().equals("true")) {
					headerData[0][2] = "Y";

				} else {
					headerData[0][2] = "N";					
				}
				
				
				if (bean.getBranchFlag().equals("true")) {
					headerData[0][3] = "Y";
				} else {
					headerData[0][3] = "N";
				}
				
				if (bean.getPaybillFlag().equals("true")) {
					headerData[0][4] = "Y";
				} else {
					headerData[0][4] = "N";
				}
				
				result = getSqlModel().singleExecute(sHeaderQuery, headerData);
				
				if (bean.getDivisionFlag().equals("true")) {
					headerData[0][2] = "Y";

				} else {
					headerData[0][2] = "N";

					/* insert into HRMS_PROFILE_DIVISION */
					String sDivQuery = "";

					Object[][] divisionData = new Object[1][1];

					divisionData[0][0] = profileId; /* Lwf Id */
					// divisionData[0][1] = bean.getSDivCode(); /* DivCode */

					String[] multipleDivCode = String.valueOf(
							bean.getSDivCode()).split(",");

					
					for (int j = 0; j < multipleDivCode.length; j++) {

						sDivQuery = " INSERT INTO HRMS_PROFILE_DIVISION (PROFILE_CODE, DIV_CODE) "
								+ " VALUES ("
								+ profileId
								+ ", "
								+ multipleDivCode[j] + ") ";

					

						getSqlModel().singleExecute(sDivQuery);
					}

				}
				/* Branch Flag */
				if (bean.getBranchFlag().equals("true")) {
					headerData[0][3] = "Y";
				} else {
					headerData[0][3] = "N";

					/* insert into HRMS_PROFILE_CENTER */
					String sBranchQuery = "";

					Object[][] branchData = new Object[1][1];

					branchData[0][0] = profileId; /* Lwf Id */
					// divisionData[0][1] = bean.getSDivCode(); /* DivCode */

					String[] multipleBranchCode = String.valueOf(
							bean.getSBranch()).split(",");

					
					for (int j = 0; j < multipleBranchCode.length; j++) {

						sBranchQuery = " INSERT INTO HRMS_PROFILE_CENTER (PROFILE_CODE, CENTER_CODE) "
								+ " VALUES ("
								+ profileId
								+ ", "
								+ multipleBranchCode[j] + ") ";

						

						getSqlModel().singleExecute(sBranchQuery);
					}

				}
				/* Paybill Flag */
				if (bean.getPaybillFlag().equals("true")) {
					headerData[0][4] = "Y";
				} else {
					headerData[0][4] = "N";

					/* insert into HRMS_PROFILE_PAYBILL */
					String sPaybillQuery = "";

					Object[][] paybillData = new Object[1][1];

					paybillData[0][0] = profileId; /* Lwf Id */
					// divisionData[0][1] = bean.getSDivCode(); /* DivCode */

					String[] multiplePaybillCode = String.valueOf(
							bean.getSPaybill()).split(",");

				
					for (int j = 0; j < multiplePaybillCode.length; j++) {

						sPaybillQuery = " INSERT INTO HRMS_PROFILE_PAYBILL (PROFILE_CODE, PAYBILL_NO) "
								+ " VALUES ("
								+ profileId
								+ ", "
								+ multiplePaybillCode[j] + ") ";

						

						getSqlModel().singleExecute(sPaybillQuery);
					}

				}
			
				return true;

			}// end of if
			else {
				return false;
			}// end of else
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private boolean checkDuplicate(ProfileAccess bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_PROFILE_ACC_HDR WHERE UPPER(PROFILE_NAME) LIKE '"
				+ bean.getProfile().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;
	}

	public boolean updateRecords(ProfileAccess bean, HttpServletRequest request) {
		boolean result = false;
		try {
			System.out.println("PROFILE ID:::" + bean.getProfileId());
			// if (!checkDuplicateMod(bean)) {
			try {
				// records update for HRMS_PROFILE_ACC_HDR

				// if (!checkDuplicate(bean)) {
				Object updateHdrObj[][] = new Object[1][5];
				updateHdrObj[0][0] = bean.getProfile();

				if (bean.getDivisionFlag().equals("true")) {
					updateHdrObj[0][1] = "Y";
					/* Delete First from HRMS_PROFILE_DIVISION start */
					String sDeleteQuery = "DELETE FROM HRMS_PROFILE_DIVISION WHERE PROFILE_CODE = "
							+ bean.getProfileId();
					result = getSqlModel().singleExecute(sDeleteQuery);
					/* Delete First from HRMS_PROFILE_DIVISION end */
				} else {
					updateHdrObj[0][1] = "N";

					/* Delete First from HRMS_PROFILE_DIVISION start */
					String sDeleteQuery = "DELETE FROM HRMS_PROFILE_DIVISION WHERE PROFILE_CODE = "
							+ bean.getProfileId();
					result = getSqlModel().singleExecute(sDeleteQuery);
					/* Delete First from HRMS_PROFILE_DIVISION end */

					/* insert into HRMS_PROFILE_DIVISION start */
					String sDivQuery = "";

					Object[][] divisionData = new Object[1][1];

					divisionData[0][0] = bean.getProfileId(); /* Lwf Id */
					// divisionData[0][1] = bean.getSDivCode(); /* DivCode */

					String[] multipleDivCode = String.valueOf(
							bean.getSDivCode()).split(",");

				
					for (int j = 0; j < multipleDivCode.length; j++) {

						sDivQuery = " INSERT INTO HRMS_PROFILE_DIVISION (PROFILE_CODE, DIV_CODE) "
								+ " VALUES ("
								+ divisionData[0][0]
								+ ", "
								+ multipleDivCode[j] + ") ";


						getSqlModel().singleExecute(sDivQuery);
					}
					/* insert into HRMS_PROFILE_DIVISION end */
				}

				if (bean.getBranchFlag().equals("true")) {
					updateHdrObj[0][2] = "Y";
					/* Delete First from HRMS_PROFILE_CENTER start */
					String sDeleteQuery = "DELETE FROM HRMS_PROFILE_CENTER WHERE PROFILE_CODE = "
							+ bean.getProfileId();
					result = getSqlModel().singleExecute(sDeleteQuery);
					/* Delete First from HRMS_PROFILE_CENTER end */
				} else {
					updateHdrObj[0][2] = "N";

					/* Delete First from HRMS_PROFILE_CENTER start */
					String sDeleteQuery = "DELETE FROM HRMS_PROFILE_CENTER WHERE PROFILE_CODE = "
							+ bean.getProfileId();
					result = getSqlModel().singleExecute(sDeleteQuery);
					/* Delete First from HRMS_PROFILE_CENTER end */

					/* insert into HRMS_PROFILE_CENTER start */
					String sBranchQuery = "";

					Object[][] branchData = new Object[1][1];

					branchData[0][0] = bean.getProfileId(); /* Lwf Id */
					// divisionData[0][1] = bean.getSDivCode(); /* DivCode */

					String[] multipleBranchCode = String.valueOf(
							bean.getSBranch()).split(",");

					
					for (int j = 0; j < multipleBranchCode.length; j++) {

						sBranchQuery = " INSERT INTO HRMS_PROFILE_CENTER (PROFILE_CODE, CENTER_CODE) "
								+ " VALUES ("
								+ branchData[0][0]
								+ ", "
								+ multipleBranchCode[j] + ") ";


						getSqlModel().singleExecute(sBranchQuery);
					}
					/* insert into HRMS_PROFILE_CENTER end */

				}
				if (bean.getPaybillFlag().equals("true")) {
					updateHdrObj[0][3] = "Y";
					/* Delete First from HRMS_PROFILE_PAYBILL start */
					String sDeleteQuery = "DELETE FROM HRMS_PROFILE_PAYBILL WHERE PROFILE_CODE = "
							+ bean.getProfileId();
					result = getSqlModel().singleExecute(sDeleteQuery);
					/* Delete First from HRMS_PROFILE_PAYBILL end */
				} else {
					updateHdrObj[0][3] = "N";

					/* Delete First from HRMS_PROFILE_PAYBILL start */
					String sDeleteQuery = "DELETE FROM HRMS_PROFILE_PAYBILL WHERE PROFILE_CODE = "
							+ bean.getProfileId();
					result = getSqlModel().singleExecute(sDeleteQuery);
					/* Delete First from HRMS_PROFILE_PAYBILL end */

					/* insert into HRMS_PROFILE_PAYBILL start */
					String sPaybillQuery = "";

					Object[][] paybillData = new Object[1][1];

					paybillData[0][0] = bean.getProfileId(); /* Lwf Id */
					// divisionData[0][1] = bean.getSDivCode(); /* DivCode */

					String[] multiplePaybillCode = String.valueOf(
							bean.getSPaybill()).split(",");

					
					for (int j = 0; j < multiplePaybillCode.length; j++) {

						sPaybillQuery = " INSERT INTO HRMS_PROFILE_PAYBILL (PROFILE_CODE, PAYBILL_NO) "
								+ " VALUES ("
								+ paybillData[0][0]
								+ ", "
								+ multiplePaybillCode[j] + ") ";

					

						getSqlModel().singleExecute(sPaybillQuery);
					}
					/* insert into HRMS_PROFILE_PAYBILL end */

				}

				updateHdrObj[0][4] = bean.getProfileId();

				String updateQuery = "UPDATE HRMS_PROFILE_ACC_HDR SET "
						+ " PROFILE_NAME = ?, PROFILE_DIV_FLAG= ? , PROFILE_CNT_FLAG= ? , PROFILE_PAYBILL_FLAG= ? ,CREATION_DATE=TO_DATE(SYSDATE,'dd-mm-yyyy') WHERE PROFILE_CODE=? ";
				result = getSqlModel().singleExecute(updateQuery, updateHdrObj);

				

				// }// end of if
				// else {
				// return false;
				// }// end of else

			} catch (Exception e) {
				// TODO: handle exception
			}

			// }// end of if
			// else {
			// return false;
			// }// end of else

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String deletecheckedRecords(ProfileAccess profileAccess,
			String[] code) {
		
		boolean result = false;
		boolean result1=false;
		boolean result2=false;
		boolean result3= false;
		Object[][]  data=null;
		int cnt=0;
		for (int i = 0; i < code.length; i++) {
			if (!code[i].equals("")) {
				String query="SELECT DISTINCT LOGIN_ACCESS_PROFILE " 
					+ "FROM HRMS_LOGIN WHERE LOGIN_ACCESS_PROFILE LIKE '%"+code[i]+"%'";
				data=getSqlModel().getSingleResult(query);
				if (data!=null && data.length > 0) {
					cnt++;
				}
			}
		}
		if (cnt==0) {
			int count = 0;
			if (code != null) {
				for (int i = 0; i < code.length; i++) {
					if (!code[i].equals("")) {
						Object[][] delete = new Object[1][1];
						delete[0][0] = code[i];
						result = getSqlModel().singleExecute(getQuery(31), delete);
						if (!result) {
							count++;
						}// end of if
					}// end of nested if
				}// end of loop

			}// end of nested if

			int count1 = 0;
			if (code != null) {
				for (int i = 0; i < code.length; i++) {
					if (!code[i].equals("")) {
						Object[][] delete = new Object[1][1];
						delete[0][0] = code[i];
						result1 = getSqlModel().singleExecute(getQuery(25), delete);
						if (!result1) {
							count1++;
						}// end of if
					}// end of nested if
				}// end of loop

			}// end of nested if

			int count2 = 0;
			if (code != null) {
				for (int i = 0; i < code.length; i++) {
					if (!code[i].equals("")) {
						Object[][] delete = new Object[1][1];
						delete[0][0] = code[i];
						result1 = getSqlModel().singleExecute(getQuery(23), delete);
						if (!result2) {
							count2++;
						}// end of if
					}// end of nested if
				}// end of loop

			}// end of nested if

			int count3 = 0;
			if (code != null) {
				for (int i = 0; i < code.length; i++) {
					if (!code[i].equals("")) {
						Object[][] delete = new Object[1][1];
						delete[0][0] = code[i];
						result3 = getSqlModel().singleExecute(getQuery(30), delete);
						if (!result3) {
							count3++;
						}// end of if
					}// end of nested if
				}// end of loop

			}// end of nested if

			if (count3==0){
				return "true";
			}
			else{
				return "false";
			}	
		}else{
			return "false";
		}
	}

	/**
	 * to delete the single access profile record
	 * 
	 * @param bean
	 * @return
	 */

	public boolean deleteSelAccessProfile(ProfileAccess bean) {
		Object[][] delObj = new Object[1][1];
		delObj[0][0] = bean.getProfileId();
		boolean res=false;
		boolean res3=false;
		boolean res1 =false;
		boolean res2=false;
		String query="SELECT DISTINCT LOGIN_ACCESS_PROFILE " 
					+ "FROM HRMS_LOGIN WHERE LOGIN_ACCESS_PROFILE LIKE '%"+bean.getProfileId()+"%'";
		Object[][] data=getSqlModel().getSingleResult(query);
		if (data!=null && data.length > 0) {
			return false;
		}
		else{
			Object[][] delDivObj = new Object[1][1];
			delDivObj[0][0] = bean.getProfileId();
			res1= getSqlModel().singleExecute(getQuery(25), delDivObj);

			Object[][] delBranchObj = new Object[1][1];
			delBranchObj[0][0] = bean.getProfileId();
			 res2= getSqlModel().singleExecute(getQuery(23), delBranchObj);

			Object[][] delPaybillObj = new Object[1][1];
			delPaybillObj[0][0] = bean.getProfileId();
			res3= getSqlModel().singleExecute(getQuery(31), delPaybillObj);
			
			res= getSqlModel().singleExecute(getQuery(30), delObj);
			
		}
		if (res && res1 && res2 && res3) {
			return true;
		}else
			return false;
				
	}

	public void getAcessProfileRecord(ProfileAccess profileAccess,
			HttpServletRequest request) {
		try {
			/* Get data from 'HRMS_LWF_SLAB_HDR' */
			String sQuery = " SELECT PROFILE_CODE, NVL(PROFILE_NAME,' '), PROFILE_DIV_FLAG, PROFILE_CNT_FLAG ,PROFILE_PAYBILL_FLAG FROM HRMS_PROFILE_ACC_HDR   "
					// + "LEFT JOIN HRMS_LWF_EMP_APPLICABLE ON
					// (HRMS_LWF_EMP_APPLICABLE.LWF_CODE = A.LWF_CODE)"
					// + "INNER JOIN HRMS_DIVISION ON
					// (HRMS_DIVISION.DIV_ID=HRMS_LWF_EMP_APPLICABLE.LWF_DIV)"
					+ "WHERE  PROFILE_CODE = " + profileAccess.getProfileId();
			Object[][] objData = getSqlModel().getSingleResult(sQuery);
			if (objData != null) {
				profileAccess.setProfileId(String.valueOf(objData[0][0]));
				profileAccess.setProfile(String.valueOf(objData[0][1]));

				if (String.valueOf(objData[0][2]).equals("Y")) {
					
					profileAccess.setDivisionFlag("true");
				} else {
					profileAccess.setDivisionFlag("false");
				}

				if (String.valueOf(objData[0][3]).equals("Y")) {
					profileAccess.setBranchFlag("true");
				} else {
					profileAccess.setBranchFlag("false");
				}

				if (String.valueOf(objData[0][4]).equals("Y")) {
					profileAccess.setPaybillFlag("true");
				} else {
					profileAccess.setPaybillFlag("false");
				}

				/* Set Division Details From HRMS_PROFILE_DIVISION start */
				String divQuery = "SELECT DIV_CODE,NVL(DIV_NAME,' '),PROFILE_CODE FROM HRMS_PROFILE_DIVISION "
						+ "INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_PROFILE_DIVISION.DIV_CODE) WHERE PROFILE_CODE = "
						+ profileAccess.getProfileId();
				Object[][] divObj = getSqlModel().getSingleResult(divQuery);

				String divCode = "";
				String divName = "";

				String[] abbr = String.valueOf(objData[0][0]).split(",");
			
				if (divObj != null && divObj.length > 0) {

					for (int j = 0; j < abbr.length; j++) {
						for (int j2 = 0; j2 < divObj.length; j2++) {
							if (String.valueOf(abbr[j]).equals(
									String.valueOf(divObj[j2][2]))) {
								
								divCode += String.valueOf(divObj[j2][0]) + ",";
								

								divName += String.valueOf(divObj[j2][1]) + ",";
							
							}

						}

					}
					divCode = divCode.substring(0, divCode.length() - 1);
					
					divName = divName.substring(0, divName.length() - 1);
					

				}
				profileAccess.setSDivCode(divCode);
				profileAccess.setSDivName(divName);
				/* Set Division Details From HRMS_PROFILE_DIVISION end */

				/* Set Branch Details From HRMS_PROFILE_CENTER start */
				String branchQuery = "SELECT CENTER_CODE,NVL(CENTER_NAME,' '),PROFILE_CODE  FROM HRMS_PROFILE_CENTER "
						+ "INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_PROFILE_CENTER.CENTER_CODE) WHERE PROFILE_CODE = "
						+ profileAccess.getProfileId();
				Object[][] branchObj = getSqlModel().getSingleResult(
						branchQuery);

				String branchCode = "";
				String branchName = "";

				String[] branch = String.valueOf(objData[0][0]).split(",");
			
				if (branchObj.length > 0) {
					for (int j = 0; j < branch.length; j++) {
						for (int j2 = 0; j2 < branchObj.length; j2++) {
							if (String.valueOf(abbr[j]).equals(
									String.valueOf(branchObj[j2][2]))) {
								
								branchCode += String.valueOf(branchObj[j2][0])
										+ ",";
							
								branchName += String.valueOf(branchObj[j2][1])
										+ ",";
							

							}

						}

					}
					branchCode = branchCode.substring(0,
							branchCode.length() - 1);
				

					branchName = branchName.substring(0,
							branchName.length() - 1);
				
				}
				profileAccess.setSBranch(branchCode);
				profileAccess.setSBranchName(branchName);
				/* Set Division Details From HRMS_PROFILE_CENTER end */

				/* Set Paybill Details From HRMS_PROFILE_PAYBILL start */
				String paybillQuery = "SELECT  PAYBILL_NO,NVL(PAYBILL_GROUP,' '),PROFILE_CODE  FROM HRMS_PROFILE_PAYBILL "
						+ "INNER JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID = HRMS_PROFILE_PAYBILL.PAYBILL_NO) WHERE PROFILE_CODE = "
						+ profileAccess.getProfileId();
				Object[][] paybillObj = getSqlModel().getSingleResult(
						paybillQuery);

				String paybillCode = "";
				String paybillName = "";

				String[] paybill = String.valueOf(objData[0][0]).split(",");
			
				if (paybillObj.length > 0) {
					for (int j = 0; j < paybill.length; j++) {
						for (int j2 = 0; j2 < paybillObj.length; j2++) {
							if (String.valueOf(abbr[j]).equals(
									String.valueOf(paybillObj[j2][2]))) {
								
								paybillCode += String
										.valueOf(paybillObj[j2][0])
										+ ",";
								

								paybillName += String
										.valueOf(paybillObj[j2][1])
										+ ",";
							

							}

						}

					}
					paybillCode = paybillCode.substring(0,
							paybillCode.length() - 1);
			
					paybillName = paybillName.substring(0,
							paybillName.length() - 1);
					

				}
				profileAccess.setSPaybill(paybillCode);
				profileAccess.setSPaybillName(paybillName);
				/* Set Paybill Details From HRMS_PROFILE_PAYBILL end */

				profileAccess.setHiddencode(String.valueOf(objData[0][0]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getReport(ProfileAccess profileAccess,
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context) {

		try {

			String reportType = "";

			logger.info("reportType--------------->" + reportType + "<-------");

			String title = " Access Profile Report ";
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("Access Profile  Report");
			rds.setReportName(title);
			rds.setReportType("Pdf");
			rds.setPageSize("A4");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);

			// For Report heading
			TableDataSet repTitle = new TableDataSet();
			Object[][] repTitleObj = new Object[1][1];
			repTitleObj[0][0] = title;
			repTitle.setData(repTitleObj);
			repTitle.setCellAlignment(new int[] { 1 });
			repTitle.setCellWidth(new int[] { 100 });
			repTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			repTitle.setBorder(false);
			repTitle.setBlankRowsBelow(1);
			rg.addTableToDoc(repTitle);

			java.util.Date d = new java.util.Date();
			logger.info("Date : - " + d);
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"dd-MM-yyyy");
			String strDate = sdf.format(d);

			Object[][] dateData = new Object[1][1];
			dateData[0][0] = "Date: - " + strDate;
			int[] cellWidthDateHeader = { 100 };
			int[] cellAlignDateHeader = { 2 };
			TableDataSet tableheadingDateData = new TableDataSet();
			tableheadingDateData.setData(dateData);
			tableheadingDateData.setCellWidth(cellWidthDateHeader);
			tableheadingDateData.setCellAlignment(cellAlignDateHeader);
			tableheadingDateData.setBodyFontDetails(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0));
			tableheadingDateData.setBorder(false);
			// tableheadingDateData.setHeaderTable(true);
			tableheadingDateData.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingDateData);

			String profileNameQuery = " SELECT PROFILE_NAME,PROFILE_CODE FROM HRMS_PROFILE_ACC_HDR ORDER BY PROFILE_NAME ASC ";
			Object[][] processDetail = getSqlModel().getSingleResult(
					profileNameQuery);

			// =============Details of tax deducted====================//

			Object[][] objTabularData = new Object[processDetail.length][6];
			String[] columns = new String[] { "Sr. No.", "Profile Name ",
					"Division", "Branch", "Paybill" };
			int[] bcellAlign = new int[] { 0, 0, 0, 0, 0 };
			int[] bcellWidth = new int[] { 10, 25, 35, 35, 35 };

			if (processDetail != null && processDetail.length > 0) {
				Object[][] processDtlDetName = new Object[1][1];
				processDtlDetName[0][0] = "Access Profile Details ";
				TableDataSet expenseDtlDetNameTable = new TableDataSet();
				expenseDtlDetNameTable.setData(processDtlDetName);
				expenseDtlDetNameTable.setCellAlignment(new int[] { 1 });
				expenseDtlDetNameTable.setCellWidth(new int[] { 100 });
				expenseDtlDetNameTable.setBodyFontDetails(Font.HELVETICA, 10,
						Font.BOLD, new Color(0, 0, 0));
				expenseDtlDetNameTable.setBodyBGColor(new Color(192, 192, 192));
				rg.addTableToDoc(expenseDtlDetNameTable);
				
				int s1 = 1;
				for (int i = 0; i < processDetail.length; i++) {

					objTabularData[i][0] = s1++;
					objTabularData[i][1] = String.valueOf(processDetail[i][0]); // Profile
																				// Name
					// Query to get division details start
					String divNameQuery = " SELECT PROFILE_CODE, DIV_CODE ,NVL(DIV_NAME,' ')"
							+ " FROM HRMS_PROFILE_DIVISION "
							+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_PROFILE_DIVISION.DIV_CODE)"
							+ " WHERE PROFILE_CODE=" + processDetail[i][1];

					Object[][] divisionDetail = getSqlModel().getSingleResult(
							divNameQuery);
					int count = 1;
					String div = "";
					if (divisionDetail.length > 0) {
						for (int j = 0; j < divisionDetail.length; j++) {

							div += count+")"+String.valueOf(divisionDetail[j][2]) + " \n";
							count++;
						}
						div = div.substring(0, div.length() - 1);
					}
					objTabularData[i][2] = String.valueOf(div); // Division
					// Query to get division details end

					// Query to get Branch details start
					String branchNameQuery = " SELECT PROFILE_CODE, CENTER_CODE ,NVL(CENTER_NAME,' ')"
							+ " FROM HRMS_PROFILE_CENTER "
							+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_PROFILE_CENTER.CENTER_CODE)"
							+ " WHERE PROFILE_CODE=" + processDetail[i][1];

					Object[][] branchDetail = getSqlModel().getSingleResult(
							branchNameQuery);
					int countBr = 1;
					String branchName = "";
					if (branchDetail.length > 0) {
						for (int j = 0; j < branchDetail.length; j++) {

							branchName += countBr+")"+String.valueOf(branchDetail[j][2])
									+  " \n";
							countBr++;
						}
						branchName = branchName.substring(0, branchName
								.length() - 1);
					}
					objTabularData[i][3] = String.valueOf(branchName); // Branch
					// Query to get Branch details end

					// Query to get Paybill details start
					String paybillNameQuery = " SELECT PROFILE_CODE, PAYBILL_NO ,NVL(PAYBILL_GROUP,' ')"
							+ " FROM HRMS_PROFILE_PAYBILL "
							+ " INNER JOIN HRMS_PAYBILL ON (HRMS_PAYBILL.PAYBILL_ID = HRMS_PROFILE_PAYBILL.PAYBILL_NO)"
							+ " WHERE PROFILE_CODE=" + processDetail[i][1];

					Object[][] paybillDetail = getSqlModel().getSingleResult(
							paybillNameQuery);
					int countPb = 1;
					String paybillName = "";
					if (paybillDetail.length > 0) {
						for (int j = 0; j < paybillDetail.length; j++) {

							paybillName +=  countPb+")"+String.valueOf(paybillDetail[j][2])
									+  " \n";
							countPb++;
						}
						paybillName = paybillName.substring(0, paybillName
								.length() - 1);
					}
					objTabularData[i][4] = String.valueOf(paybillName); // Paybill
					// Query to get Paybill details end

				}
			} else {

				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";

				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}

			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(columns);
			tdstable.setData(objTabularData);
			tdstable.setCellAlignment(bcellAlign);
			tdstable.setCellWidth(bcellWidth);
			tdstable.setBorder(true);
			tdstable.setHeaderBGColor(new Color(225, 225, 225));
			rg.addTableToDoc(tdstable);

			rg.process();
			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
