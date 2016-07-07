/**
 * 
 */
package org.paradyne.model.common;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.common.QueryReport;
import org.paradyne.lib.ModelBase;



/**
 * @author lakkichand
 *
 */
public class QueryReportModel extends ModelBase{
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	public String[][] getQueryResult(QueryReport queryReport){
		String[][]str=null;
		String queryHdr="SELECT QUERY_STRING FROM HRMS_QUERY_HDR WHERE QUERY_ID="+queryReport.getQueryId();
		Object[][] result1 = getSqlModel().getSingleResult(queryHdr);
		queryReport.setQueryString(String.valueOf(result1[0][0]));
		String queryDtl="SELECT QUERY_PARAMETER	FROM HRMS_QUERY_DTL WHERE QUERY_ID="+queryReport.getQueryId() +" ORDER BY  QUERY_DTL_ID	";
		
		
		Object[][] result = getSqlModel().getSingleResult(queryDtl);
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < result.length; i++) {
			QueryReport bean=new QueryReport();
			bean.setParaName(String.valueOf(result[i][0]));
			list.add(bean);

		}
		queryReport.setParaList(list);
		return str;
	}
	
	public String[][] getListResult(QueryReport queryReport,String[]param){
		String query =queryReport.getQueryString();
		logger.info("------------------query="+query);
		Object[] selObj=null;
		if(param!=null){
			selObj=new Object[param.length];
		
		for (int i = 0; i < selObj.length; i++) {
			selObj[i]=param[i];
			logger.info("-------------------param check"+selObj[i]);
		}
		}
		Object[][] result =null;
		String strObj[][]=null;
		try {
			if(param!=null)
			 result = getSqlModel().getSingleResultWithCol(query,selObj);
			else
				result = getSqlModel().getSingleResultWithCol(query);
			ArrayList<Object> list1 = new ArrayList<Object>();
			for (int i = 0; i < result[0].length; i++) {
				QueryReport bean = new QueryReport();
				bean.setHeaderName(String.valueOf(result[0][i]));
				list1.add(bean);

			}
			queryReport.setHeaderList(list1);
			strObj = new String[result.length - 1][result[0].length];
			int p = 0;
			for (int i = 1; i < result.length; i++) {
				for (int j = 0; j < result[0].length; j++) {
					strObj[p][j] = String.valueOf(result[i][j]);
					//logger.info("hhhhhhhhhh" + strObj[p][j]);
				}
				p++;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		/*for (int j = 0; j < result.length; j++) {
			for (int j2 = 0; j2 < result[0].length; j2++) {
				logger.info("------------------values of result="+String.valueOf(result[j][j2]));				
			}
		}*/
		return strObj;
	}
	
	public String[][] setValues(QueryReport queryReport,String[] param){
		String[][]str=null;
		String queryHdr="SELECT QUERY_STRING FROM HRMS_QUERY_HDR WHERE QUERY_ID="+queryReport.getQueryId();
		Object[][] result1 = getSqlModel().getSingleResult(queryHdr);
		queryReport.setQueryString(String.valueOf(result1[0][0]));
		String queryDtl="SELECT QUERY_PARAMETER	FROM HRMS_QUERY_DTL WHERE QUERY_ID="+queryReport.getQueryId() +" ORDER BY  QUERY_DTL_ID	";
		
		
		Object[][] result = getSqlModel().getSingleResult(queryDtl);
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < result.length; i++) {
			QueryReport bean=new QueryReport();
			bean.setParaName(String.valueOf(result[i][0]));
			bean.setParaValue(param[i]);
			list.add(bean);

		}
		queryReport.setParaList(list);
		return str;
	}

	
	public String getReport(QueryReport queryReport,HttpServletResponse response,String[] param,String[]headers) {
		String reportType = "Xls";
		String reportName = "Report";
		String[][]total_obj=getListResult(queryReport,param);
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				 reportType, reportName);
		rg.genHeader("");

		String text1[] = { "Report" };
		int style1[] = { 2 };
		 rg.addFormatedText(text1,style1,0,1,0);
		
		String[]colNames=new String[headers.length];
		int []cellWidth=new int[headers.length];
		int []cellAlign=new int[headers.length];
		//Object[][] objData=new Object[total_obj.length][total_obj[0].length];
		
		for (int i = 0; i < headers.length; i++) {
			colNames[i]=headers[i];
			cellWidth[i]=10;
			cellAlign[i]=0;
		}
		/*for (int i = 1; i < total_obj.length; i++) {
			objData[i-1]=total_obj[i];
		}*/
		rg.xlsTableBody(colNames, total_obj, cellWidth, cellAlign);
		rg.createReport(response);
		return null;
	}
}
