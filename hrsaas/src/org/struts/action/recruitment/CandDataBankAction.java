package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.CandDataBank;
import org.paradyne.model.recruitment.CandDataBankModel;
import org.paradyne.model.recruitment.CandidateJobBoardModel;
import org.struts.lib.ParaActionSupport;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

public class CandDataBankAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CandDataBankAction.class);
	CandDataBank candDB;

	public void prepare_local() throws Exception {
		candDB = new CandDataBank();
		candDB.setMenuCode(346);

	}

	public void prepare_withLoginProfileDetails() throws Exception {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "/" + poolName;
		}

		// for getting server path where configuration files are saved.
		String dataPath = getText("data_path") + "/datafiles" + poolName
				+ "/resume/";

		String photoPath = getText("data_path") + "/datafiles" + poolName
				+ "/photograph/";
		candDB.setDataPath(dataPath);
		candDB.setPathPhoto(photoPath);
	}

	public Object getModel() {
		return candDB;
	}

	public CandDataBank getCandDB() {
		return candDB;
	}

	public void setCandDB(CandDataBank candDB) {
		this.candDB = candDB;
	}

	public String input() {
		try {
			getNavigationPanel(1);
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			candDB.setSearchCandId("");
			candDB.setSearchCandName("");
			candDB.setFromDate("");
			candDB.setToDate("");
			candDB.setExpYear("");
			candDB.setExpMonth("");
			candDB.setCandStatus("");
			candDB.setResumeSrc("");
			candDB.setViewEditFlag("false");
			// candDB.setMyPage("");
			candDB.setShow("");
			candDB.setPageFlag("false");
			String path = getText("data_path");
			String msg = getMessage("postingFrmDt");
			String msg1 = getMessage("postingToDt");
			String msg2 = getMessage("stat");
			String msg3 = getMessage("resSrc");
			model.showRecords(candDB, request, "", msg, msg1, msg2, msg3);
			candDB.setCandCode("");
			candDB.setListFlag("true");
			candDB.setSearchFlag("false");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getJobNameDetails()
	{
		
		try {
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			String reqCodeStr = request.getParameter("reqCodeStr");
			System.out.println("reqCodeStr            " + reqCodeStr);
			Object[][] reqDtlObj = model.getJobNameDetails(reqCodeStr);
			request.setAttribute("reqDtlObj", reqDtlObj);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "jobNameDtlJsp";
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
			addActionMessage("Resume not found");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

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

	public String callPage() {
		try {
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			String msg = getMessage("postingFrmDt");
			String msg1 = getMessage("postingToDt");
			String msg2 = getMessage("stat");
			String msg3 = getMessage("resSrc");
			model.showRecords(candDB, request, "", msg, msg1, msg2, msg3);
			candDB.setListFlag("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return SUCCESS;

	}

	/**
	 * method addNew
	 * 
	 * @purpose : to set next button panel on clicking addNew button
	 * @return String
	 */
	public String addNew() {
		try {
			candDB.setListFlag("false");
			getNavigationPanel(2);
			candDB.setMyPage("");
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			model.addRowVac(candDB);
			model.terminate();
			String photo = "null";
			request.setAttribute("photo", photo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String saveFirst() {

		boolean result = false;
		boolean result1 = false;

		String returnString = "success";
		try {
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			result = model.saveRecord(candDB);
			if (!result) {
				if (candDB.getDupChkFlag().equals("true")) {
					addActionMessage("Candidate with given details already exists in databank");
				}

				candDB.setListFlag("false");
				if (candDB.getPathPhoto() != null) {
					request.setAttribute("photo", candDB.getPathPhoto());
				} else {
					request.setAttribute("photo",
							"../pages/images/employee/NoImage.jpg");
				}
				model.addRowVac(candDB);
				getNavigationPanel(2);
				model.terminate();
				returnString = "success";
			} else {
				getNavigationPanel(3);

				if (candDB.getPathPhoto() != null) {
					request.setAttribute("photo", candDB.getPathPhoto());
				} else {
					request.setAttribute("photo",
							"../pages/images/employee/NoImage.jpg");
				}

				if (!candDB.getCandCode().equals("")
						|| !candDB.getCandCode().equals("null")) {
					if (candDB.getDupChkFlag().equals("true")) {
					} else {
						String[] refName = request
								.getParameterValues("refName");
						String[] profession = request
								.getParameterValues("profession");
						String[] refContact = request
								.getParameterValues("contactDet");
						String[] refComments = request
								.getParameterValues("refComment");
						result1 = model.saveRefDet(refName, profession,
								refContact, refComments, candDB.getCandCode());
					}
				}
				if (result1) {

					String str = (String) session.getAttribute("session_pool");
					model.searchCandDet(candDB, str, request);
					model.searchRefDtl(candDB);
					addActionMessage("Record saved successfully.");
				}
				model.terminate();
				returnString = "viewSearch";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnString;
	}

	public String saveAndPrevious() {
		getNavigationPanel(2);
		return SUCCESS;
	}

	public String searchRecFirst() {
		try {
			candDB.setListFlag("false");
			candDB.setMyPage("");
			getNavigationPanel(3);
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			String str = (String) session.getAttribute("session_pool");
			model.searchCandDet(candDB, str, request);
			model.searchRefDtl(candDB);
			candDB.setUpdateFirst("true");
			candDB.setUpdateSecond("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSearch";
		// return "success";
	}

	public String nextPageView() {
		try {
			if(candDB.getFromCandidateScreening().equals("fromCandidateSearch")) {
				getNavigationPanel(9);
			} else {
				getNavigationPanel(4);
			}
			
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			model.retCandQual(candDB, request);
			model.retCandExp(candDB, request);
			model.retCandSkill(candDB, request);
			model.retCandFunc(candDB, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSearch1";
	}

	public String updateFirst() {
		String finalResult = "";
		try {
			String[] refName = request.getParameterValues("refName");
			String[] profession = request.getParameterValues("profession");
			String[] refContact = request.getParameterValues("contactDet");
			String[] refComments = request.getParameterValues("refComment");
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			boolean result = model.updateFirst(candDB);
			/*if (!result) {
				if (candDB.getDupChkFlag().equals("true")) {
					addActionMessage("Candidate with given details already exists in databank");
				}

				candDB.setListFlag("false");
				if (candDB.getPathPhoto() != null) {
					request.setAttribute("photo", candDB.getPathPhoto());
				} else {
					request.setAttribute("photo",
							"../images/employee/NoImage.jpg");
				}
				getNavigationPanel(2);
				finalResult = "success";
			} else {*/
			if(result) {
				getNavigationPanel(3);
				model.saveRefDet(refName, profession, refContact, refComments,
						candDB.getCandCode());
				String str = (String) session.getAttribute("session_pool");
				model.searchCandDet(candDB, str, request);
				model.searchRefDtl(candDB);
				model.terminate();
				addActionMessage("Record updated successfully.");
				finalResult = "viewSearch";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalResult;
	}

	public String previous() {
		try {
			if(candDB.getFromCandidateScreening().equals("fromCandidateSearch")) {
				getNavigationPanel(8);
			} else {
				getNavigationPanel(3);
			}
			
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			String str = (String) session.getAttribute("session_pool");
			model.searchCandDet(candDB, str, request);
			model.searchRefDtl(candDB);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSearch";
	}

	public String deleteRec() {
		try {
			getNavigationPanel(1);
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			boolean result = model.deleteRecord(candDB);
			if (result) {
				addActionMessage("Record deleted successfully.");
			} else {
				addActionMessage("This record is referenced in other resources.\nSo can't be deleted.");
			}
			String msg = getMessage("postingFrmDt");
			String msg1 = getMessage("postingToDt");
			String msg2 = getMessage("stat");
			String msg3 = getMessage("resSrc");
			model.showRecords(candDB, request, "", msg, msg1, msg2, msg3);
			// model.showRecords(candDB,request,"");
			candDB.setListFlag("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String updateSecond() {
		try {
			getNavigationPanel(4);
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			model.delQuali(candDB.getCandCode());
			saveQual(candDB.getCandCode(), request);
			model.delExp(candDB.getCandCode());
			saveExp(candDB.getCandCode(), request);
			model.delSkill(candDB.getCandCode());
			saveSkill(candDB.getCandCode(), request);
			model.delFun(candDB.getCandCode());
			saveFunc(candDB.getCandCode(), request);
			model.retCandQual(candDB, request);
			model.retCandExp(candDB, request);
			model.retCandSkill(candDB, request);
			model.retCandFunc(candDB, request);
			addActionMessage("Record updated successfully.");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSearch1";
	}

	public String editFirst() {
		try {
			getNavigationPanel(2);
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			String str = (String) session.getAttribute("session_pool");
			model.searchCandDet(candDB, str, request);
			model.searchRefDtl(candDB);
			if (candDB.getRefFlag().equals("false")) {
				model.addRowVac(candDB);
			}
			candDB.setCancelFirst("true");
			candDB.setUpdateFirst("true");
			candDB.setUpdateSecond("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * following function is called when any row is double clicked in the list
	 * the corresponding record will be set.
	 * 
	 * @return
	 */
	public String callForEdit() {
		try {
			String candidateCodeFromCandidateSearch = request.getParameter("candidateCodeFromCandidateSearch");
			System.out.println("candidateCodeFromCandidateSearch >>>>>>"+candidateCodeFromCandidateSearch);
			candDB.setListFlag("false");
			// getNavigationPanel(2);
			
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			String str = (String) session.getAttribute("session_pool");
			if(candidateCodeFromCandidateSearch == null || candidateCodeFromCandidateSearch.equals("null")) {
				candDB.setCandCode(candDB.getHiddenCode());
				candDB.setFromCandidateScreening("");
				getNavigationPanel(3);
			} else {
				candDB.setCandCode(candidateCodeFromCandidateSearch);
				candDB.setFromCandidateScreening("fromCandidateSearch");
				getNavigationPanel(8);
			}
			
			model.searchCandDet(candDB, str, request);
			model.searchRefDtl(candDB);
			if (candDB.getRefFlag().equals("false")) {
				model.addRowVac(candDB);
			}
			// candDB.setCancelFirst("true");
			candDB.setUpdateFirst("true");
			candDB.setUpdateSecond("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSearch";
	}

	public String cancelFirst() {
		try {
			getNavigationPanel(3);
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			String str = (String) session.getAttribute("session_pool");
			model.searchCandDet(candDB, str, request);
			model.searchRefDtl(candDB);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSearch";
	}

	public String nextPage() {
		String finalResult = "";
		try {
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			if (candDB.getCandCode().equals("")
					|| candDB.getCandCode().equals("null")) {
				if (("true").equals(candDB.getDupChkFlag())) {
					addActionMessage("Candidate with given details already exists in databank");
				} else {
					saveFirst();
					model.addRowQual(candDB, request);
					model.addRowExp(candDB, request);
					model.addRowSkill(candDB, request);
					model.addRowFunc(candDB, request);
				}
			} else {
				updateFirst();
				candDB.setUpdateSecond("true");
				candDB.setUpdateSecond("true");
				model.retCandQual(candDB, request);
				model.retCandExp(candDB, request);
				model.retCandSkill(candDB, request);
				model.retCandFunc(candDB, request);

				if (candDB.getQualiFlag().equals("false")) {
					model.addRowQual(candDB, request);
				}
				if (candDB.getExpFlag().equals("false")) {
					model.addRowExp(candDB, request);
				}
				if (candDB.getSkillFlag().equals("false")) {
					model.addRowSkill(candDB, request);
				}
				if (candDB.getDomFlag().equals("false")) {
					model.addRowFunc(candDB, request);
				}
			}
			if (candDB.getDupChkFlag().equals("true")) {
				model.terminate();
				getNavigationPanel(2);
				finalResult = "success";
			} else {
				model.terminate();
				getNavigationPanel(7);
				finalResult = "next";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalResult;
	}

	public String saveAndPrev() {
		try {
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			if (candDB.getUpdateSecond().equals("true")) {
				updateSecond();
				candDB.setUpdateFirst("true");
			} else {
				saveSecond();
				candDB.setUpdateFirst("true");
			}
			String str = (String) session.getAttribute("session_pool");
			model.searchCandDet(candDB, str, request);
			model.searchRefDtl(candDB);
			if (candDB.getRefFlag().equals("false")) {
				model.addRowVac(candDB);
			}
			model.terminate();
			getNavigationPanel(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String saveSecond() {
		try {
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			getNavigationPanel(4);
			model.delQuali(candDB.getCandCode());
			saveQual(candDB.getCandCode(), request);
			model.delExp(candDB.getCandCode());
			saveExp(candDB.getCandCode(), request);
			model.delSkill(candDB.getCandCode());
			saveSkill(candDB.getCandCode(), request);
			model.delFun(candDB.getCandCode());
			saveFunc(candDB.getCandCode(), request);
			model.terminate();
			addActionMessage("Record updated successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveSecond";
	}

	public String cancelSec() {
		try {
			getNavigationPanel(4);
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			model.retCandQual(candDB, request);
			model.retCandExp(candDB, request);
			model.retCandSkill(candDB, request);
			model.retCandFunc(candDB, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSearch1";
	}

	public String editSec() {
		try {
			getNavigationPanel(7);
			CandDataBankModel model = new CandDataBankModel();
			// model.retCandExp(candDB, request);
			candDB.setUpdateSecond("true");
			candDB.setCancelSecond("true");
			model.initiate(context, session);
			model.retCandQual(candDB, request);
			model.retCandExp(candDB, request);
			model.retCandSkill(candDB, request);
			model.retCandFunc(candDB, request);
			if (candDB.getQualiFlag().equals("false")) {
				model.addRowQual(candDB, request);
			}
			if (candDB.getExpFlag().equals("false")) {
				model.addRowExp(candDB, request);
			}
			if (candDB.getSkillFlag().equals("false")) {
				model.addRowSkill(candDB, request);
			}
			if (candDB.getDomFlag().equals("false")) {
				model.addRowFunc(candDB, request);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	public void saveQual(String candCode, HttpServletRequest request) {
		try {
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			String[] estbName = request.getParameterValues("estbName");
			String[] yearofPass = request.getParameterValues("yearofPass");
			if (estbName != null) {
				int j = 1;
				if (estbName.length > 0) {

					for (int i = 0; i < estbName.length; i++) {
						String code = (String) request.getParameter("qualId"
								+ j);
						if (!(code.equals("") || code.equals("null") || code
								.equals(" "))) {
							Object[][] qualObj = new Object[1][6];
							qualObj[0][0] = candCode;
							qualObj[0][1] = code;
							qualObj[0][2] = (String) request
									.getParameter("splId" + j);
							qualObj[0][3] = estbName[i];
							qualObj[0][4] = yearofPass[i];
							qualObj[0][5] = "";
							model.saveQual(qualObj, candCode);
						}
						j++;
					}
				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String deleteFunc() throws Exception {
		try {
			getNavigationPanel(7);
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

				CandDataBankModel model = new CandDataBankModel();
				model.initiate(context, session);
				model.delFunc(candDB, exp, detCode, code, name, del, request);
				getQuaList();
				getExpDet();
				getSkillList();
				model.terminate();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	public String deleteSkill() {
		try {
			getNavigationPanel(7);
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
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			model.delSkill(candDB, exp, detCode, code, name, del, request);
			getQuaList();
			getExpDet();
			getFuncList();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	public String getFuncList() {
		try {
			getNavigationPanel(7);
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
					CandDataBank bean = new CandDataBank();
					bean.setFuncExp(exp[i]);
					bean.setFuncDtlId(detCode[i]);
					funcObj[i][0] = name[i];
					funcObj[i][1] = code[i];
					tableList.add(bean);
				}
				candDB.setFuncList(tableList);
				request.setAttribute("funcObj", funcObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	public String getSkillList() {
		try {
			getNavigationPanel(7);
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
					CandDataBank bean = new CandDataBank();
					bean.setSkillExp(exp[i]);
					bean.setSkillDtlId(detCode[i]);
					skillObj[i][0] = name[i];
					skillObj[i][1] = code[i];
					tableList.add(bean);
				}
				candDB.setSkillList(tableList);
				request.setAttribute("skillObj", skillObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	public String deleteExperience() {
		try {
			getNavigationPanel(7);
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

				CandDataBankModel model = new CandDataBankModel();
				model.initiate(context, session);

				model.delExp(candDB, employer, profession, joiningDate,
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
		return "next";
	}

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
					CandDataBank bean = new CandDataBank();
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
				candDB.setExpList(tableList);
				request.setAttribute("expObj", expObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	public String deleteRowRef() throws Exception {
		try {
			getNavigationPanel(2);
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
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			model.delRef(candDB, refNa, prof, contact, comment, code, del,
					request);
			String str = "";
			if (!(candDB.getCandCode().equals("") || candDB.getCandCode()
					.equals("null"))) {
				model.searchCandDet(candDB, str, request);
			} else {
				request.setAttribute("photo", str);
			}
			if (candDB.getDoYou().equals("Y")) {
				candDB.setRadioFlag("true");
			} else if (candDB.getDoYou().equals("N")) {
				candDB.setRadioFlag("false");
			}
			if (candDB.getWheYouEmp().equals("Y")) {
				candDB.setRadioFlag1("true");
			} else if (candDB.getWheYouEmp().equals("N")) {
				candDB.setRadioFlag1("false");
			}
			if (candDB.getRelocate().equals("Y")) {
				candDB.setRadioFlag2("true");
			} else if (candDB.getRelocate().equals("N")) {
				candDB.setRadioFlag2("false");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
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
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			model.delQual(candDB, name, code, lvl, spN, spCode, del, year, est,
					detCode, request);
			
			getExpDet();
			getSkillList();
			getFuncList();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
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
					CandDataBank bean = new CandDataBank();
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
				candDB.setQualList(quaList);
				request.setAttribute("qualObj", qualObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	public void saveSkill(String candCode, HttpServletRequest request) {
		try {
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			String[] skillExp = request.getParameterValues("skillExp");
			if (skillExp != null) {
				int j = 1;
				if (skillExp.length > 0) {
					for (int i = 0; i < skillExp.length; i++) {
						String code = (String) request.getParameter("skillCode"
								+ j);
						if (!(code.equals("") || code.equals("null") || code
								.equals(" "))) {
							Object[][] skillObj = new Object[1][4];
							skillObj[0][0] = candCode;
							skillObj[0][1] = "S";
							skillObj[0][2] = code;
							skillObj[0][3] = skillExp[i];
							model.saveSkill(skillObj, candCode);
						}
						j++;
					}
				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveExp(String candCode, HttpServletRequest request) {
		try {
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			String[] emprName = request.getParameterValues("emprName");
			String[] lastJobPro = request.getParameterValues("lastJobPro");
			String[] joinDate = request.getParameterValues("joinDate");
			String[] relvDate = request.getParameterValues("relvDate");
			String[] specAch = request.getParameterValues("specAch");
			String[] ctcExp = request.getParameterValues("ctcExp");
			String[] jobExp = null;// request.getParameterValues("jdExp");
			if (emprName != null) {
				int j = 1;
				jobExp = new String[emprName.length];
				if (emprName.length > 0) {

					for (int i = 0; i < emprName.length; i++) {
						if (!(emprName[i].equals("")
								|| emprName[i].equals("null") || emprName[i]
								.equals(" "))) {
							Object[][] expObj = new Object[1][9];
							jobExp[i] = (String) request.getParameter("jdExp"
									+ j);
							expObj[0][0] = candCode;
							expObj[0][1] = emprName[i];
							expObj[0][2] = lastJobPro[i];
							expObj[0][3] = joinDate[i];
							expObj[0][4] = relvDate[i];
							expObj[0][5] = jobExp[i]; 
							expObj[0][6] = (String) request
									.getParameter("indsId" + j);
							expObj[0][7] = specAch[i];
							expObj[0][8] = ctcExp[i];
							model.saveExp(expObj, candCode);
						}
						j++;

					}

				}
			} 
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveFunc(String candCode, HttpServletRequest request) {
		try {
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			String[] funcExp = request.getParameterValues("funcExp");
			if (funcExp != null) {
				int j = 1;
				if (funcExp.length > 0) {

					for (int i = 0; i < funcExp.length; i++) {
						String code = (String) request.getParameter("funcCode"
								+ j);
						if (!(code.equals("") || code.equals("null") || code
								.equals(" "))) {
							Object[][] funcObj = new Object[1][4];
							funcObj[0][0] = candCode;
							funcObj[0][1] = "F";
							funcObj[0][2] = (String) request
									.getParameter("funcCode" + j);
							funcObj[0][3] = funcExp[i];
							model.saveFunc(funcObj, candCode);
						}

						j++;
					}

				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String f9search() throws Exception {
		String query = "SELECT nvl(CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,''),nvl(CAND_EXP_YEAR,0) ||' Year '|| nvl(CAND_EXP_MONTH,0)||' Months',"
				+ "nvl(TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY'),' '),CAND_CODE FROM HRMS_REC_CAND_DATABANK ORDER BY CAND_POSTING_DATE DESC";

		String[] headers = { getMessage("candhead"), getMessage("exphead"),
				getMessage("posthead") };
		String[] headerwidth = { "35", "25", "20" };
		String[] fieldNames = { "candName", "experience", "postingDate",
				"candCode" };
		int[] columnIndex = { 0, 1, 2, 3 };
		String submitFlage = "true";
		String submitToMethod = "CandDataBank_searchRecFirst.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9TitleAction() throws Exception {
		String query = "SELECT TITLE_NAME,TITLE_CODE FROM HRMS_TITLE ORDER BY UPPER(TITLE_NAME)";

		String[] headers = { getMessage("title") };
		String[] headerwidth = { "100" };
		String[] fieldNames = { "titleName", "titleCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9cityaction1() throws Exception {
		String query = " select HRMS_LOCATION.LOCATION_NAME,state.LOCATION_NAME,country.LOCATION_NAME,HRMS_LOCATION.LOCATION_CODE"
				+ " ,state.LOCATION_CODE,country.LOCATION_CODE from HRMS_LOCATION "
				+ " inner join HRMS_LOCATION state on (state.LOCATION_CODE = HRMS_LOCATION.LOCATION_PARENT_CODE)"
				+ " inner join HRMS_LOCATION country on (country.LOCATION_CODE = state.LOCATION_PARENT_CODE)ORDER BY HRMS_LOCATION.LOCATION_CODE";

		String[] headers = { getMessage("cityhead"), getMessage("statehead"),
				getMessage("counthead") };
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
				+ " ,HRMS_LOCATION.LOCATION_CODE,state.LOCATION_CODE,country.LOCATION_CODE from HRMS_LOCATION inner join HRMS_LOCATION state on (state.LOCATION_CODE = HRMS_LOCATION.LOCATION_PARENT_CODE)"
				+ " inner join HRMS_LOCATION country on (country.LOCATION_CODE = state.LOCATION_PARENT_CODE) ORDER BY HRMS_LOCATION.LOCATION_CODE";

		String[] headers = { getMessage("cityhead"), getMessage("statehead"),
				getMessage("counthead") };
		String[] headerwidth = { "25", "35", "40" };
		String[] fieldNames = { "city1", "state1", "country1", "cityCode1",
				"stateCode1", "countryCode1" }; 
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 }; 
		String submitFlage = "";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9Quali() throws Exception {
		String query = " SELECT NVL(QUA_NAME,' '),NVL(QUA_ABBR,' '),CASE WHEN QUA_LEVEL='UG' THEN 'Under Graduate' "
				+ " WHEN QUA_LEVEL='DI' THEN 'Diploma' WHEN QUA_LEVEL='GR' THEN 'Graduate' WHEN QUA_LEVEL='PG' THEN 'Post Graduate'"
				+ " WHEN QUA_LEVEL='PH' THEN 'Phd' WHEN QUA_LEVEL='DO' THEN 'Doctorate' ELSE ' ' END ,QUA_ID FROM HRMS_QUA WHERE QUA_STATUS='A' ORDER BY QUA_ID";

		String[] headers = { getMessage("qualihead"), getMessage("quaname"),
				getMessage("qualilvlhead") };
		String[] headerwidth = { "40", "30", "30" };
		String[] fieldNames = { "qua" + candDB.getRowId(),
				"qualName" + candDB.getRowId(),
				"qualLevel" + candDB.getRowId(), "qualId" + candDB.getRowId() };
		int[] columnIndex = { 0, 1, 2, 3 };
		String submitFlage = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9Specialization() throws Exception {
		String query = " SELECT NVL(SPEC_NAME,' '),NVL(SPEC_ABBR,' '),SPEC_ID FROM HRMS_SPECIALIZATION where SPEC_STATUS='A' ORDER BY SPEC_ID";
		String[] headers = { getMessage("specn"), getMessage("spl") };
		String[] headerwidth = { "40", "30" };
		String[] fieldNames = { "spl" + candDB.getRowId(),
				"spelName" + candDB.getRowId(), "splId" + candDB.getRowId() };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlage = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";
	}

	public String f9IndExp() throws Exception {
		String query = " SELECT NVL(INDUS_NAME,' '),NVL(INDUS_ABBRIVIATION,' '),INDUS_CODE FROM HRMS_INDUS_TYPE where INDUS_STATUS='A' ORDER BY INDUS_CODE";
		String[] headers = { getMessage("indsname"), getMessage("indsabbr") };
		String[] headerwidth = { "40", "40" };
		String[] fieldNames = { "indsName" + candDB.getRowId(),
				"indType" + candDB.getRowId(), "indsId" + candDB.getRowId() };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlage = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";
	}

	public String f9JobExp() throws Exception {
		String query = " SELECT NVL(JOB_DESC_NAME,' ') FROM HRMS_JOB_DESCRIPTION WHERE JOB_DESC_STATUS='A' ";
		String[] headers = { getMessage("jobhead") };
		String[] headerwidth = { "50" };
		String[] fieldNames = { "jdExp" + candDB.getRowId() };
		int[] columnIndex = { 0 };
		String submitFlage = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";
	}

	public String f9Skill() throws Exception {
		String query = " SELECT NVL(SKILL_NAME,' '),SKILL_ID FROM HRMS_SKILL WHERE SKILL_STATUS='A' ORDER BY SKILL_ID";
		String[] headers = { getMessage("skillhead") };
		String[] headerwidth = { "40" };
		String[] fieldNames = { "skillName" + candDB.getRowId(),
				"skillCode" + candDB.getRowId() };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9Function() throws Exception {
		String query = " SELECT NVL(FUNC_DOMAIN_NAME,' '),FUNC_DOMAIN_CODE FROM HRMS_FUNC_DOMAIN_MASTER WHERE FUNC_DOMAIN_STATUS='A' ORDER BY FUNC_DOMAIN_CODE";
		String[] headers = { getMessage("domhead") };
		String[] headerwidth = { "50" };
		String fieldNames[] = { "funcName" + candDB.getRowId(),
				"funcCode" + candDB.getRowId() };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9refAction() throws Exception {
		String query = " SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC ORDER BY EMP_ID";
		String[] headers = { getMessage("refempId"), getMessage("refEmp") };
		String[] headerwidth = { "15", "35" };
		String fieldNames[] = { "refEmpTok", "refEmpName", "refEmpId" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9searchCand() throws Exception {
		String query = " SELECT CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,CAND_CODE FROM HRMS_REC_CAND_DATABANK ORDER BY CAND_CODE";
		String[] headers = { getMessage("cand.name") };
		String[] headerwidth = { "50" };
		String fieldNames[] = { "searchCandName", "searchCandId" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	/**
	 * following function is called when Export Pdf Report button is clicked to
	 * generate the report in Pdf Format
	 * 
	 * @return
	 */
	public String getReportInPdf() {
		try {
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			model.getReport(request, response, candDB, "Pdf");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * following function is called when Export Text Report button is clicked to
	 * generate the report in Pdf Format
	 * 
	 * @return
	 */
	public String getReportInText() {
		try {
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			model.getReport(request, response, candDB, "Txt");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * following function is called when search button is clicked.
	 * 
	 * @return
	 */
	public String searchCandDet() {
		try {
			if (candDB.getViewEditFlag().equals("true")) {
				candDB.setSearchFlag("false");
			} else {
				candDB.setSearchFlag("true");

			}
			candDB.setListFlag("true");
			getNavigationPanel(1);
			CandDataBankModel model = new CandDataBankModel();
			model.initiate(context, session);
			candDB.setPageFlag("true");
			String msg = getMessage("postingFrmDt");
			String msg1 = getMessage("postingToDt");
			String msg2 = getMessage("stat");
			String msg3 = getMessage("resSrc");
			model.showRecords(candDB, request, "", msg, msg1, msg2, msg3);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}