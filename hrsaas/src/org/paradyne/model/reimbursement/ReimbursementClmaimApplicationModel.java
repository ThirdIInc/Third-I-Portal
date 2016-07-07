package org.paradyne.model.reimbursement;

import java.awt.Color;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.reimbursement.ReimbAdminApproval;
import org.paradyne.bean.reimbursement.ReimbursementClmaimApplication;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.common.ReportingModel;

import com.lowagie.text.Font;

public class ReimbursementClmaimApplicationModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ReimbursementClmaimApplicationModel.class);

	public boolean setBalanceDetails(ReimbursementClmaimApplication bean) {
		// TODO Auto-generated method stub
		boolean result = false;

		try {
 
		/*	String query = "SELECT CREDIT_CODE,NVL(CREDIT_NAME,' '),(NVL(REIMB_BALANCE_AMT,0)-NVL(REIMB_ONHOLD_AMT,0))  FROM HRMS_CREDIT_HEAD "
					+ " LEFT JOIN HRMS_REIMB_BALANCE ON(HRMS_CREDIT_HEAD.CREDIT_CODE=REIMB_CREDIT_CODE AND REIMB_EMP_ID="
					+ bean.getUserEmpId()
					+ ") "
					+ " WHERE  CREDIT_HEAD_TYPE IN('R','V')  ORDER BY CREDIT_CODE";*/
			String query = "SELECT CREDIT_CODE,NVL(CREDIT_NAME,' '),NVL(REIMB_BALANCE_AMT,0), NVL(REIMB_PRE_APPPROVED_AMT,0) FROM HRMS_CREDIT_HEAD "
					+ " LEFT JOIN HRMS_REIMB_BALANCE ON(HRMS_CREDIT_HEAD.CREDIT_CODE=REIMB_CREDIT_CODE AND REIMB_EMP_ID="
					+ bean.getUserEmpId()
					+ ") "
					+ " WHERE  CREDIT_HEAD_TYPE IN('R','V')  ORDER BY CREDIT_CODE";
			Object[][] dataObj = getSqlModel().getSingleResult(query);
			int count = 0;
			if (dataObj != null && dataObj.length > 0) {
				ArrayList<Object> balanceList = new ArrayList<Object>();
				for (Object data : dataObj) {
					ReimbursementClmaimApplication reimbursementbean = new ReimbursementClmaimApplication();
					reimbursementbean.setCreditCode(checkNull(String
							.valueOf(dataObj[count][0])));
					reimbursementbean.setCreditType(checkNull(String
							.valueOf(dataObj[count][1])));
					reimbursementbean.setBalance(checkNull(String
							.valueOf(dataObj[count][2])));
					reimbursementbean.setBillsCarriedForwardList(checkNull(String
							.valueOf(dataObj[count][3])));
					balanceList.add(reimbursementbean);
					reimbursementbean.setEmpCode(bean.getUserEmpId());
					count++;
				}

				bean.setBalanceList(balanceList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getEmployeeDetails(ReimbursementClmaimApplication bean) {
		String query = "  SELECT EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) EMP_NAME, CENTER_NAME, DEPT_NAME, RANK_NAME, "
				+ " NVL(CADRE_NAME,''),NVL(DIV_NAME,''),EMP_DIV, "
				+ "	 HRMS_EMP_OFFC.EMP_ID ,TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_EMP_OFFC "
				+ "	 LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
				+ "		 INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER )  "
				+ "	 INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )  "
				+ "	 INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )  "
				+ "	  INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC. EMP_DIV ) "
				+ "	 LEFT JOIN hrms_Cadre on(hrms_Cadre.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
				+ "	 WHERE HRMS_EMP_OFFC.EMP_ID =" + bean.getUserEmpId();

		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {
			bean.setEmpToken(checkNull(String.valueOf(data[0][0])));
			bean.setEmpName(checkNull(String.valueOf(data[0][1])));
			bean.setBranch(checkNull(String.valueOf(data[0][2])));
			bean.setDesignation(checkNull(String.valueOf(data[0][4])));
			bean.setGrade(checkNull(String.valueOf(data[0][5])));
			bean.setDivision(checkNull(String.valueOf(data[0][6])));
			bean.setDivisionCode(checkNull(String.valueOf(data[0][7])));
			bean.setEmpId(checkNull(String.valueOf(data[0][8])));
			bean.setClaimDate(checkNull(String.valueOf(data[0][9])));
			bean.setSysdate(checkNull(String.valueOf(data[0][9])));

		}
	}

	public String checkNull(String result) {
		if (result.equals("") || result.equals("null")) {
			result = "";
		}
		return result;
	}

	public void setProofList(String[] srNo, String[] proofName,
			String[] proofFileName, ReimbursementClmaimApplication bean) {
		try {
			ReimbursementClmaimApplication reimbursementBean = new ReimbursementClmaimApplication();
			reimbursementBean.setProofName(bean.getUploadFileName());
			reimbursementBean.setProofFileName(bean.getUserUploadFileName());
			ArrayList<ReimbursementClmaimApplication> proofList = displayNewValueProofList(
					srNo, proofName, proofFileName, bean);
			reimbursementBean
					.setProofSrNo(String.valueOf(proofList.size() + 1));// sr no
			proofList.add(reimbursementBean);
			bean.setProofList(proofList);
		} catch (Exception e) {
			logger.error("Exception in setProofList------" + e);
		}

	}

	private ArrayList<ReimbursementClmaimApplication> displayNewValueProofList(
			String[] srNo, String[] proofName, String[] proofFileName,
			ReimbursementClmaimApplication bean) {
		ArrayList<ReimbursementClmaimApplication> proofList = null;
		try {
			proofList = new ArrayList<ReimbursementClmaimApplication>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					ReimbursementClmaimApplication localbean = new ReimbursementClmaimApplication();
					localbean.setProofName(proofName[i]);
					localbean.setProofSrNo(srNo[i]);
					localbean.setProofFileName(proofFileName[i]);
					proofList.add(localbean);

				}

			}
		} catch (Exception e) {
			logger.error("Exception in displayNewValueProofList------" + e);
		}
		return proofList;
	}

	public void removeUploadFile(String[] srNo, String[] proofName,
			String[] proofFileName, ReimbursementClmaimApplication mainbean) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					ReimbursementClmaimApplication bean = new ReimbursementClmaimApplication();
					bean.setProofSrNo(String.valueOf(i + 1));
					bean.setProofName(proofName[i]);
					bean.setProofFileName(proofFileName[i]);
					tableList.add(bean);
				}
				tableList.remove(Integer.parseInt(mainbean
						.getCheckRemoveUpload()) - 1);

			}

			mainbean.setProofList(tableList);
		} catch (Exception e) {
			logger.error("Exception in removeUploadFile------" + e);
		}

	}

	public void addtoList(String[] srNo, String[] proofName,
			String[] reimbursementCrediCodeItt,
			String[] reimbursementCrediNameItt, String[] expenseDateItt,
			String[] claimAmountItt, String[] uploadDoc,
			String[] proofFileName, String[] documentPath,String[] billAmountItt,
			ReimbursementClmaimApplication bean) {
		try {
			ReimbursementClmaimApplication localbean = new ReimbursementClmaimApplication();
			localbean.setReimbursementCrediCodeItt(bean
					.getReimbursementCrediCode());
			localbean.setReimbursementCrediNameItt(bean
					.getReimbursementCrediName());
			localbean.setExpenseDateItt(bean.getExpenseDate());
			localbean.setClaimAmountItt(bean.getClaimAmount());
			localbean.setBillAmountItt(bean.getBillAmount());

			String uploaddocument = "";
			String uploaddocumentPath = "";
			if (proofName != null && proofName.length > 0) {

				 
				for (int i = 0; i < proofName.length; i++) {
					uploaddocument += proofName[i] + ",";
					 
					uploaddocumentPath += proofFileName[i] + ",";
				}
				uploaddocument = uploaddocument.substring(0, uploaddocument
						.length() - 1);
				uploaddocumentPath = uploaddocumentPath.substring(0,
						uploaddocumentPath.length() - 1);
				// uploadLinks=uploadLinks.substring(0,uploadLinks.length()-1);
				String[] splitDocumentPath = uploaddocumentPath.split(",");
				String[] splitDocument = uploaddocument.split(",");
				ArrayList list = new ArrayList();

			 

				if (splitDocument != null && splitDocument.length > 0) {
					for (int j = 0; j < splitDocument.length; j++) {
						ReimbursementClmaimApplication innerbean = new ReimbursementClmaimApplication();
						innerbean.setUploadDoc(splitDocument[j]);
						innerbean.setUploadDocPath(splitDocumentPath[j]);
						list.add(innerbean);
					}
					localbean.setIttUploadList(list);
				}

			}

			localbean.setUploadDoc(uploaddocument);
			localbean.setUploadDocPath(uploaddocumentPath);
			ArrayList<ReimbursementClmaimApplication> arraylist = displayNewValue(
					srNo, proofName, reimbursementCrediCodeItt,
					reimbursementCrediNameItt, expenseDateItt, claimAmountItt,
					uploadDoc, documentPath,billAmountItt, bean);

			localbean.setSrNo(String.valueOf(arraylist.size() + 1));// sr no

			arraylist.add(localbean);

			bean.setList(arraylist);

		} catch (Exception e) {
			logger.error("Exception in removeUploadFile------" + e);
			e.printStackTrace();
		}

	}

	private ArrayList<ReimbursementClmaimApplication> displayNewValue(
			String[] srNo, String[] proofName,
			String[] reimbursementCrediCodeItt,
			String[] reimbursementCrediNameItt, String[] expenseDateItt,
			String[] claimAmountItt, String[] document, String[] documentPath, String[] billAmountItt,
			ReimbursementClmaimApplication bean) {
		// TODO Auto-generated method stub

		ArrayList<ReimbursementClmaimApplication> list = null;
		try {
			list = new ArrayList<ReimbursementClmaimApplication>();
			if (srNo != null && srNo.length > 0) {
			 
				for (int i = 0; i < srNo.length; i++) {
					ReimbursementClmaimApplication localbean = new ReimbursementClmaimApplication();
					localbean
							.setReimbursementCrediCodeItt(reimbursementCrediCodeItt[i]);
					localbean
							.setReimbursementCrediNameItt(reimbursementCrediNameItt[i]);
					localbean.setExpenseDateItt(expenseDateItt[i]);
					localbean.setClaimAmountItt(claimAmountItt[i]);
					localbean.setUploadDoc(document[i]);
					localbean.setUploadDocPath(documentPath[i]);
					localbean.setBillAmountItt(billAmountItt[i]);
					if (document != null && document.length > 0) {

						if (document[i] != null && !document[i].equals("null")
								&& !document[i].equals("")) {
							String[] splitDocument = document[i].split(",");
							String[] splitDocumentPath = documentPath[i]
									.split(",");
							ArrayList arraylist = new ArrayList();
							logger.info("splitDocument" + splitDocument.length);
							for (int j = 0; j < splitDocument.length; j++) {
								logger.info("in for loopo ----------"
										+ splitDocument[j]);
								ReimbursementClmaimApplication innerbean = new ReimbursementClmaimApplication();
								innerbean.setUploadDoc(splitDocument[j]);
								innerbean
										.setUploadDocPath(splitDocumentPath[j]);
								arraylist.add(innerbean);
							}
							localbean.setIttUploadList(arraylist);
						}

					}

					list.add(localbean);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean deleteData(String[] srNo, String[] proofName,
			String[] reimbursementCrediCodeItt,
			String[] reimbursementCrediNameItt, String[] expenseDateItt,
			String[] claimAmountItt, String[] uploadDoc, String[] documentPath, String[] billAmountItt,
			ReimbursementClmaimApplication bean) {
		boolean result = false;
		try {

			ArrayList<Object> att = new ArrayList<Object>();
			if (srNo != null && srNo.length > 0) {
				 
				for (int i = 0; i < srNo.length; i++) {
					ReimbursementClmaimApplication localbean = new ReimbursementClmaimApplication();
					localbean
							.setReimbursementCrediCodeItt(reimbursementCrediCodeItt[i]);
					localbean
							.setReimbursementCrediNameItt(reimbursementCrediNameItt[i]);
					localbean.setExpenseDateItt(expenseDateItt[i]);
					localbean.setClaimAmountItt(claimAmountItt[i]);
					localbean.setUploadDoc(uploadDoc[i]);
					localbean.setUploadDocPath(documentPath[i]);
					localbean.setBillAmountItt(billAmountItt[i]);
					if (uploadDoc != null && uploadDoc.length > 0) {

						if (uploadDoc[i] != null
								&& !uploadDoc[i].equals("null")
								&& !uploadDoc[i].equals("")) {
							String[] splitDocument = uploadDoc[i].split(",");
							String[] splitDocumentPath = documentPath[i]
									.split(",");
							ArrayList arraylist = new ArrayList();
							logger.info("splitDocument" + splitDocument.length);
							for (int j = 0; j < splitDocument.length; j++) {
								logger.info("in for loopo ----------"
										+ splitDocument[j]);
								ReimbursementClmaimApplication innerbean = new ReimbursementClmaimApplication();
								innerbean.setUploadDoc(splitDocument[j]);
								innerbean
										.setUploadDocPath(splitDocumentPath[j]);
								arraylist.add(innerbean);
							}
							localbean.setIttUploadList(arraylist);
						}

					}
					att.add(localbean);
					result = true;
				}

				att.remove(Integer.parseInt(bean.getCheckEdit()) - 1);

			}

			bean.setList(att);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean save(String[] srNo, String[] proofName,
			String[] reimbursementCrediCodeItt,
			String[] reimbursementCrediNameItt, String[] expenseDateItt,
			String[] claimAmountItt, String[] uploadDoc, String status,
			String[] documentPath,String []billAmountItt, ReimbursementClmaimApplication bean) {
		boolean result = false;
		try {
			String applicationStatus = "N";
			String adminStatus = "N";
			String managerId = "";
			String adminId = "";

			if (status.equals("P")) {
				Object[][] statusObj = getStatus(bean.getHiddenCreditCode(),
						bean.getUserEmpId());
				if (statusObj != null && statusObj.length > 0) {
					applicationStatus = String.valueOf(statusObj[0][0]);
					adminStatus = checkNull(String.valueOf(statusObj[0][1]));
					managerId = checkNull(String.valueOf(statusObj[0][2]));
					//adminId = String.valueOf(statusObj[0][3]);
				}
			}

			Object saveObj[][] = new Object[1][10];
			String maxIdQuery = " select nvl(max(REIMB_CLAIM_ID),0)+1 from HRMS_REIMB_APPLICATION ";
			Object maxIdData[][] = getSqlModel().getSingleResult(maxIdQuery);
			String maxId= String.valueOf(maxIdData[0][0]);
			saveObj[0][0] = maxId;
			saveObj[0][1] = bean.getHiddenCreditCode();
			saveObj[0][2] = bean.getUserEmpId();
			saveObj[0][3] = bean.getComments();
			saveObj[0][4] = applicationStatus;
			saveObj[0][5] = bean.getTotalAmt();
			saveObj[0][6] = managerId;
			saveObj[0][7] = adminId;
			saveObj[0][8] = adminStatus;
			saveObj[0][9] = bean.getDivisionCode();
			
			String insertQuery = " INSERT INTO HRMS_REIMB_APPLICATION ( REIMB_CLAIM_ID, REIMB_CREDIT_CODE, REIMB_EMP_ID, "
					+ " REIMB_APPL_DATE, REIMB_CLAIM_PARTICULARS,  REIMB_APPL_STATUS, REIMB_TOTAL_AMOUNT, "
					+ " REIMB_CLAIM_APPROVER, REIMB_APPROVAL_LEVEL, REIMB_ADMIN_ID, "
					+ " REIMB_ADMIN_STATUS ,REIMB_DIVISION) VALUES ( "
					+ " ?, ?, ?, sysdate, ?, ? "
					+ " , ?, ?,  1, ?, ?,?) ";

			result = getSqlModel().singleExecute(insertQuery, saveObj);

			if (result) {
				bean.setHiddenApplicationCode(String.valueOf(maxId));

				String insertDtlQuery = " INSERT INTO HRMS_REIMB_APPL_DTL ( REIMB_CLAIM_ID, REIMB_REFERENCE_ID, REIMB_EXPENSE_DATE , "
						+ " REIMB_CLAIM_AMOUNT, REIMB_PROOF,REIMB_BILL_AMOUNT,REIMB_CREDIT_CODE ) VALUES ( "
						+ " ? , ? , TO_DATE(?,'DD-MM-YYYY') ,? , ?, ?, ? )";

				if (reimbursementCrediCodeItt != null
						&& reimbursementCrediCodeItt.length > 0) {
					Object saveDtlObj[][] = new Object[reimbursementCrediCodeItt.length][7];

					for (int i = 0; i < reimbursementCrediCodeItt.length; i++) {
						saveDtlObj[i][0] = maxId;
						saveDtlObj[i][1] = documentPath[i];
						saveDtlObj[i][2] = expenseDateItt[i];
						saveDtlObj[i][3] = claimAmountItt[i];
						saveDtlObj[i][4] = uploadDoc[i];
						saveDtlObj[i][5] = billAmountItt[i];
						saveDtlObj[i][6] = reimbursementCrediCodeItt[i];
					}


					result = getSqlModel().singleExecute(insertDtlQuery,
							saveDtlObj);

					try {

						if (result) {

							if (bean.getReferenceId().equals("")) {
								ApplCodeTemplateModel model = new ApplCodeTemplateModel();
								model.initiate(context, session);
								String applnCode = model
										.generateApplicationCode(bean
												.getHiddenApplicationCode(),
												"REIMB", bean.getUserEmpId(),
												bean.getClaimDate());
								logger.info("final appln code in application=="
										+ applnCode);
								bean.setReferenceId(applnCode);
								result = getSqlModel()
										.singleExecute(
												"UPDATE  HRMS_REIMB_APPLICATION SET REIMB_APPL_CODE='"
														+ applnCode
														+ "' WHERE REIMB_CLAIM_ID="
														+ bean
																.getHiddenApplicationCode());
								model.terminate();
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (result) {
						result = updateReimbursementBalance(bean,status);
						
						if(applicationStatus.equals("D") || applicationStatus.equals("C")){
							/**
							 * Update pre-approved bills
							 */
							String creditId=bean.getHiddenCreditCode();
							String empCode =bean.getUserEmpId(); 
							String claimId=bean.getHiddenApplicationCode();
							
							Object claimAmtObj[][]=getSqlModel().getSingleResult("SELECT SUM(NVL(REIMB_CLAIM_AMOUNT,0)),SUM(NVL(REIMB_BILL_AMOUNT,0)) FROM HRMS_REIMB_APPL_DTL WHERE REIMB_CLAIM_ID="+claimId);
							String preApprovedDtlQuery= "SELECT REIMB_PRE_APPPROVED_AMT, NVL(REIMB_PRE_APPPROVED_AMT_DTL,'') FROM HRMS_REIMB_BALANCE "
								+" WHERE REIMB_CREDIT_CODE="+creditId+" AND REIMB_EMP_ID="+empCode;
							
							Object [][]preApprovedDtlObj=getSqlModel().getSingleResult(preApprovedDtlQuery) ;
							String preApprovedDtl="";
							double preApprovedAmt=0;
							preApprovedDtl = Utility.checkNull(String.valueOf(preApprovedDtlObj[0][1]));
							try {
								preApprovedAmt = Double
										.parseDouble(Utility.checkNull(String
												.valueOf(preApprovedDtlObj[0][0])));
							} catch (Exception e) {
								// TODO: handle exception
							}
							double diffAmt=0;
							double totalBillAmt=0;
							
							totalBillAmt=Double.parseDouble(String.valueOf(claimAmtObj[0][1]))+
										preApprovedAmt;
							diffAmt=totalBillAmt-
									Double.parseDouble(String.valueOf(claimAmtObj[0][0]))-preApprovedAmt;
							
							if(diffAmt>Double.parseDouble(String.valueOf(claimAmtObj[0][1]))){
								if(diffAmt>preApprovedAmt){
								if(preApprovedDtl.equals("")){
									preApprovedDtl+=claimId+"#"+diffAmt;
								}else{
									preApprovedDtl+=","+claimId+"#"+diffAmt;
								}
								}
							}else if(diffAmt<Double.parseDouble(String.valueOf(claimAmtObj[0][1]))){
								
									preApprovedDtl+=claimId+"#"+diffAmt;
								
							}
							else if(diffAmt==0){
								preApprovedDtl ="";
							}
							
							String updatePreApprovedDtlQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_PRE_APPPROVED_AMT_DTL=? WHERE REIMB_CREDIT_CODE=? AND REIMB_EMP_ID=?";
							Object updatePreApprovedDtlObj[][]=new Object [1][3];
							updatePreApprovedDtlObj[0][0]= preApprovedDtl;
							updatePreApprovedDtlObj[0][1]= creditId;
							updatePreApprovedDtlObj[0][2]= empCode;
							
							getSqlModel().singleExecute(updatePreApprovedDtlQuery,updatePreApprovedDtlObj);
							/**
							 * End Update pre-approved bills
							 */
							
							if(applicationStatus.equals("C")){				// if accountant is not required
								/**
								 * Update onhold amount from HRMS_REIMB_BALANCE
								 */
								String updateOnholdQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_ONHOLD_AMT=REIMB_ONHOLD_AMT-? WHERE REIMB_CREDIT_CODE=? AND REIMB_EMP_ID=?";
								Object updateOnholdObj[][]=new Object[1][3];
								updateOnholdObj[0][0]= String.valueOf(claimAmtObj[0][0]);						
								updateOnholdObj[0][1]= creditId;
								updateOnholdObj[0][2]= empCode;
									getSqlModel().singleExecute(updateOnholdQuery,updateOnholdObj);
								}
								/**
								 * end Update onhold amount
								 */
						}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getAllTypeOfApplications(ReimbursementClmaimApplication bean,
			HttpServletRequest request, String userEmpId,String status) {
		// TODO Auto-generated method stub
		try {

			String query = " SELECT DISTINCT HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID,TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY') ,REIMB_TOTAL_AMOUNT "
					+ " ,DECODE(REIMB_APPL_STATUS,'N','Draft','P','Pending For Manager Approval','A','Pending For Admin Approval','R','Rejected','B','Sent Back','D','Pendig for disbursement','S','Pendig for disbursement','C','Closed') "
					+ " ,REIMB_EMP_ID ,REIMB_APPL_STATUS ,REIMB_APPL_CODE ,CREDIT_NAME FROM HRMS_REIMB_APPLICATION "
					+" INNER JOIN HRMS_REIMB_APPL_DTL ON(HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
					+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE )"
					+ " WHERE REIMB_EMP_ID="
					+ userEmpId;
				if(status.equals("C")){
					query +=  " AND REIMB_APPL_STATUS ='C'";
				}else {
					query +=  " AND REIMB_APPL_STATUS !='C'";
				}
				query += " ORDER BY TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY') DESC,REIMB_APPL_STATUS ";

			Object data[][] = getSqlModel().getSingleResult(query);
			ArrayList list = new ArrayList();
			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					ReimbursementClmaimApplication innerbean = new ReimbursementClmaimApplication();
					innerbean.setApplicationCodeItt(checkNull(String
							.valueOf(data[i][0])));
					innerbean.setApplicationDateItt(checkNull(String
							.valueOf(data[i][1])));
					innerbean.setClaimAmtItt(checkNull(String
							.valueOf(data[i][2])));
					innerbean.setStatusNameItt(checkNull(String
							.valueOf(data[i][3])));
					innerbean
							.setEmpIdItt(checkNull(String.valueOf(data[i][4])));
					innerbean
							.setStatusItt(checkNull(String.valueOf(data[i][5])));
					innerbean.setReimCodeItt(checkNull(String
							.valueOf(data[i][6])));
					
					innerbean.setCreditNameItt(checkNull(String
							.valueOf(data[i][7])));
					

					list.add(innerbean);
				}
				bean.setDraftList(list);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getDetails(ReimbursementClmaimApplication bean, String appCode) {
		try {

			ArrayList<Object> att = new ArrayList<Object>();

			String query = "  SELECT TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY') , HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE,CREDIT_NAME,REIMB_TOTAL_AMOUNT  ,DECODE(REIMB_APPL_STATUS,'N','DRAFT','P','PENDING FOR MANAGER APPROVAL','A','PENDING FOR ADMIN APPROVAL','R','REJECTED','B','SENT BACK','D','PENDIG FOR DISBURSEMENT','S','Pendig for disbursement')  ,"
					+" REIMB_EMP_ID ,REIMB_CLAIM_PARTICULARS ,DIV_NAME ,REIMB_DIVISION ,CASE WHEN REIMB_APPL_STATUS IN ('B','N') THEN NVL(REIMB_BALANCE_AMT,0)+REIMB_TOTAL_AMOUNT ELSE NVL(REIMB_BALANCE_AMT,0) END AS BAL_AMT,REIMB_APPL_STATUS,NVL(REIMB_PRE_APPPROVED_AMT,0) "
					+" ,REIMB_ADMIN_STATUS ,HRMS_EMP_OFFC.EMP_TOKEN, " +
					"(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) EMP_NAME, " +
					"HRMS_CENTER.CENTER_NAME, HRMS_DEPT.DEPT_NAME, HRMS_RANK.RANK_NAME,  " +
					"NVL(hrms_Cadre.CADRE_NAME,'') ,REIMB_CLAIM_ID FROM HRMS_REIMB_APPLICATION  "
					+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REIMB_APPLICATION.REIMB_EMP_ID)  "
					+" LEFT JOIN HRMS_REIMB_APPL_DTL ON(HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID) "
					+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE) " 
					+" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_REIMB_APPLICATION.REIMB_DIVISION) "
					+" LEFT JOIN HRMS_REIMB_BALANCE ON (HRMS_REIMB_BALANCE.REIMB_CREDIT_CODE= HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE AND HRMS_REIMB_BALANCE.REIMB_EMP_ID="+bean.getUserEmpId()+") "
					+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) 	"
					+"  LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER )  "	 
					+"  LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )  	"
					+"  LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )  	 "
					+"   LEFT JOIN hrms_Cadre on(hrms_Cadre.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
					+" WHERE HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID="+appCode;

			Object data[][] = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				bean.setClaimDate(checkNull(String.valueOf(data[0][0])));
				bean.setSysdate(checkNull(String.valueOf(data[0][0])));
				bean.setReimbursementCrediCode(checkNull(String
						.valueOf(data[0][1])));
				bean.setHiddenCreditCode(checkNull(String.valueOf(data[0][1])));
				bean.setReimbursementCrediName(checkNull(String
						.valueOf(data[0][2])));
				bean.setComments(checkNull(String.valueOf(data[0][6])));
				bean.setDivision(checkNull(String.valueOf(data[0][7])));
				bean.setDivisionCode(checkNull(String.valueOf(data[0][8])));
				bean.setReimbursBalance(checkNull(String.valueOf(data[0][9])));
				bean.setApplStatus(checkNull(String.valueOf(data[0][10])));
				bean.setOnHoldBalance(checkNull(String.valueOf(data[0][3])));
				bean.setHiddenApplicationCode(appCode);
				
				bean.setAdminStatus(checkNull(String.valueOf(data[0][12])));
				bean.setEmpId(checkNull(String.valueOf(data[0][5])));
				bean.setEmpToken(checkNull(String.valueOf(data[0][13])));
				bean.setEmpName(checkNull(String.valueOf(data[0][14])));
				bean.setBranch(checkNull(String.valueOf(data[0][15])));
				bean.setDesignation(checkNull(String.valueOf(data[0][17])));
				bean.setGrade(checkNull(String.valueOf(data[0][18])));
				
				bean.setReferenceId(checkNull(String.valueOf(data[0][19])));
				
			}

			double minBillAmount=0;
			System.out.println("data[0][9]===="+data[0][9]);
			System.out.println("data[0][11]===="+data[0][11]);
			minBillAmount= Double.parseDouble(checkNull(String.valueOf(data[0][9])))-Double.parseDouble(checkNull(String.valueOf(data[0][11])));
			if(minBillAmount<0)
				minBillAmount=0;
			bean.setMinBillAmount(String.valueOf(minBillAmount));
			bean.setBillsCarriedForward(checkNull(String.valueOf(data[0][11])));
			
			String dtlQuery = " SELECT HRMS_REIMB_APPL_DTL .REIMB_REFERENCE_ID,TO_CHAR(REIMB_EXPENSE_DATE,'DD-MM-YYYY') "
					+ " ,REIMB_CLAIM_AMOUNT,REIMB_PROOF ,HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE,CREDIT_NAME,NVL(REIMB_BILL_AMOUNT,0) FROM HRMS_REIMB_APPL_DTL "
					//+ " INNER JOIN HRMS_REIMB_APPLICATION  ON (HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID = HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID ) "
					+ " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE)"
					+ " WHERE HRMS_REIMB_APPL_DTL .REIMB_CLAIM_ID=" + appCode;

			Object dtldata[][] = getSqlModel().getSingleResult(dtlQuery);

			if (dtldata != null && dtldata.length > 0) {
				for (int i = 0; i < dtldata.length; i++) {
					ReimbursementClmaimApplication localbean = new ReimbursementClmaimApplication();
					localbean.setReimbursementCrediCodeItt(checkNull(String
							.valueOf(dtldata[i][4])));
					localbean.setReimbursementCrediNameItt(checkNull(String
							.valueOf(dtldata[i][5])));
					localbean.setExpenseDateItt(checkNull(String
							.valueOf(dtldata[i][1])));
					localbean.setClaimAmountItt(checkNull(String
							.valueOf(dtldata[i][2])));
					localbean.setUploadDocPath(checkNull(String
							.valueOf(dtldata[i][0])));
					localbean.setUploadDoc(checkNull(String
							.valueOf(dtldata[i][3])));
					localbean.setBillAmountItt(checkNull(String
							.valueOf(dtldata[i][6])));
					ArrayList list = new ArrayList();
					if (String.valueOf(dtldata[i][1]) != null
							&& !String.valueOf(dtldata[i][1]).equals("null")) {
						String[] splitDocumentPath = localbean
								.getUploadDocPath().split(",");
						String[] splitDocument = localbean.getUploadDoc()
								.split(",");

						// logger.info("splitDocument" + splitDocument.length);
						for (int j = 0; j < splitDocument.length; j++) {
							// logger.info("in for loopo ----------"
							// + splitDocument[j]);
							ReimbursementClmaimApplication innerbean = new ReimbursementClmaimApplication();
							innerbean.setUploadDocPath(splitDocumentPath[j]);
							innerbean.setUploadDoc(splitDocument[j]);
							list.add(innerbean);
						}
						localbean.setIttUploadList(list);

					}
					att.add(localbean);
				}

			}
			displayPreApprovedBills(bean);
			bean.setList(att);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean update(String[] srNo, String[] proofName,
			String[] reimbursementCrediCodeItt,
			String[] reimbursementCrediNameItt, String[] expenseDateItt,
			String[] claimAmountItt, String[] uploadDoc, String status,
			String[] documentPath,String []billAmountItt, ReimbursementClmaimApplication bean) {
		// TODO Auto-generated method stub
		boolean result = false;

		try {

			String applicationStatus = "N";
			String adminStatus = "N";
			String managerId = "";
			String adminId = "";
		 
			if (status.equals("P")) {
				Object[][] statusObj = getStatus(bean.getHiddenCreditCode(),
						bean.getUserEmpId());
				 
				if (statusObj != null && statusObj.length > 0) {
				 
					applicationStatus = String.valueOf(statusObj[0][0]);
					adminStatus = checkNull(String.valueOf(statusObj[0][1]));
					managerId = checkNull(String.valueOf(statusObj[0][2]));
					//adminId = String.valueOf(statusObj[0][3]);
				}

			}

			Object updateObj[][] = new Object[1][8];

			updateObj[0][0] = bean.getClaimDate();
			updateObj[0][1] = bean.getComments();
			updateObj[0][2] = applicationStatus;
			updateObj[0][3] = bean.getTotalAmt();
			updateObj[0][4] = managerId;
			updateObj[0][5] = adminId;
			updateObj[0][6] = adminStatus;
			updateObj[0][7] = bean.getDivisionCode();

			 

			String updateQuery = " update HRMS_REIMB_APPLICATION set REIMB_APPL_DATE=to_DATE(?,'DD-MM-YYYY'),REIMB_CLAIM_PARTICULARS=? "
					+ " ,REIMB_APPL_STATUS=?,REIMB_TOTAL_AMOUNT=?,REIMB_CLAIM_APPROVER=? "
					+ " ,REIMB_ADMIN_ID=?,REIMB_ADMIN_STATUS=?	,REIMB_DIVISION=?	"
					+ " where  REIMB_CLAIM_ID="
					+ bean.getHiddenApplicationCode();
			result = getSqlModel().singleExecute(updateQuery, updateObj);

			if (result) {

				String delquery = "  delete from HRMS_REIMB_APPL_DTL "
						+ " where REIMB_CLAIM_ID="
						+ bean.getHiddenApplicationCode();
				result = getSqlModel().singleExecute(delquery);

				if (result) {
					String insertDtlQuery = " INSERT INTO HRMS_REIMB_APPL_DTL ( REIMB_CLAIM_ID, REIMB_REFERENCE_ID, REIMB_EXPENSE_DATE , "
							+ " REIMB_CLAIM_AMOUNT, REIMB_PROOF,REIMB_BILL_AMOUNT,REIMB_CREDIT_CODE ) VALUES ( "
							+ " ? , ? , TO_DATE(?,'DD-MM-YYYY') ,? , ?, ? ,? )";

					if (reimbursementCrediCodeItt != null
							&& reimbursementCrediCodeItt.length > 0) {
						Object saveDtlObj[][] = new Object[reimbursementCrediCodeItt.length][7];

						for (int i = 0; i < reimbursementCrediCodeItt.length; i++) {
							saveDtlObj[i][0] = bean.getHiddenApplicationCode();
							saveDtlObj[i][1] = documentPath[i];
							saveDtlObj[i][2] = expenseDateItt[i];
							saveDtlObj[i][3] = claimAmountItt[i];
							saveDtlObj[i][4] = uploadDoc[i];
							saveDtlObj[i][5] = billAmountItt[i];
							saveDtlObj[i][6] = reimbursementCrediCodeItt[i];
						}

						 
						result = getSqlModel().singleExecute(insertDtlQuery,
								saveDtlObj);

						try {
							if (result) {
								if (bean.getReferenceId().equals("")) {
									ApplCodeTemplateModel model = new ApplCodeTemplateModel();
									model.initiate(context, session);
									String applnCode = model
											.generateApplicationCode(
													bean
															.getHiddenApplicationCode(),
													"REIMB", bean
															.getUserEmpId(),
													bean.getClaimDate());
									logger
											.info("final appln code in application=="
													+ applnCode);
									bean.setReferenceId(applnCode);
									result = getSqlModel()
											.singleExecute(
													"UPDATE  HRMS_REIMB_APPLICATION SET REIMB_APPL_CODE='"
															+ applnCode
															+ "' WHERE REIMB_CLAIM_ID="
															+ bean
																	.getHiddenApplicationCode());
									model.terminate();
								}

							}

						} catch (Exception e) {
							// TODO: handle exception
						}

						if (result) {
							result = updateReimbursementBalance(bean,status);
							
							if(applicationStatus.equals("D") || applicationStatus.equals("C")){
								/**
								 * Update pre-approved bills
								 */
								String creditId=bean.getHiddenCreditCode();
								String empCode =bean.getUserEmpId(); 
								String claimId=bean.getHiddenApplicationCode();
								
								Object claimAmtObj[][]=getSqlModel().getSingleResult("SELECT SUM(NVL(REIMB_CLAIM_AMOUNT,0)),SUM(NVL(REIMB_BILL_AMOUNT,0)) FROM HRMS_REIMB_APPL_DTL WHERE REIMB_CLAIM_ID="+claimId);
								String preApprovedDtlQuery= "SELECT REIMB_PRE_APPPROVED_AMT, NVL(REIMB_PRE_APPPROVED_AMT_DTL,'') FROM HRMS_REIMB_BALANCE "
									+" WHERE REIMB_CREDIT_CODE="+creditId+" AND REIMB_EMP_ID="+empCode;
								
								Object [][]preApprovedDtlObj=getSqlModel().getSingleResult(preApprovedDtlQuery) ;
								String preApprovedDtl="";
								double preApprovedAmt=0;
								preApprovedDtl = Utility.checkNull(String.valueOf(preApprovedDtlObj[0][1]));
								try {
									preApprovedAmt = Double
											.parseDouble(Utility.checkNull(String
													.valueOf(preApprovedDtlObj[0][0])));
								} catch (Exception e) {
									// TODO: handle exception
								}
								double diffAmt=0;
								double totalBillAmt=0;
								
								totalBillAmt=Double.parseDouble(String.valueOf(claimAmtObj[0][1]))+
											preApprovedAmt;
								diffAmt=totalBillAmt-
										Double.parseDouble(String.valueOf(claimAmtObj[0][0]))-preApprovedAmt;
								
								if(diffAmt>Double.parseDouble(String.valueOf(claimAmtObj[0][1]))){
									if(diffAmt>preApprovedAmt){
									if(preApprovedDtl.equals("")){
										preApprovedDtl+=claimId+"#"+diffAmt;
									}else{
										preApprovedDtl+=","+claimId+"#"+diffAmt;
									}
									}
								}else if(diffAmt<Double.parseDouble(String.valueOf(claimAmtObj[0][1]))){
									
										preApprovedDtl+=claimId+"#"+diffAmt;
									
								}
								else if(diffAmt==0){
									preApprovedDtl ="";
								}
								
								String updatePreApprovedDtlQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_PRE_APPPROVED_AMT_DTL=? WHERE REIMB_CREDIT_CODE=? AND REIMB_EMP_ID=?";
								Object updatePreApprovedDtlObj[][]=new Object [1][3];
								updatePreApprovedDtlObj[0][0]= preApprovedDtl;
								updatePreApprovedDtlObj[0][1]= creditId;
								updatePreApprovedDtlObj[0][2]= empCode;
								
								getSqlModel().singleExecute(updatePreApprovedDtlQuery,updatePreApprovedDtlObj);
								/**
								 * End Update pre-approved bills
								 */
								
								if(applicationStatus.equals("C")){				// if accountant is not required
									/**
									 * Update onhold amount from HRMS_REIMB_BALANCE
									 */
									String updateOnholdQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_ONHOLD_AMT=REIMB_ONHOLD_AMT-? WHERE REIMB_CREDIT_CODE=? AND REIMB_EMP_ID=?";
									Object updateOnholdObj[][]=new Object[1][3];
									updateOnholdObj[0][0]= String.valueOf(claimAmtObj[0][0]);						
									updateOnholdObj[0][1]= creditId;
									updateOnholdObj[0][2]= empCode;
										getSqlModel().singleExecute(updateOnholdQuery,updateOnholdObj);
									}
									/**
									 * end Update onhold amount
									 */
							}
					}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return result;
	}

	public Object[][] getStatus(String creditCode, String empCode) {
		Object[][] configObj = null;
		try {
			String query = " SELECT IS_MANAGER_APPROVAL,IS_ADMIN_APPROVAL,REIMB_ADMIN,NVL(IS_ACCOUNTANT_APPROVAL,'N') FROM HRMS_REIMB_CONFIG "
					+ " WHERE REIMB_CREDIT_HEAD=" + creditCode;
			Object statusObj[][] = getSqlModel().getSingleResult(query);
			if (statusObj != null && statusObj.length > 0) {
				configObj = new Object[1][5];
				if (String.valueOf(statusObj[0][0]).equals("Y")
						&& String.valueOf(statusObj[0][1]).equals("Y")) {
					configObj[0][0] = "P"; // application status
					configObj[0][1] = "P"; // admin status
					configObj[0][2] = generateEmpFlow(empCode, "REIMB", 1)[0][0];// MANAGEER
					// id
					configObj[0][3] = String.valueOf(statusObj[0][2]);
					
				}
				if (String.valueOf(statusObj[0][0]).equals("Y")
						&& String.valueOf(statusObj[0][1]).equals("N")) {
					configObj[0][0] = "P";
					configObj[0][1] = "A";
					configObj[0][2] = generateEmpFlow(empCode, "REIMB", 1)[0][0];
					configObj[0][3] = "";
				}
				if (String.valueOf(statusObj[0][0]).equals("N")
						&& String.valueOf(statusObj[0][1]).equals("Y")) {
					configObj[0][0] = "A";
					configObj[0][1] = "P";
					configObj[0][2] = "";
					configObj[0][3] = String.valueOf(statusObj[0][2]);
				}
				if (String.valueOf(statusObj[0][0]).equals("N")
						&& String.valueOf(statusObj[0][1]).equals("N")) {
					if(String.valueOf(statusObj[0][3]).equals("Y")){			// ACCOUNTANT REQUIRED)
						configObj[0][0] = "D";					// if accountant is req. then appl status is pending for disbursement		
					
					}else{
						configObj[0][0] = "C";					// if accountant is not req. then appl status is closed
					}
					configObj[0][1] = "A";
					configObj[0][2] = "";
					configObj[0][3] = "";
					
				}
				
				 
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return configObj;
	}

	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		Object result[][] = null;
		try {
			ReportingModel reporting = new ReportingModel();
			reporting.initiate(context, session);
			result = reporting.generateEmpFlow(empCode, type, order);
			reporting.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateReimbursementBalance(ReimbursementClmaimApplication reimbursementClaim,String status) {
		boolean result = false;
		Object[][] updateObj = new Object[1][6];
		if(status.equals("P")){
			updateObj[0][0] = reimbursementClaim.getOnHoldBalance();
			updateObj[0][1] = reimbursementClaim.getTotalAmt();
		}else if(status.equals("C")||status.equals("N")){
				updateObj[0][0] ="0";
				updateObj[0][1] ="0";
		}
		updateObj[0][2] = reimbursementClaim.getOnHoldBalance();
		updateObj[0][3] = reimbursementClaim.getTotalAmt();
		updateObj[0][4] = reimbursementClaim.getHiddenCreditCode();
		updateObj[0][5] = reimbursementClaim.getUserEmpId();
		try {
			String updateQuery ="";
			 updateQuery = " UPDATE HRMS_REIMB_BALANCE SET  REIMB_ONHOLD_AMT= (NVL(REIMB_ONHOLD_AMT,0)-?+?), REIMB_BALANCE_AMT=REIMB_BALANCE_AMT+?-? "
				+ " WHERE REIMB_CREDIT_CODE=? AND REIMB_EMP_ID=?" ;
						
			result = getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getReimbursementHistory(String userEmpId,
			ReimbursementClmaimApplication reimbursementClaim) {
		// TODO Auto-generated method stub
		try {
			String query = "";
			if (reimbursementClaim.getLevstatus().equals("A")) {
				query = " SELECT REIMB_APPL_CODE,CREDIT_NAME,TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY') ,SUM(REIMB_CLAIM_AMOUNT) "
						+ "	,DECODE(REIMB_APPL_STATUS,'N','Draft','P','Pending For Manager Approval','A','Pending For Admin Approval','R','Rejected','B','Sent Back','C','Closed','D','Pendig for disbursement','S','Pendig for disbursement') "
						+ "	,SUM(HRMS_REIMB_APPL_DTL.REIMB_APPROVED_AMOUNT) FROM HRMS_REIMB_APPLICATION "
						+" INNER JOIN HRMS_REIMB_APPL_DTL ON(HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
						+ "	INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE ) "
						+ " WHERE REIMB_EMP_ID="
						+ userEmpId
						+ "	AND HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE="
						+ reimbursementClaim.getHistoryCreditCode().trim()
						+" GROUP BY REIMB_APPL_CODE,CREDIT_NAME,REIMB_APPL_DATE,REIMB_APPL_STATUS"
						+ " ORDER BY REIMB_APPL_DATE DESC";
			} else if (reimbursementClaim.getLevstatus().equals("CM")) {
				query = " SELECT REIMB_APPL_CODE,CREDIT_NAME,TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY') ,SUM(REIMB_CLAIM_AMOUNT) "
						+ "	,DECODE(REIMB_APPL_STATUS,'N','Draft','P','Pending For Manager Approval','A','Pending For Admin Approval','R','Rejected','B','Sent Back','C','Closed','D','Pendig for disbursement','S','Pendig for disbursement') "
						+ "	,SUM(HRMS_REIMB_APPL_DTL.REIMB_APPROVED_AMOUNT) FROM HRMS_REIMB_APPLICATION "
						+" INNER JOIN HRMS_REIMB_APPL_DTL ON(HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
						+ "	INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE ) "
						+ " WHERE REIMB_EMP_ID="
						+ userEmpId
						+ "	AND HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE="
						+ reimbursementClaim.getHistoryCreditCode().trim()
						+ " AND TO_CHAR(REIMB_APPL_DATE,'MM')=TO_CHAR(SYSDATE,'MM')"
						+" GROUP BY REIMB_APPL_CODE,CREDIT_NAME,REIMB_APPL_DATE,REIMB_APPL_STATUS"
						+ " ORDER BY REIMB_APPL_DATE DESC ";
			} else if (reimbursementClaim.getLevstatus().equals("CY")) {
				query = " SELECT REIMB_APPL_CODE,CREDIT_NAME,TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY') ,SUM(REIMB_CLAIM_AMOUNT) "
						+ "	,DECODE(REIMB_APPL_STATUS,'N','Draft','P','Pending For Manager Approval','A','Pending For Admin Approval','R','Rejected','B','Sent Back','C','Closed','D','Pendig for disbursement','S','Pendig for disbursement') "
						+ "	,SUM(HRMS_REIMB_APPL_DTL.REIMB_APPROVED_AMOUNT) FROM HRMS_REIMB_APPLICATION "
						+" INNER JOIN HRMS_REIMB_APPL_DTL ON(HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
						+ "	INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE ) "
						+ " WHERE REIMB_EMP_ID="
						+ userEmpId
						+ "	AND HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE="
						+ reimbursementClaim.getHistoryCreditCode().trim()
						+ " AND  TO_CHAR(REIMB_APPL_DATE,'YYYY')=TO_CHAR(SYSDATE,'YYYY')"
						+" GROUP BY REIMB_APPL_CODE,CREDIT_NAME,REIMB_APPL_DATE,REIMB_APPL_STATUS"
						+ " ORDER BY REIMB_APPL_DATE DESC ";
			} else {
				query = " SELECT REIMB_APPL_CODE,CREDIT_NAME,TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY') ,SUM(REIMB_CLAIM_AMOUNT) "
						+ "	,DECODE(REIMB_APPL_STATUS,'N','Draft','P','Pending For Manager Approval','A','Pending For Admin Approval','R','Rejected','B','Sent Back','C','Closed','D','Pendig for disbursement','S','Pendig for disbursement') "
						+ "	,SUM(HRMS_REIMB_APPL_DTL.REIMB_APPROVED_AMOUNT) FROM HRMS_REIMB_APPLICATION "
						+" INNER JOIN HRMS_REIMB_APPL_DTL ON(HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
						+ "	INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE ) "
						+ " WHERE REIMB_EMP_ID="
						+ userEmpId
						+ "	AND HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE="
						+ reimbursementClaim.getHistoryCreditCode().trim()
						+ " AND TO_CHAR(REIMB_APPL_DATE,'MM')=TO_CHAR(SYSDATE,'MM')"
						+" GROUP BY REIMB_APPL_CODE,CREDIT_NAME,REIMB_APPL_DATE,REIMB_APPL_STATUS"
						+ "    ORDER BY REIMB_APPL_DATE DESC ";
			}

			Object result[][] = getSqlModel().getSingleResult(query);

			ArrayList list = new ArrayList();

			if (result != null && result.length > 0) {

				for (int i = 0; i < result.length; i++) {
					ReimbursementClmaimApplication bean = new ReimbursementClmaimApplication();
					bean.setApplicationId(checkNull(String
							.valueOf(result[i][0])));
					bean.setReimbursementHead(checkNull(String
							.valueOf(result[i][1])));
					bean.setClaimHistoryDate(checkNull(String
							.valueOf(result[i][2])));
					bean.setClaimHistoryAmount(checkNull(String
							.valueOf(result[i][3])));
					bean.setClaimHistoryStatus(checkNull(String
							.valueOf(result[i][4])));
					bean.setClaimHistoryApprovedAmount(checkNull(String
							.valueOf(result[i][5])));
					list.add(bean);
				}
				reimbursementClaim.setClaimHistory(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean setApproverComments(
			ReimbursementClmaimApplication reimbursementClaim) {

		boolean result = false;
		try {
			String approverCommentQuery = " SELECT EMP_TOKEN,(EMP_FNAME||' '|| EMP_LNAME ||' ')	,TO_CHAR(REIMB_APPROVAL_DATE,'DD-MM-YYYY HH12:MI PM') "
					+ " ,DECODE(REIMB_APPROVAL_STATUS,'N','Draft','P','Pending For Manager Approval','A','Approved','R','Rejected','B','Sent Back') "
					+ " ,REIMB_APPROVER_COMMENTS  "
					+ "  FROM HRMS_REIMB_APPL_PATH "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_REIMB_APPL_PATH.REIMB_APPROVER= HRMS_EMP_OFFC.EMP_ID) "
					+ " WHERE REIMB_CLAIM_ID="
					+ reimbursementClaim.getHiddenApplicationCode();

			Object approverCommentObj[][] = getSqlModel().getSingleResult(
					approverCommentQuery);
			if (approverCommentObj != null && approverCommentObj.length > 0) {
				ArrayList arrList = new ArrayList();
				for (int i = 0; i < approverCommentObj.length; i++) {
					ReimbursementClmaimApplication bean = new ReimbursementClmaimApplication();
					bean.setPrevApproverID(checkNull(String
							.valueOf(approverCommentObj[i][0])));
					bean.setPrevApproverName(checkNull(String
							.valueOf(approverCommentObj[i][1])));
					bean.setPrevApproverDate(checkNull(String
							.valueOf(approverCommentObj[i][2])));
					bean.setPrevApproverStatus(checkNull(String
							.valueOf(approverCommentObj[i][3])));
					bean.setPrevApproverComment(checkNull(String
							.valueOf(approverCommentObj[i][4])));
					/*
					 * String srNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
					 * leaveBean.setAppSrNo(srNo);
					 */
					arrList.add(bean);

					result = true;
				}
				reimbursementClaim.setApproverCommentList(arrList);
				reimbursementClaim.setPrevAppCommentListFlag("true");
			}
		} catch (Exception e) {
			logger.error("Exception in setApproverComments------" + e);
		}
		return result;
	}

	public boolean delete(ReimbursementClmaimApplication bean) {
		
		boolean result =false;
		
		try {
			 String deleteDtlQuery = " DELETE FROM  HRMS_REIMB_APPL_DTL "
				 	+" WHERE REIMB_CLAIM_ID="+bean.getHiddenApplicationCode();
			 result = getSqlModel().singleExecute(deleteDtlQuery);
			 
			 if(result)
			 {
				 String deleteQuery =" DELETE FROM   HRMS_REIMB_APPLICATION "
				 +" WHERE REIMB_CLAIM_ID="+bean.getHiddenApplicationCode();
				 
				 result = getSqlModel().singleExecute(deleteQuery);
			 }
			 
					Object[][] updateObj = new Object[1][4];
					updateObj[0][0] = bean.getOnHoldBalance();
					updateObj[0][1] = bean.getOnHoldBalance();
					updateObj[0][2] = bean.getHiddenCreditCode();
					updateObj[0][3] = bean.getUserEmpId();
					try {
						String updateQuery ="";
						 updateQuery = " UPDATE HRMS_REIMB_BALANCE SET  REIMB_ONHOLD_AMT= (NVL(REIMB_ONHOLD_AMT,0)-?), REIMB_BALANCE_AMT=REIMB_BALANCE_AMT+? "
							+ " WHERE REIMB_CREDIT_CODE=? AND REIMB_EMP_ID=?" ;
									
						result = getSqlModel().singleExecute(updateQuery, updateObj);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	// Function for report
	public void getReport(ReimbursementClmaimApplication bean,
			HttpServletResponse response) {
		try {
			String title = "Reimbursement Claim Report";
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("Reimbursement Claim__ "+ bean.getEmpId().trim());
			rds.setReportName(title);
			rds.setReportType("Pdf");			
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);		
			
			//For title
			TableDataSet repTitleTable = new TableDataSet();
			Object[][] repTitleObj = new Object[1][1];
			repTitleObj[0][0] = title;
			
			repTitleTable.setData(repTitleObj);
			repTitleTable.setCellAlignment(new int[] { 1 });
			repTitleTable.setCellWidth(new int[] { 100 });
			repTitleTable.setBodyFontDetails(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0));
			repTitleTable.setBlankRowsBelow(1);
			repTitleTable.setBorder(false);
			rg.addTableToDoc(repTitleTable);
			//Title End
			
			
			//Employee information 
			Object[][] employeeInformationObj = new Object[4][4];
			employeeInformationObj[0][0]="Employee ID : ";
			employeeInformationObj[0][1]= bean.getEmpToken();
			
			employeeInformationObj[0][2] = "Employee Name : ";
			employeeInformationObj[0][3]= bean.getEmpName();
			
			employeeInformationObj[1][0] = "Designation : ";
			employeeInformationObj[1][1]= bean.getDesignation();
			
			employeeInformationObj[1][2] = "Branch : ";
			employeeInformationObj[1][3]= bean.getBranch();
			
			employeeInformationObj[2][0] = "Grade : ";
			employeeInformationObj[2][1]= bean.getGrade();
			
			employeeInformationObj[2][2] = "Claim Date : ";
			employeeInformationObj[2][3]= bean.getClaimDate();
			
			employeeInformationObj[3][0] = "Division : ";
			employeeInformationObj[3][1]= bean.getDivision();
			
			employeeInformationObj[3][2] = "Reimbursement Application ID : ";
			employeeInformationObj[3][3]= bean.getReferenceId();
			
			TableDataSet employeeInformationTable = new TableDataSet();
			employeeInformationTable.setData(employeeInformationObj);
			employeeInformationTable.setCellAlignment(new int[] { 0, 0, 0, 0 });
			employeeInformationTable.setCellWidth(new int[] { 15, 35, 30, 20 });			
			employeeInformationTable.setBlankRowsBelow(1);
			employeeInformationTable.setBorder(false);
			rg.addTableToDoc(employeeInformationTable);
			//Employee information end
			
			
			String itteratorQuery = "SELECT CREDIT_NAME,TO_CHAR(REIMB_EXPENSE_DATE,'DD-MM-YYYY'), NVL(REIMB_CLAIM_AMOUNT,0) ,CASE WHEN REIMB_PROOF IS NULL THEN 'NO' ELSE 'YES' END,REIMB_CREDIT_CODE,NVL(REIMB_BILL_AMOUNT,0) "
			  						+"  FROM HRMS_REIMB_APPL_DTL  "
			  						//+" INNER JOIN HRMS_REIMB_APPLICATION  ON (HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID = HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID ) "
			  						+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE) "
			  						+" WHERE HRMS_REIMB_APPL_DTL .REIMB_CLAIM_ID="+bean.getHiddenApplicationCode();
			
			Object[][] itteratorQueryObj = getSqlModel().getSingleResult(itteratorQuery);
			
			Object[][] objTabularData = new Object[itteratorQueryObj.length][6];
			Object[][] objTotalData = new Object[1][6];
			objTotalData =Utility.checkNullObjArr(objTotalData, "");
			String[] columns = new String[] { "Sr. No.", "Reimbursement Head",
					"Expense Date", "Claim Amount","Bill Amount", "Proof Attached" };
			int[] bcellAlign = new int[] { 1, 0, 1, 2, 2, 0 };
			int[] bcellWidth = new int[] { 10, 30, 15, 20, 20, 10 };

			int count = 1;
			double totalAmount=0.0d;	
			double totalBillAmount=0.0d;	
			if(itteratorQueryObj !=null && itteratorQueryObj.length>0)
			{
				for (int i = 0; i < itteratorQueryObj.length; i++) {
					objTabularData[i][0] = count++;
					objTabularData[i][1] = checkNull(String.valueOf(itteratorQueryObj[i][0]));
					objTabularData[i][2] = checkNull(String.valueOf(itteratorQueryObj[i][1]));
					objTabularData[i][3] = checkNull(String.valueOf(itteratorQueryObj[i][2]));
					objTabularData[i][4] = checkNull(String.valueOf(itteratorQueryObj[i][5]));
					objTabularData[i][5] = checkNull(String.valueOf(itteratorQueryObj[i][3]));
					
					totalAmount += Double.parseDouble(checkNull(String.valueOf(itteratorQueryObj[i][2])));
					totalBillAmount += Double.parseDouble(checkNull(String.valueOf(itteratorQueryObj[i][5])));
				}
			}
			
			else
			{
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No data to display ";
				
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10,
						Font.BOLD, new Color(0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}		
			objTotalData[0][2]="Total Amount ";
			objTotalData[0][3]=totalAmount;	
			objTotalData[0][4]=totalBillAmount;	
			
			//For itterator
			TableDataSet tdstable = new TableDataSet();
			tdstable.setHeader(columns);
			tdstable.setData(objTabularData);
			tdstable.setCellAlignment(bcellAlign);
			tdstable.setCellWidth(bcellWidth);
			tdstable.setBorder(true);
			tdstable.setHeaderBGColor(new Color(225, 225, 225));
			rg.addTableToDoc(tdstable);
			
			//For total amount
			TableDataSet totaltdstable = new TableDataSet();			
			totaltdstable.setData(objTotalData);
			totaltdstable.setCellAlignment(bcellAlign);
			totaltdstable.setCellWidth(bcellWidth);
			totaltdstable.setBlankRowsBelow(1);
			totaltdstable.setBorder(true);
			totaltdstable.setHeaderBGColor(new Color(225, 225, 225));
			rg.addTableToDoc(totaltdstable);	
			
			
			
			//Applicants Comments
			Object[][] applicantsCommentsObj = new Object[1][2];
			applicantsCommentsObj[0][0] = "Applicant Comments : ";
			applicantsCommentsObj[0][1]= bean.getComments();
			
			TableDataSet applicantsCommentsTable = new TableDataSet();
			applicantsCommentsTable.setData(applicantsCommentsObj);
			applicantsCommentsTable.setHeaderBGColor(new Color(225, 225, 225));
			applicantsCommentsTable.setCellAlignment(new int[] { 0, 0 });
			applicantsCommentsTable.setCellWidth(new int[] { 25, 75 });			
			applicantsCommentsTable.setBlankRowsBelow(1);
			applicantsCommentsTable.setBorder(false);
			rg.addTableToDoc(applicantsCommentsTable);
			//End Applicants comments
			
			
			//Approver Comments		
			
			String approverCommentsQuery = "SELECT EMP_TOKEN,(EMP_FNAME||' '|| EMP_LNAME ||' ')	,TO_CHAR(REIMB_APPROVAL_DATE,'DD-MM-YYYY HH12:MI PM') "
					+ " ,DECODE(REIMB_APPROVAL_STATUS,'N','Draft','P','Pending For Manager Approval','A','Approved','R','Rejected','B','Sent Back','D','Pendig for disbursement') "
					+ " ,REIMB_APPROVER_COMMENTS  "
					+ "  FROM HRMS_REIMB_APPL_PATH "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_REIMB_APPL_PATH.REIMB_APPROVER= HRMS_EMP_OFFC.EMP_ID) "
					+ " WHERE REIMB_CLAIM_ID= "
					+ bean.getHiddenApplicationCode();

			Object[][] approverCommentsObj = getSqlModel().getSingleResult(approverCommentsQuery);

			Object[][] approverCommentsTabularData = new Object[approverCommentsObj.length][6];
			
			
			String[] approvercolumns = new String[] { "Sr. No.", "Approver ID",
					"Approver Name", "Date", "Status", "Comments" };
				int[] approverbcellAlign = new int[] { 1, 0, 0, 1, 0, 0 };
				int[] approverbcellWidth = new int[] { 5, 15, 30, 20, 10,25 };

				int approvercount = 1;
					
				if(approverCommentsObj !=null && approverCommentsObj.length>0)
				{
					String comments = "Approver Comments : ";
					TableDataSet apprcommentsTitleTable = new TableDataSet();
					Object[][] apprcommentsTitleObj = new Object[1][1];
					apprcommentsTitleObj[0][0] = comments;			
					apprcommentsTitleTable.setData(apprcommentsTitleObj);					
					apprcommentsTitleTable.setCellAlignment(new int[] { 0 });
					apprcommentsTitleTable.setCellWidth(new int[] { 100 });					
					apprcommentsTitleTable.setBlankRowsBelow(1);
					apprcommentsTitleTable.setBorder(false);
					rg.addTableToDoc(apprcommentsTitleTable);
					
					for (int i = 0; i < approverCommentsObj.length; i++) {
						approverCommentsTabularData[i][0] = approvercount++;
						approverCommentsTabularData[i][1] = checkNull(String.valueOf(approverCommentsObj[i][0]));
						approverCommentsTabularData[i][2] = checkNull(String.valueOf(approverCommentsObj[i][1]));
						approverCommentsTabularData[i][3] = checkNull(String.valueOf(approverCommentsObj[i][2]));
						approverCommentsTabularData[i][4] = checkNull(String.valueOf(approverCommentsObj[i][3]));
						approverCommentsTabularData[i][5] = checkNull(String.valueOf(approverCommentsObj[i][4]));						
					}
					TableDataSet apprcommentsTable = new TableDataSet();
					apprcommentsTable.setData(approverCommentsTabularData);
					apprcommentsTable.setHeaderBGColor(new Color(225, 225, 225));
					apprcommentsTable.setHeader(approvercolumns);
					apprcommentsTable.setCellAlignment(approverbcellAlign);
					apprcommentsTable.setCellWidth(approverbcellWidth);
					apprcommentsTable.setHeaderFontDetails(Font.HELVETICA, 10,
							Font.BOLD, new Color(0, 0, 0));
					apprcommentsTable.setBlankRowsBelow(1);
					apprcommentsTable.setBorder(true);
					rg.addTableToDoc(apprcommentsTable);	
				}
				else {
						
				}			
				
				
			rg.process();
			rg.createReport(response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	} // End of Function for report
	
	public void displayPreApprovedBills(ReimbursementClmaimApplication bean){
		/*
		 * display preApproved bills
		 */
		String billDetQuery="SELECT REIMB_PRE_APPPROVED_AMT_DTL FROM HRMS_REIMB_BALANCE WHERE REIMB_CREDIT_CODE="+bean.getHiddenCreditCode()+
		" AND REIMB_EMP_ID="+bean.getUserEmpId();
		
		Object [][]billDetObj=getSqlModel().getSingleResult(billDetQuery);
		ArrayList tableList=new ArrayList();
		if(billDetObj != null && billDetObj.length > 0){
			String billsDtlString=Utility.checkNull(String.valueOf(billDetObj[0][0]));
			if(billsDtlString.equals("")){
				bean.setPreApprovedListFlag("false");
			}else{
				String prevAppl[]=billsDtlString.split(",");
				for (int i = 0; i < prevAppl.length; i++) {
					String amtSplit[]=prevAppl[i].split("#");
					String applId=amtSplit[0];
					String prevAmt=amtSplit[1];
					String proofNameQuery="SELECT NVL(REIMB_REFERENCE_ID,''), NVL(REIMB_PROOF,'') FROM HRMS_REIMB_APPL_DTL WHERE REIMB_PROOF IS NOT NULL AND REIMB_CLAIM_ID="+applId;
					Object [][]proofObj=getSqlModel().getSingleResult(proofNameQuery);
					ArrayList proofList=new ArrayList();
					ReimbursementClmaimApplication amountBean=new ReimbursementClmaimApplication();
					for (int j = 0; j < proofObj.length; j++) {
						
						String []proofs=String.valueOf(proofObj[j][1]).split(",");
						String []refNo=String.valueOf(proofObj[j][0]).split(",");
						if(j==0)
						amountBean.setPrevProofAmt(prevAmt);
						
						for (int k = 0; k < refNo.length; k++) {
							ReimbursementClmaimApplication proofBean=new ReimbursementClmaimApplication();
							proofBean.setPrevProofFileName(proofs[k]);
							proofBean.setPrevProofRefNo(refNo[k]);
							proofList.add(proofBean);
						}
						amountBean.setPreApprovedProofList(proofList);
						
					}
					tableList.add(amountBean);
					
				}
				bean.setPreApprovedList(tableList);
				bean.setPreApprovedListFlag("true");
			}
			
		}else{
			bean.setPreApprovedListFlag("false");
		}
		/*
		 * display preApproved bills ends
		 */
	}
}
