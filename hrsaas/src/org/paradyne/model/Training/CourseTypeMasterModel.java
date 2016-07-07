package org.paradyne.model.Training;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Training.CourseTypeMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class CourseTypeMasterModel extends ModelBase{

	public void courseData(CourseTypeMaster courseType,
			HttpServletRequest request) {
		Object[][] courseData = getSqlModel().getSingleResult(getQuery(1));
		if (courseData != null && courseData.length > 0) {
			courseType.setModeLength("true"); // set the length of data in the
											// list
			courseType.setTotalRecords(String.valueOf(courseData.length));
			String[] pageIndex = Utility.doPaging(courseType.getMyPage(),
					courseData.length, 20);// to display the page number
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
				courseType.setMyPage("1");
			ArrayList<CourseTypeMaster> List = new ArrayList<CourseTypeMaster>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				CourseTypeMaster bean = new CourseTypeMaster();
				bean.setCourseCode(Utility.checkNull(String.valueOf(courseData[i][0])));
				bean.setCourseName(Utility.checkNull(String.valueOf(courseData[i][1])));
				bean.setCourseType(Utility.checkNull(String.valueOf(courseData[i][2])));
				bean.setSkillAdv(Utility.checkNull(String.valueOf(courseData[i][3])));
				bean.setCourseAddDate(Utility.checkNull(String.valueOf(courseData[i][4])));
				bean.setCourseAddedBy(Utility.checkNull(String.valueOf(courseData[i][5])));				
				List.add(bean);
			}
			courseType.setCourseTypeList(List);
										
		}
	}

	public void getRecords(CourseTypeMaster courseType) {
		try {
		  Object []paramID = new Object[1];
		  courseType.setCourseCode(courseType.getHiddencode());
		  paramID [0]= courseType.getCourseCode();
		  System.out.println("Course ID---------"+ courseType.getHiddencode());
		  Object [][] courseObj= getSqlModel().getSingleResult(getQuery(2),paramID);
		  if(courseObj!= null && courseObj.length >0){
			  courseType.setCourseCode(Utility.checkNull(String.valueOf(courseObj[0][0])));
			  courseType.setCourseName(Utility.checkNull(String.valueOf(courseObj[0][1])));
			  courseType.setCourseType(Utility.checkNull(String.valueOf(courseObj[0][2])));
			  courseType.setSkillAdv(Utility.checkNull(String.valueOf(courseObj[0][3])));
			  if(String.valueOf(courseObj[0][4]).equals("Y")){
				  courseType.setIsCertified("true");
			  }
			  courseType.setLevel(Utility.checkNull(String.valueOf(courseObj[0][5])));
			  String attFile =null;
			  String [] attachedFile= null; 
			  attFile = Utility.checkNull(String.valueOf(courseObj[0][6]));
			  if (!attFile.equals(" ")) {
				 attachedFile = attFile.split(",");			 
				 System.out.println("Uploaded Files-----------"+attachedFile);
				 ArrayList<CourseTypeMaster> fileList = new ArrayList<CourseTypeMaster>();
				 for(int i=0;i< attachedFile.length;i++){
					 CourseTypeMaster bean = new CourseTypeMaster();
					 bean.setAttFileSrNo(String.valueOf(i + 1));
					 bean.setAttFileName(attachedFile[i]);
					 fileList.add(bean);
				 }
				 courseType.setAttchFilesList(fileList);
			  }
			  if(String.valueOf(courseObj[0][7]).equals("Y")){
						  courseType.setIsACtive("true");
			  }
			 }			  			  		
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}

	public boolean save(CourseTypeMaster courseType, String[] attFile) {
		boolean result = false;		
		try {
			String uploaddocument = "";
			if (attFile != null && attFile.length > 0) {
				for (int i = 0; i < attFile.length; i++) {
					uploaddocument += attFile[i] + ",";
				}
				uploaddocument = uploaddocument.substring(0, uploaddocument
						.length() - 1);
			}
			
			String maxCourseID = "SELECT NVL(MAX(COURSE_ID),0)+1 FROM TRN_COURSE_TYPE";
			Object[][] outMaxID = getSqlModel().getSingleResult(maxCourseID);
			Object[][] insertObj = new Object[1][9];			
			insertObj[0][0] = outMaxID[0][0];
			insertObj[0][1] = courseType.getCourseName();
			insertObj[0][2] = courseType.getCourseType();
			if (courseType.getIsCertified().equals("true")) {
				insertObj[0][3] = "Y";
			}
			insertObj[0][4] = courseType.getSkillAdv();
			insertObj[0][5] = courseType.getLevel();
			if (courseType.getIsACtive().equals("true")) {
				insertObj[0][6] = "Y";
			}			
			insertObj[0][7] = uploaddocument;			
			insertObj[0][8] = courseType.getUserEmpId();
			result = getSqlModel().singleExecute(getQuery(3), insertObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean update(CourseTypeMaster courseType, String[] attFile) {
		boolean outResult = false;
		try {
			String uploaddocument = "";
			if (attFile != null && attFile.length > 0) {
				for (int i = 0; i < attFile.length; i++) {
					uploaddocument += attFile[i] + ",";
				}
				uploaddocument = uploaddocument.substring(0, uploaddocument
						.length() - 1);
			}
			Object[][] updateObj = new Object[1][9];			
			updateObj[0][0] = courseType.getCourseName();
			updateObj[0][1] = courseType.getCourseType();
			if (courseType.getIsCertified().equals("true")) {
				updateObj[0][2] = "Y";
			}
			updateObj[0][3] = courseType.getSkillAdv();
			updateObj[0][4] = courseType.getLevel();
			if (courseType.getIsACtive().equals("true")) {
				updateObj[0][5] = "Y";
			}
			updateObj[0][6] = uploaddocument;
			updateObj[0][7] = courseType.getUserEmpId();
			updateObj[0][8] = courseType.getCourseCode();
			outResult = getSqlModel().singleExecute(getQuery(5), updateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outResult;
	}


	
	/*public void courseSubTypeData(CourseTypeMaster courseType,
			HttpServletRequest request) {
		Object[][] courseData = getSqlModel().getSingleResult(getQuery(4));
		if (courseData != null && courseData.length > 0) {
			courseType.setSubmodeLength("true"); // set the length of data in the
											// list
			courseType.setTotalRecords(String.valueOf(courseData.length));
			String[] pageIndex = Utility.doPaging(courseType.getMyPage(),
					courseData.length, 20);// to display the page number
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
				courseType.setMyPage("1");
			ArrayList<CourseTypeMaster> list = new ArrayList<CourseTypeMaster>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				CourseTypeMaster bean = new CourseTypeMaster();
				bean.setCourseSubCodeIt(Utility.checkNull(String.valueOf(courseData[i][0])));
				bean.setCourseSubTypeIt(Utility.checkNull(String.valueOf(courseData[i][1])));
				bean.setCourseSubDescIt(Utility.checkNull(String.valueOf(courseData[i][2])));
				bean.setCourseCode(Utility.checkNull(String.valueOf(courseData[i][3])));
				bean.setSubCreatedOnIt(Utility.checkNull(String.valueOf(courseData[i][4])));
				bean.setSubCreatedByIt(Utility.checkNull(String.valueOf(courseData[i][5])));
							
				list.add(bean);
			}
			courseType.setCourseSubTypeList(list);
										
		}
	}*/

	public void removeUploadFile(String[] srNo, String[] attachFileNm,
			CourseTypeMaster courseType) {
		ArrayList<CourseTypeMaster> removeList = new ArrayList<CourseTypeMaster>();
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				CourseTypeMaster bean = new CourseTypeMaster();
				bean.setAttFileSrNo(String.valueOf(i + 1));
				bean.setAttFileName(attachFileNm[i]);
				removeList.add(bean);
			}
			removeList.remove(Integer.parseInt(courseType
					.getRemoveUpload()) - 1);
			courseType.setSetFilesFlag("true");
		}
		courseType.setAttchFilesList(removeList);
	}

	public void displayUploadFile(String[] srNo,
			String[] attachFileNm, CourseTypeMaster courseType) {		
		try {
			ArrayList<CourseTypeMaster> attArrayList = new ArrayList<CourseTypeMaster>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					CourseTypeMaster bean = new CourseTypeMaster();
					bean.setAttFileName(attachFileNm[i]);
					bean.setAttFileSrNo(srNo[i]);
					attArrayList.add(bean);
				}
				courseType.setAttchFilesList(attArrayList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setAttachedList(String[] srNo, String[] attchedName,
			CourseTypeMaster courseType) {
		try {
			CourseTypeMaster bean = new CourseTypeMaster();
			bean.setAttFileName(courseType.getUploadFileName());
			ArrayList<CourseTypeMaster> attachedList = displayNewValueProofList(
					srNo, attchedName, courseType);
			bean.setAttFileSrNo(String.valueOf(attachedList.size() + 1));// sr no
			attachedList.add(bean);
			courseType.setAttchFilesList(attachedList);
			courseType.setSetFilesFlag("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private ArrayList<CourseTypeMaster> displayNewValueProofList(String[] srNo,
			String[] attName, CourseTypeMaster courseType) {
		ArrayList<CourseTypeMaster> attFileList = null;
		try {
			attFileList = new ArrayList<CourseTypeMaster>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					CourseTypeMaster bean = new CourseTypeMaster();
					bean.setAttFileName(attName[i]);
					bean.setAttFileSrNo(srNo[i]);
					attFileList.add(bean);
				}
				courseType.setSetFilesFlag("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try{
				
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
		return attFileList;
	}
	public LinkedHashMap courseSubDat(CourseTypeMaster courseType) {
		
		String sql="SELECT  COURSE_SUB_TYPE_ID, COURSE_SUBTYPE_NAME FROM TRN_COURSE_SUB_TYPE ORDER BY COURSE_SUB_TYPE_ID";
		Object[][] data=getSqlModel().getSingleResult(sql);
		LinkedHashMap map=new LinkedHashMap();
		map.put("Select","Select");
		for(int i=0;i<data.length;i++){
		map.put(String.valueOf(data[i][0]), String.valueOf(data[i][1]));
		}
		
		return map;
	}

}
