package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.paradyne.bean.admin.master.QualificationMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/** modified
 * @author AA0650(Dilip)
 */


 /**
 to define the business logic for  qualification details
 * 
 *
 */
public class QualificationModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
																(org.struts.action.admin.master.RankMasterAction.class);
	/**
	 * following function is called to add a new record
	 * @param bean
	 * @return
	 */
public boolean save(QualificationMaster bean) {
		
		boolean result=false;
		
		
		
		String selQuery=" SELECT * FROM HRMS_QUA WHERE UPPER(QUA_NAME) LIKE '"
					+ bean.getQuaName().trim().toUpperCase() + "'";
		
		Object [][]selData=getSqlModel().getSingleResult(selQuery);
		if(selData.length==0){
		
			
			Object[][] add=new Object[1][5];
			add[0][0]=bean.getQuaName().trim();
			add[0][1]=bean.getQuaAbbr().trim();
			add[0][2]=bean.getIsactive();
			add[0][3]=bean.getDesciption();
			add[0][4]=bean.getQualevel();
		
		/**insObj[0][0]= pName;
		System.out.println(""+pName);
		insObj[0][1]=pOwnerId;
		insObj[0][2]=pDescription;
		System.out.println(""+pDescription);
		*/
			System.out.println("....................................................................");
		String insQuery="INSERT INTO HRMS_QUA (QUA_ID, QUA_NAME, QUA_ABBR, QUA_ISACTIVE, QUA_DESC, QUA_LEVEL) VALUES((SELECT NVL(MAX(QUA_ID),0)+1 FROM  HRMS_QUA) ,?,?,?,?,?)";
		result=getSqlModel().singleExecute(insQuery,add);
		
		String Query="SELECT MAX(QUA_ID) FROM  HRMS_QUA";
		Object [][]data=getSqlModel().getSingleResult(Query);
		bean.setQualificationID(String.valueOf(data[0][0]));
		}
		return result;
	

	}

/**public String addQualification(QualificationMaster bean) {
		Object[][] add=new Object[1][5];
		String flag = "";
		boolean result = false;
		try{
		add[0][0]=bean.getQuaName().trim();
		add[0][1]=bean.getQuaAbbr().trim();
		add[0][2]=bean.getIsactive();
		add[0][3]=bean.getDesciption();
		add[0][4]=bean.getQualevel();
		if(!checkDuplicate(bean))
		{
			result=getSqlModel().singleExecute(getQuery(1),add);
			if(result)
			{
			String query = " SELECT MAX(QUA_ID) FROM HRMS_QUA" ;
			Object[][] data = getSqlModel().getSingleResult(query);
					
			String query1 = " SELECT QUA_NAME,QUA_ABBR,QUA_LEVEL,QUA_DESC,QUA_ISACTIVE,QUA_ID " +
					"FROM HRMS_QUA WHERE QUA_ID="+ String.valueOf(data[0][0]);
			
			Object[][] Data = getSqlModel().getSingleResult(query1);
			bean.setQuaName(checkNull(String.valueOf(Data[0][0])));
			bean.setQuaAbbr(checkNull(String.valueOf(Data[0][1])));
			bean.setQualevel(checkNull(String.valueOf(Data[0][2])));
			bean.setDesciption(checkNull(String.valueOf(Data[0][3])));
			bean.setIsactive(checkNull(String.valueOf(Data[0][4])));
			bean.setQuaID(checkNull(String.valueOf(Data[0][5])));
			flag="saved";
			}
			else
			{
				flag="error";
			}
		}
		else
		{
			String query = "SELECT DECODE(QUA_ISACTIVE,'Y','Yes','N','No') " 
                +" FROM HRMS_QUA where  QUA_ISACTIVE='"+bean.getIsactive()+"'"; 

			Object[][] data = getSqlModel().getSingleResult(query);
			flag = "duplicate";
		}
		}catch (Exception e) {
			logger.error("Exception was rised---->");
		}
		return flag;
     }*/
	/**
	 * for modifying the data
	 * @param bean
	 * @return
	 */

public boolean update(QualificationMaster bean) {
	Object [][] data=new Object[1][6];
	
	boolean result=false;

	data[0][0]=bean.getQuaName();
	data[0][1]=bean.getQuaAbbr();
	data[0][2]=bean.getQualevel();
	data[0][3]=bean.getDesciption();
	data[0][4]=bean.getIsactive();
	data[0][5]=bean.getQualificationID();
	
	
	
	
	
	
	
	
	String upQuery="UPDATE HRMS_QUA SET QUA_NAME=?,QUA_ABBR=? ,QUA_LEVEL=? ,QUA_DESC=?,QUA_ISACTIVE=? where QUA_ID=?";
	result=getSqlModel().singleExecute(upQuery,data);
	
	
	return result;
}


	/**public String modQualification(QualificationMaster bean) {
	Object [][] data=new Object[1][6];
	String editFlag="";
	boolean result=false;
	try{
	data[0][0]=bean.getQuaName();
	data[0][1]=bean.getQuaAbbr();
	data[0][2]=bean.getQualevel();
	data[0][3]=bean.getDesciption();
	data[0][4]=bean.getIsactive();
	data[0][5]=bean.getQuaID();

	String query="UPDATE HRMS_QUA SET QUA_NAME=?,QUA_ABBR=? ,QUA_LEVEL=? ,QUA_DESC=?,QUA_ISACTIVE=? where QUA_ID=?";
	if(!checkDuplicateMod(bean))
	{
		result=getSqlModel().singleExecute(query, data);
		if(result)
		{
			String query1 = "SELECT QUA_NAME,QUA_ABBR, QUA_LEVEL ,QUA_DESC,QUA_ISACTIVE,QUA_ID FROM HRMS_QUA where QUA_ID="+bean.getQuaID();
	
			Object [][]data1=getSqlModel().getSingleResult(query1);
			bean.setQuaName(checkNull(String.valueOf(data1[0][0]).trim()));
			bean.setQuaAbbr(checkNull(String.valueOf(data1[0][1]).trim()));
			bean.setQualevel(checkNull(String.valueOf(data1[0][2]).trim()));
			bean.setDesciption(checkNull(String.valueOf(data1[0][3]).trim()));
			bean.setIsactive(String.valueOf(data1[0][4]));
			bean.setQuaID(checkNull(String.valueOf(data1[0][5]).trim()));
			editFlag="modified";
		}
		else
		{
			editFlag="error";
		}
	}
	else
	{
		editFlag="duplicate";	
	}
	}catch (Exception e) {
		logger.error("Exception was rised--->");
	}
	return editFlag;
}*/
	/**
	 * for deleting the record after selecting
	 * @param bean
	 * @return boolean
	 */
	public boolean deleteQualification(QualificationMaster bean) {
		boolean result=false;
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getQualificationID();
		
		String query="DELETE FROM HRMS_QUA WHERE QUA_ID=?";
		
		
		 result=getSqlModel().singleExecute(query, addObj);
		 return result;
	}
	/**
	 * To get the particular record whatever user wants 
	 * @param quaMaster
	 */
	public void getQualificationRecord(QualificationMaster quaMaster) {
		Object param[] = new Object[1];
		param[0] = quaMaster.getQuaID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), param);
		quaMaster.setQuaID(checkNull(String.valueOf(data[0][0])));
		quaMaster.setQuaName(checkNull(String.valueOf(data[0][1])));
		quaMaster.setQuaAbbr(checkNull(String.valueOf(data[0][2])));
		quaMaster.setQualevel(checkNull(String.valueOf(data[0][3])));
		quaMaster.setDesciption(checkNull(String.valueOf(data[0][4])));
		quaMaster.setIsactive(String.valueOf(data[0][5]));
	}
	/**
	 * To check null or not
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public void getQualification(QualificationMaster bean){
		try{
		
		String query = " SELECT QUA_NAME,QUA_ABBR,QUA_LEVEL,QUA_DESC,DECODE(QUA_ISACTIVE,'Y','Yes','N','No'),QUA_ID  FROM HRMS_QUA  "
			+" WHERE QUA_ID="+bean.getQuaID();

	Object[][] data = getSqlModel().getSingleResult(query);
	
	if(data!=null && data.length>0){
		
			bean.setQuaName(checkNull(String.valueOf(data[0][0]).trim()));
			bean.setQuaAbbr(checkNull(String.valueOf(data[0][1]).trim()));
			bean.setQualevel(String.valueOf(data[0][2]).trim());
			bean.setDesciption(checkNull(String.valueOf(data[0][3])));
			bean.setIsactive(String.valueOf(data[0][4]));
			bean.setQuaID(checkNull(String.valueOf(data[0][5])));
	     }
	 }catch(Exception e){
		e.printStackTrace();
   }
	 
}	 
	/**
	 *  this method is used to modify the record
	 * @param bean
	 */
	public void getQualificationEdt(QualificationMaster bean){
		try{
				
		String query = " SELECT QUA_NAME,QUA_ABBR,QUA_LEVEL,QUA_DESC,QUA_ISACTIVE,QUA_ID  FROM HRMS_QUA  "
			+" WHERE QUA_ID="+bean.getQuaID();

	Object[][] data = getSqlModel().getSingleResult(query);
	
	if(data!=null && data.length>0){
		
			bean.setQuaName(checkNull(String.valueOf(data[0][0]).trim()));
			bean.setQuaAbbr(checkNull(String.valueOf(data[0][1]).trim()));
			bean.setQualevel(String.valueOf(data[0][2]).trim());
			bean.setDesciption(checkNull(String.valueOf(data[0][3])));
			bean.setIsactive(String.valueOf(data[0][4]));
			bean.setQuaID(checkNull(String.valueOf(data[0][5])));
	     }
	 }catch(Exception e){
		e.printStackTrace();
   }
	 
}	 

	/**
	 *  to display the record 
	 * @param bean
	 */
	public void getQualificationRec(QualificationMaster bean){
		try{
		
		String query = " SELECT QUA_NAME,QUA_ABBR,QUA_LEVEL,QUA_DESC,QUA_ISACTIVE,QUA_ID  FROM HRMS_QUA  "
			+" WHERE QUA_ID="+bean.getQualificationID();

	Object[][] data = getSqlModel().getSingleResult(query);
	
	if(data!=null && data.length>0){
		
			bean.setQuaName(checkNull(String.valueOf(data[0][0]).trim()));
			bean.setQuaAbbr(checkNull(String.valueOf(data[0][1]).trim()));
			bean.setQualevel(checkNull(String.valueOf(data[0][2]).trim()));
			bean.setDesciption(checkNull(String.valueOf(data[0][3])));
			bean.setIsactive(checkNull(String.valueOf(data[0][4])));
			bean.setQualificationID(checkNull(String.valueOf(data[0][5])));
			//bean.setStatus(checkNull(String.valueOf(data[0][6])));
	     }
	 }catch(Exception e){
		e.printStackTrace();
   }
	 
}	

	/**
	 *  to edit the record after double click
	 * @param bean
	 */
	
	public void getQualificationOnDoubleClick(QualificationMaster bean){
		try{
		
		String query = " SELECT NVL(QUA_NAME,' '),NVL(QUA_ABBR,' '), QUA_LEVEL,QUA_DESC,QUA_ISACTIVE,QUA_ID  FROM HRMS_QUA"
			+" WHERE QUA_ID="+bean.getQualificationID();

	Object[][] data = getSqlModel().getSingleResult(query);
	
	if(data!=null && data.length>0){
		
			bean.setQuaName(String.valueOf(data[0][0]).trim());
			bean.setQuaAbbr(checkNull(String.valueOf(data[0][1]).trim()));
			bean.setQualevel(checkNull(String.valueOf(data[0][2]).trim()));
			bean.setDesciption(checkNull(String.valueOf(data[0][3]).trim()));
			bean.setIsactive(String.valueOf(data[0][4]));
			bean.setQualificationID(String.valueOf(data[0][5]));
	     }
	//getQualificationRec(bean);
	 }catch(Exception e){
		e.printStackTrace();
   }
	 
}	 
	/**
	 * to show list of records belonging to qualification
	 * @param bean
	 * @param request
	 */
	public void getRecords(QualificationMaster bean,HttpServletRequest request){
		try{
		int length=0;
		String query = " SELECT NVL(QUA_NAME,' '),NVL(QUA_ABBR,' '),QUA_LEVEL,NVL(QUA_DESC,' '),CASE WHEN QUA_ISACTIVE='Y' THEN 'Yes' ELSE 'No' END,QUA_ID  FROM HRMS_QUA"
			+" ORDER BY upper(QUA_NAME)";

		Object[][] data = getSqlModel().getSingleResult(query);
		if(data!=null && data.length>0){
			
		bean.setModeLength("true");
		bean.setTotalRecords(String.valueOf(data.length));
		
		String[] pageIndex = Utility.doPaging(bean.getMyPage(), data.length, 20);	
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
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
		
		QualificationMaster  bean1 = new QualificationMaster();
		bean1.setIttSrN0(String.valueOf(i+1));	
		bean1.setQuaName(String.valueOf(data[i][0]).trim());
		bean1.setQuaAbbr(String.valueOf(data[i][1]).trim());
		bean1.setQualevel(String.valueOf(data[i][2]).trim());
		bean1.setDesciption(checkNull(String.valueOf(data[i][3]).trim()));
		bean1.setIsactive(String.valueOf(data[i][4]).trim());
		bean1.setQuaID(String.valueOf(data[i][5]));
		List.add(bean1);
	  }
	  bean.setQualificationList(List);
	  length=data.length;
	
	}
		}	
		catch(Exception e){
		e.printStackTrace();
	}
	}	
	/**
	 * To delete the checked records ( Multiple records)
	 * @param bean
	 * @param code
	 * @param request 
	 * @param quaId 
	 * @return
	 */	
	public boolean delChkdRec(QualificationMaster bean, String[] code, String[] quaId, HttpServletRequest request) {
	
		boolean result = false;
		for (int j = 0; j < code.length; j++) {	
			if(code[j].equals("Y")){
		
			String delQuery="DELETE FROM HRMS_QUA WHERE QUA_ID="+quaId[j];
			result = getSqlModel().singleExecute(delQuery);
			
			}
		
			
		
		}
		getRecords( bean, request);
		
		
		
		return result;
		
	}

	/**
	 * for checking duplicate entry of records during insertion
	 * @param bean
	 * @return boolean
	 */
	public boolean checkDuplicate(QualificationMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_QUA WHERE UPPER(QUA_NAME) LIKE '"
				+ bean.getQuaName().trim().toUpperCase()+"'";
				
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/**
	 * for checking duplicate entry of records during modification
	 * @param bean
	 * @return boolean
	 */
	public boolean checkDuplicateMod(QualificationMaster bean) {
		boolean result = false;
		Object[][] data = null;
		String jdName = bean.getQuaName().trim().toUpperCase();
		Object[] value= new Object[1];
		try {
			value[0]=bean.getQuaName().trim().toUpperCase();
		}catch (Exception e) {
			logger.error("Exception-------");
		}
		try{
		String query = "SELECT * FROM HRMS_QUA WHERE UPPER(QUA_NAME) LIKE '"
				+ bean.getQuaName().trim().toUpperCase()
				+ "' AND QUA_ID not in(" + bean.getQualificationID() + ")";
		data = getSqlModel().getSingleResult(query);
		}
		catch (Exception e) {
			logger.error("Exception -------->");
		}
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}
	/**
	 * Following function is used to get the Pdf report about the qualification master records
	 * @param qualiMaster
	 * @param response
	 * @param label
	 */
	public void generateReport(QualificationMaster qualiMaster, HttpServletResponse response,String[]label) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName="\n\nQualification Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf", reportName);
		rg.setFName("Qualification Master.Pdf");
		String queryDes="SELECT QUA_NAME,QUA_ABBR,DECODE(QUA_ISACTIVE,'Y','Yes','N','No') FROM HRMS_QUA ORDER BY QUA_NAME";
		Object [][]data=getSqlModel().getSingleResult(queryDes);
		Object [][] Data=new Object[data.length][4];
		if (data != null && data.length > 0) {
			int j=1;
			for(int i=0;i<data.length;i++){
				Data[i][0]=j;
				Data[i][1]=data[i][0];
				Data[i][2]=data[i][1];
				Data[i][3]=data[i][2];
				j++;
			}
			int cellwidth[] = {5, 20,20,20 };
			int alignment[] = { 1, 0 , 0, 0};
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n",0,0,0);
			rg.addTextBold("Date :"+toDay, 0, 2, 0);
			rg.addText("\n",0,0,0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}
		rg.createReport(response);
	}
}
