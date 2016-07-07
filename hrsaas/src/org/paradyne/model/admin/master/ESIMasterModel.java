/**
 * 
 */
package org.paradyne.model.admin.master;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.admin.master.ESIMaster;
import org.paradyne.bean.payroll.DebitMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.CrystalReport;

import com.itextpdf.text.BaseColor;

/**
 * @author ritac
 *
 */
public class ESIMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ESIMasterModel.class);

	public String saveESI(ESIMaster esiMaster, String path) {

		Object data[][] = new Object[1][8];

		data[0][0] = esiMaster.getEsiDate();
		data[0][1] = esiMaster.getEsiGross();
		data[0][2] = esiMaster.getEsiEmp();
		data[0][3] = esiMaster.getEsiComp();
		data[0][4] = esiMaster.getEsiDebitCode();
		data[0][5] = esiMaster.getEsiFormula();
		data[0][6] = esiMaster.getStartmonth();
		data[0][7] = esiMaster.getEndmonth();

		boolean result = getSqlModel().singleExecute(getQuery(1), data);

		String recordQur = "SELECT Nvl(MAX(CREDIT_CODE),0)+1 FROM HRMS_CREDIT_HEAD";
		//logger.info("ss query " + recordQur);
		Object record[][] = getSqlModel().getSingleResult(recordQur);

		//logger.info("The Credit Code is ---- >   " + String.valueOf(record[0][0]));

		if (result) {
			xml_esiparameter(path);
			esiMaster.setEsiCode(String.valueOf(record[0][0]));
			return "Record Saved Successfully";

		} else {
			return "Record already Exist for this date!";

		}

	}

	public String updateESI(ESIMaster esiMaster, String path) {

		Object data[][] = new Object[1][9];

		data[0][0] = esiMaster.getEsiDate();
		data[0][1] = esiMaster.getEsiGross();
		data[0][2] = esiMaster.getEsiEmp();
		data[0][3] = esiMaster.getEsiComp();
		data[0][4] = esiMaster.getEsiDebitCode();
		data[0][5] = esiMaster.getEsiFormula();
		data[0][6] = esiMaster.getStartmonth();
		data[0][7] = esiMaster.getEndmonth();
		data[0][8] = esiMaster.getEsiCode();

		boolean result = getSqlModel().singleExecute(getQuery(2), data);

		if (result) {
			xml_esiparameter(path);
			return "Record Updated Successfully";

		} else {
			return "Record cann't be Updated !Record already Exist for this date!";

		}

	}

	public String deleteESI(ESIMaster esiMaster, String poolDir) {

		Object[][] code = new Object[1][1];
		code[0][0] = esiMaster.getEsiCode();
		boolean result = getSqlModel().singleExecute(getQuery(3), code);
		if (result) {
			xml_esiparameter(poolDir);
			return "deleted";

		} else {
			return "not deleted";

		}

	}

	public void getReport(ESIMaster tadist, HttpServletRequest request,	HttpServletResponse response, ServletContext context, HttpSession session) {
		CrystalReport cr = new CrystalReport();
		String path = "org\\paradyne\\rpt\\admin\\master\\esiReport.rpt";
		cr.createReport(request, response, context, session, path, "");
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void Data(ESIMaster bean, HttpServletRequest request) {

		Object[][] obj = getSqlModel().getSingleResult(getQuery(4));
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
				ESIMaster bean1 = new ESIMaster();

				bean1.setEsiCode(String.valueOf(obj[i][0]));
				bean1.setEsiDate(String.valueOf(obj[i][1]));
				bean1.setEsiGross(String.valueOf(obj[i][2]));

				list.add(bean1);
			}

			bean.setTotalRecords("" + obj.length);
			bean.setListLength("true");
		} else {
			bean.setListLength("false");
		}

		bean.setIteratorlist(list);

	}

	public void calforedit(ESIMaster bean) {

		String query = "SELECT ESI_CODE,TO_CHAR(ESI_DATE,'DD-MM-YYYY'),NVL(ESI_GROSS_UPTO,''),NVL(ESI_EMP_PERCENTAGE,''),NVL(ESI_COMP_PERCENTAGE,''),NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,''),NVL(ESI_DEBIT_CODE,'')"
				+ " ,ESI_MONTH_START, ESI_MONTH_END FROM HRMS_ESI "
				+ " LEFT JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ESI.ESI_DEBIT_CODE)"
				+ " WHERE  ESI_CODE = " + bean.getHiddencode();

		Object[][] data = getSqlModel().getSingleResult(query);
		//setter NVL(ESI_FORMULA,''),

		bean.setEsiCode(checkNull(String.valueOf(data[0][0])));
		bean.setEsiDate(checkNull(String.valueOf(data[0][1])));
		bean.setEsiGross(checkNull(String.valueOf(data[0][2])));
		bean.setEsiEmp(checkNull(String.valueOf(data[0][3])));
		bean.setEsiComp(checkNull(String.valueOf(data[0][4])));
		bean.setEsiDebitName(checkNull(String.valueOf(data[0][5])));
		//	bean.setEsiFormula(checkNull(String.valueOf(data[0][6])));
		bean.setEsiDebitCode(checkNull(String.valueOf(data[0][6])));
		bean.setStartmonth(checkNull(String.valueOf(data[0][7])));
		bean.setEndmonth(checkNull(String.valueOf(data[0][8])));

	}

	public boolean calfordelete(ESIMaster bean) {

		Object[][] delete = new Object[1][1];
		delete[0][0] = bean.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);

	}

	public String deletecheckedRecords(ESIMaster bean, String[] code,
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
			xml_esiparameter(poolDir);
		}

		if (count != 0) {
			result = false;
			return "no deleted";
		} else {
			result = true;

			return "deleted";
		}

	}

	/** WRITING IN XML **/
	/** @author reebaj **/
	public void xml_esiparameter(String path) {

		if (!(path == null || path.equals("") || path.equals(null)))
			path = "\\" + path;

		try {
			new XMLReaderWriter().write(buildEsiParameter("PAYROLL",
					"PF PARAMETER"), path);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Document buildEsiParameter(String head, String subhead) {
		String query1 = " SELECT ESI_CODE, ESI_FORMULA, ESI_EMP_PERCENTAGE, TO_CHAR(ESI_DATE,'dd-mm-yyyy'),ESI_DEBIT_CODE, ESI_GROSS_UPTO, "
				+ " ESI_MONTH_START, ESI_MONTH_END"
				+ " FROM HRMS_ESI "
				+ " ORDER BY ESI_DATE DESC";
		Object data[][] = getSqlModel().getSingleResult(query1);
		Document document = DocumentHelper.createDocument();
		//logger.info("data.length" + data.length);
		Element header;
		Element element;
		Element root = document.addElement(head);
		if (data != null && data.length > 0) {
			//header = root.addElement("ESIPARAM").addAttribute("name", subhead);

			for (int i = 0; i < data.length; i++) {
				header = root.addElement("ESIPARAM").addAttribute("code",
						String.valueOf(data[i][0])).addAttribute("formula",
						String.valueOf(data[i][1])).addAttribute("percentage",
						String.valueOf(data[i][2])).addAttribute("date",
						String.valueOf(data[i][3])).addAttribute("debitCode",
						String.valueOf(data[i][4])).addAttribute("gross",
						String.valueOf(data[i][5])).addAttribute("month_start",
						String.valueOf(data[i][6])).addAttribute("month_end",
						String.valueOf(data[i][7]));
			}

		} else {
			//logger.info("NO ESI PARAMETER");
			//header = root.addElement("ESIPARAM").addAttribute("name", subhead);
			header = root.addElement("ESIPARAM").addAttribute("code",
					String.valueOf("code")).addAttribute("formula",
					String.valueOf("fmla")).addAttribute("percentage",
					String.valueOf("pcnt")).addAttribute("date",
					String.valueOf("date")).addAttribute("debitCode",
					String.valueOf("dbcode")).addAttribute("gross",
					String.valueOf("gross")).addAttribute("month_start",
					String.valueOf("month_start")).addAttribute("month_end",
					String.valueOf("month_end"));

		}
		return document;
	}

	public void getEsiRecord(ESIMaster bean) {
		// TODO Auto-generated method stub
		String query = "SELECT ESI_CODE,TO_CHAR(ESI_DATE,'DD-MM-YYYY'),NVL(ESI_GROSS_UPTO,''),NVL(ESI_EMP_PERCENTAGE,''),NVL(ESI_COMP_PERCENTAGE,''),NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,''),NVL(ESI_DEBIT_CODE,'')"
				+ " ,ESI_MONTH_START, ESI_MONTH_END FROM HRMS_ESI "
				+ " LEFT JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ESI.ESI_DEBIT_CODE)"
				+ " WHERE  ESI_CODE = " + bean.getEsiCode();

		Object[][] data = getSqlModel().getSingleResult(query);
		//setter NVL(ESI_FORMULA,''),

		bean.setEsiCode(checkNull(String.valueOf(data[0][0])));
		bean.setEsiDate(checkNull(String.valueOf(data[0][1])));
		bean.setEsiGross(checkNull(String.valueOf(data[0][2])));
		bean.setEsiEmp(checkNull(String.valueOf(data[0][3])));
		bean.setEsiComp(checkNull(String.valueOf(data[0][4])));
		bean.setEsiDebitName(checkNull(String.valueOf(data[0][5])));
		//	bean.setEsiFormula(checkNull(String.valueOf(data[0][6])));
		bean.setEsiDebitCode(checkNull(String.valueOf(data[0][6])));
		bean.setStartmonth(checkNull(String.valueOf(data[0][7])));
		bean.setEndmonth(checkNull(String.valueOf(data[0][8])));

	}
//Added by Nilesh D on 6th Feb 2012.
	public void generateReport(ESIMaster esiMaster, HttpServletRequest request,
			HttpServletResponse	response,String reportPath)
	{
		
		ReportDataSet rds = new ReportDataSet();
		
		String type = esiMaster.getReport();// Pdf/Xls/Doc
		rds.setReportType(type);
		
		String fileName = "ESI Parameter Details " +Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("ESI Parameter Details ");
		rds.setTotalColumns(8);
		rds.setShowPageNo(true);
		// Report Generator Starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg=null;
		//Attachment Path Definition
		//String reportPath="";
		if(reportPath.equals("")){
		rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
		}
		else{
		rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
		request.setAttribute("reportPath", reportPath+fileName+"."+type);
		request.setAttribute("fileName", fileName + "." + type);
		request.setAttribute("action", "ESIMaster_input.action");
	
		// Initial Page Action name
		}
		
		String query=" SELECT ROWNUM,TO_CHAR(ESI_DATE,'DD-MM-YYYY'),NVL(ESI_GROSS_UPTO,''),NVL(ESI_EMP_PERCENTAGE,''),NVL(ESI_COMP_PERCENTAGE,''),NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,''),DECODE(ESI_MONTH_START,'1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September','10','Octomber','11','November','12','December'), DECODE(ESI_MONTH_END,'1','January','2','February','3','March','4','April','5','May','6','June','7','July','8','August','9','September','10','Octomber','11','November','12','December') FROM HRMS_ESI LEFT JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ESI.ESI_DEBIT_CODE)";
		Object[][]queryData=getSqlModel().getSingleResult(query);
		//Defining Tabular Structure and data
		TableDataSet tdstable = new TableDataSet();
		
		tdstable.setHeader(new String[]{"Sr. No.","ESI Date","ESI Gross Upto","ESI Employee %"," ESI Company%","ESI Debit Name","ESI Start Month","ESI End Month"});// defining headers
		tdstable.setData(queryData);// data object from query
		tdstable.setHeaderLines(true);
		tdstable.setBlankRowsAbove(1);
		tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
		tdstable.setCellAlignment(new int[]{1,1,1,1,1,0,0,0});
		tdstable.setCellWidth(new int[]{10,10,15,15,15,25,15,15});
		tdstable.setBorderDetail(3);
		tdstable.setHeaderTable(false);
		rg.addTableToDoc(tdstable);
		rg.process();
		if(reportPath.equals(""))
		{
		rg.createReport(response);
		}else
		{
		/* Generates the report as attachment*/
			rg.saveReport(response);
		}
	}

}
