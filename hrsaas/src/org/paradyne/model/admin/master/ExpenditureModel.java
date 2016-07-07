package org.paradyne.model.admin.master;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.paradyne.bean.admin.master.Expenditure;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.CrystalReport;

public class ExpenditureModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	Expenditure expend;
	public String add(Expenditure bean)
	{
		
		/*String query=" SELECT EXPENDITURE_NAME  FROM HRMS_DEPUT_EXPEND_TYPE "
		      +" WHERE EXPENDITURE_NAME ='"+bean.getExpName()+"'";
		Object[][] data= getSqlModel().getSingleResult(query);
		if(data.length>0)
		{
			return false;
		}else
		{*/
		Object addObj[][] =new Object[1][1];
		addObj[0][0] =bean.getExpName().trim(); 
		boolean b= getSqlModel().singleExecute(getQuery(1),addObj);	
		if(b) {
			  return "Record Saved Successfully";
			  
		  } else {
			  return "Record Cann't be Added";
			  
		  }

		
	}
	
	
	public String update(Expenditure bean)
	{
		
/*
		String query=" SELECT EXPENDITURE_NAME  FROM HRMS_DEPUT_EXPEND_TYPE "
		      +" WHERE EXPENDITURE_NAME ='"+bean.getExpName()+"'";
		Object[][] data= getSqlModel().getSingleResult(query);
		if(data.length>1)
		{
			return false;
		}else
		{*/
		Object addObj[][] =new Object[1][2];
		addObj[0][0] =bean.getExpName().trim(); 
		addObj[0][1] =bean.getExpCode();
		
		boolean b= getSqlModel().singleExecute(getQuery(2),addObj);	
		if(b) {
			  return "Record Modified Successfully";
			  
		  } else {
			  return "Record Already Existed";
			  
		  }
	
	}
	
	
	public boolean deleteExpend(Expenditure bean)
	{
		Object addObj[][] =new Object[1][1]; 
		addObj[0][0] =bean.getExpCode();
		return getSqlModel().singleExecute(getQuery(3),addObj);	
	}
	
	public void report(Expenditure expend)
	{ 
		String query="SELECT EXPENDITURE_CODE,EXPENDITURE_NAME FROM HRMS_DEPUT_EXPEND_TYPE ORDER BY EXPENDITURE_CODE";
		Object[][] data= getSqlModel().getSingleResult(query);
		
		ArrayList arry = new ArrayList();
		for(int i=0; i<data.length; i++)
		{	
			Expenditure bean1= new Expenditure();
			bean1.setExpCode(checkNull(String.valueOf(data[i][0])));
			 
			bean1.setExpName (checkNull(String.valueOf(data[i][1]))); 
		 
			arry.add(bean1);
		}
		logger.info("value of Name===========length____________________>>>>>"+arry.size());
		expend.setTableList1(arry);	
	}
	public void getReport(Expenditure bean,HttpServletRequest request,HttpServletResponse response,ServletContext context,HttpSession session){
		CrystalReport cr=new CrystalReport();
		logger.info("oooo-------------------");
		String path="org\\paradyne\\rpt\\admin\\master\\expenditure.rpt";
		cr.createReport(request, response, context,session, path, "");
	}
	
public void  Data(Expenditure  bean, HttpServletRequest request) {
		

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
		  Expenditure  bean1 = new Expenditure ();
			
                     bean1.setExpCode(checkNull(String.valueOf(repData[i][0])));
		      	bean1.setExpName(checkNull(String.valueOf(repData[i][1])));
		//	bean1.set(checkNull(String.valueOf(repData[i][2])));
			
			
			
			
            obj.add(bean1);
	  }
	
	
		
	  bean.setIteratorlist(obj);
	
	}
public void calforedit(Expenditure bean) {
		
		
		String query = "SELECT EXPENDITURE_CODE,EXPENDITURE_NAME FROM HRMS_DEPUT_EXPEND_TYPE WHERE  EXPENDITURE_CODE  = "+bean.getHiddencode() ;
		
			
		
		Object [][]data=getSqlModel().getSingleResult(query);
      //setter

	 bean.setExpCode(String.valueOf(data[0][0]));
		bean.setExpName(String.valueOf(data[0][1]));
	//	bean.set(String.valueOf(data[0][2]));
		
		
	}
public boolean calfordelete(Expenditure bean) {
	
	Object [][] delete = new Object [1][1];
	delete [0][0] =  bean.getHiddencode();
	return getSqlModel().singleExecute(getQuery(3), delete);


}


public boolean deletecheckedRecords(Expenditure bean, String[] code) {
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

	
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}	

}
 