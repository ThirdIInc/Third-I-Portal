package org.paradyne.model.payroll;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.EmployeeAnnualEarningReport;
import org.paradyne.bean.payroll.TaxInv;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

public class TaxInvModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TaxInvModel.class);

	/*public void fetchAllInvestmentPeriods(TaxInv bean, HttpServletRequest request){
		
		String query = "SELECT DISTINCT HRMS_TAX_INVESTMENT.FROM_YEAR, HRMS_TAX_INVESTMENT.TO_YEAR " 
			+ " FROM HRMS_TAX_INVESTMENT ORDER BY HRMS_TAX_INVESTMENT.FROM_YEAR DESC";
		
		Object[][] periodObj = getSqlModel().getSingleResult(query);
		
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),
				periodObj.length, 20);
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
		if (pageIndex[4].equals("1")) {
			bean.setMyPage("1");
		}
		
		ArrayList<Object> periodList = new ArrayList<Object>();
		if(periodObj!=null && periodObj.length >0){
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				TaxInv bean1 = new TaxInv();
				bean1.setInvestmentFromYearListItt(String.valueOf(periodObj[i][0]));
				bean1.setInvestmentToYearListItt(String.valueOf(periodObj[i][1]));
				periodList.add(bean1);
			}
			bean.setInvestmentPeriodList(periodList);
		}
	}*/
	
	public boolean addInvestmentData(TaxInv bean) {
		boolean result = false;
		try {
			Object addObj[][] = new Object[1][8];
			addObj[0][0] = bean.getInvestmentName();
			addObj[0][1] = bean.getInvestmentSection();
			addObj[0][2] = bean.getInvestmentChapter();
			addObj[0][3] = bean.getInvestmentLimit();
			addObj[0][4] = bean.getInvestmentType();
			//addObj[0][5] = bean.getFromYear();
			//addObj[0][6] = bean.getToYear();
			addObj[0][5] = bean.getInvestmentGroup();
			if( bean.getIsAdditive().equals("true")){
				addObj[0][6] = "A"; //is active
			}else{
				addObj[0][6] = "D";
			}
			
			if( bean.getInvIncludeCheck().equals("true")){
				addObj[0][7] = "Y";
			}else{
				addObj[0][7] = "N";
			}
			
			result = getSqlModel().singleExecute(getQuery(1), addObj);
			
			if(result){
				//fetchInvestmentDetailsByYear(bean, bean.getFromYear(), bean.getToYear());
				fetchInvestmentDetails(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateInvestmentData(TaxInv bean, String investmentCode) {
		boolean result = false;
		try {
			Object addObj[][] = new Object[1][9];
			addObj[0][0] = bean.getInvestmentName();
			addObj[0][1] = bean.getInvestmentSection();
			addObj[0][2] = bean.getInvestmentChapter();
			addObj[0][3] = bean.getInvestmentLimit();
			addObj[0][4] = bean.getInvestmentType();
			//addObj[0][5] = bean.getFromYear();
			//addObj[0][6] = bean.getToYear();
			addObj[0][5] = bean.getInvestmentGroup();
			if (bean.getInvestmentGroup().equals("O")) {
				if (bean.getIsAdditive().equals("true")) {
					addObj[0][6] = "A"; // is additive
				} else {
					addObj[0][6] = "D";
				}
			}else{
				addObj[0][6] = "A"; //is additive
			}
			if( bean.getInvIncludeCheck().equals("true")){
				addObj[0][7] = "Y";
			}else{
				addObj[0][7] = "N";
			}	
			
			addObj[0][8] = investmentCode;
			
			result = getSqlModel().singleExecute(getQuery(2), addObj);
			
			if(result){
				//fetchInvestmentDetailsByYear(bean, bean.getFromYear(), bean.getToYear());
				fetchInvestmentDetails(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean fetchInvestmentDetails(TaxInv bean){
		
		boolean result=false;
		try {
			String query = " SELECT HRMS_TAX_INVESTMENT.INV_CODE, HRMS_TAX_INVESTMENT.INV_NAME, HRMS_TAX_INVESTMENT.INV_SECTION, HRMS_TAX_INVESTMENT.INV_CHAPTER, TO_CHAR(NVL(HRMS_TAX_INVESTMENT.INV_LIMIT1,0),9999999990.99), HRMS_TAX_INVESTMENT.INV_GROUP, DECODE(HRMS_TAX_INVESTMENT.INV_TYPE,'I','Investment','N','Non Investment'), " 
				+ " DECODE(HRMS_TAX_INVESTMENT.INV_OTHER_FLAG,'A','Additive','D','Deductive'), HRMS_TAX_INVESTMENT.INV_IS_INCLUDE_IN_80C_LIMIT" 
				+ " FROM HRMS_TAX_INVESTMENT WHERE 1=1";
			
			Object[][] invCodeObj = getSqlModel().getSingleResult(query);
			
			ArrayList<Object> exemptionList = new ArrayList<Object>();
			ArrayList<Object> deductionList = new ArrayList<Object>();
			ArrayList<Object> sectionList = new ArrayList<Object>();
			ArrayList<Object> otherList = new ArrayList<Object>();
			if (invCodeObj != null && invCodeObj.length > 0) {
				for (int i = 0; i < invCodeObj.length; i++) {
					TaxInv bean1 = new TaxInv();
					
					if (String.valueOf(invCodeObj[i][5]).equals("E")) {
						bean1.setInvestmentCodeExemptItt(String.valueOf(invCodeObj[i][0]));
						bean1.setInvestmentNameExemptItt(String.valueOf(invCodeObj[i][1]));
						bean1.setInvestmentSectionExemptItt(String.valueOf(invCodeObj[i][2]));
						bean1.setInvestmentChapterExemptItt(String.valueOf(invCodeObj[i][3]));
						bean1.setInvestmentLimitExemptItt(String.valueOf(invCodeObj[i][4]));
						bean1.setInvestmentGroupExemptItt(String.valueOf(invCodeObj[i][5]));
						bean1.setInvestmentTypeNameExemptItt(String.valueOf(invCodeObj[i][6]));
						if(String.valueOf(invCodeObj[i][8]).equals("Y")){
							bean1.setInvIncludeCheck("true");
						}							
						exemptionList.add(bean1);
						bean.setExemptionFlag(true);
					}
					if (String.valueOf(invCodeObj[i][5]).equals("D")) {
						bean1.setInvestmentCodeDeductionItt(String.valueOf(invCodeObj[i][0]));
						bean1.setInvestmentNameDeductionItt(String.valueOf(invCodeObj[i][1]));
						bean1.setInvestmentSectionDeductionItt(String.valueOf(invCodeObj[i][2]));
						bean1.setInvestmentChapterDeductionItt(String.valueOf(invCodeObj[i][3]));
						bean1.setInvestmentLimitDeductionItt(String.valueOf(invCodeObj[i][4]));
						bean1.setInvestmentGroupDeductionItt(String.valueOf(invCodeObj[i][5]));
						bean1.setInvestmentTypeNameDeductionItt(String.valueOf(invCodeObj[i][6]));
						if(String.valueOf(invCodeObj[i][8]).equals("Y")){
							bean1.setInvIncludeCheck("true");
						}
						deductionList.add(bean1);
						bean.setDeductionFlag(true);
					}
					if (String.valueOf(invCodeObj[i][5]).equals("S")) {
						bean1.setInvestmentCodeSectionItt(String.valueOf(invCodeObj[i][0]));
						bean1.setInvestmentNameSectionItt(String.valueOf(invCodeObj[i][1]));
						bean1.setInvestmentSectionSectionItt(String.valueOf(invCodeObj[i][2]));
						bean1.setInvestmentChapterSectionItt(String.valueOf(invCodeObj[i][3]));
						bean1.setInvestmentLimitSectionItt(String.valueOf(invCodeObj[i][4]));
						bean1.setInvestmentGroupSectionItt(String.valueOf(invCodeObj[i][5]));
						bean1.setInvestmentTypeNameSectionItt(String.valueOf(invCodeObj[i][6]));
						if(String.valueOf(invCodeObj[i][8]).equals("Y")){
							bean1.setInvIncludeCheck("true");
						}
						sectionList.add(bean1);
						bean.setSectionFlag(true);
					}
					if (String.valueOf(invCodeObj[i][5]).equals("O")) {
						bean1.setInvestmentCodeOtherItt(String.valueOf(invCodeObj[i][0]));
						bean1.setInvestmentNameOtherItt(String.valueOf(invCodeObj[i][1]));
						bean1.setInvestmentSectionOtherItt(String.valueOf(invCodeObj[i][2]));
						bean1.setInvestmentChapterOtherItt(String.valueOf(invCodeObj[i][3]));
						bean1.setInvestmentLimitOtherItt(String.valueOf(invCodeObj[i][4]));
						bean1.setInvestmentGroupOtherItt(String.valueOf(invCodeObj[i][5]));
						bean1.setInvestmentTypeNameOtherItt(String.valueOf(invCodeObj[i][6]));
						bean1.setInvestmentIsAdditiveItt(String.valueOf(invCodeObj[i][7]));
						if(String.valueOf(invCodeObj[i][8]).equals("Y")){
							bean1.setInvIncludeCheck("true");
						}
						otherList.add(bean1);
						bean.setOtherFlag(true);
					}
					bean.setInvestmentExemptList(exemptionList);
					bean.setInvestmentDeductionList(deductionList);
					bean.setInvestmentSectionList(sectionList);
					bean.setInvestmentOtherList(otherList);
					bean.setInvestmentName("");
					bean.setInvestmentChapter("");
					bean.setInvestmentSection("");
					bean.setInvestmentType("");
					bean.setInvestmentLimit("");
				}
				result = true;
				bean.setInvestmentFlag(true);
			} else {
				bean.setInvestmentFlag(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*public boolean fetchInvestmentDetailsByYear(TaxInv bean, String fromYr, String toYr){
		
		boolean result=false;
		try {
			String query = " SELECT HRMS_TAX_INVESTMENT.INV_CODE, HRMS_TAX_INVESTMENT.INV_NAME, HRMS_TAX_INVESTMENT.INV_SECTION, HRMS_TAX_INVESTMENT.INV_CHAPTER, NVL(HRMS_TAX_INVESTMENT.INV_LIMIT1,0), HRMS_TAX_INVESTMENT.INV_GROUP, HRMS_TAX_INVESTMENT.FROM_YEAR, HRMS_TAX_INVESTMENT.TO_YEAR, DECODE(HRMS_TAX_INVESTMENT.INV_TYPE,'I','Investment','N','Non Investment'), " 
				+ " DECODE(HRMS_TAX_INVESTMENT.INV_OTHER_FLAG,'A','Additive','D','Deductive') FROM HRMS_TAX_INVESTMENT WHERE "
				+ " HRMS_TAX_INVESTMENT.FROM_YEAR ="
				+fromYr
				+ " AND HRMS_TAX_INVESTMENT.TO_YEAR ="
				+toYr;
			
			Object[][] invCodeObj = getSqlModel().getSingleResult(query);
			
			ArrayList<Object> exemptionList = new ArrayList<Object>();
			ArrayList<Object> deductionList = new ArrayList<Object>();
			ArrayList<Object> sectionList = new ArrayList<Object>();
			ArrayList<Object> otherList = new ArrayList<Object>();
			if (invCodeObj != null && invCodeObj.length > 0) {
				for (int i = 0; i < invCodeObj.length; i++) {
					TaxInv bean1 = new TaxInv();

					if (String.valueOf(invCodeObj[i][5]).equals("E")) {
						bean1.setInvestmentCodeExemptItt(String.valueOf(invCodeObj[i][0]));
						bean1.setInvestmentNameExemptItt(String.valueOf(invCodeObj[i][1]));
						bean1.setInvestmentSectionExemptItt(String.valueOf(invCodeObj[i][2]));
						bean1.setInvestmentChapterExemptItt(String.valueOf(invCodeObj[i][3]));
						bean1.setInvestmentLimitExemptItt(String.valueOf(invCodeObj[i][4]));
						bean1.setInvestmentGroupExemptItt(String.valueOf(invCodeObj[i][5]));
						bean1.setInvestmentFromYearExemptItt(String.valueOf(invCodeObj[i][6]));
						bean1.setInvestmentToYearExemptItt(String.valueOf(invCodeObj[i][7]));
						bean1.setInvestmentTypeNameExemptItt(String.valueOf(invCodeObj[i][8]));
						exemptionList.add(bean1);
						bean.setExemptionFlag(true);
					}
					if (String.valueOf(invCodeObj[i][5]).equals("D")) {
						bean1.setInvestmentCodeDeductionItt(String.valueOf(invCodeObj[i][0]));
						bean1.setInvestmentNameDeductionItt(String.valueOf(invCodeObj[i][1]));
						bean1.setInvestmentSectionDeductionItt(String.valueOf(invCodeObj[i][2]));
						bean1.setInvestmentChapterDeductionItt(String.valueOf(invCodeObj[i][3]));
						bean1.setInvestmentLimitDeductionItt(String.valueOf(invCodeObj[i][4]));
						bean1.setInvestmentGroupDeductionItt(String.valueOf(invCodeObj[i][5]));
						bean1.setInvestmentFromYearDeductionItt(String.valueOf(invCodeObj[i][6]));
						bean1.setInvestmentToYearDeductionItt(String.valueOf(invCodeObj[i][7]));
						bean1.setInvestmentTypeNameDeductionItt(String.valueOf(invCodeObj[i][8]));
						deductionList.add(bean1);
						bean.setDeductionFlag(true);
					}
					if (String.valueOf(invCodeObj[i][5]).equals("S")) {
						bean1.setInvestmentCodeSectionItt(String.valueOf(invCodeObj[i][0]));
						bean1.setInvestmentNameSectionItt(String.valueOf(invCodeObj[i][1]));
						bean1.setInvestmentSectionSectionItt(String.valueOf(invCodeObj[i][2]));
						bean1.setInvestmentChapterSectionItt(String.valueOf(invCodeObj[i][3]));
						bean1.setInvestmentLimitSectionItt(String.valueOf(invCodeObj[i][4]));
						bean1.setInvestmentGroupSectionItt(String.valueOf(invCodeObj[i][5]));
						bean1.setInvestmentFromYearSectionItt(String.valueOf(invCodeObj[i][6]));
						bean1.setInvestmentToYearSectionItt(String.valueOf(invCodeObj[i][7]));
						bean1.setInvestmentTypeNameSectionItt(String.valueOf(invCodeObj[i][8]));
						sectionList.add(bean1);
						bean.setSectionFlag(true);
					}
					if (String.valueOf(invCodeObj[i][5]).equals("O")) {
						bean1.setInvestmentCodeOtherItt(String.valueOf(invCodeObj[i][0]));
						bean1.setInvestmentNameOtherItt(String.valueOf(invCodeObj[i][1]));
						bean1.setInvestmentSectionOtherItt(String.valueOf(invCodeObj[i][2]));
						bean1.setInvestmentChapterOtherItt(String.valueOf(invCodeObj[i][3]));
						bean1.setInvestmentLimitOtherItt(String.valueOf(invCodeObj[i][4]));
						bean1.setInvestmentGroupOtherItt(String.valueOf(invCodeObj[i][5]));
						bean1.setInvestmentFromYearOtherItt(String.valueOf(invCodeObj[i][6]));
						bean1.setInvestmentToYearOtherItt(String.valueOf(invCodeObj[i][7]));
						bean1.setInvestmentTypeNameOtherItt(String.valueOf(invCodeObj[i][8]));
						bean1.setInvestmentIsAdditiveItt(String.valueOf(invCodeObj[i][9]));
						otherList.add(bean1);
						bean.setOtherFlag(true);
					}
					bean.setInvestmentExemptList(exemptionList);
					bean.setInvestmentDeductionList(deductionList);
					bean.setInvestmentSectionList(sectionList);
					bean.setInvestmentOtherList(otherList);
					bean.setFromYear(fromYr);
					bean.setToYear(toYr);
					bean.setInvestmentName("");
					bean.setInvestmentChapter("");
					bean.setInvestmentSection("");
					bean.setInvestmentType("");
					bean.setInvestmentLimit("");
					bean.setPullInvestmentFlag(false);
				}
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	/*public boolean pullInvestmentsOfPreviousYear(TaxInv bean, String fromYr, String toYr){
		boolean result = false;
		try {
			int fromYear = Integer.parseInt(fromYr) - 1;
			int toYear = Integer.parseInt(toYr) - 1;
			
			String query = " SELECT HRMS_TAX_INVESTMENT.INV_CODE, HRMS_TAX_INVESTMENT.INV_NAME, HRMS_TAX_INVESTMENT.INV_SECTION, HRMS_TAX_INVESTMENT.INV_CHAPTER, NVL(HRMS_TAX_INVESTMENT.INV_LIMIT1,0), HRMS_TAX_INVESTMENT.INV_GROUP, HRMS_TAX_INVESTMENT.FROM_YEAR, HRMS_TAX_INVESTMENT.TO_YEAR, HRMS_TAX_INVESTMENT.INV_TYPE, HRMS_TAX_INVESTMENT.INV_OTHER_FLAG FROM HRMS_TAX_INVESTMENT WHERE "
				+ " HRMS_TAX_INVESTMENT.FROM_YEAR ="
				+fromYear
				+ " AND HRMS_TAX_INVESTMENT.TO_YEAR ="
				+toYear;
		
		Object[][] invCodeObj = getSqlModel().getSingleResult(query);
		
		if(invCodeObj!=null && invCodeObj.length >0){
			
			Object[][] maxInvCodeObj = getSqlModel().getSingleResult("SELECT NVL(MAX(HRMS_TAX_INVESTMENT.INV_CODE),0)+1 FROM HRMS_TAX_INVESTMENT");	
			int maxCode = Integer.parseInt(String.valueOf(maxInvCodeObj[0][0]));
			for (int i = 0; i < invCodeObj.length; i++) {
				invCodeObj[i][0] = maxCode+i;
				invCodeObj[i][6] = bean.getFromYear();
				invCodeObj[i][7] = bean.getToYear();
			}
			
			String insertQuery = " INSERT INTO HRMS_TAX_INVESTMENT (INV_CODE, INV_NAME, INV_SECTION, INV_CHAPTER, INV_LIMIT1, INV_GROUP, FROM_YEAR, TO_YEAR, INV_TYPE, INV_OTHER_FLAG)" 
				+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
			result = getSqlModel().singleExecute(insertQuery, invCodeObj);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		fetchInvestmentDetailsByYear(bean, fromYr, toYr);
		return result;
	}*/
	
	public boolean deleteInvestmentByInvestmentCode(TaxInv taxInv, String investmentCode) {
		boolean result = false;
		try {
			String deleteQuery = "DELETE FROM HRMS_TAX_INVESTMENT WHERE HRMS_TAX_INVESTMENT.INV_CODE ="
					+ investmentCode;
			result = getSqlModel().singleExecute(deleteQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void generateReport(TaxInv taxInv, HttpServletRequest request, HttpServletResponse response, String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = "PDF";
			rds.setReportType(type);
			String fileName = "Tax Investment Report"+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setReportName("TAX INVESTMENT REPORT");
			rds.setTotalColumns(15);
			rds.setPageSize("A4");
			rds.setShowPageNo(true);
			rds.setUserEmpId(taxInv.getUserEmpId());
			
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName+"."+type);
				request.setAttribute("action", "TaxInv_input.action");
			}
			rg = getReport(rg, taxInv);
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator  rg, TaxInv taxInv) {
		try {
			
			HashMap taxConfigMap = new HashMap();
			
			String taxConfigQuery = "SELECT HRMS_TAX_INVESTMENT.INV_GROUP, HRMS_TAX_INVESTMENT.INV_NAME, HRMS_TAX_INVESTMENT.INV_CHAPTER, HRMS_TAX_INVESTMENT.INV_SECTION, DECODE(HRMS_TAX_INVESTMENT.INV_TYPE,'I','Investment','N','Non Investment')"
				+ " , TO_CHAR(NVL(HRMS_TAX_INVESTMENT.INV_LIMIT1,0),9999999990.99) FROM HRMS_TAX_INVESTMENT ORDER BY HRMS_TAX_INVESTMENT.INV_GROUP" ;
			
			taxConfigMap = getSqlModel().getSingleResultMap(taxConfigQuery, 0, 2);
			
			String header[]= {"Investment Name", "Investment Chapter", "Investment Section", "Investment Type", "Investment Limit"};
			
			int[] cellwidth = { 35, 25, 35, 25, 25};
			int[] alignment = { 0, 0, 0, 0, 2};
			Object [][] groupObject = {{"E", "Exemption under 10-17"}, 
				{"D", "Deductions under chapter VI-A" },
				{"S", "Deductions under chapter VI(Sec 80C)"}, 
				{"O", "Other Income"}};
			
			if(taxConfigMap.size() > 0){
				for (int i = 0; i < groupObject.length; i++) {
					Object[][] detailsObject = null;
					try {
						detailsObject = (Object[][]) taxConfigMap.get(String.valueOf(groupObject[i][0]));
						
						if(detailsObject != null && detailsObject.length >0){
							TableDataSet exemptHeading = new TableDataSet();
							exemptHeading.setData(new Object[][] { { String.valueOf(groupObject[i][1]) } });
							exemptHeading.setCellAlignment(new int[] { 0 });
							exemptHeading.setCellWidth(new int[] { 100 });
							exemptHeading.setBodyFontStyle(1);
							rg.addTableToDoc(exemptHeading);
							
							TableDataSet exemptData = new TableDataSet();
							exemptData.setHeader(header);
							exemptData.setCellAlignment(alignment);
							exemptData.setCellWidth(cellwidth);
							exemptData.setData(Utility.removeColumns(detailsObject, new int[]{0}));
							exemptData.setHeaderTable(true);
							exemptData.setHeaderBorderDetail(3);
							exemptData.setBorderDetail(3);
							exemptData.setBlankRowsBelow(1);
							rg.addTableToDoc(exemptData);
						}else{
							TableDataSet noDataTable = new TableDataSet();
							noDataTable.setData(new Object[][] { { "No records avaliable for "+String.valueOf(groupObject[i][1]) } });
							noDataTable.setCellAlignment(new int[] { 0 });
							noDataTable.setCellWidth(new int[] { 100 });
							noDataTable.setBorder(false);
							noDataTable.setCellColSpan(new int[]{7});
							rg.addTableToDoc(noDataTable);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
			} else {
				TableDataSet noDataTable = new TableDataSet();
				noDataTable.setData(new Object[][] { { "No data avaliable for tax configuration"} });
				noDataTable.setCellAlignment(new int[] { 1 });
				noDataTable.setCellWidth(new int[] { 100 });
				noDataTable.setBorder(false);
				noDataTable.setCellColSpan(new int[]{7});
				rg.addTableToDoc(noDataTable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
	
	/* OLD METHOD NOT IN USE*/
	/*public void getReport_old(TaxInv taxInv, HttpServletResponse response) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String reportType = "Pdf";
			rds.setReportType(reportType);
			rds.setFileName("Tax Investment Report");
			rds.setReportName("Tax Investment Report");
			rds.setPageOrientation("portrait");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
			TableDataSet subtitleName = new TableDataSet();
			Object obj[][] = new Object[2][3];
			obj[0][1] = "Tax Investment Report For The Period : "
					+ taxInv.getFromYear() + " To " + taxInv.getToYear();
			obj[0][1] = "Tax Investment Report";
			obj[1][1] = "";
			
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(new int[] { 0,1,0 });
			subtitleName.setCellWidth(new int[] { 5,90,5 });
			subtitleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,new Color(0, 0, 0));
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(false);
			subtitleName.setBlankRowsBelow(1);
			rg.createHeader(subtitleName);
			
			String exemptQuery = "SELECT HRMS_TAX_INVESTMENT.INV_NAME, NVL(HRMS_TAX_INVESTMENT.INV_LIMIT1,0), HRMS_TAX_INVESTMENT.INV_CHAPTER, HRMS_TAX_INVESTMENT.INV_SECTION, DECODE(HRMS_TAX_INVESTMENT.INV_TYPE,'I','Investment','N','Non Investment') "
				+ " FROM HRMS_TAX_INVESTMENT " ;
				//+ " WHERE HRMS_TAX_INVESTMENT.FROM_YEAR ="+taxInv.getFromYear()+" AND HRMS_TAX_INVESTMENT.TO_YEAR ="+ taxInv.getToYear()
				//+ " AND HRMS_TAX_INVESTMENT.INV_GROUP='E'";
			
			Object[][] exemptionObj = getSqlModel().getSingleResult(exemptQuery);
			
			String deductionQuery = "SELECT HRMS_TAX_INVESTMENT.INV_NAME, NVL(HRMS_TAX_INVESTMENT.INV_LIMIT1,0), HRMS_TAX_INVESTMENT.INV_CHAPTER, HRMS_TAX_INVESTMENT.INV_SECTION, DECODE(HRMS_TAX_INVESTMENT.INV_TYPE,'I','Investment','N','Non Investment')"
				+ " FROM HRMS_TAX_INVESTMENT ";
				//+ " WHERE HRMS_TAX_INVESTMENT.FROM_YEAR ="+taxInv.getFromYear()+" AND HRMS_TAX_INVESTMENT.TO_YEAR ="+ taxInv.getToYear()
				//+ " AND HRMS_TAX_INVESTMENT.INV_GROUP='D'";
			
			Object[][] deductionObj = getSqlModel().getSingleResult(deductionQuery);
			
			String sectionQuery = "SELECT HRMS_TAX_INVESTMENT.INV_NAME, NVL(HRMS_TAX_INVESTMENT.INV_LIMIT1,0), HRMS_TAX_INVESTMENT.INV_CHAPTER, HRMS_TAX_INVESTMENT.INV_SECTION, DECODE(HRMS_TAX_INVESTMENT.INV_TYPE,'I','Investment','N','Non Investment')"
				+ " FROM HRMS_TAX_INVESTMENT ";
				//+ " WHERE HRMS_TAX_INVESTMENT.FROM_YEAR ="+taxInv.getFromYear()+" AND HRMS_TAX_INVESTMENT.TO_YEAR ="+ taxInv.getToYear()
				//+ " AND HRMS_TAX_INVESTMENT.INV_GROUP='S'";
			
			Object[][] sectionObj = getSqlModel().getSingleResult(sectionQuery);
			
			String otherQuery = "SELECT HRMS_TAX_INVESTMENT.INV_NAME, NVL(HRMS_TAX_INVESTMENT.INV_LIMIT1,0), HRMS_TAX_INVESTMENT.INV_CHAPTER, HRMS_TAX_INVESTMENT.INV_SECTION, DECODE(HRMS_TAX_INVESTMENT.INV_TYPE,'I','Investment','N','Non Investment')"
				+ " FROM HRMS_TAX_INVESTMENT ";
				//+ " WHERE HRMS_TAX_INVESTMENT.FROM_YEAR ="+taxInv.getFromYear()+" AND HRMS_TAX_INVESTMENT.TO_YEAR ="+ taxInv.getToYear()
				//+ " AND HRMS_TAX_INVESTMENT.INV_GROUP='O'";
			
			Object[][] otherObj = getSqlModel().getSingleResult(otherQuery);
			
			String header[]= {"Investment Name", "Investment Limit", "Investment Chapter", "Investment Section", "Investment Type"};
			
			int[] cellwidth = { 35, 25, 35, 25, 25};
			int[] alignment = { 0, 1, 0, 0, 0};
			
			if(exemptionObj!=null && exemptionObj.length >0){
				
				TableDataSet exemptHeading = new TableDataSet();
				exemptHeading.setData(new Object[][] { { "Exemption under 10-17" } });
				exemptHeading.setCellAlignment(new int[] { 0 });
				exemptHeading.setCellWidth(new int[] { 100 });
				exemptHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0));
				exemptHeading.setBorder(false);
				exemptHeading.setBodyBGColor(new Color(225, 225, 225));
        		rg.addTableToDoc(exemptHeading);
				
				TableDataSet exemptData = new TableDataSet();
				exemptData.setHeader(header);
				exemptData.setCellAlignment(alignment);
				exemptData.setCellWidth(cellwidth);
				exemptData.setData(exemptionObj);
				exemptData.setBorder(true);
				exemptData.setHeaderTable(true);
				exemptData.setHeaderBGColor(new Color(225, 225, 225));
				exemptData.setBlankRowsBelow(1);
				rg.addTableToDoc(exemptData);
				
			}else{
				TableDataSet noDataTable = new TableDataSet();
				noDataTable.setData(new Object[][] { { "No records avaliable for Exemption under 10-17." } });
				noDataTable.setCellAlignment(new int[] { 1 });
				noDataTable.setCellWidth(new int[] { 100 });
				noDataTable.setBorder(false);
				noDataTable.setCellColSpan(new int[]{7});
				rg.addTableToDoc(noDataTable);
			}
			if(deductionObj!=null && deductionObj.length >0){
				
				TableDataSet deductionHeading = new TableDataSet();
				deductionHeading.setData(new Object[][] { { "Deductions under chapter VI-A" } });
				deductionHeading.setCellAlignment(new int[] { 0 });
				deductionHeading.setCellWidth(new int[] { 100 });
				deductionHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0));
				deductionHeading.setBorder(false);
				deductionHeading.setBodyBGColor(new Color(225, 225, 225));
        		rg.addTableToDoc(deductionHeading);
        		
				TableDataSet deductionData = new TableDataSet();
				deductionData.setHeader(header);
				deductionData.setCellAlignment(alignment);
				deductionData.setCellWidth(cellwidth);
				deductionData.setData(deductionObj);
				deductionData.setBorder(true);
				deductionData.setHeaderTable(true);
				deductionData.setHeaderBGColor(new Color(225, 225, 225));
				deductionData.setBlankRowsBelow(1);
				rg.addTableToDoc(deductionData);
			}else{
				TableDataSet noDataTable = new TableDataSet();
				noDataTable.setData(new Object[][] { { "No records avaliable for Deductions under chapter VI-A" } });
				noDataTable.setCellAlignment(new int[] { 1 });
				noDataTable.setCellWidth(new int[] { 100 });
				noDataTable.setBorder(false);
				noDataTable.setCellColSpan(new int[]{7});
				rg.addTableToDoc(noDataTable);
			}
			if(sectionObj!=null && sectionObj.length >0){
				TableDataSet sectionHeading = new TableDataSet();
				sectionHeading.setData(new Object[][] { { "Deductions under chapter VI(Sec 80C)" } });
				sectionHeading.setCellAlignment(new int[] { 0 });
				sectionHeading.setCellWidth(new int[] { 100 });
				sectionHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0));
				sectionHeading.setBorder(false);
				sectionHeading.setBodyBGColor(new Color(225, 225, 225));
        		rg.addTableToDoc(sectionHeading);
				
				TableDataSet sectionData = new TableDataSet();
				sectionData.setHeader(header);
				sectionData.setCellAlignment(alignment);
				sectionData.setCellWidth(cellwidth);
				sectionData.setData(sectionObj);
				sectionData.setBorder(true);
				sectionData.setHeaderTable(true);
				sectionData.setHeaderBGColor(new Color(225, 225, 225));
				sectionData.setBlankRowsBelow(1);
				rg.addTableToDoc(sectionData);
				
			}else{
				TableDataSet noDataTable = new TableDataSet();
				noDataTable.setData(new Object[][] { { "No records avaliable for Deductions under chapter VI(Sec 80C)" } });
				noDataTable.setCellAlignment(new int[] { 1 });
				noDataTable.setCellWidth(new int[] { 100 });
				noDataTable.setBorder(false);
				noDataTable.setCellColSpan(new int[]{7});
				rg.addTableToDoc(noDataTable);
			}
			if(otherObj!=null && otherObj.length >0){
				TableDataSet otherHeading = new TableDataSet();
				otherHeading.setData(new Object[][] { { "Other Income" } });
				otherHeading.setCellAlignment(new int[] { 0 });
				otherHeading.setCellWidth(new int[] { 100 });
				otherHeading.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0));
				otherHeading.setBorder(false);
				otherHeading.setBodyBGColor(new Color(225, 225, 225));
        		rg.addTableToDoc(otherHeading);
				
				TableDataSet otherData = new TableDataSet();
				otherData.setHeader(header);
				otherData.setCellAlignment(alignment);
				otherData.setCellWidth(cellwidth);
				otherData.setData(otherObj);
				otherData.setBorder(true);
				otherData.setHeaderTable(true);
				otherData.setHeaderBGColor(new Color(225, 225, 225));
				otherData.setBlankRowsBelow(1);
				rg.addTableToDoc(otherData);
				
			}else{
				TableDataSet noDataTable = new TableDataSet();
				noDataTable.setData(new Object[][] { { "No records avaliable for Other Income" } });
				noDataTable.setCellAlignment(new int[] { 1 });
				noDataTable.setCellWidth(new int[] { 100 });
				noDataTable.setBorder(false);
				noDataTable.setCellColSpan(new int[]{7});
				rg.addTableToDoc(noDataTable);
			}
			
			rg.process();
			rg.createReport(response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
