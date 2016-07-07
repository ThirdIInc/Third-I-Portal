package org.paradyne.sql.PAS;

import org.paradyne.lib.SqlBase;

public class AppraisalScoreBalancerReportModelSql extends SqlBase
{
  public String getQuery(int id)
  {
    switch (id) {
    case 1:
      return 
      " SELECT   ROWNUM, EMP_TOKEN,   EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMPLOYEE_NAME,   DIV_NAME,   DEPT_NAME,   CENTER_NAME, " 
      +"  A.EMP_ID AS APPRAISER_NAME,   APPR_SCORE,   APPR_ADJUST_FACTOR AS OPERAND,   APPR_SCORE_ADJUST,   APPR_FINAL_SCORE  FROM   HRMS_EMP_OFFC A" 
      +" INNER JOIN PAS_APPR_SCORE B ON A.EMP_ID = B.EMP_ID   LEFT JOIN HRMS_DIVISION C ON(C.DIV_ID = A.EMP_DIV)   LEFT JOIN HRMS_DEPT D ON(D.DEPT_ID = A.EMP_DEPT)" 
      +" LEFT JOIN HRMS_CENTER E ON(E.CENTER_ID = A.EMP_CENTER)  WHERE    APPR_ID = ? ";
    case 2:
      return 
      " SELECT  DISTINCT (C.EMP_FNAME||' '|| C.EMP_LNAME),APPR_APPRAISER_LEVEL  FROM HRMS_EMP_OFFC C, PAS_APPR_APPRAISER D " 
      +" WHERE C.EMP_ID = D.APPR_APPRAISER_CODE " 
      +" AND    APPR_APPRAISER_GRP_ID IN  ( SELECT APPR_APPRAISER_GRP_ID FROM    PAS_APPR_SCORE A " 
      +" INNER JOIN PAS_APPR_APPRAISER_GRP_DTL B      ON A.EMP_ID = B.APPR_APPRAISEE_ID    WHERE EMP_ID = ? AND APPR_ID = ? ) AND  D.APPR_ID=?" 
      +" ORDER BY APPR_APPRAISER_LEVEL";
    }

    return " Framework could not the query number specified ";
  }
}