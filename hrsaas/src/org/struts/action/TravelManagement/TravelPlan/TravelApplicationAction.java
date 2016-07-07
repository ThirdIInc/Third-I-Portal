/**
 * 
 */
package org.struts.action.TravelManagement.TravelPlan;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.TravelPlan.TravelApplication;
import org.paradyne.model.TravelManagement.TravelPlan.TravelApplicationModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0417
 *
 */
public class TravelApplicationAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */

	TravelApplication trApl;
	public void prepare_local() throws Exception {
		trApl = new TravelApplication();
		trApl.setMenuCode(808);    
	}
	 @Override
	public void prepare_withLoginProfileDetails() throws Exception { 
		  TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);
			model.callForPolicyDate(trApl); 
			if (trApl.isGeneralFlag()) {   
			  	 model.getEmployeeDetails(trApl.getUserEmpId(), trApl);  
			  	/* model.callEmpDtl(trApl);   
		    	 model.callForSettlementDate(trApl);*/
		     	/* model.addTravelList(trApl ,request);
		    	 model.addLocalCon(trApl ,request);
		    	 model.addLodgList(trApl ,request); 
		    	 model.callForSettlementDate(trApl);
		    	 model.radioFlagMethod(trApl,request); */
			}
			
			 
			model.terminate();  	
	}
	 
	 

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return trApl;
	}

	public TravelApplication getTrApl() {
		return trApl;
	}

	public void setTrApl(TravelApplication trApl) {
		this.trApl = trApl;
	}
	
	
	public String input() throws Exception {
		
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);
		 boolean reqStaus = model.callRequistionStatus(trApl);
		 System.out.println("reqStaus========= "+reqStaus);
		 if(reqStaus==false)
		 {
			 addActionMessage("Insufficient priviliges to send the Travel Requisition for approval.");   
			 getNavigationPanel(1);
				return null;
		 }
		model.callStatus(trApl ,"N",request) ;
		request.setAttribute("status", "N");
		model.terminate(); 
		getNavigationPanel(1);
		return INPUT;
	}
	
   public String callStatus() throws Exception { 
		String status=request.getParameter("status"); 
		System.out.println("status==========chk "+status);
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);
		model.callStatus(trApl ,status,request) ;
		request.setAttribute("status", status);
		model.terminate(); 
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String addNew()
	{  
		 trApl.setTrNoAddRow("true");
		trApl.setLocalNoAddRow("true");
		trApl.setLodgNoAddRow("true");
		
		/*TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);
    	model. callOnLOad(trApl);
    	model.getAllIterator(trApl);
     	model.addTravelList(trApl ,request);
    	model.addLocalCon(trApl ,request);
    	model.addLodgList(trApl ,request); 
		model.terminate();  */	
		 
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);
		model.getAllIterator(trApl);
		model.callForPolicyDate(trApl);
		model. callOnLOad(trApl);
		model.terminate(); 
		
		trApl.setLoadFlag("false");
		trApl.setAddNewFlag("true");
		trApl.setSaveFlag("false");
		 
		getNavigationPanel(2);
		return "success";
	}
	
	public String save()
	{    trApl.setButtonFlag("save");
		 TravelApplicationModel model = new TravelApplicationModel(); 
		 model.initiate(context, session);   
		 boolean flag=true; 
		if(trApl.getTravelPolInvalidFlag().equals("false") && trApl.getTrAppId().equals("") )
		{ 
			flag = model.callTravelPolicy(trApl,request,response);    
		} 
		if(flag)
		{ 	boolean result;   
				
				if(trApl.getTrAppId().equals(""))
				{
		    	 result = model.save(trApl,request);
				  if(result)
					{
						addActionMessage(" Record saved successfully.");
						trApl.setLoadFlag("false");
						trApl.setAddNewFlag("false");
						trApl.setEditFlag("true");
						trApl.setSaveFlag("false");  
						callAppDtl(); 
						model.radioFlagMethod(trApl,request); 
						model.terminate(); 
						getNavigationPanel(3); 
						//reset();
					}else
					{		addActionMessage(" Record can't saved.");
						    trApl.setTrNoAddRow("true");
							trApl.setLocalNoAddRow("true");
							trApl.setLodgNoAddRow("true");
							model.delTravelMode(trApl,request);  
							model.delLocalConv(trApl,request);   
							model.delLodg(trApl ,request); 
							model.radioFlagMethod(trApl,request); 
							model.terminate();  
							getNavigationPanel(2); 
					}
				}else
				{
					result= model.update(trApl,request);
					 if(result)
						{
							addActionMessage(" Record updated successfully."); 
							trApl.setLoadFlag("false");
							trApl.setAddNewFlag("false");
							trApl.setEditFlag("true");
							trApl.setSaveFlag("false"); 
							callAppDtl(); 
							model.radioFlagMethod(trApl,request); 
							model.terminate(); 
							getNavigationPanel(3); 
						 
						}else
						{    					 
						    trApl.setTrNoAddRow("true");
							trApl.setLocalNoAddRow("true");
							trApl.setLodgNoAddRow("true");
							model.delTravelMode(trApl,request);  
							model.delLocalConv(trApl,request);   
							model.delLodg(trApl ,request); 
							model.radioFlagMethod(trApl,request); 
							model.terminate(); 
							addActionMessage(" Record can't updated.");
							getNavigationPanel(2);		
						}
				  } 
		
		return SUCCESS; 
		}else
		{   trApl.setLoadFlag("false");
			trApl.setAddNewFlag("true");
			trApl.setSaveFlag("false");  
			model.addTravelList(trApl ,request);
	    	model.addLocalCon(trApl ,request);
	    	model.addLodgList(trApl ,request);   
	    	model.radioFlagMethod(trApl,request); 
	    	flag=true;
	    	request.setAttribute("travelPolInvalidFlag",new String("true"));
	    	getNavigationPanel(2);  
			return SUCCESS; 	
			
		} 
		
	}
	
	
	
	public String sendForApproval()
	{   
		 trApl.setButtonFlag("sendForApproval");
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session); 
		
		if(trApl.getTravelPolInvalidFlag().equals("false") && trApl.getTrAppId().equals("") )
		{ 
		  model.callTravelPolicy(trApl,request,response);   
	 
		   trApl.setLoadFlag("false");
		 	trApl.setAddNewFlag("true");
			trApl.setSaveFlag("false");  
			model.addTravelList(trApl ,request);
	    	model.addLocalCon(trApl ,request);
	    	model.addLodgList(trApl ,request);   
	    	model.radioFlagMethod(trApl,request);  
	    	request.setAttribute("travelPolInvalidFlag",new String("true"));
	    	getNavigationPanel(2);  
			return SUCCESS; 	
		} 
		 
			Object[][] empFlow = generateEmpFlow(trApl.getEmpId(), "TYD", 1);
			if(empFlow==null)
			{
				trApl.setLoadFlag("true");
				trApl.setAddNewFlag("false");
				trApl.setEditFlag("false");
				trApl.setSaveFlag("false"); 
				
				addActionMessage("Reporting structure not defined for the employee\n"
						+ trApl.getEmpName());
				addActionMessage("Please contact HR manager.");  
		        reset();
		        trApl.setGuestAppFlag("false");
		    	trApl.setTrArFlag("false");
		    	trApl.setLocalFlag("false");
		    	trApl.setAccomFlag("false"); 
				getNavigationPanel(1);  
				model.terminate(); 
				return INPUT;
			}
			else
				{
				boolean result; 
				if(trApl.getTrAppId().equals(""))
				{
		    	 result = model.sendForApprovalSave(trApl,request,empFlow);
				  if(result)
					{
					    trApl.setLoadFlag("true");
						trApl.setAddNewFlag("false");
						trApl.setEditFlag("false");
						trApl.setSaveFlag("false"); 
						
						addActionMessage(" Application forwarded successfully.");
						TravelApplicationModel model1 = new TravelApplicationModel();
						model1.initiate(context, session);
						model1.callStatus(trApl ,"N",request) ;
						model1.terminate(); 
						model.terminate();
						getNavigationPanel(1);
						return INPUT;
						//reset();
					}else
					{
						  addActionMessage("Application can't forwarded.");
						    trApl.setAddNewFlag("true");
						    trApl.setTrNoAddRow("true");
							trApl.setLocalNoAddRow("true");
							trApl.setLodgNoAddRow("true");
							model.delTravelMode(trApl,request);  
							model.delLocalConv(trApl,request);   
							model.delLodg(trApl ,request); 
							model.radioFlagMethod(trApl,request); 
							model.terminate();  
							getNavigationPanel(2); 
							return SUCCESS;  
					}
				}else
				{
					result= model.sendForApprovalUpdate(trApl,request,empFlow);
					 if(result)
						{
						 trApl.setLoadFlag("true");
							trApl.setAddNewFlag("false");
							trApl.setEditFlag("false");
							trApl.setSaveFlag("false"); 
							
						 addActionMessage(" Application forwarded successfully.");
						 TravelApplicationModel model1 = new TravelApplicationModel();
							model1.initiate(context, session);
							model1.callStatus(trApl ,"N",request) ;
							model1.terminate(); 
							model.terminate();
							getNavigationPanel(1);
							return INPUT;
						}else
						{
							addActionMessage("Application can't forwarded.");
							  trApl.setAddNewFlag("true");
							   trApl.setTrNoAddRow("true");
								trApl.setLocalNoAddRow("true");
								trApl.setLodgNoAddRow("true");
								model.delTravelMode(trApl,request);  
								model.delLocalConv(trApl,request);   
								model.delLodg(trApl ,request); 
								model.radioFlagMethod(trApl,request); 
								model.terminate();  
								getNavigationPanel(2); 
								return SUCCESS;
						}
				  }
				}    
	}
	
	
	public String callEmpDtl()
	{   
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session); 
    	model.getEmployeeDetails(trApl.getEmpId(),trApl);
    	System.out.println("in emp dtl befoe");
    	model. callOnLOad(trApl);
     	model.addTravelList(trApl ,request);
    	model.addLocalCon(trApl ,request);
    	model.addLodgList(trApl ,request);  
    	System.out.println("in emp dtl after");
    	model.callForSettlementDate(trApl);
    	model.radioFlagMethod(trApl,request); 
		model.terminate(); 
		getNavigationPanel(2);
		return SUCCESS; 
	}
	
	public String callAppDtl()
	{   
		trApl.setLoadFlag("false");
		trApl.setAddNewFlag("false");
		trApl.setEditFlag("true");
		trApl.setSaveFlag("false");
		
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);
		model.callTravelPolicy(trApl,request,response);
		model.getEmployeeDetails(trApl.getEmpId(),trApl);
		model.callForSettlementDate(trApl);
    	model.callAppDtl(trApl,request);
    	model.radioFlagMethod(trApl,request);
		model.terminate();
		 
		getNavigationPanel(3);
		return SUCCESS; 
	}
	
	public String delete()
	{   
		trApl.setLoadFlag("true");
		trApl.setAddNewFlag("false");
		trApl.setEditFlag("false");
		trApl.setSaveFlag("false"); 
		
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);
    	boolean flag =model.callDelete(trApl); 
    	trApl.setDelteArrFlag("true");
    	model.addTravelList(trApl ,request);
    	model.addLocalCon(trApl ,request);
    	model.addLodgList(trApl ,request);
    	trApl.setDelteArrFlag("false"); 
    	if(flag)
    	{
    	 addActionMessage("Record deleted successfully.");	
    	 reset();
    	}
    	model.callStatus(trApl ,"N",request) ;
		model.terminate();
		getNavigationPanel(1);
		return "input"; 
	}
	
	public String reset()
	{
		 trApl.setTrAppId(""); 
		 trApl.setEmpId("");  
		 trApl.setAppDate("");   
		 trApl.setTravelRequest("");  
		 trApl.setAppFor("");  
		 trApl.setGuestName(""); 
		 trApl.setTravelPurposeId("");  
		 trApl.setTravelPurpose("");  
		 trApl.setAccommodationReq("");  
		 trApl.setTrArrg("");  
		 trApl.setLocalConReq("");  
		 trApl.setTourLocTypeId("");  
		 trApl.setTourLocType("");  
		 trApl.setTourStartDate("");  
		 trApl.setTourEndDate("");
		 trApl.setAdvanceAmt("");
		 trApl.setPayMode("");			 
		 trApl.setSettleDate(""); 
		 trApl.setIdProof("");  			 
		 trApl.setIdProofNumber("");  		 
		 trApl.setAppComments("");  
		 trApl.setLevel("");  
		 trApl.setEmpName("");
		 trApl.setTravelRequest("");
		 trApl.setAppDate("");
		 trApl.setBranchName("");
		 trApl.setDepartment("");
		 trApl.setDesignation("");
		 trApl.setDepartment(""); 
		 trApl.setEmpGrade("");
		 trApl.setEmpGradeId("");
		 trApl.setEmpDob("");  
		 trApl.setEmpToken("");
		 trApl.setEmpAge("");
		 
		return "success";
	}
	
	public String edit()
	{   
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session); 
		model. callOnLOad(trApl);
		model.callAppDtl(trApl ,request);   
		model.callTravelPolicy(trApl,request,response);
		model.callForSettlementDate(trApl);
		model.terminate();
		trApl.setAddNewFlag("true");
		trApl.setEditFlag("false"); 
		model.radioFlagMethod(trApl,request);
		getNavigationPanel(2);
		return SUCCESS; 
	}
	

	
	public String cancel()
	{    	
		TravelApplicationModel model1 = new TravelApplicationModel();
		model1.initiate(context, session); 
		
		
		if(trApl.getAddNewFlag().equals("true"))
		{
			trApl.setLoadFlag("true");
			trApl.setAddNewFlag("false");
			reset();
			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session); 
			model.callStatus(trApl ,"N",request) ;
			model.terminate();
			 getNavigationPanel(1);
			 return "input";
		} 
		if(trApl.getEditFlag().equals("true")&& (trApl.getHidFlag().equals("search")||trApl.getHidFlag().equals("false")))
		{
			trApl.setLoadFlag("true");
			trApl.setEditFlag("false"); 
			reset();
			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session); 
			trApl.setDelteArrFlag("true");
	    	model.addTravelList(trApl ,request);
	    	model.callStatus(trApl ,"N",request) ;
	    	trApl.setDelteArrFlag("false");
	    	model.terminate();
			getNavigationPanel(1);
			return "input";
		}
		
		
		
		if(trApl.getEditFlag().equals("true")&& trApl.getHidFlag().equals("edit"))
		{
			model1.radioFlagMethod(trApl,request);
			System.out.println("edit flag true and edit ");
			 trApl.setLoadFlag("false");
			trApl.setEditFlag("true"); 
			trApl.setSaveFlag("false"); 
			trApl.setHidFlag("save"); 
			getNavigationPanel(3); 
			reset();
			return "success";
		}
		if(trApl.getEditFlag().equals("true")&& trApl.getHidFlag().equals("save"))
		{
			trApl.setEditFlag("false"); 
			trApl.setSaveFlag("false"); 
			trApl.setAddNewFlag("false");
			trApl.setLoadFlag("true"); 
			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session); 
			trApl.setDelteArrFlag("true");
	    	model.addTravelList(trApl ,request);
	    	trApl.setDelteArrFlag("false");
	    	model.callStatus(trApl ,"N",request) ;
	    	model.terminate();
			reset();
			getNavigationPanel(1);
			return "input";
		}
		
		if(trApl.getSaveFlag().equals("true"))
		 {  model1.radioFlagMethod(trApl,request);
			trApl.setLoadFlag("false");
			trApl.setEditFlag("true"); 
			trApl.setSaveFlag("false"); 
			getNavigationPanel(3);
		}
		model1.terminate();
		return "success";
	}
	
	public String addTravelMode()
	{
		trApl.setTrNoAddRow("true");
		 trApl.setLocalNoAddRow("false");
		 trApl.setLodgNoAddRow("false");
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session); 
		model. callOnLOad(trApl);
    	model.addTravelList(trApl ,request);
    	model.addLocalCon(trApl ,request);
    	model.addLodgList(trApl ,request);
    	model.radioFlagMethod(trApl,request);
		model.terminate();  
		System.out.println("trApl.getApproverPanelFlag()============ "+trApl.getApproverPanelFlag());
		if(trApl.getApproverPanelFlag().equals("true"))
		{
			getNavigationPanel(4);
			return "trApprover"; 
		}else
		{
			getNavigationPanel(2);
			return SUCCESS;
		}
		
		
		
	}
	
	public String delTravelMode()
	{
		trApl.setTrNoAddRow("false");
		 trApl.setLocalNoAddRow("false");
		 trApl.setLodgNoAddRow("false");
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);  
    	model.addLocalCon(trApl ,request);
    	model.addLodgList(trApl ,request);
    	model.delTravelMode(trApl,request);
    	model.radioFlagMethod(trApl,request);
		model.terminate();   
		
		if(trApl.getApproverPanelFlag().equals("true"))
		{
			getNavigationPanel(4);
			return "trApprover"; 
		}else
		{
			getNavigationPanel(2);
			return SUCCESS;
		}
		 
	} 
	public String addLocalCon()
	{
	    trApl.setTrNoAddRow("false");
	    trApl.setLocalNoAddRow("true");
	    trApl.setLodgNoAddRow("false");
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);  
		model. callOnLOad(trApl);
    	model.addTravelList(trApl ,request);
    	model.addLocalCon(trApl ,request);
    	model.addLodgList(trApl ,request);
    	model.radioFlagMethod(trApl,request);
		model.terminate();  
		if(trApl.getApproverPanelFlag().equals("true"))
		{
			getNavigationPanel(4);
			return "trApprover"; 
		}else
		{
			getNavigationPanel(2);
			return SUCCESS;
		}
	}
	
	public String delLoaclConv()
	{
		 trApl.setTrNoAddRow("false");
		 trApl.setLocalNoAddRow("false");
		 trApl.setLodgNoAddRow("false");
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session); 
		model. callOnLOad(trApl); 
    	model.addTravelList(trApl ,request); 
    	model.addLodgList(trApl ,request);
    	model.delLocalConv(trApl,request);
    	model.radioFlagMethod(trApl,request);
		model.terminate();  
		if(trApl.getApproverPanelFlag().equals("true"))
		{
			getNavigationPanel(4);
			return "trApprover"; 
		}else
		{
			getNavigationPanel(2);
			return SUCCESS;
		}
	} 
	

	public String addLodg()
	{
		 trApl.setTrNoAddRow("false");
		 trApl.setLocalNoAddRow("false");
		 trApl.setLodgNoAddRow("true");
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session); 
		model. callOnLOad(trApl);
    	model.addLodgList(trApl ,request);
    	model.addTravelList(trApl ,request);
    	model.addLocalCon(trApl ,request); 
    	model.radioFlagMethod(trApl,request);
		model.terminate();  
		if(trApl.getApproverPanelFlag().equals("true"))
		{
			getNavigationPanel(4);
			return "trApprover"; 
		}else
		{
			getNavigationPanel(2);
			return SUCCESS;
		}
	}
	
	public String delLodg()
	{
	    trApl.setTrNoAddRow("false");
	    trApl.setLocalNoAddRow("false");
	    trApl.setLodgNoAddRow("false");
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session); 
		model. callOnLOad(trApl); 
    	model.addTravelList(trApl ,request);
    	model.addLocalCon(trApl ,request); 
    	model.delLodg(trApl ,request); 
    	model.radioFlagMethod(trApl,request);
		model.terminate();  
		
		if(trApl.getApproverPanelFlag().equals("true"))
		{
			getNavigationPanel(4);
			return "trApprover"; 
		}else
		{
			getNavigationPanel(2);
			return SUCCESS;
		}
	}
	 
	public String TravelPolicy()
	{  
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session); 
    	boolean flag =model.callTravelPolicy(trApl,request,response);
    	if(flag==false)
    	{
    		//addActionMessage("Please define the grade for employee.");
    	}
    	model.radioFlagMethod(trApl,request);
    	getNavigationPanel(2);
		model.terminate();   
		return "trAppPolicy"; 
	}

	public String TrackForTravelMode()
	{  
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session); 
    	model.trackForTravelMode(trApl); 
    	model.radioFlagMethod(trApl,request);
    	getNavigationPanel(2);
		model.terminate();   
		return "trackMode"; 
	}
	
	public String TrackForLocal()
	{  
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session); 
    	model.trackForLocal(trApl); 
    	model.radioFlagMethod(trApl,request);
    	getNavigationPanel(2);
		model.terminate();   
		return "trackMode"; 
	}
	
	public String TrackForLodg()
	{  
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session); 
    	model.trackForLodg(trApl); 
    	model.radioFlagMethod(trApl,request);
    	getNavigationPanel(2);
		model.terminate();   
		return "trackMode"; 
	}
	
	// ================== Approver ===================
	
	public String callViewAppr()
	{  
	    System.out.println("getAppStatus=========== "+trApl.getAppStatus());
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session); 
		model.employeeInfo(trApl);
		if(trApl.getTrAppCanStatus().equals("true")){
    	model.callAppDtl(trApl,request);
		}else
		{
			model.callCancellationAppDtl(trApl,request);
		}
    	model.callTravelPolicy(trApl,request,response);
    	model.radioFlagMethod(trApl,request);
    	
    	  if(trApl.getTrAppCanStatus().equals("true"))
	         {
    		  model.dispApprCommets(trApl);
	         }else
	         {
	         model.dispApprCommetsCancel(trApl);
	         } 
		model.terminate();  
	
		if(trApl.getAppStatus().equals("P") && trApl.getTrAppCanStatus().equals("true"))
		{
			getNavigationPanel(4);
		   return "trApprover";
		}else
		{ 
			if(trApl.getAppStatus().equals("P"))
			{
				getNavigationPanel(6);
			}else
			{
			getNavigationPanel(5);
			}
			return "trApproverView";
		}
	}
	
	public String saveForApproval()
	{  
		
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);    
		boolean result; 
		result= model.updateApprover(trApl,request); 
	    if(result)
			{
				addActionMessage(" Record updated successfully."); 
				model.initiate(context, session); 
		    	model.callAppDtl(trApl,request);
		    	model.radioFlagMethod(trApl,request);  
				model.terminate();
				getNavigationPanel(4); 
			}else
			{
				addActionMessage(" Record can't updated.");
			    trApl.setTrNoAddRow("true");
				trApl.setLocalNoAddRow("true");
				trApl.setLodgNoAddRow("true");
				model.delTravelMode(trApl,request);  
				model.delLocalConv(trApl,request);   
				model.delLodg(trApl ,request); 
				model.radioFlagMethod(trApl,request); 
				model.terminate();  
				getNavigationPanel(2); 
			}   
		return "trApprover"; 
	}
	
	
	public String saveForApprovalUpadte()
	{   
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);    
		boolean result; 
		result= model.updateApprover(trApl,request); 
	    if(result)
			{
				addActionMessage(" Record updated successfully."); 
				model.initiate(context, session); 
		    	model.callAppDtl(trApl,request);
		    	model.radioFlagMethod(trApl,request);  
				model.terminate();
				getNavigationPanel(4); 
			}else
			{
				addActionMessage(" Record can't updated.");
			    trApl.setTrNoAddRow("true");
				trApl.setLocalNoAddRow("true");
				trApl.setLodgNoAddRow("true");
				model.delTravelMode(trApl,request);  
				model.delLocalConv(trApl,request);   
				model.delLodg(trApl ,request); 
				model.radioFlagMethod(trApl,request); 
				model.terminate();  
				getNavigationPanel(2); 
			}   
		return "travelApporvalUpdate"; 
	}
	
	public String saveForRejectUpadte()
	{   
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session);    
		boolean result; 
		result= model.updateApprover(trApl,request); 
	    if(result)
			{
				addActionMessage(" Record updated successfully."); 
				model.initiate(context, session); 
		    	model.callAppDtl(trApl,request);
		    	model.radioFlagMethod(trApl,request);  
				model.terminate();
				getNavigationPanel(4); 
			}else
			{
				addActionMessage(" Record can't updated.");
			    trApl.setTrNoAddRow("true");
				trApl.setLocalNoAddRow("true");
				trApl.setLodgNoAddRow("true");
				model.delTravelMode(trApl,request);  
				model.delLocalConv(trApl,request);   
				model.delLodg(trApl ,request); 
				model.radioFlagMethod(trApl,request); 
				model.terminate();  
				getNavigationPanel(2); 
			}   
		return "travelRejectUpdate"; 
	}
	
	public String TravelPolicyForApprover()
	{  
		TravelApplicationModel model = new TravelApplicationModel();
		model.initiate(context, session); 
    	boolean flag =model.callTravelPolicy(trApl,request,response);
    	if(flag==false)
    	{
    		addActionMessage("Please define the grade for employee.");
    	}
    	model.radioFlagMethod(trApl,request);
    	getNavigationPanel(4);
		model.terminate();   
		return "trAppPolicy"; 
	}
	 
	
	public String f9action()
	{
		try
		{
		String query = " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TRAVEL_APP_REQUEST ,TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY'), " 
					  +" DECODE(TRAVEL_APP_STATUS, 'N','New','P','Pending','A','Approved','R','Rejected'),TRAVEL_APP_ID ,"  
					  +" CENTER_NAME,DEPT_NAME,RANK_NAME,TRAVEL_APP_EMPID ,NVL(CADRE_NAME,' '), EMP_CADRE,TO_CHAR(EMP_DOB,'DD-MM-YYYY'),EMP_TOKEN  "
					  +" FROM HRMS_TMS_TRAVEL_APP "
                      +" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID) " 
					  +" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
					  +" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )  "
					  +" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )  "
					  +" LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "  ; 
					  if(trApl.isGeneralFlag()==true)
				   		 {
				   			query+=" WHERE EMP_ID ="+trApl.getUserEmpId(); 
				   		 } 
					  query+=" ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME  ";
			 
			
			String[] headers = {getMessage("f9Emp"), getMessage("f9travelRequest"), getMessage("f9AppDate") ,getMessage("f9Status")};

			String[] headerWidth = {"40","40","10","10"};

			String[] fieldNames = {"empName", "travelRequest","appDate","hidStatus","trAppId","branchName","department","designation","empId","empGrade","empGradeId","empDob","empToken"};

			int[] columnIndex = {0,1,2,3,4,5,6,7,8,9,10,11,12};

			String submitFlag = "true";

			String submitToMethod = "TravelApplication_callAppDtl.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 
			return null;
		}
	}
	
	
	/**
	 * @This method is used for show the Employee information.
	 */
	public String f9Employee()
	{
		try
		{
			
		  String query = " SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , TO_CHAR(EMP_DOB,'DD-MM-YYYY'), EMP_ID "
			  			+" FROM HRMS_EMP_OFFC  WHERE EMP_STATUS ='S'  ORDER BY  EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME	";
			 
		        
			String[] headers = {getMessage("f9EmpId"), getMessage("f9Emp")};

			String[] headerWidth = {"20", "80"};

			String[] fieldNames = {"empToken","empName" ,"empDob","empId"};

			int[] columnIndex = {0, 1,2,3};   

			String submitFlag = "true";

			String submitToMethod = "TravelApplication_callEmpDtl.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
		//	logger.error(e.getMessage());
			return null;
		}
	} // end of f9Employee
	
	
	

	
	public String f9LocationType()
	{
		try
		{
			String query = "  SELECT LOCATION_TYPE_NAME,LOCATION_TYPE_ID  FROM HRMS_TMS_LOCATION_TYPE WHERE LOCATION_TYPE_STATUS='A' "
						+"	ORDER BY LOCATION_TYPE_NAME ";
			 
		 	String[] headers = {getMessage("f9LocId")};

			String[] headerWidth = {"100" };

			String[] fieldNames = {"tourLocType","tourLocTypeId"};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 
			return null;
		}
	}
	
	
	public String f9FoodType()
	{
		try
		{
			String query = "SELECT FOOD_TYPE_NAME ,FOOD_TYPE_ID FROM HRMS_TMS_FOOD_TYPE "
                           +" WHERE FOOD_TYPE_STATUS ='A' ORDER BY FOOD_TYPE_NAME ";
			 
		 	String[] headers = {getMessage("f9FoodType")};

			String[] headerWidth = {"100" };

			String[] fieldNames = {"foodType","foodTypeId"};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 
			return null;
		}
	}
	
	
	public String f9Purpose()
	{
		try
		{
			String query = "  SELECT PURPOSE_NAME,PURPOSE_ID FROM HRMS_TMS_PURPOSE WHERE  PURPOSE_STATUS='A' ORDER BY PURPOSE_NAME ";
			 
			
			String[] headers = {getMessage("f9Purpose")};

			String[] headerWidth = {"100" };

			String[] fieldNames = {"travelPurpose","travelPurposeId"};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 
			return null;
		}
	}
	
	public String f9TravelMode()
	{
		try
		{
		  String query =" SELECT JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME, JOURNEY_ID "
					+" FROM HRMS_TMS_JOURNEY WHERE JOURNEY_STATUS='A'ORDER BY  JOURNEY_NAME,JOURNEY_CLASS_NAME ";
			
			String[] headers = {getMessage("f9TrMode")};

			String[] headerWidth = {"100" };

			String[] fieldNames = {"trModeClass"+trApl.getRowId(),"trModeClassId"+trApl.getRowId()};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 
			return null;
		}
	}
	
	public String f9HotelType()
	{
		try
		{ 		
		String query =" SELECT HOTEL_TYPE_NAME ,HOTEL_TYPE_ID  FROM HRMS_TMS_HOTEL_TYPE "
					  +" WHERE HOTEL_TYPE_STATUS ='A'ORDER BY HOTEL_TYPE_NAME ";
			
			String[] headers = {getMessage("f9HotelType")};

			String[] headerWidth = {"100" };

			String[] fieldNames = {"hotelType"+trApl.getRowId(),"hotelTypeId"+trApl.getRowId()};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 
			return null;
		}
	}
	 

	public String f9RoomType()
	{
		try
		{ 	 
		String query ="  SELECT ROOM_TYPE_NAME,ROOM_TYPE_ID  FROM HRMS_TMS_ROOM_TYPE "
				+" WHERE ROOM_TYPE_STATUS='A'ORDER BY ROOM_TYPE_NAME ";
			
			String[] headers = {getMessage("f9RoomType")};

			String[] headerWidth = {"100" };

			String[] fieldNames = {"roomType"+trApl.getRowId(),"roomTypeId"+trApl.getRowId()};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 
			return null;
		}
	} 
	
	public String f9FromPlace()
	{
		try
		{ 	 
		String query ="  SELECT LOCATION_NAME ,LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_TYPE='Ci' ORDER BY LOCATION_NAME  " ;
			 
			
			String[] headers = {getMessage("f9City")};

			String[] headerWidth = {"100" };

			String[] fieldNames = {"jourFromPlace"+trApl.getRowId(),"jourFromPlaceId"+trApl.getRowId()};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 
			return null;
		}
	}  
	
	public String f9ToPlace()
	{
		try
		{ 	 
			String query ="  SELECT LOCATION_NAME ,LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_TYPE='Ci' ORDER BY LOCATION_NAME " ;
			
			String[] headers = {getMessage("f9ToCity")};

			String[] headerWidth = {"100" };

			String[] fieldNames = {"jourToPlace"+trApl.getRowId(),"jourToPlaceId"+trApl.getRowId()};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 
			return null;
		}
	}  
	
	public String f9LocalCity()
	{
		try
		{ 	 
			String query ="  SELECT LOCATION_NAME ,LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_TYPE='Ci' ORDER BY LOCATION_NAME  " ;
			
			String[] headers = {getMessage("f9LocCity")};

			String[] headerWidth = {"100" };

			String[] fieldNames = {"localCity"+trApl.getRowId(),"localCityId"+trApl.getRowId()};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 
			return null;
		}
	}  
	
	public String f9LodgCity()
	{
		try
		{ 	 
			String query ="  SELECT LOCATION_NAME ,LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_TYPE='Ci' ORDER BY LOCATION_NAME  " ;
			
			String[] headers = {getMessage("f9LogCity")};

			String[] headerWidth = {"100" };

			String[] fieldNames = {"lodgCity"+trApl.getRowId(),"lodgCityId"+trApl.getRowId()};

			int[] columnIndex = {0,1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) { 
			return null;
		}
	}  
	
	
}
