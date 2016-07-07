package org.paradyne.model.TravelManagement;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.TravelManagement.TravelAdminMaster;
import org.paradyne.lib.ModelBase;
/*
 * @author SaiPavanKumar 
 *  Date:11-08-2008
 */
import org.paradyne.lib.report.ReportGenerator;

public class TravelAdminMasterModel extends ModelBase {

	/**
	 * @param travelbean
	 * @return boolean value after inserting record
	 */
	public boolean adding(TravelAdminMaster travelbean) {
		// TODO Auto-generated method stub
		Object obj[][]=new Object[1][2];
		
		obj[0][0]=travelbean.getBranchcode();
		obj[0][1]=travelbean.getEmpid();
			
		return getSqlModel().singleExecute(getQuery(1), obj);
	}
	/**
	 * @param travelbean
	 * @return  boolean value after modifying record
	 */
	public boolean modify(TravelAdminMaster travelbean) {
		// TODO Auto-generated method stub
       Object obj[][]=new Object[1][3];
		
		obj[0][0]=travelbean.getBranchcode();
		obj[0][1]=travelbean.getEmpid();
		obj[0][2]=travelbean.getTravelmastercode();
		
		return getSqlModel().singleExecute(getQuery(2), obj);
	}
	/**
	 * @param travelbean
	 * @return  boolean value after deleting the record
	 */
	public boolean delete(TravelAdminMaster travelbean) {
		// TODO Auto-generated method stub
         Object obj[][]=new Object[1][1];
		
		obj[0][0]=travelbean.getTravelmastercode();
		
		return getSqlModel().singleExecute(getQuery(3), obj);
	}

	
	/**
	 * @param bean
	 * @param request
	 */
	public void  Data(TravelAdminMaster  bean, HttpServletRequest request) {
			

			Object [][] repData = getSqlModel().getSingleResult(getQuery(4));
			int REC_TOTAL = 0;
			int To_TOT = 20;
			int From_TOT = 0;
		 int pg1=0;
		int PageNo1=1;//----------
		REC_TOTAL = repData.length;
		int no_of_pages=Math.round(REC_TOTAL/20);
		//PageNo = Integer.parseInt(bean.getMyPage());//-----------
		double row = (double)repData.length/20.0;
	   
	      java.math.BigDecimal value1 = new java.math.BigDecimal(row);
	     
	      int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
	    
		
		ArrayList<Object> obj=new ArrayList<Object>();
		request.setAttribute("abc", rowCount1);

		//PageNo
		if(String.valueOf(bean.getMyPage()).equals("null")||String.valueOf(bean.getMyPage()).equals(null)||String.valueOf(bean.getMyPage()).equals(" "))
		{
			PageNo1=1;
			From_TOT=0;
			  To_TOT=20;

			  if(To_TOT >repData.length){
				  To_TOT=repData.length;
			  }
				bean.setMyPage("1");
		}
		
		
		else{
				
			  pg1=	Integer.parseInt(bean.getMyPage());
			  PageNo1=pg1;
			  
			  if(pg1 ==1){
				 From_TOT=0;
				 To_TOT=20;
			  }
			  else{
				//  From_TOTAL=To_TOTAL+1;
					 To_TOT=To_TOT*pg1;
					 From_TOT=(To_TOT-20);
			  }
			  if(To_TOT >repData.length){
				  To_TOT=repData.length;
			  }
		  }
		request.setAttribute("xyz", PageNo1);
		  for(int i=From_TOT;i<To_TOT;i++){
	                 //setting 
			  TravelAdminMaster  bean1 = new TravelAdminMaster ();
								
			  bean1.setTravelmastercode((String.valueOf(repData[i][0])));
				 bean1.setBranchname((String.valueOf(repData[i][1])));
				 bean1.setEmployeename((String.valueOf(repData[i][2])));
				
				
	            obj.add(bean1);
		  }
		
		
			
		  bean.setIteratorlist(obj);
		
		}
	/**
	 * @param bean
	 */
	public void calforedit(TravelAdminMaster bean) {
			
			
		String query=" select TRAVEL_ADMIN_CODE,HRMS_CENTER.CENTER_NAME,(office.EMP_FNAME||' '||office.EMP_MNAME||' '||office.EMP_LNAME) as name " 
            +" ,TRAVEL_ADMIN_BRANCH_CODE,TRAVEL_ADMIN_EMP_ID from hrms_travel_admin "
            +" Left join  hrms_emp_offc office on(office.EMP_ID=hrms_travel_admin.TRAVEL_ADMIN_EMP_ID)"
            +" Left join  HRMS_CENTER  on(HRMS_CENTER.CENTER_ID=hrms_travel_admin.TRAVEL_ADMIN_BRANCH_CODE)"
            +" where TRAVEL_ADMIN_CODE="+bean.getHiddencode() ;
			
				
			
			Object [][]data=getSqlModel().getSingleResult(query);
	     
			 bean.setTravelmastercode((String.valueOf(data[0][0])));
			 bean.setBranchname((String.valueOf(data[0][1])));
			 bean.setEmployeename((String.valueOf(data[0][2])));
			 bean.setBranchcode((String.valueOf(data[0][3])));
			 bean.setEmpid((String.valueOf(data[0][4])));
		
			
		}
	

	/**
	 * @param bean
	 * @param code
	 * @return boolean after deleting the checked records.
	 */
	public boolean deletecheckedRecords(TravelAdminMaster bean, String[] code) {
		boolean result=false;
		int count=0;
		if(code !=null)
		{
			for (int i = 0; i < code.length; i++) {
				
				if(!code[i].equals("")){
					
					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					result=getSqlModel().singleExecute(getQuery(3),delete);
					if(!result)
						count++;
					
				}
			}
		}
		if(count!=0)
		{	result=false;
			return result;
			}
		else
			{result=true;
			return result; }
	}
	
	
	public void getReport(HttpServletRequest request, HttpServletResponse response,TravelAdminMaster bean)
	{ 
		String reportName = "\n Travel Admin Report \n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",	reportName);
		rg.addFormatedText(reportName, 6, 0, 1, 0); 
		  
String sqlQuery =" SELECT CENTER_NAME , EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,TRAVEL_ADMIN_CODE  FROM HRMS_TRAVEL_ADMIN "
				+" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_TRAVEL_ADMIN.TRAVEL_ADMIN_BRANCH_CODE) "
				+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_TRAVEL_ADMIN.TRAVEL_ADMIN_EMP_ID) "
				+" ORDER BY CENTER_NAME "; 
		Object [][] result = getSqlModel().getSingleResult(sqlQuery);
		if(result!=null && result.length >0 )
		 {
			 int j =1;
			 Object[][] jdata = new Object[result.length][3];  
				for(int i=0;i<result.length;i++)
				{
					jdata[i][0]=""+j;
					jdata[i][1]=""+result[i][0];
					jdata[i][2]=""+result[i][1]; 
					j++;
				} 
				int[] bcellWidth = { 6, 30, 50 };
				int[] bcellAlign = { 0, 0, 0 }; 
				rg.tableBody(jdata, bcellWidth, bcellAlign);
		 }else
		 {
			 rg.addFormatedText("No data to display", 0, 0, 1, 0); 
		 }
		rg.createReport(response);
		
	}
}
