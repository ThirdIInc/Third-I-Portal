package org.paradyne.model.admin.master;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.paradyne.bean.admin.master.LWFConfigurationMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.action.DataMigration.MigrateExcelData;

public class LWFConfigurationMasterModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LWFConfigurationMasterModel.class);

	public void getEmployeeList(LWFConfigurationMaster bean,
			HttpServletRequest request) {
		try {

			Object[][] objData = getSqlModel().getSingleResult(getQuery(bean));
			ArrayList<Object> list = new ArrayList<Object>();

			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					objData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("PageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");

			if (objData != null && objData.length != 0) {
				LWFConfigurationMaster bean1 = null;
				List lst = new ArrayList();

				// for (int i = 0; i < objData.length; i++) {
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					bean1 = new LWFConfigurationMaster();
					bean1.setISrNo_EmpLst(String.valueOf(i + 1));
					bean1.setSEmployeeId(String.valueOf(objData[i][0]));
					bean1.setSEmployeeName(String.valueOf(objData[i][1]));
					bean1.setIsLwfApplicable(String.valueOf(objData[i][2]));
					bean1.setSEmpCode(String.valueOf(objData[i][3]));
					lst.add(bean1);
				}

				bean.setLstEmpList(lst);
			}
		}

		catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterModel.getEmployeeList() method Model : "
							+ e.getMessage());
		}
	}

	private String getQuery(LWFConfigurationMaster bean) {
		try {
			int count = 0;
			String sQuery;

			System.out.println(bean.getSApplicableAll());

			if (bean.getSDivCode().equals("") && bean.getSBranch().equals("")
					&& bean.getSDepartment().equals("")
					&& bean.getSDesignation().equals("")
					&& bean.getSEmpType().equals("")
					&& bean.getSEmpCode().equals("")) {
				sQuery = " SELECT "
						+ " 	A.EMP_ID, (EMP_FNAME || ' ' || EMP_MNAME|| ' ' || EMP_LNAME) AS EMPLOYEE_NAME,"
						+ " 	NVL(SAL_LWF_FLAG,'N'), A.EMP_TOKEN "
						+ " FROM "
						+ " HRMS_EMP_OFFC A "
						+ "  	LEFT JOIN HRMS_SALARY_MISC B ON (A.EMP_ID = B.EMP_ID) "
						+ " 	ORDER BY " + " EMPLOYEE_NAME ";
			} else {

				sQuery = " SELECT "
						+ " 	A.EMP_ID,"
						+ " 	(EMP_FNAME || ' ' || EMP_MNAME|| ' ' || EMP_LNAME) AS EMPLOYEE_NAME, "
						+ "     NVL(SAL_LWF_FLAG,'N'), A.EMP_TOKEN "
						+ " FROM "
						+ " HRMS_EMP_OFFC A "
						+ "  	LEFT JOIN HRMS_SALARY_MISC B ON (A.EMP_ID = B.EMP_ID) "
						+ " WHERE ";

				/* Division */
				if (!bean.getSDivCode().equals("")) {

					if (count > 0)
						sQuery += " AND EMP_DIV IN (" + bean.getSDivCode()
								+ ")";
					else
						sQuery += " EMP_DIV IN (" + bean.getSDivCode() + ")";

					count = count + 1;
				}

				/* Branch */
				if (!bean.getSBranch().equals("")) {

					if (count > 0)
						sQuery += " AND EMP_CENTER IN (" + bean.getSBranch()
								+ ")";
					else
						sQuery += " EMP_CENTER IN (" + bean.getSBranch() + ")";

					count = count + 1;
				}

				/* Department */
				if (!bean.getSDepartment().equals("")) {

					if (count > 0)
						sQuery += " AND EMP_DEPT IN (" + bean.getSDepartment()
								+ ")";
					else
						sQuery += " EMP_DEPT IN (" + bean.getSDepartment()
								+ ")";

					count = count + 1;
				}

				/* Designation */
				if (!bean.getSDesignation().equals("")) {

					if (count > 0)
						sQuery += " AND EMP_RANK IN (" + bean.getSDesignation()
								+ ")";
					else
						sQuery += " EMP_RANK IN (" + bean.getSDesignation()
								+ ")";

					count = count + 1;
				}

				/* Employee Type */
				if (!bean.getSEmpTypeId().equals("")) {

					if (count > 0)
						sQuery += " AND EMP_TYPE IN (" + bean.getSEmpTypeId()
								+ ")";
					else
						sQuery += " EMP_TYPE IN (" + bean.getSEmpTypeId() + ")";

					count = count + 1;
				}

				/* Individual Employee */
				if (!bean.getSEmpCode().equals("")) {

					if (count > 0)
						sQuery += " AND A.EMP_ID IN (" + bean.getSEmpCode()
								+ ")";
					else
						sQuery += " A.EMP_ID IN (" + bean.getSEmpCode() + ")";

					count = count + 1;
				}

				sQuery += " ORDER BY EMPLOYEE_NAME";
			}
			return sQuery;

		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterModel.getQuery() method Model : "
							+ e.getMessage());
			return "";
		}
	}

	public Boolean saveAllEmpData(LWFConfigurationMaster bean,
			HttpServletRequest request) {
		try {

			String sQuery = " SELECT EMP_ID FROM HRMS_EMP_OFFC ";
			Object[][] objData = getSqlModel().getSingleResult(sQuery);

			int iEmp = objData.length;

			for (int i = 0; i < iEmp; i++) {
				String sQuery1 = " SELECT EMP_ID FROM HRMS_SALARY_MISC WHERE EMP_ID = "
						+ objData[i][0];

				Object[][] objData1 = getSqlModel().getSingleResult(sQuery1);

				if ((objData1 != null) && (objData1.length > 0)) {
					String sQuery2 = " UPDATE HRMS_SALARY_MISC SET SAL_LWF_FLAG = 'Y' WHERE EMP_ID = "
							+ objData[i][0];
					getSqlModel().singleExecute(sQuery2);

				} else {

					String sQuery3 = " INSERT INTO HRMS_SALARY_MISC (EMP_ID,SAL_LWF_FLAG ) VALUES ("
							+ objData[i][0] + ",'Y')";
					getSqlModel().singleExecute(sQuery3);
				}
			}

			return true;
		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterModel.saveAllEmpData() method Model : "
							+ e.getMessage());
			return false;
		}
	}

	public Boolean saveData(LWFConfigurationMaster bean,
			HttpServletRequest request) {
		try {
			// String [] aEmpIds = request.getParameterValues("sEmployeeId");
			// String [] sIsLwfApp =
			// request.getParameterValues("isLwfApplicable");
			String[] sConfigEmp = request.getParameterValues("sConfigEmp");
			String[] isLwfApplicable = request
					.getParameterValues("isLwfApplicable");

			// int iEmp = aEmpIds.length;
			int iEmp = sConfigEmp.length;

			for (int i = 0; i < iEmp; i++) {

				if (!sConfigEmp[i].equals("")) {

					String sQuery = " SELECT EMP_ID FROM HRMS_SALARY_MISC WHERE EMP_ID = "
							+ sConfigEmp[i];
					Object[][] objData = getSqlModel().getSingleResult(sQuery);

					if (objData != null && objData.length > 0) {
						String sQuery1 = " UPDATE HRMS_SALARY_MISC SET SAL_LWF_FLAG = '"
								+ isLwfApplicable[i]
								+ "' WHERE EMP_ID = "
								+ sConfigEmp[i];
						getSqlModel().singleExecute(sQuery1);

					} else {
						String sQuery2 = " INSERT INTO HRMS_SALARY_MISC (EMP_ID,SAL_LWF_FLAG ) VALUES ("
								+ sConfigEmp[i]
								+ ",'"
								+ isLwfApplicable[i]
								+ "')";
						getSqlModel().singleExecute(sQuery2);
					}
				}
			}
			// }
			return true;

		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterModel.saveData() method Model : "
							+ e.getMessage());
			return false;
		}
	}

	public void getConfiguredEmpCount(LWFConfigurationMaster bean) {
		try {
			String sQuery = "SELECT COUNT(EMP_ID) FROM HRMS_EMP_OFFC WHERE EMP_ID NOT IN "
					+ "(SELECT EMP_ID FROM HRMS_SALARY_MISC WHERE SAL_LWF_FLAG = 'Y')";

			Object[][] objData = getSqlModel().getSingleResult(sQuery);
			if (objData != null && objData.length > 0) {
				bean.setSLwfNotApplicable(String.valueOf(objData[0][0]));
			}

			String sQuery1 = "SELECT COUNT(EMP_ID) FROM HRMS_SALARY_MISC WHERE SAL_LWF_FLAG = 'Y'";

			Object[][] objData1 = getSqlModel().getSingleResult(sQuery1);
			if (objData1 != null && objData1.length > 0) {
				bean.setSLwfApplicable(String.valueOf(objData1[0][0]));
			}
		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterModel.getConfiguredEmpCount() method Model : "
							+ e.getMessage());
		}
	}

	public void addRowSlabConfig(LWFConfigurationMaster bean) {
		List list = new ArrayList();
		LWFConfigurationMaster bean1 = new LWFConfigurationMaster();

		bean1.setISrNo(1);
		bean1.setSBasicFrm("");
		bean1.setSBasicTo("");
		bean1.setSEmployeeContribution("");
		bean1.setSEmployerContribution("");
		list.add(bean1);
		bean.setLstSlabDefinition(list);

	}

	public boolean saveSlabConfiguration(LWFConfigurationMaster bean,
			HttpServletRequest request) {
		try {
			int iLwfId = 0;
			boolean result = false;

			/* Get Max for LWF_CODE */
			String sQuery = "SELECT NVL(MAX(LWF_CODE),0) + 1 FROM HRMS_LWF_SLAB_HDR";
			Object[][] objData = getSqlModel().getSingleResult(sQuery);

			if (objData != null) {
				iLwfId = Integer.parseInt(String.valueOf(objData[0][0]));
				bean.setSLwfCode(String.valueOf(iLwfId));
			}

			/* Insert Header Table Information */
			String sHeaderQuery = " INSERT INTO HRMS_LWF_SLAB_HDR (LWF_CODE, LWF_EFFECTIVE_FROM, LWF_STATE_CODE, LWF_MONTH_DEDUCTIONS) "
					+ " VALUES (?, TO_DATE(?,'DD-MM-YYYY'), ?, TO_CHAR(?)) ";

			Object[][] headerData = new Object[1][4];
			headerData[0][0] = iLwfId; /* Lwf Id */
			headerData[0][1] = bean.getSEffectiveDate(); /* Effective Date */
			headerData[0][2] = bean.getSStateCode(); /* State */

			headerData[0][3] = bean.getSDeductionMonth(); /* Deduction Month */

			result = getSqlModel().singleExecute(sHeaderQuery, headerData);
			/* --- Header End --- */

			/* Insert Detail Table Information */
			String sDetailQuery = " INSERT INTO HRMS_LWF_SLAB_DTL (LWF_CODE, LWF_SLAB_FROM, LWF_SLAB_TO, LWF_EMP_CONTRIB, LWF_EMPLR_CONTRIB, LWF_DTL_CODE) "
					+ " VALUES (?,?,?,?,?,?) ";

			String[] aBasicFrm = request.getParameterValues("sBasicFrm");
			String[] aBasicTo = request.getParameterValues("sBasicTo");
			String[] aEmployeeContribution = request
					.getParameterValues("sEmployeeContribution");
			String[] aEmployerContribution = request
					.getParameterValues("sEmployerContribution");

			Object[][] detailData = new Object[aBasicFrm.length][6];
			for (int i = 0; i < detailData.length; i++) {
				detailData[i][0] = iLwfId; /* Lwf Code */
				detailData[i][1] = aBasicFrm[i]; /* From */
				detailData[i][2] = aBasicTo[i]; /* To */
				detailData[i][3] = aEmployeeContribution[i]; /*
																 * Employee
																 * Contribution
																 */
				detailData[i][4] = aEmployerContribution[i]; /*
																 * Employer
																 * Contribution
																 */
				detailData[i][5] = (i + 1); /* Lwf Dtl Code */
			}

			result = getSqlModel().singleExecute(sDetailQuery, detailData);
			/* --- Detail End --- */

			/* ---Retain data in list--- */
			LWFConfigurationMaster tempBean = null;
			int count = 1;
			List lst = new ArrayList();

			for (int i = 0; i < aBasicFrm.length; i++) {

				tempBean = new LWFConfigurationMaster();
				tempBean.setISrNo(count++);
				tempBean.setSBasicFrm(aBasicFrm[i]);
				tempBean.setSBasicTo(aBasicTo[i]);
				tempBean.setSEmployeeContribution(aEmployeeContribution[i]);
				tempBean.setSEmployerContribution(aEmployerContribution[i]);
				lst.add(tempBean);
			}
			bean.setLstSlabDefinition(lst);
			bean.setSDeductionMonthCode(bean.getSDeductionMonth());
			/* ---End--- */

			return true;
		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterModel.saveSlabConfiguration() method Model : "
							+ e.getMessage());
			return false;
		}
	}

	public void deleteRowSlabConfig(LWFConfigurationMaster bean,
			HttpServletRequest request) {
		try {
			
			List lst = new ArrayList();

			String[] aBasicFrm = request.getParameterValues("sBasicFrm");
			String[] aBasicTo = request.getParameterValues("sBasicTo");
			String[] aEmployeeContribution = request
					.getParameterValues("sEmployeeContribution");
			String[] aEmployerContribution = request
					.getParameterValues("sEmployerContribution");
			String[] aSlabChk = request.getParameterValues("hdelete");

			LWFConfigurationMaster tempBean = null;
			int count = 1;
			for (int i = 0; i < aSlabChk.length; i++) {

				if (!aSlabChk[i].equalsIgnoreCase("Y")) {
					tempBean = new LWFConfigurationMaster();
					tempBean.setISrNo(count++);
					tempBean.setSBasicFrm(aBasicFrm[i]);
					tempBean.setSBasicTo(aBasicTo[i]);
					tempBean.setSEmployeeContribution(aEmployeeContribution[i]);
					tempBean.setSEmployerContribution(aEmployerContribution[i]);
					lst.add(tempBean);
				}
			}
			bean.setLstSlabDefinition(lst);
			
		} catch (Exception e) {
			/*logger
					.error("Error in LWFConfigurationMasterModel.deleteRowSlabConfig() method Model : "
							+ e.getMessage());*/
			e.printStackTrace();
		}
	}

	public boolean saveOrgLwfApplicability(LWFConfigurationMaster bean,
			HttpServletRequest request) {
		try {
			String sLwfAppOrg = "N";

			sLwfAppOrg = bean.getSLwfApplicableOrg();

			String sQuery = " SELECT LWF_APPLICABLE, LWF_DEBIT_CODE, LWF_CREDIT_CODE FROM HRMS_LWF_CONFIGURATION ";
			Object[][] objData = getSqlModel().getSingleResult(sQuery);

			if (objData != null && objData.length > 0) {
				String sQuery1 = " UPDATE HRMS_LWF_CONFIGURATION SET LWF_APPLICABLE = '"
						+ sLwfAppOrg
						+ "',"
						+ " LWF_DEBIT_CODE = "
						+ bean.getSLwfDebitHeadCode()
						+ ","
						+ " LWF_CREDIT_CODE = " + bean.getSLwfCreditHeadCode();

				getSqlModel().singleExecute(sQuery1);

			} else {
				String sQuery2 = " INSERT INTO HRMS_LWF_CONFIGURATION (LWF_APPLICABLE, LWF_DEBIT_CODE, LWF_CREDIT_CODE) "
						+ " VALUES ('"
						+ sLwfAppOrg
						+ "',"
						+ bean.getSLwfDebitHeadCode()
						+ ","
						+ bean.getSLwfDebitHeadCode() + ")";

				getSqlModel().singleExecute(sQuery2);
			}

			return true;

		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterModel.saveOrgLwfApplicability() method Model : "
							+ e.getMessage());
			return false;
		}
	}

	public void showLwfApplicableOrg(LWFConfigurationMaster bean) {
		try {
			String sQuery = " SELECT LWF_APPLICABLE, LWF_DEBIT_CODE, DEBIT_NAME, LWF_CREDIT_CODE, CREDIT_NAME"
					+ " FROM HRMS_LWF_CONFIGURATION A "
					+ " INNER JOIN HRMS_DEBIT_HEAD B ON (A.LWF_DEBIT_CODE = B.DEBIT_CODE) "
					+ " INNER JOIN HRMS_CREDIT_HEAD C ON (A.LWF_CREDIT_CODE = C.CREDIT_CODE) ";
			Object[][] objData = getSqlModel().getSingleResult(sQuery);

			if (objData != null) {
				bean.setSLwfApplicableOrg(String.valueOf(objData[0][0]));
				bean.setSLwfDebitHeadCode(String.valueOf(objData[0][1]));
				bean.setSLwfDebitHead(String.valueOf(objData[0][2]));
				bean.setSLwfCreditHeadCode(String.valueOf(objData[0][3]));
				bean.setSLwfCreditHead(String.valueOf(objData[0][4]));
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger
					.error("Error in LWFConfigurationMasterModel.showLwfApplicableOrg() method Model : "
							+ e.getMessage());
		}
	}

	public void getConfiguraedStateDetails(LWFConfigurationMaster bean) {
		try {

			/* Get data from 'HRMS_LWF_SLAB_HDR' */
			String sQuery = " SELECT LWF_CODE, TO_CHAR(LWF_EFFECTIVE_FROM,'DD-MM-YYYY'), LWF_STATE_CODE, LOCATION_NAME, LWF_MONTH_DEDUCTIONS  "
					+ " FROM HRMS_LWF_SLAB_HDR A INNER JOIN HRMS_LOCATION B "
					+ " ON (A.LWF_STATE_CODE = B.LOCATION_CODE) WHERE LWF_STATE_CODE = '"
					+ bean.getSStateCode()
					+ "'"
					+ " AND LWF_EFFECTIVE_FROM = TO_DATE('"
					+ bean.getSEffectiveDate() + "','DD-MM-YYYY')";

			Object[][] objData = getSqlModel().getSingleResult(sQuery);

			if (objData != null) {
				bean.setSLwfCode(String.valueOf(objData[0][0]));
				bean.setSEffectiveDate(String.valueOf(objData[0][1]));
				bean.setSStateCode(String.valueOf(objData[0][2]));
				bean.setSState(String.valueOf(objData[0][3]));
				bean.setSDeductionMonthCode(String.valueOf(objData[0][4]));
			}

			/* Get data from HRMS_LWF_SLB_DTL */
			String sQueryDtl = " SELECT LWF_SLAB_FROM, LWF_SLAB_TO, LWF_EMP_CONTRIB, LWF_EMPLR_CONTRIB , LWF_DTL_CODE "
					+ " FROM HRMS_LWF_SLAB_DTL "
					+ " WHERE LWF_CODE = '"
					+ bean.getSLwfCode() + "' ORDER BY LWF_DTL_CODE";

			Object[][] dtlData = getSqlModel().getSingleResult(sQueryDtl);
			LWFConfigurationMaster tmpBean = null;
			List lst = new ArrayList();

			for (int i = 0; i < dtlData.length; i++) {
				tmpBean = new LWFConfigurationMaster();
				tmpBean.setISrNo(i + 1);
				tmpBean.setSBasicFrm(String.valueOf(dtlData[i][0]));
				tmpBean.setSBasicTo(String.valueOf(dtlData[i][1]));
				tmpBean.setSEmployeeContribution(String.valueOf(dtlData[i][2]));
				tmpBean.setSEmployerContribution(String.valueOf(dtlData[i][3]));

				lst.add(tmpBean);
			}
			bean.setLstSlabDefinition(lst);

		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterModel.getConfiguraedStateDetails() method Model : "
							+ e.getMessage());
		}
	}

	public boolean updateSlabConfiguration(LWFConfigurationMaster bean,
			HttpServletRequest request) {

		try {
			/* Update data from 'HRMS_LWF_SLAB_HDR' */
			boolean result = false;
			String sQueryHeader = " UPDATE HRMS_LWF_SLAB_HDR SET LWF_EFFECTIVE_FROM = TO_DATE(?,'DD-MM-YYYY'), LWF_STATE_CODE = ?, "
					+ " LWF_MONTH_DEDUCTIONS = ? WHERE LWF_CODE = ? ";

			Object[][] headerData = new Object[1][4];
			headerData[0][0] = bean.getSEffectiveDate(); /* Effective Date */
			headerData[0][1] = bean.getSStateCode(); /* State */
			headerData[0][2] = bean.getSDeductionMonth(); /* Deduction Month */
			headerData[0][3] = bean.getSLwfCode(); /* Lwf Code */

			result = getSqlModel().singleExecute(sQueryHeader, headerData);

			/* Update data in HRMS_LWF_SLAB_DTL */
			String sDeleteQuery = "DELETE FROM HRMS_LWF_SLAB_DTL WHERE LWF_CODE = "
					+ bean.getSLwfCode();
			result = getSqlModel().singleExecute(sDeleteQuery);

			String sDetailQuery = " INSERT INTO HRMS_LWF_SLAB_DTL (LWF_CODE, LWF_SLAB_FROM, LWF_SLAB_TO, LWF_EMP_CONTRIB, LWF_EMPLR_CONTRIB, LWF_DTL_CODE) "
					+ " VALUES (?,?,?,?,?,?) ";

			String[] aBasicFrm = request.getParameterValues("sBasicFrm");
			String[] aBasicTo = request.getParameterValues("sBasicTo");
			String[] aEmployeeContribution = request
					.getParameterValues("sEmployeeContribution");
			String[] aEmployerContribution = request
					.getParameterValues("sEmployerContribution");

			Object[][] detailData = new Object[aBasicFrm.length][6];
			for (int i = 0; i < detailData.length; i++) {
				detailData[i][0] = bean.getSLwfCode(); /* Lwf Code */
				detailData[i][1] = aBasicFrm[i]; /* From */
				detailData[i][2] = aBasicTo[i]; /* To */
				detailData[i][3] = aEmployeeContribution[i]; /*
																 * Employee
																 * Contribution
																 */
				detailData[i][4] = aEmployerContribution[i]; /*
																 * Employer
																 * Contribution
																 */
				detailData[i][5] = (i + 1); /* Lwf Dtl Code */
			}

			result = getSqlModel().singleExecute(sDetailQuery, detailData);
			/* --- Detail End --- */

			/* ---Retain data in list--- */
			LWFConfigurationMaster tempBean = null;
			int count = 1;
			List lst = new ArrayList();

			for (int i = 0; i < aBasicFrm.length; i++) {

				tempBean = new LWFConfigurationMaster();
				tempBean.setISrNo(count++);
				tempBean.setSBasicFrm(aBasicFrm[i]);
				tempBean.setSBasicTo(aBasicTo[i]);
				tempBean.setSEmployeeContribution(aEmployeeContribution[i]);
				tempBean.setSEmployerContribution(aEmployerContribution[i]);
				lst.add(tempBean);
			}
			bean.setLstSlabDefinition(lst);
			bean.setSDeductionMonthCode(bean.getSDeductionMonth());
			/* ---End--- */

			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public boolean checkAlreadyExist(LWFConfigurationMaster bean,
			HttpServletRequest request, String sMode) {
		try {
			String sQuery = "";

			if (sMode.equals("edit")) {

				sQuery = " SELECT * FROM HRMS_LWF_SLAB_HDR "
						+ " WHERE LWF_STATE_CODE = " + bean.getSStateCode()
						+ " AND " + " LWF_EFFECTIVE_FROM = TO_DATE('"
						+ bean.getSEffectiveDate() + "','DD-MM-YYYY')"
						+ " AND LWF_CODE != " + bean.getSLwfCode();

			} else {

				sQuery = " SELECT * FROM HRMS_LWF_SLAB_HDR "
						+ " WHERE LWF_STATE_CODE = " + bean.getSStateCode()
						+ " AND " + " LWF_EFFECTIVE_FROM = TO_DATE('"
						+ bean.getSEffectiveDate() + "','DD-MM-YYYY')";

			}

			/* ---Retain data in list--- */
			LWFConfigurationMaster tempBean = null;
			int count = 1;
			List lst = new ArrayList();

			String[] aBasicFrm = request.getParameterValues("sBasicFrm");
			String[] aBasicTo = request.getParameterValues("sBasicTo");
			String[] aEmployeeContribution = request
					.getParameterValues("sEmployeeContribution");
			String[] aEmployerContribution = request
					.getParameterValues("sEmployerContribution");

			for (int i = 0; i < aBasicFrm.length; i++) {

				tempBean = new LWFConfigurationMaster();
				tempBean.setISrNo(count++);
				tempBean.setSBasicFrm(aBasicFrm[i]);
				tempBean.setSBasicTo(aBasicTo[i]);
				tempBean.setSEmployeeContribution(aEmployeeContribution[i]);
				tempBean.setSEmployerContribution(aEmployerContribution[i]);
				lst.add(tempBean);
			}
			bean.setLstSlabDefinition(lst);
			bean.setSDeductionMonthCode(bean.getSDeductionMonth());
			/* ---End--- */

			Object[][] objData = getSqlModel().getSingleResult(sQuery);

			if (objData != null && objData.length > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public void showLwfDetals(LWFConfigurationMaster bean,
			HttpServletRequest request) {

		String query = "SELECT LWF_CODE,to_CHAR(LWF_EFFECTIVE_FROM,'DD-MM-YYYY'), LWF_STATE_CODE,LOCATION_NAME FROM HRMS_LWF_SLAB_HDR"
				+ "	INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_LWF_SLAB_HDR.LWF_STATE_CODE) "
				+ "	ORDER BY LWF_CODE DESC";

		Object[][] repData = getSqlModel().getSingleResult(query);
		if (repData != null && repData.length > 0) {
			bean.setModeLength("true");
			bean.setTotalRecords(String.valueOf(repData.length));
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
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
				bean.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				LWFConfigurationMaster bean1 = new LWFConfigurationMaster();
				bean1.setLwfID(checkNull(String.valueOf(repData[i][0])));
				bean1
						.setEffectiveFrom(checkNull(String
								.valueOf(repData[i][1])));
				bean1
						.setConfigureCode(checkNull(String
								.valueOf(repData[i][2])));
				bean1
						.setConfigureState(checkNull(String
								.valueOf(repData[i][3])));
				List.add(bean1);
			}// end of loop
			bean.setIteratorlist(List);
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

	/**
	 * Set filters to records which are coming from Employee Office
	 * 
	 * @param bean
	 * @param checkBoxVar
	 * @param sqlQuery
	 * @return String as sql query
	 */
	public Object[][] setEmpOffcFiletrs(LWFConfigurationMaster bean,
			String checkBoxVar) {
		try {
			String sqlQuery = "";
			String divCode = "";
			divCode = bean.getSDivCode();
			if (checkBoxVar.equals("D")) {
				sqlQuery = "SELECT RANK_ID, RANK_NAME FROM HRMS_RANK WHERE IS_ACTIVE ='Y'";

				/*
				 * sqlQuery = " SELECT DISTINCT EMP_RANK ,NVL(RANK_NAME,' ')
				 * FROM HRMS_EMP_OFFC " + " LEFT JOIN HRMS_DIVISION ON
				 * (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)" + " LEFT JOIN
				 * HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK AND
				 * HRMS_RANK.IS_ACTIVE ='Y' )" + " WHERE EMP_DIV IN (" + divCode + ") ";
				 */

			} else if (checkBoxVar.equals("G")){
				sqlQuery = "SELECT CADRE_ID, CADRE_NAME  FROM HRMS_CADRE WHERE CADRE_IS_ACTIVE = 'Y'";
			
				/*sqlQuery = "SELECT EMP_TOKEN , EMP_FNAME||' '|| EMP_LNAME FROM HRMS_EMP_OFFC "
				+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+" WHERE EMP_STATUS='S' AND EMP_DIV IN ("+bean.getSDivCode()+") AND EMP_CADRE IN(SELECT LWF_GRADE  FROM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE ="+bean.getLwfID()+" AND LWF_APPLICABLE='Y' AND LWF_GRADE  IS NOT NULL) "
				+" AND HRMS_CENTER.CENTER_LOCATION IN(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_PARENT_CODE="+bean.getSStateCode()+" )";
				*/
				/*
				 * sqlQuery = " SELECT DISTINCT EMP_CADRE,NVL(CADRE_NAME,' ')
				 * FROM HRMS_EMP_OFFC " + " LEFT JOIN HRMS_DIVISION ON
				 * (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)" + " LEFT JOIN
				 * HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE
				 * AND HRMS_CADRE.CADRE_IS_ACTIVE = 'Y') " + " WHERE EMP_DIV IN (" +
				 * divCode + ") ";
				 */
			}

			return getSqlModel().getSingleResult(sqlQuery);
		} catch (Exception e) {
			logger.error("Exception in setEmpOffcFiletrs:" + e);
			return null;
		} // end of try-catch block
	} // end of setEmpOffcFiletrs
	
	
	public Object[][] setEmpOffcCalFiletrs(LWFConfigurationMaster bean,
			String checkBoxVar) {
		try {
			String sqlQuery = "";
			String divCode = "";
			divCode = bean.getSDivCode();
			if (checkBoxVar.equals("D")) {
			//	sqlQuery = "SELECT RANK_ID, RANK_NAME FROM HRMS_RANK WHERE IS_ACTIVE ='Y'";
				sqlQuery = "SELECT HRMS_EMP_OFFC.EMP_TOKEN , HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME ,HRMS_RANK.RANK_NAME FROM HRMS_EMP_OFFC "
					+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
					+" WHERE EMP_STATUS='S' AND EMP_DIV IN ("+bean.getSDivCode()+") AND EMP_RANK IN(SELECT LWF_DESG FROM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE ="+bean.getLwfID()+" AND LWF_APPLICABLE='Y' AND LWF_DESG IS NOT NULL) "
					+" AND HRMS_CENTER.CENTER_LOCATION IN(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_PARENT_CODE="+bean.getSStateCode()+" )";
				
				/*
				 * sqlQuery = " SELECT DISTINCT EMP_RANK ,NVL(RANK_NAME,' ')
				 * FROM HRMS_EMP_OFFC " + " LEFT JOIN HRMS_DIVISION ON
				 * (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)" + " LEFT JOIN
				 * HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK AND
				 * HRMS_RANK.IS_ACTIVE ='Y' )" + " WHERE EMP_DIV IN (" + divCode + ") ";
				 */

			} else if (checkBoxVar.equals("G")){
				///sqlQuery = "SELECT CADRE_ID, CADRE_NAME  FROM HRMS_CADRE WHERE CADRE_IS_ACTIVE = 'Y'";
			
				sqlQuery = "SELECT HRMS_EMP_OFFC.EMP_TOKEN , HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME,HRMS_CADRE.CADRE_NAME FROM HRMS_EMP_OFFC "
				+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+"INNER JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
				+" WHERE EMP_STATUS='S' AND EMP_DIV IN ("+bean.getSDivCode()+") AND EMP_CADRE IN(SELECT LWF_GRADE  FROM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE ="+bean.getLwfID()+" AND LWF_APPLICABLE='Y' AND LWF_GRADE  IS NOT NULL) "
				+" AND HRMS_CENTER.CENTER_LOCATION IN(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_PARENT_CODE="+bean.getSStateCode()+" )";
				
				/*
				 * sqlQuery = " SELECT DISTINCT EMP_CADRE,NVL(CADRE_NAME,' ')
				 * FROM HRMS_EMP_OFFC " + " LEFT JOIN HRMS_DIVISION ON
				 * (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)" + " LEFT JOIN
				 * HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE
				 * AND HRMS_CADRE.CADRE_IS_ACTIVE = 'Y') " + " WHERE EMP_DIV IN (" +
				 * divCode + ") ";
				 */
			}

			return getSqlModel().getSingleResult(sqlQuery);
		} catch (Exception e) {
			logger.error("Exception in setEmpOffcFiletrs:" + e);
			return null;
		} // end of try-catch block
	} // end of setEmpOffcFiletrs
	
	

	public String[] lwfEmpInstructionMsg() {

		String[] msg = new String[20];
		msg[0] = "";
		msg[1] = " PeoplePower Data Migration Tool";
		msg[2] = "";
		msg[3] = " Instructions";
		msg[4] = "";
		msg[5] = " 1] Do not change the sheet names and sequece of sheets.";
		msg[6] = " 2] Do not change the sequence of columns.";
		msg[7] = " 3] The First row represents the Header information. Please do not change the header names manually.";
		msg[8] = " 4] The cells having * shows that it is a compulsory information and please do not keep the cell blank.";
		msg[9] = " 5] Make sure that the required master data records are already uploaded into the system.";
		msg[10] = " 6] The dates should be in mm/dd/yyyy format only.";
		msg[11] = "";
		msg[12] = " Steps to upload this file";
		msg[13] = "";
		msg[14] = " 1] Fill up all the requirement information into this tool";
		msg[15] = " 2] Upload this file into peoplepower system at Peoplepower --> Configuration --> Data migration";
		msg[16] = " 3] The system will verify the integrity of information.";
		msg[17] = " 4] If there are integrity problems, the system will provide the error log at the last column of the data sheet.";
		msg[18] = " 5] Correct the information accordingly and upload again.";
		msg[19] = " 6] Once the integrity check is sucessful, the data will be uploaded into the system.";

		return msg;
	}

	public void downloadCalculateFile(LWFConfigurationMaster bean,
			HttpServletResponse response, String checkBoxVar) {

		try {
			String[] msg = lwfEmpInstructionMsg();
			String title = "";
			if (checkBoxVar.equals("D")) {
				title = " Peoplepower_LWF_Designation_";
			} else {
				title = " Peoplepower_LWF_Grade_";
			}
			
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Xls", title, msg);
			// get all employee according to the filters

			/*
			 * int cellwidth[] = { 50, 50 }; int alignment[] = { 0, 0, };
			 * 
			 * rg.tableBodyWithColor(new Object[][]{{"Designation",
			 * "Applicability"}}, new Object[][]{{"Designation",
			 * "Applicability","DDDDDD", "AAAAAA", "DDDDDD"}, {"Designation",
			 * "Applicability","DDDDDD", "AAAAAA", "DDDDDD"}}, cellwidth,
			 * alignment, new Object[][]{{Utility.YELLOW}});
			 * rg.createReport(response);
			 */

			// get all employee according to the filters
			Object[][] empObj = setEmpOffcFiletrs(bean, checkBoxVar);
			String concatEmployeeIds = "";
			Object[][] attData = null;

			if (empObj != null && empObj.length > 0) {
				for (int i = 0; i < empObj.length; i++) {
					if (i == (empObj.length - 1)) {
						concatEmployeeIds += String.valueOf(empObj[i][1]);
					} else {
						concatEmployeeIds += String.valueOf(empObj[i][1]) + ",";
					}
				}
			} // end of if for canocat all empId
			String attWorkFlow = "";
			String leaveWorkFlow = "";
			String recoveryWorkFlow = "";
			int dynamicCol = 0;
			if (recoveryWorkFlow.equals("Y")) {
				dynamicCol = 1;
			}
			Object[][] totalObj = new Object[empObj.length][7 + dynamicCol];
			Object[][] totalNewObj = new Object[0][];
			// for attendance late marks and half days
			for (int i = 0; i < empObj.length; i++) {
				String empDiv = String.valueOf(empObj[i][1]);
				String empRank = String.valueOf(empObj[i][1]);
				// String empDesig = String.valueOf(empObj[i][5]);
				/*
				 * String empDesg = String.valueOf(empObj[i][6]); String
				 * empBranch = String.valueOf(empObj[i][7]); String empType =
				 * String.valueOf(empObj[i][8]);
				 */

				totalObj[i][0] = empObj[i][1]; // Division
				// totalObj[i][1] = empObj[i][3]; // Designation
				// totalObj[i][2] = empObj[i][5]; // grade

				// for late marks and half days
				boolean attFlag = false;

				if (attWorkFlow.equals("Y")) {
					if (attData != null && attData.length > 0) {
						for (int j = 0; j < attData.length; j++) {
							if (empObj[i][1].equals(attData[j][0])) {
								totalObj[i][1] = attData[j][1]; // Applicability

								attFlag = true;
								break;
							}
						}
					}
				} else {
					totalObj[i][1] = "";

				}
				if (attFlag == false) {
					totalObj[i][1] = ""; // blank Applicability

				}

			}
			try {
				if (checkBoxVar.equals("D")) {
					
					String abc[] = { "Desgination", "Applicability" };
					int cellwidth[] = { 50, 50 };
					int alignment[] = { 0, 0, };
					int colsAsDouble[] = { 3, 4 };

					String abc1[] = { "*", "( yes/no )" };
					int cellwidth1[] = { 50, 50 };
					int alignment1[] = { 0, 0, };
					int colsAsDouble1[] = { 3, 4 };

					rg.tableBodyAsText(abc, totalNewObj, cellwidth, alignment,
							colsAsDouble);
					rg.tableBodyAsText(abc1, totalObj, cellwidth1, alignment1,
							colsAsDouble1);
				} else {
					
					String abc[] = { "Grade", "Applicability " };
					int cellwidth[] = { 50, 50 };
					int alignment[] = { 0, 0, };
					int colsAsDouble[] = { 3, 4 };

					String abc1[] = { "*", "( yes/no )" };
					int cellwidth1[] = { 50, 50 };
					int alignment1[] = { 0, 0, };
					int colsAsDouble1[] = { 3, 4 };

					rg.tableBodyAsText(abc, totalNewObj, cellwidth, alignment,
							colsAsDouble);
					rg.tableBodyAsText(abc1, totalObj, cellwidth1, alignment1,
							colsAsDouble1);
				}
				rg.createReport(response);
			} catch (Exception e) {
				logger.error("Exception in downloadCalculateFile:" + e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public void calforedit(LWFConfigurationMaster bean,
			HttpServletRequest request, String checkBoxAllEmpVar) {
		try {

			/* Get data from 'HRMS_LWF_SLAB_HDR' */
			String sQuery = " SELECT LWF_CODE, TO_CHAR(LWF_EFFECTIVE_FROM,'DD-MM-YYYY'), LWF_STATE_CODE, LOCATION_NAME, LWF_MONTH_DEDUCTIONS ,"
					+ "  LWF_DEBIT_CODE ,NVL(DEBIT_NAME,' '),LWF_EMP_ALL_APP ,LWF_TEMPLATE_OPTION "
					//+ ",HRMS_LWF_EMP_APPLICABLE.LWF_DIV ,DIV_NAME "
					+ " FROM HRMS_LWF_SLAB_HDR A INNER JOIN HRMS_LOCATION B "
					+ " ON (A.LWF_STATE_CODE = B.LOCATION_CODE) "
					+ "LEFT JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = A.LWF_DEBIT_CODE) "
					//+ "LEFT JOIN HRMS_LWF_EMP_APPLICABLE ON (HRMS_LWF_EMP_APPLICABLE.LWF_CODE = A.LWF_CODE)"
					//+ "INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_LWF_EMP_APPLICABLE.LWF_DIV)"
					+ "WHERE LWF_CODE = " + bean.getLwfID();

			Object[][] objData = getSqlModel().getSingleResult(sQuery);

			if (objData != null) {
				bean.setLwfID(String.valueOf(objData[0][0]));
				bean.setSEffectiveDate(String.valueOf(objData[0][1]));
				bean.setSStateCode(String.valueOf(objData[0][2]));
				bean.setSState(String.valueOf(objData[0][3]));
				bean.setSDeductionMonthCode(String.valueOf(objData[0][4]));

				bean.setSLwfDebitHeadCode(checkNull(String
						.valueOf(objData[0][5])));
				bean.setSLwfDebitHead(String.valueOf(objData[0][6]));
				
				
				if (String.valueOf(objData[0][7]).equals("Y")) {
					bean.setSApplicableAll("true");
					bean.setHiddenChechAllEmpfrmId("Y");
					/* Get data from 'HRMS_LWF_SLAB_HDR' */
					String sAllEmpQuery = " SELECT DISTINCT LWF_DIV,DIV_NAME, LWF_CODE  FROM HRMS_LWF_EMP_APPLICABLE INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_LWF_EMP_APPLICABLE.LWF_DIV) WHERE LWF_CODE =" +bean.getLwfID();

					Object[][] objAllEmpData = getSqlModel().getSingleResult(sAllEmpQuery);
					if(objAllEmpData != null && objAllEmpData.length > 0) {
					String divcode="";
					String divName="";
					
					for (int j2 = 0; j2 < objAllEmpData.length; j2++) {
					
						divcode+=objAllEmpData[j2][0]+",";
						divName+=objAllEmpData[j2][1]+",";
						//bean.setSDivName(String.valueOf(objAllEmpData[j2][1]));
					}
					divcode = divcode.substring(0, divcode
							.length() - 1);
					
					
					
					divName = divName.substring(0, divName
							.length() - 1);
					
					
					bean.setSDivCode(String.valueOf(divcode));
					bean.setSDivName(String.valueOf(divName));
					
					bean.setLwfID(String.valueOf(objAllEmpData[0][2]));
					}
				} else {
					bean.setSApplicableAll("false");
					
					/* Get data from 'HRMS_LWF_SLAB_HDR' */
					String sAllEmpQuery = "SELECT DISTINCT LWF_DIV,DIV_NAME, LWF_CODE  FROM HRMS_LWF_EMP_APPLICABLE INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_LWF_EMP_APPLICABLE.LWF_DIV) WHERE LWF_CODE =" +bean.getLwfID();

					Object[][] objAllEmpData = getSqlModel().getSingleResult(sAllEmpQuery);
					
					String divcode="";
					String divName="";
					if(objAllEmpData != null && objAllEmpData.length > 0) {
					for (int j2 = 0; j2 < objAllEmpData.length; j2++) {
					
						divcode+=objAllEmpData[j2][0]+",";
						divName+=objAllEmpData[j2][1]+",";
						//bean.setSDivName(String.valueOf(objAllEmpData[j2][1]));
					}
					divcode = divcode.substring(0, divcode
							.length() - 1);
					
					
					
					divName = divName.substring(0, divName
							.length() - 1);
					
					
					bean.setSDivCode(String.valueOf(divcode));
					bean.setSDivName(String.valueOf(divName));
					
					bean.setLwfID(String.valueOf(objAllEmpData[0][2]));
					}
					
				}
				
				if (String.valueOf(objData[0][8]).equals("D")) {
					bean.setDesignation("true");
					bean.setHiddenChechfrmId("D");
				}
				if (String.valueOf(objData[0][8]).equals("G")) {
					bean.setGrade("true");
					bean.setHiddenChechfrmId("G");
				}
				
				/*bean.setSLwfDebitHead(String.valueOf(objData[0][9]));
				bean.setSLwfDebitHead(String.valueOf(objData[0][10]));*/
				
				/*String divcode="";
				String divName="";
				
				for (int j2 = 0; j2 < objData.length; j2++) {
				
					divcode+=objData[j2][9]+",";
					divName+=objData[j2][10]+",";
					//bean.setSDivName(String.valueOf(objAllEmpData[j2][1]));
				}
				divcode = divcode.substring(0, divcode
						.length() - 1);
				System.out.println("divcode==" + divcode);
				divName = divName.substring(0, divName
						.length() - 1);
				
				
				bean.setSDivCode(String.valueOf(divcode));
				bean.setSDivName(String.valueOf(divName));*/

				bean.setHiddencode(String.valueOf(objData[0][0]));
			}

			/* Get data from HRMS_LWF_SLB_DTL */
			String sQueryDtl = " SELECT LWF_SLAB_FROM, LWF_SLAB_TO, LWF_EMP_CONTRIB, LWF_EMPLR_CONTRIB , LWF_DTL_CODE "
					+ " FROM HRMS_LWF_SLAB_DTL "
					+ " WHERE LWF_CODE = '"
					+ bean.getLwfID() + "' ORDER BY LWF_DTL_CODE";

			Object[][] dtlData = getSqlModel().getSingleResult(sQueryDtl);
			LWFConfigurationMaster tmpBean = null;
			List lst = new ArrayList();

			for (int i = 0; i < dtlData.length; i++) {
				tmpBean = new LWFConfigurationMaster();
				tmpBean.setISrNo(i + 1);
				tmpBean.setSBasicFrm(String.valueOf(dtlData[i][0]));
				tmpBean.setSBasicTo(String.valueOf(dtlData[i][1]));
				tmpBean.setSEmployeeContribution(String.valueOf(dtlData[i][2]));
				tmpBean.setSEmployerContribution(String.valueOf(dtlData[i][3]));

				lst.add(tmpBean);
			}
			bean.setLstSlabDefinition(lst);
						
			/* To set Total employee in Division */
			String sTotaldIVQuery = "select DISTINCT LWF_DIV froM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE =" +bean.getLwfID();
			Object[][] objTotalDivData = getSqlModel().getSingleResult(sTotaldIVQuery);
			
			String divTotal = "";
			for (int i = 0; i < objTotalDivData.length; i++) {
				divTotal +=objTotalDivData[i][0]+",";
				
			}
			
			divTotal = divTotal.substring(0, divTotal
					.length() - 1);
			
			/* To set Total employee in Division */
			String sTotalEmpQuery = "SELECT COUNT(EMP_ID) FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' AND EMP_DIV IN ("+divTotal+")";
			Object[][] objTotalEmpData = getSqlModel().getSingleResult(sTotalEmpQuery);

			if (objTotalEmpData != null) { 
					bean.setTotalEmpDiv(String.valueOf(objTotalEmpData[0][0]));
			}
			/* To set Total employee Applicable for LWF in selected Division  */
			String sTotalEmpLWFQuery = "SELECT COUNT(EMP_TOKEN) FROM HRMS_EMP_OFFC "
				+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+" WHERE EMP_STATUS='S' AND EMP_DIV IN ("+divTotal+") "
				+" AND HRMS_CENTER.CENTER_LOCATION IN(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_PARENT_CODE="+bean.getSStateCode()+" )";
			
			
			if(bean.getHiddenChechfrmId().equals("D")){
			 sTotalEmpLWFQuery = "SELECT COUNT(EMP_TOKEN) FROM HRMS_EMP_OFFC "
								+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
								+" WHERE EMP_STATUS='S' AND EMP_DIV IN ("+divTotal+") AND EMP_RANK IN(SELECT LWF_DESG FROM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE ="+bean.getLwfID()+" AND LWF_APPLICABLE='Y' AND LWF_DESG IS NOT NULL) "
								+" AND HRMS_CENTER.CENTER_LOCATION IN(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_PARENT_CODE="+bean.getSStateCode()+" )";
			}if(bean.getHiddenChechfrmId().equals("G")){
				sTotalEmpLWFQuery = "SELECT COUNT(EMP_TOKEN) FROM HRMS_EMP_OFFC "
					+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+" WHERE EMP_STATUS='S' AND EMP_DIV IN ("+divTotal+") AND EMP_CADRE IN(SELECT LWF_GRADE  FROM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE ="+bean.getLwfID()+" AND LWF_APPLICABLE='Y' AND LWF_GRADE  IS NOT NULL) "
					+" AND HRMS_CENTER.CENTER_LOCATION IN(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_PARENT_CODE="+bean.getSStateCode()+" )";
			}
			
			
			
			Object[][] objTotalEmpLWFData = getSqlModel().getSingleResult(sTotalEmpLWFQuery);

			if (objTotalEmpLWFData != null) {
				bean.setTotalEmpLWFDiv(String.valueOf(objTotalEmpLWFData[0][0]));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean saveFunction(LWFConfigurationMaster bean,
			HttpServletRequest request, String checkBoxAllEmpVar) {
		try {
			int iLwfId = 0;
			boolean result = false;

			/* Get Max for LWF_CODE */
			String sQuery = "SELECT NVL(MAX(LWF_CODE),0) + 1 FROM HRMS_LWF_SLAB_HDR";
			Object[][] objData = getSqlModel().getSingleResult(sQuery);

			if (objData != null) {
				iLwfId = Integer.parseInt(String.valueOf(objData[0][0]));
				bean.setLwfID(String.valueOf(iLwfId));
			
			}

			/* Insert Header Table Information */
			String sHeaderQuery;

			Object[][] headerData;
			try {
				sHeaderQuery = " INSERT INTO HRMS_LWF_SLAB_HDR (LWF_CODE, LWF_EFFECTIVE_FROM, LWF_STATE_CODE, LWF_MONTH_DEDUCTIONS,LWF_DEBIT_CODE,LWF_EMP_ALL_APP) "
						+ " VALUES (?, TO_DATE(?,'DD-MM-YYYY'), ?, TO_CHAR(?),?,?) ";
				headerData = new Object[1][6];
				headerData[0][0] = iLwfId; /* Lwf Id */
				headerData[0][1] = bean.getSEffectiveDate(); /* Effective Date */
				headerData[0][2] = bean.getSStateCode(); /* State */
				headerData[0][3] = bean.getSDeductionMonth(); /* Deduction Month */
				headerData[0][4] = bean.getSLwfDebitHeadCode(); /* Debit Head Code */
				//headerData[0][5] = bean.getSLwfDebitHeadCode(); /* Debit Head Code */
				if (bean.getSApplicableAll().equals("true")) {
					headerData[0][5] = "Y";
				} else {
					headerData[0][5] = "N";
				}
			
			
			result = getSqlModel().singleExecute(sHeaderQuery, headerData);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			/* --- Header End --- */

			/* Insert Detail Table Information */
			String sDetailQuery = " INSERT INTO HRMS_LWF_SLAB_DTL (LWF_CODE, LWF_SLAB_FROM, LWF_SLAB_TO, LWF_EMP_CONTRIB, LWF_EMPLR_CONTRIB, LWF_DTL_CODE) "
					+ " VALUES (?,?,?,?,?,?) ";

			String[] aBasicFrm = request.getParameterValues("sBasicFrm");
			String[] aBasicTo = request.getParameterValues("sBasicTo");
			String[] aEmployeeContribution = request
					.getParameterValues("sEmployeeContribution");
			String[] aEmployerContribution = request
					.getParameterValues("sEmployerContribution");

			Object[][] detailData = new Object[aBasicFrm.length][6];
			for (int i = 0; i < detailData.length; i++) {
				detailData[i][0] = iLwfId; /* Lwf Code */
				detailData[i][1] = aBasicFrm[i]; /* From */
				detailData[i][2] = aBasicTo[i]; /* To */
				detailData[i][3] = aEmployeeContribution[i]; /*
																 * Employee
																 * Contribution
																 */
				detailData[i][4] = aEmployerContribution[i]; /*
																 * Employer
																 * Contribution
																 */
				detailData[i][5] = (i + 1); /* Lwf Dtl Code */
			}
			result = getSqlModel().singleExecute(sDetailQuery, detailData);
						
			/* --- Detail End --- */

			/* ---Retain data in list--- */
			LWFConfigurationMaster tempBean = null;
			int count = 1;
			List lst = new ArrayList();

			for (int i = 0; i < aBasicFrm.length; i++) {

				tempBean = new LWFConfigurationMaster();
				tempBean.setISrNo(count++);
				tempBean.setSBasicFrm(aBasicFrm[i]);
				tempBean.setSBasicTo(aBasicTo[i]);
				tempBean.setSEmployeeContribution(aEmployeeContribution[i]);
				tempBean.setSEmployerContribution(aEmployerContribution[i]);
				lst.add(tempBean);
			}
			bean.setLstSlabDefinition(lst);
			bean.setSDeductionMonthCode(bean.getSDeductionMonth());
			/* ---End--- */
			
			
			if(checkBoxAllEmpVar.equals("Y")){
				
				/* Delete */
				String delQuery = "DELETE FROM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE="
						+ iLwfId;
				getSqlModel().singleExecute(delQuery);

				/* Insert HRMS_LWF_EMP_APPLICABLE Table Information */

				String[] multipleDivCode = String.valueOf(bean.getSDivCode())
						.split(",");
			
				for (int j = 0; j < multipleDivCode.length; j++) {

					String sDivHeaderQuery = " INSERT INTO HRMS_LWF_EMP_APPLICABLE ( LWF_APPLICABLE, LWF_DIV, LWF_CODE) "
								+ " VALUES ('Y'," + multipleDivCode[j] + ","+iLwfId+") ";

					getSqlModel().singleExecute(sDivHeaderQuery);
					bean.setViewUploadFileFlag(true);
				}
				
			}
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateRecords(LWFConfigurationMaster bean,
			HttpServletRequest request, String checkBoxAllEmpVar) {

		try {
			/* Update data from 'HRMS_LWF_SLAB_HDR' */
			boolean result = false;
			String sQueryHeader = " UPDATE HRMS_LWF_SLAB_HDR SET LWF_EFFECTIVE_FROM = TO_DATE(?,'DD-MM-YYYY'), LWF_STATE_CODE = ?, "
					+ " LWF_MONTH_DEDUCTIONS = ? ,LWF_DEBIT_CODE =? , LWF_EMP_ALL_APP = ? WHERE LWF_CODE = ? ";

			Object[][] headerData = new Object[1][6];
			headerData[0][0] = bean.getSEffectiveDate(); /* Effective Date */
			headerData[0][1] = bean.getSStateCode(); /* State */
			headerData[0][2] = bean.getSDeductionMonth(); /* Deduction Month */
			headerData[0][3] = bean.getSLwfDebitHeadCode(); /* DebitHead Code */
			
			if (bean.getSApplicableAll().equals("true")) {
				headerData[0][4] =  "Y";
			} else {
				headerData[0][4] = "N";
			}
			
			headerData[0][5] = bean.getLwfID(); /* Lwf Code */
			
			result = getSqlModel().singleExecute(sQueryHeader, headerData);

			/* Update data in HRMS_LWF_SLAB_DTL */
			String sDeleteQuery = "DELETE FROM HRMS_LWF_SLAB_DTL WHERE LWF_CODE = "
					+ bean.getLwfID();
			result = getSqlModel().singleExecute(sDeleteQuery);

			String sDetailQuery = " INSERT INTO HRMS_LWF_SLAB_DTL (LWF_CODE, LWF_SLAB_FROM, LWF_SLAB_TO, LWF_EMP_CONTRIB, LWF_EMPLR_CONTRIB, LWF_DTL_CODE) "
					+ " VALUES (?,?,?,?,?,?) ";

			String[] aBasicFrm = request.getParameterValues("sBasicFrm");
			String[] aBasicTo = request.getParameterValues("sBasicTo");
			String[] aEmployeeContribution = request
					.getParameterValues("sEmployeeContribution");
			String[] aEmployerContribution = request
					.getParameterValues("sEmployerContribution");

			Object[][] detailData = new Object[aBasicFrm.length][6];
			for (int i = 0; i < detailData.length; i++) {
				detailData[i][0] = bean.getLwfID(); /* Lwf Code */
				detailData[i][1] = aBasicFrm[i]; /* From */
				detailData[i][2] = aBasicTo[i]; /* To */
				detailData[i][3] = aEmployeeContribution[i]; /*
																 * Employee
																 * Contribution
																 */
				detailData[i][4] = aEmployerContribution[i]; /*
																 * Employer
																 * Contribution
																 */
				detailData[i][5] = (i + 1); /* Lwf Dtl Code */
			}

			result = getSqlModel().singleExecute(sDetailQuery, detailData);
			/* --- Detail End --- */

			/* ---Retain data in list--- */
			LWFConfigurationMaster tempBean = null;
			int count = 1;
			List lst = new ArrayList();

			for (int i = 0; i < aBasicFrm.length; i++) {

				tempBean = new LWFConfigurationMaster();
				tempBean.setISrNo(count++);
				tempBean.setSBasicFrm(aBasicFrm[i]);
				tempBean.setSBasicTo(aBasicTo[i]);
				tempBean.setSEmployeeContribution(aEmployeeContribution[i]);
				tempBean.setSEmployerContribution(aEmployerContribution[i]);
				lst.add(tempBean);
			}
			bean.setLstSlabDefinition(lst);
			bean.setSDeductionMonthCode(bean.getSDeductionMonth());
			/* ---End--- */

if(checkBoxAllEmpVar.equals("Y")){
				
				/* Delete */
				String delQuery = "DELETE FROM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE="
						+ bean.getLwfID();
				getSqlModel().singleExecute(delQuery);

				/* Insert HRMS_LWF_EMP_APPLICABLE Table Information */

				String[] multipleDivCode = String.valueOf(bean.getSDivCode())
						.split(",");
				for (int j = 0; j < multipleDivCode.length; j++) {

					String sDivHeaderQuery = " INSERT INTO HRMS_LWF_EMP_APPLICABLE ( LWF_APPLICABLE, LWF_DIV, LWF_CODE) "
								+ " VALUES ('Y'," + multipleDivCode[j] + ","+bean.getLwfID()+") ";

					getSqlModel().singleExecute(sDivHeaderQuery);
					bean.setViewUploadFileFlag(true);
				}
				
			}

			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public String deletecheckedHDRRecords(LWFConfigurationMaster bean,
			String[] code) {

		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(1), delete);
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
					result = getSqlModel().singleExecute(getQuery(2), delete);
					if (!result) {
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
					result = getSqlModel().singleExecute(getQuery(3), delete);
					if (!result) {
						count2++;
					}// end of if
				}// end of nested if
			}// end of loop

		}// end of nested if

		if (count != 0 || count1 != 0|| count2 != 0)
			return "false";
		else
			return "true";

	}

	public void getLwfConfigRecord(LWFConfigurationMaster bean,
			HttpServletRequest request) {
		try {

			/* Get data from 'HRMS_LWF_SLAB_HDR' */
			String sQuery = " SELECT LWF_CODE, TO_CHAR(LWF_EFFECTIVE_FROM,'DD-MM-YYYY'), LWF_STATE_CODE, LOCATION_NAME, LWF_MONTH_DEDUCTIONS ,"
					+ "  LWF_DEBIT_CODE ,NVL(DEBIT_NAME,' '),LWF_EMP_ALL_APP ,LWF_TEMPLATE_OPTION  "
					+ " FROM HRMS_LWF_SLAB_HDR A INNER JOIN HRMS_LOCATION B "
					+ " ON (A.LWF_STATE_CODE = B.LOCATION_CODE) "
					+ "LEFT JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = A.LWF_DEBIT_CODE) "
					+ "WHERE LWF_CODE = " + bean.getLwfID();

			Object[][] objData = getSqlModel().getSingleResult(sQuery);

			if (objData != null) {
				bean.setSLwfCode(String.valueOf(objData[0][0]));
				bean.setSEffectiveDate(String.valueOf(objData[0][1]));
				bean.setSStateCode(String.valueOf(objData[0][2]));
				bean.setSState(String.valueOf(objData[0][3]));
				bean.setSDeductionMonthCode(String.valueOf(objData[0][4]));

				bean.setSLwfDebitHeadCode(String.valueOf(objData[0][5]));
				bean.setSLwfDebitHead(String.valueOf(objData[0][6]));
				
				if (String.valueOf(objData[0][7]).equals("Y")) {
					bean.setSApplicableAll("true");
					bean.setHiddenChechAllEmpfrmId("Y");
					/* Get data from 'HRMS_LWF_SLAB_HDR' */
					String sAllEmpQuery = " SELECT DISTINCT LWF_DIV,DIV_NAME, LWF_CODE  FROM HRMS_LWF_EMP_APPLICABLE INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_LWF_EMP_APPLICABLE.LWF_DIV) WHERE LWF_CODE =" +bean.getLwfID();

					Object[][] objAllEmpData = getSqlModel().getSingleResult(sAllEmpQuery);
					
					String divcode="";
					String divName="";
					if(objAllEmpData != null && objAllEmpData.length > 0) {
					for (int j2 = 0; j2 < objAllEmpData.length; j2++) {
					
						divcode+=objAllEmpData[j2][0]+",";
						divName+=objAllEmpData[j2][1]+",";
						//bean.setSDivName(String.valueOf(objAllEmpData[j2][1]));
					}
					divcode = divcode.substring(0, divcode
							.length() - 1);
					
					divName = divName.substring(0, divName
							.length() - 1);
					
					
					bean.setSDivCode(String.valueOf(divcode));
					bean.setSDivName(String.valueOf(divName));
					
					bean.setLwfID(String.valueOf(objAllEmpData[0][2]));
					}
				} else {
					bean.setSApplicableAll("false");
					
					/* Get data from 'HRMS_LWF_SLAB_HDR' */
					String sAllEmpQuery = " SELECT DISTINCT LWF_DIV,DIV_NAME, LWF_CODE  FROM HRMS_LWF_EMP_APPLICABLE INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_LWF_EMP_APPLICABLE.LWF_DIV) WHERE LWF_CODE =" +bean.getLwfID();

					Object[][] objAllEmpData = getSqlModel().getSingleResult(sAllEmpQuery);
					
					String divcode="";
					String divName="";
					if(objAllEmpData != null && objAllEmpData.length > 0) {
					for (int j2 = 0; j2 < objAllEmpData.length; j2++) {
					
						divcode+=objAllEmpData[j2][0]+",";
						divName+=objAllEmpData[j2][1]+",";
						//bean.setSDivName(String.valueOf(objAllEmpData[j2][1]));
					}
					divcode = divcode.substring(0, divcode
							.length() - 1);
					
					divName = divName.substring(0, divName
							.length() - 1);
					
					bean.setSDivCode(String.valueOf(divcode));
					bean.setSDivName(String.valueOf(divName));
					
					bean.setLwfID(String.valueOf(objAllEmpData[0][2]));
					}
				}
				
				if (String.valueOf(objData[0][8]).equals("D")) {
					bean.setDesignation("true");
					bean.setHiddenChechfrmId("D");
				}
				if (String.valueOf(objData[0][8]).equals("G")) {
					bean.setGrade("true");
					bean.setHiddenChechfrmId("G");
				}

				bean.setHiddencode(String.valueOf(objData[0][0]));
			}

			/* Get data from HRMS_LWF_SLB_DTL */
			String sQueryDtl = " SELECT LWF_SLAB_FROM, LWF_SLAB_TO, LWF_EMP_CONTRIB, LWF_EMPLR_CONTRIB , LWF_DTL_CODE "
					+ " FROM HRMS_LWF_SLAB_DTL "
					+ " WHERE LWF_CODE = '"
					+ bean.getLwfID() + "' ORDER BY LWF_DTL_CODE";

			Object[][] dtlData = getSqlModel().getSingleResult(sQueryDtl);
			LWFConfigurationMaster tmpBean = null;
			List lst = new ArrayList();

			for (int i = 0; i < dtlData.length; i++) {
				tmpBean = new LWFConfigurationMaster();
				tmpBean.setISrNo(i + 1);
				tmpBean.setSBasicFrm(String.valueOf(dtlData[i][0]));
				tmpBean.setSBasicTo(String.valueOf(dtlData[i][1]));
				tmpBean.setSEmployeeContribution(String.valueOf(dtlData[i][2]));
				tmpBean.setSEmployerContribution(String.valueOf(dtlData[i][3]));

				lst.add(tmpBean);
			}
			bean.setLstSlabDefinition(lst);
			
			
			/* To set Total employee in Division */
			String sTotaldIVQuery = "select DISTINCT LWF_DIV froM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE =" +bean.getLwfID();
			Object[][] objTotalDivData = getSqlModel().getSingleResult(sTotaldIVQuery);
			String divTotal = "";
			for (int i = 0; i < objTotalDivData.length; i++) {
				divTotal +=objTotalDivData[i][0]+",";
			}
			
			divTotal = divTotal.substring(0, divTotal
					.length() - 1);
			
			/* To set Total employee in Division */
			String sTotalEmpQuery = "SELECT COUNT(EMP_ID) FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' AND EMP_DIV IN ("+divTotal+")";
			Object[][] objTotalEmpData = getSqlModel().getSingleResult(sTotalEmpQuery);

			if (objTotalEmpData != null) { 
					bean.setTotalEmpDiv(String.valueOf(objTotalEmpData[0][0]));
			}
			/* To set Total employee Applicable for LWF in selected Division  */
			String sTotalEmpLWFQuery = "SELECT COUNT(EMP_TOKEN) FROM HRMS_EMP_OFFC "
				+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+" WHERE EMP_STATUS='S' AND EMP_DIV IN ("+divTotal+") "
				+" AND HRMS_CENTER.CENTER_LOCATION IN(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_PARENT_CODE="+bean.getSStateCode()+" )";
			
			
			if(bean.getHiddenChechfrmId().equals("D")){
			 sTotalEmpLWFQuery = "SELECT COUNT(EMP_TOKEN) FROM HRMS_EMP_OFFC "
								+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
								+" WHERE EMP_STATUS='S' AND EMP_DIV IN ("+divTotal+") AND EMP_RANK IN(SELECT LWF_DESG FROM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE ="+bean.getLwfID()+" AND LWF_APPLICABLE='Y' AND LWF_DESG IS NOT NULL) "
								+" AND HRMS_CENTER.CENTER_LOCATION IN(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_PARENT_CODE="+bean.getSStateCode()+" )";
			}if(bean.getHiddenChechfrmId().equals("G")){
				sTotalEmpLWFQuery = "SELECT COUNT(EMP_TOKEN) FROM HRMS_EMP_OFFC "
					+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+" WHERE EMP_STATUS='S' AND EMP_DIV IN ("+divTotal+") AND EMP_CADRE IN(SELECT LWF_GRADE  FROM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE ="+bean.getLwfID()+" AND LWF_APPLICABLE='Y' AND LWF_GRADE  IS NOT NULL) "
					+" AND HRMS_CENTER.CENTER_LOCATION IN(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_PARENT_CODE="+bean.getSStateCode()+" )";
			}
			
			Object[][] objTotalEmpLWFData = getSqlModel().getSingleResult(sTotalEmpLWFQuery);

			if (objTotalEmpLWFData != null) {
				bean.setTotalEmpLWFDiv(String.valueOf(objTotalEmpLWFData[0][0]));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean uploadEmpAplicabilityDetails(HttpServletResponse response,
			HttpServletRequest request, LWFConfigurationMaster bean,
			String checkBoxVar) {

		try {
			
			String sQueryHeader = " UPDATE HRMS_LWF_SLAB_HDR SET LWF_TEMPLATE_OPTION=? , LWF_EMP_ALL_APP = ? WHERE LWF_CODE = ? ";

		Object[][] headerData = new Object[1][3];
			
		headerData[0][0] = bean.getHiddenChechfrmId();
		if (bean.getSApplicableAll().equals("true")) {
			headerData[0][1] =  "Y";
		} else {
			headerData[0][1] = "N";
		}
		
		/*if (bean.getHiddenChechfrmId().equals("true")) {
			headerData[0][0] =  "D";
		} else {
			headerData[0][0] = "";
		}
		if (bean.getGrade().equals("true")) {
			headerData[0][0] =  "G";
		} else {
			headerData[0][0] = "";
		}*/
		
		headerData[0][2] = bean.getLwfID(); /* Lwf Code */
		
		getSqlModel().singleExecute(sQueryHeader, headerData);
			
			
			boolean result = false;
			String divCode = "";
			divCode = bean.getSDivCode();

			String filePath = context.getRealPath("/") + "pages/images/"
					+ session.getAttribute("session_pool") + "/attendance/"
					+ bean.getUploadFileName();

			// bean.setUploadName("AssetMaster");
			// to create object of the file
			MigrateExcelData.getFile(filePath);
			/**
			 * Get column numbers with mandatory information
			 */
			HashMap<Integer, Boolean> columnInformation = MigrateExcelData
					.isColumnsMandatory();

			Object[][] desgTypeMaster = getSqlModel()
					.getSingleResult(
							" SELECT RANK_ID, RANK_NAME FROM HRMS_RANK WHERE IS_ACTIVE ='Y'");

			Object[][] gradeTypeMaster = getSqlModel()
					.getSingleResult(
							" SELECT CADRE_ID, CADRE_NAME  FROM HRMS_CADRE WHERE CADRE_IS_ACTIVE = 'Y'");

			Object[][] desgTypeObj = null;

			Object[][] gradeTypeObj = null;

			if (checkBoxVar.equals("D")) {
				if (desgTypeMaster != null && desgTypeMaster.length > 0) {
					desgTypeObj = MigrateExcelData.uploadExcelData(1,
							desgTypeMaster, MigrateExcelData.MASTER_TYPE,
							columnInformation.get(1));

				}
			}

			if (checkBoxVar.equals("G")) {
				if (gradeTypeMaster != null && gradeTypeMaster.length > 0) {
					gradeTypeObj = MigrateExcelData.uploadExcelData(1,
							gradeTypeMaster, MigrateExcelData.MASTER_TYPE,
							columnInformation.get(1));

				}
			}

			Object[][] Obj = null;
			if (checkBoxVar.equals("D")) {
				Obj = new Object[desgTypeObj.length][3];
			}
			if (checkBoxVar.equals("G")) {
				Obj = new Object[gradeTypeObj.length][3];

			}

			Object[][] applicability = MigrateExcelData.uploadExcelData(2,
					null, MigrateExcelData.STRING_TYPE, columnInformation
							.get(2));
			
			if (checkBoxVar.equals("D")) {
				for (int i = 0; i < desgTypeObj.length; i++) {

					Obj[i][0] = desgTypeObj[i][0];

					// Obj[i][1] = applicability[i][0];

					if (applicability[i][0].equals("Yes")
							|| applicability[i][0].equals("yes")|| applicability[i][0].equals("y")|| applicability[i][0].equals("Y")|| applicability[i][0].equals("YES")) {
						Obj[i][1] = "Y";
					} else  {
						Obj[i][1] = "N";
					}

					// Obj[i][2] ="";
					Obj[i][2] = bean.getLwfID();

				}
			}
			if (checkBoxVar.equals("G")) {
				for (int i = 0; i < gradeTypeObj.length; i++) {

					Obj[i][0] = gradeTypeObj[i][0];

					// Obj[i][1] = applicability[i][0];

					if (applicability[i][0].equals("Yes")
							|| applicability[i][0].equals("yes")|| applicability[i][0].equals("y")|| applicability[i][0].equals("Y")|| applicability[i][0].equals("YES")) {
						Obj[i][1] = "Y";
					} else {
						Obj[i][1] = "N";
					}

					// Obj[i][2] ="";
					Obj[i][2] = bean.getLwfID();

				}
			}

			boolean res = MigrateExcelData.isFileToBeUploaded();
			if (res) {
				/* Delete */
				String delQuery = "DELETE FROM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE="
						+ bean.getLwfID();
				getSqlModel().singleExecute(delQuery);

				/* Insert HRMS_LWF_EMP_APPLICABLE Table Information */

				String[] multipleDivCode = String.valueOf(bean.getSDivCode())
						.split(",");
				for (int j = 0; j < multipleDivCode.length; j++) {
					String sHeaderQuery = "";
					if (checkBoxVar.equals("D")) {

						sHeaderQuery = " INSERT INTO HRMS_LWF_EMP_APPLICABLE (LWF_DESG, LWF_APPLICABLE, LWF_DIV, LWF_CODE) "
								+ " VALUES (?,?," + multipleDivCode[j] + ",?) ";
					}

					if (checkBoxVar.equals("G")) {

						sHeaderQuery = " INSERT INTO HRMS_LWF_EMP_APPLICABLE (LWF_GRADE, LWF_APPLICABLE, LWF_DIV, LWF_CODE) "
								+ " VALUES (?,?," + multipleDivCode[j] + ",?) ";
					}

					getSqlModel().singleExecute(sHeaderQuery, Obj);
					bean.setViewUploadFileFlag(true);
				}

			} 
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	

	public static void viewUploadedFile(String uploadFileName, String dataPath,
			HttpServletResponse response) {
		try {
			OutputStream oStream = null;
			FileInputStream fsStream = null;
			File file = null;

			try {
				dataPath += uploadFileName;

				response.setHeader("Content-type", "application/msexcel");
				response.setHeader("Content-disposition",
						"attachment;filename=\"" + uploadFileName + "\"");

				int iChar;
				file = new File(dataPath);
				fsStream = new FileInputStream(file);
				oStream = response.getOutputStream();

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}
			} catch (Exception e) {
			} finally {
				if (fsStream != null) {
					fsStream.close();
				}

				if (oStream != null) {
					oStream.flush();
					oStream.close();
				}

				if (file != null) {
					file.setReadOnly();
				}
			}
		} catch (Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}

	public void viewCalculateFile(LWFConfigurationMaster bean,
			HttpServletResponse response, String checkBoxVar) {

		try {
			
			String autoSql = "";
				
			if (checkBoxVar.equals("D")) {
				autoSql = "SELECT HRMS_EMP_OFFC.EMP_TOKEN , HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME ,HRMS_RANK.RANK_NAME FROM HRMS_EMP_OFFC "
					+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
					+" WHERE EMP_STATUS='S' AND EMP_DIV IN ("+bean.getSDivCode()+") AND EMP_RANK IN(SELECT LWF_DESG FROM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE ="+bean.getLwfID()+" AND LWF_APPLICABLE='Y' AND LWF_DESG IS NOT NULL) "
					+" AND HRMS_CENTER.CENTER_LOCATION IN(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_PARENT_CODE="+bean.getSStateCode()+" )";
				
			}
			if (checkBoxVar.equals("G")) {
				///autoSql = "SELECT LWF_GRADE, NVL(LWF_APPLICABLE,' '), LWF_CODE FROM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE ="+ bean.getLwfID();
				
				autoSql = "SELECT HRMS_EMP_OFFC.EMP_TOKEN , HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME , HRMS_CADRE.CADRE_NAME  FROM HRMS_EMP_OFFC "
					+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+" INNER JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
					+" WHERE EMP_STATUS='S' AND EMP_DIV IN ("+bean.getSDivCode()+") AND EMP_CADRE IN(SELECT LWF_GRADE  FROM HRMS_LWF_EMP_APPLICABLE WHERE LWF_CODE ="+bean.getLwfID()+" AND LWF_APPLICABLE='Y' AND LWF_GRADE  IS NOT NULL) "
					+" AND HRMS_CENTER.CENTER_LOCATION IN(SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_PARENT_CODE="+bean.getSStateCode()+" )";
				
			}
			Object[][] attData = getSqlModel().getSingleResult(autoSql);
			
			String[] msg = lwfEmpInstructionMsg();
			String title = " ";
			if (checkBoxVar.equals("D")) {
				title = " Peoplepower_LWF_Designation_";
			} else {
				title = " Peoplepower_LWF_Grade_";
			}
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Xls", title, msg);
			// get all employee according to the filters

			/*
			 * int cellwidth[] = { 50, 50 }; int alignment[] = { 0, 0, };
			 * 
			 * rg.tableBodyWithColor(new Object[][]{{"Designation",
			 * "Applicability"}}, new Object[][]{{"Designation",
			 * "Applicability","DDDDDD", "AAAAAA", "DDDDDD"}, {"Designation",
			 * "Applicability","DDDDDD", "AAAAAA", "DDDDDD"}}, cellwidth,
			 * alignment, new Object[][]{{Utility.YELLOW}});
			 * rg.createReport(response);
			 */

			// get all employee according to the filters
			Object[][] empObj = setEmpOffcCalFiletrs(bean, checkBoxVar);
			String concatEmployeeIds = "";
		

			if (empObj != null && empObj.length > 0) {
				for (int i = 0; i < empObj.length; i++) {
					if (i == (empObj.length - 1)) {
						concatEmployeeIds += String.valueOf(empObj[i][1]);
					} else {
						concatEmployeeIds += String.valueOf(empObj[i][1]) + ",";
					}
				}
			} // end of if for canocat all empId
			String attWorkFlow = "";
			String leaveWorkFlow = "";
			String recoveryWorkFlow = "";
			int dynamicCol = 0;
			if (recoveryWorkFlow.equals("Y")) {
				dynamicCol = 1;
			}
			Object[][] totalObj = new Object[empObj.length][7 + dynamicCol];
			Object[][] totalNewObj = new Object[0][];
			// for attendance late marks and half days
			for (int i = 0; i < empObj.length; i++) {
				String empDiv = String.valueOf(empObj[i][1]);
				String empRank = String.valueOf(empObj[i][1]);
				// String empDesig = String.valueOf(empObj[i][5]);
				/*
				 * String empDesg = String.valueOf(empObj[i][6]); String
				 * empBranch = String.valueOf(empObj[i][7]); String empType =
				 * String.valueOf(empObj[i][8]);
				 */

				totalObj[i][0] = empObj[i][0]; // EMP ID
				totalObj[i][1] = empObj[i][1]; // EMP NAME
				totalObj[i][2] = empObj[i][2]; // DESIGNATION / Grade
				// totalObj[i][1] = empObj[i][3]; // Designation
				// totalObj[i][2] = empObj[i][5]; // grade

				// for late marks and half days
				boolean attFlag = false;

				/*if (checkBoxVar.equals("D")) {
					if (attData != null && attData.length > 0) {
						for (int j = 0; j < attData.length; j++) {
							if (empObj[i][0].equals(attData[j][0])) {
								//totalObj[i][1] = attData[j][1]; // Applicability
								
								if (String.valueOf(attData[j][1]).equals("Y")) {
									totalObj[i][1] = "Yes";
								}
								else if (String.valueOf(attData[j][1]).equals("N")) {
									totalObj[i][1] = "No";
								} else {
									totalObj[i][1] = "";
								}
								attFlag = true;
								break;
							}
						}
					}
				} 
				if (checkBoxVar.equals("G")) {
					if (attData != null && attData.length > 0) {
						for (int j = 0; j < attData.length; j++) {
							if (empObj[i][0].equals(attData[j][0])) {
								//totalObj[i][1] = attData[j][1]; // Applicability
								
								if (String.valueOf(attData[j][1]).equals("Y")) {
									totalObj[i][1] = "Yes";
								}
								else if (String.valueOf(attData[j][1]).equals("N")) {
									totalObj[i][1] = "No";
								}else {
									totalObj[i][1] = "";
								}
								attFlag = true;
								break;
							}
						}
					}
				} */
				
				/*if (attFlag == false) {
					System.out.println("checkBoxVar====IFFFFFFFFFFF============"+checkBoxVar );
					totalObj[i][1] = attData[0][1]; // blank Applicability

				}*/

			}
			try {
				if (checkBoxVar.equals("D")) {
					
					String abc[] = { "Emp Id", "Emp Name","Designation" };
					int cellwidth[] = { 30, 40,30 };
					int alignment[] = { 0, 0,0 };
					int colsAsDouble[] = { 2, 3,4 };

					String abc1[] = { " ", " ","" };
					int cellwidth1[] = {  30, 40,30  };
					int alignment1[] = { 0, 0,0 };
					int colsAsDouble1[] = {  2, 3,4 };

					rg.tableBodyAsText(abc, totalNewObj, cellwidth, alignment,
							colsAsDouble);
					rg.tableBodyAsText(abc1, totalObj, cellwidth1, alignment1,
							colsAsDouble1);
				} else {
					
					String abc[] = { "Emp Id", "Emp Name","Grade" };
					int cellwidth[] = { 30, 40,30 };
					int alignment[] = { 0, 0,0 };
					int colsAsDouble[] = { 2, 3,4 };

					String abc1[] = { " ", " ","" };
					int cellwidth1[] = {  30, 40,30  };
					int alignment1[] = { 0, 0,0 };
					int colsAsDouble1[] = {  2, 3,4 };

					rg.tableBodyAsText(abc, totalNewObj, cellwidth, alignment,
							colsAsDouble);
					rg.tableBodyAsText(abc1, totalObj, cellwidth1, alignment1,
							colsAsDouble1);
				}
				rg.createReport(response);
			} catch (Exception e) {
				logger.error("Exception in downloadCalculateFile:" + e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String deleteSelectedRecords(LWFConfigurationMaster bean,
			String lwfId) {
		boolean result = false;
		int count = 0;
		if (lwfId != null) {
			for (int i = 0; i < lwfId.length(); i++) {
				if (!lwfId.equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = lwfId;
					result = getSqlModel().singleExecute(getQuery(1), delete);
					if (!result) {
						count++;
					}// end of if
				}// end of nested if
			}// end of loop

		}// end of nested if

		int count1 = 0;
		if (lwfId != null) {
			for (int i = 0; i < lwfId.length(); i++) {
				if (!lwfId.equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = lwfId;
					result = getSqlModel().singleExecute(getQuery(2), delete);
					if (!result) {
						count1++;
					}// end of if
				}// end of nested if
			}// end of loop

		}// end of nested if
		int count2 = 0;
		if (lwfId != null) {
			for (int i = 0; i < lwfId.length(); i++) {
				if (!lwfId.equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = lwfId;
					result = getSqlModel().singleExecute(getQuery(3), delete);
					if (!result) {
						count2++;
					}// end of if
				}// end of nested if
			}// end of loop

		}// end of nested if

		if (count != 0 || count1 != 0|| count2 != 0)
			return "false";
		else
			return "true";
	}
}
