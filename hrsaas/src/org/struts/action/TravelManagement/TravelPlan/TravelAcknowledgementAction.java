package org.struts.action.TravelManagement.TravelPlan;

import org.paradyne.bean.TravelManagement.TravelPlan.TravelAcknowledgement;
import org.paradyne.bean.TravelManagement.TravelPlan.TravelAdvanceDisbursement;
import org.paradyne.model.Asset.AssetApprovalModel;
import org.paradyne.model.TravelManagement.TravelApproverModel;
import org.paradyne.model.TravelManagement.TravelPlan.TravelAcknowledgementModel;
import org.paradyne.model.TravelManagement.TravelPlan.TravelAdvanceDisbursementModel;
import org.struts.lib.ParaActionSupport;

	/**
	 * @author Dilip
	 *
	 */
	public class TravelAcknowledgementAction  extends ParaActionSupport {
		static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
		TravelAcknowledgement travelAcknowledgement;
		@Override
		public void prepare_local() throws Exception {
			travelAcknowledgement = new TravelAcknowledgement();
			travelAcknowledgement.setMenuCode(807);
		}

		/* (non-Javadoc)
		 * @see com.opensymphony.xwork2.ModelDriven#getModel()
		 */
		public Object getModel() {
			// TODO Auto-generated method stub
			return travelAcknowledgement;
		}
				
		public TravelAcknowledgement getTravelAcknowledgement() {
			return travelAcknowledgement;
		}

		public void setTravelAcknowledgement(TravelAcknowledgement travelAcknowledgement) {
			this.travelAcknowledgement = travelAcknowledgement;
		}

		public void prepare_withLoginProfileDetails() throws Exception {
			travelAcknowledgement.setMenuCode(807); 
		}
		public String input()
		{
			TravelAcknowledgementModel model = new TravelAcknowledgementModel();
			model.initiate(context, session);
			travelAcknowledgement.setChkFlag("true");
			model.callStatus(travelAcknowledgement,request,"P");
			model.terminate();  
			return "success";
		}
		/**
		 * @This method is used for save the status of application .
		 */
		
		/**
		 * @return success
		 */
		public String save()throws Exception {
							
			boolean result=false;
			TravelAcknowledgementModel model = new TravelAcknowledgementModel();
			String[] travelAppId = request.getParameterValues("travelAppId");
			String[] status = request.getParameterValues("checkStatus");
			String[]comment=request.getParameterValues("comment");
			model.initiate(context, session);
			 
			for (int i = 0; i < travelAppId.length; i++) {
				System.out.println("travelAppId----------------------"+travelAppId[i]);	
			}
			
			for (int i = 0; i < comment.length; i++) {
				 System.out.println("comment----------------------"+comment[i]);	
			}
			for (int i = 0; i < status.length; i++) {
				System.out.println("status----------------------"+status[i]);
			}
				if(status!=null && status.length >0)
				{
					for (int i = 0; i < status.length; i++) {
				
					result =model.getUpdateStatus(travelAcknowledgement,status[i], travelAppId[i],comment[i]);
					}
					if (result)
				          	{
							addActionMessage(getText("Record saved successfully"));
						    model.callStatus(travelAcknowledgement, request,"P");
					    
					      }
					else {
						   addActionMessage(getText("Record cann't saved successfully"));

					     }
				        }
				model.terminate();
				return "success";
			}
			
		 // end of for loop
			
		public String callStatus()
		{
			TravelAcknowledgementModel model = new TravelAcknowledgementModel();
			model.initiate(context, session);
			String status=request.getParameter("status"); 
			travelAcknowledgement.setStat(status);
			String chkFlag=request.getParameter("chkFlag"); 
			travelAcknowledgement.setChkFlag(chkFlag);
			model.callStatus(travelAcknowledgement,request,status);	
			if(status.equals("P"))
				{
				  travelAcknowledgement.setPen("true");
				}
			else if(status.equals("A")&& chkFlag.equals("true"))
				travelAcknowledgement.setApp("true");
			else if(status.equals("A")&& chkFlag.equals("false"))
				travelAcknowledgement.setCansApp("true");
			else if(status.equals("R")&& chkFlag.equals("true"))
				travelAcknowledgement.setRej("true");
			else if(status.equals("R")&& chkFlag.equals("false"))
				travelAcknowledgement.setCansRej("true");
			if(!status.equals("P")){
				System.out.println("status checking   "+status);
				travelAcknowledgement.setApprflag("true");
				
			}
			
		    model.terminate();
		    return "success";		
	    }  // end of callStatus method
		
		
		public String DupcallStatus()
		{
			TravelAcknowledgementModel model = new TravelAcknowledgementModel();
			model.initiate(context, session);
			String status=request.getParameter("dupstatus"); 
			String Bstatus=request.getParameter("Bstatus"); 
			String Fstatus=request.getParameter("Fstatus"); 
			travelAcknowledgement.setStat(status);
			String chkFlag=request.getParameter("Fstatus"); 
			travelAcknowledgement.setChkFlag(chkFlag);
			travelAcknowledgement.setButtonFlag(Bstatus);
					
			if(status.equals("P"))
				travelAcknowledgement.setPen("true");
			else if(status.equals("A")&& chkFlag.equals("true"))
				travelAcknowledgement.setApp("true");
			else if(status.equals("A")&& chkFlag.equals("false"))
				travelAcknowledgement.setCansApp("true");
			else if(status.equals("R")&& chkFlag.equals("true"))
				travelAcknowledgement.setRej("true");
			else if(status.equals("R")&& chkFlag.equals("false"))
				travelAcknowledgement.setCansRej("true");
			model.callStatus(travelAcknowledgement,request,status);
			model.terminate();
			return "success";
	    }  // end of callStatus method
				
		public String callView() {
			
			TravelAcknowledgementModel model = new TravelAcknowledgementModel();
			model.initiate(context, session);
			String status=request.getParameter("status");
			travelAcknowledgement.setStat(status);
			String chkFlag=request.getParameter("chkFlag");
			travelAcknowledgement.setBackFlag(chkFlag);
					
			String travelEmpId = request.getParameter("empCode");// 
			String travelAppId = request.getParameter("appCode");//
			travelAcknowledgement.setTravelEmpId(travelEmpId);
			travelAcknowledgement.setTravelAppId(travelAppId);
			model.callDetail(travelAcknowledgement, request);
			model.travelDetail(travelAcknowledgement, request);
			model.conveyDetail(travelAcknowledgement, request);
			model.lodgingDetail(travelAcknowledgement, request);
			model.travelAdvance(travelAcknowledgement, request);
			
			if(status.equals("A")||status.equals("R"))
			    {	
				  if(travelAcknowledgement.getChkFlag().equals("true"))
				   {
				    travelAcknowledgement.setChkFlag("true");
				    travelAcknowledgement.setButtonFlag("false");
				   }
		    	else
				   {
					travelAcknowledgement.setChkFlag("false");
					travelAcknowledgement.setButtonFlag("true");
					
					}
			     }
			else if(status.equals("P"))
			     {
				 travelAcknowledgement.setChkFlag("false");
			
			    }
			model.terminate();
			return "travelScheduleDetails";
		}

		
		public String pending() {			
			TravelAcknowledgementModel  model=new TravelAcknowledgementModel();
			model.initiate(context, session);	 
			travelAcknowledgement.setStat("P");
			String travelAppId= travelAcknowledgement.getTravelAppId();
			String comment= travelAcknowledgement.getApplComment();
			model.upDateStatus(travelAcknowledgement,travelAppId,comment);
			model.callStatus(travelAcknowledgement, request,"P");
			travelAcknowledgement.setTestFlag("true");
			model.terminate();
			return"success";
				
			}
		
		public String accept() {			
			TravelAcknowledgementModel  model=new TravelAcknowledgementModel();
			model.initiate(context, session);	 
			travelAcknowledgement.setStat("A");
			String travelAppId= travelAcknowledgement.getTravelAppId();
			String comment= travelAcknowledgement.getApplComment();
			model.upDateStatus(travelAcknowledgement,travelAppId,comment);
			model.callStatus(travelAcknowledgement, request,"P");
			model.terminate();
			return"success";
		}
		
		public String reject() {			
			TravelAcknowledgementModel  model=new TravelAcknowledgementModel();
			model.initiate(context, session);	 
			travelAcknowledgement.setStat("R");
			String travelAppId= travelAcknowledgement.getTravelAppId();
			String comment= travelAcknowledgement.getApplComment();
			model.upDateStatus(travelAcknowledgement,travelAppId,comment);				
			model.callStatus(travelAcknowledgement, request,"P");	
			model.terminate();
			return"success";
				
			}
		 public String download(){
			  TravelAcknowledgementModel model= new TravelAcknowledgementModel();
			  model.initiate(context, session);
			  model.travelTicket(travelAcknowledgement);
			  model.terminate(); 
			  return "success";
			 
		 }
		
	}   
		
		
	
		
	

	
	
	
	


