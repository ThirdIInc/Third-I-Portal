/**
 * 
 */
package org.paradyne.model.payroll.pension;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.payroll.pension.PensionProcess;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 @author aa0418 Prakash
 * Date 30-09-2009
 *
 */
public class PensionProcessModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PensionProcessModel.class);

	HashMap<String, Object[][]> empPensCreditsMap=null;
	public boolean processPension(PensionProcess bean,HttpServletRequest request, String ledgerCode, String type){
		boolean result = false;
		try{			
			bean.setFlag("false");
			Object [][] pensionHead = getPensionHeads();
			empPensCreditsMap =getEmpPensCreditsMap(bean.getDivCode());
			
			request.setAttribute("creditHead",pensionHead);
			
			String empQuery=" SELECT PENS_EMP_ID,EMP_TOKEN,EMP_FNAME||' '||NVL(EMP_MNAME,'')||'  '||EMP_LNAME AS NAME,DECODE(PENS_PENSION_TYPE,'1','Super Annuation', "
						+" '2','Voluntary','3','Compulsory Retirement','4','Death') AS TYPE, '0' AS ARREAR,NVL(PENS_GROSS_AMOUNT,0),PENS_COMM_AMOUNT,'0' AS RECOVERY,'0' AS MISC,'', "
						+" TO_CHAR(PENS_RETIRE_DATE,'DD-MM-YYYY'),TO_CHAR(SYSDATE,'DD-MM-YYYY'),  DECODE (PENS_CAL_STATUS, 'L', 'N', 'O', 'Y')"
						+" FROM HRMS_PENSION_EMPLOYEES "
						+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_PENSION_EMPLOYEES.PENS_EMP_ID "
						+" WHERE PENS_CAL_STATUS IN ('L','O') "
						+" AND HRMS_EMP_OFFC.EMP_DIV = "+bean.getDivCode()
						//+" AND TO_DATE(PENS_RETIRE_DATE,'DD-MM-YYYY') <= TO_DATE(SYSDATE,'DD-MM-YYYY')"
						+" AND TO_DATE(TO_CHAR(PENS_RETIRE_DATE ,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('01-"+bean.getMonth()+"-"+bean.getYear()+"','DD-MM-YYYY')"
						+" ORDER BY UPPER(NAME)"; 
		
		Object [][] empObj = getSqlModel().getSingleResult(empQuery);
		
		/*String[] pageIndex = Utility.doPaging(bean.getMyPage(),empObj.length, 20);	
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
			bean.setMyPage("1");*/
			
		HashMap<String, String> arrearsMap=getArrearsMap(bean);
		if (empObj != null && empObj.length != 0) {
			
			Object [][]pensionObject = new Object [empObj.length][9];
			Object [][] creditObject = new Object [empObj.length * pensionHead.length][4];
			Object[][] deleteData = new Object[empObj.length][1];
			Object [][] pensionLedger = new Object[1][5];
			Object [] temp = null;
			int count =0;
			
			HashMap salMap = new HashMap();
			HashMap commutationMap = new HashMap();
			HashMap recoveryMap = new HashMap();
			for (int i = 0; i < empObj.length; i++) {
				commutationMap.put(String.valueOf(empObj[i][0]), "0");
				recoveryMap.put(String.valueOf(empObj[i][0]), "0");
			}
			setEmpRecoveryMap(bean.getMonth(), bean.getYear(), recoveryMap);
			setEmpCommutaionMap(bean.getMonth(), bean.getYear(), commutationMap);
			ArrayList empList = new ArrayList();
			
			int j=1;
			//for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {
			for (int i = 0; i < empObj.length; i++) {
				
				PensionProcess bean1 = new PensionProcess();
				try{
					double netAmt = 0.0;
					Object [][] salObject = null;
					bean1.setEmpId(checkNull(String.valueOf(empObj[i][0])));
					bean1.setEmpCode(checkNull(String.valueOf(empObj[i][1])));
					bean1.setEmpName(checkNull(String.valueOf(empObj[i][2])));
					bean1.setPenType(checkNull(String.valueOf(empObj[i][3])));
					String arrearsAmt="0"; 
					try {
						arrearsAmt = arrearsMap.get(String
								.valueOf(empObj[i][0]));
					} catch (Exception e) {
						// TODO: handle exception
					}
					double arrearsAmtDouble=0;
					try {
						arrearsAmtDouble=Double.parseDouble(arrearsAmt);
					} catch (Exception e) {
						arrearsAmtDouble=0;
					}
					bean1.setPenArrear(checkZero(arrearsAmt));
					
					Object dateData1 [] = String.valueOf(empObj[i][10]).split("-");//retirement date
					Object dateData2 []=null;
					if(Integer.parseInt(bean.getMonth())<10){
						dateData2 = ("01-0"+bean.getMonth()+"-"+bean.getYear()).split("-");		
					}else{
					dateData2 = ("01-"+bean.getMonth()+"-"+bean.getYear()).split("-");	
					}//String.valueOf(empObj[i][11]).split("-");//sysdate
					
					if(Integer.parseInt(String.valueOf(dateData1[1])) == Integer.parseInt(String.valueOf(dateData2[1]))){
						
						if(Integer.parseInt(String.valueOf(dateData1[2])) == Integer.parseInt(String.valueOf(dateData2[2]))){
																			
							String monthDays =String.valueOf(getMonthDays(String.valueOf(dateData1[1]), String.valueOf(dateData1[2])));
							
							String days = String.valueOf(Integer.parseInt(monthDays) - Integer.parseInt(String.valueOf(dateData1[0])));
							
							bean1.setPenAmount(checkZero(getDaysAmt(String.valueOf(empObj[i][5]),days,monthDays )));
							
							salObject = getPensionCredit(String.valueOf(empObj[i][0]),pensionHead.length,days,monthDays,pensionHead);
							
						}else{							
							bean1.setPenAmount(String.valueOf(Double.parseDouble(checkZero(String.valueOf(empObj[i][5])))+arrearsAmtDouble));
							salObject = getPensionCredit(String.valueOf(empObj[i][0]),pensionHead.length,pensionHead);
						}
						
					}else{						
						bean1.setPenAmount(String.valueOf(Double.parseDouble(checkZero(String.valueOf(empObj[i][5])))+arrearsAmtDouble));
						salObject = getPensionCredit(String.valueOf(empObj[i][0]),pensionHead.length,pensionHead);
					}
						
					//if map.empid is null amt = 0 else set amount
					
					bean1.setCommAmount(checkZero(String.valueOf(commutationMap.get(String.valueOf(empObj[i][0])))));
					bean1.setRecAmount(checkZero(String.valueOf(recoveryMap.get(String.valueOf(empObj[i][0])))));
					bean1.setMiscAmount(checkZero(String.valueOf(empObj[i][8])));
										
					netAmt = Double.parseDouble(bean1.getPenAmount()) -
							(Double.parseDouble(bean1.getCommAmount()) +
							 Double.parseDouble(bean1.getRecAmount()) +
							 Double.parseDouble(bean1.getMiscAmount()));
					
					if(netAmt < 0)
						netAmt = 0;
					
					bean1.setNetAmount(checkZero(String.valueOf(Math.round(netAmt))));
										
					request.setAttribute("empId"+j, checkNull(String.valueOf(empObj[i][0])));
					salMap.put(String.valueOf(empObj[i][0]), salObject);
					
					j++;
					//====================LOGIC FOR SAVE BEGINS - ADDED BY REEBA ============================
					try {
						pensionObject[i][0] = ledgerCode;
						pensionObject[i][1] = String.valueOf(empObj[i][0]);
						pensionObject[i][2] = bean1.getPenAmount();
						pensionObject[i][3] = bean1.getCommAmount();
						pensionObject[i][4] = bean1.getRecAmount();
						pensionObject[i][5] = bean1.getPenArrear();
						pensionObject[i][6] = bean1.getMiscAmount();
						pensionObject[i][7] = bean1.getNetAmount();
						pensionObject[i][8] = String.valueOf(empObj[i][12]);
						
						deleteData[i][0] = String.valueOf(empObj[i][0]);
						
						//logger.info("salObject ======"+salObject.length);
						//logger.info("pensionHead ===="+pensionHead.length);
						//Object[][] pensionCredit = (Object[][])salMap.get(empObj[i][0]);
						for(int k=0;k<pensionHead.length;k++){
							//logger.info("salObject code===="+salObject[k][0]);
							//logger.info("salObject amnt===="+salObject[k][1]);
							creditObject[count][0] = ledgerCode;
							creditObject[count][1] = String.valueOf(empObj[i][0]);
							creditObject[count][2] = String.valueOf(salObject[k][0]);
							creditObject[count][3] = String.valueOf(salObject[k][1]);
							
							count++;
								
						}
					} catch (Exception e) {
						logger.error("error in creating object for saving -- "+e);
						e.printStackTrace();
					}
					
					
					//====================LOGIC FOR SAVE ENDS - ADDED BY REEBA ============================
					
				}catch(Exception e){
					logger.error("exception in processPension while setting the list and salary"+e);
					e.printStackTrace();
				}
				empList.add(bean1);
								
			}
			//====================LOGIC FOR SAVE BEGINS - ADDED BY REEBA ============================
			String ledgerInsert = "INSERT INTO HRMS_PENSION_LEDGER (LEDGER_CODE,LEDGER_MONTH,LEDGER_YEAR,LEDGER_DIVISION,LEDGER_STATUS) VALUES(?,?,?,?,?)";
			
			String delPension = "DELETE FROM HRMS_PENSION_"+bean.getYear()+" WHERE PENS_LEDGER_CODE = "+ledgerCode+" AND PENS_EMP_ID = ?" ;
			
			String delPensionCredit = "DELETE FROM HRMS_PENSION_CREDIT_"+bean.getYear()+" WHERE PENS_LEDGER_CODE = "+ledgerCode+" AND PENS_EMP_ID = ?";
			
			String creditInsert = "INSERT INTO HRMS_PENSION_CREDIT_"+bean.getYear()+" (PENS_LEDGER_CODE,PENS_EMP_ID,PENS_CREDIT_CODE,PENS_AMOUNT) VALUES (?,?,?,?)";
			
			String pensionInsert = "INSERT INTO HRMS_PENSION_"+bean.getYear()+" (PENS_LEDGER_CODE,PENS_EMP_ID,PENS_AMOUNT,PENS_COMM_AMOUNT,PENS_REC_AMOUNT,PENS_ARREARS_AMOUNT,PENS_MISC_RECOVERY,PENS_NET_AMOUNT,PENS_ONHOLD)"
									+"VALUES(?,?,?,?,?,?,?,?,?)";
		
			try{
				if(type.equals("I")){
					pensionLedger[0][0] = ledgerCode;
					pensionLedger[0][1] = bean.getMonth();
					pensionLedger[0][2] = bean.getYear();
					pensionLedger[0][3] = bean.getDivCode();
					pensionLedger[0][4] = "PENS_START";
					
					getSqlModel().singleExecute(ledgerInsert, pensionLedger);
				}
				
			}catch(Exception e){
				logger.error("exception in  inserting pension ledger", e);
			}
			try{
				getSqlModel().singleExecute(delPensionCredit,deleteData);
				
				getSqlModel().singleExecute(creditInsert, creditObject);
				
			}catch(Exception e){
				logger.error("exception in deleting and inserting credit data 4 pension", e);
			}
			
			try{
				getSqlModel().singleExecute(delPension,deleteData);
				
				result = getSqlModel().singleExecute(pensionInsert, pensionObject);
				
			}catch(Exception e){
				logger.error("exception in deleting and inserting data 4 pension", e);
			}
			
			//====================LOGIC FOR SAVE ENDS - ADDED BY REEBA ============================
			
			bean.setEmpList(empList);
			request.setAttribute("salaryMap", salMap);
			bean.setNoData("false");
			
		}else
			bean.setNoData("true");
		
		bean.setMonthView(Utility.month(Integer.parseInt(bean.getMonth())));
		
		
		}catch(Exception e){
			logger.error("exception in processPension------------"+e);
			e.printStackTrace();
		}
		return result;
	}
	
	private HashMap<String, Object[][]> getEmpPensCreditsMap(String divCode) {
		HashMap<String, Object[][]> empCreditMap=new HashMap<String, Object[][]>();
		String empCredits ="SELECT PENS_CREDIT_CODE,PENS_AMOUNT,PENS_EMP_ID FROM HRMS_PENSION_EMPCREDIT  ORDER BY PENS_EMP_ID,PENS_CREDIT_CODE";
		Object [][]empPensionObj=getSqlModel().getSingleResult(empCredits);
		String empQuery="SELECT DISTINCT PENS_EMP_ID FROM HRMS_PENSION_EMPCREDIT  ORDER BY PENS_EMP_ID";
		Object [][]empObj=getSqlModel().getSingleResult(empQuery);
		String []empString=new String[empObj.length];
		for (int i = 0; i < empString.length; i++) {
			empString[i]=String.valueOf(empObj[i][0]);
		}
		HashMap mapData = convertObjectToHashMap(empPensionObj, empString);
		return mapData;
	}

	private HashMap<String, String> getArrearsMap(PensionProcess bean) {
		HashMap<String , String> empArrearsMap=new HashMap<String, String>();
		int year=Integer.parseInt(bean.getYear());
		String arrearsQuery="SELECT PENS_EMP_ID,SUM(PENS_NET_AMOUNT) FROM HRMS_PENSION_"+year+" WHERE PENS_PAID_IN_MONTH="+bean.getMonth()+" AND PENS_PAID_IN_YEAR="+year+" GROUP BY PENS_EMP_ID";
		Object [][]arrearsObj=getSqlModel().getSingleResult(arrearsQuery);
		if(arrearsObj !=null && arrearsObj.length>0){
			for (int i = 0; i < arrearsObj.length; i++) {
				empArrearsMap.put(String.valueOf(arrearsObj[i][0]), String.valueOf(arrearsObj[i][1]));
			}
		}
		arrearsQuery="SELECT PENS_EMP_ID,SUM(PENS_NET_AMOUNT) FROM HRMS_PENSION_"+(year-1)+" WHERE PENS_PAID_IN_MONTH="+bean.getMonth()+" AND PENS_PAID_IN_YEAR="+year+" GROUP BY PENS_EMP_ID";
		Object [][]arrearsObjPrev=getSqlModel().getSingleResult(arrearsQuery);
		if(arrearsObjPrev !=null && arrearsObjPrev.length>0){
			for (int i = 0; i < arrearsObjPrev.length; i++) {
				double arrearsAmt=Double.parseDouble(String.valueOf(arrearsObjPrev[i][1]));
				double arrearsAmtPrev=0;
				
				try {
					arrearsAmtPrev = Double.parseDouble(empArrearsMap
							.get(String.valueOf(arrearsObjPrev[i][0])));
				} catch (Exception e) {
					arrearsAmtPrev=0;
				}
				empArrearsMap.put(String.valueOf(arrearsObjPrev[i][0]), String.valueOf(arrearsAmt+arrearsAmtPrev));
			}
		}
		return empArrearsMap;
	}

	public Object[][] getPensionCredit(String empCode,int creditLength,Object [][]creditHead){
		
		Object [][] pensObj = new Object[creditLength][2];
		Object [][] returnObj = new Object[creditLength][2];
		for (int i = 0; i < returnObj.length; i++) {
			returnObj[i][0]=creditHead[i][0];
			returnObj[i][1]="0";
		}
		String pensionQUery = " SELECT PENS_CREDIT_CODE,PENS_AMOUNT FROM HRMS_PENSION_EMPCREDIT WHERE PENS_EMP_ID = "+empCode +" ORDER BY PENS_CREDIT_CODE";
		
		pensObj = (Object[][])empPensCreditsMap.get(empCode);
		for (int i = 0; i < returnObj.length; i++) {
			for (int j = 0; j < pensObj.length; j++) {
				if(String.valueOf(pensObj[j][0]).equals(String.valueOf(returnObj[i][0]))){
					returnObj[i][1]=pensObj[j][1];
					break;
				}
			}
		}
		
		return returnObj;
	}
	public Object[][] getPensionCredit(String empCode,int creditLength,String days,String monthDays,Object [][]creditHead){
		
		Object [][] pensObj = new Object[creditLength][2];
		Object [][] returnObj = new Object[creditLength][2];
		for (int i = 0; i < returnObj.length; i++) {
			returnObj[i][0]=creditHead[i][0];
			returnObj[i][1]="0";
		}
		String pensionQUery = " SELECT PENS_CREDIT_CODE,ROUND(((PENS_AMOUNT * "+days+")/"+monthDays+")) FROM HRMS_PENSION_EMPCREDIT WHERE PENS_EMP_ID = "+empCode +" ORDER BY PENS_CREDIT_CODE";
		
		pensObj = getSqlModel().getSingleResult(pensionQUery);
		for (int i = 0; i < returnObj.length; i++) {
			for (int j = 0; j < pensObj.length; j++) {
				if(String.valueOf(pensObj[j][0]).equals(String.valueOf(returnObj[i][0]))){
					returnObj[i][1]=pensObj[j][1];
					break;
				}
			}
		}
		return returnObj;
	}
	public Object[][] getPensionHeads(){
		
		String pensionQUery = " SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE,CREDIT_ABBR FROM HRMS_PENSION_CREDIT_CONF "
							+" INNER JOIN HRMS_CREDIT_HEAD on HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PENSION_CREDIT_CONF.CREDIT_CODE " 
							+" ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
		
		Object [][] pensObj = getSqlModel().getSingleResult(pensionQUery);
						
		return pensObj;
	}
	public Object[][] getSavedPensionHeads(String year){
		
		String pensionQUery = " SELECT DISTINCT CREDIT_CODE,CREDIT_ABBR FROM HRMS_PENSION_CREDIT_"+year
							+" INNER JOIN HRMS_CREDIT_HEAD on HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PENSION_CREDIT_"+year+".PENS_CREDIT_CODE " 
							+" ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ";
		
		Object [][] pensObj = getSqlModel().getSingleResult(pensionQUery);
						
		return pensObj;
	}
	
	public HashMap setEmpCommutaionMap(String month,String year,HashMap empCommutaionMap){
		try{
		if(Integer.parseInt(month)<10){
			month ="0"+month;
		}
		String currentDate  = year+month; 
		
		String commutationQuery = " SELECT PENS_EMP_ID,NVL(PENS_COMM_AMOUNT,0) FROM HRMS_PENSION_EMPLOYEES "
								+" WHERE " 
								//+" TO_DATE('01-'||PENS_COMM_MON_FROM||'-'||PENS_COMM_YEAR_FROM,'DD-MM-YYYY') <= TO_DATE('"+currentDate+"','DD-MM-YYYY') AND "
								//+" TO_DATE('01-'||PENS_COMM_MON_TO||'-'||PENS_COMM_YEAR_TO,'DD-MM-YYYY') >= TO_DATE('"+currentDate+"','DD-MM-YYYY') AND PENS_EMP_ID = "+empId
								+" CASE WHEN PENS_COMM_MON_FROM <10 THEN TO_NUMBER(NVL(PENS_COMM_YEAR_FROM,0)||'0'||NVL(PENS_COMM_MON_FROM,0)) ELSE TO_NUMBER(NVL(PENS_COMM_YEAR_FROM,0)||''||NVL(PENS_COMM_MON_FROM,0))END "
		 						+" <="+currentDate+" AND "
		 						+" CASE WHEN PENS_COMM_MON_TO <10 THEN TO_NUMBER(NVL(PENS_COMM_YEAR_TO,0)||'0'||NVL(PENS_COMM_MON_TO,0)) ELSE TO_NUMBER(NVL(PENS_COMM_YEAR_TO,0)||''||NVL(PENS_COMM_MON_TO,0))END "
		 						+" >="+currentDate; 		//AND PENS_EMP_ID = "+empId;
		Object [][] commObject = getSqlModel().getSingleResult(commutationQuery);
		
		if (commObject != null && commObject.length != 0) 
			for (int i = 0; i < commObject.length; i++) {
				empCommutaionMap.put(String.valueOf(commObject[i][0]), String.valueOf(commObject[i][1]));
			}	
		}catch(Exception e){
			logger.error("exception in getCommutation"+e);
		}
		return empCommutaionMap;
	}
	
	/*public HashMap getCommutation(String month,String year,String empIdList){
		//String commAmt="0";
		HashMap dataMap = new HashMap();
		try{
		String currentDate  = "01-"+month+"-"+year; 
		
		String commutationQuery = " SELECT PENS_EMP_ID, NVL(PENS_COMM_AMOUNT,0) FROM HRMS_PENSION_EMPLOYEES "
								+" WHERE " 
								+" TO_DATE('01-'||PENS_COMM_MON_FROM||'-'||PENS_COMM_YEAR_FROM,'DD-MM-YYYY') <= TO_DATE('"+currentDate+"','DD-MM-YYYY') AND "
								+" TO_DATE('01-'||PENS_COMM_MON_TO||'-'||PENS_COMM_YEAR_TO,'DD-MM-YYYY') >= TO_DATE('"+currentDate+"','DD-MM-YYYY') AND PENS_EMP_ID IN ("+empIdList+")";
		
		Object [][] commObject = getSqlModel().getSingleResult(commutationQuery);
		
		String[] empLength = empIdList.split(",");
		
		Vector v = new Vector();
		
		if (commObject != null && commObject.length > 0) {
			for (int i = 0; i < empLength.length; i++) {
				String empId = "";
				empId = String.valueOf(empLength[i]);
				for (int j = 0; j < commObject.length; j++) {
					if(String.valueOf(commObject[j][0]).equals(empLength[i])){
						v.add(commObject[j]);
					} //end of if
				} //end of totalDataObject loop
				Object[][]data = new Object[v.size()][2];
				for (int k = 0; k < data.length; k++) {
					data[k] = (Object[])v.get(k);
				} //end of loop
				dataMap.put(empId, data);
				v = new Vector();
			} //end of empList loop
			
		}
				
		}catch(Exception e){
			logger.error("exception in getCommutation"+e);
		}
		return dataMap;
	}*/
	public HashMap setEmpRecoveryMap(String month,String year,HashMap recoveryMap){
		//String recAmt="0";
		try{
			String currentDate  = "01-"+month+"-"+year; 
			
			String recQuery = " SELECT PENS_EMP_ID,SUM(NVL(PENS_RECOVERY_AMT,0)) FROM HRMS_PENSION_RECOVERY " 
								+" WHERE "
								+" TO_DATE('01-'||PENS_REC_UPTO_MONTH||'-'||PENS_REC_UPTO_YEAR,'DD-MM-YYYY') >= TO_DATE('"+currentDate+"','DD-MM-YYYY')"
								+" GROUP BY PENS_EMP_ID";
			
			Object [][] recObject = getSqlModel().getSingleResult(recQuery);
			
			if (recObject != null && recObject.length != 0) 
				for (int i = 0; i < recObject.length; i++) {
					recoveryMap.put(String.valueOf(recObject[i][0]), String.valueOf(recObject[i][1]));
				}
							
		}catch(Exception e){
			logger.error("exception in getRecovery"+e);
		}
		return recoveryMap;
	}
	public String getDaysAmt(String amount,String days,String monthDays){
		
		amount = String.valueOf(Math.round((Double.parseDouble(amount) * Double.parseDouble(days)) / Double.parseDouble(monthDays))); 
			
		return amount;
	}
	public boolean save(HttpServletRequest request,String year,String month,String divCode,String ledgerCode,String type){
				
		boolean result = false;
		
		try {
								
			String ledgerInsert = "INSERT INTO HRMS_PENSION_LEDGER (LEDGER_CODE,LEDGER_MONTH,LEDGER_YEAR,LEDGER_DIVISION,LEDGER_STATUS) VALUES(?,?,?,?,?)";
			
			String delPension = "DELETE FROM HRMS_PENSION_"+year+" WHERE PENS_LEDGER_CODE = "+ledgerCode+" AND PENS_EMP_ID = ?" ;
			
			String delPensionCredit = "DELETE FROM HRMS_PENSION_CREDIT_"+year+" WHERE PENS_LEDGER_CODE = "+ledgerCode+" AND PENS_EMP_ID = ?";
			
			String creditInsert = "INSERT INTO HRMS_PENSION_CREDIT_"+year+" (PENS_LEDGER_CODE,PENS_EMP_ID,PENS_CREDIT_CODE,PENS_AMOUNT) VALUES (?,?,?,?)";
			
			String pensionInsert = "INSERT INTO HRMS_PENSION_"+year+" (PENS_LEDGER_CODE,PENS_EMP_ID,PENS_AMOUNT,PENS_COMM_AMOUNT,PENS_REC_AMOUNT,PENS_ARREARS_AMOUNT,PENS_MISC_RECOVERY,PENS_NET_AMOUNT)"
									+"VALUES(?,?,?,?,?,?,?,?)";
			
			String[] creditHeadObj = request.getParameterValues("headCode");
			String[] emp_id	=request.getParameterValues("empId");
			String[] pensionObj	=request.getParameterValues("penAmount");
			String[] commObj=request.getParameterValues("commAmount");
			String[] recObj	=request.getParameterValues("recAmount");
			String[] penArreObj=request.getParameterValues("penArrear");   
			String[] miscObj =request.getParameterValues("miscAmount");
			String[] netObj	=request.getParameterValues("netAmount");
			
			Object [][]pensionObject = new Object [emp_id.length][8];
			Object [][] creditObject = new Object [emp_id.length * creditHeadObj.length][4];
			Object[][] deleteData = new Object[emp_id.length][1];
			Object [][] pensionLedger = new Object[1][5];
			Object [] temp = null;
			int count =0;
			if(emp_id != null && emp_id.length > 0){
				
					for(int i =0;i<emp_id.length;i++){
									
						try {
							pensionObject[i][0] = ledgerCode;
							pensionObject[i][1] = String.valueOf(emp_id[i]);
							pensionObject[i][2] = String.valueOf(pensionObj[i]);
							pensionObject[i][3] = String.valueOf(commObj[i]);
							pensionObject[i][4] = String.valueOf(recObj[i]);
							pensionObject[i][5] = String.valueOf(penArreObj[i]);
							pensionObject[i][6] = String.valueOf(miscObj[i]);
							pensionObject[i][7] = String.valueOf(netObj[i]);
							
							deleteData[i][0] = String.valueOf(emp_id[i]);
							
							for(int k=0;k<creditHeadObj.length;k++){
									
								temp = request.getParameterValues((i+1)+"_"+k+"_C");
								
								creditObject[count][0] = ledgerCode;
								creditObject[count][1] = String.valueOf(emp_id[i]);
								creditObject[count][2] = String.valueOf(creditHeadObj[k]);
								creditObject[count][3] = temp[0];
								
								count++;
									
							}
						} catch (Exception e) {
							logger.error("error in creating object for saving -- "+e);
							//e.printStackTrace();
						}
					}
				}
				try{
					if(type.equals("I")){
						pensionLedger[0][0] = ledgerCode;
						pensionLedger[0][1] = month;
						pensionLedger[0][2] = year;
						pensionLedger[0][3] = divCode;
						pensionLedger[0][4] = "PENS_START";
						
						getSqlModel().singleExecute(ledgerInsert, pensionLedger);
					}
					
				}catch(Exception e){
					logger.error("exception in  inserting pension ledger", e);
				}
				try{
					getSqlModel().singleExecute(delPensionCredit,deleteData);
					
					getSqlModel().singleExecute(creditInsert, creditObject);
					
				}catch(Exception e){
					logger.error("exception in deleting and inserting credit data 4 pension", e);
				}
				
				try{
					getSqlModel().singleExecute(delPension,deleteData);
					
					result = getSqlModel().singleExecute(pensionInsert, pensionObject);
					
				}catch(Exception e){
					logger.error("exception in deleting and inserting data 4 pension", e);
				}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return result;
		
	}
	public void getEmployeeList(PensionProcess bean,HttpServletRequest request, String filterQuery){
		empPensCreditsMap =getEmpPensCreditsMap(bean.getDivCode());
		try {
			Object [][] pensionHead = getSavedPensionHeads(bean.getYear());
			request.setAttribute("creditHead",pensionHead);
						
			String empQuery=" SELECT PENS_EMP_ID,EMP_TOKEN,EMP_FNAME||' '||NVL(EMP_MNAME,'')||'  '||EMP_LNAME AS NAME,DECODE(PENS_PENSION_TYPE,'1','Super Annuation', "
						+" '2','Voluntary','3','Compulsory Retirement','4','Death') AS TYPE, NVL(HRMS_PENSION_"+bean.getYear()+".PENS_ARREARS_AMOUNT,0),NVL(HRMS_PENSION_"+bean.getYear()+".PENS_AMOUNT,0), "
						+" NVL(HRMS_PENSION_"+bean.getYear()+".PENS_COMM_AMOUNT,0),NVL(HRMS_PENSION_"+bean.getYear()+".PENS_REC_AMOUNT,0),NVL(HRMS_PENSION_"+bean.getYear()+".PENS_MISC_RECOVERY,0),NVL(HRMS_PENSION_"+bean.getYear()+".PENS_NET_AMOUNT,0) " 
						+" FROM HRMS_PENSION_"+bean.getYear()
						+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_PENSION_"+bean.getYear()+".PENS_EMP_ID "
						+" INNER JOIN HRMS_PENSION_EMPLOYEES ON HRMS_PENSION_EMPLOYEES.PENS_EMP_ID = HRMS_EMP_OFFC.EMP_ID"
					//ADDED BY REEBA - BEGINS	
						+ " WHERE 1 = 1 ";
			try{
				empQuery += filterQuery;
			}catch (Exception e) {
				logger.error("Exception in filterQuery: "+e);
			}
			
			empQuery += " AND PENS_LEDGER_CODE = "+bean.getLedgerCode()
				+" AND PENS_CAL_STATUS ='L' ORDER BY UPPER(NAME)";
			
			//ADDED BY REEBA - ENDS
			
			Object [][] empObj = getSqlModel().getSingleResult(empQuery);
			
			if(!bean.getStatus().equals("PENS_FINAL")){
				//
					
					Object [][] extraEmp = getNotSavedEmp(bean.getLedgerCode(),bean.getDivCode(), bean.getYear(), bean.getMonth(),bean);
					
					if (extraEmp != null && extraEmp.length != 0) {
						if (empObj != null && empObj.length != 0) {
						empObj = Utility.joinArrays(empObj, extraEmp, 1, 0);
						}else{
							empObj =Utility.joinArrays(extraEmp,empObj, 1, 0);
						}
					}
					
				//}		
			}
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),empObj.length, 20);	
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
			
			if (empObj != null && empObj.length != 0) {
				
				
				HashMap salMap = new HashMap();
				ArrayList empList = new ArrayList();
				
				int j=1;
				for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {
				//for (int i = 0; i < empObj.length; i++) {	
					PensionProcess bean1 = new PensionProcess();
					try{
						bean1.setEmpId(checkNull(String.valueOf(empObj[i][0])));
						bean1.setEmpCode(checkNull(String.valueOf(empObj[i][1])));
						bean1.setEmpName(checkNull(String.valueOf(empObj[i][2])));
						bean1.setPenType(checkNull(String.valueOf(empObj[i][3])));
						bean1.setPenArrear(checkNull(String.valueOf(empObj[i][4])));
						bean1.setPenAmount(checkNull(String.valueOf(empObj[i][5])));
						bean1.setCommAmount(checkZero(String.valueOf(empObj[i][6])));
						bean1.setRecAmount(checkZero(String.valueOf(empObj[i][7])));
						bean1.setMiscAmount(checkNull(String.valueOf(empObj[i][8])));
						bean1.setNetAmount(checkZero(String.valueOf(empObj[i][9])));
					
						Object [][] salObject = getPensionCredit(String.valueOf(empObj[i][0]),bean.getLedgerCode(),bean.getYear());
						
						if(salObject != null && salObject.length > 0){
							
						}else{
							salObject = getPensionCredit(String.valueOf(empObj[i][0]),pensionHead.length,pensionHead);
						}
						
						request.setAttribute("empId"+j, checkNull(String.valueOf(empObj[i][0])));
						salMap.put(String.valueOf(empObj[i][0]), salObject);
						j++;
					}catch(Exception e){
						logger.error("exception in processPension while setting the list and salary"+e);
					}
					empList.add(bean1);
									
				}
				bean.setEmpList(empList);
				request.setAttribute("salaryMap", salMap);
				bean.setNoData("false");
			}else
				bean.setNoData("true");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public Object [][] getNotSavedEmp(String ledgerCode ,String divCode,String year,String month,PensionProcess bean){
		Object [][] empObj = null;
		empPensCreditsMap =getEmpPensCreditsMap(bean.getDivCode());
		try{
			String query = "SELECT PENS_EMP_ID,EMP_TOKEN,EMP_FNAME||' '||NVL(EMP_MNAME,'')||'  '||EMP_LNAME AS NAME, "
							+" DECODE(PENS_PENSION_TYPE,'1','Super Annuation','2','Voluntary','3','Compulsory Retirement','4','Death') AS TYPE, "
							+" nvl(0,0) AS ARREAR,nvl(PENS_AMOUNT,0),nvl(PENS_COMM_AMOUNT,0),nvl(0,0) AS RECOVERY,nvl(0,0) AS MISC,nvl(0,0)  "
							+" FROM HRMS_PENSION_EMPLOYEES  "
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_PENSION_EMPLOYEES.PENS_EMP_ID " 
							+" WHERE PENS_CAL_STATUS = 'L' "
							+" AND HRMS_EMP_OFFC.EMP_DIV = "+divCode
							+" AND PENS_EMP_ID NOT IN (SELECT PENS_EMP_ID FROM HRMS_PENSION_"+year+" WHERE PENS_LEDGER_CODE = "+ledgerCode+" )"
							+" AND TO_DATE(TO_CHAR(PENS_RETIRE_DATE ,'DD-MM-YYYY'),'DD-MM-YYYY')<= TO_DATE('01-"+bean.getMonth()+"-"+bean.getYear()+"','DD-MM-YYYY')"
							+" ORDER BY UPPER(NAME)"; 
			
			 empObj = getSqlModel().getSingleResult(query);
			 HashMap commutationMap = new HashMap();
				HashMap recoveryMap = new HashMap();
				for (int i = 0; i < empObj.length; i++) {
					commutationMap.put(String.valueOf(empObj[i][0]), "0");
					recoveryMap.put(String.valueOf(empObj[i][0]), "0");
				}
				setEmpRecoveryMap(month, year, recoveryMap);
				setEmpCommutaionMap(month, year, commutationMap);
				
			if(empObj != null && empObj.length > 0){
				
					for (int i = 0; i < empObj.length; i++) {
												
						double netAmt =0.0;
						HashMap<String, String> arrearsMap=getArrearsMap(bean);
						String arrearsAmt="0"; 
						try {
							arrearsAmt = arrearsMap.get(String
									.valueOf(empObj[i][0]));
						} catch (Exception e) {
							// TODO: handle exception
						}
						empObj[i][0] = checkNull(String.valueOf(empObj[i][0]));
						empObj[i][1] = checkNull(String.valueOf(empObj[i][1]));
						empObj[i][2] = checkNull(String.valueOf(empObj[i][2]));
						empObj[i][3] = checkNull(String.valueOf(empObj[i][3]));
						empObj[i][4] = checkZero(arrearsAmt);
						empObj[i][5] = checkZero(String.valueOf(empObj[i][5]));
						empObj[i][6] = checkZero(checkZero(String.valueOf(commutationMap.get(String.valueOf(empObj[i][0])))));
						empObj[i][7] = checkZero(checkZero(String.valueOf(commutationMap.get(String.valueOf(empObj[i][0])))));
						empObj[i][8] = checkZero(String.valueOf(empObj[i][8]));
												
						netAmt = Double.parseDouble(String.valueOf(empObj[i][5])) -
								(Double.parseDouble(String.valueOf(empObj[i][6])) +
								 Double.parseDouble(String.valueOf(empObj[i][7])) +
								 Double.parseDouble(String.valueOf(empObj[i][8])));
						
						empObj[i][9] = checkZero(String.valueOf(netAmt));
						
					}
			}
		}catch(Exception e){
			logger.error("Error while getting not saved employee for selected ledger code "+e);
		}
		return empObj;
	}
	public Object[][] getPensionCredit(String empCode,String ledgerCode,String year){
		
		String pensionQUery = " SELECT PENS_CREDIT_CODE,PENS_AMOUNT FROM HRMS_PENSION_CREDIT_"+year+" WHERE PENS_EMP_ID = "+empCode +" AND PENS_LEDGER_CODE = "+ledgerCode+" ORDER BY PENS_CREDIT_CODE";
		
		Object [][] pensObj = getSqlModel().getSingleResult(pensionQUery);
				
		return pensObj;
	}
	public Object [][] getLedgerCode(){
		
		String ledgerQuery =" SELECT NVL(MAX(LEDGER_CODE),0) + 1 FROM HRMS_PENSION_LEDGER ";
		
		Object [][] ledgerObject = getSqlModel().getSingleResult(ledgerQuery);
		
		return ledgerObject;
	}
	public boolean tableExist(String year){
		boolean result = false;
		Object[][] resultObj = null;
		try {
			String query = "SELECT * FROM HRMS_PENSION_"+ year;
					
			resultObj = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if(resultObj !=null){
			result =true;
		}
		return result;
	} //end of method tableExist()
	public String pensionStatus(String month,String year,String divCode){
		String check = "";
		String lockQuery = "SELECT LEDGER_STATUS FROM HRMS_PENSION_LEDGER WHERE  LEDGER_MONTH = "
							+ month
							+ " AND LEDGER_YEAR = "+year+" AND LEDGER_DIVISION = "+divCode;
		
		Object[][] result = getSqlModel().getSingleResult(lockQuery);

		if (result.length > 0) {
			check = String.valueOf(result[0][0]);
		}
		return check;
	}
	public boolean lockPension(String ledgerCode,String month,String year,String divCode){
		
		boolean result = false;
		
		String lockQuery = "UPDATE HRMS_PENSION_LEDGER SET LEDGER_STATUS = 'PENS_FINAL' WHERE LEDGER_MONTH = "+month+" AND LEDGER_YEAR = "+year
							+" AND LEDGER_CODE = "+ledgerCode+" AND LEDGER_DIVISION = "+divCode; 
		
		result = getSqlModel().singleExecute(lockQuery);		
		
		return result;
	}
	private int getMonthDays(String month, String year) {
		int monthDays = 0;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.SUNDAY);
			cal.setTime(Utility.getDate("01-" + month + "-" + year));
			monthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			logger.error("Exception in getMonthDays:" + e);
		}
		return monthDays;
	}
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}
	public String checkZero(String result) {
		if (result == null || result.equals("null") || result.equals("")) {
			return "0";
		} // end of if
		else {
			return result;
		} // end of else
	}
	public Object[][] intZero(Object[][] tempObj){
		try {
			for (int k = 0; k < tempObj.length; k++) {
				for (int j = 0; j < tempObj[0].length; j++) {
					tempObj[k][j] = "0";
				}
			}
		} catch (Exception e) {
			
		}
		return tempObj;
	}
	
	private String convertEmpListToString(Object[][] empList) {
		String empId = "";
		try {
			for (int i = 0; i < empList.length; i++) {
				empId += String.valueOf(empList[i][0]) + ",";// this ledger
				// code contains
				// the code for
				// Apr To Dec
			} // end of loop
		} catch (Exception e) {
			logger.error("exception in empList loop", e);
		} // end of catch
		// all the ledgerCodes for salary process from April to December are
		// substring together.
		empId = empId.substring(0, empId.length() - 1);

		return empId;
	} // end of getEmpList method

	/**
	 * @author Reeba_Joseph 
	 * @param penProcess
	 * @param request
	 */
	public void displayList(PensionProcess penProcess,
			HttpServletRequest request) {
		String query = " SELECT DECODE(LEDGER_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May', " +
		" 6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') PENSION_MONTH, " +
		" LEDGER_YEAR,DIV_NAME, LEDGER_STATUS, LEDGER_CODE,LEDGER_MONTH,LEDGER_DIVISION " +
		" FROM HRMS_PENSION_LEDGER " +
		" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =  HRMS_PENSION_LEDGER.LEDGER_DIVISION) " +
		" WHERE LEDGER_STATUS IN ('PENS_START','PENS_FINAL')" ;

		if(penProcess.getUserProfileDivision() !=null && penProcess.getUserProfileDivision().length()>0)
			query+= " AND DIV_ID IN ("+penProcess.getUserProfileDivision() +")"; 
		
		query+= " ORDER BY LEDGER_YEAR DESC, LEDGER_MONTH DESC  ";
		
		Object[][] dataObj = getSqlModel().getSingleResult(query);
		
		String[] pageIndex = Utility.doPaging(penProcess.getListMyPage(), dataObj.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));

		if (pageIndex[4].equals("1"))
			penProcess.setListMyPage("1");

		ArrayList<Object> list = new ArrayList<Object>();
		if (dataObj != null && dataObj.length > 0) {
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				PensionProcess bean = new PensionProcess();
				bean.setListMonth(checkNull(String.valueOf(dataObj[i][0])));
				bean.setListYear(checkNull(String.valueOf(dataObj[i][1])));
				bean.setListDivName(checkNull(String.valueOf(dataObj[i][2])));
				bean.setListPensionStatus(checkNull(String.valueOf(dataObj[i][3])));
				bean.setListPensionCode(String.valueOf(dataObj[i][4]));
				bean.setListMonthCode(String.valueOf(dataObj[i][5]));
				bean.setListDivId(String.valueOf(dataObj[i][6]));
				list.add(bean);
			}

			penProcess.setTotalRecords(String.valueOf(dataObj.length));
		} else {
			penProcess.setListLength("false");
		}

		if (list.size() > 0)
			penProcess.setListLength("true");
		else
			penProcess.setListLength("false");

		penProcess.setIteratorlist(list);
	}

	/**
	 * @author Reeba_Joseph
	 * @param listOfFilters
	 * @return
	 */
	public String setEmpFiletrs(String[] listOfFilters) {
		String filterQuery="";
		try {
			// if branch is selected
			if(!listOfFilters[0].equals("")) {
				filterQuery += " AND EMP_CENTER = " + listOfFilters[0];
			}
			// if department is selected
			if(!listOfFilters[1].equals("")) {
				filterQuery += " AND EMP_DEPT = " + listOfFilters[1];
			}
			// if paybill group is selected
			if(!listOfFilters[2].equals("")) {
				filterQuery += " AND EMP_PAYBILL = " + listOfFilters[2];
			}
			// if employee type is selected
			if(!listOfFilters[3].equals("")) {
				filterQuery += " AND EMP_TYPE = " + listOfFilters[3];
			}
			// if division is selected
			if(!listOfFilters[4].equals("")) {
				filterQuery += " AND EMP_DIV = " + listOfFilters[4];
			}
		} catch (Exception e) {
			logger.error("Exception in setSalaryLedgerFiletrs:" + e);
		}
		return filterQuery;
	}
	private HashMap convertObjectToHashMap(Object[][] objectData,String[] empLength) {
		HashMap dataMap = new HashMap();
		if (objectData == null) {

		} // end of if
		else if (objectData.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try{
			for (int i = 0; i < empLength.length; i++) {
				String empId = "";
				empId = String.valueOf(empLength[i]);
				for (int j = 0; j < objectData.length; j++) {
					if(String.valueOf(objectData[j][2]).equals(empLength[i])){
						v.add(objectData[j]);
					} //end of if
				} //end of totalDataObject loop
				Object[][]newData = new Object[v.size()][objectData[0].length];
				for (int k = 0; k < newData.length; k++) {
					newData[k] = (Object[])v.get(k);
				} //end of loop
				dataMap.put(empId, newData);
				v = new Vector();
			} //end of empList loop
			}catch(Exception e){
				logger.error("exception in convertAllowanceObjectToHashMap",e);
			} //end of catch
			////logger.info("dataMap.get(429)======"+dataMap.get("429"));
		} //end of else
		return dataMap;
	} //end of convertObjectToHashMap method
	
}
