package org.struts.action.CR;

import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.CR.DataReconcile;
import org.paradyne.model.CR.DataReconcileModel;
import org.struts.lib.ParaActionSupport;

public class DataReconcileAction extends ParaActionSupport
{
  private static final long serialVersionUID = 1L;
  DataReconcile bean = null;

  static Logger logger = Logger.getLogger(ParaActionSupport.class);

  public DataReconcile getBean()
  {
    return this.bean;
  }

  public void setTm(DataReconcile bean) {
    this.bean = bean;
  }

  public Object getModel()
  {
    return this.bean;
  }

  public String input()
    throws Exception
  {
    this.bean.setTableFlagCheckedCount(false);
    this.bean.setFilters(false);
    this.bean.setFiltersDefault(false);
    this.bean.setDataReconcileFlag(false);
    this.bean.setSourceId("");
    this.bean.setSourceName("");
    System.out.println("22 Datareconcile Action is called----------------------------------");
    String D1_URL = getText("D1_PeoplepowerCRM_URL") + "cr/DataReconcile_input.action";
    System.out.println(D1_URL);
    this.response.sendRedirect(D1_URL);
    return "success";
  }

  public String addNew()
  {
    try
    {
      getNavigationPanel(2);
      return "clientData";
    } catch (Exception e) {
      logger.error("Exception in addNew in action:" + e);
    }return null;
  }

  public void prepare_local()
    throws Exception
  {
    this.bean = new DataReconcile();
  }

  public void prepare_withLoginProfileDetails()
    throws Exception
  {
    DataReconcileModel model = new DataReconcileModel();
    model.initiate(this.context, this.session);

    model.terminate();
  }

  public String callPage()
    throws Exception
  {
    DataReconcileModel model = new DataReconcileModel();
    model.initiate(this.context, this.session);

    getNavigationPanel(1);
    model.terminate();
    return "success";
  }

  public String displayColumns()
  {
    try
    {
      String source = this.request
        .getParameter("sourceId");
      DataReconcileModel model = new DataReconcileModel();
      model.initiate(this.context, this.session);

      model.displayColumns(this.bean, this.request, source);
      this.bean.setTableFlagCheckedCount(true);
      this.bean.setFilters(true);
      this.bean.setFiltersDefault(true);
      this.bean.setDataReconcileFlag(false);
      this.bean.setSourceId(source);
      model.terminate();
    }
    catch (Exception e) {
      logger.error(e);
      e.printStackTrace();
    }
    return "success";
  }

  public String displayColumnsWithData()
  {
    try
    {
      DataReconcileModel model = new DataReconcileModel();
      model.initiate(this.context, this.session);
      String source = this.bean.getSourceId();
      model.displayColumns(this.bean, this.request, source);
      this.bean.setTableFlagCheckedCount(true);
      this.bean.setFilters(true);
      this.bean.setFiltersDefault(false);
      this.bean.setSourceId(source);

      model.terminate();
    }
    catch (Exception e) {
      logger.error(e);
      e.printStackTrace();
    }

    return "success";
  }

  public String hasData()
  {
    addActionMessage("");
    DataReconcileModel model = new DataReconcileModel();
    model.initiate(this.context, this.session);
    String source = this.bean.getSourceId();

    boolean result = model.hasData(this.bean, this.request, source);

    this.bean.setDataReconcileFlag(true);
    displayColumnsWithData();
    model.terminate();
    return "success";
  }

  public String updateData()
  {
    DataReconcileModel model = new DataReconcileModel();
    model.initiate(this.context, this.session);

    String source = this.bean.getSourceId();
    boolean result = model.updateData(this.bean, this.request, source);
    hasData();
    if (result)
    {
      addActionMessage("Records Updated successfully.");
    }
    else
    {
      addActionMessage("Error while updating record");
    }
    displayColumnsWithData();
    model.terminate();
    return "success";
  }

  public String getDataReconciliation()
    throws Exception
  {
    addActionMessage("");
    DataReconcileModel model = new DataReconcileModel();
    model.initiate(this.context, this.session);

    String source = this.bean.getSourceId();
    boolean result = model.getDataReconciliation(this.bean, this.request, source);
    if (result)
    {
      this.bean.setDataReconcileFlag(true);
      displayColumnsWithData();
    }
    else {
      addActionMessage(getMessage("Filter data is incorrect"));
    }
    model.terminate();
    return "success";
  }

  public String dataSource()
    throws Exception
  {
    String query = " SELECT SOURCE_ID,SOURCE_NAME FROM  cr_datasource  ORDER BY  SOURCE_ID ";

    String[] headers = { getMessage("source.id"), getMessage("source.name") };

    String[] headerWidth = { "20", "80" };

    String[] fieldNames = { "sourceId", "sourceName" };

    int[] columnIndex = { 0, 1 };

    String submitFlag = "false";

    String submitToMethod = "";

    setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod, "POOL_D1");

    return "f9page";
  }
}