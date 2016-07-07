/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.AnnualIncrement;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.AnnualIncrementModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0554
 *
 */
public class AnnualIncrementAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AnnualIncrementAction.class);
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	AnnualIncrement annualInc;
	@Override
	public void prepare_local() throws Exception {
		annualInc = new AnnualIncrement();
		annualInc.setMenuCode(1086);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return annualInc;
	}
	
	public String input() {
		AnnualIncrementModel model=new AnnualIncrementModel();
		model.initiate(context, session);
		model.getIncrementList(annualInc,request);
		model.terminate();
		return SUCCESS;
	}
	public String addNew() {
		AnnualIncrementModel model=new AnnualIncrementModel();
		model.initiate(context, session);
		model.getIncrementList(annualInc,request);
		model.terminate();
		return "annualIncrementData";
	}
	
	public String processIncrement() {
		AnnualIncrementModel model=new AnnualIncrementModel();
		model.initiate(context, session);
		String returnMsg = model.processIncrement(annualInc);
		if (returnMsg.equals("Increment processed successfully.")){
			addActionMessage(returnMsg);
			model.showIncrementDetails(annualInc,request);
			annualInc.setShowFlag(true);
			annualInc.setMonthView(Utility.month(Integer.parseInt(annualInc.getMonth())));
			annualInc.setIncrementStatus("UNLOCK");
		}else{
			addActionMessage(returnMsg);
			addNew();
		}
		model.terminate();
		return "annualIncrementData";
	}
	
	public String callForEdit(){
		AnnualIncrementModel model=new AnnualIncrementModel();
		model.initiate(context, session);
		logger.info("getIncrementCode()=="+annualInc.getIncrementCode());
		model.showIncrementDetails(annualInc,request);
		annualInc.setShowFlag(true);
		annualInc.setMonthView(Utility.month(Integer.parseInt(annualInc.getMonth())));
		model.terminate();
		return "annualIncrementData";
	}
	public String deleteAndReProcess(){
		AnnualIncrementModel model=new AnnualIncrementModel();
		model.initiate(context, session);
		logger.info("getIncrementCode()=="+annualInc.getIncrementCode());
		String incrementStatus = annualInc.getIncrementStatus();
		if(!(incrementStatus.equals("LOCK"))){
		boolean result =model.deleteIncrement(annualInc);
		if(result){
			annualInc.setIncrementCode("");
			processIncrement();
		}
		}else{
			addActionMessage("Increment records already locked");
		}
		model.terminate();
		return "annualIncrementData";
	}
	public String showSavedRecords(){
		AnnualIncrementModel model=new AnnualIncrementModel();
		model.initiate(context, session);
		logger.info("getIncrementCode()=="+annualInc.getIncrementCode());
		model.showIncrementDetails(annualInc,request);
		annualInc.setShowFlag(true);
		annualInc.setMonthView(Utility.month(Integer.parseInt(annualInc.getMonth())));
		model.terminate();
		return "annualIncrementData";
	}
	
	public String f9Division() {
		try {
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			if(annualInc.getUserProfileDivision() != null && annualInc.getUserProfileDivision().length() > 0) {
				query += " WHERE DIV_ID IN(" + annualInc.getUserProfileDivision() + ") ";
			}
			query += " ORDER BY UPPER(DIV_NAME) ";

			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divisionName", "divisionId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			return "";
		}
	} //end of f9Div
	
	/**
	 * Opens a popup window containing a list of all branches
	 * @return String f9page
	 */
	public String f9Branch() {
		try {
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"branchName", "branchId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			return "";
		}
	}
	
	public String f9Department() {
		try {
			String query = " SELECT DEPT_NAME, DEPT_ID FROM HRMS_DEPT ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"departmentName", "departmentId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			return "";
		}
	}
	
	public String f9searchAction() {
		try {
			String query = " SELECT DECODE(INCREMENT_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') MONTH,"  
						+" INCREMENT_YEAR, DEPT_NAME, CENTER_NAME, DIV_NAME, DECODE(INCREMENT_STATUS,'U','UNLOCK','L','LOCK','UNLOCK'), "
						+" DEPT_ID,CENTER_ID,DIV_ID, INCREMENT_ID,INCREMENT_MONTH "
						+" FROM HRMS_INCREMENT_HDR  "
						+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID =  INCREMENT_DEPT) "
						+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID =  INCREMENT_BRANCH)  "
						+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =  INCREMENT_DIVISION) ";
		
					if(annualInc.getUserProfileDivision() != null && annualInc.getUserProfileDivision().length() > 0) {
						query += " AND DIV_ID IN(" + annualInc.getUserProfileDivision() + ") ";
					}
					
					query	+=  " ORDER BY INCREMENT_YEAR DESC, INCREMENT_MONTH DESC ";

			String[] headers = {getMessage("month"),getMessage("year"),getMessage("department"),getMessage("branch"),getMessage("division"),
					getMessage("incrementStatus")};

			String[] headerWidth = {"15","15","20","20","20","10"};

			String[] fieldNames = {"monthView", "year","departmentName","branchName","divisionName","incrementStatus","departmentId","branchId","divisionId","incrementCode","month"};

			int[] columnIndex = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

			String submitFlag = "true";

			String submitToMethod = "AnnualIncrement_callForEdit.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		} catch (Exception e) {
			return "";
		}
	}
	public String saveAfterRecalculate() throws Exception {
		try {
			boolean result =false;
			AnnualIncrementModel model = new AnnualIncrementModel();
			model.initiate(context,session);
			//for getting server path where configuration files are saved.
		
			
			String incrementStatus = annualInc.getIncrementStatus();
			if(!(incrementStatus.equals("LOCK"))){
				result = model.saveIncrementRecords(annualInc,request);
				if(result){
					addActionMessage("Records saved successfully.");
				}
				
			}else {
				addActionMessage("Increment records already locked");
			}
			
			model.terminate();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return showSavedRecords();
	} //end of method saveNonIndustrialSalaries
	
	public String lockIncrement() throws Exception {
		boolean result =false;
		AnnualIncrementModel model = new AnnualIncrementModel();
		model.initiate(context,session);
		try {
			result = model.lockIncrement(annualInc);
			if(result){
				addActionMessage("Increment locked successfully");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return showSavedRecords();
	}

}
