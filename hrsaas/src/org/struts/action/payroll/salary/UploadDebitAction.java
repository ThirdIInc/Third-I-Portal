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
import org.paradyne.bean.payroll.salary.UploadDebit;
import org.paradyne.model.payroll.salary.UploadDebitModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author varunk
 *
 */
public class UploadDebitAction extends ParaActionSupport {

	UploadDebit upload;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		
		upload = new UploadDebit();
		upload.setMenuCode(590);
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return upload;
	}
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public UploadDebit getUpload() {
		return upload;
	}

	public void setUpload(UploadDebit upload) {
		this.upload = upload;
	}
	
	public String uploadReport(){
		UploadDebitModel model = new UploadDebitModel();
		model.initiate(context, session);
		model.generateReport(upload,response);
		model.terminate();
		return null;
	}
	
	public String displayExcel()throws Exception {
		
		String fullFile = context.getRealPath("/")+ "pages/oo/"+session.getAttribute("session_pool")+"/pay/" +upload.getUploadFileName() ; 
		logger.info("file name------------------------------------"+fullFile);
		UploadDebitModel model = new UploadDebitModel();
		model.initiate(context, session);
		try {
			logger.info("in try........");
			InputStream myxls = new FileInputStream(fullFile);
			HSSFWorkbook wb     = new HSSFWorkbook(myxls);
			
			HSSFSheet sheet = wb.getSheetAt(0); 
			/*HSSFRow rowCheck    = sheet.getRow(0);
			HSSFCell cellCheck   = rowCheck.getCell((short)0);
			HSSFCell cellCheck1   = rowCheck.getCell((short)2);*/
			
			//logger.info("value of cellCheck 0--------------------------"+cellCheck.getStringCellValue());
			//logger.info("value of cellCheck 0--------------------------"+cellCheck1.getStringCellValue());

			//if(cellCheck.getStringCellValue().equals("Employee Code") && cellCheck1.getStringCellValue().equals("Amount")){
		
			/*logger.info("-----tolt row"+sheet.getLastRowNum());// first sheet
			HSSFRow row    = sheet.getRow(3);        // third row
			row.getFirstCellNum();
			logger.info("row.getFirstCellNum();------------------------------"+row.getFirstCellNum());
			HSSFCell cell   = row.getCell((short)0);  // fourth cell
			cell.getNumericCellValue();
			logger.info("excel sheet column value-name-----------------------------"+cell.getNumericCellValue());
			HSSFCell cell1   = row.getCell((short)2);
			logger.info("excel sheet column value--code----------------------------"+cell1.getNumericCellValue());
				*/
			int c=0;
			Object[][] add=new Object[Integer.parseInt(String.valueOf(sheet.getLastRowNum()))+4][2];
				for(int i=0; i<=sheet.getLastRowNum();i++){
					try {
						HSSFRow row = sheet.getRow(i);
						HSSFCell cell1 = row.getCell((short) 2);
						//logger.info("excel sheet column value-name-----------------------------"+cell.getNumericCellValue());
						HSSFCell cell = row.getCell((short) 0);
						//logger.info("excel sheet column value--code----------------------------"+cell1.getNumericCellValue());
						
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
									addActionMessage("Enter only Numbers in the amount field in excel sheet ");
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
					} catch (Exception e) {
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
				
				String result=model.updDebits(upload,finalObj);
				
				if(result.equals("2")){
					addActionMessage("Debit Updated Successfully");
					// addActionMessage(getText("addMessage", ""));
				}else if(result.equals("1")){
					addActionMessage("Salary Not Processed or Salary has been Locked");
				}
				
			/*}
			else{
				logger.info("in else action-----------------------------------------");
				addActionMessage("Invalid Xls File Uploaded");
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		model.terminate();
		
		return SUCCESS;
	}
	
	public String reset()throws Exception{
		
		upload.setMonth("");
		upload.setYear("");
		upload.setDebitName("");
		upload.setDebitCode("");
		upload.setUploadFileName("");
		upload.setSalLedgerCode("");
		
		return SUCCESS;
	}
	
	public String f9DebitAction() throws Exception 
	{
		
		logger.info("in f9 action");
		
		String query = "SELECT DEBIT_CODE , DEBIT_NAME FROM HRMS_DEBIT_HEAD  ORDER BY DEBIT_CODE";		
		
		
		String[] headers={getMessage("debitCode") ,getMessage("debitName")};
		
		String[] headerWidth={"20", "80"};
		
		
		String[] fieldNames={"debitCode" , "debitName"};
		
		
		int[] columnIndex={0,1};
		
		String submitFlag = "false";
		
		
		String submitToMethod="";
		
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}

}
