package org.paradyne.model.admin.srd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.srd.EmployeeExperience;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Prajakta.Bhandare
 *	@date 23 Jan 2013
 */
public class EmployeeExperienceModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeeExperienceModel.class);
	org.paradyne.bean.admin.srd.EmployeeExperience empExp = null;
	/**
	 * To save the record
	 * @param bean
	 * @return String
	 */
	public String addExp(EmployeeExperience bean) {
		String str="";
		String QueryCode = "SELECT NVL(MAX(EXP_ID)+1,0) FROM HRMS_EMP_EXP";
		Object dataCode[][] = getSqlModel().getSingleResult(QueryCode);
		Object addObj[][] = new Object[1][10];
		if(dataCode!=null && dataCode.length>0){//if data			
				addObj[0][0] = dataCode[0][0];//exp ID
		}//end if data
				addObj[0][1] = bean.getEmpId();//employee ID
				addObj[0][2] = bean.getEmployer();//previous employer
				addObj[0][3] = bean.getDesignation();//designation
				addObj[0][4] = bean.getJobDesc();//job description
				addObj[0][5] = bean.getJoiningDate();//joining date
				addObj[0][6] = bean.getRelDate();//relieving date
				addObj[0][7] = bean.getEmpSal();//last salary
				addObj[0][8] = bean.getCtc();//ctc in lacs
				addObj[0][9] = bean.getPfno();//pf No.
				boolean result = getSqlModel().singleExecute(getQuery(1), addObj);
				if(result){//if result true
				str="Record saved successfully";	
				}//end if result true
				else{
					str="Error while saving record";
				}//end of else
				return str;
}
	/**
	 * To update the record
	 * @param bean
	 * @return String
	 */
	public String updateExp(EmployeeExperience bean) {
		String str = "";
		try {
			Object modObj[][] = new Object[1][10];// to get the data for
													// updating record
			modObj[0][0] = bean.getEmployer();// previous employer
			modObj[0][1] = bean.getDesignation();// designation
			modObj[0][2] = bean.getJobDesc();// job description
			modObj[0][3] = bean.getJoiningDate();// joining date
			modObj[0][4] = bean.getRelDate();// relieving date
			modObj[0][5] = bean.getEmpSal();// last salary
			modObj[0][6] = bean.getCtc();// ctc in lacs
			modObj[0][7] = bean.getPfno();// pf No.
			modObj[0][8] = bean.getParaExpId();// exp ID
			modObj[0][9] = bean.getEmpId();// employee id

			boolean result = getSqlModel().singleExecute(getQuery(2), modObj);
			if(result){//if result true
				str="Record updated successfully";
			}//end if result true
			else{
				str="Error while updating record";
			}//end of else
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/** To display the record below.
	 * @param bean
	 */
	public void getExpRecord(EmployeeExperience bean) {
		
		try{
			Object[] addObj = new Object[1];
			addObj[0] = bean.getEmpId();//employee id
			Object[][] data = getSqlModel().getSingleResult(getQuery(3), addObj);
			ArrayList<EmployeeExperience> expList = new ArrayList<EmployeeExperience>();
			//ArrayList<EmployeeExperience> prjList = new ArrayList<EmployeeExperience>();
			if (data.length > 0 && data!=null) {//if data
			for (int i = 0; i < data.length; i++) { // to get the length of record in the list
				EmployeeExperience bean1 = new EmployeeExperience();
				bean1.setExpId(checkNull(String.valueOf(data[i][0])));//expId
				bean1.setEmployer(checkNull(String.valueOf(data[i][1])));//previous employer
				if(bean1.getEmployer().length() >20){//if employer text length greater
				bean1.setAbbrEmployer(bean1.getEmployer().substring(0, 19)+"...")	;
				}//end of if length greater
				else{
					bean1.setAbbrEmployer(bean1.getEmployer());
				}//end of else
				bean1.setDesignation(checkNull(String.valueOf(data[i][2])));//designation
				if(bean1.getDesignation().length() > 20){//if designation text length greater
					bean1.setAbbrDesignation(bean1.getDesignation().substring(0, 19)+"...");
				}//end of if designation text length greater
				else{
					bean1.setAbbrDesignation(bean1.getDesignation());
				}//end of else
				bean1.setJobDesc(checkNull(String.valueOf(data[i][3])));//job description
				if(bean1.getJobDesc().length() > 20){//if description text length greater
					bean1.setAbbrJobDesc(bean1.getJobDesc().substring(0, 19)+"...");
				}//end of if description text length greater
				else{
					bean1.setAbbrJobDesc(bean1.getJobDesc());
				}//end of else
				bean1.setJoiningDate(checkNull(String.valueOf(data[i][4])));//joining date
				bean1.setRelDate(checkNull(String.valueOf(data[i][5])));//relieving date
				bean1.setEmpSal(checkNull(String.valueOf(data[i][6])));//last salary
				bean1.setCtc(checkNull(String.valueOf(data[i][7])));//ctc 
				bean1.setPfno(checkNull(String.valueOf(data[i][8])));//pf no.
				String query="SELECT NVL(PROJECT_NAME,''),NVL(PROJECT_CODE,''),NVL(PROJECT_EXP_ID,'') FROM HRMS_EMP_EXP_PROJDTLS"
							+" LEFT JOIN HRMS_EMP_EXP ON(HRMS_EMP_EXP.EXP_ID=HRMS_EMP_EXP_PROJDTLS.PROJECT_EXP_ID)"
							+" WHERE PROJECT_EXP_ID="+bean1.getExpId();
				Object[][] projObj = getSqlModel().getSingleResult(query);
				ArrayList<EmployeeExperience> prjListnew = new ArrayList<EmployeeExperience>();
				if(projObj.length > 0 && projObj!=null){//if data
				for (int j = 0; j < projObj.length; j++) {//nested for loop
					EmployeeExperience projBean = new EmployeeExperience();
					projBean.setProjName(checkNull(String.valueOf(projObj[j][0])));//project name
					if(projBean.getProjName().length() >12){//if project name text length greater
						projBean.setAbbrProjName(projBean.getProjName().substring(0, 11)+"...");
					}//end of if project name text length greater
					else{
						projBean.setAbbrProjName(projBean.getProjName());
					}//end of else
					projBean.setProjCode(checkNull(String.valueOf(projObj[j][1])));//project code
					projBean.setProjExpId(checkNull(String.valueOf(projObj[j][2])));//project exp id
					prjListnew.add(projBean);
				}//end of nested for loop
				if(prjListnew.size() > 0){
					bean1.setProjNameList(prjListnew);
				}
				}//end if
			expList.add(bean1);
			}// end of for loop
			bean.setNoData("false");
			bean.setEmpExpList(expList);
			}//end if data
			else{
				bean.setNoData("true");
				bean.setEmpExpList(null);
			}
			}// end of try 
		catch(Exception e){
			e.printStackTrace();
			
		}// end of catch
	}
    
	/** Method to get single record of work experience of respective employee
	 * @param bean
	 * @throws Exception
	 */
	public void getOneExpRecord(EmployeeExperience bean) throws Exception{
		try {
			Object[] addObj = new Object[2];
			addObj[0] = bean.getEmpId();//employee id
			addObj[1] = bean.getParaExpId();//exp id
			Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);
			if (data.length > 0 && data!=null) {//if data
				bean.setExpId(checkNull(String.valueOf(data[0][0])));//exp id
				bean.setEmployer(checkNull(String.valueOf(data[0][1])));//previous employer
				bean.setDesignation(checkNull(String.valueOf(data[0][2])));//designation
				bean.setJobDesc(checkNull(String.valueOf(data[0][3])));//job description
				bean.setJoiningDate(checkNull(String.valueOf(data[0][4])));//joining date
				bean.setRelDate(checkNull(String.valueOf(data[0][5])));//relieving date
				bean.setEmpSal(checkNull(String.valueOf(data[0][6])));//last salary
				bean.setCtc(checkNull(String.valueOf(data[0][7])));// ctc in lacs
				bean.setPfno(checkNull(String.valueOf(data[0][8])));// pf no.
				
			}//end if data
			else{
				bean.setExpId("");//exp id
				bean.setEmployer("");//previous employer
				bean.setDesignation("");//designation
				bean.setJobDesc("");//job description
				bean.setJoiningDate("");//joining date
				bean.setRelDate("");//relieving date
				bean.setEmpSal("");//last salary
				bean.setCtc("");// ctc in lacs
				bean.setPfno("");// pf no.
			}//end of else
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	/** Method to get single project record for edit
	 * @param bean
	 * @throws Exception
	 */
	public void getOneProjRecord(EmployeeExperience bean) throws Exception{
		try {
			Object[] addObj = new Object[2];
			addObj[0] = bean.getParaProjId();//project code
			addObj[1] = bean.getParaExpId();//project exp id	
			Object[][] data = getSqlModel().getSingleResult(getQuery(10), addObj);
			if(data!=null && data.length > 0){//if data
				bean.setProjectName(checkNull(String.valueOf(data[0][0])));//project name
				bean.setProjectDesc(checkNull(String.valueOf(data[0][1])));//project description
				bean.setProjectDuration(checkNull(String.valueOf(data[0][2])));//project duration
				bean.setTeamSize(checkNull(String.valueOf(data[0][3])));//team size
				bean.setProjectRole(checkNull(String.valueOf(data[0][4])));//role
				bean.setProjectItt(checkNull(String.valueOf(data[0][5])));//project code
				bean.setProjectExpId(checkNull(String.valueOf(data[0][6])));//project exp id
			}//end if data
			else{
				bean.setProjectName("");//project name
				bean.setProjectDesc("");//project description
				bean.setProjectDuration("");//project duration
				bean.setTeamSize("");//team size
				bean.setProjectRole("");//role
				bean.setProjectItt("");//project code
				bean.setProjectExpId("");//project exp id
			}//end of else
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	/**
	 * To delete the experience record
	 * @param bean
	 * @return boolean
	 */
	public boolean delExpRecord(EmployeeExperience bean) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getParaExpId();
		boolean result = getSqlModel().singleExecute(getQuery(9), addObj);
		if (result) {//if result
			// delete the corresponding projects also...
			return getSqlModel().singleExecute(getQuery(11), addObj);
		}// end of if result
		else {
			return false;
		}// end of else

	}

	/**
	 * To delete the project record
	 * @param bean
	 * @return boolean
	 */
	public boolean delProjRecord(EmployeeExperience bean) {
		Object addObj[][] = new Object[1][2];
		addObj[0][0] = bean.getParaExpId();
		addObj[0][1] = bean.getParaProjId();
		boolean result = getSqlModel().singleExecute(getQuery(8), addObj);
		if (result) {//if result
			return true;
		}// end of if result
		else {
			return false;
		}// end of else
	}
	/**
	 * To remove null
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {//if result null
			return "";
		}// end of if result null
		else {
			return result;
		}// end of else
	}
	
	/**
	 * To display the project record 
	 * @param bean
	 * @param request
	 */
	public void getProjectRecords(EmployeeExperience bean,HttpServletRequest request) {
		try {
			Object obj[]= new Object[1];
			obj[0] = bean.getParaExpId();//project exp id
			Object[][] projectObj = getSqlModel().getSingleResult(getQuery(5), obj);
			if(projectObj!=null && projectObj.length > 0){//if data
				ArrayList<EmployeeExperience> prjList = new ArrayList<EmployeeExperience>();
				for (int i = 0; i < projectObj.length; i++) {//start i for loop
					EmployeeExperience bean1 = new EmployeeExperience();
					bean1.setProjectNameItt(checkNull(String.valueOf(projectObj[i][0])));//project name
					if(bean1.getProjectNameItt().length() >15){//if project name text length greater
						bean1.setAbbrProjectName(bean1.getProjectNameItt().substring(0, 14)+"...")	;
						}//end of if project name length greater
						else{
							bean1.setAbbrProjectName(bean1.getProjectNameItt());
						}//end of else
					bean1.setProjectDescItt(checkNull(String.valueOf(projectObj[i][1])));//project description
					if(bean1.getProjectDescItt().length() >15){//if project description text length greater
						bean1.setAbbrProjectDesc(bean1.getProjectDescItt().substring(0, 14)+"...")	;
						}//end of if project description length greater
						else{
							bean1.setAbbrProjectDesc(bean1.getProjectDescItt());
						}//end of else
					bean1.setProjectDurationItt(checkNull(String.valueOf(projectObj[i][2])));//project duration
					bean1.setTeamSizeItt(checkNull(String.valueOf(projectObj[i][3])));//team size
					bean1.setProjectRoleItt(checkNull(String.valueOf(projectObj[i][4])));//role
					if(bean1.getProjectRoleItt().length() >15){//if project role text length greater
						bean1.setAbbrProjectRole(bean1.getProjectRoleItt().substring(0, 14)+"...")	;
						}//end of if project role length greater
						else{
							bean1.setAbbrProjectRole(bean1.getProjectRoleItt());
						}//end of else
					bean1.setProjectItt(checkNull(String.valueOf(projectObj[i][5])));//project code
					bean1.setProjectExpId(checkNull(String.valueOf(projectObj[i][6])));//project exp id
					prjList.add(bean1);
				}// end i for loop
				bean.setNoProjData("false");
				bean.setProjectList(prjList);
			}// end if data
			else{
				bean.setNoProjData("true");
				bean.setProjectList(null);
			}//end of else
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	/** Method to add project record
	 * @param bean
	 * @return String
	 */
	public String addProject(EmployeeExperience bean){
	String str="";	
	Object addObj[][]= new Object[1][6];
	addObj[0][0]= bean.getParaExpId();//project exp ID
	addObj[0][1]= bean.getProjectName();//project name
	addObj[0][2]= bean.getProjectDuration();//project duration
	addObj[0][3]= bean.getTeamSize();//team size
	addObj[0][4]=bean.getProjectRole();//project role
	addObj[0][5]=bean.getProjectDesc();//project description 
	boolean result = getSqlModel().singleExecute(getQuery(6), addObj);
	if(result){//if result true
	str="Record saved successfully";	
	}//end if result true
	else{
		str="Error while saving record";
	}//end of else
	
	return str;
	}
	
	/** Method toupdate project record
	 * @param bean
	 * @return String
	 */
	public String updateProject(EmployeeExperience bean){
		String str="";	
		Object addObj[][]= new Object[1][7];
		addObj[0][0]= bean.getProjectName();//project name
		addObj[0][1]= bean.getProjectDuration();//project duration
		addObj[0][2]= bean.getTeamSize();//team size
		addObj[0][3]=bean.getProjectRole();//project role
		addObj[0][4]=bean.getProjectDesc();//project description 
		addObj[0][5]=bean.getParaExpId();//project exp ID
		addObj[0][6]=bean.getParaProjId();//project code
		boolean result = getSqlModel().singleExecute(getQuery(7), addObj);
		if(result){//if result true
		str="Record updated successfully";	
		}//end if result true
		else{
			str="Error while updating record";
		}//end of else	
		return str;
	}
	/**
 *  to generate the report for  employee details
 * @param bean
 * @param request
 * @param response
 */
	public void generateReport(EmployeeExperience bean,HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		 String s = "Employee Experience Details\n\n";
		 ReportGenerator rg = new ReportGenerator("Pdf", s);
		 
		 String emplQuery="SELECT EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME,EMP_ID, "
							+ " NVL(TITLE_NAME,' ')||' ' ||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ' )|| ' ' || NVL(HRMS_EMP_OFFC.EMP_MNAME,' ') || ' ' || NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')	FROM HRMS_EMP_OFFC "
							+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
							+ " INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
							+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					 		+" WHERE  EMP_ID="+bean.getEmpId();
		   
		  Object[][] hearderData= getSqlModel().getSingleResult(emplQuery);
		  logger.info(" Employee  Details----------------"+hearderData.length);
				 	Object headers[][] = new Object[2][5];
					headers[0][0] = "Employee Token";
					headers[0][1] = ": " + String.valueOf(hearderData[0][0]);
					headers[0][2] = "Employee Name";
					headers[0][3] = ": " + String.valueOf(hearderData[0][1]);
					headers[1][0] = "Designation";
					headers[1][1] = ": " + String.valueOf(hearderData[0][3]);
					headers[1][2] = "Branch";
					headers[1][3] = ": " +String.valueOf(hearderData[0][2]);
					int cellwidth[]={20, 30, 20, 30};
					int alignment[]={0, 0, 0, 0};
					rg.addFormatedText(s, 6, 0, 1, 0);
					String date="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
					Object [][]today=getSqlModel().getSingleResult(date);
					rg.addFormatedText("", 1, 0, 2, 3);
					rg.addText("Date: "+today[0][0], 0, 2, 0);
					rg.addFormatedText("", 1, 0, 2, 3);
					rg.addFormatedText("", 1, 0, 2, 3);
				    rg.tableBodyNoBorder(headers, cellwidth, alignment);
				    
		    String SQL=" SELECT NVL(EXP_EMPLOYER,' '),NVL(EXP_JOBTITLE,' '),NVL(TO_CHAR(EXP_JOINING_DATE,'DD-MM-YYYY'),' '),NVL(TO_CHAR(EXP_RELIEVING_DATE,'DD-MM-YYYY'),' '),NVL(EXP_SCALE_OF_PAY,' '),NVL(EXP_JOBDESC,' '),EXP_ID  FROM HRMS_EMP_EXP "
						+"	WHERE HRMS_EMP_EXP.EMP_ID =" +bean.getEmpId()
						+" ORDER by EXP_JOINING_DATE desc";
		
		    Object data[][]=getSqlModel().getSingleResult(SQL);
		    try{
				
		    if(data.length>0){
		    	
		     for (int i = 0; i < data.length; i++) {
						
				    Object headers2[][] = new Object[3][4];
				    headers2[0][0] = "Employer ";
				    headers2[0][1] = " " + String.valueOf(data[i][0]);
				    headers2[0][2] = "Designation";
				    headers2[0][3] = " " + String.valueOf(data[i][1]);
				    headers2[1][0] = "Joining Date ";
				    headers2[1][1] = " " + String.valueOf(data[i][2]);
				    headers2[1][2] = "Reliving Date";
				    headers2[1][3] = " " +String.valueOf(data[i][3]);
				    headers2[2][0] = "CTC ";
				    headers2[2][1] = " " + String.valueOf(data[i][4]);
				    headers2[2][2] = "Job Description";
				    headers2[2][3] = " " +String.valueOf(data[i][5]);
					int cellwidth1[]={20, 30, 20, 30};
					int alignment1[]={0, 0, 0, 0};
					rg.addFormatedText("", 0, 0, 1, 0);
					rg.addFormatedText("", 0, 0, 1, 0);
					rg.addFormatedText("", 0, 0, 1, 0);
					rg.addFormatedText("", 0, 0, 1, 0);
					rg.addFormatedText("", 0, 0, 1, 0);
					rg.addFormatedText("", 0, 0, 1, 0);
					rg.addText("Experience Particulars\n\n", 6, 1, 0);
					rg.tableBody(headers2, cellwidth1, alignment1);
					
		 
		    String PROJSQL="SELECT NVL(PROJECT_NAME,''),  NVL(PROJECT_DURATION,''),NVL(PROJECT_TEAMSIZE,''),NVL(PROJECT_ROLE,''),NVL(PROJECT_DESCRIPTION,''), NVL(PROJECT_CODE,''),NVL(PROJECT_EXP_ID,'') "
						   + "  FROM HRMS_EMP_EXP_PROJDTLS " 
						   + " INNER JOIN HRMS_EMP_EXP ON(HRMS_EMP_EXP.EXP_ID=HRMS_EMP_EXP_PROJDTLS.PROJECT_EXP_ID)	 WHERE HRMS_EMP_EXP.EMP_ID ="+bean.getEmpId()+" AND PROJECT_EXP_ID="+Integer.parseInt(String.valueOf(data[i][6]))+"";
		    String colNames1[]={"Project Name","Project Duration(In Months)","Project Team Size","Role In Project","Project Description"};
		   
					int []cellwidth2={20,15,15,20,25};
					int []alignmnet2={0,2,2,0,0};
				
					Object data1[][]=getSqlModel().getSingleResult(PROJSQL);
					logger.info(" Project Experience Details for---------------"+data1.length);
					rg.addText("Project Experience Details for "+data[i][0], 0, 1, 0);
					rg.tableBody(colNames1, data1, cellwidth2, alignmnet2);
						
				  }
			}
		}
	catch(Exception e){
			e.printStackTrace();
		    }
		 rg.createReport(response);
	}

/**Method to get profile name and profile image
 * @param empExp2
 */
public void getImage(EmployeeExperience empExp2) {
		
		try {
			String query="select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="+empExp2.getEmpId();
			
			Object[][] myPics = getSqlModel().getSingleResult(query);
				
			empExp2.setUploadFileName( String.valueOf(myPics[0][0]));	
			empExp2.setProfileEmpName( String.valueOf(myPics[0][1])+" "+String.valueOf(myPics[0][2])+" "+String.valueOf(myPics[0][3]));
			
			System.out.println("persDetail.setUploadFileName( String.valueOf(myPics[0][0]))----"+empExp2.getUploadFileName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
}
}
