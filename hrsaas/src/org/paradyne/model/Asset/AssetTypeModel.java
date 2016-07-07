package org.paradyne.model.Asset;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.paradyne.bean.Asset.AssetType;
import org.paradyne.bean.admin.master.TitleMaster;
import org.paradyne.bean.attendance.AutoPresentAttendance;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author mangeshj
 * Date 24/07/2008
 * AssetTypeModel class to write business logic to add, update, view the asset type
 */
public class AssetTypeModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

	/* method name : addAsset 
	 * purpose     : to add the assets type in the HRMS_ASSET_CATEGORY
	 * return type : String
	 * parameter   : AssetType instance
	 */
	public String addAsset(AssetType bean)
	{
		
		String query = "SELECT ASSET_CATEGORY_NAME FROM HRMS_ASSET_CATEGORY  WHERE UPPER(ASSET_CATEGORY_NAME) LIKE '"
			+ bean.getAssetname().trim().toUpperCase() + "'";
		
	 
		Object[][] data= getSqlModel().getSingleResult(query);
		if(data.length>0)
		{
			return "Duplicate entry found.";
		} // end of if
		else
		{
			Object addObj[][] =new Object[1][2];
			addObj[0][0] =bean.getAssetname().trim();
			addObj[0][1] =bean.getIsActive();
			String recordQur="SELECT Nvl(MAX(ASSET_CATEGORY_CODE),0)+1 FROM HRMS_ASSET_CATEGORY";
			logger.info("ss query "+recordQur);
			Object record[][]=getSqlModel().getSingleResult(recordQur);
			
			logger.info("sss "+bean.getAssetCode());
			boolean result=getSqlModel().singleExecute(getQuery(1),addObj);
			logger.info("u r in if..result.!!"+result);
			if(result) {
				if(record!=null && record.length>0)
				{
					bean.setAssetCode(String.valueOf(record[0][0]));
					logger.info("u r in if...!!"+record[0][0]);
				}
				else{
					logger.info("u r in else...!!");
					logger.info("u r in else...!!"+record);
				}
				return "Record saved successfully.";

			}  // end of if
			else {
				return "Duplicate entry Found.";

			} // end of else
		} // end of else
	}
	/* method name : modAsset 
	 * purpose     : to update the assets type in the HRMS_ASSET_CATEGORY
	 * return type : String
	 * parameter   : AssetType instance
	 */
	public String modAsset(AssetType bean){
		Object modObj[][]=new Object[1][3];
		modObj[0][0]=bean.getAssetname().trim();
		modObj[0][1]=bean.getIsActive();
		modObj[0][2]=bean.getAssetCode();
		boolean result=getSqlModel().singleExecute(getQuery(2),modObj);			// update query
		if(result) {
			return "Record updated successfully.";

		} // end of if
		else {
			return "Duplicate entry Found.";

		}// end of else
	}
	/* method name : deleteAsset 
	 * purpose     : to delete the assets type from the HRMS_ASSET_CATEGORY
	 * return type : boolean
	 * parameter   : AssetType instance
	 */
	public boolean deleteAsset(AssetType bean){
		Object delObj[][]=new Object[1][1];
		delObj[0][0]=bean.getAssetCode().trim();
		return getSqlModel().singleExecute(getQuery(3),delObj);
	}
	
	/* method name : getReport 
	 * purpose     : to show the report of the assets type from the HRMS_ASSET_CATEGORY
	 * return type : void
	 * parameter   : AssetType instance, HttpServletRequest request,HttpServletResponse response,ServletContext context,HttpSession session
	 */
	public void getReport(AssetType bean,HttpServletRequest request,HttpServletResponse response,ServletContext context,HttpSession session, String label[]){
		/*CrystalReport cr=new CrystalReport();
		String path="org/paradyne/rpt/admin/master/Asset.rpt";
		cr.createReport(request, response, context,session, path, "");*/
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nAsset Category Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Asset Category Master.Pdf");
		String queryDes = "SELECT  ASSET_CATEGORY_NAME FROM HRMS_ASSET_CATEGORY ORDER BY upper(ASSET_CATEGORY_CODE)";
						
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][2];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				j++;
			}
			int cellwidth[] = { 10, 40 };
			int alignment[] = { 1, 0 };
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}
		rg.createReport(response);
	}
	/* 
	 * method name : checkNull 
	 * purpose     : check the null value
	 * return type : String
	 * parameter   : String result
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	/* method name : Data 
	 * purpose     : to show the assets type in the list
	 * return type : void
	 * parameter   : AssetType instance, HttpServletRequest request
	 */
	public void  Data(AssetType bean, HttpServletRequest request) {


		Object [][] obj = getSqlModel().getSingleResult(getQuery(6));
		
		//Object repData[][] = getSqlModel().getSingleResult(query);
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),obj.length,20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		
		
		
		 if(pageIndex[4].equals("1"))
			   bean.setMyPage("1");
		  			
			
			ArrayList<Object> list = new ArrayList<Object>();
			if (obj != null && obj.length > 0) {

			//	for (int i = 0; i < obj.length; i++) {
				 for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){

					 AssetType  bean1 = new AssetType ();

						bean1.setAssetCode(checkNull(String.valueOf(obj[i][0])));
						bean1.setAssetname(checkNull(String.valueOf(obj[i][1])));
						bean1.setIsActive(checkNull(String.valueOf(obj[i][2])));
						list.add(bean1);
				}
				
				 bean.setTotalRecords(""+obj.length);
			}
			else{				
				bean.setListLength("false");
				}			
			
		    if(list.size()>0)
		    	bean.setListLength("true");
		    else
		    	bean.setListLength("false");
		    //logger.info("length --> "+obj.length);
		    bean.setIteratorlist(list);

	}
	/* method name : callForEdit 
	 * purpose     : to set the assets type on the screen after clicking for edit
	 * return type : void
	 * parameter   : AssetType instance
	 */
	public void callForEdit(AssetType bean) {


		try {
			String query = "Select ASSET_CATEGORY_CODE,ASSET_CATEGORY_NAME,ASSET_CATEGORY_TYPE,ASSET_CATEGORY_UNIT ,ASSET_ISACTIVE FROM HRMS_ASSET_CATEGORY WHERE ASSET_CATEGORY_CODE= "
					+ bean.getHiddencode();
			Object[][] data = getSqlModel().getSingleResult(query);
			bean.setAssetCode(String.valueOf(data[0][0]));
			bean.setAssetname(String.valueOf(data[0][1]));
			bean.setType(String.valueOf(data[0][2]));
			bean.setUnit(String.valueOf(data[0][3]));
			bean.setIsActive(String.valueOf(data[0][4]));
		} catch (Exception e) {
			// TODO: handle exception
		}


	}
	/* method name : deleteCheckedRecords 
	 * purpose     : to delete the checked assets type on the screen 
	 * return type : void
	 * parameter   : AssetType instance,  String[] code
	 */
	public boolean deleteCheckedRecords(AssetType bean, String[] code) {
		boolean result=false;
		int count=0;
		if(code !=null)
		{
			for (int i = 0; i < code.length; i++) {

				if(!code[i].equals("")){

					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					result=getSqlModel().singleExecute(getQuery(5), delete);
					if(!result)
						count++;

				}// end of if
			}// end of for loop
		}// end of if
		if(count!=0)
		{
		result=false;
		return result;
		}// end of if
		else
		{
		result=true;
		return result; 
		}// end of else
	}



	/**
	 *  to get the record after click on search button
	 * @param tm2
	 * @param request
	 */
		public void data1(AssetType aT) {
			// TODO Auto-generated method stub
			try{
		 
			String query = " SELECT  ASSET_CATEGORY_NAME , ASSET_ISACTIVE FROM HRMS_ASSET_CATEGORY WHERE ASSET_CATEGORY_CODE="+aT.getAssetCode()+" ORDER BY ASSET_CATEGORY_CODE ";
			Object data[][] = getSqlModel().getSingleResult(query);
			//AssetType bean1 = new AssetType();
			aT.setAssetname(checkNull(String.valueOf(data[0][0])));
			aT.setIsActive(checkNull(String.valueOf(data[0][1])));
				logger.info("asset moder");
			}//end of loop
			
			catch(Exception e){
				logger.error("asset model data1 --- "+e);
				e.printStackTrace();
		   }
		}
	}




	/*
public void  getiteratorRecord(AssetType bean)
	{
		Object param[] =new Object[1];

               param[0]=bean.getAssetCode();

		Object[][] data = getSqlModel().getSingleResult(getQuery(6),param);

		//setter

		bean.setAssetCode(checkNull(String.valueOf(data[0][0])));

	        	bean.setAssetname(checkNull(String.valueOf(data[0][1])));



	}*/





