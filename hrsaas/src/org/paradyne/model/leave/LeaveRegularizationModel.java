package org.paradyne.model.leave;

import java.util.Calendar;
import java.util.HashMap;

 import org.paradyne.lib.ModelBase;
import org.paradyne.bean.leave.LeaveMisReport;
import org.paradyne.bean.leave.LeaveRegularization;
import org.paradyne.bean.payroll.incometax.TdsCalculation;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.lib.Utility;

public class LeaveRegularizationModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	/*public  String[][] getRecordView(LeaveRegularization leaveRe,HttpServletRequest request){
		
		String query=" SELECT LEAVE_CODE,HRMS_LEAVE.LEAVE_NAME, "
					+"NVL(TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),0) "
					+"FROM HRMS_LEAVE_DTL "
					+"INNER JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_DTL.LEAVE_CODE) "
					+"WHERE HRMS_LEAVE_DTL.EMP_ID="+leaveRe.getEmpID()+" ";
		
		Object[][] data = getSqlModel().getSingleResult(query);
		logger.info("********1:"+data.length);
		HashMap afdata=new HashMap();
		HashMap chkFlag=new HashMap();
		String[][] total= new String[data.length][3];
		
		for(int i=0;i<data.length;i++)
		{
			
		
			total[i][0]= String.valueOf(data[i][0]);
			total[i][1]=String.valueOf(data[i][1]);
			total[i][2]=String.valueOf(data[i][2]);
			total[i][3]=String.valueOf(data[i][3]);
			total[i][4]=String.valueOf(data[i][4]);
			total[i][5]=String.valueOf(data[i][5]);
			total[i][6]=String.valueOf(data[i][6]);
			total[i][7]=String.valueOf(data[i][7]);
			total[i][8]=String.valueOf(data[i][8]);
			total[i][9]=String.valueOf(data[i][9]);
			total[i][10]=String.valueOf(data[i][10]);
			total[i][11]=String.valueOf(data[i][11]);
			total[i][12]=String.valueOf(data[i][12]);
			total[i][13]=String.valueOf(data[i][13]);
			total[i][14]=String.valueOf(data[i][14]);
			if(String.valueOf(data[i][3]).equals("Y"))
					{
						total[i][3]=String.valueOf("true");
					}
			else
				total[i][3]=String.valueOf("false");
			
			logger.info("---------------------------
			 * ------------------################################# total["+i+"][15]value="+total[i][15]);
			afdata.put(""+i,String.valueOf(data[i][15]));
			request.setAttribute("hashData",afdata);
			logger.info("*******************In Model value of Audit---------Flag**********************"+String.valueOf(data[i][16]));
			if(String.valueOf(data[i][16]).equals("Y"))
			{
				chkFlag.put(""+i,"true");
				
			}
			else
			{
				chkFlag.put(""+i,"false");
			}
					
			logger.info("*******************In Model value of Flag**********************"+chkFlag.get(""+i));
			request.setAttribute("chkFlag",chkFlag);
			
			
		}
		
		logger.info("********2 Para ID:");
		
		return total;
		
		}*/
	
	/*public float getAmount(LeaveRegularization leaveRe,HttpServletRequest request){
		String frmDate=leaveRe.getLeaveFrom();
		String year=frmDate.substring(6,10);
		String month=frmDate.substring(3,5);
		float result;
		float lopAmount=0;
		
		String query2=" SELECT SAL_AMOUNT FROM HRMS_SAL_CREDITS_2007 WHERE SAL_CREDIT_CODE IN(1,2,3) AND EMP_ID="+leaveRe.getEmpID()+" " +
				" AND SAL_MONTH="+month;
		Object Data[][]=getSqlModel().getSingleResult(query2);
		result = Float.parseFloat(String
				.valueOf(Data[0][0]))
				+ Float.parseFloat(String
						.valueOf(Data[1][0]))
				+ Float.parseFloat(String
					.valueOf(Data[2][0]));
		Calendar cal = Calendar.getInstance();
		cal.setTime(Utility.getDate(leaveRe.getLeaveFrom()));
		int daysOfMonth = cal
				.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		lopAmount = (result) / daysOfMonth;
		
		

		
		
		return lopAmount;
	}*/
	
/*public boolean generateLeaveStatus(LeaveRegularization leaveRe,HttpServletRequest request){
		
		String frmDate=leaveRe.getLeaveFrom();
		String year=frmDate.substring(6,10);
		boolean result=false;
		
		String SQL="SELECT HRMS_EMP_OFFC.EMP_ID,TO_CHAR(LEAVE_FROM_DATE,'DD-MM-YYYY'),TO_CHAR(LEAVE_TO_DATE,'DD-MM-YYYY')  FROM HRMS_LEAVE_DTL "
				  +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_DTL.EMP_ID)"
				  +" WHERE (LEAVE_FROM_DATE <=TO_DATE('"+leaveRe.getLeaveTo()+"','DD-MM-YYYY') AND"
				  +" LEAVE_TO_DATE >=TO_DATE('"+leaveRe.getLeaveFrom()+"','DD-MM-YYYY'))"
				  +" AND HRMS_LEAVE_DTL.LEAVE_DTL_STATUS='A' AND HRMS_LEAVE_DTL.EMP_ID="+leaveRe.getEmpID()+" " ;
					
		SQL+=" ORDER BY HRMS_LEAVE_DTL.EMP_ID";
		
		//1. Get records of employees which fall in the date range
			 Object leaveData[][]=getSqlModel().getSingleResult(SQL);
			 logger.info("leaveData.length>>>>>>>>>>>>>>"+leaveData.length);
			 for(int i=0;i<leaveData.length;i++){
				 String empId=String.valueOf(leaveData[i][0]);
				 String fromDate=String.valueOf(leaveData[i][1]);
				 String toDate=String.valueOf(leaveData[i][2]);
				 //2. Update leave status of employees in DAILY_ATTENDANCE which fall in the range from hrms_leave_dtl FROM TO	
				 SQL="UPDATE HRMS_DAILY_ATTENDANCE_"+year+" SET ATT_LEAVE_STATUS='LL' WHERE ATT_DATE BETWEEN "+
				 	"TO_DATE('"+fromDate+"','DD-MM-YYYY') AND TO_DATE('"+toDate+"','DD-MM-YYYY') AND ATT_EMP_ID="+empId;
				 logger.info(">>>>>>HEMANT"+i+"--"+SQL);
				 result=getSqlModel().singleExecute(SQL);
				 
			 }
		return result;
	}*/

	public String[][]  getDetail(LeaveRegularization leaveRe,HttpServletRequest request){
		String frmDate=leaveRe.getLeaveFrom();
		String year=frmDate.substring(6,10);
		
		String query=" SELECT distinct hrms_leave_dtl.leave_code, hrms_leave.leave_name, "
					+"TO_CHAR(HRMS_DAILY_ATTENDANCE_2007.ATT_DATE,'DD-MM-YYYY'),HRMS_DAILY_ATTENDANCE_2007.ATT_EMP_ID,ATT_STATUS FROM HRMS_DAILY_ATTENDANCE_2007 "
					+"INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_DAILY_ATTENDANCE_2007.ATT_EMP_ID) "
					+" left join hrms_leave_dtl on(hrms_leave_dtl.EMP_ID=HRMS_DAILY_ATTENDANCE_2007.ATT_EMP_ID) "
					 +" left join hrms_leave on(hrms_leave.LEAVE_ID=hrms_leave_dtl.LEAVE_CODE)  "
					+"WHERE ATT_DATE BETWEEN TO_DATE('"+leaveRe.getLeaveFrom()+"','DD-MM-YYYY') and " 
					+"TO_DATE('"+leaveRe.getLeaveTo()+"','DD-MM-YYYY') AND  ATT_STATUS='AA'  "
					+"AND ATT_LEAVE_STATUS IS NULL AND HRMS_DAILY_ATTENDANCE_2007.att_EMP_ID="+leaveRe.getEmpID()+" order by HRMS_DAILY_ATTENDANCE_2007.ATT_EMP_ID";
		/*if(leaveRe.getEmpID()!=null && leaveRe.getEmpID().length()>0){
		query=query+" AND HRMS_DAILY_ATTENDANCE_"+frmDate.substring(6,10)+".ATT_EMP_ID="+leaveRe.getEmpID()+" " ;
		}*/
		HashMap afdata=new HashMap();
		Object leaveAppData[][]= getSqlModel().getSingleResult(query);
		String[][] total1= new String[leaveAppData.length][4];
		float amt=0;
		for(int i=0;i<leaveAppData.length;i++)
		
		{
			
		
			total1[i][0]= String.valueOf(leaveAppData[i][0]);
			total1[i][1]=checkNull(String.valueOf(leaveAppData[i][1]));
			total1[i][2]=String.valueOf(leaveAppData[i][2]);
			afdata.put(""+i,String.valueOf(leaveAppData[i][4]));
			String fDate=String.valueOf(leaveAppData[i][2]);
			String y=fDate.substring(6,10);
			String m=fDate.substring(3,5);
			float result=0.0f;
			float lopAmount=0;
			
			logger.info("AMOUNT----->>>>>>>>>>>"+String.valueOf(leaveAppData[i][2]));
			String query2=" SELECT sum(SAL_AMOUNT) FROM HRMS_SAL_CREDITS_"+y+"  WHERE SAL_CREDIT_CODE IN(1,2,3) AND EMP_ID="+leaveRe.getEmpID()+" " +
					" AND SAL_MONTH="+m;
			Object Data[][]=getSqlModel().getSingleResult(query2);
			try {
				if(Data[0][0]==null|| String.valueOf(Data[0][0]).equals("")|| String.valueOf(Data[0][0]).equals("null")){
					result=0.0f;
				}else{
				result = Float.parseFloat(String.valueOf(Data[0][0]));
				}
				Calendar cal = Calendar.getInstance();
				cal.setTime(Utility.getDate(String.valueOf(leaveAppData[i][2])));
				int daysOfMonth = cal
						.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
				lopAmount = (result) / daysOfMonth;
				total1[i][3] = String.valueOf(lopAmount);
				
				amt= amt+lopAmount;
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		leaveRe.setTotalAmt(String.valueOf(amt));
		request.setAttribute("data",afdata);
		return total1;
	}

	public boolean addRegular(LeaveRegularization leaveRe,String date,String amt ) {
	//	logger.info("in model Amount----->>>>>>"+String.valueOf(amt));
		
			
			String dt=" INSERT INTO HRMS_LEAVE_REGULARIZE (LEAVE_EMP_ID,LEAVE_DATE,LEAVE_AMOUNT,LEAVE_PROCESS_DATE,LEAVE_PROCESSED_BY) "
				 +"VALUES("+leaveRe.getEmpID()+",TO_DATE('"+date+"','DD-MM-YYYY'),"+amt+",TO_DATE(SYSDATE,'DD-MM-YYYY'),"+leaveRe.getUserEmpId()+") ";

			boolean result= getSqlModel().singleExecute(dt);
			 logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+dt);
			 
		return result;
	}
	
	public String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "";
		}
		else{
			return result;
		}
}

	


}
