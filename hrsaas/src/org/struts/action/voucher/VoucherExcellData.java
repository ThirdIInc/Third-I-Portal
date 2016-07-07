/* Bhushan Dasare April 19, 2010 */

package org.struts.action.voucher;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.*;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class VoucherExcellData {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(VoucherExcellData.class);
	
	public static final int STRING_TYPE = 0;
	public static final int DATE_TYPE = 1;
	//public static final int NUMBER_INTEGER_TYPE = 2;
	public static final int NUMBER_DOUBLE_TYPE = 3;
	public static final int MASTER_TYPE = 2;
	//public static final int STRING_TYPE_WITHOUT_SPL_CHARS = 5;
	public static final int YEAR_TYPE = 6;
	public static final int MASTER_DATE_TYPE = 7;
	
	//public static final int NUMBER_DOUBLE_TYPE_NEGATIVE=8;
	
	private static final int DATA_SHHET_NUMBER = 2;
	private static final int START_ROW_NO = 2;
	private static int lastRowNo = 0, lastColNo = 0;
	
	public static final boolean MANDATORY = true;
	public static final boolean NON_MANDATORY = false;
		
	private static boolean fileToBeUploaded = false;
	private static Object[][] uploadData = null;
	
	private static String[][] colNames = null;
	private static String PATH_OF_FILE = "";
	private static final String MANDATORY_CHAR = "*";
		
	private static HSSFWorkbook workbook = null;
	private static HSSFSheet sheet = null;
	
	public static void downloadTemplateWithData(HttpServletRequest request, HttpServletResponse response, String dataPath, String[] listOfColumns, 
		Object[][] data, String applicationName, String client) {
		try {
			String[][] columnNames = getColumnNames(dataPath, listOfColumns);
			
			String templateName = "PeoplePower_VoucherMaster.xls";
			String templateFilePath = dataPath + "\\voucher\\Templates\\" + templateName;
			
			String downloadFileName = "PeoplePower_Employee_" + applicationName + ".xls";
			String downloadFilePath = dataPath + "\\voucher\\" + client + "\\Templates\\" + downloadFileName;
			
			File clientFile = new File(downloadFilePath);
			new File(dataPath + "\\voucher\\" + client + "\\Templates").mkdirs();
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
			
			ReportGenerator rg = new ReportGenerator("Xls", "Sample", workbook, sheet);
			
			int[] cellWidth = new int[columnNames[0].length];
			for(int i = 0; i < cellWidth.length; i++) {
				cellWidth[i] = 20;
			}
			
			int[] cellAlign = new int[columnNames[0].length];
			for(int i = 0; i < cellAlign.length; i++) {
				cellAlign[i] = 0;
			}
			int[] colsAsDouble = {};
			
			rg.tableBodyAsText(columnNames, data, cellWidth, cellAlign, colsAsDouble);
			FileOutputStream fio = new FileOutputStream(new File(downloadFilePath));
			workbook.write(fio);
			
			openTemplate(request, response, downloadFileName, downloadFilePath);
		} catch(Exception e) {
			logger.error("Exception in downloadTemplate:" + e);
		}
	}
	
	public static Object[][] getColumnInfo() {
		Object [][] columnInfo = null;
		try {
			HSSFRow rowForColNames = sheet.getRow(0);
			HSSFRow rowForColInfo = sheet.getRow(1);
			
			TreeMap<Integer, String> columnNameMap = new TreeMap<Integer, String>();
			TreeMap<Integer, String> columnInfoMap = new TreeMap<Integer, String>();
			
			/**
			 * Get total count of rows
			 */
			for(int i = 0; i < lastColNo; i++) {
				HSSFCell cellForColName = rowForColNames.getCell((short) i);
				HSSFCell cellForColInfo = rowForColInfo.getCell((short) i);
				
				if(cellForColName != null) {
					String cellValueForColName = cellForColName.getStringCellValue().trim();
					String cellValueForColInfo = cellForColInfo.getStringCellValue().trim();
					
					if(!(cellValueForColName.equals("") || cellValueForColName.equals("Integrity Status") || cellValueForColName.equals("Comments"))) {
						columnNameMap.put((i+1), cellValueForColName);
						columnInfoMap.put((i+1), cellValueForColInfo);
					}
				}
			}
			
			if(columnNameMap != null && columnNameMap.size() > 0 ){
				columnInfo = new Object[columnNameMap.size()][3];
				int l = 0;
				
				for (Iterator<Integer> k = columnNameMap.keySet().iterator(); k.hasNext();) {
					columnInfo[l][0] = k.next();
					columnInfo[l][1] = columnNameMap.get(Integer.parseInt(String.valueOf(columnInfo[l][0])));
					columnInfo[l][2] = columnInfoMap.get(Integer.parseInt(String.valueOf(columnInfo[l][0])));
					l++;
				}
			}
		} catch(Exception e) {
			logger.error("Exception in getColumnInfo:" + e);
		}
		return columnInfo;
	}
	
	private static String[][] getColumnNames(String dataPath, String[] listOfColumns) {
		String[][] columnNames = null;
		try {
			Properties properties = readColumnsInformation(dataPath);
			
			if(listOfColumns != null && listOfColumns.length > 0) {
				TreeMap<Integer, String> colNames = new TreeMap<Integer, String>();
				TreeMap<Integer, String> colInfo = new TreeMap<Integer, String>();
				
				for(int i = 0; i < listOfColumns.length; i++) {
					String column = listOfColumns[i];
					String columnName = properties.getProperty(column + ".name");
					String columnInfo = properties.getProperty(column + ".info");
					
					colNames.put(i, columnName);
					colInfo.put(i, columnInfo);
				}
				
				Collections.sort(new ArrayList<String>(colNames.values()));
				Collections.sort(new ArrayList<String>(colInfo.values()));
				
				if(colNames != null && colNames.size() > 0) {
					columnNames = new String[2][colNames.size()];
					int cnt = 0;
					
					for(Iterator<Integer> keyIt = colNames.keySet().iterator(); keyIt.hasNext();) {
						int key = keyIt.next();
						
						columnNames[0][cnt] = colNames.get(key);
						columnNames[1][cnt] = colInfo.get(key);
						
						cnt++;
					}
				}
			}			
		} catch(Exception e) {
			logger.error("Exception in getColumnNames:" + e);
		}
		return columnNames;
	}
	
	private static void getColumnNamesFromXLs() {
		try {
			HSSFRow rowForColNames = sheet.getRow(0);
			int totalCols = rowForColNames.getLastCellNum();
			ArrayList<String> columnNames = new ArrayList<String>();
			
			for(int i = 0; i < totalCols; i++) {
				HSSFCell cellForColName = rowForColNames.getCell((short) i);
				
				if(cellForColName != null) {
					String cellValueForColName = cellForColName.getStringCellValue();
					
					if(!(cellValueForColName.equals("") || cellValueForColName.equals("Integrity Status") || cellValueForColName.equals("Comments"))) {
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
			
			for(int i = 0; i < columnNames.size(); i++) {
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
		} catch(Exception e) {
			logger.error("Exception in getColumnNamesFromXLs:" + e);
		}
	}
	
	public static void getFile(String filePath) {
		try {
			PATH_OF_FILE = filePath;
			
			InputStream uploadXls = new FileInputStream(filePath);
			workbook = new HSSFWorkbook(uploadXls);
			sheet = workbook.getSheetAt(DATA_SHHET_NUMBER - 1);
			
			/**
			 * Get columns names
			 */
			getColumnNamesFromXLs();
			
			/**
			 * Get total count of rows
			 */
			for(int rowNo = START_ROW_NO; rowNo <= sheet.getLastRowNum(); rowNo++) {
				HSSFRow curRow = sheet.getRow(rowNo);
				boolean isDataExists = false;
				
				if(curRow != null && lastColNo > 0) {
					for(int curCol = 0; curCol < lastColNo; curCol++) {
						HSSFCell cell = curRow.getCell((short) curCol);

						if(cell != null) {
							String cellValue = "";
							
							try {
								if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
									cellValue = String.valueOf(Math.round(Double.parseDouble(String.valueOf(cell.getNumericCellValue()).trim())));
								} else {
									cellValue = cell.getStringCellValue();
								}
							} catch(Exception e) {
								cellValue = cell.getStringCellValue();
							}
							
							if(!cellValue.trim().equals("")) {
								isDataExists = true;
								break;
							}
						}
					}
				}
				
				if(isDataExists) {
					lastRowNo++;
				}
			}
			
			/**
			 * Create an object for upload data
			 */
			int totalRows = lastRowNo;
			if(totalRows == 0) { totalRows = 1; }
			uploadData = new Object[totalRows][lastColNo + 2];
			
			fileToBeUploaded = true;
		} catch(Exception e) {
			logger.error("Exception in getFile :" + e);
			fileToBeUploaded = false;
		}
	}
	
	public static HashMap<Integer, Boolean> isColumnsMandatory() {
		HashMap<Integer, Boolean> columnInformation = new HashMap<Integer, Boolean>(0);
		try {
			Object[][] columnInfo = getColumnInfo();
			
			if(columnInfo != null && columnInfo.length > 0) {
				int colNo = 1;
				
				for(int i = 0; i < columnInfo.length; i++) {
					String colInfo = String.valueOf(columnInfo[i][2]);
					
					if(colInfo.indexOf(MANDATORY_CHAR) == -1) {
						columnInformation.put(colNo, false);
					} else {
						columnInformation.put(colNo, true);
					}
					
					colNo++;
				}
			}
		} catch(Exception e) {
			logger.error("Exception in getColumnsInformation:" + e);
		}
		return columnInformation;
	}
	
	public static boolean isFileToBeUploaded() {
		System.out.println("in isFileToBeUploaded()");
		try {
			ReportGenerator rg = new ReportGenerator("Xls", "Sample", workbook, sheet);
			
			int[] cellWidth = new int[colNames[0].length];
			for(int i = 0; i < cellWidth.length; i++) {
				cellWidth[i] = 20;
			}
			
			int[] cellAlign = new int[colNames[0].length];
			for(int i = 0; i < cellAlign.length; i++) {
				cellAlign[i] = 0;
			}
			int[] colsAsDouble = {};
			
			for (int i = 0; i < uploadData.length; i++) {
				for (int j = 0; j < uploadData[0].length; j++) {
					System.out.print(uploadData[i][j] + "-");
				}
				System.out.println();
			}
			
			rg.tableBodyAsText(colNames, uploadData, cellWidth, cellAlign, colsAsDouble);
			
			FileOutputStream fio = new FileOutputStream(new File(PATH_OF_FILE));
			
			workbook.write(fio);
			
			resetToNull();
		} catch(Exception e) {
			logger.error("Exception in isFileToBeUploaded:" + e);
			fileToBeUploaded = false;
		}
		logger.info("fileToBeUploaded-" + fileToBeUploaded);
		System.out.println("fileToBeUploaded-" + fileToBeUploaded);
		return fileToBeUploaded;
	}
	
	public static void main(String[] args) throws IOException {
		try {
			getFile("C:\\Sample.xls");
			
			//uploadExcelData(1, null, NUMBER_INTEGER_TYPE, VoucherExcellData.MANDATORY);
			uploadExcelData(1, null, STRING_TYPE, VoucherExcellData.MANDATORY);
			uploadExcelData(2, null, DATE_TYPE ,VoucherExcellData.NON_MANDATORY);
			
			isFileToBeUploaded();
		} catch(Exception e) {
			logger.error("Exception in main" + e);
		}
	}
	
	public static void openTemplate(HttpServletRequest request, HttpServletResponse response, String templateName, String filePath) {
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
			} catch(Exception e) {
				logger.error("Exception in openTemplate:" + e);
			} finally {
				if(fsStream != null) {
					fsStream.close();  
				}
				
				if(oStream != null) {
					oStream.flush();
					oStream.close();
				}
			}
		} catch(Exception e) {
			logger.error("Exception in openTemplate:" + e);
		}
	}
	
	private static Properties readColumnsInformation(String dataPath) {
		Properties properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream(dataPath + "\\voucher\\Columns_Information.properties");
			properties.load(fileInputStream);
			fileInputStream.close();
		} catch(Exception e) {
			logger.error("Exception in readColumnsInformation:" + e);
		}
		return properties;
	}
	
	private static void resetToNull() {
		workbook = null;
		sheet = null;
		uploadData = null;
		lastRowNo = 0; 
		lastColNo = 0;
		colNames = null;
		PATH_OF_FILE = "";
	}
	
	private static Object[][] uploadDateData(ArrayList<String> dataFromExcel, int colNo) {
		Object[][] dateData = null;
		try {
			dateData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";
			
			for(int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++) {
				if(!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || 
						dataFromExcel.get(rowNo).equals(""))) {
					excelValue = dataFromExcel.get(rowNo).trim();
				} else {
					excelValue = " ";
				}
				
				dateData[rowNo][0] = excelValue.trim();
				uploadData[rowNo][colNo] = excelValue;
				
				try {
					if(!excelValue.equals(" ")) {
						int yearDigit = excelValue.split("/")[2].length();
						if(yearDigit != 4) {
							throw new Exception();
						}
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
						sdf.setLenient(false);
						sdf.parse(excelValue);
					}
					
					writeStatusData(rowNo, "Success", "");
					
					if(fileToBeUploaded) {
						fileToBeUploaded = true;
					}
				} catch(Exception e) {
					String columnName = colNames[0][colNo];
					writeStatusData(rowNo, "Fail", "'" + excelValue + "' in '" + columnName + "' is not in 'mm/dd/yyyy' format.");
					
					fileToBeUploaded = false;
				}
			}
		} catch(Exception e) {
			logger.error("Exception in uploadDateData:" + e);
			fileToBeUploaded = false;
		}
		return dateData;
	}
	
	public static Object[][] uploadExcelData(int colNo, Object[][] dataFromMaster, int dataType,boolean isMandatory) {
		System.out.println("isMandatory value i am getting is =============================="+isMandatory);
		
		
		Object[][] excelData = null;
		try {
			colNo = colNo - 1;
			
			ArrayList<String> dataFromExcel = new ArrayList<String>(lastRowNo);
			boolean isDataValid= true;
			int counter = 0;

			if(lastRowNo > 0) {
				for(int rowNo = START_ROW_NO; rowNo <= lastRowNo + 1; rowNo++) {
    				if(isDataValid) { isDataValid = true; }
    				
    				HSSFRow row = sheet.getRow(rowNo);
    				
    				if(row != null) {
    					HSSFCell cell = row.getCell((short) (colNo));
    
    					try {
    						String excelValue = "";
    						
    						if(cell != null) {
    							if(dataType == DATE_TYPE) {
    								try {
    									Date d = cell.getDateCellValue();
    									Calendar cal = Calendar.getInstance();
    									cal.setTime(d);
    									excelValue = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
    									//System.out.println("MONTH="+(Calendar.MONTH)+ 1+"DAY_OF_MONTH="+Calendar.DAY_OF_MONTH +"YEAR="+Calendar.YEAR);
    								} catch(Exception e) {
    									if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
    										excelValue = String.valueOf(Math.round(Double.parseDouble(String.valueOf(cell.getNumericCellValue()).trim())));
    									} else {
    										excelValue = cell.getStringCellValue().trim();
    									}
    								}
    								/*} else if(dataType == NUMBER_INTEGER_TYPE) {
    								if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
    									excelValue = String.valueOf(Math.round(Double.parseDouble(String.valueOf(cell.getNumericCellValue()).trim())));
    								} else {
    									excelValue = cell.getStringCellValue().trim();
    								}*/
    							} else if(dataType == NUMBER_DOUBLE_TYPE ) {
    								if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
    									excelValue = String.valueOf(cell.getNumericCellValue());
    								} else {
    									excelValue = cell.getStringCellValue().trim();
    								}
    							} else if(dataType == MASTER_TYPE) {
    								if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
    									excelValue = String.valueOf(Math.round(Double.parseDouble(String.valueOf(cell.getNumericCellValue()).trim())));
    								} else {
    									excelValue = cell.getStringCellValue().trim();
    								}
    							} else if(dataType == MASTER_DATE_TYPE) {
    								try {
    									Date d = cell.getDateCellValue();
    									Calendar cal = Calendar.getInstance();
    									cal.setTime(d);
    									excelValue = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
    								} catch(Exception e) {
    									if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
    										excelValue = String.valueOf(Math.round(Double.parseDouble(String.valueOf(cell.getNumericCellValue()).trim())));
    									} else {
    										excelValue = cell.getStringCellValue().trim();
    									}
    								}
    							} else {
    								if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
    									excelValue = String.valueOf(Math.round(Double.parseDouble(String.valueOf(cell.getNumericCellValue()).trim())));
    								} else {
    									excelValue = cell.getStringCellValue().trim();
    								}
    							}
    							dataFromExcel.add(excelValue);
    						} else {
    							dataFromExcel.add("");
    						}
    					} catch(Exception e) {
    						logger.error("Exception in uploadExcelData" + e);
    						dataFromExcel.add("");
    					}
    				} else {
    					dataFromExcel.add("");
    				}
    				
    				uploadData[counter][colNo] = dataFromExcel.get(counter);
    				System.out.println("dataFromExcel.get(counter)--" + dataFromExcel.get(counter) + "-");
    				if(isMandatory && dataFromExcel.get(counter).equals("")) {
    					String columnName = colNames[0][colNo];
    					writeStatusData(counter, "Fail", columnName + " should not be blank.");
    					
    					fileToBeUploaded = false;
    					isDataValid = false;
    				}
    				counter++;
    			}
			} else {
				if(isMandatory) {
					String columnName = colNames[0][colNo];
					writeStatusData(counter, "Fail", columnName + " should not be blank.");
					
					fileToBeUploaded = false;
					isDataValid = false;
				}
			}

			if(isDataValid && dataFromExcel != null && dataFromExcel.size() > 0) {
				switch(dataType) {
					case STRING_TYPE : excelData = uploadStringData(dataFromExcel, colNo);
						break;
						
					case DATE_TYPE : excelData = uploadDateData(dataFromExcel, colNo);
						break;
						
					//case NUMBER_INTEGER_TYPE : excelData = uploadNumberData(dataFromExcel, colNo);
						//break;
						
					case NUMBER_DOUBLE_TYPE : excelData = uploadNumberData(dataFromExcel, colNo);
						break;
						
					//case NUMBER_DOUBLE_TYPE_NEGATIVE : excelData = uploadNumberNegativeData(dataFromExcel, colNo);
					//break;
						
					case MASTER_TYPE : excelData = uploadMasterData(dataFromExcel, dataFromMaster, colNo);
						break;
						
					//case STRING_TYPE_WITHOUT_SPL_CHARS : excelData = uploadStringDataWithoutSplChars(dataFromExcel, colNo);
						//break;

					case YEAR_TYPE : excelData = uploadYearData(dataFromExcel, colNo);
						break;
					
					case MASTER_DATE_TYPE : excelData = uploadMasterDateData(dataFromExcel, dataFromMaster, colNo);
						break;
						
					default : break;
				}
			}
		} catch(Exception e) {
			logger.error("Exception in uploadExcelData" + e);
			fileToBeUploaded = false;
		}
		return excelData;
	}
	
	private static Object[][] uploadMasterData(ArrayList<String> dataFromExcel, Object[][] dataFromMaster, int colNo) {
		Object[][] masterData = null;
		try {
			masterData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";
			
			for(int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++) {
				if(!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || 
						dataFromExcel.get(rowNo).equals(""))) {
					excelValue = dataFromExcel.get(rowNo).trim();
				} else {
					excelValue = " ";
				}
				uploadData[rowNo][colNo] = excelValue;
				
				boolean isDataExists = false;
				
				if(dataFromMaster != null && dataFromMaster.length > 0) {
					for(int j = 0; j < dataFromMaster.length; j++) {
						String idFromMaster = String.valueOf(dataFromMaster[j][0]);
						String valueFromMaster = String.valueOf(dataFromMaster[j][1]).trim();

						if(excelValue.equalsIgnoreCase(valueFromMaster) || excelValue.equals(" ")) {
							if(excelValue.equals(" ")) {
								masterData[rowNo][0] = "";
							} else {
								masterData[rowNo][0] = 0;
							}
							
							writeStatusData(rowNo, "Success", "");

							isDataExists = true;
							
							if(fileToBeUploaded) {
								fileToBeUploaded = true;
							}
							
							break;
						}
					}
					
					if(!isDataExists) {
						masterData[rowNo][0] = "NA";
						
						String columnName = colNames[0][colNo];
						writeStatusData(rowNo, "Fail", "'" + excelValue + "' in '" + columnName + "' not available in master.");
						
						fileToBeUploaded = false;
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in uploadMasterData" + e);
		}
		return masterData;
	}
	
	private static Object[][] uploadMasterDateData(ArrayList<String> dataFromExcel, Object[][] dataFromMaster, int colNo) {
		Object[][] masterDateData = null;
		try {
			masterDateData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";
			
			for(int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++) {
				if(!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || 
						dataFromExcel.get(rowNo).equals(""))) {
					excelValue = dataFromExcel.get(rowNo).trim();
				} else {
					excelValue = " ";
				}
				uploadData[rowNo][colNo] = excelValue;
				
				boolean isDataExists = false;
				
				if(dataFromMaster != null && dataFromMaster.length > 0) {
					for(int j = 0; j < dataFromMaster.length; j++) {
						String idFromMaster = String.valueOf(dataFromMaster[j][0]);
						String valueFromMaster = String.valueOf(dataFromMaster[j][1]).trim();

						try {
							SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
							sdf.setLenient(false);
							
							Calendar cal = Calendar.getInstance();
							cal.setTime(sdf.parse(excelValue));
							String dateFromExcel = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);

							cal.setTime(sdf.parse(valueFromMaster));
							String dateFromMaster = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);

							if(dateFromExcel.equals(dateFromMaster) || excelValue.equals(" ")) {
								if(excelValue.equals(" ")) {
									masterDateData[rowNo][0] = "";
								} else {
									masterDateData[rowNo][0] = idFromMaster;
								}
								
								writeStatusData(rowNo, "Success", "");

								isDataExists = true;
								
								if(fileToBeUploaded) {
									fileToBeUploaded = true;
								}
								
								break;
							}
						} catch(Exception e) {}
					}
					
					if(!isDataExists) {
						masterDateData[rowNo][0] = "NA";
						
						String columnName = colNames[0][colNo];
						writeStatusData(rowNo, "Fail", "'" + excelValue + "' in '" + columnName + "' not available in master.");
						
						fileToBeUploaded = false;
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in uploadMasterDateData:" + e);
		}
		return masterDateData;
	}
	
	private static Object[][] uploadNumberData(ArrayList<String> dataFromExcel, int colNo) {
		Object[][] numberData = null;
		try {
			numberData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";
			
			for(int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++) {
				if(!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || 
						dataFromExcel.get(rowNo).equals(""))) {
					excelValue = dataFromExcel.get(rowNo).trim();
				} else {
					excelValue = " ";
				}
				
				numberData[rowNo][0] = excelValue.trim();
				uploadData[rowNo][colNo] = excelValue;

				Pattern pattern = Pattern.compile("^[+]?\\d+(\\.\\d+)?$");
				Matcher matcher = pattern.matcher(excelValue);
				
				if(matcher.matches() || excelValue.equals(" ")) {
					writeStatusData(rowNo, "Success", "");
					
					if(fileToBeUploaded) {
						fileToBeUploaded = true;
					}
				} else {
					String columnName = colNames[0][colNo];
					writeStatusData(rowNo, "Fail", "'" + excelValue + "' in '" + columnName + "' is not in proper format.");
					
					fileToBeUploaded = false;
				}
			}
		} catch(Exception e) {
			logger.error("Exception in uploadNumberData" + e);
		}
		return numberData;
	}
	
	
	private static Object[][] uploadNumberNegativeData(ArrayList<String> dataFromExcel, int colNo) {
		Object[][] numberData = null;
		try {
			numberData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";
			
			for(int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++) {
				if(!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || 
						dataFromExcel.get(rowNo).equals(""))) {
					excelValue = dataFromExcel.get(rowNo).trim();
				} else {
					excelValue = " ";
				}
				
				numberData[rowNo][0] = excelValue.trim();
				uploadData[rowNo][colNo] = excelValue;

				Pattern pattern = Pattern.compile("^[+,-]?\\d+(\\.\\d+)?$");
				Matcher matcher = pattern.matcher(excelValue);
				
				if(matcher.matches() || excelValue.equals(" ")) {
					writeStatusData(rowNo, "Success", "");
					
					if(fileToBeUploaded) {
						fileToBeUploaded = true;
					}
				} else {
					String columnName = colNames[0][colNo];
					writeStatusData(rowNo, "Fail", "'" + excelValue + "' in '" + columnName + "' is not in proper format.");
					
					fileToBeUploaded = false;
				}
			}
		} catch(Exception e) {
			logger.error("Exception in uploadNumberData" + e);
		}
		return numberData;
	}
	private static Object[][] uploadStringData(ArrayList<String> dataFromExcel, int colNo) {
		Object[][] stringData = null;
		try {
			stringData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";

			for(int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++) {
				if(!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || 
						dataFromExcel.get(rowNo).equals(""))) {
					excelValue = dataFromExcel.get(rowNo).trim();
				} else {
					excelValue = " ";
				}

				stringData[rowNo][0] = excelValue.trim();
				uploadData[rowNo][colNo] = excelValue;
				
				writeStatusData(rowNo, "Success", "");
				
				if(fileToBeUploaded) {
					fileToBeUploaded = true;
				}
			}
		} catch(Exception e) {
			logger.error("Exception in uploadStringData" + e);
		}
		return stringData;
	}
	
	private static Object[][] uploadStringDataWithoutSplChars(ArrayList<String> dataFromExcel, int colNo) {
		Object[][] stringData = null;
		try {
			stringData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";
			//char[] specialChars = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '~', '`', '|'}; 
			char[] specialChars = {'\\', '\'', '\"'};

			for(int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++) {
				if(!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || 
						dataFromExcel.get(rowNo).equals(""))) {
					excelValue = dataFromExcel.get(rowNo).trim();
				} else {
					excelValue = " ";
				}
				
				stringData[rowNo][0] = excelValue.trim();
				uploadData[rowNo][colNo] = excelValue;
				
				char[] excelValueChars = excelValue.toCharArray();
				
				boolean foundSplChars = false;
				
				if(!excelValue.equals(" ")) {
					for(int i = 0; i < excelValueChars.length; i++) {
						for(int j = 0; j < specialChars.length; j++) {
							if(excelValueChars[i] == specialChars[j]) {
								foundSplChars = true;
								break;
							}
						}
					}
				}
				
				if(foundSplChars) {
					String columnName = colNames[0][colNo];
					writeStatusData(rowNo, "Fail", "'" + excelValue + "' in '" + columnName + 
						"' contains special character. Special characters are not Allowed. e.g. [ \\ , \" ,  \' ]");
					
					fileToBeUploaded = false;;
				} else {
					writeStatusData(rowNo, "Success", "");
					
					if(fileToBeUploaded) {
						fileToBeUploaded = true;
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in uploadStringData" + e);
		}
		return stringData;
	}

	private static Object[][] uploadYearData(ArrayList<String> dataFromExcel, int colNo) {
		Object[][] yearData = null;
		try {
			yearData = new Object[dataFromExcel.size()][1];
			String excelValue = " ";
			
			for(int rowNo = 0; rowNo < dataFromExcel.size(); rowNo++) {
				if(!(dataFromExcel.get(rowNo) == null || dataFromExcel.get(rowNo).equals(null) || dataFromExcel.get(rowNo).equals("null") || 
						dataFromExcel.get(rowNo).equals(""))) {
					excelValue = dataFromExcel.get(rowNo).trim();
				} else {
					excelValue = " ";
				}
				
				yearData[rowNo][0] = excelValue.trim();
				uploadData[rowNo][colNo] = excelValue;

				if(!excelValue.equals(" ")) {
					String yearDigit = excelValue;

					if(yearDigit.length() != 4) {
						String columnName = colNames[0][colNo];
						writeStatusData(rowNo, "Fail", "'" + excelValue + "' in '" + columnName + "' is not in proper format.");

						fileToBeUploaded = false;
					} else {
						writeStatusData(rowNo, "Success", "");

						if(fileToBeUploaded) {
							fileToBeUploaded = true;
						}
					}
				} else {
					writeStatusData(rowNo, "Success", "");

					if(fileToBeUploaded) {
						fileToBeUploaded = true;
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in uploadYearData:" + e);
		}
		return yearData;
	}
	
	public static void viewUploadedFile(String uploadFileName, String dataPath, HttpServletResponse response) {
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
				
				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}
			} catch(Exception e) {
			} finally{
				if(fsStream != null) {
					fsStream.close();  
				}
				
				if(oStream != null) {
					oStream.flush();
					oStream.close();
				}
				
				if(file != null) {
					file.setReadOnly();
				}
			}
		} catch(Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}
	
	private static void writeStatusData(int rowNo, String status, String comment) {
		try {
			String oldStatus = "Success", oldComments = "";
			System.out.println("rowNo--" + rowNo);
			System.out.println("status--" + status);
			System.out.println("comment--" + comment);
			if(!(uploadData[rowNo][lastColNo] == null || uploadData[rowNo][lastColNo].equals(null) || 
					uploadData[rowNo][lastColNo].equals("") || uploadData[rowNo][lastColNo] == "")) {
				oldStatus = String.valueOf(uploadData[rowNo][lastColNo]);
			}
			
			if(!(uploadData[rowNo][lastColNo + 1] == null || uploadData[rowNo][lastColNo + 1].equals(null) || 
					uploadData[rowNo][lastColNo + 1].equals("") || uploadData[rowNo][lastColNo + 1] == "")) {
				oldComments = String.valueOf(uploadData[rowNo][lastColNo + 1]);
			}
			
			if(!oldStatus.equals("Fail")) {
				uploadData[rowNo][lastColNo] = status;
			}
			
			if(oldComments.equals("")) {
				uploadData[rowNo][lastColNo + 1] = comment;
			} else {
				uploadData[rowNo][lastColNo + 1] = oldComments + " " + comment;
			}
		} catch(Exception e) {
			logger.error("Exception in writeStatusData:" + e);
			fileToBeUploaded = false;
		}
	}

	private VoucherExcellData() {}
}