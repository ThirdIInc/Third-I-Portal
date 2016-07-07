package org.paradyne.model.payroll;

import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.NdaAcqRoll;
 import org.paradyne.lib.ModelBase;

public class NdaAcqRollModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	String done="";

	
	public Object [][] getEmpId(NdaAcqRoll ndaAcqRoll)
	{
		Object emp_id[][]=null;
		try
		{
		String SQL="SELECT  DISTINCT HRMS_NDA.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME || ' '" 
							+" || HRMS_EMP_OFFC.EMP_MNAME ||' '|| HRMS_EMP_OFFC.EMP_LNAME AS EMP_NAME,"
							+" HRMS_DESG.DESG_DESC FROM HRMS_NDA"							
							+" INNER JOIN HRMS_EMP_OFFC ON(hrms_emp_offc.emp_id=hrms_nda.emp_id)"
							+" LEFT  JOIN HRMS_DESG ON(hrms_desg.desg_id=hrms_emp_offc.emp_desg)"
							//+" INNER JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
							//+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
							+" INNER JOIN HRMS_PAYBILL ON (HRMS_PAYBILL.PAYBILL_ID=HRMS_EMP_OFFC.EMP_PAYBILL)"
							+" WHERE HRMS_NDA.NDA_MONTH BETWEEN "+ndaAcqRoll.getMonth1()+" and "+ndaAcqRoll.getMonth2()
							+" AND HRMS_NDA.NDA_YEAR="+ndaAcqRoll.getYear();
		
		//String where=" where hrms_nda.nda_month between"+ndaAcqRoll.getMonth1()+" and "+ndaAcqRoll.getMonth2()
			//		+" and hrms_nda.nda_year="+ndaAcqRoll.getYear();
		String where="";
		String center=ndaAcqRoll.getCenterNo();
		String type=ndaAcqRoll.getTypeCode();
		String payBill=ndaAcqRoll.getPayBilGrp();
		
		/*if (center != null && center.length() != 0) {
			where = where + " AND HRMS_EMP_OFFC.EMP_CENTER="
					+ center;
		}*/
		if (type != null && type.length() != 0) {
			where = where + " AND HRMS_EMP_OFFC.EMP_TYPE="
					+ type;
		}if (payBill != null && payBill.length() != 0) {
			where = where + " AND HRMS_PAYBILL.PAYBILL_ID="
			+ payBill;
         }
		
		
		emp_id = getSqlModel().getSingleResult(SQL+where);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			return emp_id;
	}
	public Object [][]getRow(String emp_id,NdaAcqRoll ndaAcqRoll){
		//Get all NDA details for a employee
		String SQL="Select nda_month,nda_amount from hrms_nda where emp_id="+emp_id+" and nda_year="+ndaAcqRoll.getYear()
                  +" and nda_month between "+ndaAcqRoll.getMonth1()+" and "+ndaAcqRoll.getMonth2();
		
		Object[][] ndaDetails= getSqlModel().getSingleResult(SQL);
		
		
		return ndaDetails;
	}
	
	public Object [][]getRows(int month1,int month2,String []colNames,String []MONTH,NdaAcqRoll ndaAcqRoll){
		Object emp_id[][]=getEmpId(ndaAcqRoll);
		int c=((month2-month1)+1)+6;
		int r=0;
		r+=emp_id.length+1;
		
		Object [][] rows=new Object[r][c];
		logger.info("\n\n\nROWS========>"+ r);
		logger.info("\n\n\nCOLUMNS========>"+ c);
		double MonthTotal[]=new double [Math.abs(month2-month1)+1+6];
		double gNetPay=0;
		for(int i=0;i<r-1;i++){
			double netPay=0.0;			
			rows[i][0]=""+(i+1);//S.No.
			rows[i][1]=emp_id[i][1];//TOKEN NO
			rows[i][2]=emp_id[i][2];//NAME
			rows[i][3]=checkNull(String.valueOf(emp_id[i][3]));//DESGNATION
			
	 	    //Get Nda details from HRMS_NDA
			Object[][] row=getRow(String.valueOf(emp_id[i][0]),ndaAcqRoll);
	 	    
            for(int j=0;j<row.length;j++){
            	logger.info("\n\nROWS[][]----------->"+(Integer.parseInt(String.valueOf(row[j][0]))+3));            	
            	int mnth=Integer.parseInt(String.valueOf(row[j][0]));
            	logger.info("\n\n\n\n\n=======MONTH==========="+mnth);
            	
            	for(int k=4;k<colNames.length-2;k++){
            		if(colNames[k].equals(MONTH[mnth-1])){
            			rows[i][k]=row[j][1];
            			MonthTotal[k]+=Math.round(Float.parseFloat(String.valueOf(row[j][1])));
            			netPay=netPay+Math.round(Float.parseFloat(String.valueOf(row[j][1])));
            		}if(!colNames[k].equals(MONTH[mnth-1]) && rows[i][k]==null){
            			rows[i][k]="0";
            			if(MonthTotal[k]==0){MonthTotal[k]=0;}
            			MonthTotal[k]+=Math.round(Float.parseFloat(String.valueOf(rows[i][k])));            			
            		}
            		
            	}
            	logger.info("-->"+MONTH[mnth-1]);
            	logger.info("NET PAY--->"+netPay);            	
            }
            rows[i][colNames.length-2]="Rs."+netPay;
            rows[i][colNames.length-1]="";
            gNetPay+=netPay;
		}		
	    rows[r-1][0]="";
		rows[r-1][1]="";
		rows[r-1][2]="";
		rows[r-1][3]="Page Total.";
		rows[r-1][colNames.length-2]="Rs."+gNetPay;
		rows[r-1][colNames.length-1]="";
		
		for(int ct=4;ct<MonthTotal.length-2;ct++){
			rows[r-1][ct]=MonthTotal[ct];
		}
		return rows;
	}
	
	public String  generateReport(NdaAcqRoll ndaAcqRoll,HttpServletResponse response){
//		0--left align, 1--align center, 2--right align,3-- justified align				
		logger.info("CENTER NO-->"+ndaAcqRoll.getCenterNo());
		logger.info("EMP TYPE-->"+ndaAcqRoll.getEmpType());
		logger.info("YEAR-->"+ndaAcqRoll.getYear());
		logger.info("MONTH1-->"+ndaAcqRoll.getMonth1());
		logger.info("MONTH2-->"+ndaAcqRoll.getMonth2());
		logger.info("REPORT TYPE-->"+ndaAcqRoll.getRptTyp());
		logger.info("PAYBILL GRP-->"+ndaAcqRoll.getPayBilGrp());
		
		//1. Generate Column headings for the Report
		String[] MONTH={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
		int month1=Integer.parseInt(ndaAcqRoll.getMonth1());
		int month2=Integer.parseInt(ndaAcqRoll.getMonth2());
		
		String reportType=""+ndaAcqRoll.getRptTyp();		
		String reportName="NIGHT DUTY ALLOWANCE ACQUITTANCE ROLL FOR THE MONTH OF"+MONTH[month1-1]+" TO "+MONTH[month2-1]+" "+ndaAcqRoll.getYear();
		
		String[] colNames=new String[Math.abs(month2-month1)+1+6];
		int [] cellWidth=new int[colNames.length];
		int [] alignment=new int[colNames.length];	
		
		
		colNames[0]="S.No.";
		colNames[1]="Pers.No.";
		colNames[2]="Name";
		colNames[3]="Designation";
		colNames[colNames.length-2]="NET PAY";
		colNames[colNames.length-1]="Signature";
		int temp=month1;
		for(int i=4;i<colNames.length-2;temp++,i++){
			colNames[i]=MONTH[temp-1];		
		}
		
		
		cellWidth[0]=25;
		cellWidth[1]=45;
		cellWidth[2]=120;
		cellWidth[3]=45;
		cellWidth[cellWidth.length-2]=50;
		cellWidth[cellWidth.length-1]=40;
		for(int l=4;l<cellWidth.length-2;l++){
			cellWidth[l]=40;
		}
				
		alignment[0]=1;
		alignment[1]=1;
		alignment[2]=1;
		alignment[3]=1;
		alignment[cellWidth.length-2]=2;
		alignment[cellWidth.length-1]=1;
		for(int a=4;a<cellWidth.length-2;a++){
			alignment[a]=1;
		}
				
		//2. Generate Data for the Report		
	try{				
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,reportName);		
		rg.genHeader("");
		
		String []message={"PAY BILL NO. "+ndaAcqRoll.getPayBilGrp(),"                           ","PART NO.","                     ","CBI NO. OF"};
		int []style={1};
		rg.addFormatedText(message, style, 0, 1, 0);
		
		//Table body	
		Object rows[][]=getRows(month1,month2,colNames,MONTH,ndaAcqRoll);
		rg.addFormatedText("PAY BILL NO. "+ndaAcqRoll.getPayBilGrp()+"               PART NO."+"               CBI NO.", 1, 1, 0, 0);
		rg.tableBody(colNames,rows,cellWidth,alignment);				
			
		Object [][]rows1=new Object[1][5];
		int cellWidth1[]={44,44,44,44,45};		
		
		if(reportType.equals("Pdf")){
			rg.createReport(response);
		}else{
			rg.createReport(response);
			//String file="NIGHTDUTYALLOWANCEACQUITTANCEROLLFORTHEMONTHOF"+MONTH[month1-1]+"TO"+MONTH[month2-1]+""+ndaAcqRoll.getYear();
			//new org.paradyne.lib.report.Report().showReport("C:/"+file+"."+ndaAcqRoll.getRptTyp());
		}
		
		
		//String file="NIGHTDUTYALLOWANCEACQUITTANCEROLLFORTHEMONTHOF"+MONTH[month1-1]+"TO"+MONTH[month2-1]+""+ndaAcqRoll.getYear();
		/*if(ndaAcqRoll.getRptTyp().equals("Pdf")){
			String path=rg.creatorPath(getServletContext(), file);
			new org.paradyne.lib.report.Report().showReport(path+""+file+"."+ndaAcqRoll.getRptTyp());
		}if(ndaAcqRoll.getRptTyp().equals("Xls")){
			new org.paradyne.lib.report.Report().showReport("C:/"+file+"."+ndaAcqRoll.getRptTyp());
		}*/
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
		return "";	
	}	
	
	
	public String checkNull(String result) {
		logger.info("\n\n\n\n****HEMANT");
		if (result == null || result.equals("null")){
			return "";
		} else {
			return result;
		}
	}	
}