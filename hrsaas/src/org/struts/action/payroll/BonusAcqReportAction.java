package org.struts.action.payroll;

 import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.payroll.*;

import org.paradyne.model.payroll.BonusAcqReportModel;
import org.paradyne.model.payroll.EmpBonusModel;

public class BonusAcqReportAction extends ParaActionSupport {

	BonusAcqReport bonusAcq = null;

	 public Object getModel() {

		return bonusAcq;
	}

	public String report() {

		BonusAcqReportModel model = new BonusAcqReportModel();

		model.initiate(context,session);

		model.generateReport(bonusAcq,response);

		model.terminate();

		return null;
	}
	

	public BonusAcqReport getBonusAcq() {
		return bonusAcq;
	}

	public void setBonusAcq(BonusAcqReport bonusAcq) {
		this.bonusAcq = bonusAcq;
	}

	public String f9Page() {
		return "f9page";
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		
		bonusAcq = new BonusAcqReport();
		
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

		String[] fieldName = {	"bonusAcq.bonusCode", "bonusAcq.bonusFrom",
								"bonusAcq.bonusTo", "bonusAcq.bonusType",
								"bonusAcq.bonusDays", "bonusAcq.bonusEmptype",
								"bonusAcq.typeCode"};

		String submitFlag = "false";

		String submitToMethod = "";

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6};

		setF9Window(sql, headers, headerWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9actionBonusPaybill() {

		//String sql = "	SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL ORDER BY PAYBILL_ID ";
		
String sql = "	SELECT DISTINCT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL INNER join  HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_PAYBILL=HRMS_PAYBILL.PAYBILL_ID)";
		
		sql+=getprofilePaybillQuery(bonusAcq);
		
		sql+= "ORDER BY PAYBILL_ID ";
		
		
		String[] headers = {"PayBill Id","PayBill Group"};

		String[] headerWidth = { "20","30" };

		String[] fieldName = {	"bonusAcq.paybillId", "bonusAcq.bonusPaybill"};

		String submitFlag = "false";

		String submitToMethod = "";

		int[] columnIndex = { 0, 1};

		setF9Window(sql, headers, headerWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	
}
