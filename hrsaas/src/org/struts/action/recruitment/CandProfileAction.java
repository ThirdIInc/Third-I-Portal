package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.*;
import org.struts.lib.DyneActionSupport;
import org.paradyne.bean.Recruitment.CandProfile;
import org.paradyne.model.recruitment.CandProfileModel;

/**
 * 
 * @author Pradeep Kumar Sahoo Date:24-06-2009
 * 
 */

public class CandProfileAction extends DyneActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CandProfileAction.class);

	CandProfile candProf;

	public void prepare_local() throws Exception {
		candProf = new CandProfile();
		candProf.setMenuCode(898);
	}

	public static org.apache.log4j.Logger getLogger() {
		return logger;
	}

	public static void setLogger(org.apache.log4j.Logger logger) {
		CandProfileAction.logger = logger;
	}

	public CandProfile getCandProf() {
		return candProf;
	}

	public void setCandProf(CandProfile candProf) {
		this.candProf = candProf;
	}

	public Object getModel() {
		return candProf;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			// for getting server path where configuration files are saved.
			String dataPath = getText("data_path") + "/datafiles" + poolName
					+ "/resume/";
			String photoPath = getText("data_path") + "/datafiles" + poolName
					+ "/photograph/";
			candProf.setDataPath(dataPath);
			candProf.setPathPhoto(photoPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * following function is called when edit button in the first page is
	 * clicked.
	 * 
	 * @return
	 */
	public String editFun() {
		try {
			CandProfileModel model = new CandProfileModel();
			if (candProf.getCandCode().equals("")
					|| candProf.getCandCode().equals("null")) {

				addRowVac(candProf);
				request.setAttribute("photo", "");
			} else {

				model.initiate(context, session);
				model.searchCandDet(candProf, request);
				model.searchRefDtl(candProf);
				if (candProf.getRefFlag().equals("false")) {
					addRowVac(candProf);
				}
				model.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String input() {
		try {
			CandProfileModel model = new CandProfileModel();
			model.initiate(context, session);
			System.out.println("candProf.getCandidateUserEmpId()  "+candProf.getCandidateUserEmpId());
			candProf.setCandCode(candProf.getCandidateUserEmpId());
			// || candProf.getCandCode().equals("null"))
			if (!candProf.getCandCode().equals("")) {
				model.searchCandDet(candProf, request);
				model.searchRefDtl(candProf);
			} else {
				request.setAttribute("photo", "");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}

	/**
	 * following function will display the qualification details,exp history
	 * details,skill details and domain details.
	 * 
	 * @return
	 */

	public String nextPage() {
		try {
			CandProfileModel model = new CandProfileModel();
			model.initiate(context, session);
			if (!(candProf.getCandCode().equals("") || candProf.getCandCode()
					.equals("null"))) {
				model.retCandQual(candProf, request);
				model.retCandExp(candProf, request);
				model.retCandFunc(candProf, request);
				model.retCandSkill(candProf, request);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewNext";
	}

	/**
	 * following function is called when save and next button is clicked.
	 * 
	 * @return
	 */
	public String nextPageEdit() {
		try {
			CandProfileModel model = new CandProfileModel();
			model.initiate(context, session);
			if (candProf.getCandCode().equals("")
					|| candProf.getCandCode().equals("null")) {
				model.saveFirst(candProf);
				model.saveRefDet(candProf, candProf.getCandCode(), request);
				model.addRowQual(candProf, request);
				model.addRowExp(candProf, request);
				model.addRowFunc(candProf, request);
				model.addRowSkill(candProf, request);
				addActionMessage("Record saved successfully.");
			} else {
				model.updateFirst(candProf);
				model.saveRefDet(candProf, candProf.getCandCode(), request);
				model.retCandQual(candProf, request);
				if (candProf.getQualiFlag().equals("false")) {
					model.addRowQual(candProf, request);
				}
				model.retCandExp(candProf, request);
				if (candProf.getExpFlag().equals("false")) {
					model.addRowExp(candProf, request);
				}
				model.retCandFunc(candProf, request);
				if (candProf.getDomFlag().equals("false")) {
					model.addRowFunc(candProf, request);
				}
				model.retCandSkill(candProf, request);
				if (candProf.getSkillFlag().equals("false")) {
					model.addRowSkill(candProf, request);
				}
				addActionMessage("Record updated successfully.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	/**
	 * following function is called to display the records in the first page
	 * when Previous button is clicked in the second page.
	 * 
	 * @return
	 */
	public String previousView() {
		try {
			CandProfileModel model = new CandProfileModel();
			model.initiate(context, session);
			if (!(candProf.getCandCode().equals("") || candProf.getCandCode()
					.equals("null"))) {
				model.searchCandDet(candProf, request);
				model.searchRefDtl(candProf);
			} else {
				request.setAttribute("photo", "");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}

	/**
	 * following function is called when the Save button in the second page is
	 * clicked. to insert the records in the database.
	 * 
	 * @return
	 */
	public String saveSecond() {
		try {
			CandProfileModel model = new CandProfileModel();
			model.initiate(context, session);
			boolean result = model.saveQual(candProf.getCandCode(), request);
			boolean result1 = model.saveExp(candProf.getCandCode(), request);
			boolean result2 = model.saveSkill(candProf.getCandCode(), request);
			boolean result3 = model.saveFunc(candProf.getCandCode(), request);
			// if(result || result1 || result2 || result3){
			model.retCandQual(candProf, request);
			model.retCandExp(candProf, request);
			model.retCandSkill(candProf, request);
			model.retCandFunc(candProf, request);
			addActionMessage("Record updated successfully.");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewNext";
	}

	/**
	 * following function is called to display the records
	 * 
	 * @return
	 */
	public String editSec() {
		try {
			CandProfileModel model = new CandProfileModel();
			model.initiate(context, session);
			model.retCandQual(candProf, request);
			model.retCandExp(candProf, request);
			model.retCandSkill(candProf, request);
			model.retCandFunc(candProf, request);
			if (candProf.getQualiFlag().equals("false")) {
				model.addRowQual(candProf, request);
			}
			if (candProf.getExpFlag().equals("false")) {
				model.addRowExp(candProf, request);
			}
			if (candProf.getDomFlag().equals("false")) {
				model.addRowFunc(candProf, request);
			}
			if (candProf.getSkillFlag().equals("false")) {
				model.addRowSkill(candProf, request);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	/**
	 * following function is used to save the records in the second page and
	 * display the candidate details and reference details in the first page
	 * when the save and previous button is clicked in the second page.
	 * 
	 * @return
	 */
	public String saveAndPrevious() {
		try {
			if (candProf.getPathPhoto() != null) {
				request.setAttribute("photo", candProf.getPathPhoto());
			} else {
				request.setAttribute("photo",
						"../pages/images/employee/NoImage.jpg");
			}
			CandProfileModel model = new CandProfileModel();
			model.initiate(context, session);
			model.saveQual(candProf.getCandCode(), request);
			model.saveExp(candProf.getCandCode(), request);
			model.saveSkill(candProf.getCandCode(), request);
			model.saveFunc(candProf.getCandCode(), request);
			model.searchCandDet(candProf, request);
			model.searchRefDtl(candProf);
			if (candProf.getRefFlag().equals("false")) {
				addRowVac(candProf);
			}
			addActionMessage("Record updated successfully.");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * @purpose : For adding row in Reference table
	 */
	public void addRowVac(CandProfile bean) {
		try {
			ArrayList list = new ArrayList();
			CandProfile bean1 = new CandProfile();
			bean1.setSrNo(String.valueOf(1));
			bean1.setRefName("");
			bean1.setProfession("");
			bean1.setContactDet("");
			bean1.setRefComment("");
			list.add(bean1);
			bean.setRefList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	} // end of method

	/**
	 * following function is called to delete the rows from the Reference
	 * details list.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteRowRef() throws Exception {
		try {
			if (candProf.getPathPhoto() != null) {
				request.setAttribute("photo", candProf.getPathPhoto());
			} else {
				request.setAttribute("photo",
						"../pages/images/employee/NoImage.jpg");
			}
			String refNa[] = request.getParameterValues("refName");
			String[] prof = request.getParameterValues("profession");
			String[] contact = request.getParameterValues("contactDet");
			String[] comment = request.getParameterValues("refComment");//
			String[] code = request.getParameterValues("refDtlCode");
			String[] del = null;
			if (refNa != null) {
				del = new String[refNa.length];
				int j = 1;
				for (int i = 0; i < refNa.length; i++) {
					del[i] = request.getParameter("hdeleteVac" + j);
					j++;

				}

			}
			CandProfileModel model = new CandProfileModel();
			model.initiate(context, session);
			model.delRef(candProf, refNa, prof, contact, comment, code, del,
					request);
			String str = "";
			if (!(candProf.getCandCode().equals("") || candProf.getCandCode()
					.equals("null"))) {
				// model.searchCandDet(candProf, str, request);
			} else {
				request.setAttribute("photo", str);
			}
			if (candProf.getDoYou().equals("Y")) {
				candProf.setRadioFlag("true");
			} else if (candProf.getDoYou().equals("N")) {
				candProf.setRadioFlag("false");
			}
			if (candProf.getWheYouEmp().equals("Y")) {
				candProf.setRadioFlag1("true");
			} else if (candProf.getWheYouEmp().equals("N")) {
				candProf.setRadioFlag1("false");
			}
			if (candProf.getRelocate().equals("Y")) {
				candProf.setRadioFlag2("true");
			} else if (candProf.getRelocate().equals("N")) {
				candProf.setRadioFlag2("false");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * following function is called to retrieve the qualification details when
	 * any delete button is clicked in the second page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getQuaList() throws Exception {
		try {
			ArrayList<Object> quaList = new ArrayList<Object>();
			String name[] = null;
			String code[] = null;
			String[] lvl = null;
			String spN[] = null;
			String spCode[] = null;
			String year[] = request.getParameterValues("yearofPass");
			String[] est = request.getParameterValues("estbName");
			String[] detCode = request.getParameterValues("qualDetCode");

			if (est != null) {
				Object qualObj[][] = new Object[year.length][5];
				name = new String[est.length];
				code = new String[est.length];
				lvl = new String[est.length];
				spN = new String[est.length];
				spCode = new String[est.length];
				int j = 1;

				for (int i = 0; i < est.length; i++) {
					CandProfile bean = new CandProfile();
					bean.setYearofPass(year[i]);
					bean.setEstbName(est[i]);
					bean.setQualDetCode(detCode[i]);
					name[i] = (String) request.getParameter("qualName" + j);
					code[i] = (String) request.getParameter("qualId" + j);
					lvl[i] = (String) request.getParameter("qualLevel" + j);
					spN[i] = (String) request.getParameter("spelName" + j);
					spCode[i] = (String) request.getParameter("splId" + j);

					qualObj[i][0] = name[i];
					qualObj[i][1] = lvl[i];
					qualObj[i][2] = spN[i];
					qualObj[i][3] = code[i];
					qualObj[i][4] = spCode[i];
					quaList.add(bean);
					j++;

				}
				candProf.setQualList(quaList);
				request.setAttribute("qualObj", qualObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	/**
	 * following function is called to get the exp history details when any
	 * record is deleted from any list.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getExpDet() throws Exception {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			String employer[] = request.getParameterValues("emprName");
			String profession[] = request.getParameterValues("lastJobPro");
			String[] joiningDate = request.getParameterValues("joinDate");
			String relieving[] = request.getParameterValues("relvDate");
			String splAch[] = request.getParameterValues("specAch");
			String[] ctc = request.getParameterValues("ctcExp");
			String[] detCode = request.getParameterValues("expDtlId");
			String[] indsType = null;
			String[] indCode = null;
			String jobDescr[] = null;
			String[] del = null;
			if (employer != null) {
				indsType = new String[employer.length];
				indCode = new String[employer.length];
				jobDescr = new String[employer.length];
				del = new String[employer.length];
				int j = 1;
				for (int i = 0; i < employer.length; i++) {
					indsType[i] = (String) request.getParameter("indType" + j);
					indCode[i] = (String) request.getParameter("indsId" + j);
					jobDescr[i] = (String) request.getParameter("jdExp" + j);
					del[i] = (String) request.getParameter("deleteExp" + j);
					j++;
				}

				Object expObj[][] = new Object[employer.length][3];
				for (int i = 0; i < employer.length; i++) {
					CandProfile bean = new CandProfile();
					bean.setEmprName(employer[i]);
					bean.setLastJobPro(profession[i]);
					bean.setJoinDate(joiningDate[i]);
					bean.setRelvDate(relieving[i]);
					bean.setSpecAch(splAch[i]);
					bean.setCtcExp(ctc[i]);
					bean.setExpDtlId(detCode[i]);
					expObj[i][0] = jobDescr[i];
					expObj[i][1] = indsType[i];
					expObj[i][2] = indCode[i];
					tableList.add(bean);
				}
				candProf.setExpList(tableList);
				request.setAttribute("expObj", expObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	/**
	 * following function is called get the skill details when any record is
	 * deleted from any list.
	 * 
	 * @return
	 */
	public String getSkillList() {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			String exp[] = request.getParameterValues("skillExp");
			String[] detCode = request.getParameterValues("skillDtlId");
			String[] del = null;
			String[] name = null;
			String[] code = null;
			if (exp != null) {
				del = new String[exp.length];
				name = new String[exp.length];
				code = new String[exp.length];
				int j = 1;
				for (int i = 0; i < exp.length; i++) {
					name[i] = (String) request.getParameter("skillName" + j);
					code[i] = (String) request.getParameter("skillCode" + j);
					del[i] = (String) request.getParameter("deleteSkill" + j);
					j++;
				}

				Object[][] skillObj = new String[exp.length][2];
				for (int i = 0; i < exp.length; i++) {
					CandProfile bean = new CandProfile();
					bean.setSkillExp(exp[i]);
					bean.setSkillDtlId(detCode[i]);
					skillObj[i][0] = name[i];
					skillObj[i][1] = code[i];
					tableList.add(bean);
				}
				candProf.setSkillList(tableList);
				request.setAttribute("skillObj", skillObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	/**
	 * following function is called to display the functional/domain details
	 * when any record is deleted from any list.
	 * 
	 * @return
	 */
	public String getFuncList() {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			String exp[] = request.getParameterValues("funcExp");
			String[] detCode = request.getParameterValues("funcDtlId");
			String[] del = null;
			String[] name = null;
			String[] code = null;
			if (exp != null) {
				del = new String[exp.length];
				name = new String[exp.length];
				code = new String[exp.length];
				int j = 1;
				for (int i = 0; i < exp.length; i++) {
					name[i] = (String) request.getParameter("funcName" + j);
					code[i] = (String) request.getParameter("funcCode" + j);
					del[i] = (String) request.getParameter("deleteSkill" + j);
					j++;
				}
				Object[][] funcObj = new String[exp.length][2];
				for (int i = 0; i < exp.length; i++) {
					CandProfile bean = new CandProfile();
					bean.setFuncExp(exp[i]);
					bean.setFuncDtlId(detCode[i]);
					funcObj[i][0] = name[i];
					funcObj[i][1] = code[i];
					tableList.add(bean);
				}
				candProf.setFuncList(tableList);
				request.setAttribute("funcObj", funcObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	/**
	 * following function is called when the delete button of Qualification list
	 * is clicked. The rows of the qualification details are removed from the
	 * list
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteQualification() throws Exception {
		try {
			getNavigationPanel(7);
			String name[] = null;
			String code[] = null;
			String[] lvl = null;
			String spN[] = null;
			String spCode[] = null;
			String[] del = null;
			String year[] = request.getParameterValues("yearofPass");
			String[] est = request.getParameterValues("estbName");
			String[] detCode = request.getParameterValues("qualDetCode");
			if (est != null) {
				name = new String[est.length];
				code = new String[est.length];
				lvl = new String[est.length];
				spN = new String[est.length];
				spCode = new String[est.length];
				del = new String[est.length];
				int j = 1;
				for (int i = 0; i < est.length; i++) {
					name[i] = (String) request.getParameter("qualName" + j);
					code[i] = (String) request.getParameter("qualId" + j);
					lvl[i] = (String) request.getParameter("qualLevel" + j);
					spN[i] = (String) request.getParameter("spelName" + j);
					spCode[i] = (String) request.getParameter("splId" + j);
					del[i] = (String) request.getParameter("deleteQuali" + j);
					j++;
				}
			}
			CandProfileModel model = new CandProfileModel();
			model.initiate(context, session);
			model.delQual(candProf, name, code, lvl, spN, spCode, del, year,
					est, detCode, request);
			getExpDet();
			getSkillList();
			getFuncList();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	/**
	 * following function is called to delete the records from Exp History when
	 * delete button is clicked
	 * 
	 * @return
	 */
	public String deleteExperience() {
		try {
			String employer[] = request.getParameterValues("emprName");
			String profession[] = request.getParameterValues("lastJobPro");
			String[] joiningDate = request.getParameterValues("joinDate");
			String relieving[] = request.getParameterValues("relvDate");
			String splAch[] = request.getParameterValues("specAch");
			String[] ctc = request.getParameterValues("ctcExp");
			String[] detCode = request.getParameterValues("expDtlId");
			String[] indsType = null;
			String[] indCode = null;
			String jobDescr[] = null;
			String[] del = null;
			if (employer != null) {
				indsType = new String[employer.length];
				indCode = new String[employer.length];
				jobDescr = new String[employer.length];
				del = new String[employer.length];
				int j = 1;
				for (int i = 0; i < employer.length; i++) {
					indsType[i] = (String) request.getParameter("indType" + j);
					indCode[i] = (String) request.getParameter("indsId" + j);
					jobDescr[i] = (String) request.getParameter("jdExp" + j);
					del[i] = (String) request.getParameter("deleteExp" + j);
					j++;
				}

				CandProfileModel model = new CandProfileModel();
				model.initiate(context, session);
				model.delExp(candProf, employer, profession, joiningDate,
						relieving, splAch, ctc, detCode, indsType, indCode,
						jobDescr, del, request);
				getQuaList();
				getSkillList();
				getFuncList();
				model.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	/**
	 * following function is called when any record is deleted from Skill list.
	 * 
	 * @return
	 * @throws Exception
	 */

	public String deleteSkill() {
		try {
			String exp[] = request.getParameterValues("skillExp");
			String[] detCode = request.getParameterValues("skillDtlId");
			String[] del = null;
			String[] name = null;
			String[] code = null;
			if (exp != null) {
				del = new String[exp.length];
				name = new String[exp.length];
				code = new String[exp.length];
				int j = 1;
				for (int i = 0; i < exp.length; i++) {
					name[i] = (String) request.getParameter("skillName" + j);
					code[i] = (String) request.getParameter("skillCode" + j);
					del[i] = (String) request.getParameter("deleteSkill" + j);
					j++;
				}
			}
			CandProfileModel model = new CandProfileModel();
			model.initiate(context, session);
			model.delSkill(candProf, exp, detCode, code, name, del, request);
			getQuaList();
			getExpDet();
			getFuncList();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	/**
	 * following function is called when any record is deleted from
	 * Functional/Domain list.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteFunc() throws Exception {
		try {
			String exp[] = request.getParameterValues("funcExp");
			String[] detCode = request.getParameterValues("funcDtlId");
			String[] del = null;
			String[] name = null;
			String[] code = null;
			if (exp != null) {
				del = new String[exp.length];
				name = new String[exp.length];
				code = new String[exp.length];
				int j = 1;
				for (int i = 0; i < exp.length; i++) {

					name[i] = (String) request.getParameter("funcName" + j);
					code[i] = (String) request.getParameter("funcCode" + j);
					del[i] = (String) request.getParameter("deleteFunc" + j);
					j++;
				}
				CandProfileModel model = new CandProfileModel();
				model.initiate(context, session);
				model.delFunc(candProf, exp, detCode, code, name, del, request);
				getQuaList();
				getExpDet();
				getSkillList();
				model.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSecond";
	}

	/**
	 * following function is called to save
	 * 
	 * @return
	 */
	public String saveFirst() {
		try {
			if (candProf.getPathPhoto() != null) {
				request.setAttribute("photo", candProf.getPathPhoto());
			} else {
				request.setAttribute("photo",
						"../pages/images/employee/NoImage.jpg");
			}
			CandProfileModel model = new CandProfileModel();
			model.initiate(context, session);
			boolean result1 = false;
			if (candProf.getCandCode().equals("")
					|| candProf.getCandCode().equals("null")) {
				boolean result = model.saveFirst(candProf);
				if (result) {
					result1 = model.saveRefDet(candProf,
							candProf.getCandCode(), request);
				}
				if (result1) {
					model.searchCandDet(candProf, request);
					model.searchRefDtl(candProf);
					addActionMessage("Record saved successfully.");
				}
			} else {
				model.updateFirst(candProf);
				model.saveRefDet(candProf, candProf.getCandCode(), request);
				model.searchCandDet(candProf, request);
				model.searchRefDtl(candProf);
				addActionMessage("Record updated successfully.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}

	/**
	 * following function is called when View button nis clicked in the
	 * candDataBankView.jsp page This function displays the candidate's cv.
	 * 
	 * @throws Exception
	 */
	public void viewCV() throws Exception {
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
			String path = getText("data_path") + "/datafiles/" + poolName
					+ "/resume/" + fileName;
			oStream = response.getOutputStream();

			response.setHeader("Content-type", "application/msword");
			response.setHeader("Content-disposition", "inline;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (FileNotFoundException e) {
			logger.error("-----in file not found catch", e);
			addActionMessage("Resume not found");
		} catch (Exception e) {
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

	public String f9Quali() throws Exception {
		String query = " SELECT NVL(QUA_NAME,' '),NVL(QUA_ABBR,' '),CASE WHEN QUA_LEVEL='UG' THEN 'Under Graduate' "
				+ " WHEN QUA_LEVEL='DI' THEN 'Diploma' WHEN QUA_LEVEL='GR' THEN 'Graduate' WHEN QUA_LEVEL='PG' THEN 'Post Graduate'"
				+ " WHEN QUA_LEVEL='PH' THEN 'Phd' WHEN QUA_LEVEL='DO' THEN 'Doctorate' ELSE ' ' END ,QUA_ID FROM HRMS_QUA WHERE QUA_STATUS='A' ORDER BY QUA_ID";

		String[] headers = { "Qualification Name", "Quaification Abbr",
				"Qualification Level" };
		String[] headerwidth = { "40", "30", "30" };
		String[] fieldNames = { "qua" + candProf.getRowId(),
				"qualName" + candProf.getRowId(),
				"qualLevel" + candProf.getRowId(),
				"qualId" + candProf.getRowId() };
		int[] columnIndex = { 0, 1, 2, 3 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9Specialization() throws Exception {
		String query = " SELECT NVL(SPEC_NAME,' '),NVL(SPEC_ABBR,' '),SPEC_ID FROM HRMS_SPECIALIZATION where SPEC_STATUS='A' ORDER BY SPEC_ID";
		String[] headers = { "Specialization Name", "Specialization Abbr" };
		String[] headerwidth = { "40", "30" };
		String[] fieldNames = { "spl" + candProf.getRowId(),
				"spelName" + candProf.getRowId(), "splId" + candProf.getRowId() };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlage = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9Function() throws Exception {
		String query = " SELECT NVL(FUNC_DOMAIN_NAME,' '),FUNC_DOMAIN_CODE FROM HRMS_FUNC_DOMAIN_MASTER WHERE FUNC_DOMAIN_STATUS='A' ORDER BY FUNC_DOMAIN_CODE";
		System.out.println("pppppp");
		String[] headers = { "Domain Name" };
		String[] headerwidth = { "50" };
		String fieldNames[] = { "funcName" + candProf.getRowId(),
				"funcCode" + candProf.getRowId() };
		// String fieldNames[]={request.getParameter("field")};
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9Skill() throws Exception {
		String query = " SELECT NVL(SKILL_NAME,' '),SKILL_ID FROM HRMS_SKILL WHERE SKILL_STATUS='A' ORDER BY SKILL_ID";
		String[] headers = { "Skill Name" };
		String[] headerwidth = { "40" };
		String[] fieldNames = { "skillName" + candProf.getRowId(),
				"skillCode" + candProf.getRowId() };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9IndExp() throws Exception {
		String query = " SELECT NVL(INDUS_NAME,' '),NVL(INDUS_ABBRIVIATION,' '),INDUS_CODE FROM HRMS_INDUS_TYPE where INDUS_STATUS='A' ORDER BY INDUS_CODE";
		String[] headers = { "Industry Name", "Industry Abbr" };
		String[] headerwidth = { "40", "40" };
		String[] fieldNames = { "indsName" + candProf.getRowId(),
				"indType" + candProf.getRowId(), "indsId" + candProf.getRowId() };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlage = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9JobExp() throws Exception {
		String query = " SELECT NVL(JOB_DESC_NAME,' ') FROM HRMS_JOB_DESCRIPTION WHERE JOB_DESC_STATUS='A' ";
		String[] headers = { "Job Description Name" };
		String[] headerwidth = { "50" };
		String[] fieldNames = { "jdExp" + candProf.getRowId() };
		int[] columnIndex = { 0 };
		String submitFlage = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9cityaction1() throws Exception {
		String query = " select HRMS_LOCATION.LOCATION_NAME,state.LOCATION_NAME,country.LOCATION_NAME,HRMS_LOCATION.LOCATION_CODE"
				+ " ,state.LOCATION_CODE,country.LOCATION_CODE from HRMS_LOCATION "
				+ " inner join HRMS_LOCATION state on (state.LOCATION_CODE = HRMS_LOCATION.LOCATION_PARENT_CODE)"
				+ " inner join HRMS_LOCATION country on (country.LOCATION_CODE = state.LOCATION_PARENT_CODE)ORDER BY HRMS_LOCATION.LOCATION_CODE";
		String[] headers = { "City", "State", "Country" };
		String[] headerwidth = { "25", "35", "40" };
		String[] fieldNames = { "city", "state", "country", "cityCode",
				"stateCode", "countryCode" };
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };
		String submitFlage = "";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9cityaction2() throws Exception {
		String query = " select NVL(HRMS_LOCATION.LOCATION_NAME,' '),NVL(state.LOCATION_NAME,' '),NVL(country.LOCATION_NAME,' ')"// ,HRMS_LOCATION.LOCATION_CODE,"
		// +" state.LOCATION_CODE,country.LOCATION_CODE from HRMS_LOCATION "
				+ " ,HRMS_LOCATION.LOCATION_CODE,state.LOCATION_CODE,country.LOCATION_CODE from HRMS_LOCATION inner join HRMS_LOCATION state on (state.LOCATION_CODE = HRMS_LOCATION.LOCATION_PARENT_CODE)"
				+ " inner join HRMS_LOCATION country on (country.LOCATION_CODE = state.LOCATION_PARENT_CODE) ORDER BY HRMS_LOCATION.LOCATION_CODE";
		String[] headers = { "City", "State", "Country" };
		String[] headerwidth = { "25", "35", "40" };
		String[] fieldNames = { "city1", "state1", "country1", "cityCode1",
				"stateCode1", "countryCode1" };//," countryCode1"};
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };//,3,4,5};
		String submitFlage = "";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}
}
