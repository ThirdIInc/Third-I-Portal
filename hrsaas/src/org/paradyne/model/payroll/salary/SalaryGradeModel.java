/**
 * 
 */
package org.paradyne.model.payroll.salary;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.payroll.DebitMaster;
import org.paradyne.bean.payroll.salary.SalaryGrade;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author varunk
 * @modified ayyappa
 *
 */
public class SalaryGradeModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SalaryGradeModel.class);
	
	public String[][] getRecord(SalaryGrade bean) {

		logger.info("In getRecord Model===========1");
		
		String query="SELECT CREDIT_CODE,CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE";
		Object[][] data = getSqlModel().getSingleResult(query);
		ArrayList list = new ArrayList();
		String[][] total = new String[data.length][3];
		for (int i = 0; i < data.length; i++) {

			logger.info("---------------------------------------------"
					+ total.length);
			SalaryGrade bean1 = new SalaryGrade();
			/*total[i][0] = String.valueOf(data[i][0]);
			total[i][1] = String.valueOf(data[i][1]);
			total[i][2] = "0";*/
			bean1.setCreditCode(checkNull(String.valueOf(data[i][0])));
			bean1.setCreditName(checkNull(String.valueOf(data[i][1])));
			//bean1.setCreditAmt("0");
			bean1.setSalAmt("0");
			list.add(bean1);
		}

		/* tabRec.setRecList(list); */
		bean.setCreditlist(list);
		return total;

	}

	public boolean saveGrade(SalaryGrade salGrade, String[] creditCode, String[] creditName, String[] creditAmt) {
		
		boolean result=false;
		boolean result1=false;
		logger.info("VALUE OF GRADE TYPE------------------->>"+salGrade.getGradeName());
		logger.info("value of credit length----------------------->>"+creditCode.length);
		
		String query = "INSERT INTO HRMS_SALGRADE_HDR (SALGRADE_CODE,SALGRADE_TYPE) VALUES " 
						+"((SELECT NVL(MAX(SALGRADE_CODE),0)+1 FROM HRMS_SALGRADE_HDR),'"+salGrade.getGradeName()+"')";
		 result = getSqlModel().singleExecute(query);
		
		String query1 = "SELECT  MAX(SALGRADE_CODE) FROM HRMS_SALGRADE_HDR";
		Object[][] codeValue = getSqlModel().getSingleResult(query1);
		Object[][] addObj=new Object[creditCode.length][2];
		ArrayList list = new ArrayList();
		if(result){
			salGrade.setGradeCode(checkNull(String.valueOf(codeValue[0][0])));
		for (int i = 0; i < creditCode.length; i++) {
			addObj[i][0]=creditCode[i];
			addObj[i][1]=creditAmt[i];
			logger.info("value of credit amt---------------->>"+addObj[i][1]);
			if(addObj[i][1]==null){
				addObj[i][1]= 0;
			}
		
			SalaryGrade bean1 = new SalaryGrade();
			bean1.setCreditCode(String.valueOf(creditCode[i]));
			bean1.setCreditName(creditName[i]);
			bean1.setSalAmt(String.valueOf(creditAmt[i]));
			list.add(bean1);
			
			
		String query2= "INSERT INTO HRMS_SALGRADE_DTL (SALGRADE_CODE,SALGRADE_CREDIT_CODE,SALGRADE_CREDIT_AMT) "
						+"VALUES ("+codeValue[0][0]+","+addObj[i][0]+","+addObj[i][1]+")";
		
		System.out.println("Query2:"+query2);
	
		 result1 = getSqlModel().singleExecute(query2);
		}
		salGrade.setCreditlist(list);
		}
		if(result && result1)
		return true;
		else
		{
			return false;
		}
		
	}
	
public boolean updateGrade(SalaryGrade salGrade, String[] creditCode, String[] creditName, String[] creditAmt) {
	
	
		System.out.println("updateGrade updateGrade updateGrade");
		boolean result=false;
		boolean result1=false;
		logger.info("VALUE OF GRADE TYPE------------------->>"+salGrade.getGradeName());
		logger.info("value of credit length----------------------->>"+creditCode.length);
		
		
		Object[][] addObj=new Object[creditCode.length][2];
		ArrayList list = new ArrayList();
		
		for (int i = 0; i < creditCode.length; i++) {
			addObj[i][0]=creditCode[i];
			addObj[i][1]=creditAmt[i];
			logger.info("value of credit amt---------------->>"+addObj[i][1]);
		
			if(addObj[i][1]==null){
				addObj[i][1]= 0;
			}
			SalaryGrade bean1 = new SalaryGrade();
			bean1.setCreditCode(String.valueOf(creditCode[i]));
			bean1.setCreditName(creditName[i]);
			bean1.setSalAmt(String.valueOf(creditAmt[i]));
			list.add(bean1);			
		String query2= "INSERT INTO HRMS_SALGRADE_DTL (SALGRADE_CODE,SALGRADE_CREDIT_CODE,SALGRADE_CREDIT_AMT) "
						+"VALUES ("+salGrade.getGradeCode()+","+addObj[i][0]+","+addObj[i][1]+")";
	
		result1 = getSqlModel().singleExecute(query2);
		
		String query3 = "UPDATE HRMS_SALGRADE_HDR SET SALGRADE_TYPE='"+salGrade.getGradeName()+"' WHERE SALGRADE_CODE ="+salGrade.getGradeCode()+"";
		
		result1 = getSqlModel().singleExecute(query3);
		}
		salGrade.setCreditlist(list);
		
			return result1;
		
	}
	
	public String[][] getData(SalaryGrade salGrade) {
		
		String query="SELECT HRMS_CREDIT_HEAD.CREDIT_CODE,HRMS_CREDIT_HEAD.CREDIT_NAME,NVL(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_AMT,'0') " 
					+"from HRMS_CREDIT_HEAD "
					+"left JOIN HRMS_SALGRADE_DTL ON (HRMS_SALGRADE_DTL.SALGRADE_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE and HRMS_SALGRADE_DTL.SALGRADE_CODE="+salGrade.getGradeCode()+") "
					+" ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
		Object[][] data = getSqlModel().getSingleResult(query);
		ArrayList list = new ArrayList();
		
		String[][] total = new String[data.length][3];
		for (int i = 0; i < data.length; i++) {
			SalaryGrade bean1 = new SalaryGrade();
			logger.info("---------------------------------------------"
					+ total.length);
			/*total[i][0] = String.valueOf(data[i][0]);
			total[i][1] = String.valueOf(data[i][1]);
			total[i][2] = String.valueOf(data[i][2]);*/
			bean1.setCreditCode(String.valueOf(data[i][0]));
			bean1.setCreditName(String.valueOf(data[i][1]));
			bean1.setSalAmt(String.valueOf(data[i][2]));
			list.add(bean1);
		}
		salGrade.setCreditlist(list);
		return total;
	}
	


	public boolean deleteRecord(SalaryGrade salGrade) {
		
		String query1 = "DELETE FROM HRMS_SALGRADE_DTL WHERE SALGRADE_CODE="+salGrade.getGradeCode()+" ";
				

		boolean result1 = getSqlModel().singleExecute(query1);

		return result1;
	}

	public boolean deleteGrade(SalaryGrade salGrade, String salCode) {
		
		boolean resultHdr = false;
		boolean resultDtl = false;
		String query = "DELETE FROM HRMS_SALGRADE_DTL WHERE SALGRADE_CODE="+salCode+" ";
		
		resultDtl = getSqlModel().singleExecute(query);
		
		if(resultDtl)
		{
			String query1 = "DELETE FROM HRMS_SALGRADE_HDR WHERE SALGRADE_CODE = "+salCode+"";
			
			resultHdr = getSqlModel().singleExecute(query1);
		}
		
		return resultHdr;
	}
	public String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "";
		}
		else{
			return result;
		}
		}


	public void Data(SalaryGrade bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String query ="SELECT SALGRADE_CODE, SALGRADE_TYPE FROM HRMS_SALGRADE_HDR ORDER BY SALGRADE_TYPE";
		Object obj[][] = getSqlModel().getSingleResult(query);
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),obj.length,20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));

		 if(pageIndex[4].equals("1"))
			   bean.setMyPage("1");	
			ArrayList<Object> list = new ArrayList<Object>();
			if (obj != null && obj.length > 0) {

			//	for (int i = 0; i < obj.length; i++) {
				 for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
		                
					  SalaryGrade   bean1 = new SalaryGrade();
					  bean1.setGradeCode(checkNull(String.valueOf(obj[i][0])));
						bean1.setGradeName(checkNull(String.valueOf(obj[i][1])));

						list.add(bean1);
				}
				
				 bean.setTotalRecords(""+obj.length);
			}
			else{				
				bean.setListLength("false");
				}			
			
		    if(list.size()>0)
		    	bean.setListLength("true");
		    else
		    	bean.setListLength("false");
		    //logger.info("length --> "+obj.length);
		    bean.setIteratorlist(list);	
	}

	public String[][] calforedit(SalaryGrade salGrade) {
		// TODO Auto-generated method stub
		String nameQuery = "SELECT SALGRADE_CODE, SALGRADE_TYPE FROM HRMS_SALGRADE_HDR WHERE SALGRADE_CODE = "+salGrade.getHiddencode()+"";
		Object[][] gradeName = getSqlModel().getSingleResult(nameQuery);
		salGrade.setGradeCode(checkNull(String.valueOf(gradeName[0][0])));
		salGrade.setGradeName(checkNull(String.valueOf(gradeName[0][1])));
		
		String query="SELECT HRMS_CREDIT_HEAD.CREDIT_CODE,HRMS_CREDIT_HEAD.CREDIT_NAME,NVL(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_AMT,'0') " 
			+"from HRMS_CREDIT_HEAD "
			+"left JOIN HRMS_SALGRADE_DTL ON (HRMS_SALGRADE_DTL.SALGRADE_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE and HRMS_SALGRADE_DTL.SALGRADE_CODE="+salGrade.getHiddencode()+") "
			+" ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
		Object[][] data = getSqlModel().getSingleResult(query);
		ArrayList list = new ArrayList();
		String[][] total = new String[data.length][3];
		for (int i = 0; i < data.length; i++) {
			SalaryGrade bean1 = new SalaryGrade();
			logger.info("---------------------------------------------"
					+ total.length);
			/*total[i][0] = String.valueOf(data[i][0]);
			total[i][1] = String.valueOf(data[i][1]);
			total[i][2] = String.valueOf(data[i][2]);*/
			bean1.setCreditCode(checkNull(String.valueOf(data[i][0])));
			bean1.setCreditName(String.valueOf(data[i][1]));
			//bean1.setCreditAmt(String.valueOf(data[i][2]));
			bean1.setSalAmt(String.valueOf(data[i][2]));
			list.add(bean1);
		}
		salGrade.setCreditlist(list);
		return total;
	}

	public boolean deletecheckedRecords(SalaryGrade salGrade, String[] code) {
		// TODO Auto-generated method stub
		
		/*		boolean resultHdr = false;
		boolean resultDtl = false;
		String query = "DELETE FROM HRMS_SALGRADE_DTL WHERE SALGRADE_CODE="+salCode+" ";
		
		resultDtl = getSqlModel().singleExecute(query);
		
		if(resultDtl)
		{
			String query1 = "DELETE FROM HRMS_SALGRADE_HDR WHERE SALGRADE_CODE = "+salCode+"";
			
			resultHdr = getSqlModel().singleExecute(query1);
		}
		
		return resultHdr;
	}
		 */
		boolean result=false;
		int count=0;
		boolean resultHdr = false;
		boolean resultDtl = false;
		if(code !=null)
		{
			for (int i = 0; i < code.length; i++) {
				
				if(!code[i].equals("")){
					
					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					String query = "DELETE FROM HRMS_SALGRADE_DTL WHERE SALGRADE_CODE="+code[i]+" ";
					
					resultDtl = getSqlModel().singleExecute(query);
					
					if(resultDtl)
					{
						String query1 = "DELETE FROM HRMS_SALGRADE_HDR WHERE SALGRADE_CODE = "+code[i]+"";
						
						resultHdr = getSqlModel().singleExecute(query1);
					}
					if(!resultHdr)
						count++;
					
				}//if
			}//for
		}//End of if

		if(count!=0)
		{	result=false;
			return result;
			}
		else
			{result=true;
			return result; }
	}
	
	

}
