package org.paradyne.model.recruitment;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Recruitment.SubjectiveMarks;
import org.paradyne.lib.ModelBase;

public class SubjectiveMarksModel extends ModelBase {

public void getPendingList(SubjectiveMarks subMarks) {
		// TODO Auto-generated method stub
	

		String query = " SELECT DISTINCT CANDIDATE_CODE,CANDIDATE_HDR_FNAME,CANDIDATE_HDR_LNAME ,PAPER_CODE,PAPER_NAME "
				+ " FROM HRMS_CANDIDATE_HDR "
				+ " INNER JOIN HRMS_ONLINETEST_HDR ON(HRMS_CANDIDATE_HDR.CANDIDATE_HDR_CODE=HRMS_ONLINETEST_HDR.CANDIDATE_CODE ) "
				+ " INNER JOIN HRMS_ONLINETEST_DTL ON(HRMS_ONLINETEST_HDR.TEST_CODE=HRMS_ONLINETEST_DTL.TEST_CODE ) "
				+"INNER JOIN HRMS_PAPER_HDR ON(HRMS_PAPER_HDR.PAPER_CODE=HRMS_ONLINETEST_HDR.TEST_PAPER_CODE)"
				+ " WHERE TEST_QUES_TYPE='S'  ";

		if (!subMarks.getToDate().equals("")) {
			query += " AND HRMS_ONLINETEST_HDR.TEST_DATE <=TO_DATE('"
					+ subMarks.getToDate() + "','dd-mm-yyyy')  ";
		}
		if (!subMarks.getFromDate().equals("")) {
			query += " AND HRMS_ONLINETEST_HDR.TEST_DATE >=TO_DATE('"
					+ subMarks.getFromDate() + "','dd-mm-yyyy') ";
		}
		if (!subMarks.getPaperCode().equals("")) {
			query += " AND TEST_PAPER_CODE="+subMarks.getPaperCode();
		}
		
		Object[][] candObj = getSqlModel().getSingleResult(query);
		
		ArrayList<Object> list=new ArrayList<Object>();
		for (int i = 0; i < candObj.length; i++) {
			ArrayList<Object> listOp=new ArrayList<Object>();
			SubjectiveMarks newsubMarks=new SubjectiveMarks();
			newsubMarks.setCandidateName(String.valueOf(candObj[i][1]));
			
			String quesQuery="SELECT TEST_CODE, TEST_SUBJECT_CODE, TEST_QUES_CODE,QUES_DESC, QUES_MARK,PAPER_CODE "
				  +"  TEST_CANDIDATE_ANSWER,TEST_QUES_OPTION_CODE "
				  +" FROM HRMS_ONLINETEST_DTL "
				  +" INNER JOIN HRMS_QUESTION_BANK ON(HRMS_QUESTION_BANK.QUES_CODE=HRMS_ONLINETEST_DTL.TEST_QUES_CODE ) "
				  +" INNER JOIN HRMS_ONLINETEST_HDR ON(HRMS_ONLINETEST_HDR.TEST_CODE=HRMS_ONLINETEST_DTL.TEST_CODE ) "
				  +" INNER JOIN HRMS_CANDIDATE_HDR ON(HRMS_CANDIDATE_HDR.CANDIDATE_HDR_CODE=HRMS_ONLINETEST_HDR.CANDIDATE_CODE ) "
				  +" INNER JOIN HRMS_PAPER_HDR ON(HRMS_PAPER_HDR.PAPER_CODE=HRMS_ONLINETEST_HDR.TEST_PAPER_CODE)"
				  +" WHERE  TEST_QUES_TYPE='S' AND CANDIDATE_CODE="+String.valueOf(candObj[i][0]);
			Object[][] quesObj = getSqlModel().getSingleResult(quesQuery);
		
			if(quesObj!=null)
			{
				for (int j = 0; j < quesObj.length; j++) {
					SubjectiveMarks newsubM=new SubjectiveMarks();
					newsubM.setTestCode(String.valueOf(quesObj[j][0]));
					newsubM.setSubjectQuestCode(String.valueOf(quesObj[j][1]));
					newsubM.setQuesCode(String.valueOf(quesObj[j][2]));
					newsubM.setQuesDescItt(String.valueOf(quesObj[j][3]));
					newsubM.setMaxMarks(String.valueOf(quesObj[j][4]));
					newsubM.setSubjOpt(String.valueOf(quesObj[j][6]));
					listOp.add(newsubM);
				}
			}
			newsubMarks.setShowListOption(listOp);
			list.add(newsubMarks);
		}
		subMarks.setShowList(list);
		
		
	}


public void save(SubjectiveMarks subMarks, String[] testCode, String[] subjectQuestCode,String[] quesCode, String[] maxMarks,String[] marks) 
{
	
	for (int i = 0; i < quesCode.length; i++) {
		System.out.println("============="+marks[i]);
		String quer="update HRMS_ONLINETEST_DTL set TEST_CANDIDATE_ANSWER="+marks[i]+" where TEST_CODE="+testCode[i]+" and TEST_SUBJECT_CODE="+subjectQuestCode[i]+" and TEST_QUES_CODE="+quesCode[i]+"";
		getSqlModel().singleExecute(quer);
	}


}


public void update(SubjectiveMarks subMarks, String[] testCode,	String[] subjectQuestCode, String[] quesCode, String[] maxMarks,String[] marks)
{
	// TODO Auto-generated method stub
	
	
	
	
}

	
	}
 

	  
	  
	  
	  
	 