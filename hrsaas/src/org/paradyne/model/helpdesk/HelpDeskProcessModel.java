package org.paradyne.model.helpdesk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.helpdesk.HelpDeskProcess;
import org.paradyne.lib.DateUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;
import org.xhtmlrenderer.css.style.derived.StringValue;

import com.itextpdf.text.BaseColor;

/**
 * @author AA0554
 * 
 */
public class HelpDeskProcessModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(HelpDeskProcessModel.class);

	public void getPendingList(HelpDeskProcess bean,
			HttpServletRequest request, boolean isFilter) {
		try {
			
			String listStatus = request.getParameter("listStatus");
			
			bean.setClosedLength("false");
			String query = " SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,REQUEST_POSTED_BY,REQUEST_APPLIED_TO_DEPT,HRMS_DEPT.DEPT_NAME,HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_NAME,"
					+ " REQUEST_ID,TO_CHAR(REQUEST_DATE,'DD-MM-YYYY HH24:MI'),NVL(REQUEST_TOKEN,''),NVL(REQUEST_SUBJECT,' '),'false','',0, FLOOR(SYSDATE - REQUEST_DATE) PENDING_SINCE,"
					+ " NVL(REQUEST_TYPE_NAME,' '), REQUEST_TYPE"
					+ " FROM HELPDESK_REQUEST_HDR"
					+ " INNER JOIN HRMS_EMP_OFFC ON(REQUEST_POSTED_BY=EMP_ID)"
					+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_HDR.REQUEST_APPLIED_TO_DEPT) "
					+ " LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON(HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV = HELPDESK_REQUEST_HDR.REQUEST_APPL_STATUS)"
					+ " INNER JOIN HELPDESK_MGRREPORTING_HDR ON(HELPDESK_MGRREPORTING_HDR.DEPT_CODE=HELPDESK_DEPT.DEPT_CODE AND REQ_TYPE_CODE=REQUEST_TYPE"
					+ " AND BRANCH_CODE=EMP_CENTER) "
					+ " INNER JOIN HELPDESK_REQUEST_TYPE ON(HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID = HELPDESK_REQUEST_HDR.REQUEST_TYPE)";

			query += " WHERE 1=1 AND REQUEST_APPL_STATUS IN('O','I','W','P') ";
			query += " AND REQUEST_MGR_STATUS IN ('N','A')";
			query += " AND (MANAGER_CODE=" + bean.getUserEmpId()
					+ " OR REQUEST_PENDING_WITH=" + bean.getUserEmpId() 
					+ " OR DEPT_HEAD="+ bean.getUserEmpId()+")";
			System.out.println("bean.getFilterByStatus()  "
					+ bean.getFilterByStatus());
			if (!bean.getFilterByStatus().trim().equals("-1") && !bean.getFilterByStatus().trim().equals("")) {
				query += " AND REQUEST_APPL_STATUS = '";
				query += bean.getFilterByStatus() + "'";
			}

			if (!bean.getFilterByReqToken().equals("")) {
				query += "  AND REQUEST_TOKEN='" + bean.getFilterByReqToken() + "' ";
			}
			if (!bean.getFilterByEmpId().equals("")) {
				query += "  AND REQUEST_POSTED_BY='" + bean.getFilterByEmpId()
						+ "' ";
			}
			if (!bean.getFilterByCatId().equals("")) {
				query += "  AND REQUEST_TYPE='" + bean.getFilterByCatId()
						+ "' ";
			}
			if (!bean.getFilterByDeptId().equals("")) {
				query += "  AND DEPT_CODE='" + bean.getFilterByDeptId() + "' ";
			}
			if (!bean.getFilterByDate().equals("")) {
				query += "  AND TO_CHAR(REQUEST_DATE,'dd-mm-yyyy')='"
						+ bean.getFilterByDate() + "' ";
			}

			query += " ORDER BY REQUEST_DATE DESC";
			Object[][] requestObj = getSqlModel().getSingleResult(query);
			Object[][] escalatedRequests = getEscalatedRecords(bean);
			try {
				/*
				 * Join the pending with object & escalated object
				 */
				if (requestObj != null && requestObj.length > 0) {
					if (escalatedRequests != null
							&& escalatedRequests.length > 0) {
						requestObj = Utility.joinArrays(requestObj,
								escalatedRequests, 1, 0);
					}
				} else {
					requestObj = escalatedRequests;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (requestObj != null && requestObj.length > 0) {
				String[] pageIndex = Utility.doPaging(bean.getMyPage(),
						requestObj.length, 20);// to display the page number

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2]))); // to set the total number
				// of page
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));// to set the page number
				if (pageIndex[4].equals("1"))
					bean.setMyPage("1");
				bean.setListType("pending");
				ArrayList requestList = new ArrayList();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					HelpDeskProcess beanList = new HelpDeskProcess();
					beanList.setEmpIdList(Utility.checkNull(String
							.valueOf(requestObj[i][0])));
					beanList.setEmpNameList(Utility.checkNull(String
							.valueOf(requestObj[i][1])));
					beanList.setEmpCodeList(Utility.checkNull(String
							.valueOf(requestObj[i][2])));
					beanList.setApplDeptCodeList(Utility.checkNull(String
							.valueOf(requestObj[i][3])));
					beanList.setApplDeptNameList(Utility.checkNull(String
							.valueOf(requestObj[i][4])));
					beanList.setStatusList(Utility.checkNull(String
							.valueOf(requestObj[i][5])));
					beanList.setRequestDateList(Utility.checkNull(String
							.valueOf(requestObj[i][7])));
					beanList.setRequestIdList(Utility.checkNull(String
							.valueOf(requestObj[i][6])));
					beanList.setRequestTokenList(Utility.checkNull(String
							.valueOf(requestObj[i][8])));
					beanList.setSubjectList(Utility.checkNull(String
							.valueOf(requestObj[i][9])));
					beanList.setEscalatedFlagList(Utility.checkNull(String
							.valueOf(requestObj[i][10])));
					beanList.setEscalatedFromNameList(Utility.checkNull(String
							.valueOf(requestObj[i][11])));
					beanList.setEscalatedTimeList(Utility.twoDecimals(Utility
							.checkNull(String.valueOf(requestObj[i][12]))));
					beanList.setPendingSinceList((Utility.checkNull(String.valueOf(requestObj[i][13]))));
					beanList.setCategoryList(Utility.checkNull(String
							.valueOf(requestObj[i][14])));
					beanList.setCategoryIdList(Utility.checkNull(String
							.valueOf(requestObj[i][15])));
					requestList.add(beanList);
				}
				bean.setPendingList(requestList);
				bean.setPendingLength("true");
			} else {
				bean.setPendingLength("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getRequestDetails(HelpDeskProcess bean) {
		String query = "SELECT NVL(REQUEST_TOKEN,' '), TO_CHAR(REQUEST_DATE,'DD-MM-YYYY'), DECODE(REQUEST_APPLIED_FOR,'S','Self','O','Other','C','Client'), REQUEST_POSTED_BY, "
				+ " POSTED_BY.EMP_TOKEN, POSTED_BY.EMP_FNAME||' '||POSTED_BY.EMP_MNAME||' '||POSTED_BY.EMP_LNAME INITIATOR,"
				+ " POSTED_BY_DEPT.DEPT_NAME,POSTED_BY_CENTER.CENTER_NAME,POSTED_BY_RANK.RANK_NAME,HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_NAME,"
				+ " REQUEST_APPLIED_TO_DEPT, NVL(HRMS_DEPT.DEPT_NAME,' '),REQUEST_TYPE,NVL(REQUEST_TYPE_NAME,' '),NVL(REQUEST_SUBJECT,' '),"
				+ "  ASSET_TYPE,NVL(ASSET_CATEGORY_NAME,' '),ASSET_SUB_TYPE, NVL(ASSET_SUBTYPE_NAME,' '),NVL(ASSET_QUANTITY,0),NVL(REQUEST_DESC,' '), NVL(REQUEST_FILE_UPLOAD, ''),"
				+ " REQUEST_POSTED_FOR, POSTED_FOR.EMP_TOKEN, POSTED_FOR.EMP_FNAME||' '||POSTED_FOR.EMP_MNAME||' '||POSTED_FOR.EMP_LNAME "
				+ " , NVL(REQUEST_CLIENT_NAME,' '),REQUEST_SUBTYPE,DECODE(IS_ASSET_REQUEST,'Y','true','false'),TO_CHAR(REQUEST_DATE,'DD-MM-YYYY HH:MI')"
				+ " ,NVL(REQUEST_TOKEN,''), NVL(ADD_MOBILE,' '), NVL(ADD_EXTENSION,0), NVL(ADD_EMAIL,' '),DEPT_ID"
				+ " FROM HELPDESK_REQUEST_HDR"
				+ " LEFT JOIN HRMS_EMP_OFFC POSTED_BY ON (POSTED_BY.EMP_ID =  HELPDESK_REQUEST_HDR.REQUEST_POSTED_BY)"
				+ " LEFT JOIN HRMS_EMP_OFFC POSTED_FOR ON (POSTED_FOR.EMP_ID =  HELPDESK_REQUEST_HDR.REQUEST_POSTED_FOR)"
				+ " LEFT JOIN HRMS_DEPT POSTED_BY_DEPT ON (POSTED_BY.EMP_DEPT =  POSTED_BY_DEPT.DEPT_ID)"
				+ " LEFT JOIN HRMS_CENTER POSTED_BY_CENTER ON (POSTED_BY.EMP_CENTER =  POSTED_BY_CENTER.CENTER_ID)"
				+ " LEFT JOIN HRMS_RANK POSTED_BY_RANK ON (POSTED_BY.EMP_RANK =  POSTED_BY_RANK.RANK_ID)"
				// +" LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE =
				// HELPDESK_REQUEST_HDR.REQUEST_APPLIED_TO_DEPT)"
				+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_HDR.REQUEST_APPLIED_TO_DEPT) "
				+ " LEFT JOIN HELPDESK_REQUEST_TYPE ON (HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID = HELPDESK_REQUEST_HDR.REQUEST_TYPE)"
				+ " LEFT JOIN HELPDESK_REQUEST_SUBTYPE ON (HELPDESK_REQUEST_SUBTYPE.REQUEST_SUBTYPE_ID = HELPDESK_REQUEST_HDR.REQUEST_SUBTYPE)"
				// +" INNER JOIN HRMS_EMP_OFFC APPROVER ON (APPROVER.EMP_ID =
				// HELPDESK_REQUEST_HDR.REQUEST_PENDING_WITH)"
				+ " LEFT JOIN HRMS_ASSET_CATEGORY ON (HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE = HELPDESK_REQUEST_HDR.ASSET_TYPE)"
				+ " LEFT JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE = HELPDESK_REQUEST_HDR.ASSET_SUB_TYPE)"
				+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HELPDESK_REQUEST_HDR.REQUEST_POSTED_BY and ADD_TYPE='P')"
				+ " LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON(HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV =HELPDESK_REQUEST_HDR.REQUEST_APPL_STATUS)"
				+ " WHERE REQUEST_ID =" + bean.getRequestId();
		Object[][] requestDetails = getSqlModel().getSingleResult(query);
		if (requestDetails != null && requestDetails.length > 0) {
			bean.setEmpId(String.valueOf(requestDetails[0][4]));
			bean.setEmpName(String.valueOf(requestDetails[0][5]));
			bean.setEmpCode(String.valueOf(requestDetails[0][3]));
			bean.setBranchName(String.valueOf(requestDetails[0][7]));
			bean.setDeptName(String.valueOf(requestDetails[0][6]));
			bean.setDesgName(String.valueOf(requestDetails[0][8]));
			bean.setRequestDate(String.valueOf(requestDetails[0][1]));
			bean.setStatus(String.valueOf(requestDetails[0][9]));

			bean.setAppliedFor(String.valueOf(requestDetails[0][2]));
			bean.setApplDeptCode(String.valueOf(requestDetails[0][10]));
			bean.setApplDeptName(String.valueOf(requestDetails[0][11]));
			bean.setReqTypeCode(String.valueOf(requestDetails[0][12]));
			bean.setReqType(String.valueOf(requestDetails[0][13]));
			bean.setSubject(String.valueOf(requestDetails[0][14]));
			bean.setAssetTypeCode(String.valueOf(requestDetails[0][15]));
			bean.setAssetType(String.valueOf(requestDetails[0][16]));
			bean.setAssetSubTypeCode(String.valueOf(requestDetails[0][17]));
			bean.setAssetSubType(String.valueOf(requestDetails[0][18]));
			bean.setReqQuantity(String.valueOf(requestDetails[0][19]));
			bean.setReqDesc(String.valueOf(requestDetails[0][20]));
			bean.setReqSubTypeCode(String.valueOf(requestDetails[0][26]));
			bean.setAssetFlag(String.valueOf(requestDetails[0][27]));
			bean.setRequestDateTime(String.valueOf(requestDetails[0][28]));
			bean.setRequestToken(String.valueOf(requestDetails[0][29]));
			bean.setReqEmpContactNo(String.valueOf(requestDetails[0][30]));
			bean.setReqEmpExtensionNo(String.valueOf(requestDetails[0][31]));
			bean.setReqEmpEmailId(String.valueOf(requestDetails[0][32]));
			bean.setDeptCode(String.valueOf(requestDetails[0][33]));
			String fileNames = Utility.checkNull(String
					.valueOf(requestDetails[0][21]));
			if (!fileNames.equals("")) {
				String[] fileArray = fileNames.split(",");
				ArrayList fileList = new ArrayList();
				for (int i = 0; i < fileArray.length; i++) {
					HelpDeskProcess listBean = new HelpDeskProcess();
					listBean.setFileName(fileArray[i]);
					fileList.add(listBean);
				}
				bean.setFileList(fileList);
				bean.setFileFlag("true");
			} else {
				bean.setFileFlag("false");
			}
			if (!bean.getAppliedFor().equals("Client")) {
				bean.setEmpCodeFor(Utility.checkNull(String
						.valueOf(requestDetails[0][22])));
				bean.setEmpIdFor(Utility.checkNull(String
						.valueOf(requestDetails[0][23])));
				bean.setEmpNameFor(Utility.checkNull(String
						.valueOf(requestDetails[0][24])));
			} else {
				bean.setClientName(Utility.checkNull(String
						.valueOf(requestDetails[0][25])));
			}
		}
	}

	public void setSlaDetails(HelpDeskProcess bean) {
		String query = "SELECT DECODE(STATUS_CATAGORY_ID,'2','InProcess','3','Resolved','4','Waiting for Approval','5','Waiting for parts'),SLA_STATUS_DURATION||' '||"
				+ " DECODE(SLA_STATUS_DURATION_TYPE,'H','Hours','M','Minutes','D','Days'),SLA_STATUS_DURATION_TYPE "
				+ " FROM  HELPDESK_SLA_DTL "
				+ " INNER JOIN HELPDESK_REQUEST_SUBTYPE ON REQUEST_SUBTYPE_SLA=SLA_ID AND REQUEST_SUBTYPE_ID="
				+ bean.getReqSubTypeCode()
				+ " WHERE STATUS_CATAGORY_ID!=1 ORDER BY STATUS_CATAGORY_ID";
		Object[][] slaObj = getSqlModel().getSingleResult(query);

		if (slaObj != null && slaObj.length > 0) {
			ArrayList slaList = new ArrayList();
			bean.setSlaStatus("Open");
			bean.setSlaDuration("");
			slaList.add(bean);
			for (int i = 0; i < slaObj.length; i++) {
				HelpDeskProcess beanList = new HelpDeskProcess();
				beanList.setSlaStatus(Utility.checkNull(String
						.valueOf(slaObj[i][0])));
				beanList.setSlaDuration(Utility.checkNull(String
						.valueOf(slaObj[i][1])));
				String inProcessTime = "";
				String reProcessTime = "";
				
				 Object[][] maxObj={};;
				
				String slaQuery3="";
				 String status="";
				 if (i == 0) {
						 status="I";
				}else if (i == 1){
						 status="R";
						 
				}else if (i == 2){
						 status="W";
				}else if (i == 3){
						 status="P";
				}else{
					 status="O";
				}
				
				if(!status.equals("")){
					try{
						
				          String query1="SELECT TO_CHAR(MAX(HELPDESK_ACTIVITY_LOG.REQUEST_DATE),'DD-MM-YYYY  HH24:MI:SS') FROM HELPDESK_ACTIVITY_LOG "+
				               " WHERE REQUEST_ID="+bean.getRequestId() +" AND HELPDESK_ACTIVITY_LOG.REQUEST_STATUS='"+status+"'";
				          maxObj = getSqlModel().getSingleResult(query1);
									   
						
				         
				          String query2="SELECT TO_CHAR(MAX(HELPDESK_ACTIVITY_LOG.REQUEST_DATE),'DD-MM-YYYY  HH24:MI:SS') "
		        	  			+" FROM HELPDESK_ACTIVITY_LOG	WHERE REQUEST_ID= "+bean.getRequestId() 
		        	  			+" AND TO_CHAR(HELPDESK_ACTIVITY_LOG.REQUEST_DATE,'DD-MM-YYYY  HH24:MI:SS') < "
		        	  			+" (SELECT TO_CHAR(MAX(HELPDESK_ACTIVITY_LOG.REQUEST_DATE),'DD-MM-YYYY  HH24:MI:SS') "
		        	  			+" FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID="+bean.getRequestId() +" AND HELPDESK_ACTIVITY_LOG.REQUEST_STATUS='"+status+"')";
						 Object[][] minObj = getSqlModel().getSingleResult(query2);
						 
						  String query4=" SELECT TO_NUMBER(TO_CHAR(TO_DATE('1','J')+(TO_DATE('"+String.valueOf(maxObj[0][0])+"','DD-MM-YYYY  HH24:MI:SS') - "+
						  			   " TO_DATE('"+String.valueOf(minObj[0][0])+"','DD-MM-YYYY  HH24:MI:SS')),'J')-1)DAYS, "+
						  			   " TO_CHAR(TO_DATE('00:00:00','HH24:MI:SS')+(TO_DATE('"+String.valueOf(maxObj[0][0])+"','DD-MM-YYYY  HH24:MI:SS')- TO_DATE('"+String.valueOf(minObj[0][0])+"','DD-MM-YYYY  HH24:MI:SS')), +" +
						  			   " 'HH24:MI:SS') "+
									   " TIME FROM HELPDESK_ACTIVITY_LOG " +
									   " INNER JOIN HELPDESK_REQUEST_HDR ON(HELPDESK_REQUEST_HDR.REQUEST_ID=HELPDESK_ACTIVITY_LOG.REQUEST_ID"+
									   " )" +
									   " WHERE "+
									   " HELPDESK_REQUEST_HDR.REQUEST_ID="+bean.getRequestId()
									 + " GROUP BY HELPDESK_REQUEST_HDR.REQUEST_DATE ";
						  
						  Object[][] finalObj = getSqlModel().getSingleResult(query4);			   
								
						  String days=String.valueOf(finalObj[0][0]);
								  if(!days.equals("0")){
							          inProcessTime = Utility.checkNull((String
												.valueOf(finalObj[0][0])))
										+ " Days ";
								  }
						        
								  String time=String .valueOf(finalObj[0][1]);
								  String[] splitTime = time.split(":"); 
								  if(!splitTime[0].equals("00")){
									  String hourdata=splitTime[0]; 
									 inProcessTime+=hourdata+" Hours ";
								  }
								  
								  String minutedata =splitTime[1]; 
								  String seconddata =splitTime[2];
								  inProcessTime+=minutedata+" Minutes " +seconddata+" Seconds";
						          
						          beanList.setSlaActualDuration(inProcessTime);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				 
				slaList.add(beanList);
			}
			bean.setSlaList(slaList);
		}
	}

	public void setActivityLogDetails(HelpDeskProcess bean) {
		System.out.println("filterbyId"+ bean.getFilterById());
		System.out.println("filterby REquest Id"+bean.getRequestId());
		String reqId="";
		if(bean.getFilterById().equals("")){
			reqId =bean.getRequestId();
		}else{
			reqId = bean.getFilterById();
		}
		String query = "SELECT FROM_EMP.EMP_FNAME||' '||FROM_EMP.EMP_MNAME||' '||FROM_EMP.EMP_LNAME,TO_EMP.EMP_FNAME||' '||TO_EMP.EMP_MNAME||' '||TO_EMP.EMP_LNAME,"
				+ " DECODE(HELPDESK_ACTIVITY_LOG.REQUEST_STATUS,'C','Closed','N','Rejected',HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_NAME),TO_CHAR(REQUEST_DATE,'DD-MM-YYYY HH24:MI:SS'),"
				+ "NVL(REQUEST_COMMENTS,'') FROM HELPDESK_ACTIVITY_LOG "
				+ " LEFT JOIN HRMS_EMP_OFFC FROM_EMP  ON (FROM_EMP.EMP_ID=REQUEST_ACTION_BY)"
				+ " LEFT JOIN HRMS_EMP_OFFC TO_EMP ON (TO_EMP.EMP_ID=REQUEST_FORWARDED_TO)"
				+ " LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON(HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV =HELPDESK_ACTIVITY_LOG.REQUEST_STATUS)"
				+ "WHERE REQUEST_ID="+ reqId
				+ " ORDER BY REQUEST_DATE DESC";
		Object[][] logObj = getSqlModel().getSingleResult(query);
		if (logObj != null && logObj.length > 0) {
			ArrayList logList = new ArrayList();
			for (int i = 0; i < logObj.length; i++) {
				HelpDeskProcess beanList = new HelpDeskProcess();
				beanList.setLogFromEmp(Utility.checkNull(String
						.valueOf(logObj[i][0])));
				beanList.setLogToEmp(Utility.checkNull(String
						.valueOf(logObj[i][1])));
				beanList.setLogStatus(Utility.checkNull(String
						.valueOf(logObj[i][2])));
				beanList.setLogDate(Utility.checkNull(String
						.valueOf(logObj[i][3])));
				beanList.setLogComments(Utility.checkNull(String
						.valueOf(logObj[i][4])));
				logList.add(beanList);
			}
			bean.setLogList(logList);
			bean.setLogLength("true");
		}
	}

	public void showForAssignment(HelpDeskProcess bean) {

		String assetDetailsQuery = "SELECT HELPDESK_REQUEST_HDR.ASSET_TYPE,ASSET_CATEGORY_NAME,HELPDESK_REQUEST_HDR.ASSET_SUB_TYPE,ASSET_SUBTYPE_NAME,(NVL(ASSET_QUANTITY,0)-NVL(ASSET_ASSIGNED,0)),"
				+ " ASSET_INVENTORY_TYPE,REQUEST_ID FROM HELPDESK_REQUEST_HDR"
				+ " INNER JOIN HRMS_ASSET_CATEGORY ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HELPDESK_REQUEST_HDR.ASSET_TYPE)"
				+ " INNER JOIN HRMS_ASSET_SUBTYPE ON(HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HELPDESK_REQUEST_HDR.ASSET_SUB_TYPE)"
				+ " WHERE REQUEST_ID ="
				+ bean.getRequestId()
				+ " AND (NVL(ASSET_QUANTITY,0)-NVL(ASSET_ASSIGNED,0))>0";
		Object[][] result = getSqlModel().getSingleResult(assetDetailsQuery);

		ArrayList<Object> tableList = new ArrayList<Object>();

		logger.info("emp code==" + bean.getEmpCode());
		String warehouseQuery = "SELECT WAREHOUSE_CODE, WAREHOUSE_NAME FROM HRMS_EMP_OFFC"
				+ " INNER JOIN HRMS_WAREHOUSE_BRANCH ON(HRMS_WAREHOUSE_BRANCH.WAREHOUSE_BRANCH=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " INNER JOIN HRMS_WAREHOUSE_MASTER ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE =HRMS_WAREHOUSE_BRANCH.WAREHOUSE_CODE)"
				+ " WHERE EMP_ID=" + bean.getEmpCode();
		Object warehouseName[][] = getSqlModel()
				.getSingleResult(warehouseQuery);
		bean.setEmpWarehouseCode(String.valueOf(warehouseName[0][0]));

		/*
		 * show the applied assets for the particular application
		 */
		for (int i = 0; i < result.length; i++) {
			/*
			 * if the Inventory type is bulk
			 * 
			 */
			if (!checkNull(String.valueOf(result[i][5])).equals("I")) {
				String quantityAvailableQuery = "SELECT  NVL(SUM(HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE),0) FROM HRMS_ASSET_MASTER_DTL "
						+ "INNER JOIN HRMS_ASSET_MASTER ON(HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE)"
						+ "WHERE ASSET_SUBTYPE_CODE ="
						+ String.valueOf(result[i][2])
						+ " AND ASSET_WAREHOUSE_CODE ="
						+ bean.getEmpWarehouseCode();
				Object quantityAvailable[][] = getSqlModel().getSingleResult(
						quantityAvailableQuery);

				if (Float.parseFloat(String.valueOf(quantityAvailable[0][0])) >= Float
						.parseFloat("" + result[i][4])) {
					HelpDeskProcess bean1 = new HelpDeskProcess();
					bean1.setAssetCode(checkNull(String.valueOf(result[i][0])));
					bean1
							.setAsstHdType(checkNull(String
									.valueOf(result[i][1])));
					bean1.setSubTypeCodeIterator(checkNull(String
							.valueOf(result[i][2])));
					bean1.setAssetSubTypeIterator(checkNull(String
							.valueOf(result[i][3])));
					bean1.setAssetRequiredIterator(checkNull(String
							.valueOf(result[i][4])));
					bean1.setReturnFlagIterator(checkNull(String
							.valueOf(result[i][5])));
					bean1.setEmpWarehouse(String.valueOf(warehouseName[0][1]));
					logger.info("warehousename=====" + warehouseName[0][0]);
					tableList.add(bean1);
				} // end if
				else if (!String.valueOf(quantityAvailable[0][0]).equals("0")) {

					HelpDeskProcess bean2 = new HelpDeskProcess();
					bean2.setAssetCode(checkNull(String.valueOf(result[i][0])));
					bean2
							.setAsstHdType(checkNull(String
									.valueOf(result[i][1])));
					bean2.setSubTypeCodeIterator(checkNull(String
							.valueOf(result[i][2])));
					bean2.setAssetSubTypeIterator(checkNull(String
							.valueOf(result[i][3])));
					bean2
							.setAssetRequiredIterator(checkNull(("" + Float
									.parseFloat(String
											.valueOf(quantityAvailable[0][0])))));
					bean2.setReturnFlagIterator(checkNull(String
							.valueOf(result[i][5])));
					bean2.setEmpWarehouse(String.valueOf(warehouseName[0][1]));
					logger.info("warehousename=====" + warehouseName[0][0]);
					tableList.add(bean2);

				}
			} // end of if
			/*
			 * if the Inventory type is Itemized
			 * 
			 */
			else {

				String quantityAvailableQuery = "SELECT  NVL(SUM(HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE),0) FROM HRMS_ASSET_MASTER_DTL "
						+ "INNER JOIN HRMS_ASSET_MASTER ON(HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE)"
						+ "WHERE ASSET_SUBTYPE_CODE ="
						+ String.valueOf(result[i][2])
						+ " AND ASSET_WAREHOUSE_CODE ="
						+ bean.getEmpWarehouseCode();
				Object quantityAvailable[][] = getSqlModel().getSingleResult(
						quantityAvailableQuery);
				try {
					if (Float.parseFloat(String
							.valueOf(quantityAvailable[0][0])) >= Float
							.parseFloat("" + result[i][4])) {

						for (int j = 0; j < Integer.parseInt(String
								.valueOf(result[i][4])); j++) {
							HelpDeskProcess bean1 = new HelpDeskProcess();
							bean1.setAssetCode(checkNull(String
									.valueOf(result[i][0])));
							bean1.setAsstHdType(checkNull(String
									.valueOf(result[i][1])));
							bean1.setSubTypeCodeIterator(checkNull(String
									.valueOf(result[i][2])));
							bean1.setAssetSubTypeIterator(checkNull(String
									.valueOf(result[i][3])));
							bean1.setReturnFlagIterator(checkNull(String
									.valueOf(result[i][5])));
							bean1.setAssetRequiredIterator("1");
							bean1.setEmpWarehouse(String
									.valueOf(warehouseName[0][1]));
							tableList.add(bean1);
						} // end of for loop

					} else if (!String.valueOf(quantityAvailable[0][0]).equals(
							"0")) {
						for (int j = 0; j < Integer.parseInt(String
								.valueOf(quantityAvailable[0][0])); j++) {
							HelpDeskProcess bean1 = new HelpDeskProcess();
							bean1.setAssetCode(checkNull(String
									.valueOf(result[i][0])));
							bean1.setAsstHdType(checkNull(String
									.valueOf(result[i][1])));
							bean1.setSubTypeCodeIterator(checkNull(String
									.valueOf(result[i][2])));
							bean1.setAssetSubTypeIterator(checkNull(String
									.valueOf(result[i][3])));
							bean1.setAssetRequiredIterator("1");
							bean1.setReturnFlagIterator(checkNull(String
									.valueOf(result[i][5])));
							bean1.setEmpWarehouse(String
									.valueOf(warehouseName[0][1]));
							logger.info("warehousename====="
									+ warehouseName[0][0]);
							tableList.add(bean1);
						}
					} else if (String.valueOf(quantityAvailable[0][0]).equals(
							"0")) {
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			} // end of else

		} // end of for loop
		bean.setAssignList(tableList);
		bean.setTableLength(String.valueOf(tableList.size()));
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}

	public boolean processRequest(HelpDeskProcess bean) {
		/*
		 * calculate the action duration
		 */
		boolean result = false;
		try {
			String duartionQuery = "SELECT NVL(((SYSDATE-MAX(REQUEST_DATE))*24*60),0) FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID="
					+ bean.getRequestId()
					+ " AND (REQUEST_MGR_STATUS IS NULL OR REQUEST_MGR_STATUS='N') AND REQUEST_STATUS='O' AND REQUEST_EMP_STATUS IN ('O','N')";
			Object[][] durationObj = getSqlModel().getSingleResult(duartionQuery);
			String actionDuration = "";
			if (durationObj != null && durationObj.length > 0) {
				actionDuration = String.valueOf(durationObj[0][0]);
			}

			/*
			 * Insert the the record in HELPDESK_ACTIVITY_LOG table
			 */
			
			
			String activityLogQuery = "INSERT INTO HELPDESK_ACTIVITY_LOG ( REQUEST_ID, REQUEST_ACTION_BY, REQUEST_FORWARDED_TO,"
					+ " REQUEST_STATUS, REQUEST_DATE, REQUEST_COMMENTS, REQUEST_ACTION_DURATION, "
					+ " REQUEST_MGR_STATUS, REQUEST_EMP_STATUS,ACTUAL_CALL_DURATION,REQUEST_CALL_DURATION) VALUES ( ?,?,?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS'),'DD-MM-YYYY HH24:MI:SS'),?,?,'N','N',?,?)";

			Object[][] activityLogObj = new Object[1][8];
			activityLogObj[0][0] = bean.getRequestId();
			activityLogObj[0][1] = bean.getUserEmpId();
			//updated by anantha lakshmi
			String assignToCode=checkNull(bean.getAssignToCode());
			
			if (bean.getProcessStatus().equals("I")) {
				activityLogObj[0][2] = assignToCode;
			} else {
				activityLogObj[0][2] = bean.getEmpCode();
			}
			
			if (bean.getProcessStatus().equals("W") || bean.getProcessStatus().equals("P") ) {
				activityLogObj[0][2] = bean.getUserEmpId();
			} 

			activityLogObj[0][3] = bean.getProcessStatus().trim();
			activityLogObj[0][4] = bean.getProcessComments();
			activityLogObj[0][5] = actionDuration;
			
			
			String MadateQuery = "SELECT  TO_CHAR(MAX(REQUEST_DATE),'DD-MM-YYYY HH24:MI')  FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID ="
				+ bean.getRequestId();
			Object[][] MaxdateQueryObj = getSqlModel().getSingleResult(MadateQuery);
			
			if(MaxdateQueryObj!=null && MaxdateQueryObj.length >0){
				
				String diffDateTime ="SELECT round((SYSDATE- TO_DATE('"+String.valueOf(MaxdateQueryObj[0][0])+"','DD-MM-YYYY HH24:MI'))*24 *60) FROM DUAL";
				Object[][] diffDateTimeObj = getSqlModel().getSingleResult(diffDateTime);
				
				if(diffDateTimeObj !=null && diffDateTimeObj.length >0){
					activityLogObj[0][6] =String.valueOf(diffDateTimeObj[0][0]) ;
				}else{
					activityLogObj[0][6] = 0.00;
				}
					
			}
			if((bean.getProcessStatus().equals("W") )|| (bean.getProcessStatus().equals("P"))){
				activityLogObj[0][7] = 0;
			}else{
			String dateQuery = "SELECT  TO_CHAR(MAX(REQUEST_DATE),'DD-MM-YYYY')  FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID =" +bean.getRequestId();
			
			Object[][]dateQueryObj=getSqlModel().getSingleResult(dateQuery);
			if(dateQueryObj!=null && dateQueryObj[0][0] !=null){
				
				HelpDeskAppModel model = new HelpDeskAppModel();
				model.initiate(context, session);
				Date date = new Date();
				SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
				String sysDate = formater.format(date);

				
				  
				String query = "  SELECT TO_DATE('" + sysDate
						+ "','dd-mm-yyyy')-TO_DATE('"
						+ String.valueOf(dateQueryObj[0][0])
						+ "','dd-mm-yyyy') from dual ";

				Object dateDiffQueryObj[][] = getSqlModel().getSingleResult(query);
				int looplength = 0;
				if (dateDiffQueryObj != null && dateDiffQueryObj.length > 0) {
					looplength = Integer.parseInt(String
							.valueOf(dateDiffQueryObj[0][0]));
				}
				String[][] splitObj = null;
				splitObj = new DateUtility().splitDatesObj(String
						.valueOf(dateQueryObj[0][0]), looplength + 1);
				int minutes = 0;
				for (int i = 0; i < splitObj.length; i++) {
					System.out.println("splitObj  " + splitObj[i][0]);
					if (model.IsWeekEnd(splitObj[i][0], bean.getUserEmpId())) {
						System.out.println("True sataraday");
						
						minutes +=0;
					}
					if (model.IsHoliday(splitObj[i][0], bean.getUserEmpId())) {
						System.out.println("True holiday");
						minutes += 0;
					}
					
				if(!model.IsWeekEnd(splitObj[i][0], bean.getUserEmpId()) && ! model.IsHoliday(splitObj[i][0], bean.getUserEmpId())) {
						if(!splitObj[i][0].equals(sysDate)){
							int workingHours=model.getWorkingHours(String.valueOf(splitObj[i][0]),bean.getUserEmpId(),bean.getRequestId());
						minutes +=workingHours;
					}else{
						minutes += model.getCurrentDayWorkingHour(bean.getUserEmpId(),bean.getRequestId());
					}
				}else{
					minutes+=0;
				}

			}
				activityLogObj[0][7] = minutes;
				model.terminate();
				
			}else{
				activityLogObj[0][7] = 0;
			}
			}
				
			
			boolean isshiftallowed=false;
			String shiftAllowedQuery="SELECT SFT_FLEXITIME_ALLOWED from HRMS_SHIFT where SHIFT_ID = "+
			 						" (SELECT EMP_SHIFT FROM HRMS_EMP_OFFC WHERE EMP_ID ="+bean.getUserEmpId()+") ";
			Object[][] shftAllowedObj = getSqlModel().getSingleResult(shiftAllowedQuery);
			if(shftAllowedObj!=null && shftAllowedObj.length>0){
				if(String.valueOf(shftAllowedObj[0][0]).equals("Y")){
				isshiftallowed=true;
				}	
			}
			if(isshiftallowed){
				activityLogObj[0][7] = activityLogObj[0][6];
			}
			/*String dateQuery = "SELECT  TO_CHAR(MAX(REQUEST_DATE),'DD-MM-YYYY HH24:MI:SS')  FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID =" +bean.getRequestId();
			
			Object[][]dateQueryObj=getSqlModel().getSingleResult(dateQuery);
			if(dateQueryObj!=null && dateQueryObj[0][0] !=null){
				String datetime="SELECT TO_NUMBER(TO_CHAR(TO_DATE('1','J')+(SYSDATE - TO_DATE('"+String.valueOf(dateQueryObj[0][0])+"','DD-MM-YYYY HH24:MI:SS') ),'J')-1)DAYS,TO_CHAR(TO_DATE('00:00:00','HH24:MI:SS')+(SYSDATE - TO_DATE('"+String.valueOf(dateQueryObj[0][0])+"','DD-MM-YYYY HH24:MI:SS')),'HH24:MI:SS') TIME"+
				 "  FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID="+ bean.getRequestId();
				Object[][]datetimeObj=getSqlModel().getSingleResult(datetime);
				if(datetimeObj!=null && datetimeObj.length>0){
				String days=String.valueOf(datetimeObj[0][0]);
				String time= String.valueOf(datetimeObj[0][1]);
				String[] splitTime = time.split(":");
				String hourdata=splitTime[0];
				String minutedata = splitTime[1];
				String seconddata =splitTime[2];
				
				String finalDateTime="";
				if(!days.equals("0")){
					finalDateTime +=days+" Days ";
				}
				if(!hourdata.equals("00")){
					finalDateTime +=hourdata+" Hours ";
				}
				
				if(!minutedata.equals("00")){
					finalDateTime +=minutedata+" Minutes ";
				}
				if(!seconddata.equals("00")){
					finalDateTime +=seconddata+" Seconds ";
				}
				
				activityLogObj[0][6] = finalDateTime;
				}
			}else{
				activityLogObj[0][6] = "00.00";
			}*/
			
			
			result = getSqlModel().singleExecute(activityLogQuery,
					activityLogObj);

			/*
			 * UPDATE the request status in HELPDESK_REQUEST_HDR
			 */
			if (result) {
				String updateReqStatQuery = "";
				Object[][] updateReqStatObj = null;
				if (bean.getProcessStatus().equals("I")) {
					updateReqStatQuery = "UPDATE HELPDESK_REQUEST_HDR SET REQUEST_PENDING_WITH=?,REQUEST_APPL_STATUS=? WHERE REQUEST_ID=?";
					updateReqStatObj = new Object[1][3];
					updateReqStatObj[0][0] = checkNull(assignToCode);
					updateReqStatObj[0][1] = bean.getProcessStatus().trim();
					updateReqStatObj[0][2] = bean.getRequestId();
					System.out.println("=======BF IN PROCESS OF TEAM=====%%%%%%TEAM MEMBER%%%%%====="+assignToCode);
					sendForwardNotificationMailToTeamMember(bean.getRequestId(),
							bean.getEmpCode(), bean.getUserEmpId(), assignToCode);

					/*Send request resolved mail to manager & initiator*/

				} else {
					updateReqStatQuery = "UPDATE HELPDESK_REQUEST_HDR SET REQUEST_APPL_STATUS=? WHERE REQUEST_ID=?";
					updateReqStatObj = new Object[1][2];
					updateReqStatObj[0][0] = bean.getProcessStatus().trim();
					updateReqStatObj[0][1] = bean.getRequestId();
					
					
					
						
				
					//Updated by "	

					
					if((bean.getProcessStatus().equals("W") ) || (bean.getProcessStatus().equals("P"))){
						MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
						processAlerts.initiate(context, session);
						String module = "HelpDesk";
						processAlerts.removeProcessAlert(bean.getRequestId(),module);
						EmailTemplateBody template = new EmailTemplateBody();
						template.initiate(context, session);
						String message="Your request "+ bean.getRequestToken() +" has been Waiting for Approval/Parts " ;
						String alertLink = "/help/HelpDeskProcess_retrieveDetails.action";
						String linkParam = "reqAppCode=" + bean.getRequestId() + "&reqStatus=W"
								+ "&listStatus=PD";
						
						template.sendProcessManagerAlertWithdraw("", "HelpDesk", "A",
								bean.getRequestId(), "1", linkParam, alertLink, message,
																 "Processed", bean.getEmpCode(),bean.getUserEmpId(),"", bean.getUserEmpId());
						alertLink = "/help/HelpDesk_retrieveDetails.action";
						 linkParam = "reqAppCode=" + bean.getRequestId();
						template.sendProcessManagerAlertWithdraw(bean.getEmpCode(), "HelpDesk", "I",
								bean.getRequestId(), "1", linkParam, alertLink, message,
																 "Processed", bean.getEmpCode(),bean.getUserEmpId(),"", "");
						template.terminate();
						System.out.println("Waiting for approval");
					}else{
						System.out.println("========DEPT CODE>>>>>>>>>>>.========"+bean.getApplDeptCode());
						String managerIdQuery = " SELECT MANAGER_CODE FROM HELPDESK_MGRREPORTING_HDR " 
							+ " WHERE DEPT_CODE = "+bean.getApplDeptCode()+" AND REQ_TYPE_CODE  = "
							+ bean.getReqTypeCode() +" AND BRANCH_CODE = ( "
							+ " SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = "+bean.getEmpCode()
							+ ") ";
						
						Object[][] managerObj = getSqlModel().getSingleResult(managerIdQuery);
						String managerCode = "0";
						if (managerObj != null && managerObj.length > 0) {
							managerCode = String.valueOf(managerObj[0][0]);
						}
						if (!managerCode.equals(bean.getUserEmpId())) {	
							sendResolvedNotificationMailToOwner(bean.getRequestId(),
									bean.getEmpCode(), managerCode, bean.getUserEmpId());
						} else {
							sendNotificationMailToApplicant(bean.getRequestId(), bean
									.getEmpCode(), bean.getUserEmpId());
						}
					}
				}
				double totalMinutes =0;
				double totalActualduration=0;
				String updateReqSumQuery = "";
				Object[][] updateReqSumObj = null;
				String sumQuery = "SELECT SUM(REQUEST_CALL_DURATION),SUM(ACTUAL_CALL_DURATION) from HELPDESK_ACTIVITY_LOG where REQUEST_ID = "+bean.getRequestId();
				Object[][] sumObj = getSqlModel().getSingleResult(sumQuery);
				if(sumObj!=null && sumObj.length >0){
					totalMinutes = Double.parseDouble(String.valueOf(sumObj[0][0]));
					totalActualduration = Double.parseDouble(String.valueOf(sumObj[0][1]));
				}
				updateReqSumQuery = "UPDATE HELPDESK_REQUEST_HDR SET REQUEST_COMPLETION_TIME = ?,ACTUAL_COMPLITION_TIME=?,REQUEST_COMPLITION_DATE=SYSDATE WHERE REQUEST_ID=?";
				updateReqSumObj = new Object[1][3];
				
				String sysdatequery="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI')  FROM DUAL";
				Object[][] sysdateObj = getSqlModel().getSingleResult(sysdatequery);
				updateReqSumObj[0][0] = totalMinutes;
				updateReqSumObj[0][1] = totalActualduration;
				updateReqSumObj[0][2] = bean.getRequestId();
				
				result = getSqlModel().singleExecute(updateReqSumQuery,
						updateReqSumObj);
				
				result = getSqlModel().singleExecute(updateReqStatQuery,
						updateReqStatObj);
				
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void getResolvedList(HelpDeskProcess bean,
			HttpServletRequest request, boolean isFilter) {
		try {

			bean.setPendingLength("false");

			String query = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '|| EMP_LNAME,REQUEST_POSTED_BY,REQUEST_APPLIED_TO_DEPT,DEPT_NAME,STATUS_CATAGORY_NAME,"
					+ " REQUEST_ID,TO_CHAR(REQUEST_DATE,'DD-MM-YYYY HH24:MI'),REQUEST_TOKEN,NVL(REQUEST_SUBJECT,' '),"
					+ " NVL(REQUEST_TYPE_NAME,' '), REQUEST_TYPE"
					+ " FROM HELPDESK_REQUEST_HDR"
					+ " INNER JOIN HRMS_EMP_OFFC ON(REQUEST_POSTED_BY=EMP_ID)"
					+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_HDR.REQUEST_APPLIED_TO_DEPT) "
					+ " INNER JOIN HELPDESK_MGRREPORTING_HDR ON(HELPDESK_MGRREPORTING_HDR.DEPT_CODE=HELPDESK_DEPT.DEPT_CODE AND REQ_TYPE_CODE=REQUEST_TYPE"
					+ " AND BRANCH_CODE=EMP_CENTER) "
					+ " INNER JOIN HELPDESK_REQUEST_TYPE ON(HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID = HELPDESK_REQUEST_HDR.REQUEST_TYPE)"
					+ " LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON(HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV =REQUEST_APPL_STATUS)";
			query += " WHERE 1=1 AND REQUEST_APPL_STATUS IN('R') ";
			query += " AND REQUEST_MGR_STATUS IN ('N','A')";
			query += " AND (MANAGER_CODE=" + bean.getUserEmpId()
					+ " OR REQUEST_PENDING_WITH=" + bean.getUserEmpId() 
					+ " OR DEPT_HEAD="+ bean.getUserEmpId()+")";

			System.out.println("bean.getFilterByStatus()  "
					+ bean.getFilterByStatus());

			if (!bean.getFilterByStatus().trim().equals("-1") && !bean.getFilterByStatus().trim().equals("")) {
				query += " AND REQUEST_APPL_STATUS = '";
				query += bean.getFilterByStatus() + "'";
			}

			if (!bean.getFilterByReqToken().equals("")) {
				query += "  AND REQUEST_TOKEN='" + bean.getFilterByReqToken() + "' ";
			}
			if (!bean.getFilterByEmpId().equals("")) {
				query += "  AND REQUEST_POSTED_BY='" + bean.getFilterByEmpId()
						+ "' ";
			}
			if (!bean.getFilterByCatId().equals("")) {
				query += "  AND REQUEST_TYPE='" + bean.getFilterByCatId()
						+ "' ";
			}
			if (!bean.getFilterByDeptId().equals("")) {
				query += "  AND DEPT_CODE='" + bean.getFilterByDeptId() + "' ";
			}
			if (!bean.getFilterByDate().equals("")) {
				query += "  AND TO_CHAR(REQUEST_DATE,'dd-mm-yyyy')='"
						+ bean.getFilterByDate() + "' ";
			}
			query += " ORDER BY REQUEST_DATE DESC";

			Object[][] requestObj = getSqlModel().getSingleResult(query);

			if (requestObj != null && requestObj.length > 0) {
				String[] pageIndex = Utility.doPaging(bean.getMyPageClosed(),
						requestObj.length, 20);

				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPageClosed", Integer.parseInt(String
						.valueOf(pageIndex[2]))); // to set the total number of page
				request.setAttribute("pageNoClosed", Integer.parseInt(String
						.valueOf(pageIndex[3])));// to set the page number
				if (pageIndex[4].equals("1"))
					bean.setMyPageClosed("1");
				bean.setListType("closed");
				ArrayList requestList = new ArrayList();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					HelpDeskProcess beanList = new HelpDeskProcess();
					beanList.setEmpIdList(Utility.checkNull(String
							.valueOf(requestObj[i][0])));
					beanList.setEmpNameList(Utility.checkNull(String
							.valueOf(requestObj[i][1])));
					beanList.setEmpCodeList(Utility.checkNull(String
							.valueOf(requestObj[i][2])));
					beanList.setApplDeptCodeList(Utility.checkNull(String
							.valueOf(requestObj[i][3])));
					beanList.setApplDeptNameList(Utility.checkNull(String
							.valueOf(requestObj[i][4])));
					beanList.setStatusList(Utility.checkNull(String
							.valueOf(requestObj[i][5])));
					beanList.setRequestDateList(Utility.checkNull(String
							.valueOf(requestObj[i][7])));
					beanList.setRequestIdList(Utility.checkNull(String
							.valueOf(requestObj[i][6])));
					beanList.setRequestTokenList(Utility.checkNull(String
							.valueOf(requestObj[i][8])));
					beanList.setSubjectList(Utility.checkNull(String
							.valueOf(requestObj[i][9])));
					beanList.setCategoryList(Utility.checkNull(String
							.valueOf(requestObj[i][10])));
					beanList.setCategoryIdList(Utility.checkNull(String
							.valueOf(requestObj[i][11])));
					requestList.add(beanList);
				}
				bean.setClosedList(requestList);
				bean.setClosedLength("true");
			} else {
				bean.setClosedLength("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Object[][] getEscalatedRecords(HelpDeskProcess bean) {
		String query = "";
		Object returnObj[][] = null;
		Object tempObj[][] = null;
		try {
			for (int i = 1; i <= 3; i++) { // for loop for 3 escalation levels
				tempObj = null;
				query = " SELECT  HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,REQUEST_POSTED_BY,REQUEST_APPLIED_TO_DEPT,DEPT_NAME,"
						+ " DECODE(REQUEST_APPL_STATUS,'O','Open','I','In Process','R','Resolved','W','Waiting for Approval','P','Waiting for Parts'),"
						+ " REQUEST_ID,TO_CHAR(REQUEST_DATE,'DD-MM-YYYY'),NVL(REQUEST_TOKEN,''),NVL(REQUEST_SUBJECT,''),'true',"
						+ " PENDING.EMP_FNAME||' '||PENDING.EMP_MNAME||' '||PENDING.EMP_LNAME,"
						+ " CASE WHEN SLA_ESCALATION_DURN_TYPE"
						+ i
						+ "='H' THEN (SYSDATE-REQUEST_DATE)*24||' '||'Hours'  "
						+ " WHEN SLA_ESCALATION_DURN_TYPE"
						+ i
						+ "='M' THEN (SYSDATE-REQUEST_DATE)*24*60||' '||'Minutes' "
						+ " WHEN SLA_ESCALATION_DURN_TYPE"
						+ i
						+ "='D' THEN (SYSDATE-REQUEST_DATE)||' '||'Days'  END"
						+ " FROM HELPDESK_REQUEST_HDR "
						+ " INNER JOIN HRMS_EMP_OFFC ON(REQUEST_POSTED_BY=HRMS_EMP_OFFC.EMP_ID)"
						+ " INNER JOIN HRMS_EMP_OFFC PENDING ON(REQUEST_PENDING_WITH=PENDING.EMP_ID)"
						// +" INNER JOIN HRMS_DEPT
						// ON(DEPT_ID=REQUEST_APPLIED_TO_DEPT)"
						+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_HDR.REQUEST_APPLIED_TO_DEPT) "
						+ " INNER JOIN HELPDESK_REQUEST_SUBTYPE ON REQUEST_SUBTYPE=REQUEST_SUBTYPE_ID"
						+ " INNER JOIN HELPDESK_SLA_DTL ON (REQUEST_SUBTYPE_SLA=SLA_ID)"
						+ " WHERE "
						+ " CASE WHEN SLA_ESCALATION_DURN_TYPE"
						+ i
						+ "='H' THEN (SYSDATE-REQUEST_DATE)*24"
						+ " WHEN SLA_ESCALATION_DURN_TYPE"
						+ i
						+ "='M' THEN (SYSDATE-REQUEST_DATE)*24*60"
						+ " WHEN SLA_ESCALATION_DURN_TYPE"
						+ i
						+ "='D' THEN (SYSDATE-REQUEST_DATE) END"
						+ " >= SLA_ESCALATION_TIME_"
						+ i
						+ " AND REQUEST_APPL_STATUS='O' AND SLA_ESCALATION_TO_"
						+ i
						+ " ="
						+ bean.getUserEmpId()
						+ " AND STATUS_CATAGORY_ID=2 "
						+ " UNION ALL"
						+ " SELECT  HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,REQUEST_POSTED_BY,REQUEST_APPLIED_TO_DEPT,DEPT_NAME,"
						+ " DECODE(REQUEST_APPL_STATUS,'O','Open','I','In Process','R','Resolved','W','Waiting for Approval','P','Waiting for Parts'),"
						+ " REQUEST_ID,TO_CHAR(REQUEST_DATE,'DD-MM-YYYY'),NVL(REQUEST_TOKEN,''),NVL(REQUEST_SUBJECT,''),'true',"
						+ " PENDING.EMP_FNAME||' '||PENDING.EMP_MNAME||' '||PENDING.EMP_LNAME,"
						+ " CASE WHEN SLA_ESCALATION_DURN_TYPE"
						+ i
						+ "='H' THEN (SYSDATE-REQUEST_DATE)*24||' '||'Hours'  "
						+ " WHEN SLA_ESCALATION_DURN_TYPE"
						+ i
						+ "='M' THEN (SYSDATE-REQUEST_DATE)*24*60||' '||'Minutes' "
						+ " WHEN SLA_ESCALATION_DURN_TYPE"
						+ i
						+ "='D' THEN (SYSDATE-REQUEST_DATE)||' '||'Days'  END"
						+ " FROM HELPDESK_REQUEST_HDR "
						+ " INNER JOIN HRMS_EMP_OFFC ON(REQUEST_POSTED_BY=HRMS_EMP_OFFC.EMP_ID)"
						+ " INNER JOIN HRMS_EMP_OFFC PENDING ON(REQUEST_PENDING_WITH=PENDING.EMP_ID)"
						// +" INNER JOIN HRMS_DEPT
						// ON(DEPT_ID=REQUEST_APPLIED_TO_DEPT)"
						+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_HDR.REQUEST_APPLIED_TO_DEPT) "
						+ " INNER JOIN HELPDESK_REQUEST_SUBTYPE ON REQUEST_SUBTYPE=REQUEST_SUBTYPE_ID"
						+ " INNER JOIN HELPDESK_SLA_DTL ON (REQUEST_SUBTYPE_SLA=SLA_ID)"
						+ " WHERE "
						+ " CASE WHEN SLA_ESCALATION_DURN_TYPE"
						+ i
						+ "='H' THEN (SYSDATE-REQUEST_DATE)*24"
						+ " WHEN SLA_ESCALATION_DURN_TYPE"
						+ i
						+ "='M' THEN (SYSDATE-REQUEST_DATE)*24*60"
						+ " WHEN SLA_ESCALATION_DURN_TYPE"
						+ i
						+ "='D' THEN (SYSDATE-REQUEST_DATE) END"
						+ " >= SLA_ESCALATION_TIME_"
						+ i
						+ " AND REQUEST_APPL_STATUS IN ('I','O') AND SLA_ESCALATION_TO_"
						+ i
						+ " ="
						+ bean.getUserEmpId()
						+ " AND STATUS_CATAGORY_ID=3 " + " ORDER BY 8 DESC";

				tempObj = getSqlModel().getSingleResult(query);
				if (tempObj != null && tempObj.length > 0) {
					if (returnObj == null || returnObj.length == 0) {
						returnObj = tempObj;
					} else {
						returnObj = Utility
								.joinArrays(returnObj, tempObj, 1, 0);
					}
				}
			} // end of for loop
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnObj;
	}

	public void sendNotificationMailToApplicant(String requestId,
			String initiatorId, String managerId) {
		try {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			logger.info("############# REQUEST ID ##################"
					+ requestId);
			logger.info("############# INITIATOR ID ##################"
					+ initiatorId);
			logger.info("############# MANAGER ID ##################"
					+ managerId);

			if (!managerId.equals("0")) {
				/*
				 * 1. Request Closure Notification Mail by Request Owner to
				 * Applicant
				 */
				MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
				processAlerts.initiate(context, session);
				String module = "HelpDesk";
				processAlerts.removeProcessAlert(String.valueOf(requestId),module);
				
				EmailTemplateBody templateForRequestOwner = new EmailTemplateBody();
				templateForRequestOwner.initiate(context, session);
				templateForRequestOwner
						.setEmailTemplate("Helpdesk Request Closure Notification Mail by Request Owner to Applicant");
				templateForRequestOwner.getTemplateQueries();
				try {
					// get the query as per number
					EmailTemplateQuery templateQueryForOwner1 = templateForRequestOwner
							.getTemplateQuery(1);// FROM EMAIL
					// set the parameter of queries
					templateQueryForOwner1.setParameter(1, managerId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForOwner2 = templateForRequestOwner
							.getTemplateQuery(2);// To EMAIL
					templateQueryForOwner2.setParameter(1, initiatorId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForOwner3 = templateForRequestOwner
							.getTemplateQuery(3);
					templateQueryForOwner3.setParameter(1, requestId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForOwner4 = templateForRequestOwner
							.getTemplateQuery(4);
					templateQueryForOwner4.setParameter(1, requestId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForOwner5 = templateForRequestOwner
							.getTemplateQuery(5);
					templateQueryForOwner5.setParameter(1, requestId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				templateForRequestOwner.configMailAlert();
				
				String link = "/help/HelpDesk_retrieveDetails.action";
				String linkParam = "reqAppCode=" + requestId+ "&"+"mgrStatus=P";
				templateForRequestOwner.sendProcessManagerAlert(initiatorId, "HelpDesk", "I", requestId,
						"1", linkParam, link, "Processed", initiatorId,
						"", "", "", managerId);
				
				 //linkParam = "reqAppCode=" + requestId+"&reqStatus=A"+"&listStatus=RD";
				 linkParam =   "reqAppCode=" + requestId+"&reqStatus=Resolved"+"&listStatus=details&listType=Resolved";
				  link = "/help/HelpDeskProcess_retrieveDetails.action";
				templateForRequestOwner.sendProcessManagerAlert("", "HelpDesk", "I", requestId,
						"1", linkParam, link, "Processed", initiatorId,
						"", "", managerId, managerId);
				
				templateForRequestOwner.sendApplicationMail();
				templateForRequestOwner.clearParameters();
				templateForRequestOwner.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendForwardNotificationMailToTeamMember(String requestId,
			String initiatorId, String managerId, String teamMemberId) {
		try {
			System.out.println("++++++++++++QQQQQQQQQQQQQQQq+++++++++++++++++");
			logger.info("############# REQUEST ID ##################"+ requestId);
			logger.info("############# INITIATOR ID ##################"+ initiatorId);
			logger.info("############# MANAGER ID ##################"+ managerId);
			logger.info("############# TEAM MEMBER ID ##################"+ teamMemberId);

			if (!managerId.equals("0")) {
				/*
				 * 1. Request Closure Notification Mail by Request Owner to
				 * Applicant
				 */

				EmailTemplateBody templateForTeamMember = new EmailTemplateBody();
				templateForTeamMember.initiate(context, session);
				templateForTeamMember
						.setEmailTemplate("Helpdesk Request Forward Notification to Team Members by Request Owner");
				templateForTeamMember.getTemplateQueries();
				
				MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
				processAlerts.initiate(context, session);
				String module = "HelpDesk";
				processAlerts.removeProcessAlert(String.valueOf(requestId),module);
				
				try {
					// get the query as per number
					EmailTemplateQuery templateQueryForTeamMember1 = templateForTeamMember
							.getTemplateQuery(1);// FROM EMAIL
					// set the parameter of queries
					templateQueryForTeamMember1.setParameter(1, managerId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForTeamMember2 = templateForTeamMember
							.getTemplateQuery(2);// To EMAIL
					templateQueryForTeamMember2.setParameter(1, teamMemberId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForTeamMember3 = templateForTeamMember
							.getTemplateQuery(3);
					templateQueryForTeamMember3.setParameter(1, requestId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForTeamMember4 = templateForTeamMember
							.getTemplateQuery(4);
					templateQueryForTeamMember4.setParameter(1, requestId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					System.out.println("==============5==============");
					EmailTemplateQuery templateQueryForTeamMember5 = templateForTeamMember
							.getTemplateQuery(5);
					templateQueryForTeamMember5.setParameter(1, requestId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					System.out.println("==============6==============");
					EmailTemplateQuery templateQueryForTeamMember6 = templateForTeamMember
							.getTemplateQuery(6);
					templateQueryForTeamMember6.setParameter(1, teamMemberId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				templateForTeamMember.configMailAlert();
				 String link = "/help/HelpDeskProcess_retrieveDetails.action";
				 String linkParam = "reqAppCode=" + requestId+"&reqStatus=I"+"&listStatus=details";
				 templateForTeamMember.sendProcessManagerAlert(teamMemberId, "HelpDesk", "A", requestId,
									"1", linkParam, link, "Processed", initiatorId,
									"", "", "", managerId);	
				 
				link = "/help/HelpDesk_retrieveDetails.action";
				linkParam = "reqAppCode=" + requestId+ "&"+"mgrStatus=I";
				templateForTeamMember.sendProcessManagerAlert(managerId, "HelpDesk", "I", requestId,
							"1", linkParam, link, "Processed", initiatorId,
							"", "", initiatorId, managerId);
				templateForTeamMember.sendApplicationMail();
				templateForTeamMember.clearParameters();
				templateForTeamMember.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendResolvedNotificationMailToOwner(String requestId,
			String initiatorId, String managerId,String teamMemberId) {
		try {

			System.out.println("=============ERERR SDSD DSDDD=============");
			logger.info("############# REQUEST ID ##################"
					+ requestId);
			logger.info("############# INITIATOR ID ##################"
					+ initiatorId);
			logger.info("############# MANAGER ID ##################"
					+ managerId);
			logger.info("############# TEAM MEMBER ID ##################"
					+ teamMemberId);

			if (!managerId.equals("0")) {
				/*
				 * 1. Request Closure Notification Mail by Request Owner to
				 * Applicant
				 */

				EmailTemplateBody templateForOwner = new EmailTemplateBody();
				templateForOwner.initiate(context, session);
				templateForOwner
						.setEmailTemplate("Helpdesk Request Resolved mail to Request Generator & Owner by Team Member");
				templateForOwner.getTemplateQueries();
				
				MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
				processAlerts.initiate(context, session);
				String module = "HelpDesk";
				processAlerts.removeProcessAlert(String.valueOf(requestId),module);
				
				try {
					// get the query as per number
					EmailTemplateQuery templateQueryForOwner1 = templateForOwner
							.getTemplateQuery(1);// FROM EMAIL
					// set the parameter of queries
					templateQueryForOwner1.setParameter(1, teamMemberId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForOwner2 = templateForOwner
							.getTemplateQuery(2);// To EMAIL
					templateQueryForOwner2.setParameter(1, initiatorId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForOwner3 = templateForOwner
							.getTemplateQuery(3);
					templateQueryForOwner3.setParameter(1, requestId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForOwner4 = templateForOwner
							.getTemplateQuery(4);
					templateQueryForOwner4.setParameter(1, requestId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForOwner5 = templateForOwner
							.getTemplateQuery(5);
					templateQueryForOwner5.setParameter(1, requestId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// And so on
					EmailTemplateQuery templateQueryForOwner6 = templateForOwner
							.getTemplateQuery(6);
					templateQueryForOwner6.setParameter(1, teamMemberId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				templateForOwner.configMailAlert();
				String linkParam = "reqAppCode=" + requestId+"&reqStatus=Resolved"+"&listStatus=details&listType=Resolved";
				 String link = "/help/HelpDeskProcess_retrieveDetails.action";
				 templateForOwner.sendProcessManagerAlert(managerId, "HelpDesk", "I", requestId,
									"1", linkParam, link, "Processed", initiatorId,
									"", "",teamMemberId, teamMemberId);	
				 link= "/help/HelpDesk_retrieveDetails.action";
				 templateForOwner.sendProcessManagerAlert(initiatorId, "HelpDesk", "I", requestId,
							"1", linkParam, link, "Processed", initiatorId,
							"", "", "", teamMemberId);	
				String[] mailToOwnerInitiator = { initiatorId, managerId };
				templateForOwner
						.sendApplicationMailToKeepInfo(mailToOwnerInitiator);
				templateForOwner.clearParameters();
				templateForOwner.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @ Nilesh Dhandare 5th Jan 2011.
	 * @param helpDeskProcess : Used to get Employee Name.
	 * @param request : Used to set Attribute.
	 * @param response :passed as argument to create report.
	 * @param string
	 */
	public void genReport(HelpDeskProcess helpDeskProcess,
			HttpServletRequest request, HttpServletResponse response,
			String appCode) {

		System.out.println("in genReport model calll------------------------");
		try {
			String reportType = "Pdf";
			String title = "Helpdesk Report";

			ReportDataSet rds = new ReportDataSet();
			String fileName = "Helpdesk Request for "
					+ helpDeskProcess.getEmpName();

			rds.setFileName(fileName);
			rds.setReportName(title);
			rds.setReportType(reportType);
			rds.setPageOrientation("landscape");
			rds.setPageSize("A5");

			ReportGenerator rg = null;

			logger.info("################# ATTACHMENT PATH #############"
					+ fileName + "." + reportType);
			rg = new ReportGenerator(rds, session, context,request);
			request.setAttribute("reportPath", fileName + "." + reportType);

			//Display Initiator Details 

			TableDataSet subtitleIniName = new TableDataSet();
			Object iniObj[][] = new Object[1][2];
			String str = (String) session.getAttribute("session_pool");
			iniObj[0][0] = "\n\nInitiator Details : ";
			iniObj[0][1] = " ";

			subtitleIniName.setData(iniObj);
			subtitleIniName.setCellAlignment(new int[] { 0, 1 });
			subtitleIniName.setCellWidth(new int[] { 15, 15 });
			//subtitleIniName.setBodyFontDetails(FontFamily.HELVETICA, 12,	Font.BOLD, new BaseColor(0, 0, 0));
			subtitleIniName.setBorderDetail(0);
			subtitleIniName.setBlankRowsBelow(0);
			rg.addTableToDoc(subtitleIniName);

			String initiatorDetailsQuery = " SELECT (POSTED_BY.EMP_TOKEN||'-'||POSTED_BY.EMP_FNAME||' '||POSTED_BY.EMP_MNAME||' '||POSTED_BY.EMP_LNAME ) AS INITIATOR, POSTED_BY_CENTER.CENTER_NAME, POSTED_BY_DEPT.DEPT_NAME,POSTED_BY_RANK.RANK_NAME,TO_CHAR(REQUEST_DATE,'DD-MM-YYYY HH:MI'),NVL(ADD_MOBILE,' '),NVL(ADD_EXTENSION,0),HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_NAME,NVL(ADD_EMAIL,' ') "
					+ "	FROM HELPDESK_REQUEST_HDR "
					+ "LEFT JOIN HRMS_EMP_OFFC POSTED_BY ON (POSTED_BY.EMP_ID =  HELPDESK_REQUEST_HDR.REQUEST_POSTED_BY) "
					+ "LEFT JOIN HRMS_DEPT POSTED_BY_DEPT ON (POSTED_BY.EMP_DEPT =  POSTED_BY_DEPT.DEPT_ID) "
					+ "LEFT JOIN HRMS_CENTER POSTED_BY_CENTER ON (POSTED_BY.EMP_CENTER =  POSTED_BY_CENTER.CENTER_ID) "
					+ "LEFT JOIN HRMS_RANK POSTED_BY_RANK ON (POSTED_BY.EMP_RANK =  POSTED_BY_RANK.RANK_ID) "
					+ "LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HELPDESK_REQUEST_HDR.REQUEST_POSTED_BY and ADD_TYPE='P') "
					+" LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON(HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV =REQUEST_APPL_STATUS)"
					+ " WHERE REQUEST_ID =" + appCode;

			Object[][] initiatorData = getSqlModel().getSingleResult(
					initiatorDetailsQuery);

			Object[][] formatTab = new Object[5][4];
		if(initiatorData!=null && initiatorData.length>0)
			{
			formatTab[0][0] = "Employee ";
			formatTab[0][1] = ":   "
					+ checkNull(String.valueOf(initiatorData[0][0]));
			formatTab[0][2] = "";
			formatTab[0][3] = "";

			formatTab[1][0] = "Branch ";
			formatTab[1][1] = ":   "
					+ checkNull(String.valueOf(initiatorData[0][1]));
			formatTab[1][2] = "Department ";
			formatTab[1][3] = ":   "
					+ checkNull(String.valueOf(initiatorData[0][2]));

			formatTab[2][0] = "Designation ";
			formatTab[2][1] = ":   "
					+ checkNull(String.valueOf(initiatorData[0][3]));
			formatTab[2][2] = "Request Date ";
			formatTab[2][3] = ":   "
					+ checkNull(String.valueOf(initiatorData[0][4]));

			formatTab[3][0] = "Contact Number ";
			formatTab[3][1] = ":   "
					+ checkNull(String.valueOf(initiatorData[0][5]));
			formatTab[3][2] = "Extension ";
			formatTab[3][3] = ":   "
					+ checkNull(String.valueOf(initiatorData[0][6]));

			formatTab[4][0] = "Request Status ";
			formatTab[4][1] = ":   "
					+ checkNull(String.valueOf(initiatorData[0][7]));
			formatTab[4][2] = "Email Id";
			formatTab[4][3] = ":   "
					+ checkNull(String.valueOf(initiatorData[0][8]));

			int[] bcellWidth = { 20, 30, 20, 30 };
			int[] bcellAlign = { 0, 0, 0, 0 };

			TableDataSet subtitleTableDetails = new TableDataSet();
			subtitleTableDetails.setData(formatTab);
			subtitleTableDetails.setCellAlignment(bcellAlign);
			subtitleTableDetails.setCellWidth(bcellWidth);
			subtitleTableDetails.setBorderDetail(0);
			subtitleTableDetails.setBlankRowsBelow(0);
			rg.addTableToDoc(subtitleTableDetails);

		}
			
			TableDataSet requestTable = new TableDataSet();
			Object objNew[][] = new Object[1][2];
			String str1 = (String) session.getAttribute("session_pool");
			objNew[0][0] = "\n\nRequest Details : ";
			objNew[0][1] = " ";

			requestTable.setData(objNew);
			requestTable.setCellAlignment(new int[] { 0, 1 });
			requestTable.setCellWidth(new int[] { 15, 15 });
			requestTable.setBorderDetail(0);
			requestTable.setBlankRowsBelow(0);
			rg.addTableToDoc(requestTable);

			//Request Details  
			String requestDetailsQuery = " SELECT (POSTED_FOR.EMP_TOKEN ||'-'||POSTED_FOR.EMP_FNAME||' '||POSTED_FOR.EMP_MNAME||' '||POSTED_FOR.EMP_LNAME) REQUESTOR,NVL(DEPT_NAME,' '),NVL(REQUEST_TYPE_NAME,' '),NVL(REQUEST_SUBJECT,''),NVL(REQUEST_TOKEN,''),NVL(REQUEST_DESC,' ') "
					+ "	FROM HELPDESK_REQUEST_HDR "
					+ "	LEFT JOIN HRMS_EMP_OFFC POSTED_FOR ON (POSTED_FOR.EMP_ID =  HELPDESK_REQUEST_HDR.REQUEST_POSTED_FOR) "
					+ "	LEFT JOIN HELPDESK_REQUEST_TYPE ON (HELPDESK_REQUEST_TYPE.REQUEST_TYPE_ID = HELPDESK_REQUEST_HDR.REQUEST_TYPE)  "
					+ "	LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_HDR.REQUEST_APPLIED_TO_DEPT) "
					+ "  WHERE REQUEST_ID =" + appCode;

			Object[][] requestorData = getSqlModel().getSingleResult(
					requestDetailsQuery);
		    
		  
	Object[][] formatTab1 = new Object[4][4];
if(requestorData!=null && requestorData.length>0)	{
	
	String queryChkReqFor = " SELECT REQUEST_APPLIED_FOR FROM HELPDESK_REQUEST_HDR  WHERE  REQUEST_ID ="+appCode;

Object [][] reqObj  = getSqlModel().getSingleResult(queryChkReqFor);
	
	if(reqObj!=null && reqObj.length>0)
	 {
			String reqfor  = String.valueOf(reqObj[0][0]);
		     System.out.println("reqfor ------- "+ reqfor);
			
			if(reqfor.equals("C"))
			{
				String queryChkReqForClient = " SELECT REQUEST_CLIENT_NAME FROM HELPDESK_REQUEST_HDR WHERE REQUEST_ID ="+appCode;

			Object [][] clientReqObj  = getSqlModel().getSingleResult(queryChkReqForClient);
				
			if(clientReqObj!=null && clientReqObj.length>0){
				formatTab1[0][0] = "Request For Client ";
				formatTab1[0][1] = ":   " + checkNull(String.valueOf(clientReqObj[0][0]));
				formatTab1[0][2] = "";
				formatTab1[0][3] = "";
			}	
				
				
			}
			
			if (reqfor.equals("S")){
				formatTab1[0][0] = "Request For Self ";
				formatTab1[0][1] = ":   "
						+ checkNull(String.valueOf(requestorData[0][0]));
				formatTab1[0][2] = "";
				formatTab1[0][3] = "";
			}
			if (reqfor.equals("O")){
				
				String queryChkReqForOther= " SELECT (EMP_TOKEN||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME)  "
					+ " FROM HELPDESK_REQUEST_HDR  "
					+ "  INNER JOIN HRMS_EMP_OFFC on (HRMS_EMP_OFFC.EMP_ID = HELPDESK_REQUEST_HDR.REQUEST_POSTED_FOR) "
					+ " WHERE  REQUEST_ID ="+appCode;
Object [][] otherReqObj  = getSqlModel().getSingleResult(queryChkReqForOther);		
				
				if(otherReqObj!=null && otherReqObj.length>0){
					formatTab1[0][0] = "Request For Other ";
					formatTab1[0][1] = ":   "
							+ checkNull(String.valueOf(otherReqObj[0][0]));
					formatTab1[0][2] = "";
					formatTab1[0][3] = "";
				}
				
			}
			
	 }
			formatTab1[1][0] = "Request To Department ";
			formatTab1[1][1] = ":   "
					+ checkNull(String.valueOf(requestorData[0][1]));
			formatTab1[1][2] = "Request Catagory ";
			formatTab1[1][3] = ":   "
					+ checkNull(String.valueOf(requestorData[0][2]));

			formatTab1[2][0] = "Subject ";
			formatTab1[2][1] = ":   "
					+ checkNull(String.valueOf(requestorData[0][3]));
			formatTab1[2][2] = "Request Id ";
			formatTab1[2][3] = ":   "
					+ checkNull(String.valueOf(requestorData[0][4]));

			formatTab1[3][0] = "Description ";
			formatTab1[3][1] = ":   "
					+ checkNull(String.valueOf(requestorData[0][5]));
			formatTab1[3][2] = "";
			formatTab1[3][3] = "";

			int[] bcellWidth1 = { 20, 30, 20, 30 };
			int[] bcellAlign1 = { 0, 0, 0, 0 };

			TableDataSet subtitleTableDetailsNew = new TableDataSet();
			subtitleTableDetailsNew.setData(formatTab1);
			subtitleTableDetailsNew.setCellAlignment(bcellAlign1);
			subtitleTableDetailsNew.setCellWidth(bcellWidth1);
			//subtitleTableDetailsNew.setBodyFontDetails(FontFamily.HELVETICA,10, Font.BOLD, new BaseColor(0, 0, 0));
			subtitleTableDetailsNew.setBorderDetail(0);
			subtitleTableDetailsNew.setBlankRowsBelow(0);
			rg.addTableToDoc(subtitleTableDetailsNew);
	 

  }
			//SLA Details 				

			TableDataSet slaTable = new TableDataSet();
			Object objSLA[][] = new Object[1][2];
			objSLA[0][0] = "\n\n SLA Details : ";
			objSLA[0][1] = " ";

			slaTable.setData(objSLA);
			slaTable.setCellAlignment(new int[] { 0, 1 });
			slaTable.setCellWidth(new int[] { 15, 15 });
			slaTable.setBorderDetail(0);
			slaTable.setBlankRowsBelow(0);
			rg.addTableToDoc(slaTable);

			String code = helpDeskProcess.getReqSubTypeCode();
			System.out.println("helpDeskProcess.getReqSubTypeCode() =========== "+ code);
			
			String slaQuery = "SELECT ROWNUM, DECODE(STATUS_CATAGORY_ID,'2','InProcess','3','Resolved','4','Waiting for Approval','5','Waiting for Parts'),SLA_STATUS_DURATION||' '|| DECODE(SLA_STATUS_DURATION_TYPE,'H','Hours','M','Minutes','D','Days'),DECODE(SLA_STATUS_DURATION_TYPE,'H','Hours','M','Minutes','D','Days')  FROM  HELPDESK_SLA_DTL  INNER JOIN HELPDESK_REQUEST_SUBTYPE ON REQUEST_SUBTYPE_SLA=SLA_ID AND REQUEST_SUBTYPE_ID="+code+" WHERE STATUS_CATAGORY_ID!=1 ORDER BY STATUS_CATAGORY_ID";
			Object[][] slaData = getSqlModel().getSingleResult(slaQuery);
			if (slaData != null && slaData.length > 0) {

				for (int i = 0; i < slaData.length; i++) {
					slaData[i][0] = i + 2;
				}
			}
			/*String timeQuery = " SELECT (MAX(HELPDESK_ACTIVITY_LOG.REQUEST_DATE)-HELPDESK_REQUEST_HDR.REQUEST_DATE ) T_day,  (MAX(HELPDESK_ACTIVITY_LOG.REQUEST_DATE)-HELPDESK_REQUEST_HDR.REQUEST_DATE)*24 T_hr,  (MAX(HELPDESK_ACTIVITY_LOG.REQUEST_DATE)-HELPDESK_REQUEST_HDR.REQUEST_DATE)*24*60 T_mi,"
									+ " HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_NAME FROM HELPDESK_ACTIVITY_LOG  " +
											"INNER JOIN HELPDESK_REQUEST_HDR ON(HELPDESK_REQUEST_HDR.REQUEST_ID=HELPDESK_ACTIVITY_LOG.REQUEST_ID)" +
											" LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON(HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV =HELPDESK_ACTIVITY_LOG.REQUEST_STATUS) WHERE HELPDESK_REQUEST_HDR.REQUEST_ID="+appCode+"  GROUP BY HELPDESK_REQUEST_HDR.REQUEST_DATE ,HELPDESK_ACTIVITY_LOG.REQUEST_STATUS,HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_NAME ";

			 	*/	
			//new code begins
		
			Object[][] maxObj={};;
			
			try{  
				
				//String slaQuery2 = "SELECT  HELPDESK_ACTIVITY_LOG.REQUEST_STATUS from HELPDESK_ACTIVITY_LOG where REQUEST_ID="+appCode;
				//Object[][] statusObj = getSqlModel().getSingleResult(slaQuery2);
				 
				boolean avl=false;
				String slaQuery3 ="";
			
					if (slaData != null && slaData.length > 0) {
						for (int i = 0; i < slaData.length; i++) {
							boolean result = false;
							 String status="";
							 if (i == 0) {
									 status="I"; 
							}else if (i == 1){
									 status="R"; 
							}else if (i == 2){
									 status="W"; 
							}else if (i == 3){
								    status="P"; 
							}else{
								   status="O";
							   }
							
							 
							if(!status.equals("")){
								
							String query1="SELECT TO_CHAR(MAX(HELPDESK_ACTIVITY_LOG.REQUEST_DATE),'DD-MM-YYYY  HH24:MI:SS') FROM HELPDESK_ACTIVITY_LOG "+
				               			" WHERE REQUEST_ID="+appCode+"AND HELPDESK_ACTIVITY_LOG.REQUEST_STATUS='"+status+"'";
				          maxObj = getSqlModel().getSingleResult(query1);
						 
				          
				          String query2="SELECT TO_CHAR(MAX(HELPDESK_ACTIVITY_LOG.REQUEST_DATE),'DD-MM-YYYY  HH24:MI:SS') "
		        	  			+" FROM HELPDESK_ACTIVITY_LOG	WHERE REQUEST_ID= "+appCode 
		        	  			+" AND TO_CHAR(HELPDESK_ACTIVITY_LOG.REQUEST_DATE,'DD-MM-YYYY  HH24:MI:SS') < "
		        	  			+" (SELECT TO_CHAR(MAX(HELPDESK_ACTIVITY_LOG.REQUEST_DATE),'DD-MM-YYYY  HH24:MI:SS') "
		        	  			+" FROM HELPDESK_ACTIVITY_LOG WHERE REQUEST_ID="+appCode +" AND HELPDESK_ACTIVITY_LOG.REQUEST_STATUS='"+status+"')";
						 Object[][] minObj = getSqlModel().getSingleResult(query2);
						 
						 
						  String query4=" SELECT TO_NUMBER(TO_CHAR(TO_DATE('1','J')+(TO_DATE('"+String.valueOf(maxObj[0][0])+"','DD-MM-YYYY  HH24:MI:SS') - "+
						  			   " TO_DATE('"+String.valueOf(minObj[0][0])+"','DD-MM-YYYY  HH24:MI:SS')),'J')-1)DAYS, "+
						  			   " TO_CHAR(TO_DATE('00:00:00','HH24:MI:SS')+(TO_DATE('"+String.valueOf(maxObj[0][0])+"','DD-MM-YYYY  HH24:MI:SS')- TO_DATE('"+String.valueOf(minObj[0][0])+"','DD-MM-YYYY  HH24:MI:SS')), +" +
						  			   " 'HH24:MI:SS') "+
									   " TIME FROM HELPDESK_ACTIVITY_LOG " +
									   " INNER JOIN HELPDESK_REQUEST_HDR ON(HELPDESK_REQUEST_HDR.REQUEST_ID=HELPDESK_ACTIVITY_LOG.REQUEST_ID"+
									   " )" +
									   " WHERE "+
									   " HELPDESK_REQUEST_HDR.REQUEST_ID="+appCode
									 + " GROUP BY HELPDESK_REQUEST_HDR.REQUEST_DATE ";
						  
						  Object[][] finalObj = getSqlModel().getSingleResult(query4);
							
							if(finalObj!=null && finalObj.length >0){
								
								Object[][] tempObj=new Object[1][3];
								  String days=String.valueOf(finalObj[0][0]);
										  if(!days.equals("0")){
											  tempObj[0][0] = Utility.checkNull((String
														.valueOf(finalObj[0][0])))
												+ " Days ";
										  }
								        
										  String time=String .valueOf(finalObj[0][1]);
										  String[] splitTime = time.split(":"); 
										  if(!splitTime[0].equals("00")){
											  Object hourdata=splitTime[0]; 
											  tempObj[0][1]=hourdata+" Hours ";
										  }
										  
										  String minutedata =splitTime[1]; 
										  String seconddata =splitTime[2];
										  tempObj[0][2]=minutedata+" Minutes " +seconddata+" Seconds";
										  
										  slaData[i][3]=Utility.checkNull(String.valueOf(tempObj[0][0]))+""+Utility.checkNull(String.valueOf(tempObj[0][1]))+""+ Utility.checkNull(String.valueOf(tempObj[0][2]));
							      }
								 result = true;
							}
							if(!result)
							{
								slaData[i][3] ="";
								
							}
						}
						
						
						String colnamesSla[] = { "Sr.No", "Status", "Duration", "Actual Duration" };
						int[] cellwidthsla = { 10, 20, 35, 35 };
						int[] alignmentsla = { 1, 1, 1, 1 };
						
						Object [][] slaDataObject = new Object[slaData.length+1][slaData[0].length]; 
						
						slaDataObject[0][0] = "1";
						slaDataObject[0][1] = "Open";
						for (int n = 1; n < slaDataObject.length; n++) {
							for (int j = 0; j < slaDataObject[0].length; j++) {
								slaDataObject[n][j] = String.valueOf(slaData[n-1][j]);
							}
						}
						
						TableDataSet slatab = new TableDataSet();
						slatab.setHeader(colnamesSla);
						slatab.setHeaderBorderDetail(3);
						slatab.setData(slaDataObject);
						slatab.setCellAlignment(alignmentsla);
						slatab.setCellWidth(cellwidthsla);
						slatab.setBorderDetail(3);
						slatab.setHeaderTable(true);
						slatab.setHeaderLines(true);
						slatab.setHeaderBGColor(new BaseColor(200, 200, 200));
						rg.addTableToDoc(slatab);
					} else {
						int[] cellwidth = { 10, 10, 10, 10 };
						int[] alignment = { 1, 1, 1, 1 };

						TableDataSet nosladata = new TableDataSet();
						Object[][] noslaDataObj = new Object[1][1];
						noslaDataObj[0][0] = "No records available";

						nosladata.setData(noslaDataObj);
						nosladata.setCellAlignment(alignment);
						nosladata.setCellWidth(cellwidth);
						nosladata.setBorderDetail(0);
						nosladata.setBlankRowsBelow(1);
						rg.addTableToDoc(nosladata);

					}
			}catch (Exception e) {
				e.printStackTrace();
			}
				  
		
			//new code ends
			
			/*
			
			Object[][] timeObj = getSqlModel().getSingleResult(timeQuery);
		
System.out.println("slaData.length ===== "+ slaData.length);
System.out.println("timeObj.length ===== "+ timeObj.length);
			if (slaData != null && slaData.length > 0) {
				for (int i = 0; i < slaData.length; i++) {
					boolean result = false;
					if(timeObj!=null && timeObj.length >0){
					for (int j = 0; j < timeObj.length; j++) {
					if(String.valueOf(slaData[i][1]).equals(String.valueOf(timeObj[j][3]))){
						
							//System.out.println("ste12---%%%%%----"+ ste12);
							String dhmValue =String.valueOf(slaData[i][3]);
							System.out.println("dhmValue ===== " + dhmValue);
							
							if(dhmValue.equals("Hours")){
								String ste12 = Utility.twoDecimals(String.valueOf(timeObj[j][1]));
								
								slaData[i][3]= ste12 +"  " +String.valueOf(slaData[i][3]);
								
							
								
							}
							 if(dhmValue.equals("Minutes")){
								 
								String ste12 = Utility.twoDecimals(String.valueOf(timeObj[j][2]));
								slaData[i][3]= ste12 +"  "+String.valueOf(slaData[i][3]);
							}
							
							 if(dhmValue.equals("Days")){
								String ste12 = Utility.twoDecimals(String.valueOf(timeObj[j][0]));
							   slaData[i][3]= ste12 +"  "+String.valueOf(slaData[i][3]);
							}
							
							 result = true;
						
					}
				}
					if(!result)
					{
						slaData[i][3] ="";
						
					}
					}	
			}
				String colnamesSla[] = { "Sr.No", "Status", "Duration", "Actual Duration" };
				int[] cellwidthsla = { 10, 20, 35, 35 };
				int[] alignmentsla = { 1, 1, 1, 1 };
				
				Object [][] slaDataObject = new Object[slaData.length+1][slaData[0].length]; 
				
				slaDataObject[0][0] = "1";
				slaDataObject[0][1] = "Open";
				for (int i = 1; i < slaDataObject.length; i++) {
					for (int j = 0; j < slaDataObject[0].length; j++) {
						slaDataObject[i][j] = String.valueOf(slaData[i-1][j]);
					}
				}
				
				TableDataSet slatab = new TableDataSet();
				slatab.setHeader(colnamesSla);
				slatab.setHeaderBorderDetail(3);
				slatab.setData(slaDataObject);
				slatab.setCellAlignment(alignmentsla);
				slatab.setCellWidth(cellwidthsla);
				slatab.setBorderDetail(3);
				slatab.setHeaderTable(true);
				slatab.setHeaderLines(true);
				slatab.setHeaderBGColor(new BaseColor(200, 200, 200));
				rg.addTableToDoc(slatab);
			} else {
				int[] cellwidth = { 10, 10, 10, 10 };
				int[] alignment = { 1, 1, 1, 1 };

				TableDataSet nosladata = new TableDataSet();
				Object[][] noslaDataObj = new Object[1][1];
				noslaDataObj[0][0] = "No records available";

				nosladata.setData(noslaDataObj);
				nosladata.setCellAlignment(alignment);
				nosladata.setCellWidth(cellwidth);
				//nosladata.setBodyFontDetails(Font.FontFamily.HELVETICA, 6,Font.NORMAL, new BaseColor(0, 0, 0));
				nosladata.setBorderDetail(0);
				nosladata.setBlankRowsBelow(1);
				rg.addTableToDoc(nosladata);

			}*/

			//History Details

			TableDataSet historyTable = new TableDataSet();
			Object objHis[][] = new Object[1][2];
			objHis[0][0] = "\n\n History Details : ";
			objHis[0][1] = " ";

			historyTable.setData(objHis);
			historyTable.setCellAlignment(new int[] { 0, 1 });
			historyTable.setCellWidth(new int[] { 15, 15 });
			//historyTable.setBodyFontDetails(FontFamily.HELVETICA, 12,	Font.BOLD, new BaseColor(0, 0, 0));
			historyTable.setBorderDetail(0);
			historyTable.setBlankRowsBelow(0);
			rg.addTableToDoc(historyTable);

			String historyQuesry = " SELECT ROWNUM,FROM_EMP.EMP_FNAME||' '||FROM_EMP.EMP_MNAME||' '||FROM_EMP.EMP_LNAME,TO_EMP.EMP_FNAME||' '||TO_EMP.EMP_MNAME||' '||TO_EMP.EMP_LNAME,"
					+ " HELPDESK_SLA_STATUS_CATAGORY.STATUS_CATAGORY_NAME,TO_CHAR(REQUEST_DATE,'DD-MM-YYYY HH24:MI:SS'),"
					+ " NVL(REQUEST_COMMENTS,' ') FROM HELPDESK_ACTIVITY_LOG "
					+ " LEFT JOIN HRMS_EMP_OFFC FROM_EMP  ON (FROM_EMP.EMP_ID=REQUEST_ACTION_BY)"
					+ " LEFT JOIN HRMS_EMP_OFFC TO_EMP ON (TO_EMP.EMP_ID=REQUEST_FORWARDED_TO)"
					+" LEFT JOIN HELPDESK_SLA_STATUS_CATAGORY ON(HELPDESK_SLA_STATUS_CATAGORY.STATUS_ABBREV =HELPDESK_ACTIVITY_LOG.REQUEST_STATUS)"
					+ " WHERE REQUEST_ID="
					+ appCode
					+ " ORDER BY REQUEST_DATE DESC";
			Object[][] historyData = getSqlModel().getSingleResult(
					historyQuesry);
			if (historyData != null & historyData.length > 0) {
				for (int i = 0; i < historyData.length; i++) {

					historyData[i][0] = i + 1;
				}

			}
			


			if (historyData != null && historyData.length > 0) {

				String colnames[] = { "Sr.No", "From", "To", "Status", "Date","Comments" };
				int[] cellwidth = { 20, 75, 75, 30, 30, 90 };
				int[] alignment = { 1, 1, 1, 1, 1, 1 };

				TableDataSet historytab = new TableDataSet();
				historytab.setHeader(colnames);
				
				historytab.setHeaderBorderDetail(3);
				historytab.setData(historyData);
				historytab.setCellAlignment(alignment);
				historytab.setCellWidth(cellwidth);
				historytab.setBorderDetail(3);
				//historytab.setHeaderTable(true);
				historytab.setHeaderLines(true);
				historytab.setHeaderBGColor(new BaseColor(200, 200, 200));

				rg.addTableToDoc(historytab);
				
				String footerQuery = "SELECT (EMP_TOKEN||'-'|| EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) "
					+ " FROM HRMS_EMP_OFFC "
					+ "WHERE EMP_ID="
					+ helpDeskProcess.getUserEmpId();

			Object[][] footerDetail = getSqlModel().getSingleResult(footerQuery);
			Date date = new Date();
			int[] cellWidthFooter = {70};
			int[] cellAlignFooter = {1};
			
			Object alignData [][] = new Object[1][1];
			alignData[0][0] = "\n ";
			TableDataSet footerDataAlign = new TableDataSet();
			footerDataAlign.setData(alignData);
			footerDataAlign.setCellWidth(cellWidthFooter);
			footerDataAlign.setCellAlignment(cellAlignFooter);
			footerDataAlign.setBlankRowsBelow(1);
			footerDataAlign.setBorderDetail(0);
			
			rg.addTableToDoc(footerDataAlign);
				
				
			Object footerObj[][] = new Object[1][2];
			footerObj[0][0] = "Generated By  :";
			footerObj[0][1] = String.valueOf(footerDetail[0][0]) + " on " + DateFormat.getDateTimeInstance().format(date);
			int[] footerDataCellWidth = {10,85};
			int[] footerDataCellAlign = {0,0};
			
			TableDataSet footerDataSet = new TableDataSet();
			footerDataSet.setData(footerObj);
			footerDataSet.setCellWidth(footerDataCellWidth);
			footerDataSet.setCellAlignment(footerDataCellAlign);
			footerDataSet.setBlankRowsBelow(1);
			footerDataSet.setBorderDetail(0);
			
			rg.addTableToDoc(footerDataSet);	
				
			} else {
				int[] cellwidth = { 100 };
				int[] alignment = {1};

				TableDataSet nodata = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";

				nodata.setData(noDataObj);
				nodata.setCellAlignment(alignment);
				nodata.setCellWidth(cellwidth);
				
				nodata.setBorderDetail(0);
				nodata.setBlankRowsBelow(1);
				rg.addTableToDoc(nodata);
			}

			try {
				rg.process();
				rg.createReport(response);
			} catch (RuntimeException e) {

				e.printStackTrace();
			}
		} catch (RuntimeException e) {

			e.printStackTrace();
		}
		

	}
	
	public TreeMap getStatusMap(){
		TreeMap map = new TreeMap();
		String sql = " SELECT  STATUS_ABBREV,STATUS_CATAGORY_NAME  FROM  HELPDESK_SLA_STATUS_CATAGORY  ";
		// + " where ASSET_CATEGORY_CODE= "+chk;
		Object[][] data = getSqlModel().getSingleResult(sql);
		map.put("","--Select--");
		for (int j = 0; j < data.length; j++) {

			map.put(String.valueOf(data[j][0]).trim(), String.valueOf(data[j][1]));

		}
		return map;
	}
	
}
