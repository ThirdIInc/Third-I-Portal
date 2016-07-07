package org.paradyne.model.TravelManagement.Master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.Master.CurrencyMaster;
import org.paradyne.bean.admin.master.TradeMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
public class CurrencyMasterModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	
	/* 
	 * method name : checkNull 
	 * purpose     : check the null value
	 * return type : String
	 * parameter   : String result
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	/* method name : data 
	 * purpose     : to show the assets type in the list
	 * return type : void
	 * parameter   : AssetType instance, HttpServletRequest request
	 */
	public void data(CurrencyMaster bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Object[][] obj = getSqlModel().getSingleResult(getQuery(4));
		
		String[] pageIndex = Utility.doPaging(bean.getMyPage(), obj.length, 20); 
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
				 for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){

					 CurrencyMaster  bean1 = new CurrencyMaster ();

						bean1.setCurCode(checkNull(String.valueOf(obj[i][0])));
						bean1.setCurName(checkNull(String.valueOf(obj[i][1])));	
						bean1.setCurAbbr(checkNull(String.valueOf(obj[i][2])));	
						bean1.setDescription(checkNull(String.valueOf(obj[i][3])));	
						bean1.setCurStatus(checkNull(String.valueOf(obj[i][4])));

						list.add(bean1);
				}
				
				 bean.setTotalRecords(""+obj.length);
				 bean.setListLength("true");
			}
			else{				
				bean.setListLength("false");
				}			
		    bean.setTableList(list);
		
	}

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(CurrencyMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_CURRENCY WHERE UPPER(CURRENCY_NAME) LIKE '"
				+ bean.getCurName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;

	}

	/* for checking duplicate entry of record during modificaion */
	public boolean checkDuplicateMod(CurrencyMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_CURRENCY WHERE CURRENCY_NAME LIKE '"
				+ bean.getCurName().trim()
				+ "' AND CURRENCY_ID not in(" + bean.getCurCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;

	}

	
	public boolean addCurrency(CurrencyMaster bean) {
		// TODO Auto-generated method stub
		if(!checkDuplicate(bean))
		{
			Object[][] addObj = new Object[1][4];
			addObj[0][0] = bean.getCurName().trim();
			addObj[0][1] = bean.getCurAbbr().trim();
			addObj[0][2] = checkNull(bean.getDescription()).trim();
			addObj[0][3] =  checkNull(bean.getCurStatus()).trim();
			//System.out.println("Code -- > "+rel[0][0].toString());
			System.out.println("Name -- > "+addObj[0][0].toString());
			System.out.println("Abbr -- > "+addObj[0][1].toString());
			System.out.println("Desc -- > "+addObj[0][2].toString());
			System.out.println("Status -- > "+addObj[0][3].toString());
			boolean result = getSqlModel().singleExecute(getQuery(1),addObj);
			System.out.println("b--------------------> "+result);
			String query = "SELECT MAX(CURRENCY_ID) FROM HRMS_CURRENCY";
			Object[][] rel =  getSqlModel().getSingleResult(query);
			if(result){
				bean.setCurCode(String.valueOf(rel[0][0]));
				}
			logger.info("code"+bean.getCurCode());
			return result;
		}
		else
			return false;
	}

	public boolean modCurrency(CurrencyMaster bean) {
		// TODO Auto-generated method stub
		if(!checkDuplicateMod(bean))
		{
			logger.info("it is not duplicate");
			Object[][] modObj = new Object[1][5];
			modObj[0][0] = bean.getCurName().trim();
			modObj[0][1] = bean.getCurAbbr().trim();
			modObj[0][2] = checkNull(bean.getDescription()).trim();
			modObj[0][3] = checkNull(bean.getCurStatus()).trim();
			modObj[0][4] = bean.getCurCode();
			return getSqlModel().singleExecute(getQuery(2),modObj);
		}
		else
			return false;
	}

	/* for selecting the record from list by double clicking */
	public void callforedit(CurrencyMaster bean) {
		// TODO Auto-generated method stub
		String query = "select CURRENCY_ID, CURRENCY_NAME, CURRENCY_ABBR, CURRENCY_DESC, CURRENCY_STATUS  " 
						+ "	from HRMS_CURRENCY WHERE CURRENCY_ID ="+ bean.getHiddenCode();
		Object[][] obj = getSqlModel().getSingleResult(query);
		bean.setCurCode(String.valueOf(obj[0][0]));
		bean.setCurName(String.valueOf(obj[0][1]));
		bean.setCurAbbr(String.valueOf(obj[0][2]));
		bean.setDescription(checkNull(String.valueOf(obj[0][3])));
		bean.setCurStatus(String.valueOf(obj[0][4]));
	}

	public void getCurrencyRecord(CurrencyMaster currencyMaster) {
		// TODO Auto-generated method stub
		logger.info("I am in getCurrencyRecord - model");
		Object param[] = new Object[1];
		param[0] = currencyMaster.getCurCode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(5), param);
		logger.info("record size - "+data.length);
		currencyMaster.setCurCode(String.valueOf(data[0][0]));
		currencyMaster.setCurName(String.valueOf(data[0][1]));
		currencyMaster.setCurAbbr(String.valueOf(data[0][2]));
		currencyMaster.setDescription(checkNull(String.valueOf(data[0][3])));
		currencyMaster.setCurStatus(checkNull(String.valueOf(data[0][4])));
	}

	public boolean deleteCurrency(CurrencyMaster currencyMaster, String[] code) {
		// TODO Auto-generated method stub
		boolean result = false;
		boolean flag = false;
		int count = 0;
		if(code != null)
		{
			for(int i=0; i<code.length; i++)
			{
				if(code[i] != "")
				{
					Object delete[][] = new Object[1][1];
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(getQuery(3), delete);
					if(!flag)
					{
						count++;
					}//End of if
				}//End of if
			}//End of for
		}//End of id
		if(count >0)
			result = false;
		else
			result = true;
		return result;
	}

	public boolean deleteCurrency(CurrencyMaster currencyMaster) {
		// TODO Auto-generated method stub
		Object[][] delete = new Object[1][1];
		delete[0][0] = currencyMaster.getCurCode();
		return getSqlModel().singleExecute(getQuery(3), delete);
	}

	public void getReport(CurrencyMaster currencyMaster,
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context, String[] label) {

		try {
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			String reportName = "\n\nCurrency Master\n\n";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					"Pdf", reportName, "");
			rg.setFName("Currency Master");
			String queryDes =  " SELECT  NVL(CURRENCY_NAME,''), NVL(CURRENCY_ABBR,''),CURRENCY_DESC,decode(CURRENCY_STATUS,'A','Active','D','Deactive') " 
					+" FROM HRMS_CURRENCY ORDER BY CURRENCY_ID ";
			Object[][] data = getSqlModel().getSingleResult(queryDes);
			Object[][] Data = new Object[data.length][5];
			if (data != null && data.length > 0) {
				int j = 1;
				for (int i = 0; i < data.length; i++) {
					Data[i][0] = j;
					Data[i][1] = data[i][0];
					Data[i][2] = data[i][1];
					Data[i][3] = data[i][2];
					Data[i][4] = data[i][3];
					j++;
				}
				int cellwidth[] = { 10,20,20,30,20 };
				int alignment[] = { 1, 0,0,0,0};
				rg.addFormatedText(reportName, 6, 0, 1, 0);
				rg.addText("\n", 0, 0, 0);
				rg.addTextBold("Date :" + toDay, 0, 2, 0);
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(label, Data, cellwidth, alignment);
			} else {
				rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
			}
			rg.createReport(response);
		} catch (Exception e) {
			// TODO: handle exception
		}

	
		
	}

}
