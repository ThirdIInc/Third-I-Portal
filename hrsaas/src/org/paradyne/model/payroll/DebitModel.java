/**
 * 
 */
package org.paradyne.model.payroll;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.admin.master.TradeMaster;
import org.paradyne.bean.payroll.DebitMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.CrystalReport;

import com.itextpdf.text.BaseColor;

/**
 * @author MuzaffarS
 * 
 */
public class DebitModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(DebitModel.class);

	
	/**
	 * Added by Nilesh D for duplicate check during Add Record.
	 */
	public boolean addDebit(DebitMaster bean) {
		
		if (!this.checkDuplicateAdd(bean)) {
			
			if(!this.checkDuplicatePriority(bean)){	
		
		Object addObj[][] = new Object[1][11];

		addObj[0][0] = bean.getDebitName().trim();
		addObj[0][1] = bean.getDebitAbbr().trim();
		addObj[0][2] = bean.getDebitexempt().trim();
		//addObj[0][3] = bean.getDebitBalFlag().trim();
		//addObj[0][4] = bean.getDebitforLoan().trim();
		addObj[0][3] = bean.getDebitPriority().trim();
		addObj[0][4] = bean.getTaxCode().trim();
		addObj[0][5] = bean.getTableRecover().trim();
		addObj[0][6] = bean.getDebitPayFlag().trim();
		addObj[0][7] = bean.getAppArrears().trim();
		addObj[0][8] = bean.getDebitRound().trim();
		addObj[0][9] = bean.getDebitType().trim();
		
		if(bean.getDebitType().trim().equals("FX")||bean.getDebitType().trim().equals("SD")){
			addObj[0][10] = bean.getDebitFixedAmount();
		}else if(bean.getDebitType().trim().equals("FR")){
			addObj[0][10] = bean.getDebitFormula();
		}else{
			addObj[0][10] = "";
		}

		return  getSqlModel().singleExecute(getQuery(1), addObj);

		
		}	else {

			return false;
		}
		
		}	else {

			return false;
		}
		
		
	}

	/**
	 * Method : checkDuplicateAdd().
	 * Purpose : Used to Check the Duplicate Debit Name & Debit Abbrivation.
	 * @param bean - Used to get Debit Name & Abbrivation.
	 * @return result either true or false.
	 */
	public boolean checkDuplicateAdd(DebitMaster bean) {
		boolean result = false;
		 String query = "SELECT * FROM HRMS_DEBIT_HEAD " 
			 	+ " WHERE HRMS_DEBIT_HEAD.DEBIT_NAME = ?" 
			 	//+ bean.getDebitName() 
			 	+ " AND HRMS_DEBIT_HEAD.DEBIT_ABBR = ?" 
			 	//+ bean.getDebitAbbr() 
			 	+ " AND HRMS_DEBIT_HEAD.DEBIT_PRIORITY = ?"; 
			 	//+ bean.getDebitPriority();
		 
		Object[] parameterObj = new Object[3];
		parameterObj[0] = bean.getDebitName();
		parameterObj[1] = bean.getDebitAbbr();
		parameterObj[2] = bean.getDebitPriority();
			
		 Object[][] data = this.getSqlModel().getSingleResult(query,parameterObj);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}
	
	/**
	 * Method : checkDuplicatePriority().
	 * Purpose : Used to Check the Duplicate Priority.
	 * @param bean - Used to get Debit Priority.
	 * @return result either true or false.
	 */
	public boolean checkDuplicatePriority(DebitMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_DEBIT_HEAD WHERE HRMS_DEBIT_HEAD.DEBIT_PRIORITY = ?"; //+ bean.getDebitPriority();
		Object[] parameterObj = new Object[1];
		parameterObj[0] = bean.getDebitPriority();
			
		 Object[][] data = this.getSqlModel().getSingleResult(query,parameterObj);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
		
	}
	
	
	
	/**
	 * Added by Nilesh D for duplicate check during modify.
	 */
	public boolean modDebit(DebitMaster bean) {

		if (!this.checkDuplicateUpdate(bean)) {

			if (!this.checkDuplicatePriorityUpdate(bean)) {

				Object addObj[][] = new Object[1][12];

				addObj[0][0] = bean.getDebitName().trim();
				addObj[0][1] = bean.getDebitAbbr().trim();
				addObj[0][2] = bean.getDebitexempt().trim();
				// addObj[0][3] = bean.getDebitBalFlag().trim();
				// addObj[0][4] = bean.getDebitforLoan().trim();
				addObj[0][3] = bean.getDebitPriority().trim();
				addObj[0][4] = bean.getTaxCode().trim();
				addObj[0][5] = bean.getTableRecover().trim();
				addObj[0][6] = bean.getDebitPayFlag().trim();
				addObj[0][7] = bean.getAppArrears().trim();
				addObj[0][8] = bean.getDebitRound();

				addObj[0][9] = bean.getDebitType().trim();

				if (bean.getDebitType().trim().equals("FX")	|| bean.getDebitType().trim().equals("SD")) {
					addObj[0][10] = bean.getDebitFixedAmount();
				} else if (bean.getDebitType().trim().equals("FR")) {
					addObj[0][10] = bean.getDebitFormula();
				} else {
					addObj[0][10] = "";
				}
				addObj[0][11] = bean.getDebitCode();
				// if(bean.getDebitexempt().equals(String.valueOf("N")) )
				// {
				// addObj[0][6] =0;
				// logger.info(bean.getDebitexempt()+"*****************************************");
				// }
				/*
				 * else { addObj[0][6] =0; }
				 */
				return getSqlModel().singleExecute(getQuery(2), addObj);

			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * Method : checkDuplicateUpdate().
	 * Purpose : Used to Check the Duplicate Debit Name & Debit Abbrivation.
	 * @param bean - Used to get Debit Name & Abbrivation.
	 * @return result either true or false.
	 */
	public boolean checkDuplicateUpdate(DebitMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_DEBIT_HEAD WHERE HRMS_DEBIT_HEAD.DEBIT_NAME = ?"
				// + bean.getDebitName()
				+ " AND HRMS_DEBIT_HEAD.DEBIT_ABBR = ?"
				// + bean.getDebitAbbr()
				+ " AND HRMS_DEBIT_HEAD.DEBIT_PRIORITY = ?";
				// + bean.getDebitPriority();

		Object[] parameterObj = new Object[3];
		parameterObj[0] = bean.getDebitName();
		parameterObj[1] = bean.getDebitAbbr();
		parameterObj[2] = bean.getDebitPriority();

		Object[][] data = this.getSqlModel().getSingleResult(query,	parameterObj);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}
	
/**
 * Method : checkDuplicatePriorityUpdate().
 * Purpose : Used to Check the Duplicate Priority.
 * @param bean - Used to get Debit Priority.
 * @return result either true or false.
 */
	public boolean checkDuplicatePriorityUpdate(DebitMaster bean) {

		boolean result = false;
		String query = "SELECT * FROM HRMS_DEBIT_HEAD WHERE HRMS_DEBIT_HEAD.DEBIT_PRIORITY = ?"; // + bean.getDebitPriority();

		Object[] parameterObj = new Object[1];
		parameterObj[0] = bean.getDebitPriority();

		Object[][] data = this.getSqlModel().getSingleResult(query, parameterObj );
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}
	
	
	public String deleteDebit(DebitMaster bean, String poolDir) {
		Object addObj[][] = new Object[1][1];

		addObj[0][0] = bean.getDebitCode();
		boolean result = getSqlModel().singleExecute(getQuery(3), addObj);
		if (result) {
			xml_debithead(poolDir);
			return "deleted";

		} else {
			return "not deleted";

		}

	}

	public void getDebitReport(DebitMaster bean) {
		Object addObj[] = new Object[1];
		ArrayList<Object> dispList = new ArrayList();

		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		for (int i = 0; i < data.length; i++) {
			DebitMaster bean1 = new DebitMaster();
			logger.info("I am 2");
			bean1.setDebitCode(String.valueOf(data[i][0]));
			bean1.setDebitName(String.valueOf(data[i][1]));
			bean1.setDebitAbbr(checkNull(String.valueOf(data[i][2])));
			bean1.setDebitexempt(checkNull(String.valueOf(data[i][3])));
			//bean1.setDebitBalFlag(checkNull(String.valueOf(data[i][4])));
			//bean1.setDebitforLoan(checkNull(String.valueOf(data[i][5])));
			bean1.setDebitPriority(checkNull(String.valueOf(data[i][4])));
			bean1.setExemptSectionNo(checkNull(String.valueOf(data[i][5])));
			bean1.setTableRecover(checkNull(String.valueOf(data[i][6])));
			/*
			 * bean1.setDebitType(String.valueOf(data[i][3]));
			 * bean1.setDebitmaxcap(String.valueOf(data[i][4]));
			 * bean1.setDebitminimumfloor(String.valueOf(data[i][5]));
			 * bean1.setDebitpolicy(String.valueOf(data[i][6]));
			 * bean1.setDebitpayflag(String.valueOf(data[i][7]));
			 */
			logger.info("value of setExemptSectionNo================"
					+ String.valueOf(data[i][5]));
			logger.info("value of setTableRecover================"
					+ String.valueOf(data[i][6]));
			dispList.add(bean1);
		}
		bean.setDebitArray(dispList);

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void getDebitRecord(DebitMaster bean) {
		Object addObj[] = new Object[1];
		logger.info("the debit code is" + bean.getDebitCode());
		addObj[0] = bean.getDebitCode();
		/*
		 * String cd= (String.valueOf( bean.getDebitCode())); String obj
		 * =cd.substring(addObj.length-1, addObj.length);
		 * logger.info("the *************** code is"+obj);
		 */
		addObj[0] = addObj[0];
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);

		for (int i = 0; i < data.length; i++) {
			logger.info("I am 2");
			bean.setDebitCode(String.valueOf(data[i][0]));
			bean.setDebitName(String.valueOf(data[i][1]));
			bean.setDebitAbbr(checkNull(String.valueOf(data[i][2])));
			bean.setDebitexempt(checkNull(String.valueOf(data[i][3])));
			//bean.setDebitBalFlag(checkNull(String.valueOf(data[i][4])));
			//bean.setDebitforLoan(checkNull(String.valueOf(data[i][5])));
			bean.setDebitPriority(checkNull(String.valueOf(data[i][4])));

			if (String.valueOf(data[i][5]).equals("null")) {
				bean.setExemptSectionNo("");

			} else {
				bean.setExemptSectionNo(String.valueOf(data[i][5]));
			}
			bean.setTableRecover(checkNull(String.valueOf(data[i][6])));
			bean.setDebitPayFlag(checkNull(String.valueOf(data[i][7])));
			bean.setAppArrears(checkNull(String.valueOf(data[i][8])));
			bean.setTaxCode(checkNull(String.valueOf(data[i][9])));
			bean.setDebitRound(checkNull(String.valueOf(data[i][10])));
			
			bean.setDebitType(checkNull(String.valueOf(data[i][11])));
			if(bean.getDebitType().equals("FX")||bean.getDebitType().equals("SD") ){
				bean.setDebitFixedAmount(checkNull(String.valueOf(data[i][12])));
			}else if(bean.getDebitType().equals("FR")){
				bean.setDebitFormula(checkNull(String.valueOf(data[i][12])));
			}else{
				bean.setDebitFormula("");
				bean.setDebitFixedAmount("");
			}
		}

	}

	public void getReport(DebitMaster debitMaster, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			HttpSession session) {
		// TODO Auto-generated method stub

		CrystalReport cr = new CrystalReport();
		String path = "org/paradyne/rpt/payroll/debit.rpt ";
		cr.createReport(request, response, context, session, path, "");
	}

	public void Data(DebitMaster bean, HttpServletRequest request) {

		Object obj [][]  = getSqlModel().getSingleResult(getQuery(7));
		//Object repData[][] = getSqlModel().getSingleResult(query);
		String[] pageIndex = Utility.doPaging(bean.getMyPage(), obj.length, 20);
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

		ArrayList<Object> list = new ArrayList<Object>();
		if (obj != null && obj.length > 0) {

			//	for (int i = 0; i < obj.length; i++) {
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				DebitMaster bean1 = new DebitMaster();
				bean1.setDebitCode(String.valueOf(obj[i][0]));
				bean1.setDebitName(String.valueOf(obj[i][1]));
				bean1.setDebitAbbr(checkNull(String.valueOf(obj[i][2])));

				bean1.setDebitexempt(checkNull(String.valueOf(obj[i][3])));
				//bean1.setDebitBalFlag(checkNull(String.valueOf(obj[i][4])));
				//bean1.setDebitforLoan(checkNull(String.valueOf(obj[i][5])));
				bean1.setDebitPriority(checkNull(String.valueOf(obj[i][4])));
				bean1.setExemptSectionNo(String.valueOf(obj[i][5]));
				bean1.setTableRecover(checkNull(String.valueOf(obj[i][6])));
				bean1.setDebitPayFlag(checkNull(String.valueOf(obj[i][7])));

				list.add(bean1);
			}

			bean.setTotalRecords("" + obj.length);
		} else {
			bean.setListLength("false");
		}

		if (list.size() > 0)
			bean.setListLength("true");
		else
			bean.setListLength("false");
		//logger.info("length --> "+obj.length);
		bean.setIteratorlist(list);

	}

	public void calforedit2(DebitMaster bean) {

		String query = " SELECT HRMS_DEBIT_HEAD.DEBIT_CODE, HRMS_DEBIT_HEAD.DEBIT_NAME," 
				+ " NVL(HRMS_DEBIT_HEAD.DEBIT_ABBR,''), NVL(HRMS_DEBIT_HEAD.DEBIT_ISEXEMPT,' '), "
				+ " NVL(HRMS_DEBIT_HEAD.DEBIT_BALANCE_FLAG,''), NVL(HRMS_DEBIT_HEAD.DEBIT_FOR_LOAN,''), "
				+ " NVL(HRMS_DEBIT_HEAD.DEBIT_PRIORITY,'0'), HRMS_TAX_INVESTMENT.INV_NAME,"
				+ " HRMS_DEBIT_HEAD.DEBIT_PERIODICITY, HRMS_DEBIT_HEAD.DEBIT_PAYFLAG," 
				+ " HRMS_DEBIT_HEAD.DEBIT_APPLICABLE_ARREARS ,HRMS_TAX_INVESTMENT.INV_CODE," 
				+ " HRMS_DEBIT_HEAD.DEBIT_ROUND, NVL(HRMS_DEBIT_HEAD.DEBIT_TYPE,'SG')," 
				+ " NVL(HRMS_DEBIT_HEAD.DEBIT_FORMULA,'0')  "
				+ " FROM HRMS_DEBIT_HEAD " 
				+ " LEFT JOIN HRMS_TAX_INVESTMENT ON (HRMS_DEBIT_HEAD.DEBIT_EXEMPT_INV_CODE=HRMS_TAX_INVESTMENT.INV_CODE)" 
				+ " WHERE HRMS_DEBIT_HEAD.DEBIT_CODE= ?"; // + bean.getHiddencode();

		Object[] parameterObj = new Object[1];
		parameterObj[0] = bean.getHiddencode();
		
		Object[][] data = getSqlModel().getSingleResult(query,parameterObj);
		//setter

		bean.setDebitCode(String.valueOf(data[0][0]));
		bean.setDebitName(String.valueOf(data[0][1]).trim());
		bean.setDebitAbbr(checkNull(String.valueOf(data[0][2])).trim());
		bean.setDebitexempt(checkNull(String.valueOf(data[0][3])));
		bean.setDebitBalFlag(checkNull(String.valueOf(data[0][4])));
		bean.setDebitforLoan(checkNull(String.valueOf(data[0][5])));
		bean.setDebitPriority(checkNull(String.valueOf(data[0][6])));

		if (String.valueOf(data[0][7]).equals("null")) {
			bean.setExemptSectionNo("");

		} else {
			bean.setExemptSectionNo(String.valueOf(data[0][7]));
		}
		bean.setTableRecover(checkNull(String.valueOf(data[0][8])));
		bean.setDebitPayFlag(checkNull(String.valueOf(data[0][9])));
		bean.setAppArrears(checkNull(String.valueOf(data[0][10])));
		bean.setTaxCode(checkNull(String.valueOf(data[0][11])));
		bean.setDebitRound(checkNull(String.valueOf(data[0][12])));
		bean.setDebitType(checkNull(String.valueOf(data[0][13])));
		
		if(bean.getDebitType().equals("FX")||bean.getDebitType().equals("SD") ){
			bean.setDebitFixedAmount(checkNull(String.valueOf(data[0][14])));
		}else if(bean.getDebitType().equals("FR")){
			bean.setDebitFormula(checkNull(String.valueOf(data[0][14])));
		}else{
			bean.setDebitFormula("");
			bean.setDebitFixedAmount("");
		}
	}

	public boolean calfordelete(DebitMaster bean) {

		Object[][] delete = new Object[1][1];
		delete[0][0] = bean.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);
		// TODO Auto-generated method stub

	}

	public boolean deletecheckedRecords(DebitMaster bean, String[] code,
			String poolDir) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(3), delete);
					if (!result)
						count++;

				}
			}
		}
		if (result) {
			xml_debithead(poolDir);
		}
		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	/** WRITING IN XML **/
	/** @author reebaj **/
	public void xml_debithead(String path) {

		if (!(path == null || path.equals("") || path.equals(null)))
			path = "/" + path;

		try {
			new XMLReaderWriter().write(
					buildDebithead("PAYROLL", "DEBIT HEAD"), path);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Document buildDebithead(String head, String subhead) {
		String query1 = " SELECT HRMS_DEBIT_HEAD.DEBIT_CODE, HRMS_DEBIT_HEAD.DEBIT_ABBR "
				+ " FROM HRMS_DEBIT_HEAD "
				+ " WHERE HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND HRMS_DEBIT_HEAD.DEBIT_PAYFLAG='Y' "
				+ " ORDER BY HRMS_DEBIT_HEAD.DEBIT_PRIORITY, HRMS_DEBIT_HEAD.DEBIT_CODE ";
		Object data[][] = getSqlModel().getSingleResult(query1);
		Document document = DocumentHelper.createDocument();
		logger.info("data.length" + data.length);
		Element header;
		Element element;
		Element root = document.addElement(head);
		if (data != null && data.length > 0) {
			header = root.addElement("DEBIT").addAttribute("name", subhead);
			for (int i = 0; i < data.length; i++) {
				element = header.addElement("DEBIT").addAttribute("Code",
						String.valueOf(data[i][0])).addAttribute(
						"Abbreviation", String.valueOf(data[i][1]));
			}
		} else {
			logger.info("NO DEBIT HEAD");
			header = root.addElement("DEBIT").addAttribute("name", subhead);
			element = header.addElement("DEBIT").addAttribute("Code",
					String.valueOf("code")).addAttribute("Abbreviation",
					String.valueOf("abbr"));

		}
		return document;
	}
	//By vijay START
	public void getReport2(DebitMaster debitMaster, HttpServletRequest request,
			HttpServletResponse response, String reportPath) {

		ReportDataSet rds = new ReportDataSet();

		String type = debitMaster.getReport();// Pdf/Xls/Doc
		rds.setReportType(type);

		String fileName = "Debit_Master";
		rds.setFileName(fileName);
		rds.setReportName("Debit Master Details ");
		rds.setTotalColumns(11);
		rds.setShowPageNo(true);
		// Report Generator Starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;
		// Attachment Path Definition
		// String reportPath="";
		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
		} else {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,reportPath, session, context, request);
			request.setAttribute("reportPath", reportPath + fileName + "." + type);
			request.setAttribute("fileName", fileName + "." + type);
			request.setAttribute("action", "/payroll/DebitMaster_input.action");
			// Initial Page Action name
		}

		String query = " SELECT ROWNUM,D.DEBIT_NAME, D.DEBIT_ABBR, DECODE(D.DEBIT_TYPE,'FR','Formula','FX','Fixed','SG','System Generated','SD','As Per Salary Days')TYPE,"
				+ " D.DEBIT_PRIORITY,DECODE(D.DEBIT_ISEXEMPT,'Y','Yes','N','No')IS_EXEMPT,I.INV_NAME,DECODE(D.DEBIT_PERIODICITY,'M','Monthly','Q','Quarterly','H','Half yearly','A','Annually')PERIODICITY,DECODE(D.DEBIT_PAYFLAG,'Y','Yes','N','No')PAYFLAG,DECODE(D.DEBIT_APPLICABLE_ARREARS,'Y','Yes','N','No')ARREARS_APPLICABLE,DECODE(D.DEBIT_ROUND,'0','No Round','1','Round','2','Floor','3','Ceil','4','Round to Next 10')ROUND_TO FROM HRMS_DEBIT_HEAD D  LEFT JOIN HRMS_TAX_INVESTMENT I ON(D.DEBIT_EXEMPT_INV_CODE=I.INV_CODE) ";
		Object[][] queryData = getSqlModel().getSingleResult(query);
		// Defining Tabular Structure and data
		TableDataSet tdstable = new TableDataSet();

		tdstable.setHeader(new String[] { "Sr. No.", "Debit Name",
						"Abbreviation", "Type", "Priority", " Is Exempt",
						"Exempt Under", "Period", " Pay Flag", "Arrears",
						"Round Type" });// defining headers
		tdstable.setData(queryData);// data object from query
		// tdstable.setHeaderLines(true);
		tdstable.setBlankRowsAbove(1);
		// tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
		tdstable.setHeaderBorderDetail(3);
		tdstable.setCellAlignment(new int[] { 1, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0 });
		tdstable.setCellWidth(new int[] { 10, 10, 10, 5, 5, 10, 10, 10, 10, 10,	10 });
		tdstable.setBorderDetail(3);
		// tdstable.setHeaderTable(false);
		rg.addTableToDoc(tdstable);
		rg.process();
		if (reportPath.equals("")) {
			rg.createReport(response);
		} else {
			/* Generates the report as attachment*/
			rg.saveReport(response);
		}
	}
	//END


}
