package org.paradyne.model.recruitment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Recruitment.AdvertiseReport; 
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class AdvertiseReportModel extends ModelBase{ 

	public void getReport(HttpServletRequest request,
			HttpServletResponse response,AdvertiseReport bean,String[] colnames) { 
					
		/**
		 * getMessage("serial.no"),getMessage("reqs.code"),getMessage("position"),getMessage("reqs.date"),getMessage("hiring.mgr"),getMessage("total.vac"),
				getMessage("rec.name"),getMessage("required.date"),getMessage("vac.assigned"),getMessage("vac.open"),getMessage("vac.close")
		 */
								String s="\n  Advertisement Analysis  \n\n";
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
					org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator(reportType,s,"A4");
					
					if(bean.getReportType().equals("X")){
						 Object [][] title = new Object[2][3];
						 title [0][0] = "";
						 title [0][1] = "";
						 title [0][2] = "Advertisement Analysis";  
						 
						 title [1][0] = "";
						 title [1][1] = "";
						 title [1][2] = "";  
						 
						 int [] cols = {20,20,30};
						 int [] align = {0,0,1};
						rg.tableBodyNoCellBorder(title,cols,align,1); 
						
					}
					
				 	Object tab[][]=getSqlModel().getSingleResult(callSqlQuery(bean));
					int length; 
				   String [] actualCol= new String [Integer.parseInt(bean.getCheckedCount())]; 
				    int cellwidth[]= new int [Integer.parseInt(bean.getCheckedCount())];  
					int alignment[]=new int [Integer.parseInt(bean.getCheckedCount())];  
					int k=1; 
					actualCol[0]=colnames[0];
					cellwidth[0]=15;
					alignment[0]=1;   
					 
					if(bean.getReqCodeChk().equals("on")){
						actualCol[k]=colnames[1];
						cellwidth[k]=30;
						alignment[k]=0;
						k++;
					} 
					
					if(bean.getPositionChk().equals("on")){
						actualCol[k]=colnames[2]; 
						cellwidth[k]=45;
						alignment[k]=0;
						k++;
					}  
					
					if(bean.getNumOfVacChk().equals("on")){
						actualCol[k]=colnames[3]; 
						cellwidth[k]=25;
						alignment[k]=2;
						k++;
					}  
					if(bean.getModeOfAdvChk().equals("on")){
						actualCol[k]=colnames[4];
						cellwidth[k]=45;
						alignment[k]=0;
						k++;
					} 
					if(bean.getAgencuNameChk().equals("on")){
						actualCol[k]=colnames[5]; 
						cellwidth[k]=35;
						alignment[k]=0;
						k++;
					}
					if(bean.getAdvDateChk().equals("on")){
						actualCol[k]=colnames[6]; 
						cellwidth[k]=25;
						alignment[k]=1;
						k++;
					}
					if(bean.getAdvCostChk().equals("on")){
						actualCol[k]=colnames[7]; 
						cellwidth[k]=35;
						alignment[k]=2;
						k++;
					}
					if(bean.getOnlineResChk().equals("on")){
						actualCol[k]=colnames[8]; 
						cellwidth[k]=45;
						alignment[k]=2;
						k++;
					}  
					 
					
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
						if(bean.getReqCodeChk().equals("on")){
							if(tab[i][0] !=null){
								totalObj[i][m]=checkNull(""+tab[i][0]);
							}
							else{
								totalObj[i][m]="";
							}
							m++; 
						}   
						 
						if(bean.getPositionChk().equals("on")){
							if(tab[i][1] !=null){
								totalObj[i][m]=checkNull(""+tab[i][1]);
							}
							else{
								totalObj[i][m]="";
							}
							m++; 
						}   
						
						if(bean.getNumOfVacChk().equals("on")){
							if(tab[i][2] !=null)	{					
								totalObj[i][m]=checkNull(""+tab[i][2]);
							}else
							{
								totalObj[i][m]="";
							}
							m++; 
						}  
						if(bean.getModeOfAdvChk().equals("on")){
							if(tab[i][3] !=null){
								totalObj[i][m]=checkNull(""+tab[i][3]);
							}else{
								totalObj[i][m]="";
							}
							m++;
						}  
						
						
						if(bean.getAgencuNameChk().equals("on")){
							if(tab[i][4] !=null){
								totalObj[i][m]=checkNull(""+tab[i][4]); 
							}else {
								totalObj[i][m]="";
							}
							m++;
						}
					 
						if(bean.getAdvDateChk().equals("on")){
							if(tab[i][5] !=null)
							{
								totalObj[i][m]=checkNull(""+tab[i][5]);
							}
							else{
								totalObj[i][m]="";
							}
							m++;
						}
						if(bean.getAdvCostChk().equals("on")){
							if(tab[i][6] !=null){
								totalObj[i][m]=checkNull(""+tab[i][6]);
							}else{
								totalObj[i][m]="";
							}
							m++;
						}
						if(bean.getOnlineResChk().equals("on")){
							if(tab[i][7] !=null){
								totalObj[i][m]=checkNull(""+tab[i][7]);
							}else{
								totalObj[i][m]="";
							}
							m++;
						} 
						 
						m=1;
					}  // end of for loop
					
						 
					 // end of for loop
					
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
							 
							 if(bean.getReportType().equalsIgnoreCase("Xls")){
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
	public void displayReq(AdvertiseReport bean)
	{
		String sql ="  SELECT distinct REQS_NAME,RANK_NAME,TO_CHAR(REQS_DATE ,'DD-MM-YYYY'), "
				+" DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A'  ,'Approved','H','On Hold','B','New Requisition','Q','Quick Requisition')," 
				+" HRMS_REC_ADVT_HDR.REQS_CODE FROM HRMS_REC_ADVT_HDR "
				+" INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_ADVT_HDR.REQS_CODE )"
				+" INNER JOIN  HRMS_REC_ADVT_DTL ON (HRMS_REC_ADVT_DTL.ADVT_CODE = HRMS_REC_ADVT_HDR.ADVT_CODE ) "
				+" INNER JOIN  HRMS_RANK ON (RANK_ID =REQS_POSITION) "
				+"  WHERE REQS_APPROVAL_STATUS IN ('A','Q') ";
		 
		 
		 if(!bean.getPositionId().equals(""))
		 {
			  sql +=" AND REQS_POSITION ="+bean.getPositionId();
		 } 
		 
		 if(!bean.getModeOfAdvertise().equals("1"))
		 {
			  sql +=" AND ADVT_MODE ='"+bean.getModeOfAdvertise()+"'";
		 } 
		 
		 if(!bean.getDateFilter().equals("1") && bean.getReqCode().equals(""))
		 {
			   if(bean.getDateFilter().equals("O"))
			   {
				   sql +=" AND ADVT_START_DATE = TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
			   }
			   
			   if(bean.getDateFilter().equals("OB"))
			   {
				   sql +=" AND ADVT_START_DATE <= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
			   }
			   
			   if(bean.getDateFilter().equals("OA"))
			   {
				   sql +=" AND REQS_DATE >= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
			   }
			   
			   if(bean.getDateFilter().equals("B"))
			   {
				   sql +=" AND ADVT_START_DATE < TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
			   }
			   
			   if(bean.getDateFilter().equals("A"))
			   {
				   sql +=" AND ADVT_START_DATE > TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
			   }
			   
			   if(bean.getDateFilter().equals("F"))
			   {
				   sql +=" AND ADVT_START_DATE >= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')";
				   if(!(bean.getToDate().equals("")||bean.getToDate().equals("dd-mm-yyyy"))){
				   sql +=" AND ADVT_START_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ;
				   }
				 }
		 } 
		 
		  Object [][] data = getSqlModel().getSingleResult(sql);
		  if(data!=null && data.length >0)
		  { 
			  bean.setDataLength(""+data.length);
			 ArrayList list = new ArrayList();  
			 for (int i = 0; i < data.length; i++) {
				 AdvertiseReport bean1 = new AdvertiseReport();
				 bean1.setItReqName(callStringBreak(""+data[i][0],13));
				 bean1.setItPosition(""+data[i][1]);
				 bean1.setItReqDate(""+data[i][2]);
				 bean1.setItStatus(""+data[i][3]);
				 bean1.setItReqCode(""+data[i][4]); 
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
	
	public void callViewScreen(AdvertiseReport bean,HttpServletRequest request)
	{ 
			
		if(bean.getReqCodeChk().equals("on")){
			bean.setReqCodeChkFlag("true");
		} 
		
		if(bean.getPositionChk().equals("on")){
			bean.setPositionChkFlag("true");
		} 
		
		if(bean.getNumOfVacChk().equals("on")){
			bean.setNumOfVacChkFlag("true");
		}
		if(bean.getModeOfAdvChk().equals("on")){
			bean.setModeOfAdvChkFlag("true");
		}
		if(bean.getAgencuNameChk().equals("on")){
			bean.setAgencuNameChkFlag("true"); 
		}
		if(bean.getAdvDateChk().equals("on")){
			bean.setAdvDateChkFlag("true");
		}
		
		if(bean.getAdvCostChk().equals("on")){
			bean.setAdvCostChkFlag("true");
		}
		if(bean.getOnlineResChk().equals("on")){
			bean.setOnlineResChkFlag("true");
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
				
				/**
				 *  
		+"  SUM(NVL(ADVT_COST, 0)) AS COST, SUM(NVL(ADVT_RESPONSES, 0)) AS RESPONSE
				 */
				
			   ArrayList advertiseViewList = new ArrayList();  
			   for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
				   AdvertiseReport bean1= new AdvertiseReport();
				   bean1.setIvReqCode(String.valueOf(data[i][0])); 
				   bean1.setIvPostion(String.valueOf(data[i][1]));
				   bean1.setIvNoVac(checkNull(String.valueOf(data[i][2])));
				   bean1.setIvModeOfAdv(checkNull(String.valueOf(data[i][3])));
				   bean1.setIvAgencyName(String.valueOf(data[i][4]));
				   bean1.setIvAdvDate(checkNull(String.valueOf(data[i][5]))); 
				   bean1.setIvAdvCost(checkNull(String.valueOf(data[i][6]))); 
				   bean1.setIvOnlineResp(checkNull(String.valueOf(data[i][7])));
				
				   
					if(bean.getReqCodeChk().equals("on")){
						bean1.setReqCodeChkFlag("true");
					} 
					if(bean.getPositionChk().equals("on")){
						bean1.setPositionChkFlag("true");
					} 
					
					if(bean.getNumOfVacChk().equals("on")){
						bean1.setNumOfVacChkFlag("true");
					}
					if(bean.getModeOfAdvChk().equals("on")){
						bean1.setModeOfAdvChkFlag("true");
					}
					if(bean.getAgencuNameChk().equals("on")){
						bean1.setAgencuNameChkFlag("true"); 
					}
					if(bean.getAdvDateChk().equals("on")){
						bean1.setAdvDateChkFlag("true");
					}
					
					if(bean.getAdvCostChk().equals("on")){
						bean1.setAdvCostChkFlag("true");
					}
					if(bean.getOnlineResChk().equals("on")){
						bean1.setOnlineResChkFlag("true");
					} 
					
					advertiseViewList.add(bean1);
			  } 
			   bean.setAdvertiseViewList(advertiseViewList);
		  }else{
			  request.setAttribute("totalPage", new Integer(0));
				request.setAttribute("PageNo", new Integer(0));
				bean.setNoData("true");
		  }
	}
	
	public String callSqlQuery(AdvertiseReport bean)
	{
String query="SELECT DISTINCT   REQS_NAME , RANK_NAME,NVL(ADVT_NOOFVAC,0) AS NOVAC,DECODE (ADVT_MODE,'N','News Paper','T','TV Media','W','Website','O','Other') AS ADVMODE ," 
		+" NVL(ADVT_NAME,'') as agencyName, NVL(TO_CHAR(ADVT_START_DATE,'DD-MM-YYYY'),'') AS ADVDATE,SUM(NVL(ADVT_COST, 0)) AS COST, SUM(NVL(ADVT_RESPONSES, 0)) AS RESPONSE ,ADVT_START_DATE "
		+" FROM HRMS_REC_ADVT_HDR "
		+" INNER JOIN HRMS_REC_ADVT_DTL ON(HRMS_REC_ADVT_DTL.ADVT_CODE = HRMS_REC_ADVT_HDR.ADVT_CODE) "
		+" INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_ADVT_HDR.REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE ) "
		+" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = ADVT_POSITION) "
		+" WHERE 1=1";
		// ============  started  filter ===========
			if(!bean.getReqCode().equals("")){						
				query+= "  AND HRMS_REC_ADVT_HDR.REQS_CODE ="+ bean.getReqCode();
			}  // end of if
			 
			 
			 if(!bean.getPositionId().equals(""))
			 {
				 query +=" AND ADVT_POSITION ="+bean.getPositionId();
			 } 
			 
			 if(!bean.getModeOfAdvertise().equals("1"))
			 {
				 query +=" AND ADVT_MODE ='"+bean.getModeOfAdvertise()+"'";
			 } 
			 
			 
			 if(!bean.getDateFilter().equals("1") && bean.getReqCode().equals(""))
			 {
				   if(bean.getDateFilter().equals("O"))
				   {
					   query +=" AND ADVT_START_DATE = TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
				   }
				   
				   if(bean.getDateFilter().equals("OB"))
				   {
					   query +=" AND ADVT_START_DATE <= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
				   }
				   
				   if(bean.getDateFilter().equals("OA"))
				   {
					   query +=" AND ADVT_START_DATE >= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
				   }
				   
				   if(bean.getDateFilter().equals("B"))
				   {
					   query +=" AND ADVT_START_DATE < TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
				   }
				   
				   if(bean.getDateFilter().equals("A"))
				   {
					   query +=" AND ADVT_START_DATE > TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY')" ; 
				   }
				   
				   if(bean.getDateFilter().equals("F"))
				   {
					   query +=" AND ADVT_START_DATE >= TO_DATE('"+bean.getFrmDate()+"','DD-MM-YYYY') " ; 
					  
					   if(!(bean.getToDate().equals("")||bean.getToDate().equals("dd-mm-yyyy"))){
						   query +=" AND ADVT_START_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ;
						   }
				   
				   }
			 }    
			// ============  end of filter =========== 
			// ============  start of advance ===========
			 
			 if(!bean.getNumOfVacAdvCom().equals("1"))
			   {
				   query +=" AND NVL(ADVT_NOOFVAC,0) "+getAdvanceFilter(bean.getNumOfVacAdvCom())+" "+bean.getNumOfVacAdvTxt() ; 
			   }
			  
			// ============  end of advance ===========
			 
				// ============ for selected requistion===========  
			  
			  if(!bean.getSelectedReq().equals("") && !bean.getSelectedReq().equals("null"))
			  {
				  query+= " AND  HRMS_REC_ADVT_HDR.REQS_CODE IN ("+bean.getSelectedReq()+")";
			  }
			  query+="GROUP BY HRMS_REC_ADVT_HDR.ADVT_CODE, HRMS_REC_ADVT_HDR.REQS_CODE, ADVT_NOOFVAC, ADVT_POSITION,REQS_NAME,RANK_NAME ,ADVT_MODE,ADVT_NAME,ADVT_START_DATE HAVING 1=1 "; 
			  // FOR ADVANCE FILTER FOR GROUP FUNCTION
			  
				 if(!bean.getOnlineRespAdvCom().equals("1"))
				   {
					   query +=" AND SUM(NVL(ADVT_RESPONSES, 0)) "+getAdvanceFilter(bean.getOnlineRespAdvCom())+" "+bean.getOnlineRespAdvTxt() ; 
				   }
				 
				 if(!bean.getCostAdvCom().equals("1"))
				   {
					   query +=" AND SUM(NVL(ADVT_COST, 0)) "+getAdvanceFilter(bean.getCostAdvCom())+" "+bean.getCostAdvTxt(); 
				   } 
			 
			// ============  start of sorting ===========
			 if(!bean.getSortBy().equals("1") || ! bean.getThenBy1().equals("1") || ! bean.getThenBy2().equals("1"))
			  {
			      query+=" ORDER BY ";
			  }
			  if(!bean.getSortBy().equals("1"))
			  {
				  query+= getSortVal(bean.getSortBy()) +" "+getSortOrder(bean.getSortByOrder());
				  if(!bean.getThenBy1().equals("1") ||!bean.getThenBy2().equals("1") ){ 
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
	{  String sql="";
		if(Status.equals("R")){
			sql=" REQS_NAME ";
		}  
		if(Status.equals("A"))
		{
			sql=" ADVT_START_DATE  ";
		}
		if(Status.equals("C")){
			sql=" COST  ";
		}
		if(Status.equals("O")){
			sql=" RESPONSE ";
		}   
		
		if(Status.equals("N")){
			sql=" NVL(ADVT_NOOFVAC,0) ";
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
	
	public void callBack(AdvertiseReport bean)
	{
		if(bean.getReqCodeChk().equals("on")){
			bean.setReqCodeChk("checked");
		}  
		 
		if(bean.getPositionChk().equals("on")){
			bean.setPositionChk("checked");
		} 
		
		if(bean.getNumOfVacChk().equals("on")){
			bean.setNumOfVacChk("checked");
		}
		if(bean.getModeOfAdvChk().equals("on")){
			bean.setModeOfAdvChk("checked");
		}
		if(bean.getAgencuNameChk().equals("on")){
			bean.setAgencuNameChk("checked");
		}
		if(bean.getAdvDateChk().equals("on")){
			bean.setAdvDateChk("checked");
		}
		
		if(bean.getAdvCostChk().equals("on")){
			bean.setAdvCostChk("checked");
		}
		if(bean.getOnlineResChk().equals("on")){
			bean.setOnlineResChk("checked");
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
	
	public void input(AdvertiseReport bean)
	{ 
			bean.setReqCodeChk("checked");   
			bean.setPositionChk("checked"); 
			bean.setNumOfVacChk("checked"); 
			bean.setModeOfAdvChk("checked");  
			bean.setAgencuNameChk("checked");  
			bean.setAdvDateChk("checked"); 
			bean.setAdvCostChk("checked"); 
			bean.setOnlineResChk("checked");  
	}
	
	public void callPreviousRecord(AdvertiseReport bean)
	{
	  String sql =" SELECT ADVREP_CODE, ADVREP_NAME FROM HRMS_REC_ADVREP_FILTERS WHERE ADVREP_USEREMPID="+bean.getUserEmpId()+" ORDER BY UPPER(ADVREP_NAME) ";
	  Object [][] data = getSqlModel().getSingleResult(sql);
		  if(data!=null && data.length>0)
		  {  LinkedHashMap map = new LinkedHashMap();
			  for (int i = 0; i < data.length; i++) {
				  map.put(data[i][0], data[i][1]);
			}
			  bean.setMap(map); 
		  }
		 
	}
	public boolean callSave(AdvertiseReport bean)
	{
		if(saveFiletr(bean) && saveSort(bean) && saveColumnDef(bean) && saveAdvance(bean))
		{
			return true;
		}else{
		return false;
		}
	}
	
	public boolean saveFiletr(AdvertiseReport bean)
	{   
		
		Object [][] filterObj = null;
		if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
			filterObj= new Object [1][12];  
		}else{
			filterObj= new Object [1][13];   
		}
		filterObj [0][0]=bean.getReqCode().equals("")?null : bean.getReqCode();
		filterObj [0][1]=bean.getPositionId().equals("")?null :bean.getPositionId();
		 if(!bean.getDateFilter().equals("1") && bean.getReqCode().equals(""))
		 {
			   if(bean.getDateFilter().equals("O"))
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
	     filterObj [0][9]= bean.getModeOfAdvertise();
	     filterObj [0][10]= bean.getSelectedReq().equals("")? null : bean.getSelectedReq(); 
	     filterObj [0][11]= bean.getRadioStatus().equals("")? null : bean.getRadioStatus(); 
  
	   boolean flag= false;
	   if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
		   flag = getSqlModel().singleExecute(getQuery(1),filterObj);
	   }else{
		   filterObj[0][12]=bean.getSearchSetting();
		   flag =  getSqlModel().singleExecute(getQuery(6),filterObj);
	   }
		 
		return flag;
	}
	
	public boolean saveSort(AdvertiseReport bean)
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
			 flag =getSqlModel().singleExecute(getQuery(7),sortObj); 
		 }
		System.out.println("flag======sort========= "+flag);
	    return flag;
	}
	
	public boolean saveColumnDef(AdvertiseReport bean)
	{   
		Object [][] colObj = null;
		  if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
			  colObj = new Object[1][8];
		  }else{
			  colObj = new Object[1][9];
		  }  
		 
		colObj[0][0]=bean.getReqCodeChk().equals("on")?"Y":"N";
		colObj[0][1]=bean.getNumOfVacChk().equals("on")?"Y":"N";
		colObj[0][2]=bean.getModeOfAdvChk().equals("on")?"Y":"N";
		colObj[0][3]=bean.getAgencuNameChk().equals("on")?"Y":"N";
		colObj[0][4]=bean.getAdvDateChk().equals("on")?"Y":"N";
		colObj[0][5]=bean.getAdvCostChk().equals("on")?"Y":"N";  
		colObj[0][6]=bean.getOnlineResChk().equals("on")?"Y":"N";
		colObj[0][7]=bean.getPositionChk().equals("on")?"Y":"N";
		 
		boolean flag= false; 
		 if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
			 flag = getSqlModel().singleExecute(getQuery(3),colObj); 
		 }else{
			 colObj[0][8]=bean.getSearchSetting();
			 flag = getSqlModel().singleExecute(getQuery(8),colObj); 
		 }
		System.out.println("flag======column========= "+flag);
	    return flag;
	}
	
	public boolean saveAdvance(AdvertiseReport bean)
	{  
		Object [][] colAdv =null;
		 if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
		   colAdv = new Object[1][6];
		 }else{
			 colAdv = new Object[1][7];
		 } 
		colAdv[0][0]=bean.getNumOfVacAdvCom().trim();
		colAdv[0][1]=bean.getNumOfVacAdvTxt().trim().equals("")?null :bean.getNumOfVacAdvTxt();
		colAdv[0][2]=bean.getOnlineRespAdvCom().trim();
		colAdv[0][3]=bean.getOnlineRespAdvTxt().trim().equals("")?null :bean.getOnlineRespAdvTxt();
		colAdv[0][4]=bean.getCostAdvCom().trim();
		colAdv[0][5]=bean.getCostAdvTxt().trim().equals("")?null :bean.getCostAdvTxt();
		 boolean flag= false; 
		 if(bean.getSearchSetting().equals("B")|| (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){
			 flag = getSqlModel().singleExecute(getQuery(4),colAdv); 
		 }else{
			 colAdv[0][6]=bean.getSearchSetting();
			 flag = getSqlModel().singleExecute(getQuery(9),colAdv); 
		 }
		System.out.println("flag======advance========= "+flag);
	    return flag;
	} 
	
	public boolean callUpdate(AdvertiseReport bean)
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
	public void callFilterSavedData(AdvertiseReport bean)
	{
 String sqlFilter =" SELECT REQS_NAME,ADVREP_REQ_CODE, NVL(RANK_NAME,' '),ADVREP_POSITION_CODE, ADVREP_DATEOPTION," 
		 		+" TO_CHAR(ADVREP_REQDATE_FROM,'DD-MM-YYYY'), ADVREP_REQSTATUS, ADVREP_REPOPTION, ADVREP_NAME, " 
		 		+" TO_CHAR(ADVREP_REQDATE_TO,'DD-MM-YYYY'),ADVREP_MODE_ADVERTISE, ADVREP_SELECTED_REQ ,ADVREP_RADIO_OPTION " 
 		        +" FROM HRMS_REC_ADVREP_FILTERS "
 		        +" LEFT JOIN HRMS_REC_REQS_HDR ON (ADVREP_REQ_CODE = REQS_CODE) "
 		        +" LEFT JOIN HRMS_RANK  ON (ADVREP_POSITION_CODE= RANK_ID ) "
 		        +" WHERE ADVREP_CODE ="+bean.getSearchSetting(); 
		Object [][] data = getSqlModel().getSingleResult(sqlFilter);
		
		if(data!= null && data.length >0)
		{
		bean.setReqname(checkNull(String.valueOf(data[0][0])));
		bean.setReqCode(checkNull(String.valueOf(data[0][1])));	  
		bean.setPosition(checkNull(String.valueOf(data[0][2])));
		bean.setPositionId(checkNull(String.valueOf(data[0][3]))); 
		bean.setDateFilter(checkNull(String.valueOf(data[0][4])));
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
		bean.setModeOfAdvertise(checkNull(String.valueOf(data[0][10])));  
		bean.setSelectedReq(checkNull(String.valueOf(data[0][11]))); 
		if(String.valueOf(data[0][12]).equals("R")){
			bean.setRadioReq("checked");
		}
		if(String.valueOf(data[0][12]).equals("P")){
			bean.setRadioPosition("checked");
		}
		 if(!bean.getSelectedReq().equals("") &&!bean.getSelectedReq().equals("null") )
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
	public void callSortSavedData(AdvertiseReport bean)
	{
		String sqlSort =" SELECT  SORT_BY, SORT_BY_ORDER,  SORT_THENBY, SORT_THENBY_ORDER, SORT_THENBY1, "
					 +" SORT_THENBY1_ORDER FROM HRMS_REC_ADVREP_SORT WHERE ADVREP_CODE ="+bean.getSearchSetting();
     Object [][] sortData = getSqlModel().getSingleResult(sqlSort);
	 if(sortData!=null && sortData.length>0){
		bean.setSortBy(String.valueOf(sortData[0][0]));
		if(String.valueOf(sortData[0][1]).equals("A")){
			bean.setSortByAsc("checked");
		}else{
			bean.setSortByDsc("checked");
		}
		
		bean.setThenBy1(String.valueOf(sortData[0][2]));
		if(String.valueOf(sortData[0][3]).equals("A")){
			bean.setThenByOrder1Asc("checked");
		}else{
			bean.setThenByOrder1Dsc("checked");
		}
		
		bean.setThenBy2(String.valueOf(sortData[0][4]));
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
	
	public void callColumnSavedData(AdvertiseReport bean)
	{
	String sqlColumn =" SELECT COL_REQNAME, COL_NUMVAC, COL_MODEADVERTISE, COL_AGENCYNAME, COL_ADVDATE, "
					+" COL_ADVCOST, COL_ONLINERESP,COL_POSITION FROM HRMS_REC_ADVREP_COLDEF WHERE ADVREP_CODE ="+bean.getSearchSetting();
	 	 Object [][] data = getSqlModel().getSingleResult(sqlColumn);
	 	 
	 	 if(data!=null && data.length>0)
	 	 { 
	 		if(String.valueOf(data[0][0]).equals("Y")){
				bean.setReqCodeChk("checked");
			}  
	 		if(String.valueOf(data[0][1]).equals("Y")){
	 			bean.setNumOfVacChk("checked");
			}  
	 		if(String.valueOf(data[0][2]).equals("Y")){
	 			bean.setModeOfAdvChk("checked");
			}  
	 		if(String.valueOf(data[0][3]).equals("Y")){
	 			bean.setAgencuNameChk("checked"); 
			}  
	 		if(String.valueOf(data[0][4]).equals("Y")){
	 			bean.setAdvDateChk("checked");
			}   
	 		if(String.valueOf(data[0][5]).equals("Y")){
	 			bean.setAdvCostChk("checked");
			}  
	 		if(String.valueOf(data[0][6]).equals("Y")){
	 			bean.setOnlineResChk("checked");
			} 
	 		if(String.valueOf(data[0][7]).equals("Y")){
	 			bean.setPositionChk("checked");
			} 
	 	 }
	}
	
	/**
	 * set the data of advance filter 
	 * @param bean
	 */
	
	public void callAdvanceSavedData(AdvertiseReport bean)
	{
	  String sqlAdvance =" SELECT ADV_NUMVAC_OPTION, ADV_NUMVAC,ADV_ONLINERESP_OPTION, ADV_ONLINERESP, ADV_COST_OPTION, "
		  				+" ADV_COST FROM HRMS_REC_ADVREP_ADV WHERE ADVREP_CODE = "+bean.getSearchSetting();
	  Object [][] data = getSqlModel().getSingleResult(sqlAdvance ); 
	  
	 	 if(data!=null && data.length>0)
	 	 { 
	 		 bean.setNumOfVacAdvCom(String.valueOf(data[0][0]));
	 		 bean.setNumOfVacAdvTxt(checkNull(String.valueOf(data[0][1]))); 
	 		 bean.setOnlineRespAdvCom(String.valueOf(data[0][2]));
	 		 bean.setOnlineRespAdvTxt(checkNull(String.valueOf(data[0][3])));
	 		 bean.setCostAdvCom(String.valueOf(data[0][4]));
	 		 bean.setCostAdvTxt(checkNull(String.valueOf(data[0][5])));  
	 	 }
	  
	}
	
	/**
	 * set the data of reqution textarea 
	 * @param bean
	 */
	
	public void callSavedReqData(AdvertiseReport bean)
	{
		  String sqlAdvance =" SELECT ADVREP_SELECTED_REQ FROM HRMS_REC_VACREP_REQS WHERE VACREP_CODE ="+bean.getSearchSetting();
         Object [][] data = getSqlModel().getSingleResult(sqlAdvance );

		if(data!=null && data.length>0)
		{     bean.setSelectedReq(String.valueOf(data[0][0]));
			String reqSql ="SELECT  REQS_NAME FROM HRMS_REC_REQS_HDR WHERE REQS_CODE IN("+String.valueOf(data[0][0])+")";
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
			} 
		}
	}
	public String checkNull(String result) {
		 
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	} // end of checkNull
	
	public Object [][] checkDuplicate (AdvertiseReport bean)
	{
		String sql ="  SELECT  ADVREP_NAME ,ADVREP_CODE  FROM HRMS_REC_ADVREP_FILTERS WHERE UPPER(ADVREP_NAME)='"+bean.getSettingName().toUpperCase()+"' AND ADVREP_USEREMPID="+bean.getUserEmpId();
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
