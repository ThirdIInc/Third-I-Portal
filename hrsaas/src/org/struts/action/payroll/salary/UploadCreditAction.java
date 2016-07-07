/**
 * 
 */
package org.struts.action.payroll.salary;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.paradyne.bean.payroll.salary.UploadCredit;
import org.paradyne.model.payroll.salary.UploadCreditModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author varunk
 *
 */
public class UploadCreditAction extends ParaActionSupport {

		UploadCredit upload;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		upload = new UploadCredit();
		upload.setMenuCode(626);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return upload;
	}

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	public UploadCredit getUpload() {
		return upload;
	}

	public void setUpload(UploadCredit upload) {
		this.upload = upload;
	}
	
	///this method is called when Download Excel Sheet button is pressed.
	public String uploadReport(){
		UploadCreditModel model = new UploadCreditModel();
		model.initiate(context, session);
		///it generated the report of all the employees who r in Service (Emp_Status = 'S')
		model.generateReport(upload,response);
		model.terminate();
		return null;
	}

///this method is called when Update button is pressed.	
public String displayExcel()throws Exception {
		/// In this fullFile the Downloaded Excel Sheet is copied from the path where it is saved.
	
	
		String fullFile = context.getRealPath("/")+ "pages/oo/"+session.getAttribute("session_pool")+"/pay/" +upload.getUploadFileName() ; 
		System.out.println("fullfile:"+fullFile);
		UploadCreditModel model = new UploadCreditModel();
		model.initiate(context, session);
		try {
			///this InputStream class is imported.
			InputStream myxls = new FileInputStream(fullFile);
			//this HSSFWorkbook class is used to read the columns and Rows from the Downloaded Excel sheet. 
			HSSFWorkbook wb     = new HSSFWorkbook(myxls);
			
			HSSFSheet sheet = wb.getSheetAt(0); 
			///in this rowCheck the total rows are read from the XLS
			/*HSSFRow rowCheck    = sheet.getRow(0);
			///in this cellCheck the each cell of dat particular row is read...
			HSSFCell cellCheck   = rowCheck.getCell((short)0);
			HSSFCell cellCheck1   = rowCheck.getCell((short)2);*/

			///this if condition is to check whether the uploaded Excel Sheet have the same cell Names....
			//Otherwise it will display the message Invalid XLS File Uploaded.
			//if(cellCheck.getStringCellValue().equals("Employee Code") && cellCheck1.getStringCellValue().equals("Amount")){
			
				int c=0;
				
				//Object add is created according to the RowNum...and  -2 is used...because the 
				//the first 2 rows of the XLS sheet are empty ven it is generated.
			Object[][] add=new Object[Integer.parseInt(String.valueOf(sheet.getLastRowNum()))+4][2];
				for(int i=0; i<=sheet.getLastRowNum();i++){
					try {
						HSSFRow row    = sheet.getRow(i);
						///in this cell...the value of the amount field is read.
						HSSFCell cell1   = row.getCell((short)2);
						///in this cell1 the value of Employee Code is read.
						HSSFCell cell   = row.getCell((short)0);
						///cell.getNumericCellValue is used because both rows contain Numeric Cell Values....
						/// if the cell contains String(Names) u can use cell.getStringCellValue()
						///Object add is created which contains all the employee amount and Code from the Excel Sheet
						/**
						 * if(outGoingTimeValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
									readTime [0]  = outGoingTimeValue.getNumericCellValue();
								}else if(outGoingTimeValue.getCellType() == HSSFCell.CELL_TYPE_STRING){
									readTime [0]  = outGoingTimeValue.getStringCellValue();
								}
						 */
						
						if(cell !=null){
							if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
								add[c][1]=(int)cell.getNumericCellValue();
								//logger.info("Employee Code in if------"+add[c][0]);
							}else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
								add[c][1]= cell.getStringCellValue();
								//logger.info("Employee Code in else if------"+add[c][0]);
							}
						}
						
						if(cell1!=null){
							if(cell1.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
								add[c][0]= (int)cell1.getNumericCellValue();
								//logger.info("Employee Code in if------"+add[c][0]);
							}else if(cell1.getCellType() == HSSFCell.CELL_TYPE_STRING){
								add[c][0]= cell1.getStringCellValue();
								//logger.info("Employee Code in else if------"+add[c][0]);
								if(cell1.getStringCellValue().equals("Amount")){
								}
								else{
									addActionMessage("Characters found at amount column for employee "+String.valueOf(add[c][1]));
									addActionMessage("Enter only Numbers in the amount column in excel sheet ");
									upload.setUploadFileName("");
									return "success";
								}
							}
						}
						
						//logger.info("Employee Code-------"+add[c][0]);
						if(cell1!=null || cell !=null){
							c++;
							//logger.info("val c----------------"+c);
						}
					} catch (RuntimeException e) {
						logger.error(e.getMessage());
					}
				}
				int objCnt=0;
				for (int i = 0; i < add.length; i++) {
					if(!(add[i][1] ==null || String.valueOf(add[i][1]).equals("null") || 
							String.valueOf(add[i][1]).equalsIgnoreCase("Employee Code"))){
						objCnt++;
					}
				}
				Object[][] finalObj = new Object[objCnt][2];
				objCnt=0;
				for (int i = 0; i < add.length; i++) {
					if(!(add[i][1] ==null || String.valueOf(add[i][1]).equals("null") || 
							String.valueOf(add[i][1]).equalsIgnoreCase("Employee Code"))){
						finalObj[objCnt][0] = add[i][0];
						finalObj[objCnt][1] = add[i][1];
						objCnt++;
					}
				}
				String poolName = String.valueOf(session.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null )) {
					poolName = "/" + poolName;
				}
				//for getting server path where configuration files are saved.
				String path = getText("data_path") + "/datafiles/" + poolName+ "/xml/Payroll/";
				///this method is called to update the Credits with the amount which is entered in the XLS Sheet
				// add Object is passed in the method.
				String result=model.updCredits(upload,finalObj,path);
				
				if(result.equals("2")){
					addActionMessage("Credit Updated Successfully");
				}else if(result.equals("1")){
					addActionMessage("Salary Not Processed");
				}
				
			/*}
			else{
				addActionMessage("Invalid Xls File Uploaded");
			}*/
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		
		model.terminate();
		
		return SUCCESS;
	}

	public String reset()throws Exception{
			
			upload.setMonth("");
			upload.setYear("");
			upload.setCreditName("");
			upload.setCreditCode("");
			upload.setUploadFileName("");
			upload.setSalLedgerCode("");
			
			return SUCCESS;
		}
	
	public String f9CreditAction() throws Exception 
	{
		
		logger.info("in f9 action");
		
		String query = "SELECT CREDIT_CODE, CREDIT_NAME FROM HRMS_CREDIT_HEAD" +
					   " WHERE CREDIT_PERIODICITY = 'M' AND CREDIT_PAYFLAG = 'Y' ORDER BY CREDIT_CODE";		
		
		
		String[] headers={getMessage("creditCode") ,getMessage("creditName")};
		
		String[] headerWidth={"20", "80"};
		
		
		String[] fieldNames={"creditCode" , "creditName"};
		
		
		int[] columnIndex={0,1};
		
		String submitFlag = "false";
		
		
		String submitToMethod="";
		
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	

}
