package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.master.CheckListMaster;
import org.paradyne.bean.admin.master.RankMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author pranali Date 26-04-07
 */
/**
 *  to define the business logic for check list
 */
public class CheckListModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	org.paradyne.bean.admin.master.CheckListMaster checkListMaster = null;

	/**
	 * for checking duplicate entry of records during insertion
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean checkDuplicate(CheckListMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_CHECKLIST WHERE UPPER(CHECK_NAME) LIKE '"
				+ bean.getCheckName().trim().toUpperCase()
				+ "'AND CHECK_TYPE LIKE '" + bean.getCheckType() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/**
	 * for checking duplicate entry of records during modification
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean checkDuplicateMod(CheckListMaster bean) {
		boolean result = false;

		String query = "SELECT * FROM HRMS_CHECKLIST WHERE UPPER(CHECK_NAME) LIKE '"
				+ bean.getCheckName().trim().toUpperCase()
				+ "' AND CHECK_TYPE LIKE '"
				+ bean.getCheckType()
				+ "'"
				+ " AND CHECK_CODE not in(" + bean.getCheckCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/**
	 * for inserting records
	 * 
	 * @param bean
	 * @return String
	 */
	public String addCheckList(CheckListMaster bean) {
		String flag = "";
		boolean result = false;
		Object addObj[][] = new Object[1][4];
		try {
			addObj[0][0] = bean.getCheckName().trim();
			addObj[0][1] = bean.getCheckType();
			addObj[0][2] = bean.getDesciption().trim();
			addObj[0][3] = bean.getStatus();

			if (!checkDuplicate(bean)) {
				result = getSqlModel().singleExecute(getQuery(1), addObj);

				if (result) {

					String code = "SELECT MAX(CHECK_CODE) FROM HRMS_CHECKLIST  ";
					Object[][] chk_code = getSqlModel().getSingleResult(code);

					String query = "SELECT CHECK_CODE,CHECK_NAME,DECODE(CHECK_TYPE,'J','Joining CheckList','M','Medical CheckList','T','Transfer CheckList','I','Interview CheckList','B','Background Verification CheckList','P','Prejoining CheckList'),  "
							+ " CHECK_DESC,CHECK_STATUS "
							+ " FROM HRMS_CHECKLIST  "
							+ "  where  CHECK_CODE= "
							+ String.valueOf(chk_code[0][0]);

					Object[][] data = getSqlModel().getSingleResult(query);
					bean.setCheckCode(checkNull(String.valueOf(data[0][0]).trim()));
					bean.setCheckName(checkNull(String.valueOf(data[0][1]).trim()));
					bean.setTypeCheck(String.valueOf(data[0][2]));
					bean.setDesciption(checkNull(String.valueOf(data[0][3]).trim()));
					bean.setStatus(checkNull(String.valueOf(data[0][4]).trim()));
					bean.setHidStatus(String.valueOf(data[0][4]));
					flag = "saved";
				} else {
					flag = "error";
				}
			} else {
				String query = "SELECT DECODE(CHECK_STATUS,'A','Active','D','Deactive'),DECODE(CHECK_TYPE,'J','Joining CheckList','M','Medical CheckList','T','Transfer CheckList','I','Interview CheckList','B','Background Verification CheckList','P','Prejoining CheckList') "
						+ " FROM HRMS_CHECKLIST where CHECK_TYPE='"
						+ bean.getCheckType()
						+ "' and CHECK_STATUS='"
						+ bean.getStatus() + "'";

				Object[][] data = getSqlModel().getSingleResult(query);
				flag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was rised---->");
		}
		return flag;
	}

	/**
	 * for modifying records
	 * 
	 * @param bean
	 * @return String
	 */
	public String modCheckList(CheckListMaster bean) {
		String editFlag = "";
		boolean result = false;
		Object addObj[][] = new Object[1][5];
		try {
			addObj[0][0] = bean.getCheckName().trim();
			addObj[0][1] = bean.getCheckType();
			addObj[0][2] = bean.getDesciption().trim();
			addObj[0][3] = bean.getStatus();
			addObj[0][4] = bean.getCheckCode();
			if (!checkDuplicateMod(bean)) {

				result = getSqlModel().singleExecute(getQuery(2), addObj);

				if (result) {
					String query = "SELECT CHECK_CODE,CHECK_NAME,CHECK_TYPE,CHECK_DESC,CHECK_STATUS FROM HRMS_CHECKLIST "
							+ "  where  CHECK_CODE= " + bean.getCheckCode();

					Object[][] data = getSqlModel().getSingleResult(query);
					bean.setCheckCode(checkNull(String.valueOf(data[0][0]).trim()));
					bean.setCheckName(checkNull(String.valueOf(data[0][1]).trim()));
					bean.setCheckType(String.valueOf(data[0][2]));
					bean.setDesciption(checkNull(String.valueOf(data[0][3]).trim()));
					bean.setStatus(String.valueOf(data[0][4]));
					editFlag = "modified";
				} else {
					editFlag = "error";
				}
			} else {
				editFlag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was rised---->");
		}
		return editFlag;
	}

	/**
	 * for deleting single record
	 * 
	 * @param bean
	 * @return
	 */
	public boolean deleteCheckList(CheckListMaster bean) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getCheckCode();
		return getSqlModel().singleExecute(getQuery(3), addObj);
	}

	public void getCheckListReport(CheckListMaster checkListMaster) {
		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		ArrayList<Object> att = new ArrayList<Object>();
		for (int i = 0; i < data.length; i++) {
			CheckListMaster bean1 = new CheckListMaster();
			bean1.setCheckCode(String.valueOf(data[i][0]));
			bean1.setCheckName(String.valueOf(data[i][1]));
			bean1.setCheckType(String.valueOf(data[i][2]));
			/*
			 * bean1.setDesciption(checkNull((String.valueOf(data[i][3]))));
			 * bean1.setStatus(String.valueOf(data[i][4]));
			 */
			att.add(bean1);
		}
		checkListMaster.setAtt(att);
	}

	/**
	 * for generating report
	 * 
	 * @param checkListMaster2
	 * @param request
	 * @param response
	 * @param context
	 */
	public void getReport(CheckListMaster checkListMaster2,
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context) {
		// TODO Auto-generated method stub
		CrystalReport cr = new CrystalReport();
		String path = "org\\paradyne\\rpt\\admin\\master\\checklist.rpt ";
		cr.createReport(request, response, context, session, path, "");

	}
/**
 *  to  check the null value
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

	/**
	 * for displaying records in the list below
	 * 
	 * @param checkListMaster
	 * @param request
	 */
	public void checkData(CheckListMaster checkListMaster,
			HttpServletRequest request) {
		int length=0;	
		Object[][] repData = getSqlModel().getSingleResult(getQuery(5));
		if(repData!=null && repData.length>0){
			checkListMaster.setModeLength("true");
			checkListMaster.setTotalRecords(String.valueOf(repData.length));		
		String[] pageIndex = Utility.doPaging(checkListMaster.getMyPage(), repData.length, 20);	
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
			checkListMaster.setMyPage("1");
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {		
			CheckListMaster bean1 = new CheckListMaster();
			bean1.setCheckCode(checkNull(String.valueOf(repData[i][0])));
			bean1.setCheckName(checkNull(String.valueOf(repData[i][1])));
			bean1.setCheckType(checkNull(String.valueOf(repData[i][2])));
			bean1.setStatus(checkNull(String.valueOf(repData[i][3])));
			List.add(bean1);
		}
		checkListMaster.setAtt(List);
		}
	}

	public void getRecords(CheckListMaster bean) {
		try {

			//logger.info("bean.getSpecializationCode--->" + bean.getCheckCode());

			String query = "SELECT CHECK_NAME,"
					+ " CASE WHEN CHECK_TYPE='J' THEN 'Joining Checklist' "
					+ " WHEN CHECK_TYPE='M' THEN 'Medical Checklist'"
					+ " WHEN CHECK_TYPE='J' THEN 'Joining Checklist'"
					+ " WHEN CHECK_TYPE='T' THEN 'Transfer CheckList'"
					+ " WHEN CHECK_TYPE='I' THEN 'Interview CheckList'"
					+ "  WHEN CHECK_TYPE='B' THEN 'Background Verification CheckList'"
					+ "  WHEN CHECK_TYPE='P' THEN 'Prejoining CheckList'  END "
					+ " ,CHECK_DESC,CHECK_STATUS,CHECK_CODE "
					+ " FROM HRMS_CHECKLIST  " + "  where  CHECK_CODE= "
					+ bean.getCheckCode();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				bean.setCheckName(checkNull(String.valueOf(data[0][0])));
				bean.setCheckType(String.valueOf(data[0][1]));
				bean.setDesciption(checkNull((String.valueOf(data[0][2]))));
				bean.setStatus(String.valueOf(data[0][3]));
				bean.setCheckCode(checkNull(String.valueOf(data[0][4])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * for selecting records from the list
	 * 
	 * @param checkListMaster
	 */
	public void calforedit(CheckListMaster checkListMaster) {
		try {
			String query = "SELECT NVL(CHECK_NAME,' '),CHECK_TYPE,NVL(CHECK_DESC,' '),CHECK_STATUS,CHECK_CODE "
					+ " FROM HRMS_CHECKLIST  "

					+ "  where  CHECK_CODE= " + checkListMaster.getCheckCode();

			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				checkListMaster.setCheckName(checkNull((String.valueOf(data[0][0]))));
				
				checkListMaster.setCheckType(checkNull((String.valueOf(data[0][1]))));
				
				checkListMaster.setDesciption(checkNull((String.valueOf(data[0][2]))));
				
				checkListMaster.setStatus(checkNull((String.valueOf(data[0][3]))));
				
				checkListMaster.setCheckCode(checkNull((String.valueOf(data[0][4]))));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * for selecting records from the list
	 * 
	 * @param checkListMaster
	 */
	public void getCall(CheckListMaster checkListMaster) {
		//logger.info("val of check code" + checkListMaster.getCheckCode());
		try {
			String query = "SELECT CHECK_NAME,CHECK_DESC, "
					+ "CHECK_TYPE, "
					+ "CHECK_STATUS,CHECK_CODE "
					+ "FROM HRMS_CHECKLIST  "
					+ "where  CHECK_CODE="
					+ checkListMaster.getCheckCode();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {			
				checkListMaster.setCheckName(checkNull(String
						.valueOf(data[0][0])));
				checkListMaster.setCheckType(String.valueOf(data[0][2]));
				checkListMaster.setDesciption(checkNull((String.valueOf(data[0][1]))));
				checkListMaster.setStatus(String.valueOf(data[0][3]));
				checkListMaster.setCheckCode(checkNull(String.valueOf(data[0][4])));
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * for deleting multiple records
	 * 
	 * @param checkListMaster2
	 * @param code
	 * @return
	 */
	public String deletecheckedRecords(CheckListMaster checkListMaster2,
			String[] code) {
		String result = "";
		int count = 0;

		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					boolean res;
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					res = getSqlModel().singleExecute(getQuery(3), delete);
					if (!res)
						count++;
			}
		}
		}
		if (count != 0) {
			result = "false";
		} else
			result = "true";
		return result;
	}
	public void getCheckListType(CheckListMaster bean) {
		// TODO Auto-generated method stub
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		String query = "SELECT TYPE_CODE, TYPE_NAME	FROM HRMS_CHECK_LIST_TYPE "
				+ "WHERE TYPE_STATUS	= 'A'";

		Object[][] checkList = getSqlModel().getSingleResult(query);

		if (checkList != null && checkList.length != 0) {
			for (int i = 0; i < checkList.length; i++) {
				map.put(String.valueOf(checkList[i][0]), String
						.valueOf(checkList[i][1]));
			}

			bean.setMap(map);
		}

	}

	/**
	 * Following function is called to get the report of the Pdf format for list
	 * of Check List Records
	 * 
	 * @param checkList
	 * @param response
	 * @param label
	 */
	public void generateReport(CheckListMaster checkList,
			HttpServletResponse response, String[] label) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nCheck List Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Check List Master.Pdf");
		String queryDes = "SELECT CHECK_NAME,DECODE(CHECK_TYPE,'J','Joining CheckList','M','Medical CheckList','T',"
				+ " 'Transfer CheckList','I','Interview CheckList','B','Background Verification CheckList',"
				+ " 'P','Prejoining CheckList'),DECODE(CHECK_STATUS,'A','Active','D','Deactive') "
				+ " FROM HRMS_CHECKLIST ORDER BY CHECK_NAME";
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][4];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				j++;
			}
			int cellwidth[] = { 5, 20, 20, 20 };
			int alignment[] = { 1, 0, 0, 0 };
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);

	}
}
