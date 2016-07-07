/**
 * 
 */
package org.struts.action.leave;

import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.model.admin.srd.BioDataModel;
import org.paradyne.model.leave.LeaveApplicationModel;
import org.paradyne.model.leave.LeaveApprovalModel;
 import org.struts.lib.ParaActionSupport;

/**
 * @author sunil
 *
 */
public class LeaveDCEListAction extends ParaActionSupport {

	LeaveApplication leaveDce ;
	
	
	public LeaveApplication getLeaveDce() {
		return leaveDce;
	}

	public void setLeaveDce(LeaveApplication leaveDce) {
		this.leaveDce = leaveDce;
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		leaveDce = new LeaveApplication();
		leaveDce.setMenuCode(215);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	 public Object getModel() {

		return leaveDce;
	}
	
	//Added by Pradeep on Date 13.11.2007
	 public void report(){
		 LeaveApprovalModel model=new LeaveApprovalModel();
		 model.initiate(context,session);
		 model.generateReport(leaveDce,response);
		 model.terminate();
	 }
	 //Following method is called to display the record upon selecting the Approved or Cancelled from the combo box in leaveDCE.jsp page 
	//Modified on Date 20.11.2007 by Pradeep
	 public String dceLIst() throws Exception {
		try{
			LeaveApprovalModel model = new LeaveApprovalModel();
			model.initiate(context,session);
			
			String query =" SELECT LEAVE_APPL_CODE,HRMS_EMP_OFFC.EMP_TOKEN, "
				+" HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, " 
				+" NVL(TO_CHAR(LEAVE_APPL_DATE,'DD-MM-YYYY'),' '), "
				+" DECODE(LEAVE_STATUS,'C','Recomended','A','Approved','P','Pending','N','Cancelled') "
				+" FROM HRMS_LEAVE_HDR " 
				+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LEAVE_HDR.EMP_ID) "
				+" LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE   "
			//	+" WHERE LEAVE_STATUS= IN ('A') AND LEAVE_DCE_LIST_NO IS NULL " ;
				+" WHERE LEAVE_STATUS="+"'"+leaveDce.getStatus()+"'"+" AND LEAVE_DCE_LIST_NO IS NULL " ;
			
				if (!leaveDce.getUserProfileCenters().equals("")) {			
					query +=" AND HRMS_EMP_OFFC.EMP_CENTER IN("+leaveDce.getUserProfileCenters()+")" ;
				}
				
			model.generateListForDCE(leaveDce,query);
			model.terminate();
			}catch (Exception e) {	
				
			}
		return "success";
	}
	public String gerateDCENo() throws Exception {
		LeaveApprovalModel model = new LeaveApprovalModel();
		model.initiate(context,session);
		model.gerateDceNo(leaveDce);
		model.terminate();
		return dceLIst();
	}
	public String generateDce() throws Exception {
		try{
			LeaveApprovalModel model = new LeaveApprovalModel();
			model.initiate(context,session);
			String[] dceCheck = (String[])request.getParameterValues("leaveCode");
			if (dceCheck != null) {
				model.setDCE(dceCheck,leaveDce);
			model.terminate();
			
			}
		}catch (Exception e) {
			
		}
		return dceLIst();
	}
	
	
	
	
	
}
