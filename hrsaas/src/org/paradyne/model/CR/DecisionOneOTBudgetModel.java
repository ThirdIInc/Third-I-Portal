package org.paradyne.model.CR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.CR.DataReconcile;
import org.paradyne.bean.CR.DecisionOneOTBudget;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;

import com.businessobjects.reports.crprompting.CRPromptValue.Str;

/**
 * @Date July 19, 2013
 */
public class DecisionOneOTBudgetModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	DataReconcile tm = null;

	/**
	 * @purpose: to check null value
	 * 
	 * @param String
	 *            result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}
	
	public void getbudgetDetailforMonthYear(DecisionOneOTBudget bean){
		try {
			Object totalBudgetObj[][]=getSqlModel("POOL_D1").getSingleResult(getQuery(1),new Object[] {String.valueOf(bean.getYearMonth())});
			if(totalBudgetObj.length>0){
			for (int i = 0; i < totalBudgetObj.length; i++) {
			bean.setTotalBudget(String.valueOf(totalBudgetObj[i][0]));
			bean.setNumberOfWorkingDays(String.valueOf(totalBudgetObj[i][1]));
			}}
			else{
				bean.setTotalBudget("");
				bean.setNumberOfWorkingDays("");
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
		
		
	}
	public void setTotalbudgetForMonthYear(DecisionOneOTBudget bean,HttpServletRequest request){
		try{
			Object totalBudgetObj[][]=getSqlModel("POOL_D1").getSingleResult(getQuery(1),new Object[] {String.valueOf(bean.getYearMonth())});
			String updateQuery="";
			if(totalBudgetObj.length>0){
				 updateQuery="update cr_otbudget	set totalbudget=" +
				 		bean.getTotalBudget() +
				 		", no_of_days=" +
				 		bean.getNumberOfWorkingDays() +
				 		"where monthyear=" +
				 		bean.getYearMonth();
			}
			else{
				updateQuery="insert into cr_otbudget(monthyear,totalbudget,no_of_days) values(" +
							bean.getYearMonth()+
							" , "+
							bean.getTotalBudget() +
							"," +
							bean.getNumberOfWorkingDays() +
							") ";
			}
			boolean updataData=getSqlModel("POOL_D1").singleExecute(updateQuery);
			if(updataData){
				bean.setActionMessage("Save Successfully");
			}
			else{
				bean.setActionMessage("Not Saved");
			}
			OTBudgetEmpList(bean,request);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public void checkData(DecisionOneOTBudget bean){
		String priDataQuery="select monthyear,totalbudget from cr_otbudget where monthyear= "+bean.getYearMonth();
		Object[][] priDataObj=getSqlModel("POOL_D1").getSingleResult(priDataQuery);
		if(priDataObj!=null && priDataObj.length>0){
			bean.setPreviousData("YY");
			
		}
		else{
			bean.setPreviousData("NN");
		String availablequery="select monthyear,totalbudget from cr_otbudget where monthyear=(select max(monthyear) from cr_otbudget)"	;
			
		Object[][] priavaibleObj=getSqlModel("POOL_D1").getSingleResult(availablequery);
			if(priavaibleObj.length>0){
				
				bean.setPreviousMonthYear(String.valueOf(priavaibleObj[0][0]));
			}
			
		}
	}
	public void setlatestData(DecisionOneOTBudget bean,HttpServletRequest request){
		
		String currMonthYear=bean.getOtYear()+bean.getOtMonth();
		String bugetquery="insert into cr_otbudget(monthyear,totalbudget,no_of_days) " +
				" select "+currMonthYear +",co.totalbudget ,co.no_of_days from cr_otmonthbudget com,cr_otbudget co " +
				" where com.monthyear = (select max(monthyear) from cr_otmonthbudget) ";
		boolean isInsertedData=getSqlModel("POOL_D1").singleExecute(bugetquery);
		//for Setting bean value 
		String query="select totalbudget,no_of_days from cr_otbudget where monthyear=  "+currMonthYear;
		Object[][] totObj=getSqlModel("POOL_D1").getSingleResult(query);
		if(totObj!=null){
			bean.setTotalBudget(String.valueOf(totObj[0][0]));
			bean.setNumberOfWorkingDays(String.valueOf(totObj[0][1]));
		}
		
		
		String maxMonthquery="select monthyear,region_id,person_type,reporting_id,person_id,budget_percent," +
				"budget_amount,budget_daily,budget_weekly,auto_id,person_name,reporting_name from cr_otmonthbudget " +
				"where monthyear=(select max(monthyear) from cr_otmonthbudget)";
		Object maxmonthObj[][]=getSqlModel("POOL_D1").getSingleResult(maxMonthquery);
		//for putting maxAutoID to be putted
		String maxdataAutoID="Select max(auto_id) from cr_otmonthbudget";
		Object[][] maxdataAutoIDObj=getSqlModel("POOL_D1").getSingleResult(maxdataAutoID);
		int Mautoid=Integer.parseInt(String.valueOf(maxdataAutoIDObj[0][0]));
		boolean insertNewMonthData=false;
		
		for (int i = 0; i < maxmonthObj.length; i++) {
		String q="insert into cr_otmonthbudget(monthyear,region_id,person_type,reporting_id,person_id,budget_percent,budget_amount,budget_daily,budget_weekly,auto_id,person_name,reporting_name) values " +
				"("+currMonthYear+","
				+"'"+String.valueOf(maxmonthObj[i][1])+"'"+ ","//region_id
				+"'"+String.valueOf(maxmonthObj[i][2])+"'"+ ","; //person_type
				if(String.valueOf(maxmonthObj[i][3]).equals("")){
				q=q+String.valueOf("' '")+ "," ;//reporting_id
				}
				else{
				q=q+"'"+String.valueOf(maxmonthObj[i][3])+"'"+ "," ;	
				}
				q=q+String.valueOf(maxmonthObj[i][4])+	"," //person_id
				+String.valueOf(maxmonthObj[i][5])+	"," //budget_percent
				+String.valueOf(maxmonthObj[i][6])+	"," //budget_amount
				+String.valueOf(maxmonthObj[i][7])+	"," //budget_daily
				+String.valueOf(maxmonthObj[i][8])+	"," //budget_weekly
				+String.valueOf(Mautoid+1)+	"," //auto_id
				+"'"+String.valueOf(maxmonthObj[i][10])+ "' ," +//person name
				"'"+String.valueOf(maxmonthObj[i][11])+ "'" //reporting name
				
					+	")"	;
	 insertNewMonthData=getSqlModel("POOL_D1").singleExecute(q);
		
		}
		if(insertNewMonthData && isInsertedData){
		bean.setActionMessage("Update Data for Month-"+bean.getYearMonth());
		bean.setPreviousData("YY");
		OTBudgetEmpList(bean, request);
		}
		else{
			bean.setActionMessage("not Data Updated ");
		}
		
	
		
		
	}
	
	public void setBudgetAmount(DecisionOneOTBudget bean,HttpServletRequest request){
		try {
			
			//Double actualBudget=(Double)totalBudgetObj[0][0];
			//Double beanBudget=Double.parseDouble(bean.getTotalBudget());
			float budgetAmt=0.0f;
			float budgetDaily=0.0f;
			float budgetWeekly=0.0f;
			String currMonthYear=bean.getOtYear()+bean.getOtYear();
		
				//check extsting data is availabel in db or not if not then inserted
				/*String query="select monthyear from cr_otmonthbudget where monthyear="+currMonthYear;
				Object[][] currMotnhYearObj=getSqlModel("POOL_D1").getSingleResult(query);
				if(currMotnhYearObj!=null ){}*/
				
				
			if(bean.getOtPersonType().equals("S")){
				Object[] supervisorPara=new Object[3];
				   supervisorPara[0]=bean.getReportingID();
				   supervisorPara[1]=bean.getYearMonth();
				   supervisorPara[2]=bean.getRegionID();
				   Object supervisorBudgetObj[][]=getSqlModel("POOL_D1").getSingleResult(getQuery(4),supervisorPara);
				   if(supervisorBudgetObj!=null && supervisorBudgetObj.length>0){
					  String supervisorTotBudget=String.valueOf(supervisorBudgetObj[0][0]); 
					  budgetAmt=((Float.parseFloat(supervisorTotBudget)*Float.parseFloat(bean.getBudgetPer()))/100);
					  budgetDaily=budgetAmt/Float.parseFloat(bean.getNumberOfWorkingDays());
					 budgetWeekly=budgetDaily*5;
					bean.setBudgetAmt(String.valueOf(budgetAmt));
					bean.setBudgetDaily(String.valueOf(budgetDaily));
					bean.setBudgetWeekly(String.valueOf(budgetWeekly));
				   
				   } 
				
			}
			else{
				
				//Object totalBudgetObj[][]=getSqlModel("POOL_D1").getSingleResult(getQuery(1),new Object[] {String.valueOf(bean.getYearMonth())});
				String actualBudget=bean.getTotalBudget();
			//if(!actualBudget.equals(bean.getTotalBudget())){
				//bean.setActionMessage("Total Budget and Number of Week For month save first");
			//}
			//else{
			 budgetAmt=((Float.parseFloat(actualBudget)*Float.parseFloat(bean.getBudgetPer()))/100);
			 budgetDaily=budgetAmt/Float.parseFloat(bean.getNumberOfWorkingDays());
			 budgetWeekly=budgetDaily*5;
			bean.setBudgetAmt(String.valueOf(budgetAmt));
			bean.setBudgetDaily(String.valueOf(budgetDaily));
			bean.setBudgetWeekly(String.valueOf(budgetWeekly));
			
			//}
			}//end else
			
			//For Saving and updateing data
			
			String maxAutoID="Select max(auto_id) from cr_otmonthbudget";
			Object[][] maxAutoIDObj=getSqlModel("POOL_D1").getSingleResult(maxAutoID);
			String maxID="";
			if(String.valueOf(maxAutoIDObj[0][0]).equals("null")){
				maxID="1";
				
			}
			else{
				maxID=String.valueOf(Integer.parseInt(String.valueOf(maxAutoIDObj[0][0]))+1);
				
			}
			
			
			
			boolean isaddbudget=false;
			String eautoid="select auto_id from cr_otmonthbudget where auto_id="+bean.getAutoid();
			Object[][] eautoidobj=getSqlModel("POOL_D1").getSingleResult(eautoid);
			
			if(eautoidobj!=null && eautoidobj.length>0 && bean.getAutoid().equals(String.valueOf(eautoidobj[0][0]))){
				Object[][] updateParameter=new Object[1][12];
				updateParameter[0][0]=bean.getAutoid();
				updateParameter[0][1]=String.valueOf(bean.getYearMonth());
				updateParameter[0][2]=String.valueOf(bean.getRegionID());
				updateParameter[0][3]=String.valueOf(bean.getOtPersonType());
				updateParameter[0][4]=String.valueOf(bean.getReportingID());
				updateParameter[0][5]=String.valueOf(bean.getPersonID());
				updateParameter[0][6]=String.valueOf(bean.getBudgetPer());
				updateParameter[0][7]=String.valueOf(bean.getBudgetAmt());
				updateParameter[0][8]=String.valueOf(bean.getBudgetDaily());
				updateParameter[0][9]=String.valueOf(bean.getBudgetWeekly());
				updateParameter[0][10]=String.valueOf(bean.getPersonName());
				updateParameter[0][11]=String.valueOf(bean.getReportingName());
				updateParameter[0][12]=bean.getAutoid();
				isaddbudget=getSqlModel("POOL_D1").singleExecute(getQuery(3),updateParameter);
				if(isaddbudget){
					bean.setActionMessage("Update Successfully");
					bean.setAutoid("");
				}
				else{
					
					bean.setActionMessage("Not Saved");}
			}
			else{
				Object[][] parameter=new Object[1][12];
				parameter[0][0]=maxID;
				parameter[0][1]=String.valueOf(bean.getYearMonth());
				parameter[0][2]=String.valueOf(bean.getRegionID());
				parameter[0][3]=String.valueOf(bean.getOtPersonType());
				parameter[0][4]=String.valueOf(bean.getReportingID());
				parameter[0][5]=String.valueOf(bean.getPersonID());
				parameter[0][6]=String.valueOf(bean.getBudgetPer());
				parameter[0][7]=String.valueOf(bean.getBudgetAmt());
				parameter[0][8]=String.valueOf(bean.getBudgetDaily());
				parameter[0][9]=String.valueOf(bean.getBudgetWeekly());
				parameter[0][10]=String.valueOf(bean.getPersonName());
				parameter[0][11]=String.valueOf(bean.getReportingName());
				isaddbudget=getSqlModel("POOL_D1").singleExecute(getQuery(2),parameter);
				if(isaddbudget)
					bean.setActionMessage("Save Successfully");
				else
					bean.setActionMessage("Not Saved");
					
			}
			if(isaddbudget){
				
				bean.setRegionID("");
				bean.setRegionName("");
				bean.setPersonID("");
				bean.setOtPersonType("");
				bean.setBudgetAmt("");
				bean.setBudgetDaily("");
				bean.setBudgetPer("");
				bean.setBudgetWeekly("");
				bean.setReportingID("");
				bean.setPersonName("");
				bean.setReportingName("");
				
			}
			else{
				bean.setActionMessage("Not Saved");
			}
			OTBudgetEmpList(bean,request);
			
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void addOTBudget(DecisionOneOTBudget bean,HttpServletRequest request){
		try {
			String maxAutoID="Select max(auto_id) from cr_otmonthbudget";
			Object[][] maxAutoIDObj=getSqlModel("POOL_D1").getSingleResult(maxAutoID);
			String maxID="";
			if(String.valueOf(maxAutoIDObj[0][0]).equals("null")){
				maxID="0";
			}
			else{
				maxID=String.valueOf(Integer.parseInt(String.valueOf(maxAutoIDObj[0][0]))+1);
			}
			
			
			
			boolean isaddbudget=false;
			String eautoid="select auto_id from cr_otmonthbudget where auto_id="+bean.getAutoid();
			Object[][] eautoidobj=getSqlModel("POOL_D1").getSingleResult(eautoid);
			
			if(eautoidobj!=null && bean.getAutoid().equals(String.valueOf(eautoidobj[0][0]))){
				Object[][] updateParameter=new Object[1][11];
				updateParameter[0][0]=bean.getAutoid();
				updateParameter[0][1]=String.valueOf(bean.getYearMonth());
				updateParameter[0][2]=String.valueOf(bean.getRegionID());
				updateParameter[0][3]=String.valueOf(bean.getOtPersonType());
				updateParameter[0][4]=String.valueOf(bean.getReportingID());
				updateParameter[0][5]=String.valueOf(bean.getPersonID());
				updateParameter[0][6]=String.valueOf(bean.getBudgetPer());
				updateParameter[0][7]=String.valueOf(bean.getBudgetAmt());
				updateParameter[0][8]=String.valueOf(bean.getBudgetDaily());
				updateParameter[0][9]=String.valueOf(bean.getBudgetWeekly());
				updateParameter[0][10]=bean.getAutoid();
				isaddbudget=getSqlModel("POOL_D1").singleExecute(getQuery(3),updateParameter);
				if(isaddbudget)
					bean.setActionMessage("Update Successfully");
				else
					bean.setActionMessage("Not Saved");
			}
			else{
				Object[][] parameter=new Object[1][10];
				parameter[0][0]=maxID;
				parameter[0][1]=String.valueOf(bean.getYearMonth());
				parameter[0][2]=String.valueOf(bean.getRegionID());
				parameter[0][3]=String.valueOf(bean.getOtPersonType());
				parameter[0][4]=String.valueOf(bean.getReportingID());
				parameter[0][5]=String.valueOf(bean.getPersonID());
				parameter[0][6]=String.valueOf(bean.getBudgetPer());
				parameter[0][7]=String.valueOf(bean.getBudgetAmt());
				parameter[0][8]=String.valueOf(bean.getBudgetDaily());
				parameter[0][9]=String.valueOf(bean.getBudgetWeekly());
				isaddbudget=getSqlModel("POOL_D1").singleExecute(getQuery(2),parameter);
				if(isaddbudget)
					bean.setActionMessage("Save Successfully");
				else
					bean.setActionMessage("Not Saved");
					
			}
			if(isaddbudget){
				
				bean.setRegionID("");
				bean.setRegionName("");
				bean.setPersonID("");
				bean.setOtPersonType("");
				bean.setBudgetAmt("");
				bean.setBudgetDaily("");
				bean.setBudgetPer("");
				bean.setBudgetWeekly("");
				bean.setReportingID("");
				
			}
			else{
				bean.setActionMessage("Not Saved");
			}
			OTBudgetEmpList(bean,request);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void deleteBudgetRecords(DecisionOneOTBudget bean,String autoid){
		String deleteQuery="Delete from cr_otmonthbudget where auto_id="+autoid;
		
		boolean isdelete=getSqlModel("POOL_D1").singleExecute(deleteQuery);
		if(isdelete){
			bean.setActionMessage("Deleted");
		}
		else{
			bean.setActionMessage("Not Deleted");
		}
	}
	
	public void OTBudgetEmpList(DecisionOneOTBudget bean,HttpServletRequest request){
		ArrayList<DecisionOneOTBudget> otBudgetList=new ArrayList<DecisionOneOTBudget>();
		try {
			String query="select co.monthyear,cor.ot_region_name , " +
					" case co.person_type when 'M' then 'Manager' else 'Supervisor' end ,co.reporting_id+'-'+co.reporting_name, " +
					" co.person_id+' - '+co.person_name,co.budget_percent,co.budget_amount,co.budget_daily,co.budget_weekly,co.auto_id ,cor.ot_region_id  	" +
					" from cr_otmonthbudget co, cr_otregion cor " +
					" where co.monthyear="+String.valueOf(bean.getYearMonth())+
					" and  cor.ot_region_id=co.region_id";
					
			Object[][] otBudgetObj=getSqlModel("POOL_D1").getSingleResult(query);
			
			
			//if(otBudgetObj!=null && otBudgetObj.length>0){
				String[] pageindex=Utility.doPaging(bean.getMyPage(),otBudgetObj.length, 20);
				if(pageindex==null){
					pageindex[0] = "0";
					pageindex[1] = "20";
					pageindex[2] = "1";
					pageindex[3] = "1";
					pageindex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageindex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageindex[3])));
				if (pageindex[4].equals("1"))
					bean.setMyPage("1");
				bean.setListLength(true);
				 for(int i = Integer.parseInt(pageindex[0]); i < Integer.parseInt(pageindex[1]); i++){
					 DecisionOneOTBudget beanlist=new DecisionOneOTBudget();
					 bean.setForMonthYear(String.valueOf(otBudgetObj[i][0]));
					 beanlist.setRegionName(String.valueOf(otBudgetObj[i][1]));
					 beanlist.setOtPersonType(String.valueOf(otBudgetObj[i][2]));
					 beanlist.setReportingID(String.valueOf(otBudgetObj[i][3]));
					 beanlist.setPersonID(String.valueOf(otBudgetObj[i][4]));
					 beanlist.setBudgetPer(String.valueOf(otBudgetObj[i][5]));
					 beanlist.setBudgetAmt(String.valueOf(otBudgetObj[i][6]));
					 beanlist.setBudgetDaily(String.valueOf(otBudgetObj[i][7]));
					 beanlist.setBudgetWeekly(String.valueOf(otBudgetObj[i][8]));
					 beanlist.setAutoid(String.valueOf(otBudgetObj[i][9]));
					 beanlist.setRegionIDItt(String.valueOf(otBudgetObj[i][9]));
					 otBudgetList.add(beanlist);
				 }
			//}
			/*else{
				
				String latestRecordQuery="select co.monthyear,cor.ot_region_name ,case co.person_type when 'M' then 'Manager' else 'Supervisor' end,co.reporting_id, 						 co.person_id,co.budget_percent,co.budget_amount,co.budget_daily,co.budget_weekly,co.auto_id   	" +
						"	 from cr_otmonthbudget co, cr_otregion cor " +
						"						 where             " +
						"               monthyear=(select distinct max (monthyear) from cr_otmonthbudget " +
						"              where monthyear<         " +
						bean.getYearMonth() +
						" )" +
						" and  cor.ot_region_id=co.region_id";
				Object[][] latestRecordObj=getSqlModel("POOL_D1").getSingleResult(latestRecordQuery);
				if(latestRecordObj!=null && latestRecordObj.length>0){
					String[] pageindex=Utility.doPaging(bean.getMyPage(),latestRecordObj.length, 20);
					if(pageindex==null){
						pageindex[0] = "0";
						pageindex[1] = "20";
						pageindex[2] = "1";
						pageindex[3] = "1";
						pageindex[4] = "";
					}
					request.setAttribute("totalPage", Integer.parseInt(String
							.valueOf(pageindex[2])));
					request.setAttribute("pageNo", Integer.parseInt(String
							.valueOf(pageindex[3])));
					if (pageindex[4].equals("1"))
						bean.setMyPage("1");
					bean.setListLength(true);
					for(int i = Integer.parseInt(pageindex[0]); i < Integer.parseInt(pageindex[1]); i++){
						DecisionOneOTBudget lbeanlist=new DecisionOneOTBudget();
						 bean.setForMonthYear(String.valueOf(latestRecordObj[i][0]));
						 lbeanlist.setRegionID(String.valueOf(latestRecordObj[i][1]));
						 lbeanlist.setOtPersonType(String.valueOf(latestRecordObj[i][2]));
						 lbeanlist.setReportingID(String.valueOf(latestRecordObj[i][3]));
						 lbeanlist.setPersonID(String.valueOf(latestRecordObj[i][4]));
						 lbeanlist.setBudgetPer(String.valueOf(latestRecordObj[i][5]));
						 lbeanlist.setBudgetAmt(String.valueOf(latestRecordObj[i][6]));
						 lbeanlist.setBudgetDaily(String.valueOf(latestRecordObj[i][7]));
						 lbeanlist.setBudgetWeekly(String.valueOf(latestRecordObj[i][8]));
						 lbeanlist.setAutoid(String.valueOf(latestRecordObj[i][9]));
						 otBudgetList.add(lbeanlist);
					}
					
					
				}
				
				
				
			}*/
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		bean.setOtBudgetList(otBudgetList);
	}
	
	public void editBudget(DecisionOneOTBudget bean,HttpServletRequest request){
		try {
			String editBudgetquery="select cor.ot_region_name ,com.person_type,com.reporting_id,com.reporting_name,com.person_id," +
					  "	com.budget_percent,com.budget_amount,com.budget_daily,com.budget_weekly,com.region_id,com.person_name," +
					  " com.auto_id" +
					  " from cr_otmonthbudget com , cr_otregion cor " +
					  "	 where auto_id= " +
					  bean.getAutoid()+
					  " and cor.ot_region_id=com.region_id";
			Object[][] editBudgetObj=getSqlModel("POOL_D1").getSingleResult(editBudgetquery);
			bean.setRegionName(String.valueOf(editBudgetObj[0][0]));
			bean.setOtPersonType(String.valueOf(editBudgetObj[0][1]));
			bean.setReportingID(String.valueOf(editBudgetObj[0][2]));
			bean.setRegionName(String.valueOf(editBudgetObj[0][3]));
			bean.setPersonID(String.valueOf(editBudgetObj[0][4]));
			bean.setBudgetPer(String.valueOf(editBudgetObj[0][5]));
			bean.setBudgetAmt(String.valueOf(editBudgetObj[0][6]));
			bean.setBudgetDaily(String.valueOf(editBudgetObj[0][7]));
			bean.setBudgetWeekly(String.valueOf(editBudgetObj[0][8]));
			bean.setRegionID(String.valueOf(editBudgetObj[0][9]));
			bean.setPersonName(String.valueOf(editBudgetObj[0][10]));
			bean.setAutoid(String.valueOf(editBudgetObj[0][1]));
			OTBudgetEmpList(bean, request);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	
}
