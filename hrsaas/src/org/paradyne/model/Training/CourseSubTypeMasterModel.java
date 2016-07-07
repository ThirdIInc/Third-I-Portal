package org.paradyne.model.Training;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Training.CourseSubTypeMaster;
import org.paradyne.bean.Training.TrainingTypeMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class CourseSubTypeMasterModel extends ModelBase{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(CourseSubTypeMasterModel.class);
	
	public boolean checkDuplicate(CourseSubTypeMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM TRN_COURSE_SUB_TYPE WHERE UPPER(COURSE_SUBTYPE_NAME) LIKE '"
				+ bean.getCourseSubType().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}
	
	/* inserting record */
	public boolean addCourseSubType(CourseSubTypeMaster trnBean) {
		if (!checkDuplicate(trnBean)) {
			/*String query = "SELECT NVL(MAX(COURSE_SUB_TYPE_ID),0)+1 FROM TRN_COURSE_SUB_TYPE";
			Object[][] rel = getSqlModel().getSingleResult(query);
			trnBean.setCourseSubCode(String.valueOf(rel[0][0]));
			trnBean.setHiddenSubcode(String.valueOf(rel[0][0]));
			*/
			Object[][] addObj = new Object[1][3];
			addObj[0][0] = trnBean.getCourseSubType().trim();   // Training name
			addObj[0][1] = trnBean.getCourseSubDesc().trim();  // Training descrition
			addObj[0][2] = trnBean.getUserEmpId();    // emp_id
						
			return getSqlModel().singleExecute(getQuery(1), addObj);

		}// end of if
		else {
			return false;
		}// end of else
	}

	/* modifing record */
	public boolean modCourseSubType(CourseSubTypeMaster bean) {
		
				Object addObj[][] = new Object[1][4];
				
			
				addObj[0][0] =bean.getCourseSubType().trim(); // course name
				addObj[0][1] = bean.getCourseSubDesc().trim(); //course description
				addObj[0][2] = bean.getUserEmpId().trim(); // empId
				addObj[0][3] = bean.getCourseSubCode(); //id
							
				return getSqlModel().singleExecute(getQuery(2), addObj);
		
	}
	/*
	 * for selecting data from list and setting them in respective fields.
	 */
	public void calforeditSubtype(String courseSubCode,CourseSubTypeMaster courseMaster) {
		String query = "  SELECT COURSE_SUB_TYPE_ID, COURSE_SUBTYPE_NAME, COURSE_SUBTYPE_DESCRIPTION, RECORD_CREATEDBY, RECORD_CREATEDON FROM TRN_COURSE_SUB_TYPE"
				+ " WHERE  COURSE_SUB_TYPE_ID = " + courseSubCode
				+ " ORDER BY COURSE_SUB_TYPE_ID  ";
		Object[][] data = getSqlModel().getSingleResult(query);// to get the record and update the data in  double click in the list													
		courseMaster.setCourseSubCode(String.valueOf(data[0][0])); // course subtype Id
		courseMaster.setCourseSubType(String.valueOf(data[0][1])); // course subtype name
		courseMaster.setCourseSubDesc(String.valueOf(data[0][2])); // course subtype description
		courseMaster.setSubCreatedBy(String.valueOf(data[0][3])); // course subtype created
		courseMaster.setSubCreatedOn(String.valueOf(data[0][4])); // course subtype created on
	}

	
	/*
	 * deleting data from list
	 */
	public boolean deleteSubtypeCodeList(CourseSubTypeMaster courseMaster, String[] code) {

		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		Object [][] delParamObj=null;
		if (code != null) {
			 delParamObj = new Object [code.length][2]; 
			for (int i = 0; i < code.length; i++) {
				delParamObj[i][0]=String.valueOf(code[i]);
					// result=true;
				}
			}
		String delQuery = "DELETE FROM TRN_COURSE_SUB_TYPE WHERE COURSE_SUB_TYPE_ID=?";
		Object[] queryArray = new Object[1];
		queryArray[0] = delQuery;
		Vector<Object[][]> dataVector = new Vector<Object[][]>();
		dataVector.add(delParamObj);
		try {
			result = getSqlModel().multiExecute(queryArray, dataVector);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	
	public void courseSubTypeListData(CourseSubTypeMaster courseBean,
			HttpServletRequest request) {
		try {
			ArrayList<CourseSubTypeMaster> list = null;

			Object[][] repData = getSqlModel().getSingleResult(getQuery(4));
			if (repData != null && repData.length > 0) {
				courseBean.setSubTypemodeLength("true");
				String[] pageIndex = Utility.doPaging(courseBean.getMyPage(),
						repData.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					courseBean.setMyPage("1");
				list = new ArrayList<CourseSubTypeMaster>();
				if(repData!=null && repData.length>0){
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					CourseSubTypeMaster bean1 = new CourseSubTypeMaster();
					bean1.setCourseSubCodeIt(String.valueOf(repData[i][0]));
					bean1.setCourseSubTypeIt(String.valueOf(repData[i][1]));
					bean1.setCourseSubDescIt(Utility.checkNull(String.valueOf(repData[i][2])));
					bean1.setSubCreatedByIt(Utility.checkNull(String.valueOf(repData[i][4])));
					bean1.setSubCreatedByIt(Utility.checkNull(String.valueOf(repData[i][3])));
					
					list.add(bean1);
				}
				courseBean.setCourseSubList(list);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean deleteSubtypeCodeList(CourseSubTypeMaster courseMaster, String code) {

		boolean result = false;
				
		try {
			String delQuery = "DELETE FROM TRN_COURSE_SUB_TYPE WHERE COURSE_SUB_TYPE_ID="+code;
				result = getSqlModel().singleExecute(delQuery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;

	}
}
