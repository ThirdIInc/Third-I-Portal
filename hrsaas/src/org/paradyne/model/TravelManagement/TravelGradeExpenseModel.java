/**
 * Balaji Mane
 * Date 17-10-2008
 * purpose of this class is to save,update and delete the gradewise expense of travel
 */
package org.paradyne.model.TravelManagement;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelGradeExpense;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;
 
public class TravelGradeExpenseModel extends ModelBase {
	
	public void callOnLoad(TravelGradeExpense bean)
	{
		String sqlQuery = " SELECT JOURNEY_NAME ,JOURNEY_CLASS_NAME, JOURNEY_ID ,JOURNEY_CLASS_ID  FROM HRMS_JOURNEY "
					+" LEFT JOIN HRMS_JOURNEY_CLASS ON (HRMS_JOURNEY_CLASS.JOURNEY_CLASS_JOURNEY_ID = HRMS_JOURNEY.JOURNEY_ID) "
					+" ORDER BY JOURNEY_NAME "; 
		Object [][] result = getSqlModel().getSingleResult(sqlQuery);
		
		if(result!=null && result.length >0)
		{
			ArrayList jlist = new ArrayList();
			 for(int i=0; i< result.length;i++)
			 {
				 TravelGradeExpense bean1 = new TravelGradeExpense();
				 bean1.setJourneyName(""+result[i][0]);
				 bean1.setClassName(""+result[i][1]);
				 bean1.setJourneyId(""+result[i][2]);
				 bean1.setClassId(""+result[i][3]);
				 jlist.add(bean1);
			 }
			 bean.setJourExp(jlist);
		}
		
	}
	
	public void callF9dtl(TravelGradeExpense bean)
	{
		String sqlQuery = " SELECT JOURNEY_NAME ,JOURNEY_CLASS_NAME, JOURNEY_ID ,JOURNEY_CLASS_ID,TRAVEL_GRADE_DTL_CLASS_FLAG  FROM HRMS_TRAVEL_GRADE_DTL "
						+"  LEFT JOIN HRMS_JOURNEY  ON (HRMS_JOURNEY.JOURNEY_ID = HRMS_TRAVEL_GRADE_DTL .TRAVEL_GRADE_DTL_JOURNEY_ID) "
						+"  LEFT JOIN HRMS_JOURNEY_CLASS ON (HRMS_JOURNEY_CLASS.JOURNEY_CLASS_ID=  TRAVEL_GRADE_DTL_JCLASS_ID)  "
						+" WHERE TRAVEL_GRADE_HDR_ID = "+bean.getTrGradeId()+" ORDER BY JOURNEY_NAME"; 
		Object [][] result = getSqlModel().getSingleResult(sqlQuery);
		if(result!=null && result.length >0)
		{
			ArrayList jlist = new ArrayList();
			 for(int i=0; i< result.length;i++)
			 {
				 TravelGradeExpense bean1 = new TravelGradeExpense();
				 bean1.setJourneyName(""+result[i][0]);
				 bean1.setClassName(""+result[i][1]);
				 bean1.setJourneyId(""+result[i][2]);
				 bean1.setClassId(""+result[i][3]);
				 bean1.setGrCheck(""+(result[i][4].equals("Y")?"checked":""));
				 bean1.setHidCheck(""+(result[i][4]));
				 jlist.add(bean1);
			 }
			bean.setJourExp(jlist);
		}
		
	}
	
	
	public boolean save(TravelGradeExpense bean,HttpServletRequest request)
	{
		 String [] journeyId= (String [])request.getParameterValues("journeyId");
		 String [] classId= (String [])request.getParameterValues("classId");
		 String [] hidCheck= (String [])request.getParameterValues("hidCheck");
		 Object[][]para = new Object[1][6];
		 para[0][0]=bean.getGradeCode();
		 para[0][1]=bean.getHotelPerDayCost();
		 para[0][2]=bean.getHotelOtherCost();
		 para[0][3]=bean.getTravelOtherCost();  
		 para[0][4]=bean.getPocketCost().trim().equals("")?"0":bean.getPocketCost();
		 para[0][5]=bean.getFoodCost().trim().equals("")?"0":bean.getFoodCost(); 
		 
		 String query = " INSERT INTO HRMS_TRAVEL_GRADE_HDR(TRAVEL_GRADE_HDR_ID, TRAVEL_GRADE_CODE, TRAVEL_GRADE_HOTEL_COST, "
                       +" TRAVEL_GRADE_OTHER_HOTEL_COST, TRAVEL_GRADE_OTHER_TRAVEL_COST,TRAVEL_GRADE_POCKET_COST, TRAVEL_GRADE_FOOD_COST)VALUES( " 
                       +"(SELECT NVL(MAX(TRAVEL_GRADE_HDR_ID),0)+1 FROM  HRMS_TRAVEL_GRADE_HDR), ?,?,?,?,?,?)";
		 boolean flag = getSqlModel().singleExecute(query,para);
		 if(flag==true && journeyId!=null && journeyId.length >0)
		 {		  
			 for(int i=0;i <journeyId.length;i++)
			 {
				String chkFlag = hidCheck[i] ;
				if(!chkFlag.equals("Y"))	
				{		
					chkFlag="N"; 
				 }			 
		      String sql =" INSERT INTO HRMS_TRAVEL_GRADE_DTL(TRAVEL_GRADE_DTL_ID, TRAVEL_GRADE_HDR_ID, TRAVEL_GRADE_DTL_JOURNEY_ID, " 
				 	+" TRAVEL_GRADE_DTL_JCLASS_ID, TRAVEL_GRADE_DTL_CLASS_FLAG)  VALUES ( (SELECT NVL(MAX(TRAVEL_GRADE_DTL_ID),0)+1 " 
				 	+" FROM HRMS_TRAVEL_GRADE_DTL) , (SELECT NVL(MAX(TRAVEL_GRADE_HDR_ID),0) FROM  HRMS_TRAVEL_GRADE_HDR) ," 
				 	+""+journeyId[i]+","+classId[i]+",'"+chkFlag+"')";
		         flag= getSqlModel().singleExecute(sql);
			 }
		   }
		return flag;
	}
	
	public boolean update(TravelGradeExpense bean,HttpServletRequest request)
	{
		 String [] journeyId= (String [])request.getParameterValues("journeyId");
		 String [] classId= (String [])request.getParameterValues("classId");
		 String [] hidCheck= (String [])request.getParameterValues("hidCheck");
		 Object[][]para = new Object[1][7];
		 para[0][0]=bean.getGradeCode();
		 para[0][1]=bean.getHotelPerDayCost();
		 para[0][2]=bean.getHotelOtherCost();
		 para[0][3]=bean.getTravelOtherCost(); 
		 para[0][4]=bean.getPocketCost();
		 para[0][5]=bean.getFoodCost(); 
		 para[0][6]=bean.getTrGradeId();
		 
		 String query = " UPDATE HRMS_TRAVEL_GRADE_HDR  SET  TRAVEL_GRADE_CODE = ?, TRAVEL_GRADE_HOTEL_COST = ?, "
                       +" TRAVEL_GRADE_OTHER_HOTEL_COST = ? , TRAVEL_GRADE_OTHER_TRAVEL_COST = ? , "  
                       +" TRAVEL_GRADE_POCKET_COST = ?, TRAVEL_GRADE_FOOD_COST = ?  WHERE TRAVEL_GRADE_HDR_ID = ?";
		 boolean flag = getSqlModel().singleExecute(query,para);
		 if(flag)
		 {	
			 String delSql =" DELETE FROM HRMS_TRAVEL_GRADE_DTL WHERE TRAVEL_GRADE_HDR_ID = "+bean.getTrGradeId();
			 flag= getSqlModel().singleExecute(delSql);
			 if(flag && journeyId!=null && journeyId.length >0)
			 {
				 for(int i=0;i <journeyId.length;i++)
				 {
					String chkFlag = hidCheck[i] ;
					if(!chkFlag.equals("Y"))	
					{		
						chkFlag="N"; 
					 }			 
			      String sql =" INSERT INTO HRMS_TRAVEL_GRADE_DTL(TRAVEL_GRADE_DTL_ID, TRAVEL_GRADE_HDR_ID, TRAVEL_GRADE_DTL_JOURNEY_ID, " 
					 	+" TRAVEL_GRADE_DTL_JCLASS_ID, TRAVEL_GRADE_DTL_CLASS_FLAG)  VALUES ( (SELECT NVL(MAX(TRAVEL_GRADE_DTL_ID),0)+1 " 
					 	+" FROM HRMS_TRAVEL_GRADE_DTL) , "+bean.getTrGradeId()+" ," 
					 	+""+journeyId[i]+","+classId[i]+",'"+chkFlag+"')";
			         flag= getSqlModel().singleExecute(sql);
				 } //end of for loop
			 }
		   }
		return flag;
	}
	
	public boolean delete(TravelGradeExpense bean)
	{
		  
		 
		 String query = " DELETE FROM HRMS_TRAVEL_GRADE_HDR  WHERE TRAVEL_GRADE_HDR_ID ="+bean.getTrGradeId();
		 boolean flag = getSqlModel().singleExecute(query);
		 if(flag)
		 {	
			 String delSql =" DELETE FROM HRMS_TRAVEL_GRADE_DTL WHERE TRAVEL_GRADE_HDR_ID = "+bean.getTrGradeId();
			 flag= getSqlModel().singleExecute(delSql);
			 
		   }
		return flag;
	}
	
	
	public void getReport(HttpServletRequest request, HttpServletResponse response,TravelGradeExpense bean)
	{ 
		String reportName = "\n Gradewise Travel Expense Report \n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",	reportName);
		rg.addFormatedText(reportName, 6, 0, 1, 0); 
		
		String query = "SELECT CADRE_NAME ,TRAVEL_GRADE_OTHER_TRAVEL_COST,TRAVEL_GRADE_OTHER_HOTEL_COST, TRAVEL_GRADE_HOTEL_COST ,"
			   +" TRAVEL_GRADE_POCKET_COST, TRAVEL_GRADE_FOOD_COST  FROM HRMS_TRAVEL_GRADE_HDR "
			   +" LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_TRAVEL_GRADE_HDR.TRAVEL_GRADE_CODE)"
			   +" WHERE TRAVEL_GRADE_HDR_ID = "+bean.getTrGradeId();
		Object [][] gradeData = getSqlModel().getSingleResult(query);
		
		if(gradeData!=null && gradeData.length >0 )
		 {
			Object[][] gradeTabData = new Object[3][2];
			gradeTabData[0][0]="Grade : "+gradeData[0][0];
			gradeTabData[0][1]="Travel Miscellaneous Expenses : "+gradeData[0][1];
			gradeTabData[1][0]="Lodging Miscellaneous allowance per day : "+gradeData[0][2];
			gradeTabData[1][1]="Lodging allowance per Day : "+gradeData[0][3];  
			gradeTabData[2][0]="Out of pocket allowance per day : "+gradeData[0][4];
			gradeTabData[2][1]="Food allowance per day: "+gradeData[0][5]; 
			int[] width = {30,50};
			int[] align = { 0, 0 }; 
			rg.tableBodyNoBorder(gradeTabData, width, align);
		  }  
		String sqlQuery = " SELECT JOURNEY_NAME ,JOURNEY_CLASS_NAME,DECODE(TRAVEL_GRADE_DTL_CLASS_FLAG,'Y','Applicable','N','Not Applicable')  FROM HRMS_TRAVEL_GRADE_DTL "
			+"  LEFT JOIN HRMS_JOURNEY  ON (HRMS_JOURNEY.JOURNEY_ID = HRMS_TRAVEL_GRADE_DTL .TRAVEL_GRADE_DTL_JOURNEY_ID) "
			+"  LEFT JOIN HRMS_JOURNEY_CLASS ON (HRMS_JOURNEY_CLASS.JOURNEY_CLASS_ID=  TRAVEL_GRADE_DTL_JCLASS_ID)  "
			+" WHERE TRAVEL_GRADE_HDR_ID = "+bean.getTrGradeId()+" ORDER BY JOURNEY_NAME"; 
		Object [][] result = getSqlModel().getSingleResult(sqlQuery);
		if(result!=null && result.length >0 )
		 {
			 int j =1;
			 Object[][] jdata = new Object[result.length][4];  
				for(int i=0;i<result.length;i++)
				{
					jdata[i][0]=""+j;
					jdata[i][1]=""+result[i][0];
					jdata[i][2]=""+result[i][1];
					jdata[i][3]=""+result[i][2]; 
					j++;
				} 
				int[] bcellWidth = { 6, 30, 30, 34 };
				int[] bcellAlign = { 0, 0, 0, 0 }; 
				rg.tableBody(jdata, bcellWidth, bcellAlign);
		 }
		rg.createReport(response);
		
	}
	

}
