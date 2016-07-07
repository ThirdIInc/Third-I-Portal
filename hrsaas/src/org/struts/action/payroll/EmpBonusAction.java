/**
 * 
 */
package org.struts.action.payroll;

 import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.payroll.*;
import org.paradyne.model.leave.LeaveMisReportModel;
import org.paradyne.model.payroll.BonusParaModel;
import org.paradyne.model.payroll.EmpBonusModel;

/**
 * @author Ibrahim
 * 
 */
public class EmpBonusAction extends ParaActionSupport {

	/**
	 * 
	 */

	EmpBonus empBonus;

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		empBonus = new EmpBonus();
	}

	public EmpBonusAction() {
		// TODO Auto-generated constructor stub
	}

	public EmpBonus getEmpBonus() {
		return empBonus;
	}

	 public Object getModel() {
		return empBonus;
	}

	public void setEmpBonus(EmpBonus empBonus) {
		this.empBonus = empBonus;
	}

	public String save() {

		EmpBonusModel model = new EmpBonusModel();

		model.initiate(context,session);

		String[] empIdVals = request.getParameterValues("cEmpId");
	 
		String[] empBonDaysVals = request.getParameterValues("cEmpBonusDays");

		String[] empAmtVals = request.getParameterValues("cAmount");
		
		String payBill = empBonus.getPaybillId();
		
	  
       boolean result = false;
       result= model.saveList(empBonus,empIdVals,empBonDaysVals,empAmtVals);
       
      if(result==true)
      {
    	 addActionMessage("Record Saved Successfully"); 
    	 
      }else{
    	  addActionMessage("Record cannot be added"); 
      }
      if(result)
		{
			reset();
		}
   
		model.terminate();

		return SUCCESS;
	}

	
	

	public String reset() {

	EmpBonusModel model = new EmpBonusModel();
	empBonus.setBonusDays("");
	empBonus.setBonusEmptype("");
	empBonus.setBonusType("");
	empBonus.setBonusFrom("");
	empBonus.setBonusTo("");
	empBonus.setBonusPaybill("");
	return "success";
	
	}
	
	public String view() {

		EmpBonusModel model = new EmpBonusModel();

		model.initiate(context,session);

		Object[][] result=model.viewData(empBonus);
		if(result.length==0){
			addActionMessage("The Bonus has not been processed.");
		}
		empBonus.setFlag("true");
		model.terminate();

		return SUCCESS;
	}



	public String process() {

		EmpBonusModel model = new EmpBonusModel();

		model.initiate(context,session);

		model.getTableData(empBonus,context);

		empBonus.setFlag("true");
		empBonus.setEmpFlag("true");
		model.terminate();
	
		

		return SUCCESS;
	}

	public String f9actionBonusParam() {

		String sql = " SELECT DISTINCT HRMS_BONUS_PARAMETER.BONUS_CODE, "+
					" TO_CHAR(HRMS_BONUS_PARAMETER.BONUS_PERIOD_FROM,'DD-MM-YYYY'),"+
					" TO_CHAR(HRMS_BONUS_PARAMETER.BONUS_PERIOD_TO,'DD-MM-YYYY'), "+
					" HRMS_BONUS_PARAMETER.BONUS_TYPE, "+
					" NVL(HRMS_BONUS_PARAMETER.BONUS_DAYS_DECLARED,''), "+
					" NVL(HRMS_EMP_TYPE.TYPE_NAME,' '), "+
         			" HRMS_EMP_TYPE.TYPE_ID "+
        
					" FROM HRMS_BONUS_PARAMETER "+
	
					" LEFT JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_BONUS_PARAMETER.BONUS_EMP_TYPE)" + 
	
					" ORDER BY HRMS_BONUS_PARAMETER.BONUS_CODE ";

		String[] headers = {"Bonus Code", "Bonus From", "Bonus To",
							"Bonus Type","Bonus Days", "Bonus Emp Type Name"
							};

		String[] headerWidth = { "15", "15", "15", "15", "20", "20" };

		String[] fieldName = {	"empBonus.bonusCode", "empBonus.bonusFrom",
								"empBonus.bonusTo", "empBonus.bonusType",
								"empBonus.bonusDays", "empBonus.bonusEmptype",
								"empBonus.typeCode"};

		String submitFlag = "false";

		String submitToMethod = "";

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6};

		setF9Window(sql, headers, headerWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9actionBonusPaybill() {

		String sql = "	SELECT DISTINCT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL INNER join  HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_PAYBILL=HRMS_PAYBILL.PAYBILL_ID)";
		
		sql+=getprofilePaybillQuery(empBonus);
		
		sql+=" ORDER BY PAYBILL_ID ";
		
		logger.info("query=============================================@@@@@@@@@@@@@@@@@@@@@@@@@@"+sql);

		String[] headers = {"PayBill Id","PayBill Group"};

		String[] headerWidth = { "20","30" };

		String[] fieldName = {	"empBonus.paybillId", "empBonus.bonusPaybill"};

		String submitFlag = "false";

		String submitToMethod = "";

		int[] columnIndex = { 0, 1};

		setF9Window(sql, headers, headerWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	
}
