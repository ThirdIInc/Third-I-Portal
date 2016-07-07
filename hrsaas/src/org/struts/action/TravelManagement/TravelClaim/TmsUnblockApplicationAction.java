package org.struts.action.TravelManagement.TravelClaim;

import org.paradyne.bean.TravelManagement.TravelClaim.*;

import org.paradyne.model.TravelManagement.TravelClaim.*;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.struts.lib.ParaActionSupport;

public class TmsUnblockApplicationAction extends ParaActionSupport {
	TmsUnblockApplication unblockbean;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TmsUnblockApplicationAction.class);

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		unblockbean = new TmsUnblockApplication();
		unblockbean.setMenuCode(1124);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return unblockbean;
	}

	public TmsUnblockApplication getUnblockbean() {
		return unblockbean;
	}

	public void setUnblockbean(TmsUnblockApplication unblockbean) {
		this.unblockbean = unblockbean;
	}

	public String input() throws Exception {
		try {
			System.out.println("User employee ID : "
					+ unblockbean.getUserEmpId());
			TmsUnblockApplicationModel model = new TmsUnblockApplicationModel();
			model.initiate(context, session);
			model.getBlockedApplications(unblockbean, request, unblockbean
					.getUserEmpId());
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String approve() throws Exception {
		try {
			/*ReportingModel reptmodel = new ReportingModel();
			reptmodel.initiate(context, session);
			Object higherAuthObj[][] = reptmodel.generateEmpFlow(unblockbean
					.getUserEmpId(), "TYD", 2);
			String higherAuthId = "";			
			try {
				higherAuthId = String.valueOf(higherAuthObj[0][0]);
			} catch (Exception e) {
				// TODO: handle exception
			}*/
			TmsUnblockApplicationModel unblockAppmodel = new TmsUnblockApplicationModel();
			unblockAppmodel.initiate(context, session);
			String adminId = "";
			String higherAuthId = "";
			String adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID,AUTH_POLICY_VIOLN_ESCLN_ID  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
					+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ unblockbean.getUserEmpId().trim() + ")";// branch Id
			Object[][] adminObj = unblockAppmodel.getSqlModel().getSingleResult(
					adminQuery);

			if (adminObj != null && adminObj.length > 0) {
				adminId = String.valueOf(adminObj[0][0]);
				// higherAuthId = String.valueOf(adminObj[0][1]);
			}
			adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID,AUTH_POLICY_VIOLN_ESCLN_ID  FROM TMS_MANG_AUTH_HDR "
					+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch
			// Id
			adminObj = unblockAppmodel.getSqlModel().getSingleResult(adminQuery);
			if (adminObj != null && adminObj.length > 0) {
				adminId = String.valueOf(adminObj[0][0]);
				// higherAuthId = String.valueOf(adminObj[0][1]);
			}
			
			higherAuthId=adminId;
			
			String applicationid = request.getParameter("applicationid");			
			String applicationCode = request.getParameter("applicationCode");
			
			unblockAppmodel.terminate();
			
			TmsTrvlClmApprovalModel model = new TmsTrvlClmApprovalModel();
			model.initiate(context, session);
			String result;
			result = model.onlineUnblockRejectRequest(request, unblockbean
					.getUserEmpId(), applicationid, "A", "", higherAuthId, "",applicationCode);
			System.out.println("resultresultresultresult ======> "+result);
			if(result!=null)
			{
				addActionMessage("Application approved.");
			}
			else
			{
				addActionMessage("Application is not approved.");
			}
			
			MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			String module = "Travel Claim";
			processAlerts.removeProcessAlert(String.valueOf(applicationCode), module);

			
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return input();
	}
	
	public String reject() throws Exception {
		/*ReportingModel reptmodel = new ReportingModel();
		reptmodel.initiate(context, session);
		Object higherAuthObj[][] = reptmodel.generateEmpFlow(unblockbean
				.getUserEmpId(), "TYD", 2);
		String higherAuthId = "";
		
		try {
			higherAuthId = String.valueOf(higherAuthObj[0][0]);
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		
		TmsUnblockApplicationModel unblockAppmodel = new TmsUnblockApplicationModel();
		unblockAppmodel.initiate(context, session);
		String adminId = "";
		String higherAuthId = "";
		String adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID,AUTH_POLICY_VIOLN_ESCLN_ID  FROM TMS_MANG_AUTH_HDR "
				+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID IN "
				+ "(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="
				+ unblockbean.getUserEmpId().trim() + ")";// branch Id
		Object[][] adminObj = unblockAppmodel.getSqlModel().getSingleResult(
				adminQuery);

		if (adminObj != null && adminObj.length > 0) {
			adminId = String.valueOf(adminObj[0][0]);
			// higherAuthId = "(" + String.valueOf(adminObj[0][1]) +
			// ")";
		}
		adminQuery = "  SELECT  AUTH_MAIN_SCHL_ID,AUTH_POLICY_VIOLN_ESCLN_ID  FROM TMS_MANG_AUTH_HDR "
				+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch
		// Id
		adminObj = unblockAppmodel.getSqlModel().getSingleResult(adminQuery);
		if (adminObj != null && adminObj.length > 0) {
			adminId = String.valueOf(adminObj[0][0]);
			// higherAuthId = String.valueOf(adminObj[0][1]);
		}
		
		higherAuthId=adminId;
		
		
		String applicationid = request.getParameter("applicationid");		
		String applicationCode = request.getParameter("applicationCode");		
		TmsTrvlClmApprovalModel model = new TmsTrvlClmApprovalModel();
		model.initiate(context, session);
		
		String result;
		result = model.onlineUnblockRejectRequest(request, unblockbean.getUserEmpId(),
				applicationid, "R", "", higherAuthId, "",applicationCode);
		
		if(result!=null)
		{
			addActionMessage("Application rejected.");
		}
		else
		{
			addActionMessage("Error occured.");
		}
		
		
		MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
		processAlerts.initiate(context, session);
		String module = "Travel Claim";
		processAlerts.removeProcessAlert(String.valueOf(applicationCode), module);
		model.terminate();
		
		return input();
	}

}