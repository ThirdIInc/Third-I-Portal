/**
 * 
 */
package org.struts.action.lta;

import org.paradyne.bean.lta.LTACalculation;
import org.paradyne.model.lta.LTATaxCalculationModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0554
 *
 */
public class LTATaxCalculationAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.action.lta.LTATaxCalculationAction.class);
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	LTACalculation ltaCalc;
	public void prepare_local() throws Exception {
		ltaCalc=new LTACalculation();
		ltaCalc.setMenuCode(1013);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return ltaCalc;
	}
	
	public String reset(){
		ltaCalc.setEmpCode("");
		ltaCalc.setEmpName("");
		ltaCalc.setEmpToken("");
		ltaCalc.setBlockYearFrom("");
		ltaCalc.setBlockYearTo("");
		ltaCalc.setClaimAmt("");
		ltaCalc.setClaimDate("");
		ltaCalc.setYearOfExemption("");
		ltaCalc.setYearOfExemptionTo("");
		ltaCalc.setYearOfVisit("");
		ltaCalc.setClaimType("S");
		ltaCalc.setIsTaxExempted("Y");
		ltaCalc.setLtaId("");
		
		return SUCCESS;
	}
	
	
	public String viewEmpOnScreen(){
		LTATaxCalculationModel model =new LTATaxCalculationModel();
		model.initiate(context, session);
		//model.showApplicableEmpList(ltaCalc,request);
		model.terminate();
		return SUCCESS;
	}
	public String addNew(){
		getNavigationPanel(2);
		reset();
		ltaCalc.setEnableAll("Y");
		ltaCalc.setIsOnload("false");
		return SUCCESS;
	}
	/*public String reset(){
		
		return SUCCESS;
	}*/
	
	public String edit(){
		getNavigationPanel(2);
		ltaCalc.setEnableAll("Y");
		ltaCalc.setIsOnload("false");
		return SUCCESS;
	}
	public String delete(){
		LTATaxCalculationModel model= new LTATaxCalculationModel();
		model.initiate(context, session);
		boolean result= model.delete(ltaCalc);
		if(result){
			addActionMessage(getMessage("delete"));
			getNavigationPanel(1);
			reset();
			ltaCalc.setEnableAll("Y");
			ltaCalc.setIsOnload("true");
			model.showLtaEmpList(ltaCalc,request);
		}else{
			addActionMessage(getMessage("delete.error"));
			getNavigationPanel(3);
			ltaCalc.setEnableAll("N");
			ltaCalc.setIsOnload("false");
			
			model.showLtaRecord(ltaCalc);
		}
		
		model.terminate();
		return SUCCESS;
	}
	public String editOnDoubleClick(){
		getNavigationPanel(3);
		LTATaxCalculationModel model= new LTATaxCalculationModel();
		model.initiate(context, session);
		logger.info("getparameter ltaId ==="+request.getParameter("ltaId"));
		ltaCalc.setLtaId(request.getParameter("ltaId"));
		model.showLtaRecord(ltaCalc);
		ltaCalc.setEnableAll("N");
		ltaCalc.setIsOnload("false");
		model.terminate();
		return SUCCESS;
	}
	public String f9empAction(){
		String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ "HRMS_EMP_OFFC.EMP_LNAME),EMP_ID FROM HRMS_EMP_OFFC  WHERE EMP_STATUS='S' ORDER BY EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "empToken", "empName", "empCode" };

		int[] columnIndex = { 0, 1, 2 };

		/*
		 * String submitFlag = "true";
		 * 
		 * 
		 * String submitToMethod="AssetEmployee_showDetails.action";
		 */
		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

	return "f9page";
	}
	
	public String f9LTAAction(){
		String query = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME,"  
				+" LTA_BLOCK_FROM,LTA_BLOCK_TO, LTA_YEAROFVISIT, LTA_ID," 
				+" HRMS_LTA_TAXCALC.EMP_ID FROM HRMS_LTA_TAXCALC  LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LTA_TAXCALC.EMP_ID)";

		String[] headers = { getMessage("employee.id"), getMessage("employee"),getMessage("blockYearFromLabel"),getMessage("blockYearFromLabel"),
				getMessage("yearOfVisitLabel") };

		String[] headerWidth = { "10", "45","15","15","15" };

		String[] fieldNames = { "empToken", "empName","blockYearFrom","blockYearTo","yearOfVisit","ltaId","empCode" };

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6 };

		/*
		 * String submitFlag = "true";
		 * 
		 * 
		 * String submitToMethod="AssetEmployee_showDetails.action";
		 */
		String submitFlag = "true";

		String submitToMethod = "LTATaxCalculation_showLtaRecord.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

	return "f9page";
	}
	public String input(){
		LTATaxCalculationModel model =new LTATaxCalculationModel();
		model.initiate(context, session);
		model.showLtaEmpList(ltaCalc,request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	
	public String back(){
		LTATaxCalculationModel model =new LTATaxCalculationModel();
		model.initiate(context, session);
		reset();
		model.showLtaEmpList(ltaCalc,request);
		getNavigationPanel(1);
		ltaCalc.setIsOnload("true");
		ltaCalc.setEnableAll("Y");
		model.terminate();
		return SUCCESS;
	}
	
	public String showLtaRecord(){
		LTATaxCalculationModel model =new LTATaxCalculationModel();
		model.initiate(context, session);
		model.showLtaRecord(ltaCalc);
		getNavigationPanel(3);
		ltaCalc.setIsOnload("false");
		model.terminate();
		return SUCCESS;
	}
	
	public String save(){
		LTATaxCalculationModel model =new LTATaxCalculationModel();
		model.initiate(context, session);
		boolean result=false;
		String msg="";
		msg = model.checkYearValidation(ltaCalc);
		msg = model.checkExemptYearValidation(ltaCalc,msg);
		logger.info("msg=="+msg);
		if(msg.equals("true")){
			result = model.save(ltaCalc);
			if(result){
				addActionMessage(getMessage("save"));
				ltaCalc.setEnableAll("N");
			}else{
				addActionMessage(getMessage("save.error"));
				ltaCalc.setEnableAll("Y");
			}
			
		}else{
			addActionMessage(msg);
			ltaCalc.setEnableAll("N");
		}
		
		getNavigationPanel(3);
		
		ltaCalc.setIsOnload("false");
		model.terminate();
		return SUCCESS;
	}

}
