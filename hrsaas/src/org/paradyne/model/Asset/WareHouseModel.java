package org.paradyne.model.Asset;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.Asset.WareHouseMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author mangeshj Date 30/07/2008 WareHouseModel class to write the business
 *         logic to add, update, view the Warehouse
 */
public class WareHouseModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/*
	 * method name : addBranch purpose : to add the branch in the branch list
	 * return type : void parameter : WareHouseMaster instance, String
	 * []branchCode,String [] branchName
	 */
	public void addBranch(WareHouseMaster bean, String[] branchCode,
			String[] branchName) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (branchCode != null) {
			/*
			 * sets the previously present branches in the list
			 * 
			 */
			for (int i = 0; i < branchCode.length; i++) {
				WareHouseMaster bean1 = new WareHouseMaster();
				bean1.setBranchCodeIterator(branchCode[i]);
				bean1.setBranchNameIterator(branchName[i]);
				tableList.add(bean1);
			} // end of for loop
			bean.setBranchCodeIterator(bean.getBranchCode());
			bean.setBranchNameIterator(bean.getBranchName());
			tableList.add(bean);
		} // end of if
		else {
			logger.info("u r in else in model...!!");
			bean.setBranchCodeIterator(bean.getBranchCode());
			bean.setBranchNameIterator(bean.getBranchName());
			tableList.add(bean);
		} // end of else
		bean.setBranchList(tableList);
		bean.setTableLength(String.valueOf(tableList.size()));
		logger.info("table length...!!" + tableList.size());
	}

	/*
	 * method name : addCentralizeBranch purpose : to add the Centralized Branch
	 * in the branch list return type : void parameter : WareHouseMaster
	 * instance
	 */
	public void addCentralizeBranch(WareHouseMaster bean) {
		ArrayList<Object> tableList = new ArrayList<Object>();

		bean.setBranchCodeIterator(bean.getCentralizeBranchCode());
		bean.setBranchNameIterator(bean.getCentralizeBranchName());
		tableList.add(bean);
		bean.setBranchList(tableList);
		bean.setTableLength(String.valueOf(tableList.size()));
	}

	/*
	 * method name : showDetails purpose : to show the details of the selected
	 * warehouse return type : void parameter : WareHouseMaster instance
	 */
	public void showDetails(WareHouseMaster bean) {
		try {

			String query = "SELECT WAREHOUSE_CODE,WAREHOUSE_NAME,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME, "
					+ "WAREHOUSE_RESPONSIBLE_PERSON,EMP_TOKEN,HRMS_EMP_ADDRESS.ADD_MOBILE,HRMS_EMP_ADDRESS.ADD_EMAIL,WAREHOUSE_ISACTIVE FROM HRMS_WAREHOUSE_MASTER "
					+ "INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_WAREHOUSE_MASTER.WAREHOUSE_RESPONSIBLE_PERSON) "
					+ "INNER JOIN HRMS_EMP_ADDRESS ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_RESPONSIBLE_PERSON = HRMS_EMP_ADDRESS.EMP_ID AND ADD_TYPE='P' ) "
					+ " WHERE WAREHOUSE_CODE=" + bean.getWareHousecode();
			Object[][] warehouseData = getSqlModel().getSingleResult(query);

			if (warehouseData != null && warehouseData.length > 0) {
				// {"wareHousecode","wareHouseName","respName","respCode","respToken"};
				bean.setWareHousecode(checkNull(String
						.valueOf(warehouseData[0][0])));
				bean.setWareHouseName(checkNull(String
						.valueOf(warehouseData[0][1])));
				bean
						.setRespName(checkNull(String
								.valueOf(warehouseData[0][2])));
				bean
						.setRespCode(checkNull(String
								.valueOf(warehouseData[0][3])));
				bean
						.setRespToken(checkNull(String
								.valueOf(warehouseData[0][4])));
				bean
						.setMobileno(checkNull(String
								.valueOf(warehouseData[0][5])));
				bean.setEmailid(checkNull(String.valueOf(warehouseData[0][6])));
				bean.setIsActive(String.valueOf(warehouseData[0][7]));

			}

			Object[][] isCentralized = getSqlModel()
					.getSingleResult(
							"SELECT WAREHOUSE_IS_CENTRALIZE FROM HRMS_WAREHOUSE_MASTER WHERE WAREHOUSE_CODE="
									+ bean.getWareHousecode());

			if (String.valueOf(isCentralized[0][0]).equals("Y")) {
				bean.setCentralizeFlag("true");
				bean.setBranchFlag("false");
			} // end of if
			else {
				bean.setCentralizeFlag("false");
				bean.setBranchFlag("true");
			} // end of else
			Object[] wareHouseCode = new Object[1];
			wareHouseCode[0] = bean.getWareHousecode();
			Object[][] branchData = getSqlModel().getSingleResult(getQuery(5),
					wareHouseCode);
			ArrayList<Object> tableList = new ArrayList<Object>();
			/*
			 * set the retrieved branch data in the list
			 * 
			 */
			for (int i = 0; i < branchData.length; i++) {
				WareHouseMaster bean1 = new WareHouseMaster();
				bean1.setBranchCodeIterator(String.valueOf(branchData[i][0]));
				bean1.setBranchNameIterator(String.valueOf(branchData[i][1]));
				tableList.add(bean1);
			} // end of for loop
			bean.setBranchList(tableList);
			bean.setTableLength(String.valueOf(tableList.size()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception in showDetails()=" + e);
		}

	}

	/*
	 * method name : save purpose : to add the new warehouse with its branches
	 * return type : boolean parameter : WareHouseMaster instance, String []
	 * branchCode
	 */
	public boolean save(WareHouseMaster bean, String[] branchCode) {

		logger.info("inside save method CentralizeFlag"
				+ bean.getCentralizeFlag());
		boolean result = false;
		Object[][] addData = new Object[1][4];
		addData[0][0] = bean.getWareHouseName();
		addData[0][1] = bean.getRespCode();
		addData[0][3] = bean.getIsActive();
		if (bean.getCentralizeFlag().equals("true"))
			addData[0][2] = "Y";
		else
			addData[0][2] = "N";

		result = getSqlModel().singleExecute(getQuery(1), addData); // insert
		// into the
		// HRMS_WAREHOUSE_MASTER
		if (result) {
			Object[][] maxwareHouseCode = getSqlModel().getSingleResult(
					"SELECT MAX(WAREHOUSE_CODE) FROM HRMS_WAREHOUSE_MASTER");
			bean.setWareHousecode(String.valueOf(maxwareHouseCode[0][0]));

			result = getSqlModel().singleExecute(getQuery(3), maxwareHouseCode);
			/*
			 * insert the branches in the HRMS_WAREHOUSE_BRANCH
			 * 
			 */
			for (int i = 0; i < branchCode.length; i++) {
				Object[][] branchdata = new Object[1][2];
				branchdata[0][0] = bean.getWareHousecode();
				branchdata[0][1] = branchCode[i];

				result = getSqlModel().singleExecute(getQuery(4), branchdata);
			} // end of for loop

		} // end of if
		return result;
	}

	/*
	 * method name : update purpose : to update the warehouse with its branches
	 * return type : boolean parameter : WareHouseMaster instance, String []
	 * branchCode
	 */
	public boolean update(WareHouseMaster bean, String[] branchCode) {
		boolean result = false;
		Object[][] updateData = new Object[1][5];
		updateData[0][0] = bean.getWareHouseName();
		updateData[0][1] = bean.getRespCode();

		if (bean.getCentralizeFlag().equals("true"))
			updateData[0][2] = "Y";
		else
			updateData[0][2] = "N";

		updateData[0][3] = bean.getIsActive();
		updateData[0][4] = bean.getWareHousecode();

		result = getSqlModel().singleExecute(getQuery(2), updateData);

		Object[][] wareHouseCode = new Object[1][1];
		wareHouseCode[0][0] = bean.getWareHousecode();

		if (result)
			result = getSqlModel().singleExecute(getQuery(3), wareHouseCode);
		if (result) {
			for (int i = 0; i < branchCode.length; i++) {
				Object[][] branchdata = new Object[1][2];
				branchdata[0][0] = bean.getWareHousecode();
				branchdata[0][1] = branchCode[i];
				result = getSqlModel().singleExecute(getQuery(4), branchdata);
			} // end of for loop
		} // end of if
		return result;
	}

	/*
	 * method name : deleteBranch purpose : to delete the branch from list
	 * return type : void parameter : WareHouseMaster instance, String[]
	 * branchCode,String[] branchName
	 */
	public void deleteBranch(WareHouseMaster bean, String[] branchCode,
			String[] branchName) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		for (int i = 0; i < branchName.length; i++) {
			WareHouseMaster bean1 = new WareHouseMaster();
			/*
			 * remove the selected branch
			 */
			if (!bean.getParaId().equals(String.valueOf(i + 1))) {
				bean1.setBranchCodeIterator(branchCode[i]);
				bean1.setBranchNameIterator(branchName[i]);
				tableList.add(bean1);
			} // end of if
		} // end of for loop
		bean.setBranchList(tableList);
		bean.setTableLength(String.valueOf(tableList.size()));
	}

	/*
	 * method name : deleteRecord purpose : to delete the warehouse with all its
	 * details return type : boolean parameter : WareHouseMaster instance
	 */
	public boolean deleteRecord(WareHouseMaster bean) {
		boolean result = false;
		Object[][] wareHouseCode = new Object[1][1];
		wareHouseCode[0][0] = bean.getWareHousecode();

		Object sqlQuery[] = new Object[2];
		sqlQuery[0] = String.valueOf(getQuery(3));
		sqlQuery[1] = String.valueOf(getQuery(6));

		java.util.Vector dataVector = new Vector();

		dataVector.add(wareHouseCode);
		dataVector.add(wareHouseCode);

		// result = getSqlModel().singleExecute(getQuery(6), wareHouseCode);
		// if(result){
		// result = getSqlModel().singleExecute(getQuery(3),wareHouseCode);
		// } // end of if

		result = getSqlModel().multiExecute(sqlQuery, dataVector);
		logger.info("resulttttttttttttttttt" + result);
		return result;
	}

	/*
	 * method name : showBranchList purpose : to show the branches in the list
	 * return type : void parameter : WareHouseMaster instance
	 */
	public void showBranchList(WareHouseMaster bean, String[] branchCode,
			String[] branchName) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		for (int i = 0; i < branchName.length; i++) {
			WareHouseMaster bean1 = new WareHouseMaster();
			bean1.setBranchCodeIterator(branchCode[i]);
			bean1.setBranchNameIterator(branchName[i]);
			tableList.add(bean1);

		} // end of for loop
		bean.setBranchList(tableList);
		bean.setTableLength(String.valueOf(tableList.size()));
	}

	/*
	 * method name : report purpose : to show the PDF report of the selected
	 * warehouse return type : void parameter : WareHouseMaster instance,
	 * HttpServletRequest request, HttpServletResponse response
	 */
	public void report(WareHouseMaster bean, HttpServletRequest request,
			HttpServletResponse response) {

		String s = "\n Warehouse Master Report\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				s);
		String query = "SELECT rownum,Nvl(WAREHOUSE_NAME,' '),nvl(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME,' '),"
				+ " Decode(WAREHOUSE_IS_CENTRALIZE,'Y', 'YES', 'N' ,'NO'),WAREHOUSE_CODE FROM HRMS_WAREHOUSE_MASTER "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_WAREHOUSE_MASTER.WAREHOUSE_RESPONSIBLE_PERSON) ";

		Object[][] data = getSqlModel().getSingleResult(query);
		String dateQuery = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object[][] dateObject = getSqlModel().getSingleResult(dateQuery);

		Object actualData[][] = new Object[2][2];

		if (data.length > 0) {
			rg.addFormatedText(s, 6, 0, 1, 0);
			rg.addText("Date:" + String.valueOf(dateObject[0][0]), 2, 2, 0);

			rg.addText("\n", 2, 2, 0);
			int cellwidth[] = { 10, 50 };
			int alignment[] = { 1, 0 };

			int[] bcellWidth = { 20, 80 };
			int[] bcellAlign = { 0, 0 };
			rg.addText("\n", 2, 2, 0);
			for (int i = 0; i < data.length; i++) {
				actualData[0][0] = String.valueOf(data[i][0])
						+ ") Warehouse Name";
				actualData[0][1] = ": " + String.valueOf(data[i][1]);
				actualData[1][0] = "Responsible Person ";
				actualData[1][1] = ": " + String.valueOf(data[i][2]);

				/*
				 * actualData[2][0] = "Warehouse Type";
				 * 
				 * if(String.valueOf(data[i][3]).equals("YES")){
				 * actualData[2][1] = ": Centralize Warehouse"; } // end of if
				 * else actualData[2][1] = ": Branch wise Warehouse";
				 */

				String warquery = "SELECT rownum,CENTER_NAME FROM HRMS_WAREHOUSE_BRANCH "
						+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_WAREHOUSE_BRANCH.WAREHOUSE_BRANCH)where WAREHOUSE_CODE="
						+ String.valueOf(data[i][4]);
				Object[][] branchData = getSqlModel().getSingleResult(warquery);
				String colnames1[] = { "Sr.No", "Branch Name" };
				rg.tableBodyNoBorder(actualData, bcellWidth, bcellAlign);

				if (branchData.length > 0) {
					rg.addText("\nBranch List:", 2, 0, 0);
					rg.tableBody(colnames1, branchData, cellwidth, alignment);
				} // end of if
				rg.addText("\n\n", 2, 2, 0);
			} // end of for loop
		} // end of if
		else {
			rg.addFormatedText("\n\n No records to display ", 0, 0, 1, 0);
		} // end of else
		rg.createReport(response);
	}

	/*
	 * method name : checkNull purpose : check the null value return type :
	 * String parameter : String result
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void Data(WareHouseMaster bean, HttpServletRequest request) {

		try {
			// Object [][] repData = getSqlModel().getSingleResult(getQuery(6));
			Object[][] obj = getSqlModel().getSingleResult(getQuery(7));
			String[] pageIndex = Utility.doPaging(bean.getMyPage(), obj.length,
					20);
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
				bean.setMyPage("1");
			ArrayList<Object> list = new ArrayList<Object>();
			if (obj != null && obj.length > 0) {

				// for (int i = 0; i < obj.length; i++) {
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {

					WareHouseMaster bean1 = new WareHouseMaster();
					bean1
							.setWareHousecode(checkNull(String
									.valueOf(obj[i][0])));
					bean1
							.setWareHouseName(checkNull(String
									.valueOf(obj[i][1])));
					bean1.setRespName(checkNull(String.valueOf(obj[i][2])));
					bean1.setIsActive(checkNull(String.valueOf(obj[i][5])));

					list.add(bean1);
				}

				bean.setTotalRecords("" + obj.length);
			} else {
				bean.setRecordsLength("false");

			}
			// bean.setEmpList(list);
			bean.setTotalRecords(String.valueOf(list.size()));
			if (list.size() > 0)
				bean.setRecordsLength("true");
			else
				bean.setRecordsLength("false");
			bean.setRecordList(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean deleteCheckedRecords(WareHouseMaster bean, String[] code) {
		boolean result = false;
		try {
			int count = 0;
			if (code != null) {
				for (int i = 0; i < code.length; i++) {

					if (!code[i].equals("")) {

						Object[][] delete = new Object[1][1];
						delete[0][0] = code[i];
						result = getSqlModel().singleExecute(getQuery(3),
								delete); // delete
						// the
						// checked
						// voucher
						// heads

						if (result)
							result = getSqlModel().singleExecute(getQuery(6),
									delete);
						if (!result)
							count++;

					} // end of if
				} // end of for loop
			} // end of if
			if (count != 0) {
				result = false;
				return result;
			} // end of if
			else {
				result = true;
				return result;
			} // end of else
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
