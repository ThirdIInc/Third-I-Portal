/**
 * 
 */
package org.paradyne.model.TravelManagement.Master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.Master.ExpensesCategory;
import org.paradyne.bean.TravelManagement.Master.RoomType;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author aa0651
 *
 */
public class ExpensesCategoryModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	/**
	 * following function is called when  a record is selected from search window to set the records.
	 * @param bean
	 */
	public void getExpense(ExpensesCategory bean) {
		// TODO Auto-generated method stub
		try{						
				logger.info("-------getExpense---------"+bean.getExpenseId());
			/*String query = " SELECT EXP_CATEGORY_NAME, EXP_CATEGORY_UNIT ,NVL(EXP_CATEGORY_DESC,' '),CASE WHEN EXP_CATEGORY_STATUS='A' THEN 'Active' ELSE 'Deactive' END,EXP_CATEGORY_ID  FROM HRMS_TMS_EXP_CATEGORY  "
				+" WHERE EXP_CATEGORY_ID="+bean.getExpenseId();*/
			
			String query = " SELECT EXP_CATEGORY_NAME,CASE WHEN EXP_CATEGORY_UNIT ='D' THEN 'Per Day' WHEN EXP_CATEGORY_UNIT ='T' THEN 'Per Travel' ELSE ' ' END,NVL(EXP_CATEGORY_DESC,''),CASE WHEN EXP_CATEGORY_STATUS='A' THEN 'Active' WHEN EXP_CATEGORY_STATUS='D' THEN 'Deactive' ELSE '' END,EXP_CATEGORY_ID  FROM HRMS_TMS_EXP_CATEGORY  "
				+" WHERE EXP_CATEGORY_ID="+bean.getExpenseId();

		Object[][] data = getSqlModel().getSingleResult(query);
		
		if(data!=null && data.length>0){
			
				bean.setExpenseName(checkNull(String.valueOf(data[0][0]).trim()));	
				bean.setExpenseUnit(checkNull(String.valueOf(data[0][1])));
				bean.setDescription(checkNull(String.valueOf(data[0][2])));
				bean.setStatus(checkNull(String.valueOf(data[0][3])));
				bean.setExpenseId(checkNull(String.valueOf(data[0][4])));
		     }
		 }catch(Exception e){
			e.printStackTrace();
	   }
		
	}
	
	public void getExpenseSearch(ExpensesCategory bean) {
		// TODO Auto-generated method stub
		try{						
				logger.info("-------getExpense---------"+bean.getExpenseId());
			String query = " SELECT EXP_CATEGORY_NAME, EXP_CATEGORY_UNIT ,NVL(EXP_CATEGORY_DESC,''),EXP_CATEGORY_STATUS,EXP_CATEGORY_ID  FROM HRMS_TMS_EXP_CATEGORY  "
				+" WHERE EXP_CATEGORY_ID="+bean.getExpenseId();
			
			/*String query = " SELECT EXP_CATEGORY_NAME,CASE WHEN EXP_CATEGORY_UNIT ='D' THEN 'Per Day' WHEN EXP_CATEGORY_UNIT ='T' THEN 'Per Travel' ELSE ' ' END,NVL(EXP_CATEGORY_DESC,' '),CASE WHEN EXP_CATEGORY_STATUS='A' THEN 'Active' ELSE 'Deactive' END,EXP_CATEGORY_ID  FROM HRMS_TMS_EXP_CATEGORY  "
				+" WHERE EXP_CATEGORY_ID="+bean.getExpenseId();*/

		Object[][] data = getSqlModel().getSingleResult(query);
		
		if(data!=null && data.length>0){
			
				bean.setExpenseName(checkNull(String.valueOf(data[0][0]).trim()));	
				bean.setExpenseUnit(checkNull(String.valueOf(data[0][1])));
				bean.setDescription(checkNull(String.valueOf(data[0][2])));
				bean.setStatus(checkNull(String.valueOf(data[0][3])));
				bean.setExpenseId(checkNull(String.valueOf(data[0][4])));
		     }
		 }catch(Exception e){
			e.printStackTrace();
	   }
		
	}
	
	public boolean addExpenseType(ExpensesCategory bean) {
		// TODO Auto-generated method stub
		if(!checkDuplicate(bean)){
			logger.info("crossing dupe---------");
		Object[][] add=new Object[1][4];
		try{		
		add[0][0]=bean.getExpenseName().trim();	
		add[0][1]=bean.getExpenseUnit();	
		add[0][2]=bean.getDescription();
		add[0][3]=bean.getStatus();
		}catch(Exception e){
			e.printStackTrace();
		}		
		boolean result=getSqlModel().singleExecute(getQuery(1),add);		
		
		if(result){

			String query = " SELECT MAX(EXP_CATEGORY_ID) FROM HRMS_TMS_EXP_CATEGORY" ;
			
			Object[][] data = getSqlModel().getSingleResult(query);
			
			System.out.println("data[0][0]---->"+data[0][0]);
			
			String query1 = " SELECT EXP_CATEGORY_NAME	,CASE WHEN EXP_CATEGORY_UNIT ='D' THEN 'Per Day' WHEN EXP_CATEGORY_UNIT ='T' THEN 'Per Travel' ELSE ' ' END,CASE WHEN EXP_CATEGORY_STATUS	='A' THEN 'Active' WHEN EXP_CATEGORY_STATUS	='D' THEN 'Deactive' ELSE ' ' END,NVL(EXP_CATEGORY_DESC,''),EXP_CATEGORY_ID FROM "
						   +" HRMS_TMS_EXP_CATEGORY WHERE EXP_CATEGORY_ID="+String.valueOf(data[0][0]);
			
			
			
			Object[][] Data = getSqlModel().getSingleResult(query1);
			
			bean.setExpenseName(checkNull(String.valueOf(Data[0][0])).trim());		
			bean.setExpenseUnit(checkNull(String.valueOf(Data[0][1])).trim());
			bean.setDescription(checkNull(String.valueOf(Data[0][3])).trim());			
			bean.setStatus(checkNull(String.valueOf(Data[0][2])).trim());			
			bean.setExpenseId(checkNull(String.valueOf(Data[0][4])).trim());
			
		}
		return result;
		}else{
			return false;
		}
	}
	public boolean checkDuplicate(ExpensesCategory bean) {
		
		logger.info("--------checkDuplicate----------------");
		boolean result = false;
		String query = "SELECT * FROM  HRMS_TMS_EXP_CATEGORY WHERE UPPER(EXP_CATEGORY_NAME) LIKE '"
				+ bean.getExpenseName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		logger.info("checkDuplicate---result----"+result);
		return result;
	}
	
	public boolean checkDuplicateMod(ExpensesCategory bean) {
		if(!checkDuplicate(bean)){
		boolean result = false;
		String query = "SELECT * FROM  HRMS_TMS_EXP_CATEGORY WHERE UPPER(EXP_CATEGORY_NAME) LIKE '"
				+ bean.getExpenseName().trim().toUpperCase()
				+ "' AND EXP_CATEGORY_ID not in(" + bean.getExpenseId() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;
		}else{
			return false;
		}

	}
	
	/**
	 * following function is called to update the record.
	 * @param bean
	 * @return
	 */
	
	public boolean modExpenseType(ExpensesCategory bean) {
	
			Object mod[][] = new Object[1][5];
			
			mod[0][0]=bean.getExpenseName().trim();	
			mod[0][1]=bean.getExpenseUnit();
			mod[0][2]=bean.getDescription();
			mod[0][3]=bean.getStatus();
			mod[0][4]=bean.getExpenseId();
				
			boolean res= getSqlModel().singleExecute(getQuery(2), mod);
			
			if(res){
				
				
				String query1 = " SELECT EXP_CATEGORY_NAME	,CASE WHEN EXP_CATEGORY_UNIT ='D' THEN 'Per Day' WHEN EXP_CATEGORY_UNIT ='T' THEN 'Per Travel' ELSE ' ' END,NVL(EXP_CATEGORY_DESC,''),CASE WHEN EXP_CATEGORY_STATUS	='A' THEN 'Active' WHEN EXP_CATEGORY_STATUS	='D' THEN 'Deactive' ELSE ' ' END,EXP_CATEGORY_ID FROM "
					   +" HRMS_TMS_EXP_CATEGORY WHERE EXP_CATEGORY_ID="+bean.getExpenseId();
				
				Object[][] Data = getSqlModel().getSingleResult(query1);
				
				bean.setExpenseName(checkNull(String.valueOf(Data[0][0])).trim());	
				bean.setExpenseUnit(checkNull(String.valueOf(Data[0][1])).trim());	
				bean.setDescription(checkNull(String.valueOf(Data[0][2])).trim());
				bean.setStatus(checkNull(String.valueOf(Data[0][3])).trim());
				bean.setExpenseId(checkNull(String.valueOf(Data[0][4])).trim());
			}
			return res;
	
	}
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	/**
	 * following function is called to delete a record
	 * @param bean
	 * @return
	 */
	public boolean deleteExpenseType(ExpensesCategory bean) {
		Object del[][] = new Object[1][1];		
		del[0][0] = bean.getExpenseId();				
		return getSqlModel().singleExecute(getQuery(3), del);
	}
	
	public void getData(ExpensesCategory bean,HttpServletRequest request){
		try{
		
		String query = " SELECT NVL(UPPER(EXP_CATEGORY_NAME),' '),CASE WHEN EXP_CATEGORY_UNIT ='D' THEN 'Per Day' WHEN EXP_CATEGORY_UNIT ='T' THEN 'Per Travel' ELSE ' ' END,"
			+ " CASE WHEN EXP_CATEGORY_STATUS='A' THEN 'Active' WHEN EXP_CATEGORY_STATUS='D' THEN 'Deactive' ELSE ' ' END,NVL(EXP_CATEGORY_DESC,''),EXP_CATEGORY_ID  FROM HRMS_TMS_EXP_CATEGORY"
			+" ORDER BY UPPER(EXP_CATEGORY_NAME),EXP_CATEGORY_UNIT,EXP_CATEGORY_STATUS";

		Object[][] data = getSqlModel().getSingleResult(query);
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
		int pg1=0;
		int PageNo1=1;//----------
		REC_TOTAL = data.length;
		int no_of_pages=Math.round(REC_TOTAL/20);
		double row = (double)data.length/20.0;
   
      java.math.BigDecimal value1 = new java.math.BigDecimal(row);
     
      int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
    
	
	ArrayList<Object> obj=new ArrayList<Object>();
	System.out.println("val of riwC"+rowCount1);
	request.setAttribute("abc", rowCount1);

	//PageNo
	if(String.valueOf(bean.getMyPage()).equals("null")||String.valueOf(bean.getMyPage()).equals(null)||String.valueOf(bean.getMyPage()).equals(" "))
	{
		PageNo1=1;
		From_TOT=0;
		  To_TOT=20;

		  if(To_TOT >data.length){
			  To_TOT=data.length;
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
		  if(To_TOT >data.length){
			  To_TOT=data.length;
		  }
	  }
	request.setAttribute("xyz", PageNo1);
	  
	  for(int i=From_TOT;i<To_TOT;i++){
                 //setting 
		  ExpensesCategory  bean1 = new ExpensesCategory();
			
		bean1.setExpenseName(String.valueOf(data[i][0]).trim());	
		bean1.setExpenseUnit(String.valueOf(data[i][1]));		
		bean1.setStatus(String.valueOf(data[i][2]));
		bean1.setExpenseId1(String.valueOf(data[i][4]));
		obj.add(bean1);
	  }
	
	
		
	  bean.setExpenseList(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	
	}	
	public void getExpenseTypeOnDoubleClick(ExpensesCategory bean){
		logger.info("-------Inside getExpenseTypeOnDoubleClick()--------");
		try{			
		
		String query1 = " SELECT NVL(EXP_CATEGORY_NAME,' ')	, EXP_CATEGORY_UNIT ,NVL(EXP_CATEGORY_DESC,''),EXP_CATEGORY_STATUS	,EXP_CATEGORY_ID FROM "
			   +" HRMS_TMS_EXP_CATEGORY WHERE EXP_CATEGORY_ID="+bean.getHiddencode();
		
		String query="SELECT NVL(EXP_CATEGORY_NAME,' '),EXP_CATEGORY_UNIT,NVL(EXP_CATEGORY_DESC,''),EXP_CATEGORY_STATUS,EXP_CATEGORY_ID" 
					+"	FROM HRMS_TMS_EXP_CATEGORY  WHERE EXP_CATEGORY_ID="+bean.getHiddencode();

	Object[][] data = getSqlModel().getSingleResult(query);
	
	if(data!=null && data.length>0){
		
			bean.setExpenseName(checkNull(String.valueOf(data[0][0]).trim()));		
			bean.setExpenseUnit(checkNull(String.valueOf(data[0][1])));			
			bean.setDescription(checkNull(String.valueOf(data[0][2]).trim()));
			bean.setStatus(checkNull(String.valueOf(data[0][3])));
			bean.setExpenseId(checkNull(String.valueOf(data[0][4])));
	     }
	 }catch(Exception e){
		e.printStackTrace();
   }
	 
}	 
	
	public boolean delChkdRec(ExpensesCategory bean,String[] code){
		
		
		int count=0;
		boolean result=false;
		for(int i=0;i<code.length;i++){
			if(!code[i].equals("")){
				
				Object [][] delete = new Object [1][1];
				delete [0][0] =code[i] ;
				result=getSqlModel().singleExecute(getQuery(3), delete);
				if(!result){
					count++;
				}
				}
	  	}
		
		
		if(count!=0){
			result=false;
			return result;
		}else {
			result=true;
			return result;
		}
	}

	public void generateReport(ExpensesCategory expenses,
			HttpServletResponse response,String []label) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName="Expenses Category";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf", reportName);		
				
		String query = "SELECT  EXP_CATEGORY_NAME,CASE WHEN EXP_CATEGORY_UNIT ='D' THEN 'Per Day' WHEN EXP_CATEGORY_UNIT ='T' THEN 'Per Travel' ELSE ' ' END,NVL(EXP_CATEGORY_DESC,''),"
			+ " CASE WHEN EXP_CATEGORY_STATUS='A' THEN 'Active' WHEN EXP_CATEGORY_STATUS='D' THEN 'Deactive' ELSE ' ' END FROM HRMS_TMS_EXP_CATEGORY "
			+ " ORDER BY EXP_CATEGORY_NAME,EXP_CATEGORY_UNIT,EXP_CATEGORY_STATUS ";
		Object [][]data=getSqlModel().getSingleResult(query);
		Object [][] finalData=new Object[data.length][5];
			
		
		
		if (data != null && data.length > 0) {
			int j=1;
			for(int i=0;i<data.length;i++){
				finalData[i][0]=j;
				finalData[i][1]=data[i][0];
				finalData[i][2]=data[i][1];
				finalData[i][3]=data[i][2];
				finalData[i][4]=data[i][3];
				j++;
			}
			
			//String abc[] = { "Sr No", "Expenses Category Name","Expenses Category Unit","Description","Status"};
			int cellwidth[] = {10,25,25,30,15};
			int alignment[] = { 0, 0 , 0, 0,0};
			//rg.addFormatedText("Room type", 1,0,1,0);
			rg.addTextBold("Expenses Category", 0, 1, 0);
			rg.addText("\n\n",0,0,0);
			rg.addTextBold("Date :"+toDay, 0, 2, 0);
			rg.addText("\n\n",0,0,0);
			rg.tableBody(label, finalData, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);
		
		
	}

	public void getDesc(ExpensesCategory expenses) {	
		String query="SELECT NVL(EXP_CATEGORY_DESC,'') FROM HRMS_TMS_EXP_CATEGORY WHERE EXP_CATEGORY_ID="+expenses.getExpenseId();
		Object[][] data = getSqlModel().getSingleResult(query);
		expenses.setDescription(checkNull(String.valueOf(data[0][0])));
		
	}


}
