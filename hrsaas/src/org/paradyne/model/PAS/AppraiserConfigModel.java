/**
 * 
 */
package org.paradyne.model.PAS;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.AppraisalPhaseConfig;
import org.paradyne.bean.PAS.AppraiserConfig;
import org.paradyne.lib.ModelBase;

/**
 * @author aa0554
 *
 */
public class AppraiserConfigModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(AppraiserConfigModel.class);
	
	public Object[][] getEmployees(AppraiserConfig bean){
		String query ="";
		String subQuery="";
		String divCheck ="";
		String deptCheck ="";
		String branchCheck ="";
		String branchFilter ="";
		String deptFilter ="";
		if(!checkNull(bean.getBranchCode()).equals("")){
			branchFilter = " AND CENTER_ID ="+bean.getBranchCode();
		}
		if(!checkNull(bean.getDeptCode()).equals("")){
			deptFilter = " AND DEPT_ID ="+bean.getDeptCode();
		}
			if(bean.getEmpTypeFlag().equals("true")){
				subQuery = "  AND APPR_EMP_ID NOT IN (SELECT DISTINCT APPR_APPRAISEE_ID FROM PAS_APPR_APPRAISER_GRP_DTL "
							+" INNER JOIN PAS_APPR_APPRAISER ON (PAS_APPR_APPRAISER.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID)"
							+" WHERE APPR_ID ="+bean.getApprId()+" )";
			}else {
				subQuery ="  AND APPR_EMP_ID IN (SELECT DISTINCT APPR_APPRAISEE_ID FROM PAS_APPR_APPRAISER_GRP_DTL "
							+" WHERE APPR_APPRAISER_GRP_ID ="+bean.getSelectGroupId()+")";
			}
			String checkDataQuery = "SELECT DISTINCT EMP_DIV,EMP_CENTER,EMP_DEPT  FROM PAS_APPR_ELIGIBLE_EMP INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID)" 
				+" WHERE APPR_ID="+bean.getApprId()+subQuery ;
			Object [][]checkDataObj = getSqlModel().getSingleResult(checkDataQuery);
			try {
				for (int i = 0; i < checkDataObj.length; i++) {
					if(i < checkDataObj.length-1)
					{
					divCheck += checkDataObj [i][0]+",";
					branchCheck += checkDataObj [i][1]+",";
					deptCheck += checkDataObj [i][2]+",";
					}else
					{
						divCheck += checkDataObj [i][0];
						branchCheck += checkDataObj [i][1];
						deptCheck += checkDataObj [i][2];
					}
				}
				
				
			} catch (Exception e) {
				divCheck = "0";
				branchCheck = "0";
				deptCheck = "0";
			}
			if(bean.getEmpTypeFlag().equals("true")){											// for pending employees
			query = "SELECT * FROM (SELECT DISTINCT DIV_ID, 0 AS PARENT , DIV_NAME, DIV_ID AS ID1, 0 AS ID2, 0 AS ID3, 0 AS ID4 FROM HRMS_EMP_OFFC "
					 	+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) WHERE DIV_ID ="+bean.getDivCode()
						+ " UNION ALL  SELECT DISTINCT DEPT_ID,EMP_CENTER AS PARENT,DEPT_NAME, DIV_ID AS ID1, EMP_CENTER AS ID2,DEPT_ID AS ID3, 0 AS ID4  "
						+" FROM HRMS_EMP_OFFC "
						+" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
						+" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
						+" LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) WHERE DEPT_ID IN("+deptCheck+") "+deptFilter
						+ " UNION ALL  SELECT DISTINCT APPR_EMP_ID,EMP_DEPT AS PARENT, EMP_FNAME||' '||EMP_MNAME|| ' ' || EMP_LNAME, EMP_DIV  AS ID1,EMP_CENTER AS ID2, DEPT_ID AS ID3 , EMP_ID AS ID4 FROM "
						+ " PAS_APPR_ELIGIBLE_EMP INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID)" 
						+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) WHERE APPR_ID="+bean.getApprId()+subQuery
						+ " UNION ALL SELECT DISTINCT CENTER_ID,EMP_DIV AS PARENT ,CENTER_NAME,EMP_DIV AS ID1,CENTER_ID AS ID2, 0 AS ID3, 0 AS ID4 "
						+ "  FROM HRMS_CENTER LEFT JOIN  (SELECT DISTINCT EMP_CENTER, EMP_DEPT,EMP_DIV  FROM HRMS_EMP_OFFC  INNER JOIN HRMS_DEPT ON "
						+ " ( HRMS_DEPT.DEPT_ID =HRMS_EMP_OFFC.EMP_DEPT)) X  ON (X.EMP_CENTER=HRMS_CENTER.CENTER_ID) WHERE CENTER_ID IN("+branchCheck+") "+branchFilter+" ) ORDER BY ID1, ID2 , ID3, ID4 ";
			}
			else{																					// for configured employees
				query = "SELECT * FROM (SELECT DISTINCT DIV_ID, 0 AS PARENT , DIV_NAME, DIV_ID AS ID1, 0 AS ID2, 0 AS ID3, 0 AS ID4 FROM HRMS_EMP_OFFC "
				 	+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) WHERE DIV_ID IN("+divCheck+") "
					+ " UNION ALL  SELECT DISTINCT DEPT_ID,EMP_CENTER AS PARENT,DEPT_NAME, DIV_ID AS ID1, EMP_CENTER AS ID2,DEPT_ID AS ID3, 0 AS ID4  "
					+" FROM HRMS_EMP_OFFC "
					+" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
					+" LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV) WHERE DEPT_ID IN("+deptCheck+")"
					+ " UNION ALL  SELECT DISTINCT APPR_EMP_ID,EMP_DEPT AS PARENT, EMP_FNAME||' '||EMP_MNAME|| ' ' || EMP_LNAME, EMP_DIV  AS ID1,EMP_CENTER AS ID2, DEPT_ID AS ID3 , EMP_ID AS ID4 FROM "
					+ " PAS_APPR_ELIGIBLE_EMP INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID)" 
					+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) WHERE APPR_ID="+bean.getApprId()+subQuery
					+ " UNION ALL SELECT DISTINCT CENTER_ID,EMP_DIV AS PARENT ,CENTER_NAME,EMP_DIV AS ID1,CENTER_ID AS ID2, 0 AS ID3, 0 AS ID4 "
					+ "  FROM HRMS_CENTER LEFT JOIN  (SELECT DISTINCT EMP_CENTER, EMP_DEPT,EMP_DIV  FROM HRMS_EMP_OFFC  INNER JOIN HRMS_DEPT ON "
					+ " ( HRMS_DEPT.DEPT_ID =HRMS_EMP_OFFC.EMP_DEPT)) X  ON (X.EMP_CENTER=HRMS_CENTER.CENTER_ID) WHERE CENTER_ID IN("+branchCheck+")) ORDER BY ID1, ID2 , ID3, ID4 ";
			}
			Object[][] obj = getSqlModel().getSingleResultInsensitive(query);
			String[][] strObj = null;
			try {
				if (obj.length > 0) {
					strObj = new String[obj.length][3];

					logger.info("length1---------" + obj.length);
					int count = 1000000;
					int count1 = 2000000;
					int count2 = 3000000;
					for (int i = 0; i < strObj.length; i++) {// no.of rows
						if (String.valueOf(obj[i][4]).equals("0")
								&& String.valueOf(obj[i][5]).equals("0")
								&& String.valueOf(obj[i][6]).equals("0")) {
							count += 5000;
							strObj[i][1] = String.valueOf(obj[i][1]);
							strObj[i][0] = count + "" + String.valueOf(obj[i][0]);
															//  new Division
							
						}else
						if (!String.valueOf(obj[i][4]).equals("0")
								&& String.valueOf(obj[i][5]).equals("0")
								&& String.valueOf(obj[i][6]).equals("0")) {
							count1 += 5000;	
							strObj[i][1] = count+String.valueOf(obj[i][1]);
							strObj[i][0] = count1 + "" + String.valueOf(obj[i][0]);
													// new Branch
							
						}else
						if (!String.valueOf(obj[i][4]).equals("0")
								&& !(String.valueOf(obj[i][5]).equals("0"))
								&& String.valueOf(obj[i][6]).equals("0")) {
							count2 += 5000;	
							strObj[i][1] = count1+String.valueOf(obj[i][1]);
							strObj[i][0] = count2 + "" + String.valueOf(obj[i][0]);
														// new Department
							
						}else{
							strObj[i][1] = count2+String.valueOf(obj[i][1]);
							strObj[i][0] =  String.valueOf(obj[i][0]);			// new Employee
						}
					

						/*if (!String.valueOf(obj[i][4]).equals("0")) {
							
							 * new
							 * 
							 
							if (!String.valueOf(obj[i][5]).equals("0")) {
								
								 * new employee
								 * 
								 
								if (!String.valueOf(obj[i][6]).equals("0")) {

									strObj[i][1] = count2 + ""
											+ String.valueOf(obj[i][1]);
									strObj[i][0] = String.valueOf(obj[i][0]);

								}

								else {

									strObj[i][1] = count1 + ""
											+ String.valueOf(obj[i][1]);
									strObj[i][0] = count2 + ""
											+ String.valueOf(obj[i][0]);

								}
								count2 += 1000;

							} else {
								strObj[i][1] = count + "" + String.valueOf(obj[i][1]);
								strObj[i][0] = count1 + "" + String.valueOf(obj[i][0]);

							}

						} else {
							strObj[i][1] = String.valueOf(obj[i][1]);
							strObj[i][0] = count + "" + String.valueOf(obj[i][0]);
							count += 1000;
						}*/

						strObj[i][2] = String.valueOf(obj[i][2]);

					}// end of i loop
				}// end of if
				else {
					strObj = new String[0][0];
				}// end of else
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Object finalObject [][]= getSortedObj(strObj);
			
			return finalObject;
		}
	
	public Object [][] getSortedObj(Object [][] dataObj){
		Object sortedObj [][]= null;
		
		Vector<Object> empDataVector = new Vector<Object>();
		Vector<Object> deptDataVector = new Vector<Object>();
		Vector<Object> brnDataVector = new Vector<Object>();
		Vector<Object> divDataVector = new Vector<Object>();
		Vector<Object> totalVector =  new Vector<Object>();
		Vector<Object> finalVector =  new Vector<Object>();
		
		/*
		 * adding employee
		 * 
		 */
		
		for (int i = 0; i < dataObj.length; i++) {
			if(String.valueOf(dataObj[i][0]).length()<8){
				Object [][]tempObj = new Object[1][3];
				tempObj [0][0] = dataObj[i][0];
				tempObj [0][1] = dataObj[i][1];
				tempObj [0][2] = dataObj[i][2];
				empDataVector.add(tempObj);
			}else{
					Object [][]tempObj = new Object[1][3];
					tempObj [0][0] = dataObj[i][0];
					tempObj [0][1] = dataObj[i][1];
					tempObj [0][2] = dataObj[i][2];
					totalVector.add(tempObj);
				
			}
				
			
		}
		logger.info("total vector size=="+totalVector.size());
		
		
		/*
		 * adding department
		 * 
		 */
			
				for (int i = 0; i < totalVector.size(); i++) {
					for (int j = 0; j < empDataVector.size(); j++) {
				if (String.valueOf(((Object[][]) empDataVector.get(j))[0][1]).equals
						(String.valueOf(((Object[][]) totalVector.get(i))[0][0]))) {
					if(deptDataVector.size()!=0){							// checking whether parent added previously
						boolean equalFlag= false;
					for (int k = 0; k < deptDataVector.size(); k++) {
						
						if (String.valueOf(((Object[][]) totalVector.get(i))[0][0]).equals
								(String.valueOf(((Object[][]) deptDataVector.get(k))[0][0]))) {
							equalFlag = true;
							
						} 
					}
					if(!equalFlag) {
						Object[][] tempObj = new Object[1][3];
						
						tempObj[0][0] = String.valueOf(((Object[][]) totalVector.get(i))[0][0]);
						tempObj[0][1] = String.valueOf(((Object[][]) totalVector.get(i))[0][1]);
						tempObj[0][2] = String.valueOf(((Object[][]) totalVector.get(i))[0][2]);
						
						deptDataVector.add(tempObj);
						
					}
					}else{
						Object[][] tempObj = new Object[1][3];
						
						tempObj[0][0] = String.valueOf(((Object[][]) totalVector.get(i))[0][0]);
						tempObj[0][1] = String.valueOf(((Object[][]) totalVector.get(i))[0][1]);
						tempObj[0][2] = String.valueOf(((Object[][]) totalVector.get(i))[0][2]);
						
						deptDataVector.add(tempObj);
					}
					
				}
			}
		}
		
				/*
				 * adding branch
				 * 
				 */
			
				for (int i = 0; i < totalVector.size(); i++) {
					for (int j = 0; j < deptDataVector.size(); j++) {
				if (String.valueOf(((Object[][]) deptDataVector.get(j))[0][1]).equals
						(String.valueOf(((Object[][]) totalVector.get(i))[0][0]))) {
					if(brnDataVector.size()!=0){
						boolean equalFlag= false;
					for (int k = 0; k < brnDataVector.size(); k++) {
						if (String.valueOf(((Object[][]) totalVector.get(i))[0][0]).equals
								(String.valueOf(((Object[][]) brnDataVector.get(k))[0][0]))) {
							equalFlag = true;
							
						} 
					}
					if(!equalFlag) {
						Object[][] tempObj = new Object[1][3];
						
						tempObj[0][0] = String.valueOf(((Object[][]) totalVector.get(i))[0][0]);
						tempObj[0][1] = String.valueOf(((Object[][]) totalVector.get(i))[0][1]);
						tempObj[0][2] = String.valueOf(((Object[][]) totalVector.get(i))[0][2]);
						
						brnDataVector.add(tempObj);
						
					}
					}else{
						Object[][] tempObj = new Object[1][3];
						
						tempObj[0][0] = String.valueOf(((Object[][]) totalVector.get(i))[0][0]);
						tempObj[0][1] = String.valueOf(((Object[][]) totalVector.get(i))[0][1]);
						tempObj[0][2] = String.valueOf(((Object[][]) totalVector.get(i))[0][2]);
						
						brnDataVector.add(tempObj);
					}
				}
			}
		}
		
				/*
				 * adding division
				 * 
				 */
			
				for (int i = 0; i < totalVector.size(); i++) {
					for (int j = 0; j < brnDataVector.size(); j++) {
				if (String.valueOf(((Object[][]) brnDataVector.get(j))[0][1]).equals
						(String.valueOf(((Object[][]) totalVector.get(i))[0][0]))) {
					if(divDataVector.size()!=0){
						boolean equalFlag = false;
					for (int k = 0; k < divDataVector.size(); k++) {
						if (String.valueOf(((Object[][]) totalVector.get(i))[0][0]).equals
								(String.valueOf(((Object[][]) divDataVector.get(k))[0][0]))) {
							equalFlag = true;
							
						} 
					}
					if(!equalFlag) {
						Object[][] tempObj = new Object[1][3];
						
						tempObj[0][0] = String.valueOf(((Object[][]) totalVector.get(i))[0][0]);
						tempObj[0][1] = String.valueOf(((Object[][]) totalVector.get(i))[0][1]);
						tempObj[0][2] = String.valueOf(((Object[][]) totalVector.get(i))[0][2]);
						
						divDataVector.add(tempObj);
						
					}
				}else {
					Object[][] tempObj = new Object[1][3];
					
					tempObj[0][0] = String.valueOf(((Object[][]) totalVector.get(i))[0][0]);
					tempObj[0][1] = String.valueOf(((Object[][]) totalVector.get(i))[0][1]);
					tempObj[0][2] = String.valueOf(((Object[][]) totalVector.get(i))[0][2]);
					
					divDataVector.add(tempObj);
				}
				}
			}
		}
		
		
		
	
			
			for (int i = 0; i < empDataVector.size(); i++) {
				Object[][] obj1=(Object[][])empDataVector.get(i);
				//logger.info("Emp----obj1[0][0]=="+obj1[0][0]+"obj1[0][1]=="+obj1[0][1]+"obj1[0][2]=="+obj1[0][2]);
				finalVector.add(obj1);
			}
			
			for (int i = 0; i < deptDataVector.size(); i++) {
				Object[][] obj1=(Object[][])deptDataVector.get(i);
				//logger.info("dept----obj1[0][0]=="+obj1[0][0]+"obj1[0][1]=="+obj1[0][1]+"obj1[0][2]=="+obj1[0][2]);
				finalVector.add(obj1);
			}
			
			for (int i = 0; i < brnDataVector.size(); i++) {
				Object[][] obj1=(Object[][])brnDataVector.get(i);
				//logger.info("brn----obj1[0][0]=="+obj1[0][0]+"obj1[0][1]=="+obj1[0][1]+"obj1[0][2]=="+obj1[0][2]);
				finalVector.add(obj1);
			}
			for (int i = 0; i < divDataVector.size(); i++) {
				Object[][] obj1=(Object[][])divDataVector.get(i);
				//logger.info("div----obj1[0][0]=="+obj1[0][0]+"obj1[0][1]=="+obj1[0][1]+"obj1[0][2]=="+obj1[0][2]);
				finalVector.add(obj1);
			}
			
			sortedObj = new Object [finalVector.size()][3];
			for (int i = 0; i < sortedObj.length; i++) {
				Object[][] obj1=(Object[][])finalVector.get(i);
				sortedObj [i][0] = obj1 [0][0];
				sortedObj [i][1] = obj1 [0][1];
				sortedObj [i][2] = obj1 [0][2];
			}
			logger.info("sortedObj length==="+sortedObj.length);
			return sortedObj;
		}
		
	
	
	
public void showConfiguredAppraisers(AppraiserConfig bean){
		String query= "SELECT APPR_CAL_CODE,PAS_APPR_APPRAISER.APPR_ID,PAS_APPR_APPRAISER.APPR_PHASE_ID,APPR_PHASE_NAME,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),"
					+" APPR_APPRAISER_CODE ,APPR_APPRAISER_LEVEL,APPR_IS_SELFPHASE FROM PAS_APPR_APPRAISER "
					+" LEFT JOIN PAS_APPR_CALENDAR ON(PAS_APPR_CALENDAR.APPR_ID=PAS_APPR_APPRAISER.APPR_ID)"
					+" LEFT JOIN PAS_APPR_PHASE_CONFIG ON (PAS_APPR_PHASE_CONFIG.APPR_PHASE_ID=PAS_APPR_APPRAISER.APPR_PHASE_ID)"
					+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=PAS_APPR_APPRAISER.APPR_APPRAISER_CODE)"
					//+" WHERE APPR_APPRAISER_GRP_ID = "+bean.getSelectGroupId();
					+" WHERE APPR_APPRAISER_GRP_ID = "+bean.getGroupId()+" ORDER BY PAS_APPR_APPRAISER.APPR_PHASE_ID";
					
		Object appraoserObj [][]= getSqlModel().getSingleResult(query);
			ArrayList<Object> tableList = new ArrayList<Object>();
		if(appraoserObj != null){
			for (int i = 0; i < appraoserObj.length; i++) {
				AppraiserConfig beanList= new AppraiserConfig();
				beanList.setAppraisalCodeList(checkNull(""+appraoserObj[i][0]));
				beanList.setAppraisalIdList(checkNull(""+appraoserObj[i][1]));
				beanList.setPhaseIdList(checkNull(""+appraoserObj[i][2]));
				beanList.setPhaseNameList(checkNull(""+appraoserObj[i][3]));
				logger.info("appraoserObj[i][4]=="+appraoserObj[i][4]);
				if((""+appraoserObj[i][4]).trim().equals("")){
					beanList.setAppraiserNameList("--NA--");
				}else
				beanList.setAppraiserNameList(checkNull(""+appraoserObj[i][4]));
				beanList.setAppraiserCodeList(checkNull(""+appraoserObj[i][5]));
				beanList.setAppraisalLevelList(checkNull(""+appraoserObj[i][6]));
				beanList.setIsSelfPhaseList(checkNull(""+appraoserObj[i][7]));
				tableList.add(beanList);
			}
		}
		
		bean.setAppraiserList(tableList);
	}
public void showAppraiserList(HttpServletRequest request, AppraiserConfig bean){
		
		String apprCode []=request.getParameterValues("appraisalCodeList");
		String apprId [] =request.getParameterValues("appraisalIdList");
		String phaseId []=request.getParameterValues("phaseIdList");
		String phaseName []=request.getParameterValues("phaseNameList");
		String AppriserName []=request.getParameterValues("appraiserNameList");
		String AppriserCode []=request.getParameterValues("appraiserCodeList");
		String appraiserLevel []=request.getParameterValues("appraisalLevelList");
		String isSelfPhaseList []=request.getParameterValues("isSelfPhaseList");
		
			ArrayList<Object> tableList = new ArrayList<Object>();
		if(apprCode != null){
			for (int i = 0; i < apprCode.length; i++) {
				AppraiserConfig beanList= new AppraiserConfig();
				beanList.setAppraisalCodeList(apprCode[i]);
				beanList.setAppraisalIdList(apprId[i]);
				beanList.setPhaseIdList(phaseId[i]);
				beanList.setPhaseNameList(phaseName[i]);
				beanList.setAppraiserNameList(checkNull(AppriserName[i]));
				beanList.setAppraiserCodeList(checkNull(AppriserCode[i]));
				beanList.setAppraisalLevelList(checkNull(appraiserLevel[i]));
				beanList.setIsSelfPhaseList(isSelfPhaseList[i]);
				tableList.add(beanList);
			}
		}
		
		bean.setAppraiserList(tableList);
	}
	public void addAppraiser(HttpServletRequest request, AppraiserConfig bean){
		
		String apprCode []=request.getParameterValues("appraisalCodeList");
		String apprId [] =request.getParameterValues("appraisalIdList");
		String phaseId []=request.getParameterValues("phaseIdList");
		String phaseName []=request.getParameterValues("phaseNameList");
		String AppriserName []=request.getParameterValues("appraiserNameList");
		String AppriserCode []=request.getParameterValues("appraiserCodeList");
		String appraiserLevel []=request.getParameterValues("appraisalLevelList");
		String isSelfPhaseList []=request.getParameterValues("isSelfPhaseList");
			
			ArrayList<Object> tableList = new ArrayList<Object>();
		if(apprCode != null){
			for (int i = 0; i < apprCode.length; i++) {
				AppraiserConfig beanList= new AppraiserConfig();
				beanList.setAppraisalCodeList(apprCode[i]);
				beanList.setAppraisalIdList(apprId[i]);
				beanList.setPhaseIdList(phaseId[i]);
				beanList.setPhaseNameList(phaseName[i]);
				beanList.setAppraiserNameList(checkNull(AppriserName[i]));
				beanList.setAppraiserCodeList(checkNull(AppriserCode[i]));
				beanList.setAppraisalLevelList(checkNull(appraiserLevel[i]));
				beanList.setIsSelfPhaseList(checkNull(isSelfPhaseList[i]));
				tableList.add(beanList);
			}
		}
		
		bean.setAppraisalCodeList(bean.getApprCode());
		bean.setAppraisalIdList(bean.getApprId());
		bean.setPhaseIdList(bean.getPhaseId());
		bean.setPhaseNameList(bean.getPhaseName());
		bean.setIsSelfPhaseList(bean.getIsSelfPhase());
		if(bean.getIsSelfPhase().equals("Y")){
			
			bean.setAppraiserNameList("NA");
			bean.setAppraiserCodeList("");
			bean.setAppraisalLevelList("1");
			
		}
		else{
			
			bean.setAppraiserNameList(bean.getAppraiserName());
			bean.setAppraiserCodeList(bean.getAppraiserCode());
			bean.setAppraisalLevelList(bean.getAppraiserLevel());
			
		}
		tableList.add(bean);
		
		bean.setAppraiserList(tableList);
	}
	public boolean saveDefineAppraiser(HttpServletRequest request, AppraiserConfig bean){
		boolean result=false;
		String counter="";
		String groupId ="";
		
		try {
			groupId=bean.getGroupId();
			System.out.println("Group Id ::: "+groupId);
			result = getSqlModel().singleExecute(
					"DELETE FROM PAS_APPR_APPRAISER WHERE APPR_APPRAISER_GRP_ID="
							+ groupId);
			if (result) {

				String apprId[] = request.getParameterValues("appraisalIdList");
				String phaseId[] = request.getParameterValues("phaseIdList");
				String AppriserCode[] = request
						.getParameterValues("appraiserCodeList");
				String appraiserLevel[] = request
						.getParameterValues("appraisalLevelList");
				String isSelfPhaseList[] = request
						.getParameterValues("isSelfPhaseList");

				Object insertAppraiserObj[][] = new Object[apprId.length][5];
				for (int i = 0; i < appraiserLevel.length; i++) {
					logger.info("apprId ["+i+"]==="+apprId[i]);
					logger.info("phaseId ["+i+"]==="+phaseId[i]);
					logger.info("AppriserCode ["+i+"]==="+AppriserCode[i]);
					logger.info("appraiserLevel ["+i+"]==="+appraiserLevel[i]);
					
					insertAppraiserObj [i][0] = String.valueOf(apprId[i]);
					insertAppraiserObj [i][1] = groupId;
					insertAppraiserObj [i][2] = String.valueOf(phaseId[i]);
					insertAppraiserObj [i][3] = String.valueOf(AppriserCode[i]);
					insertAppraiserObj [i][4] = String.valueOf(appraiserLevel[i]);
					
				}
				result = getSqlModel().singleExecute(getQuery(3),insertAppraiserObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean save(HttpServletRequest request, AppraiserConfig bean){
		boolean result=false;
		String counter="";
		String groupId ="";
		String empIds[]=null;
		//if(bean.getEmpTypeFlag().equals("true")){
		Object maxGroupId [][]=getSqlModel().getSingleResult("SELECT MAX(NVL(APPR_APPRAISER_GRP_ID,0))+1 FROM PAS_APPR_APPRAISER_GRP_HDR");
		
		
		bean.setGroupId(""+maxGroupId[0][0]);
	
		groupId = bean.getGroupId();
		if(groupId.equals("null")){
			groupId = "1";
		}
		bean.setGroupId(groupId);
		Object insertHdrObj[][]= new Object[1][3];
			insertHdrObj [0][0] = groupId;
			insertHdrObj [0][1] = bean.getGroupName();
			insertHdrObj [0][2] = bean.getApprId();
			logger.info("group id=="+groupId);
			
			result = getSqlModel().singleExecute(getQuery(1),insertHdrObj);							// INsert into PAS_APPR_APPRAISER_GRP_HDR
		//}
		/*else{
			groupId = bean.getSelectGroupId();
			Object updateHdrObj[][]= new Object[1][2];
			
			updateHdrObj [0][0] = bean.getSelectGroupName();
			updateHdrObj [0][1] = bean.getSelectGroupId();
			
			result = getSqlModel().singleExecute(getQuery(4),updateHdrObj);							// UPDATE PAS_APPR_APPRAISER_GRP_HDR
		}
			if(result)
		try {
			if(groupId.equals("")){
				groupId = "0";
			}
			result= getSqlModel().singleExecute("DELETE FROM PAS_APPR_APPRAISER_GRP_DTL WHERE APPR_APPRAISER_GRP_ID="+groupId);
			
			if(result){
			String checkBox[]= request.getParameterValues("insertChk");
			logger.info(("items lenght====" + checkBox.length));
			
			for (int i = 0; i < checkBox.length; i++) {
				logger.info("checkBox["+i+"] value=="+checkBox[i]);
				logger.info("checkBox["+i+"] length=="+checkBox[i].length());
				if(checkBox[i].length()< 8){
					counter += checkBox[i]+",";
				}
			}
			 empIds = counter.split(",");
			Object insertDtlObj [][]= new Object [empIds.length][2];
			for (int i = 0; i < insertDtlObj.length; i++) {
				insertDtlObj [i][0] = groupId;
				insertDtlObj [i][1] = empIds[i];
			}
			result = getSqlModel().singleExecute(getQuery(2),insertDtlObj);									// INsert into PAS_APPR_APPRAISER_GRP_DTL
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		if(result)
			try{
				result= getSqlModel().singleExecute("DELETE FROM PAS_APPR_APPRAISER WHERE APPR_APPRAISER_GRP_ID="+groupId);
				
				if(result){
					
				
				String apprId [] =request.getParameterValues("appraisalIdList");
				String phaseId []=request.getParameterValues("phaseIdList");
				String AppriserCode []=request.getParameterValues("appraiserCodeList");
				String appraiserLevel []=request.getParameterValues("appraisalLevelList");
				String isSelfPhaseList []=request.getParameterValues("isSelfPhaseList");
				
				Object insertAppraiserObj [][]= new Object [apprId.length][5];
				
				/*for (int i = 0; i < appraiserLevel.length; i++) {
						logger.info("apprId [" + i + "]===" + apprId[i]);
						logger.info("phaseId [" + i + "]===" + phaseId[i]);
						logger.info("AppriserCode [" + i + "]==="
								+ AppriserCode[i]);
						logger.info("appraiserLevel [" + i + "]==="
								+ appraiserLevel[i]);
						if (isSelfPhaseList[i].equals("Y")) {
							Object selfAppraisers[][] = new Object[empIds.length][5];
							for (int j = 0; j < empIds.length; j++) {
								selfAppraisers[j][0] = String.valueOf(apprId[i]);
								selfAppraisers[j][1] = groupId;
								selfAppraisers[j][2] = String.valueOf(phaseId[i]);
								selfAppraisers[j][3] = empIds[j];
								selfAppraisers[j][4] = String.valueOf(appraiserLevel[i]);
							}
							result = getSqlModel().singleExecute(getQuery(3),selfAppraisers); 				// insert into PAS_APPR_APPRAISER for self appraisers
						} else {
							insertAppraiserObj[0][0] = String.valueOf(apprId[i]);
							insertAppraiserObj[0][1] = groupId;
							insertAppraiserObj[0][2] = String.valueOf(phaseId[i]);
							insertAppraiserObj[0][3] = String.valueOf(AppriserCode[i]);
							insertAppraiserObj[0][4] = String.valueOf(appraiserLevel[i]);
							result = getSqlModel().singleExecute(getQuery(3),insertAppraiserObj); 				// insert into PAS_APPR_APPRAISER

						}
				}*/
					/*	for (int i = 0; i < appraiserLevel.length; i++) {
							logger.info("apprId ["+i+"]==="+apprId[i]);
							logger.info("phaseId ["+i+"]==="+phaseId[i]);
							logger.info("AppriserCode ["+i+"]==="+AppriserCode[i]);
							logger.info("appraiserLevel ["+i+"]==="+appraiserLevel[i]);
							
							insertAppraiserObj [i][0] = String.valueOf(apprId[i]);
							insertAppraiserObj [i][1] = groupId;
							insertAppraiserObj [i][2] = String.valueOf(phaseId[i]);
							insertAppraiserObj [i][3] = String.valueOf(AppriserCode[i]);
							insertAppraiserObj [i][4] = String.valueOf(appraiserLevel[i]);
							
						}
						result = getSqlModel().singleExecute(getQuery(3),insertAppraiserObj);					// insert into PAS_APPR_APPRAISER 
				
				
				
				}
			}catch (Exception e) {
				e.printStackTrace();
			}*/
		return result;
	}
public boolean deleteApprGroupConfg(AppraiserConfig bean, HttpServletRequest request){
		
		String proConfigList [] = request.getParameterValues("chkDel");
		boolean result = false;
		
		if(proConfigList!=null && proConfigList.length>0){
			
			for(int i=0;i<proConfigList.length;i++){
				
				Object delParam[][] = new Object [1][1];
				delParam[0][0] = proConfigList[i];
				String delGroupHdr="DELETE FROM PAS_APPR_APPRAISER_GRP_HDR WHERE APPR_APPRAISER_GRP_ID= ?";
				result = getSqlModel().singleExecute(delGroupHdr,delParam);
				
			}
			
		}
		return result;
		
		
	}

	public boolean addAppraisee(HttpServletRequest request, AppraiserConfig bean)
	{
		boolean result=false;
		String splitId[] = bean.getParaId().split(",");
		if(splitId!=null && splitId.length>0)
		{
			Object addAppraiseeObj[][]=new Object[splitId.length][2];
			for (int j = 0; j < splitId.length; j++) {
				System.out.println("Appraisal Id :: "+bean.getApprId());
				System.out.println("Group Id :: "+bean.getGroupId());
				System.out.println("Employee ::splitId["+j+"] :: "+splitId[j]);
				addAppraiseeObj[j][0]=checkNull(bean.getGroupId());
				addAppraiseeObj[j][1]=checkNull(splitId[j]);
			}
			result = getSqlModel().singleExecute(getQuery(2),addAppraiseeObj);
		}
		
		
		return result;
	}
	
	public void removeMultiple(HttpServletRequest request, AppraiserConfig bean){
			
			String apprCode []=request.getParameterValues("appraisalCodeList");
			String apprId [] =request.getParameterValues("appraisalIdList");
			String phaseId []=request.getParameterValues("phaseIdList");
			String phaseName []=request.getParameterValues("phaseNameList");
			String AppriserName []=request.getParameterValues("appraiserNameList");
			String AppriserCode []=request.getParameterValues("appraiserCodeList");
			String appraiserLevel []=request.getParameterValues("appraisalLevelList");
			
			String splitId[] = bean.getParaId().split(",");
			
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (apprCode != null) {
			for (int i = 0; i < apprCode.length; i++) {
				boolean removeFlag = false;
				AppraiserConfig beanList = new AppraiserConfig();
				
				for (int j = 0; j < splitId.length; j++) {
					
					if (i == Integer.parseInt(splitId[j])) {
						removeFlag = true;
						break;
					}

				}						// end of inner for loop
				if (!removeFlag) {
					beanList.setAppraisalCodeList(apprCode[i]);
					beanList.setAppraisalIdList(apprId[i]);
					beanList.setPhaseIdList(phaseId[i]);
					beanList.setPhaseNameList(phaseName[i]);
					beanList.setAppraiserNameList(AppriserName[i]);
					beanList.setAppraiserCodeList(AppriserCode[i]);
					beanList.setAppraisalLevelList(appraiserLevel[i]);
					tableList.add(beanList);
				}
			}						// end of outer for loop
		}
			
			
			bean.setAppraiserList(tableList);
	}
	public boolean deleteAppraisee(AppraiserConfig bean,HttpServletRequest request){
		boolean result=false;
		String empFinal []= bean.getParaId().split(",");
		if(empFinal!=null && empFinal.length>0)
		{
			String sqlQuery="delete from PAS_APPR_APPRAISER_GRP_dtl where APPR_APPRAISER_GRP_ID=? and APPR_APPRAISEE_ID = ?";
			Object deleteObj [][]= new Object[empFinal.length][2];
			for (int i = 0; i < deleteObj.length; i++) {
				deleteObj [i][0] = bean.getGroupId();
				deleteObj [i][1] = empFinal[i];
			}
			result=getSqlModel().singleExecute(sqlQuery,deleteObj);
		}
		return result;
	}
	public boolean moveToGroup(AppraiserConfig bean,String empId,String moveGroup,HttpServletRequest request){
		boolean result=false;
		String checked []=request.getParameterValues("moveChk");
		String selectedEmp ="";
		/*for (int i = 0; i < checked.length; i++) {
			if(checked[i].length()<8 ){
				selectedEmp += checked[i]+",";
			}
		}*/
		//String empFinal []= selectedEmp.split(",");
		String empFinal []= bean.getParaId().split(",");
		String insertQuery ="INSERT INTO PAS_APPR_APPRAISER_GRP_DTL(APPR_APPRAISER_GRP_ID,APPR_APPRAISEE_ID) VALUES(?,?)";
		
		Object insertObj [][]= new Object[empFinal.length][2];
		for (int i = 0; i < insertObj.length; i++) {
			insertObj [i][0] = moveGroup;
			insertObj [i][1] = empFinal[i];
		}
		
		result = getSqlModel().singleExecute(insertQuery , insertObj);
		Object dltObj [][]= new Object[empFinal.length][2];
		for (int i = 0; i < dltObj.length; i++) {
			//dltObj [i][0] = bean.getSelectGroupId();
			dltObj [i][0] = bean.getGroupId();
			dltObj [i][1] = empFinal[i];
		}
		if(result){
			getSqlModel().singleExecute("DELETE FROM PAS_APPR_APPRAISER_GRP_DTL WHERE APPR_APPRAISER_GRP_ID=? AND APPR_APPRAISEE_ID= ?",dltObj);
		}
		return result;
	}
	public boolean removeFromGroup(AppraiserConfig bean, HttpServletRequest request){
		boolean result=false;
		String checked []=request.getParameterValues("removeChk");
		String selectedEmp ="";
		for (int i = 0; i < checked.length; i++) {
			if(checked[i].length()<8 ){
				selectedEmp += checked[i]+",";
			}
		}
		String empFinal []= selectedEmp.split(",");
		
		
		Object deleteObj [][]= new Object[empFinal.length][2];
		for (int i = 0; i < deleteObj.length; i++) {
			deleteObj [i][0] = bean.getSelectGroupId();
			deleteObj [i][1] = empFinal[i];
		}
		
			result = getSqlModel().singleExecute("DELETE FROM PAS_APPR_APPRAISER_GRP_DTL WHERE APPR_APPRAISER_GRP_ID=? AND APPR_APPRAISEE_ID= ?",deleteObj);
		
		return result;
	}
	public void getApprGroupList(AppraiserConfig bean){
		String sqlQuery="SELECT NVL(APPR_CAL_CODE,''),NVL(APPR_APPRAISER_GRP_NAME,''),TO_CHAR(APPR_APPRAISER_GRP_DATE,'DD-MM-YYYY'),APPR_APPRAISER_GRP_ID, PAS_APPR_APPRAISER_GRP_HDR.APPR_ID "+ 
						"FROM PAS_APPR_APPRAISER_GRP_HDR "+
						"INNER JOIN PAS_APPR_CALENDAR ON(PAS_APPR_CALENDAR.APPR_ID=PAS_APPR_APPRAISER_GRP_HDR.APPR_ID) "+ 
						"WHERE PAS_APPR_APPRAISER_GRP_HDR.APPR_ID="+bean.getApprId();
		Object [][]dataObj = getSqlModel().getSingleResult(sqlQuery);
		ArrayList tableList=new ArrayList();
		if(dataObj!=null && dataObj.length>0)
		{
			for(int i=0;i<dataObj.length;i++)
			{
				AppraiserConfig apprConfObj=new AppraiserConfig();
				apprConfObj.setIttrApprCode(checkNull(String.valueOf(dataObj[i][0])));
				apprConfObj.setIttrGroupName(checkNull(String.valueOf(dataObj[i][1])));
				apprConfObj.setIttrGroupDate(checkNull(String.valueOf(dataObj[i][2])));
				apprConfObj.setIttrGroupId(checkNull(String.valueOf(dataObj[i][3])));
				apprConfObj.setIttrApprId(checkNull(String.valueOf(dataObj[i][4])));
				tableList.add(apprConfObj);
			}
		}
		bean.setApprGroupList(tableList);
	}
	public void getAppCalDetails(AppraiserConfig bean){
		String sqlQuery="SELECT  APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID FROM PAS_APPR_CALENDAR "
						+"WHERE APPR_ID="+bean.getApprId();
		Object [][]dataObj = getSqlModel().getSingleResult(sqlQuery);
		
		if(dataObj!=null && dataObj.length>0)
		{
			bean.setApprCode(checkNull(String.valueOf(dataObj[0][0])));
			bean.setFrmDate(checkNull(String.valueOf(dataObj[0][1])));
			bean.setToDate(checkNull(String.valueOf(dataObj[0][2])));
			bean.setApprId(checkNull(String.valueOf(dataObj[0][3])));
		
		}
	
	}
	
	public void getConfiguredAppraisee(AppraiserConfig bean,HttpServletRequest request){
		String sqlQuery="SELECT E1.EMP_TOKEN,E1.EMP_FNAME|| ' ' || E1.EMP_LNAME AS NAME,CENTER_NAME,DEPT_NAME,RANK_NAME,E2.EMP_FNAME|| ' ' || E2.EMP_LNAME AS REPORTING_TO,E1.EMP_ID "+
						"FROM HRMS_EMP_OFFC E1 "+ 
						"INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (E1.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID AND PAS_APPR_ELIGIBLE_EMP.APPR_ID="+bean.getApprId()+") "+
						"LEFT JOIN HRMS_EMP_OFFC E2 ON (E1.EMP_REPORTING_OFFICER=E2.EMP_ID) "+
						"LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=E1.EMP_CENTER) "+
						"LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=E1.EMP_DEPT) "+
						"LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=E1.EMP_DIV) "+
						"LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=E1.EMP_RANK) "+
						"WHERE E1.EMP_ID IN (SELECT APPR_APPRAISEE_ID FROM PAS_APPR_APPRAISER_GRP_DTL WHERE APPR_APPRAISER_GRP_ID="+bean.getGroupId()+")"+
						"AND E1.EMP_STATUS='S'";
		Object [][]dataObj = getSqlModel().getSingleResult(sqlQuery);
		ArrayList tableList=new ArrayList();
		if(dataObj!=null && dataObj.length>0)
		{
			for(int i=0;i<dataObj.length;i++)
			{
				AppraiserConfig apprConfObj=new AppraiserConfig();
				apprConfObj.setIttrConfEmpToken(checkNull(String.valueOf(dataObj[i][0])));
				apprConfObj.setIttrConfEmpName(checkNull(String.valueOf(dataObj[i][1])));
				apprConfObj.setIttrConfEmpBranch(checkNull(String.valueOf(dataObj[i][2])));
				apprConfObj.setIttrConfEmpDepartment(checkNull(String.valueOf(dataObj[i][3])));
				apprConfObj.setIttrConfEmpDesgination(checkNull(String.valueOf(dataObj[i][4])));
				apprConfObj.setIttrConfEmpReptTo(checkNull(String.valueOf(dataObj[i][5])));
				apprConfObj.setIttrConfEmpId(checkNull(String.valueOf(dataObj[i][6])));
				tableList.add(apprConfObj);
			}
		}
		bean.setConfAppraiseeList(tableList);
	}
	
	public void getPendingAppraisee(AppraiserConfig bean,HttpServletRequest request){
		String sqlQuery="SELECT E1.EMP_TOKEN,E1.EMP_FNAME|| ' ' || E1.EMP_LNAME AS NAME,CENTER_NAME,DEPT_NAME,RANK_NAME,E2.EMP_FNAME|| ' ' || E2.EMP_LNAME AS REPORTING_TO,E1.EMP_ID "+
						"FROM HRMS_EMP_OFFC E1 "+ 
						"INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (E1.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID AND PAS_APPR_ELIGIBLE_EMP.APPR_ID="+bean.getApprId()+") "+
						"LEFT JOIN HRMS_EMP_OFFC E2 ON (E1.EMP_REPORTING_OFFICER=E2.EMP_ID) "+
						"LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=E1.EMP_CENTER) "+
						"LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=E1.EMP_DEPT) "+
						"LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=E1.EMP_DIV) "+
						"LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=E1.EMP_RANK) "+
						"WHERE E1.EMP_ID NOT IN (SELECT DISTINCT APPR_APPRAISEE_ID FROM PAS_APPR_APPRAISER_GRP_DTL "+ 
						"INNER JOIN PAS_APPR_APPRAISER_GRP_HDR ON (PAS_APPR_APPRAISER_GRP_HDR.APPR_APPRAISER_GRP_ID=PAS_APPR_APPRAISER_GRP_DTL.APPR_APPRAISER_GRP_ID) "+
						"WHERE PAS_APPR_APPRAISER_GRP_HDR.APPR_ID="+bean.getApprId()+") "+
						"AND E1.EMP_STATUS='S'";
		if(!bean.getDivCode().equals(""))
		{
			sqlQuery+="AND E1.EMP_DIV = "+bean.getDivCode();
		}
		if(!bean.getBranchCode().equals(""))
		{
			sqlQuery+="AND E1.EMP_CENTER = "+bean.getBranchCode();
		}
		if(!bean.getDeptCode().equals(""))
		{
			sqlQuery+="AND E1.EMP_DEPT = "+bean.getDeptCode();
		}
		if(!bean.getDesignationId().equals(""))
		{
			sqlQuery+="AND E1.EMP_RANK = "+bean.getDesignationId();
		}
		if(!bean.getGradeId().equals(""))
		{
			sqlQuery+="AND E1.EMP_CADRE = "+bean.getGradeId();
		}
		if(!bean.getEmpTypeId().equals(""))
		{
			sqlQuery+="AND E1.EMP_TYPE = "+bean.getEmpTypeId();
		}
		if(!bean.getEmpreptId().equals(""))
		{
			sqlQuery+="AND E1.EMP_REPORTING_OFFICER = "+bean.getEmpreptId();
		}
		Object [][]dataObj = getSqlModel().getSingleResult(sqlQuery);
		ArrayList tableList=new ArrayList();
		if(dataObj!=null && dataObj.length>0)
		{
			for(int i=0;i<dataObj.length;i++)
			{
				AppraiserConfig apprConfObj=new AppraiserConfig();
				apprConfObj.setIttrPendEmpToken(checkNull(String.valueOf(dataObj[i][0])));
				apprConfObj.setIttrPendEmpName(checkNull(String.valueOf(dataObj[i][1])));
				apprConfObj.setIttrPendEmpBranch(checkNull(String.valueOf(dataObj[i][2])));
				apprConfObj.setIttrPendEmpDepartment(checkNull(String.valueOf(dataObj[i][3])));
				apprConfObj.setIttrPendEmpDesgination(checkNull(String.valueOf(dataObj[i][4])));
				apprConfObj.setIttrPendEmpReptTo(checkNull(String.valueOf(dataObj[i][5])));
				apprConfObj.setIttrPendEmpId(checkNull(String.valueOf(dataObj[i][6])));
				tableList.add(apprConfObj);
			}
		}
		bean.setPendAppraiseeList(tableList);
	}
	public void setGroupName(AppraiserConfig bean)
	{
		String sqlQuery="SELECT nvl(APPR_APPRAISER_GRP_NAME,'') FROM PAS_APPR_APPRAISER_GRP_HDR WHERE APPR_APPRAISER_GRP_ID="+bean.getGroupId();
		Object [][]dataObj = getSqlModel().getSingleResult(sqlQuery);
		if(dataObj!=null && dataObj.length>0)
		{
			bean.setGroupName(checkNull(String.valueOf(dataObj[0][0])));
		}
	}
	public boolean checkDuplicateGroup(AppraiserConfig bean)
	{
		boolean result=false;
		String sqlQuery="SELECT APPR_APPRAISER_GRP_ID FROM 	PAS_APPR_APPRAISER_GRP_HDR WHERE APPR_ID="+bean.getApprId()+" AND UPPER(APPR_APPRAISER_GRP_NAME) ='"+checkNull(bean.getGroupName()).trim().toUpperCase()+"'";
		Object [][]dataObj = getSqlModel().getSingleResult(sqlQuery);
		if(dataObj!=null && dataObj.length>0)
		{
			result=true;
		}
		return result;
	}
	/* 
	 * method name : checkNull
	 * purpose     : check the string is null or not
	 * return type : String
	 * parameter   : String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}  // end of if
		else {
			return result;
		} // end of else
	}
	
	public boolean removeAppraisee(AppraiserConfig bean, HttpServletRequest request){
		boolean result=false;
		String checked []=request.getParameterValues("paraId");
		String selectedEmp ="";
		for (int i = 0; i < checked.length; i++) {
			
				selectedEmp += checked[i]+",";
		
		}
		String empFinal []= selectedEmp.split(",");
		
		
		Object deleteObj [][]= new Object[empFinal.length][2];
		for (int i = 0; i < deleteObj.length; i++) {
			deleteObj [i][0] = bean.getGroupId();
			deleteObj [i][1] = empFinal[i];
		}
		
		result = getSqlModel().singleExecute("DELETE FROM PAS_APPR_APPRAISER_GRP_DTL WHERE APPR_APPRAISER_GRP_ID=? AND APPR_APPRAISEE_ID= ?",deleteObj);
		
		return result;	
		
	}
}
