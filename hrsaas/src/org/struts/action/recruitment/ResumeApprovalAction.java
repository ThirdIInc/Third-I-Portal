/**
 * @author Pradeep
 * Date 14/01/2009
 */
package org.struts.action.recruitment;

import org.apache.struts2.components.Bean;
import org.paradyne.bean.Recruitment.CandDataBank;
import org.paradyne.bean.Recruitment.EmployeeRequisition;
import org.paradyne.bean.Recruitment.ResumeApproval;
import org.paradyne.model.TravelManagement.TravelClaim.TmsTrvlClmApprovalModel;
import org.paradyne.model.recruitment.EmployeeRequisitionModel;
import org.paradyne.model.recruitment.ResumeApprovalModel;
import org.paradyne.model.recruitment.backGroundCheckModel;
import org.struts.lib.ParaActionSupport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

public class ResumeApprovalAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ResumeApprovalAction.class);
	ResumeApproval resumeApproval;

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub

		resumeApproval = new ResumeApproval();
		resumeApproval.setMenuCode(1103);

	}

	@Override
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
		resumeApproval.setDataPath(dataPath);
		resumeApproval.setPathPhoto(photoPath);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return resumeApproval;
	}

	public ResumeApproval getCandDB() {
		return resumeApproval;
	}

	public void setCandDB(ResumeApproval resumeApproval) {
		this.resumeApproval = resumeApproval;
	}

	public String input() {
		getNavigationPanel(1);
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		resumeApproval.setSearchCandId("");
		resumeApproval.setSearchCandName("");
		resumeApproval.setFromDate("");
		resumeApproval.setToDate("");
		resumeApproval.setExpYear("");
		resumeApproval.setExpMonth("");
		resumeApproval.setCandStatus("");
		resumeApproval.setResumeSrc("");
		resumeApproval.setViewEditFlag("false");
		// resumeApproval.setMyPage("");
		resumeApproval.setShow("");
		resumeApproval.setPageFlag("false");
		String path = getText("data_path");
		String msg = getMessage("postingFrmDt");
		String msg1 = getMessage("postingToDt");
		String msg2 = getMessage("stat");
		String msg3 = getMessage("resSrc");
		model.showRecords(resumeApproval, request, "", msg, msg1, msg2, msg3);
		
		//model.setBackgroundList(resumeApproval,"Y", request);
		
		resumeApproval.setCandCode("");
		resumeApproval.setListFlag("true");
		resumeApproval.setSearchFlag("false");
		model.terminate();
		return SUCCESS;
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
			// TODO Auto-generated catch block
			logger.error("-----in file not found catch", e);
			addActionMessage("Resume not found");
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

		getNavigationPanel(1);
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		String msg = getMessage("postingFrmDt");
		String msg1 = getMessage("postingToDt");
		String msg2 = getMessage("stat");
		String msg3 = getMessage("resSrc");
		model.showRecords(resumeApproval, request, "", msg, msg1, msg2, msg3);
		//model.setBackgroundList(resumeApproval,"Y", request);
		resumeApproval.setListFlag("true");
		model.terminate();
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
			resumeApproval.setListFlag("false");
			getNavigationPanel(2);
			resumeApproval.setMyPage("");
			ResumeApprovalModel model = new ResumeApprovalModel();
			model.initiate(context, session);
			model.addRowVac(resumeApproval);
			model.terminate();
			String photo = "null";
			request.setAttribute("photo", photo);
		} catch (Exception e) {
			logger.info("Exception in addNew method of EmployeeReqAction", e);
		}
		return SUCCESS;
	}

	public String saveFirst() {

		boolean result = false;
		boolean result1 = false;

		String returnString = "success";
		try {
			ResumeApprovalModel model = new ResumeApprovalModel();
			model.initiate(context, session);
			result = model.saveRecord(resumeApproval);
			// CandDataBank bean=(CandDataBank)candidateList.get(0);
			// if(("true").equals(bean.getDuplicateFlag()))
			if (!result) {

				if (resumeApproval.getDupChkFlag().equals("true")) {
					addActionMessage("Candidate with given details already exists in databank");
				}

				resumeApproval.setListFlag("false");
				if (resumeApproval.getPathPhoto() != null) {
					request.setAttribute("photo", resumeApproval.getPathPhoto());
				} else {
					request.setAttribute("photo",
							"../pages/images/employee/NoImage.jpg");
				}
				model.addRowVac(resumeApproval);
				getNavigationPanel(2);
				model.terminate();
				returnString = "success";
			} else {
				getNavigationPanel(3);

				if (resumeApproval.getPathPhoto() != null) {
					request.setAttribute("photo", resumeApproval.getPathPhoto());
				} else {
					request.setAttribute("photo",
							"../pages/images/employee/NoImage.jpg");
				}

				if (!resumeApproval.getCandCode().equals("")
						|| !resumeApproval.getCandCode().equals("null")) {
					if (resumeApproval.getDupChkFlag().equals("true")) {
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
								refContact, refComments, resumeApproval.getCandCode());
					}
				}
				if (result1) {

					String str = (String) session.getAttribute("session_pool");
					model.searchCandDet(resumeApproval, str, request);
					model.searchRefDtl(resumeApproval);
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
			resumeApproval.setListFlag("false");
			// getNavigationPanel(2);
			resumeApproval.setMyPage("");
			getNavigationPanel(3);
			ResumeApprovalModel model = new ResumeApprovalModel();
			model.initiate(context, session);
			String str = (String) session.getAttribute("session_pool");
			model.searchCandDet(resumeApproval, str, request);
			model.searchRefDtl(resumeApproval);
			resumeApproval.setUpdateFirst("true");
			resumeApproval.setUpdateSecond("true");
			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in searchRec method of CanaDataBankAction",
					e);
		}
		return "viewSearch";
		// return "success";
	}

	public String nextPageView() {
		try {
			getNavigationPanel(4);
			ResumeApprovalModel model = new ResumeApprovalModel();
			// resumeApproval.setCandCode("15");
			model.initiate(context, session);
			model.retCandQual(resumeApproval, request);
			model.retCandExp(resumeApproval, request);
			model.retCandSkill(resumeApproval, request);
			model.retCandFunc(resumeApproval, request);
			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in searchRec method of CanaDataBankAction",
					e);
		}
		return "viewSearch1";
	}

	public String updateFirst() {

		String[] refName = request.getParameterValues("refName");
		String[] profession = request.getParameterValues("profession");
		String[] refContact = request.getParameterValues("contactDet");
		String[] refComments = request.getParameterValues("refComment");
		ResumeApprovalModel model = new ResumeApprovalModel();
		// resumeApproval.setCandCode("15");
		model.initiate(context, session);
		// ArrayList<Object> candidateList
		boolean result = model.updateFirst(resumeApproval);
		// CandDataBank bean=(CandDataBank)candidateList.get(0);
		// if(("true").equals(bean.getDuplicateFlag()))
		if (!result) {

			if (resumeApproval.getDupChkFlag().equals("true")) {
				addActionMessage("Candidate with given details already exists in databank");
			}

			resumeApproval.setListFlag("false");
			if (resumeApproval.getPathPhoto() != null) {
				request.setAttribute("photo", resumeApproval.getPathPhoto());
			} else {
				request.setAttribute("photo", "../images/employee/NoImage.jpg");
			}
			getNavigationPanel(2);
			return "success";
		} else {
			getNavigationPanel(3);
			model.saveRefDet(refName, profession, refContact, refComments,
					resumeApproval.getCandCode());
			String str = (String) session.getAttribute("session_pool");
			model.searchCandDet(resumeApproval, str, request);
			model.searchRefDtl(resumeApproval);
			model.terminate();
			// if(("true").equals(bean.getResult())){
			addActionMessage("Record updated successfully.");
			// }
			return "viewSearch";
		}
	}

	public String previous() {
		getNavigationPanel(3);
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		String str = (String) session.getAttribute("session_pool");
		model.searchCandDet(resumeApproval, str, request);
		model.searchRefDtl(resumeApproval);
		model.terminate();
		return "viewSearch";
	}

	public String deleteRec() {
		getNavigationPanel(1);
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		boolean result = model.deleteRecord(resumeApproval);
		if (result) {
			addActionMessage("Record deleted successfully.");
		} else {
			addActionMessage("This record is referenced in other resources.\nSo can't be deleted.");
		}
		String msg = getMessage("postingFrmDt");
		String msg1 = getMessage("postingToDt");
		String msg2 = getMessage("stat");
		String msg3 = getMessage("resSrc");
		model.showRecords(resumeApproval, request, "", msg, msg1, msg2, msg3);
		// model.showRecords(resumeApproval,request,"");
		resumeApproval.setListFlag("true");
		model.terminate();
		return "success";
	}

	public String updateSecond() {
		getNavigationPanel(4);
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		model.delQuali(resumeApproval.getCandCode());
		saveQual(resumeApproval.getCandCode(), request);
		model.delExp(resumeApproval.getCandCode());
		saveExp(resumeApproval.getCandCode(), request);
		model.delSkill(resumeApproval.getCandCode());
		saveSkill(resumeApproval.getCandCode(), request);
		model.delFun(resumeApproval.getCandCode());
		saveFunc(resumeApproval.getCandCode(), request);
		model.retCandQual(resumeApproval, request);
		model.retCandExp(resumeApproval, request);
		model.retCandSkill(resumeApproval, request);
		model.retCandFunc(resumeApproval, request);
		addActionMessage("Record updated successfully.");
		model.terminate();
		return "viewSearch1";
	}

	public String editFirst() {
		getNavigationPanel(2);
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		String str = (String) session.getAttribute("session_pool");
		model.searchCandDet(resumeApproval, str, request);
		model.searchRefDtl(resumeApproval);
		if (resumeApproval.getRefFlag().equals("false")) {
			model.addRowVac(resumeApproval);
		}
		resumeApproval.setCancelFirst("true");
		resumeApproval.setUpdateFirst("true");
		resumeApproval.setUpdateSecond("true");
		model.terminate();
		return "success";
	}

	/**
	 * following function is called when any row is double clicked in the list
	 * the corresponding record will be set.
	 * 
	 * @return
	 */
	public String callForEdit() {
		resumeApproval.setListFlag("false");
		// getNavigationPanel(2);
		getNavigationPanel(3);
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		String str = (String) session.getAttribute("session_pool");
		resumeApproval.setCandCode(resumeApproval.getHiddenCode());
		model.searchCandDet(resumeApproval, str, request);
		model.searchRefDtl(resumeApproval);
		if (resumeApproval.getRefFlag().equals("false")) {
			model.addRowVac(resumeApproval);
		}
		// resumeApproval.setCancelFirst("true");
		resumeApproval.setUpdateFirst("true");
		resumeApproval.setUpdateSecond("true");
		model.terminate();
		return "viewSearch";
		// return "success";
	}

	public String cancelFirst() {
		getNavigationPanel(3);
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		String str = (String) session.getAttribute("session_pool");
		model.searchCandDet(resumeApproval, str, request);
		model.searchRefDtl(resumeApproval);
		model.terminate();
		return "viewSearch";
	}

	public String nextPage() {

		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);

		if (resumeApproval.getCandCode().equals("")
				|| resumeApproval.getCandCode().equals("null")) {
			if (("true").equals(resumeApproval.getDupChkFlag())) {
				addActionMessage("Candidate with given details already exists in databank");

			} else {
				saveFirst();
				model.addRowQual(resumeApproval, request);
				model.addRowExp(resumeApproval, request);
				model.addRowSkill(resumeApproval, request);
				model.addRowFunc(resumeApproval, request);
			}
		} else {

			updateFirst();

			resumeApproval.setUpdateSecond("true");

			resumeApproval.setUpdateSecond("true");
			model.retCandQual(resumeApproval, request);
			model.retCandExp(resumeApproval, request);
			model.retCandSkill(resumeApproval, request);
			model.retCandFunc(resumeApproval, request);

			if (resumeApproval.getQualiFlag().equals("false")) {
				model.addRowQual(resumeApproval, request);
			}

			if (resumeApproval.getExpFlag().equals("false")) {
				model.addRowExp(resumeApproval, request);
			}
			if (resumeApproval.getSkillFlag().equals("false")) {
				model.addRowSkill(resumeApproval, request);
			}
			if (resumeApproval.getDomFlag().equals("false")) {
				model.addRowFunc(resumeApproval, request);
			}

		}

		if (resumeApproval.getDupChkFlag().equals("true")) {
			model.terminate();
			getNavigationPanel(2);
			return "success";
		} else {
			model.terminate();
			getNavigationPanel(7);
			return "next";
		}

	}

	public String saveAndPrev() {
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		if (resumeApproval.getUpdateSecond().equals("true")) {
			updateSecond();
			resumeApproval.setUpdateFirst("true");
		} else {
			saveSecond();
			resumeApproval.setUpdateFirst("true");
		}
		String str = (String) session.getAttribute("session_pool");
		model.searchCandDet(resumeApproval, str, request);
		model.searchRefDtl(resumeApproval);
		if (resumeApproval.getRefFlag().equals("false")) {
			model.addRowVac(resumeApproval);
		}
		model.terminate();
		getNavigationPanel(2);
		return "success";
	}

	public String saveSecond() {
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		getNavigationPanel(4);
		// resumeApproval.setCandCode("10");
		model.delQuali(resumeApproval.getCandCode());
		saveQual(resumeApproval.getCandCode(), request);
		model.delExp(resumeApproval.getCandCode());
		saveExp(resumeApproval.getCandCode(), request);
		model.delSkill(resumeApproval.getCandCode());
		saveSkill(resumeApproval.getCandCode(), request);
		model.delFun(resumeApproval.getCandCode());
		saveFunc(resumeApproval.getCandCode(), request);
		model.terminate();
		addActionMessage("Record updated successfully.");
		return "saveSecond";
	}

	public String cancelSec() {
		getNavigationPanel(4);
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		model.retCandQual(resumeApproval, request);
		model.retCandExp(resumeApproval, request);
		model.retCandSkill(resumeApproval, request);
		model.retCandFunc(resumeApproval, request);
		model.terminate();
		return "viewSearch1";
	}

	public String editSec() {
		getNavigationPanel(7);
		ResumeApprovalModel model = new ResumeApprovalModel();

		// model.retCandExp(resumeApproval, request);
		resumeApproval.setUpdateSecond("true");
		resumeApproval.setCancelSecond("true");
		model.initiate(context, session);
		model.retCandQual(resumeApproval, request);
		model.retCandExp(resumeApproval, request);
		model.retCandSkill(resumeApproval, request);
		model.retCandFunc(resumeApproval, request);
		if (resumeApproval.getQualiFlag().equals("false")) {
			model.addRowQual(resumeApproval, request);
		}

		if (resumeApproval.getExpFlag().equals("false")) {
			model.addRowExp(resumeApproval, request);
		}
		if (resumeApproval.getSkillFlag().equals("false")) {
			model.addRowSkill(resumeApproval, request);
		}
		if (resumeApproval.getDomFlag().equals("false")) {
			model.addRowFunc(resumeApproval, request);
		}
		model.terminate();
		return "next";
	}

	public void saveQual(String candCode, HttpServletRequest request) {
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		String[] estbName = request.getParameterValues("estbName");
		String[] yearofPass = request.getParameterValues("yearofPass");

		if (estbName != null) {
			int j = 1;
			if (estbName.length > 0) {

				for (int i = 0; i < estbName.length; i++) {
					String code = (String) request.getParameter("qualId" + j);
					if (!(code.equals("") || code.equals("null") || code
							.equals(" "))) {
						Object[][] qualObj = new Object[1][6];
						qualObj[0][0] = candCode;
						qualObj[0][1] = code;// (String)request.getParameter("qualId"+j);
						qualObj[0][2] = (String) request.getParameter("splId"
								+ j);
						qualObj[0][3] = estbName[i];
						qualObj[0][4] = yearofPass[i];
						qualObj[0][5] = "";
						model.saveQual(qualObj, candCode);
					}
					j++;
				}

			}
		}// else{
		// model.delQuali(candCode);
		// }
		model.terminate();
	}

	public String deleteFunc() throws Exception {
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

			ResumeApprovalModel model = new ResumeApprovalModel();
			model.initiate(context, session);
			model.delFunc(resumeApproval, exp, detCode, code, name, del, request);
			getQuaList();
			getExpDet();
			getSkillList();
			model.terminate();

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
			ResumeApprovalModel model = new ResumeApprovalModel();
			model.initiate(context, session);

			// Object[][] data=model.checkStatus(manPowerReqs,
			// manPowerReqs.getReqCode());
			// if(data!=null && data.length>0){
			// if(!(String.valueOf(data[0][0]).equals("B") ||
			// String.valueOf(data[0][0]).equals("R"))){
			// addActionMessage("Record can't be deleted as the application has
			// been approved.");
			// getQuaList();
			// getDomain();
			// getSkill();
			// getCert();
			// }else{
			model.delSkill(resumeApproval, exp, detCode, code, name, del, request);
			// getDomain();
			// getSkill();
			// getCert();
			// }
			// }
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
				// if(String.valueOf(hdel[i]).equals("Y")){
				// if(!(detCode[i].equals("") || detCode[i].equals(" ") ||
				// detCode[i].equals("null"))){
				// Object [][]del=new Object[1][2];
				// del[0][0]=detCode[i];
				// del[0][1]=bean.getReqCode();
				// getSqlModel().singleExecute(getQuery(13),del);
				// }
				//
				// }else

				bean.setFuncExp(exp[i]);
				bean.setFuncDtlId(detCode[i]);
				funcObj[i][0] = name[i];
				funcObj[i][1] = code[i];
				tableList.add(bean);

			}
			resumeApproval.setFuncList(tableList);
			request.setAttribute("funcObj", funcObj);
		}
		return "next";
	}

	public String getSkillList() {
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
				// if(String.valueOf(hdel[i]).equals("Y")){
				// if(!(detCode[i].equals("") || detCode[i].equals(" ") ||
				// detCode[i].equals("null"))){
				// Object [][]del=new Object[1][2];
				// del[0][0]=detCode[i];
				// del[0][1]=bean.getReqCode();
				// getSqlModel().singleExecute(getQuery(13),del);
				// }
				//
				// }else

				bean.setSkillExp(exp[i]);
				bean.setSkillDtlId(detCode[i]);
				skillObj[i][0] = name[i];
				skillObj[i][1] = code[i];
				tableList.add(bean);

			}
			resumeApproval.setSkillList(tableList);
			request.setAttribute("skillObj", skillObj);
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

				ResumeApprovalModel model = new ResumeApprovalModel();
				model.initiate(context, session);

				// Object[][] data=model.checkStatus(manPowerReqs,
				// manPowerReqs.getReqCode());
				// if(data!=null && data.length>0){
				// if(!(String.valueOf(data[0][0]).equals("B") ||
				// String.valueOf(data[0][0]).equals("R"))){
				// addActionMessage("Record can't be deleted as the application
				// has been approved.");
				// getQuaList();
				// getDomain();
				// getSkill();
				// getCert();
				// }else{
				model.delExp(resumeApproval, employer, profession, joiningDate,
						relieving, splAch, ctc, detCode, indsType, indCode,
						jobDescr, del, request);
				// getDomain();
				// getSkill();
				// getCert();
				// }
				// }
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
			resumeApproval.setExpList(tableList);
			request.setAttribute("expObj", expObj);
		}

		return "next";
	}

	public String deleteRowRef() throws Exception {
		getNavigationPanel(2);// getWheYouEmpgetRelocategetDoYou
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
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		model.delRef(resumeApproval, refNa, prof, contact, comment, code, del, request);
		String str = "";
		if (!(resumeApproval.getCandCode().equals("") || resumeApproval.getCandCode().equals(
				"null"))) {
			model.searchCandDet(resumeApproval, str, request);
		} else {
			request.setAttribute("photo", str);
		}
		if (resumeApproval.getDoYou().equals("Y")) {
			resumeApproval.setRadioFlag("true");
		} else if (resumeApproval.getDoYou().equals("N")) {
			resumeApproval.setRadioFlag("false");
		}

		if (resumeApproval.getWheYouEmp().equals("Y")) {
			resumeApproval.setRadioFlag1("true");
		} else if (resumeApproval.getWheYouEmp().equals("N")) {
			resumeApproval.setRadioFlag1("false");
		}

		if (resumeApproval.getRelocate().equals("Y")) {
			resumeApproval.setRadioFlag2("true");
		} else if (resumeApproval.getRelocate().equals("N")) {
			resumeApproval.setRadioFlag2("false");
		}
		model.terminate();

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

		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);

		// Object[][] data=model.checkStatus(manPowerReqs,
		// manPowerReqs.getReqCode());
		// if(data!=null && data.length>0){
		// if(!(String.valueOf(data[0][0]).equals("B") ||
		// String.valueOf(data[0][0]).equals("R"))){
		// addActionMessage("Record can't be deleted as the application has been
		// approved.");
		// getQuaList();
		// getDomain();
		// getSkill();
		// getCert();
		// }else{
		model.delQual(resumeApproval, name, code, lvl, spN, spCode, del, year, est,
				detCode, request);
		// getDomain();
		// getSkill();
		// getCert();
		// }
		// }
		getExpDet();
		getSkillList();
		getFuncList();
		model.terminate();

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
				resumeApproval.setQualList(quaList);
				request.setAttribute("qualObj", qualObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "next";
	}

	public void saveSkill(String candCode, HttpServletRequest request) {
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		String[] skillExp = request.getParameterValues("skillExp");
		if (skillExp != null) {
			int j = 1;
			if (skillExp.length > 0) {

				for (int i = 0; i < skillExp.length; i++) {
					String code = (String) request
							.getParameter("skillCode" + j);
					if (!(code.equals("") || code.equals("null") || code
							.equals(" "))) {
						Object[][] skillObj = new Object[1][4];
						skillObj[0][0] = candCode;
						skillObj[0][1] = "S";
						skillObj[0][2] = code;// (String)request.getParameter("skillCode"+j);
						skillObj[0][3] = skillExp[i];
						model.saveSkill(skillObj, candCode);
					}
					j++;
				}

			}
		}// else{
		// / model.delSkill(candCode);
		// }
		model.terminate();
	}

	public void saveExp(String candCode, HttpServletRequest request) {
		try {
			ResumeApprovalModel model = new ResumeApprovalModel();
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
							expObj[0][5] = jobExp[i];// (String)request.getParameter("jdExp");
							expObj[0][6] = (String) request
									.getParameter("indsId" + j);
							expObj[0][7] = specAch[i];
							expObj[0][8] = ctcExp[i];
							model.saveExp(expObj, candCode);
						}
						j++;

					}

				}
			}// else{
			// model.delExp(candCode);
			// }
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveFunc(String candCode, HttpServletRequest request) {
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		String[] funcExp = request.getParameterValues("funcExp");
		if (funcExp != null) {
			int j = 1;
			if (funcExp.length > 0) {

				for (int i = 0; i < funcExp.length; i++) {
					String code = (String) request.getParameter("funcCode" + j);
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
		}// else{
		// model.delFun(candCode);
		// }
		model.terminate();
	}

	// f9cityaction1
	public String f9search() throws Exception {
		// String query = "SELECT nvl(CAND_FIRST_NAME||' '||CAND_MID_NAME||'
		// '||CAND_LAST_NAME,''),nvl(TO_CHAR(CAND_DOB,'dd-mm-yyyy'),'
		// '),nvl(CAND_EXP_YEAR,0) ||' Year '||nvl(CAND_EXP_MONTH,0)||'
		// Months'," +
		// "nvl(TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY'),'
		// '),nvl(CAND_SHORT_STATUS,' '),CAND_CODE FROM HRMS_REC_CAND_DATABANK
		// ";
		String query = "SELECT nvl(CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,''),nvl(CAND_EXP_YEAR,0) ||' Year '|| nvl(CAND_EXP_MONTH,0)||' Months',"
				+ "nvl(TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY'),' '),CAND_CODE FROM HRMS_REC_CAND_DATABANK WHERE CAND_RESUME_APPROVAL_FLAG = 'Y' ORDER BY CAND_POSTING_DATE DESC";

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
		// +" state.LOCATION_CODE,country.LOCATION_CODE from HRMS_LOCATION "
				+ " ,HRMS_LOCATION.LOCATION_CODE,state.LOCATION_CODE,country.LOCATION_CODE from HRMS_LOCATION inner join HRMS_LOCATION state on (state.LOCATION_CODE = HRMS_LOCATION.LOCATION_PARENT_CODE)"
				+ " inner join HRMS_LOCATION country on (country.LOCATION_CODE = state.LOCATION_PARENT_CODE) ORDER BY HRMS_LOCATION.LOCATION_CODE";

		String[] headers = { getMessage("cityhead"), getMessage("statehead"),
				getMessage("counthead") };
		String[] headerwidth = { "25", "35", "40" };
		String[] fieldNames = { "city1", "state1", "country1", "cityCode1",
				"stateCode1", "countryCode1" };// ," countryCode1"};
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };// ,3,4,5};
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
		String[] fieldNames = { "qua" + resumeApproval.getRowId(),
				"qualName" + resumeApproval.getRowId(),
				"qualLevel" + resumeApproval.getRowId(), "qualId" + resumeApproval.getRowId() };
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
		String[] fieldNames = { "spl" + resumeApproval.getRowId(),
				"spelName" + resumeApproval.getRowId(), "splId" + resumeApproval.getRowId() };
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
		String[] fieldNames = { "indsName" + resumeApproval.getRowId(),
				"indType" + resumeApproval.getRowId(), "indsId" + resumeApproval.getRowId() };
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
		String[] fieldNames = { "jdExp" + resumeApproval.getRowId() };
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

		String[] fieldNames = { "skillName" + resumeApproval.getRowId(),
				"skillCode" + resumeApproval.getRowId() };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	public String f9Function() throws Exception {

		String query = " SELECT NVL(FUNC_DOMAIN_NAME,' '),FUNC_DOMAIN_CODE FROM HRMS_FUNC_DOMAIN_MASTER WHERE FUNC_DOMAIN_STATUS='A' ORDER BY FUNC_DOMAIN_CODE";
		System.out.println("pppppp");
		String[] headers = { getMessage("domhead") };
		String[] headerwidth = { "50" };

		String fieldNames[] = { "funcName" + resumeApproval.getRowId(),
				"funcCode" + resumeApproval.getRowId() };
		// String fieldNames[]={request.getParameter("field")};
		int[] columnIndex = { 0, 1 };

		String submitFlage = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";

	}

	public String f9refAction() throws Exception {

		String query = " SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC ORDER BY EMP_ID";
		System.out.println("pppppp");
		String[] headers = { getMessage("refempId"), getMessage("refEmp") };
		String[] headerwidth = { "15", "35" };

		String fieldNames[] = { "refEmpTok", "refEmpName", "refEmpId" };
		// String fieldNames[]={request.getParameter("field")};
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
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		model.getReport(request, response, resumeApproval, "Pdf");
		model.terminate();
		return null;
	}

	/**
	 * following function is called when Export Text Report button is clicked to
	 * generate the report in Pdf Format
	 * 
	 * @return
	 */
	public String getReportInText() {
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		model.getReport(request, response, resumeApproval, "Txt");
		model.terminate();
		return null;
	}

	/**
	 * following function is called when search button is clicked.
	 * @return
	 */
	public String searchCandDet() {

		if (resumeApproval.getViewEditFlag().equals("true")) {
			resumeApproval.setSearchFlag("false");
		} else {
			resumeApproval.setSearchFlag("true");

		}

		resumeApproval.setListFlag("true");

		getNavigationPanel(1);
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		resumeApproval.setPageFlag("true");
		String msg = getMessage("postingFrmDt");
		String msg1 = getMessage("postingToDt");
		String msg2 = getMessage("stat");
		String msg3 = getMessage("resSrc");
		model.showRecords(resumeApproval, request, "", msg, msg1, msg2, msg3);
		//model.showRecords(resumeApproval, request,"");
		model.terminate();
		return SUCCESS;
	}

	public String doFunction() {
		
		ResumeApprovalModel model = new ResumeApprovalModel();
		model.initiate(context, session);
		
		String msgFlag = resumeApproval.getStatusSave();
		String status = resumeApproval.getCheckStatus().trim();
		String CandCode = resumeApproval.getCandCode().trim();
		boolean result = model.insertApprovalStatus(CandCode, status, request);
		
		if (result) {
			
			if (status.equals("Y")) {
				addActionMessage("Application approved successfully");
			} else if (status.equals("R")) {
				addActionMessage("Application Rejected successfully");
			} 
			
		} else {
			addActionMessage("Application can't be saved");

		}
		
		
		
		// imp ....set the status here....
		
		return input();

	}
	
	public String callstatus(){  //retrieving application details 
		try {
			getNavigationPanel(1);
			ResumeApprovalModel model = new ResumeApprovalModel();
			model.initiate(context, session);
			
			String status = request.getParameter("status");
		
		/*	if (status.equals("R")) {
				
				System.out.println(" IN callstatus IF RRRRRRRRRR----------" + status);
				
				resumeApproval.setCheckStatus("R");
				status = "R";
				resumeApproval.setConduct("true");

			} else {
				System.out.println(" IN callstatus IF YYYYYYYY----------" + status);
				
				resumeApproval.setCheckStatus("Y");
				status = "Y";
				resumeApproval.setConduct("false");
			}*/
			//resumeApproval.setButtonpanelcode("1");
			
			model.setBackgroundList(resumeApproval, status,request);
			request.setAttribute("stat", status);
			
			resumeApproval.setListFlag("true");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return input();
	}
	
	
}