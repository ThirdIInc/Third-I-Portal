package org.paradyne.sql.gov;

import org.paradyne.lib.SqlBase;

public class EmpCreditModelSqleGov extends SqlBase {
    public String getQuery(int id)
    {
        switch(id)
        {
        case 1: // '\001'
            return " INSERT INTO HRMS_EMP_CREDIT (CREDIT_CODE,CREDIT_APPLICABLE,CREDIT_AMT,EMP_ID)  VALUES(?,?,?,?)";

        case 2: // '\002'
            return " UPDATE HRMS_EMP_CREDIT SET CREDIT_AMT=? WHERE CREDIT_CODE=? AND EMP_ID=?";

        case 3: // '\003'
            return " DELETE FROM HRMS_EMP_CREDIT WHERE EMP_ID=?";

        case 4: // '\004'
            return " SELECT CREDIT_CODE,CREDIT_NAME FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";

        case 5: // '\005'
            return " SELECT CREDIT_CODE FROM HRMS_EMP_CREDIT WHERE EMP_ID = ? ";

        case 6: // '\006'
            return " UPDATE HRMS_EMP_OFFC SET EMP_SAL_GRADE = ? WHERE EMP_ID=? ";

        case 7: // '\007'
            return " \tINSERT INTO HRMS_EMP_PERQUISITE(PERQ_CODE,PERQ_AMT,EMP_ID)  \tVALUES (?,?,?)";

        case 8: // '\b'
            return " \tDELETE FROM HRMS_EMP_PERQUISITE WHERE EMP_ID = ? ";

        case 9: // '\t'
            return " UPDATE HRMS_SALARY_MISC SET FORMULA_ID=?, GROSS_AMT=?,  TOT_MONTHLY_SAL = ?,TOT_ANNUAL_SAL =?, TOT_ANNUAL_PERQUISITE =?,CTC =?  WHERE EMP_ID=?";
        }
        return "Framework could not EXECUTE the query number specified";
    }
}
