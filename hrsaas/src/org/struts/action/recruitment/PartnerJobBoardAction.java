package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.PartnerJobBoard;
import org.paradyne.model.recruitment.PartnerJobBoardModel;

/**
 * aa0417
 Jul 4, 2009
 */
public class PartnerJobBoardAction extends PartnerActionSupport {
	PartnerJobBoard jobBoard;

	public void prepare_local() throws Exception {
		jobBoard = new PartnerJobBoard();
		jobBoard.setMenuCode(907);
	}

	public void prepare_withLoginProfileDetails() throws Exception {

	}

	public Object getModel() {
		return jobBoard;
	}

	public PartnerJobBoard getJobBoard() {
		return jobBoard;
	}

	public void setJobBoard(PartnerJobBoard jobBoard) {
		this.jobBoard = jobBoard;
	}

	public String input() {
		try {
			PartnerJobBoardModel model = new PartnerJobBoardModel();
			model.initiate(context, session);
			model.callPartnerJobBorad(jobBoard, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String viewReqDetails() {
		try {
			jobBoard.setFlagView("true");
			jobBoard.setPageNumber(request.getParameter("page"));
			jobBoard.setReqCode(request.getParameter("reqCode"));
			jobBoard.setFormAction(request.getParameter("formAction"));
			jobBoard.setStatusKey(request.getParameter("statusKey"));
			jobBoard.setFormFlag("true");
			jobBoard.setViewforEmployeeRequisition("false");
			PartnerJobBoardModel model = new PartnerJobBoardModel();
			model.initiate(context, session);
			model.searchHdrRec(jobBoard, request);
			model.searchVacDtl(jobBoard);
			model.getApprovalDtls(jobBoard);
			model.checkStatus(jobBoard, jobBoard.getReqCode());
		} catch (Exception e) {
			logger.info("exception in viewReqDetails : " + e);
		}
		return "viewFirst";
	}
}
