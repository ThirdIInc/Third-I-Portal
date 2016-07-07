package org.paradyne.model.admin.srd;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.components.Bean;
import org.paradyne.bean.admin.srd.SkillDetails;
import org.paradyne.bean.leave.LeaveBalance;
import org.paradyne.lib.ModelBase;

public class SkillDetailsModel extends ModelBase {
	
	/** Method to add Skill Detail of Employee
	 * @param skillBean
	 * @param request
	 * @return boolean
	 */
	public boolean addSkillDetails(SkillDetails skillBean, HttpServletRequest request) {
		boolean isDuplicate = false;
		Object addObj[][] = new Object[1][7];
		addObj[0][0] = skillBean.getEmpId();
		addObj[0][1] = skillBean.getSkilltype();
		addObj[0][2] = skillBean.getSkillValue();
		addObj[0][3] = skillBean.getLevel();
		addObj[0][4] = skillBean.getDuration();
		addObj[0][5] = skillBean.getDurationType();
		addObj[0][6] = skillBean.getOtherSkill();
		String query = "INSERT INTO HRMS_EMP_SKILLDTL(SKILL_EMP_ID,SKILL_TYPE,SKILL,SKILL_LEVEL," 
					  +" SKILL_DURATION,SKILL_PERIODICITY,SKILL_OTHER,SKILL_ID)"
					  +" VALUES(?,?,?,?,?,?,?,(SELECT NVL(MAX(SKILL_ID),0)+1 FROM HRMS_EMP_SKILLDTL))";
		isDuplicate = getSqlModel().singleExecute(query,addObj);	
		return isDuplicate;
	}
	
	/** Method to update skill details of Employee
	 * @param skillBean
	 * @param request
	 * @return boolean
	 */
	public boolean updateSkillDetails( SkillDetails skillBean, HttpServletRequest request) {
		boolean isDuplicate = false;
		Object addObj[][] = new Object[1][8];
		addObj[0][0] = skillBean.getSkilltype();
		addObj[0][1] = skillBean.getSkillValue();
		addObj[0][2] = skillBean.getLevel();
		addObj[0][3] = skillBean.getDuration();
		addObj[0][4] = skillBean.getDurationType();
		addObj[0][5] = skillBean.getOtherSkill();
		addObj[0][6] = skillBean.getEmpId();
		addObj[0][7] = skillBean.getParacode();
		String query = "UPDATE HRMS_EMP_SKILLDTL SET SKILL_TYPE=?, SKILL=?,SKILL_LEVEL=?,SKILL_DURATION=?,"
						+" SKILL_PERIODICITY=?,SKILL_OTHER=? WHERE SKILL_EMP_ID=? AND SKILL_ID=?";
		isDuplicate = getSqlModel().singleExecute(query,addObj);	
		return isDuplicate;
	}
	
	/** Method to get skill details of Employee
	 * @param skillBean
	 * @param request
	 */
	public void getRecord(SkillDetails skillBean,HttpServletRequest request)
	{
		String query = "SELECT SKILL_TYPE,SKILL_NAME,SKILL_OTHER,SKILL_LEVEL,SKILL_DURATION,SKILL_PERIODICITY,"
			 +" HRMS_EMP_SKILLDTL.SKILL_ID FROM HRMS_EMP_SKILLDTL"
			 +" LEFT JOIN HRMS_SKILL ON(HRMS_SKILL.SKILL_ID=HRMS_EMP_SKILLDTL.SKILL) WHERE SKILL_EMP_ID = " +skillBean.getEmpId();
		Object[][] data = getSqlModel().getSingleResult(query);
		if(data!=null && data.length>0){
			ArrayList<SkillDetails> list = new ArrayList<SkillDetails>();
			for(int i=0;i<data.length;i++)
			{
				SkillDetails bean=new SkillDetails();
				bean.setSkilltype(String.valueOf(data[i][0]));
				if (String.valueOf(data[i][0]).equals("P"))
					bean.setSkilltype("Primary");
				else if (String.valueOf(data[i][0]).trim().equals("S"))
					bean.setSkilltype("Secondary");
				else if (String.valueOf(data[i][0]).trim().equals("T"))
					bean.setSkilltype("Tertiary");
				bean.setSkillName(this.checkNull(String.valueOf(data[i][1])));
				if(bean.getSkillName().length() > 25){
					bean.setAbbrSkill(bean.getSkillName().substring(0, 24)+"...");
				}else{
					bean.setAbbrSkill(bean.getSkillName());
				}
				bean.setOtherSkill(this.checkNull(String.valueOf(data[i][2])));
				if(bean.getOtherSkill().length() > 25){
					bean.setAbbrOtherSkill(bean.getOtherSkill().substring(0, 24)+"...");
				}else{
					bean.setAbbrOtherSkill(bean.getOtherSkill());
				}
				bean.setLevel(this.checkNull(String.valueOf(data[i][3])));
				bean.setDuration(this.checkNull(String.valueOf(data[i][4])));
				bean.setDurationType(String.valueOf(data[i][5]));
				if (String.valueOf(data[i][5]).equals("D"))
					bean.setDurationType("Day(s)");
				else if (String.valueOf(data[i][5]).equals("M"))
					bean.setDurationType("Month(s)");
				else if (String.valueOf(data[i][5]).equals("Y"))
					bean.setDurationType("Year(s)");
				bean.setSkillId(checkNull(String.valueOf(data[i][6])));
				list.add(bean);
				
			}
			skillBean.setList(list);
		}else{
			skillBean.setNoData("true");
			skillBean.setList(null);
		}
	}
	
	public void getEditRecord(SkillDetails skillBean,HttpServletRequest request) throws Exception{
		String query = "SELECT SKILL_TYPE,HRMS_SKILL.SKILL_NAME,SKILL_OTHER,SKILL_LEVEL,SKILL_DURATION,SKILL_PERIODICITY, HRMS_EMP_SKILLDTL.SKILL_ID,SKILL FROM HRMS_EMP_SKILLDTL LEFT JOIN HRMS_SKILL ON(HRMS_SKILL.SKILL_ID=HRMS_EMP_SKILLDTL.SKILL)"
					+" WHERE SKILL_EMP_ID = " +skillBean.getEmpId()
						+" AND HRMS_EMP_SKILLDTL.SKILL_ID="+skillBean.getParacode();
		Object[][] data = getSqlModel().getSingleResult(query);
		if(data!=null && data.length>0)
		{
				skillBean.setSkilltype(checkNull(String.valueOf(data[0][0])));
				skillBean.setSkillName(this.checkNull(String.valueOf(data[0][1])));
				skillBean.setOtherSkill(this.checkNull(String.valueOf(data[0][2])));
				skillBean.setLevel(this.checkNull(String.valueOf(data[0][3])));
				skillBean.setDuration(this.checkNull(String.valueOf(data[0][4])));
				skillBean.setDurationType(checkNull(String.valueOf(data[0][5])));
				skillBean.setSkillId(checkNull(String.valueOf(data[0][6])));
				skillBean.setSkillValue(checkNull(String.valueOf(data[0][7])));
	
		}
	}
	
	/** Method to delete skill details of employee
	 * @param skillBean
	 * @param request
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delete(SkillDetails skillBean,HttpServletRequest request) throws Exception{
		boolean result=false;
		String str;
		Object addObj[][] = new Object[1][2];
		addObj[0][0] = skillBean.getEmpId();
		addObj[0][1] = skillBean.getParacode();
		String query="DELETE FROM HRMS_EMP_SKILLDTL WHERE SKILL_EMP_ID=? AND SKILL_ID=?";
		result = getSqlModel().singleExecute(query, addObj);
		return result;
	}
	
	/** Method to check null value
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
	
	/** METHOD TO GET IMAGE OF EMPLOYEE
	 * @param skillDetail
	 */
	public void getImage(SkillDetails skillDetail) {

		try {
			String query = "select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="
					+ skillDetail.getEmpId();

			Object[][] myPics = getSqlModel().getSingleResult(query);
			if (myPics != null && myPics.length > 0) {
				skillDetail.setUploadFileName(String.valueOf(myPics[0][0]));
				skillDetail.setProfileEmpName(String.valueOf(myPics[0][1]) + " "
						+ String.valueOf(myPics[0][2]) + " "
						+ String.valueOf(myPics[0][3]));
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

}
}
