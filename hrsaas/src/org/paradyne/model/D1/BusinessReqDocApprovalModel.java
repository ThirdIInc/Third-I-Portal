package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.BusinessReqDocApproval;
import org.paradyne.bean.D1.BusinessRequirementDocument;
import org.paradyne.bean.D1.ClassRequestApproveBean;
import org.paradyne.bean.Recruitment.EmployeeRequisition;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class BusinessReqDocApprovalModel extends ModelBase {

	/**
	 * Set True.
	 */
	private static final String FLAG_TRUE = "true";
	/**
	 * Set 0.
	 */
	private static final String PAGE_ZERO = "0";
	/**
	 * Set 20.
	 */
	private static final String PAGE_TWENTY = "20";
	/**
	 * Set 1.
	 */
	private static final String PAGE_ONE = "1";

	public void viewData(BusinessReqDocApproval brdAppBean, String code) {
		System.out.println("hiiiiiii"+ code);
		try {
			String viewQuery = " select BRD_TICKET_NO, PROJECT_NAME, TO_CHAR(PROJ_START_DATE,'dd-mm-yyyy'),  TO_CHAR(PROJ_END_DATE,'dd-mm-yyy') ,PROJ_ALLOCATED_BUDGET,BISUNESS_OBJ,BUSINESS_CODE from HRMS_D1_BUSINESS_REQUIREMENT" +
					" where BUSINESS_CODE =" + code;
			                    	
			Object[][] data = getSqlModel().getSingleResult(viewQuery);
			if(data!= null & data.length>0){
				brdAppBean.setBrdNumber(checkNull(String.valueOf(data[0][0])));
				brdAppBean.setProjectName(checkNull(String.valueOf(data[0][1])));
				brdAppBean.setStartDate(checkNull(String.valueOf(data[0][2])));
				brdAppBean.setExpectedEndDate(checkNull(String.valueOf(data[0][3])));
				brdAppBean.setAllocatedBudget(checkNull(String.valueOf(data[0][4])));
				brdAppBean.setBusinessObjective(checkNull(String.valueOf(data[0][5])));
				brdAppBean.setViewCode(checkNull(String.valueOf(data[0][6])));

			}
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
	}

	public void ongoingList(BusinessReqDocApproval brdAppBean,
			HttpServletRequest request, String userId) {

		Object[][] queryData = null;
		String myOngoingQuery = " SELECT BRD_TICKET_NO, PROJECT_NAME, TO_CHAR(PROJ_END_DATE,'dd-mm-yyyy'),HRMS_D1_ROLE.ROLE_NAME,(hrms_emp_offc.EMP_TOKEN||''||'-'||''||hrms_emp_offc.EMP_FNAME||' '||hrms_emp_offc.EMP_MNAME||'  '||hrms_emp_offc.EMP_LNAME), BUSINESS_CODE,PROJ_FORWARD_EMPID "
			                        + " FROM HRMS_D1_BUSINESS_REQUIREMENT left join hrms_emp_offc on(hrms_emp_offc.emp_id = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_EMPID) "
			                    	+ " left join HRMS_D1_ROLE on(HRMS_D1_ROLE.ROLE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_TO)"
				                    + " WHERE PROJ_STATUS ='F' AND PROJ_FORWARD_EMPID="+userId +" ORDER BY BUSINESS_CODE DESC";
		queryData = getSqlModel().getSingleResult(myOngoingQuery);

		if (queryData != null && queryData.length > 0) {
			brdAppBean.setModeLength(BusinessReqDocApprovalModel.FLAG_TRUE);

			brdAppBean.setTotalNoOfRecords(String.valueOf(queryData.length));

			final String[] myOngoingPageIndex = Utility.doPaging(brdAppBean
					.getMyongoingProjectPage(), queryData.length, 20);
			if (myOngoingPageIndex == null) {
				myOngoingPageIndex[0] = BusinessReqDocApprovalModel.PAGE_ZERO;
				myOngoingPageIndex[1] = BusinessReqDocApprovalModel.PAGE_TWENTY;
				myOngoingPageIndex[2] = BusinessReqDocApprovalModel.PAGE_ONE;
				myOngoingPageIndex[3] = BusinessReqDocApprovalModel.PAGE_ONE;

			}

			request.setAttribute("totalPageOngoing", Integer.parseInt(String
					.valueOf(myOngoingPageIndex[2])));
			request.setAttribute("pageNoOngoing", Integer.parseInt(String
					.valueOf(myOngoingPageIndex[3])));
			if (BusinessReqDocApprovalModel.PAGE_ONE
					.equals(myOngoingPageIndex[4])) {
				brdAppBean
						.setMyongoingProjectPage(BusinessReqDocApprovalModel.PAGE_ONE);
			}
			final List<BusinessReqDocApproval> myOngoingList = new ArrayList<BusinessReqDocApproval>();
			brdAppBean.setFormyOngoingListLength(true);

			for (int i = Integer.parseInt(myOngoingPageIndex[0]); i < Integer
					.parseInt(myOngoingPageIndex[1]); i++) {

				BusinessReqDocApproval bean = new BusinessReqDocApproval();
				bean.setBrdNumber(checkNull(String.valueOf(queryData[i][0])));
				bean.setProjectName(checkNull(String.valueOf(queryData[i][1])));
				bean.setExpectedEndDate(checkNull(String
						.valueOf(queryData[i][2])));
				bean.setSelectRole(checkNull(String.valueOf(queryData[i][3])));
				bean.setForwardEmpName(checkNull(String.valueOf(queryData[i][4])));
				bean.setBrdAppCodeItt(checkNull(String.valueOf(queryData[i][5])));
				bean.setFwdempCode(checkNull(String.valueOf(queryData[i][6])));
				System.out.println("queryData[i][5]==========="
						+ queryData[i][5]);

				myOngoingList.add(bean);
			}

			brdAppBean.setMyAppOngoingList(myOngoingList);
		} else {
			brdAppBean.setMyAppOngoingList(null);
		}
	}

	public void closeAppList(BusinessReqDocApproval brdAppBean,
			HttpServletRequest request, String UserId) {
		Object[][] sendbackData = null;
		/*String myOngoingQuery = " SELECT BRD_TICKET_NO, PROJECT_NAME, TO_CHAR(PROJ_END_DATE,'dd-mm-yyyy'),HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE ,HRMS_D1_BUSINESS_PATH.BUSINESS_COMMENTS," 
				+ " (OFFC.EMP_TOKEN||''||'-'||''||OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||'  '||OFFC.EMP_LNAME) "+ " from HRMS_D1_BUSINESS_REQUIREMENT" 
                + " LEFT JOIN HRMS_D1_BUSINESS_PATH on (HRMS_D1_BUSINESS_PATH.BUSINESS_CODE = HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE) "
                + " LEFT JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_BUSINESS_PATH.BUSINESS_PROJ_CLOSE_BY) "
	 		    + " WHERE PROJ_STATUS = 'C' AND BUSINESS_PROJ_CLOSE_BY ="+ UserId;*/
		
		String myOngoingQuery = "SELECT BRD_TICKET_NO, PROJECT_NAME, TO_CHAR(PROJ_END_DATE,'dd-mm-yyyy') , HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE FROM HRMS_D1_BUSINESS_REQUIREMENT where PROJ_STATUS = 'C'  AND PROJ_FORWARD_EMPID = "+ UserId+" ORDER BY BUSINESS_CODE DESC";
		
		sendbackData = getSqlModel().getSingleResult(myOngoingQuery);

		if (sendbackData != null && sendbackData.length > 0) {
			brdAppBean.setModeLength(BusinessReqDocApprovalModel.FLAG_TRUE);

			brdAppBean.setTotalNoOfRecords(String.valueOf(sendbackData.length));

			final String[] mySendBackPageIndex = Utility.doPaging(brdAppBean
					.getMyClosedProjectPage(), sendbackData.length, 20);
			if (mySendBackPageIndex == null) {
				mySendBackPageIndex[0] = BusinessReqDocApprovalModel.PAGE_ZERO;
				mySendBackPageIndex[1] = BusinessReqDocApprovalModel.PAGE_TWENTY;
				mySendBackPageIndex[2] = BusinessReqDocApprovalModel.PAGE_ONE;
				mySendBackPageIndex[3] = BusinessReqDocApprovalModel.PAGE_ONE;

			}

			request.setAttribute("totalPageClosed", Integer.parseInt(String
					.valueOf(mySendBackPageIndex[2])));
			request.setAttribute("pageNoClosed", Integer.parseInt(String
					.valueOf(mySendBackPageIndex[3])));
			if (BusinessReqDocApprovalModel.PAGE_ONE
					.equals(mySendBackPageIndex[4])) {
				brdAppBean
						.setMyClosedProjectPage(BusinessReqDocApprovalModel.PAGE_ONE);
			}
			final List<BusinessReqDocApproval> myClosedList = new ArrayList<BusinessReqDocApproval>();
			brdAppBean.setMyCloseedListLength(true);

			for (int i = Integer.parseInt(mySendBackPageIndex[0]); i < Integer
					.parseInt(mySendBackPageIndex[1]); i++) {

				BusinessReqDocApproval bean = new BusinessReqDocApproval();
				bean.setBrdNumber(checkNull(String.valueOf(sendbackData[i][0])));
				bean.setProjectName(checkNull(String.valueOf(sendbackData[i][1])));
				bean.setExpectedEndDate(checkNull(String.valueOf(sendbackData[i][2])));
				bean.setBrdAppCodeItt(checkNull(String.valueOf(sendbackData[i][3])));
				myClosedList.add(bean);
			}

			brdAppBean.setMyAppClosedList(myClosedList);
		} else {
			brdAppBean.setMyAppClosedList(null);
		}

	}

	/**
	 * Method to check string is null or not.
	 * 
	 * @param result -
	 *            Check null or not
	 * @return - String after checking null
	 */
	public String checkNull(final String result) {
		if (result == null || "null".equals(result)) {
			return "";
		} else {
			return result;
		}
	}

	public String closed(BusinessReqDocApproval brdAppBean, String applicationId, String applStatus, String userID) {
		final String message = "";
        final String comments = brdAppBean.getComments().trim();
       // final String empRole = brdAppBean.getEmpRole().trim();
        final String fwdempCode = brdAppBean.getFwdempCode().trim();
       // final String currentState = brdAppBean.getCurrentState().trim();
       // final String documentAttached = brdAppBean.getDocumentAttached().trim();
       // final String uploadDocName = brdAppBean.getAttachFile().trim();
        
		
		
		
		try {
			final String changeStatusQuery = " UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET PROJ_STATUS = '" + applStatus + "'" + " WHERE BUSINESS_CODE = " + applicationId;
			this.getSqlModel().singleExecute(changeStatusQuery);

			this.insertApproverData(applicationId, comments,fwdempCode,userID);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return message;

	}

	/**
	 * @param applicationId - set application Id.
	 * @param userId - set  user id.
	 * @param approverComments - set Approver Comments.
	 * @param statusToUpdate - set ToUpdate status.
	 */
	private void insertApproverData(final String applicationId, final String approverComments, final String fwdempCode,String userID) {
		final String insertSql = " INSERT INTO HRMS_D1_BUSINESS_PATH (BUSINESS_CODE,BUSINESS_APPROVER_CODE, BUSINESS_COMMENTS, BUSINESS_APPR_DATE, BUSINESS_PATH_ID, BUSINESS_PROJ_CLOSE_BY, BUSINESS_PROJ_CLOSE_BY_DATE,BUSINESS_STATUS) "
				+ " VALUES (?,?,?,SYSDATE,(SELECT NVL(MAX(BUSINESS_PATH_ID), 0) + 1 FROM HRMS_D1_BUSINESS_PATH), ?, SYSDATE,?) ";
		final Object[][] insertObj = new Object[1][5];

		insertObj[0][0] = applicationId;
		insertObj[0][1] = fwdempCode;
		insertObj[0][2] = approverComments;
		//insertObj[0][3] = currentState;
		//insertObj[0][4] = empRole;
		//insertObj[0][5] = documentAttached; 
		//insertObj[0][6] = uploadDocName;
		insertObj[0][3] = userID;
		insertObj[0][4] = "C";
		this.getSqlModel().singleExecute(insertSql, insertObj);
	}

	//Map Functionality Starts.
	/**Method call for displaying dropdown list elements in onload jsp**/

	public void setDocumentType(BusinessReqDocApproval bean) {
		System.out.println("in setDocumentType-------------------------------");
		
		TreeMap<String, String> tmap = new TreeMap<String, String>();
		String emptypeQuery = "SELECT SCOPE_DOC_CODE,SCOPE_DOC_NAME FROM HRMS_D1_SCOPE_DOC  ORDER BY SCOPE_DOC_CODE";
		Object[][] emptypeObj = getSqlModel().getSingleResult(emptypeQuery);
		if (emptypeObj != null && emptypeObj.length > 0) {
			for (int i = 0; i < emptypeObj.length; i++) {
				tmap.put(String.valueOf(emptypeObj[i][0]), String
						.valueOf(emptypeObj[i][1]));
			}
		}
		bean.setMapDoc(tmap);

	}

	/**Method call for displaying dropdown list elements in onload jsp**/

	public void setRole(BusinessReqDocApproval bean) {
		System.out.println("in role-------------------------------");
		
		TreeMap<String, String> tmapRole = new TreeMap<String, String>();
		String emptypeQuery = "SELECT ROLE_CODE,ROLE_NAME FROM HRMS_D1_ROLE  ORDER BY ROLE_CODE";
		Object[][] emptypeObj = getSqlModel().getSingleResult(emptypeQuery);
		if (emptypeObj != null && emptypeObj.length > 0) {
			for (int i = 0; i < emptypeObj.length; i++) {
				tmapRole.put(String.valueOf(emptypeObj[i][0]), String
						.valueOf(emptypeObj[i][1]));
			}
		}
		bean.setMapRole(tmapRole);

	}

	/**Method call for displaying dropdown list elements in onload jsp**/

	public void setState(BusinessReqDocApproval bean) {
		System.out.println("in state-------------------------------");
		
		try {
			TreeMap<String, String> samplemap = new TreeMap<String, String>();
			String emptypeQuery = "SELECT STAGE_CODE,STAGE_NAME FROM HRMS_D1_STAGE  ORDER BY STAGE_CODE";
			Object[][] emptypeObj = getSqlModel().getSingleResult(emptypeQuery);
			if (emptypeObj != null && emptypeObj.length > 0) {
				for (int i = 0; i < emptypeObj.length; i++) {
					samplemap.put(String.valueOf(emptypeObj[i][0]), String
							.valueOf(emptypeObj[i][1]));
				}
			}
			bean.setMapState(samplemap);
			System.out.println("values >>>>>>>>>>>>"+bean.getMapState());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	public boolean saveInfo(BusinessReqDocApproval brdAppBean, String empId ,String applicationId) {

		/*String businessCodeQuery  = "SELECT  BUSINESS_CODE from HRMS_D1_BUSINESS_REQUIREMENT where BUSINESS_CODE ="+applicationId; 
		Object [][] businessObj = getSqlModel().getSingleResult(businessCodeQuery);
*/		
		String insertSql = " INSERT INTO HRMS_D1_BUSINESS_PATH (BUSINESS_CODE,BUSINESS_APPROVER_CODE, BUSINESS_COMMENTS, BUSINESS_APPR_DATE, BUSINESS_PATH_ID, BUSINESS_STATUS, BUSINESS_APPROVER_TYPE, BUSINESS_DOC_TYPE,BUSINESS_UPLOAD_FILE,BUSINESS_PROJ_CLOSE_BY,BUSINESS_PROJ_CLOSE_BY_DATE,BUSINESS_STATE) "
			+ " VALUES (?,?,?,SYSDATE,(SELECT NVL(MAX(BUSINESS_PATH_ID), 0) + 1 FROM HRMS_D1_BUSINESS_PATH), ?, ?, ?, ?,?,SYSDATE,?) ";

		Object[][] insertObj = new Object[1][9];
		insertObj[0][0] = applicationId;
		insertObj[0][1] = brdAppBean.getFwdempCode().trim();
		System.out.println("insertObj[0][1]------------------"+ insertObj[0][1]);
		insertObj[0][2] = brdAppBean.getComments().trim();
		insertObj[0][3] = "F";
		insertObj[0][4] = brdAppBean.getEmpRole().trim();
		insertObj[0][5] = brdAppBean.getDocumentAttached();
		insertObj[0][6] = brdAppBean.getAttachFile().trim();
		insertObj[0][7] = empId;
		insertObj[0][8]=brdAppBean.getCurrentState();
		for (int i = 0; i < insertObj.length; i++) {
			for (int j = 0; j < insertObj[i].length; j++) {

				System.out.println("updateObj[i][j]=========" + insertObj[i][j]);
			}
		}
		
		
		
		
		return getSqlModel().singleExecute(insertSql, insertObj);
	}

	public void setDetails(BusinessReqDocApproval brdAppBean, String code) {
		Object [][] viewObj = null;
		String query = " SELECT (hrms_emp_offc.EMP_TOKEN||''||'-'||''||hrms_emp_offc.EMP_FNAME||' '||hrms_emp_offc.EMP_MNAME||'  '||hrms_emp_offc.EMP_LNAME), TO_CHAR(BUSINESS_APPR_DATE,'dd-mm-yyyy'),HRMS_D1_STAGE.STAGE_NAME ,BUSINESS_COMMENTS,BUSINESS_CODE,BUSINESS_PROJ_CLOSE_BY "
                            + " FROM HRMS_D1_BUSINESS_PATH	"	
                            + " INNER JOIN HRMS_EMP_OFFC ON(hrms_emp_offc.emp_id = HRMS_D1_BUSINESS_PATH.BUSINESS_PROJ_CLOSE_BY)	"
                            + " LEFT JOIN HRMS_D1_STAGE ON (HRMS_D1_STAGE.STAGE_CODE = HRMS_D1_BUSINESS_PATH.BUSINESS_STATE)"
                            + " WHERE BUSINESS_CODE ="+code;

		viewObj = getSqlModel().getSingleResult(query);
		ArrayList<BusinessReqDocApproval> list = new ArrayList<BusinessReqDocApproval>();
		 
	if(viewObj!=null && viewObj.length>0){
		for (int i = 0; i < viewObj.length; i++){
			
			
			BusinessReqDocApproval bean = new BusinessReqDocApproval();
			bean.setConcernedPerson(checkNull(String.valueOf(viewObj[i][0])));
			bean.setApplDate(checkNull(String.valueOf(viewObj[i][1])));
			bean.setLogState(checkNull(String.valueOf(viewObj[i][2])));
			bean.setEmpComments(checkNull(String.valueOf(viewObj[i][3])));
			bean.setViewCode(checkNull(String.valueOf(viewObj[i][4])));
			bean.setAppCode(checkNull(String.valueOf(viewObj[i][5])));
			list.add(bean);
		}
	}
		
	brdAppBean.setLogList(list);
	}

	
	
	
	
	
	
	public void insertSate(BusinessReqDocApproval brdAppBean) {
	
		String insertQuery = "INSERT INTO HRMS_D1_BUSINESS_REQUIREMENT (PROJ_CURENT_STAGE) VALUES(?) ";
		Object[][] insertObj = new Object[1][1];
		
		insertObj[0][0] = brdAppBean.getCurrentState().trim();
		
		getSqlModel().singleExecute(insertQuery, insertObj);
	}

	public void updateStatus(BusinessReqDocApproval brdAppBean, String empId ,String appId) {
		// TODO Auto-generated method stub
		
			String updateQuery = " UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET PROJ_FORWARD_EMPID ="+empId + "where BUSINESS_CODE = "+appId; 
			getSqlModel().singleExecute(updateQuery);
		
	}
	
	public void updateCurrentStage(BusinessReqDocApproval brdAppBean, String code) {
		// TODO Auto-generated method stub
		System.out.println("code+++++++++++++++++++++++++    "+ code);
		
		String stageCode = brdAppBean.getCurrentState();
			String updateQuery = " UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET PROJ_CURENT_STAGE = +stageCode+ "
                                        + " WHERE BUSINESS_CODE ="+code; 
			getSqlModel().singleExecute(updateQuery);
		
	}
	

	public String sendBack(String applicationId, String userId) {
		final String message = "";

		try {
			final String updateQuery = " UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET PROJ_STATUS = 'B' " + " WHERE BUSINESS_CODE = " + applicationId;
			this.getSqlModel().singleExecute(updateQuery);

			
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return message;
		
	}

	public void setApproverDetails(BusinessReqDocApproval brdAppBean, String brdCode) {
		System.out.println("heloo000000000--------------------------------------------->>>>>>>>");
		
		try {
			String setQuery = " select BUSINESS_APPROVER_CODE,OFFC.EMP_TOKEN,(OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||'  '||OFFC.EMP_LNAME), BUSINESS_COMMENTS, BUSINESS_UPLOAD_FILE from HRMS_D1_BUSINESS_PATH  LEFT JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_BUSINESS_PATH.BUSINESS_APPROVER_CODE)  where BUSINESS_CODE = "
					+ brdCode;
			String setDetailsQuery = " select HRMS_D1_ROLE.ROLE_NAME,OFFC.EMP_TOKEN,(OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||'  '||OFFC.EMP_LNAME), BUSINESS_COMMENTS,HRMS_D1_STAGE.STAGE_NAME,HRMS_D1_SCOPE_DOC.SCOPE_DOC_NAME, BUSINESS_UPLOAD_FILE from HRMS_D1_BUSINESS_PATH  "
					+ "  LEFT JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_BUSINESS_PATH.BUSINESS_APPROVER_CODE) "
					+ " left join HRMS_D1_ROLE on (HRMS_D1_ROLE.ROLE_CODE = HRMS_D1_BUSINESS_PATH.BUSINESS_APPROVER_TYPE) "
					+ " left join HRMS_D1_STAGE on (HRMS_D1_STAGE.STAGE_CODE = HRMS_D1_BUSINESS_PATH.BUSINESS_STATUS) "
					+ " left join HRMS_D1_SCOPE_DOC on (HRMS_D1_SCOPE_DOC.SCOPE_DOC_CODE = HRMS_D1_BUSINESS_PATH.BUSINESS_DOC_TYPE) "
					+ " where BUSINESS_CODE = " + brdCode;
			Object[][] data = getSqlModel().getSingleResult(setDetailsQuery);
			System.out.println("LENGTH >>" + data.length);
			if (data != null && data.length > 0) {
				System.out.println("data[0][0]===============" + data[0][0]);
				brdAppBean.setEmpRole(this
						.checkNull(String.valueOf(data[0][0])));
				brdAppBean.setForwardEmpToken(this.checkNull(String
						.valueOf(data[0][1])));
				brdAppBean.setForwardEmpName(this.checkNull(String
						.valueOf(data[0][2])));
				brdAppBean.setComments(this.checkNull(String
						.valueOf(data[0][3])));
				System.out.println("data[0][4]===============" + data[0][4]);
				brdAppBean.setCurrentState(this.checkNull(String
						.valueOf(data[0][4])));
				System.out.println("data[0][5]===============" + data[0][5]);
				brdAppBean.setDocumentAttached(this.checkNull(String
						.valueOf(data[0][5])));
				brdAppBean.setAttachFile(this.checkNull(String
						.valueOf(data[0][6])));

				/**
				 * Set Map.
				 */
				/*	
						this.setRole(brdAppBean);
						this.setState(brdAppBean);
						this.setDocumentType(brdAppBean);
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void updateSendBackData(BusinessReqDocApproval brdAppBean,String applicationId) {
	System.out.println("updateSendBackData----------------------------------------------------");
		try {
	     	Object [][] updateObj = new Object[1][2];
			
			
			updateObj[0][0] = brdAppBean.getFwdempCode().trim();
			updateObj[0][1] = applicationId;
			
			for (int i = 0; i < updateObj.length; i++) {
				for (int j = 0; j < updateObj[i].length; j++) {

					System.out.println("updateObj[i][j]=========" + updateObj[i][j]);
				}
			}
			
			System.out.println("111111111111111111111111111111111111");
	             getSqlModel().singleExecute(this.getQuery(2), updateObj);
			
			
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	}

		
	

