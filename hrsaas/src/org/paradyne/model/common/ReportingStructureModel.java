/**
 * 
 */
package org.paradyne.model.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.paradyne.bean.common.ReportingStructure;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author tarunc
 * @date Jan 15, 2008
 * @description ReportingStructureModel class serves as model for reporting structure form to write the 
 * 				business logic to save, update reporting structure  for various application
 */
public class ReportingStructureModel extends ModelBase {

	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/**
	 * @method saveReportingStructure
	 * @purpose to insert or update record in to HRMS_REPORTING_STRUCTUREHDR 
	 * 			and HRMS_REPORTING_STRUCTUREDTL tables
	 * @param bean to pop all the form properties
	 * @param approverId to get the approver code
	 * @param approverToken to get the approver token number
	 * @param approverName to get the approver name
	 * @param designation to get the approver designation
	 * @param alternateApproverId to get the alternate approver code
	 * @return Strung
	 */
	public boolean saveReportingStructure(ReportingStructure bean,
			String[] approverId, String[] approverToken, String[] approverName,
			String[] designation, String[] alternateApproverId) {
		logger.info("inside SaveData");
		boolean result;

		Object[][] hdrCode = new Object[1][1];
		Object[][] insertFormData = new Object[1][7];
		String sameAs = "null";
		String isDefault = "N";

		Object[][] formData = getFormData(bean); //call to formData method to retrieve the form data
		Object[] isDataExist = checkSave(bean, formData); //call to checkSave method to check if the record is already exist or not

		String selectQuery = "SELECT REPORTINGHDR_SAME_AS, REPORTINGHDR_ISDEFAULT FROM HRMS_REPORTING_STRUCTUREHDR "
				+ "WHERE REPORTINGHDR_TYPE = '" + bean.getReportingType() + "'";

		Object[][] getData = getSqlModel().getSingleResult(selectQuery);

		if (getData != null && getData.length != 0) {
			for (int i = 0; i < getData.length; i++) {
				if (!String.valueOf(getData[i][0]).equals("")
						&& !String.valueOf(getData[i][0]).equals("null")) {
					sameAs = String.valueOf(getData[i][0]);
				} //end of if
				if (!String.valueOf(getData[i][1]).equals("")
						&& !String.valueOf(getData[i][1]).equals("N")) {
					isDefault = String.valueOf(getData[i][1]);
				} //end of if
			} //end of for loop
		} //end of if

		insertFormData[0][0] = formData[0][0];
		insertFormData[0][1] = formData[0][1];
		insertFormData[0][2] = formData[0][2];
		insertFormData[0][3] = formData[0][3];
		insertFormData[0][4] = formData[0][4];
		insertFormData[0][5] = sameAs;
		insertFormData[0][6] = isDefault;

		//if record is not exist then
		if (isDataExist[0] == null) {
			logger.info("is data alredy there----------------no");
			//to clear already present records of default and same as of this type
			Object structure[][] = getSqlModel().getSingleResult(getQuery(21),
					new Object[] { bean.getStructureType() });

			if (structure != null && structure.length > 0) {
				/*logger.info("while insert data alredy there with same as or default str----------------Yes");
				String clearQuery= " UPDATE HRMS_REPORTING_STRUCTUREHDR SET REPORTINGHDR_ISDEFAULT = 'N', REPORTINGHDR_SAME_AS ='null' where REPORTINGHDR_TYPE = '"+bean.getStructureType()+"'";
				getSqlModel().singleExecute(clearQuery);*/
			} //end of if

			result = getSqlModel().singleExecute(getQuery(2), insertFormData); //query to insert header details in HRMS_REPORTING_STRUCTUREHDR
			logger.info("result---------- " + result);

			if (result) {
				Object[][] maxHdrCode = getSqlModel().getSingleResult(
						getQuery(5)); //query to select max header code from HRMS_REPORTING_STRUCTUREHDR
				logger.info("hdrCode   " + maxHdrCode[0][0]);
				hdrCode[0][0] = maxHdrCode[0][0];
			} //end of if
		} //end of if

		//if record is already exist then
		else {
			logger.info("is data alredy there----------------yes");

			hdrCode[0][0] = isDataExist[0];
			insertFormData[0][4] = sameAs;
			insertFormData[0][5] = isDefault;
			insertFormData[0][6] = hdrCode[0][0];
			//to clear already present records of default and same as of this type
			/*Object structure[][] = getSqlModel().getSingleResult(getQuery(21),new Object[]{bean.getStructureType()});
			if(structure != null && structure.length > 0){
				logger.info("while update data alredy there with same as or default str----------------Yes");
			String clearQuery= " UPDATE HRMS_REPORTING_STRUCTUREHDR SET REPORTINGHDR_ISDEFAULT = 'N', REPORTINGHDR_SAME_AS ='null' where REPORTINGHDR_TYPE = '"+bean.getStructureType()+"'";
			getSqlModel().singleExecute(clearQuery);
			}*/

			result = getSqlModel().singleExecute(getQuery(27), insertFormData); //query to update HRMS_REPORTING_STRUCTUREHDR

			if (result) {
				result = getSqlModel().singleExecute(getQuery(7), hdrCode); //query to delete data from HRMS_REPORTING_STRUCTUREDTL
			} //end of if
		} //end of else

		if (result) {
			if (approverId != null && approverId.length != 0) {
				Object[][] tableData = new Object[1][4]; //table data for reporting structure for insert query
				for (int i = 0; i < approverId.length; i++) {
					tableData[0][0] = hdrCode[0][0];
					tableData[0][1] = hdrCode[0][0];
					tableData[0][2] = approverId[i];
					tableData[0][3] = alternateApproverId[i];

					logger.info("tableData [1][0] ************ "
							+ tableData[0][0]);
					logger.info("tableData [1][1] ************ "
							+ tableData[0][1]);
					logger.info("tableData [1][2] ************ "
							+ tableData[0][2]);
					logger.info("tableData [1][3] ************ "
							+ tableData[0][3]);

					result = getSqlModel()
							.singleExecute(getQuery(6), tableData); //query to insert approver details in HRMS_REPORTING_STRUCTUREDTL
					logger.info("result is ************ " + result);
				} //end of for loop
			} //end of if
		} //end of if
		return result;
	}

	/**
	 * @method checkSave
	 * @purpose to check whether the record is already exist in HRMS_REPORTING_STRUCTUREHDR or not
	 * @param bean to pop all the form properties
	 * @param headerData to get the approver code
	 * @return hdrCode (code of the record if available)
	 */
	public Object[] checkSave(ReportingStructure bean, Object[][] headerData) {
		logger.info("inside checkSave");
		Object[] selectData = new Object[5];
		Object[] hdrCode = new Object[1];

		//getting form data 
		selectData[0] = headerData[0][0];
		selectData[1] = headerData[0][1];
		selectData[2] = headerData[0][2];
		selectData[3] = headerData[0][3];
		selectData[4] = headerData[0][4];

		Object[][] isDataExist = getSqlModel().getSingleResult(getQuery(3),
				selectData); //Query to select data for specified form data from HRMS_REPORTING_STRUCTUREHDR

		if (isDataExist != null && isDataExist.length != 0) {
			hdrCode[0] = isDataExist[0][0];
		} //end of if
		return hdrCode;
	}

	/**
	 * @method showApproverData
	 * @purpose to retrieve the approver details and set these values 
	 * 			on the form fields for selected record
	 * @param bean to pop all the form properties
	 * @return String
	 */
	public String showApproverData(ReportingStructure bean) {
		logger.info("inside model");

		String result = "";
		Object[][] formData = getFormData(bean); //call to formData method to retrieve the form data
		Object[] hdrCode = checkSave(bean, formData); //call to checkSave method to check if the record is already exist or not

		if (hdrCode[0] != null) {
			ArrayList list = new ArrayList();

			Object[][] getApproverData = getSqlModel().getSingleResult(
					getQuery(1), hdrCode); //query to select approver details for given header code from HRMS_REPORTING_STRUCTUREDTL
			logger.info("result.length showEmployee   "
					+ getApproverData.length);

			if (getApproverData != null && getApproverData.length != 0) {
				for (int i = 0; i < getApproverData.length; i++) {
					ReportingStructure bean1 = new ReportingStructure();
					bean1.setEmpIdIterator(checkNull(String
							.valueOf(getApproverData[i][0]))); //Setting the selected values to bean properties
					bean1.setEmpTokenIterator(checkNull(String
							.valueOf(getApproverData[i][1])));
					bean1.setEmpNameIterator(checkNull(String
							.valueOf(getApproverData[i][2])));
					bean1.setAlternateEmpIdIterator(checkNull(String
							.valueOf(getApproverData[i][3])));
					bean1.setAlternateEmpTokenIterator(checkNull(String
							.valueOf(getApproverData[i][4])));
					bean1.setAlternateEmpNameIterator(checkNull(String
							.valueOf(getApproverData[i][5])));

					/*bean1.setDesgIdIterator(String.valueOf(getApproverData[i][2]));
					bean1.setDesgNameIterator(String.valueOf(getApproverData[i][3]));*/

					String srNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
					bean1.setSrNoIterator(srNo);

					logger.info("1111111111111 " + getApproverData[i][0]);
					logger.info("2222222222222 " + getApproverData[i][1]);
					logger.info("3333333333333 " + getApproverData[i][2]);
					logger.info("4444444444444 " + getApproverData[i][3]);

					logger.info("srNo " + srNo);
					list.add(bean1);
				} //end of for loop
			} //end of if
			else {
				result = "no data";
			} //end of else

			bean.setHdrCode(String.valueOf(hdrCode[0]));
			bean.setEmpList(list);

		} //end of if
		else {
			result = "no data";
		} //end of else
		return result;
	}

	/**
	 * @method getFormData
	 * @purpose to retrieve all the form parameters
	 * @param bean to pop all the form properties
	 * @return headerData (Array of form parameters)
	 */
	public Object[][] getFormData(ReportingStructure bean) {
		Object[][] headerData = new Object[1][5]; //Header data for Department and Branch wise reporting structure for insert query

		//get the form data through bean instance
		String deptCode = bean.getDeptCode();
		String branchCode = bean.getBrnCode();
		String desgCode = bean.getDesignationCode();
		String empCode = bean.getEmpCode();

		//if a particular value is null then set it to 0
		if (deptCode.equals("")) {
			deptCode = "0";
		} //end of if
		if (branchCode.equals("")) {
			branchCode = "0";
		} //end of if
		if (desgCode.equals("")) {
			desgCode = "0";
		} //end of if
		if (empCode.equals("")) {
			empCode = "0";
		} //end of if

		headerData[0][0] = deptCode; //Department Code
		headerData[0][1] = branchCode; //Branch Code
		headerData[0][2] = empCode; //Employee Code
		headerData[0][3] = desgCode; //Designation Code
		headerData[0][4] = bean.getReportingType(); //Reporting Type

		return headerData; //return headerData with form values
	}

	/**
	 * @method deleteRecord
	 * @purpose to delete the selected record
	 * @param bean to pop all the form properties
	 * @return  String
	 */
	public String deleteRecord(ReportingStructure bean) {
		logger.info("in side delete");
		String result = "";
		boolean queryResult = false;

		Object[][] formData = getFormData(bean); //call to formData method to retrieve the form data
		Object[] hdrCode = checkSave(bean, formData); //call to checkSave method to check if the record is already exist or not

		if (hdrCode[0] != null) {
			Object[][] deleteHdrCode = new Object[1][1];
			deleteHdrCode[0][0] = hdrCode[0];

			queryResult = getSqlModel().singleExecute(getQuery(7),
					deleteHdrCode); //query to delete approver details from HRMS_REPORTING_STRUCTUREDTL

			if (queryResult) {
				queryResult = getSqlModel().singleExecute(getQuery(28),
						deleteHdrCode); //query to delete header details from HRMS_REPORTING_STRUCTUREHDR

				if (queryResult) {
					result = "deleted";
				} //end of if
			} //end of if
			else {
				result = "error";
			} //end of else
		} //end of if
		return result;
	}

	/**
	 * @Method deleteApprover
	 * @Purpose to delete the approver from the list
	 * @param bean to pop all the form properties
	 * @param srNo to get the sr number
	 * @param approverId to get the approver code
	 * @param approverToken to get the approver token number
	 * @param approverName to get the approver name
	 * @param designation to get the approver designation name
	 * @param designationId to get the approver designation code
	 * @param alternateEmpId to get the alternate approver code
	 * @param alternateEmpToken to get the alternate approver token number
	 * @param alternateEmpName to get the alternate approver name
	 * @return result(boolean)
	 **/
	public boolean deleteApprover(ReportingStructure bean, String srNo,
			String[] approverId, String[] approverToken, String[] approverName,
			String[] designation, String[] designationId,
			String[] alternateEmpId, String[] alternateEmpToken,
			String[] alternateEmpName) {
		logger.info("in side delete");

		boolean result = false;

		if (approverId != null && approverId.length != 0) {
			ArrayList list = new ArrayList();
			ArrayList list1 = new ArrayList();
			for (int i = 0; i < approverId.length; i++) {
				ReportingStructure bean1 = new ReportingStructure();
				bean1.setEmpIdIterator(approverId[i]);
				bean1.setEmpTokenIterator(approverToken[i]);
				bean1.setEmpNameIterator(approverName[i]);
				bean1.setDesgNameIterator(designation[i]);
				bean1.setDesgIdIterator(designationId[i]);
				bean1.setAlternateEmpIdIterator(alternateEmpId[i]);
				bean1.setAlternateEmpTokenIterator(alternateEmpToken[i]);
				bean1.setAlternateEmpNameIterator(alternateEmpName[i]);

				list.add(bean1);
			} //end of for loop
			list.remove(Integer.parseInt(srNo) - 1);

			for (int i = 0; i < list.size(); i++) {
				ReportingStructure bean1 = (ReportingStructure) list.get(i);

				String showSrNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
				bean1.setSrNoIterator(showSrNo);
				logger.info("showSrNo-----------" + showSrNo);
				list1.add(bean1);
			} //end of for loop
			bean.setEmpList(list1);
		} //end of if
		return result;
	}

	/**
	 * @Method shuffleColumns
	 * @Purpose shuffle the approver names in the list
	 * @param bean to pop all the form properties
	 * @param srNo to get the sr number
	 * @param approverId to get the approver code
	 * @param approverToken to get the approver token number
	 * @param approverName to get the approver name
	 * @param designation to get the approver designation name
	 * @param designationId to get the approver designation code
	 * @param srNoIterator to get the sr number for iterator
	 * @param buttonType to get the button type (up or down)
	 * @param alternateEmpId to get the alternate approver code
	 * @param alternateEmpToken to get the alternate approver token number
	 * @param alternateEmpName to get the alternate approver name
	 **/
	public void shuffleColumns(ReportingStructure bean, String srNo,
			String[] approverId, String[] approverToken, String[] approverName,
			String[] designation, String[] designationId,
			String[] srNoIterator, String buttonType, String[] alternateEmpId,
			String[] alternateEmpToken, String[] alternateEmpName) {
		logger.info("in side delete");

		boolean result = false;
		int shuffleCode = Integer.parseInt(srNo);

		if (approverId != null && approverId.length != 0) {
			ArrayList list = new ArrayList();
			ArrayList list1 = new ArrayList();

			for (int i = 0; i < approverId.length; i++) {
				ReportingStructure bean1 = new ReportingStructure();
				bean1.setEmpIdIterator(approverId[i]);
				bean1.setEmpTokenIterator(approverToken[i]);
				bean1.setEmpNameIterator(approverName[i]);
				bean1.setDesgNameIterator(designation[i]);
				bean1.setDesgIdIterator(designationId[i]);
				bean1.setSrNoIterator(srNoIterator[i]);
				bean1.setAlternateEmpIdIterator(alternateEmpId[i]);
				bean1.setAlternateEmpTokenIterator(alternateEmpToken[i]);
				bean1.setAlternateEmpNameIterator(alternateEmpName[i]);

				list.add(bean1);
			} //end of for loop

			if (buttonType.equals("up")) {
				logger.info("shuffleCode-----------" + shuffleCode);

				ReportingStructure bean1 = (ReportingStructure) list
						.get(shuffleCode - 1);
				shuffleCode = Integer.parseInt(srNo) - 1;

				logger.info("shuffleCode 4444-----------" + shuffleCode);

				if (shuffleCode > 0) {
					list.add(shuffleCode - 1, bean1);
					logger.info("shuffleCode after-----------" + shuffleCode);

					list.remove(shuffleCode + 1);
				} //end of if
			} //end of if
			else if (buttonType.equals("down")) {
				logger.info("shuffleCode-----------" + shuffleCode + " srNo "
						+ srNo);

				ReportingStructure bean1 = (ReportingStructure) list
						.get(shuffleCode - 1);
				shuffleCode = Integer.parseInt(srNo) + 1;

				logger.info("shuffleCode 4444-----------" + shuffleCode);

				if (shuffleCode > 0 && shuffleCode < list.size() + 1) {
					list.add(shuffleCode, bean1);
					logger.info("shuffleCode after-----------" + shuffleCode);

					list.remove(shuffleCode - 2);
				} //end of if
			} //end of else

			for (int i = 0; i < list.size(); i++) {
				ReportingStructure bean1 = (ReportingStructure) list.get(i);

				String showSrNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
				bean1.setSrNoIterator(showSrNo);

				logger.info("showSrNo-----------" + showSrNo);

				list1.add(bean1);
			} //end of for loop
			bean.setEmpList(list1);
		} //end of if
	}

	/**
	 * @Method addApprover
	 * @Purpose add the approver in the list
	 * @param bean to pop all the form properties
	 * @param approverId to get the approver code
	 * @param approverToken to get the approver token number
	 * @param approverName to get the approver name
	 * @param alternateEmpId to get the alternate approver code
	 * @param alternateEmpToken to get the alternate approver token number
	 * @param alternateEmpName to get the alternate approver name
	 * @return ArrayList containing approver data
	 **/
	public ArrayList addApprover(ReportingStructure bean, String[] approverId,
			String[] approverToken, String[] approverName,
			String[] alternateEmpId, String[] alternateEmpToken,
			String[] alternateEmpName) {
		logger.info("inside setApprover");

		ArrayList list = new ArrayList();
		int count = 0;

		if (approverId != null && approverId.length != 0) {
			for (int i = 0; i < approverId.length; i++) {
				ReportingStructure bean1 = new ReportingStructure();

				if (!bean.getSrNo().equals("")
						&& i == Integer.parseInt(bean.getSrNo()) - 1) {
					logger.info("iffffffffffffffffffffff");
					bean1.setEmpIdIterator(bean.getEmpId());
					bean1.setEmpTokenIterator(bean.getEmpTokenAdd());
					bean1.setEmpNameIterator(bean.getEmpNameAdd());
					bean1.setAlternateEmpIdIterator(bean.getAlternateEmpId());
					bean1.setAlternateEmpTokenIterator(bean
							.getAlternateEmpToken());
					bean1.setAlternateEmpNameIterator(bean
							.getAlternateEmpName());
				} //end of if statement
				else {
					logger.info("elseeeeeeeeeeeeeeeeeeee");
					bean1.setEmpIdIterator(approverId[i]);
					bean1.setEmpTokenIterator(approverToken[i]);
					bean1.setEmpNameIterator(approverName[i]);
					bean1.setAlternateEmpIdIterator(alternateEmpId[i]);
					http: //localhost:1487/pims/pages/images/recruitment/search2.gif
					bean1.setAlternateEmpTokenIterator(alternateEmpToken[i]);
					bean1.setAlternateEmpNameIterator(alternateEmpName[i]);
				} //end of else statement

				String srNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
				bean1.setSrNoIterator(srNo);

				logger.info("setSrNoIterator------------ " + srNo);
				count = i + 1;
				logger.info("count------------ " + count);
				list.add(bean1);
			} //end of for loop
		} //end of if
		if (!bean.getEmpId().equals("") && bean.getSrNo().equals("")) {
			logger.info("emp id");
			bean.setEmpIdIterator(bean.getEmpId());
			bean.setEmpTokenIterator(bean.getEmpTokenAdd());
			bean.setEmpNameIterator(bean.getEmpNameAdd());
			bean.setAlternateEmpIdIterator(bean.getAlternateEmpId());
			bean.setAlternateEmpTokenIterator(bean.getAlternateEmpToken());
			bean.setAlternateEmpNameIterator(bean.getAlternateEmpName());

			/*bean.setDesgNameIterator(bean.getDesgName());
			bean.setDesgIdIterator(bean.getDesgId());*/

			String srNo = count + 1 + getOrdinalFor(count + 1) + "-Approver";

			logger.info("------------ " + srNo);
			bean.setSrNoIterator(srNo);

			list.add(bean);
		} //end of if
		bean.setEmpList(list);

		return list;
	}

	/**
	 * @Method rollBack
	 * @Purpose to remove either default or same as criteria
	 * @param bean
	 * @return boolean
	 */
	public String rollBack(ReportingStructure bean) {
		logger.info("in side rollBack model ");
		logger.info("bean.getStructureType() " + bean.getStructureType());
		String type = decodeStructure(bean, bean.getStructureType());

		String updateQuery = "UPDATE HRMS_REPORTING_STRUCTUREHDR SET REPORTINGHDR_ISDEFAULT = 'N', "
				+ "REPORTINGHDR_SAME_AS = 'null' "
				+ "WHERE REPORTINGHDR_TYPE = '" + bean.getStructureType() + "'";

		boolean result = getSqlModel().singleExecute(updateQuery);
		logger.info("result  " + result);
		
		//UPDATE HRMS_REPORTING_APPL_TYPE TABLE
		String updateAppQuery = " UPDATE HRMS_REPORTING_APPL_TYPE SET REPORTING_IS_SAMEASOFFICIAL = 'N', "
			+ " REPORTING_ISDEFAULT = 'N', REPORTING_SAME_AS = 'null' WHERE REPORTING_APPL_TYPE_ABBREV = "
			+ "'" + bean.getStructureType() + "'";
		result = getSqlModel().singleExecute(updateAppQuery);

		if (result)
			return "You have successfully removed the manager level/default/same as hierarchy of "
					+ type + ".";
		else
			return "Error while changing.";
	}

	/**
	 * @Method report
	 * @Purpose to generate the report for selected record
	 * @param bean to pop all the form properties
	 * @param response to get the HttpServletResponse instance
	 * @return boolean
	 **/
	public boolean report(ReportingStructure bean, HttpServletResponse response) {
		logger.info("inside model report");
		String name = "Reporting Structure Report";
		String type = "Xls";
		String title = "Reporting Structure Report";

		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,
				title);
		rg = getHeader(bean, rg);

		logger.info("rg in report================================ " + rg);

		rg.createReport(response);
		return true;

	}

	/**
	 * @Method getHeader
	 * @Purpose to retrieve all the report data and generate the report
	 * @param bean to pop all the form properties
	 * @param rg to get the ReportGenerator instance
	 * @return rg (ReportGenerator instance)
	 **/
	public ReportGenerator getHeader(ReportingStructure bean, ReportGenerator rg) {
		logger.info("get header ----------------------");
		Object[] repType = new Object[1];
		repType[0] = bean.getReportingType();
		logger.info("repType[0] " + bean.getReportingType());

		/*Object[][] hdrData  		= getSqlModel().getSingleResult(getQuery(8), repType); 	//query to select Department and Branch Data
		logger.info("hdrData length--------- "+hdrData.length);*/

		String query1 = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object[][] today = getSqlModel().getSingleResult(query1);
		String date = "Date : " + (String) today[0][0];

		logger.info("Reporting type ----------------------"
				+ bean.getReportingType());

		String header = " Reporting Structure Report for "
				+ decodeStructure(bean, bean.getReportingType());
		rg.addFormatedText(header, 6, 0, 1, 0);
		rg.addText(date, 0, 2, 0);
		// rg.addText("\n", 0, 0, 0);
		System.out.println("The value of Default : ------------------: "+bean.getManagerRecord());
		System.out.println("The Level : -----------------: "+bean.getManagerLevel());
		int order=Integer.parseInt(bean.getManagerLevel());
		if("on".equals(bean.getManagerRecord()))
		{
			String sqlQuery="SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS = 'S'";
			Object empData[][] = getSqlModel().getSingleResult(sqlQuery);
			rg.addText("\n", 0, 0, 0);
			if(empData!=null && empData.length >0)
			{
				for(int j=0;j<empData.length;j++)
				{
					String isReportingManager = "N";
					int maxApprovalLevels = 1;

					String checkForOfficial = "SELECT NVL(REPORTING_IS_SAMEASOFFICIAL,'N'),NVL(REPORTING_LEVELS,0) FROM HRMS_REPORTING_APPL_TYPE WHERE  REPORTING_APPL_TYPE_ABBREV='"
							+ bean.getReportingType() + "'";
					Object OfficialObj[][] = getSqlModel().getSingleResult(
							checkForOfficial);
					if (OfficialObj != null && OfficialObj.length > 0) {
						Object data[][] = null;
						isReportingManager = String.valueOf(OfficialObj[0][0]);
						maxApprovalLevels = Integer.parseInt(String
								.valueOf(OfficialObj[0][1]));
						System.out.println("isReportingManager  " + isReportingManager);
						Object empInfo[][]=getEmployeeData(String.valueOf(empData[j][0]));
						int[] rowcellwidth = { 100};
						int[] rowalignmnet = { 0 };
						
						int[] rowcellwidthEmp = { 20,80 };
						int[] rowalignmnetEmp = { 0,0 };
						if(empInfo!=null && empInfo.length>0)
						{
							/*System.out.println("The employee Token : "+empInfo[0][0]);
							System.out.println("The employee Name : "+empInfo[0][1]);
							System.out.println("The employee Branch : "+empInfo[0][2]);
							System.out.println("The employee Designation : "+empInfo[0][3]);
							System.out.println("The employee Id : "+empInfo[0][4]);*/
							Object[][] empName =new Object[1][2];
							
							empName[0][0]="Employee ID :"+String.valueOf(empInfo[0][0]);
							empName[0][1]="Employee Name :"+String.valueOf(empInfo[0][1]);
							
							/*{ { String.valueOf(" Employee ID :")
									+ "     " + String.valueOf(Data[i][6]) },{ String.valueOf(" Employee Name :")
										+ "     " + String.valueOf(Data[i][4]) } };*/
							
							rg.tableBody(empName, rowcellwidthEmp, rowalignmnetEmp);
						}
						if (isReportingManager.equals("Y")) {
							String ManagerId = String.valueOf(empData[j][0]);
							for (int i = 0; i < order; i++) {
								if (i < maxApprovalLevels) {
									data = getManager(ManagerId, bean.getReportingType());
									if (!StringUtils.isEmpty(checkNull(String
											.valueOf(data[0][0])))) {
										ManagerId = String.valueOf(data[0][0]);
										Object mangerInfo[][]=getEmployeeData(ManagerId);
										

										Object[][] setHdr = new Object[4][1];
										/*String colNames2[] = { "Approver Id", "Approver Name",
												"Alternate Approver Id", "Alternate Approver Name" };*/
										String colNames2[] = { "Approver Id", "Approver Name"};

										int[] cellwidth2 = { 15, 30 };
										if(mangerInfo!=null && mangerInfo.length>0)
										{
											/*System.out.println("The Manger Token : "+mangerInfo[0][0]);
											System.out.println("The Manager Name : "+mangerInfo[0][1]);
											System.out.println("The Manager Branch : "+mangerInfo[0][2]);
											System.out.println("The Manager Designation : "+mangerInfo[0][3]);
											System.out.println("The employee Id : "+mangerInfo[0][4]);*/
										}
										data[0][2] = order;
										//data[0][2] = 1;
										rg.addText("\n", 0, 0, 0);
										rg.tableBody(colNames2, mangerInfo, cellwidth2);
										rg.addText("\n", 0, 0, 0);
										
									} else {
										data = null;
									}
								} else {
									data = null;
								}
								
							}
							//return data;
						}
					}
				}
				
			}
		}
		else
		{
			String Query = " SELECT REPORTINGHDR_CODE,NVL(DEPT_NAME,' ') ,NVL(CENTER_NAME,' '),NVL(RANK_NAME,' ') , "
				+ "(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)NAME , ";
		/*+"DECODE(REPORTINGHDR_TYPE, 'Leave','Leave','Requi','Requisition', 'Cash','Cash Voucher', 'Train', 'Training', "
		+"'Tran', 'Transfer', 'Sugg', 'Suggestion', 'Help', 'Help Desk', 'LTC', 'LTC Advance', 'Festi', 'Festival Advance', "
		+"'Other', 'Other Advance', 'Deput', 'Deputation', 'HBA', 'HBA', 'GPF', 'GPF', 'CTF', 'Children Tution Fee', "
		+"'Medi', 'Medical Advance', 'TYD', 'Travel', 'Appra', 'Appraisal', 'Confere', 'Conference', 'Loan', 'Loan', "
		+"'Asset', 'Asset', 'Cash Process', 'Cash Process', 'Recruitment', 'Recruitment')TYPE "*/
		Query += " '" + decodeStructure(bean, bean.getReportingType()) + "' ";

		Query += " , EMP_TOKEN FROM HRMS_REPORTING_STRUCTUREHDR "
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_DEPT_ID) "
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_BRN_ID) "
				+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_DESG_ID)"
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_EMP_ID)where REPORTINGHDR_TYPE='"
				+ bean.getReportingType() + "'  ORDER BY REPORTINGHDR_CODE ";

		logger.info("value of Query                             " + Query);

		Object Data[][] = getSqlModel().getSingleResult(Query);
		rg.addText("\n", 0, 0, 0);
		
		

		if (Data.length > 0 && Data != null) {
			/*header = " Reporting Structure Report for "+String.valueOf(Data[0][5]);
			rg.addFormatedText(header, 1, 0, 1, 0);*/
			
			

			int[] rowcellwidth = { 100};
			int[] rowalignmnet = { 0 };
			
			int[] rowcellwidthEmp = { 20,80 };
			int[] rowalignmnetEmp = { 0,0 };

			Object[][] setHdr = new Object[4][1];
			for (int i = 0; i < Data.length; i++) {
				//----------------
				String subQuery = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME NAME, "
						+ "ALTERNATE.EMP_TOKEN, ALTERNATE.EMP_FNAME||' '||ALTERNATE.EMP_MNAME|| ' ' || ALTERNATE.EMP_LNAME ALTERNATE_NAME "
						+ "FROM HRMS_REPORTING_STRUCTUREDTL "
						+ "INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REPORTING_STRUCTUREDTL.REPORTINGDTL_EMP_ID) "
						+ "LEFT JOIN  HRMS_EMP_OFFC ALTERNATE ON (ALTERNATE.EMP_ID = REPORTINGDTL_ALTER_EMP_ID)  "
						+ "WHERE REPORTINGHDR_CODE = "
						+ Data[i][0]
						+ " "
						+ "ORDER BY REPORTINGDTL_CODE";

				Object DataDtl[][] = getSqlModel().getSingleResult(subQuery);

				String colNames2[] = { "Approver Id", "Approver Name",
						"Alternate Approver Id", "Alternate Approver Name" };

				int[] cellwidth2 = { 15, 30, 25, 30 };

				String recordNum = i + 1 + getOrdinalFor(i + 1) + "-Record";

				rg.addFormatedText(recordNum + " :-", 4, 0, 0, 0);

				if (!String.valueOf(Data[i][1]).trim().equals("")) {
					logger.info("department " + String.valueOf(Data[i][1]));
					Object[][] deptName = { { String
							.valueOf(" Department Name :")
							+ "     " + String.valueOf(Data[i][1]) } };
					rg.tableBody(deptName, rowcellwidth, rowalignmnet);
				} //if statement ends
				if (!String.valueOf(Data[i][2]).trim().equals("")) {
					logger.info("branch " + String.valueOf(Data[i][2]));
					Object[][] branchName = { { String
							.valueOf(" Branch Name :")
							+ "     " + String.valueOf(Data[i][2]) } };
					rg.tableBody(branchName, rowcellwidth, rowalignmnet);
				} //if statement ends
				if (!String.valueOf(Data[i][3]).trim().equals("")) {
					logger.info("designation " + String.valueOf(Data[i][3]));
					Object[][] desgName = { { String
							.valueOf(" Designation Name :")
							+ "     " + String.valueOf(Data[i][3]) } };
					rg.tableBody(desgName, rowcellwidth, rowalignmnet);
				} //if statement ends
				if (!String.valueOf(Data[i][4]).trim().equals("")) {
					logger.info("employee " + String.valueOf(Data[i][4]));
					Object[][] empName =new Object[1][2];
					
					empName[0][0]="Employee ID :"+String.valueOf(Data[i][6]);
					empName[0][1]="Employee Name :"+String.valueOf(Data[i][4]);
					
					/*{ { String.valueOf(" Employee ID :")
							+ "     " + String.valueOf(Data[i][6]) },{ String.valueOf(" Employee Name :")
								+ "     " + String.valueOf(Data[i][4]) } };*/
					
					rg.tableBody(empName, rowcellwidthEmp, rowalignmnetEmp);
				} //if statement ends

				/*setHdr[0][0] = String.valueOf(" Department Name :")+"     "+String.valueOf(Data[i][1]);
				setHdr[1][0] = String.valueOf(" Branch Name :")+"     "+String.valueOf(Data[i][2]);
				setHdr[2][0] = String.valueOf(" Designation Name :")+"     "+String.valueOf(Data[i][3]);
				setHdr[3][0] = String.valueOf(" Employee Name :")+"     "+String.valueOf(Data[i][4]);*/

				//rg.tableBody(setHdr, rowcellwidth, rowalignmnet);
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(colNames2, DataDtl, cellwidth2);
				rg.addText("\n", 0, 0, 0);
			} //end of for loop
		} //end of if

		else {

			header = " Reporting Structure Not Designed  for this model";
			rg.addFormatedText(header, 1, 0, 1, 0);
		} //end of else
		}
		
		return rg;
	}

	/**
	 * @Method genReport
	 * @Purpose to set the header data for the report
	 * @param rg to get the ReportGenerator instance
	 * @param setHdrData to get the header data
	 * @param header to get the header for the report
	 **/
	public void genReport(ReportGenerator rg, Object[][] setHdrData,
			String header) {
		int[] rowcellwidth = { 100 };
		int[] rowalignmnet = { 0 };

		try {
			rg.addFormatedText(header, 1, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(setHdrData, rowcellwidth, rowalignmnet);
			rg.addText("\n", 0, 0, 0);
			header = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Method checkNull
	 * @Purpose to check whether the selected data is null or not
	 * @param result value of the data
	 * @return String
	 **/
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} //end of if
		else {
			return result;
		} //end of else
	}

	/**
	 * @Method checkDefault
	 * @Purpose to apply default structure this selected type 
	 * @param bean to pop all form properties
	 **/
	public void checkDefault(ReportingStructure bean) {
		logger.info("inside model checkDefault " + bean.getReportingType());
		Object[] checkObj = new Object[1];
		checkObj[0] = bean.getReportingType();

		Object[][] defaultObj = getSqlModel().getSingleResult(getQuery(26));
		if (defaultObj != null && defaultObj.length > 0) {
			bean.setDefaultAllCheck("true");
			bean.setDefaultName(String.valueOf(defaultObj[0][0]));

		} //end of if

		Object res[][] = getSqlModel().getSingleResult(getQuery(18), checkObj);
		//logger.info("inside model cek "+res[0][0]);
		if (res != null && res.length > 0) {
			if (String.valueOf(res[0][0]).equals("Y")) {
				bean.setDefaultCheck("true");
				logger.info("insideif true " + bean.getDefaultCheck());
			} //end of if
			else {
				bean.setDefaultCheck("false");
			} //end of else

			if (!(String.valueOf(res[0][1]).equals("null"))) {
				logger.info("insideielse if true " + String.valueOf(res[0][1])
						+ "");
				bean.setSameAsCheck("true");
				bean.setSameStr(String.valueOf(res[0][1]));

				logger.info("bean.getSameStr() " + bean.getSameStr());
				String structure = decodeStructure(bean, bean.getSameStr());
				bean.setSameStr(structure);
				logger.info("bean.getSameStr()66666 " + bean.getSameStr());
			} //end of if
			else {
				bean.setSameAsCheck("false");
			} //end of else
			
			
		} //end of if
	}

	/**
	 * @Method defaultAll
	 * @Purpose to check whether the structure for this particular 
	 * 			type is default for all or not
	 * @param bean to pop all form properties
	 **/
	public String defaultAll(ReportingStructure bean) {
		try {
			Object[][] update = new Object[1][2];
			boolean result = false;
			logger.info("chech " + bean.getDefaultCheck());
			logger.info("TYPE " + bean.getStructureType());

			Object structure[][] = getSqlModel().getSingleResult(getQuery(21),
					new Object[] { bean.getStructureType() });

			if (structure != null && structure.length > 0) {
				if (String.valueOf(bean.getDefaultCheck()).equals("on")) {
					update[0][0] = "Y";
					getSqlModel().singleExecute(getQuery(23));
				} //end of if  
				else {
					update[0][0] = "N";
				} //end of else

				update[0][1] = bean.getStructureType();

				Object check[][] = getSqlModel().getSingleResult(getQuery(21),
						new Object[] { bean.getStructureType() });
				if (check != null && check.length > 0)
					result = getSqlModel().singleExecute(getQuery(17), update);// update
				else
					result = getSqlModel().singleExecute(getQuery(24), update);// insert

				// boolean result =
				// getSqlModel().singleExecute(getQuery(17),update);
				logger.info("result" + result);
				logger.info("on or off" + update[0][0]);
				
				//UPDATE HRMS_REPORTING_APPL_TYPE TABLE
				String updateAppQuery = " UPDATE HRMS_REPORTING_APPL_TYPE SET REPORTING_IS_SAMEASOFFICIAL = 'N', "
					+ " REPORTING_ISDEFAULT = ?, REPORTING_SAME_AS = 'null' WHERE REPORTING_APPL_TYPE_ABBREV = ?";
				result = getSqlModel().singleExecute(updateAppQuery, update);
				
				
				
				String defaultType = decodeStructure(bean, bean
						.getStructureType());

				if (result && String.valueOf(update[0][0]).equals("Y"))
					return "This reporting hierarchy of " + defaultType
							+ " is applied to all types of applictions. ";
				else if (result && String.valueOf(update[0][0]).equals("N"))
					return " This is not a Default structure ";
				else
					return "Error While Changing ";
			} //end of if
			else
				return "There is no hierarchy defined So define hierarchy first and then make it default.";

		} catch (Exception e) {
			e.printStackTrace();
			return "Error While Changing";
		}
	}

	/**
	 * @Method sameAs
	 * @Purpose to check whether the structure for this particular 
	 * 			type is same for any other type
	 * @param bean to pop all form properties
	 * @return String
	 **/
	public String sameAs(ReportingStructure bean) {
		try {
			Object[][] update = new Object[1][2];
			boolean result = false;
			boolean result1 = false;

			logger.info("first     " + bean.getStructureType());
			logger.info("make as  =" + bean.getSameStr());

			bean.setSameStr(bean.getSameStr());

			//update[0][0] = setSameAsStructureType(bean);

			update[0][0] = setSameAsStructureType(bean, bean.getSameStr());//abbrev
			update[0][1] = bean.getStructureType();

			if (String.valueOf(bean.getSameAsCheck()).equals("on")) {
				logger.info("status off to on ");

				getSqlModel().singleExecute(getQuery(25));

				Object check[][] = getSqlModel().getSingleResult(getQuery(21),
						new Object[] { bean.getStructureType() });

				if (check != null && check.length > 0)
					result = getSqlModel().singleExecute(getQuery(19), update);//update
				else
					result = getSqlModel().singleExecute(getQuery(20), update);//insert
				
			} //end of if
			else {
				logger.info("status on to off ");

				Object check1[][] = new Object[1][1];
				check1[0][0] = bean.getStructureType();

				result1 = getSqlModel().singleExecute(getQuery(22), check1);
			} //end of else

			
			//UPDATE HRMS_REPORTING_APPL_TYPE TABLE
			String updateAppQuery = " UPDATE HRMS_REPORTING_APPL_TYPE SET REPORTING_IS_SAMEASOFFICIAL = 'N', "
				+ " REPORTING_ISDEFAULT = 'N', REPORTING_SAME_AS = ? WHERE REPORTING_APPL_TYPE_ABBREV = ?";
			result = getSqlModel().singleExecute(updateAppQuery, update);
			
			
			String fromType = decodeStructure(bean, bean.getStructureType());
			String toType = decodeStructure(bean,bean.getSameStr());

			if (result)
				return " Reporting hierarchy of " + toType
						+ " is applied to " + fromType + ".";
			else if (result1)
				return "Record Rollback";
			else
				return "Error while changing ";

		} catch (Exception e) {
			e.printStackTrace();
			return "Error while changing";
		}
	}

	/**
	 * @Method setSameAsStructureType
	 * @Purpose to set structure type in short form
	 * @param bean to pop all form properties
	 * @param sameAs to get the structure type
	 * @return sameAsStructure
	 **/
	public String setSameAsStructureType(ReportingStructure bean, String sameAs) {
		String sameAsStructure = "";

		try {
			Object reportingStrForObj[][] = getDBValueForreportingStructure();
			if (reportingStrForObj != null && reportingStrForObj.length > 0) {
				for (int i = 0; i < reportingStrForObj.length; i++) {
					if (sameAs != null) {
						if (sameAs.trim().equals(String
								.valueOf(reportingStrForObj[i][0]).trim())) {
							sameAsStructure = String
									.valueOf(reportingStrForObj[i][0]);
						}
					}
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		/*if(sameAs.equals("Leave")){
			logger.info("reporting type is Leave");
			sameAsStructure=  "Leave";
		}	//end of if
		else if(sameAs.equals("Requisition")){
			logger.info("reporting type is Requisition");
			sameAsStructure = "Requi";
		}	//end of elseif
		else if(sameAs.equals("Cash Voucher")){
			logger.info("reporting type is Cash");
			sameAsStructure = "Cash";
		}	//end of elseif
		else if(sameAs.equals("Training")){
			logger.info("reporting type is Training");
			sameAsStructure = "Train";
		}	//end of elseif
		else if(sameAs.equals("Transfer")){
			logger.info("reporting type is Transfer");
			sameAsStructure = "Tran";
		}	//end of elseif
		else if(sameAs.equals("Suggestion")){
			logger.info("reporting type is Suggestion");
			sameAsStructure = "Sugg";
		}	//end of elseif
		else if(sameAs.equals("Help Desk")){
			logger.info("reporting type is Help Desk");
			sameAsStructure = "Help";
		}	//end of elseif
		else if(sameAs.equals("LTC Advance")){
			logger.info("reporting type is LTC Advance");
			sameAsStructure = "LTC";
		}	//end of elseif
		else if(sameAs.equals("Festival Advance")){
			logger.info("reporting type is Festival Advance");
			sameAsStructure = "Festi";
		}	//end of elseif
		else if(sameAs.equals("Other Advance")){
			logger.info("reporting type is Other Advance");
			sameAsStructure = "Other";
		}	//end of elseif
		else if(sameAs.equals("Deputation")){
			logger.info("reporting type is Deputation");
			sameAsStructure = "Deput";
		}	//end of elseif
		else if(sameAs.equals("HBA")){
			logger.info("reporting type is HBA");
			sameAsStructure = "HBA";
		}	//end of elseif
		else if(sameAs.equals("GPF")){
			logger.info("reporting type is GPF");
			sameAsStructure = "GPF";
		}	//end of elseif
		else if(sameAs.equals("Children Tution Fee")){
			logger.info("reporting type is Children Tution Fee");
			sameAsStructure = "CTF";
		}	//end of elseif
		else if(sameAs.equals("Medical Advance")){
			logger.info("reporting type is Medical Advance");
			sameAsStructure = "Medi";
		}	//end of elseif
		else if(sameAs.equals("Travel")){
			logger.info("reporting type is Travel");
			sameAsStructure = "TYD";
		}//end of elseif
		else if(sameAs.equals("Appraisal")){
			logger.info("reporting type is Appraisal");
			sameAsStructure = "Appra";
		}	//end of elseif
		else if(sameAs.equals("Conference")){
			logger.info("reporting type is Conference");
			sameAsStructure = "Confere";
		}	//end of elseif
		else if(sameAs.equals("Loan")){
			logger.info("reporting type is Loan");
			sameAsStructure = "Loan";
		}	//end of elseif
		else if(sameAs.equals("Asset")){
			logger.info("reporting type is Asset");
			sameAsStructure = "Asset";
		}	//end of elseif
		else if(sameAs.equals("Purchase")){
			logger.info("reporting type is Purchase");
			sameAsStructure = "Purchase";
		}	//end of elseif 
		else if(sameAs.equals("Cash Process")){
			logger.info("reporting type is Cash Process");
			sameAsStructure = "Cash Process";
		}	//end of elseif
		else if(sameAs.equals("Recruitment")){
			logger.info("reporting type is Recruitment");
			sameAsStructure = "Recruitment";
		}	//end of elseif
		 */
		return sameAsStructure;
	}

	/**
	 * @Method decodeStructure
	 * @Purpose to set structure type in long form
	 * @param bean to pop all form properties
	 * @param reportingType to get the structure type
	 * @return structureType
	 **/
	public String decodeStructure(ReportingStructure bean, String reportingType) {
		//String reportingType = bean.getSameStr();
		String structureType = "";

		try {
			Object reportingStrForObj[][] = getDBValueForreportingStructure();
			if (reportingStrForObj != null && reportingStrForObj.length > 0) {
				for (int i = 0; i < reportingStrForObj.length; i++) {
					if (reportingType.equals(String
							.valueOf(reportingStrForObj[i][0]))) {
						structureType = String
								.valueOf(reportingStrForObj[i][1]);
					}
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		/*if(reportingType.equals("Leave")){
			logger.info("reporting type is Leave");
			structureType = "Leave";
		}	//end of if
		else if(reportingType.equals("Requi")){
			logger.info("reporting type is Requisition");
			structureType = "Requisition";
		}	//end of elseif
		else if(reportingType.equals("Cash")){
			logger.info("reporting type is Cash");
			structureType = "Cash Voucher";
		}	//end of elseif
		else if(reportingType.equals("Train")){
			logger.info("reporting type is Training");
			structureType = "Training";
		}	//end of elseif
		else if(reportingType.equals("Tran")){
			logger.info("reporting type is Transfer");
			structureType = "Transfer";
		}	//end of elseif
		else if(reportingType.equals("Sugg")){
			logger.info("reporting type is Suggestion");
			structureType = "Suggestion";
		}	//end of elseif
		else if(reportingType.equals("Help")){
			logger.info("reporting type is Help Desk");
			structureType = "Help Desk";
		}	//end of elseif
		else if(reportingType.equals("LTC")){
			logger.info("reporting type is LTC Advance");
			structureType = "LTC Advance";
		}	//end of elseif
		else if(reportingType.equals("Festi")){
			logger.info("reporting type is Festival Advance");
			structureType = "Festival Advance";
		}	//end of elseif
		else if(reportingType.equals("Other")){
			logger.info("reporting type is Other Advance");
			structureType = "Other Advance";
		}	//end of elseif
		else if(reportingType.equals("Deput")){
			logger.info("reporting type is Deputation");
			structureType = "Deputation";
		}	//end of elseif
		else if(reportingType.equals("HBA")){
			logger.info("reporting type is HBA");
			structureType = "HBA";
		}	//end of elseif
		else if(reportingType.equals("GPF")){
			logger.info("reporting type is GPF");
			structureType = "GPF";
		}	//end of elseif
		else if(reportingType.equals("CTF")){
			logger.info("reporting type is Children Tution Fee");
			structureType = "Children Tution Fee";
		}	//end of elseif
		else if(reportingType.equals("Medi")){
			logger.info("reporting type is Medical Advance");
			structureType = "Medical Advance";
		}	//end of elseif
		else if(reportingType.equals("TYD")){
			logger.info("reporting type is Travel");
			structureType = "Travel";
		}	//end of elseif
		else if(reportingType.equals("Appra")){
			logger.info("reporting type is Appraisal");
			structureType = "Appraisal";
		}	//end of elseif
		else if(reportingType.equals("Confere")){
			logger.info("reporting type is Conference");
			structureType = "Conference";
		}	//end of elseif
		else if(reportingType.equals("Loan")){
			logger.info("reporting type is Loan");
			structureType = "Loan";
		}	//end of elseif
		else if(reportingType.equals("Asset")){
			logger.info("reporting type is Asset");
			structureType = "Asset";
		}	//end of elseif
		else if(reportingType.equals("Purchase")){
			logger.info("reporting type is Purchase");
			structureType = "Purchase";
		}	//end of elseif 
		else if(reportingType.equals("Cash Process")){
			logger.info("reporting type is Cash Process");
			structureType = "Cash Process";
		}	//end of elseif
		else if(reportingType.equals("Recruitment")){
			logger.info("reporting type is Recruitment");
			structureType = "Recruitment";
		}	//end of elseif
		else if(reportingType.equals("ExtraDayBenefit")){
			logger.info("reporting type is Extra Day Benefit");
			structureType = "Extra Day Benefit";
		}	//end of elseif
		else if(reportingType.equals("Outdoor")){
			logger.info("reporting type is Outdoor Visit");
			structureType = "Outdoor Visit";
		}	//end of elseif
		 */
		return structureType;
	}

	/**
	 * @Method getDefinedStructureNames
	 * @Purpose to retrieve all the defined structures
	 * @param bean to pop all form properties
	 **/
	public void getDefinedStructureNames(ReportingStructure bean) {
		logger
				.info("in getDefinedStructureNames -----------------------------");
		HashMap structureMap = new HashMap();
		String structureType = setSameAsStructureType(bean, bean
				.getStructureType());
 
		if (structureType.equals("")) {
			structureType = bean.getStructureType();
		} //end of if

		Object[] reportingType = { structureType };
		logger.info("bean.getReportingType()  " + bean.getStructureType());

		Object[][] getSelectedStructure = getSqlModel().getSingleResult(
				getQuery(29), reportingType);

		if (getSelectedStructure != null && getSelectedStructure.length != 0) {
			for (int i = 0; i < getSelectedStructure.length; i++) {
				structureMap.put(String.valueOf(getSelectedStructure[i][1]),
						String.valueOf(getSelectedStructure[i][0]));
			} //end of for loop
			bean.setStructureMap(structureMap);
			bean.setStructureFlag("true");

			logger.info("in if condition  " + bean.getStructureFlag());
		} //end of if
		else {
			bean.setStructureFlag("false");

			logger.info("in else condition  " + bean.getStructureFlag());
		} //end of else
		logger.info("structure " + bean.getSameStr());
	}

	/**
	 * @Method getOrdinalFor
	 * @Purpose to append the ordinal with  serial number
	 * @param value value to which ordinal will be appended
	 * @return String
	 **/
	public String getOrdinalFor(int value) {
		int hundredRemainder = value % 100;

		if (hundredRemainder >= 10 && hundredRemainder <= 20) {
			return "th";
		} //end of if

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
		} //end of switch
	}

	public void setDropdownValue(ReportingStructure bean) {
 
		try {
			Object reportingStrForObj[][] = getDBValueForreportingStructure();
			TreeMap map_1 = new TreeMap();
			if (reportingStrForObj != null && reportingStrForObj.length > 0) {
				for (int i = 0; i < reportingStrForObj.length; i++) {

					map_1.put(String.valueOf(reportingStrForObj[i][0]), String
							.valueOf(reportingStrForObj[i][1]));
				}

				bean.setTmap(map_1);
 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Object[][] getDBValueForreportingStructure() {
		Object reportingStrForObj[][] = null;
		try {

			String selectQuery = " SELECT REPORTING_APPL_TYPE_ABBREV,REPORTING_APPL_TYPE_NAME,REPORTING_APPL_TYPE_CODE FROM HRMS_REPORTING_APPL_TYPE "
					+ " ORDER BY REPORTING_APPL_TYPE_CODE ";
			reportingStrForObj = getSqlModel().getSingleResult(selectQuery);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return reportingStrForObj;
	}
	
	/**
	 * @author REEBA JOSEPH
	 * @param bean
	 * @param toEmpId 
	 * @param fromEmpId 
	 * @param structureType 
	 * @param applTypeCode 
	 * @return
	 */
	public boolean saveManagerHierarchy(ReportingStructure bean,
			String[] fromEmpId, String[] toEmpId, String structureType, Object[][] applTypeCode) {
		boolean result = false;
		try {
			logger.info("TYPE " + structureType);
			
			logger.info("APPL TYPE CODE===="+applTypeCode[0][0]);
			logger.info("getManagerRecord==== " + bean.getManagerRecord());
			logger.info("getDefaultCheck==== " + bean.getDefaultCheck());
			logger.info("getSameAsCheck==== " + bean.getSameAsCheck());
			
			Object[][] updateObj = new Object[1][5];
			
			if (String.valueOf(bean.getManagerRecord()).equals("on")) {
				updateObj[0][0] = "Y"; //REPORTING_IS_SAMEASOFFICIAL
				if(bean.getManagerLevel().trim().equals(""))
					updateObj[0][1] = "1"; //REPORTING_LEVELS
				else
					updateObj[0][1] = bean.getManagerLevel().trim(); //REPORTING_LEVELS
			} //end of if  
			else {
				updateObj[0][0] = "N"; //REPORTING_IS_SAMEASOFFICIAL
				updateObj[0][1] = ""; //REPORTING_LEVELS
			} //end of else
			
			if (String.valueOf(bean.getDefaultCheck()).equals("on")) {
				updateObj[0][2] = "Y"; //REPORTING_ISDEFAULT
			}else
				updateObj[0][2] = "N"; //REPORTING_ISDEFAULT
			if (String.valueOf(bean.getSameAsCheck()).equals("on")) {
				updateObj[0][3] = bean.getSameStr(); //REPORTING_SAME_AS
			}else
				updateObj[0][3] = ""; //REPORTING_SAME_AS
					
			updateObj[0][4] = applTypeCode[0][0]; //REPORTING_APPL_TYPE_CODE
			
			//UPDATE HRMS_REPORTING_APPL_TYPE TABLE
			String updateAppQuery = " UPDATE HRMS_REPORTING_APPL_TYPE SET REPORTING_IS_SAMEASOFFICIAL = ?, REPORTING_LEVELS =?, "
				+ " REPORTING_ISDEFAULT = ?, REPORTING_SAME_AS = ? WHERE REPORTING_APPL_TYPE_CODE = ? ";
			result = getSqlModel().singleExecute(updateAppQuery, updateObj);
			
			if(result){
				//DELETE FROM HRMS_REPORTING_EXCEPTIONS TABLE
				String deleteQuery = "DELETE FROM HRMS_REPORTING_EXCEPTIONS WHERE REPORTING_APP_TYPE = "+applTypeCode[0][0];
				getSqlModel().singleExecute(deleteQuery);
				
				//INSERT INTO HRMS_REPORTING_EXCEPTIONS TABLE
				String insertExceptions = " INSERT INTO HRMS_REPORTING_EXCEPTIONS (REPORTING_APP_TYPE, REPORTING_FROM_EXCEPTION, REPORTING_TO )"
					+ " VALUES (?, ?, ?) ";
				Object[][] tableData = new Object[fromEmpId.length][3]; 
				for (int i = 0; i < fromEmpId.length; i++) {
					tableData[i][0] = applTypeCode[0][0];
					tableData[i][1] = fromEmpId[i];
					tableData[i][2] = toEmpId[i];

					logger.info("tableData ["+i+"][0]======= "+ tableData[i][0]);
					logger.info("tableData ["+i+"][1]======= "+ tableData[i][1]);
					logger.info("tableData ["+i+"][2]======= "+ tableData[i][2]);

				} //end of for loop
				result = getSqlModel().singleExecute(insertExceptions, tableData);
			}//end of if
						
			logger.info("result11=======" + result);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}//end of try-catch block
	}//end of method saveManagerHierarchy
	
	/**
	 * @author REEBA JOSEPH
	 * @param bean
	 * @param fromEmpIdIt
	 * @param fromEmpTokenIt
	 * @param fromEmpNameIt
	 * @param toEmpIdIt
	 * @param toEmpTokenIt
	 * @param toEmpNameIt
	 * @return
	 */
	public ArrayList addExceptionEmployees(ReportingStructure bean, String[] fromEmpIdIt,
			String[] fromEmpTokenIt, String[] fromEmpNameIt,
			String[] toEmpIdIt, String[] toEmpTokenIt,
			String[] toEmpNameIt) {
		logger.info("inside addExceptionEmployees");

		ArrayList list = new ArrayList();
		int count = 0;

		if (fromEmpIdIt != null && fromEmpIdIt.length != 0) {
			for (int i = 0; i < fromEmpIdIt.length; i++) {
				ReportingStructure bean1 = new ReportingStructure();

				if (!bean.getExcepSrNo().equals("")
						&& i == Integer.parseInt(bean.getExcepSrNo()) - 1) {
					bean1.setFromEmpIdIt(bean.getFromEmpCode());
					bean1.setFromEmpTokenIt(bean.getFromEmpToken());
					bean1.setFromEmpNameIt(bean.getFromEmpName());
					bean1.setToEmpIdIt(bean.getToEmpCode());
					bean1.setToEmpTokenIt(bean.getToEmpToken());
					bean1.setToEmpNameIt(bean.getToEmpName());
				} //end of if statement
				else {
					bean1.setFromEmpIdIt(fromEmpIdIt[i]);
					bean1.setFromEmpTokenIt(fromEmpTokenIt[i]);
					bean1.setFromEmpNameIt(fromEmpNameIt[i]);
					bean1.setToEmpIdIt(toEmpIdIt[i]);
					http: //localhost:1487/pims/pages/images/recruitment/search2.gif
					bean1.setToEmpTokenIt(toEmpTokenIt[i]);
					bean1.setToEmpNameIt(toEmpNameIt[i]);
				} //end of else statement

				String srNo = ""+(i + 1);
				bean1.setExcepSrNoIterator(srNo);

				count = i + 1;
				list.add(bean1);
			} //end of for loop
		} //end of if
		if (!bean.getFromEmpCode().equals("") && bean.getExcepSrNo().equals("")) {
			bean.setFromEmpIdIt(bean.getFromEmpCode());
			bean.setFromEmpTokenIt(bean.getFromEmpToken());
			bean.setFromEmpNameIt(bean.getFromEmpName());
			bean.setToEmpIdIt(bean.getToEmpCode());
			bean.setToEmpTokenIt(bean.getToEmpToken());
			bean.setToEmpNameIt(bean.getToEmpName());
			
			String srNo = ""+(count + 1);

			bean.setExcepSrNoIterator(srNo);

			list.add(bean);
		} //end of if
		bean.setExceptionEmpList(list);
		
		

		return list;
	}
	
	/**
	 * @author REEBA JOSEPH
	 * @param bean
	 * @param srNo
	 * @param fromEmpIdIt
	 * @param fromEmpTokenIt
	 * @param fromEmpNameIt
	 * @param toEmpIdIt
	 * @param toEmpTokenIt
	 * @param toEmpNameIt
	 * @return
	 */
	public boolean deleteEmployeeExceptions(ReportingStructure bean, String srNo, String[] fromEmpIdIt,
			String[] fromEmpTokenIt, String[] fromEmpNameIt,
			String[] toEmpIdIt, String[] toEmpTokenIt,
			String[] toEmpNameIt) {
		logger.info("in side delete");

		boolean result = false;

		if (fromEmpIdIt != null && fromEmpIdIt.length != 0) {
			ArrayList list = new ArrayList();
			ArrayList list1 = new ArrayList();
			for (int i = 0; i < fromEmpIdIt.length; i++) {
				ReportingStructure bean1 = new ReportingStructure();
				bean1.setFromEmpIdIt(fromEmpIdIt[i]);
				bean1.setFromEmpTokenIt(fromEmpTokenIt[i]);
				bean1.setFromEmpNameIt(fromEmpNameIt[i]);
				bean1.setToEmpIdIt(toEmpIdIt[i]);
				bean1.setToEmpTokenIt(toEmpTokenIt[i]);
				bean1.setToEmpNameIt(toEmpNameIt[i]);

				list.add(bean1);
			} //end of for loop
			list.remove(Integer.parseInt(srNo) - 1);
			//bean.setExceptionEmpList(list);
			for (int i = 0; i < list.size(); i++) {
				ReportingStructure bean1 = (ReportingStructure) list.get(i);

				String showSrNo = ""+(i + 1);
				bean1.setExcepSrNoIterator(showSrNo);
				logger.info("showSrNo-----------" + showSrNo);
				list1.add(bean1);
			} //end of for loop
			bean.setExceptionEmpList(list1);
		} //end of if
		return result;
	}

	/**
	 * @author REEBA JOSEPH
	 * @param reportingStr
	 * @param applTypeCode
	 */
	public void setManagerHierarchies(ReportingStructure reportingStr, Object[][] applTypeCode) {
		if(applTypeCode!=null){
			String hdrQuery = " SELECT REPORTING_APPL_TYPE_CODE, REPORTING_IS_SAMEASOFFICIAL, REPORTING_LEVELS, REPORTING_ISDEFAULT, "
				+ " REPORTING_SAME_AS FROM HRMS_REPORTING_APPL_TYPE WHERE REPORTING_APPL_TYPE_CODE = "+applTypeCode[0][0];
			Object[][] hdrObj = getSqlModel().getSingleResult(hdrQuery);
	
			if(hdrObj != null && hdrObj.length > 0){
				if(String.valueOf(hdrObj[0][1]).equals("Y")){
					reportingStr.setManagerRecord("true");
					reportingStr.setManagerLevel(String.valueOf(hdrObj[0][2]));
				}else{
					reportingStr.setManagerRecord("false");
					reportingStr.setManagerLevel("");
				}
				if(String.valueOf(hdrObj[0][3]).equals("Y")){
					reportingStr.setDefaultCheck("true");
				}else{
					reportingStr.setDefaultCheck("false");
				}
				logger.info("Same as check===="+String.valueOf(hdrObj[0][4]));
				if(!String.valueOf(hdrObj[0][4]).equals("null") && !String.valueOf(hdrObj[0][4]).equals("")){
					reportingStr.setSameAsCheck("true");
					reportingStr.setSameStr(Utility.checkNull(String.valueOf(hdrObj[0][4])));
				}else{
					reportingStr.setSameAsCheck("false");
					reportingStr.setSameStr("");
				}
			}
			
			if(String.valueOf(hdrObj[0][1]).equals("Y")){
				String excepQuery = " SELECT FROMEMP.EMP_FNAME||' '||FROMEMP.EMP_MNAME||' '||FROMEMP.EMP_LNAME, FROMEMP.EMP_TOKEN, "
					+ " REPORTING_FROM_EXCEPTION, TOEMP.EMP_FNAME||' '||TOEMP.EMP_MNAME||' '||TOEMP.EMP_LNAME, TOEMP.EMP_TOKEN, REPORTING_TO "
					+ " FROM HRMS_REPORTING_EXCEPTIONS "
					+ " INNER JOIN HRMS_EMP_OFFC FROMEMP ON (FROMEMP.EMP_ID = REPORTING_FROM_EXCEPTION) "
					+ " INNER JOIN HRMS_EMP_OFFC TOEMP ON (TOEMP.EMP_ID = REPORTING_TO) "
					+ " WHERE REPORTING_APP_TYPE = "+applTypeCode[0][0];
				Object[][] excepObj = getSqlModel().getSingleResult(excepQuery);
				
				if(excepObj != null && excepObj.length > 0){
					ArrayList list = new ArrayList();
					for (int i = 0; i < excepObj.length; i++) {
						ReportingStructure bean = new ReportingStructure();
						bean.setFromEmpNameIt(Utility.checkNull(String.valueOf(excepObj[i][0])));
						bean.setFromEmpTokenIt(Utility.checkNull(String.valueOf(excepObj[i][1])));
						bean.setFromEmpIdIt(Utility.checkNull(String.valueOf(excepObj[i][2])));
						bean.setToEmpNameIt(Utility.checkNull(String.valueOf(excepObj[i][3])));
						bean.setToEmpTokenIt(Utility.checkNull(String.valueOf(excepObj[i][4])));
						bean.setToEmpIdIt(Utility.checkNull(String.valueOf(excepObj[i][5])));
						bean.setExcepSrNoIterator(""+(i+1));
						list.add(bean);
					}
					reportingStr.setExceptionEmpList(list);
				}
			}
		}
		
	}
	
	public Object[][] getEmployeeData(String empCode)
	{
		Object data[][]=null;
		String query="SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),	NVL(CENTER_NAME,' '),NVL(RANK_NAME,' ') , EMP_ID FROM HRMS_EMP_OFFC  	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK 	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE WHERE EMP_ID="+empCode;
		data=getSqlModel().getSingleResult(query);
		return data;
	}
	
	public Object[][] getManager(String empCode, String applicationType) {

		Object managerEmpCodeObj[][] = null;

		try {

			String query = " SELECT NVL(EMP_REPORTING_OFFICER,0),0,0,0 FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ empCode;
			managerEmpCodeObj = getSqlModel().getSingleResult(query);

			if (managerEmpCodeObj != null && managerEmpCodeObj.length > 0) {

				if (!String.valueOf(managerEmpCodeObj[0][0]).equals("0")) {
					Object[][] isExceptionObj = isExceptionalEmployee(String
							.valueOf(managerEmpCodeObj[0][0]), applicationType);
					if (isExceptionObj != null && isExceptionObj.length > 0) {
						return isExceptionObj;
					} else {
						return managerEmpCodeObj;
					}
				} else {
					managerEmpCodeObj[0][0] = null;
					managerEmpCodeObj[0][1] = null;
					managerEmpCodeObj[0][2] = null;
					managerEmpCodeObj[0][3] = null;
				}
				return managerEmpCodeObj;
			} else {
				managerEmpCodeObj[0][0] = null;
				managerEmpCodeObj[0][1] = null;
				managerEmpCodeObj[0][2] = null;
				managerEmpCodeObj[0][3] = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return managerEmpCodeObj;
	}
	
	private Object[][] isExceptionalEmployee(String empCode,
			String applicationType) {
		Object[][] isExceptionObj = null;
		String isExceptionQuery = "SELECT REPORTING_TO,0,0,0 FROM HRMS_REPORTING_EXCEPTIONS "
				+ " INNER JOIN HRMS_REPORTING_APPL_TYPE ON (HRMS_REPORTING_APPL_TYPE.REPORTING_APPL_TYPE_CODE=HRMS_REPORTING_EXCEPTIONS.REPORTING_APP_TYPE) "
				+ " WHERE REPORTING_FROM_EXCEPTION="
				+ empCode
				+ " AND REPORTING_APPL_TYPE_ABBREV='" + applicationType + "'";
		isExceptionObj = getSqlModel().getSingleResult(isExceptionQuery);
		return isExceptionObj;
	}


	
}
