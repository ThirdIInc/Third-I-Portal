package org.paradyne.model.admin.srd;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.AddressBook;
import org.paradyne.lib.ModelBase;

public class AddressBookModel extends ModelBase {

	/**
	 * 
	 */
	  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class);
	public AddressBookModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void  getEmployeeDetails(String empId,AddressBook addressBook){
		logger.info("*****getEmployeeDetails");
		
			Object[] beanObj = new Object[1];
			beanObj[0] =String.valueOf(empId) ;
			logger.info("*****beanObj[0]:"+beanObj[0]);
			String query =" SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN, "
						+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
						+ "	HRMS_RANK.RANK_NAME,TO_CHAR(CENTER_ID||'-'||CENTER_NAME) ,DEPT_NAME"
						+ "	FROM HRMS_EMP_OFFC "
						+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK " 
						+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
						+" INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
						+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
						+"  WHERE HRMS_EMP_OFFC.EMP_ID =?";
			
			Object[][] values = getSqlModel().getSingleResult(query, beanObj);
			logger.info("addApplication:-------------------"+values.length);
			logger.info("addApplication:-------------------"+String.valueOf(beanObj[0]));
			
			addressBook.setEmpId(checkNull(String.valueOf(values[0][0])));
			addressBook.setToken(checkNull(String.valueOf(values[0][1])));
			addressBook.setEmpName(checkNull(String.valueOf(values[0][2])));
			addressBook.setRank(checkNull(String.valueOf(values[0][3])));
			addressBook.setCenter(checkNull(String.valueOf(values[0][4])));
			addressBook.setDeptName(checkNull(String.valueOf(values[0][5])));
			//addressBook.setDivCode(checkNull(String.valueOf(values[0][6])));
			
	}
	
	public String checkNull(String data){
		if(data==null || data.equals("")){
			return "";
		}else{
			return data;
		}
	}
	
	

	public String getReport(AddressBook addressBook,HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		/*Object[] beanObj = new Object[1];
		beanObj[0] =String.valueOf(divCode);*/
		try {
			//String reportType="Xls";
			String reportName="Address Book Report";
			org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(addressBook.getReportType(),reportName);
			//rg.addText("Address Book Report", 0, 0, 0);
			rg.addTextBold("Address Book", 0, 1, 0);
			//rg.addTextBold("\n", 0, 1, 0);
			if(!(addressBook.getCenterNo().equals("") )){
				rg.addTextBold("Branch : " + addressBook.getCenterName(),0,0,0);}
			
			if(!(addressBook.getDeptName1().equals("") )){
				rg.addTextBold("Department : " + addressBook.getDeptName1(),0,0,0);}
			if(!(addressBook.getDesgName1().equals("") )){
				rg.addTextBold("Division : " + addressBook.getDesgName1(),0,0,0);}
			if(!(addressBook.getDivsion().equals("") )){
				rg.addTextBold("Designation : " + addressBook.getDivsion(),0,0,0);
				}
										
        	String sql="SELECT NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') ,ADD_1||''|| ADD_2||''||ADD_3||'\n'||ADD_CITY||'\n'||ADD_STATE||'\n'||ADD_CNTRY,EMP_DIV"
				 +" FROM HRMS_EMP_OFFC "
				 +" INNER JOIN HRMS_RANK ON( HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
				 +" INNER JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
				 +" INNER JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
			 
				 +" WHERE ADD_TYPE='"+"P"+"'  ";
				 		
				 		// AND EMP_DIV="+addressBook.getDivCode()+" " ;	
        	   
				if(addressBook.getEmpId()!=null && addressBook.getEmpId().length()>0){
					sql+=" AND HRMS_EMP_ADDRESS.EMP_ID="+addressBook.getEmpId()+" " ;}
				
				if(addressBook.getCenterNo()!=null && addressBook.getCenterNo().length()>0){
					sql+=" AND HRMS_EMP_OFFC.EMP_CENTER="+addressBook.getCenterNo()+" ";}
				
				if(addressBook.getDivCode()!=null && addressBook.getDivCode().length()>0){
					sql+=" AND HRMS_EMP_OFFC.EMP_DIV="+addressBook.getDivCode()+" ";
				}
				if(addressBook.getDeptCode()!=null && addressBook.getDeptCode().length()>0){
					sql+=" AND HRMS_EMP_OFFC.EMP_DEPT="+addressBook.getDeptCode()+" ";
				}
				if(addressBook.getDesgCode()!=null && addressBook.getDesgCode().length()>0){
					sql+=" AND HRMS_EMP_OFFC.EMP_RANK="+addressBook.getDesgCode()+" ";
				}
			
				
				// System.out.println(" +++++++++++++++++"+addressBook.getDivCode());
					
				//sql+=" ORDER BY HRMS_EMP_OFFC.EMP_TOKEN ";
				
				Object addData[][]= getSqlModel().getSingleResult(sql);
				Object[][] finalAddData=new Object[addData.length][3];
				//String name="";
				//String address="";
				//String empTok="";
				//int s=1;
				
				for(int i=0;i<addData.length;i++){
					
						//finalAddData[i][0]=s++;
						finalAddData[i][0]= addData[i][0];
						finalAddData[i][1]= addData[i][1];
				
						//finalAddData[i][3]= addData[i][2];
						//finalAddData[i][4]= addData[i][3];
						//finalAddData[i][5]= addData[i][4];
					
						
					
					//name=String.valueOf(addData[i][0]);
					//address=String.valueOf(addData[i][1]);
					//empTok=String.valueOf(addData[i][1]);
				}
				if (finalAddData != null && finalAddData.length > 0) {
				String[] colNames={"Employee ","Address"};
				int [] cellWidth={8,20};
				int [] alignment={0,0};
				//rg.genHeader("Address Book ");				
		    	//rg.addFormatedText("Address Book", 1, 1, 0, 0);
				
				rg.tableBody(colNames,finalAddData,cellWidth,alignment);	
				
				}else{
					rg.addTextBold("There is no data to display.",0,1,0);
				}
			
				rg.createReport(response);
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return "true";
	}

}
	