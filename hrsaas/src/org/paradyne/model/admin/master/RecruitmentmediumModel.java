package org.paradyne.model.admin.master;
import org.paradyne.bean.admin.master.*;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.CrystalReport;

public class RecruitmentmediumModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
//	RecruitmentMedium rectMed = null;
	
	public boolean addRecr(RecruitmentMedium bean) {
		String query="SELECT UPPER(MEDIUM_NAME) FROM HRMS_RECUITMENT_MEDIUM WHERE (MEDIUM_NAME='"+bean.getMediumName().trim().toUpperCase()+"' OR MEDIUM_NAME='"+bean.getMediumName().trim().toLowerCase()+"')";
		Object [][]data=getSqlModel().getSingleResult(query);
		
		boolean flag=false;
		if(data.length>0)
		{
			 flag=false;
		}
		else{
		logger.info("in addRecr()");
		Object[][] saveObj = new Object[1][1];
		logger.info("Name19999111********"+bean.getMediumName());
		saveObj[0][0]=bean.getMediumName().trim();
		logger.info("Name1111------"+saveObj.length);
		logger.info("Sql Wuery-----------"+getQuery(8));
		flag= getSqlModel().singleExecute(getQuery(8),saveObj);
		}
		return flag;
	}
	
	
	public boolean modRecrt(RecruitmentMedium bean){
		Object addObj [][]= new Object[1][2];
		logger.info("In Modify method111");
		
		addObj [0][0]=bean.getMediumName().trim();
		addObj[0][1]=bean.getMediumCode();
		
		return getSqlModel().singleExecute(getQuery(2),addObj);
		
		
	}
	
	public boolean deleteRecrt(RecruitmentMedium bean){
		Object addObj [][]= new Object [1][1];
		addObj [0][0]=bean.getMediumCode();
		return getSqlModel().singleExecute(getQuery(3),addObj);
	}
	
	
	public void getMediumRecord(RecruitmentMedium bean){
		Object addObj []= new Object [1];
		addObj [0]=bean.getMediumCode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);
		
		for(int i=0; i<data.length; i++) {
			bean.setMediumCode(String.valueOf(data [i][0]));
			bean.setMediumName(String.valueOf(data [i][1]));
			
					
		}
	}
	
	public void getMediumReport(RecruitmentMedium bean){
		
		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		
		ArrayList<Object> mediumList = new ArrayList<Object>();
		
		for(int i=0; i<data.length; i++) {
			RecruitmentMedium bean1= new RecruitmentMedium();
			bean1.setMediumCode(String.valueOf(data[i][0]));
			logger.info("Value of code"+String.valueOf(data[i][0]));
			bean1.setMediumName(String.valueOf(data[i][1]));
			
					
			mediumList.add(bean1);
		}
		bean.setRecrtList(mediumList);
		
	}
	public void getReport(RecruitmentMedium bean,HttpServletRequest request,HttpServletResponse response,ServletContext context,HttpSession session){
		CrystalReport cr=new CrystalReport();
		String path="org\\paradyne\\rpt\\admin\\master\\recruitment.rpt";
		cr.createReport(request, response, context,session,path, "");
	}
	
	public String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "";
		}
		else{
			return result;
		}
	}


	public void recData(RecruitmentMedium recrtMedium,
			HttpServletRequest request) {
		Object [][] repData = getSqlModel().getSingleResult(getQuery(5));
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
	 int pg1=0;
	int PageNo1=1;//----------
	REC_TOTAL = repData.length;
	int no_of_pages=Math.round(REC_TOTAL/20);
	//PageNo = Integer.parseInt(locationMaster.getMyPage());//-----------
	double row = (double)repData.length/20.0;
   
      java.math.BigDecimal value1 = new java.math.BigDecimal(row);
     
      int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
    
	logger.info("--------------------"+rowCount1);
	logger.info("------- recrtMedium.getMyPage()-------------"+ recrtMedium.getMyPage());
	ArrayList<Object> obj=new ArrayList<Object>();
	request.setAttribute("abc", rowCount1);

	//PageNo
	if(String.valueOf( recrtMedium.getMyPage()).equals("null")||String.valueOf( recrtMedium.getMyPage()).equals(null)||String.valueOf( recrtMedium.getMyPage()).equals(" "))
	{
		PageNo1=1;
		From_TOT=0;
		  To_TOT=20;

		  if(To_TOT >repData.length){
			  To_TOT=repData.length;
		  }
			logger.info("-----------To_TOTAL----null-----"+To_TOT);
			 recrtMedium.setMyPage("1");
	}
	
	
	else{
			
		  pg1=	Integer.parseInt( recrtMedium.getMyPage());
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
		  RecruitmentMedium bean1 = new RecruitmentMedium();
			bean1.setMediumCode(checkNull(String.valueOf(repData[i][0])));
			bean1.setMediumName(checkNull(String.valueOf(repData[i][1])));
		
			
			
			
			
            obj.add(bean1);
	  }
	
	
		
	   recrtMedium.setRecrtList(obj);
	
		
	}


	public void calforedit(RecruitmentMedium recrtMedium) {
		String query = " SELECT  MEDIUM_ID,MEDIUM_NAME FROM HRMS_RECUITMENT_MEDIUM " 
	         
	          +"  where   MEDIUM_ID= "+recrtMedium.getHiddencode() 
+"    ORDER BY MEDIUM_ID ";


Object [][]data=getSqlModel().getSingleResult(query);
recrtMedium.setMediumCode(String.valueOf(data[0][0]));
recrtMedium.setMediumName(String.valueOf(data[0][1]));

		
	}


	public boolean calfordelete(RecruitmentMedium recrtMedium) {
		Object [][] delete = new Object [1][1];
	delete [0][0] =recrtMedium.getHiddencode();
	return getSqlModel().singleExecute(getQuery(3), delete);
	}

	
	public boolean deletecheckedRecords(RecruitmentMedium bean, String[] code) {
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
