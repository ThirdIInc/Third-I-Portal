package org.struts.action.PAS;
/**
 * @modified by @author Prajakta.Bhandare
 * @date 25 April 2013
 */
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.AppraisalScoreBalancerReport;
import org.paradyne.model.PAS.AppraisalScoreBalancerReportModel;
import org.struts.lib.ParaActionSupport;

public class AppraisalScoreBalancerReportAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(AppraisalScoreBalancerReportAction.class);
  AppraisalScoreBalancerReport bean = null;

  public void prepare_local() throws Exception
  {
    this.bean = new AppraisalScoreBalancerReport();
    this.bean.setMenuCode(842);
  }

  public Object getModel()
  {
    return this.bean;
  }

  public void setAppraisalScoreBalancerReport(AppraisalScoreBalancerReport appraisalScoreBalancerReport)
  {
    this.bean = appraisalScoreBalancerReport;
  }

  public void prepare_withLoginProfileDetails() throws Exception
  {
    AppraisalScoreBalancerReportModel model = new AppraisalScoreBalancerReportModel();
    model.initiate(this.context, this.session);
    model.terminate();
  }

  public String input()
  {
    getNavigationPanel(1);
    return "input";
  }

  public String getReport()
  {
    AppraisalScoreBalancerReportModel model = new AppraisalScoreBalancerReportModel();
    model.initiate(this.context, this.session);
    model.getReport(this.request, this.response, this.bean);
    model.terminate();
    return null;
  }
  
  /** @author Prajakta.Bhandare
   * @return String 
   */
public String genReport(){
	  AppraisalScoreBalancerReportModel model = new AppraisalScoreBalancerReportModel();
	    model.initiate(this.context, this.session);
	    String reportPath="";
	    model.genReport(this.request, this.response, this.bean,reportPath);
	    model.terminate();
	    return null;
  }

  public String f9SelectAppraisal()
    throws Exception
  {
    String query = " SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), APPR_ID  FROM PAS_APPR_CALENDAR WHERE APPR_ID IN (SELECT APPR_ID FROM PAS_APPR_SCORE )ORDER BY APPR_ID ";

    String[] headers = { "Appraisal Code", "Start Date", "End Date" };

    String[] headerWidth = { "20", "30", "30" };

    String[] fieldNames = { "sAppCode", "sAppStartDate", "sAppEndDate", "sAppId" };

    int[] columnIndex = { 0, 1, 2, 3 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

    return "f9page";
  }
}