package org.paradyne.sql.admin.master;

import org.paradyne.lib.SqlBase;

public class IndusModelSql extends SqlBase
{
  public String getQuery(int id)
  {
    switch (id) { case 1:
      return " INSERT INTO HRMS_INDUS_TYPE(INDUS_CODE,INDUS_NAME,INDUS_ABBRIVIATION,INDUS_DESCRIPTION,INDUS_STATUS\t) VALUES((SELECT NVL(MAX(INDUS_CODE),0)+1 FROM HRMS_INDUS_TYPE ),?,?,?,?)";
    case 2:
      return " UPDATE HRMS_INDUS_TYPE SET INDUS_NAME=?,INDUS_ABBRIVIATION=?,INDUS_DESCRIPTION=?,INDUS_STATUS=? WHERE INDUS_CODE=?";
    case 3:
      return " DELETE FROM HRMS_INDUS_TYPE WHERE INDUS_CODE=?";
    case 4:
      return " SELECT  INDUS_CODE,nvl(INDUS_NAME,''),nvl(INDUS_ABBRIVIATION,'') FROM HRMS_INDUS_TYPE WHERE INDUS_CODE=?";
    case 5:
      return " SELECT INDUS_CODE,nvl(INDUS_NAME,''),nvl(INDUS_ABBRIVIATION,'') FROM HRMS_INDUS_TYPE ORDER BY INDUS_CODE";
    case 6:
      return " SELECT  INDUS_CODE,SUBSTR(INDUS_NAME,0,20),nvl(INDUS_ABBRIVIATION,''),DECODE(INDUS_STATUS,'A','Active','D','Deactive') FROM HRMS_INDUS_TYPE ORDER BY INDUS_CODE";
    }
    return "Framework could not the query number specified";
  }
}
