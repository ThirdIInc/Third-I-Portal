package org.struts.action.settlement;

import java.io.PrintStream;
import org.apache.log4j.Logger;
import org.paradyne.bean.settlement.ResignationApproval;
import org.paradyne.model.settlement.ResignationApprovalModel;
import org.struts.lib.ParaActionSupport;

public class ResignationApprovalAction extends ParaActionSupport
{
  static Logger logger = Logger.getLogger(ResignationApprovalAction.class);
  ResignationApproval resignApproval;

  public void prepare_local()
    throws Exception
  {
    this.resignApproval = new ResignationApproval();
    this.resignApproval.setMenuCode(1019);
  }

  public Object getModel()
  {
    return this.resignApproval;
  }

  public ResignationApproval getResignApproval() {
    return this.resignApproval;
  }

  public void setResignApproval(ResignationApproval resignApproval) {
    this.resignApproval = resignApproval;
  }

  public String callstatus()
  {
    ResignationApprovalModel model = new ResignationApprovalModel();
    model.initiate(this.context, this.session);
    System.out.println("hi------------");
    model.getAllTypeOfApplications(this.resignApproval, this.request, this.resignApproval.getUserEmpId());
    this.resignApproval.setListType("pending");
    model.terminate();
    return "success";
  }

  public String getApprovedList() throws Exception
  {
    try {
      ResignationApprovalModel model = new ResignationApprovalModel();
      model.initiate(this.context, this.session);
      model.getApprovedList(this.resignApproval, this.request, 
        this.resignApproval.getUserEmpId());
      this.resignApproval.setListType("approved");
      model.terminate();
    } catch (Exception e) {
      logger.error("Exception in getApprovedList------" + e);
    }
    return "success";
  }

  public String getRejectedList() throws Exception
  {
    try
    {
      ResignationApprovalModel model = new ResignationApprovalModel();
      model.initiate(this.context, this.session);
      model.getRejectedList(this.resignApproval, this.request, 
        this.resignApproval.getUserEmpId());
      this.resignApproval.setListType("rejected");
      model.terminate();
    } catch (Exception e) {
      logger.error("Exception in getRejectedList------" + e);
    }
    return "success";
  }
}