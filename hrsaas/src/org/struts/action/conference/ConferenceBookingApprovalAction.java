package org.struts.action.conference;

import org.paradyne.bean.conference.ConferenceBookingApproval;
import org.paradyne.model.conference.ConferenceBookingApprovalModel;
import org.paradyne.model.conference.ConferenceBookingModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Tarun Chaturvedi
 * @date 04-04-2008
 * ConferenceBookingApprovalAction class to change the status 
 * of the application from pending to approved or rejected and also to forward the 
 * application to the next approver
 */
public class ConferenceBookingApprovalAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;

	ConferenceBookingApproval confApproval;
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ConferenceBookingApprovalAction.class);
	
	@Override
	public void prepare_local() throws Exception {
		confApproval = new ConferenceBookingApproval();
		confApproval.setMenuCode(566);
	}

	public Object getModel() {
		return confApproval;
	}
	
	public ConferenceBookingApproval getConfApproval() {
		return confApproval;
	}

	public void setConfApproval(ConferenceBookingApproval confApproval) {
		this.confApproval = confApproval;
	}
	
	public String input(){
		ConferenceBookingApprovalModel model = new ConferenceBookingApprovalModel();
		model.initiate(context, session);
		try {
			confApproval.setConfAppStatus("P");
			model.showApplications(confApproval);		
			confApproval.setStatusIterator("Pending");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return "success";
	}
	/* method name : prepare_withLoginProfileDetails 
	 * purpose     : to display the pending applications in tabular format
	 * return type : void
	 * parameter   : none
	 */
	/*public void prepare_withLoginProfileDetails()throws Exception{
		 logger.info("prepare_withLoginProfileDetails------------");
		 confApproval.setConfAppStatus("P");		 
		 ConferenceBookingApprovalModel model = new ConferenceBookingApprovalModel();
		 model.initiate(context, session);
		 model.showApplications(confApproval);
		 model.terminate();
	}*/
	
	/** 
	 * purpose     : to display the applications in tabular 
	 * 					format as per the selected status
	 * return type : String
	 * parameter   : none
	 */
	public String ckeckdata(){
		try {
			String status = request.getParameter("status");
			logger.info("status------------" + status);
			confApproval.setConfAppStatus(status);
			String stat = "";
			//String statusIterator = "";
			if (status.equals("P") || status.equals(" ")) {
				logger.info("pending-------------");
				stat = "Pending List";
				confApproval.setStatusIterator("Pending");
				
				confApproval.setApprflag("false");
			} //end of if
			else if (status.equals("A")) {
				stat = "Approved List";
				confApproval.setStatusIterator("Approved");
				logger.info("approved-------------");
				confApproval.setApprflag("true");
				confApproval.setApproveFlag(true);
			} //end of else if
			else if (status.equals("R")||status.equals("C") ) {
				stat = "Rejected List";
				confApproval.setStatusIterator("Rejected");
				logger.info("Rejected-------------");
				confApproval.setApprflag("true");
			} //end of else if
			else if (status.equals("B") ) {
				logger.info("pending-------------");
				stat = "Pending List";
				confApproval.setStatusIterator("Send Back");
				
				confApproval.setApprflag("false");
			} //end of if
			else if (status.equals("H")) {
				stat = "On Hold List";
				confApproval.setStatusIterator("On Hold");
				logger.info("on hold-------------");
				confApproval.setApprflag("false");
			} //end of else if
			request.setAttribute("stat", stat);
			ConferenceBookingApprovalModel model = new ConferenceBookingApprovalModel();
			model.initiate(context, session);
			model.showApplications(confApproval);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/** method name : saveApproval 
	 * purpose     : to change the status of the selected application
	 * 					or to forward the application to the next approver
	 * return type : String
	 * parameter   : none
	 */
	public String saveApproval(){
		logger.info("in saveApproval method");
		boolean result = false;		
		 try {
			ConferenceBookingApprovalModel model = new ConferenceBookingApprovalModel();
			model.initiate(context, session);
			String appStatus = confApproval.getConfAppStatus();
			String[] confCode = request.getParameterValues("conferenceID");
			String[] empCode = request.getParameterValues("empID");
			String[] level = request.getParameterValues("level");
			String[] status = request.getParameterValues("status");
			String[] comments = request.getParameterValues("comment");
			String applnStatus = "";
			confApproval.setHiddenStatus(appStatus);
			boolean isRoomAvailableGlo = true;
			for (int i = 0; i < status.length; i++) {
				confApproval.setConfAppStatus(status[i]);
				logger.info("comments---------" + comments[i]);
				Object[][] empFlow = null;
				if (status[i].equals("B")) {
					applnStatus = model.sentBackApplication(confApproval,
							status[i], confCode[i], empCode[i], comments[i]);
					result = true;
				}
				String isRoomAvailable = "true";
				if (!(status[i].equals("P"))) {
					if (status[i].equals("A")) {
						isRoomAvailable = model
								.checkConfRoomAvailabilty(confCode[i]);
						if (isRoomAvailable.equals("true")) {

							empFlow = generateEmpFlow(empCode[i], "Confere",
									Integer.parseInt(level[i]) + 1);
							//logger.info("empflow--------"+empFlow+"  length "+empFlow.length);
							applnStatus = model.changeApplStatus(confApproval,
									empFlow, String.valueOf(confCode[i]),
									empCode[i]);
							result = true;
							//isRoomAvailableGlo =false;
						} else {
							isRoomAvailableGlo = false;
						}
					} //end of if
					if (isRoomAvailable.equals("true")) {
						applnStatus = model.forward(confApproval, status[i],
								confCode[i], empCode[i], comments[i]);
					} else {
						isRoomAvailableGlo = false;
					}
					result = true;
					//------------------------Process Manager Alert------------------------start
					String applnID = String.valueOf(confCode[i]);
					String module = "Conference";
					String applicant = "", oldApprover = "", newApprover = "";
					try {
						applicant = String.valueOf(empCode[i]);
						oldApprover = confApproval.getUserEmpId();
						newApprover = String.valueOf(empFlow[0][0]);
					} catch (Exception e) {
						logger.error(e);
					}
					String alertLevel = String.valueOf(Integer
							.parseInt(level[i]) + 1);
					model.sendApprovalAlert(request, applnID, module,
							applicant, oldApprover, newApprover, alertLevel,
							applnStatus);
					//------------------------Process Manager Alert------------------------end
				} //end of if
			} //end of for loop
			if (result && isRoomAvailableGlo) {
				addActionMessage(getText("addMessage", ""));
			} //end of if
			else if (!isRoomAvailableGlo) {
				addActionMessage("One or more applications cant be approved as Conference room is busy for specified time and date.");
			} //end of else
			//model.saveValue (approval,reqCode,checkStatus);
			model.showApplications(confApproval);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ckeckdata();	
	}
	
	public String cancelApp(){
		ConferenceBookingApprovalModel model =new ConferenceBookingApprovalModel();
		model.initiate(context, session);
		try{
			confApproval.setConfAppStatus("P");
			model.showApplications(confApproval);		
			confApproval.setStatusIterator("Pending");
			model.terminate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
}
