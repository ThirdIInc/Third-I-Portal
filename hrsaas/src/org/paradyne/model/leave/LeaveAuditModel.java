/**
 * 
 */
package org.paradyne.model.leave;

import java.util.ArrayList; 

import org.paradyne.bean.leave.LeaveAudit;
import org.paradyne.lib.BeanBase;
 import org.paradyne.lib.ModelBase;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author Venkatesh

 */
public class LeaveAuditModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

	public LeaveAuditModel() {
		// TODO Auto-generated constructor stub
	}
	
	public void getLeaveDetails(LeaveAudit leaveAudit,HttpServletRequest request){
		ArrayList<Object> att = new ArrayList<Object>();
		ArrayList<Object> att1 = new ArrayList<Object>();
		HashMap afdata=new HashMap();
		Object levObj [] = new Object[1];
		logger.info("*******************In Model Get Leave Details***********************");
		levObj[0]= leaveAudit.getEmpID();
		logger.info("*******************In Model Emp Id***********************"+leaveAudit.getEmpID());
		Object[][] values = getSqlModel().getSingleResult(getQuery(1), levObj);
		logger.info("*******************In Model Length of Values**********************"+values.length);
		for(int i = 0 ;i < values.length ; i++){
			LeaveAudit levAudit = new LeaveAudit();
			levAudit.setEmpID(String.valueOf(values[i][0]));
			levAudit.setEmpName1(String.valueOf(values[i][1]));
			levAudit.setLeaveDays(String.valueOf(values[i][2]));
			levAudit.setLeaveFromDate(String.valueOf(values[i][3]));
			levAudit.setLeaveToDate(String.valueOf(values[i][4]));
			levAudit.setLeaveDtlId(String.valueOf(values[i][5]));
			leaveAudit.setLeaveDtlId1(String.valueOf(values[i][5]));
			logger.info("*******************In Model value of Leave Id**********************"+levAudit.getLeaveDtlId());
			levAudit.setLeaveAuditFlag(String.valueOf(values[i][6]));
			afdata.put(""+i,String.valueOf(values[i][6]));
			logger.info("*******************In Model value of Audit---------Flag**********************"+levAudit.getLeaveAuditFlag());
			if(levAudit.getLeaveAuditFlag().equals("Y"))
			{
				levAudit.setChkFlag("true");
				//levAudit.setFlag(true);
			}
			else
			{
				levAudit.setChkFlag("false");
				//levAudit.setFlag(false);
			}
					
			logger.info("*******************In Model value of Flag**********************"+levAudit.getChkFlag());
			levAudit.setLeaveAuditDate(String.valueOf(values[i][7]));
			levAudit.setLeaveAuditBy(String.valueOf(values[i][8]));
			
			att.add(levAudit);
		}
		leaveAudit.setAuditList(att);
		logger.info("*******************In Model Length of ArrayList-att**********************"+att.size());
		request.setAttribute("data",afdata);
	}
	
		
	public boolean updLeaveDtl(LeaveAudit leaveAudit,HttpServletRequest request) {
	// TODO Auto-generated method stub
		
		String[] Ids=(String[])request.getParameterValues("leaveDtlId");
		String[] flags=(String[])request.getParameterValues("leaveAuditFlag");
		logger.info("&&&&&&&&&&&&&&&&&&&After Get Ids length----------------------"+Ids.length);
		logger.info("-------------After Get Flag length----------------------"+flags.length);
	
		String userId=leaveAudit.getUserEmpId();
		boolean result=false;
		for(int i=0;i<Ids.length;i++){
			
			String SQL=" UPDATE HRMS_LEAVE_DTL SET LEAVE_AUDIT_FLAG='"+flags[i]+"',LEAVE_AUDIT_DATE=SYSDATE, LEAVE_AUDIT_BY="+userId+" WHERE LEAVE_DTL_ID="+Ids[i] ;
			result=getSqlModel().singleExecute(SQL);			
		}
		return result;
}

	
	
	
}