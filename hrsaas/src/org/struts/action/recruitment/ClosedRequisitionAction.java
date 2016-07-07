package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.ClosedRequisitionBean;
import org.paradyne.model.recruitment.ClosedRequisitionModel;
import org.struts.lib.ParaActionSupport;

public class ClosedRequisitionAction extends ParaActionSupport {
	
	/**
	 * @author AA1363
	 */
	private static final long serialVersionUID = 1L;
	ClosedRequisitionBean crBean;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		crBean =new ClosedRequisitionBean();
		crBean.setMenuCode(1132);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return crBean;
	}

	public ClosedRequisitionBean getCrBean() {
		return crBean;
	}

	public void setCrBean(ClosedRequisitionBean crBean) {
		this.crBean = crBean;
	}
	
	public String input() throws Exception {
		
		ClosedRequisitionModel model = new ClosedRequisitionModel();
		model.initiate(context, session);
		model.terminate();
		return "input";
	}
	
	public String reset() throws Exception{
		
		crBean.setReqCode("");
		crBean.setReqName("");
		crBean.setReqStatus("");
		crBean.setReqStatus("");
		return "input";
	}
	
	public String closeStatus() throws Exception{
		
		ClosedRequisitionModel model = new ClosedRequisitionModel();
		model.initiate(context, session);
		boolean result;
		try{
		
		if (!(crBean.getReqCode().equals(""))){
			result=model.updateStatus(crBean);
			if(result){
				addActionMessage("Requisition Status closed successfully");
				reset();
			}//end of if
			else{
				addActionMessage("Requisition Status cannot be updated");
				reset();
			}//end of else
		}//end of nested if
		model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "input";
	}
	
	
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " select REQS_NAME, REQS_CODE from HRMS_REC_REQS_HDR where REQS_STATUS='O' ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("req.name"), getMessage("req.code")};

		String[] headerWidth = {"50", "50"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = {"reqName","reqCode" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = {0,1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
