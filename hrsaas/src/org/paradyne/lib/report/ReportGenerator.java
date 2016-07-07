/*
 * Code Written By : Sreedhar K Dt:21-08-2004
 */

package org.paradyne.lib.report;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.paradyne.lib.ModelBase;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.rtf.RtfWriter;
import com.lowagie.text.html.HtmlTags;
import com.lowagie.text.rtf.RtfHeaderFooters;
import com.lowagie.text.rtf.RtfHeaderFooter; // import
// com.lowagie.text.rtf.document.RtfDocument;
import org.paradyne.lib.ModelBase;
import java.util.Vector;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import javax.servlet.http.HttpSession;
import java.awt.Color;


/**
 * A class which depending on the parameter passed to it generates the report in the required format
 */

public class ReportGenerator extends PdfPageEventHelper {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReportGenerator.class);
	static boolean rotate = false;

	/**
	 * Function for converting portrait to landscape / ROTATE
	 */
	public static void rotatePage() {
		rotate = true;
	}

	boolean flag;
	String fileType;
	String fName = "download";

	ModelBase model;
	String str, today, repname, fileName, userName;
	int collength;
	Document doc;
	HtmlWriter hw;
	RtfWriter rw;
	HeaderFooter header;
	PDFGenerator pdfg;
	HTMLGenerator htmlg;
	TXTGenerator txtg;
	XLSGenerate xlsg;
	Table table;
	PdfPTable msgRow;
	ByteArrayOutputStream bout;
	public Image headerImage;
	public boolean showPage=true;

	/** The headertable. */
	public PdfPTable pTable = null;

	/** The Graphic state */
	public PdfGState gstate;

	/** A template that will hold the total number of pages. */
	public PdfTemplate tpl;

	/** The font that will be used. */
	public BaseFont helv;

	/**
	 * to create new blank document with the size,width etc
	 * 
	 * @param model
	 * @param type
	 * @param name
	 */
	public ReportGenerator(ModelBase model, String type, String name) {
		repname = name;
		fileName = name.replaceAll("", "");
		fileType = type;

		userName = "asmin";
		this.model = model;
		logger.info("CONTEXT PATH-->" + model.getServletContext());
		String filePath = creatorPath(model.getServletContext(), fileName);
		logger.info("FilePath--->" + filePath);
		Calendar now = Calendar.getInstance();
		int day = now.get(Calendar.DATE);
		int month = now.get(Calendar.MONTH);
		int year = now.get(Calendar.YEAR);

		today = day + "-" + (month + 1) + "-" + year;

		// Creating a new Document.
		// doc=new Document(PageSize.A4,20,20,20,20);

		if(ReportGenerator.rotate != true) doc = new Document(PageSize.A4, 20, 20, 20, 20);
		else doc = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);

		ReportGenerator.rotate = false;
		try {
			if(type.equals("Pdf")) {

				/*
				 * Getting an object of PdfWriter and directing the stream to a file, Creating a PDFGenerator object for use in other functions.
				 */

				// str=".pdf";
				pdfg = new PDFGenerator();
				bout = new java.io.ByteArrayOutputStream();
				pdfg = new PDFGenerator();
				PdfWriter writer = PdfWriter.getInstance(doc, bout);
				writer.setPageEvent(this);
			}// end of if
			else {
				doc.setMargins(54, 54, 54, 54);
				str = ".doc";
				txtg = new TXTGenerator();
				rw = RtfWriter.getInstance(doc, new FileOutputStream(filePath + fileName + ".doc"));
				RtfHeaderFooters rtffooter = new RtfHeaderFooters();
				rtffooter.set(RtfHeaderFooters.ALL_PAGES, genFooter());
				rw.setFooter(rtffooter);
			}// end of else
		} catch(Exception e) {
			e.printStackTrace();
			logger.info("report generator :" + e);
		}
	}
	
	/**
	 * Create new excel sheet instructions on the first sheet
	 * @Date :- 02/11/2011 
	 * @param name :- file name
	 * @param message :- instruction message array
	 * 
	 */
	
	public ReportGenerator(String type, String name, String[] message) {
		repname = name;
		fileName = name.replaceAll(" ", "");
		fileType = type;
		fName = fileName;
		// this.model=model;

		doc = new Document(PageSize.A3.rotate(), 50, 50, 50, 50);
		// doc.setMargins(1.0f, 1.0f,1.0f,1.0f);

		try {
			if(type.equals("Pdf")) {
				/*
				 * Creating a PDFGenerator object for use in other functions.
				 */
				bout = new java.io.ByteArrayOutputStream();
				pdfg = new PDFGenerator();
				PdfWriter writer = PdfWriter.getInstance(doc, bout);
				writer.setPageEvent(this);
				// doc.setFooter(genFooter());
			}// end of if
			else if(type.equals("Txt")) {
				/*
				 * Creating a TXTGenerator object for use in other functions.
				 */
				bout = new java.io.ByteArrayOutputStream();
				txtg = new TXTGenerator();
				rw = RtfWriter.getInstance(doc, bout);
				// doc.setFooter(genFooter());
				RtfHeaderFooters rtffooter = new RtfHeaderFooters();
				rtffooter.set(RtfHeaderFooters.ALL_PAGES, genFooter());
				rw.setFooter(rtffooter);
			}// end of else if
			else if(type.equals("Xls")) {
				bout = new java.io.ByteArrayOutputStream();
				logger.info("Report generator XLS");
				str = ".xls";
				xlsg = new XLSGenerate(fileName, message);
			}// end if else if
		} catch(Exception e) {
			// logger.info("report generator :" + e );
		}
	}
	
	/**
	 * to create new blank document with the size,width etc
	 * 
	 * @param type
	 * @param name
	 */
	public ReportGenerator(String type, String name) {
		repname = name;
		fileName = name.replaceAll(" ", "");
		fileType = type;
		fName = fileName;
		// this.model=model;

		if(ReportGenerator.rotate != true) {
			doc = new Document(PageSize.A4, 50, 50, 50, 50);
		} else {
			doc = new Document(PageSize.A4.rotate(), 50, 50, 50, 50);
		}

		try {
			if(type.equals("Pdf")) {
				/*
				 * Creating a PDFGenerator object for use in other functions.
				 */
				bout = new java.io.ByteArrayOutputStream();
				pdfg = new PDFGenerator();
				PdfWriter writer = PdfWriter.getInstance(doc, bout);
				writer.setPageEvent(this);
				// doc.setFooter(genFooter());
			}// end of if
			else if(type.equals("Txt")) {
				/*
				 * Creating a TXTGenerator object for use in other functions.
				 */
				bout = new java.io.ByteArrayOutputStream();
				txtg = new TXTGenerator();
				rw = RtfWriter.getInstance(doc, bout);
				// doc.setFooter(genFooter());
				RtfHeaderFooters rtffooter = new RtfHeaderFooters();
				rtffooter.set(RtfHeaderFooters.ALL_PAGES, genFooter());
				rw.setFooter(rtffooter);
			}// end of else if
			else if(type.equals("Xls")) {
				bout = new java.io.ByteArrayOutputStream();
				logger.info("Report generator XLS");
				str = ".xls";
				xlsg = new XLSGenerate(fileName);
			}// end of else if
		} catch(Exception e) {
			// logger.info("report generator :" + e );
		}
	}

	public ReportGenerator(String type, String name, HSSFWorkbook workbook, HSSFSheet sheet) {
		repname = name;
		fileName = name.replaceAll(" ", "");
		fileType = type;
		fName = fileName;
		// this.model=model;

		if(ReportGenerator.rotate != true) {
			doc = new Document(PageSize.A4, 50, 50, 50, 50);
		} else {
			doc = new Document(PageSize.A4.rotate(), 50, 50, 50, 50);
		}

		try {
			if(type.equals("Pdf")) {
				/*
				 * Creating a PDFGenerator object for use in other functions.
				 */
				bout = new java.io.ByteArrayOutputStream();
				pdfg = new PDFGenerator();
				PdfWriter writer = PdfWriter.getInstance(doc, bout);
				writer.setPageEvent(this);
				// doc.setFooter(genFooter());
			}// end of if
			else if(type.equals("Txt")) {
				/*
				 * Creating a TXTGenerator object for use in other functions.
				 */
				bout = new java.io.ByteArrayOutputStream();
				txtg = new TXTGenerator();
				rw = RtfWriter.getInstance(doc, bout);
				// doc.setFooter(genFooter());
				RtfHeaderFooters rtffooter = new RtfHeaderFooters();
				rtffooter.set(RtfHeaderFooters.ALL_PAGES, genFooter());
				rw.setFooter(rtffooter);
			}// end of else if
			else if(type.equals("Xls")) {
				bout = new java.io.ByteArrayOutputStream();
				logger.info("Report generator XLS");
				str = ".xls";
				xlsg = new XLSGenerate(fileName, workbook, sheet);
			}// end of else if
		} catch(Exception e) {
			// logger.info("report generator :" + e );
		}
	}

	/**
	 * create new blank document with the size,width etc
	 * 
	 * @param type
	 * @param name
	 * @param pagesize
	 */
	public ReportGenerator(String type, String name, String pagesize) {
		repname = name;
		fileName = name.replaceAll(" ", "");
		fileType = type;
		fName = fileName;
		// this.model=model;

		doc = new Document(PageSize.A3.rotate(), 50, 50, 50, 50);  //left,right,top,bottom
		
		// doc.setMargins(1.0f, 1.0f,1.0f,1.0f);

		try {
			if(type.equals("Pdf")) {
				/*
				 * Creating a PDFGenerator object for use in other functions.
				 */
				bout = new java.io.ByteArrayOutputStream();
				pdfg = new PDFGenerator();
				PdfWriter writer = PdfWriter.getInstance(doc, bout);
				writer.setPageEvent(this);
				// doc.setFooter(genFooter());
			}// end of if
			else if(type.equals("Txt")) {
				/*
				 * Creating a TXTGenerator object for use in other functions.
				 */
				bout = new java.io.ByteArrayOutputStream();
				txtg = new TXTGenerator();
				rw = RtfWriter.getInstance(doc, bout);
				// doc.setFooter(genFooter());
				RtfHeaderFooters rtffooter = new RtfHeaderFooters();
				rtffooter.set(RtfHeaderFooters.ALL_PAGES, genFooter());
				rw.setFooter(rtffooter);
			}// end of else if
			else if(type.equals("Xls")) {
				bout = new java.io.ByteArrayOutputStream();
				logger.info("Report generator XLS");
				str = ".xls";
				xlsg = new XLSGenerate(fileName);
			}// end if else if
		} catch(Exception e) {
			// logger.info("report generator :" + e );
		}
	}
	public ReportGenerator(String type, String name, int[] margin) {
		repname = name;
		fileName = name.replaceAll(" ", "");
		fileType = type;
		fName = fileName;
		// this.model=model;
		if(margin!=null && margin.length>3){
			doc = new Document(PageSize.A2.rotate(), margin[0], margin[1], margin[2], margin[3]);  //left,right,top,bottom
			logger.info("doc type==LETTER");
		}else {
			doc = new Document(PageSize.LETTER.rotate(), 30, 30, 30, 30);  //left,right,top,bottom
		}
		// doc.setMargins(1.0f, 1.0f,1.0f,1.0f);

		try {
			if(type.equals("Pdf")) {
				/*
				 * Creating a PDFGenerator object for use in other functions.
				 */
				bout = new java.io.ByteArrayOutputStream();
				pdfg = new PDFGenerator();
				PdfWriter writer = PdfWriter.getInstance(doc, bout);
				writer.setPageEvent(this);
				// doc.setFooter(genFooter());
			}// end of if
			else if(type.equals("Txt")) {
				/*
				 * Creating a TXTGenerator object for use in other functions.
				 */
				bout = new java.io.ByteArrayOutputStream();
				txtg = new TXTGenerator();
				rw = RtfWriter.getInstance(doc, bout);
				// doc.setFooter(genFooter());
				RtfHeaderFooters rtffooter = new RtfHeaderFooters();
				rtffooter.set(RtfHeaderFooters.ALL_PAGES, genFooter());
				rw.setFooter(rtffooter);
			}// end of else if
			else if(type.equals("Xls")) {
				bout = new java.io.ByteArrayOutputStream();
				logger.info("Report generator XLS");
				str = ".xls";
				xlsg = new XLSGenerate(fileName);
			}// end if else if
		} catch(Exception e) {
			// logger.info("report generator :" + e );
		}
	}

	/**
	 * to add the text in report with borderstyle ,alignment etc
	 * 
	 * @param message
	 * @param style
	 * @param borderStyle
	 * @param align
	 * @param margin
	 */
	public void addFormatedText(String message, int style, int borderStyle, int align, int margin) {
		addFormatedText(new String[]{message}, new int[]{style}, borderStyle, align, margin);
	}

	/**
	 * to add text in report with message,style, borderstyle, align and margin
	 * 
	 * @param message
	 * @param style
	 * @param borderStyle
	 * @param align
	 * @param margin
	 */
	public void addFormatedText(String message[], int style[], int borderStyle, int align, int margin) {
		int fontSize = 11;

		// logger.info("inside formated text");
		Phrase msg = new Phrase("");
		java.util.ArrayList list = new java.util.ArrayList();
		Font font[] = new Font[style.length];
		int[] margins = {margin, (100 - margin)};

		String[] cellMargins = new String[2];
		cellMargins[0] = String.valueOf(margins[0]);
		cellMargins[1] = String.valueOf(margins[1]);
		try {
			try {
				if(!doc.isOpen()) doc.open();
			} catch(Exception de) {
				logger.info("document already open");
			}
			if(fileType.equals("Txt")) {
				table = new Table(1);
				// msgRow.setBorder(Rectangle.NO_BORDER);
				// msgRow.setBorderWidth(0);
				// msgRow.setWidths(margins);
				// msgRow.setPadding(2.0f);

				for(int i = 0; i < message.length; i++) {
					if(style[i] == 1) {
						// font[i]=FontLab.getMatterFont();
						font[i] = FontLab.getBookmanFont(fontSize, 0, false);
					} else if(style[i] == 2) {
						// font[i]=FontLab.getBoldFont();
						font[i] = FontLab.getBookmanFont(fontSize, 1, false);
					} else if(style[i] == 3) {
						// font[i]=FontLab.getItalicFont();
						font[i] = FontLab.getBookmanFont(fontSize, 2, false);
					} else if(style[i] == 4) {
						// font[i]=FontLab.getBoldItalicFont();
						font[i] = FontLab.getBookmanFont(fontSize, 3, false);
					} else if(style[i] == 5) {
						font[i] = FontLab.getBookmanFont(fontSize, 0, true);
						// font[i]=FontLab.getNormalUnderlineFont();
					} else if(style[i] == 6) {
						font[i] = FontLab.getBookmanFont(fontSize, 1, true);
						// font[i]=FontLab.getBoldUnderlineFont();
					} else {
						font[i] = FontLab.getBookmanFont(fontSize, 0, false);
						// font[i]=FontLab.getMatterFont();
					}
					// logger.info("Message "+message[i]+" style
					// "+style[i])
					// ;
					Chunk part = new Chunk(message[i], font[i]);
					list.add(part);
				}
				msg.addAll(list);

				Cell cell = new Cell(new Phrase());
				/*
				 * // cell.setBorder(Rectangle.NO_BORDER); // cell.setBorderWidth(0); // cell.setWidth(cellMargins[0]); msgRow.addCell(cell);
				 */

				cell = new Cell(msg);
				// cell.setBorder(Rectangle.NO_BORDER);
				// cell.setBorderWidth(1);
				// cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}
				table.addCell(cell);
				doc.add(table);
			} else {
				msgRow = new PdfPTable(1);
				// msgRow.setBorder(Rectangle.NO_BORDER);
				// msgRow.setBorderWidth(0);
				// msgRow.setWidths(margins);
				// msgRow.setPadding(2.0f);

				for(int i = 0; i < message.length; i++) {
					if(style[i] == 1) {
						// font[i]=FontLab.getMatterFont();
						font[i] = FontLab.getBookmanFont(fontSize, 0, false);
					} else if(style[i] == 2) {
						// font[i]=FontLab.getBoldFont();
						font[i] = FontLab.getBookmanFont(fontSize, 1, false);
					} else if(style[i] == 3) {
						// font[i]=FontLab.getItalicFont();
						font[i] = FontLab.getBookmanFont(fontSize, 2, false);
					} else if(style[i] == 4) {
						// font[i]=FontLab.getBoldItalicFont();
						font[i] = FontLab.getBookmanFont(fontSize, 3, false);
					} else if(style[i] == 5) {
						font[i] = FontLab.getBookmanFont(fontSize, 0, true);
						// font[i]=FontLab.getNormalUnderlineFont();
					} else if(style[i] == 6) {
						font[i] = FontLab.getBookmanFont(fontSize, 1, true);
						// font[i]=FontLab.getBoldUnderlineFont();
					} else {
						font[i] = FontLab.getBookmanFont(fontSize, 0, false);
						// font[i]=FontLab.getMatterFont();
					}
					// logger.info("Message "+message[i]+" style
					// "+style[i])
					// ;
					Chunk part = new Chunk(message[i], font[i]);
					list.add(part);
				}
				msg.addAll(list);

				PdfPCell cell = new PdfPCell(new Phrase());
				/*
				 * // cell.setBorder(Rectangle.NO_BORDER); // cell.setBorderWidth(0); // cell.setWidth(cellMargins[0]); msgRow.addCell(cell);
				 */

				cell = new PdfPCell(msg);
				// cell.setBorder(Rectangle.NO_BORDER);
				// cell.setBorderWidth(1);
				// cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}
				msgRow.addCell(cell);
				doc.add(msgRow);
			}
		} catch(Exception me) {
			logger.info("in report generator addmsgrow  " + me);
		}
	}
	public void addFormatedTextPaybill(String message[], int style[], int borderStyle, int align, int margin, int fontSize[]) {
		Phrase msg = new Phrase("");
		java.util.ArrayList list = new java.util.ArrayList();
		Font font[] = new Font[style.length];
		int[] margins = {margin, (100 - margin)};

		String[] cellMargins = new String[2];
		cellMargins[0] = String.valueOf(margins[0]);
		cellMargins[1] = String.valueOf(margins[1]);
		try {
			try {
				if(!doc.isOpen()) doc.open();
			} catch(Exception de) {
				logger.info("document already open");
			}
			msgRow = new PdfPTable(2);
			// msgRow.setBorder(Rectangle.NO_BORDER);
			// msgRow.setBorderWidth(0);
			// msgRow.setBorderWidth(0);

			msgRow.setWidths(margins);
			// msgRow.setSpaceInsideCell(0.0f);
			// msgRow.setSpaceBetweenCells(0.0f);
			// msgRow.setPadding(0.0f);
			// msgRow.setSpacing(0.0f);
			// msgRow.setCellspacing(0);
			// msgRow.setCellpadding(0);
			msgRow.setWidthPercentage(50.0f);

			for(int i = 0; i < message.length; i++) {
				if(style[i] == 1) {
					// font[i]=FontLab.getMatterFont();
					font[i] = FontLab.getBookmanFont(fontSize[i], 0, false);
				} else if(style[i] == 2) {
					// font[i]=FontLab.getBoldFont();
					font[i] = FontLab.getBookmanFont(fontSize[i], 1, false);
				} else if(style[i] == 3) {
					// font[i]=FontLab.getItalicFont();
					font[i] = FontLab.getBookmanFont(fontSize[i], 2, false);
				} else if(style[i] == 4) {
					// font[i]=FontLab.getBoldItalicFont();
					font[i] = FontLab.getBookmanFont(fontSize[i], 3, false);
				} else if(style[i] == 5) {
					font[i] = FontLab.getBookmanFont(fontSize[i], 0, true);
					// font[i]=FontLab.getNormalUnderlineFont();
				} else if(style[i] == 6) {
					font[i] = FontLab.getBookmanFont(fontSize[i], 1, true);
					// font[i]=FontLab.getBoldUnderlineFont();
				} else if(style[i] == 7) {
					font[i] = FontLab.getWingdingFont(fontSize[i], 1, true);
					// font[i]=FontLab.getBoldUnderlineFont();
				} else {
					font[i] = FontLab.getBookmanFont(fontSize[i], 0, false);
					// font[i]=FontLab.getMatterFont();
				}
				// logger.info("Message "+message[i]+" style "+style[i])
				// ;
				Chunk part = new Chunk(message[i], font[i]);
				list.add(part);
			}
			msg.addAll(list);

			PdfPCell bcell = new PdfPCell(new Phrase());
			if(borderStyle>4){
				bcell.setBorder(Rectangle.BOX);
			}else
			bcell.setBorder(Rectangle.NO_BORDER);
			bcell.setBorderWidth(0);
			// bcell.setWidth(cellMargins[0]);
			msgRow.addCell(bcell);

			bcell = new PdfPCell(msg);
			if(borderStyle>4){
				bcell.setBorder(Rectangle.BOX);
			}else
			bcell.setBorder(Rectangle.NO_BORDER);
			bcell.setBorderWidth(2);
			// bcell.setWidth(cellMargins[1]);
			bcell.setHorizontalAlignment(align);

			// if(borderStyle==0) {
			// cell.setBorder(Rectangle.NO_BORDER);
			// } else if(borderStyle==1) {
			// cell.setBorder(Rectangle.BOTTOM);
			// } else if(borderStyle==2) {
			// cell.setBorder(Rectangle.TOP);
			// } else if(borderStyle==3) {
			// cell.setBorder(Rectangle.LEFT);
			// } else if(borderStyle==4) {
			// cell.setBorder(Rectangle.RIGHT);
			// } else {
			// cell.setBorder(Rectangle.BOX);
			// }
			msgRow.addCell(bcell);
			doc.add(msgRow);
		} catch(Exception me) {
			logger.info("in report generator addmsgrow  " + me);
		}
	}
	/**
	 * TO add Text in report Styles : 1-normal 2-bold 3-italic 4-bolditalic 5-normal, underline 6-bold , underline borderstyle : 0-no border 1-bottom
	 * 2-top 3-left 4-right
	 */
	public void addFormatedText(String message[], int style[], int borderStyle, int align, int margin, int fontSize[]) {
		Phrase msg = new Phrase("");
		java.util.ArrayList list = new java.util.ArrayList();
		Font font[] = new Font[style.length];
		int[] margins = {margin, (100 - margin)};

		String[] cellMargins = new String[2];
		cellMargins[0] = String.valueOf(margins[0]);
		cellMargins[1] = String.valueOf(margins[1]);
		try {
			try {
				if(!doc.isOpen()) doc.open();
			} catch(Exception de) {
				logger.info("document already open");
			}
			msgRow = new PdfPTable(2);
			// msgRow.setBorder(Rectangle.NO_BORDER);
			// msgRow.setBorderWidth(0);
			// msgRow.setBorderWidth(0);

			msgRow.setWidths(margins);
			// msgRow.setSpaceInsideCell(0.0f);
			// msgRow.setSpaceBetweenCells(0.0f);
			// msgRow.setPadding(0.0f);
			// msgRow.setSpacing(0.0f);
			// msgRow.setCellspacing(0);
			// msgRow.setCellpadding(0);
			msgRow.setWidthPercentage(100.0f);

			for(int i = 0; i < message.length; i++) {
				if(style[i] == 1) {
					// font[i]=FontLab.getMatterFont();
					font[i] = FontLab.getBookmanFont(fontSize[i], 0, false);
				} else if(style[i] == 2) {
					// font[i]=FontLab.getBoldFont();
					font[i] = FontLab.getBookmanFont(fontSize[i], 1, false);
				} else if(style[i] == 3) {
					// font[i]=FontLab.getItalicFont();
					font[i] = FontLab.getBookmanFont(fontSize[i], 2, false);
				} else if(style[i] == 4) {
					// font[i]=FontLab.getBoldItalicFont();
					font[i] = FontLab.getBookmanFont(fontSize[i], 3, false);
				} else if(style[i] == 5) {
					font[i] = FontLab.getBookmanFont(fontSize[i], 0, true);
					// font[i]=FontLab.getNormalUnderlineFont();
				} else if(style[i] == 6) {
					font[i] = FontLab.getBookmanFont(fontSize[i], 1, true);
					// font[i]=FontLab.getBoldUnderlineFont();
				} else if(style[i] == 7) {
					font[i] = FontLab.getWingdingFont(fontSize[i], 1, true);
					// font[i]=FontLab.getBoldUnderlineFont();
				} else {
					font[i] = FontLab.getBookmanFont(fontSize[i], 0, false);
					// font[i]=FontLab.getMatterFont();
				}
				// logger.info("Message "+message[i]+" style "+style[i])
				// ;
				Chunk part = new Chunk(message[i], font[i]);
				list.add(part);
			}
			msg.addAll(list);

			PdfPCell bcell = new PdfPCell(new Phrase());
			if(borderStyle>4){
				bcell.setBorder(Rectangle.BOX);
			}else
			bcell.setBorder(Rectangle.NO_BORDER);
			bcell.setBorderWidth(0);
			// bcell.setWidth(cellMargins[0]);
			msgRow.addCell(bcell);

			bcell = new PdfPCell(msg);
			if(borderStyle>4){
				bcell.setBorder(Rectangle.BOX);
			}else
			bcell.setBorder(Rectangle.NO_BORDER);
			bcell.setBorderWidth(2);
			// bcell.setWidth(cellMargins[1]);
			bcell.setHorizontalAlignment(align);

			// if(borderStyle==0) {
			// cell.setBorder(Rectangle.NO_BORDER);
			// } else if(borderStyle==1) {
			// cell.setBorder(Rectangle.BOTTOM);
			// } else if(borderStyle==2) {
			// cell.setBorder(Rectangle.TOP);
			// } else if(borderStyle==3) {
			// cell.setBorder(Rectangle.LEFT);
			// } else if(borderStyle==4) {
			// cell.setBorder(Rectangle.RIGHT);
			// } else {
			// cell.setBorder(Rectangle.BOX);
			// }
			msgRow.addCell(bcell);
			doc.add(msgRow);
		} catch(Exception me) {
			logger.info("in report generator addmsgrow  " + me);
		}
	}
	/**
	 * to add the text in report with borderstyle ,alignment,font etc
	 * 
	 * @param message
	 * @param style
	 * @param borderStyle
	 * @param align
	 * @param margin
	 * @param fontSize
	 */
	public void addFormatedText(String message, int style, int borderStyle, int align, int margin, int fontSize) {
		addFormatedText(new String[]{message}, new int[]{style}, borderStyle, align, margin, new int[]{fontSize});
	}
	public void addFormatedTextPaybill(String message, int style, int borderStyle, int align, int margin, int fontSize) {
		addFormatedTextPaybill(new String[]{message}, new int[]{style}, borderStyle, align, margin, new int[]{fontSize});
	}

	/**
	 * to add the text in report with borderstyle ,alignment,margin,style etc
	 * 
	 * @param message
	 * @param style
	 * @param borderStyle
	 * @param align
	 * @param margin
	 */
	public void addFormatedText(String message[], int style[], int borderStyle, int[] align, int margin) {

		int fontSize = 11;

		// logger.info("inside formated text");
		Phrase msg = new Phrase("");
		java.util.ArrayList list = new java.util.ArrayList();
		Font font[] = new Font[style.length];
		int[] margins = {margin, (100 - margin)};

		String[] cellMargins = new String[2];
		cellMargins[0] = String.valueOf(margins[0]);
		cellMargins[1] = String.valueOf(margins[1]);
		try {
			try {
				if(!doc.isOpen()) doc.open();
			} catch(Exception de) {
				logger.info("document already open");
			}
			if(fileType.equals("Txt")) {
				table = new Table(1);
				// msgRow.setBorder(Rectangle.NO_BORDER);
				// msgRow.setBorderWidth(0);
				// msgRow.setWidths(margins);
				// msgRow.setPadding(2.0f);

				for(int i = 0; i < message.length; i++) {
					if(style[i] == 1) {
						// font[i]=FontLab.getMatterFont();
						font[i] = FontLab.getBookmanFont(fontSize, 0, false);
					} else if(style[i] == 2) {
						// font[i]=FontLab.getBoldFont();
						font[i] = FontLab.getBookmanFont(fontSize, 1, false);
					} else if(style[i] == 3) {
						// font[i]=FontLab.getItalicFont();
						font[i] = FontLab.getBookmanFont(fontSize, 2, false);
					} else if(style[i] == 4) {
						// font[i]=FontLab.getBoldItalicFont();
						font[i] = FontLab.getBookmanFont(fontSize, 3, false);
					} else if(style[i] == 5) {
						font[i] = FontLab.getBookmanFont(fontSize, 0, true);
						// font[i]=FontLab.getNormalUnderlineFont();
					} else if(style[i] == 6) {
						font[i] = FontLab.getBookmanFont(fontSize, 1, true);
						// font[i]=FontLab.getBoldUnderlineFont();
					} else {
						font[i] = FontLab.getBookmanFont(fontSize, 0, false);
						// font[i]=FontLab.getMatterFont();
					}
					// logger.info("Message "+message[i]+" style
					// "+style[i])
					// ;
					Chunk part = new Chunk(message[i], font[i]);
					list.add(part);
				}
				msg.addAll(list);

				Cell cell = new Cell(new Phrase());
				/*
				 * // cell.setBorder(Rectangle.NO_BORDER); // cell.setBorderWidth(0); // cell.setWidth(cellMargins[0]); msgRow.addCell(cell);
				 */

				cell = new Cell(msg);
				// cell.setBorder(Rectangle.NO_BORDER);
				// cell.setBorderWidth(1);
				// cell.setWidth(cellMargins[1]);

				for(int i = 0; i < align.length; i++) {
					if(align[i] == 0) {
						cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
					}
					if(align[i] == 1) {
						cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
					}
					if(align[i] == 2) {
						cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
					}
					if(align[i] == 3) {
						cell.setHorizontalAlignment(Rectangle.ALIGN_JUSTIFIED);
					}
				}

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}
				table.addCell(cell);
				doc.add(table);
			} else {
				msgRow = new PdfPTable(1);
				// msgRow.setBorder(Rectangle.NO_BORDER);
				// msgRow.setBorderWidth(0);
				// msgRow.setWidths(margins);
				// msgRow.setPadding(2.0f);

				for(int i = 0; i < message.length; i++) {
					if(style[i] == 1) {
						// font[i]=FontLab.getMatterFont();
						font[i] = FontLab.getBookmanFont(fontSize, 0, false);
					} else if(style[i] == 2) {
						// font[i]=FontLab.getBoldFont();
						font[i] = FontLab.getBookmanFont(fontSize, 1, false);
					} else if(style[i] == 3) {
						// font[i]=FontLab.getItalicFont();
						font[i] = FontLab.getBookmanFont(fontSize, 2, false);
					} else if(style[i] == 4) {
						// font[i]=FontLab.getBoldItalicFont();
						font[i] = FontLab.getBookmanFont(fontSize, 3, false);
					} else if(style[i] == 5) {
						font[i] = FontLab.getBookmanFont(fontSize, 0, true);
						// font[i]=FontLab.getNormalUnderlineFont();
					} else if(style[i] == 6) {
						font[i] = FontLab.getBookmanFont(fontSize, 1, true);
						// font[i]=FontLab.getBoldUnderlineFont();
					} else {
						font[i] = FontLab.getBookmanFont(fontSize, 0, false);
						// font[i]=FontLab.getMatterFont();
					}
					// logger.info("Message "+message[i]+" style
					// "+style[i])
					// ;
					Chunk part = new Chunk(message[i], font[i]);
					list.add(part);
				}
				msg.addAll(list);

				PdfPCell cell = new PdfPCell(new Phrase());
				/*
				 * // cell.setBorder(Rectangle.NO_BORDER); // cell.setBorderWidth(0); // cell.setWidth(cellMargins[0]); msgRow.addCell(cell);
				 */

				cell = new PdfPCell(msg);
				// cell.setBorder(Rectangle.NO_BORDER);
				// cell.setBorderWidth(1);
				// cell.setWidth(cellMargins[1]);
				for(int i = 0; i < align.length; i++) {
					if(align[i] == 0) {
						cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
					}
					if(align[i] == 1) {
						cell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
					}
					if(align[i] == 2) {
						cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
					}
					if(align[i] == 3) {
						cell.setHorizontalAlignment(Rectangle.ALIGN_JUSTIFIED);
					}
				}

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}
				msgRow.addCell(cell);
				doc.add(msgRow);
			}
		} catch(Exception me) {
			logger.info("in report generator addmsgrow  " + me);
		}

	}

	/**
	 * to add the text in report with borderstyle ,alignment etc (used for purchase order report )
	 * 
	 * @param message
	 * @param style
	 * @param borderStyle
	 * @param align
	 * @param margin
	 */
	public void addFormatedTextForPurchase(String message, int style, int borderStyle, int align, int margin) {
		addFormatedTextForPurchase(new String[]{message}, new int[]{style}, borderStyle, align, margin);
	}

	/**
	 * to add the text in report with borderstyle ,alignment,margin,style etc
	 * 
	 * @param message
	 * @param style
	 * @param borderStyle
	 * @param align
	 * @param margin
	 */
	public void addFormatedTextForPurchase(String message[], int style[], int borderStyle, int align, int margin) {
		int fontSize = 9;

		// logger.info("inside formated text");
		Phrase msg = new Phrase("");
		java.util.ArrayList list = new java.util.ArrayList();
		Font font[] = new Font[style.length];
		int[] margins = {margin, (100 - margin)};

		String[] cellMargins = new String[2];
		cellMargins[0] = String.valueOf(margins[0]);
		cellMargins[1] = String.valueOf(margins[1]);
		try {
			try {
				if(!doc.isOpen()) doc.open();
			} catch(Exception de) {
				logger.info("document already open");
			}
			if(fileType.equals("Txt")) {
				table = new Table(1);
				// msgRow.setBorder(Rectangle.NO_BORDER);
				// msgRow.setBorderWidth(0);
				// msgRow.setWidths(margins);
				// msgRow.setPadding(2.0f);

				for(int i = 0; i < message.length; i++) {
					if(style[i] == 1) {
						// font[i]=FontLab.getMatterFont();
						font[i] = FontLab.getBookmanFont(fontSize, 0, false);
					} else if(style[i] == 2) {
						// font[i]=FontLab.getBoldFont();
						font[i] = FontLab.getBookmanFont(fontSize, 1, false);
					} else if(style[i] == 3) {
						// font[i]=FontLab.getItalicFont();
						font[i] = FontLab.getBookmanFont(fontSize, 2, false);
					} else if(style[i] == 4) {
						// font[i]=FontLab.getBoldItalicFont();
						font[i] = FontLab.getBookmanFont(fontSize, 3, false);
					} else if(style[i] == 5) {
						font[i] = FontLab.getBookmanFont(fontSize, 0, true);
						// font[i]=FontLab.getNormalUnderlineFont();
					} else if(style[i] == 6) {
						font[i] = FontLab.getBookmanFont(fontSize, 1, true);
						// font[i]=FontLab.getBoldUnderlineFont();
					} else {
						font[i] = FontLab.getBookmanFont(fontSize, 0, false);
						// font[i]=FontLab.getMatterFont();
					}
					// logger.info("Message "+message[i]+" style
					// "+style[i])
					// ;
					Chunk part = new Chunk(message[i], font[i]);
					list.add(part);
				}
				msg.addAll(list);

				Cell cell = new Cell(new Phrase());
				/*
				 * // cell.setBorder(Rectangle.NO_BORDER); // cell.setBorderWidth(0); // cell.setWidth(cellMargins[0]); msgRow.addCell(cell);
				 */

				cell = new Cell(msg);
				// cell.setBorder(Rectangle.NO_BORDER);
				// cell.setBorderWidth(1);
				// cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}
				table.addCell(cell);
				doc.add(table);
			} else {
				msgRow = new PdfPTable(1);
				// msgRow.setBorder(Rectangle.NO_BORDER);
				// msgRow.setBorderWidth(0);
				// msgRow.setWidths(margins);
				// msgRow.setPadding(2.0f);

				for(int i = 0; i < message.length; i++) {
					if(style[i] == 1) {
						// font[i]=FontLab.getMatterFont();
						font[i] = FontLab.getBookmanFont(fontSize, 0, false);
					} else if(style[i] == 2) {
						// font[i]=FontLab.getBoldFont();
						font[i] = FontLab.getBookmanFont(fontSize, 1, false);
					} else if(style[i] == 3) {
						// font[i]=FontLab.getItalicFont();
						font[i] = FontLab.getBookmanFont(fontSize, 2, false);
					} else if(style[i] == 4) {
						// font[i]=FontLab.getBoldItalicFont();
						font[i] = FontLab.getBookmanFont(fontSize, 3, false);
					} else if(style[i] == 5) {
						font[i] = FontLab.getBookmanFont(fontSize, 0, true);
						// font[i]=FontLab.getNormalUnderlineFont();
					} else if(style[i] == 6) {
						font[i] = FontLab.getBookmanFont(fontSize, 1, true);
						// font[i]=FontLab.getBoldUnderlineFont();
					} else {
						font[i] = FontLab.getBookmanFont(fontSize, 0, false);
						// font[i]=FontLab.getMatterFont();
					}
					// logger.info("Message "+message[i]+" style
					// "+style[i])
					// ;
					Chunk part = new Chunk(message[i], font[i]);
					list.add(part);
				}
				msg.addAll(list);

				PdfPCell cell = new PdfPCell(new Phrase());
				/*
				 * // cell.setBorder(Rectangle.NO_BORDER); // cell.setBorderWidth(0); // cell.setWidth(cellMargins[0]); msgRow.addCell(cell);
				 */

				cell = new PdfPCell(msg);
				// cell.setBorder(Rectangle.NO_BORDER);
				// cell.setBorderWidth(1);
				// cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}
				msgRow.addCell(cell);
				doc.add(msgRow);
			}
		} catch(Exception me) {
			logger.info("in report generator addmsgrow  " + me);
		}
	}

	public void addFormatedTextPromo(String message[], int style[], int borderStyle, int align, int margin) {
		int fontSize = 11;

		Phrase msg = new Phrase("");
		java.util.ArrayList list = new java.util.ArrayList();
		Font font[] = new Font[style.length];

		/*
		 * int[] margins = { margin, (100 - margin) }; String[] cellMargins = new String[2]; cellMargins[0] = String.valueOf(margins[0]);
		 * cellMargins[1] = String.valueOf(margins[1]);
		 */
		try {
			try {
				if(!doc.isOpen()) doc.open();
			} catch(Exception de) {
				logger.info("document already open");
			}
			msgRow = new PdfPTable(1);
			// msgRow.setBorder(Rectangle.NO_BORDER);
			// msgRow.setBorderWidth(0);
			// msgRow.setWidths(margins);
			msgRow.setWidthPercentage(95.0f);

			for(int i = 0; i < message.length; i++) {
				if(style[i] == 1) {
					// font[i]=FontLab.getMatterFontPromo();
					font[i] = FontLab.getBookmanFont(fontSize, 0, false);
				} else if(style[i] == 2) {
					font[i] = FontLab.getBoldaddText();
					// font[i] = FontLab.getBookmanFont(fontSize, 1, false);
				} else if(style[i] == 3) {
					// font[i]=FontLab.getItalicFont();
					font[i] = FontLab.getBookmanFont(fontSize, 2, false);
				} else if(style[i] == 4) {
					// font[i]=FontLab.getBoldItalicFont();
					font[i] = FontLab.getBookmanFont(fontSize, 3, false);
				} else if(style[i] == 5) {
					font[i] = FontLab.getBookmanFont(fontSize, 0, true);
					// font[i]=FontLab.getNormalUnderlineFont();
				} else if(style[i] == 6) {
					font[i] = FontLab.getBookmanFont(fontSize, 1, true);
					// font[i]=FontLab.getBoldUnderlineFont();
				} else {
					// font[i] = FontLab.getBookmanFont(fontSize, 0, false);
					font[i] = FontLab.getMatterFontPromo();
				}
				// logger.info("Message "+message[i]+" style
				// "+style[i])
				// ;
				Chunk part = new Chunk(message[i], font[i]);
				list.add(part);
			}
			msg.addAll(list);

			PdfPCell cell = new PdfPCell(new Phrase());
			/*
			 * // cell.setBorder(Rectangle.NO_BORDER); // cell.setBorderWidth(0); // cell.setWidth(cellMargins[0]); msgRow.addCell(cell);
			 */

			cell = new PdfPCell(msg);
			// cell.setBorder(Rectangle.NO_BORDER);
			// cell.setBorderWidth(0);
			// cell.setWidth(cellMargins[1]);
			cell.setHorizontalAlignment(align);

			if(borderStyle == 0) {
				cell.setBorder(Rectangle.NO_BORDER);
			} else if(borderStyle == 1) {
				cell.setBorder(Rectangle.BOTTOM);
			} else if(borderStyle == 2) {
				cell.setBorder(Rectangle.TOP);
			} else if(borderStyle == 3) {
				cell.setBorder(Rectangle.LEFT);
			} else if(borderStyle == 4) {
				cell.setBorder(Rectangle.RIGHT);
			} else {
				cell.setBorder(Rectangle.BOX);
			}
			msgRow.addCell(cell);
			doc.add(msgRow);

		} catch(Exception me) {
			logger.info("in report generator addmsgrow  " + me);
		}
	}

	/**
	 * to add the table with related object
	 * 
	 * @param obj
	 * @param details
	 */
	public void addTable(Object[][] obj, String[] details) {
		try {

			int width = ((Object[])obj[0]).length;
			int rows = (int)Math.ceil(width / 2);

			String[][] data = new String[obj.length][width];

			for(int i = 0; i < obj.length; i++) {
				for(int j = 0; j < width; j++) {
					try {
						data[i][j] = String.valueOf(obj[i][j]);
					} catch(Exception e1) {
						logger.info("error in converting data in addTable " + e1);
						data[i][j] = " ";
					}
				}
			}

			Table table = new Table(4);
			table.setAutoFillEmptyCells(true);
			table.setBorder(Rectangle.NO_BORDER);
			table.setPadding(2.0f);
			Cell tableCell = new Cell();
			int k = 0;

			for(int i = 0; i < rows; i++) {

				tableCell = new Cell(new Phrase(details[k], FontLab.getBodyHeaderFont()));
				table.addCell(tableCell, new java.awt.Point(i, 0));
				tableCell.setBorder(Rectangle.NO_BORDER);

				tableCell = new Cell(new Phrase(data[0][k], FontLab.getBodyFont()));
				table.addCell(tableCell, new java.awt.Point(i, 1));
				tableCell.setBorder(Rectangle.NO_BORDER);

				tableCell = new Cell(new Phrase(details[k + 1], FontLab.getBodyHeaderFont()));
				table.addCell(tableCell, new java.awt.Point(i, 2));
				tableCell.setBorder(Rectangle.NO_BORDER);

				tableCell = new Cell(new Phrase(data[0][k + 1], FontLab.getBodyFont()));
				table.addCell(tableCell, new java.awt.Point(i, 3));
				tableCell.setBorder(Rectangle.NO_BORDER);

				k = k + 2;
			}

			doc.add(table);

		} catch(Exception e) {
			logger.info("In Report generator addTable " + e);
		}

	}

	/**
	 * to add the header to the table with underlined required or not
	 * 
	 * @param msg
	 * @param borderStyle
	 * @param align
	 * @param margin
	 * @param underline
	 */
	public void addTableHeader(String msg, int borderStyle, int align, int margin, boolean underline) {

		if(fileType.equals("Xls")) {
			xlsg.addText(msg, borderStyle, align, margin);
		}
		if(fileType.equals("Txt")) {

			int[] margins = {margin, (100 - margin)};
			String[] cellMargins = new String[2];
			cellMargins[0] = String.valueOf(margins[0]);
			cellMargins[1] = String.valueOf(margins[1]);
			try {
				try {
					if(!doc.isOpen()) doc.open();
				} catch(Exception de) {
					logger.info("document already open");
				}

				table = new Table(2);
				table.setBorder(Rectangle.NO_BORDER);
				table.setBorderWidth(0);
				table.setWidths(margins);
				table.setPadding(2.0f);

				Cell cell = new Cell(new Phrase());
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				cell.setWidth(cellMargins[0]);
				table.addCell(cell);

				cell = new Cell(new Phrase(msg, FontLab.getBookmanFont(11, 0, false)));
				// cell=new Cell(new Phrase(msg,FontLab.getBookmanFont(11,0)));

				// cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(1);
				// cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}

				table.addCell(cell);
				doc.add(table);
				logger.info("Report-----------------------Text");
			} catch(Exception me) {
				logger.info("in report generator adtext  " + me);
			}
		} else {
			int[] margins = {margin, (100 - margin)};
			String[] cellMargins = new String[2];
			cellMargins[0] = (new Integer(margins[0])).toString();
			cellMargins[1] = (new Integer(margins[1])).toString();

			try {
				try {
					if(!doc.isOpen()) doc.open();
				} catch(Exception de) {
					// logger.info("document already open");
				}

				msgRow = new PdfPTable(2);
				// msgRow.setBorder(Rectangle.NO_BORDER);
				// msgRow.setBorderWidth(0);
				msgRow.setWidths(margins);
				// msgRow.setPadding(2.0f);
				msgRow.setWidthPercentage(95.0f);

				PdfPCell cell = new PdfPCell(new Phrase());
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				// cell.setWidth(cellMargins[0]);
				msgRow.addCell(cell);
				if(underline) cell = new PdfPCell(new Phrase(msg, FontLab.getBoldUnderlineFont()));
				else cell = new PdfPCell(new Phrase(msg, FontLab.getBoldFont()));
				logger.info("getBoldUnderlineFont");
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				// cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}
				msgRow.addCell(cell);
				doc.add(msgRow);
			} catch(Exception me) {
				// logger.info("in report generator addmsgrow "+me);
			}
		}
	}

	/**
	 * to add the header to the table with underlined required or not
	 * 
	 * @param msg
	 * @param borderStyle
	 * @param align
	 * @param margin
	 * @param underline
	 */
	public void addTableHeader(String msg, int borderStyle, int align, int margin, boolean underline, int fontSize) {

		if(fileType.equals("Xls")) {
			xlsg.addText(msg, borderStyle, align, margin);
		}
		if(fileType.equals("Txt")) {

			int[] margins = {margin, (100 - margin)};
			String[] cellMargins = new String[2];
			cellMargins[0] = String.valueOf(margins[0]);
			cellMargins[1] = String.valueOf(margins[1]);
			try {
				try {
					if(!doc.isOpen()) doc.open();
				} catch(Exception de) {
					logger.info("document already open");
				}

				table = new Table(2);
				table.setBorder(Rectangle.NO_BORDER);
				table.setBorderWidth(0);
				table.setWidths(margins);
				table.setPadding(2.0f);

				Cell cell = new Cell(new Phrase());
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				cell.setWidth(cellMargins[0]);
				table.addCell(cell);

				cell = new Cell(new Phrase(msg, FontLab.getBookmanFont(fontSize, 0, false)));
				// cell=new Cell(new Phrase(msg,FontLab.getBookmanFont(11,0)));

				// cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(1);
				// cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}

				table.addCell(cell);
				doc.add(table);
				logger.info("Report-----------------------Text");
			} catch(Exception me) {
				logger.info("in report generator adtext  " + me);
			}
		} else {
			int[] margins = {margin, (100 - margin)};
			String[] cellMargins = new String[2];
			cellMargins[0] = (new Integer(margins[0])).toString();
			cellMargins[1] = (new Integer(margins[1])).toString();

			try {
				try {
					if(!doc.isOpen()) doc.open();
				} catch(Exception de) {
					// logger.info("document already open");
				}

				msgRow = new PdfPTable(2);
				// msgRow.setBorder(Rectangle.NO_BORDER);
				// msgRow.setBorderWidth(0);
				msgRow.setWidths(margins);
				// msgRow.setPadding(2.0f);
				msgRow.setWidthPercentage(95.0f);

				PdfPCell cell = new PdfPCell(new Phrase());
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				// cell.setWidth(cellMargins[0]);
				msgRow.addCell(cell);
				if(underline) cell = new PdfPCell(new Phrase(msg, FontLab.getBoldUnderlineFont(fontSize)));
				else cell = new PdfPCell(new Phrase(msg, FontLab.getBoldFont(fontSize)));
				logger.info("getBoldUnderlineFont");
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				// cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}
				msgRow.addCell(cell);
				doc.add(msgRow);
			} catch(Exception me) {
				// logger.info("in report generator addmsgrow "+me);
			}
		}
	}

	/**
	 * to add the text wherever in the report
	 * 
	 * @param msg
	 * @param borderStyle
	 * @param align
	 * @param margin
	 */
	public void addText(String msg, int borderStyle, int align, int margin) {

		if(fileType.equals("Xls")) {
			xlsg.addText(msg.trim(), borderStyle, align, margin);
		}
		if(fileType.equals("Txt")) {

			int[] margins = {margin, (100 - margin)};
			String[] cellMargins = new String[2];
			cellMargins[0] = String.valueOf(margins[0]);
			cellMargins[1] = String.valueOf(margins[1]);
			try {
				try {
					if(!doc.isOpen()) doc.open();
				} catch(Exception de) {
					logger.info("document already open");
				}

				table = new Table(2);
				table.setBorder(Rectangle.NO_BORDER);
				table.setBorderWidth(0);
				table.setWidths(margins);
				table.setPadding(2.0f);

				Cell cell = new Cell(new Phrase());
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				cell.setWidth(cellMargins[0]);
				table.addCell(cell);

				cell = new Cell(new Phrase(msg, FontLab.getBookmanFont(11, 0, false)));
				// cell=new Cell(new Phrase(msg,FontLab.getBookmanFont(11,0)));

				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(1);
				cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}

				table.addCell(cell);
				doc.add(table);
				logger.info("Report-----------------------Text");
			} catch(Exception me) {
				logger.info("in report generator adtext  " + me);
			}
		} else {
			int[] margins = {margin, (100 - margin)};
			String[] cellMargins = new String[2];
			cellMargins[0] = (new Integer(margins[0])).toString();
			cellMargins[1] = (new Integer(margins[1])).toString();

			try {
				try {
					if(!doc.isOpen()) doc.open();
				} catch(Exception de) {
					// logger.info("document already open");
				}

				msgRow = new PdfPTable(2);
				// msgRow.setBorder(Rectangle.NO_BORDER);
				// msgRow.setBorderWidth(0);
				msgRow.setWidths(margins);
				// msgRow.setPadding(2.0f);
				msgRow.setWidthPercentage(95.0f);

				PdfPCell cell = new PdfPCell(new Phrase());
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				// cell.setWidth(cellMargins[0]);
				msgRow.addCell(cell);

				cell = new PdfPCell(new Phrase(msg, FontLab.getMatterFont()));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				// cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}
				msgRow.addCell(cell);
				doc.add(msgRow);
			} catch(Exception me) {
				// logger.info("in report generator addmsgrow "+me);
			}
		}
	}

	/**
	 * to add the text wherever in the report
	 * 
	 * @param msg
	 * @param borderStyle
	 * @param align
	 * @param margin
	 */
	public void addText(String msg, int borderStyle, int align, int margin, int fontSize) {
		if(fileType.equals("Xls")) {
			xlsg.addText(msg.trim(), borderStyle, align, margin);
		}
		if(fileType.equals("Txt")) {

			int[] margins = {margin, (100 - margin)};
			String[] cellMargins = new String[2];
			cellMargins[0] = String.valueOf(margins[0]);
			cellMargins[1] = String.valueOf(margins[1]);
			try {
				try {
					if(!doc.isOpen()) doc.open();
				} catch(Exception de) {
					logger.info("document already open");
				}

				table = new Table(2);
				table.setBorder(Rectangle.NO_BORDER);
				table.setBorderWidth(0);
				table.setWidths(margins);
				table.setPadding(2.0f);

				Cell cell = new Cell(new Phrase());
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				cell.setWidth(cellMargins[0]);
				table.addCell(cell);

				cell = new Cell(new Phrase(msg, FontLab.getBookmanFont(fontSize, 0, false)));
				// cell=new Cell(new Phrase(msg,FontLab.getBookmanFont(11,0)));

				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(1);
				cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}

				table.addCell(cell);
				doc.add(table);
				logger.info("Report-----------------------Text");
			} catch(Exception me) {
				logger.info("in report generator adtext  " + me);
			}
		} else {
			int[] margins = {margin, (100 - margin)};
			String[] cellMargins = new String[2];
			cellMargins[0] = (new Integer(margins[0])).toString();
			cellMargins[1] = (new Integer(margins[1])).toString();

			try {
				try {
					if(!doc.isOpen()) doc.open();
				} catch(Exception de) {
					// logger.info("document already open");
				}

				msgRow = new PdfPTable(2);
				// msgRow.setBorder(Rectangle.NO_BORDER);
				// msgRow.setBorderWidth(0);
				msgRow.setWidths(margins);
				// msgRow.setPadding(2.0f);
				msgRow.setWidthPercentage(95.0f);

				PdfPCell cell = new PdfPCell(new Phrase());
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				// cell.setWidth(cellMargins[0]);
				msgRow.addCell(cell);

				cell = new PdfPCell(new Phrase(msg, FontLab.getMatterFont()));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				// cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}
				msgRow.addCell(cell);
				doc.add(msgRow);
			} catch(Exception me) {
				// logger.info("in report generator addmsgrow "+me);
			}
		}
	}

	// ------------------ newly added for promotion by prakash------------------------------------------------
	public void addTextBold(String msg, int borderStyle, int align, int margin) {

		if(fileType.equals("Xls")) {
			xlsg.addText(msg, borderStyle, align, margin);
		}
		if(fileType.equals("Txt")) {

			int[] margins = {margin, (100 - margin)};
			String[] cellMargins = new String[2];
			cellMargins[0] = String.valueOf(margins[0]);
			cellMargins[1] = String.valueOf(margins[1]);
			try {
				try {
					if(!doc.isOpen()) doc.open();
				} catch(Exception de) {
					logger.info("document already open");
				}

				table = new Table(2);
				table.setBorder(Rectangle.NO_BORDER);
				table.setBorderWidth(0);
				table.setWidths(margins);
				table.setPadding(2.0f);

				Cell cell = new Cell(new Phrase());
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				cell.setWidth(cellMargins[0]);
				table.addCell(cell);

				cell = new Cell(new Phrase(msg, FontLab.getBookmanFont(11, 0, false)));
				// cell=new Cell(new Phrase(msg,FontLab.getBookmanFont(11,0)));

				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(1);
				cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}

				table.addCell(cell);
				doc.add(table);
				logger.info("Report-----------------------Text");
			} catch(Exception me) {
				logger.info("in report generator adtext  " + me);
			}
		} else {
			int[] margins = {margin, (100 - margin)};
			String[] cellMargins = new String[2];
			cellMargins[0] = (new Integer(margins[0])).toString();
			cellMargins[1] = (new Integer(margins[1])).toString();

			try {
				try {
					if(!doc.isOpen()) doc.open();
				} catch(Exception de) {
					// logger.info("document already open");
				}

				msgRow = new PdfPTable(2);
				// msgRow.setBorder(Rectangle.NO_BORDER);
				// msgRow.setBorderWidth(0);
				msgRow.setWidths(margins);
				// msgRow.setPadding(2.0f);
				msgRow.setWidthPercentage(95.0f);

				PdfPCell cell = new PdfPCell(new Phrase());
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				// cell.setWidth(cellMargins[0]);
				msgRow.addCell(cell);

				cell = new PdfPCell(new Phrase(msg, FontLab.getBoldaddText()));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				// cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}
				msgRow.addCell(cell);
				doc.add(msgRow);
			} catch(Exception me) {
				// logger.info("in report generator addmsgrow "+me);
			}
		}
	}

	public void addTextBold(String msg, int borderStyle, int align, int margin, int fontSize) {

		if(fileType.equals("Xls")) {
			xlsg.addText(msg, borderStyle, align, margin);
		}
		if(fileType.equals("Txt")) {

			int[] margins = {margin, (100 - margin)};
			String[] cellMargins = new String[2];
			cellMargins[0] = String.valueOf(margins[0]);
			cellMargins[1] = String.valueOf(margins[1]);
			try {
				try {
					if(!doc.isOpen()) doc.open();
				} catch(Exception de) {
					logger.info("document already open");
				}

				table = new Table(2);
				table.setBorder(Rectangle.NO_BORDER);
				table.setBorderWidth(0);
				table.setWidths(margins);
				table.setPadding(2.0f);

				Cell cell = new Cell(new Phrase());
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				cell.setWidth(cellMargins[0]);
				table.addCell(cell);

				cell = new Cell(new Phrase(msg, FontLab.getBookmanFont(fontSize, 0, false)));
				// cell=new Cell(new Phrase(msg,FontLab.getBookmanFont(11,0)));

				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(1);
				cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}

				table.addCell(cell);
				doc.add(table);
				logger.info("Report-----------------------Text");
			} catch(Exception me) {
				logger.info("in report generator adtext  " + me);
			}
		} else {
			int[] margins = {margin, (100 - margin)};
			String[] cellMargins = new String[2];
			cellMargins[0] = (new Integer(margins[0])).toString();
			cellMargins[1] = (new Integer(margins[1])).toString();

			try {
				try {
					if(!doc.isOpen()) doc.open();
				} catch(Exception de) {
					// logger.info("document already open");
				}

				msgRow = new PdfPTable(2);
				// msgRow.setBorder(Rectangle.NO_BORDER);
				// msgRow.setBorderWidth(0);
				msgRow.setWidths(margins);
				// msgRow.setPadding(2.0f);
				msgRow.setWidthPercentage(95.0f);

				PdfPCell cell = new PdfPCell(new Phrase());
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				// cell.setWidth(cellMargins[0]);
				msgRow.addCell(cell);

				cell = new PdfPCell(new Phrase(msg, FontLab.getBoldaddText(fontSize)));
				//cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(2);
				// cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}
				cell.setBorderWidth(2);
				msgRow.addCell(cell);
				doc.add(msgRow);
			} catch(Exception me) {
				// logger.info("in report generator addmsgrow "+me);
			}
		}
	}

	public void addTextPromo(String msg, int borderStyle, int align, int margin) {

		if(fileType.equals("Xls")) {
			xlsg.addText(msg, borderStyle, align, margin);
		}
		if(fileType.equals("Txt")) {

			int[] margins = {margin, (100 - margin)};
			String[] cellMargins = new String[2];
			cellMargins[0] = String.valueOf(margins[0]);
			cellMargins[1] = String.valueOf(margins[1]);
			try {
				try {
					if(!doc.isOpen()) doc.open();
				} catch(Exception de) {
					logger.info("document already open");
				}

				table = new Table(2);
				table.setBorder(Rectangle.NO_BORDER);
				table.setBorderWidth(0);
				table.setWidths(margins);
				table.setPadding(2.0f);

				Cell cell = new Cell(new Phrase());
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				cell.setWidth(cellMargins[0]);
				table.addCell(cell);

				cell = new Cell(new Phrase(msg, FontLab.getBookmanFont(11, 0, false)));
				// cell=new Cell(new Phrase(msg,FontLab.getBookmanFont(11,0)));

				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(1);
				cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}

				table.addCell(cell);
				doc.add(table);
				logger.info("Report-----------------------Text");
			} catch(Exception me) {
				logger.info("in report generator adtext  " + me);
			}
		} else {
			int[] margins = {margin, (100 - margin)};
			String[] cellMargins = new String[2];
			cellMargins[0] = (new Integer(margins[0])).toString();
			cellMargins[1] = (new Integer(margins[1])).toString();

			try {
				try {
					if(!doc.isOpen()) doc.open();
				} catch(Exception de) {
					// logger.info("document already open");
				}

				msgRow = new PdfPTable(2);
				// msgRow.setBorder(Rectangle.NO_BORDER);
				// msgRow.setBorderWidth(0);
				msgRow.setWidths(margins);
				// msgRow.setPadding(2.0f);
				msgRow.setWidthPercentage(95.0f);

				PdfPCell cell = new PdfPCell(new Phrase());
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				// cell.setWidth(cellMargins[0]);
				msgRow.addCell(cell);

				cell = new PdfPCell(new Phrase(msg, FontLab.getMatterFontPromo()));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setBorderWidth(0);
				// cell.setWidth(cellMargins[1]);
				cell.setHorizontalAlignment(align);

				if(borderStyle == 0) {
					cell.setBorder(Rectangle.NO_BORDER);
				} else if(borderStyle == 1) {
					cell.setBorder(Rectangle.BOTTOM);
				} else if(borderStyle == 2) {
					cell.setBorder(Rectangle.TOP);
				} else if(borderStyle == 3) {
					cell.setBorder(Rectangle.LEFT);
				} else if(borderStyle == 4) {
					cell.setBorder(Rectangle.RIGHT);
				} else {
					cell.setBorder(Rectangle.BOX);
				}
				msgRow.addCell(cell);
				doc.add(msgRow);
			} catch(Exception me) {
				// logger.info("in report generator addmsgrow "+me);
			}
		}
	}

	/**
	 * function to add row of sum at the end of table. Function for adding a row which shows the total for a particular column.
	 * 
	 * @param msg
	 * @param cols
	 * @param pos
	 * @param value
	 */
	public void addTotalRow(String msg, int cols, int pos, String value) {
		try {
			// cols -- no of colums in the table
			// pos -- position of the value String i.e under whic column is it
			// needed
			Table totalRow = new Table(cols);
			totalRow.setBorder(Rectangle.NO_BORDER);
			totalRow.setPadding(2.0f);

			Cell cell = new Cell(new Phrase(msg, FontLab.getMatterFont()));
			cell.setBorder(Rectangle.NO_BORDER);
			// cell.setBorder(Rectangle.BOTTOM);
			cell.setColspan(pos - 1);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			totalRow.addCell(cell);

			cell = new Cell(new Phrase(value, FontLab.getMatterFont()));
			cell.setBorder(Rectangle.NO_BORDER);
			// cell.setBorder(Rectangle.BOTTOM);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			totalRow.addCell(cell);
			doc.add(totalRow);
		} catch(Exception te) {
			// logger.info("in report generator addtotalrow "+te);
		}
	}

	public String checkNull(String result) {
		// logger.info("\n\n\n\n****HEMANT");
		if(result == null || result.equals("null") || result.equals(" ")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * to generate header for PdfPTable
	 * 
	 * @param pTable return--PdfPTable
	 */
	public PdfPTable createHeader(PdfPTable pTable) {
		this.pTable = pTable;
		return pTable;
	}

	/**
	 * Function which finally creates the document
	 */
	public String createReport() {
		// Closing the document.
		try {
			if(doc.isOpen()) {
				doc.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			if(xlsg != null) {
				xlsg.closeWrite(bout);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		String retpath = returnPath(flag);
		return retpath;
	}
	/**
	 * Kiosk specific create report function
	 */
	public void createReportForKiosk(HttpServletResponse response) {
		try {
			doc.close();
			if(fileType.equals("Pdf")) {
				response.setContentType("application/pdf");
				fName += ".pdf";
				
			}
			response.getOutputStream().write(bout.toByteArray());
			} catch(Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * Function which finally creates the document
	 */
	public void createReport(HttpServletResponse response) {
		try {
			doc.close();
			if(fileType.equals("Pdf")) {
				response.setContentType("application/pdf");
				fName += ".pdf";
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fName + "\"");
			}
			if(fileType.equals("Txt")) {
				response.setContentType("application/msword");
				if(fName.equals("download")) {
					fName += ".doc";
				}
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fName + "\"");
			}
			if(fileType.equals("Xls")) {
				response.setContentType("application/vnd.ms-excel");
				fName += ".xls";
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fName + "\"");
				xlsg.closeWrite(bout);
			}
			response.getOutputStream().write(bout.toByteArray());
		} catch(Exception e) {}
	}

	/**
	 * @param context
	 * @param reportName
	 * @return
	 */
	public String creatorPath(javax.servlet.ServletContext context, String reportName) {
		String path = "";

		// get real path i.e where the file has to be placed.
		path = context.getRealPath("\\");

		path = path + "reportGenerated\\";

		// checking to see if the directory structure exists or not if not then
		// create it.
		java.io.File f = new java.io.File(path);
		if(!f.exists()) {
			try {
				f.mkdir();
				flag = true;
			} catch(SecurityException se) {
				flag = false;
				userName = "";
				path = context.getRealPath("\\") + "reportGenerated";
			}
		} else {
			flag = true;
			path = context.getRealPath("\\") + "reportGenerated\\" + userName + "\\";
		}
		return path;
	}

	/**
	 * to generate header
	 * 
	 * @param headerTable
	 * @return headerTable
	 */
	public PdfPTable generateHeader(PdfPTable headerTable) {
		if(headerTable == null) {
			headerTable = new PdfPTable(1);
		}
		return headerTable;
	}

	/**
	 * Function for generating a footer depending on the type of file passed while creating the object of ReportGenerator.
	 * 
	 * @return
	 */
	public HeaderFooter genFooter() {
		HeaderFooter footer = null;
		if(fileType.equals("Pdf")) {
			footer = pdfg.genFooter();
		} else if(fileType.equals("Txt")) {
			footer = txtg.genFooter();
		}
		return footer;
	}

	/**
	 * to generate the report header with proper date ,report name etc
	 * 
	 * @param name
	 */
	public void genHeader(String name) {
		String coname = "";
		String address = "";
		String imgname = "";
		String img = "C:\\win.jpg";
		Calendar cal = Calendar.getInstance(java.util.TimeZone.getDefault());
		String today = cal.get(Calendar.DAY_OF_MONTH) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR);

		if(fileType.equals("Pdf")) {
			// Creating a PDF file Header.
			logger.info(" PDF 1 ");
			header = pdfg.genHeader(coname, today, repname, img, address);
			logger.info(" PDF 2 ");
			doc.setHeader(header);
		} else if(fileType.equals("Html")) {
			// Creating a HTML file Header.
			header = htmlg.genHeader(coname, today, repname, img, address);
			doc.setHeader(header);
		} else if(fileType.equals("Xls")) {
			logger.info("XLS 203");

		} else {
			// Creating a TEXT file Header.
			Rectangle rect = doc.getPageSize();
			int width = new Float(rect.width()).intValue();
			RtfHeaderFooters rtfHeader = txtg.genHeader(coname, today, repname, img, address, width);
			rw.setHeader(rtfHeader);
		}
		try {
			if(!doc.isOpen()) doc.open();
		} catch(Exception de) {
			de.printStackTrace();
			logger.info("document already open header");
		}
	}

	/**
	 * Function for adding image to the report while creating the object of ReportGenerator.
	 * 
	 * @param name
	 * @param data
	 * @param nophoto
	 */
	public void genImage(String name, String data, String nophoto) {
		String imgname = name;

		String filepath = "/pages/images/employee/" + imgname;

		ServletContext serv = model.getServletContext();

		String img = serv.getRealPath("//") + filepath;
		try {
			Image image = Image.getInstance(img);

			image.setAlignment(2);
		} catch(Exception e) {
			img = serv.getRealPath("//") + "/pages/images/employee/" + nophoto;
		}
		if(fileType.equals("Pdf")) {
			try {
				doc.add(pdfg.genPhoto(data, img));
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(fileType.equals("Html")) {
			// Creating a HTML file Header.
			doc.setHeader(header);
		} else if(fileType.equals("Xls")) {
			logger.info("XLS 203");
		}

		try {
			if(!doc.isOpen()) doc.open();
		} catch(Exception de) {
			de.printStackTrace();
			logger.info("document already open header");
		}
	}

	/**
	 * to add the image of employee
	 * 
	 * @param name
	 * @param data
	 * @param nophoto
	 * @param session
	 */
	public void genImageEmp(String name, String data, String nophoto, HttpSession session) {
		String imgname = name;
		String str = (String)session.getAttribute("session_pool");
		String img = model.getServletContext().getRealPath("//") + "//pages//images//" + str + "//employee//" + imgname;// +".jpg";
		ServletContext serv = model.getServletContext();

		try {
			Image image = Image.getInstance(img);

			image.setAlignment(2);
		} catch(Exception e) {
			img = serv.getRealPath("//") + "/pages/images/employee/" + nophoto;
		}
		if(fileType.equals("Pdf")) {
			try {
				doc.add(pdfg.genPhoto(data, img));
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(fileType.equals("Html")) {
			// Creating a HTML file Header.
			doc.setHeader(header);
		} else if(fileType.equals("Xls")) {
			logger.info("XLS 203");
		}

		try {
			if(!doc.isOpen()) doc.open();
		} catch(Exception de) {
			de.printStackTrace();
			logger.info("document already open header");
		}
	}

	/**
	 * TO GET DOC FOR MODifications
	 */
	public Document getDocument() {
		return doc;
	}

	public String getFName() {
		return fName;
	}

	/**
	 * to generate the page number at footer
	 * 
	 * @param writer
	 * @param document
	 */
	public void onCloseDocument(PdfWriter writer, Document document) {
		try {
			tpl.beginText();
			tpl.setFontAndSize(helv, 8);
			tpl.setTextMatrix(0, 0);
			if(showPage)
			tpl.showText("" + (writer.getPageNumber() - 1));
			else{
				tpl.showText("");
			}
			tpl.endText();
		} catch(Exception e) {}
	}

	/**
	 * to add proper page number with alignment in footer
	 * 
	 * @param writer
	 * @param document
	 */
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			PdfContentByte cb = writer.getDirectContent();
			cb.saveState();

			if(pTable == null) {
				pTable = new PdfPTable(1);
			}
			pTable.setTotalWidth(document.right() - document.left());
			pTable.writeSelectedRows(0, -1, document.left(), document.getPageSize().height() - 50, cb);
			// compose the footer
			String text="";
			if(showPage)
			text = "Page " + writer.getPageNumber() + " of ";
			float textSize = helv.getWidthPoint(text, 8);
			float textBase = document.bottom() - 5;
			cb.beginText();
			cb.setFontAndSize(helv, 8);
			cb.setTextMatrix((document.right() - document.left()) / 2, textBase);
			cb.showText(text);
			cb.endText();
			cb.addTemplate(tpl, (document.right() - document.left()) / 2 + textSize, textBase);
			cb.saveState();
			cb.restoreState();
		} catch(Exception e) {}
	}

	/**
	 * to open document to write the content
	 * 
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
		} catch(Exception e) {
			throw new ExceptionConverter(e);
		}
	}

	/**
	 * function for new page
	 */
	public void pageBreak() {
		try {
			doc.newPage();
		} catch(Exception e) {
			logger.info("Cannot break");
		}
	}

	public String returnPath(boolean value) {
		String returnFilePath = "";
		if(value) {
			returnFilePath = "reportGenerated/" + userName + "/" + fileName + str;
		} else {
			returnFilePath = "reportGenerated" + "/" + fileName + str;
		}
		return returnFilePath;
	}

	/**
	 * To scan the data and remove the blank rows returns---String[][]
	 */
	public String[][] scanData(Object[][] obj) {
		Vector mainVect = new Vector();
		
		for(int i = 0; i < obj.length; i++) {
			boolean flag = false;
			for(int j = 0; j < obj[i].length; j++) {
				if(String.valueOf(obj[i][j]).trim().equals("") || String.valueOf(obj[i][j]).trim().equals("null")) {
				} else {
					flag = true;
					break;
				}
			}
			if(flag) {
				Vector childVect = new Vector();
				for(int j = 0; j < obj[0].length; j++) {
					childVect.add(obj[i][j]);
				}
				mainVect.add(childVect);
			}
		}
		String[][] colData = new String[mainVect.size()][obj[0].length];
		for(int i = 0; i < mainVect.size(); i++) {
			Vector childVect = (Vector)mainVect.get(i);
			for(int j = 0; j < childVect.size(); j++) {
				try {
					if(String.valueOf(childVect.get(j)).equals("null")) {
						colData[i][j] = " ";
					} else {
						colData[i][j] = String.valueOf(childVect.get(j));
					}
				} catch(Exception ae) {
					colData[i][j] = " ";
				}
			}
		}
		return colData;
	}

	public void setFName(String name) {
		fName = name;
	}

	/**
	 * function for setting the margins of the document
	 * 
	 * @param marginLeft
	 * @param marginRight
	 * @param marginTop
	 * @param marginBottom
	 */
	public void setMargins(double marginLeft, double marginRight, double marginTop, double marginBottom) {
		float left = (new Double(marginLeft)).floatValue();
		float right = (new Double(marginRight)).floatValue();
		float top = (new Double(marginTop)).floatValue();
		float bottom = (new Double(marginBottom)).floatValue();
		doc.setMargins(left, right, top, bottom);
	}

	/**
	 * function for setting some of the standard page sizes of the document
	 */
	public void setPageSize(int size) {
		if(size == 1) doc.setPageSize(PageSize.A3);
		else if(size == 2) doc.setPageSize(PageSize.A6);
		else if(size == 3) doc.setPageSize(PageSize.LEGAL);
		else if(size == 4) doc.setPageSize(PageSize.LETTER);
		else doc.setPageSize(PageSize.A4);
	}

	/**
	 * function for setting the page size of the document
	 * 
	 * @param lowerX
	 * @param lowerY
	 */
	public void setPageSize(int lowerX, int lowerY) {
		Rectangle rect = new Rectangle(0, 0, lowerX, lowerY);
		doc.setPageSize(rect);
	}

	/**
	 * function for adding table without header row...
	 * 
	 * @param obj
	 */
	public void tableAdd(Object[][] obj) {
		int wid[] = {1, 10};

		try {
			int width = ((Object[])obj[0]).length;

			String[][] data = new String[obj.length][width];

			for(int i = 0; i < obj.length; i++) {
				for(int j = 0; j < width; j++) {
					try {
						data[i][j] = String.valueOf(obj[i][j]);
					} catch(Exception e1) {
						logger.info("error in converting data in tableAdd " + e1);
						data[i][j] = " ";
					}
				}
			}

			Table aTable = new Table(2);
			aTable.setBorder(Rectangle.NO_BORDER);
			aTable.setWidths(wid);
			Cell aTableCell = new Cell(" ");
			aTableCell.setBorder(Rectangle.NO_BORDER);
			aTable.addCell(aTableCell, new java.awt.Point(0, 0));

			Table table = new Table(width);
			table.setAutoFillEmptyCells(true);
			table.setBorder(Rectangle.NO_BORDER);
			table.setPadding(2.0f);
			Cell tableCell = new Cell();

			for(int i = 0; i < data.length; i++) {
				for(int j = 0; j < width; j++) {
					tableCell = new Cell(new Phrase(data[i][j], FontLab.getBodyFont()));
					tableCell.setBorder(Rectangle.NO_BORDER);
					tableCell.setWidth((String.valueOf(new Integer(20))));
					table.addCell(tableCell);
				}
			}

			aTable.insertTable(table, new java.awt.Point(0, 1));

			doc.add(aTable);
		} catch(Exception e) {
			logger.info("in report generator tableAdd " + e);
		}
	}

	/**
	 * Creates the table with no cell border with multiple rows
	 * 
	 * @param obj
	 */
	public void tableAddWithBorder(Object[][] obj) {
		int wid[] = {1, 10};

		try {

			int width = ((Object[])obj[0]).length;

			String[][] data = new String[obj.length][width];

			for(int i = 0; i < obj.length; i++) {
				for(int j = 0; j < width; j++) {
					try {
						data[i][j] = String.valueOf(obj[i][j]);
					} catch(Exception e1) {
						logger.info("error in converting data in tableAdd " + e1);
						data[i][j] = " ";
					}
				}
			}

			Table aTable = new Table(2);
			aTable.setWidth(100);
			aTable.setBorder(Rectangle.BOX);
			aTable.setBorderColor(FontLab.getBorderColor());
			aTable.setWidths(wid);
			Cell aTableCell = new Cell(" ");
			aTableCell.setBorder(Rectangle.NO_BORDER);
			aTable.addCell(aTableCell, new java.awt.Point(0, 0));

			Table table = new Table(width);
			table.setAutoFillEmptyCells(true);
			table.setBorder(Rectangle.NO_BORDER);
			table.setPadding(2.0f);
			Cell tableCell = new Cell();

			for(int i = 0; i < data.length; i++) {
				for(int j = 0; j < width; j++) {
					tableCell = new Cell(new Phrase(data[i][j], FontLab.getBodyFont()));
					tableCell.setBorder(Rectangle.NO_BORDER);
					tableCell.setWidth((String.valueOf(new Integer(120))));
					table.addCell(tableCell);
				}
			}

			aTable.insertTable(table, new java.awt.Point(0, 1));

			doc.add(aTable);
		} catch(Exception e) {
			logger.info("in report generator tableAdd " + e);
		}
	}

	/**
	 * Function for generating table depending on cell width ,alignment and data
	 * 
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 */
	public void tableBody(Object data[][], int cellwidth[], int alignment[]) {
		String[][] colData = new String[data.length][alignment.length];

		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < alignment.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}

		try {
			if(fileType.equals("Pdf")) {
				// Creating PDF file content.
				table = pdfg.tableBody(colData, cellwidth, alignment);
				doc.add(table);
			} else if(fileType.equals("Html")) {
				// Creating HTML file content.
				table = htmlg.tableBody(colData, cellwidth, alignment);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
				doc.add(table);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
			} else if(fileType.equals("Xls")) {
				try {
					logger.info("\n\n\n========================EXCELL SHEET================================");
					xlsg.tableBody(colData, cellwidth, alignment);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
				}
			} else {
				// Creating TEXT file content.
				table = txtg.tableBody(colData, cellwidth, alignment);
				doc.add(table);
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	/**
	 * Function for generating table depending on cell width ,alignment and data and accepts user defined font size.
	 * 
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 */
	public void tableBody(Object data[][], int cellwidth[], int alignment[], int fontSize) {
		String[][] colData = new String[data.length][alignment.length];

		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < alignment.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}

		try {
			if(fileType.equals("Pdf")) {
				// Creating PDF file content.
				table = pdfg.tableBody(colData, cellwidth, alignment, fontSize, 0);
				doc.add(table);
			} else if(fileType.equals("Html")) {
				// Creating HTML file content.
				table = htmlg.tableBody(colData, cellwidth, alignment);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
				doc.add(table);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
			} else if(fileType.equals("Xls")) {
				try {
					xlsg.tableBody(colData, cellwidth, alignment);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
				}
			} else {
				// Creating TEXT file content.
				table = txtg.tableBody(colData, cellwidth, alignment);
				doc.add(table);
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	/**
	 * Function for generating table depending on cell widht ,alignment and data while creating the object of ReportGenerator.
	 * 
	 * @param colNames
	 * @param data
	 * @param cellwidth
	 */
	public void tableBody(String colNames[], Object data[][], int cellwidth[]) {
		int[] alignment = new int[cellwidth.length];
		int[] colors = new int[cellwidth.length];
		for(int i = 0; i < cellwidth.length; i++) {
			alignment[i] = 0;
			colors[i] = 0;
		}
		tableBody(colNames, data, cellwidth, alignment);
	}

	/**
	 * to generate table with parameters & bgcolor like header
	 * 
	 * @param colNames
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 */
	public void tableBodyWithColor(Object colNames[][], Object data[][], int cellwidth[], int alignment[],Object[][] color) {
		String[][] colData = null;
		
		if(data!=null && data.length >0){
			colData = new String[data.length][data[0].length];
			for(int i = 0; i < data.length; i++) {
				for(int j = 0; j < data[0].length; j++) {
					try {
						colData[i][j] = checkNull(String.valueOf(data[i][j]));
					} catch(Exception ae) {
						logger.info("error in converting data " + ae);
						colData[i][j] = " ";
					}
				}
			}
		}
		
		if(fileType.equals("Xls")) {
			try {
				xlsg.tableBodyWithColor(colNames, colData, cellwidth, alignment, color);
			} catch(Exception e) {
				logger.info("EXCEPTIOn XLS WORKBOOK " + e);
			}
		} 
	}

	/**
	 * to generate table with proper parameters passed
	 * 
	 * @param colNames
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 * @param widthSize
	 */
	public void tableBody(String colNames[], Object data[][], int cellwidth[], int alignment[], int widthSize) {

		String[][] colData = new String[data.length][colNames.length];

		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < colNames.length; j++) {
				try {
					colData[i][j] = String.valueOf(data[i][j]);
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}

		try {
			if(fileType.equals("Pdf")) {
				// Creating PDF file content.
				table = pdfg.tableBody(colNames, colData, cellwidth, alignment, widthSize);
				doc.add(table);
			}
			// New added----------------
			else {
				// Creating TEXT file content.

				table = txtg.tableBody(colNames, colData, cellwidth, alignment);
				doc.add(table);
			}

		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	/**
	 * generate table with parameters shown below
	 * 
	 * @param colNames
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 * @param rg
	 * @param recordsPerPage
	 * @param colNoForTotal
	 * @param header
	 */
	public void tableBody(String colNames[], Object data[][], int cellwidth[], int alignment[], ReportGenerator rg, int recordsPerPage,
		int colNoForTotal, String header) {
		logger.info("--------------------In reportgenerator talbe body------------------------------------");
		String[][] colData = new String[data.length][colNames.length];
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < colNames.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}
		try {
			if(fileType.equals("Pdf")) {
				doc.add(pdfg.tableBody(colNames, colData, cellwidth, alignment));
			} else if(fileType.equals("Html")) {
				table = htmlg.tableBody(colNames, colData, cellwidth, alignment);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
				doc.add(table);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
			} else if(fileType.equals("Xls")) {
				try {

					xlsg.tableBody(colNames, data, cellwidth, alignment);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
				}
			} else {
				int count = 0;

				for(int k = 0; k < data.length; k++) {
					if(count == 0) {
						String dataStr[][] = new String[recordsPerPage][colNames.length];
						for(int i = 0; i < recordsPerPage; i++) {
							for(int j = 0; j < colNames.length; j++) {
								try {
									dataStr[i][j] = checkNull(String.valueOf(data[i][j]));
								} catch(Exception ae) {
									logger.info("error in converting data " + ae);
									colData[i][j] = " ";
								}
							}
						}
						// to calculate page wise total
						String totalByColumn[][] = new String[1][dataStr[0].length];
						String totalHeader[] = new String[colNames.length];
						totalHeader[0] = "";

						totalByColumn[0][1] = "PAGE TOTAL :-";
						totalHeader[1] = "";

						for(int i = colNoForTotal; i < dataStr[0].length; i++) {
							double total = 0;
							for(int j = 0; j < dataStr.length; j++) {
								total += Double.parseDouble(String.valueOf(dataStr[j][i]));
							}
							totalHeader[i] = "";
							totalByColumn[0][i] = String.valueOf(Math.round(total));
						}
						rg.addText(header, 0, 1, 0);
						table = txtg.tableBody(colNames, dataStr, cellwidth, alignment, rg);
						table.setAlignment(0);
						doc.add(table);

						table = txtg.tableBody(totalByColumn, cellwidth, alignment, rg);
						table.setAlignment(0);
						doc.add(table);
						rg.pageBreak();
					}
					if(count != 0 && count % recordsPerPage == 0) {
						if(count + recordsPerPage <= data.length) {
							String dataStr[][] = new String[recordsPerPage][colNames.length];
							int incr = 0;
							for(int i = count; i < count + recordsPerPage; i++) {
								for(int j = 0; j < colNames.length; j++) {
									try {
										dataStr[incr][j] = checkNull(String.valueOf(data[i][j]));
									} catch(Exception ae) {
										logger.info("error in converting data " + ae);
										colData[i][j] = " ";
									}
								}
								incr++;
							}
							// to calculate page wise total
							String totalByColumn[][] = new String[1][dataStr[0].length];
							String totalHeader[] = new String[colNames.length];
							totalHeader[0] = "";
							totalByColumn[0][1] = "PAGE TOTAL :-";
							totalHeader[1] = "";

							for(int i = colNoForTotal; i < dataStr[0].length; i++) {
								double total = 0;
								for(int j = 0; j < dataStr.length; j++) {
									total += Double.parseDouble(String.valueOf(dataStr[j][i]));
								}
								totalHeader[i] = "";
								totalByColumn[0][i] = String.valueOf(Math.round(total));
							}
							rg.addText(header, 0, 1, 0);
							table = txtg.tableBody(colNames, dataStr, cellwidth, alignment, rg);
							table.setAlignment(0);
							doc.add(table);
							table = txtg.tableBody(totalByColumn, cellwidth, alignment, rg);
							table.setAlignment(0);
							doc.add(table);
							rg.pageBreak();
						} else {
							String dataStr[][] = new String[data.length - count][colNames.length];
							int incr = 0;
							for(int i = count; i < data.length; i++) {
								for(int j = 0; j < colNames.length; j++) {
									try {
										dataStr[incr][j] = checkNull(String.valueOf(data[i][j]));
									} catch(Exception ae) {
										logger.info("error in converting data " + ae);
										colData[i][j] = " ";
									}
								}
								incr++;
							}
							// to calculate page wise total
							String totalByColumn[][] = new String[1][dataStr[0].length];
							String totalHeader[] = new String[colNames.length];
							totalHeader[0] = "";
							totalByColumn[0][1] = "PAGE TOTAL :-";
							totalHeader[1] = "";

							for(int i = colNoForTotal; i < dataStr[0].length; i++) {
								double total = 0;
								for(int j = 0; j < dataStr.length; j++) {
									total += Double.parseDouble(String.valueOf(dataStr[j][i]));
								}
								totalHeader[i] = "";
								totalByColumn[0][i] = String.valueOf(Math.round(total));
							}
							rg.addText(header, 0, 1, 0);
							table = txtg.tableBody(colNames, dataStr, cellwidth, alignment, rg);
							table.setAlignment(0);
							doc.add(table);
							table = txtg.tableBody(totalByColumn, cellwidth, alignment, rg);
							table.setAlignment(0);
							doc.add(table);
							// rg.pageBreak();

						}

					}
					count++;
				}

			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	/**
	 * generate table with columns ,data cell width with No GAP BETWEEN ROWS AND HEADER
	 * 
	 * @param colNames
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 * @param noGap
	 */
	public void tableBody(String colNames[], Object data[][], int cellwidth[], int alignment[], String noGap) {

		String[][] colData = new String[data.length][colNames.length];
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < colNames.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}
		try {
			if(fileType.equals("Pdf")) {
				doc.add(pdfg.tableBody(colNames, colData, cellwidth, alignment));
			} else if(fileType.equals("Html")) {
				table = htmlg.tableBody(colNames, colData, cellwidth, alignment);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
				doc.add(table);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
			} else if(fileType.equals("Xls")) {
				try {
					xlsg.tableBody(colNames, data, cellwidth, alignment, noGap);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
				}
			} else {
				table = txtg.tableBody(colNames, colData, cellwidth, alignment);
				doc.add(table);
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	public void tableBody1(Object data[][], int cellwidth[], int alignment[]) {

		String[][] colData = new String[data.length][alignment.length];

		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < alignment.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}

		try {
			if(fileType.equals("Pdf")) {
				// Creating PDF file content.
				table = pdfg.tableBody1(colData, cellwidth, alignment);
				doc.add(table);
			} else if(fileType.equals("Html")) {
				// Creating HTML file content.
				table = htmlg.tableBody(colData, cellwidth, alignment);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
				doc.add(table);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
			} else if(fileType.equals("Xls")) {
				try {
					logger.info("\n\n\n========================EXCELL SHEET================================");
					xlsg.tableBody(colData, cellwidth, alignment);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
				}
			} else {
				// Creating TEXT file content.
				table = txtg.tableBody(colData, cellwidth, alignment);
				doc.add(table);
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	public void tableBodyAnnex(Object data[][], int cellwidth[], int alignment[]) {

		String[][] colData = new String[data.length][alignment.length];

		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < alignment.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j])) + " ";
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}

		try {
			if(fileType.equals("Pdf")) {
				// Creating PDF file content.
				table = pdfg.tableBodyAnnex(colData, cellwidth, alignment);
				doc.add(table);
			} else if(fileType.equals("Html")) {
				// Creating HTML file content.
				table = htmlg.tableBody(colData, cellwidth, alignment);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
				doc.add(table);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
			} else if(fileType.equals("Xls")) {
				try {
					logger.info("\n\n\n========================EXCELL SHEET================================");
					xlsg.tableBody(colData, cellwidth, alignment);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
				}
			} else {
				// Creating TEXT file content.
				table = txtg.tableBody(colData, cellwidth, alignment);
				doc.add(table);
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	/**
	 * to generate table with parameters
	 * 
	 * @param colNames
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 */
	public void tableBodyAsText(String colNames[], Object data[][], int cellwidth[], int alignment[], int[] colsAsDouble) {
		String[][] colData = new String[data.length][colNames.length];
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < colNames.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}
		try {
			if(fileType.equals("Pdf")) {
				doc.add(pdfg.tableBody(colNames, colData, cellwidth, alignment));
			} else if(fileType.equals("Html")) {
				table = htmlg.tableBody(colNames, colData, cellwidth, alignment);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
				doc.add(table);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
			} else if(fileType.equals("Xls")) {
				try {
					logger.info("\n\n\n========================EXCELL SHEET================================");
					xlsg.tableBodyAsText(colNames, data, cellwidth, alignment, colsAsDouble);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
				}
			} else {
				table = txtg.tableBody(colNames, colData, cellwidth, alignment);
				doc.add(table);
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
			e.printStackTrace();
		}
	}

	public void tableBodyAsText(String colNames[][], Object data[][], int cellwidth[], int alignment[], int[] colsAsDouble) {
		String[][] colData = new String[data.length][data[0].length];
		
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}
		try {
			if(fileType.equalsIgnoreCase("Xls")) {
				try {
					xlsg.tableBodyAsText(colNames, colData, cellwidth, alignment, colsAsDouble);
				} catch(Exception e) {
					logger.info("Exception XLS WORKBOOK " + e);
					e.printStackTrace();
				}
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	/**
	 * Function for generating table depending on cell widht ,alignment and data table with bold font
	 * 
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 */
	public void tableBodyBold(Object data[][], int cellwidth[], int alignment[]) {
		// String[][] colData=new String[data.length][alignment.length];
		// for(int i=0;i<data.length;i++ ) {
		// for(int j=0;j<alignment.length;j++ ) {
		// try {
		// colData[i][j] = String.valueOf(data[i][j]);
		// } catch(Exception ae) {
		// logger.info("error in converting data "+ae);
		// colData[i][j]=" ";
		// }
		// }
		// }

		String[][] colData = scanData(data);
		try {
			if(fileType.equals("Pdf")) {
				// Creating PDF file content.
				doc.add(pdfg.tableBodyBold(colData, cellwidth, alignment));
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	public void tableBodyBold(String colNames[], Object data[][], int cellwidth[], int alignment[], int fontSize) {
		String[][] colData = new String[data.length][colNames.length];
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < colNames.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}
		try {
			if(fileType.equals("Pdf")) {
				doc.add(pdfg.tableBodyBold(colNames, colData, cellwidth, alignment, fontSize));
			} else if(fileType.equals("Html")) {
				table = htmlg.tableBody(colNames, colData, cellwidth, alignment);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
				doc.add(table);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
			} else if(fileType.equals("Xls")) {
				try {
					logger.info("\n\n\n========================EXCELL SHEET================================");
					xlsg.tableBody(colNames, data, cellwidth, alignment);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
				}
			} else {
				table = txtg.tableBody(colNames, colData, cellwidth, alignment);
				doc.add(table);
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}
	public void tableBodyForPaybill(String colNames[], Object data[][], int cellwidth[], int alignment[], int fontSize,Object[][]otherData) {
		String[][] colData = new String[data.length][colNames.length];
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < colNames.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}
		try {
				doc.add(pdfg.tableBodyForPaybill(colNames, colData, cellwidth, alignment, fontSize,otherData));
			} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	/**
	 * to gerate table with sum of values at left *
	 * 
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 * @param colors
	 */
	public void tableBodyLeft(Object data[][], int cellwidth[], int alignment[], int[] colors) {
		String[][] colData = scanData(data);
		try {
			if(fileType.equals("Pdf")) {
				doc.add(pdfg.tableBodyLeft(colData, cellwidth, alignment, colors));
			} else if(fileType.equals("Txt")) {
				// table=txtg.tableBodyLeft(colData,cellwidth,alignment);
				doc.add(table);
			}
		} catch(Exception e) {
			// logger.info("in report Generator table body" +e);
		}
	}

	/**
	 * Function for generating table depending on cell widht ,alignment and data table with no border
	 * 
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 */
	public void tableBodyNoBorder(Object data[][], int cellwidth[], int alignment[]) {
		String[][] colData = null;
		if(data != null && data.length > 0) {
			colData = scanData(data);
		}
		try {
			if(fileType.equals("Pdf")) {
				// Creating PDF file content.
				doc.add(pdfg.tableBodyNoBorder(colData, cellwidth, alignment));
			} else if(fileType.equals("Html")) {
				// Creating HTML file content.
				table = htmlg.tableBodyNoBorder(colData, cellwidth, alignment);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
				doc.add(table);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
			} else {
				// Creating TEXT file content.

				table = txtg.tableBodyNoBorder(colData, cellwidth, alignment);
				doc.add(table);
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	/**
	 * Function for generating table depending on cell widht ,alignment, fontSize and data table with no border
	 * 
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 * @param bold
	 * @param fontSize
	 */
	public void tableBodyNoBorder(Object data[][], int cellwidth[], int alignment[], int[] bold, int[] fontSize) {
		String[][] colData = scanData(data);
		try {
			if(fileType.equals("Pdf")) {
				// Creating PDF file content.
				doc.add(pdfg.tableBodyNoBorder(colData, cellwidth, alignment, bold, fontSize));
			} else if(fileType.equals("Html")) {
				// Creating HTML file content.
				table = htmlg.tableBodyNoBorder(colData, cellwidth, alignment);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
				doc.add(table);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
			} else {
				// Creating TEXT file content.

				table = txtg.tableBodyNoBorder(colData, cellwidth, alignment);
				doc.add(table);
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	public void tableBodyNoBorderPromo(Object data[][], int cellwidth[], int alignment[]) {
		// String[][] colData=new String[data.length][alignment.length];
		// for(int i=0;i<data.length;i++ ) {
		// for(int j=0;j<alignment.length;j++ ) {
		// try {
		// colData[i][j] = String.valueOf(data[i][j]);
		// } catch(Exception ae) {
		// logger.info("error in converting data "+ae);
		// colData[i][j]=" ";
		// }
		// }
		// }

		String[][] colData = scanData(data);
		try {
			if(fileType.equals("Pdf")) {
				// Creating PDF file content.
				doc.add(pdfg.tableBodyNoBorderPr(colData, cellwidth, alignment));

			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	public void tableBodyNoBorderPromoSmall(Object data[][], int cellwidth[], int alignment[]) {
		// String[][] colData=new String[data.length][alignment.length];
		// for(int i=0;i<data.length;i++ ) {
		// for(int j=0;j<alignment.length;j++ ) {
		// try {
		// colData[i][j] = String.valueOf(data[i][j]);
		// } catch(Exception ae) {
		// logger.info("error in converting data "+ae);
		// colData[i][j]=" ";
		// }
		// }
		// }

		String[][] colData = scanData(data);
		try {
			if(fileType.equals("Pdf")) {
				// Creating PDF file content.
				doc.add(pdfg.tableBodyNoBorderPrSmall(colData, cellwidth, alignment));

			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	/**
	 * to generate the table with no cell border
	 * 
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 * @param fonStyle
	 */
	public void tableBodyNoCellBorder(Object data[][], int cellwidth[], int alignment[], int fonStyle) {

		String[][] colData = new String[data.length][alignment.length];

		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < alignment.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}

		try {
			if(fileType.equals("Pdf")) {
				// Creating PDF file content.
				table = pdfg.tableBodyNoCellBorder(colData, cellwidth, alignment, fonStyle);
				doc.add(table);
			}

			if(fileType.equals("Xls")) {
				try {
					// 1 for bold 2 for normal
					xlsg.tableBodyNoCellBorder(colData, cellwidth, alignment, fonStyle);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
				}
			}

		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}

	}

	/**
	 * @param colNames
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 */
	public void tableBodyPayDown(String colNames[], Object data[][], int cellwidth[], int alignment[]) {

		String[][] colData = new String[data.length][colNames.length];
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < colNames.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}
		try {
			if(fileType.equals("Pdf")) {
				doc.add(pdfg.tableBody(colNames, colData, cellwidth, alignment));
			} else if(fileType.equals("Html")) {
				table = htmlg.tableBody(colNames, colData, cellwidth, alignment);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
				doc.add(table);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
			} else if(fileType.equals("Xls")) {
				try {
					logger.info("\n\n\n========================EXCELL SHEET================================");
					xlsg.tableBodyPayDown(colNames, data, cellwidth, alignment);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
				}
			} else {
				table = txtg.tableBody(colNames, colData, cellwidth, alignment);
				doc.add(table);
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	public void tableBodyPromo(String colNames[], Object data[][], int cellwidth[], int alignment[]) {

		String[][] colData = new String[data.length][colNames.length];
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < colNames.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}
		try {
			if(fileType.equals("Pdf")) {
				doc.add(pdfg.tableBodyPr(colNames, colData, cellwidth, alignment));
			} else if(fileType.equals("Html")) {
				table = htmlg.tableBody(colNames, colData, cellwidth, alignment);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
				doc.add(table);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
			} else if(fileType.equals("Xls")) {
				try {
					logger.info("\n\n\n========================EXCELL SHEET================================");
					xlsg.tableBody(colNames, data, cellwidth, alignment);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
				}
			} else {
				table = txtg.tableBody(colNames, colData, cellwidth, alignment);
				doc.add(table);
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	/* Used for formula columns */
	public void tableBodyWithFormaula(String colNames[], Object data[][], int cellwidth[], int alignment[], int formulaColumn) {

		String[][] colData = new String[data.length][colNames.length];
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < colNames.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}
		try {
			if(fileType.equals("Pdf")) {
				doc.add(pdfg.tableBody(colNames, colData, cellwidth, alignment));
			} else if(fileType.equals("Html")) {
				table = htmlg.tableBody(colNames, colData, cellwidth, alignment);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
				doc.add(table);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
			} else if(fileType.equals("Xls")) {
				try {
					logger.info("\n\n\n========================EXCELL SHEET================================");
					xlsg.tableBodyWithFormula(colNames, data, cellwidth, alignment, formulaColumn);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
				}
			} else {
				table = txtg.tableBody(colNames, colData, cellwidth, alignment);
				doc.add(table);
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	public void tableRowWithBG(String colNames[], int cellwidth[], int alignment[], int widthSize) {

		String[][] colData = new String[1][colNames.length];

		try {
			if(fileType.equals("Pdf")) {
				// Creating PDF file content.
				table = pdfg.tableBodyBG(colNames, cellwidth, alignment, widthSize);
				doc.add(table);
			}
			// New added----------------
			else {
				// Creating TEXT file content.

				table = txtg.tableBody(colNames, colData, cellwidth, alignment);
				doc.add(table);
			}

		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}

	public void writeFile(OutputStream out) {
		xlsg.writeFile(out);
	}

	/**
	 * to generate the xls table with columns ,alignment and data
	 * 
	 * @param colNames
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 */
	public void xlsTableBody(String colNames[], Object data[][], int cellwidth[], int alignment[]) {

		String[][] colData = new String[data.length][colNames.length];
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < colNames.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}
		try {
			/*
			 * if (fileType.equals("Pdf")) { doc .add(pdfg.tableBody(colNames, colData, cellwidth, alignment)); } else if (fileType.equals("Html")) {
			 * table = htmlg .tableBody(colNames, colData, cellwidth, alignment); hw.add("<" + HtmlTags.HORIZONTALRULE + ">"); doc.add(table);
			 * hw.add("<" + HtmlTags.HORIZONTALRULE + ">"); } else
			 */if(fileType.equals("Xls")) {
				try {
					logger.info("\n\n\n========================EXCELL SHEET================================");
					xlsg.xlsTableBody(colNames, data, cellwidth, alignment);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
				}
			} /*
				 * else { table = txtg.tableBody(colNames, colData, cellwidth, alignment); doc.add(table); }
				 */
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}
	public void xlsTableBodyForStatement(String colNames[], Object data[][], int cellwidth[], int alignment[], String[] format) {

		String[][] colData = new String[data.length][colNames.length];
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < colNames.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}
		try {
			/*
			 * if (fileType.equals("Pdf")) { doc .add(pdfg.tableBody(colNames, colData, cellwidth, alignment)); } else if (fileType.equals("Html")) {
			 * table = htmlg .tableBody(colNames, colData, cellwidth, alignment); hw.add("<" + HtmlTags.HORIZONTALRULE + ">"); doc.add(table);
			 * hw.add("<" + HtmlTags.HORIZONTALRULE + ">"); } else
			 */if(fileType.equals("Xls")) {
				try {
					logger.info("\n\n\n========================EXCELL SHEET================================");
					xlsg.xlsTableForStatement(colNames, data, cellwidth, alignment,format);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
					e.printStackTrace();
				}
			} /*
				 * else { table = txtg.tableBody(colNames, colData, cellwidth, alignment); doc.add(table); }
				 */
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}
	public void xlsTableBodyForStatement(String colNames[], Object data[][], int cellwidth[], int alignment[]) {

		String[][] colData = new String[data.length][colNames.length];
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < colNames.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}
		try {
			/*
			 * if (fileType.equals("Pdf")) { doc .add(pdfg.tableBody(colNames, colData, cellwidth, alignment)); } else if (fileType.equals("Html")) {
			 * table = htmlg .tableBody(colNames, colData, cellwidth, alignment); hw.add("<" + HtmlTags.HORIZONTALRULE + ">"); doc.add(table);
			 * hw.add("<" + HtmlTags.HORIZONTALRULE + ">"); } else
			 */if(fileType.equals("Xls")) {
				try {
					logger.info("\n\n\n========================EXCELL SHEET================================");
					xlsg.xlsTableForStatement(colNames, data, cellwidth, alignment);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
					e.printStackTrace();
				}
			} /*
				 * else { table = txtg.tableBody(colNames, colData, cellwidth, alignment); doc.add(table); }
				 */
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}
	
	public ReportGenerator(ModelBase model, String type, String name, String pagesize) {
		repname = name;
		fileName = name.replaceAll(" ", "");
		fileType = type;
		fName = fileName;
		// this.model=model;
		this.model = model;
		doc = new Document(PageSize.A3.rotate(), 50, 50, 50, 50);
		// doc.setMargins(1.0f, 1.0f,1.0f,1.0f);

		try {
			if(type.equals("Pdf")) {
				/*
				 * Creating a PDFGenerator object for use in other functions.
				 */
				bout = new java.io.ByteArrayOutputStream();
				pdfg = new PDFGenerator();
				PdfWriter writer = PdfWriter.getInstance(doc, bout);
				writer.setPageEvent(this);
				// doc.setFooter(genFooter());
			}// end of if
			else if(type.equals("Txt")) {
				/*
				 * Creating a TXTGenerator object for use in other functions.
				 */
				bout = new java.io.ByteArrayOutputStream();
				txtg = new TXTGenerator();
				rw = RtfWriter.getInstance(doc, bout);
				// doc.setFooter(genFooter());
				RtfHeaderFooters rtffooter = new RtfHeaderFooters();
				rtffooter.set(RtfHeaderFooters.ALL_PAGES, genFooter());
				rw.setFooter(rtffooter);
			}// end of else if
			else if(type.equals("Xls")) {
				bout = new java.io.ByteArrayOutputStream();
				logger.info("Report generator XLS");
				str = ".xls";
				xlsg = new XLSGenerate(fileName);
			}// end if else if
		} catch(Exception e) {
			// logger.info("report generator :" + e );
		}
	}
	
	/**
	 * to generate table with parameters
	 * 
	 * @param colNames
	 * @param data
	 * @param cellwidth
	 * @param alignment
	 */
	public void tableBody(String colNames[], Object data[][], int cellwidth[], int alignment[]) {
		String[][] colData = new String[data.length][colNames.length];
		
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < colNames.length; j++) {
				try {
					colData[i][j] = checkNull(String.valueOf(data[i][j]));
				} catch(Exception ae) {
					logger.info("error in converting data " + ae);
					colData[i][j] = " ";
				}
			}
		}
		try {
			if(fileType.equals("Pdf")) {
				doc.add(pdfg.tableBody(colNames, colData, cellwidth, alignment));
			} else if(fileType.equals("Html")) {
				table = htmlg.tableBody(colNames, colData, cellwidth, alignment);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
				doc.add(table);
				hw.add("<" + HtmlTags.HORIZONTALRULE + ">");
			} else if(fileType.equals("Xls")) {
				try {
					xlsg.tableBody(colNames, colData, cellwidth, alignment);
				} catch(Exception e) {
					logger.info("EXCEPTIOn XLS WORKBOOK " + e);
				}
			} else {
				table = txtg.tableBody(colNames, colData, cellwidth, alignment);
				doc.add(table);
			}
		} catch(Exception e) {
			logger.info("in report Generator table body" + e);
		}
	}
	
}