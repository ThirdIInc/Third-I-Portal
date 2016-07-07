package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.paradyne.lib.Utility;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.admin.master.CenterMaster;
import org.paradyne.bean.admin.master.DivisionMaster;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

/**
 * 
 * @author AA0650
 *
 */
/**
 *  to  define the business logic for center/ branch master
 */
public class CenterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	CenterMaster cntrMaster = null;

	/* for checking duplicate entry of record during insertion*/

	public boolean checkDuplicate(CenterMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_CENTER WHERE UPPER(CENTER_NAME) LIKE '"
				+ bean.getCenterName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/* for checking duplicate entry of record during modification*/

	public boolean checkDuplicateMod(CenterMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_CENTER WHERE UPPER(CENTER_NAME) LIKE '"
				+ bean.getCenterName().trim().toUpperCase()
				+ "' AND CENTER_ID not in(" + bean.getCenterID() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;

	}

	/* for inserting record*/
	public boolean addCenter(CenterMaster bean) {
		if (!checkDuplicate(bean)) {

			String query = "SELECT NVL(MAX(CENTER_ID),0)+1 FROM  HRMS_CENTER";
			Object[][] rel = getSqlModel().getSingleResult(query);
			bean.setCenterID(String.valueOf(rel[0][0]));// to inster the center details  in to	center master
			Object addObj[][] = new Object[1][16];
			addObj[0][0] = bean.getCenterName().trim(); // center name
			addObj[0][1] = bean.getLocId().trim(); // location name/id
			addObj[0][2] = bean.getAdd1().trim(); // address1  of the center 
			addObj[0][3] = bean.getAdd2().trim(); // address2  of the center 
			addObj[0][4] = bean.getAdd3().trim(); // address3  of the center 
			addObj[0][5] = bean.getCity().trim(); // city name
			addObj[0][6] = bean.getPin().trim(); // pin number
			addObj[0][7] = bean.getFax().trim(); // fax number
			addObj[0][8] = bean.getTel().trim(); // telephone number
			addObj[0][9] = bean.getEsiZone().trim(); // esi zone
			addObj[0][10] = bean.getPtZone().trim(); // pt zone 
			addObj[0][11] = bean.getPfZone().trim(); // pf zone
			addObj[0][12] = bean.getCenterAbbr().trim(); // center abbreviation			
			logger
			.info("helooooooabcd");
	 
			System.out.println("bean.getIsActive()-------------"+bean.getIsActive());
			
			addObj [0][13]=bean.getIsActive().trim();//is active field(y/n).
			addObj[0][14]=bean.getZone().trim();
			addObj[0][15]= bean.getCenterType();
			logger.info("error here");
			
			return getSqlModel().singleExecute(getQuery(1), addObj);

		}//end of if
		else {
			return false;

		}//end of else
	}

	/* for modifing the record*/

	public boolean modCenter(CenterMaster bean) {
		if (!checkDuplicateMod(bean)) {
			// to  get  the data  for modify the center details
			Object addObj[][] = new Object[1][17];
			addObj[0][0] = bean.getCenterName().trim(); // center name
			addObj[0][1] = bean.getLocId().trim(); // location  name/Id
			addObj[0][2] = bean.getAdd1().trim(); // address1
			addObj[0][3] = bean.getAdd2().trim(); // address1
			addObj[0][4] = bean.getAdd3().trim(); // address1
			addObj[0][5] = bean.getCity().trim(); // city name  
			addObj[0][6] = bean.getPin().trim(); // pin number
			addObj[0][7] = bean.getFax().trim(); // fax number
			addObj[0][8] = bean.getTel().trim(); // telephone number 
			addObj[0][9] = bean.getEsiZone().trim(); // esi zone
			addObj[0][10] = bean.getPtZone().trim(); // pt zone
			addObj[0][11] = bean.getPfZone().trim(); // pf zone
			addObj[0][12] = bean.getCenterAbbr().trim(); // center abbreviation
			addObj[0][13] = bean.getIsActive().trim(); // center id
			addObj[0][14]=bean.getZone().trim();
			addObj[0][15]= bean.getCenterType().trim();
			addObj [0][16]=bean.getCenterID().trim(); //is active field(y/n).
			
			
			return getSqlModel().singleExecute(getQuery(2), addObj);
		}//end of if
		else
			return false;
	}

	/* for deleting the record */
	public boolean deleteCenter(CenterMaster bean) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getCenterID(); // to get the  center id for deleting th eparticular reord(one record)
		//logger.info("Center ID"+String.valueOf(addObj[0][0]));
		boolean result = getSqlModel().singleExecute(getQuery(3), addObj);
		//logger.info("result"+result);
		return result;
	}

	/**
	 *  to display the  center detail
	 * @param bean
	 */
	public void getCenterRecord(CenterMaster bean) {
		Object addObj[] = new Object[1];
		addObj[0] = bean.getCenterID(); //   to get the center id 

		Object data[][] = getSqlModel().getSingleResult(getQuery(4), addObj);
		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				bean.setCenterID(checkNull(String.valueOf(data[0][0]))); 
				bean.setCenterName(checkNull(String.valueOf(data[0][1]))); 
				bean.setLocId(checkNull(String.valueOf(data[0][2])));
				bean.setCity(checkNull(String.valueOf(data[0][3]))); 
				bean.setAdd1(checkNull(String.valueOf(data[0][4]))); 
				bean.setAdd2(checkNull(String.valueOf(data[0][5]))); 
				bean.setAdd3(checkNull(String.valueOf(data[0][6]))); 
				// bean.setCity(checkNull(String.valueOf(data[0][7])));
				bean.setPin(checkNull(String.valueOf(data[0][7]))); 
				bean.setFax(checkNull(String.valueOf(data[0][8]))); 
				bean.setTel(checkNull(String.valueOf(data[0][9]))); 
				bean.setEsiZone(checkNull(String.valueOf(data[0][10]))); 
				bean.setPtZone(checkNull(String.valueOf(data[0][11]))); 
				bean.setPfZone(checkNull(String.valueOf(data[0][12]))); 
				bean.setCenterAbbr(checkNull(String.valueOf(data[0][13]))); 
				bean.setIsActive(checkNull(String.valueOf(data[0][14])));
				bean.setZone(checkNull(String.valueOf(data[0][15])));
				bean.setCenterType(checkNull(String.valueOf(data[0][16])));
			}
		}
	}

	/**
	 *  to generate the report
	 * @param bean
	 */
	public void getCenterReport(CenterMaster bean) {

		Object data[][] = getSqlModel().getSingleResult(getQuery(5));
		ArrayList<Object> cntrList = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			CenterMaster bean1 = new CenterMaster();
			bean1.setCenterID(checkNull(String.valueOf(data[i][0])));
			bean1.setCenterName(checkNull(String.valueOf(data[i][1])));
			bean.setLocId(checkNull(String.valueOf(data[i][2])));
			bean.setLocName(checkNull(String.valueOf(data[i][3])));
			bean.setAdd1(checkNull(String.valueOf(data[i][4])));
			bean.setAdd2(checkNull(String.valueOf(data[i][5])));
			bean.setAdd3(checkNull(String.valueOf(data[i][6])));
			bean.setCity(checkNull(String.valueOf(data[i][7])));
			bean.setPin(checkNull(String.valueOf(data[i][8])));
			bean.setFax(checkNull(String.valueOf(data[i][9])));
			bean.setTel(checkNull(String.valueOf(data[i][10])));
			bean.setEsiZone(checkNull(String.valueOf(data[i][11])));
			bean.setPtZone(checkNull(String.valueOf(data[i][12])));
			bean.setPfZone(checkNull(String.valueOf(data[0][13])));
			bean.setIsActive(checkNull(String.valueOf(data[0][14])));
			bean.setCenterType(checkNull(String.valueOf(data[0][15])));
			
			cntrList.add(bean1);

		}//end of loop
		bean.setCenterList(cntrList);

	}

	/**
	 *  to check the null value 
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}//end of if
		else {
			return result;
		}//end of else
	}

	/* to generating the report*/
	public void getReport(CenterMaster cntrMaster2, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			HttpSession session, String[] label) {
		// TODO Auto-generated method stub

		/*CrystalReport cr=new CrystalReport();
		String path="org\\paradyne\\rpt\\admin\\master\\center.rpt ";
		cr.createReport(request, response, context,session, path, "");*/
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);

		String reportName = "\n\nBranch Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Branch Master");
		String query = "SELECT CENTER_NAME,LOCATION_NAME,CENTER_ADDRESS1||''||CENTER_ADDRESS2||''||CENTER_ADDRESS3,"
					+ " CENTER_FAX,CENTER_TELEPHONE ,DECODE(HRMS_CENTER.IS_ACTIVE,'Y','Yes','N','No'),"
					+ " DECODE(HRMS_CENTER.CENTER_ZONE,'EA','East','NO','North','SO','South','WE','West','CE','Central')," 
					+ " DECODE(HRMS_CENTER.CENTER_ISSITE,'B','Branch','C','Client_Site') "
					+ " FROM HRMS_CENTER "
					+ " LEFT JOIN HRMS_LOCATION ON (HRMS_CENTER.CENTER_LOCATION=HRMS_LOCATION.LOCATION_CODE)"
					+ " ORDER  BY UPPER(CENTER_NAME)";
		Object[][] data = getSqlModel().getSingleResult(query);
		Object[][] Data = new Object[data.length][9];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				Data[i][4] = data[i][3];
				Data[i][5] = data[i][4];
				Data[i][6] = data[i][5];
				Data[i][7] = data[i][6];
				Data[i][8] = data[i][7];
				j++;
			}
			int cellwidth[] = { 15, 30, 25, 40, 25, 25, 20,15,20 };
			int alignment[] = { 1, 0, 0, 0, 1, 1, 0, 0, 0 };
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

	/* generating the list onload */
	public void hasData(CenterMaster cntrMaster, HttpServletRequest request) {

		// get the center data to display in the list 

		Object[][] repData = getSqlModel().getSingleResult(getQuery(6));
		if (repData != null && repData.length > 0) {
			cntrMaster.setTotalRecords(String.valueOf(repData.length)); // to display the total records inn the list  
			cntrMaster.setModeLength("true");
			String[] pageIndex = Utility.doPaging(cntrMaster.getMyPage(),
					repData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2]))); // to display  the total number of page 
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3]))); // to display the page number 
			if (pageIndex[4].equals("1"))
				cntrMaster.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				CenterMaster bean1 = new CenterMaster();
				bean1.setCenterID(checkNull(String.valueOf(repData[i][0]))); // center id
				bean1.setCenterName(checkNull(String.valueOf(repData[i][1]))); // center name

				bean1.setAddress(checkNull(String.valueOf(repData[i][2]))); // center address
				bean1.setIsActive(checkNull(String.valueOf(repData[i][3])));//is active (y/n).
				List.add(bean1); // add the details in the list
			}//end of loop
			cntrMaster.setCenterList(List);
		}

	}

	/* for selecting the data from list */
	public void calforedit(CenterMaster cntrMaster) {

		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER where CENTER_ID= "
				+ cntrMaster.getHiddencode();

		// to get the record in double clck for update
		Object[][] data = getSqlModel().getSingleResult(query);
		cntrMaster.setCenterID(String.valueOf(data[0][0])); // center id
		cntrMaster.setCenterName(String.valueOf(data[0][1])); // center name

	}

	/**
	 *  to delete the  single record
	 * @param cntrMaster
	 * @return
	 */
	public boolean calfordelete(CenterMaster cntrMaster) {

		Object[][] delete = new Object[1][1];
		delete[0][0] = cntrMaster.getHiddencode(); // to get id for delete the single record
		return getSqlModel().singleExecute(getQuery(3), delete);
		// TODO Auto-generated method stub

	}

	/* for deleting one or more  records from list*/
	public boolean deleteCenter(CenterMaster cntrMaster, String[] code) {
		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					//logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"+code[i]);	
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(getQuery(3), delete);
					if (!flag) {
						cnt++;
					}//end of if
					//result=true;
				}//end of nested if
			}//end of loop
		}//end of nested if
		if (cnt > 0) {
			result = false;
		}//end of if
		else
			result = true;
		return result;

	}

}
