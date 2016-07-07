/**
 * 
 */
package org.struts.action.common;

import org.paradyne.bean.common.QueryReport;

import org.paradyne.model.common.QueryReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author lakkichand
 *
 */
public class QueryReportAction extends ParaActionSupport {
		
	QueryReport queryReport;
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		queryReport=new QueryReport();

	}
	/**
	 * @return the queryReport
	 */
	public QueryReport getQueryReport() {
		return queryReport;
	}

	/**
	 * @param queryReport the queryReport to set
	 */
	public void setQueryReport(QueryReport queryReport) {
		this.queryReport = queryReport;
	}
	

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return queryReport;
	}
	
	public String getQuery()throws Exception {
		QueryReportModel model=new QueryReportModel();
		model.initiate(context,session);
		logger.info("################################"+queryReport.getQueryId());
		model.getQueryResult(queryReport);
		model.terminate();
		
		return SUCCESS;
	}
	
	public String f9Query() throws Exception {
		try{
		logger.info("*********F9Window********************");
		String query = "SELECT QUERY_ID	,QUERY_NAME FROM HRMS_QUERY_HDR";		
		String[] headers={"Query Code", "Query Name"};
		String[] headerWidth={"20", "80"};
		String[] fieldNames={"queryReport.queryId","queryReport.queryName"};
		int[] columnIndex={0,1};
		String submitFlag = "true";
		String submitToMethod="QueryReport_getQuery.action";
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		}catch(Exception e){e.printStackTrace();}
		return "f9page";
	}

	public String view()throws Exception{
		String[]param= request.getParameterValues("paraValue");
		if(param==null){
			logger.info("YYYYYYYY");
		}
		
		
		QueryReportModel model=new QueryReportModel();
		model.initiate(context,session);
		
		logger.info("################################"+queryReport.getQueryId());
		String[][]strObj=model.getListResult(queryReport,param);
		request.setAttribute("strObj",strObj);
		model.setValues(queryReport,param);
		model.terminate();
		
		  
		return SUCCESS;
	}
	public String export() throws Exception {
		String[]param= request.getParameterValues("paraValue");
		String[]headers= request.getParameterValues("headers");
		QueryReportModel model=new QueryReportModel();
		model.initiate(context,session);
		model.getReport(queryReport, response,param,headers);
		model.terminate();
		return null;
	}
	
	

}
