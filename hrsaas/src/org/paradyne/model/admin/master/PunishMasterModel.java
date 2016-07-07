/**
 * 
 */
package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.admin.master.PunishMaster;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author riteshr
 * 
 */
/**
 *  to define the business logic for the punishment
 */
public class PunishMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	PunishMaster punishMaster = null;
	
	
	public void getCreditPunish(PunishMaster bean,HttpServletRequest request){
		try{
		String query="SELECT PUNISH_SALARY FROM HRMS_PUNISHMENT WHERE PUNISH_ID="+bean.getPunishId();	
		Object[][] data = getSqlModel().getSingleResult(query);
		if(data!=null && data.length>0){
			if(String.valueOf(data[0][0]).equals("Y")){
				bean.setIsCredit("true");
				bean.setCreditChk("true");
				bean.setFlag(true);
				request.setAttribute("creditData","true");
			}else{
				bean.setIsCredit("false");
				bean.setCreditChk("false");
				bean.setFlag(false);
				request.setAttribute("creditData","false");
			}
		}
		
		String creditQuery="SELECT CREDIT_CODE,CREDIT_NAME,PUNISH_PERCENTAGE FROM HRMS_CREDIT_HEAD INNER JOIN HRMS_PUNISH_SALARY"
			+" ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_PUNISH_SALARY.PUNISH_CREDIT_CODE)"
			+" WHERE PUNISH_ID="+bean.getPunishId();
		Object[][] credit = getSqlModel().getSingleResult(creditQuery);
		ArrayList<Object> list=new ArrayList<Object>();
		if(credit!=null && credit.length>0){
			bean.setFlag(true);
			for(int i=0;i<credit.length;i++){
				PunishMaster bean1=new PunishMaster();
				bean1.setCreditCode(String.valueOf(credit[i][0]));
				bean1.setCreditHead(String.valueOf(credit[i][1]));
				if(String.valueOf(credit[i][2]).equals("null") || String.valueOf(credit[i][2]).equals("")){
					bean1.setCreditPercent("");
				}else{
				    bean1.setCreditPercent(String.valueOf(credit[i][2]));
				}
				list.add(bean1);
				}
			bean.setCreditList(list);	
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/* for checking duplicate entry of records during insertion. */
	public boolean checkDuplicate(PunishMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_PUNISHMENT WHERE UPPER(PUNISH_NAME) LIKE '"
				+ bean.getPunishName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for checking duplicate entry of records during modification. */
	public boolean checkDuplicateMod(PunishMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_PUNISHMENT WHERE UPPER(PUNISH_NAME) LIKE '"
				+ bean.getPunishName().trim().toUpperCase()
				+ "' AND PUNISH_ID not in(" + bean.getPunishId() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for inserting records. */
	public boolean savePunish(PunishMaster pMaster,String[] creditId,String []percent,HttpServletRequest request) {
			boolean result=false;
		if (!checkDuplicate(pMaster)) {
			Object addObj[][] = new Object[1][4];
			
			String query = " 	SELECT NVL(MAX(PUNISH_ID),0) + 1"
				+ " FROM HRMS_PUNISHMENT ";
			Object data[][] = getSqlModel().getSingleResult(query);			
			pMaster.setPunishId(String.valueOf(data[0][0]));
			addObj[0][0] = pMaster.getPunishName().trim();
			addObj[0][1] = pMaster.getFImplication().trim();
			addObj[0][2] = pMaster.getIsMajor();
			if(pMaster.getCreditChk().equals("true")){
				addObj[0][3]=String.valueOf("Y");	
			}else{
				addObj[0][3]=String.valueOf("N");	
			}
			result=getSqlModel().singleExecute(getQuery(1),addObj);			
				if(pMaster.getCreditChk().equals("true")){		 		
				if(creditId!=null && creditId.length>0){
					Object[][] addCredit=new Object[creditId.length][3];
					for(int i=0;i<creditId.length;i++){
					addCredit[i][0]=String.valueOf(data[0][0]);
					addCredit[i][1]=creditId[i];
					addCredit[i][2]=percent[i];
					}		
			getSqlModel().singleExecute(getQuery(6),addCredit);
				}
			}	
		 return result;

		}// end of if
		return false;
	}

	/* for modifying records. */
	public boolean modPunish(PunishMaster pMaster,String[] creditId,String[] percent) {
		boolean result=false;
		if (!checkDuplicateMod(pMaster)) {
			Object modObj[][] = new Object[1][5];
			modObj[0][0] = pMaster.getPunishName().trim();
			modObj[0][1] = pMaster.getFImplication().trim();
			modObj[0][2] = pMaster.getIsMajor().trim();
			//System.out.println("check box is ------> "+pMaster.getCreditChk());
			if(pMaster.getCreditChk().equals("true")){
				modObj[0][3]=String.valueOf("Y");	
			}else{
				modObj[0][3]=String.valueOf("N");	
			}
			modObj[0][4] = pMaster.getPunishId();
			result=getSqlModel().singleExecute(getQuery(4), modObj);
			Object[][] delObj = new Object[1][1];
			delObj[0][0] = pMaster.getPunishId();			
			if(pMaster.getCreditChk().equals("true")){			
				if(creditId!=null && creditId.length>0){
					Object[][] addCredit=new Object[creditId.length][3];
					for(int i=0;i<creditId.length;i++){
					addCredit[i][0]=pMaster.getPunishId();
					addCredit[i][1]=creditId[i];				
					addCredit[i][2]=percent[i];
					}		
					getSqlModel().singleExecute(getQuery(7),delObj);
					getSqlModel().singleExecute(getQuery(6),addCredit);
				 
				}
			}else{
				getSqlModel().singleExecute(getQuery(7),delObj);
			}
			
			return result;
		}// end of if
		else {
			return false;
		}// end of else

	}

	/* for deleting single record. */
	public String deleteRecord(PunishMaster pMaster) {

		Object[][] delObj = new Object[1][1];
		delObj[0][0] = pMaster.getPunishId();
		getSqlModel().singleExecute(getQuery(7),delObj);
		boolean result = getSqlModel().singleExecute(getQuery(5),delObj);
		if (result) {
			return "Record deleted successfully";
		}// end of if
		else {
			return "This record is referenced in other resources.So cannot delete.";
		}// end of else

	}

	public void getReport(PunishMaster bean) {
		String query = "  SELECT  PUNISH_ID,PUNISH_NAME, FINANCIAL_IMPLICATIONS ,DECODE(IS_MAJOR ,'Y','Yes','N','No')"
				+ " FROM HRMS_PUNISHMENT ORDER BY PUNISH_ID ";

		Object data[][] = getSqlModel().getSingleResult(query);
		ArrayList<Object> punishmentList = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			PunishMaster bean1 = new PunishMaster();
			bean1.setPunishId(checkNull(String.valueOf(data[i][0])));
			bean1.setPunishName(checkNull(String.valueOf(data[i][1])));
			bean1.setFImplication(checkNull(String.valueOf(data[i][2])));
			bean1.setIsMajor(checkNull(String.valueOf(data[i][3])));
			punishmentList.add(bean1);

		}// end of loop
		bean.setPunishmentList(punishmentList);

	}
/**
 * to check null value
 * @param result
 * @return
 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/* for generating report. */
	public void getReport(PunishMaster tadist, HttpServletRequest request,
			HttpServletResponse

			response, ServletContext context, HttpSession session,String[] label) {
		/*CrystalReport cr = new CrystalReport();
		String path = "org\\paradyne\\rpt\\admin\\master\\punishment.rpt";
		cr.createReport(request, response, context, session, path, "");*/
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nPunishment Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Punishment  Master.Pdf");
		String queryDes = "SELECT PUNISH_NAME,FINANCIAL_IMPLICATIONS,DECODE(IS_MAJOR ,'Y','Yes','N','No') FROM HRMS_PUNISHMENT ORDER BY upper(PUNISH_NAME) ";
						
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][4];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				
				j++;
			}
			int cellwidth[] = { 15, 40, 40, 30 };
			int alignment[] = { 1, 0, 0, 0};
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

	/* for displaying records in the list. */
	public void punisData(PunishMaster punishMaster, HttpServletRequest request) {

		Object[][] repData = getSqlModel().getSingleResult(getQuery(2));
		if(repData!=null && repData.length>0){
			punishMaster.setModeLength("true");	
			punishMaster.setTotalRecords(String.valueOf(repData.length));		
		String[] pageIndex = Utility.doPaging(punishMaster.getMyPage(), repData.length, 20);	
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
			punishMaster.setMyPage("1");
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {
			PunishMaster bean1 = new PunishMaster();
			bean1.setPunishId(checkNull(String.valueOf(repData[i][0])));
			bean1.setPunishName(checkNull(String.valueOf(repData[i][1])));
			bean1.setFImplication(checkNull(String.valueOf(repData[i][2])));

			List.add(bean1);
		}// end of loop
		punishMaster.setPunishList(List);
		}
	}

	/* for selecting records from the list. */
	public void calforedit(PunishMaster punishMaster,HttpServletRequest request) {
/**
 * String query="SELECT PUNISH_SALARY FROM HRMS_PUNISHMENT WHERE PUNISH_ID="+bean.getPunishId();	
		Object[][] data = getSqlModel().getSingleResult(query);
		if(data!=null && data.length>0){
			if(String.valueOf(data[0][0]).equals("Y")){
				request.setAttribute("creditData","true");
			}else{
				request.setAttribute("creditData","false");
			}
		}
 */
		String query = " SELECT  PUNISH_ID,PUNISH_NAME, NVL(FINANCIAL_IMPLICATIONS,' '),IS_MAJOR,PUNISH_SALARY FROM HRMS_PUNISHMENT   "

				+ "  where PUNISH_ID = "
				+ punishMaster.getHiddencode();
				//+ "    ORDER BY PUNISH_ID  ";

		Object[][] data = getSqlModel().getSingleResult(query);
		punishMaster.setPunishId(String.valueOf(data[0][0]));
		punishMaster.setPunishName(String.valueOf(data[0][1]));
		punishMaster.setFImplication(String.valueOf(data[0][2]));
		punishMaster.setIsMajor(String.valueOf(data[0][3]));
		if(String.valueOf(data[0][4]).equals("Y")){
			request.setAttribute("creditData","true");
		}else{
			request.setAttribute("creditData","false");
		}

	}

	/*
	 * public boolean deletePunishment(PunishMaster punishMaster, String[] code) {
	 * 
	 * 
	 * boolean result=false; boolean flag=false; if(code !=null) { for (int i =
	 * 0; i < code.length; i++) {
	 * 
	 * if(!code[i].equals("")){ logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"+code[i]);
	 * Object [][] delete = new Object [1][1]; delete [0][0] =code[i] ; flag
	 * =getSqlModel().singleExecute(getQuery(5), delete); if(flag) result=true; } } }
	 * return result;
	 *  }
	 */
	/* for deleting multiple records. */
	public String deletecheckedRecords(PunishMaster punishMaster, String[] code) {
		String result = "";
		int count = 0;

		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					boolean res;
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					getSqlModel().singleExecute(getQuery(7), delete);
					res = getSqlModel().singleExecute(getQuery(5), delete);
					if (!res)
						count++;

				}// end of if
			}// end of loop

		}// end of nested if
		if (count != 0) {
			result = "false";
		}// end of if
		else
			result = "true";

		return result;
	}
	
	
	public void getCreditDetails(PunishMaster bean){
		try{
		String query="SELECT CREDIT_CODE,CREDIT_NAME FROM HRMS_CREDIT_HEAD WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
		Object[][] data = getSqlModel().getSingleResult(query);
		ArrayList<Object> list=new ArrayList<Object>();
		if(data!=null && data.length>0){
			
			for(int i=0;i<data.length;i++){
				PunishMaster bean1=new PunishMaster();
				bean1.setCreditCode(String.valueOf(data[i][0]));
				bean1.setCreditHead(String.valueOf(data[i][1]));
				list.add(bean1);
				}
			bean.setCreditList(list);	
		}		
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public boolean chkPunishId(PunishMaster bean){
		String query="SELECT DISTINCT PUNISH_ID FROM HRMS_PUNISH_SALARY WHERE PUNISH_ID="+bean.getPunishId();
			Object[][] data = getSqlModel().getSingleResult(query);
		if(data!=null && data.length>0){
			return true;
		}else{
			return false;
		}
		
	}
	

	/*public boolean calfordelete(PunishMaster punishMaster) {

		Object[][] delete = new Object[1][1];
		delete[0][0] = punishMaster.getHiddencode();
		return getSqlModel().singleExecute(getQuery(5), delete);
	}
*/
}
