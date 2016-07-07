package org.paradyne.model.recruitment;

import java.util.ArrayList;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.Recruitment.EmployeeRequisition;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;

public class EmployeeRequisitionModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeeRequisitionModel.class);

	public void sendMailToApprover(EmployeeRequisition bean,
			String approverCode, String[] keepInfromEmpCode,
			String alterNamteApproverCode, HttpServletRequest request,
			String applCode) {
		try {
			String keepInformId = "";
			if (keepInfromEmpCode != null && keepInfromEmpCode.length > 0) {
				for (int i = 0; i < keepInfromEmpCode.length; i++) {
					keepInformId += String.valueOf(keepInfromEmpCode[i]) + ",";
				}
				keepInformId = keepInformId.substring(0, keepInformId.length() - 1);
			}
			
			String link = "/recruit/EmployeeRequi_viewReqDetailsFromApproval.action";
			String linkParam = "reqCode=" + applCode + "&formAction=RequisitionApproval_showApplicationList.action&statusKey=P&flag=true&level=1";
			// Mail from Applicant to Approver BEGINS
			EmailTemplateBody template1 = new EmailTemplateBody();
			template1.initiate(context, session);
			template1.setEmailTemplate("MANPOWER REQUISITION MAIL FROM APPLICANT TO APPROVER");
			template1.getTemplateQueries();

			EmailTemplateQuery templateQueryFirst = template1.getTemplateQuery(1);
			templateQueryFirst.setParameter(1, bean.getUserEmpId());

			EmailTemplateQuery templateQuerySecond = template1
					.getTemplateQuery(2);
			templateQuerySecond.setParameter(1, approverCode);

			EmailTemplateQuery templateQueryThird = template1
					.getTemplateQuery(3);
			templateQueryThird.setParameter(1, bean.getReqCode());

			EmailTemplateQuery templateQueryFourth = template1
					.getTemplateQuery(4);
			templateQueryFourth.setParameter(1, bean.getReqCode());

			EmailTemplateQuery templateQueryFifth = template1
					.getTemplateQuery(5);
			templateQueryFifth.setParameter(1, approverCode);

			EmailTemplateQuery templateQuerySixth = template1
					.getTemplateQuery(6);
			templateQuerySixth.setParameter(1, bean.getReqCode());
			template1.configMailAlert();
			if (keepInfromEmpCode != null) {
				template1.sendApplicationMailToKeepInfo(keepInfromEmpCode); 
			}
		

			String applicant =  bean.getUserEmpId();
			String applnID = bean.getReqCode();
			String approver = approverCode;
			String level = "1";
			
			String[] link_parameter = new String[4];
			String[] link_labels = new String[4];
			String applicationType = "Recruitment";
			link_parameter[0] = applicationType + "#" + applicant	+ "#" + applnID + "#" + "A" + "#" + "..." + "#" + approver	+ "#" + level;
			link_parameter[1] = applicationType + "#" + applicant	+ "#" + applnID + "#" + "R" + "#" + "..." + "#" + approver	+ "#" + level;
			link_parameter[2] = applicationType + "#" + applicant	+ "#" + applnID + "#" + "S" + "#" + "..." + "#" + approver	+ "#" + level;
			link_parameter[3] = applicationType + "#" + applicant	+ "#" + applnID + "#" + "H" + "#" + "..." + "#" + approver	+ "#" + level;
			link_labels[0] = "Approve";
			link_labels[1] = "Reject";
			link_labels[2] = "Send Back"; 
			link_labels[3] = "On Hold";
			template1.addOnlineLink(request, link_parameter, link_labels);
			try {
				template1.sendProcessManagerAlert(approverCode,
						"Resource Requisition", "A", bean.getReqCode(), level,
						linkParam, link, "Pending", bean.getHiringcode(), "0",
						"", "", bean.getHiringcode());
			} catch (Exception e) {
				e.printStackTrace();
			}
			template1.sendApplicationMail();
			template1.clearParameters();
			template1.terminate();
			//BEGINS of alternate Approval
			if (!alterNamteApproverCode.equals("0")) {
				EmailTemplateBody alttemplate1 = new EmailTemplateBody();
				alttemplate1.initiate(context, session);
				alttemplate1.setEmailTemplate("MANPOWER REQUISITION MAIL FROM APPLICANT TO APPROVER");
				alttemplate1.getTemplateQueries();

				EmailTemplateQuery alttemplateQueryFirst = alttemplate1.getTemplateQuery(1);
				alttemplateQueryFirst.setParameter(1, bean.getUserEmpId());

				EmailTemplateQuery alttemplateQuerySecond = alttemplate1
						.getTemplateQuery(2);
				alttemplateQuerySecond.setParameter(1, alterNamteApproverCode);

				EmailTemplateQuery alttemplateQueryThird = alttemplate1
						.getTemplateQuery(3);
				alttemplateQueryThird.setParameter(1, bean.getReqCode());

				EmailTemplateQuery alttemplateQueryFourth = alttemplate1
						.getTemplateQuery(4);
				alttemplateQueryFourth.setParameter(1, bean.getReqCode());

				EmailTemplateQuery alttemplateQueryFifth = alttemplate1
						.getTemplateQuery(5);
				alttemplateQueryFifth.setParameter(1, alterNamteApproverCode);

				EmailTemplateQuery alttemplateQuerySixth = alttemplate1
						.getTemplateQuery(6);
				alttemplateQuerySixth.setParameter(1, bean.getReqCode());
				alttemplate1.configMailAlert();
			/*	
				template1.sendProcessManagerAlert(alterNamteApproverCode, "Resource Requisition", "A",
													bean.getReqCode(), level, linkParam, link, "Pending",
													 bean.getUserEmpId(), alterNamteApproverCode, keepInformId,  bean.getUserEmpId(),  bean.getUserEmpId());
								*/		
				template1.addOnlineLink(request, link_parameter, link_labels);
				alttemplate1.sendApplicationMailToAlternateApprover(alterNamteApproverCode); 
				alttemplate1.clearParameters();
				alttemplate1.terminate();
			}  
			//END of alternate Approval
			// Mail from Applicant to Approver ENDS
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMailToApplicant(EmployeeRequisition bean,
			String approverCode, String applCode, String[] keepInformEmpCode) {
		try {
			// System generated Mail to Applicant BEGINS
			EmailTemplateBody templateApplicant = new EmailTemplateBody();
			templateApplicant.initiate(context, session);
			templateApplicant
					.setEmailTemplate("MANPOWER REQUISITION DETAILS TO APPLICANT");
			templateApplicant.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = templateApplicant
					.getTemplateQuery(1);

			EmailTemplateQuery templateQuery2 = templateApplicant
					.getTemplateQuery(2); // TO
			templateQuery2.setParameter(1, bean.getUserEmpId());

			EmailTemplateQuery templateQuery3 = templateApplicant
					.getTemplateQuery(3);
			templateQuery3.setParameter(1, applCode);

			EmailTemplateQuery templateQuery4 = templateApplicant
					.getTemplateQuery(4);
			templateQuery4.setParameter(1, applCode);

			EmailTemplateQuery templateQuery5 = templateApplicant
					.getTemplateQuery(5);
			templateQuery5.setParameter(1, approverCode);

			EmailTemplateQuery templateQuery6 = templateApplicant
					.getTemplateQuery(6);
			templateQuery6.setParameter(1, applCode);

			templateApplicant.configMailAlert();
			
			String keepInformId = "";
			if (keepInformEmpCode != null && keepInformEmpCode.length > 0) {
				for (int i = 0; i < keepInformEmpCode.length; i++) {
					keepInformId += String.valueOf(keepInformEmpCode[i]) + ",";
				}
				keepInformId = keepInformId.substring(0, keepInformId.length() - 1);
			} 
			
			String link = "/recruit/EmployeeRequi_callForEdit.action";
			String linkParam = "requisitionCode=" + applCode + "&applStatus=P";
			templateApplicant.sendProcessManagerAlert("", "Resource Requisition", "I", applCode, "1", linkParam, link, "Pending",
														bean.getHiringcode(), "0", keepInformId, bean.getHiringcode(), bean.getHiringcode());
			 
			templateApplicant.sendApplicationMail();
			templateApplicant.clearParameters();
			templateApplicant.terminate();
			// System generated Mail to Applicant ENDS
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to display the approver name,date and
	 * remarks when a record gets approved
	 * 
	 * @param bean
	 */
	public void getApprovalDtls(EmployeeRequisition bean) {
		try {
			String query = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, NVL(TO_CHAR(HRMS_REC_REQS_PATH.PATH_APPR_DATE,'DD-MM-YYYY'),' '),"
					+ " NVL(HRMS_REC_REQS_PATH.PATH_REMARK,' '),DECODE(PATH_STATUS,'A','Approved','R','Rejected','S','Send Back','H','On Hold'), HRMS_REC_REQS_PATH.PATH_APPR_DATE"
					+ " FROM HRMS_REC_REQS_PATH "
					+ " INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_REQS_PATH.PATH_REQ_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_REQS_PATH.PATH_APPROVER_CODE)"
					+ " WHERE HRMS_REC_REQS_HDR.REQS_CODE="
					+ bean.getReqCode()
					+ " ORDER BY HRMS_REC_REQS_PATH.PATH_APPR_DATE DESC";
			Object[][] data = getSqlModel().getSingleResult(query);
			ArrayList<Object> list = new ArrayList<Object>();
			if (data != null && data.length > 0) {
				bean.setApprvFlag("true");
				for (int i = 0; i < data.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();
					// bean1.setSrNo(String.valueOf(data[i][0]));//Sr No
					bean1.setApprvName(String.valueOf(data[i][0]));
					bean1.setApprvDate(String.valueOf(data[i][1]));
					bean1.setApprvStat(String.valueOf(data[i][3]));
					bean1.setApprvRem(String.valueOf(data[i][2]));
					list.add(bean1);
				}
				bean.setApprvList(list);
			} else {
				bean.setApprvFlag("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Object[][] checkStatus(EmployeeRequisition bean, String code) {
		Object[][] data = null;
		try {
			String query = "SELECT DECODE(HRMS_REC_REQS_HDR.REQS_STATUS,'O','Open','C','Close'), HRMS_REC_REQS_HDR.REQS_NAME, HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS FROM HRMS_REC_REQS_HDR WHERE HRMS_REC_REQS_HDR.REQS_CODE="
					+ code;
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public void getCurrentDate(EmployeeRequisition bean) {
		try {
			String query = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] dt = getSqlModel().getSingleResult(query);
			bean.setReqDt(String.valueOf(dt[0][0]));
			String userQuery = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID="
					+ bean.getUserEmpId();
			Object[][] name = getSqlModel().getSingleResult(userQuery);
			bean.setHiringManager(String.valueOf(name[0][0]));
			bean.setHiringcode(String.valueOf(name[0][1]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delSkill(EmployeeRequisition req, HttpServletRequest request,
			String[] sn, String[] stype, String[] name, String[] code,
			String[] exp, String[] sel, String[] sdel, String[] detCode) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		try {
			req.setSkill("true");
			if (stype != null) {
				int m = 1;
				for (int i = 0; i < stype.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();
					/*
					 * if(String.valueOf(sdel[i]).equals("Y")){
					 * if(!(detCode[i].equals("") || detCode[i].equals(" ") ||
					 * detCode[i].equals("null"))){ Object [][]del=new
					 * Object[1][2]; del[0][0]=detCode[i];
					 * del[0][1]=req.getReqCode();
					 * getSqlModel().singleExecute(getQuery(14),del); } }else
					 */if (!(String.valueOf(sdel[i]).equals("Y"))) {
						bean1.setSkillType(stype[i]);
						request.setAttribute("skillName" + m, name[i]);
						request.setAttribute("skillId" + m, code[i]);
						bean1.setSkillExp(exp[i]);
						bean1.setSkillSel(sel[i]);
						tableList.add(bean1);
						m++;
					}
				}
			}
			req.setSkillList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to delete the records from the database
	 * 
	 * @param bean
	 * @return
	 */
	public boolean deleteRequisition(EmployeeRequisition bean) {
		boolean result = false;
		try {
			Object[][] delete = new Object[1][1];
			delete[0][0] = bean.getReqCode();
			getSqlModel().singleExecute(getQuery(16), delete);
			getSqlModel().singleExecute(getQuery(17), delete);
			getSqlModel().singleExecute(getQuery(18), delete);
			getSqlModel().singleExecute(getQuery(19), delete);
			result = getSqlModel().singleExecute(getQuery(20), delete);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void deleteData(EmployeeRequisition bean) {
		try {
			Object[][] delete = new Object[1][1];
			delete[0][0] = bean.getReqCode();
			getSqlModel().singleExecute(getQuery(16), delete);
			getSqlModel().singleExecute(getQuery(17), delete);
			getSqlModel().singleExecute(getQuery(18), delete);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delCert(EmployeeRequisition bean, String type[], String name[],
			String issue[], String valid[], String option[], String[] del,
			String[] hdel, HttpServletRequest request, String[] detCode) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			bean.setCertFlag("true");
			if (type != null) {
				for (int i = 0; i < type.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();
					/*
					 * if(String.valueOf(hdel[i]).equals("Y")){
					 * if(!(detCode[i].equals("") || detCode[i].equals(" ") ||
					 * detCode[i].equals("null"))){ logger.info("det code in
					 * cert"+detCode[i]); Object [][]delete=new Object[1][2];
					 * delete[0][0]=detCode[i]; delete[0][1]=bean.getReqCode();
					 * getSqlModel().singleExecute(getQuery(15),delete); } }
					 */if (!(String.valueOf(hdel[i]).equals("Y"))) {
						bean1.setCertType(type[i]);
						bean1.setCertName(name[i]);
						bean1.setCertIssueBy(issue[i]);
						bean1.setCertValid(valid[i]);
						bean1.setCertOption(option[i]);
						tableList.add(bean1);
					}
				}
			}
			bean.setCertList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delDom(EmployeeRequisition req, String[] domType,
			String[] domName, String[] exp, String[] option, String[] delDom,
			String[] domChk, String[] domId, HttpServletRequest request,
			String detCode[]) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			req.setDomFlag("true");
			if (domType != null) {
				int m = 1;
				for (int i = 0; i < domType.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();

					/*
					 * if(String.valueOf(domChk[i]).equals("Y")){
					 * if(!(detCode[i].equals("") || detCode[i].equals(" ") ||
					 * detCode[i].equals("null"))){ Object [][]del=new
					 * Object[1][2]; del[0][0]=detCode[i];
					 * del[0][1]=req.getReqCode();
					 * getSqlModel().singleExecute(getQuery(14),del); } }else
					 */if (!(String.valueOf(domChk[i]).equals("Y"))) {
						bean1.setDomType(domType[i]);
						request.setAttribute("domName" + m, domName[i]);
						request.setAttribute("domId" + m, domId[i]);
						bean1.setDomExp(exp[i]);
						bean1.setDomSel(option[i]);
						m++;
						tableList.add(bean1);
					}
				}
			}
			req.setDomList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delQual(EmployeeRequisition bean, String sn[], String qtype[],
			String qName[], String lvl[], String spl[], String cut[],
			String sel[], HttpServletRequest request, String qcode[],
			String[] spCode, String hdel[], String detCode[]) {
		try {
			bean.setQuaFlag("true");
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (qtype != null) {
				int m = 1;
				for (int i = 0; i < qtype.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();
					if (!(String.valueOf(hdel[i]).equals("Y"))) {
						bean1.setHqualType(qtype[i]);
						request.setAttribute("qualificationName" + m, qName[i]);
						request.setAttribute("qualificationId" + m, qcode[i]);
						request.setAttribute("hqualiLevelName" + m, lvl[i]);
						request.setAttribute("hsplName" + m, spl[i]);
						request.setAttribute("hsplId" + m, spCode[i]);
						bean1.setHcutOff(cut[i]);
						bean1.setSel(sel[i]);
						m++;
						tableList.add(bean1);
					}
				}
			}
			bean.setQualList(tableList);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Object[][] getReqCode() {
		String query = "SELECT MAX(HRMS_REC_REQS_HDR.REQS_CODE) FROM HRMS_REC_REQS_HDR";
		Object[][] reqsCode = getSqlModel().getSingleResult(query);
		return reqsCode;
	}

	/**
	 * Following function is called edit button is called in Employee
	 * Requisition for deleting a record
	 * 
	 * @param bean
	 * @param srNo
	 * @param vacancy
	 * @param dt
	 * @param check
	 */
	public void delVacancy(EmployeeRequisition bean, String[] vacancy,
			String dt[], String code[], String[] vacancyCode) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (vacancy != null) {
				for (int i = 0; i < vacancy.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();
					if (!(String.valueOf(code[i]).equals("Y"))) {
						bean1.setVacDate(dt[i]);
						bean1.setNoOfVac(vacancy[i]);
						tableList.add(bean1);
					}
				}
			}
			bean.setVacList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @method showRecords
	 * @purpose to retrieve the application details as per the selected status
	 * @param bean
	 * @param status
	 * @return String
	 */
	public void showRecords(EmployeeRequisition bean, String status,
			HttpServletRequest request) {
		/*
		 * query to select the application data from HRMA_REQS_HDR as per
		 * selected status
		 */
		try {
			String showQuery = "SELECT HRMS_REC_REQS_HDR.REQS_CODE, NVL(TO_CHAR(HRMS_REC_REQS_HDR.REQS_DATE, 'DD-MM-YYYY'),' ') , HRMS_REC_REQS_HDR.REQS_POSITION, NVL(HRMS_RANK.RANK_NAME,' '), "
					+ "HRMS_REC_REQS_HDR.REQS_APPLIED_BY,  APP_BY.EMP_FNAME||' '||APP_BY.EMP_MNAME||' '||APP_BY.EMP_LNAME APP_BY, "
					+ "HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER, HIRING_MGR.EMP_FNAME||' '||HIRING_MGR.EMP_MNAME||' '||HIRING_MGR.EMP_LNAME HIRING_MGR, "
					+ "HRMS_REC_REQS_HDR.REQS_NAME  FROM HRMS_REC_REQS_HDR "
					+ "LEFT JOIN HRMS_RANK ON (HRMS_REC_REQS_HDR.REQS_POSITION = HRMS_RANK.RANK_ID) "
					+ "INNER JOIN HRMS_EMP_OFFC APP_BY ON (HRMS_REC_REQS_HDR.REQS_APPLIED_BY = APP_BY.EMP_ID) "
					+ "INNER JOIN HRMS_EMP_OFFC HIRING_MGR ON (HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER = HIRING_MGR.EMP_ID) "
					+ "WHERE HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS = '"
					+ status
					+ "' AND HRMS_REC_REQS_HDR.REQS_APPLIED_BY = "
					+ bean.getUserEmpId()
					+ " ORDER BY HRMS_REC_REQS_HDR.REQS_DATE DESC";
			Object[][] applicationData = getSqlModel().getSingleResult(
					showQuery);

			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					applicationData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("PageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");

			ArrayList<Object> list = new ArrayList<Object>();
			if (applicationData != null && applicationData.length != 0) {
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();
					bean1.setHiddenReqCode(String
							.valueOf(applicationData[i][0]));
					bean1.setSrNo(String.valueOf(i + 1));
					bean1.setReqCode(String.valueOf(applicationData[i][0]));
					bean1.setReqDt(String.valueOf(applicationData[i][1]));
					bean1.setAppliedFor(String.valueOf(applicationData[i][3]));
					bean1.setAppliedBy(String.valueOf(applicationData[i][5]));
					bean1.setHiringManager(String
							.valueOf(applicationData[i][7]));
					bean1.setReqName(String.valueOf(applicationData[i][8]));
					list.add(bean1);
				}
				bean.setNoData("false");
			} else {
				bean.setNoData("true");
			}
			bean.setLoadList(list);
			bean.setLeadTimeFlag(leadTimeCheck());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCalculatedRequiedByDate() {
		String requiredDate = "";
		try {
			String leadTimeQuery = "SELECT TO_CHAR(TO_DATE(SYSDATE)+NVL(HRMS_REC_CONF.CONF_LEAD_TIME,0), 'dd-mm-yyyy') FROM HRMS_REC_CONF";
			Object[][] leadTimeObj = getSqlModel().getSingleResult(
					leadTimeQuery);
			if (leadTimeObj != null && leadTimeObj.length > 0) {
				requiredDate = checkNull(String.valueOf(leadTimeObj[0][0]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requiredDate;
	}

	/**
	 * @purpose : For saving first page of req form
	 * 
	 */
	public String saveFirst(Object[][] addObj) {
		boolean result = false;
		result = getSqlModel().singleExecute(getQuery(1), addObj);
		for (int i = 0; i < addObj.length; i++) {
			for (int j = 0; j < addObj[0].length; j++) {
				System.out.println("AddObj [" + 0 + "][" + j + "] >>"
						+ addObj[0][j]);
			}
		}
		if (result) {
			String query = "SELECT MAX(HRMS_REC_REQS_HDR.REQS_CODE) FROM HRMS_REC_REQS_HDR";
			Object[][] reqsCode = getSqlModel().getSingleResult(query);
			if (reqsCode != null) {
				return String.valueOf(String.valueOf(reqsCode[0][0]));
			}
		}
		return "";
	}

	public boolean chkReqsName(EmployeeRequisition bean, String data) {
		boolean result = false;
		try {
			String showQuery = "SELECT HRMS_REC_REQS_HDR.REQS_NAME FROM HRMS_REC_REQS_HDR ORDER BY HRMS_REC_REQS_HDR.REQS_CODE";
			Object[][] Data = getSqlModel().getSingleResult(showQuery);
			int count = 0;
			if (Data != null && Data.length > 0) {
				for (int i = 0; i < Data.length; i++) {
					if (String.valueOf(Data[i][0]).equals(data)) {
						count++;
						break;
					}
				}
			}
			if (count > 0) {
				result = false;
			} else {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Object[][] chkReqsn(EmployeeRequisition bean) {
		Object[][] data = null;
		try {
			String query = "SELECT HRMS_REC_REQS_HDR.REQS_NAME FROM HRMS_REC_REQS_HDR WHERE UPPER(HRMS_REC_REQS_HDR.REQS_NAME) = '"
					+ bean.getReqName().toUpperCase() + "'";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}

	public Object[][] chkReqsnInUpdate(EmployeeRequisition bean) {
		Object[][] data = null;
		try {
			String query = "SELECT HRMS_REC_REQS_HDR.REQS_NAME FROM HRMS_REC_REQS_HDR WHERE UPPER(HRMS_REC_REQS_HDR.REQS_NAME)='"
					+ bean.getReqName().toUpperCase()
					+ "'"
					+ " AND HRMS_REC_REQS_HDR.REQS_CODE !=("
					+ bean.getReqCode() + ")";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}

	public boolean updateFirst(Object[][] data, EmployeeRequisition bean) {
		boolean result = false;
		String flag = "";
		try {
			Object[][] reqName = chkReqsnInUpdate(bean);
			if (reqName != null && reqName.length > 0) {
				result = false;
			} else {
				if (data != null) {
					if (data.length > 0) {
						result = getSqlModel().singleExecute(getQuery(8), data);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateVacDet(String[] noofVac, String[] vacDate,
			String[] code, EmployeeRequisition bean) {
		boolean result = false;
		try {
			String query = "SELECT COUNT(HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE) FROM HRMS_REC_REQS_VACDTL WHERE REQS_CODE="
					+ bean.getReqCode();
			Object[][] reqnCode = getSqlModel().getSingleResult(query);
			if (noofVac != null) {
				if (noofVac.length > 0) {
					Object[][] updateVacObj = new Object[code.length][4];
					Object[][] addObj = new Object[noofVac.length
							- reqnCode.length][3];
					int count = 0;
					for (int i = 0; i < noofVac.length; i++) {
						if (code[i].equals(" ") || code[i].equals("")
								|| code[i].equals("null")) {
							addObj[count][0] = bean.getReqCode();// noofVac[i];
							addObj[count][1] = noofVac[i];
							addObj[count][2] = vacDate[i];// bean.getReqCode();
							count++;
						} else {
							updateVacObj[i][0] = noofVac[i];
							updateVacObj[i][1] = vacDate[i];
							updateVacObj[i][2] = code[i];
							updateVacObj[i][3] = bean.getReqCode();
						}
					}
					getSqlModel().singleExecute(getQuery(2), addObj);
					result = getSqlModel().singleExecute(getQuery(7),
							updateVacObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void updateQualiDet(String[] code, String[] spCode, String[] cut,
			String[] sel, String[] qtype, String[] detCode, String rCode) {
		try {
			String query = "SELECT COUNT(HRMS_REC_REQS_QUA_DTL.REQS_QUA_DTL_CODE) FROM HRMS_REC_REQS_QUA_DTL WHERE HRMS_REC_REQS_QUA_DTL.REQS_CODE="
					+ rCode;
			Object[][] reqnCode = getSqlModel().getSingleResult(query);
			if (cut != null) {
				if (cut.length > 0) {
					Object[][] updateObj = new Object[code.length][7];
					Object[][] addQua = new Object[cut.length - reqnCode.length][6];
					int count = 0;
					for (int i = 0; i < cut.length; i++) {
						if (detCode[i].equals(" ") || detCode[i].equals("")
								|| detCode[i].equals("null")) {
							if (code[i].equals("null") || code[i].equals("")
									|| code[i].equals(" ")) {
								addQua[count][0] = String.valueOf("");
							} else {
								addQua[count][0] = code[i];
							}

							if (spCode[i].equals("null")
									|| spCode[i].equals("")
									|| spCode[i].equals(" ")) {
								addQua[count][1] = String.valueOf("");
							} else {
								addQua[count][1] = spCode[i];
							}
							addQua[count][2] = cut[i];
							addQua[count][3] = sel[i];
							addQua[count][4] = qtype[i];
							addQua[count][5] = rCode;
							getSqlModel().singleExecute(getQuery(4), addQua);
							count++;
						} else {
							if (code[i].equals("null") || code[i].equals("")
									|| code[i].equals(" ")) {
								updateObj[i][0] = String.valueOf("");
							} else {
								updateObj[i][0] = code[i];
							}
							if (spCode[i].equals("null")
									|| spCode[i].equals("")
									|| spCode[i].equals(" ")) {
								updateObj[i][1] = String.valueOf("");
							} else {
								updateObj[i][1] = spCode[i];
							}
							updateObj[i][2] = cut[i];
							updateObj[i][3] = sel[i];
							updateObj[i][4] = qtype[i];
							updateObj[i][5] = detCode[i];
							updateObj[i][6] = rCode;
						}
					}
					getSqlModel().singleExecute(getQuery(10), updateObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateSkillDet(String[] skillCode, String[] skillType,
			String[] skillExp, String[] skillSel, String[] detCode, String rCode) {
		try {
			String query = "SELECT COUNT(HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FUN_DTL_CODE) FROM HRMS_REC_REQS_SKILL_FUNC_DTL WHERE HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FIELD_TYPE='S' AND REQS_CODE="
					+ rCode;
			Object[][] reqnCode = getSqlModel().getSingleResult(query);
			if (skillExp != null) {
				if (skillExp.length > 0) {
					int count = 0;
					Object[][] updateObj = new Object[skillExp.length][7];
					Object[][] addSkill = new Object[skillExp.length
							- reqnCode.length][6];
					for (int i = 0; i < skillExp.length; i++) {
						if (detCode[i].equals(" ") || detCode[i].equals("")
								|| detCode[i].equals("null")) {

							if (skillCode[i].equals("")
									|| skillCode[i].equals("null")) {
								addSkill[count][0] = String.valueOf("");
							} else {

								addSkill[count][0] = skillCode[i];
							}
							addSkill[count][1] = skillType[i];
							addSkill[count][2] = skillExp[i];
							addSkill[count][3] = skillSel[i];
							addSkill[count][4] = rCode;
							addSkill[count][5] = String.valueOf("S");
							getSqlModel().singleExecute(getQuery(5), addSkill);
							count++;
						} else {

							if (skillCode[i].equals("")
									|| skillCode[i].equals("null")
									|| skillCode[i].equals(" ")) {
								updateObj[i][0] = String.valueOf("");
							} else {
								updateObj[i][0] = skillCode[i];
							}
							updateObj[i][1] = skillType[i];
							updateObj[i][2] = skillExp[i];
							updateObj[i][3] = skillSel[i];
							updateObj[i][4] = String.valueOf("S");
							updateObj[i][5] = detCode[i];
							updateObj[i][6] = rCode;
						}
					}

					getSqlModel().singleExecute(getQuery(11), updateObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateDomDet(String[] domCode, String[] domType,
			String[] domExp, String[] domSel, String[] detCode, String rCode) {
		try {
			String query = "SELECT COUNT(HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FUN_DTL_CODE) FROM HRMS_REC_REQS_SKILL_FUNC_DTL WHERE HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FIELD_TYPE='F' AND HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_CODE="
					+ rCode;
			Object[][] reqnCode = getSqlModel().getSingleResult(query);
			if (domExp != null) {
				if (domExp.length > 0) {
					int count = 0;
					Object[][] updateObj = new Object[domExp.length][7];
					Object[][] addObj = new Object[domExp.length
							- reqnCode.length][6];

					for (int i = 0; i < domExp.length; i++) {
						logger.info("domDetCode" + detCode[i]);
						if (detCode[i].equals(" ") || detCode[i].equals("")
								|| detCode[i].equals("null")) {
							if (domCode[i].equals("null")
									|| domCode[i].equals("")
									|| domCode[i].equals(" ")) {
								addObj[count][0] = String.valueOf("");
							} else {

								addObj[count][0] = domCode[i];
							}
							addObj[count][1] = domType[i];
							addObj[count][2] = domExp[i];
							addObj[count][3] = domSel[i];
							addObj[count][4] = rCode;
							addObj[count][5] = String.valueOf("F");
							getSqlModel().singleExecute(getQuery(5), addObj);
							count++;
						} else {

							if (domCode[i].equals("null")
									|| domCode[i].equals("")
									|| domCode[i].equals(" ")) {
								updateObj[i][0] = String.valueOf("");
							} else {

								updateObj[i][0] = domCode[i];
							}
							// updateObj[i][0] = domCode[i];
							updateObj[i][1] = domType[i];
							updateObj[i][2] = domExp[i];
							updateObj[i][3] = domSel[i];
							updateObj[i][4] = String.valueOf("F");
							updateObj[i][5] = detCode[i];
							updateObj[i][6] = rCode;
						}
					}

					getSqlModel().singleExecute(getQuery(11), updateObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean chkCode(EmployeeRequisition bean, String code) {
		boolean result = false;
		try {
			String status = "SELECT HRMS_REC_REQS_CERT_DTL.REQS_CODE FROM HRMS_REC_REQS_CERT_DTL WHERE HRMS_REC_REQS_CERT_DTL.REQS_CODE="
					+ code;
			Object[][] data = getSqlModel().getSingleResult(status);
			String quaReqCode = " SELECT HRMS_REC_REQS_QUA_DTL.REQS_CODE FROM HRMS_REC_REQS_QUA_DTL WHERE HRMS_REC_REQS_QUA_DTL.REQS_CODE="
					+ code;
			Object[][] data1 = getSqlModel().getSingleResult(quaReqCode);
			String str = " SELECT HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_CODE FROM HRMS_REC_REQS_SKILL_FUNC_DTL WHERE HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_CODE="
					+ code;
			Object[][] data2 = getSqlModel().getSingleResult(str);
			if (data != null && data.length > 0) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateCertDet(String[] certType, String[] certName,
			String[] issue, String[] valid, String[] certOption,
			String[] detCode, String rCode) {
		boolean result = false;
		try {

			String status = "SELECT HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS FROM HRMS_REC_REQS_HDR WHERE HRMS_REC_REQS_HDR.REQS_CODE="
					+ rCode;
			Object[][] data = getSqlModel().getSingleResult(status);
			if (!(String.valueOf(data[0][0]).equals("B") || String.valueOf(
					data[0][0]).equals("R"))) {
				return false;
			} else {
				String query = "SELECT COUNT(HRMS_REC_REQS_CERT_DTL.REQS_CERT_DTL_CODE) FROM HRMS_REC_REQS_CERT_DTL WHERE HRMS_REC_REQS_CERT_DTL.REQS_CODE="
						+ rCode;
				Object[][] reqnCode = getSqlModel().getSingleResult(query);

				if (certName != null) {
					if (certName.length > 0) {
						int count = 0;
						Object[][] updateObj = new Object[certName.length][7];
						Object[][] addObj = new Object[certName.length
								- reqnCode.length][6];

						for (int i = 0; i < certName.length; i++) {

							if (detCode[i].equals(" ") || detCode[i].equals("")
									|| detCode[i].equals("null")) {
								addObj[count][0] = certType[i];
								addObj[count][1] = certName[i];
								addObj[count][2] = issue[i];
								addObj[count][3] = valid[i];
								addObj[count][4] = certOption[i];
								addObj[count][5] = rCode;
								getSqlModel()
										.singleExecute(getQuery(6), addObj);
								count++;
							} else {
								updateObj[i][0] = certType[i];
								updateObj[i][1] = certName[i];
								updateObj[i][2] = issue[i];
								updateObj[i][3] = valid[i];
								updateObj[i][4] = certOption[i];
								updateObj[i][5] = detCode[i];
								updateObj[i][6] = rCode;
							}
						}

						result = getSqlModel().singleExecute(getQuery(12),
								updateObj);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return result;

	}

	public void addVacDet(String[] noofVac, String[] vacDate, String reqsCode) {
		try {
			String deleteQuery = "DELETE FROM HRMS_REC_REQS_VACDTL WHERE HRMS_REC_REQS_VACDTL.REQS_CODE="
					+ reqsCode;
			getSqlModel().singleExecute(deleteQuery);

			if (noofVac != null) {
				if (noofVac.length > 0) {
					Object[][] addVacObj = new Object[noofVac.length][3];
					for (int i = 0; i < noofVac.length; i++) {
						if (!(noofVac[i].equals("")
								|| noofVac[i].equals("null") || noofVac[i]
								.equals(" "))) {
							addVacObj[i][0] = reqsCode;
							addVacObj[i][1] = noofVac[i];
							addVacObj[i][2] = vacDate[i];
						}
					}
					getSqlModel().singleExecute(getQuery(2), addVacObj);
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to add qualification details to the database
	 * 
	 * @param quaCode
	 * @param specilisationCode
	 * @param cutOff
	 * @param sel
	 * @param quaType
	 * @param reqsCode
	 */
	public void addQualiDet(String[] quaCode, String[] specilisationCode,
			String[] cutOff, String[] sel, String[] quaType, String reqsCode) {
		try {
			if (quaCode != null) {
				if (quaCode.length > 0) {

					for (int i = 0; i < quaCode.length; i++) {
						if (!(quaCode[i].equals("")
								|| quaCode[i].equals("null") || quaCode[i]
								.equals(" "))) {
							Object[][] addQua = new Object[1][6];
							addQua[0][0] = quaCode[i];
							if (!(specilisationCode[i].equals("")
									|| specilisationCode[i].equals("null") || specilisationCode[i]
									.equals(" "))) {
								addQua[0][1] = specilisationCode[i];
							} else {
								addQua[0][1] = "";
							}

							addQua[0][2] = cutOff[i];
							addQua[0][3] = sel[i];
							addQua[0][4] = quaType[i];
							addQua[0][5] = reqsCode;
							getSqlModel().singleExecute(getQuery(4), addQua);
						}

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to add the skill details in the database
	 * 
	 * @param skillCode
	 * @param skillType
	 * @param skillExp
	 * @param skillSel
	 * @param reqsCode
	 */
	public void addSkillDet(String[] skillCode, String[] skillType,
			String[] skillExp, String[] skillSel, String reqsCode) {
		try {
			if (skillCode != null) {
				if (skillCode.length > 0) {
					for (int i = 0; i < skillCode.length; i++) {
						if (!(skillCode[i].equals("")
								|| skillCode[i].equals("null") || skillCode[i]
								.equals(" "))) {
							Object[][] addSkill = new Object[1][6];
							addSkill[0][0] = skillCode[i];
							addSkill[0][1] = skillType[i];
							addSkill[0][2] = skillExp[i];
							addSkill[0][3] = skillSel[i];
							addSkill[0][4] = reqsCode;
							addSkill[0][5] = String.valueOf("S");
							getSqlModel().singleExecute(getQuery(5), addSkill);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called when domain details are added to the data
	 * base
	 * 
	 * @param domCode
	 * @param domType
	 * @param domExp
	 * @param domSel
	 * @param reqsCode
	 */
	public void addDomainDet(String[] domCode, String[] domType,
			String[] domExp, String[] domSel, String reqsCode) {
		try {
			if (domCode != null) {
				if (domCode.length > 0) {

					for (int i = 0; i < domCode.length; i++) {
						if (!(domCode[i].equals("null")
								|| domCode[i].equals("") || domCode[i]
								.equals(" "))) {
							Object[][] addDom = new Object[1][6];
							addDom[0][0] = domCode[i];
							addDom[0][1] = domType[i];
							addDom[0][2] = domExp[i];
							addDom[0][3] = domSel[i];
							addDom[0][4] = reqsCode;
							addDom[0][5] = String.valueOf("F");
							getSqlModel().singleExecute(getQuery(5), addDom);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called when certification details are added to the
	 * data base
	 * 
	 * @param certType
	 * @param certName
	 * @param issue
	 * @param valid
	 * @param certOption
	 * @param reqsCode
	 */

	public boolean addCertDet(String[] certType, String[] certName,
			String[] issue, String[] valid, String[] certOption, String reqsCode) {
		boolean result = false;
		try {
			if (certType != null) {
				if (certType.length > 0) {
					for (int i = 0; i < certType.length; i++) {
						if (!(certName[i].trim().equals("")
								|| certName[i].trim().equals("null") || certName[i]
								.trim().equals(" "))) {
							Object[][] addCert = new Object[1][6];
							addCert[0][0] = certType[i];
							addCert[0][1] = certName[i];
							addCert[0][2] = issue[i];
							addCert[0][3] = valid[i];
							addCert[0][4] = certOption[i];
							addCert[0][5] = reqsCode;
							result = getSqlModel().singleExecute(getQuery(6),
									addCert);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @purpose : For adding row in vacancy table
	 */
	public void addRowVac(String[] noofVacan, String[] vacDate,
			EmployeeRequisition bean) {
		try {
			ArrayList list = new ArrayList();
			int count = 1;
			EmployeeRequisition bean1 = new EmployeeRequisition();
			if (noofVacan != null) {

				if (noofVacan.length != 0) {
					for (int i = 0; i < noofVacan.length; i++) {
						EmployeeRequisition loopBean = new EmployeeRequisition();
						loopBean.setSrNo(String.valueOf(count));
						loopBean.setNoOfVac(String.valueOf((noofVacan[i])));
						loopBean.setVacDate(String.valueOf((vacDate[i])));
						list.add(loopBean);
						count++;
					}
				}
			}
			bean1.setSrNo(String.valueOf(count));
			bean1.setNoOfVac("");
			bean1.setVacDate(bean.getCalCulayedRequiredByDate());
			list.add(bean1);
			bean.setVacList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean saveForAprove(EmployeeRequisition bean, Object[][] empFlow,
			String code) {
		boolean result = false;
		try {
			String reqCode = bean.getReqCode();
			if (code == null || code.equals("") || code.equals("null")
					|| code.equals(" ")) {
				return false;
			} else {
				Object[][] send = new Object[1][4];
				send[0][0] = empFlow[0][0];
				send[0][1] = String.valueOf("P");

				send[0][2] = empFlow[0][3];
				send[0][3] = code;

				result = getSqlModel().singleExecute(getQuery(21), send);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @purpose : To retrive and Requisition header information
	 */
	public void searchHdrRec(EmployeeRequisition manPowerReqs,
			String requisitionCode, HttpServletRequest request) {
		try {
			String query = "SELECT NVL(HRMS_REC_REQS_HDR.REQS_NAME,' '), HRMS_REC_REQS_HDR.REQS_POSITION, NVL(HRMS_RANK.RANK_NAME,' '), DECODE(HRMS_REC_REQS_HDR.REQS_STATUS,'O','Open','C','Close'), HRMS_REC_REQS_HDR.REQS_DIVISION, NVL(HRMS_DIVISION.DIV_NAME,' '), HRMS_REC_REQS_HDR.REQS_BRANCH, NVL(HRMS_CENTER.CENTER_NAME,' '), HRMS_REC_REQS_HDR.REQS_DEPT, NVL(HRMS_DEPT.DEPT_NAME,' '), "
					+ " DECODE(HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','S','Send Back','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'), "
					+ " HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER, OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||' '||OFFC1.EMP_LNAME,  "
					+ " HRMS_REC_REQS_HDR.REQS_RECTYPE_INT, HRMS_REC_REQS_HDR.REQS_APPLIED_BY,  "
					+ " OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME, NVL(HRMS_REC_REQS_HDR.REQS_JOBDESC_NAME,' '), "
					+ " NVL(HRMS_REC_REQS_HDR.REQS_JOB_DESCRIPTION,' '), NVL(HRMS_REC_REQS_HDR.REQS_ROLE_RESPON,' '),  "
					+ " NVL(HRMS_REC_REQS_HDR.REQS_SPECIAL_REQ,' '),  "
					+ " NVL(HRMS_REC_REQS_HDR.REQS_PERSONEL_REQ,' '), HRMS_REC_REQS_HDR.REQS_VACAN_COMPEN, "
					+ " HRMS_REC_REQS_HDR.REQS_VACAN_EXPMIN, HRMS_REC_REQS_HDR.REQS_VACAN_EXPMAX, HRMS_REC_REQS_HDR.REQS_VACAN_TYPE, HRMS_REC_REQS_HDR.REQS_VACAN_CONTRACT, HRMS_REC_REQS_HDR.REQS_VACAN_CONTYPE, HRMS_REC_REQS_HDR.REQS_APPR_CODE, HRMS_REC_REQS_HDR.REQS_LEVEL, HRMS_REC_REQS_HDR.REQS_RECTYPE_EXT, "
					+ " NVL(TO_CHAR(HRMS_REC_REQS_HDR.REQS_DATE,'DD-MM-YYYY'),' '), HRMS_REC_REQS_HDR.REQS_REPLACE_EMP, "
					+ " HRMS_JOB_DESCRIPTION.JOB_DESC_MIN_RECRUIT_DAYS, HRMS_JOB_DESCRIPTION.JOB_DESC_CODE  "
					+ " FROM HRMS_REC_REQS_HDR  "
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON OFFC1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER "
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY  "
					+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION  "
					+ " LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_REC_REQS_HDR.REQS_DIVISION "
					+ " LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_REC_REQS_HDR.REQS_DEPT  "
					+ " LEFT JOIN HRMS_JOB_DESCRIPTION ON HRMS_JOB_DESCRIPTION.JOB_DESC_CODE = HRMS_REC_REQS_HDR.REQS_JOB_DESC_CODE "
					+ " LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH   "
					// + " LEFT JOIN HRMS_EMP_OFFC OFFC2 ON OFFC2.EMP_ID =
					// HRMS_REC_REQS_HDR.REQS_KEEP_INFO_ID "
					+ " WHERE REQS_CODE= " + requisitionCode;
			Object[][] reqHdrDet = getSqlModel().getSingleResult(query);
			if (reqHdrDet != null) {
				manPowerReqs.setReqName(String.valueOf(reqHdrDet[0][0]));
				manPowerReqs
						.setReqPositionCode(String.valueOf(reqHdrDet[0][1]));
				manPowerReqs
						.setReqPositionName(String.valueOf(reqHdrDet[0][2]));
				manPowerReqs.setReqStatus(String.valueOf(reqHdrDet[0][3]));
				manPowerReqs.setReqDivCode(String.valueOf(reqHdrDet[0][4]));
				manPowerReqs.setReqDiv(String.valueOf(reqHdrDet[0][5]));

				if (String.valueOf(reqHdrDet[0][6]).equals("null")) {
					manPowerReqs.setReqBrnCode("");
					manPowerReqs.setReqBrn("");
				} else {
					manPowerReqs.setReqBrnCode(String.valueOf(reqHdrDet[0][6]));
					manPowerReqs.setReqBrn(String.valueOf(reqHdrDet[0][7]));
				}
				if (String.valueOf(reqHdrDet[0][8]).equals("null")) {
					manPowerReqs.setReqDeptCode("");
					manPowerReqs.setReqDept("");
				} else {
					manPowerReqs
							.setReqDeptCode(String.valueOf(reqHdrDet[0][8]));
					manPowerReqs.setReqDept(String.valueOf(reqHdrDet[0][9]));
				}

				if (String.valueOf(reqHdrDet[0][10]).equals("")
						|| String.valueOf(reqHdrDet[0][10]).equals("null")) {
					manPowerReqs.setReqApprStatus("");
				} else {
					manPowerReqs.setReqApprStatus(String
							.valueOf(reqHdrDet[0][10]));
					manPowerReqs.setHiddenApproveStatus(String.valueOf(
							reqHdrDet[0][10]).trim());
				}
				manPowerReqs.setHiringcode(String.valueOf(reqHdrDet[0][11]));
				manPowerReqs.setHiringManager(String.valueOf(reqHdrDet[0][12]));
				if (String.valueOf(reqHdrDet[0][13]).equals("Y")) {
					manPowerReqs.setInternal("true");
				} else {
					manPowerReqs.setInternal("false");
				}
				request.setAttribute("int", manPowerReqs.getInternal());
				manPowerReqs.setAppliedBy(String.valueOf(reqHdrDet[0][14]));

				if (String.valueOf(reqHdrDet[0][16]).equals("null")
						|| String.valueOf(reqHdrDet[0][16]).equals("")) {
					manPowerReqs.setJdDescName(String.valueOf(""));
				} else {
					manPowerReqs
							.setJdDescName(String.valueOf(reqHdrDet[0][16]));
				}

				if (String.valueOf(reqHdrDet[0][17]).equals("null")
						|| String.valueOf(reqHdrDet[0][17]).equals("")) {
					manPowerReqs.setJdDesc(String.valueOf(""));
				} else {
					manPowerReqs.setJdDesc(String.valueOf(reqHdrDet[0][17]));
				}

				if (String.valueOf(reqHdrDet[0][18]).equals("null")
						|| String.valueOf(reqHdrDet[0][18]).equals("")) {
					manPowerReqs.setJdRoleDesc(String.valueOf(""));
				} else {
					manPowerReqs
							.setJdRoleDesc(String.valueOf(reqHdrDet[0][18]));
				}

				if (String.valueOf(reqHdrDet[0][19]).equals("null")
						|| String.valueOf(reqHdrDet[0][19]).equals("")) {
					manPowerReqs.setSpecialReq(String.valueOf(""));
				} else {
					manPowerReqs
							.setSpecialReq(String.valueOf(reqHdrDet[0][19]));
				}

				if (String.valueOf(reqHdrDet[0][20]).equals("null")
						|| String.valueOf(reqHdrDet[0][20]).equals("")) {
					manPowerReqs.setPersoanlReq(String.valueOf(""));
				} else {
					manPowerReqs.setPersoanlReq(String
							.valueOf(reqHdrDet[0][20]));
				}

				// manPowerReqs.setJdDesc(String.valueOf(reqHdrDet[0][17]));
				// manPowerReqs.setJdRoleDesc(String.valueOf(reqHdrDet[0][18]));
				// manPowerReqs.setSpecialReq(String.valueOf(reqHdrDet[0][19]));
				// manPowerReqs.setPersoanlReq(String.valueOf(reqHdrDet[0][20]));
				if (String.valueOf(reqHdrDet[0][21]).equals("null")) {
					manPowerReqs.setReqCompensation("");
				} else {
					manPowerReqs.setReqCompensation(String
							.valueOf(reqHdrDet[0][21]));
				}

				if (String.valueOf(reqHdrDet[0][22]).equals("null")) {
					manPowerReqs.setMinExp("");
				} else {
					manPowerReqs.setMinExp(String.valueOf(reqHdrDet[0][22]));
				}

				if (String.valueOf(reqHdrDet[0][23]).equals("null")) {
					manPowerReqs.setMaxExp("");
				} else {
					manPowerReqs.setMaxExp(String.valueOf(reqHdrDet[0][23]));
				}

				if (String.valueOf(reqHdrDet[0][24]).equals("null")) {
					manPowerReqs.setVacType("");
				} else {
					if (manPowerReqs.getFlagView().equals("true")) {
						if (String.valueOf(reqHdrDet[0][24]).equals("N")) {
							manPowerReqs.setNewPostComment(checkNull(String
									.valueOf(reqHdrDet[0][31])));
							manPowerReqs.setVacType(String.valueOf("New Post"));
						} else if (String.valueOf(reqHdrDet[0][24]).equals("R")) {
							manPowerReqs.setVacType(String
									.valueOf("Replacement"));
							manPowerReqs.setReplaceEmpId(String
									.valueOf(reqHdrDet[0][31]));
							getRemoveList(manPowerReqs);
						} else {
							manPowerReqs.setVacType(String.valueOf(""));
						}

					} else {
						manPowerReqs.setVacType(String
								.valueOf(reqHdrDet[0][24]));
						if (String.valueOf(reqHdrDet[0][24]).equals("N")) {
							manPowerReqs.setNewPostComment(checkNull(String
									.valueOf(reqHdrDet[0][31])));
						} else if (String.valueOf(reqHdrDet[0][24]).equals("R")) {
							manPowerReqs.setReplaceEmpId(String
									.valueOf(reqHdrDet[0][31]));
							getRemoveList(manPowerReqs);
						}
					}
				}

				if (String.valueOf(reqHdrDet[0][25]).equals("null")) {
					manPowerReqs.setReqConType("");
				} else {
					if (manPowerReqs.getFlagView().equals("true")) {
						if (String.valueOf(reqHdrDet[0][25]).equals("R")) {
							manPowerReqs.setReqConType(String
									.valueOf("Regular"));
						} else if (String.valueOf(reqHdrDet[0][25]).equals("O")) {
							manPowerReqs.setReqConType(String
									.valueOf("On Contract"));
						} else {
							manPowerReqs.setReqConType(String.valueOf(""));
						}
					} else {
						manPowerReqs.setReqConType(String
								.valueOf(reqHdrDet[0][25]));
					}
				}

				if (String.valueOf(reqHdrDet[0][26]).equals("null")) {
					manPowerReqs.setReqPartFull("");
				} else {
					if (manPowerReqs.getFlagView().equals("true")) {
						if (String.valueOf(reqHdrDet[0][26]).equals("P")) {
							manPowerReqs.setReqPartFull(String
									.valueOf("Part Time"));
						} else if (String.valueOf(reqHdrDet[0][26]).equals("F")) {
							manPowerReqs.setReqPartFull(String
									.valueOf("Full Time"));
						} else {
							manPowerReqs.setReqPartFull(String.valueOf(""));
						}
					} else {
						manPowerReqs.setReqPartFull(String
								.valueOf(reqHdrDet[0][26]));
					}
				}
				if (String.valueOf(reqHdrDet[0][29]).equals("Y")) {
					manPowerReqs.setExternal("true");
				} else {
					manPowerReqs.setExternal("false");
				}
				request.setAttribute("ext", manPowerReqs.getExternal());
				manPowerReqs.setReqDt(String.valueOf(reqHdrDet[0][30]));

				if (String.valueOf(reqHdrDet[0][32]).equals("null")) {
					manPowerReqs.setMinReuiredDays("");
				} else {
					manPowerReqs.setMinReuiredDays(String
							.valueOf(reqHdrDet[0][32]));
				}

				if (String.valueOf(reqHdrDet[0][33]).equals("null")) {
					manPowerReqs.setJdCode("");
				} else {
					manPowerReqs.setJdCode(String.valueOf(reqHdrDet[0][33]));
				}

				// manPowerReqs.setEmpid(String.valueOf(reqHdrDet[0][34]));
				// manPowerReqs.setReqDiv(String.valueOf(reqHdrDet[0][35]));

				String keepInfoempId = "0";
				String sqlKeepinfo = "SELECT HRMS_REC_REQS_HDR.REQS_KEEP_INFO_ID FROM HRMS_REC_REQS_HDR WHERE HRMS_REC_REQS_HDR.REQS_CODE  = "
						+ manPowerReqs.getReqCode();
				Object keepInfoId[][] = getSqlModel().getSingleResult(
						sqlKeepinfo);
				if (keepInfoId != null && keepInfoId.length > 0) {
					keepInfoempId = String.valueOf(keepInfoId[0][0]);
				}
				String keepInfodata = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID "
						+ "FROM HRMS_EMP_OFFC "
						+ "WHERE HRMS_EMP_OFFC.EMP_ID IN("
						+ keepInfoempId
						+ ")";
				Object keepInfo[][] = getSqlModel().getSingleResult(
						keepInfodata);

				ArrayList<EmployeeRequisition> keepInfolst = new ArrayList<EmployeeRequisition>();
				if (keepInfo != null && keepInfo.length > 0) {
					for (int i = 0; i < keepInfo.length; i++) {
						EmployeeRequisition reqBean = new EmployeeRequisition();
						reqBean.setKeepInformedEmpNameItt(String
								.valueOf(keepInfo[i][0]));
						reqBean.setEmpid(String.valueOf(keepInfo[i][1]));
						keepInfolst.add(reqBean);
					}
				}
				manPowerReqs.setKeepInformedList(keepInfolst);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @purpose : To retrive and Requisition header information
	 */
	public void searchVacDtl(EmployeeRequisition manPowerReqs) {
		try {
			String query = "SELECT HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE, NVL(HRMS_REC_REQS_VACDTL.VACAN_NUMBERS,0), NVL(TO_CHAR(HRMS_REC_REQS_VACDTL.VACAN_REQ_DATE,'DD-MM-YYYY'),' ') FROM HRMS_REC_REQS_VACDTL"
					+ " WHERE HRMS_REC_REQS_VACDTL.REQS_CODE="
					+ manPowerReqs.getReqCode();
			Object[][] vacDets = getSqlModel().getSingleResult(query);
			if (vacDets != null && vacDets.length > 0) {
				int m = 1;
				ArrayList<EmployeeRequisition> list = new ArrayList<EmployeeRequisition>();
				for (int i = 0; i < vacDets.length; i++) {
					EmployeeRequisition loopBean = new EmployeeRequisition();
					loopBean.setSrNo(String.valueOf(i + 1));
					loopBean.setVacDetCode(String.valueOf(String
							.valueOf(vacDets[i][0])));
					loopBean.setNoOfVac(String.valueOf(
							String.valueOf(vacDets[i][1])).trim());
					loopBean.setVacDate(String.valueOf(
							String.valueOf(vacDets[i][2])).trim());
					list.add(loopBean);
				}
				manPowerReqs.setVacList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called to populate the data in qualification
	 * details table
	 * 
	 * @param manPowerReqs
	 * @param code
	 * @param request
	 */
	public void searchQualiDtl(EmployeeRequisition manPowerReqs, String code,
			HttpServletRequest request) {
		try {
			String query = "SELECT HRMS_REC_REQS_QUA_DTL.REQS_QUA_TYPE,NVL(HRMS_QUA.QUA_ABBR,' '),HRMS_REC_REQS_QUA_DTL.REQS_QUA_CODE,CASE WHEN QUA_LEVEL='UG' THEN 'Under Graduate' "
					+ " WHEN QUA_LEVEL='DI' THEN 'Diploma' WHEN QUA_LEVEL='GR' THEN 'Graduate' WHEN QUA_LEVEL='PG' THEN 'Post Graduate'"
					+ " WHEN QUA_LEVEL='PH' THEN 'Phd' WHEN QUA_LEVEL='DO' THEN 'Doctorate' ELSE ' ' END,"
					+ " NVL(HRMS_SPECIALIZATION.SPEC_ABBR,' '),HRMS_REC_REQS_QUA_DTL.REQS_SPECIALIZATION_CODE,REQS_QUA_CUTOFF,REQS_QUA_OPTION"
					+ " ,REQS_QUA_DTL_CODE FROM HRMS_REC_REQS_QUA_DTL LEFT JOIN HRMS_QUA ON HRMS_QUA.QUA_ID=HRMS_REC_REQS_QUA_DTL.REQS_QUA_CODE "
					+ " LEFT JOIN HRMS_SPECIALIZATION ON HRMS_REC_REQS_QUA_DTL.REQS_SPECIALIZATION_CODE=HRMS_SPECIALIZATION.SPEC_ID"
					+ " WHERE HRMS_REC_REQS_QUA_DTL.REQS_CODE="
					+ code
					+ " ORDER BY HRMS_REC_REQS_QUA_DTL.REQS_QUA_CODE";

			Object[][] qualification = getSqlModel().getSingleResult(query);

			ArrayList<Object> tableList = new ArrayList<Object>();

			if (qualification != null && qualification.length > 0) {
				manPowerReqs.setQuaFlag("true");
				manPowerReqs.setViewQua("true");
				int m = 1;
				for (int i = 0; i < qualification.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();

					bean1.setHqualType(String.valueOf(qualification[i][0]));

					request.setAttribute("qualificationName" + m, String
							.valueOf(qualification[i][1]).trim());
					request.setAttribute("qualificationId" + m, String
							.valueOf(qualification[i][2]));
					request.setAttribute("hqualiLevelName" + m, String.valueOf(
							qualification[i][3]).trim());
					request.setAttribute("hsplName" + m, String.valueOf(
							qualification[i][4]).trim());
					request.setAttribute("hsplId" + m, String
							.valueOf(qualification[i][5]));
					if (String.valueOf(qualification[i][6]).equals("")
							|| String.valueOf(qualification[i][6]).equals(
									"null")) {
						bean1.setHcutOff("");
					} else {
						bean1.setHcutOff(String.valueOf(qualification[i][6]));
					}
					bean1.setSel(String.valueOf(qualification[i][7]));
					bean1.setQuaDetCode(String.valueOf(qualification[i][8]));
					m++;
					tableList.add(bean1);
				}
			}
			manPowerReqs.setQualList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchQualiDtlInView(EmployeeRequisition manPowerReqs,
			String code, HttpServletRequest request) {
		try {
			String query = "SELECT CASE WHEN HRMS_REC_REQS_QUA_DTL.REQS_QUA_TYPE='E' THEN 'Essential' WHEN HRMS_REC_REQS_QUA_DTL.REQS_QUA_TYPE='D' THEN 'Desirable' else ' ' end ,NVL(HRMS_QUA.QUA_ABBR,' '),HRMS_REC_REQS_QUA_DTL.REQS_QUA_CODE,CASE WHEN QUA_LEVEL='UG' THEN 'Under Graduate' "
					+ " WHEN QUA_LEVEL='DI' THEN 'Diploma' WHEN QUA_LEVEL='GR' THEN 'Graduate' WHEN QUA_LEVEL='PG' THEN 'Post Graduate'"
					+ " WHEN QUA_LEVEL='PH' THEN 'Phd' WHEN QUA_LEVEL='DO' THEN 'Doctorate' ELSE ' ' END,"
					+ " NVL(HRMS_SPECIALIZATION.SPEC_ABBR,' '),HRMS_REC_REQS_QUA_DTL.REQS_SPECIALIZATION_CODE,REQS_QUA_CUTOFF,CASE WHEN REQS_QUA_OPTION='A' THEN 'And' WHEN REQS_QUA_OPTION='R' THEN 'Or' ELSE ' ' END"
					+ " ,REQS_QUA_DTL_CODE FROM HRMS_REC_REQS_QUA_DTL LEFT JOIN HRMS_QUA ON HRMS_QUA.QUA_ID=HRMS_REC_REQS_QUA_DTL.REQS_QUA_CODE "
					+ " LEFT JOIN HRMS_SPECIALIZATION ON HRMS_REC_REQS_QUA_DTL.REQS_SPECIALIZATION_CODE=HRMS_SPECIALIZATION.SPEC_ID"
					+ " WHERE HRMS_REC_REQS_QUA_DTL.REQS_CODE="
					+ code
					+ " ORDER BY HRMS_REC_REQS_QUA_DTL.REQS_QUA_CODE";

			Object[][] qualification = getSqlModel().getSingleResult(query);

			ArrayList<Object> tableList = new ArrayList<Object>();

			if (qualification != null && qualification.length > 0) {
				manPowerReqs.setQuaFlag("true");
				manPowerReqs.setViewQua("true");
				int m = 1;
				for (int i = 0; i < qualification.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();

					bean1.setHqualType(String.valueOf(qualification[i][0]));
					request.setAttribute("qualificationName" + m, String
							.valueOf(qualification[i][1]).trim());
					request.setAttribute("qualificationId" + m, String
							.valueOf(qualification[i][2]));
					request.setAttribute("hqualiLevelName" + m, String.valueOf(
							qualification[i][3]).trim());
					request.setAttribute("hsplName" + m, String.valueOf(
							qualification[i][4]).trim());
					request.setAttribute("hsplId" + m, String
							.valueOf(qualification[i][5]));
					if (String.valueOf(qualification[i][6]).equals("")
							|| String.valueOf(qualification[i][6]).equals(
									"null")) {
						bean1.setHcutOff("");
					} else {
						bean1.setHcutOff(String.valueOf(qualification[i][6]));
					}

					logger.info("qualification flag view >>>>"
							+ bean1.getFlagView());

					bean1.setSel(String.valueOf(qualification[i][7]));

					bean1.setQuaDetCode(String.valueOf(qualification[i][8]));
					m++;
					tableList.add(bean1);
				}
			}
			manPowerReqs.setQualList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchSkillDtl(EmployeeRequisition manPowerReqs, String code,
			HttpServletRequest request) {
		try {
			String query = "SELECT HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_TYPE, NVL(HRMS_REC_REQS_SKILL_FUNC_DTL.HRMS_SKILL.SKILL_NAME,' '), HRMS_REC_REQS_SKILL_FUNC_DTL.HRMS_SKILL.SKILL_ID, NVL(HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_EXP,''),"
					+ " HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_OPTION, HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FUN_DTL_CODE FROM HRMS_REC_REQS_SKILL_FUNC_DTL LEFT JOIN HRMS_SKILL "
					+ " ON HRMS_SKILL.SKILL_ID=HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_CODE "
					+ " WHERE REQS_CODE="
					+ code
					+ " AND HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FIELD_TYPE='S' ORDER BY HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_CODE ";

			Object[][] skill = getSqlModel().getSingleResult(query);

			ArrayList<Object> tableList = new ArrayList<Object>();

			if (skill != null && skill.length > 0) {
				manPowerReqs.setSkill("true");
				manPowerReqs.setViewSkill("true");
				int m = 1;
				for (int i = 0; i < skill.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();

					bean1.setSkillType(String.valueOf(skill[i][0]));
					request.setAttribute("skillName" + m, String.valueOf(
							skill[i][1]).trim());
					request.setAttribute("skillId" + m, String
							.valueOf(skill[i][2]));

					if (String.valueOf(skill[i][3]).equals("")
							|| String.valueOf(skill[i][3]).equals("null")) {

						bean1.setSkillExp("");
					} else {
						bean1.setSkillExp(String.valueOf(skill[i][3]));
					}

					bean1.setSkillSel(String.valueOf(skill[i][4]));

					// bean1.setSkillSel(String.valueOf(skill[i][4]));
					bean1.setSkillDetCode(String.valueOf(skill[i][5]));
					tableList.add(bean1);
					m++;
				}
				manPowerReqs.setSkillList(tableList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void searchSkillDtlInView(EmployeeRequisition manPowerReqs,
			String code, HttpServletRequest request) {
		try {
			String query = "SELECT CASE WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_TYPE='E' THEN 'Essential' WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_TYPE='D' THEN 'Desirable' ELSE ' ' END ,NVL(HRMS_SKILL.SKILL_NAME,' '),HRMS_SKILL.SKILL_ID, NVL(HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_EXP,''),"
					+ " CASE WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_OPTION='A' THEN 'And' WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_OPTION='R' THEN 'Or' ELSE ' ' END ,HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FUN_DTL_CODE FROM HRMS_REC_REQS_SKILL_FUNC_DTL LEFT JOIN HRMS_SKILL "
					+ " ON HRMS_SKILL.SKILL_ID=HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_CODE "
					+ " WHERE REQS_CODE="
					+ code
					+ " AND HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FIELD_TYPE='S' ORDER BY HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_CODE ";

			Object[][] skill = getSqlModel().getSingleResult(query);

			ArrayList<Object> tableList = new ArrayList<Object>();

			if (skill != null && skill.length > 0) {
				manPowerReqs.setViewSkill("true");
				manPowerReqs.setSkill("true");
				int m = 1;
				for (int i = 0; i < skill.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();

					bean1.setSkillType(String.valueOf(skill[i][0]));
					request.setAttribute("skillName" + m, String.valueOf(
							skill[i][1]).trim());
					request.setAttribute("skillId" + m, String
							.valueOf(skill[i][2]));

					if (String.valueOf(skill[i][3]).equals("")
							|| String.valueOf(skill[i][3]).equals("null")) {

						bean1.setSkillExp("");
					} else {
						bean1.setSkillExp(String.valueOf(skill[i][3]));
					}

					bean1.setSkillSel(String.valueOf(skill[i][4]));

					// bean1.setSkillSel(String.valueOf(skill[i][4]));
					bean1.setSkillDetCode(String.valueOf(skill[i][5]));
					tableList.add(bean1);
					m++;

				}// End of or loop
			}// End of if condition
			manPowerReqs.setSkillList(tableList);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// End of method

	/**
	 * following function is called to populate the domain details table
	 * 
	 * @param manPowerReqs
	 * @param code
	 * @param request
	 */
	public void searchDomainDtl(EmployeeRequisition manPowerReqs, String code,
			HttpServletRequest request) {
		try {
			String query = "SELECT HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_TYPE, NVL(HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_NAME,' '), HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_CODE, NVL(HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_EXP,''),"
					+ " HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_OPTION , HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FUN_DTL_CODE FROM HRMS_REC_REQS_SKILL_FUNC_DTL LEFT JOIN HRMS_FUNC_DOMAIN_MASTER "
					+ " ON HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_CODE=HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_CODE "
					+ " WHERE HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_CODE="
					+ code
					+ " AND HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FIELD_TYPE='F' ORDER BY HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_CODE ";

			Object[][] domain = getSqlModel().getSingleResult(query);

			ArrayList<Object> tableList = new ArrayList<Object>();

			if (domain != null && domain.length > 0) {
				manPowerReqs.setDomFlag("true");
				manPowerReqs.setViewDom("true");
				int m = 1;
				for (int i = 0; i < domain.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();

					bean1.setDomType(String.valueOf(domain[i][0]));

					// bean1.setDomType(String.valueOf(domain[i][0]));
					request.setAttribute("domName" + m, String.valueOf(
							domain[i][1]).trim());
					request.setAttribute("domId" + m, String
							.valueOf(domain[i][2]));
					if (String.valueOf(domain[i][3]).equals("")
							|| String.valueOf(domain[i][3]).equals("null")) {
						bean1.setDomExp("");
					} else {
						bean1.setDomExp(String.valueOf(domain[i][3]));
					}

					bean1.setDomSel(String.valueOf(domain[i][4]));

					logger.info("selct is" + String.valueOf(domain[i][4]));

					bean1.setDomDetCode(String.valueOf(domain[i][5]));
					m++;

					tableList.add(bean1);

				}// End of for loop
			}// End of if condition

			manPowerReqs.setDomList(tableList);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// End of method

	public void searchDomainDtlInView(EmployeeRequisition manPowerReqs,
			String code, HttpServletRequest request) {
		try {

			String query = "SELECT CASE WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_TYPE='E' THEN 'Essential' WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_TYPE='D' THEN 'Desirable' ELSE ' ' END, NVL(HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_NAME,' '), HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_CODE, NVL(HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_EXP,''),"
					+ " CASE WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_OPTION='A' THEN 'And' WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_OPTION='R' THEN 'Or' ELSE ' ' END , HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FUN_DTL_CODE FROM HRMS_REC_REQS_SKILL_FUNC_DTL LEFT JOIN HRMS_FUNC_DOMAIN_MASTER "
					+ " ON HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_CODE=HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_CODE "
					+ " WHERE REQS_CODE="
					+ code
					+ " AND HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FIELD_TYPE='F' ORDER BY HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_CODE";

			Object[][] domain = getSqlModel().getSingleResult(query);

			ArrayList<Object> tableList = new ArrayList<Object>();
			if (domain != null && domain.length > 0) {
				manPowerReqs.setDomFlag("true");
				manPowerReqs.setViewDom("true");
				int m = 1;
				for (int i = 0; i < domain.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();

					bean1.setDomType(String.valueOf(domain[i][0]));

					// bean1.setDomType(String.valueOf(domain[i][0]));
					request.setAttribute("domName" + m, String.valueOf(
							domain[i][1]).trim());
					request.setAttribute("domId" + m, String
							.valueOf(domain[i][2]));
					if (String.valueOf(domain[i][3]).equals("")
							|| String.valueOf(domain[i][3]).equals("null")) {
						bean1.setDomExp("");
					} else {
						bean1.setDomExp(String.valueOf(domain[i][3]));
					}

					bean1.setDomSel(String.valueOf(domain[i][4]));
					bean1.setDomDetCode(String.valueOf(domain[i][5]));
					m++;

					tableList.add(bean1);

				}// End of for loop
			}// End of if condition

			manPowerReqs.setDomList(tableList);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// End of method

	/**
	 * following function is called to populate the certification details
	 * 
	 * @param manPowerReqs
	 * @param code
	 * @param request
	 */
	public void searchCertDtl(EmployeeRequisition manPowerReqs, String code,
			HttpServletRequest request) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			String query = "SELECT  HRMS_REC_REQS_CERT_DTL.REQS_CERTI_TYPE, NVL(HRMS_REC_REQS_CERT_DTL.REQS_CERTI_NAME,' '), NVL(HRMS_REC_REQS_CERT_DTL.REQS_CERTI_ISSUED_BY,' '), NVL(HRMS_REC_REQS_CERT_DTL.REQS_CERTI_VALID_TILL,' '), HRMS_REC_REQS_CERT_DTL.REQS_CERTI_OPTION, HRMS_REC_REQS_CERT_DTL.REQS_CERT_DTL_CODE FROM HRMS_REC_REQS_CERT_DTL"
					+ " WHERE HRMS_REC_REQS_CERT_DTL.REQS_CODE="
					+ code
					+ " ORDER BY NVL(HRMS_REC_REQS_CERT_DTL.REQS_CERTI_NAME,' ')";
			Object[][] cert = getSqlModel().getSingleResult(query);
			if (cert != null && cert.length > 0) {
				manPowerReqs.setCertFlag("true");
				manPowerReqs.setViewCert("true");
				for (int i = 0; i < cert.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();
					bean1.setCertType(String.valueOf(cert[i][0]));
					bean1.setCertName(String.valueOf(cert[i][1]).trim());
					bean1.setCertIssueBy(String.valueOf(cert[i][2]).trim());
					bean1.setCertValid(String.valueOf(cert[i][3]).trim());
					bean1.setCertOption(String.valueOf(cert[i][4]));
					// bean1.setCertOption(String.valueOf(cert[i][4]));
					bean1.setCertDetCode(String.valueOf(cert[i][5]));
					tableList.add(bean1);
				} // End of for loop
			}// End of if condition
			manPowerReqs.setCertList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// END OF METHOD

	public void searchCertDtlInView(EmployeeRequisition manPowerReqs,
			String code, HttpServletRequest request) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			String query = "SELECT  CASE WHEN HRMS_REC_REQS_CERT_DTL.REQS_CERTI_TYPE='E' THEN 'Essential' when HRMS_REC_REQS_CERT_DTL.REQS_CERTI_TYPE='D' then 'Desirable' else ' ' end , NVL(HRMS_REC_REQS_CERT_DTL.REQS_CERTI_NAME,' '), NVL(HRMS_REC_REQS_CERT_DTL.REQS_CERTI_ISSUED_BY,' '), NVL(HRMS_REC_REQS_CERT_DTL.REQS_CERTI_VALID_TILL,' '), "
					+ " CASE WHEN HRMS_REC_REQS_CERT_DTL.REQS_CERTI_OPTION='A' THEN 'And' WHEN HRMS_REC_REQS_CERT_DTL.REQS_CERTI_OPTION='R' THEN 'Or' ELSE ' ' END, HRMS_REC_REQS_CERT_DTL.REQS_CERT_DTL_CODE FROM HRMS_REC_REQS_CERT_DTL"
					+ " WHERE HRMS_REC_REQS_CERT_DTL.REQS_CODE="
					+ code
					+ " ORDER BY NVL(HRMS_REC_REQS_CERT_DTL.REQS_CERTI_NAME,' ')";
			Object[][] cert = getSqlModel().getSingleResult(query);

			if (cert != null && cert.length > 0) {
				manPowerReqs.setCertFlag("true");
				manPowerReqs.setViewCert("true");
				for (int i = 0; i < cert.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();
					bean1.setCertType(String.valueOf(cert[i][0]));
					bean1.setCertName(String.valueOf(cert[i][1]).trim());
					bean1.setCertIssueBy(String.valueOf(cert[i][2]).trim());
					bean1.setCertValid(String.valueOf(cert[i][3]).trim());
					bean1.setCertOption(String.valueOf(cert[i][4]));
					// bean1.setCertOption(String.valueOf(cert[i][4]));
					bean1.setCertDetCode(String.valueOf(cert[i][5]));
					tableList.add(bean1);
				} // End of for loop
			}// End of if condition
			manPowerReqs.setCertList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// END OF METHOD

	public void getReport(HttpServletRequest request,
			HttpServletResponse response, EmployeeRequisition bean, String type) {
		try {
			String sqlQuery = "SELECT NVL(HRMS_REC_REQS_HDR.REQS_NAME,' '), HRMS_REC_REQS_HDR.REQS_POSITION, NVL(HRMS_RANK.RANK_NAME,' '), DECODE(HRMS_REC_REQS_HDR.REQS_STATUS,'O','Open','C','Close'),"
					+ " HRMS_REC_REQS_HDR.REQS_DIVISION, NVL(HRMS_DIVISION.DIV_NAME,' '), HRMS_REC_REQS_HDR.REQS_BRANCH, NVL(HRMS_CENTER.CENTER_NAME,' '), HRMS_REC_REQS_HDR.REQS_DEPT,NVL(HRMS_DEPT.DEPT_NAME,' '),DECODE(HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','S','Send Back','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),"
					+ " HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER, OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||' '||OFFC1.EMP_LNAME, CASE WHEN HRMS_REC_REQS_HDR.REQS_RECTYPE_INT='Y' THEN 'Internal' ELSE ' ' END,"
					+ " HRMS_REC_REQS_HDR.REQS_APPLIED_BY,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME,NVL(HRMS_REC_REQS_HDR.REQS_JOBDESC_NAME,' '),"
					+ " NVL(HRMS_REC_REQS_HDR.REQS_JOB_DESCRIPTION,' '),NVL(HRMS_REC_REQS_HDR.REQS_ROLE_RESPON,' '),NVL(HRMS_REC_REQS_HDR.REQS_SPECIAL_REQ,' '),NVL(HRMS_REC_REQS_HDR.REQS_PERSONEL_REQ,' '),HRMS_REC_REQS_HDR.REQS_VACAN_COMPEN,"
					+ " NVL(HRMS_REC_REQS_HDR.REQS_VACAN_EXPMIN,''),NVL(HRMS_REC_REQS_HDR.REQS_VACAN_EXPMAX,''),decode(HRMS_REC_REQS_HDR.REQS_VACAN_TYPE,'N','New Post','R','Replacement','',''),decode(HRMS_REC_REQS_HDR.REQS_VACAN_CONTRACT,'R','Regular','O','On Contract','',''),"
					+ " decode(HRMS_REC_REQS_HDR.REQS_VACAN_CONTYPE,'P','Part Time','F','Full Time'),HRMS_REC_REQS_HDR.REQS_APPR_CODE,HRMS_REC_REQS_HDR.REQS_LEVEL,CASE WHEN HRMS_REC_REQS_HDR.REQS_RECTYPE_EXT='Y' THEN 'External' ELSE ' ' END,NVL(TO_CHAR(HRMS_REC_REQS_HDR.REQS_DATE,'DD-MM-YYYY'),' ') FROM HRMS_REC_REQS_HDR "
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON OFFC1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER "
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY "
					+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION "
					+ " LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_REC_REQS_HDR.REQS_DIVISION "
					+ " LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_REC_REQS_HDR.REQS_DEPT "
					+ " LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH "
					+ " WHERE HRMS_REC_REQS_HDR.REQS_CODE=" + bean.getReqCode();

			Object[][] reqHdrDet = getSqlModel().getSingleResult(sqlQuery);

			String query = "SELECT ROWNUM,NVL(HRMS_REC_REQS_VACDTL.VACAN_NUMBERS,0),NVL(TO_CHAR(HRMS_REC_REQS_VACDTL.VACAN_REQ_DATE,'DD-MM-YYYY'),' ') FROM HRMS_REC_REQS_VACDTL"
					+ " WHERE HRMS_REC_REQS_VACDTL.REQS_CODE="
					+ bean.getReqCode();
			Object[][] vacDets = getSqlModel().getSingleResult(query);

			String apprvQuery = " SELECT ROWNUM, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,NVL(TO_CHAR(HRMS_REC_REQS_PATH.PATH_APPR_DATE,'DD-MM-YYYY'),' '),"
					+ " NVL(HRMS_REC_REQS_PATH.PATH_REMARK,' ') FROM HRMS_REC_REQS_PATH "
					+ " INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_REQS_PATH.PATH_REQ_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_REQS_PATH.PATH_APPROVER_CODE)"
					+ " WHERE REQS_CODE=" + bean.getReqCode();
			Object[][] apprvData = getSqlModel().getSingleResult(apprvQuery);

			String query1 = "SELECT ROWNUM,CASE WHEN HRMS_REC_REQS_QUA_DTL.REQS_QUA_TYPE='E' THEN 'Essential' WHEN HRMS_REC_REQS_QUA_DTL.REQS_QUA_TYPE='D' THEN 'Desirable' else ' ' end ,NVL(HRMS_QUA.QUA_ABBR,' '),CASE WHEN HRMS_QUA.QUA_LEVEL='UG' THEN 'Under Graduate' "
					+ " WHEN HRMS_QUA.QUA_LEVEL='DI' THEN 'Diploma' WHEN HRMS_QUA.QUA_LEVEL='GR' THEN 'Graduate' WHEN HRMS_QUA.QUA_LEVEL='PG' THEN 'Post Graduate'"
					+ " WHEN HRMS_QUA.QUA_LEVEL='PH' THEN 'Phd' WHEN HRMS_QUA.QUA_LEVEL='DO' THEN 'Doctorate' ELSE ' ' END,"
					+ " NVL(HRMS_SPECIALIZATION.SPEC_ABBR,' '),HRMS_REC_REQS_QUA_DTL.REQS_QUA_CUTOFF,CASE WHEN HRMS_REC_REQS_QUA_DTL.REQS_QUA_OPTION='A' THEN 'And' WHEN HRMS_REC_REQS_QUA_DTL.REQS_QUA_OPTION='R' THEN 'Or' ELSE ' ' END"
					+ " ,REQS_QUA_DTL_CODE FROM HRMS_REC_REQS_QUA_DTL LEFT JOIN HRMS_QUA ON HRMS_QUA.QUA_ID=HRMS_REC_REQS_QUA_DTL.REQS_QUA_CODE "
					+ " LEFT JOIN HRMS_SPECIALIZATION ON HRMS_REC_REQS_QUA_DTL.REQS_SPECIALIZATION_CODE=HRMS_SPECIALIZATION.SPEC_ID"
					+ " WHERE HRMS_REC_REQS_QUA_DTL.REQS_CODE="
					+ bean.getReqCode();

			Object[][] qualification = getSqlModel().getSingleResult(query1);

			String query2 = "SELECT ROWNUM,CASE WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_TYPE='E' THEN 'Essential' WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_TYPE='D' THEN 'Desirable' ELSE ' ' END ,NVL(HRMS_SKILL.SKILL_NAME,' '), NVL(HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_EXP,''),"
					+ " CASE WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_OPTION='A' THEN 'And' WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_OPTION='R' THEN 'Or' ELSE ' ' END ,HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FUN_DTL_CODE FROM HRMS_REC_REQS_SKILL_FUNC_DTL LEFT JOIN HRMS_SKILL "
					+ " ON HRMS_SKILL.SKILL_ID=HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_CODE "
					+ " WHERE HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_CODE="
					+ bean.getReqCode()
					+ " AND HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FIELD_TYPE='S' ";

			Object[][] skill = getSqlModel().getSingleResult(query2);

			String query3 = "SELECT ROWNUM,CASE WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_TYPE='E' THEN 'Essential' WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_TYPE='D' THEN 'Desirable' ELSE ' ' END,NVL(HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_NAME,' '),NVL(HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_EXP,''),"
					+ " CASE WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_OPTION='A' THEN 'And' WHEN HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_OPTION='R' THEN 'Or' ELSE ' ' END ,HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FUN_DTL_CODE FROM HRMS_REC_REQS_SKILL_FUNC_DTL LEFT JOIN HRMS_FUNC_DOMAIN_MASTER "
					+ " ON HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_CODE=HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_CODE "
					+ " WHERE HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_CODE="
					+ bean.getReqCode()
					+ " AND HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_FIELD_TYPE='F' ";

			Object[][] domain = getSqlModel().getSingleResult(query3);

			String query4 = "SELECT  ROWNUM,CASE WHEN HRMS_REC_REQS_CERT_DTL.REQS_CERTI_TYPE='E' THEN 'Essential' when HRMS_REC_REQS_CERT_DTL.REQS_CERTI_TYPE='D' then 'Desirable' else ' ' end ,NVL(HRMS_REC_REQS_CERT_DTL.REQS_CERTI_NAME,' '),NVL(HRMS_REC_REQS_CERT_DTL.REQS_CERTI_ISSUED_BY,' '),NVL(HRMS_REC_REQS_CERT_DTL.REQS_CERTI_VALID_TILL,' '),CASE WHEN HRMS_REC_REQS_CERT_DTL.REQS_CERTI_OPTION='A' THEN 'And' WHEN HRMS_REC_REQS_CERT_DTL.REQS_CERTI_OPTION='R' THEN 'Or' ELSE ' ' END,HRMS_REC_REQS_CERT_DTL.REQS_CERT_DTL_CODE FROM HRMS_REC_REQS_CERT_DTL"
					+ " WHERE HRMS_REC_REQS_CERT_DTL.REQS_CODE="
					+ bean.getReqCode();
			Object[][] cert = getSqlModel().getSingleResult(query4);

			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					type, "ManPower Requisition Report");
			if (type.equals("Pdf")) {
				rg.setFName("Manpower Requisition Report.Pdf");
			} else {
				rg.setFName("Manpower Requisition Report.doc");
			}

			Object manpow[][] = new Object[6][7];
			Object job[][] = new Object[5][3];
			Object vac[][] = new Object[2][7];
			rg.addFormatedText("Manpower Requisition Report", 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addText("\n", 0, 0, 0);
			int[] bcellWidth = { 10, 3, 15, 7, 10, 3, 15 };
			int[] bcellAlign = { 0, 0, 0, 0, 0, 0, 0 };
			int[] jobCellWidth = { 10, 3, 50 };
			int[] jobCellAlign = { 0, 0, 0 };
			int[] vacCellWidth = { 5, 5, 10 };
			int[] vacCellAlign = { 1, 1, 1 };
			int[] apprvCellWidth = { 3, 15, 10, 10 };
			int[] apprvCellAlign = { 1, 0, 1, 0 };
			String[] vacCol = { "Sr No", "No. Of Vacancies", "Required by Date" };
			String[] apprvCol = { "Sr No", "Approver Name", "Approved Date",
					"Approver's Comment" };

			rg.addTextBold("Manpower Information :", 0, 0, 0);
			rg.addText("\n", 0, 0, 0);
			manpow[0][0] = "Requisition Code";
			manpow[0][1] = ":";
			manpow[0][2] = "" + reqHdrDet[0][0];
			manpow[0][3] = "";
			manpow[0][4] = "Requisition Date";
			manpow[0][5] = ":";
			manpow[0][6] = "" + reqHdrDet[0][30];
			manpow[1][0] = "Position";
			manpow[1][1] = ":";
			manpow[1][2] = "" + reqHdrDet[0][2];
			manpow[1][3] = "";
			manpow[1][4] = "Approval Status";
			manpow[1][5] = ":";
			manpow[1][6] = "" + reqHdrDet[0][10];
			manpow[2][0] = "Division";
			manpow[2][1] = ":";
			manpow[2][2] = "" + reqHdrDet[0][5];
			manpow[2][3] = "";
			manpow[2][4] = "Requisition Status";
			manpow[2][5] = ":";
			manpow[2][6] = "" + reqHdrDet[0][3];
			manpow[3][0] = "Branch";
			manpow[3][1] = ":";
			manpow[3][2] = "" + reqHdrDet[0][7];
			manpow[3][3] = "";
			manpow[3][4] = "";
			manpow[3][5] = "";
			manpow[3][6] = "";
			manpow[4][0] = "Department";
			manpow[4][1] = ":";
			manpow[4][2] = "" + reqHdrDet[0][9];
			manpow[4][3] = "";
			manpow[4][4] = "";
			manpow[4][5] = "";
			manpow[4][6] = "";
			manpow[5][0] = "Hiring Manager";
			manpow[5][1] = ":";
			manpow[5][2] = "" + reqHdrDet[0][12];
			manpow[5][3] = "";
			manpow[5][4] = "Recruitment Type";
			manpow[5][5] = ":";
			manpow[5][6] = reqHdrDet[0][13] + "    " + reqHdrDet[0][29];

			if (type.equals("Pdf"))
				rg.tableBodyNoCellBorder(manpow, bcellWidth, bcellAlign, 0);
			else
				rg.tableBody(manpow, bcellWidth, bcellAlign);

			rg.addTextBold("Job Description :", 0, 0, 0);
			rg.addText("\n", 0, 0, 0);

			job[0][0] = "Job Name";
			job[0][1] = ": ";
			job[0][2] = "" + reqHdrDet[0][16];
			job[1][0] = "Job Description";
			job[1][1] = ":";
			job[1][2] = "" + reqHdrDet[0][17];
			job[2][0] = "Job Roles and Responsibilty";
			job[2][1] = ":";
			job[2][2] = "" + reqHdrDet[0][18];
			job[3][0] = "Special Requirement";
			job[3][1] = ":";
			job[3][2] = "" + reqHdrDet[0][19];
			job[4][0] = "Personal Qualities";
			job[4][1] = ":";
			job[4][2] = "" + reqHdrDet[0][20];

			if (type.equals("Pdf"))
				rg.tableBodyNoCellBorder(job, jobCellWidth, jobCellAlign, 0);
			else
				rg.tableBody(job, jobCellWidth, jobCellAlign);

			// rg.tableBodyNoBorder(job, bcellWidth, bcellAlign);
			if (vacDets != null && vacDets.length > 0) {
				rg.addTextBold("Vacancy Details:", 0, 0, 0);
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(vacCol, vacDets, vacCellWidth, vacCellAlign);
			}

			vac[0][0] = "Compensation In Lacs";
			vac[0][1] = ":";
			vac[0][2] = "" + reqHdrDet[0][21];
			vac[0][3] = "";
			vac[0][4] = "Experience";
			vac[0][5] = ":";
			vac[0][6] = " Min :"
					+ (String.valueOf(reqHdrDet[0][22]).equals("null") ? ""
							: reqHdrDet[0][22])
					+ " "
					+ "Max :"
					+ (String.valueOf(reqHdrDet[0][23]).equals("null") ? ""
							: reqHdrDet[0][23]);

			vac[1][0] = "Vacancy Type";
			vac[1][1] = ":";
			vac[1][2] = "" + reqHdrDet[0][24];// +reqHdrDet[0][24];
			vac[1][3] = "";
			vac[1][4] = "Contract Type:";
			vac[1][5] = ": ";
			vac[1][6] = ""
					+ (String.valueOf(reqHdrDet[0][25]).equals("null") ? ""
							: reqHdrDet[0][25])
					+ "  "
					+ (String.valueOf(reqHdrDet[0][26]).equals("null") ? ""
							: reqHdrDet[0][26]);
			if (type.equals("Pdf")) {
				rg.tableBodyNoCellBorder(vac, bcellWidth, bcellAlign, 0);
			} else {
				rg.tableBody(vac, bcellWidth, bcellAlign);
			}

			if (qualification != null && qualification.length > 0) {
				rg.addTextBold("Qualification Details:", 0, 0, 0);
				rg.addText("\n", 0, 0, 0);
				String colnames1[] = { "Sr No", "Qualification Type",
						"Qualification Abbreviation", "Qualification Level",
						"Specialization", "Cut Off Marks", "Option" };
				int cellwidth1[] = { 5, 10, 15, 10, 10, 10, 5 };
				int alignment1[] = { 1, 1, 0, 0, 0, 0, 0 };
				rg.tableBody(colnames1, qualification, cellwidth1, alignment1);
			}

			if (skill != null && skill.length > 0) {
				rg.addTextBold("Skill Details:", 0, 0, 0);
				rg.addText("\n", 0, 0, 0);
				String colnames2[] = { "Sr No", "Skill Type", "Skill Name",
						"Experience in Years", "Option" };
				int cellwidth2[] = { 5, 10, 10, 5, 5 };
				int alignment2[] = { 1, 1, 0, 0, 0 };
				rg.tableBody(colnames2, skill, cellwidth2, alignment2);
			}

			if (domain != null && domain.length > 0) {
				// rg.addFormatedText("Domain/Functional Details:", 6, 0, 0, 0);
				rg.addTextBold("Domain/Functional Details:", 0, 0, 0);
				rg.addText("\n", 0, 0, 0);
				String colnames3[] = { "Sr No", "Domain Type", "Domain Name",
						"Experience in Years", "Option" };
				int cellwidth3[] = { 5, 10, 10, 5, 5 };
				int alignment3[] = { 1, 1, 0, 0, 0 };
				rg.tableBody(colnames3, domain, cellwidth3, alignment3);
			}

			if (cert != null && cert.length > 0) {
				rg.addTextBold("Certification Details:", 0, 0, 0);
				rg.addText("\n", 0, 0, 0);
				String colnames4[] = { "Sr No", "Certification Type",
						"Certification Name", "Issued By", "Valid Till",
						"Option" };
				int cellwidth4[] = { 5, 10, 10, 10, 5, 5 };
				int alignment4[] = { 1, 0, 0, 0, 0, 0 };
				rg.tableBody(colnames4, cert, cellwidth4, alignment4);
			}

			if (apprvData != null && apprvData.length > 0) {
				rg.addTextBold("Approver Details:", 0, 0, 0);
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(apprvCol, apprvData, apprvCellWidth,
						apprvCellAlign);

			}

			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * created by Varun Khetan on 28/04/2009
	 * 
	 * @param manPowerReqs
	 */
	public void updApprStatFrmOfferAppoint(EmployeeRequisition manPowerReqs,
			String code) {
		try {
			String update = "UPDATE HRMS_REC_REQS_HDR SET HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS = 'Q' WHERE HRMS_REC_REQS_HDR.REQS_CODE = "
					+ code + "";
			getSqlModel().singleExecute(update);
		} catch (Exception e) {
			e.printStackTrace();
		}

	} // end of method

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * This method is useful for selecting record from the search window
	 * 
	 * @param bean
	 */
	public void getJobDetails(EmployeeRequisition bean) {
		try {
			String query = " SELECT NVL(HRMS_JOB_DESCRIPTION.JOB_DESC_NAME,' '),TO_CHAR(HRMS_JOB_DESCRIPTION.JOB_DESC_EFFC_DATE,'DD-MM-YYYY'),NVL(HRMS_JOB_DESCRIPTION.JOB_DESC_DESC, "
					+ "' '),NVL(HRMS_JOB_DESCRIPTION.JOB_DESC_ROLE_RESP,' '),HRMS_JOB_DESCRIPTION.JOB_DESC_CODE, NVL(HRMS_JOB_DESCRIPTION.JOB_DESC_MIN_RECRUIT_DAYS,0) FROM HRMS_JOB_DESCRIPTION  WHERE HRMS_JOB_DESCRIPTION.JOB_DESC_STATUS='A' "
					+ " AND HRMS_JOB_DESCRIPTION.JOB_DESC_CODE= "
					+ bean.getJdCode()
					+ "  AND HRMS_JOB_DESCRIPTION.JOB_DESC_EFFC_DATE <= TO_DATE("
					+ "'" + bean.getReqDt() + "'" + ",'DD-MM-YYYY') ";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				bean
						.setJdDescName(checkNull(String.valueOf(data[0][0])
								.trim()));
				bean.setJdEffDate(checkNull(String.valueOf(data[0][1]).trim()));
				bean.setJdDesc(checkNull(String.valueOf(data[0][2]).trim()));
				bean
						.setJdRoleDesc(checkNull(String.valueOf(data[0][3])
								.trim()));
				bean.setJdCode(checkNull(String.valueOf(data[0][4]).trim()));
				bean.setMinReuiredDays(checkNull(String.valueOf(data[0][5])
						.trim()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ====METHOD ADDED BY VARUN KHETAN=====
	 */
	public String leadTimeCheck() throws Exception {
		String value = "0";
		try {
			String sqlQuery = "SELECT NVL(HRMS_REC_CONF.CONF_LEAD_TIME,0) FROM HRMS_REC_CONF";
			Object[][] confData = getSqlModel().getSingleResult(sqlQuery);
			if (confData != null && confData.length > 0) {
				value = String.valueOf(confData[0][0]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public void removeEmpList(EmployeeRequisition manPowerReqs) {
		Object data[][] = null;
		try {
			String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID IN ("
					+ manPowerReqs.getReplaceEmpId() + ")";
			data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				ArrayList list = new ArrayList();
				for (int i = 0; i < data.length; i++) {
					EmployeeRequisition bean = new EmployeeRequisition();
					bean.setRemoveEmpId(String.valueOf(data[i][2]));
					bean.setRemoveEmpToken(String.valueOf(data[i][0]));
					bean.setRemoveEmpName(String.valueOf(data[i][1]));
					list.add(bean);
				}
				manPowerReqs.setRemoveEmpDataList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getRemoveList(EmployeeRequisition manPowerReqs) {
		Object data[][] = null;
		String empName = "";
		try {
			String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID IN ("
					+ manPowerReqs.getReplaceEmpId() + ")";
			data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					empName += String.valueOf(data[i][0]) + "-"
							+ String.valueOf(data[i][1]) + ",";
				}
				empName = empName.substring(0, empName.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		manPowerReqs.setReplaceEmpName(empName);
	}

	public void setDropDownValueList(EmployeeRequisition manPowerReqs) {
		try {
			TreeMap map = new TreeMap();
			String selectSql = " SELECT HRMS_REC_ROUND_TYPE.ROUND_CODE, HRMS_REC_ROUND_TYPE.ROUND_TYPE FROM HRMS_REC_ROUND_TYPE ORDER BY HRMS_REC_ROUND_TYPE.ROUND_CODE  ";
			Object dataObj[][] = getSqlModel().getSingleResult(selectSql);
			if (dataObj != null && dataObj.length > 0) {
				for (int i = 0; i < dataObj.length; i++) {
					map.put(String.valueOf(dataObj[i][0]), String
							.valueOf(dataObj[i][1]));
				}
			}
			/*
			 * else { map.put("-1","--Select--"); }
			 */
			manPowerReqs.setTmap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean updateMasterData(String jbDescName, String jbDesc,
			String roleDesc, EmployeeRequisition manPowerReqs, String jdCode,
			HttpServletRequest request) {
		boolean result = false;
		try {
			String checkDuplicate = " SELECT HRMS_JOB_DESCRIPTION.JOB_DESC_CODE, HRMS_JOB_DESCRIPTION.JOB_DESC_EFFC_DATE, HRMS_JOB_DESCRIPTION.JOB_DESC_NAME, HRMS_JOB_DESCRIPTION.JOB_DESC_DESC, HRMS_JOB_DESCRIPTION.JOB_DESC_ROLE_RESP, HRMS_JOB_DESCRIPTION.JOB_DESC_STATUS, HRMS_JOB_DESCRIPTION.JOB_DESC_MIN_RECRUIT_DAYS, HRMS_JOB_DESCRIPTION.JOB_DESC_GRADE FROM HRMS_JOB_DESCRIPTION WHERE UPPER(HRMS_JOB_DESCRIPTION.JOB_DESC_NAME) LIKE '"
					+ jbDescName.trim().toUpperCase() + "'";
			Object checkDuplicateObj[][] = getSqlModel().getSingleResult(
					checkDuplicate);
			if (checkDuplicateObj != null && checkDuplicateObj.length > 0) {
				Object[][] updateObj = new Object[1][3];
				updateObj[0][0] = request.getParameter("JbDesc");
				updateObj[0][1] = request.getParameter("RoleDesc");
				updateObj[0][2] = jdCode;
				getSqlModel().singleExecute(getQuery(22), updateObj);
			} else {
				String inserQuery = "INSERT INTO HRMS_JOB_DESCRIPTION(HRMS_JOB_DESCRIPTION.JOB_DESC_CODE, HRMS_JOB_DESCRIPTION.JOB_DESC_NAME, HRMS_JOB_DESCRIPTION.JOB_DESC_DESC, HRMS_JOB_DESCRIPTION.JOB_DESC_ROLE_RESP, HRMS_JOB_DESCRIPTION.JOB_DESC_STATUS, HRMS_JOB_DESCRIPTION.JOB_DESC_EFFC_DATE) VALUES ( "
						+ " (SELECT NVL(MAX(HRMS_JOB_DESCRIPTION.JOB_DESC_CODE),0)+1 FROM HRMS_JOB_DESCRIPTION), '"
						+ jbDescName
						+ "','"
						+ jbDesc
						+ "','"
						+ roleDesc
						+ "','A',(select To_date(TO_DATE(Sysdate,'DD-MM-YYYY')) from dual)) ";
				result = getSqlModel().singleExecute(inserQuery);
			}
			/*
			 * if (checkDuplicateObj != null && checkDuplicateObj.length > 0) {
			 * String inserQuery = "INSERT INTO
			 * HRMS_JOB_DESCRIPTION(JOB_DESC_CODE, JOB_DESC_NAME, JOB_DESC_DESC,
			 * JOB_DESC_ROLE_RESP,JOB_DESC_STATUS,JOB_DESC_EFFC_DATE) VALUES ( " + "
			 * (SELECT NVL(MAX(JOB_DESC_CODE),0)+1 FROM HRMS_JOB_DESCRIPTION), '" +
			 * jbDescName + "','" + jbDesc + "','" + roleDesc + "','A',(select
			 * To_date(TO_DATE(Sysdate,'DD-MM-YYYY')) from dual)) "; result =
			 * getSqlModel().singleExecute(inserQuery); }
			 */
			getJobDetails(manPowerReqs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void addKeepInfoTo(EmployeeRequisition manPowerReqs,
			String[] empToken, String[] empName, String[] empid) {
		try {
			ArrayList<EmployeeRequisition> keepinfoList = new ArrayList<EmployeeRequisition>();
			if (empToken != null && empToken.length > 0) {
				for (int i = 0; i < empToken.length; i++) {
					EmployeeRequisition innerBean = new EmployeeRequisition();
					innerBean.setKeepInformedEmpTokenItt(empToken[i]);
					innerBean.setKeepInformedEmpNameItt(empName[i]);
					innerBean.setEmpid(empid[i]);
					keepinfoList.add(innerBean);
				}
			}
			EmployeeRequisition bean = new EmployeeRequisition();
			bean.setKeepInformedEmpTokenItt(manPowerReqs.getEmployeeToken()
					.trim());
			bean.setKeepInformedEmpNameItt(manPowerReqs.getEmployeeName()
					.trim());
			bean.setEmpid(manPowerReqs.getEmployeeId().trim());
			keepinfoList.add(bean);

			manPowerReqs.setKeepInformedList(keepinfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteDivisionRecord(EmployeeRequisition manPowerReqs,
			String[] keepInformedEmpTokenItt, String[] keepInformedEmpNameItt,
			String[] empid) {
		try {
			ArrayList<Object> list = new ArrayList();
			if (empid != null && empid.length > 0) {
				for (int i = 0; i < empid.length; i++) {
					EmployeeRequisition innerBean = new EmployeeRequisition();
					innerBean
							.setKeepInformedEmpTokenItt(keepInformedEmpTokenItt[i]);
					innerBean
							.setKeepInformedEmpNameItt(keepInformedEmpNameItt[i]);
					innerBean.setEmpid(empid[i]);

					list.add(innerBean);
				}
			}
			list.remove(Integer.parseInt(manPowerReqs.getHiddenDeleteId()) - 1);
			manPowerReqs.setKeepInformedList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean setApproverData(EmployeeRequisition manPowerReqs,
			Object[][] empFlow) {
		boolean isApproverLogin=false;
		try {
			if (empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][2];
				for (int i = 0; i < empFlow.length; i++) {
					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
							+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME  "
							+ " ,EMP_ID FROM HRMS_EMP_OFFC "
							+ " WHERE HRMS_EMP_OFFC.EMP_ID IN("
							+ empFlow[i][0]
							+ ")";

					Object temObj[][] = getSqlModel().getSingleResult(
							selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
					approverDataObj[i][1] = String.valueOf(temObj[0][1]);
				}

				if (approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for (int i = 0; i < approverDataObj.length; i++) {
						EmployeeRequisition empBean = new EmployeeRequisition();
						empBean.setApproverName(String
								.valueOf(approverDataObj[i][0]));
						/*
						 * String srNo = i + 1 + getOrdinalFor(i + 1) +
						 * "-Approver"; empBean.setSrNoIterator(srNo);
						 */
						if(String.valueOf(approverDataObj[i][1]).equals(manPowerReqs.getUserEmpId()))
						{
							isApproverLogin=true;
						}
						
						System.out.println("isApproverLogin   "+isApproverLogin);
						
						arrList.add(empBean);
					}
					manPowerReqs.setApproverList(arrList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isApproverLogin;
	}

	/**
	 * @Method getOrdinalFor
	 * @Purpose to append the ordinal with serial number
	 * @param value
	 *            value to which ordinal will be appended
	 * @return String
	 */
	public String getOrdinalFor(int value) {
		int hundredRemainder = value % 100;
		if (hundredRemainder >= 10 && hundredRemainder <= 20) {
			return "th";
		} // end of if
		int tenRemainder = value % 10;
		switch (tenRemainder) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		} // end of switch
	}

	public void removeKeepInformedData(String[] serialNo, String[] empCode,
			String[] empName, EmployeeRequisition manPowerReqs) {
		try {
			ArrayList<Object> keepinfoList = new ArrayList<Object>();
			if (serialNo != null) {
				for (int i = 0; i < serialNo.length; i++) {
					EmployeeRequisition bean = new EmployeeRequisition();
					bean.setSrNo(String.valueOf(i + 1));
					bean.setEmpid(empCode[i]);
					bean.setKeepInformedEmpNameItt(empName[i]);
					keepinfoList.add(bean);

				}
				keepinfoList.remove(Integer.parseInt(manPowerReqs
						.getCheckRemove()) - 1);
			}
			manPowerReqs.setKeepInformedList(keepinfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void displayIteratorValueForKeepInformed(String[] srNo,
			String[] empCode, String[] empName, EmployeeRequisition manPowerReqs) {
		try {
			ArrayList<EmployeeRequisition> keepinfoarrayList = new ArrayList<EmployeeRequisition>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					EmployeeRequisition bean = new EmployeeRequisition();
					bean.setEmpid(empCode[i]);
					bean.setKeepInformedEmpNameItt(empName[i]);
					bean.setSerialNo(srNo[i]);
					keepinfoarrayList.add(bean);
				}
				manPowerReqs.setKeepInformedList(keepinfoarrayList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setKeepInformed(String[] srNo, String[] empCode,
			String[] empName, EmployeeRequisition manPowerReqs) {
		try {
			EmployeeRequisition bean = new EmployeeRequisition();
			bean.setEmpid(manPowerReqs.getEmployeeId());
			bean.setKeepInformedEmpNameItt(manPowerReqs.getEmployeeName());
			ArrayList<EmployeeRequisition> list = displayNewValueForKeepInformed(
					srNo, empCode, empName, manPowerReqs);
			bean.setSerialNo(String.valueOf(list.size() + 1));// sr no
			list.add(bean);
			manPowerReqs.setKeepInformedList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private ArrayList<EmployeeRequisition> displayNewValueForKeepInformed(
			String[] srNo, String[] empCode, String[] empName,
			EmployeeRequisition manPowerReqs) {
		ArrayList<EmployeeRequisition> list = null;
		try {
			list = new ArrayList<EmployeeRequisition>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					EmployeeRequisition bean1 = new EmployeeRequisition();
					bean1.setEmpid(empCode[i]);
					bean1.setKeepInformedEmpNameItt(empName[i]);
					bean1.setSerialNo(srNo[i]);
					list.add(bean1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void updateKeepInfoData(HttpServletRequest request,
			EmployeeRequisition bean) {
		try {
			String str = "";
			String keepInformedEmpId[] = request.getParameterValues("empid");
			if (keepInformedEmpId != null && keepInformedEmpId.length > 0) {
				for (int i = 0; i < keepInformedEmpId.length; i++) {
					if (i == 0) {
						str += keepInformedEmpId[i];
					} else {
						str += "," + keepInformedEmpId[i];
					}
				}
			}

			String query = " UPDATE  HRMS_REC_REQS_HDR SET HRMS_REC_REQS_HDR.REQS_KEEP_INFO_ID='"
					+ str
					+ "'"
					+ "WHERE HRMS_REC_REQS_HDR.REQS_CODE="
					+ bean.getReqCode();
			getSqlModel().singleExecute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateRecordSendBack(EmployeeRequisition manPowerReqs,
			String ststus) {
		String query = " UPDATE  HRMS_REC_REQS_HDR WHERE HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS="
				+ ststus;
		getSqlModel().singleExecute(query);
	}

	public String getReportingPerson(String altEmpCode) {
		String hrempCode = "";
		try {
			Object data[][] = null;
			String sqlQuery = "";
			sqlQuery = "SELECT HRMS_EMP_OFFC.EMP_REPORTING_OFFICER FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID="
					+ altEmpCode;
			data = getSqlModel().getSingleResult(sqlQuery);
			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					hrempCode += String.valueOf(data[i][0]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hrempCode;
	}

	public void updateStatus(EmployeeRequisition manPowerReqs) {
		String query = " UPDATE  HRMS_REC_REQS_HDR SET HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS ='B' WHERE HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS ='S' AND HRMS_REC_REQS_HDR.REQS_CODE="
				+ manPowerReqs.getReqCode();
		getSqlModel().singleExecute(query);
	}

	public void changeStatus(HttpServletRequest request,
			EmployeeRequisition manPowerReqs) {
		String modifyQuery = " UPDATE HRMS_REC_REQS_HDR SET HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS ='B' WHERE HRMS_REC_REQS_HDR.REQS_CODE="
				+ manPowerReqs.getReqCode()
				+ "AND HRMS_REC_REQS_HDR.REQS_APPROVAL_STATUS ='S'";
		getSqlModel().singleExecute(modifyQuery);
	}

	public boolean updateJobDesc(EmployeeRequisition manPowerReqs) {
		Object[][] updateObj = new Object[1][3];
		updateObj[0][0] = manPowerReqs.getJdDesc().trim();
		updateObj[0][1] = manPowerReqs.getJdRoleDesc().trim();
		updateObj[0][2] = manPowerReqs.getJdCode().trim();
		return getSqlModel().singleExecute(getQuery(22), updateObj);
	}

	public void insertRecord(EmployeeRequisition manPowerReqs) {
		final Object[][] addObj = new Object[1][3];
		final String query1 = "SELECT NVL(MAX(HRMS_JOB_DESCRIPTION.JOB_DESC_CODE),0)+1 FROM  HRMS_JOB_DESCRIPTION";
		final Object[][] recordId = this.getSqlModel().getSingleResult(query1);

		addObj[0][0] = checkNull(String.valueOf(recordId[0][0]));
		addObj[0][1] = manPowerReqs.getJdDesc().trim();
		addObj[0][2] = manPowerReqs.getJdRoleDesc().trim();
		manPowerReqs.setJdCode(String.valueOf(recordId[0][0]));
		getSqlModel().singleExecute(this.getQuery(23), addObj);
	}

} //end