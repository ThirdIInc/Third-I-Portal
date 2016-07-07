 package org.struts.action.TravelManagement.TravelProcess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ResourceBundle;
import org.paradyne.bean.TravelManagement.TravelProcess.TravelQuickBooking;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.TravelManagement.TravelProcess.TravelApplicationModel;
import org.paradyne.model.TravelManagement.TravelProcess.TravelQuickBookingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class TravelQuickBookingAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelQuickBookingAction.class);

	TravelQuickBooking travelQuickBook;

	public void prepare_local() throws Exception {
		travelQuickBook = new TravelQuickBooking();
		travelQuickBook.setMenuCode(1089);
	}

	public Object getModel() {
		return travelQuickBook;
	}

	public TravelQuickBooking getTravelQuickBook() {
		return travelQuickBook;
	}

	public void setTravelQuickBook(TravelQuickBooking travelQuickBook) {
		this.travelQuickBook = travelQuickBook;
	}

	public String input() {
		try {
			travelQuickBook.setListType("pending");
			callStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String search() {
		try {
			TravelQuickBookingModel model = new TravelQuickBookingModel();
			model.initiate(context, session);
			if (travelQuickBook.getStatus().equals("C")) {
				getCompletedBookingList();
			} else {
				travelQuickBook.setListType("pending");
				model.callStatus(travelQuickBook, travelQuickBook.getStatus(),
						request);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void callStatus() {
		try {
			TravelQuickBookingModel model = new TravelQuickBookingModel();
			model.initiate(context, session);
			model.callStatus(travelQuickBook, "A", request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String revoke() throws Exception {
		try {

			TravelQuickBookingModel model = new TravelQuickBookingModel();
			model.initiate(context, session);
			boolean result = model.revoke(travelQuickBook);
			if (result) {
				addActionMessage("Application revoked successfully");
				Object[][] empFlow = generateEmpFlow(travelQuickBook
						.getInitiatorCode(), "TYD", 1);
				if (empFlow != null && empFlow.length > 0) {
					sendRevokeApplicationMail(travelQuickBook
							.getHiddenApplicationCode(), String
							.valueOf(empFlow[0][0]), travelQuickBook
							.getInitiatorCode());
				}

			} else {
				addActionMessage("Application can't be revoked");
			}
			model.terminate();
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void sendRevokeApplicationMail(String aplicationCode,
			String approverId, String empCode) {

		System.out.println("approverId   " + approverId);
		System.out.println("empCode       " + empCode);
		try {
			TravelApplicationModel model = new TravelApplicationModel();
			model.initiate(context, session);
			String accountantId = "";
			String adminId = "0";

			String adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID,AUTH_POLICY_VIOLN_ESCLN_ID,AUTH_ACCOUNTANT_EMAIL_ID  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
					+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ empCode + ")";// branch Id
			Object[][] adminObj = model.getSqlModel().getSingleResult(
					adminQuery);

			if (adminObj != null && adminObj.length > 0) {
				adminId = String.valueOf(adminObj[0][0]);
				// higherAuthId = "(" + String.valueOf(adminObj[0][1]) +
				// ")";
			}
			adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID,AUTH_POLICY_VIOLN_ESCLN_ID,AUTH_ACCOUNTANT_EMAIL_ID  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch
			// Id
			adminObj = model.getSqlModel().getSingleResult(adminQuery);
			if (adminObj != null && adminObj.length > 0) {
				adminId = String.valueOf(adminObj[0][0]);
				// higherAuthId = String.valueOf(adminObj[0][1]);
			}

			System.out.println("adminId   " + adminId);
			
			
			try{
				
				String accountantQuery = "  SELECT AUTH_ACCOUNTENT  FROM TMS_MANG_AUTH_HDR  WHERE AUTH_MAIN_SCHL_ID="
						+ travelQuickBook.getUserEmpId();

				Object[][] accountantObj = model.getSqlModel()
						.getSingleResult(accountantQuery);

				if (accountantObj != null && accountantObj.length > 0) {
					accountantId = String.valueOf(accountantObj[0][0]);
					logger.info("########## accountantId ################"+accountantId);
				}
				

				EmailTemplateBody template = new EmailTemplateBody();
				// Initiate template
				template.initiate(context, session);

				// Set respective template name
				template.setEmailTemplate("Travel  revoke application mail");
				// call compulsory for set the queries of template
				template.getTemplateQueries();
				try {
					// get the query as per number
					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);// FROM EMAIL
					// set the parameter of queries
					templateQuery1.setParameter(1, travelQuickBook.getUserEmpId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);// To EMAIL
					templateQuery2.setParameter(1, accountantId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1,accountantId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, aplicationCode);
					templateQuery6.setParameter(2, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery7 = template
							.getTemplateQuery(7);
					templateQuery7.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery8 = template
							.getTemplateQuery(8);
					templateQuery8.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery9 = template
							.getTemplateQuery(9);
					templateQuery9.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery10 = template
							.getTemplateQuery(10);
					templateQuery10.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery11 = template
							.getTemplateQuery(11);
					templateQuery11.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery12 = template
							.getTemplateQuery(12);
					templateQuery12.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				template.configMailAlert();
				
				//Check Whether advance amount is greater than 0 OR not.
				//If advance amount is greater than 0 then send Cc mail to Accountant
				Object[][] checkAdavanceAmountObj = model.getSqlModel().getSingleResult("SELECT NVL(APPL_EMP_ADVANCE_AMT,0) FROM TMS_APP_EMPDTL " +
						  							" WHERE TMS_APP_EMPDTL.APPL_ID = " + aplicationCode + " AND TMS_APP_EMPDTL.APPL_EMP_ADVANCE_AMT > 0 ");
				if (checkAdavanceAmountObj != null && checkAdavanceAmountObj.length > 0) {
					template.sendMailToCCEmailIdsWithAttachment(new String[]{Utility.checkNull(String.valueOf(adminObj[0][2]))}, null);
				}
				
				
				
				// call for sending the email
				//template.sendApplicationMail();
				// clear the template
				template.clearParameters();
				// terminate template
				template.terminate();
			
			}
			catch(Exception e)
			{
				
			}
			
			
			try{
				EmailTemplateBody template = new EmailTemplateBody();
				// Initiate template
				template.initiate(context, session);

				// Set respective template name
				template.setEmailTemplate("Travel  revoke application mail");
				// call compulsory for set the queries of template
				template.getTemplateQueries();
				try {
					// get the query as per number
					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);// FROM EMAIL
					// set the parameter of queries
					templateQuery1.setParameter(1, travelQuickBook.getUserEmpId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);// To EMAIL
					templateQuery2.setParameter(1, travelQuickBook.getInitiatorCode());
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, travelQuickBook.getInitiatorCode());
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, aplicationCode);
					templateQuery6.setParameter(2, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery7 = template
							.getTemplateQuery(7);
					templateQuery7.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery8 = template
							.getTemplateQuery(8);
					templateQuery8.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery9 = template
							.getTemplateQuery(9);
					templateQuery9.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery10 = template
							.getTemplateQuery(10);
					templateQuery10.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQuery11 = template
							.getTemplateQuery(11);
					templateQuery11.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQuery12 = template
							.getTemplateQuery(12);
					templateQuery12.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				template.configMailAlert();
				// call for sending the email
				template.sendApplicationMail();
				// clear the template
				template.clearParameters();
				// terminate template
				template.terminate();
			}
			catch(Exception e)
			{
				
			}

			EmailTemplateBody template = new EmailTemplateBody();
			// Initiate template
			template.initiate(context, session);

			// Set respective template name
			template.setEmailTemplate("Travel  revoke application mail");
			// call compulsory for set the queries of template
			template.getTemplateQueries();
			try {
				// get the query as per number
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1);// FROM EMAIL
				// set the parameter of queries
				templateQuery1.setParameter(1, travelQuickBook.getUserEmpId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// And so on
				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2);// To EMAIL
				templateQuery2.setParameter(1, approverId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, approverId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, aplicationCode);
				templateQuery6.setParameter(2, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);
				templateQuery8.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery9 = template
						.getTemplateQuery(9);
				templateQuery9.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery10 = template
						.getTemplateQuery(10);
				templateQuery10.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				EmailTemplateQuery templateQuery11 = template
						.getTemplateQuery(11);
				templateQuery11.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery12 = template
						.getTemplateQuery(12);
				templateQuery12.setParameter(1, aplicationCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			template.configMailAlert();
			// call for sending the email
			template.sendApplicationMail();
			// clear the template
			template.clearParameters();
			// terminate template
			template.terminate();

			if (!adminId.equals("0")) {
				EmailTemplateBody templateforadmin = new EmailTemplateBody();
				// Initiate template
				templateforadmin.initiate(context, session);

				// Set respective template name
				templateforadmin
						.setEmailTemplate("Travel  revoke application mail");
				// call compulsory for set the queries of template
				templateforadmin.getTemplateQueries();
				try {
					// get the query as per number
					EmailTemplateQuery templateQueryadmin1 = templateforadmin
							.getTemplateQuery(1);// FROM EMAIL
					// set the parameter of queries
					templateQueryadmin1.setParameter(1, travelQuickBook
							.getUserEmpId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryadmin2 = templateforadmin
							.getTemplateQuery(2);// To EMAIL
					templateQueryadmin2.setParameter(1, adminId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQueryadmin3 = templateforadmin
							.getTemplateQuery(3);
					templateQueryadmin3.setParameter(1, adminId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQueryadmin4 = templateforadmin
							.getTemplateQuery(4);
					templateQueryadmin4.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQueryadmin5 = templateforadmin
							.getTemplateQuery(5);
					templateQueryadmin5.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQueryadmin6 = templateforadmin
							.getTemplateQuery(6);
					templateQueryadmin6.setParameter(1, aplicationCode);
					templateQueryadmin6.setParameter(2, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQueryadmin7 = templateforadmin
							.getTemplateQuery(7);
					templateQueryadmin7.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQueryadmin8 = templateforadmin
							.getTemplateQuery(8);
					templateQueryadmin8.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQueryadmin9 = templateforadmin
							.getTemplateQuery(9);
					templateQueryadmin9.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQueryadmin10 = templateforadmin
							.getTemplateQuery(10);
					templateQueryadmin10.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					EmailTemplateQuery templateQueryadmin11 = templateforadmin
							.getTemplateQuery(11);
					templateQueryadmin11.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					EmailTemplateQuery templateQueryadmin12 = templateforadmin
							.getTemplateQuery(12);
					templateQueryadmin12.setParameter(1, aplicationCode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				templateforadmin.configMailAlert();
				// call for sending the email
				try {
					MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
					processAlerts.initiate(context, session);
					processAlerts.removeProcessAlert(travelQuickBook.getHiddenApplicationCode(), "Travel");
					processAlerts.terminate();
				} catch (Exception e) {
					e.printStackTrace();
				}			
				try {
					String linkParam = "applicationId="+travelQuickBook.getHiddenApplicationCode();
					String alertlink = "/TMS/TravelApplication_viewApplications.action";
					 
					templateforadmin.sendProcessManagerAlert(approverId, "Travel", "I",
							travelQuickBook.getHiddenApplicationCode(), "1",
							linkParam, alertlink,
							"Booking Completed", travelQuickBook.getInitiatorCode(),
							"0", accountantId,travelQuickBook.getInitiatorCode(),
									travelQuickBook.getUserEmpId());
			 	
				} catch (Exception e) {
					e.printStackTrace();
				} 	
				templateforadmin.sendApplicationMail();
				
				templateforadmin.clearParameters();// clear the template
				
				templateforadmin.terminate();// terminate template
			}

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String assignEmployee() {
		try {
			System.out.println("In assignEmployee---------------------");
			TravelQuickBookingModel model = new TravelQuickBookingModel();
			model.initiate(context, session);
			String employeeCode[] = request.getParameterValues("hdeleteCode");

			if (employeeCode != null && employeeCode.length > 0) {
				for (int i = 0; i < employeeCode.length; i++) {
					System.out.println("employeeCode[i]------------"
							+ employeeCode[i]);
				}
			}

			boolean result = model
					.assignEmployee(travelQuickBook, employeeCode);

			if (result) {
				addActionMessage("Record assigned successfully.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mainPage";
	}

	public String finalalize() {
		try {
			TravelQuickBookingModel model = new TravelQuickBookingModel();
			model.initiate(context, session);
			String query = " SELECT  APPL_TRAVEL_ID "
				+ " FROM TMS_APPLICATION "
				+ " WHERE APPL_ID="
				+ travelQuickBook.getHiddenApplicationCode();

			Object data[][] = model.getSqlModel().getSingleResult(query);
			
			
			
			model.saveBooking(travelQuickBook, request);
			model.updateAdminComments(travelQuickBook);
			boolean result = model.finalize(travelQuickBook);
			if (result) {
				addActionMessage("Record finalize successfully");
			} else {
				addActionMessage("Record can't be finalize ");
			}
			model.callStatus(travelQuickBook, "A", request);
			travelQuickBook.setListType("pending");
			sendMail();
			try {
				MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
				processAlerts.initiate(context, session);
				processAlerts.removeProcessAlert(travelQuickBook.getHiddenApplicationCode(), "Travel");
				processAlerts.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			String TravelId="";
			if(data!=null && data.length>0){
				 TravelId=String.valueOf(data[0][0]);
			}
			String message="Travel Application  "+   TravelId +" booked ";
			String alertlink="/TMS/TravelQuickBooking_startBooking.action";
			String linkParam="applicationId="+travelQuickBook.getHiddenApplicationCode()+"&applicationStatus=C&applicationType=bookingApp";
			
			
			template.sendProcessManagerAlertWithdraw("", "Travel", "I",
					travelQuickBook.getHiddenApplicationCode(), "1", linkParam, alertlink, message,
					"Finalized", travelQuickBook.getInitiatorCode(), travelQuickBook.getUserEmpId(), "", travelQuickBook.getUserEmpId());
			
			
			
			 alertlink="/TMS/TravelQuickBooking_viewBooking.action";
			 // alertlink="/TMS/TravelQuickBooking_startBooking.action";
			  linkParam="applicationId="+travelQuickBook.getHiddenApplicationCode();
			template.sendProcessManagerAlertWithdraw(travelQuickBook.getInitiatorCode(), "Travel", "I",
					travelQuickBook.getHiddenApplicationCode(), "1", linkParam, alertlink, message,
													 "Finalized", travelQuickBook.getInitiatorCode(), travelQuickBook.getUserEmpId(), "", "");
			
			template.terminate();
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (request.getParameter("source").equals("mymessages")) {
			reset();
			return "mymessages";
		} else if (request.getParameter("source").equals("myservices")) {
			reset();
			return "serviceJsp";
		}   else {
			reset();
			return SUCCESS;
		}
		
	}

	public String back() {
		try {
			TravelQuickBookingModel model = new TravelQuickBookingModel();
			model.initiate(context, session);
			model.callStatus(travelQuickBook, "A", request);
			travelQuickBook.setListType("pending");
			reset();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String sendMail() {
		try {

			String source = request.getParameter("source");
			travelQuickBook.setSource(source);
			TravelQuickBookingModel model = new TravelQuickBookingModel();
			model.initiate(context, session);
			String empCode = travelQuickBook.getUserEmpId();
			String initiatorCode = travelQuickBook.getInitiatorCode();
			String applicationId = travelQuickBook.getHiddenApplicationCode();			
			String query="SELECT  APPL_STATUS FROM TMS_APPLICATION WHERE APPL_ID="+applicationId;
			Object [][] statusObj = model.getSqlModel().getSingleResult(query);
			String status= (String.valueOf(statusObj[0][0]));
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("Booking details to Applicant");
			template.getTemplateQueries();

			
			try {
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, empCode);
			} catch (Exception e) {
				e.printStackTrace();				
			}

			try {
				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, initiatorCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				// templateQuery3.setParameter(1, applnDate);
				templateQuery3.setParameter(1, initiatorCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, travelQuickBook.getUserEmpId());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, applicationId);
				templateQuery5.setParameter(2, applicationId);

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(6);
				templateQuery6.setParameter(1, applicationId);

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(7);
				templateQuery7.setParameter(1, applicationId);

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(8);
				templateQuery8.setParameter(1, applicationId);

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery9 = template
						.getTemplateQuery(9);
				templateQuery9.setParameter(1, applicationId);

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				EmailTemplateQuery templateQuery10 = template
						.getTemplateQuery(10);
				templateQuery10.setParameter(1, applicationId);

			} catch (Exception e) {
				e.printStackTrace();
			}

			template.configMailAlert();
			
			
			System.out.println("Status--------------"+status);
			try {
				String linkParam = "applicationId="+applicationId+"&applicationStatus="+status+"&applicationType=travelApp";				
				String alertlink = "/TMS/TravelQuickBooking_startBooking.action";
				 
				template.sendProcessManagerAlert("", "Travel", "I",
						travelQuickBook.getHiddenApplicationCode(), "1",
						linkParam, alertlink,
						"Booking Completed", travelQuickBook.getInitiatorCode(),
						"0", "",travelQuickBook.getInitiatorCode(),
								travelQuickBook.getUserEmpId());
		 	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			String[] jourFileName = request
					.getParameterValues("journeyFileUpload");
			String[] lodgFileName = request
					.getParameterValues("bookingFileUpload");
			String[] locFileName = request
					.getParameterValues("localConveyanceFileUpload");

			int attachLen = 0;
			if (jourFileName != null && jourFileName.length > 0) {
				attachLen = attachLen + jourFileName.length;
			}

			if (locFileName != null && locFileName.length > 0) {
				attachLen = attachLen + locFileName.length;
			}

			if (lodgFileName != null && lodgFileName.length > 0) {
				attachLen = attachLen + lodgFileName.length;
			}

			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}

			ResourceBundle boundle = ResourceBundle.getBundle("globalMessages");
			String path = boundle.getString("data_path") + "/TMS/" + poolName
					+ "/Tickets/";

			String[] attachPath = new String[attachLen];
			int counter = 0;
			try {
				if (jourFileName != null && jourFileName.length > 0) {
					logger.info("jourFileName.length : " + jourFileName.length);
					for (int i = 0; i < jourFileName.length; i++) {
						if (jourFileName[i] != null
								&& !jourFileName[i].equals("null")
								&& !jourFileName[i].equals("")) {
							attachPath[counter] = path + jourFileName[i];
							counter++;
						}
					}
				} else {
					logger.info("in else jour");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (locFileName != null && locFileName.length > 0) {
					logger.info("locFileName.length : " + locFileName.length);
					for (int i = 0; i < locFileName.length; i++) {
						if (locFileName[i] != null
								&& !locFileName[i].equals("null")
								&& !locFileName[i].equals("")) {
							attachPath[counter] = path + locFileName[i];
							counter++;
						}
					}
				} else {
					logger.info("in else loc");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (lodgFileName != null && lodgFileName.length > 0) {
					for (int i = 0; i < lodgFileName.length; i++) {
						if (lodgFileName[i] != null
								&& !lodgFileName[i].equals("null")
								&& !lodgFileName[i].equals("")) {
							attachPath[counter] = path + lodgFileName[i];
							counter++;
						}
					}
				} else {
					logger.info("in else lodg");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String empInfoQuery = " SELECT APPL_KEEP_INFO "
					+ "	 FROM TMS_APPLICATION"
					+ "	 INNER JOIN HRMS_EMP_OFFC ON(TMS_APPLICATION.APPL_KEEP_INFO = HRMS_EMP_OFFC.EMP_ID) "
					+ "	WHERE APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();
			Object[][] empInfo = model.getSqlModel().getSingleResult(
					empInfoQuery);
			String sourceDest = "";
			String pnrDtls = "";
			Object[][] sourceDestinationObj = model.getSqlModel()
					.getSingleResult(
							"SELECT SOURCE_DEST, PNR FROM TMS_TRAVEL_DTL WHERE TRAVEL_APPL_ID="
									+ travelQuickBook
											.getHiddenApplicationCode());
			if (sourceDestinationObj != null && sourceDestinationObj.length > 0) {
				sourceDest = String.valueOf(sourceDestinationObj[0][0]);
				pnrDtls = String.valueOf(sourceDestinationObj[0][1]);
			}
			if (!travelQuickBook.getCcMailId().trim().equals("")
					&& travelQuickBook.getCcMailId().length() > 0) {
				String ccEmailIds[] = travelQuickBook.getCcMailId().split(";");
				String newccEmailIds[] = null;
				if (ccEmailIds != null && ccEmailIds.length > 0) {
					newccEmailIds = new String[ccEmailIds.length];
					for (int i = 0; i < ccEmailIds.length; i++) {
						newccEmailIds[i] = ccEmailIds[i];

					}
				}

				if (newccEmailIds != null && newccEmailIds.length > 0) {

					try {
						//template.sendMailToCCEmailIdsWithAttachment(
						//		newccEmailIds, attachPath, true,
						////		travelQuickBook.getTravelStartDate(),
							//	travelQuickBook.getTravelEndDate(), "Travel : "
							//			+ sourceDest + " PNR : " + pnrDtls,
							//	"Travel : " + sourceDest + " PNR : " + pnrDtls);
						template.sendMailToCCEmailIdsWithAttachment(newccEmailIds,attachPath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}

			if (empInfo != null && empInfo.length > 0) {
				String[] keepInfo = new String[empInfo.length];
				for (int i = 0; i < empInfo.length; i++) {
					keepInfo[i] = travelQuickBook.getToEamilAddress();
				}
				//template.sendMailToCCEmailIdsWithAttachment(keepInfo,
					//	attachPath, true, travelQuickBook.getTravelStartDate(),
				//		travelQuickBook.getTravelEndDate(), "Travel : "
						//		+ sourceDest, "Travel : " + sourceDest);
				template.sendMailToCCEmailIdsWithAttachment(keepInfo,attachPath);
			}
			template.sendApplMailWithAttachment(attachPath);
			// clear the template
			template.clearParameters();

			// terminate template
			template.terminate();

			model.callStatus(travelQuickBook, "A", request);
			travelQuickBook.setListType("pending");

			addActionMessage("Mail sent successfully");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (travelQuickBook.getSource().equals("mymessages")) {
			reset();
			return "mymessages";
		} else if (travelQuickBook.getSource().equals("myservices")) {
			reset();
			return "serviceJsp";
			
		}  
		else {
			reset();
			return SUCCESS;
		}
		
	}

	public String assignToSubscheduler() {
		try {
			TravelQuickBookingModel model = new TravelQuickBookingModel();
			model.initiate(context, session);
			String authManagementId = request.getParameter("authManagementId");
			String applicationId = request.getParameter("applicationId");
			System.out.println("applicationId-------------" + applicationId);
			String assignedEmployee = request.getParameter("assignedEmployee");

			System.out.println("assignedEmployee                    "
					+ assignedEmployee);

			travelQuickBook.setExistingAssignedEmployee(assignedEmployee);

			model.assignToSubscheduler(travelQuickBook, authManagementId,
					applicationId);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "assignToSubschedulerPage";
	}

	public String startBooking() {
		try {
			TravelQuickBookingModel model = new TravelQuickBookingModel();
			model.initiate(context, session);
			String src=request.getParameter("src");
			System.out.println("Source"+src);
			travelQuickBook.setSource(src);
			travelQuickBook.setHiddenApplicationCode(request
					.getParameter("applicationId"));

			String toEmailAddressQuery = "  SELECT NVL(ADD_EMAIL,'') AS TO_EMAIL_ID FROM HRMS_EMP_ADDRESS WHERE EMP_ID =(SELECT APPL_INITIATOR FROM TMS_APPLICATION WHERE APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode()
					+ ")  AND ADD_TYPE='P' ";

			Object[][] toEmailAddressQueryObj = model.getSqlModel()
					.getSingleResult(toEmailAddressQuery);

			if (toEmailAddressQueryObj != null
					&& toEmailAddressQueryObj.length > 0) {
				travelQuickBook.setToEamilAddress(String
						.valueOf(toEmailAddressQueryObj[0][0]));
			}

			String gradeQuery = " SELECT EMP_CADRE FROM HRMS_EMP_OFFC WHERE EMP_ID=(SELECT APPL_INITIATOR FROM TMS_APPLICATION WHERE APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode() + ")";

			Object gradeQueryObj[][] = model.getSqlModel().getSingleResult(
					gradeQuery);

			if (gradeQueryObj != null && gradeQueryObj.length > 0) {
				travelQuickBook.setHiddenGradeCode(String.valueOf(gradeQueryObj[0][0]));
				
				Object[][] travelPolicyCurrency = model.getSqlModel().getSingleResult("SELECT POLICY_TRAVEL_CURRENCY FROM TMS_TRAVEL_POLICY " + 
																				  " INNER JOIN  TMS_POLICY_GRADE_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_ID = TMS_TRAVEL_POLICY.POLICY_ID) " +
																				  " WHERE POLICY_GRAD_ID= " + String.valueOf(gradeQueryObj[0][0]) + 
																				  " AND  POLICY_STATUS='A'");
				if (travelPolicyCurrency != null && travelPolicyCurrency.length > 0) {
					travelQuickBook.setDefaultCurrency(String.valueOf(travelPolicyCurrency[0][0]));
				}
			}

			String status = request.getParameter("applicationStatus");
			String applicationType = request.getParameter("applicationType");

			String query = " SELECT  APPL_MANAGE_ACCOMODATION, APPL_MANAGE_LOCAL_CONV ,APPL_ISPOLICYVIOLATED, NVL(APPL_VIOLATION_DETAILS,' ') "
					+ " ,APPL_STATUS FROM TMS_APPLICATION "
					+ " WHERE APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();

			Object data[][] = model.getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				if (String.valueOf(data[0][0]).equals("C")) {
					travelQuickBook.setLodgeDtlFlag("true");
				}
				if (String.valueOf(data[0][1]).equals("C")) {
					travelQuickBook.setLocalConvFlag("true");
				}

				if (String.valueOf(data[0][2]).equals("Y")) {
					travelQuickBook.setViolationFlag("Y");
					travelQuickBook.setPolicyViolationMsg(String
							.valueOf(data[0][3]));
				}
			}

			System.out.println("status-----------------------" + status);
			if (status.equals("P")) {
				travelQuickBook.setShowFlag("true");
				travelQuickBook.setButtonShowFlag("true");
				travelQuickBook.setSaveFlag("true");
			} else if (status.equals("E")) {
				travelQuickBook.setShowFlag("true");
				travelQuickBook.setButtonShowFlag("true");
				travelQuickBook.setSaveFlag("true");
			} else {
				if (applicationType.equals("travelApp")) {
					travelQuickBook.setBookingPageFlag("true");
				}
				travelQuickBook.setButtonShowFlag("false");
			}

			if (data != null && data.length > 0
					&& String.valueOf(data[0][4]).equals("F")) {
				if (applicationType.equals("travelApp")) {
					travelQuickBook.setBookingPageFlag("true");
				}
				travelQuickBook.setButtonShowFlag("true");
				travelQuickBook.setRevokeFlag(true);
			}
			model.setData(travelQuickBook, request);
			model.setApplicationAndSavedRecord(travelQuickBook);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bookingDetailsJspPage";
	}

	public String viewBooking() {
		try {
			TravelQuickBookingModel model = new TravelQuickBookingModel();
			model.initiate(context, session);
			String src=request.getParameter("src");
			System.out.println("Source"+src);
			travelQuickBook.setSource(src);
			travelQuickBook.setHiddenApplicationCode(request
					.getParameter("applicationId"));

			String query = " SELECT  APPL_MANAGE_ACCOMODATION, APPL_MANAGE_LOCAL_CONV ,APPL_ISPOLICYVIOLATED, NVL(APPL_VIOLATION_DETAILS,' ') "
					+ " FROM TMS_APPLICATION "
					+ " WHERE APPL_ID="
					+ travelQuickBook.getHiddenApplicationCode();

			Object data[][] = model.getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				if (String.valueOf(data[0][0]).equals("C")) {
					travelQuickBook.setLodgeDtlFlag("true");
				}
				if (String.valueOf(data[0][1]).equals("C")) {
					travelQuickBook.setLocalConvFlag("true");
				}

				if (String.valueOf(data[0][2]).equals("Y")) {
					travelQuickBook.setViolationFlag("Y");
					travelQuickBook.setPolicyViolationMsg(String
							.valueOf(data[0][3]));
				}
			}
			model.setData(travelQuickBook, request);
			model.setApplicationAndSavedRecord(travelQuickBook);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bookingDtlViewPage";
	}

	public String getCompletedBookingList() {
		try {

			TravelQuickBookingModel model = new TravelQuickBookingModel();
			model.initiate(context, session);
			travelQuickBook.setListType("completed");
			model.getCompletedBookingList(travelQuickBook, "C", request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void viewAttachedProof() throws Exception {
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream = null;
		String fileName = "";
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			fileName = request.getParameter("fileName");
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				}
			}
			// for getting server path where configuration files are saved.
			String path = getText("data_path") + "/TMS/" + poolName
					+ "/Tickets/" + fileName;
			oStream = response.getOutputStream();

			// response.setHeader("Content-type", "application/msword");
			response.setHeader("Content-disposition", "attachment;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("-----in file not found catch", e);
			addActionMessage("File not found");
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		} finally {
			logger.info("in finally for closing");
			if (fsStream != null) {
				fsStream.close();
			}
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}
		// return null;
	}

	public void reset() {
		travelQuickBook.setInitiatorId("");
		travelQuickBook.setTravelStartDateFilter("");
		travelQuickBook.setStatus("");
		travelQuickBook.setSource("");
		travelQuickBook.setDestination("");
		travelQuickBook.setTravelId("");
	}

	public String saveBooking() {
		try {
			TravelQuickBookingModel model = new TravelQuickBookingModel();
			model.initiate(context, session);
			boolean result = model.saveBooking(travelQuickBook, request);
			result = model.updateAdminComments(travelQuickBook);
			if (result) {

				addActionMessage("Record saved successfully");
				// travelQuickBook.setSaveFlag("save");
				model.setsavedRecord(travelQuickBook);
				model.setData(travelQuickBook, request);

				if (!travelQuickBook.isRevokeFlag()) {
					travelQuickBook.setSaveFlag("false");
				} else {
					travelQuickBook.setSaveFlag("true");
				}

				String query = " SELECT  APPL_MANAGE_ACCOMODATION, APPL_MANAGE_LOCAL_CONV,APPL_TRAVEL_ID "
						+ " FROM TMS_APPLICATION "
						+ " WHERE APPL_ID="
						+ travelQuickBook.getHiddenApplicationCode();

				Object data[][] = model.getSqlModel().getSingleResult(query);

				if (data != null && data.length > 0) {
					if (String.valueOf(data[0][0]).equals("C")) {
						travelQuickBook.setLodgeDtlFlag("true");
					}
					if (String.valueOf(data[0][1]).equals("C")) {
						travelQuickBook.setLocalConvFlag("true");
					}
					travelQuickBook.setTravelId(String.valueOf(data[0][2]));
				}

			} else {
				addActionMessage("Record can't be saved ");
			}
			travelQuickBook.setButtonShowFlag("true");
			travelQuickBook.setShowFlag("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bookingDetailsJspPage";
	}

	public String addDataToCustomerMaster() throws Exception {

		try {
			TravelQuickBookingModel model = new TravelQuickBookingModel();
			model.initiate(context, session);
			String customer = request.getParameter("otherCustomer");
			String applicationCode = request.getParameter("applicationCode");
			boolean res = model.updateCustomerMasterData(customer,
					applicationCode, travelQuickBook);
			model.terminate();
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");

			String query = " SELECT TRAVEL_CUST_NAME,TOUR_CUSTOMER FROM TMS_APPLICATION "
					+ "	INNER JOIN TMS_TRAVEL_CUSTOMER ON (TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID =TMS_APPLICATION.TOUR_CUSTOMER) "
					+ "	WHERE APPL_ID=" + applicationCode;

			Object obj[][] = model.getSqlModel().getSingleResult(query);

			String newCustomer = "";
			if (obj != null && obj.length > 0) {
				newCustomer = String.valueOf(obj[0][0]);
			}
			if (res) {
				response.getWriter().write(
						"<customermessage>" + newCustomer
								+ "</customermessage>");
			} else {
				response.getWriter().write(
						"<customermessage>" + "duplicate"
								+ "</customermessage>");
			}
		} catch (Exception e) {
			logger.error("----Exception in - -------------", e);
		}
		return null;
	}

	public String addDataToMaster() throws Exception {

		try {
			TravelQuickBookingModel model = new TravelQuickBookingModel();
			model.initiate(context, session);

			String project = request.getParameter("otherProject");

			String applicationCode = request.getParameter("applicationCode");

			boolean res = model.updateProjectMasterData(project,
					applicationCode, travelQuickBook);
			model.terminate();
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");

			String query = " SELECT PROJECT_NAME,TOUR_PROJECT FROM TMS_APPLICATION "
					+ "	INNER JOIN TMS_TRAVEL_PROJECT ON (TMS_TRAVEL_PROJECT.PROJECT_ID =TMS_APPLICATION.TOUR_PROJECT) "
					+ "	WHERE APPL_ID=" + applicationCode;

			Object obj[][] = model.getSqlModel().getSingleResult(query);

			String newProject = "";
			if (obj != null && obj.length > 0) {
				newProject = String.valueOf(obj[0][0]);
			}
			if (res) {
				response.getWriter().write(
						"<message>" + newProject + "</message>");

			} else {
				response.getWriter().write(
						"<message>" + "duplicate" + "</message>");
			}
		} catch (Exception e) {
			logger.error("----Exception in - -------------", e);
		}
		return null;
	}

	public String f9City() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT LOCATION_NAME FROM HRMS_LOCATION ORDER BY LOCATION_NAME ASC";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "City" };
		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "30" };
		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String id = request.getParameter("fieldName");
		logger.info("id>>>>" + id);
		String[] fieldNames = { id };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9JourneyMode() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT JOURNEY_NAME||'-'||CLASS_NAME,CLASS_ID	FROM HRMS_TMS_JOURNEY_CLASS"
				+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID=HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)"
				+ " WHERE JOURNEY_STATUS='A' AND CLASS_STATUS='A' ORDER BY JOURNEY_LEVEL";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Journey Mode" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String id = request.getParameter("fieldName");
		logger.info("id>>>>" + id);
		String[] fieldNames = { "jourMode" + id, "journeyModeId" + id };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9LodgingType() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "SELECT HOTEL_TYPE_NAME,HOTEL_TYPE_ID FROM HRMS_TMS_HOTEL_TYPE WHERE HOTEL_TYPE_STATUS='A' ORDER BY HOTEL_LEVEL ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Hotel Type" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "25" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String id = request.getParameter("fieldName");
		logger.info("id>>>>" + id);

		String[] fieldNames = { "accomodationHotelType" + id,
				"accomodationHotelTypeId" + id };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9RoomType() throws Exception {
		String id = request.getParameter("fieldName");

		String fieldValue = request.getParameter("fieldValue");
		logger.info("id>>>>" + id);

		logger.info("fieldValue>>>>" + fieldValue);

		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/*
		 * String query = "SELECT ROOM_TYPE_NAME,ROOM_TYPE_ID FROM
		 * HRMS_TMS_ROOM_TYPE WHERE ROOM_TYPE_STATUS='A' AND ROOM_HOTEL_TYPE=" +
		 * value + " ORDER BY ROOM_LEVEL ";
		 */

		String query = "SELECT ROOM_TYPE_NAME,ROOM_TYPE_ID FROM HRMS_TMS_ROOM_TYPE WHERE ROOM_HOTEL_TYPE="
				+ fieldValue + " AND ROOM_TYPE_STATUS='A' ORDER BY ROOM_LEVEL ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Room Type" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "accomodationRoomType" + id,
				"accomodationRoomTypeId" + id };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9AgencyName() throws Exception {
		String id = request.getParameter("fieldName");
		String fieldValue = request.getParameter("fieldValue");
		logger.info("id>>>>" + id);
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/*
		 * String query = "SELECT ROOM_TYPE_NAME,ROOM_TYPE_ID FROM
		 * HRMS_TMS_ROOM_TYPE WHERE ROOM_TYPE_STATUS='A' AND ROOM_HOTEL_TYPE=" +
		 * value + " ORDER BY ROOM_LEVEL ";
		 */

		String travelModeId = "0";

		System.out.println("fieldValue    " + fieldValue);

		TravelQuickBookingModel model = new TravelQuickBookingModel();
		model.initiate(context, session);

		String selectQuery = " select CLASS_JOURNEY_ID from HRMS_TMS_JOURNEY_CLASS where CLASS_ID="
				+ fieldValue;

		Object data[][] = model.getSqlModel().getSingleResult(selectQuery);

		if (data != null && data.length > 0) {
			travelModeId = String.valueOf(data[0][0]);
		}

		String query = " SELECT AGENCY_NAME,AGENCY_CODE FROM TMS_TRAVEL_AGENCY WHERE INSTR(AGENCY_TRAVEL_MODE_AVAIL,'"
				+ travelModeId + "') >0";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Agency Name" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "journeyAgency" + id, "journeyAgencyId" + id };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9TravelMedium() throws Exception {
		String id = request.getParameter("fieldName");

		String fieldValue = request.getParameter("fieldValue");
		logger.info("id>>>>" + id);
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/*
		 * String query = "SELECT ROOM_TYPE_NAME,ROOM_TYPE_ID FROM
		 * HRMS_TMS_ROOM_TYPE WHERE ROOM_TYPE_STATUS='A' AND ROOM_HOTEL_TYPE=" +
		 * value + " ORDER BY ROOM_LEVEL ";
		 */

		String travelModeId = "0";

		System.out.println("fieldValue    " + fieldValue);

		TravelQuickBookingModel model = new TravelQuickBookingModel();
		model.initiate(context, session);

		String selectQuery = " select CLASS_JOURNEY_ID from HRMS_TMS_JOURNEY_CLASS where CLASS_ID="
				+ fieldValue;

		Object data[][] = model.getSqlModel().getSingleResult(selectQuery);

		if (data != null && data.length > 0) {
			travelModeId = String.valueOf(data[0][0]);
		}

		String query = " SELECT TRAVEL_CARRIER_NAME,TRAVEL_CARRIER_ID FROM TMS_CARRIER WHERE TRAVEL_MODE_ID="
				+ travelModeId + " ORDER BY TRAVEL_CARRIER_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Travel Medium" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "journeyMedium" + id, "journeyMediumId" + id };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9HotelName() throws Exception {
		String id = request.getParameter("fieldName");

		String selectedCity = request.getParameter("selectedCity");

		String roomType = request.getParameter("roomType");

		String hotelType = request.getParameter("hotelType");

		System.out.println("selectedCity------------" + selectedCity);
		logger.info("id>>>>" + id);
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		/*
		 * String query = "SELECT ROOM_TYPE_NAME,ROOM_TYPE_ID FROM
		 * HRMS_TMS_ROOM_TYPE WHERE ROOM_TYPE_STATUS='A' AND ROOM_HOTEL_TYPE=" +
		 * value + " ORDER BY ROOM_LEVEL ";
		 */

		String query = " SELECT HOTEL_NAME, nvl(decode(ROOM_ISBREAKFAST,'Y','YES','N','NO'),'NO'),NVL(HOTEL_DIST_FROM_AIRPORT,0)"
				+ " ,NVL(HOTEL_AVERAGE_RATING,0), NVL(DECODE(HOTEL_DINNER_PACKAGE,'Y','Yes','N','No'),'No'),NVL(HOTEL_DINNER_PACKAGE_COST,0),HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ID, 'BreakFast'||'-'||nvl(decode(ROOM_ISBREAKFAST,'Y','Yes','N','No'),'No')||'  '||'Dinner'||'-'||nvl(decode(HOTEL_DINNER_PACKAGE,'Y','Yes','N','No'),'No')||'  '||nvl(HOTEL_REMARK,'')"

				+ " ,NVL(ROOM_ACTUAL_RATE,0),NVL(ROOM_CORPORATE_RATE,0)"

				+ " FROM HRMS_TRAVEL_HOTEL_MASTER "

				+ " LEFT JOIN HRMS_TRAVEL_HOTEL_MASTER_DTL  ON(HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ID=HRMS_TRAVEL_HOTEL_MASTER_DTL.HOTEL_ID and ROOM_TYPE="
				+ roomType
				+ ")"

				+ " WHERE HOTEL_CITY LIKE '"
				+ selectedCity.trim()
				+ "' AND HOTEL_TYPE="
				+ hotelType + " order by NVL(HOTEL_AVERAGE_RATING,0) DESC ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Hotel Name", "Breakfast Available",
				"Distance From AirPort", "Hotel Rating", "Dinner Package",
				"Cost of dinner" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "25", "15", "15", "15", "15", "15" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "accomodationHotelName" + id, "", "", "", "",
				"", "accomodationHotelNameId" + id, "bookingDetails" + id,
				"bookingAmount" + id, "corporateRate" + id };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9employee() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
				+ "  CENTER_NAME,RANK_NAME,EMP_ID"
				+ " FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_RANK   ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) ";

		query += "	ORDER BY EMP_ID ASC ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "initiatorId", "initiaterName" };
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9action

	public String f9JourneyCurrency() {
		String currencyID = request.getParameter("currencyID");
		String currencyName = request.getParameter("currencyName");
		System.out.println("f9Currency=== currencyID>>"+currencyID);
		 
		String query = " SELECT HRMS_CURRENCY.CURRENCY_ABBR, HRMS_CURRENCY.CURRENCY_ABBR, HRMS_CURRENCY.CURRENCY_ABBR FROM HRMS_CURRENCY WHERE " + 
						" HRMS_CURRENCY.CURRENCY_STATUS = 'A'";	
		String[] fieldNames = { "currencyCost"+currencyID, "currencyActualCost"+currencyID, "currencyCancelAmountJourney"+currencyID};
		 
		String[] headers = { "Currency" };

		String[] headerWidth = {"100"};
		
		int[] columnIndex = { 0, 1, 2 };
		 
		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9LodgingCurrency() {
		String currencyID = request.getParameter("currencyID");
		String currencyName = request.getParameter("currencyName");
		System.out.println("f9LodgingCurrency=== currencyID>>"+currencyID);
		String query = " SELECT HRMS_CURRENCY.CURRENCY_ABBR, HRMS_CURRENCY.CURRENCY_ABBR, HRMS_CURRENCY.CURRENCY_ABBR FROM HRMS_CURRENCY WHERE " + 
					   " HRMS_CURRENCY.CURRENCY_STATUS = 'A'";	
		String[] fieldNames = {"currencyBookingAmount"+currencyID, "currencyCorporateRate"+currencyID, "currencyCancelAmountAccom"+currencyID };
		
		String[] headers = { "Currency" };
		
		String[] headerWidth = {"100"};
		
		int[] columnIndex = { 0, 1, 2};
		
		String submitFlag = "false";
		
		String submitToMethod = "";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String f9CoveyanceCurrency() {
		String currencyID = request.getParameter("currencyID");
		String currencyName = request.getParameter("currencyName");
		System.out.println("f9CoveyanceCurrency=== currencyID>>"+currencyID);
		String query = " SELECT HRMS_CURRENCY.CURRENCY_ABBR, HRMS_CURRENCY.CURRENCY_ABBR FROM HRMS_CURRENCY WHERE " + 
						" HRMS_CURRENCY.CURRENCY_STATUS = 'A'";	
		String[] fieldNames = { "currencyLocalConveyanceTariffCost"+currencyID,"currencyCancelAmountLocalConv"+currencyID};
		
		String[] headers = { "Currency" };
		
		String[] headerWidth = {"100"};
		
		int[] columnIndex = { 0, 1};
		
		String submitFlag = "false";
		
		String submitToMethod = "";
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		
		return "f9page";
	}
}
