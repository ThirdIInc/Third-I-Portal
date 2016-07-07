/**
 * 
 */
package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.DebitHeadMaster;
import org.paradyne.model.admin.master.DebitHeadModel;
import org.struts.lib.ParaActionSupport;


/**
 * @author lakkichand
 * @date 25 APR 2007
 */
public class DebitHeadMasterAction extends ParaActionSupport {
	DebitHeadMaster debMaster=null;

	
	/**
	 * @return the debMaster
	 */
	public DebitHeadMaster getDebMaster() {
		return debMaster;
	}

	/**
	 * @param debMaster the debMaster to set
	 */
	public void setDebMaster(DebitHeadMaster debMaster) {
		this.debMaster = debMaster;
	}

	public Object getModel(){
		return debMaster;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		debMaster=new DebitHeadMaster();	
	}
	public void prepare(){
		debMaster=new DebitHeadMaster();
		debMaster.setMenuCode(29);
	}
	
	public String save()throws Exception{
		DebitHeadModel model=new DebitHeadModel();
		model.initiate(context,session);
		boolean result;
		if(debMaster.getDebitCode().equals("")){
			result=model.addDebit(debMaster);
		}
		else{
			result=model.modDebit(debMaster);
		}
		model.terminate();
		if(result){
			addActionMessage(getText("addMessage",""));
		}
		else{
			addActionMessage("Debit can not be added");
		}
		
		return reset();
	}
	
	public String reset()throws Exception{
		try{
			debMaster.setDebitCode("");
			debMaster.setDebitName("");
			debMaster.setDebitAbbr("");
			debMaster.setAuthority("");
			debMaster.setAutorDate("");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
	
	
	public String delete()throws Exception{
		DebitHeadModel model=new DebitHeadModel();
		model.initiate(context,session);
		boolean result=model.deleteDebit(debMaster);
		model.terminate();
		debMaster.setDebitCode("");
		debMaster.setDebitName("");
		debMaster.setDebitAbbr("");
		debMaster.setAuthority("");
		debMaster.setAutorDate("");
		
		if(result){
			addActionMessage(getText("delMessage",""));
		}
		else{
			addActionMessage("Debit can not be deleted");
		}
		return "success";
		
	}
	
	public String debitRecord()throws Exception{
		DebitHeadModel model=new DebitHeadModel();
		model.initiate(context,session);
		model.getDebitRecord(debMaster);
		model.terminate();
		return"success";
	}
	
	public String report()throws Exception{
		DebitHeadModel model=new DebitHeadModel();
		model.initiate(context,session);
		model.getDebitReport(debMaster);
		model.terminate();
		return"report";
	}
	
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT  DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Debit Code", "Debit Name"};
		
		String[] headerWidth={"20", "20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"debMaster.debitCode","debMaster.debitName"};
		
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
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="DebitHeadMaster_debitRecord.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
 
	 

	
	
}
