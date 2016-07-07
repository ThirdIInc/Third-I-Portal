package org.paradyne.model.Promotion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.PAS.AppraisalMisReport;
import org.paradyne.bean.payroll.EmpCredit;
import org.paradyne.bean.pramotion.PramotionMaster;
import org.paradyne.bean.promotion.PromotionMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.report.ReportGenerator;
import org.nfunk.jep.*;
import org.lsmp.djep.xjep.*;
import org.paradyne.lib.Utility;

import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;


public class PromotionReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.paradyne.lib.ModelBase.class);
public boolean report(PromotionMaster promotion,HttpServletResponse response) 
	{

		String name="Application ";
		String type="Pdf";
		String title="Promotion Letter";
		
		org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator(type,title);
	 	rg=getHeader(rg,promotion);	
		rg.createReport(response);
		return true;
		
	}


	private ReportGenerator getHeader(ReportGenerator rg, PromotionMaster promotion) {
		try {
			
		
		Object [] eCode=new Object[2];
		eCode[0]=promotion.getEmpCode();
		logger.info("eeeeeeeee==="+eCode[0]);
		eCode[1]=promotion.getRepToCode();
		logger.info("rrrrrrrrrrrr==="+eCode[1]);
	
		
		String query="SELECT DISTINCT PROM_CODE,T0.TITLE_NAME||' '||E0.EMP_FNAME||' '||E0.EMP_LNAME,E0.EMP_TOKEN,"+
			" C1.CENTER_NAME,C2.CENTER_NAME PROCENTER,DP1.DEPT_NAME ,DP2.DEPT_NAME PRODEPT,R1.RANK_NAME,R2.RANK_NAME PRORANK, nvl(CD1.CADRE_NAME,' '),nvl(CD2.CADRE_NAME,' ') PROCADRE, DV1.DIV_NAME,DV2.DIV_NAME PRODIV,"+
			" TO_CHAR(DATE_JOINING,'DD-MM-YYYY'),TO_CHAR(PROM_DATE,'DD-MM-YYYY'),TO_CHAR(EFFECT_DATE,'ddth Mon,yyyy'), "+
			" NVL(REPORTING_TO,'0'),EMP_CODE,TO_CHAR(EFFECT_DATE,'yyyy')-1||'-'||TO_CHAR(EFFECT_DATE,'yyyy'),TO_CHAR(SYSDATE,'dd-mm-yyyy'),nvl(RATING,'0'),PROM_PROMFLAG,"+ 
			" E0.EMP_FNAME||' '||E0.EMP_LNAME"+
			" FROM  DUAL,HRMS_PROMOTION"+
			" LEFT JOIN HRMS_EMP_OFFC E0 ON(E0.EMP_ID = HRMS_PROMOTION.EMP_CODE)"+ 
		
			" LEFT JOIN HRMS_TITLE T0 ON(T0.TITLE_CODE=E0.EMP_TITLE_CODE)"+
			" LEFT JOIN HRMS_CENTER C1 ON(C1.CENTER_ID=HRMS_PROMOTION.BRANCH_CODE)"+
			" LEFT JOIN HRMS_RANK R1 ON(R1.RANK_ID=HRMS_PROMOTION.DESG_CODE)"+ 
			" LEFT JOIN HRMS_CADRE CD1 ON(CD1.CADRE_ID=HRMS_PROMOTION.GRADE_CODE)"+ 
		    " LEFT JOIN HRMS_DEPT DP1 ON(DP1.DEPT_ID=HRMS_PROMOTION.DEPT_CODE)"+ 
		    " LEFT JOIN HRMS_DIVISION DV1 ON(DV1.DIV_ID = HRMS_PROMOTION.DIV_CODE)"+
		    " LEFT JOIN HRMS_CENTER C2 ON(C2.CENTER_ID=HRMS_PROMOTION.PRO_BRANCH)"+ 
		    " LEFT JOIN HRMS_RANK R2 ON(R2.RANK_ID=HRMS_PROMOTION.PRO_DESG)"+ 
		    " LEFT JOIN HRMS_CADRE CD2 ON(CD2.CADRE_ID=HRMS_PROMOTION.PRO_GRADE)"+ 
		    " LEFT JOIN HRMS_DEPT DP2 ON(DP2.DEPT_ID=HRMS_PROMOTION.PRO_DEPT)"+
		    " LEFT JOIN HRMS_DIVISION DV2 ON(DV2.DIV_ID = HRMS_PROMOTION.PRO_DIV)"+ 
		    " WHERE HRMS_PROMOTION.PROM_CODE="+promotion.getPromCode()+
		    " ORDER BY PROM_CODE";

		  Object[][] data=getSqlModel().getSingleResult(query);
			
		  for(int i=0;i<data.length;i++)
		  {
			  logger.info("krishna");
		  String query1="SELECT DISTINCT  (SELECT HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC INNER JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) WHERE EMP_REPORTING_OFFICER = "+data[i][16]+" AND EMP_ID ="+data[i][17]+") REPTO,"+
			 		
			 			" NVL(T1.TITLE_NAME||' '||E1.EMP_FNAME||' '||E1.EMP_MNAME||' '||E1.EMP_LNAME,' ') PROREP , NVL(T2.TITLE_NAME||' '||E2.EMP_FNAME||' '||E2.EMP_MNAME||' '||E2.EMP_LNAME,' ') PRO_BY,NVL(T3.TITLE_NAME||' '||E3.EMP_FNAME||' '||E3.EMP_MNAME||' '||E3.EMP_LNAME,' ') APPR_BY"+
			 			" ,NVL(STRENGTH,' '),NVL(WEAKNESS,' '),NVL(RATING,'0')"+
			 		//	" HRMS_PROMOTION_SALARY.SAL_CODE,CREDIT_NAME,OLD_AMT,NEW_AMT,PROM_SAL_CODE"+
			 			" FROM HRMS_PROMOTION"+
			 			" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_REPORTING_OFFICER = HRMS_PROMOTION.REPORTING_TO)"+
			 			" LEFT JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID= HRMS_PROMOTION.PRO_REPORT_TO)"+
			 			" LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=E1.EMP_TITLE_CODE)"+
			 			" LEFT JOIN HRMS_EMP_OFFC E2 ON(E2.EMP_ID= HRMS_PROMOTION.PROPOSED_BY)"+
			 			" LEFT JOIN HRMS_TITLE T2 ON(T2.TITLE_CODE=E2.EMP_TITLE_CODE)"+
			 			" LEFT JOIN HRMS_EMP_OFFC E3 ON(E3.EMP_ID= HRMS_PROMOTION.APPROVED_BY)"+
			 			" LEFT JOIN HRMS_TITLE T3 ON(T3.TITLE_CODE=E3.EMP_TITLE_CODE)"+
			 		//	" LEFT JOIN HRMS_APPRAISAL ON(HRMS_APPRAISAL.APPR_SCORE = HRMS_PROMOTION.RATING)"+
			 			" WHERE PROM_CODE="+data[i][0];
			 		//	" LEFT JOIN HRMS_PROMOTION_SALARY ON(HRMS_PROMOTION_SALARY.PROM_CODE= HRMS_PROMOTION.PROM_CODE)"+
			 	//		" LEFT JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_ID=HRMS_PROMOTION_SALARY.SAL_CODE)"+
			 			
		  
		  Object[][] data1=getSqlModel().getSingleResult(query1);
		  
		  
	/*  String salquery ="SELECT CREDIT_NAME,NEW_AMT,TOTAL_NEW_AMOUNT,nvl(CASE WHEN CREDIT_PERIODICITY = 'M' THEN NEW_AMT * 12"+
	  					" WHEN CREDIT_PERIODICITY = 'Q' THEN NEW_AMT * 4  WHEN CREDIT_PERIODICITY = 'H' THEN NEW_AMT * 2"+
	  					" WHEN CREDIT_PERIODICITY = 'A' THEN NEW_AMT ELSE NEW_AMT end,0) as sum,DECODE(credit_periodicity,'M','Monthly','A','Annually','H','Half Yearly','Q','Quarterly',NULL,' ')"+ 
	  					" FROM HRMS_PROMOTION_SALARY"+ 
	  					" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PROMOTION_SALARY.SAL_CODE)"+
	  					" LEFT JOIN HRMS_PROMOTION ON(HRMS_PROMOTION.PROM_CODE = HRMS_PROMOTION_SALARY.PROM_CODE)"+
	  					" WHERE HRMS_PROMOTION.EMP_CODE="+data[i][17]+" AND HRMS_PROMOTION.PROM_CODE="+data[i][0]+" AND NEW_AMT <> '0'"+
	  					" ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";

	  Object[][] salData=getSqlModel().getSingleResult(salquery);*/
		  
		  String salMthQue =" SELECT CREDIT_NAME,NVL(NEW_AMT,'0'),TOTAL_NEW_AMOUNT,nvl(CASE WHEN CREDIT_PERIODICITY = 'M' THEN NEW_AMT * 12  ELSE NEW_AMT end,0) as sum,DECODE(credit_periodicity,'M','Monthly',NULL,' ') FROM HRMS_PROMOTION_SALARY LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PROMOTION_SALARY.SAL_CODE) LEFT JOIN HRMS_PROMOTION ON(HRMS_PROMOTION.PROM_CODE = HRMS_PROMOTION_SALARY.PROM_CODE) WHERE HRMS_PROMOTION.PROM_CODE="+promotion.getPromCode()+" AND NEW_AMT <> '0'"+ 
			" AND CREDIT_PERIODICITY ='M'"+
			" ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
		  Object[][] salMthData=getSqlModel().getSingleResult(salMthQue);

			String salQuaQue =" SELECT CREDIT_NAME,NVL(NEW_AMT,'0'),TOTAL_NEW_AMOUNT,nvl(CASE WHEN CREDIT_PERIODICITY = 'Q' THEN NEW_AMT * 4  ELSE NEW_AMT end,0) as sum,DECODE(credit_periodicity,'Q','Quarterly',NULL,' ') FROM HRMS_PROMOTION_SALARY LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PROMOTION_SALARY.SAL_CODE) LEFT JOIN HRMS_PROMOTION ON(HRMS_PROMOTION.PROM_CODE = HRMS_PROMOTION_SALARY.PROM_CODE) WHERE  HRMS_PROMOTION.PROM_CODE="+promotion.getPromCode()+" AND NEW_AMT <> '0'"+ 
			" AND CREDIT_PERIODICITY ='Q'"+
			" ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			Object[][] salQuaData=getSqlModel().getSingleResult(salQuaQue);

		String salHalfQue =" SELECT CREDIT_NAME,NVL(NEW_AMT,'0'),TOTAL_NEW_AMOUNT,nvl(CASE WHEN CREDIT_PERIODICITY = 'H' THEN NEW_AMT * 2  ELSE NEW_AMT end,0) as sum,DECODE(credit_periodicity,'H','Half Yearly',NULL,' ') FROM HRMS_PROMOTION_SALARY LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PROMOTION_SALARY.SAL_CODE) LEFT JOIN HRMS_PROMOTION ON(HRMS_PROMOTION.PROM_CODE = HRMS_PROMOTION_SALARY.PROM_CODE) WHERE HRMS_PROMOTION.PROM_CODE="+promotion.getPromCode()+" AND NEW_AMT <> '0'"+ 
		" AND CREDIT_PERIODICITY ='H'"+
		" ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
		Object[][] salHalfData=getSqlModel().getSingleResult(salHalfQue);

		String salAnnQue =" SELECT CREDIT_NAME,NVL(NEW_AMT,'0'),TOTAL_NEW_AMOUNT,nvl(CASE WHEN CREDIT_PERIODICITY = 'A' THEN NEW_AMT * 1  ELSE NEW_AMT end,0) as sum,DECODE(credit_periodicity,'A','Annual',NULL,' ') FROM HRMS_PROMOTION_SALARY LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PROMOTION_SALARY.SAL_CODE) LEFT JOIN HRMS_PROMOTION ON(HRMS_PROMOTION.PROM_CODE = HRMS_PROMOTION_SALARY.PROM_CODE) WHERE HRMS_PROMOTION.PROM_CODE="+promotion.getPromCode()+" AND NEW_AMT <> '0'"+ 
		" AND CREDIT_PERIODICITY ='A'"+
		" ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
		Object[][] salAnnData=getSqlModel().getSingleResult(salAnnQue);	  
			  
	  String infoQuery="SELECT DISTINCT T0.TITLE_NAME||' '||E0.EMP_FNAME||' '||E0.EMP_LNAME,RANK_NAME,DEPT_NAME ,CENTER_NAME FROM  HRMS_PROMOTION "
	  					+"	LEFT JOIN HRMS_EMP_OFFC E0 ON(E0.EMP_ID = HRMS_PROMOTION.EMP_CODE)" 
	  					+"	LEFT JOIN HRMS_TITLE T0 ON(T0.TITLE_CODE=E0.EMP_TITLE_CODE) "
	  					+"	LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_PROMOTION.PRO_DESG)"
	  					+"	LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID = HRMS_PROMOTION.PRO_DEPT)"
	  					+"	LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_PROMOTION.PRO_BRANCH) WHERE PROM_CODE="+data[i][0];
	  
	  Object[][] infoData=getSqlModel().getSingleResult(infoQuery);
	  
	 /* String pfQuery="SELECT PF_PERCENTAGE FROM HRMS_PF_CONF WHERE TO_CHAR(PF_DATE,'dd-MON-yyyy') = "
	  		             +"( SELECT MAX(PF_DATE)FROM HRMS_PF_CONF WHERE TO_CHAR(PF_DATE,'yyyy')<=(SELECT TO_CHAR(SYSDATE,'yyyy') FROM dual))";
	  
	  Object[][] pfData=getSqlModel().getSingleResult(pfQuery);
	*/
	


	  
	 // logger.info("pfData====="+pfData.length);
	//  Object[][] show = new Object[salData.length+1][4];
	  Object[][] show = new Object[1][4];
	  Object[][] showMth = new Object[salMthData.length][4];
	  Object[][] showQua = new Object[salQuaData.length][4];
	  Object[][] showHalf = new Object[salHalfData.length][4];
	  Object[][] showAnn = new Object[salAnnData.length][4];
	  Object[][] showInfo = new Object[4][2];
	  int j=0;
	 
	  double annsum=0.0;
	  double annsumMth=0.0;
	  double annsumQua=0.0;
	  double annsumHalf=0.0;
	  double annsumAnn=0.0;
	  double annSumGrs =0.0;
	  double annsumMthGrs =0.0;
	  double annsumAnnGrs =0.0;
	  double annsumQuaGrs =0.0;
	  double annsumHalfGrs =0.0;
	
	  String dateToPrint = "";
	  
	  if(promotion.getPromDate() != null && promotion.getPromDate().equals("") )
		  dateToPrint = String.valueOf(data[0][19]);
	  else
		  dateToPrint = promotion.getPromDate();
	  
		int []cellwidthInfo={50,50}; 
		int []cellwidth={30,20,20,15};
		int []alignmnetInfo={0,0};
		int []alignmnet={0,2,2,1};	

		/* if(salData.length<0)
		  {
			 Object [][]data2= new Object[1][1];
			 data2[0][0] ="Salary is not present";
			  rg.tableBody(data2, cellwidth, alignmnet);
		  }
	  */
		String PerformData[][]= null;
		int w1[]={100};
		int a1[]={0};
	  for(j = 0; j < salMthData.length; j++)
	  {
		  showMth[j][0] = String.valueOf(salMthData[j][0]);
		  showMth[j][1] =Math.round(Double.parseDouble(String.valueOf(salMthData[j][1])));
		  showMth[j][2] =Math.round(Double.parseDouble(String.valueOf(salMthData[j][3])));
		  showMth[j][3] = String.valueOf(salMthData[j][4]);
		  logger.info("showMth[j][1]===="+showMth[j][1]);
		 
		  annsumMth+= (Double.parseDouble(String.valueOf(salMthData[j][1])));
		  annsumMthGrs+=(Double.parseDouble(String.valueOf(salMthData[j][3])));
		 
		  if(String.valueOf(showMth[j][0]).indexOf("*")>=0)
				  {
			  		PerformData = new String[1][1];
					PerformData[0][0]="* The performance Pay will be released in the month of June/ July of every year based on the Performance Appraisal Rating.";
			
				  }
		 
	  }
	  for(int j1 = 0; j1 < salQuaData.length; j1++)
	  {
		  showQua[j1][0] = String.valueOf(salQuaData[j1][0]);
		  showQua[j1][1] =Math.round(Double.parseDouble(String.valueOf(salQuaData[j1][1])));
		  showQua[j1][2] = Math.round(Double.parseDouble(String.valueOf(salQuaData[j1][3])));
		  showQua[j1][3] = String.valueOf(salQuaData[j1][4]);

		  annsumQua+= Double.parseDouble(String.valueOf(showQua[j1][1]));
		  annsumQuaGrs +=Double.parseDouble(String.valueOf(showQua[j1][2]));
		  
		  if(String.valueOf(showQua[j1][0]).indexOf("*")>=0)
		  {
			PerformData = new String[1][1];
			PerformData[0][0]="* The performance Pay will be released in the month of June/ July of every year based on the Performance Appraisal Rating.";
			
		  }
 
	  }
	 
	  for(int j3 = 0; j3 < salHalfData.length; j3++)
	  {
		  showHalf[j3][0] = String.valueOf(salHalfData[j3][0]);
		  showHalf[j3][1] =Math.round(Double.parseDouble(String.valueOf(salHalfData[j3][1])));
		  showHalf[j3][2] = Math.round(Double.parseDouble(String.valueOf(salHalfData[j3][3])));
		  showHalf[j3][3] = String.valueOf(salHalfData[j3][4]);

		  annsumHalf+= Double.parseDouble(String.valueOf(showHalf[j3][1]));
		  annsumHalfGrs +=Double.parseDouble(String.valueOf(showHalf[j3][2]));
		  
		  if(String.valueOf(showHalf[j3][0]).indexOf("*")>=0)
		  {
			PerformData = new String[1][1];
			PerformData[0][0]="* The performance Pay will be released in the month of June/ July of every year based on the Performance Appraisal Rating.";
		
		  }
	  }
	  
	  for(int j4 = 0; j4 < salAnnData.length; j4++)
	  {
		  showAnn[j4][0] = String.valueOf(salAnnData[j4][0]);
		  showAnn[j4][1] = "";
		  showAnn[j4][2] = Math.round(Double.parseDouble(String.valueOf(salAnnData[j4][1])));
		  showAnn[j4][3] = String.valueOf(salAnnData[j4][4]);
	//	  Math.round(Double.parseDouble(String.valueOf(salAnnData[j4][3])));
		 // annsumAnn+= Double.parseDouble(String.valueOf(showAnn[j4][1]));
		  annsumAnnGrs +=Double.parseDouble(String.valueOf(showAnn[j4][2]));
		  
		  if(String.valueOf(showAnn[j4][0]).indexOf("*")>=0)
		  {
			PerformData = new String[1][1];
			PerformData[0][0]="* The performance Pay will be released in the month of June/ July of every year based on the Performance Appraisal Rating.";
		  }
	  }
	 

	  double pfper = 0.00;
	  double basic = 0.00;
	  double pfpermon = 0.00;
	  double pfperanum =0.00;
       //pf row   pf  ---- per month ?-------per anum-------periodicity
	  
	  double annsumMthPf = 0.00;
		 
		 double annsumMthPfGrs = 0.00;
		 
	  
	//to add the info data from database to array
	  for(int x = 0; x < infoData.length; x++)
	  {
		 	  
		  showInfo[0][1] = String.valueOf(infoData[x][0]);
		  showInfo[1][1] = String.valueOf(infoData[x][1]);
		  showInfo[2][1] = String.valueOf(infoData[x][2]);
		  showInfo[3][1] = String.valueOf(infoData[x][3]);
		  
	  }
	  
	  
	  showInfo[0][0] = "Name";
	  showInfo[1][0] = "Designation";
	  showInfo[2][0] = "Department";
	  showInfo[3][0] = "Region";
	 
	  
	
	   
     // pfper=Math.round(Double.parseDouble(String.valueOf(pfData[0][0])));
	   
	 
	 /*  String pfQue=" SELECT TYPE_PF FROM HRMS_EMP_TYPE" 
			+" INNER  JOIN HRMS_EMP_OFFC ON(HRMS_EMP_TYPE.TYPE_ID = HRMS_EMP_OFFC.EMP_TYPE)"
			+" WHERE  HRMS_EMP_OFFC.EMP_ID ="+promotion.getEmpCode();
      Object[][] pfObj = getSqlModel().getSingleResult(pfQue);*/

      String pfQue=" SELECT SAL_EPF_FLAG FROM HRMS_EMP_OFFC "
			+" INNER JOIN HRMS_SALARY_MISC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID) "	 
			+" WHERE HRMS_EMP_OFFC.EMP_ID = "+promotion.getEmpCode();
	Object[][] pfObj = getSqlModel().getSingleResult(pfQue);
		
	
     if(pfObj.length>0)
	 {
      if(String.valueOf(pfObj[0][0]).equals("Y"))
		 {
    	  if(salMthData.length > 0)
    		{
		  basic=Math.round(Double.parseDouble(String.valueOf(showMth[0][1])));
		  pfpermon=basic*(0.12);
	      pfperanum=(pfpermon*12);
    		}
		 
	      
		  show[0][0] = " PF";
		  show[0][1] = Math.round(pfpermon);
		  show[0][2] = Math.round(pfperanum);
		  show[0][3] = "Monthly";
		  
		   annsumMthPf = annsumMth + pfpermon;
		    annsumMthPfGrs = annsumMthGrs + pfperanum;
		 }
		 else{
			   annsumMthPf = annsumMth ;
			    annsumMthPfGrs = annsumMthGrs ;
		 }
	}  
	
	  
	   

		annsum =annsumMthPf + annsumQua +annsumHalf;
		  
		annSumGrs= annsumMthPfGrs + annsumQuaGrs + annsumHalfGrs + annsumAnnGrs;
		
	 //reduce the pf from total
	 // AnnAmount=AnnAmount+pfpermon;
	//  annsum=annsum+pfperanum;

	 
	  
	  /*show[salData.length+1][0] ="CTC";
	  show[salData.length+1][1] = Math.round(AnnAmount);
	  show[salData.length+1][2] = Math.round(annsum);
	  show[salData.length+1][3] ="";*/
	
		 String s3="";
		 String s4="";
		  
		String queryStrength = " SELECT NVL(STRENGTH,' '),NVL(WEAKNESS,' ') FROM HRMS_PROMOTION "
							+" WHERE HRMS_PROMOTION.PROM_CODE="+promotion.getPromCode();
		
		Object [][] resultObj = getSqlModel().getSingleResult(queryStrength);
		
		if(resultObj != null && resultObj.length > 0){
			s3= String.valueOf(resultObj[0][0]);
			s4= String.valueOf(resultObj[0][1]);
		}
			  
	  String [] temp=null;
		double rate=0.0;
	
	  
	  temp=s3.split(",");
	  	
		Date toDate=new Date();
		SimpleDateFormat today=new SimpleDateFormat("dd-MM-yyyy");
		String day=today.format(toDate);
	
		
		if(String.valueOf(data[0][20]).equals(" ") ){
			data[0][20]="0";
			rate=Double.parseDouble(String.valueOf(data[0][20]));
          }
		rate=Double.parseDouble(String.valueOf(data[0][20]));;
	    String rateStr="";
		if (rate >= 96 && rate<=100) {
			  rateStr="EE(Exceeding Expectations)/ "+String.valueOf(rate); 
			
		} else if(rate >= 86 && rate<=95){
			rateStr="ES(Exceeding in Some)/ "+String.valueOf(rate);
			
		}
		else if(rate >= 76 && rate<=85){
			rateStr="ME(Meeting Expectations)/ "+String.valueOf(rate);
			
		}
		else if(rate >= 66 && rate<=75){
			rateStr="MM(Meeting Most)/ "+String.valueOf(rate);
			
		}
		else{
			rateStr="DNM(Does Not Meet)/ "+String.valueOf(rate);
		}
			
		rg.addTextPromo("\n\n\n\n\n\n\n\n\n\n\n",Element.ALIGN_JUSTIFIED,0,0);
		rg.tableBodyNoBorderPromoSmall(new Object[][]{{"Glodyne/HumanCapital/"+String.valueOf(data[0][2]),"Date :"+dateToPrint+" "}}, new int[]{30,10},new int[]{0,2});
		//String message="Glodyne/HumanCapital/"+String.valueOf(data[0][2])+"															Date :"+String.valueOf(data[0][19]);
		
		String newText = "Level "+String.valueOf(data[0][10]);
		
		String message2="\nTo, ";
		String message3=String.valueOf(data[0][1]) ;
		String message4=String.valueOf(data[0][12])+"\n" ;
		
		String message5="\nSubject: Performance Appraisal for the year "+String.valueOf(data[0][18]) ;
		String message6="\nDear\t"+String.valueOf(data[0][22])+",";
		String message7="\nIt gives us immense pleasure to share that appraisal for the year "+String.valueOf(data[0][18])+
						" has been completed successfully and  your performance has been rated as "+rateStr+
						" %. During the appraisal process the following observations "+
						" have been made by your appraiser. \n\n";
		String message8="A. Your strength are as follows\t";
		String message9="\nB. Your areas of improvement are as follows\t";
		String message10="\nWe are sure that you will continue to excel in your performance to bring desired improvement wherever needed.";
		String message11="\nWe are happy to have you in our  “Glodyne Family”.";
		String message12="\nBased on your Performance rating, you have been promoted as “"+String.valueOf(data[0][8])+"” in  ";//+newText+"  and your revised compensation structure is as per the attached annexure and will be effective from  "+String.valueOf(data[0][15])+".\n\n";
		String message14="  and your revised compensation structure is as per the attached annexure and will be effective from  "+String.valueOf(data[0][15])+".\n\n";
		
		String message13="\nBased on your Performance rating your revised compensation structure is as per the attached annexure and you will be at ";//+newText+", effective from "+String.valueOf(data[0][15])+".\n\n";;
		String message15=", effective from "+String.valueOf(data[0][15])+".\n\n";
		
		String colNames[]={"Monthly Emoluments","P.M. (Rs.)","P.A. (Rs.) ","Payment Mode"};
		//String message13="* The performance Pay will be released in the month of June/ July of every year based on the Performance Appraisal Rating.";
		
		String[] messgeOne={message12,newText,message14};
		String[] messgeTwo={message13,newText,message15};
		int style[]={0,2,0};
		 	
		if(data !=null && data.length!=0)
		{
			
			//rg.addTextPromo(message, 0,Element.ALIGN_JUSTIFIED, 0);
			rg.addTextBold(message2, 0, Element.ALIGN_JUSTIFIED, 0);
			rg.addTextBold(message3, 0, Element.ALIGN_JUSTIFIED, 0);
			rg.addTextPromo(message4, 0, Element.ALIGN_JUSTIFIED, 0);
			rg.addTextBold(message5, 0, Element.ALIGN_JUSTIFIED, 0);
			rg.addTextPromo(message6, 0, Element.ALIGN_JUSTIFIED, 0);
			rg.addTextPromo(message7, 0, Element.ALIGN_JUSTIFIED, 0);
			rg.addTextBold(message8, 0, Element.ALIGN_JUSTIFIED, 0);
		
		   /*rg.addFormatedText(message, 0, 0, 0,0);
			rg.addFormatedText(message2, 0, 0,0, 0);
			rg.addFormatedText(message3, 0, 0,0, 0);
			rg.addFormatedText(message4, 0, 0,0, 0);
			rg.addFormatedText(message5, 0, 0,0, 0);
			rg.addFormatedText(message6, 0, 0,0, 0);
			rg.addFormatedText(message7, 0, 0,0, 0);
			rg.addFormatedText(message8, 0, 0,0, 0);*/
			
			//to display strengths
		
			for (int x = 0 ; x < temp.length ; x++) {
		    rg.addTextPromo(""+(x+1)+". "+temp[x], 0, 0, 0);      
			
			}
			
			rg.addTextBold(message9, 0, Element.ALIGN_JUSTIFIED, 0);
			
			//to display weaknesses

			temp=s4.split(",");
			for (int x = 0 ; x < temp.length ; x++) {
		    rg.addTextPromo(""+(x+1)+". "+temp[x], 0, 0, 0);      
		
			}
			rg.addTextPromo(message10, 0, Element.ALIGN_JUSTIFIED, 0);

			rg.addTextPromo(message11, 0, Element.ALIGN_JUSTIFIED, 0);
			logger.info("flag=========="+data[0][21]);
			
			if(String.valueOf(data[0][21]).equals("P"))
			{
				rg.addFormatedTextPromo(messgeOne, style, 0, Element.ALIGN_JUSTIFIED, 0);
				//rg.addTextPromo(message12, 0, Element.ALIGN_JUSTIFIED, 0);
				//rg.addTextBold(newText, 0, Element.ALIGN_JUSTIFIED, 0);
				//rg.addTextPromo(message14, 0, Element.ALIGN_JUSTIFIED, 0);
			}
			else{
				rg.addFormatedTextPromo(messgeTwo, style, 0, Element.ALIGN_JUSTIFIED, 0);
				//rg.addTextPromo(message13, 0, Element.ALIGN_JUSTIFIED, 0);
				//rg.addTextBold(newText, 0, Element.ALIGN_JUSTIFIED, 0);
			//	rg.addTextPromo(message15, 0, Element.ALIGN_JUSTIFIED, 0);
			}
			
			
			
		//Annexure
								
			String dat[]=new String[1];
			dat[0]="ANNEXURE";
			int w[]={100};
			int a[]={1};
			rg.pageBreak();
			rg.addTextPromo("\n\n\n\n\n\n\n\n",Element.ALIGN_JUSTIFIED,0,0);
			rg.tableRowWithBG(dat, w, a , 98);
			
			rg.tableBody1(showInfo, cellwidthInfo, alignmnetInfo);
			 Object[][]obj=new Object[1][1];
			 obj[0][0]="\n";
			 int []cwidth={100};
				
				int []align={0};	
			rg.tableBody1(obj, cwidth, align);
		//	rg.addTotalRow("", 0, 0, "");
			
		
			
		}
		
	
		
		String colNamesBGGrs[]={"Gross Pay",String.valueOf(Math.round(annsumMthPf)),String.valueOf(Math.round(annsumMthPfGrs)),""};
		String colNamesBGQua[]={"Gross Pay(Quarterly)",String.valueOf(Math.round(annsumQua)),String.valueOf(Math.round(annsumQuaGrs)),""};
		String colNamesBGHalf[]={"Gross Pay(Half Yearly)",String.valueOf(Math.round(annsumHalf)),String.valueOf(Math.round(annsumHalfGrs)),""};
		String colNamesBGCTC[]={"CTC","",String.valueOf(Math.round(annSumGrs)),""};
		String colNamesBG[]={message12};
		if(salMthData.length>0)
		{
			rg.tableRowWithBG(colNames, cellwidth, alignmnet , 98);
			rg.tableBody1(showMth,cellwidth, alignmnet);
		//	rg.tableBody(colNames, showQua, cellwidth, alignmnet,80);
			rg.tableBody1(show, cellwidth, alignmnet);
			rg.tableRowWithBG(colNamesBGGrs, cellwidth, alignmnet,98);
		}
		if(salQuaData.length>0)
			{
				rg.tableBody1(showQua, cellwidth, alignmnet);
				rg.tableRowWithBG(colNamesBGQua, cellwidth, alignmnet,98);
			}
		if(salHalfData.length>0)
			{
				rg.tableBody1(showHalf, cellwidth, alignmnet);
				rg.tableRowWithBG(colNamesBGHalf, cellwidth, alignmnet,98);
			}
		if(salAnnData.length>0)
			{
			rg.tableBody1(showAnn, cellwidth, alignmnet);
			
			}
			rg.tableRowWithBG(colNamesBGCTC, cellwidth, alignmnet,98);
		
			if(PerformData != null && PerformData.length > 0)
				rg.tableBody1(PerformData, w1, a1);
		
		/*String colNamesBG[]={""};
		colNamesBG[0]="CTC";
		colNamesBG[0]=String.valueOf(Math.round(AnnAmount));
		colNamesBG[0]=String.valueOf(Math.round(annsum));;
		colNamesBG[0]="";
		*/
			if(salMthData.length ==0 && salQuaData.length==0 && salHalfData.length==0 && salAnnData.length==0)
			{
				rg.addTextBold("Salary is not present", 0, 0, 0);
			}
			
			
			Object wishObj [][] = new Object[3][1];
			wishObj[0][0] = String.valueOf("\n\nVikas V. Pathak");
			wishObj[1][0] = String.valueOf("Sr.Vice President & Head");
			wishObj[2][0] = String.valueOf("Human Capital & Admin (Global Operation)");
			
			rg.tableBodyNoBorderPromoSmall(new Object[][]{{String.valueOf("\n\nBest Wishes,")}}, new int[]{20},new int[]{0});
			rg.tableBodyNoBorderPromo(wishObj, new int[]{20},new int[]{0});
					
		
		 }
		 	
	   } catch (Exception e) {
		e.printStackTrace();
		return null;
		}
	   return rg;
}
	
	/*
	 * This method  will set the totalRecords and record per page value...! this varible
	 *  to be used for Generating DownLoad appraisal report.
	 */
	public void generateUrlList(HttpServletRequest request,
				HttpServletResponse response, PromotionMaster bean) {
			try{
				 //Object [][] resultObj = getSqlModel().getSingleResult(getQuery(1, bean));	
				String sqlQuery="SELECT * FROM HRMS_PROMOTION WHERE 1=1 AND PROM_TEMPLATE IS NOT NULL "+getFilter(bean);
				Object [][] resultObj = getSqlModel().getSingleResult(sqlQuery);
					if (resultObj != null && resultObj.length >0) {
						request.setAttribute("totalRecords",resultObj.length);
						request.setAttribute("recPerPage",Integer.parseInt(bean.getRecordsPerPage()));
						bean.setRecordFlag("true");
					}else{
						request.setAttribute("totalRecords",0);
					}
				}
				catch (Exception e) {
					logger.error("Error in generateUrlList == "+e);
			}
		}
	public Object[][] getTemplateCode(String rangeFrom ,String rangeTo,PromotionMaster bean)
	{
		Object[][] data=null;
		String sqlQuery="SELECT PROM_CODE ,PROM_TEMPLATE  FROM HRMS_PROMOTION "
			+" WHERE PROM_TEMPLATE IS NOT NULL "+getFilter(bean);
		data = getSqlModel().getSingleResult(sqlQuery);
		return data;
	}
	
	public String getFilter(PromotionMaster bean)
	{
		String query="";
		if(!bean.getEmpCode().equals(""))
		{
			query+=" and EMP_CODE IN ("+bean.getEmpCode()+")";
		}
		if(!bean.getEffDate().equals(""))
		{
			query+=" and EFFECT_DATE = to_date('"+bean.getEffDate()+"','dd-mm-yyyy')";
		}
		if(!bean.getDivCode().equals(""))
			query+=" and DIV_CODE IN ("+bean.getDivCode()+")";
		if(!bean.getBranchCode().equals(""))
			query+=" and BRANCH_CODE IN ("+bean.getBranchCode()+")";
		if(!bean.getDeptCode().equals(""))
			query+=" and DEPT_CODE IN ("+bean.getDeptCode()+")";
		if(!bean.getDesgCode().equals(""))
			query+=" and DESG_CODE IN ("+bean.getDesgCode()+")";
		
		
		return query;
	}


	public void getEmployeeDetails(String userEmpId, PromotionMaster pramotion) {
		
		try {
			String sqlQuery = "SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME,' ') AS NAME,HRMS_EMP_OFFC.EMP_ID,NVL(ADD_EMAIL,'') "
					+ "FROM HRMS_EMP_OFFC "
					+ "LEFT JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND ADD_TYPE='P') "
					+ "WHERE HRMS_EMP_OFFC.EMP_ID= " + userEmpId;
			Object[][] values = getSqlModel().getSingleResult(sqlQuery);
			if(values!=null && values.length>0)
			{
				pramotion.setEmpToken(checkNull(String.valueOf(values[0][0])));
				pramotion.setEmpName(checkNull(String.valueOf(values[0][1])));
				pramotion.setEmpCode(checkNull(String.valueOf(values[0][2])));
				pramotion.setEmpMailId(checkNull(String.valueOf(values[0][3])));
			}
		} catch (Exception e) {
			logger.info("when onloading ..!!");
		}
		
	}
	
public String checkNull(String result) {
		
		if (result == null || result.equals("null") || result.equals(" ")) {
			return "";
		} else {
			return result;
		}
	}

}	
	
	