/**
 * 
 */
package org.paradyne.model.recruitment;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Recruitment.Advertisement;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author AA0417
 *
 */
public class AdvertisementModel extends ModelBase {
	/**
	 * method for setting the number of vaccanies
	 * @param bean
	 */
	
	public void callVaccany(Advertisement bean)
	{
		String sql ="SELECT NVL(SUM(VACAN_NUMBERS),0)  FROM HRMS_REC_REQS_VACDTL 	WHERE REQS_CODE ="+bean.getReqCode();
		Object [][] data = getSqlModel().getSingleResult(sql);
		if(data!=null && data.length >0)
		{
			bean.setNoOfVaccany(""+data [0][0]); 
		}
	}
	/**
	 * delete the record from the list.
	 * @param bean
	 * @param request
	 */
	
	public void deleteDetail(Advertisement bean,HttpServletRequest request)
	{
		 Object []  advertiseMode = request.getParameterValues("advertiseMode");
		 Object []  advertiseModeName = request.getParameterValues("advertiseModeName"); 
		 Object []  advertiseStartDate = request.getParameterValues("advertiseStartDate");
		 Object []  advertiseEndDate = request.getParameterValues("advertiseEndDate");
		 Object []  advertiseDetails = request.getParameterValues("advertiseDetails"); 
		 Object []  advertiseCost = request.getParameterValues("advertiseCost");
		 Object []  advertiseResponse = request.getParameterValues("advertiseResponse");
		 Object []  hidAdverChkBox = request.getParameterValues("hidAdverChkBox"); 
		 
		 if(advertiseMode != null && advertiseMode.length >0)
		 { 
			  ArrayList  list = new ArrayList();
			 for(int i=0;i<advertiseMode.length ;i++)
			 {  
			     if(!hidAdverChkBox[i].equals("Y"))
				    { 
					     Advertisement bean1 = new Advertisement(); 
						 bean1.setAdvertiseMode(""+advertiseMode[i]);
						 bean1.setAdvertiseModeName(""+advertiseModeName[i]);
						 bean1.setAdvertiseStartDate(""+advertiseStartDate[i]); 
						 bean1.setAdvertiseEndDate(""+advertiseEndDate[i]); 
						 bean1.setAdvertiseDetails(""+advertiseDetails[i]);
						 bean1.setAdvertiseCost(""+advertiseCost[i]); 
						 bean1.setAdvertiseResponse(""+advertiseResponse[i]); 
						 list.add(bean1); 
				   }
			 }
			 bean.setAdvertiseList(list); 
		 } 
	}

	/**
	 * display the advertise details.
	 * @param bean
	 * @param request
	 */
	public void advertiseDetail(Advertisement bean,HttpServletRequest request)
	{
		 Object []  advertiseMode = request.getParameterValues("advertiseMode");
		 Object []  advertiseModeName = request.getParameterValues("advertiseModeName"); 
		 Object []  advertiseStartDate = request.getParameterValues("advertiseStartDate");
		 Object []  advertiseEndDate = request.getParameterValues("advertiseEndDate");
		 Object []  advertiseDetails = request.getParameterValues("advertiseDetails"); 
		 Object []  advertiseCost = request.getParameterValues("advertiseCost");
		 Object []  advertiseResponse = request.getParameterValues("advertiseResponse");
		 Object []  advertiseModeText = request.getParameterValues("advertiseModeText");
		 Object []  hidAdverChkBox = request.getParameterValues("hidAdverChkBox"); 
		 if(advertiseMode != null && advertiseMode.length >0)
		 { 
			  ArrayList  list = new ArrayList();
			 for(int i=0;i<advertiseMode.length ;i++)
			 {    
				 
				     Advertisement bean1 = new Advertisement(); 
					 if(!(((""+advertiseMode[i]).equals("0") ||(""+advertiseMode[i]).equals("")) 
							 && ((""+advertiseModeName[i]).trim().equals("")) && ((""+advertiseCost[i]).equals("0")||(""+advertiseCost[i]).equals("")))){
					 bean1.setAdvertiseMode(""+advertiseMode[i]);
					 bean1.setAdvertiseModeName(""+advertiseModeName[i]);
					 bean1.setAdvertiseStartDate(checkNull(""+advertiseStartDate[i])); 
					 bean1.setAdvertiseEndDate(checkNull(""+advertiseEndDate[i])); 
					 bean1.setAdvertiseDetails(checkNull(""+advertiseDetails[i]));
					 bean1.setAdvertiseCost(""+advertiseCost[i]); 
					 bean1.setAdvertiseResponse(checkNull(""+advertiseResponse[i]));  
					 
					 if((""+advertiseMode[i]).equals("N")){
					 bean1.setAdvertiseModeText("News Paper");  
					 }
					 if((""+advertiseMode[i]).equals("T")){
						 bean1.setAdvertiseModeText("TV Media");  
					 }
					 if((""+advertiseMode[i]).equals("W")){
						 
						 bean1.setAdvertiseModeText("Website");  
					 }
					 if((""+advertiseMode[i]).equals("O")){
						 bean1.setAdvertiseModeText("Other");  
					 } 
					 list.add(bean1); 
					 }
			 }
			 bean.setAdvertiseList(list); 
		 } 
	}
	
	/**
	 * save the advertise related data.
	 * @param bean
	 * @param request
	 * @return
	 */
	public boolean saveAdvertiseDtl(Advertisement bean,HttpServletRequest request)
	{
		 Object []  advertiseMode = request.getParameterValues("advertiseMode");
		 Object []  advertiseModeName = request.getParameterValues("advertiseModeName"); 
		 Object []  advertiseStartDate = request.getParameterValues("advertiseStartDate");
		 Object []  advertiseEndDate = request.getParameterValues("advertiseEndDate");
		 Object []  advertiseDetails = request.getParameterValues("advertiseDetails"); 
		 Object []  advertiseCost = request.getParameterValues("advertiseCost");
		// Object []  advertiseResponse = request.getParameterValues("advertiseResponse"); 
		 
		 Object [][] parameterHeader = new Object [1][3]; 
		 parameterHeader [0][0] =  bean.getReqCode() ;
		 parameterHeader [0][1] = bean.getPostionId();
		 parameterHeader [0][2] = bean.getNoOfVaccany(); 
		 
		 boolean result =getSqlModel().singleExecute(getQuery(1),parameterHeader);
		  if(result)
		   {
			  String sqlQuery=" SELECT MAX(ADVT_CODE) FROM HRMS_REC_ADVT_HDR ";
			   Object [][]dataObj = getSqlModel().getSingleResult(sqlQuery);
			   if(dataObj!=null && dataObj.length >0)
			   {
				   bean.setAdvertiseCode(""+dataObj[0][0]);
			   }
			   
			 if(advertiseMode != null && advertiseMode.length >0)
			 {   
				 int count =0;
				 String sql ="SELECT  NVL(MAX(ADVT_DTL_CODE),0)+1  FROM HRMS_REC_ADVT_DTL";
				 Object [][] data = getSqlModel().getSingleResult(sql);
				 if(data!=null && data.length >0)
				 { 
					 count =Integer.parseInt(""+data[0][0]); 
				 }
				 
				     Object [][] parameterDtl = new Object [1][7];
					 for(int i=0;i<advertiseMode.length ;i++)
					 {   
						 parameterDtl[0][0]=""+count;
						 parameterDtl[0][1]=""+advertiseMode[i];
						 parameterDtl[0][2]=(""+advertiseModeName[i]).trim();
						 parameterDtl[0][3]=checkNull((""+advertiseStartDate[i])).equals("")?null :(""+advertiseStartDate[i]);
						 parameterDtl[0][4]=checkNull((""+advertiseEndDate[i])).equals("")?null :(""+advertiseEndDate[i]);
						 parameterDtl[0][5]=""+advertiseDetails[i];
						 parameterDtl[0][6]=""+advertiseCost[i];  
						 
						 count++; 
						 if(!(((""+advertiseMode[i]).equals("0") ||(""+advertiseMode[i]).equals("")) 
								 && ((""+advertiseModeName[i]).trim().equals("")) && ((""+advertiseCost[i]).equals("0")||(""+advertiseCost[i]).equals("")))){
						   getSqlModel().singleExecute(getQuery(2),parameterDtl);
						 }
					 } 
				 
			 } 
		  }
		  return result;
	}
	
	/**
	 * update the advertise related data.
	 * @param bean
	 * @param request
	 * @return
	 */
	public boolean updateAdvertiseDtl(Advertisement bean,HttpServletRequest request)
	{
		 Object []  advertiseMode = request.getParameterValues("advertiseMode");
		 Object []  advertiseModeName = request.getParameterValues("advertiseModeName"); 
		 Object []  advertiseStartDate = request.getParameterValues("advertiseStartDate");
		 Object []  advertiseEndDate = request.getParameterValues("advertiseEndDate");
		 Object []  advertiseDetails = request.getParameterValues("advertiseDetails"); 
		 Object []  advertiseCost = request.getParameterValues("advertiseCost");
		// Object []  advertiseResponse = request.getParameterValues("advertiseResponse"); 
		 
		 Object [][] parameterHeader = new Object [1][4]; 
		 parameterHeader [0][0] =  bean.getReqCode() ;
		 parameterHeader [0][1] = bean.getPostionId();
		 parameterHeader [0][2] = bean.getNoOfVaccany(); 
		 parameterHeader [0][3] = bean.getAdvertiseCode();
		 
		 boolean result =getSqlModel().singleExecute(getQuery(5),parameterHeader);
		  if(result)
		   {
			     Object [][] para = new Object[1][1];
				 para [0][0]= bean.getAdvertiseCode(); 
                 getSqlModel().singleExecute(getQuery(3),para);
				 
			 if(advertiseMode != null && advertiseMode.length >0)
			 {   
				 int count =0;
				 String sql ="SELECT  NVL(MAX(ADVT_DTL_CODE),0)+1  FROM HRMS_REC_ADVT_DTL";
				 Object [][] data = getSqlModel().getSingleResult(sql);
				 if(data!=null && data.length >0)
				 { 
					 count =Integer.parseInt(""+data[0][0]); 
				 }
				 
				 Object [][] parameterDtl = new Object [1][8];
					 for(int i=0;i<advertiseMode.length ;i++)
					 {  
						 parameterDtl[0][0]=""+count;
						 parameterDtl[0][1]= bean.getAdvertiseCode();
						 parameterDtl[0][2]=""+advertiseMode[i];
						 parameterDtl[0][3]=(""+advertiseModeName[i]).trim();
						 parameterDtl[0][4]=""+advertiseStartDate[i];
						 parameterDtl[0][5]=""+advertiseEndDate[i];
						 parameterDtl[0][6]=""+advertiseDetails[i];
						 parameterDtl[0][7]=""+advertiseCost[i]; 
						 count++;
						 if(!(((""+advertiseMode[i]).equals("0") ||(""+advertiseMode[i]).equals("")) 
								 && ((""+advertiseModeName[i]).trim().equals("")) && ((""+advertiseCost[i]).equals("0")||(""+advertiseCost[i]).equals("")))){
							 result =getSqlModel().singleExecute(getQuery(6),parameterDtl);
						 }
					 } 
				
			 } 
		  }
		  return result;
	}
	
	/**
	 * display the saved application.
	 * @param bean
	 * @param request
	 * @return
	 */
	
	public void showSavedList(Advertisement bean ,HttpServletRequest request)
	{
	String sql =" SELECT NVL(REQS_NAME,' '), RANK_NAME,NVL(ADVT_NOOFVAC,0),SUM(NVL(ADVT_COST, 0)), SUM(NVL(ADVT_RESPONSES, 0)) , " +
				" HRMS_REC_ADVT_HDR.REQS_CODE,   ADVT_POSITION ,HRMS_REC_ADVT_HDR.ADVT_CODE 	FROM HRMS_REC_ADVT_HDR "
			+" 	INNER JOIN HRMS_REC_ADVT_DTL ON(HRMS_REC_ADVT_DTL.ADVT_CODE = HRMS_REC_ADVT_HDR.ADVT_CODE) "
			+"	INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_ADVT_HDR.REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE ) "
			+"	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = ADVT_POSITION) "
			+"	GROUP BY HRMS_REC_ADVT_HDR.ADVT_CODE, HRMS_REC_ADVT_HDR.REQS_CODE, ADVT_NOOFVAC, ADVT_POSITION,REQS_NAME,RANK_NAME " 
			+"	ORDER BY ADVT_CODE ASC"; 
	  Object [][] data = getSqlModel().getSingleResult(sql);  
	  
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
		
		
	  ArrayList  list = new ArrayList();
		  if(data != null && data.length >0)
		  {
				for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
			  Advertisement bean1 = new  Advertisement();
				  bean1.setItReqName(""+data[i][0]);
				  bean1.setItPosition(""+data[i][1]); 
				  bean1.setItVaccany(""+data[i][2]);
				  bean1.setItCost(""+data[i][3]); 
				  bean1.setItResponse(""+data[i][4]);
				  bean1.setItReqCode(""+data[i][5]); 
				  bean1.setItPositionId(""+data[i][6]);
				  bean1.setItAdvertiseCode(""+data[i][7]); 
				  list.add(bean1);
			  }
			  bean.setAdvertiseMainList(list);
			  bean.setTotalRecords(""+data.length);
		  } else{
			  bean.setNoData("true"); 
		  }
	}
	/**
	 * display the saved details of the application.
	 * @param bean
	 */
	public void showSaveDetailList(Advertisement bean)
	{ 
	String sql =" SELECT  ADVT_MODE, NVL(ADVT_NAME,' '), TO_CHAR(ADVT_START_DATE,'DD-MM-YYYY'),TO_CHAR( ADVT_END_DATE,'DD-MM-YYYY'), "
			+" NVL(ADVT_DETAILS,' '), NVL(ADVT_COST,0), NVL(ADVT_RESPONSES,0), " 
		    +" DECODE(ADVT_MODE, 'N','News Paper','T','TV Media','W','Website','O','Other')  FROM HRMS_REC_ADVT_DTL "
		    +" WHERE ADVT_CODE = "+bean.getAdvertiseCode()+" ORDER BY ADVT_DTL_CODE "; 
	  Object [][] data = getSqlModel().getSingleResult(sql);   
		  if(data != null && data.length >0)
		  { 
			  ArrayList  list = new ArrayList();
					 for(int i=0;i<data.length ;i++)
					 {    
						     Advertisement bean1 = new Advertisement(); 
							 bean1.setAdvertiseMode(""+data[i][0]);
							 bean1.setAdvertiseModeName(""+data[i][1]);
							 bean1.setAdvertiseStartDate(checkNull(""+data[i][2]));
							 bean1.setAdvertiseEndDate(checkNull(""+data[i][3])); 
							 bean1.setAdvertiseDetails(checkNull(""+data[i][4]));
							 bean1.setAdvertiseCost(""+data[i][5]); 
							 bean1.setAdvertiseResponse(checkNull(""+data[i][6]));
							 bean1.setAdvertiseModeText(checkNull(""+data[i][7])); 
							 list.add(bean1); 
					 }
					 bean.setAdvertiseList(list);  
		  }  
	}
	/**
	 * delete the advertise application.
	 * @param bean
	 * @return
	 */
	public boolean delete(Advertisement bean)
	{
		 Object [][] para = new Object[1][1];
		 para [0][0]= bean.getAdvertiseCode(); 
		 boolean result=getSqlModel().singleExecute(getQuery(3),para);
			if(result)
			{
				getSqlModel().singleExecute(getQuery(4),para);
			}
		return result;
	}
	/**
	 * returning the blank whenever the null value.
	 * @param result
	 * @return
	 */
	
	public String checkNull(String result) {
		 
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	} // end of checkNull
}
