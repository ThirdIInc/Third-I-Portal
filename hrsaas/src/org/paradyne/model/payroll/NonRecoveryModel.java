
package org.paradyne.model.payroll;

import java.util.ArrayList;


import org.paradyne.bean.payroll.NonRecovery;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Hemant
 *
 */
public class NonRecoveryModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	public void generateReport(NonRecovery bean,HttpServletResponse response){		
		
		
		String[] colNames;
		int [] cellWidth;
		int [] alignment;
		Object rows[][]=null;
		
		
		logger.info("pppppp"+bean.getPayBill());
		
		//If Debit Head is not selected 
		if(bean.getDebitCode().length()<=0 || bean.getDebitCode().equals("")){
			String  dbSQL="SELECT NVL(DEBIT_NAME,' ') FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE";
			Object dbData[][]=getSqlModel().getSingleResult(dbSQL);
			int c=0;
			if(dbData.length>0){
				c=dbData.length;
			}
			colNames=new String[c+3+1];
			cellWidth=new int[c+3+1];
            alignment=new int[c+3+1];
            
//          ADD COLUMN HEADINGS
    		colNames[0]="Token Number";
    		colNames[1]="Name";
    		colNames[2]="Account Number";
    		colNames[colNames.length-1]="Net Amount";
    		
    		//ADD CELL WIDTH
    		cellWidth[0]=15;
    		cellWidth[1]=30;
    		cellWidth[2]=25;
    		cellWidth[cellWidth.length-1]=25;
    		
    		//ADD ALIGNMENT
    		alignment[0]=0;
    		alignment[1]=0;
    		alignment[2]=0;
    		alignment[alignment.length-1]=0;
    		
    		//ADD COL HEADINGS/CELL WIDTH/ALLIGNMENT
    		for(int i=3;i<colNames.length-1;i++){
    			
    			if(String.valueOf(dbData[i-3][0]).equals("null")) {
    				colNames[i]="";
    			}else {
    				colNames[i]=String.valueOf(dbData[i-3][0]);
    			    				
    			}
    			cellWidth[i]=15;
    			alignment[i]=2;
    		}
		}
		//If Debit Head is selected
		else{
			String  dbSQL="SELECT DEBIT_NAME FROM HRMS_DEBIT_HEAD WHERE DEBIT_CODE="+bean.getDebitCode();
			Object dbData[][]=getSqlModel().getSingleResult(dbSQL);
			
			colNames=new String[1+3+1];
			cellWidth=new int[1+3+1];
            alignment=new int[1+3+1];
            
            //ADD COLUMN HEADINGS
    		colNames[0]="Token Number";
    		colNames[1]="Name";
    		colNames[2]="Account Number";
    		colNames[3]=String.valueOf(dbData[0][0]);    		
    		colNames[colNames.length-1]="Net Amount";
    		
    		//ADD CELL WIDTH
    		cellWidth[0]=15;
    		cellWidth[1]=30;
    		cellWidth[2]=25;
    		cellWidth[3]=25;
    		cellWidth[cellWidth.length-1]=25;
    		
    		//ADD ALIGNMENT
    		alignment[0]=0;
    		alignment[1]=0;
    		alignment[2]=0;
    		alignment[3]=0;
    		alignment[alignment.length-1]=0;
		}
		try{			
			String title="Non Recovery";
			String type="Xls";
			String MONTH[]={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
			org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator("Xls",title);
			rg.genHeader("");			
			Object data[][]=getRows(colNames,bean);
			
			Object finalData[][]=getTotalAmount(data);					
			
			rg.addText("Non Recovery for "+MONTH[Integer.parseInt(bean.getMonth())-1]+"-"+bean.getYear(), 0, 0, 0);
			if(bean.getDebitHead().equals("")) {
				rg.addText("Debit Head :",0,0,0);
				
			}else {
				rg.addText("Debit Head :"+bean.getDebitHead(),0,0,0);
			}
			
			if(bean.getPayBill().equals("")) {
				rg.addText("Pybill Group :",0,0,0);
				
			} else {
				String query="SELECT PAYBILL_GROUP FROM HRMS_PAYBILL WHERE PAYBILL_ID="+bean.getPayBill();
				Object Data[][]=getSqlModel().getSingleResult(query);
				rg.addText("Paybill Group :"+String.valueOf(Data[0][0]),0,0,0);
			}
			
			rg.tableBody(colNames, finalData, cellWidth,alignment);
			rg.createReport(response);			
		}catch(Exception e){
			e.printStackTrace();
		}
}
	public Object [][]getTotalAmount(Object data[][]){
		Object finalData[][]=null;
		////GRAND TOTAL FOR AMOUNT
		if(data.length>0){
			finalData=new Object[data.length+1][data[0].length];
			
			for (int i = 0; i < finalData[0].length; i++) {
				finalData[data.length][i]="";
			}
			
			double total=0.0;				
			for(int i=0;i<finalData.length-1;i++){
				finalData[i]=data[i];	
				
					try {
						total += Double
								.parseDouble(String
										.valueOf(finalData[i][finalData[0].length - 1]));
					} catch (Exception e) {
						// TODO: handle exception
					}		
					
				
			}
			finalData[finalData.length-1][finalData[0].length-1]=""+total;
		}else{
			finalData=data;
		}
		return finalData;
	}

	//Get all debit heads for a particular employee
	public Object [][]getRows(String colNames[],NonRecovery bean){
		
		
		Object emp_id[][]=getEmpId(bean);
		int c=0;
		int r=0;
		r+=emp_id.length;
		c+=colNames.length;
				
		Object rows[][]=new Object[r][c];
		for(int i=0;i<emp_id.length;i++){
			//STORE THE INITIAL 3 values in the Object array
			if(String.valueOf(emp_id[i][0]).equals("null")){
				rows[i][0]="";
			}else {
			rows[i][0]=emp_id[i][0];//TOKEN
			}
			logger.info("Value of token no."+rows[i][0]);
			if(String.valueOf(emp_id[i][1]).equals("null")){
				rows[i][1]="";
			}else {
			rows[i][1]=emp_id[i][1];//NAME
			}
			if(String.valueOf(emp_id[i][2]).equals("null")){
				rows[i][2]="";
			}else {
			
			rows[i][2]=emp_id[i][2];//ACCOUNT NUMBER
			}
			logger.info("Value of emp name no."+rows[i][1]);
			logger.info("Value of account no."+rows[i][2]);
			
			Object[][] row=getRow(String.valueOf(emp_id[i][3]),bean);
			logger.info("Value of row in pradip"+ row.length);
		//	if(row.length > 0){
			double total=0;
			for(int j=0;j<row.length;j++){							
				for(int k=3;k<colNames.length-1;k++){
					rows[i][k] ="0";
					if(colNames[k].equals(String.valueOf(row[j][0]))){
						rows[i][k]=row[j][1];
						
						try {
							total+=Float.parseFloat(String.valueOf(row[j][1]));	
					    }catch(Exception e) { }
					}
				}			
			}
			rows[i][c-1]=""+total;
			//}
		}
		return rows;
	}
	public Object[][] getRow(String emp_id,NonRecovery bean){
		logger.info("\n\n>>>>getRow()......");
		int month=0;
		month=Integer.parseInt(bean.getMonth());
		month++;
		String SQL="SELECT NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' '),NVL(DEBIT_AMT,0) FROM HRMS_SAL_DEDUCTION_"+bean.getYear()
				  +" INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_SAL_DEDUCTION_"+bean.getYear()+".DEBIT_CODE)"
				  +" WHERE EMP_ID="+emp_id
			//	  +" AND mth="+bean.getMonth()
				  +" AND mth="+month
				  +" AND HRMS_SAL_DEDUCTION_"+bean.getYear()+".DEBIT_CODE IS NOT NULL";
		
		if(bean.getDebitCode().length()>0){		
		    SQL+=" AND HRMS_SAL_DEDUCTION_"+bean.getYear()+".DEBIT_CODE="+bean.getDebitCode();
		}
		Object data[][]=getSqlModel().getSingleResult(SQL);
		return data;
	}
	
	//Get all employees in the Deductions table
	public Object [][] getEmpId(NonRecovery bean){		
		int month=0;
		month=Integer.parseInt(bean.getMonth());
		month++;
		String 	SQL="SELECT  DISTINCT NVL(HRMS_EMP_OFFC.EMP_TOKEN, ' '), NVL(EMP_FNAME,' ') || ' ' || NVL(EMP_MNAME,' ') || ' ' || NVL(EMP_LNAME,' '),"
				   +" NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,''),HRMS_EMP_OFFC.EMP_ID FROM HRMS_SAL_DEDUCTION_"+bean.getYear()
				   +" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SAL_DEDUCTION_"+bean.getYear()+".EMP_ID)"
				   +" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_SAL_DEDUCTION_"+bean.getYear()+".EMP_ID)"
				   +" LEFT JOIN HRMS_SALARY_"+bean.getYear()+" ON(HRMS_SALARY_"+bean.getYear()+".EMP_ID=HRMS_SAL_DEDUCTION_"+bean.getYear()+".EMP_ID)"
				   +" WHERE HRMS_SAL_DEDUCTION_"+bean.getYear()+".MTH="+month+" AND HRMS_SAL_DEDUCTION_"+bean.getYear()+".DEBIT_CODE IS NOT NULL";				   
		if(bean.getDebitCode().length()>0){		
			    SQL+=" AND HRMS_SAL_DEDUCTION_"+bean.getYear()+".DEBIT_CODE="+bean.getDebitCode();
		}	
		
		
		
		if(!(bean.getPayBill().equals(""))){ 
			
			SQL+=  " AND HRMS_SALARY_"+bean.getYear()+".SAL_EMP_PAYBILL="+bean.getPayBill();
		}
		
		Object data[][]=getSqlModel().getSingleResult(SQL);
		
		return data;		
	}
	
	
}
