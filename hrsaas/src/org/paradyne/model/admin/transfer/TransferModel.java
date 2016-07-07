package org.paradyne.model.admin.transfer;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.transfer.TransferApplication;
import org.paradyne.lib.ModelBase;

/***
 * author:Pradeep Kumar Sahoo
 * Date:20-06-2008
 */


	public class TransferModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	org.paradyne.bean.admin.transfer.TransferApplication trfApp= null; 
	
	/**
	 * following function is called to save the record.
	 * @param bean
	 * @param empflow
	 * @return
	 */
	public boolean addTrf(TransferApplication bean,Object [][] empflow) {
		boolean result;
		String query1="(SELECT NVL(MAX(TRANSFER_CODE),0)+1 FROM HRMS_TRANSFER)";
		Object[][] data = getSqlModel().getSingleResult(query1);
		
		Object[][] addObj = new Object[1][18];
		addObj[0][0]=data[0][0];
		addObj[0][1]=bean.getEmpId();
		addObj[0][2]=bean.getType();
		addObj[0][3]=bean.getCurCentId();
		addObj[0][4]=bean.getNewCentId();
		addObj[0][5]=bean.getReason();
		addObj[0][6]=empflow[0][0];
		addObj[0][7]=bean.getRelId();
		addObj[0][8]=bean.getRelReq();
		addObj[0][9]=bean.getAppDt();
		addObj[0][10]=bean.getRelDt();
		addObj[0][11]="r";//;bean.getResult();
		addObj[0][12]=bean.getTransUnit();	
		addObj[0][13]=bean.getNewDivId();
		addObj[0][14]=bean.getNewDeptId();
		addObj[0][15]=bean.getCurDivId();
		addObj[0][16]=bean.getCurDeptId();
		if(String.valueOf(empflow[0][3]).equals("") || String.valueOf(empflow[0][3]).equals("null")){
			addObj[0][17]=String.valueOf("");
		}else{
		addObj[0][17]=String.valueOf(empflow[0][3]);
		}
		result=getSqlModel().singleExecute(getQuery(1),addObj);
		if(result){
			bean.setTrfId(String.valueOf(data[0][0]));
		}
		
		return result;
		
	}
	
	/**
	 * following function is called when the transfer record is updated.
	 * @param bean
	 * @param emp
	 * @return
	 */
   public boolean modTrf(TransferApplication bean,Object[][] emp) {
		
		if(emp==null){
			return false;
		}
		
		Object[][] modObj = new Object[1][15];
		
		modObj[0][0]=bean.getEmpId();
		
		modObj[0][1]=bean.getType();
		modObj[0][2]=bean.getNewCentId();
		
		modObj[0][3]=bean.getReason();
		modObj[0][4]=bean.getAppDt();
		bean.setFlag("false");
		modObj[0][5]=bean.getRelDt();
		modObj[0][6]=bean.getRelReq();
		modObj[0][7]=bean.getRelId();
		modObj[0][8]=bean.getResult();
		modObj[0][9]=bean.getTransUnit();
		modObj[0][10]=bean.getNewDivId();
		modObj[0][11]=bean.getNewDeptId();
		modObj[0][12]=emp[0][0];
	
		if(String.valueOf(emp[0][3]).equals("") || String.valueOf(emp[0][3]).equals("null")){
			modObj[0][13]=String.valueOf("");
		}else{
			modObj[0][13]=String.valueOf(emp[0][3]);
		}
		
		modObj[0][14]=bean.getTrfId();
		return  getSqlModel().singleExecute(getQuery(2),modObj);	
		 
		
		 
	
		}

	/**
	 * following function is called when transfer record is deleted
	 * @param bean
	 * @return
	 */
	public boolean deleteTrf(TransferApplication bean){
		Object delObj [][]= new Object [1][1];
		delObj [0][0]=bean.getTrfId();
		
		getSqlModel().singleExecute(getQuery(15),delObj);
	return getSqlModel().singleExecute(getQuery(3),delObj);
		}
	
	/**
	 * following function is called when an employee is selected from the employee list.
	 * @param trfApp
	 */
	public void getEmpRecord(TransferApplication trfApp) {
		
			Object[] tokObj=new Object[1];
			tokObj[0]=trfApp.getEmpId();
			Object[][] data = getSqlModel().getSingleResult(getQuery(18),tokObj);
			
			trfApp.setEmpId(checkNull(String.valueOf(data[0][0])));
			trfApp.setEmpToken(checkNull(String.valueOf(data[0][1])));
			trfApp.setEmpName(checkNull(String.valueOf(data[0][2])));
			trfApp.setRank(checkNull(String.valueOf(data[0][3])));
			trfApp.setCurDept(checkNull(String.valueOf(data[0][4])));
			trfApp.setCurCentId(checkNull(String.valueOf(data[0][5])));
			trfApp.setCurCent(checkNull(String.valueOf(data[0][6])));
			trfApp.setCurDiv(checkNull(String.valueOf(data[0][7])));
			trfApp.setCurDivId(checkNull(String.valueOf(data[0][8])));
			trfApp.setCurDeptId(checkNull(String.valueOf(data[0][9])));
			trfApp.setNewCentId(checkNull(String.valueOf(data[0][5])));
			trfApp.setNewCent(checkNull(String.valueOf(data[0][6])));
			trfApp.setNewDiv(checkNull(String.valueOf(data[0][7])));
			trfApp.setNewDivId(checkNull(String.valueOf(data[0][8])));
			trfApp.setNewDeptId(checkNull(String.valueOf(data[0][9])));
			trfApp.setNewDept(checkNull(String.valueOf(data[0][4])));
						
			trfApp.setFlag("true");
			trfApp.setCentFlag("false");
			trfApp.setDivFlag("false");
			trfApp.setDeptFlag("false");
			trfApp.setRelFlag("false");
			trfApp.setDateFlag("false");
			//setFlag is used to disable the popup window of forwrd to when status is pending,recommended or approved
			
		
	}
	
	/**
	 * following function is called when a transfer record is selected from the search window. 
	 * @param trfApp
	 */
	public void getTrfRec(TransferApplication trfApp) {
		
		
			Object addObj[] =new Object[1];
		
			addObj[0] =trfApp.getTrfId();
			Object[] tokObj=new Object[1];
			tokObj[0]=trfApp.getEmpId();
		
		
			Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);
			Object[][] data1=getSqlModel().getSingleResult(getQuery(16),tokObj);
		
			trfApp.setTrfId(checkNull(String.valueOf(data[0][0])));
			trfApp.setEmpId(checkNull(String.valueOf(data[0][1])));
			trfApp.setEmpToken(checkNull(String.valueOf(data1[0][0])));
			trfApp.setEmpName(checkNull(String.valueOf(data[0][3])));
			trfApp.setRank(checkNull(String.valueOf(data[0][4])));
			trfApp.setType(checkNull(String.valueOf(data[0][5])));
			trfApp.setCurDept(checkNull(String.valueOf(data[0][6])));
			if(String.valueOf(data[0][7]).equals("-")){
				
				trfApp.setCurCent("");
				
			}else {
				trfApp.setCurCent(checkNull(String.valueOf(data[0][7])));
				
			}
			
			if(String.valueOf(data[0][8]).equals("-")){
				
				trfApp.setNewCent("");
				
			}else {
				trfApp.setNewCent(checkNull(String.valueOf(data[0][8])));
				
			}
		
			
			trfApp.setAppDt(checkNull(String.valueOf(data[0][10])));
			trfApp.setRelDt(checkNull(String.valueOf(data[0][11])));
			trfApp.setRelReq(checkNull(String.valueOf(data[0][12])));
			trfApp.setRelName(checkNull(String.valueOf(data[0][13])));
			trfApp.setReason(checkNull(String.valueOf(data[0][14])));
			trfApp.setResult(checkNull(String.valueOf(data[0][15])));
			trfApp.setTransUnit(checkNull(String.valueOf(data[0][16])));
			trfApp.setLevel(checkNull(String.valueOf(data[0][17])));
			//the flag is used that once the employee has applied for transfer to forward his record,
			//he cannot modify that forwarding person again.
			
			if(checkNull(String.valueOf(data[0][9])).equals("Pending")&& !(trfApp.getLevel().equals("1")))
					trfApp.setStatus("Forwarded");
				else
				trfApp.setStatus(checkNull(String.valueOf(data[0][9])));
			if(String.valueOf(data[0][9]).equals("Pending") || String.valueOf(data[0][9]).equals("Approved") || String.valueOf(data[0][9]).equals("Recommended"))
			{
				trfApp.setFlag("false");
			} else {
				 trfApp.setFlag("true");
				
			}
			
			trfApp.setNewDeptId(checkNull(String.valueOf(data[0][18])));
			trfApp.setNewDept(checkNull(String.valueOf(data[0][19])));
			trfApp.setNewDivId(checkNull(String.valueOf(data[0][20])));
			trfApp.setNewDiv(checkNull(String.valueOf(data[0][21])));
			trfApp.setCurDiv(checkNull(String.valueOf(data[0][22])));
			trfApp.setNewCentId(checkNull(String.valueOf(data[0][23])));
			trfApp.setGenEmpFlag("true");
		
	
	}
	

/**
 * following function is called when view link is clicked in the transfer approval form. 
 * @param trfApp
 */
	public void getTrfRecord(TransferApplication trfApp) {
				
			Object addObj[] =new Object[1];
			addObj[0] =trfApp.getTrfId();
		
			Object[] tokObj=new Object[1];
			tokObj[0]=trfApp.getEmpId();
		
		
			Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);
			Object[][] data1=getSqlModel().getSingleResult(getQuery(16),tokObj);
			
			trfApp.setTrfId(checkNull(String.valueOf(data[0][0])));
			trfApp.setEmpId(checkNull(String.valueOf(data[0][1])));
			trfApp.setEmpToken(checkNull(String.valueOf(data1[0][0])));
			trfApp.setEmpName(checkNull(String.valueOf(data[0][3])));
			trfApp.setRank(checkNull(String.valueOf(data[0][4])));
			trfApp.setType(checkNull(String.valueOf(data[0][5])));
			trfApp.setCurDept(checkNull(String.valueOf(data[0][6])));
			if(String.valueOf(data[0][7]).equals("-")){
				
				trfApp.setCurCent("");
				
			}else {
				trfApp.setCurCent(checkNull(String.valueOf(data[0][7])));
				
			}
			
			if(String.valueOf(data[0][8]).equals("-")){
				
				trfApp.setNewCent("");
				
			}else {
				trfApp.setNewCent(checkNull(String.valueOf(data[0][8])));
				
			}
		
		
			trfApp.setAppDt(checkNull(String.valueOf(data[0][10])));
			trfApp.setRelDt(checkNull(String.valueOf(data[0][11])));
			trfApp.setRelReq(checkNull(String.valueOf(data[0][12])));
		
				trfApp.setRelReqFlag("true");
	
			trfApp.setRelName(checkNull(String.valueOf(data[0][13])));
		
			trfApp.setReason(checkNull(String.valueOf(data[0][14])));
	
			trfApp.setResult(checkNull(String.valueOf(data[0][15])));
			trfApp.setTransUnit(checkNull(String.valueOf(data[0][16])));
			trfApp.setLevel(checkNull(String.valueOf(data[0][17])));
			//the flag is used that once the employee has applied for transfer to forward his record,
			//he cannot modify that forwarding person again.
		
				if(checkNull(String.valueOf(data[0][9])).equals("Pending")&& !(trfApp.getLevel().equals("1")))
					trfApp.setStatus("Forwarded");
				else
				trfApp.setStatus(checkNull(String.valueOf(data[0][9])));
			if(String.valueOf(data[0][9]).equals("Pending") || String.valueOf(data[0][9]).equals("Approved") || String.valueOf(data[0][9]).equals("Recommended"))
			{
				trfApp.setFlag("false");
			} else {
				trfApp.setFlag("true");
				
			}
			
			trfApp.setNewDeptId(checkNull(String.valueOf(data[0][18])));
			trfApp.setNewDept(checkNull(String.valueOf(data[0][19])));
		
			trfApp.setNewDivId(checkNull(String.valueOf(data[0][20])));
			trfApp.setNewDiv(checkNull(String.valueOf(data[0][21])));
			trfApp.setCurDiv(checkNull(String.valueOf(data[0][22])));
			trfApp.setNewCentId(checkNull(String.valueOf(data[0][23])));
			trfApp.setGenEmpFlag("true");
		

	}
	
	/*
	 * Following function is called when to display the system date
	 */
	public void getApplicationDate(TransferApplication trfApp) {
		
		 Object[][] data= getSqlModel().getSingleResult(getQuery(24),new Object[]{});
		 trfApp.setAppDt(String.valueOf(data[0][0]));
		
	}
	
	
	/**
	 * following function is called when the view button link is clicked in the transfer approval 
	 * form to display the list of employees who has approved or rejected the list of records 
	 * @param trfApp
	 */
	
	public void getTableHistoryDetails(TransferApplication trfApp) {
		
		
		Object[] bean = new Object[1];
	
		ArrayList<Object> list = new ArrayList<Object>();		
		bean[0] = trfApp.getTrfId();
		
		
		Object[][] result = getSqlModel().getSingleResult(getQuery(10),bean);

		for (int i = 0; i < result.length; i++) {
			TransferApplication bean3 = new TransferApplication();
			bean3.setEmp(checkNull(String.valueOf(result[i][0])));//Employee Name
			bean3.setApprDate(checkNull(String.valueOf(result[i][1])));//Approved date			
			bean3.setRemark(checkNull(String.valueOf(result[i][2])));	//remark		
			bean3.setTrfStatus(String.valueOf(result[i][3]));//Transfer Status
			
			list.add(bean3);
		}
		trfApp.setTrnAppList(list);
		
		
	}

	/*
	 * Following function is called when report button is clicked in Transfer Application form 
	 */
	public void  generateReportPdf(TransferApplication trfApp,HttpServletResponse response){
		
		String reportType=new String("Pdf");	
		
		String reportName="Transfer Application Report";		
		String comname="Glodyne Technoserve Ltd.";
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,reportName);
		try{

			 Object[][] data= getSqlModel().getSingleResult(getQuery(24),new Object[]{});
			 /*
			  * Following query is used to select the name,id,new div,new dept,new branch etc.
			  */
			 String query="SELECT EMP_TOKEN,NVL(TITLE_NAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')|| ' ' ||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' ||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '), "
							+" NVL(HRMS_CENTER.CENTER_NAME,' '), NVL(NEWCENT.CENTER_NAME,' '),NVL(HRMS_DIVISION.DIV_NAME,' '),NVL(NEWDIV.DIV_NAME,' '),NVL(HRMS_DEPT.DEPT_NAME,' '),NEWDEPT.DEPT_NAME,NVL(RANK_NAME,' '),NVL(TRANSFER_DETAILS,' ')  "
							+" FROM HRMS_TRANSFER "
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TRANSFER.EMP_ID)"
							+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_TRANSFER.TRANSFER_OLD_CENTER) "
							+" LEFT JOIN HRMS_CENTER NEWCENT ON(NEWCENT.CENTER_ID=HRMS_TRANSFER.TRANSFER_NEW_CENTER)"
							+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_TRANSFER.TRANSFER_OLD_DEPT)"
							+" LEFT JOIN HRMS_DEPT NEWDEPT ON(NEWDEPT.DEPT_ID=HRMS_TRANSFER.TRANSFER_DEPT)"
							+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_TRANSFER.TRANSFER_OLD_DIV)"
							+" LEFT JOIN HRMS_DIVISION NEWDIV ON(NEWDIV.DIV_ID=HRMS_TRANSFER.TRANSFER_DIVISION)"
							+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
							+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
							+" WHERE HRMS_TRANSFER.TRANSFER_CODE="+trfApp.getTrfId()+" AND ROWNUM=1 ";
		
		Object empDet[][] = getSqlModel().getSingleResult(query); 		
		
		
		String[] msg={"Transfer Application Report","","","\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDate:"+String.valueOf(data[0][0])};
		int[] st={6,0,0,2};
		rg.addFormatedText(msg, st, 0,1,0);
		rg.addText("\n\n",0,0,0);
		
		Object[][] empData=new Object[5][6];
		
		empData[0][0]="Employee Id ";empData[0][1]=":";empData[0][2]=String.valueOf(empDet[0][0]);
		
		empData[0][3]="Name ";empData[0][4]=":";empData[0][5]=String.valueOf(empDet[0][1]);
		
		empData[1][0]="Branch ";empData[1][1]=":";empData[1][2]=String.valueOf(empDet[0][2]);
		empData[1][3]="New Branch ";empData[1][4]=":";empData[1][5]=String.valueOf(empDet[0][3]);
		empData[2][0]="Division ";empData[2][1]=":";empData[2][2]=String.valueOf(empDet[0][4]);
		empData[2][3]="New Division ";empData[2][4]=":";empData[2][5]=String.valueOf(empDet[0][5]);
		empData[3][0]="Department ";empData[3][1]=":";empData[3][2]=String.valueOf(empDet[0][6]);
		empData[3][3]="New Department ";empData[3][4]=":";
		if(String.valueOf(empDet[0][7]).equals("null")){
			empData[3][5]="";
			
		}else{
			empData[3][5]=String.valueOf(empDet[0][7]);
			
			
		}
		empData[4][0]="Designation ";empData[4][1]=":";empData[4][2]=String.valueOf(empDet[0][8]);
		empData[4][3]="";empData[4][4]="";empData[4][5]="";
		Object[][] reason=new Object[1][3];
		reason[0][0]="Reason";reason[0][1]=":";reason[0][2]=String.valueOf(empDet[0][9]);
		int[] cellwidth={15,5,20,15,5,20};
		int[] cellwidth1={15,5,60};
		int[] alignment1={0,0,0};
		int[] alignment={0,0,0,0,0,0};
		
	
		
		rg.tableBodyNoBorder(empData,cellwidth,alignment);
		rg.tableBodyNoBorder(reason,cellwidth1,alignment1);
		
		
		String sql= "SELECT DISTINCT NVL(TITLE_NAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')|| ' ' ||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')|| ' ' ||NVL(HRMS_EMP_OFFC.EMP_LNAME,' '),"
				+" CASE WHEN TRANSFER_PATH_STATUS='P' THEN 'Pending' WHEN TRANSFER_PATH_STATUS='A' THEN 'Approved' WHEN TRANSFER_STATUS='R' THEN 'Rejected' ELSE ' ' END,"
				+" nvl(HRMS_TRANSFER_PATH.APPR_COMMENT,''),"	
				+" TO_CHAR(TRANSFER_APPR_DATE,'DD-MM-YYYY'),hrms_transfer_path.transfer_path_id,TRANSFER_LEVEL FROM HRMS_EMP_OFFC "//INNER JOIN HRMS_TRANSFER ON(HRMS_TRANSFER.TRANSFER_APPROVED_BY=HRMS_EMP_OFFC.EMP_ID)"
				+" INNER JOIN HRMS_TRANSFER_PATH ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TRANSFER_PATH.TRANSFER_APPR_ID)"
				+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+" inner join hrms_transfer on(hrms_transfer.transfer_code=hrms_transfer_path.transfer_id)"
				+" WHERE TRANSFER_CODE="+trfApp.getTrfId()+" order by hrms_transfer_path.transfer_path_id";
	
		Object param[][]= getSqlModel().getSingleResult(sql); 
		
		Object[][] param1=new Object[param.length][5];
		int s=1;
		for(int j=0;j< param.length;j++) {
			param1[j][0]=s++;//Serial No.
			param1[j][1]=param[j][0];//Approver Name
		
			param1[j][2]=param[j][1];//Status
						
			param1[j][3]=param[j][2];//Comment
			param1[j][4]=param[j][3];//Approve Date
		}//End of for loop
		
		String[] colNames={"Sr. No.","Approver Name","Status","Approver's Comment","Approved/Rejected Date"};		
		int [] cellWidth={6,17,10,12,14};		
		int [] cellAlign={1,1,1,1,1};	
		rg.addText("\n\n",0,0,0);
		rg.tableBody(colNames, param1, cellWidth, cellAlign);	
		
		
						
			rg.genHeader(comname);
			
			rg.createReport(response);
			}catch(Exception e) {
				e.printStackTrace();
			logger.error(e.getMessage());
			}
		}
	
	
	/*
	 * Following function is called when a general user makes login.
	 */
	
	public void getLoginTransfer(String empId, TransferApplication trfApp) {
		Object[] bean = new Object[1];

		bean[0] = empId;
		
		
		String query="SELECT TRANSFER_CODE,HRMS_TRANSFER.EMP_ID,EMP_TOKEN,(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME),HRMS_RANK.RANK_NAME,TRANSFER_TYPE, "
			+" HRMS_DEPT.DEPT_NAME,HRMS_CENTER.CENTER_NAME ,NEWCENT.CENTER_NAME,CASE WHEN TRANSFER_STATUS='P' THEN 'Pending' WHEN TRANSFER_STATUS='A' THEN 'Approved' WHEN TRANSFER_STATUS='R' THEN 'Rejected' ELSE 'Recommended' END  ,"
			+" TO_CHAR(TRANSFER_APPLICATION_DATE,'DD-MM-YYYY'),  TO_CHAR(TRANSFER_RELIEVING_DATE,'DD-MM-YYYY'),TRANSFER_RELIEVER_REQUIRED,(RELTITLE.TITLE_NAME||' '||RELNAME.EMP_FNAME || ' ' || RELNAME.EMP_MNAME || ' ' || RELNAME.EMP_LNAME), "
			+" TRANSFER_DETAILS,TRANSFER_RESULT,NVL(TRANSFER_UNIT,''),TRANSFER_LEVEL,NEWDEPT.DEPT_ID,NEWDEPT.DEPT_NAME,NEWDIV.DIV_ID,NEWDIV.DIV_NAME,HRMS_DIVISION.DIV_NAME,NEWCENT.CENTER_ID FROM HRMS_TRANSFER  INNER JOIN HRMS_EMP_OFFC ON(HRMS_TRANSFER.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
			+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_TRANSFER.TRANSFER_OLD_CENTER)  " 
			+" LEFT JOIN HRMS_CENTER NEWCENT ON(NEWCENT.CENTER_ID=HRMS_TRANSFER.TRANSFER_NEW_CENTER) LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_TRANSFER.TRANSFER_OLD_DEPT)" +
			" LEFT JOIN HRMS_DEPT NEWDEPT ON(NEWDEPT.DEPT_ID=HRMS_TRANSFER.TRANSFER_DEPT) "
			+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_TRANSFER.TRANSFER_OLD_DIV) LEFT JOIN HRMS_DIVISION NEWDIV ON(NEWDIV.DIV_ID=HRMS_TRANSFER.TRANSFER_DIVISION) LEFT JOIN HRMS_EMP_OFFC  RELNAME ON(RELNAME.EMP_ID=HRMS_TRANSFER.TRANSFER_RELIEVER_EMPID)"
			+" LEFT JOIN HRMS_TITLE RELTITLE ON(RELTITLE.TITLE_CODE=RELNAME.EMP_TITLE_CODE)WHERE HRMS_TRANSFER.EMP_ID = ?" ; 
		
		
	
		Object[][] data = getSqlModel().getSingleResult(query, bean);
		Object[][] data1=getSqlModel().getSingleResult(getQuery(16),bean);
				
				trfApp.setTrfId(checkNull(String.valueOf(data[0][0])));
				trfApp.setEmpId(checkNull(String.valueOf(data[0][1])));
				trfApp.setEmpToken(checkNull(String.valueOf(data1[0][0])));
				trfApp.setEmpName(checkNull(String.valueOf(data[0][3])));
				trfApp.setRank(checkNull(String.valueOf(data[0][4])));
				trfApp.setType(checkNull(String.valueOf(data[0][5])));
				trfApp.setCurDept(checkNull(String.valueOf(data[0][6])));
				
				if(String.valueOf(data[0][7]).equals("-")){
					
					trfApp.setCurCent("");
					
				}else {
					trfApp.setCurCent(checkNull(String.valueOf(data[0][7])));
					
				}
			
				if(String.valueOf(data[0][8]).equals("-")){
					
					trfApp.setNewCent("");
					
				}else {
					trfApp.setNewCent(checkNull(String.valueOf(data[0][8])));
					
				}
				trfApp.setStatus(checkNull(String.valueOf(data[0][9])));
				trfApp.setAppDt(checkNull(String.valueOf(data[0][10])));
				trfApp.setRelDt(checkNull(String.valueOf(data[0][11])));
				trfApp.setRelReq(checkNull(String.valueOf(data[0][12])));
				trfApp.setRelName(checkNull(String.valueOf(data[0][13])));
				trfApp.setReason(checkNull(String.valueOf(data[0][14])));
				trfApp.setTransUnit(checkNull(String.valueOf(data[0][16])));
				trfApp.setLevel(checkNull(String.valueOf(data[0][17])));
				trfApp.setIsForApprove("true");
				if(checkNull(String.valueOf(data[0][9])).equals("Pending")&& !(trfApp.getLevel().equals("1")))
					trfApp.setStatus("Forwarded");
				else
				trfApp.setStatus(checkNull(String.valueOf(data[0][9])));
				
				if(String.valueOf(data[0][9]).equals("Pending") || String.valueOf(data[0][9]).equals("Approved") || String.valueOf(data[0][9]).equals("Recommended"))
				{
					trfApp.setFlag("false");
				} else {
					trfApp.setFlag("true");
					
				}
				trfApp.setNewDeptId(checkNull(String.valueOf(data[0][18])));
				trfApp.setNewDept(checkNull(String.valueOf(data[0][19])));
				trfApp.setNewDivId(checkNull(String.valueOf(data[0][20])));
				trfApp.setNewDiv(checkNull(String.valueOf(data[0][21])));
				trfApp.setCurDiv(checkNull(String.valueOf(data[0][22])));
				trfApp.setNewCentId(checkNull(String.valueOf(data[0][23])));
				
		
		
	}

	
	/*
	 * Following function is called when an employee is selected to show the details of the employee.
	 */
	
	public void  getEmployeeDetails(String empId, TransferApplication trfApp)
	{
	Object[] beanObj = new Object[1];
	beanObj[0] =empId ;
	String query =" SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN, "
				+" HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,HRMS_RANK.RANK_NAME, " 
				+" HRMS_DEPT.DEPT_NAME,HRMS_CENTER.CENTER_ID,HRMS_CENTER.CENTER_NAME,HRMS_DEPT.DEPT_ID,HRMS_DIVISION.DIV_ID,NVL(DIV_NAME,' ') "
				+" FROM HRMS_EMP_OFFC "
				+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
				+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
				+" LEFT JOIN HRMS_DIVISION ON(HRMS_EMP_OFFC.EMP_DIV=HRMS_DIVISION.DIV_ID)"
				+" WHERE HRMS_EMP_OFFC.EMP_ID =?";
	Object[][] values = getSqlModel().getSingleResult(query, beanObj);
	
	
			trfApp.setEmpId(checkNull(String.valueOf(values[0][0])));
			trfApp.setEmpToken(checkNull(String.valueOf(values[0][1])));
			trfApp.setEmpName(checkNull(String.valueOf(values[0][2])));
			trfApp.setRank(checkNull(String.valueOf(values[0][3])));
			trfApp.setCurDept(checkNull(String.valueOf(values[0][4])));
			trfApp.setNewDept(checkNull(String.valueOf(values[0][4])));
			trfApp.setCurCentId(checkNull(String.valueOf(values[0][5])));
			trfApp.setNewCentId(checkNull(String.valueOf(values[0][5])));
			trfApp.setCurCent(checkNull(String.valueOf(values[0][6])));
			trfApp.setNewCent(checkNull(String.valueOf(values[0][6])));
			trfApp.setCurDeptId(checkNull(String.valueOf(values[0][7])));	
			trfApp.setNewDeptId(checkNull(String.valueOf(values[0][7])));
			trfApp.setCurDivId(checkNull(String.valueOf(values[0][8])));
			trfApp.setNewDivId(checkNull(String.valueOf(values[0][8])));
			trfApp.setCurDiv(checkNull(String.valueOf(values[0][9])));
			trfApp.setNewDiv(checkNull(String.valueOf(values[0][9])));
			trfApp.setFlag("true");
			trfApp.setShowFlag("true");
	//trfApp.setGenEmpFlag("false");
	}
	
	
	
	public String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "";
		}
		else{
			return result;
		}
	}
	
	/*
	 * Following function is called to check whether the record has been locked or not.
	 */
	public Object[][] getLock(TransferApplication bean){
		Object[] beanObj = new Object[1];
		beanObj[0] =bean.getTrfId();
		
		String query=" SELECT TRANSFER_LOCK FROM HRMS_TRANSFER WHERE TRANSFER_CODE="+bean.getTrfId();
		Object[][] data =getSqlModel().getSingleResult(query); 
		
		return data;
	}
	
	/*
	 * Following function is called to update the department,division and branch in HRMS_EMP_OFFC table
	 * after a record has been approved. 
	 */
	public boolean updateOfficialDetails(TransferApplication bean){
		
		
		String query="SELECT EMP_ID FROM HRMS_TRANSFER WHERE TRANSFER_STATUS='A' AND TRANSFER_CODE="+bean.getTrfId();
		Object param[][]= getSqlModel().getSingleResult(query); 
		boolean result=false;
		if(param!=null && param.length >0){
		Object[][] data=new Object[1][4];
		
		
		String statusOld=" SELECT TRANSFER_OLD_DEPT,TRANSFER_OLD_DIV,TRANSFER_OLD_CENTER "

		+"	FROM  HRMS_TRANSFER WHERE TRANSFER_CODE="+bean.getTrfId();
		Object[][] oldVal= getSqlModel().getSingleResult(statusOld);
		
		if(bean.getNewCentId().equals("") || bean.getNewCentId().equals("null")){
			data[0][0]=String.valueOf(oldVal[0][2]);
		}else {
		data[0][0]=bean.getNewCentId();
		}
		if(bean.getNewDeptId().equals("") || bean.getNewDeptId().equals("null")){
			data[0][1]=String.valueOf(oldVal[0][0]);
		}else{
			data[0][1]=bean.getNewDeptId();
		}
		if(bean.getNewDivId().equals("") || bean.getNewDivId().equals("null")){
			data[0][2]=String.valueOf(oldVal[0][1]);
		}else {
			data[0][2]=bean.getNewDivId();
		}
		
		data[0][3]=String.valueOf(param[0][0]);
		Object[][] para=new Object[1][2];
		para[0][0]="L";
		para[0][1]=bean.getTrfId();
		
		
		getSqlModel().singleExecute(getQuery(25),data);	
		result=getSqlModel().singleExecute(getQuery(26),para);	
	
		}
		return result;
		
		
	}
	
	
	}
