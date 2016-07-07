package org.paradyne.model.PAS;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.lib.ModelBase;
import org.paradyne.bean.PAS.AppraisalPhaseConfig;



/**
 * @author Hemant Nagam
 * date - 09-JAN-2009
 *
 */
public class AppraisalPhaseConfigModel extends ModelBase{
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
		
	/**
	 * This method gathers all existing appraisal calendars 
	 * @param bean
	 */
	public void getAppraisalCalendar(AppraisalPhaseConfig bean,HttpServletRequest request ){
		
		
		
		Object repData[][] = getSqlModel().getSingleResult(getQuery(7));
		//Object [][] repData = getSqlModel().getSingleResult(query);
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
	 int pg1=0;
	int PageNo1=1;//----------
	REC_TOTAL = repData.length;
	int no_of_pages=Math.round(REC_TOTAL/20);
	//PageNo = Integer.parseInt(bean.getMyPage());//-----------
	double row = (double)repData.length/20.0;
   
      java.math.BigDecimal value1 = new java.math.BigDecimal(row);
     
      int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
    
	
	ArrayList<Object> list=new ArrayList<Object>();
	request.setAttribute("abc", rowCount1);

	//PageNo
	if(String.valueOf(bean.getMyPage()).equals("null")||String.valueOf(bean.getMyPage()).equals(null)||String.valueOf(bean.getMyPage()).equals(" "))
	{
		PageNo1=1;
		From_TOT=0;
		  To_TOT=20;

		  if(To_TOT >repData.length){
			  To_TOT=repData.length;
		  }
			logger.info("-----------To_TOTAL----null-----"+To_TOT);
			bean.setMyPage("1");
	}
	
	
	else{
			
		  pg1=	Integer.parseInt(bean.getMyPage());
		  PageNo1=pg1;
		  
		  if(pg1 ==1){
			 From_TOT=0;
			 To_TOT=20;
		  }
		  else{
			//  From_TOTAL=To_TOTAL+1;
				 To_TOT=To_TOT*pg1;
				 From_TOT=(To_TOT-20);
		  }
		  if(To_TOT >repData.length){
			  To_TOT=repData.length;
		  }
	  }
	request.setAttribute("xyz", PageNo1);
	  logger.info("------------from total--------"+From_TOT);
	  logger.info("----------to total----------"+To_TOT);
	  for(int i=From_TOT;i<To_TOT;i++){
                 //setting 
		  	AppraisalPhaseConfig bean1 = new AppraisalPhaseConfig();	
		  	
			bean1.setCalId(checkNull(String.valueOf(repData[i][3])));
			bean1.setCalCode(checkNull(String.valueOf(repData[i][0])));
			bean1.setStartdate(checkNull(String.valueOf(repData[i][1])));
			bean1.setEndDate(checkNull(String.valueOf(repData[i][2])));
			
			list.add(bean1);
	  }
	
	
		
	  bean.setCalList(list);
		
		
		
	}
	
	public boolean deleteProcessConfigurations(AppraisalPhaseConfig bean, HttpServletRequest request){
		
		String proConfigList [] = request.getParameterValues("chkDel");
		boolean result = false;
		
		if(proConfigList!=null && proConfigList.length>0){
			
			for(int i=0;i<proConfigList.length;i++){
				
				Object delParam[][] = new Object [1][1];
				delParam[0][0] = proConfigList[i];
				result = getSqlModel().singleExecute(getQuery(4),delParam);
				
			}
			
		}
		return result;
		
		
	}
	
	
	
	/**
	 * This method generates the phase details for the specific calendar
	 * @param bean
	 */
	public void getPhaseConfigDetails(AppraisalPhaseConfig bean, HttpServletRequest request){
		
		Object [] param = new Object [1];
		param [0] = bean.getApprId(); 
		
		
		Object phaseData [][] = getSqlModel().getSingleResult(getQuery(2),param);
		int totalWeightage = 0;
		Object isSelf[] = new Object[phaseData.length];
		
		
		if(phaseData!=null && phaseData.length>0){
			
			ArrayList list  = new ArrayList<AppraisalPhaseConfig>();
			
			
			for(int i=0;i<phaseData.length;i++){
				
				AppraisalPhaseConfig bean1 = new AppraisalPhaseConfig();
				
				if(i==0){//SET APPRAISAL CODE/FROM DATE/TO DATE
					bean.setAppStarted(""+phaseData[i][11]);
					bean.setHApprId(bean.getApprId());
					bean.setApprCode(checkNull(String.valueOf(phaseData[i][7])).trim());
					bean.setFrmDate(checkNull(String.valueOf(phaseData[i][8])).trim());
					bean.setToDate(checkNull(String.valueOf(phaseData[i][9])).trim());
					
				}if(i<phaseData.length){//SET
					System.out.println("PHASE ID :"+checkNull(String.valueOf(phaseData[i][0])));
					if(checkNull(String.valueOf(phaseData[i][3])).equals("Active")){//ADD TO PHASE TOTAL ONLY WHEN STATUS=A
						totalWeightage += Integer.parseInt(checkNull(String.valueOf(phaseData[i][5])).trim());
					}if(checkNull(String.valueOf(phaseData[i][4])).equals("Y")){
						request.setAttribute("rating","checked" );
					}
					bean1.setSNo(""+(i+1));
					bean1.setPhaseId(checkNull(String.valueOf(phaseData[i][0])).trim());
					bean1.setHPhase(checkNull(String.valueOf(phaseData[i][1])).trim());
					bean1.setHOrder(checkNull(String.valueOf(phaseData[i][2])).trim());
					bean1.setHWeightage(checkNull(String.valueOf(phaseData[i][5])).trim());
					bean1.setHStatus(checkNull(String.valueOf(phaseData[i][3])).trim());
					bean1.setHDescr(checkNull(String.valueOf(phaseData[i][6])));
					bean1.setHRating(checkNull(String.valueOf(phaseData[i][4])));
					bean1.setHQueWeight(checkNull(String.valueOf(phaseData[i][12])));
					isSelf[i] = phaseData[i][10];
										
				}else if(i==phaseData.length){//FOR GRAND TOTAL
					
					/*bean1.setHPhase(" ");
					bean1.setHPhase(" ");
					bean1.setHOrder(" ");
					bean1.setHWeightage(""+weightage);
					bean1.setHStatus(" ");
					bean1.setHDescr(" ");*/
					
				}
				
				list.add(bean1);
			}
			
			bean.setPhaseList(list);
		}
		System.out.println("isSelf>>>>"+isSelf.length);
		System.out.println("phaseData>>>>"+phaseData.length);
		request.setAttribute("gTotal",totalWeightage );
		request.setAttribute("isSelf", isSelf);
	}
	
	
	public void  addPhase(AppraisalPhaseConfig bean, HttpServletRequest request){
		
		////PHASE DETAILS TO BE ADDED
		String apprCode = bean.getApprCode();
		String frmDate = bean.getFrmDate();
		String toDate = bean.getToDate();
		String phase = bean.getPhase();
		String order = bean.getPOrder();
		String status = bean.getPStatus();
		String weightage = bean.getPWeightage();
		String descr = bean.getPDescr();
		String ratingFlag = bean.getPhaseRating(); 
		String rating = "";
		String ratingCheck = request.getParameter("rating");
		String queweight = bean.getPQueWeight();
		//System.out.print("---AAAAAAA---question weight checked - "+queweight);
		//System.out.println("ratingFlag--->"+ratingFlag);
		
		if(ratingFlag.equals("true") || ratingCheck.equals("checked")){
			rating = "on";
		}
		
		System.out.println("rating---->"+rating);
		
		System.out.println(rating.equals("on")?weightage:"100");
		
		////EXISTING PHASE DETAILS
		String sNo[] = request.getParameterValues("sNo");
		String phaseId[] = request.getParameterValues("phaseId");//
		String hPhase[] = request.getParameterValues("hPhase");
		String hRself[] = request.getParameterValues("hRself");
		String hOrder[] = request.getParameterValues("hOrder");
		String hRating[] = request.getParameterValues("hRating");
		String hWeightage[] = request.getParameterValues("hWeightage");
		String hStatus[] = request.getParameterValues("hStatus");
		String hDescr[] = request.getParameterValues("hDescr");
		String sno = request.getParameter("hSno");
		String apprId = bean.getApprId();
		String hQueWeight[] = request.getParameterValues("hQueWeight");
		String newHrSelf[] = null;
		
		if(hRself!=null){
			newHrSelf = new String[hRself.length+1];
		for(int i=0;i<newHrSelf.length;i++){
			
			if(i<newHrSelf.length-1){
				newHrSelf[i] = hRself[i];
			}else{
				newHrSelf[i] = "N";
			}
			
			
		}
		}	
		
		ArrayList list = new ArrayList<AppraisalPhaseConfig>();
		int totalWeightage = 0;
		//System.out.println("phaseId.length--->"+phaseId.length);
		//PHASE LIST ALREADY EXISTS
		if(phaseId!=null && phaseId.length>0){
			
			for(int i=0;i<=phaseId.length;i++){
				
				AppraisalPhaseConfig bean1 = new AppraisalPhaseConfig();
				
				if(i==phaseId.length ){//ADD NEW PHASE RECORD AT THE END, CHECK FOR PHASE UPDATE
					
					if((sno==null || sno.equals(""))){
						bean1.setSNo(""+(i+1));
						//bean1.setPhaseId(phaseId[i]);
						bean1.setHPhase(phase);
						bean1.setHOrder(order);
						bean1.setHWeightage(""+Integer.parseInt(rating.equals("on")?weightage:"100"));
						bean1.setHStatus(status.equals("A")?"Active":"De-Active");
						bean1.setHDescr(descr);
						bean1.setHRating(rating.equals("on")?"Y":"N");
						if(status.equals("A")){//ADD TO PHASE TOTAL ONLY WHEN STATUS=A
							totalWeightage+=Integer.parseInt(rating.equals("on")?weightage:"100");
						}
						bean1.setHQueWeight(queweight);
						list.add(bean1);
					}
					
				}else{//
					if(!(sno==null || sno.equals(""))&& sNo[i].equals(sno)){//IF PAHSE TO BE UPDATED FOUND
						
						bean1.setSNo(""+(i+1));
						bean1.setPhaseId(phaseId[i]);
						bean1.setHPhase(bean.getPhase());
						bean1.setHOrder(bean.getPOrder());
						bean1.setHWeightage(""+Integer.parseInt(rating.equals("on")?weightage:"100"));
						bean1.setHStatus(status.equals("A")?"Active":"De-Active");
						bean1.setHDescr(bean.getPDescr());
						bean1.setHRating(rating.equals("on")?"Y":"N");
						if(status.equals("A")){//ADD TO PHASE TOTAL ONLY WHEN STATUS=A
							totalWeightage+=Integer.parseInt(rating.equals("on")?weightage:"100");
						}
						bean1.setHQueWeight(bean.getPQueWeight());
					}else{
						
						bean1.setSNo(""+(i+1));
						bean1.setPhaseId(phaseId[i]);
						bean1.setHPhase(hPhase[i]);
						bean1.setHOrder(hOrder[i]);
						bean1.setHWeightage(""+Integer.parseInt(hWeightage[i]));
						bean1.setHStatus(hStatus[i]);
						bean1.setHDescr(hDescr[i]);
						bean1.setHRating(hRating[i]);
						if(hStatus[i].equals("Active")){//ADD TO PHASE TOTAL ONLY WHEN STATUS=A
							totalWeightage+=Integer.parseInt(hWeightage[i]);
						}
						bean1.setHQueWeight(hQueWeight[i]);
					}
					
					list.add(bean1);
					
				}
							
			}
			request.setAttribute("isSelf", newHrSelf);
	}else{//PHASE LIST IS EMPTY OR FIRST PHASE TO BE POPULATED TO THE LIST
		
		AppraisalPhaseConfig bean1 = new AppraisalPhaseConfig();
		
		bean1.setSNo("1");
		bean1.setHPhase(phase);
		bean1.setHOrder(order);
		bean1.setHWeightage(""+Integer.parseInt(rating.equals("on")?weightage:"100"));
		bean1.setHStatus(status.equals("A")?"Active":"De-Active");
		bean1.setHDescr(descr);
		bean1.setHRating(rating.equals("on")?"Y":"N");
		if(status.equals("A")){//ADD TO PHASE TOTAL ONLY WHEN STATUS=A
			totalWeightage+=(int)Integer.parseInt(rating.equals("on")?weightage:"100");
		}
		bean1.setHQueWeight(queweight);
		list.add(bean1); 
		Object param[] = new Object[1];
		param[0] = "N";
		request.setAttribute("isSelf", param);
	}
			
		bean.setPhaseList(list);
		
		request.setAttribute("gTotal",totalWeightage );
		if(rating.equals("on")){
			request.setAttribute("rating","checked" );
		}
		try{
		System.out.println("hRself>>>>>>>>>>>>>>>>>>>>>>>>"+hRself.length);
		}catch(Exception e){
			
		}
		
	}
	
	
	public boolean removePhase(AppraisalPhaseConfig bean, HttpServletRequest request){
		
		
		String delPhase = (String) request.getParameter("hSno");
		String delSno = (String) request.getParameter("removeSno");
		
		String sNo[] = request.getParameterValues("sNo");
		String phaseId[] = request.getParameterValues("phaseId");//
		String hPhase[] = request.getParameterValues("hPhase");
		String hRself[] = request.getParameterValues("hRself");
		String hOrder[] = request.getParameterValues("hOrder");
		String hRating[] = request.getParameterValues("hRating");
		String hWeightage[] = request.getParameterValues("hWeightage");
		String hStatus[] = request.getParameterValues("hStatus");
		String hDescr[] = request.getParameterValues("hDescr");
		String chkOpr[] = request.getParameterValues("chkOpr");
		String rating = request.getParameter("rating");
		
		String apprId = bean.getApprId();
		
		ArrayList list = new ArrayList<AppraisalPhaseConfig>();
		long totalWeightage = 0; 
		boolean result = true;
		int count=0;
		
		System.out.println("pRatingCheck-->"+request.getParameter("rating"));
		
			
			if(!delPhase.equals("")){ //CHECK FOR REMOVAL OF PHASES FROM DATABASE
				
				if(sNo!=null && sNo.length>0){
					
					
				for(int i=0;i<sNo.length;i++){
					
						if( !phaseId[i].equals(delPhase)){//IF PHASE TO BE REMOVED DOESNT MATCH THE LIST
							
							AppraisalPhaseConfig bean1 = new AppraisalPhaseConfig();
							//phaseId
							bean1.setSNo(""+(++count));
							bean1.setPhaseId(phaseId[i]);
							bean1.setHPhase(hPhase[i]);
							bean1.setHOrder(hOrder[i]);
							bean1.setHRating(hRating[i]);
							bean1.setHWeightage(""+Integer.parseInt(hWeightage[i]));
							bean1.setHStatus(hStatus[i]);
							bean1.setHDescr(hDescr[i]);
							if(hStatus[i].equals("Active")){
								totalWeightage += Integer.parseInt(hWeightage[i]);
							}
							
							list.add(bean1);
							request.setAttribute("rating",rating );
							
						}else{//IF MATCH OCCURS 
							
							Object delParam[] = new Object [1];
							delParam[0] =  delPhase.trim();
							System.out.println("DELETE PHASE--->"+delPhase);
							Object data [][] = getSqlModel().getSingleResult(getQuery(6),delParam);
							if(data!=null && data.length>0){//IF REFERENCE FOR PHASEID EXISTS
								
								AppraisalPhaseConfig bean1 = new AppraisalPhaseConfig();
								bean1.setSNo(""+(++count));
								bean1.setPhaseId(phaseId[i]);
								bean1.setHPhase(hPhase[i]);
								bean1.setHOrder(hOrder[i]);
								bean1.setHRating(hRating[i]);
								bean1.setHWeightage(""+Integer.parseInt(hWeightage[i]));
								bean1.setHStatus(hStatus[i]);
								bean1.setHDescr(hDescr[i]);
								list.add(bean1);
								if(hStatus[i].equals("Active")){
									totalWeightage += Integer.parseInt(hWeightage[i]);
								}
								result =  false;
							}else{//NO REFERENCES EXISTS
								
								bean.setRemovePhases(bean.getRemovePhases()+delPhase+",");
								
							}
							
						}
				} //for ends				
				
			}
				
				
			}else{//REMOVAL OF STATIC PHASES
				
			if(sNo!=null && sNo.length>0){
				
				
				
				for(int i=0;i<sNo.length;i++){
					
					if( !sNo[i].equals(delSno)){ //IF PHASE TO BE REMOVED DOESNT MATCH THE LIST
						
						AppraisalPhaseConfig bean1 = new AppraisalPhaseConfig();
						//phaseId
						bean1.setSNo(""+(++count));
						bean1.setPhaseId(phaseId[i]);
						bean1.setHPhase(hPhase[i]);
						bean1.setHOrder(hOrder[i]);
						bean1.setHRating(hRating[i]);
						bean1.setHWeightage(""+Integer.parseInt(hWeightage[i]));
						bean1.setHStatus(hStatus[i]);
						bean1.setHDescr(hDescr[i]);
						if(hStatus[i].equals("Active")){
							totalWeightage += Integer.parseInt(hWeightage[i]);
						}
						
						list.add(bean1);
						request.setAttribute("rating",rating );
					}
				
				}//for ends
				
				
			}
			

		}
			
			
		
		
		bean.setPhaseList(list);
		System.out.println("totalWeightage-->"+totalWeightage);
		request.setAttribute("gTotal",totalWeightage);
		request.setAttribute("isSelf", hRself);
		
		return result;
		
	}
	
	
	public boolean removeStaticPhase(AppraisalPhaseConfig bean, HttpServletRequest request){
		
		////EXISTING PHASE DETAILS 
		String sNo[] = request.getParameterValues("sNo");
		String phaseId[] = request.getParameterValues("phaseId");//
		String hPhase[] = request.getParameterValues("hPhase");
		String hRself[] = request.getParameterValues("hRself");
		String hOrder[] = request.getParameterValues("hOrder");
		String hRating[] = request.getParameterValues("hRating");
		String hWeightage[] = request.getParameterValues("hWeightage");
		String hStatus[] = request.getParameterValues("hStatus");
		String hDescr[] = request.getParameterValues("hDescr");
		String chkOpr[] = request.getParameterValues("chkOpr");
		String removeSno = ""+request.getParameter("removeSno");
		String rating = request.getParameter("rating");
		
		String apprId = bean.getApprId();		
		String ratingFlag = bean.getPhaseRating(); 		
		
		
		ArrayList list = new ArrayList<AppraisalPhaseConfig>();
		long totalWeightage = 0; 
		int count=0;
		
		for(int i=0;i<sNo.length;i++){
			
			if(!removeSno.equals(sNo[i])){
				
				AppraisalPhaseConfig bean1 = new AppraisalPhaseConfig();
				bean1.setSNo(""+(++count));
				bean1.setHPhase(hPhase[i]);
				bean1.setHOrder(hOrder[i]);
				bean1.setHRating(hRating[i]);
				bean1.setHWeightage(""+Integer.parseInt(hWeightage[i]));
				bean1.setHStatus(hStatus[i]);
				bean1.setHDescr(hDescr[i]);
				if(hStatus[i].equals("Active")){
					totalWeightage += Integer.parseInt(hWeightage[i]);
				}
				
				list.add(bean1);
				request.setAttribute("rating","checked" );
			}
			
		}
		
		bean.setPhaseList(list);
		request.setAttribute("gTotal", totalWeightage);
		if(ratingFlag.equals("true") || rating.equals("checked")){
			request.setAttribute("rating","checked" );
		}
		request.setAttribute("isSelf", hRself);

		return true;
		
	}
	
	
	
	/**
	 * This method deletes the phase details of a specific calendar 
	 * @param bean
	 * @return
	 */
	public boolean delete(AppraisalPhaseConfig bean){
		
		Object paramDelete[][] = new Object[1][1];
		paramDelete [0][0] = bean.getApprId();
		
		return getSqlModel().singleExecute(getQuery(4),paramDelete);		
		
	}
/////////////////////////////////////////SECTION DETAILS STARTS/////////////////	
	public void saveSectionDetails(String apprId){
		
		Object data[][]= getSqlModel().getSingleResult(getQuery(8),new Object[]{apprId});
		logger.info("data>>>>>"+data.length);
		if(data!=null && data.length>0){//SECTION DTLS EXISTS
			
			//MAX PHASEID FOR APPRID
			Object maxPhaseId[][] = getSqlModel().getSingleResult(getQuery(9),new Object[]{apprId});
			logger.info("maxPhaseId>>>>>"+maxPhaseId[0][0]);
			Object insert[][] = new Object [data.length][7]; 
			for(int i=0;i<data.length;i++){
				
				insert[i][0] = apprId;
				insert[i][1] = data[i][0];
				insert[i][2] =  data[i][1];
				insert[i][3] = maxPhaseId[0][0];
				insert[i][4] = "N";
				insert[i][5] = "N";
				insert[i][6] = "N";
			}
			getSqlModel().singleExecute(getQuery(10),insert);
			
		}
	}
	public void saveSectionDetails(String apprId,String phaseId){
			
			Object data1[][] = getSqlModel().getSingleResult(getQuery(11),new Object[]{apprId,phaseId});
			
			
			logger.info("data>>>>>"+data1.length);
		
			try {
				if(!(data1!=null && data1.length>0)){//SECTION DTLS EXISTS
					
					Object data[][]= getSqlModel().getSingleResult(getQuery(8),new Object[]{apprId});
					Object insert[][] = new Object [data.length][7]; 
					
					for(int i=0;i<data.length;i++){
						
						insert[i][0] = apprId;
						insert[i][1] = data[i][0];
						insert[i][2] =  data[i][1];
						insert[i][3] = phaseId;
						insert[i][4] = "N";
						insert[i][5] = "N";
						insert[i][6] = "N";
					}
					getSqlModel().singleExecute(getQuery(10),insert);
				}//End of if
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("Exception in savesectiondetails(string,string)");
			}
			
			
			
		}
/////////////////////////////////////////SECTION DETAILS ENDS/////////////////	
	
	
	
	public boolean update(AppraisalPhaseConfig bean,HttpServletRequest request){
		System.out.println("UPDATE...");		
		
		String phaseId[] = request.getParameterValues("phaseId");
		String hPhase[] = request.getParameterValues("hPhase");
		String hOrder[] = request.getParameterValues("hOrder");
		String hWeightage[] = request.getParameterValues("hWeightage");
		String hStatus[] = request.getParameterValues("hStatus");
		String hDescr[] = request.getParameterValues("hDescr");	
		String hRating[] = request.getParameterValues("hRating");
		String rIsSelf = request.getParameter("rIsSelf");
		String hRself[] = request.getParameterValues("hRself");
		String hQueWeight[] = request.getParameterValues("hQueWeight");
		//System.out.println("rIsSelf>>>>>>>>>>"+rIsSelf.length());
		//System.out.println("rIsSelf>>>>>"+rIsSelf);
		//System.out.println("hweight -----------------"+hWeightage.length);
		//System.out.println("-------------------AAAAAAAAAAAAAAAAAAAAAAAAAAA------"+hQueWeight.length);
		String apprId = bean.getApprId();
		
		boolean result = false;
		Object paramInsert [][] = null;
		Object paramUpdate [][] = null;
		int count = 0;
		
		
		
		
		if(phaseId!=null && phaseId.length>0){
			
			for(int j=0;j<phaseId.length;j++){
				if(!phaseId[j].equals("") || !phaseId[j].equals("null")){
					count++;//UPDATABLE ROW COUNT
				}
			}
			
			
			for(int i=0;i<phaseId.length;i++){
				System.out.println("i="+i);
				//INSERT THE NEW PHASE ADDED RECENTLY IN THE LIST
				if(phaseId[i].equals("") || phaseId[i].equals("null")){
					
					paramInsert = new Object[1][9];
					paramInsert[0][0] = apprId;
					paramInsert[0][1] = hPhase[i]; //NOT REQUIRED AS IT IS AUTO GENERATED
					paramInsert[0][2] = hOrder[i];
					paramInsert[0][3] = hStatus[i].equals("Active")?"A":"D";
					paramInsert[0][4] = hRating[i];///RATING CHECK BOX VALUES
					paramInsert[0][5] = hWeightage[i];
					paramInsert[0][6] = hDescr[i];
					paramInsert[0][7] = hRself[i];	
					paramInsert[0][8] = hQueWeight[i];
					
					result = getSqlModel().singleExecute(getQuery(1),paramInsert);
					if(hStatus[i].equals("Active")){////SECTION DTLS
						saveSectionDetails(apprId);
					}
					
					
				}else{//UPDATE THE EXISTING PHASE
					//System.out.println(" Phase:"+hPhase[i]+" priori :"+hPriority[i]+" status: "+hStatus[i]+" weightage"+hWeightage[i]+" hDesc:"+hDesc[i]);
					
					paramUpdate  = new Object[1][10];
					
					paramUpdate[0][0] = hPhase[i]; 
					paramUpdate[0][1] = hOrder[i];
					paramUpdate[0][2] = hStatus[i].equals("Active")?"A":"D";
					paramUpdate[0][3] = hRating[i];///RATING CHECK BOX VALUES
					paramUpdate[0][4] = hWeightage[i];
					paramUpdate[0][5] = hDescr[i];
					paramUpdate[0][6] = hRself[i];	
					paramUpdate[0][7] = hQueWeight[i];
					paramUpdate[0][8] = apprId;
					paramUpdate[0][9] = phaseId[i];
					
					
					result = getSqlModel().singleExecute(getQuery(3),paramUpdate);
					if(hStatus[i].equals("Active")){////SECTION DTLS
						saveSectionDetails(apprId,phaseId[i]);
					}
					
				}
				
			}
					
			
		}if(!bean.getRemovePhases().equals("")){
			
			String phaseList[] = bean.getRemovePhases().split(",");			
			Object delParam [][] = new Object [phaseList.length][1];
			
			for(int i=0;i<delParam .length;i++){
				delParam [i][0] =  phaseList[i];
			}
			
			result = getSqlModel().singleExecute(getQuery(5),delParam);
			
		}
		
		return result;
	}
	
	public boolean save(AppraisalPhaseConfig bean,HttpServletRequest request){
		System.out.println("SAVE.....");
		
		String phaseId[] = request.getParameterValues("phaseId");
		String hPhase[] = request.getParameterValues("hPhase");
		String hOrder[] = request.getParameterValues("hOrder");
		String hWeightage[] = request.getParameterValues("hWeightage");
		String hStatus[] = request.getParameterValues("hStatus");
		String hDescr[] = request.getParameterValues("hDescr");		
		String hRating[] = request.getParameterValues("hRating");
		String rIsSelf = request.getParameter("rIsSelf");
		String hRself[] = request.getParameterValues("hRself");
		String apprId = bean.getApprId();
		String hQueWeight[] = request.getParameterValues("hQueWeight");
		System.out.println("****Phase length"+phaseId.length);
		
		
		System.out.println("****IS SELF"+rIsSelf);
		Object param [][] = null;
		boolean result = false;
		
		if(phaseId!=null && phaseId.length>0){
			
			
			
			for(int i=0;i<phaseId.length;i++){
				
				param  = new Object[1][9];
				//System.out.println(" Phase:"+hPhase[i]+" priori :"+hPriority[i]+" status: "+hStatus[i]+" weightage"+hWeightage[i]+" hDesc:"+hDesc[i]);
				
				//A priori :1 status: 1 weightage1 hDesc:ddfdf
				
				param[0][0] = apprId;
				param[0][1] = hPhase[i]; 
				param[0][2] = hOrder[i];
				param[0][3] = hStatus[i].equals("Active")?"A":"D";
				param[0][4] = hRating[i];///RATING CHECK BOX VALUES
				param[0][5] = hWeightage[i];
				param[0][6] = hDescr[i];
				param[0][7] = hRself[i];
				param[0][8] = hQueWeight[i];
				result = getSqlModel().singleExecute(getQuery(1),param);
				
			}
			
			
			//getSqlModel().getSingleResult("SELECT SYSDATE FROM DUAL");
			
			
		}
		
		return result;
	}
	
public String checkNull(String result) {
		
		if (result == null || result.equals("null") || result.equals(" ")) {
			return "";
		} else {
			return result;
		}
	}
	
	
}
