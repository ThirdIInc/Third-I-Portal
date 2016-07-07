/*
 * Code Written By Sreedhar K Dt:21-08-2004
 */

package org.paradyne.lib.report;

import org.paradyne.lib.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.usermodel.HSSFFont;

// A class which generates the report in XLS format

public class XLSGenerate {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(XLSGenerate.class);
	int rowNum = 0;

	HSSFWorkbook workbook = null;
	HSSFSheet sheet = null;
	HSSFRow rows = null;
	HSSFCell cell = null;
	HSSFCellStyle cellStyle = null;
	HSSFFont font01Bold = null;

	FileOutputStream fileOutput = null;

	ModelBase model = null;

	public XLSGenerate(String fileName) {
		try {
			workbook = new HSSFWorkbook();
			
			if(fileName.equals("ASSET_REPORT")){
				sheet = workbook.createSheet("Instructions");
				assetInstructionMsg();
				sheet = workbook.createSheet("Data");
			}else{
				sheet = workbook.createSheet("Sheet 1");
				cellStyle = workbook.createCellStyle();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public XLSGenerate(String fileName, String[] message) {
		try {
			workbook = new HSSFWorkbook();
			sheet = workbook.createSheet("Instructions");
			writeInstructions(message);
			sheet = workbook.createSheet("Data");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** This method includes the instructions on the first sheet
	 * 
	 * @param msg - 
	 */
	public void writeInstructions(String[] msg){
		try {
			cellStyle = workbook.createCellStyle();
			logger.info("Writing Instructions ........");
			if (msg != null && msg.length > 0) {
				for (int i = 1; i < msg.length; i++) {
					rows = sheet.createRow(i);
					rows.createCell((short) 0).setCellValue(msg[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void assetInstructionMsg(){
		try {
			cellStyle = workbook.createCellStyle();
			String[] msg = new String[20];
			msg[0] = "";
			msg[1] = " PeoplePower Data Migration Tool";
			msg[2] = "";
			msg[3] = " Instructions";
			msg[4] = "";
			msg[5] = " 1] Do not change the sheet names and sequece of sheets.";							
			msg[6] = " 2] Do not change the sequence of columns.";							
			msg[7] = " 3] The First row represents the Header information. Please do not change the header names manually.";							
			msg[8] = " 4] The cells having * shows that it is a compulsory information and please do not keep the cell blank.";							
			msg[9] = " 5] Make sure that the required master data records are already uploaded into the system.";							
			msg[10] = " 6] The dates should be in mm/dd/yyyy format only.";							
			msg[11] = "";
			msg[12] = " Steps to upload this file";
			msg[13] = "";
			msg[14] = " 1] Fill up all the requirement information into this tool";
			msg[15] = " 2] Upload this file into peoplepower system at Peoplepower --> Configuration --> Data migration";
			msg[16] = " 3] The system will verify the integrity of information.";
			msg[17] = " 4] If there are integrity problems, the system will provide the error log at the last column of the data sheet.";
			msg[18] = " 5] Correct the information accordingly and upload again.";
			msg[19] = " 6] Once the integrity check is sucessful, the data will be uploaded into the system.";
			
			for (int i = 1; i < msg.length; i++) {
				rows = sheet.createRow(i);
				if(i==1 || i==3 || i== 12){
					rows.createCell((short) 1).setCellValue(msg[i]);
				}else{
					rows.createCell((short) 0).setCellValue(msg[i]);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public XLSGenerate(String fileName, HSSFWorkbook workbook, HSSFSheet sheet) {
		try {
			this.workbook = workbook;
			this.sheet = sheet;
			cellStyle = workbook.createCellStyle();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * to add text in report in excel param message
	 * 
	 * @param style
	 * @param borderStyle
	 * @param align
	 * @param margin
	 */
	public void addFormatedText(String message[], int style[], int borderStyle, int align, int margin) {

		try {
			rowNum = rowNum;

			for(int i = 0; i < message.length; i++) {

				cellStyle = workbook.createCellStyle();
				font01Bold = workbook.createFont();
				font01Bold.setFontHeightInPoints((short)8);
				// font01Bold.setFontName("Times New Roman");

				rows = sheet.createRow(rowNum);
				cell = rows.createCell((short)0);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(message[i]);

				if(style[i] == 1) {
					font01Bold.setBoldweight((short)400);

				} else if(style[i] == 2) {
					font01Bold.setBoldweight((short)700);

				} else if(style[i] == 3) {
					font01Bold.setItalic(true);

				} else if(style[i] == 4) {
					font01Bold.setBoldweight((short)700);
					font01Bold.setItalic(true);

				} else if(style[i] == 5) {
					font01Bold.setUnderline((byte)1);

				} else if(style[i] == 6) {
					font01Bold.setBoldweight((short)700);
					font01Bold.setUnderline((byte)1);
				}

				// logger.info("Boldness: "+(short)style[i]);
				cellStyle.setFont(font01Bold);

				if(i == 0) {
					cellStyle.setBorderLeft((short)borderStyle);
					cellStyle.setBorderTop((short)borderStyle);
					cellStyle.setBorderRight((short)borderStyle);
					cellStyle.setBorderBottom((short)borderStyle);
				} else if(i == (message.length - 1)) {
					cellStyle.setBorderLeft((short)borderStyle);
					cellStyle.setBorderRight((short)borderStyle);
					cellStyle.setBorderBottom((short)borderStyle);
				} else {
					cellStyle.setBorderRight((short)borderStyle);
				}

				cellStyle.setAlignment((short)align);

				rowNum = rowNum + 1;

			}

		} catch(Exception e) {
			logger.info("Exception In Add Text " + e);
		}

	}

	/**
	 * to add text in report in excel sheet cells
	 * 
	 * @param msg
	 */
	public void addText(String msg) {
		try {
			rowNum = rowNum + 1;
			rows = sheet.createRow(rowNum);
			rows.createCell((short)0).setCellValue(msg);

		} catch(Exception e) {
			logger.info("Exception In Add Text " + e);
		}
	}

	/**
	 * to add text in excel report
	 * 
	 * @param msg
	 * @param borderStyle
	 * @param align
	 * @param margin
	 */
	public void addText(String msg, int borderStyle, int align, int margin) {
		try {
			rowNum = rowNum + 0;

			cellStyle = workbook.createCellStyle();
			font01Bold = workbook.createFont();
			font01Bold.setFontHeightInPoints((short)8);
			// font01Bold.setFontName("Times New Roman");

			rows = sheet.createRow(rowNum);
			cell = rows.createCell((short)0);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(msg);

			cellStyle.setFont(font01Bold);

			if(borderStyle == 0) {
				cellStyle.setBorderLeft((short)borderStyle);
				cellStyle.setBorderTop((short)borderStyle);
				cellStyle.setBorderRight((short)borderStyle);
				cellStyle.setBorderBottom((short)borderStyle);
			}

			cellStyle.setAlignment((short)align);

			rowNum = rowNum + 1;

		} catch(Exception e) {
			logger.info("Exception In Add Text " + e);
		}
	}

	/**
	 * @param bout
	 */
	public void closeWrite(ByteArrayOutputStream bout) {
		try {
			workbook.write(bout);

		} catch(Exception e) {

		}

	}

	/**
	 * to delete the column in excel
	 * 
	 * @param column
	 */
	public void deleteColumn(int column) {

		try {

			int rowNum = getSheet().getLastRowNum();

			for(int i = 0; i < rowNum; i++) {

				HSSFRow row = getSheet().getRow(i);

				if(row != null && cell != null) {

					cell = row.getCell((short)column);

					row.removeCell(cell);

				}

			}

			// HSSFRow row = new HSSFRow();
			//		
			// HSSFCell cell = new HSSFCell(workbook,sheet,row,col);
			//			
			// row.removeCell(cell);
			//			
			// logger.info("Removed Cell: "+row+"\t"+col);
		} catch(Exception e) {

			logger.info("Exception Caught while deleting cell " + e);

		}

	}

	/**
	 * to generate header
	 * 
	 * @param coname
	 * @param today
	 * @param repname
	 * @param img
	 * @param address
	 */
	public void genHeader(String coname, String today, String repname, String img, String address) {

		try {

			rowNum = rowNum + 1;
			rows = sheet.createRow(rowNum);
			rows.createCell((short)0).setCellValue(coname);
		} catch(Exception e) {
			logger.info("Exception In Add Text " + e);
		}
	}

	public HSSFCell getCell() {
		return cell;
	}

	public HSSFRow getRow() {

		return rows;
	}

	public HSSFSheet getSheet() {

		return sheet;

	}

	/**
	 * to add table in excel sheet
	 * 
	 * @param data
	 * @param widths
	 * @param alignment
	 */
	public void tableBody(String data[][], int widths[], int alignment[]) {

		rowNum = rowNum + 0;
		rows = sheet.createRow(rowNum);
		cellStyle = workbook.createCellStyle();

		font01Bold = workbook.createFont();
		// logger.info("XLS GEN4");
		font01Bold.setFontHeightInPoints((short)10);
		// logger.info("XLS GEN5");
		font01Bold.setFontName("Arial");
		font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		for(int cc = 0; cc < widths.length; cc++) {
			sheet.setColumnWidth((short)cc, (short)(250 * widths[cc]));
		}
		cellStyle = workbook.createCellStyle();
		for(int r = 0; r < data.length; r++) {
			// logger.info("r equals----> "+r);
			rows = sheet.createRow(rowNum);

			// sheet.setColumnWidth((short)r,(short)(250*widths[r]));

			for(int c = 0; c < widths.length; c++) {

				cell = rows.createCell((short)c);
				// cell.setCellStyle(cellStyle);
				// cellStyle.setFont(font01Bold);

				try {
					cell.setCellValue(Double.parseDouble(String.valueOf(data[r][c])));
					// cellStyle.setAlignment((short)alignment[c]);
					cell.setCellStyle(cellStyle);
				} catch(Exception e) {
					cell.setCellValue(String.valueOf(data[r][c]));
					// cellStyle.setAlignment((short)alignment[c]);
					cell.setCellStyle(cellStyle);
				}
				cell.setCellStyle(cellStyle);

				// if(r ==data.length-1)
				// cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

				// if(c == widths.length-1)
				// cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);

				// if(c==0)
				// cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

				// if(r ==0)
				// cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);

			}
			rowNum = rowNum + 1;
		}

	}

	/**
	 * to add table in excel sheet
	 * 
	 * @param header
	 * @param data
	 * @param width
	 * @param align
	 */
	public void tableBody(String[] header, Object[][] data, int[] width, int[] align) {

		// logger.info("XLS GEN1");
		rowNum = rowNum + 0;
		// logger.info("XLS GEN2");

		rows = sheet.createRow(rowNum);
		cellStyle = workbook.createCellStyle();

		// logger.info("XLS GEN3");

		font01Bold = workbook.createFont();
		// logger.info("XLS GEN4");
		font01Bold.setFontHeightInPoints((short)10);
		// logger.info("XLS GEN5");
		font01Bold.setFontName("Arial");
		font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// logger.info("XLS GEN5");

		for(int cc = 0; cc < width.length; cc++) {
			sheet.setColumnWidth((short)cc, (short)(250 * width[cc]));
		}

		for(int i = 0; i < header.length; i++) {
			cell = rows.createCell((short)i);
			cell.setCellValue(header[i]);
			try {
				cellStyle = workbook.createCellStyle();
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(font01Bold);
				cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

				cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);

				// logger.info("XLS GEN "+i);

			} catch(Exception e) {
				logger.info("EXC BACK: " + e);

			}

		}

		// logger.info("OVER");
		// logger.info("ROWE NUM "+rowNum);

		rowNum = rowNum + 1;

		// logger.info("DATA LENGTH "+data.length);

		cellStyle = workbook.createCellStyle();
		for(int r = 0; r < data.length; r++) {
			rows = sheet.createRow(rowNum);
			// sheet.setColumnWidth((short)r,(short)width[r]);
			for(int c = 0; c < header.length; c++) {

				cell = rows.createCell((short)c);
				try {
					cell.setCellValue(Double.parseDouble("" + data[r][c]));
					cellStyle.setAlignment((short)align[c]);

				} catch(Exception e) {
					cell.setCellValue(String.valueOf(data[r][c]));
					cellStyle.setAlignment((short)align[c]);
					// e.printStackTrace();

				}
				// cell.setCellStyle(cellStyle);

				// if(r ==data.length-1)
				// cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

				// if(c == header.length-1)
				// cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);

				// if(c==0)
				// cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

			}
			/*
			 * cellStyle = workbook.createCellStyle(); cell.setCellStyle(cellStyle);
			 */
			rowNum = rowNum + 1;
		}

		// logger.info("ROW COL");

	}
	/**
	 * to generate table with parameters & bgcolor like header
	 * 
	 * @param header
	 * @param data = 
	 * @param width
	 * @param align
	 */
	public void tableBodyWithColor(Object[][] header, Object[][] data, int[] width, int[] align, Object[][] color) {
		
		rowNum = rowNum + 0;
		
		/*Define font details*/
		
		font01Bold = workbook.createFont();
		font01Bold.setFontHeightInPoints((short)10);
		font01Bold.setFontName("Arial");
		font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		/*Define cell style details*/
		
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment((short)2);
		cellStyle.setFont(font01Bold);
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		
		for(int cc = 0; cc < width.length; cc++) {
			sheet.setColumnWidth((short)cc, (short)(250 * width[cc]));
		}
		
		/* Creates a row to apply the style & set the data */
		
		if(header!=null && header.length >0){
			for(int i = 0; i < header.length; i++) {
				rowNum+=i;
				rows = sheet.createRow(rowNum); 
				for (int j = 0; j < header[0].length; j++) {
					try {
						/* Apply the style defined for cellStyle */
						cell = rows.createCell((short)j);
						cell.setCellValue(String.valueOf(header[i][j]));
						cell.setCellStyle(cellStyle);
					} catch(Exception e) {
						logger.info("EXC BACK: " + e);
					}
				}
			}
		}
		
		
		
		/* This block applies the same header styling to the data object*/
		
		if(data!=null && data.length >0){
			rowNum = rowNum + 1;
			for(int r = 0; r < data.length; r++) {
				//rowNum+=r;
				rows = sheet.createRow(rowNum++);
				for(int c = 0; c < data[0].length; c++) {
					try {
						cellStyle = workbook.createCellStyle();
						cellStyle.setAlignment((short)align[c]);
						//cellStyle.setFont(font01Bold);
						if(!(String.valueOf(color[r][c]).equals("")||String.valueOf(color[r][c]).equals("null")||String.valueOf(color[r][c]).equals(null))){
							cellStyle.setFillForegroundColor((short)Integer.parseInt(String.valueOf(color[r][c])));
						}else{
							cellStyle.setFillForegroundColor((short)9);
						}
						cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
						cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
						cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
						cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
						cell = rows.createCell((short)c);
						cell.setCellValue(String.valueOf(data[r][c]));
						cell.setCellStyle(cellStyle);
					} catch(Exception e) {
						logger.info("######### EXCEPTION ##########");
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * to add table in excel sheet
	 * 
	 * @param header
	 * @param data
	 * @param width
	 * @param align
	 * @param noGap
	 */
	public void tableBody(String[] header, Object[][] data, int[] width, int[] align, String noGap) {
		rows = sheet.createRow(rowNum);
		cellStyle = workbook.createCellStyle();

		font01Bold = workbook.createFont();
		font01Bold.setFontHeightInPoints((short)10);
		font01Bold.setFontName("Arial");
		font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		for(int cc = 0; cc < width.length; cc++) {
			sheet.setColumnWidth((short)cc, (short)(250 * width[cc]));
		}

		for(int i = 0; i < header.length; i++) {
			cell = rows.createCell((short)i);
			cell.setCellValue(header[i]);
			try {
				cellStyle = workbook.createCellStyle();
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(font01Bold);
				cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

				cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
			} catch(Exception e) {
				logger.info("EXC BACK: " + e);
			}
		}

		rowNum = rowNum + 1;

		cellStyle = workbook.createCellStyle();

		for(int r = 0; r < data.length; r++) {
			rows = sheet.createRow(rowNum);

			for(int c = 0; c < header.length; c++) {
				cell = rows.createCell((short)c);

				try {
					cell.setCellValue(Double.parseDouble(String.valueOf(data[r][c])));
				} catch(Exception e) {
					cell.setCellValue(String.valueOf(data[r][c]));
					cellStyle.setAlignment((short)align[c]);
				}
				cell.setCellStyle(cellStyle);

				if(r == data.length - 1) cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

				if(c == header.length - 1) cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);

				if(c == 0) cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

			}
			rowNum = rowNum + 1;
		}
	}

	/**
	 * to add table in excel sheet
	 * 
	 * @param header
	 * @param data
	 * @param width
	 * @param align
	 */
	public void tableBodyAsText(String[] header, Object[][] data, int[] width, int[] align, int[] colsAsDouble) {

		// logger.info("XLS GEN1");
		rowNum = rowNum + 0;
		// logger.info("XLS GEN2");

		rows = sheet.createRow(rowNum);
		cellStyle = workbook.createCellStyle();

		// logger.info("XLS GEN3");

		font01Bold = workbook.createFont();
		// logger.info("XLS GEN4");
		font01Bold.setFontHeightInPoints((short)10);
		// logger.info("XLS GEN5");
		font01Bold.setFontName("Arial");
		font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// logger.info("XLS GEN5");

		for(int cc = 0; cc < width.length; cc++) {
			sheet.setColumnWidth((short)cc, (short)(250 * width[cc]));
		}

		for(int i = 0; i < header.length; i++) {
			cell = rows.createCell((short)i);
			cell.setCellValue(header[i]);
			try {
				cellStyle = workbook.createCellStyle();
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(font01Bold);
				cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

				cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setAlignment(new Short("2"));
				// logger.info("XLS GEN "+i);

			} catch(Exception e) {
				logger.info("EXC BACK: " + e);

			}

		}

		// logger.info("OVER");
		// logger.info("ROWE NUM "+rowNum);

		rowNum = rowNum + 1;

		// logger.info("DATA LENGTH "+data.length);

		cellStyle = workbook.createCellStyle();
		for(int r = 0; r < data.length; r++) {
			rows = sheet.createRow(rowNum);
			// sheet.setColumnWidth((short)r,(short)width[r]);
			for(int c = 0; c < header.length; c++) {
				cell = rows.createCell((short)c);
				boolean findCol = false;
				try {
					for(int i = 0; i < colsAsDouble.length; i++) {
						if(c == colsAsDouble[i]) {
							cell.setCellValue(Double.parseDouble(String.valueOf(data[r][c])));
							findCol = true;
							break;
						}
					}
					if(!findCol) {
						cell.setCellValue(String.valueOf(data[r][c]));
					}
					cellStyle.setAlignment((short)align[c]);
				} catch(Exception e) {
					cell.setCellValue(String.valueOf(data[r][c]));
					cellStyle.setAlignment((short)align[c]);
				}
				// cell.setCellStyle(cellStyle);

				// if(r ==data.length-1)
				// cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

				// if(c == header.length-1)
				// cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);

				// if(c==0)
				// cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

			}
			/*
			 * cellStyle = workbook.createCellStyle(); cell.setCellStyle(cellStyle);
			 */
			rowNum = rowNum + 1;
		}

		// logger.info("ROW COL");

	}

	public void tableBodyAsText(String[][] header, Object[][] data, int[] width, int[] align, int[] colsAsDouble) {
		rowNum = rowNum + 0;
		rows = sheet.createRow(rowNum);
		cellStyle = workbook.createCellStyle();
		font01Bold = workbook.createFont();
		font01Bold.setFontHeightInPoints((short)10);
		font01Bold.setFontName("Arial");
		font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		for(int cc = 0; cc < width.length; cc++) {
			sheet.setColumnWidth((short)cc, (short)(250 * width[cc]));
		}

		/**
		 * Write headers
		 */
		for(int i = 0; i < header.length; i++) {
			for(int j = 0; j < header[0].length; j++) {
				cell = rows.createCell((short)j);
				cell.setCellValue(header[i][j]);

				try {
					cellStyle = workbook.createCellStyle();
					cell.setCellStyle(cellStyle);

					cellStyle.setFont(font01Bold);
					cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					cellStyle.setAlignment(new Short("2"));

					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
				} catch(Exception e) {
					logger.info("EXC BACK: " + e);
				}
			}

			rowNum++;
			rows = sheet.createRow(rowNum);
		}

		cellStyle = workbook.createCellStyle();

		/**
		 * Write data
		 */
		for(int r = 0; r < data.length; r++) {
			rows = sheet.createRow(rowNum);

			for(int c = 0; c < data[0].length; c++) {
				cell = rows.createCell((short)c);
				boolean findCol = false;

				try {
					for(int i = 0; i < colsAsDouble.length; i++) {
						if(c == colsAsDouble[i]) {
							cell.setCellValue(Double.parseDouble(String.valueOf(data[r][c])));
							findCol = true;
							break;
						}
					}

					if(!findCol) {
						cell.setCellValue(String.valueOf(data[r][c]));
					}

					cellStyle.setAlignment((short)align[c]);
				} catch(Exception e) {
					cell.setCellValue(String.valueOf(data[r][c]));
					cellStyle.setAlignment((short)align[c]);
				}
			}
			rowNum++;
		}
	}

	/**
	 * to add table in excel sheet with no table border
	 * 
	 * @param data
	 * @param widths
	 * @param alignment
	 */
	public void tableBodyNoBorder(String data[][], int widths[], int alignment[]) {
		rowNum = rowNum + 0;

		rows = sheet.createRow(rowNum);
		cellStyle = workbook.createCellStyle();

		for(int r = 0; r < data.length; r++) {
			rows = sheet.createRow(rowNum);

			sheet.setColumnWidth((short)r, (short)(250 * widths[r]));

			for(int c = 0; c < widths.length; c++) {

				cell = rows.createCell((short)c);
				cellStyle = workbook.createCellStyle();
				cell.setCellStyle(cellStyle);
				cellStyle.setAlignment((short)alignment[c]);

				try {
					cell.setCellValue(Double.parseDouble(String.valueOf(data[r][c])));
				} catch(Exception e) {
					cell.setCellValue(String.valueOf(data[r][c]));
					cellStyle.setAlignment((short)alignment[c]);
				}
			}
			rowNum = rowNum + 1;
		}
	}

	/**
	 * to add table in excel sheet with no cell border
	 * 
	 * @param data
	 * @param widths
	 * @param alignment
	 * @param fontStyle
	 */
	public void tableBodyNoCellBorder(String data[][], int widths[], int alignment[], int fontStyle) {

		rowNum = rowNum + 0;
		rows = sheet.createRow(rowNum);
		cellStyle = workbook.createCellStyle();

		font01Bold = workbook.createFont();
		// logger.info("XLS GEN4");
		font01Bold.setFontHeightInPoints((short)10);
		// logger.info("XLS GEN5");
		font01Bold.setFontName("Arial");
		font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		for(int cc = 0; cc < widths.length; cc++) {
			sheet.setColumnWidth((short)cc, (short)(250 * widths[cc]));
		}

		for(int r = 0; r < data.length; r++) {
			// logger.info("r equals----> "+r);
			rows = sheet.createRow(rowNum);

			// sheet.setColumnWidth((short)r,(short)(250*widths[r]));
			cellStyle = workbook.createCellStyle();

			for(int c = 0; c < widths.length; c++) {

				cell = rows.createCell((short)c);
				cell.setCellStyle(cellStyle);

				if(fontStyle == 1) {
					cellStyle.setFont(font01Bold);
				}

				try {
					cell.setCellValue(Double.parseDouble(String.valueOf(data[r][c])));
				} catch(Exception e) {
					cell.setCellValue(String.valueOf(data[r][c]));
					// cellStyle.setAlignment((short)alignment[c]);
				}

				// if(r ==data.length-1)
				// cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

				// if(c == widths.length-1)
				// cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);

				// if(c==0)
				// cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

				// if(r ==0)
				// cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);

			}
			rowNum = rowNum + 1;
		}

	}

	/**
	 * @param header
	 * @param data
	 * @param width
	 * @param align
	 */
	public void tableBodyPayDown(String[] header, Object[][] data, int[] width, int[] align) {

		// logger.info("XLS GEN1");
		rowNum = rowNum + 0;
		// logger.info("XLS GEN2");

		rows = sheet.createRow(rowNum);
		cellStyle = workbook.createCellStyle();

		// logger.info("XLS GEN3");

		font01Bold = workbook.createFont();
		// logger.info("XLS GEN4");
		font01Bold.setFontHeightInPoints((short)10);
		// logger.info("XLS GEN5");
		font01Bold.setFontName("Arial");
		font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// logger.info("XLS GEN5");

		for(int cc = 0; cc < width.length; cc++) {
			sheet.setColumnWidth((short)cc, (short)(250 * width[cc]));
		}

		for(int i = 0; i < header.length; i++) {
			cell = rows.createCell((short)i);
			cell.setCellValue(header[i]);
			try {
				cellStyle = workbook.createCellStyle();
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(font01Bold);
				cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

				cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);

				// logger.info("XLS GEN "+i);

			} catch(Exception e) {
				logger.info("EXC BACK: " + e);

			}

		}

		rowNum = rowNum + 1;

		cellStyle = workbook.createCellStyle();
		for(int r = 0; r < data.length; r++) {
			rows = sheet.createRow(rowNum);
			// sheet.setColumnWidth((short)r,(short)width[r]);
			for(int c = 0; c < header.length; c++) {

				cell = rows.createCell((short)c);

				try {
					if(header[c].equals("Amount")) {
						cell.setCellValue(Double.parseDouble(String.valueOf(data[r][c])));
						cellStyle.setAlignment((short)align[c]);
					} else {
						cell.setCellValue(String.valueOf((data[r][c])));
						cellStyle.setAlignment((short)align[c]);
					}

				} catch(Exception e) {
					cell.setCellValue(String.valueOf(data[r][c]));
					cellStyle.setAlignment((short)align[c]);
					// e.printStackTrace();

				}

			}
			/*
			 * cellStyle = workbook.createCellStyle(); cell.setCellStyle(cellStyle);
			 */
			rowNum = rowNum + 1;
		}

		// logger.info("ROW COL");

	}

	/**
	 * to add table in excel sheet
	 * 
	 * @param header
	 * @param data
	 * @param width
	 * @param align
	 */
	public void tableBodyWithFormula(String[] header, Object[][] data, int[] width, int[] align, int formulaColumn) {

		// logger.info("XLS GEN1");
		rowNum = rowNum + 0;
		// logger.info("XLS GEN2");

		rows = sheet.createRow(rowNum);
		cellStyle = workbook.createCellStyle();

		// logger.info("XLS GEN3");

		font01Bold = workbook.createFont();
		// logger.info("XLS GEN4");
		font01Bold.setFontHeightInPoints((short)10);
		// logger.info("XLS GEN5");
		font01Bold.setFontName("Arial");
		font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// logger.info("XLS GEN5");

		for(int cc = 0; cc < width.length; cc++) {
			sheet.setColumnWidth((short)cc, (short)(250 * width[cc]));
		}

		for(int i = 0; i < header.length; i++) {
			cell = rows.createCell((short)i);
			cell.setCellValue(header[i]);
			try {
				cellStyle = workbook.createCellStyle();
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(font01Bold);
				cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

				cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);

				// logger.info("XLS GEN "+i);

			} catch(Exception e) {
				logger.info("EXC BACK: " + e);

			}

		}

		// logger.info("OVER");
		// logger.info("ROWE NUM "+rowNum);

		rowNum = rowNum + 1;

		// logger.info("DATA LENGTH "+data.length);

		cellStyle = workbook.createCellStyle();
		for(int r = 0; r < data.length; r++) {
			rows = sheet.createRow(rowNum);
			// sheet.setColumnWidth((short)r,(short)width[r]);
			for(int c = 0; c < header.length; c++) {

				cell = rows.createCell((short)c);
				try {
					cell.setCellValue(Double.parseDouble("" + data[r][c]));
					cellStyle.setAlignment((short)align[c]);

					if(c == formulaColumn) {
						cell.setCellFormula(("" + data[r][c]));
						// System.out.println("Formaula : - :" + data[r][c]);
					}

				} catch(Exception e) {

					if(c == formulaColumn) {
						cell.setCellFormula(("" + data[r][c]));
						// System.out.println("Formaula : - :" + data[r][c]);
					} else {
						cell.setCellValue(String.valueOf(data[r][c]));
						cellStyle.setAlignment((short)align[c]);
					}
					// e.printStackTrace();

				}
				// cell.setCellStyle(cellStyle);

				// if(r ==data.length-1)
				// cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

				// if(c == header.length-1)
				// cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);

				// if(c==0)
				// cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

			}
			/*
			 * cellStyle = workbook.createCellStyle(); cell.setCellStyle(cellStyle);
			 */
			rowNum = rowNum + 1;
		}

		// logger.info("ROW COL");

	}

	public void writeFile(OutputStream out) {
		try {
			workbook.write(out);
		} catch(Exception e) {
			logger.error("Exception in :" + e);
		}
	}

	/**
	 * to add table in excel sheet
	 * 
	 * @param header
	 * @param data
	 * @param width
	 * @param align
	 */
	public void xlsTableBody(String[] header, Object[][] data, int[] width, int[] align) {

		// logger.info("XLS GEN1");
		rowNum = rowNum + 0;
		// logger.info("XLS GEN2");

		rows = sheet.createRow(rowNum);
		cellStyle = workbook.createCellStyle();

		// logger.info("XLS GEN3");

		font01Bold = workbook.createFont();
		// logger.info("XLS GEN4");
		font01Bold.setFontHeightInPoints((short)10);
		// logger.info("XLS GEN5");
		font01Bold.setFontName("Arial");
		font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// logger.info("XLS GEN5");

		for(int cc = 0; cc < width.length; cc++) {
			sheet.setColumnWidth((short)cc, (short)(250 * width[cc]));
		}

		for(int i = 0; i < header.length; i++) {
			cell = rows.createCell((short)i);
			cell.setCellValue(header[i]);
			try {
				cellStyle = workbook.createCellStyle();
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(font01Bold);// HSSFColor.GREY_25_PERCENT
				cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				// cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
				// cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

				// cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
				// cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);

				// logger.info("XLS GEN "+i);

			} catch(Exception e) {
				logger.info("EXC BACK: " + e);

			}

		}

		// logger.info("OVER");
		// logger.info("ROWE NUM "+rowNum);

		rowNum = rowNum + 1;

		// logger.info("DATA LENGTH "+data.length);
		cellStyle = workbook.createCellStyle();

		for(int r = 0; r < data.length; r++) {

			rows = sheet.createRow(rowNum);
			// sheet.setColumnWidth((short)r,(short)width[r]);

			for(int c = 0; c < header.length; c++) {

				cell = rows.createCell((short)c);
				try {
					cell.setCellValue(Double.parseDouble(String.valueOf(data[r][c])));
				} catch(Exception e) {
					cell.setCellValue(String.valueOf(data[r][c]));
					cellStyle.setAlignment((short)align[c]);
				}
				cell.setCellStyle(cellStyle);
				/*
				 * if(r ==data.length-1) cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM); if(c == header.length-1)
				 * cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM); if(c==0) cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
				 */

			}
			rowNum = rowNum + 1;
		}

		// logger.info("ROW COL");

	}
	public void xlsTableForStatement(String[] header, Object[][] data, int[] width, int[] align,String formats[]) {

		// logger.info("XLS GEN1");
		rowNum = rowNum + 0;
		// logger.info("XLS GEN2");

		rows = sheet.createRow(rowNum);
		cellStyle = workbook.createCellStyle();

		// logger.info("XLS GEN3");

		font01Bold = workbook.createFont();
		// logger.info("XLS GEN4");
		font01Bold.setFontHeightInPoints((short)10);
		// logger.info("XLS GEN5");
		font01Bold.setFontName("Arial");
		font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// logger.info("XLS GEN5");

		for(int cc = 0; cc < width.length; cc++) {
			sheet.setColumnWidth((short)cc, (short)(250 * width[cc]));
		}

		for(int i = 0; i < header.length; i++) {
			cell = rows.createCell((short)i);
			cell.setCellValue(header[i]);
			try {
				cellStyle = workbook.createCellStyle();
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(font01Bold);// HSSFColor.GREY_25_PERCENT
				cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				// cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
				// cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

				// cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
				// cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);

				// logger.info("XLS GEN "+i);

			} catch(Exception e) {
				logger.info("EXC BACK: " + e);

			}

		}

		// logger.info("OVER");
		// logger.info("ROWE NUM "+rowNum);

		rowNum = rowNum + 1;

		// logger.info("DATA LENGTH "+data.length);
		cellStyle = workbook.createCellStyle();

		for(int r = 0; r < data.length; r++) {

			rows = sheet.createRow(rowNum);
			// sheet.setColumnWidth((short)r,(short)width[r]);

			for(int c = 0; c < header.length; c++) {
				cellStyle = workbook.createCellStyle();
				cell = rows.createCell((short)c);
				 HSSFDataFormat df = workbook.createDataFormat();
				/* int colCount=52;
				 int rowCount=1;*/
				if(!formats[c].equals("")){
					 cellStyle.setDataFormat(df.getFormat(String.valueOf(formats[c])));
					// HSSFCell cellRef=sheet.createRow(rowCount).createCell((short)colCount);
					 //CellReference cellRef1=new CellReference("cellRef");
					 if(!Utility.checkNull(String.valueOf(data[r][c])).equals("") && String.valueOf(data[r][c]).length()>15){
						 /*cellRef.setCellFormula("\""+((String.valueOf(data[r][c])))+"\"");
						 try {
								
							 cellRef.setCellValue(Double.parseDouble((String.valueOf(data[r][c]))));
							
						} catch(Exception e) {
							cellRef.setCellValue(String.valueOf(data[r][c]));
							cellStyle.setAlignment((short)align[c]);
						}*/
						// cell.setCellFormula("\""+((String.valueOf(data[r][c])))+"\"");
						 cell.setCellValue(Double.parseDouble((String.valueOf(data[r][c]))));
					 } else{
						 try {
								
								cell.setCellValue(Double.parseDouble((String.valueOf(data[r][c]))));
							
						} catch(Exception e) {
							cell.setCellValue(String.valueOf(data[r][c]));
							cellStyle.setAlignment((short)align[c]);
						}
					 }
					// cell.setCellFormula("\""+((String.valueOf(data[r][c])))+"\"");
					 //cell.setCellValue(Double.parseDouble((String.valueOf(data[r][c]))));
				}else{
					try {
						
						cell.setCellValue(Double.parseDouble((String.valueOf(data[r][c]))));
						//cell.setCellValue(String.valueOf(data[r][c]));
					
				} catch(Exception e) {
					cell.setCellValue(String.valueOf(data[r][c]));
					cellStyle.setAlignment((short)align[c]);
				}
				}
				//rowCount++;
				/*else{
					if(formats[c]==2){
						cellStyle.setDataFormat(df.getFormat("0000"));
						//cell.setCellFormula("INT(IF(LEN(F4)<9,0,IF(LEN(F4)>12,LEFT(F4,4),LEFT(F4,LEN(F4) - 8))))");
					}
				}*/
				/*try {
					
						cell.setCellValue(Double.parseDouble((String.valueOf(data[r][c]))));
					
				} catch(Exception e) {
					cell.setCellValue(String.valueOf(data[r][c]));
					cellStyle.setAlignment((short)align[c]);
				}*/
				cell.setCellStyle(cellStyle);
				/*
				 * if(r ==data.length-1) cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM); if(c == header.length-1)
				 * cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM); if(c==0) cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
				 */

			}
			rowNum = rowNum + 1;
		}

		// logger.info("ROW COL");

	}
	public void xlsTableForStatement(String[] header, Object[][] data, int[] width, int[] align) {

		// logger.info("XLS GEN1");
		rowNum = rowNum + 0;
		// logger.info("XLS GEN2");

		rows = sheet.createRow(rowNum);
		cellStyle = workbook.createCellStyle();

		// logger.info("XLS GEN3");

		font01Bold = workbook.createFont();
		// logger.info("XLS GEN4");
		font01Bold.setFontHeightInPoints((short)10);
		// logger.info("XLS GEN5");
		font01Bold.setFontName("Arial");
		font01Bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// logger.info("XLS GEN5");

		for(int cc = 0; cc < width.length; cc++) {
			sheet.setColumnWidth((short)cc, (short)(250 * width[cc]));
		}

		for(int i = 0; i < header.length; i++) {
			cell = rows.createCell((short)i);
			cell.setCellValue(header[i]);
			try {
				cellStyle = workbook.createCellStyle();
				cell.setCellStyle(cellStyle);
				cellStyle.setFont(font01Bold);// HSSFColor.GREY_25_PERCENT
				cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				// cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
				// cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

				// cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
				// cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);

				// logger.info("XLS GEN "+i);

			} catch(Exception e) {
				logger.info("EXC BACK: " + e);

			}

		}

		// logger.info("OVER");
		// logger.info("ROWE NUM "+rowNum);

		rowNum = rowNum + 1;

		// logger.info("DATA LENGTH "+data.length);
		cellStyle = workbook.createCellStyle();

		for(int r = 0; r < data.length; r++) {

			rows = sheet.createRow(rowNum);
			// sheet.setColumnWidth((short)r,(short)width[r]);

			for(int c = 0; c < header.length; c++) {
				cellStyle = workbook.createCellStyle();
				cell = rows.createCell((short)c);
				 HSSFDataFormat df = workbook.createDataFormat();
				/* int colCount=52;
				 int rowCount=1;*/
				try {
						
						//cell.setCellValue(Double.parseDouble((String.valueOf(data[r][c]))));
						cell.setCellValue(String.valueOf(data[r][c]));
					
				} catch(Exception e) {
					//cell.setCellValue(String.valueOf(data[r][c]));
					//cellStyle.setAlignment((short)align[c]);
				}
							
				cell.setCellStyle(cellStyle);

			}
			rowNum = rowNum + 1;
		}

		// logger.info("ROW COL");

	}
}