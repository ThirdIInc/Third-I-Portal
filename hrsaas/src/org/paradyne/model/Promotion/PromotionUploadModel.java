package org.paradyne.model.Promotion;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.promotion.PromotionUpload;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.action.DataMigration.MigratePayrollExcelData;

public class PromotionUploadModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PromotionUploadModel.class);
	/**
	 * This function is used to create a template for uploading salary details & to
	 *  generate report.
	 *
	 * @param proBean - proBean
	 * @param response - response
	 * @param type :- Determines whether to generate template or generate report
	 */

	public final void generateTemplate(final PromotionUpload proBean, final HttpServletResponse response, final String type) {
		try {
			String fileName = "PROMOTION_UPLOAD_REPORT";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Xls", fileName, "A4");
			rg.addFormatedText(fileName, 6, 0, 1, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
			if (type.equals("TEMPLATE")) {
				rg = getTemplate(rg, proBean);
			} else {
				rg = getReport(rg, proBean);
			}
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**This function creates the template for uploading the promotion details.
	 * @param rg - rg
	 * @param proBean - proBean
	 * @return rg
	 */
	public final ReportGenerator getTemplate(final ReportGenerator rg, final PromotionUpload proBean) {
		try {
			Object[][] creditsObj = null;
			int fixedHeader = 20;
			int creditObjLength = 0;
			String creditsQuery = "SELECT DISTINCT NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' '), HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD "
					+ " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			creditsObj = getSqlModel().getSingleResult(creditsQuery);
			creditObjLength = creditsObj.length;

			/*Assigning headers*/
			Object[][] header = new Object[2][fixedHeader + creditObjLength];
			logger.info("############# header[0].length ###############"+header[0].length);
			header[0][0] = "Employee Code";
			header[0][1] = "Employee Name";
			header[0][2] = "Promotion Date";
			header[0][3] = "Effective Date";
			header[0][4] = "Proposed Division";
			header[0][5] = "Proposed Branch";
			header[0][6] = "Proposed Department";
			header[0][7] = "Propose Designation";
			header[0][8] = "Proposed Role";
			header[0][9] = "Proposed Grade";
			header[0][10] = "Proposed Reporting To";
			header[0][11] = "Appraisal Ratings";
			header[0][12] = "Old CTC";
			header[0][13] = "Strengths";
			header[0][14] = "Weakness";
			header[0][15] = "Is Promoted";
			header[0][16] = "Salary Grade";
			header[0][17] = "Salary Formula";
			header[0][18] = "Template Name";
			header[0][19] = "CTC";
			
			header[1][0] = "*";
			header[1][1] = "*";
			header[1][2] = "*";
			header[1][3] = "*";
			header[1][4] = "*";
			header[1][5] = "*";
			header[1][6] = "*";
			header[1][7] = "*";
			header[1][8] = "";
			header[1][9] = "";
			header[1][10] = "*";
			header[1][11] = "";
			header[1][12] = "";
			header[1][13] = "";
			header[1][14] = "";
			header[1][15] = "";
			header[1][16] = "";
			header[1][17] = "";
			header[1][18] = "";
			header[1][19] = "";
			
			if (creditsObj  != null && creditsObj.length > 0) {
				int headerIndex = header[0].length - creditObjLength;
				System.out.println("############## headerIndex ################" + headerIndex);
				for (int i = 0; i < creditsObj.length; i++) {
					header[0][headerIndex + i] = String.valueOf(creditsObj[i][0]);
					header[1][headerIndex + i] = "*";
				}
			}

			int[] alignment = new int[header[0].length];
			int[] cellwidth = new int[header[0].length];

			for (int i = 0; i < header[0].length; i++) {
				if (i == 12 || i > 14) {
					alignment[i] = 2;
				} else {
					alignment[i] = 0;
				}
				cellwidth[i] = 25;
			}

			//Setting alignment & width for token & name 

			cellwidth[1] = 30;

			Object [][] dataObject = getEmployeesByFilter(rg, proBean, alignment, cellwidth);
			Object [][] finalObject = new Object[dataObject.length][header[0].length];

			if (dataObject  != null && dataObject.length > 0) {
				for (int i = 0; i < dataObject.length; i++) {
					for (int j = 0; j < header[0].length; j++) {
						if (j < 16) {
							finalObject[i][j] = String.valueOf(dataObject[i][j]);
					}else if(j==16 || j==17 || j==18){
						finalObject[i][j] = " ";
					}else {
							finalObject[i][j] = "0";
						}
					}
				}
			}

			Object [][] color = new Object[dataObject.length][header[0].length];

			if (color  != null && color.length > 0) {
				for (int i = 0; i < color.length; i++) {
					for (int j = 0; j < color[0].length; j++) {
						color[i][0] = Utility.LIGHT_YELLOW;
						color[i][1] = Utility.LIGHT_YELLOW;
					}
				}
			}
			rg.tableBodyWithColor(header, finalObject, cellwidth, alignment, color);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/**  This method adds employees as per the filter to the excel table.
	 * @param rg - rg
	 * @param proBean - proBean
	 * @param alignment - alignment
	 * @param cellwidth - cellwidth
	 * @return Object[][]
	 */
	public final Object[][] getEmployeesByFilter(final ReportGenerator rg, final PromotionUpload proBean, final int[] alignment, final int[] cellwidth) {
		Object[][] empDataObj = null;
		try {
			String query = "SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME),"
					+ " '','', NVL(HRMS_DIVISION.DIV_NAME,' '), NVL(HRMS_CENTER.CENTER_NAME, ' '), NVL(HRMS_DEPT.DEPT_NAME, ' '), NVL(HRMS_RANK.RANK_NAME, ' '),"
					+ " NVL(HRMS_EMP_OFFC.EMP_ROLE, ' '), NVL(HRMS_CADRE.CADRE_NAME,' '), NVL(E1.EMP_TOKEN,' '),"
					+ " '', NVL(HRMS_SALARY_MISC.CTC,0), '', '', ''"
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"  
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)" 
					+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)" 
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)" 
					+ " LEFT JOIN HRMS_EMP_OFFC E1 ON (E1.EMP_ID = HRMS_EMP_OFFC.EMP_REPORTING_OFFICER)"
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
					+ " WHERE HRMS_EMP_OFFC.EMP_STATUS='S' ";
					if (!proBean.getDivisionId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_DIV = " + proBean.getDivisionId();
					}
					if (!proBean.getBranchId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_CENTER = " + proBean.getBranchId();
					}
					if (!proBean.getPaybillId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_PAYBILL = " + proBean.getPaybillId();
					}
					if (!proBean.getGradeId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_CADRE = " + proBean.getGradeId();
					}
					if (!proBean.getDepartmentId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_DEPT = " + proBean.getDepartmentId();
					}
					if (!proBean.getEmpId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_ID IN ("+ proBean.getEmpId()+")";
					}
					query += " AND ROWNUM < 1000 ORDER BY HRMS_EMP_OFFC.EMP_ID ";

				empDataObj = getSqlModel().getSingleResult(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return empDataObj;
	}

	/**This function generates the promotion details report of the employees as per the filter selected.
	 * @param rg - rg
	 * @param proBean - proBean
	 * @return rg
	 */
	public final ReportGenerator getReport(final ReportGenerator rg, final PromotionUpload proBean) {
		try {
			Object[][] creditsObj = null;
			Object[][] debitsObj = null;
			int creditObjLength = 0;
			int debitObjLength = 0;

			String creditsQuery = "";

			String debitsQuery = "";

			creditsObj = getSqlModel().getSingleResult(creditsQuery);

			debitsObj = getSqlModel().getSingleResult(debitsQuery);

			if (creditsObj  != null && creditsObj.length > 0) {
				creditObjLength = creditsObj.length;	
			}

			if (debitsObj  != null && debitsObj.length > 0) {
				debitObjLength = debitsObj.length + 1;
			}

			Object [][] finalCreditsDebitsObj = null;

			Object[][] header = new Object[2][creditObjLength + debitObjLength];

			logger.info("############# header.length ###############" + header.length);

			/*Assigning headers*/

			header[0][0] = "Employee Code";
			header[0][1] = "Employee Name";
			header[0][2] = "Promotion Date";
			header[0][3] = "Effective Date";
			header[0][4] = "Proposed Division";
			header[0][5] = "Proposed Branch";
			header[0][6] = "Proposed Department";
			header[0][7] = "Propose Designation";
			header[0][8] = "Proposed Role";
			header[0][9] = "Proposed Grade";
			header[0][10] = "Proposed Reporting To";
			header[0][11] = "Proposed By";
			header[0][12] = "Approved By";
			header[0][13] = "Appraisal Ratings";
			header[0][14] = "Old CTC";
			header[0][15] = "Strengths";
			header[0][16] = "Weakness";
			header[0][17] = "Is Promoted";
			header[0][16] = "Salary Grade";
			header[0][18] = "Salary Formula";
			header[0][19] = "Template Name";

			header[1][0] = "*";
			header[1][1] = "*";
			header[1][2] = "*";
			header[1][3] = "*";
			header[1][4] = "*";
			header[1][5] = "*";
			header[1][6] = "*";
			header[1][7] = "*";
			header[1][8] = "";
			header[1][9] = "";
			header[1][10] = "*";
			header[1][11] = "";
			header[1][12] = "";
			header[1][13] = "";
			header[1][14] = "";
			header[1][15] = "";
			header[1][16] = "";
			header[1][17] = "";
			header[1][18] = "";
			header[1][19] = "";
			header[1][20] = "";

			int[] alignment = new int[header[0].length];
			int[] cellwidth = new int[header[0].length];

			if (finalCreditsDebitsObj  != null && finalCreditsDebitsObj.length > 0) {

				Object[][] color = new Object[finalCreditsDebitsObj.length][header[0].length];

				if (color  != null && color.length >0) {
					for (int i = 0; i < color.length; i++) {
						for (int j = 0; j < color[0].length; j++) {
							color[i][0] = Utility.LIGHT_YELLOW;
							color[i][1] = Utility.LIGHT_YELLOW;
						}
					}
				}

				rg.tableBodyWithColor(header, finalCreditsDebitsObj, cellwidth, alignment, color);
			} else {
				rg.tableBodyWithColor(null, new Object[][]{{"No data to Display"}}, new int[]{100}, new int[]{2}, new Object[][]{{Utility.RED}});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/**
	 * This method uploads the values from the excel sheet to the respective
	 * promotion related tables in data base.
	 *
	 * @param response - response
	 * @param request - request
	 * @param proBean - proBean
	 */
	public final void uploadEmployeePromotions(final HttpServletResponse response, final HttpServletRequest request, final PromotionUpload proBean) {
		proBean.setUploadedFile(proBean.getUploadFileName());
		String filePath = proBean.getDataPath() + "" + proBean.getUploadFileName();
		logger.info("############# FILE PATH #############" + filePath);
		proBean.setUploadName("UploadPromotions");
		// to create object of the file
		MigratePayrollExcelData.getFile(filePath);

		/*SETTING THE LIMIT ON NO OF RECORDS TO UPLOAD*/
		int uploadLimit = 50;

		/*CHECKS IF THE NO OF ROWS BEING UPLOADED ARE IN THE ALLOWED RANGE*/
		logger.info("TOTAL ROWS IN EXCEL -- "+MigratePayrollExcelData.totalRecords);
		logger.info("UPLOAD LIMIT -- " + uploadLimit);
		if (MigratePayrollExcelData.totalRecords <= uploadLimit) {

			String creditsQuery = "SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' ') FROM HRMS_CREDIT_HEAD "
				+ " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			Object[][] creditHeadMaster = getSqlModel().getSingleResult(creditsQuery);
			Object[][] finalCreditHead = null;
			Vector<Object[][]> creditsHeadVector = new Vector<Object[][]>();
			if (creditHeadMaster  != null && creditHeadMaster.length >0){
				int no_of_creditHead_rows = 0;
				for (int i = 1; i <= creditHeadMaster.length; i++) {
					Object[][] creditHeaderObj = MigratePayrollExcelData.uploadPayrollExcelData(20+i,
							creditHeadMaster, MigratePayrollExcelData.MASTER_TYPE);
					no_of_creditHead_rows = creditHeaderObj.length;
					if (!String.valueOf(creditHeaderObj[0][0]).equals("")) {
						creditsHeadVector.add(creditHeaderObj);
					}
				}

				finalCreditHead = new Object[no_of_creditHead_rows][creditsHeadVector.size()];

				int creditsCount = 0;

				for (Iterator iterator = creditsHeadVector.iterator(); iterator.hasNext();) {
					Object[][] iteratorValueObj = (Object[][]) iterator.next();
					int inner_count = 0;
					for (int j = 0; j < iteratorValueObj.length; j++) {
						finalCreditHead[inner_count++][creditsCount] = String.valueOf(iteratorValueObj[j][0]);
					}
					creditsCount++;
				}
				logger.info("############ finalCreditHead.length ################" + finalCreditHead.length);
			}

			/*Checks if the credit headers are available in master*/
			boolean integrityResult = MigratePayrollExcelData.checkFileToBeUploaded();
			int noOfCreditColumns = creditsHeadVector.size();
			logger.info("####1st Integrity Check ####"+integrityResult);

			if (integrityResult) {
				/**
				 * Get column numbers with mandatory information
				 */

				HashMap<Integer, Boolean> columnInfo = MigratePayrollExcelData.isColumnsMandatory();		

				String query = "SELECT HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME),"
					+ " '','', NVL(HRMS_DIVISION.DIV_ID,0), NVL(HRMS_CENTER.CENTER_ID,0), NVL(HRMS_DEPT.DEPT_ID,0), NVL(HRMS_RANK.RANK_ID,0),"
					+ " NVL(HRMS_EMP_OFFC.EMP_ROLE, ' '), NVL(HRMS_CADRE.CADRE_ID,0), TO_CHAR(E1.EMP_FNAME||' '||E1.EMP_MNAME||'  '||E1.EMP_LNAME),"
					+ " '', NVL(HRMS_SALARY_MISC.CTC,0), '', '', NVL(HRMS_EMP_OFFC.EMP_REPORTING_OFFICER,0), TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE, 'MM-DD-YYYY')"
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"  
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)" 
					+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)" 
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)" 
					+ " LEFT JOIN HRMS_EMP_OFFC E1 ON (E1.EMP_ID = HRMS_EMP_OFFC.EMP_REPORTING_OFFICER)"
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
					+ " WHERE HRMS_EMP_OFFC.EMP_STATUS='S' ";
					if (!proBean.getDivisionId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_DIV = " + proBean.getDivisionId();
					}
					if (!proBean.getBranchId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_CENTER = " + proBean.getBranchId();
					}
					if (!proBean.getPaybillId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_PAYBILL = " + proBean.getPaybillId();
					}
					if (!proBean.getGradeId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_CADRE = " + proBean.getGradeId();
					}
					if (!proBean.getDepartmentId().equals("")) {
						query += " AND HRMS_EMP_OFFC.EMP_DEPT = " + proBean.getDepartmentId();
					}
					if (!proBean.getEmpId().equals("")){
						query += " AND HRMS_EMP_OFFC.EMP_ID IN (" + proBean.getEmpId() + ")";
					}
					query += " ORDER BY HRMS_EMP_OFFC.EMP_ID ";

				Object[][] empMasterObj = getSqlModel().getSingleResult(query);

				HashMap empDataMap= getSqlModel().getSingleResultMap(query, 0, 2);

				Object[][] divisionMaster = getSqlModel().getSingleResult(
						"SELECT HRMS_DIVISION.DIV_ID, HRMS_DIVISION.DIV_NAME FROM HRMS_DIVISION");

				Object[][] branchMaster = getSqlModel().getSingleResult(
						"SELECT HRMS_CENTER.CENTER_ID, HRMS_CENTER.CENTER_NAME FROM HRMS_CENTER");

				Object[][] deptMaster = getSqlModel().getSingleResult(
						"SELECT HRMS_DEPT.DEPT_ID, HRMS_DEPT.DEPT_NAME FROM HRMS_DEPT");

				Object[][] designationMaster = getSqlModel().getSingleResult(
						"SELECT HRMS_RANK.RANK_ID, HRMS_RANK.RANK_NAME FROM HRMS_RANK ");

				Object[][] gradeMaster = getSqlModel().getSingleResult(
						"SELECT HRMS_CADRE.CADRE_ID, HRMS_CADRE.CADRE_NAME FROM HRMS_CADRE");

				Object[][] reportingToMaster = getSqlModel().getSingleResult(
					"SELECT HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_EMP_OFFC.EMP_TOKEN,' ') FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'");

				String queryTemplateMaster = "SELECT TEMPLATE_ID,TEMPLATE_NAME FROM HRMS_LETTERTEMPLATE_HDR";
				Object [][] queryTemplateMasterObj = getSqlModel().getSingleResult(queryTemplateMaster);
				
						
				String querySalaryGradeMaster = "SELECT SALGRADE_CODE, NVL(SALGRADE_TYPE ,' ') FROM HRMS_SALGRADE_HDR";
				Object [][] querySalaryGradeObj = getSqlModel().getSingleResult(querySalaryGradeMaster);
				
				
				String SalaryFormulaMaster = "SELECT FORMULA_ID, NVL(FORMULA_NAME,' ') FROM HRMS_FORMULABUILDER_HDR";
				Object [][] querySalaryFormulaObj = getSqlModel().getSingleResult(SalaryFormulaMaster);

				Object[][] empTokenObj = null;
				Object[][] empNameObj = null;
				//logger.info("b4 calling empNameObj");
				if (empMasterObj  != null && empMasterObj.length > 0) {
					empTokenObj = MigratePayrollExcelData.uploadExcelData(1,empMasterObj, 
							MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(1));

					empNameObj = MigratePayrollExcelData.uploadExcelData(2,	null, 
							MigratePayrollExcelData.STRING_TYPE, columnInfo.get(2));
				}

				Object[][] promotionDateObj = MigratePayrollExcelData.uploadExcelData(3,
						null, MigratePayrollExcelData.DATE_TYPE, columnInfo.get(3));

				Object[][] effectiveDateObj = MigratePayrollExcelData.uploadExcelData(4,
						null, MigratePayrollExcelData.DATE_TYPE, columnInfo.get(4));

				Object[][] proposedDivisionObj = MigratePayrollExcelData.uploadExcelData(5,
						divisionMaster, MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(5));
				divisionMaster=null;

				Object[][] proposedBranchObj = MigratePayrollExcelData.uploadExcelData(6,
						branchMaster, MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(6));
				branchMaster=null;

				Object[][] proposedDeptObj = MigratePayrollExcelData.uploadExcelData(7,
						deptMaster, MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(7));
				deptMaster=null;

				Object[][] proposedDesigObj = MigratePayrollExcelData.uploadExcelData(8,
						designationMaster, MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(8));
				designationMaster=null;

				Object[][] proposedRoleObj = MigratePayrollExcelData.uploadExcelData(9,
						null, MigratePayrollExcelData.STRING_TYPE, columnInfo.get(9));

				Object[][] proposedGradeObj = MigratePayrollExcelData.uploadExcelData(10,
						gradeMaster, MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(10));
				gradeMaster=null;

				Object[][] proposedReportingObj = MigratePayrollExcelData.uploadExcelData(11,
						reportingToMaster, MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(11));
				reportingToMaster=null;

				Object[][] proposedRatingObj = MigratePayrollExcelData.uploadExcelData(12,
						null, MigratePayrollExcelData.NUMBER_INTEGER_TYPE, columnInfo.get(12));

				Object[][] proposedCtcObj = MigratePayrollExcelData.uploadExcelData(20,
						null, MigratePayrollExcelData.NUMBER_INTEGER_TYPE, columnInfo.get(20));

				Object[][] proposedStrengthsObj = MigratePayrollExcelData.uploadExcelData(14,
						null, MigratePayrollExcelData.STRING_TYPE, columnInfo.get(14));

				Object[][] proposedWeaknessObj = MigratePayrollExcelData.uploadExcelData(15,
						null, MigratePayrollExcelData.STRING_TYPE, columnInfo.get(15));
				
				Object[][] proposedIsPromotoedObj = MigratePayrollExcelData.uploadExcelData(16,
						null,  MigratePayrollExcelData.STRING_TYPE, columnInfo.get(16));
								
				Object[][] proposedSalaryGradeObj = MigratePayrollExcelData.uploadExcelData(17,
						querySalaryGradeObj,  MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(17));
				
				querySalaryGradeObj=null;
				Object[][] proposedSalaryFormulaObj = MigratePayrollExcelData.uploadExcelData(18,
						querySalaryFormulaObj,  MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(18));
				
				querySalaryFormulaObj=null;
				Object[][] proposedTemplateObj = MigratePayrollExcelData.uploadExcelData(19,
						queryTemplateMasterObj,  MigratePayrollExcelData.MASTER_TYPE, columnInfo.get(19));

				Object[][] oldCtcTemplateObj = MigratePayrollExcelData.uploadExcelData(13,
						null,  MigratePayrollExcelData.NUMBER_INTEGER_TYPE, columnInfo.get(13));
				
				queryTemplateMasterObj=null;

				/*Credit values Object block*/

				Vector<Object[][]> creditsVector = new Vector<Object[][]>();
				int no_of_credit_rows = 0;
				int no_of_credit_cols = 0;
				Object[][] finalCredit = null;

				if (noOfCreditColumns > 0) {
					for (int i = 1; i < noOfCreditColumns; i++) {
						Object[][] creditHeadObj = MigratePayrollExcelData.uploadExcelData(
								20+i, null, MigratePayrollExcelData.NUMBER_DOUBLE_TYPE,
								columnInfo.get(20+i));
						no_of_credit_rows = creditHeadObj.length;
						no_of_credit_cols = noOfCreditColumns;
						creditsVector.add(creditHeadObj);
						creditHeadObj = null;
					}

					finalCredit = new Object[no_of_credit_rows][no_of_credit_cols];
					int creditsCount = 0;
					for (Iterator iterator = creditsVector.iterator(); iterator.hasNext();) {
						Object[][] iteratorValueObj = (Object[][]) iterator.next();
						int inner_count = 0;
						for (int j = 0; j < iteratorValueObj.length; j++) {
							finalCredit[inner_count++][creditsCount] = String.valueOf(iteratorValueObj[j][0]);
							//System.out.println("######## CREDITS COLUMN VALUES #########"+String.valueOf(iteratorValueObj[j][0]));
						}
						creditsCount++;
					}
				}

				boolean result = MigratePayrollExcelData.isFileToBeUploaded();
				logger.info("#### 2nd Integrity Check ####" + result);

				if (result) {
					/* DATA FOR INSERTING RECORDS INTO HRMS_PROMOTION */

					String maxPromCodeQuery = "SELECT NVL(MAX(PROM_CODE),0)+1 FROM HRMS_PROMOTION";
					Object[][] maxCode = getSqlModel().getSingleResult(maxPromCodeQuery);

					int promotionCode = Integer.parseInt(String.valueOf(maxCode[0][0]));

					Object [][] finalEmpCurrentDataObj = new Object [empTokenObj.length][28]; 

					Object [][] delParamObj = new Object [empTokenObj.length][3]; 

					HashMap promoCodeMap = new HashMap();
					if (empTokenObj  != null && empTokenObj.length >0) {
						for (int i = 0; i < empTokenObj.length; i++) {
							Object[][] currentDataObj=null;
							try {
								currentDataObj = (Object[][]) empDataMap.get(Utility.checkNull(String.valueOf(empTokenObj[i][0])));
							} catch (Exception e) {
								e.printStackTrace();
							}

							if (currentDataObj  != null && currentDataObj.length>0) {
								promoCodeMap.put(String.valueOf(currentDataObj[0][0]), promotionCode+i);
								finalEmpCurrentDataObj[i][0] = promotionCode+i; //Incrementing promo code
								finalEmpCurrentDataObj[i][1] = String.valueOf(currentDataObj[0][0]);//Emp Id
								delParamObj[i][0] = String.valueOf(currentDataObj[0][0]);
								finalEmpCurrentDataObj[i][2] = String.valueOf(promotionDateObj[i][0]);//Promotion Date
								delParamObj[i][1] = String.valueOf(promotionDateObj[i][0]);
								finalEmpCurrentDataObj[i][3] = String.valueOf(effectiveDateObj[i][0]);//Effective Date
								delParamObj[i][2] = String.valueOf(effectiveDateObj[i][0]);
								finalEmpCurrentDataObj[i][4] = String.valueOf(proposedDivisionObj[i][0]);
								finalEmpCurrentDataObj[i][5] = String.valueOf(proposedBranchObj[i][0]);
								finalEmpCurrentDataObj[i][6] = String.valueOf(proposedDeptObj[i][0]);
								finalEmpCurrentDataObj[i][7] = String.valueOf(proposedDesigObj[i][0]);
								finalEmpCurrentDataObj[i][8] = String.valueOf(proposedRoleObj[i][0]);
								finalEmpCurrentDataObj[i][9] = String.valueOf(proposedGradeObj[i][0]);
								finalEmpCurrentDataObj[i][10] = String.valueOf(proposedReportingObj[i][0]);
								finalEmpCurrentDataObj[i][11] = String.valueOf(proposedRatingObj[i][0]);
								finalEmpCurrentDataObj[i][12] = String.valueOf(proposedCtcObj[i][0]);
								finalEmpCurrentDataObj[i][13] = String.valueOf(proposedStrengthsObj[i][0]);
								finalEmpCurrentDataObj[i][14] = String.valueOf(proposedWeaknessObj[i][0]);
								finalEmpCurrentDataObj[i][15] = String.valueOf(currentDataObj[0][5]);//Current Division 
								finalEmpCurrentDataObj[i][16] = String.valueOf(currentDataObj[0][6]);//Current Branch
								finalEmpCurrentDataObj[i][17] = String.valueOf(currentDataObj[0][7]);//Current Department
								finalEmpCurrentDataObj[i][18] = String.valueOf(currentDataObj[0][8]);//Current Designation
								finalEmpCurrentDataObj[i][19] = String.valueOf(currentDataObj[0][9]);//Current Role
								finalEmpCurrentDataObj[i][20] = String.valueOf(currentDataObj[0][10]);//Current Grade
								finalEmpCurrentDataObj[i][21] = String.valueOf(currentDataObj[0][16]);//Current Reporting To
								//finalEmpCurrentDataObj[i][22] = String.valueOf(currentDataObj[0][13]);//Current OLD CTC
								finalEmpCurrentDataObj[i][22] = String.valueOf(oldCtcTemplateObj[i][0]);//Current OLD CTC
								finalEmpCurrentDataObj[i][23] = String.valueOf(currentDataObj[0][17]);//Joining Date
								finalEmpCurrentDataObj[i][24] = String.valueOf(String.valueOf(proposedTemplateObj[i][0]));//Template
								if((proposedIsPromotoedObj[i][0].toString().equalsIgnoreCase(("Yes") ))|| (proposedIsPromotoedObj[i][0].toString().equalsIgnoreCase("Y"))){
									finalEmpCurrentDataObj[i][25] = "P";//Is Promoted
								}else{
									finalEmpCurrentDataObj[i][25] = "I";//Is Promoted
								}
								
								finalEmpCurrentDataObj[i][26] = String.valueOf(String.valueOf(proposedSalaryGradeObj[i][0]));//Salary Grade
								finalEmpCurrentDataObj[i][27] = String.valueOf(String.valueOf(proposedSalaryFormulaObj[i][0]));//Salary Formula
								//finalEmpCurrentDataObj[i][28] = String.valueOf(String.valueOf(newCtcTemplateObj[i][0]));//new CTC
							
							}
						}
					}

					/* DATA FOR INSERTING RECORDS INTO HRMS_PROMOTION_SALARY */

						String empCreditQuery = "SELECT HRMS_EMP_CREDIT.EMP_ID, HRMS_EMP_CREDIT.CREDIT_CODE, HRMS_EMP_CREDIT.CREDIT_AMT FROM HRMS_EMP_CREDIT "
							+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_CREDIT.EMP_ID)";
						if (!proBean.getDivisionId().equals("")) {
							query += " AND HRMS_EMP_OFFC.EMP_DIV = " + proBean.getDivisionId();
						}
						if (!proBean.getBranchId().equals("")) {
							query += " AND HRMS_EMP_OFFC.EMP_CENTER = " + proBean.getBranchId();
						}
						if (!proBean.getPaybillId().equals("")) {
							query += " AND HRMS_EMP_OFFC.EMP_PAYBILL = " + proBean.getPaybillId();
						}
						if (!proBean.getGradeId().equals("")) {
							query += " AND HRMS_EMP_OFFC.EMP_CADRE = " + proBean.getGradeId();
						}
						if (!proBean.getDepartmentId().equals("")) {
							query += " AND HRMS_EMP_OFFC.EMP_DEPT = " + proBean.getDepartmentId();
						}
						if (!proBean.getEmpId().equals("")) {
							query += " AND HRMS_EMP_OFFC.EMP_ID IN (" + proBean.getEmpId()+ ")";
						}
						Object [][] empCreditObj = getSqlModel().getSingleResult(empCreditQuery);

						/*Old Credit map for inserting values to HRMS_PROMOTION_SALARY*/

						HashMap oldCreditMap = new HashMap();
						if (empCreditObj  != null && empCreditObj.length >0) {
							for (int j = 0; j < empCreditObj.length; j++) {
								oldCreditMap.put(String.valueOf(empCreditObj[j][0]) + "#" + String.valueOf(empCreditObj[j][1]),
										String.valueOf(empCreditObj[j][2]));
							}
						}

						/*New Credit map for inserting values to HRMS_PROMOTION_SALARY*/

						HashMap newCreditMap = new HashMap();
						if (empTokenObj != null && empTokenObj.length >0) {
							for (int i = 0; i < empTokenObj.length; i++) {
								for (int j = 0; j < finalCreditHead[0].length; j++) {
									newCreditMap.put(String.valueOf(empTokenObj[i][0]) + "#" + String.valueOf(finalCreditHead[0][j]), String.valueOf(finalCredit[i][j]));
								}
							}
						}

						Vector<Object[]> creditVector = new Vector<Object[]>();
						for (int i = 0; i < empTokenObj.length; i++) {
							for (int j = 0; j < creditHeadMaster.length; j++) {
								String oldCredit =checkNullZero(String.valueOf(oldCreditMap.get(String.valueOf(empTokenObj[i][0])+"#"+String.valueOf(creditHeadMaster[j][0]))));
								String newCredit =checkNullZero(String.valueOf(newCreditMap.get(String.valueOf(empTokenObj[i][0])+"#"+String.valueOf(creditHeadMaster[j][0]))));

								if (!(oldCredit.equals("0")) ||  !(newCredit.equals("0"))){
									Object []tempObj=new Object[4];
									tempObj[0]=promoCodeMap.get(String.valueOf(empTokenObj[i][0]));
									tempObj[1]=(String.valueOf(creditHeadMaster[j][0]));
									tempObj[2]=oldCredit;
									tempObj[3]=newCredit;
									creditVector.add(tempObj);
								}
							}
						}
						oldCreditMap = null;
						newCreditMap = null;
						promotionDateObj = null;
						effectiveDateObj = null;
						proposedDivisionObj = null;
						proposedBranchObj = null;
						proposedDeptObj = null;
						proposedDesigObj = null;
						proposedRoleObj = null;
						proposedGradeObj = null;
						proposedReportingObj = null;
						proposedRatingObj = null;
						proposedCtcObj = null;
						proposedStrengthsObj = null;
						proposedWeaknessObj = null;
						proposedTemplateObj = null;
						proposedIsPromotoedObj=null;
						proposedSalaryGradeObj=null;
						proposedSalaryFormulaObj=null;

						System.gc();
						Object [][] finalEmpNewDataObj = new Object [creditVector.size()][4];
						for (int i = 0; i < finalEmpNewDataObj.length; i++) {
							finalEmpNewDataObj[i] = (Object[])creditVector.get(i);
						}
						creditVector = null;
						/*****************************************************************
						 **** PREPARING DATA FOR INSERTING INTO THE RESPECTIVE TABLES ****
						 *****************************************************************/

						String delQuery = "DELETE FROM HRMS_PROMOTION WHERE EMP_CODE=?" + " AND PROM_DATE=TO_DATE(?,'MM-DD-YYYY') AND EFFECT_DATE=TO_DATE(?,'MM-DD-YYYY')";

						String insertCurrentDataQuery = "INSERT INTO HRMS_PROMOTION (PROM_CODE, EMP_CODE, PROM_DATE, EFFECT_DATE, PRO_DIV, PRO_BRANCH, PRO_DEPT, PRO_DESG, "
							+ " PRO_ROLE, PRO_GRADE, PRO_REPORT_TO, RATING, PROM_GR0SSAMT, STRENGTH, WEAKNESS, DIV_CODE, BRANCH_CODE, DEPT_CODE, DESG_CODE," 
							+ " CURRENT_ROLE, GRADE_CODE, REPORTING_TO, OLD_CTC, DATE_JOINING,PROM_TEMPLATE,PROM_PROMFLAG,PROM_SALGRADE,PROM_FORMULA)" 
							+ " VALUES(?,?,TO_DATE(?,'MM-DD-YYYY'), TO_DATE(?,'MM-DD-YYYY'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?,'MM-DD-YYYY'),?,?,?,?)";

						String insertNewDataQuery = "INSERT INTO HRMS_PROMOTION_SALARY (PROM_CODE, SAL_CODE, OLD_AMT, NEW_AMT) VALUES (?,?,?,?)";

						Vector<Object[][]> dataVector = new Vector<Object[][]>();
						dataVector.add(delParamObj);
						dataVector.add(finalEmpCurrentDataObj);
						dataVector.add(finalEmpNewDataObj);
						Object[] queryArray = new Object[3];
						queryArray[0] = delQuery;
						queryArray[1] = insertCurrentDataQuery;
						queryArray[2] = insertNewDataQuery;

						boolean insertResult = false;
						try {
							insertResult = getSqlModel().multiExecute(queryArray, dataVector);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (insertResult) {
							proBean.setStatus("Success");
							proBean.setNote("Data Uploaded successfully");
						} else {
							proBean.setStatus("Fail");
							proBean.setNote("Error uploading the data");
						}
				} else {
					proBean.setStatus("Fail");
					proBean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
				}
			} else {
				proBean.setStatus("Fail");
				proBean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
			}
		} else {
			proBean.setStatus("Fail");
			proBean.setNote("Please upload only " + uploadLimit + " records at a time.");
		}
		proBean.setUploadFileName("");
		proBean.setDisplayNoteFlag(true);
	}

	/**This checks if the string in null.
	 * @param result
	 * @return String
	 */
	public static String checkNullZero(final String result) {
		if (result == null || result.equals("null")) {
			return "0";
		} else {
			return result;
		}
	}

}
