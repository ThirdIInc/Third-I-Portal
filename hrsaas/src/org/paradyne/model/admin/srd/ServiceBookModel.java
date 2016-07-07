package org.paradyne.model.admin.srd;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.ReportGenerator;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.model.leave.LeavePolicyModel;
import org.paradyne.bean.admin.srd.ServiceBook;
import com.lowagie.text.*;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceBookModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ServiceBookModel.class);

	private int RCOUNT = 0;
	private String[] RPN = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII",
			"IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII",
			"XVIII", "XIX", "XX" };

	private String checkNull(String data) {
		if (data == null) {
			return "";
		} else {
			return data;
		}

	}

	public void generateReport(ServiceBook bean, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ReportDataSet rds = new ReportDataSet();
			rds.setReportType(bean.getRepType());
			rds.setFileName("Employee Service Book");
			rds.setPageSize("A3");
			rds.setPageOrientation("portrait");
			rds.setMarginBottom(1.5f);
			rds.setMarginLeft(1.5f);
			rds.setMarginRight(1.5f);
			rds.setMarginTop(1.5f);

			ReportGenerator rg = new ReportGenerator(rds);

			/**
			 * Employee Photo and Report Title
			 */
			String img = "C:\\hrwork\\dataFiles\\NoImage.jpg";

			String empPhotoSql = " SELECT EMP_PHOTO FROM HRMS_EMP_OFFC WHERE EMP_ID = "
					+ bean.getEmpId();
			Object[][] empPhotoObj = getSqlModel().getSingleResult(empPhotoSql);

			if (empPhotoObj != null && empPhotoObj.length > 0) {
				String empPhoto = String.valueOf(empPhotoObj[0][0]);

				if (!(empPhoto.equals("null") || empPhoto.equals(null)
						|| empPhoto.equals("") || empPhoto == null || empPhoto == "")) {
					File imgFile = new File("C:\\hrwork\\dataFiles\\"
							+ empPhoto);
					if (imgFile.exists()) {
						img = "C:\\hrwork\\dataFiles\\" + empPhoto;
					}
				}
			}

			Object[][] objTitle = new Object[1][2];
			objTitle[0][0] = rg.getImage(img, request);
			objTitle[0][1] = "Employee Service Book Report";

			TableDataSet tdsTitle = new TableDataSet();
			tdsTitle.setData(objTitle);
			tdsTitle.setBodyFontDetails(Font.COURIER, 15, Font.BOLD, new Color(
					0, 0, 0));
			tdsTitle.setCellAlignment(new int[] { 0, 1 });
			tdsTitle.setCellWidth(new int[] { 10, 90 });
			tdsTitle.setBlankRowsBelow(5);
			tdsTitle.setCellColSpan(new int[] { 1, 7 });
			rg.addTableToDoc(tdsTitle);

			/**
			 * Official Details
			 */
			getOfficialDetails(rg, bean);

			/**
			 * Personal Information
			 */
			getPersonalDetails(rg, bean);
			// rg.pageBreak();

			/**
			 * Qualification Details
			 */
			getQualificationDetails(rg, bean);

			/**
			 * Address Details
			 */
			getAddressDetails(rg, bean);

			/**
			 * Family Details
			 */
			getFamilyDetails(rg, bean);

			/**
			 * Nomination Details
			 */
			getNominationDetails(rg, bean);

			/**
			 * Experience Details
			 */
			getExperienceDetails(rg, bean);
			// rg.pageBreak();

			/**
			 * Salary Details
			 */
			getSalaryDetails(rg, bean);

			/**
			 * DQE Details
			 */
			getDQEDetails(rg, bean);

			/**
			 * Leave Details
			 */
			getLeaveDetails(rg, bean);

			/**
			 * Leave Balance Details
			 */
			getLeaveBalance(rg, bean);

			/**
			 * Award Details
			 */
			getAwardDetails(rg, bean);

			/**
			 * Promotion Details
			 */
			// getPromotionDetails(rg, bean);
			// rg.pageBreak();
			/**
			 * Punishment Details
			 */
			getPunishmentDetails(rg, bean);

			/**
			 * Training Details
			 */
			// getTrainingDetails(rg, bean);
			// rg.pageBreak();
			/**
			 * Pay History Details
			 */
			// getPayHistoryDetails(rg,bean);
			// rg.pageBreak();
			/**
			 * Check List Details
			 */
			getCheckListDetails(rg, bean);

			/**
			 * Transfer Details
			 */
			getTransferDetails(rg, bean);

			// PdfWriter writer=null;
			// Document document=new Document();
			// Image jpg=Image.getInstance("C:\\a.jpg");
			// document.add(jpg);
			// document.close();

			/*
			 * Document document=new Document();
			 * PdfWriter.getInstance(document,new FileOutputStream("a.jpg"));
			 * document.open(); Image image = Image.getInstance ("C:\\a.jpg");
			 * document.add(new Paragraph("Roseindia")); document.add(image);
			 * document.close();
			 */

			// onOpenDocument(writer,document);
			getPromotionHistoryDetails(rg, bean);

			rg.process();

			rg.createReport(response);
		} catch (Exception e) {
			logger.error("Exception in generateReport:" + e);
		}
	}

	private void getAddressDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = " SELECT DECODE(ADD_TYPE,'P','Permanent','E','Emergency','L','Local'), NVL(ADD_1,' ') || '' || NVL(ADD_2,' ') || '' || NVL(ADD_3,' '),"
				+ " NVL(ADD_PH1,' ')||' / '||NVL(ADD_PH2,' '),NVL(ADD_MOBILE,' '),NVL(ADD_EMAIL,' ') FROM HRMS_EMP_ADDRESS "
				+ " WHERE EMP_ID=" + bean.getEmpId();

		try {
			Object objData[][] = getSqlModel().getSingleResult(SQL);

			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Address Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setBorder(true);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0, 0, 0, 0 };
				int[] cellWidth = { 10, 35, 15, 15, 20 };
				int[] cellColSpan = { 1, 2, 1, 1, 2 };

				String[] objHeader = { "Address Type", "Address", "Phone No.",
						"Mobile No.", "Email Id" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);

				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}

		} catch (Exception e) {
			logger.error("Exception in getAddressDetails:" + e);
		}
	}

	private void getAwardDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = " SELECT HRMS_AWARD_MASTER.AWARD_TYPE, TO_CHAR(AWARD_DATE,'DD-MM-YYYY'), AWARD_NAME FROM HRMS_AWARD  "
				+ " LEFT JOIN HRMS_AWARD_MASTER ON(HRMS_AWARD.AWARD_TYPE_ID=HRMS_AWARD_MASTER.AWARD_CODE)"
				+ " WHERE EMP_ID =" + bean.getEmpId();

		try {
			Object objData[][] = getSqlModel().getSingleResult(SQL);
			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Award Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0, 0 };
				int[] cellWidth = { 20, 10, 20 };
				int[] cellColSpan = { 2, 1, 4 };

				String[] objHeader = { "Award Type", "Award Date",
						"Award Description" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getAwardDetails:" + e);
		}
	}

	private void getPersonalDetails(ReportGenerator rg, ServiceBook bean) {
		String sqlQuery = "SELECT "
				+ " NVL(EMP_NATIONALITY,' '),NVL(EMP_PASSPORT,' ')||' '||NVL(TO_CHAR(EMP_PASSPORT_EXPDATE,'DD-MM-YYYY'),' '),NVL(EMP_BLDGP, ' ') ,"
				+ " NVL(HRMS_RELIGION.RELIGION_NAME,' '),NVL( HRMS_CATG.CATG_NAME,' '),"
				+ " CASE WHEN EMP_ISHANDICAP='Y' THEN 'Yes' WHEN EMP_ISHANDICAP='N' THEN 'No' ELSE '' END,"
				+ " NVL(EMP_IDMARK,' '),"
				+ " CASE WHEN EMP_MARITAL_STATUS='M' THEN 'Married' "
				+ " WHEN EMP_MARITAL_STATUS='U' THEN 'Unmarried' "
				+ " WHEN EMP_MARITAL_STATUS='W' THEN 'Widow'"
				+ " WHEN EMP_MARITAL_STATUS='A' THEN 'Widower' "
				+ " WHEN EMP_MARITAL_STATUS='D' THEN 'Divorce'  END,"
				+ " NVL(EMP_WEIGHT,' '),NVL(EMP_HEIGHT,' ') ,NVL(EMP_HOBBY,' ') FROM HRMS_EMP_OFFC  "
				+ " INNER JOIN HRMS_EMP_PERS ON(HRMS_EMP_PERS.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
				+ " LEFT JOIN HRMS_CATG ON(HRMS_EMP_PERS.EMP_CASTE_CATG=HRMS_CATG.CATG_ID)"
				+ " LEFT JOIN HRMS_RELIGION ON(HRMS_RELIGION.RELIGION_ID=HRMS_EMP_PERS.EMP_RELIGION)"
				+ " WHERE HRMS_EMP_OFFC.EMP_ID=" + bean.getEmpId();

		try {

			Object data[][] = getSqlModel().getSingleResult(sqlQuery);

			// String name=String.valueOf(data[0][0]);
			// String dob=String.valueOf(data[0][1]);
			String nationality = String.valueOf(data[0][0]);
			String passport = String.valueOf(data[0][1]);
			String bg = String.valueOf(data[0][2]);
			String religion = String.valueOf(data[0][3]);
			String casteCat = String.valueOf(data[0][4]);
			String handiCap = String.valueOf(data[0][5]);
			String mark = String.valueOf(data[0][6]);
			String martialSt = String.valueOf(data[0][7]);
			String wght = String.valueOf(data[0][8]);
			String hgt = String.valueOf(data[0][9]);
			String hobby = String.valueOf(data[0][10]);

			int count1 = 0;
			int z = 1;
			for (int i = 0; i < data[0].length; i++) {
				if (!(String.valueOf(data[0][i]).trim().equals(""))
						&& !(String.valueOf(data[0][i]) == null)
						&& !String.valueOf(data[0][i]).equals("null")) {
					count1++;
				}
			}

			int counter1 = 0;

			Object param[][] = new Object[count1][3];

			if (!(nationality.trim().equals("")) && !(nationality == null)
					&& !nationality.equals("null")) {
				param[counter1][0] = z;
				param[counter1][1] = "Nationality";
				param[counter1][2] = "" + nationality;
				counter1++;
				z++;
			}

			if (!(passport.trim().equals("")) && !(passport == null)
					&& !passport.equals("null")) {
				param[counter1][0] = z;
				param[counter1][1] = "Passport No. & Expiry Date";
				param[counter1][2] = "" + passport;
				counter1++;
				z++;
			}
			if (!(bg.trim().equals("")) && !(bg == null) && !bg.equals("-1")) {
				param[counter1][0] = z;
				param[counter1][1] = "Blood Group";
				param[counter1][2] = "" + bg;
				counter1++;
				z++;
			}
			if (!(religion.trim().equals("")) && !(religion == null)
					&& !religion.equals("null")) {
				param[counter1][0] = z;
				param[counter1][1] = "Religion";
				param[counter1][2] = "" + religion;
				counter1++;
				z++;
			}
			if (!(casteCat.trim().equals("")) && !(casteCat == null)
					&& !casteCat.equals("null")) {
				param[counter1][0] = z;
				param[counter1][1] = "Caste Category: ";
				param[counter1][2] = "" + casteCat;
				counter1++;
				z++;
			}
			if (!(handiCap.trim().equals("")) && !(handiCap == null)
					&& !handiCap.equals("null")) {
				param[counter1][0] = z;
				param[counter1][1] = "Is Handicap ";
				param[counter1][2] = "" + handiCap;// +handicsap;
				counter1++;
				z++;
			}
			if (!(mark.trim().equals("")) && !(mark == null)
					&& !mark.equals("null")) {
				param[counter1][0] = z;
				param[counter1][1] = "ID Mark";
				param[counter1][2] = "" + mark;
				counter1++;
				z++;
			}
			if (!(martialSt.trim().equals("")) && !(martialSt == null)
					&& !martialSt.equals("null")) {
				param[counter1][0] = z;
				param[counter1][1] = "Martial Status ";
				param[counter1][2] = "" + martialSt;
				counter1++;
				z++;
			}
			if (!(wght.trim().equals("")) && !(wght == null)
					&& !wght.equals("null")) {
				param[counter1][0] = z;
				param[counter1][1] = "Weight";
				param[counter1][2] = "" + wght;
				counter1++;
				z++;
			}
			if (!(hgt.trim().equals("")) && !(hgt == null)
					&& !hgt.equals("null")) {
				param[counter1][0] = z;
				param[counter1][1] = "Height";
				param[counter1][2] = "" + hgt;
				counter1++;
				z++;
			}
			if (!(hobby.trim().equals("")) && !(hobby == null)
					&& !hobby.equals("null")) {
				param[counter1][0] = z;
				param[counter1][1] = "Hobbies";
				param[counter1][2] = "" + hobby;
				counter1++;
				z++;
			}

			Object[][] objData = new Object[counter1][3];

			for (int i = 0; i < counter1; i++) {
				objData[i][0] = param[i][0];
				objData[i][1] = param[i][1];
				objData[i][2] = param[i][2];

			}
			if (z > 1) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Personal Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 1, 0, 0 };
				int[] cellWidth = { 10, 25, 25 };
				int[] cellColSpan = { 1, 2, 4 };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				tdsHeaderAndData.setColumnColor(1, new Color(192, 192, 192),
						new Font());
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getPersonaldetails:" + e);
		}
	}

	/*
	 * private ReportGenerator getCertAttstn(ReportGenerator rg, ServiceBook
	 * bean) { String SQL = "";
	 * 
	 * String colNames[] = {"Sl.No", "Subject", "Certificate", "Signature and
	 * designation of the Certifying officer"}; // int
	 * []cellwidth={20,25,25,25}; int[] cellwidth = {4, 32, 32, 32};
	 * 
	 * int[] alignmnet = {0, 0, 0, 0};
	 * 
	 * rg.addText("II-Certificate and Attestation", 0, 1, 0);
	 *  // Object data[][]=getCertRecord(bean); Object data[][] = new
	 * Object[10][4]; // data[0][0]="1";data[0][1]="Medical
	 * Examination";data[0][2]=" // ";data[0][3]=" "; data[0][0] = "1";
	 * data[0][1] = "Medical Examination"; data[0][2] = "The employee was
	 * medically examined by_____________________ on _________and found fit.The
	 * medical certfificate has been kept in safe custody."; data[0][3] = " "; //
	 * data[1][0]="2";data[1][1]="Character & antecedent";data[1][2]=" //
	 * ";data[1][3]=" "; data[1][0] = "2"; data[1][1] = "Character &
	 * antecedent"; data[1][2] = "His/her character and antecendent have been
	 * verified and the verification report kept in safe custody. "; data[1][3] = " ";
	 * data[2][0] = "3"; data[2][1] = "Allegiance to the Constitution";
	 * data[2][2] = " He/She has taken the oath of allegiance/affirmatioin to
	 * the constitution"; data[2][3] = " "; //
	 * data[2][0]="3";data[2][1]="Allegiance to the //
	 * Constitution";data[2][2]=" ";data[2][3]=" "; //
	 * data[3][0]="4";data[3][1]="Oath of Secrecy";data[3][2]=" //
	 * ";data[3][3]=" "; data[3][0] = "4"; data[3][1] = "Oath of Secrecy";
	 * data[3][2] = " He/She has read the Official Secrets Act and Central
	 * Services(Conduct)Rules has also taken the oath of secrecy."; data[3][3] = " ";
	 * data[4][0] = "5"; data[4][1] = "Marital Status"; data[4][2] = "He/She has
	 * furnished declaration regarding his/her not having contracted bigmaous
	 * marriage.The relevant declaration has been filed. "; data[4][3] = " "; //
	 * data[4][0]="5";data[4][1]="Marital Status";data[4][2]=" // ";data[4][3]=" ";
	 * data[5][0] = "6"; data[5][1] = "Declaration of home town"; data[5][2] =
	 * "He /She has furnished the declaration of home town which has been
	 * accepted and filed."; data[5][3] = " "; //
	 * data[5][0]="6";data[5][1]="Declaration of home //
	 * town";data[5][2]="";data[5][3]=" "; data[6][0] = "7"; data[6][1] =
	 * "Verification of entries in Part I"; data[6][2] = "The correctness of the
	 * entries against Sl.No.5-8 of part.I Bio-data has been verified from
	 * original certificates considered as valid documentary evidence for the
	 * respective purposes.Attested copies of these certificates have been
	 * filed. "; data[6][3] = " "; // data[6][0]="7";data[6][1]="Verification of
	 * entries in Part // I";data[6][2]="";data[6][3]=" "; //
	 * data[7][0]="8";data[7][1]="(a) G.P.F. No. \n (b) Nomination for // G.P.F.
	 * ";data[7][2]="";data[7][3]=" "; data[7][0] = "8"; data[7][1] = "(a)
	 * G.P.F. No. \n (b) Nomination for G.P.F. "; data[7][2] = "He/She has filed
	 * nomination for G.P.F. and the following related notices which have been
	 * forwarded to the Accounts Officer on dates shown against them have been
	 * filed. "; data[7][3] = " "; // data[8][0]="9";data[8][1]="Family //
	 * Particulars";data[8][2]="";data[8][3]=" "; data[8][0] = "9"; data[8][1] =
	 * "Family Particulars"; data[8][2] = "He/She has furnished details of the
	 * family members which have been filed."; data[8][3] = " "; //
	 * data[9][0]="10";data[9][1]="D.C.R. gratuity and family //
	 * pension";data[9][2]="";data[9][3]=" "; data[9][0] = "10"; data[9][1] =
	 * "D.C.R. gratuity and family pension"; data[9][2] = "He/She has filed
	 * nomination for D.C.R. gratuity and family pension and the following
	 * related notice which have been filed."; data[9][3] = " ";
	 * rg.tableBody(colNames, data, cellwidth, alignmnet); return rg; }
	 */

	private void getCheckListDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = "  SELECT HRMS_CHECKLIST.CHECK_NAME, HRMS_CHECKLIST.CHECK_DESC, CASE WHEN CHECK_SUBMIT='true' THEN 'Yes' ELSE 'No' END  FROM HRMS_CHECKLIST "
				+ " LEFT JOIN HRMS_EMP_CHECKLIST ON(HRMS_EMP_CHECKLIST.CHECK_CODE=HRMS_CHECKLIST.CHECK_CODE) "
				+ " WHERE HRMS_CHECKLIST.CHECK_TYPE='J' AND HRMS_EMP_CHECKLIST.EMP_ID="
				+ bean.getEmpId() + " ORDER BY HRMS_CHECKLIST.CHECK_CODE ";

		try {
			Object objData[][] = getSqlModel().getSingleResult(SQL);

			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Employee Check List Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0, 1 };
				int[] cellWidth = { 20, 20, 10 };
				int[] cellColSpan = { 3, 3, 1 };

				String[] objHeader = { "Check Name", "Description", "Submitted" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getCheckListDetails:" + e);
		}
	}

	private void getDQEDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = "SELECT ROWNUM,HRMS_RANK.RANK_NAME,HRMS_TRADE.TRADE_NAME,TO_CHAR(HRMS_DQE_HISTORY.DQE_DOJ,'DD-MM-YYYY'),HRMS_DQE_HISTORY.DQE_DC_LIST_NO,"
				+ " HRMS_DQE_HISTORY.DQE_SR_NO FROM HRMS_DQE_HISTORY"
				+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_DQE_HISTORY.DQE_POST)"
				+ " INNER JOIN HRMS_TRADE ON(HRMS_TRADE.TRADE_CODE=HRMS_DQE_HISTORY.DQE_TRADE)"
				+ " WHERE EMP_ID='" + bean.getEmpId() + "'";

		try {
			Object objData[][] = getSqlModel().getSingleResult(SQL);

			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - DQE Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0, 0, 0, 0, 0 };
				int[] cellWidth = { 20, 25, 25, 55, 70, 55 };

				String[] objHeader = { "S.No", "Post", "Trade", "Date",
						"Dc List No", "Serial No." };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getDQEDetails:" + e);
		}
	}

	public void getEmpDetails(ServiceBook bean) {

		String query = " SELECT EMP_TOKEN, "
				+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	HRMS_RANK.RANK_NAME,TO_CHAR(CENTER_ID||'-'||CENTER_NAME),HRMS_EMP_OFFC.EMP_ID "
				+ "	FROM HRMS_EMP_OFFC "
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
				+ "  WHERE HRMS_EMP_OFFC.EMP_ID = " + bean.getUserEmpId();
		Object[][] values = getSqlModel().getSingleResult(query);

		bean.setToken(checkNull(String.valueOf(values[0][0])));
		bean.setEmpId(checkNull(String.valueOf(values[0][4])));
		bean.setEmpName(checkNull(String.valueOf(values[0][1])));
		bean.setRank(checkNull(String.valueOf(values[0][2])));
		bean.setCenter(checkNull(String.valueOf(values[0][3])));

	}

	private void getExperienceDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = " SELECT NVL(EXP_EMPLOYER,' '), NVL(EXP_JOBTITLE,' '), NVL(EXP_JOBDESC,' '), NVL(TO_CHAR(EXP_JOINING_DATE,'DD-MM-YYYY'),' '), "
				+ " NVL(TO_CHAR(EXP_RELIEVING_DATE,'DD-MM-YYYY'),' '), NVL(EXP_SCALE_OF_PAY,''), EXP_ID  FROM HRMS_EMP_EXP "
				+ " WHERE HRMS_EMP_EXP.EMP_ID = " + bean.getEmpId();

		try {
			Object dataExp[][] = getSqlModel().getSingleResult(SQL);
			Object objData[][] = null;

			if (dataExp != null && dataExp.length > 0) {
				objData = new Object[dataExp.length][6];

				for (int i = 0; i < dataExp.length; i++) {
					for (int j = 0; j < dataExp[0].length - 1; j++) {
						objData[i][j] = dataExp[i][j];
					}
				}
			}

			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Experience Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0, 0, 0, 0, 2 };
				int[] cellWidth = { 20, 25, 25, 14, 14, 15 };
				int[] cellColSpan = { 1, 1, 2, 1, 1, 1 };

				String[] objHeader = { "Employer", "Designation",
						"Job Description", "Joining Date", "Relieving Date",
						"CTC" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;

				for (int j = 0; j < objData.length; j++) {
					String PROJSQL = "SELECT NVL(PROJECT_NAME,''),  NVL(PROJECT_DURATION,''),NVL(PROJECT_TEAMSIZE,''),NVL(PROJECT_ROLE,''),NVL(PROJECT_DESCRIPTION,'') "
							+ "  FROM HRMS_EMP_EXP_PROJDTLS "
							+ " INNER JOIN HRMS_EMP_EXP ON(HRMS_EMP_EXP.EXP_ID=HRMS_EMP_EXP_PROJDTLS.PROJECT_EXP_ID)	 WHERE HRMS_EMP_EXP.EMP_ID ="
							+ bean.getEmpId()
							+ " AND PROJECT_EXP_ID="
							+ Integer.parseInt(String.valueOf(dataExp[j][6]))
							+ "";

					try {
						Object objData1[][] = getSqlModel().getSingleResult(
								PROJSQL);

						if (objData1 != null && objData1.length > 0) {
							/**
							 * Set title
							 */
							Object[][] objTitle1 = new Object[1][1];
							objTitle1[0][0] = RPN[RCOUNT]
									+ " - Project Experience Details for "
									+ String.valueOf(objData[j][0]);

							TableDataSet tdsTitle1 = new TableDataSet();
							tdsTitle1.setData(objTitle1);
							tdsTitle1.setCellAlignment(new int[] { 1 });
							tdsTitle1.setCellWidth(new int[] { 100 });
							tdsTitle1.setCellColSpan(new int[] { 7 });
							tdsTitle1.setBodyFontDetails(Font.HELVETICA, 12,
									Font.BOLD, new Color(0, 0, 0));
							tdsTitle1.setBorder(true);
							rg.addTableToDoc(tdsTitle1);

							/**
							 * Set data
							 */
							int[] cellAlignment1 = { 0, 0, 0, 0, 0 };
							int[] cellWidth1 = { 20, 15, 15, 20, 25 };
							int[] cellColSpan1 = { 1, 1, 1, 1, 3 };

							String[] objHeader1 = { "Project Name",
									"Project Duration(In Months)",
									"Project Team Size", "Role In Project",
									"Project Description" };

							TableDataSet tdsHeaderAndData1 = new TableDataSet();
							tdsHeaderAndData1.setHeader(objHeader1);
							tdsHeaderAndData1.setData(objData1);
							tdsHeaderAndData1.setCellAlignment(cellAlignment1);
							tdsHeaderAndData1.setCellWidth(cellWidth1);
							tdsHeaderAndData1.setCellColSpan(cellColSpan1);
							tdsHeaderAndData1.setBorder(true);
							tdsHeaderAndData1.setBlankRowsBelow(2);
							rg.addTableToDoc(tdsHeaderAndData1);

							RCOUNT++;
						}
					} catch (Exception e) {
						logger.error("Exception in getExperienceDetails:" + e);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getExperienceDetails:" + e);
		}
	}

	private void getFamilyDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = "SELECT FML_FNAME || ' '||FML_MNAME || ' ' ||FML_LNAME,nvl(to_char(FML_DOB,'DD-MM-YYYY'),' '),DECODE(FML_GENDER,'M','Male','F','Female'),RELATION_NAME,"
				+ " NVL(FML_PROFESSION,' '),DECODE(FML_MARITAL_STATUS,'M','Married','S','Single'), "
				+ " DECODE(FML_ISDEPENDENT,'Y','Yes','N','No') FROM HRMS_EMP_FML"
				+ " INNER JOIN HRMS_RELATION ON(HRMS_RELATION.RELATION_CODE=HRMS_EMP_FML.FML_RELATION)"
				+ " WHERE HRMS_EMP_FML.EMP_ID=" + bean.getEmpId();
		try {
			Object objData[][] = getSqlModel().getSingleResult(SQL);

			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Family Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0, 0, 0, 0, 0, 0 };
				int[] cellWidth = { 60, 30, 25, 30, 38, 22, 30 };

				String[] objHeader = { "Name", "Date of Birth", "Gender",
						"Relation", "Profession", "Marital Status",
						"Is Dependant" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getFamilyDetails:" + e);
		}
	}

	private void getFamilyDtls(ReportGenerator rg, ServiceBook bean) {
		String SQL = "SELECT HRMS_RELATION.RELATION_NAME,"
				+ " HRMS_EMP_FML.FML_FNAME || '' || HRMS_EMP_FML.FML_MNAME || '' || HRMS_EMP_FML.FML_LNAME,"
				+ " HRMS_EMP_FML.FML_DOB,HRMS_EMP_FML.FML_PROFESSION,"
				+ " HRMS_EMP_FML.FML_ADDRESS,HRMS_EMP_FML.FML_PH,"
				+ " HRMS_EMP_FML.FML_EMAIL,HRMS_EMP_FML.FML_ISALIVE"
				+ " FROM HRMS_EMP_FML"
				+ " LEFT JOIN HRMS_RELATION ON(HRMS_RELATION.RELATION_CODE=HRMS_EMP_FML.FML_RELATION)"
				+ " WHERE HRMS_EMP_FML.EMP_ID=2" + bean.getEmpId();

		try {
			Object objData[][] = getSqlModel().getSingleResult(SQL);

			if (objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Family Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set header and data
				 */
				int[] cellAlignment = { 0, 0, 0, 0, 0, 0, 0, 0 };
				int[] cellWidth = { 20, 25, 25, 55, 70, 55, 45, 45 };

				String[] objHeader = { "Relation", "Name", "Date of Birth",
						"Profession", "Address", "Phone", "Email", "Alive" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getFamilyDtls:" + e);
		}
	}

	/*
	 * private ReportGenerator getFrontPage(ReportGenerator rg, ServiceBook
	 * bean) {
	 * 
	 * 
	 * String message[]={"\n\n Form M.S.O.(T)-27 (Revised)", "\n\n\n\n\n\n\n\n\n
	 * _________________________________________________________________",
	 * "\n\n\n\n\n SERVICE BOOK ", "\n\n OF\n\n\n\n\n", "\n Name :
	 * "+bean.getEmpName(), "\n Token : "+bean.getToken(), "\n Center :
	 * "+bean.getCenter(), "\n Rank : "+bean.getRank(), "\n
	 * _______________________________________________________________________",
	 * "\n\n\n\n\n\n\n\n\n\n MGIPTC-27 Civil/90-91-Giptc-(C-37)-23-891-520,000
	 * Books." };
	 * 
	 * 
	 * String message[] =
	 * {"\n\n\n\n\n\n\n\n\n\n\n\n_______________________________________________________________________",
	 * "\n\n\n\n\n SERVICE BOOK ", "\n\n OF\n\n\n\n\n", "\n Name \t\t\t: " +
	 * bean.getEmpName(), // "\n Id \t\t\t\t\t\t : "+bean.getToken(), // "\n
	 * Branch \t\t: "+bean.getCenter(), // "\n Designation : "+bean.getRank(),
	 * "\n\n\n\n\n\n ", ""};
	 * 
	 * int style[] = {2, 2, 2, 2, 2, 2, 2, 2, 2}; rg.addFormatedText(message,
	 * style, 0, 0, 1);
	 * 
	 * rg.addText("______________________________________________________________________________________",
	 * 0, 1, 1); // rg.tableBody(data, cellwidth, alignment); return rg; }
	 */

	/*
	 * private ReportGenerator getHistVerfOfSrvc(ReportGenerator rg, ServiceBook
	 * bean) { String SQL = "";
	 * 
	 * String colNames[] = {"Sl.No", "From", "To", "Post, scale of pay and
	 * office (with station)", "Substantive Pay", "Officiating Pay", "Event
	 * affecting cols. 4-6 (vide instruction 10)", "Signature and designation of
	 * attesting officer (with date)", "Signature and designation of verifying
	 * officer (with date)", "Signature of the Government Servant", "Remarks"};
	 * int[] cellwidth = {30, 45, 45, 35, 35, 35, 35, 35, 35, 35, 35}; int[]
	 * alignmnet = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	 * 
	 * rg.addText("IV - History and Verification of Service", 0, 1, 0);
	 * 
	 * Object data[][] = new Object[0][11];
	 * 
	 * rg.tableBody(colNames, data, cellwidth, alignmnet); return rg; }
	 */

	private void getLeaveBalance(ReportGenerator rg, ServiceBook bean) {
		try {
			String policyCode = "";
			String empId = bean.getEmpId();
			policyCode = getLeavePolicyCode(empId);

			String SQL = " SELECT TO_CHAR(LEAVE_NAME) AS LEAVE_NAME, NVL(LEAVE_OPENING_BALANCE, 0) AS LEAVE_OPENING_BALANCE, "
					+ " NVL(LEAVE_CLOSING_BALANCE, 0) AS LEAVE_CLOSING_BALANCE FROM HRMS_LEAVE "
					+ " LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_BALANCE.LEAVE_CODE AND EMP_ID = "
					+ bean.getEmpId()
					+ ") "
					+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE "
					+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE = "
					+ policyCode
					+ ") "
					+ " WHERE HRMS_LEAVE_POLICY_DTL.LEAVE_APPLICABLE = 'Y' ORDER BY HRMS_LEAVE.LEAVE_ID ";

			Object objData[][] = getSqlModel().getSingleResult(SQL);

			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Leave Balance";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set header and data
				 */
				int[] cellAlignment = { 0, 0, 0 };
				int[] cellWidth = { 20, 15, 15 };
				int[] cellColSpan = { 3, 2, 2 };

				String[] objHeader = { "Leave Type", "Opening Balance",
						"Closing Balance" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getLeaveBalance:" + e);
		}
	}

	private void getLeaveDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = "SELECT NVL(HRMS_LEAVE.LEAVE_NAME,' '),NVL(to_char(HRMS_LEAVE_DTL.LEAVE_FROM_DATE,'DD-MM-yyyy'),' '),NVL(to_char(HRMS_LEAVE_DTL.LEAVE_TO_DATE,'DD-MM-YYYY'),' '),"
				+ " NVL(HRMS_LEAVE_DTL.LEAVE_DAYS,0) FROM HRMS_LEAVE_DTL"
				+ " INNER JOIN HRMS_LEAVE  ON (HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_DTL.LEAVE_CODE)"
				+ " WHERE HRMS_LEAVE_DTL.EMP_ID="
				+ bean.getEmpId()
				+ " ORDER BY HRMS_LEAVE_DTL.LEAVE_FROM_DATE";

		try {
			Object objData[][] = getSqlModel().getSingleResult(SQL);

			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Leave History";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set header and data
				 */
				int[] cellAlignment = { 0, 0, 0, 0 };
				int[] cellWidth = { 10, 10, 10, 10 };
				int[] cellColSpan = { 2, 1, 1, 1 };

				String[] objHeader = { "Leave Type", "Leave\nFrom Date",
						"Leave\nTo Date", "Leave Days" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getLeaveDetails:" + e);
		}
	}

	private String getLeavePolicyCode(String empId) {
		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);

		String leavePolicyCode = model.getLeavePolicy(empId);

		model.terminate();
		return leavePolicyCode;
	}

	private void getNominationDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = "SELECT FML_FNAME || ' '||FML_MNAME || ' ' ||FML_LNAME,DECODE(NOM_TYPE,'G','GRATUITY','F','GPF/PPAN','P','PENSION','S','CGEGIS','N','NOK'),"
				+ " TO_CHAR(FML_DOB,'DD-MM-YYYY'),DECODE(FML_GENDER,'M','Male','F','Female'),RELATION_NAME"
				+ " FROM  HRMS_EMP_NOMINEE"
				+ " INNER JOIN HRMS_EMP_FML ON(HRMS_EMP_FML.FML_ID=HRMS_EMP_NOMINEE.NOM_NOMINEE)"
				+ " INNER JOIN HRMS_RELATION ON(HRMS_RELATION.RELATION_CODE=HRMS_EMP_FML.FML_RELATION)"
				+ " WHERE HRMS_EMP_NOMINEE.EMP_ID=" + bean.getEmpId();

		try {
			Object objData[][] = getSqlModel().getSingleResult(SQL);

			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Nomination Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set header and data
				 */
				int[] cellAlignment = { 0, 0, 0, 0, 0 };
				int[] cellWidth = { 60, 25, 45, 25, 30 };
				int[] cellColSpan = { 2, 2, 1, 1, 1 };

				String[] objHeader = { "Nominee", "Nominee Type",
						"Date of Birth", "Gender", "Relation" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}

		} catch (Exception e) {
			logger.error("Exception in getNominationDetails:" + e);
		}
	}

	private void getOfficialDetails(ReportGenerator rg, ServiceBook bean) {
		String query = " SELECT NVL(t1.TITLE_NAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') ,"
				+ " 	NVL(HRMS_EMP_OFFC.EMP_TOKEN,' ')"
				+ " ,NVL(DIV_NAME,' '),NVL(CENTER_NAME,' '),NVL(DEPT_NAME,' '),NVL(RANK_NAME,' '),NVL(SHIFT_NAME,' '),NVL(TYPE_NAME,' '),NVL(PAYBILL_GROUP,' '),"
				+ " NVL(T2.TITLE_NAME,' ')||' '||NVL(E1.EMP_FNAME,' ')||' '||NVL(E1.EMP_MNAME,' ')||' '||NVL(E1.EMP_LNAME,' '),"
				+ " CASE WHEN HRMS_EMP_OFFC.EMP_GENDER='M' THEN 'Male' WHEN HRMS_EMP_OFFC.EMP_GENDER='F' THEN 'Female' ELSE 'Other' END, "
				+ " CASE WHEN HRMS_EMP_OFFC.EMP_STATUS='S' THEN 'Service' WHEN HRMS_EMP_OFFC.EMP_STATUS='R' THEN 'Retired' WHEN HRMS_EMP_OFFC.EMP_STATUS='N' THEN 'Resigned' WHEN HRMS_EMP_OFFC.EMP_STATUS='E' THEN 'Terminated' END,"
				+ " NVL(CADRE_NAME,' '),NVL(TRADE_NAME,' '),NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),'') ,"
				+ " NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_INCR_DATE,'DD-MM-YYYY'),' '),  "
				+ " NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_DOB,'DD-MM-YYYY'),''),NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' '), CASE WHEN EMP_GENDER='M' THEN 'Male' WHEN EMP_GENDER='F' THEN 'Female' ELSE 'Other' END, "
				+ " CASE WHEN EMP_STATUS='S' THEN 'Service' WHEN EMP_STATUS='R' THEN 'Retired' WHEN EMP_STATUS='N' THEN 'Resigned' WHEN EMP_STATUS='E' THEN 'Terminated' END "
				+ " ,NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_CONFIRM_DATE,'DD-MM-YYYY'),' ') FROM HRMS_EMP_OFFC"
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) 	"
				+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "
				+ " LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_EMP_OFFC.EMP_DIV)"
				+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
				+ " LEFT JOIN HRMS_TRADE ON(HRMS_TRADE.TRADE_CODE=HRMS_EMP_OFFC.EMP_TRADE)"
				+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
				+ " LEFT JOIN HRMS_SHIFT ON(HRMS_SHIFT.SHIFT_ID=HRMS_EMP_OFFC.EMP_SHIFT) "
				+ " LEFT JOIN HRMS_TITLE t1 ON(t1.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " LEFT JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID=HRMS_EMP_OFFC.EMP_REPORTING_OFFICER)"
				+ " LEFT JOIN HRMS_TITLE T2 ON(T2.TITLE_CODE=E1.EMP_TITLE_CODE) "
				+ " INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)"
				+ " LEFT JOIN HRMS_PAYBILL ON(HRMS_EMP_OFFC.EMP_PAYBILL=HRMS_PAYBILL.PAYBILL_ID)"
				+ " WHERE HRMS_EMP_OFFC.EMP_ID = " + bean.getEmpId();
		Object data[][] = getSqlModel().getSingleResult(query);
		int count1 = 0;
		int z = 1;
		for (int i = 0; i < data[0].length; i++) {
			if (!(String.valueOf(data[0][i]).equals(""))
					&& !(String.valueOf(data[0][i]) == null)
					&& !String.valueOf(data[0][i]).equals("null")) {
				count1++;
			}
		}

		int counter1 = 0;

		Object[][] param = new Object[count1][3];

		if (!(String.valueOf(data[0][0]) == null)
				&& !(String.valueOf(data[0][0]).trim().equals(""))
				&& !String.valueOf(data[0][0]).equals("null")) {
			param[counter1][0] = z;
			param[counter1][1] = "Name in Full(Block Letters)";
			param[counter1][2] = "" + String.valueOf(data[0][0]).toUpperCase();
			counter1++;
			z++;
		}

		if (!(String.valueOf(data[0][1]).trim().equals(""))
				&& !(String.valueOf(data[0][1]) == null)
				&& !(String.valueOf(data[0][1]).equals("null"))) {
			param[counter1][0] = z;
			param[counter1][1] = "Employee Id";
			param[counter1][2] = "" + String.valueOf(data[0][1]);
			counter1++;
			z++;
		}

		if (!(String.valueOf(data[0][16]).trim().equals(""))
				&& !(String.valueOf(data[0][16]) == null)
				&& !String.valueOf(data[0][16]).equals("null")) {
			param[counter1][0] = z;
			param[counter1][1] = "Date of Birth";
			param[counter1][2] = "" + String.valueOf(data[0][16]);
			counter1++;
			z++;
		}

		if (!(String.valueOf(data[0][10]).trim().equals(""))
				&& !(String.valueOf(data[0][10]) == null)
				&& !String.valueOf(data[0][10]).equals("null")) {
			param[counter1][0] = z;
			param[counter1][1] = "Gender";
			param[counter1][2] = "" + String.valueOf(data[0][10]);
			counter1++;
			z++;
		}
		if (!(String.valueOf(data[0][2]).trim().equals(""))
				&& !(String.valueOf(data[0][2]) == null)
				&& !String.valueOf(data[0][2]).equals("null")) {
			param[counter1][0] = z;
			param[counter1][1] = "Division";
			param[counter1][2] = "" + String.valueOf(data[0][2]);
			counter1++;
			z++;
		}

		if (!(String.valueOf(data[0][3]).trim().equals(""))
				&& !(String.valueOf(data[0][3]) == null)
				&& !String.valueOf(data[0][3]).equals("null")) {
			param[counter1][0] = z;
			param[counter1][1] = "Branch";
			param[counter1][2] = "" + String.valueOf(data[0][3]);
			counter1++;
			z++;
		}

		if (!(String.valueOf(data[0][4]).trim().equals(""))
				&& !(String.valueOf(data[0][4]) == null)
				&& !String.valueOf(data[0][4]).equals("null")) {
			param[counter1][0] = z;
			param[counter1][1] = "Department";
			param[counter1][2] = "" + String.valueOf(data[0][4]);
			counter1++;
			z++;
		}

		if (!(String.valueOf(data[0][5]).trim().equals(""))
				&& !(String.valueOf(data[0][5]) == null)
				&& !String.valueOf(data[0][5]).equals("null")) {
			param[counter1][0] = z;
			param[counter1][1] = "Designation";
			param[counter1][2] = "" + String.valueOf(data[0][5]);
			counter1++;
			z++;
		}

		if (!(String.valueOf(data[0][6]).trim().equals(""))
				&& !(String.valueOf(data[0][6]) == null)
				&& !String.valueOf(data[0][6]).equals("null")) {
			param[counter1][0] = z;
			param[counter1][1] = "Shift";
			param[counter1][2] = "" + String.valueOf(data[0][6]);
			counter1++;
			z++;
		}

		if (!(String.valueOf(data[0][7]).trim().equals(""))
				&& !(String.valueOf(data[0][7]) == null)
				&& !String.valueOf(data[0][7]).equals("null")) {
			param[counter1][0] = z;
			param[counter1][1] = "Employee Type";
			param[counter1][2] = "" + String.valueOf(data[0][7]);
			counter1++;
			z++;
		}

		if (!(String.valueOf(data[0][8]).trim().equals(""))
				&& !(String.valueOf(data[0][8]) == null)
				&& !String.valueOf(data[0][8]).equals("null")) {
			param[counter1][0] = z;
			param[counter1][1] = "Pay Bill";
			param[counter1][2] = "" + String.valueOf(data[0][8]);
			counter1++;
			z++;
		}

		if (!(String.valueOf(data[0][9]).trim().equals(""))
				&& !(String.valueOf(data[0][9]) == null)
				&& !String.valueOf(data[0][9]).equals("null")) {
			param[counter1][0] = z;
			param[counter1][1] = "Reporting Officer";
			param[counter1][2] = "" + String.valueOf(data[0][9]);
			counter1++;
			z++;
		}

		if (!(String.valueOf(data[0][19]).trim().equals(""))
				&& !(String.valueOf(data[0][19]) == null)
				&& !String.valueOf(data[0][19]).equals("null")) {
			param[counter1][0] = z;
			param[counter1][1] = "Status";
			param[counter1][2] = "" + String.valueOf(data[0][19]);
			counter1++;
			z++;
		}

		if (!(String.valueOf(data[0][12]) == null)
				&& !(String.valueOf(data[0][12]).trim().equals(""))
				&& !String.valueOf(data[0][12]).equals("null")) {
			param[counter1][0] = z;
			param[counter1][1] = "Grade";
			param[counter1][2] = "" + String.valueOf(data[0][12]);
			counter1++;
			z++;
		}

		try {
			if (!(String.valueOf(data[0][13]).trim().equals(""))
					&& !(String.valueOf(data[0][13]) == null)
					&& !String.valueOf(data[0][13]).equals("null")) {
				param[counter1][0] = z;
				param[counter1][1] = "Trade";
				param[counter1][2] = String.valueOf(data[0][13]);
				counter1++;
				z++;
			}
		} catch (Exception e) {
			logger.error("Exception in getOfficialDetails :" + e);
		}

		if (!(String.valueOf(data[0][17]).trim().equals(""))
				&& !(String.valueOf(data[0][17]) == null)
				&& !(String.valueOf(data[0][17]).equals("null"))) {
			param[counter1][0] = z;
			param[counter1][1] = "Date Of Joining";
			param[counter1][2] = "" + String.valueOf(data[0][17]);
			counter1++;
			z++;
		}

		// Date of confirmation
		if (!(String.valueOf(data[0][20]).trim().equals(""))
				&& !(String.valueOf(data[0][20]) == null)
				&& !(String.valueOf(data[0][20]).equals("null"))) {
			param[counter1][0] = z;
			param[counter1][1] = "Date Of Confirmation";
			param[counter1][2] = "" + String.valueOf(data[0][20]);
			counter1++;
			z++;
		}

		if (!(String.valueOf(data[0][15]).trim().equals(""))
				&& !(String.valueOf(data[0][15]) == null)
				&& !String.valueOf(data[0][15]).equals("null")) {
			param[counter1][0] = z;
			param[counter1][1] = "Last Increment Date";
			param[counter1][2] = "" + String.valueOf(data[0][15]);
			counter1++;
			z++;
		}

		Object[][] objData = new Object[counter1][3];

		for (int i = 0; i < counter1; i++) {
			objData[i][0] = param[i][0];
			objData[i][1] = param[i][1];
			objData[i][2] = param[i][2];
		}

		if (objData != null && objData.length > 0) {
			try {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Official Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 1, 0, 0 };
				int[] cellWidth = { 10, 25, 25 };
				int[] cellColSpan = { 1, 2, 4 };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				tdsHeaderAndData.setColumnColor(1, new Color(192, 192, 192),
						new Font());
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			} catch (Exception e) {
				logger.error("Exception in getOfficialDetails :" + e);
			}
		}
	}

	private void getPayHistoryDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = "  SELECT NVL(RANK_NAME,' '),NVL(TRADE_NAME,' '),DECODE(INCR_PAY_TYPE,'0',' ','1','Annual Increment','2','Step Up','3','Ante dating','4','Pay Fixation','5','Promotion','6','ACP','7','Placement','8','Special Increment','9','Sports','10','Stagnation','11','Re-fixation','12','Deduction','13','Restoration'), "
				+ " NVL(HRMS_CENTER.CENTER_NAME,' '),NVL(INCR_NEW_BASIC,0),  NVL(TO_CHAR(INCR_EFFECTIVE_DATE,'DD-MM-YYYY'),' ')  FROM HRMS_INCR_HDR "
				+ " INNER JOIN HRMS_RANK ON (HRMS_INCR_HDR.INCR_RANK=HRMS_RANK.RANK_ID)"
				+ " INNER JOIN HRMS_TRADE ON (HRMS_INCR_HDR.INCR_TRADE=HRMS_TRADE.TRADE_CODE)"
				+ " INNER JOIN HRMS_CENTER ON (HRMS_INCR_HDR.INCR_CENTER_NO=HRMS_CENTER.CENTER_ID)"
				+ " WHERE EMP_ID =" + bean.getEmpId() + " ORDER BY INCR_CODE ";

		try {
			Object objData[][] = getSqlModel().getSingleResult(SQL);

			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Pay History";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0, 0, 0, 0, 0 };
				int[] cellWidth = { 20, 20, 20, 20, 10, 10 };
				int[] cellColSpan = { 1, 1, 1, 2, 1, 1 };

				String[] objHeader = { "Post", "Trade", "Pay Type", "Center",
						"Basic", "Date of Increment" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getPayHistoryDetails:" + e);
		}
	}

	private void getPromotionDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = "SELECT NVL(RANK_NAME,' '),TO_CHAR(HRMS_PROMO_HISTORY.PROMO_FROM_DT,'DD-MM-YYYY')"
				+ " FROM HRMS_PROMO_HISTORY LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_PROMO_HISTORY.PROMO_POST)"
				+ " WHERE HRMS_PROMO_HISTORY.EMP_ID=" + bean.getEmpId();

		try {
			Object objData[][] = getSqlModel().getSingleResult(SQL);
			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Promotion Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0 };
				int[] cellWidth = { 15, 20 };
				int[] cellColSpan = { 5, 2 };

				String[] objHeader = { "Promotion Post", "Promotion Date" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getPromotionDetails:" + e);
		}
	}

	private void getPunishmentDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = "SELECT NVL(PUNISH_NAME,' '),NVL(PUNISH_AUTH,' '),NVL(TO_CHAR(PUNISH_LETTER_DT,'DD-MM-YYYY'),' '),NVL(PUNISH_PERIOD,0) FROM HRMS_PUNISH INNER JOIN HRMS_PUNISHMENT"
				+ " ON(HRMS_PUNISH.DISCP_ID = HRMS_PUNISHMENT.PUNISH_ID)"
				+ " WHERE EMP_ID ="
				+ bean.getEmpId()
				+ " ORDER BY HRMS_PUNISH.PUNISH_ID";

		try {
			Object objData[][] = getSqlModel().getSingleResult(SQL);

			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Punishment Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0, 0, 0 };
				int[] cellWidth = { 20, 20, 10, 10 };
				int[] cellColSpan = { 3, 2, 1, 1 };

				String[] objHeader = { "Punishment", "Authority",
						"Action Date", "Period" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getPunishmentDetails:" + e);
		}
	}

	private void getQualificationDetails(ReportGenerator rg, ServiceBook bean) {
		String sqlQuery = " SELECT NVL(QUA_NAME,' '),NVL(QUA_UNIV,'  '),nvl(TO_CHAR(QUA_YEAR,'DD-MM-YYYY'),'  '), "
				+ " NVL(QUA_PER,0),DECODE(QUA_ISTECH,'Y','Yes','N','No') FROM HRMS_EMP_QUA  LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_QUA.EMP_ID)"
				+ " INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK  INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_QUA ON(HRMS_QUA.QUA_ID=HRMS_EMP_QUA.QUA_MAST_CODE)  "
				+ " WHERE HRMS_EMP_QUA.EMP_ID ="
				+ bean.getEmpId()
				+ "  ORDER BY HRMS_EMP_QUA.QUA_ID ";

		try {
			Object objData[][] = getSqlModel().getSingleResult(sqlQuery);

			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Qualification Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0, 0, 0, 0 };
				int[] cellWidth = { 25, 25, 10, 10, 10 };
				int[] cellColSpan = { 2, 2, 1, 1, 1 };

				String[] objHeader = { "Qualification\nName", "University",
						"Date of\nPassing", "Percentage", "Is Technical" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getQualificationDetails:" + e);
		}
	}

	private void getSalaryDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = "SELECT NVL(SAL_PANNO,' '),nvl(SAL_GPFNO,' '),NVL(SAL_ACCNO_REGULAR,' '), NVL(B1.BANK_NAME,' '),NVL(SAL_REIMBMENT,' '),NVL(B2.BANK_NAME,' '),"
				+ " CASE WHEN SAL_PENSIONABLE='Y' THEN 'Yes'"
				+ "    WHEN SAL_PENSIONABLE='N' THEN 'No' ELSE ' ' END,"
				+ " CASE WHEN SAL_PAY_MODE='T' THEN 'Transfer' "
				+ "   WHEN SAL_PAY_MODE='C' THEN 'Cash'"
				+ "   WHEN SAL_PAY_MODE='H' THEN 'Cheque' ELSE ' ' END "
				+ ", nvl(SAL_ESCINUMBER,' ') FROM HRMS_SALARY_MISC "
				+ " LEFT JOIN HRMS_BANK B1 ON(HRMS_SALARY_MISC.SAL_MICR_REGULAR=B1.BANK_MICR_CODE) "
				+ " LEFT JOIN HRMS_BANK B2 ON(HRMS_SALARY_MISC.SAL_REIMBANK=B2.BANK_MICR_CODE) "
				+ " WHERE EMP_ID =" + bean.getEmpId();

		try {
			Object data[][] = getSqlModel().getSingleResult(SQL);

			String pan = "", pf = "", sal = "", bank = "", resal = "", rebank = "", pension = "", paymode = "", esic = "";

			if (data != null && data.length > 0) {
				pan = String.valueOf(data[0][0]);
				pf = String.valueOf(data[0][1]);
				sal = String.valueOf(data[0][2]);
				bank = String.valueOf(data[0][3]);
				resal = String.valueOf(data[0][4]);
				rebank = String.valueOf(data[0][5]);
				pension = String.valueOf(data[0][6]);
				paymode = String.valueOf(data[0][7]);
				esic = String.valueOf(data[0][8]);
			}

			int count1 = 0;
			int z = 1;
			if (data != null && data.length > 0) {
				for (int i = 0; i < data[0].length; i++) {
					if (!(String.valueOf(data[0][i]).equals(""))
							&& !(String.valueOf(data[0][i]) == null)
							&& !String.valueOf(data[0][i]).equals("null")) {
						count1++;
					}
				}
			}

			int counter1 = 0;

			Object objData[][] = new Object[count1][3];

			if (!(pan.trim().equals("")) && !(pan == null)
					&& !pan.equals("null")) {
				objData[counter1][0] = z;
				objData[counter1][1] = "PAN No.";
				objData[counter1][2] = "" + pan;
				counter1++;
				z++;
			}

			if (!(pf.trim().equals("")) && !(pf == null) && !pf.equals("null")) {
				objData[counter1][0] = z;
				objData[counter1][1] = "PF No";
				objData[counter1][2] = "" + pf;
				counter1++;
				z++;
			}

			if (!(sal.trim().equals("")) && !(sal == null)
					&& !sal.equals("null")) {
				objData[counter1][0] = z;
				objData[counter1][1] = "Salary Acc. No.";
				objData[counter1][2] = "" + sal;
				counter1++;
				z++;
			}

			if (!(bank.trim().equals("")) && !(bank == null)
					&& !bank.equals("null")) {
				objData[counter1][0] = z;
				objData[counter1][1] = "Salary Bank";
				objData[counter1][2] = "" + bank;
				counter1++;
				z++;
			}

			if (!(resal.trim().equals("")) && !(resal == null)
					&& !resal.equals("null")) {
				objData[counter1][0] = z;
				objData[counter1][1] = "Reimbursement AccNo.";
				objData[counter1][2] = "" + resal;
				counter1++;
				z++;
			}

			if (!(rebank.trim().equals("")) && !(rebank == null)
					&& !rebank.equals("null")) {
				objData[counter1][0] = z;
				objData[counter1][1] = "Reimbursement Bank ";
				objData[counter1][2] = "" + rebank;
				counter1++;
				z++;
			}

			if (!(pension.trim().equals("")) && !(pension == null)
					&& !pension.equals("-1")) {
				objData[counter1][0] = z;
				objData[counter1][1] = "Pensionable";
				objData[counter1][2] = "" + pension;
				counter1++;
				z++;
			}

			if (!(paymode.trim().equals("")) && !(paymode == null)
					&& !paymode.equals("-1")) {
				objData[counter1][0] = z;
				objData[counter1][1] = "Pay Mode";
				objData[counter1][2] = "" + paymode;
				counter1++;
				z++;
			}

			if (!(esic.trim().equals("")) && !(esic == null)
					&& !esic.equals("null")) {
				objData[counter1][0] = z;
				objData[counter1][1] = "ESIC No.";
				objData[counter1][2] = "" + esic;
				counter1++;
				z++;
			}

			if (z > 1) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Salary Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 1, 0, 0 };
				int[] cellWidth = { 10, 25, 25 };
				int[] cellColSpan = { 1, 2, 4 };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				tdsHeaderAndData.setColumnColor(1, new Color(192, 192, 192),
						new Font());
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getSalaryDetails:" + e);
		}
	}

	private void getServiceHistoryAndVerf(ReportGenerator rg, ServiceBook bean) {
		String sqlQuery = "SELECT EXP_JOINING_DATE,EXP_RELIEVING_DATE,EXP_JOBTITLE,"
				+ " EXP_JOBDESC FROM HRMS_EMP_EXP WHERE EMP_ID="
				+ bean.getEmpId();

		try {
			Object objData[][] = getSqlModel().getSingleResult(sqlQuery);

			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT]
						+ " - History and Verification of Service";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
				int[] cellWidth = { 25, 25, 55, 70, 55, 25, 25, 25, 25, 25 };

				String[] objHeader = {
						"From",
						"To",
						"Post,scale of pay and office(with station)",
						"Substantive",
						"Officiating",
						"Event affecting cols. 4-6 (vide instruction 10)",
						"Signature and designation of attesting officer(with date)",
						"Signature and designation of verifying officer (with date)",
						"Signature of the Government Servant", "Remarks" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getServiceHistoryAndVerf:" + e);
		}
	}

	private void getLICDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = "    SELECT nvl(LIC_NAME,' '),nvl(LIC_POLICY_NUMBER,' '),nvl(LIC_INSURANCE_AMOUNT,0),nvl(LIC_MONTHLY_PREMIUM,0),NVL(TO_CHAR(LIC_START_DATE,'DD-MM-YYYY'),' '),nvl(TO_CHAR(LIC_END_DATE,'DD-MM-YYYY'),' '), 	"
				+ " CASE WHEN LIC_TAX_EXEMPTED='Y' THEN 'Yes' WHEN LIC_TAX_EXEMPTED='N' THEN  'No' END, CASE WHEN LIC_PAID_IN_SALARY='Y' THEN 'Yes' WHEN LIC_PAID_IN_SALARY='N' THEN  'No' END "
				+ " FROM HRMS_LIC WHERE EMP_ID ="
				+ bean.getEmpId()
				+ " ORDER BY LIC_ID";

		try {
			Object objData[][] = getSqlModel().getSingleResult(SQL);

			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - LIC Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0, 0, 0, 0, 0, 0, 0 };
				int[] cellWidth = { 15, 10, 10, 10, 10, 10, 10, 5 };

				String[] objHeader = { "LIC Name", "Policy Number",
						"Insurance Amount", "Monthly Premium", "Start Date",
						"End Date", "Tax Exempetion", "Paid in\nSalary" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getLICDetails:" + e);
		}
	}
/**
 * Modified by Priyanka. Kumbhar
 * @param rg
 * @param bean
 */
	private void getPromotionHistoryDetails(ReportGenerator rg, ServiceBook bean) {
		try {
			//Object[][] objData = null;
			Object[][] objData1 = null;
			// Object[][] objData2 = null;

			/*
			 * String SQL = "SELECT
			 * NVL(RANK_NAME,''),NVL(TO_CHAR(PROMO_FROM_DT,'DD-MM-YYYY'),''), " + "
			 * NVL(PAYSC_NAME,''), " + "
			 * PROMO_PAY_FIXED,PROMO_ORDER_NO,PROMO_SERIAL_NO,PROMO_ID,TO_CHAR(PROMO_DCE_DT,'DD-MM-YYYY')," + "
			 * PROMO_PAY_SCALE,RANK_ID,HRMS_DEPT.DEPT_NAME,PROMO_DEPT,PROMO_CTC" + "
			 * FROM HRMS_PROMO_HISTORY " + " INNER JOIN HRMS_RANK ON
			 * (HRMS_PROMO_HISTORY.PROMO_POST=HRMS_RANK.RANK_ID) " + " LEFT JOIN
			 * HRMS_PAYSCALE ON (HRMS_PROMO_HISTORY.PROMO_PAY_SCALE =
			 * HRMS_PAYSCALE.PAYSC_ID) " + " LEFT JOIN HRMS_DEPT ON
			 * (HRMS_PROMO_HISTORY.PROMO_DEPT=HRMS_DEPT.DEPT_ID) " + " WHERE
			 * EMP_ID = " + bean.getEmpId() + " ORDER BY PROMO_ID";
			 */

			String SQL = "SELECT NVL(RANK_NAME,''),HRMS_DEPT.DEPT_NAME,NVL(TO_CHAR(PROMO_FROM_DT,'DD-MM-YYYY'),''), "
					+ " TO_CHAR(PROMO_DCE_DT,'DD-MM-YYYY'),PROMO_PAY_SCALE,PROMO_CTC "
					+ " FROM HRMS_PROMO_HISTORY "
					+ " INNER JOIN HRMS_RANK ON (HRMS_PROMO_HISTORY.PROMO_POST=HRMS_RANK.RANK_ID) "
					+ " LEFT JOIN HRMS_PAYSCALE ON (HRMS_PROMO_HISTORY.PROMO_PAY_SCALE = HRMS_PAYSCALE.PAYSC_ID) "
					+ " LEFT JOIN HRMS_DEPT ON (HRMS_PROMO_HISTORY.PROMO_DEPT=HRMS_DEPT.DEPT_ID)"
					+ " WHERE EMP_ID ="
					+ bean.getEmpId()
					+ " UNION ALL "
					+ " SELECT HRMS_RANK.RANK_NAME,HRMS_DEPT.DEPT_NAME,"
					+ " TO_CHAR(PROM_DATE,'DD-MM-YYYY'), "
					+ " TO_CHAR(EFFECT_DATE,'DD-MM-YYYY'),OLD_CTC,PROM_GR0SSAMT "
					+ " FROM HRMS_PROMOTION INNER JOIN HRMS_RANK ON "
					+ " (HRMS_PROMOTION.PRO_DESG=HRMS_RANK.RANK_ID) "
					+ " LEFT JOIN HRMS_DEPT ON (HRMS_PROMOTION.PRO_DEPT=HRMS_DEPT.DEPT_ID) "
					+ " LEFT JOIN HRMS_CENTER ON (HRMS_PROMOTION.PRO_BRANCH= HRMS_CENTER.CENTER_ID) "
					+ " LEFT JOIN HRMS_PROMO_HISTORY ON(HRMS_PROMO_HISTORY.EMP_ID=HRMS_PROMOTION.EMP_CODE) "
					+ " WHERE EMP_CODE="
					+ bean.getEmpId()
					+ "  AND LOCK_FLAG='Y' ";

			Object[][] data = getSqlModel().getSingleResult(SQL);

			if (data != null && data.length > 0) {
				objData1 = new Object[data.length][4];
				for (int i = 0; i < data.length; i++) {
					objData1[i][0] = checkNull(String.valueOf(data[i][0]));
					objData1[i][1] = checkNull(String.valueOf(data[i][1]));
					objData1[i][2] = checkNull(String.valueOf(data[i][2]));
					objData1[i][3] = checkNull(String.valueOf(data[i][5]));

				}
			}

			/*
			 * SQL = "SELECT HRMS_RANK.RANK_NAME,HRMS_DEPT.DEPT_NAME," +
			 * "TO_CHAR(PROM_DATE,'DD-MM-YYYY')," +
			 * "TO_CHAR(EFFECT_DATE,'DD-MM-YYYY'),PROM_GR0SSAMT,OLD_CTC" + "
			 * FROM HRMS_PROMOTION INNER JOIN HRMS_RANK ON" +
			 * "(HRMS_PROMOTION.PRO_DESG=HRMS_RANK.RANK_ID) LEFT JOIN HRMS_DEPT
			 * ON " + "(HRMS_PROMOTION.PRO_DEPT=HRMS_DEPT.DEPT_ID) " + " LEFT
			 * JOIN HRMS_CENTER ON (HRMS_PROMOTION.PRO_BRANCH=
			 * HRMS_CENTER.CENTER_ID) " + " WHERE EMP_CODE=" + bean.getEmpId() + "
			 * AND LOCK_FLAG='Y' ORDER BY PROM_CODE";
			 * 
			 * data = getSqlModel().getSingleResult(SQL);
			 * 
			 * if (data != null && data.length > 0) { objData2 = new
			 * Object[data.length][4]; for (int i = 0; i < data.length; i++) {
			 * objData2[i][0] = checkNull(String.valueOf(data[i][0]));
			 * objData2[i][1] = checkNull(String.valueOf(data[i][1]));
			 * objData2[i][2] = checkNull(String.valueOf(data[i][3]));
			 * objData2[i][3] = checkNull(String.valueOf(data[i][4])); } }
			 * 
			 * objData = new Object[objData1.length + objData2.length][4];
			 */
			/*for (int i = 0; i < objData1.length; i++) {

				objData[i][0] = checkNull(String.valueOf(objData1[i][0]));
				objData[i][1] = checkNull(String.valueOf(objData1[i][1]));
				objData[i][2] = checkNull(String.valueOf(objData1[i][2]));
				objData[i][3] = checkNull(String.valueOf(objData1[i][3]));
			}*/
			/*
			 * for (int i = 0; i < objData2.length; i++) {
			 * objData[objData1.length + i][0] =
			 * checkNull(String.valueOf(objData2[i][0]));
			 * objData[objData1.length + i][1] =
			 * checkNull(String.valueOf(objData2[i][1]));
			 * objData[objData1.length + i][2] =
			 * checkNull(String.valueOf(objData2[i][2]));
			 * objData[objData1.length + i][3] =
			 * checkNull(String.valueOf(objData2[i][3])); }
			 */
			if (data != null && data.length > 0) {

				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Promotion Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0, 0, 0 };
				int[] cellWidth = { 20, 15, 15, 15 };
				int[] cellColSpan = { 2, 2, 2, 1 };

				String[] objHeader = { "Designation", "Department",
						"Effective Date", "CTC" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData1);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getTransferDetails:" + e);
			e.printStackTrace();
		}
	}
	
	//Ended By Priyanka

	private void getTransferDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = "SELECT CASE WHEN TRANSFER_TYPE='intra' THEN 'Intra-Departmental' WHEN TRANSFER_TYPE='mut' THEN 'Mutual Transfer' WHEN TRANSFER_TYPE='trf' THEN 'Transfer on Promotion' WHEN TRANSFER_TYPE='ptod' THEN 'Permanent Transfer' WHEN TRANSFER_TYPE='temp' THEN 'Temporary Attachment' END  ,TO_CHAR(TRANSFER_APPLICATION_DATE,'DD-MM-YYYY'),NVL(OLD.CENTER_NAME,' '),"
				+ " NVL(NEW.CENTER_NAME,' '),CASE WHEN TRANSFER_STATUS='P' THEN 'Pending' WHEN TRANSFER_STATUS='A' THEN 'Approved' WHEN TRANSFER_STATUS='R' THEN 'Rejected' ELSE 'Recommended' END,TO_CHAR(TRANSFER_RELIEVING_DATE,'DD-MM-YYYY')"
				+ " ,TRANSFER_LEVEL FROM HRMS_TRANSFER  LEFT JOIN HRMS_CENTER OLD ON(OLD.CENTER_ID=HRMS_TRANSFER.TRANSFER_OLD_CENTER)"
				+ " LEFT JOIN HRMS_CENTER NEW ON(NEW.CENTER_ID=HRMS_TRANSFER.TRANSFER_NEW_CENTER)"
				+ " WHERE EMP_ID=" + bean.getEmpId();

		try {
			Object data[][] = getSqlModel().getSingleResult(SQL);

			if (data != null && data.length > 0) {
				Object[][] objData = new Object[data.length][6];
				for (int i = 0; i < data.length; i++) {
					objData[i][0] = data[i][0];
					objData[i][1] = data[i][1];
					objData[i][2] = data[i][2];
					objData[i][3] = data[i][3];

					if (String.valueOf(data[i][4]).equals("Pending")
							&& !(String.valueOf(data[i][6]).equals("1"))) {
						objData[i][4] = "Forwarded";
					} else {
						objData[i][4] = data[i][4];
					}
					objData[i][5] = data[i][5];
				}

				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Transfer Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0, 0, 0, 0, 0 };
				int[] cellWidth = { 20, 15, 15, 15, 15, 15 };
				int[] cellColSpan = { 2, 1, 1, 1, 1, 1 };

				String[] objHeader = { "Transfer Type",
						"Transfer Application Date", "Old Branch",
						"New Branch", "Transfer Status",
						"Transfer Relieving Date" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setCellColSpan(cellColSpan);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getTransferDetails:" + e);
		}
	}

	private void getTrainingDetails(ReportGenerator rg, ServiceBook bean) {
		String SQL = " SELECT TRAINING_NAME,LOCATION_NAME,TO_CHAR(TRNG_STARTDATE,'DD-MM-YYYY'),TO_CHAR(TRNG_ENDDATE,'DD-MM-YYYY'),  TRNG_INSTITUTE,TRNG_FEES "
				+ " FROM HRMS_EMP_TRNG  INNER JOIN HRMS_TRAINING ON (HRMS_EMP_TRNG .TRNG_MAST_CODE = HRMS_TRAINING.TRAINING_ID) "
				+ " LEFT JOIN HRMS_LOCATION ON (HRMS_EMP_TRNG .TRNG_LOC = HRMS_LOCATION.LOCATION_CODE)  "
				+ " WHERE EMP_ID =" + bean.getEmpId() + " ORDER BY TRNG_ID ";

		try {
			Object objData[][] = getSqlModel().getSingleResult(SQL);

			if (objData != null && objData.length > 0) {
				/**
				 * Set title
				 */
				Object[][] objTitle = new Object[1][1];
				objTitle[0][0] = RPN[RCOUNT] + " - Training Details";

				TableDataSet tdsTitle = new TableDataSet();
				tdsTitle.setData(objTitle);
				tdsTitle.setCellAlignment(new int[] { 1 });
				tdsTitle.setCellWidth(new int[] { 100 });
				tdsTitle.setCellColSpan(new int[] { 7 });
				tdsTitle.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
						new Color(0, 0, 0));
				tdsTitle.setBorder(true);
				rg.addTableToDoc(tdsTitle);

				/**
				 * Set data
				 */
				int[] cellAlignment = { 0, 0, 0, 0, 0, 0 };
				int[] cellWidth = { 20, 20, 10, 10, 20, 10 };

				String[] objHeader = { "Training\nType", "Training\nLocation",
						"Start Date", "End Date", "Training\nInstitute", "Fees" };

				TableDataSet tdsHeaderAndData = new TableDataSet();
				tdsHeaderAndData.setHeader(objHeader);
				tdsHeaderAndData.setData(objData);
				tdsHeaderAndData.setCellAlignment(cellAlignment);
				tdsHeaderAndData.setCellWidth(cellWidth);
				tdsHeaderAndData.setBorder(true);
				tdsHeaderAndData.setBlankRowsBelow(2);
				rg.addTableToDoc(tdsHeaderAndData);

				RCOUNT++;
			}
		} catch (Exception e) {
			logger.error("Exception in getTrainingDetails:" + e);
		}
	}

	/*
	 * private void onOpenDocument(PdfWriter writer, Document document) { try {
	 * PdfPTable pTable = null; Image headerImage = null;
	 * 
	 * pTable = new PdfPTable(2); // String img=
	 * context.getRealPath("\\")+"pages\\images\\logo.jpg"; String img =
	 * "C:\\a.jpg";
	 * 
	 * headerImage = Image.getInstance(img); pTable.addCell(new Phrase(new
	 * Chunk(headerImage, 0, 0))); pTable.addCell(new Phrase("hi"));
	 * 
	 * Phrase p = new Phrase(); Chunk ck = new Chunk("Financial Assistance to :
	 * Name of Borrower", new Font(Font.TIMES_ROMAN, 12, Font.BOLDITALIC));
	 * p.add(ck); pTable.getDefaultCell().setBorderWidth(0); pTable.addCell(p);
	 * pTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	 * Chunk test = new Chunk("Test \n", new Font(Font.TIMES_ROMAN, 16,
	 * Font.BOLDITALIC, Color.blue)); // initialization of the Graphic State
	 * PdfGState gstate = new PdfGState(); gstate.setFillOpacity(0.3f);
	 * gstate.setStrokeOpacity(0.3f); // initialization of the template
	 * PdfTemplate tpl = writer.getDirectContent().createTemplate(100, 100);
	 * tpl.setBoundingBox(new Rectangle(-20, -20, 100, 100)); // initialization
	 * of the font BaseFont helv = BaseFont.createFont("Helvetica",
	 * BaseFont.WINANSI, false); } catch(Exception e) { throw new
	 * ExceptionConverter(e); } }
	 */

}