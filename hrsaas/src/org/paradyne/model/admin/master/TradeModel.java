package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.admin.master.TradeMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.itextpdf.text.BaseColor;

/**
 * @author pranali Date 23-04-07
 * Modified by Dilip Kumar Dewangan
 */
public class TradeModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	org.paradyne.bean.admin.master.TradeMaster tradeMaster = null;

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(TradeMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TRADE WHERE UPPER(TRADE_NAME) LIKE '"
				+ bean.getTradeName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;

	}

	/* for checking duplicate entry of record during modificaion */
	public boolean checkDuplicateMod(TradeMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TRADE WHERE UPPER(TRADE_NAME) LIKE '"
				+ bean.getTradeName().trim().toUpperCase()
				+ "' AND TRADE_CODE not in(" + bean.getTradeCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;

	}

	/* for inserting the data */
	public boolean addTrade(TradeMaster bean) {

		/*
		 * String query = " SELECT TRADE_NAME FROM HRMS_TRADE WHERE TRADE_NAME =
		 * '"+bean.getTradeName()+"'"; Object[][] data =
		 * getSqlModel().getSingleResult(query); if(data.length>0) { return
		 * false; }else {
		 */
		if (!checkDuplicate(bean)) {
			String query="SELECT NVL(MAX(TRADE_CODE),0)+1 FROM  HRMS_TRADE";
			Object[][]rel=getSqlModel().getSingleResult(query);
			 bean.setTradeCode(String.valueOf(rel[0][0]));
			
			Object addObj[][] = new Object[1][4];
			addObj[0][0] = bean.getTradeName().trim();
			addObj[0][1] = bean.getTradeAbbr().trim();			
			addObj[0][2] = bean.getTradeDesc().trim();
			addObj[0][3] = bean.getTradeParentCode().trim();
			return getSqlModel().singleExecute(getQuery(1), addObj);
		}//end of if
		else {
			return false;
		}//end of else
	}

	/* for modifing the data */
	public boolean modTrade(TradeMaster bean) {
		if (!checkDuplicateMod(bean)) {
			Object addObj[][] = new Object[1][5];
			addObj[0][0] = bean.getTradeName().trim();
			addObj[0][1] = bean.getTradeAbbr().trim();
			addObj[0][2] = bean.getTradeDesc().trim();
			addObj[0][3] = bean.getTradeParentCode().trim();
			addObj[0][4] = bean.getTradeCode().trim();
			return getSqlModel().singleExecute(getQuery(2), addObj);
		} //end of if
		else
			return false;
	}

	/* for deleting the record after selecting */
	public boolean deleteTrade(TradeMaster bean) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getTradeCode();
		return getSqlModel().singleExecute(getQuery(3), addObj);
	}

	public void getTradeReport(TradeMaster tradeMaster) {
		
		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		ArrayList<Object> att = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			TradeMaster bean1 = new TradeMaster();
			bean1.setTradeCode(checkNull(String.valueOf(data[i][0])));
			bean1.setTradeName(checkNull(String.valueOf(data[i][1])));
			bean1.setTradeAbbr(checkNull(String.valueOf(data[i][2])));
			bean1.setTradeDesc(checkNull(String.valueOf(data[i][3])));
			bean1.setTradeParentCode(checkNull(String.valueOf(data[i][4])));
			att.add(bean1);

		}//end of loop
		tradeMaster.setAtt(att);

	}
	/**
	 *  to generate the report for trade masters
	 * @param tradeMaster
	 * @param request
	 * @param response
	 * @param context
	 * @param session
	 * @param label
	 */
	

	public void getReport(TradeMaster tradeMaster, HttpServletRequest request,
			HttpServletResponse	response, ServletContext context, HttpSession session,String []label) {
		/*CrystalReport cr = new CrystalReport();
		String path = "org\\paradyne\\rpt\\admin\\master\\trade.rpt";
		cr.createReport(request, response, context, session, path, "");*/
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nTrade Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Trade  Master.Pdf");
		String queryDes = "SELECT TRADE_NAME,TRADE_ABBR,TRADE_DESC,TRADE_PARENT_CODE FROM HRMS_TRADE order by upper(TRADE_NAME)";
						
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][5];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				Data[i][4] = data[i][3];
				j++;
			}
			int cellwidth[] = { 15, 40, 20, 40, 30 };
			int alignment[] = { 1, 0, 0, 0, 0 };
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
	
	public void getTradeRecord(TradeMaster tradeMaster) {
		Object param[] = new Object[1];
		param[0] = tradeMaster.getTradeCode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), param);
		tradeMaster.setTradeCode(checkNull(String.valueOf(data[0][0])));
		tradeMaster.setTradeName(checkNull(String.valueOf(data[0][1])));
		tradeMaster.setTradeAbbr(checkNull(String.valueOf(data[0][2])));
		tradeMaster.setTradeParentName(checkNull(String.valueOf(data[0][3])));
		tradeMaster.setTradeDesc(checkNull(String.valueOf(data[0][4])));
		tradeMaster.setTradeParentCode(checkNull(String.valueOf(data[0][5])));

	}
/**
 *  to check null value
 * @param result
 * @return
 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}//end of if
		else {
			return result;
		}//end of else
	}

	/* generating the  record list in onload */
	public void tradeData(TradeMaster tradeMaster, HttpServletRequest request) {

		Object[][] repData = getSqlModel().getSingleResult(getQuery(6));
		if(repData!=null&& repData.length>0){
			tradeMaster.setModeLength("true");
			tradeMaster.setTotalRecords(String.valueOf(repData.length));
		
		String[] pageIndex = Utility.doPaging(tradeMaster.getMyPage(), repData.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			tradeMaster.setMyPage("1");
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
			
			TradeMaster bean1 = new TradeMaster();
			bean1.setTradeCode(checkNull(String.valueOf(repData[i][0])));
			bean1.setTradeName(checkNull(String.valueOf(repData[i][1])));
			bean1.setTradeAbbr(checkNull(String.valueOf(repData[i][2])));
			bean1.setTradeDesc(checkNull(String.valueOf(repData[i][3])));

			List.add(bean1);
		}//end of loop

		tradeMaster.setAtt(List);
		}
	}

	/* for selecting the record from list by double clicking */
	public void calforedit(TradeMaster tradeMaster) {

		String query = "  SELECT TRADE_CODE,TRADE_NAME,TRADE_ABBR,TRADE_PARENT_CODE FROM HRMS_TRADE  where TRADE_CODE= "
				+ tradeMaster.getHiddencode() + "    ORDER BY TRADE_CODE";

		Object[][] data = getSqlModel().getSingleResult(query);
		tradeMaster.setTradeCode(String.valueOf(data[0][0]));
		tradeMaster.setTradeName(String.valueOf(data[0][1]));
		tradeMaster.setTradeAbbr(String.valueOf(data[0][2]));
		tradeMaster.setTradeParentCode(String.valueOf(data[0][3]));

	}

	/**
	 *  to delete the single record
	 * @param tradeMaster
	 * @return
	 */
	public boolean calfordelete(TradeMaster tradeMaster) {

		Object[][] delete = new Object[1][1];
		delete[0][0] = tradeMaster.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);
		// TODO Auto-generated method stub

	}

	/* for deleting one or more record from list */
	public boolean deleteTrade(TradeMaster tradeMaster, String[] code) {

		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					//logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->" + code[i]);
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(getQuery(3), delete);
					if (!flag) {
						cnt++;
					}//end of if
					// result=true;
				}//end of if
			}//end of loop
		}//end of nested if
		if (cnt > 0) {
			result = false;
		}//end of if
		else
			result = true;
		return result;

	}
	//By vijay START
	public void getReport2(TradeMaster tradeMaster, HttpServletRequest request,
			HttpServletResponse	response,String reportPath)
	{
		
		ReportDataSet rds = new ReportDataSet();
		String type = tradeMaster.getReport();// Pdf/Xls/Doc
		rds.setReportType(type);
		String fileName = "TradeMaster Details " +Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("TradeMaster Details ");
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
		request.setAttribute("action", "TradeMaster_input.action");
		// Initial Page Action name
		}
		
		String query=" SELECT ROWNUM ,T.TRADE_NAME, T.TRADE_ABBR, T.TRADE_DESC,N.TRADE_NAME FROM HRMS_TRADE T LEFT JOIN HRMS_TRADE N ON(N.TRADE_CODE=T.TRADE_PARENT_CODE)";
		Object[][]queryData=getSqlModel().getSingleResult(query);
		//Defining Tabular Structure and data
		TableDataSet tdstable = new TableDataSet();
		tdstable.setHeader(new String[]{"Sr. No.", "Trade Name", "Trade Abbreviation","Trade Description","Trade Parent" });// defining headers
		tdstable.setData(queryData);// data object from query
		tdstable.setHeaderLines(true);
		tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
		tdstable.setCellAlignment(new int[]{2,0,0,0,0});
		tdstable.setCellWidth(new int[]{10,20,20,40,10});
		tdstable.setBorderDetail(0);
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
	//END

}
