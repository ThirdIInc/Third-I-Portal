package org.paradyne.model.voucher;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.voucher.VoucherApprovalMaster;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.common.ReportingModel;
/**
 * @author mangeshj
 * Date 01/02/2008
 * VoucherApprovalModel class to write business logic to change the status 
 * of the application from pending to approved or rejected and also to forward the 
 * application to the next approver
 */
public class VoucherApprovalModel extends ModelBase {
	ReportingModel reporting = new ReportingModel();
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	/** method name : collect 
	 * purpose     : to display the applications in tabular format according to the selected status
	 * return type : void
	 * parameter   : VoucherApprovalMaster instance, String status
	 */
	public void collect(VoucherApprovalMaster vouchApprov,String status,HttpServletRequest request){	
		try {
			ArrayList<VoucherApprovalMaster> list = new ArrayList<VoucherApprovalMaster>();
			Object[][] result = null;
			try {
				Object[] emp = new Object[3];
				emp[0] = status;
				emp[1] = vouchApprov.getUserEmpId();
				emp[2] = vouchApprov.getUserEmpId();
				result = getSqlModel().getSingleResult(getQuery(1), emp);
				String pathQuery = "SELECT NVL(VOUCHER_REMARK,' ') FROM HRMS_VOUCHER_PATH WHERE APPROVER_CODE="
						+ vouchApprov.getUserEmpId() + " AND VOUCHER_CODE= ?";
				logger.info("length od obj" + result.length);
				String[] pageIndexApproved = Utility.doPaging(vouchApprov
						.getMyPage(), result.length, 20);
				if (pageIndexApproved == null) {
					pageIndexApproved[0] = "0";
					pageIndexApproved[1] = "20";
					pageIndexApproved[2] = "1";
					pageIndexApproved[3] = "1";
					pageIndexApproved[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndexApproved[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndexApproved[3])));
				if (pageIndexApproved[4].equals("1"))
					vouchApprov.setMyPage("1");

				if (result != null && result.length > 0) {
					vouchApprov.setListLengthPage("true");
					for (int i = 0; i < result.length; i++) {
						VoucherApprovalMaster bean = new VoucherApprovalMaster();
						bean.setVoucherNo(String.valueOf(result[i][0]));
						bean.setEmpCode(String.valueOf(result[i][1]));
						bean.setEmpName(String.valueOf(result[i][2]));
						bean.setDate(String.valueOf(result[i][3]));
						bean.setTotalAmt(String.valueOf(result[i][4]));
						bean.setCheckStatus(String.valueOf(result[i][5]));
						if (status.equals("A"))
							bean.setStatusNew("Approved");
						if (status.equals("R"))
							bean.setStatusNew("Rejected");

						bean.setLevel((String.valueOf(result[i][6])));
						bean.setTokenNo((String.valueOf(result[i][7])));
						Object[][] data = getSqlModel().getSingleResult(pathQuery,new Object[] { String.valueOf((result[i][0])) });
						if (data != null && data.length> 0) {
							bean.setVchRemark(String.valueOf(data[0][0]));
						}
						else {
							bean.setVchRemark("");
						}
						if (status.equals("P")) {
							bean.setApprflag("false");
						}
						list.add(bean);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			vouchApprov.setList(list);
			vouchApprov.setListLength("" + list.size());
			if (list.size() != 0) {
				vouchApprov.setNoData("false");
			}
			else {
				vouchApprov.setNoData("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* method name : forward 
	 * purpose     : to insert the approver details in HRMS_VOUCHER_PATH table and
	 * 					forward the application to the next approver
	 * return type : boolean
	 * parameter   : VoucherApprovalMaster instance, String status, String voucherNo, String empCode, String comments
	 */
	public String forward(VoucherApprovalMaster bean, String status, String voucherNo, String comments, String empCode) {
		Object [][] pathData = new Object[1][4];
		boolean result = false;
		String applStatus = "pending";

		/*Object[][] to_mailIds = new Object[1][1];	
		Object[][] from_mailIds = new Object[1][1];	*/
		
		pathData[0][0] = voucherNo;
		pathData[0][1] = bean.getUserEmpId();
		pathData[0][2] = comments;
		pathData[0][3] = status;

		/*
		 * change the application status to 'Rejected'
		 */

		if((String.valueOf(status).equals("R")) || String.valueOf(status).equals("B") ){
			Object[][]reject=new Object[1][2];
			reject[0][0]=String.valueOf(status);
			reject[0][1]=String.valueOf(voucherNo);

			result = getSqlModel().singleExecute(getQuery(8), reject);
			if(result){
				/*try {
	
					to_mailIds[0][0] = empCode;
					from_mailIds[0][0] = bean.getUserEmpId();
	
					MailUtility mail = new MailUtility();
					mail.initiate(context, session);
					logger.info("to_mailIds[0][0]" + to_mailIds[0][0]);
					logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
					mail.sendMail(to_mailIds, from_mailIds, "Voucher", "", "R");
	
					mail.terminate();
					
					
				} catch (Exception e) {
					logger.error(e);
				}*/
				applStatus = "rejected";
			}
		}
		result = getSqlModel().singleExecute(getQuery(5), pathData); // insert into HRMS_VOUCHER_PATH
		return applStatus;
	}
	/** Method Name : changeApplStatus used to change application status when level >1
	 * @param bean
	 * @param empFlow
	 * @param voucherNo
	 * @param empCode
	 * @param request
	 * @return
	 */
	public String changeApplStatus(Object[][] empFlow, String voucherNo,
			String empId, String status, HttpServletRequest request) {
		String applStatus = "pending";
		if(empFlow != null && empFlow.length != 0) {
			Object [][]updateApprover = new Object[1][4];
			updateApprover[0][0] = empFlow[0][2];				// next approver 
			updateApprover[0][1] = empFlow[0][0];				// level
			updateApprover[0][2] = empFlow[0][3];				// alternate approver	
			updateApprover[0][3] = voucherNo;
			boolean result = getSqlModel().singleExecute(getQuery(7), updateApprover);
			if(result) {
				applStatus = "forwarded";
			}			
		} else {
			Object[][]statusChanged = new Object[1][2];
			statusChanged[0][0] = "A";
			statusChanged[0][1] = voucherNo;
			getSqlModel().singleExecute(getQuery(8), statusChanged);
			applStatus = "approved";
		}
		return applStatus;
	}

	/** NMethod Name : generateEmpFlow
	 * @param empCode
	 * @param type
	 * @param order
	 * @return
	 */
	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		Object result[][]= null;
		try {
			ReportingModel reporting = new ReportingModel();
			reporting.initiate(context, session);
			result = reporting.generateEmpFlow(empCode, type, order);
			reporting.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** Method Name : approveRejectSendBackApp 
	 * @param request
	 * @param empId
	 * @param voucherCode
	 * @param status
	 * @param comments
	 * @param approverID
	 * @param level
	 * @param appDate
	 * @return
	 */
	public String approveRejectSendBackApp(HttpServletRequest request,
			String empId, String voucherCode, String status,
			String comments, String approverID, String level, String appDate) {
		String applStatus = "pending";
	 try{	
		Object[][] empFlow = null;		
		Object[][] changeStatus = new Object[1][4];
		changeStatus[0][0] = voucherCode; 
		changeStatus[0][1] = approverID;
		changeStatus[0][2] = comments;
		changeStatus[0][3] = status;		
		if (String.valueOf(status).equals("A")) {
			empFlow = generateEmpFlow(empId, "Cash",Integer
					.parseInt(level) + 1);
			applStatus = changeApplStatus(empFlow, String.valueOf(voucherCode),
					empId, status, request);
		}
		if (String.valueOf(status).equals("B")) {
			Object[][] sendBackObj = new Object[1][4];
			sendBackObj[0][0] = status; // status
			sendBackObj[0][1] = "1";
			empFlow = generateEmpFlow(empId, "Cash", 1);
			sendBackObj[0][2] = String.valueOf(empFlow[0][0]);
			sendBackObj[0][3] = voucherCode;
			getSqlModel().singleExecute(getQuery(9), sendBackObj);
			applStatus = "sendback";
		}
		if (String.valueOf(status).equals("R")) {
			Object[][] reject = new Object[1][2];
			reject[0][0] = String.valueOf(status); 
			reject[0][1] = String.valueOf(voucherCode);
			getSqlModel().singleExecute(getQuery(8), reject);
			applStatus = "rejected";
		}
		getSqlModel().singleExecute(getQuery(5),changeStatus);
		generateTemplateWithAlerts(request, status, voucherCode, comments,
				empId, approverID, level, empFlow, applStatus);
	 } catch(Exception e){
		 e.printStackTrace();
	 }
		return applStatus;
	}

	public void generateTemplateWithAlerts(HttpServletRequest request,
			String status, String voucherCode, String comments, String empId,
			String approverID, String level, Object[][] empFlow,
			String applStatus) {
		try{
			String linkParam = "";
			String link = "";
			String applicant = "", oldApprover = "", newApprover = "";
			String actualStatus= applStatus;
			String keepInformCode="";
			String alternateApprover="";
			String keepInformedId = "SELECT VOUCHER_KEEP_INFORM FROM HRMS_VOUCHER_APPL WHERE VOUCHER_APPL_CODE=" + voucherCode;
			Object[][] objKeepInformed = getSqlModel().getSingleResult(
				keepInformedId);
			
			if (!String.valueOf(status).equals("P")) {
					ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
					processAlerts.initiate(context, session);
					String module = "Voucher";
					processAlerts.removeProcessAlert(String.valueOf(voucherCode),
						module);
					if (status.equals("A")) {
						actualStatus = "Approved";
					}
					if (status.equals("R")) {
						actualStatus = "Rejected";
					}					
					try {
						applicant = empId;
						oldApprover = approverID;
						if (status.equals("B"))
							newApprover = "";
						else
							newApprover = String.valueOf(empFlow[0][0]);
					} catch (Exception e) {
						e.printStackTrace();
					}
					String alertLevel = String.valueOf(Integer.parseInt(level) + 1);
					if(!newApprover.equals("")){
						//oldapprover to newApprover
						EmailTemplateBody templateApp = new EmailTemplateBody();
						templateApp.initiate(context, session);
						templateApp.setEmailTemplate("VOUCHER - APPROVER1 TO APPROVER2");
						templateApp.getTemplateQueries();						
						
						EmailTemplateQuery templateQuery1 = templateApp.getTemplateQuery(1); //FROM EMAIL
						templateQuery1.setParameter(1, oldApprover);
						
						EmailTemplateQuery templateQuery2 = templateApp.getTemplateQuery(2); //TO EMAIL
						templateQuery2.setParameter(1,newApprover);
						
						EmailTemplateQuery templateQuery3 = templateApp.getTemplateQuery(3);
						templateQuery3.setParameter(1, voucherCode);
						
						EmailTemplateQuery templateQuery4 = templateApp.getTemplateQuery(4);
						templateQuery4.setParameter(1, approverID);
						
						EmailTemplateQuery templateQuery5 = templateApp.getTemplateQuery(5);
						templateQuery5.setParameter(1, newApprover);
						
						EmailTemplateQuery templateQuery6 = templateApp.getTemplateQuery(6);
						templateQuery6.setParameter(1, voucherCode);
						
						templateApp.configMailAlert();
						try {						
							link = "/voucher/VoucherApplication_retriveForApproval.action";
							linkParam = "voucherCode="+voucherCode+"&status=F";
							 templateApp.sendProcessManagerAlert(newApprover, "Voucher",
										"A", voucherCode, alertLevel, linkParam, link,
										actualStatus, applicant, "", "", "",
										oldApprover);						
							 templateApp.sendApplicationMail();
							} catch (Exception e) {
								logger.error(e);
							}					
							templateApp.clearParameters();
							templateApp.terminate();
						
							//Applicant	Regarding First Approval
							EmailTemplateBody template = new EmailTemplateBody();
							template.initiate(context, session);
							template.setEmailTemplate("VOUCHER - APPROVER TO APPLICANT");
							template.getTemplateQueries();						
							
							EmailTemplateQuery templateQueryA1 = template.getTemplateQuery(1); //FROM EMAIL
							templateQueryA1.setParameter(1, oldApprover);
							
							EmailTemplateQuery templateQueryA2 = template.getTemplateQuery(2); //TO EMAIL
							templateQueryA2.setParameter(1,applicant);
							
							EmailTemplateQuery templateQueryA3 = template.getTemplateQuery(3);
							templateQueryA3.setParameter(1, voucherCode);
							
							EmailTemplateQuery templateQueryA4 = template.getTemplateQuery(4);
							templateQueryA4.setParameter(1, approverID);
							
							EmailTemplateQuery templateQueryA5 = template.getTemplateQuery(5);
							templateQueryA5.setParameter(1, newApprover);
							
							EmailTemplateQuery templateQueryA6 = template.getTemplateQuery(6);
							templateQueryA6.setParameter(1, voucherCode);
							
							template.configMailAlert();
							if(objKeepInformed != null && objKeepInformed.length >0){
								keepInformCode = String.valueOf(objKeepInformed[0][0]);
								template.sendApplicationMailToKeepInfo(keepInformCode);
							 }
							try {										
								link = "/voucher/VoucherApplication_retriveForApproval.action";
								linkParam = "voucherCode="+voucherCode+"&status=A";
								template.sendProcessManagerAlert(oldApprover, "Voucher",
											"I", voucherCode, alertLevel, linkParam, link,
											actualStatus, applicant, "", keepInformCode, applicant,
											oldApprover);									
								template.sendApplicationMail();								
								} catch (Exception e) {
									logger.error(e);
								}					
								template.clearParameters();
								template.terminate();
					}
					else{
						//oldApprover to Applicant
						EmailTemplateBody template1 = new EmailTemplateBody();
						template1.initiate(context, session);
						template1.setEmailTemplate("VOUCHER - OLDAPPROVER TO APPLICANT");
						template1.getTemplateQueries();						
						
						EmailTemplateQuery templateQueryApp1 = template1.getTemplateQuery(1); //FROM EMAIL
						templateQueryApp1.setParameter(1, oldApprover);
						
						EmailTemplateQuery templateQueryApp2 = template1.getTemplateQuery(2); //TO EMAIL
						templateQueryApp2.setParameter(1,applicant);
						
						EmailTemplateQuery templateQueryApp3 = template1.getTemplateQuery(3);
						templateQueryApp3.setParameter(1, voucherCode);
						
						EmailTemplateQuery templateQueryApp4 = template1.getTemplateQuery(4);
						templateQueryApp4.setParameter(1, approverID);
						
						EmailTemplateQuery templateQueryApp5 = template1.getTemplateQuery(5);
						templateQueryApp5.setParameter(1, voucherCode);
						
						template1.configMailAlert();
						if(status.equals("P")){
							actualStatus="Pending";
						}
						if(status.equals("A")){
							actualStatus="Approved";
						}
						if(status.equals("R")){
							actualStatus="Rejected";
						}		
						if(objKeepInformed != null && objKeepInformed.length >0){
							keepInformCode = String.valueOf(objKeepInformed[0][0]);
							template1.sendApplicationMailToKeepInfo(keepInformCode);
						 }
						try {		
							link = "/voucher/VoucherApplication_callforedit.action";
							linkParam = "voucherCode=" + voucherCode + "&voucherStatus="+status;

							if (status.equals("B")) {
								actualStatus = "SentBack";
								template1.sendProcessManagerAlert("", "Voucher", "A",
										voucherCode, alertLevel, linkParam, link,
										actualStatus, applicant, "", "",
										applicant, oldApprover);
								link = "/voucher/VoucherApplication_retriveForApproval.action";
								linkParam = "voucherCode="+voucherCode+"&status=A"; 
								template1.sendProcessManagerAlert(oldApprover,
										"Voucher", "I", voucherCode, alertLevel,
										linkParam, link, actualStatus, applicant,
										alternateApprover, keepInformCode, "", oldApprover);
							} else {

								template1.sendProcessManagerAlert(oldApprover,
										"Voucher", "I", voucherCode, alertLevel,
										linkParam, link, actualStatus, applicant,
										alternateApprover, keepInformCode, applicant,
										oldApprover);
							}	
							
							template1.sendApplicationMail();
							} catch (Exception e) {
								logger.error(e);
							}					
							template1.clearParameters();
							template1.terminate();
					}

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}