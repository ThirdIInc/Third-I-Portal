 package org.struts.action.leave;

import org.paradyne.bean.leave.Regularization;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.leave.LeavePolicyModel;
import org.paradyne.model.leave.RegularizationApprovalModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class RegularizationApprovalAction extends ParaActionSupport {

	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(RegularizationApprovalAction.class);
	Regularization regularization;
	public void prepare_local() throws Exception {
		regularization=new Regularization();
		regularization.setMenuCode(962);
	}

	public Object getModel() {
		return regularization;
	}

	public Regularization getRegularization() {
		return regularization;
	}

	public void setRegularization(Regularization regularization) {
		this.regularization = regularization;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		regularization.setListType("pending");	
		onLoad();
	}
	
	/**
	 * METHOD ON LOAD
	 * @return
	 */
	public String onLoad() throws Exception{
		
		try {
			RegularizationApprovalModel model = new RegularizationApprovalModel();
			model.initiate(context, session);
			String status = regularization.getStatus();
			if (status.equals("")) {
				regularization.setViewApplication("View/Approve");
				model.callOnLoad(regularization, "P", request);
			} else {
				model.callOnLoad(regularization, status, request);
			}
			model.terminate();
			regularization.setBackActionName("RegularizationApproval_onLoad");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String approved() throws Exception{
		regularization.setBackActionName("RegularizationApproval_onLoad");
		RegularizationApprovalModel model = new RegularizationApprovalModel();
		model.initiate(context, session);
		model.callOnLoad(regularization,"A",request);
		model.terminate();		
		return SUCCESS;
	}
	public String sendBack() throws Exception{
		regularization.setBackActionName("RegularizationApproval_onLoad");
		RegularizationApprovalModel model = new RegularizationApprovalModel();
		model.initiate(context, session);
		model.callOnLoad(regularization,"B",request);
		model.terminate();		
		return SUCCESS;
	}
	public String reject() throws Exception{
		regularization.setBackActionName("RegularizationApproval_onLoad");
		RegularizationApprovalModel model = new RegularizationApprovalModel();
		model.initiate(context, session);
		model.callOnLoad(regularization,"R",request);
		model.terminate();		
		return SUCCESS;
	}
	/**
	 * METHOD TO SHOW APPROVER LIST
	 */
	public void getApproverList(String empCode) throws Exception {
		RegularizationApprovalModel model = new RegularizationApprovalModel();
		ReportingModel model1 = new ReportingModel();
		model1.initiate(context, session);		
		Object[][] empFlow = model1.generateEmpFlow(empCode, "Regularize");
		model.initiate(context, session);
		model.setApproverData(regularization, empFlow);
		model1.terminate();
	}
	
	/**
	 * METHOD FOR APPROVE REDRESSAL APPLICATION
	 */
	public String approveRedressalAppl() throws Exception{
		RegularizationApprovalModel model =new RegularizationApprovalModel();
		model.initiate(context, session);
		String[]leaveCode=request.getParameterValues("rLeaveCode");
		String[]rrdressalDays=request.getParameterValues("rrdressalDays");
		String[]redressalAdjStatus=request.getParameterValues("redressalAdjStatus");
		String[]rPenaltyDays=request.getParameterValues("rPenaltyDays");
		String[]redressalAdjDays=request.getParameterValues("redressalAdjDays");
		String[]rFromDate=request.getParameterValues("rFromDate");
		String result=model.approveRedressalAppl(regularization,"A",leaveCode,
				rrdressalDays,redressalAdjStatus,rPenaltyDays,redressalAdjDays,rFromDate);
		if(!result.equals("")){
			addActionMessage(result);
			String applicationCode=	regularization.getApplicationCode();
			String firstApprover=regularization.getUserEmpId();
			String secondApprover=regularization.getSecondApproverCode();
			String empCode=regularization.getEmpCode();
			if(result.equals("This application has been forwarded to next approval")){
				//Mail sent to second approver  for approval
				String applicationType = "RedressalRegularization";
				// Add approval link -pass parameters to the link
				String[]link_param=new String[3];
				String[]link_label=new String[3];
				 link_param[0] = applicationType + "#"
					+ empCode + "#" + applicationCode + "#" + "A" + "#"
					+ "..." + "#" + secondApprover;
				
				 link_param[1] = applicationType + "#"
					+ empCode + "#" + applicationCode + "#" + "R" + "#"
					+ "..." + "#" + secondApprover;
				 link_param[2] = applicationType + "#"
					+ empCode + "#" + applicationCode + "#" + "B" + "#"
					+ "..." + "#" + secondApprover;
				
				 link_label[0]="Approve";
				 link_label[1]="Reject";
				 link_label[2]="Send Back";
				/*sendMailTOSecondApprover("81", firstApprover, secondApprover, applicationCode,secondApprover,""
						, link_param, link_label);*/
				 sendMailTOSecondApprover("Redressal Mail sent to second approver  for approval", firstApprover, secondApprover, applicationCode,secondApprover,""
							, link_param, link_label);
				 
				//Mail to employee regarding first approval
				String[]link_param1=null;
				String[]link_label1=null;
				/*sendMailTOSecondApprover("82", firstApprover, empCode, applicationCode,secondApprover,"",link_param1, link_label1);*/
				sendMailTOSecondApprover("Redressal Mail to employee regarding first approval", firstApprover, empCode, applicationCode,secondApprover,"",link_param1, link_label1);
			}
			else if(result.equals("Application has been approved")){
				//Mail to employee regarding second approval
				String appQuery="SELECT NVL(REDRESSAL_KEEP_INFORM,0) FROM HRMS_REDRESSAL_HDR WHERE REDRESSAL_ID="+applicationCode;
				Object[][]keep=model.getSqlModel().getSingleResult(appQuery);
				String keepData="";
				if(keep !=null && keep.length>0 && !String.valueOf(keep[0][0]).equals("0")){
					keepData=String.valueOf(keep[0][0]);
				}
				String secondAppr=regularization.getUserEmpId();
				String[]link_param1=null;
				String[]link_label1=null;
				/*sendMailTOSecondApprover("83", secondAppr, empCode, applicationCode,"",keepData, link_param1,
						link_label1);*/
				
				sendMailTOSecondApprover("Redressal Mail to employee regarding second approval", secondAppr, empCode, applicationCode,"",keepData, link_param1,
						link_label1);
			}
		}
		else{
			addActionMessage("Application has been approved");
		}
		model.terminate();		
		return onLoad();
	}
	/**
	 * METHOD FOR REJECT REDRESSAL APPLICATION
	 */
	public String rejectRedressalAppl() throws Exception{
		logger.info("UNDER REJECT REDRESSAL APPLICATION");
		RegularizationApprovalModel model =new RegularizationApprovalModel();
		model.initiate(context, session);
		String[]leaveCode=request.getParameterValues("rLeaveCode");
		String[]rrdressalDays=request.getParameterValues("rrdressalDays");
		String[]redressalAdjStatus=request.getParameterValues("redressalAdjStatus");
		String[]rPenaltyDays=request.getParameterValues("rPenaltyDays");
		String[]redressalAdjDays=request.getParameterValues("redressalAdjDays");
		String[]rFromDate=request.getParameterValues("rFromDate");
		String result=model.approveRedressalAppl(regularization,"R",leaveCode,rrdressalDays,
				redressalAdjStatus,rPenaltyDays,redressalAdjDays,rFromDate);
		if(!result.equals("")){
			addActionMessage(result);
			String empCode=regularization.getEmpCode();
			String applicationCode=regularization.getApplicationCode();
			String secondAppr=regularization.getUserEmpId();
			
			String appQuery="SELECT NVL(REDRESSAL_KEEP_INFORM,0) FROM HRMS_REDRESSAL_HDR WHERE REDRESSAL_ID="+applicationCode;
			Object[][]keep=model.getSqlModel().getSingleResult(appQuery);
			String keepData="";
			if(keep !=null && keep.length>0 && !String.valueOf(keep[0][0]).equals("0")){
				keepData=String.valueOf(keep[0][0]);
			}	
			String[]link_param1=null;
			String[]link_label1=null;
			/*sendMailTOSecondApprover("83", secondAppr, empCode, applicationCode,"",keepData, link_param1,
					link_label1);*/			
			sendMailTOSecondApprover("Redressal Mail to employee regarding second approval", secondAppr, empCode, applicationCode,"",keepData, link_param1,
					link_label1);
		}
		else{
			addActionMessage("Application has been rejected");
		}
		model.terminate();		
		return onLoad();
	}
	
	//
	/**
	 * METHOD FOR SENDBACK REDRESSAL APPLICATION
	 */
	public String sendBackRedressalAppl() throws Exception{
		logger.info("UNDER REJECT REDRESSAL APPLICATION");
		RegularizationApprovalModel model =new RegularizationApprovalModel();
		model.initiate(context, session);
		String[]leaveCode=request.getParameterValues("rLeaveCode");
		String[]rrdressalDays=request.getParameterValues("rrdressalDays");
		String[]redressalAdjStatus=request.getParameterValues("redressalAdjStatus");
		String[]rPenaltyDays=request.getParameterValues("rPenaltyDays");
		String[]redressalAdjDays=request.getParameterValues("redressalAdjDays");
		String[]rFromDate=request.getParameterValues("rFromDate");
		String result=model.approveRedressalAppl(regularization,"B",leaveCode,rrdressalDays,
				redressalAdjStatus,rPenaltyDays,redressalAdjDays,rFromDate);
		if(!result.equals("")){
			addActionMessage(result);
			String empCode=regularization.getEmpCode();
			String applicationCode=regularization.getApplicationCode();
			String secondAppr=regularization.getUserEmpId();
			
			/*String appQuery="SELECT NVL(REDRESSAL_KEEP_INFORM,0) FROM HRMS_REDRESSAL_HDR WHERE REDRESSAL_ID="+applicationCode;
			Object[][]keep=model.getSqlModel().getSingleResult(appQuery);
			String keepData="";
			if(keep !=null && keep.length>0 && !String.valueOf(keep[0][0]).equals("0")){
				keepData=String.valueOf(keep[0][0]);
			}*/
			String[]link_param1=null;
			String[]link_label1=null;
			/*sendMailTOSecondApprover("83", secondAppr, empCode, applicationCode,"","", link_param1,
					link_label1);*/
			sendMailTOSecondApprover("Redressal Mail to employee regarding second approval", secondAppr, empCode, applicationCode,"","", link_param1,
					link_label1);
		}
		else{
			addActionMessage("Application has been send back");
		}
		model.terminate();		
		return onLoad();
	}
	
	/**
	 * METHOD FOR APPROVE swipe APPLICATION
	 */
	public String approveSwipeAppl() throws Exception{
		try {
			RegularizationApprovalModel model = new RegularizationApprovalModel();
			model.initiate(context, session);
			String fromDate[] = request.getParameterValues("date");
			String fromTime[] = request.getParameterValues("fromTime");
			String toTime[] = request.getParameterValues("toTime");
			String result = model.approveSwipeAppl(regularization, fromDate,
					fromTime, toTime);
			if (!result.equals("")) {
				addActionMessage(result);
				String applicationCode = regularization.getApplicationCode();
				String firstApprover = regularization.getUserEmpId();
				String secondApprover = regularization.getSecondApproverCode();
				String empCode = regularization.getEmpCode();
				if (result
						.equals("This application has been forwarded to next approval")) {
					//Mail sent to second approver  for approval
					String applicationType = "SwipeRegularization";
					// Add approval link -pass parameters to the link
					String[] link_param = new String[3];
					String[] link_label = new String[3];
					link_param[0] = applicationType + "#" + empCode + "#"
							+ applicationCode + "#" + "A" + "#" + "..." + "#"
							+ secondApprover;

					link_param[1] = applicationType + "#" + empCode + "#"
							+ applicationCode + "#" + "R" + "#" + "..." + "#"
							+ secondApprover;
					link_param[2] = applicationType + "#" + empCode + "#"
							+ applicationCode + "#" + "B" + "#" + "..." + "#"
							+ secondApprover;

					link_label[0] = "Approve";
					link_label[1] = "Reject";
					link_label[2] = "Send Back";
					/*sendMailTOSecondApprover("90", firstApprover, secondApprover, applicationCode,secondApprover,"", link_param,
							link_label);*/

					sendMailTOSecondApprover(
							"Attendance Regularization Mail sent to second approver  for approval",
							firstApprover, secondApprover, applicationCode,
							secondApprover, "", link_param, link_label);

					//Mail to employee regarding first approval
					String[] link_param1 = null;
					String[] link_label1 = null;
					/*sendMailTOSecondApprover("92", firstApprover, empCode, applicationCode,secondApprover,"",link_param1,link_param1);*/
					sendMailTOSecondApprover(
							"Attendance Mail to employee regarding first approval",
							firstApprover, empCode, applicationCode,
							secondApprover, "", link_param1, link_param1);
				} else if (result.equals("Application has been approved")) {
					//Mail to employee regarding second approval
					String appQuery = "SELECT NVL(SWIPE_REG_KEEP_INFORM,0) FROM HRMS_SWIPE_REG_HDR WHERE SWIPE_REG_ID="
							+ applicationCode;
					Object[][] keep = model.getSqlModel().getSingleResult(
							appQuery);
					String keepData = "";
					if (keep != null && keep.length > 0
							&& !String.valueOf(keep[0][0]).equals("0")) {
						keepData = String.valueOf(keep[0][0]);
					}
					String secondAppr = regularization.getUserEmpId();
					String[] link_param1 = null;
					String[] link_label1 = null;
					/*sendMailTOSecondApprover("93", secondAppr, empCode, applicationCode,"",keepData,link_param1,link_param1);*/
					sendMailTOSecondApprover(
							"Attendance regularization Mail to employee regarding second approval",
							secondAppr, empCode, applicationCode, "", keepData,
							link_param1, link_param1);
				}
			} else {
				addActionMessage("Application has been approved");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (regularization.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (regularization.getSource().equals("myservices")) {
			return "serviceJsp";
		} else if (regularization.getSource().equals("mytimecard")) {
			return "mytimeCard";
		}  else {
			return onLoad();
		}
		
	}
	/**
	 * METHOD FOR reject swipe APPLICATION
	 */
	public String rejectSwipeAppl() throws Exception{
		try {
			RegularizationApprovalModel model = new RegularizationApprovalModel();
			model.initiate(context, session);
			String fromDate[] = request.getParameterValues("date");
			String result = model.rejectSwipeAppl(regularization, fromDate);
			if (!result.equals("")) {
				addActionMessage(result);
				String empCode = regularization.getEmpCode();
				String applicationCode = regularization.getApplicationCode();
				String secondAppr = regularization.getUserEmpId();
				String appQuery = "SELECT NVL(SWIPE_REG_KEEP_INFORM,0) FROM HRMS_SWIPE_REG_HDR WHERE SWIPE_REG_ID="
						+ applicationCode;
				Object[][] keep = model.getSqlModel().getSingleResult(appQuery);
				String keepData = "";
				if (keep != null && keep.length > 0
						&& !String.valueOf(keep[0][0]).equals("0")) {
					keepData = String.valueOf(keep[0][0]);
				}
				String[] link_param = null;
				String[] link_label = null;
				/*sendMailTOSecondApprover("93", secondAppr, empCode, applicationCode,"",keepData,link_param,link_param);*/
				sendMailTOSecondApprover(
						"Attendance regularization Mail to employee regarding second approval",
						secondAppr, empCode, applicationCode, "", keepData,
						link_param, link_param);
			} else {
				addActionMessage("Application has been rejected");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (regularization.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (regularization.getSource().equals("myservices")) {
			return "serviceJsp";
		} else if (regularization.getSource().equals("mytimecard")) {
			return "mytimeCard";
		}  else {
			return onLoad();
		}
		//return onLoad();
	}

	/**
	 * METHOD FOR reject swipe APPLICATION
	 */
	public String sendBackSwipeAppl() throws Exception{
		try {
			RegularizationApprovalModel model = new RegularizationApprovalModel();
			model.initiate(context, session);
			String fromDate[] = request.getParameterValues("date");
			String result = model.sendBackSwipeAppl(regularization);
			if (!result.equals("")) {
				addActionMessage(result);
				 String empCode=regularization.getEmpCode();
				String applicationCode=regularization.getApplicationCode();
				String secondAppr=regularization.getUserEmpId();
				String appQuery="SELECT NVL(SWIPE_REG_KEEP_INFORM,0) FROM HRMS_SWIPE_REG_HDR WHERE SWIPE_REG_ID="+applicationCode;
				Object[][]keep=model.getSqlModel().getSingleResult(appQuery);
				String keepData="";
				if(keep !=null && keep.length>0 && !String.valueOf(keep[0][0]).equals("0")){
					keepData=String.valueOf(keep[0][0]);
				}			
				
				//sendMailTOSecondApprover("93", secondAppr, empCode, applicationCode,"",keepData); 
				String[] link_param = null;
				String[] link_label = null;
				sendMailTOSecondApprover(
						"Attendance regularization Mail to employee regarding second approval",
						secondAppr, empCode, applicationCode, "", keepData,
						link_param, link_param);
				
			} else {
				addActionMessage("Application has been send back");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (regularization.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (regularization.getSource().equals("myservices")) {
			return "serviceJsp";
		} else if (regularization.getSource().equals("mytimecard")) {
			return "mytimeCard";
		}  else {
			return onLoad();
		}
		//return onLoad();
	}
	
	/**
	 * METHOD FOR APPROVE late Reg APPLICATION
	 */
	public String approveLateRegAppl() throws Exception{
		RegularizationApprovalModel model =new RegularizationApprovalModel();
		model.initiate(context, session);
		String fromDate[]=request.getParameterValues("date");
		String result=model.approveLateRegAppl(regularization,fromDate);
		if(!result.equals("")){
			addActionMessage(result);
			String applicationCode=	regularization.getApplicationCode();
			String firstApprover=regularization.getUserEmpId();
			String secondApprover=regularization.getSecondApproverCode();
			String empCode=regularization.getEmpCode();
			if(result.equals("This application has been forwarded to next approval")){
				//Mail sent to second approver  for approval
				
				String applicationType = "LateRegularization";
				// Add approval link -pass parameters to the link
				String[]link_param=new String[3];
				String[]link_label=new String[3];
				 link_param[0] = applicationType + "#"
					+ empCode + "#" + applicationCode + "#" + "A" + "#"
					+ "..." + "#" + secondApprover;
				
				 link_param[1] = applicationType + "#"
					+ empCode + "#" + applicationCode + "#" + "R" + "#"
					+ "..." + "#" + secondApprover;
				 link_param[2] = applicationType + "#"
					+ empCode + "#" + applicationCode + "#" + "B" + "#"
					+ "..." + "#" + secondApprover;
				
				 link_label[0]="Approve";
				 link_label[1]="Reject";
				 link_label[2]="Send Back";
				
				/*sendMailTOSecondApprover("85", firstApprover, secondApprover, applicationCode,secondApprover,"", link_param, link_label);*/
				 sendMailTOSecondApprover("Late Regularization Mail sent to second approver  for approval", firstApprover, secondApprover, applicationCode,secondApprover,"", link_param, link_label);
				 
				//Mail to employee regarding first approval
				String[]link_param1=null;
				String[]link_label1=null;
				/*sendMailTOSecondApprover("87", firstApprover, empCode, applicationCode,secondApprover,"", link_param1,
						link_label1);*/
				sendMailTOSecondApprover("Late regularization Mail to employee regarding first approval", firstApprover, empCode, applicationCode,secondApprover,"", link_param1,
						link_label1);
			}
			else if(result.equals("Application has been approved")){
				//Mail to employee regarding second approval
				String appQuery="SELECT NVL(LATE_REG_KEEP_INFORM,0) FROM HRMS_LATE_REG_HDR WHERE LATE_REG_ID="+applicationCode;
				Object[][]keep=model.getSqlModel().getSingleResult(appQuery);
				String keepData="";
				if(keep !=null && keep.length>0 && !String.valueOf(keep[0][0]).equals("0")){
					keepData=String.valueOf(keep[0][0]);
				}
				String secondAppr=regularization.getUserEmpId();
				String[]link_param1=null;
				String[]link_label1=null;
				/*sendMailTOSecondApprover("88", secondAppr, empCode, applicationCode,"",keepData, link_param1, link_label1);*/
				sendMailTOSecondApprover("Late regularization Mail to employee regarding second approval", secondAppr, empCode, applicationCode,"",keepData, link_param1, link_label1);
			}
		}
		else{
			addActionMessage("Application has been approved");
		}
		model.terminate();		
		return onLoad();
	}
	/**
	 * METHOD FOR APPROVE late Reg APPLICATION
	 */
	public String rejectPTAppl() throws Exception{
		RegularizationApprovalModel model =new RegularizationApprovalModel();
		model.initiate(context, session);		
		String result=model.rejectPTAppl(regularization,"R");
		if(!result.equals("")){
			addActionMessage(result);			
				//Mail to employee regarding second approval
			String appQuery="SELECT NVL(PT_REG_KEEP_INFORM,0) FROM HRMS_PT_REG_HDR WHERE PT_REG_ID="+regularization.getApplicationCode();
			Object[][]keep=model.getSqlModel().getSingleResult(appQuery);
			String keepData="";
			if(keep !=null && keep.length>0 && !String.valueOf(keep[0][0]).equals("0")){
				keepData=String.valueOf(keep[0][0]);
			}
				String empCode=regularization.getEmpCode();
				String applicationCode=regularization.getApplicationCode();
				String secondAppr=regularization.getUserEmpId();
				String[] link_param=null;
				String[] link_label=null;
				/*sendMailTOSecondApprover("98", secondAppr, empCode, applicationCode,"",keepData,link_param,link_param);*/
				sendMailTOSecondApprover("Personal time  Mail to employee regarding second approval", secondAppr, empCode, applicationCode,"",keepData,link_param,link_param);
		}
		else{
			addActionMessage("Application has been rejected");
		}
		model.terminate();		
		return onLoad();
	}
	
	
	
	/**
	 * METHOD FOR SEND BACK APPLICATION
	 */
	public String sendBackPTAppl() throws Exception{
		RegularizationApprovalModel model =new RegularizationApprovalModel();
		model.initiate(context, session);		
		String result=model.rejectPTAppl(regularization,"B");
		if(!result.equals("")){
			addActionMessage(result);			
				//Mail to employee regarding second approval
			/*String appQuery="SELECT NVL(PT_REG_KEEP_INFORM,0) FROM HRMS_PT_REG_HDR WHERE PT_REG_ID="+applicationCode;
			Object[][]keep=model.getSqlModel().getSingleResult(appQuery);
			String keepData="";
			if(keep !=null && keep.length>0 && !String.valueOf(keep[0][0]).equals("0")){
				keepData=String.valueOf(keep[0][0]);
			}*/
				String empCode=regularization.getEmpCode();
				String applicationCode=regularization.getApplicationCode();
				String secondAppr=regularization.getUserEmpId();
				String[] link_param=null;
				String[] link_label=null;
				/*sendMailTOSecondApprover("98", secondAppr, empCode, applicationCode,"","",link_param,link_param);*/
				sendMailTOSecondApprover("Personal time  Mail to employee regarding second approval", secondAppr, empCode, applicationCode,"","",link_param,link_param);			
		}
		else{
			addActionMessage("Application has been send back");
		}
		model.terminate();		
		return onLoad();
	}
	
	/**
	 * METHOD FOR APPROVE late Reg APPLICATION
	 */
	public String approvePTAppl() throws Exception{
		RegularizationApprovalModel model =new RegularizationApprovalModel();
		model.initiate(context, session);	
		String empCode=regularization.getEmpCode();
		String applicationCode=regularization.getApplicationCode();
		
		String result=model.approvePTAppl(regularization,empCode,applicationCode);
		if(!result.equals("")){
			addActionMessage(result);		
			String firstApprover=regularization.getUserEmpId();
			String secondApprover=regularization.getSecondApproverCode();			
			if(result.equals("This application has been forwarded to next approval")){
				String keepData="";
				//Prsonal time Mail sent to second approver  for approval
				String applicationType = "Regularization";
				// Add approval link -pass parameters to the link
				String[]link_param=new String[3];
				String[]link_label=new String[3];
				 link_param[0] = applicationType + "#"
					+ empCode + "#" + applicationCode + "#" + "A" + "#"
					+ "..." + "#" + secondApprover;
				
				 link_param[1] = applicationType + "#"
					+ empCode + "#" + applicationCode + "#" + "R" + "#"
					+ "..." + "#" + secondApprover;
				 link_param[2] = applicationType + "#"
					+ empCode + "#" + applicationCode + "#" + "B" + "#"
					+ "..." + "#" + secondApprover;
				
				 link_label[0]="Approve";
				 link_label[1]="Reject";
				 link_label[2]="Send Back";
				/*sendMailTOSecondApprover("95", firstApprover, secondApprover, applicationCode,secondApprover,"",link_param,link_label);*/
				 sendMailTOSecondApprover("Prsonal time Mail sent to second approver  for approval", firstApprover, secondApprover, applicationCode,secondApprover,"",link_param,link_label);
				String[]link_param1=null;
				String[]link_label1=null;
				/*sendMailTOSecondApprover("97", firstApprover, empCode, applicationCode,secondApprover,"",link_param1,link_label1);*/
				sendMailTOSecondApprover("Personal Time Mail to employee regarding first approval", firstApprover, empCode, applicationCode,secondApprover,"",link_param1,link_label1);
			}
			else if(result.equals("Application has been approved")){
				String appQuery="SELECT NVL(PT_REG_KEEP_INFORM,0) FROM HRMS_PT_REG_HDR WHERE PT_REG_ID="+applicationCode;
				Object[][]keep=model.getSqlModel().getSingleResult(appQuery);
				String keepData="";
				if(keep !=null && keep.length>0 && !String.valueOf(keep[0][0]).equals("0")){
					keepData=String.valueOf(keep[0][0]);
				}
				//Mail to employee regarding second approval
				String secondAppr=regularization.getUserEmpId();
				String[]link_param1=null;
				String[]link_label1=null;
				/*sendMailTOSecondApprover("98", secondAppr, empCode, applicationCode,"",keepData,link_param1,link_label1);*/
				sendMailTOSecondApprover("Personal time  Mail to employee regarding second approval", secondAppr, empCode, applicationCode,"",keepData,link_param1,link_label1);
			}
		}
		else{
			addActionMessage("Application has been approved");
		}
		model.terminate();		
		return onLoad();
	}
	
	/**
	 * METHOD FOR reject late Regularization APPLICATION
	 */
	public String rejectLateRegAppl() throws Exception{
		RegularizationApprovalModel model =new RegularizationApprovalModel();
		model.initiate(context, session);
		String fromDate[]=request.getParameterValues("date");
		String lateHrsDeductFromCode[]=request.getParameterValues("lateHrsDeductFromCode");
		String lateHrsDeduct[]=request.getParameterValues("lateHrsDeduct");
		String result=model.rejectLateRegAppl(regularization,fromDate,lateHrsDeductFromCode,lateHrsDeduct,"R");
		if(!result.equals("")){
			addActionMessage(result);
			String empCode=regularization.getEmpCode();
			String applicationCode=regularization.getApplicationCode();
			String secondAppr=regularization.getUserEmpId();
			String appQuery="SELECT NVL(LATE_REG_KEEP_INFORM,0) FROM HRMS_LATE_REG_HDR WHERE LATE_REG_ID="+applicationCode;
			Object[][]keep=model.getSqlModel().getSingleResult(appQuery);
			String keepData="";
			if(keep !=null && keep.length>0 && !String.valueOf(keep[0][0]).equals("0")){
				keepData=String.valueOf(keep[0][0]);
			}
			String[]link_param1=null;
			String[]link_label1=null;
			/*sendMailTOSecondApprover("88", secondAppr, empCode, applicationCode,"",keepData,link_param1,link_label1);*/
			sendMailTOSecondApprover("Late regularization Mail to employee regarding second approval", secondAppr, empCode, applicationCode,"",keepData,link_param1,link_label1);
		}
		else{
			addActionMessage("Application has been rejected");
		}
		model.terminate();		
		return onLoad();
	}
	/**
	 * METHOD FOR send back late Regularization APPLICATION
	 */
	
	public String sendBackLateRegAppl() throws Exception{
		RegularizationApprovalModel model =new RegularizationApprovalModel();
		model.initiate(context, session);
		String fromDate[]=request.getParameterValues("date");
		String lateHrsDeductFromCode[]=request.getParameterValues("lateHrsDeductFromCode");
		String lateHrsDeduct[]=request.getParameterValues("lateHrsDeduct");
		String result=model.rejectLateRegAppl(regularization,fromDate,lateHrsDeductFromCode,lateHrsDeduct,"B");
		if(!result.equals("")){
			addActionMessage(result);
			String empCode=regularization.getEmpCode();
			String applicationCode=regularization.getApplicationCode();
			String secondAppr=regularization.getUserEmpId();
			String appQuery="SELECT NVL(LATE_REG_KEEP_INFORM,0) FROM HRMS_LATE_REG_HDR WHERE LATE_REG_ID="+applicationCode;
			Object[][]keep=model.getSqlModel().getSingleResult(appQuery);
			String keepData="";
			if(keep !=null && keep.length>0 && !String.valueOf(keep[0][0]).equals("0")){
				keepData=String.valueOf(keep[0][0]);
			}
			String[]link_param1=null;
			String[]link_label1=null;
			/*sendMailTOSecondApprover("88", secondAppr, empCode, applicationCode,"",keepData,link_param1,link_label1);*/
			sendMailTOSecondApprover("Late regularization Mail to employee regarding second approval", secondAppr, empCode, applicationCode,"",keepData,link_param1,link_label1);
		}
		else{
			addActionMessage("Application has been rejected");
		}
		model.terminate();		
		return onLoad();
	}
	
	
	/*public String sendBackLateRegAppl() throws Exception{
		RegularizationApprovalModel model =new RegularizationApprovalModel();
		model.initiate(context, session);		
		String result=model.sendBackLateRegAppl(regularization);
		if(!result.equals("")){
			addActionMessage(result);
			String empCode=regularization.getEmpCode();
			String applicationCode=regularization.getApplicationCode();
			String secondAppr=regularization.getUserEmpId();
			String appQuery="SELECT NVL(LATE_REG_KEEP_INFORM,0) FROM HRMS_LATE_REG_HDR WHERE LATE_REG_ID="+applicationCode;
			Object[][]keep=model.getSqlModel().getSingleResult(appQuery);
			String keepData="";
			if(keep !=null && keep.length>0 && !String.valueOf(keep[0][0]).equals("0")){
				keepData=String.valueOf(keep[0][0]);
			}
			sendMailTOSecondApprover("88", secondAppr, empCode, applicationCode,"","");
		}
		else{
			addActionMessage("Application has been send back");
		}
		model.terminate();		
		return onLoad();
	}*/
	
	
	/**
	 * METHOD FOR VIEW APPLICATION
	 * @return
	 */
	public String viewApplication() throws Exception{
		try {
			//flag for hidden apply,back button
			//regularization.setViewApplFlag("false");
			/**
			 * SET FLAG FOR IF TRUE===FOR PENDING APP/
			 * FALSE===APPROVED APPLICATION
			 */
			String source = request.getParameter("src");
			System.out.println("source--------------" + source);
			regularization.setSource(source);
			regularization.setBackActionName("RegularizationApproval_onLoad");
			regularization.setShowButtonFlag("false");
			/**SHOW COMMENTS OF THE APPROVER
			 * FALSE==FOR FIRST APPROVER
			 */
			regularization.setCommentsFlag("true");
			RegularizationApprovalModel model = new RegularizationApprovalModel();
			model.initiate(context, session);
			String applCode = request.getParameter("appCode");
			String swipeType = request.getParameter("swipeType");
			if (swipeType != null) {

			}
			String status = request.getParameter("status");
			if (status.equals("A") || status.equals("R")) {
				regularization.setHideApproverCommentsSectionFlag(false);
			}
			String type = request.getParameter("type");
			String empCode = request.getParameter("empCode");
			if (status.trim().equals("P") || status.trim().equals("")) {
				regularization.setShowButtonFlag("true");
			}
			String[] delDate = null;
			String[] rFromDate = null;
			regularization.setApplicationCode(applCode);
			regularization.setApplyFor(type);
			regularization.setEmpCode(empCode);
			regularization.setStatus(status);
			//model.setKeepInform(regularization,applCode);
			if (type.equals("RR")) {
				/**
				 * SET APPROVE & REJECT ACTINO NAME
				 */
				regularization.setApproveActionName("approveRedressalAppl");
				regularization.setRejectActionName("rejectRedressalAppl");
				regularization.setSendBackActionName("sendBackRedressalAppl");

				String empQuery = " SELECT HRMS_EMP_OFFC.EMP_ID, EMP_TOKEN,	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
						+ "	(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME ,TO_CHAR(REDRESSAL_APPLICATION_DATE,'DD-MM-YYYY'),EMP_GENDER,NVL(EMP_SHIFT,0)	FROM HRMS_EMP_OFFC   LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE "
						+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
						+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK  "
						+ "  INNER JOIN HRMS_REDRESSAL_HDR ON (HRMS_REDRESSAL_HDR.REDRESSAL_EMP_ID=HRMS_EMP_OFFC.EMP_ID AND REDRESSAL_ID="
						+ regularization.getApplicationCode()
						+ ")  "
						+ "  WHERE HRMS_EMP_OFFC.EMP_ID = "
						+ regularization.getEmpCode();
				model.setEmployeeInformation(regularization, empQuery);
				String hdr = "SELECT NVL(REDRESSAL_KEEP_INFORM,'0'),NVL(REDRESSAL_REASON,' '),'0',NVL(REDRESSAL_LEVEL,1) FROM HRMS_REDRESSAL_HDR WHERE REDRESSAL_ID="
						+ applCode;
				model.setKeepInform(regularization, applCode, hdr);
				model.viewRedressalApplication(regularization, applCode,
						status, delDate, rFromDate);
				String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(REDRESSAL_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
						+ "		INNER JOIN HRMS_REDRESSAL_PATH ON (HRMS_REDRESSAL_PATH.REDRESSAL_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
						+ "		WHERE REDRESSAL_ID=" + applCode;
				//REDRESSAL_PATH_COMMENTS is not null and 
				model.setApproverNameComments(regularization, query);
			} else if (type.equals("LR")) {
				/**
				 * SET APPROVE & REJECT ACTINO NAME
				 */
				System.out
						.println("statusstatusstatusstatusstatus : " + status);
				regularization.setApproveActionName("approveLateRegAppl");
				regularization.setRejectActionName("rejectLateRegAppl");
				regularization.setSendBackActionName("sendBackLateRegAppl");

				regularization.setViewApplFlag("true");
				String empQuery = " SELECT HRMS_EMP_OFFC.EMP_ID, EMP_TOKEN,	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
						+ "	(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME ,TO_CHAR(LATE_REG_APPLICATION_DATE,'DD-MM-YYYY'),EMP_GENDER,NVL(EMP_SHIFT,0)	FROM HRMS_EMP_OFFC   LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE "
						+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
						+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK  "
						+ "  INNER JOIN HRMS_LATE_REG_HDR ON (HRMS_LATE_REG_HDR.LATE_REG_EMP_ID=HRMS_EMP_OFFC.EMP_ID AND LATE_REG_ID="
						+ applCode
						+ ") "
						+ "  WHERE HRMS_EMP_OFFC.EMP_ID = "
						+ empCode;
				model.setEmployeeInformation(regularization, empQuery);
				String hdr = "SELECT NVL(LATE_REG_KEEP_INFORM,'0'),NVL(LATE_REG_REASON,' '),'0',NVL(LATE_LEVEL,1) FROM HRMS_LATE_REG_HDR WHERE LATE_REG_ID="
						+ applCode;
				model.setKeepInform(regularization, applCode, hdr);
				model.viewLateApplication(regularization, applCode, status,
						delDate, rFromDate);
				/*
				 * SET APPROVER NAME & COMMENTS
				 */
				String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(LATE_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
						+ "		INNER JOIN HRMS_LATE_REG_PATH ON (HRMS_LATE_REG_PATH.LATE_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
						+ "		WHERE  LATE_REG_ID=" + applCode;
				//LATE_REG_PATH_COMMENTS is not null and
				model.setApproverNameComments(regularization, query);
			} else if (type.equals("PT")) {
				/**
				 * SET APPROVE & REJECT ACTINO NAME
				 */
				regularization.setApproveActionName("approvePTAppl");
				regularization.setRejectActionName("rejectPTAppl");
				regularization.setSendBackActionName("sendBackPTAppl");

				regularization.setViewApplFlag("true");
				regularization.setApproverFlag("false");
				if (status.equals("P")) {
					regularization.setApproverFlag("true");
				}

				String empQuery = " SELECT HRMS_EMP_OFFC.EMP_ID, EMP_TOKEN,	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
						+ "	(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME ,TO_CHAR(PT_REG_APPLICATION_DATE,'DD-MM-YYYY'),EMP_GENDER,NVL(EMP_SHIFT,0)	FROM HRMS_EMP_OFFC   LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE "
						+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
						+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK  "
						+ "  INNER JOIN HRMS_PT_REG_HDR ON (HRMS_PT_REG_HDR.PT_REG_EMP_ID=HRMS_EMP_OFFC.EMP_ID AND PT_REG_ID="
						+ applCode
						+ ") "
						+ "  WHERE HRMS_EMP_OFFC.EMP_ID = "
						+ empCode;
				model.setEmployeeInformation(regularization, empQuery);
				String hdr = "SELECT NVL(PT_REG_KEEP_INFORM,'0'),NVL(PT_REG_REASON,' '),'0',NVL(PT_REG_LEVEL,1) FROM HRMS_PT_REG_HDR WHERE PT_REG_ID="
						+ applCode;
				model.setKeepInform(regularization, applCode, hdr);
				model.viewPersonalTimeApplication(regularization, applCode,
						status);
				/*
				 * SET APPROVER NAME & COMMENTS
				 */
				String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(PT_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
						+ "		INNER JOIN HRMS_PT_REG_PATH ON (HRMS_PT_REG_PATH.PT_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
						+ "		WHERE  PT_REG_ID=" + applCode;
				model.setApproverNameComments(regularization, query);
				getApproverList(empCode);
				return "personalTime";
			}

			else {
				/**
				 * SET APPROVE & REJECT ACTINO NAME
				 */
				regularization.setApproveActionName("approveSwipeAppl");
				regularization.setRejectActionName("rejectSwipeAppl");
				regularization.setSendBackActionName("sendBackSwipeAppl");
				String empQuery = " SELECT HRMS_EMP_OFFC.EMP_ID, EMP_TOKEN,	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
						+ "	(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME ,TO_CHAR(SWIPE_REG_APPLICATION_DATE,'DD-MM-YYYY'),EMP_GENDER,NVL(EMP_SHIFT,0)	FROM HRMS_EMP_OFFC   LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE "
						+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
						+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK  "
						+ " INNER JOIN HRMS_SWIPE_REG_HDR ON (HRMS_SWIPE_REG_HDR.SWIPE_REG_EMP_ID=HRMS_EMP_OFFC.EMP_ID AND SWIPE_REG_ID="
						+ applCode
						+ ") "
						+ "  WHERE HRMS_EMP_OFFC.EMP_ID = "
						+ empCode;
				model.setEmployeeInformation(regularization, empQuery);
				String hdr = "SELECT NVL(SWIPE_REG_KEEP_INFORM,'0'),NVL(SWIPE_REG_REASON,' '),NVL(SWIPE_REG_TYPE,0),NVL(SWIPE_LEVEL,1) FROM HRMS_SWIPE_REG_HDR WHERE SWIPE_REG_ID="
						+ applCode;
				model.setKeepInform(regularization, applCode, hdr);
				model.viewSwipeApplication(regularization, applCode, status,
						delDate, rFromDate);
				/*
				 * SET APPROVER NAME & COMMENTS
				 */
				String query = "SELECT EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME,NVL(SWIPE_REG_PATH_COMMENTS,' ') FROM HRMS_EMP_OFFC  "
						+ "		INNER JOIN HRMS_SWIPE_REG_PATH ON (HRMS_SWIPE_REG_PATH.SWIPE_REG_PATH_ASSESS_BY=HRMS_EMP_OFFC.EMP_ID) "
						+ "		WHERE  SWIPE_REG_ID=" + applCode;
				//SWIPE_REG_PATH_COMMENTS is not null and
				model.setApproverNameComments(regularization, query);
			}
			model.terminate();
			getApproverList(empCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "regularizationApprAppl";
	}
	/**
	 * MEHTOD FOR DELETE FROM FROM REQULARIZATION APPLICATION
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteRedressal() throws Exception {
		String date[] = request.getParameterValues("lateCheckBox");	
		String rFromDate[] = request.getParameterValues("rFromDate");	
		String empCode = regularization.getEmpCode();
		String applCode=regularization.getApplicationCode();
		String status=regularization.getStatus();
		// SET POLICY CODE
		regularization.setPolicyCode(getLeavePolicyCode(empCode));
		RegularizationApprovalModel model = new RegularizationApprovalModel();
		model.initiate(context, session);
		/**
		 * DISPALY INFORM TO LIST
		 */
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String keepInform[] = request.getParameterValues("keepInform");
		model.addInformList(regularization, keepInformCode, keepInform, "");
		model.viewRedressalApplication(regularization,applCode,status,date,rFromDate);
		model.terminate();
		getApproverList(empCode);
		return "regularizationApprAppl";
	}
	/**
	 * MEHTOD FOR DELETE FROM FROM REQULARIZATION APPLICATION
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteLate() throws Exception {
		String date[] = request.getParameterValues("date");
		String delDate[] = request.getParameterValues("sCheck");
		String empCode = regularization.getEmpCode();
		String applCode=regularization.getApplicationCode();
		String status=regularization.getStatus();
		String type = regularization.getApplyFor();
		// SET POLICY CODE
		regularization.setPolicyCode(getLeavePolicyCode(empCode));
		RegularizationApprovalModel model = new RegularizationApprovalModel();
		model.initiate(context, session);
		/**
		 * DISPALY INFORM TO LIST
		 */
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String keepInform[] = request.getParameterValues("keepInform");
		model.addInformList(regularization, keepInformCode, keepInform, "");
		if(type.equals("LR")){	
		model.viewLateApplication(regularization,applCode,status,date,delDate);
		}
		else{
			model.viewSwipeApplication(regularization,applCode,status,date,delDate);
		}
		model.terminate();
		getApproverList(empCode);
		return "regularizationApprAppl";
	}
	public String addInformListForRedressal() throws Exception {
		
		String type = regularization.getApplyFor();
		String empCode = regularization.getEmpCode();
		String applCode=regularization.getApplicationCode();
		String status=regularization.getStatus();		
		String date[] = request.getParameterValues("lateCheckBox");	
		String rFromDate[] = request.getParameterValues("rFromDate");
		String keepInformCode[] = request.getParameterValues("keepInformCode");
		String keepInform[] = request.getParameterValues("keepInform");
		String getDate[] = request.getParameterValues("date");
		String delDate[] = request.getParameterValues("sCheck");
		RegularizationApprovalModel model = new RegularizationApprovalModel();
		model.initiate(context, session);
		model.addInformList(regularization, keepInformCode, keepInform, "add");	
		if(type.equals("RR")){					
		model.viewRedressalApplication(regularization,applCode,status,date,rFromDate);
		}
		else if(type.equals("LR")){	
			
			model.viewLateApplication(regularization,applCode,status,getDate,delDate);
			}	
		else{			
			model.viewSwipeApplication(regularization,applCode,status,getDate,delDate);
		}
		
		model.terminate();
		regularization.setInformCode("");
		regularization.setInformName("");
		regularization.setInformToken("");
		getApproverList(empCode);
		return "regularizationApprAppl";
	}
	
	public String getLeavePolicyCode(String empId) {
		String leavePolicyCode = "";
		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);
		leavePolicyCode = model.getLeavePolicy(empId);
		model.terminate();
		return leavePolicyCode;
	}
	/**
	 * Mail sent to second approver  for approval	
	 * sendMailTOSecondApprover("97", firstApprover, empCode, applicationCode,"");
	 */
	public String sendMailTOSecondApprover(String templateName,String firstApprover,String secondApprover,String applicationCode
			,String secondApp,String keepData,String[] link_param,String[] link_label) throws Exception{
			try {		
			Object[][]eventData = null;
			Object[][]templateData=null;
			RegularizationApprovalModel model = new RegularizationApprovalModel();
			model.initiate(context, session);
			try {
				MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
				processAlerts.initiate(context, session);
				String module = "Regularization";
				processAlerts.removeProcessAlert(applicationCode, module);
				processAlerts.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template.setEmailTemplate(templateName);
				System.out.println("############### APPROVE TEMPLATE NAME : "+templateName);
				System.out.println("FIRST APPROVER : "+firstApprover);
				template.getTemplateQueries();
				//from email id
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL			
				templateQuery1.setParameter(1, firstApprover);				

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL			
				templateQuery2.setParameter(1, secondApprover);					
				
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);		
				templateQuery3.setParameter(1, applicationCode);

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, firstApprover);

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, applicationCode);
				
				EmailTemplateQuery templateQuery6 = template
				.getTemplateQuery(6);
				templateQuery6.setParameter(1, applicationCode);
				
				if(!secondApp.equals("")){
				EmailTemplateQuery templateQuery7 = template
				.getTemplateQuery(7);
				templateQuery7.setParameter(1, secondApp);				
				}
				template.configMailAlert();				 
				String query = " SELECT  decode(SWIPE_REG_STATUS,'P','Pending','A','Approved','R','Rejected','B','SentBack'),SWIPE_REG_STATUS FROM HRMS_SWIPE_REG_HDR "
					+" WHERE SWIPE_REG_ID="+applicationCode;
				
				Object statusdata[][] =model.getSqlModel().getSingleResult(query);
				String status ="";
				String linkstatus ="";
				if(statusdata!=null && statusdata.length>0)
				{
					status =String.valueOf(statusdata[0][0]);
					linkstatus=String.valueOf(statusdata[0][1]);
				}					
				if(templateName.equals("Attendance regularization Mail to employee regarding second approval"))
				{
					String applicant =secondApprover;
					if(linkstatus.equals("B")){						
						String link = "/leaves/Regularization_viewApplication.action";						
						String linkParam ="appCode="+applicationCode+"&status="+linkstatus+"&type=SR";					 
						template.sendProcessManagerAlert("", "Regularization", "A",
								applicationCode, "1", linkParam, link,
								status, applicant, "", "",
								applicant, firstApprover);
						
						linkParam ="appCode="+applicationCode+"&status=A&type=SR";
						System.out.println("linkParam  "+linkParam);

						template.sendProcessManagerAlert(firstApprover,
								"Regularization", "I", applicationCode, "1",
								linkParam, link, status, applicant,
								"0", keepData, "", firstApprover);
					}
					else{
						String link = "/leaves/Regularization_viewApplication.action";						
						String linkParam ="appCode="+applicationCode+"&status="+linkstatus+"&type=SR";						 				
						template.sendProcessManagerAlert(firstApprover,
								"Regularization", "I", applicationCode, "1",
								linkParam, link, status, applicant,
								"0", keepData, applicant,
								firstApprover);
					}										
				}							 
				if(!keepData.equals("")){
					template.sendApplicationMailToKeepInfo(keepData);
				}
				if(link_param !=null && link_param.length>0){
					template.addOnlineLink(request, link_param, link_label);
					}
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();	
			//}		
		//}		
			} catch (Exception e) {
				e.printStackTrace();
			}		
		return "";
	}
}
