package org.paradyne.model.admin.master;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.master.Expenditure;
import org.paradyne.bean.admin.master.Requisition;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.CrystalReport;

public class RequisitionModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	public boolean save(Requisition bean)
	{  
		Object[][] addObj = new Object[1][1];
		addObj[0][0]=bean.getReuisitionName();
		getSqlModel().singleExecute(getQuery(1),addObj);  
		return true;
	}
	
	public boolean update(Requisition bean)
	{  
		Object[][] addObj = new Object[1][2];
		addObj[0][0]=bean.getReuisitionName();
		addObj[0][1]=bean.getRequisitionCode();
		getSqlModel().singleExecute(getQuery(2),addObj);  
		return true;
	}

	public boolean delete(Requisition bean)
	{  
		Object[][] addObj = new Object[1][1]; 
		addObj[0][0]=bean.getRequisitionCode();
		getSqlModel().singleExecute(getQuery(3),addObj);  
		return true;
	}
	
	public void report(Requisition bean)
	{ 
		String query = "  SELECT REQUISITION_HEAD_CODE,REQUISITION_HEAD_NAME  FROM HRMS_REQUISITION_HEAD ORDER BY REQUISITION_HEAD_CODE ";
		Object[][] data= getSqlModel().getSingleResult(query);
		
		ArrayList arry = new ArrayList();
		for(int i=0; i<data.length; i++)
		{	
			Requisition bean1= new Requisition();
			bean1.setRequisitionCode(checkNull(String.valueOf(data[i][0]))); 
			bean1.setReuisitionName(checkNull(String.valueOf(data[i][1])));  
			arry.add(bean1);
		}
		 
		bean.setReqTableList(arry);	
	}
	
	
	
	
	
	
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void getReport(Requisition requis, HttpServletRequest request,
			HttpServletResponse response, ServletContext context) {
		// TODO Auto-generated method stub

		CrystalReport cr=new CrystalReport();
		String path="org\\paradyne\\rpt\\admin\\master\\CashRequisition.rpt ";
		cr.createReport(request, response, context,session, path, "");
	}	
	


	public void  Data(Requisition  bean, HttpServletRequest request) {
			

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
				logger.info("-----------To_TOTAL----null-----"+To_TOT);
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
		  logger.info("------------from total--------"+From_TOT);
		  logger.info("----------to total----------"+To_TOT);
		  for(int i=From_TOT;i<To_TOT;i++){
	                 //setting 
			  Requisition  bean1 = new Requisition ();
				
			    bean1.setRequisitionCode(checkNull(String.valueOf(repData[i][0]))); 
				bean1.setReuisitionName(checkNull(String.valueOf(repData[i][1])));  
				
				
				
				
	            obj.add(bean1);
		  }
		
		
			
		  bean.setIteratorlist(obj);
		
		}
	public void calforedit(Requisition bean) {
			
			
			String query = "SELECT REQUISITION_HEAD_CODE,REQUISITION_HEAD_NAME  FROM HRMS_REQUISITION_HEAD WHERE REQUISITION_HEAD_CODE  = "+bean.getHiddencode() ;
			
				
			
			Object [][]data=getSqlModel().getSingleResult(query);
	      //setter
			bean.setRequisitionCode(String.valueOf(data[0][0])); 
			bean.setReuisitionName(checkNull(String.valueOf(data[0][1])));  
			
		}
	public boolean calfordelete(Requisition bean) {
		
		Object [][] delete = new Object [1][1];
		delete [0][0] =  bean.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);
		// TODO Auto-generated method stub

	}


	public boolean deletecheckedRecords(Requisition bean, String[] code) {
		boolean result=false;
		int count=0;
		if(code !=null)
		{
			for (int i = 0; i < code.length; i++) {
				
				if(!code[i].equals("")){
					
					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					result=getSqlModel().singleExecute(getQuery(3), delete);
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
}
