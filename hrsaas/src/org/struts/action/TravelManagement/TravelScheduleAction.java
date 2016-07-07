/**
 * Balaji
 * 15-08-2008
**/
package org.struts.action.TravelManagement;
import org.paradyne.bean.TravelManagement.TravelSchedule;
import org.paradyne.model.TravelManagement.TravelScheduleModel;
import org.struts.lib.ParaActionSupport;
/**
 * This class is used for prepare the schedule for travel.
**/
public class TravelScheduleAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	 
	TravelSchedule traSch;
	public void prepare_local() throws Exception {
		traSch = new TravelSchedule();
		traSch.setMenuCode(654);  
	}

	public TravelSchedule getTraSch() {
		return traSch;
	}

	public void setTraSch(TravelSchedule traSch) {
		this.traSch = traSch;
	}

	public Object getModel() {
		 
		return traSch;
	}
	
	
	public void prepare_withLoginProfileDetails() throws Exception {
		TravelScheduleModel model = new TravelScheduleModel();
		model.initiate(context, session);
		model.callStatus(traSch,"U",request);
		model.terminate();
		
	}
 
	/**
	 * @This method is used for show view TravelSchedule details.
	 */
	
	/**
	 * @return success
	 */
	public String callStatus()
	{
		TravelScheduleModel model = new TravelScheduleModel();
		model.initiate(context, session);
		String status=request.getParameter("status"); 
		model.callStatus(traSch,status,request);
		if(status.equals("P"))
			traSch.setPen("true");
		else if(status.equals("A"))
			traSch.setApp("true"); 
		if(!status.equals("P")){			 
			traSch.setApprflag("true");
		} 
		
		if(status.equals("S"))
		{
			traSch.setScheduled("true");
		}
		model.terminate();
		return "success";
    }  // end of callStatus
	
	/*public String callView()
	{
		String travelAppId = request.getParameter("traSchViewNo"); 
		TravelScheduleModel model = new TravelScheduleModel();
		model.initiate(context, session);
		model.setApplication(traApp ,travelAppId);	  
	  	model.callDtl(traApp ,travelAppId);	  	
	  	model.setApprover(traApp);
		model.terminate();
		
		return "viewTravelAppDtl";
	}*/
	
	
}
