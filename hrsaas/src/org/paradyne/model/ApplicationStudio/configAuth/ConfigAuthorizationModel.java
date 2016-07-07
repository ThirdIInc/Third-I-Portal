package org.paradyne.model.ApplicationStudio.configAuth;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element; 
import org.paradyne.bean.ApplicationStudio.configAuth.ConfigAuthorization;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.XMLReaderWriter;

 

public class ConfigAuthorizationModel extends ModelBase {
	
	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ConfigAuthorizationModel.class);
	public boolean getRecord(ConfigAuthorization bean, String fileName,HttpServletRequest request) {

		File file = new File(fileName);
		Document document; 
		try {
			document = new XMLReaderWriter().parse(file);
			List nodes = document.selectNodes("//CONFIG_AUTHORIZATION/MODULE"); 
			ArrayList moduleList = new ArrayList();

			for (Iterator<Element> it = nodes.iterator(); it.hasNext();) {
				Element data = (Element) it.next();
				String moduleId = String.valueOf(data.attributeValue("moduleId"));
				String moduleName = String.valueOf(data.attributeValue("moduleName"));
				ConfigAuthorization bean1 = new ConfigAuthorization();
				bean1.setModuleId(moduleId);
				bean1.setModuleName(moduleName);
				moduleList.add(bean1); 
			}
			bean.setModuleList(moduleList);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void addEmployee(ConfigAuthorization bean,HttpServletRequest request)
	{
		String [] moduleId = request.getParameterValues("moduleId");
		String [] moduleName = request.getParameterValues("moduleName");
		String [] authType = request.getParameterValues("authType");
		ArrayList moduleList = new ArrayList();
		if(moduleId!=null && moduleId.length >0)
		{
			for (int i = 0; i < authType.length; i++) { 
				ConfigAuthorization bean1 = new ConfigAuthorization();
				bean1.setModuleId(moduleId[i]);
				bean1.setModuleName(moduleName[i]);
				bean1.setAuthType(authType[i]);  
				bean1.setHiddenAuthType(authType[i]);  
				// for display the employee 				
				String []hidEmployee = request.getParameterValues("hidEmployee"+i);
				
				ArrayList employeeList = new ArrayList();
				employeeList.clear();
				if(hidEmployee!=null && hidEmployee.length>0)
				{
					int length = 0;
					if (i == Integer.parseInt(bean.getRowNum())) {
						length = hidEmployee.length + 1;
					} else {
						length = hidEmployee.length;
					}

					for (int j = 0; j < length; j++) {

						String dispEmpName = request.getParameter("dispEmpName_" + i + "_" + j);
						String dispEmpId = request.getParameter("dispEmpId_"+ i + "_" + j);

						ConfigAuthorization bean2 = new ConfigAuthorization();
						if (j < hidEmployee.length) {
							if (dispEmpName != null && dispEmpName.length() > 0) {
								request.setAttribute("dispEmpName_" + i + "_"+ j, dispEmpName);
								request.setAttribute("dispEmpId_" + i + "_" + j, dispEmpId); 
							}
						} else {
							if (request.getParameter("empName" + i) != null && request.getParameter("empName" + i).length() > 0) 
							   {
								request.setAttribute("dispEmpName_" + i + "_"+ j, request.getParameter("empName" + i));
								request.setAttribute("dispEmpId_" + i + "_" + j, request.getParameter("empId" + i));
								}
						}
						bean1.setEmployeeList(employeeList);
						employeeList.add(bean2);

					}
				}else{
					 if(request.getParameter("empName"+i)!=null && request.getParameter("empName"+i).length()>0)
					 {
						 ConfigAuthorization bean2= new ConfigAuthorization(); 
						 request.setAttribute("dispEmpName_"+i+"_"+0,request.getParameter("empName"+i));
						 request.setAttribute("dispEmpId_"+i+"_"+0,request.getParameter("empId"+i)); 
						 employeeList.add(bean2);  
						 bean1.setEmployeeList(employeeList);
					 }
				} 
				// end of employee list   
				moduleList.add(bean1);  
			} 
		}
		bean.setModuleList(moduleList); 
	}

	
	public void removeEmployee(ConfigAuthorization bean,HttpServletRequest request)
	{
		String [] moduleId = request.getParameterValues("moduleId");
		String [] moduleName = request.getParameterValues("moduleName");
		String [] authType = request.getParameterValues("authType");
		ArrayList moduleList = new ArrayList();
		if(moduleId!=null && moduleId.length >0)
		{
			for (int i = 0; i < authType.length; i++) { 
				ConfigAuthorization bean1 = new ConfigAuthorization();
				bean1.setModuleId(moduleId[i]);
				bean1.setModuleName(moduleName[i]);
				bean1.setAuthType(authType[i]);  
				bean1.setHiddenAuthType(authType[i]); 
				// for display the employee 				
				String []hidEmployee = request.getParameterValues("hidEmployee"+i); 
				ArrayList employeeList = new ArrayList();
				employeeList.clear();
				
				if(hidEmployee!=null && hidEmployee.length>0)
				{  boolean flag =false;
					for (int j = 0; j < hidEmployee.length; j++) {
						
						String dispEmpName = request.getParameter("dispEmpName_" + i + "_" + j);
						String dispEmpId = request.getParameter("dispEmpId_"+ i + "_" + j); 
						ConfigAuthorization bean2 = new ConfigAuthorization(); 
						if (!(i == Integer.parseInt(bean.getRowNum()) && j == Integer.parseInt(bean.getCellNum())))
						{ 
							if(flag==true)
							{
								 if (dispEmpName != null && dispEmpName.length() > 0) { 
										request.setAttribute("dispEmpName_" + i + "_"+( j-1), dispEmpName);
										request.setAttribute("dispEmpId_" + i + "_" + (j-1), dispEmpId); 
								 } 
							}else{
								if (dispEmpName != null && dispEmpName.length() > 0) { 
									request.setAttribute("dispEmpName_" + i + "_"+ j, dispEmpName);
									request.setAttribute("dispEmpId_" + i + "_" + j, dispEmpId); 
							    } 
							}
							bean1.setEmployeeList(employeeList);
							employeeList.add(bean2); 
						} else{ 
							flag =true;
						}
						
						
					}
				}
				// end of employee list   
				moduleList.add(bean1);  
			} 
		}
		bean.setModuleList(moduleList); 
	}

	
	
	public boolean save(ConfigAuthorization bean,HttpServletRequest request)
	{
		boolean resultFlag =false;
		String [] moduleId = request.getParameterValues("moduleId");
		String [] moduleName = request.getParameterValues("moduleName");
		String [] authType = request.getParameterValues("authType");
		ArrayList moduleList = new ArrayList();
		String sqlDel = "DELETE FROM HRMS_AUTHORIZATION_SETTINGS";
		getSqlModel().singleExecute(sqlDel);
		
		if(moduleId!=null && moduleId.length >0)
		{
			for (int i = 0; i < authType.length; i++) { 
				ConfigAuthorization bean1 = new ConfigAuthorization();
				bean1.setModuleId(moduleId[i]);
				bean1.setModuleName(moduleName[i]);
				bean1.setAuthType(authType[i]);  
				bean1.setHiddenAuthType(authType[i]);  
				// for display the employee 				
				String []hidEmployee = request.getParameterValues("hidEmployee"+i); 
				ArrayList employeeList = new ArrayList();
				employeeList.clear();
				String finalEmp ="";
				System.out.println("authType[i]=========== "+authType[i]);
				if(!authType[i].equals("M")){
					hidEmployee=null;
				}
				 if(hidEmployee!=null && hidEmployee.length>0)
				  {   
					boolean flag =false; 
					for (int j = 0; j < hidEmployee.length; j++) {
						
						String dispEmpName = request.getParameter("dispEmpName_" + i + "_" + j);
						String dispEmpId = request.getParameter("dispEmpId_"+ i + "_" + j); 
						ConfigAuthorization bean2 = new ConfigAuthorization();  
							if (dispEmpName != null && dispEmpName.length() > 0) { 
								request.setAttribute("dispEmpName_" + i + "_"+ j, dispEmpName);
								request.setAttribute("dispEmpId_" + i + "_" + j, dispEmpId); 
								
								if(j < hidEmployee.length){
									finalEmp+=dispEmpId+",";
								}else{
									finalEmp+=dispEmpId;
								} 
						    }  
								employeeList.add(bean2);
							 
						bean1.setEmployeeList(employeeList); 
					}
				  }  
				 
				// end of employee list    
				moduleList.add(bean1);  
				String chkString="";
				if(!finalEmp.equals("")&& finalEmp.length()>0){
					chkString=finalEmp.substring(finalEmp.length()-1, finalEmp.length());
				} 
				System.out.println("chkString========= "+chkString);
				if(chkString.equals(",")){
					finalEmp=finalEmp.substring(0,finalEmp.length()-1);
				} 
				if(!authType[i].equals("M")){
					finalEmp="";
				}
				String sqlQuery ="INSERT INTO HRMS_AUTHORIZATION_SETTINGS (AUTH_MODULE_ID, AUTH_TYPE, AUTH_EMP_ID) "
					+" VALUES ("+moduleId[i]+" ,'"+authType[i]+"' ,'"+finalEmp+"' )";
				 resultFlag = getSqlModel().singleExecute(sqlQuery);
				 
			} 
		}
		bean.setModuleList(moduleList); 
		return resultFlag ;
	} 
	
	public boolean checkSaveData()
	{
		String sql = "SELECT AUTH_MODULE_ID, AUTH_TYPE, AUTH_EMP_ID FROM HRMS_AUTHORIZATION_SETTINGS ";
		Object[][] data = getSqlModel().getSingleResult(sql);

		if (data != null && data.length > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	public boolean getSaveRecord(ConfigAuthorization bean, String fileName,HttpServletRequest request) {  

		File file = new File(fileName);
		Document document; 
		try {
			document = new XMLReaderWriter().parse(file);
			List nodes = document.selectNodes("//CONFIG_AUTHORIZATION/MODULE");
			Object [][]xmlObj =new Object[nodes.size()][2];
		 
			int m =0;
			for (Iterator<Element> it = nodes.iterator(); it.hasNext();) {
				Element data = (Element) it.next();
				String moduleId = String.valueOf(data.attributeValue("moduleId"));
				String moduleName = String.valueOf(data.attributeValue("moduleName")); 
				xmlObj [m][0]=moduleId;
				xmlObj [m][1]=moduleName; 
				m++;
			}
			 
			 String sqlQuery =" SELECT AUTH_MODULE_ID, AUTH_TYPE, AUTH_EMP_ID FROM HRMS_AUTHORIZATION_SETTINGS ";
			 Object [][] data = getSqlModel().getSingleResult(sqlQuery);
			 
			 Object [][] finalObj = new Object[xmlObj.length][4];
			 
			for (int i = 0;i < xmlObj.length; i++) { 
				
				for (int j = 0;j < data.length; j++) {
					finalObj[i][0]=xmlObj[i][0];
					finalObj[i][1]=xmlObj[i][1];
					finalObj[i][2]="N";
					finalObj[i][3]=""; 
					if(String.valueOf(xmlObj[i][0]).equals(String.valueOf(data[j][0]))){
						finalObj[i][2]=String.valueOf(data[j][1]);
						finalObj[i][3]=String.valueOf(data[j][2]);
						break;
					}
				} 
			} 
			
			 
			ArrayList moduleList = new ArrayList();
			if(finalObj!=null && finalObj.length >0)
			{
				for (int i = 0; i < finalObj.length; i++) { 
					ConfigAuthorization bean1 = new ConfigAuthorization();
					bean1.setModuleId(String.valueOf(finalObj[i][0]));
					bean1.setModuleName(String.valueOf(finalObj[i][1]));
					bean1.setAuthType(String.valueOf(finalObj[i][2]));
					bean1.setHiddenAuthType(String.valueOf(finalObj[i][2]));
					
					// for display the employee 				
				 
					ArrayList employeeList = new ArrayList();
					employeeList.clear();
					if(String.valueOf(finalObj[i][3])!=null && String.valueOf(finalObj[i][3]).length()>0)
					{
						String empSql ="  SELECT EMP_ID , EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC "
	   						+" WHERE EMP_ID IN("+String.valueOf(finalObj[i][3])+") "; 
						Object [][] empData = getSqlModel().getSingleResult(empSql);
						
						for (int j = 0; j < empData.length; j++) {  
							
							ConfigAuthorization bean2 = new ConfigAuthorization(); 
							if (empData[j][0] != null && String.valueOf(empData[j][0]).length() > 0) {  
								request.setAttribute("dispEmpName_" + i + "_"+ j, String.valueOf(empData[j][1])); 
								request.setAttribute("dispEmpId_" + i + "_" + j, String.valueOf(empData[j][0])); 
							} 
							bean1.setEmployeeList(employeeList);
							employeeList.add(bean2); 
						}
					} 
					// end of employee list   
					moduleList.add(bean1);  
				} 
			}
			bean.setModuleList(moduleList);  

		} catch (Exception e) {
			return false;
		} 
		return true;
	}

	public boolean addConfigAuth(ConfigAuthorization bean) {
boolean result = false;
		
		try {
			
			Object addObj[][] = new Object[1][7];
			//addObj[0][0] = bean.getHiddenBankId();
			addObj[0][0] = bean.getConfigAuth();
			addObj[0][1] = bean.getDivCode();
			addObj[0][2] = bean.getPayBillId();
			addObj[0][3] = bean.getManagerCode();
			addObj[0][4] = bean.getDebitCode();
			addObj[0][5] = bean.getCreditCode();
			addObj[0][6] = bean.getCenterCode();
			
			
			System.out.println("6=" + addObj[0][6]);
			
			
			String insertQuery = "INSERT INTO HRMS_SAL_UPLOAD_AUTH"
				+ "(SAL_AUTH_CODE, SAL_AUTH_TYPE, DIV_CODE, PAYBILL_CODE, MANAGER_CODE, DEBIT_CODE,CREDIT_CODE,CENTER_CODE)"
				+ " VALUES((SELECT NVL(MAX(SAL_AUTH_CODE),0)+1 FROM HRMS_SAL_UPLOAD_AUTH),?,?,?,?,?,?,?)";
			
			result = getSqlModel().singleExecute(insertQuery, addObj);
			
			
			/*if (result) {
				String autoCodeQuery = " SELECT NVL((BANK_ID),0) FROM HRMS_BANK ";
				Object[][] data = getSqlModel().getSingleResult(
						autoCodeQuery);
				if (data != null && data.length > 0) {
					bean.setHiddenBankId(String.valueOf(data[0][0]));
					
				}
			}*/
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	public boolean updateRecords(ConfigAuthorization bean,
			HttpServletRequest request) {
		boolean result = false;
		try {

			System.out.println("IN UPDATE ");

			Object updateObj[][] = new Object[1][7];

			updateObj[0][0] = bean.getConfigAuth();
			updateObj[0][1] = bean.getDivCode();
			updateObj[0][2] = bean.getPayBillId();
			updateObj[0][3] = bean.getManagerCode();
			updateObj[0][4] = bean.getDebitCode();
			updateObj[0][5] = bean.getCreditCode();
			updateObj[0][6] = bean.getCenterCode();
		
			String updateQuery = "UPDATE HRMS_SAL_UPLOAD_AUTH SET "
				+ " SAL_AUTH_TYPE = ?, DIV_CODE = ?, PAYBILL_CODE = ?, MANAGER_CODE = ?, DEBIT_CODE = ?  , CREDIT_CODE=? , CENTER_CODE=? "
				+ " WHERE SAL_AUTH_CODE = "
				+ bean.getParacode();

			result = getSqlModel().singleExecute(updateQuery, updateObj);


		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public void getMiscSalaryUpoadDetails(ConfigAuthorization bean) {
		try {
			

			String miscSalaryUpoaQuery = "SELECT SAL_AUTH_CODE, DECODE(SAL_AUTH_TYPE,'N','No Authorization','M','Manager Authorization'), DIV_CODE,DIV_NAME, PAYBILL_CODE,PAYBILL_GROUP, "
								+ " MANAGER_CODE,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
								+ " HRMS_SAL_UPLOAD_AUTH.DEBIT_CODE,HRMS_SAL_UPLOAD_AUTH.CREDIT_CODE,CENTER_CODE,CENTER_NAME"
								+ " FROM HRMS_SAL_UPLOAD_AUTH"
								+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_UPLOAD_AUTH.MANAGER_CODE)"
								+ " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_SAL_UPLOAD_AUTH.DIV_CODE)"
								+ " LEFT JOIN HRMS_PAYBILL ON (HRMS_PAYBILL.PAYBILL_ID = HRMS_SAL_UPLOAD_AUTH.PAYBILL_CODE)"
								+ "LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_SAL_UPLOAD_AUTH.CENTER_CODE)"
								+ " ORDER BY SAL_AUTH_CODE DESC ";
			Object[][] repData = getSqlModel().getSingleResult(miscSalaryUpoaQuery);
			if (repData != null && repData.length > 0) {
				bean.setMiscSalaryUploadListPage(true);
				ArrayList<Object> List = new ArrayList<Object>();
				int k = 0;
				for (int i = 0; i < repData.length; i++) {
					ConfigAuthorization bean1 = new ConfigAuthorization();
					bean1.setMiscSalaryUploadHiddenId(checkNull((String
							.valueOf(repData[k][0]))));
					bean1
							.setMiscSalConfigAuth(checkNull((String
									.valueOf(repData[k][1]))));
					bean1.setMiscSalDivId(checkNull((String
							.valueOf(repData[k][2]))));
					bean1.setMiscSalDivName(checkNull((String
							.valueOf(repData[k][3]))));
					bean1.setMiscSalPayBillId(checkNull((String
							.valueOf(repData[k][4]))));
					bean1.setMiscSalPayBillName(checkNull((String
							.valueOf(repData[k][5]))));
					bean1.setManagerEmpAuthId(checkNull((String
							.valueOf(repData[k][6]))));
										bean1.setManagerEmpName(checkNull((String.valueOf(repData[k][7]))));
					
					
					bean1.setMiscSalComponentCode(checkNull(String.valueOf(repData[k][8])));
					bean1.setMiscSalComponentHead(checkNull(getActualDebitName(String.valueOf(repData[k][8]))));
					bean1.setMiscSalCreditCode(checkNull((String.valueOf(repData[k][9]))));
					bean1.setMiscSalCreditHead(checkNull(getActualCreditName(String.valueOf(repData[k][9]))));
					
					
					bean1
					.setMiscSalCenterCode(checkNull((String.valueOf(repData[k][10]))));
					bean1
					.setMiscSalCenterName(checkNull((String.valueOf(repData[k][11]))));

					List.add(bean1);
					k++;
				}

				bean.setMiscSalaryUploadList(List);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * to check null value
	 * 
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void getMiscSalUploadRecord(ConfigAuthorization bean, HttpServletRequest request) {
		try {
			String editQuery = " SELECT SAL_AUTH_CODE, SAL_AUTH_TYPE, DIV_CODE,DIV_NAME, PAYBILL_CODE," +
					" PAYBILL_GROUP,  MANAGER_CODE,NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '), " +
					" HRMS_SAL_UPLOAD_AUTH.DEBIT_CODE ,HRMS_SAL_UPLOAD_AUTH.CREDIT_CODE ,CENTER_CODE,CENTER_NAME  FROM HRMS_SAL_UPLOAD_AUTH " +
					" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_UPLOAD_AUTH.MANAGER_CODE) " +
					" LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_SAL_UPLOAD_AUTH.DIV_CODE) " +
					" LEFT JOIN HRMS_PAYBILL ON (HRMS_PAYBILL.PAYBILL_ID = HRMS_SAL_UPLOAD_AUTH.PAYBILL_CODE)  " +
					" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_SAL_UPLOAD_AUTH.CENTER_CODE) "
					+ " WHERE  SAL_AUTH_CODE = "
					+ bean.getParacode();
			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {

				bean.setMiscSalaryUploadHiddenId(checkNull(String.valueOf(editObj[0][0]).trim()));
				bean.setConfigAuth(checkNull(String.valueOf(editObj[0][1]).trim()));
				bean.setDivCode(checkNull(String.valueOf(editObj[0][2]).trim()));
				bean.setDivName(checkNull(String.valueOf(editObj[0][3]).trim()));
				bean.setPayBillId(checkNull(String.valueOf(editObj[0][4])));
				bean.setPayBillName(checkNull(String.valueOf(editObj[0][5])));
				bean.setManagerCode(checkNull(String.valueOf(editObj[0][6]).trim()));
				bean.setManagerToken(checkNull(String.valueOf(editObj[0][7]).trim()));
				bean.setManagerName(checkNull(String.valueOf(editObj[0][8]).trim()));
				bean.setDebitCode(checkNull(String.valueOf(editObj[0][9])));
				bean.setDebitName(checkNull(getActualDebitName(String.valueOf(editObj[0][9]))));
				bean.setCreditCode(checkNull((String.valueOf(editObj[0][10]))));
				bean.setCreditName(checkNull(getActualCreditName(String.valueOf(editObj[0][10]))));
				bean.setCenterCode(checkNull((String.valueOf(editObj[0][11]))));
				bean.setCenterName(checkNull((String.valueOf(editObj[0][12]))));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	/**Method : getActualDebitName.
	 * Purpose : this method is used to get Debit Head names based on supplied Config Auth ID
	 * @param payBillID : payBillID
	 * @return String
	 */
	private String getActualDebitName(final String debitID) {
		String debitName = "";
		try {
			if (debitID == null || debitID.equals("null")) {
				debitName = "";
			} else {
				String debitNameQuery = " SELECT HRMS_DEBIT_HEAD.DEBIT_NAME FROM HRMS_DEBIT_HEAD WHERE HRMS_DEBIT_HEAD.DEBIT_CODE IN  ("+ debitID + ")";
				Object[][] debitNameObj = getSqlModel().getSingleResult(debitNameQuery);
				if (debitNameObj != null && debitNameObj.length > 0) {
					for (int i = 0; i < debitNameObj.length; i++) {
						debitName += String.valueOf(debitNameObj[i][0])+",";
					}
				}
				debitName = debitName.substring(0, debitName.length()-1);
				System.out.println("debitName==="+debitName);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return debitName;
	}
	
	
	/**Method : getActualDebitName.
	 * Purpose : this method is used to get Debit Head names based on supplied Config Auth ID
	 * @param payBillID : payBillID
	 * @return String
	 */
	private String getActualCreditName(final String creditID) {
		String creditName = "";
		try {
			if (creditID == null || creditID.equals("null")) {
				creditName = "";
			} else {
				String creditNameQuery = " SELECT HRMS_CREDIT_HEAD.CREDIT_NAME FROM HRMS_CREDIT_HEAD WHERE HRMS_CREDIT_HEAD.CREDIT_CODE IN  ("+ creditID + ")";
				Object[][] creditNameObj = getSqlModel().getSingleResult(creditNameQuery);
				if (creditNameObj != null && creditNameObj.length > 0) {
					for (int i = 0; i < creditNameObj.length; i++) {
						creditName += String.valueOf(creditNameObj[i][0])+",";
					}
				}
				creditName = creditName.substring(0, creditName.length()-1);
				System.out.println("creditName==="+creditName);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return creditName;
	}
	

	public boolean delMiscSalUploadDtl(ConfigAuthorization bean,
			HttpServletRequest request) {
		Object del[][] = null;
		try {
			del = new Object[1][1];
			// to delete the single record after clicking on saving or searching
			// button
			del[0][0] = bean.getParacode();
		} catch(Exception e) {
			// TODO: handle exception
		}
		String deleteQuery = "DELETE FROM HRMS_SAL_UPLOAD_AUTH WHERE SAL_AUTH_CODE=? ";

		return getSqlModel().singleExecute(deleteQuery, del);
	}
	
}
