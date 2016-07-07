package org.struts.action.leave;

 import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.leave.LeaveRegularization;
 import org.paradyne.lib.ModelBase;
import org.paradyne.model.leave.LeaveRegularizationModel;

public class LeaveRegularizationAction extends ParaActionSupport {

	LeaveRegularization leaveRe;
	
	
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		leaveRe=new LeaveRegularization();
		// TODO Auto-generated method stub

	}

	 public Object getModel() {
		// TODO Auto-generated method stub
		return leaveRe;
	}

	public LeaveRegularization getLeaveRe() {
		return leaveRe;
	}

	public void setLeaveRe(LeaveRegularization leaveRe) {
		this.leaveRe = leaveRe;
	}
	
	public String reset(){
		leaveRe.setEmpID("");
		leaveRe.setEmpName("");
		leaveRe.setEmpRank("");
		leaveRe.setEmpCenter("");
		leaveRe.setLeaveFrom("");
		leaveRe.setLeaveTo("");
		leaveRe.setEmpToken("");
		return "success";
	}
	
	/*public String view()throws Exception{
		LeaveRegularizationModel model=new LeaveRegularizationModel();
		model.initiate(context,session);
		logger.info("In TdsCalc***********1**");
		boolean data=model.generateLeaveStatus(leaveRe,request);
		
		logger.info("In TdsCalc***********2**");
	
		model.terminate();
		
		return details();
	}*/
	
	public String details()throws Exception{
		LeaveRegularizationModel model=new LeaveRegularizationModel();
		model.initiate(context,session);
		//float result1=model.getAmount(leaveRe,request);
		logger.info("--------------In Detail---------------------");
		String[][] result= model.getDetail(leaveRe,request);
		//logger.info("in result-----------------------------"+result.length());
		 
		model.terminate();
		
		request.setAttribute("result", result);
		 
		 
		 if(result.length>0)
		 {
		leaveRe.setFlag("true");
		leaveRe.setIsFromEdit("true");
		 }
		 else
		 {
			 addActionMessage("No Records To View");
		 }
		return "success";
	}
	
	public String regular()throws Exception{
		LeaveRegularizationModel model=new LeaveRegularizationModel();
		model.initiate(context,session);
		String[] leaveid=(String[])request.getParameterValues("leaveCode");
	
		String[] date=(String[])request.getParameterValues("date");
		String[] amount=(String[])request.getParameterValues("amount");
		String[] ids=(String[])request.getParameterValues("pmChkFlag");
		/*String [] date =new String[leaveid.length];
		String [] amt =new String[leaveid.length];*/
		//float result1=model.getAmount(leaveRe,request);

		for(int i=0;i<ids.length;i++)
		{
			/*String date1=request.getParameter("date"+i);
			date[i]=date1;
			String amount=request.getParameter("amount"+i);
			logger.info("ammmmmmmmmmmmmmount--..."+amount);
			amt[i]=amount;
			logger.info("in action date---->>>>>"+date.length);
			logger.info("in action amount---->>>>>"+amt.length);*/
			if(ids[i].equals("Y"))
			{
				boolean result=model.addRegular(leaveRe,date[i],amount[i]);
				if(result)
					addActionMessage("Record Regularized Successfully");
			}
			
			logger.info("in action amount---->>>>>"+ids[i]);
			logger.info("in action amount---->>>>>"+date[i]);
			logger.info("in action amount---->>>>>"+amount[i]);
		}

		/*boolean result=false;
		// result= model.addRegular(leaveRe,leaveid,amt,date,request);
		 if(result){ 
				addActionMessage("Record Regularized successfully");
				//model.getDetail(leaveRe,request);
				
		}*/
		//logger.info("in result-----------------------------"+result.length());
		//model.getDetail(leaveRe,request);
		details();
		model.terminate();
		leaveRe.setIsFromEdit("true");
		return "success";
	}
	
	public String f9empaction() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = "SELECT EMP_TOKEN,(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),(HRMS_CENTER.CENTER_ID||'-'||HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME,EMP_ID "
							+" FROM HRMS_EMP_OFFC "
							+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
							+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
							+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)";
							query+=getprofileQuery(leaveRe);
							query+=" ORDER BY EMP_ID "	;
								
					
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Token No.","Employee Name","Center Name","Rank Name"};
		
		String[] headerWidth={"30","30","30","30"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		
		String[] fieldNames={"empToken","empName","empCenter","empRank","empID"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2,3,4};
		
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
