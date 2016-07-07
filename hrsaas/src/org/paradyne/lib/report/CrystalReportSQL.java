package org.paradyne.lib.report;

import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.lib.JRCHelper;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;

import com.crystaldecisions.report.web.viewer.CrPrintMode;
import com.crystaldecisions.report.web.viewer.CrystalReportViewer;
import com.crystaldecisions.reports.sdk.SubreportController;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.data.Fields;
import com.crystaldecisions.sdk.occa.report.data.ParameterField;
import com.crystaldecisions.sdk.occa.report.data.ParameterFieldDiscreteValue;
import com.crystaldecisions.sdk.occa.report.data.Values;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;

public class CrystalReportSQL extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(CrystalReportSQL.class);
/**
* METHOD TO CONNECT DATABASE
* @param session
* @return
*/
public String[] getConnectionDtls(HttpSession session) {

String pool = (String) session.getAttribute("session_pool");
String driverName = "";
String connectString = "";
String userName = "";
String password = "";
try {
	ResourceBundle boundle = ResourceBundle
			.getBundle("org.paradyne.lib.ConnectionModel");
	/*driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	connectString ="jdbc:sqlserver://gtlerp:1433;databaseName=astea";
	userName = "sa";
	password = "sa";*/
	
	
	driverName = new StringEncrypter(
			StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
			StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(boundle
			.getString(pool + ".driver"));
	connectString = new StringEncrypter(
			StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
			StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(boundle
			.getString(pool + ".url"));
	userName = new StringEncrypter(
			StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
			StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(boundle
			.getString(pool + ".username"));
	password = new StringEncrypter(
			StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
			StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(boundle
			.getString(pool + ".password"));

	System.out.println("driverName:"+driverName);
	System.out.println("connectString:"+connectString);
	System.out.println("userName:"+userName);
	System.out.println("password:"+password);
} catch (Exception e) {
	logger.error("Connection url does not exist" + e);
}

String dbDtls[] = new String[4];
dbDtls[0] = driverName;
dbDtls[1] = connectString;
dbDtls[2] = userName;
dbDtls[3] = password;
return dbDtls;
}
/**
* METHOD TO GENERATE REPORT
* @param request
* @param response
* @param context
* @param session
* @param path
* @param subReportName
* @throws Exception 
*/
public  void createReport(HttpServletRequest request,HttpServletResponse response,
		ServletContext context,HttpSession session,String path,String subReportName) throws Exception{
		String dbDtls[]=getConnectionDtls(session);
		String driverName=dbDtls[0];
		String connectString=dbDtls[1]; 
		String userName=dbDtls[2];
		String password=dbDtls[3]; 
		logger.fatal("driver11-->"+driverName+"-- connectString"+connectString+"--userName "+userName+"--password"+password);
		Object reportSource = null;
		 try {
		if(reportSource == null)
		{
		  ReportClientDocument reportClientDoc = new ReportClientDocument();
		  String report = path;		 
		  reportClientDoc.open(report, 0);		
		  reportSource = reportClientDoc.getReportSource();        
		  session.setAttribute("reportSource", reportSource);
		}
		CrystalReportViewer viewer = new CrystalReportViewer();
		viewer.setReportSource(reportSource);
		//layout
		viewer.setOwnPage(true);
		viewer.setBestFitPage(true);
		viewer.setHasLogo(false);
		viewer.setHasRefreshButton(true);
		// group navigation
		viewer.setHasToggleGroupTreeButton(false);
		viewer.setDisplayGroupTree(false);
		// page navigation:
		viewer.setHasGotoPageButton(false);
		// print/export
		viewer.setHasExportButton(true);
		//viewer.setPrintMode(CrPrintMode.PDF);
		//viewer.setPrintMode(CrPrintMode.ACTIVEX);
		viewer.setIgnoreViewStateOnLoad(true);
		//viewer.setParameterFields(fields);
		viewer.setEnableParameterPrompt(false);
		viewer.refresh();
		viewer.processHttpRequest(request, response, context, null);
		viewer.dispose();

} catch (ReportSDKException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}


public  void createReportNew(HttpServletRequest request,HttpServletResponse response,ServletContext context,HttpSession session,String path,String subReportName){

			String dbDtls[]=getConnectionDtls(session);
			String driverName=dbDtls[0];
			String connectString=dbDtls[1]; 
			String userName=dbDtls[2];
			String password=dbDtls[3]; 
			System.out.println("INSIDE NEW METHOD");
			logger.fatal("driver11-->"+driverName+"-- connectString"+connectString+"--userName "+userName+"--password"+password);
			//logger.info("driver->"+driverName+"\nconnectString "+connectString+"\nuserName "+userName+"\n password"+password);
			String reportName=path;
			try{
			Object reportSource =null;// session.getAttribute("Report1");
			if (reportSource == null)
			{			
			    com.crystaldecisions.reports.sdk.ReportClientDocument reportClientDocument = new com.crystaldecisions.reports.sdk.ReportClientDocument();
				    reportClientDocument.open(reportName, 0);				   
				JRCHelper.changeDataSource(reportClientDocument, userName, password, connectString, driverName, "");
			   	reportClientDocument.verifyDatabase();		   
			    reportSource = reportClientDocument.getReportSource();		  
			}    
			
			com.crystaldecisions.report.web.viewer.CrystalReportViewer crystalReportViewer = new com.crystaldecisions.report.web.viewer.CrystalReportViewer();
			//Set the reportsource property of the viewer
			crystalReportViewer.setReportSource(reportSource);
			//Set viewer attributes
			crystalReportViewer.setOwnPage(true);
			crystalReportViewer.setOwnForm(true);		
			crystalReportViewer.setGroupTreeWidth(0);
			crystalReportViewer.setLeft(0);
			crystalReportViewer.setHeight(100);
			crystalReportViewer.setWidth(100);
			crystalReportViewer.setPrintMode(com.crystaldecisions.report.web.viewer.CrPrintMode.PDF);
			crystalReportViewer.processHttpRequest(request, response, context, null);

}catch(Exception e){
e.printStackTrace();
}

}


public  void createReportNew(HttpServletRequest request,HttpServletResponse response,ServletContext context,
		HttpSession session,String path,String subReportName,
		String paramNames,String paramValues,String paramNames1,String paramValues1,String paramNames2,String paramValues2){

				String dbDtls[]=getConnectionDtls(session);
				String driverName=dbDtls[0];
				String connectString=dbDtls[1];
				String userName=dbDtls[2];
				String password=dbDtls[3];
				String reportName=path;
				try{
				Object reportSource =null;// session.getAttribute("Report1");
				if (reportSource == null)
				{
				com.crystaldecisions.reports.sdk.ReportClientDocument reportClientDocument = new com.crystaldecisions.reports.sdk.ReportClientDocument();
				reportClientDocument.open(reportName, 0);
				logger.info("driver11-->"+driverName+"-- connectString"+connectString+"--userName "+userName+"--password"+password);
				org.paradyne.lib.JRCHelper.changeDataSource(reportClientDocument, userName, password, connectString, driverName, "");
				reportSource = reportClientDocument.getReportSource();	
				//reportClientDocument.
				} 
				
				//Create the CrystalReportViewer object
				com.crystaldecisions.report.web.viewer.CrystalReportViewer viewer = new com.crystaldecisions.report.web.viewer.CrystalReportViewer();
				
				System.out.println("paramNames:"+paramNames);
				System.out.println("paramValues:"+paramValues);
				Fields fields = new Fields();
				
				if(!(paramNames.equals("")&&paramValues.equals(""))){
					ParameterField pfield1 = new ParameterField();
					Values vals1 = new Values();
					ParameterFieldDiscreteValue pfieldDV1 = new ParameterFieldDiscreteValue();
					  pfield1.setReportName("");
					pfield1.setName(paramNames);
					pfieldDV1.setValue(paramValues);
				//	pfieldDV1.setDescription("Query Dinamica....");
					vals1.add(pfieldDV1);
					pfield1.setCurrentValues(vals1);
					fields.add(pfield1);	
				}
				
				if(!(paramNames1.equals("")&&paramValues1.equals(""))){
					ParameterField pfield1 = new ParameterField();
					Values vals1 = new Values();
					ParameterFieldDiscreteValue pfieldDV1 = new ParameterFieldDiscreteValue();
					  pfield1.setReportName("");
					pfield1.setName(paramNames1);
					pfieldDV1.setValue(paramValues1);
					vals1.add(pfieldDV1);
					pfield1.setCurrentValues(vals1);
					fields.add(pfield1);	
				}
				if(!(paramNames2.equals("")&&paramValues2.equals(""))){
					ParameterField pfield1 = new ParameterField();
					Values vals1 = new Values();
					ParameterFieldDiscreteValue pfieldDV1 = new ParameterFieldDiscreteValue();
					  pfield1.setReportName("");
					pfield1.setName(paramNames2);
					pfieldDV1.setValue(paramValues2);
					vals1.add(pfieldDV1);
					pfield1.setCurrentValues(vals1);
					fields.add(pfield1);	
				}
				
				System.out.println("GENERATE");
				//CrystalReportViewer viewer = new CrystalReportViewer();
				viewer.setReportSource(reportSource);
				// layout
				viewer.setOwnPage(true);
				viewer.setBestFitPage(false);
				viewer.setHasLogo(false);
				viewer.setHasRefreshButton(true);
				// group navigation
				viewer.setHasToggleGroupTreeButton(false);
				viewer.setDisplayGroupTree(false);
				// page navigation:
				viewer.setHasGotoPageButton(false);
				//viewer.setpa
				System.out.println("width:::"+viewer.getWidth());
				// print/export
				viewer.setHasExportButton(true);
				//viewer.setPrintMode(CrPrintMode.PDF);
				//viewer.setPrintMode(CrPrintMode.ACTIVEX);
				//viewer.setIgnoreViewStateOnLoad(true);
				if(!(paramNames.equals("")&&paramValues.equals(""))){
				viewer.setParameterFields(fields);
				}
				viewer.setEnableParameterPrompt(false);
				viewer.refresh();
				viewer.processHttpRequest(request, response, context, null); 
				viewer.dispose();

}catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}

public  void createReportNew(HttpServletRequest request,HttpServletResponse response,ServletContext context,
		HttpSession session,String path,String subReportName,
		String paramNames[],String paramValues[],String subParamNames[],String subParamValues[]){

				String dbDtls[]=getConnectionDtls(session);
				String driverName=dbDtls[0];
				String connectString=dbDtls[1];
				String userName=dbDtls[2];
				String password=dbDtls[3];
				String reportName=path;
				try{
				Object reportSource =null;// session.getAttribute("Report1");
				if (reportSource == null)
				{
				com.crystaldecisions.reports.sdk.ReportClientDocument reportClientDocument = new com.crystaldecisions.reports.sdk.ReportClientDocument();
				reportClientDocument.open(reportName, 0);
				logger.info("driver11-->"+driverName+"-- connectString"+connectString+"--userName "+userName+"--password"+password);
				//org.paradyne.lib.JRCHelper.changeDataSource(reportClientDocument, userName, password, connectString, driverName, "",null);
				org.paradyne.lib.JRCHelper.changeDataSource(reportClientDocument, userName, password, connectString, driverName, "");
				reportSource = reportClientDocument.getReportSource();	
				//reportClientDocument.
				} 
				
				//Create the CrystalReportViewer object
				com.crystaldecisions.report.web.viewer.CrystalReportViewer viewer = new com.crystaldecisions.report.web.viewer.CrystalReportViewer();
				
				Fields fields = new Fields();
				if(paramNames!=null && paramNames.length>0){
					for (int i = 0; i < paramNames.length; i++) {
						System.out.println("PARAMETER:"+paramNames[i]);
						System.out.println("VALUES:"+paramValues[i]);
						ParameterField pfield1 = new ParameterField();
						Values vals1 = new Values();
						ParameterFieldDiscreteValue pfieldDV1 = new ParameterFieldDiscreteValue();
						pfield1.setReportName("");
						pfield1.setName(String.valueOf(paramNames[i]));
						pfieldDV1.setValue(String.valueOf(paramValues[i]));
						//pfieldDV1.setDescription("Query Dinamica....");
						vals1.add(pfieldDV1);
						pfield1.setCurrentValues(vals1);
						fields.add(pfield1);						
					}
				}
				
				  // SubreportController src = reportClientDocument.getSubreportController();
				
				if(subParamNames!=null && subParamNames.length>0){
					for (int i = 0; i < subParamNames.length; i++) {
						System.out.println("SUB PARAMETER:"+subParamNames[i]);
						System.out.println("SUB VALUES:"+subParamValues[i]);
						ParameterField pfield1 = new ParameterField();
						Values vals1 = new Values();
						ParameterFieldDiscreteValue pfieldDV1 = new ParameterFieldDiscreteValue();
						pfield1.setReportName("WeeklyStoplightSummary.rpt");
						pfield1.setName(String.valueOf(subParamNames[i]));
						pfieldDV1.setValue(String.valueOf(subParamValues[i]));
						//pfieldDV1.setDescription("Query Dinamica....");
						vals1.add(pfieldDV1);
						pfield1.setCurrentValues(vals1);
						fields.add(pfield1);						
					}
				}
								
				System.out.println("GENERATE");
				//CrystalReportViewer viewer = new CrystalReportViewer();
				viewer.setReportSource(reportSource);
				// layout
				viewer.setOwnPage(true);
				viewer.setBestFitPage(false);
				viewer.setHasLogo(false);
				viewer.setHasRefreshButton(true);
				// group navigation
				viewer.setHasToggleGroupTreeButton(false);
				viewer.setDisplayGroupTree(false);
				// page navigation:
				viewer.setHasGotoPageButton(false);
				// print/export
				viewer.setHasExportButton(true);
				//viewer.setPrintMode(CrPrintMode.PDF);
				//viewer.setPrintMode(CrPrintMode.ACTIVEX);
				//viewer.setIgnoreViewStateOnLoad(true);
				if(paramNames!=null && paramNames.length>0){
				viewer.setParameterFields(fields);
				}
				viewer.setEnableParameterPrompt(false);
				viewer.refresh();
				viewer.processHttpRequest(request, response, context, null); 
				viewer.dispose();

}catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}


public  void createReportNew(HttpServletRequest request,HttpServletResponse response,ServletContext context,HttpSession session,String path,String subReportName,String paramNames,String paramValues,Object[][]obj){

String dbDtls[]=getConnectionDtls(session);
String driverName=dbDtls[0];
String connectString=dbDtls[1];
String userName=dbDtls[2];
String password=dbDtls[3];
String reportName=path;
try{
Object reportSource =null;// session.getAttribute("Report1");
if (reportSource == null)
{
com.crystaldecisions.reports.sdk.ReportClientDocument reportClientDocument = new com.crystaldecisions.reports.sdk.ReportClientDocument();
reportClientDocument.open(reportName, 0);
logger.info("driver11-->"+driverName+"-- connectString"+connectString+"--userName "+userName+"--password"+password);
org.paradyne.lib.JRCHelper.changeDataSource(reportClientDocument, userName, password, connectString, driverName, "");
reportSource = reportClientDocument.getReportSource();	
//reportClientDocument.
} 

//Create the CrystalReportViewer object
com.crystaldecisions.report.web.viewer.CrystalReportViewer viewer = new com.crystaldecisions.report.web.viewer.CrystalReportViewer();

			System.out.println("paramNames:"+paramNames);
			System.out.println("paramValues:"+paramValues);
			Fields fields = new Fields();
			
			if(!(paramNames.equals("")&&paramValues.equals(""))){

				ParameterField pfield1 = new ParameterField();
				Values vals1 = new Values();
				ParameterFieldDiscreteValue pfieldDV1 = new ParameterFieldDiscreteValue();
				  pfield1.setReportName("");
				pfield1.setName(paramNames);
				pfieldDV1.setValue(paramValues);
			//	pfieldDV1.setDescription("Query Dinamica....");
			
				vals1.add(pfieldDV1);
				pfield1.setCurrentValues(vals1);
				fields.add(pfield1);	
			}
			
			
			System.out.println("GENERATE");
			//CrystalReportViewer viewer = new CrystalReportViewer();
			viewer.setReportSource(reportSource);
			
		
			// layout
			viewer.setOwnPage(true);
			viewer.setBestFitPage(true);
			viewer.setHasLogo(false);
			viewer.setHasRefreshButton(true);
			viewer.setWidth(200);
			// group navigation
			viewer.setHasToggleGroupTreeButton(false);
			viewer.setDisplayGroupTree(false);
			// page navigation:
			viewer.setHasGotoPageButton(false);
		
			// print/export
			viewer.setHasExportButton(true);
			//viewer.setPrintMode(CrPrintMode.PDF);
			//viewer.setPrintMode(CrPrintMode.ACTIVEX);
			viewer.setIgnoreViewStateOnLoad(true);
			if(!(paramNames.equals("")&&paramValues.equals(""))){
			viewer.setParameterFields(fields);
			}
			viewer.setEnableParameterPrompt(false);
			viewer.refresh();
		
			viewer.processHttpRequest(request, response, context, null); 
			viewer.dispose();

}catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
}
}
