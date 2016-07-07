package org.paradyne.model.admin.master;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.master.SkillMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Pradeep Sahoo
 * @modified By saipavan
 *
 */
public class SkillMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	/**
	 * @param skillMaster
	 * @return boolean after inserting new skill
	 */
	public String saveData(SkillMaster skillMaster) {
		
		Object [][] data=new Object[1][4];
		String flag = "";
		boolean result = false;
		try{
		data[0][0]=skillMaster.getSkills();
		data[0][1]=skillMaster.getSkillsAbbr();
		data[0][2]=skillMaster.getDesciption();
		data[0][3]=skillMaster.getStatus();
		if(!checkDuplicate(skillMaster)){
		
		String query="INSERT INTO HRMS_SKILL(SKILL_ID,SKILL_NAME,SKILL_ABBR,SKILL_DESC,SKILL_STATUS) VALUES((SELECT NVL(MAX(SKILL_ID),0)+1 FROM HRMS_SKILL),?,?,?,?) ";
		
		result=getSqlModel().singleExecute(query, data);
		
		if(result){
			String code="SELECT MAX(SKILL_ID) FROM HRMS_SKILL ";
			Object [][] value=getSqlModel().getSingleResult(code);
			
			String query1 = "SELECT SKILL_NAME,SKILL_ABBR,SKILL_ID,SKILL_DESC,CASE WHEN SKILL_STATUS='A' THEN 'Active' ELSE 'Deactive' END FROM HRMS_SKILL where SKILL_ID ="+String.valueOf(value[0][0]) ;
			
			Object [][]data1=getSqlModel().getSingleResult(query1);
	      //setter
			skillMaster.setSkills(String.valueOf(data1[0][0]).trim());
			skillMaster.setSkillsAbbr(String.valueOf(data1[0][1]).trim());
			skillMaster.setSkillCode(String.valueOf(data1[0][2]).trim());
			skillMaster.setDesciption(checkNull(String.valueOf(data1[0][3]).trim()));
			skillMaster.setStatus(String.valueOf(data1[0][4]));
			flag = "saved";
		}
		else{
			flag = "error";
		}
	}
	else
	{
		String query = "SELECT DECODE(SKILL_STATUS,'A','Active','D','Deactive') " 
            +" FROM HRMS_SKILL where SKILL_STATUS='"+skillMaster.getStatus()+"'"; 
		Object[][] value = getSqlModel().getSingleResult(query);
		flag = "duplicate";
	}
	}catch (Exception e) {
		logger.error("Exception was rised-->");
	}
	return flag;
 }
	/**
	 * @param skillMaster
	 * @return boolean after modifying the skill
	 */
	public String updateData(SkillMaster skillMaster) {
		
		Object [][] data=new Object[1][5];
		String editFlag = "";
		boolean result = false;
		try{
		data[0][0]=skillMaster.getSkills();
		data[0][1]=skillMaster.getSkillsAbbr();
		data[0][2]=skillMaster.getDesciption();
		data[0][3]=skillMaster.getStatus();
		data[0][4]=skillMaster.getSkillCode();
		if(!checkDuplicateMod(skillMaster)){
			String query="UPDATE HRMS_SKILL SET SKILL_NAME=?,SKILL_ABBR=? ,SKILL_DESC=?,SKILL_STATUS=? where SKILL_ID=?";
			result=getSqlModel().singleExecute(query,data);
			if(result)
			{
				String query1 = "SELECT SKILL_NAME,SKILL_ABBR,SKILL_ID,SKILL_DESC,DECODE(SKILL_STATUS,'A','Active','D','Deactive') FROM HRMS_SKILL where SKILL_ID ="+skillMaster.getSkillCode() ;
				
				Object [][]data1=getSqlModel().getSingleResult(query1);
		    	skillMaster.setSkills(String.valueOf(data1[0][0]).trim());
				skillMaster.setSkillsAbbr(String.valueOf(data1[0][1]).trim());
				skillMaster.setSkillCode(String.valueOf(data1[0][2]).trim());
				skillMaster.setDesciption(checkNull(String.valueOf(data1[0][3]).trim()));
				skillMaster.setStatus(String.valueOf(data1[0][4]));
				editFlag="modified";
			}
			else
			{
				editFlag="error";
			}
		}
		else
		{
			editFlag = "duplicate";
		}
		}catch (Exception e) {
			logger.error("Exception was rised--->");
		}
		return editFlag;
}
		

	/**
	 * @param skillMaster
	 * @return boolean after deleting the skill
	 */
	
	public boolean deleteData(SkillMaster skillMaster) {
		
		/*Object [][] data=new Object[1][1];
		data[0][0]=skillMaster.getHskillCode();*/
		String query="DELETE FROM HRMS_SKILL WHERE SKILL_ID="+skillMaster.getSkillCode();
	    try {
	    	logger.info("query....!!!!"+query);
			return getSqlModel().singleExecute(query);
		} catch (Exception e) {
			return false;
		}
	}

	/*public void recorditterate(SkillMaster skillMaster) {
	
		ArrayList<Object> list=new ArrayList<Object>();
		String query="SELECT SKILL_NAME,SKILL_ABBR,SKILL_ID FROM HRMS_SKILL ORDER BY SKILL_ID ";
		Object [][]data=getSqlModel().getSingleResult(query);
		for (int i = 0; i < data.length; i++) {
			SkillMaster model1=new SkillMaster();
			model1.setSkillItt(String.valueOf(data[i][0]).trim());
			model1.setSkillAbbrItt(String.valueOf(data[i][1]).trim());
			model1.setSkillsCode(String.valueOf(data[i][2]).trim());
			list.add(model1);
		}
		skillMaster.setList(list);
	}*/
	
	
	
	
	/**following fucntion is called to display all the records 
	 * @param bean
	 * @param request http request
	 */
	public void  Data(SkillMaster  bean, HttpServletRequest request) {
		String query="SELECT SKILL_NAME,SKILL_ABBR,SKILL_ID,decode(SKILL_STATUS,'A','Active','D','Deactive') FROM HRMS_SKILL ORDER BY SKILL_ID ";
		Object [][] repData = getSqlModel().getSingleResult(query);
		ArrayList<Object> obj=new ArrayList<Object>();
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),repData.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
      if(pageIndex[4].equals("1"))
			bean.setMyPage("1");
      
     if(repData!=null && repData.length>0){ 
	  for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
                 //setting 
		  SkillMaster  bean1 = new SkillMaster ();
			
		  bean1.setSkillItt(String.valueOf(repData[i][0]).trim());
		  bean1.setSkillAbbrItt(String.valueOf(repData[i][1]).trim());
		  bean1.setSkillsCode(String.valueOf(repData[i][2]).trim());
		  bean1.setStatus(String.valueOf(repData[i][3]).trim());
          obj.add(bean1);
	  }
	  bean.setIteratorlist(obj);
	  bean.setTotRecord(String.valueOf(Integer.parseInt(String.valueOf(repData.length))));
     }  
	}
/**
 * @param bean SkillMaster bean
 * method for setting  the selected record details
 */
public void calforedit(SkillMaster bean) {
	try{	
	String query = "SELECT SKILL_NAME,SKILL_ABBR,SKILL_ID,SKILL_DESC,CASE WHEN SKILL_STATUS='A' then 'Active' else 'Deactive' END  FROM HRMS_SKILL where SKILL_ID ="+bean.getHiddencode() ;
	Object [][]data=getSqlModel().getSingleResult(query);
    //setter
    bean.setSkills(String.valueOf(data[0][0]).trim());
    bean.setSkillsAbbr(String.valueOf(data[0][1]).trim());
    bean.setSkillCode(String.valueOf(data[0][2]).trim());
    bean.setDesciption(checkNull(String.valueOf(data[0][3]).trim()));
    bean.setStatus(String.valueOf(data[0][4]));
	}catch (Exception e) {
		logger.error("Exception was rised-->");
	}
}

	/**
	 * @param bean SkillMaster bean
	 * @param code set of checked Records
	 * @return boolean after deleting all the checked records.
	 */
	public boolean deletecheckedRecords(SkillMaster bean, String[] code) {
		boolean result=false;
		int count=0;
		if(code !=null)
		{
			for (int i = 0; i < code.length; i++) {
				
				if(!code[i].equals("")){
					logger.info("value of code is"+code[i]);
					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					
					String query="DELETE FROM HRMS_SKILL WHERE SKILL_ID=? ";
					result=getSqlModel().singleExecute(query,delete);

					if(!result)
					count++;
					
					
				}
			}
		}

		if(count!=0)
				{	result=false;
					return result;
					}
				else
					{result=true;
					return true; }
	}
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	/**
	 * following function is called when a record is selected from the search window.
	 * @param bean
	 */
	
	public void details(SkillMaster bean) {
		try{
		String query = "SELECT SKILL_NAME,SKILL_ABBR,SKILL_ID,SKILL_DESC,SKILL_STATUS FROM HRMS_SKILL where SKILL_ID ="+bean.getSkillCode() ;
	
		Object [][]data=getSqlModel().getSingleResult(query);
      //setter
		  bean.setSkills(String.valueOf(data[0][0]).trim());
		  bean.setSkillsAbbr(String.valueOf(data[0][1]).trim());
		  bean.setSkillCode(String.valueOf(data[0][2]).trim());
		  bean.setDesciption(checkNull(String.valueOf(data[0][3]).trim()));
		  bean.setStatus(String.valueOf(data[0][4]));
		}catch (Exception e) {
		  logger.error("Exception was rised---->");		
		}	
		
	}
	public void details1(SkillMaster bean) {
		try{
		String query = "SELECT SKILL_NAME,SKILL_ABBR,SKILL_DESC,DECODE(SKILL_STATUS,'A','Active','D','Deactive'),SKILL_ID FROM HRMS_SKILL where SKILL_ID ="+bean.getSkillCode() ;
		Object [][]data=getSqlModel().getSingleResult(query);
      //setter
		  bean.setSkills(String.valueOf(data[0][0]).trim());
		  bean.setSkillsAbbr(String.valueOf(data[0][1]).trim());
		  bean.setDesciption(checkNull(String.valueOf(data[0][2]).trim()));
		  bean.setStatus(String.valueOf(data[0][3]));
		  bean.setSkillCode(String.valueOf(data[0][4]).trim());
		}catch (Exception e) {
		  logger.error("Exception was rised---->");
		}
	}
	/**
	 * for checking duplicate entry of records during insertion. 
	 * @param bean
	 * @return boolean
	 */
	public boolean checkDuplicate(SkillMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_SKILL WHERE UPPER(SKILL_NAME) LIKE '"
				+ bean.getSkills().trim().toUpperCase()+"'";
				
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/**
	 * for checking duplicate entry of records during modification
	 * @param bean
	 * @return boolean
	 */
	public boolean checkDuplicateMod(SkillMaster bean) {
		boolean result = false;
		Object[][] data = null;
		String skName = bean.getSkills().trim().toUpperCase();
		Object[] value= new Object[1];
		try {
			value[0] = bean.getSkills().trim().toUpperCase();
		} catch (Exception e) {
			logger.error("exception in object value in duplicate check method",e);
		}
		try {
			String query = "SELECT * FROM HRMS_SKILL WHERE UPPER(SKILL_NAME) =?" +
					   " AND SKILL_ID not in(" + bean.getSkillCode() + ")";
			data = getSqlModel().getSingleResult(query,value);
		}catch (Exception e) {
			logger.error("exception in duplicate query",e);
		}
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}
	/**
	 * Following function is called to get the report of the Pdf format for list of Skill Records
	 * @param skillMaster
	 * @param response
	 * @param label
	 */
		
	 public void generateReport(SkillMaster skillMaster, HttpServletResponse response,String[]label) {
			// TODO Auto-generated method stub
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			String reportName="Skill Master";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf", reportName);
			rg.setFName("Skill Master.Pdf");
			String queryDes=" SELECT SKILL_NAME,SKILL_ABBR,DECODE(SKILL_STATUS,'A','Active','D','Deactive') " +
					        " FROM HRMS_SKILL ORDER BY SKILL_ID ";
			Object [][]data=getSqlModel().getSingleResult(queryDes);
			Object [][] Data=new Object[data.length][4];
			if (data != null && data.length > 0) {
				int j=1;
				for(int i=0;i<data.length;i++){
					Data[i][0]=j;
					Data[i][1]=data[i][0];
					Data[i][2]=data[i][1];
					Data[i][3]=data[i][2];
					j++;
				}
				int cellwidth[] = {5, 20,20,20 };
				int alignment[] = { 1, 0 , 0, 0};
				rg.addTextBold("Skill Master", 0, 1, 0);
				rg.addText("\n",0,0,0);
				rg.addTextBold("Date :"+toDay, 0, 2, 0);
				rg.addText("\n",0,0,0);
				rg.tableBody(label, Data, cellwidth, alignment);
			} else {
				rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
			}

			rg.createReport(response);
			
		}

}
