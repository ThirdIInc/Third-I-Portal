package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.BusinessRequirementDocument;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;

/**
 * @modified By AA1711
 * @purpose : Change Request  
 */
public class BusinessRequirementDocumentModel extends ModelBase {
	private static final String FLAG_TRUE = "true";
	private static final String PAGE_ZERO = "0";
	private static final String PAGE_TWENTY = "20";
	private static final String PAGE_ONE = "1";

	/**Method Name: getcurrentUserAndSysDate()
	 * @purpose : Set values who create application
	 * @param brdBean
	 */
	public void getcurrentUserAndSysDate(BusinessRequirementDocument brdBean) {
		try {
			String currentUserQuery = "SELECT EMP_ID,EMP_TOKEN||' - '||EMP_FNAME||' '||EMP_LNAME, "
					+ " TO_CHAR(SYSDATE,'DD-MM-YYYY')"
					+ " FROM HRMS_EMP_OFFC WHERE EMP_ID = "
					+ brdBean.getUserEmpId();
			Object[][] currentUserObj = getSqlModel().getSingleResult(
					currentUserQuery);
			if ((currentUserObj != null) && (currentUserObj.length > 0)) {
				brdBean.setCompletedByID(checkNull(String
						.valueOf(currentUserObj[0][0])));
				brdBean.setCompletedBy(checkNull(String
						.valueOf(currentUserObj[0][1])));
				brdBean.setCompletedDate(checkNull(String
						.valueOf(currentUserObj[0][2])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: checkNull()
	 * @purpose : To check is NULL or not
	 * @param result
	 * @return String 
	 */
	public String checkNull(String result) {
		if ((result == null) || ("null".equals(result))) {
			return "";
		}
		return result;
	}

	/**Method Name: addInfo()
	 * @purpose : Insert values into table HRMS_D1_BUSINESS_REQUIREMENT
	 * @param brdBean
	 * @param userID
	 * @param uploadedFileName
	 * @return boolean
	 */
	public boolean addInfo(BusinessRequirementDocument brdBean, String userID,
			String uploadedFileName) {
		boolean result = false;
		try {
			Object[][] addObj = new Object[1][25];
			String query1 = "SELECT NVL(MAX(BUSINESS_CODE),0)+1 FROM  HRMS_D1_BUSINESS_REQUIREMENT";
			Object[][] code = getSqlModel().getSingleResult(query1);
			addObj[0][1] = brdBean.getProjectName().trim();
			addObj[0][2] = brdBean.getStartDate().trim();
			addObj[0][3] = brdBean.getExpectedEndDate().trim();
			addObj[0][4] = brdBean.getBusinessObjective().trim();
			addObj[0][5] = brdBean.getProjectClosureCriteria().trim();
			addObj[0][6] = brdBean.getConstraints().trim();
			addObj[0][7] = brdBean.getAssuptions().trim();
			addObj[0][8] = brdBean.getDocType();

			addObj[0][9] = uploadedFileName.trim();
			addObj[0][10] = brdBean.getSelectRole();
			addObj[0][11] = brdBean.getFwdempCode().trim();
			String autoCode = "";
			String selQuery = "SELECT NVL(MAX(BUSINESS_CODE),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(BUSINESS_CODE),0)+1\t,TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_D1_BUSINESS_REQUIREMENT";
			Object[][] selData = getSqlModel().getSingleResult(selQuery);
			if ("".equals(brdBean.getBrdCode())) {
				autoCode = String.valueOf(selData[0][0]);
				brdBean.setBrdCode(autoCode);

				String qq = " SELECT NVL(MAX(BUSINESS_CODE),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(BUSINESS_CODE),0)+1\t,TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_D1_BUSINESS_REQUIREMENT";
				Object[][] obj = getSqlModel().getSingleResult(qq);
				if ((obj != null) && (obj.length > 0)) {
					try {
						ApplCodeTemplateModel model = new ApplCodeTemplateModel();
						model.initiate(this.context, this.session);
						String applnCode = model.generateApplicationCode(String
								.valueOf(obj[0][1]), "D1-BRD", brdBean
								.getUserEmpId(), String.valueOf(obj[0][2]));
						brdBean.setBrdNumber(checkNull(applnCode));
						model.terminate();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			addObj[0][0] = brdBean.getBrdNumber().trim();
			addObj[0][12] = brdBean.getCompletedByID().trim();
			addObj[0][13] = brdBean.getCompletedDate().trim();
			addObj[0][14] = checkNull(String.valueOf(code[0][0]));
			addObj[0][15] = brdBean.getStatus().trim();
			if (addObj[0][15].equals("D"))
				addObj[0][16] = userID;
			else {
				addObj[0][16] = brdBean.getFwdempCode().trim();
			}
			addObj[0][17] = brdBean.getAllocatedBudget().trim();
			addObj[0][18] = brdBean.getApplicantsComments().trim();
			addObj[0][19] = brdBean.getPriority();

			addObj[0][20] = brdBean.getProjectType();
			addObj[0][21] = brdBean.getProjectClassification();
			addObj[0][22] = brdBean.getProjectAnnualFinancialBenefit();
			addObj[0][23] = brdBean.getBusinessUnitID();
			addObj[0][24] = brdBean.getRank();

			brdBean.setBrdCode(String.valueOf(code[0][0]));
			result = getSqlModel().singleExecute(getQuery(1), addObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**Method Name: modifyInfo()
	 * @purpose : Update values of HRMS_D1_BUSINESS_REQUIREMENT table
	 * @param brdBean
	 * @param userID
	 * @param uploadedFileName
	 * @return boolean
	 */
	public boolean modifyInfo(BusinessRequirementDocument brdBean,
			String userID, String uploadedFileName) {
		boolean result = false;
		try {
			Object[][] updateObj = new Object[1][22];
			updateObj[0][0] = brdBean.getProjectName().trim();
			updateObj[0][1] = brdBean.getStartDate().trim();
			updateObj[0][2] = brdBean.getExpectedEndDate().trim();
			updateObj[0][3] = brdBean.getBusinessObjective().trim();
			updateObj[0][4] = brdBean.getProjectClosureCriteria().trim();
			updateObj[0][5] = brdBean.getConstraints().trim();
			updateObj[0][6] = brdBean.getAssuptions().trim();
			updateObj[0][7] = brdBean.getDocType();

			updateObj[0][8] = uploadedFileName.trim();
			updateObj[0][9] = brdBean.getSelectRole();
			updateObj[0][10] = brdBean.getFwdempCode().trim();
			updateObj[0][11] = brdBean.getStatus().trim();
			updateObj[0][12] = brdBean.getAllocatedBudget().trim();
			if (updateObj[0][11].equals("D"))
				updateObj[0][13] = userID;
			else {
				updateObj[0][13] = brdBean.getFwdempCode().trim();
			}
			updateObj[0][14] = brdBean.getApplicantsComments().trim();
			updateObj[0][15] = brdBean.getPriority();
			updateObj[0][16] = brdBean.getProjectType();
			updateObj[0][17] = brdBean.getProjectClassification();
			updateObj[0][18] = brdBean.getProjectAnnualFinancialBenefit();
			updateObj[0][19] = brdBean.getBusinessUnitID().trim();
			updateObj[0][20] = brdBean.getRank().trim();
			updateObj[0][21] = brdBean.getBrdCode().trim();
			result = getSqlModel().singleExecute(getQuery(2), updateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**Method Name: myAction()
	 * @purpose : Display forwarded and drafted record to particular Employee to whom it forwarded to 
	 * @param brdBean
	 * @param request
	 * @param userId
	 */
	public void myAction(BusinessRequirementDocument brdBean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] queryData = (Object[][]) null;
			String myActionQuery = "SELECT BRD_TICKET_NO, PROJECT_NAME, TO_CHAR(PROJ_END_DATE,'DD-MM-YYYY'), "
					+ " STAGE_NAME,BUSINESS_CODE,PROJ_STATUS "
					+ " FROM HRMS_D1_BUSINESS_REQUIREMENT "
					+ " LEFT JOIN HRMS_D1_STAGE ON (HRMS_D1_STAGE.STAGE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CURENT_STAGE)"
					+ " WHERE PROJ_STATUS IN('D','F','B') AND PROJ_INITIATOR =  "
					+ userId + " ORDER BY BRD_TICKET_NO DESC";
			queryData = getSqlModel().getSingleResult(myActionQuery);
			if ((queryData != null) && (queryData.length > 0)) {
				brdBean.setModeLength("true");

				brdBean.setTotalNoOfRecords(String.valueOf(queryData.length));

				String[] myActionPageIndex = Utility.doPaging(brdBean
						.getForMyActionPage(), queryData.length, 20);
				if (myActionPageIndex == null) {
					myActionPageIndex[0] = "0";
					myActionPageIndex[1] = "20";
					myActionPageIndex[2] = "1";
					myActionPageIndex[3] = "1";
				}

				request.setAttribute("totalPageMyAction", Integer
						.valueOf(Integer.parseInt(String
								.valueOf(myActionPageIndex[2]))));
				request.setAttribute("pageNoMyAction", Integer.valueOf(Integer
						.parseInt(String.valueOf(myActionPageIndex[3]))));

				if ("1".equals(myActionPageIndex[4])) {
					brdBean.setForMyActionPage("1");
				}
				List myActionList = new ArrayList();
				brdBean.setFormyActionListLength(true);

				for (int i = 0; i < queryData.length; i++) {
					BusinessRequirementDocument bean = new BusinessRequirementDocument();
					bean
							.setBrdNumber(checkNull(String
									.valueOf(queryData[i][0])));
					bean.setProjectName(checkNull(String
							.valueOf(queryData[i][1])));
					bean.setExpectedEndDate(checkNull(String
							.valueOf(queryData[i][2])));
					bean.setCurrentStage(checkNull(String
							.valueOf(queryData[i][3])));
					bean.setBrdCode(checkNull(String.valueOf(queryData[i][4])));
					bean.setStatus(checkNull(String.valueOf(queryData[i][5])));

					myActionList.add(bean);
				}

				brdBean.setForMyActionList(myActionList);
			} else {
				brdBean.setForMyActionList(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: setDocumentType()
	 * @purpose : set values into dropdown
	 * @param bean
	 */
	public void setDocumentType(BusinessRequirementDocument bean) {
		try {
			TreeMap tmap = new TreeMap();
			String emptypeQuery = "SELECT SCOPE_DOC_CODE,SCOPE_DOC_NAME FROM HRMS_D1_SCOPE_DOC  ORDER BY SCOPE_DOC_CODE";
			Object[][] emptypeObj = getSqlModel().getSingleResult(emptypeQuery);
			if ((emptypeObj != null) && (emptypeObj.length > 0)) {
				for (int i = 0; i < emptypeObj.length; i++) {
					tmap.put(String.valueOf(emptypeObj[i][0]), String
							.valueOf(emptypeObj[i][1]));
				}
			}
			bean.setMapDoc(tmap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: setState()
	 * @purpose : set State name 
	 * @param bean
	 */
	public void setState(BusinessRequirementDocument bean) {
		try {
			TreeMap samplemap = new TreeMap();
			String emptypeQuery = "SELECT STAGE_CODE,STAGE_NAME FROM HRMS_D1_STAGE  ORDER BY STAGE_CODE";
			Object[][] emptypeObj = getSqlModel().getSingleResult(emptypeQuery);
			if ((emptypeObj != null) && (emptypeObj.length > 0)) {
				for (int i = 0; i < emptypeObj.length; i++) {
					samplemap.put(String.valueOf(emptypeObj[i][0]), String
							.valueOf(emptypeObj[i][1]));
				}
			}
			bean.setMapState(samplemap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: setEmployeeRole()
	 * @purpose : set Employee Role 
	 * @param bean
	 */
	public void setEmployeeRole(BusinessRequirementDocument bean) {
		try {
			TreeMap tmapRole = new TreeMap();
			String emptypeQuery = "SELECT ROLE_CODE,ROLE_NAME FROM HRMS_D1_ROLE  ORDER BY ROLE_CODE";
			Object[][] emptypeObj = getSqlModel().getSingleResult(emptypeQuery);
			if ((emptypeObj != null) && (emptypeObj.length > 0)) {
				for (int i = 0; i < emptypeObj.length; i++) {
					tmapRole.put(String.valueOf(emptypeObj[i][0]), String
							.valueOf(emptypeObj[i][1]));
				}
			}
			bean.setMapRole(tmapRole);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Method Name: setProjectTypeDropDown()
	 * @purpose : set Project Type's Values
	 * @param bean
	 */
	public void setProjectTypeDropDown(BusinessRequirementDocument bean) {
		try {
			TreeMap tempMap = new TreeMap();
			String typeQuery = "SELECT HRMS_D1_BRD_TYPE.TYPE_ID, HRMS_D1_BRD_TYPE.TYPE_NAME FROM HRMS_D1_BRD_TYPE ORDER BY HRMS_D1_BRD_TYPE.TYPE_ID";
			Object[][] projectTypeObj = getSqlModel()
					.getSingleResult(typeQuery);
			if ((projectTypeObj != null) && (projectTypeObj.length > 0)) {
				for (int i = 0; i < projectTypeObj.length; i++) {
					tempMap.put(String.valueOf(projectTypeObj[i][0]), String
							.valueOf(projectTypeObj[i][1]));
				}
			}
			bean.setMapProjectType(tempMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: setProjectClassificationDropDown
	 * @purpose : set Project Classification values into Dropdown
	 * @param bean
	 */
	public void setProjectClassificationDropDown(
			BusinessRequirementDocument bean) {
		try {
			TreeMap tempMap = new TreeMap();
			String classificationQuery = "SELECT HRMS_D1_BRD_CLASSIFICATION.CLASSIFICATION_ID, HRMS_D1_BRD_CLASSIFICATION.CLASSIFICATION_NAME FROM HRMS_D1_BRD_CLASSIFICATION ORDER BY HRMS_D1_BRD_CLASSIFICATION.CLASSIFICATION_ID";
			Object[][] projectTypeObj = getSqlModel().getSingleResult(
					classificationQuery);
			if ((projectTypeObj != null) && (projectTypeObj.length > 0)) {
				for (int i = 0; i < projectTypeObj.length; i++) {
					tempMap.put(String.valueOf(projectTypeObj[i][0]), String
							.valueOf(projectTypeObj[i][1]));
				}
			}
			bean.setMapProjectClassification(tempMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: myonGoingProj()
	 * @purpose : Application display to Employee
	 * 			who is involve into that application as Stakeholder, Initiator, Forwarded one etc. 
	 * @param brdBean
	 * @param request
	 * @param userId
	 */
	public void myonGoingProj(BusinessRequirementDocument brdBean,
			HttpServletRequest request, String userId) {
		try {
			List myOngoingList = new ArrayList();
			String query = "SELECT PROJ_STAKE_HOLDER,BUSINESS_CODE from HRMS_D1_BUSINESS_REQUIREMENT WHERE PROJ_STATUS IN('F','B') ";
			Object[][] obj = getSqlModel().getSingleResult(query);
			String businesscode = "0";
			if ((obj != null) && (obj.length > 0)) {
				for (int i = 0; i < obj.length; i++) {
					String[] str = String.valueOf(obj[i][0]).split(",");

					if ((str != null) && (str.length > 0)) {
						for (int j = 0; j < str.length; j++) {
							if (userId.equals(str[j])) {
								businesscode = businesscode + ","
										+ String.valueOf(obj[i][1]);
							}
						}
					}
				}
			}
			String code = "0,0";
			Object[][] queryData = (Object[][]) null;
			Object[][] pathData = (Object[][]) null;

			String pathQuery = "SELECT BUSINESS_CODE FROM HRMS_D1_BUSINESS_PATH "
					+ " WHERE BUSINESS_FORCASTED_DATE IS NULL AND BUSINESS_PROJ_CLOSE_BY ="
					+ userId;
			pathData = getSqlModel().getSingleResult(pathQuery);
			if ((pathData != null) && (pathData.length > 0)) {
				for (int i = 0; i < pathData.length; i++) {
					code = code + "," + String.valueOf(pathData[i][0]);
				}
			}
			String pathQuery1 = "SELECT BUSINESS_CODE FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE PROJ_COMPLETED_BY ="
					+ userId;
			Object[][] pathDataObj = getSqlModel().getSingleResult(pathQuery1);

			if ((pathDataObj != null) && (pathDataObj.length > 0)) {
				for (int i = 0; i < pathDataObj.length; i++) {
					code = code + "," + String.valueOf(pathDataObj[i][0]);
				}
			}

			if (((pathData != null) && (pathData.length > 0))
					|| ((pathDataObj != null) && (pathDataObj.length > 0))) {
				String myOngoingQuery = " SELECT BRD_TICKET_NO, PROJECT_NAME, TO_CHAR(PROJ_END_DATE,'DD-MM-YYYY'),"
						+ " STAGE_NAME, HRMS_D1_ROLE.ROLE_NAME,"
						+ " (HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME),BUSINESS_CODE"
						+ " FROM HRMS_D1_BUSINESS_REQUIREMENT "
						+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_EMPID)"
						+ " LEFT JOIN HRMS_D1_STAGE ON(HRMS_D1_STAGE.STAGE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CURENT_STAGE)"
						+ " LEFT JOIN HRMS_D1_ROLE ON(HRMS_D1_ROLE.ROLE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_TO)"
						+ " WHERE PROJ_STATUS IN('F','B') AND HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE IN("
						+ code + ")" + " ORDER BY BRD_TICKET_NO DESC";
				queryData = getSqlModel().getSingleResult(myOngoingQuery);

				if ((queryData != null) && (queryData.length > 0)) {
					brdBean.setModeLength("true");
					brdBean.setTotalNoOfRecords(String
							.valueOf(queryData.length));
					String[] myOngoingPageIndex = Utility.doPaging(brdBean
							.getMyongoingProjectPage(), queryData.length, 15);
					if (myOngoingPageIndex == null) {
						myOngoingPageIndex[0] = "0";
						myOngoingPageIndex[1] = "15";
						myOngoingPageIndex[2] = "1";
						myOngoingPageIndex[3] = "1";
					}

					request.setAttribute("totalPageOngoing", Integer
							.valueOf(Integer.parseInt(String
									.valueOf(myOngoingPageIndex[2]))));
					request.setAttribute("pageNoOngoing", Integer
							.valueOf(Integer.parseInt(String
									.valueOf(myOngoingPageIndex[3]))));
					if ("1".equals(myOngoingPageIndex[4])) {
						brdBean.setMyongoingProjectPage("1");
					}

					brdBean.setFormyOngoingListLength(true);

					for (int i = Integer.parseInt(myOngoingPageIndex[0]); i < Integer
							.parseInt(myOngoingPageIndex[1]); i++) {
						BusinessRequirementDocument bean = new BusinessRequirementDocument();
						bean.setBrdNumber(checkNull(String
								.valueOf(queryData[i][0])));
						bean.setProjectName(checkNull(String
								.valueOf(queryData[i][1])));
						bean.setExpectedEndDate(checkNull(String
								.valueOf(queryData[i][2])));
						bean.setCurrentStage(checkNull(String
								.valueOf(queryData[i][3])));
						bean.setSelectRole(checkNull(String
								.valueOf(queryData[i][4])));
						bean.setForwardEmpName(checkNull(String
								.valueOf(queryData[i][5])));
						bean.setBrdCode(checkNull(String
								.valueOf(queryData[i][6])));
						myOngoingList.add(bean);
					}
					brdBean.setForMyOngoingList(myOngoingList);
				} else {
					brdBean.setForMyOngoingList(null);
				}
			}

			String BRDCODE = "0";
			if ((code.contains(",")) && (businesscode.contains(","))) {
				String[] str = code.split(",");
				String[] strBrd = businesscode.split(",");
				for (int i = 0; i < strBrd.length; i++) {
					boolean result = true;
					for (int j = 0; j < str.length; j++) {
						if (strBrd[i].equals(str[j])) {
							result = false;
						}
					}
					if (result) {
						BRDCODE = BRDCODE + "," + strBrd[i];
					}
				}
			}
			String myStakeHolderOngoingQuery = " SELECT BRD_TICKET_NO, PROJECT_NAME, TO_CHAR(PROJ_END_DATE,'dd-mm-yyyy'),STAGE_NAME,(hrms_emp_offc.EMP_TOKEN||''||'-'||''||hrms_emp_offc.EMP_FNAME||' '||hrms_emp_offc.EMP_MNAME||'  '||hrms_emp_offc.EMP_LNAME), HRMS_D1_ROLE.ROLE_NAME,BUSINESS_CODE  FROM HRMS_D1_BUSINESS_REQUIREMENT  left join hrms_emp_offc on(hrms_emp_offc.emp_id = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_EMPID)  left join HRMS_D1_STAGE on(HRMS_D1_STAGE.STAGE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CURENT_STAGE)  left join HRMS_D1_ROLE on(HRMS_D1_ROLE.ROLE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_TO)  WHERE BUSINESS_CODE in ("
					+ BRDCODE + ") ORDER BY BRD_TICKET_NO DESC";
			Object[][] stakeData = getSqlModel().getSingleResult(
					myStakeHolderOngoingQuery);

			if ((stakeData != null) && (stakeData.length > 0)) {
				brdBean.setModeLength("true");
				brdBean.setTotalNoOfRecords(String.valueOf(stakeData.length));
				String[] myOngoingPageIndex = Utility.doPaging(brdBean
						.getMyongoingProjectPage(), stakeData.length, 15);
				if (myOngoingPageIndex == null) {
					myOngoingPageIndex[0] = "0";
					myOngoingPageIndex[1] = "15";
					myOngoingPageIndex[2] = "1";
					myOngoingPageIndex[3] = "1";
				}

				request.setAttribute("totalPageOngoing", Integer
						.valueOf(Integer.parseInt(String
								.valueOf(myOngoingPageIndex[2]))));
				request.setAttribute("pageNoOngoing", Integer.valueOf(Integer
						.parseInt(String.valueOf(myOngoingPageIndex[3]))));
				if ("1".equals(myOngoingPageIndex[4])) {
					brdBean.setMyongoingProjectPage("1");
				}

				brdBean.setFormyOngoingListLength(true);
				for (int i = Integer.parseInt(myOngoingPageIndex[0]); i < Integer
						.parseInt(myOngoingPageIndex[1]); i++) {
					BusinessRequirementDocument bean = new BusinessRequirementDocument();
					bean
							.setBrdNumber(checkNull(String
									.valueOf(stakeData[i][0])));
					bean.setProjectName(checkNull(String
							.valueOf(stakeData[i][1])));
					bean.setExpectedEndDate(checkNull(String
							.valueOf(stakeData[i][2])));
					bean.setCurrentStage(checkNull(String
							.valueOf(stakeData[i][3])));
					bean.setForwardEmpName(checkNull(String
							.valueOf(stakeData[i][4])));
					bean.setSelectRole(checkNull(String
							.valueOf(stakeData[i][5])));
					bean.setBrdCode(checkNull(String.valueOf(stakeData[i][6])));
					myOngoingList.add(bean);
				}
				brdBean.setForMyOngoingList(myOngoingList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Method Name: mycloseProj()
	 * @purpose : Display Closed Project List
	 * @param brdBean
	 * @param request
	 * @param userId
	 */
	public void mycloseProj(BusinessRequirementDocument brdBean,
			HttpServletRequest request, String userId) {
		try {
			String query = "SELECT  PROJ_STAKE_HOLDER,BUSINESS_CODE FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE PROJ_STATUS='C' ";
			Object[][] obj = getSqlModel().getSingleResult(query);
			String businesscode = "0";
			if ((obj != null) && (obj.length > 0)) {
				for (int i = 0; i < obj.length; i++) {
					String[] str = String.valueOf(obj[i][0]).split(",");

					if ((str != null) && (str.length > 0)) {
						for (int j = 0; j < str.length; j++) {
							if (userId.equals(str[j])) {
								businesscode = businesscode + ","
										+ String.valueOf(obj[i][1]);
							}
						}
					}
				}
			}
			Object[][] queryData = (Object[][]) null;
			Object[][] pathData = (Object[][]) null;
			String code = "0";
			String pathQuery = "SELECT BUSINESS_CODE FROM HRMS_D1_BUSINESS_PATH WHERE BUSINESS_PROJ_CLOSE_BY ="
					+ userId;
			pathData = getSqlModel().getSingleResult(pathQuery);
			if ((pathData != null) && (pathData.length > 0)) {
				for (int i = 0; i < pathData.length; i++) {
					code = code + "," + String.valueOf(pathData[i][0]);
				}
			}
			String pathQuery1 = "SELECT BUSINESS_CODE FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE PROJ_COMPLETED_BY ="
					+ userId;
			Object[][] pathDataObj = getSqlModel().getSingleResult(pathQuery1);
			if ((pathDataObj != null) && (pathDataObj.length > 0)) {
				for (int i = 0; i < pathDataObj.length; i++) {
					code = code + "," + String.valueOf(pathDataObj[i][0]);
				}
			}
			if (((pathData != null) && (pathData.length > 0))
					|| ((pathDataObj != null) && (pathDataObj.length > 0))) {
				String myCloseQuery = " SELECT BRD_TICKET_NO, PROJECT_NAME, TO_CHAR(PROJ_END_DATE,'DD-MM-YYYY'),BUSINESS_CODE "
						+ " FROM HRMS_D1_BUSINESS_REQUIREMENT "
						+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_EMPID)"
						+ " LEFT JOIN HRMS_D1_STAGE ON(HRMS_D1_STAGE.STAGE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CURENT_STAGE)"
						+ " LEFT JOIN HRMS_D1_ROLE ON(HRMS_D1_ROLE.ROLE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_TO) "
						+ " WHERE PROJ_STATUS = 'C' AND HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE IN("
						+ code + ") " + " ORDER BY BRD_TICKET_NO DESC";
				queryData = getSqlModel().getSingleResult(myCloseQuery);
				if ((queryData != null) && (queryData.length > 0)) {
					brdBean.setModeLength("true");
					brdBean.setTotalNoOfRecords(String
							.valueOf(queryData.length));
					String[] myClosedPageIndex = Utility.doPaging(brdBean
							.getMyCloseProjectPage(), queryData.length, 20);
					if (myClosedPageIndex == null) {
						myClosedPageIndex[0] = "0";
						myClosedPageIndex[1] = "20";
						myClosedPageIndex[2] = "1";
						myClosedPageIndex[3] = "1";
					}

					request.setAttribute("totalPageClosed", Integer
							.valueOf(Integer.parseInt(String
									.valueOf(myClosedPageIndex[2]))));
					request.setAttribute("pageNoClosed", Integer
							.valueOf(Integer.parseInt(String
									.valueOf(myClosedPageIndex[3]))));
					if ("1".equals(myClosedPageIndex[4])) {
						brdBean.setMyCloseProjectPage("1");
					}
					List myCloseList = new ArrayList();
					brdBean.setFormyClosedListLength(true);

					for (int i = Integer.parseInt(myClosedPageIndex[0]); i < Integer
							.parseInt(myClosedPageIndex[1]); i++) {
						BusinessRequirementDocument bean = new BusinessRequirementDocument();
						bean.setBrdNumber(checkNull(String
								.valueOf(queryData[i][0])));
						bean.setProjectName(checkNull(String
								.valueOf(queryData[i][1])));
						bean.setExpectedEndDate(checkNull(String
								.valueOf(queryData[i][2])));
						bean.setBrdCode(checkNull(String
								.valueOf(queryData[i][3])));
						myCloseList.add(bean);
					}
					brdBean.setForMyClosedList(myCloseList);
				} else {
					brdBean.setForMyClosedList(null);
				}
			}

			String myStakeHolderCloseQuery = " SELECT BRD_TICKET_NO, PROJECT_NAME, "
					+ " TO_CHAR(PROJ_END_DATE,'DD-MM-YYYY'),BUSINESS_CODE "
					+ " FROM HRMS_D1_BUSINESS_REQUIREMENT  WHERE BUSINESS_CODE IN ("
					+ businesscode + ")" + " ORDER BY BUSINESS_CODE DESC";
			Object[][] stakeData = getSqlModel().getSingleResult(
					myStakeHolderCloseQuery);
			if ((stakeData != null) && (stakeData.length > 0)) {
				brdBean.setModeLength("true");
				brdBean.setTotalNoOfRecords(String.valueOf(stakeData.length));
				String[] myClosedPageIndex = Utility.doPaging(brdBean
						.getMyCloseProjectPage(), stakeData.length, 20);
				if (myClosedPageIndex == null) {
					myClosedPageIndex[0] = "0";
					myClosedPageIndex[1] = "20";
					myClosedPageIndex[2] = "1";
					myClosedPageIndex[3] = "1";
				}

				request.setAttribute("totalPageClosed", Integer.valueOf(Integer
						.parseInt(String.valueOf(myClosedPageIndex[2]))));
				request.setAttribute("pageNoClosed", Integer.valueOf(Integer
						.parseInt(String.valueOf(myClosedPageIndex[3]))));
				if ("1".equals(myClosedPageIndex[4])) {
					brdBean.setMyCloseProjectPage("1");
				}
				List myCloseList = new ArrayList();
				brdBean.setFormyClosedListLength(true);

				for (int i = Integer.parseInt(myClosedPageIndex[0]); i < Integer
						.parseInt(myClosedPageIndex[1]); i++) {
					BusinessRequirementDocument bean = new BusinessRequirementDocument();
					bean
							.setBrdNumber(checkNull(String
									.valueOf(stakeData[i][0])));
					bean.setProjectName(checkNull(String
							.valueOf(stakeData[i][1])));
					bean.setExpectedEndDate(checkNull(String
							.valueOf(stakeData[i][2])));
					bean.setBrdCode(checkNull(String.valueOf(stakeData[i][3])));
					myCloseList.add(bean);
				}
				brdBean.setForMyClosedList(myCloseList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: myCancelProject()
	 * @purpose :Display Cancel Project Records
	 * @param brdBean
	 * @param request
	 * @param userId
	 */
	public void myCancelProject(BusinessRequirementDocument brdBean,
			HttpServletRequest request, String userId) {
		try {
			String query = "SELECT PROJ_STAKE_HOLDER,BUSINESS_CODE from HRMS_D1_BUSINESS_REQUIREMENT WHERE PROJ_STATUS='Z' ";
			Object[][] obj = getSqlModel().getSingleResult(query);
			String businesscode = "0";
			if ((obj != null) && (obj.length > 0)) {
				for (int i = 0; i < obj.length; i++) {
					String[] str = String.valueOf(obj[i][0]).split(",");

					if ((str != null) && (str.length > 0)) {
						for (int j = 0; j < str.length; j++) {
							if (userId.equals(str[j])) {
								businesscode = businesscode + ","
										+ String.valueOf(obj[i][1]);
							}
						}
					}
				}
			}
			Object[][] queryData = (Object[][]) null;
			Object[][] pathData = (Object[][]) null;
			String code = "0";
			String pathQuery = "SELECT BUSINESS_CODE FROM HRMS_D1_BUSINESS_PATH WHERE BUSINESS_PROJ_CLOSE_BY ="
					+ userId;
			pathData = getSqlModel().getSingleResult(pathQuery);
			if ((pathData != null) && (pathData.length > 0)) {
				for (int i = 0; i < pathData.length; i++) {
					code = code + "," + String.valueOf(pathData[i][0]);
				}
			}
			String pathQuery1 = "SELECT BUSINESS_CODE FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE PROJ_COMPLETED_BY ="
					+ userId;
			Object[][] pathDataObj = getSqlModel().getSingleResult(pathQuery1);
			if ((pathDataObj != null) && (pathDataObj.length > 0)) {
				for (int i = 0; i < pathDataObj.length; i++) {
					code = code + "," + String.valueOf(pathDataObj[i][0]);
				}
			}
			if (((pathData != null) && (pathData.length > 0))
					|| ((pathDataObj != null) && (pathDataObj.length > 0))) {
				String myCancelQuery = " SELECT BRD_TICKET_NO, PROJECT_NAME, "
						+ " TO_CHAR(PROJ_END_DATE,'DD-MM-YYYY'), BUSINESS_CODE"
						+ " FROM HRMS_D1_BUSINESS_REQUIREMENT "
						+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_EMPID) "
						+ " LEFT JOIN HRMS_D1_STAGE ON(HRMS_D1_STAGE.STAGE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CURENT_STAGE)"
						+ " LEFT JOIN HRMS_D1_ROLE ON(HRMS_D1_ROLE.ROLE_CODE = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_TO)"
						+ " WHERE PROJ_STATUS = 'Z' AND HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE IN("
						+ code + ") " + " ORDER BY BRD_TICKET_NO DESC";
				queryData = getSqlModel().getSingleResult(myCancelQuery);
				if ((queryData != null) && (queryData.length > 0)) {
					brdBean.setModeLength("true");
					brdBean.setTotalNoOfRecords(String
							.valueOf(queryData.length));
					String[] myCancelPageIndex = Utility.doPaging(brdBean
							.getMyCancelProjectPage(), queryData.length, 20);
					if (myCancelPageIndex == null) {
						myCancelPageIndex[0] = "0";
						myCancelPageIndex[1] = "20";
						myCancelPageIndex[2] = "1";
						myCancelPageIndex[3] = "1";
					}

					request.setAttribute("totalPageCancelled", Integer
							.valueOf(Integer.parseInt(String
									.valueOf(myCancelPageIndex[2]))));
					request.setAttribute("pageNoCancelled", Integer
							.valueOf(Integer.parseInt(String
									.valueOf(myCancelPageIndex[3]))));
					if ("1".equals(myCancelPageIndex[4])) {
						brdBean.setMyCancelProjectPage("1");
					}
					List myCloseList = new ArrayList();
					brdBean.setMyCancelListLength(true);

					for (int i = Integer.parseInt(myCancelPageIndex[0]); i < Integer
							.parseInt(myCancelPageIndex[1]); i++) {
						BusinessRequirementDocument bean = new BusinessRequirementDocument();
						bean.setBrdNumber(checkNull(String
								.valueOf(queryData[i][0])));
						bean.setProjectName(checkNull(String
								.valueOf(queryData[i][1])));
						bean.setExpectedEndDate(checkNull(String
								.valueOf(queryData[i][2])));
						bean.setBrdCode(checkNull(String
								.valueOf(queryData[i][3])));
						myCloseList.add(bean);
					}
					brdBean.setMyCancelList(myCloseList);
				} else {
					brdBean.setMyCancelList(null);
				}
			}

			String myStakeHolderCancelQuery = " SELECT BRD_TICKET_NO, PROJECT_NAME, TO_CHAR(PROJ_END_DATE,'dd-mm-yyyy'),BUSINESS_CODE  FROM HRMS_D1_BUSINESS_REQUIREMENT  WHERE BUSINESS_CODE in ("
					+ businesscode + ") ORDER BY BUSINESS_CODE DESC";
			Object[][] stakeData = getSqlModel().getSingleResult(
					myStakeHolderCancelQuery);
			if ((stakeData != null) && (stakeData.length > 0)) {
				brdBean.setModeLength("true");
				brdBean.setTotalNoOfRecords(String.valueOf(stakeData.length));
				String[] myCancelPageIndex = Utility.doPaging(brdBean
						.getMyCancelProjectPage(), stakeData.length, 20);
				if (myCancelPageIndex == null) {
					myCancelPageIndex[0] = "0";
					myCancelPageIndex[1] = "20";
					myCancelPageIndex[2] = "1";
					myCancelPageIndex[3] = "1";
				}

				request.setAttribute("totalPageCancelled", Integer
						.valueOf(Integer.parseInt(String
								.valueOf(myCancelPageIndex[2]))));
				request.setAttribute("pageNoCancelled", Integer.valueOf(Integer
						.parseInt(String.valueOf(myCancelPageIndex[3]))));
				if ("1".equals(myCancelPageIndex[4])) {
					brdBean.setMyCancelProjectPage("1");
				}
				List myCloseList = new ArrayList();
				brdBean.setMyCancelListLength(true);

				for (int i = Integer.parseInt(myCancelPageIndex[0]); i < Integer
						.parseInt(myCancelPageIndex[1]); i++) {
					BusinessRequirementDocument bean = new BusinessRequirementDocument();
					bean
							.setBrdNumber(checkNull(String
									.valueOf(stakeData[i][0])));
					bean.setProjectName(checkNull(String
							.valueOf(stakeData[i][1])));
					bean.setExpectedEndDate(checkNull(String
							.valueOf(stakeData[i][2])));
					bean.setBrdCode(checkNull(String.valueOf(stakeData[i][3])));
					myCloseList.add(bean);
				}
				brdBean.setMyCancelList(myCloseList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: editApplication()
	 * @purpose : Used to edit Application
	 * @param brdBean
	 * @param classId
	 */
	public void editApplication(BusinessRequirementDocument brdBean,
			String classId) {
		getcurrentUserAndSysDate(brdBean);
		try {
			String editQuery = " SELECT BRD_TICKET_NO,PROJECT_NAME,TO_CHAR(PROJ_START_DATE,'DD-MM-YYYY'),"
					+ " TO_CHAR(PROJ_END_DATE,'DD-MM-YYYY'), BISUNESS_OBJ, "
					+ " PROJECT_CLOSER, PROJ_CONSTRAINTS, PROJ_ASSUMPTIONS, PROJ_DOC_TYPE, "
					+ " PROJ_UPLOAD_DOC,PROJ_FORWARD_TO,PROJ_FORWARD_EMPID,OFFC.EMP_TOKEN,"
					+ " (OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||'  '||OFFC.EMP_LNAME) AS NAME,"
					+ " PROJ_ALLOCATED_BUDGET,NVL(PROJ_APPLICANT_COMMENTS,''), PROJ_PRIORITY, "
					+ " PROJ_TYPE_ID, PROJ_CLASSIFICATION_ID, NVL(PROJ_ANNUAL_FINANCIAL_BENIFIT,''),"
					+ " NVL(HRMS_D1_BUSSINESS_UNIT.BUSS_UNIT_NAME,''), BUSINESS_UNIT_ID,RANK "
					+ " FROM HRMS_D1_BUSINESS_REQUIREMENT  "
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_EMPID)"
					+ " LEFT JOIN HRMS_D1_BUSSINESS_UNIT ON(HRMS_D1_BUSSINESS_UNIT.BUSS_UNIT_ID = HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_UNIT_ID)"
					+ " WHERE BUSINESS_CODE="
					+ classId
					+ " AND PROJ_STATUS IN ('D','B') ";
			Object[][] data = getSqlModel().getSingleResult(editQuery);
			if (((data != null ? 1 : 0) & (data.length > 0 ? 1 : 0)) != 0) {
				brdBean.setBrdNumber(checkNull(String.valueOf(data[0][0])));
				brdBean.setProjectName(checkNull(String.valueOf(data[0][1])));
				brdBean.setStartDate(checkNull(String.valueOf(data[0][2])));
				brdBean
						.setExpectedEndDate(checkNull(String
								.valueOf(data[0][3])));
				brdBean.setBusinessObjective(checkNull(String
						.valueOf(data[0][4])));
				brdBean.setProjectClosureCriteria(checkNull(String
						.valueOf(data[0][5])));
				brdBean.setConstraints(checkNull(String.valueOf(data[0][6])));
				brdBean.setAssuptions(checkNull(String.valueOf(data[0][7])));
				brdBean.setDocType(checkNull(String.valueOf(data[0][8])));
				brdBean.setAttachFile(checkNull(String.valueOf(data[0][9])));
				brdBean.setUploadFileNameItr(checkNull(String
						.valueOf(data[0][9])));
				brdBean.setSelectRole(checkNull(String.valueOf(data[0][10])));
				brdBean.setFwdempCode(checkNull(String.valueOf(data[0][11])));
				brdBean.setForwardEmpToken(checkNull(String
						.valueOf(data[0][12])));
				brdBean
						.setForwardEmpName(checkNull(String
								.valueOf(data[0][13])));
				brdBean.setAllocatedBudget(checkNull(String
						.valueOf(data[0][14])));
				brdBean.setApplicantsComments(checkNull(String
						.valueOf(data[0][15])));
				brdBean.setPriority(checkNull(String.valueOf(data[0][16])));
				brdBean.setProjectType(checkNull(String.valueOf(data[0][17])));
				brdBean.setProjectClassification(checkNull(String
						.valueOf(data[0][18])));
				brdBean.setProjectAnnualFinancialBenefit(checkNull(String
						.valueOf(data[0][19])));
				brdBean.setBusinessUnit(checkNull(String.valueOf(data[0][20])));
				brdBean
						.setBusinessUnitID(checkNull(String
								.valueOf(data[0][21])));
				brdBean.setRank(checkNull(String.valueOf(data[0][22])));

				setDocumentType(brdBean);
				setEmployeeRole(brdBean);
				savedStakeholder(brdBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: showUploadedDocuments()
	 * @purpose : Used to display Upload Documents Name
	 * @param brdBean
	 * @param applicationID
	 */
	public void showUploadedDocuments(BusinessRequirementDocument brdBean,
			String applicationID) {
		try {
			String query = "SELECT HRMS_D1_BUSINESS_REQUIREMENT.PROJ_UPLOAD_DOC "
					+ " FROM HRMS_D1_BUSINESS_REQUIREMENT"
					+ " WHERE HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE = "
					+ applicationID;
			Object[][] uploadedDocObj = getSqlModel().getSingleResult(query);
			if (((uploadedDocObj != null ? 1 : 0) & (uploadedDocObj.length > 0 ? 1
					: 0)) != 0) {
				String[] seperateDocuments = String.valueOf(
						uploadedDocObj[0][0]).split(",");
				List docList = new ArrayList();
				BusinessRequirementDocument innerBean = null;
				for (int i = 0; i < seperateDocuments.length; i++) {
					innerBean = new BusinessRequirementDocument();
					innerBean.setUploadFileNameItr(checkNull(String
							.valueOf(seperateDocuments[i])));
					docList.add(innerBean);
				}
				brdBean.setUploadDocumentIterator(docList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: setUploadedDocuments
	 * @purpose :Used to set Upload Documents Name
	 * @param brdBean
	 * @param request
	 */
	public void setUploadedDocuments(BusinessRequirementDocument brdBean,
			HttpServletRequest request) {
		try {
			String[] uploadedDocs = request
					.getParameterValues("uploadFileNameItr");
			if (uploadedDocs != null && uploadedDocs.length > 0) {
				List docList = new ArrayList();

				for (int i = 0; i < uploadedDocs.length; i++) {
					BusinessRequirementDocument innerBean = new BusinessRequirementDocument();
					innerBean.setUploadFileNameItr(String
							.valueOf(uploadedDocs[i]));
					docList.add(innerBean);
				}
				brdBean.setUploadDocumentIterator(docList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: delete()
	 * @purpose : Used to delete Record from HRMS_D1_BUSINESS_REQUIREMENT
	 * @param brdBean
	 * @return
	 */
	public boolean delete(BusinessRequirementDocument brdBean) {
		boolean result = false;
		try {
			String deleteId = brdBean.getBrdCode();
			String delQuery = "DELETE FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE BUSINESS_CODE="
					+ deleteId;
			result = getSqlModel().singleExecute(delQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**Method Name: forward()
	 * @purpose :Used to forward record 
	 * @param brdBean
	 * @param empId
	 * @param uploadedFileName
	 * @return
	 */
	public boolean forward(BusinessRequirementDocument brdBean, String empId,
			String uploadedFileName) {
		boolean result = false;
		try {
			if ("".equals(brdBean.getBrdCode()))
				result = addInfo(brdBean, empId, uploadedFileName);
			else
				result = modifyInfo(brdBean, empId, uploadedFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**Method Name: displayIteratorValueForKeepInformed()
	 * @purpose : Display Keep Informed to' List
	 * @param srNo
	 * @param empCode
	 * @param empName
	 * @param brdBean
	 */
	public void displayIteratorValueForKeepInformed(String[] srNo,
			String[] empCode, String[] empName,
			BusinessRequirementDocument brdBean) {
		try {
			List stakeholdersList = new ArrayList();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					BusinessRequirementDocument bean = new BusinessRequirementDocument();
					bean.setEmpid(empCode[i]);
					bean.setStakeholderEmpNameItt(empName[i]);
					bean.setSerialNo(srNo[i]);
					stakeholdersList.add(bean);
				}
				brdBean.setStakeHoldersList(stakeholdersList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: setKeepInformed()
	 * @purpose : Set values of Keep Informed to
	 * @param srNo
	 * @param empCode
	 * @param empName
	 * @param brdBean
	 */
	public void setKeepInformed(String[] srNo, String[] empCode,
			String[] empName, BusinessRequirementDocument brdBean) {
		try {
			BusinessRequirementDocument bean = new BusinessRequirementDocument();
			bean.setEmpid(brdBean.getEmployeeId());
			bean.setStakeholderEmpNameItt(brdBean.getEmployeeName());
			List list = displayNewValueForKeepInformed(srNo, empCode, empName,
					brdBean);
			bean.setSerialNo(String.valueOf(list.size() + 1));
			list.add(bean);
			brdBean.setStakeHoldersList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: displayNewValueForKeepInformed()
	 * @purpose : To display new record in Iterator at last
	 * @param srNo
	 * @param empCode
	 * @param empName
	 * @param brdBean
	 * @return ArrayList
	 */
	public List<BusinessRequirementDocument> displayNewValueForKeepInformed(
			String[] srNo, String[] empCode, String[] empName,
			BusinessRequirementDocument brdBean) {
		List list = null;
		try {
			list = new ArrayList();
			if (srNo != null)
				for (int i = 0; i < srNo.length; i++) {
					BusinessRequirementDocument bean1 = new BusinessRequirementDocument();
					bean1.setEmpid(empCode[i]);
					bean1.setStakeholderEmpNameItt(empName[i]);
					bean1.setSerialNo(srNo[i]);
					list.add(bean1);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**Method Name:  removeKeepInformedData()
	 * @purpose : Removed keep Informed to from List 
	 * @param serialNo
	 * @param empCode
	 * @param empName
	 * @param brdBean
	 */
	public void removeKeepInformedData(String[] serialNo, String[] empCode,
			String[] empName, BusinessRequirementDocument brdBean) {
		try {
			List stakeholdersList = new ArrayList();
			if (serialNo != null) {
				for (int i = 0; i < serialNo.length; i++) {
					BusinessRequirementDocument bean = new BusinessRequirementDocument();
					bean.setSerialNo(String.valueOf(i + 1));
					bean.setEmpid(empCode[i]);
					bean.setStakeholderEmpNameItt(empName[i]);
					stakeholdersList.add(bean);
				}
				stakeholdersList.remove(Integer.parseInt(brdBean
						.getCheckRemove()) - 1);
			}
			brdBean.setStakeHoldersList(stakeholdersList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: updateKeepInfoData
	 * @purpose :Update values of  Keep Informed to into Iterator
	 * @param request
	 * @param bean
	 */
	public void updateKeepInfoData(HttpServletRequest request,
			BusinessRequirementDocument bean) {
		try {
			String str = "";
			String[] stakeHolderEmpId = request.getParameterValues("empid");
			if ((stakeHolderEmpId != null) && (stakeHolderEmpId.length > 0)) {
				for (int i = 0; i < stakeHolderEmpId.length; i++) {
					if (i == 0)
						str = str + stakeHolderEmpId[i];
					else {
						str = str + "," + stakeHolderEmpId[i];
					}
				}
			}
			String query = " UPDATE  HRMS_D1_BUSINESS_REQUIREMENT  SET PROJ_STAKE_HOLDER='"
					+ str + "'" + "WHERE BUSINESS_CODE=" + bean.getBrdCode();
			getSqlModel().singleExecute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: updateKeepInfoDataApprover()
	 * @param request
	 * @param bean
	 */
	public void updateKeepInfoDataApprover(HttpServletRequest request,
			BusinessRequirementDocument bean) {
		try {
			String str = "";
			String[] stakeHolderEmpId = request.getParameterValues("empIttId");
			if ((stakeHolderEmpId != null) && (stakeHolderEmpId.length > 0)) {
				for (int i = 0; i < stakeHolderEmpId.length; i++) {
					if (i == 0)
						str = str + stakeHolderEmpId[i];
					else {
						str = str + "," + stakeHolderEmpId[i];
					}
				}
				String query = " UPDATE  HRMS_D1_BUSINESS_REQUIREMENT  SET PROJ_STAKE_HOLDER='"
						+ str
						+ "'"
						+ "WHERE BUSINESS_CODE="
						+ bean.getBrdCode();
				getSqlModel().singleExecute(query);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: view()
	 * @purpose : To view particular Application details
	 * @param brdBean
	 * @param id
	 */
	public void view(BusinessRequirementDocument brdBean, String id) {
		try {
			String viewQuery = " SELECT BRD_TICKET_NO,PROJECT_NAME,"
					+ " TO_CHAR(PROJ_START_DATE,'DD-MM-YYYY'),TO_CHAR(PROJ_END_DATE,'DD-MM-YYYY'),"
					+ " BISUNESS_OBJ, PROJECT_CLOSER, PROJ_CONSTRAINTS, PROJ_ASSUMPTIONS, PROJ_DOC_TYPE,"
					+ " PROJ_UPLOAD_DOC,PROJ_FORWARD_TO,PROJ_FORWARD_EMPID,OFFC.EMP_TOKEN,"
					+ " (OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||'  '||OFFC.EMP_LNAME) AS NAME,  "
					+ " PROJ_ALLOCATED_BUDGET,(OFFC1.EMP_TOKEN||'-'||OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||'  '||OFFC1.EMP_LNAME) AS PROJECT_COMPLETED_NAME, "
					+ " TO_CHAR(PROJ_COMPLETED_DATE,'DD-MM-YYYY'), NVL(PROJ_APPLICANT_COMMENTS,''), "
					+ " PROJ_PRIORITY,  PROJ_TYPE_ID, PROJ_CLASSIFICATION_ID, PROJ_ANNUAL_FINANCIAL_BENIFIT,"
					+ " HRMS_D1_BUSSINESS_UNIT.BUSS_UNIT_NAME,RANK,PROJ_STATUS,BUSINESS_UNIT_ID"
					+ " FROM HRMS_D1_BUSINESS_REQUIREMENT  "
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC on(OFFC.EMP_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_FORWARD_EMPID)  "
					+ " LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_BY)  "
					+ " LEFT JOIN HRMS_D1_BUSSINESS_UNIT ON(HRMS_D1_BUSSINESS_UNIT.BUSS_UNIT_ID = HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_UNIT_ID)"
					+ " WHERE BUSINESS_CODE = " + id;
			Object[][] viewObj = getSqlModel().getSingleResult(viewQuery);
			brdBean.setBrdNumber(checkNull(String.valueOf(viewObj[0][0])));
			brdBean.setProjectName(checkNull(String.valueOf(viewObj[0][1])));
			brdBean.setStartDate(checkNull(String.valueOf(viewObj[0][2])));
			brdBean
					.setExpectedEndDate(checkNull(String.valueOf(viewObj[0][3])));
			brdBean.setBusinessObjective(checkNull(String
					.valueOf(viewObj[0][4])));
			brdBean.setProjectClosureCriteria(checkNull(String
					.valueOf(viewObj[0][5])));
			brdBean.setConstraints(checkNull(String.valueOf(viewObj[0][6])));
			brdBean.setAssuptions(checkNull(String.valueOf(viewObj[0][7])));
			brdBean.setDocType(checkNull(String.valueOf(viewObj[0][8])));
			brdBean.setAttachFile(checkNull(String.valueOf(viewObj[0][9])));
			brdBean.setSelectRole(checkNull(String.valueOf(viewObj[0][10])));
			brdBean.setFwdempCode(checkNull(String.valueOf(viewObj[0][11])));
			brdBean
					.setForwardEmpToken(checkNull(String
							.valueOf(viewObj[0][12])));
			brdBean
					.setForwardEmpName(checkNull(String.valueOf(viewObj[0][13])));
			brdBean
					.setAllocatedBudget(checkNull(String
							.valueOf(viewObj[0][14])));
			brdBean.setCompletedBy(checkNull(String.valueOf(viewObj[0][15])));
			brdBean.setCompletedDate(checkNull(String.valueOf(viewObj[0][16])));
			brdBean.setApplicantsComments(checkNull(String
					.valueOf(viewObj[0][17])));
			brdBean.setPriority(checkNull(String.valueOf(viewObj[0][18])));
			brdBean.setProjectType(checkNull(String.valueOf(viewObj[0][19])));
			brdBean.setProjectClassification(checkNull(String
					.valueOf(viewObj[0][20])));
			brdBean.setProjectAnnualFinancialBenefit(checkNull(String
					.valueOf(viewObj[0][21])));
			brdBean.setBusinessUnit(checkNull(String.valueOf(viewObj[0][22])));
			brdBean.setRank(checkNull(String.valueOf(viewObj[0][23])));
			brdBean.setStatus(checkNull(String.valueOf(viewObj[0][24])));
			brdBean
					.setBusinessUnitID(checkNull(String.valueOf(viewObj[0][25])));
			setDocumentType(brdBean);
			setEmployeeRole(brdBean);
			setProjectTypeDropDown(brdBean);
			setProjectClassificationDropDown(brdBean);
			setBusinessUnitDrop(brdBean);
			savedStakeholder(brdBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: setDetails()
	 * @purpose : Show Approver Comments into table
	 * @param brdBean
	 * @param id
	 */
	public void setDetails(BusinessRequirementDocument brdBean, String id) {
		try {
			Object[][] viewObj = (Object[][]) null;
			String query = " SELECT (HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME), "
					+ " TO_CHAR(HRMS_D1_BUSINESS_PATH.BUSINESS_APPR_DATE,'dd-mm-yyyy'),  "
					+ " HRMS_D1_STAGE.STAGE_NAME, HRMS_D1_BUSINESS_PATH.BUSINESS_COMMENTS, "
					+ " HRMS_D1_BUSINESS_PATH.BUSINESS_CODE, HRMS_D1_BUSINESS_PATH.BUSINESS_PROJ_CLOSE_BY,  "
					+ " HRMS_D1_BUSINESS_PATH.BUSINESS_UPLOAD_FILE , "
					+ " NVL(DECODE(BUSINESS_ACTIVITY_STATUS,'S','Started', 'N','Not Started', 'H','OnHold / Hold'),''), "
					+ " TO_CHAR(BUSINESS_FORCASTED_DATE, 'DD-MM-YYYY') "
					+ " FROM HRMS_D1_BUSINESS_PATH  "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_BUSINESS_PATH.BUSINESS_PROJ_CLOSE_BY)"
					+ " LEFT JOIN HRMS_D1_STAGE ON (HRMS_D1_STAGE.STAGE_CODE = HRMS_D1_BUSINESS_PATH.BUSINESS_STATE) "
					+ " WHERE BUSINESS_CODE = "
					+ id
					+ " ORDER BY BUSINESS_PATH_ID DESC ";
			viewObj = getSqlModel().getSingleResult(query);
			ArrayList list = new ArrayList();
			if ((viewObj != null) && (viewObj.length > 0)) {
				brdBean.setActivityList(true);
				for (int i = 0; i < viewObj.length; i++) {
					BusinessRequirementDocument bean = new BusinessRequirementDocument();
					bean.setConcernedPerson(checkNull(String
							.valueOf(viewObj[i][0])));
					bean.setApplDate(checkNull(String.valueOf(viewObj[i][1])));
					bean.setLogState(checkNull(String.valueOf(viewObj[i][2])));
					bean
							.setEmpComments(checkNull(String
									.valueOf(viewObj[i][3])));
					bean.setViewCode(checkNull(String.valueOf(viewObj[i][4])));
					bean.setAppCode(checkNull(String.valueOf(viewObj[i][5])));
					String[] forwardedApproverUploadDoc = checkNull(
							String.valueOf(viewObj[i][6])).split(",");
					if ((forwardedApproverUploadDoc != null)
							&& (forwardedApproverUploadDoc.length > 0)) {
						ArrayList uploadDocList = new ArrayList();
						for (int j = 0; j < forwardedApproverUploadDoc.length; j++) {
							BusinessRequirementDocument innerBean = new BusinessRequirementDocument();
							innerBean
									.setForwardedApprActivityLogUploadDocItr(forwardedApproverUploadDoc[j]);
							uploadDocList.add(innerBean);
						}
						bean
								.setForwardedApproverUploadDocActivityLogIterator(uploadDocList);
					}
					bean.setCurrentActivityStatusItr(checkNull(String
							.valueOf(viewObj[i][7])));
					bean.setForeCastedDateItr(checkNull(String
							.valueOf(viewObj[i][8])));
					list.add(bean);
				}
			}
			brdBean.setLogList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: viewData()
	 * @purpose : To set detail of Application
	 * @param brdBean
	 * @param code
	 */
	public void viewData(BusinessRequirementDocument brdBean, String code) {
		try {
			String viewQuery = " SELECT BRD_TICKET_NO, PROJECT_NAME, TO_CHAR(PROJ_START_DATE,'DD-MM-YYYY'),"
					+ " TO_CHAR(PROJ_END_DATE,'DD-MM-YYYY'),  PROJ_ALLOCATED_BUDGET,BISUNESS_OBJ,"
					+ " PROJECT_CLOSER, PROJ_CONSTRAINTS, PROJ_ASSUMPTIONS, BUSINESS_CODE, "
					+ " NVL(PROJ_APPLICANT_COMMENTS,''), PROJ_PRIORITY,  PROJ_TYPE_ID, PROJ_CLASSIFICATION_ID,"
					+ " PROJ_ANNUAL_FINANCIAL_BENIFIT, BUSINESS_UNIT_ID, HRMS_D1_BUSSINESS_UNIT.BUSS_UNIT_NAME,RANK, PROJ_STATUS "
					+ " FROM HRMS_D1_BUSINESS_REQUIREMENT "
					+ " LEFT JOIN HRMS_D1_BUSSINESS_UNIT ON(HRMS_D1_BUSSINESS_UNIT.BUSS_UNIT_ID = HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_UNIT_ID)"
					+ " WHERE BUSINESS_CODE =" + code;

			Object[][] data = getSqlModel().getSingleResult(viewQuery);
			if (((data != null ? 1 : 0) & (data.length > 0 ? 1 : 0)) != 0) {
				brdBean.setBrdNumber(checkNull(String.valueOf(data[0][0])));
				brdBean.setProjectName(checkNull(String.valueOf(data[0][1])));
				brdBean.setStartDate(checkNull(String.valueOf(data[0][2])));
				brdBean
						.setExpectedEndDate(checkNull(String
								.valueOf(data[0][3])));
				brdBean
						.setAllocatedBudget(checkNull(String
								.valueOf(data[0][4])));
				brdBean.setBusinessObjective(checkNull(String
						.valueOf(data[0][5])));
				brdBean.setProjectClosureCriteria(checkNull(String
						.valueOf(data[0][6])));
				brdBean.setConstraints(checkNull(String.valueOf(data[0][7])));
				brdBean.setAssuptions(checkNull(String.valueOf(data[0][8])));
				brdBean.setBrdCode(checkNull(String.valueOf(data[0][9])));
				brdBean.setApplicantsComments(checkNull(String
						.valueOf(data[0][10])));
				brdBean.setPriority(checkNull(String.valueOf(data[0][11])));
				brdBean.setProjectType(checkNull(String.valueOf(data[0][12])));
				brdBean.setProjectClassification(checkNull(String
						.valueOf(data[0][13])));
				brdBean.setProjectAnnualFinancialBenefit(checkNull(String
						.valueOf(data[0][14])));
				brdBean
						.setBusinessUnitID(checkNull(String
								.valueOf(data[0][15])));
				brdBean.setBusinessUnit(checkNull(String.valueOf(data[0][16])));
				brdBean.setRank(checkNull(String.valueOf(data[0][17])));
				brdBean.setStatus(checkNull(String.valueOf(data[0][18])));
				setDocumentType(brdBean);
				setEmployeeRole(brdBean);
				setState(brdBean);
				setProjectTypeDropDown(brdBean);
				setProjectClassificationDropDown(brdBean);
				setBusinessUnitDrop(brdBean);
				savedStakeholder(brdBean);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/**Method Name: updateCloseAppData()
	 * @purpose : Update Employee ID who Close that record
	 * @param brdBean
	 * @param applicationId
	 * @param userId
	 */
	public void updateCloseAppData(BusinessRequirementDocument brdBean,
			String applicationId, String userId) {
		try {
			String closeStatusUpdateQuery = "UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET PROJ_FORWARD_EMPID ="
					+ userId + " WHERE BUSINESS_CODE =" + applicationId;
			getSqlModel().singleExecute(closeStatusUpdateQuery);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/**Method Name: closed()
	 * @purpose : Changed status of Closed Application
	 * @param brdBean
	 * @param applicationId
	 * @param applStatus
	 * @param userID
	 * @param uploadedFileName
	 * @return String 
	 */
	public String closed(BusinessRequirementDocument brdBean,
			String applicationId, String applStatus, String userID,
			String uploadedFileName) {
		String message = "";
		String comments = brdBean.getComments().trim();
		String currentState = brdBean.getCurrentState().trim();
		try {
			String changeStatusQuery = " UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET PROJ_STATUS = '"
					+ applStatus
					+ "'"
					+ " WHERE BUSINESS_CODE = "
					+ applicationId;
			getSqlModel().singleExecute(changeStatusQuery);
			insertApproverData(applicationId, comments, userID, currentState,
					uploadedFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**Method Name: insertApproverData()
	 * @purpose : Insert Approver's detail into HRMS_D1_BUSINESS_PATH
	 * @param applicationId
	 * @param approverComments
	 * @param userID
	 * @param stage
	 * @param uploadedFileName
	 */
	public void insertApproverData(String applicationId,
			String approverComments, String userID, String stage,
			String uploadedFileName) {
		try {
			String insertSql = " INSERT INTO HRMS_D1_BUSINESS_PATH "
					+ " (BUSINESS_CODE,BUSINESS_COMMENTS, BUSINESS_APPR_DATE, "
					+ " BUSINESS_PATH_ID, BUSINESS_PROJ_CLOSE_BY, BUSINESS_PROJ_CLOSE_BY_DATE,"
					+ " BUSINESS_STATE,BUSINESS_STATUS, BUSINESS_UPLOAD_FILE) "
					+ " VALUES (?,?,SYSDATE,(SELECT NVL(MAX(BUSINESS_PATH_ID), 0) + 1 FROM HRMS_D1_BUSINESS_PATH),"
					+ " ?, SYSDATE,?,'C',?) ";

			Object[][] insertObj = new Object[1][5];
			insertObj[0][0] = applicationId;
			insertObj[0][1] = approverComments;
			insertObj[0][2] = userID;
			insertObj[0][3] = stage;
			insertObj[0][4] = uploadedFileName;
			getSqlModel().singleExecute(insertSql, insertObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: sendBack()
	 * @purpose :To send Back Application
	 * @param applicationId
	 * @param status
	 * @param brdBean
	 * @param empId
	 * @param completedCode
	 * @return String 
	 */
	public String sendBack(String applicationId, String status,
			BusinessRequirementDocument brdBean, String empId,
			String completedCode) {
		String message = "";
		try {
			String updateQueryHdr = " UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET PROJ_STATUS ='B' ,PROJ_INITIATOR="
					+ completedCode
					+ "  WHERE BUSINESS_CODE = "
					+ applicationId;
			getSqlModel().singleExecute(updateQueryHdr);
			String insertSql = " INSERT INTO HRMS_D1_BUSINESS_PATH "
					+ " (BUSINESS_CODE, BUSINESS_COMMENTS, BUSINESS_APPR_DATE, "
					+ " BUSINESS_PATH_ID, BUSINESS_STATUS,BUSINESS_PROJ_CLOSE_BY, "
					+ " BUSINESS_PROJ_CLOSE_BY_DATE) "
					+ " VALUES (?,?,SYSDATE,(SELECT NVL(MAX(BUSINESS_PATH_ID), 0) + 1"
					+ " FROM HRMS_D1_BUSINESS_PATH), ?, ?,SYSDATE) ";

			Object[][] insertObj = new Object[1][4];
			insertObj[0][0] = applicationId;
			insertObj[0][1] = brdBean.getComments().trim();
			insertObj[0][2] = "B";
			insertObj[0][3] = empId;
			getSqlModel().singleExecute(insertSql, insertObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**Method Name:saveInfo() 
	 * @purpose : Insert Approver's detail into HRMS_D1_BUSINESS_PATH
	 * @param brdAppBean
	 * @param empId
	 * @param applicationId
	 * @param uploadedFileName
	 * @return bbolean
	 */
	public boolean saveInfo(BusinessRequirementDocument brdAppBean,
			String empId, String applicationId, String uploadedFileName) {
		boolean result = false;
		try {
			String insertSql = " INSERT INTO HRMS_D1_BUSINESS_PATH "
					+ " (BUSINESS_CODE,BUSINESS_APPROVER_CODE, BUSINESS_COMMENTS, "
					+ " BUSINESS_APPR_DATE, BUSINESS_PATH_ID, BUSINESS_STATUS, "
					+ " BUSINESS_APPROVER_TYPE, BUSINESS_DOC_TYPE,BUSINESS_UPLOAD_FILE,"
					+ " BUSINESS_STATE,BUSINESS_PROJ_CLOSE_BY, BUSINESS_PROJ_CLOSE_BY_DATE)"
					+ " VALUES (?,?,?,SYSDATE,(SELECT NVL(MAX(BUSINESS_PATH_ID), 0) + 1 FROM HRMS_D1_BUSINESS_PATH),"
					+ " ?, ?, ?, ?, ?,?,SYSDATE) ";

			Object[][] insertObj = new Object[1][9];
			insertObj[0][0] = applicationId;
			insertObj[0][1] = brdAppBean.getFwdempCode().trim();
			insertObj[0][2] = brdAppBean.getComments().trim();
			insertObj[0][3] = "F";
			insertObj[0][4] = brdAppBean.getEmpRole().trim();
			insertObj[0][5] = brdAppBean.getDocumentAttached();

			insertObj[0][6] = uploadedFileName.trim();
			insertObj[0][7] = brdAppBean.getCurrentState();
			insertObj[0][8] = empId;
			result = getSqlModel().singleExecute(insertSql, insertObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**Method Name: updateStatus()
	 * @purpose :Update values of editable Fields after clicked on Update Activity Status 
	 * @param brdAppBean
	 * @param empId
	 * @param appId
	 * @param stakeHolderEmp
	 */
	public void updateStatus(BusinessRequirementDocument brdAppBean,
			String empId, String appId, String stakeHolderEmp) {
		try {

			String updateQuery = "  UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET PROJ_FORWARD_TO="
					+ brdAppBean.getEmpRole()
					+ " ,PROJ_INITIATOR = "
					+ empId
					+ " , PROJ_CURENT_STAGE ="
					+ brdAppBean.getCurrentState()
					+ " , PROJ_FORWARD_EMPID =" + empId;
			/**only BRD Super User can edit values of following fields*/
			if (brdAppBean.getProjectClassification() != null
					&& !brdAppBean.getProjectClassification().trim().equals(""))
				updateQuery += " , PROJ_CLASSIFICATION_ID ="
						+ brdAppBean.getProjectClassification();

			if (brdAppBean.getPriority() != null
					&& !brdAppBean.getPriority().trim().equals(""))
				updateQuery += " , PROJ_PRIORITY = '"
						+ brdAppBean.getPriority() + "'";

			if (brdAppBean.getProjectType() != null
					&& !brdAppBean.getProjectType().trim().equals(""))
				updateQuery += " , PROJ_TYPE_ID ="
						+ brdAppBean.getProjectType();

			if (brdAppBean.getBusinessUnitID() != null
					&& !brdAppBean.getBusinessUnitID().trim().equals(""))
				updateQuery += " , BUSINESS_UNIT_ID ="
						+ brdAppBean.getBusinessUnitID();

			if (brdAppBean.getRank() != null
					&& !brdAppBean.getRank().trim().equals(""))
				updateQuery += " , RANK =" + brdAppBean.getRank();
			if (stakeHolderEmp != null) {
				updateQuery += " , PROJ_STAKE_HOLDER='" + stakeHolderEmp + "'";
			}
			updateQuery += " WHERE HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE = "
					+ appId;
			getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: mysendbackProj()
	 * @purpose : Set Send back detail(Cooments,Project Name..)
	 * @param brdBean
	 * @param request
	 * @param userId
	 */
	public void mysendbackProj(BusinessRequirementDocument brdBean,
			HttpServletRequest request, String userId) {
		try {
			Object[][] queryData = (Object[][]) null;
			String sendbackQuery = "SELECT DISTINCT BRD_TICKET_NO, PROJECT_NAME, TO_CHAR(PROJ_END_DATE,'DD-MM-YYYY'),"
					+ " BUSINESS_CODE,PROJ_STATUS FROM  HRMS_D1_BUSINESS_REQUIREMENT "
					+ " WHERE PROJ_STATUS ='B' AND PROJ_FORWARD_EMPID = "
					+ userId;
			queryData = getSqlModel().getSingleResult(sendbackQuery);
			if ((queryData != null) && (queryData.length > 0)) {
				brdBean.setModeLength("true");
				brdBean.setTotalNoOfRecords(String.valueOf(queryData.length));
				String[] mysendbackPageIndex = Utility.doPaging(brdBean
						.getMySendBackProjectPage(), queryData.length, 20);
				if (mysendbackPageIndex == null) {
					mysendbackPageIndex[0] = "0";
					mysendbackPageIndex[1] = "20";
					mysendbackPageIndex[2] = "1";
					mysendbackPageIndex[3] = "1";
				}
				request.setAttribute("totalPageSendBack", Integer
						.valueOf(Integer.parseInt(String
								.valueOf(mysendbackPageIndex[2]))));
				request.setAttribute("pageNoSendBack", Integer.valueOf(Integer
						.parseInt(String.valueOf(mysendbackPageIndex[3]))));
				if ("1".equals(mysendbackPageIndex[4])) {
					brdBean.setMySendBackProjectPage("1");
				}
				List mySendbackList = new ArrayList();
				brdBean.setFormySendbackListLength(true);

				int i = Integer.parseInt(mysendbackPageIndex[0]);
				for (; i < Integer.parseInt(mysendbackPageIndex[1]); i++) {
					BusinessRequirementDocument bean = new BusinessRequirementDocument();
					bean
							.setBrdNumber(checkNull(String
									.valueOf(queryData[i][0])));
					bean.setProjectName(checkNull(String
							.valueOf(queryData[i][1])));
					bean.setExpectedEndDate(checkNull(String
							.valueOf(queryData[i][2])));
					bean.setBrdCode(checkNull(String.valueOf(queryData[i][3])));
					bean.setStatus(checkNull(String.valueOf(queryData[i][4])));
					mySendbackList.add(bean);
				}
				brdBean.setForMySendbackList(mySendbackList);
			} else {
				brdBean.setForMySendbackList(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: insertSendBackData()
	 * @purpose : Insert values into HRMS_D1_BUSINESS_PATH when employee send back record
	 * @param brdBean
	 * @param applicationId
	 * @param userId
	 */
	public void insertSendBackData(BusinessRequirementDocument brdBean,
			String applicationId, String userId) {
		try {
			String insertSql = " INSERT INTO HRMS_D1_BUSINESS_PATH "
					+ "(BUSINESS_CODE, BUSINESS_APPROVER_CODE, BUSINESS_COMMENTS,"
					+ " BUSINESS_APPR_DATE, BUSINESS_PATH_ID, BUSINESS_STATUS, BUSINESS_APPROVER_TYPE,"
					+ " BUSINESS_DOC_TYPE, BUSINESS_UPLOAD_FILE, BUSINESS_PROJ_CLOSE_BY, "
					+ " BUSINESS_PROJ_CLOSE_BY_DATE, BUSINESS_STATE) "
					+ " VALUES (?,?,?,SYSDATE,(SELECT NVL(MAX(BUSINESS_PATH_ID), 0) + 1 FROM HRMS_D1_BUSINESS_PATH),"
					+ " ?,?,?,?,?,SYSDATE,?) ";

			Object[][] insertObj = new Object[1][9];
			insertObj[0][0] = applicationId;
			insertObj[0][1] = brdBean.getFwdempCode().trim();
			insertObj[0][2] = brdBean.getComments().trim();
			insertObj[0][3] = "B";
			insertObj[0][4] = brdBean.getEmpRole().trim();
			insertObj[0][5] = brdBean.getDocumentAttached().trim();
			insertObj[0][6] = brdBean.getAttachFile().trim();
			insertObj[0][7] = userId;
			insertObj[0][8] = brdBean.getCurrentState().trim();
			getSqlModel().singleExecute(insertSql, insertObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: editSendBackApplication() 
	 * @purpose : set values
	 * @param brdBean
	 * @param classId
	 */
	public void editSendBackApplication(BusinessRequirementDocument brdBean,
			String classId) {
		try {
			String sendAppEditQuery = "SELECT BUSINESS_APPROVER_TYPE,BUSINESS_APPROVER_CODE,"
					+ " OFFC.EMP_TOKEN,(OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||'  '||OFFC.EMP_LNAME), "
					+ " BUSINESS_COMMENTS, BUSINESS_STATE ,BUSINESS_DOC_TYPE, BUSINESS_UPLOAD_FILE"
					+ " FROM HRMS_D1_BUSINESS_PATH  "
					+ " INNER JOIN HRMS_EMP_OFFC OFFC on(OFFC.EMP_ID = HRMS_D1_BUSINESS_PATH.BUSINESS_APPROVER_CODE) "
					+ " WHERE BUSINESS_CODE="
					+ classId
					+ " AND BUSINESS_STATUS ='B' ";
			Object[][] data = getSqlModel().getSingleResult(sendAppEditQuery);
			if (((data != null ? 1 : 0) & (data.length > 0 ? 1 : 0)) != 0) {
				brdBean.setEmpRole(checkNull(String.valueOf(data[0][0])));
				brdBean.setFwdempCode(checkNull(String.valueOf(data[0][1])));
				brdBean
						.setForwardEmpToken(checkNull(String
								.valueOf(data[0][2])));
				brdBean
						.setForwardEmpName(checkNull(String.valueOf(data[0][3])));
				brdBean.setComments(checkNull(String.valueOf(data[0][4])));
				brdBean.setCurrentState(checkNull(String.valueOf(data[0][5])));
				brdBean.setDocumentAttached(checkNull(String
						.valueOf(data[0][6])));
				brdBean.setAttachFile(checkNull(String.valueOf(data[0][7])));
				setDocumentType(brdBean);
				setEmployeeRole(brdBean);
				setState(brdBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name:updateReqStatus() 
	 * @purpose : Update Current Activity Owner, Current State of Project
	 * @param brdBean
	 * @param applicationId
	 * @param fwdCode
	 */
	public void updateReqStatus(BusinessRequirementDocument brdBean,
			String applicationId, String fwdCode) {
		try {
			String updateQuery = " UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET PROJ_STATUS ='F' ,PROJ_FORWARD_EMPID= "
					+ fwdCode
					+ " ,PROJ_INITIATOR ="
					+ fwdCode
					+ " WHERE BUSINESS_CODE = " + applicationId;
			getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: updatePath()
	 * @purpose : Update records
	 * @param brdBean
	 * @param applicationId
	 * @param uploadedFileName
	 */
	public void updatePath(BusinessRequirementDocument brdBean,
			String applicationId, String uploadedFileName) {
		try {
			Object[][] updatePathObj = new Object[1][8];
			updatePathObj[0][0] = brdBean.getEmpRole().trim();
			updatePathObj[0][1] = brdBean.getFwdempCode().trim();
			updatePathObj[0][2] = brdBean.getComments().trim();
			updatePathObj[0][3] = brdBean.getCurrentState().trim();
			updatePathObj[0][4] = brdBean.getDocumentAttached().trim();
			updatePathObj[0][5] = uploadedFileName.trim();
			updatePathObj[0][6] = brdBean.getStatus().trim();
			updatePathObj[0][7] = applicationId;
			getSqlModel().singleExecute(getQuery(5), updatePathObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: initiatorData()
	 * @purpose : Set Initiator List 
	 * @param brdBean
	 * @param classId
	 */
	public void initiatorData(BusinessRequirementDocument brdBean,
			String classId) {
		try {
			Object[][] viewObj = (Object[][]) null;
			String query = " SELECT (HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME),"
					+ " TO_CHAR(PROJ_COMPLETED_DATE,'dd-mm-yyyy'), BUSINESS_CODE, PROJ_COMPLETED_BY, PROJ_UPLOAD_DOC"
					+ " FROM HRMS_D1_BUSINESS_REQUIREMENT  "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_BUSINESS_REQUIREMENT.PROJ_COMPLETED_BY) "
					+ " WHERE BUSINESS_CODE =" + classId;
			viewObj = getSqlModel().getSingleResult(query);
			ArrayList list = new ArrayList();
			if ((viewObj != null) && (viewObj.length > 0)) {
				BusinessRequirementDocument bean = new BusinessRequirementDocument();
				bean.setInitiatorName(checkNull(String.valueOf(viewObj[0][0])));
				bean.setCompletedDate(checkNull(String.valueOf(viewObj[0][1])));
				bean.setViewCode(checkNull(String.valueOf(viewObj[0][2])));
				bean.setAppCode(checkNull(String.valueOf(viewObj[0][3])));
				bean.setFileName(checkNull(String.valueOf(viewObj[0][4])));

				String[] initiatorUploadDoc = checkNull(
						String.valueOf(viewObj[0][4])).split(",");
				if ((initiatorUploadDoc != null)
						&& (initiatorUploadDoc.length > 0)) {
					ArrayList uploadDocList = new ArrayList();
					for (int i = 0; i < initiatorUploadDoc.length; i++) {
						BusinessRequirementDocument innerBean = new BusinessRequirementDocument();
						innerBean
								.setInitiatorUploadDocNameItr(initiatorUploadDoc[i]);
						uploadDocList.add(innerBean);
					}
					bean.setInitiatorUploadDocIterator(uploadDocList);
				}
				list.add(bean);
			}
			brdBean.setInitiatorList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: displayApproverIteratorValueForStake()
	 * @purpose : Display Stake Holder List 
	 * @param serialNum
	 * @param empIttId
	 * @param stakeholderAppEmpNameItt
	 * @param brdBean
	 */
	public void displayApproverIteratorValueForStake(String[] serialNum,
			String[] empIttId, String[] stakeholderAppEmpNameItt,
			BusinessRequirementDocument brdBean) {
		try {
			List appStakeholdersList = new ArrayList();
			if (serialNum != null) {
				for (int i = 0; i < serialNum.length; i++) {
					BusinessRequirementDocument bean = new BusinessRequirementDocument();
					bean.setEmpIttId(empIttId[i]);
					bean
							.setStakeholderAppEmpNameItt(stakeholderAppEmpNameItt[i]);
					bean.setSerialNum(serialNum[i]);
					appStakeholdersList.add(bean);
				}
				brdBean.setApproverStakeHoldersList(appStakeholdersList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: setStakeHolders()
	 * @purpose : Set by default Stake holder's Name depent on Project Type
	 * @param serialNum
	 * @param empIttId
	 * @param stakeholderAppEmpNameItt
	 * @param brdBean
	 */
	public void setStakeHolders(String[] serialNum, String[] empIttId,
			String[] stakeholderAppEmpNameItt,
			BusinessRequirementDocument brdBean) {
		try {
			BusinessRequirementDocument bean = new BusinessRequirementDocument();
			bean.setEmpIttId(brdBean.getEmpCode());
			bean.setStakeholderAppEmpNameItt(brdBean.getEmpName());
			List list = displayNewApproverValueForKeepInformed(serialNum,
					empIttId, stakeholderAppEmpNameItt, brdBean);
			bean.setSerialNum(String.valueOf(list.size() + 1));
			list.add(bean);
			brdBean.setApproverStakeHoldersList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: displayNewApproverValueForKeepInformed()
	 * @purpose : Display Stake Holder List 
	 * @param serialNum
	 * @param empIttId
	 * @param stakeholderAppEmpNameItt
	 * @param brdBean
	 * @return ArrayList
	 */
	private List<BusinessRequirementDocument> displayNewApproverValueForKeepInformed(
			String[] serialNum, String[] empIttId,
			String[] stakeholderAppEmpNameItt,
			BusinessRequirementDocument brdBean) {
		List list = null;
		try {
			list = new ArrayList();
			if (serialNum != null)
				for (int i = 0; i < serialNum.length; i++) {
					BusinessRequirementDocument bean1 = new BusinessRequirementDocument();
					bean1.setEmpIttId(empIttId[i]);
					bean1
							.setStakeholderAppEmpNameItt(stakeholderAppEmpNameItt[i]);
					bean1.setSerialNum(serialNum[i]);
					list.add(bean1);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**Method Name: removeStakeHolders()
	 * @purpose :Remove Stake Holder from List
	 * @param serialNum
	 * @param empIttId
	 * @param stakeholderAppEmpNameItt
	 * @param brdBean
	 */
	public void removeStakeHolders(String[] serialNum, String[] empIttId,
			String[] stakeholderAppEmpNameItt,
			BusinessRequirementDocument brdBean) {
		try {
			List stakeholdersList = new ArrayList();
			if (serialNum != null) {
				for (int i = 0; i < serialNum.length; i++) {
					BusinessRequirementDocument bean = new BusinessRequirementDocument();
					bean.setSerialNum(String.valueOf(i + 1));
					bean.setEmpIttId(empIttId[i]);
					bean
							.setStakeholderAppEmpNameItt(stakeholderAppEmpNameItt[i]);
					stakeholdersList.add(bean);
				}
				stakeholdersList.remove(Integer.parseInt(brdBean
						.getCheckAppRemove()) - 1);
			}
			brdBean.setApproverStakeHoldersList(stakeholdersList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Method Name: isCurrentUserIsApplicant()
	 * @purpose :Check that Login User and Owner is same 
	 * @param brdBean
	 * @param id
	 * @return
	 */
	public Object[][] isCurrentUserIsApplicant(
			BusinessRequirementDocument brdBean, String id) {
		Object[][] applicantObj = null;
		try {
			String applicantQuery = "SELECT NVL(PROJ_COMPLETED_BY,0), NVL(PROJ_STATUS,''),NVL (PROJ_FORWARD_EMPID,0)"
					+ " FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE BUSINESS_CODE = "
					+ id;
			applicantObj = getSqlModel().getSingleResult(applicantQuery);
			if ((applicantObj != null) && (applicantObj.length > 0)) {
				String applicantID = String.valueOf(applicantObj[0][0]);
				String status = String.valueOf(applicantObj[0][1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return applicantObj;
	}

	/**Method Name: updateApplicantRelatedData()
	 * @purpose : Update Values into HRMS_D1_BUSINESS_REQUIREMENT 
	 * @param brdBean
	 * @param userID
	 * @param uploadedFileName
	 * @return boolean 
	 */
	public boolean updateApplicantRelatedData(
			BusinessRequirementDocument brdBean, String userID,
			String uploadedFileName) {
		boolean result = false;
		try {
			Object[][] updateObj = new Object[1][22];
			updateObj[0][0] = brdBean.getProjectName().trim();
			updateObj[0][1] = brdBean.getStartDate().trim();
			updateObj[0][2] = brdBean.getExpectedEndDate().trim();
			updateObj[0][3] = brdBean.getBusinessObjective().trim();
			updateObj[0][4] = brdBean.getProjectClosureCriteria().trim();
			updateObj[0][5] = brdBean.getConstraints().trim();
			updateObj[0][6] = brdBean.getAssuptions().trim();
			updateObj[0][7] = brdBean.getDocType();

			updateObj[0][8] = uploadedFileName.trim();
			updateObj[0][9] = brdBean.getSelectRole();
			updateObj[0][10] = brdBean.getFwdempCode().trim();
			updateObj[0][11] = "F";
			updateObj[0][12] = brdBean.getAllocatedBudget().trim();
			if (updateObj[0][11].equals("D"))
				updateObj[0][13] = userID;
			else {
				updateObj[0][13] = brdBean.getFwdempCode().trim();
			}
			updateObj[0][14] = brdBean.getApplicantsComments().trim();
			updateObj[0][15] = brdBean.getPriority();
			updateObj[0][16] = brdBean.getProjectType();
			updateObj[0][17] = brdBean.getProjectClassification();
			updateObj[0][18] = brdBean.getProjectAnnualFinancialBenefit();
			updateObj[0][19] = brdBean.getBusinessUnitID().trim();
			updateObj[0][20] = brdBean.getRank().trim();
			updateObj[0][21] = brdBean.getBrdCode().trim();
			result = getSqlModel().singleExecute(getQuery(2), updateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**Method Name: cancelProject()
	 * @purpose : To Cancel Project
	 * @param brdBean
	 * @param userID
	 * @param applicationId
	 * @return boolean
	 */
	public boolean cancelProject(BusinessRequirementDocument brdBean,
			String userID, String applicationId) {
		boolean result = false;
		try {
			boolean updateFormData = getSqlModel()
					.singleExecute(
							"UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET HRMS_D1_BUSINESS_REQUIREMENT.PROJ_STATUS = 'Z' WHERE HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE = "
									+ applicationId);
			if (updateFormData) {
				String insertSql = "INSERT INTO HRMS_D1_BUSINESS_PATH "
						+ " (BUSINESS_CODE, BUSINESS_APPROVER_CODE, "
						+ " BUSINESS_COMMENTS, BUSINESS_APPR_DATE, BUSINESS_PATH_ID, BUSINESS_STATUS, "
						+ " BUSINESS_PROJ_CLOSE_BY, BUSINESS_PROJ_CLOSE_BY_DATE)  "
						+ " VALUES (?, ?, ?, SYSDATE, (SELECT NVL(MAX(BUSINESS_PATH_ID), 0) + 1 FROM HRMS_D1_BUSINESS_PATH), ?,?, SYSDATE) ";

				Object[][] insertObj = new Object[1][5];
				insertObj[0][0] = applicationId;
				insertObj[0][1] = userID;
				insertObj[0][2] = brdBean.getCancelProjectComments();
				insertObj[0][3] = "Z";
				insertObj[0][4] = userID;
				result = getSqlModel().singleExecute(insertSql, insertObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**Method Name: saveCurrentActivityStatus()
	 * @purpose : Saved values into HRMS_D1_BUSINESS_PATH 
	 * @param applicationId
	 * @param currentActivityStatus
	 * @param forecastedCompletionDate
	 * @param comments
	 * @param currentState
	 * @param currentUser
	 * @param documentType
	 * @param attachedDocumentName
	 * @param stakeHolderEmp
	 * @param brdAppBean
	 * @return boolean
	 */
	public boolean saveCurrentActivityStatus(String applicationId,
			String currentActivityStatus, String forecastedCompletionDate,
			String comments, String currentState, String currentUser,
			String documentType, String attachedDocumentName,
			String stakeHolderEmp, BusinessRequirementDocument brdAppBean) {
		boolean result = false;
		try {
			Object[][] saveObj = new Object[1][8];
			saveObj[0][0] = applicationId;
			saveObj[0][1] = comments;
			saveObj[0][2] = currentActivityStatus;
			saveObj[0][3] = forecastedCompletionDate;
			saveObj[0][4] = currentState;
			saveObj[0][5] = currentUser;
			saveObj[0][6] = documentType;
			saveObj[0][7] = attachedDocumentName;
			String insertIntoLogTableQuery = "INSERT INTO HRMS_D1_BUSINESS_PATH "
					+ "(BUSINESS_CODE, BUSINESS_COMMENTS, BUSINESS_APPR_DATE, BUSINESS_PATH_ID, "
					+ " BUSINESS_ACTIVITY_STATUS, BUSINESS_FORCASTED_DATE, BUSINESS_STATE,"
					+ " BUSINESS_PROJ_CLOSE_BY, BUSINESS_DOC_TYPE, BUSINESS_UPLOAD_FILE )  "
					+ " VALUES (?, ?, SYSDATE, (SELECT NVL(MAX(BUSINESS_PATH_ID), 0) + 1 FROM HRMS_D1_BUSINESS_PATH), ?, TO_DATE(?, 'DD-MM-YYYY'), ?, ?, ?, ?)";
			result = getSqlModel().singleExecute(insertIntoLogTableQuery,
					saveObj);
			String updateQuery = " UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CURENT_STAGE = "
					+ currentState;

			if (brdAppBean.getProjectClassification() != null
					&& !brdAppBean.getProjectClassification().trim().equals(""))
				updateQuery += " , PROJ_CLASSIFICATION_ID ="
						+ brdAppBean.getProjectClassification();

			if (brdAppBean.getPriority() != null
					&& !brdAppBean.getPriority().trim().equals(""))
				updateQuery += " , PROJ_PRIORITY = '"
						+ brdAppBean.getPriority() + "'";

			if (brdAppBean.getProjectType() != null
					&& !brdAppBean.getProjectType().trim().equals(""))
				updateQuery += " , PROJ_TYPE_ID ="
						+ brdAppBean.getProjectType();

			if (brdAppBean.getBusinessUnitID() != null
					&& !brdAppBean.getBusinessUnitID().trim().equals(""))
				updateQuery += " , BUSINESS_UNIT_ID ="
						+ brdAppBean.getBusinessUnitID();

			if (brdAppBean.getRank() != null
					&& !brdAppBean.getRank().trim().equals(""))
				updateQuery += " , RANK =" + brdAppBean.getRank();

			if (brdAppBean.getProjectName() != null
					&& !brdAppBean.getProjectName().trim().equals(""))
				updateQuery += " , PROJECT_NAME ='"
						+ brdAppBean.getProjectName() + "'";
			if (stakeHolderEmp != null) {
				updateQuery += " , PROJ_STAKE_HOLDER='" + stakeHolderEmp + "'";
			}
			updateQuery += " WHERE HRMS_D1_BUSINESS_REQUIREMENT.BUSINESS_CODE = "
					+ applicationId;
			getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**Method Name: setEmployee()
	 * @purpose: To set Employee Name according to Business Unit 
	 * @return String 
	 */
	public void setSatkeHolderEmp(BusinessRequirementDocument brdBean) {
		try {
			BusinessRequirementDocumentModel model = new BusinessRequirementDocumentModel();
			model.initiate(context, session);
			String bussinessUnit = brdBean.getBusinessUnitID();
			/**To check is null or blank*/
			if (!(bussinessUnit == null || bussinessUnit.equals("") || bussinessUnit
					.equals("null"))) {
				String query = " SELECT BUSS_EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
						+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME"
						+ " FROM  HRMS_D1_BUSSINESS_UNIT "
						+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_BUSSINESS_UNIT.BUSS_EMP_ID)"
						+ " WHERE BUSS_UNIT_ID= " + bussinessUnit;
				Object[][] empObj = model.getSqlModel().getSingleResult(query);
				if (empObj != null && empObj.length > 0) {
					brdBean.setFwdempCode(String.valueOf(empObj[0][0]));
					brdBean.setForwardEmpToken(String.valueOf(empObj[0][1]));
					brdBean.setForwardEmpName(String.valueOf(empObj[0][2]));
				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name: setConfiguredStakeHolder()
	 * @purpose : Set default Stake Holder
	 * @param brdBean
	 */
	public void setConfiguredStakeHolder(BusinessRequirementDocument brdBean) {
		try {
			String query = "SELECT  HRMS_EMP_OFFC.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||"
					+ " HRMS_EMP_OFFC.EMP_LNAME FROM HRMS_D1_APPROVAL_SETTINGS"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID)"
					+ " WHERE APP_SETTINGS_TYPE='B'";
			Object[][] empStake = getSqlModel().getSingleResult(query);
			List stakeholdersList = new ArrayList();
			if (empStake != null && empStake.length > 0) {
				for (int i = 0; i < empStake.length; i++) {
					BusinessRequirementDocument bean = new BusinessRequirementDocument();
					bean.setEmpid(String.valueOf(empStake[i][0]));
					bean.setStakeholderEmpNameItt(String
							.valueOf(empStake[i][1]));
					bean.setSerialNo(String.valueOf(i));
					stakeholdersList.add(bean);
				}
				brdBean.setStakeHoldersList(stakeholdersList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**Method Name: callProjectTypeOwner()
	 * @purpose :Set Project Type Owner values into drop down
	 * @param brdBean
	 */
	public void callProjectTypeOwner(BusinessRequirementDocument brdBean) {
		String query = " SELECT HRMS_D1_BRD_TYPE.TYPE_ID, HRMS_D1_BRD_TYPE.TYPE_NAME, TYPE_OWNER, EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME"
				+ " FROM HRMS_D1_BRD_TYPE"
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_D1_BRD_TYPE.TYPE_OWNER)"
				+ " WHERE TYPE_ID  = "
				+ brdBean.getProjectType()
				+ " ORDER BY HRMS_D1_BRD_TYPE.TYPE_ID";
		Object[][] objType = getSqlModel().getSingleResult(query);
		if (objType != null && objType.length > 0) {
			brdBean.setFwdempCode(checkNull(String.valueOf(objType[0][2])));
			brdBean
					.setForwardEmpToken(checkNull(String.valueOf(objType[0][3])));
			brdBean.setForwardEmpName(checkNull(String.valueOf(objType[0][4])));
			if (objType[0][1].equals("Project")) {

			}
		}

	}

	/**Method Name: savedStakeholder()
	 * @purpose : set values of stake Holder
	 * @param brdBean
	 */
	public void savedStakeholder(BusinessRequirementDocument brdBean) {
		try {
			String stakeInfoempId = "0";
			String stakeQuery = "SELECT PROJ_STAKE_HOLDER FROM HRMS_D1_BUSINESS_REQUIREMENT WHERE BUSINESS_CODE  = "
					+ brdBean.getBrdCode();
			Object[][] stakeId = getSqlModel().getSingleResult(stakeQuery);
			if ((stakeId != null) && (stakeId.length > 0)) {
				stakeInfoempId = String.valueOf(stakeId[0][0]);
			}
			String keepInfodata = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_ID IN("
					+ stakeInfoempId + ")";
			Object[][] stakeInfo = getSqlModel().getSingleResult(keepInfodata);

			List keepInfolst = new ArrayList();
			List keepInfolst1 = new ArrayList();
			if ((stakeInfo != null) && (stakeInfo.length > 0)) {
				for (int i = 0; i < stakeInfo.length; i++) {
					BusinessRequirementDocument reqBean = new BusinessRequirementDocument();
					//if(brdBean.getBrdSuperUserFlag().equals("BRDSuperUser")){				   
					reqBean.setStakeholderAppEmpNameItt(String
							.valueOf(stakeInfo[i][0]));
					reqBean.setEmpIttId(String.valueOf(stakeInfo[i][1]));
					keepInfolst.add(reqBean);
					// }				   
					//else{
					reqBean.setStakeholderEmpNameItt(String
							.valueOf(stakeInfo[i][0]));
					reqBean.setEmpid(String.valueOf(stakeInfo[i][1]));
					keepInfolst1.add(reqBean);
					//  }
				}
			}
			//if(brdBean.getBrdSuperUserFlag().equals("BRDSuperUser")){			  
			brdBean.setApproverStakeHoldersList(keepInfolst);
			// }else
			brdBean.setStakeHoldersList(keepInfolst1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Method Name: setBusinessUnitDrop()
	 * @purpose : Display Business Unit Name 
	 * @param bean
	 */
	public void setBusinessUnitDrop(BusinessRequirementDocument bean) {
		try {
			TreeMap businessMap = new TreeMap();
			String typeQuery = "SELECT BUSS_UNIT_ID,BUSS_UNIT_NAME " 
				 				+ " FROM  HRMS_D1_BUSSINESS_UNIT";
			Object[][] businessIDObj = getSqlModel()
					.getSingleResult(typeQuery);
			if ((businessIDObj != null) && (businessIDObj.length > 0)) {
				for (int i = 0; i < businessIDObj.length; i++) {
					businessMap.put(String.valueOf(businessIDObj[i][0]), String
							.valueOf(businessIDObj[i][1]));
				}
			}
			bean.setMapBusinessUnitID(businessMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Method Name : 
	 * @purpose : 
	 * @param brdBean
	 */
	public void callBusinessUnitEmp(BusinessRequirementDocument brdBean) {
		try{
		String query = " SELECT BUSS_UNIT_ID,BUSS_UNIT_NAME,HRMS_D1_BUSSINESS_UNIT.BUSS_EMP_ID, EMP_TOKEN ,"
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"	
				+ " FROM  HRMS_D1_BUSSINESS_UNIT "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_D1_BUSSINESS_UNIT.BUSS_EMP_ID)"
				+ " WHERE BUSS_UNIT_ID= "+brdBean.getBusinessUnitID()
				+ " ORDER BY BUSS_UNIT_NAME ";				
		Object[][] objType = getSqlModel().getSingleResult(query);
		if (objType != null && objType.length > 0) {
			brdBean.setFwdempCode(checkNull(String.valueOf(objType[0][2])));
			brdBean
					.setForwardEmpToken(checkNull(String.valueOf(objType[0][3])));
			brdBean.setForwardEmpName(checkNull(String.valueOf(objType[0][4])));		
		}			
		}catch(Exception e){
		e.printStackTrace();
	    }
	}
}