/**
 * @author pradeep
 * @modified by Reeba
 * @modified by Dilip
 * @modified by Anantha Lakshmi
 */


package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.lib.Utility;
import org.paradyne.bean.Recruitment.PostApplied;
import org.paradyne.bean.admin.master.DivisionConfiguration;
import org.paradyne.bean.admin.master.DivisionMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

/**
 *  To define the business logic for Division
 */
public class DivisionConfigurationModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);
	DivisionConfiguration divMast = null;

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(DivisionConfiguration bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_DIVISION WHERE UPPER(DIV_NAME) LIKE '"
				+ bean.getDivName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for checking duplicate entry of record during modification */
	public boolean checkDuplicateMod(DivisionConfiguration bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_DIVISION WHERE UPPER(DIV_NAME) LIKE '"
				+ bean.getDivName().trim().toUpperCase()
				+ "' AND DIV_ID not in(" + bean.getDivId() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	

	/* modifing record */
	public boolean modDiv(DivisionConfiguration bean) {
		if (!checkDuplicateMod(bean)) {
				// to get the division data for updating the record
				Object addObj[][] = new Object[1][33];
				addObj[0][0] = bean.getEsiZone().trim(); // esi zone
				addObj[0][1] = bean.getDivName().trim(); // division name
				addObj[0][2] = bean.getDivDesc().trim(); // division description
				addObj[0][3] = bean.getProtaxregNo().trim(); // professional tax												// number
				addObj[0][4] = bean.getPanNo().trim(); // pan number
				addObj[0][5] = bean.getTanNo().trim(); // tan number
				addObj[0][6] = bean.getBankid().trim(); // bank id/ name
				addObj[0][7] = bean.getAccno().trim(); // account number
				addObj[0][8] = bean.getPfNumber().trim(); // pf number
				addObj[0][9] = bean.getDivAbbr().trim(); // division abbreviation														// code
				addObj[0][10] = bean.getAcctGrpNo().trim(); // account group no		
				addObj[0][11] = bean.getEsiNumber().trim();// esi number
				addObj[0][12] = bean.getDtCommencementBusiness().trim();
				addObj[0][13] = bean.getLglStsEstablishmentId().trim();
				addObj[0][14] = bean.getOwnershipId().trim();
				addObj[0][15] = bean.getRegistrationNo().trim();
				addObj[0][16] = bean.getExpiryDt().trim();
				addObj[0][17] = bean.getTitleActId().trim();
				addObj[0][18] = bean.getEname().trim();
				addObj[0][19] = bean.getEdesignation().trim();
				addObj[0][20] = bean.getEmobNo().trim();
				addObj[0][21] = bean.getEfax().trim();
				addObj[0][22] = bean.getMname().trim();
				addObj[0][23] = bean.getMdesignation().trim();
				addObj[0][24] = bean.getMteleNo().trim();
				addObj[0][25] = bean.getMmobNo().trim();
				addObj[0][26] = bean.getMfax().trim();
				addObj[0][27] = bean.getMemail().trim();
				addObj[0][28] = bean.getCitAddress().trim();
				addObj[0][29] = bean.getCitCityId().trim();	
				addObj[0][30] = bean.getCitPinCode().trim();
				addObj[0][31] =bean.getEstbCode().trim(); 
				addObj[0][32] = bean.getDivId().trim();
				
				return getSqlModel().singleExecute(getQuery(2), addObj);
		}// end of if
		else{
			return false;
		}
	}

	/* deleting the record after selecting the data */
	public boolean deleteDiv(DivisionConfiguration bean) {
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = bean.getDivId();
		// to delete the division record from data base
		return getSqlModel().singleExecute(getQuery(3), delObj);
	}

	/**
	 * to display the division data after click on search button
	 * 
	 * @param bean
	 */
	public void getDivRecord(DivisionConfiguration bean) {
		Object paraObj[] = new Object[1];
		paraObj[0] = bean.getDivId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), paraObj);
		String stdTel=checkNull(String.valueOf(data[0][9]));
		bean.setDivId(checkNull(String.valueOf(data[0][0])));// division id
		bean.setDivName(checkNull(String.valueOf(data[0][1]))); // division name
		bean.setDivDesc(checkNull(String.valueOf(data[0][2]))); // division		// description
		bean.setProtaxregNo(checkNull(String.valueOf(data[0][11]))); // professional	// tax registration number															
		bean.setPanNo(checkNull(String.valueOf(data[0][12]))); // pan number
		bean.setTanNo(checkNull(String.valueOf(data[0][13]))); // tan number
		bean.setEsiZone(checkNull(String.valueOf(data[0][14]))); // esi zone
		bean.setBankid(checkNull(String.valueOf(data[0][15]))); // bank id/ name
		bean.setAccno(checkNull(String.valueOf(data[0][16]))); // account	// number
		bean.setDivAbbr(checkNull(String.valueOf(data[0][17]))); // division// abbreviation						
		bean.setAcctGrpNo(checkNull(String.valueOf(data[0][19]))); // account // group no.
		bean.setEsiNumber(checkNull(String.valueOf(data[0][20]))); // esi no.
		bean.setIsActive(checkNull(String.valueOf(data[0][21])));// is
		bean.setDtCommencementBusiness(checkNull(String.valueOf(data[0][22]))); 
		bean.setLglStsEstablishmentId(checkNull(String.valueOf(data[0][23]))); 
		bean.setOwnership(checkNull(String.valueOf(data[0][24]))); 
		bean.setRegistrationNo(checkNull(String.valueOf(data[0][25]))); 
		bean.setExpiryDt(checkNull(String.valueOf(data[0][26]))); 
		bean.setTitleAct(checkNull(String.valueOf(data[0][27]))); 
		bean.setEname(checkNull(String.valueOf(data[0][28]))); 
		bean.setEdesignation(checkNull(String.valueOf(data[0][29]))); 
		bean.setEmobNo(checkNull(String.valueOf(data[0][30]))); 
		bean.setEfax(checkNull(String.valueOf(data[0][31]))); 
		bean.setMname(checkNull(String.valueOf(data[0][32]))); 
		bean.setMdesignation(checkNull(String.valueOf(data[0][33]))); 
		bean.setMteleNo(checkNull(String.valueOf(data[0][34]))); 
		bean.setMmobNo(checkNull(String.valueOf(data[0][35]))); 
		bean.setMfax(checkNull(String.valueOf(data[0][36]))); 
		bean.setMemail(checkNull(String.valueOf(data[0][37]))); 

		bean.setCitAddress((checkNull(String.valueOf(data[0][39]))));
		bean.setCitCity(checkNull(String.valueOf(data[0][40])));
		bean.setCitPinCode((checkNull(String.valueOf(data[0][41]))));
		

	}

	/* generating report */
	public void getDivisionReport(DivisionConfiguration bean) {
		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		ArrayList<Object> diviList = new ArrayList<Object>();
		for (int i = 0; i < data.length; i++) {
			DivisionConfiguration bean1 = new DivisionConfiguration();
			bean1.setDivId(checkNull(String.valueOf(data[i][0])));
			bean1.setDivName(checkNull(String.valueOf(data[i][1])));
			bean1.setDivDesc(checkNull(String.valueOf(data[i][2])));
			bean1.setProtaxregNo(checkNull(String.valueOf(data[i][11])));
			bean1.setPanNo(checkNull(String.valueOf(data[i][12])));
			bean1.setTanNo(checkNull(String.valueOf(data[i][13])));
			bean1.setEsiZone(checkNull(String.valueOf(data[i][14])));
			bean1.setEsiNumber(checkNull(String.valueOf(data[i][16])));
			bean1.setIsActive(checkNull(String.valueOf(data[i][17])));
			diviList.add(bean1);
		}// end of loop
		bean.setDivList(diviList);

	}

	/**
	 * to cheking null value
	 * 
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/*  */
	
	/**
	 * Changed Query for Generate Report by Nilesh D on 3rd Feb 12.
	 * Method : getReport().
	 * Purpose : generating report.
	 */
	public void getReport(DivisionConfiguration divMast, HttpServletRequest request,
			HttpServletResponse response, ServletContext context, String[] label) {
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nDivision Configuration\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",	reportName,"");
		rg.setFName("Division Configuration");
		String queryDes = " SELECT DIV_NAME,DIV_DESC,BANK_NAME,DIV_ACCOUNT_NUMBER,DIV_PTAX_REG,"
					  + " DECODE(DIV_ESI_ZONE,'Y','Yes','N','No'),DIV_PANNO,DIV_TANNO, DIV_PFACCOUNTNO,DIV_ABBR"
					  + " FROM HRMS_DIVISION "
					  + " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=HRMS_DIVISION.DIV_CITY_ID)"
					  + " LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE=HRMS_DIVISION.DIV_BANK)"
					  + " WHERE  HRMS_DIVISION.IS_ACTIVE='Y' ORDER BY DIV_NAME ";

		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][17];
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
				Data[i][9] = data[i][8];
				Data[i][10] = data[i][9];
				//Data[i][11] = data[i][10];
				j++;
			}
			int cellwidth[] = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,5};
			int alignment[] = { 1, 0, 0, 0, 0, 1, 1, 0, 0, 1,1};
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


	/**
	 * to generate the list in onload
	 * 
	 */
	public void divData(DivisionConfiguration divMast, HttpServletRequest request) {
		// to get the data from division master
		Object[][] repData = getSqlModel().getSingleResult(getQuery(6));
		if (repData != null && repData.length > 0) {
			divMast.setModeLength("true"); // set the length of data in the
											// list
			divMast.setTotalRecords(String.valueOf(repData.length));// display
																	// the total
																	// record in
																	// the list

			String[] pageIndex = Utility.doPaging(divMast.getMyPage(),
					repData.length, 20);// to display the page number
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2]))); // to set the total number of page
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));// to set the page number
			if (pageIndex[4].equals("1"))
				divMast.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				DivisionConfiguration bean1 = new DivisionConfiguration();
				bean1.setDivId(checkNull(String.valueOf(repData[i][0]))); // division																			// id
				bean1.setDivName(checkNull(String.valueOf(repData[i][1]))); // division																			// name
				bean1.setDivDesc(checkNull(String.valueOf(repData[i][2]))); // division																			// description
				bean1.setIsActive(checkNull(String.valueOf(repData[i][3]))); // added is active main	 page display.
				List.add(bean1);
			}// end of loop
			divMast.setDivList(List);// to display the division list in the  page
										
		}
	}
	
	public void data(DivisionConfiguration divMast) {
		// TODO Auto-generated method stub
		try {
			Object[] para = new Object[1];
			para[0] = divMast.getDivId();
			String dataQuery = "SELECT DIV_ID ,DIV_NAME,DIV_DESC,BANK_NAME,DIV_ACCOUNT_NUMBER,DIV_PTAX_REG,"
					+ " DIV_ESI_ZONE,DIV_PANNO,DIV_TANNO, DIV_PFACCOUNTNO,DIV_ABBR, ACCOUNT_GROUP_CODE,DIV_ESI_CODE,"
					+ " DIV_BANK,TO_CHAR(DIV_COMMENCEMENT_DATE,'DD-MM-YYYY'),"
					+ " LMS_LEGALSTAT_OF_ESTBLSHMNT.LEGALSTATUS_OF_ESTBLSHMNT, LMS_OWNERSHIP.OWNERSHIP_NAME," 
					+ " DIV_REG_NO, TO_CHAR(DIV_EXPIRY_DATE,'DD-MM-YYYY'),LMS_TITLE_OF_ACTS.TITLE_OF_ACT," 
					+ " DIV_EMPLOYER_NAME, DIV_EMPLOYER_DESG, DIV_EMPLOYER_MOBILE, DIV_EMPLOYER_FAX, " 
					+ " DIV_MANAGER_NAME, DIV_MANAGER_DESG, DIV_MANAGER_TELEPHONE,DIV_MANAGER_MOBILE," 
					+ " DIV_MANAGER_FAX, DIV_MANAGER_EMAIL, DIV_CIT_ADDRESS, " 
					+ " LOC.LOCATION_NAME, DIV_CIT_PIN,ESTABLISHMENT_CODE  FROM HRMS_DIVISION "
					+ " LEFT JOIN LMS_LEGALSTAT_OF_ESTBLSHMNT ON(LMS_LEGALSTAT_OF_ESTBLSHMNT.LEGAL_STATUS_ID=HRMS_DIVISION.DIV_LEGAL_STATUS)"
					+ " LEFT JOIN LMS_OWNERSHIP ON(LMS_OWNERSHIP.OWNERSHIP_ID=HRMS_DIVISION.DIV_OWNERSHIP )" 
					+ " LEFT JOIN LMS_TITLE_OF_ACTS ON(LMS_TITLE_OF_ACTS.TITLE_OF_ACT_ID=HRMS_DIVISION.DIV_TITLE_OF_ACT)"
					+ " LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=HRMS_DIVISION.DIV_CITY_ID)"
					+ " LEFT JOIN HRMS_LOCATION LOC ON (LOC.LOCATION_CODE=HRMS_DIVISION.DIV_CIT_CITY)"
					+ " LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE=HRMS_DIVISION.DIV_BANK) WHERE DIV_ID="
					+ String.valueOf(para[0]);
			Object[][] data = getSqlModel().getSingleResult(dataQuery);
			divMast.setDivId(checkNull(String.valueOf(data[0][0])));
			divMast.setDivName(checkNull(String.valueOf(data[0][1])));
			divMast.setDivDesc(checkNull(String.valueOf(data[0][2])));
			
			divMast.setBank(checkNull(String.valueOf(data[0][3])));
			divMast.setAccno(checkNull(String.valueOf(data[0][4])));
			divMast.setProtaxregNo(checkNull(String.valueOf(data[0][5])));
			divMast.setEsiZone(checkNull(String.valueOf(data[0][6])));
			divMast.setPanNo(checkNull(String.valueOf(data[0][7])));
			divMast.setTanNo(checkNull(String.valueOf(data[0][8])));
			divMast.setPfNumber(checkNull(String.valueOf(data[0][9])));
			divMast.setDivAbbr(checkNull(String.valueOf(data[0][10])));
			divMast.setAcctGrpNo(checkNull(String.valueOf(data[0][11])));
			divMast.setEsiNumber(checkNull(String.valueOf(data[0][12])));
			divMast.setBankid(checkNull(String.valueOf(data[0][13])));
			divMast.setDtCommencementBusiness(checkNull(String.valueOf(data[0][14]))); 
			divMast.setLglStsEstablishmentId(checkNull(String.valueOf(data[0][15]))); 
			divMast.setOwnership(checkNull(String.valueOf(data[0][16]))); 
			divMast.setRegistrationNo(checkNull(String.valueOf(data[0][17]))); 
			divMast.setExpiryDt(checkNull(String.valueOf(data[0][18]))); 
			divMast.setTitleAct(checkNull(String.valueOf(data[0][19]))); 
			divMast.setEname(checkNull(String.valueOf(data[0][20]))); 
			divMast.setEdesignation(checkNull(String.valueOf(data[0][21]))); 
			divMast.setEmobNo(checkNull(String.valueOf(data[0][22]))); 
			
			divMast.setEfax(checkNull(String.valueOf(data[0][23]))); 
			divMast.setMname(checkNull(String.valueOf(data[0][24]))); 
			divMast.setMdesignation(checkNull(String.valueOf(data[0][25]))); 
			divMast.setMteleNo(checkNull(String.valueOf(data[0][26]))); 
			divMast.setMmobNo(checkNull(String.valueOf(data[0][27]))); 
			divMast.setMfax(checkNull(String.valueOf(data[0][28]))); 
			divMast.setMemail(checkNull(String.valueOf(data[0][29]))); 
		
			divMast.setCitAddress(checkNull(String.valueOf(data[0][30])));
			divMast.setCitCity(checkNull(String.valueOf(data[0][31])));
			divMast.setCitPinCode(checkNull(String.valueOf(data[0][32])));
			divMast.setEstbCode(checkNull(String.valueOf(data[0][33])));
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * for selecting data from list and setting them in respective fields.
	 */
	public void calforedit(DivisionConfiguration divMast) {
		String query = "  SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION"
				+ " WHERE  DIV_ID = " + divMast.getHiddencode()
				+ " ORDER BY DIV_ID  ";
		Object[][] data = getSqlModel().getSingleResult(query);// to get the record and update the data in  double click in the list													
		divMast.setDivId(String.valueOf(data[0][0])); // division id
		divMast.setDivName(String.valueOf(data[0][1])); // division name
	}

	/**
	 * to delete the record 
	 * @param divMast
	 * @return
	 */
	public boolean calfordelete(DivisionConfiguration divMast) {

		Object[][] delete = new Object[1][1];
		delete[0][0] = divMast.getHiddencode();// to get the division hidden code for delete particular record
		return getSqlModel().singleExecute(getQuery(3), delete);
	}

	/*
	 * deleting data from list
	 */
	public boolean deleteDivision(DivisionConfiguration divMast, String[] code) {

		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if (code != null) {// to check the division code which not null
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {// to check the division code which not null
					Object[][] delete = new Object[1][1];// to delete the multiple record
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(getQuery(3), delete);
					if (!flag) {
						cnt++;
					}
					// result=true;
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

}
