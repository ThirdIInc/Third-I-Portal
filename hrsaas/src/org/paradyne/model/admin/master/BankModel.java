/**
 * 
 */
package org.paradyne.model.admin.master;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.master.BankMaster;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.common.ReportingModel;

import com.lowagie.text.Font;

/**
 * @author riteshr
 * @modified by Lakkichand
 * @modified by Ganesh 16/9/2011
 */
/**
 * to define the business logic for bank application
 */
public class BankModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	/**
	 * for inserting the data
	 * 
	 * @param bean
	 * @return
	 */
	public boolean addBank(BankMaster bean) {
		// logger.info("ritesh in addBank model");
		Object[][] addParam = new Object[1][9];

		addParam[0][0] = bean.getBankMicrCode().trim();
		addParam[0][1] = bean.getBankName().trim();
		addParam[0][2] = bean.getBranchName().trim();
		addParam[0][3] = bean.getBranchAddress().trim();
		addParam[0][4] = bean.getBranchCity().trim();
		addParam[0][5] = bean.getBranchCode();
		addParam[0][6] = bean.getBsrCode();
		addParam[0][7] = bean.getIfscCode().trim();
		addParam[0][8] = bean.getIsActive();

		return getSqlModel().singleExecute(getQuery(1), addParam);

	}

	/**
	 * to chech the microcode of bank
	 * 
	 * @param micrCode
	 * @return
	 */
	public Object[][] checkMicrCode(String micrCode) {
		// logger.info("ritesh in checkMicrCode model");
		Object[] param = new Object[1];
		param[0] = micrCode;
		Object[][] obj = getSqlModel().getSingleResult(getQuery(2), param);
		return obj;
	}

	/**
	 * to modify the bank details
	 * 
	 * @param bean
	 * @return
	 */
	public boolean modBank(BankMaster bean) {
		// logger.info("ritesh in modBank model");
		Object[][] modParam = new Object[1][10];
		modParam[0][0] = bean.getBankName().trim();
		modParam[0][1] = bean.getBranchName().trim();
		modParam[0][2] = bean.getBranchAddress().trim();
		modParam[0][3] = bean.getBranchCity().trim();
		modParam[0][4] = bean.getBranchCode().trim();
		modParam[0][5] = bean.getBankMicrCode().trim();
		modParam[0][6] = bean.getBsrCode().trim();
		modParam[0][7] = bean.getIfscCode().trim();
		modParam[0][8] = bean.getIsActive();

		modParam[0][9] = bean.getHiddenBankMicrCode();
		// logger.info("xxxxxl" + modParam[0][7]);
		return getSqlModel().singleExecute(getQuery(5), modParam);
	}

	/**
	 * to get the bank details
	 * 
	 * @param bean
	 */
	public void getBankRecord(BankMaster bean,HttpServletRequest request) {
		try {
		/*
		 * // logger.info("ritesh in getBankRecord bank"); Object[] param = new
		 * Object[1]; param[0] = bean.getBankMicrCode(); Object[][] obj =
		 * getSqlModel().getSingleResult(getQuery(2), param); for (int i = 0; i <
		 * obj.length; i++) {
		 * bean.setBankMicrCode(checkNull(String.valueOf(obj[i][0])));
		 * bean.setBankName(checkNull(String.valueOf(obj[i][1])));
		 * bean.setBranchName(checkNull(String.valueOf(obj[i][2])));
		 * bean.setBranchAddress(checkNull(String.valueOf(obj[i][3])));
		 * bean.setBranchCity(checkNull(String.valueOf(obj[i][4])));
		 * bean.setBranchCode(checkNull(String.valueOf(obj[i][5])));
		 * bean.setBsrCode(checkNull(String.valueOf(obj[i][6])));
		 * bean.setIfscCode(checkNull(String.valueOf(obj[i][7])));
		 * bean.setIsActive(checkNull(String.valueOf(obj[i][8])));
		 *  }
		 */
		System.out.println("BANK ID_______________== " + bean.getHiddenBankId());
		// logger.info("calforedit in model");
		String query = "SELECT BANK_MICR_CODE,BANK_NAME,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_CITY,BRANCH_CODE,BANK_BSR_CODE,BANK_IFSC_CODE,BANK_ID FROM HRMS_BANK WHERE BANK_ID= "
				+ bean.getHiddenBankId();
		Object[][] data = getSqlModel().getSingleResult(query);
		// bean.setBankMicrCode(checkNull((String.valueOf(data[0][0]))));
		bean.setBankName(checkNull((String.valueOf(data[0][1]))));
		// bean.setBranchName(checkNull((String.valueOf(data[0][2]))));
		// bean.setBranchAddress(checkNull((String.valueOf(data[0][3]))));
		// bean.setBranchCity(checkNull((String.valueOf(data[0][4]))));
		// bean.setBranchCode(checkNull((String.valueOf(data[0][5]))));
		// bean.setBsrCode(checkNull((String.valueOf(data[0][6]))));
		// bean.setIfscCode(checkNull((String.valueOf(data[0][7]))));
		bean.setBankId(checkNull((String.valueOf(data[0][8]))));
		// bean.setHiddenBankMicrCode(checkNull((String.valueOf(data[0][0]))));

		String branchQuery = "SELECT BANK_MICR_CODE,BANK_NAME,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_CITY,BRANCH_CODE,BANK_BSR_CODE,BANK_IFSC_CODE,BANK_ID FROM HRMS_BANK WHERE BANK_TYPE='C' AND BANK_PARENT_ID= "
				+ bean.getBankId();

		Object[][] repData = getSqlModel().getSingleResult(branchQuery);
		System.out.println("repData.length====" + repData.length);
		if (repData != null && repData.length > 0) {
			System.out.println("repData.length==INSIDE IFFFF==" + repData.length);
			bean.setModeLength("true");
			bean.setTotalRecords(String.valueOf(repData.length));
			String[] pageIndex = Utility.doPaging(bean.getMyPageInProcess(),
					repData.length, 5);
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
				bean.setMyPageInProcess("1");
			int k = 0;
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
			.parseInt(pageIndex[1]); i++) {
				BankMaster bean1 = new BankMaster();
				bean1.setIttbankMicrCode(checkNull((String
						.valueOf(repData[k][0]))));
				bean1
						.setBankName(checkNull((String
								.valueOf(repData[k][1]))));
				bean1.setIttbranchName(checkNull((String
						.valueOf(repData[k][2]))));
				bean1.setIttbranchAddress(checkNull((String
						.valueOf(repData[k][3]))));
				bean1.setIttbranchCity(checkNull((String
						.valueOf(repData[k][4]))));
				bean1.setIttbranchCode(checkNull((String
						.valueOf(repData[k][5]))));
				bean1.setIttbsrCode(checkNull((String
						.valueOf(repData[k][6]))));
				bean1.setIttifscCode(checkNull((String
						.valueOf(repData[k][7]))));
				bean1.setBankId(checkNull((String.valueOf(repData[k][8]))));
				bean1
						.setIttbankId(checkNull((String
								.valueOf(repData[k][8]))));

				System.out.println("repData[0][0]" + repData[k][0]);
				List.add(bean1);
				k++;
			}

			bean.setBranchesList(List);

		}

	} catch (Exception e) {
		// TODO: handle exception
	}
}

	/**
	 * to delete the single bank record
	 * 
	 * @param bean
	 * @return
	 */
	public boolean deleteBank(BankMaster bean) {
		// logger.info("ritesh in deleteBank METHOD IN MODEL");
		Object[][] delObj = new Object[1][1];
		delObj[0][0] = bean.getHiddenBankId();
		 getSqlModel().singleExecute(getQuery(3), delObj);
		
		 
		 String delQuery = "DELETE FROM HRMS_BANK WHERE BANK_PARENT_ID="+ bean.getHiddenBankId();
		 getSqlModel().singleExecute( delQuery);
		 return true;
	}

	public void getBankReport(BankMaster bean) {
		Object[][] reportObj = getSqlModel().getSingleResult(getQuery(4));
		ArrayList list = new ArrayList();
		for (int i = 0; i < reportObj.length; i++) {
			BankMaster bean1 = new BankMaster();
			bean1.setBankMicrCode(checkNull(String.valueOf(reportObj[i][0])));
			bean1.setBankName(checkNull(String.valueOf(reportObj[i][1])));
			bean1.setBranchName(checkNull(String.valueOf(reportObj[i][2])));
			bean1.setBranchAddress(checkNull(String.valueOf(reportObj[i][3])));
			bean1.setBranchCity(checkNull(String.valueOf(reportObj[i][4])));
			bean1.setBranchCode(checkNull(String.valueOf(reportObj[i][5])));
			bean1.setIsActive(checkNull(String.valueOf(reportObj[i][6])));
			list.add(bean1);

		}// end of loop
		bean.setBankList(list);

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
	 * to generate the bank report
	 * 
	 * @param bankMaster
	 * @param request
	 * @param response
	 * @param context
	 * @param label
	 */
	public void getReport_old(BankMaster bankMaster,
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context, String[] label) {
		// TODO Auto-generated method stub
		/*
		 * CrystalReport cr = new CrystalReport(); String path =
		 * "org\\paradyne\\rpt\\admin\\master\\bank.rpt";
		 * cr.createReport(request, response, context, session, path, "");
		 */
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nBank Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Bank  Master.Pdf");
		String queryDes = "SELECT BANK_MICR_CODE,BANK_NAME,BRANCH_CODE ,BRANCH_NAME,BRANCH_CITY,BRANCH_ADDRESS,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_BANK order by BANK_MICR_CODE";
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][8];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				Data[i][4] = data[i][3];
				Data[i][5] = data[i][4];
				Data[i][6] = data[i][5];
				Data[i][7] = data[i][6];
				j++;
			}
			int cellwidth[] = { 15, 25, 35, 25, 35, 25, 40, 20 };
			int alignment[] = { 1, 0, 0, 0, 0, 0, 0, 0 };
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}
		rg.createReport(response);

	}

	/**
	 * to display the data after clicking on search button
	 * 
	 * @param bean
	 * @param request
	 */

	public void Data(BankMaster bean, HttpServletRequest request) {
		Object[][] repData = getSqlModel().getSingleResult(getQuery(6));
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
				BankMaster bean1 = new BankMaster();
				bean1.setHiddenBankId(checkNull(String.valueOf(repData[i][0])));
				bean1.setBankName(checkNull(String.valueOf(repData[i][1])));
			//	bean1.setBranchName((checkNull(String.valueOf(repData[i][2]))));
			//	bean1.setIsActive(checkNull(String.valueOf(repData[i][3])));
				List.add(bean1);
			}// end of loop
			bean.setIteratorlist(List);
		}
	}

	/**
	 * to maodify the record by double click on the list
	 * 
	 * @param bean
	 */

	public void calforedit(BankMaster bean, HttpServletRequest request) {
		try {
			System.out.println("BANK ID_______________== " + bean.getHiddenBankId());
			// logger.info("calforedit in model");
			String query = "SELECT BANK_MICR_CODE,BANK_NAME,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_CITY,BRANCH_CODE,BANK_BSR_CODE,BANK_IFSC_CODE,BANK_ID FROM HRMS_BANK WHERE BANK_ID= "
					+ bean.getHiddenBankId();
			Object[][] data = getSqlModel().getSingleResult(query);
			// bean.setBankMicrCode(checkNull((String.valueOf(data[0][0]))));
			bean.setBankName(checkNull((String.valueOf(data[0][1]))));
			// bean.setBranchName(checkNull((String.valueOf(data[0][2]))));
			// bean.setBranchAddress(checkNull((String.valueOf(data[0][3]))));
			// bean.setBranchCity(checkNull((String.valueOf(data[0][4]))));
			// bean.setBranchCode(checkNull((String.valueOf(data[0][5]))));
			// bean.setBsrCode(checkNull((String.valueOf(data[0][6]))));
			// bean.setIfscCode(checkNull((String.valueOf(data[0][7]))));
			bean.setHiddenBankId(checkNull((String.valueOf(data[0][8]))));
			
			bean.setHiddencode(checkNull((String.valueOf(data[0][8]))));
			
			
			// bean.setHiddenBankMicrCode(checkNull((String.valueOf(data[0][0]))));

			String branchQuery = "SELECT BANK_MICR_CODE,BANK_NAME,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_CITY,BRANCH_CODE,BANK_BSR_CODE,BANK_IFSC_CODE,BANK_ID FROM HRMS_BANK WHERE BANK_TYPE='C' AND BANK_PARENT_ID  = "
					+ bean.getHiddenBankId();

			Object[][] repData = getSqlModel().getSingleResult(branchQuery);
			System.out.println("repData.length====" + repData.length);
			if (repData != null && repData.length > 0) {
				System.out.println("repData.length==INSIDE IFFFF==" + repData.length);
				bean.setModeLength("true");
				bean.setTotalRecords(String.valueOf(repData.length));
				String[] pageIndex = Utility.doPaging(bean.getMyPageInProcess(),
						repData.length, 5);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "5";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					bean.setMyPageInProcess("1");
				int k = 0;
				ArrayList<Object> List = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
				.parseInt(pageIndex[1]); i++) {
					BankMaster bean1 = new BankMaster();
					bean1.setIttbankMicrCode(checkNull((String
							.valueOf(repData[k][0]))));
					bean1
							.setBankName(checkNull((String
									.valueOf(repData[k][1]))));
					bean1.setIttbranchName(checkNull((String
							.valueOf(repData[k][2]))));
					bean1.setIttbranchAddress(checkNull((String
							.valueOf(repData[k][3]))));
					bean1.setIttbranchCity(checkNull((String
							.valueOf(repData[k][4]))));
					bean1.setIttbranchCode(checkNull((String
							.valueOf(repData[k][5]))));
					bean1.setIttbsrCode(checkNull((String
							.valueOf(repData[k][6]))));
					bean1.setIttifscCode(checkNull((String
							.valueOf(repData[k][7]))));
					bean1.setIttbankId(checkNull((String.valueOf(repData[k][8]))));
					bean1
							.setIttbankId(checkNull((String
									.valueOf(repData[k][8]))));

					System.out.println("repData[0][0]" + repData[k][0]);
					List.add(bean1);
					k++;
				}

				bean.setBranchesList(List);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	/**
	 * to delete the single record after click on save or search button
	 * 
	 * @param bean
	 * @return
	 */
	public boolean calfordelete(BankMaster bean) {

		Object[][] delete = null;
		try {
			delete = new Object[1][1];
			delete[0][0] = bean.getHiddencode();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getSqlModel().singleExecute(getQuery(3), delete);
		// TODO Auto-generated method stub

	}

	/**
	 * to delete the multiple record using with check boxes
	 * 
	 * @param bean
	 * @param code
	 * @return
	 */
	public String deletecheckedRecords(BankMaster bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(3), delete);
					if (!result) {
						count++;
					}// end of if
				}// end of nested if
			}// end of loop

		}// end of nested if
		if (count != 0)
			return "false";
		else
			return "true";

	}

	/*public boolean saveDesc(BankMaster bankMaster, HttpServletRequest request) {
		boolean result;
		Object[][] insDesc = new Object[1][2];
		insDesc[0][0] = bankMaster.getDescription();
		insDesc[0][1] = bankMaster.getDate();

		String insQuery = "INSERT INTO HRMS_DAILY_STUFF(DAILY_STUFF_DESCRIPTION, DAILY_STUFF_DATE) VALUES(?,TO_DATE(?,'DD-MM-YYYY'))";
		result = getSqlModel().singleExecute(insQuery, insDesc);
		return result;

	}*/

	public boolean saveFunction(BankMaster bean) {
		boolean result = false;
		try {

			if (!checkDuplicate(bean)) {
				Object[][] addParam = new Object[1][2];
				addParam[0][0] = bean.getBankName().trim();
				addParam[0][1] = "P";

				String insertQuery = "INSERT INTO HRMS_BANK"
						+ "(BANK_ID, BANK_NAME, BANK_TYPE ,BANK_MICR_CODE)"
						+ " VALUES((SELECT NVL(MAX(BANK_ID),0)+1 FROM HRMS_BANK),?,?,(SELECT NVL(MAX(BANK_MICR_CODE),0)+1 FROM HRMS_BANK))";

				
				for (int i = 0; i < addParam.length; i++) {
					System.out.println( "" + addParam[0][i]);
				}
				 

				result = getSqlModel().singleExecute(insertQuery, addParam);

				if (result) {
					String autoCodeQuery = " SELECT NVL(MAX(BANK_ID),0) FROM HRMS_BANK ";
					Object[][] data = getSqlModel().getSingleResult(
							autoCodeQuery);
					if (data != null && data.length > 0) {
						bean.setHiddenBankId(String.valueOf(data[0][0]));
					}
				}

			}// end of if
			else {
				return false;
			}// end of else

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateRecords(BankMaster bean, HttpServletRequest request) {
		// logger.info("ritesh in modBank model");
		boolean result = false;
		try {
			System.out.println("BANK ID:::" + bean.getHiddenBankId());
			//if (!checkDuplicateMod(bean)) {
				try {
					// records update for parent bank name..

					// if (!checkDuplicate(bean)) {
					Object updateBankNameObj[][] = new Object[1][2];
					updateBankNameObj[0][0] = bean.getBankName();
					// updateBankNameObj[0][1] = "P";
					updateBankNameObj[0][1] = bean.getHiddenBankId();

					String updateQuery = "UPDATE HRMS_BANK SET "
							+ " BANK_NAME = ?  WHERE BANK_ID=? AND BANK_TYPE = 'P'";
					result = getSqlModel().singleExecute(updateQuery,
							updateBankNameObj);

					for (int i = 0; i < 5; i++) {
						System.out.println("Insert Bank  Name::::::"
								+ updateBankNameObj[0][i]);
					}

					// }// end of if
					// else {
					// return false;
					// }// end of else

				} catch (Exception e) {
					// TODO: handle exception
				}
				

			//}// end of if
			//else {
			//	return false;
			//}// end of else

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(BankMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_BANK WHERE UPPER(BANK_NAME) LIKE '"
				+ bean.getBankName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for checking duplicate entry of record during modification */
	public boolean checkDuplicateMod(BankMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_BANK WHERE BANK_MICR_CODE IN ("
				+ bean.getBankMicrCode().trim() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	public void getReport(BankMaster bankMaster, HttpServletRequest request,
			HttpServletResponse response, ServletContext context, String[] label) {
		try {

			String reportType = "";

			logger.info("reportType--------------->" + reportType + "<-------");

			String title = " Bank Master Report ";
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("Bank Master Report");
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

			String bankNameQuery = " SELECT BANK_NAME,BANK_ID FROM HRMS_BANK WHERE BANK_TYPE='P' ORDER BY BANK_NAME ASC ";
			Object[][] processDetail = getSqlModel().getSingleResult(
					bankNameQuery);

			// =============Details of tax deducted====================//

			if (processDetail != null && processDetail.length > 0) {
				int count = 0;
				for (int i = 0; i < processDetail.length; i++) {
					Object[][] bankNameDetails = new Object[1][4];// new-------------->
					bankNameDetails[0][0] = "Bank Name :";
					bankNameDetails[0][1] = String.valueOf(processDetail[i][0]);
					bankNameDetails[0][2] = "";
					bankNameDetails[0][3] = "";

					TableDataSet bankInfoTable = new TableDataSet();
					bankInfoTable.setData(bankNameDetails);
					bankInfoTable.setCellWidth(new int[] { 25, 25, 25, 25 });
					bankInfoTable.setCellAlignment(new int[] { 0, 0, 0, 0 });
					bankInfoTable.setBorder(false);
					bankInfoTable.setBodyFontDetails(Font.HELVETICA, 8,
							Font.BOLD, new Color(0, 0, 0));
					rg.addTableToDoc(bankInfoTable);

					Object[][] branchData = null;
					try {

						String branchDataQuery = "SELECT BANK_MICR_CODE,BRANCH_NAME,BANK_BSR_CODE,BANK_IFSC_CODE,BRANCH_CITY,BRANCH_ADDRESS,"
								+ "BANK_ID FROM HRMS_BANK WHERE BANK_TYPE='C' AND BANK_PARENT_ID = "
								+ processDetail[i][1];

						branchData = getSqlModel().getSingleResult(
								branchDataQuery);

						String[] columns = new String[] { "Bank MICR Code",
								"Branch Name","BSR Code", "IFSC Code ", "City ", "Address" };

						int[] cellWidthChallan = { 20, 20,20, 20, 20, 20 };
						int[] cellAlignChallan = { 0, 0, 0, 0, 0, 0 };

						if (branchData != null && branchData.length > 0) {

							TableDataSet branchDetails = new TableDataSet();

							branchDetails.setHeader(columns);
							branchDetails.setData(branchData);
							branchDetails.setCellWidth(cellWidthChallan);
							branchDetails.setCellAlignment(cellAlignChallan);
							branchDetails.setBorder(true);
							branchDetails.setHeaderBGColor(new Color(225, 225,
									225));
							branchDetails.setBodyFontDetails(Font.HELVETICA, 8,
									Font.NORMAL, new Color(0, 0, 0));
							branchDetails.setBlankRowsBelow(1);
							rg.addTableToDoc(branchDetails);
						} else {
							TableDataSet noData = new TableDataSet();
							Object[][] noDataObj = new Object[1][1];
							noDataObj[0][0] = "No records available";
							// noData.setHeader(columns);
							noData.setData(noDataObj);
							noData.setCellAlignment(new int[] { 1 });
							noData.setCellWidth(new int[] { 100 });
							noData.setBodyFontDetails(Font.HELVETICA, 10,
									Font.NORMAL, new Color(0, 0, 0));
							noData.setBorder(true);
							noData.setBlankRowsBelow(1);
							rg.addTableToDoc(noData);
						}
					} catch (Exception e) {
						logger
								.error("exception in challanDataMarDec object",
										e);
					} // end of catch
					// =============Branch details header===================//
					Object[][] branchNameDtl = new Object[1][5];// new------------->

					// =====================Branch details==================//

					count++;
				}

			} else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				// noData.setHeader(columns);
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10,
						Font.NORMAL, new Color(0, 0, 0));
				noData.setBorder(true);
				noData.setBlankRowsBelow(1);
				rg.addTableToDoc(noData);
			}

			rg.process();
			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void branchSavedData(BankMaster bean,
			HttpServletRequest request) {
		try {
					String branchQuery = "SELECT BANK_MICR_CODE,BANK_NAME,BRANCH_NAME,BRANCH_ADDRESS,BRANCH_CITY,BRANCH_CODE,BANK_BSR_CODE,BANK_IFSC_CODE,BANK_ID FROM HRMS_BANK WHERE BANK_TYPE='C' AND BANK_PARENT_ID= "
			+ bean.getHiddenBankId();

	Object[][] repData = getSqlModel().getSingleResult(branchQuery);
	if (repData != null && repData.length > 0) {
		System.out.println("================"+repData.length);
		bean.setModeLength("true");
		bean.setTotalRecords(String.valueOf(repData.length));
		String[] pageIndex = Utility.doPaging(bean.getMyPageInProcess(),
				repData.length, 5);
		System.out.println("pageIndex ::: "+pageIndex);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "5";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		System.out.println("pageIndex[0] : "+pageIndex[0]);
		System.out.println("pageIndex[1] : "+pageIndex[1]);
		System.out.println("pageIndex[2] : "+pageIndex[2]);
		System.out.println("pageIndex[3] : "+pageIndex[3]);
		System.out.println("pageIndex[4] : "+pageIndex[4]);
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			bean.setMyPageInProcess("1");
		//int k = 0;
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i = Integer.parseInt(pageIndex[0]); i < Integer
		.parseInt(pageIndex[1]); i++) {
			BankMaster bean1 = new BankMaster();
			bean1.setIttbankMicrCode(checkNull((String
					.valueOf(repData[i][0]))));
			bean1
					.setBankName(checkNull((String
							.valueOf(repData[i][1]))));
			bean1.setIttbranchName(checkNull((String
					.valueOf(repData[i][2]))));
			bean1.setIttbranchAddress(checkNull((String
					.valueOf(repData[i][3]))));
			bean1.setIttbranchCity(checkNull((String
					.valueOf(repData[i][4]))));
			bean1.setIttbranchCode(checkNull((String
					.valueOf(repData[i][5]))));
			bean1.setIttbsrCode(checkNull((String
					.valueOf(repData[i][6]))));
			bean1.setIttifscCode(checkNull((String
					.valueOf(repData[i][7]))));
			bean1.setBankId(checkNull((String.valueOf(repData[i][8]))));
			bean1
					.setIttbankId(checkNull((String
							.valueOf(repData[i][8]))));

			System.out.println("repData[0][0]" + repData[i][0]);
			List.add(bean1);
			//k++;
		}

		bean.setBranchesList(List);
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public boolean addBranchDtl(BankMaster bean,
			HttpServletRequest request) {
		boolean result = false;
		
		try {
			
			Object updateBankNameObj[][] = new Object[1][2];
			updateBankNameObj[0][0] = bean.getBankName();
			// updateBankNameObj[0][1] = "P";
			updateBankNameObj[0][1] = bean.getHiddenBankId();

			String updateQuery = "UPDATE HRMS_BANK SET "
					+ " BANK_NAME = ?  WHERE BANK_ID=? AND BANK_TYPE = 'P'";
			result = getSqlModel().singleExecute(updateQuery,
					updateBankNameObj);
			
			if (!checkDuplicateMod(bean)) {
			
			Object addObj[][] = new Object[1][8];
			//addObj[0][0] = bean.getHiddenBankId();
			addObj[0][0] = bean.getBranchName();
			addObj[0][1] = bean.getBranchCode();
			addObj[0][2] = bean.getBsrCode();
			addObj[0][3] = bean.getBankMicrCode();
			addObj[0][4] = bean.getIfscCode();
			addObj[0][5] = bean.getBranchCity();
			addObj[0][6] = bean.getBranchAddress();
			addObj[0][7] = "C";
			
			System.out.println("1=" + addObj[0][0]);
			System.out.println("2=" + addObj[0][1]);
			System.out.println("3=" + addObj[0][2]);
			System.out.println("4=" + addObj[0][3]);
			System.out.println("5=" + addObj[0][4]);
			System.out.println("6=" + addObj[0][5]);
			System.out.println("7=" + addObj[0][6]);
			System.out.println("8=" + addObj[0][7]);
			System.out.println("bean.getHiddenBankId()=" + bean.getHiddenBankId());
			
			String insertQuery = "INSERT INTO HRMS_BANK"
				+ "(BANK_ID, BRANCH_NAME, BRANCH_CODE,BANK_BSR_CODE,BANK_MICR_CODE,BANK_IFSC_CODE,BRANCH_CITY ,BRANCH_ADDRESS , BANK_TYPE,BANK_NAME,BANK_PARENT_ID)"
				+ " VALUES((SELECT NVL(MAX(BANK_ID),0)+1 FROM HRMS_BANK),?,?,?,?,?,?,?,?,'"+bean.getBankName()+"','"+bean.getHiddenBankId()+"')";
			
			result = getSqlModel().singleExecute(insertQuery, addObj);
			
			
			/*if (result) {
				String autoCodeQuery = " SELECT NVL((BANK_ID),0) FROM HRMS_BANK ";
				Object[][] data = getSqlModel().getSingleResult(
						autoCodeQuery);
				if (data != null && data.length > 0) {
					bean.setHiddenBankId(String.valueOf(data[0][0]));
					
				}
			}*/
			
			for (int i = 0; i < addObj.length; i++) {
				System.out.println("" + addObj[0][i]);
			}
			
			}// end of if
			else {
				return false;
			}// end of else
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	public boolean modBranchDtl(BankMaster bean,
			HttpServletRequest request) {
		boolean result = false;
		
		try {
			// records update for parent bank name..

		//	 if (!checkDuplicate(bean)) {
			Object updateBankNameObj[][] = new Object[1][2];
			updateBankNameObj[0][0] = bean.getBankName();
			// updateBankNameObj[0][1] = "P";
			updateBankNameObj[0][1] = bean.getHiddenBankId();

			String updateQuery = "UPDATE HRMS_BANK SET "
					+ " BANK_NAME = ?  WHERE BANK_ID=? AND BANK_TYPE = 'P'";
			result = getSqlModel().singleExecute(updateQuery,
					updateBankNameObj);

					
			
			System.out.println("UPDATEEEEEE IN MODELLLL");
			Object updateObj[][] = new Object[1][9];
			
			updateObj[0][0] = bean.getBankName();
			updateObj[0][1] = bean.getBranchName();
			updateObj[0][2] = bean.getBranchCode();
			updateObj[0][3] = bean.getBsrCode();
			updateObj[0][4] = bean.getBankMicrCode();
			updateObj[0][5] = bean.getIfscCode();
			updateObj[0][6] = bean.getBranchCity();
			updateObj[0][7] = bean.getBranchAddress();
			updateObj[0][8] = "C";
			//updateObj[0][9] = bean.getIttbankId();
			
			System.out.println("1=" + updateObj[0][0]);
			System.out.println("2=" + updateObj[0][1]);
			System.out.println("3=" + updateObj[0][2]);
			System.out.println("4=" + updateObj[0][3]);
			System.out.println("5=" + updateObj[0][4]);
			System.out.println("6=" + updateObj[0][5]);
			System.out.println("7=" + updateObj[0][6]);
			System.out.println("8=" + updateObj[0][7]);
			System.out.println("9=" + updateObj[0][8]);
			System.out.println("10=" + bean.getIttbankId());
			System.out.println("10=" + bean.getHiddenBankId());
			
			String insertQuery = "UPDATE HRMS_BANK SET "
				+ " BANK_NAME = ? , BRANCH_NAME = ? , BRANCH_CODE = ?  , BANK_BSR_CODE  = ? , BANK_MICR_CODE = ? , BANK_IFSC_CODE = ?  ,BRANCH_CITY = ?  ,BRANCH_ADDRESS = ? " +
						" ,BANK_TYPE = ?    WHERE BANK_ID = " + bean.getParacode()+" AND BANK_PARENT_ID ="+ bean.getHiddenBankId();
			

			result = getSqlModel().singleExecute(insertQuery, updateObj);

			for(int i = 0; i < updateObj.length; i++) {
				for(int j = 0; j < updateObj[i].length; j++) {
					logger.info("updateObj[" + i + "][" + j + "]  " + updateObj[i][j]);
				}
			}

			
			

			// }// end of if
		//	 else {
			// return false;
		//	 }// end of else

		
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public void getRecord(BankMaster bankMaster) {
 
		try {
			String editQuery = " SELECT NVL(BANK_NAME,' '),NVL(BRANCH_NAME,' '),BRANCH_CODE,BANK_BSR_CODE,BANK_MICR_CODE,BANK_IFSC_CODE,"
					+ " BRANCH_CITY ,BRANCH_ADDRESS FROM HRMS_BANK "
					+ " WHERE  BANK_TYPE ='C'	AND BANK_ID = "
					+ bankMaster.getParacode();
			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {

				bankMaster.setBankName(checkNull(String.valueOf(editObj[0][0]).trim()));
				bankMaster.setBranchName(checkNull(String.valueOf(editObj[0][1]).trim()));
				bankMaster.setBranchCode(checkNull(String.valueOf(editObj[0][2]).trim()));
				bankMaster.setBsrCode(checkNull(String.valueOf(editObj[0][3]).trim()));
				bankMaster.setBankMicrCode(checkNull(String.valueOf(editObj[0][4])));
				bankMaster.setIfscCode(checkNull(String.valueOf(editObj[0][5])));
				bankMaster.setBranchCity(checkNull(String.valueOf(editObj[0][6]).trim()));
				bankMaster.setBranchAddress(checkNull(String.valueOf(editObj[0][7]).trim()));
								
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
		
	}
	
	

	public boolean delBranchDtl(BankMaster bean,
			HttpServletRequest request) {
		Object del[][] = null;
		try {
			del = new Object[1][1];
			// to delete the single record after clicking on saving or searching
			// button
			del[0][0] = bean.getParacode();
		} catch(Exception e) {
			// TODO: handle exception
		}
		String deleteQuery = "DELETE FROM HRMS_BANK WHERE BANK_ID=? ";

		return getSqlModel().singleExecute(deleteQuery, del);
	}
	
	public void getReportBranch(BankMaster bankMaster, HttpServletRequest request,
			HttpServletResponse response, ServletContext context, String[] label) {
		try {

			String reportType = "";

			logger.info("reportType--------------->" + reportType + "<-------");

			String title = " Bank Master Report ";
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("Bank Master Report");
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

			String bankNameQuery = " SELECT BANK_NAME,BANK_ID FROM HRMS_BANK WHERE BANK_TYPE='P' AND BANK_ID = "+ bankMaster.getHiddenBankId()+" ORDER BY BANK_NAME ASC ";
			Object[][] processDetail = getSqlModel().getSingleResult(
					bankNameQuery);

			// =============Details of tax deducted====================//

			if (processDetail != null && processDetail.length > 0) {
				int count = 0;
				for (int i = 0; i < processDetail.length; i++) {
					Object[][] bankNameDetails = new Object[1][4];// new-------------->
					bankNameDetails[0][0] = "Bank Name :";
					bankNameDetails[0][1] = String.valueOf(processDetail[i][0]);
					bankNameDetails[0][2] = "";
					bankNameDetails[0][3] = "";

					TableDataSet bankInfoTable = new TableDataSet();
					bankInfoTable.setData(bankNameDetails);
					bankInfoTable.setCellWidth(new int[] { 25, 25, 25, 25 });
					bankInfoTable.setCellAlignment(new int[] { 0, 0, 0, 0 });
					bankInfoTable.setBorder(false);
					bankInfoTable.setBodyFontDetails(Font.HELVETICA, 8,
							Font.BOLD, new Color(0, 0, 0));
					rg.addTableToDoc(bankInfoTable);

					Object[][] branchData = null;
					try {

						String branchDataQuery = "SELECT BANK_MICR_CODE,BRANCH_NAME,BANK_BSR_CODE,BANK_IFSC_CODE,BRANCH_CITY,BRANCH_ADDRESS,"
								+ "BANK_ID FROM HRMS_BANK WHERE BANK_TYPE='C' AND BANK_PARENT_ID = "
								+ processDetail[i][1];

						branchData = getSqlModel().getSingleResult(
								branchDataQuery);

						String[] columns = new String[] { "Bank MICR Code",
								"Branch Name","BSR Code", "IFSC Code ", "City ", "Address" };

						int[] cellWidthChallan = { 20, 20,20, 20, 20, 20 };
						int[] cellAlignChallan = { 0, 0,0, 0, 0, 0 };

						if (branchData != null && branchData.length > 0) {

							TableDataSet branchDetails = new TableDataSet();

							branchDetails.setHeader(columns);
							branchDetails.setData(branchData);
							branchDetails.setCellWidth(cellWidthChallan);
							branchDetails.setCellAlignment(cellAlignChallan);
							branchDetails.setBorder(true);
							branchDetails.setHeaderBGColor(new Color(225, 225,
									225));
							branchDetails.setBodyFontDetails(Font.HELVETICA, 8,
									Font.NORMAL, new Color(0, 0, 0));
							branchDetails.setBlankRowsBelow(1);
							rg.addTableToDoc(branchDetails);
						} else {
							TableDataSet noData = new TableDataSet();
							Object[][] noDataObj = new Object[1][1];
							noDataObj[0][0] = "No records available";
							// noData.setHeader(columns);
							noData.setData(noDataObj);
							noData.setCellAlignment(new int[] { 1 });
							noData.setCellWidth(new int[] { 100 });
							noData.setBodyFontDetails(Font.HELVETICA, 10,
									Font.NORMAL, new Color(0, 0, 0));
							noData.setBorder(true);
							noData.setBlankRowsBelow(1);
							rg.addTableToDoc(noData);
						}
					} catch (Exception e) {
						logger
								.error("exception in challanDataMarDec object",
										e);
					} // end of catch
					// =============Branch details header===================//
					Object[][] branchNameDtl = new Object[1][5];// new------------->

					// =====================Branch details==================//

					count++;
				}

			} else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				// noData.setHeader(columns);
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10,
						Font.NORMAL, new Color(0, 0, 0));
				noData.setBorder(true);
				noData.setBlankRowsBelow(1);
				rg.addTableToDoc(noData);
			}

			rg.process();
			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getTemplateDetails(BankMaster bean, String requestID) {
		try {
			String query = " SELECT  BANK_ID,STATEMENT_IFSC_CODE,STATEMENT_CR_DR, STATEMENT_ACCOUNT, STATEMENT_AMOUNT, " 
				+ " STATEMENT_EMP_CODE, STATEMENT_NAME, STATEMENT_NARRATION, STATEMENT_SOL_ID, " 
				+ " STATEMENT_TRANS_DESC, STATEMENT_TRANS_PART, STATEMENT_TITLE, STATEMENT_CURRENCY, COVERING_LETTER_TEMPLATE, NVL(TEMPLATE_NAME,' '), STATEMENT_DR_ACCOUNT "
				+ " FROM HRMS_BANK "
				+ " LEFT JOIN HRMS_LETTERTEMPLATE_HDR ON (HRMS_LETTERTEMPLATE_HDR.TEMPLATE_ID = HRMS_BANK.COVERING_LETTER_TEMPLATE)"
				+ " WHERE BANK_ID =  " + requestID;

			Object[][] data = getSqlModel().getSingleResult(query);

			try {
				bean.setHiddencode(checkNull(String.valueOf(data[0][0])));
				bean.setStatementIfscCode(checkNull(String.valueOf(data[0][1])));
				bean.setStatementCrDr(checkNull(String.valueOf(data[0][2])));
				bean.setStatementAccount(checkNull(String.valueOf(data[0][3])));
				bean.setStatementAmount(checkNull(String.valueOf(data[0][4])));
				bean.setStatementEmpCode(checkNull(String.valueOf(data[0][5])));
				bean.setStatementName(checkNull(String.valueOf(data[0][6])));
				
				bean.setStatementNarration(checkNull(String.valueOf(data[0][7])));
				bean.setStatementSolId(checkNull(String.valueOf(data[0][8])));
				bean.setStatementTransDesc(checkNull(String.valueOf(data[0][9])));
				bean.setStatementTransPart(checkNull(String.valueOf(data[0][10])));
				bean.setStatementTitle(checkNull(String.valueOf(data[0][11])));
				bean.setStatementCurrency(checkNull(String.valueOf(data[0][12])));
				bean.setTemplateCode(checkNull(String.valueOf(data[0][13])));
				bean.setTemplateName(checkNull(String.valueOf(data[0][14])));
				bean.setStatementDrAccount(checkNull(String.valueOf(data[0][15])));
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			System.out.println("Exception in getRecord -- " + e);
		}
	}

	public boolean updateBankStmt(BankMaster bean) {
		boolean result = false;
		try {
		
			Object updateObj[][] = new Object[1][15];
			updateObj[0][0] = bean.getStatementTitle();
			updateObj[0][1] = bean.getStatementIfscCode();
			updateObj[0][2] = bean.getStatementCrDr();
			updateObj[0][3] = bean.getStatementAccount();
			updateObj[0][4] = bean.getStatementAmount();
			updateObj[0][5] = bean.getStatementEmpCode();
			updateObj[0][6] = bean.getStatementName();
			updateObj[0][7] = bean.getStatementNarration();
			updateObj[0][8] = bean.getStatementSolId();
			updateObj[0][9] = bean.getStatementTransDesc();
			updateObj[0][10] = bean.getStatementTransPart();
			updateObj[0][11] = bean.getStatementCurrency();
			updateObj[0][12] = bean.getTemplateCode();
			updateObj[0][13] = bean.getStatementDrAccount();
			updateObj[0][14] = bean.getHiddencode();
			
			String updateQuery = "UPDATE HRMS_BANK SET "
				+ " STATEMENT_TITLE = ? , STATEMENT_IFSC_CODE = ? , STATEMENT_CR_DR = ? ,  STATEMENT_ACCOUNT = ? , "
				+ "STATEMENT_AMOUNT = ? , STATEMENT_EMP_CODE = ? ,STATEMENT_NAME = ? ,  STATEMENT_NARRATION = ? ,STATEMENT_SOL_ID = ?  ,"
				+ "STATEMENT_TRANS_DESC = ? , STATEMENT_TRANS_PART = ? ,STATEMENT_CURRENCY = ?, COVERING_LETTER_TEMPLATE=?, STATEMENT_DR_ACCOUNT=? WHERE BANK_ID = ?";
				

			result = getSqlModel().singleExecute(updateQuery, updateObj);

			/*for(int i = 0; i < updateObj.length; i++) {
				for(int j = 0; j < updateObj[i].length; j++) {
					logger.info("updateObj[" + i + "][" + j + "]  " + updateObj[i][j]);
				}
			}*/

		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;

	}
}