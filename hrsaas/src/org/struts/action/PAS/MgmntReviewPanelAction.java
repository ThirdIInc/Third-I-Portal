/**
 * 
 */
package org.struts.action.PAS;

import java.util.LinkedHashMap;

import org.paradyne.bean.PAS.MgmntReviewPanel;
import org.paradyne.model.PAS.MgmntReviewPanelModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0623
 *
 */
public class MgmntReviewPanelAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(MgmntReviewPanelAction.class);
	MgmntReviewPanel panel = null;
	
	public MgmntReviewPanel getPanel() {
		return panel;
	}

	public void setPanel(MgmntReviewPanel panel) {
		this.panel = panel;
	}

	@Override
	public void prepare_local() throws Exception {
		panel = new MgmntReviewPanel();
		panel.setMenuCode(2036);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return panel;
	}
	
	public String f9AppCal()throws Exception{
		
		String query = " SELECT  APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),"
			+ " TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID FROM PAS_APPR_CALENDAR "
			+ " ORDER BY APPR_ID";

		String[] headers = { getMessage("appraisal.code"),
				getMessage("appraisal.from"), getMessage("appraisal.to")};

		String[] headerWidth = { "50", "25", "25" };

		String[] fieldNames = { "apprCode", "frmDate", "toDate", "apprId"};

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "true";

		String submitToMethod = "MgmntReviewPanel_setPhaseDetails.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String setPhaseDetails() {
		MgmntReviewPanelModel model = new MgmntReviewPanelModel();
		model.initiate(context, session);
		setPhases();
		model.getGroupHeadRecords(panel, request);
		panel.setGrpHeadFlag(true);
		panel.setManagerFlag(false);
		model.terminate();
		return INPUT;
	}
	
	public String getEmpAppraisalDetails(){
		try {
			MgmntReviewPanelModel model = new MgmntReviewPanelModel();
			model.initiate(context, session);
			model.getEmployeeAppraisalDetails(panel,request);
			panel.setEnableAll("Y");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "employeeDetails";
	}


	public String getManagersUnderGroupHeads() {
		try {
			MgmntReviewPanelModel model = new MgmntReviewPanelModel();
			model.initiate(context, session);
			String groupHeadId = request.getParameter("groupHeadId");
			model.getManagersUnderGrpHeads(panel, request, groupHeadId);
			panel.setEnableAll("Y");
			setPhases();
			panel.setGrpHeadFlag(false);
			panel.setManagerFlag(true);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}
	public String setPhases(){
		MgmntReviewPanelModel model = new MgmntReviewPanelModel();
		model.initiate(context, session);
		LinkedHashMap<String, String> phaseMap = model.setPhaseDetails(panel);
		panel.setPhaseMap(phaseMap);
		return INPUT;
	}
	
	public String getEmployeeList(){
		MgmntReviewPanelModel model = new MgmntReviewPanelModel();
		model.initiate(context, session);
		String groupId=request.getParameter("groupId");
		String managerId=request.getParameter("managerId");
		
		model.getEmployeeList(panel, request,groupId);
		logger.info("groupId=="+groupId);
		logger.info("managerId=="+managerId);
		getNavigationPanel(2);
		panel.setEnableAll("Y");
		return "employeeList";
	}
	
	public String saveMgmntScore(){
		MgmntReviewPanelModel model = new MgmntReviewPanelModel();
		model.initiate(context, session);
		boolean result = model.saveMgmntScore(panel,request);
		logger.info("saveMgmntScore result=="+result);
		if(result){
			addActionMessage("Record saved successfully.");
		}else{
			addActionMessage("Error while saving the record");
		}
		model.getEmployeeList(panel, request,panel.getHidGroupId());
		panel.setEnableAll("Y");
		getNavigationPanel(2);
		model.terminate();
		return "employeeList";
	}
	
	public String generateReport(){
		try {
			MgmntReviewPanelModel model = new MgmntReviewPanelModel();
			model.initiate(context, session);
			String labels[]={getMessage("employee"),getMessage("kra.score"),getMessage("comp.score"),getMessage("total.score"),getMessage("mod.score"),getMessage("hr.action")};
			model.generateReport(panel, response,labels);
			panel.setEnableAll("Y");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;	
	}
	

}
