package org.struts.action.probation;

import java.util.Date;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.probation.ProbationEvaluation;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.probation.*;
import org.paradyne.model.settlement.ExitIntQuesModel;
import org.paradyne.lib.PPEncrypter;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.Asset.PurchaseOrderModel;
import org.paradyne.lib.PPEncrypter.*;
import com.ibm.icu.text.SimpleDateFormat;

public class ProbationEvaluationAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ProbationEvaluationAction.class);

	ProbationEvaluation probationEvaluation = null;

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		probationEvaluation = new ProbationEvaluation();
		probationEvaluation.setMenuCode(2114);

	}

	public String input() {
		try {
			ProbationEvaluationModel model = new ProbationEvaluationModel();
			model.initiate(context, session);
			String status = request.getParameter("status") != null ? request
					.getParameter("status") : "P";

			model.getRecord(probationEvaluation, status, request);

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return INPUT;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return probationEvaluation;
	}

	public String processEvaluation() {
		try {
			ProbationEvaluationModel model = new ProbationEvaluationModel();
			model.initiate(context, session);
			String empCode = probationEvaluation.getHiddenempcode();
			String query = " select * from HRMS_PROBATION_EMPEVAL_hdr where HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EVAL_ID="
					+ probationEvaluation.getEvalcode();
			Object data[][] = model.getSqlModel().getSingleResult(query);
			if (data != null && data.length == 0) {
				getRecord();
			} else {
				model.getComment(probationEvaluation, probationEvaluation
						.getHiddenempcode(), probationEvaluation.getEvalcode());
				probationEvaluation.setEvalFlag("true");
			}
			model.getEmployeeDetails(probationEvaluation, empCode);
			probationEvaluation.setDisplayFlag("true");
			probationEvaluation.setBackBtnFlag("true");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String viewRecord() {
		try {
			ProbationEvaluationModel model = new ProbationEvaluationModel();
			model.initiate(context, session);
			// String empCode ="1";// probationEvaluation.getHiddenempcode();

			String empCode = request.getParameter("empCode");
			String probCode = request.getParameter("probCode");

			String backBtnstatus = request.getParameter("backBtnstatus");

			if (backBtnstatus.equals("A")) {
				probationEvaluation.setBackBtnFlag("true");
				probationEvaluation.setViewReportFlag("true");
			} else {
				probationEvaluation.setBackBtnFlag("false");
			}

			probationEvaluation.setEvalcode(probCode);
			probationEvaluation.setHiddenempcode(empCode);
			/*
			 * String dueDate=model.getDueDate(empCode);
			 * probationEvaluation.setDuedateconf(dueDate);
			 */

			model.getComment(probationEvaluation, empCode, probCode);
			probationEvaluation.setEnableAll("N");
			model.getEmployeeDetails(probationEvaluation, empCode);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String sendtoHR() {
		try {
			boolean result = false;
			ProbationEvaluationModel model = new ProbationEvaluationModel();
			model.initiate(context, session);
			String query = "";
			String code = probationEvaluation.getEvalcode();
			String[] quesCode = (String[]) request
					.getParameterValues("probationevalCode");
			System.out.println("The quesCode : " + quesCode.length);
			String[] rating = (String[]) request.getParameterValues("rating");
			boolean res = false;
			String[] comment = (String[]) request.getParameterValues("comment");
			String hrempCode = model.getHRempCode(probationEvaluation
					.getHiddenempcode());
			System.out.println("the hr code : "+hrempCode);
			if (hrempCode.equals("")) {
				addActionMessage("HR Manager not defined. Please configure");
				return input();
			} else {
				if (probationEvaluation.getRecommendation().equals("E")) {
					query = " UPDATE HRMS_PROBATION_EMPEVAL_HDR SET PROBATION_EVAL_DATE=TO_DATE('"
							+ probationEvaluation.getEvaluationDate()
							+ "','DD-MM-YYYY'),PROBATION_RECOMMENDATION='"
							+ probationEvaluation.getRecommendation()
							+ "',PROBATION_EXT_DAYS= "
							+ probationEvaluation.getExtendedProbationDays()
							+ ", PROBATION_STATUS = '"
							+ probationEvaluation.getEvalstatus()
							+ "' WHERE PROBATION_EVAL_ID = " + code;
				} else {
					query = " UPDATE HRMS_PROBATION_EMPEVAL_HDR SET PROBATION_EVAL_DATE=TO_DATE('"
							+ probationEvaluation.getEvaluationDate()
							+ "','DD-MM-YYYY'),PROBATION_RECOMMENDATION='"
							+ probationEvaluation.getRecommendation()
							+ "', PROBATION_STATUS = '"
							+ probationEvaluation.getEvalstatus()
							+ "' WHERE PROBATION_EVAL_ID = " + code;
				}
				res = model.getSqlModel().singleExecute(query);
				if (res) {
					result = model.updateDtl(probationEvaluation, code,
							quesCode, comment, rating);
					result = model.sendtoHR(probationEvaluation);

					if (result) {
						addActionMessage("Application sent to HR");
						try {
							String tomailId="";
							String keepInfoId="";
							String hrempcodes[]=hrempCode.split(",");
							if(hrempcodes!=null && hrempcodes.length>0)
							{
								
									for(int i=0;i<hrempcodes.length;i++)
									{
										if(i==0)
										{
											tomailId += hrempcodes[i];
										}
										else if (i == 1) {
											keepInfoId += hrempcodes[i];
										} else {
											keepInfoId += "," +hrempcodes[i];
										}
									}
								
							}
							System.out.println("tomailId ====== "+tomailId);
							System.out.println("keepInfoId ====== "+keepInfoId);
							

							EmailTemplateBody template1 = new EmailTemplateBody();
							template1.initiate(context, session);
							template1
									.setEmailTemplate("PROBATION - MANAGER TO HR");
							template1.getTemplateQueries();
							EmailTemplateQuery templateQuery11 = template1
									.getTemplateQuery(1); // FROM
							// EMAIL
							templateQuery11.setParameter(1, probationEvaluation
									.getUserEmpId());
							EmailTemplateQuery templateQuery12 = template1
									.getTemplateQuery(2); // TO
							// EMAIL
							templateQuery12.setParameter(1, tomailId);
							EmailTemplateQuery templateQuery13 = template1
									.getTemplateQuery(3);
							templateQuery13.setParameter(1, probationEvaluation
									.getHiddenempcode());
							EmailTemplateQuery templateQuery14 = template1
									.getTemplateQuery(4);
							templateQuery14.setParameter(1, "4");
							EmailTemplateQuery templateQuery15 = template1
									.getTemplateQuery(5);
							templateQuery15.setParameter(1, probationEvaluation
									.getUserEmpId());
							EmailTemplateQuery templateQuery16 = template1
									.getTemplateQuery(6);
							templateQuery16.setParameter(1, probationEvaluation
									.getHiddenempcode());
							EmailTemplateQuery templateQuery17 = template1
									.getTemplateQuery(7);
							templateQuery17.setParameter(1, probationEvaluation
									.getEvalcode());
							EmailTemplateQuery templateQuery18 = template1
									.getTemplateQuery(8);
							templateQuery18.setParameter(1, probationEvaluation
									.getEvalcode());

							template1.configMailAlert();
							
							template1.sendApplicationMailToKeepInfo(keepInfoId);
														
													
							
						String[] link_parameter = new String[3];
							String[] link_label = new String[3];
							// String applicationType = "TYD";
							String applicationType = "ProbationConfirmationAppl";
							String chkId="4";
							link_parameter[0] = applicationType + "#" + tomailId
									+ "#applicationDtls#";

							String link = "/probation/ProbationConfirmation_showDetails.action?employeeId="
								+ probationEvaluation.getHiddenempcode() + "$probCode=0$stat=P$opertype=online";
							// link= PPEncrypter.encrypt(link);
							System.out.println("applicationDtls  ..." + link);
							link_parameter[0] += link;
							link_label[0] = "here";
							template1.addOnlineLink(request, link_parameter, link_label);
							
							try {

								template1.sendApplicationMail();
							} catch (Exception e) {
								logger.error(e);
							}
							template1.clearParameters();
							template1.terminate();
							// TODO Auto-generated method stub
						} catch (Exception e) {
							// TODO: handle exception
						}

					} else {
						addActionMessage("Application cant sent to HR");
					}
				}
			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return input();
	}

	public String report() {
		try {

			ProbationEvaluationModel model = new ProbationEvaluationModel();
			model.initiate(context, session);

			/*
			 * Object[][] empFlow = generateEmpFlow(purchaseOrder.getEmpCode(),
			 * "Purchase", 2); //Object[][]
			 * empFirstFlow=generateEmpFlow(purchaseOrder.getEmpCode(),
			 * //"Purchase")
			 * 
			 * ReportingModel model1 = new ReportingModel();
			 * model1.initiate(context, session); Object[][] empFlow1 =
			 * model1.generateEmpFlow(purchaseOrder.getEmpCode(), "Purchase");
			 * model.getComponyLogo(request);
			 * 
			 * String fileName = (String) request.getAttribute("logo");
			 * 
			 * String filePath = context.getRealPath("/") + "pages/Logo/" +
			 * session.getAttribute("session_pool") + "/" + fileName;
			 */

			model.genReport(request, response, probationEvaluation);

			// model1.terminate();

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public String getRecord() {
		try {
			ProbationEvaluationModel model = new ProbationEvaluationModel();
			model.initiate(context, session);

			model.getDisplay(probationEvaluation);
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception in getRecord() method	:" + e);
			return ERROR;
		}
	}

	public String callstatus() {
		try {
			ProbationEvaluationModel model = new ProbationEvaluationModel();
			model.initiate(context, session);
			String status = "";
			String stat = "";
			/*
			 * try { status = request.getParameter("status"); status =
			 * String.valueOf(status.charAt(0)); }// end of try catch (Exception
			 * e) { logger.error("Exception in callstatus-------" + e); }// end
			 * of catch
			 */
			if (status == null || status.equals("")) {
				status = "P";
			}// end of if
			if (status.equals("P")) {
				stat = "Pending Evaluation List";
			}// end of if
			if (status.equals("H")) {
				stat = "Evaluated List";
			}

			request.setAttribute("stat", stat);
			boolean result = model.isProbationApplicable();

			System.out.println("result===========" + result);
			if (result) {
				model.getRecord(probationEvaluation, status, request);
			} else {
				request.setAttribute("totalPage", 1);
				request.setAttribute("pageNo", 1);
				addActionMessage("Probation Setting not define.");
			}

			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in callstatus---------" + e);
			e.printStackTrace();
		}
		return "success";

	}// end of callstatus

	/**
	 * Method to add new record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() {
		try {
			ProbationEvaluationModel model = new ProbationEvaluationModel();
			model.initiate(context, session);
			// probationEvaluation.setEvalcode("1");
			String code = probationEvaluation.getEvalcode();
			String[] quesCode = (String[]) request
					.getParameterValues("probationevalCode");
			System.out.println("The quesCode : " + quesCode.length);
			String[] rating = (String[]) request.getParameterValues("rating");
			boolean res = false;
			String[] comment = (String[]) request.getParameterValues("comment");
			if (probationEvaluation.getEvalcode() == null
					|| probationEvaluation.getEvalcode().equals("null")
					|| (probationEvaluation.getEvalcode().equals(""))
					|| (probationEvaluation.getEvalcode().equals("0"))) {
				res = model.saveEvalCode(probationEvaluation, quesCode, rating,
						comment);
				if (res) {
					// model.getComment(probationEvaluation);
					addActionMessage("Record Saved Successfully");
					model.getComment(probationEvaluation, probationEvaluation
							.getHiddenempcode(), probationEvaluation
							.getEvalcode());
					probationEvaluation.setEvalFlag("true");

				} else {
					addActionMessage("Record can't be Saved");
					// reset();
				}
			} else {
				String query = "";
				/*if (probationEvaluation.getRecommendation().equals("E")) {
					query = " UPDATE HRMS_PROBATION_EMPEVAL_HDR SET PROBATION_EVAL_DATE=TO_DATE('"
							+ probationEvaluation.getEvaluationDate()
							+ "','DD-MM-YYYY'),PROBATION_RECOMMENDATION='"
							+ probationEvaluation.getRecommendation()
							+ "',PROBATION_EXT_DAYS= "
							+ probationEvaluation.getExtendedProbationDays()
							+ ", PROBATION_STATUS = '"
							+ probationEvaluation.getEvalstatus()
							+ "' WHERE PROBATION_EVAL_ID = " + code;
				} else {
					query = " UPDATE HRMS_PROBATION_EMPEVAL_HDR SET PROBATION_EVAL_DATE=TO_DATE('"
							+ probationEvaluation.getEvaluationDate()
							+ "','DD-MM-YYYY'),PROBATION_RECOMMENDATION='"
							+ probationEvaluation.getRecommendation()
							+ "', PROBATION_STATUS = '"
							+ probationEvaluation.getEvalstatus()
							+ "' WHERE PROBATION_EVAL_ID = " + code;
				}*/
				query = " UPDATE HRMS_PROBATION_EMPEVAL_HDR SET PROBATION_EVAL_DATE=TO_DATE('"
					+ probationEvaluation.getEvaluationDate()
					+ "','DD-MM-YYYY'),PROBATION_RECOMMENDATION='"
					+ probationEvaluation.getRecommendation()
					+ "', PROBATION_STATUS = '"
							+ probationEvaluation.getEvalstatus();
				if(probationEvaluation.getRecommendation().equals("E"))
				{
					query += "',PROBATION_EXT_DAYS= "
					+ probationEvaluation.getExtendedProbationDays();
					
				}
				if(probationEvaluation.getRecommendation().equals("C"))
				{
					query += "',PROBATION_CONFIRMATION_DATE=TO_DATE(' "
					+ probationEvaluation.getConfirmDate()+ "','DD-MM-YYYY')";
					
				}
				if(probationEvaluation.getRecommendation().equals("T"))
				{
					query += "',PROBATION_TERMINATION_DATE=TO_DATE(' "
						+ probationEvaluation.getTerminationDate()+ "','DD-MM-YYYY')";
					
				}
				query+=" WHERE PROBATION_EVAL_ID = "+code;
				System.out.println("Update Query : "+query);
				res = model.getSqlModel().singleExecute(query);
				if (res) {
					boolean result = model.updateDtl(probationEvaluation, code,
							quesCode, comment, rating);
					model.getComment(probationEvaluation, probationEvaluation
							.getHiddenempcode(), probationEvaluation
							.getEvalcode());
					probationEvaluation.setEvalFlag("true");
					addActionMessage("Record Updated Successfully");
				} else {
					addActionMessage("Record cannot be updated");
					// reset();
				}
			}
			probationEvaluation.setDisplayFlag("true");
			probationEvaluation.setReportFlag("false");
			probationEvaluation.setBackBtnFlag("true");
			model.getEmployeeDetails(probationEvaluation, probationEvaluation
					.getHiddenempcode());

			model.terminate();
			return "success";
		} catch (Exception e) {
			logger.error("Exception in save() method	:" + e);
			return "";
		}
	}

	public String delete() {
		try {
			ProbationEvaluationModel model = new ProbationEvaluationModel();
			model.initiate(context, session);
			String msg = model.deleteRecord(probationEvaluation);
			addActionMessage(msg);
			reset();
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return input();
	}

	public String reset() {
		try {
			// probationEvaluation.setEmpToken("");
			// probationEvaluation.setEmployeeId("");
			// probationEvaluation.setEmployeeId("");
			// probationEvaluation.setHiddenempcode("");
			// probationEvaluation.setHiddenprobationcode("");
			// probationEvaluation.setDateOfJoining("");
			// probationEvaluation.setDepartment("");
			// probationEvaluation.setDesignation("");
			// probationEvaluation.setDueDate("");
			// probationEvaluation.setBranch("");
			// probationEvaluation.setProbationevalCode("");
			// probationEvaluation.setProbationevalName("");
			probationEvaluation.setComment("");
			// probationEvaluation.setDuedateconf("");
			probationEvaluation.setRecommendation("");
			probationEvaluation.setEvaluationDate("");
			getRecord();
			probationEvaluation.setReportFlag("false");
			probationEvaluation.setBackBtnFlag("true");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String cancel() {
		return input();
	}

	public String callPage() throws Exception {
		try {
			ProbationEvaluationModel model = new ProbationEvaluationModel();
			model.initiate(context, session);
			callstatus();
			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in callPage----------" + e);
		}
		return INPUT;
	}

	public ProbationEvaluation getProbationEvaluation() {
		return probationEvaluation;
	}

	public void setProbationEvaluation(ProbationEvaluation probationEvaluation) {
		this.probationEvaluation = probationEvaluation;
	}
	
	public String f9Allemployee() throws Exception {
		/*String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME,EMP_ID,'0','P' "
				+ "FROM HRMS_EMP_OFFC "
				+ "WHERE EMP_TYPE = 1 AND EMP_STATUS='S' ";*/
		String query = "SELECT DISTINCT NVL(HRMS_EMP_OFFC.EMP_TOKEN,''),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME,EMP_ID,0 "
				+ "FROM HRMS_EMP_OFFC "
				+ "WHERE EMP_TYPE=(SELECT PROBATION_EMPTYPE FROM HRMS_PROBATION_HDR) AND  EMP_ID NOT IN(SELECT DISTINCT PROBATION_EMP_CODE FROM HRMS_PROBATION_EMPEVAL_HDR WHERE PROBATION_EMP_CODE IS NOT NULL) AND EMP_STATUS='S' AND EMP_REPORTING_OFFICER =  "+probationEvaluation.getUserEmpId()
				+ "UNION "
				+ "SELECT NVL(EMP_TOKEN,''),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME ,EMP_ID,NVL(HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EVAL_ID,0) "
				+ "FROM HRMS_PROBATION_EMPEVAL_HDR "
				+ "LEFT JOIN HRMS_EMP_OFFC ON (HRMS_PROBATION_EMPEVAL_HDR.PROBATION_EMP_CODE=HRMS_EMP_OFFC.EMP_ID) "
				+ "WHERE  HRMS_PROBATION_EMPEVAL_HDR.PROBATION_STATUS='D'  AND EMP_REPORTING_OFFICER ="+probationEvaluation.getUserEmpId(); 

		String[] headers = { "Employee Id", "Employee Name" };

		String[] headerWidth = { "30", "50" };
		String[] fieldNames = { "selectemployeetoken", "selectemployeeName","hiddenempcode","evalcode"};

		int[] columnIndex = { 0, 1,2,3 };

		String submitFlag = "true";

		String submitToMethod = "probationEvaluation_processEvaluation.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
