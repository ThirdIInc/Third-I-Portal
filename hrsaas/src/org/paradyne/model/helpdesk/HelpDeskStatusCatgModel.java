/**
 * 
 */
package org.paradyne.model.helpdesk;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.helpdesk.HelpDeskStatusCatg;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa0623
 *
 */
public class HelpDeskStatusCatgModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(HelpDeskStatusCatgModel.class);

	public void getRecords(HelpDeskStatusCatg statusCatg,
			HttpServletRequest request) {
		String query = " SELECT NVL(STATUS_CATAGORY_NAME,' '), NVL(STATUS_ABBREV,' '), "
			+ " STATUS_ORDER, STATUS_ISLAST, STATUS_CATAGORY_ID"
			+ " FROM HELPDESK_SLA_STATUS_CATAGORY "
			+ " WHERE STATUS_CATAGORY_ID ="+statusCatg.getStatusCategCode();
		Object[][] data = getSqlModel().getSingleResult(query);
		if(data!=null && data.length>0){
			statusCatg.setStatusCateg(String.valueOf(data[0][0]));
			statusCatg.setStatusAbbrev(String.valueOf(data[0][1]));
			statusCatg.setStatusOrder(String.valueOf(data[0][2]));
			if(String.valueOf(data[0][0]).equals("L"))
				statusCatg.setIsStatusLast("true");
			else
				statusCatg.setIsStatusLast("false");
		}
		
	}

	public void getStatusCategList(HelpDeskStatusCatg statusCatg,
			HttpServletRequest request) {
		try {
			int length = 0;
			String query = " SELECT NVL(STATUS_CATAGORY_NAME,' '), NVL(STATUS_ABBREV,' '), "
					+ " STATUS_ORDER, STATUS_ISLAST, STATUS_CATAGORY_ID"
					+ " FROM HELPDESK_SLA_STATUS_CATAGORY"
					+ " ORDER BY STATUS_CATAGORY_ID";

			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				statusCatg.setModeLength("true");
				// to display the total number of record in the list
				statusCatg.setTotalRecords(String.valueOf(data.length));

				String[] pageIndex = Utility.doPaging(statusCatg.getMyPage(),
						data.length, 20);
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
					statusCatg.setMyPage("1");
				ArrayList<Object> List = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					HelpDeskStatusCatg statusCatgBean = new HelpDeskStatusCatg();

					statusCatgBean.setStatusCategItr(String.valueOf(data[i][0])
							.trim());
					statusCatgBean.setStatusAbbrevItr(String.valueOf(data[i][1])
							.trim());
					statusCatgBean.setStatusCategCodeItr(String.valueOf(data[i][4]));
					List.add(statusCatgBean);
				}

				statusCatg.setStatusCategList(List);
				length = data.length;
				statusCatg.setTotalRecords(String.valueOf(length));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean delChkdRec(String[] code) {
		int count = 0;
		boolean result = false;
		for (int i = 0; i < code.length; i++) {
			if (!code[i].equals("")) {

				Object[][] delete = new Object[1][1];
				delete[0][0] = code[i];
				String query = " DELETE FROM HELPDESK_SLA_STATUS_CATAGORY WHERE STATUS_CATAGORY_ID=?";
				result = getSqlModel().singleExecute(query, delete);
				if (!result) {
					count++;
				}
			}
		}

		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	public boolean deleteStatusCateg(HelpDeskStatusCatg statusCatg) {
		// to delete the single record after clicking on saving or searching
		// button
		String query = " DELETE FROM HELPDESK_SLA_STATUS_CATAGORY WHERE STATUS_CATAGORY_ID="
			+statusCatg.getStatusCategCode();
		return getSqlModel().singleExecute(query);
	}

	public void getStatusCategOnDblClick(HelpDeskStatusCatg statusCatg) {
		try {

			String query = " SELECT NVL(STATUS_CATAGORY_NAME,' '), NVL(STATUS_ABBREV,' '), "
				+ " STATUS_ORDER, STATUS_ISLAST, STATUS_CATAGORY_ID"
				+ " FROM HELPDESK_SLA_STATUS_CATAGORY "
				+ " WHERE STATUS_CATAGORY_ID ="+statusCatg.getHiddencode();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				statusCatg.setStatusCateg(String.valueOf(data[0][0]));
				statusCatg.setStatusAbbrev(String.valueOf(data[0][1]));
				statusCatg.setStatusOrder(String.valueOf(data[0][2]));
				if(String.valueOf(data[0][3]).equals("L"))
					statusCatg.setIsStatusLast("true");
				else
					statusCatg.setIsStatusLast("false");
				statusCatg.setStatusCategCode(String.valueOf(data[0][4]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String addStatusCategories(HelpDeskStatusCatg statusCatg) {
		Object[][] add = new Object[1][4];
		String flag = "";
		boolean result = false;
		try {
			add[0][0] = initCap(statusCatg.getStatusCateg().trim()); 
			add[0][1] = statusCatg.getStatusAbbrev().trim().toUpperCase(); 
			add[0][2] = statusCatg.getStatusOrder();
			if(statusCatg.getIsStatusLast().equals("true"))
				add[0][3] = "L";
			else
				add[0][3] = "";

			if (!checkLastStatus(statusCatg)) {
				if (!checkDuplicate(statusCatg)) {
					// to get the data from designation/rank
					String insQuery = "INSERT INTO HELPDESK_SLA_STATUS_CATAGORY (STATUS_CATAGORY_ID, "
							+ " STATUS_CATAGORY_NAME, STATUS_ABBREV, STATUS_ORDER, STATUS_ISLAST) "
							+ " VALUES ((SELECT NVL(MAX(STATUS_CATAGORY_ID),0)+1 FROM HELPDESK_SLA_STATUS_CATAGORY ),?,?,?,?)";
					result = getSqlModel().singleExecute(insQuery, add);
					if (result) {
						flag = "saved";
					} else {
						flag = "error";
					}
				} else {
					flag = "duplicate";
				}
			} else {
				flag = "last";
			}
		} catch (Exception e) {
			logger.error("Exception caught: ");
			e.printStackTrace();
		}
		return flag;
	}

	public boolean checkDuplicate(HelpDeskStatusCatg statusCatg) {
		boolean result = false;
		String query = "SELECT * FROM HELPDESK_SLA_STATUS_CATAGORY WHERE UPPER(STATUS_CATAGORY_NAME) LIKE '"
				+ statusCatg.getStatusCateg().trim().toUpperCase()+"' or UPPER(STATUS_ABBREV) LIKE '"
				+ statusCatg.getStatusAbbrev().trim().toUpperCase() + "'";

		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}
	
	public boolean checkLastStatus(HelpDeskStatusCatg statusCatg) {
		boolean result = false;
		if (statusCatg.getIsStatusLast().equals("true")) {
			String query = "SELECT * FROM HELPDESK_SLA_STATUS_CATAGORY WHERE STATUS_ISLAST='L'";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}
		}else
			result = false;
		return result;
	}

	public String modStatusCategories(HelpDeskStatusCatg statusCatg) {
		Object[][] data = new Object[1][5];
		String editFlag = "";
		boolean result = false;
		try {

			data[0][0] = initCap(statusCatg.getStatusCateg().trim());
			data[0][1] = statusCatg.getStatusAbbrev().trim().toUpperCase();
			data[0][2] = statusCatg.getStatusOrder();
			if (statusCatg.getIsStatusLast().equals("true"))
				data[0][3] = "L";
			else
				data[0][3] = "";
			data[0][4] = statusCatg.getStatusCategCode();
			if (!checkLastStatus(statusCatg)) {
				if (!checkDuplicateMod(statusCatg)) {
					// to get the data for modifying the record
					String query = "UPDATE HELPDESK_SLA_STATUS_CATAGORY SET STATUS_CATAGORY_NAME=?, "
							+ " STATUS_ABBREV=? ,STATUS_ORDER=?, STATUS_ISLAST=? where STATUS_CATAGORY_ID=?";
					result = getSqlModel().singleExecute(query, data);
					if (result) {
						editFlag = "modified";
					} else {
						editFlag = "error";
					}
				} else {
					editFlag = "duplicate";
				}
			} else {
				editFlag = "last";
			}
		} catch (Exception e) {
			logger.error("Exception was raised--->"+e);
		}
		return editFlag;
	}

	public boolean checkDuplicateMod(HelpDeskStatusCatg statusCatg) {
		boolean result = false;
		Object[][] data = null;
		try {
			String query = "SELECT * FROM HELPDESK_SLA_STATUS_CATAGORY WHERE UPPER(STATUS_CATAGORY_NAME) LIKE '"
				+ statusCatg.getStatusCateg().trim().toUpperCase()
				+ "' AND STATUS_CATAGORY_ID not in(" + statusCatg.getStatusCategCode() + ")";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {

		}
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}
	
	public static String initCap(String name) {
		String properName = "";

		try {
			properName = name.substring(0, 1).toUpperCase()
					+ name.substring(1, name.length());
		} catch (Exception e) {
			return properName;
		}
		return properName;
	}
		
}
