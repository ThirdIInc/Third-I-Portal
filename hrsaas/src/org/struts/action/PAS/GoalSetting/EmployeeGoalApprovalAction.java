package org.struts.action.PAS.GoalSetting;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.PAS.GoalSetting.EmployeeGoalApproval;
import org.paradyne.model.PAS.GoalSetting.EmployeeGoalApprovalModel;
import org.struts.lib.ParaActionSupport;

public class EmployeeGoalApprovalAction extends ParaActionSupport
{
  EmployeeGoalApproval goalApproval;

  public void prepare_local()
    throws Exception
  {
    this.goalApproval = new EmployeeGoalApproval();
    this.goalApproval.setMenuCode(1074);
  }

  public Object getModel()
  {
    return this.goalApproval;
  }

  public String input() {
    getPendingList();
    return "success";
  }

  public String getPendingList() {
    EmployeeGoalApprovalModel model = new EmployeeGoalApprovalModel();
    model.initiate(this.context, this.session);
    model.getEmpGoalList(this.goalApproval, "2", this.request);
    this.goalApproval.setListType("pending");
    model.terminate();
    return "success";
  }

  public String getApprovedList() {
    EmployeeGoalApprovalModel model = new EmployeeGoalApprovalModel();
    model.initiate(this.context, this.session);
    model.getEmpGoalListApproved(this.goalApproval, "3", this.request);
    this.goalApproval.setListType("approved");
    model.terminate();
    return "success";
  }
  public String reSubmitGoal() {
    EmployeeGoalApprovalModel model = new EmployeeGoalApprovalModel();
    model.initiate(this.context, this.session);
    String empGoalIdString = this.request.getParameter("goalIds");
    model.updateApprovalStatus(empGoalIdString, "6");
    model.terminate();
    return getApprovedList();
  }
}