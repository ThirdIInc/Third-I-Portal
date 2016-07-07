/**
 * Balaji
 * 22-08-2008
**/ 
package org.struts.action.TravelManagement;
import org.paradyne.bean.TravelManagement.TravelConfirmation;
import org.paradyne.model.TravelManagement.TravelConfirmationModel;
import org.struts.lib.ParaActionSupport;
/**
 * This class is used for check to confirmation or cancel the journey
**/
public class TravelConfirmationAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	TravelConfirmation travelConf ;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		travelConf =new TravelConfirmation();
		travelConf.setMenuCode(658);  
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return travelConf;
	}

	public TravelConfirmation getTravelConf() {
		return travelConf;
	}

	public void setTravelConf(TravelConfirmation travelConf) {
		this.travelConf = travelConf;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		
		TravelConfirmationModel model =new TravelConfirmationModel();
		model.initiate(context, session); 
		model.generateListForConfirm(travelConf,"D",request); 
		model.terminate();
	}
	
	/**
	 * @This method is used for save the status of Travel confirmation
	 */
	
	/**
	 * @return success
	 */
	
	public String callConfirm()
	{
		String status = request.getParameter("status"); 
		TravelConfirmationModel model =new TravelConfirmationModel();
		model.initiate(context, session); 
		model.generateListForConfirm(travelConf,status,request); 
		model.terminate();
		return "success";
	}
	
	public String callAdminCancel()
	{
		TravelConfirmationModel model =new TravelConfirmationModel();
		model.initiate(context, session); 
		String cancelStatus= request.getParameter("cancelStatus");		
		String[] cancelStat= cancelStatus.split("-");	
		System.out.println("cancelStat[0]..  "+cancelStat[0]);
		System.out.println("cancelStat[1]..  "+cancelStat[1]);
		model.getAdminCancelList(travelConf,cancelStat[0],cancelStat[1],request); 
		model.terminate();
		return "success";
	}
	
	
	public String save()
	{
		String status = request.getParameter("status");
		TravelConfirmationModel model =new TravelConfirmationModel();
		model.initiate(context, session);
		String travelId = request.getParameter("hiddenTravelId");
		
		
					
				boolean flag=model.save(travelConf,travelId);
				if(flag)
			{
				addActionMessage("Travel confirmed successfully.");
			}
			
		
		model.generateListForConfirm(travelConf,status,request);
			
		model.terminate();
		
		return SUCCESS;
	} // end of save method
	
	public String travelCancel()
	{
		String status = request.getParameter("status");
		TravelConfirmationModel model =new TravelConfirmationModel();
		model.initiate(context, session);
		String travelId = request.getParameter("hiddenTravelId");
		
		
					
				boolean flag=model.travelCancel(travelConf,travelId);
				if(flag)
			{
				addActionMessage("Travel cancel Successfully.");
			}			
		
		model.generateListForConfirm(travelConf,status,request);
			
		model.terminate();
		
		return SUCCESS;
	} // end of save method
	 
}
