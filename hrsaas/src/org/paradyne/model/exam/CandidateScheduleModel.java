/**
 * 
 */
package org.paradyne.model.exam;

import java.util.Random;

import org.paradyne.bean.exam.CandidateSchedule;
import org.paradyne.bean.exam.ExamMaster;
import org.paradyne.lib.ModelBase;

/**
 * @author diptip
 *
 */
public class CandidateScheduleModel extends ModelBase {

	/**
	 * 
	 */
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ModelBase.class);
	CandidateSchedule candidateSchedule;
	
	public CandidateScheduleModel() {
		// TODO Auto-generated constructor stub
	}
	public String getRandomPassword(int length) {// Random generation of
		// password
		StringBuffer password = new StringBuffer();
		Random random = new Random();
		char[] chars = new char[] { 'a', 'b', 'c', 'd', '1', '2', '3', '4',
				'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
				'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '5', '6',
				'7', '8', '9', '0' };
		for (int i = 0; i < length; i++) {
			password.append(chars[random.nextInt(chars.length)]);
		}
		return password.toString();
	}
	
	public String add(CandidateSchedule bean) {
		try {
			Object addObj[][] = new Object[1][6];
			addObj[0][0] = bean.getCandidateCode();
			addObj[0][1] = bean.getScheduleDate();
			addObj[0][2] = bean.getTime();
			addObj[0][3] = bean.getPaperCode();
			if(!(String.valueOf(bean.getCandidateCode())==null)){
				
			String query="SELECT CANDIDATE_HDR_FNAME || '.' || CANDIDATE_HDR_LNAME FROM HRMS_CANDIDATE_HDR WHERE CANDIDATE_HDR_CODE="+bean.getCandidateCode(); 
			 Object [][]data=getSqlModel().getSingleResult(query);
			 addObj[0][4]=String.valueOf(data[0][0]);
			 addObj[0][5]=getRandomPassword(8);
			}
			
			logger.info("--------------"+bean.getPaperName());
			
			boolean result = getSqlModel().singleExecute(getQuery(1), addObj);
			
			if (result) {
				return "Record saved Successfully!";
			} else {
				return "Duplicate entry Found! ";

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Duplicate entry Found! ";
		}

	}
	
	public String mod(CandidateSchedule bean) {
		try {
			Object addObj[][] = new Object[1][5];
			addObj[0][0] = bean.getCandidateCode();
			addObj[0][1] = bean.getScheduleDate();
			addObj[0][2] = bean.getTime();
			addObj[0][3] = bean.getPaperCode();
			addObj[0][4] = bean.getScheduleCode();
			boolean result = getSqlModel().singleExecute(getQuery(2), addObj);
			if (result) {
				return "Record modified Successfully!";
			} else {
				return "Duplicate entry Found! ";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "Duplicate entry Found! ";
		}

	}	
	
	public boolean delete(CandidateSchedule bean){
		Object obj[][] = new Object[1][1];
		obj[0][0]=bean.getScheduleCode();
		logger.info("code"+bean.getScheduleCode());
		boolean result=getSqlModel().singleExecute(getQuery(3),obj);
		return result;
	}
	
	
}
