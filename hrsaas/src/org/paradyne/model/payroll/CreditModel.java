/**
 * 
 */
package org.paradyne.model.payroll;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.admin.master.EmpTypeMaster;
import org.paradyne.bean.payroll.CreditMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.CrystalReport;

import com.itextpdf.text.BaseColor;

/**
 * @author MuzaffarS
 * Modified by GTL-AA1712
 */
public class CreditModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CreditModel.class);

	CreditMaster creditMaster = null;

	public String addCredit(CreditMaster bean, String path) {
		Object addObj[][] = new Object[1][17];
		logger.info("the credit pay flag" + bean.getCreditpayflag());
		addObj[0][0] = bean.getCreditName().trim();
		addObj[0][1] = bean.getCreditAbbr().trim();
		addObj[0][2] = bean.getCreditType();
		addObj[0][3] = bean.getCreditpayflag();
		addObj[0][4] = bean.getAppArrears();
		//addObj[0][5] = bean.getCreditFor();
		addObj[0][5] = bean.getCalculatedCreditFormulaValue();
		addObj[0][6] = bean.getTaxable();
		addObj[0][7] = bean.getCrePeriod();
		addObj[0][8] = bean.getCreditPrior();
		addObj[0][9] = bean.getProTax();
		addObj[0][10] = bean.getEsic();

		addObj[0][11] = bean.getCreditexempt().trim();
		addObj[0][12] = bean.getTaxCode().trim();

		// ADDED BY REEBA
		//addObj[0][13] = bean.getCreditReimbursement().trim();
		// ADDED BY DHANASHREE 
		addObj[0][13] = bean.getCreditHeadType().trim();
		addObj[0][14] = bean.getCreditIsCTCComponent().trim();
		// ADDED BY Vijay
		addObj[0][15] = bean.getLwf().trim();
		addObj[0][16] = bean.getCreditmaxcap().trim();
		/*logger.info("into model");
		logger.info("obj value...!" + bean.getCreditFor() + "hhh"
				+ bean.getTaxable() + "Credittype" + bean.getCreditType());*/

		String recordQur = "SELECT Nvl(MAX(CREDIT_CODE),0)+1 FROM HRMS_CREDIT_HEAD";
		Object record[][] = getSqlModel().getSingleResult(recordQur);

		boolean result = getSqlModel().singleExecute(getQuery(1), addObj);
		if (result) {
			xml_credithead(path);
			bean.setCreditCode(String.valueOf(record[0][0]));
			return "saved";

		} else {
			return "not saved";

		}
	}

	public String modCredit(CreditMaster bean, String path) {
		Object addObj[][] = new Object[1][18];

		addObj[0][0] = bean.getCreditName().trim();
		addObj[0][1] = bean.getCreditAbbr().trim();
		addObj[0][2] = bean.getCreditType();
		addObj[0][3] = bean.getCreditpayflag();
		addObj[0][4] = bean.getAppArrears();
		//addObj[0][5] = bean.getCreditFor();
		addObj[0][5] = bean.getCalculatedCreditFormulaValue();
		addObj[0][6] = bean.getTaxable();
		addObj[0][7] = bean.getCrePeriod();
		addObj[0][8] = bean.getCreditPrior();
		logger.info("priority - " + addObj[0][8] + "  bean -- "
				+ bean.getCreditPrior());
		addObj[0][9] = bean.getProTax();
		addObj[0][10] = bean.getEsic();

		addObj[0][11] = bean.getCreditexempt().trim();
		addObj[0][12] = bean.getTaxCode().trim();

		// ADDED BY REEBA
		//addObj[0][13] = bean.getCreditReimbursement();

		

		// ADDED BY DHANASHREE 
		addObj[0][13] = bean.getCreditHeadType().trim();
		addObj[0][14] = bean.getCreditIsCTCComponent().trim();
		
		// ADDED BY Vijay
		addObj[0][15] = bean.getLwf().trim();
		addObj[0][16] = bean.getCreditmaxcap();
		addObj[0][17] = bean.getCreditCode();

		
		/*for (int i = 0; i < 12; i++) {
			logger.info("obj values... i =  " + i + " -  " + addObj[0][i]);

		}*/
		boolean result = getSqlModel().singleExecute(getQuery(2), addObj);
		if (result) {
			xml_credithead(path);
			return "modified";

		} else {
			return "not modified";

		}
	}

	public String deleteCredit(CreditMaster bean, String poolDir) {
		Object addObj[][] = new Object[1][1];

		addObj[0][0] = bean.getCreditCode();
		boolean result = getSqlModel().singleExecute(getQuery(3), addObj);
		if (result) {
			xml_credithead(poolDir);
			return "deleted";

		} else {
			return "not deleted";

		}

	}

	public void getCreditRecord(CreditMaster bean) {
		Object addObj[] = new Object[1];

		addObj[0] = bean.getCreditCode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);

		for (int i = 0; i < data.length; i++) {
			bean.setCreditCode(String.valueOf(data[i][0]));
			bean.setCreditName(String.valueOf(data[i][1]));
			bean.setCreditAbbr(String.valueOf(data[i][2]));
			bean.setCreditType(checkNull(String.valueOf(data[i][3])));
			bean.setCreditpayflag(checkNull(String.valueOf(data[i][4])));
			bean.setAppArrears(checkNull(String.valueOf(data[i][5])));
			//bean.setCreditFor(checkNull(String.valueOf(data[i][6])));
			bean.setCalculatedCreditFormulaValue(checkNull(String.valueOf(data[i][6])));
			bean.setTaxable(checkNull(String.valueOf(data[i][7])));
			bean.setCrePeriod(String.valueOf(data[i][8]));
			bean.setCreditPrior(checkNull(String.valueOf(data[i][9])));
			bean.setProTax(String.valueOf(data[i][10]));
			bean.setEsic(checkNull(String.valueOf(data[i][11])));

			bean.setCreditexempt(checkNull(String.valueOf(data[i][12])));
			bean.setTaxCode(checkNull(String.valueOf(data[i][13])));
			bean.setExemptSectionNo(checkNull(String.valueOf(data[i][14])));
			// ADDED BY REEBA
			bean.setCreditReimbursement(checkNull(String.valueOf(data[i][15])));
			// ADDED BY DHANASHREE 
			bean.setCreditHeadType(checkNull(String.valueOf(data[i][16])));
			bean.setCreditIsCTCComponent(checkNull(String.valueOf(data[i][17])));
			// ADDED BY vijay
			bean.setLwf(checkNull(String.valueOf(data[i][18])));
			bean.setCreditmaxcap(checkNull(String.valueOf(data[i][19])));
		}

	}

	public void getCreditReport(CreditMaster bean) {
		ArrayList<Object> dispList = new ArrayList<Object>();

		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		for (int i = 0; i < data.length; i++) {
			CreditMaster bean1 = new CreditMaster();
			bean1.setCreditCode(String.valueOf(data[i][0]));
			bean1.setCreditName(String.valueOf(data[i][1]));
			bean1.setCreditAbbr(String.valueOf(data[i][2]));
			bean1.setCreditType(String.valueOf(data[i][3]));
			bean1.setCreditmaxcap(String.valueOf(data[i][4]));
			bean1.setCreditminimumfloor(String.valueOf(data[i][5]));
			bean1.setCreditpolicy(String.valueOf(data[i][6]));
			bean1.setCreditpayflag(String.valueOf(data[i][7]));
			dispList.add(bean1);
		}
		bean.setCreditArray(dispList);
	}

	public void getReport(CreditMaster creditMaster2,
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context) {
		// TODO Auto-generated method stub
		CrystalReport cr = new CrystalReport();
		String path = "org/paradyne/rpt/payroll/credit.rpt ";
		cr.createReport(request, response, context, session, path, "");

	}

	public void Data(CreditMaster bean, HttpServletRequest request) {

		Object[][] obj = getSqlModel().getSingleResult(getQuery(7));

		// Object repData[][] = getSqlModel().getSingleResult(query);
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

			// for (int i = 0; i < obj.length; i++) {
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				CreditMaster bean1 = new CreditMaster();

				bean1.setCreditCode(String.valueOf(obj[i][0]));
				bean1.setCreditName(String.valueOf(obj[i][1]));
				bean1.setCreditAbbr(String.valueOf(obj[i][2]));
				//bean1.setCreditFor(checkNull(String.valueOf(obj[i][3])));
				bean1.setCalculatedCreditFormulaValue(checkNull(String.valueOf(obj[i][3])));
				bean1.setCreditType(checkNull(String.valueOf(obj[i][4])));
				bean1.setCreditpayflag(checkNull(String.valueOf(obj[i][5])));
				bean1.setAppArrears(checkNull(String.valueOf(obj[i][6])));
				bean1.setTaxable(checkNull(String.valueOf(obj[i][7])));
				bean1.setCrePeriod(checkNull(String.valueOf(obj[i][8])));
				bean1.setCreditPrior(checkNull(String.valueOf(obj[i][9])));
				bean1.setProTax(checkNull(String.valueOf(obj[i][10])));
				bean1.setEsic(checkNull(String.valueOf(obj[i][11])));
				// ADDED BY REEBA
				bean1.setCreditReimbItt(checkNull(String.valueOf(obj[i][12])));
				// ADDED BY vijay
				bean1.setLwf(checkNull(String.valueOf(obj[i][13])));
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
		// logger.info("length --> "+obj.length);
		bean.setIteratorlist(list);

	}

	public void calforedit(CreditMaster bean) {

		String query = " SELECT CREDIT_CODE,NVL(CREDIT_NAME,''),NVL(CREDIT_ABBR,''),CREDIT_TYPE, "
				+ " CREDIT_PAYFLAG,NVL(CREDIT_APPLICABLE_ARREARS,'N'), "
				+ " NVL(CREDIT_FORMULA,' '),NVL(CREDIT_TAXABLE_FLAG,'N'),CREDIT_PERIODICITY,CREDIT_PRIORITY,"
				+ " NVL(CREDIT_APPLICABLE_PTAX,'N'),NVL(CREDIT_APPLICABLE_ESI,' N'),NVL(CREDIT_ISEXEMPT,'N'),CREDIT_EXEMPT_INV_CODE ,HRMS_TAX_INVESTMENT.INV_NAME "
				+ " ,'', CREDIT_HEAD_TYPE, CREDIT_IS_CTC_COMPONENT,NVL(CREDIT_APPLICABLE_LWF,'N'),NVL(CREDIT_MAXCAP,0) FROM HRMS_CREDIT_HEAD  "
				+ " LEFT JOIN HRMS_TAX_INVESTMENT ON(HRMS_CREDIT_HEAD.CREDIT_EXEMPT_INV_CODE=HRMS_TAX_INVESTMENT.INV_CODE) "
				+ " WHERE CREDIT_CODE= " + bean.getHiddencode();

		Object[][] data = getSqlModel().getSingleResult(query);

		bean.setCreditCode(String.valueOf(data[0][0]));
		bean.setCreditName(String.valueOf(data[0][1]).trim());
		bean.setCreditAbbr(String.valueOf(data[0][2]).trim());
		bean.setCreditType(String.valueOf(data[0][3]));
		bean.setCreditpayflag(String.valueOf(data[0][4]));
		bean.setAppArrears(String.valueOf(data[0][5]));
		//bean.setCreditFor(checkNull(String.valueOf(data[0][6])));
		bean.setCalculatedCreditFormulaValue(checkNull(String.valueOf(data[0][6])));
		bean.setTaxable(String.valueOf(data[0][7]));
		bean.setCrePeriod(checkNull(String.valueOf(data[0][8])));
		bean.setCreditPrior(checkNull(String.valueOf(data[0][9])));
		bean.setProTax(String.valueOf(data[0][10]));
		bean.setEsic(String.valueOf(data[0][11]));

		bean.setCreditexempt(checkNull(String.valueOf(data[0][12])));
		bean.setTaxCode(checkNull(String.valueOf(data[0][13])));
		bean.setExemptSectionNo(checkNull(String.valueOf(data[0][14])));

		// ADDED BY REEBA
		bean.setCreditReimbursement(checkNull(String.valueOf(data[0][15])));
		//ADDED BY dhanashree
		bean.setCreditHeadType(checkNull(String.valueOf(data[0][16])));
        bean.setCreditIsCTCComponent(checkNull(String.valueOf(data[0][17])));
        //ADDED BY vijay
        bean.setLwf(checkNull(String.valueOf(data[0][18])));
        bean.setCreditmaxcap(checkNull(String.valueOf(data[0][19])));
	}

	public boolean calfordelete(CreditMaster bean) {

		Object[][] delete = new Object[1][1];
		delete[0][0] = bean.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);

	}

	public boolean deletecheckedRecords(CreditMaster bean, String[] code,
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
			xml_credithead(poolDir);
		}
		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/** WRITING IN XML * */
	/** @author reebaj * */
	public void xml_credithead(String path) {

		if (!(path == null || path.equals("") || path.equals(null)))
			path = "/" + path;

		try {
			new XMLReaderWriter().write(buildCredithead("PAYROLL",
					"CREDIT HEAD"), path);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Document buildCredithead(String head, String subhead) {
		String query1 = "  SELECT CREDIT_CODE, CREDIT_ABBR "
				+ " FROM HRMS_CREDIT_HEAD "
				+ " WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' "
				+ " ORDER BY CREDIT_CODE ";
		Object data[][] = getSqlModel().getSingleResult(query1);
		Document document = DocumentHelper.createDocument();
		logger.info("data.length" + data.length);
		Element header;
		Element element;
		Element root = document.addElement(head);
		if (data != null && data.length > 0) {
			header = root.addElement("CREDIT").addAttribute("name", subhead);
			for (int i = 0; i < data.length; i++) {
				element = header.addElement("CREDIT").addAttribute("Code",
						String.valueOf(data[i][0])).addAttribute(
						"Abbreviation", String.valueOf(data[i][1]));
			}
		} else {
			logger.info("NO CREDIT HEAD");
			header = root.addElement("CREDIT").addAttribute("name", subhead);
			element = header.addElement("CREDIT").addAttribute("Code",
					String.valueOf("code")).addAttribute("Abbreviation",
					String.valueOf("abbr"));

		}
		return document;
	}
	
//Added by Tinshuk Banafar....Begin....
	
	public void getCreditReport(CreditMaster creditMast, HttpServletRequest request,
			HttpServletResponse response, String reportPath) {

		ReportDataSet rds = new ReportDataSet();
		String type = creditMast.getReport(); // Pdf/Xls/Doc
		rds.setReportType(type);
		String fileName = "Credit Master Details " + Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Credit Master Details ");
		rds.setShowPageNo(true);
		rds.setGeneratedByText(creditMast.getUserEmpId());
		rds.setUserEmpId(creditMast.getUserEmpId());
		
		rds.setPageSize("TABLOID");
		rds.setPageOrientation("landscape");
		rds.setShowPageNo(true);
		rds.setTotalColumns(13);
		
		// Report Generator Starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;
		
		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
		}
		else{
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
			request.setAttribute("reportPath", reportPath+fileName+"."+type);
			request.setAttribute("fileName", fileName + "." + type);
			request.setAttribute("action", "/payroll/CreditMaster_input.action");
			// Initial Page Action name
			}

		
		String query = " SELECT rownum,HRMS_CREDIT_HEAD.CREDIT_NAME,HRMS_CREDIT_HEAD.CREDIT_ABBR,"
		+" DECODE(HRMS_CREDIT_HEAD.CREDIT_PAYFLAG, 'Y','YES','N','NO'),"
		+" DECODE(HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ARREARS, 'Y','YES','N','NO')," 
		+" DECODE(HRMS_CREDIT_HEAD.CREDIT_TAXABLE_FLAG, 'Y','YES','N','NO'),"
		+" DECODE(HRMS_CREDIT_HEAD.CREDIT_PERIODICITY, 'A','Annually','M','Monthly','Q','Quarterly','H','Half Yearly'),"
		+" HRMS_CREDIT_HEAD.CREDIT_PRIORITY,"
		+" NVL(DECODE(HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_PTAX, 'Y','YES','N','NO'),'NO'),"
		+" NVL(DECODE(HRMS_CREDIT_HEAD.CREDIT_APPLICABLE_ESI, 'Y','YES','N','NO'),'NO'),"
		+" NVL(DECODE(HRMS_CREDIT_HEAD.CREDIT_ISEXEMPT, 'Y','YES','N','NO'),'NO'),HRMS_TAX_INVESTMENT.INV_NAME"
		+" FROM HRMS_CREDIT_HEAD" 
		+" LEFT JOIN HRMS_TAX_INVESTMENT ON (HRMS_TAX_INVESTMENT.INV_CODE = HRMS_CREDIT_HEAD.CREDIT_EXEMPT_INV_CODE)";
		
		query +=" order by rownum,HRMS_CREDIT_HEAD.CREDIT_NAME ";
		Object[][] queryData = getSqlModel().getSingleResult(query);
		//Defining Tabular Structure and data //NVL(CREDIT_ISEXEMPT,'N')
		TableDataSet tdstable = new TableDataSet();
				
		tdstable.setHeader(new String[] {"Sr.No.","Credit Name","Credit Abbreviation","Including Salary", "Applicable for Arrears","Applicable for Income Tax","Credit Period","Credit Priority","Applicable for Professional Tax","Applicable for ESIC","Credit Exempted in Tax","Exempted Under Section"});// defining headers
		
		
		tdstable.setCellAlignment(new int[]{0,0,0,0,0,0,0,0,0,0,0,0});
		tdstable.setCellWidth(new int[]{5,18,7,10,10,10,8,7,7,5,5,8});
		//tdstable.setCellColSpan(new int[] { 13 });
		tdstable.setHeaderTable(true);
		tdstable.setHeaderBorderDetail(3);
		tdstable.setData(queryData);// data object from query
		tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
		tdstable.setBorderDetail(3);
		rg.addTableToDoc(tdstable);
		
			
		int totalRecords=queryData.length;
		TableDataSet totalEmp = new TableDataSet();
		totalEmp.setData(new Object[][] { { "Total Records : "
				+ totalRecords } });
		totalEmp.setCellAlignment(new int[] { 0 });
		totalEmp.setCellWidth(new int[] { 100 });
		totalEmp.setBorderDetail(0);
		totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
		totalEmp.setBodyFontStyle(1);
		totalEmp.setBlankRowsAbove(1);
		rg.addTableToDoc(totalEmp);

		rg.process();
		
		
		
		//rg.process();
		
		if(reportPath.equals("")){
		rg.createReport(response);
		}
		else{
		/* Generates the report as attachment*/
		rg.saveReport(response);
		}
			
	}
	//Added by Tinshuk Banafar....End.....

}
