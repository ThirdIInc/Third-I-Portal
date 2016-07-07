/**
 * 
 */
package org.paradyne.model.loan;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Loan.LoanApplication;
import org.paradyne.bean.Loan.LoanProcessing;
import org.paradyne.bean.conference.ConferenceBooking;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.common.ApplCodeTemplateModel;

/**
 * @author tarunc
 * @date Oct 23, 2008
 * @description LoanApplicationModel class serves as model for loan application
 *              form to write business logic to save, update and delete the loan
 *              application
 */
public class LoanApplicationModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LoanApplicationModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");

	/**
	 * @method getEmployeeDetails
	 * @purpose To retrieve selected employee details from HRMS_EMP_OFFC table
	 * @param bean
	 *            to pop up all the form field values
	 */
	public void getEmployeeDetails(LoanApplication bean) {
		logger.info("in getEmployeeDetailsAction method model");

		Object[] employeeCode = new Object[1];

		if (bean.isGeneralFlag()) {
			employeeCode[0] = bean.getUserEmpId(); // if generalFlag is true
			// then take user id
		} // end of if
		else {
			employeeCode[0] = bean.getEmpCode(); // else take the selected
			// employee id
		} // end of else

		/*
		 * execute select query to retrieve employee data from hrms_emp_offc
		 */
		Object[][] getEmployeeData = getSqlModel().getSingleResult(getQuery(1),
				employeeCode);

		/*
		 * if data retrieved successfully then set the data to the related form
		 * fields
		 */
		if (getEmployeeData != null && getEmployeeData.length != 0) {
			bean.setBranchCode(String.valueOf(getEmployeeData[0][0]));
			bean.setBranchName(String.valueOf(getEmployeeData[0][1]));
			bean.setDeptCode(String.valueOf(getEmployeeData[0][2]));
			bean.setDeptName(String.valueOf(getEmployeeData[0][3]));
			bean.setDesgCode(String.valueOf(getEmployeeData[0][4]));
			bean.setDesgName(String.valueOf(getEmployeeData[0][5]));
			bean.setConfirmationDate(String.valueOf(getEmployeeData[0][6]));
			bean.setGradeCode(String.valueOf(getEmployeeData[0][7]));
			bean.setGrade(String.valueOf(getEmployeeData[0][8]));

			if (bean.isGeneralFlag()) {
				bean.setEmpToken(String.valueOf(getEmployeeData[0][9]));
				bean.setEmpName(String.valueOf(getEmployeeData[0][10]));
				bean.setEmpCode(String.valueOf(getEmployeeData[0][11]));
			} // end of if

			setLoanList(bean, String.valueOf(getEmployeeData[0][12]));
			
			bean.setDivCode(String.valueOf(getEmployeeData[0][13]));
			bean.setDivName(String.valueOf(getEmployeeData[0][14]));
			
			
			String dateQuery = "SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual ";
			Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
			if (dateObj != null && dateObj.length > 0) {
				bean.setInitiatorDate(String.valueOf(dateObj[0][0]));
			}

			String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
					+ "	INITIATOR.EMP_TOKEN||' - '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
					+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID ="
					+ bean.getUserEmpId();

			Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
			if (initiatorObj != null && initiatorObj.length > 0) {
				bean.setInitiatorCode(bean.getUserEmpId());
				bean.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
				bean.setInitiatorName(String.valueOf(initiatorObj[0][1]));
			}
			
			

		}  else {
			
			String dateQuery = "SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual ";
			Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
			if (dateObj != null && dateObj.length > 0) {
				bean.setInitiatorDate(String.valueOf(dateObj[0][0]));
			}

			String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
					+ "	INITIATOR.EMP_TOKEN||' - '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
					+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID ="
					+ bean.getUserEmpId();

			Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
			if (initiatorObj != null && initiatorObj.length > 0) {
				bean.setInitiatorCode(bean.getUserEmpId());
				bean.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
				bean.setInitiatorName(String.valueOf(initiatorObj[0][1]));
			}
			
		}

		/*
		 * execute select query to retrieve gross salary of the selected
		 * employee from HRMS_EMP_CREDIT
		 */
		Object[][] getGrossSalary = getSqlModel().getSingleResult(getQuery(6),
				employeeCode);

		/*
		 * if data retrieved successfully then set the data to the related form
		 * fields
		 */
		if (getGrossSalary != null && getGrossSalary.length != 0) {
			bean.setGrossSalary(String.valueOf(getGrossSalary[0][0]));
		} // end of if
		if (checkNull(String.valueOf(getEmployeeData[0][12])).equals("Y")) {
			bean.setPfBalance(calPfBalance(bean));
		} else {
			bean.setPfBalance("");
		}
	}

	public boolean setLoanList(LoanApplication bean, String type) {
		boolean result = false;
		logger.info("type==" + type);
		String quer = "SELECT LOAN_CODE,LOAN_NAME FROM HRMS_LOAN_MASTER ";
		if (type.equals("N")) {
			String loanivode="0";
			String divQuery = "SELECT LOAN_DIV_CODE,LOAN_CODE FROM HRMS_LOAN_MASTER WHERE LOAN_DIV_CODE IS NOT NULL ";
			Object[][] obj = getSqlModel().getSingleResult(divQuery);
			for (int i = 0; i < obj.length; i++) {
				String aa=String.valueOf(obj[i][0]);
				String[]str=aa.split(",");
				if(str!=null && str.length>0){
					for (int j = 0; j < str.length; j++) {
						if(String.valueOf(str[j]).equals(bean.getDivCode())){
							loanivode+=","+String.valueOf(obj[i][1]);
							break;
						}
					}
				}
			}
			
			quer += " WHERE LOAN_CODE NOT IN(SELECT PFT_LOAN_CODE FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE)"
					+ " FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE )) AND LOAN_CODE IN ( "+loanivode+" ) ";
		}
		quer += "  ORDER BY UPPER(LOAN_NAME)";
		Object[][] iterator = getSqlModel().getSingleResult(quer);
		HashMap mp = new HashMap();
		
		if(iterator!=null && iterator.length > 0){
			try {
				logger.info("iterator.length==" + iterator.length);
				for (int i = 0; i < iterator.length; i++) {
					mp.put(String.valueOf(iterator[i][0]), String
							.valueOf(iterator[i][1]));

				}
				mp = (HashMap<Object, Object>) org.paradyne.lib.Utility
						.sortMapByValue(mp, null, true);
				bean.setLoanTypeHashmap(mp);
				
				 result =true ;
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
		}
		else{
			mp.put("","");
			bean.setLoanTypeHashmap(mp);

		}
		 
		
		
		return result ;
	}

	/**
	 * @method setApplicationDate
	 * @purpose To set the application date to current date
	 * @param bean
	 *            to pop up all the form field values
	 */
	public void setApplicationDate(LoanApplication bean) {
		logger.info("in setApplicationDate method model");

		String dateQuery = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL"; // query
		// to
		// select
		// sysdate
		Object[][] date = getSqlModel().getSingleResult(dateQuery); // execute
		// select
		// query to
		// get
		// sysdate

		bean.setApplicationdate(String.valueOf(date[0][0])); // set the value
		// of sysdate to
		// application
		// date field
	}

	/**
	 * @method saveLoanApplication
	 * @purpose to insert the loan application details in HRMS_LOAN_APPLICATION
	 *          table
	 * @param bean
	 *            to pop up all the form field values
	 * @param empFlow
	 *            to get the approver code for the selected employee
	 * @return boolean
	 */
	public boolean saveLoanApplication(LoanApplication bean, Object[][] empFlow) {
		logger.info("in saveLoanApplication method model");

		/*
		 * get the form data in saveApplicationData object array
		 */
		Object maxCode[][] = getSqlModel()
				.getSingleResult(
						"SELECT NVL(MAX(LOAN_APPL_CODE),0) +1 FROM HRMS_LOAN_APPLICATION");

		Object[][] saveApplicationData = new Object[1][14];
		saveApplicationData[0][0] = checkNull(String.valueOf(maxCode[0][0])); // next
		// loan
		// application
		// code
		saveApplicationData[0][1] = bean.getEmpCode(); // emp code
		saveApplicationData[0][2] = bean.getLoanCode(); // loan type code
		saveApplicationData[0][3] = bean.getApplicationdate(); // application
		// date
		saveApplicationData[0][4] = bean.getLoanAmount(); // loan amount
		saveApplicationData[0][5] = bean.getHiddenApplicationStatus();// application
		// status
		saveApplicationData[0][6] = bean.getRecommendedByCode(); // emp code
		// of
		// recommended
		// by person
		saveApplicationData[0][7] = bean.getFirstGuarantorCode(); // emp code
		// of first
		// guarantaor
		saveApplicationData[0][8] = bean.getSecondGuarantorCode(); // emp code
		// of second
		// guarantor
		saveApplicationData[0][9] = empFlow[0][0]; // approver code from
		// empFlow
		saveApplicationData[0][12] = empFlow[0][3]; // ALTERNATE approver code
		// from empFlow
		saveApplicationData[0][13] = bean.getApplicantComment(); // ALTERNATE
		// approver
		// code from
		// empFlow

	

		// saveApplicationData [0][9] = 1;
		if (bean.getPfLoanCode().equals(bean.getLoanCode())) {
			saveApplicationData[0][10] = bean.getLoanSubType(); // if refundable
			// or
			// not(applicable
			// only for PF
			// loan)
			saveApplicationData[0][11] = bean.getLoanPurpose(); // Loan purpose
			// (applicable
			// only for PF
			// loan)
		} else {
			saveApplicationData[0][10] = "Y";
			saveApplicationData[0][11] = "";
		}
		/*
		 * execute insert query to insert data in hrms_loan_application
		 */
	
		boolean result = getSqlModel().singleExecute(getQuery(2),
				saveApplicationData);
		if (result) {
			bean.setLoanApplCode(checkNull(String.valueOf(maxCode[0][0])));
		}

		/*
		 * if(result){ try { Object [][] fromMailIds = {{bean.getEmpCode()}};
		 * Object [][] toMailIds = {{empFlow[0][0]}};
		 * 
		 * MailUtility mail=new MailUtility(); mail.initiate(context, session);
		 * mail.sendMail(toMailIds, fromMailIds,"Loan", "", "P");
		 * 
		 * mail.terminate(); } catch (Exception e) { // TODO: handle exception } }
		 * //end of if
		 */return result;
	}

	/**
	 * @method updateLoanApplication
	 * @purpose to update the selected application details in
	 *          HRMS_LOAN_APPLICATION table
	 * @param bean
	 *            to pop up all the form field values
	 * @param empFlow
	 *            to get the approver code for the selected employee
	 * @return boolean
	 */
	public boolean updateLoanApplication(LoanApplication bean,
			Object[][] empFlow) {
	

		/*
		 * get the form data in updateApplicationData object array
		 */
		Object[][] updateApplicationData = new Object[1][16];
		updateApplicationData[0][0] = bean.getEmpCode(); // emp code
		updateApplicationData[0][1] = bean.getLoanCode(); // loan type code
		updateApplicationData[0][2] = bean.getApplicationdate(); // application
		// date
		updateApplicationData[0][3] = bean.getLoanAmount(); // loan amount
		updateApplicationData[0][4] = bean.getHiddenApplicationStatus();// application
		// status
		updateApplicationData[0][5] = bean.getRecommendedByCode(); // emp code
		// of
		// recommended
		// by person
		updateApplicationData[0][6] = bean.getFirstGuarantorCode(); // emp code
		// of first
		// guarantor
		updateApplicationData[0][7] = bean.getSecondGuarantorCode(); // emp
		// code
		// of
		// second
		// guarantor
		updateApplicationData[0][8] = empFlow[0][0]; // approver code from
		// empFlow
		updateApplicationData[0][11] = empFlow[0][3]; // alternate approver
		// code from empFlow
		updateApplicationData[0][12] = bean.getApplicantComment(); // applicant
		// comment

		if (bean.getPfLoanCode().equals(bean.getLoanCode())) {
			updateApplicationData[0][9] = bean.getLoanSubType(); // if
			// refundable
			// or
			// not(applicable
			// only for
			// PF loan)
			updateApplicationData[0][10] = bean.getLoanPurpose(); // Loan
			// purpose
			// (applicable
			// only for
			// PF loan)
		} else {
			updateApplicationData[0][9] = "Y"; // if refundable or
			// not(applicable only for PF
			// loan)
			updateApplicationData[0][10] = ""; // Loan purpose (applicable only
			// for PF loan)
		}

		if (bean.getHiddenChechfrmId().equals("E")) {
			updateApplicationData[0][13] = "E";
		} else if (bean.getHiddenChechfrmId().equals("T")) {
			updateApplicationData[0][13] = "T";
		} else if (bean.getHiddenChechfrmId().equals("")) {
			updateApplicationData[0][13] = "";
		}

		updateApplicationData[0][14] = bean.getInitiatorCode(); // initiator
		// code

		updateApplicationData[0][15] = bean.getLoanApplCode(); // application
		// code
		/*
		 * execute update query to update application data in
		 * hrms_loan_application
		 */
	
		boolean result = getSqlModel().singleExecute(getQuery(4),
				updateApplicationData);
		return result;
	}

	/**
	 * @method showApplicationRecord
	 * @purpose to retrieve the selected application details from
	 *          HRMS_LOAN_APPLICATION and display them on the respective form
	 *          fields
	 * @param bean
	 *            to pop up all the form field values
	 */
	public void showApplicationRecord(LoanApplication bean) {
		logger.info("in showApplicationRecord method model");

		/*
		 * get the application code in loanApplicationCode object array
		 */
		Object[] loanApplicationCode = new Object[1];
		loanApplicationCode[0] = bean.getLoanApplCode();
		/*
		 * execute the select query to retrieve application data from hrms_loan
		 * _application in applicationData object array
		 */
		Object[][] applicationData = getSqlModel().getSingleResult(getQuery(3),
				loanApplicationCode);

		/*
		 * if data is present then set the data to the related form fields
		 */
		if (applicationData != null && applicationData.length != 0) {
			bean.setBranchCode(String.valueOf(applicationData[0][0]));
			bean.setBranchName(String.valueOf(applicationData[0][1]));
			bean.setDeptCode(String.valueOf(applicationData[0][2]));
			bean.setDeptName(String.valueOf(applicationData[0][3]));
			bean.setDesgCode(String.valueOf(applicationData[0][4]));
			bean.setDesgName(String.valueOf(applicationData[0][5]));
			bean.setConfirmationDate(String.valueOf(applicationData[0][6]));
			bean.setGradeCode(String.valueOf(applicationData[0][7]));
			bean.setGrade(String.valueOf(applicationData[0][8]));
			bean.setLoanCode(String.valueOf(applicationData[0][10]));
			bean.setLoanType(String.valueOf(applicationData[0][11]));
			bean.setLoanAmount(String.valueOf(applicationData[0][12]));
			bean.setRecommendedByCode(checkNull(String
					.valueOf(applicationData[0][13])));
			bean.setRecommendedByToken(checkNull(String
					.valueOf(applicationData[0][14])));
			bean.setRecommendedByName(checkNull(String
					.valueOf(applicationData[0][15])));
			bean.setFirstGuarantorCode(checkNull(String
					.valueOf(applicationData[0][16])));
			bean.setFirstGuarantorToken(checkNull(String
					.valueOf(applicationData[0][17])));
			bean.setFirstGuarantorName(checkNull(String
					.valueOf(applicationData[0][18])));
			bean.setSecondGuarantorCode(checkNull(String
					.valueOf(applicationData[0][19])));
			bean.setSecondGuarantorToken(checkNull(String
					.valueOf(applicationData[0][20])));
			bean.setSecondGuarantorName(checkNull(String
					.valueOf(applicationData[0][21])));
			bean.setLevel(String.valueOf(applicationData[0][22]));
			bean.setApplicationdate(String.valueOf(applicationData[0][23]));
			bean.setLoanSubType(checkNull(String
					.valueOf(applicationData[0][24])));
			bean.setHiddenLoanSubType(checkNull(bean.getLoanSubType()));
			bean.setLoanPurpose(checkNull(String
					.valueOf(applicationData[0][25])));
			bean.setApplicantComment(String.valueOf(applicationData[0][27]));// Applicant
			// comment

			
			if (String.valueOf(applicationData[0][28]).equals("E")) {
				bean.setExpectedEmi("true");
				bean.setHiddenChechfrmId("E");
			}

			if (String.valueOf(applicationData[0][28]).equals("T")) {
				bean.setTenure("true");
				bean.setHiddenChechfrmId("E");
			}

			bean.setInitiatorCode(checkNull(String
					.valueOf(applicationData[0][29])));
			bean.setInitiatorName(checkNull(String
					.valueOf(applicationData[0][30])));
			bean.setInitiatorDate(checkNull(String
					.valueOf(applicationData[0][31])));
			
			bean.setTrackingNo(checkNull(String
					.valueOf(applicationData[0][32])));
			bean.setDivCode(checkNull(String
					.valueOf(applicationData[0][33])));
			bean.setDivName(checkNull(String
					.valueOf(applicationData[0][34])));
			

			/*
			 * if application level is not equal to 1 and application status is
			 * still pending then set application status to F i.e. Forwarded
			 */
			if (!(String.valueOf(applicationData[0][22]).equals("1"))
					&& (String.valueOf(applicationData[0][9]).equals("P"))) {
				bean.setApplicationStatus("F");
			} // end of if
			else { // otherwise set it to selected value
				bean
						.setApplicationStatus(String
								.valueOf(applicationData[0][9]));
			} // end of else
		} // end of if

		/*
		 * execute select query to retrieve gross salary of the employee from
		 * HRMS_EMP_CREDIT
		 */
		Object[][] getGrossSalary = getSqlModel().getSingleResult(getQuery(6),
				new Object[] { bean.getEmpCode() });

		/*
		 * if data retrieved successfully then set the data to the related form
		 * fields
		 */
		if (getGrossSalary != null && getGrossSalary.length != 0) {
			bean.setGrossSalary(String.valueOf(getGrossSalary[0][0]));
		} // end of if
		try {
			if (checkNull(String.valueOf(applicationData[0][26])).equals("Y")) {
				bean.setPfBalance(calPfBalance(bean));
				Object[][] purposeObj = getSqlModel()
						.getSingleResult(
								"SELECT PFT_PURPOSE, PFT_ELIGIBILITY FROM HRMS_PFTRUST_LOAN_PURS WHERE  PFT_PURPOSE_CODE="
										+ bean.getLoanPurpose());
				if (purposeObj != null || purposeObj.length > 0) {
					bean.setLoanPurposeValue(String.valueOf(purposeObj[0][0]));
					bean.setLoanEligibility(String.valueOf(purposeObj[0][1]));
				}

			} else {
				bean.setPfBalance("");
			}
		} catch (Exception e) {

		}
		setLoanList(bean, String.valueOf(applicationData[0][26]));

	}

	/**
	 * @method deleteApplication
	 * @purpose to delete the selected application details from
	 *          HRMS_LOAN_APPLICATION table
	 * @param bean
	 *            to pop up all the form field values
	 * @return boolean
	 */
	public boolean deleteApplication(LoanApplication bean) {
		logger.info("in deleteApplication method model");

		/*
		 * get the application code in applicationCode object array
		 */
		Object[][] applicationCode = new Object[1][1];
		applicationCode[0][0] = bean.getHiddenCode();

		/*
		 * execute the delete query to delete application data from hrms_loan
		 * _application
		 */
		boolean result = getSqlModel().singleExecute(getQuery(5),
				applicationCode);

		/*
		 * if(result){ try { Object [][] fromMailIds = {{bean.getEmpCode()}};
		 * Object [][] toMailIds = {{bean.getApproverCode()}};
		 * 
		 * MailUtility mail=new MailUtility(); mail.initiate(context, session);
		 * mail.sendMail(toMailIds, fromMailIds,"Loan", "", "D");
		 * 
		 * mail.terminate(); } catch (Exception e) { // TODO: handle exception } }
		 * //end of if
		 */return result;
	}

	/**
	 * @method getApplicationDetailsForApproval
	 * @purpose to retrieve all the details from HRMS_LOAN_APPLICATION table for
	 *          the application selected from Loan Approval form and display
	 *          them on the respective form fields
	 * @param bean
	 *            to pop up all the form field values
	 * @param request
	 */
	public void getApplicationDetailsForApproval(LoanApplication bean,
			HttpServletRequest request) {
		logger.info("in getApplicationDetailsForApproval method model");

		/*
		 * query to select employee id, employee token and employee name from
		 * hrms_emp_offc
		 */
		String query = "SELECT HRMS_EMP_OFFC.EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME "
				+ "FROM HRMS_EMP_OFFC "
				+ "WHERE HRMS_EMP_OFFC.EMP_ID = "
				+ bean.getEmpCode();

		/*
		 * execute select query and retrieve data in employeeDetails object
		 * array
		 */
		Object[][] employeeDetails = getSqlModel().getSingleResult(query);

		/*
		 * if data is present then set it to the related form fields
		 */
		if (employeeDetails != null && employeeDetails.length != 0) {
			bean.setEmpCode(String.valueOf(employeeDetails[0][0]));
			bean.setEmpToken(String.valueOf(employeeDetails[0][1]));
			bean.setEmpName(String.valueOf(employeeDetails[0][2]));

			/*
			 * execute select query to retrieve gross salary of the employee
			 * from HRMS_EMP_CREDIT
			 */
			Object[][] getGrossSalary = getSqlModel().getSingleResult(
					getQuery(6), new Object[] { bean.getEmpCode() });

			/*
			 * if data retrieved successfully then set the data to the related
			 * form fields
			 */
			if (getGrossSalary != null && getGrossSalary.length != 0) {
				bean.setGrossSalary(String.valueOf(getGrossSalary[0][0]));
			} // end of if

			showApplicationRecord(bean); // call showApplicationRecord method
			// to retrieve complete details of
			// selected employee

			/*
			 * if application level is equal to 1 and application status is
			 * pending then set approverCommentFlag to true
			 */
			if (bean.getLevel().equals("1")
					&& bean.getApplicationStatus().equals("P")) {
				logger.info("level is equal to one  ");
				bean.setAppCommentFlag("false");
			} // end of if
			else {
				logger.info("level is not equal to one  ");
				ArrayList<Object> commentList = new ArrayList<Object>();

				/*
				 * select query to select approver comment data from
				 * hrms_loan_path
				 */
				String commentQuery = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "
						+ "TO_CHAR(LOAN_APPROVED_DATE, 'DD-MM-YYYY'), DECODE(LOAN_APPL_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected') STATUS, "
						+ "NVL(LOAN_COMMENTS, ' ') "
						+ "FROM HRMS_LOAN_PATH "
						+ "INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LOAN_PATH.LOAN_APPROVER_CODE) "
						+ "WHERE LOAN_APPL_CODE = "
						+ bean.getLoanApplCode()
						+ " ORDER BY LOAN_PATH_ID";

				/*
				 * execute select query and set the comment data to the related
				 * form fields
				 */
				Object[][] comment = getSqlModel()
						.getSingleResult(commentQuery);

				if (comment != null && comment.length != 0) {
					logger.info("comment.length  " + comment.length);

					for (int i = 0; i < comment.length; i++) {
						ConferenceBooking bean1 = new ConferenceBooking();
						bean1.setApproverName(String.valueOf(comment[i][0]));
						bean1.setApprovedDate(String.valueOf(comment[i][1]));
						bean1.setApprovedStatus(String.valueOf(comment[i][2]));
						bean1.setApproverComment(String.valueOf(comment[i][3]));

						commentList.add(bean1);
					} // end of for loop

					bean.setCommentList(commentList);
					bean.setAppCommentFlag("true");
				} // end of if
			} // end of else
		} // end of if
	}

	/**
	 * @method report
	 * @purpose to generate report for the selected application
	 * @param bean
	 *            to pop up all the form field values
	 * @param response
	 */
	public void report(LoanApplication bean, HttpServletResponse response) {
		logger.info("inside model generateApplicationReport");

		String type = "Pdf"; // type of the report
		String title = "Loan Application Report"; // title of the report

		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,
				title);
		rg = generateApplicationReport(rg, bean);
		rg.createReport(response);
	}

	/**
	 * @method generateApplicationReport
	 * @purpose to configure the report parameters and create a pdf file for
	 *          report
	 * @param rg
	 * @param bean
	 *            to pop up all the form field values
	 * @return rg
	 */
	public ReportGenerator generateApplicationReport(ReportGenerator rg,
			LoanApplication bean) {
		String status = "";
		Object[][] heading = new Object[1][1];
		int[] cells = { 25 };
		int[] align = { 0 };

		/*
		 * get the application code in param object array
		 */
		Object[] param = new Object[1];
		param[0] = bean.getLoanApplCode();
		logger.info("Conf_Code " + bean.getLoanApplCode());

		/*
		 * query to select employee id, employee token and employee name from
		 * hrms_emp_offc
		 */
		String query = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME "
				+ "FROM HRMS_EMP_OFFC "
				+ "WHERE HRMS_EMP_OFFC.EMP_ID = "
				+ bean.getEmpCode();

		/*
		 * execute select query and retrieve data in employeeDetails object
		 * array
		 */
		Object[][] employeeDetails = getSqlModel().getSingleResult(query);

		/*
		 * execute select query and retrieve application data in
		 * getApplicationData object array
		 */
		Object[][] getApplicationData = getSqlModel().getSingleResult(
				getQuery(3), param);
		logger.info("result.length " + getApplicationData.length);

		/*
		 * set status Pending, Approved, Rejected according to its value i.e. P,
		 * A or R
		 */
		if (String.valueOf(getApplicationData[0][9]).equals("P")) {
			status = "Pending";
		} // end of if
		else if (String.valueOf(getApplicationData[0][9]).equals("A")) {
			status = "Approved";
		} // end of elseif
		else if (String.valueOf(getApplicationData[0][9]).equals("R")) {
			status = "Rejected";
		} // end of else

		String query1 = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL"; // query
		// to
		// select
		// sysdate
		Object[][] today = getSqlModel().getSingleResult(query1); // execute
		// select
		// query to
		// get
		// sysdate
		String date = "Date : " + (String) today[0][0];

		Object[][] employeeName = new Object[1][1];
		Object[][] loanApplicationData = new Object[7][2];
		Object[][] pfloanData = new Object[1][2];

		/*
		 * execute select query to retrieve gross salary of the employee from
		 * HRMS_EMP_CREDIT
		 */
		Object[][] getGrossSalary = getSqlModel().getSingleResult(getQuery(6),
				new Object[] { bean.getEmpCode() });

		/*
		 * if data retrieved successfully then set the data to the related form
		 * fields
		 */
		if (getGrossSalary != null && getGrossSalary.length != 0) {
			loanApplicationData[2][1] = String.valueOf(" Gross Salary :")
					+ "  " + checkNull(String.valueOf(getGrossSalary[0][0]));
		} // end of if

		/*
		 * store the application report data in employeeName and
		 * loanApplicationData array
		 */
		employeeName[0][0] = String.valueOf(" Employee Name :") + "  "
				+ checkNull(String.valueOf(employeeDetails[0][1]));

		loanApplicationData[0][0] = String.valueOf(" Branch : ") + "  "
				+ checkNull(String.valueOf(getApplicationData[0][1]));
		loanApplicationData[0][1] = String.valueOf(" Department : ") + "  "
				+ checkNull(String.valueOf(getApplicationData[0][3]));
		loanApplicationData[1][0] = String.valueOf(" Designation :") + "  "
				+ checkNull(String.valueOf(getApplicationData[0][5]));
		loanApplicationData[1][1] = String.valueOf(" Joining Date :") + "  "
				+ checkNull(String.valueOf(getApplicationData[0][6]));
		loanApplicationData[2][0] = String.valueOf(" Grade :") + "  "
				+ checkNull(String.valueOf(getApplicationData[0][8]));
		loanApplicationData[3][0] = String.valueOf(" Loan Type : ") + "  "
				+ checkNull(String.valueOf(getApplicationData[0][11]));
		loanApplicationData[3][1] = String.valueOf(" Application Date :")
				+ "  " + checkNull(String.valueOf(getApplicationData[0][23]));
		loanApplicationData[4][0] = String.valueOf(" Loan Amount :") + "  "
				+ checkNull(String.valueOf(getApplicationData[0][12]));
		loanApplicationData[4][1] = String.valueOf(" Status :") + "  "
				+ checkNull(status);
		loanApplicationData[5][0] = String.valueOf(" Recommended By :") + "  "
				+ checkNull(String.valueOf(getApplicationData[0][15]));
		loanApplicationData[5][1] = String.valueOf(" First Guarantor :") + "  "
				+ checkNull(String.valueOf(getApplicationData[0][18]));
		loanApplicationData[6][0] = String.valueOf(" Second Guarantor :")
				+ "  " + checkNull(String.valueOf(getApplicationData[0][21]));
		if (bean.getPfLoanCode().equals(bean.getLoanCode())) {
			pfloanData[0][0] = String.valueOf(" Loan Purpose :") + "  "
					+ checkNull(bean.getLoanPurposeValue());
			if (bean.getHiddenLoanSubType().equals("Y")) {
				pfloanData[0][1] = String.valueOf(" Loan Refundable : YES");
			} else
				pfloanData[0][1] = String.valueOf(" Loan Refundable : NO");

		}
		/*
		 * Header of the report
		 */
		String header = " Loan Application Report ";

		/*
		 * select query to select approver comment data from hrms_loan_path
		 */
		String commentQuery = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "
				+ "TO_CHAR(LOAN_APPROVED_DATE, 'DD-MM-YYYY'), DECODE(LOAN_APPL_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected') STATUS, "
				+ "NVL(LOAN_COMMENTS, ' ') "
				+ "FROM HRMS_LOAN_PATH "
				+ "INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LOAN_PATH.LOAN_APPROVER_CODE) "
				+ "WHERE LOAN_APPL_CODE = "
				+ bean.getLoanApplCode()
				+ " ORDER BY LOAN_PATH_ID";

		/*
		 * execute select query and set the comment data
		 */
		Object[][] comment = getSqlModel().getSingleResult(commentQuery);

		try {
			int[] rowCellwidthEmp = { 100 };
			int[] rowAlignmnetEmp = { 0 };

			int[] rowCellwidthApp = { 50, 50 };
			int[] rowAlignmnetApp = { 0, 0 };

			rg.addFormatedText(header, 6, 0, 1, 0); // print report header in
			// the report
			rg.addText(date, 0, 2, 0);
			// rg.addText("\n", 0, 0, 0);

			heading[0][0] = "Report Details :-";
			rg.tableBodyBold(heading, cells, align);

			// rg.addFormatedText("Report Details :-", 4, 0, 0, 0);
			// rg.addText("\n", 0, 0, 0);
			rg.tableBody(employeeName, rowCellwidthEmp, rowAlignmnetEmp); // print
			// employee
			// name
			rg.tableBody(loanApplicationData, rowCellwidthApp, rowAlignmnetApp);// print
			// application
			// data
			if (bean.getPfLoanCode().equals(bean.getLoanCode())) {
				rg.tableBody(pfloanData, rowCellwidthApp, rowAlignmnetApp);// print
				// pf
				// data
			}
			rg.addText("\n", 0, 0, 0);

			/*
			 * store the comment data in approverComment array
			 */
			if (comment != null && comment.length != 0) {
				logger.info("comment.length  " + comment.length);
				Object[][] approverComment = new Object[comment.length][5];
				for (int i = 0; i < comment.length; i++) {
					approverComment[i][0] = i + 1;
					approverComment[i][1] = checkNull(String
							.valueOf(comment[i][0]));
					approverComment[i][2] = checkNull(String
							.valueOf(comment[i][1]));
					approverComment[i][3] = checkNull(String
							.valueOf(comment[i][2]));
					approverComment[i][4] = checkNull(String
							.valueOf(comment[i][3]));
				} // end of for loop

				/*
				 * set the comment table's column names
				 */
				String approverColNames[] = { "Sr. No", "Approver Name",
						"Approved Date", "Status", "Comments" };
				int[] approvercellwidth = { 8, 30, 15, 10, 30 };
				int[] approveralignmnet = { 1, 0, 1, 0, 0 };

				heading[0][0] = "Approver Details :-";
				rg.tableBodyBold(heading, cells, align);

				// rg.addFormatedText("Approver Details :-",4,0,0,0);
				rg.tableBody(approverColNames, approverComment,
						approvercellwidth, approveralignmnet); // print comment
				// data
			} // end of if

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e);
		}
		return rg;
	}

	public void setPfDetails(LoanApplication bean) {

		String query = "SELECT PFT_LOAN_CODE,PFT_CODE,PFT_LOAN_MINLIMIT, PFT_LOAN_MAXLIMIT FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE "
				+ " ORDER BY PFT_EFFECTIVE_DATE DESC";

		Object[][] pfObj = getSqlModel().getSingleResult(query);
		try {
			if (pfObj != null || pfObj.length > 0) {

				bean.setPfLoanCode(String.valueOf(pfObj[0][0]));
				bean.setMinSanctionLimit(String.valueOf(pfObj[0][2]));
				bean.setMaxSanctionLimit(String.valueOf(pfObj[0][3]));
			} else {
				bean.setPfLoanCode("0");
				bean.setMinSanctionLimit("0");
				bean.setMaxSanctionLimit("0");
			}
		} catch (Exception e) {
			bean.setPfLoanCode("0");
			bean.setMinSanctionLimit("0");
			bean.setMaxSanctionLimit("0");
		}

	}

	public String calPfBalance(LoanApplication bean) {
		String pfBalance = "";
		String frmYear = "";
		double openingBalance = 0.0;
		double pfSubAmt = 0.0;
		double pfRefundAmt = 0.0;
		double pfLoanAmt = 0.0;
		int noOfMonth = 0;
		int year = 0;

		String date[] = bean.getApplicationdate().split("-");

		if (Integer.parseInt(date[1]) > 3) {
			frmYear = String.valueOf(Integer.parseInt(date[2]) - 1);
			noOfMonth = Integer.parseInt(date[1]) - 3;
			year = Integer.parseInt(date[2]);
		} else {
			frmYear = String.valueOf(Integer.parseInt(date[2]) - 2);
			noOfMonth = Integer.parseInt(date[1]) + 9;
			year = Integer.parseInt(date[2]) - 1;
		}
		String opnBalanceQuery = "SELECT NVL(PF_CLOSING_BAL,'0') FROM HRMS_PF_LEDGER WHERE PF_EMPID="
				+ bean.getEmpCode() + " AND " + " PF_FROM_YEAR =" + frmYear;

		Object[][] opnBalanceObj = getSqlModel().getSingleResult(
				opnBalanceQuery);

		try {
			openingBalance = Double.parseDouble(String
					.valueOf(opnBalanceObj[0][0]));
		} catch (Exception e) {
			// TODO: handle exception
		}
		String pfSubQuery = "";
		String pfRefundQuery = "";
		String pfLoanQuery = "";
		int month = 0;
		for (int i = 0; i < noOfMonth; i++) {
			month = (4 + i) % 12;
			if (month == 0)
				month = 12;
			if (month == 1) {
				year += 1;
			}
			pfSubQuery = "SELECT NVL(SUM(SAL_AMOUNT),0) FROM HRMS_SAL_DEBITS_"
					+ year
					+ " INNER JOIN HRMS_PFTRUST_CONF ON(PFT_DEBIT_CODE=SAL_DEBIT_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE))"
					+ " INNER JOIN HRMS_SALARY_LEDGER ON(LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="
					+ month + " AND LEDGER_YEAR=" + year + " )"
					+ " WHERE EMP_ID =" + bean.getEmpCode();

			Object[][] pfSubObj = getSqlModel().getSingleResult(pfSubQuery);

			try {
				pfSubAmt += Double.parseDouble(String.valueOf(pfSubObj[0][0]));
			} catch (Exception e) {
				// TODO: handle exception
			}
			pfRefundQuery = "SELECT NVL(SUM(SAL_AMOUNT),0) FROM HRMS_SAL_DEBITS_"
					+ year
					+ " INNER JOIN HRMS_LOAN_MASTER ON (LOAN_DEBIT_CODE=SAL_DEBIT_CODE)"
					+ " INNER JOIN HRMS_PFTRUST_CONF ON(PFT_LOAN_CODE=LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE))"
					+ " INNER JOIN HRMS_SALARY_LEDGER ON(LEDGER_CODE=SAL_LEDGER_CODE AND LEDGER_MONTH="
					+ month
					+ " AND LEDGER_YEAR="
					+ year
					+ " )"
					+ " WHERE EMP_ID =" + bean.getEmpCode();

			Object[][] pfRefundObj = getSqlModel().getSingleResult(
					pfRefundQuery);

			try {
				pfRefundAmt += Double.parseDouble(String
						.valueOf(pfRefundObj[0][0]));
			} catch (Exception e) {
				// TODO: handle exception
			}
			String monthString = "" + month;
			if (month < 10) {
				monthString = "0" + month;
			}
			pfLoanQuery = "SELECT SUM(LOAN_SANCTION_AMOUNT) FROM HRMS_LOAN_PROCESS "
					+ " INNER JOIN HRMS_LOAN_APPLICATION ON(HRMS_LOAN_APPLICATION.LOAN_APPL_CODE=HRMS_LOAN_PROCESS.LOAN_APPL_CODE)"
					+ " INNER JOIN HRMS_PFTRUST_CONF ON(PFT_LOAN_CODE=LOAN_CODE AND PFT_EFFECTIVE_DATE =(SELECT MAX(PFT_EFFECTIVE_DATE) FROM HRMS_PFTRUST_CONF WHERE PFT_EFFECTIVE_DATE <= SYSDATE))"
					+ " WHERE TO_CHAR(LOAN_PAYMENT_DATE,'MM-YYYY') ='"
					+ monthString
					+ "-"
					+ year
					+ "' AND LOAN_EMP_ID ="
					+ bean.getEmpCode();

			Object[][] pfLoanObj = getSqlModel().getSingleResult(pfLoanQuery);

			try {
				pfLoanAmt += Double
						.parseDouble(String.valueOf(pfLoanObj[0][0]));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		pfBalance = formatter.format(openingBalance + pfSubAmt + pfRefundAmt
				- pfLoanAmt);

		double minSanctAmt = 0.0;
		double maxSanctAmt = 0.0;
		try {
			minSanctAmt = Double.parseDouble(bean.getMinSanctionLimit())
					* Double.parseDouble(pfBalance) / 100;
			maxSanctAmt = Double.parseDouble(bean.getMaxSanctionLimit())
					* Double.parseDouble(pfBalance) / 100;
		} catch (Exception e) {
			minSanctAmt = 0;
			maxSanctAmt = 0;
		}
		bean.setMinSanctionAmt(formatter.format(minSanctAmt));
		bean.setMaxSanctionAmt(formatter.format(maxSanctAmt));

		return pfBalance;
	}

	/**
	 * @method checkNull
	 * @purpose to check whether the value of the string parameter is null or
	 *          not
	 * @param result
	 *            value of the parameter
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}

	public boolean installmentScheduleForEMI(LoanApplication bean,
			HttpServletRequest request) {

		boolean result = false;
		String startingDate = bean.getStartingDate();
		LoanProccessModel model = new LoanProccessModel();
		model.initiate(context, session);
		try {

			logger.info("inside generateInstallmentSchedule");

			Map checkMap = new HashMap();
			bean.setInstallmentFlag("true");
			ArrayList<Object> tableList = new ArrayList<Object>();

			double totalEmi = 0.0;
			double totalIntPaid = 0.0;
			double totalPrincipal = 0.0;
			double firstInt = 0.0;

			double principalAmt = Double.parseDouble(bean.getSanctionAmount());

			/*
			 * set paid installment if any
			 * 
			 */

			logger.info("intAmt inside generateFirstInterest===" + firstInt);
			String[][] installmentData = null;
			if (bean.getInterestType().equals("F")) {
				firstInt = model.generateFirstInterest(bean.getPaymentDate(),
						bean.getStartingDate(), Double.parseDouble(bean
								.getSanctionAmount()), bean.getInterestRate());
				installmentData = model.calculateNoOfInstallment(principalAmt,
						Double.parseDouble(bean.getEmiAmount()), bean
								.getInterestRate(), startingDate, firstInt);
			} else if (bean.getInterestType().equals("N")) {
				installmentData = model.calculateNoOfInstallment(principalAmt,
						Double.parseDouble(bean.getEmiAmount()), startingDate);
			} else {
				installmentData = model.calculateNoOfInstallmentForReduce(
						principalAmt, Double.parseDouble(bean.getEmiAmount()),
						Double.parseDouble(bean.getInterestRate()),
						startingDate);
			}

			if (installmentData != null) {
				for (int i = 0; i < installmentData.length; i++) {
					LoanProcessing bean1 = new LoanProcessing();

					bean1.setMonthYear(String.valueOf(installmentData[i][0]));
					bean1.setPrincipalAmt(formatter.format(Double
							.parseDouble(installmentData[i][1])));
					bean1.setInterestAmt(formatter.format(Double
							.parseDouble(installmentData[i][2])));
					bean1.setInstallmentAmt(formatter.format(Double
							.parseDouble(installmentData[i][3])));
					bean1.setBalancePrincipalAmt(formatter.format(Double
							.parseDouble(installmentData[i][4])));

					totalEmi += Double.parseDouble(installmentData[i][3]);
					totalIntPaid += Double.parseDouble(installmentData[i][2]);
					totalPrincipal += Double.parseDouble(installmentData[i][1]);
					tableList.add(bean1);
					try {

						bean.setPaidFlag("false");
						checkMap.put("" + (tableList.size() - 1), "N");

						// logger.info("checkmap===="+checkMap.size());
					} catch (Exception e) {
						// TODO: handle exception
						logger.error(e);
					}

				} // end of for loop

				if (!bean.getInterestType().equals("R")) {
					bean.setTotalPrincipalAmt(formatter.format(Double
							.parseDouble(bean.getSanctionAmount())));
					bean.setTotalInstallmenteAmt(formatter.format((Double
							.parseDouble(bean.getSanctionAmount()))
							+ totalIntPaid));
				} // end of if
				else
					bean.setTotalInstallmenteAmt(formatter.format(totalEmi));
				bean.setTotalInterestAmt(formatter.format(totalIntPaid));

				bean.setInstallmentList(tableList);
				request.setAttribute("data", checkMap);

				result = true;
				bean.setInstallmentFlag("true");
			} else {
				result = false;
				bean.setInstallmentFlag("false");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			bean.setInstallmentFlag("false");

			return false;
		}
		model.terminate();

		return result;
	}

	public boolean installmentScheduleForPrincipal(LoanApplication bean,
			HttpServletRequest request) {

		boolean result = false;
		Map<String, String> checkMap = new HashMap<String, String>();
		LoanProccessModel model = new LoanProccessModel();
		model.initiate(context, session);
		try {

			logger.info("inside installmentScheduleForPrincipal");

			ArrayList<Object> tableList = new ArrayList<Object>();

			double totalEmi = 0.0;
			double totalIntPaid = 0.0;
			double totalPrincipal = 0.0;
			double intRate = Double.parseDouble(bean.getInterestRate());
			String[] date = bean.getPaymentDate().split("-");
			String[] date1 = bean.getStartingDate().split("-");
			String strDay1 = date1[0];
			String strYear = date[2];
			String strMon1 = date1[1];
			String startingDate = bean.getStartingDate();

			double firstInt = model.generateFirstInterest(
					bean.getPaymentDate(), startingDate, Double
							.parseDouble(bean.getSanctionAmount()), bean
							.getInterestRate());

			logger.info("intAmt inside generateFirstInterest===" + firstInt);
			String[][] installmentData = null;

			double principalAmt = Double.parseDouble(bean.getSanctionAmount());

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			// subtract the prepaid amount.
			startingDate = model.getDate(startingDate);
			int noOfInstallment = 0;
			double monthlyPrincAmt = Double.parseDouble(bean
					.getMonthlyPrincAmount());
			String noOfInstallmentString = String
					.valueOf(Math.ceil(principalAmt
							/ Double.parseDouble(bean.getMonthlyPrincAmount())));
			noOfInstallment = Integer.parseInt(noOfInstallmentString.substring(
					0, noOfInstallmentString.indexOf(".")));

			logger.info("balance principal after prepayment==" + principalAmt);

			if ((bean.getInstallmentPaidFlag().equals("false"))) {

				installmentData = model.calcReduceInterestInstallmentSch(
						principalAmt
								- Double.parseDouble(bean
										.getMonthlyPrincAmount()),
						noOfInstallment - 1, intRate, startingDate, Double
								.parseDouble(bean.getMonthlyPrincAmount()));

				LoanProcessing bean1 = new LoanProcessing();

				bean1.setMonthYear(strDay1
						+ "-"
						+ (model.getMonthString(Integer.parseInt(strMon1))
								+ "-" + strYear));
				bean1.setPrincipalAmt(formatter.format(monthlyPrincAmt));
				bean1.setInterestAmt(formatter.format(firstInt));
				bean1.setInstallmentAmt(formatter.format(firstInt
						+ monthlyPrincAmt));
				bean1.setBalancePrincipalAmt(formatter.format(Double
						.parseDouble(bean.getSanctionAmount())
						- (Double.parseDouble(bean.getSanctionAmount()) / 12)));
				totalIntPaid += firstInt;
				tableList.add(bean1);
				try {

					bean.setPaidFlag("false");
					checkMap.put("" + (tableList.size() - 1), "N");

					logger.info("checkmap====" + checkMap.size());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // end of if
			else {
				Calendar calInst = Calendar.getInstance();
				Date instDate = sdf.parse(startingDate);
				calInst.setTime(instDate);

				calInst.add(calInst.MONTH, -1);
				installmentData = model.calcReduceInterestInstallmentSch(
						principalAmt, noOfInstallment, intRate, sdf
								.format(calInst.getTime()), Double
								.parseDouble(bean.getMonthlyPrincAmount()));
			}
			if (installmentData != null) {

				for (int i = 0; i < installmentData.length; i++) {
					LoanProcessing bean1 = new LoanProcessing();

					bean1.setMonthYear(String.valueOf(installmentData[i][0]));
					bean1.setPrincipalAmt(formatter.format(Double
							.parseDouble(installmentData[i][1])));
					bean1.setInterestAmt(formatter.format(Double
							.parseDouble(installmentData[i][2])));
					bean1.setInstallmentAmt(formatter.format(Double
							.parseDouble(installmentData[i][3])));
					bean1.setBalancePrincipalAmt(formatter.format(Double
							.parseDouble(installmentData[i][4])));

					totalEmi += Double.parseDouble(installmentData[i][3]);
					totalIntPaid += Double.parseDouble(installmentData[i][2]);
					totalPrincipal += Double.parseDouble(installmentData[i][1]);
					tableList.add(bean1);
					try {

						bean.setPaidFlag("false");
						checkMap.put("" + (tableList.size() - 1), "N");

						logger.info("checkmap====" + checkMap.size());
					} catch (Exception e) {
						// TODO: handle exception
						logger.error(e);
					}
				}

				bean.setTotalPrincipalAmt(formatter.format(Double
						.parseDouble(bean.getSanctionAmount())));
				logger.info("bean.setTotalPrincipalAmt=="
						+ bean.getTotalPrincipalAmt());
				bean
						.setTotalInstallmenteAmt(formatter
								.format((Double.parseDouble(bean
										.getSanctionAmount()) + totalIntPaid)));
				bean.setTotalInterestAmt(formatter.format(totalIntPaid));

				bean.setInstallmentList(tableList);
				request.setAttribute("data", checkMap);
				// end of if
				result = true;
				bean.setInstallmentFlag("true");
			} else {
				result = false;
				bean.setInstallmentFlag("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			bean.setInstallmentFlag("false");
			return false;
		}
		model.terminate();
		return result;
	}

	public boolean generateInstallmentSchedule(LoanApplication bean,
			HttpServletRequest request) {

		boolean result = false;
		Map<String, String> checkMap = new HashMap<String, String>();
		LoanProccessModel model = new LoanProccessModel();
		model.initiate(context, session);
		try {

			logger.info("inside generateInstallmentSchedule");

			ArrayList<Object> tableList = new ArrayList<Object>();
			int noOfInstallment = 0;
			if (!(bean.getInterestType().equals("I"))) {
				noOfInstallment = Integer.parseInt(bean
						.getInstallmentNumberFlat());
			} else {
				noOfInstallment = Integer.parseInt(bean.getInstallmentNumber());
			}

			double totalEmi = 0.0;
			double totalIntPaid = 0.0;
			double totalPrincipal = 0.0;
			String[] date = bean.getPaymentDate().split("-");
			String[] date1 = bean.getStartingDate().split("-");
			String strDay1 = date1[0];
			String strYear = date[2];
			String strMon1 = date1[1];
			String startingDate = bean.getStartingDate();
			double firstInt = model.generateFirstInterest(
					bean.getPaymentDate(), startingDate, Double
							.parseDouble(bean.getSanctionAmount()), bean
							.getInterestRate());

			logger.info("intAmt inside generateFirstInterest===" + firstInt);
			String[][] installmentData = null;
			/*
			 * if (! bean.getInterestType().equals("I")){ installmentData =
			 * calculateInstallment(Double.parseDouble(bean.getSanctionAmount()),
			 * noOfInstallment, bean.getInterestRate(), bean.getInterestType(),
			 * bean.getStartingDate()); } else if (
			 * bean.getInterestType().equals("I")){
			 * 
			 * installmentData =
			 * calcReduceInterestInstallmentSch(balanceAmt,noOfInstallment,
			 * Double.parseDouble(bean.getInterestRate()),
			 * bean.getStartingDate(),Double.parseDouble(bean.getSanctionAmount())/noOfInstallment); }
			 */

			double principalAmt = Double.parseDouble(bean.getSanctionAmount());

			startingDate = model.getDate(startingDate);

			logger.info("balance principal after prepayment==" + principalAmt);
			boolean isInstallmentPaid = Boolean.parseBoolean(bean
					.getInstallmentPaidFlag());
			installmentData = model.calculateInstallment(principalAmt,
					noOfInstallment, bean.getInterestRate(), bean
							.getInterestType(), startingDate, firstInt,
					isInstallmentPaid);

			if (bean.getInterestType().equals("I")
					&& bean.getInstallmentPaidFlag().equals("false")) {
				LoanProcessing bean1 = new LoanProcessing();

				bean1.setMonthYear(strDay1
						+ "-"
						+ (model.getMonthString(Integer.parseInt(strMon1))
								+ "-" + strYear));
				bean1.setPrincipalAmt(formatter.format((Double.parseDouble(bean
						.getSanctionAmount()) / noOfInstallment)));
				bean1.setInterestAmt(formatter.format(firstInt));
				bean1
						.setInstallmentAmt(formatter
								.format(firstInt
										+ ((Double.parseDouble(bean
												.getSanctionAmount()) / noOfInstallment))));
				bean1.setBalancePrincipalAmt(formatter.format(Double
						.parseDouble(bean.getSanctionAmount())
						- (Double.parseDouble(bean.getSanctionAmount()) / 12)));
				totalIntPaid += firstInt;
				tableList.add(bean1);
				try {

					bean.setPaidFlag("false");
					checkMap.put("" + (tableList.size() - 1), "N");

					logger.info("checkmap====" + checkMap.size());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // end of if

			if (installmentData != null) {
				if (!bean.getInterestType().equals("I")) {

					for (int i = 0; i < installmentData.length; i++) {
						LoanProcessing bean1 = new LoanProcessing();

						bean1.setMonthYear(String
								.valueOf(installmentData[i][0]));
						bean1.setPrincipalAmt(formatter.format(Double
								.parseDouble(installmentData[i][1])));
						bean1.setInterestAmt(formatter.format(Double
								.parseDouble(installmentData[i][2])));
						bean1.setInstallmentAmt(formatter.format(Double
								.parseDouble(installmentData[i][3])));
						bean1.setBalancePrincipalAmt(formatter.format(Double
								.parseDouble(installmentData[i][4])));

						totalEmi += Double.parseDouble(installmentData[i][3]);
						totalIntPaid += Double
								.parseDouble(installmentData[i][2]);
						totalPrincipal += Double
								.parseDouble(installmentData[i][1]);
						tableList.add(bean1);
						try {

							bean.setPaidFlag("false");
							checkMap.put("" + (tableList.size() - 1), "N");

							logger.info("checkmap====" + checkMap.size());
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(e);
						}

					} // end of for loop

				} else {

					for (int i = 0; i < installmentData.length; i++) {
						LoanProcessing bean1 = new LoanProcessing();

						bean1.setMonthYear(String
								.valueOf(installmentData[i][0]));
						bean1.setPrincipalAmt(formatter.format(Double
								.parseDouble(installmentData[i][1])));
						bean1.setInterestAmt(formatter.format(Double
								.parseDouble(installmentData[i][2])));
						bean1.setInstallmentAmt(formatter.format(Double
								.parseDouble(installmentData[i][3])));
						bean1.setBalancePrincipalAmt(formatter.format(Double
								.parseDouble(installmentData[i][4])));

						totalEmi += Double.parseDouble(installmentData[i][3]);
						totalIntPaid += Double
								.parseDouble(installmentData[i][2]);
						totalPrincipal += Double
								.parseDouble(installmentData[i][1]);
						tableList.add(bean1);
						try {

							bean.setPaidFlag("false");
							checkMap.put("" + (tableList.size() - 1), "N");

							logger.info("checkmap====" + checkMap.size());
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(e);
						}
					}

				}
				if (!bean.getInterestType().equals("R")) {
					bean.setTotalPrincipalAmt(formatter.format(Double
							.parseDouble(bean.getSanctionAmount())));
					logger.info("bean.setTotalPrincipalAmt=="
							+ bean.getTotalPrincipalAmt());
					bean.setTotalInstallmenteAmt(formatter
							.format((Double.parseDouble(bean
									.getSanctionAmount()) + totalIntPaid)));
				} // end of if
				else
					bean
							.setTotalInstallmenteAmt((formatter
									.format((totalEmi))));
				bean.setTotalInterestAmt(formatter.format(totalIntPaid));

				bean.setInstallmentList(tableList);
				request.setAttribute("data", checkMap);
				// end of if
				result = true;
				bean.setInstallmentFlag("true");
			} else {
				result = false;
				bean.setInstallmentFlag("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			bean.setInstallmentFlag("false");
			return false;
		}
		// showPrePayemntDetails(bean);
		model.terminate();
		return result;
	}

	// Added by Prashant Shinde

	public boolean getLoanLimitAmountFromLoanMaster(LoanApplication loanApp,
			String loanApplAmount) {

		String loanAmountQuery = " SELECT NVL(LOAN_LIMIT, 0)  FROM HRMS_LOAN_MASTER WHERE LOAN_CODE= "
				+ loanApp.getLoanCode();

		Object[][] loanLimitAmt = getSqlModel()
				.getSingleResult(loanAmountQuery);
		if (Double.parseDouble(String.valueOf(loanLimitAmt[0][0])) > 0) {
			if (Double.parseDouble(loanApplAmount) > Double.parseDouble(String
					.valueOf(loanLimitAmt[0][0]))) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	public boolean isCurrentUser(String userId) {
		String currentUserSql = " SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ userId;
		Object[][] currentUserObj = getSqlModel().getSingleResult(
				currentUserSql);
		if (currentUserObj != null && currentUserObj.length > 0) {
			return true;
		}
		return false;
	}

	public void getPendingList(LoanApplication bean,
			HttpServletRequest request, String userId) {

		try {
			System.out.println("fgdfgfgdg");
			Object[][] draftListData = null;
			ArrayList draftLoanApplList = new ArrayList();

			Object[][] inProcessListData = null;
			ArrayList inProcessLoanApplList = new ArrayList();

			Object[][] sentBackListData = null;
			ArrayList sentBackLoanApplList = new ArrayList();

			// For drafted application Begins

			String selQuery = "SELECT LOAN_APPL_CODE, LOAN_EMP_ID,OFFC.EMP_TOKEN|| ' - '||OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ADMIN_NAME, "
					+ "HRMS_LOAN_APPLICATION.LOAN_CODE,LOAN_NAME, TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY'), LOAN_AMOUNT, LOAN_APPL_STATUS ,LOAN_TRACKING_NUMBER "
					+ "	FROM HRMS_LOAN_APPLICATION "
					+ "		INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE)"
					+ "		LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_EMP_ID) "
					+ "		WHERE  LOAN_APPL_STATUS = 'D'  AND LOAN_INITIATOR_CODE =  "
					+ userId
					+ "			ORDER BY HRMS_LOAN_APPLICATION.LOAN_APPL_CODE  DESC";
			draftListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexDrafted = Utility.doPaging(bean.getMyPage(),
					draftListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = "0";
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = "1";
				pageIndexDrafted[3] = "1";
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalDraftPage", Integer.parseInt(String
					.valueOf(pageIndexDrafted[2])));
			request.setAttribute("draftPageNo", Integer.parseInt(String
					.valueOf(pageIndexDrafted[3])));
			if (pageIndexDrafted[4].equals("1"))
				bean.setMyPage("1");

			if (draftListData != null && draftListData.length > 0) {
				bean.setDraftLoanApplListLength(true);

				System.out.println("draftListData.length : "
						+ draftListData.length);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer
						.parseInt(pageIndexDrafted[1]); i++) {
					System.out.println("Fo loop : " + i);
					LoanApplication beanItt = new LoanApplication();

					beanItt.setLoanApplCode(checkNull(String
							.valueOf(draftListData[i][0])));
					beanItt.setEmpCode(checkNull(String
							.valueOf(draftListData[i][1])));
					beanItt.setEmpName(checkNull(String
							.valueOf(draftListData[i][2])));
					beanItt.setLoanCode(checkNull(String
							.valueOf(draftListData[i][3])));
					beanItt.setLoanType(checkNull(String
							.valueOf(draftListData[i][4])));
					beanItt.setApplicationdate(checkNull(String
							.valueOf(draftListData[i][5])));
					beanItt.setLoanAmount(checkNull(String
							.valueOf(draftListData[i][6])));
					beanItt.setApplicationStatus(checkNull(String
							.valueOf(draftListData[i][7])));
					
					beanItt.setTrackingNo(checkNull(String
							.valueOf(draftListData[i][8])));

					draftLoanApplList.add(beanItt);
				}
				bean.setDraftLoanApplIteratorList(draftLoanApplList);
			}
			// For drafted application Ends

			
			// For in-Process application Begins

			
			String inProcessQuery = "SELECT LOAN_APPL_CODE, LOAN_EMP_ID,OFFC.EMP_TOKEN|| ' - '||OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ADMIN_NAME, "
				+ "HRMS_LOAN_APPLICATION.LOAN_CODE,LOAN_NAME, TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY'), LOAN_AMOUNT, LOAN_APPL_STATUS  ,LOAN_TRACKING_NUMBER "
				+ "	FROM HRMS_LOAN_APPLICATION "
				+ "		INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE)"
				+ "		LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_EMP_ID) "
				+ "		WHERE  LOAN_APPL_STATUS IN ('P')  AND LOAN_INITIATOR_CODE =  "
				+ userId
				+ "			ORDER BY HRMS_LOAN_APPLICATION.LOAN_APPL_CODE  DESC";
			
			
			
			inProcessListData = getSqlModel().getSingleResult(inProcessQuery);

			String[] pageIndexInProcess = Utility.doPaging(bean.getMyPageInProcess(), inProcessListData.length, 20);
			if(pageIndexDrafted == null) {
				pageIndexInProcess[0] = "0";
				pageIndexInProcess[1] = "20";
				pageIndexInProcess[2] = "1";
				pageIndexInProcess[3] = "1";
				pageIndexInProcess[4] = "";
			}

			request.setAttribute("totalInProcessPage", Integer.parseInt(String.valueOf(pageIndexInProcess[2])));
			request.setAttribute("inProcessPageNo", Integer.parseInt(String.valueOf(pageIndexInProcess[3])));
			if(pageIndexInProcess[4].equals("1")) bean.setMyPageInProcess("1");
			bean.setInProcessLoanApplIteratorList(null);
			if(inProcessListData != null && inProcessListData.length > 0) {
				bean.setInProcessLoanApplListLength(true);
				for(int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer.parseInt(pageIndexInProcess[1]); i++) {
					LoanApplication beanItt = new LoanApplication();

					beanItt.setLoanApplCode(checkNull(String
							.valueOf(inProcessListData[i][0])));
					beanItt.setEmpCode(checkNull(String
							.valueOf(inProcessListData[i][1])));
					beanItt.setEmpName(checkNull(String
							.valueOf(inProcessListData[i][2])));
					beanItt.setLoanCode(checkNull(String
							.valueOf(inProcessListData[i][3])));
					beanItt.setLoanType(checkNull(String
							.valueOf(inProcessListData[i][4])));
					beanItt.setApplicationdate(checkNull(String
							.valueOf(inProcessListData[i][5])));
					beanItt.setLoanAmount(checkNull(String
							.valueOf(inProcessListData[i][6])));
					beanItt.setApplicationStatus(checkNull(String
							.valueOf(inProcessListData[i][7])));
					beanItt.setTrackingNo(checkNull(String
							.valueOf(inProcessListData[i][8])));
					inProcessLoanApplList.add(beanItt);
				}
				bean.setInProcessLoanApplIteratorList(inProcessLoanApplList);
			}
			// For in-Process application Ends
			  
			/*
			 * * // Sent-Back application Begins / * String sentBackQuery =
			 * "SELECT TRACKING_NUMBER, HRMS_D1_INF_CNG_REQ.CREATED_BY, " +"
			 * HRMS_EMP_OFFC.EMP_TOKEN||'-'||HRMS_EMP_OFFC.EMP_FNAME||'
			 * '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , " +"
			 * TO_CHAR(CREATED_ON,'DD-MM-YYYY'),TO_CHAR(CNG_DATE,'DD-MM-YYYY') ," +"
			 * INF_CNG_CODE , APPL_STATUS" +" FROM HRMS_D1_INF_CNG_REQ" +" left
			 * join HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID =
			 * HRMS_D1_INF_CNG_REQ.CREATED_BY)" +" where
			 * HRMS_D1_INF_CNG_REQ.APPL_STATUS = 'B' AND
			 * HRMS_D1_INF_CNG_REQ.CREATED_BY = " +userId +" ORDER BY
			 * HRMS_D1_INF_CNG_REQ.INF_CNG_CODE DESC" ;
			 * 
			 * sentBackListData = getSqlModel().getSingleResult(sentBackQuery);
			 * 
			 * String[] pageIndexSentBack =
			 * Utility.doPaging(bean.getMyPageSentBack(),
			 * sentBackListData.length, 20); if (pageIndexSentBack == null) {
			 * pageIndexSentBack[0] = "0"; pageIndexSentBack[1] = "20";
			 * pageIndexSentBack[2] = "1"; pageIndexSentBack[3] = "1";
			 * pageIndexSentBack[4] = ""; }
			 * 
			 * request.setAttribute("totalSentBackPage", Integer.parseInt(String
			 * .valueOf(pageIndexSentBack[2])));
			 * request.setAttribute("sentBackPageNo", Integer.parseInt(String
			 * .valueOf(pageIndexSentBack[3]))); if
			 * (pageIndexSentBack[4].equals("1")) bean.setMyPageSentBack("1");
			 * 
			 * if (sentBackListData != null && sentBackListData.length > 0) {
			 * bean.setSentBackVoucherListLength(true); for (int i =
			 * Integer.parseInt(pageIndexSentBack[0]); i < Integer
			 * .parseInt(pageIndexSentBack[1]); i++) {
			 * InformationSystemChangeRequestForm beanItt = new
			 * InformationSystemChangeRequestForm();
			 * 
			 * beanItt.setTrackingNo(checkNull(String
			 * .valueOf(sentBackListData[i][0])));
			 * beanItt.setEmployeeName(checkNull(String
			 * .valueOf(sentBackListData[i][2])));
			 * beanItt.setApplDate(checkNull(String
			 * .valueOf(sentBackListData[i][3])));
			 * beanItt.setChangeSchedularOccur(checkNull(String
			 * .valueOf(sentBackListData[i][4])));
			 * beanItt.setInfoSysReqId(checkNull(String
			 * .valueOf(sentBackListData[i][5])));
			 * beanItt.setStatus(checkNull(String
			 * .valueOf(sentBackListData[i][6])));
			 * 
			 * sentBackVoucherList.add(beanItt); }
			 * bean.setSentBackVoucherIteratorList(sentBackVoucherList); } //
			 * Sent-Back application Ends
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void editApplicationFunction(LoanApplication loanApp,
			String requestID) {
		try {

			String editQuery = "SELECT LOAN_APPL_CODE, LOAN_EMP_ID,OFFC.EMP_TOKEN ,"
					+ "OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ADMIN_NAME,"
					+ " HRMS_LOAN_APPLICATION.LOAN_CODE,LOAN_NAME, TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY'), LOAN_AMOUNT,"
					+ " LOAN_APPL_STATUS ,LOAN_APPLICANT_COMMENT ,OFFC.EMP_RANK,RANK_NAME AS DESIGNATION ,"
					+ " OFFC.EMP_DEPT,DEPT_NAME AS DEPARTMENT , OFFC.EMP_CENTER,CENTER_NAME AS BRANCH,"
					+ "TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') ,OFFC.EMP_CADRE,CADRE_NAME ,LOAN_EMI_TYPE, LOAN_INITIATOR_CODE,"
					+ "INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, "
					+ "TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY HH24:MI'),LOAN_TRACKING_NUMBER ,OFFC.EMP_DIV,DIV_NAME "
					+ "	FROM HRMS_LOAN_APPLICATION "
					+ "		INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE)"
					+ "		LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_EMP_ID) "
					+ "	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = OFFC.EMP_RANK)	"
					+ "	 LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = OFFC.EMP_DEPT)"
					+ "	  LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = OFFC.EMP_CENTER)"
					+ "	   LEFT JOIN HRMS_CADRE  ON (HRMS_CADRE.CADRE_ID = OFFC.EMP_CADRE)"
					+ "LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_INITIATOR_CODE)"
					+"LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = OFFC.EMP_DIV)"
					+ "		WHERE  LOAN_APPL_CODE = " + requestID;

			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if (editObj != null && editObj.length > 0) {
				loanApp.setHiddenCode(checkNull(String.valueOf(editObj[0][0])));
				loanApp.setEmpCode(checkNull(String.valueOf(editObj[0][1])));
				loanApp.setEmpToken(checkNull(String.valueOf(editObj[0][2])));
				loanApp.setEmpName(checkNull(String.valueOf(editObj[0][3])));
				loanApp.setLoanCode(checkNull(String.valueOf(editObj[0][4])));
				loanApp.setLoanType(checkNull(String.valueOf(editObj[0][5])));
				loanApp.setApplicationdate(checkNull(String
						.valueOf(editObj[0][6])));
				loanApp.setLoanAmount(checkNull(String.valueOf(editObj[0][7])));
				loanApp.setApplicationStatus(checkNull(String
						.valueOf(editObj[0][8])));
				loanApp.setApplicantComment(checkNull(String
						.valueOf(editObj[0][9])));

				loanApp.setDesgCode(checkNull(String.valueOf(editObj[0][10])));
				loanApp.setDesgName(checkNull(String.valueOf(editObj[0][11])));

				loanApp.setDeptCode(checkNull(String.valueOf(editObj[0][12])));
				loanApp.setDeptName(checkNull(String.valueOf(editObj[0][13])));

				loanApp
						.setBranchCode(checkNull(String.valueOf(editObj[0][14])));
				loanApp
						.setBranchName(checkNull(String.valueOf(editObj[0][15])));

				loanApp.setConfirmationDate(checkNull(String
						.valueOf(editObj[0][16])));

				loanApp.setGradeCode(checkNull(String.valueOf(editObj[0][17])));
				loanApp.setGrade(checkNull(String.valueOf(editObj[0][18])));

				if (String.valueOf(editObj[0][19]).equals("E")) {
					loanApp.setExpectedEmi("true");
					loanApp.setHiddenChechfrmId("E");
				}

				if (String.valueOf(editObj[0][19]).equals("T")) {
					loanApp.setTenure("true");
					loanApp.setHiddenChechfrmId("E");
				}

				loanApp.setInitiatorCode(checkNull(String
						.valueOf(editObj[0][20])));
				loanApp.setInitiatorName(checkNull(String
						.valueOf(editObj[0][21])));
				loanApp.setInitiatorDate(checkNull(String
						.valueOf(editObj[0][22])));
				loanApp.setTrackingNo(checkNull(String
						.valueOf(editObj[0][23])));
				
				loanApp.setDivCode(checkNull(String
						.valueOf(editObj[0][24])));
				loanApp.setDivName(checkNull(String
						.valueOf(editObj[0][25])));

				
				getApproverCommentList(loanApp, requestID);
				
				
				/*
				 * for (int i = 0; i < editObj.length; i++) { for (int j = 0; j <
				 * editObj[i].length; j++) { logger.info("addObj[" + i + "][" +
				 * j + "] " + editObj[i][j]); } }
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getApproverCommentList(LoanApplication loanApp,
			String requestID) {
		/*
		 * select query to select approver comment data from hrms_loan_path
		 */
		String commentQuery = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "
				+ "TO_CHAR(LOAN_APPROVED_DATE, 'DD-MM-YYYY'), DECODE(LOAN_APPL_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected', 'B', 'SendBack') STATUS, "
				+ "NVL(LOAN_COMMENTS, ' '),LOAN_APPR_AMT  "
				+ "FROM HRMS_LOAN_PATH "
				+ "INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LOAN_PATH.LOAN_APPROVER_CODE) "
				+ "WHERE LOAN_APPL_CODE = "
				+ requestID
				+ " ORDER BY LOAN_PATH_ID  DESC";

			Object[][] apprCommentListObj = getSqlModel().getSingleResult(commentQuery);
			ArrayList approverList = new ArrayList<Object>(apprCommentListObj.length);
			if(apprCommentListObj !=null && apprCommentListObj.length>0)
				{
				loanApp.setApproverCommentFlag(true);
				for(int i = 0; i < apprCommentListObj.length; i++) {
					LoanApplication innerBean = new LoanApplication();
				innerBean.setApprName(String.valueOf(apprCommentListObj[i][0]));
				innerBean.setApprComments(checkNull(String.valueOf(apprCommentListObj[i][3])));
				innerBean.setApprDate(String.valueOf(apprCommentListObj[i][1]));
				innerBean.setApprStatus(String.valueOf(apprCommentListObj[i][2]));
				innerBean.setApprAmount(checkNull(String.valueOf(apprCommentListObj[i][4])));
				approverList.add(innerBean);
				}
				loanApp.setListComment(approverList);
		}
		
	}

	public boolean draftFunction(LoanApplication loanApp, Object[][] empFlow,
			HttpServletRequest request, String status) {
		boolean result = false;
		System.out.println("Status : " + status);
		try {
			Object addObj[][] = new Object[1][11];
			addObj[0][0] = loanApp.getEmpCode();
			addObj[0][1] = loanApp.getApplicationdate();
			addObj[0][2] = loanApp.getLoanCode();
			addObj[0][3] = loanApp.getLoanAmount();

			if (loanApp.getHiddenChechfrmId().equals("E")) {
				addObj[0][4] = "E";
			} else if (loanApp.getHiddenChechfrmId().equals("T")) {
				addObj[0][4] = "T";
			} else if (loanApp.getHiddenChechfrmId().equals("")) {
				addObj[0][4] = "";
			}

			addObj[0][5] = loanApp.getApplicantComment();
			addObj[0][6] = status;
			addObj[0][7] = loanApp.getUserEmpId();

			addObj[0][8] = empFlow[0][0]; // approver code from
			// empFlow
			addObj[0][9] = empFlow[0][3]; // ALTERNATE approver code
			// from empFlow

			
			String autoCode = "";
			String selQuery = "SELECT NVL(MAX(LOAN_APPL_CODE),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(LOAN_APPL_CODE),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_LOAN_APPLICATION	";
			Object[][] selData = getSqlModel().getSingleResult(selQuery);
			if (loanApp.getHiddenCode().equals("")) {			
				autoCode = String.valueOf(selData[0][0]);
				System.out.println("autoCode==="+ autoCode);
				loanApp.setHiddenCode(autoCode);			
				/**
				 * SET TRACKING NO
				 */
				String qq="SELECT NVL(MAX(LOAN_APPL_CODE),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(LOAN_APPL_CODE),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_LOAN_APPLICATION	";
						Object[][]obj=getSqlModel().getSingleResult(qq);
						if(obj !=null && obj.length>0){			
						try {
							ApplCodeTemplateModel model = new ApplCodeTemplateModel();
							model.initiate(context, session);
							String applnCode = model.generateApplicationCode(String.valueOf(obj[0][1]), "Loan", loanApp.getUserEmpId(),String.valueOf(obj[0][2]));
							loanApp.setTrackingNo(checkNull(applnCode));
							System.out.println("applnCode%%%%%" + applnCode);
							model.terminate();
						} catch (Exception e) {
							// TODO: handle exception
						}
						}			
			}
			
			addObj[0][10] = loanApp.getTrackingNo();
			
			
			
			String insertQuery = "INSERT INTO HRMS_LOAN_APPLICATION"
					+ "(LOAN_APPL_CODE, LOAN_EMP_ID, LOAN_APPL_DATE,LOAN_CODE, "
					+ " LOAN_AMOUNT,  LOAN_EMI_TYPE,LOAN_APPLICANT_COMMENT,LOAN_APPL_STATUS," +
							" LOAN_INITIATOR_CODE,LOAN_APPL_APPROVER,LOAN_APPL_ALTER_APPROVER, LOAN_TRACKING_NUMBER,LOAN_APPL_LEVEL)"
					+ " VALUES((SELECT NVL(MAX(LOAN_APPL_CODE),0)+1 FROM HRMS_LOAN_APPLICATION),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,1)";
			/* ,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; */

			result = getSqlModel().singleExecute(insertQuery, addObj);
			/*for (int i = 0; i < addObj.length; i++) {
				for (int j = 0; j < addObj[i].length; j++) {
					logger.info("insertObj[" + i + "][" + j + "]  "
							+ addObj[i][j]);
				}
			}*/

			if (result) {
				String autoCodeQuery = " SELECT NVL(MAX(LOAN_APPL_CODE),0) FROM HRMS_LOAN_APPLICATION ";
				Object[][] data = getSqlModel().getSingleResult(autoCodeQuery);
				if (data != null && data.length > 0) {
					loanApp.setHiddenCode(String.valueOf(data[0][0]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getAmountLimit(LoanApplication loanApp) {
		String loanAmountQuery = " SELECT NVL(LOAN_LIMIT, 0),DECODE(INT_TYPE,'N','No Interest','F','Flat Interest','R','Reducing Principal','I','Reducing Interest'), INT_RATE  FROM HRMS_LOAN_MASTER WHERE LOAN_CODE= "
				+ loanApp.getLoanCode();

		Object[][] loanLimitAmt = getSqlModel()
				.getSingleResult(loanAmountQuery);

		if (loanLimitAmt != null && loanLimitAmt.length > 0) {
			loanApp.setLoanAllowedLimit(checkNull(String
					.valueOf(loanLimitAmt[0][0])));
			loanApp.setInterestType(checkNull(String
					.valueOf(loanLimitAmt[0][1])));
			loanApp.setInterestRate(checkNull(String
					.valueOf(loanLimitAmt[0][2])));

		}

	}

	public void getSetEmployeeDetails(LoanApplication loanApp) {
		String dateQuery = "SELECT to_char(sysdate,'DD-MM-YYYY HH24:MI') from dual ";
		Object[][] dateObj = getSqlModel().getSingleResult(dateQuery);
		if (dateObj != null && dateObj.length > 0) {
			loanApp.setInitiatorDate(String.valueOf(dateObj[0][0]));
		}

		String initiatorQuery = "SELECT INITIATOR.EMP_TOKEN, "
				+ "	INITIATOR.EMP_TOKEN||' - '||INITIATOR.EMP_FNAME||' '||INITIATOR.EMP_MNAME||' '||INITIATOR.EMP_LNAME "
				+ "	FROM HRMS_EMP_OFFC INITIATOR WHERE INITIATOR.EMP_ID ="
				+ loanApp.getUserEmpId();

		Object[][] initiatorObj = getSqlModel().getSingleResult(initiatorQuery);
		if (initiatorObj != null && initiatorObj.length > 0) {
			loanApp.setInitiatorCode(loanApp.getUserEmpId());
			loanApp.setInitiatorToken(String.valueOf(initiatorObj[0][0]));
			loanApp.setInitiatorName(String.valueOf(initiatorObj[0][1]));
		}

	}

	public boolean sendForApprovalFunction(LoanApplication bean,
			HttpServletRequest request, Object[][] empFlow, String status) {
		boolean result = false;
		if (bean.getHiddenCode().equals("")) {
			result = draftFunction(bean, empFlow, request,status);
		} else {
			result = updateLoanApplication(bean, empFlow);
			try {
				String changeStatusQuery = "UPDATE HRMS_LOAN_APPLICATION SET LOAN_APPL_STATUS = 'P' WHERE LOAN_APPL_CODE = "
						+ bean.getHiddenCode();
				result = getSqlModel().singleExecute(changeStatusQuery);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void viewApplicationFunction(LoanApplication loanApp,
			String requestID) {
		try {

			String viewQuery = "SELECT LOAN_APPL_CODE, LOAN_EMP_ID,OFFC.EMP_TOKEN ,"
					+ "OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME AS ADMIN_NAME,"
					+ " HRMS_LOAN_APPLICATION.LOAN_CODE,LOAN_NAME, TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY'), LOAN_AMOUNT,"
					+ " LOAN_APPL_STATUS ,LOAN_APPLICANT_COMMENT ,OFFC.EMP_RANK,RANK_NAME AS DESIGNATION ,"
					+ " OFFC.EMP_DEPT,DEPT_NAME AS DEPARTMENT , OFFC.EMP_CENTER,CENTER_NAME AS BRANCH,"
					+ "TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') ,OFFC.EMP_CADRE,CADRE_NAME ,LOAN_EMI_TYPE, LOAN_INITIATOR_CODE,"
					+ "INITIATOR.EMP_TOKEN||' '||INITIATOR.EMP_FNAME || ' ' || INITIATOR.EMP_MNAME || ' ' || INITIATOR.EMP_LNAME AS INITIATOR_NAME, "
					+ "TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY HH24:MI'),LOAN_TRACKING_NUMBER ,EMP_DIV,DIV_NAME "
					+ "	FROM HRMS_LOAN_APPLICATION "
					+ "		INNER JOIN HRMS_LOAN_MASTER ON (HRMS_LOAN_MASTER.LOAN_CODE = HRMS_LOAN_APPLICATION.LOAN_CODE)"
					+ "		LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_EMP_ID) "
					+ "	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = OFFC.EMP_RANK)	"
					+ "	 LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = OFFC.EMP_DEPT)"
					+ "	  LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = OFFC.EMP_CENTER)"
					+ "	   LEFT JOIN HRMS_CADRE  ON (HRMS_CADRE.CADRE_ID = OFFC.EMP_CADRE)"
					+ "LEFT JOIN HRMS_EMP_OFFC INITIATOR ON(INITIATOR.EMP_ID = HRMS_LOAN_APPLICATION.LOAN_INITIATOR_CODE)"
					+" LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = OFFC.EMP_DIV) "
					+ "		WHERE  LOAN_APPL_CODE = " + requestID;

			Object[][] viewObj = getSqlModel().getSingleResult(viewQuery);
			if (viewObj != null && viewObj.length > 0) {
				loanApp.setHiddenCode(checkNull(String.valueOf(viewObj[0][0])));
				loanApp.setEmpCode(checkNull(String.valueOf(viewObj[0][1])));
				loanApp.setEmpToken(checkNull(String.valueOf(viewObj[0][2])));
				loanApp.setEmpName(checkNull(String.valueOf(viewObj[0][3])));
				loanApp.setLoanCode(checkNull(String.valueOf(viewObj[0][4])));
				loanApp.setLoanType(checkNull(String.valueOf(viewObj[0][5])));
				loanApp.setApplicationdate(checkNull(String
						.valueOf(viewObj[0][6])));
				loanApp.setLoanAmount(checkNull(String.valueOf(viewObj[0][7])));
				loanApp.setApplicationStatus(checkNull(String
						.valueOf(viewObj[0][8])));
				loanApp.setApplicantComment(checkNull(String
						.valueOf(viewObj[0][9])));

				loanApp.setDesgCode(checkNull(String.valueOf(viewObj[0][10])));
				loanApp.setDesgName(checkNull(String.valueOf(viewObj[0][11])));

				loanApp.setDeptCode(checkNull(String.valueOf(viewObj[0][12])));
				loanApp.setDeptName(checkNull(String.valueOf(viewObj[0][13])));

				loanApp
						.setBranchCode(checkNull(String.valueOf(viewObj[0][14])));
				loanApp
						.setBranchName(checkNull(String.valueOf(viewObj[0][15])));

				loanApp.setConfirmationDate(checkNull(String
						.valueOf(viewObj[0][16])));

				loanApp.setGradeCode(checkNull(String.valueOf(viewObj[0][17])));
				loanApp.setGrade(checkNull(String.valueOf(viewObj[0][18])));

				if (String.valueOf(viewObj[0][19]).equals("E")) {
					loanApp.setExpectedEmi("true");
					loanApp.setHiddenChechfrmId("E");
				}

				if (String.valueOf(viewObj[0][19]).equals("T")) {
					loanApp.setTenure("true");
					loanApp.setHiddenChechfrmId("E");
				}

				loanApp.setInitiatorCode(checkNull(String
						.valueOf(viewObj[0][20])));
				loanApp.setInitiatorName(checkNull(String
						.valueOf(viewObj[0][21])));
				loanApp.setInitiatorDate(checkNull(String
						.valueOf(viewObj[0][22])));
				loanApp.setTrackingNo(checkNull(String
						.valueOf(viewObj[0][23])));
				loanApp.setDivCode(checkNull(String
						.valueOf(viewObj[0][24])));
				loanApp.setDivName(checkNull(String
						.valueOf(viewObj[0][25])));
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public void setApproverData(LoanApplication bean, Object[][] empFlow) {
		try {
			if(empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][1];
				for(int i = 0; i < empFlow.length; i++) {

					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
						+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' " + "  FROM HRMS_EMP_OFFC "
						+ " left JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)" + " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

					Object temObj[][] = getSqlModel().getSingleResult(selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
				}

				if(approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for(int i = 0; i < approverDataObj.length; i++) {
						LoanApplication bean1 = new LoanApplication();
						bean1.setApproverName(String.valueOf(approverDataObj[i][0]));
						// String srNo = i + 1 + getOrdinalFor(i + 1)
						// + "-Approver";
						// bean1.setSrNoIterator(srNo);
						arrList.add(bean1);
					}
					bean.setApproverList(arrList);
				}else {
					
				}

			}
		} catch(Exception e) {
			logger.error("Exception in setApproverData------" + e);
		}

	}


	/**
	 * Method name-- showInstallmentSchedule parameters--- LoanProcessing
	 * bean,HttpServletRequest request return type-- void Purpose --- To
	 * retrieve all the installment details for the selected application and to
	 * display those details on the form
	 */
	public void showInstallmentSchedule(LoanApplication bean,
			HttpServletRequest request) {
		try {
			logger.info("inside showInstallmentSchedule");
			String monthYear = "";
			double totalEmi = 0.0;
			double totalIntPaid = 0.0;
			double totalPrincipal = 0.0;
			bean.setInstallmentPaidFlag("false");
			Map checkMap = new HashMap();
			int noOfPaidInstallments = 0;
			ArrayList<Object> tableList = new ArrayList<Object>();
			Object[] loanApplCode = { bean.getHiddenCode() };
			Object[][] installData = getSqlModel().getSingleResult(getQuery(7),
					loanApplCode);
			for (int i = 0; i < installData.length; i++) {
				LoanApplication bean1 = new LoanApplication();
				monthYear = String.valueOf(installData[i][7]) + "-"
						+ String.valueOf(installData[i][0]) + "-"
						+ String.valueOf(installData[i][1]);
				bean1.setMonthYear(monthYear);
				bean1.setPrincipalAmt(String.valueOf(installData[i][2]));
				bean1.setInterestAmt(String.valueOf(Double.parseDouble(String
						.valueOf(installData[i][3]))));
				bean1.setInstallmentAmt(String.valueOf(Double
						.parseDouble(String.valueOf(installData[i][4]))));
				bean1.setBalancePrincipalAmt(Utility.twoDecimals(String
						.valueOf(installData[i][5])));
				totalEmi += Double.parseDouble(String
						.valueOf(installData[i][4]));
				totalIntPaid += Double.parseDouble(String
						.valueOf(installData[i][3]));
				totalPrincipal += Double.parseDouble(String
						.valueOf(installData[i][2]));
				// bean1.setCheckFlag(String.valueOf(installData[i][6]));

				if (String.valueOf(installData[i][6]).equals("Y"))
					bean1.setPaidFlag("true");
				else
					bean1.setPaidFlag("false");
				tableList.add(bean1);
				try {
					if (String.valueOf(installData[i][6]).equals("Y")) {
						logger.info("Paid flag  in if   "
								+ String.valueOf(installData[i][6]));
						checkMap.put("" + i, "Y");
						bean.setInstallmentPaidFlag("true");
						noOfPaidInstallments++;
					} // end of if
					else {
						logger.info("Paid flag  in else  "
								+ String.valueOf(installData[i][6]));
						checkMap.put("" + i, "N");
					} // end of else

					/*
					 * if(bean1.getCheckFlag().equals("Y")){
					 * bean1.setPaidFlag("true"); checkMap.put(""+i,"Y"); }else{
					 * bean1.setPaidFlag("false"); checkMap.put(""+i,"N"); }
					 */
				} catch (Exception e) {
					// TODO: handle exception
					logger.error(e);
				}
				// checkMap.put(""+i, "Y");
			} // end of for loop

			bean.setInstallmentFlag("true");
			/*
			 * if (!bean.getInterestType().equals("R")) {
			 * bean.setTotalPrincipalAmt(formatter.format(Double
			 * .parseDouble(bean.getSanctionAmount())));
			 * bean.setTotalInstallmenteAmt(formatter.format(Double
			 * .parseDouble(bean.getSanctionAmount()) + totalIntPaid)); } // end
			 * of if else
			 */

				bean.setTotalInstallmenteAmt(formatter.format(totalEmi));
			bean.setTotalInterestAmt(formatter.format(totalIntPaid));

			bean.setInstallmentList(tableList);
			request.setAttribute("data", checkMap);
			bean.setNoOfPaidInstallments(String.valueOf(noOfPaidInstallments));
		} catch (Exception e) {
			 e.printStackTrace();
			// logger.error(e);
		}

	}
}
