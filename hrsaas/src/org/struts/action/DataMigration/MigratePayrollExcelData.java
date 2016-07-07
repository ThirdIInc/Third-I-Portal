/************************************************************************ 
 ** Modified by Prashant Shinde for payroll & promotion September 2011 **
 ************************************************************************/

package org.struts.action.DataMigration;

import java.io.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.*;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class MigratePayrollExcelData {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MigratePayrollExcelData.class);
	public static final int STRING_TYPE = 0;
	public static final int DATE_TYPE = 1;
	public static final int NUMBER_INTEGER_TYPE = 2;
	public static final int NUMBER_DOUBLE_TYPE = 3;
	public static final int MASTER_TYPE = 4;
	public static final int STRING_TYPE_WITHOUT_SPL_CHARS = 5;
	public static final int YEAR_TYPE = 6;
	public static final int MASTER_DATE_TYPE = 7;
	public static final int NUMBER_DOUBLE_TYPE_NEGATIVE = 8;
	public static final int IS_DUPLICATE = 9;
	public static final int HOURS_TYPE = 10;
	/*Changed to 1 from 2 i.e reads first sheet from excel file*/
	private static final int DATA_SHEET_NUMBER = 1; 
	private static final int START_ROW_NO = 2; 
	private static int lastRowNo = 0, lastColNo = 0;
	public static int totalRecords = 0;
	public static final boolean MANDATORY = true;
	public static final boolean NON_MANDATORY = false;
	private static boolean fileToBeUploaded = false;
	private static Object[][] uploadData = null;
	private static String[][] colNames = null;
	private static String PATH_OF_FILE = "";
	private static final String MANDATORY_CHAR = "*";
	private static HSSFWorkbook workbook = null;
	private static HSSFSheet sheet = null;
	
	private MigratePayrollExcelData() {
	}
	
	public static void downloadTemplateWithData(HttpServletRequest request,
			HttpServletResponse response, String dataPath,
			String[] listOfColumns, Object[][] data, String applicationName,
			String client) {
		try {
			String[][] columnNames = getColumnNames(dataPath, listOfColumns);

			String templateName = "PeoplePower_Partially_Migrate.xls";
			String templateFilePath = dataPath + "/DataMigration/Templates/"	+ templateName;

			String downloadFileName = "PeoplePower_Employee_" + applicationName	+ ".xls";
			String downloadFilePath = dataPath + "/DataMigration/" + client + "/Templates/" + downloadFileName;

			File clientFile = new File(downloadFilePath);
			new File(dataPath + "/DataMigration/" + client + "/Templates").mkdirs();
			clientFile.createNewFile();

			FileInputStream fileInputStream = new FileInputStream(new File(templateFilePath));
			FileOutputStream fileOutputStream = new FileOutputStream(clientFile);

			byte[] buf = new byte[1024];
			int len;
			while ((len = fileInputStream.read(buf)) > 0) {
				fileOutputStream.write(buf, 0, len);
			}

			FileInputStream uploadXls = new FileInputStream(new File(downloadFilePath));
			HSSFWorkbook workbook = new HSSFWorkbook(uploadXls);
			HSSFSheet sheet = workbook.getSheetAt(1);

			ReportGenerator rg = new ReportGenerator("Xls", "Sample", workbook,	sheet);

			int[] cellWidth = new int[columnNames[0].length];
			for (int i = 0; i < cellWidth.length; i++) {
				cellWidth[i] = 20;
			}

			int[] cellAlign = new int[columnNames[0].length];
			for (int i = 0; i < cellAlign.length; i++) {
				cellAlign[i] = 0;
			}
			int[] colsAsDouble = {};

			rg.tableBodyAsText(columnNames, data, cellWidth, cellAlign, colsAsDouble);
			FileOutputStream fio = new FileOutputStream(new File(downloadFilePath));
			workbook.write(fio);

			openTemplate(request, response, downloadFileName, downloadFilePath);
		} catch (Exception e) {
			logger.error("Exception in downloadTemplate:" + e);
		}
	}

	public static Object[][] getColumnInfo() {
		Object[][] columnInfo = null;
		try {
			HSSFRow rowForColNames = sheet.getRow(0);
			HSSFRow rowForColInfo = sheet.getRow(1);

			TreeMap<Integer, String> columnNameMap = new TreeMap<Integer, String>();
			TreeMap<Integer, String> columnInfoMap = new TreeMap<Integer, String>();

			/**
			 * Get total count of rows
			 */
			for (int i = 0; i < lastColNo; i++) {
				HSSFCell cellForColName = rowForColNames.getCell((short) i);
				HSSFCell cellForColInfo = rowForColInfo.getCell((short) i);

				if (cellForColName != null) {
					String cellValueForColName = cellForColName.getStringCellValue().trim();
					String cellValueForColInfo = cellForColInfo.getStringCellValue().trim();

					if (!(cellValueForColName.equals("") || cellValueForColName.equals("Integrity Status") || cellValueForColName.equals("Comments"))) {
						columnNameMap.put((i + 1), cellValueForColName);
						columnInfoMap.put((i + 1), cellValueForColInfo);
					}
				}
			}

			if (columnNameMap != null && columnNameMap.size() > 0) {
				columnInfo = new Object[columnNameMap.size()][3];
				int l = 0;
				for (Iterator<Integer> k = columnNameMap.keySet().iterator(); k.hasNext();) {
					columnInfo[l][0] = k.next();
					columnInfo[l][1] = columnNameMap.get(Integer.parseInt(String.valueOf(columnInfo[l][0])));
					columnInfo[l][2] = columnInfoMap.get(Integer.parseInt(String.valueOf(columnInfo[l][0])));
					l++;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getColumnInfo:" + e);
			e.printStackTrace();
		}
		return columnInfo;
	}

	private static String[][] getColumnNames(String dataPath, String[] listOfColumns) {
		String[][] columnNames = null;
		try {
			Properties properties = readColumnsInformation(dataPath);

			if (listOfColumns != null && listOfColumns.length > 0) {
				TreeMap<Integer, String> colNames = new TreeMap<Integer, String>();
				TreeMap<Integer, String> colInfo = new TreeMap<Integer, String>();

				for (int i = 0; i < listOfColumns.length; i++) {
					String column = listOfColumns[i];
					String columnName = properties.getProperty(column + ".name");
					String columnInfo = properties.getProperty(column + ".info");

					colNames.put(i, columnName);
					colInfo.put(i, columnInfo);
				}

				Collections.sort(new ArrayList<String>(colNames.values()));
				Collections.sort(new ArrayList<String>(colInfo.values()));

				if (colNames != null && colNames.size() > 0) {
					columnNames = new String[2][colNames.size()];
					int cnt = 0;
					for (Iterator<Integer> keyIt = colNames.keySet().iterator(); keyIt.hasNext();) {
						int key = keyIt.next();
						columnNames[0][cnt] = colNames.get(key);
						columnNames[1][cnt] = colInfo.get(key);
						cnt++;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getColumnNames:" + e);
		}
		return columnNames;
	}

	private static void getColumnNamesFromXLs() {
		try {
			HSSFRow rowForColNames = sheet.getRow(0);
			int totalCols = rowForColNames.getLastCellNum();
			ArrayList<String> columnNames = new ArrayList<String>();

			for (int i = 0; i < totalCols; i++) {
				HSSFCell cellForColName = rowForColNames.getCell((short) i);
				if (cellForColName != null) {
					String cellValueForColName = cellForColName.getStringCellValue();
					if (!(cellValueForColName.equals("") || cellValueForColName.equals("Integrity Status") || cellValueForColName.equals("Comments"))) {
						columnNames.add(cellValueForColName);
					}
				}
			}
			lastColNo = columnNames.size();

			/**
			 * Copy column names into object
			 */
			HSSFRow rowForInfo = sheet.getRow(1);
			colNames = new String[2][columnNames.size() + 2];
			int cnt = 0;
			for (int i = 0; i < columnNames.size(); i++) {
				// copy column name
				colNames[0][cnt] = columnNames.get(i);
				// copy information
				HSSFCell cellForInfo = rowForInfo.getCell((short) i);
				String cellValueForInfo = cellForInfo.getStringCellValue();
				colNames[1][cnt] = cellValueForInfo;
				cnt++;
			}
			colNames[0][cnt] = "Integrity Status";
			colNames[1][cnt] = " ";
			colNames[0][cnt + 1] = "Comments";
			colNames[1][cnt + 1] = " ";
		} catch (Exception e) {
			logger.error("Exception in getColumnNamesFromXLs:" + e);
		}
	}

	public static void getFile(String filePath) {
		try {
			PATH_OF_FILE = filePath;
			InputStream uploadXls = new FileInputStream(filePath);
			workbook = new HSSFWorkbook(uploadXls);
			sheet = workbook.getSheetAt(DATA_SHEET_NUMBER - 1);

			/*** Get columns names */
			getColumnNamesFromXLs();
			lastRowNo=0;
			/*** Get total count of rows */
			for (int rowNo = START_ROW_NO; rowNo <= sheet.getLastRowNum(); rowNo++) {
				HSSFRow curRow = sheet.getRow(rowNo);
				boolean isDataExists = false;
				if (curRow != null && lastColNo > 0) {
					for (int curCol = 0; curCol < lastColNo; curCol++) {
						HSSFCell cell = curRow.getCell((short) curCol);
						if (cell != null) {
							String cellValue = "";
							try {
								if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
									cellValue = String.valueOf(Math.round(Double.parseDouble(String.valueOf(cell.getNumericCellValue()).trim())));
								} else {
									cellValue = cell.getStringCellValue();
								}
							} catch (Exception e) {
								cellValue = cell.getStringCellValue();
							}
							if (!cellValue.trim().equals("")) {
								isDataExists = true;
								break;
							}
						}
					}
				}
				if (isDataExists) {
					lastRowNo++;
				}
			}

			/**
			 * Create an object for upload data
			 */
			int totalRows = lastRowNo;
			if (totalRows == 0) {
				totalRows = 1;
			}
			uploadData = new Object[totalRows][lastColNo + 2];
			fileToBeUploaded = true;
		} catch (Exception e) {
			logger.error("Exception in getFile :" + e);
			fileToBeUploaded = false;
		}
		/******************************************************************************************************************
		 *   Setting this variable to check if no of records in the file are within the allowed limit.                     *
		 *   NOTE - Excel row number starts from 0, To ignore first two header rows subtracting 1 row from the total rows.*
		 ******************************************************************************************************************/
		totalRecords = sheet.getLastRowNum()-1;
	}
	
	/** This function returns a map with the information about the mandatory columns mandatory with *
	 * @return - map
	 */
	public static HashMap<Integer, Boolean> isColumnsMandatory() {
		HashMap<Integer, Boolean> columnInformation = new HashMap<Integer, Boolean>(0);
		try {
			Object[][] columnInfo = getColumnInfo();
			if(columnInfo != null && columnInfo.length > 0){
				int colNo = 1;
				for(int i = 0; i < columnInfo.length; i++){
					String colInfo = String.valueOf(columnInfo[i][2]);
					if (colInfo.indexOf(MANDATORY_CHAR) == -1){
						columnInformation.put(colNo, false);
					}else{
						columnInformation.put(colNo, true);
					}
					colNo++;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getColumnsInformation:" + e);
		}
		return columnInformation;
	}
	
	/** This function returns a map with the information about the mandatory columns,
	 * Credits & Debits are marked as mandatory along with *.
	 * @author - Prashant (aa1383)
	 * @return - HashMap
	 */
	public static HashMap<Integer, Boolean> checkColumnsInfoForCreditsDebits() {
		HashMap<Integer, Boolean> columnInformation = new HashMap<Integer, Boolean>(0);
		try {
			Object[][] columnInfo = getColumnInfo();
			if (columnInfo != null && columnInfo.length > 0){
				int colNo = 1;
				for (int i = 0; i < columnInfo.length; i++){
					String colInfo = String.valueOf(columnInfo[i][2]);
					if(colInfo.trim().equals("CREDITS")){
						columnInformation.put(colNo, true);
					}
					if(colInfo.trim().equals("DEBITS")){
						columnInformation.put(colNo, true);
					}
					if(colInfo.trim().equals("*")){
						columnInformation.put(colNo, true);
					}
					if(colInfo.trim().equals("")){
						columnInformation.put(colNo, false);
					}
					colNo++;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getColumnsInformation:" + e);
		}
		return columnInformation;
	}
	
	/** This function returns a map with the information about the mandatory columns,
	 * Credits & Debits are marked as mandatory along with *.
	 * @author - Prashant (aa1383)
	 * @return - HashMap
	 */
	public static HashMap<Integer, String> checkColumnsInfo() {
		HashMap<Integer, String> columnInformation = new HashMap<Integer, String>(0);
		try{
			Object[][] columnInfo = getColumnInfo();
			if(columnInfo != null && columnInfo.length > 0){
				int colNo = 1;
				for(int i = 0; i < columnInfo.length; i++){
					String colInfo = String.valueOf(columnInfo[i][2]);
					
					/*if (colInfo.indexOf(MANDATORY_CHAR) == -1) {
						columnInformation.put(colNo, false);
					} else {
						columnInformation.put(colNo, true);
					}*/
					
					if(colInfo.trim().equals("CREDITS")){
						columnInformation.put(colNo, "CREDITS");
					}
					if(colInfo.trim().equals("DEBITS")){
						columnInformation.put(colNo, "DEBITS");
					}
					if(colInfo.trim().equals("*")){
						columnInformation.put(colNo, "TRUE");
					}
					if(colInfo.trim().equals("")){
						columnInformation.put(colNo, "FALSE");
					}
					colNo++;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getColumnsInformation:" + e);
		}
		return columnInformation;
	}
	
	/**This function checks the integrity status of only headers in the excel file 
	 * @author - Prashant Shinde (aa1383)
	 * @return - boolean
	 */
	public static boolean checkFileToBeUploaded(){
		System.out.println("in isFileToBeUploaded()"+fileToBeUploaded);
		if(!fileToBeUploaded){
			try {
				ReportGenerator rg = new ReportGenerator("Xls", "Sample", workbook,	sheet);
				int[] cellWidth = new int[colNames[0].length];
				for (int i = 0; i < cellWidth.length; i++) {
					cellWidth[i] = 20;
				}
				cellWidth[colNames[0].length-1] = 60;
				int[] cellAlign = new int[colNames[0].length];
				
				for (int i = 0; i < cellAlign.length; i++) {
					cellAlign[i] = 0;
				}
				int[] colsAsDouble = {};
				Object[][] color = new Object[uploadData.length][colNames[0].length];
				for (int i = 0; i < uploadData.length; i++) {
					for (int j = 0; j < uploadData[0].length-2; j++) {
						uploadData[i][j] = "";
						color[0][colNames[0].length-1] = Utility.RED;
						color[0][colNames[0].length-2] = Utility.RED;
					}
				}
				rg.tableBodyWithColor(colNames, uploadData, cellWidth, cellAlign, color);
				FileOutputStream fio = new FileOutputStream(new File(PATH_OF_FILE));
				workbook.write(fio);
				resetToNull();
			} catch (Exception e) {
				logger.error("Exception in isFileToBeUploaded:" + e);
				e.printStackTrace();
				fileToBeUploaded = false;
			}
			logger.info("fileToBeUploaded-" + fileToBeUploaded);
		}
		return fileToBeUploaded;
	}
	
	/**This function checks the integrity status of data in the excel file. 
	 * @return - boolean
	 */
	public static boolean isFileToBeUploaded() {
		System.out.println("in isFileToBeUploaded()"+fileToBeUploaded);
		try {
			ReportGenerator rg = new ReportGenerator("Xls", "Sample", workbook,	sheet);

			int[] cellWidth = new int[colNames[0].length];
			for (int i = 0; i < cellWidth.length; i++) {
				cellWidth[i] = 20;
			}
			//Setting width of last column
			cellWidth[cellWidth.length-1] = 60;
			
			int[] cellAlign = new int[colNames[0].length];
			for (int i = 0; i < cellAlign.length; i++) {
				cellAlign[i] = 2;
			}
			//Setting  alignment, width of first two columns
			cellAlign[0] = 1;
			cellAlign[1] = 1;
			cellWidth[1] = 30;
			
			Object[][] color = new Object[uploadData.length][colNames[0].length];		

			for (int i = 0; i < uploadData.length; i++) {
				color[i][0] = Utility.LIGHT_YELLOW;
				color[i][1] = Utility.LIGHT_YELLOW;
				
				for (int j = 0; j < uploadData[0].length; j++) {
					if(fileToBeUploaded){
						color[i][uploadData[0].length-1] = Utility.LIGHT_GREEN;
						color[i][uploadData[0].length-2] = Utility.LIGHT_GREEN;
						uploadData[i][uploadData[0].length-1] = "Data uploaded successfully";
					}else{
						if(String.valueOf(uploadData[i][uploadData[0].length-2]).equals("Fail")){
							//Setting color of last two columns when upload fails
							color[i][j] = Utility.RED;
						}else{
							color[i][uploadData[0].length-1] = Utility.LIGHT_GREEN;
							color[i][uploadData[0].length-2] = Utility.LIGHT_GREEN;
						}
					}
				}
			}

			rg.tableBodyWithColor(colNames, uploadData, cellWidth, cellAlign, color);
			FileOutputStream fio = new FileOutputStream(new File(PATH_OF_FILE));
			workbook.write(fio);
			resetToNull();
		} catch (Exception e) {
			logger.error("Exception in isFileToBeUploaded:" + e);
			e.printStackTrace();
			fileToBeUploaded = false;
		}
		logger.info("fileToBeUploaded-" + fileToBeUploaded);
		return fileToBeUploaded;
	}

	public static void openTemplate(HttpServletRequest request,	HttpServletResponse response, String templateName, String filePath) {
		try {
			OutputStream oStream = null;
			FileInputStream fsStream = null;
			try {
				response.setHeader("Content-type", "application/msexcel");
				response.setHeader("Content-disposition", "attachment;filename=\"" + templateName + "\"");
				int iChar;
				fsStream = new FileInputStream(filePath);
				oStream = response.getOutputStream();
				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}
			} catch (Exception e) {
				logger.error("Exception in openTemplate:" + e);
			} finally {
				if (fsStream != null) {
					fsStream.close();
				}
				if (oStream != null) {
					oStream.flush();
					oStream.close();
				}
			}
		} catch (Exception e) {
			logger.error("Exception in openTemplate:" + e);
		}
	}

	private static Properties readColumnsInformation(String dataPath) {
		Properties properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream(dataPath + "/DataMigration/Columns_Information.properties");
			properties.load(fileInputStream);
			fileInputStream.close();
		} catch (Exception e) {
			logger.error("Exception in readColumnsInformation:" + e);
		}
		return properties;
	}

	private static Object[][] uploadDateData(ArrayList<String> dataFromExcel, int colNo){
		Object[][] dateData = null;
		try {
			dateData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";

			for (int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++){
				if (!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || dataFromExcel.get(rowNo).equals(""))){
					excelValue = dataFromExcel.get(rowNo).trim();
				} else {
					excelValue = " ";
				}
				dateData[rowNo][0] = excelValue.trim();
				uploadData[rowNo][colNo] = excelValue;
				try{
					if(!excelValue.equals(" ")){
						int yearDigit = excelValue.split("/")[2].length();
						if (yearDigit != 4){
							throw new Exception();
						}
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
						sdf.setLenient(false);
						sdf.parse(excelValue);
					}
					writeStatusData(rowNo, "Success", "");
					if(fileToBeUploaded){
						fileToBeUploaded = true;
					}
				}catch(Exception e){
					String columnName = colNames[0][colNo];
					writeStatusData(rowNo, "Fail", "'" + excelValue + "' in '" + columnName + "' is not in 'mm/dd/yyyy' format.");
					fileToBeUploaded = false;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in uploadDateData:" + e);
			fileToBeUploaded = false;
		}
		return dateData;
	}
	
	/**
	 * This function checks if the headers in the excel file match with those from the master.
	 * Note - The master data object must always have unique code at index 0 & string at index 1.
	 * @author - Prashant Shinde (aa1383)
	 * @param colNo - Excel sheet column number
	 * @param dataFromMaster - Object of records from master table with unique id @ 0 index 
	 * @param dataType - data types defined in MigratePayrollExcelData class
	 * @return - Object[][]
	 */
	public static Object[][] uploadPayrollExcelData(int colNo, Object[][] dataFromMaster, int dataType){
		Object[][] excelData = null;
		try {
			colNo = colNo - 1;
			ArrayList<String> dataFromExcel = new ArrayList<String>(0);
			boolean isDataValid = true;
			int counter = 0;
			if (lastRowNo > 0) {
				HSSFRow row = sheet.getRow(0);
				HSSFCell cell = row.getCell((short) (colNo));
				String excelValue = "";
				if (cell != null) {
					excelValue = cell.getStringCellValue().trim();
				}
				dataFromExcel.add(excelValue);
			}
			uploadData[counter][colNo] = dataFromExcel.get(counter);
			excelData = uploadMasterData(dataFromExcel, dataFromMaster,	colNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return excelData;
	}
	
	/**
	 * This function checks if the data in the excel file is in correct format as per the data type.
	 * @param colNo - Excel sheet column number
	 * @param dataFromMaster - Object of records from master table with unique id @ 0 index if used 
	 * @param dataType - data types defined in MigratePayrollExcelData class
	 * @param isMandatory - checks if the data can be blank
	 * @return - Object[][]
	 */
	
	public static Object[][] uploadExcelData(int colNo,	Object[][] dataFromMaster, int dataType, boolean isMandatory) {
		System.out.println("isMandatory == "+ isMandatory);
		NumberFormat formatter = new DecimalFormat("#0.00");
		Object[][] excelData = null;
		try {
			colNo = colNo - 1;
			ArrayList<String> dataFromExcel = new ArrayList<String>(lastRowNo);
			boolean isDataValid = true;
			int counter = 0;
			if(lastRowNo > 0){
				for (int rowNo = START_ROW_NO; rowNo <= lastRowNo + 1; rowNo++){
					if(isDataValid){
						isDataValid = true;
					}
					HSSFRow row = sheet.getRow(rowNo);
					if(row != null){
						HSSFCell cell = row.getCell((short)(colNo));
						try{
							String excelValue = "";
							if(cell != null){
								if(dataType == DATE_TYPE){
									try{
										Date d = cell.getDateCellValue();
										Calendar cal = Calendar.getInstance();
										cal.setTime(d);
										excelValue = (cal.get(Calendar.MONTH) + 1)+ "/"	+ cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
									}catch(Exception e){
										if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
											excelValue = String.valueOf(Math.round(Double.parseDouble(String.valueOf(cell.getNumericCellValue()).trim())));
										}else{
											excelValue = cell.getStringCellValue().trim();
										}
									}
								}else if(dataType == NUMBER_INTEGER_TYPE){
									if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
										excelValue = String.valueOf(Math.round(Double.parseDouble(String.valueOf(cell.getNumericCellValue()).trim())));
									}else{
										excelValue = cell.getStringCellValue().trim();
									}
								}else if(dataType == NUMBER_DOUBLE_TYPE || dataType == NUMBER_DOUBLE_TYPE_NEGATIVE){
									if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
										excelValue =formatter.format(cell.getNumericCellValue());
									}else{
										excelValue = cell.getStringCellValue().trim();
									}
								}else if(dataType == MASTER_TYPE){
									if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
										excelValue = String.valueOf(Math.round(Double.parseDouble(String.valueOf(cell.getNumericCellValue()).trim())));
									}else{
										excelValue = cell.getStringCellValue().trim();
									}
								}else if(dataType == MASTER_DATE_TYPE){
									try{
										Date d = cell.getDateCellValue();
										Calendar cal = Calendar.getInstance();
										cal.setTime(d);
										excelValue = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
									}catch(Exception e){
										if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
											excelValue = String.valueOf(Math.round(Double.parseDouble(String.valueOf(cell.getNumericCellValue()).trim())));
										}else{
											excelValue = cell.getStringCellValue().trim();
										}
									}
								}
								else if(dataType == HOURS_TYPE){
									try{
										if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
											Date d = cell.getDateCellValue();
											Calendar cal = Calendar.getInstance();
											cal.setTime(d);
											excelValue = (cal.get(Calendar.HOUR))+ ":"	+ cal.get(Calendar.MINUTE);
										}else{
											excelValue = cell.getStringCellValue().trim();
										}
									}catch(Exception e){
										/*if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
											Date d = cell.getDateCellValue();
											Calendar cal = Calendar.getInstance();
											cal.setTime(d);
											excelValue = (cal.get(Calendar.HOUR))+ ":"	+ cal.get(Calendar.MINUTE);
										}else{
											excelValue = cell.getStringCellValue().trim();
										}*/
										if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
											Date d = cell.getDateCellValue();
											Calendar cal = Calendar.getInstance();
											cal.setTime(d);
											excelValue = (cal.get(Calendar.HOUR))+ ":"	+ cal.get(Calendar.MINUTE);
										}else{
											excelValue = cell.getStringCellValue().trim();
										}
										e.printStackTrace();
									}
								}else{
									if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
										excelValue = String.valueOf(Math.round(Double.parseDouble(String.valueOf(cell.getNumericCellValue()).trim())));
									}else{
										excelValue = cell.getStringCellValue().trim();
									}
								}
								dataFromExcel.add(excelValue);
							}else{
								dataFromExcel.add("");
							}
						}catch (Exception e){
							e.printStackTrace();
							logger.error("Exception in uploadExcelData" + e);
							dataFromExcel.add("");
						}
					}else{
						dataFromExcel.add("");
					}
					uploadData[counter][colNo] = dataFromExcel.get(counter);
					//System.out.println("dataFromExcel.get(counter)--"+ dataFromExcel.get(counter) + "-");
					if(isMandatory && dataFromExcel.get(counter).equals("")){
						String columnName = colNames[0][colNo];
						writeStatusData(counter, "Fail", columnName	+ " should not be blank.");
						fileToBeUploaded = false;
						isDataValid = false;
					}
					counter++;
				}
			}else{
				if(isMandatory){
					String columnName = colNames[0][colNo];
					writeStatusData(counter, "Fail", columnName	+ " should not be blank.");
					fileToBeUploaded = false;
					isDataValid = false;
				}
			}

			if(isDataValid && dataFromExcel != null && dataFromExcel.size() > 0){
				switch (dataType){
				case STRING_TYPE:
					excelData = uploadStringData(dataFromExcel, colNo);
					break;

				case DATE_TYPE:
					excelData = uploadDateData(dataFromExcel, colNo);
					break;

				case HOURS_TYPE:
					excelData = uploadDateData(dataFromExcel, colNo);
					break;
					
				case NUMBER_INTEGER_TYPE:
					excelData = uploadNumberData(dataFromExcel, colNo);
					break;

				case NUMBER_DOUBLE_TYPE:
					excelData = uploadNumberData(dataFromExcel, colNo);
					break;

				case NUMBER_DOUBLE_TYPE_NEGATIVE:
					excelData = uploadNumberNegativeData(dataFromExcel, colNo);
					break;

				case MASTER_TYPE:
					excelData = uploadMasterData(dataFromExcel, dataFromMaster,	colNo);
					break;

				case STRING_TYPE_WITHOUT_SPL_CHARS:
					excelData = uploadStringDataWithoutSplChars(dataFromExcel, colNo);
					break;

				case YEAR_TYPE:
					excelData = uploadYearData(dataFromExcel, colNo);
					break;

				case MASTER_DATE_TYPE:
					excelData = uploadMasterDateData(dataFromExcel,	dataFromMaster, colNo);
					break;

				case IS_DUPLICATE:
					excelData = checkDuplicate(dataFromExcel, colNo);
					break;

				default:
					break;
				}
			}
		}catch (Exception e){
			logger.error("Exception in uploadExcelData" + e);
			fileToBeUploaded = false;
		}
		return excelData;
	}
	
	/** This method checks for duplicate values in the excel sheet
	 * @author - Prashant Shinde (aa1383)
	 * @param dataFromExcel
	 * @param colNo
	 * @return duplicateData
	 */
	private static Object[][] checkDuplicate(ArrayList<String> dataFromExcel, int colNo){
		Object[][] duplicateData = null;
		try{
			duplicateData = new Object[dataFromExcel.size()][1];
			LinkedHashSet set = new LinkedHashSet();
			String excelValue = " ";
			boolean isDataDuplicate = false;
			for(int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++){
				excelValue = dataFromExcel.get(rowNo).trim();
				boolean val = set.add(dataFromExcel.get(rowNo));
				if(val == true){
					isDataDuplicate = false;
					duplicateData[rowNo][0] =  excelValue;
					writeStatusData(rowNo, "Success", "");
				}else{
					isDataDuplicate = true;
					duplicateData[rowNo][0] =  excelValue;
				}
				if(isDataDuplicate){
					duplicateData[rowNo][0] = "NA";
					String columnName = colNames[0][colNo];
					writeStatusData(rowNo, "Fail", "'" + excelValue	+ "' in '" + columnName	+ "' is duplicate.");
					fileToBeUploaded = false;
				}
			}
		}catch (Exception e){
			logger.error("Exception in uploadMasterData" + e);
			e.printStackTrace();
		}
		return duplicateData;
	}

	private static Object[][] uploadMasterData(ArrayList<String> dataFromExcel,	Object[][] dataFromMaster, int colNo) {
		Object[][] masterData = null;
		try{
			masterData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";

			for (int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++){
				if(!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || dataFromExcel.get(rowNo).equals(""))){
					excelValue = dataFromExcel.get(rowNo).trim();
				}else{
					excelValue = " ";
				}
				uploadData[rowNo][colNo] = excelValue;

				boolean isDataExists = false;

				if(dataFromMaster != null && dataFromMaster.length > 0){
					for(int j = 0; j < dataFromMaster.length; j++){
						String idFromMaster = String.valueOf(dataFromMaster[j][0]);
						String valueFromMaster = String.valueOf(dataFromMaster[j][1]).trim();

						if (excelValue.equalsIgnoreCase(valueFromMaster) || excelValue.equals(" ")){
							if(excelValue.equals(" ")){
								masterData[rowNo][0] = "";
							}else{
								masterData[rowNo][0] = idFromMaster;
							}
							writeStatusData(rowNo, "Success", "");
							isDataExists = true;
							if(fileToBeUploaded){
								fileToBeUploaded = true;
							}
							break;
						}
					}
					if(!isDataExists){
						masterData[rowNo][0] = "NA";
						String columnName = colNames[0][colNo];
						writeStatusData(rowNo, "Fail", "'" + excelValue	+ "' in '" + columnName	+ "' not available in master.");
						fileToBeUploaded = false;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in uploadMasterData" + e);
		}
		return masterData;
	}

	private static Object[][] uploadMasterDateData(ArrayList<String> dataFromExcel, Object[][] dataFromMaster, int colNo){
		Object[][] masterDateData = null;
		try {
			masterDateData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";
			for (int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++) {
				if(!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || dataFromExcel.get(rowNo).equals(""))){
					excelValue = dataFromExcel.get(rowNo).trim();
				}else{
					excelValue = " ";
				}
				uploadData[rowNo][colNo] = excelValue;
				boolean isDataExists = false;
				if(dataFromMaster != null && dataFromMaster.length > 0){
					for (int j = 0; j < dataFromMaster.length; j++){
						String idFromMaster = String.valueOf(dataFromMaster[j][0]);
						String valueFromMaster = String.valueOf(dataFromMaster[j][1]).trim();
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
							sdf.setLenient(false);
							Calendar cal = Calendar.getInstance();
							cal.setTime(sdf.parse(excelValue));
							String dateFromExcel = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH)	+ "/" + cal.get(Calendar.YEAR);
							cal.setTime(sdf.parse(valueFromMaster));
							String dateFromMaster = (cal.get(Calendar.MONTH) + 1) + "/"	+ cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
							if (dateFromExcel.equals(dateFromMaster) || excelValue.equals(" ")) {
								if (excelValue.equals(" ")) {
									masterDateData[rowNo][0] = "";
								} else {
									masterDateData[rowNo][0] = idFromMaster;
								}
								writeStatusData(rowNo, "Success", "");
								isDataExists = true;
								if (fileToBeUploaded) {
									fileToBeUploaded = true;
								}
								break;
							}
						} catch (Exception e) {}
					}
					if (!isDataExists) {
						masterDateData[rowNo][0] = "NA";
						String columnName = colNames[0][colNo];
						writeStatusData(rowNo, "Fail", "'" + excelValue + "' in '" + columnName	+ "' not available in master.");
						fileToBeUploaded = false;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in uploadMasterDateData:" + e);
		}
		return masterDateData;
	}

	private static Object[][] uploadNumberData(ArrayList<String> dataFromExcel, int colNo){
		Object[][] numberData = null;
		try {
			numberData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";
			for (int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++){
				if (!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || dataFromExcel.get(rowNo).equals(""))){
					excelValue = dataFromExcel.get(rowNo).trim();
				} else {
					excelValue = " ";
				}

				numberData[rowNo][0] = excelValue.trim();
				uploadData[rowNo][colNo] = excelValue;

				Pattern pattern = Pattern.compile("^[+]?\\d+(\\.\\d+)?$");
				Matcher matcher = pattern.matcher(excelValue);

				if (matcher.matches() || excelValue.equals(" ")){
					writeStatusData(rowNo, "Success", "");
					if (fileToBeUploaded) {
						fileToBeUploaded = true;
					}
				} else {
					String columnName = colNames[0][colNo];
					writeStatusData(rowNo, "Fail", "'" + excelValue + "' in '" + columnName + "' is not in proper format.");
					fileToBeUploaded = false;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in uploadNumberData" + e);
		}
		return numberData;
	}

	private static Object[][] uploadNumberNegativeData(ArrayList<String> dataFromExcel, int colNo) {
		Object[][] numberData = null;
		try{
			numberData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";

			for(int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++){
				if(!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || dataFromExcel.get(rowNo).equals(""))){
					excelValue = dataFromExcel.get(rowNo).trim();
				} else {
					excelValue = " ";
				}
				numberData[rowNo][0] = excelValue.trim();
				uploadData[rowNo][colNo] = excelValue;

				Pattern pattern = Pattern.compile("^[+,-]?\\d+(\\.\\d+)?$");
				Matcher matcher = pattern.matcher(excelValue);

				if(matcher.matches() || excelValue.equals(" ")){
					writeStatusData(rowNo, "Success", "");
					if (fileToBeUploaded){
						fileToBeUploaded = true;
					}
				} else {
					String columnName = colNames[0][colNo];
					writeStatusData(rowNo, "Fail", "'" + excelValue + "' in '" + columnName + "' is not in proper format.");
					fileToBeUploaded = false;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in uploadNumberData" + e);
		}
		return numberData;
	}

	private static Object[][] uploadStringData(ArrayList<String> dataFromExcel, int colNo) {
		Object[][] stringData = null;
		try {
			stringData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";

			for(int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++){
				if(!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || dataFromExcel.get(rowNo).equals(""))){
					excelValue = dataFromExcel.get(rowNo).trim();
				}else{
					excelValue = " ";
				}
				stringData[rowNo][0] = excelValue.trim();
				uploadData[rowNo][colNo] = excelValue;
				writeStatusData(rowNo, "Success", "");
				if (fileToBeUploaded){
					fileToBeUploaded = true;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in uploadStringData" + e);
		}
		return stringData;
	}

	private static Object[][] uploadStringDataWithoutSplChars(ArrayList<String> dataFromExcel, int colNo){
		Object[][] stringData = null;
		try {
			stringData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";
			// char[] specialChars = {'!', '@', '#', '$', '%', '^', '&', '*',
			// '(', ')', '~', '`', '|'};
			char[] specialChars = { '\\', '\'', '\"' };

			for (int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++){
				if (!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || dataFromExcel.get(rowNo).equals(""))){
					excelValue = dataFromExcel.get(rowNo).trim();
				} else {
					excelValue = " ";
				}
				stringData[rowNo][0] = excelValue.trim();
				uploadData[rowNo][colNo] = excelValue;
				char[] excelValueChars = excelValue.toCharArray();
				boolean foundSplChars = false;

				if (!excelValue.equals(" ")){
					for (int i = 0; i < excelValueChars.length; i++){
						for (int j = 0; j < specialChars.length; j++){
							if (excelValueChars[i] == specialChars[j]){
								foundSplChars = true;
								break;
							}
						}
					}
				}
				if (foundSplChars){
					String columnName = colNames[0][colNo];
					writeStatusData(rowNo, "Fail", "'" + excelValue	+ "' in '" + columnName	+ "' contains special character. Special characters are not Allowed. e.g. [ \\ , \" ,  \' ]");
					fileToBeUploaded = false;
				} else {
					writeStatusData(rowNo, "Success", "");
					if (fileToBeUploaded) {
						fileToBeUploaded = true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in uploadStringData" + e);
		}
		return stringData;
	}

	private static Object[][] uploadYearData(ArrayList<String> dataFromExcel, int colNo) {
		Object[][] yearData = null;
		try {
			yearData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";

			for (int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++) {
				if(!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null)	|| dataFromExcel.get(rowNo).equals("null") || dataFromExcel.get(rowNo).equals(""))){
					excelValue = dataFromExcel.get(rowNo).trim();
				}else{
					excelValue = " ";
				}

				yearData[rowNo][0] = excelValue.trim();
				uploadData[rowNo][colNo] = excelValue;

				if(!excelValue.equals(" ")){
					String yearDigit = excelValue;
					if(yearDigit.length() != 4){
						String columnName = colNames[0][colNo];
						writeStatusData(rowNo, "Fail", "'" + excelValue	+ "' in '" + columnName	+ "' is not in proper format.");
						fileToBeUploaded = false;
					}else{
						writeStatusData(rowNo, "Success", "");
						if (fileToBeUploaded) {
							fileToBeUploaded = true;
						}
					}
				}else{
					writeStatusData(rowNo, "Success", "");
					if (fileToBeUploaded) {
						fileToBeUploaded = true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in uploadYearData:" + e);
		}
		return yearData;
	}

	public static void viewUploadedFile(String uploadFileName, String dataPath, HttpServletResponse response){
		try {
			OutputStream oStream = null;
			FileInputStream fsStream = null;
			File file = null;
			try {
				dataPath += uploadFileName;
				response.setHeader("Content-type", "application/msexcel");
				response.setHeader("Content-disposition", "attachment;filename=\"" + uploadFileName + "\"");
				int iChar;
				file = new File(dataPath);
				fsStream = new FileInputStream(file);
				oStream = response.getOutputStream();
				while ((iChar = fsStream.read()) != -1){
					oStream.write(iChar);
				}
			}catch(Exception e){
			}finally{
				if (fsStream != null) {
					fsStream.close();
				}
				if(oStream != null){
					oStream.flush();
					oStream.close();
				}
				if (file != null) {
					file.setReadOnly();
				}
			}
		} catch (Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}

	private static void writeStatusData(int rowNo, String status, String comment) {
		try {
			String oldStatus = "Success", oldComments = "";
			//System.out.println("rowNo--" + rowNo);
			//System.out.println("status--" + status);
			//System.out.println("comment--" + comment);
			if (!(uploadData[rowNo][lastColNo] == null || uploadData[rowNo][lastColNo].equals(null) || uploadData[rowNo][lastColNo].equals("") || uploadData[rowNo][lastColNo] == "")){
				oldStatus = String.valueOf(uploadData[rowNo][lastColNo]);
			}
			if (!(uploadData[rowNo][lastColNo + 1] == null || uploadData[rowNo][lastColNo + 1].equals(null) || uploadData[rowNo][lastColNo + 1].equals("") || uploadData[rowNo][lastColNo + 1] == "")){
				oldComments = String.valueOf(uploadData[rowNo][lastColNo + 1]);
			}
			if (!oldStatus.equals("Fail")) {
				uploadData[rowNo][lastColNo] = status;
			}
			if (oldComments.equals("")) {
				uploadData[rowNo][lastColNo + 1] = comment;
			} else {
				uploadData[rowNo][lastColNo + 1] = oldComments + " " + comment;
			}
		} catch (Exception e) {
			logger.error("Exception in writeStatusData:" + e);
			fileToBeUploaded = false;
		}
	}
	
	private static void resetToNull() {
		workbook = null;
		sheet = null;
		uploadData = null;
		lastRowNo = 0;
		lastColNo = 0;
		totalRecords = 0;
		colNames = null;
		PATH_OF_FILE = "";
	}
	
	/*public static void main(String[] args) throws IOException {
	try {
		getFile("C:\\Sample.xls");

		uploadExcelData(1, null, NUMBER_INTEGER_TYPE,
				MigrateExcelData.MANDATORY);
		uploadExcelData(2, null, DATE_TYPE, MigrateExcelData.MANDATORY);
		uploadExcelData(3, null, STRING_TYPE,
				MigrateExcelData.NON_MANDATORY);

		isFileToBeUploaded();
	} catch (Exception e) {
		logger.error("Exception in main" + e);
	}
}*/
}