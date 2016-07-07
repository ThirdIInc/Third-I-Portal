package org.paradyne.model.payroll;
import org.paradyne.bean.payroll.SalaryLedger;
import java.util.*;

/*
 * Author:Pradeep Kumar Sahoo
 * Date:02.08.2007
 */

 import org.paradyne.lib.ModelBase;

public class SalaryLedgerModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	SalaryLedger sl=null;
	
	public String checkNull(String result){
		if(result == null ||result.equals("null")){
			return "";
		} else{
			return result;
		}
	}
	
	/*The following function is called inside the 
	 * action class function go
	 */
	
	public void  getSalDetails(SalaryLedger sl,String queryStr)
	{
	Object[] beanObj = new Object[2];
	beanObj[0] =sl.getMonth();
	logger.info("Values of month"+beanObj[0]);
	beanObj[1] =sl.getYear();
	logger.info("Values of year"+beanObj[1]);
	
	
	Object[][] param = getSqlModel().getSingleResult(queryStr);
	
	
	if(param.length > 0) {
		sl.setFlag(true);//It points to whether there is data or not for that particular month and year when go button is clicked on the screen
		

	
	ArrayList<Object> salDet=new ArrayList<Object>();
	for(int i=0;i<param.length;i++) {
	
		
	String query= " SELECT " //HRMS_PAYBILL.PAYBILL_ID,HRMS_PAYBILL.PAYBILL_GROUP "
				+" CASE "
				+" WHEN SAL_LOCK='L' THEN 'Locked'  "
				+" WHEN SAL_LOCK='P' THEN 'Processed' "
				+" ELSE 'Pending' "
				+" END "
				+" FROM HRMS_SAL_LEDGER  "//HRMS_PAYBILL "
				//+" LEFT JOIN HRMS_SAL_LEDGER ON(HRMS_SAL_LEDGER.SAL_PAYBILL=HRMS_PAYBILL.PAYBILL_ID AND"  
				+" WHERE SAL_MONTH= "+String.valueOf(beanObj[0])+"  " +
				" AND SAL_YEAR="+String.valueOf(beanObj[1])+" AND HRMS_SAL_LEDGER.SAL_EMP_TYPE="+String.valueOf(param[i][0]) + " " +
				" AND HRMS_SAL_LEDGER.SAL_PAYBILL="+String.valueOf(param[i][1])  ;
	
	Object[][] values = getSqlModel().getSingleResult(query);
			
		SalaryLedger bean=new SalaryLedger();
			
		bean.setPayBillNo(checkNull(String.valueOf(param[i][1])));//Paybill code
		bean.setPayGrp(checkNull(String.valueOf(param[i][2])));//Pay bill group name
		bean.setTypeCode(checkNull(String.valueOf(param[i][0])));//Employee type code
		
	
		
		bean.setEmpType(checkNull(String.valueOf(param[i][3])));
		
	
		if (values.length >0) {
			 if(String.valueOf(values[0][0]).equals("Locked")) { //If status is L the button on Salary Ledger Form 
				 bean.setChkStatus(true);						 //will be disabled	
			   } else {
				bean.setChkStatus(false);
			          }
			bean.setStatus(String.valueOf(values[0][0]));//Status
		}else{
			bean.setStatus("Pending");
		     }
		salDet.add(bean);
		
		
		sl.setSalList(salDet);
	

		}
	 }
   }
}

