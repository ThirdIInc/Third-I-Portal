package org.paradyne.model.recruitment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.InterviewAssessMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.apache.log4j.Logger;

/**
 * @author Nilesh Dhandare
 * @modified by @author Prajakta.Bhandare 
 * @date 05 march 2013
 */

public class InterviewAssessMasterModel extends ModelBase {

	/** * logger.  */
	private Logger logger = Logger.getLogger(ModelBase.class);
	
	
	/**Method : checkNull.
	 * Purpose : used to check null values
	 * @param result : result
	 * @return String
	 */
	public String checkNull(final String result) {
		if (result == null || "null".equals(result)) {
			return "";
		} else {
			return result;
		} 
	}

	 
	/**
	 * following function is called to delete a record.
	 * 
	 * @param bean : bean
	 * @return boolean
	 */
	public boolean delRecord(final InterviewAssessMaster bean) {
		final Object[][] del = new Object[1][1];
		del[0][0] = bean.getParameterCode();
		return this.getSqlModel().singleExecute(this.getQuery(4), del);
	}


	/**
	 * Method : showInitialGroupList. 
	 * Purpose : This method is used to set
	 * initial group list
	 * 
	 * @param bean : instance of InterviewAssessMaster
	 * @param request : instance of HttpServletRequest
	 */
	public void showInitialGroupList(final InterviewAssessMaster bean,
			final HttpServletRequest request) {
		try {
			final Object[][] grpData = this.getSqlModel().getSingleResult(
							"SELECT HRMS_REC_INTERVIEW_GROUP.GROUP_ID, NVL(HRMS_REC_INTERVIEW_GROUP.GROUP_NAME,''), NVL(HRMS_REC_INTERVIEW_GROUP.GROUP_DESCRIPTION,''), " + 
									" CASE WHEN HRMS_REC_INTERVIEW_GROUP.GROUP_IS_ACTIVE='Y' THEN 'Yes' ELSE 'No' END " + 
									" FROM HRMS_REC_INTERVIEW_GROUP ORDER BY HRMS_REC_INTERVIEW_GROUP.GROUP_ID");
			if (grpData != null && grpData.length > 0) {
				bean.setListLength(true);
				bean.setTotalNoOfRecords(String.valueOf(grpData.length));
				final String[] pageIndex = Utility.doPaging(bean.getMyPage(),
						grpData.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
				}
				request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
				if ("1".equals(pageIndex[4])) {
					bean.setMyPage("1");
				}
				final List<InterviewAssessMaster> list = new ArrayList<InterviewAssessMaster>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final InterviewAssessMaster innerBean = new InterviewAssessMaster();
					innerBean.setGroupCode(this.checkNull(String.valueOf(grpData[i][0])));
					innerBean.setGroupNameItr(this.checkNull(String.valueOf(grpData[i][1])));
					innerBean.setGroupDescriptionItr(this.checkNull(String.valueOf(grpData[i][2])));
					innerBean.setGroupIsActiveItr(this.checkNull(String.valueOf(grpData[i][3])));
					list.add(innerBean);
				}
				bean.setGroupList(list);
			} else {
				bean.setGroupList(null);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method : addGroupData. 
	 * Purpose : This method is used to save group data
	 * 
	 * @param bean : bean
	 * @return boolean
	 */
	public boolean addGroupData(final InterviewAssessMaster bean) {
		boolean result = false;
		try {
			final Object[][] addObj = new Object[1][4];
			addObj[0][0] = bean.getGroupName();
			addObj[0][1] = bean.getGroupDescription();
			addObj[0][2] = bean.getGroupAbbreviation();
			addObj[0][3] = bean.getIsGroupActive();
			final String insertQuery = "INSERT INTO HRMS_REC_INTERVIEW_GROUP (HRMS_REC_INTERVIEW_GROUP.GROUP_NAME, HRMS_REC_INTERVIEW_GROUP.GROUP_DESCRIPTION, HRMS_REC_INTERVIEW_GROUP.GROUP_ABBR, HRMS_REC_INTERVIEW_GROUP.GROUP_IS_ACTIVE, HRMS_REC_INTERVIEW_GROUP.GROUP_ID) " + 
					" VALUES (?, ?, ?, ?, (SELECT NVL(MAX(HRMS_REC_INTERVIEW_GROUP.GROUP_ID),0)+1 FROM HRMS_REC_INTERVIEW_GROUP))";
			result = this.getSqlModel().singleExecute(insertQuery, addObj);
			if (result) {
				final Object[][] data = this.getSqlModel().getSingleResult(
								" SELECT NVL(MAX(HRMS_REC_INTERVIEW_GROUP.GROUP_ID),0) FROM HRMS_REC_INTERVIEW_GROUP ");
				if (data != null && data.length > 0) {
					bean.setGroupCode(String.valueOf(data[0][0]));
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	} 
	
	/**
	 * Method : updateGroupData. 
	 * Purpose : used to update group data
	 * 
	 * @param bean : bean
	 * @return boolean
	 */
	public boolean updateGroupData(final InterviewAssessMaster bean) {
		boolean result = false;
		try {
			final Object[][] updateObj = new Object[1][5];
			updateObj[0][0] = bean.getGroupName();
			updateObj[0][1] = bean.getGroupDescription();
			updateObj[0][2] = bean.getGroupAbbreviation();
			updateObj[0][3] = bean.getIsGroupActive();
			updateObj[0][4] = bean.getGroupCode();
			result = this.getSqlModel().singleExecute(
							"UPDATE HRMS_REC_INTERVIEW_GROUP SET HRMS_REC_INTERVIEW_GROUP.GROUP_NAME = ?, HRMS_REC_INTERVIEW_GROUP.GROUP_DESCRIPTION = ?, HRMS_REC_INTERVIEW_GROUP.GROUP_ABBR = ?, HRMS_REC_INTERVIEW_GROUP.GROUP_IS_ACTIVE = ? WHERE  HRMS_REC_INTERVIEW_GROUP.GROUP_ID = ? ",
							updateObj);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	/**
	 * Method : deleteGroupData. 
	 * Purpose : This method is used to delete group
	 * data along with the mapped parameters
	 * 
	 * @param bean : instance of bean
	 * @return boolean
	 */
	public boolean deleteGroupData(final InterviewAssessMaster bean) {
		boolean result = false;
		try {
			result = this.getSqlModel().singleExecute(
							"DELETE FROM HRMS_REC_INTERVIEW_ASSESSMENT WHERE HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_GROUP_ID = " + bean.getGroupCode());
			if (result) {
				result = this.getSqlModel().singleExecute(
								"DELETE FROM HRMS_REC_INTERVIEW_GROUP WHERE HRMS_REC_INTERVIEW_GROUP.GROUP_ID = " + bean.getGroupCode());
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method : getGroupRecord. 
	 * Purpose : This method is used to set details of
	 * selected group ID
	 * 
	 * @param bean : instance of InterviewAssessMaster
	 */
	public void getGroupRecord(final InterviewAssessMaster bean) {
		try {
			final Object[][] groupData = this.getSqlModel().getSingleResult(
							" SELECT HRMS_REC_INTERVIEW_GROUP.GROUP_NAME, HRMS_REC_INTERVIEW_GROUP.GROUP_ABBR, HRMS_REC_INTERVIEW_GROUP.GROUP_DESCRIPTION, HRMS_REC_INTERVIEW_GROUP.GROUP_IS_ACTIVE, HRMS_REC_INTERVIEW_GROUP.GROUP_ID " + 
									" FROM HRMS_REC_INTERVIEW_GROUP WHERE HRMS_REC_INTERVIEW_GROUP.GROUP_ID = " + bean.getGroupCode()); 
			if (groupData != null && groupData.length > 0) {
				bean.setGroupName(checkNull(String.valueOf(groupData[0][0])));
				bean.setGroupAbbreviation(checkNull(String.valueOf(groupData[0][1])));
				bean.setGroupDescription(checkNull(String.valueOf(groupData[0][2])));
				bean.setIsGroupActive(checkNull(String.valueOf(groupData[0][3])));
				bean.setGroupCode(checkNull(String.valueOf(groupData[0][4])));
			}
		} catch (final Exception e) {
			this.logger.error("Exception in getGroupRecord -- " + e);
		}
	}
	
	
	/**
	 * Method : deleteCheckedGroupRecords. 
	 * Purpose : Used to delete checked records
	 * 
	 * @param bean : instance of InterviewAssessMaster
	 * @param code - unique code for deletion.
	 * @return boolean.
	 */
	public boolean deleteCheckedGroupRecords(final InterviewAssessMaster bean, final String[] code) {
		boolean result = false;
		String codeTodelete = "";
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!"".equals(code[i])) {
					codeTodelete += String.valueOf(code[i]) + ",";
				}
			}
			codeTodelete = codeTodelete.substring(0, codeTodelete.length() - 1);
			result = this.getSqlModel().singleExecute(
							"DELETE FROM HRMS_REC_INTERVIEW_ASSESSMENT WHERE HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_GROUP_ID IN (" + codeTodelete + ")");
			if (result) {
				result = this.getSqlModel().singleExecute(
								"DELETE FROM HRMS_REC_INTERVIEW_GROUP WHERE HRMS_REC_INTERVIEW_GROUP.GROUP_ID IN (" + codeTodelete + ")");
			}
		}

		return result;
	}

	/**Method : setGroupDetailsForParameter.
	 * Purpose : this method is used to set Group name and description
	 * @param bean : instance of InterviewAssessMaster
	 * @param groupCode : Group Code
	 */
	public void setGroupDetailsForParameter(final InterviewAssessMaster bean, final String groupCode) {
		try {
			final Object[][] getGroupDetails = this.getSqlModel().getSingleResult("SELECT HRMS_REC_INTERVIEW_GROUP.GROUP_NAME, HRMS_REC_INTERVIEW_GROUP.GROUP_DESCRIPTION " + 
																			" FROM HRMS_REC_INTERVIEW_GROUP WHERE HRMS_REC_INTERVIEW_GROUP.GROUP_ID = " + groupCode);
			if (getGroupDetails != null && getGroupDetails.length > 0) {
				bean.setGroupName(this.checkNull(String.valueOf(getGroupDetails[0][0])));
				bean.setGroupDescription(this.checkNull(String.valueOf(getGroupDetails[0][1])));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
	}

	/**@modified By @author Prajakta.Bhandare
	 * Method : addParameterToList.
	 * Purpose : This method is used to add parameter to the list
	 * @param parameterName : Parameter name
	 * @param parameterDescription : Parameter description
	 * @param parameterIsActive : parameter status
	 * @param parameterNameItr : Parameter name from list
	 * @param parameterDescriptionItr : Parameter description from list
	 * @param parameterIsActiveItr : parameter status from list
	 * @param bean : instance of InterviewAssessMaster
	 */
	public void addParameterToList(final InterviewAssessMaster bean,
			final String parameterName, final String parameterDescription,
			final String parameterIsActive, final String[] parameterNameItr,
			final String[] parameterDescriptionItr,
			final String[] parameterIsActiveItr, HttpServletRequest request) {
		try {
			final List<InterviewAssessMaster> paramList = new ArrayList<InterviewAssessMaster>();
			final InterviewAssessMaster outerBean = new InterviewAssessMaster();
			if (parameterNameItr != null) {//if data
				if(bean.getParaCode()==null || bean.getParaCode().equals("") || bean.getParaCode().length() < 0 ){//if not in edit mode
					
					for (int i =0; i < parameterNameItr.length; i++) {//i for loop
						final InterviewAssessMaster innerBean = new InterviewAssessMaster();
						innerBean.setParameterNameItr(String.valueOf(parameterNameItr[i]));
						innerBean.setParameterDescriptionItr(String.valueOf(parameterDescriptionItr[i]));
						innerBean.setParameterIsActiveItr("Yes".equals(String.valueOf(parameterIsActiveItr[i])) ? "Yes" : "No" );
						paramList.add(innerBean);
					}//end of for loop
					
					outerBean.setParameterNameItr(parameterName);
					outerBean.setParameterDescriptionItr(parameterDescription);
					outerBean.setParameterIsActiveItr("Y".equals(parameterIsActive) ? "Yes" : "No");
					paramList.add(outerBean);
			}//end if not in edit mode
				
				else{
					for (int i =0; i < parameterNameItr.length; i++) {// i for loop
						final InterviewAssessMaster innerBean = new InterviewAssessMaster();
						if(i==Integer.parseInt(String.valueOf(bean.getParaCode()))-1){//if edited record
							innerBean.setParameterNameItr(bean.getParaName());
							innerBean.setParameterDescriptionItr(bean.getParaDes());
							innerBean.setParameterIsActiveItr("Y".equals(bean.getParaIsActive()) ? "Yes" : "No" );
						}//end of if
						else{
						innerBean.setParameterNameItr(String.valueOf(parameterNameItr[i]));
						innerBean.setParameterDescriptionItr(String.valueOf(parameterDescriptionItr[i]));
						innerBean.setParameterIsActiveItr("Yes".equals(String.valueOf(parameterIsActiveItr[i])) ? "Yes" : "No" );
						}//end of else
						paramList.add(innerBean);
					}//end of i for loop
					}//end of else
						
			}//end if data
			else{
			outerBean.setParameterNameItr(parameterName);
			outerBean.setParameterDescriptionItr(parameterDescription);
			outerBean.setParameterIsActiveItr("Y".equals(parameterIsActive) ? "Yes" : "No");
			paramList.add(outerBean);
			}
			if (paramList.size() > 0) {//if sizegreater
				bean.setDataPresent(true);
			}//end if size greater
			bean.setParameterList(paramList);
			
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * Method : saveParameters.
	 * 
	 * @param intwassMaster : instance of InterviewAssessMaster
	 * @param groupCode : Group Code
	 * @param parameterIsActiveItr : parameter status from list
	 * @param parameterDescriptionItr : parameter description from list
	 * @param parameterNameItr : parameter name from list
	 * @return boolean
	 */
	public boolean saveParameters(final InterviewAssessMaster intwassMaster,
			final String groupCode, final String[] parameterNameItr,
			final String[] parameterDescriptionItr,
			final String[] parameterIsActiveItr) {
		boolean result = false;
		try {
			if (parameterNameItr != null && parameterDescriptionItr.length > 0) {
				final Object[][] autoIncrementParametrId = this.getSqlModel().getSingleResult("SELECT NVL(MAX(HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_CODE),0) FROM HRMS_REC_INTERVIEW_ASSESSMENT ");
				Object[][] insertObject = new Object[parameterNameItr.length][5];
				result = this.getSqlModel().singleExecute("DELETE FROM HRMS_REC_INTERVIEW_ASSESSMENT WHERE HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_GROUP_ID = " + groupCode);
				String insertParametersQuery = "INSERT INTO HRMS_REC_INTERVIEW_ASSESSMENT(HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_PARAM, HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_DESC, HRMS_REC_INTERVIEW_ASSESSMENT.REC_ISACTIVE, " + 
											   " HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_GROUP_ID, HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_CODE) " + 
				  							   " VALUES(?, ?, ?, ?, ?) ";
				if (result) {
					for (int i = 0; i < parameterNameItr.length; i++) {
						insertObject[i][0] = parameterNameItr[i];
						insertObject[i][1] = parameterDescriptionItr[i];
						insertObject[i][2] = "Yes".equals(String.valueOf(parameterIsActiveItr[i])) ? "Y" : "N";
						insertObject[i][3] = groupCode;
						insertObject[i][4] = String.valueOf(Integer.parseInt(String.valueOf(autoIncrementParametrId[0][0]))+(i+1));
					}
					this.getSqlModel().singleExecute(insertParametersQuery, insertObject);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * Method : setSavedParameters.
	 * Purpose : This method is used to set saved parameters
	 * @param intwassMaster : instance of InterviewAssessMaster
	 * @param groupCode : Group Code
	 */
	public void setSavedParameters(final InterviewAssessMaster intwassMaster, final String groupCode,HttpServletRequest request) {
		try {
			final Object[][] parametersData = this.getSqlModel().getSingleResult("SELECT HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_PARAM, HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_DESC, " 
					+" DECODE(HRMS_REC_INTERVIEW_ASSESSMENT.REC_ISACTIVE,'Y','Yes','N','No'),REC_ASSESS_CODE "
					+" FROM HRMS_REC_INTERVIEW_ASSESSMENT WHERE HRMS_REC_INTERVIEW_ASSESSMENT.REC_ASSESS_GROUP_ID = " + groupCode);
			final ArrayList<InterviewAssessMaster> innerList = new ArrayList<InterviewAssessMaster>();
			if (parametersData != null && parametersData.length > 0) {
				
				for (int i =0; i< parametersData.length; i++) {
					final InterviewAssessMaster innerBean = new InterviewAssessMaster();
					innerBean.setParameterNameItr(this.checkNull(String.valueOf(parametersData[i][0])));
					innerBean.setParameterDescriptionItr(this.checkNull(String.valueOf(parametersData[i][1])));
					innerBean.setParameterIsActiveItr(this.checkNull(String.valueOf(parametersData[i][2])));
					innerBean.setParameterCodeItr(this.checkNull(String.valueOf(parametersData[i][3])));
					innerList.add(innerBean);
				}
				if (innerList.size() > 0) {
					intwassMaster.setDataPresent(true);
				}
				intwassMaster.setParameterList(innerList);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** @author Prajakta.Bhandare
	 * Method : getRecord. 
	 * Purpose : This method is used to set details of
	 * selected group ID
	 * 
	 * @param bean : instance of InterviewAssessMaster
	 */
	public void getRecord(final InterviewAssessMaster intwassMaster,final String[] parameterNameItr, final String[] parameterDescriptionItr,
			final String[] parameterIsActiveItr, HttpServletRequest request) {
		try {
			intwassMaster.setParaName(intwassMaster.getParaName());
			intwassMaster.setParaDes(intwassMaster.getParaDes());
			intwassMaster.setParaIsActive(intwassMaster.getParaIsActive());
			
			final List<InterviewAssessMaster> paramList = new ArrayList<InterviewAssessMaster>();
			if (parameterNameItr != null) {//if data
				for (int i =0; i < parameterNameItr.length; i++) {//i for loop
					final InterviewAssessMaster innerBean = new InterviewAssessMaster();
					innerBean.setParameterNameItr(String.valueOf(parameterNameItr[i]));
					innerBean.setParameterDescriptionItr(String.valueOf(parameterDescriptionItr[i]));
					innerBean.setParameterIsActiveItr("Yes".equals(String.valueOf(parameterIsActiveItr[i])) ? "Yes" : "No" );
					paramList.add(innerBean);
				}//end of i for loop
			}//end if data
			if (paramList.size() > 0) {//if size is greater
				intwassMaster.setDataPresent(true);
			}//end if size is greater
			intwassMaster.setParameterList(paramList);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
