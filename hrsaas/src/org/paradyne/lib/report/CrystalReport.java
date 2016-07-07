package org.paradyne.lib.report;

import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.lib.JRCHelper;
import org.paradyne.lib.StringEncrypter;

import com.crystaldecisions.sdk.occa.report.data.Fields;
import com.crystaldecisions.sdk.occa.report.data.ParameterField;
import com.crystaldecisions.sdk.occa.report.data.ParameterFieldDiscreteValue;
import com.crystaldecisions.sdk.occa.report.data.Values;
import com.crystaldecisions.sdk.occa.report.exportoptions.ExportOptions;
import com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat; 
import com.crystaldecisions.sdk.occa.report.exportoptions.ExcelExportFormatOptions;
import com.crystaldecisions.report.web.viewer.ReportExportControl;
import com.crystaldecisions.report.web.viewer.CrHtmlUnitEnum;



public class CrystalReport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CrystalReport.class);
	
	public CrystalReport(){
		
	}
	
public String []getConnectionDtls(HttpSession session){
		
		String pool=(String)session.getAttribute("session_pool");
		
		String driverName = "";
		String connectString = "";
		String userName = "";
		String password ="";
		
		try {
			ResourceBundle boundle = ResourceBundle.getBundle("org.paradyne.lib.ConnectionModel");
			driverName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(boundle
					.getString(pool+".driver"));
			connectString = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(boundle.getString(pool+".url"));
			userName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(boundle
					.getString(pool+".username"));
			password = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt(boundle
					.getString(pool+".password"));
			
		} catch (Exception e) {
			logger.error("Connection url does not exist"+e);
		}
		
		String dbDtls[]=new String[4];
		dbDtls[0]=driverName;
		dbDtls[1]=connectString;
		dbDtls[2]=userName;
		dbDtls[3]=password;
		return dbDtls;
	}
	
	public  void createReport(HttpServletRequest request,HttpServletResponse response,ServletContext context,HttpSession session,String path,String subReportName){
		
		String dbDtls[]=getConnectionDtls(session);
		String driverName=dbDtls[0];
		String connectString=dbDtls[1]; 
		String userName=dbDtls[2];
		String password=dbDtls[3]; 
		
		
		logger.fatal("driver11-->"+driverName+"-- connectString"+connectString+"--userName "+userName+"--password"+password);
		//logger.info("driver->"+driverName+"\nconnectString "+connectString+"\nuserName "+userName+"\n password"+password);
		String reportName="\\WEB-INF\\classes\\"+path;
		try{
			//Check if the Report Source Session Variable already exists
			Object reportSource =null;// session.getAttribute("Report1");
			
			//if the report source has not been opened
			if (reportSource == null)
			{
				//---------- Create a ReportClientDocument -------------
			    com.crystaldecisions.reports.sdk.ReportClientDocument reportClientDocument = new com.crystaldecisions.reports.sdk.ReportClientDocument();

			    //---------- Set the path to the location of the report source -------------

			    //Open report
			   
			   reportClientDocument.open(reportName, 0);
			   
			   
			 /*   String connectString = resc.getString("connectString");//"jdbc:oracle:thin:@192.168.25.206:1521:oradev9";
				String driverName = resc.getString("driverName");//"oracle.jdbc.OracleDriver";
				String JNDIName = resc.getString("JNDIName");//"";
				String userName = resc.getString("userName");//"hrsaas";			// TODO: Fill in database user
				String password = resc.getString("password");//"hrsaas";		// TODO: Fill in password
*/
				// Switch all tables on the main report and sub reports
				JRCHelper.changeDataSource(reportClientDocument, userName, password, connectString, driverName, "");
			   
				reportClientDocument.verifyDatabase();
			    //Get report source
			    reportSource = reportClientDocument.getReportSource();
			    
			    //Cache report source session variable
			    //This will be used by the viewer to display the desired report.
			    //session.setAttribute("Report1", reportSource);
			}    
			    
			//---------- Create the viewer and render the report -------------
			
			//Create the CrystalReportViewer object
			com.crystaldecisions.report.web.viewer.CrystalReportViewer crystalReportViewer = new com.crystaldecisions.report.web.viewer.CrystalReportViewer();
			
			//Set the reportsource property of the viewer
			crystalReportViewer.setReportSource(reportSource);
			//crystalReportViewer.setZoomFactor(100);
			//ReportExportControl exportControl = new ReportExportControl();
			//HEMANT
			//crystalReportViewer.setEnableLogonPrompt(false);
			
			
			
			//Set viewer attributes
			crystalReportViewer.setOwnPage(true);
			crystalReportViewer.setOwnForm(true);
			
			crystalReportViewer.setGroupTreeWidth(0);
			//crystalReportViewer.setBestFitPage(false);
			//crystalReportViewer.setDisplayPage(true);
			crystalReportViewer.setLeft(0);
			crystalReportViewer.setHeight(100);
			crystalReportViewer.setWidth(100);
			//crystalReportViewer.setZoomFactor(100);
			//crystalReportViewer.setHasZoomFactorList(false);
			
			
			
			//crystalReportViewer.set
			//Set the CrystalReportViewer print mode
			crystalReportViewer.setPrintMode(com.crystaldecisions.report.web.viewer.CrPrintMode.PDF);
			
	/*		
			//1.
			ExcelExportFormatOptions csvOptions=new ExcelExportFormatOptions();
			
			//2.
			ExportOptions exportOptions = new ExportOptions();
			  exportOptions.setFormatOptions(csvOptions);
		      exportOptions.setExportFormatType(ReportExportFormat.MSWord);
		      csvOptions.setExcelTabHasColumnHeadings(true);
		       
			
			
			//3.
			 ReportExportControl reportExportControl = new ReportExportControl();
		      reportExportControl.setReportSource(reportSource);
		      reportExportControl.setExportOptions(exportOptions);

		     		
		      
		      //4.
		      //ExportAsAtttachment(true) prompts for open or save; false opens the report
		      // in the specified format after exporting.
		      reportExportControl.setExportAsAttachment(true);
		      
		      // Export the report
		      reportExportControl.processHttpRequest(request, response, context, null);
*/		     

		      //crystalReportViewer.setHasZoomFactorList(false);
		     // crystalReportViewer.setLeft(-111);
	
			//Process the report
			crystalReportViewer.processHttpRequest(request, response, context, null);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/*
	 * PARAMETRISED REPORT GENERATION WITH SINGLE VALUE FOR EACH PARAMETER
	 */	
public  void createReport(HttpServletRequest request,HttpServletResponse response,ServletContext context,HttpSession session,String path,String subReportName,String paramNames[],String paramValues[]){
	
	String dbDtls[]=getConnectionDtls(session);
	String driverName=dbDtls[0];
	String connectString=dbDtls[1];
	String userName=dbDtls[2];
	String password=dbDtls[3];
	
	String reportName="\\WEB-INF\\classes\\"+path;
	try{
		//Check if the Report Source Session Variable already exists
		Object reportSource =null;// session.getAttribute("Report1");
		
		//if the report source has not been opened
		if (reportSource == null)
		{
			//---------- Create a ReportClientDocument -------------
		    com.crystaldecisions.reports.sdk.ReportClientDocument reportClientDocument = new com.crystaldecisions.reports.sdk.ReportClientDocument();
		    
		    //---------- Set the path to the location of the report source -------------
		    
		    //Open report
		    reportClientDocument.open(reportName, 0);
		    
		   /* String connectString = "jdbc:oracle:thin:@192.168.25.206:1521:oradev9";
			String driverName = "oracle.jdbc.OracleDriver";
			String JNDIName = "";
			String userName = "hrsaas";			// TODO: Fill in database user
			String password = "hrsaas";		// TODO: Fill in password
*/			logger.info("driver11-->"+driverName+"-- connectString"+connectString+"--userName "+userName+"--password"+password);
			// Switch all tables on the main report and sub reports
			JRCHelper.changeDataSource(reportClientDocument, userName, password, connectString, driverName, "");
		    //Get report source
		    reportSource = reportClientDocument.getReportSource();
		    
		    //Cache report source session variable
		    //This will be used by the viewer to display the desired report.
		   // session.setAttribute("Report1", reportSource);
		}    
		    
		//---------- Create the viewer and render the report -------------
			
		//Create the CrystalReportViewer object
		com.crystaldecisions.report.web.viewer.CrystalReportViewer crystalReportViewer = new com.crystaldecisions.report.web.viewer.CrystalReportViewer();
		
		Fields fields = new Fields();
		
		ParameterField param = null;
		int len=0;
		try{
			len=paramNames.length;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for (int i = 0; i < len; i++) {
			param = new ParameterField();
			param.setReportName("");
			param.setName(paramNames[i]);
			Values vals = new Values();
			ParameterFieldDiscreteValue val = new ParameterFieldDiscreteValue();
			val.setValue(paramValues[i]);
			vals.add(val);
			param.setCurrentValues(vals);
			fields.add(param);
		}
		
		
		
		
		//System.out.println("---------------------------"+desgMaster.getDesgID());
		
		
		
		
		crystalReportViewer.setParameterFields(fields);
		//Set the reportsource property of the viewer
		crystalReportViewer.setReportSource(reportSource);
		
		//Set viewer attributes
		crystalReportViewer.setOwnPage(true);
		crystalReportViewer.setOwnForm(true);
		
		
		
		crystalReportViewer.setGroupTreeWidth(0);
		//crystalReportViewer.setBestFitPage(false);
		//crystalReportViewer.setDisplayPage(true);
		crystalReportViewer.setLeft(0);
		crystalReportViewer.setHeight(100);
		crystalReportViewer.setWidth(100);
		  
		//Set the CrystalReportViewer print mode
		crystalReportViewer.setPrintMode(com.crystaldecisions.report.web.viewer.CrPrintMode.PDF);
		
		//Process the report
		crystalReportViewer.processHttpRequest(request, response, context, null); 
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

/*
 * NON-PARAMETERISED REPORT GENERATION
 */

public  void createReport(HttpServletRequest request,HttpServletResponse response,ServletContext context,HttpSession session,String path,String subReportName,String paramNames,String paramValues){
	
	String dbDtls[]=getConnectionDtls(session);
	String driverName=dbDtls[0];
	String connectString=dbDtls[1];
	String userName=dbDtls[2];
	String password=dbDtls[3];
	
	String reportName="\\WEB-INF\\classes\\"+path;
	try{
	//Check if the Report Source Session Variable already exists
	Object reportSource =null;// session.getAttribute("Report1");
	
	//if the report source has not been opened
	if (reportSource == null)
	{
		//---------- Create a ReportClientDocument -------------
	    com.crystaldecisions.reports.sdk.ReportClientDocument reportClientDocument = new com.crystaldecisions.reports.sdk.ReportClientDocument();
	
	    //---------- Set the path to the location of the report source -------------
	
	    //Open report
	   reportClientDocument.open(reportName, 0);
	    
	   /* String connectString = "jdbc:oracle:thin:@192.168.25.206:1521:oradev9";
		String driverName = "oracle.jdbc.OracleDriver";
		String JNDIName = "";
		String userName = "hrsaas";			// TODO: Fill in database user
		String password = "hrsaas";		// TODO: Fill in password
*/
		// Switch all tables on the main report and sub reports
	   logger.info("driver11-->"+driverName+"-- connectString"+connectString+"--userName "+userName+"--password"+password);
		org.paradyne.lib.JRCHelper.changeDataSource(reportClientDocument, userName, password, connectString, driverName, "");
	    //Get report source
	    reportSource = reportClientDocument.getReportSource();
	
	    //Cache report source session variable
	    //This will be used by the viewer to display the desired report.
	   // session.setAttribute("Report1", reportSource);
	}    
	    
	//---------- Create the viewer and render the report -------------
		
	//Create the CrystalReportViewer object
	com.crystaldecisions.report.web.viewer.CrystalReportViewer crystalReportViewer = new com.crystaldecisions.report.web.viewer.CrystalReportViewer();
	
	
	//Set the reportsource property of the viewer
	crystalReportViewer.setReportSource(reportSource);
	
	//Set viewer attributes
	crystalReportViewer.setOwnPage(true);
	crystalReportViewer.setOwnForm(true);
	  
	
	crystalReportViewer.setGroupTreeWidth(0);
	//crystalReportViewer.setBestFitPage(false);
	//crystalReportViewer.setDisplayPage(true);
	crystalReportViewer.setLeft(0);
	crystalReportViewer.setHeight(100);
	crystalReportViewer.setWidth(100);
	
	
	//Set the CrystalReportViewer print mode
	crystalReportViewer.setPrintMode(com.crystaldecisions.report.web.viewer.CrPrintMode.PDF);
	
	
	
	
	
	
	
	//Process the report
	crystalReportViewer.processHttpRequest(request, response, context, null); 
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	
}

}
