package org.paradyne.model.admin.master;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.master.DonationMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 *  to define the business logic for designation/rank 
 */
public class DonationMasterModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.action.admin.master.RankMasterAction.class);
	

	/**
	 * following function is called to delete a record
	 * 
	 * @param bean
	 * @return
	 */
	public boolean deleteDonation(DonationMaster bean) {
		Object del[][] = null;
		try {
			del = new Object[1][1];
			// to delete the single record after clicking on saving or searching
			// button
			del[0][0] = bean.getParacode();
		} catch(Exception e) {
			// TODO: handle exception
		}
		String deleteQuery = "DELETE FROM HRMS_DONATION WHERE HRMS_DONATION.DONATION_ID=? ";

		return getSqlModel().singleExecute(deleteQuery, del);
	}
	
	
	
	/**
	 *  to check the null value
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
	 * following function is called when a record is selected from search window
	 * to set the records.
	 * 
	 * @param bean
	 */

	public void getDonation(DonationMaster bean) {
		try {

			/*String query = " SELECT RANK_NAME,RANK_ABBR,RANK_STATUS,RANK_DESC,RANK_ID,CASE WHEN RANK_STATUS='A' THEN 'Active' ELSE 'Deactive' END FROM HRMS_RANK  "
					+ " WHERE RANK_ID=" + bean.getDesignationCode();*/
			
			String query = " SELECT NVL(HRMS_DONATION.DONATION_NAME,' '),HRMS_DONATION.DONATION_PER, HRMS_DONATION.DONATION_IS_ACTIVE,HRMS_DONATION.DONATION_ID "
						+"FROM HRMS_DONATION  WHERE HRMS_DONATION.DONATION_ID =" + bean.getParacode();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				bean.setDonationName(checkNull(String.valueOf(data[0][0]).trim()));   // designation name
				bean.setPercentage(checkNull(String.valueOf(data[0][1]).trim()));   //designation abbreviation
				if (String.valueOf(data[0][2]).equals("Y")) {
					bean.setIsActive("true");
				} else {
					bean.setIsActive("false");
				}
				
				bean.setDonationHiddenCode(checkNull(String.valueOf(data[0][3])));			 // designation code
				//bean.setPageStatus(checkNull(String.valueOf(data[0][5])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 *  to get the after double click on list(displyed record)
	 * @param bean
	 */
	public void getDonationOnDoubleClick(DonationMaster bean) {
		try {
			String query = " SELECT NVL(DONATION_NAME,' '),DONATION_PER,CASE WHEN DONATION_IS_ACTIVE='Y' THEN 'Yes' ELSE 'No' END ,DONATION_ID  FROM HRMS_DONATION "
					+ " WHERE DONATION_ID = " + bean.getHiddencode();
			
			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				bean.setDonationName(String.valueOf(data[0][0]).trim()); // donation name
				
				bean.setPercentage(String.valueOf(data[0][1]).trim()); // percentage
				
				bean.setIsActive(String.valueOf(data[0][2])); // active/deactive status
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 *  to get the record in onload 
	 * @param bean
	 * @param request
	 */
	public void getRecords(DonationMaster bean, HttpServletRequest request) {
		try {
			int length=0;	
			String query = " SELECT NVL(HRMS_DONATION.DONATION_NAME,' '),HRMS_DONATION.DONATION_PER,CASE WHEN HRMS_DONATION.DONATION_IS_ACTIVE='Y' THEN 'Yes' ELSE 'No' END,HRMS_DONATION.DONATION_ID FROM HRMS_DONATION "
				+ " ORDER BY HRMS_DONATION.DONATION_ID Desc";

			Object[][] data = getSqlModel().getSingleResult(query);
			if(data!=null && data.length>0){
				bean.setModeLength("true");
			bean.setTotalRecords(String.valueOf(data.length));  //   to  display the total number of record in  the list 
			
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),data.length, 10);	
			if(pageIndex==null){
				pageIndex[0] = "0";
				pageIndex[1] ="20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "1";
			
			}
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			
			if(pageIndex[4].equals("1"))
				bean.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 	
			DonationMaster bean1 = new DonationMaster();

				bean1.setIttdonationName(String.valueOf(data[i][0]).trim());   // designation name
				bean1.setIttpercentage(String.valueOf(data[i][1]).trim());   // designation abbreviation
				bean1.setIttactive(String.valueOf(data[i][2]));       // designation status
				bean1.setIttdonationCode(String.valueOf(data[i][3]));         // designation code
				List.add(bean1);
			
			}
			
			bean.setDonationList(List);
			length=data.length;
			bean.setTotalRecords(String.valueOf(length));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * for checking duplicate entry of records during Insertion
	 * 
	 * @param bean
	 * @return
	 */
	public boolean checkDuplicate(DonationMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_DONATION WHERE UPPER(HRMS_DONATION.DONATION_NAME) LIKE '"
				+ bean.getDonationName().trim().toUpperCase() + "'";

		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	

	/**
	 * Following function is called to get the report of the Pdf format for list of Designation Records
	 * @param DonationMaster
	 * @param response
	 * @param label
	 */
	public void generateReport(DonationMaster rankMaster,
			HttpServletResponse response, String[] label) {
		try {

			String reportType = "";
			String title = " Donation Master Report ";
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("Donation Master Report");
			rds.setReportName(title);
			rds.setReportType("Pdf");
			rds.setPageSize("A4");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);

			// For Report heading
			TableDataSet repTitle = new TableDataSet();
			Object[][] repTitleObj = new Object[1][1];
			repTitleObj[0][0] = title;
			repTitle.setData(repTitleObj);
			repTitle.setCellAlignment(new int[] { 1 });
			repTitle.setCellWidth(new int[] { 100 });
			repTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			repTitle.setBorder(false);
			repTitle.setBlankRowsBelow(1);
			rg.addTableToDoc(repTitle);

			java.util.Date d = new java.util.Date();
			logger.info("Date :  " + d);
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"dd-MM-yyyy");
			String strDate = sdf.format(d);

			Object[][] dateData = new Object[1][1];
			dateData[0][0] = "Date: " + strDate;
			int[] cellWidthDateHeader = { 100 };
			int[] cellAlignDateHeader = { 2 };
			TableDataSet tableheadingDateData = new TableDataSet();
			tableheadingDateData.setData(dateData);
			tableheadingDateData.setCellWidth(cellWidthDateHeader);
			tableheadingDateData.setCellAlignment(cellAlignDateHeader);
			tableheadingDateData.setBodyFontDetails(Font.HELVETICA, 10,
					Font.NORMAL, new Color(0, 0, 0));
			tableheadingDateData.setBorder(false);
			// tableheadingDateData.setHeaderTable(true);
			tableheadingDateData.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingDateData);

			String bankNameQuery = " SELECT HRMS_DONATION.DONATION_NAME, HRMS_DONATION.DONATION_PER, DECODE(HRMS_DONATION.DONATION_IS_ACTIVE,'Y','Yes','N','No') FROM HRMS_DONATION ORDER BY HRMS_DONATION.DONATION_ID DESC ";
			Object[][] processDetail = getSqlModel().getSingleResult(
					bankNameQuery);

			// =============Details of tax deducted====================//

					Object[][] branchData = null;
					try {

						String branchDataQuery = " SELECT ROWNUM AS SRNO, HRMS_DONATION.DONATION_NAME, HRMS_DONATION.DONATION_PER, DECODE(HRMS_DONATION.DONATION_IS_ACTIVE,'Y','Yes','N','No') FROM HRMS_DONATION ORDER BY HRMS_DONATION.DONATION_ID DESC ";

						branchData = getSqlModel().getSingleResult(
								branchDataQuery);

						
						Object[][] objTabularData = new Object[branchData.length][4];
						String[] columns = new String[] {"Sr.No.", "Donation Name",
								"Percentage %", "Is Active "};

						int[] cellWidthChallan = {10, 20, 20, 20};
						int[] cellAlignChallan = {0, 0, 2, 0 };
						int count = 1;
						if (branchData != null && branchData.length > 0) {

							TableDataSet branchDetails = new TableDataSet();

							branchDetails.setHeader(columns);
							branchDetails.setData(branchData);
							branchDetails.setCellWidth(cellWidthChallan);
							branchDetails.setCellAlignment(cellAlignChallan);
							branchDetails.setBorder(true);
							branchDetails.setHeaderBGColor(new Color(225, 225,
									225));
							branchDetails.setBodyFontDetails(Font.HELVETICA, 8,
									Font.NORMAL, new Color(0, 0, 0));
							//branchDetails.setBlankRowsBelow(1);
						//	rg.addTableToDoc(branchDetails);
							
							for (int i = 0; i < branchData.length; i++) {
								
								objTabularData[i][0] = count++;
								objTabularData[i][1] = String.valueOf(processDetail[i][0]);
								objTabularData[i][2] = String.valueOf(processDetail[i][1]);
								objTabularData[i][3] = String.valueOf(processDetail[i][2]);
							//	objTabularData[i][6] = String.valueOf(expDetail[i][6]);
								
							}
							
						} else {
							TableDataSet noData = new TableDataSet();
							Object[][] noDataObj = new Object[1][1];
							noDataObj[0][0] = "No records available";
							// noData.setHeader(columns);
							noData.setData(noDataObj);
							noData.setCellAlignment(new int[] { 1 });
							noData.setCellWidth(new int[] { 100 });
							noData.setBodyFontDetails(Font.HELVETICA, 10,
									Font.NORMAL, new Color(0, 0, 0));
							noData.setBorder(true);
							noData.setBlankRowsBelow(1);
							rg.addTableToDoc(noData);
						}
						
						 TableDataSet tdstable = new TableDataSet();
							tdstable.setHeader(columns);
							tdstable.setData(objTabularData);
							tdstable.setCellAlignment(cellAlignChallan);
							tdstable.setCellWidth(cellWidthChallan);
							tdstable.setBorder(true);
							tdstable.setHeaderBGColor(new Color(225, 225, 225));
							rg.addTableToDoc(tdstable);
						
						
					} catch (Exception e) {
						logger
								.error("exception in challanDataMarDec object",
										e);
					} // end of catch
					// =============Branch details header===================//
					Object[][] branchNameDtl = new Object[1][5];// new------------->

					// =====================Branch details==================//

			rg.process();
			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function is used to save Donation Details.
	 * @param bean :DonationMaster
	 * @param request
	 * @return
	 */
	public boolean addDonationDtl(DonationMaster bean,
			HttpServletRequest request) {
boolean result = false;
		
		try {
			
			if (!checkDuplicate(bean)) {
			
			Object addObj[][] = new Object[1][3];
			//addObj[0][0] = bean.getHiddenBankId();
			addObj[0][0] = bean.getDonationName();
			addObj[0][1] = bean.getPercentage();
			
			if(bean.getIsActive().equals("true")) 
				addObj[0][2] = "Y";
			else 
				addObj[0][2] = "N";
			
			
			System.out.println("1=" + addObj[0][0]);
			System.out.println("2=" + addObj[0][1]);
			System.out.println("3=" + addObj[0][2]);
			
			String insertQuery = "INSERT INTO HRMS_DONATION"
				+ "(HRMS_DONATION.DONATION_ID, HRMS_DONATION.DONATION_NAME, HRMS_DONATION.DONATION_PER, HRMS_DONATION.DONATION_IS_ACTIVE)"
				+ " VALUES((SELECT NVL(MAX(HRMS_DONATION.DONATION_ID),0)+1 FROM HRMS_DONATION),?,?,?)";
			
			result = getSqlModel().singleExecute(insertQuery, addObj);
			
			
			/*if (result) {
				String autoCodeQuery = " SELECT NVL((BANK_ID),0) FROM HRMS_BANK ";
				Object[][] data = getSqlModel().getSingleResult(
						autoCodeQuery);
				if (data != null && data.length > 0) {
					bean.setHiddenBankId(String.valueOf(data[0][0]));
					
				}
			}*/
			
			for (int i = 0; i < addObj.length; i++) {
				System.out.println("" + addObj[0][i]);
			}
			
			}// end of if
			else {
				return false;
			}// end of else
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	/**
	 * This function is used to modify records.
	 * @param bean : DonationMaster
	 * @param request
	 * @return
	 */
	public boolean modDonationDtl(DonationMaster bean,
			HttpServletRequest request) {
		boolean result = false;
		try {

			Object updateObj[][] = new Object[1][3];
			
			updateObj[0][0] = bean.getDonationName();
			updateObj[0][1] = bean.getPercentage();
				
			if(bean.getIsActive().equals("true")) 
				updateObj[0][2] = "Y";
			else 
				updateObj[0][2] = "N";
			
			
			String insertQuery = "UPDATE HRMS_DONATION SET "
				+ " HRMS_DONATION.DONATION_NAME = ? , HRMS_DONATION.DONATION_PER = ? , HRMS_DONATION.DONATION_IS_ACTIVE = ?  " +
						"  WHERE HRMS_DONATION.DONATION_ID = " + bean.getDonationHiddenCode();

			result = getSqlModel().singleExecute(insertQuery, updateObj);

		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;

	}
}
