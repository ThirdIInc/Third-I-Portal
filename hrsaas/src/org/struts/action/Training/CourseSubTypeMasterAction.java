package org.struts.action.Training;

import org.paradyne.bean.Training.CourseSubTypeMaster;
import org.paradyne.model.Training.CourseSubTypeMasterModel;
import org.struts.lib.ParaActionSupport;

public class CourseSubTypeMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CourseSubTypeMasterAction.class);
	CourseSubTypeMaster courseType;
	CourseSubTypeMasterModel model;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		courseType=new CourseSubTypeMaster();
		courseType.setMenuCode(5042);
		 model = new CourseSubTypeMasterModel();
		model.initiate(context, session);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return courseType;
	}

	public CourseSubTypeMaster getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseSubTypeMaster courseType) {
		this.courseType = courseType;
	}

	public String input() {
		try {
			model.courseSubTypeListData(courseType, request);
			model.terminate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "input";
	}
	public String saveCourseSubType() throws Exception {
				
		boolean result;

		if(courseType.getCourseSubCode().equals("")) {
			logger.info("else addDiv");
			result = model.addCourseSubType(courseType);
			if(result) {
				addActionMessage(getMessage("save"));
				
				
			} else {
				addActionMessage(getMessage("duplicate"));
				
				
			}// end of else
		} else {
			result = model.modCourseSubType(courseType);
			System.out.println("------RESULT--------------"+result);
			model.terminate();
			if(result) {
				addActionMessage(getMessage("update"));
				
				
			} else {
				addActionMessage(getMessage("duplicate"));
				
			
			}// end of else
		}// end of else
		reset();
		return input();
	}
	
	public String editSubType() {
	String[] courseSubCodeIt=request.getParameterValues("courseSubCodeIt");
	String courseSubCode=request.getParameter("courseSubCode");
	model.calforeditSubtype(courseSubCode,courseType);
		return input();
	}

	/*public String deleteCourseSubType(){
		String[] code = request.getParameterValues("courseSubCodeIt");
		model.deleteSubtypeCodeList(courseType, code);
		return input();
		
	}*/
	public String deleteCourseSubType(){
		String code = request.getParameter("courseSubCodeIt");
		model.deleteSubtypeCodeList(courseType, code);
		
		return input();
		
	}
	
	public String reset() {
		
		courseType.setCourseSubCode("");
		courseType.setCourseSubType("");
		courseType.setCourseSubDesc("");
		courseType.setSubCreatedOn("");
		courseType.setSubCreatedBy("");
		model.courseSubTypeListData(courseType, request);
		return "input";
	}
}

