/*      */ package org.paradyne.model.payroll.incometax;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Vector;
/*      */ import javax.servlet.ServletContext;
/*      */ import javax.servlet.http.HttpServletResponse;
/*      */ import jxl.Workbook;
/*      */ import jxl.write.Label;
/*      */ import jxl.write.Number;
/*      */ import jxl.write.NumberFormats;
/*      */ import jxl.write.WritableCellFormat;
/*      */ import jxl.write.WritableSheet;
/*      */ import jxl.write.WritableWorkbook;
/*      */ import org.apache.log4j.Logger;
/*      */ import org.paradyne.bean.payroll.incometax.Form24;
/*      */ import org.paradyne.lib.ModelBase;
/*      */ import org.paradyne.lib.SqlModel;
/*      */ 
/*      */ public class Form24Model extends ModelBase
/*      */ {
/*   27 */   static Logger logger = Logger.getLogger(Form24Model.class);
/*      */ 
/*   29 */   WritableWorkbook copy = null;
/*   30 */   Workbook workbook = null;
/*   31 */   int startMth = 0;
/*      */ 
/*   34 */   String endMth = "0";
/*   35 */   String year = "0";
/*   36 */   Object[][] challanData = null;
/*      */ 
/*      */   public String execute(Form24 form24, HttpServletResponse response)
/*      */   {
/*      */     try
/*      */     {
/*   43 */       String query = "SELECT NVL(DIV_TANNO,' '),nvl(DIV_PANNO,' '),NVL(DIV_NAME,' '),NVL(DIV_ADDRESS1,' '),NVL(DIV_ADDRESS2,' '),  NVL(DIV_ADDRESS3,' '),  NVL(L1.LOCATION_NAME,' '),  DIV_PINCODE,NVL(L2.LOCATION_NAME,' '),DIV_TELEPHONE,DIV_EMAIL  FROM HRMS_DIVISION   LEFT JOIN HRMS_LOCATION L1 ON L1.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID  LEFT JOIN HRMS_LOCATION L2 ON L1.LOCATION_PARENT_CODE = L2.LOCATION_CODE  WHERE DIV_ID=" + 
/*   50 */         form24.getDivCode();
/*   51 */       Object[][] dataDeductor = getSqlModel().getSingleResult(query);
/*      */ 
/*   53 */       if (dataDeductor == null) {
/*   54 */         return "Please add address details in division master";
/*      */       }
/*   56 */       if (dataDeductor.length == 0) {
/*   57 */         return "Please add address details in division master";
/*      */       }
/*      */ 
/*   60 */       if ((String.valueOf(dataDeductor[0][0]).equals("null")) || (String.valueOf(dataDeductor[0][0]).equals(""))) {
/*   61 */         return "Please add TAN no in division master";
/*      */       }
/*   63 */       if ((String.valueOf(dataDeductor[0][1]).equals("null")) || (String.valueOf(dataDeductor[0][1]).equals(""))) {
/*   64 */         return "Please add PAN no in division master";
/*      */       }
/*   66 */       if ((String.valueOf(dataDeductor[0][3]).equals("null")) || (String.valueOf(dataDeductor[0][3]).equals(""))) {
/*   67 */         return "Please add address details in division master";
/*      */       }
/*   69 */       if ((String.valueOf(dataDeductor[0][9]).equals("null")) || (String.valueOf(dataDeductor[0][9]).equals(""))) {
/*   70 */         return "Please add telephone number in division master";
/*      */       }
/*   72 */       String stdTel = checkNull(String.valueOf(dataDeductor[0][9]));
/*   73 */       String[] telPhoneArrary = stdTel.split("-");
/*      */       try {
/*   75 */         if ((!telPhoneArrary[1].equals("")) && 
/*   76 */           (telPhoneArrary[0].equals("")))
/*   77 */           return "Please add STD code for telephone number in division master";
/*      */       }
/*      */       catch (Exception e) {
/*   80 */         return "Please add STD code for telephone number in division master";
/*      */       }
/*      */ 
/*   84 */       logger.info("dataDeductor[0][10]====" + dataDeductor[0][10]);
/*   85 */       if ((String.valueOf(dataDeductor[0][10]).equals("null")) || (String.valueOf(dataDeductor[0][10]).equals(""))) {
/*   86 */         return "Please add email id in division master";
/*      */       }
/*      */ 
/*   90 */       String addDetailQuery = " SELECT ROWNUM,  (ADD_1),HRMS_LOCATION.LOCATION_NAME,ADD_STATE,ADD_PINCODE,ADD_MOBILE,ADD_EMAIL   FROM HRMS_EMP_ADDRESS   LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_EMP_ADDRESS.ADD_CITY)  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID)   WHERE HRMS_EMP_ADDRESS.EMP_ID =" + 
/*   95 */         form24.getSignAuthEmpId() + " ";
/*   96 */       Object[][] addDetail = getSqlModel().getSingleResult(addDetailQuery);
/*      */ 
/*   98 */       if (addDetail == null) {
/*   99 */         return "Please add employee address details in HRM";
/*      */       }
/*  101 */       if (addDetail.length == 0) {
/*  102 */         return "Please add employee address details in HRM";
/*      */       }
/*      */ 
/*  105 */       if ((String.valueOf(addDetail[0][1]).equals("null")) || (String.valueOf(addDetail[0][1]).equals(""))) {
/*  106 */         return "Please add employee address details in HRM";
/*      */       }
/*  108 */       if ((String.valueOf(addDetail[0][2]).equals("null")) || (String.valueOf(addDetail[0][2]).equals(""))) {
/*  109 */         return "Please add employee City in HRM";
/*      */       }
/*  111 */       if ((String.valueOf(addDetail[0][3]).equals("null")) || (String.valueOf(addDetail[0][3]).equals(""))) {
/*  112 */         form24.setSignAuthEmpState("OTHERS");
/*  113 */         return "Please add employee State in HRM";
/*      */       }
/*  115 */       if ((String.valueOf(addDetail[0][4]).equals("null")) || (String.valueOf(addDetail[0][4]).equals(""))) {
/*  116 */         form24.setSignAuthEmpPin("0");
/*  117 */         return "Please add employee PinCode in HRM";
/*      */       }
/*  119 */       if ((String.valueOf(addDetail[0][5]).equals("null")) || (String.valueOf(addDetail[0][4]).equals(""))) {
/*  120 */         return "Please add employee Mobile No in HRM";
/*      */       }
/*  122 */       if ((String.valueOf(addDetail[0][6]).equals("null")) || (String.valueOf(addDetail[0][4]).equals(""))) {
/*  123 */         return "Please add employee Email Address in HRM";
/*      */       }
/*      */ 
/*  128 */       this.workbook = Workbook.getWorkbook(new File(getServletContext().getRealPath("/") + "form24/FrmQTemplate.xls"));
/*  129 */       response.setContentType("application/vnd.ms-excel");
/*  130 */       this.copy = Workbook.createWorkbook(response.getOutputStream(), this.workbook);
/*      */ 
/*  132 */       logger.info("REAL PATH: " + getServletContext().getRealPath("/"));
/*  133 */       setQuarterHeaderSheet(form24, response, dataDeductor, addDetail);
/*  134 */       if ((!form24.getQuarter().equals("June")) && (!form24.getQuarter().equals("September")) && (!form24.getQuarter().equals("December"))) {
/*  135 */         setQuarterSalary(form24, response);
/*      */       }
/*  137 */       setQuarterChallanSheet(form24, response);
/*  138 */       setQuarterAnnexure(form24, response);
/*  139 */       this.copy.write();
/*  140 */       this.copy.close();
/*      */     }
/*      */     catch (Exception e) {
/*  143 */       e.printStackTrace();
/*      */     }
/*  145 */     return "";
/*      */   }
/*      */ 
/*      */   private String convertEmpListToString(Object[][] empList)
/*      */   {
/*  156 */     String empId = "";
/*      */     try {
/*  158 */       for (int i = 0; i < empList.length; i++) {
/*  159 */         empId = empId + String.valueOf(empList[i][1]) + ",";
/*      */       }
/*      */ 
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  165 */       logger.error("exception in empList loop", e);
/*      */     }
/*      */ 
/*  169 */     empId = empId.substring(0, empId.length() - 1);
/*      */ 
/*  171 */     return empId;
/*      */   }
/*      */ 
/*      */   private void setQuarterAnnexure(Form24 form24, HttpServletResponse response)
/*      */   {
/*      */     try {
/*  177 */       WritableSheet sheet2 = this.copy.getSheet("Deductee");
/*  178 */       int k = 0;
/*  179 */       int d = 0;
/*  180 */       double total_taxable = 0.0D;
/*  181 */       double debAmt = 0.0D; double debAmt1 = 0.0D; double taxDeducted = 0.0D;
/*  182 */       for (int i = 0; i < this.challanData.length; i++) {
/*  183 */         d = 0;
/*  184 */         float challan_tds = 0.0F;
/*  185 */         float challan_sur = 0.0F;
/*  186 */         float challan_edu = 0.0F;
/*  187 */         float challan_tax = 0.0F;
/*      */ 
/*  191 */         String detailQuery = "SELECT HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE,HRMS_TAX_CHALLAN_DTL.EMP_ID,NVL(SAL_PANNO,'PANNOTAVBL'),TO_CHAR(EMP_FNAME||'  '||EMP_LNAME)  ,CHALLAN_TDS,HRMS_TAX_CHALLAN_DTL.CHALLAN_SURCHARGE,  CHALLAN_EDU_CESS,CHALLAN_TOTAL_TAX FROM HRMS_TAX_CHALLAN_DTL  INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE)  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_TAX_CHALLAN_DTL.EMP_ID)  LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)   WHERE HRMS_TAX_CHALLAN.CHALLAN_CODE=" + 
/*  197 */           String.valueOf(this.challanData[i][9]) + " AND CHALLAN_TDS >0 ORDER BY EMP_ID";
/*  198 */         logger.info("challanData[i][9]code     : " + this.challanData[i][9]);
/*  199 */         Object[][] empData = getSqlModel().getSingleResult(detailQuery);
/*      */ 
/*  201 */         if ((empData != null) && (empData.length > 0))
/*      */         {
/*  205 */           String empIdList = convertEmpListToString(empData);
/*  206 */           logger.info("print empIdList========" + empIdList);
/*  207 */           String query = getUnionQuery(String.valueOf(this.challanData[i][16]), String.valueOf(this.challanData[i][14]), form24.getDivCode());
/*  208 */           logger.info("print query========" + query);
/*  209 */           form24.setIncomeDataMap(getIncomeData(empIdList, query));
/*      */         }
/*      */         try
/*      */         {
/*  213 */           for (int j = 0; j < empData.length; j++) {
/*  214 */             int taxableAmount = 0;
/*  215 */             Object[][] incomeData = (Object[][])form24.getIncomeDataMap().get(
/*  216 */               String.valueOf(empData[j][1]));
/*      */ 
/*  220 */             if ((incomeData != null) && (incomeData.length > 0)) {
/*  221 */               taxableAmount = Integer.parseInt(String.valueOf(incomeData[0][1]));
/*      */             }
/*      */ 
/*  224 */             if (taxableAmount > 0) {
/*  225 */               d++;
/*  226 */               k++;
/*  227 */               Number label1 = new Number(0, k, d, new WritableCellFormat(NumberFormats.INTEGER));
/*  228 */               Number label2 = new Number(1, k, Integer.parseInt(String.valueOf(this.challanData[i][0])), new WritableCellFormat(NumberFormats.INTEGER));
/*      */ 
/*  230 */               Number label3 = new Number(2, k, Integer.parseInt(String.valueOf(empData[j][1])), new WritableCellFormat(NumberFormats.INTEGER));
/*      */ 
/*  232 */               Label label4 = new Label(5, k, String.valueOf(empData[j][2]));
/*  233 */               Label label5 = new Label(6, k, String.valueOf(empData[j][3]));
/*  234 */               Label label6 = new Label(7, k, checkNull(String.valueOf(this.challanData[i][15])));
/*  235 */               Number label7 = new Number(8, k, Integer.parseInt(String.valueOf(taxableAmount)), new WritableCellFormat(NumberFormats.INTEGER));
/*      */ 
/*  237 */               Number label8 = new Number(10, k, Integer.parseInt(String.valueOf(empData[j][4])), new WritableCellFormat(NumberFormats.INTEGER));
/*      */ 
/*  239 */               Number label9 = new Number(11, k, Integer.parseInt(String.valueOf(empData[j][5])), new WritableCellFormat(NumberFormats.INTEGER));
/*      */ 
/*  241 */               Number label10 = new Number(12, k, Integer.parseInt(String.valueOf(empData[j][6])), new WritableCellFormat(NumberFormats.INTEGER));
/*      */ 
/*  243 */               Number label11 = new Number(13, k, Integer.parseInt(String.valueOf(empData[j][7])), new WritableCellFormat(NumberFormats.INTEGER));
/*      */ 
/*  245 */               Number label12 = new Number(14, k, Integer.parseInt(String.valueOf(empData[j][7])), new WritableCellFormat(NumberFormats.INTEGER));
/*      */ 
/*  247 */               Label label13 = new Label(15, k, checkNull(String.valueOf(this.challanData[i][15])));
/*  248 */               Label label14 = new Label(16, k, checkNull(String.valueOf(this.challanData[i][7])));
/*      */ 
/*  250 */               sheet2.addCell(label1);
/*  251 */               sheet2.addCell(label2);
/*  252 */               sheet2.addCell(label3);
/*  253 */               sheet2.addCell(label4);
/*  254 */               sheet2.addCell(label5);
/*  255 */               sheet2.addCell(label6);
/*  256 */               sheet2.addCell(label7);
/*  257 */               sheet2.addCell(label8);
/*  258 */               sheet2.addCell(label9);
/*  259 */               sheet2.addCell(label10);
/*  260 */               sheet2.addCell(label11);
/*  261 */               sheet2.addCell(label12);
/*  262 */               sheet2.addCell(label13);
/*  263 */               sheet2.addCell(label14);
/*      */             }
/*      */           }
/*      */         }
/*      */         catch (Exception e)
/*      */         {
/*  269 */           logger.error("in deductee data");
/*  270 */           e.printStackTrace();
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  489 */       logger.info("exception in Annexure");
/*  490 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private String getUnionQuery(String year, String month, String divCode)
/*      */   {
/*  496 */     String query = "";
/*      */     try
/*      */     {
/*  499 */       query = "SELECT EMPID,ROUND(SUM(AMT)) FROM (  SELECT DISTINCT HRMS_SALARY_" + 
/*  500 */         year + ".EMP_ID AS EMPID,SUM(SAL_AMOUNT) AS AMT " + 
/*  501 */         " FROM HRMS_SAL_CREDITS_" + year + "  " + 
/*  502 */         " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SAL_CREDITS_" + year + ".SAL_CREDIT_CODE) " + 
/*  503 */         " INNER JOIN HRMS_SALARY_" + year + " ON (HRMS_SALARY_" + year + ".SAL_LEDGER_CODE = HRMS_SAL_CREDITS_" + year + ".SAL_LEDGER_CODE " + 
/*  504 */         " AND HRMS_SAL_CREDITS_" + year + ".EMP_ID = HRMS_SALARY_" + year + ".EMP_ID)  " + 
/*  505 */         " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_" + year + ".SAL_LEDGER_CODE) " + 
/*  506 */         " WHERE LEDGER_DIVISION=" + divCode + " AND LEDGER_YEAR=" + year + " AND LEDGER_MONTH=" + month + "  AND CREDIT_TAXABLE_FLAG = 'Y' " + 
/*  507 */         " AND HRMS_SALARY_" + year + ".EMP_ID NOT IN(SELECT SETTL_ECODE FROM HRMS_SETTL_HDR  " + 
/*  508 */         " INNER JOIN HRMS_RESIGN ON (HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO) " + 
/*  509 */         " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE) " + 
/*  510 */         " WHERE RESIGN_WITHDRAWN = 'N' AND TO_CHAR(SETTL_SETTLDT,'MM')=" + month + " AND TO_CHAR(SETTL_SETTLDT,'YYYY')=" + year + " " + 
/*  511 */         " AND EMP_DIV = " + divCode + ")  " + 
/*  512 */         " GROUP BY HRMS_SALARY_" + year + ".EMP_ID " + 
/*  513 */         " UNION " + 
/*  514 */         " SELECT ARREARS_EMP_ID AS EMPID,SUM(NVL(ARREARS_AMT,0))AS AMT " + 
/*  515 */         " FROM HRMS_ARREARS_CREDIT_" + year + "  " + 
/*  516 */         " INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ARREARS_CREDIT_" + year + ".ARREARS_CREDIT_CODE " + 
/*  517 */         " INNER JOIN HRMS_EMP_OFFC ON HRMS_ARREARS_CREDIT_" + year + ".ARREARS_EMP_ID = HRMS_EMP_OFFC.EMP_ID  " + 
/*  518 */         " WHERE  ARREARS_CODE IN (SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_PAID_MONTH =" + month + " " + 
/*  519 */         " AND ARREARS_PAID_YEAR = " + year + " AND ARREARS_DIVISION = " + divCode + ") AND CREDIT_TAXABLE_FLAG = 'Y'  " + 
/*  520 */         " GROUP BY ARREARS_EMP_ID " + 
/*  521 */         " UNION " + 
/*  522 */         " SELECT SETTL_ECODE AS EMPID, " + 
/*  523 */         " CEIL(SUM(SETTL_AMT)+ (SETTL_GRATUITY+ SETTL_REIMBURSE+ SETTL_LEAVE_ENCASH)) AS AMT  " + 
/*  524 */         " FROM HRMS_SETTL_CREDITS   " + 
/*  525 */         " INNER JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_CREDITS.SETTL_CODE) " + 
/*  526 */         " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE) " + 
/*  527 */         " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE) " + 
/*  528 */         " INNER JOIN HRMS_RESIGN ON (HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO) " + 
/*  529 */         " WHERE (SETTL_MTH_TYPE IN ('OH','CO')) AND RESIGN_WITHDRAWN = 'N' AND EMP_DIV = " + divCode + "  " + 
/*  530 */         " AND TO_CHAR(SETTL_SETTLDT,'MM')=" + month + " AND TO_CHAR(SETTL_SETTLDT,'YYYY')=" + year + " " + 
/*  531 */         " AND CREDIT_TAXABLE_FLAG = 'Y' " + 
/*  532 */         " GROUP BY SETTL_ECODE,SETTL_GRATUITY, SETTL_REIMBURSE,SETTL_LEAVE_ENCASH " + 
/*  533 */         " UNION " + 
/*  534 */         " SELECT HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID AS EMPID,NVL(SUM(ALLW_AMOUNT_FINAL),0) AS AMT  " + 
/*  535 */         " FROM HRMS_ALLOWANCE_HDR  " + 
/*  536 */         " INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_ALLOWANCE_EMP_DTL.ALLW_ID = HRMS_ALLOWANCE_HDR.ALLW_ID) " + 
/*  537 */         " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID)  " + 
/*  538 */         " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ALLOWANCE_HDR.ALLW_CREDIT_HEAD) " + 
/*  539 */         " AND TO_CHAR(ALLW_PROCESS_DATE,'MM')=" + month + " " + 
/*  540 */         " AND TO_CHAR(ALLW_PROCESS_DATE,'YYYY')=" + year + " AND EMP_DIV = " + divCode + " " + 
/*  541 */         " AND CREDIT_TAXABLE_FLAG = 'Y' " + 
/*  542 */         " GROUP BY HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID " + 
/*  543 */         " UNION " + 
/*  544 */         " SELECT HRMS_BONUS_EMP.EMP_ID AS EMPID,NVL(SUM(EMP_BONUS_AMT),0) AS AMT  " + 
/*  545 */         " FROM HRMS_BONUS_HDR  " + 
/*  546 */         " INNER JOIN HRMS_BONUS_EMP ON (HRMS_BONUS_HDR.BONUS_CODE = HRMS_BONUS_EMP.BONUS_CODE )   " + 
/*  547 */         " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_BONUS_EMP.EMP_ID)  " + 
/*  548 */         " INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_BONUS_HDR.PAY_CREDIT_CODE)  " + 
/*  549 */         " AND PAY_MONTH=" + month + " " + 
/*  550 */         " AND PAY_YEAR=" + year + " AND EMP_DIV = " + divCode + " " + 
/*  551 */         " AND CREDIT_TAXABLE_FLAG = 'Y' " + 
/*  552 */         " GROUP BY HRMS_BONUS_EMP.EMP_ID " + 
/*  553 */         " )GROUP BY EMPID";
/*      */     } catch (Exception e) {
/*  555 */       logger.error("exception in union challan query", e);
/*      */     }
/*  557 */     return query;
/*      */   }
/*      */ 
/*      */   private HashMap getIncomeData(String empIdList, String query) {
/*  561 */     Object[][] incomeData = (Object[][])null;
/*      */     try {
/*  563 */       incomeData = getSqlModel().getSingleResult(query);
/*      */     } catch (Exception e) {
/*  565 */       logger.error("exception in incomeData query", e);
/*      */     }
/*      */ 
/*  568 */     String[] empLength = empIdList.split(",");
/*      */ 
/*  570 */     HashMap incomeMap = convertObjectToHashMap(incomeData, empLength);
/*      */ 
/*  572 */     return incomeMap;
/*      */   }
/*      */ 
/*      */   private HashMap convertObjectToHashMap(Object[][] incomeData, String[] empLength) {
/*  576 */     HashMap dataMap = new HashMap();
/*  577 */     if (incomeData != null)
/*      */     {
/*  580 */       if (incomeData.length != 0)
/*      */       {
/*  584 */         Vector v = new Vector();
/*      */         try {
/*  586 */           for (int i = 0; i < empLength.length; i++) {
/*  587 */             String empId = "";
/*  588 */             empId = String.valueOf(empLength[i]);
/*  589 */             for (int j = 0; j < incomeData.length; j++) {
/*  590 */               if (String.valueOf(incomeData[j][0]).equals(empLength[i])) {
/*  591 */                 v.add(incomeData[j]);
/*      */               }
/*      */             }
/*  594 */             Object[][] data = new Object[v.size()][2];
/*  595 */             for (int k = 0; k < data.length; k++) {
/*  596 */               data[k] = ((Object[])v.get(k));
/*      */             }
/*  598 */             dataMap.put(empId, data);
/*  599 */             v = new Vector();
/*      */           }
/*      */         } catch (Exception e) {
/*  602 */           logger.error("exception in convertAllowanceObjectToHashMap", e);
/*      */         }
/*      */       }
/*      */     }
/*  606 */     return dataMap;
/*      */   }
/*      */ 
/*      */   private void setQuarterChallanSheet(Form24 form24, HttpServletResponse response) {
/*      */     try {
/*  611 */       String quarter = String.valueOf(form24.getQuarter());
/*      */ 
/*  613 */       String QUARTER_NUMBER = "0";
/*  614 */       if (quarter.equals("June")) {
/*  615 */         this.startMth = 4;
/*  616 */         this.endMth = "6";
/*  617 */         this.year = form24.getFromYear();
/*  618 */         QUARTER_NUMBER = "1";
/*  619 */       } else if (quarter.equals("September")) {
/*  620 */         this.startMth = 7;
/*  621 */         this.endMth = "9";
/*  622 */         this.year = form24.getFromYear();
/*  623 */         QUARTER_NUMBER = "2";
/*  624 */       } else if (quarter.equals("December")) {
/*  625 */         this.startMth = 10;
/*  626 */         this.endMth = "12";
/*  627 */         this.year = form24.getFromYear();
/*  628 */         QUARTER_NUMBER = "3";
/*  629 */       } else if (quarter.equals("March")) {
/*  630 */         this.startMth = 1;
/*  631 */         this.endMth = "3";
/*  632 */         this.year = form24.getToYear();
/*  633 */         QUARTER_NUMBER = "4";
/*      */       }
/*      */ 
/*  638 */       Object[] ob = (Object[])null;
/*  639 */       String query = "";
/*      */ 
/*  672 */       ob = new Object[4];
/*  673 */       ob[0] = form24.getDivCode();
/*  674 */       ob[1] = this.year;
/*  675 */       ob[2] = this.startMth;
/*  676 */       ob[3] = this.endMth;
/*      */ 
/*  678 */       query = "SELECT ROWNUM,NVL(CHALLAN_TAX,0),NVL(CHALLAN_SURCHARGE,0),  NVL(CHALLAN_EDUCESS,0),NVL(CHALLAN_TOTALTAX,0),CHALLAN_CHQNO,CHALLAN_BANK,TO_CHAR(CHALLAN_DATE,'DD-MM-YYYY'),  CHALLAN_NO,CHALLAN_CODE,TO_CHAR(CHALLAN_DATE,'DD-MM-YYYY'),CHALLAN_BOOK_ENTRY, NVL(CHALLAN_INT_AMT,0),NVL(CHALLAN_OTHR_AMT,0),CHALLAN_MONTH, TO_CHAR(CHALLAN_DATE_PAYMENT,'DD-MM-YYYY'),CHALLAN_YEAR   FROM HRMS_TAX_CHALLAN   WHERE CHALLAN_DIVISION_ID=?  AND CHALLAN_YEAR =? AND CHALLAN_MONTH BETWEEN ? AND ?  ORDER BY CHALLAN_MONTH ASC";
/*      */ 
/*  688 */       this.challanData = getSqlModel().getSingleResult(query, ob);
/*      */ 
/*  691 */       WritableSheet sheet4 = this.copy.getSheet("Challan");
/*      */ 
/*  697 */       String bookEntry = "";
/*  698 */       if (this.challanData != null)
/*      */       {
/*  701 */         if (this.challanData.length != 0)
/*      */         {
/*  705 */           int count = 0;
/*  706 */           for (int i = 0; i < this.challanData.length; i++) {
/*  707 */             count++;
/*      */ 
/*  711 */             if (quarter.equals("March")) {
/*  712 */               this.challanData[i][0] = Integer.valueOf(count);
/*      */             }
/*  714 */             Number label1 = new Number(0, 1 + i, Integer.parseInt(String.valueOf(this.challanData[i][0])), new WritableCellFormat(NumberFormats.INTEGER));
/*  715 */             Label label2 = new Label(1, i + 1, "92B");
/*  716 */             Number label3 = new Number(2, 1 + i, Double.parseDouble(String.valueOf(this.challanData[i][1])), new WritableCellFormat(NumberFormats.INTEGER));
/*  717 */             Number label4 = new Number(3, 1 + i, Double.parseDouble(String.valueOf(this.challanData[i][2])), new WritableCellFormat(NumberFormats.INTEGER));
/*  718 */             Number label5 = new Number(4, 1 + i, Double.parseDouble(String.valueOf(this.challanData[i][3])), new WritableCellFormat(NumberFormats.INTEGER));
/*  719 */             Number label6 = new Number(5, 1 + i, Double.parseDouble(String.valueOf(this.challanData[i][12])), new WritableCellFormat(NumberFormats.INTEGER));
/*  720 */             Number label7 = new Number(6, 1 + i, Integer.parseInt(String.valueOf(this.challanData[i][13])), new WritableCellFormat(NumberFormats.INTEGER));
/*  721 */             Number label8 = new Number(7, 1 + i, Integer.parseInt(String.valueOf(this.challanData[i][4])), new WritableCellFormat(NumberFormats.INTEGER));
/*  722 */             if ((String.valueOf(this.challanData[i][5]).equals("")) || (String.valueOf(this.challanData[i][5]).equals("null")) || 
/*  723 */               (String.valueOf(this.challanData[i][5]).equals(null))) {
/*  724 */               this.challanData[i][5] = Integer.valueOf(0);
/*      */             }
/*  726 */             Label label9 = new Label(8, i + 1, String.valueOf(this.challanData[i][5]));
/*  727 */             Label label10 = new Label(9, i + 1, String.valueOf(this.challanData[i][6]));
/*  728 */             Label label11 = new Label(10, i + 1, String.valueOf(this.challanData[i][7]));
/*  729 */             Label label12 = new Label(11, i + 1, String.valueOf(this.challanData[i][8]));
/*      */ 
/*  731 */             if (String.valueOf(this.challanData[i][11]).equals("Y")) {
/*  732 */               bookEntry = "Yes";
/*      */             }
/*      */             else {
/*  735 */               bookEntry = "No";
/*      */             }
/*  737 */             Label label13 = new Label(12, i + 1, bookEntry);
/*  738 */             Number label14 = new Number(13, 1 + i, 0.0D, new WritableCellFormat(NumberFormats.INTEGER));
/*  739 */             Number label15 = new Number(14, 1 + i, 0.0D, new WritableCellFormat(NumberFormats.INTEGER));
/*      */ 
/*  741 */             sheet4.addCell(label1);
/*  742 */             sheet4.addCell(label2);
/*  743 */             sheet4.addCell(label3);
/*  744 */             sheet4.addCell(label4);
/*  745 */             sheet4.addCell(label5);
/*  746 */             sheet4.addCell(label6);
/*  747 */             sheet4.addCell(label7);
/*  748 */             sheet4.addCell(label8);
/*  749 */             sheet4.addCell(label9);
/*  750 */             sheet4.addCell(label10);
/*  751 */             sheet4.addCell(label11);
/*  752 */             sheet4.addCell(label12);
/*  753 */             sheet4.addCell(label13);
/*  754 */             sheet4.addCell(label14);
/*  755 */             sheet4.addCell(label15);
/*      */           }
/*      */         }
/*      */       }
/*      */     } catch (Exception e) {
/*  760 */       logger.error("exception in setQuarterChallanSheet()", e);
/*      */     }
/*      */   }
/*      */ 
/*      */   public String checkNull(String result)
/*      */   {
/*  769 */     if ((result == null) || (result.equals("null")) || (result.equals(" "))) {
/*  770 */       return "";
/*      */     }
/*  772 */     return result;
/*      */   }
/*      */ 
/*      */   public Object[][] retProcessData(int frmMonth, int toMonth, String year, Form24 form24)
/*      */   {
/*  780 */     String process = " SELECT LEDGER_CODE,LEDGER_MONTH FROM HRMS_SALARY_LEDGER WHERE LEDGER_YEAR=" + 
/*  781 */       year + " AND LEDGER_STATUS='SAL_FINAL' AND LEDGER_MONTH>=" + frmMonth + " " + 
/*  782 */       "AND LEDGER_MONTH<=" + toMonth + " ";
/*      */ 
/*  784 */     Object[][] processData = getSqlModel().getSingleResult(process);
/*  785 */     return processData;
/*      */   }
/*      */ 
/*      */   public String getledgerCode(Object[][] processData) {
/*  789 */     String ledgerCode = "";
/*      */ 
/*  791 */     for (int i = 0; i < processData.length; i++) {
/*  792 */       if (i == processData.length - 1)
/*      */       {
/*  795 */         ledgerCode = ledgerCode + processData[i][0];
/*      */       }
/*      */       else
/*      */       {
/*  800 */         ledgerCode = ledgerCode + processData[i][0] + ",";
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  806 */     return ledgerCode;
/*      */   }
/*      */ 
/*      */   private void setQuarterSalary(Form24 form24, HttpServletResponse response)
/*      */   {
/*      */     try
/*      */     {
/*  813 */       String frmYear = form24.getFromYear();
/*  814 */       String toYear = form24.getToYear();
/*      */ 
/*  844 */       Date date = new Date();
/*  845 */       SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
/*  846 */       String sysDate = formater.format(date);
/*      */ 
/*  849 */       String paraDebQuery = "SELECT TDS_DEBIT_CODE FROM HRMS_TAX_PARAMETER  WHERE TDS_CODE = (SELECT MAX(TDS_CODE) FROM HRMS_TAX_PARAMETER)";
/*      */ 
/*  851 */       Object[][] tdsParaDebCode = getSqlModel().getSingleResult(paraDebQuery);
/*      */ 
/*  867 */       String query = "select  ROWNUM,NVL(SAL_PANNO,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),DECODE(EMP_GENDER,'M','MALE','F','FEMALE','O','OTHER'),  CASE WHEN EMP_REGULAR_DATE < TO_DATE('01/04/'||TDS_FROM_YEAR,'DD/MM/YYYY') THEN '01/04/'||TDS_FROM_YEAR   ELSE TO_CHAR(EMP_REGULAR_DATE,'DD/MM/YYYY') END FROMDT , '31/03/'||TDS_TO_YEAR,  TDS_GROSS_SALARY-TDS_OTH_INCOME,0,NVL(TDS_PROF_TAX,0),TDS_GROSS_SALARY-TDS_OTH_INCOME-TDS_PROF_TAX,  TDS_OTH_INCOME,TDS_GROSS_SALARY-TDS_PROF_TAX,TDS_REBATE,TDS_DEDUCTIONS+TDS_EXCEMPTIONS,  TDS_DEDUCTIONS+TDS_REBATE+TDS_EXCEMPTIONS,TDS_TAXABLE_INCOME,  ROUND(TDS_TOTAL_TAX),ROUND(TDS_SURCHARGE),ROUND(TDS_EDUC_CESS),0,  TDS_NET_TAX,TDS_EMP_ID  ,TDS_TAX_PAID FROM HRMS_TDS  INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_TDS.TDS_EMP_ID  LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)  WHERE TDS_EMP_ID IN (SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DIV=" + 
/*  878 */         form24.getDivCode() + ") " + 
/*  879 */         " and TDS_FROM_YEAR=" + form24.getFromYear() + " AND EMP_REGULAR_DATE < TO_DATE('31/03/'||TDS_TO_YEAR,'DD/MM/YYYY')";
/*      */ 
/*  883 */       Object[][] dataSalary = getSqlModel().getSingleResult(query);
/*      */ 
/*  885 */       WritableSheet sheet3 = this.copy.getSheet("Salary");
/*  886 */       for (int i = 0; i < dataSalary.length; i++)
/*      */       {
/*      */         try
/*      */         {
/*  890 */           Label label1 = new Label(0, i + 1, String.valueOf(dataSalary[i][0]));
/*  891 */           Label label2 = new Label(1, i + 1, String.valueOf(dataSalary[i][1]));
/*  892 */           Label label3 = new Label(2, i + 1, "");
/*  893 */           Label label4 = new Label(3, i + 1, String.valueOf(dataSalary[i][2]));
/*  894 */           Label label5 = new Label(4, i + 1, String.valueOf(dataSalary[i][3]));
/*  895 */           Label label6 = new Label(5, i + 1, String.valueOf(dataSalary[i][4]));
/*  896 */           Label label7 = new Label(6, i + 1, String.valueOf(dataSalary[i][5]));
/*  897 */           Label label8 = new Label(7, i + 1, String.valueOf(dataSalary[i][6]));
/*  898 */           Label label9 = new Label(8, i + 1, String.valueOf(dataSalary[i][7]));
/*  899 */           Label label10 = new Label(9, i + 1, String.valueOf(dataSalary[i][8]));
/*  900 */           Label label11 = new Label(10, i + 1, String.valueOf(dataSalary[i][9]));
/*  901 */           Label label12 = new Label(11, i + 1, String.valueOf(dataSalary[i][10]));
/*  902 */           Label label13 = new Label(12, i + 1, String.valueOf(dataSalary[i][11]));
/*  903 */           Label label14 = new Label(13, i + 1, String.valueOf(dataSalary[i][12]));
/*  904 */           Label label15 = new Label(14, i + 1, String.valueOf(dataSalary[i][13]));
/*  905 */           Label label16 = new Label(15, i + 1, String.valueOf(dataSalary[i][14]));
/*  906 */           Label label17 = new Label(16, i + 1, String.valueOf(dataSalary[i][15]));
/*  907 */           Label label18 = new Label(17, i + 1, String.valueOf(dataSalary[i][16]));
/*  908 */           Label label19 = new Label(18, i + 1, String.valueOf(dataSalary[i][17]));
/*  909 */           Label label20 = new Label(19, i + 1, String.valueOf(dataSalary[i][18]));
/*  910 */           Label label21 = new Label(20, i + 1, String.valueOf(dataSalary[i][19]));
/*  911 */           Label label22 = new Label(21, i + 1, String.valueOf(dataSalary[i][20]));
/*  912 */           Label label23 = new Label(22, i + 1, String.valueOf(dataSalary[i][22]));
/*  913 */           Label label24 = new Label(23, i + 1, "0");
/*      */           try {
/*  915 */             double net_tax = Double.parseDouble(String.valueOf(dataSalary[i][20]));
/*  916 */             double tax_paid = Double.parseDouble(String.valueOf(dataSalary[i][22]));
/*  917 */             label24 = new Label(23, i + 1, String.valueOf(Math.round(net_tax - tax_paid)));
/*      */           }
/*      */           catch (Exception localException1)
/*      */           {
/*      */           }
/*      */ 
/*  976 */           sheet3.addCell(label1);
/*  977 */           sheet3.addCell(label2);
/*  978 */           sheet3.addCell(label3);
/*  979 */           sheet3.addCell(label4);
/*  980 */           sheet3.addCell(label5);
/*  981 */           sheet3.addCell(label6);
/*  982 */           sheet3.addCell(label7);
/*  983 */           sheet3.addCell(label8);
/*  984 */           sheet3.addCell(label9);
/*  985 */           sheet3.addCell(label10);
/*  986 */           sheet3.addCell(label11);
/*  987 */           sheet3.addCell(label12);
/*  988 */           sheet3.addCell(label13);
/*  989 */           sheet3.addCell(label14);
/*  990 */           sheet3.addCell(label15);
/*  991 */           sheet3.addCell(label16);
/*  992 */           sheet3.addCell(label17);
/*  993 */           sheet3.addCell(label18);
/*  994 */           sheet3.addCell(label19);
/*  995 */           sheet3.addCell(label20);
/*  996 */           sheet3.addCell(label21);
/*  997 */           sheet3.addCell(label22);
/*  998 */           sheet3.addCell(label23);
/*  999 */           sheet3.addCell(label24);
/*      */         }
/*      */         catch (Exception e)
/*      */         {
/* 1003 */           e.printStackTrace();
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/* 1012 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setQuarterHeaderSheet(Form24 form24, HttpServletResponse response, Object[][] dataDeductor, Object[][] addDetail)
/*      */   {
/*      */     try
/*      */     {
/* 1022 */       String signAutnMobile = "";
/* 1023 */       String signAutnEmail = "";
/* 1024 */       if (addDetail.length == 0) {
/* 1025 */         form24.setSignAuthEmpAdd("Nil");
/* 1026 */         form24.setSignAuthEmpCity("Nil");
/* 1027 */         form24.setSignAuthEmpState("OTHERS");
/* 1028 */         form24.setSignAuthEmpPin("0");
/*      */       }
/*      */       else
/*      */       {
/* 1034 */         if ((String.valueOf(addDetail[0][1]).equals("null")) || (String.valueOf(addDetail[0][1]).equals(""))) {
/* 1035 */           form24.setSignAuthEmpAdd("Nil");
/*      */         }
/*      */         else
/* 1038 */           form24.setSignAuthEmpAdd(String.valueOf(addDetail[0][1]));
/* 1039 */         if ((String.valueOf(addDetail[0][2]).equals("null")) || (String.valueOf(addDetail[0][2]).equals(""))) {
/* 1040 */           form24.setSignAuthEmpCity("Nil");
/*      */         }
/*      */         else
/* 1043 */           form24.setSignAuthEmpCity(String.valueOf(addDetail[0][2]));
/* 1044 */         if ((String.valueOf(addDetail[0][3]).equals("null")) || (String.valueOf(addDetail[0][3]).equals(""))) {
/* 1045 */           form24.setSignAuthEmpState("OTHERS");
/*      */         }
/*      */         else
/* 1048 */           form24.setSignAuthEmpState(String.valueOf(addDetail[0][3]));
/* 1049 */         if ((String.valueOf(addDetail[0][4]).equals("null")) || (String.valueOf(addDetail[0][4]).equals(""))) {
/* 1050 */           form24.setSignAuthEmpPin("0");
/*      */         }
/*      */         else {
/* 1053 */           form24.setSignAuthEmpPin(String.valueOf(addDetail[0][4]));
/*      */         }
/* 1055 */         signAutnMobile = String.valueOf(addDetail[0][5]);
/* 1056 */         signAutnEmail = String.valueOf(addDetail[0][6]);
/*      */       }
/*      */ 
/* 1059 */       String frmYear = form24.getToYear();
/* 1060 */       String finYearTo = frmYear.substring(2, 4);
/* 1061 */       String assesYear = String.valueOf(Double.parseDouble(String.valueOf(frmYear)) + 1.0D);
/* 1062 */       String assesmentYear = assesYear.substring(2, 4);
/* 1063 */       String quarter = String.valueOf(form24.getQuarter());
/*      */ 
/* 1065 */       String QUARTER_NUMBER = "0";
/* 1066 */       if (quarter.equals("June")) {
/* 1067 */         this.year = form24.getFromYear();
/* 1068 */         QUARTER_NUMBER = "Q1";
/* 1069 */       } else if (quarter.equals("September")) {
/* 1070 */         this.year = form24.getFromYear();
/* 1071 */         QUARTER_NUMBER = "Q2";
/* 1072 */       } else if (quarter.equals("December")) {
/* 1073 */         this.year = form24.getFromYear();
/* 1074 */         QUARTER_NUMBER = "Q3";
/* 1075 */       } else if (quarter.equals("March")) {
/* 1076 */         this.year = form24.getFromYear();
/* 1077 */         QUARTER_NUMBER = "Q4";
/*      */       }
/*      */ 
/* 1080 */       String deductorStatus = "";
/* 1081 */       if (String.valueOf(form24.getDeductorStat()).equals("C")) {
/* 1082 */         deductorStatus = "C  CentralGovt";
/*      */       }
/*      */       else {
/* 1085 */         deductorStatus = "O  Other";
/*      */       }
/*      */ 
/* 1088 */       WritableSheet sheet1 = this.copy.getSheet("Deductor");
/* 1089 */       Label label1 = new Label(1, 0, String.valueOf(dataDeductor[0][0]));
/* 1090 */       if ((String.valueOf(dataDeductor[0][1]).equals("null")) || (String.valueOf(dataDeductor[0][1]).equals(""))) {
/* 1091 */         dataDeductor[0][1] = "PANNOTAVBL";
/*      */       }
/* 1093 */       Label label2 = new Label(1, 1, String.valueOf(dataDeductor[0][1]));
/* 1094 */       Label label3 = new Label(1, 2, String.valueOf(dataDeductor[0][2]));
/* 1095 */       if ((String.valueOf(dataDeductor[0][3]).equals("null")) || (String.valueOf(dataDeductor[0][3]).equals(""))) {
/* 1096 */         dataDeductor[0][3] = "Nil";
/*      */       }
/* 1098 */       Label label4 = new Label(1, 4, String.valueOf(dataDeductor[0][3]));
/* 1099 */       Label label5 = new Label(1, 5, String.valueOf(dataDeductor[0][4]));
/* 1100 */       Label label6 = new Label(1, 6, String.valueOf(dataDeductor[0][5]));
/* 1101 */       if ((String.valueOf(dataDeductor[0][6]).equals("null")) || (String.valueOf(dataDeductor[0][6]).equals(""))) {
/* 1102 */         dataDeductor[0][6] = "Nil";
/*      */       }
/* 1104 */       Label label7 = new Label(1, 8, String.valueOf(dataDeductor[0][6]));
/* 1105 */       if ((String.valueOf(dataDeductor[0][8]).equals("null")) || (String.valueOf(dataDeductor[0][8]).equals(""))) {
/* 1106 */         dataDeductor[0][8] = "OTHERS";
/*      */       }
/* 1108 */       Label label13 = new Label(1, 9, String.valueOf(dataDeductor[0][8]));
/* 1109 */       if ((String.valueOf(dataDeductor[0][7]).equals("null")) || (String.valueOf(dataDeductor[0][7]).equals(""))) {
/* 1110 */         dataDeductor[0][7] = "0";
/*      */       }
/* 1112 */       Label label8 = new Label(1, 10, String.valueOf(dataDeductor[0][7]));
/*      */ 
/* 1115 */       String[] telephoneNo = String.valueOf(dataDeductor[0][9]).split("-");
/* 1116 */       Label labe22 = new Label(1, 11, telephoneNo[0]);
/* 1117 */       Label label23 = new Label(1, 12, telephoneNo[1]);
/* 1118 */       Label label24 = new Label(1, 13, String.valueOf(dataDeductor[0][10]));
/*      */ 
/* 1121 */       Label label14 = new Label(1, 14, deductorStatus);
/* 1122 */       Label label9 = new Label(1, 15, form24.getSignAuthName());
/*      */ 
/* 1126 */       Label label16 = new Label(1, 16, form24.getSignAuthEmpAdd());
/* 1127 */       Label label17 = new Label(1, 20, form24.getSignAuthEmpCity());
/* 1128 */       Label label18 = new Label(1, 21, form24.getSignAuthEmpState());
/* 1129 */       Label label19 = new Label(1, 22, form24.getSignAuthEmpPin());
/*      */ 
/* 1131 */       Label label20 = new Label(1, 24, signAutnMobile);
/* 1132 */       Label label21 = new Label(1, 25, signAutnEmail);
/*      */ 
/* 1134 */       Label label15 = new Label(1, 26, form24.getSignAuthEmpDesg());
/* 1135 */       Label label10 = new Label(1, 29, this.year + finYearTo);
/* 1136 */       Label label11 = new Label(1, 30, QUARTER_NUMBER);
/* 1137 */       Label label12 = new Label(1, 31, frmYear + assesmentYear);
/* 1138 */       sheet1.addCell(label1);
/* 1139 */       sheet1.addCell(label2);
/* 1140 */       sheet1.addCell(label3);
/* 1141 */       sheet1.addCell(label4);
/* 1142 */       sheet1.addCell(label5);
/* 1143 */       sheet1.addCell(label6);
/* 1144 */       sheet1.addCell(label7);
/* 1145 */       sheet1.addCell(label8);
/* 1146 */       sheet1.addCell(label9);
/* 1147 */       sheet1.addCell(label10);
/* 1148 */       sheet1.addCell(label11);
/* 1149 */       sheet1.addCell(label12);
/* 1150 */       sheet1.addCell(label13);
/* 1151 */       sheet1.addCell(label14);
/* 1152 */       sheet1.addCell(label15);
/* 1153 */       sheet1.addCell(label16);
/* 1154 */       sheet1.addCell(label17);
/* 1155 */       sheet1.addCell(label18);
/* 1156 */       sheet1.addCell(label19);
/* 1157 */       sheet1.addCell(label20);
/* 1158 */       sheet1.addCell(label21);
/* 1159 */       sheet1.addCell(labe22);
/* 1160 */       sheet1.addCell(label23);
/* 1161 */       sheet1.addCell(label24);
/*      */     } catch (Exception e) {
/* 1163 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void getFatherRecord(Form24 bean, HttpServletResponse response)
/*      */   {
/* 1169 */     String query = "SELECT RELATION_CODE,RELATION_NAME FROM HRMS_RELATION  where upper(RELATION_NAME)= 'FATHER'  ORDER BY RELATION_CODE ";
/*      */ 
/* 1173 */     Object[][] relation = getSqlModel().getSingleResult(query);
/*      */ 
/* 1176 */     if (relation.length == 0)
/*      */     {
/* 1178 */       bean.setSignAuthEmpFather("");
/*      */     }
/*      */     else
/*      */     {
/* 1182 */       String sql = "SELECT FML_ID,NVL(FML_FNAME||' '||FML_MNAME||' '||FML_LNAME,' '),  FML_RELATION  FROM  HRMS_EMP_FML  LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_FML.EMP_ID)  LEFT JOIN HRMS_RELATION  ON(HRMS_RELATION.RELATION_CODE=HRMS_EMP_FML.FML_RELATION)  WHERE HRMS_EMP_OFFC.EMP_ID =" + 
/* 1187 */         bean.getSignAuthEmpId() + " AND RELATION_CODE=" + relation[0][0] + " " + 
/* 1188 */         " ORDER BY FML_ID ";
/*      */ 
/* 1190 */       Object[][] fatherName = getSqlModel().getSingleResult(sql);
/*      */ 
/* 1192 */       if (fatherName.length == 0)
/*      */       {
/* 1194 */         bean.setSignAuthEmpFather("");
/*      */       }
/*      */       else
/* 1197 */         bean.setSignAuthEmpFather(String.valueOf(fatherName[0][1]));
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Documents and Settings\Administrator\Desktop\
 * Qualified Name:     org.paradyne.model.payroll.incometax.Form24Model
 * JD-Core Version:    0.6.0
 */