package org.paradyne.model.admin.transfer;
import java.util.ArrayList;
import java.util.HashMap;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

import org.paradyne.bean.admin.transfer.TransferPmApproval;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * author:Pradeep Kumar Sahoo
 * Date:14.11.2007
 */

public class TransferPmApprovalModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	public void getEmp(TransferPmApproval tpma,HttpServletRequest request) {
		String query=" SELECT TRANSFER_CODE,HRMS_TRANSFER.EMP_ID,NVL(EMP_TOKEN,''),TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
					+" NVL(TO_CHAR(TRANSFER_APPLICATION_DATE,'DD-MM-YYYY'),'') FROM HRMS_TRANSFER "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TRANSFER.EMP_ID) "
					+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+" WHERE TRANSFER_STATUS='A' AND TRANSFER_CEODATE IS NULL AND TRANSFER_CEONO IS NULL ORDER BY HRMS_TRANSFER.TRANSFER_CODE ";
		
		Object[][] values = getSqlModel().getSingleResult(query);
		HashMap afdata=new HashMap();
		ArrayList<Object> transList = new ArrayList<Object>();
		for(int i=0;i<values.length;i++) {
			TransferPmApproval bean=new TransferPmApproval();
			bean.setTrfId(String.valueOf(values[i][0]));
			bean.setEmpId(String.valueOf(values[i][1]));
			bean.setEmpToken(String.valueOf(values[i][2]));
			bean.setEmpName(String.valueOf(values[i][3]));
			bean.setAppDate(String.valueOf(values[i][4]));
			afdata.put(""+i,String.valueOf("A"));
			
			
			
			
			transList.add(bean);
		}
		tpma.setTransferList(transList);
		request.setAttribute("data",afdata);
	}
	
	public boolean saveByApprover(TransferPmApproval trnApp,String trnCode)
	{
		Object [][] pmApprove=new Object[1][3];
		pmApprove[0][0]=trnApp.getDceNo();
		pmApprove[0][1]=trnApp.getDceDate();
		pmApprove[0][2]=trnCode;
		

		return getSqlModel().singleExecute(getQuery(1),pmApprove);
		
	
	
	}
	
	
	public boolean saveByDCEApprover(TransferPmApproval trnApp,String trnCode)
	{
		Object [][] pmApprove=new Object[1][3];
		pmApprove[0][0]="";//trnApp.getDceNo();
		pmApprove[0][1]="";//trnApp.getDceDate();
		pmApprove[0][2]=trnCode;
		

		return getSqlModel().singleExecute(getQuery(1),pmApprove);
		
	
	
	}
	
public boolean dcelist(TransferPmApproval tranferPmApp,HttpServletRequest request ,HttpServletResponse response,int c,String[][] empId) {
		
		logger.info("---------------------------------------c value"+c);
		
		String type="Txt";
		String title="DCE LIST";
		int sno=1;
		
		Object[][] data = new Object[c][6];
		for (int i = 0; i < empId.length; i++) {
			
			String query ="SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME|| "+
							"'\n'||HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_ID||' - '||HRMS_CENTER.CENTER_NAME,TO_CHAR(TRANSFER_RELIEVING_DATE,'DD-MM-YYYY'),NVL(TRANSFER_UNIT,' ') "
							+ " FROM HRMS_EMP_OFFC "
							+" INNER JOIN HRMS_TRANSFER ON(HRMS_TRANSFER.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
							+" LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
							+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
							+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
							+" WHERE TRANSFER_CODE= "+ empId[i][0] ;
			
			Object result[][] = getSqlModel().getSingleResult(query);
			
			data[i][0] = sno++;
			data[i][1] =String.valueOf(result[0][0]);
			data[i][2] =String.valueOf(result[0][1]);
			data[i][3] =String.valueOf(result[0][2]);
			data[i][4] =String.valueOf(result[0][3]);
			data[i][5] =String.valueOf(result[0][4]);
		
			
							
		}
		
		try{
						
			org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator(type,title);
			
			rg=getHeaderDce(rg,tranferPmApp);//ADDRESS
			
			rg=getEmpDataDce(rg,data);//TABLE DATA
			
			rg=getDataDce(rg);//THIRD
			//rg=getHeader(rg);//fourth
			
			rg.createReport(response);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
	} 
	
	public ReportGenerator getHeader(ReportGenerator rg){
		
		rg.addText("",0,0,1);
		Object[][] obj=new Object[3][2];
		obj[0][0]="";
		obj[1][0]="";
		obj[2][0]="";
		
		obj[0][1]="Office of the Admiral Superintendent";
		obj[1][1]="Naval Dockyard";
		obj[2][1]="Mumbai- 400 023 ";
		
		int[] cell={28,20};
		int[] align={0,0};
		rg.tableBody(obj, cell, align);
					
		return rg;
}
	

	
	
	//THIRD
	public ReportGenerator getDataDce(ReportGenerator rg){
		
		//String message="\n\n for ADMIRAL SUPERINTENDENT ";
		String[] message1={"NAVAL DOCKYARD                             FOR ADMIRAL SUPERINTENDENT\nMUMBAI\nDATE:\n\nDISTRIBUTION : STANDARD AS REQUIRED"};
		
		//rg.addText(message, 0,2,1);
	//	rg.addText(message1, 0,0,1);
		int[] style={0,0};
		rg.addFormatedText(message1, style, 0,0,0);
	
		
					
		return rg;
}
	//FIRST
public ReportGenerator getHeaderDce(ReportGenerator rg,TransferPmApproval tranferPmApp){
		
		String message="\n PERSONNEL DEPARTMENT, NAVAL DOCKYARD, MUMBAI \n CIVILIAN ESTABLISHMENT LIST PART I (NON INDUSTRIAL)\n " +
				"NUMBER "+tranferPmApp.getDceNo();//+"/"+tranferPmApp.getDceDate();
		
		rg.addText(message,  0,0,0);
		
		String[] msg={"The following personnel have been transferred on promotion to the next higher  grade  and  struck  off  strength  of the dockyard. They are directed  to  report  to  the  next unit. They  have been granted TA/DA & joining time as per Govt.Rules in Force. "};
		int[] style={0};
		rg.addFormatedText(msg, style, 0,0,0);
		
		String[] msg1={" PROMOTIONS/TRANSFERS OUT OF DOCKYARD"};
		int[] st1={0};
		rg.addFormatedText(msg1, st1, 0,1,0);
		
				
		return rg;
}
//SECOND
public ReportGenerator getEmpDataDce(ReportGenerator rg,Object data[][]){
	
	String colNames[]={"SNo.","T No./P No.","Name/Rank","C.No/C.Name","Date Of\nTransfer","Transfer To"};
	int []cellwidth={5,10,25,25,15,20};
	int []alignmnet={0,1,0,0,0,0};
	rg.tableBody(colNames, data, cellwidth, alignmnet);		
	return rg;
	
}
	
}
