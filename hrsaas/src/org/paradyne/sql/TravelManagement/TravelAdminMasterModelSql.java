package org.paradyne.sql.TravelManagement;
import org.paradyne.lib.SqlBase;
/*
 * @author SaiPavankumar 
 * Date:17-09-2008
 */
public class TravelAdminMasterModelSql extends SqlBase {
	
	@Override
	public String getQuery(int id) {
		// TODO Auto-generated method stub
		switch(id)
		{
			case 1:return "insert into HRMS_TRAVEL_ADMIN(TRAVEL_ADMIN_CODE,TRAVEL_ADMIN_BRANCH_CODE,TRAVEL_ADMIN_EMP_ID)"+"" +
					" values((Select nvl(max(TRAVEL_ADMIN_CODE),0)+1 from HRMS_TRAVEL_ADMIN),?,?) ";
			
			case 2:return "update  HRMS_TRAVEL_ADMIN set TRAVEL_ADMIN_BRANCH_CODE=?,TRAVEL_ADMIN_EMP_ID=? where TRAVEL_ADMIN_CODE=? ";
			
			case 3:return "delete from HRMS_TRAVEL_ADMIN where TRAVEL_ADMIN_CODE=?";
			
			case 4: return " select TRAVEL_ADMIN_CODE,HRMS_CENTER.CENTER_NAME,(office.EMP_FNAME||' '||office.EMP_MNAME||' '||office.EMP_LNAME) as name " 
            +" from hrms_travel_admin "
            +" Left join  hrms_emp_offc office on(office.EMP_ID=hrms_travel_admin.TRAVEL_ADMIN_EMP_ID)"
            +" Left join  HRMS_CENTER  on(HRMS_CENTER.CENTER_ID=hrms_travel_admin.TRAVEL_ADMIN_BRANCH_CODE)"
            +" ORDER BY HRMS_CENTER.CENTER_NAME";
			
			default:return "Framework could not EXECUTE the query number specified"; 
		}
	
		
	}
	
	

}
