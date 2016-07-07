/**
 * 
 */
package org.struts.action.recruitment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.paradyne.bean.Recruitment.VacancyPublish;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.recruitment.VacancyPublishModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author varunk
 * 
 */
public class VacancyPublishAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(VacancyPublishAction.class);
	VacancyPublish vacanPublish;

	public void prepare_local() throws Exception {
		vacanPublish = new VacancyPublish();
		vacanPublish.setMenuCode(746);
	}

	public Object getModel() {
		return vacanPublish;
	}

	public VacancyPublish getVacanPublish() {
		return vacanPublish;
	}

	public void setVacanPublish(VacancyPublish vacanPublish) {
		this.vacanPublish = vacanPublish;
	}

	public String deleteSkill() {
		try {
			vacanPublish.setCancelFlag("true");
			String name[] = null;
			String code[] = null;
			String sn[] = request.getParameterValues("hssrlNo");
			String[] exp = request.getParameterValues("skillExp");
			String sdel[] = null;
			if (exp != null) {
				name = new String[exp.length];
				code = new String[exp.length];
				sdel = new String[exp.length];
				int j = 1;
				for (int i = 0; i < exp.length; i++) {
					name[i] = (String) request.getParameter("paraFrm_skillName"
							+ j);
					code[i] = (String) request.getParameter("paraFrm_skillId"
							+ j);
					sdel[i] = (String) request.getParameter("hdeleteSkill" + j);
					j++;
				}
			}
			VacancyPublishModel model = new VacancyPublishModel();
			model.initiate(context, session);
			model
					.delRecruiter(vacanPublish, request, sn, name, code, exp,
							sdel);
			model.terminate();
			getConsList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String reset() {
		try {
			vacanPublish.setReqCode("");
			vacanPublish.setReqName("");
			vacanPublish.setReqDate("");
			vacanPublish.setReqDt("");
			vacanPublish.setTotalVacancy("");
			vacanPublish.setAppliedBy("");
			vacanPublish.setHiringMgr("");
			vacanPublish.setPosition("");
			vacanPublish.setRequiredDate("");
			vacanPublish.setSkillExp("");
			vacanPublish.setComments("");
			vacanPublish.setIntEmployee("");
			vacanPublish.setRefProgram("");
			vacanPublish.setDivCode("");
			vacanPublish.setDivsion("");
			vacanPublish.setJobCons("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String deleteConsultant() {
		try {
			vacanPublish.setCancelFlag("true");
			String name[] = null;
			String code[] = null;
			String city[] = null;
			String cityName[] = null;
			String phoneNum[] = null;
			String email[] = null;
			String sn[] = request.getParameterValues("hssrlNoConsultant");
			String[] totalRows = request
					.getParameterValues("consultantVacancies");
			String sdel[] = null;
			if (totalRows != null) {
				name = new String[totalRows.length];
				code = new String[totalRows.length];
				city = new String[totalRows.length];
				cityName = new String[totalRows.length];
				phoneNum = new String[totalRows.length];
				email = new String[totalRows.length];
				sdel = new String[totalRows.length];
				int j = 1;
				for (int i = 0; i < totalRows.length; i++) {
					name[i] = (String) request
							.getParameter("paraFrm_consultantName" + j);
					code[i] = (String) request
							.getParameter("paraFrm_consultantId" + j);
					city[i] = (String) request.getParameter("paraFrm_city" + j);
					cityName[i] = (String) request
							.getParameter("paraFrm_cityName" + j);
					phoneNum[i] = (String) request
							.getParameter("paraFrm_phoneNo" + j);
					email[i] = (String) request.getParameter("paraFrm_emailAdd"
							+ j);
					sdel[i] = (String) request.getParameter("hdeleteConsultant"
							+ j);
					System.out.println();
					j++;
				}

			}
			VacancyPublishModel model = new VacancyPublishModel();
			model.initiate(context, session);
			model.delConsultant(vacanPublish, request, sn, name, code, city,
					phoneNum, email, totalRows, sdel, cityName);
			model.terminate();
			getRecList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String viewModePublish() throws Exception {
		try {
			VacancyPublishModel model = new VacancyPublishModel();
			model.initiate(context, session);
			vacanPublish.setFormAction(request.getParameter("formAction"));
			vacanPublish.setStatusKey(request.getParameter("statusKey"));
			vacanPublish.setFlagVal(request.getParameter("flagVal"));
			vacanPublish.setHeaderView("false");
			if (request.getParameter("requiDate") != null) {
				vacanPublish.setRequiredDate(request.getParameter("requiDate"));
			} else {
				vacanPublish.setRequiredDate(vacanPublish.getRequiredDate());
			}
			model.viewModePublish(vacanPublish, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewPublish";
	}

	public String viewCancelVacanciesList() throws Exception {
		try {
			VacancyPublishModel model = new VacancyPublishModel();
			String requisitionCode = request.getParameter("requisitionCode");
			String vacancyPublishCode = request
					.getParameter("vacancyPublishCode");
			String vacancyDetailCode = request
					.getParameter("vacancyDetailCode");
			model.initiate(context, session);
			model.viewCancelVacanciesList(vacanPublish, request,
					requisitionCode, vacancyPublishCode, vacancyDetailCode);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewcancelVacanciesList";
	}

	public String cancelVacancy() throws Exception {
		try {
			VacancyPublishModel model = new VacancyPublishModel();
			String requisitionCode = request.getParameter("requisitionCode");
			String vacancyCode = request.getParameter("vacancyCode");
			String vacancyPublishCodeItr = request
					.getParameter("vacancyPublishCodeItr");
			String vacancyDetailCodeItr = request
					.getParameter("vacancyDetailCodeItr");
			model.initiate(context, session);
			boolean result = model.updateVacancyStatus(vacanPublish, request,
					requisitionCode, vacancyCode, vacancyPublishCodeItr,
					vacancyDetailCodeItr);
			if (result) {
				addActionMessage("Vacancy canceled successfully.");
			} else {
				addActionMessage("Unable to canceled this vacancy.");
			}
			model.terminate();
			// viewCancelVacanciesList();
			model.viewCancelVacanciesList(vacanPublish, request,
					requisitionCode, vacancyPublishCodeItr,
					vacancyDetailCodeItr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewcancelVacanciesList";
	}

	/**
	 * this method is called from the Vacancy Management.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewPublish() throws Exception {
		try {
			VacancyPublishModel model = new VacancyPublishModel();
			model.initiate(context, session);
			vacanPublish.setF9Flag("false");
			vacanPublish.setHeaderView("false");
			vacanPublish.setCancelFlag("true");
			model.viewPublish(vacanPublish, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String rePublish() throws Exception {
		try {
			VacancyPublishModel model = new VacancyPublishModel();
			model.initiate(context, session);
			vacanPublish.setHeaderView("false");
			vacanPublish.setCancelFlag("true");
			vacanPublish.setFlag("true");
			vacanPublish.setRequiredDate(request.getParameter("requiredDate"));
			model.rePublish(vacanPublish, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String publish() throws Exception {
		try {
			VacancyPublishModel model = new VacancyPublishModel();
			model.initiate(context, session);
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String publishDate = formater.format(date);
			vacanPublish.setRequiredDate(request.getParameter("requiredDate"));
			String message = model.save(vacanPublish, request, publishDate);
			if (message.equals("1")) {
				addActionMessage("Vacancy republished successfully");
			} else {
				addActionMessage("Vacancy published successfully");
			}
			vacanPublish.setFlag("true");
			getRecList();
			getConsList();
			vacanPublish.setAfterSaveView("true");
			viewModePublish();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewPublish";
	}

	public void sendApplicationAlert(String applnID, String module,
			String applicant, String approver) {
		try {
			String msgType = "I";
			String level = "1";
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("VACANCY PUBLISH FOR RECRUITER");

			template.getTemplateQueries();
			
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
			templateQuery1.setParameter(1, applicant);

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
			templateQuery2.setParameter(1, approver);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applnID);
			templateQuery3.setParameter(2, approver);

			template.configMailAlert();
			template.sendProcessManagerAlert(approver, module, msgType,
					applnID, level);
			try {
				template.sendApplicationMail();
			} catch (Exception e) {
				e.printStackTrace();
			}
			template.clearParameters();
			template.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getRecList() throws Exception {
		ArrayList<Object> recruiterList = new ArrayList<Object>();
		String name[] = null;
		String code[] = null;
		String[] exp = request.getParameterValues("skillExp");

		try {
			if (exp != null) {
				name = new String[exp.length];
				code = new String[exp.length];
				int j = 1;
				try {
					for (int i = 0; i < exp.length; i++) {
						VacancyPublish bean1 = new VacancyPublish();
						name[i] = (String) request
								.getParameter("paraFrm_skillName" + j);
						code[i] = (String) request
								.getParameter("paraFrm_skillId" + j);
						request.setAttribute("paraFrm_skillName" + j, name[i]);
						request.setAttribute("paraFrm_skillId" + j, code[i]);
						bean1.setSkillExp(exp[i]);
						recruiterList.add(bean1);
						j++;
					}
				} catch (Exception e) {
					logger.error("exception in recruiter list for loop", e);
				}
				vacanPublish.setRecruiterList(recruiterList);
				vacanPublish.setRecruiterListFlag("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getConsList() throws Exception {
		ArrayList<Object> consultantList = new ArrayList<Object>();
		String consultName[] = null;
		String consultCode[] = null;
		String city[] = null;
		String cityName[] = null;
		String phoneNum[] = null;
		String email[] = null;
		String[] totalRows = request.getParameterValues("consultantVacancies");
		try {
			if (totalRows != null) {
				consultName = new String[totalRows.length];
				consultCode = new String[totalRows.length];
				city = new String[totalRows.length];
				phoneNum = new String[totalRows.length];
				email = new String[totalRows.length];
				cityName = new String[totalRows.length];
				int c = 1;
				for (int i = 0; i < totalRows.length; i++) {
					VacancyPublish consultBean = new VacancyPublish();
					consultName[i] = (String) request
							.getParameter("paraFrm_consultantName" + c);
					consultCode[i] = (String) request
							.getParameter("paraFrm_consultantId" + c);
					city[i] = (String) request.getParameter("paraFrm_city" + c);
					cityName[i] = (String) request
							.getParameter("paraFrm_cityName" + c);
					phoneNum[i] = (String) request
							.getParameter("paraFrm_phoneNo" + c);
					email[i] = (String) request.getParameter("paraFrm_emailAdd"
							+ c);
					request.setAttribute("paraFrm_consultantName" + c,
							consultName[i]);
					request.setAttribute("paraFrm_consultantId" + c,
							consultCode[i]);
					request.setAttribute("paraFrm_city" + c, city[i]);
					request.setAttribute("paraFrm_cityName" + c, cityName[i]);
					request.setAttribute("paraFrm_phoneNo" + c, phoneNum[i]);
					request.setAttribute("paraFrm_emailAdd" + c, email[i]);
					consultBean.setConsultantVacancies(totalRows[i]);
					consultantList.add(consultBean);
					c++;
				}
				vacanPublish.setConsultantList(consultantList);
				vacanPublish.setConsultantListFlag("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * this method is to display all the recruiter name
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9Recruiter() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS = 'S' ORDER BY UPPER(EMP_FNAME||' '||EMP_LNAME)  ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("Recruiter.Name") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "skillName" + vacanPublish.getRowId(),
				"skillId" + vacanPublish.getRowId() };

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

	/**
	 * this method is to display all the consultant name
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9Consultant() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT NVL(REC_PARTNERNAME,''),LOCATION_NAME,REC_CODE,NVL(REC_PHONENO,0),NVL(REC_EMAIL,' '),LOCATION_NAME,REC_CODE  FROM HRMS_REC_PARTNER "
				+ " left join hrms_location on (hrms_location.LOCATION_CODE = HRMS_REC_PARTNER.REC_CITY) WHERE REC_TYPE='C' AND REC_STATUS = 'A'";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("Consultant.Name"), getMessage("City") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "50", "50" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "consultantName" + vacanPublish.getRowId(),
				"cityName" + vacanPublish.getRowId(),
				"consultantId" + vacanPublish.getRowId(),
				"phoneNo" + vacanPublish.getRowId(),
				"emailAdd" + vacanPublish.getRowId(),
				"city" + vacanPublish.getRowId() };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };

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

	public String checkRecType() throws Exception {
		try {
			VacancyPublishModel model = new VacancyPublishModel();
			model.initiate(context, session);
			model.checkRecType(vacanPublish, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * this method is to display all the consultant name
	 * 
	 * @return
	 * @throws Exception
	 */
	public String f9Publish() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = "SELECT  REQS_NAME,RANK_NAME, A1.EMP_FNAME||' '||A1.EMP_LNAME,NVL(TO_CHAR(REQS_DATE,'DD-MM-YYYY'),''),HRMS_REC_REQS_HDR.REQS_CODE, "
				+ " B1.EMP_FNAME||' '||B1.EMP_LNAME,VACAN_NUMBERS,NVL(TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),' '),"
				+ " VACAN_DTL_CODE,REQS_HIRING_MANAGER,REQS_APPLIED_BY,DECODE(REQS_RECTYPE_INT,'N','false','Y','true'), "
				+ " DECODE(REQS_RECTYPE_EXT,'N','false','Y','true') FROM HRMS_REC_REQS_HDR  "
				+ " INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
				+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION)  "
				+ " INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER) "
				+ " INNER JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY) "
				+ " WHERE REQS_STATUS='O' AND REQS_APPROVAL_STATUS IN ('A','Q') AND VACAN_STATUS='O' "
				+ "  order by REQS_DATE desc ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("reqname"), getMessage("position"),
				getMessage("hiring.mgr"), getMessage("reqs.date") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "15", "25", "10" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "reqName", "position", "hiringMgr", "reqDate",
				"reqCode", "appliedBy", "totalVacancy", "requiredDate",
				"vacanDtlCode", "hiringEmpId", "appEmpId", "intEmployee" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "VacancyPublish_checkRecType.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9MultiDivision() {

		try {
			System.out.println("UserDivision---------"
					+ vacanPublish.getUserProfileDivision());
			String query = " SELECT DISTINCT DIV_ID, DIV_NAME FROM HRMS_DIVISION ";

			if (this.vacanPublish.getUserProfileDivision() != null
					&& this.vacanPublish.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN ("
						+ this.vacanPublish.getUserProfileDivision() + ")";
			query += " ORDER BY DIV_ID ";

			String header = getMessage("division");
			String textAreaId = "paraFrm_divsion";

			String hiddenFieldId = "paraFrm_divCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in VacancyPublishAction.f9MultiDivision() method of Action : "
							+ e.getMessage());
			return "";
		}
	}

}
