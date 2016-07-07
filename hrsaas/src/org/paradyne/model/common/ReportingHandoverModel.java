
package org.paradyne.model.common;

import java.util.ArrayList;

import org.paradyne.bean.common.ReportingHandover;
import org.paradyne.lib.ModelBase;

/**
 * @author tarunc
 * @date Oct 16, 2008
 * @description ReportingHandoverModel class serves as model for Reporting Hand Over form 
 * 				to write business logic for hand over
 */
public class ReportingHandoverModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReportingHandoverModel.class);
	
	/**
	 * @method handoverStructure
	 * @purpose to hand over the records from one employee to another employee also update the same in HRMS_REPORTING_STRUCTUREDTL 
	 * 			table and insert the back up record in HRMS_REPORTING_HANDOVER table
	 * @param bean
	 * @return boolean
	 */
	public boolean handoverStructure(ReportingHandover bean) {
		logger.info("inside handoverStructure model");
		
		Object [][] handoverData = null;		//Object array to pass as query parameter to insert the values in HRMS_REPORTING_HANDOVER table
		Object [][] empCodes = new Object[1][2];//Object array to pass as query parameter to update the values in HRMS_REPORTING_STRUCTUREDTL table
		
		empCodes [0][0] = bean.getToEmpCode();		//employee code to which hand over done
		empCodes [0][1] = bean.getFromEmpCode();	//employee code from which hand over done
		
		/*
		 * execute select query to select the records in which the selected employee is main approver
		 * and store them in mainApproverData object array
		 */
		Object [][] mainApproverData = getSqlModel().getSingleResult(getQuery(1), new Object []{bean.getFromEmpCode()});
		logger.info("mainApproverData  "+mainApproverData.length);
		
		/*
		 * execute select query to select the records in which the selected employee is alternate approver
		 * and store them in alternateApproverData object array
		 */
		Object [][] alternateApproverData = getSqlModel().getSingleResult(getQuery(2), new Object []{bean.getFromEmpCode()});
		logger.info("alternateApproverData  "+alternateApproverData.length);
		
		handoverData = new Object [mainApproverData.length+alternateApproverData.length][5];	//define the length of handoverData array 
		
		if(mainApproverData != null && mainApproverData.length != 0){
			try {
				/*
				 * iterator over mainApproverData and copy the records in handoverData array
				 */
				for (int i = 0; i < mainApproverData.length; i++) {
					handoverData [i][0] = checkNull(String.valueOf(mainApproverData[i][0]));	//reporting hdr code
					handoverData [i][1] = checkNull(String.valueOf(mainApproverData[i][1]));	//reporting dtl code
					handoverData [i][2] = checkNull(bean.getFromEmpCode());		//from employee code
					handoverData [i][3] = checkNull(bean.getToEmpCode());		//to employee code
					handoverData [i][4] = "M";									//approver type i.e. main approver
				}	//for loop ends
			} catch (Exception e) {
				logger.error("error in retrieving main approver details "+e);
				// TODO: handle exception
			}	//try-catch block ends
		}	//if statement ends
		
		if(alternateApproverData != null && alternateApproverData.length != 0){
			try {
				/*
				 * iterator over alternateApproverData and copy the records in handoverData array
				 */
				for (int i = 0; i < alternateApproverData.length; i++) {
					int j = i+mainApproverData.length;
					handoverData [j][0] = checkNull(String.valueOf(alternateApproverData[i][0]));	//reporting hdr code
					handoverData [j][1] = checkNull(String.valueOf(alternateApproverData[i][1]));	//reporting dtl code
					handoverData [j][2] = checkNull(bean.getFromEmpCode());		//from employee code
					handoverData [j][3] = checkNull(bean.getToEmpCode());		//to employee code
					handoverData [j][4] = "A";									//approver type i.e. alternate approver
				}	//for loop ends
			} catch (Exception e) {
				logger.error("error in retrieving alternate approver details "+e);
				// TODO: handle exception
			}	//try-catch block ends
		}	//if statement ends
		
		logger.info("handoverData  "+handoverData.length);
		
		/*
		 * execute the query to update the main approver code in HRMS_REPORTING_STRUCTUREDTL table
		 * where the approver was selected employee
		 */
		boolean result = getSqlModel().singleExecute(getQuery(4), empCodes);
		logger.info("result  "+result);
		
		if(result){
			/*
			 * execute the query to update the alternate approver code in HRMS_REPORTING_STRUCTUREDTL table
			 * where the approver was selected employee
			 */
			result = getSqlModel().singleExecute(getQuery(5), empCodes);
			
			if(result){
				/*
				 * execute the query to insert the back up details HRMS_REPORTING_HANDOVER table
				 */
				result = getSqlModel().singleExecute(getQuery(3), handoverData);
			}	//inner if statement ends
		}	//outer if statement ends
		return result;
	}
	
	/**
	 * @method showStructure
	 * @purpose to retrieve all the records from HRMS_REPORTING_STRUCTUREDTL and HRMS_REPORTING_STRUCTUREHDR tables
	 * 			in which approver is selected employee and display them in tabular format
	 * @param bean
	 */
	public void showStructure(ReportingHandover bean) {
		logger.info("inside showStructure method in model");
		
		ArrayList<Object> approverDataList = new ArrayList<Object>();
		int count = 0;
		
		String selectQuery = getQuery(6);	//get the query to select data from HRMS_REPORTING_STRUCTUREHDR and HRMS_REPORTING_STRUCTUREDTL tables
		
		selectQuery += "WHERE REPORTINGDTL_EMP_ID = "+bean.getFromEmpCode()	//applying condition to the query where the selected employee is main approver
						+" AND EMP_STATUS='S' ORDER BY HRMS_REPORTING_STRUCTUREDTL.REPORTINGHDR_CODE ASC, REPORTINGDTL_CODE ASC";
		logger.info("selectQuery is "+selectQuery);
		
		Object [][] approverData = getSqlModel().getSingleResult(selectQuery);	//get the selected data and store it in approverData object array
		logger.info("approverData "+approverData.length);
		
		if(approverData != null && approverData.length != 0){	//if data is present
			logger.info("form HRMS_REPORTING_STRUCTUREDTL table");
			
			for (int i = 0; i < approverData.length; i++) {	//iterate over the approverData array and add the records in a list
				try {
					ReportingHandover bean1 = new ReportingHandover();
					
					bean1.setStructureDefinedFor(setStructureDefinedFor(i, approverData));
					bean1.setReportingType(checkNull(String.valueOf(approverData[i][6])));
					bean1.setApproverType("Main Approver");
					bean1.setLevel(String.valueOf(approverData[i][0])+getOrdinalFor(Integer.parseInt(String.valueOf(approverData[i][0])))+"-Approver");
					
					approverDataList.add(bean1);
				} catch (Exception e) {
					logger.error("error in main approver "+e);
					// TODO: handle exception
				}	//try-catch block ends
			}	//for loop ends
		}	//if statement ends
		else{
			count++;	//else increase the count by one
		}	//else statement ends
		
		selectQuery = getQuery(6);	//get the query to select data from HRMS_REPORTING_STRUCTUREHDR and HRMS_REPORTING_STRUCTUREDTL tables
		
		selectQuery += "WHERE REPORTINGDTL_ALTER_EMP_ID = "+bean.getFromEmpCode()	//applying condition to the query where the selected employee is alternate approver
						+" AND EMP_STATUS='S' ORDER BY HRMS_REPORTING_STRUCTUREDTL.REPORTINGHDR_CODE ASC, REPORTINGDTL_CODE ASC";
		logger.info("selectQuery is "+selectQuery);
		
		approverData = getSqlModel().getSingleResult(selectQuery);	//get the selected data and store it in approverData object array
		logger.info("approverData "+approverData.length);
		
		if(approverData != null && approverData.length != 0){	//if data is present
			logger.info("form HRMS_REPORTING_STRUCTUREDTL table");
			
			for (int i = 0; i < approverData.length; i++) {	//iterate over the approverData array and add the records in a list
				try {
					ReportingHandover bean1 = new ReportingHandover();
					
					bean1.setStructureDefinedFor(setStructureDefinedFor(i, approverData));
					bean1.setReportingType(checkNull(String.valueOf(approverData[i][6])));
					bean1.setApproverType("Alternate Approver");
					bean1.setLevel(String.valueOf(approverData[i][0])+getOrdinalFor(Integer.parseInt(String.valueOf(approverData[i][0])))+"-Approver");
					
					approverDataList.add(bean1);
				} catch (Exception e) {
					logger.error("error in alternate approver "+e);
					// TODO: handle exception
				}	//try-catch block ends
			}	//for loop ends
		}	//if statement ends
		else{
			count++;	//else increase the count by one
		}	//else statement ends
		
		if(count == 2){	//if the value of count is 2
			//get the details from HRMS_REPORTING_HANDOVER table where selected employee is old approver and stores them in approverData array
			approverData = getSqlModel().getSingleResult(getQuery(7), new Object[]{bean.getFromEmpCode()});
			
			if(approverData != null && approverData.length != 0){	//if records are present 
				logger.info("form HRMS_REPORTING_HANDOVER table");
				
				for (int i = 0; i < approverData.length; i++) {	//iterate over the approverData array and add the records in a list
					try {
						ReportingHandover bean1 = new ReportingHandover();
						
						bean1.setStructureDefinedFor(setStructureDefinedFor(i, approverData));
						bean1.setReportingType(checkNull(String.valueOf(approverData[i][6])));
						bean1.setApproverType(checkNull(String.valueOf(approverData[i][7])));
						bean1.setLevel(String.valueOf(approverData[i][0])+getOrdinalFor(Integer.parseInt(String.valueOf(approverData[i][0])))+"-Approver");
						
						approverDataList.add(bean1);
					} catch (Exception e) {
						logger.error("error in alternate approver "+e);
						// TODO: handle exception
					}	//try-catch block ends
				}	//for loop ends
			}	//inner if statement ends
		}	//outer if statement ends
		bean.setApproverDataList(approverDataList);
	}
	
	/**
	 * @method setStructureDefinedFor
	 * @purpose
	 * @param approverData
	 * @return String
	 */
	public String setStructureDefinedFor(int index, Object [][] approverData) {
		String structureDefinedFor = "";
		
		if(!checkNull(String.valueOf(approverData[index][2])).equals("")){
			structureDefinedFor += checkNull(String.valueOf(approverData[index][2])).trim();
		}	//if statement ends
		
		if(!checkNull(String.valueOf(approverData[index][3])).equals("")){
			structureDefinedFor += ", "+checkNull(String.valueOf(approverData[index][3])).trim();
		}//if statement ends
		
		if(!checkNull(String.valueOf(approverData[index][5])).equals("")){
			structureDefinedFor += ", "+checkNull(String.valueOf(approverData[index][5]));
		}//if statement ends
		
		if(!checkNull(String.valueOf(approverData[index][4])).equals("")){
			structureDefinedFor += checkNull(String.valueOf(approverData[index][4]));
		}//if statement ends
		
		logger.info("yes*******    "+structureDefinedFor.charAt(0));
		logger.info("length*******    "+structureDefinedFor.length());
		
		if(structureDefinedFor.charAt(0) == ','){
			logger.info("yes");
			structureDefinedFor = structureDefinedFor.substring(1);
		}//if statement ends
		return structureDefinedFor;
	}
	
	/**
	 * @method getOrdinalFor
	 * @purpose to append the ordinal with  serial number
	 * @param value : specify the value to which ordinal will be appended
	 * @return String
	 */
	public String getOrdinalFor(int value) {
		 int hundredRemainder = value % 100;
		 
		 if(hundredRemainder >= 10 && hundredRemainder <= 20) {
		  return "th";
		 }	//if statement ends
		 
		 int tenRemainder = value % 10;
		 
		 switch (tenRemainder) {
		  case 1:
		   return "st";
		  case 2:
		   return "nd";
		  case 3:
		   return "rd";
		  default:
		   return "th";
		 }	//switch-case ends
	}
	
	/**
	 * @method checkNull
	 * @purpose to check whether the selected data is null or not
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}	//if statement ends
		else {
			return result;
		}	//else statement ends
	}
}
