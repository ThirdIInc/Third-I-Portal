package org.struts.action.event;

import org.paradyne.bean.event.CeoMessageList;
import org.paradyne.model.event.CeoMessageListModel;
import org.paradyne.model.leave.LeaveApprovalModel;
import org.struts.lib.ParaActionSupport;

public class CeoMessageListAction extends ParaActionSupport {

	CeoMessageList bean;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(CeoMessageListAction.class);
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new CeoMessageList();
		bean.setMenuCode(1173);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public CeoMessageList getBean() {
		return bean;
	}

	public void setBean(CeoMessageList bean) {
		this.bean = bean;
	}

	public String callstatus() {

		try {
			System.out.println("hi------------");
			
			CeoMessageListModel model = new CeoMessageListModel();
			model.initiate(context, session);
			System.out.println("hi------------");
			model.getAllTypeOfMessages(bean, request, bean.getUserEmpId());
			bean.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
	public String getApprovedList() throws Exception {
		 
		try {
			CeoMessageListModel model = new CeoMessageListModel();
			model.initiate(context, session);
			model.getApprovedList(bean, request, bean
					.getUserEmpId());
			bean.setListType("approved");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		return SUCCESS;
	}
	
	public String retriveForApproval() throws Exception {
		 
		try {
			CeoMessageListModel model = new CeoMessageListModel();
			model.initiate(context, session);
			String messageNo = request.getParameter("messageNo");
			String status = request.getParameter("messageStatus");
			String empCode = request.getParameter("empCode");
			
			System.out.println("status         "+status);
			System.out.println("empCode         "+empCode);
			
			if(status.equals("P"))
			{
				bean.setShowFlag(true);
			}
			else{
				bean.setShowFlag(false);
			}
			
			model.getDetails(bean,messageNo,empCode);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		return "view";
	}
	
	
	public String getRejectedList() throws Exception {
		 
		try {
			CeoMessageListModel model = new CeoMessageListModel();
			model.initiate(context, session);
			model.getRejectedList(bean, request, bean
					.getUserEmpId());
			bean.setListType("rejected");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getRejectedList------" + e);
		}
		return SUCCESS;
	}
	
	public String acceptRejectMessage() throws Exception {
		 
		try {
			CeoMessageListModel model = new CeoMessageListModel();
			model.initiate(context, session);
			boolean result = model.acceptRejectMessage(bean);
			if(result)
			{
				if(bean.getCheckAcceptRejectStatus().equals("A"))
				{
					addActionMessage("Message accepted successfully");
				}
				if(bean.getCheckAcceptRejectStatus().equals("R"))
				{
					addActionMessage("Message rejected ");
				}
			}
			model.getAllTypeOfMessages(bean, request, bean.getUserEmpId());
			bean.setListType("pending");
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in getRejectedList------" + e);
		}
		return SUCCESS;
	}
	
	

}
