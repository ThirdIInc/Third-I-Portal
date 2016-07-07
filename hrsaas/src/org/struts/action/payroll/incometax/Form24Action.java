/*     */ package org.struts.action.payroll.incometax;
/*     */ 
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.paradyne.bean.payroll.incometax.Form24;
/*     */ import org.paradyne.model.payroll.incometax.Form24Model;
/*     */ import org.struts.lib.ParaActionSupport;
/*     */ 
/*     */ public class Form24Action extends ParaActionSupport
/*     */ {
/*     */   Form24 form24;
/*  31 */   static Logger logger = Logger.getLogger(Form24Action.class);
/*     */ 
/*     */   public Form24 getForm24()
/*     */   {
/*  24 */     return this.form24;
/*     */   }
/*     */ 
/*     */   public void setForm24(Form24 form24) {
/*  28 */     this.form24 = form24;
/*     */   }
/*     */ 
/*     */   public void prepare_local()
/*     */     throws Exception
/*     */   {
/*  34 */     this.form24 = new Form24();
/*  35 */     this.form24.setMenuCode(238);
/*     */   }
/*     */ 
/*     */   public Object getModel()
/*     */   {
/*  43 */     return this.form24;
/*     */   }
/*     */ 
/*     */   public void prepare_withLoginProfileDetails()
/*     */     throws Exception
/*     */   {
/*  51 */     Date date = new Date();
/*  52 */     SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
/*  53 */     String sysdate = formater.format(date);
/*  54 */     String[] split = sysdate.split("/");
/*  55 */     int year = Integer.parseInt(String.valueOf(split[2]));
/*  56 */     this.form24.setFromYear(String.valueOf(year));
/*  57 */     this.form24.setToYear(String.valueOf(year + 1));
/*     */   }
/*     */ 
/*     */   public String report() throws Exception
/*     */   {
/*  62 */     Form24Model model = new Form24Model();
/*  63 */     model.initiate(this.context, this.session);
/*  64 */     String message = model.execute(this.form24, this.response);
/*  65 */     logger.info("message   :" + message);
/*  66 */     if (!message.equals("")) {
/*  67 */       addActionMessage(message);
/*  68 */       return "success";
/*     */     }
/*  70 */     model.terminate();
/*     */ 
/*  72 */     return null;
/*     */   }
/*     */ 
/*     */   public String getEmpFather()
/*     */     throws Exception
/*     */   {
/*  78 */     Form24Model model = new Form24Model();
/*  79 */     model.initiate(this.context, this.session);
/*  80 */     model.getFatherRecord(this.form24, this.response);
/*  81 */     model.terminate();
/*  82 */     return "success";
/*     */   }
/*     */ 
/*     */   public String f9division()
/*     */     throws Exception
/*     */   {
/*  91 */     String query = " SELECT DIV_NAME,DIV_ID FROM HRMS_DIVISION";
/*     */ 
/*  93 */     if ((this.form24.getUserProfileDivision() != null) && (this.form24.getUserProfileDivision().length() > 0))
/*  94 */       query = query + " WHERE DIV_ID IN (" + this.form24.getUserProfileDivision() + ")";
/*  95 */     query = query + " ORDER BY  DIV_ID ";
/*     */ 
/* 101 */     String[] headers = { getMessage("division") };
/*     */ 
/* 103 */     String[] headerWidth = { "100" };
/*     */ 
/* 112 */     String[] fieldNames = { "form24.divName", "form24.divCode" };
/*     */ 
/* 123 */     int[] columnIndex = { 0, 1 };
/*     */ 
/* 129 */     String submitFlag = "false";
/*     */ 
/* 137 */     String submitToMethod = "";
/*     */ 
/* 143 */     setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
/*     */ 
/* 145 */     return "f9page";
/*     */   }
/*     */ 
/*     */   public String f9signAuthority()
/*     */     throws Exception
/*     */   {
/* 154 */     logger.info("In f9 action===========1");
/* 155 */     String query = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),  NVL(RANK_NAME,' '),EMP_ID  FROM HRMS_EMP_OFFC  LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK ";
/*     */ 
/* 164 */     String[] headers = { getMessage("employee.id"), 
/* 165 */       getMessage("employee"), getMessage("designation") };
/* 166 */     String[] headerWidth = { "15", "30", "20" };
/*     */ 
/* 175 */     String[] fieldNames = { "form24.empToken", "form24.signAuthName", "form24.signAuthEmpDesg", "form24.signAuthEmpId" };
/*     */ 
/* 186 */     int[] columnIndex = { 0, 1, 2, 3 };
/*     */ 
/* 192 */     String submitFlag = "true";
/*     */ 
/* 198 */     logger.info("In f9 action===========3");
/*     */ 
/* 201 */     String submitToMethod = "Form24_getEmpFather.action";
/*     */ 
/* 203 */     logger.info("In f9 action===========4");
/*     */ 
/* 208 */     setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
/*     */ 
/* 210 */     return "f9page";
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\Administrator\Desktop\
 * Qualified Name:     org.struts.action.payroll.incometax.Form24Action
 * JD-Core Version:    0.6.0
 */