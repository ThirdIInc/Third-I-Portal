/**
 * Balaji
 * 15-08-2008
**/
package org.paradyne.model.TravelManagement;
import java.util.ArrayList; 

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.TravelSchedule;
import org.paradyne.bean.attendance.MonthAttendance;
import org.paradyne.lib.ModelBase;

public class TravelScheduleModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	/*
	 *  This method is used for show employee details and change the status of schedule
	 */
	/**
	 * @param bean
	 * @param status
	 */
	public void callStatus(TravelSchedule bean , String status,HttpServletRequest request)
	{ 
        String query = "  SELECT EMP_TOKEN,EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMPNAME, "
				+" TO_CHAR(TRAVEL_APPDATE,'DD-MM-YYYY'), TRAVEL_STATUS,TRAVEL_LEVEL, TRAVEL_ID  FROM HRMS_TRAVEL "
				+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_TRAVEL.TRAVEL_EMPID)  "
				+" LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) "
				+" WHERE TRAVEL_STATUS  ='A'  AND TRAVEL_SCHEDULE_STATUS ='"+status+"'" 
				+" AND  HRMS_EMP_OFFC.EMP_CENTER IN( SELECT TRAVEL_ADMIN_BRANCH_CODE  FROM HRMS_TRAVEL_ADMIN WHERE TRAVEL_ADMIN_EMP_ID ="+bean.getUserEmpId()+")"
				 +"ORDER BY TRAVEL_APPDATE DESC";
        
		Object [][] result = getSqlModel().getSingleResult(query ); 
		
		doPaging(bean, result.length, new Object[][]{}, request);
		int fromTotRec = Integer.parseInt(bean.getFromTotRec());
		int toTotRec = Integer.parseInt(bean.getToTotRec());
		
		ArrayList travelList = new ArrayList();
		
		for(int i = fromTotRec; i < toTotRec;i++)
		{
			 
				TravelSchedule bean1 = new TravelSchedule();
				bean1.setTravelEmpToken(String.valueOf(result[i][0]));
				bean1.setTravelEmpId(String.valueOf(result[i][1]));
				bean1.setEmpName(String.valueOf(result[i][2]));
				bean1.setAppDate(String.valueOf(result[i][3]));
				bean1.setCheckStatus(String.valueOf(result[i][4]));
				if(status.equals("A"))
					bean1.setStatusNew("Approved"); 
				bean1.setLevel(String.valueOf(result[i][5]));
				bean1.setTraSchAppId(String.valueOf(result[i][6])); 
				String pathQuery="  SELECT TRAVEL_REMARK FROM HRMS_TRAVEL_PATH WHERE APPROVER_CODE="+bean.getUserEmpId()
				+" AND TRAVEL_ID ="+String.valueOf((result[i][6])); 
				Object [][] data=getSqlModel().getSingleResult(pathQuery);
				if(data.length==0 || String.valueOf(data[0][0]).equals("null") || data==null){				 
					bean1.setRemark("");
				}else {
					 bean1.setRemark(String.valueOf(data[0][0]));
				} 
				travelList.add(bean1); 
			  // end of for loop
		
		}
		
		
		
		
			 bean.setTravelSchList(travelList);
			 if(travelList.size()==0)
			 {
				  bean.setListLength("0");
				  bean.setNoData("true"); 
			 }else
			 {
				 bean.setListLength("1"); 
				 bean.setNoData("false"); 
			 }
			 bean.setStatus(status); 
	 }  // end of callStatus
	//}
	
	public void doPaging(TravelSchedule bean, int empLength, Object[][] attnObj, HttpServletRequest request)
	{
		try
		{
			/*
			 * totalRec -: No. of records per page
			 * fromTotRec -: Starting No. of record to show on a current page
			 * toTotRec -: Last No. of record to show on a current page
			 * pageNo -: Current page to show
			 * totalPage -: Total No. of Pages
			*/
			
			String pagingSql = " SELECT CONF_RECORDS_PER_PAGE FROM HRMS_SALARY_CONF ";
			Object[][] pagingObj = getSqlModel().getSingleResult(pagingSql);
			int totalRec = Integer.parseInt(String.valueOf(pagingObj[0][0]));
			
			int pageNo = 1;
			int fromTotRec = 0;
			int toTotRec = totalRec;
			int searchCount = 0;
			int totalPage = 0;
			String empExists = "false";

			java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal((double)empLength / totalRec);
			totalPage = Integer.parseInt(bigDecRow1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
			
			if(String.valueOf(bean.getHdPage()).equals("null")||String.valueOf(bean.getHdPage()).equals(null)||String.valueOf(bean.getHdPage()).equals(""))
			{
				pageNo = 1;
				fromTotRec = 0;
				toTotRec = totalRec;

				if(toTotRec > empLength)
				{
					toTotRec = empLength;
				}
				bean.setHdPage("1");
			}
			else
			{   	pageNo = Integer.parseInt(bean.getHdPage());
						  
					if(pageNo == 1)
					{
						fromTotRec = 0;
						toTotRec = totalRec;
					}
					else
					{
						toTotRec = toTotRec * pageNo;
						fromTotRec = toTotRec - totalRec;
					}
					if(toTotRec > empLength)
					{
						toTotRec = empLength;
					}
								
			}
			
			bean.setFromTotRec(String.valueOf(fromTotRec));
			bean.setToTotRec(String.valueOf(toTotRec));
			
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("fromTotRec", fromTotRec);
			request.setAttribute("toTotRec", toTotRec);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
}
