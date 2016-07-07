package org.struts.action.Asset;
import org.paradyne.bean.Asset.AssetApproval;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.Asset.AssetApprovalModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author mangeshj
 * 
 */
public class AssetApprovalAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	AssetApproval assetApproval;

	@Override
	public void prepare_local() throws Exception {
		assetApproval = new AssetApproval();
		assetApproval.setMenuCode(634);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return assetApproval;
	}

	public String input() {
		try {
			AssetApprovalModel model = new AssetApprovalModel();
			model.initiate(context, session);
			String stat = "";
			model.getRecords(assetApproval, "P", request);
			stat = "Pending List";
			// ltcClaimApp.setApprflag("false");
			request.setAttribute("stat", stat);
			logger.info("inside prepare_withlogin getApprflag=="
					+ assetApproval.getApprflag());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (assetApproval.getSource().equals("mymessages")) {
			return "mymessages";
		} else if (assetApproval.getSource().equals("myservices")) {
			return "serviceJsp";
		} else {
			return INPUT;
		}
	}

	public String checkData() throws Exception {
		AssetApprovalModel model = new AssetApprovalModel();
		model.initiate(context, session);
		String status = request.getParameter("status"); // empReqAppr.getStatus();
		logger
				.info("statussssssssssssssssssssssssssssssssssssssssssssssssssssssss"
						+ status);
		status = String.valueOf(status.charAt(0));
		String stat = "";
		if (status.equals("F"))
			status = "P";
		model.getRecords(assetApproval, status, request);
		if (status.equals("P"))
			stat = "Pending List";
		else if (status.equals("A"))
			stat = "Approved List";
		else if (status.equals("R"))
			stat = "Rejected List";
		if (!(status.equals("P"))) {
			assetApproval.setApprflag("true");

		}
		request.setAttribute("stat", stat);
		model.terminate();

		return SUCCESS;
	}

	public String save() {
		logger.info("in saveApproval method");

		boolean result = false;
		int j = 0;

		AssetApprovalModel model = new AssetApprovalModel();
		model.initiate(context, session);

		String appStatus = assetApproval.getStatus();
		String[] assetCode = request.getParameterValues("assetCode");
		String[] empCode = request.getParameterValues("empID");
		String[] level = request.getParameterValues("level");
		String[] status = request.getParameterValues("checkStatus");
		String[] comments = request.getParameterValues("comment");
		logger.info("appStatus---------" + appStatus);
		String applnStatus = "";
		for (int i = 0; i < status.length; i++) {
			logger.info("assetCode---------" + assetCode[i]);
			logger.info("empCode---------" + empCode[i]);
			logger.info("level---------" + level[i]);
			logger.info("status---------" + status[i]);
			Object[][] empFlow = null;
			if (status[i].equals("B")) {
				model.sentbackApplication(assetApproval, status[i],
						assetCode[i], empCode[i], comments[i]);
				result = true;
			}
			if (!(status[i].equals("P"))) {

				applnStatus = model.forward(assetApproval, status[i],
						assetCode[i], empCode[i], comments[i], request);
				result = true;

				if (status[i].equals("A")) {
					empFlow = generateEmpFlow(empCode[i], "Asset", Integer
							.parseInt(level[i]) + 1);
					applnStatus = model.changeApplStatus(assetApproval,
							empFlow, String.valueOf(assetCode[i]), empCode[i],
							request);
					result = true;
				}

				// ------------------------Process Manager
				// Alert------------------------start
				String applnID = String.valueOf(assetCode[i]);
				String module = "Asset";

				String applicant = "", oldApprover = "", newApprover = "";
				try {
					applicant = String.valueOf(empCode[i]);
					oldApprover = assetApproval.getUserEmpId();
					newApprover = String.valueOf(empFlow[0][0]);
				} catch (Exception e) {
					logger.error(e);
				}
				String alertLevel = String
						.valueOf(Integer.parseInt(level[i]) + 1);
				// sendApprovalAlert(applnID, module, applicant, oldApprover,
				// newApprover, alertLevel, applnStatus);
				// ------------------------Process Manager
				// Alert------------------------end

			}

		}
		if (result) {
			addActionMessage(getText("addMessage", ""));
		} else {
			addActionMessage("Record status can not be changed !");
		}

		// model.saveValue (approval,reqCode,checkStatus);

		model.getRecords(assetApproval, appStatus, request);
		model.terminate();
		return "success";
	}

	public String callApprove() {
		try {
			String result = "No";
			AssetApprovalModel approval_model = new AssetApprovalModel();
			approval_model.initiate(context, session);

			Object[][] empFlow = null;
			System.out.println("The status : =============== : "+assetApproval.getHiddenStatus());
			result = approval_model.approverecord(assetApproval
					.getHiddenStatus(), assetApproval.getAppcomment(),
					assetApproval.getEmpCode(), assetApproval.getCode(),
					assetApproval.getUserEmpId(), request, assetApproval
							.getApplicationLevel());

			if (result.equals("rejected")) {
				addActionMessage("Application rejected successfully");
			} else if (result.equals("sendback")) {
				addActionMessage("Application sent back successfully");
			} else {
				addActionMessage("Application approved successfully");
				empFlow = generateEmpFlow(assetApproval.getEmpCode(), "Asset", assetApproval.getApplicationLevel()+ 1);
			}

			String applnStatus = "";

			applnStatus = result;
			// ------------------------Process Manager
			// Alert------------------------start
			String applnID = String.valueOf(assetApproval.getCode());
			String module = "Asset";

			String applicant = "", oldApprover = "", newApprover = "";
			try {
				applicant = String.valueOf(assetApproval.getEmpCode());
				oldApprover = assetApproval.getUserEmpId();
				newApprover = String.valueOf(empFlow[0][0]);
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
			String alertLevel = "";
			alertLevel = String
			.valueOf(assetApproval.getApplicationLevel() + 1);
			sendApprovalAlert(applnID, module, applicant, oldApprover,
					newApprover, alertLevel, applnStatus,assetApproval.getUserEmpId());
			// ------------------------Process Manager
			// Alert------------------------end
			// sendMailToApplicant(applnID, module, applicant,
			// applnStatus,oldApprover,newApprover);

			approval_model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
			return input();
		}
		
		if (assetApproval.getSource().equals("mymessages")) {
			return "mymessages";
		} else {
			return input();
		}
		//return input();
	}

	public void sendMailToApplicant(String applnID, String module,
			String applicant, String applStatus) {
		try {

			EmailTemplateBody template1 = new EmailTemplateBody();
			template1.initiate(context, session);
			template1.setEmailTemplate("ASSET - APPROVER TO APPLICANT");
			template1.getTemplateQueries();

			EmailTemplateQuery templateQuery11 = template1.getTemplateQuery(1); // FROM
																				// EMAIL
			templateQuery11.setParameter(1, assetApproval.getUserEmpId());

			EmailTemplateQuery templateQuery12 = template1.getTemplateQuery(2); // TO
																				// EMAIL
			templateQuery12.setParameter(1, applicant);

			EmailTemplateQuery templateQuery13 = template1.getTemplateQuery(3);
			templateQuery13.setParameter(1, applnID);

			EmailTemplateQuery templateQuery14 = template1.getTemplateQuery(4);
			templateQuery14.setParameter(1, assetApproval.getUserEmpId());

			EmailTemplateQuery templateQuery15 = template1.getTemplateQuery(5);
			templateQuery15.setParameter(1, assetApproval.getUserEmpId());

			EmailTemplateQuery templateQuery16 = template1.getTemplateQuery(6);
			templateQuery16.setParameter(1, applnID);

			EmailTemplateQuery templateQuery17 = template1.getTemplateQuery(7);
			templateQuery17.setParameter(1, applnID);

			EmailTemplateQuery templateQuery18 = template1.getTemplateQuery(8);
			templateQuery18.setParameter(1, applnID);
			template1.configMailAlert();
			

			try {

				template1.sendApplicationMail();
			} catch (Exception e) {
				logger.error(e);
			}
			template1.clearParameters();
			template1.terminate();
			if (applStatus.equals("approved")) {
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				String assigner = getAssignerCode(applicant);
				template.setEmailTemplate("ASSET -FINAL APPROVAL TO ASSIGNER");

				module = "Asset";
				String msgType = "A";
				String alertLevel = "1";
				template.getTemplateQueries();

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery5.setParameter(1, assetApproval.getUserEmpId());

				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery6.setParameter(1, assigner);

				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(3);
				templateQuery7.setParameter(1, applnID);

				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(4);
				templateQuery8.setParameter(1, assetApproval.getUserEmpId());

				EmailTemplateQuery templateQuery9 = template
						.getTemplateQuery(5);
				templateQuery9.setParameter(1, assigner);

				EmailTemplateQuery templateQuery10 = template
						.getTemplateQuery(6);
				templateQuery10.setParameter(1, applnID);

				EmailTemplateQuery templateQuery20 = template
						.getTemplateQuery(7);
				templateQuery20.setParameter(1, applnID);

				EmailTemplateQuery templateQuery21 = template
						.getTemplateQuery(8);
				templateQuery21.setParameter(1, applnID);
				template.configMailAlert();
				String[] link_parameter = new String[3];
				String[] link_label = new String[3];
				// String applicationType = "TYD";
				String applicationType = "AssetAssignAppl";

				link_parameter[0] = applicationType + "#" + assigner
						+ "#applicationDtls#";

				String link = "/asset/AssetEmployee_showForAssignment.action?hiddenAssetCode="
						+ applnID + "$status=A";
				// link= PPEncrypter.encrypt(link);
				System.out.println("applicationDtls  ..." + link);
				link_parameter[0] += link;
				link_label[0] = "online assign";
				template.addOnlineLink(request, link_parameter, link_label);
				template.clearParameters();
				template.terminate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendApprovalAlert(String applnID, String module,
			String applicant, String oldApprover, String newApprover,
			String alertLevel, String applStatus,String loginEmpId ) {
		try {
			
			 
			
			
			/**
			 * Remove process manager entry from mypage.
			 */
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			processAlerts.removeProcessAlert(applnID,module);
			
			String alertlink = "";
			String alertlinkParam = "";
			String  alternateApprover="";
			AssetApprovalModel model=new AssetApprovalModel();
			model.initiate(context, session);
			String keepInfo=model.getKeepInfoIdList(applnID);
			model.terminate();
			String empID = "", msgType = "";
			
			/**
			 * process manager aletrty logic
			 */
			Object[][]empFlow =null;
			if(Integer.parseInt(alertLevel)==1)
			{	
				empFlow = generateEmpFlow(applicant, "Asset",
					Integer.parseInt(alertLevel) );
			}
			else{
				empFlow = generateEmpFlow(applicant, "Asset",
						Integer.parseInt(alertLevel) );
			}

			if (!newApprover.equals("")) {
				// send alert from oldApprover to newApprover
				System.out
						.println("########### first approver ######################");
				empID = newApprover;
				msgType = "A";

				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				template.setEmailTemplate("ASSET - APPROVER1 TO APPROVER2");

				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, newApprover);

				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID); // application
															// details

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, oldApprover); // current
																// approver
																// details

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, newApprover); // next approver
																// details

				EmailTemplateQuery templateQuery26 = template
						.getTemplateQuery(6);
				templateQuery26.setParameter(1, applnID);

				EmailTemplateQuery templateQuery27 = template
						.getTemplateQuery(7);
				templateQuery27.setParameter(1, applnID);

				EmailTemplateQuery templateQuery28 = template
						.getTemplateQuery(8);
				templateQuery28.setParameter(1, applnID);

				template.configMailAlert();
				String[] link_parameter = new String[3];
				String[] link_label = new String[3];
				// String applicationType = "TYD";
				String applicationType = "AssetAppl";

				link_parameter[0] = applicationType + "#" + newApprover
						+ "#applicationDtls#";

				String link = "/asset/AssetEmployee_callByApprover.action?hiddenAssetCode="
						+ applnID + "$status=P$applicationLevel="+alertLevel;
				// link= PPEncrypter.encrypt(link);
				System.out.println("applicationDtls  ..." + link);
				link_parameter[0] += link;
				link_label[0] = "here";
				try {
					template.sendApplicationMailToKeepInfo(keepInfo);
				} catch (Exception e) {
					logger.error(e);
				}
				template.addOnlineLink(request, link_parameter, link_label);
			
			 
				/**
				 * 	for process manager alert logic
				 */
				
				try {
					alertlink = "/asset/AssetEmployee_callByApprover.action";
					alertlinkParam = "hiddenAssetCode=" + applnID
							+ "&status=P&applicationLevel="+alertLevel+"&empCode="+applicant ;
					
					template.sendProcessManagerAlert(newApprover, module,
							msgType, applnID, alertLevel, alertlinkParam,
							alertlink, applStatus, applicant, "", "", "",oldApprover);
				 
				} catch (Exception e) {
					logger.error(e);
				}
		 	
				template.clearParameters();
				template.terminate();

				// send alert from oldApprover to applicant
				empID = applicant;
				msgType = "I";

				EmailTemplateBody template1 = new EmailTemplateBody();
				template1.initiate(context, session);
				template1.setEmailTemplate("ASSET - APPROVER TO APPLICANT");
				template1.getTemplateQueries();

				EmailTemplateQuery templateQuery11 = template1
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery11.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery12 = template1
						.getTemplateQuery(2); // TO EMAIL
				templateQuery12.setParameter(1, applicant);

				EmailTemplateQuery templateQuery13 = template1
						.getTemplateQuery(3);
				templateQuery13.setParameter(1, applnID);

				EmailTemplateQuery templateQuery14 = template1
						.getTemplateQuery(4);
				templateQuery14.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery15 = template1
						.getTemplateQuery(5);
				templateQuery15.setParameter(1, newApprover);

				EmailTemplateQuery templateQuery16 = template1
						.getTemplateQuery(6);
				templateQuery16.setParameter(1, applnID);

				EmailTemplateQuery templateQuery17 = template1
						.getTemplateQuery(7);
				templateQuery17.setParameter(1, applnID);

				EmailTemplateQuery templateQuery18 = template1
						.getTemplateQuery(8);
				templateQuery18.setParameter(1, applnID);
				template1.configMailAlert();
				
				  alternateApprover = (empFlow != null && !String
							.valueOf(empFlow[0][3]).equals("0")) ? String
							.valueOf(empFlow[0][3]) : "";
							
				try {
					alertlink = "/asset/AssetEmployee_callforedit.action";
					alertlinkParam = "hiddencode=" + applnID + "&hiddenStatus=P";
					template1.sendProcessManagerAlert(oldApprover, module, "I",
							applnID, alertLevel, alertlinkParam, alertlink, "Pending",
							applicant, alternateApprover, keepInfo, applicant,oldApprover);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
	 		
				try {
					template1.sendApplicationMailToKeepInfo(keepInfo);
				} catch (Exception e) {
					logger.error(e);
				}
				try {

					template1.sendApplicationMail();
				} catch (Exception e) {
					logger.error(e);
				}
				template1.clearParameters();
				template1.terminate();
			} else {
				// send alert from oldApprover to applicant
				System.out
						.println("############# Applicant ###########################");
				empID = applicant;
				msgType = "I";

				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				template.setEmailTemplate("ASSET -FINAL APPROVER TO APPLICANT");

				template.getTemplateQueries();

				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, applicant);

				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, applnID);

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, oldApprover);

				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, applnID);

				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, applnID);

				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);
				templateQuery8.setParameter(1, applnID);

				template.configMailAlert();
				try {
					template.sendApplicationMailToKeepInfo(keepInfo);
				} catch (Exception e) {
					logger.error(e);
				}
				
				String str="";
				if(applStatus.equals("approved"))
				{
					str ="A";
				}
				if(applStatus.equals("rejected"))
				{
					str ="R";
				}
				if(applStatus.equals("forwarded"))
				{
					str ="F";
				}
				if(applStatus.equals("sendback"))
				{
					str ="B";
				}
					
				
				if (str.equals("B")) {
					
					alertlink = "/asset/AssetEmployee_callforedit.action";
					alertlinkParam = "hiddencode=" + applnID + "&hiddenStatus="+str;
					applStatus = "SentBack";
					template.sendProcessManagerAlert("", module, "A",
							applnID, alertLevel, alertlinkParam, alertlink,
							applStatus, applicant, "", "",
							applicant, oldApprover);
					
					alertlinkParam = "hiddencode=" + applnID + "&hiddenStatus=R";

					template.sendProcessManagerAlert(oldApprover,
							module, "I", applnID, alertLevel,
							alertlinkParam, alertlink, applStatus, applicant,
							alternateApprover, keepInfo, "", oldApprover);

				}
				else{
					try {
						alertlink = "/asset/AssetEmployee_callforedit.action";
						alertlinkParam = "hiddencode=" + applnID + "&hiddenStatus="+str;
						template.sendProcessManagerAlert(oldApprover, module, "I",
								applnID, alertLevel, alertlinkParam, alertlink, applStatus,
								applicant, alternateApprover, keepInfo, applicant,oldApprover);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				template.clearParameters();

				if (applStatus.equals("approved")) {
					String assigner = getAssignerCode(applicant);
					template.setEmailTemplate("ASSET -FINAL APPROVAL TO ASSIGNER");
					module = "Asset";
					msgType = "A";
					alertLevel = "1";
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery30 = template
							.getTemplateQuery(1); // FROM EMAIL
					templateQuery30.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery31 = template
							.getTemplateQuery(2); // TO EMAIL
					templateQuery31.setParameter(1, assigner);

					EmailTemplateQuery templateQuery32 = template
							.getTemplateQuery(3);
					templateQuery32.setParameter(1, applnID);

					EmailTemplateQuery templateQuery33 = template
							.getTemplateQuery(4);
					templateQuery33.setParameter(1, oldApprover);

					EmailTemplateQuery templateQuery9 = template
							.getTemplateQuery(5);
					templateQuery9.setParameter(1, assigner);

					EmailTemplateQuery templateQuery10 = template
							.getTemplateQuery(6);
					templateQuery10.setParameter(1, applnID);

					EmailTemplateQuery templateQuery20 = template
							.getTemplateQuery(7);
					templateQuery20.setParameter(1, applnID);

					EmailTemplateQuery templateQuery21 = template
							.getTemplateQuery(8);
					templateQuery21.setParameter(1, applnID);
					template.configMailAlert();
					String[] link_parameter = new String[3];
					String[] link_label = new String[3];
					String applicationType = "AssetAssignAppl";

					link_parameter[0] = applicationType + "#" + assigner
							+ "#applicationDtls#";

					String link = "/asset/AssetEmployee_showForAssignment.action?hiddenAssetCode="
							+ applnID + "$status=A";
					System.out.println("applicationDtls  ..." + link);
					link_parameter[0] += link;
					link_label[0] = "online assign";
					template.addOnlineLink(request, link_parameter, link_label);
					try {
						template.sendApplicationMailToKeepInfo(keepInfo);
					} catch (Exception e) {
						logger.error(e);
					}

					try {
								alertlink = "/asset/AssetEmployee_showForAssignment.action";
								alertlinkParam = "hiddenAssetCode=" + applnID + "&hiddenStatus="+str;
								template.sendProcessManagerAlert(assigner, module, "A",
										applnID, alertLevel, alertlinkParam, alertlink, applStatus,
										applicant, "", "", "",oldApprover);
						template.sendApplicationMail();
					} catch (Exception e) {
						logger.error(e);
					}
					template.clearParameters();
					template.terminate();
				}

			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	
	public AssetApproval getAssetApproval() {
		return assetApproval;
	}

	public void setAssetApproval(AssetApproval assetApproval) {
		this.assetApproval = assetApproval;
	}

	public String getAssignerCode(String empCode) {
		String assignerCode = "";

		AssetApprovalModel model = new AssetApprovalModel();
		model.initiate(context, session);
		assignerCode = model.getAssignerCode(empCode);
		model.terminate();

		return assignerCode;
	}

}
