package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.master.CostCenterMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class CostCenterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	/**
	 * to insert/ save the data in in table
	 * 
	 *
	 * @return
	 */

	public boolean addCast(CostCenterMaster castMaster, String[] abbr,
			String[] name) {
		// TODO Auto-generated method stub
		boolean result = false;
		if(!checkDuplicate(castMaster)){
		String Query1 = "SELECT UPPER(COST_CENTER_NAME) FROM HRMS_COST_CENTER WHERE (COST_CENTER_NAME='"
				+ castMaster.getCastName().trim().toUpperCase()
				+ "' OR COST_CENTER_NAME='"
				+ castMaster.getCastName().trim().toLowerCase()
				+ "')"
				+ "and COST_CENTER_ABBR='" + castMaster.getCastAbbr() + "'";
		Object[][] Recordlength = getSqlModel().getSingleResult(Query1);
		if (Recordlength.length > 0) {
			return false;
		}

		String query = " SELECT NVL(MAX(COST_CENTER_ID),0)+1 FROM HRMS_COST_CENTER ";
		Object[][] code = getSqlModel().getSingleResult(query);
		castMaster.setCastCode(String.valueOf(code[0][0]));
		Object obj[][] = new Object[1][2];
		obj[0][0] = checkNull(castMaster.getCastAbbr());
		obj[0][1] = checkNull(castMaster.getCastName());
		String query1 = " INSERT INTO HRMS_COST_CENTER (COST_CENTER_ID,COST_CENTER_ABBR, COST_CENTER_NAME) VALUES ((SELECT NVL(MAX(COST_CENTER_ID),0)+1 FROM HRMS_COST_CENTER ), ? , ?)";
		result = getSqlModel().singleExecute(query1, obj);
		//if (result) {
			//castMaster.setCastCode(String.valueOf(code[0][0]));
			//result = addOption(castMaster, abbr, name);
		addOption(castMaster, abbr, name);
		//}
		return result;
		}
		else {
			return false;
		}
	}

	/**
	 * to display the subcost data in the list
	 * 
	 * @param castMaster
	 */

	public void showRecord(CostCenterMaster castMaster) {
		// TODO Auto-generated method stub
		String query = "SELECT SUB_COST_CENTER_ID,SUB_COST_CENTER_ABBR ,SUB_COST_CENTER_NAME  FROM HRMS_SUB_COST_CENTER WHERE COST_CENTER_ID = "
				+ castMaster.getCastCode() + " ORDER BY SUB_COST_CENTER_ID";

		Object data[][] = getSqlModel().getSingleResult(query);
	
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < data.length; i++) {
			CostCenterMaster bean1 = new CostCenterMaster();
			bean1.setSubcode(String.valueOf(data[i][0]));
			bean1.setSubCAbbr(String.valueOf(data[i][1]));
			bean1.setSubCName(String.valueOf(data[i][2]));
			bean1.setSrNo(String.valueOf(i + 1));
			list.add(bean1);
		}
		castMaster.setList(list);
		if (list.size() == 0) {
			castMaster.setTableLength("0");
			castMaster.setModeLength("false");
			castMaster.setSubcostLength("false");
		} else
		{
			castMaster.setTableLength("1");
		    castMaster.setModeLength("true");
		    castMaster.setSubcostLength("true");
		}
			
	}

	/**
	 * to modify the subcost list
	 * 
	 * @param castMaster
	 * @param abbr
	 * @param name
	 * @return
	 */
	public boolean modCast(CostCenterMaster castMaster, String[] abbr,
			String[] name) {
		// TODO Auto-generated method stub
		boolean result1 = false;
		boolean result2 = false;
		boolean result=false;
		if(!checkDuplicateMod(castMaster)){
		Object modData[][] = new Object[1][3];
		modData[0][0] = castMaster.getCastAbbr().trim();
		modData[0][1] = castMaster.getCastName().trim();
		modData[0][2] = castMaster.getCastCode().trim();
		String Updatequery = "UPDATE HRMS_COST_CENTER SET COST_CENTER_ABBR=?,COST_CENTER_NAME=? WHERE COST_CENTER_ID=?";
		 result = getSqlModel().singleExecute(Updatequery, modData);

		if (result) {
			String queryDel = "DELETE FROM HRMS_SUB_COST_CENTER WHERE COST_CENTER_ID= "
					+ castMaster.getCastCode();
			result1 = getSqlModel().singleExecute(queryDel);
			if (result1)
				result2 = addOption(castMaster, abbr, name);
		}
		return result;
		}else{
			return false;
		}
		}
	

	/**
	 * to inser the subcost list in the table
	 * 
	 * @param castMaster
	 * @param subCAbbr
	 * @param subCName
	 * @return
	 */

	public boolean addOption(CostCenterMaster castMaster, String[] subCAbbr,
			String[] subCName) {

		boolean result = false;
		Object addObj[][] = new Object[1][4];
		if (subCName != null) {
			for (int i = 0; i < subCName.length; i++) {
				addObj[0][0] = castMaster.getCastCode();
				addObj[0][1] = subCAbbr[i];
				addObj[0][2] = subCName[i];
				addObj[0][3] = String.valueOf(i + 1);

				result = getSqlModel().singleExecute(getQuery(2), addObj);
			}
		}
		return result;
	}

	/**
	 * to display the cost list in on load
	 * 
	 * @param castMaster
	 * @param request
	 */
	public void castCenterListData(CostCenterMaster castMaster,
			HttpServletRequest request) {
		// TODO Auto-generated method stub

		Object[][] repData = getSqlModel().getSingleResult(getQuery(6));
		ArrayList<Object> List = new ArrayList<Object>();
		int rowCnt = 0;
		String equlcId = "";
		for (int i = 0; i < repData.length; i++) {
			if (equlcId.equals(String.valueOf(repData[i][0]))) {
			} else {
				rowCnt++;
			}

			equlcId = String.valueOf(repData[i][0]);
		}
		Object[][] objList = new Object[repData.length + rowCnt][7];

		String chkEqual = "";
		int j = 0;
		int cnt = 1;
		int k = 0;
		for (int i = 0; i < repData.length; i++) {

			if (chkEqual.equals(String.valueOf(repData[i][0]))) {
				objList[k][0] = "";
				objList[k][1] = String.valueOf(repData[i - j][0]);
				objList[k][2] = "";
				objList[k][3] = "";
				objList[k][4] = String.valueOf(repData[i - j][3]);
				objList[k][5] = String.valueOf(repData[i - j][4]);
				objList[k][6] = String.valueOf(repData[i - j][5]);
				k++;
				if (i < repData.length - 1) {
					if (!String.valueOf(repData[i][0]).equals(
							String.valueOf(repData[i + 1][0]))) {
						objList[k][0] = "";
						objList[k][1] = String.valueOf(repData[i - j+1][0]);
						objList[k][2] = "";
						objList[k][3] = "";
						objList[k][4] = String.valueOf(repData[i - j+1][3]);
						objList[k][5] = String.valueOf(repData[i - j+1][4]);
						objList[k][6] = String.valueOf(repData[i - j+1][5]);
						k++;
						j--;
					}
				} else {
					objList[k][0] = "";
					objList[k][1] = String.valueOf(repData[i - j+1][0]);
					objList[k][2] = "";
					objList[k][3] = "";
					objList[k][4] = String.valueOf(repData[i - j+1][3]);
					objList[k][5] = String.valueOf(repData[i - j+1][4]);
					objList[k][6] = String.valueOf(repData[i - j+1][5]);
				}

			} else {

				j++;
				objList[k][0] = cnt;
				objList[k][1] = String.valueOf(repData[i - j+1][0]);
				objList[k][2] = String.valueOf(repData[i - j+1][1]);
				objList[k][3] = String.valueOf(repData[i - j+1][2]);
				objList[k][4] = "";
				objList[k][5] = "";
				objList[k][6] = "";
				cnt++;
				k++;

				if (i < repData.length - 1) {
					if (!String.valueOf(repData[i][0]).equals(
							String.valueOf(repData[i + 1][0]))) {
						objList[k][0] = "";
						objList[k][1] = String.valueOf(repData[i][0]);
						objList[k][2] = "";
						objList[k][3] = "";
						objList[k][4] = String.valueOf(repData[i][3]);
						objList[k][5] = String.valueOf(repData[i][4]);
						objList[k][6] = String.valueOf(repData[i][5]);
						k++;
						j--;
					}
				}
				if (i == (repData.length - 1)) {
					objList[k][0] = "";
					objList[k][1] = String.valueOf(repData[i][0]);
					objList[k][2] = "";
					objList[k][3] = "";
					objList[k][4] = String.valueOf(repData[i][3]);
					objList[k][5] = String.valueOf(repData[i][4]);
					objList[k][6] = String.valueOf(repData[i][5]);
				}

			}

			chkEqual = String.valueOf(repData[i][0]);
		}
		
		int len = 0;
		Object[][] list = null;

		if(objList != null && objList.length > 0) {
			for(int i = 0; i < objList.length; i++) {
				if(!((String.valueOf(objList[i][2]).equals("")||String.valueOf(objList[i][2]).equals("null")||
						String.valueOf(objList[i][2])==null) && (String.valueOf(objList[i][5]).equals("")|| 
						String.valueOf(objList[i][5]).equals("null")|| String.valueOf(objList[i][5])==null))) {
					len++;
				}
			}
			
			list = new Object[len][7];
			int count = 0;
			for(int i = 0; i < objList.length; i++) {
				if(!((String.valueOf(objList[i][2]).equals("")||String.valueOf(objList[i][2]).equals("null")||
						String.valueOf(objList[i][2])==null) && (String.valueOf(objList[i][5]).equals("")|| 
						String.valueOf(objList[i][5]).equals("null")|| String.valueOf(objList[i][5])==null))) {
					list[count][0] = objList[i][0];
					list[count][1] = objList[i][1];
					list[count][2] = objList[i][2];
					list[count][3] = objList[i][3];
					list[count][4] = objList[i][4];
					list[count][5] = objList[i][5];
					list[count][6] = objList[i][6];
					count++;
				}
			}
		}
	if (list != null && list.length > 0) {
			castMaster.setModeLength("true");
			castMaster.setTotalRecords(String.valueOf(list.length));

			String[] pageIndex = Utility.doPaging(castMaster.getMyPage(),
					list.length, 20);
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
				castMaster.setMyPage("1");
			
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				CostCenterMaster bean1 = new CostCenterMaster();
				bean1.setSrNo(checkNull(String.valueOf(list[i][0])));
				bean1.setCastCode(checkNull(String.valueOf(list[i][1])));
				bean1.setCastAbbr(checkNull(String.valueOf(list[i][2])));
				bean1.setCastName(checkNull(String.valueOf(list[i][3])));
				bean1.setSubcastCode(checkNull(String.valueOf(list[i][4])));
				bean1.setSubcastAbbr(checkNull(String.valueOf(list[i][5])));
				bean1.setSubcastName(checkNull(String.valueOf(list[i][6])));
				
				List.add(bean1);
			}

			castMaster.setCastlist(List);
		}else{
			castMaster.setModeLength("false");
			castMaster.setTotalRecords("0");
			castMaster.setCastlist(List);
		}

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * To Delete multiple record in the list
	 * 
	 * @param accountCategory
	 * @param code
	 * @return
	 */
	public String deleteList(CostCenterMaster castMaster, String deletedCostIds, String deletedSubCostIds) {
		String message = "";
		boolean isDeleted = false;
		
		if (!deletedCostIds.equals("")) {
			String isSubcostExistSql=" SELECT * FROM HRMS_SALARY_MISC " +
			" WHERE COST_CENTER_ID IN(" + deletedCostIds + ") ";
			Object[][] data = getSqlModel().getSingleResult(isSubcostExistSql);
			
			if(data == null || data.length < 1) {
				String deleteSubCostSql = " DELETE FROM HRMS_SUB_COST_CENTER WHERE COST_CENTER_ID IN(" + deletedCostIds + ") ";
				isDeleted = getSqlModel().singleExecute(deleteSubCostSql);
				
				if(isDeleted) {
					String deleteCostSql = " DELETE FROM HRMS_COST_CENTER WHERE COST_CENTER_ID IN(" + deletedCostIds + ") ";
					isDeleted = getSqlModel().singleExecute(deleteCostSql);
				}
			}
		}
		
		if (!deletedSubCostIds.equals("")) {
			String costId = "";
			String subCostId = "";
			
			String[] delSubCostIds = deletedSubCostIds.split(",");
			
			for(int i = 0; i < delSubCostIds.length; i++) {
				String[] deleteIds = delSubCostIds[i].split("#");
				
				costId = deleteIds[0];
				subCostId = deleteIds[1];
				
				String isSubcostExistSql=" SELECT * FROM HRMS_SALARY_MISC " +
				" WHERE COST_CENTER_ID = " + costId + " AND SUB_COST_CENTER_ID = " + subCostId;
				Object[][] data = getSqlModel().getSingleResult(isSubcostExistSql);
				
				if(data == null || data.length < 1) {
					String deleteSubCostSql = " DELETE FROM HRMS_SUB_COST_CENTER WHERE SUB_COST_CENTER_ID = " + subCostId + 
					" AND COST_CENTER_ID = " + costId;
					isDeleted = getSqlModel().singleExecute(deleteSubCostSql);
				}
			}
		}
		
		if(isDeleted) {
			message = "delete";
		} else {
			message = "multiple.del.error";
		}
		return message;
	}

	/**
	 * to check the Duplicate value or data
	 * 
	 */
	public boolean checkDuplicate(CostCenterMaster castMaster) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_COST_CENTER WHERE UPPER(COST_CENTER_NAME) LIKE '"
				+ castMaster.getCastName().trim().toUpperCase() + "'";
		Object obj[][] = getSqlModel().getSingleResult(query);
		if (obj != null && obj.length > 0) {
			return true;
		}
		return result;
	}

	/**
	 * to check the Modified Duplicate value or data
	 * 
	 */
	public boolean checkDuplicateMod(CostCenterMaster castMaster) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_COST_CENTER WHERE UPPER(COST_CENTER_NAME) LIKE '"
				+ castMaster.getCastName().trim().toUpperCase()
				+ "' AND COST_CENTER_ID not in ("
				+ castMaster.getCastCode()
				+ ")";
		Object obj[][] = getSqlModel().getSingleResult(query);
		if (obj != null && obj.length > 0) {
			return true;
		}
		return result;
	}

	/**
	 * to modify the record in double click in the list
	 * 
	 * @param accountCategory
	 */

	public void calforedit(CostCenterMaster castMaster) {
		// TODO Auto-generated method stub
		String query = "SELECT   COST_CENTER_ABBR, COST_CENTER_NAME, COST_CENTER_ID  from HRMS_COST_CENTER"
				+ " WHERE COST_CENTER_ID="
				+ castMaster.getCastCode()
				+ " ORDER BY COST_CENTER_ID ";

		Object[][] data = getSqlModel().getSingleResult(query);
		castMaster.setCastAbbr(String.valueOf(data[0][0]));
		castMaster.setCastName(String.valueOf(data[0][1]));
		castMaster.setCastCode(String.valueOf(data[0][2]));
		showRecord(castMaster);
	}

	public void delDtl(CostCenterMaster castMaster, String[] code,
			String[] abbr, String[] name) {
		// TODO Auto-generated method stub
		boolean isDeleted=false;
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			/*for (int j = 0; j < code.length; j++) {
				
					String isSubcostExistSql=" SELECT * FROM HRMS_SALARY_MISC " +
			" WHERE COST_CENTER_ID = " + castMaster.getCastCode()+ " AND SUB_COST_CENTER_ID ="+code[]+" ";
			Object[][] data = getSqlModel().getSingleResult(isSubcostExistSql);
			 logger.info("castMaster.getSubcastCode()------select-------->"+code.length);
			}	
			if(data !=null && data.length >0) {
				String deleteSubCostSql = " DELETE FROM HRMS_SUB_COST_CENTER WHERE SUB_COST_CENTER_ID ="+code+" "
				+" AND COST_CENTER_ID = " + castMaster.getCastCode();
				  isDeleted = getSqlModel().singleExecute(deleteSubCostSql);
				  logger.info("castMaster.getSubcastCode()------delete-------->"+castMaster.getSubcastCode());
		 if(isDeleted){*/
			if (name != null && name.length >0) {
				for (int i = 0; i < name.length; i++) {
					CostCenterMaster bean = new CostCenterMaster();
					if ((String.valueOf(code[i]).equals(""))) {
						bean.setSubCAbbr(abbr[i]);
						bean.setSubCName(name[i]);
						list.add(bean);

					}

				}
				castMaster.setList(list);
				castMaster.setSubcostLength("true");
				
			}
			else{
				//castMaster.setList(list);
				castMaster.setSubcostLength("false");
				
			}
			/* }
		 else{
				//castMaster.setList(list);
				castMaster.setSubcostLength("false");
				
			}
		 }
			*/
		}
		
		
		
		catch (Exception e) {
			e.printStackTrace();

			// TODO: handle exception
		}

	}

	/**
	 * to add the subcost record in the list
	 * 
	 * @param castMaster
	 * @param srNo
	 * @param abbr
	 * @param name
	 * @param check
	 */

	public void addItem(CostCenterMaster castMaster, String[] srNo,
			String[] abbr, String[] name, int check) {
		// TODO Auto-generated method stub

		ArrayList<Object> tableList = new ArrayList<Object>();
		logger.info("in new add");
		if (abbr != null && abbr.length>0) {
			
			for (int i = 0; i < abbr.length; i++) {
				CostCenterMaster bean = new CostCenterMaster();
				bean.setSrNo(String.valueOf(i + 1));
				bean.setSubCAbbr(abbr[i]);
				bean.setSubCName(name[i]);
				tableList.add(bean);
				logger.info("abbr[i]"+abbr[i]);
				logger.info("name[i]"+name[i]);
				
			}
		}
		if (check == 1) {
			castMaster.setSrNo(String.valueOf(tableList.size() + 1));
			castMaster.setSubCAbbr(castMaster.getSubcastAbbr());
			castMaster.setSubCName(castMaster.getSubcastName());
			logger.info("castMaster.getSubcastAbbr()  "+castMaster.getSubcastAbbr());
			logger.info("castMaster.getSubcastName()   "+castMaster.getSubcastName());
			tableList.add(castMaster);
		} else if (check == 0) {
			tableList.remove(Integer.parseInt(castMaster.getSubcode()) - 1);
		}

		if (tableList.size() != 0) {
			castMaster.setTableLength("1");
		castMaster.setModeLength("true");
		castMaster.setSubcostLength("true");
		
		
		}
		else {
			castMaster.setTableLength("0");
			castMaster.setModeLength("false");
			castMaster.setSubcostLength("false");
		}
		   
		
		castMaster.setList(tableList);
	}

	/**
	 * to modify the subcost records from the list
	 * 
	 * @param castMaster
	 * @param srNo
	 * @param abbr
	 * @param name
	 * @param check
	 */

	public void moditem(CostCenterMaster castMaster, String[] srNo,
			String[] abbr, String[] name, int check) {
		// TODO Auto-generated method stub
		ArrayList<Object> tableList = new ArrayList<Object>();
		logger.info("in  moditem");
		if (srNo != null) {

			for (int i = 0; i < srNo.length; i++) {
				CostCenterMaster bean = new CostCenterMaster();
				logger.info("hidden edit==" + castMaster.getCheckEdit());
				if (i == Integer.parseInt(castMaster.getCheckEdit()) - 1) {

					bean.setSrNo(String.valueOf(i + 1));
					bean.setSubCAbbr(castMaster.getSubcastAbbr());
					bean.setSubCName(castMaster.getSubcastName());

				} else {
					bean.setSrNo(String.valueOf(i + 1));
					bean.setSubCAbbr(abbr[i]);
					bean.setSubCName(name[i]);
				}
				tableList.add(bean);
			}
		}

		if (tableList.size() != 0){
			castMaster.setTableLength("1");
			castMaster.setModeLength("true");
		castMaster.setSubcostLength("true");
		}
		else{
			castMaster.setTableLength("0");
		
		castMaster.setModeLength("false");
		castMaster.setSubcostLength("false");
		
		}
		castMaster.setList(tableList);
	}

	/**
	 * to delete the subcost list
	 * 
	 * @param castMaster
	 * @return
	 */

	public boolean deleteCast(CostCenterMaster castMaster) {
		// TODO Auto-generated method stub
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = castMaster.getCastCode();
		/*boolean res = getSqlModel().singleExecute(getQuery(4), delObj);
		if (res)
			return getSqlModel().singleExecute(getQuery(3), delObj);
		else
			return false;*/
		String message = "";
		boolean isDeleted = false;
		
		String isSubcostExistSql=" SELECT * FROM HRMS_SALARY_MISC " +
			" WHERE COST_CENTER_ID =(" + castMaster.getCastCode() + ") ";
			Object[][] data = getSqlModel().getSingleResult(isSubcostExistSql);
			
			if(data == null || data.length < 1) {
				String deleteSubCostSql = " DELETE FROM HRMS_SUB_COST_CENTER WHERE COST_CENTER_ID ="+castMaster.getCastCode()+" ";
				isDeleted = getSqlModel().singleExecute(deleteSubCostSql);
				
				if(isDeleted) {
					String deleteCostSql = " DELETE FROM HRMS_COST_CENTER WHERE COST_CENTER_ID ="+castMaster.getCastCode()+" ";
					isDeleted = getSqlModel().singleExecute(deleteCostSql);
				}
			}
			return isDeleted;
		
		
		
	}

	/**
	 * to generate the report
	 * 
	 * @param castMaster
	 * @param response
	 * @param label
	 */
	
	public void generateReport(CostCenterMaster castMaster,
			HttpServletResponse response, String[] label) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nCost Center  Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Cost Center  Master.Pdf");
		
	String queryDes = " SELECT  HRMS_COST_CENTER.COST_CENTER_ABBR, HRMS_COST_CENTER.COST_CENTER_NAME,SUB_COST_CENTER_ABBR ,SUB_COST_CENTER_NAME FROM HRMS_COST_CENTER"
						+" LEFT JOIN HRMS_SUB_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SUB_COST_CENTER.COST_CENTER_ID)"
						+" ORDER BY  HRMS_COST_CENTER.COST_CENTER_NAME";
		
	Object[][] repData = getSqlModel().getSingleResult(queryDes);
	int rowCnt = 0;
	String equlcId = "";
	for (int i = 0; i < repData.length; i++) {
		if (equlcId.equals(String.valueOf(repData[i][0]))) {
		} else {
			rowCnt++;
		}

		equlcId = String.valueOf(repData[i][0]);
	}
	Object[][] objList = new Object[repData.length + rowCnt][5];

	String chkEqual = "";
	int j = 0;
	int cnt = 1;
	int k = 0;
	for (int i = 0; i < repData.length; i++) {

		if (chkEqual.equals(String.valueOf(repData[i][0]))) {
			objList[k][0] = "";
			//objList[k][1] = String.valueOf(repData[i - j][0]);
			objList[k][1] = "";
			objList[k][2] = "";
			//objList[k][4] = String.valueOf(repData[i - j][3]);
			objList[k][3] = String.valueOf(repData[i - j][2]);
			objList[k][4] = String.valueOf(repData[i - j][3]);
			k++;
			if (i < repData.length - 1) {
				if (!String.valueOf(repData[i][0]).equals(
						String.valueOf(repData[i + 1][0]))) {
					objList[k][0] = "";
					//objList[k][1] = String.valueOf(repData[i - j+1][0]);
					objList[k][1] = "";
					objList[k][2] = "";
				//	objList[k][4] = String.valueOf(repData[i - j+1][3]);
					objList[k][3] = String.valueOf(repData[i - j+1][2]);
					objList[k][4] = String.valueOf(repData[i - j+1][3]);
					k++;
					j--;
				}
			} else {
				objList[k][0] = "";
				//objList[k][1] = String.valueOf(repData[i - j+1][0]);
				objList[k][1] = "";
				objList[k][2] = "";
				//objList[k][4] = String.valueOf(repData[i - j+1][3]);
				objList[k][3] = String.valueOf(repData[i - j+1][2]);
				objList[k][4] = String.valueOf(repData[i - j+1][3]);
			}

		} else {

			j++;
			objList[k][0] = cnt;
			//objList[k][1] = String.valueOf(repData[i - j+1][0]);
			objList[k][1] = String.valueOf(repData[i - j+1][0]);
			objList[k][2] = String.valueOf(repData[i - j+1][1]);
			//objList[k][4] = "";
			objList[k][3] = "";
			objList[k][4] = "";
			cnt++;
			k++;

			if (i < repData.length - 1) {
				if (!String.valueOf(repData[i][0]).equals(
						String.valueOf(repData[i + 1][0]))) {
					objList[k][0] = "";
					//objList[k][1] = String.valueOf(repData[i][0]);
					objList[k][1] = "";
					objList[k][2] = "";
					//objList[k][4] = String.valueOf(repData[i][3]);
					objList[k][3] = String.valueOf(repData[i][2]);
					objList[k][4] = String.valueOf(repData[i][3]);
					k++;
					j--;
				}
			}
			if (i == (repData.length - 1)) {
				objList[k][0] = "";
				//objList[k][1] = String.valueOf(repData[i][0]);
				objList[k][1] = "";
				objList[k][2] = "";
				//objList[k][4] = String.valueOf(repData[i][3]);
				objList[k][3] = String.valueOf(repData[i][2]);
				objList[k][4] = String.valueOf(repData[i][3]);
			}

		}

		chkEqual = String.valueOf(repData[i][0]);
	}
	
	int len = 0;
	Object[][] list = null;

	if(objList != null && objList.length > 0) {
		for(int i = 0; i < objList.length; i++) {
			if(!((String.valueOf(objList[i][1]).equals("")||String.valueOf(objList[i][1]).equals("null")||
					String.valueOf(objList[i][1])==null) && (String.valueOf(objList[i][3]).equals("")|| 
					String.valueOf(objList[i][3]).equals("null")|| String.valueOf(objList[i][3])==null))) {
				len++;
			}
		}
		
		list = new Object[len][5];
		int count = 0;
		for(int i = 0; i < objList.length; i++) {
			if(!((String.valueOf(objList[i][1]).equals("")||String.valueOf(objList[i][1]).equals("null")||
					String.valueOf(objList[i][1])==null) && (String.valueOf(objList[i][3]).equals("")|| 
					String.valueOf(objList[i][3]).equals("null")|| String.valueOf(objList[i][3])==null))) {
				list[count][0] = objList[i][0];
				list[count][1] = objList[i][1];
				list[count][2] = objList[i][2];
				list[count][3] = objList[i][3];
				list[count][4] = objList[i][4];
				//list[count][5] = objList[i][5];
				//list[count][6] = objList[i][6];
				count++;
			}
		}
	}
	if (list != null && list.length > 0) {
		
	
	int cellwidth[] = { 10, 20, 20,20,20 };
	int alignment[] = { 1, 0, 0,0,0 };
	rg.addFormatedText(reportName, 6, 0, 1, 0);
	rg.addText("\n", 0, 0, 0);
	rg.addTextBold("Date :" + toDay, 0, 2, 0);
	rg.addText("\n", 0, 0, 0);
	rg.tableBody(label, list, cellwidth, alignment);
} else {
	rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
}

rg.createReport(response);

}

	public void getDuplicate(CostCenterMaster castMaster, String[] srNo,
			String[] abbr, String[] name, int i) {
		// TODO Auto-generated method stub
		
	}
}
