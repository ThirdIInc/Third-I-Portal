package org.paradyne.sql.PAS.Competency;

import org.paradyne.lib.SqlBase;

public class CompDesignMappingModelSql extends SqlBase
{
  public String getQuery(int id)
  {
    switch (id)
    {
    case 3:
      return "DELETE FROM HRMS_COMPETENCY_ROLE WHERE COMPETENCY_ROLE = ? ";
    case 4:
      return " SELECT DISTINCT COMPETENCY_ROLE  FROM HRMS_COMPETENCY_ROLE ";
    }
    return " Query is not available ";
  }
}