package org.struts.action.admin.increment;

import org.paradyne.bean.admin.increment.AnnualIncrement;
import org.paradyne.model.admin.increment.AnnualIncrementModel;
import org.paradyne.model.admin.increment.PeriodicalIncrementModel;


/**
 * @author radha 
 *
 */
public class PeriodicalIncrementAction extends org.struts.lib.ParaActionSupport {
	
	AnnualIncrement annIncre;
	
	
/**
	 * @return the annIncre
	 */
	public AnnualIncrement getAnnIncre() {
		return annIncre;
	}

	/**
	 * @param annIncre the annIncre to set
	 */
	public void setAnnIncre(AnnualIncrement annIncre) {
		this.annIncre = annIncre;
	}

public Object getModel(){
		
		return annIncre;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		annIncre = new AnnualIncrement();
		//annIncre.setMenuCode(162);
	}
public String report()throws Exception{
		
		logger.info("qwe");
		PeriodicalIncrementModel model = new PeriodicalIncrementModel();
		model.initiate(context,session);
		model.getReport(annIncre,response);
		model.terminate();
		return null ;
	}
	
public String f9empaction() throws Exception {
	/**
	 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
	 */
	String query = "SELECT EMP_ID,TO_CHAR(TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),EMP_CENTER,TO_CHAR(CENTER_NAME)"
						+" FROM HRMS_EMP_OFFC "
						+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
						+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
						+" ORDER BY EMP_ID ";		
				
	
	/**
	 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
	 */ 
	String[] headers={"Emp code","Emp Name"};
	
	String[] headerWidth={"10", "30"};
	
	/**
	 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
	 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
	 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
	 * */ 	
	
	String[] fieldNames={"annIncre.empID","annIncre.empName"};
	
	/**
	 * 	 	SET THE COLUMN INDEX
	 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
	 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
	 * 			THEN THE COLUMN INDEX CAN BE {1,3}
	 * 		
	 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
	 * 
	 */ 
	int[] columnIndex={0,1};
	
	/**
	 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
	 * 
	 */
	String submitFlag = "false";
	
	/**		 
	 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
	 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
	 */
	String submitToMethod="";
	
	/**
	 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
	 */
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	
	return "f9page";
}
	
	
}	
	
	