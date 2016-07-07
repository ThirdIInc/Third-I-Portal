/**
 * 
 */
package org.struts.action.payroll.incometax;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.paradyne.bean.payroll.incometax.ETds;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.incometax.ETdsModel;
import org.struts.lib.ParaActionSupport;


/**
 * @author AA0517
 *
 */
public class ETdsAction extends ParaActionSupport {

	ETds eTds;
	ByteArrayOutputStream bout;
	NumberFormat formatter = new DecimalFormat("#0");
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		eTds = new ETds();
		eTds.setMenuCode(678);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return eTds;
	}
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ETdsAction.class);
	
	public ETds getETds() {
		return eTds;
	}

	public void setETds(ETds tds) {
		eTds = tds;
	}
	/**
	 * GET THE STATE CODE
	 * @param stateName
	 * @return
	 * @throws Exception
	 */
	public String stateCode(String stateName)throws Exception{
		String templatecode="";
		Object[][] code = new Object[37][2];
		code[0][0]="ANDAMAN AND NICOBARISLANDS";
		code[0][1]="1";
		code[1][0]="ANDHRA PRADESH";
		code[1][1]="2";
		code[2][0]="ARUNACHAL PRADESH";
		code[2][1]="3";
		code[3][0]="ASSAM";
		code[3][1]="4";
		code[4][0]="BIHAR";
		code[4][1]="5";
		code[5][0]="CHANDIGARH";
		code[5][1]="6";
		code[6][0]="DADRA & NAGAR HAVELI";
		code[6][1]="7";
		code[7][0]="DAMAN & DIU";
		code[7][1]="8";
		code[8][0]="DELHI";
		code[8][1]="9";
		code[9][0]="GOA";
		code[9][1]="10";
		code[10][0]="GUJARAT";
		code[10][1]="11";
		code[11][0]="HARYANA";
		code[11][1]="12";
		code[12][0]="HIMACHAL PRADESH";
		code[12][1]="13";
		code[13][0]="JAMMU & KASHMIR";
		code[13][1]="14";
		code[14][0]="KARNATAKA";
		code[14][1]="15";
		code[15][0]="KERALA";
		code[15][1]="16";
		code[16][0]="LAKSHWADEEP";
		code[16][1]="17";
		code[17][0]="MADHYA PRADESH";
		code[17][1]="18";
		code[18][0]="MAHARASHTRA";
		code[18][1]="19";
		code[19][0]="MANIPUR";
		code[19][1]="20";
		code[20][0]="MEGHALAYA";
		code[20][1]="21";
		code[21][0]="MIZORAM";
		code[21][1]="22";
		code[22][0]="NAGALAND";
		code[22][1]="23";
		code[23][0]="ORISSA";
		code[23][1]="24";
		code[24][0]="PONDICHERRY";
		code[24][1]="25";
		code[25][0]="PUNJAB";
		code[25][1]="26";
		code[26][0]="RAJASTHAN";
		code[26][1]="27";
		code[27][0]="SIKKIM";
		code[27][1]="28";
		code[28][0]="TAMILNADU";
		code[28][1]="29";
		code[29][0]="TRIPURA";
		code[29][1]="30";
		code[30][0]="UTTAR PRADESH";
		code[30][1]="31";
		code[31][0]="WEST BENGAL";
		code[31][1]="32";
		code[32][0]="CHHATISHGARH";
		code[32][1]="33";
		code[33][0]="UTTARANCHAL";
		code[33][1]="34";
		code[34][0]="JHARKHAND";
		code[34][1]="35";
		code[35][0]="OTHERS";
		code[35][1]="99";
		code[36][0]=" ";
		code[36][1]="99";
		
		for (int i = 0; i < code.length; i++) {
			//logger.info("state name   :"+stateName);
			//logger.info("code[i][0]   :"+code[i][0]);
			if(String.valueOf(code[i][0]).toUpperCase().trim().equals(stateName.toUpperCase().trim())){
				//logger.info("in if-----------------   :");
				templatecode = String.valueOf(code[i][1]);
			}
		}
		//logger.info("templatecode-----------------   :"+templatecode);
		return templatecode;
	}
	
	/**
	 * Generate the txt file from uploaded file
	 * @return
	 * @throws Exception
	 */
	public String generate()throws Exception {
		/// In this fullFile the Downloaded Excel Sheet is copied from the path where it is saved.
		String fullFile = context.getRealPath("/")+ "pages/oo/"+session.getAttribute("session_pool")+"/pay/" +eTds.getUploadFileName() ; 
		
		try {
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("ddMMyyyy");
			String sysDate = formater.format(date);
			
			///this InputStream class is imported.
			InputStream myxls = new FileInputStream(fullFile);
			//this HSSFWorkbook class is used to read the columns and Rows from the Downloaded Excel sheet. 
			HSSFWorkbook wb     = new HSSFWorkbook(myxls);
			
			/**
			 * this sheet wb.getSheetAt(0) is used to read the values of DEDUCTOR
			 */
			HSSFSheet sheet = wb.getSheetAt(0); 
			
			HSSFRow row    = sheet.getRow(0);
			HSSFCell cellTan   = row.getCell((short)1);
			HSSFRow rowPan    = sheet.getRow(1);
			HSSFCell cellPan   = rowPan.getCell((short)1);
			HSSFRow rowEmployerName   = sheet.getRow(2);
			HSSFCell cellEmployerName   = rowEmployerName.getCell((short)1);//Employer/Deductor Name
			HSSFRow rowEmployerAdd   = sheet.getRow(4);
			HSSFCell cellEmployerAdd   = rowEmployerAdd.getCell((short)1);//Employer/Deductor Address line 1
			String employerAdd="";
			employerAdd = cellEmployerAdd.getStringCellValue();
			if(employerAdd.length() > 25){
				employerAdd = employerAdd.substring(0, 25);
			}
			HSSFRow rowEmployerCity  = sheet.getRow(8);
			HSSFCell cellEmployerCity  = rowEmployerCity.getCell((short)1);//Employer/Deductor city
			HSSFRow rowEmployerState = sheet.getRow(9);
			HSSFCell cellEmployerState  = rowEmployerState.getCell((short)1);//Employer/Deductor State
			String stateName = cellEmployerState.getStringCellValue();
			String stateCodeDeductee = stateCode(stateName);///the stateName is passed in the method to retrieve the required Annexure state code.
			HSSFRow rowEmployerPin  = sheet.getRow(10);
			HSSFCell cellEmployerPin  = rowEmployerPin.getCell((short)1);//Employer/Deductor PINCODE
			String cellEmployerPinCode="";
			if(cellEmployerPin.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
				cellEmployerPinCode=String.valueOf(cellEmployerPin.getNumericCellValue());
				//logger.info("Employee Code in if------"+add[c][0]);
			}else if(cellEmployerPin.getCellType() == HSSFCell.CELL_TYPE_STRING){
				cellEmployerPinCode= cellEmployerPin.getStringCellValue();
				//logger.info("Employee Code in else if------"+add[c][0]);
			}
			//Added by Reeba start
			String cellEmployerEmail="";
			HSSFRow rowEmployerEmail = sheet.getRow(13);
			HSSFCell cellEmployerEmailId  = rowEmployerEmail.getCell((short)1);//Employer/Deductor Email ID
			if(cellEmployerEmailId.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
				cellEmployerEmail=String.valueOf(cellEmployerEmailId.getNumericCellValue());
			}else if(cellEmployerEmailId.getCellType() == HSSFCell.CELL_TYPE_STRING){
				cellEmployerEmail= cellEmployerEmailId.getStringCellValue();
			}
			
			String cellEmployerStdCode="";
			HSSFRow rowEmployerStdCode = sheet.getRow(11);
			HSSFCell cellEmployerStd = rowEmployerStdCode.getCell((short)1);//Employer/Deductor Std Code
			if(cellEmployerStd.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
				cellEmployerStdCode=String.valueOf(cellEmployerStd.getNumericCellValue());
			}else if(cellEmployerStd.getCellType() == HSSFCell.CELL_TYPE_STRING){
				cellEmployerStdCode= cellEmployerStd.getStringCellValue();
			}
			String cellEmployerTelNo = "";
			HSSFRow rowEmployerTelNo = sheet.getRow(12);
			HSSFCell cellEmployerTelephone = rowEmployerTelNo.getCell((short)1);//Employer/Deductor Std Code
			if(cellEmployerTelephone.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
				cellEmployerTelNo=String.valueOf(cellEmployerTelephone.getNumericCellValue());
			}else if(cellEmployerTelephone.getCellType() == HSSFCell.CELL_TYPE_STRING){
				cellEmployerTelNo= cellEmployerTelephone.getStringCellValue();
			}
			cellEmployerTelNo=formatter.format(Double.parseDouble(cellEmployerTelNo));
			//Added by Reeba end
			
			HSSFRow rowEmployerStatus  = sheet.getRow(14);
			HSSFCell cellEmployerStatus = rowEmployerStatus .getCell((short)1);
			String employerStatus= cellEmployerStatus.getStringCellValue();//STATUS OF DEDUCTOR
			HSSFRow rowResName  = sheet.getRow(15);
			HSSFCell cellResName = rowResName .getCell((short)1);//NAME OF RESPONSIBLE PERSON
			logger.info("cellResName.getStringCellValue()  :"+cellResName.getStringCellValue());
			HSSFRow rowResDesg  = sheet.getRow(26);
			HSSFCell cellResDesg = rowResDesg .getCell((short)1);//RESPONSIBLE PERSON DESIGNATION
			logger.info("cellResDesg.getStringCellValue()  :"+cellResDesg.getStringCellValue());
			HSSFRow rowResAdd  = sheet.getRow(16);
			HSSFCell cellResAdd = rowResAdd .getCell((short)1);//RESPONSIBLE PERSON ADDRESS
			String resAdd="";
			resAdd = cellResAdd.getStringCellValue();
			if(resAdd.length() > 25){
				resAdd = resAdd.substring(0, 25);
			}
			logger.info("cellResAdd.getStringCellValue()  :"+cellResAdd.getStringCellValue());
			HSSFRow rowResCity  = sheet.getRow(20);
			HSSFCell cellResCity = rowResCity .getCell((short)1);//RESPONSIBLE PERSON CITY
			logger.info("cellResCity.getStringCellValue()  :"+cellResCity.getStringCellValue());
			HSSFRow rowResState  = sheet.getRow(21);
			HSSFCell cellResState = rowResState .getCell((short)1);//RESPONSIBLE PERSON STATE
			logger.info("cellResState.getStringCellValue()  :"+cellResState.getStringCellValue());
			String resStateName = cellResState.getStringCellValue();
			logger.info("resStateName :"+resStateName);
			String stateCodeRespon = stateCode(resStateName);///the responsible person stateName is passed in the method to retrieve the required Annexure state code.
			logger.info("stateCodeRespon :"+stateCodeRespon);
			HSSFRow rowResPincode  = sheet.getRow(22);
			HSSFCell cellResPincode = rowResPincode .getCell((short)1);//RESPONSIBLE PERSON PIN CODE
			String pinCodeRespon="";
			logger.info("cellResPincode.getCellType()   :"+cellResPincode.getCellType());
			if(cellResPincode.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
				pinCodeRespon=String.valueOf(cellResPincode.getNumericCellValue());
				//logger.info("Employee Code in if------"+add[c][0]);
			}else if(cellResPincode.getCellType() == HSSFCell.CELL_TYPE_STRING){
				pinCodeRespon= cellResPincode.getStringCellValue();
				//logger.info("Employee Code in else if------"+add[c][0]);
			}
			logger.info("pinCodeRespon :"+pinCodeRespon);
			//Added by reeba start
			String emailResPerson = "";
			HSSFRow rowResEmail  = sheet.getRow(25);
			HSSFCell cellResEmailId = rowResEmail .getCell((short)1);//RESPONSIBLE PERSON EMAIL ID
			if(cellResEmailId.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
				emailResPerson=String.valueOf(cellResEmailId.getNumericCellValue());
			}else if(cellResEmailId.getCellType() == HSSFCell.CELL_TYPE_STRING){
				emailResPerson= cellResEmailId.getStringCellValue();
			}
			String mobileResPerson = "";
			HSSFRow rowResMobile  = sheet.getRow(24);
			HSSFCell cellResMobile = rowResMobile .getCell((short)1);//RESPONSIBLE PERSON MOBILE NO
			if(cellResMobile.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
				mobileResPerson=String.valueOf(cellResMobile.getNumericCellValue());
			}else if(cellResMobile.getCellType() == HSSFCell.CELL_TYPE_STRING){
				mobileResPerson= cellResMobile.getStringCellValue();
			}
			mobileResPerson=formatter.format(Double.parseDouble(mobileResPerson));
			if(String.valueOf(employerStatus).equals("C  CentralGovt")){
				employerStatus = "C";
			}
			else{
				employerStatus = "O";
			}
			logger.info("employerStatus :"+employerStatus);
			int count=0;
			Object []data = new Object[3];
			/**
			 * this for loop is to read the values of Financial year, Quarter & Assessment Year 
			 * 
			 */
			for(int i=29;i<32;i++){
				HSSFRow row1    = sheet.getRow(i);
				HSSFCell cell1   = row1.getCell((short)1);
				if(cell1.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					data[count]=(int)cell1.getNumericCellValue();
					//logger.info("Employee Code in if------"+add[c][0]);
				}else if(cell1.getCellType() == HSSFCell.CELL_TYPE_STRING){
					data[count]= cell1.getStringCellValue();
					//logger.info("Employee Code in else if------"+add[c][0]);
				}
				count++;
			}
			
			/**
			 * End of DEDUCTOR sheet----------------------------------------------------
			 */
			
			/**
			 * Start of Challan Sheet---------------------------------------------------
			 */
			NumberFormat formatter = new DecimalFormat("#0.00");
			double totTaxDeposited=0.00;
			HSSFSheet challanSheet = wb.getSheetAt(3); 
			logger.info("challanSheet.getLastRowNum()    :"+challanSheet.getLastRowNum());
			try {
				for (int i = 1; i <= challanSheet.getLastRowNum(); i++) {
					HSSFRow challanRow = challanSheet.getRow(i);
					HSSFCell challanCell = challanRow.getCell((short)7);
					double challanTaxDep= Double.parseDouble(String.valueOf(challanCell.getNumericCellValue()));
					challanTaxDep=Double.parseDouble(formatter.format(challanTaxDep));
					totTaxDeposited += challanTaxDep;
					//logger.info("totTaxDeposited    :" + Utility.twoDecimals(totTaxDeposited));
				}
			} catch (Exception e) {
				logger.error("in Challan Sheet");
				e.printStackTrace();
			}
			String challanTaxDeposit = formatter.format(totTaxDeposited);
			//logger.info("totTaxDeposited abc2   :" + abc2);
			/**
			 * End of Challan Sheet------------------------------------------------------
			 */
			/**
			 * Start of Deductee sheet---------------------------------------------------
			 */
				HSSFSheet deducteeSheet = wb.getSheetAt(1); 
			/**
			 * end of deductee sheet----------------------------------------------------
			 */
			
			/**
			 * start of Salary sheet---------------------------------------------------
			 */
			double grossTotIncome=0.00;
			double salGrossTotIncome=0.00;
			HSSFSheet salarySheet = wb.getSheetAt(2); 
			logger.info("salarySheet.getLastRowNum()    :"+salarySheet.getLastRowNum());
			try {
				for (int i = 1; i <= salarySheet.getLastRowNum(); i++) {
					HSSFRow salaryRow = salarySheet.getRow(i);
					HSSFCell salaryCell = salaryRow.getCell((short) 12);
					if(salaryCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
						salGrossTotIncome=(int)salaryCell.getNumericCellValue();
						salGrossTotIncome=Double.parseDouble(formatter.format(salGrossTotIncome));
						//logger.info("Employee Code in if------"+add[c][0]);
					}else if(salaryCell.getCellType() == HSSFCell.CELL_TYPE_STRING){
						salGrossTotIncome= Double.parseDouble(salaryCell.getStringCellValue());
						//logger.info("Employee Code in else if------"+add[c][0]);
					}
					grossTotIncome += Double.parseDouble(Utility.twoDecimals(formatter.format(salGrossTotIncome)));
					grossTotIncome = Double.parseDouble(formatter.format(grossTotIncome));
					logger.info("grossTotIncome    :" + grossTotIncome);
				}
			} catch (Exception e) {
				logger.error("in Salary Sheet");
				e.printStackTrace();
			}
			String grossTotIncomeFrmSalary = formatter.format(grossTotIncome);
			logger.info("grossTotIncome abc   :" + grossTotIncomeFrmSalary);
			PrintWriter output = null;
			String poolName=String.valueOf(session.getAttribute("session_pool"));
			String inFileName = getText("data_path") + "/datafiles/" + poolName+"/incometax/write.txt";
			File file1 = new File(getText("data_path") + "/datafiles/" + poolName+"/incometax");
			if(!file1.exists()){
				file1.mkdir();
			}
			File file = new File(inFileName);
		    output = new PrintWriter(new FileWriter(file));
			//response.setContentType("application/txt");
		   // output = response.getWriter();

		    
		    /**
		     * this output.write is for File Header data.
		     */
		    String utilityName = "TDS/TCS File Validation Utility - Version 3.6";
		    
		    logger.info("utilityName===="+utilityName.length());
		    
		    output.write("1^FH^SL1^R^"+sysDate+"^1^D^"+cellTan.getStringCellValue()+"^1^Validation Utility - Version 3.6^^^^^^^^");
		    output.println();
		    /**
		     * this output.write is for Batch Header Data
		     */
		    output.write("2^BH^1^"+challanSheet.getLastRowNum()+"^24Q^^^^^^^^"+cellTan.getStringCellValue()+"^^"+cellPan.getStringCellValue()
		    		+"^"+data[2]+"^"+data[0]+"^"+data[1]		// data[0]=Financial year  data[1]=Assessment year   data[2]=Quarter No
		            +"^"+cellEmployerName.getStringCellValue()+"^" +
		    		"^"+employerAdd+"^^"+cellEmployerCity.getStringCellValue()+"^"+cellEmployerCity.getStringCellValue()+"^^"+stateCodeDeductee+"^"+cellEmployerPinCode+"^"+cellEmployerEmail+"^"+cellEmployerStdCode+"^"+cellEmployerTelNo+"^N^K^" +
		    		""+cellResName.getStringCellValue()+"^"+cellResDesg.getStringCellValue()+"^"+resAdd+"^^^"+cellResCity.getStringCellValue()+"^"+cellResCity.getStringCellValue()+"^"+stateCodeRespon
		    		+"^"+Math.round(Double.parseDouble(pinCodeRespon))+"^"+emailResPerson+"^"+mobileResPerson+"^^^N^"+Utility.twoDecimals(challanTaxDeposit)+"" +    
		    		"^^"+salarySheet.getLastRowNum()+"^"+Utility.twoDecimals(grossTotIncomeFrmSalary)+"^N^^^^^^^^^^^");
		    output.println();
		  
		    /**
		     * for adding Challan Header Detail(CD) & Deductee Detail(DD)...........
		     */
		  
		    int deducteeCount =0;
		    /**
		     * this loops is used to calculate the total number of deductee record as per challan SR.NO
		     * and also 
		     */
		    int countRecNo=3;
		    int z=0;
		    String chalRecordno="";
		    String bankChallanNo="";
		    String deducteSrno="";
		    String totalTaxDeposited = "";//THIS IS USED TO STORE THE TOTAL VALUE FROM DEDUCTEE SHEET FOR COLUMN NO 323
		   
		    
		    for (int i = 1; i <= challanSheet.getLastRowNum(); i++) {//challan for loop
		    	HSSFRow challanRow = challanSheet.getRow(i);
				HSSFCell challansSrno = challanRow.getCell((short)0);
				HSSFCell bankChallanCol = challanRow.getCell((short)11);
				if(challansSrno.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					chalRecordno=String.valueOf(challansSrno.getNumericCellValue());
					//logger.info("Employee Code in if------"+add[c][0]);
				}else if(challansSrno.getCellType() == HSSFCell.CELL_TYPE_STRING){
					chalRecordno= challansSrno.getStringCellValue();
					//logger.info("Employee Code in else if------"+add[c][0]);
				}
				if(bankChallanCol.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
					bankChallanNo=String.valueOf(bankChallanCol.getNumericCellValue());
					//logger.info("Employee Code in if------"+add[c][0]);
				}else if(bankChallanCol.getCellType() == HSSFCell.CELL_TYPE_STRING){
					bankChallanNo= bankChallanCol.getStringCellValue();
					//logger.info("Employee Code in else if------"+add[c][0]);
				}
				// logger.info("ChalRecordno     :"+ChalRecordno);
				 deducteeCount=0;
				 /**
				  * this loop is run according to the rows in the Deductee sheet
				  */
				 for (int j = 1; j <= deducteeSheet.getLastRowNum(); j++) {//deductee for loop
					 HSSFRow deducteeRow = deducteeSheet.getRow(j);
						HSSFCell deducteeSrno = deducteeRow.getCell((short)1);
						if(deducteeSrno.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							deducteSrno=String.valueOf(deducteeSrno.getNumericCellValue());
							//logger.info("Employee Code in if------"+add[c][0]);
						}else if(deducteeSrno.getCellType() == HSSFCell.CELL_TYPE_STRING){
							deducteSrno= deducteeSrno.getStringCellValue();
							//logger.info("Employee Code in else if------"+add[c][0]);
						}
						// deducteSrno = String.valueOf(Math.round(Double.parseDouble(String.valueOf(deducteeSrno.getNumericCellValue()))));
						
						 /**
						  * this if condition is used to compare the Challan Sr.No from the values in the deductee sheet
						  * only those record is being selected which matches the challan sr.no
						  */
						if(deducteSrno.equals(chalRecordno)){///condition check
							// logger.info("deducteSrno    :"+deducteSrno);
							 deducteeCount++;
						 }
				 }
				 
				
				 
				 ////////////////////////////////////////////////
				 Object[][] deducteeValues = new Object[deducteeCount][14];
				 int xyz=0;///this is used as a count in the Object deducteeValues below
				 ////////2nd deductee forloop////
				 for (int j = 1; j <= deducteeSheet.getLastRowNum(); j++) {//deductee for loop
					 HSSFRow deducteeRow = deducteeSheet.getRow(j);
						HSSFCell deducteeSrno = deducteeRow.getCell((short)1);
						if(deducteeSrno.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							deducteSrno=String.valueOf(deducteeSrno.getNumericCellValue());
							//logger.info("Employee Code in if------"+add[c][0]);
						}else if(deducteeSrno.getCellType() == HSSFCell.CELL_TYPE_STRING){
							deducteSrno= deducteeSrno.getStringCellValue();
							//logger.info("Employee Code in else if------"+add[c][0]);
						}
						// deducteSrno = String.valueOf(Math.round(Double.parseDouble(String.valueOf(deducteeSrno.getNumericCellValue()))));
						//logger.info("--------------------deducteSrno ===="+deducteSrno);
						//logger.info("--------------------ChalRecordno ===="+ChalRecordno);
						 /**
						  * this if condition is used to compare the Challan Sr.No from the values in the deductee sheet
						  * only those record is being selected which matches the challan sr.no
						  */
						if(deducteSrno.equals(chalRecordno)){
							 //logger.info("in if of deductee xyz   :"+xyz);
						 		 totalTaxDeposited="";
								 z=0;
								 for(int c=0; c<17;c++){
									
									 if(c!=3 && c!=4 && c!=9){
										 
										 //logger.info("-------------- c   :"+c);
									 HSSFRow deduteeSheet    = deducteeSheet.getRow(j);
									 HSSFCell challanCellValue   = deduteeSheet.getCell((short)c);
									 //logger.info("value of j=="+j);
									 if(challanCellValue.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
										 totalTaxDeposited = String.valueOf(challanCellValue.getNumericCellValue());
											//logger.info("Employee Code in if------"+add[c][0]);
										}else if(challanCellValue.getCellType() == HSSFCell.CELL_TYPE_STRING){
											totalTaxDeposited =challanCellValue.getStringCellValue();
											//logger.info("Employee Code in else if------"+add[c][0]);
										}
									// logger.info("totalTaxDeposited  :"+totalTaxDeposited);
									 deducteeValues[xyz][z] = totalTaxDeposited;
									 
									 //logger.info("z  :"+z);
									// logger.info("deducteeValues[0][z]  :"+deducteeValues[xyz][z]);
									 z++;
								 }
								}
								 xyz++;
						 }	 
				 }
				 ////end of 2nd for loop////
				 ///start of object for total of columns/////
				 Object[][] totValDedutee = new Object[1][5];
				 int abc=0;
				    	double totDed=0;
				    	for(int g=7;g<12;g++){
				    		totDed=0;
				    	    for (int a = 0; a < deducteeCount; a++) {
				    		// logger.info(" deducteeValues[a][g] =================  :"+ deducteeValues[a][g]);
				    		totDed += Double.parseDouble(String.valueOf(deducteeValues[a][g]));
				    	    }
				    	    totValDedutee[0][abc]= Utility.twoDecimals(totDed);	
				    	   // logger.info(" totValDedutee[0][a] =================  :"+ totValDedutee[0][abc]);
				    	    abc++;
				    	}
				    	
						////challan write///
							
						 	String challanObj="",bookEntryChallan="";	
							Object challanData[][] = new Object[1][15];
							HSSFRow challanRowFinal = challanSheet.getRow(i);
							int e=0;
							for(int d=0;d<15;d++){
								String currDate="";
								HSSFCell challanFinal = challanRowFinal.getCell((short)d);
								if(d==10){
									String dateString = "";
									if(challanFinal.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
										challanFinal.getDateCellValue();
										Date chalDate = challanFinal.getDateCellValue();	
										SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
										currDate = sdf.format(chalDate); 
										challanData[0][e] = currDate;
											//logger.info("Employee Code in if------"+add[c][0]);
										}else if(challanFinal.getCellType() == HSSFCell.CELL_TYPE_STRING){
											dateString =challanFinal.getStringCellValue();
											logger.info("dateString   :"+dateString);
											//logger.info("Employee Code in else if------"+add[c][0]);
											challanData[0][e] = dateString;
										}
								}
								else{
									if(challanFinal.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
										challanObj = String.valueOf(challanFinal.getNumericCellValue());
										logger.info("challanObj   :"+challanObj);
											//logger.info("Employee Code in if------"+add[c][0]);
										}else if(challanFinal.getCellType() == HSSFCell.CELL_TYPE_STRING){
											challanObj =challanFinal.getStringCellValue();
											logger.info("challanObj   :"+challanObj);
											//logger.info("Employee Code in else if------"+add[c][0]);
										}
									challanData[0][e] = challanObj;
								}
								//logger.info("challanData[0][e]    :"+challanData[0][e]);
								e++;
							}
							if(String.valueOf(challanData[0][12]).equals("Yes")){
								bookEntryChallan="Y";
							}
							else{
								bookEntryChallan="N";
							}
							/**
							 * challanData[0][1]=Section-Code,
							 * challanData[0][2]=TDS,
							 * challanData[0][3]=Surcharge,
							 * challanData[0][4]=Education Cess,
							 * challanData[0][5]=Interest Amount,
							 * challanData[0][6]=Other Amount
							 * challanData[0][7]=Total Tax Deposited,
							 * challanData[0][8]=,challanData[0][9]=
							 * totValDedutee[0][0]='TDS / TCS -Income Tax',
							 * totValDedutee[0][1]='TDS / TCS -Surcharge'
							 * totValDedutee[0][2]='TDS / TCS - Cess',
							 * totValDedutee[0][3]=Total (TDS+Surcharge+cess)
							 * ,totValDedutee[0][4]=Total Tax Deposit as per deductee
							 * 
							 */
							logger.info("transac date    :"+challanData[0][10]);
							//logger.info("bsr code    :"+challanData[0][9]);
							String transactionDate[] = String.valueOf(challanData[0][10]).split("-");
							String challan = ""+countRecNo+"^CD^1^"+Math.round(Double.parseDouble(chalRecordno))+"^"+deducteeCount+"^" +
					    	"N^^^^^^"+bankChallanNo+"^^^^"+challanData[0][9]+"^^" +   // challanData[0][9]=Bank BSR code
					    	""+transactionDate[0]+""+transactionDate[1]+""+transactionDate[2]  // [0]DD  [1]MM  [2]YYYY
					    	+"^^^"+challanData[0][1]+"^" +
					    	""+Utility.twoDecimals(String.valueOf(challanData[0][2]))+"^"+Utility.twoDecimals(String.valueOf(challanData[0][3]))+"^" +
					    	""+Utility.twoDecimals(String.valueOf(challanData[0][4]))+"^"+Utility.twoDecimals(String.valueOf(challanData[0][5]))+"^"+Utility.twoDecimals(String.valueOf(challanData[0][6]))+"^" +
					    	""+Utility.twoDecimals(String.valueOf(challanData[0][7]))+"^^"+totValDedutee[0][4]+"^"+totValDedutee[0][0]+"^"+totValDedutee[0][1]+"^" +
					    	""+totValDedutee[0][2]+"^"+totValDedutee[0][3]+"^0.00^0.00^0^"+bookEntryChallan+"^^";
					    	output.write(challan);
					    	output.println();
					    	
					    	////deductee for loop for writing////////
					    	for(int b=0;b<deducteeValues.length;b++){
					    		countRecNo++;
					    		String depositDate[] = String.valueOf(deducteeValues[b][13]).split("-");
					    		String deductionDate[] = String.valueOf(deducteeValues[b][12]).split("-");
					    		String deductee="";
					    		if(String.valueOf(deducteeValues[b][3]).trim().equals("PANNOTAVBL")){
					    			//challanData[0][0]==running sequence number
					    			/**
					    			 * deducteeValues[b][0]=sequence no Deductee ,
					    			 * deducteeValues[b][1]=,
					    			 * deducteeValues[b][2]=Employee SR no
					    			 * deducteeValues[b][3]=Emp PAN,
					    			 * deducteeValues[b][4]=Emp Name,
					    			 * deducteeValues[b][5]=
					    			 * deducteeValues[b][6]=credit amount,
					    			 * deducteeValues[b][7]=TDS,
					    			 * deducteeValues[b][8]=Surcharge
					    			 * deducteeValues[b][9]=cess,
					    			 * deducteeValues[b][10]=Total (TDS+Surcharge+cess),
					    			 * deducteeValues[b][11]=TDS deposited 
					    			 */
					    			deductee =""+countRecNo+"^DD^1^"+Math.round(Double.parseDouble(String.valueOf(challanData[0][0])))+"^"+Math.round(Double.parseDouble(String.valueOf(deducteeValues[b][0])))+"^O^" +
						    		""+Math.round(Double.parseDouble(String.valueOf(deducteeValues[b][2])))+"^^^"+deducteeValues[b][3]+"^^^"+deducteeValues[b][4]+"^"+Utility.twoDecimals(String.valueOf(deducteeValues[b][7]))+"^" +
						    		""+Utility.twoDecimals(String.valueOf(deducteeValues[b][8]))+"^"+Utility.twoDecimals(String.valueOf(deducteeValues[b][9]))+"^"+Utility.twoDecimals(String.valueOf(deducteeValues[b][10]))+"^^" +
						    		""+Utility.twoDecimals(String.valueOf(deducteeValues[b][11]))+"^^^"+Utility.twoDecimals(String.valueOf(deducteeValues[b][6]))+"^"+depositDate[0]+""+depositDate[1]+"" +
						    		""+depositDate[2]+"^"+deductionDate[0]+""+deductionDate[1]+""+deductionDate[2]+"^"+transactionDate[0]+""+transactionDate[1]+""+transactionDate[2]+"^^^^^C^^^";
					    		} //end of if
					    		else{
					    			/*logger.info("value of b=="+b);
					    			logger.info("String.valueOf(deducteeValues[b][11])=="+String.valueOf(deducteeValues[b][11]));
					    			logger.info("String.valueOf(deducteeValues[b][6])=="+String.valueOf(deducteeValues[b][6]));
					    			logger.info("depositDate[0]=="+depositDate[0]);
					    			logger.info("depositDate[1]=="+depositDate[1]);*/
					    			deductee =""+countRecNo+"^DD^1^"+Math.round(Double.parseDouble(String.valueOf(challanData[0][0])))+"^"+Math.round(Double.parseDouble(String.valueOf(deducteeValues[b][0])))+"^O^" +
						    		""+Math.round(Double.parseDouble(String.valueOf(deducteeValues[b][2])))+"^^^"+deducteeValues[b][3]+"^^^"+deducteeValues[b][4]+"^"+Utility.twoDecimals(String.valueOf(deducteeValues[b][7]))+"^" +
						    		""+Utility.twoDecimals(String.valueOf(deducteeValues[b][8]))+"^"+Utility.twoDecimals(String.valueOf(deducteeValues[b][9]))+"^"+Utility.twoDecimals(String.valueOf(deducteeValues[b][10]))+"^^" +
						    		""+Utility.twoDecimals(String.valueOf(deducteeValues[b][11]))+"^^^"+Utility.twoDecimals(String.valueOf(deducteeValues[b][6]))+"^"+depositDate[0]+""+depositDate[1]+"" +
						    		""+depositDate[2]+"^"+deductionDate[0]+""+deductionDate[1]+""+deductionDate[2]+"^"+transactionDate[0]+""+transactionDate[1]+""+transactionDate[2]+"^^^^^^^^";
					    		} ////end of else
					    		
					    		 output.write(deductee);
							     output.println();
					    	}
					    	/////end of loop////////////////////////
					    	
					countRecNo++;
		    }//end of challan loop
		    
		    /**
		     * this loop is used to read the SALARY SHEET record....It is written only for quarter(Q4)
		     */
		    if(data[1].equals("Q4")){
		    	//logger.info("data[1]======in q4====="+data[1]);
		    	int salCount= countRecNo;
			    String salData="";
			    String empCategory="";
			    Object salaryObj[][]=new Object [salarySheet.getLastRowNum()][24];
			   // logger.info("salaryObj======in q4====="+salaryObj.length);
			    try {
					for (int i = 1; i <= salarySheet.getLastRowNum(); i++) {
						for (int d = 0; d < 24; d++) {
							HSSFRow salaryRow = salarySheet.getRow(i);
							HSSFCell salaryCell = salaryRow.getCell((short) d);
							//logger.info("VALUE OF D====="+d);
							//logger.info("VALUE OF i====="+i);
							try{
							if (salaryCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								salData = String.valueOf(salaryCell
										.getNumericCellValue());
								salData =String.valueOf(formatter.format(Double.parseDouble(salData)));
							} else if (salaryCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
								salData = salaryCell.getStringCellValue();
							}
							}catch (Exception e) {
								salData="";
							}
							
							salaryObj[i - 1][d] = salData;
							//logger.info("salaryObj[i-1][d]   :"+ salaryObj[i - 1][d]);
						}
					}
					
					for(int i=0;i<salaryObj.length;i++){
						if(salaryObj[i][4].equals("MALE")){
							empCategory="G";
						}
						else if(salaryObj[i][4].equals("FEMALE")){
							empCategory="W";
						}
						else if(salaryObj[i][4].equals("OTHER")){
							empCategory="S";
						}
						double secII=0.00,dedSecCCE=0.0;
						String countSec="0",countSecCCE="0";
						/*
						 * salaryObj[i][0]=Records no,
						 * salaryObj[i][1]=PAN,
						 * salaryObj[i][2]=PAN ref no.,
						 * salaryObj[i][3]=Emp Nme,
						 * salaryObj[i][4]=Gender,
						 * salaryObj[i][5]=DOJ
						 * salaryObj[i][6]=Leave Date,
						 * salaryObj[i][7]=Salary Amount,
						 * salaryObj[i][8]=Allowance amount,
						 * salaryObj[i][9]=PTAX,
						 * salaryObj[i][10]=col 334,
						 * salaryObj[i][11]=col 335
						 * salaryObj[i][12]=,
						 * salaryObj[i][12]=Gross income,
						 * salaryObj[i][13]=Deductions 80CCE,
						 * salaryObj[i][14]=Deductions other than 80CCE,
						 * salaryObj[i][15]=total deduction,
						 * salaryObj[i][16]=Taxable income
						 * salaryObj[i][17]=,salaryObj[i][18]=TDS,
						 * salaryObj[i][19]=Surcharge,
						 * salaryObj[i][20]=Cess,
						 * salaryObj[i][21]=Total Tax,
						 * salaryObj[i][22]=total Tax deducted,
						 * salaryObj[i][23]=shortfall of tax
						 * 
						 */
						/**
						 * this if/ else if condition is check for column (333)
						 */
						String dedSecII="",dedSecIII="",secCCE="",secOther="";
						if((String.valueOf(salaryObj[i][8]).equals("0")||String.valueOf(salaryObj[i][8]).equals("0.0")) && !(String.valueOf(salaryObj[i][9]).equals("0")||String.valueOf(salaryObj[i][9]).equals("0.0"))){
							secII=Double.parseDouble(String.valueOf(salaryObj[i][9]));
							countSec="1";
							dedSecII="^S16^1^"+salaryObj[i][0]+"^1^16(iii)^"+Utility.twoDecimals(secII)+"^";
						}
						else if(!(String.valueOf(salaryObj[i][8]).equals("0")||String.valueOf(salaryObj[i][8]).equals("0.0")) && (String.valueOf(salaryObj[i][9]).equals("0")||String.valueOf(salaryObj[i][9]).equals("0.0"))){
							secII=Double.parseDouble(String.valueOf(salaryObj[i][8]));
							countSec="1";
							dedSecII="^S16^1^"+salaryObj[i][0]+"^1^16(ii)^"+Utility.twoDecimals(secII)+"^";
						}
						else if(!(String.valueOf(salaryObj[i][8]).equals("0")||String.valueOf(salaryObj[i][8]).equals("0.0")) && !(String.valueOf(salaryObj[i][9]).equals("0")||String.valueOf(salaryObj[i][9]).equals("0.0"))){
							secII=Double.parseDouble(String.valueOf(salaryObj[i][8]))+Double.parseDouble(String.valueOf(salaryObj[i][9]));
							countSec="2";
							dedSecII="^S16^1^"+salaryObj[i][0]+"^1^16(ii)^"+Utility.twoDecimals((String.valueOf(salaryObj[i][8])))+"^";
							dedSecIII="^S16^1^"+salaryObj[i][0]+"^2^16(iii)^"+Utility.twoDecimals((String.valueOf(salaryObj[i][9])))+"^";
						}
						
						/**
						 * this if/ else if condition is check for column (337 & 338)
						 */
						if((String.valueOf(salaryObj[i][13]).equals("0")||String.valueOf(salaryObj[i][13]).equals("0.0")) && !(String.valueOf(salaryObj[i][14]).equals("0")||String.valueOf(salaryObj[i][14]).equals("0.0"))){
							dedSecCCE=Double.parseDouble(String.valueOf(salaryObj[i][14]));
							countSecCCE="1";
							secCCE="^C6A^1^"+salaryObj[i][0]+"^1^OTHERS^"+Utility.twoDecimals(dedSecCCE)+"^";
						}
						else if(!(String.valueOf(salaryObj[i][13]).equals("0")||String.valueOf(salaryObj[i][13]).equals("0.0")) && (String.valueOf(salaryObj[i][14]).equals("0")||String.valueOf(salaryObj[i][14]).equals("0.0"))){
							dedSecCCE=Double.parseDouble(String.valueOf(salaryObj[i][13]));
							countSecCCE="1";
							secCCE="^C6A^1^"+salaryObj[i][0]+"^1^80CCE^"+Utility.twoDecimals(dedSecCCE)+"^";
						}
						else if(!(String.valueOf(salaryObj[i][13]).equals("0")||String.valueOf(salaryObj[i][13]).equals("0.0")) && !(String.valueOf(salaryObj[i][14]).equals("0")||String.valueOf(salaryObj[i][14]).equals("0.0"))){
							dedSecCCE=Double.parseDouble(String.valueOf(salaryObj[i][13]))+Double.parseDouble(String.valueOf(salaryObj[i][14]));
							countSecCCE="2";
							secCCE="^C6A^1^"+salaryObj[i][0]+"^1^80CCE^"+Utility.twoDecimals(String.valueOf(salaryObj[i][13]))+"^";
							secOther="^C6A^1^"+salaryObj[i][0]+"^2^OTHERS^"+Utility.twoDecimals(String.valueOf(salaryObj[i][14]))+"^";
						}
						 String frmEmployedDate[] = String.valueOf(salaryObj[i][5]).split("/");
						 String uptoEmployedDate[] = String.valueOf(salaryObj[i][6]).split("/");
						 String salary=""+salCount+"^SD^1^"+salaryObj[i][0]+"^A^^"+salaryObj[i][1]+"^^"+salaryObj[i][3]+"^"+empCategory+"^" +
						 ""+frmEmployedDate[0]+""+frmEmployedDate[1]+""+frmEmployedDate[2]+"^"+uptoEmployedDate[0]+""+uptoEmployedDate[1]+""+uptoEmployedDate[2]+"^" +
						 ""+Utility.twoDecimals(String.valueOf(salaryObj[i][7]))+"^^"+countSec+"^"+Utility.twoDecimals(secII)+"^"+Utility.twoDecimals(String.valueOf(salaryObj[i][10]))+"^" +
						 ""+Utility.twoDecimals(String.valueOf(salaryObj[i][11]))+"^"+Utility.twoDecimals(String.valueOf(salaryObj[i][12]))+"^^"+countSecCCE+"^"+Utility.twoDecimals(dedSecCCE)+"^" +
						 ""+Utility.twoDecimals(String.valueOf(salaryObj[i][16]))+"^"+Utility.twoDecimals(String.valueOf(salaryObj[i][17]))+"^"+Utility.twoDecimals(String.valueOf(salaryObj[i][18]))+"^" +
						 ""+Utility.twoDecimals(String.valueOf(salaryObj[i][19]))+"^"+Utility.twoDecimals(String.valueOf(salaryObj[i][20]))+"^"+Utility.twoDecimals(String.valueOf(salaryObj[i][21]))+"^" +
						 ""+Utility.twoDecimals(String.valueOf(salaryObj[i][22]))+"^"+Utility.twoDecimals(String.valueOf(salaryObj[i][23]))+"^^^^";
						 output.write(salary);
						 output.println();
						 salCount++;
						 
						 if(countSec.equals("1")){
							 output.write(""+salCount+""+dedSecII+"");
							 output.println();
							 salCount++;
						 }
						 else if(countSec.equals("2")){
							 output.write(""+salCount+""+dedSecII+"");
							 output.println();
							 salCount++;
							 output.write(""+salCount+""+dedSecIII+"");
							 output.println();
							 salCount++;
						 }
						 
						 if(countSecCCE.equals("1")){
							 output.write(""+salCount+""+secCCE+"");
							 output.println();
							 salCount++;
						 }
						 else if(countSecCCE.equals("2")){
							 output.write(""+salCount+""+secCCE+"");
							 output.println();
							 salCount++;
							 output.write(""+salCount+""+secOther+"");
							 output.println();
							 salCount++;
						 }
					}
				} catch (Exception e) {
					logger.error("error in creating salary object/writing");
					e.printStackTrace();
				}
		    }
			/**
		     * this Object deducteeValues is created according to the deducteeCount, i.e- total number of 
		     * deductees w.r.t challan in the deductee sheet
		     */
		   
		   output.close();
		   String quaYear=String.valueOf(data[0])+"_"+String.valueOf(data[1]);
		   OutputStream oStream = null;
			FileInputStream fsStream = null;
			try {
				oStream = response.getOutputStream();
			} catch (Exception e) {
				System.out.println("--------------");
				e.printStackTrace();
			} //end of catch
			try {
				System.out.println("hashFileName  :"+output);
				System.out.println("htmlErrorFileName  :"+output);
				
					response.setHeader("Content-type", "html/text");
					response.setHeader("Content-disposition",
							"attachment;filename=\"" + quaYear+".txt" + "\"");
					fsStream = new FileInputStream(file);
				
				int iChar;
				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				// TODO: handle exception
				
			}
			finally{
				if (fsStream != null) {
					fsStream.close();
				}
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				}
				
			}
		  //model.getBtnValidate(response, getText("data_path"),quaYear,eTds);
		 } catch (Exception e) {
			logger.error("in generate ascii");
			e.printStackTrace();
		}
		 
		 
		return null;
	}

	
}
