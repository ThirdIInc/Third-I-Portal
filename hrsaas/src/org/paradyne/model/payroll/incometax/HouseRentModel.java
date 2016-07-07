package org.paradyne.model.payroll.incometax;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import org.paradyne.bean.payroll.incometax.EmpInvestmentMaster;
import org.paradyne.bean.payroll.incometax.HouseRent;

import com.ibm.icu.util.Calendar;

import javax.servlet.http.HttpServletRequest;
/*
 * author:Pradeep Kumar Sahoo
 * Date:27.03.2008
 */
import javax.servlet.http.HttpServletResponse;

public class HouseRentModel extends ModelBase {

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	/**
	 * following function is used to select the rent code 
	 * @param empInvestment
	 * @return
	 */
	public boolean getRentCode(EmpInvestmentMaster empInvestment){
				Object[] para=new Object[3];
				para[0]=empInvestment.getFromYear();
				para[1]=empInvestment.getToYear();
				para[2]=empInvestment.getEmpID();
				Object[][] param1 = getSqlModel().getSingleResult(getQuery(4),para);
				if(param1==null || param1.length < 1){
					empInvestment.setRentCode("");
					return false;
				}else {
				empInvestment.setRentCode(String.valueOf(param1[0][0]));
				return true;
				}
	}
	
	/**
	 * If the query returns the employee id from hrms_houserent_hdr then the  
	 * else part is executed to get the rent amount from HRMS_HOUSERENT_DTL for the selected employee.
	 * Otherwise the rent amount is set as zero
	 * @param empInvestment
	 * @return
	 */
	public Object[][] getRentAmt(EmpInvestmentMaster empInvestment,HttpServletRequest request) throws Exception{
				String query=" SELECT RENT_EMPID FROM HRMS_HOUSERENT_HDR WHERE RENT_EMPID ="+empInvestment.getEmpID()+" AND RENT_FROMYEAR="+empInvestment.getFromYear()+" and RENT_TOYEAR ="+empInvestment.getToYear();
				Object[][] data = getSqlModel().getSingleResult(query);
				java.util.HashMap hm=new java.util.HashMap();	
					
				boolean result=false;		
				String dobQuery=" SELECT TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') FROM HRMS_EMP_OFFC WHERE " 
								+" EMP_ID="+empInvestment.getEmpID();
				Object[][] dob=getSqlModel().getSingleResult(dobQuery);
				
				String query1="SELECT TO_CHAR(EMP_REGULAR_DATE,'MM-YYYY') FROM HRMS_EMP_OFFC WHERE "
							  +" (TO_CHAR(EMP_REGULAR_DATE,'yyyy-mm')>='"+empInvestment.getFromYear()+"-04' AND "
							  +" TO_CHAR(EMP_REGULAR_DATE,'yyyy-mm')<='"+empInvestment.getToYear()+"-03') AND EMP_ID="+empInvestment.getEmpID();

				Object[][] dateOfJoining=getSqlModel().getSingleResult(query1);
				
				if(dateOfJoining!=null && dateOfJoining.length!=0){
					result=true;	
				}
				int joinMonth=0; int joinYear=0;
			
				if(dob!=null && dob.length!=0){
					if(!(String.valueOf(dob[0][0]).equals("") || String.valueOf(dob[0][0]).equals("null"))){
						joinMonth=Integer.parseInt(String.valueOf(dob[0][0]).substring(3,5));
						joinYear=Integer.parseInt(String.valueOf(dob[0][0]).substring(6,10));
					}
				}
				if(data==null || data.length < 1){
					logger.info("if data is null");
					float sum=0;
					int index=0;
					ArrayList<Object> hrList=new ArrayList<Object>();
					int startMonth=4,endMonth=12;
					int fromYear=Integer.parseInt(empInvestment.getFromYear());
					boolean enable=false;
					for(int i=startMonth;i<=endMonth;i++){
						/**
						 * following if condition checks if joining month and year falls with in the financial year
						 * then the amount text field will be uneditable before his joining month.
						 * If true is passed then text field will be uneditable.Otherwise editable. 
						 */
					//	if(result){
						String s="0";
						if (i<10){
							s=s+String.valueOf(i);
						}else{
							s=String.valueOf(i);
						}
						
						String sql="SELECT CASE WHEN TO_CHAR(EMP_REGULAR_DATE,'yyyy-mm') > '"+String.valueOf(fromYear)+"-"+String.valueOf(s)+"' THEN 'TRUE' ELSE 'FALSE' END FROM HRMS_EMP_OFFC WHERE EMP_ID="+empInvestment.getEmpID();	
						Object[][] param=getSqlModel().getSingleResult(sql);
						
						if(String.valueOf(param[0][0]).equals("TRUE")){
							enable=false;
						}else{
							enable=true;
						}
					
						if(enable){
							hm.put(""+index++, "");
						}else{
							hm.put(""+index++, "true");
						}
						HouseRent bean=new HouseRent();
						bean.setMonthNo(String.valueOf(i));//Month No.
						bean.setMonth(Utility.month(i)+"-"+(fromYear));//Month Name
						bean.setAmount("0");
						sum+=0;
						if(i==12){
							i=0;
							startMonth=1;endMonth=3;fromYear=fromYear+1;
						}//End if
						hrList.add(bean);
					}//End of for loop
			
				java.util.HashMap hmFinal=new java.util.HashMap();
				hmFinal.put("0", hm);
				request.setAttribute("month",hmFinal);
			
				empInvestment.setTotAmt(String.valueOf(sum));	//Sets the total amount	
				empInvestment.setRentList(hrList);
				empInvestment.setRentFlag("true");
				return data;	
			}//End if
				
			else {
				
				
				
				/**
				 * following code sets the rent amount value from hrms_houserent_dtl table.
				 */
				Object[] para=new Object[3];
				para[0]=empInvestment.getFromYear();//Financial From Year
				para[1]=empInvestment.getToYear();//Financial To Year
				para[2]=empInvestment.getEmpID();//Employee Id
				
				Object[][] param = getSqlModel().getSingleResult(getQuery(3),para);
				Object[][] param1 = getSqlModel().getSingleResult(getQuery(4),para);
				if(param1==null || param1.length < 1){
					empInvestment.setRentCode("");
				}else {
				empInvestment.setRentCode(String.valueOf(param1[0][0]));
				}
			
				ArrayList<Object> houserRentList=new ArrayList<Object>();
				float sum=0;
				for(int i=0;i< param.length;i++){
					sum+=Float.parseFloat(String.valueOf(param[i][2]));
					HouseRent bean =new HouseRent();
					bean.setMonthNo(String.valueOf(param[i][0]));     //Month No.
					bean.setMonth(String.valueOf(param[i][1]));       //Month Name
					bean.setAmount(String.valueOf(param[i][2]));      //Rent amount
					if((String.valueOf(param[i][3])).equals("")|| param[i][3]==null){
						bean.setProofAttach("");  // attach proof
					}else{
						bean.setProofAttach(String.valueOf(param[i][3]));// attach proof
					}
					houserRentList.add(bean);	
				}//End of for loop
				empInvestment.setTotAmt(String.valueOf(sum));	//Total Amount
				empInvestment.setRentList(houserRentList);
				empInvestment.setRentFlag("true");
				
				/*
				 * following code is used to make the amount text field uneditable before his joining month.
				 */
				int index=0;
				int startMonth=4,endMonth=12;
				int fromYear=Integer.parseInt(empInvestment.getFromYear());
				boolean enable=false;
				for(int i=startMonth;i<=endMonth;i++){
					/**
					 * following if condition checks if joining month and year falls with in the financial year
					 * then the amount text field will be uneditable before his joining month.
					 */
					
					String s="0";
					if (i<10){
						s=s+String.valueOf(i);
					}else{
						s=String.valueOf(i);
					}
					
					
					String sql="SELECT CASE WHEN TO_CHAR(EMP_REGULAR_DATE,'yyyy-mm') > '"+String.valueOf(fromYear)+"-"+String.valueOf(s)+"' THEN 'TRUE' ELSE 'FALSE' END FROM HRMS_EMP_OFFC WHERE EMP_ID="+empInvestment.getEmpID();	
					Object[][] param2=getSqlModel().getSingleResult(sql);
					
					if(String.valueOf(param2[0][0]).equals("TRUE")){
						enable=false;
					}else{
						enable=true;
					}
						if(enable){
							hm.put(""+index++, "");
						}else{
							hm.put(""+index++, "true");
						}
					if(i==12){
						i=0;
						startMonth=1;
						endMonth=3;
						fromYear=fromYear+1;
					}//End if
					
				}//End of for loop
				java.util.HashMap hmFinal=new java.util.HashMap();
				hmFinal.put("0", hm);
				request.setAttribute("month",hmFinal);
				return param;	
			}//End of else condition
	}
	
	
	public void getFinYear(HouseRent hr){
		String query=" SELECT RENT_FROMYEAR,RENT_TOYEAR FROM HRMS_HOUSERENT_HDR WHERE RENT_EMPID ="+hr.getEmpid()+" AND RENT_CODE="+hr.getRentCode();
		Object[][] data = getSqlModel().getSingleResult(query);
		hr.setFromYear(String.valueOf(data[0][0]));
		hr.setToYear(String.valueOf(data[0][1]));
		
	}
	
	/**
	 * following function is called to add the rent amount for the respective months in the table HRMS_HOUSERENT_DTL
	 * first query inserts employee id,fin from year,fin to year in the hrms_houserent_hdr table 
	 * @param empInvestment
	 * @param monCode
	 * @param amount
	 * @return
	 */
	public boolean addRent(EmpInvestmentMaster empInvestment,String[] monCode,String[] amount,String[] prfAttach){
		
				boolean result=false;
				Object[][] add=new Object[1][11];
				add[0][0]=empInvestment.getEmpID();//Employee Id
				add[0][1]=empInvestment.getFromYear();//Fin from year
				add[0][2]=empInvestment.getToYear();//Fin To Year
				add[0][3]=empInvestment.getOwner1Name();//Owner1 name
				add[0][4]=empInvestment.getOwner1Address();//Owner1 Address
				add[0][5]=empInvestment.getOwner1Agreement();//Owner1 Agreement
				add[0][6]=empInvestment.getOwner1Period();//Owner1 Period
				add[0][7]=empInvestment.getOwner2Name();//Owner2 name
				add[0][8]=empInvestment.getOwner2Address();//Owner2 Address
				add[0][9]=empInvestment.getOwner2Agreement();//Owner2 Agreement
				add[0][10]=empInvestment.getOwner2Period();//Owner2 Period
				
				getSqlModel().singleExecute(getQuery(1),add);
				
				String query="SELECT MAX(RENT_CODE) FROM HRMS_HOUSERENT_HDR ";
				Object[][] data=getSqlModel().getSingleResult(query);
				
				if(data!=null && data.length >0){
					if(monCode!=null && monCode.length >0){
						
						Object[][] param=new Object[monCode.length][4];
						for(int i=0;i<monCode.length;i++){
							param[i][0]=monCode[i];//Month No
							if(amount[i].equals("") || amount[i].equals("null")){
								param[i][1]=String.valueOf("0");//Amount
							}else{
								param[i][1]=amount[i];//Amount
							}
							param[i][2]=String.valueOf(data[0][0]);//Rent code
							param[i][3]=String.valueOf(prfAttach[i]);//proof attach
							
						}//End of for loop
						result=getSqlModel().singleExecute(getQuery(2),param);
					}//End if
				}//End of outer if	
				if(result)
				{
					/**
					 * Following code calculates the tax
					 * and updates tax process
					 */
					try {
						CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
						taxmodel.initiate(context, session);
						logger.info("I m calling tax calculation method");
						Object empList[][] = new Object[1][1];
						empList[0][0] =  empInvestment.getEmpID();
						if(empList != null && empList.length > 0)
							taxmodel.calculateTax(empList,empInvestment.getFromYear(),empInvestment.getToYear());
						taxmodel.terminate();
					} catch (Exception e) {
						logger.error("Exception in addRent() of House Rent  while calling calculateTax : "+e);
						e.printStackTrace();
					}
				}
				
				return result;
	   }
	/**
	 * following function is called when the record is modified
	 * @param empInvestment
	 * @param monCode
	 * @param amount
	 * @return
	 */
	
	public boolean modRent(EmpInvestmentMaster empInvestment,String monCode[],String amount[],String prfAttach[]) {
				Boolean result=false;
				Object[][] param=null;
				if(monCode!=null && monCode.length >0){
					param=new Object[monCode.length][4];
					for(int i=0;i<monCode.length;i++){
						if(amount[i].equals("") || amount[i].equals("null")){
							param[i][0]=String.valueOf("0");//Amount
						}else{
						param[i][0]=amount[i];//Amount
						}
						param[i][1]=prfAttach[i];
						param[i][2]=empInvestment.getRentCode();//Rent Code
						param[i][3]=monCode[i];//Month No.
										
					}//End of for loop
				    result=getSqlModel().singleExecute(getQuery(5),param);
				    if(result){
					    Object[][] onwerDetails=new Object[1][11];
						
					    onwerDetails[0][0]=empInvestment.getOwner1Name();//Owner1 name
					    onwerDetails[0][1]=empInvestment.getOwner1Address();//Owner1 Address
					    onwerDetails[0][2]=empInvestment.getOwner1Agreement();//Owner1 Agreement
					    onwerDetails[0][3]=empInvestment.getOwner1Period();//Owner1 Period
					    onwerDetails[0][4]=empInvestment.getOwner2Name();//Owner2 name
					    onwerDetails[0][5]=empInvestment.getOwner2Address();//Owner2 Address
					    onwerDetails[0][6]=empInvestment.getOwner2Agreement();//Owner2 Agreement
					    onwerDetails[0][7]=empInvestment.getOwner2Period();//Owner2 Period
					    onwerDetails[0][8]=empInvestment.getFromYear();//Fin from year
					    onwerDetails[0][9]=empInvestment.getToYear();//Fin To Year
					    onwerDetails[0][10]=empInvestment.getEmpID();//Employee Id
					   					
					    result=getSqlModel().singleExecute(getQuery(9),onwerDetails);
				    }    
				    
				}//End if
				if(result)
				{
					/**
					 * Following code calculates the tax
					 * and updates tax process
					 */
					try {
						CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
						taxmodel.initiate(context, session);
						logger.info("I m calling tax calculation method");
						Object empList[][] = new Object[1][1];
						empList[0][0] =  empInvestment.getEmpID();
						if(empList != null && empList.length > 0)
							taxmodel.calculateTax(empList,empInvestment.getFromYear(),empInvestment.getToYear());
						taxmodel.terminate();
					} catch (Exception e) {
						logger.error("Exception in modRent() of House Rent  while calling calculateTax : "+e);
						e.printStackTrace();
					}
				}
				return result;
	}
	
	/**
	 * following function is called when the record is deleted
	 * @param empInvestment
	 * @return
	 * first the record will be deleted from hrms_houserent_dtl
	 * second record will be deleted from hrms_houserent_hdr
	 * 	 */
	public boolean deleteRent(EmpInvestmentMaster empInvestment){
		Object delObj[][]=new Object[1][1];
		delObj[0][0]=empInvestment.getRentCode();
		logger.info("delObj[0][0]===="+delObj[0][0]);
		getSqlModel().singleExecute(getQuery(6),delObj);
		return getSqlModel().singleExecute(getQuery(7),delObj);
	}
	/**
	 * following function is called when a general user makes login
	 * @param emId
	 * @param hr
	 */
	public void getEmployeeDetails(String emId,HouseRent hr){
		String query=" SELECT EMP_TOKEN,NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ')," 
				+"	NVL(RANK_NAME,' '),NVL(CENTER_NAME,' ') , EMP_ID FROM HRMS_EMP_OFFC  	"
				+ "LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)	" 
				+ "LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK " 
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
				+" WHERE EMP_ID="+emId;
				Object[][] data = getSqlModel().getSingleResult(query);
				hr.setEmpTokenNo(String.valueOf(data[0][0]));//Token No.
				hr.setEmpName(String.valueOf(data[0][1]));//Employee Name
				hr.setDesignation(String.valueOf(data[0][2]));//Designation
				hr.setCenter(String.valueOf(data[0][3]));//Branch
				hr.setEmpid(String.valueOf(data[0][4]));//Emp id
		
	}
	/**
	 * following function is get Owner Details
	 * @param empInvestment
	 */
	
	public void getOwnerDetails(EmpInvestmentMaster empInvestment){
		Object[] para=new Object[3];
		para[0]=empInvestment.getFromYear();//Financial From Year
		para[1]=empInvestment.getToYear();//Financial To Year
		para[2]=empInvestment.getEmpID();//Employee Id
		
		Object[][] ownerData = getSqlModel().getSingleResult(getQuery(8),para);
		if(ownerData.length <0 ||ownerData== null){
			empInvestment.setOwner1Address("");
			empInvestment.setOwner1Agreement("");
			empInvestment.setOwner1Name("");
			empInvestment.setOwner1Period("");
			empInvestment.setOwner2Address("");
			empInvestment.setOwner2Agreement("");
			empInvestment.setOwner2Name("");
			empInvestment.setOwner2Period("");
			
		}else {
			for(int i=0;i<=ownerData.length;i++){
				
				empInvestment.setOwner1Name(checkNull(String.valueOf(ownerData[i][0])));
				empInvestment.setOwner1Address(checkNull(String.valueOf(ownerData[i][1])));
				empInvestment.setOwner1Agreement(checkNull(String.valueOf(ownerData[i][2])));
				empInvestment.setOwner1Period(checkNull(String.valueOf(ownerData[i][3])));
				empInvestment.setOwner2Name(checkNull(String.valueOf(ownerData[i][4])));
				empInvestment.setOwner2Address(checkNull(String.valueOf(ownerData[i][5])));
				empInvestment.setOwner2Agreement(checkNull(String.valueOf(ownerData[i][6])));
				empInvestment.setOwner2Period(checkNull(String.valueOf(ownerData[i][7])));
			}	
		}

	}
	public String checkNull(String result) {
		
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
}
