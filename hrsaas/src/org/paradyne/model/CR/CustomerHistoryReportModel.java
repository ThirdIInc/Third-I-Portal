package org.paradyne.model.CR;

import com.itextpdf.text.BaseColor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.CR.AccountMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class CustomerHistoryReportModel extends ModelBase
{
  public static final String REPORT_TYPE_PDF = "Pdf";
  private static Logger logger = Logger.getLogger(CustomerHistoryReportModel.class);

  public String checkNull(String result) 
  {
    if ((result == null) || ("null".equals(result))) { 
      return "";
    }
    return result;
  }

  public void getReport(AccountMaster bean, HttpServletResponse response, HttpServletRequest request, String reportPath)
  {
    try
    {
      ReportDataSet rds = new ReportDataSet();
      String type = bean.getReport();
      rds.setReportType(type);
      String fileName = "Customer History Report " + Utility.getRandomNumber(1000);
      String reportPathName = reportPath + fileName + "." + type;
      rds.setFileName(fileName);
      rds.setReportName("Customer History Report");
      rds.setUserEmpId(bean.getUserEmpId());
      rds.setPageSize("A4");
      rds.setShowPageNo(true);
      rds.setTotalColumns(5);
      
      ReportGenerator rg = null;

      if (reportPath.equals("")) {
        rg = new ReportGenerator(rds, this.session, this.context, request);
      } else {
        logger.info("################# ATTACHMENT PATH #############" + reportPath + fileName + "." + type);
        rg = new ReportGenerator(rds, reportPath, this.session, this.context, request);
        request.setAttribute("reportPath", reportPath + fileName + "." + type);
        request.setAttribute("fileName", fileName + "." + type);
        request.setAttribute("action", "/cr/CustomerHistoryReport_input.action");
      }
      rg.SPLIT_LATE=false;
      rg = getReport(rg, bean);
      rg.process();
      if (reportPath.equals("")) {
        rg.createReport(response);
      }
      else
        rg.saveReport(response);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private ReportGenerator getReport(ReportGenerator rg, AccountMaster bean) {
    try {
      String fromDate = bean.getFromDate();
      String toDate = bean.getToDate();

      String filters = "Period: " + fromDate + " to " + toDate;

      TableDataSet filterData = new TableDataSet();
      filterData.setData(new Object[][] { { filters } });
      filterData.setCellAlignment(new int[1]);
      filterData.setCellWidth(new int[] { 100 });
      filterData.setCellColSpan(new int[] { 5 });
      filterData.setBorder(Boolean.valueOf(false));
      rg.addTableToDoc(filterData);
      
      /*String bankNameQuery = "SELECT TO_CHAR(FIRST_NAME||' '||LAST_NAME),ACCOUNT_ID,REPORT_NAME,  TO_CHAR(GENEARTE_DATE,'DD-MM-YYYY  HH24: MM')  FROM CR_REPORT_HISTORY  INNER JOIN CR_CLIENT_MASTER ON(CR_CLIENT_MASTER.ACCOUNT_CODE=CR_REPORT_HISTORY.ACCOUNT_CODE) INNER JOIN CR_REPORT_MASTER ON(CR_REPORT_MASTER.REPORT_CODE=CR_REPORT_HISTORY.REPORT_CODE) INNER JOIN CR_CLIENT_USERS ON (CR_CLIENT_USERS.CRUSER_CODE = CR_REPORT_HISTORY.CUSTOMER_CODE)    WHERE  CR_REPORT_HISTORY.GENEARTE_DATE >=TO_DATE('" + 
        fromDate + "','DD-MM-YYYY')  " + 
        "\tAND CR_REPORT_HISTORY.GENEARTE_DATE <=TO_DATE('" + toDate + "','DD-MM-YYYY')";*/
      
      /*String bankNameQuery = "SELECT TO_CHAR(FIRST_NAME||' '||LAST_NAME),ACCOUNT_ID,REPORT_NAME,  TO_CHAR(GENEARTE_DATE,'DD-MM-YYYY  HH24: MI'),'Client' user_type  FROM CR_REPORT_HISTORY  "
			+" INNER JOIN CR_CLIENT_MASTER ON(CR_CLIENT_MASTER.ACCOUNT_CODE=CR_REPORT_HISTORY.ACCOUNT_CODE) "
			+" INNER JOIN CR_REPORT_MASTER ON(CR_REPORT_MASTER.REPORT_CODE=CR_REPORT_HISTORY.REPORT_CODE) "
			+" INNER JOIN CR_CLIENT_USERS ON (CR_CLIENT_USERS.CRUSER_CODE = CR_REPORT_HISTORY.CUSTOMER_CODE) and cr_report_history.crm_flag='N'"
			+" WHERE  TRUNC(CR_REPORT_HISTORY.GENEARTE_DATE) >=TO_DATE('" + fromDate + "','DD-MM-YYYY')  " + 
				"\tAND TRUNC(CR_REPORT_HISTORY.GENEARTE_DATE) <=TO_DATE('" + toDate + "','DD-MM-YYYY')"
			+" union all          "
			+" SELECT TO_CHAR(EMP_FNAME||' '||EMP_LNAME),ACCOUNT_ID,REPORT_NAME,  TO_CHAR(GENEARTE_DATE,'DD-MM-YYYY  HH24: MI'),'CRM' user_type  "
			+" FROM CR_REPORT_HISTORY  "
			+" INNER JOIN CR_CLIENT_MASTER ON(CR_CLIENT_MASTER.ACCOUNT_CODE=CR_REPORT_HISTORY.ACCOUNT_CODE) "
			+" INNER JOIN CR_REPORT_MASTER ON(CR_REPORT_MASTER.REPORT_CODE=CR_REPORT_HISTORY.REPORT_CODE) "
			+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = CR_REPORT_HISTORY.CUSTOMER_CODE) and cr_report_history.crm_flag='Y'"
			+" WHERE  TRUNC(CR_REPORT_HISTORY.GENEARTE_DATE) >=TO_DATE('" + fromDate + "','DD-MM-YYYY') " + 
				"\tAND TRUNC(CR_REPORT_HISTORY.GENEARTE_DATE) <=TO_DATE('" + toDate + "','DD-MM-YYYY')";*/
    	  
      String bankNameQuery="select heo.emp_fname||' '||heo.emp_mname||' '||heo.emp_lname, ccu.first_name||' '|| ccu.last_name ," +
      		" cag.usertype ,cag.actiondate, cag.ip,cag.port,  cd.dashboard_caption,       		" +
      		" crm.report_name ,ccm.component_caption,   " +
      		" case when cag.isemail = 1 then 'Y' else 'N' end    " +
      		" from cr_access_log cag ,   " +
      		" cr_dashboard cd, cr_report_master  crm ,cr_component_master ccm           , " +
      		" hrms_emp_offc heo,cr_client_users ccu  " +
      		" where trunc(cag.actiondate)>=to_date('" + fromDate + "','dd-mm-yy')   " +
      		" and trunc(cag.actiondate)<=to_date('" + toDate + "'  ,'dd-mm-yy')     " +
      		" and cd.dashboard_id=cag.dashboardid      " +
      		" and crm.report_id(+) =cag.reportid       " +
      		" and ccm.component_id(+)=cag.graphid      " +
      		" and heo.emp_id(+)=cag.empid       " +
      		" and ccu.cruser_code(+)=cag.clientid     " +
      		" order by cag.actiondate";
      Object[][] processDetail = getSqlModel().getSingleResult(bankNameQuery);
      Object[][] branchData = (Object[][])null;
      try {
        /*String branchDataQuery = "SELECT rownum,TO_CHAR(FIRST_NAME||' '||LAST_NAME),ACCOUNT_ID,REPORT_NAME,  TO_CHAR(GENEARTE_DATE,'DD-MM-YYYY  HH24: MM')  FROM CR_REPORT_HISTORY  INNER JOIN CR_CLIENT_MASTER ON(CR_CLIENT_MASTER.ACCOUNT_CODE=CR_REPORT_HISTORY.ACCOUNT_CODE) INNER JOIN CR_REPORT_MASTER ON(CR_REPORT_MASTER.REPORT_CODE=CR_REPORT_HISTORY.REPORT_CODE) INNER JOIN CR_CLIENT_USERS ON (CR_CLIENT_USERS.CRUSER_CODE = CR_REPORT_HISTORY.CUSTOMER_CODE)    " +
        		"WHERE  CR_REPORT_HISTORY.GENEARTE_DATE >=TO_DATE('" + fromDate + "','DD-MM-YYYY')  " + 
          "\tAND CR_REPORT_HISTORY.GENEARTE_DATE <=TO_DATE('" + toDate + "','DD-MM-YYYY')";*/
        
        
    /*    String branchDataQuery = "SELECT ROWNUM,A.* FROM "          
				+" (SELECT TO_CHAR(FIRST_NAME||' '||LAST_NAME),ACCOUNT_ID,REPORT_NAME,  TO_CHAR(GENEARTE_DATE,'DD-MM-YYYY  HH24: MI'),'Client' user_type  FROM CR_REPORT_HISTORY  "
				+" INNER JOIN CR_CLIENT_MASTER ON(CR_CLIENT_MASTER.ACCOUNT_CODE=CR_REPORT_HISTORY.ACCOUNT_CODE) "
				+" INNER JOIN CR_REPORT_MASTER ON(CR_REPORT_MASTER.REPORT_CODE=CR_REPORT_HISTORY.REPORT_CODE) "
				+" INNER JOIN CR_CLIENT_USERS ON (CR_CLIENT_USERS.CRUSER_CODE = CR_REPORT_HISTORY.CUSTOMER_CODE) and cr_report_history.crm_flag='N'"
				+" WHERE  TRUNC(CR_REPORT_HISTORY.GENEARTE_DATE) >=TO_DATE('" + fromDate + "','DD-MM-YYYY')  " + 
					"\tAND TRUNC(CR_REPORT_HISTORY.GENEARTE_DATE) <=TO_DATE('" + toDate + "','DD-MM-YYYY')"
				+" union all          "
				+" SELECT TO_CHAR(EMP_FNAME||' '||EMP_LNAME),ACCOUNT_ID,REPORT_NAME,  TO_CHAR(GENEARTE_DATE,'DD-MM-YYYY  HH24: MI'),'CRM' user_type  "
				+" FROM CR_REPORT_HISTORY  "
				+" INNER JOIN CR_CLIENT_MASTER ON(CR_CLIENT_MASTER.ACCOUNT_CODE=CR_REPORT_HISTORY.ACCOUNT_CODE) "
				+" INNER JOIN CR_REPORT_MASTER ON(CR_REPORT_MASTER.REPORT_CODE=CR_REPORT_HISTORY.REPORT_CODE) "
				+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = CR_REPORT_HISTORY.CUSTOMER_CODE) and cr_report_history.crm_flag='Y'"
				+" WHERE  TRUNC(CR_REPORT_HISTORY.GENEARTE_DATE) >=TO_DATE('" + fromDate + "','DD-MM-YYYY') " + 
					"\tAND TRUNC(CR_REPORT_HISTORY.GENEARTE_DATE) <=TO_DATE('" + toDate + "','DD-MM-YYYY')) A";*/
        
  	  
        String branchDataQuery="select heo.emp_fname||' '||heo.emp_mname||' '||heo.emp_lname, ccu.first_name||' '|| ccu.last_name ," +
  		" cag.usertype ,cag.actiondate, cag.ip,cag.port,  cd.dashboard_caption,       		" +
  		" crm.report_name ,ccm.component_caption,   " +
  		" case when cag.isemail = 1 then 'Y' else 'N' end    " +
  		" from cr_access_log cag ,   " +
  		" cr_dashboard cd, cr_report_master  crm ,cr_component_master ccm           , " +
  		" hrms_emp_offc heo,cr_client_users ccu  " +
  		" where trunc(cag.actiondate)>=to_date('" + fromDate + "','dd-mm-yy')   " +
  		" and trunc(cag.actiondate)<=to_date('" + toDate + "'  ,'dd-mm-yy')     " +
  		" and cd.dashboard_id=cag.dashboardid      " +
  		" and crm.report_id(+) =cag.reportid       " +
  		" and ccm.component_id(+)=cag.graphid      " +
  		" and heo.emp_id(+)=cag.empid       " +
  		" and ccu.cruser_code(+)=cag.clientid     " +
  		" order by cag.actiondate";
        branchData = getSqlModel().getSingleResult(branchDataQuery);

        Object[][] objTabularData = new Object[branchData.length][11];
        String[] columns = { "Sr.No", "Employee", "Client", "User Type", "Action Date" ,"IP","Port","DashBoard","Report ID","Graph Name","Email Send"};
        //String[] header={ "Sr.No", "EMPID", "Client ID", "User Type", "Action Date" ,"IP","Port","DashBoardID","Report ID","GraphID","ISEmail"};
        int[] cellWidthChallan = {6, 12, 12, 6, 15,13,6,12,10,10,12 };
        int[] cellAlignChallan = { 0, 0, 0, 1, 0,1,0,0,0,0,0 };
        int count = 1;
        if ((branchData != null) && (branchData.length > 0))
        { 
          TableDataSet branchDetails = new TableDataSet();
          branchDetails.setHeader(columns);
         
          branchDetails.setData(branchData);
          branchDetails.setCellWidth(cellWidthChallan);
          branchDetails.setCellAlignment(cellAlignChallan);
          branchDetails.setHeaderTable(true);
          branchDetails.setHeaderBorderDetail(3);
          branchDetails.setHeaderBorderColor(new BaseColor(0, 255, 0));
          branchDetails.setBorderDetail(3);

          for (int i = 0; i < branchData.length; i++) {
            objTabularData[i][0] = Integer.valueOf(count++);
            objTabularData[i][1] = String.valueOf(processDetail[i][0]);
            objTabularData[i][2] = String.valueOf(processDetail[i][1]);
            objTabularData[i][3] = String.valueOf(processDetail[i][2]);
            objTabularData[i][4] = String.valueOf(processDetail[i][3]);
            objTabularData[i][5] = String.valueOf(processDetail[i][4]);
            objTabularData[i][6] = String.valueOf(processDetail[i][5]);
            objTabularData[i][7] = String.valueOf(processDetail[i][6]);
            objTabularData[i][8] = String.valueOf(processDetail[i][7]);
            objTabularData[i][9] = String.valueOf(processDetail[i][8]);
            objTabularData[i][10] = String.valueOf(processDetail[i][9]);
          }

          TableDataSet tdstable = new TableDataSet();
          tdstable.setHeader(columns);
          tdstable.setData(objTabularData);
          tdstable.setHeaderBorderDetail(3);
          tdstable.setCellAlignment(cellAlignChallan);
          tdstable.setCellWidth(cellWidthChallan);
          tdstable.setBorderDetail(3);
          tdstable.setBlankRowsAbove(1);
          rg.addTableToDoc(tdstable);
        }
        else {
          TableDataSet noData = new TableDataSet();
          Object[][] noDataObj = new Object[1][1];
          noDataObj[0][0] = "Customer History Report : No records available";

          noData.setData(noDataObj);
          noData.setCellAlignment(new int[1]);
          noData.setCellWidth(new int[] { 100 });
          noData.setBlankRowsAbove(1);
          noData.setBorder(Boolean.valueOf(false));
          rg.addTableToDoc(noData);
        }
      }
      catch (Exception e) {
        logger
          .error("exception in challanDataMarDec object", 
          e);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rg;
  }
}