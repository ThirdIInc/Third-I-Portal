/**
 * 
 */
package org.paradyne.model.recruitment;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.VacancyPublish;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.email.HtmlEmail;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;

/**
 * @author varunk
 * 
 */
public class VacancyPublishModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(VacancyPublishModel.class);

	String[][] fromMailId = null;
	int mailCount = 0;
	HtmlEmail email = null;

	public void delRecruiter(VacancyPublish req, HttpServletRequest request,
			String[] sn, String[] name, String[] code, String[] exp,
			String[] sdel) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		try {
			req.setRecruiterListFlag("true");
			if (exp != null) {
				int m = 1;
				for (int i = 0; i < exp.length; i++) {
					VacancyPublish bean1 = new VacancyPublish();
					if (!(String.valueOf(sdel[i]).equals("Y"))) {
						// bean1.setSkillType(stype[i]);
						request.setAttribute("paraFrm_skillName" + m, name[i]);
						request.setAttribute("paraFrm_skillId" + m, code[i]);
						bean1.setSkillExp(exp[i]);
						// bean1.setSkillSel(sel[i]);
						tableList.add(bean1);
						m++;
					} else {
						System.out.println("val of in else" + i);
					}
				}
			}
			req.setRecruiterList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delConsultant(VacancyPublish vacanPublish,
			HttpServletRequest request, String[] sn, String[] name,
			String[] code, String[] city, String[] phoneNo, String[] emailAdd,
			String[] totalRows, String[] sdel, String[] cityName) {

		ArrayList<Object> tableList = new ArrayList<Object>();
		try {
			vacanPublish.setConsultantListFlag("true");
			if (totalRows != null) {
				int m = 1;
				for (int i = 0; i < totalRows.length; i++) {
					VacancyPublish bean1 = new VacancyPublish();
					if (!(String.valueOf(sdel[i]).equals("Y"))) {
						request.setAttribute("paraFrm_consultantName" + m,
								name[i]);
						request.setAttribute("paraFrm_consultantId" + m,
								code[i]);
						request.setAttribute("paraFrm_city" + m, city[i]);
						request.setAttribute("paraFrm_cityName" + m,
								cityName[i]);
						request.setAttribute("paraFrm_phoneNo" + m, phoneNo[i]);
						request.setAttribute("paraFrm_emailAdd" + m,
								emailAdd[i]);

						bean1.setConsultantVacancies(totalRows[i]);
						tableList.add(bean1);
						m++;
					} else {
						System.out.println("val of in else" + i);
					}
				}
			}
			vacanPublish.setConsultantList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end of method delConsultant

	/**
	 * this method is called from the vacancy management when Publish button is
	 * clicked.
	 * 
	 * @param vacanPublish
	 * @param request
	 */
	public void viewPublish(VacancyPublish vacanPublish,
			HttpServletRequest request) {
		try {
			vacanPublish.setReqCode(request.getParameter("code"));
			vacanPublish.setPosition(request.getParameter("position"));
			vacanPublish.setAppliedBy(request.getParameter("appliedBy"));
			vacanPublish.setHiringMgr(request.getParameter("hiringMgr"));
			vacanPublish.setReqDate(request.getParameter("requiDate"));
			vacanPublish.setTotalVacancy(request.getParameter("noOfVacan"));
			vacanPublish.setRequiredDate(request.getParameter("reqDate"));
			vacanPublish.setVacanDtlCode(request.getParameter("vacanDtlCode"));
			vacanPublish.setReqName(request.getParameter("reqName"));
			vacanPublish.setAppEmpId(request.getParameter("appEmpId"));
			vacanPublish.setHiringEmpId(request.getParameter("hiringEmpId"));
			vacanPublish.setReqDt(request.getParameter("requiDate"));
			vacanPublish.setNoVac(request.getParameter("noOfVacan"));
			vacanPublish.setRecruitmentInternal(request
					.getParameter("recInternal"));
			if (vacanPublish.getRecruitmentInternal().equals("Yes")) {
				vacanPublish.setIntEmployee("true");
			}

			vacanPublish.setRecruitmentExternal(request
					.getParameter("recExternal"));
			if (vacanPublish.getRecruitmentExternal().equals("Yes")) {
				vacanPublish.setExtEmployee("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// end of method viewPublish

	/**
	 * this method is used to save the publish record in HDR,recruiter and
	 * consultant list tables.
	 * 
	 * @param vacanPublish
	 * @param request
	 * @param publishDate
	 */
	public String save(VacancyPublish bean, HttpServletRequest request,
			String publishDate) {
		String message = "";
		try {
			Object[][] checkData = null;
			/**
			 * TO CHECK WHETHER THE DATA IS ALREADY IN THE TABLE OR NOT, IF YES
			 * THEN WE NEED TO DELETE AND THEN INSERT
			 */
			String query = "SELECT PUB_CODE FROM HRMS_REC_VACPUB_HDR WHERE PUB_VACAN_DTLCODE="
					+ bean.getVacanDtlCode() + "";
		
			checkData = getSqlModel().getSingleResult(query);
			if (checkData != null && checkData.length > 0) {
				String delConsList = "DELETE FROM  HRMS_REC_VACPUB_CONSDTL WHERE PUB_CODE = "
						+ checkData[0][0] + "";
				boolean delConsultant = getSqlModel()
						.singleExecute(delConsList);

				String delRecruit = "DELETE FROM HRMS_REC_VACPUB_RECDTL WHERE PUB_CODE ="
						+ checkData[0][0] + "";
				boolean delRecruiter = getSqlModel().singleExecute(delRecruit);

				String delHdr = "DELETE FROM HRMS_REC_VACPUB_HDR WHERE PUB_VACAN_DTLCODE="
						+ bean.getVacanDtlCode() + "";
				boolean delHeader = getSqlModel().singleExecute(delHdr);
				
				
				String selectStatsDataQuery = "SELECT VAC_CODE, VAC_REQS_CODE, VAC_PUBLISH_CODE FROM HRMS_REC_VACANCIES_STATS WHERE VAC_PUBLISH_CODE="+checkData[0][0];	
				Object[][] selectStatsDataObj = getSqlModel().getSingleResult(selectStatsDataQuery);
				if(selectStatsDataObj != null && selectStatsDataObj.length>0) {
					String delVacanciesStatsQuery = "DELETE FROM HRMS_REC_VACANCIES_STATS WHERE VAC_PUBLISH_CODE ="+checkData[0][0];
					boolean delVacanciesResult = getSqlModel().singleExecute(delVacanciesStatsQuery);
				}
				message = "1";
			}
			
			Object[][] pubMaxCode = null;
			String pubCode = "SELECT NVL(MAX(PUB_CODE),0)+1 FROM HRMS_REC_VACPUB_HDR";
			pubMaxCode = getSqlModel().getSingleResult(pubCode);
			
			String totalNumberOfVacancy =request.getParameter("totalVacancy");
			Object[][] vacancyObj = null;
			String insertVacancyDtlQuery = "";
			String maxCodeQuery = "SELECT NVL(MAX(VAC_CODE),0)+1 FROM HRMS_REC_VACANCIES_STATS";
			Object[][] maxCodeQueryObj = getSqlModel().getSingleResult(maxCodeQuery);
			int count=0;
			if(maxCodeQueryObj!=null && maxCodeQueryObj.length>0) {
				count =Integer.parseInt(String.valueOf(maxCodeQueryObj[0][0]));
			}
			vacancyObj =new Object[Integer.parseInt(totalNumberOfVacancy)][6];
			for (int i = 0; i < vacancyObj.length; i++) {
				vacancyObj[i][0] = count++; 
				vacancyObj[i][1] = checkNull(String.valueOf(bean.getReqCode()));
				vacancyObj[i][2] = checkNull(String.valueOf("O"));
				vacancyObj[i][3] = checkNull(String.valueOf("N"));
				vacancyObj[i][4] = checkNull(String.valueOf("N"));
				vacancyObj[i][5] = checkNull(String.valueOf(pubMaxCode[0][0]));
				
				insertVacancyDtlQuery = "INSERT INTO HRMS_REC_VACANCIES_STATS (VAC_CODE, VAC_REQS_CODE, VAC_STATUS, VAC_OFFER_GIVEN, VAC_APPOINT_GIVEN, VAC_PUBLISH_CODE) "
											+ " VALUES(?,?,?,?,?,?)";
			}
			getSqlModel().singleExecute(insertVacancyDtlQuery, vacancyObj);
			String internalEmp = "N";
			String externalEmp = "N";
			String copWeb = "N";
			String jobCons = "N";
			String refEmployee = "N";
			String onlinejobport = "N";
			if (String.valueOf(bean.getIntEmployee()).equals("true")) {
				internalEmp = "Y";
			}
			if (String.valueOf(bean.getExtEmployee()).equals("true")) {
				externalEmp = "Y";
			}
			if (String.valueOf(bean.getCopWeb()).equals("true")) {
				copWeb = "Y";
			}
			if (String.valueOf(bean.getJobCons()).equals("true")) {
				jobCons = "Y";
			}			
			if (String.valueOf(bean.getOnlinejobPort()).equals("true")) {
				onlinejobport = "Y";
			}
			if (String.valueOf(bean.getRefProgram()).equals("true")) {
				refEmployee = "Y";
			}
			else{
				bean.setDivCode("");
				bean.setDivsion("");
			}
			Object[][] data = new Object[1][13];
			data[0][0] = bean.getReqCode();
			data[0][1] = bean.getVacanDtlCode();
			data[0][2] = "P";
			data[0][3] = publishDate;
			data[0][4] = jobCons;
			data[0][5] = refEmployee;
			data[0][6] = copWeb;
			data[0][7] = bean.getComments().trim();
			data[0][8] = bean.getUserEmpId();
			data[0][9] = internalEmp;
			data[0][10] = externalEmp;
			data[0][11] = onlinejobport;
			data[0][12]=  bean.getDivCode();
			boolean result = false;
			
			String hdrQuery = " INSERT INTO HRMS_REC_VACPUB_HDR (PUB_CODE,PUB_REQS_CODE,PUB_VACAN_DTLCODE,PUB_STATUS,PUB_DATE,PUB_TO_CONS,PUB_TO_REF, "
					+ " PUB_TO_WEB,PUB_TO_COMMENTS,PUB_EMPID,PUB_REC_TYPE_INT,PUB_REC_TYPE_EXT,PUB_TO_CANDJOB, PUB_REF_DIV) VALUES ("
					+ pubMaxCode[0][0]
					+ ", "
					+ " ?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?)";
			result = getSqlModel().singleExecute(hdrQuery, data);
			if (result) {
				String updReqVacDtl = "UPDATE HRMS_REC_REQS_VACDTL SET VACAN_STATUS='P' WHERE VACAN_DTL_CODE = "
						+ bean.getVacanDtlCode() + "";
				boolean update = getSqlModel().singleExecute(updReqVacDtl);

				String[] exp = request.getParameterValues("skillExp");
				int j = 1;
				if (exp != null && exp.length > 0) {
					for (int i = 0; i < exp.length; i++) {
						String recruiterQuery = "INSERT INTO HRMS_REC_VACPUB_RECDTL (PUB_DTL_CODE,PUB_CODE,PUB_REC_EMPID,PUB_ASG_VAC) "
								+ " VALUES ((SELECT NVL(MAX(PUB_DTL_CODE),0)+1 FROM HRMS_REC_VACPUB_RECDTL),"
								+ pubMaxCode[0][0]
								+ ","
								+ (String) request
										.getParameter("paraFrm_skillId" + j)
								+ "," + exp[i].trim() + ")";
						boolean resultRec = getSqlModel().singleExecute(
								recruiterQuery);

						j++;
					}
				}
			}
			
			//If Consultant check-box is true then only enter into this block
			if(String.valueOf(bean.getJobCons()).equals("true")) {
				if (result) {
					String[] totalConsult = request.getParameterValues("consultantVacancies");
					int c = 1;

					for (int i = 0; i < totalConsult.length; i++) {

						String consultQuery = "INSERT INTO HRMS_REC_VACPUB_CONSDTL (PUB_DTL_CODE,PUB_CODE,PUB_CONS_ID,PUB_ASG_VAC) "
								+ " VALUES ((SELECT NVL(MAX(PUB_DTL_CODE),0)+1 FROM HRMS_REC_VACPUB_CONSDTL),"
								+ pubMaxCode[0][0] + "," + (String) request.getParameter("paraFrm_consultantId" + c)
								+ "," + totalConsult[i] + ")";
						boolean resultRec = getSqlModel().singleExecute(
								consultQuery);
						c++;
					}

				}
			}
			
			// =========== for mail to recruiter ==============

			int j = 1;
			String[] skillExp = request.getParameterValues("skillExp");
			if (skillExp != null && skillExp.length > 0) {
				for (int i = 0; i < skillExp.length; i++) {
					String rectruiter = (String) request
							.getParameter("paraFrm_skillId" + j);
					j++;
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template
							.setEmailTemplate("RMS-VACANCY PUBLISH MAIL TO RECRUITER");
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);
					templateQuery1.setParameter(1, bean.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);
					templateQuery2.setParameter(1, rectruiter);

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, rectruiter);

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, bean.getUserEmpId());

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, bean.getReqCode());

					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, bean.getReqCode());
					templateQuery6.setParameter(2, rectruiter);
					templateQuery6.setParameter(3, String
							.valueOf(pubMaxCode[0][0]));
					templateQuery6.setParameter(4, bean.getVacanDtlCode());
					template.configMailAlert();
					template.sendApplicationMail();
					template.clearParameters();
				}

				try {
					if (bean.getJobCons().equals("true")) {
						String consultantDataQuery = "SELECT REC_CODE, REC_PARTNERNAME, TRIM(REC_EMAIL) FROM HRMS_REC_VACPUB_CONSDTL "
								+ " INNER JOIN HRMS_REC_PARTNER ON (HRMS_REC_PARTNER.REC_CODE = HRMS_REC_VACPUB_CONSDTL.PUB_CONS_ID) "
								+ " WHERE  PUB_CODE= " + pubMaxCode[0][0];
						Object[][] consultantObject = getSqlModel()
								.getSingleResult(consultantDataQuery);
						if (consultantObject != null
								&& consultantObject.length > 0) {
							for (int i = 0; i < consultantObject.length; i++) {
								EmailTemplateBody template = new EmailTemplateBody();
								template.initiate(context, session);
								template
										.setEmailTemplate("RMS-VACANCY PUBLISH MAIL TO CONSULTANT");
								template.getTemplateQueries();

								EmailTemplateQuery templateQuery1 = template
										.getTemplateQuery(1);
								templateQuery1.setParameter(1, bean
										.getUserEmpId());

								EmailTemplateQuery templateQuery2 = template
										.getTemplateQuery(2);
								templateQuery2.setParameter(1, String
										.valueOf(consultantObject[i][0]));

								EmailTemplateQuery templateQuery3 = template
										.getTemplateQuery(3);
								templateQuery3.setParameter(1, String
										.valueOf(consultantObject[i][0]));

								EmailTemplateQuery templateQuery4 = template
										.getTemplateQuery(4);
								templateQuery4.setParameter(1, bean
										.getUserEmpId());

								EmailTemplateQuery templateQuery5 = template
										.getTemplateQuery(5);
								templateQuery5.setParameter(1, bean
										.getReqCode());

								EmailTemplateQuery templateQuery6 = template
										.getTemplateQuery(6);
								templateQuery6.setParameter(1, bean
										.getReqCode());
								templateQuery6.setParameter(2, String
										.valueOf(pubMaxCode[0][0]));
								templateQuery6.setParameter(3, bean
										.getVacanDtlCode());
								templateQuery6.setParameter(4, String
										.valueOf(consultantObject[i][0]));

								template.configMailAlert();
								template.sendApplicationMail();
								template.clearParameters();
							}
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			// =============referral mail to internal employees
			if (refEmployee.equals("Y")) {
				sendMailToEmployees(bean, true);
			}else {
				sendMailToEmployees(bean, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	public void viewModePublish(VacancyPublish vacanPublish,
			HttpServletRequest request) {
		try {
			vacanPublish.setReqCode(request.getParameter("code"));
			vacanPublish
					.setPosition(checkNull(request.getParameter("position")));
			vacanPublish.setAppliedBy(request.getParameter("appliedBy"));
			vacanPublish.setHiringMgr(request.getParameter("hiringMgr"));
			if (String.valueOf(vacanPublish.getAfterSaveView()).equals("false")) {
				vacanPublish.setReqDate(checkNull(request
						.getParameter("reqDate")));
				vacanPublish.setTotalVacancy(checkNull(request
						.getParameter("noOfVacan")));
			} else {
				vacanPublish.setReqDate(checkNull(vacanPublish.getReqDt()));
				vacanPublish
						.setTotalVacancy(checkNull(vacanPublish.getNoVac()));
			}
			vacanPublish.setReqDate(checkNull(request.getParameter("reqDate")));
			vacanPublish.setRequiredDate(checkNull(vacanPublish
					.getRequiredDate()));
			vacanPublish.setVacanDtlCode(request.getParameter("vacanDtlCode"));
			vacanPublish.setReqName(request.getParameter("reqName"));
			vacanPublish.setAppEmpId(request.getParameter("appEmpId"));
			vacanPublish.setHiringEmpId(request.getParameter("hiringEmpId"));
			Object[][] viewModeData = null;
			String query = "SELECT PUB_TO_CONS,PUB_TO_REF,PUB_TO_WEB,PUB_REC_TYPE_INT,"
					+ " PUB_REC_TYPE_EXT,PUB_TO_COMMENTS,PUB_CODE,PUB_TO_CANDJOB "
					+ " FROM HRMS_REC_VACPUB_HDR WHERE PUB_VACAN_DTLCODE="
					+ vacanPublish.getVacanDtlCode() + "";
			viewModeData = getSqlModel().getSingleResult(query);
			if (viewModeData != null && viewModeData.length > 0) {
				if (String.valueOf(viewModeData[0][2]).equals("Y")) {
					vacanPublish.setCopWeb("true");
				}
				if (String.valueOf(viewModeData[0][1]).equals("Y")) {
					vacanPublish.setRefProgram("true");
				}
				if (String.valueOf(viewModeData[0][0]).equals("Y")) {
					vacanPublish.setJobCons("true");
				}
				if (String.valueOf(viewModeData[0][3]).equals("Y")) {
					vacanPublish.setIntEmployee("true");
				}
				if (String.valueOf(viewModeData[0][4]).equals("Y")) {
					vacanPublish.setExtEmployee("true");
				}
				vacanPublish.setComments(checkNull(String
						.valueOf(viewModeData[0][5])));
				if (String.valueOf(viewModeData[0][7]).equals("Y")) {
					vacanPublish.setOnlinejobPort("true");// new field Added
				}		
				getDivisionName(vacanPublish);
				Object[][] recListData = null;
				String recQuery = "SELECT B1.EMP_FNAME||' '||B1.EMP_LNAME,PUB_ASG_VAC,PUB_REC_EMPID "
						+ " FROM HRMS_REC_VACPUB_RECDTL "
						+ " INNER JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID = HRMS_REC_VACPUB_RECDTL.PUB_REC_EMPID) "
						+ " WHERE PUB_CODE = "
						+ viewModeData[0][6]
						+ " ORDER BY UPPER(EMP_FNAME||' '||EMP_LNAME)";
				recListData = getSqlModel().getSingleResult(recQuery);

				ArrayList<Object> recList = new ArrayList<Object>();
				if (recListData != null) {
					for (int i = 0; i < recListData.length; i++) {
						VacancyPublish bean = new VacancyPublish();
						bean.setSkillName(String.valueOf(recListData[i][0]));
						bean.setSkillExp(String.valueOf(recListData[i][1]));
						bean.setSkillId(String.valueOf(recListData[i][2]));
						recList.add(bean);
					}
				}
				vacanPublish.setRecruiterList(recList);

				Object[][] consListData = null;

				String consQuery = "SELECT REC_PARTNERNAME,PUB_ASG_VAC,PUB_CONS_ID,REC_CITY,REC_PHONENO,REC_EMAIL,LOCATION_NAME   "
						+ " FROM HRMS_REC_VACPUB_CONSDTL "
						+ " INNER JOIN HRMS_REC_PARTNER ON (HRMS_REC_PARTNER.REC_CODE = HRMS_REC_VACPUB_CONSDTL.PUB_CONS_ID) "
						+ "    left join hrms_location on (hrms_location.LOCATION_CODE = HRMS_REC_PARTNER.REC_CITY)"
						+ " WHERE PUB_CODE = " + viewModeData[0][6] + " ";
				consListData = getSqlModel().getSingleResult(consQuery);

				ArrayList<Object> consList = new ArrayList<Object>();

				if (consList != null) {
					for (int i = 0; i < consListData.length; i++) {
						VacancyPublish bean1 = new VacancyPublish();
						bean1.setConsultantName(String
								.valueOf(consListData[i][0]));
						bean1.setConsultantVacancies(String
								.valueOf(consListData[i][1]));
						bean1.setCity(checkNull(String
								.valueOf(consListData[i][3])));
						bean1.setPhoneNo(checkNull(String
								.valueOf(consListData[i][4])));
						bean1.setEmailAdd(checkNull(String
								.valueOf(consListData[i][5])));
						bean1.setCityName(checkNull(String
								.valueOf(consListData[i][6])));
						consList.add(bean1);
					}
				}
				vacanPublish.setConsultantList(consList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals(" ") || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void sendMailToEmployees(VacancyPublish bean, boolean isEmloyeeReferal) {
		try {
			final String[][] fromMailIds = getDefaultMailIds();
			String templateDataQuery = " SELECT TEMPLATE_DEFAULT_BODY,TEMPLATE_ID FROM HRMS_EMAILTEMPLATE_HDR WHERE TEMPLATE_NAME= 'REFERRAL MAIL TO THE INTERNAL EMPLOYEES' ";
			Object[][] companyDtls = getSqlModel().getSingleResult(
					"SELECT COMPANY_NAME FROM HRMS_COMPANY");
			String toEmpQry = " SELECT DISTINCT(ADD_EMAIL), HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_DIV FROM HRMS_EMP_ADDRESS" 
							  +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_ADDRESS.EMP_ID)"
							  +" WHERE HRMS_EMP_OFFC .EMP_STATUS='S' AND ADD_TYPE='P'" 
							  +" AND ADD_EMAIL IS NOT NULL ";
							  
			if(isEmloyeeReferal){
				toEmpQry= toEmpQry+	"AND EMP_DIV IN( "+ bean.getDivCode()+" )";// AND
			}
							  		
			// HRMS_EMP_OFFC.EMP_ID=10";
			Object[][] toEmpObj = getSqlModel().getSingleResult(toEmpQry);
			Object[][] templateObj = getSqlModel().getSingleResult(
					templateDataQuery);
			final String[] toEmpAddArr = new String[toEmpObj.length];
			if (toEmpObj != null && toEmpObj.length > 0) {
				for (int j = 0; j < toEmpObj.length; j++) {
					toEmpAddArr[j] = String.valueOf(toEmpObj[j][0]);
				}
			}
			String reqDetailsQry = "SELECT DISTINCT REQS_JOB_DESCRIPTION,REQS_ROLE_RESPON , CASE WHEN REQS_VACAN_EXPMIN IS NULL AND REQS_VACAN_EXPMAX IS NULL THEN ' ' ELSE REQS_VACAN_EXPMIN||' to '||REQS_VACAN_EXPMAX||' Years' END FROM HRMS_REC_REQS_HDR"
					+ " WHERE REQS_CODE=" + bean.getReqCode();
			Object reqDetails[][] = getSqlModel()
					.getSingleResult(reqDetailsQry);
			String skillDetailsQry = "SELECT SKILL_NAME FROM HRMS_REC_REQS_SKILL_FUNC_DTL"
					+ " INNER JOIN HRMS_SKILL ON(SKILL_ID=REQS_SKILL_FUNC_CODE AND REQS_FIELD_TYPE ='S')"
					+ " WHERE REQS_CODE=" + bean.getReqCode();
			Object skillDetails[][] = getSqlModel().getSingleResult(
					skillDetailsQry);
			String skillString = "";
			if (skillDetails != null || skillDetails.length > 0) {
				for (int i = 0; i < skillDetails.length; i++) {
					if (i != skillDetails.length - 1) {
						skillString += String.valueOf(skillDetails[i][0]) + ",";
					} else {
						skillString += String.valueOf(skillDetails[i][0]);
					}
				}
			}

			/*
			 * String getMailBox = " SELECT MAILBOX_SERVER, MAILBOX_PROTOCOL,
			 * MAILBOX_PORT, MAILBOX_USERID,
			 * MAILBOX_PASSW,MAILBOX_AUTH_CHK,MAILBOX_FLAG FROM " + "
			 * HRMS_SETTINGS_MAILBOX WHERE MAILBOX_FLAG='O' "; Object[][]
			 * mailBoxData = getSqlModel().getSingleResult(getMailBox);
			 */
			final Object[][] mailBoxData = getServerMailBox("",
					fromMailIds[0][0]);
			String messageText = String.valueOf(templateObj[0][0]);
			messageText = messageText.replace("&lt;#REQ_DATE#&gt;", bean
					.getReqDate());
			messageText = messageText.replace("&lt;#POSITION#&gt;", bean
					.getPosition());
			messageText = messageText.replace("&lt;#JOB_DESC#&gt;",
					checkNull(String.valueOf(reqDetails[0][0])));
			messageText = messageText.replace("&lt;#JOB_ROLES#&gt;",
					checkNull(String.valueOf(reqDetails[0][1])));
			messageText = messageText.replace("&lt;#SKILLS_REQ#&gt;",
					skillString);
			messageText = messageText.replace("&lt;#EXPERIENCE#&gt;",
					checkNull(String.valueOf(reqDetails[0][2])));
			messageText = messageText.replace("&lt;#VACANCIES#&gt;", bean
					.getTotalVacancy());
			messageText = messageText.replace("&lt;#HIRE_MANAGER#&gt;",
					checkNull(bean.getHiringMgr()));
			messageText = messageText.replace("&lt;#COMPANY_NAME#&gt;",
					checkNull(String.valueOf(companyDtls[0][0])));

			final String finalmessageText = messageText;
			final String position = bean.getPosition();
			
		//Send to Recruitment head START	
			final String checkForRecHeadQuery = "SELECT HRMS_REC_CONF.CONF_REC_HEAD FROM HRMS_REC_CONF";
			final Object[][] checkForRecHeadObj = this.getSqlModel().getSingleResult(checkForRecHeadQuery);
			String recruitmentHeadID = "";
			if (checkForRecHeadObj != null && checkForRecHeadObj.length > 0) {
				recruitmentHeadID = org.paradyne.lib.Utility.checkNull(String.valueOf(checkForRecHeadObj[0][0]));
				this.sendMailToRecruitmentHead(bean, recruitmentHeadID);
			}
		//Send to Recruitment head ENDS	
			
			if(isEmloyeeReferal){
				new Thread(new Runnable() {
					public void run() {
						sendMail(toEmpAddArr, fromMailIds,
								"Referral mail for position :" + position,
								finalmessageText, mailBoxData);
					}
				}).start();
				
				// CC mail to Recruitment head START
				/*String recruitHeadQuery = "SELECT ADD_EMAIL FROM HRMS_EMP_ADDRESS WHERE EMP_ID = (SELECT HRMS_REC_CONF.CONF_REC_HEAD FROM HRMS_REC_CONF) AND ADD_TYPE='P'";
				Object[][] recruitHeadObj = getSqlModel().getSingleResult(recruitHeadQuery);
					if (recruitHeadObj != null && recruitHeadObj.length > 0) {
						sendMail(new String[]{String.valueOf(recruitHeadObj[0][0])}, fromMailIds,
								"Referral mail for position :" + position, finalmessageText, mailBoxData);
					}*/
				// CC mail to Recruitment head END
				 
			}else {
				// CC mail to Recruitment head START
				/*String recruitHeadQuery = "SELECT ADD_EMAIL FROM HRMS_EMP_ADDRESS WHERE EMP_ID = (SELECT HRMS_REC_CONF.CONF_REC_HEAD FROM HRMS_REC_CONF) AND ADD_TYPE='P'";
				Object[][] recruitHeadObj = getSqlModel().getSingleResult(recruitHeadQuery);
					if (recruitHeadObj != null && recruitHeadObj.length > 0) {
						sendMail(new String[]{String.valueOf(recruitHeadObj[0][0])}, fromMailIds,
								"Referral mail for position :" + position, finalmessageText, mailBoxData);
					}*/
				// CC mail to Recruitment head END
				 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**Method : sendMailToRecruitmentHead.
	 * Purpose : This method is used to send mail to the recruitmentHead
	 * @param bean : bean
	 * @param recruitmentHeadID : recruitmentHeadID
	 */
	private void sendMailToRecruitmentHead(final VacancyPublish bean,
			final String recruitmentHeadID) {
		try {
			final EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("RMS-VACANCY PUBLISH MAIL TO RECRUITMENT HEAD");
			templateApp.getTemplateQueries();
			final EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1);
			templateQueryApp1.setParameter(1, bean.getUserEmpId());

			final EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2);  
			templateQueryApp2.setParameter(1, recruitmentHeadID);

			final EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, bean.getUserEmpId());
			templateQueryApp3.setParameter(2, bean.getReqCode());
			templateQueryApp3.setParameter(3, recruitmentHeadID);
			

			final EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, bean.getReqCode());

			final EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, bean.getReqCode());

			templateApp.configMailAlert();
			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void rePublish(VacancyPublish vacanPublish,
			HttpServletRequest request) {

		try {
			Object[][] hdrData = null;
			String query = "SELECT PUB_REQS_CODE,PUB_CODE,PUB_STATUS,PUB_DATE,TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),VACAN_NUMBERS,REQS_NAME,RANK_NAME,TO_CHAR(REQS_DATE,'DD-MM-YYYY'), "
					+ " B1.EMP_FNAME||' '||B1.EMP_MNAME||' '||B1.EMP_LNAME,A1.EMP_FNAME||' '||A1.EMP_MNAME||' '||A1.EMP_LNAME,B1.EMP_ID,A1.EMP_ID, PUB_REF_DIV "
					+ "  FROM HRMS_REC_VACPUB_HDR "
					+ " INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) "
					+ " INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE) "
					+ "   INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION)  "
					+ "  INNER JOIN HRMS_EMP_OFFC A1 ON (A1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER) "
					+ "   INNER JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY)    "
					+ " WHERE PUB_VACAN_DTLCODE="
					+ vacanPublish.getVacanDtlCode() + "";
			hdrData = getSqlModel().getSingleResult(query);
			vacanPublish.setReqCode(String.valueOf(hdrData[0][0]));
			vacanPublish.setPosition(String.valueOf(hdrData[0][7]));
			vacanPublish.setAppliedBy(String.valueOf(hdrData[0][9]));
			vacanPublish.setHiringMgr(String.valueOf(hdrData[0][10]));
			vacanPublish.setRequiredDate(checkNull(String
					.valueOf(hdrData[0][4])));
			vacanPublish.setReqName(String.valueOf(hdrData[0][6]));
			vacanPublish.setReqDate(String.valueOf(hdrData[0][8]));
			vacanPublish.setAppEmpId(String.valueOf(hdrData[0][11]));
			vacanPublish.setHiringEmpId(String.valueOf(hdrData[0][12]));
			vacanPublish.setTotalVacancy(String.valueOf(hdrData[0][5]));
			vacanPublish.setReqDt(String.valueOf(hdrData[0][8]));
			vacanPublish.setNoVac(String.valueOf(hdrData[0][5]));
			vacanPublish.setDivCode(String.valueOf(hdrData[0][13]));
			getDivisionName(vacanPublish);
			Object[][] viewModeData = null;
			String queryDtl = "SELECT PUB_TO_CONS,PUB_TO_REF,PUB_TO_WEB,PUB_REC_TYPE_INT,PUB_REC_TYPE_EXT,PUB_TO_COMMENTS,PUB_CODE,PUB_TO_CANDJOB "
					+ " FROM HRMS_REC_VACPUB_HDR WHERE PUB_VACAN_DTLCODE="
					+ vacanPublish.getVacanDtlCode() + "";
			viewModeData = getSqlModel().getSingleResult(queryDtl);
			if (viewModeData != null && viewModeData.length > 0) {
				if (String.valueOf(viewModeData[0][2]).equals("Y")) {
					vacanPublish.setCopWeb("true");
				}
				if (String.valueOf(viewModeData[0][1]).equals("Y")) {
					vacanPublish.setRefProgram("true");
				}
				if (String.valueOf(viewModeData[0][0]).equals("Y")) {
					vacanPublish.setJobCons("true");
				}
				if (String.valueOf(viewModeData[0][3]).equals("Y")) {
					vacanPublish.setIntEmployee("true");
				}
				if (String.valueOf(viewModeData[0][4]).equals("Y")) {
					vacanPublish.setExtEmployee("true");
				}
				vacanPublish.setComments(checkNull(String
						.valueOf(viewModeData[0][5])));
				if (String.valueOf(viewModeData[0][7]).equals("Y")) {
					vacanPublish.setOnlinejobPort("true");
				}
			}
			Object[][] recListData = null;
			String recQuery = "SELECT B1.EMP_FNAME||' '||B1.EMP_MNAME||' '||B1.EMP_LNAME,PUB_ASG_VAC,PUB_REC_EMPID "
					+ " FROM HRMS_REC_VACPUB_RECDTL "
					+ " INNER JOIN HRMS_EMP_OFFC B1 ON (B1.EMP_ID = HRMS_REC_VACPUB_RECDTL.PUB_REC_EMPID) "
					+ " WHERE PUB_CODE = "
					+ viewModeData[0][6]
					+ " ORDER BY UPPER(EMP_FNAME||' '||EMP_LNAME)";
			recListData = getSqlModel().getSingleResult(recQuery);
			ArrayList<Object> recruiterList = new ArrayList<Object>();
			int j = 1;
			for (int i = 0; i < recListData.length; i++) {
				VacancyPublish bean1 = new VacancyPublish();
				request.setAttribute("paraFrm_skillName" + j, ""
						+ recListData[i][0]);
				request.setAttribute("paraFrm_skillId" + j, ""
						+ recListData[i][2]);
				bean1.setSkillExp(String.valueOf(recListData[i][1]));
				recruiterList.add(bean1);
				j++;
			}
			vacanPublish.setRecruiterList(recruiterList);
			vacanPublish.setRecruiterListFlag("true");
			Object[][] consListData = null;
			String consQuery = "SELECT REC_PARTNERNAME,PUB_ASG_VAC,PUB_CONS_ID,REC_CITY,REC_PHONENO,REC_EMAIL,LOCATION_NAME  "
					+ " FROM HRMS_REC_VACPUB_CONSDTL "
					+ " INNER JOIN HRMS_REC_PARTNER ON (HRMS_REC_PARTNER.REC_CODE = HRMS_REC_VACPUB_CONSDTL.PUB_CONS_ID) "
					+ "   left join hrms_location on (hrms_location.LOCATION_CODE = HRMS_REC_PARTNER.REC_CITY)"
					+ " WHERE PUB_CODE = " + viewModeData[0][6] + " ";
			consListData = getSqlModel().getSingleResult(consQuery);
			ArrayList<Object> consList = new ArrayList<Object>();
			int c = 1;
			for (int i = 0; i < consListData.length; i++) {
				VacancyPublish consultBean = new VacancyPublish();
				request.setAttribute("paraFrm_consultantName" + c, ""
						+ consListData[i][0]);
				request.setAttribute("paraFrm_consultantId" + c, ""
						+ consListData[i][2]);
				request.setAttribute("paraFrm_city" + c, ""
						+ consListData[i][3]);
				request.setAttribute("paraFrm_phoneNo" + c, checkNull(""
						+ consListData[i][4]));
				request.setAttribute("paraFrm_emailAdd" + c, ""
						+ consListData[i][5]);
				request.setAttribute("paraFrm_cityName" + c, checkNull(""
						+ consListData[i][6]));
				consultBean.setConsultantVacancies(String
						.valueOf(consListData[i][1]));
				consList.add(consultBean);
				c++;
			}
			vacanPublish.setConsultantList(consList);
			vacanPublish.setConsultantListFlag("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkRecType(VacancyPublish vacanPublish,
			HttpServletRequest request) {
		try {
			Object[][] data = null;
			String query = "SELECT DECODE(REQS_RECTYPE_INT,'N','false','Y','true'),DECODE(REQS_RECTYPE_EXT,'N','false','Y','true') "
					+ " FROM HRMS_REC_REQS_HDR   "
					+ " INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "
					+ " WHERE REQS_STATUS='O' AND REQS_APPROVAL_STATUS='A' AND VACAN_STATUS='O' AND HRMS_REC_REQS_HDR.REQS_CODE = "
					+ vacanPublish.getReqCode() + "";
			data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				if (String.valueOf(data[0][0]).equals("true")) {
					vacanPublish.setIntEmployee("true");
				}
				if (String.valueOf(data[0][1]).equals("true")) {
					vacanPublish.setExtEmployee("true");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[][] getDefaultMailIds() {
		String fromQuery = " SELECT SYSMAIL_EMAIL_ID, SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID ORDER BY SYSMAIL_CODE ";
		Object fromEmp[][] = getSqlModel().getSingleResult(fromQuery);
		String[][] mailIds = null;
		if (fromEmp != null && fromEmp.length > 0) {
			mailIds = new String[fromEmp.length][2];
			for (int i = 0; i < fromEmp.length; i++) {
				mailIds[i][0] = String.valueOf(fromEmp[i][0]);
				mailIds[i][1] = String.valueOf(fromEmp[i][1]);
			}
		}
		return mailIds;
	}

	public void sendMail(String[] toMailId, String[][] fromMailIds,
			String subject, String textBody, Object[][] mailBoxData) {
		fromMailId = fromMailIds;
		try {
			int patch = Integer.parseInt(String.valueOf(mailBoxData[0][16]));
			if (patch == 0) {
				patch = 80;
			}

			if (patch > 0) {
				System.out.println("patch" + patch);
				int count = toMailId.length / patch;
				int rem = toMailId.length % patch;
				if (rem > 0) {
					count = count + 1;
				}
				int k = 0;
				if (patch > toMailId.length) {
					patch = toMailId.length;
				}

				for (int i = 0; i < count; i++) {
					String[] tomailIds = null;
					if (i == count - 1) {
						if (rem > 0) {
							tomailIds = new String[rem];
						} else {
							tomailIds = new String[patch];
						}
					} else {
						tomailIds = new String[patch];
					}

					for (int j = 0; j < tomailIds.length; j++) {
						tomailIds[j] = toMailId[k];
						k++;
					}
					fireCounter = 0;
					fireEmail(mailBoxData, subject, textBody, tomailIds);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int fireCounter = 0;

	public void sendMail_OLD(String[] toMailId, String[][] fromMailIds,
			String subject, String textBody, Object[][] mailBoxData) {
		fromMailId = fromMailIds;
		try {
			int patch = 80;
			int count = toMailId.length / patch;
			int rem = toMailId.length % patch;
			if (rem > 0) {
				count = count + 1;
			}
			int k = 0;
			if (patch > toMailId.length) {
				patch = toMailId.length;
			}
			for (int i = 0; i < count; i++) {
				String[] tomailIds = null;
				if (i == count - 1) {
					if (rem > 0) {
						tomailIds = new String[rem];
					} else {
						tomailIds = new String[patch];
					}
				} else {
					tomailIds = new String[patch];
				}
				for (int j = 0; j < tomailIds.length; j++) {
					tomailIds[j] = toMailId[k];
					k++;
				}
				// HtmlEmail email=setHtmlEmail(mailBoxData, subject, textBody,
				// request, tomailIds);
				fireEmail(mailBoxData, subject, textBody, tomailIds);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fireEmail(Object[][] mailBoxData, String subject,
			String textBody, String[] tomailIds) {

		try {

			if (mailCount < fromMailId.length) {
				email = setHtmlEmail(mailBoxData, subject, textBody, tomailIds);
				email.setFrom(fromMailId[mailCount][0], ""
						+ fromMailId[mailCount][0]);
				email.addTo(fromMailId[mailCount][0], ""
						+ fromMailId[mailCount][0]);

				if (String.valueOf(mailBoxData[0][12]).equals("D")) {
					email.setAuthentication(String
							.valueOf(mailBoxData[mailCount][3]), String
							.valueOf(mailBoxData[0][4]));

				} else {
					email.setAuthentication(String
							.valueOf(fromMailId[mailCount][0]), String
							.valueOf(fromMailId[mailCount][1]));

				}
				if (String.valueOf(mailBoxData[0][6]).equals("Y")) {
					try {
						email.setPopBeforeSmtp(true, String
								.valueOf(mailBoxData[0][7]), String
								.valueOf(mailBoxData[0][3]), String
								.valueOf(mailBoxData[0][4]));

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				if (String.valueOf(mailBoxData[0][2]).equals("SMTPTLS")) {
					email.setSmtpWithTLS(true);
					email.setSslSmtpPort(String.valueOf(mailBoxData[0][1]));
				}
				if (String.valueOf(mailBoxData[0][2]).equals("SMTPSSL")) {
					email.setSmtpWithSSL(true);
					email.setSslSmtpPort(String.valueOf(mailBoxData[0][1]));
				}

				String str = email.send();
				List unsentList = email.getUnsentList();

				if (unsentList.size() > 0) {
					fireCounter++;
					String[] toObj = new String[unsentList.size()];
					for (int i = 0; i < unsentList.size(); i++) {
						String[] splitedStr = unsentList.get(i).toString()
								.split("<");
						toObj[i] = splitedStr[1].substring(0, splitedStr[1]
								.length() - 1);

					}

					List invalidList = email.getInvalidList();
					if (fireCounter < 5) {
						fireEmail(mailBoxData, subject, textBody, toObj);
					}

				}
			} else {
				System.out.println("SYSTEM MAIL IDS OVER....");
			}
		} catch (Exception e) {
			mailCount++;
			System.out.println("EXCEPTION________________________________ ");
			e.printStackTrace();
		}

	}

	private void fireEmail_OLD(Object[][] mailBoxData, String subject,
			String textBody, String[] tomailIds) {
		try {
			if (mailCount < fromMailId.length) {
				/*
				 * if(mailCount==0){ email=setHtmlEmail(mailBoxData, subject,
				 * textBody, request, tomailIds); }
				 */
				email = setHtmlEmail(mailBoxData, subject, textBody, tomailIds);
				email.setFrom(fromMailId[mailCount][0], ""
						+ fromMailId[mailCount][0]);
				email.addTo(fromMailId[mailCount][0], ""
						+ fromMailId[mailCount][0]);
				email.setAuthentication(String
						.valueOf(fromMailId[mailCount][0]), String
						.valueOf(fromMailId[mailCount][1]));
				String str = email.send();
				List unsentList = email.getUnsentList();

				if (unsentList.size() > 0) {
					String[] toObj = new String[unsentList.size()];
					for (int i = 0; i < unsentList.size(); i++) {
						String[] splitedStr = unsentList.get(i).toString()
								.split("<");
						toObj[i] = splitedStr[1].substring(0, splitedStr[1]
								.length() - 1);
					}
					fireEmail(mailBoxData, subject, textBody, toObj);
				}

			} else {
				System.out.println("SYSTEM MAIL IDS OVER....");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HtmlEmail setHtmlEmail(Object[][] mailBoxData, String subject,
			String textBody, String[] tomailIds) {
		HtmlEmail email = new HtmlEmail();

		try {
			email.setHostName(String.valueOf(mailBoxData[0][0])); // 64.202.165.58
			email.setSmtpPort(Integer.parseInt(String
					.valueOf(mailBoxData[0][1]))); // 80
			email.setSubject("" + subject);
			email.setHtmlMsg(textBody);
			email
					.setTextMsg("Your email client does not support HTML messages");
			addTo_CC(email, tomailIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}

	public HtmlEmail addTo_CC(HtmlEmail email, String[] toObj) {
		try {
			for (int i = 0; i < toObj.length; i++) {
				email.addBcc(toObj[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}

	/**
	 * 
	 * @param empId
	 * @return
	 */
	public Object[][] getServerMailBox(String empId, String fromMailId) {
		System.out.println("empId  in getServerMailBox   " + empId);
		System.out.println("fromMailId  in getServerMailBox   " + fromMailId);
		String getMailBox = "";
		Object[][] empMailBoxData = null;
		Object[][] sysMailData = null;

		if (empId != null && !empId.equals("")) {// this is for pop before
			// smtp check
			getMailBox = " SELECT  SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE,EMAIL_USER_NAME,EMAIL_USER_PASS, SERVER_AUTH_REQUIRED, "
					+ " SERVER_POP_BEFORE_SMTP,SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,SERVER_CODE,EMAIL_USER_NAME  "
					+ " ,SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD ,SERVER_USESYSTEMMAILID_FLAG " // newly
					// added
					// for
					// logon
					// using
					// same
					// or
					// different
					// Id
					+ " NVL(NUMBER_OF_MAILS_SEND,0) FROM HRMS_EMAIL_SERVER  "
					+ " INNER JOIN HRMS_EMAIL_ACCOUNT ON (HRMS_EMAIL_ACCOUNT.EMAIL_SERVER_CODE=HRMS_EMAIL_SERVER.SERVER_CODE) "
					+ " WHERE EMAIL_EMP_ID="
					+ empId
					+ "  AND EMAIL_OFFICIAL_FLAG='Y'";
			empMailBoxData = getSqlModel().getSingleResult(getMailBox);
			if (empMailBoxData != null && empMailBoxData.length > 0) {
				String sysmailQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE= "
						+ String.valueOf(empMailBoxData[0][10])
						+ " AND ROWNUM=1";
				sysMailData = getSqlModel().getSingleResult(sysmailQuery);
				if (sysMailData != null && sysMailData.length > 0) {
					if (String.valueOf(empMailBoxData[0][15]).equals("Y")) {
						fromMailId = String.valueOf(sysMailData[0][0]);
					}
				}
				if (String.valueOf(empMailBoxData[0][12]).equals("S")) {
					if (empId == null || empId.length() == 0) {
						System.out.println("emp Id NULL :");
						if (sysMailData != null && sysMailData.length > 0) {
							System.out.println("sysMailData not NULL :");
							empMailBoxData[0][3] = sysMailData[0][0];
							empMailBoxData[0][4] = sysMailData[0][1];
							empMailBoxData[0][11] = sysMailData[0][0];
						}
					} else {
						System.out.println("fromMailId   emp Id present:"
								+ fromMailId);
						empMailBoxData[0][3] = sysMailData[0][0];
						empMailBoxData[0][4] = sysMailData[0][1];
						empMailBoxData[0][11] = fromMailId;
					}
				} else {
					empMailBoxData[0][3] = empMailBoxData[0][13];
					empMailBoxData[0][4] = empMailBoxData[0][14];
					empMailBoxData[0][11] = fromMailId;
				}

			}
			System.out.println(" in POP BEFORE SMTP");
		}
		if (empMailBoxData == null || empMailBoxData.length == 0) {

			getMailBox = " SELECT  SERVER_OUT_IP, SERVER_OUT_PORT,SERVER_OUT_TYPE,' ',' ',SERVER_AUTH_REQUIRED, "
					+ " SERVER_POP_BEFORE_SMTP,SERVER_IN_IP, SERVER_IN_PORT, SERVER_IN_TYPE,SERVER_CODE,'' "
					+ " ,SERVER_LOGON_USING, SERVER_LOGON_USERNAME, SERVER_LOGON_PASSWORD,SERVER_USESYSTEMMAILID_FLAG " // newly
					// added
					// for
					// logon
					// using
					// same
					// or
					// different
					// Id
					+ " ,NVL(NUMBER_OF_MAILS_SEND,0) FROM HRMS_EMAIL_SERVER "
					+ " WHERE SERVER_SYSTEMMAIL_FLAG='Y' ";
			empMailBoxData = getSqlModel().getSingleResult(getMailBox);
			System.out.println(" ELSE POP");
			if (empMailBoxData != null && empMailBoxData.length > 0) {
				String sysmailQuery = "SELECT SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID WHERE SYSMAIL_SERVER_CODE= "
						+ String.valueOf(empMailBoxData[0][10])
						+ " AND ROWNUM=1";
				sysMailData = getSqlModel().getSingleResult(sysmailQuery);
				if (sysMailData != null && sysMailData.length > 0) {
					if (String.valueOf(empMailBoxData[0][15]).equals("Y")) {
						fromMailId = String.valueOf(sysMailData[0][0]);
					}
				}
				if (String.valueOf(empMailBoxData[0][12]).equals("S")) {
					if (empId == null || empId.length() == 0) {
						System.out.println("emp Id NULL :");
						if (sysMailData != null && sysMailData.length > 0) {
							System.out.println("sysMailData not NULL :");
							empMailBoxData[0][3] = sysMailData[0][0];
							empMailBoxData[0][4] = sysMailData[0][1];
							empMailBoxData[0][11] = sysMailData[0][0];
						}
					} else {
						System.out.println("fromMailId   emp Id present:"
								+ fromMailId);
						empMailBoxData[0][3] = sysMailData[0][0];
						empMailBoxData[0][4] = sysMailData[0][1];
						empMailBoxData[0][11] = fromMailId;
					}
				} else {
					empMailBoxData[0][3] = empMailBoxData[0][13];
					empMailBoxData[0][4] = empMailBoxData[0][14];
					empMailBoxData[0][11] = fromMailId;
				}

			}
		}
		if (sysMailData != null && sysMailData.length > 0) {
			for (int i = 0; i < sysMailData[0].length; i++) {
				System.out.println("  EMAIL SERVER DATA  : "
						+ String.valueOf(sysMailData[0][i]));
			}
		}
		if (empMailBoxData != null && empMailBoxData.length > 0) {
			for (int i = 0; i < empMailBoxData.length; i++) {
				for (int j = 0; j < empMailBoxData[0].length; j++) {
					System.out.println("empMailBoxData   [" + i + "][" + j
							+ "]" + empMailBoxData[i][j]);
				}

			}

		}

		return empMailBoxData;
	}

	public void viewCancelVacanciesList(VacancyPublish vacanPublish,
			HttpServletRequest request, String requisitionCode, String vacancyPublishCode, String vacancyDetailCode) {
		try {
			String getCancelVacancyList = "SELECT HRMS_REC_VACANCIES_STATS.VAC_CODE, HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE, HRMS_REC_REQS_HDR.REQS_NAME, "
										+" DECODE(HRMS_REC_VACANCIES_STATS.VAC_OFFER_GIVEN,'N','No','Y','Yes'),  "
										+" DECODE(HRMS_REC_VACANCIES_STATS.VAC_APPOINT_GIVEN,'N','No','Y','Yes'), " 
										+" DECODE(HRMS_REC_VACANCIES_STATS.VAC_STATUS,'O','Open','C','Close'), VAC_PUBLISH_CODE " 
										+" FROM HRMS_REC_VACANCIES_STATS "
										+" INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE)" 
										+" WHERE HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE = "+requisitionCode+" AND VAC_PUBLISH_CODE="+vacancyPublishCode;
			Object[][] getCancelVacancyObj = getSqlModel().getSingleResult(getCancelVacancyList);
			ArrayList<VacancyPublish> cancelList = new ArrayList<VacancyPublish>();
			if(getCancelVacancyObj!=null && getCancelVacancyObj.length>0) {
				vacanPublish.setVacancyListAvailable("true");
				vacanPublish.setTotalNumberOfRecords(String.valueOf(getCancelVacancyObj.length));
				for (int i = 0; i < getCancelVacancyObj.length; i++) {
					VacancyPublish beanItt = new VacancyPublish();
					beanItt.setVacancyNumberItr(checkNull(String.valueOf(getCancelVacancyObj[i][0])));
					beanItt.setRequisitionCodeItr(checkNull(String.valueOf(getCancelVacancyObj[i][1])));
					beanItt.setRequisitionNameItr(checkNull(String.valueOf(getCancelVacancyObj[i][2])));
					beanItt.setOfferGivenItr(checkNull(String.valueOf(getCancelVacancyObj[i][3])));
					beanItt.setAppointmentGivenItr(checkNull(String.valueOf(getCancelVacancyObj[i][4])));
					beanItt.setVacancyStatusItr(checkNull(String.valueOf(getCancelVacancyObj[i][5])));
					if(checkNull(String.valueOf(getCancelVacancyObj[i][5])).equals("Close")) {
						 beanItt.setIsVacancyClose("true");
					}
					beanItt.setVacancyPublishCodeItr(checkNull(String.valueOf(getCancelVacancyObj[i][6])));
					beanItt.setVacancyDetailCodeItr(vacancyDetailCode);
					cancelList.add(beanItt);
				}
				vacanPublish.setCancelVacancyIteratorList(cancelList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean updateVacancyStatus(VacancyPublish vacanPublish,
			HttpServletRequest request, String requisitionCode,
			String vacancyCode, String vacancyPublishCodeItr, String vacancyDetailCodeItr) {
		boolean result = false;
		/*try {
				 //String checkForVacancyNumberQuery = "SELECT HRMS_REC_REQS_VACDTL.VACAN_NUMBERS FROM HRMS_REC_REQS_VACDTL WHERE HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = "+vacancyDetailCodeItr;
				 String checkForVacancyNumberQuery = "SELECT VAC_STATUS, VAC_PUBLISH_CODE FROM HRMS_REC_VACANCIES_STATS WHERE VAC_PUBLISH_CODE = "+vacancyPublishCodeItr+"  AND VAC_REQS_CODE =  " +requisitionCode
				 									+" AND VAC_STATUS = 'O' ";
				 Object[][] checkForVacancyNumberObj = getSqlModel().getSingleResult(checkForVacancyNumberQuery);
				 if(checkForVacancyNumberObj != null && checkForVacancyNumberObj.length > 0) {
					 String updateVacancyStaus = "UPDATE HRMS_REC_VACANCIES_STATS SET HRMS_REC_VACANCIES_STATS.VAC_STATUS = 'C', HRMS_REC_VACANCIES_STATS.VAC_CLOSE_DATE = SYSDATE WHERE HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE = "+requisitionCode
												+" AND HRMS_REC_VACANCIES_STATS.VAC_CODE = "+vacancyCode+" AND HRMS_REC_VACANCIES_STATS.VAC_PUBLISH_CODE="+vacancyPublishCodeItr;
					 result = getSqlModel().singleExecute(updateVacancyStaus);
					 if(checkForVacancyNumberObj.length == 1) {
						 String updateReqsVacancyNumberQuery = "UPDATE HRMS_REC_REQS_VACDTL SET HRMS_REC_REQS_VACDTL.VACAN_STATUS='C', HRMS_REC_REQS_VACDTL.VACAN_CLOSE_DATE=SYSDATE WHERE HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = "+vacancyDetailCodeItr;
						 getSqlModel().singleExecute(updateReqsVacancyNumberQuery);
					 }
				 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		try {
			 String updateVacancyStaus = "UPDATE HRMS_REC_VACANCIES_STATS SET HRMS_REC_VACANCIES_STATS.VAC_STATUS = 'C' WHERE HRMS_REC_VACANCIES_STATS.VAC_REQS_CODE = "+requisitionCode
			 							+" AND HRMS_REC_VACANCIES_STATS.VAC_CODE = "+vacancyCode+" AND HRMS_REC_VACANCIES_STATS.VAC_PUBLISH_CODE="+vacancyPublishCodeItr;
			 result = getSqlModel().singleExecute(updateVacancyStaus);
			 if(result) {
				 String updateReqsVacancyNumberQuery = "UPDATE HRMS_REC_REQS_VACDTL SET HRMS_REC_REQS_VACDTL.VACAN_NUMBERS = (HRMS_REC_REQS_VACDTL.VACAN_NUMBERS -1) WHERE HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = "+vacancyDetailCodeItr;
				 result = getSqlModel().singleExecute(updateReqsVacancyNumberQuery);
				 
				 String query="SELECT VAC_STATUS FROM HRMS_REC_VACANCIES_STATS WHERE VAC_REQS_CODE="+requisitionCode+" AND VAC_STATUS='O'";
				Object dataAvailableObj[][] = getSqlModel().getSingleResult(query);
				 
				if(dataAvailableObj==null || dataAvailableObj.length ==0){
				 String updateVacancyQuery = "UPDATE  HRMS_REC_REQS_HDR SET REQS_STATUS='C' WHERE REQS_CODE= "+requisitionCode;
				 result = getSqlModel().singleExecute(updateVacancyQuery);
				 
				 String select="select  PUB_CODE from HRMS_REC_VACPUB_RECDTL where  PUB_CODE="+vacancyPublishCodeItr;
				 Object dataAvaObj[][] = getSqlModel().getSingleResult(select);
				 if(dataAvaObj!=null || dataAvaObj.length>0){
				 String updatePartnerQuery = "UPDATE  HRMS_REC_VACPUB_RECDTL set PUB_VAC_STATUS='C' WHERE PUB_CODE="+vacancyPublishCodeItr;
				 result = getSqlModel().singleExecute(updatePartnerQuery);
				 }
				 
				}
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void getDivisionName(VacancyPublish bean){
		String getName = "";
		try {			
			VacancyPublishModel model = new VacancyPublishModel();
			model.initiate(context, session);
			String query = "SELECT PUB_REF_DIV FROM HRMS_REC_VACPUB_HDR WHERE PUB_VACAN_DTLCODE="
					+ bean.getVacanDtlCode();
			Object[][] codeObj = model.getSqlModel().getSingleResult(query);
			String code = "";
			if (codeObj != null && codeObj.length > 0) {
				code = String.valueOf(codeObj[0][0]);
			}
			String selQuery = " SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION ";
			Object[][] obj = model.getSqlModel().getSingleResult(selQuery);
			if (!code.equals("null")) {
				getName = Utility.getNameByKey(obj, code);
			}			
			bean.setDivsion(getName);
			bean.setDivCode(code);
			model.terminate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
