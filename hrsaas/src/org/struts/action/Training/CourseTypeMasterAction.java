package org.struts.action.Training;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.apache.commons.fileupload.FileItem;
import org.paradyne.bean.Training.CourseTypeMaster;
import org.paradyne.model.Training.CourseTypeMasterModel;
import org.paradyne.model.WBT.ProgrammeMasterModel;
import org.struts.lib.ParaActionSupport;

public class CourseTypeMasterAction extends ParaActionSupport{
	private static final long serialVersionUID = 1L;
	CourseTypeMaster courseType;
	LinkedHashMap<String,String> map=null;
	
	public String uploadForm() {

		return NONE;

		}

	
	/**
	 * Method : prepare_local. 
	 * Purpose : Used to set menu code and create bean instance
	 * @throws Exception : Exception
	 */
	public void prepare_local() throws Exception {
		courseType = new CourseTypeMaster ();
		courseType.setMenuCode(5042);		
	}

	/**
	 * Method : getModel. 
	 * Purpose : Used to return bean instance
	 * @return Object
	 */
	public Object getModel() {		
		return courseType;
	}
	
	/** 
	 * Used to set drafted, in-process, sent-back application list.
	 * @return String
	 */
	public String input() throws Exception{
		CourseTypeMasterModel model = new CourseTypeMasterModel();
		model.initiate(context, session);
		model.courseData(courseType, request);	
		courseType.setIsAttFile("true");
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	public void prepare_withLoginProfileDetails() throws Exception{	
		String poolName = String.valueOf(session
				.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null)) {
			poolName = "\\" + poolName;
		}
		
		String fileDataPath = getText("data_path") + "\\upload" + poolName
				+ "\\TrainingAndDevelopment\\";
		System.out.println("data path------- : " + fileDataPath);
		courseType.setDataPath(fileDataPath);
		map=setCourseSubType();
	}
	
	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		CourseTypeMasterModel model = new CourseTypeMasterModel();
		model.initiate(context, session);
		model.courseData(courseType, request);
		courseType.setIsAttFile("true");
		courseType.setMap(map);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	
	/** To display screen in AddNew Mode
	 * @return string
	 */
	public String addNew() {
		try {		
			courseType.setIsAttFile("true");
			courseType.setMap(map);
			getNavigationPanel(4);
		} catch(Exception e) {
			e.printStackTrace();			
		}
		return "courseData";
	}
	
	/**
	 * To Search Course Type
	 * @return String
	 */	
	public String f9action(){
		 String query = " SELECT COURSE_ID,COURSE_NAME FROM TRN_COURSE_TYPE"
			 			+ " ORDER BY COURSE_ID"; 	 	 
		String[] headers = { getMessage("cCode"), getMessage("cName")};
		String[] headerWidth = { "50", "50"};
		String[] fieldNames = {"courseCode", "courseName"};
		int[] columnIndex = {0, 1};
		String submitFlag = "true";
		String submitToMethod = "CourseTypeMaster_getRecord.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";		
	}
	
	/** To call record for Edit
	 * @return String
	 */
	public String callForEdit(){
		try {
			CourseTypeMasterModel model = new CourseTypeMasterModel();
			model.initiate(context, session);	
			model.getRecords(courseType);
			courseType.setIsAttFile("true");
			
			courseType.setMap(map);
			getNavigationPanel(2);
			model.terminate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "courseData";
	}
	
	/**
	 * Method : save. 
	 * Purpose : Used to Save or Edit record
	 * @return String
	 */
	
	public String save(){
		boolean result=false;
		try{
			CourseTypeMasterModel model = new CourseTypeMasterModel();
			model.initiate(context, session);
			String [] attFile = request.getParameterValues("attFileName");
			if(courseType.getCourseCode().equals("")){
				result= model.save(courseType, attFile);
				if(result){
					addActionMessage("Record Saved Successfully");
				}
				else
					addActionMessage(getMessage("save.error"));
				getNavigationPanel(4);
			}
			else {
				result= model.update(courseType,attFile);
				if(result)
					addActionMessage("Record Updated Successfully");
				else
					addActionMessage("Record cannot be Updated");
				courseType.setIsAttFile("true");
				getNavigationPanel(2);
			}					
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "courseData";
	}
	
	public String reset(){
		courseType.setCourseCode("");
		courseType.setCourseName("");
		courseType.setSkillAdv("");
		courseType.setIsCertified("");
		courseType.setIsACtive("");
		courseType.setUploadFileName("");
		courseType.setLevel("");
		getNavigationPanel(4);
		return "courseData";
	}
	
	public String addMultipleFiles(){
		try {
			String[] srNo = request.getParameterValues("attFileSrNo");
			String[] attachFileNm = request.getParameterValues("attFileName");
			CourseTypeMasterModel model = new CourseTypeMasterModel();
			model.initiate(context, session);
			model.displayUploadFile(srNo, attachFileNm, courseType);
			model.setAttachedList(srNo, attachFileNm, courseType);
			courseType.setUploadFileName("");
			courseType.setIsAttFile("true");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(courseType.getCourseCode().equals("")){
		getNavigationPanel(4);
		}
		else
			getNavigationPanel(2);
		return "courseData";
	}
	
	public String removeUploadFile() {
		try {
			getNavigationPanel(4);
			String[] srNo = request.getParameterValues("attFileSrNo");
			String[] attachFileNm = request.getParameterValues("attFileName");// keep
			CourseTypeMasterModel model = new CourseTypeMasterModel();
			model.initiate(context, session);
			model.removeUploadFile(srNo, attachFileNm, courseType);
		
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "courseData";
	}
	
	public String viewUploadedFile(){
		try {
			OutputStream oStream = null;
			response.getOutputStream();
			FileInputStream fsStream = null;
			String fileName = "";
			String mimeType = "";
			try {
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "/" + poolName;
				}
				fileName = request.getParameter("templateName");
				fileName = fileName.replace(".", "#");
				String[] extension = fileName.split("#");
				String ext = extension[extension.length - 1];
				fileName = fileName.replace("#", ".");
				if (ext.equals("pdf")) {
					mimeType = "acrobat";
				} else if (ext.equals("doc")) {
					mimeType = "msword";
				} else if (ext.equals("xls")) {
					mimeType = "msexcel";
				} else if (ext.equals("xlsx")) {
					mimeType = "msexcel";
				} else if (ext.equals("jpg")) {
					mimeType = "jpg";
				} else if (ext.equals("txt")) {
					mimeType = "txt";
				} else if (ext.equals("gif")) {
					mimeType = "gif";
				}
				if (fileName == null) {
					if (fileName.length() <= 0) {
						fileName = "blank.doc";
					}
				}
				String path = getText("data_path") + "/TrainingAndDevelopment"
						+ poolName + "/" + fileName;
				oStream = response.getOutputStream();
				if (ext.equals("pdf")) {
				} else {
					response.setHeader("Content-type", "application/"
							+ mimeType + "");
				}
				response.setHeader("Content-disposition",
						"attachment;filename=\"" + fileName + "\"");
				int iChar;
				fsStream = new FileInputStream(path);
				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}
			} catch (FileNotFoundException e) {				
				addActionMessage("File document not found");
			} 
			catch (Exception e) {
				e.printStackTrace();
			} 
			finally {
				if (fsStream != null) {
					fsStream.close();
				}
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				} 
			} 		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public LinkedHashMap setCourseSubType() {
		
			CourseTypeMasterModel model = new CourseTypeMasterModel();
			model.initiate(context, session);
			 map=model.courseSubDat(courseType);
			return map;
	
	}
}
