package org.struts.action.recruitment;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Recruitment.Advertisement;
import org.paradyne.model.recruitment.AdvertisementModel;
import org.struts.lib.ParaActionSupport;
/**
 * @author AA0417
 *
 */

public class AdvertisementAction extends ParaActionSupport {

	 
	Advertisement advment = null;
	public void prepare_local() throws Exception {
		advment =  new Advertisement(); 
		advment.setMenuCode(895);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return advment;
	}

	public Advertisement getAdvment() {
		return advment;
	}

	public void setAdvment(Advertisement advment) {
		this.advment = advment;
	}
	
	public String input (){
		getNavigationPanel(1);
		AdvertisementModel model = new AdvertisementModel ();
		model.initiate(context, session);  
		model.showSavedList(advment,request);
		model.terminate();
		return "success";
	}
	
	/**
	 * method for display the details form 
	 * @return
	 */
	public String addNew (){
		advment.setReqName("");
		advment.setReqCode("");
		advment.setPostionId("");
		advment.setPostionName("");
		advment.setNoOfVaccany("");
		getNavigationPanel(2);
		return "advertiseDtl";
	} 
	
	public String addDetail(){
		getNavigationPanel(2); 
		return "advertiseDtl";
	} 
	/**
	 * display the all field in view mode
	 * @return
	 */
	public String edit(){
		advment.setEditFlag(false);
		getNavigationPanel(2); 
		AdvertisementModel model = new AdvertisementModel ();
		model.initiate(context, session); 
		model.advertiseDetail(advment, request);
		model.terminate();	 
		return "advertiseDtl";
	} 
	
	/**
	 * displaying the previous stage of the application.
	 * @return
	 */
	public String cancel(){ 
		if(advment.isEditFlag()==false && advment.isSearchFlag()==true)
		{   
			//advment.setSearchFlag(false);
			advment.setEditFlag(true);
			getNavigationPanel(3);
			AdvertisementModel model = new AdvertisementModel ();
			model.initiate(context, session);  
			model.showSaveDetailList(advment);
			model.terminate(); 
			return "advertiseDtl";
		}else
		{ 
			 advment.setEditFlag(false);
			 advment.setSearchFlag(false);
			 advment.setAdvertiseCode("");
			getNavigationPanel(1);   
			AdvertisementModel model = new AdvertisementModel ();
			model.initiate(context, session);  
			model.showSavedList(advment,request);
			model.terminate(); 
			return "success";
		}
	} 
	/**
	 * delete the advertise from the list.
	 * @return
	 */
	public String delete(){
		getNavigationPanel(1);
		AdvertisementModel model = new AdvertisementModel ();
		model.initiate(context, session);
		boolean result = model.delete(advment);
		model.showSavedList(advment,request);
		if(result)
		{
			addActionMessage(getMessage("delete"));
		} 
		
		model.terminate();		
		return "success";
	} 
	/**
	 * remove the record from detail table list.
	 * @return
	 */
	public String deleteDetailList(){
		getNavigationPanel(2);
		AdvertisementModel model = new AdvertisementModel ();
		model.initiate(context, session);
		model.deleteDetail(advment,request);
		model.terminate();		
		return "advertiseDtl";
	} 
 /**
  * displaying in view mode
  * @return
  */
	public String callVaccany(){
		getNavigationPanel(2);
		AdvertisementModel model = new AdvertisementModel ();
		model.initiate(context, session);
		model.callVaccany(advment);
		model.advertiseDetail(advment, request);
		model.terminate();		
		return "advertiseDtl";
	}
	/**
	 * save and update the advertise.
	 * @return
	 */
	public String saveAdvertise(){
		advment.setEditFlag(true);
		getNavigationPanel(3);
		AdvertisementModel model = new AdvertisementModel();
		boolean result =false;
		model.initiate(context, session); 
		if(advment.getAdvertiseCode().equals("")){
			 result = model.saveAdvertiseDtl(advment, request);
			if(result)
			{
				addActionMessage(getMessage("save"));
			}else{
				addActionMessage(getMessage("save.error"));
			}
		}else{
			 result = model.updateAdvertiseDtl(advment, request);
				if(result)
				{
					addActionMessage(getMessage("update"));
				}else{
					addActionMessage(getMessage("update.error"));
				}
		}
		model.advertiseDetail(advment, request);
		model.terminate();		
		return "advertiseDtl";
	}
	
	/**
	 * displaying the details of saved application
	 * @return
	 */
	public String showSavedDtl(){
		advment.setSearchFlag(true);
		advment.setEditFlag(true);
		getNavigationPanel(3);
		AdvertisementModel model = new AdvertisementModel ();
		model.initiate(context, session);  
	    model.showSaveDetailList(advment);
		model.terminate();		
		return "advertiseDtl";
	} 
	/**
	 * display the list of requisition
	 * @return
	 */
	public String f9Requistion()
	{
		try
		{
			/*String query = " SELECT  R1.REQS_NAME,HRMS_RANK.RANK_NAME , R1.REQS_CODE ,R1.REQS_POSITION FROM HRMS_REC_REQS_HDR R1 "
					      +" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = R1.REQS_POSITION) "   
						  +" WHERE REQS_STATUS='O' AND REQS_APPROVAL_STATUS IN ('A','Q')  " 
						  +" AND R1.REQS_CODE NOT IN (SELECT REQS_CODE FROM HRMS_REC_ADVT_HDR) "
				          +" order by REQS_DATE desc";*/
			
			String query = "SELECT REQS_NAME, HRMS_RANK.RANK_NAME , REQS_CODE, REQS_POSITION " 
						  +" FROM HRMS_REC_REQS_HDR   "
						  +" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "  
						  +" INNER  JOIN HRMS_REC_VACPUB_HDR ON (HRMS_REC_VACPUB_HDR.PUB_REQS_CODE = HRMS_REC_REQS_HDR.REQS_CODE) "    
						  +" INNER JOIN HRMS_REC_REQS_VACDTL ON (HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE) " 
						  +" WHERE REQS_STATUS='O' AND REQS_APPROVAL_STATUS IN ('A','Q')  AND VACAN_STATUS='P'  "
						  +" AND HRMS_REC_REQS_HDR.REQS_CODE NOT IN (SELECT REQS_CODE FROM HRMS_REC_ADVT_HDR)   "
						  +" ORDER BY REQS_DATE DESC ";
			 
			
			String []headers ={getMessage("reqs.code"), getMessage("position")};

			String[] headerWidth = {"40", "60"};

			String[] fieldNames = {"reqName","postionName","reqCode" ,"postionId"};

			int[] columnIndex = {0,1,2,3};

			String submitFlag = "true";

			String submitToMethod = "Advertisement_callVaccany.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			 
			return null;
		}
	}
	
	/**
	 * display the list of saved application
	 * @return
	 */
	
	
	public String f9Advertise()
	{
		try
		{
		String query =   "  SELECT NVL(REQS_NAME,' '), RANK_NAME,NVL(ADVT_NOOFVAC,0), ADVT_POSITION,HRMS_REC_ADVT_HDR.REQS_CODE,ADVT_CODE "
						+"	FROM HRMS_REC_ADVT_HDR "
						+"	INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_ADVT_HDR.REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE ) "
						+"	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = ADVT_POSITION) ORDER BY ADVT_CODE ASC ";
			 
			
			String []headers ={getMessage("reqs.code"), getMessage("position"), getMessage("noOfVaccanies")};

			String[] headerWidth = {"40", "30","30"};

			String[] fieldNames = {"reqName","postionName","noOfVaccany","postionId","reqCode","advertiseCode"};

			int[] columnIndex = {0,1,2,3,4,5};

			String submitFlag = "true";

			String submitToMethod = "Advertisement_showSavedDtl.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}catch (Exception e) {
			 
			return null;
		}
	}

}
