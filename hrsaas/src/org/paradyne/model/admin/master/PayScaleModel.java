package org.paradyne.model.admin.master;


import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.admin.master.ESIMaster;
import org.paradyne.bean.admin.master.PayScaleMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.CrystalReport;
/**
 * @author pranali 
 * Date 26-04-07
 */
public class PayScaleModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	

	org.paradyne.bean.admin.master.PayScaleMaster payScaleMaster=null;
	
	public boolean addPayScale(PayScaleMaster bean)
	{
String var= "";
		
		
		if(bean.getPayStartAmt()==null||bean.getPayStartAmt().equals("")||bean.getPayStartAmt().equals("null"))
		{
			var =var+"";
		}else{
			var =bean.getPayStartAmt();
		}
		  
		
		if(bean.getPayIncrAmt1()==null ||bean.getPayIncrAmt1().equals("")||bean.getPayIncrAmt1().equals("null"))
		{
			var =var+"";
		}else{
			var =var+"-"+bean.getPayIncrAmt1();
		}
		
		if(bean.getPayFinalAmt1()==null ||bean.getPayFinalAmt1().equals("")||bean.getPayFinalAmt1().equals("null"))
		{
			var =var+"";
		}else{
			var =var+"-"+bean.getPayFinalAmt1();
		}
		
		if( bean.getPayIncrAmt2()==null ||bean.getPayIncrAmt2().equals("")||bean.getPayIncrAmt2().equals("null"))
		{
			var =var+"";
		}else{
			var =var+"-"+bean.getPayIncrAmt2();
		}
		
		

		if( bean.getPayFinalAmt2()==null ||bean.getPayFinalAmt2().equals("")||bean.getPayFinalAmt2().equals("null"))
		{
			var =var+"";
		}else{
			var =var+"-"+bean.getPayFinalAmt2();
		}
		
		if(bean.getPayIncrAmt3()==null ||bean.getPayIncrAmt3().equals("")||bean.getPayIncrAmt3().equals("null"))
		{
			var =var+"";
		}else{
			var =var+"-"+bean.getPayIncrAmt3();
		}
		
		if(bean.getPayIncrAmt4()==null ||bean.getPayIncrAmt4().equals("")||bean.getPayIncrAmt4().equals("null"))
		{
			var =var+"";
		}else{
			var =var+"-"+bean.getPayIncrAmt4();
		}
		

		if(bean.getPayFinalAmt4()==null ||bean.getPayFinalAmt4().equals("")||bean.getPayFinalAmt4().equals("null"))
		{
			var =var+"";
		}else{
			var =var+"-"+bean.getPayFinalAmt4();
		}
		
		if(bean.getPayIncrAmt5()==null ||bean.getPayIncrAmt5().equals("")||bean.getPayIncrAmt5().equals("null"))
		{
			var =var+"";
		}else{
			var =var+"-"+bean.getPayIncrAmt5();
		}
		
		if(bean.getPayFinalAmt5()==null ||bean.getPayFinalAmt5().equals("")||bean.getPayFinalAmt5().equals("null"))
		{
			var =var+"";
		}else{
			var =var+"-"+bean.getPayFinalAmt5();
		}
		
		  		
		  logger.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+var);
		Object addObj[][] =new Object[1][13];
		addObj[0][0] =var;
		
		
		if(bean.getPayStartAmt()==null||bean.getPayStartAmt().equals("")||bean.getPayStartAmt().equals("null"))
		{
			addObj[0][1]=0;
		}
		else
		{
			addObj[0][1]=bean.getPayStartAmt().trim();
		}
		if(bean.getPayIncrAmt1()==null ||bean.getPayIncrAmt1().equals("")||bean.getPayIncrAmt1().equals("null"))
		{
			addObj[0][2]=0;
		}
		else
		{
		addObj[0][2] =bean.getPayIncrAmt1().trim();
		}
		if(bean.getPayFinalAmt1()==null ||bean.getPayFinalAmt1().equals("")||bean.getPayFinalAmt1().equals("null"))
		{
			addObj[0][3]=0;
		}
		else
		{
		addObj[0][3] =bean.getPayFinalAmt1().trim();
		}
		if( bean.getPayIncrAmt2()==null ||bean.getPayIncrAmt2().equals("")||bean.getPayIncrAmt2().equals("null"))
		{
			addObj[0][4]=0;
		}
		else
		{
		addObj[0][4] =bean.getPayIncrAmt2().trim();
		}
		if( bean.getPayFinalAmt2()==null ||bean.getPayFinalAmt2().equals("")||bean.getPayFinalAmt2().equals("null"))
		{
			addObj[0][5]=0;
		}
		else
		{
		addObj[0][5] =bean.getPayFinalAmt2().trim();
		}
		if(bean.getPayIncrAmt3()==null ||bean.getPayIncrAmt3().equals("")||bean.getPayIncrAmt3().equals("null"))
		{
			addObj[0][6]=0;
		}
		else
		{
		addObj[0][6] =bean.getPayIncrAmt3().trim();
		}
		if(bean.getPayFinalAmt3()==null ||bean.getPayFinalAmt3().equals("")||bean.getPayFinalAmt3().equals("null"))
		{
			addObj[0][7]=0;
		}
		else
		{
		addObj[0][7] =bean.getPayFinalAmt3().trim();
		}
		if(bean.getPayCommission()==null ||bean.getPayCommission().equals("")||bean.getPayCommission().equals("null"))
		{
			addObj[0][8]=0;
		}
		else
		{
		addObj[0][8] =bean.getPayCommission().trim();
		}
		if(bean.getPayIncrAmt4()==null ||bean.getPayIncrAmt4().equals("")||bean.getPayIncrAmt4().equals("null"))
		{
			addObj[0][9]=0;
		}
		else
		{
		addObj[0][9] =bean.getPayIncrAmt4().trim();
		}
		if(bean.getPayFinalAmt4()==null ||bean.getPayFinalAmt4().equals("")||bean.getPayFinalAmt4().equals("null"))
		{
			addObj[0][10]=0;
		}
		else
		{
		addObj[0][10] =bean.getPayFinalAmt4().trim();
		}
		if(bean.getPayIncrAmt5()==null ||bean.getPayIncrAmt5().equals("")||bean.getPayIncrAmt5().equals("null"))
		{
			addObj[0][11]=0;
		}
		else
		{
		addObj[0][11] =bean.getPayIncrAmt5().trim();
		}
		if(bean.getPayFinalAmt5()==null ||bean.getPayFinalAmt5().equals("")||bean.getPayFinalAmt5().equals("null"))
		{
			addObj[0][12]=0;
		}
		else
		{
		addObj[0][12] =bean.getPayFinalAmt5().trim();
		}
		
		 
		
		return getSqlModel().singleExecute(getQuery(1),addObj);	
	}
	
	public boolean modPayScale(PayScaleMaster bean)
	{
		  String var=bean.getPayStartAmt()+"-"+bean.getPayIncrAmt1()+"-"+bean.getPayFinalAmt1()+"-"+bean.getPayIncrAmt2()+"-"+bean.getPayFinalAmt2()+"-"+bean.getPayIncrAmt3()+"-"+bean.getPayIncrAmt4()+"-"+bean.getPayFinalAmt4()+"-"+bean.getPayIncrAmt5()+"-"+bean.getPayFinalAmt5();
		
		Object addObj[][]=new Object[1][14];
		addObj[0][0] =var;
		if(bean.getPayStartAmt()==null||bean.getPayStartAmt().equals("")||bean.getPayStartAmt().equals("null"))
		{
			addObj[0][1]=0;
		}
		else
		{
			addObj[0][1]=bean.getPayStartAmt().trim();
		}
		if(bean.getPayIncrAmt1()==null ||bean.getPayIncrAmt1().equals("")||bean.getPayIncrAmt1().equals("null"))
		{
			addObj[0][2]=0;
		}
		else
		{
		addObj[0][2] =bean.getPayIncrAmt1().trim();
		}
		if(bean.getPayFinalAmt1()==null ||bean.getPayFinalAmt1().equals("")||bean.getPayFinalAmt1().equals("null"))
		{
			addObj[0][3]=0;
		}
		else
		{
		addObj[0][3] =bean.getPayFinalAmt1().trim();
		}
		if( bean.getPayIncrAmt2()==null ||bean.getPayIncrAmt2().equals("")||bean.getPayIncrAmt2().equals("null"))
		{
			addObj[0][4]=0;
		}
		else
		{
		addObj[0][4] =bean.getPayIncrAmt2().trim();
		}
		if( bean.getPayFinalAmt2()==null ||bean.getPayFinalAmt2().equals("")||bean.getPayFinalAmt2().equals("null"))
		{
			addObj[0][5]=0;
		}
		else
		{
		addObj[0][5] =bean.getPayFinalAmt2().trim();
		}
		if(bean.getPayIncrAmt3()==null ||bean.getPayIncrAmt3().equals("")||bean.getPayIncrAmt3().equals("null"))
		{
			addObj[0][6]=0;
		}
		else
		{
		addObj[0][6] =bean.getPayIncrAmt3().trim();
		}
		if(bean.getPayFinalAmt3()==null ||bean.getPayFinalAmt3().equals("")||bean.getPayFinalAmt3().equals("null"))
		{
			addObj[0][7]=0;
		}
		else
		{
		addObj[0][7] =bean.getPayFinalAmt3().trim();
		}
		if(bean.getPayCommission()==null ||bean.getPayCommission().equals("")||bean.getPayCommission().equals("null"))
		{
			addObj[0][8]=0;
		}
		else
		{
		addObj[0][8] =bean.getPayCommission().trim();
		}
		if(bean.getPayIncrAmt4()==null ||bean.getPayIncrAmt4().equals("")||bean.getPayIncrAmt4().equals("null"))
		{
			addObj[0][9]=0;
		}
		else
		{
		addObj[0][9] =bean.getPayIncrAmt4().trim();
		}
		if(bean.getPayFinalAmt4()==null ||bean.getPayFinalAmt4().equals("")||bean.getPayFinalAmt4().equals("null"))
		{
			addObj[0][10]=0;
		}
		else
		{
		addObj[0][10] =bean.getPayFinalAmt4().trim();
		}
		if(bean.getPayIncrAmt5()==null ||bean.getPayIncrAmt5().equals("")||bean.getPayIncrAmt5().equals("null"))
		{
			addObj[0][11]=0;
		}
		else
		{
		addObj[0][11] =bean.getPayIncrAmt5().trim();
		}
		if(bean.getPayFinalAmt5()==null ||bean.getPayFinalAmt5().equals("")||bean.getPayFinalAmt5().equals("null"))
		{
			addObj[0][12]=0;
		}
		else
		{
		addObj[0][12] =bean.getPayFinalAmt5().trim();
		}
		
		addObj[0][13] =bean.getPayID(); 
		
		 String upd="UPDATE HRMS_PAYSCALE SET PAYSC_NAME='"+addObj[0][0]+"',PAYSC_START_AMT="+addObj[0][1]+",PAYSC_INCR_AMT1="+addObj[0][2]+ ",  "+
				 " PAYSC_FINAL_AMT1="+addObj[0][3]+",PAYSC_INCR_AMT2="+addObj[0][4]+",PAYSC_FINAL_AMT2="+addObj[0][5]+",PAYSC_INCR_AMT3="+addObj[0][6]+", "+
						" PAYSC_FINAL_AMT3="+addObj[0][7]+",PAYSC_COMMISION='"+addObj[0][8]+"',PAYSC_INCR_AMT4="+addObj[0][9]+",PAYSC_FINAL_AMT4="+addObj[0][10]+" "+
								" ,PAYSC_INCR_AMT5="+addObj[0][11]+",PAYSC_FINAL_AMT5="+addObj[0][12]+" WHERE PAYSC_ID="+addObj[0][13] ;
		 
		 logger.info(upd);
		return getSqlModel().singleExecute(upd);	
		
	}
	
	public boolean deletePayScale(PayScaleMaster bean)
	{
		Object addObj[][]=new Object[1][1];
		addObj[0][0]=bean.getPayID();
		return getSqlModel().singleExecute(getQuery(3),addObj);	
	}
	
	
	public void getPayScaleReport(PayScaleMaster payScaleMaster)
	{
		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		ArrayList<Object> att= new ArrayList<Object>();
		for(int i=0;i<data.length;i++)
		{
			PayScaleMaster bean=new PayScaleMaster();
			bean.setPayID(String.valueOf(data[i][0]));
			bean.setPayName(String.valueOf(data[i][1]));
			bean.setPayStartAmt(String.valueOf(data[i][2]));
			bean.setPayIncrAmt1(String.valueOf(data[i][3]));
			bean.setPayFinalAmt1(String.valueOf(data[i][4]));
			bean.setPayIncrAmt2(String.valueOf(data[i][5])); 
			bean.setPayFinalAmt2(String.valueOf(data[i][6]));
			bean.setPayIncrAmt3(String.valueOf(data[i][7]));
			bean.setPayFinalAmt3(String.valueOf(data[i][8]));
			bean.setPayCommission(String.valueOf(data[i][9]));
			bean.setPayIncrAmt4(String.valueOf(data[i][10]));
			bean.setPayFinalAmt4(String.valueOf(data[i][11]));
			bean.setPayIncrAmt5(String.valueOf(data[i][12]));
			bean.setPayFinalAmt5(String.valueOf(data[i][13]));
			att.add(bean);
		}
		payScaleMaster.setAtt(att);
	}
	

	public void getPayScaleRecord(PayScaleMaster bean) 
	{
		Object addObj[] =new Object[1];
		
		addObj[0] =bean.getPayID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);
		
		for(int i=0; i<data.length; i++) {
			
			bean.setPayID(String.valueOf(data[i][0]));
			bean.setPayName(String.valueOf(data[i][1]));
			bean.setPayStartAmt(String.valueOf(data[i][2]));
			bean.setPayIncrAmt1(String.valueOf(data[i][3]));
			bean.setPayFinalAmt1(String.valueOf(data[i][4]));
			bean.setPayIncrAmt2(String.valueOf(data[i][5]));
			bean.setPayFinalAmt2(String.valueOf(data[i][6]));
			bean.setPayIncrAmt3(String.valueOf(data[i][7]));
			bean.setPayFinalAmt3(String.valueOf(data[i][8]));
			bean.setPayCommission(String.valueOf(data[i][9]));
			bean.setPayIncrAmt4(String.valueOf(data[i][10]));
			bean.setPayFinalAmt4(String.valueOf(data[i][11]));
			bean.setPayIncrAmt5(String.valueOf(data[i][12]));
			bean.setPayFinalAmt5(String.valueOf(data[i][13]));
		}
	}
	public void getReport(PayScaleMaster payScaleMaster,HttpServletRequest request,HttpServletResponse 

			response,ServletContext context,HttpSession session){
				CrystalReport cr=new CrystalReport(); 
				String path="org\\paradyne\\rpt\\admin\\master\\payScale.rpt";
				cr.createReport(request, response, context,session, path, "");
			}	
	
}
