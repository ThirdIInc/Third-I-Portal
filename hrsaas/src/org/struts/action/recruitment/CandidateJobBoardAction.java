/**
 * 
 */
package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.EmployeeRequisition;
import org.paradyne.model.recruitment.CandidateJobBoardModel;
import org.paradyne.model.recruitment.EmployeeRequisitionModel;
import org.struts.lib.DyneActionSupport;

/**
 * @author AA0517
 * 
 */
public class CandidateJobBoardAction extends DyneActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.DyneActionSupport.class);

	EmployeeRequisition manPowerReqs;

	public void prepare_local() throws Exception {
		manPowerReqs = new EmployeeRequisition();
		manPowerReqs.setMenuCode(899);
	}

	public Object getModel() {
		return manPowerReqs;
	}

	public EmployeeRequisition getManPowerReqs() {
		return manPowerReqs;
	}

	public void setManPowerReqs(EmployeeRequisition manPowerReqs) {
		this.manPowerReqs = manPowerReqs;
	}

	/**
	 * this method is to retrieve all open requisitions data on load.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			CandidateJobBoardModel model = new CandidateJobBoardModel();
			model.initiate(context, session);
			model.getRecord(manPowerReqs, request);
			manPowerReqs.setApplyFlag("false");
			manPowerReqs.setCheckUploadProfile(model
					.checkProfileUpdate(manPowerReqs));
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getJobNameDetails()
	{
		try {
			CandidateJobBoardModel model = new CandidateJobBoardModel();
			model.initiate(context, session);
			String reqCodeStr =request.getParameter("reqCodeStr");
			System.out.println("reqCodeStr            "+reqCodeStr);
			Object[][]reqDtlObj =model.getJobNameDetails(manPowerReqs, request,reqCodeStr);
			request.setAttribute("reqDtlObj", reqDtlObj);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "jobNameDtlJsp";
	}

	public String dashLetviewReqDetails1() { // for Dashlet view Requisition
		try {
			manPowerReqs.setFlagView("true");
			manPowerReqs.setReqCode(request.getParameter("reqCode"));
			// manPowerReqs.setFormAction(request.getParameter("formAction"));
			// manPowerReqs.setStatusKey(request.getParameter("statusKey"));
			manPowerReqs.setFormFlag("true");
			manPowerReqs.setDashletFlag("true");
			EmployeeRequisitionModel model = new EmployeeRequisitionModel();
			model.initiate(context, session);
			model.searchHdrRec(manPowerReqs, manPowerReqs.getReqCode(),  request);
			model.searchVacDtl(manPowerReqs);
			model.getApprovalDtls(manPowerReqs);
			model.checkStatus(manPowerReqs, manPowerReqs.getReqCode());
			model.searchQualiDtlInView(manPowerReqs, manPowerReqs.getReqCode(), request);
			model.searchSkillDtlInView(manPowerReqs, manPowerReqs.getReqCode(), request);
			model.searchDomainDtlInView(manPowerReqs, manPowerReqs.getReqCode(), request);
			model.searchCertDtlInView(manPowerReqs, manPowerReqs.getReqCode(), request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "candJobBoardViewReq";
	}

	public String sourceOfResume() throws Exception {
		try {
			
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if(poolName != null && poolName.equals("pool_sagepublications")){
				manPowerReqs.setCheckFlag("true");
			}
			String requisitionCode = request.getParameter("requisitionCode");
			CandidateJobBoardModel model = new CandidateJobBoardModel();
			model.initiate(context, session);
			manPowerReqs.setReqCodeToApply(requisitionCode);
			model.sourceOfResume(requisitionCode, manPowerReqs);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "scopeOfResume";
	}

	public String applyForJob() throws Exception {
		try {
			CandidateJobBoardModel model = new CandidateJobBoardModel();
			model.initiate(context, session);
			String reqCode = manPowerReqs.getReqCodeToApply();
			String result = model.applyForJob(manPowerReqs, reqCode);
			addActionMessage(result);
			prepare_withLoginProfileDetails();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	} 

}
