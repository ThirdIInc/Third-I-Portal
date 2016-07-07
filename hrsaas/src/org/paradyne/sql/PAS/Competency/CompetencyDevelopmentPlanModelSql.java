package org.paradyne.sql.PAS.Competency;

import org.paradyne.lib.SqlBase;

public class CompetencyDevelopmentPlanModelSql extends SqlBase
{
  public String getQuery(int id)
  {
    switch (id)
    {
    case 1:
      return "INSERT INTO HRMS_COMPETENCY_DEV_PLAN(COMP_DEV_CODE, COMP_DEV_PLAN, COMP_DEV_DESC)   VALUES((SELECT NVL(MAX(COMP_DEV_CODE),0)+1 FROM HRMS_COMPETENCY_DEV_PLAN),?,? ) ";
    case 2:
      return " UPDATE HRMS_COMPETENCY_DEV_PLAN SET COMP_DEV_PLAN= ?,COMP_DEV_DESC=? WHERE COMP_DEV_CODE = ? ";
    case 3:
      return " DELETE FROM HRMS_COMPETENCY_DEV_PLAN WHERE COMP_DEV_CODE = ? ";
    case 4:
      return "  SELECT  HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE, HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_PLAN, HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_DESC FROM HRMS_COMPETENCY_DEV_PLAN ORDER BY HRMS_COMPETENCY_DEV_PLAN.COMP_DEV_CODE DESC";
    }
    return "Query number not specified ";
  }
}