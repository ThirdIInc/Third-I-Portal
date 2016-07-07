/**
 * 
 */
package org.struts.action.leave;

import java.util.ArrayList;
import java.util.Map;


//import org.paradyne.bean.attendance.TestBean;
import org.paradyne.bean.leave.LeaveMaster;
// import org.paradyne.lib.ModelBase;
//import org.paradyne.lib.ModelController;
import org.paradyne.model.leave.LeaveModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author sunil
 * 
 */
public class LeaveMasterAction extends ParaActionSupport {

	LeaveMaster levMaster;
	
	public LeaveMaster getLevMaster() {
		return levMaster;
	}
	
	public void setLevMaster(LeaveMaster levMaster) {
		this.levMaster = levMaster;
	}
	
	 public Object getModel() {
		return levMaster;
	}
	
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		levMaster = new LeaveMaster();
		levMaster.setMenuCode(202);
	}

	public String getLeaveDetails() {
		levMaster.setLeavCode("3455");
		levMaster.setLeavName("sldfjsldkfj");
		return "success";
	}

	/**
	 * 	THIS IS THE FIRST METHOD CALLED BEFORE ANY ACTION.
	 * 	INITIALISE ALL THE PRE-POPULATED FIELDS IN THIS METHOD,
	 * 
	 * 	* MUST DO - INITIALISE THE BEAN OBJECT 
	 * 
	 */
	
	
	public void prepareInput() throws Exception {		
			// MUST BE INITIALIZED HERE
			LeaveModel model = new LeaveModel();
			model.initiate(context,session);
			model.prePopulate(levMaster);
			model.terminate();
			levMaster.setLeavCode("123");
	}

	public String save() throws Exception {
		
/*		logger.info("1");
		LeaveModel model = new LeaveModel();
		logger.info("2");
		model.initiate(context,session);
		logger.info("3");
		boolean result = model.addLeave(levMaster);
		logger.info("4");
		model.terminate();

		ArrayList<Object> list = new ArrayList<Object>();
		TestBean bean = new TestBean();
		bean.setId("1");
		bean.setName("Sachin");
		list.add(bean);
		list.add(bean);
		levMaster.setTestList(list);

		if (result) {
			addActionMessage(getText("addMessage", ""));
		} else {
			addActionMessage("Leaves can not be added");
		}
		logger.info("5");
		
		*/
		
		LeaveModel model = new LeaveModel();		
		logger.info("leave code : "+levMaster.getLeavCode());
		
		try{
			ArrayList list = levMaster.getTestList();
			/*TestBean bean1= (TestBean)list.get(0);
			logger.info("id : "+bean1.getId());
			logger.info("name : "+bean1.getName());*/
			
			//logger.info("id : "+context);
			
		}catch(Exception e){
			logger.info("error no bean foubnd: "+e);			
		}	
		model.terminate();
		return "success";
	}
	
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 * 		ALONG WITH PROFILES  
		 */		
		String query = " SELECT LEAVE_ID, LEAVE_NAME FROM HRMS_LEAVE ";
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Leave code", "Leave name"};
		
		/**
		 * 		DEFINE THE PERCENT WIDTH OF EACH COLUMN 
		 */
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"levMaster.leavCode","levMaster.leavName"};
		
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
		String submitToMethod="LeaveMaster_getLeaveDetails.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}

	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
}
