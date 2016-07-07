/**
 * 
 */
package org.paradyne.model.payroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.payroll.AnnualIncrement;
import org.paradyne.bean.payroll.SalaryProcess;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author AA0554
 *
 */
public class AnnualIncrementModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AnnualIncrementModel.class);
	public void getIncrementList(AnnualIncrement bean,HttpServletRequest request){

		String listQuery = " SELECT DECODE(INCREMENT_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May',  6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') MONTH,"  
						+" INCREMENT_YEAR, DEPT_NAME, CENTER_NAME, DIV_NAME, DECODE(INCREMENT_STATUS,'U','UNLOCK','L','LOCK','UNLOCK'), "
						+" DEPT_ID,CENTER_ID,DIV_ID, INCREMENT_ID,INCREMENT_MONTH "
						+" FROM HRMS_INCREMENT_HDR  "
						+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID =  INCREMENT_DEPT) "
						+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID =  INCREMENT_BRANCH)  "
						+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID =  INCREMENT_DIVISION) ";
		
					if(bean.getUserProfileDivision() != null && bean.getUserProfileDivision().length() > 0) {
						listQuery += " AND DIV_ID IN(" + bean.getUserProfileDivision() + ") ";
					}
					
					listQuery	+=  " ORDER BY INCREMENT_YEAR DESC, INCREMENT_MONTH DESC ";
					
					Object[][] listObj =  getSqlModel().getSingleResult(listQuery);
					
					String[] pageIndex = Utility.doPaging(bean.getMyPage(),listObj.length,10);	
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
					
					if(listObj != null && listObj.length > 0) {
						for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
			
							 AnnualIncrement  bean1 = new AnnualIncrement();
			
							 	bean1.setListMonthName(checkNull_Space(String.valueOf(listObj[i][0])));
								bean1.setListYear(checkNull_Space(String.valueOf(listObj[i][1])));
								bean1.setListDeptName(checkNull_Space(String.valueOf(listObj[i][2])));
								bean1.setListBranchName(checkNull_Space(String.valueOf(listObj[i][3])));
								bean1.setListDivName(checkNull_Space(String.valueOf(listObj[i][4])));
								bean1.setListIncrementStatus(checkNull_Space(String.valueOf(listObj[i][5])));
								bean1.setListDeptId(checkNull_Space(String.valueOf(listObj[i][6])));
								bean1.setListBranchId(checkNull_Space(String.valueOf(listObj[i][7])));
								bean1.setListDivId(checkNull_Space(String.valueOf(listObj[i][8])));
								bean1.setListIncrementCode(checkNull_Space(String.valueOf(listObj[i][9])));
								bean1.setListMonthCode(checkNull_Space(String.valueOf(listObj[i][10])));
								list.add(bean1);
						}
						bean.setTotalRecords(String.valueOf(listObj.length));
					} else {
						bean.setListLength("false");
					}
					if(list.size()>0)
						bean.setListLength("true");
					else
						bean.setListLength("false");
					
					bean.setIteratorList(list);
	
	}
	public String processIncrement(AnnualIncrement bean){
		String msg="Eligible Employees not found for Increment process";
		String processDate="01-"+bean.getMonth()+"-"+bean.getYear();
		String eligibleEmpQuery="SELECT HRMS_EMP_CREDIT.EMP_ID,CREDIT_CODE,CREDIT_AMT,CREDIT_AMT AS NEW_AMT,NVL(EMP_ISHANDICAP,'N') AS NEW_AMT FROM HRMS_EMP_CREDIT "
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_CREDIT.EMP_ID)"
				+" LEFT JOIN HRMS_EMP_PERS ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_PERS.EMP_ID)"
				+" WHERE FLOOR(MONTHS_BETWEEN(TO_DATE('"+processDate+"','DD-MM-YYYY'),TO_DATE(TO_CHAR(EMP_INCR_DATE,'DD-MM-YYYY'),'DD-MM-YYYY'))/12)>0  "
				+" AND CREDIT_CODE IN (1,2,3,4,5,6) AND EMP_STATUS='S' AND EMP_DIV="+bean.getDivisionId();
		if(!bean.getBranchId().equals(""))
		eligibleEmpQuery+= " AND EMP_CENTER="+bean.getBranchId();
		if(!bean.getDepartmentId().equals(""))
			eligibleEmpQuery+= " AND EMP_DEPT="+bean.getDepartmentId();
		eligibleEmpQuery+=" ORDER BY HRMS_EMP_CREDIT.EMP_ID,CREDIT_CODE";
		Object [][]eligibleEmpObj=getSqlModel().getSingleResult(eligibleEmpQuery);
		if(eligibleEmpObj !=null && eligibleEmpObj.length>0){
		
		HashMap<String, Object[][]> eligibleEmpMap= convertObjectToHashMap(eligibleEmpObj);
		
		Object [][]increCreditObj=null;
		try {
			for (Iterator<String> iter = eligibleEmpMap.keySet().iterator(); iter
					.hasNext();) {
				String empId = iter.next();
				Object[][] empCreditObj = eligibleEmpMap.get(empId);
				double basicAmt = Double.parseDouble(String
						.valueOf(empCreditObj[0][2]));
				double gradePayAmt = Double.parseDouble(String
						.valueOf(empCreditObj[1][2]));
				double basicIncrement = (basicAmt + gradePayAmt) * 0.03;
				double payAmt = 0;
				double newClaAmt = Double.parseDouble(String
						.valueOf(empCreditObj[4][3]));
				double newTransportAmt = Double.parseDouble(String
						.valueOf(empCreditObj[5][3]));
				/*
				 * calculate new basic amt(Increment round to 10)
				 */
				if (!(Math.round((basicIncrement)) % 10 == 0))
					basicIncrement = Double
							.parseDouble(Utility.twoDecimals((Math
									.round(basicIncrement) + (10 - (Math
									.round(basicIncrement) % 10)))));
				empCreditObj[0][3] = basicIncrement + basicAmt; // new Basic
				payAmt = basicIncrement + basicAmt + gradePayAmt;
				empCreditObj[2][3] = Math.round(payAmt * 0.27); // new DA
				empCreditObj[3][3] = Math.round(payAmt * 0.30); // new HRA

				if (payAmt < 3000) {
					newClaAmt = 90;
				} else if (payAmt >= 3000 && payAmt < 4500) {
					newClaAmt = 125;
				} else if (payAmt >= 4500 && payAmt <= 5999) {
					newClaAmt = 200;
				} else
					newClaAmt = 300;
				empCreditObj[4][3] = newClaAmt; // new CLA amt

				if ((basicAmt + basicIncrement) < 6500) {
					if (String.valueOf(eligibleEmpObj[5][4]).equals("Y"))
						newTransportAmt = 1000;
					else {
						newTransportAmt = 200;
					}
				} else {
					if (String.valueOf(eligibleEmpObj[5][4]).equals("Y"))
						newTransportAmt = 1600;
					else
						newTransportAmt = 800;
				}

				empCreditObj[5][3] = newTransportAmt; // new TransportAmt

				if (increCreditObj != null && increCreditObj.length > 0) {
					increCreditObj = Utility.joinArrays(increCreditObj,
							empCreditObj, 1, 0);
				} else {
					increCreditObj = empCreditObj;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
					if(increCreditObj !=null || increCreditObj.length>0){
		Object maxIncrId[][]= getSqlModel().getSingleResult("SELECT NVL(MAX(INCREMENT_ID),0)+1 FROM HRMS_INCREMENT_HDR");
		String insertHDRQuery="INSERT INTO HRMS_INCREMENT_HDR ( INCREMENT_ID, INCREMENT_YEAR, INCREMENT_MONTH, INCREMENT_DIVISION,INCREMENT_BRANCH, INCREMENT_DEPT, INCREMENT_STATUS ) " 
						+ " VALUES ( ?, ?, ?, ?, ?, ?, ?)";
		Object[][] insertHDRData = new Object[1][7];
		insertHDRData[0][0] = String.valueOf(maxIncrId[0][0]); // INCREMENT_ID
		insertHDRData[0][1] = bean.getYear();
		insertHDRData[0][2] = bean.getMonth();
		insertHDRData[0][3] = bean.getDivisionId();
		insertHDRData[0][4] = bean.getBranchId();
		insertHDRData[0][5] = bean.getDepartmentId();
		insertHDRData[0][6] = "U";
		
		boolean result = getSqlModel().singleExecute(insertHDRQuery,insertHDRData);
		if(result){
			bean.setIncrementCode(String.valueOf(maxIncrId[0][0]));
			Object [][]finalCreditObj =new Object[increCreditObj.length][5];
			String creditInsertQuery="INSERT INTO HRMS_INCREMENT_DTL ( INCREMENT_ID, INCREMENT_EMP_ID, INCREMENT_CREDIT_CODE,"
							+" INCREMENT_CREDIT_OLD_AMT, INCREMENT_CREDIT_AMT  ) VALUES ( ?,?,?,?,?)";
			for (int i = 0; i < finalCreditObj.length; i++) {
				finalCreditObj[i][0] = bean.getIncrementCode();
				finalCreditObj[i][1] = String.valueOf(increCreditObj[i][0]);
				finalCreditObj[i][2] = String.valueOf(increCreditObj[i][1]);
				finalCreditObj[i][3] = String.valueOf(increCreditObj[i][2]);
				finalCreditObj[i][4] = String.valueOf(increCreditObj[i][3]);
			}
			result = getSqlModel().singleExecute(creditInsertQuery,finalCreditObj);
			String dtlEmpIdsQuery="SELECT DISTINCT INCREMENT_EMP_ID,INCREMENT_ID, NVL(INCREMENT_CREDIT_AMT,0),'01-"+bean.getMonth()+"-"+bean.getYear()+"' " 
								+" FROM HRMS_INCREMENT_DTL WHERE INCREMENT_CREDIT_CODE=1 AND INCREMENT_ID="+bean.getIncrementCode();
			
			Object [][]increDateInsert=getSqlModel().getSingleResult(dtlEmpIdsQuery);
			String increDateInsertQuery="INSERT INTO HRMS_ANNUAL_INCREMENT ( EMP_ID, PAY_ID, PAY_BASIC,PAY_INCR_DT )"
							+" VALUES ( ?, ?, ?,  TO_DATE(?, 'DD-MM-YYYY '))";
			result = getSqlModel().singleExecute(increDateInsertQuery,increDateInsert);
			if(result){
				msg="Increment processed successfully.";
			}
		}
					}
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	
}else{
	msg ="Employees not available for increment process";
}
		return msg;
	}
	private HashMap <String, Object[][]> convertObjectToHashMap(Object[][] totalDataObject) {
		HashMap<String, Object[][]> dataMap = new HashMap<String, Object[][]>();

		if (totalDataObject == null) {

		} // end of if
		else if (totalDataObject.length == 0) {

		} // end of else if
		else {
			Vector vector = new Vector();
			String empId = "";
			try{
			for (int i = 0; i < totalDataObject.length; i++) {
				if(i==0)
					empId = String.valueOf(totalDataObject[i][0]);
				
				if(empId.equals(String.valueOf(totalDataObject[i][0]))){
					vector.add(totalDataObject[i]);
				}else{
					Object[][]empData = new Object[vector.size()][totalDataObject[0].length];
					for (int k = 0; k < empData.length; k++) {
						empData[k] = (Object[])vector.get(k);
					} //end of loop
					dataMap.put(empId, empData);
					vector = new Vector();
					empId = String.valueOf(totalDataObject[i][0]);
					vector.add(totalDataObject[i]);
				}
			} //end of empList loop
			}catch(Exception e){
				logger.error("exception in convertAllowanceObjectToHashMap",e);
			} //end of catch
			////logger.info("dataMap.get(429)======"+dataMap.get("429"));
		} //end of else
	
		return dataMap;
	} // end of convertObjectToHashMap method
	
	public HashMap getSavedCreditMap(AnnualIncrement bean){
		String empQuery="SELECT INCREMENT_EMP_ID, EMP_TOKEN,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME, NVL(INCREMENT_CREDIT_AMT,0),INCREMENT_CREDIT_CODE"
					+" FROM HRMS_INCREMENT_DTL "
					+" LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=INCREMENT_EMP_ID) WHERE INCREMENT_ID="+bean.getIncrementCode()
					+" ORDER BY EMP_TOKEN,INCREMENT_CREDIT_CODE";
		Object [][]empObj=getSqlModel().getSingleResult(empQuery);
		return convertObjectToHashMap(empObj);
	}
	public void showIncrementDetails(AnnualIncrement bean,HttpServletRequest request){
		String empQuery="SELECT DISTINCT INCREMENT_EMP_ID, EMP_TOKEN,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME, "
					+" INCREMENT_CREDIT_OLD_AMT FROM HRMS_INCREMENT_DTL "
					+" LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=INCREMENT_EMP_ID) WHERE INCREMENT_ID="+bean.getIncrementCode()
					+" AND INCREMENT_CREDIT_CODE=1 ORDER BY EMP_TOKEN";
		Object [][]incrementEmpId=getSqlModel().getSingleResult(empQuery);
		Object [][]empObj = getEmployeeDataToView(request, bean, incrementEmpId);
		
		request.setAttribute("rows", empObj);
		
		//return convertObjectToHashMap(empObj);
	}
	public Object [][] getEmployeeDataToView(HttpServletRequest request,AnnualIncrement bean,Object [][] incrementEmpId){
		Object[][] allEmpRow = null;
		try {
			String recordPerPage = "20";
			
			Object [][]creditObj=getSqlModel().getSingleResult("SELECT DISTINCT INCREMENT_CREDIT_CODE,CREDIT_ABBR FROM HRMS_INCREMENT_DTL "
					+" INNER JOIN HRMS_CREDIT_HEAD ON (CREDIT_CODE=INCREMENT_CREDIT_CODE)WHERE INCREMENT_ID="+bean.getIncrementCode()+" ORDER BY INCREMENT_CREDIT_CODE");
			
			doPaging(bean, incrementEmpId.length, incrementEmpId, request, "false", Integer.parseInt(recordPerPage));
			
			int From_TOT = Integer.parseInt(bean.getFromTotRec());
			int To_TOT = Integer.parseInt(bean.getToTotRec());
			
			HashMap<String,Object[][]> empSavedCreditMap = getSavedCreditMap(bean); 
			
			allEmpRow = new Object[To_TOT - From_TOT][4+creditObj.length+1];
			
			int count = 0;
			for (int i = From_TOT; i < To_TOT; i++) {
				String [] tempEmp = new String [4+creditObj.length+1];
				tempEmp[0] = String.valueOf(incrementEmpId[i][0]);//empid
				tempEmp[1] = String.valueOf(incrementEmpId[i][1]);//token
				tempEmp[2] = String.valueOf(incrementEmpId[i][2]);//name
				tempEmp[3] = String.valueOf(incrementEmpId[i][3]);//OLD BASIC
				
				Object [][] tempCredit = empSavedCreditMap.get(String.valueOf(incrementEmpId[i][0]));
				int colCount=4;
				double creditTotal=0;
				for (int j = 0; j < tempCredit.length; j++) {
					tempEmp[colCount]=String.valueOf(tempCredit[j][3])	;
					creditTotal+= Double.parseDouble(String.valueOf(tempCredit[j][3]));
					colCount++;
				}
				tempEmp[colCount]=String.valueOf(creditTotal);
				allEmpRow[count]=tempEmp;
				count++;
			}
			request.setAttribute("index", count);	
			request.setAttribute("creditLength", creditObj);
			ArrayList<AnnualIncrement> creditNames = new ArrayList<AnnualIncrement>();
			for (int i = 0; i < creditObj.length; i++) {
				AnnualIncrement creditName = new AnnualIncrement();
				creditName.setCreditCode(String.valueOf(creditObj[i][0]));
				creditName.setCreditName(String.valueOf(creditObj[i][1]));
				creditNames.add(creditName);
			}
			bean.setCreditHeader(creditNames);
		}catch(Exception e) {
			logger.error("Exception in getemployeeDataToView:" + e);
			e.printStackTrace();
			return new Object[1][1];
		}
		return allEmpRow;
	}
	public static String checkNull_Space(String result){
		if(result ==null ||result.equals("null") || result.equals("")){
			return "";
		}
		else{
			return result;
		}
	}
	public void doPaging(AnnualIncrement bean, int empLength, Object[][] empObj, 
			HttpServletRequest request, String empSearch,int totalRec)
	{
		try
		{
			//totalRec -: No. of records per page
			//fromTotRec -: Starting No. of record to show on a current page
			//toTotRec -: Last No. of record to show on a current page
			//pageNo -: Current page to show
			//totalPage -: Total No. of Pages
			
			
			double row1 = 0.0;
			int pageNo = 1;
			int fromTotRec = 0;
			int toTotRec = totalRec;
			int searchCount = 0;
			int totalPage = 0;
			String status="false";

			try {
				row1 = (double)empLength / totalRec;
				java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(row1);
				totalPage = Integer.parseInt(bigDecRow1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			if(String.valueOf(bean.getHdPage()).equals("null")||String.valueOf(bean.getHdPage()).equals(""))
			{
				pageNo = 1;
				fromTotRec = 0;
				toTotRec = totalRec;

				if(toTotRec > empLength)
				{
					toTotRec = empLength;
				}
				bean.setHdPage("1");
			}
			else
			{
				if(empSearch.equals("true"))
				{
					for (int j = 0; j < empObj.length; j++)
					{
						//if(String.valueOf(empObj[j][0]).equals(bean.getEmpSearchId()))
						if(false)
						{
							status = "true";
							break;
						}
					}
					if(status.equals("true"))
					{
						for (int i = 0; i < empLength; i++)
						{
							//if(!String.valueOf(empObj[i][0]).equals(bean.getEmpSearchId()))
							if(false)
							{
								searchCount = i;
							}
							else
							{
								searchCount = searchCount + 2;
								break;
							}
						}
						if(searchCount == 0)
						{
							searchCount = 1;
						}
						try {
							row1= (double)searchCount / totalRec;				   
							java.math.BigDecimal value2 = new java.math.BigDecimal(row1);	
							int pageSearch =Integer.parseInt(value2.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
							pageNo = Integer.parseInt(String.valueOf(pageSearch));
						} catch (RuntimeException e) {
							logger.error(e.getMessage());
						}
					}
					else
					{
						pageNo = Integer.parseInt(bean.getHdPage());
					}
					if(pageNo == 1)
				    {
						 fromTotRec = 0;
						 toTotRec = totalRec;
				    }
				    else
				    {
				    	toTotRec = toTotRec * pageNo;
				    	fromTotRec = (toTotRec - totalRec);
				    }
				    if(toTotRec > empLength)
				    {
				    	toTotRec = empLength;
				    }
				}
				else
				{
					pageNo = Integer.parseInt(bean.getHdPage());
						  
					if(pageNo == 1)
					{
						fromTotRec = 0;
						toTotRec = totalRec;
					}
					else
					{
						toTotRec = toTotRec * pageNo;
						fromTotRec = toTotRec - totalRec;
					}
					if(toTotRec > empLength)
					{
						toTotRec = empLength;
					}
				}				
			}
			
			bean.setFromTotRec(String.valueOf(fromTotRec));
			bean.setToTotRec(String.valueOf(toTotRec));
			
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("PageNo", pageNo);
			request.setAttribute("To_TOT", toTotRec);
			request.setAttribute("From_TOT", fromTotRec);	
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
	} // end of method doPaging()
	public boolean saveIncrementRecords(AnnualIncrement bean,HttpServletRequest request){
		
		Object [][]creditHeader=getSqlModel().getSingleResult("SELECT DISTINCT INCREMENT_CREDIT_CODE,CREDIT_ABBR FROM HRMS_INCREMENT_DTL "
				+" INNER JOIN HRMS_CREDIT_HEAD ON (CREDIT_CODE=INCREMENT_CREDIT_CODE)WHERE INCREMENT_ID="+bean.getIncrementCode()+" ORDER BY INCREMENT_CREDIT_CODE");
		String[] emp_id			=	request.getParameterValues("emp_id");
		boolean result=false;
		Object [][] updateObj=new Object[emp_id.length*creditHeader.length][4]; 
		 int empCount=0;	
		 for(int i=0;i<emp_id.length;i++)
			{
			 String creditAmt[]=request.getParameterValues(String.valueOf(i));
			 for (int j = 0; j < creditAmt.length; j++) {
				 updateObj[empCount][0] =creditAmt[j]; 
				 updateObj[empCount][1] = bean.getIncrementCode();
				 updateObj[empCount][2] =emp_id[i];
				 updateObj[empCount++][3] = String.valueOf(creditHeader[j][0]);  // CREDIT CODE
				 
				 //rows[empCount++][4] = oldBasic[i];
			 }
			 
			}
		 try {
			/*for (int i = 0; i < updateObj.length; i++) {
				for (int k = 0; k < updateObj[0].length; k++) {
					logger.info("rows[" + i + "][" + k + "]=="+ updateObj[i][k]);
				}
			}*/
			String updateQuery ="UPDATE HRMS_INCREMENT_DTL SET INCREMENT_CREDIT_AMT=? WHERE INCREMENT_ID=? " 
					+ " AND INCREMENT_EMP_ID=? AND INCREMENT_CREDIT_CODE=?";
			
			 result=getSqlModel().singleExecute(updateQuery,updateObj);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public boolean deleteIncrement(AnnualIncrement bean){
		boolean result=false;
		result =getSqlModel().singleExecute("DELETE FROM HRMS_ANNUAL_INCREMENT WHERE PAY_ID="+bean.getIncrementCode());
		if(result){
			result =getSqlModel().singleExecute("DELETE FROM HRMS_INCREMENT_DTL WHERE INCREMENT_ID="+bean.getIncrementCode());
		}
		if(result){
			result =getSqlModel().singleExecute("DELETE FROM HRMS_INCREMENT_HDR WHERE INCREMENT_ID="+bean.getIncrementCode());
		}
		return result;
	}
	
	public boolean lockIncrement(AnnualIncrement bean){
		boolean result=false;
		result = getSqlModel().singleExecute("UPDATE HRMS_INCREMENT_HDR SET INCREMENT_STATUS='L' WHERE INCREMENT_ID="+bean.getIncrementCode());
		if(result){
			/*
			 * Update employee credits in HRMS_EMP_CREDIT
			 * 
			 */
			String empIncrementQuery="SELECT INCREMENT_CREDIT_AMT,INCREMENT_EMP_ID, INCREMENT_CREDIT_CODE FROM HRMS_INCREMENT_DTL WHERE INCREMENT_ID="+bean.getIncrementCode();
			
			Object[][]empCreditObj=getSqlModel().getSingleResult(empIncrementQuery);
			
			if(empCreditObj !=null && empCreditObj.length>0){
				String empCreditUpdate="UPDATE HRMS_EMP_CREDIT SET CREDIT_AMT=? WHERE  EMP_ID=? AND CREDIT_CODE=? ";
				result = getSqlModel().singleExecute(empCreditUpdate,empCreditObj);
			}
			if(result){
				Object[][] increDate=getSqlModel().getSingleResult("SELECT TO_CHAR(PAY_INCR_DT,'DD-MM-YYYY'),EMP_ID FROM HRMS_ANNUAL_INCREMENT WHERE PAY_ID="+bean.getIncrementCode());
				
				String updateEmpIncrementDate="UPDATE HRMS_EMP_OFFC SET EMP_INCR_DATE=TO_DATE(?,'DD-MM-YYYY') WHERE EMP_ID=?";
				
				result =getSqlModel().singleExecute(updateEmpIncrementDate,increDate);
				
			}
			
		}
		return result;
	}
}
