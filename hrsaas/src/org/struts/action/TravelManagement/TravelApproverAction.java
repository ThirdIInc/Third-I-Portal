/**
 * Balaji
 * 12-08-2008
**/
package org.struts.action.TravelManagement;
import org.paradyne.bean.TravelManagement.TravelApprover;
import org.paradyne.model.TravelManagement.TravelApproverModel;
import org.struts.lib.ParaActionSupport;
/**
 * This class is used for store the information about Travel Application.
**/
public class TravelApproverAction extends ParaActionSupport{

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	TravelApprover traApp;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		traApp = new TravelApprover();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return traApp;
	}

	public TravelApprover getTraApp() {
		return traApp;
	}

	public void setTraApp(TravelApprover traApp) {
		this.traApp = traApp;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		traApp.setMenuCode(653);
		TravelApproverModel model = new TravelApproverModel();
		model.initiate(context, session);
		model.callStatus(traApp,"P",request);
		model.terminate(); 
	}
	/**
	 * @This method is used for save the status of application .
	 */
	
	/**
	 * @return success
	 */
	public String save()
	{
		 int j=0;
		 logger.info("In save==========");
		  boolean result=false;
		  String [] travelAppId = request.getParameterValues("travelAppId");
		  String [] travelEmpId   = request.getParameterValues("travelEmpId");
		  String [] status    = request.getParameterValues("checkStatus");
		  String [] level     = request.getParameterValues("level");
		  String [] comments     = request.getParameterValues("remark");
		  
		  TravelApproverModel model = new TravelApproverModel();
	      model.initiate(context, session);
		
		for(int i=0;i<status.length;i++){  
			 
			if(!(status[i].equals("P"))){
				 model.forward(traApp, status[i], travelAppId[i],comments[i],travelEmpId[i]);
				 if(status[i].equals("A")){
				Object[][]empFlow=generateEmpFlow(travelEmpId[i], "TYD", Integer.parseInt(level[i])+1);
				result= model.changeApplStatus(traApp, empFlow,travelAppId[i],travelEmpId[i]);
				j=1;
				}  
				
			}
				
	} // end of for loop
		if(result && j==1){
			  addActionMessage("Record saved successfully.");
		  }
		model.callStatus(traApp,traApp.getStatus(),request);
		model.terminate();
		return "success";
	} // end of save method
	/**
	 * @This method is used for show the information according to the status.
	 */
	
	/**
	 * @return success
	 */
	public String callStatus()
	{
		TravelApproverModel model = new TravelApproverModel();
		model.initiate(context, session);
		String status=request.getParameter("status"); 
		model.callStatus(traApp,status,request);
		if(status.equals("P"))
			traApp.setPen("true");
		else if(status.equals("A"))
			traApp.setApp("true");
		else if(status.equals("R"))
			traApp.setRej("true");
		if(!status.equals("P")){
			System.out.println("status checking   "+status);
			traApp.setApprflag("true");
		} 
		model.terminate();
		return "success";
    }  // end of callStatus method
	/**
	 * @This method is used for show the details of application
	 */
	
	/**
	 * @return viewTravelAppDtl
	 */
	public String callView()
	{
		String travelAppId = request.getParameter("travelViewNo"); 
		TravelApproverModel model = new TravelApproverModel();
		model.initiate(context, session);
		model.setApplication(traApp ,travelAppId);	  
	  	model.callDtl(traApp ,travelAppId);	  	
	  	model.setApprover(traApp);
	  	model.generateExpense(traApp);
		model.terminate();
		
		return "viewTravelAppDtl";
	} // end of callStatus method
	
	
	
}   // end of class
 
 
