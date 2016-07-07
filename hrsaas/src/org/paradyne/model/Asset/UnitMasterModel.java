package org.paradyne.model.Asset;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Asset.UnitMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * 
 * @author krishna
 *Date 25/07/2008
 */

public class UnitMasterModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(UnitMasterModel.class);

	/* method name : add 
	 * purpose     : to add the record
	 * return type : boolean
	 * parameter   : bean
	 */
	public boolean add(UnitMaster bean) {
		if (!checkDuplicate(bean)) {
			Object addObj[][] = new Object[1][2];
			addObj[0][0] = checkNull(String.valueOf(bean.getUnitName().trim()));
			addObj[0][1] = checkNull(String.valueOf(bean.getIsActive().trim()));
			String query="SELECT NVL(MAX(UNIT_CODE),0)+1 FROM HRMS_UNIT_MASTER";
			Object [][] repData = getSqlModel().getSingleResult(query);
			boolean result= getSqlModel().singleExecute(getQuery(1), addObj);
			
			if(result){
				if(repData!=null&& repData.length>0)
				{	bean.setUnitCode(String.valueOf(repData[0][0]));
				return result;
				}
				else 	
					return false;
			}else
				return false;
		}//end of if
		else {
			return false;
		}//end of else
	}

	/* method name : mod 
	 * purpose     : to modify the record
	 * return type : boolean
	 * parameter   : bean
	 */
	public boolean mod(UnitMaster bean) {
		if (!checkDuplicateMod(bean)) {
			Object modObj[][] = new Object[1][3];

			modObj[0][0] = checkNull(String.valueOf(bean.getUnitName().trim()));
			modObj[0][1] = checkNull(String.valueOf(bean.getIsActive().trim()));
			modObj[0][2] = checkNull(String.valueOf(bean.getUnitCode().trim()));
			
			return getSqlModel().singleExecute(getQuery(2), modObj);
		}//end of if
		else {
			return false;
		}//end of else
	}

	/* method name : checkDuplicate 
	 * purpose     : for checking duplicate entry of record during insertion
	 * return type : boolean
	 * parameter   : bean
	 */

	public boolean checkDuplicate(UnitMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_UNIT_MASTER WHERE UPPER(UNIT_NAME) LIKE '"
				+ bean.getUnitName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;
	}

	/* method name : checkDuplicateMod 
	 * purpose     : for checking duplicate entry of record during modification 
	 * return type : boolean
	 * parameter   : bean
	 */

	public boolean checkDuplicateMod(UnitMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_UNIT_MASTER WHERE UPPER(UNIT_NAME) LIKE '"
				+ bean.getUnitName().trim().toUpperCase()
				+ "' AND UNIT_CODE not in(" + bean.getUnitCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;

	}

	/* method name : delete 
	 * purpose     : to delete the record 
	 * return type : boolean
	 * parameter   : bean
	 */
	public boolean delete(UnitMaster bean) {
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = String.valueOf(bean.getUnitCode());
		return getSqlModel().singleExecute(getQuery(3), delObj);
	}

	/* method name : delete 
	 * purpose     : to display the data in the form while loading
	 * return type : void
	 * parameter   : bean,request
	 */

	public void Data(UnitMaster bean, HttpServletRequest request) {
		
		
		
		Object [][] obj = getSqlModel().getSingleResult(getQuery(4));
		
		
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),obj.length,20);	
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
			if (obj != null && obj.length > 0) {

			//	for (int i = 0; i < obj.length; i++) {
				 for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
					
					 UnitMaster bean1 = new UnitMaster();
						bean1.setUnitCode(checkNull(String.valueOf(obj[i][0])));
						bean1.setUnitName(checkNull(String.valueOf(obj[i][1])));
						bean1.setIsActive(checkNull(String.valueOf(obj[i][2])));
					list.add(bean1);
				}
				
				 bean.setTotalRecords(""+obj.length);
			}
			else{
			
				bean.setRecordsLength("false");
				
			}
			//bean.setEmpList(list);
			bean.setTotalRecords(String.valueOf(list.size()));
		    if(list.size()>0)
		    	bean.setRecordsLength("true");
		    else
		    	bean.setRecordsLength("false");
		
		    bean.setRecordList(list);
		
		
		
	}

	/* method name : calforedit 
	 * purpose     : to edit the record
	 * return type : void
	 * parameter   : bean
	 */
	public void calforedit(UnitMaster bean) {
		try {
			String query = "SELECT UNIT_CODE,NVL(UNIT_NAME,' '),UNIT_ISACTIVE FROM HRMS_UNIT_MASTER WHERE UNIT_CODE="
					+ bean.getHiddencode();
			Object[][] data = getSqlModel().getSingleResult(query);
			bean.setUnitCode(checkNull(String.valueOf(data[0][0])));
			bean.setUnitName(checkNull(String.valueOf(data[0][1])));
			bean.setIsActive(checkNull(String.valueOf(data[0][2])));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/* method name : calfordelete 
	 * purpose     : to delete the record
	 * return type : boolean
	 * parameter   : bean
	 */

	public boolean calfordelete(UnitMaster bean) {

		Object[][] delete = new Object[1][1];
		delete[0][0] = bean.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);

	}

	/* method name : deletecheckedRecords 
	 * purpose     : to delete the checked records
	 * return type : boolean
	 * parameter   : bean,code
	 */

	public boolean deletecheckedRecords(UnitMaster bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(3), delete);
					if (!result)
						count++;

				}//end of if
			}//end of for loop
		}//end of if
		if (count != 0) {
			result = false;
			return result;
		}//end of if 
		else {
			result = true;
			return result;
		}//end of else
	}

	/* method name : report 
	 * purpose     : to generate the report for the selected application
	 * return type : void
	 * parameter   : bean,request,response
	 */

	public void report(UnitMaster bean, HttpServletRequest request,
			HttpServletResponse response) {

		String s = "\n\nUnit Master Report\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				s);
		String query = " SELECT UNIT_CODE,NVL(UNIT_NAME,'') FROM HRMS_UNIT_MASTER ORDER BY UNIT_CODE";
		// IMS_STATION_CODE,
		Object result[][] = getSqlModel().getSingleResult(query);
		rg.addFormatedText(s, 6, 0, 1, 0);
		if (result.length > 0)

		{

			Object[][] tabledata = new Object[result.length][2];

			for (int i = 0; i < result.length; i++) {
				tabledata[i][0] = String.valueOf(i + 1);
				tabledata[i][1] = checkNull(String.valueOf(result[i][1]));

			}//end of for loop
			int[] bcellWidth = { 10, 40 };
			int[] bcellAlign = { 0, 0 };
			String appCol[] = { "Sr no", "Unit Name" };
			rg.tableBody(appCol, tabledata, bcellWidth, bcellAlign);
		}//end of if
		rg.createReport(response);
	}

	/* method name : checkNull 
	 * purpose     : to check the null values and to make them as blank.
	 * return type : String
	 * parameter   : result
	 */

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void setData(UnitMaster unitMaster) {
		// TODO Auto-generated method stub
		try{
			 
			String query = " SELECT UNIT_CODE,UNIT_NAME ,UNIT_ISACTIVE FROM HRMS_UNIT_MASTER "
					+" where UNIT_CODE="+unitMaster.getUnitCode();
			Object data[][] = getSqlModel().getSingleResult(query);
			//AssetType bean1 = new AssetType();
			unitMaster.setUnitCode(checkNull(String.valueOf(data[0][0])));
			unitMaster.setUnitName(checkNull(String.valueOf(data[0][1])));
			unitMaster.setIsActive(checkNull(String.valueOf(data[0][2])));
				logger.info("asset moder");
			}//end of loop
			
			catch(Exception e){
				logger.error("asset model data1 --- "+e);
				e.printStackTrace();
		   }
	}

}
