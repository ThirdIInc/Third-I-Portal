package org.struts.action.CR;

import org.apache.log4j.Logger;
import org.paradyne.bean.CR.CustomerHistoryReportBean;
import org.paradyne.model.CR.CustomerHistoryReportModel;
import org.struts.lib.ParaActionSupport;

public class CustomerHistoryReportAction extends ParaActionSupport
{
  private static final String F9_PAGE = "f9page";
  private static final String SUBMIT_FLAG_FALSE = "false";
  private static Logger logger = Logger.getLogger(CustomerHistoryReportAction.class);
  private CustomerHistoryReportBean bean;

  public void prepare_local()
    throws Exception
  {
    this.bean = new CustomerHistoryReportBean();
    this.bean.setMenuCode(5044);
  }

  public Object getModel()
  {
    return this.bean;
  }

  public String getReport()
  {
    CustomerHistoryReportModel model = new CustomerHistoryReportModel();
    model.initiate(this.context, this.session);
    String reportPath = "";
    model.getReport(this.bean, this.response, this.request, reportPath);
    model.terminate();
    return null;
  }

  public CustomerHistoryReportBean getBean()
  {
    return this.bean;
  }

  public void setBean(CustomerHistoryReportBean bean)
  {
    this.bean = bean;
  }

  public String reset()
  {
    return "success";
  }

  public String input() {
    return "input";
  }
}