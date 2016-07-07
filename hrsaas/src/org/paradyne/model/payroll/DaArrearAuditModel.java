package org.paradyne.model.payroll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.paradyne.bean.payroll.DaArrearAudit;
 import org.paradyne.lib.ModelBase;
import javax.servlet.http.HttpServletRequest;


	public class DaArrearAuditModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
		
		public String  view (DaArrearAudit daArrearAudit,HttpServletRequest request)
		{
			
			String availEffDate="false";
			try
			{
			String dadate=daArrearAudit.getDaDate();
			
			logger.info("\n\n\n\n========>DATE: "+daArrearAudit.getDaDate());
			logger.info("\n\n\n\n========>EMP CODE: "+daArrearAudit.getTypeCode());
			logger.info("\n\n\n\n========>EMP: "+daArrearAudit.getTypeName());
			logger.info("\n\n\n\n========>PAYBILL NO: "+daArrearAudit.getPayBillNo());
			
			Date   date = new Date();
			SimpleDateFormat	formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			String month=String.valueOf((Integer.parseInt(sysDate.substring(2, 4))-1));
			   
			daArrearAudit.setSysDate(sysDate);
			String daSelect="SELECT DA_CODE, TO_CHAR(DA_EFFECTIVE_DATE,'DD-MM-YYYY') , DA_RATE FROM HRMS_DA_PARAMETER WHERE DA_EFFECTIVE_DATE=TO_DATE('"+dadate+"','DD-MM-YYYY')  ";
			Object da[][]= getSqlModel().getSingleResult(daSelect);
			
			daArrearAudit.setDaCode(String.valueOf(da[0][0]));
			daArrearAudit.setEffectiveDate(String.valueOf(da[0][1]));
			daArrearAudit.setDaRate(String.valueOf(da[0][2]));
			availEffDate="true";
			try
			{
				String type=daArrearAudit.getTypeCode();
				String paybill=daArrearAudit.getPayBillNo();
				logger.info("TYPE-->"+type);
				logger.info("PAYBILL-->"+paybill);
			String daArrearSelect="SELECT HRMS_EMP_OFFC.EMP_ID , HRMS_EMP_OFFC.EMP_FNAME ||' '|| HRMS_EMP_OFFC.EMP_MNAME ||' '|| HRMS_EMP_OFFC.EMP_LNAME AS EMP_NAME ,"
								 +" DA_PAID ,DA_DUE ,to_char(DA_EFFECT_DATE,'dd-mm-yyyy') ,DA_PROCESS_DATE,DA_AUDIT FROM HRMS_DA_ARREAR join HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID=HRMS_DA_ARREAR.EMP_ID"
								 //+" join HRMS_SALARY_MISC ON HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID"
								 +" join HRMS_PAYBILL on HRMS_PAYBILL.PAYBILL_ID=HRMS_EMP_OFFC.EMP_PAYBILL";
			String where=" WHERE HRMS_DA_ARREAR.DA_EFFECT_DATE=to_date('"+daArrearAudit.getEffectiveDate()+"','dd/mm/yyyy') ";
			if((paybill!=null && paybill.length()>0) && (type!=null && type.length()>0)){
				where=where+" AND HRMS_EMP_OFFC.EMP_TYPE="+type+" AND HRMS_PAYBILL.PAYBILL_ID="+paybill;
			}else if(type!=null && type.length()>0){
				where=where+" AND HRMS_EMP_OFFC.EMP_TYPE="+type;
			}else if(paybill!=null && paybill.length()>0){
				where=where+" AND HRMS_PAYBILL.PAYBILL_ID="+paybill;
			}/*else if(!(paybill!=null && paybill.length()>0) && !(type!=null && type.length()>0)){
				where="";
			}*/			
			daArrearSelect=daArrearSelect+where;
			daArrearSelect+=" ORDER BY HRMS_EMP_OFFC.EMP_ID";
			
			Object daArrearData[][]= getSqlModel().getSingleResult(daArrearSelect);
			ArrayList daList=new ArrayList();	
			HashMap hmArr=new HashMap();
			for(int i=0;i<daArrearData.length;i++)
			{
				DaArrearAudit bean=new DaArrearAudit();
				bean.setEmpId(String.valueOf(daArrearData[i][0]));
				bean.setEmpName(String.valueOf(daArrearData[i][1]));
				bean.setPaidDa(String.valueOf(daArrearData[i][2]));
				bean.setDueDa(String.valueOf(daArrearData[i][3]));
				bean.setEffectiveDate(String.valueOf(daArrearData[i][4]));
				bean.setSysDate(sysDate);
				bean.setChk(String.valueOf(daArrearData[i][6]).equals("1")?"true":"false");
				daList.add(bean);
				logger.info("\n"+String.valueOf(daArrearData[i][6]));
				logger.info(String.valueOf(daArrearData[i][6]).equals("1")?"true":"false");
				hmArr.put(""+i,String.valueOf(daArrearData[i][6]).equals("1")?"true":"false");
			}
			daArrearAudit.setDaList(daList);
			request.setAttribute("AUDIT",hmArr);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			}
			catch(Exception e)
			{
				availEffDate="false";
				e.printStackTrace();
			}
			return availEffDate;
		}

		

		public String  save (String [] emp_id, String []  paidDa,String []  dueDa,String[] audit,DaArrearAudit daArrearAudit)
		{
			String availEffDate="true";
			Object selectData[][]=null;
			boolean update=false;
			
			String dadate=daArrearAudit.getDaDate();	
			Date   date = new Date();
			SimpleDateFormat	formater = new SimpleDateFormat("dd-MM-yyyy");
			   String sysDate = formater.format(date);
			   String month=String.valueOf((Integer.parseInt(sysDate.substring(2, 4))-1));
			   
			   daArrearAudit.setSysDate(sysDate);
			   
			   
			   
			String sqlInsert="INSERT INTO  HRMS_DA_ARREAR (EMP_ID ,DA_PAID, DA_DUE, DA_EFFECT_DATE ,DA_PROCESS_DATE,DA_AUDIT ) VALUES(?,?,?,to_date(?,'dd-mm-yyyy'),to_date(?,'dd-mm-yyyy'),?)";
			String sqlUpdate="UPDATE  HRMS_DA_ARREAR SET DA_PAID=?, DA_DUE=?, DA_PROCESS_DATE=TO_DATE(?,'DD-MM-YYYY'),DA_AUDIT=? WHERE  EMP_ID=? AND DA_EFFECT_DATE=TO_DATE(?,'DD-MM-YYYY')";
			Object [][] insertData = new Object[emp_id.length][6];
			for(int i=0;i<emp_id.length;i++)
			{
				try
				{
				String sqlSelect="SELECT EMP_ID FROM HRMS_DA_ARREAR WHERE EMP_ID="+emp_id[i]+" AND DA_EFFECT_DATE=TO_DATE('"+dadate+"','DD-MM-YYYY')";
				selectData= getSqlModel().getSingleResult(sqlSelect);
				if(selectData.length>0)
				update=true;
				
			    }
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				if(update)
				{
					
					insertData[i][0]=paidDa[i];
					insertData[i][1]=dueDa[i];
					insertData[i][2]=daArrearAudit.getSysDate();
					insertData[i][3]=audit[i].equals("true")?"1":"0";
					insertData[i][4]=emp_id[i];
					insertData[i][5]=daArrearAudit.getDaDate();					
				}
				else
				{
					insertData[i][0]=emp_id[i];
					insertData[i][1]=paidDa[i];
					insertData[i][2]=dueDa[i];
					insertData[i][3]=daArrearAudit.getDaDate();
					insertData[i][4]=daArrearAudit.getSysDate();
					insertData[i][5]=audit[i].equals("true")?"1":"0";
					
				}
				
			}
			if(update)
				getSqlModel().singleExecute(sqlUpdate, insertData);
			
			else
				getSqlModel().singleExecute(sqlInsert, insertData);
			 
			return availEffDate;
		}


	}
