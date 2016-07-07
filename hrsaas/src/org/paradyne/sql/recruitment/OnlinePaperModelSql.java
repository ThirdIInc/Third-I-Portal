package org.paradyne.sql.recruitment;
import org.paradyne.lib.SqlBase;
public class OnlinePaperModelSql extends SqlBase {

	public String getQuery(int id) 
	{
		switch(id)
		{
		   case 1: return "INSERT INTO HRMS_PAPER_HDR(PAPER_CODE,PAPER_NAME,PAPER_TIME,PAPER_PASS_CRIETERIA,TOTAL_NO_OF_QUES,NO_QUESTION_PER_PAGE) VALUES((SELECT NVL(MAX(PAPER_CODE),0)+1 FROM HRMS_PAPER_HDR),?,?,?,?,?,?) ";			
			
		   /*case 1: return " INSERT INTO HRMS_PAPER_HDR(PAPER_CODE,PAPER_NAME) " 
				           +"VALUES((SELECT NVL(MAX(PAPER_CODE),0)+1 FROM RMS_PAPER_HDR ),?)";
			*/
			case 2: return " UPDATE HRMS_PAPER_HDR SET PAPER_NAME=? WHERE PAPER_CODE=?";
			
			case 3: return " DELETE FROM HRMS_PAPER_HDR WHERE PAPER_CODE=?";
			
			//case 4: return ""INSERT INTO HRMS_PAPER_DTl(SUBJECT_CODE,NO_QUESTION,PAPER_TIME,PAPER_PASS_CRIETERIA,TOTAL_NO_OF_QUES) VALUES((SELECT NVL(MAX(PAPER_CODE),0)+1 FROM HRMS_PAPER_HDR),?,?,?,?,?) ";
			case 4: return " DELETE FROM HRMS_PAPER_DTL WHERE PAPER_CODE=?";
			case 5: return "delete FROM HRMS_PAPER_DTL where PAPER_CODE=?";
						
			default : return "Framework could not the query number specified";
			
		}
	}
}

