/**
 * 
 */
package org.paradyne.model.recruitment;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.paradyne.bean.Recruitment.EntranceTestMaster;
import org.paradyne.bean.admin.master.DivisionMaster;


import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.CrystalReport;

/**
 * @author pankajj
 *
 */
public class EntranceTestMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class);
	EntranceTestMaster entrance=null;
	public boolean checkDuplicate(EntranceTestMaster bean){
		boolean result=false;
		String query="SELECT * FROM HRMS_ENTR_EXAM WHERE UPPER(EXAM_NAME) LIKE '"+bean.getExamName().trim().toUpperCase()+"'";
		Object [][]data=getSqlModel().getSingleResult(query);
		if(data!=null && data.length>0)
		{
			result=true;
		}
		return result;

	}
	
	public boolean checkDuplicateMod(EntranceTestMaster bean){
		boolean result=false;
		String query="SELECT * FROM HRMS_ENTR_EXAM WHERE UPPER(EXAM_NAME) LIKE '"+bean.getExamName().trim().toUpperCase()+"' AND EXAM_CODE not in("+bean.getExamCode()+")" ;
		Object [][]data=getSqlModel().getSingleResult(query);
		if(data!=null && data.length>0)
		{
			result=true;
		}
		return result;

	}
	public boolean saveData(EntranceTestMaster bean)
	{
		
		if(!checkDuplicate(bean)){
			Object [][]saveObj = new Object[1][3];
			saveObj[0][0] = bean.getExamName();
			saveObj[0][1] = bean.getTotalMarks();
			saveObj[0][2] = bean.getPassMarks();
			return  getSqlModel().singleExecute(getQuery(1), saveObj);
		}
		else
		{
		return false;
		}
	}	
	public boolean update(EntranceTestMaster bean)
	{
			Object [][]updtObj = new Object[1][4];
			updtObj[0][0] = bean.getExamName();
			updtObj[0][1] = bean.getTotalMarks();
			updtObj[0][2] = bean.getPassMarks();
			updtObj[0][3] = bean.getExamCode();
			return getSqlModel().singleExecute(getQuery(2), updtObj);
	}
	
	public boolean delete(EntranceTestMaster bean)
	{
		Object[][]delObj = new Object[1][1];
		delObj[0][0] = bean.getExamCode();
		return getSqlModel().singleExecute(getQuery(3), delObj);
	}
	
	public String getdistReport(EntranceTestMaster entrance)
	{
		Object[][] data = getSqlModel().getSingleResult(getQuery(4));
		
		ArrayList<Object> distList1 = new ArrayList<Object>();
		
		for(int i=0; i<data.length; i++) {
			EntranceTestMaster bean1= new EntranceTestMaster();
			bean1.setExamCode(checkNull(String.valueOf(data[i][0])));
			bean1.setExamName(checkNull(String.valueOf(data[i][1])));
			bean1.setTotalMarks(checkNull(String.valueOf(data[i][2])));
			bean1.setPassMarks(checkNull(String.valueOf(data[i][3])));
			
								
			distList1.add(bean1);
		}
		entrance.setDistList(distList1);
		
		return "";
	}
	
	public void getReport(EntranceTestMaster bean, HttpServletRequest request,
			HttpServletResponse response, ServletContext context) {
		// TODO Auto-generated method stub
		CrystalReport cr=new CrystalReport();
		String path="org\\paradyne\\rpt\\recruitment\\EntranceTestMaster.rpt";
		cr.createReport(request, response, context,session, path, "");
	}
	
	public String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "";
		}
		else{
			return result;
		}
	}
	public void entData(EntranceTestMaster entrance, HttpServletRequest request) {
		
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
    
	System.out.println("--------------------"+rowCount1);
	System.out.println("------- entrance.getMyPage()-------------"+ entrance.getMyPage());
	ArrayList<Object> obj=new ArrayList<Object>();
	request.setAttribute("abc", rowCount1);

	//PageNo
	if(String.valueOf( entrance.getMyPage()).equals("null")||String.valueOf( entrance.getMyPage()).equals(null)||String.valueOf( entrance.getMyPage()).equals(" "))
	{
		PageNo1=1;
		From_TOT=0;
		  To_TOT=20;

		  if(To_TOT >repData.length){
			  To_TOT=repData.length;
		  }
			System.out.println("-----------To_TOTAL----null-----"+To_TOT);
			 entrance.setMyPage("1");
	}
	
	
	else{
			
		  pg1=	Integer.parseInt( entrance.getMyPage());
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
	  System.out.println("------------from total--------"+From_TOT);
	  System.out.println("----------to total----------"+To_TOT);
	  for(int i=From_TOT;i<To_TOT;i++){
		  EntranceTestMaster bean1 = new EntranceTestMaster ();
			bean1.setExamCode(checkNull(String.valueOf(repData[i][0])));
			bean1.setExamName(checkNull(String.valueOf(repData[i][1])));
			bean1.setTotalMarks(checkNull(String.valueOf(repData[i][2])));
			bean1.setPassMarks(checkNull(String.valueOf(repData[i][3])));
			
			
			
			
			
            obj.add(bean1);
	  }
	
	
		
	   entrance.setDistList(obj);
	
		// TODO Auto-generated method stub
		
	}
	public void calforedit(EntranceTestMaster entrance) {
		String query = " SELECT EXAM_CODE,EXAM_NAME,EXAM_TOTAL_MARKS,EXAM_PASS_MARKS FROM HRMS_ENTR_EXAM "	
		
	          
	          +"  where  EXAM_CODE = "+entrance.getHiddencode() 
+"    ORDER BY EXAM_CODE";


Object [][]data=getSqlModel().getSingleResult(query);
entrance.setExamCode(String.valueOf(data[0][0]));
entrance.setExamName(String.valueOf(data[0][1]));
entrance.setTotalMarks(String.valueOf(data[0][2]));
entrance.setPassMarks(String.valueOf(data[0][3]));

		
		
	}
	public boolean calfordelete(EntranceTestMaster entrance) {
		Object [][] delete = new Object [1][1];
		delete [0][0] =entrance.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);
	}
	
public boolean deleteTest(EntranceTestMaster entrance, String[] code) {
		
		
		boolean result=false;
		boolean flag=false;
		if(code !=null)
		{
			for (int i = 0; i < code.length; i++) {
				
				if(!code[i].equals("")){
				
					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					flag	=getSqlModel().singleExecute(getQuery(3), delete);
					if(flag)
					result=true;
				}
			}
		}
		return result;
		
	}
}
