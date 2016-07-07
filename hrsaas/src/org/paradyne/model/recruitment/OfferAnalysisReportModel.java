 package org.paradyne.model.recruitment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Recruitment.OfferAnalysisReport; 
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class OfferAnalysisReportModel extends ModelBase{ 

	public void getReport(HttpServletRequest request,
			HttpServletResponse response,OfferAnalysisReport bean,String[] colnames) { 
					
		/**
		 * getMessage("serial.no"),getMessage("reqs.code"),getMessage("position"),getMessage("reqs.date"),getMessage("hiring.mgr"),getMessage("total.vac"),
				getMessage("rec.name"),getMessage("required.date"),getMessage("vac.assigned"),getMessage("vac.open"),getMessage("vac.close")
		 */
								String s="\n  Offer Letter Analysis \n\n";
								String reportType="";
								System.out.println("reportType-------1-------->"+bean.getReportType()+"<-------");
								if(bean.getReportType().equals("P")){
									reportType="Pdf";
								}
								if(bean.getReportType().equals("X")){
									reportType="Xls";
								}
								if(bean.getReportType().equals("T")){
									reportType="Txt";
								}
								System.out.println("reportType--------------->"+reportType+"<-------");
					org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator(reportType,s,"A3");
					if(bean.getReportType().equals("X")){
						 Object [][] title = new Object[2][3];
						 title [0][0] = "";
						 title [0][1] = "";
						 title [0][2] = "Offer Letter Analysis";  
						 
						 title [1][0] = "";
						 title [1][1] = "";
						 title [1][2] = "";  
						 
						 int [] cols = {20,20,30};
						 int [] align = {0,0,1};
						rg.tableBodyNoCellBorder(title,cols,align,1); 
						
					}
				 	Object tab[][]=getSqlModel().getSingleResult(callSqlQuery(bean));
					int length; 
				   String [] actualCol= new String [Integer.parseInt(bean.getCheckedCount()) + 1]; 
				   int cellwidth[]=new int [Integer.parseInt(bean.getCheckedCount()) + 1];  
					int alignment[]=new int [Integer.parseInt(bean.getCheckedCount()) + 1];  
					int k=1; 
					actualCol[0]=colnames[0];
					cellwidth[0]=15;
					alignment[0]=1;
					System.out.println("bean.getCheckedCount()===== "+bean.getCheckedCount());
					if(bean.getReqCodeChk().equals("on")){
						actualCol[k]=colnames[1];
						cellwidth[k]=50;
						alignment[k]=0;
						k++;
					} 
					if(bean.getPostionChk().equals("on")){
						actualCol[k]=colnames[2]; 
						cellwidth[k]=45;
						alignment[k]=0;
						k++;
					}  
					
					if(bean.getReqDateChk().equals("on")){
						actualCol[k]=colnames[3];
						cellwidth[k]=30;
						alignment[k]=1;
						k++;
					} 
					
					if(bean.getNovacChk().equals("on")){
						actualCol[k]=colnames[4];
						cellwidth[k]=30;
						alignment[k]=2;
						k++;
					} 
					
					
					if(bean.getOfferDueChk().equals("on")){
						actualCol[k]=colnames[5]; 
						cellwidth[k]=30;
						alignment[k]=2;
						k++;
					}  
					
					
					if(bean.getOfferIssueChk().equals("on")){
						actualCol[k]=colnames[6]; 
						cellwidth[k]=30;
						alignment[k]=2;
						k++;
					} 
					if(bean.getOfferAccptedChk().equals("on")){
						actualCol[k]=colnames[7]; 
						cellwidth[k]=30;
						alignment[k]=2;
						k++;
					}
					if(bean.getOfferRejectedChk().equals("on")){
						actualCol[k]=colnames[8]; 
						cellwidth[k]=30;
						alignment[k]=2;
						k++;
					} 
					if(bean.getOfferCancelChk().equals("on")){
						actualCol[k]=colnames[9]; 
						cellwidth[k]=30;
						alignment[k]=2;
						k++;
					}
					 
					actualCol[k]=colnames[10]; 
					cellwidth[k]=30;
					alignment[k]=2;
					
					length=tab.length;
					Object totalObj[][]=new Object[length][Integer.parseInt(bean.getCheckedCount())];
					
					rg.addFormatedText(s, 6, 0, 1, 0);
					if(bean.getReportType().equalsIgnoreCase("Xls"))
					{
						rg.addText("", 0, 6, 0);
						rg.addText(s, 0, 6, 0);
						rg.addText("", 0, 6, 0);
						rg.addText("", 0, 6, 0);
					}
					
					if(tab!=null && tab.length==0){
	
						rg.addText("No Records to Display",  0, 1, 0);
	
					} else{
						int m=1;
						int cnt=1;
					for (int i = 0; i < tab.length; i++) {
						
						totalObj[i][0]=cnt;
						cnt++;
						System.out.println("bean.getReqCodeChk()========= "+bean.getReqCodeChk());
						if(bean.getReqCodeChk().equals("on")){
							if(tab[i][0] !=null){
								totalObj[i][m]=checkNull(""+tab[i][0]);
							}
							else{
								totalObj[i][m]="";
							}
							m++;
							System.out.println("bean.getReqCodeChk()=value======== "+tab[i][0]);
						}   
						
						if(bean.getPostionChk().equals("on")){
							if(tab[i][1] !=null)	{					
								totalObj[i][m]=checkNull(""+tab[i][1]);
							}else
							{
								totalObj[i][m]="";
							}
							m++; 
						}   
						
						if(bean.getReqDateChk().equals("on")){
							if(tab[i][2] !=null){
								totalObj[i][m]=checkNull(""+tab[i][2]);
							}else{
								totalObj[i][m]="";
							}
							m++;
						}  
						
						if(bean.getNovacChk().equals("on")){
							if(tab[i][3] !=null){
								totalObj[i][m]=checkNull(""+tab[i][3]);
							}else{
								totalObj[i][m]="";
							}
							m++;
						}  
						
						if(bean.getOfferDueChk().equals("on")){
							if(tab[i][4] !=null){
								totalObj[i][m]=checkNull(""+tab[i][4]); 
							}else {
								totalObj[i][m]="";
							}
							m++;
						}
						
						if(bean.getOfferIssueChk().equals("on")){
							if(tab[i][5] !=null){
								totalObj[i][m]=checkNull(""+tab[i][5]); 
							}else {
								totalObj[i][m]="";
							}
							m++;
						}
						
						if(bean.getOfferAccptedChk().equals("on")){
							if(tab[i][6] !=null){
								totalObj[i][m]=checkNull(""+tab[i][6]);
							}else{
								totalObj[i][m]="";
							}
							m++;
						}
						if(bean.getOfferRejectedChk().equals("on")){
							if(tab[i][7] !=null){
								totalObj[i][m]=checkNull(""+tab[i][7]);
							}else{
								totalObj[i][m]="";
							}
							m++;
						} 
						
						if(bean.getOfferCancelChk().equals("on")){
							if(tab[i][8] !=null)
							{
								totalObj[i][m]=checkNull(""+tab[i][8]);
							}
							else{
								totalObj[i][m]="";
							}
							m++;
						}
						
						totalObj[i][m]=checkNull(""+tab[i][10]);;
						
						 
						m=1;
					}  // end of for loop
					System.out.println("bean.getExportAll()=============>>>"+bean.getExportAll());
						 // for page wise
					 if(!bean.getExportAll().equals("on")){
						 
							String[] pageIndex = Utility.doPaging(bean.getMyPage(),totalObj.length, 20);	
							if(pageIndex==null){
								pageIndex[0] = "0";
								pageIndex[1] ="20";
								pageIndex[2] = "1";
								pageIndex[3] = "1";
								pageIndex[4] = "";
							} 
							 int numOfRec = Integer.parseInt(pageIndex[1])-Integer.parseInt(pageIndex[0]) ;
							Object [][] pageObj =  new Object [numOfRec][Integer.parseInt(bean.getCheckedCount())];
							 int z =0;
							 int srNo =1;
							 for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {  
								 for (int j = 0; j < Integer.parseInt(bean.getCheckedCount()); j++) {
									 pageObj [z][j] =   totalObj [i][j];
									 pageObj [z][0] = String.valueOf(srNo);
								   } 
								 z++;
								 srNo++;
							 } 

						 if(k==1){
							 rg.addText("No Records to Display",  0, 1, 0);
						   }else{
							rg.tableBody(actualCol,pageObj, cellwidth, alignment);  
						   }
						 
						 if(bean.getReportType().equalsIgnoreCase("Xls")){
							 if(k==1){
								 rg.addText("No Records to Display",  0, 1, 0);
							   }else{
								rg.tableBodyNoBorder(pageObj,cellwidth,alignment);
							   }
						   }  
						 //================end of pagewise report========== 
						 
					 }else   // for export all the records
						  {
							 if(k==1){
								 rg.addText("No Records to Display",  0, 1, 0);
							   }else{
								rg.tableBody(actualCol,totalObj, cellwidth, alignment);  
							   }
							 
							 if(bean.getReportType().equalsIgnoreCase("X")){
								 if(k==1){
									 rg.addText("No Records to Display",  0, 1, 0);
								   }else{
									rg.tableBodyNoBorder(totalObj,cellwidth,alignment);
								   }
							   }
							 }
				}  
			 rg.createReport(response);
	}
	
	// for display the list of requistion on the pop up
	public void displayReq(OfferAnalysisReport bean)
	{ 
		
		String sql = "  SELECT NVL(REQS_NAME,' '),RANK_NAME,TO_CHAR(REQS_DATE ,'DD-MM-YYYY') AS REQ_DATE," +
		" DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A' ,'Approved','H','On Hold','B','New Requisition','Q','Quick Requisition') AS APPR_STATUS,REQS_CODE "
		+"  FROM HRMS_REC_REQS_HDR  INNER JOIN  HRMS_RANK ON (RANK_ID =REQS_POSITION) "
		+" WHERE REQS_APPROVAL_STATUS IN ('A','Q') ";
	 
		 
		 if(!bean.getPositionId().equals(""))
		 {
			  sql +=" AND REQS_POSITION ="+bean.getPositionId();
		 } 
		 
		 if(!bean.getDateFilter().equals("1") && bean.getReqCode().equals(""))
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
		 
		 sql +=" ORDER BY  REQS_DATE DESC";
		  Object [][] data = getSqlModel().getSingleResult(sql);
		  if(data!=null && data.length >0)
		  { 
			  bean.setDataLength(""+data.length);
			 ArrayList list = new ArrayList();  
			 for (int i = 0; i < data.length; i++) {
				 OfferAnalysisReport bean1 = new OfferAnalysisReport();
				 
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
	
	public void callViewScreen(OfferAnalysisReport bean,HttpServletRequest request)
	{ 
			
		if(bean.getReqCodeChk().equals("on")){
			bean.setReqCodeChkFlag("true");
		} 
		if(bean.getPostionChk().equals("on")){
			bean.setPostionChkFlag("true");  
		}  
		if(bean.getReqDateChk().equals("on")){
			bean.setReqDateChkFlag("true");  
		}  
		if(bean.getNovacChk().equals("on")){
			bean.setNovacChkFlag("true");
		} 
		if(bean.getOfferDueChk().equals("on")){
			bean.setOfferDueChkFlag("true");
		}
		
		if(bean.getOfferIssueChk().equals("on")){
			bean.setOfferIssueChkFlag("true");
		}
		if(bean.getOfferAccptedChk().equals("on")){
			bean.setOfferAccptedChkFlag("true");
		}
		if(bean.getOfferRejectedChk().equals("on")){
			bean.setOfferRejectedChkFlag("true");
		}
		if(bean.getOfferCancelChk().equals("on")){
			bean.setOfferCancelChkFlag("true");
		} 
		 
		
	    Object [][] data = getSqlModel().getSingleResult(callSqlQuery(bean)); 
		  if(data != null && data.length >0)
		  { 
			  bean.setDataLength(String.valueOf(data.length));
				String[] pageIndex = Utility.doPaging(bean.getMyPage(),data.length, 20);	
				if(pageIndex==null){
					pageIndex[0] = "0";
					pageIndex[1] ="20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				
				request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
				if(pageIndex[4].equals("1"))
					bean.setMyPage("1");
				
			   ArrayList openViewList = new ArrayList();  
			   for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
				   OfferAnalysisReport bean1= new OfferAnalysisReport();
				   bean1.setIvReqCode(callStringBreak(String.valueOf(data[i][0]),13)); 
				   bean1.setIvPostion(String.valueOf(data[i][1]));
				   bean1.setIvReqDate(String.valueOf(data[i][2])); 
				   bean1.setIvNoVac(String.valueOf(data[i][3])); 
				   bean1.setIvOfferDue(checkNull(String.valueOf(data[i][4]))); 
				   bean1.setIvOfferIssue(checkNull(String.valueOf(data[i][5])));  
				   bean1.setIvOfferAccept(checkNull(String.valueOf(data[i][6])));
				   bean1.setIvOfferReject(checkNull(String.valueOf(data[i][7]))); 
				   bean1.setIvOfferCancel(checkNull(String.valueOf(data[i][8])));  
				   bean1.setIvOfferDate(checkNull(String.valueOf(data[i][10])));  
				   
					if(bean.getReqCodeChk().equals("on")){
						bean1.setReqCodeChkFlag("true");
					}
					 
					if(bean.getPostionChk().equals("on")){
						bean1.setPostionChkFlag("true");  
					}  
					
					if(bean.getReqDateChk().equals("on")){
						bean1.setReqDateChkFlag("true");  
					} 
					
					if(bean.getNovacChk().equals("on")){
						bean1.setNovacChkFlag("true");  
					} 
					
					if(bean.getOfferDueChk().equals("on")){
						bean1.setOfferDueChkFlag("true");  
					} 
					 
					if(bean.getOfferIssueChk().equals("on")){
						bean1.setOfferIssueChkFlag("true");
					}
					if(bean.getOfferAccptedChk().equals("on")){
						bean1.setOfferAccptedChkFlag("true");
					}
					if(bean.getOfferRejectedChk().equals("on")){
						bean1.setOfferRejectedChkFlag("true");
					}
					if(bean.getOfferCancelChk().equals("on")){
						bean1.setOfferCancelChkFlag("true");
					}   
				   openViewList.add(bean1);
			  } 
			   bean.setOpenViewList(openViewList);
		  }else{
			  request.setAttribute("totalPage", new Integer(0));
				request.setAttribute("PageNo", new Integer(0));
				bean.setNoData("true");
		  }
	}
	
	public String callSqlQuery(OfferAnalysisReport bean)
	{
		String query=" SELECT REQS_NAME,RANK_NAME,TO_CHAR(REQS_DATE,'DD-MM-YYYY'),SUM(NVL(VACAN_NUMBERS,0)) AS VAC_NO, DUE1,ISSUED1,ACCEPTED1,REJECTED1,CANCELED1," +
				" REQUISITION_CODE, TO_CHAR(OFFER_DATE,'DD-MM-YYYY') AS OFFER_DATE FROM "
			+" ("
			+" 	SELECT REQS_NAME,RANK_NAME,SUM(ISSUED) AS ISSUED1,SUM(OFFER_DUE) AS DUE1 ,SUM(ACCEPTED) AS ACCEPTED1," 
			+"  SUM(REJECTED) AS REJECTED1 ,SUM(CANCELED) AS CANCELED1 ,REQUISITION_CODE ,REQS_DATE, OFFER_DATE FROM  "
			+" 	( SELECT REQS_NAME,RANK_NAME,  "
			+" 	CASE WHEN OFFER_STATUS ='I' THEN COUNT(OFFER_STATUS) ELSE 0 END AS ISSUED , "
			+" 	CASE WHEN OFFER_STATUS ='D' THEN COUNT(OFFER_STATUS) ELSE 0 END + "
			+" CASE WHEN OFFER_APPR_STATUS ='P' THEN COUNT(OFFER_STATUS) ELSE 0 END AS OFFER_DUE , "
			+" 	CASE WHEN OFFER_STATUS ='OA' THEN COUNT(OFFER_STATUS) ELSE 0 END AS ACCEPTED, "
			+" 	CASE WHEN OFFER_STATUS ='OR' THEN COUNT(OFFER_STATUS) ELSE 0 END +" 
			+"  CASE WHEN OFFER_APPR_STATUS ='R' THEN COUNT(OFFER_STATUS) ELSE 0 END AS REJECTED,  "
			+" 	CASE WHEN OFFER_STATUS ='C' THEN COUNT(OFFER_STATUS) ELSE 0 END AS CANCELED  ,HRMS_REC_REQS_HDR.REQS_CODE  AS REQUISITION_CODE ,REQS_DATE, OFFER_DATE "
			+" 	FROM HRMS_REC_REQS_HDR   "
			+" 	LEFT JOIN  HRMS_REC_OFFER ON (HRMS_REC_REQS_HDR.REQS_CODE =OFFER_REQS_CODE ) "
			+" 	LEFT JOIN  HRMS_RANK ON (RANK_ID =REQS_POSITION)  "
			+" 	where REQS_APPROVAL_STATUS IN ('A','Q') "
			+" 	GROUP BY REQS_NAME,RANK_NAME,OFFER_STATUS ,HRMS_REC_REQS_HDR.REQS_CODE ,REQS_DATE ,OFFER_APPR_STATUS,OFFER_DATE "
			+" 	) GROUP BY REQS_NAME,RANK_NAME ,REQUISITION_CODE ,REQS_DATE, OFFER_DATE  "
			+" 	) "
			+" 	LEFT join HRMS_REC_REQS_VACDTL on(HRMS_REC_REQS_VACDTL.REQS_CODE= REQUISITION_CODE) WHERE 1=1 ";
		
		// ============  started  filter ===========
			if(!bean.getReqCode().equals("")){						
				query+= " AND REQUISITION_CODE ="+ bean.getReqCode();
			}  // end of if
			 
			if(!bean.getOfferDate().equals("")){						
				query+= " AND OFFER_DATE = TO_DATE('"+ bean.getOfferDate()+"','DD-MM-YYYY')";
			}  // end of if
			
			 if(!bean.getPosition().trim().equals(""))
			 {
				 query +=" AND RANK_NAME ='"+bean.getPosition().trim()+"'";
			 } 
			 
			 if(!bean.getDateFilter().equals("1") && bean.getReqCode().equals(""))
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
			 
			  if(!bean.getSelectedReq().equals("") && !bean.getSelectedReq().equals("null"))
			  {
				  query+= " AND REQUISITION_CODE IN ("+bean.getSelectedReq()+")";
			  }
			  
			 
			// ============  end of filter =========== 
			// ============  start of advance =========== 
			 
			 if(!bean.getOfferIssueAdvCom().equals("1"))
			   {
				   query +=" AND ISSUED1 "+getAdvanceFilter(bean.getOfferIssueAdvCom())+" "+bean.getOfferIssueAdvTxt() ; 
			   }
			 
			 if(!bean.getOfferDueAdvCom().equals("1"))
			   {
				 query +=" AND DUE1 "+getAdvanceFilter(bean.getOfferDueAdvCom())+" "+bean.getOfferDueAdvTxt() ; 
			   }
			 
			 if(!bean.getOfferAcceptAdvCom().equals("1"))
			   {
				   query +=" AND ACCEPTED1 "+getAdvanceFilter(bean.getOfferAcceptAdvCom())+" "+bean.getOfferAcceptAdvTxt() ; 
			   }
			 
			 if(!bean.getOfferRejectAdvCom().equals("1"))
			   {
				   query +=" AND REJECTED1 "+getAdvanceFilter(bean.getOfferRejectAdvCom())+" "+bean.getOfferRejectAdvTxt() ; 
			   }
			 
			 if(!bean.getOfferCancelAdvCom().equals("1"))
			   {
				   query +=" AND CANCELED1 "+getAdvanceFilter(bean.getOfferCancelAdvCom())+" "+bean.getOfferCancelAdvTxt() ; 
			   }
			 
			 if(!bean.getNoVacAdvCom().equals("1"))
			   {
				   query +=" HAVING SUM(NVL(VACAN_NUMBERS,0)) "+getAdvanceFilter(bean.getNoVacAdvCom())+" "+bean.getNoVacAdvTxt() ; 
			   } 
			 query +="GROUP BY REQS_NAME,RANK_NAME ,REQUISITION_CODE ,ISSUED1, DUE1,ACCEPTED1,REJECTED1,CANCELED1,REQS_DATE,OFFER_DATE  ";
			 
			// ============  end of advance ===========
			    
			
			// ============  start of sorting ===========
			 if(!bean.getSortBy().equals("1") || ! bean.getThenBy1().equals("1") || ! bean.getThenBy2().equals("1"))
			  {
			      query+=" ORDER BY ";
			  }
			  if(!bean.getSortBy().equals("1"))
			  {
				  query+= getSortVal(bean.getSortBy()) +" "+getSortOrder(bean.getSortByOrder());
				  if(!bean.getThenBy1().equals("1")|| ! bean.getThenBy2().equals("1")){ 
					  query+=" , ";
				  }
			  }
			  
			  if(!bean.getThenBy1().equals("1"))
			  {
				  query+= getSortVal(bean.getThenBy1()) +" "+getSortOrder(bean.getThenByOrder1()); 
				  if(!bean.getThenBy2().equals("1")) {
					  query+=" , ";
				  }
			  }
			  
			  if(!bean.getThenBy2().equals("1"))
			  {
				  query+= getSortVal(bean.getThenBy2()) +" "+getSortOrder(bean.getThenByOrder2());
			  }
			// ============  end of sorting ===========  
			  
			
			
			return query;
	}
	
	public String getSortVal(String Status)
	{   String sql="";
		if(Status.equals("R")){
			sql=" UPPER(REQS_NAME) ";
		}   
		if(Status.equals("D"))
		{
			sql=" REQS_DATE";
		}
		if(Status.equals("V")){
			sql="VAC_NO ";
		} 
		 
		if(Status.equals("U")){
			sql=" DUE1 ";
		} 
		if(Status.equals("I")){
			sql=" ISSUED1 ";
		} 
		if(Status.equals("A"))
		{
			sql=" ACCEPTED1 ";
		}
		if(Status.equals("J")){
			sql=" REJECTED1 ";
		}
		if(Status.equals("C")){
			sql=" CANCELED1 ";
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
 
	public String getAdvanceFilter(String Status)
	{ 
	   String sql=""; 
	   if(Status.equals("IE")) {		   
		   sql="=";
	   } 
	   if(Status.equals("LE")) {		   
		   sql="<=";
	   }
	   
	   if(Status.equals("GE")) {		   
		   sql=">=";
	   } 
	   if(Status.equals("LT")) {		   
		   sql="<";
	   } 
	   if(Status.equals("GT")) {		   
		   sql=">";
	   } 
	   return sql ;
	}
	
	public void callBack(OfferAnalysisReport bean)
	{
		if(bean.getReqCodeChk().equals("on")){
			bean.setReqCodeChk("checked");
		} 
		if(bean.getPostionChk().equals("on")){
			bean.setPostionChk("checked");
		}  
		if(bean.getReqDateChk().equals("on")){
			bean.setReqDateChk("checked");
		}  
		
		if(bean.getNovacChk().equals("on")){
			bean.setNovacChk("checked");
		} 
		
		if(bean.getOfferDueChk().equals("on")){
			bean.setOfferDueChk("checked");
		}
		
		if(bean.getOfferIssueChk().equals("on")){
			bean.setOfferIssueChk("checked");
		}
		if(bean.getOfferAccptedChk().equals("on")){
			bean.setOfferAccptedChk("checked");
		}
		if(bean.getOfferRejectedChk().equals("on")){
			bean.setOfferRejectedChk("checked");
		}
		if(bean.getOfferCancelChk().equals("on")){
			bean.setOfferCancelChk("checked");
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
	
	public void input(OfferAnalysisReport bean)
	{
		    bean.setReqCodeChk("checked"); 
			bean.setPostionChk("checked");  
			bean.setReqDateChk("checked"); 
			bean.setNovacChk("checked"); 
			bean.setOfferDueChk("checked"); 
			bean.setOfferIssueChk("checked"); 
			bean.setOfferAccptedChk("checked"); 
			bean.setOfferRejectedChk("checked");  
			bean.setOfferCancelChk("checked");   
	}
	
	public void callPreviousRecord(OfferAnalysisReport bean)
	{
	  String sql =" SELECT OFFERREP_CODE, OFFERREP_NAME FROM HRMS_REC_OFFERREP_FILTERS WHERE OFFERREP_USEREMPID ="+bean.getUserEmpId()+" ORDER BY UPPER(OFFERREP_NAME) ";
	  Object [][] data = getSqlModel().getSingleResult(sql);
		  if(data!=null && data.length>0)
		  {  LinkedHashMap map = new LinkedHashMap();
			  for (int i = 0; i < data.length; i++) {
				  map.put(data[i][0], data[i][1]);
			}
			  bean.setMap(map); 
		  }
		 
	}
	public boolean callSave(OfferAnalysisReport bean)
	{
		if(saveFiletr(bean) && saveSort(bean) && saveColumnDef(bean) && saveAdvance(bean))
		{
			return true;
		}else{
		return false;
		}
	}
	
	public boolean saveFiletr(OfferAnalysisReport bean)
	{   
		Object [][] filterObj = null;
		if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
			filterObj= new Object [1][11];  
		}else{
			filterObj= new Object [1][12];   
		}
		filterObj [0][0]=bean.getReqCode().equals("")?null : bean.getReqCode(); 
		filterObj [0][1]=bean.getPositionId().equals("")?null :bean.getPositionId();
		 if(!bean.getDateFilter().equals("1") && bean.getReqCode().equals(""))
		 {
			   if(bean.getDateFilter().equals("O") )
			   {
				   filterObj [0][2]="O";
			   }
			   
			   if(bean.getDateFilter().equals("B"))
			   {
				   filterObj [0][2]="B";
			   }
			   
			   if(bean.getDateFilter().equals("A"))
			   {
				   filterObj [0][2]="A";
			   }
			   
			   if(bean.getDateFilter().equals("OB"))
			   {
				   filterObj [0][2]="OB";
			   }
			   
			   if(bean.getDateFilter().equals("OA"))
			   {
				   filterObj [0][2]="OA";
			   }
			   
			   if(bean.getDateFilter().equals("F"))
			   {
				   filterObj [0][2]="F";
				 }
		 }  else{
			 filterObj [0][2]="1";
		 }     
		 filterObj [0][3]=bean.getDateFilter().equals("1")? null: bean.getFrmDate().equals("")? null : bean.getFrmDate();
		 filterObj [0][4]=bean.getReqStatus().equals("")?"V":bean.getReqStatus();
		 filterObj [0][5]=bean.getReportType().equals("1")?"1":bean.getReportType();   
		 filterObj [0][6]=bean.getSettingName();  
		 filterObj [0][7]=(!bean.getDateFilter().equals("F"))? null:bean.getToDate().equals("")? null : bean.getToDate(); 
	     filterObj [0][8]=bean.getUserEmpId();  
		 filterObj [0][9]=bean.getRadioStatus();
		 filterObj [0][10]=bean.getSelectedReq();
	   boolean flag= false;
	   if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
		   flag = getSqlModel().singleExecute(getQuery(1),filterObj);
	   }else{
		   filterObj[0][11]=bean.getSearchSetting();
		   flag =  getSqlModel().singleExecute(getQuery(5),filterObj);
	   }
		System.out.println("flag======filetr========= "+flag); 
		return flag;
	}
	
	public boolean saveSort(OfferAnalysisReport bean)
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
		 
	    return flag;
	}
	
	public boolean saveColumnDef(OfferAnalysisReport bean)
	{   
		Object [][] colObj = null;
		  if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
			  colObj = new Object[1][9];
		  }else{
			  colObj = new Object[1][10];
		  } 
		  
		colObj[0][0]=bean.getReqCodeChk().equals("on")?"Y":"N";
		colObj[0][1]=bean.getPostionChk().equals("on")?"Y":"N";
		colObj[0][2]=bean.getReqDateChk().equals("on")?"Y":"N";
		colObj[0][3]=bean.getNovacChk().equals("on")?"Y":"N";
		colObj[0][4]=bean.getOfferDueChk().equals("on")?"Y":"N";
		colObj[0][5]=bean.getOfferIssueChk().equals("on")?"Y":"N";
		colObj[0][6]=bean.getOfferAccptedChk().equals("on")?"Y":"N";
		colObj[0][7]=bean.getOfferRejectedChk().equals("on")?"Y":"N";  
		colObj[0][8]=bean.getOfferCancelChk().equals("on")?"Y":"N";  
		
		boolean flag= false; 
		 if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
			 flag = getSqlModel().singleExecute(getQuery(3),colObj); 
		 }else{
			 colObj[0][9]=bean.getSearchSetting();
			 flag = getSqlModel().singleExecute(getQuery(7),colObj); 
		 }
		System.out.println("flag======column========= "+flag);
	    return flag;
	}
	
	public boolean saveAdvance(OfferAnalysisReport bean)
	{  
		Object [][] colAdv =null;
		 if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
		   colAdv = new Object[1][12];
		 }else{
			 colAdv = new Object[1][13];
		 }
		 
		
				    
		colAdv[0][0]=bean.getNoVacAdvCom();
		colAdv[0][1]=bean.getNoVacAdvTxt().equals("")?null :bean.getNoVacAdvTxt();
		colAdv[0][2]=bean.getOfferDueAdvCom();
		colAdv[0][3]=bean.getOfferDueAdvTxt().equals("")?null :bean.getOfferDueAdvTxt();
		colAdv[0][4]=bean.getOfferIssueAdvCom();
		colAdv[0][5]=bean.getOfferIssueAdvTxt().equals("")?null :bean.getOfferIssueAdvTxt();
		colAdv[0][6]=bean.getOfferAcceptAdvCom();
		colAdv[0][7]=bean.getOfferAcceptAdvTxt().equals("")?null :bean.getOfferAcceptAdvTxt();
		colAdv[0][8]=bean.getOfferRejectAdvCom().trim();
		colAdv[0][9]=bean.getOfferRejectAdvTxt().equals("")?null :bean.getOfferRejectAdvTxt();
		colAdv[0][10]=bean.getOfferCancelAdvCom();
		colAdv[0][11]=bean.getOfferCancelAdvTxt().equals("")?null :bean.getOfferCancelAdvTxt();
		boolean flag= false;
		 if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
			 flag= getSqlModel().singleExecute(getQuery(4),colAdv); 
		 }else{
			   colAdv[0][12]=bean.getSearchSetting();
			   flag = getSqlModel().singleExecute(getQuery(8),colAdv); 
		 }
		System.out.println("flag======advance========= "+flag);
	    return flag;
	}
 
	
	
	public boolean callUpdate(OfferAnalysisReport bean)
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
	public void callFilterSavedData(OfferAnalysisReport bean)
	{ 
		
 String sqlFilter ="   SELECT REQS_NAME , OFFERREP_REQ_CODE,NVL(RANK_NAME,' '), OFFERREP_POSITION_CODE, "
		   +" OFFERREP_DATEOPTION, TO_CHAR(OFFERREP_REQDATE_FROM,'DD-MM-YYYY'), OFFERREP_REQSTATUS, "
		   +" OFFERREP_REPOPTION, OFFERREP_NAME,TO_CHAR(OFFERREP_REQDATE_TO,'DD-MM-YYYY'),  "
		   +" OFFERREP_RADIO_OPTION, OFFERREP_SELECTED_REQ FROM HRMS_REC_OFFERREP_FILTERS "		   
		   +" LEFT JOIN HRMS_REC_REQS_HDR ON (OFFERREP_REQ_CODE = REQS_CODE) "
		   +" LEFT JOIN HRMS_RANK  ON (OFFERREP_POSITION_CODE = RANK_ID ) "		   
		   +" WHERE OFFERREP_CODE ="+bean.getSearchSetting(); 
	 
		Object [][] data = getSqlModel().getSingleResult(sqlFilter);
		
		if(data!= null && data.length >0)
		{
		bean.setReqname(checkNull(String.valueOf(data[0][0])));
		bean.setReqCode(checkNull(String.valueOf(data[0][1])));	  
		bean.setPosition(checkNull(String.valueOf(data[0][2])));
		bean.setPositionId(checkNull(String.valueOf(data[0][3]))); 
		bean.setDateFilter(checkNull(String.valueOf(data[0][4])).trim());
		bean.setFrmDate(checkNull(String.valueOf(data[0][5]))); 
		if(String.valueOf(data[0][6]).equals("V")){
			bean.setHidReportView("checked");
			bean.setHidReportRadio("");
		}else{
			bean.setHidReportRadio("checked");
			bean.setHidReportView("");
		}
		bean.setReportType(String.valueOf(data[0][7]));
		bean.setSettingName(checkNull(String.valueOf(data[0][8])));
		bean.setSettingNameDup(checkNull(String.valueOf(data[0][8]))); 
		bean.setToDate(checkNull(String.valueOf(data[0][9])));  
		if(String.valueOf(data[0][10]).equals("C")){
			bean.setRadioReq("checked");
		 } 
		if(String.valueOf(data[0][10]).equals("N")){
			bean.setRadioRecr("checked");
		 } 
		if(String.valueOf(data[0][10]).equals("M")){
			bean.setRadioHirMng("checked");
		 } 
		if(String.valueOf(data[0][10]).equals("P")){
			bean.setRadioPosition("checked");
		 } 
		bean.setSelectedReq(checkNull(String.valueOf(data[0][11]))); 
		 
		if(bean.getSelectedReq()!=null && bean.getSelectedReq().length()>0)
		{     
			String reqSql ="SELECT  REQS_NAME FROM HRMS_REC_REQS_HDR WHERE REQS_CODE IN("+bean.getSelectedReq()+")";
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
		
		}  
	} 
	 
	/**
	 * set the data of sort 
	 * @param bean
	 */
	public void callSortSavedData(OfferAnalysisReport bean)
	{
		String sqlSort ="  SELECT SORT_BY, SORT_BY_ORDER, SORT_THENBY, SORT_THENBY_ORDER, SORT_THENBY1,  "
				  +" SORT_THENBY1_ORDER FROM HRMS_REC_OFFERREP_SORT WHERE OFFREP_CODE = "+bean.getSearchSetting();
     Object [][] sortData = getSqlModel().getSingleResult(sqlSort);
	 if(sortData!=null && sortData.length>0){
		bean.setSortBy(String.valueOf(sortData[0][0]).trim());
		if(String.valueOf(sortData[0][1]).equals("A")){
			bean.setSortByAsc("checked");
		}else{
			bean.setSortByDsc("checked");
		}
		
		bean.setThenBy1(String.valueOf(sortData[0][2]).trim());
		if(String.valueOf(sortData[0][3]).equals("A")){
			bean.setThenByOrder1Asc("checked");
		}else{
			bean.setThenByOrder1Dsc("checked");
		}
		
		bean.setThenBy2(String.valueOf(sortData[0][4]).trim());
		if(String.valueOf(sortData[0][5]).equals("A")){
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
	
	public void callColumnSavedData(OfferAnalysisReport bean)
	{
	String sqlColumn ="SELECT COL_REQNAME,COL_POSITION, COL_REQDATE,COL_NUMVAC, COL_OFFER_DUE, COL_OFFER_ISSUE, " 
					 +" COL_OFFER_ACCP, COL_OFFER_REJECT, COL_OFFER_CANCEL "
					 +" FROM HRMS_REC_OFFERREP_COLDEF WHERE OFFREP_CODE ="+bean.getSearchSetting();
	 	 Object [][] data = getSqlModel().getSingleResult(sqlColumn);
	 	 
	 	 if(data!=null && data.length>0)
	 	 {
	 		
	 		if(String.valueOf(data[0][0]).equals("Y")){
				bean.setReqCodeChk("checked");
			} 
	 		if(String.valueOf(data[0][1]).equals("Y")){
				bean.setPostionChk("checked");
			}   
	 		if(String.valueOf(data[0][2]).equals("Y")){
				bean.setReqDateChk("checked");
			}  
	 		
	 		if(String.valueOf(data[0][3]).equals("Y")){
				bean.setNovacChk("checked");
			} 
	 		if(String.valueOf(data[0][4]).equals("Y")){
				bean.setOfferDueChk("checked");
			}
	 		if(String.valueOf(data[0][5]).equals("Y")){
				bean.setOfferIssueChk("checked");
			}
	 		if(String.valueOf(data[0][6]).equals("Y")){
				bean.setOfferAccptedChk("checked");
			}
	 		if(String.valueOf(data[0][7]).equals("Y")){
				bean.setOfferRejectedChk("checked");
			}
	 		if(String.valueOf(data[0][8]).equals("Y")){
				bean.setOfferCancelChk("checked");
			} 
	 		 
	 	 }
	}
	
	/**
	 * set the data of advance filter 
	 * @param bean
	 */
	
	public void callAdvanceSavedData(OfferAnalysisReport bean)
	{
	  String sqlAdvance ="  SELECT  OFFREP_NOOFVAC_OPTION, OFFREP_NOOFVAC, OFFREP_DUE_OPTION,OFFREP_DUE, OFFREP_ISSUE_OPTION, OFFREP_ISSUE, " +
	  					" OFFREP_ACCP_OPTION, OFFREP_ACCP, OFFREP_REJ_OPTION, OFFREP_REJECT, OFFREP_CANCEL_OPTION, OFFREP_CANCEL  "
					  +" FROM HRMS_REC_OFFERREP_ADV WHERE OFFREP_CODE ="+bean.getSearchSetting();
	  Object [][] data = getSqlModel().getSingleResult(sqlAdvance );
	 	 
	 	 if(data!=null && data.length>0)
	 	 { 
	 		 bean.setNoVacAdvCom(String.valueOf(data[0][0]));
	 		 bean.setNoVacAdvTxt(checkNull(String.valueOf(data[0][1]))); 
	 		 bean.setOfferDueAdvCom(String.valueOf(data[0][2]));
	 		 bean.setOfferDueAdvTxt(checkNull(String.valueOf(data[0][3])));
	 		 bean.setOfferIssueAdvCom(String.valueOf(data[0][4]));
	 		 bean.setOfferIssueAdvTxt(checkNull(String.valueOf(data[0][5]))); 
	 		 bean.setOfferAcceptAdvCom(String.valueOf(data[0][6]));
	 		 bean.setOfferAcceptAdvTxt(checkNull(String.valueOf(data[0][7])));  
	 		 bean.setOfferRejectAdvCom(String.valueOf(data[0][8]).trim());
	 		 bean.setOfferRejectAdvTxt(checkNull(String.valueOf(data[0][9])));
	 		 bean.setOfferCancelAdvCom(checkNull(String.valueOf(data[0][10]))); 
	 		 bean.setOfferCancelAdvTxt(checkNull(String.valueOf(data[0][11]))); 
	 	 }
	  
	}
	

	public String checkNull(String result) {
		 
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	} // end of checkNull
	
	public Object [][] checkDuplicate (OfferAnalysisReport bean)
	{
		String sql =" SELECT OFFERREP_NAME,OFFERREP_CODE FROM HRMS_REC_OFFERREP_FILTERS "
				 +" WHERE UPPER(OFFERREP_NAME) = '"+bean.getSettingName().toUpperCase()+"' AND OFFERREP_USEREMPID= "+bean.getUserEmpId();
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
	
	
}
