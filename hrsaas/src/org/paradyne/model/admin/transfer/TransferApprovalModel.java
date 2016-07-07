package org.paradyne.model.admin.transfer;
import java.util.ArrayList;

 import org.paradyne.lib.ModelBase;
import org.paradyne.bean.admin.transfer.TransferApproval;


import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @author pradeep kumar
 *Date:28-06-2008
 */


public class TransferApprovalModel extends ModelBase {  
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	/*
	 * Following function is called to display any pending records for approval when the user makes login.
	 */
	
	public void getLoginTrnRecord(TransferApproval bean,HttpServletRequest request,String status){
		/*
		 * Following query is used to display the records.
		 */
		Object[][] data=null;
		if(status.equals("A")){
		
			String query= "  SELECT DISTINCT TRANSFER_CODE,HRMS_TRANSFER.EMP_ID, "
				 +" HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME, NVL(TO_CHAR(TRANSFER_APPLICATION_DATE,'DD-MM-YYYY'),' '),"
		         +" NVL(OLDCENT.CENTER_NAME,' '),NVL(NEWCENT.CENTER_NAME,' '), "
		         +" HRMS_TRANSFER.TRANSFER_STATUS,EMP_TOKEN,TRANSFER_LEVEL,NVL(TRANSFER_DEPT,''),NVL(TRANSFER_DIVISION,''),NVL(TRANSFER_NEW_CENTER,'') "
		         +" FROM HRMS_TRANSFER  "
		         +" INNER JOIN 	HRMS_EMP_OFFC ON(HRMS_TRANSFER.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"   
		         +" LEFT JOIN HRMS_CENTER OLDCENT ON(OLDCENT.CENTER_ID=HRMS_TRANSFER.TRANSFER_OLD_CENTER) "  
		         +" LEFT JOIN HRMS_CENTER NEWCENT ON(NEWCENT.CENTER_ID=HRMS_TRANSFER.TRANSFER_NEW_CENTER)  " 
		         +" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)  "
		         +" WHERE (HRMS_TRANSFER.TRANSFER_APPROVED_BY ="+bean.getUserEmpId() +" OR TRANSFER_ALT_APPRV_ID="+bean.getUserEmpId()+") AND (TRANSFER_STATUS ='A')  ORDER BY NVL(TO_CHAR(TRANSFER_APPLICATION_DATE,'DD-MM-YYYY'),' ') DESC ";
			data = getSqlModel().getSingleResult(query);
		}else{
			String query= "  SELECT DISTINCT TRANSFER_CODE,HRMS_TRANSFER.EMP_ID, "
				 +" HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME, NVL(TO_CHAR(TRANSFER_APPLICATION_DATE,'DD-MM-YYYY'),' '),"
		         +" NVL(OLDCENT.CENTER_NAME,' '),NVL(NEWCENT.CENTER_NAME,' '), "
		         +" HRMS_TRANSFER.TRANSFER_STATUS,EMP_TOKEN,TRANSFER_LEVEL,NVL(TRANSFER_DEPT,''),NVL(TRANSFER_DIVISION,''),NVL(TRANSFER_NEW_CENTER,'') "
		         +" FROM HRMS_TRANSFER  "
		         +" INNER JOIN 	HRMS_EMP_OFFC ON(HRMS_TRANSFER.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"   
		         +" LEFT JOIN HRMS_CENTER OLDCENT ON(OLDCENT.CENTER_ID=HRMS_TRANSFER.TRANSFER_OLD_CENTER) "  
		         +" LEFT JOIN HRMS_CENTER NEWCENT ON(NEWCENT.CENTER_ID=HRMS_TRANSFER.TRANSFER_NEW_CENTER)  " 
		         +" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)  "
		         +" WHERE (HRMS_TRANSFER.TRANSFER_APPROVED_BY ="+bean.getUserEmpId() +" OR TRANSFER_ALT_APPRV_ID="+bean.getUserEmpId()+") AND TRANSFER_STATUS ='"+status+"'  ORDER BY NVL(TO_CHAR(TRANSFER_APPLICATION_DATE,'DD-MM-YYYY'),' ') DESC ";
			data = getSqlModel().getSingleResult(query);
		}
			Object[] param= new Object[2];
			param[0]=bean.getUserEmpId();
			param[1]=status;
			
			
			
			
			
			ArrayList<Object> appList = new ArrayList<Object>();
			
			for(int i=0; i<data.length; i++) {	
			TransferApproval bean1= new TransferApproval();
			
			
				bean1.setEmpToken(String.valueOf(data[i][7]));//Employee Token
				bean1.setTrfId(String.valueOf(data[i][0]));//Transfer code
				bean1.setEmpId(String.valueOf(data[i][1]));//Emp Id
				bean1.setEmpName(String.valueOf(data[i][2]));//Employee Name
				bean1.setAppDt(String.valueOf(data[i][3]));//Application Date
				bean1.setStatusList(status);//Status of the record.
				bean1.setLevel(String.valueOf(data[i][8]));
				if(String.valueOf(data[i][4]).equals("-")){//Branch
					
					bean1.setCurCent("");
					
				}else {
					bean1.setCurCent(String.valueOf(data[i][4]));
					
				}
				
				if(String.valueOf(data[i][5]).equals("-")){//New Branch
					
					bean1.setNewCent("");
					
				}else {
					bean1.setNewCent(String.valueOf(data[i][5]));
					
				}
				bean1.setNewDeptId(String.valueOf(data[i][9]));
				bean1.setNewDivId(String.valueOf(data[i][10]));
				bean1.setNewCentId(String.valueOf(data[i][11]));
			
			
				appList.add(bean1);
		}
			bean.setTrfList(appList);
			
			if(appList.size()!=0){
				bean.setListLength("1");
				bean.setNoData("false");
				//setNoData if set to false will not display the meassage 'No Data to Display' in Jsp
			
			}else 	{
				bean.setListLength("0");
				bean.setNoData("true");
				//setNoData if set to true will display the meassage 'No Data to Display' in Jsp
				
			}
	
}
	/*
	 * Following function is called when Pending List,Approved List or Rejected List button is clicked in the Jsp page.
	 */
	public void getTrnRecord(TransferApproval bean,HttpServletRequest request,String status){
		
		Object empObject[] = new Object[3];
		empObject[2]	   =	bean.getUserEmpId();
		empObject[1]	   =	bean.getUserEmpId();
		empObject[0]	   =   status; 
		Object empObject1[] = new Object[2];
		empObject1[0]	   =	bean.getUserEmpId();
		empObject1[1]	   =	bean.getUserEmpId();
		Object[][] data =null;// getSqlModel().getSingleResult(query);
			if(status.equals("A")){
			      String query= " SELECT DISTINCT TRANSFER_CODE,HRMS_TRANSFER.EMP_ID, "
						 +" HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME, NVL(TO_CHAR(TRANSFER_APPLICATION_DATE,'DD-MM-YYYY'),' '),"
			             +" NVL(OLDCENT.CENTER_NAME,' '),NVL(NEWCENT.CENTER_NAME,' '), "
			             +" HRMS_TRANSFER.TRANSFER_STATUS,EMP_TOKEN,TRANSFER_LEVEL,NVL(TRANSFER_DEPT,''),NVL(TRANSFER_DIVISION,''),NVL(TRANSFER_NEW_CENTER,''),TRANSFER_ALT_APPRV_ID "
			             +" FROM HRMS_TRANSFER  "
			             +" INNER JOIN 	HRMS_EMP_OFFC ON(HRMS_TRANSFER.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"   
			             +" LEFT JOIN HRMS_CENTER OLDCENT ON(OLDCENT.CENTER_ID=HRMS_TRANSFER.TRANSFER_OLD_CENTER) "  
			             +" LEFT JOIN HRMS_CENTER NEWCENT ON(NEWCENT.CENTER_ID=HRMS_TRANSFER.TRANSFER_NEW_CENTER)  " 
			             +" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)  "
			             +" WHERE (HRMS_TRANSFER.TRANSFER_APPROVED_BY ="+bean.getUserEmpId() +" OR TRANSFER_ALT_APPRV_ID="+bean.getUserEmpId()+") AND (TRANSFER_STATUS ='A')  ORDER BY NVL(TO_CHAR(TRANSFER_APPLICATION_DATE,'DD-MM-YYYY'),' ') DESC ";
			       data = getSqlModel().getSingleResult(query);
			}else {
				
				 String query= "  SELECT DISTINCT TRANSFER_CODE,HRMS_TRANSFER.EMP_ID, "
					 +" HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME || ' ' || HRMS_EMP_OFFC.EMP_MNAME || ' ' || HRMS_EMP_OFFC.EMP_LNAME, NVL(TO_CHAR(TRANSFER_APPLICATION_DATE,'DD-MM-YYYY'),' '),"
		             +" NVL(OLDCENT.CENTER_NAME,' '),NVL(NEWCENT.CENTER_NAME,' '), "
		             +" HRMS_TRANSFER.TRANSFER_STATUS,EMP_TOKEN,TRANSFER_LEVEL,NVL(TRANSFER_DEPT,''),NVL(TRANSFER_DIVISION,''),NVL(TRANSFER_NEW_CENTER,''),TRANSFER_ALT_APPRV_ID "
		             +" FROM HRMS_TRANSFER  "
		             +" INNER JOIN 	HRMS_EMP_OFFC ON(HRMS_TRANSFER.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"   
		             +" LEFT JOIN HRMS_CENTER OLDCENT ON(OLDCENT.CENTER_ID=HRMS_TRANSFER.TRANSFER_OLD_CENTER) "  
		             +" LEFT JOIN HRMS_CENTER NEWCENT ON(NEWCENT.CENTER_ID=HRMS_TRANSFER.TRANSFER_NEW_CENTER)  " 
		             +" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)  "
		             +" WHERE  (HRMS_TRANSFER.TRANSFER_APPROVED_BY ="+bean.getUserEmpId() +" OR TRANSFER_ALT_APPRV_ID="+bean.getUserEmpId()+") AND TRANSFER_STATUS ='"+status+"'  ORDER BY NVL(TO_CHAR(TRANSFER_APPLICATION_DATE,'DD-MM-YYYY'),' ') DESC ";
				 	data = getSqlModel().getSingleResult(query);
			}
					
					
					ArrayList<Object> appList = new ArrayList<Object>();
					
					for(int i=0; i<data.length; i++) {	
					TransferApproval bean1= new TransferApproval();
					
				
					bean1.setEmpToken(String.valueOf(data[i][7]));//Token No.
					bean1.setTrfId(String.valueOf(data[i][0]));//Transfer Id
					bean1.setEmpId(String.valueOf(data[i][1]));//Emp Id
					bean1.setEmpName(String.valueOf(data[i][2]));//Name
					bean1.setAppDt(String.valueOf(data[i][3]));//Application Date
					bean1.setStatusList(status);//Status List
					bean1.setLevel(String.valueOf(data[i][8]));//Level
					if(String.valueOf(data[i][4]).equals("-")){
						
						bean1.setCurCent("");
						
					}else {
						bean1.setCurCent(String.valueOf(data[i][4]));
						
					}
					
					if(String.valueOf(data[i][5]).equals("-")){
						
						bean1.setNewCent("");
						
					}else {
						bean1.setNewCent(String.valueOf(data[i][5]));
						
					}
					bean1.setNewDeptId(String.valueOf(data[i][9]));
					bean1.setNewDivId(String.valueOf(data[i][10]));
					bean1.setNewCentId(String.valueOf(data[i][11]));
			
					
					if(status.equals("A")){
						bean1.setStatusNew("Approved");
						bean1.setStatusFlag("true");
					}else if(status.equals("R")){
						bean1.setStatusNew("Rejected");
						bean1.setStatusFlag("true");
					}
					
					/*String pathQuery="SELECT NVL(APPR_COMMENT,' ') FROM HRMS_TRANSFER_PATH inner join hrms_transfer on(hrms_transfer.transfer_code=hrms_transfer_path.TRANSFER_ID) WHERE (TRANSFER_APPR_ID="+bean.getUserEmpId() 
					+" OR TRANSFER_ALT_APPRV_ID="+bean.getUserEmpId()+" or TRANSFER_APPROVED_BY="+bean.getUserEmpId()+" ) AND transfer_id= ?";
					*/
					Object [][] comment=null;
					if(String.valueOf(data[i][12]).equals("") || String.valueOf(data[i][12]).equals("null")){
						String pathQuery="SELECT NVL(APPR_COMMENT,' ') FROM HRMS_TRANSFER_PATH "//inner join hrms_transfer on(hrms_transfer.transfer_code=hrms_transfer_path.TRANSFER_ID)"
							+" WHERE (TRANSFER_APPR_ID="+bean.getUserEmpId()+") AND transfer_id="+String.valueOf(data[i][0]);
							comment=getSqlModel().getSingleResult(pathQuery);
					}else{
					String pathQuery="SELECT NVL(APPR_COMMENT,' ') FROM HRMS_TRANSFER_PATH "//inner join hrms_transfer on(hrms_transfer.transfer_code=hrms_transfer_path.TRANSFER_ID)"
					+" WHERE (TRANSFER_APPR_ID="+bean.getUserEmpId()+" OR  TRANSFER_ALT_APPRV_ID="+String.valueOf(data[i][12])+") AND transfer_id="+String.valueOf(data[i][0]);
					 		comment=getSqlModel().getSingleResult(pathQuery);
					}
					//	+" WHERE TRANSFER_APPR_ID IN("+bean.getUserEmpId()+","+String.valueOf(data[i][12])+") AND transfer_id="+String.valueOf(data[i][0]);
					Object[] param= new Object[2];
					param[0]=bean.getUserEmpId();
					param[1]=bean.getTrfId();
					
					//Object [][] comment=getSqlModel().getSingleResult(pathQuery);//,new Object []{String.valueOf(data[i][0])});
					if(comment==null || comment.length==0 || comment.equals(null) ){
						
						bean1.setRemark("");
					}else {
					
						bean1.setRemark(String.valueOf(comment[0][0]));
					}
				
					appList.add(bean1);
			 }//End of for loop
					bean.setTrfList(appList);
					if(appList.size()!=0){
						bean.setListLength("1");
						bean.setNoData("false");
					}else 	{
						bean.setListLength("0");
						bean.setNoData("true");
						
					}
		
	}
	

	/*
	 * Following function is called to forward the record for approval to the next person defined in the Reporting structure. 
	 */
	
	public void forward(TransferApproval bean, String status,String trfNo,String rmk) {
		String query1="SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object[][] data = getSqlModel().getSingleResult(query1);
		
		Object [][] changeStatus = new Object[1][6];
					changeStatus[0][0] = trfNo;//Transfer Code
					String query="SELECT TRANSFER_ALT_APPRV_ID FROM HRMS_TRANSFER WHERE TRANSFER_CODE="+trfNo;
					Object[][] data1 = getSqlModel().getSingleResult(query);
					changeStatus[0][1]=bean.getUserEmpId();//Approver id the person who approves the record.
					changeStatus[0][2]=String.valueOf(data[0][0]);//Current Date
					changeStatus[0][3]=rmk;//Remark 
					changeStatus[0][4]=status;//Status whether approved or rejected.
					if(String.valueOf(data1[0][0]).equals("") || String.valueOf(data1[0][0]).equals("null")){
						changeStatus[0][5]=String.valueOf("");
					}else{
						changeStatus[0][5]=String.valueOf(data1[0][0]);
					}
			
				if(String.valueOf(status).equals("R")){
					Object[][]reject=new Object[1][2];
					reject[0][0]=String.valueOf(status);
					reject[0][1]=String.valueOf(trfNo);
					/*
					 * Following query is used to update the status of the record in Hrms_transfer table
					 */
					getSqlModel().singleExecute(getQuery(7), reject);
					
					
				}//End if
			
				/*
				 * Following query is called to insert comment,status,approved person id,transfer code in hrms_tranfer_path table upon approval of the record in transfer approval.
				 */
			getSqlModel().singleExecute(getQuery(6), changeStatus);       
	
			
		
	}
	
	/*
	 * Following function is called when the applicant has more than one approver in reporting structure 
	 */
	public boolean changeApplStatus(TransferApproval bean,Object [][]empFlow,String trfNo){
				boolean result=false;
				
					if(empFlow !=null && empFlow.length!=0){
							Object [][]updateApprover=new Object[1][4];
							
							updateApprover[0][0]=empFlow[0][0];
							updateApprover[0][1]=empFlow[0][2];
							
							if(String.valueOf(empFlow[0][3]).equals("") || String.valueOf(empFlow[0][3]).equals("null")){
								updateApprover[0][2]=String.valueOf("");
							}else{
								updateApprover[0][2]=String.valueOf(empFlow[0][3]);
							}
							updateApprover[0][3]=trfNo;
							/*
							 * Following query is called to update the approver id,transfer level in HRMS_TRANSFER TABLE.
							 */
							result= getSqlModel().singleExecute(getQuery(8), updateApprover);
							
							}	else     {
								
							Object[][]statusChanged=new Object[1][2];
							statusChanged[0][0]="A";
							statusChanged[0][1]=trfNo;
							/*
							 * Following query is called to update the status of the record in HRMS_TRANSFER table. 
							 */
							result= getSqlModel().singleExecute(getQuery(7), statusChanged);
							
								
				    }
				
				return result;
			
			}
	}


