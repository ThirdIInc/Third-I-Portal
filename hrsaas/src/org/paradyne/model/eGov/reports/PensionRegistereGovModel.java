/**
 * 
 */
package org.paradyne.model.eGov.reports;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.eGov.reports.PensionRegistereGovBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.payroll.pension.PensionRegisterModel;

/**
 * @author AA0418
 *
 */
public class PensionRegistereGovModel extends ModelBase {

        NumberFormat formatter = new DecimalFormat("#0.00");
        
        static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PensionRegisterModel.class); 
        
        public void getReport(PensionRegistereGovBean penReg,HttpServletResponse response){
                
                String name = "Pension Register";
                org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(penReg.getReport(), name,"");
                
                try {
                        String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
                        Object[][] dateData = getSqlModel().getSingleResult(dateQuery);
                        //rg.setPageSize(4);
                        
                                
                        String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_PENSION_LEDGER WHERE LEDGER_MONTH = "
                                + penReg.getMonth()
                                + " AND LEDGER_YEAR = "
                                + penReg.getYear()
                                + " AND LEDGER_DIVISION = "
                                + penReg.getDivCode()
                                +" AND LEDGER_STATUS IN ('PENS_START','PENS_FINAL')";
                        
                        Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
                        
                        int recPerPage=20;
                        if(penReg.getReport().equalsIgnoreCase("xls")){
                                recPerPage = 15000;
                        }
                        
                        boolean brnCheck=false;
                        boolean deptCheck=false;
                        boolean desgCheck=false;
                
                        if(penReg.getChkBrnOrder().equals("Y"))
                                brnCheck=true;
                        if(penReg.getChkDeptOrder().equals("Y"))
                                deptCheck=true;
                        if(penReg.getChkDesgOrder().equals("Y"))
                                desgCheck=true;
                                                
                        String ledgerCode = "";
                        if (ledgerData != null && ledgerData.length > 0){
                                
                                for (int i = 0; i < ledgerData.length; i++) {
                                        ledgerCode += String.valueOf(ledgerData[i][0]) + ",";
                                }
                                ledgerCode = ledgerCode.substring(0, ledgerCode.length() - 1);
                                
                                ArrayList pensionVect = getSalary(penReg, ledgerCode);
                               if(pensionVect.size()>0) {
                                String[] header=(String [])pensionVect.get(0);
                                Object [][] penObject=(Object[][])pensionVect.get(1);
                                int [] width=(int [])pensionVect.get(2);
                                int [] alignment=(int [])pensionVect.get(3);
                                int startSum = Integer.parseInt(String.valueOf(pensionVect.get(4)));
                                
                                if(penObject != null && penObject.length > 0){
                                        
                                                                        
                                        if(brnCheck || deptCheck || desgCheck){
                                                
                                                rg = setReport(penObject, rg, brnCheck, deptCheck, desgCheck, header, width, alignment, startSum,penReg.getReport(),String.valueOf(dateData[0][0]),penReg);                     
                                                
                                        }else{                                          
                                                
                                                rg = printAllReport(penObject, header, width, alignment, recPerPage, rg, brnCheck, deptCheck, desgCheck,startSum,penReg.getReport(),String.valueOf(dateData[0][0]),penReg);
                                                
                                        }
                                }
                                
                        }        
                               else{
                                       rg.addText("No records avaliable for selected criteria", 0, 1,1);   
                               }
                                                        
                        }else
                                rg.addText("No records avaliable for selected criteria", 0, 1,1);
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
                rg.createReport(response);
                
        }
        public ArrayList getSalary(PensionRegistereGovBean penReg,String ledgerCode){
                ArrayList vect = new ArrayList();
                try{
                        String empQuery = " SELECT HRMS_PENSION_"
                                + penReg.getYear()
                                + ".PENS_EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
                                + " FML_FNAME||' '||FML_MNAME||' '||FML_LNAME ";

                        if (penReg.getCheckBrn().equals("Y"))
                                empQuery += " ,NVL(CENTER_NAME,' ' )";
        
                        if (penReg.getCheckDept().equals("Y"))
                                empQuery += " ,NVL(DEPT_NAME,' ') ";
        
                        if (penReg.getCheckDob().equals("Y"))
                                empQuery += " ,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' ') ";
        
                        if (penReg.getCheckRetDate().equals("Y"))
                                empQuery += " ,NVL(TO_CHAR(PENS_RETIRE_DATE,'DD-MM-YYYY'),' ') ";
        
                        if (penReg.getCheckBank().equals("Y"))
                                empQuery += " ,NVL(BANK_NAME,' ') ";
                                        
                        if (penReg.getCheckPan().equals("Y"))
                                empQuery += " ,NVL(SAL_PANNO,' ')  ";
        
                        if (penReg.getCheckDoj().equals("Y"))
                                empQuery += " ,NVL(TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),' ') ";
        
                        if (penReg.getCheckDesg().equals("Y"))
                                empQuery += " ,NVL(RANK_NAME,' ') ";
        
                        if (penReg.getCheckGender().equals("Y"))
                                empQuery += " ,DECODE(EMP_GENDER,'M','Male','F','Female','Others') ";
                
                                empQuery += " ,NVL(SAL_PENSION_ACCNO,' '),NVL(PENS_ARREARS_AMOUNT,0),NVL(HRMS_PENSION_"+ penReg.getYear()+".PENS_AMOUNT,0),NVL(PENS_COMM_AMOUNT,0),NVL(PENS_REC_AMOUNT,0),NVL(PENS_MISC_RECOVERY,0),NVL(PENS_NET_AMOUNT,0), "
                                                        +" EMP_CENTER,EMP_DEPT,EMP_RANK,NVL(CENTER_NAME,' ' ),NVL(DEPT_NAME,' '),NVL(RANK_NAME,' ')";
                        
                                empQuery += " FROM HRMS_PENSION_"+ penReg.getYear()
                                                + " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_PENSION_"+ penReg.getYear() + ".PENS_EMP_ID "
                                 +" LEFT JOIN HRMS_EMP_NOMINEE ON (HRMS_EMP_NOMINEE.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND NOM_TYPE='P')"
                                +" LEFT JOIN HRMS_EMP_FML ON (HRMS_EMP_NOMINEE.NOM_NOMINEE=FML_ID)";
                        //if (penReg.getCheckBrn().equals("Y"))
                                empQuery += " LEFT JOIN HRMS_CENTER ON   HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER ";
        
                        //if(penReg.getCheckDept().equals("Y"))
                                empQuery += " left JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT ";
        
                                empQuery += " LEFT JOIN HRMS_SALARY_MISC ON HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID  "
                                                + " LEFT JOIN HRMS_BANK ON HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_PENSION_BANK  ";
        
                        //if (penReg.getCheckDesg().equals("Y"))
                                empQuery += " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK ";
        
                        if (penReg.getCheckRetDate().equals("Y"))
                                empQuery += " INNER JOIN HRMS_PENSION_EMPLOYEES ON HRMS_PENSION_EMPLOYEES.PENS_EMP_ID = HRMS_PENSION_"+ penReg.getYear()+".PENS_EMP_ID";
                        
                                empQuery += " WHERE PENS_LEDGER_CODE IN (" + ledgerCode + ")";
                                if(!penReg.getOnHold().equals("A")){
                                	empQuery += " AND PENS_ONHOLD = '"+penReg.getOnHold()+"' ";
                                }
                                
                                
                        if (!penReg.getBranchCode().equals(""))
                                empQuery += "AND HRMS_EMP_OFFC.EMP_CENTER = " + penReg.getBranchCode();
        
                        if (!penReg.getDeptCode().equals(""))
                                empQuery += "AND HRMS_EMP_OFFC.EMP_DEPT = " + penReg.getDeptCode();
                        
                        if (!penReg.getPayBillNo().equals(""))
                                empQuery += "AND HRMS_EMP_OFFC.EMP_PAYBILL = " + penReg.getPayBillNo();
                
                        if (!penReg.getDesgCode().equals(""))
                                empQuery += "AND HRMS_EMP_OFFC.EMP_RANK = " + penReg.getDesgCode();
        
                                empQuery += " ORDER BY ";
        
                        if (penReg.getChkBrnOrder().equals("Y"))
                                empQuery += " CENTER_NAME , ";
        
                        if (penReg.getChkDeptOrder().equals("Y"))
                                empQuery += "  DEPT_NAME, ";
        
                        if (penReg.getChkDesgOrder().equals("Y"))
                                empQuery += " RANK_NAME , ";
        
                       // empQuery += "  UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
                        empQuery += "  UPPER(HRMS_EMP_OFFC.EMP_TOKEN)";
        
                        Object [][] empData = getSqlModel().getSingleResult(empQuery);
                       
                        logger.info("empData.LENGTH " + empData.length);

                        if (empData != null && empData.length > 0) {
                        	 for (int i = 0; i < empData.length; i++) {
                        		 if(String.valueOf(empData[i][1]).startsWith("0") && !penReg.getReport().equals("Pdf") )
                             	empData[i][1] ="'"+String.valueOf(empData[i][1]);
     						}
                                String creditQuery="";
                                String creditQuery1=" ";
                                String creditQuery2="";String creditQuery3="",creditQuery4="";;
                                String count="0",totalCreditQuery1="",totalCreditQuery="",groupBy="";
                                int sumStart=0;
                                Object[][] creditHead = getPensionHeads(ledgerCode,penReg.getYear());
                                
                                for (int i = 0; i < creditHead.length; i++) {
                                        if(i==0){
                                                creditQuery1 += "select a"+i+".PENS_EMP_ID,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,'',a"+i+".PENS_AMOUNT,";
                                                
                                                creditQuery2 += " from (select PENS_EMP_ID,PENS_AMOUNT from HRMS_PENSION_CREDIT_"+penReg.getYear()+" " +
                                                                                " where PENS_CREDIT_CODE = "+String.valueOf(creditHead[i][0])+" and PENS_LEDGER_CODE in ("+ledgerCode+")) a"+i+" ";
                                                
                                                totalCreditQuery1 += "select ' ','Total  [' || count(*)|| '] ',sum(a"+i+".PENS_AMOUNT),";
                                                
                                                creditQuery4 = "inner join hrms_emp_offc on hrms_emp_offc.EMP_ID = a"+i+".PENS_EMP_ID "
                                                				+" INNER JOIN HRMS_PENSION_"+penReg.getYear()+" ON HRMS_EMP_OFFC.EMP_ID = HRMS_PENSION_"+penReg.getYear()+".PENS_EMP_ID  AND PENS_LEDGER_CODE in ("+ledgerCode+") ";
                                                
                                                creditQuery4 += " INNER JOIN HRMS_CENTER ON(CENTER_ID=EMP_CENTER) "
                                                        +" left JOIN HRMS_DEPT ON(DEPT_ID=EMP_DEPT)"
                                                        +" LEFT JOIN HRMS_RANK ON(RANK_ID=EMP_RANK)";
                                                
                                                creditQuery4 += " WHERE  1=1 ";
                                                if(!penReg.getOnHold().equals("A")){
                                                	creditQuery4 += " AND PENS_ONHOLD = '"+penReg.getOnHold()+"' ";
                                                }
                                                
                                                if (!penReg.getBranchCode().equals(""))
                                                        creditQuery4 += "AND HRMS_EMP_OFFC.EMP_CENTER = " + penReg.getBranchCode();
                                
                                                if (!penReg.getDeptCode().equals(""))
                                                        creditQuery4 += "AND HRMS_EMP_OFFC.EMP_DEPT = " + penReg.getDeptCode();
                                        
                                                if (!penReg.getDesgCode().equals(""))
                                                        creditQuery4 += "AND HRMS_EMP_OFFC.EMP_RANK = " + penReg.getDesgCode();
                                                
                                                
                                                creditQuery4 += " ORDER BY ";
                                                                        
                                                if (penReg.getChkBrnOrder().equals("Y"))
                                                        creditQuery4 += " CENTER_NAME , ";
                                                        
                                                if (penReg.getChkDeptOrder().equals("Y"))
                                                        creditQuery4 += "  DEPT_NAME, ";
                                                        
                                                if (penReg.getChkDesgOrder().equals("Y"))
                                                        creditQuery4 += " RANK_NAME , ";
                                                        
                                                        //creditQuery4 += "  UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME)";
                                                        creditQuery4 += "  UPPER(HRMS_EMP_OFFC.EMP_TOKEN)";
                                                                                        
                                                groupBy="inner join hrms_emp_offc on hrms_emp_offc.EMP_ID = a"+i+".PENS_EMP_ID ";
                                                                        
                                                count=String.valueOf(i);
                                        }
                                        else{
                                                
                                                creditQuery1 += "a"+i+".PENS_AMOUNT,";
                                                totalCreditQuery1 += "sum(a"+i+".PENS_AMOUNT),";
                                                creditQuery3 += " INNER JOIN (select PENS_EMP_ID,PENS_AMOUNT from HRMS_PENSION_CREDIT_"+penReg.getYear()+" WHERE PENS_CREDIT_CODE = "+String.valueOf(creditHead[i][0])+" AND PENS_LEDGER_CODE IN ("+ledgerCode+")) a"+i+" on a"+count+".PENS_EMP_ID = a"+i+".PENS_EMP_ID ";
                                        }
                                                                                                
                                }
                                
                                creditQuery = creditQuery1.substring(0,creditQuery1.length()-1) + creditQuery2 +creditQuery3+creditQuery4;
                                totalCreditQuery = totalCreditQuery1.substring(0,totalCreditQuery1.length()-1) + creditQuery2 +creditQuery3+groupBy;
                                logger.info("creditQuery "+creditQuery );
                                logger.info("total Crdit query"+totalCreditQuery);
                                logger.info("empData[0].length"+empData[0].length);
                                String [] header = new String[(empData[0].length - 6)+ creditHead.length];
                                
                                int [] cellwidth = new int[header.length];
                                int [] alignment = new int[header.length];
                                
                                header[0] = "Sr. No.";
                                header[1] = "Emp Id";
                                header[2] = "Employee Name";
                                header[3] = "Nominee Name";
                                
                                cellwidth[0] = 5;
                                cellwidth[1] = 10;
                                cellwidth[2] = 25;
                                cellwidth[3] = 25;

                                alignment[0] = 0;
                                alignment[1] = 0;
                                alignment[2] = 0;
                                alignment[3] = 0;
                                
                                int headCount = 4;
                                int dataCount = 0;
                                
                                if (penReg.getCheckBrn().equals("Y")) {
                                        header[headCount] = "Brn";
                                        cellwidth[headCount] = 10;
                                        alignment[headCount] = 0;
                                        headCount = headCount + 1;
                                }
                                if (penReg.getCheckDept().equals("Y")) {
                                        header[headCount] = "Dept";
                                        cellwidth[headCount] = 10;
                                        alignment[headCount] = 0;
                                        headCount = headCount + 1;
                                }
                                if (penReg.getCheckDob().equals("Y")) {
                                        header[headCount] = "DOB";
                                        cellwidth[headCount] = 10;
                                        alignment[headCount] = 0;
                                        headCount = headCount + 1;
                                }
                                if (penReg.getCheckRetDate().equals("Y")) {
                                        header[headCount] = "DOR";
                                        cellwidth[headCount] = 10;
                                        alignment[headCount] = 0;
                                        headCount = headCount + 1;
                                }
                                if (penReg.getCheckBank().equals("Y")) {
                                        header[headCount] = "Bank";
                                        cellwidth[headCount] = 10;
                                        alignment[headCount] = 0;
                                        headCount = headCount + 1;
                                }
                                if (penReg.getCheckPan().equals("Y")) {
                                        header[headCount] = "PAN No.";
                                        cellwidth[headCount] = 10;
                                        alignment[headCount] = 0;
                                        headCount = headCount + 1;
                                }
                                if (penReg.getCheckDoj().equals("Y")) {
                                        header[headCount] = "DOJ";
                                        cellwidth[headCount] = 10;
                                        alignment[headCount] = 0;
                                        headCount = headCount + 1;
                                }
                                if (penReg.getCheckDesg().equals("Y")) {
                                        header[headCount] = "Desg";
                                        cellwidth[headCount] = 10;
                                        alignment[headCount] = 0;
                                        headCount = headCount + 1;
                                }
                                if (penReg.getCheckGender().equals("Y")) {
                                        header[headCount] = "Gender";
                                        cellwidth[headCount] = 8;
                                        alignment[headCount] = 0;
                                        headCount = headCount + 1;
                                }
                                
                                header[headCount] = "AccNo.";
                                cellwidth[headCount] = 15;
                                alignment[headCount] = 0;
                                headCount = headCount + 1;
                                
                                for (int i = 0; i < creditHead.length; i++) {
                                        
                                        header[headCount] = String.valueOf(creditHead[i][1]).trim();
                                        cellwidth[headCount] = 10;
                                        alignment[headCount] = 2;
                                        headCount = headCount + 1;
                                        
                                }
                                /*header[headCount] = "Basic";
                                cellwidth[headCount] = 10;
                                alignment[headCount] = 0;
                                headCount = headCount + 1;
                                
                                header[headCount] = "D.P.";
                                cellwidth[headCount] = 10;
                                alignment[headCount] = 0;
                                headCount = headCount + 1;
                                
                                header[headCount] = "D.A.";
                                cellwidth[headCount] = 10;
                                alignment[headCount] = 0;
                                headCount = headCount + 1;*/
                                
                                header[headCount] = "ARREAR";
                                cellwidth[headCount] = 10;
                                alignment[headCount] = 2;
                                headCount = headCount + 1;
                                
                                header[headCount] = "PENSION AMT";
                                cellwidth[headCount] = 10;
                                alignment[headCount] = 2;
                                headCount = headCount + 1;
                                
                                header[headCount] = "COMMU";
                                cellwidth[headCount] = 10;
                                alignment[headCount] = 2;
                                headCount = headCount + 1;
                                
                                header[headCount] = "RECOVERY";
                                cellwidth[headCount] = 10;
                                alignment[headCount] = 2;
                                headCount = headCount + 1;
                                
                                header[headCount] = "MISC";
                                cellwidth[headCount] = 10;
                                alignment[headCount] = 2;
                                headCount = headCount + 1;
                                
                                header[headCount] = "PENSION PAYABLE";
                                cellwidth[headCount] = 10;
                                alignment[headCount] = 2;
                                headCount = headCount + 1;
                                
                                Object [][] creditObject = getSqlModel().getSingleResult(creditQuery);
                                
                                Object [][] totalObject = getSqlModel().getSingleResult(totalCreditQuery);
                                
                                Object [][] repData = new Object [empData.length][header.length + 6];
                                repData = intSpace(repData);
                                
                                for (int e = 0; e < empData.length; e++) {
                                                                                
                                        repData[e][0] = String.valueOf(e + 1);
                                        repData[e][1] = String.valueOf(empData[e][1]);
                                        repData[e][2] = String.valueOf(empData[e][2]);
                                        System.out.println("String.valueOf(empData[e][3])====== " + String.valueOf(empData[e][3]));
                                        repData[e][3] = setEmpName(String.valueOf(empData[e][3]),String.valueOf(empData[e][2]));
                                        
                                        headCount = 4;
                                        dataCount = 4;
                                        if (penReg.getCheckBrn().equals("Y")) {
                                                repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                                headCount = headCount + 1;
                                                dataCount = dataCount + 1;
                                        }
                                        if (penReg.getCheckDept().equals("Y")) {
                                                repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                                headCount = headCount + 1;
                                                dataCount = dataCount + 1;
                                        }
                                        if (penReg.getCheckDob().equals("Y")) {
                                                repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                                headCount = headCount + 1;
                                                dataCount = dataCount + 1;
                                        }
                                        if (penReg.getCheckRetDate().equals("Y")) {
                                                repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                                headCount = headCount + 1;
                                                dataCount = dataCount + 1;
                                        }
                                        if (penReg.getCheckBank().equals("Y")) {
                                                repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                                headCount = headCount + 1;
                                                dataCount = dataCount + 1;
                                        }
                                        if (penReg.getCheckPan().equals("Y")) {
                                                repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                                headCount = headCount + 1;
                                                dataCount = dataCount + 1;
                                        }
                                        if (penReg.getCheckDoj().equals("Y")) {
                                                repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                                headCount = headCount + 1;
                                                dataCount = dataCount + 1;
                                        }
                                        if (penReg.getCheckDesg().equals("Y")) {
                                                repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                                headCount = headCount + 1;
                                                dataCount = dataCount + 1;
                                        }
                                        if (penReg.getCheckGender().equals("Y")) {
                                                repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                                headCount = headCount + 1;
                                                dataCount = dataCount + 1;
                                        }
                                        
                                        repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                        headCount = headCount + 1;
                                        dataCount = dataCount + 1;
                                        
                                        sumStart=headCount;
                                        for (int i = 0; i < creditHead.length; i++) {
                                                
                                                repData[e][headCount] = String.valueOf(creditObject[e][3+i]);
                                                headCount = headCount + 1;
                                                
                                        }
                                        
                                        repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                        headCount = headCount + 1;
                                        dataCount = dataCount + 1;
                                        
                                        repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                        headCount = headCount + 1;
                                        dataCount = dataCount + 1;
                                        
                                        repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                        headCount = headCount + 1;
                                        dataCount = dataCount + 1;
                                        
                                        repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                        headCount = headCount + 1;
                                        dataCount = dataCount + 1;
                                        
                                        repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                        headCount = headCount + 1;
                                        dataCount = dataCount + 1;
                                        
                                        repData[e][headCount] = String.valueOf(empData[e][dataCount]);
                                        headCount = headCount + 1;
                                        dataCount = dataCount + 1;
                                                                        
                                        repData[e][headCount] = String.valueOf(empData[e][empData[0].length - 6]);
                                        repData[e][headCount + 1] = String.valueOf(empData[e][empData[0].length - 5]);
                                        repData[e][headCount + 2] = String.valueOf(empData[e][empData[0].length - 4]);
                                        repData[e][headCount + 3] = String.valueOf(empData[e][empData[0].length - 3]);
                                        repData[e][headCount + 4] = String.valueOf(empData[e][empData[0].length - 2]);
                                        repData[e][headCount + 5] = String.valueOf(empData[e][empData[0].length - 1]);

                                }
                                
                                vect.add(0, header);
                                vect.add(1, repData);
                                vect.add(2, cellwidth);
                                vect.add(3, alignment);
                                vect.add(4, sumStart);
                                
                        }
        
                }catch(Exception e){
                        logger.error("Error in getSalary "+e);
                        e.printStackTrace();
                }
                
                return vect;
        }
        public ReportGenerator printAllReport(Object [][] dataObj,String [] colsName, int [] width,int [] alignment,int recPerPage,ReportGenerator rg,
                        boolean brnCheck,boolean  deptCheck,boolean desgCheck,int startSum,String reportType,String date,PensionRegistereGovBean penReg){
                
                try{
                        int empCount=0;
                        if(dataObj.length < recPerPage)
                                recPerPage=dataObj.length;
                        Object grndTot [][] =new Object[1][colsName.length];
                        grndTot =intSpace(grndTot);
                        Object[][] totObj = new Object[1][colsName.length];
                        totObj=intSpace(totObj);
                        totObj[0][1] = "Total";
                        Object [][] printObj=new Object[recPerPage][dataObj[0].length];
                        
                        for (int i = 0; i < dataObj.length; i++) {
                                
                                if(empCount < recPerPage){
                                        
                                        printObj[empCount]=dataObj[i];
                                        empCount++;
                                        
                                }
                                for (int j = startSum; j < colsName.length; j++) {

                                        if (String.valueOf(totObj[0][j]).equals(""))
                                                totObj[0][j] = "0";

                                        totObj[0][j] = formatter.format((Double.parseDouble(String.valueOf(totObj[0][j])) 
                                                                                + Double.parseDouble(checkNull(String.valueOf(dataObj[i][j])))));
                                        
                                        if (String.valueOf(grndTot[0][j]).equals(""))
                                                grndTot[0][j] = "0";

                                        grndTot[0][j] = formatter.format((Double.parseDouble(String.valueOf(grndTot[0][j])) 
                                                                                + Double.parseDouble(checkNull(String.valueOf(dataObj[i][j])))));

                                        
                                        }
                                if(empCount==(recPerPage) || i == dataObj.length-1){    
                                      
                                	rg.addTextBold(penReg.getDivName(), 0, 1, 1,12);
                                	rg.addTextBold("Pension Register for "
                                                        + Utility.month(Integer.parseInt(penReg.getMonth()))
                                                        + " , " + penReg.getYear()+"\n", 0, 1, 1,12);
                                        
                                        
                                        
                                       // rg.addText("Date: " + date+"\n", 0, 2, 0,12);
                                        //rg.addText("\n", 0, 0, 0);
                                       // rg.addTextBold("Pension Details", 0, 0, 0,12);
                                        //rg.addText("\n", 0, 0, 0);
                                        //rg.tableBody(colsName, printObj, width, alignment);
                                        Object[][] finalPrintObj =Utility.removeColumns(printObj, new int[]{0});
                    					Object[][] finalTotObj =Utility.removeColumns(totObj, new int[]{0});
                    					int newWidth[]=new int[width.length-1]; 
                    					
                    					int newAlignment[]=new int[alignment.length-1]; 
                    					String newColumnName[]=new String[colsName.length-1]; 
                    					
                    					for (int j = 1; j < width.length; j++) {
                    						newWidth[j-1]=width[j];
                    						newAlignment[j-1]=alignment[j];
                    						newColumnName[j-1]=colsName[j];
                    					}
                    					if(reportType.equalsIgnoreCase("pdf")){
                    						rg.tableBodyBold(newColumnName, finalPrintObj, newWidth,newAlignment,11);
                    						rg.tableBody(finalTotObj, newWidth, newAlignment,11);
                    					}else
                    					{
                    						rg.xlsTableBody(newColumnName, finalPrintObj, width,alignment);
                    						rg.tableBody(finalTotObj, newWidth, newAlignment,11);
                    						
                    					}
                                        //rg.tableBody(colsName, printObj, width, alignment);
                                                                        
                                        if(empCount == recPerPage)
                                                rg.pageBreak();
                                        
                                        int pendingCount = dataObj.length - (i+1);
                                        if( pendingCount!=0 && pendingCount < (recPerPage))
                                                printObj = new Object[pendingCount][dataObj[0].length];
                                        
                                        printObj = intSpace(printObj);
                                        totObj=intSpace(totObj);
                                        totObj[0][1] = "Total";
                                        
                                        empCount=0;
                                }
                        }
                        grndTot [0][1]="Grand Total";
                        rg.addText("", 0, 1, 1);
                        
                        int newWidth[]=new int[width.length-1]; 
            			
            			int newAlignment[]=new int[alignment.length-1]; 
            			
            			for (int j = 1; j < width.length; j++) {
            				newWidth[j-1]=width[j];
            				newAlignment[j-1]=alignment[j];
            			}
            			Object [][] finalGrndTot =Utility.removeColumns(grndTot, new int[]{0});
            			rg.tableBody(finalGrndTot,newWidth , newAlignment,11);
                        
                }catch(Exception e){
                        e.printStackTrace();
                        
                }
        return rg;
    }
        public ReportGenerator setReport(Object[][] salData, ReportGenerator rg,
                        boolean brnCheck, boolean deptCheck, boolean desgCheck ,String[] colNames,
                        int[] cellwidth, int[] alignmnet, int startSum,String reportType,String date,PensionRegistereGovBean penReg ) {
                try {
                        Object grndTot [][] =new Object[1][colNames.length];
                        grndTot =intSpace(grndTot);
                        Vector braVector = new Vector();
                        Object[][] braTotObj = new Object[1][colNames.length];
                        braTotObj = intSpace(braTotObj);
                        int count = 0;
                        int check=0;
                        String brnCode = String.valueOf(salData[0][salData[0].length - 6]);
                        String deptCode = String.valueOf(salData[0][salData[0].length - 5]);
                        String desgCode = String.valueOf(salData[0][salData[0].length - 4]);
                        braTotObj[0][1] = "Total";
                        String printString="";
                        for (int i = 0; i < salData.length; i++) {

                                if (i < salData.length 
                                                && checkCondition(brnCheck, deptCheck, desgCheck,
                                                                brnCode, String.valueOf(salData[i][salData[0].length - 6]),
                                                                deptCode, String.valueOf(salData[i][salData[0].length - 5]), 
                                                                desgCode, String.valueOf(salData[i][salData[0].length - 4]))){

                                        braVector.add(salData[i]);
                                        count++;
                                        for (int j = startSum; j < colNames.length; j++) {

                                        if (String.valueOf(braTotObj[0][j]).equals(""))
                                                braTotObj[0][j] = "0";

                                                braTotObj[0][j] = formatter.format((Double.parseDouble(String.valueOf(braTotObj[0][j])) 
                                                                                + Double.parseDouble(checkNull(String.valueOf(salData[i][j])))));
                                        
                                        
                                                if (checkNull(String.valueOf(grndTot[0][j])).equals(""))
                                                grndTot[0][j] = "0";

                                                grndTot[0][j] = formatter.format((Double.parseDouble(String.valueOf(grndTot[0][j])) 
                                                                                        + Double.parseDouble(checkNull(String.valueOf(salData[i][j])))));

                                        }

                                } else if (count < salData.length) {
                                        
                                        Object[][] reportObj = new Object[braVector.size()][colNames.length];
                                        for (int j = 0; j < braVector.size(); j++) {
                                                reportObj[j] = (Object[]) braVector.get(j);
                                        }
                                        if (reportObj != null && reportObj.length > 0) {
                                        		rg.addTextBold(penReg.getDivName(), 0, 1, 1,12);
                                                rg.addTextBold("Pension Register for "
                                                                + Utility.month(Integer.parseInt(penReg.getMonth()))
                                                                + " , " + penReg.getYear()+"\n", 0, 1, 1,12);
                                                
                                                
                                                
                                               // rg.addText("Date: " + date+"\n", 0, 2, 0,12);
                                                //rg.addText("\n", 0, 0, 0);
                                                //rg.addTextBold("Pension Details", 0, 0, 0,12);
                                                //rg.addText("\n", 0, 0, 0);
                                                if (brnCheck)
                                                        printString += "Branch : "+ String.valueOf(salData[i - 1][salData[0].length - 3])+"   ";
                                                if (deptCheck)
                                                        printString += "Department : "+ String.valueOf(salData[i - 1][salData[0].length - 2])+"   ";
                                                if (desgCheck)
                                                        printString += "Designation : "+ String.valueOf(salData[i - 1][salData[0].length - 1])+"   ";

                                                rg.addTextBold(printString, 0, 0, 0,12);
                                                rg.addText("\n", 0, 0, 0);
                                                //rg.tableBody(colsName, printObj, width, alignment);   
                                                
                                                if(check==0){
                                                        
                                                        if(reportType.equalsIgnoreCase("pdf"))
                                                                rg.tableBodyBold(colNames, reportObj, cellwidth,alignmnet,12);
                                                        else
                                                                rg.xlsTableBody(colNames, reportObj, cellwidth,alignmnet);
                                                }
                                                else
                                                        rg.tableBody(reportObj, cellwidth, alignmnet,12);
                                                                                                
                                                rg.tableBody(braTotObj, cellwidth, alignmnet,12);
                                                
                                                check++;
                                                printString="";
                                                
                                        }

                                        braVector = new Vector();
                                        braTotObj = new Object[1][colNames.length];
                                        braTotObj = intSpace(braTotObj);
                                        braTotObj[0][1] = "Total";
                                        brnCode = String.valueOf(salData[i][salData[0].length - 6]);
                                        deptCode = String.valueOf(salData[i][salData[0].length - 5]);
                                        desgCode = String.valueOf(salData[i][salData[0].length - 4]);
                                        i--;

                                }

                        }
                        Object[][] reportObj = new Object[braVector.size()][colNames.length];
                        for (int j = 0; j < braVector.size(); j++) {
                                reportObj[j] = (Object[]) braVector.get(j);
                        }
                        if (reportObj != null && reportObj.length > 0) {
                                                
                                rg.addText("\n", 0, 0, 0);
                                if (brnCheck)
                                        printString += "Branch : "+ String.valueOf(reportObj[0][reportObj[0].length - 3])+"   ";
                                if (deptCheck)
                                        printString += "Department : "+ String.valueOf(reportObj[0][reportObj[0].length - 2])+"   ";
                                if (desgCheck)
                                        printString += "Designation : "+ String.valueOf(reportObj[0][reportObj[0].length - 1])+"   ";

                                rg.addTextBold(printString, 0, 0, 0,12);
                                rg.addText("\n", 0, 0, 0);
                                
                                if(check==0){
                                        if(reportType.equalsIgnoreCase("pdf"))
                                                rg.tableBodyBold(colNames, reportObj, cellwidth,alignmnet,12);
                                        else
                                                rg.xlsTableBody(colNames, reportObj, cellwidth,alignmnet);
                                }else
                                        rg.tableBody(reportObj, cellwidth, alignmnet,12);
                                
                                rg.tableBody(braTotObj, cellwidth, alignmnet,12);
                                                                
                        }
                        
                        try{
                                                        
                        //grndTot =intSpace(grndTot);
                        grndTot [0][1]="Grand Total";
                        rg.addText("", 0, 1, 1);
                        rg.tableBody(grndTot, cellwidth, alignmnet,12);

                        
                        }catch (Exception e) {
                                logger.error("exception in grand total");
                                e.printStackTrace();
                        }
                        
                } catch (Exception e) {
                        e.printStackTrace();
                }

                return rg;
        }
        public boolean checkCondition(boolean brnFlag, boolean deptFlag,boolean desgFlag
                        ,String brn1, String brn2,String dept1, String dept2,String desg1, String desg2 ) {

                boolean flag = ((brnFlag && deptFlag && desgFlag && brn1.equals(brn2) && dept1.equals(dept2) && desg1.equals(desg2))
                                                || (brnFlag && deptFlag && !desgFlag && brn1.equals(brn2) && dept1.equals(dept2))
                                                || (brnFlag && desgFlag && !deptFlag && brn1.equals(brn2) && desg1.equals(desg2))
                                                || (deptFlag && desgFlag && !brnFlag && dept1.equals(dept2) && desg1.equals(desg2))
                                                || (brnFlag && !deptFlag && !desgFlag && brn1.equals(brn2))
                                                || (deptFlag && !brnFlag && !desgFlag && dept1.equals(dept2))
                                                || (desgFlag && !brnFlag && !deptFlag && desg1.equals(desg2))
                );
                //logger.info("flag-----------" + flag);
                return flag;
        }
        public Object[][] getPensionHeads(String ledgerCode,String year){
                
                Object[][] pensObj = null;
                try {
                        String pensionQUery =" SELECT DISTINCT PENS_CREDIT_CODE,CREDIT_ABBR from HRMS_PENSION_CREDIT_"+year
                                                                +" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PENSION_CREDIT_"+year+".PENS_CREDIT_CODE " 
                                                                +" WHERE PENS_LEDGER_CODE IN ("+ledgerCode+")"
                                                                +" ORDER BY PENS_CREDIT_CODE ";
                        
                        pensObj = getSqlModel().getSingleResult(pensionQUery);
                        
                } catch (Exception e) {
                        logger.info("Error while getting the pension heads"+e);
                        
                }                                               
                return pensObj;
        }
        public Object[][] intSpace(Object[][] tempObj){
                try {
                        for (int k = 0; k < tempObj.length; k++) {
                                for (int j = 0; j < tempObj[0].length; j++) {
                                        tempObj[k][j] = "";
                                }
                        }
                } catch (Exception e) {
                        
                }
                return tempObj;
        }
        public Object[][] intZero(Object[][] tempObj){
                try {
                        for (int k = 0; k < tempObj.length; k++) {
                                for (int j = 0; j < tempObj[0].length; j++) {
                                        tempObj[k][j] = "0";
                                }
                        }
                } catch (Exception e) {
                        
                }
                return tempObj;
        }
        public static String checkNull(String result){
                if(result ==null ||result.equals("null")){
                        return "0";
                }
                else{
                        return result;
                }
        }
        
        public static String setEmpName(String result,String value){
                if(result.trim() ==null ||result.trim().equals("null")||result.trim().equals("")){
                        return value;
                }
                else{
                        return result;
                }
        }
}

