package org.paradyne.model.leave;

import org.paradyne.bean.leave.MaternityLeaveSetting;
import org.paradyne.lib.ModelBase;

public class MaternityLeaveSettingModel extends ModelBase {

	public boolean save(MaternityLeaveSetting bean) {
		
		String query="UPDATE HRMS_MATERNITY_LEAVE_POLICY "
       +" SET HRMS_MATERNITY_LEAVE_TYPE=?, HRMS_MATERNITY_LEAVE_DAYS=?, HRMS_MIN_SERVICE_PERIOD=?,"
       +" HRMS_MATERNITY_PRE_LEAVES=?, "
       +" HRMS_MATERNITY_PRE_LEAVEDAYS=?, HRMS_MATERNITY_POST_LEAVES=?,"
       +" HRMS_MATERNITY_POST_LEAVEDAYS=?, HRMS_PATERNITY_LEAVE_TYPE=?,HRMS_PATERNITY_LEAVE_DAYS=? ";
		
		try {
			Object myobj[][] = new Object[1][9];
			myobj[0][0] = bean.getLeaveTypecode();
			myobj[0][1] = bean.getNoofLeavedays();
			myobj[0][2] = bean.getMinServiceperiod();
			myobj[0][3] = bean.getPreMaternityType();
			myobj[0][4] = bean.getPreMaternitydays();
			myobj[0][5] = bean.getPostMaternityType();
			myobj[0][6] = bean.getPostMaternitydays();
			myobj[0][7] = bean.getSubLeaveTypecode();
			myobj[0][8] = bean.getSubNoofLeaves();
			
			String que="Select * from HRMS_MATERNITY_LEAVE_POLICY";
			Object dataobj[][] =getSqlModel().getSingleResult(que);
			
			if(dataobj!=null && dataobj.length>0)
			{
				return getSqlModel().singleExecute(query,myobj);
			}else{
				System.out.println("no records...!!");
				String query1="insert into HRMS_MATERNITY_LEAVE_POLICY "
					+" (HRMS_MATERNITY_LEAVE_TYPE, HRMS_MATERNITY_LEAVE_DAYS, HRMS_MIN_SERVICE_PERIOD,"
					+" HRMS_MATERNITY_PRE_LEAVES, HRMS_MATERNITY_PRE_LEAVEDAYS, HRMS_MATERNITY_POST_LEAVES,"
					+" HRMS_MATERNITY_POST_LEAVEDAYS, HRMS_PATERNITY_LEAVE_TYPE,HRMS_PATERNITY_LEAVE_DAYS)"
					+" values (?,?,?,?,?,?,?,?,?)";
				
				return getSqlModel().singleExecute(query1,myobj);
			}
		
			
			
		} catch (Exception e) {
			System.out.println("Exception...!"+e);
			return false;
		}
		
	}
	
public void getMaternityData(MaternityLeaveSetting bean) {
		
		String query="SELECT  "
			       +" NVL(HRMS_MATERNITY_LEAVE_TYPE,''),  NVL(HRMS_MATERNITY_LEAVE_DAYS,0), NVL(HRMS_MIN_SERVICE_PERIOD,0)"
			       +"  ,NVL(HRMS_MATERNITY_PRE_LEAVES,''), "
			       +"  NVL(HRMS_MATERNITY_PRE_LEAVEDAYS,0), NVL(HRMS_MATERNITY_POST_LEAVES,''),"
			       +"  NVL(HRMS_MATERNITY_POST_LEAVEDAYS,0), NVL(HRMS_PATERNITY_LEAVE_TYPE,''), NVL(HRMS_PATERNITY_LEAVE_DAYS,0) " 
			       +"  ,L1.LEAVE_NAME,L2.LEAVE_NAME FROM HRMS_MATERNITY_LEAVE_POLICY  MATERNITY" 
			       +" LEFT JOIN HRMS_LEAVE L1 ON (MATERNITY.HRMS_MATERNITY_LEAVE_TYPE=L1.LEAVE_ID)"
			       +"  LEFT JOIN HRMS_LEAVE L2 ON (MATERNITY.HRMS_PATERNITY_LEAVE_TYPE=L2.LEAVE_ID)";
		
					
		try {
			Object myobj[][] =getSqlModel().getSingleResult(query);
			
			if(myobj!=null && myobj.length>0)
			{
			bean.setLeaveTypecode(checkNull(String.valueOf(myobj[0][0])));
			bean.setNoofLeavedays(checkNull(String.valueOf(myobj[0][1])));
			bean.setMinServiceperiod(checkNull(String.valueOf(myobj[0][2])));
			bean.setPreMaternityType(checkNull(String.valueOf(myobj[0][3])));
			bean.setPreMaternitydays(checkNull(String.valueOf(myobj[0][4])));
			bean.setPostMaternityType(checkNull(String.valueOf(myobj[0][5])));
			bean.setPostMaternitydays(checkNull(String.valueOf(myobj[0][6])));
			bean.setSubLeaveTypecode(checkNull(String.valueOf(myobj[0][7])));
			bean.setSubNoofLeaves(checkNull(String.valueOf(myobj[0][8])));
			bean.setLeaveType(checkNull(String.valueOf(myobj[0][9])));
			bean.setSubleaveType(checkNull(String.valueOf(myobj[0][10])));
			}
			
		} catch (Exception e) {
			
		}
		
	}
public String checkNull(String result) {
	
	if (result == null || result.equals("null")) {
		return "";
	} else {
		return result;
	}
}
	

}
