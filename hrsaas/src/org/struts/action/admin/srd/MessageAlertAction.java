package org.struts.action.admin.srd;

import java.util.HashMap;


import org.paradyne.bean.admin.srd.MessageAlert;


import org.paradyne.model.admin.srd.EmployeeCheckListModel;
import org.paradyne.model.admin.srd.MessageAlertModel;
import org.paradyne.model.admin.transfer.TransferApprovalModel;

 import org.struts.lib.ParaActionSupport;

public class MessageAlertAction extends ParaActionSupport {
	
	
	private MessageAlert msgAl;
	public MessageAlert getMsgAl() {
		return msgAl;
	}

	public void setMsgAl(MessageAlert msgAl) {
		this.msgAl = msgAl;
	}
	

	
	 public Object getModel() {

		return this.msgAl;
	}

	public String execute() throws Exception {
		
		return "success";
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		msgAl=new MessageAlert();
		try {
			MessageAlertModel model = new MessageAlertModel();
			model.initiate(context,session);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void prepare_withLoginProfileDetails() throws Exception {
		logger.info("***LOGINPROFILE***");
		MessageAlertModel model=new MessageAlertModel();
		model.initiate(context,session);
		msgAl.setMsgTo(msgAl.getUserEmpId());
		model.getEmpChkList(msgAl,request);
		model.terminate();
	}
	
	public String detail()throws Exception
	{
		MessageAlertModel model=new MessageAlertModel();
		model.initiate(context,session);
		String msgId = msgAl.getMsgCode();
		logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+msgId);
		model.getDetail(msgAl,msgId,request);
		model.terminate();
		return "sucessPage";
	}
	
	
	
	public String saveForApprove(){
		MessageAlertModel model = new MessageAlertModel();
		boolean iaApprove=false;
		model.initiate(context,session);
		String[] Ids=(String[])request.getParameterValues("chkSubmitTest");
		String[] flags=(String[])request.getParameterValues("msgId");

		for(int i=0;i<Ids.length;i++)
		{
			logger.info("&&&&&&&&&&&&&&&&&&&After Get San Id Values----------------------@@@@@@@@@"+Ids[i]);
			logger.info("&&&&&&&&&&&&&&&&&&&After Get San proposal code----------------------@@@@@@@@@"+flags[i]);
			
			if (Ids[i].equals("Y")) {
				iaApprove = model.saveByApprover(msgAl,flags[i]);
			}
			
		}
		if(iaApprove)
		{
			addActionError("Record Saved Successfully");
		//	msgAl.setMsgDate("");
		//	msgAl.setMsgDet("");
		//	msgAl.setMsgSub("");
		//	msgAl.setMsgTo("");
		//	msgAl.setMsgType("");
		//	msgAl.setMsgId("");
	
		}
		model.getEmpChkList(msgAl,request);
		model.terminate();
	
		return "success";
	}
	

}
