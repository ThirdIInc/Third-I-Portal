package org.paradyne.lib.ireport;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import javax.swing.event.TableModelEvent;

import org.paradyne.lib.Utility;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/*
 * 	Coded  By 	:  Prakash Shetkar 	
 *	Date    	:  20-11-2009					
 *
 */

public class PDFGenerator extends PdfPageEventHelper {
	
	Document doc;
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PDFGenerator.class); 
	public PdfGState gstate;
	public PdfTemplate tpl;
	public PdfTemplate tplHeader;
	public BaseFont helv;
	PdfPTable footer;
	HeaderFooter header;
	boolean showPage;
	int pageNoHPosition;
	int pageNoVPosition;
	
	public Document openDoc(ReportDataSet rds,ByteArrayOutputStream bout){
		
		if(rds.getPageSize().equalsIgnoreCase("A3")){
			
			if(rds.getPageOrientation().equalsIgnoreCase("landscape")){
			
				doc = new Document(PageSize.A3.rotate(), rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}else{

				doc = new Document(PageSize.A3, rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
			
			}
		}else{
				
			if(rds.getPageOrientation().equalsIgnoreCase("landscape")){
				
				doc = new Document(PageSize.A4.rotate(), rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}else{
				
				doc = new Document(PageSize.A4, rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}
		}
		showPage = rds.isShowPageNo();
		pageNoHPosition = rds.getPageNoHPosition();
		pageNoVPosition = rds.getPageNoVPosition();
			try{
				
				PdfWriter writer = PdfWriter.getInstance(doc, bout);
				writer.setPageEvent(this);
				
			}catch(Exception e){
				 logger.error("Error while creating writer for pdfgenerator :" + e );
				//e.printStackTrace();
			}
			
		return doc;
	}
	/**
	 * to open document to write the content
	 * @param writer
	 * @param document
	 */
	public void onOpenDocument(PdfWriter writer, Document document) {
		try {			
			gstate = new PdfGState();
			gstate.setFillOpacity(0.3f);
			gstate.setStrokeOpacity(0.3f);
			// initialization of the template
			tpl = writer.getDirectContent().createTemplate(100, 100);
			tpl.setBoundingBox(new Rectangle(-20, -20, 100, 100));
			// initialization of the font
			helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
		} catch (Exception e) {
			logger.info("error in onOpenDocument"+e);
			//e.printStackTrace();
		}
	}

	/**
	 * to generate the page number at footer
	 * @param writer
	 * @param document
	 */
	public void onCloseDocument(PdfWriter writer, Document document) {
		try {
			if(showPage){
				tpl.beginText();
				tpl.setFontAndSize(helv, 8);
				tpl.setTextMatrix(0, 0);
				tpl.showText("" + (writer.getPageNumber() - 1));
				tpl.endText();
			}
			
		} catch (Exception e) {
			logger.info("error in onCloseDocument"+e);
			//e.printStackTrace();
		}
	}
	/**
	 * to add proper page number with alignment in footer
	 * @param writer
	 * @param document
	 */
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			PdfContentByte cb = writer.getDirectContent();
			cb.saveState();
			PdfPTable	pTable = new PdfPTable(2);
			pTable.setTotalWidth(document.right() - document.left());
			pTable.writeSelectedRows(0, -1, document.left(), document.getPageSize().height() - 50, cb);
			// compose the footer
			String text = "Page " + writer.getPageNumber() + " of ";
			float textSize = helv.getWidthPoint(text, 8);
			float textBase	= 0;	
			if(footer != null)
				footer.writeSelectedRows(0, 500,document.leftMargin(), document.bottom() - 5, cb);
			
			cb.beginText();
			cb.setFontAndSize(helv, 8);
			
			if(showPage){
				
				if(pageNoVPosition == 0)
					textBase = document.top()- 2;
				else
					textBase = document.bottom()- 2;
				
				
				if(pageNoHPosition == 0)
					cb.setTextMatrix(document.leftMargin(),textBase);
				else if(pageNoHPosition == 1)
					cb.setTextMatrix(document.rightMargin(),textBase);
				else
					cb.setTextMatrix((document.right() - document.left()) / 2,textBase);
					
				
				cb.showText(text);
				cb.endText();
							
				if(pageNoHPosition == 0)
					cb.addTemplate(tpl, document.leftMargin() + textSize , textBase);
				else if(pageNoHPosition == 1)
					cb.addTemplate(tpl, document.rightMargin() + textSize , textBase);
				else
					cb.addTemplate(tpl, (document.right() - document.left()) / 2 + textSize , textBase);
					
					
							
				cb.saveState();
				cb.restoreState();
				
			}
			
		} catch (Exception e) {
			logger.info("error in onEndPage"+e);
			//e.printStackTrace();
		}
	}
	public void pageBreak() {
		try {
			doc.newPage();
		} catch (Exception e) {
			 logger.error("error while page break");
			//e.printStackTrace();
		}
	}
	public PdfPTable createTable(TableDataSet tds) {
			PdfPTable bodyTable = null;
			Font bodyFont = null;
			Font headerFont = null;
			Font sumFont = null;
			Float headerCellPadding = 0f;
			Float bodyCellPadding = 0f;
			try {
				Object[][] data = tds.getData();
				if(data != null && data.length > 0){
					
					int[] alignment = tds.getCellAlignment();// alignment array
					int[] width = tds.getCellWidth();// width
					Color clrBody = tds.getBodyBGColor();// background color for cell
					Color clrHeader = tds.getHeaderBGColor();// background color for cell
					bodyFont = tds.getBodyFontDetails();// font 4 body of the table with
					// font,size style and color
					String headers[] = tds.getHeader();// table headers
					Boolean border = tds.getBorder();// border yes no
					Object[] columColorData = tds.getColumnColor();// if some colum required special color
					// column no which is to be colored
					int colNo = Integer.parseInt(String.valueOf(columColorData[0]));
					Color colBackColor = (Color) columColorData[1];// background color
					Font colFontColor = (Font) columColorData[2];// fontcolor of that// column
					headerFont = tds.getHeaderFontDetails();
					sumFont = tds.getSumFontDetails();
					int tempOne = 1;
					int tempTwo = 1;
					if(tds.getHeaderCellpadding() > 0)
						headerCellPadding = tds.getHeaderCellpadding();
					
					if(tds.getBodyCellpadding() > 0)
						bodyCellPadding = tds.getBodyCellpadding();
	
					int[] colsSum = tds.getColumnSum();
					int[] colsAvg = tds.getColumnAvg();
					double[] sumOut = null;
					double[] avgOut = null;
					
					if (!(colsSum == null))
						sumOut = new double[colsSum.length];
					
					if (!(colsAvg == null))
						avgOut = new double[colsAvg.length];
					
					int dataLength = data.length;
	
					int[] rowsSum = tds.getRowSum();
					int[] rowsAvg = tds.getRowAvg();
					
					if(rowsSum != null && rowsAvg != null){
						tempOne = 1;
						tempTwo = 2;
					}
						
					double rowSumOut = 0.0;
					double rowAvgOut = 0.0;
					//logger.info("inside createtable data.length " + data.length);
					//logger.info("inside createtable alignment.length "+ alignment.length);
					//logger.info("inside createtable width.length " + width.length);
					//logger.info("inside createtable  columnbackground color"+ columColorData[1]);
					//logger.info("inside createtable columnforeground color"+ columColorData[2]);
					//logger.info("inside createtable font details" + bodyFont.family());
					//logger.info("inside createtable cell color" + clr);
					
					bodyTable = new PdfPTable(alignment.length);
					// adjustment of table width
					if (!(width == null)) {
						float[] www = new float[width.length];
						for (int i = 0; i < www.length; i++) {
							www[i] = (float) width[i];
						}
						bodyTable.setTotalWidth(www);
						bodyTable.setWidthPercentage(100.0f);
						bodyTable.setWidths(width);
					}
					// cell created.
					PdfPCell bcell = null;
					// table header or column names
					if (!(headers == null)) {
						for (int i = 0; i < headers.length; i++) {
							// font mentioned or default fonnt for the table headings
							if (!(headerFont.family() == -1))
								bcell = new PdfPCell(new Phrase(headers[i], headerFont));
							else
								bcell = new PdfPCell(new Phrase(headers[i], new Font(Font.HELVETICA, 10, Font.BOLD,new java.awt.Color(0, 0, 0))));
	
							// default alignment for column is center
								bcell.setHorizontalAlignment(1);
							if(tds.getHeaderCellpadding() > 0)
								bcell.setPadding(tds.getHeaderCellpadding());
													
							// color for the header cell
							if (clrHeader == null)
								bcell.setBackgroundColor(new Color(255, 255, 255));
							else
								bcell.setBackgroundColor(clrHeader);
							// border required or not
							if (!border)
								bcell.setBorder(Rectangle.NO_BORDER);
	
							bodyTable.addCell(bcell);
						}
					}
	
					// The table containing the data for the report required.
					for (int i = 0; i < dataLength; i++) {
						for (int j = 0; j < alignment.length; j++) {

							// adding the cell to bodytable
							if (!(rowsAvg == null) && j == alignment.length - tempOne) {
								
								if (!(sumFont.family() == -1))
									bcell = new PdfPCell(new Phrase(String.valueOf(Utility.twoDecimals(rowAvgOut / rowsAvg.length)),sumFont));
								else
									bcell = new PdfPCell(new Phrase(String.valueOf(Utility.twoDecimals(rowAvgOut / rowsAvg.length)), new Font(Font.HELVETICA,8, Font.NORMAL, new java.awt.Color(0, 0, 0))));
								
								bcell.setHorizontalAlignment(alignment[j]);
								
	
							}else if (!(rowsSum == null) && j == alignment.length - tempTwo) {
								
								if (!(sumFont.family() == -1))
									bcell = new PdfPCell(new Phrase(String.valueOf(rowSumOut),sumFont));
								else
									bcell = new PdfPCell(new Phrase(String.valueOf(rowSumOut), new Font(Font.HELVETICA,8, Font.NORMAL, new java.awt.Color(0, 0, 0))));
								
								bcell.setHorizontalAlignment(alignment[j]);
								
	
							} else {
								// if this culumn has some special properties apply
								// those else general column
								if ((!(colNo == -1)) && j == colNo) {
									if(data[i][j] instanceof Image){
										bcell = new PdfPCell((Image)data[i][j]);
									} else {										
									bcell = new PdfPCell(new Phrase(checkNull(String.valueOf(data[i][j])), colFontColor));
									}
									bcell.setBackgroundColor(colBackColor);		
									
								} else {
									if (!(bodyFont.family() == -1)) {
										// font mentioned for table data else take
										// default font
										if(data[i][j] instanceof com.lowagie.text.Image){
											bcell = new PdfPCell((Image)data[i][j]);
										} else {
											bcell = new PdfPCell(new Phrase(checkNull(String.valueOf(data[i][j])), bodyFont));
										}
										
									} else {
										// logger.info("font not found");
										try{
										if(data[i][j] instanceof com.lowagie.text.Image){
											bcell = new PdfPCell((Image)data[i][j]);
										} else {
											bcell = new PdfPCell(new Phrase(checkNull(String.valueOf(data[i][j])), new Font(Font.HELVETICA, 8, Font.NORMAL,new java.awt.Color(0, 0, 0))));
										}
										}catch(Exception e){
											logger.info("error in setting image "+e);
										}
									}									
									 bcell.setBackgroundColor(clrBody);
								}
								// rowwise sum for mentioned rows
								if (!(rowsSum == null)) {
									for (int l = 0; l < rowsSum.length; l++) {
									
										if (j == rowsSum[l]) {
											if (!String.valueOf(data[i][j]).equals(""))
												rowSumOut = rowSumOut+ Double.parseDouble(checkZero(String.valueOf(data[i][j])));
										}
									}
								}
								// rowwise average for mentioned rows
								if (!(rowsAvg == null)) {
									for (int l = 0; l < rowsAvg.length; l++) {
									
										if (j == rowsAvg[l]) {
											if (!String.valueOf(data[i][j]).equals(""))
												rowAvgOut = rowAvgOut+ Double.parseDouble(checkZero(String.valueOf(data[i][j])));
										}
									}
								}
								// columwise sum for mentioned columns
								if (!(colsSum == null)) {
									for (int k = 0; k < colsSum.length; k++) {
	
										if (j == colsSum[k]) {
											if (!String.valueOf(data[i][j]).equals(""))
												sumOut[k] = sumOut[k]+ Double.parseDouble(checkZero(String.valueOf(data[i][j])));
										}
									}
	
								}
								// columwise average for mentioned columns
								if (!(colsAvg == null)) {
									for (int k = 0; k < colsAvg.length; k++) {
	
										if (j == colsAvg[k]) {
											if (!String.valueOf(data[i][j]).equals(""))
												avgOut[k] = avgOut[k]+ Double.parseDouble(checkZero(String.valueOf(data[i][j])));
										}
									}
	
								}
								// alignment according to the mentioned array
								bcell.setHorizontalAlignment(alignment[j]);
								if(tds.getBodyCellpadding() > 0)
									bcell.setPadding(tds.getBodyCellpadding());
								// border required yes or no
								if (!border)
									bcell.setBorder(Rectangle.NO_BORDER);
							}
							bodyTable.addCell(bcell);
						}
						rowSumOut = 0.00;
						rowAvgOut = 0.00;
						if (tds.isHeaderTable) {

						} else
							bodyTable.setHeaderRows(1);
					}
					if (!(colsSum == null)) {
						int k = 0;
						for (int i = 0; i < 1; i++) {
							
							for (int j = 0; j < alignment.length; j++) {
								// logger.info("value of k "+sumOut.length);
								if (k < colsSum.length) {
									// logger.info("hi for chk k ="+k);
									if (j == colsSum[k]) {
										// logger.info("logger for j="+j);
										if (!(sumFont.family() == -1))
											bcell = new PdfPCell(new Phrase(String.valueOf(Utility.twoDecimals(sumOut[k])),sumFont));
										else{
											bcell = new PdfPCell(new Phrase(String.valueOf(Utility.twoDecimals(sumOut[k])),
															new Font(Font.HELVETICA, 8,Font.NORMAL,new java.awt.Color(0, 0, 0))));
											}
										k++;
									} else
										bcell = new PdfPCell(new Phrase(String.valueOf(""), new Font(Font.HELVETICA,8, Font.NORMAL, new java.awt.Color(0,0, 0))));
								} else
									bcell = new PdfPCell(new Phrase(String.valueOf(""),new Font(Font.HELVETICA, 8, Font.NORMAL,new java.awt.Color(0, 0, 0))));
	
								bcell.setHorizontalAlignment(alignment[j]);
								if(tds.getBodyCellpadding() > 0)
									bcell.setPadding(tds.getBodyCellpadding());
								bodyTable.addCell(bcell);
							}
						}
					}
					
					if (!(colsAvg == null)) {
						int k = 0;
						for (int i = 0; i < 1; i++) {
							
							for (int j = 0; j < alignment.length; j++) {
								// logger.info("value of k "+sumOut.length);
								if (k < colsAvg.length) {
									// logger.info("hi for chk k ="+k);
									if (j == colsAvg[k]) {
										// logger.info("logger for j="+j);
										if (!(sumFont.family() == -1))
											bcell = new PdfPCell(new Phrase(String.valueOf(Utility.twoDecimals(avgOut[k]/colsAvg.length)),sumFont));
										else{
											bcell = new PdfPCell(new Phrase(String.valueOf(Utility.twoDecimals(avgOut[k]/colsAvg.length)),
															new Font(Font.HELVETICA, 8,Font.NORMAL,new java.awt.Color(0, 0, 0))));
											}
										k++;
									} else
										bcell = new PdfPCell(new Phrase(String.valueOf(""), new Font(Font.HELVETICA,8, Font.NORMAL, new java.awt.Color(0,0, 0))));
								} else
									bcell = new PdfPCell(new Phrase(String.valueOf(""),new Font(Font.HELVETICA, 8, Font.NORMAL,new java.awt.Color(0, 0, 0))));
	
								bcell.setHorizontalAlignment(alignment[j]);
								if(tds.getBodyCellpadding() > 0)
									bcell.setPadding(tds.getBodyCellpadding());
								bodyTable.addCell(bcell);
							}
						}
					}
				}else
					bodyTable = new PdfPTable(1);
				
			} catch (Exception e) {
				logger.error("error in createtable"+e);
				//e.printStackTrace();
			}
			if(tds.getBlankRowsAbove() > 0) {
				
				PdfPTable tempTable = new PdfPTable(1);
				tempTable.setWidthPercentage(100.0f);
				PdfPCell bcell = new PdfPCell(new Phrase("                                "));
				bcell.setBorder(Rectangle.NO_BORDER);
				for(int i=0; i<tds.getBlankRowsAbove(); i++)
					tempTable.addCell(bcell);
				
				bodyTable = joinTable(tempTable, bodyTable, false);
				
			}
			if(tds.getBlankRowsBelow() > 0) {
				
				PdfPTable tempTable = new PdfPTable(1);
				tempTable.setWidthPercentage(100.0f);
				PdfPCell bcell = new PdfPCell(new Phrase("                                "));
				bcell.setBorder(Rectangle.NO_BORDER);
				for(int i=0; i<tds.getBlankRowsBelow(); i++)
					tempTable.addCell(bcell);
				
				bodyTable = joinTable( bodyTable,tempTable, false);
				
			}
			return bodyTable;
		}
		public PdfPTable joinTable(PdfPTable table1,PdfPTable table2,boolean horizontalFlag){
			
			PdfPTable parent = null;
					
			if(horizontalFlag){
				parent = new PdfPTable(2);
				parent.setWidthPercentage(100.0f);
			}else{
				parent = new PdfPTable(1);
				parent.setWidthPercentage(100.0f);
			}
			PdfPCell bcell = new PdfPCell(table1);
			bcell.setBorder(Rectangle.NO_BORDER);
					
			PdfPCell bcell1 = new PdfPCell(table2);
			bcell1.setBorder(Rectangle.NO_BORDER);
			
			parent.addCell(bcell);
			parent.addCell(bcell1);
			
			//parent.addCell(table1);
			//parent.addCell(table2);
			
			return parent;	
			
		}
		public PdfPCell generateCell(TableDataSet tds) {
			PdfPCell cell= new PdfPCell();
			
			try {
				PdfPTable ptable = createTable(tds);
				cell.addElement(ptable);
				cell.setBorder(Rectangle.NO_BORDER);
			} catch (Exception e) {
				logger.error("error in generateCell"+e);
				//e.printStackTrace();
			}
			
			return cell;
		}
		
		public PdfPTable generateMapTable(HashMap<String,Object> map){
			PdfPTable table = null;
			try {
				if(map.get("Direction").equals("H")){
					table = new PdfPTable(2);
					int [] width = new int[2];
					try{
						if(map.get("width") != null){
							width [0] = Integer.parseInt(String.valueOf(map.get("width")));
							width [1] = 100 - Integer.parseInt(String.valueOf(map.get("width")));
						}else{
							width [0] = 50;
							width [1] = 50;
						}
						
						float[] www = new float[width.length];
						for (int i = 0; i < www.length; i++) {
							www[i] = (float) width[i];
						}
						
						table.setTotalWidth(www);
						table.setWidthPercentage(100.0f);
						table.setWidths(width);
					
					}catch(Exception e){
						//e.printStackTrace();
					}
				}
				else{
					table = new PdfPTable(1);
					table.setWidthPercentage(100.0f);
				}
				//logger.info("pdfg process"+map.size());
				
					for(int i=0;i<map.size();i++){

						Object obj = map.get(String.valueOf(i));
						if(obj != null){
							
								if(obj instanceof TableDataSet){
									PdfPCell cell = generateCell((TableDataSet)obj);
									cell.setBorder(Rectangle.NO_BORDER);
									cell.setPadding(0f);
									table.addCell(cell);
									
								}else if(obj instanceof HashMap){
									
									PdfPCell cell = new PdfPCell();
									HashMap<String,Object> NewMap = (HashMap<String,Object>)map.get(String.valueOf(i));
									PdfPTable NewTable = generateMapTable(NewMap);
									cell.addElement(NewTable);
									cell.setBorder(Rectangle.NO_BORDER);
									cell.setPadding(0f);
									table.addCell(cell);					
							   }
						 }
				 }
					
			} catch (Exception e) {
				logger.error("error in generateMapTable"+e);
				//e.printStackTrace();
			}
			return table;
		}
		
		public void process(HashMap<String,Object> map,Document doc){
			try {
				boolean isLast = false;
				PdfPTable table = new PdfPTable(1);
				table.setWidthPercentage(100.0f);
				try {
					if(!doc.isOpen())
						doc.open();
				} catch(Exception de) {
					logger.error("error in opening document" + de);
				}
				for(int i=0;i<map.size();i++){
					isLast = false;
					Object obj = map.get(String.valueOf(i));
					
					if(obj != null){
						
						if(obj instanceof TableDataSet){
							PdfPCell cell = generateCell((TableDataSet)obj);
							cell.setBorder(Rectangle.NO_BORDER);
							cell.setPadding(0f);
							table.addCell(cell);
							
						}else if( obj instanceof HashMap){
							
							PdfPTable NewTable = generateMapTable((HashMap<String,Object>)obj);	
							PdfPCell cell = new PdfPCell();
							cell.addElement(NewTable);
							cell.setBorder(Rectangle.NO_BORDER);
							cell.setPadding(0f);
							table.addCell(cell);
							
						}else if(obj.equals("PAGE_BREAK")){
							doc.add(table);
							isLast = true;
							table = new PdfPTable(1);
							table.setWidthPercentage(100.0f);
							if(!(i == map.size()-1))
								pageBreak();													
						}
						
					}
				}
				if(!isLast)
					doc.add(table);
				
			} catch (DocumentException e) {
				logger.error("error in process"+e);
				//e.printStackTrace();
			}catch(Exception e1){
				logger.error("error in process"+e1);
				//e1.printStackTrace();
			}
		
		}
		public void genHeader(Object obj) {
			PdfPTable headerTable = null;
			try {		
				
				if(obj instanceof TableDataSet)
					headerTable = createTable((TableDataSet)obj);
				else if(obj instanceof HashMap )
					headerTable = generateMapTable((HashMap<String, Object>)obj);
					
				Phrase para = new Phrase();
				para.setLeading(0);
				para.add(headerTable);
				header = new HeaderFooter(para, false);
				header.setBorder(Rectangle.NO_BORDER);
								
			} catch (Exception e) {
				logger.error("error in genHeader"+e);
				//e.printStackTrace();
			}
			doc.setHeader(header);
			/*try {
				if(!doc.isOpen())
					doc.open();
			} catch(Exception de) {
				logger.error("error in opening document" + de);
			}*/
			
		}
		public void genFooter(Object obj) {
						
			try {
				if(obj instanceof TableDataSet)
					footer = createTable((TableDataSet)obj);
				else if(obj instanceof HashMap )
					footer = generateMapTable((HashMap<String, Object>)obj);
				
				 footer.setTotalWidth(750);			
				 
			} catch (Exception e) {
				logger.error("error in genFooter"+e);
				//e.printStackTrace();
			}
			/*try {
				if(!doc.isOpen())
					doc.open();
			} catch(Exception de) {
				logger.error("error in opening document" + de);
			}*/
		}				
		
		public String checkNull(String result) {

			if (result == null || result.equals("null") || result.equals(" ")) {
				return "";
			} else {
				return result;
			}
		}
		
		public String checkZero(String result) {
		       
			if (result == null || result.equals("null") || result.trim().equals("")) {
				return "0";
			} else {
				try{
					
					Double.parseDouble(result);
					
				}catch(Exception e){
					result="0";
				}
				return result;
			}
		}
	}