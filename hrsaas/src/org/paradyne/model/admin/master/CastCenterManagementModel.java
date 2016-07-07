package org.paradyne.model.admin.master;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.paradyne.bean.admin.master.CastCenterManagementMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class CastCenterManagementModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);

	
	/**
 *  to insert/ save the data in in table 
 * @param castMaster
 * @param subcastAbbr
 * @param subcastName
 * @return
 */
	
	
	public boolean addCast(CastCenterManagementMaster castMaster,
			String[] abbr, String[] name) {
		// TODO Auto-generated method stub
		
		String Query1 = "SELECT UPPER(COST_CENTER_NAME) FROM HRMS_COST_CENTER WHERE (COST_CENTER_NAME='"
			+castMaster.getCastName().trim().toUpperCase()
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
   String query1=" INSERT INTO HRMS_COST_CENTER (COST_CENTER_ID,COST_CENTER_ABBR, COST_CENTER_NAME) VALUES ((SELECT NVL(MAX(COST_CENTER_ID),0)+1 FROM HRMS_COST_CENTER ), ? , ?)";
	boolean result= getSqlModel().singleExecute(query1, obj);
	if (result) {
		castMaster.setCastCode(String.valueOf(code[0][0]));
		result = addOption(castMaster, abbr,name);
	}
	return result;
		
}
	
		/**
		 *  to display the subcost dat in tha list
		 * @param castMaster
		 */
	
	public void showRecord(CastCenterManagementMaster castMaster) {
		// TODO Auto-generated method stub
			String query = "SELECT SUB_COST_CENTER_ID,SUB_COST_CENTER_ABBR ,SUB_COST_CENTER_NAME  FROM HRMS_SUB_COST_CENTER WHERE COST_CENTER_ID = "
					+ castMaster.getCastCode() + " ORDER BY SUB_COST_CENTER_ID";

			Object data[][] = getSqlModel().getSingleResult(query);

			ArrayList<Object> list = new ArrayList<Object>();
			for (int i = 0; i < data.length; i++) {
				CastCenterManagementMaster bean1 = new CastCenterManagementMaster();
				bean1.setSubcode(String.valueOf(data[i][0]));
				bean1.setSubCAbbr(String.valueOf(data[i][1]));
				bean1.setSubCName(String.valueOf(data[i][2]));
				bean1.setSrNo(String.valueOf(i + 1));
				list.add(bean1);
		}
			castMaster.setList(list);
			if (list.size() == 0) {
				castMaster.setTableLength("0");
			} else
				castMaster.setTableLength("1");
		}

	/**
	 *  to modify the subcost list 
	 * @param castMaster
	 * @param abbr
	 * @param name
	 * @return
	 */
	public boolean modCast(CastCenterManagementMaster castMaster,
			String[] abbr, String[] name) {
		// TODO Auto-generated method stub
		boolean result1 = false;
		boolean result2 = false;
			Object modData[][] = new Object[1][3];
			modData[0][0] = castMaster.getCastAbbr().trim();
			modData[0][1] = castMaster.getCastName().trim();
			modData[0][2] = castMaster.getCastCode().trim();
			String Updatequery = "UPDATE HRMS_COST_CENTER SET COST_CENTER_ABBR=?,COST_CENTER_NAME=? WHERE COST_CENTER_ID=?";
			boolean result = getSqlModel().singleExecute(Updatequery, modData);
		
	if (result) {
		String queryDel = "DELETE FROM HRMS_SUB_COST_CENTER WHERE COST_CENTER_ID= "
				+ castMaster.getCastCode();
		result1 = getSqlModel().singleExecute(queryDel);
		if (result1)
			result2 = addOption(castMaster, abbr, name);
	}
	return result;		
}
	
	
	/**
	 *  to inser the  subcost list in the table
	 * @param castMaster
	 * @param subCAbbr
	 * @param subCName
	 * @return
	 */
	
	public boolean addOption(CastCenterManagementMaster castMaster, String[] subCAbbr,
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
 *  to display the  cost list in on load
 * @param castMaster
 * @param request
 */
	public void castCenterListData(CastCenterManagementMaster castMaster,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		
		Object[][] repData = getSqlModel().getSingleResult(getQuery(6));
		if (repData != null && repData.length > 0) {
			castMaster.setModeLength("true");
			castMaster.setTotalRecords(String.valueOf(repData.length));

			String[] pageIndex = Utility.doPaging(castMaster.getMyPage(),
					repData.length, 20);
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
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				CastCenterManagementMaster bean1 = new CastCenterManagementMaster();
				bean1.setCastCode(checkNull(String.valueOf(repData[i][0])));
				bean1.setCastAbbr(checkNull(String.valueOf(repData[i][1])));
				bean1.setCastName(checkNull(String.valueOf(repData[i][2])));
				bean1.setSubcastAbbr(checkNull(String.valueOf(repData[i][3])));
				bean1.setSubcastName(checkNull(String.valueOf(repData[i][4])));
				List.add(bean1);
			}

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
	public boolean deleteList(CastCenterManagementMaster castMaster,
			String[] code) {
			// TODO Auto-generated method stub
			boolean result = false;
			boolean flag = false;
			int cnt = 0;
			if (code != null) {
				for (int i = 0; i < code.length; i++) {

					if (!code[i].equals("")) {
						Object[][] delete = new Object[1][1];
						delete[0][0] = code[i];
						flag = getSqlModel().singleExecute(getQuery(3), delete);
						if (!flag) {
							cnt++;
						}
						
					}// end of nested if
				}// end of loop
			}// end of if
			if (cnt > 0) {
				result = false;
			}// end of if
			else
				result = true;
			return result;

		}
	
	

	/**
	 * to check the Duplicate value or data
	 * 
	 */
	public boolean checkDuplicate(CastCenterManagementMaster castMaster) {
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
	public boolean checkDuplicateMod(CastCenterManagementMaster castMaster) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_COST_CENTER WHERE UPPER(COST_CENTER_NAME) LIKE '"
				+ castMaster.getCastName().trim().toUpperCase()
				+ "' AND COST_CENTER_ID not in ("
				+ castMaster.getCastCode() + ")";
		Object obj[][] = getSqlModel().getSingleResult(query);
		if (obj != null && obj.length > 0) {
			return true;
		}
		return result;
	}
	
		/**
		 *  to modify the record in double click in the list
		 * @param accountCategory
		 */

	public void calforedit(CastCenterManagementMaster castMaster) {
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


		public void delDtl(CastCenterManagementMaster castMaster,
				String[] code, String[] abbr, String[] name) {
			// TODO Auto-generated method stub			
				try {
					ArrayList<Object> list = new ArrayList<Object>();

					if (name != null) {
						for (int i = 0; i < name.length; i++) {
						CastCenterManagementMaster bean = new CastCenterManagementMaster();
							if ((String.valueOf(code[i]).equals(""))) {
								bean.setSubCAbbr(abbr[i]);
								bean.setSubCName(name[i]);
								list.add(bean);

							}

						}
					}
					castMaster.setList(list);
				} catch (Exception e) {
					e.printStackTrace();

					// TODO: handle exception
				}

			}
/**
 *  to add the subcost record in the list
 * @param castMaster
 * @param srNo
 * @param abbr
 * @param name
 * @param check
 */

		public void addItem(CastCenterManagementMaster castMaster,
				String[] srNo, String[] abbr, String[] name, int check) {
			// TODO Auto-generated method stub
			
			ArrayList<Object> tableList = new ArrayList<Object>();
			logger.info("in new add");
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					CastCenterManagementMaster bean = new CastCenterManagementMaster();
					bean.setSrNo(String.valueOf(i + 1));
					bean.setSubCAbbr(abbr[i]);
					bean.setSubCName(name[i]);
					tableList.add(bean);
				}
			}
			if (check == 1) {
				castMaster.setSrNo(String.valueOf(tableList.size() + 1));
				castMaster.setSubCAbbr(castMaster.getSubcastAbbr());
				castMaster.setSubCName(castMaster.getSubcastName());
				tableList.add(castMaster);
			} else if (check == 0) {
				tableList.remove(Integer.parseInt(castMaster.getSubcode()) - 1);
			}
			
			if (tableList.size() != 0)
				castMaster.setTableLength("1");
			else
				castMaster.setTableLength("0");
			castMaster.setList(tableList);
			
			
		}

		/**
		 *  to modify the subcost records from the list
		 * @param castMaster
		 * @param srNo
		 * @param abbr
		 * @param name
		 * @param check
		 */

		public void moditem(CastCenterManagementMaster castMaster,
				String[] srNo, String[] abbr, String[] name, int check) {
			// TODO Auto-generated method stub
				ArrayList<Object> tableList = new ArrayList<Object>();
				logger.info("in  moditem");
				if (srNo != null) {

					for (int i = 0; i < srNo.length; i++) {
						CastCenterManagementMaster	bean = new CastCenterManagementMaster();
						logger.info("hidden edit==" + castMaster.getHiddenEdit());
						if (i == Integer.parseInt(castMaster.getHiddenEdit()) - 1) {
					
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
				
				if (tableList.size() != 0)
					castMaster.setTableLength("1");
				else
					castMaster.setTableLength("0");
				castMaster.setList(tableList);

			}

		/**
		 *  to delete the subcost list 
		 * @param castMaster
		 * @return
		 */

		public boolean deleteCast(CastCenterManagementMaster castMaster) {
			// TODO Auto-generated method stub
				Object delObj[][] = new Object[1][1];
				delObj[0][0] = castMaster.getCastCode();
				boolean res = getSqlModel().singleExecute(getQuery(3), delObj);
				if (res)
					return getSqlModel().singleExecute(getQuery(4), delObj);
				else
					return false;
			}
/**
 *  to generate the report
 * @param castMaster
 * @param response
 * @param label
 */

		public void generateReport(CastCenterManagementMaster castMaster,
				HttpServletResponse response, String[] label) {
			// TODO Auto-generated method stub
			
			
			String reportName = "Onsite Posting Master";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
					reportName);
			rg.setFName("Cast Center Management Master.Pdf");
			String queryDes = "SELECT  COST_CENTER_ABBR, COST_CENTER_NAME FROM HRMS_COST_CENTER ORDER BY COST_CENTER_ID";
			Object[][] data = getSqlModel().getSingleResult(queryDes);
			Object[][] Data = new Object[data.length][3];
			if (data != null && data.length > 0) {
				int j = 1;
				for (int i = 0; i < data.length; i++) {
					Data[i][0] = j;
					Data[i][1] = data[i][0];
					Data[i][2] = data[i][1];

					j++;
				}
				int cellwidth[] = { 10, 20, 20 };
				int alignment[] = { 1, 0, 0 };
				rg.addTextBold("Cast Center Management Master", 0, 1, 0);
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(label, Data, cellwidth, alignment);
			} else {
				rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
			}

			rg.createReport(response);

		}
			
		}
			
	
			
		

