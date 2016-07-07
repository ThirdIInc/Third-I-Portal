/**
 * @author Diptimayee Pandey
 * 08-08-2008
 */
package org.struts.action.admin.master;
import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.admin.master.Journey;
import org.paradyne.model.admin.master.JourneyModel;



public class JourneyMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	Journey journey;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		journey=new Journey();
		journey.setMenuCode(652);
	}

	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return journey;
	}
	
	public String save()throws Exception
	{
		
		String [] jClass=request.getParameterValues("jClass");
		
		boolean result;
		JourneyModel model= new JourneyModel();
		logger.info("hi........in save");
		model.initiate(context,session);
		if(journey.getHiddenMode().equals("")){
			result=model.addMode(journey,jClass);
			if (result) {
				addActionMessage(getText("addMessage", ""));
			} else {
				addActionMessage("Duplicate entry found.");
			}
		} else {
		result=model.modMode(journey,jClass);
			if (result) {
			addActionMessage("Record updated succesfully.");
		} else {
			addActionMessage("Duplicate entry found.");
		}
		}
		//model.questionData(journey,request);
		model.terminate();
		
		return reset();
	}
	
public String addItem()throws Exception{
		
		String [] srNo=request.getParameterValues("srNo");
		String [] jClass=request.getParameterValues("jClass");
		String  hiddenEdit=request.getParameter("hiddenEdit");
		JourneyModel model= new JourneyModel();
		model.initiate(context, session);
		if(jClass !=null){
			model.getDuplicate(journey, srNo, jClass, 1);
			/*for (int i = 0; i < jClass.length; i++) {
				if(journey.getJourneyClass().toUpperCase().equals(""+jClass[i].toUpperCase()+"")){
					addActionMessage("Duplicate entry found.");
					journey.setJourneyClass("");
					journey.setSubcode("");
					journey.setHiddenEdit("");
					return SUCCESS;
				}
			}*/
			for (int i = 0; i < jClass.length; i++) {
				if(String.valueOf(hiddenEdit)!=null &String.valueOf(hiddenEdit)!="")
				{
					if((Integer.parseInt(hiddenEdit)-1)!=i)
						if(journey.getJourneyClass().toUpperCase().equals(""+jClass[i].toUpperCase()+"")){
							addActionMessage("Duplicate entry found.");
							journey.setJourneyClass("");
							journey.setSubcode("");
							journey.setHiddenEdit("");
							return SUCCESS;
						}
						
				}
				else if(journey.getJourneyClass().toUpperCase().equals(""+jClass[i].toUpperCase()+"")){
					addActionMessage("Duplicate entry found.");
					journey.setJourneyClass("");
					journey.setSubcode("");
					journey.setHiddenEdit("");
					return SUCCESS;
				}
			}
			
		}
	/*String [] srNo=request.getParameterValues("srNo");
	String [] jClass=request.getParameterValues("jClass");
	String  hiddenEdit=request.getParameter("hiddenEdit");
	logger.info("hiddenEdit=="+hiddenEdit);
	JourneyModel model= new JourneyModel();
	model.initiate(context, session);
	if(jClass !=null){
		model.getDuplicate(journey, srNo, jClass, 1);
		for (int i = 0; i < jClass.length; i++) {
			if(String.valueOf(hiddenEdit)!=null)
			if((Integer.parseInt(hiddenEdit)-1)!=i)
			if(journey.getJourneyClass().toUpperCase().equals(""+jClass[i].toUpperCase()+"")){
				addActionMessage("Duplicate entry found.");
				journey.setJourneyClass("");
				journey.setSubcode("");
				journey.setHiddenEdit("");
				return SUCCESS;
			}
		}
	}*/
		
		
		if (journey.getHiddenEdit().equals(""))
			model.addItem(journey, srNo, jClass, 1);
		else
			model.moditem(journey, srNo, jClass, 1);
		
		model.terminate();
		
		journey.setJourneyClass("");
		journey.setSubcode("");
		journey.setHiddenEdit("");
		return SUCCESS;
	}
	public String reset()throws Exception{
		journey.setJClass("");
		journey.setJourneyClass("");
		journey.setJourneyMode("");
		journey.setHiddenMode("");
		journey.setHiddenEdit("");
		journey.setSrNo("");
		journey.setSubcode("");
		journey.setHdeleteOp("");
		return SUCCESS;
	}
	
	public String deleteDtl()throws Exception {
		String code[]=request.getParameterValues("hdeleteOp");
		
		String [] jcl=request.getParameterValues("jClass");
		
		boolean result= false ;
		JourneyModel model= new JourneyModel();
		model.initiate(context,session);
		result = model.delDtl(journey,code,jcl);
		
		if(result)
		{
			addActionMessage("Record Deleted Successfully"); 
		}
		model.terminate();
		journey.setHiddenEdit("");
		journey.setHdeleteOp("");
		return SUCCESS;

	}
	
	
	public String delete () throws Exception{
		boolean result;
		JourneyModel model= new JourneyModel();
		logger.info("hi........in delete");
		model.initiate(context,session);
		result=model.deleteMode(journey);
		if(result){
			addActionMessage("Record deleted successfully !");
			reset();
		}
		else 
			addActionMessage("This record is referenced in other resources.So cannot be deleted.");
		//model.questionData(journey,request);
		model.terminate();
		
		return SUCCESS;
	}
	
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT  JOURNEY_ID , JOURNEY_NAME FROM HRMS_JOURNEY " 
					+" ORDER BY JOURNEY_ID ";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Journey Id", getMessage("journey.mode")};
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"hiddenMode","journeyMode"};
		
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
		String submitToMethod="JourneyMaster_showRecord.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	
public String showRecord () throws Exception{
		
	JourneyModel model= new JourneyModel();
		model.initiate(context,session);
		model.showRecord(journey);
		model.terminate();
		return SUCCESS;
	}
public String report(){
	JourneyModel model= new JourneyModel();
	model.initiate(context,session);
	model.generateReport(journey,response);
	model.terminate();
	return null;
}

	


}
