package org.paradyne.model.recruitment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.paradyne.bean.Recruitment.SchInterviewAnalysis;
import org.paradyne.bean.Recruitment.SchTestAnalysis;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class SchTestAnalysisModel extends ModelBase{ 

	public void getReport(HttpServletRequest request,
			HttpServletResponse response,SchTestAnalysis bean,String[] colnames) { 
					
		/**
		 * getMessage("serial.no"),getMessage("reqs.code"),getMessage("position"),getMessage("reqs.date"),getMessage("hiring.mgr"),getMessage("total.vac"),
				getMessage("rec.name"),getMessage("required.date"),getMessage("vac.assigned"),getMessage("vac.open"),getMessage("vac.close")
		 */
								String s="\n  Schedule Test Analysis  \n\n";
								String reportType="";
								 
								if(bean.getReportType().equals("P")){
									reportType="Pdf";
								}
								if(bean.getReportType().equals("X")){
									reportType="Xls";
								}
								if(bean.getReportType().equals("T")){
									reportType="Txt";
								} 
					org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator(reportType,s ,"");
					if(bean.getReportType().equals("X")){
						 Object [][] title = new Object[2][3];
						 title [0][0] = "";
						 title [0][1] = "";
						 title [0][2] = "Schedule Test Analysis";  
						 
						 title [1][0] = "";
						 title [1][1] = "";
						 title [1][2] = "";  
						 
						 int [] cols = {20,20,30};
						 int [] align = {0,0,1};
						rg.tableBodyNoCellBorder(title,cols,align,1); 
						
					}
				 
					int length; 
				   String [] actualCol= new String [Integer.parseInt(bean.getCheckedCount())]; 
				   int cellwidth[]=new int [Integer.parseInt(bean.getCheckedCount())];  
					int alignment[]=new int [Integer.parseInt(bean.getCheckedCount())];  
					int k=1; 
					actualCol[0]=colnames[0];
					cellwidth[0]=15;
					alignment[0]=1; 
					
					if(bean.getCandiCodeChk().equals("on")){
						actualCol[k]=colnames[1];
						cellwidth[k]=30;
						alignment[k]=0;
						k++;
					} 
					
					
					if(bean.getEmailIdChk().equals("on")){
						actualCol[k]=colnames[2];
						cellwidth[k]=30;
						alignment[k]=0;
						k++;
					} 
					
					if(bean.getContactNumChk().equals("on")){
						actualCol[k]=colnames[3];
						cellwidth[k]=20;
						alignment[k]=0;
						k++;
					}  
					 
					
					if(bean.getRoundTypeChk().equals("on")){
						actualCol[k]=colnames[4]; 
						cellwidth[k]=20;
						alignment[k]=0;
						k++;
					}  
					if(bean.getInterviewDateChk().equals("on")){
						actualCol[k]=colnames[5];
						cellwidth[k]=13;
						alignment[k]=1;
						k++;
					} 
					if(bean.getInterviewTimeChk().equals("on")){
						actualCol[k]=colnames[6]; 
						cellwidth[k]=10;
						alignment[k]=0;
						k++;
					} 
					if(bean.getInterviewVenueChk().equals("on")){
						actualCol[k]=colnames[7]; 
						cellwidth[k]=45;
						alignment[k]=0;
						k++;
					}
					if(bean.getUserIdChk().equals("on")){
						actualCol[k]=colnames[8]; 
						cellwidth[k]=20;
						alignment[k]=0;
						k++;
					} 
					
					if(bean.getPasswordChk().equals("on")){
						actualCol[k]=colnames[9]; 
						cellwidth[k]=20;
						alignment[k]=0;
						k++;
					} 
					
					if(bean.getRecruiterChk().equals("on")){
						actualCol[k]=colnames[10]; 
						cellwidth[k]=20;
						alignment[k]=0;
						k++;
					}
					if(bean.getInterviewRoundTypeChk().equals("on")){
						actualCol[k]=colnames[21];
						cellwidth[k]=20;
						alignment[k]=0;
						k++;
					} 
					
					if(bean.getSchStatusChk().equals("on")){
						actualCol[k]=colnames[11];
						cellwidth[k]=15;
						alignment[k]=0;
						k++;
					} 
				  
					
					
					rg.addFormatedText(s, 6, 0, 1, 0);
					if(bean.getReportType().equalsIgnoreCase("Xls"))
					{
						rg.addText("", 0, 6, 0);
						rg.addText(s, 0, 6, 0);
						rg.addText("", 0, 6, 0);
						rg.addText("", 0, 6, 0);
					}  
					 
						 // for page wise
					int fromIndex=0;
					int toIndex=0;
				    Object [][] reqData = getSqlModel().getSingleResult(callReqQuery(bean));  
					String[] pageIndex = Utility.doPaging(bean.getMyPage(),reqData.length, 1);	
					if(pageIndex==null){
						pageIndex[0] = "0";
						pageIndex[1] ="1";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					 }   
						if(!bean.getExportAll().equals("on")){ 
							fromIndex=Integer.parseInt(pageIndex[0]);
							toIndex=Integer.parseInt(pageIndex[1]);
						}else{
							fromIndex=0;
						    toIndex=reqData.length;
						}
							 for (int p=fromIndex;p<toIndex;p++) {   
								  
								 
									Object [][] pageReqObj = new Object [1][4];  
								 int pageReqCellWidth[]={25,30,25,30};
								 int pageReqAlign[]={0,0,0,0};
								 for (int i = 0; i < colnames.length; i++) {
									System.out.println("colnames=====  "+i+"  ========="+colnames[i]);
								}
								 pageReqObj [0][0] = colnames[12]+" : ";
								 pageReqObj [0][1] = reqData[p][0];
								 pageReqObj [0][2] = colnames[17]+" : ";
								 pageReqObj [0][3] = reqData[p][5]; 
								 /* 
								 pageReqObj [1][0] = colnames[13]+" : ";
								 pageReqObj [1][1] = reqData[p][2];
								 pageReqObj [1][2] = colnames[14]+" : ";
								 pageReqObj [1][3] = reqData[p][3];
								 
								 pageReqObj [2][0] = colnames[15]+" : ";
								 pageReqObj [2][1] = reqData[p][4];
								 pageReqObj [2][2] = colnames[16]+" : ";
								 pageReqObj [2][3] = reqData[p][5];
								 
								 pageReqObj [3][0] = colnames[17]+" : ";
								 pageReqObj [3][1] = reqData[p][6];
								 pageReqObj [3][2] = colnames[18]+" : ";
								 pageReqObj [3][3] = reqData[p][7];
								 
								 pageReqObj [4][0] = colnames[19]+" : ";
								 pageReqObj [4][1] = reqData[p][8];
								 pageReqObj [4][2] = "";
								 pageReqObj [4][3] = "";  */
								 if(bean.getReportType().equals("P")){
										rg.addText("", 0, 0, 0);
									}
								 if(bean.getReportType().equals("T")){
									 rg.addFormatedText("Requisition Details", 2, 0, 0, 0);  
									}else{
										 rg.addTextBold("Requisition Details", 0, 0, 0, 0);  
									}
								
								
								if(bean.getReportType().equals("X")){
									 rg.tableBody(pageReqObj, pageReqCellWidth, pageReqAlign);   
								}
								if(bean.getReportType().equals("T")){
									 rg.addFormatedText(colnames[12]+" : "+reqData[p][0]+"         "+colnames[17]+" : "+reqData[p][5], 0, 0, 0,0); 
							  //  rg.tableBodyNoBorder(pageReqObj, pageReqCellWidth, pageReqAlign);   
								}
								if(bean.getReportType().equals("P")){
									 rg.addText(colnames[12]+" : "+reqData[p][0]+"         "+colnames[17]+" : "+reqData[p][5], 0, 0, 0,0); 
							  //  rg.tableBodyNoBorder(pageReqObj, pageReqCellWidth, pageReqAlign);   
								}
								 if(bean.getReportType().equals("X")){
										rg.addText("\n",  0, 6, 0);  
									 }
								 bean.setVReqCode(""+reqData[p][9]); 
								 
								 Object tab[][]=callCandidateQuery(bean, request);
									length=tab.length;
									Object totalObj[][]=new Object[length][Integer.parseInt(bean.getCheckedCount())];
									
									if(tab!=null && tab.length==0){
					
										 if(bean.getReportType().equals("T")){
											 rg.addFormatedText("No schedule record for display", 0, 0, 0, 0);  
											}else{
												rg.addText("No schedule record for display ",  0, 6, 0);  
											} 
										 
										 if(bean.getReportType().equals("P")){
											 Object [][]blankData = new Object[1][1];
											  int [] blankWidth ={230};
											  int [] blankAlignment ={0};
											  blankData [0][0]="-----------------------------------------------------------------------------------------------------------" +
										 		"-------------------------------------------------------------------------------------------------------------------------------------------------------------" +
										 		"------------------------------------------------------------------------------------------------------------------------------------------" ;
											   rg.tableBodyNoBorder(blankData, blankWidth, blankAlignment);     
											 }
											 
											 if(bean.getReportType().equals("T")){
												 Object [][]blankData = new Object[1][1];
												  int [] blankWidth ={230};
												  int [] blankAlignment ={0};
												  blankData [0][0]="-----------------------------------------------------------------------------------------------------------" +
											 		"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------" ;
											 	     rg.tableBodyNoBorder(blankData, blankWidth, blankAlignment);     
												 }
											 if(bean.getReportType().equals("X")){
													rg.addText("\n",  0, 6, 0);  
												 } 
									} else{ 
										int m=1;
										int cnt=1;
									for (int i = 0; i < tab.length; i++) {
										 
										totalObj[i][0]=cnt;
										cnt++;
									 
										if(bean.getCandiCodeChk().equals("on")){
											if(tab[i][0] !=null){
												totalObj[i][m]=checkNull(""+tab[i][0]);
											}
											else{
												totalObj[i][m]="";
											}
											m++; 
										}   
										
										
										if(bean.getEmailIdChk().equals("on")){
											if(tab[i][1] !=null){
												totalObj[i][m]=checkNull(""+tab[i][1]);
											}
											else{
												totalObj[i][m]="";
											}
											m++;
											System.out.println("bean.getReqCodeChk()=value======== "+tab[i][0]);
										}   
										
										if(bean.getContactNumChk().equals("on")){
											if(tab[i][2] !=null){
												totalObj[i][m]=checkNull(""+tab[i][2]);
											}
											else{
												totalObj[i][m]="";
											}
											m++;
											System.out.println("bean.getReqCodeChk()=value======== "+tab[i][0]);
										}   
										
										if(bean.getRoundTypeChk().equals("on")){
											if(tab[i][3] !=null)	{					
												totalObj[i][m]=checkNull(""+tab[i][3]);
											}else
											{
												totalObj[i][m]="";
											}
											m++; 
										}  
										if(bean.getInterviewDateChk ().equals("on")){
											if(tab[i][4] !=null){
												totalObj[i][m]=checkNull(""+tab[i][4]);
											}else{
												totalObj[i][m]="";
											}
											m++;
										}   
										
										if(bean. getInterviewTimeChk().equals("on")){
											if(tab[i][5] !=null){
												totalObj[i][m]=checkNull(""+tab[i][5]); 
											}else {
												totalObj[i][m]="";
											}
											m++;
										}
										
										if(bean.getInterviewVenueChk().equals("on")){
											if(tab[i][6] !=null){
												totalObj[i][m]=checkNull(""+tab[i][6]);
											}else{
												totalObj[i][m]="";
											}
											m++;
										}
										if(bean.getUserIdChk().equals("on")){
											if(tab[i][7] !=null){
												totalObj[i][m]=checkNull(""+tab[i][7]);
											}else{
												totalObj[i][m]="";
											}
											m++;
										} 
										
										if(bean.getPasswordChk().equals("on")){
											if(tab[i][8] !=null){
												totalObj[i][m]=checkNull(""+tab[i][8]);
											}else{
												totalObj[i][m]="";
											}
											m++;
										} 
										
										if(bean.getRecruiterChk().equals("on")){
											if(tab[i][9] !=null)
											{
												totalObj[i][m]=checkNull(""+tab[i][9]);
											}
											else{
												totalObj[i][m]="";
											}
											m++;
										}
										
										if(bean.getInterviewRoundTypeChk().equals("on")){
											if(tab[i][10] !=null){
												totalObj[i][m]=checkNull(""+tab[i][10]);
											}else {
												totalObj[i][m]="";
											}
											m++;
										} 
										
										if(bean.getSchStatusChk().equals("on")){
											if(tab[i][10] !=null){
												totalObj[i][m]=checkNull(""+tab[i][11]);
											}else {
												totalObj[i][m]="";
											}
											m++;
										} 
										 
										m=1;
									}  // end of inner for loop
									 System.out.println("in else--------- "+totalObj.length);
										if(bean.getReportType().equals("P")){
											rg.addText("", 0, 0, 0);
										}
										if(bean.getReportType().equals("T")){
											 rg.addFormatedText("Schedule Details", 2, 0, 0, 0);  
											}else{
												rg.addTextBold("Schedule Details", 0, 0, 0); 
											} 
								 
								rg.tableBody(actualCol,totalObj, cellwidth, alignment);  
								
								 if(bean.getReportType().equals("P")){
									 Object [][]blankData = new Object[1][1];
									  int [] blankWidth ={230};
									  int [] blankAlignment ={0};
									  blankData [0][0]="-----------------------------------------------------------------------------------------------------------" +
								 		"-------------------------------------------------------------------------------------------------------------------------------------------------------------" +
								 		"------------------------------------------------------------------------------------------------------------------------------------------" ;
									   rg.tableBodyNoBorder(blankData, blankWidth, blankAlignment);     
									 }
									 
									 if(bean.getReportType().equals("T")){
										 Object [][]blankData = new Object[1][1];
										  int [] blankWidth ={230};
										  int [] blankAlignment ={0};
										  blankData [0][0]="-----------------------------------------------------------------------------------------------------------" +
									 		"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------" ;
									 	     rg.tableBodyNoBorder(blankData, blankWidth, blankAlignment);     
										 }
									 if(bean.getReportType().equals("X")){
											rg.addText("\n",  0, 6, 0);  
										 }
							  }   // end of else  
					    } // end of outer for loop
					 
				 
			 rg.createReport(response);
	}
	
	// for display the list of requistion on the pop up
	public void displayReq(SchTestAnalysis bean)
	{ 
		
		String sql=" SELECT NVL(REQS_NAME,' '),RANK_NAME,TO_CHAR(REQS_DATE ,'DD-MM-YYYY'),DECODE(REQS_APPROVAL_STATUS,'P','Pending', "
				+" 'R','Rejected','A' ,'Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),REQS_CODE "
				+" FROM HRMS_REC_REQS_HDR  INNER JOIN  HRMS_RANK ON (RANK_ID =REQS_POSITION) "
				+" INNER JOIN HRMS_EMP_OFFC  ON(EMP_ID=HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)"
				+" WHERE REQS_APPROVAL_STATUS IN ('A','Q') ";
		 
		 
		 if(!bean.getPositionId().equals(""))
		 {
			  sql +=" AND REQS_POSITION ="+bean.getPositionId();
		 } 
		 if(!bean.getHiringMgrCode().equals(""))
		  {
			 sql += " AND REQS_HIRING_MANAGER="+bean.getHiringMgrCode();
		  }  
		 
		 if(!bean.getDateFilter().equals("1"))
		 {
			   if(bean.getDateFilter().equals("O"))
			   {
				   sql +=" AND REQS_DATE = TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
			   }
			   
			   if(bean.getDateFilter().equals("OB"))
			   {
				   sql +=" AND REQS_DATE <= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
			   }
			   
			   if(bean.getDateFilter().equals("OA"))
			   {
				   sql +=" AND REQS_DATE >= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
			   }
			   
			   if(bean.getDateFilter().equals("B"))
			   {
				   sql +=" AND REQS_DATE < TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
			   }
			   
			   if(bean.getDateFilter().equals("A"))
			   {
				   sql +=" AND REQS_DATE > TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
			   }
			   
			   if(bean.getDateFilter().equals("F"))
			   {
				   sql +=" AND REQS_DATE >= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY') ";
				   if (!(bean.getToDate().equals("") || bean.getToDate().equals("dd-mm-yyyy"))){
					   sql +=" AND REQS_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ; 
				   }
			   }
		 } 
		  sql +=" ORDER BY REQS_DATE DESC ";
		  Object [][] data = getSqlModel().getSingleResult(sql);
		  if(data!=null && data.length >0)
		  { 
			  bean.setDataLength(""+data.length);
			 ArrayList list = new ArrayList();  
			 for (int i = 0; i < data.length; i++) {
				 SchTestAnalysis bean1 = new SchTestAnalysis();
				 
				 bean1.setItReqName(callStringBreak(""+data[i][0],21));
				 bean1.setItPosition(""+data[i][1]);
				 bean1.setItReqDate(""+data[i][2]);
				 bean1.setItStatus(""+data[i][3]);
				 bean1.setItReqCode(""+data[i][4]);
				// bean1.setItRecriuterName(""+data[i][5]); 
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
				 list.add(bean1);
			 }
			 bean.setDispList(list);
		  }else{
			  
			  bean.setNoDataReq("true");
		  }
	} 
	
	public void callViewScreen(SchTestAnalysis bean,HttpServletRequest request)
	{ 
			 
			
		if(bean.getCandiCodeChk().equals("on")){
			bean.setCandiCodeChkFlag("true");
		}
		
		if(bean.getEmailIdChk().equals("on")){
			bean.setEmailIdChkFlag("true");
		}
		
		if(bean.getContactNumChk().equals("on")){
			bean.setContactNumChkFlag("true");
		}
		 
		if(bean.getInterviewTimeChk().equals("on")){
			bean.setInterviewTimeChkFlag("true");  
		} 
		
		if(bean.getRecruiterChk().equals("on")){
			bean.setRecruiterChkFlag("true");
		}
		 
		if(bean.getRoundTypeChk().equals("on")){
			bean.setRoundTypeChkFlag("true");
		}
		if(bean.getInterviewVenueChk().equals("on")){
			bean.setInterviewVenueChkFlag("true");
		}
		
		if(bean.getInterviewRoundTypeChk().equals("on")){
			bean.setInterviewRoundTypeChkFlag("true");
		}
		
		if(bean.getSchStatusChk().equals("on")){
			bean.setSchStatusChkFlag("true");
		}
		if(bean.getInterviewDateChk().equals("on")){
			bean.setInterviewDateChkFlag("true");
		} 
		if(bean.getUserIdChk().equals("on")){
			bean.setUserIdChkFlag("true");
		}  
		
		if(bean.getPasswordChk().equals("on")){
			bean.setPasswordChkFlag("true");
		}  
		
	    Object [][] data = getSqlModel().getSingleResult(callReqQuery(bean)); 
		  if(data != null && data.length >0)
		  { 
			  bean.setDataLength(String.valueOf(data.length));
				String[] pageIndex = Utility.doPaging(bean.getMyPage(),data.length, 1);	
				if(pageIndex==null){
					pageIndex[0] = "0";
					pageIndex[1] ="1";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				
				request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
				if(pageIndex[4].equals("1"))
					bean.setMyPage("1"); 
			   
			   for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {
				   bean.setVReqName(String.valueOf(data[i][0]));
				   bean.setVReqDate(String.valueOf(data[i][1]));
				   bean.setVBranch(String.valueOf(data[i][2]));
				   bean.setVDepartment(String.valueOf(data[i][3]));
				   bean.setVDivision(String.valueOf(data[i][4]));
				   bean.setVPosition(String.valueOf(data[i][5]));
				   bean.setVHiringMgr(String.valueOf(data[i][6]));
				   bean.setVAppStatus(String.valueOf(data[i][7]));
				   bean.setVReqStatus(String.valueOf(data[i][8])); 
				   bean.setVReqCode(String.valueOf(data[i][9])); 
				   if (!(String.valueOf(data[i][9]).equals("") ||String.valueOf(data[i][9]).equals("null") || String.valueOf(data[i][9])==null)){
					   if(bean.getCandidateFlag().equals("false")){
					   callCandidateQuery(bean,request);
					   }
				   }
			   } 
			    
		  }else{
			request.setAttribute("totalPage", new Integer(0));
			request.setAttribute("PageNo", new Integer(0));
			request.setAttribute("totalPageCandi", new Integer(0));
			request.setAttribute("PageNoCandi", new Integer(0));
				bean.setNoData("true");
		  }
	}
	
	public String callReqQuery(SchTestAnalysis bean)
	{
String query=" SELECT REQS_NAME, NVL(TO_CHAR(REQS_DATE, 'DD-MM-YYYY'),' ') AS REQ_DATE ,CENTER_NAME AS BRANCH," 
		+" DEPT_NAME,DIV_NAME, NVL(RANK_NAME,' ') AS POSITION,HIRING_MGR.EMP_FNAME||' '||HIRING_MGR.EMP_MNAME||' '||HIRING_MGR.EMP_LNAME HIRING_MGR , " 
		+" DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A','Approved','H','On Hold','B','New Requisition','Q','Quick Requisition') AS APPR_STATUS," 
		+" DECODE(REQS_STATUS,'O','Open','C','Close','C','Cancel') AS REQ_STATUS , REQS_CODE "
		+" FROM HRMS_REC_REQS_HDR  "
		+" LEFT JOIN HRMS_RANK ON (REQS_POSITION = RANK_ID) " 
		+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION) " 
		+" INNER JOIN HRMS_EMP_OFFC HIRING_MGR ON (REQS_HIRING_MANAGER = HIRING_MGR.EMP_ID) "
		+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH) " 
		+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)  "
		+" WHERE REQS_APPROVAL_STATUS IN ('A','Q') ";
		// ============  started  filter ===========
			if(!bean.getReqCode().equals("")){						
				query+= " AND HRMS_REC_REQS_HDR.REQS_CODE="+ bean.getReqCode();
			}  // end of if
			 
			 if(!bean.getHiringMgrCode().equals(""))
			 {
				 query +=" AND HIRING_MGR.EMP_ID ="+bean.getHiringMgrCode();
			 }  
			 
			 if(!bean.getPositionId().equals(""))
			 {
				 query +=" AND REQS_POSITION ="+bean.getPositionId();
			 } 
			 
			 if(!bean.getDateFilter().equals("1"))
			 {
				   if(bean.getDateFilter().equals("O"))
				   {
					   query +=" AND REQS_DATE = TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
				   }
				   
				   if(bean.getDateFilter().equals("OB"))
				   {
					   query +=" AND REQS_DATE <= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
				   }
				   
				   if(bean.getDateFilter().equals("OA"))
				   {
					   query +=" AND REQS_DATE >= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
				   }
				   
				   if(bean.getDateFilter().equals("B"))
				   {
					   query +=" AND REQS_DATE < TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
				   }
				   
				   if(bean.getDateFilter().equals("A"))
				   {
					   query +=" AND REQS_DATE > TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
				   }
				   
				   if(bean.getDateFilter().equals("F"))
				   {
					   query +=" AND REQS_DATE >= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')";
					   if(!(bean.getToDate().equals("")|| bean.getToDate().equals("dd-mm-yyyy")))
					      {
						   query +=" AND REQS_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ; 
					   		}
					 }
			 }    
			// ============  end of filter ===========  
			 
				// ============ for selected requistion===========  
			  
			  if(!bean.getSelectedReq().equals("") && !bean.getSelectedReq().equals("null"))
			  {
				  query+= " AND HRMS_REC_REQS_HDR.REQS_CODE IN ("+bean.getSelectedReq()+")";
			  }
			 // FOR CANDIDATE FILTER ONLY 
			  String candiReqCode=callCandidateReq(bean);
			  if(!candiReqCode.equals(""))
				{
				 query+= " AND HRMS_REC_REQS_HDR.REQS_CODE IN ("+candiReqCode+")";
				} 
			  
			// ============  start of sorting ===========
			 if(bean.getSortBy().equals("R") || bean.getSortBy().equals("P") ||  bean.getThenBy1().equals("R") ||  bean.getThenBy1().equals("P") ||  bean.getThenBy2().equals("R")  ||  bean.getThenBy2().equals("P"))
			  {
			      query+=" ORDER BY ";
			  }
			  if(bean.getSortBy().equals("R") || bean.getSortBy().equals("P") )
			  {
				  query+= getSortVal(bean.getSortBy()) +" "+getSortOrder(bean.getSortByOrder());
				  if(bean.getThenBy1().equals("R")|| bean.getThenBy1().equals("P") || bean.getThenBy2().equals("R") || bean.getThenBy2().equals("P")){ 
					  query+=" , ";
				  }
			  }
			  
			  if(bean.getThenBy1().equals("R") || bean.getThenBy1().equals("P"))
			  {
				  query+= getSortVal(bean.getThenBy1()) +" "+getSortOrder(bean.getThenByOrder1()); 
				  if(bean.getThenBy2().equals("R") || bean.getThenBy2().equals("P")) {
					  query+=" , ";
				  }
			  }
			  
			  if(bean.getThenBy2().equals("R") || bean.getThenBy2().equals("P"))
			  {
				  query+= getSortVal(bean.getThenBy2()) +" "+getSortOrder(bean.getThenByOrder2());
			  }
			// ============  end of sorting ===========   
			
			return query;
	}
	
	
	
	public Object [][] callCandidateQuery(SchTestAnalysis bean,HttpServletRequest request)
	{
		 if(bean.getScreenFlag().equals("false")){
			 callViewScreen(bean,request);  
		 } 
		
String query=" SELECT CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME AS CANDI_NAME, NVL(CAND_EMAIL_ID,' ') AS EMAIL_ID, "
			+" NVL(CAND_MOB_PHONE,'') AS CONCATNUMBER,NVL(DECODE(TEST_TYPE,'O','Online','W','Written'),'') AS TEST_TYPE," 
			+" NVL(TO_CHAR(HRMS_REC_SCHTEST_DTL.TEST_DATE,'DD-MM-YYYY'),' ') AS TEST_DATE,  NVL(TEST_TIME,' ') AS TEST_TIME, " 
			+" NVL(TEST_VENUE_DET,' ') AS VENUE , TEST_USERID, TEST_PWD,  RECTR.EMP_FNAME||' '||RECTR.EMP_MNAME||' '||RECTR.EMP_LNAME AS RECRUITER,NVL(TEST_ROUND_TYPE,' '), " 
			+" DECODE(TEST_STATUS ,'Y','Conducted','X','Conducted','N',' Scheduled','R','Rescheduled','C','Canceled') AS TEST_STATUS  "
			+"  FROM HRMS_REC_SCHTEST_DTL  "
			+" INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE) "  
			+" INNER JOIN HRMS_REC_SCHTEST ON(HRMS_REC_SCHTEST.TEST_CODE=HRMS_REC_SCHTEST_DTL.TEST_CODE) "
			+" LEFT JOIN HRMS_EMP_OFFC RECTR ON(RECTR.EMP_ID=HRMS_REC_SCHTEST.TEST_REC_EMPID) " 
			+" INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE) "
			+" WHERE HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE ="+bean.getVReqCode();

		// ============  started Advance filter ===========
        
				if(!bean.getSchStatusCom().equals("1"))
				{
					query+= " AND TEST_STATUS ='"+bean.getSchStatusCom()+"'";
				}
				
				if(!bean.getCandidateCode().equals(""))
				{
					query+= " AND HRMS_REC_CAND_DATABANK.CAND_CODE="+bean.getCandidateCode();
				} 
				 
	   // ============  start of sorting ===========
				
				 if (bean.getSortBy().equals("C")||bean.getSortBy().equals("D")||bean.getSortBy().equals("N")||bean.getSortBy().equals("T")
				   || bean.getThenBy1().equals("C")||bean.getThenBy1().equals("D")||bean.getThenBy1().equals("N")||bean.getThenBy1().equals("T") 
				    || bean.getThenBy2().equals("C")||bean.getThenBy2().equals("D")||bean.getThenBy2().equals("N")||bean.getThenBy2().equals("T")
				 )
			 	  {
				      query+=" ORDER BY ";
				  } 
			 
				 if(!(bean.getSortBy().equals("1") || bean.getSortBy().equals("R") || bean.getSortBy().equals("P")))
			     {
				  query+= getSortVal(bean.getSortBy()) +" "+getSortOrder(bean.getSortByOrder());
				  if (bean.getThenBy1().equals("C")||bean.getThenBy1().equals("D")||bean.getThenBy1().equals("N") ||bean.getThenBy1().equals("T")
						    || bean.getThenBy2().equals("C")||bean.getThenBy2().equals("D")||bean.getThenBy2().equals("N") ||bean.getThenBy2().equals("T") )
				   {
					  query+=" , ";
				  }
			  }
			  
				 if(!(bean.getThenBy1().equals("1")|| bean.getThenBy1().equals("R") ||bean.getThenBy1().equals("P")))
			  {
				  query+= getSortVal(bean.getThenBy1()) +" "+getSortOrder(bean.getThenByOrder1()); 
				  if(bean.getThenBy2().equals("C")||bean.getThenBy2().equals("D")||bean.getThenBy2().equals("N")||bean.getThenBy2().equals("T") )
				  {
					  query+=" , ";
				  }
			  }
			  
			  if(!(bean.getThenBy2().equals("1")  || bean.getThenBy2().equals("R")  ||bean.getThenBy2().equals("P")||bean.getThenBy2().equals("T")))
			  {
				  query+= getSortVal(bean.getThenBy2()) +" "+getSortOrder(bean.getThenByOrder2());
			  }
			// ============  end of sorting ===========   
			
			 
			
			 Object [][] data = getSqlModel().getSingleResult(query); 
			  if(data != null && data.length >0)
			  {   bean.setNoDataCandi("false");
				  bean.setDataLength(String.valueOf(data.length));
					String[] pageIndex = Utility.doPaging(bean.getMyPageCandi(),data.length,20);	
					if(pageIndex==null){
						pageIndex[0] = "0";
						pageIndex[1] ="20";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}
					
					request.setAttribute("totalPageCandi", Integer.parseInt(String.valueOf(pageIndex[2])));
					request.setAttribute("PageNoCandi", Integer.parseInt(String.valueOf(pageIndex[3])));
					if(pageIndex[4].equals("1"))
						bean.setMyPageCandi("1");
					
				   ArrayList candidateList = new ArrayList();  
				   for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
					   SchTestAnalysis bean1= new SchTestAnalysis();
					   bean1.setIvCandidateName(String.valueOf(data[i][0]));
					   bean1.setIvEmailId(checkNull(String.valueOf(data[i][1])));
					   bean1.setIvContactNum(checkNull(String.valueOf(data[i][2])));
					   bean1.setIvRoundType(checkNull(String.valueOf(data[i][3]))); 
					   bean1.setIvInterviewDate(checkNull(String.valueOf(data[i][4]))); 
					   bean1.setIvInterviewTime(checkNull(String.valueOf(data[i][5])));
					   bean1.setIvVenue(checkNull(String.valueOf(data[i][6]))); 
					   bean1.setIvUserId(checkNull(String.valueOf(data[i][7]))); 
					   String decryptPwd="";
					   try
					   {
						   
						   decryptPwd = new StringEncrypter(
									StringEncrypter.DESEDE_ENCRYPTION_SCHEME)
									.decrypt(String.valueOf(data[i][8]));  
					   }catch (Exception e) { 
						System.out.println("error in decrypt.......... ");
					}
					   bean1.setIvPassword(checkNull(decryptPwd));  
					   bean1.setIvRecruiter(checkNull(String.valueOf(data[i][9])));
					   bean1.setIvInterviewRoundType(checkNull(String.valueOf(data[i][10]))); 
					   bean1.setIvSchStatus(checkNull(String.valueOf(data[i][11]))); 
					   
					   
						if(bean.getCandiCodeChk().equals("on")){
							bean1.setCandiCodeChkFlag("true");
						}
						
						if(bean.getEmailIdChk().equals("on")){
							bean1.setEmailIdChkFlag("true");
						}
						
						if(bean.getContactNumChk().equals("on")){
							bean1.setContactNumChkFlag("true");
						}
						 
						if(bean.getInterviewTimeChk().equals("on")){
							bean1.setInterviewTimeChkFlag("true");  
						} 
						
						if(bean.getRecruiterChk().equals("on")){
							bean1.setRecruiterChkFlag("true");
						}
						 
						if(bean.getRoundTypeChk().equals("on")){
							bean1.setRoundTypeChkFlag("true");
						}
						if(bean.getInterviewVenueChk().equals("on")){
							bean1.setInterviewVenueChkFlag("true");
						}
						
						if(bean.getInterviewRoundTypeChk().equals("on")){
							bean1.setInterviewRoundTypeChkFlag("true");
						}
						
						if(bean.getSchStatusChk().equals("on")){
							bean1.setSchStatusChkFlag("true");
						}
						if(bean.getInterviewDateChk().equals("on")){
							bean1.setInterviewDateChkFlag("true");
						}  
						if(bean.getUserIdChk().equals("on")){
							bean1.setUserIdChkFlag("true");
						}  
						
						if(bean.getPasswordChk().equals("on")){
							bean1.setPasswordChkFlag("true");
						}  
						
						
						candidateList.add(bean1);
				  } 
				   bean.setCandidateList(candidateList);
			  }else{
				  request.setAttribute("totalPageCandi", new Integer(0));
					request.setAttribute("PageNoCandi", new Integer(0));
					bean.setNoDataCandi("true");
			  }
			  return data;
	} 
	
	
	public String getSortVal(String Status)
	{  String sql="";
		if(Status.equals("R")){
			sql=" UPPER(REQS_NAME) ";
		} 
		if(Status.equals("P")){
			sql="UPPER(RANK_NAME)  ";
		} 
		if(Status.equals("D"))
		{
			sql=" REQS_DATE";
		}
		 
		if(Status.equals("C")){
			sql="UPPER(CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME) ";
		}  
		 
		if(Status.equals("D")){
			sql=" HRMS_REC_SCHTEST_DTL.TEST_DATE ";
		} 
		 
		if(Status.equals("N")){
			sql=" UPPER(RECTR.EMP_FNAME||' '||RECTR.EMP_MNAME||' '||RECTR.EMP_LNAME) ";
		} 
		
		if(Status.equals("T")){
			sql=" UPPER(TEST_TYPE) ";
		} 
		return sql ;
	}
	
	public String getSortOrder(String Status)
	{ 
	   String sql=""; 
	   if(Status.equals("A")) {		   
		   sql="Asc";
	   } 
	   if(Status.equals("D")) {		   
		   sql="Desc";
	   }
	   return sql ;
	}
 
	 
	
	public void callBack(SchTestAnalysis bean)
	{  
		
		if(bean.getCandiCodeChk().equals("on")){
			bean.setCandiCodeChk("checked"); 
		} 
		if(bean.getEmailIdChk().equals("on")){
			bean.setEmailIdChk("checked"); 
		} 
		if(bean.getContactNumChk().equals("on")){
			bean.setContactNumChk("checked"); 
		} 
		if(bean.getInterviewTimeChk().equals("on")){
			bean.setInterviewTimeChk("checked"); 
		}  
		if(bean.getRecruiterChk().equals("on")){
			bean.setRecruiterChk("checked"); 
		} 
		if(bean.getRoundTypeChk().equals("on")){
			bean.setRoundTypeChk("checked"); 
		}
		if(bean.getInterviewVenueChk().equals("on")){
			bean.setInterviewVenueChk("checked");  
		} 
		if(bean.getInterviewDateChk().equals("on")){
			bean.setInterviewDateChk("checked");  
		}  
		
		if(bean.getUserIdChk().equals("on")){
			bean.setUserIdChk("checked");
		}  
		
		if(bean.getPasswordChk().equals("on")){
			bean.setPasswordChk("checked");
		}   
		
		if(bean.getInterviewRoundTypeChk().equals("on")){
			bean.setInterviewRoundTypeChk("checked");
		}  
		if(bean.getSchStatusChk().equals("on")){
			bean.setSchStatusChk("checked");
		} 
		if(bean.getSortByOrder().equals("A")){
			bean.setSortByAsc("checked");
		}else{
			bean.setSortByDsc("checked");
		}  
		if(bean.getThenByOrder1().equals("A")){
			bean.setThenByOrder1Asc("checked");
		}else{
			bean.setThenByOrder1Dsc("checked");
		}  
		if(bean.getThenByOrder2().equals("A")){
			bean.setThenByOrder2Asc("checked");
		}else{
			bean.setThenByOrder2Dsc("checked");
		}  
	}
	
	public void input(SchTestAnalysis bean)
	{ 
		    bean.setCandiCodeChk("checked"); 
		    bean.setEmailIdChk("checked"); 
			bean.setContactNumChk("checked");  
			bean.setInterviewTimeChk("checked"); 
			bean.setRecruiterChk("checked"); 
			bean.setRoundTypeChk("checked"); 
			bean.setInterviewVenueChk("checked"); 
			bean.setInterviewRoundTypeChk("checked"); 
			bean.setSchStatusChk("checked");  
			bean.setInterviewDateChk("checked");  
			bean.setUserIdChk("checked");  
			bean.setPasswordChk("checked");  
			 
	}
	
	public void callPreviousRecord(SchTestAnalysis bean)
	{
	  String sql =" SELECT TESTREP_CODE, TESTREP_NAME FROM HRMS_REC_TESTREP_FILTERS WHERE TESTREP_USEREMPID ="+bean.getUserEmpId()+" ORDER BY UPPER(TESTREP_NAME) ";
	  Object [][] data = getSqlModel().getSingleResult(sql);
		  if(data!=null && data.length>0)
		  {  LinkedHashMap map = new LinkedHashMap();
			  for (int i = 0; i < data.length; i++) {
				  map.put(data[i][0], data[i][1]);
			}
			  bean.setMap(map); 
		  }
		 
	}
	public boolean callSave(SchTestAnalysis bean)
	{
		if(saveFiletr(bean) && saveSort(bean) && saveColumnDef(bean) && saveAdvance(bean))
		{
			return true;
		}else{
		return false;
		}
	}
	
	public boolean saveFiletr(SchTestAnalysis bean)
	{    
		Object [][] filterObj = null;
		if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
			filterObj= new Object [1][12];  
		}else{
			filterObj= new Object [1][13];   
		}
		filterObj [0][0]=bean.getReqCode().equals("")?null : bean.getReqCode();
		filterObj [0][1]= bean.getHiringMgrCode().equals("")?null : bean.getHiringMgrCode();
		filterObj [0][2]=bean.getPositionId().equals("")?null :bean.getPositionId();
		 if(!bean.getDateFilter().equals("1"))
		 {
			   if(bean.getDateFilter().equals("O"))
			   {
				   filterObj [0][3]="O";
			   }
			   
			   if(bean.getDateFilter().equals("B"))
			   {
				   filterObj [0][3]="B";
			   }
			   
			   if(bean.getDateFilter().equals("A"))
			   {
				   filterObj [0][3]="A";
			   }
			   
			   if(bean.getDateFilter().equals("OB"))
			   {
				   filterObj [0][3]="OB";
			   }
			   
			   if(bean.getDateFilter().equals("OA"))
			   {
				   filterObj [0][3]="OA";
			   }
			   
			   if(bean.getDateFilter().equals("F"))
			   {
				   filterObj [0][3]="F";
				 }
		 }  else{
			 filterObj [0][3]="1";
		 }     
		  
		 filterObj [0][4]=bean.getDateFilter().equals("1")? null: bean.getFrmDate().equals("")? null : bean.getFrmDate();
		 filterObj [0][5]=bean.getReqStatus().trim().equals("")?"V":bean.getReqStatus().trim(); 
		 filterObj [0][6]=bean.getSettingName().trim();  
		 filterObj [0][7]=(!bean.getDateFilter().equals("F"))? null:bean.getToDate().equals("")? null : bean.getToDate(); 
	     filterObj [0][8]=bean.getUserEmpId().trim(); 
	     filterObj [0][9]=bean.getSelectedReq().trim();
	     filterObj [0][10]=bean.getReportType().equals("1")?"1":bean.getReportType().trim();  
	     filterObj [0][11]=bean.getRadioStatus().trim();
	   boolean flag= false;
	   if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
		   flag = getSqlModel().singleExecute(getQuery(1),filterObj);
	   }else{
		   filterObj[0][12]=bean.getSearchSetting();
		   System.out.println("in update ============"); 
		   
		   flag =  getSqlModel().singleExecute(getQuery(5),filterObj);
	   }
		System.out.println("flag======filetr========= "+flag); 
		return flag;
	}
	
	public boolean saveSort(SchTestAnalysis bean)
	{ 
		Object [][] sortObj =null;
		   if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
			   sortObj = new Object[1][6];
		   }else{
			   sortObj = new Object[1][7];
		   }
		    
		sortObj[0][0]=bean.getSortBy();
		sortObj[0][1]=bean.getSortByOrder();
		sortObj[0][2]=bean.getThenBy1();
		sortObj[0][3]=bean.getThenByOrder1();
		sortObj[0][4]=bean.getThenBy2();
		sortObj[0][5]=bean.getThenByOrder2();; 
		boolean flag= false;
		 if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
			 flag=getSqlModel().singleExecute(getQuery(2),sortObj); 
		 }else{
			 sortObj[0][6]=bean.getSearchSetting();
			 flag =getSqlModel().singleExecute(getQuery(6),sortObj); 
		 }
		System.out.println("flag======sort========= "+flag);
	    return flag;
	}
	
	public boolean saveColumnDef(SchTestAnalysis bean)
	{   
		Object [][] colObj = null;
		  if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
			  colObj = new Object[1][12];
		  }else{
			  colObj = new Object[1][13];
		  } 
		colObj[0][0]=bean.getCandiCodeChk().equals("on")?"Y":"N";
		colObj[0][1]=bean.getEmailIdChk().equals("on")?"Y":"N";
		colObj[0][2]=bean.getContactNumChk().equals("on")?"Y":"N";
		colObj[0][3]=bean.getRoundTypeChk().equals("on")?"Y":"N";
		colObj[0][4]=bean.getInterviewDateChk().equals("on")?"Y":"N";
		colObj[0][5]=bean.getInterviewTimeChk().equals("on")?"Y":"N";
		colObj[0][6]=bean.getInterviewVenueChk().equals("on")?"Y":"N"; 
		colObj[0][7]=bean.getUserIdChk().equals("on")?"Y":"N"; 
		colObj[0][8]=bean.getPasswordChk().equals("on")?"Y":"N"; 
		colObj[0][9]=bean.getRecruiterChk().equals("on")?"Y":"N"; 
		colObj[0][10]=bean.getSchStatusChk().equals("on")?"Y":"N";  
		System.out.println("bean.getInterviewRoundTypeChk()========== "+bean.getInterviewRoundTypeChk());
		colObj[0][11]=bean.getInterviewRoundTypeChk().equals("on")?"Y":"N";   
		boolean flag= false; 
		 if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
			 flag = getSqlModel().singleExecute(getQuery(3),colObj); 
		 }else{
			 colObj[0][12]=bean.getSearchSetting();
			 flag = getSqlModel().singleExecute(getQuery(7),colObj); 
		 }
		System.out.println("flag======column========= "+flag);
	    return flag;
	}
	
	public boolean saveAdvance(SchTestAnalysis bean)
	{  
		Object [][] colAdv =null;
		 if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
		   colAdv = new Object[1][2];
		 }else{
			 colAdv = new Object[1][3];
		 }
		  
		colAdv[0][0]=bean.getSchStatusCom(); 
		colAdv[0][1]=(bean.getCandidateCode().equals("")||bean.getCandidateCode().equals("null"))?null :bean.getCandidateCode();  
		boolean flag= false;
		 if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
			 flag= getSqlModel().singleExecute(getQuery(4),colAdv); 
		 }else{
			   colAdv[0][2]=bean.getSearchSetting(); 
			   flag = getSqlModel().singleExecute(getQuery(8),colAdv); 
		 }
		System.out.println("flag======advance========= "+flag);
	    return flag;
	}
 
	
	
	public boolean callUpdate(SchTestAnalysis bean)
	{
		if(saveFiletr(bean) && saveSort(bean) && saveColumnDef(bean) && saveAdvance(bean))
		{
			return true;
		}else{
		return false;
		}
	}
	
	/**
	 * set the data of filter
	 * @param bean
	 */
	public void callFilterSavedData(SchTestAnalysis bean)
	{
 String sqlFilter =" SELECT REQS_NAME,TESTREP_REQ_CODE, NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' ') ,  NVL(TESTREP_HIRINGMGR_CODE,0), NVL(RANK_NAME,' '), " 
			 +"	NVL(TESTREP_POSITION_CODE,0), TESTREP_DATEOPTION, TO_CHAR(TESTREP_SCHD_FROMDATE,'DD-MM-YYYY') , "
			 +"	TESTREP_REPOPTION, TESTREP_NAME, TO_CHAR(TESTREP_SCHD_TODATE,'DD-MM-YYYY') , "
			 +"	TESTREP_SELECTED_REQ, TESTREP_REP_TYPE, "
			 +"	TESTREP_RADIO_STATUS FROM HRMS_REC_TESTREP_FILTERS "
			 +" LEFT JOIN HRMS_REC_REQS_HDR ON ( TESTREP_REQ_CODE= REQS_CODE)"
			 +" LEFT JOIN HRMS_RANK  ON ( TESTREP_POSITION_CODE= RANK_ID )  "
			 +" LEFT JOIN HRMS_EMP_OFFC  ON (TESTREP_HIRINGMGR_CODE= EMP_ID )"
			 +" WHERE TESTREP_CODE = "+bean.getSearchSetting(); 
		Object [][] data = getSqlModel().getSingleResult(sqlFilter);
		
		if(data!= null && data.length >0)
		{
		bean.setReqname(checkNull(String.valueOf(data[0][0])));
		bean.setReqCode(checkNull(String.valueOf(data[0][1])));	  
		bean.setHiringMgrName(checkNull(String.valueOf(data[0][2])));
		bean.setHiringMgrCode(checkNull(String.valueOf(data[0][3])));  
		bean.setPosition(checkNull(String.valueOf(data[0][4])));
		bean.setPositionId(checkNull(String.valueOf(data[0][5]))); 
		bean.setDateFilter(checkNull(String.valueOf(data[0][6])));
		bean.setFrmDate(checkNull(String.valueOf(data[0][7])));  
		if(String.valueOf(data[0][8]).equals("V")){
			bean.setHidReportView("checked");
			bean.setHidReportRadio("");
		}else{
			bean.setHidReportRadio("checked");
			bean.setHidReportView("");
		} 
		bean.setSettingName(checkNull(String.valueOf(data[0][9])));
		bean.setSettingNameDup(checkNull(String.valueOf(data[0][9]))); 
		bean.setToDate(checkNull(String.valueOf(data[0][10]))); 
		bean.setSelectedReq(checkNull(String.valueOf(data[0][11])));  

		if(String.valueOf(data[0][11])!=null && String.valueOf(data[0][11]).length()>0 && ! String.valueOf(data[0][11]).equals(""))
		{     
			String reqSql ="SELECT  REQS_NAME FROM HRMS_REC_REQS_HDR WHERE REQS_CODE IN("+String.valueOf(data[0][11])+")";
		 	Object [][] reqData = getSqlModel().getSingleResult(reqSql );
    		if(reqData!=null && reqData.length>0)
			{  String reqCode=""; 
			   for (int i = 0; i < reqData.length; i++) {
				   if(i < reqData.length-1){ 
				   reqCode+=String.valueOf(reqData[i][0])+",";
				   }else{ 
				   reqCode+=String.valueOf(reqData[i][0]);
				   }
			   }
			   bean.setSelectedReqName(reqCode); 
			   bean.setHidSelectedReqName(reqCode); 
			} 
		}
		
		bean.setReportType(String.valueOf(data[0][12]));
		bean.setRadioReq("");
		bean.setRadioHiringMgr("");
		bean.setRadioPosition("");
		if(String.valueOf(data[0][13]).equals("C")){
			bean.setRadioReq("checked");
		 }  
		if(String.valueOf(data[0][13]).equals("H")){
			bean.setRadioHiringMgr("checked");
		 } 
		if(String.valueOf(data[0][13]).equals("P")){
			bean.setRadioPosition("checked");
		 }  
		}   
		bean.setRadioStatus(checkNull(String.valueOf(data[0][13])));
	} 
	 
	/**
	 * set the data of sort 
	 * @param bean
	 */
	public void callSortSavedData(SchTestAnalysis bean)
	{
		String sqlSort ="  SELECT SORT_BY, SORT_BY_ORDER,SORT_THENBY, SORT_THENBY_ORDER, SORT_THENBY1,"
					+" SORT_THENBY1_ORDER FROM HRMS_REC_TESTREP_SORT  WHERE TESTREP_CODE ="+bean.getSearchSetting();
     Object [][] sortData = getSqlModel().getSingleResult(sqlSort);
	 if(sortData!=null && sortData.length>0){
		bean.setSortBy(String.valueOf(sortData[0][0]).trim()); 
		if(String.valueOf(sortData[0][1]).trim().equals("A")){
			bean.setSortByAsc("checked");
			 
		}else{ 
			bean.setSortByDsc("checked");
		}
		
		bean.setThenBy1(String.valueOf(sortData[0][2]).trim());
		if(String.valueOf(sortData[0][3]).trim().equals("A")){
			bean.setThenByOrder1Asc("checked");
		}else{
			bean.setThenByOrder1Dsc("checked");
		}
		
		bean.setThenBy2(String.valueOf(sortData[0][4]).trim());
		if(String.valueOf(sortData[0][5]).trim().equals("A")){
			bean.setThenByOrder2Asc("checked");
		}else{
			bean.setThenByOrder2Dsc("checked");
		} 
	  }
	}
	
	/**
	 * set the data of column 
	 * @param bean
	 */
	
	public void callColumnSavedData(SchTestAnalysis bean)
	{
	String sqlColumn ="SELECT  COL_CANDI_NAME, COL_EMAILID, COL_CONTACT_NO, COL_TEST_TYPE, COL_TEST_DATE, "
			  +" COL_TEST_TIME, COL_TEST_VENUE, COL_USERID,  COL_PASSWORD,COL_RECRUITER, COL_SCH_STATUS ,COL_ROUND_TYPE  FROM HRMS_REC_TESTREP_COLDEF "
			  +" WHERE TESTREP_CODE ="+bean.getSearchSetting();
	 	  Object [][] data = getSqlModel().getSingleResult(sqlColumn);
	 	 
	 	 if(data!=null && data.length>0)
	 	 {  
	 		if(String.valueOf(data[0][0]).equals("Y")){
	 			 bean.setCandiCodeChk("checked"); 
			} 
	 		
	 		if(String.valueOf(data[0][1]).equals("Y")){
	 			 bean.setEmailIdChk("checked"); 
			} 
	 		
	 		if(String.valueOf(data[0][2]).equals("Y")){
	 			 bean.setContactNumChk("checked"); 
			} 
	 		
	 		if(String.valueOf(data[0][3]).equals("Y")){
	 			bean.setRoundTypeChk("checked"); 
			} 
	 		if(String.valueOf(data[0][4]).equals("Y")){
	 			bean.setInterviewDateChk("checked");  
			} 
	 		
	 		if(String.valueOf(data[0][5]).equals("Y")){
	 			bean.setInterviewTimeChk("checked"); 
			}  
	 		if(String.valueOf(data[0][6]).equals("Y")){
	 			bean.setInterviewVenueChk("checked");  
			}
	 		
	 		if(String.valueOf(data[0][7]).equals("Y")){
	 			bean.setUserIdChk("checked");  
			}  
	 		if(String.valueOf(data[0][8]).equals("Y")){
	 			bean.setPasswordChk("checked");  
			}    
	 		if(String.valueOf(data[0][9]).equals("Y")){
	 			bean.setRecruiterChk("checked"); 
			}  
	 		if(String.valueOf(data[0][10]).equals("Y")){
	 			bean.setSchStatusChk("checked");  
			}  
	 		if(String.valueOf(data[0][11]).equals("Y")){
	 			bean.setInterviewRoundTypeChk("checked");  
			} else{
				bean.setInterviewRoundTypeChk("");  
			}
			
	 	 }
	}
	
	/**
	 * set the data of advance filter 
	 * @param bean
	 */
	
	public void callAdvanceSavedData(SchTestAnalysis bean)
	{
	  String sqlAdvance =" SELECT ADV_SCH_STATUS,NVL(CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,''), ADV_CANDI_CODE FROM HRMS_REC_TESTREP_ADV " 
						+" LEFT JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_CAND_DATABANK.CAND_CODE=ADV_CANDI_CODE) "
						+" WHERE TESTREP_CODE= "+bean.getSearchSetting();
	  				Object [][] data = getSqlModel().getSingleResult(sqlAdvance );
	 	 
	 	 if(data!=null && data.length>0)
	 	 {
	 		 bean.setSchStatusCom(String.valueOf(data[0][0]));
	 		 bean.setCandidateName(checkNull(String.valueOf(data[0][1])));
	 		 bean.setCandidateCode(checkNull(String.valueOf(data[0][2]))); 
	 	 }
	  
	}
	
	/**
	 * set the data of reqution textarea 
	 * @param bean
	 */
	
	 
	public String checkNull(String result) {
		 
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	} // end of checkNull
	
	public Object [][] checkDuplicate (SchTestAnalysis bean)
	{
		System.out.println("bean.getSettingName().trim()---------------->"+bean.getSettingName().trim());
		String sql =" SELECT  TESTREP_NAME ,TESTREP_CODE FROM HRMS_REC_TESTREP_FILTERS WHERE UPPER(TESTREP_NAME) ='"+bean.getSettingName().trim().toUpperCase()+"' AND TESTREP_USEREMPID="+bean.getUserEmpId();
		System.out.println("sql-------->"+sql);
		return getSqlModel().getSingleResult(sql);    
		
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
	
	public void viewSummary(SchTestAnalysis bean)
	{
  String sql =" SELECT REQS_NAME, SUM(SCHEDULE), SUM(RESCHEDULE), SUM(CONDUCTED), SUM(CANCELD) FROM ( "
			+" SELECT NVL(REQS_NAME,'') AS REQS_NAME , "
			+" CASE WHEN TEST_STATUS='N' THEN COUNT(TEST_STATUS) ELSE 0 END AS SCHEDULE, " 
			+" CASE WHEN TEST_STATUS='R' THEN COUNT(TEST_STATUS) ELSE 0 END AS RESCHEDULE, " 
			+" ( CASE WHEN TEST_STATUS='Y' THEN COUNT(TEST_STATUS) ELSE 0 END + " 
			+" CASE WHEN TEST_STATUS='X' THEN COUNT(TEST_STATUS) ELSE 0 END  )AS CONDUCTED, "
			+" CASE WHEN TEST_STATUS='C' THEN COUNT(TEST_STATUS) ELSE 0 END AS CANCELD  "
			+" FROM  HRMS_REC_REQS_HDR "
			+" LEFT JOIN HRMS_REC_SCHTEST_DTL ON (HRMS_REC_REQS_HDR.REQS_CODE =HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE) "  
			+" INNER JOIN HRMS_EMP_OFFC HIRING_MGR ON (REQS_HIRING_MANAGER = HIRING_MGR.EMP_ID) "
			+" LEFT JOIN HRMS_RANK ON (REQS_POSITION = RANK_ID)  WHERE REQS_APPROVAL_STATUS IN ('A','Q') " ;
   
			  if(!bean.getReqCode().equals("")){						
				  sql+= " AND REQS_CODE ="+ bean.getReqCode();
				}  // end of if
				 
				 if(!bean.getHiringMgrCode().equals(""))
				 {
					 sql +=" AND HIRING_MGR.EMP_ID ="+bean.getHiringMgrCode();
				 }  
				 
				 if(!bean.getPositionId().equals(""))
				 {
					 sql +=" AND REQS_POSITION ="+bean.getPositionId();
				 } 
				 
				 if(!bean.getDateFilter().equals("1"))
				 {
					   if(bean.getDateFilter().equals("O"))
					   {
						   sql +=" AND REQS_DATE = TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
					   }
					   
					   if(bean.getDateFilter().equals("OB"))
					   {
						   sql +=" AND REQS_DATE <= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
					   }
					   
					   if(bean.getDateFilter().equals("OA"))
					   {
						   sql +=" AND REQS_DATE >= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
					   }
					   
					   if(bean.getDateFilter().equals("B"))
					   {
						   sql +=" AND REQS_DATE < TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
					   }
					   
					   if(bean.getDateFilter().equals("A"))
					   {
						   sql +=" AND REQS_DATE > TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
					   }
					   
					   if(bean.getDateFilter().equals("F"))
					   {
						   sql +=" AND REQS_DATE >= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')";
						   if(!(bean.getToDate().equals("")|| bean.getToDate().equals("dd-mm-yyyy")))
						      {
							   sql +=" AND REQS_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ; 
						   }
						 }
				 }    
				// ============  end of filter ===========  
				 
			    // ============ for selected requisition===========  
				  
				  if(!bean.getSelectedReq().equals("") && !bean.getSelectedReq().equals("null"))
				  {
					  sql+= " AND HRMS_REC_REQS_HDR.REQS_CODE IN ("+bean.getSelectedReq()+")";
				  } 
				  
				  // ============ for Candidate Requisition ===========  
				  String candiReqCode=callCandidateReq(bean);
				  if(!candiReqCode.equals(""))
					{
					  sql+= " AND HRMS_REC_REQS_HDR.REQS_CODE IN ("+candiReqCode+")";
					} 
			 
  	     sql +=" GROUP BY TEST_STATUS,REQS_NAME  "
			 +"	) GROUP BY REQS_NAME ORDER BY REQS_NAME ";
		Object [][] data = getSqlModel().getSingleResult(sql);
		if(data != null && data.length>0)
		{ 
			 bean.setDataSummLength(""+data.length);
			ArrayList summaryList = new ArrayList();  
			for (int i = 0; i < data.length; i++) {
				SchTestAnalysis bean1 = new SchTestAnalysis();
				bean1.setSReqName(String.valueOf(data[i][0]));
				bean1.setSSchedule(String.valueOf(data[i][1]));
				bean1.setSReschedule(String.valueOf(data[i][2]));
				bean1.setSConducted(String.valueOf(data[i][3]));
				bean1.setSCancel(String.valueOf(data[i][4]));
				summaryList.add(bean1);
			}
			bean.setSummaryList(summaryList);
		}else{
			bean.setNoDataSummFlag("true");
		}
	}
	
	public String callCandidateReq(SchTestAnalysis bean)
	{
		  String candiSql =" SELECT DISTINCT HRMS_REC_SCHTEST_DTL.TEST_REQS_CODE  FROM HRMS_REC_SCHTEST_DTL "   
				 +" INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_SCHTEST_DTL.TEST_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE) " 
				 +" WHERE 1=1  ";
		  if(!bean.getSchStatusCom().equals("1"))
			{
			  candiSql+= " AND TEST_STATUS ='"+bean.getSchStatusCom()+"'";
			}
			
			if(!bean.getCandidateCode().equals(""))
			{
				candiSql+= " AND HRMS_REC_CAND_DATABANK.CAND_CODE="+bean.getCandidateCode();
			} 
			
			String candiReqCode ="";
			
			 if(!bean.getSchStatusCom().equals("1") || !bean.getCandidateCode().equals(""))
				{  
					Object [][] candiObj = getSqlModel().getSingleResult(candiSql);
					if(candiObj != null && candiObj.length>0)
					{
						 for (int i = 0; i < candiObj.length; i++) {
								if(i < candiObj.length-1)
								{
									candiReqCode +=String.valueOf(candiObj[i][0])+",";
								}else{
									candiReqCode +=String.valueOf(candiObj[i][0]);
								}
							} 
					} 
					
				} 
		 return candiReqCode;
	}
}
