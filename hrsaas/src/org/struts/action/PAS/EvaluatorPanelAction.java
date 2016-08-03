package org.struts.action.PAS;

import org.paradyne.bean.PAS.AppraisalMisReport;
import org.paradyne.bean.PAS.EvaluatorPanel;
import org.paradyne.model.PAS.AppraisalMisReportModel;
import org.paradyne.model.PAS.EvaluatorPanelModel;
import org.struts.lib.ParaActionSupport;

public class EvaluatorPanelAction extends ParaActionSupport {

	EvaluatorPanel evaluatorPanel;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		evaluatorPanel = new EvaluatorPanel();
		evaluatorPanel.setMenuCode(760);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return evaluatorPanel;
	}

	public EvaluatorPanel getEvaluatorPanel() {
		return evaluatorPanel;
	}

	public void setEvaluatorPanel(EvaluatorPanel evaluatorPanel) {
		this.evaluatorPanel = evaluatorPanel;
	}
	
	public String input(){
		String forwardStatus =""+(String)request.getAttribute("forwardStatus");
		try {
			if(forwardStatus.equals("true")){
				addActionMessage(getMessage("appraisal.form.head")+" forwarded successfully");
			}else if(forwardStatus.equals("false")){
				System.out.println("6<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<manager phase>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>6 ");
				addActionMessage("Error while forwarding the "+getMessage("appraisal.form.head")+" due to empty rating or blank comments.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		evaluatorPanel.setListType("pending");
		EvaluatorPanelModel model = new EvaluatorPanelModel();
		model.initiate(context, session);
		model.getAppraisalRecords(evaluatorPanel,request);
		model.getEligibleRecords(evaluatorPanel,request);
		model.terminate();
		return SUCCESS;
	}
	
	public String getProcessedList() {
		evaluatorPanel.setListType("processed");
		EvaluatorPanelModel model = new EvaluatorPanelModel();
		model.initiate(context, session);
		model.getProcessedList(evaluatorPanel,request);
		model.terminate();
		return SUCCESS;
	}
	
	/* method name : callPage
	 * purpose     : to displays the records in the form
	 * return type : String
	 * parameter   : none
	 */

	public String callPage() throws Exception {
		EvaluatorPanelModel model = new EvaluatorPanelModel();
		model.initiate(context, session);
		model.getAppraisalRecords(evaluatorPanel,request);
		model.terminate();
		return SUCCESS;

	}

	public String callPage1() throws Exception {
		EvaluatorPanelModel model = new EvaluatorPanelModel();
		model.initiate(context, session);
		model.getProcessedList(evaluatorPanel,request);
		model.terminate();
		return SUCCESS;

	}
	
	public String callView() throws Exception {
		/*String apprId = request.getParameter("apprId");
		String empId = request.getParameter("empId");
		String phaseCode = request.getParameter("phaseCode");
		EvaluatorPanelModel model = new EvaluatorPanelModel();
		model.initiate(context, session);
		model.getReport(request,response,empId,apprId,phaseCode,evaluatorPanel.getUserEmpId());
		model.terminate();*/
		
		String apprId = request.getParameter("apprId");
		String empId = request.getParameter("empId");
		String phaseCode = request.getParameter("phaseCode");
		
		AppraisalMisReportModel apprMismodel=new AppraisalMisReportModel();
		apprMismodel.initiate(context, session);
		
		String div_id="0";
		String divQuery="SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="+empId;
		Object data[][]=apprMismodel.getSqlModel().getSingleResult(divQuery);
		if(data!=null && data.length>0)
		{
			div_id=String.valueOf(data[0][0]);
		}
		String[] careerLabels = { getMessage("career.progression"),
				getMessage("career.general.comments") };
		AppraisalMisReport bean=new AppraisalMisReport();
		bean.setPhaseCode(phaseCode);
		bean.setReportType("EvaluationView");
		bean.setEmpId(empId);
		bean.setApprId(apprId);
		bean.setBranchId("");
		bean.setDeptId("");
		bean.setEmpTypeId("");
		bean.setDivisionId(div_id);
		
		apprMismodel.generatePreviewReport(request, response, bean, careerLabels);
		
		//apprMismodel.terminate();
		apprMismodel.terminate();
		return null;
	}
}
