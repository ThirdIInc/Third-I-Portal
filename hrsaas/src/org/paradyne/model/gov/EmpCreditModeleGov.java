package org.paradyne.model.gov;

import org.paradyne.lib.ModelBase;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.lsmp.djep.xjep.XJep;
import org.nfunk.jep.Node;
import org.paradyne.bean.gov.EmpCrediteGov;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.Utility;

public class EmpCreditModeleGov extends ModelBase {

    static Logger logger = Logger.getLogger(EmpCreditModeleGov.class);
    AuditTrail trial;

    public EmpCreditModeleGov() {
        trial = null;
    }

    public Object[][] showGrade(EmpCrediteGov empCredit, String gradeId, HttpServletRequest request)
    {
        String query = (new StringBuilder("SELECT HRMS_CREDIT_HEAD.CREDIT_CODE,HRMS_CREDIT_HEAD.CREDIT_NAME,CASE WHEN CREDIT_PERIODICITY='M' THEN 'Monthly'   WHEN CREDIT_PERIODICITY='Q' THEN 'Quarterly' WHEN CREDIT_PERIODICITY='A' THEN 'Annually' WHEN CREDIT_PERIODICITY='H' THEN 'Half Yearly' ELSE ' ' END,NVL(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_AMT,'0')  from HRMS_CREDIT_HEAD left JOIN HRMS_SALGRADE_DTL ON (HRMS_SALGRADE_DTL.SALGRADE_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE and HRMS_SALGRADE_DTL.SALGRADE_CODE=")).append(empCredit.getGradeId()).append(") ").append(" ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE").toString();
        String amtMonthQuery = (new StringBuilder(" SELECT NVL(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_AMT,0) FROM HRMS_SALGRADE_DTL INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE) WHERE HRMS_SALGRADE_DTL.SALGRADE_CODE=")).append(empCredit.getGradeId()).append(" AND CREDIT_PERIODICITY='M' ").toString();
        Object totAmtMonth[][] = getSqlModel().getSingleResult(amtMonthQuery);
        String amtHalfQuery = (new StringBuilder(" SELECT NVL(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_AMT,0) FROM HRMS_SALGRADE_DTL INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE) WHERE HRMS_SALGRADE_DTL.SALGRADE_CODE=")).append(empCredit.getGradeId()).append(" AND CREDIT_PERIODICITY='H' ").toString();
        Object totAmtHalf[][] = getSqlModel().getSingleResult(amtHalfQuery);
        String amtQuartQuery = (new StringBuilder(" SELECT NVL(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_AMT,0) FROM HRMS_SALGRADE_DTL INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE) WHERE HRMS_SALGRADE_DTL.SALGRADE_CODE=")).append(empCredit.getGradeId()).append(" AND CREDIT_PERIODICITY='Q' ").toString();
        Object totAmtQuart[][] = getSqlModel().getSingleResult(amtQuartQuery);
        String amtAnnualQuery = (new StringBuilder(" SELECT NVL(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_AMT,0) FROM HRMS_SALGRADE_DTL INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_SALGRADE_DTL.SALGRADE_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE) WHERE HRMS_SALGRADE_DTL.SALGRADE_CODE=")).append(empCredit.getGradeId()).append(" AND CREDIT_PERIODICITY='A' ").toString();
        Object totAmtAnnual[][] = getSqlModel().getSingleResult(amtAnnualQuery);
        Object amt[][] = getSqlModel().getSingleResult(query);
        ArrayList atlist = new ArrayList();
        HashMap hmp = new HashMap();
        double monthAMT = 0.0D;
        double halfAmt = 0.0D;
        double quarterAmt = 0.0D;
        double annualAmt = 0.0D;
        double annualSum = 0.0D;
        double PreAMT = 0.0D;
        for(int j = 0; j < totAmtMonth.length; j++)
            monthAMT += Double.parseDouble(String.valueOf(totAmtMonth[j][0]));

        for(int j = 0; j < totAmtHalf.length; j++)
            halfAmt += Double.parseDouble(String.valueOf(totAmtHalf[j][0]));

        for(int j = 0; j < totAmtQuart.length; j++)
            quarterAmt += Double.parseDouble(String.valueOf(totAmtQuart[j][0]));

        for(int j = 0; j < totAmtAnnual.length; j++)
            annualAmt += Double.parseDouble(String.valueOf(totAmtAnnual[j][0]));

        String countQuery = " select count(*),HRMS_CREDIT_HEAD.CREDIT_PERIODICITY from HRMS_CREDIT_HEAD  group by HRMS_CREDIT_HEAD.CREDIT_PERIODICITY";
        Object periLength[][] = getSqlModel().getSingleResult(countQuery);
        Object mdata[][] = (Object[][])null;
        Object qdata[][] = (Object[][])null;
        Object hdata[][] = (Object[][])null;
        Object adata[][] = (Object[][])null;
        if(periLength != null && periLength.length > 0)
        {
            for(int i = 0; i < periLength.length; i++)
            {
                if(String.valueOf(periLength[i][1]).equals("M"))
                    mdata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
                if(String.valueOf(periLength[i][1]).equals("Q"))
                    qdata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
                if(String.valueOf(periLength[i][1]).equals("H"))
                    hdata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
                if(String.valueOf(periLength[i][1]).equals("A"))
                    adata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
            }

        }
        ArrayList mlist = new ArrayList();
        ArrayList qlist = new ArrayList();
        ArrayList hlist = new ArrayList();
        ArrayList alist = new ArrayList();
        int m = 0;
        int a = 0;
        int h = 0;
        int q = 0;
        if(amt != null && amt.length > 0)
        {
            for(int i = 0; i < amt.length; i++)
            {
                EmpCrediteGov bean1 = new EmpCrediteGov();
                bean1.setCredCode(String.valueOf(amt[i][0]));
                bean1.setSalHead(String.valueOf(amt[i][1]));
                bean1.setPeriod(String.valueOf(amt[i][2]));
                bean1.setGeneralFlag(empCredit.isGeneralFlag());
                bean1.setAmount(String.valueOf(amt[i][3]));
                bean1.setGeneralFlag(empCredit.isGeneralFlag());
                bean1.setChkId(String.valueOf(amt[i][0]));
                if(bean1.getPeriod().equals("Monthly"))
                {
                    mdata[m][0] = amt[i][0];
                    mdata[m][1] = amt[i][1];
                    mdata[m][2] = amt[i][2];
                    mdata[m][3] = amt[i][3];
                    m++;
                    mlist.add(bean1);
                }
                if(bean1.getPeriod().equals("Quarterly"))
                {
                    qdata[q][0] = amt[i][0];
                    qdata[q][1] = amt[i][1];
                    qdata[q][2] = amt[i][2];
                    qdata[q][3] = amt[i][3];
                    q++;
                    qlist.add(bean1);
                }
                if(bean1.getPeriod().equals("Half Yearly"))
                {
                    hdata[h][0] = amt[i][0];
                    hdata[h][1] = amt[i][1];
                    hdata[h][2] = amt[i][2];
                    hdata[h][3] = amt[i][3];
                    h++;
                    hlist.add(bean1);
                }
                if(bean1.getPeriod().equals("Annually"))
                {
                    adata[a][0] = amt[i][0];
                    adata[a][1] = amt[i][1];
                    adata[a][2] = amt[i][2];
                    adata[a][3] = amt[i][3];
                    a++;
                    alist.add(bean1);
                }
            }

        }
        empCredit.setMIterator(mlist);
        empCredit.setQIterator(qlist);
        empCredit.setHIterator(hlist);
        empCredit.setAIterator(alist);
        request.setAttribute("mdata", ((Object) (mdata)));
        request.setAttribute("qdata", ((Object) (qdata)));
        request.setAttribute("hdata", ((Object) (hdata)));
        request.setAttribute("adata", ((Object) (adata)));
        annualSum = 12D * monthAMT + halfAmt * 2D + quarterAmt * 4D + annualAmt;
        for(int i = 0; i < amt.length; i++)
        {
            EmpCrediteGov bean1 = new EmpCrediteGov();
            bean1.setCredCode(String.valueOf(amt[i][0]));
            bean1.setSalHead(String.valueOf(amt[i][1]));
            bean1.setPeriod(String.valueOf(amt[i][2]));
            bean1.setAmount(String.valueOf(amt[i][3]));
            bean1.setGeneralFlag(empCredit.isGeneralFlag());
            bean1.setChkId(String.valueOf(amt[i][0]));
            atlist.add(bean1);
        }

        empCredit.setTotalamt(String.valueOf(Math.round(monthAMT)));
        empCredit.setAnnualAmt(String.valueOf(Math.round(annualSum)));
        empCredit.setTotalPreCommisionamt(String.valueOf(PreAMT));
        request.setAttribute("data", hmp);
        empCredit.setAtt(atlist);
        String pCode[] = request.getParameterValues("perqCode");
        String pHead[] = request.getParameterValues("perqHead");
        String pPeriod[] = request.getParameterValues("periodPerq");
        String pAmount[] = request.getParameterValues("amountPerq");
        String pAnnualAmt = request.getParameter("annualAmtPer");
        empCredit.setAnnualAmtPer(pAnnualAmt);
        String perCount = "select count(*),HRMS_PERQUISITE_HEAD.PERQ_PERIOD from HRMS_PERQUISITE_HEAD  group by HRMS_PERQUISITE_HEAD.PERQ_PERIOD ";
        Object perCountObj[][] = getSqlModel().getSingleResult(perCount);
        Object mdataPerq[][] = (Object[][])null;
        Object qdataPerq[][] = (Object[][])null;
        Object hdataPerq[][] = (Object[][])null;
        Object adataPerq[][] = (Object[][])null;
        if(perCountObj != null && perCountObj.length > 0)
        {
            for(int i = 0; i < perCountObj.length; i++)
            {
                if(String.valueOf(perCountObj[i][1]).equals("M"))
                    mdataPerq = new Object[Integer.parseInt(String.valueOf(perCountObj[i][0]))][4];
                if(String.valueOf(perCountObj[i][1]).equals("Q"))
                    qdataPerq = new Object[Integer.parseInt(String.valueOf(perCountObj[i][0]))][4];
                if(String.valueOf(perCountObj[i][1]).equals("H"))
                    hdataPerq = new Object[Integer.parseInt(String.valueOf(perCountObj[i][0]))][4];
                if(String.valueOf(perCountObj[i][1]).equals("A"))
                    adataPerq = new Object[Integer.parseInt(String.valueOf(perCountObj[i][0]))][4];
            }

        }
        ArrayList mlistPerq = new ArrayList();
        ArrayList qlistPerq = new ArrayList();
        ArrayList hlistPerq = new ArrayList();
        ArrayList alistPerq = new ArrayList();
        m = 0;
        a = 0;
        h = 0;
        q = 0;
        if(pCode != null && pCode.length > 0)
        {
            for(int i = 0; i < pCode.length; i++)
            {
                EmpCrediteGov bean1 = new EmpCrediteGov();
                bean1.setPerqCode(String.valueOf(pCode[i]));
                bean1.setPerqHead(String.valueOf(pHead[i]));
                bean1.setPeriodPerq(String.valueOf(pPeriod[i]));
                bean1.setGeneralFlag(empCredit.isGeneralFlag());
                bean1.setAmountPerq(String.valueOf(pAmount[i]));
                bean1.setGeneralFlag(empCredit.isGeneralFlag());
                bean1.setChkIdPerq(String.valueOf(pCode[i]));
                if(bean1.getPeriodPerq().equals("Monthly"))
                {
                    mdataPerq[m][0] = pCode[i];
                    mdataPerq[m][1] = pHead[i];
                    mdataPerq[m][2] = pPeriod[i];
                    mdataPerq[m][3] = pAmount[i];
                    m++;
                    mlistPerq.add(bean1);
                }
                if(bean1.getPeriodPerq().equals("Quarterly"))
                {
                    qdataPerq[q][0] = pCode[i];
                    qdataPerq[q][1] = pHead[i];
                    qdataPerq[q][2] = pPeriod[i];
                    qdataPerq[q][3] = pAmount[i];
                    q++;
                    qlistPerq.add(bean1);
                }
                if(bean1.getPeriodPerq().equals("Half Yearly"))
                {
                    hdataPerq[h][0] = pCode[i];
                    hdataPerq[h][1] = pHead[i];
                    hdataPerq[h][2] = pPeriod[i];
                    hdataPerq[h][3] = pAmount[i];
                    h++;
                    hlistPerq.add(bean1);
                }
                if(bean1.getPeriodPerq().equals("Annually"))
                {
                    adataPerq[a][0] = pCode[i];
                    adataPerq[a][1] = pHead[i];
                    adataPerq[a][2] = pPeriod[i];
                    adataPerq[a][3] = pAmount[i];
                    a++;
                    alistPerq.add(bean1);
                }
            }

        }
        empCredit.setMIteratorPer(mlistPerq);
        empCredit.setQIteratorPer(qlistPerq);
        empCredit.setHIteratorPer(hlistPerq);
        empCredit.setAIteratorPer(alistPerq);
        request.setAttribute("mdataPerq", ((Object) (mdataPerq)));
        request.setAttribute("qdataPerq", ((Object) (qdataPerq)));
        request.setAttribute("hdataPerq", ((Object) (hdataPerq)));
        request.setAttribute("adataPerq", ((Object) (adataPerq)));
        ctcMethod(empCredit);
        return amt;
    }

    public void showGeneralEmpData(EmpCrediteGov bean, String empId)
    {
        try
        {
            String empQuery = (new StringBuilder(" SELECT   HRMS_EMP_OFFC.EMP_TOKEN ,  HRMS_TITLE.TITLE_NAME||' '|| HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,NVL(SALGRADE_TYPE,' ') ,SALGRADE_CODE,  HRMS_EMP_OFFC.EMP_ID,  HRMS_SALARY_MISC.FORMULA_ID, NVL(HRMS_FORMULABUILDER_HDR.FORMULA_NAME,' '),  HRMS_SALARY_MISC.GROSS_AMT,DECODE(HRMS_SALARY_MISC.SAL_EPF_FLAG,'Y','true','N','false')  FROM HRMS_EMP_OFFC   INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID)  LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE)  LEFT JOIN HRMS_SALGRADE_HDR ON(HRMS_EMP_OFFC.EMP_SAL_GRADE=HRMS_SALGRADE_HDR.SALGRADE_CODE)  LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)  LEFT JOIN HRMS_FORMULABUILDER_HDR ON(HRMS_FORMULABUILDER_HDR.FORMULA_ID = HRMS_SALARY_MISC.FORMULA_ID)  where emp_id = ")).append(empId).toString();
            Object empData[][] = getSqlModel().getSingleResult(empQuery);
            bean.setEmpToken(String.valueOf(empData[0][0]));
            bean.setEmpName(String.valueOf(empData[0][1]));
            bean.setEmpCenter(String.valueOf(empData[0][2]));
            bean.setEmpRank(String.valueOf(empData[0][3]));
            bean.setGradeName(String.valueOf(empData[0][4]));
            bean.setGradeId(String.valueOf(empData[0][5]));
            bean.setEmpId(String.valueOf(empData[0][6]));
            bean.setFrmId(String.valueOf(empData[0][7]));
            bean.setFrmName(String.valueOf(empData[0][8]));
            bean.setGrsAmt(String.valueOf(empData[0][9]));
            bean.setPfFlag(String.valueOf(empData[0][10]));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public Object[][] showEmp(EmpCrediteGov empCredit, String empId, HttpServletRequest request)
    {
        String query = (new StringBuilder(" SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE ,HRMS_CREDIT_HEAD.CREDIT_NAME ,CASE WHEN CREDIT_PERIODICITY='M' THEN 'Monthly'   WHEN CREDIT_PERIODICITY='Q' THEN 'Quarterly' WHEN CREDIT_PERIODICITY='A' THEN 'Annually' WHEN CREDIT_PERIODICITY='H' THEN 'Half Yearly' ELSE ' ' END, NVL(CREDIT_AMT,0)\t EMP_ID\tFROM HRMS_EMP_CREDIT \t RIGHT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE AND EMP_ID=")).append(empCredit.getEmpId()).append(")").append("\t ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE ").toString();
        String amtQuery = (new StringBuilder(" SELECT  NVL(CREDIT_AMT,0) FROM HRMS_EMP_CREDIT INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE WHERE EMP_ID=")).append(empCredit.getEmpId()).append(" AND CREDIT_PERIODICITY ='M' ORDER BY HRMS_EMP_CREDIT.CREDIT_CODE").toString();
        Object totMonthlyAmt[][] = getSqlModel().getSingleResult(amtQuery);
        String amtHalfQuery = (new StringBuilder(" SELECT NVL(CREDIT_AMT,0) FROM HRMS_EMP_CREDIT INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE WHERE EMP_ID=")).append(empCredit.getEmpId()).append(" AND CREDIT_PERIODICITY ='H' ORDER BY HRMS_EMP_CREDIT.CREDIT_CODE").toString();
        Object totAmtHalf[][] = getSqlModel().getSingleResult(amtHalfQuery);
        String amtQuartQuery = (new StringBuilder(" SELECT NVL(CREDIT_AMT,0) FROM HRMS_EMP_CREDIT INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE WHERE EMP_ID=")).append(empCredit.getEmpId()).append(" AND CREDIT_PERIODICITY ='Q' ORDER BY HRMS_EMP_CREDIT.CREDIT_CODE").toString();
        Object totAmtQuart[][] = getSqlModel().getSingleResult(amtQuartQuery);
        String amtAnnualQuery = (new StringBuilder(" SELECT NVL(CREDIT_AMT,0) FROM HRMS_EMP_CREDIT INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE WHERE EMP_ID=")).append(empCredit.getEmpId()).append(" AND CREDIT_PERIODICITY ='A' ORDER BY HRMS_EMP_CREDIT.CREDIT_CODE").toString();
        Object totAmtAnnual[][] = getSqlModel().getSingleResult(amtAnnualQuery);
        Object amt[][] = getSqlModel().getSingleResult(query);
        ArrayList atlist = new ArrayList();
        HashMap hmp = new HashMap();
        double monthAMT = 0.0D;
        double halfAmt = 0.0D;
        double quarterAmt = 0.0D;
        double annualAmt = 0.0D;
        double annualSum = 0.0D;
        double PreAMT = 0.0D;
        for(int j = 0; j < totMonthlyAmt.length; j++)
            monthAMT += Double.parseDouble(String.valueOf(totMonthlyAmt[j][0]));

        for(int j = 0; j < totAmtHalf.length; j++)
            halfAmt += Double.parseDouble(String.valueOf(totAmtHalf[j][0]));

        for(int j = 0; j < totAmtQuart.length; j++)
            quarterAmt += Double.parseDouble(String.valueOf(totAmtQuart[j][0]));

        for(int j = 0; j < totAmtAnnual.length; j++)
            annualAmt += Double.parseDouble(String.valueOf(totAmtAnnual[j][0]));

        annualSum = 12D * monthAMT + halfAmt * 2D + quarterAmt * 4D + annualAmt;
        String countQuery = " select count(*),HRMS_CREDIT_HEAD.CREDIT_PERIODICITY from HRMS_CREDIT_HEAD  group by HRMS_CREDIT_HEAD.CREDIT_PERIODICITY";
        Object periLength[][] = getSqlModel().getSingleResult(countQuery);
        Object mdata[][] = (Object[][])null;
        Object qdata[][] = (Object[][])null;
        Object hdata[][] = (Object[][])null;
        Object adata[][] = (Object[][])null;
        if(periLength != null && periLength.length > 0)
        {
            for(int i = 0; i < periLength.length; i++)
            {
                if(String.valueOf(periLength[i][1]).equals("M"))
                    mdata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
                if(String.valueOf(periLength[i][1]).equals("Q"))
                    qdata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
                if(String.valueOf(periLength[i][1]).equals("H"))
                    hdata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
                if(String.valueOf(periLength[i][1]).equals("A"))
                    adata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
            }

        }
        ArrayList mlist = new ArrayList();
        ArrayList qlist = new ArrayList();
        ArrayList hlist = new ArrayList();
        ArrayList alist = new ArrayList();
        int m = 0;
        int a = 0;
        int h = 0;
        int q = 0;
        if(amt != null && amt.length > 0)
        {
            for(int i = 0; i < amt.length; i++)
            {
                EmpCrediteGov bean1 = new EmpCrediteGov();
                bean1.setCredCode(String.valueOf(amt[i][0]));
                bean1.setSalHead(String.valueOf(amt[i][1]));
                bean1.setPeriod(String.valueOf(amt[i][2]));
                bean1.setGeneralFlag(empCredit.isGeneralFlag());
                bean1.setAmount(String.valueOf(amt[i][3]));
                bean1.setGeneralFlag(empCredit.isGeneralFlag());
                bean1.setChkId(String.valueOf(amt[i][0]));
                if(bean1.getPeriod().equals("Monthly"))
                {
                    mdata[m][0] = amt[i][0];
                    mdata[m][1] = amt[i][1];
                    mdata[m][2] = amt[i][2];
                    mdata[m][3] = amt[i][3];
                    m++;
                    mlist.add(bean1);
                }
                if(bean1.getPeriod().equals("Quarterly"))
                {
                    qdata[q][0] = amt[i][0];
                    qdata[q][1] = amt[i][1];
                    qdata[q][2] = amt[i][2];
                    qdata[q][3] = amt[i][3];
                    q++;
                    qlist.add(bean1);
                }
                if(bean1.getPeriod().equals("Half Yearly"))
                {
                    hdata[h][0] = amt[i][0];
                    hdata[h][1] = amt[i][1];
                    hdata[h][2] = amt[i][2];
                    hdata[h][3] = amt[i][3];
                    h++;
                    hlist.add(bean1);
                }
                if(bean1.getPeriod().equals("Annually"))
                {
                    adata[a][0] = amt[i][0];
                    adata[a][1] = amt[i][1];
                    adata[a][2] = amt[i][2];
                    adata[a][3] = amt[i][3];
                    a++;
                    alist.add(bean1);
                }
            }

        }
        empCredit.setMIterator(mlist);
        empCredit.setQIterator(qlist);
        empCredit.setHIterator(hlist);
        empCredit.setAIterator(alist);
        request.setAttribute("mdata", ((Object) (mdata)));
        request.setAttribute("qdata", ((Object) (qdata)));
        request.setAttribute("hdata", ((Object) (hdata)));
        request.setAttribute("adata", ((Object) (adata)));
        for(int i = 0; i < amt.length; i++)
        {
            EmpCrediteGov bean1 = new EmpCrediteGov();
            bean1.setCredCode(String.valueOf(amt[i][0]));
            bean1.setSalHead(String.valueOf(amt[i][1]));
            bean1.setPeriod(String.valueOf(amt[i][2]));
            bean1.setGeneralFlag(empCredit.isGeneralFlag());
            bean1.setAmount(String.valueOf(amt[i][3]));
            bean1.setGeneralFlag(empCredit.isGeneralFlag());
            bean1.setChkId(String.valueOf(amt[i][0]));
            atlist.add(bean1);
        }

        empCredit.setTotalamt(String.valueOf(monthAMT));
        empCredit.setTotalPreCommisionamt(String.valueOf(PreAMT));
        empCredit.setAnnualAmt((new StringBuilder(String.valueOf(String.valueOf(Math.round(annualSum))))).append(".00").toString());
        request.setAttribute("data", hmp);
        empCredit.setAtt(atlist);
        String qury = (new StringBuilder("  SELECT DISTINCT NVL(PERQ_AMT,0),HRMS_PERQUISITE_HEAD.PERQ_CODE ,HRMS_PERQUISITE_HEAD.PERQ_NAME ,  EMP_ID,CASE WHEN PERQ_PERIOD='M' THEN 'Monthly' WHEN PERQ_PERIOD='Q' THEN 'Quarterly' WHEN PERQ_PERIOD='A' THEN 'Annually' WHEN PERQ_PERIOD='H' THEN 'Half Yearly' ELSE ' ' END \tFROM HRMS_EMP_PERQUISITE  RIGHT JOIN HRMS_PERQUISITE_HEAD ON (HRMS_PERQUISITE_HEAD.PERQ_CODE = HRMS_EMP_PERQUISITE.PERQ_CODE  AND EMP_ID=")).append(empId).append(")  ORDER BY HRMS_PERQUISITE_HEAD.PERQ_CODE ").toString();
        Object amtPerq[][] = getSqlModel().getSingleResult(qury);
        String queryPer = (new StringBuilder("SELECT NVL(PERQ_AMT,0) FROM HRMS_EMP_PERQUISITE INNER JOIN HRMS_PERQUISITE_HEAD ON(HRMS_PERQUISITE_HEAD.PERQ_CODE=HRMS_EMP_PERQUISITE.PERQ_CODE) WHERE EMP_ID=")).append(empCredit.getEmpId()).append(" and PERQ_PERIOD='M' ").toString();
        Object perAmt[][] = getSqlModel().getSingleResult(queryPer);
        String halfQueryPer = (new StringBuilder("SELECT NVL(PERQ_AMT,0) FROM HRMS_EMP_PERQUISITE INNER JOIN HRMS_PERQUISITE_HEAD ON(HRMS_EMP_PERQUISITE.PERQ_CODE=HRMS_PERQUISITE_HEAD.PERQ_CODE) WHERE EMP_ID=")).append(empCredit.getEmpId()).append(" and PERQ_PERIOD='H' ").toString();
        Object perHalfAmt[][] = getSqlModel().getSingleResult(halfQueryPer);
        String quartQueryPer = (new StringBuilder("SELECT NVL(PERQ_AMT,0) FROM HRMS_EMP_PERQUISITE INNER JOIN HRMS_PERQUISITE_HEAD ON(HRMS_EMP_PERQUISITE.PERQ_CODE=HRMS_PERQUISITE_HEAD.PERQ_CODE) WHERE EMP_ID=")).append(empCredit.getEmpId()).append(" and PERQ_PERIOD='Q' ").toString();
        Object perQuartAmt[][] = getSqlModel().getSingleResult(quartQueryPer);
        String annualQueryPer = (new StringBuilder("SELECT NVL(PERQ_AMT,0) FROM HRMS_EMP_PERQUISITE INNER JOIN HRMS_PERQUISITE_HEAD ON(HRMS_EMP_PERQUISITE.PERQ_CODE=HRMS_PERQUISITE_HEAD.PERQ_CODE) WHERE EMP_ID=")).append(empCredit.getEmpId()).append(" and PERQ_PERIOD='A' ").toString();
        Object perAnnualAmt[][] = getSqlModel().getSingleResult(annualQueryPer);
        double sumPer = 0.0D;
        double HalfSumPer = 0.0D;
        double quartSumPer = 0.0D;
        double anlSumPer = 0.0D;
        double annualSumPer = 0.0D;
        if(perAmt != null && perAmt.length > 0)
        {
            for(int j = 0; j < perAmt.length; j++)
                sumPer += Double.parseDouble(String.valueOf(perAmt[j][0]));

        }
        if(perHalfAmt != null && perHalfAmt.length > 0)
        {
            for(int j = 0; j < perHalfAmt.length; j++)
                HalfSumPer += Double.parseDouble(String.valueOf(perHalfAmt[j][0]));

        }
        if(perQuartAmt != null && perQuartAmt.length > 0)
        {
            for(int j = 0; j < perQuartAmt.length; j++)
                quartSumPer += Double.parseDouble(String.valueOf(perQuartAmt[j][0]));

        }
        if(perAnnualAmt != null && perAnnualAmt.length > 0)
        {
            for(int j = 0; j < perAnnualAmt.length; j++)
                anlSumPer += Double.parseDouble(String.valueOf(perAnnualAmt[j][0]));

        }
        annualSumPer = 12D * sumPer + 2D * HalfSumPer + quartSumPer * 4D + anlSumPer;
        empCredit.setAnnualAmtPer(String.valueOf(annualSumPer));
        String perCount = "select count(*),HRMS_PERQUISITE_HEAD.PERQ_PERIOD from HRMS_PERQUISITE_HEAD  group by HRMS_PERQUISITE_HEAD.PERQ_PERIOD ";
        Object perCountObj[][] = getSqlModel().getSingleResult(perCount);
        Object mdataPerq[][] = (Object[][])null;
        Object qdataPerq[][] = (Object[][])null;
        Object hdataPerq[][] = (Object[][])null;
        Object adataPerq[][] = (Object[][])null;
        if(perCountObj != null && perCountObj.length > 0)
        {
            for(int i = 0; i < perCountObj.length; i++)
            {
                if(String.valueOf(perCountObj[i][1]).equals("M"))
                    mdataPerq = new Object[Integer.parseInt(String.valueOf(perCountObj[i][0]))][4];
                if(String.valueOf(perCountObj[i][1]).equals("Q"))
                    qdataPerq = new Object[Integer.parseInt(String.valueOf(perCountObj[i][0]))][4];
                if(String.valueOf(perCountObj[i][1]).equals("H"))
                    hdataPerq = new Object[Integer.parseInt(String.valueOf(perCountObj[i][0]))][4];
                if(String.valueOf(perCountObj[i][1]).equals("A"))
                    adataPerq = new Object[Integer.parseInt(String.valueOf(perCountObj[i][0]))][4];
            }

        }
        ArrayList mlistPerq = new ArrayList();
        ArrayList qlistPerq = new ArrayList();
        ArrayList hlistPerq = new ArrayList();
        ArrayList alistPerq = new ArrayList();
        m = 0;
        a = 0;
        h = 0;
        q = 0;
        if(amtPerq != null && amtPerq.length > 0)
        {
            for(int i = 0; i < amtPerq.length; i++)
            {
                EmpCrediteGov bean1 = new EmpCrediteGov();
                bean1.setPerqCode(String.valueOf(amtPerq[i][1]));
                bean1.setPerqHead(String.valueOf(amtPerq[i][2]));
                bean1.setPeriodPerq(String.valueOf(amtPerq[i][4]));
                bean1.setGeneralFlag(empCredit.isGeneralFlag());
                bean1.setAmountPerq(String.valueOf(amtPerq[i][0]));
                bean1.setGeneralFlag(empCredit.isGeneralFlag());
                bean1.setChkIdPerq(String.valueOf(amtPerq[i][0]));
                if(String.valueOf(amtPerq[i][4]).equals("Monthly"))
                {
                    mdataPerq[m][0] = amtPerq[i][1];
                    mdataPerq[m][1] = amtPerq[i][2];
                    mdataPerq[m][2] = amtPerq[i][4];
                    mdataPerq[m][3] = amtPerq[i][0];
                    m++;
                    mlistPerq.add(bean1);
                }
                if(String.valueOf(amtPerq[i][4]).equals("Quarterly"))
                {
                    qdataPerq[q][0] = amtPerq[i][1];
                    qdataPerq[q][1] = amtPerq[i][2];
                    qdataPerq[q][2] = amtPerq[i][4];
                    qdataPerq[q][3] = amtPerq[i][0];
                    q++;
                    qlistPerq.add(bean1);
                }
                if(String.valueOf(amtPerq[i][4]).equals("Half Yearly"))
                {
                    hdataPerq[h][0] = amtPerq[i][1];
                    hdataPerq[h][1] = amtPerq[i][2];
                    hdataPerq[h][2] = amtPerq[i][4];
                    hdataPerq[h][3] = amtPerq[i][0];
                    h++;
                    hlistPerq.add(bean1);
                }
                if(String.valueOf(amtPerq[i][4]).equals("Annually"))
                {
                    adataPerq[a][0] = amtPerq[i][1];
                    adataPerq[a][1] = amtPerq[i][2];
                    adataPerq[a][2] = amtPerq[i][4];
                    adataPerq[a][3] = amtPerq[i][0];
                    a++;
                    alistPerq.add(bean1);
                }
            }

        }
        empCredit.setMIteratorPer(mlistPerq);
        empCredit.setQIteratorPer(qlistPerq);
        empCredit.setHIteratorPer(hlistPerq);
        empCredit.setAIteratorPer(alistPerq);
        request.setAttribute("mdataPerq", ((Object) (mdataPerq)));
        request.setAttribute("qdataPerq", ((Object) (qdataPerq)));
        request.setAttribute("hdataPerq", ((Object) (hdataPerq)));
        request.setAttribute("adataPerq", ((Object) (adataPerq)));
        ctcMethod(empCredit);
        return amt;
    }

    public void ctcMethod(EmpCrediteGov empCredit)
    {
        try
        {
            double annualAmt = 0.0D;
            String creditQuery = (new StringBuilder("SELECT  SUM(CASE WHEN CREDIT_PERIODICITY ='M' THEN  NVL(CREDIT_AMT,0) * 12  WHEN CREDIT_PERIODICITY ='Q' THEN  NVL(CREDIT_AMT,0) * 4 WHEN CREDIT_PERIODICITY ='H' THEN  NVL(CREDIT_AMT,0) * 2 WHEN CREDIT_PERIODICITY ='A' THEN  NVL(CREDIT_AMT,0) * 1 ELSE  0 END ) FROM HRMS_CREDIT_HEAD  LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE)  WHERE EMP_ID=")).append(empCredit.getEmpId()).append(" AND CREDIT_IS_CTC_COMPONENT='Y'").toString();
            Object creditAmtObj[][] = getSqlModel().getSingleResult(creditQuery);
            annualAmt = Double.parseDouble(String.valueOf(creditAmtObj[0][0]));
            empCredit.setCtcAmt((new StringBuilder(String.valueOf(String.valueOf(Math.round(annualAmt))))).append(".00").toString());
        }
        catch(Exception exception) { }
    }

    public boolean addCredit(EmpCrediteGov bean)
    {
        Object addObj[][] = new Object[1][8];
        addObj[0][0] = bean.getEmpCredit();
        addObj[0][1] = bean.getEmpId();
        addObj[0][2] = bean.getEmpAmount();
        return getSqlModel().singleExecute(getQuery(1), addObj);
    }

    public boolean modCredit(EmpCrediteGov bean)
    {
        Object addObj[][] = new Object[1][3];
        addObj[0][0] = bean.getEmpAmount();
        addObj[0][1] = bean.getEmpId();
        addObj[0][2] = bean.getEmpCredit();
        boolean result = getSqlModel().singleExecute(getQuery(2), addObj);
        return result;
    }

    public boolean deleteCredit(EmpCrediteGov bean)
    {
        Object addObj[][] = new Object[1][2];
        String del = (new StringBuilder(" DELETE FROM HRMS_EMP_CREDIT WHERE EMP_ID=")).append(bean.getEmpId()).toString();
        Object bean1[][] = new Object[1][1];
        bean1[0][0] = bean.getEmpId();
        boolean result = false;
        boolean result1 = false;
        try
        {
            result = getSqlModel().singleExecute(del);
            result1 = getSqlModel().singleExecute(getQuery(8), bean1);
        }
        catch(Exception exception) { }
        return result && result1;
    }

    public void getCreditRecord(EmpCrediteGov bean)
    {
        Object addObj[] = new Object[1];
        addObj[0] = bean.getEmpId();
        Object data[][] = getSqlModel().getSingleResult(getQuery(4), addObj);
        for(int i = 0; i < data.length; i++)
        {
            bean.setEmpCredit(String.valueOf(data[i][0]));
            bean.setEmpName(String.valueOf(data[i][1]));
            bean.setEmpDepart(String.valueOf(data[i][2]));
            bean.setEmpCenter(String.valueOf(data[i][3]));
            bean.setEmpTrade(String.valueOf(data[i][4]));
            bean.setEmpRank(String.valueOf(data[i][5]));
            bean.setEmpAmount(String.valueOf(data[i][6]));
        }

    }

    public void getCreditReport(EmpCrediteGov bean)
    {
        Object addObj[] = new Object[1];
        ArrayList dispList = new ArrayList();
        Object data[][] = getSqlModel().getSingleResult(getQuery(5));
        for(int i = 0; i < data.length; i++)
        {
            EmpCrediteGov bean1 = new EmpCrediteGov();
            bean1.setEmpId(String.valueOf(data[i][0]));
            bean1.setEmpCredit(String.valueOf(data[i][1]));
            bean1.setEmpAmount(String.valueOf(data[i][2]));
            dispList.add(bean1);
        }

        bean.setEmpCreditArray(dispList);
    }

    public void getTableDetails(EmpCrediteGov bean)
    {
        Object addObj[] = new Object[1];
        ArrayList list = new ArrayList();
        addObj[0] = bean.getEmpCredit();
        Object result[][] = getSqlModel().getSingleResult(getQuery(7));
        for(int i = 0; i < result.length; i++)
        {
            EmpCrediteGov bean1 = new EmpCrediteGov();
            bean1.setSalHead(String.valueOf(result[i][0]));
            list.add(bean1);
        }
        bean.setAtt(list);
    }

    public void getChkList(EmpCrediteGov bean)
    {
        String sqlSelect = "SELECT CREDIT_CODE ,CREDIT_NAME  FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE ";
        Object data[][] = getSqlModel().getSingleResult(sqlSelect, ((Object []) (new Object[0][])));
        ArrayList atlist = new ArrayList();
        for(int i = 0; i < data.length; i++)
        {
            EmpCrediteGov bean1 = new EmpCrediteGov();
            bean1.setChkId(String.valueOf(data[i][0]));
            bean1.setSalHead(String.valueOf(data[i][1]));
            bean1.setChkSubmit("");
            atlist.add(bean1);
        }

        bean.setAtt(atlist);
    }

    public Object[][] chkEmpId(String emp)
    {
        return getSqlModel().getSingleResult(getQuery(5), new Object[] {
            emp
        });
    }

    public void updateSalGrade(EmpCrediteGov bean)
    {
        Object updateGrade[][] = new Object[1][2];
        updateGrade[0][0] = bean.getGradeId();
        updateGrade[0][1] = bean.getEmpId();
        getSqlModel().singleExecute(getQuery(6), updateGrade);
    }

    public String checkNull(String result)
    {
        if(result == null || result.equals("null"))
            return "";
        else
            return result;
    }

    public void updateFormula(EmpCrediteGov bean)
    {
        Object updateObj[][] = new Object[1][7];
        updateObj[0][0] = checkNull(bean.getFrmId());
        updateObj[0][1] = checkNull(bean.getGrsAmt());
        updateObj[0][2] = checkNull(bean.getTotalamt());
        updateObj[0][3] = bean.getAnnualAmt();
        updateObj[0][4] = bean.getAnnualAmtPer();
        ctcMethod(bean);
        updateObj[0][5] = bean.getCtcAmt();
        updateObj[0][6] = bean.getEmpId();
        getSqlModel().singleExecute(getQuery(9), updateObj);
    }

    public String addCreditData(Object tableData[], EmpCrediteGov empCredit, HttpServletRequest request)
    {
        String str = null;
        String qury = (new StringBuilder(" SELECT HRMS_EMP_CREDIT.CREDIT_AMT FROM HRMS_EMP_CREDIT WHERE HRMS_EMP_CREDIT.CREDIT_CODE=")).append(tableData[0]).append("  AND ").append("  HRMS_EMP_CREDIT.EMP_ID =").append(tableData[3]).toString();
        Object amt[][] = getSqlModel().getSingleResult(qury);
        if(amt.length == 0)
        {
            Object bean[][] = new Object[1][4];
            bean[0][0] = tableData[0];
            bean[0][1] = tableData[1];
            bean[0][2] = tableData[2];
            bean[0][3] = tableData[3];
            boolean result = getSqlModel().singleExecute(getQuery(1), bean);
            if(result)
            {
                trial = new AuditTrail(empCredit.getUserEmpId());
                trial.initiate(context, session);
                trial.setParameters("HRMS_EMP_CREDIT", new String[] {
                    "CREDIT_CODE", "EMP_ID"
                }, new String[] {
                    String.valueOf(tableData[0]), empCredit.getEmpId()
                }, empCredit.getEmpId());
                trial.setComments((new StringBuilder("CREDIT_CODE   :")).append(String.valueOf(tableData[0])).toString());
                trial.executeADDTrail(request);
            }
            str = "Record saved Successfully!";
        } else
        {
            trial = new AuditTrail(empCredit.getUserEmpId());
            trial.initiate(context, session);
            trial.setParameters("HRMS_EMP_CREDIT", new String[] {
                "CREDIT_CODE", "EMP_ID"
            }, new String[] {
                String.valueOf(tableData[0]), empCredit.getEmpId()
            }, empCredit.getEmpId());
            trial.beginTrail();
            trial.setComments((new StringBuilder("CREDIT_CODE   :")).append(String.valueOf(tableData[0])).toString());
            String upd = (new StringBuilder("UPDATE HRMS_EMP_CREDIT SET CREDIT_AMT=")).append(tableData[2]).append(", CREDIT_APPLICABLE='").append(tableData[1]).append("' WHERE CREDIT_CODE=").append(tableData[0]).append(" AND EMP_ID=").append(tableData[3]).toString();
            boolean result = getSqlModel().singleExecute(upd);
            trial.executeMODTrail(request);
            str = "Record updated Successfully!";
        }
        return str;
    }

    public void view(String empId, String chkCode[], EmpCrediteGov bean)
    {
        String sqlSelect = (new StringBuilder(" SELECT \tHRMS_EMP_CREDIT.CREDIT_CODE, HRMS_CREDIT_HEAD.CREDIT_NAME, HRMS_EMP_CREDIT.CREDIT_AMT,CREDIT_AMT_PRECOMMISSION FROM HRMS_EMP_CREDIT LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_EMP_CREDIT.CREDIT_CODE\t=\tHRMS_CREDIT_HEAD.CREDIT_CODE) WHERE HRMS_EMP_CREDIT.EMP_ID =")).append(empId).toString();
        Object data[][] = getSqlModel().getSingleResult(sqlSelect, ((Object []) (new Object[0][])));
        ArrayList atlist = new ArrayList();
        for(int i = 0; i < chkCode.length; i++)
        {
            EmpCrediteGov bean1 = new EmpCrediteGov();
            bean1.setChkId(String.valueOf(data[i][0]));
            bean1.setSalHead(String.valueOf(data[i][1]));
            bean1.setEmpAmount(String.valueOf(data[i][2]));
            bean1.setPreCommAmt(String.valueOf(data[0][3]));
            atlist.add(bean1);
        }

        bean.setAtt(atlist);
    }

    public String executeFormula(String sal_formula, double CTC, Object tableData[][], ArrayList frmData)
    {
        try {
			String frml;
			String str[] = sal_formula.split("#");
			frml = "";
			for (int i = 0; i < str.length; i++) {
				if (str[i].equals("CTC")) {
					frml = (new StringBuilder(String.valueOf(frml)))
							.append(CTC).toString();
				} else {
					String flag = "false";
					String strValue = "";
					int cnt = 1;
					if (str[i].equals("") || str[i].equals("null"))
						strValue = str[i];
					else if (str[i].length() == 1)
						strValue = str[i];
					else if (str[i].contains("C"))
						strValue = str[i].substring(1, str[i].length());
					else
						strValue = str[i];
					for (int z = 0; z < tableData.length; z++) {
						if (String.valueOf(strValue).trim().equals(
								(new StringBuilder()).append(cnt).toString())) {
							for (int j = 0; j < tableData.length; j++)
								if (String.valueOf(tableData[j][0]).trim()
										.equals((new StringBuilder()).append(cnt).toString())) {
									frml = (new StringBuilder(String
											.valueOf(frml))).append(
											String.valueOf(tableData[j][3]))
											.toString();
									flag = "true";
								}

						}
						cnt++;
					}

					if (flag.equals("false")) {
						frml = (new StringBuilder(String.valueOf(frml)))
								.append(strValue).toString();
					}
				}
			}
			return expressionEvaluate(frml);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
    }

    public String expressionEvaluate(String formula)
    {
		try 
		{
			XJep j = new XJep();			
			try  {    
				 j.addStandardConstants();       
				 j.addStandardFunctions();       
				 j.addComplex();       
				 j.setAllowUndeclared(true);       
				 j.setImplicitMul(true);        
				 j.setAllowAssignment(true);	
				
				// parse expression    
				Node node = j.parse(formula);    // print it    				 				
				Node processed = j.preprocess(node);  
				Node simp = j.simplify(processed);	 
				Object value = j.evaluate(simp);	
				double vv=Double.parseDouble(String.valueOf(value));
				return ""+vv;
				//return ""+Utility.twoDecimals(Double.parseDouble(String.valueOf((value))));
			}
			catch(Exception e) {}
		} 
		catch (Exception e)  {
			return "0";
		}
		return "0";		
	}

    public void addPreqData(Object tableData[])
    {
        String qury = (new StringBuilder(" SELECT HRMS_EMP_PERQUISITE.PERQ_AMT  FROM HRMS_EMP_PERQUISITE  WHERE HRMS_EMP_PERQUISITE.PERQ_CODE=")).append(tableData[0]).append("  AND ").append("  HRMS_EMP_PERQUISITE.EMP_ID =").append(tableData[2]).toString();
        Object amt[][] = getSqlModel().getSingleResult(qury);
        if(amt.length == 0) {
            Object bean[][] = new Object[1][3];
            bean[0][0] = tableData[0];
            bean[0][1] = tableData[1];
            bean[0][2] = tableData[2];
            boolean result = getSqlModel().singleExecute(getQuery(7), bean);
        } else {
            String upd = (new StringBuilder(" UPDATE HRMS_EMP_PERQUISITE SET  PERQ_AMT=")).append(tableData[1]).append("    WHERE PERQ_CODE=").append(tableData[0]).append(" AND EMP_ID=").append(tableData[2]).toString();
            boolean result = getSqlModel().singleExecute(upd);
        }
    }

    public void saveCtc(EmpCrediteGov empcredit)
    {
    }

    public Object[][] showFormula(EmpCrediteGov empCredit, HttpServletRequest request)
    {
        String creditsQuery = (new StringBuilder("SELECT DISTINCT HRMS_CREDIT_HEAD.CREDIT_CODE ,HRMS_CREDIT_HEAD.CREDIT_NAME , CASE WHEN CREDIT_PERIODICITY='M' THEN 'Monthly' WHEN CREDIT_PERIODICITY='Q' THEN 'Quarterly' WHEN CREDIT_PERIODICITY='A' THEN 'Annually' WHEN CREDIT_PERIODICITY='H' THEN 'Half Yearly' ELSE ' ' END,  NVL(CREDIT_AMT,0)  EMP_ID\tFROM HRMS_EMP_CREDIT  RIGHT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_EMP_CREDIT.CREDIT_CODE AND EMP_ID=")).append(empCredit.getEmpId()).append(") ").append(" ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE").toString();
        Object creditsObj[][] = getSqlModel().getSingleResult(creditsQuery);
        Object tableData[][] = (Object[][])null;
        if(creditsObj != null && creditsObj.length > 0) {
            tableData = new Object[creditsObj.length][4];
            for(int i = 0; i < creditsObj.length; i++) {
                tableData[i][0] = String.valueOf(creditsObj[i][0]);
                tableData[i][1] = String.valueOf(creditsObj[i][1]);
                tableData[i][2] = String.valueOf(creditsObj[i][2]);
                tableData[i][3] = String.valueOf(creditsObj[i][3]);
            }
        }
        String formulaQuery = (new StringBuilder(" SELECT SAL_CODE, SAL_TYPE,SAL_FORMULA FROM HRMS_FORMULABUILDER_HDR  INNER JOIN HRMS_FORMULABUILDER_DTL ON(HRMS_FORMULABUILDER_DTL.FORMULA_ID = HRMS_FORMULABUILDER_HDR.FORMULA_ID)  WHERE HRMS_FORMULABUILDER_HDR.FORMULA_ID = ")).append(empCredit.getFrmId()).append(" ORDER BY SAL_CODE").toString();
        Object formulaObj[][] = getSqlModel().getSingleResult(formulaQuery);
        if(empCredit.getGrsAmt().length() > 0) {
            double CTC = Double.parseDouble(empCredit.getGrsAmt());
            for(int j = 0; j < tableData.length; j++)
                tableData[j][3] = "0.00";

            ArrayList diffData = new ArrayList();
            ArrayList formData = new ArrayList();
            for(int j = 0; j < tableData.length; j++) {
                for(int i = 0; i < formulaObj.length; i++) {
                    String exec = "";
                    if(String.valueOf(tableData[j][0]).equals(String.valueOf(formulaObj[i][0]))) {
                        String sal_type = String.valueOf(formulaObj[i][1]);
                        String sal_formula = String.valueOf(formulaObj[i][2]);
                        if(sal_type.trim().equals("Fi"))
                            tableData[j][3] = sal_formula;
                        else
                        if(sal_type.trim().equals("Fr"))
                        {
                            logger.info("In formula");
                            try
                            {
                                exec = executeFormula(sal_formula, CTC, tableData, formData);
                                logger.info((new StringBuilder("exec===")).append(exec).toString());
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                            tableData[j][3] = Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(exec))));
                        } else
                        if(sal_type.trim().equals("Df"))
                        {
                            diffData.add(String.valueOf(tableData[j][0]));
                            tableData[j][3] = "0.00";
                        }
                    }
                }

            }

            double sum = 0.0D;
            for(int k = 0; k < tableData.length; k++)
                try
                {
                    if(String.valueOf(tableData[k][2]).equals("Annually"))
                        sum += Double.parseDouble(String.valueOf(tableData[k][3]));
                    else
                    if(String.valueOf(tableData[k][2]).equals("Quarterly"))
                        sum += Double.parseDouble(String.valueOf(tableData[k][3])) * 4D;
                    else
                    if(String.valueOf(tableData[k][2]).equals("Half Yearly"))
                        sum += Double.parseDouble(String.valueOf(tableData[k][3])) * 2D;
                    else
                    if(String.valueOf(tableData[k][2]).equals("Monthly"))
                        sum += Double.parseDouble(String.valueOf(tableData[k][3])) * 12D;
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            double cal = 0.0D;
            if(CTC >= sum)
                cal = (CTC - sum) / 12D;
            if(CTC <= sum)
                cal = (sum - CTC) / 12D;
            for(int j = 0; j < tableData.length; j++)
            {
                for(int i = 0; i < diffData.size(); i++)
                    if(String.valueOf(tableData[j][0]).equals(diffData.get(i)))
                    {
                        if(String.valueOf(tableData[j][2]).equals("Annually"))
                            tableData[j][3] = Double.valueOf(cal * 12D);
                        if(String.valueOf(tableData[j][2]).equals("Half Yearly"))
                            tableData[j][3] = Double.valueOf(cal * 6D);
                        if(String.valueOf(tableData[j][2]).equals("Quarterly"))
                            tableData[j][3] = Double.valueOf(cal * 4D);
                        if(String.valueOf(tableData[j][2]).equals("Monthly"))
                            tableData[j][3] = Double.valueOf(cal);
                    }

            }

        }
        double temp = 0.0D;
        for(int i = 0; i < tableData.length; i++)
        {
            if(String.valueOf(tableData[i][2]).equals("Annually"))
                temp += Double.parseDouble(String.valueOf(tableData[i][3]));
            if(String.valueOf(tableData[i][2]).equals("Half Yearly"))
                temp += Double.parseDouble(String.valueOf(tableData[i][3])) * 2D;
            if(String.valueOf(tableData[i][2]).equals("Quarterly"))
                temp += Double.parseDouble(String.valueOf(tableData[i][3])) * 4D;
            if(String.valueOf(tableData[i][2]).equals("Monthly"))
                temp += Double.parseDouble(String.valueOf(tableData[i][3])) * 12D;
        }

        empCredit.setAnnualAmt((new StringBuilder()).append(Utility.twoDecimals(temp)).toString());
        double monthTemp = 0.0D;
        for(int i = 0; i < tableData.length; i++)
            if(String.valueOf(tableData[i][2]).equals("Monthly"))
                monthTemp += Double.parseDouble(String.valueOf(tableData[i][3]));

        empCredit.setTotalamt((new StringBuilder()).append(Utility.twoDecimals(monthTemp)).toString());
        String countQuery = " select count(*),HRMS_CREDIT_HEAD.CREDIT_PERIODICITY from HRMS_CREDIT_HEAD  group by HRMS_CREDIT_HEAD.CREDIT_PERIODICITY";
        Object periLength[][] = getSqlModel().getSingleResult(countQuery);
        Object mdata[][] = (Object[][])null;
        Object qdata[][] = (Object[][])null;
        Object hdata[][] = (Object[][])null;
        Object adata[][] = (Object[][])null;
        if(periLength != null && periLength.length > 0)
        {
            for(int i = 0; i < periLength.length; i++)
            {
                if(String.valueOf(periLength[i][1]).equals("M"))
                    mdata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
                if(String.valueOf(periLength[i][1]).equals("Q"))
                    qdata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
                if(String.valueOf(periLength[i][1]).equals("H"))
                    hdata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
                if(String.valueOf(periLength[i][1]).equals("A"))
                    adata = new Object[Integer.parseInt(String.valueOf(periLength[i][0]))][4];
            }

        }
        ArrayList mlist = new ArrayList();
        ArrayList qlist = new ArrayList();
        ArrayList hlist = new ArrayList();
        ArrayList alist = new ArrayList();
        int m = 0;
        int a = 0;
        int h = 0;
        int q = 0;
        ArrayList atlist = new ArrayList();
        if(tableData != null && tableData.length > 0)
        {
            for(int i = 0; i < tableData.length; i++)
            {
                EmpCrediteGov bean1 = new EmpCrediteGov();
                bean1.setCredCode(String.valueOf(tableData[i][0]));
                bean1.setSalHead(String.valueOf(tableData[i][1]));
                bean1.setPeriod(String.valueOf(tableData[i][2]));
                bean1.setAmount(String.valueOf(tableData[i][3]));
                bean1.setGeneralFlag(empCredit.isGeneralFlag());
                atlist.add(bean1);
                if(bean1.getPeriod().equals("Monthly"))
                {
                    mdata[m][0] = tableData[i][0];
                    mdata[m][1] = tableData[i][1];
                    mdata[m][2] = tableData[i][2];
                    mdata[m][3] = tableData[i][3];
                    m++;
                    mlist.add(bean1);
                }
                if(bean1.getPeriod().equals("Quarterly"))
                {
                    qdata[q][0] = tableData[i][0];
                    qdata[q][1] = tableData[i][1];
                    qdata[q][2] = tableData[i][2];
                    qdata[q][3] = tableData[i][3];
                    q++;
                    qlist.add(bean1);
                }
                if(bean1.getPeriod().equals("Half Yearly"))
                {
                    hdata[h][0] = tableData[i][0];
                    hdata[h][1] = tableData[i][1];
                    hdata[h][2] = tableData[i][2];
                    hdata[h][3] = tableData[i][3];
                    h++;
                    hlist.add(bean1);
                }
                if(bean1.getPeriod().equals("Annually"))
                {
                    adata[a][0] = tableData[i][0];
                    adata[a][1] = tableData[i][1];
                    adata[a][2] = tableData[i][2];
                    adata[a][3] = tableData[i][3];
                    a++;
                    alist.add(bean1);
                }
            }

        }
        empCredit.setMIterator(mlist);
        empCredit.setQIterator(qlist);
        empCredit.setHIterator(hlist);
        empCredit.setAIterator(alist);
        request.setAttribute("mdata", ((Object) (mdata)));
        request.setAttribute("qdata", ((Object) (qdata)));
        request.setAttribute("hdata", ((Object) (hdata)));
        request.setAttribute("adata", ((Object) (adata)));
        for(int i = 0; i < tableData.length; i++)
        {
            EmpCrediteGov bean1 = new EmpCrediteGov();
            bean1.setCredCode(String.valueOf(tableData[i][0]));
            bean1.setSalHead(String.valueOf(tableData[i][1]));
            bean1.setPeriod(String.valueOf(tableData[i][2]));
            bean1.setAmount(String.valueOf(tableData[i][3]));
            bean1.setGeneralFlag(empCredit.isGeneralFlag());
            atlist.add(bean1);
        }

        empCredit.setAtt(atlist);
        String pCode[] = request.getParameterValues("perqCode");
        String pHead[] = request.getParameterValues("perqHead");
        String pPeriod[] = request.getParameterValues("periodPerq");
        String pAmount[] = request.getParameterValues("amountPerq");
        String pAnnualAmt = request.getParameter("annualAmtPer");
        empCredit.setAnnualAmtPer(pAnnualAmt);
        String perCount = "select count(*),HRMS_PERQUISITE_HEAD.PERQ_PERIOD from HRMS_PERQUISITE_HEAD  group by HRMS_PERQUISITE_HEAD.PERQ_PERIOD ";
        Object perCountObj[][] = getSqlModel().getSingleResult(perCount);
        Object mdataPerq[][] = (Object[][])null;
        Object qdataPerq[][] = (Object[][])null;
        Object hdataPerq[][] = (Object[][])null;
        Object adataPerq[][] = (Object[][])null;
        if(perCountObj != null && perCountObj.length > 0)
        {
            for(int i = 0; i < perCountObj.length; i++)
            {
                if(String.valueOf(perCountObj[i][1]).equals("M"))
                    mdataPerq = new Object[Integer.parseInt(String.valueOf(perCountObj[i][0]))][4];
                if(String.valueOf(perCountObj[i][1]).equals("Q"))
                    qdataPerq = new Object[Integer.parseInt(String.valueOf(perCountObj[i][0]))][4];
                if(String.valueOf(perCountObj[i][1]).equals("H"))
                    hdataPerq = new Object[Integer.parseInt(String.valueOf(perCountObj[i][0]))][4];
                if(String.valueOf(perCountObj[i][1]).equals("A"))
                    adataPerq = new Object[Integer.parseInt(String.valueOf(perCountObj[i][0]))][4];
            }

        }
        ArrayList mlistPerq = new ArrayList();
        ArrayList qlistPerq = new ArrayList();
        ArrayList hlistPerq = new ArrayList();
        ArrayList alistPerq = new ArrayList();
        m = 0;
        a = 0;
        h = 0;
        q = 0;
        if(pCode != null && pCode.length > 0)
        {
            for(int i = 0; i < pCode.length; i++)
            {
                EmpCrediteGov bean1 = new EmpCrediteGov();
                bean1.setPerqCode(String.valueOf(pCode[i]));
                bean1.setPerqHead(String.valueOf(pHead[i]));
                bean1.setPeriodPerq(String.valueOf(pPeriod[i]));
                bean1.setGeneralFlag(empCredit.isGeneralFlag());
                bean1.setAmountPerq(String.valueOf(pAmount[i]));
                bean1.setGeneralFlag(empCredit.isGeneralFlag());
                bean1.setChkIdPerq(String.valueOf(pCode[i]));
                if(bean1.getPeriodPerq().equals("Monthly"))
                {
                    mdataPerq[m][0] = pCode[i];
                    mdataPerq[m][1] = pHead[i];
                    mdataPerq[m][2] = pPeriod[i];
                    mdataPerq[m][3] = pAmount[i];
                    m++;
                    mlistPerq.add(bean1);
                }
                if(bean1.getPeriodPerq().equals("Quarterly"))
                {
                    qdataPerq[q][0] = pCode[i];
                    qdataPerq[q][1] = pHead[i];
                    qdataPerq[q][2] = pPeriod[i];
                    qdataPerq[q][3] = pAmount[i];
                    q++;
                    qlistPerq.add(bean1);
                }
                if(bean1.getPeriodPerq().equals("Half Yearly"))
                {
                    hdataPerq[h][0] = pCode[i];
                    hdataPerq[h][1] = pHead[i];
                    hdataPerq[h][2] = pPeriod[i];
                    hdataPerq[h][3] = pAmount[i];
                    h++;
                    hlistPerq.add(bean1);
                }
                if(bean1.getPeriodPerq().equals("Annually"))
                {
                    adataPerq[a][0] = pCode[i];
                    adataPerq[a][1] = pHead[i];
                    adataPerq[a][2] = pPeriod[i];
                    adataPerq[a][3] = pAmount[i];
                    a++;
                    alistPerq.add(bean1);
                }
            }

        }
        empCredit.setMIteratorPer(mlistPerq);
        empCredit.setQIteratorPer(qlistPerq);
        empCredit.setHIteratorPer(hlistPerq);
        empCredit.setAIteratorPer(alistPerq);
        request.setAttribute("mdataPerq", ((Object) (mdataPerq)));
        request.setAttribute("qdataPerq", ((Object) (qdataPerq)));
        request.setAttribute("hdataPerq", ((Object) (hdataPerq)));
        request.setAttribute("adataPerq", ((Object) (adataPerq)));
        ctcMethod(empCredit);
        return tableData;
    }

	public boolean calculateCreditDebitDetails(EmpCrediteGov empCredit,
			String currentUserEmpID, HttpServletRequest request, Object[][] insertDataObj) {
		boolean result = false;
		try {
			String deleteEmpCreditQuery = "DELETE FROM HRMS_EMP_CREDIT WHERE EMP_ID = " + currentUserEmpID;
			getSqlModel().singleExecute(deleteEmpCreditQuery);
			String insertEmpCreditQuery = " INSERT INTO HRMS_EMP_CREDIT (CREDIT_CODE,CREDIT_APPLICABLE,CREDIT_AMT,EMP_ID)  VALUES(?,?,?,?)";
			getSqlModel().singleExecute(insertEmpCreditQuery, insertDataObj);
			 
			String getFormulaQuery = "SELECT CREDIT_CODE, CREDIT_FORMULA FROM HRMS_CREDIT_HEAD WHERE (CREDIT_FORMULA IS NOT NULL AND CREDIT_FORMULA != ' ')";
			Object[][] getFormulaObj = getSqlModel().getSingleResult(getFormulaQuery);
			String getCreditConfigInfoQuery = "SELECT CREDIT_CODE, NVL(CREDIT_AMT,0) FROM HRMS_EMP_CREDIT WHERE  EMP_ID = " + currentUserEmpID;
			Object[][]creditConfigInfoObj = getSqlModel().getSingleResult(getCreditConfigInfoQuery);;
			if(getFormulaObj != null && getFormulaObj.length > 0) {
				if(creditConfigInfoObj != null && creditConfigInfoObj.length > 0) {
					String creditCode = "";
					double	pfEmoluments = 0.0;
					
					for (int i = 0; i < getFormulaObj.length; i++) {
						 creditCode = String.valueOf(getFormulaObj[i][0]);
						 try {
							pfEmoluments = Utility
									.expressionEvaluate(new Utility()
											.generateFormulaPay(
													creditConfigInfoObj,
													String
															.valueOf(getFormulaObj[i][1]),
													context, session));
						} catch (Exception e) {
							pfEmoluments = 0.0;
						}
						getSqlModel().singleExecute("DELETE FROM HRMS_EMP_CREDIT WHERE CREDIT_CODE = "+creditCode+" and EMP_ID = "+currentUserEmpID);
						result = getSqlModel().singleExecute(" INSERT INTO HRMS_EMP_CREDIT (CREDIT_CODE,CREDIT_APPLICABLE,CREDIT_AMT,EMP_ID) VALUES(" + creditCode + ", 'Y'," + String.valueOf(pfEmoluments) + ","+currentUserEmpID + ")");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
