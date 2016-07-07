package org.paradyne.model.recruitment;
import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.ModelBase;
import org.paradyne.bean.Recruitment.ManpwrReqsnAnalysis;
import org.paradyne.bean.Recruitment.ReqsnMisReport;
import org.paradyne.lib.report.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.lib.Utility;
import org.struts.action.recruitment.ManpowerRequisitionAnalysisAction;
/*
 * @author Pradeep Kumar Sahoo
 * Date:07-04-2009
 *
 */
public class ReqsnMisReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReqsnMisReportModel.class);
	
/**
 * following method is called to display all the requisitions when export all pages check box is clicked. 
 * in Manpower Requisition Report
 * @param bean
 */	
	public void getAllRequisitions(String type,ReqsnMisReport bean,HttpServletRequest request,HttpServletResponse response){
		try{
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,"ManPower Requisition Report");
		
		rg.addFormatedText("Manpower Requisition Report",6,0,1,1);	
		bean.setReportType(type.substring(0,1));
		String query ="SELECT NVL(REQS_NAME,' '),NVL(RANK_NAME,' '),DECODE(REQS_STATUS,'O','Open','C','Close')," +
				" NVL(DIV_NAME,' '),NVL(CENTER_NAME,' '),NVL(DEPT_NAME,' '),DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition')," +
				" OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||' '||OFFC1.EMP_LNAME,CASE WHEN REQS_RECTYPE_INT='Y' THEN 'Internal' ELSE ' ' END," +
				" OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME,NVL(REQS_JOBDESC_NAME,' ')," +
				" NVL(REQS_JOB_DESCRIPTION,' '),NVL(REQS_ROLE_RESPON,' '),NVL(REQS_SPECIAL_REQ,' '),NVL(REQS_PERSONEL_REQ,' '),REQS_VACAN_COMPEN," +
				" NVL(REQS_VACAN_EXPMIN,''),NVL(REQS_VACAN_EXPMAX,''),decode(REQS_VACAN_TYPE,'N','New Post','R','Replacement','',''),decode(REQS_VACAN_CONTRACT,'R','Regular','O','On Contract','','')," +
				" decode(REQS_VACAN_CONTYPE,'P','Part Time','F','Full Time'),REQS_APPR_CODE,REQS_LEVEL,CASE WHEN REQS_RECTYPE_EXT='Y' THEN 'External' ELSE ' ' END,NVL(TO_CHAR(REQS_DATE,'DD-MM-YYYY'),' '),REQS_CODE FROM HRMS_REC_REQS_HDR " +
				" LEFT JOIN HRMS_EMP_OFFC OFFC1 ON OFFC1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER " +
				" LEFT JOIN HRMS_EMP_OFFC OFFC ON OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY " +
				" LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION " +
				" LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_REC_REQS_HDR.REQS_DIVISION " +
				" LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_REC_REQS_HDR.REQS_DEPT " +
				" LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH " +
				" WHERE 1=1"; 
				
				if(!(bean.getDivCode().equals("") || bean.getDivCode().equals("null"))){
					query+=" AND HRMS_REC_REQS_HDR.REQS_DIVISION="+bean.getDivCode();
				}
				
				if(!(bean.getBrnCode().equals("") || bean.getBrnCode().equals("null"))){
					query+=" AND HRMS_REC_REQS_HDR.REQS_BRANCH="+bean.getBrnCode();
				}
				
				if(!(bean.getDeptCode().equals("") || bean.getDeptCode().equals("null"))){
					query+=" AND HRMS_REC_REQS_HDR.REQS_DEPT="+bean.getDeptCode();
				}
				
				if(!(bean.getPosCode().equals("") || bean.getPosCode().equals("null"))){
					query+=" AND HRMS_REC_REQS_HDR.REQS_POSITION="+bean.getPosCode();
				}
				
				
				
				if(bean.getDateFilter().equals("O ")){
					if(!(bean.getFrmDate()==null) && !bean.getFrmDate().equals("")){
						query+= " AND HRMS_REC_REQS_HDR.REQS_DATE= TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
					}
			    }
				
					
			   if(bean.getDateFilter().equals("B ")){
					if(!(bean.getFrmDate()==null)&&!bean.getFrmDate().equals("")){
						query+= " AND HRMS_REC_REQS_HDR.REQS_DATE < TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
					}
				}
				
			   if(bean.getDateFilter().equals("A ")){
					if(!(bean.getFrmDate()==null)&&!bean.getFrmDate().equals("")){
						query+= " AND HRMS_REC_REQS_HDR.REQS_DATE > TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
					}
				}
				
			   if(bean.getDateFilter().equals("OB")){
					if(!(bean.getFrmDate()==null)&&!bean.getFrmDate().equals("")){
						query+= " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
					}
				}
				
			   if(bean.getDateFilter().equals("OA")){
					if(!(bean.getFrmDate()==null)&&!bean.getFrmDate().equals("")){
						query+= " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
					}
				}
				
				
			   if(bean.getDateFilter().equals("F ")){
					if(!(bean.getFrmDate().equals("dd-mm-yyyy"))){
						query+= " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
					}
					
			   if(!(bean.getTDate()==null) && !bean.getTDate().equals("")){
				   	if(!(bean.getTDate().equals("dd-mm-yyyy")))
						query+= " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("+"'"+bean.getTDate()+"'"+",'DD-MM-YYYY')";
					}
					
			   }
			
				
				if(!(bean.getSelectedReq().equals("") || bean.getSelectedReq().equals("null"))){
					query+=" AND REQS_CODE IN("+bean.getSelectedReq()+")";
				}
				
				
				if(!(bean.getHiringMgrId().equals("null") || bean.getHiringMgrId().equals(""))){
					query+=" AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER="+bean.getHiringMgrId();
				}
				
				if(!(bean.getReqsStatus().equals("1"))){
					query+=" and reqs_status='"+bean.getReqsStatus()+"'";
				}
			
			 
				if(!bean.getFirstSort().equals("1") || !bean.getSecondSort().equals("1") || !bean.getThirdSort().equals("1")){
					
					query+=" ORDER BY ";
				}else {
					query+=" ORDER BY TO_CHAR(REQS_DATE,'DD-MM-YYYY') DESC";
				}
				
				if(!bean.getFirstSort().equals("1")){
					query+=" "+getSortingValue(bean.getFirstSort());
					if(bean.getHidFirstAsc().equals("A")){
						query+=" "+getSortOrder("A"); 
					}else{
						query+=" "+getSortOrder("D"); 
					}
				}
				
				if(!bean.getSecondSort().equals("1")){
					if(!bean.getFirstSort().equals("1")){
						query+=",";
					}
					query+=" "+getSortingValue(bean.getSecondSort());
					if(bean.getHidSecAsc().equals("A")){
						query+=" "+getSortOrder("A"); 
					}else{
						query+=" "+getSortOrder("D"); 
					}
				}
				
				if(!bean.getThirdSort().equals("1")){
					if(!bean.getFirstSort().equals("1") || !bean.getSecondSort().equals("1")){
						query+=",";	
					}
					query+=" "+getSortingValue(bean.getThirdSort());
					if(bean.getHidThirdAsc().equals("A")){
						query+=" "+getSortOrder("A"); 
					}else{
						query+=" "+getSortOrder("D"); 
					}
				}
					     
			     
			 Object[][] data= getSqlModel().getSingleResult(query); 
			
			if(data!=null && data.length>0){
				
				
				for(int r=0;r<data.length;r++){
					if(bean.getReportType().equals("P"))
					rg.addText("\n",0,0,0);
					
					Object manpow[][] = new Object[6][7];		 
					Object job[][] = new Object[5][3];		
					Object vac[][] = new Object[2][7];
					int[] bcellWidth = {10,3,15,7,10,3,15};
					int[] bcellAlign = {0,0,0,0,0,0,0};
					int[] jobCellWidth={10,3,50};
					int[] jobCellAlign={0,0,0}; 
					manpow[0][0] = "Requisition Code";manpow[0][1]=":";manpow[0][2]=""+String.valueOf(data[r][0]);manpow[0][3]="";manpow[0][4]="Requisition Date";manpow[0][5]=":";manpow[0][6]=""+String.valueOf(data[r][24]);
					manpow[1][0] = "Position";manpow[1][1]=":";manpow[1][2]=""+String.valueOf(data[r][1]);manpow[1][3]="";manpow[1][4]="Approval Status";manpow[1][5]=":";manpow[1][6]=""+String.valueOf(data[r][6]);
					manpow[2][0] = "Division";manpow[2][1]=":";manpow[2][2]=""+String.valueOf(data[r][3]) ;manpow[2][3]="";manpow[2][4]="Requisition Status";manpow[2][5]=":";manpow[2][6]=""+String.valueOf(data[r][2]);		
					manpow[3][0] = "Branch";manpow[3][1]=":";manpow[3][2]=""+String.valueOf(data[r][4]);manpow[3][3]="";manpow[3][4]="";manpow[3][5]="";manpow[3][6]="";
					manpow[4][0] = "Department";manpow[4][1]=":";manpow[4][2]=""+String.valueOf(data[r][5]);manpow[4][3]="";manpow[4][4]="";manpow[4][5]="";manpow[4][6]=""; 
					manpow[5][0] = "Hiring Manager";manpow[5][1]=":";manpow[5][2]=""+String.valueOf(data[r][7]);manpow[5][3]="";manpow[5][4]="Recruitment Type";manpow[5][5]=":";manpow[5][6]=String.valueOf(data[r][8])+"  "+String.valueOf(data[r][23]) ;
					
					if(type.equals("Pdf"))
					    rg.addTextBold("Manpower Information :", 0, 0, 0);
					else
						rg.addFormatedText("Manpower Information :", 6,0,0,0);
				
					if(type.equals("Pdf")){
						rg.tableBodyNoCellBorder(manpow, bcellWidth,bcellAlign,0);
					}else{
						rg.tableBodyNoBorder(manpow, bcellWidth,bcellAlign);
						
					}
					if(type.equals("Pdf"))
					   rg.addTextBold("Job Description :", 0, 0, 0);
					else
						rg.addFormatedText("Job Description",6,0,0,1);	
					
					job[0][0]="Job Name";job[0][1] = ": ";job[0][2]=""+String.valueOf(data[r][10]);
					job[1][0]="Job Description";job[1][1]=":";job[1][2]=""+String.valueOf(data[r][11]);
					job[2][0]="Job Roles and Responsibilty";job[2][1]=":";job[2][2]=""+String.valueOf(data[r][12]);
					job[3][0]="Special Requirement";job[3][1]=":";job[3][2]=""+String.valueOf(data[r][13]);
					job[4][0]="Personal Qualities";job[4][1]=":";job[4][2]=""+String.valueOf(data[r][14]);
					if(type.equals("Pdf")){
						rg.tableBodyNoCellBorder(job, jobCellWidth,jobCellAlign,0);
					}else{
						rg.tableBodyNoBorder(job, jobCellWidth,jobCellAlign);
					}
					    rg=getVacancy(rg, bean.getItReqCode1(),bean);
					
					
				
					
					vac[0][0] = "Compensation In Lacs";
					vac[0][1] = ":";
					vac[0][2] =""+String.valueOf(data[r][15]);
					vac[0][3] ="";
					vac[0][4] ="Experience";
					vac[0][5] =":";
					vac[0][6]=" Min :"+(String.valueOf(data[r][16]).equals("null") ?"":String.valueOf(data[r][16]))+" "+"Max :"+(String.valueOf(data[r][17]).equals("null") ?"":String.valueOf(data[r][17])) ;
						
					vac[1][0] ="Vacancy Type";
					vac[1][1] =":";
					vac[1][2] =""+String.valueOf(data[r][18]);
					vac[1][3] ="";
					vac[1][4] ="Contract Type:";
					vac[1][5] =": ";
					vac[1][6]=""+(String.valueOf(data[r][19]).equals("null") ?"":String.valueOf(data[r][19]))+"  "+(String.valueOf(data[r][20]).equals("null") ?"":String.valueOf(data[r][20])) ;
					if(type.equals("Pdf")){
						rg.tableBodyNoCellBorder(vac,bcellWidth,bcellAlign,0);
					}else{
					    rg.tableBody(vac,bcellWidth,bcellAlign) ;
					}
					
					rg=getQuaDets(rg,String.valueOf(data[r][25]),bean);
					rg=getSkillDets(rg,String.valueOf(data[r][25]),bean);
					rg=getDomainDets(rg,String.valueOf(data[r][25]),bean);
					rg=getCertDets(rg,String.valueOf(data[r][25]),bean);
					rg=getApprvDet(rg,String.valueOf(data[r][25]),bean);
					rg.pageBreak();
				}//End of for loop
				
			}//End of if condition
			else{
				rg.addText("There is no data to display", 0,1,0);
			}
			 rg.createReport(response);		
		}catch(Exception e){
			e.printStackTrace();
		}
				
		
	}//End of method
	
	
	
	/**
	 * following function is called to sort the records depending on the Sorting value.
	 * @param status
	 * @return
	 */
	public String getSortingValue(String status){
		String sql="";
		
		if(status.equals("RN")){
			sql="REQS_NAME ";
		}
		
		if(status.equals("PO")){
			sql="RANK_NAME";
		}
		
		if(status.equals("RD")){
			sql="TO_CHAR(REQS_DATE,'DD-MM-YYYY') ";
		}
		if(status.equals("BR")){
			sql="CENTER_NAME ";
		}
		if(status.equals("DE")){
			sql="DEPT_NAME ";
		}
		
		if(status.equals("DI")){
			sql="DIV_NAME";
		}
		
		return sql;
		
	}
	/**
	 * following function is called for sort the records in Ascending order or in Descending
	 * @param Status
	 * @return
	 */
	public String getSortOrder(String Status)
	{ 
	   String sql=""; 
	   if(Status.equals("A")) {		   
		   sql="ASC";
	   } 
	   if(Status.equals("D")) {		   
		   sql="DESC";
	   }
	   return sql ;
	}
	
	
	
	/**
	 * following function is used to display the requisition details along with the check boxes.
	 * @param bean
	 */
	public void displayReq(ReqsnMisReport bean){
		
	try{	
		String query =" SELECT REQS_NAME,RANK_NAME,TO_CHAR(REQS_DATE ,'DD-MM-YYYY'),DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A' "
					+" ,'Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),REQS_CODE FROM HRMS_REC_REQS_HDR  "
					+" INNER JOIN  HRMS_RANK ON (RANK_ID =REQS_POSITION) " 
		            +" WHERE  1=1  ";
	
		
		if(!(bean.getDivCode().equals("") || bean.getDivCode().equals("null"))){
			query+=" AND HRMS_REC_REQS_HDR.REQS_DIVISION="+bean.getDivCode();
		}
		
		if(!(bean.getBrnCode().equals("") || bean.getBrnCode().equals("null"))){
			query+=" AND HRMS_REC_REQS_HDR.REQS_BRANCH="+bean.getBrnCode();
		}
		
		if(!(bean.getDeptCode().equals("") || bean.getDeptCode().equals("null"))){
			query+=" AND HRMS_REC_REQS_HDR.REQS_DEPT="+bean.getDeptCode();
		}
		 
		 if(!bean.getPosCode().equals(""))
		 {
			  query +=" AND REQS_POSITION ="+bean.getPosCode();
		 } 
		if(bean.getDateFilter().equals("O ")){
				if(!(bean.getFrmDate()==null) && !bean.getFrmDate().equals("")){
					query+= " AND HRMS_REC_REQS_HDR.REQS_DATE= TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
				}
		}
			
		if(bean.getDateFilter().equals("B ")){
				if(!(bean.getFrmDate()==null)&&!bean.getFrmDate().equals("")){
					query+= " AND HRMS_REC_REQS_HDR.REQS_DATE < TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
				}
			}
			
		if(bean.getDateFilter().equals("A ")){
				if(!(bean.getFrmDate()==null)&&!bean.getFrmDate().equals("")){
					query+= " AND HRMS_REC_REQS_HDR.REQS_DATE > TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
				}
			}
			
		if(bean.getDateFilter().equals("OB")){
				if(!(bean.getFrmDate()==null)&&!bean.getFrmDate().equals("")){
					query+= " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
				}
			}
			
		if(bean.getDateFilter().equals("OA")){
				if(!(bean.getFrmDate()==null)&&!bean.getFrmDate().equals("")){
					query+= " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
				}
			}
			
			
		 if(bean.getDateFilter().equals("F ")){
				if(!(bean.getFrmDate()==null)&& !bean.getFrmDate().equals("")){
					query+= " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
				}
				
		 if(!(bean.getToDate()==null) && !bean.getToDate().equals("")){
					query+= " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("+"'"+bean.getToDate()+"'"+",'DD-MM-YYYY')";
				}
				
				
			 }
			
	
		  Object [][] data = getSqlModel().getSingleResult(query);
		  if(data!=null && data.length >0)
		  {	  bean.setNoDataReq("false");
		      bean.setEditFlag("true");
			  bean.setDataLength(""+data.length);
			  int counter=0;
			 ArrayList<Object> list = new ArrayList<Object>();  
			 for (int i = 0; i < data.length; i++) {
				 counter++;
				 ReqsnMisReport bean1 = new ReqsnMisReport();
				 bean1.setItReqName(callStringBreak(""+data[i][0],15));
				 bean1.setItPosition(""+data[i][1]);
				 bean1.setItReqDate(""+data[i][2]);
				 bean1.setItStatus(""+data[i][3]);
				 bean1.setItReqCode(""+data[i][4]);
				 list.add(bean1);
				 if(bean.getEditReqFlag().equals("true"))
				 {
					 String [] selectedreqId =bean.getSelectedReq().split(",");
					 for (int j = 0; j < selectedreqId.length; j++) {
						if(selectedreqId[j].equals(""+data[i][4])){
							bean1.setSelectedReqFlag("checked");
							break;
						}
					}
				 }
			 }
			bean.setDispList(list);
			//bean.setDataLen(String.valueOf(counter));
		  }else{
			  bean.setNoDataReq("true");
			   bean.setEditFlag("false");
		  }
	 
	}catch(Exception e){
		e.printStackTrace();
	}

}

	public static String callStringBreak(String strVal,int breakLen)
	{ 
		 String strBreak ="";
		 int numOfBreak =0; 
			if(strVal!=null && strVal.length()>0 && strVal.length()>breakLen)
			{ 
				 numOfBreak = strVal.length()/breakLen; 
				 int j =0;
				 for (int i = breakLen; i < strVal.length(); ) {
					 strBreak+= strVal.substring(j, breakLen+j)+" ";
					 j =breakLen+j;
					 i=i+breakLen;
					 strBreak.trim();
				} 
				 if(j<strVal.length()){
					 strBreak+= strVal.substring(j, strVal.length());
					 strBreak.trim();
				 }
			} else{
				strBreak =strVal;
			} 
		return strBreak;
	}
	/**
	 * following function is called to display the approver details in the report.  
	 * @param rg
	 * @param reqCode
	 * @param bean
	 * @return
	 */
	public ReportGenerator getApprvDet(ReportGenerator rg,String reqCode,ReqsnMisReport bean){
		try{
		String apprvQuery="  SELECT DISTINCT ROWNUM, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,NVL(TO_CHAR(PATH_APPR_DATE,'DD-MM-YYYY'),' '),"
			 +" NVL(HRMS_REC_REQS_PATH.PATH_REMARK,' ') FROM HRMS_REC_REQS_PATH " 
		     +" INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_REQS_PATH.PATH_REQ_CODE)"
			 +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_REQS_PATH.PATH_APPROVER_CODE)"
		     +" WHERE REQS_CODE="+reqCode;
			Object[][] apprvData = getSqlModel().getSingleResult(apprvQuery);
			String[] apprvCol={"Sr No","Approver Name","Approved Date","Approver's Comment"};
			int[] apprvCellWidth={3,15,10,10};
			int[] apprvCellAlign={1,0,1,0};
			if(apprvData!=null && apprvData.length>0){
				if(bean.getReportType().equals("T"))
				    rg.addText("Approver Details:",0,0,0);
				else
					rg.addTextBold("Approver Details:",0,0,0);
				
				rg.tableBody(apprvCol,apprvData,apprvCellWidth,apprvCellAlign) ;
				
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return rg;
	}
	
	/**
	 * following function is called to get the vacancy details of the requisition
	 * @param reqCode
	 * @param bean
	 * @return
	 */
	public ReportGenerator getVacancy(ReportGenerator rg,String reqCode,ReqsnMisReport bean){
			try{
			String query = "SELECT ROWNUM,NVL(VACAN_NUMBERS,0),NVL(TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),' ') FROM HRMS_REC_REQS_VACDTL" +
			" WHERE REQS_CODE="+reqCode;
			
			if(bean.getAdvVacOpt().equals("IE")){
				query+=" AND VACAN_NUMBERS="+bean.getAdvVacVal();
			}
			
			if(bean.getAdvVacOpt().equals("LT")){
				query+=" AND VACAN_NUMBERS < "+bean.getAdvVacVal();
			}
			
			if(bean.getAdvVacOpt().equals("GT")){
				query+=" AND VACAN_NUMBERS > "+bean.getAdvVacVal();
			}
			
			if(bean.getAdvVacOpt().equals("LE")){
				query+=" AND VACAN_NUMBERS <= "+bean.getAdvVacVal();
			}
			
			if(bean.getAdvVacOpt().equals("GT")){
				query+=" AND VACAN_NUMBERS >="+bean.getAdvVacVal();
			}
					
				
			Object[][] vacDets = getSqlModel().getSingleResult(query);
				if(vacDets!=null && vacDets.length >0){	
					int[] vacCellWidth={5,5,10};
					int[] vacCellAlign={1,1,1};
					String[] vacCol={"Sr No","No. Of Vacancies","Required by Date"};
					if(bean.getReportType().equals("T"))
						rg.addFormatedText("Vacancy Details :",6,0,0,0);
					else
					    rg.addTextBold("Vacancy Details:", 0, 0, 0);	
					rg.tableBody(vacCol,vacDets,vacCellWidth,vacCellAlign);
				}	
			}catch(Exception e){
			e.printStackTrace();
		}
		return rg;
	}
	
	/**
	 * following function is called to get the qualification details
	 * @param reqCode
	 * @param bean
	 * @return
	 */
	public ReportGenerator getQuaDets(ReportGenerator rg,String reqCode,ReqsnMisReport bean){
			try{
			String query="SELECT ROWNUM,CASE WHEN HRMS_REC_REQS_QUA_DTL.REQS_QUA_TYPE='E' THEN 'Essential' WHEN HRMS_REC_REQS_QUA_DTL.REQS_QUA_TYPE='D' THEN 'Desirable' else ' ' end ,NVL(HRMS_QUA.QUA_ABBR,' '),CASE WHEN QUA_LEVEL='UG' THEN 'Under Graduate' "
				+" WHEN QUA_LEVEL='DI' THEN 'Diploma' WHEN QUA_LEVEL='GR' THEN 'Graduate' WHEN QUA_LEVEL='PG' THEN 'Post Graduate'"
				+" WHEN QUA_LEVEL='PH' THEN 'Phd' WHEN QUA_LEVEL='DO' THEN 'Doctorate' ELSE ' ' END,"
				+" NVL(HRMS_SPECIALIZATION.SPEC_ABBR,' '),REQS_QUA_CUTOFF,CASE WHEN REQS_QUA_OPTION='A' THEN 'And' WHEN REQS_QUA_OPTION='R' THEN 'Or' ELSE ' ' END"
				+" ,REQS_QUA_DTL_CODE FROM HRMS_REC_REQS_QUA_DTL LEFT JOIN HRMS_QUA ON HRMS_QUA.QUA_ID=HRMS_REC_REQS_QUA_DTL.REQS_QUA_CODE "
				+" LEFT JOIN HRMS_SPECIALIZATION ON HRMS_REC_REQS_QUA_DTL.REQS_SPECIALIZATION_CODE=HRMS_SPECIALIZATION.SPEC_ID"
				+" WHERE HRMS_REC_REQS_QUA_DTL.REQS_CODE="+reqCode;
				
			Object[][] qualification = getSqlModel().getSingleResult(query);
				if(qualification!=null && qualification.length>0){	
					if(bean.getReportType().equals("T"))
						rg.addFormatedText("Qualification Details",6,0,0,1);	
					else
						rg.addTextBold("Qualification Details:", 0, 0, 0);
					
					String colnames1[] = {"Sr No","Qualification Type", "Qualification Abbreviation","Qualification Level","Specialization","Cut Off Marks","Option"};
					int cellwidth1[] = { 5,10, 15,10,10,10,5};
					int alignment1[] = { 1,1, 0,0,0,0,0};
					rg.tableBody(colnames1,qualification,cellwidth1, alignment1);
				}
			}catch(Exception e){
			e.printStackTrace();
		}
		return rg;
	}
	
	/**
	 * following function is called to get the skill details for the corresponding requisition code
	 * @param reqCode
	 * @param bean
	 * @return
	 */
	
	public ReportGenerator getSkillDets(ReportGenerator rg,String reqCode,ReqsnMisReport bean){
			try{		
			String skillQuery="SELECT ROWNUM,CASE WHEN REQS_SKILL_FUNC_TYPE='E' THEN 'Essential' WHEN REQS_SKILL_FUNC_TYPE='D' THEN 'Desirable' ELSE ' ' END ,NVL(HRMS_SKILL.SKILL_NAME,' '), NVL(REQS_SKILL_FUNC_EXP,''),"
				+" CASE WHEN REQS_SKILL_FUNC_OPTION='A' THEN 'And' WHEN REQS_SKILL_FUNC_OPTION='R' THEN 'Or' ELSE ' ' END ,REQS_FUN_DTL_CODE FROM HRMS_REC_REQS_SKILL_FUNC_DTL LEFT JOIN HRMS_SKILL "
				+" ON HRMS_SKILL.SKILL_ID=HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_CODE "
				+" WHERE REQS_CODE="+reqCode+" AND REQS_FIELD_TYPE='S' " ;		
	
			Object[][] skill = getSqlModel().getSingleResult(skillQuery);
			 if(skill!=null && skill.length>0){
				 if(bean.getReportType().equals("T"))
						rg.addFormatedText("Skill Details :",6,0,0,1);	
				 else
					 rg.addTextBold("Skill Details :" ,0, 0, 0);
				 
				String colnames2[] = {"Sr No","Skill Type", "Skill Name","Experience in Years","Option"};
				int cellwidth2[] = {5,10,10,5,5};
				int alignment2[] = {1,1,0,0,0};
				rg.tableBody(colnames2,skill,cellwidth2, alignment2);
			   }
			}catch(Exception e){
			e.printStackTrace();
		}
		return rg;
	}
	/**
	 * following function is called to get the domain/functional details
	 * @param reqCode
	 * @param bean
	 * @return
	 */
	public ReportGenerator getDomainDets(ReportGenerator rg,String reqCode,ReqsnMisReport bean){
		try{
			String domQuery="SELECT ROWNUM,CASE WHEN REQS_SKILL_FUNC_TYPE='E' THEN 'Essential' WHEN REQS_SKILL_FUNC_TYPE='D' THEN 'Desirable' ELSE ' ' END,NVL(HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_NAME,' '),NVL(REQS_SKILL_FUNC_EXP,''),"
				+" CASE WHEN REQS_SKILL_FUNC_OPTION='A' THEN 'And' WHEN REQS_SKILL_FUNC_OPTION='R' THEN 'Or' ELSE ' ' END ,REQS_FUN_DTL_CODE FROM HRMS_REC_REQS_SKILL_FUNC_DTL LEFT JOIN HRMS_FUNC_DOMAIN_MASTER "
				+" ON HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_CODE=HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_CODE "
				+" WHERE REQS_CODE="+reqCode+" AND REQS_FIELD_TYPE='F' " ;		
	
			Object[][] domain = getSqlModel().getSingleResult(domQuery);
			if(domain!=null && domain.length>0){
				 if(bean.getReportType().equals("T"))
					 rg.addFormatedText("Domain/Functional Details :",6,0,0,1);	
				
				 else
					 rg.addTextBold("Domain/Functional Details:", 0, 0, 0);	 
				 
				String colnames3[] = {"Sr No","Domain Type", "Domain Name","Experience in Years","Option"};
				int cellwidth3[] = { 5,10, 10,5,5};
				int alignment3[] = {1,1,0,0,0};
				rg.tableBody(colnames3,domain,cellwidth3, alignment3);
			 }
	 }catch(Exception e){
		e.printStackTrace();
	 }
		return rg;
	}
	
	/**
	 * following function is called to get the certification details for the corresponding requisition code.
	 * @param reqCode
	 * @param bean
	 * @return
	 */
	public ReportGenerator getCertDets(ReportGenerator rg,String reqCode,ReqsnMisReport bean){
		try{
			String query4="SELECT  ROWNUM,CASE WHEN REQS_CERTI_TYPE='E' THEN 'Essential' when REQS_CERTI_TYPE='D' then 'Desirable' else ' ' end ,NVL(REQS_CERTI_NAME,' '),NVL(REQS_CERTI_ISSUED_BY,' '),NVL(REQS_CERTI_VALID_TILL,' '),CASE WHEN REQS_CERTI_OPTION='A' THEN 'And' WHEN REQS_CERTI_OPTION='R' THEN 'Or' ELSE ' ' END,REQS_CERT_DTL_CODE FROM HRMS_REC_REQS_CERT_DTL"
				+" WHERE REQS_CODE="+reqCode;
			Object[][] cert= getSqlModel().getSingleResult(query4);
			if(cert!=null && cert.length>0){
				if(bean.getReportType().equals("T"))
					 rg.addFormatedText("Certification Details :",6,0,0,1);
				
				else
				   rg.addTextBold("Certification Details:", 0, 0, 0);
				
				String colnames4[] = {"Sr No","Certification Type", "Certification Name","Issued By","Valid Till","Option"};
				int cellwidth4[] = { 5,10, 10,10,5,5};
				int alignment4[] = {1,0,0,0,0,0};
				rg.tableBody(colnames4,cert,cellwidth4, alignment4);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return rg;
	}
	/**
	 * following function is called to generate the report in Pdf or Text or Xls format depending on the report type 
	 * This function is called when the Export All Pages check box is not checked.This function will display only one Manpower
	 * Requisition Data
	 * @param bean
	 * @param request
	 */
	public void getReport(String type,ReqsnMisReport bean,HttpServletRequest request,HttpServletResponse response){
		try{
		
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,"ManPower Requisition Report");
		
		if(type.equals("Pdf")){
			rg.setFName("Manpower Requisition Report");
			  rg.addFormatedText("Manpower Requisition Report",6,0,1,1);	
			  rg.addText("\n",0,0,0);	
			}else if(type.equals("Xls")){
			 rg.setFName("Manpower Requisition Report.xls");
			  rg.addText("Manpower Requisition Report",0,1,0);	
			  rg.addText("\n",0,0,0);	
			}else{
			  rg.setFName("Manpower Requisition Report.doc");
			  rg.addFormatedText("Manpower Requisition Report",6,0,1,0);	
			}
		
		rg.addText("\n",0,0,0);
		
				
					Object manpow[][] = new Object[6][7];		 
					Object job[][] = new Object[5][3];		
					Object vac[][] = new Object[2][7];
					int[] bcellWidth = { 10,3,15,7,10,3,15};
					int[] bcellAlign = {0,0,0,0,0,0,0};
					int[] jobCellWidth={10,3,50};
					int[] jobCellAlign={0,0,0}; 
					
					manpow[0][0] = "Requisition Code";manpow[0][1]=":";manpow[0][2]=""+bean.getItReqName1();manpow[0][3]="";manpow[0][4]="Requisition Date";manpow[0][5]=":";manpow[0][6]=""+bean.getItReqDate1();
					manpow[1][0] = "Position";manpow[1][1]=":";manpow[1][2]=""+bean.getItPostition();manpow[1][3]="";manpow[1][4]="Approval Status";manpow[1][5]=":";manpow[1][6]=""+bean.getItApprStatus();
					manpow[2][0] = "Division";manpow[2][1]=":";manpow[2][2]=""+bean.getItDiv() ;manpow[2][3]="";manpow[2][4]="Requisition Status";manpow[2][5]=":";manpow[2][6]=""+bean.getItStatus();;		
					manpow[3][0] = "Branch";manpow[3][1]=":";manpow[3][2]=""+bean.getItBranch();manpow[3][3]="";manpow[3][4]="";manpow[3][5]="";manpow[3][6]="";
					manpow[4][0] = "Department";manpow[4][1]=":";manpow[4][2]=""+bean.getItDept();manpow[4][3]="";manpow[4][4]="";manpow[4][5]="";manpow[4][6]=""; 
					manpow[5][0] = "Hiring Manager";manpow[5][1]=":";manpow[5][2]=""+bean.getItHirmanger();manpow[5][3]="";manpow[5][4]="Recruitment Type";manpow[5][5]=":";manpow[5][6]=bean.getItReqType1()+"  "+bean.getItReqType() ;
					rg.addTextBold("Manpower Information :", 0, 0, 0);
				
					if(type.equals("Pdf")){
						rg.tableBodyNoCellBorder(manpow, bcellWidth,bcellAlign,0);
					}else{
						rg.tableBody(manpow, bcellWidth,bcellAlign);
					}
					rg.addTextBold("Job Description :", 0, 0, 0);
				
					
					job[0][0] ="Job Name";job[0][1] = ": ";job[0][2]=""+bean.getItJobDecName();
					job[1][0] ="Job Description";job[1][1]=":";job[1][2]=""+bean.getItJobDec();
					job[2][0]="Job Roles and Responsibilty";job[2][1]=":";job[2][2]=""+bean.getItRoleperson();
					job[3][0] = "Special Requirement";job[3][1]=":";job[3][2]=""+bean.getItSpecReq();
					job[4][0] = "Personal Qualities";job[4][1]=":";job[4][2]=""+bean.getItPerReq();
					if(type.equals("Pdf")){
						rg.tableBodyNoCellBorder(job, jobCellWidth,jobCellAlign,0);
					}else{
						rg.tableBody(job, jobCellWidth,jobCellAlign);
					}
					rg=getVacancy(rg, bean.getItReqCode1(),bean);
					
					vac[0][0] = "Compensation In Lacs";
					vac[0][1] = ":";
					vac[0][2] =""+bean.getItVacanCompn();
					vac[0][3]="";
					vac[0][4] = "Experience";
					vac[0][5] =":";
					vac[0][6]=" Min :"+(bean.getItVacexpMin().equals("null") ?"":bean.getItVacexpMin())+" "+"Max :"+(bean.getItVacexpMax().equals("null") ?"":bean.getItVacexpMax()) ;
						
					vac[1][0] = "Vacancy Type";
					vac[1][1] = ":";
					vac[1][2]=""+bean.getItVacType();
					vac[1][3]="";
					vac[1][4] = "Contract Type:";
					vac[1][5] = ": ";
					vac[1][6]=""+(bean.getItVacanContract().equals("null") ?"":bean.getItVacanContract())+"  "+(bean.getItVacConType().equals("null") ?"":bean.getItVacConType()) ;
					if(type.equals("Pdf")){
						rg.tableBodyNoCellBorder(vac,bcellWidth,bcellAlign,0);
					}else{
					    rg.tableBody(vac,bcellWidth,bcellAlign) ;
					}
					
					rg=getQuaDets(rg,bean.getItReqCode1(),bean);
					rg=getSkillDets(rg,bean.getItReqCode1(),bean);
					rg=getDomainDets(rg,bean.getItReqCode1(),bean);
					rg=getCertDets(rg,bean.getItReqCode1(),bean);
					rg=getApprvDet(rg,bean.getItReqCode1(),bean);
					rg.pageBreak();  
                  rg.createReport(response);
		}catch(Exception e){
			e.printStackTrace();
		}
	  }//End of method
	
	
	 public void doPaging(ReqsnMisReport bean, int empLength, Object[][] data, HttpServletRequest request)
		{
			try
			{
				/*
				 * totalRec -: No. of records per page
				 * fromTotRec -: Starting No. of record to show on a current page
				 * toTotRec -: Last No. of record to show on a current page
				 * pageNo -: Current page to show
				 * totalPage -: Total No. of Pages
				*/
				
				/*String pagingSql = " SELECT CONF_RECORDS_PER_PAGE FROM HRMS_SALARY_CONF ";
				Object[][] pagingObj = getSqlModel().getSingleResult(pagingSql);*/
				int totalRec =1;// Integer.parseInt(String.valueOf(pagingObj[0][0])); 
				int pageNo = 1;
				int fromTotRec = 0;
				int toTotRec = totalRec; 
				int totalPage = 0; 
				
				java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal((double)empLength / totalRec);
				totalPage = Integer.parseInt(bigDecRow1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
				
				if(String.valueOf(bean.getHdPage()).equals("null")||String.valueOf(bean.getHdPage()).equals(null)||String.valueOf(bean.getHdPage()).equals(""))
				{
					pageNo = 1;
					fromTotRec = 0;
					toTotRec = totalRec;

					if(toTotRec > empLength)
					{
						toTotRec = empLength;
					}
					bean.setHdPage("1");
				}
				else
				{   	pageNo = Integer.parseInt(bean.getHdPage());
							  
						if(pageNo == 1)
						{
							fromTotRec = 0;
							toTotRec = totalRec;
						}
						else
						{
							toTotRec = toTotRec * pageNo;
							fromTotRec = toTotRec - totalRec;
						}
						if(toTotRec > empLength)
						{
							toTotRec = empLength;
						}
									
				}
				
				bean.setFromTotRec(String.valueOf(fromTotRec));
				bean.setToTotRec(String.valueOf(toTotRec));
				
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("pageNo", pageNo); 
				request.setAttribute("fromTotRec", fromTotRec);
				request.setAttribute("toTotRec", toTotRec);  
				
				 
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	/**
	 * following function is called to display the vacancy details in the Jsp Page when Show Report button is clicked. 
	 * @param bean
	 */
	 public void jspViewVacDet(ReqsnMisReport bean){
		 
			String query = "SELECT NVL(VACAN_NUMBERS,0),NVL(TO_CHAR(VACAN_REQ_DATE,'DD-MM-YYYY'),' ') FROM HRMS_REC_REQS_VACDTL" +
			" WHERE REQS_CODE="+bean.getItReqCode1();
			if(bean.getAdvVacOpt().equals("IE")){
				query+=" AND VACAN_NUMBERS="+bean.getAdvVacVal();
			}
			
			if(bean.getAdvVacOpt().equals("LT")){
				query+=" AND VACAN_NUMBERS < "+bean.getAdvVacVal();
			}
			
			if(bean.getAdvVacOpt().equals("GT")){
				query+=" AND VACAN_NUMBERS > "+bean.getAdvVacVal();
			}
			
			if(bean.getAdvVacOpt().equals("LE")){
				query+=" AND VACAN_NUMBERS <= "+bean.getAdvVacVal();
			}
			
			if(bean.getAdvVacOpt().equals("GT")){
				query+=" AND VACAN_NUMBERS >="+bean.getAdvVacVal();
			}
			
			Object[][] vacDets = getSqlModel().getSingleResult(query);
			ArrayList<Object> list=new ArrayList<Object>();
				if(vacDets!=null && vacDets.length >0){	
					for(int i=0;i<vacDets.length;i++){
						ReqsnMisReport bean1=new ReqsnMisReport();	
						bean1.setNoOfVac(String.valueOf(vacDets[i][0]));//No. of Vacancies
						bean1.setVacDate(String.valueOf(vacDets[i][1]));//Required by Date
						list.add(bean1);
						
					}
				bean.setVacList(list);	
		 
				}	
		 
		 
	 }
	 
	 /**
	  * following function is called to display the Qualification Details when Show Report button is clicked in the Jsp Page
	  * @param bean
	  */
	 public void jspViewQuaDets(ReqsnMisReport bean){
			try{
			String query="SELECT CASE WHEN HRMS_REC_REQS_QUA_DTL.REQS_QUA_TYPE='E' THEN 'Essential' WHEN HRMS_REC_REQS_QUA_DTL.REQS_QUA_TYPE='D' THEN 'Desirable' else ' ' end ,NVL(HRMS_QUA.QUA_ABBR,' '),CASE WHEN QUA_LEVEL='UG' THEN 'Under Graduate' "
				+" WHEN QUA_LEVEL='DI' THEN 'Diploma' WHEN QUA_LEVEL='GR' THEN 'Graduate' WHEN QUA_LEVEL='PG' THEN 'Post Graduate'"
				+" WHEN QUA_LEVEL='PH' THEN 'Phd' WHEN QUA_LEVEL='DO' THEN 'Doctorate' ELSE ' ' END,"
				+" NVL(HRMS_SPECIALIZATION.SPEC_ABBR,' '),REQS_QUA_CUTOFF,CASE WHEN REQS_QUA_OPTION='A' THEN 'And' WHEN REQS_QUA_OPTION='R' THEN 'Or' ELSE ' ' END"
				+" ,REQS_QUA_DTL_CODE FROM HRMS_REC_REQS_QUA_DTL LEFT JOIN HRMS_QUA ON HRMS_QUA.QUA_ID=HRMS_REC_REQS_QUA_DTL.REQS_QUA_CODE "
				+" LEFT JOIN HRMS_SPECIALIZATION ON HRMS_REC_REQS_QUA_DTL.REQS_SPECIALIZATION_CODE=HRMS_SPECIALIZATION.SPEC_ID"
				+" WHERE HRMS_REC_REQS_QUA_DTL.REQS_CODE="+bean.getItReqCode1();
				
			Object[][] qualification = getSqlModel().getSingleResult(query);
			ArrayList<Object> list=new ArrayList<Object>();
				if(qualification!=null && qualification.length>0){	
					bean.setQualFlag("true");
				  for(int i=0;i<qualification.length;i++){
					  ReqsnMisReport bean1=new ReqsnMisReport();
					  bean1.setQualType(String.valueOf(qualification[i][0]));//Qualification type
					  bean1.setQualName(String.valueOf(qualification[i][1]));//Qualification Abbr
					  bean1.setQualLvl(String.valueOf(qualification[i][2]));//Qualification Level
					  bean1.setSpl(String.valueOf(qualification[i][3]));//Specialization Abbr
					  bean1.setCutOff(String.valueOf(qualification[i][4]));//Cut Off Marks
					  bean1.setSel(String.valueOf(qualification[i][5]));//Qualification Option
					  list.add(bean1);
				  }
				bean.setQualList(list);
					
				}else{
					bean.setQualFlag("false");
				}
			}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	 /**
	  * following function is called to display the domain/functional details in the jsp page when Show Report button is clicked. 
	  * @param bean
	  */
	 public void jspViewDom(ReqsnMisReport bean){
			try{
				String domQuery="SELECT CASE WHEN REQS_SKILL_FUNC_TYPE='E' THEN 'Essential' WHEN REQS_SKILL_FUNC_TYPE='D' THEN 'Desirable' ELSE ' ' END,NVL(HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_NAME,' '),NVL(REQS_SKILL_FUNC_EXP,''),"
					+" CASE WHEN REQS_SKILL_FUNC_OPTION='A' THEN 'And' WHEN REQS_SKILL_FUNC_OPTION='R' THEN 'Or' ELSE ' ' END ,REQS_FUN_DTL_CODE FROM HRMS_REC_REQS_SKILL_FUNC_DTL LEFT JOIN HRMS_FUNC_DOMAIN_MASTER "
					+" ON HRMS_FUNC_DOMAIN_MASTER.FUNC_DOMAIN_CODE=HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_CODE "
					+" WHERE REQS_CODE="+bean.getItReqCode1()+" AND REQS_FIELD_TYPE='F' " ;		
				ArrayList<Object> list=new ArrayList<Object>();
				Object[][] domain = getSqlModel().getSingleResult(domQuery);
				if(domain!=null && domain.length>0){
					bean.setDomFlag("true");
					for(int i=0;i<domain.length;i++){
						ReqsnMisReport bean1=new ReqsnMisReport();
						bean1.setDomType(String.valueOf(domain[i][0]));//Domain Type 
						bean1.setDomName(String.valueOf(domain[i][1]));//Domain Name
						bean1.setDomExp(String.valueOf(domain[i][2]));//Domain Exp
						bean1.setDomSel(String.valueOf(domain[i][3]));//Domain Option
						list.add(bean1);
					}
					bean.setDomList(list);
				 }else{
					 bean.setDomFlag("false");
				 }
		 }catch(Exception e){
			e.printStackTrace();
		 }
			
		}
	 
	 /**
	  * following function is called to display the skill details in the jsp page 
	  * when show report button is clicked  
	  * @param bean
	  */
	 public void jspViewSkill(ReqsnMisReport bean){
		 try{
			 String skillQuery="SELECT CASE WHEN REQS_SKILL_FUNC_TYPE='E' THEN 'Essential' WHEN REQS_SKILL_FUNC_TYPE='D' THEN 'Desirable' ELSE ' ' END ,NVL(HRMS_SKILL.SKILL_NAME,' '), NVL(REQS_SKILL_FUNC_EXP,''),"
					+" CASE WHEN REQS_SKILL_FUNC_OPTION='A' THEN 'And' WHEN REQS_SKILL_FUNC_OPTION='R' THEN 'Or' ELSE ' ' END ,REQS_FUN_DTL_CODE FROM HRMS_REC_REQS_SKILL_FUNC_DTL LEFT JOIN HRMS_SKILL "
					+" ON HRMS_SKILL.SKILL_ID=HRMS_REC_REQS_SKILL_FUNC_DTL.REQS_SKILL_FUNC_CODE "
					+" WHERE REQS_CODE="+bean.getItReqCode1()+" AND REQS_FIELD_TYPE='S' " ;		
	
			Object[][] skill = getSqlModel().getSingleResult(skillQuery);
			ArrayList<Object> list=new ArrayList<Object>();
			if(skill!=null && skill.length>0){
				bean.setSkillFlag("true");
					for(int i=0;i<skill.length;i++){
						ReqsnMisReport bean1=new ReqsnMisReport();
						bean1.setSkillType(String.valueOf(skill[i][0]));//Skill type
						bean1.setSkillName(String.valueOf(skill[i][1]));//Skill Name
						if(String.valueOf(skill[i][2]).equals("null") || String.valueOf(skill[i][2]).equals("") || String.valueOf(skill[i][2]).equals(" "))
							bean1.setSkillExp("");
						else
							bean1.setSkillExp(String.valueOf(skill[i][2]));//Skill Experience
						bean1.setSkillSel(String.valueOf(skill[i][3]));//Skill Option
						list.add(bean1);
				}
			bean.setSkillList(list);
				
			}else{
				bean.setSkillFlag("false");
			}
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
	 }
	 
	 /**
	  * following function is called to display the certification details in the jsp page when 
	  * Show report button is clicked.
	  * @param bean
	  */
	 
		public void jspViewCert(ReqsnMisReport bean){
			try{
				String query4="SELECT  CASE WHEN REQS_CERTI_TYPE='E' THEN 'Essential' when REQS_CERTI_TYPE='D' then 'Desirable' else ' ' end ,NVL(REQS_CERTI_NAME,' '),NVL(REQS_CERTI_ISSUED_BY,' '),NVL(REQS_CERTI_VALID_TILL,' '),CASE WHEN REQS_CERTI_OPTION='A' THEN 'And' WHEN REQS_CERTI_OPTION='R' THEN 'Or' ELSE ' ' END,REQS_CERT_DTL_CODE FROM HRMS_REC_REQS_CERT_DTL "
					+" WHERE REQS_CODE="+bean.getItReqCode1();
				Object[][] cert= getSqlModel().getSingleResult(query4);
				ArrayList<Object> list=new ArrayList<Object>();
				if(cert!=null && cert.length>0){
					bean.setCertFlag("true");
					for(int i=0;i<cert.length;i++){
						ReqsnMisReport bean1=new ReqsnMisReport();
						bean1.setCertType(String.valueOf(cert[i][0]));//Certification Type
						bean1.setCertName(String.valueOf(cert[i][1]));//Certification Name
						bean1.setCertIssueBy(String.valueOf(cert[i][2]));//Issued By
						bean1.setCertValid(String.valueOf(cert[i][3]));//Valid Till
						bean1.setCertOption(String.valueOf(cert[i][4]));//Certification Option
						list.add(bean1);
					}
					bean.setCertList(list);
				}else{
					bean.setCertFlag("false");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}
		/**
		 * following function is called to display the approver details in the jsp page when the Show report button 
		 * is clicked.
		 * @param bean
		 */
		public void jspViewApprv(ReqsnMisReport bean){
			try{
				String apprvQuery="  SELECT DISTINCT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,NVL(TO_CHAR(PATH_APPR_DATE,'DD-MM-YYYY'),' '),"
					 +" NVL(HRMS_REC_REQS_PATH.PATH_REMARK,' ') FROM HRMS_REC_REQS_PATH " 
				     +" INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_REQS_PATH.PATH_REQ_CODE)"
					 +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_REQS_PATH.PATH_APPROVER_CODE)"
				     +" WHERE REQS_CODE="+bean.getItReqCode1();
					Object[][] apprvData = getSqlModel().getSingleResult(apprvQuery);
					ArrayList<Object> list=new ArrayList<Object>();
				if(apprvData!=null && apprvData.length>0){
					bean.setApprvFlag("true");
					for(int i=0;i<apprvData.length;i++){
						ReqsnMisReport bean1=new ReqsnMisReport();
						bean1.setApprvName(String.valueOf(apprvData[i][0]));//Approver Name
						bean1.setApprvDate(String.valueOf(apprvData[i][1]));//Approved Date
						bean1.setApprvRem(String.valueOf(apprvData[i][2]));//Approver's Remark
						list.add(bean1);
					}
					bean.setApprvList(list);
				}else{
					bean.setApprvFlag("false");
				}
						
				
				}catch(Exception e){
					e.printStackTrace();
					
				}
				
			}
		
	/**
	 * following function is called to display the requisition header information when the show report button is clicked in the jsp page.
	 * This function also display the job description details.
	 * @param bean
	 * @param request
	 * @param response
	 */	
	 public void callJspView (ReqsnMisReport bean,HttpServletRequest request,HttpServletResponse response)
	 {
			String query ="SELECT NVL(REQS_NAME,' '),NVL(RANK_NAME,' '),DECODE(REQS_STATUS,'O','Open','C','Close')," +
			" NVL(DIV_NAME,' '),NVL(CENTER_NAME,' '),NVL(DEPT_NAME,' '),DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition')," +
			" OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||' '||OFFC1.EMP_LNAME,CASE WHEN REQS_RECTYPE_INT='Y' THEN 'Internal' ELSE ' ' END," +
			" OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME,NVL(REQS_JOBDESC_NAME,' ')," +
			" NVL(REQS_JOB_DESCRIPTION,' '),NVL(REQS_ROLE_RESPON,' '),NVL(REQS_SPECIAL_REQ,' '),NVL(REQS_PERSONEL_REQ,' '),REQS_VACAN_COMPEN," +
			" NVL(REQS_VACAN_EXPMIN,''),NVL(REQS_VACAN_EXPMAX,''),decode(REQS_VACAN_TYPE,'N','New Post','R','Replacement','',''),decode(REQS_VACAN_CONTRACT,'R','Regular','O','On Contract','','')," +
			" decode(REQS_VACAN_CONTYPE,'P','Part Time','F','Full Time'),REQS_APPR_CODE,REQS_LEVEL,CASE WHEN REQS_RECTYPE_EXT='Y' THEN 'External' ELSE ' ' END,NVL(TO_CHAR(REQS_DATE,'DD-MM-YYYY'),' '),REQS_CODE FROM HRMS_REC_REQS_HDR " +
			" LEFT JOIN HRMS_EMP_OFFC OFFC1 ON OFFC1.EMP_ID = HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER " +
			" LEFT JOIN HRMS_EMP_OFFC OFFC ON OFFC.EMP_ID = HRMS_REC_REQS_HDR.REQS_APPLIED_BY " +
			" LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION " +
			" LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_REC_REQS_HDR.REQS_DIVISION " +
			" LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_REC_REQS_HDR.REQS_DEPT " +
			" LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_REC_REQS_HDR.REQS_BRANCH " +
			" WHERE 1=1"; 
			
			if(!(bean.getDivCode().equals("") || bean.getDivCode().equals("null"))){
				query+=" AND HRMS_REC_REQS_HDR.REQS_DIVISION="+bean.getDivCode();
			}
			
			if(!(bean.getBrnCode().equals("") || bean.getBrnCode().equals("null"))){
				query+=" AND HRMS_REC_REQS_HDR.REQS_BRANCH="+bean.getBrnCode();
			}
			
			if(!(bean.getDeptCode().equals("") || bean.getDeptCode().equals("null"))){
				query+=" AND HRMS_REC_REQS_HDR.REQS_DEPT="+bean.getDeptCode();
			}
			
			if(!(bean.getPosCode().equals("") || bean.getPosCode().equals("null"))){
				query+=" AND HRMS_REC_REQS_HDR.REQS_POSITION="+bean.getPosCode();
			}
			
			
			
			if(bean.getDateFilter().equals("O ")){
				if(!(bean.getFrmDate()==null) && !bean.getFrmDate().equals("")){
					query+= " AND HRMS_REC_REQS_HDR.REQS_DATE= TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
				}
		    }
			
				
		   if(bean.getDateFilter().equals("B ")){
				if(!(bean.getFrmDate()==null)&&!bean.getFrmDate().equals("")){
					query+= " AND HRMS_REC_REQS_HDR.REQS_DATE < TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
				}
			}
			
		   if(bean.getDateFilter().equals("A ")){
				if(!(bean.getFrmDate()==null)&&!bean.getFrmDate().equals("")){
					query+= " AND HRMS_REC_REQS_HDR.REQS_DATE > TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
				}
			}
			
		   if(bean.getDateFilter().equals("OB")){
				if(!(bean.getFrmDate()==null)&&!bean.getFrmDate().equals("")){
					query+= " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
				}
			}
			
		   if(bean.getDateFilter().equals("OA")){
				if(!(bean.getFrmDate()==null)&&!bean.getFrmDate().equals("")){
					query+= " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
				}
			}
			
			
		   if(bean.getDateFilter().equals("F ")){
				if(!(bean.getFrmDate().equals("dd-mm-yyyy"))){
					query+= " AND HRMS_REC_REQS_HDR.REQS_DATE >=TO_DATE("+"'"+bean.getFrmDate()+"'"+",'DD-MM-YYYY')";
				}
				
		   if(!(bean.getTDate()==null) && !bean.getTDate().equals("")){
			   	if(!(bean.getTDate().equals("dd-mm-yyyy")))
					query+= " AND HRMS_REC_REQS_HDR.REQS_DATE <=TO_DATE("+"'"+bean.getTDate()+"'"+",'DD-MM-YYYY')";
				}
				
		   }
		
			
			if(!(bean.getSelectedReq().equals("") || bean.getSelectedReq().equals("null"))){
				query+=" AND REQS_CODE IN("+bean.getSelectedReq()+")";
			}
			
			
			if(!(bean.getHiringMgrId().equals("null") || bean.getHiringMgrId().equals(""))){
				query+=" AND HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER="+bean.getHiringMgrId();
			}
			
			if(!(bean.getReqsStatus().equals("1"))){
				query+=" and reqs_status='"+bean.getReqsStatus()+"'";
			}
		
		 
			if(!bean.getFirstSort().equals("1") || !bean.getSecondSort().equals("1") || !bean.getThirdSort().equals("1")){
				
				query+=" ORDER BY ";
			}else{
				query+=" ORDER BY NVL(TO_CHAR(REQS_DATE,'DD-MM-YYYY'),' ') DESC";
			}
			
			if(!bean.getFirstSort().equals("1")){
				query+=" "+getSortingValue(bean.getFirstSort());
				if(bean.getHidFirstAsc().equals("A")){
					query+=" "+getSortOrder("A"); 
				}else{
					query+=" "+getSortOrder("D"); 
				}
			}
			
			if(!bean.getSecondSort().equals("1")){
				if(!bean.getFirstSort().equals("1")){
					query+=",";
				}
				query+=" "+getSortingValue(bean.getSecondSort());
				if(bean.getHidSecAsc().equals("A")){
					query+=" "+getSortOrder("A"); 
				}else{
					query+=" "+getSortOrder("D"); 
				}
			}
			
			if(!bean.getThirdSort().equals("1")){
				if(!bean.getFirstSort().equals("1") || !bean.getSecondSort().equals("1")){
					query+=",";	
				}
				query+=" "+getSortingValue(bean.getThirdSort());
				if(bean.getHidThirdAsc().equals("A")){
					query+=" "+getSortOrder("A"); 
				}else{
					query+=" "+getSortOrder("D"); 
				}
			}
		     
		     
		 Object[][] data= getSqlModel().getSingleResult(query); 
		 
		 if(data!=null && data.length>0){  
			    doPaging(bean, data.length, data, request);
				int fromTotRec = Integer.parseInt(bean.getFromTotRec());
				int toTotRec = Integer.parseInt(bean.getToTotRec()); 
			 
			 if(data!=null && data.length >0)
				{ 
				  int pg =  Integer.parseInt(bean.getHdPage()); 
					for (int i = 0,j=1; i < data.length; i++,j++) { 
						if(pg==j){
							bean.setItReqName1(""+data[i][0]);//Requisition Name
							bean.setItPostition(""+data[i][1]); //Position
							bean.setItStatus(""+data[i][2]); //Requisition Status
							bean.setItDiv(""+data[i][3]);//Division
							bean.setItBranch(""+data[i][4]);//Branch
							bean.setItDept(""+data[i][5]);//Department
							bean.setItApprStatus(""+data[i][6]);//Requisition Approval Status
							bean.setItHirmanger(""+data[i][7]);//Hiring Manager
							bean.setItReqType1(""+data[i][8]);//Internal
							bean.setItCandiName(""+data[i][9]);//Requisition Applied By
							bean.setItJobDecName(""+data[i][10]);//Job Description Name
							bean.setItJobDec(""+data[i][11]);//Job Description
							bean.setItRoleperson(""+data[i][12]);//Roles and Responsibility
							bean.setItSpecReq(""+data[i][13]);//Special Requirement
							bean.setItPerReq(""+data[i][14]); //Personal Requirement
							bean.setItVacanCompn(""+Utility.checkNull(String.valueOf(data[i][15])));//Compensation
							bean.setItVacexpMin(""+Utility.checkNull(String.valueOf(data[i][16])));//Min Experience
							bean.setItVacexpMax(""+Utility.checkNull(String.valueOf(data[i][17])));//Max Experience
							bean.setItVacType(""+Utility.checkNull(String.valueOf(data[i][18])));//Vac Type
							bean.setItVacanContract(""+Utility.checkNull(String.valueOf(data[i][19]))); //Vacancy Contract 
							bean.setItVacConType(""+Utility.checkNull(String.valueOf(data[i][20])));//Contract Type
							bean.setItReqApprCode(""+data[i][21]); //Requisition Approver Code
							bean.setItReqLevel(""+data[i][22]);//Requisition level
							bean.setItReqType(""+data[i][23]);//Recruitment Type  
							bean.setItReqDate1(""+data[i][24]);//Requisition Date
							bean.setItReqCode1(""+data[i][25]); //Requisition Code 
						}
					} 
				}
			 jspViewVacDet(bean);//is called to display the Vacancy Details
			 jspViewQuaDets(bean);//is called to display the Qualification Details
			 jspViewSkill(bean);//is called to display the skill details
			 jspViewDom(bean);//is called to display the domain details 
			 jspViewCert(bean);//is called to display the certification details
			 jspViewApprv(bean);//is called to display the approval details
		 }
		 
	   } 
	 
/**
 * following function is called to save the setting details into the database
 * @param bean
 * @return
 */	 
	public boolean saveFilters(ReqsnMisReport bean){
				Object[][] addFilt=new Object[1][11];
				Object[][] addSort=new Object[1][7];
				Object[][] addAdv=new Object[1][5];
				Object[][] addReqs=new Object[1][2];
			/**
			 * following code is used to insert the filter option details
			 */
				addFilt[0][0]=bean.getDivCode();
				addFilt[0][1]=bean.getBrnCode();
				addFilt[0][2]=bean.getDeptCode();
				addFilt[0][3]=bean.getPosCode();
				if(!bean.getDateFilter().equals("1"))
				addFilt[0][4]=bean.getDateFilter();
				else
					addFilt[0][4]=String.valueOf("");
			
				
				if(bean.getFrmDate().equals("dd-mm-yyyy")){
					addFilt[0][5]="";
				}else{
				addFilt[0][5]=bean.getFrmDate();
				}
				
				addFilt[0][6]=bean.getToDate();
			
				if(bean.getHidReportView().equals("checked")){
					addFilt[0][7]=String.valueOf("V");
				}else{
					addFilt[0][7]=String.valueOf("R");
				}
				
				if(!bean.getReportType().equals("1"))
				addFilt[0][8]=bean.getReportType();
				else
					addFilt[0][8]=String.valueOf("");
				
				addFilt[0][9]=bean.getSettingName();
			
				addFilt[0][10]=bean.getUserEmpId();
				
				boolean result=getSqlModel().singleExecute(getQuery(1),addFilt);
				if(result){
					
					String query="SELECT MAX(MPR_FIL_CODE) FROM HRMS_REC_MPR_FILTERS";
					Object [][] data = getSqlModel().getSingleResult(query);
					if(data!=null && data.length>0){
						bean.setMraRepCode(String.valueOf(data[0][0]));
					}
	     		}
			/**
			 * following code is used to insert sorting details
			 */
				addSort[0][0]=bean.getMraRepCode();
				addSort[0][1]=bean.getFirstSort();
				if(bean.getHidFirstAsc().equals("A"))
				  addSort[0][2]=bean.getHidFirstAsc();
			    else
				  addSort[0][2]=String.valueOf("D");
			    addSort[0][3]=bean.getSecondSort();
			    if(bean.getHidSecAsc().equals("A"))
					  addSort[0][4]=bean.getHidSecAsc();
				 else
					  addSort[0][4]=String.valueOf("D");
				    
			    addSort[0][5]=bean.getThirdSort();
			    if(bean.getHidThirdAsc().equals("A"))
			    addSort[0][6]=bean.getHidThirdAsc();
			    else
			    	addSort[0][6]=String.valueOf("D");	
			    
			    getSqlModel().singleExecute(getQuery(2),addSort);
		    /**
		     * following code is used to insert the advance filter details into the datavbase. 
		     */
		    
			    addAdv[0][0]=bean.getMraRepCode();
			    addAdv[0][1]=bean.getHiringMgrId();
			    addAdv[0][2]=bean.getReqsStatus();
			    addAdv[0][3]=bean.getAdvVacOpt();
			    addAdv[0][4]=bean.getAdvVacVal();
			    getSqlModel().singleExecute(getQuery(3),addAdv); 
		
		    /**
			   * FOLLOWING code is used to insert the selected requisition details
			   */
				  if(bean.getSelectedReq().length()>0 && bean.getSelectedReq()!=null){	
						String[] str=bean.getSelectedReq().split(",");
						for(int i=0;i<str.length;i++){
							addReqs[0][0]=bean.getMraRepCode();
							addReqs[0][1]=str[i];
							getSqlModel().singleExecute(getQuery(4),addReqs);
					    }
					  }	
		
		return result;
	}
	
/**
 * following function is called to update the setting details 
 * @param bean
 * @return
 */	
	public boolean updateSettings(ReqsnMisReport bean){

				Object[][] updateFilt=new Object[1][10];
				Object[][] updateSort=new Object[1][7];
				Object[][] updateAdv=new Object[1][5];
				Object[][] updateReqs=new Object[1][2];
			/**
			 * following code is used to update the filter option details
			 */
				updateFilt[0][0]=bean.getDivCode();
				updateFilt[0][1]=bean.getBrnCode();
				updateFilt[0][2]=bean.getDeptCode();
				updateFilt[0][3]=bean.getPosCode();
				if(bean.getDateFilter().equals("1")){
					updateFilt[0][4]=String.valueOf("");
				}else{
				    updateFilt[0][4]=bean.getDateFilter();
				}
			
				if(bean.getFrmDate().equals("dd-mm-yyyy")){
					updateFilt[0][5]=String.valueOf("");
				}else{
				   updateFilt[0][5]=bean.getFrmDate();
				}
				
				if(bean.getToDate().equals("dd-mm-yyyy")){
					updateFilt[0][6]=String.valueOf("");
				}else{
				   updateFilt[0][6]=bean.getToDate();
				}
			
				
				if(bean.getHidReportView().equals("checked")){
					updateFilt[0][7]=String.valueOf("V");
				}else{
					updateFilt[0][7]=String.valueOf("R");
				}
				
				if(bean.getReportType().equals("1")){
					updateFilt[0][8]=String.valueOf("");
				}else{
					updateFilt[0][8]=bean.getReportType();
				}
				
				updateFilt[0][9]=bean.getMraRepCode();
				
				boolean result=getSqlModel().singleExecute(getQuery(5),updateFilt);
			
			
			/**
			 * following code is used to update sorting details
			 */
				
				updateSort[0][0]=bean.getFirstSort();
				if(bean.getHidFirstAsc().equals("A"))
					updateSort[0][1]=bean.getHidFirstAsc();
				    else
				    	updateSort[0][1]=String.valueOf("D");
				updateSort[0][2]=bean.getSecondSort();
				    if(bean.getHidSecAsc().equals("A"))
				    	updateSort[0][3]=bean.getHidSecAsc();
					 else
						 updateSort[0][3]=String.valueOf("D");
					    
				    updateSort[0][4]=bean.getThirdSort();
				    if(bean.getHidThirdAsc().equals("A"))
				    	updateSort[0][5]=bean.getHidThirdAsc();
				    else
				    	updateSort[0][5]=String.valueOf("D");	
				    updateSort[0][6]=bean.getMraRepCode();
			    
			    getSqlModel().singleExecute(getQuery(6),updateSort);
			   
		    /**
		     * following code is used to update the advance filter details into the database. 
		     */
		    
			    updateAdv[0][0]=bean.getHiringMgrId();
			    updateAdv[0][1]=bean.getReqsStatus();
			    updateAdv[0][2]=bean.getAdvVacOpt();
			    updateAdv[0][3]=bean.getAdvVacVal();
			    updateAdv[0][4]=bean.getMraRepCode();
			    getSqlModel().singleExecute(getQuery(7),updateAdv); 
		
		    /**
			   * FOLLOWING code is used to insert the selected requisition details
			   */String query="DELETE FROM HRMS_REC_MPR_REQS WHERE MPR_FIL_CODE=? ";
			    Object[][] del=new Object[1][1];
				del[0][0]=bean.getMraRepCode();
				getSqlModel().singleExecute(query,del);
				
				  if(bean.getSelectedReq().length()>0 && bean.getSelectedReq()!=null){	
						String[] str=bean.getSelectedReq().split(",");
						for(int i=0;i<str.length;i++){
							updateReqs[0][0]=bean.getMraRepCode();
							updateReqs[0][1]=str[i];
							getSqlModel().singleExecute(getQuery(4),updateReqs);
					    }
					  }	
				
return result;

	}
/**
 * following method is called to select the setting details and display them in the jsp
 * @param bean
 */	
public void getSettingDetails(ReqsnMisReport bean){
	try{
		bean.setScrnFlg("true");
/**
 * following query is used to select the filter option details.
 */		
		String filtQuery="SELECT DIV_NAME,MPR_FIL_DIV,CENTER_NAME,MPR_FIL_BRN,DEPT_NAME,MPR_FIL_DEPT,RANK_NAME,MPR_FIL_POSITION,"
						+" MPR_FIL_DATE_OPT,TO_CHAR(MPR_FIL_FRM_DATE,'DD-MM-YYYY'),TO_CHAR(MPR_FIL_TO_DATE,'DD-MM-YYYY'),MPR_REP_FIL_STATUS,MPR_FIL_REP_OPT,"
						+" MPR_FIL_USER_NAME FROM HRMS_REC_MPR_FILTERS "
						+" LEFT JOIN HRMS_DIVISION ON DIV_ID=MPR_FIL_DIV "
						+" LEFT JOIN HRMS_CENTER ON CENTER_ID=MPR_FIL_BRN"
						+" LEFT JOIN HRMS_DEPT ON DEPT_ID=MPR_FIL_DEPT"
						+" LEFT JOIN HRMS_RANK ON RANK_ID=MPR_FIL_POSITION"
						+" WHERE MPR_FIL_CODE="+bean.getMraRepCode();
		  Object [][] data = getSqlModel().getSingleResult(filtQuery);
		  if(data!=null && data.length>0){
			 
					    bean.setDivName(checkNull(String.valueOf(data[0][0])));
					    bean.setDivCode(checkNull(String.valueOf(data[0][1])));
					    bean.setBrnName(checkNull(String.valueOf(data[0][2])));			    
					    bean.setBrnCode(checkNull(String.valueOf(data[0][3])));
					    bean.setDeptName(checkNull(String.valueOf(data[0][4])));
					    bean.setDeptCode(checkNull(String.valueOf(data[0][5])));
					    bean.setPosName(checkNull(String.valueOf(data[0][6])));
					    bean.setPosCode(checkNull(String.valueOf(data[0][7])));
					    bean.setDateFilter(checkNull(String.valueOf(data[0][8])));
					    bean.setFrmDate(checkNull(String.valueOf(data[0][9])));
					    bean.setToDate(checkNull(String.valueOf(data[0][10])));
					    if(!(String.valueOf(data[0][10]).equals("") || String.valueOf(data[0][10]).equals("null"))){
					    	bean.setDateFlag("true");
					    }else{
					    	bean.setDateFlag("false");
					    }
					    if(String.valueOf(data[0][11]).equals("V")){
					    	bean.setHidReportView("checked");
					    	bean.setViewOnScrn("true");
					    }else{
					    	bean.setHidReportView("");
					    	bean.setViewOnScrn("false");
					    }
					    if(String.valueOf(data[0][11]).equals("R")){
					    	bean.setHidReportRadio("checked");
					    	bean.setReportFlag("true");
					    }else{
					    	bean.setHidReportRadio("");
					    	bean.setReportFlag("false");
					    }
					    	
					    bean.setReportType(checkNull(String.valueOf(data[0][12])));
					    bean.setSettingName(checkNull(String.valueOf(data[0][13])));
			    
		  }
		 
	/**
	 * following query is used to select the sorting option details.
	 */	  
		String sortQuery="SELECT SORT_BY,SORT_BY_ORDER,SORT_THENBY,SORT_THENBY_ORDER,SORT_THENBY1 ,SORT_THENBY_OREDR1"
						+" FROM HRMS_REC_MPR_SORT WHERE MPR_FIL_CODE="+bean.getMraRepCode();
				Object sortData[][]= getSqlModel().getSingleResult(sortQuery);
			if(sortData!=null && sortData.length>0){
				bean.setRadioFlag("true");
						bean.setFirstSort(checkNull(String.valueOf(sortData[0][0])));
					   if(String.valueOf(sortData[0][1]).equals("A")){
						
						  bean.setRadio1("true");
						  bean.setHidFirstAsc("A");
						  bean.setRadioFlag1("false");
						}else{
						
						  bean.setRadioFlag1("true");
						  bean.setHidFirstDesc("D");
						
						  bean.setRadio1("false");
						}
						bean.setSecondSort(checkNull(String.valueOf(sortData[0][2])));
						if(String.valueOf(sortData[0][3]).equals("A")){
							  
							  bean.setRadio2("true");
							  bean.setHidSecAsc("A");
							 bean.setRadioFlag2("false");
							}else{
							
							  bean.setRadioFlag2("true");
							  bean.setHidSecDesc("D");
							  bean.setRadio2("false");
							}
						bean.setThirdSort(checkNull(String.valueOf(sortData[0][4])));
						if(String.valueOf(sortData[0][5]).equals("A")){
							 
							  bean.setRadio3("true");
							  bean.setHidThirdAsc("A");
							  bean.setRadioFlag3("false");
							
						}else{
							
							  bean.setRadioFlag3("true");
							  bean.setHidThirdDesc("D");
							  bean.setRadio3("false");
							
						}
					
			}
			/**
			 * following query is used to select the advanced filter option details
			 */
			String advQuery="SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,ADV_HIRING_MGR,ADV_REQS_STAT ,ADV_VAC_OPT  ,ADV_VAC_VAL"
							+" FROM HRMS_REC_MPR_ADV LEFT JOIN HRMS_EMP_OFFC ON EMP_ID=ADV_HIRING_MGR"	
							+" WHERE MPR_FIL_CODE="+bean.getMraRepCode();
						
			Object advData[][]= getSqlModel().getSingleResult(advQuery);
							if(advData!=null && advData.length>0){
								bean.setHiringMgr(checkNull(String.valueOf(advData[0][0])));
								bean.setHiringMgrId(checkNull(String.valueOf(advData[0][1])));
								bean.setReqsStatus(checkNull(String.valueOf(advData[0][2])));
								bean.setAdvVacOpt(checkNull(String.valueOf(advData[0][3])));
								bean.setAdvVacVal(checkNull(String.valueOf(advData[0][4])));
					    	}
		/**
		 * following query is used to select the requisition details.
		 */
			String reqsnQuery="SELECT REQS_NAME,HRMS_REC_MPR_REQS.REQS_CODE FROM HRMS_REC_MPR_REQS "
							 +" LEFT JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_MPR_REQS.REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
							 +" WHERE MPR_FIL_CODE="+bean.getMraRepCode();
			Object reqsnData[][]= getSqlModel().getSingleResult(reqsnQuery);
					String str="";
					String str1="";
						if(reqsnData!=null && reqsnData.length>0){
							bean.setEditFlag("true");
								for(int i=0;i<reqsnData.length;i++){
									if (i == reqsnData.length - 1) {
									    str+=checkNull(String.valueOf(reqsnData[i][0]));
									    str1+=checkNull(String.valueOf(reqsnData[i][1]));
									}else{
										  str+=checkNull(String.valueOf(reqsnData[i][0]))+",";
										  str1+=checkNull(String.valueOf(reqsnData[i][1]))+",";
									}
							 	}
								bean.setSelectedReq(str1);
								bean.setSelectedReqName(str);
					 
					  		}else{
					  			bean.setEditFlag("false");
					  		}
	}catch(Exception e){
		e.printStackTrace();
	}
	
}
	
/*
 * Method to replace the null with a space.
 * @param result
 * @return String
 */
public String checkNull(String result) {
	if (result == null || result.equals("null")) {
		return "";
	}// end of if
	else {
		return result;
	}// end of else
} 
public void callSavedLIst(ReqsnMisReport bean)
{
	 String quer = "SELECT MPR_FIL_CODE,MPR_FIL_USER_NAME FROM HRMS_REC_MPR_FILTERS WHERE MPR_FIL_USER_ID="+bean.getUserEmpId();
	  Object[][] iterator = getSqlModel().getSingleResult(quer);
			HashMap mp = new HashMap();
			for (int i = 0; i < iterator.length; i++) {
			//	logger.info("String.valueOf(iterator[i][0]) ---------> "+String.valueOf(iterator[i][0]));
				mp.put(String.valueOf(iterator[i][0]),String.valueOf(iterator[i][1]));
		
			}
			bean.setHashMap(mp);
}

/**
 * following method is called to check the duplicate 
 * @param bean
 * @return
 */
public Object[][] chkUser(ReqsnMisReport bean){
	String query="SELECT MPR_FIL_USER_NAME,MPR_FIL_CODE FROM HRMS_REC_MPR_FILTERS WHERE UPPER(MPR_FIL_USER_NAME) = '"+bean.getSettingName().toUpperCase()+"'"+" AND MPR_FIL_USER_ID =("+bean.getUserEmpId()+")";
	Object[][] data = getSqlModel().getSingleResult(query);
	return data;
	
}
	
}//End of class
