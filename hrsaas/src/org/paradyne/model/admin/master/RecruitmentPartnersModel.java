package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.admin.srd.SendMailModel;
import org.paradyne.bean.admin.master.RecruitmentPartners;

/**
 * @author Prasad
 */

public class RecruitmentPartnersModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(RecruitmentPartnersModel.class);

	/**
	 * @Method checkNull
	 * @Purpose to check whether the selected data is null or not
	 * @param result
	 *            value of the data
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * @param bean
	 * @return String after saving the application
	 * 
	 */

	public String addRecruitment(RecruitmentPartners bean,
			HttpServletRequest request) {
		Object[][] add = new Object[1][17];
		String flag = "";
		boolean result = false;
		try {
			add[0][0] = bean.getRecPartnerName().trim();
			add[0][1] = bean.getRecPartnerType().trim();
			add[0][2] = bean.getRecContactPerson().trim();
			add[0][3] = bean.getRecPartnerAddress().trim();
			add[0][4] = bean.getCityCode().trim();
			add[0][5] = bean.getPhoneNo().trim();
			add[0][6] = bean.getPinCode().trim();
			add[0][7] = bean.getMobileNo().trim();
			add[0][8] = bean.getPartnerDesc().trim();
			add[0][9] = bean.getEmail().trim();
			add[0][10] = bean.getFaxNo().trim();
			add[0][11] = bean.getStatus().trim();
			add[0][12] = bean.getCharges().trim();
			String simplePass = getAltRandomPassword(8);
			String encPass = "";
			encPass = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(simplePass);
			add[0][13] = encPass;
			add[0][14] = bean.getTermsCond();
			add[0][15]=bean.getStartDate().trim();
			add[0][16]=bean.getEndDate().trim();    
			if (!(checkDuplicate(bean))) {
				result = getSqlModel().singleExecute(getQuery(1), add);
				if (result) {
					sendPartnerMail(request, bean, simplePass);
					String query = " SELECT MAX(REC_CODE) FROM HRMS_REC_PARTNER";
					Object[][] data = getSqlModel().getSingleResult(query);
					String query1 = " SELECT REC_PARTNERNAME, REC_CONTACTPERSON,DECODE(REC_TYPE,'C ','Job Consultant', "
							+ "'AA','Advertising Agency','O ','Outsourcing Agency'), REC_ADDRESS, REC_CITY, REC_PHONENO, REC_PINCODE, "
							+ "REC_MOBILENO, REC_EMAIL, REC_FAXNO, REC_CHARGES, REC_DESC, "
							+ "DECODE(REC_STATUS,'A','Active','D','Deactive'), REC_CODE,LOCATION_NAME  "
							+ "FROM HRMS_REC_PARTNER "
							+ "LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_REC_PARTNER.REC_CITY) "
							+ "  WHERE REC_CODE=" + String.valueOf(data[0][0]);
					Object[][] Data = getSqlModel().getSingleResult(query1);

					if (Data != null && Data.length != 0) {
						bean.setRecPartnerName(checkNull(String.valueOf(Data[0][0])));
						bean.setRecContactPerson(checkNull(String.valueOf(Data[0][1])));
						bean.setPageRecPartnerType(checkNull(String.valueOf(Data[0][2])).trim());
						bean.setRecPartnerAddress(checkNull(String.valueOf(Data[0][3])));
						bean.setCityCode(checkNull(String.valueOf(Data[0][4])));
						bean.setPhoneNo(checkNull(String.valueOf(Data[0][5])));
						bean.setPinCode(checkNull(String.valueOf(Data[0][6])));
						bean.setMobileNo(checkNull(String.valueOf(Data[0][7])));
						bean.setEmail(checkNull(String.valueOf(Data[0][8])));
						bean.setFaxNo(checkNull(String.valueOf(Data[0][9])));
						bean.setCharges(checkNull(String.valueOf(Data[0][10])));
						bean.setPartnerDesc(checkNull(String.valueOf(Data[0][11])));
						bean.setPageStatus(checkNull(String.valueOf(Data[0][12])).trim());
						bean.setRecpartnerCode(checkNull(String.valueOf(Data[0][13])));
						bean.setRecPartnerCity(checkNull(String.valueOf(Data[0][14])));
						flag = "saved";
					}
				} else {
					flag = "error";
				}
			} else {
				String query = "SELECT DECODE(REC_STATUS,'A','Active','D','Deactive') "
						+ " FROM HRMS_REC_PARTNER where  REC_STATUS='"
						+ bean.getStatus() + "'";

				Object[][] data = getSqlModel().getSingleResult(query);
				flag = "duplicate";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * @param bean
	 * @return String after updating the application
	 * 
	 */
	public String modRecruitment(RecruitmentPartners bean) {
		Object mod[][] = new Object[1][17];
		String editFlag = "";
		boolean result = false;
		try {
			mod[0][0] = bean.getRecPartnerName().trim();
			mod[0][1] = bean.getRecPartnerType().trim();
			mod[0][2] = bean.getRecContactPerson().trim();
			mod[0][3] = bean.getRecPartnerAddress().trim();
			mod[0][4] = bean.getCityCode().trim();
			mod[0][5] = bean.getPhoneNo().trim();
			mod[0][6] = bean.getPinCode().trim();
			mod[0][7] = bean.getMobileNo().trim();
			mod[0][8] = bean.getPartnerDesc().trim();
			mod[0][9] = bean.getEmail().trim();
			mod[0][10] = bean.getFaxNo().trim();
			mod[0][11] = bean.getStatus().trim();
			mod[0][12] = bean.getCharges().trim();
			mod[0][13] = bean.getTermsCond().trim();
			mod[0][14] =bean.getStartDate().trim();
			System.out.println("=======START======="+mod[0][14]);
			mod[0][15] =bean.getEndDate().trim();
			System.out.println("========END========="+mod[0][15]);
			mod[0][16] = bean.getRecpartnerCode().trim();
			

			if (!(checkDuplicateMod(bean))) {
				result = getSqlModel().singleExecute(getQuery(2), mod);

				if (result) {
					String query = "SELECT REC_PARTNERNAME, REC_CONTACTPERSON,DECODE(REC_TYPE,'C ','Job Consultant', "
							+ "'AA','Advertising Agency','O ','Outsourcing Agency'), REC_ADDRESS, REC_CITY, REC_PHONENO, REC_PINCODE, "
							+ "REC_MOBILENO, REC_EMAIL, REC_FAXNO, REC_CHARGES, REC_DESC, "
							+ "DECODE(REC_STATUS,'A','Active','D','Deactive'), REC_CODE,LOCATION_NAME  "
							+ "FROM HRMS_REC_PARTNER "
							+ "LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_REC_PARTNER.REC_CITY) "
							+ "WHERE REC_CODE=" + bean.getRecpartnerCode();

					Object[][] Data = getSqlModel().getSingleResult(query);

					if (Data != null && Data.length != 0) {
						bean.setRecPartnerName(checkNull(String
								.valueOf(Data[0][0])));
						bean.setRecContactPerson(checkNull(String
								.valueOf(Data[0][1])));
						bean.setPageRecPartnerType(checkNull(String
								.valueOf(Data[0][2])));
						bean.setRecPartnerAddress(checkNull(String
								.valueOf(Data[0][3])));
						bean.setCityCode(checkNull(String.valueOf(Data[0][4])));
						bean.setPhoneNo(checkNull(String.valueOf(Data[0][5])));
						bean.setPinCode(checkNull(String.valueOf(Data[0][6])));
						bean.setMobileNo(checkNull(String.valueOf(Data[0][7])));
						bean.setEmail(checkNull(String.valueOf(Data[0][8])));
						bean.setFaxNo(checkNull(String.valueOf(Data[0][9])));
						bean.setCharges(checkNull(String.valueOf(Data[0][10])));
						bean.setPartnerDesc(checkNull(String
								.valueOf(Data[0][11])));
						bean.setPageStatus(checkNull(
								String.valueOf(Data[0][12])).trim());
						bean.setRecpartnerCode(checkNull(String
								.valueOf(Data[0][13])));
						bean.setRecPartnerCity(checkNull(String
								.valueOf(Data[0][14])));
					}
					editFlag = "modified";
				} else {
					editFlag = "error";
				}
			} else {
				editFlag = "duplicate";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editFlag;
	}

	/**
	 * To delete the record from the application
	 * 
	 * @param bean
	 * @return boolean value
	 */
	public boolean deleteRecruitment(RecruitmentPartners bean) {
		boolean result = false;
		try {
			Object del[][] = new Object[1][1];
			del[0][0] = bean.getRecpartnerCode();
			result = getSqlModel().singleExecute(getQuery(3), del);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * to get the record
	 * 
	 * @param bean
	 */
	public void getRecruitment(RecruitmentPartners bean) {
		try {

			String query = " SELECT REC_PARTNERNAME, REC_CONTACTPERSON,REC_TYPE, "
					+ " REC_ADDRESS, REC_CITY, REC_PHONENO, REC_PINCODE, "
					+ "REC_MOBILENO, REC_EMAIL, REC_FAXNO, REC_CHARGES, REC_DESC, "
					+ "REC_STATUS, REC_CODE,LOCATION_NAME,DECODE(REC_STATUS,'A','Active','D','Deactive'),DECODE(REC_TYPE,'C ','Job Consultant','AA','Advertising Agency','O ','Outsourcing Agency'),NVL(REC_TERMS_CONDITIONS,''),TO_CHAR(REC_START_DATE,'DD-MM-YYYY'),TO_CHAR(REC_END_DATE,'DD-MM-YYYY') "
					+ "FROM HRMS_REC_PARTNER "
					+ "LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_REC_PARTNER.REC_CITY) "
					+ "  WHERE REC_CODE=" + bean.getRecpartnerCode();

			Object[][] Data = getSqlModel().getSingleResult(query);

			if (Data != null && Data.length > 0) {

				bean.setRecPartnerName(checkNull(String.valueOf(Data[0][0])));
				bean.setRecContactPerson(checkNull(String.valueOf(Data[0][1])));
				bean.setRecPartnerType(checkNull(String.valueOf(Data[0][2])).trim());
				bean.setRecPartnerAddress(checkNull(String.valueOf(Data[0][3])));
				bean.setCityCode(checkNull(String.valueOf(Data[0][4])));
				bean.setPhoneNo(checkNull(String.valueOf(Data[0][5])));
				bean.setPinCode(checkNull(String.valueOf(Data[0][6])));
				bean.setMobileNo(checkNull(String.valueOf(Data[0][7])));
				bean.setEmail(checkNull(String.valueOf(Data[0][8])));
				bean.setFaxNo(checkNull(String.valueOf(Data[0][9])));
				bean.setCharges(checkNull(String.valueOf(Data[0][10])));
				bean.setPartnerDesc(checkNull(String.valueOf(Data[0][11])));
				bean.setStatus(checkNull(String.valueOf(Data[0][12])));
				bean.setRecpartnerCode(checkNull(String.valueOf(Data[0][13])));
				bean.setRecPartnerCity(checkNull(String.valueOf(Data[0][14])));
				bean.setPageStatus(checkNull(String.valueOf(Data[0][15])).trim());
				bean.setPageRecPartnerType(checkNull(String.valueOf(Data[0][16])).trim());
				bean.setTermsCond(checkNull(String.valueOf(Data[0][17])).trim());
				bean.setStartDate(checkNull(String.valueOf(Data[0][18])).trim());
				bean.setEndDate(checkNull(String.valueOf(Data[0][19])).trim());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * to get the record
	 * 
	 * @param bean
	 */
	public void getRecruitmentRec(RecruitmentPartners bean) {
		try {

			String query = " SELECT REC_PARTNERNAME, REC_CONTACTPERSON,DECODE(REC_TYPE,'C ','Job Consultant', "
					+ "'AA','Advertising Agency','O ','Outsourcing Agency'), REC_ADDRESS, REC_CITY, REC_PHONENO, REC_PINCODE, "
					+ "REC_MOBILENO, REC_EMAIL, REC_FAXNO, REC_CHARGES, REC_DESC, "
					+ "DECODE(REC_STATUS,'A','Active','D','Deactive'), REC_CODE,LOCATION_NAME ,REC_STATUS "
					+ "FROM HRMS_REC_PARTNER "
					+ "LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_REC_PARTNER.REC_CITY) "
					+ "  WHERE REC_CODE=" + bean.getRecpartnerCode();
			Object[][] Data = getSqlModel().getSingleResult(query);

			if (Data != null && Data.length > 0) {

				bean.setRecPartnerName(checkNull(String.valueOf(Data[0][0])));
				bean.setRecContactPerson(checkNull(String.valueOf(Data[0][1])));
				bean.setRecPartnerType(checkNull(String.valueOf(Data[0][2])).trim());
				bean.setRecPartnerAddress(checkNull(String.valueOf(Data[0][3])));
				bean.setCityCode(checkNull(String.valueOf(Data[0][4])));
				bean.setPhoneNo(checkNull(String.valueOf(Data[0][5])));
				bean.setPinCode(checkNull(String.valueOf(Data[0][6])));
				bean.setMobileNo(checkNull(String.valueOf(Data[0][7])));
				bean.setEmail(checkNull(String.valueOf(Data[0][8])));
				bean.setFaxNo(checkNull(String.valueOf(Data[0][9])));
				bean.setCharges(checkNull(String.valueOf(Data[0][10])));
				bean.setPartnerDesc(checkNull(String.valueOf(Data[0][11])));
				bean.setPageStatus(checkNull(String.valueOf(Data[0][12])));
				bean.setRecpartnerCode(checkNull(String.valueOf(Data[0][13])));
				bean.setRecPartnerCity(checkNull(String.valueOf(Data[0][14])));
				bean.setStatus(checkNull(String.valueOf(Data[0][15])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * to get the record when user double click on the records
	 * 
	 * @param bean
	 */
	public void getRecruitmentOnDoubleClick(RecruitmentPartners bean) {
		try {
			String query = " SELECT REC_PARTNERNAME, REC_CONTACTPERSON,REC_TYPE,  "
					+ " REC_ADDRESS, REC_CITY, REC_PHONENO, REC_PINCODE, "
					+ "REC_MOBILENO, REC_EMAIL, REC_FAXNO, REC_CHARGES, REC_DESC, "
					+ " REC_STATUS, REC_CODE,LOCATION_NAME ,DECODE(REC_STATUS,'A','Active','D','Deactive'),DECODE(REC_TYPE,'C ','Job Consultant','AA','Advertising Agency','O ','Outsourcing Agency'),NVL(REC_TERMS_CONDITIONS,''),TO_CHAR(REC_START_DATE,'DD-MM-YYYY'),TO_CHAR(REC_END_DATE,'DD-MM-YYYY') "
					+ "FROM HRMS_REC_PARTNER "
					+ "LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_REC_PARTNER.REC_CITY) "
					+ "  WHERE REC_CODE=" + bean.getHiddencode();

			Object[][] Data = getSqlModel().getSingleResult(query);

			if (Data != null && Data.length > 0) {

				bean.setRecPartnerName(checkNull(String.valueOf(Data[0][0])));
				bean.setRecContactPerson(checkNull(String.valueOf(Data[0][1])));
				bean.setRecPartnerType(checkNull(String.valueOf(Data[0][2])).trim());
				bean.setRecPartnerAddress(checkNull(String.valueOf(Data[0][3])));
				bean.setCityCode(checkNull(String.valueOf(Data[0][4])));
				bean.setPhoneNo(checkNull(String.valueOf(Data[0][5])));
				bean.setPinCode(checkNull(String.valueOf(Data[0][6])));
				bean.setMobileNo(checkNull(String.valueOf(Data[0][7])));
				bean.setEmail(checkNull(String.valueOf(Data[0][8])));
				bean.setFaxNo(checkNull(String.valueOf(Data[0][9])));
				bean.setCharges(checkNull(String.valueOf(Data[0][10])));
				bean.setPartnerDesc(checkNull(String.valueOf(Data[0][11])));
				bean.setStatus(checkNull(String.valueOf(Data[0][12])));
				bean.setRecpartnerCode(checkNull(String.valueOf(Data[0][13])));
				bean.setRecPartnerCity(checkNull(String.valueOf(Data[0][14])));
				bean.setPageStatus(checkNull(String.valueOf(Data[0][15])));
				bean.setPageRecPartnerType(checkNull(String.valueOf(Data[0][16])));
				bean.setTermsCond(checkNull(String.valueOf(Data[0][17])));
				bean.setStartDate(checkNull(String.valueOf(Data[0][18])));
				bean.setEndDate(checkNull(String.valueOf(Data[0][19])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To show the list of records
	 * 
	 * @param bean
	 * @param request
	 */
	public void getRecords(RecruitmentPartners bean, HttpServletRequest request) {
		try {
			String query = "SELECT REC_PARTNERNAME, REC_CONTACTPERSON, DECODE(REC_TYPE,'C ','Job Consultant','AA','Advertising Agency','O ','Outsourcing Agency'), REC_CITY, "
					+ "DECODE(REC_STATUS, 'A', 'Active', 'D', 'Deactive'), REC_CODE, LOCATION_NAME  "
					+ "FROM HRMS_REC_PARTNER "
					+ "LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_REC_PARTNER.REC_CITY)"
					+ "ORDER BY REC_CODE";

			Object[][] data = getSqlModel().getSingleResult(query);
			/*
			 * int REC_TOTAL = 0; int To_TOT = 20; int From_TOT = 0; int pg1=0;
			 * int PageNo1=1;//---------- REC_TOTAL = data.length; int
			 * no_of_pages=Math.round(REC_TOTAL/20); double row =
			 * (double)data.length/20.0;
			 * 
			 * java.math.BigDecimal value1 = new java.math.BigDecimal(row);
			 * 
			 * int rowCount1
			 * =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
			 * 
			 * 
			 * ArrayList<Object> obj=new ArrayList<Object>();
			 * System.out.println("val of riwC"+rowCount1);
			 * request.setAttribute("abc", rowCount1);
			 * 
			 * //PageNo
			 * if(String.valueOf(bean.getMyPage()).equals("null")||String.valueOf(bean.getMyPage()).equals(null)||String.valueOf(bean.getMyPage()).equals("
			 * ")) { PageNo1=1; From_TOT=0; To_TOT=20;
			 * 
			 * if(To_TOT >data.length){ To_TOT=data.length; }
			 * 
			 * bean.setMyPage("1"); }
			 * 
			 * 
			 * else{
			 * 
			 * pg1= Integer.parseInt(bean.getMyPage()); PageNo1=pg1;
			 * 
			 * if(pg1 ==1){ From_TOT=0; To_TOT=20; } else{ //
			 * From_TOTAL=To_TOTAL+1; To_TOT=To_TOT*pg1; From_TOT=(To_TOT-20); }
			 * if(To_TOT >data.length){ To_TOT=data.length; } }
			 * request.setAttribute("xyz", PageNo1);
			 */
			ArrayList<Object> obj = new ArrayList<Object>();
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					data.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("abc", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("xyz", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");

			if (data != null && data.length > 0) {
				bean.setNoData("false");
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					// setting
					RecruitmentPartners bean1 = new RecruitmentPartners();

					bean1.setRecPartnerName(checkNull(String
							.valueOf(data[i][0])));
					bean1.setRecContactPerson(checkNull(String
							.valueOf(data[i][1])));
					bean1.setRecPartnerType(checkNull(String
							.valueOf(data[i][2])));
					bean1.setCityCode(checkNull(String.valueOf(data[i][3])));
					bean1.setStatus(checkNull(String.valueOf(data[i][4])));
					bean1.setRecpartnerCode(checkNull(String
							.valueOf(data[i][5])));
					bean1.setRecPartnerCity(checkNull(String
							.valueOf(data[i][6])));
					obj.add(bean1);

				}
				bean.setDataLength(String.valueOf(data.length));
			} else {
				bean.setNoData("true");
			}

			bean.setRecpartnerList(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To delete the checked records which were you wants to delete
	 * 
	 * @param bean
	 * @param code
	 * @return
	 */
	public boolean delChkdRec(RecruitmentPartners bean, String[] code) {
		int count = 0;
		boolean result = false;
		try {
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(3), delete);
					if (!result) {
						count++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	/**
	 * To check the duplicate during insertion
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean checkDuplicate(RecruitmentPartners bean) {
		boolean result = false;
		try {
			String query = "SELECT * FROM HRMS_REC_PARTNER WHERE UPPER(REC_PARTNERNAME) LIKE '"
					+ bean.getRecPartnerName().trim().toUpperCase() + "'";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * To check the duplicate during modification
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean checkDuplicateMod(RecruitmentPartners bean) {
		boolean result = false;
		try {
			String query = "SELECT * FROM HRMS_REC_PARTNER WHERE UPPER(REC_PARTNERNAME) LIKE '"
					+ bean.getRecPartnerName().trim().toUpperCase()
					+ "' AND REC_CODE not in(" + bean.getRecpartnerCode() + ")";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * Following function is called to get the report of the Pdf format for list
	 * of Partners Records
	 * 
	 * @param recPartnersMaster
	 * @param response
	 * @param label
	 */
	public void generateReport(RecruitmentPartners recPartnersMaster,
			HttpServletResponse response, String[] label) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "Recruitment Partners Master";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Recruitment Partners Master.Pdf");
		String queryDes = " SELECT REC_PARTNERNAME,REC_CONTACTPERSON,DECODE(REC_TYPE,'C ','Job Consultant','AA', "
				+ " 'Advertising Agency','O ','Outsourcing Agency'), "
				+ " HRMS_LOCATION.LOCATION_NAME as CITY,REC_PHONENO,REC_EMAIL,DECODE(REC_STATUS, 'A', 'Active', 'D', 'Deactive') "
				+ " FROM HRMS_REC_PARTNER LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_REC_PARTNER.REC_CITY) "
				+ " ORDER BY REC_CODE";
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][8];
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
				j++;
			}
			int cellwidth[] = { 10, 25, 25, 25, 20, 20, 30, 20 };
			int alignment[] = { 1, 0, 0, 0, 0, 0, 0, 0 };
			rg.addTextBold("Recruitment Partners Master", 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}
		rg.createReport(response);
	}

	public String getAltRandomPassword(int length) {
		Random random = new Random();
		StringBuffer password = new StringBuffer();
		char[] allCharsNum = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
				'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2',
				'3', '4', '5', '6', '7', '8', '9'

		};

		for (int i = 0; i < length; i++) {
			password.append(allCharsNum[random.nextInt(allCharsNum.length)]);
		}
		return password.toString();
	}

	/**
	 * Functionality for sending the mail added by manish
	 */

	private void sendPartnerMail(HttpServletRequest request,
			RecruitmentPartners bean, String password) {

		String poolName = "";

		try {
			poolName = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.URL_ENCRYPTION_KEY)
					.encrypt((String) session.getAttribute("session_pool"));
		} catch (Exception e) {
			poolName = "";
		}

		String URL = "";

		// Use this URL while giving file to the TESTING Server

		URL = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath()
				+ "/recruit/PartnerLogin_input.action?infoId=" + poolName;

		// Use this URL while giving file to the Live Server

		/*
		 * URL = "http://" + request.getServerName() + request.getContextPath() +
		 * "/recruit/PartnerLogin_input.action?infoId=" + poolName;
		 */

		String username = bean.getRecPartnerName();
		String[] to_Emailid = new String[1];
		to_Emailid[0] = bean.getEmail();
		String[] subject = { "Partner Login Details" };
		String[] message = new String[1];
		String loginPassMsg = "";
		loginPassMsg = "User Name :" + bean.getEmail() + "<br>Password  :"
				+ password + "<br><br>";
		try {
			message[0] = getMessage(username, URL, loginPassMsg);
			MailUtility mod = new MailUtility();
			mod.initiate(context, session);
			mod.sendMail(to_Emailid, mod.getDefaultMailIds(), subject, message,
					"", bean.getUserEmpId());
			mod.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMessage(String uname, String url, String msg) {
		String[] htmlText = new String[1];
		String tempMsg = "";
		htmlText[0] = "<html> "
				+ "<style>"
				+ " .txt {font-family: Verdana, Arial, Helvetica, sans-serif;	font-size: 12px; color: #000000; text-decoration: none; } "
				+ " .style13 { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; color: #FFFFFF; text-decoration: none; font-weight: bold; } "
				+ " .birth { font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; }"
				+ ".style14 {font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; color: #FF6600; }"
				+ ".style15 {color: #CC0000} "
				+ ".style16 {color: #D23A49} "
				+ "</style>"
				+ "	<body> "
				+ "<table width='60%' border='0' cellpadding='0' cellspacing='0'>"
				+ "<tr> "
				+ "<td width='66%'>"
				+ "<table width='96%' border='0' cellpadding='2' cellspacing='2' class='border'> "
				+ "<tr> "
				+ "<td><p>Dear&nbsp;<b>"
				+ uname
				+ "</b>, </p><br /> "
				+ "Welcome to People Power-Recruitment Management Tool  "
				+ "Kindly find your account information below.</p> "
				+ "<br /><p>People Power Link : <a href='"
				+ url
				+ "'>"
				+ url
				+ "</a></p>"
				+ msg

				+ "<br /><p>For any assistance, please contact the System Administrator.</p><br />"
				+ "<p>Best Regards,</p>" + "<p>Human Resource Team</p>"
				+ "</td> " + "</tr> " + "</table> " + "</td> " + "</tr> "

				+ "</table> " + "</body> " + "</html> ";
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		tempMsg = model.getMassMessage(htmlText[0]);
		return tempMsg;
	}

	public boolean sendCredentialsToPartner(RecruitmentPartners bean,
			HttpServletRequest request) {
		boolean result = false;
		try {

			String getPartnerDataQuery = "SELECT REC_PARTNERNAME, REC_EMAIL, REC_PASSWORD FROM HRMS_REC_PARTNER WHERE REC_CODE = "+bean.getRecpartnerCode();
			Object forgotUserData[][] = getSqlModel().getSingleResult(getPartnerDataQuery);
			if (forgotUserData != null && forgotUserData.length > 0) {
				result = true;
				String poolName = "";
				try {
					poolName = new StringEncrypter(
							StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
							StringEncrypter.URL_ENCRYPTION_KEY)
							.encrypt((String) session
									.getAttribute("session_pool"));
				} catch (Exception e) {
					poolName = "";
				}
				String URL = "";
				URL = "http://" + request.getServerName() + ":"
						+ request.getServerPort() + request.getContextPath()
						+ "/recruit/PartnerLogin_input.action?infoId="
						+ poolName;

				String password = "";
				String partnerName = String.valueOf(forgotUserData[0][0]);
				String[] to_Emailid = new String[1];
				to_Emailid[0] = String.valueOf(forgotUserData[0][1]);
				String[] subject = { "Partner Login Details" };
				String[] message = new String[1];
				String loginPassMsg = "";

				password =new StringEncrypter("DESede").decrypt(String.valueOf(forgotUserData[0][2])) ;
					
				/*	
					new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
						.decrypt(String.valueOf(forgotUserData[0][2]).trim());*/

				loginPassMsg = "User Name :"
						+ String.valueOf(forgotUserData[0][1])
						+ "<br>Password  :" + password + "<br><br>";
				message[0] = getMessage(partnerName, URL, loginPassMsg);
				MailUtility mod = new MailUtility();
				mod.initiate(context, session);
				mod.sendMail(to_Emailid, mod.getDefaultMailIds(), subject,
						message, "", "");
				mod.terminate();
			} else {
				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

}