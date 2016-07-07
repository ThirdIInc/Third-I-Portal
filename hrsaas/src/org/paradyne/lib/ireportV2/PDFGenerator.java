package org.paradyne.lib.ireportV2;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Vector;


import org.paradyne.lib.Utility;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;

import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.lowagie.text.pdf.PdfCell;

/*
 * 	Coded  By 	:  Prakash Shetkar 	
 *	Date    	:  20-11-2009					
 *
 */

public class PDFGenerator extends PdfPageEventHelper {
	
	Document doc;
	Chapter chapter = null;

	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PDFGenerator.class); 
	NumberFormat formatter = new DecimalFormat("#0.00");
	public PdfGState gstate;
	public PdfTemplate tpl;
	public PdfTemplate tplHeader;
	public BaseFont helv;
	public Object headerObj;
	PdfPTable footer;
	Header header;
	boolean showPage;
	int pageNoHPosition;
	int pageNoVPosition;
	ReportDataSet rds;
	public PDFGenerator(Object headerObj,Object footerObj){
		this.headerObj=headerObj;
		//genHeader(headerObj);
	}
	public PDFGenerator(){
		//genHeader(headerObj);
	}
	public Document openDoc(ReportDataSet rdsDoc,ByteArrayOutputStream bout){
		this.rds=rdsDoc;
		if(rds.getPageSize().equalsIgnoreCase("A3")){
			
			if(rds.getPageOrientation().equalsIgnoreCase("landscape")){
			
				doc = new Document(PageSize.A3.rotate(), rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}else{

				doc = new Document(PageSize.A3, rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
			
			}
		}else if(rds.getPageSize().equalsIgnoreCase("TABLOID")){
			
			if(rds.getPageOrientation().equalsIgnoreCase("landscape")){
			
				doc = new Document(PageSize.TABLOID.rotate(), rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}else{

				doc = new Document(PageSize.TABLOID, rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
			
			}
		}else if(rds.getPageSize().equalsIgnoreCase("A4")){
			
			if(rds.getPageOrientation().equalsIgnoreCase("landscape")){
				
				doc = new Document(PageSize.A4.rotate(), rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}else{
				
				doc = new Document(PageSize.A4, rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}
		}else {
				
			if(rds.getPageOrientation().equalsIgnoreCase("landscape")){
				
				doc = new Document(PageSize.A3.rotate(), rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}else{
				
				doc = new Document(PageSize.A3, rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}
		}
		
		showPage = rds.isShowPageNo();
		pageNoHPosition = rds.getPageNoHPosition();
		pageNoVPosition = rds.getPageNoVPosition();
			try{
				
				PdfWriter writer = PdfWriter.getInstance(doc, bout);
				//writer.setUserunit(6f);
				writer.setPageEvent(this);
				
				
			}catch(Exception e){
				 logger.error("Error while creating writer for pdfgenerator :" + e );
				//e.printStackTrace();
			}
			
		return doc;
	}
public Document openDoc(ReportDataSet rdsDoc,String filename){
	this.rds=rdsDoc;
		if(rds.getPageSize().equalsIgnoreCase("A3")){
			
			if(rds.getPageOrientation().equalsIgnoreCase("landscape")){
			
				doc = new Document(PageSize.A3.rotate(), rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}else{

				doc = new Document(PageSize.A3, rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
			
			}
		}else if(rds.getPageSize().equalsIgnoreCase("TABLOID")){
			
			if(rds.getPageOrientation().equalsIgnoreCase("landscape")){
			
				doc = new Document(PageSize.TABLOID.rotate(), rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}else{

				doc = new Document(PageSize.TABLOID, rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
			
			}
		}else if(rds.getPageSize().equalsIgnoreCase("A4")){
			
			if(rds.getPageOrientation().equalsIgnoreCase("landscape")){
				
				doc = new Document(PageSize.A4.rotate(), rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}else{
				
				doc = new Document(PageSize.A4, rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}
		}else{
				
			if(rds.getPageOrientation().equalsIgnoreCase("landscape")){
				
				doc = new Document(PageSize.A3.rotate(), rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}else{
				
				doc = new Document(PageSize.A3, rds.getMarginLeft(), rds.getMarginRight(), rds.getMarginTop(), rds.getMarginBottom());
				
			}
		}
		showPage = rds.isShowPageNo();
		pageNoHPosition = rds.getPageNoHPosition();
		pageNoVPosition = rds.getPageNoVPosition();
			try{
				
				PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(filename));
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
			tpl = writer.getDirectContent().createTemplate(30, 16);
			//total = writer.getDirectContent().createTemplate(30, 16);
			tpl.setBoundingBox(new Rectangle(-20, -20, 100, 100));
			// initialization of the font
			helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
		} catch (Exception e) {
			logger.info("error in onOpenDocument"+e);
			//e.printStackTrace();
		}		
		 tpl = writer.getDirectContent().createTemplate(30, 16);
		
	}

	/**
	 * to generate the page number at footer
	 * @param writer
	 * @param document
	 */
	public void onCloseDocument(PdfWriter writer, Document document) {
		try {
			//tpl.setFontAndSize(helv, 8);
			if(showPage){
			/*ColumnText.showTextAligned(tpl, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1), new Font(Font.getFamily(rds.getTableBodyFontFace()), rds.getTableBodyFontSize(), 0,new BaseColor(0, 0, 0))),
                    2, 2, 0);*/
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
	public void onStartPage(PdfWriter writer, Document document)  {
		PdfPTable table=new PdfPTable(1);
		PdfPTable mainTable=new PdfPTable(1);
		try {
			table.setWidths(new int[] { 100 });
			mainTable.setWidths(new int[] { 100 });
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		table.setTotalWidth(document.getPageSize().getRight()-(document.leftMargin()+document.rightMargin()));
		//table.setWidthPercentage(100);
		mainTable.setTotalWidth(document.getPageSize().getRight()-(document.leftMargin()+document.rightMargin()));
        table.setLockedWidth(true);
        mainTable.setLockedWidth(true);
		PdfPTable logoTable=null;
		if(rds.isReportHeaderRequired()){
		if(rds.isLogoRequired()){
			PdfPCell logoCell;
			try {
				Image newLogo = Image.getInstance(rds
						.getImageRealPath()
						+ rds.getLogoPath());
				newLogo.setScaleToFitLineWhenOverflow(false);
				newLogo.scaleAbsoluteHeight(40f);
				logoCell = new PdfPCell(newLogo);
				logoCell.setBorder(0);
			} catch (Exception e) {
				logoCell=new PdfPCell(new Phrase(""));
				logoCell.setBorder(0);
			}
			PdfPCell nameCell=null;
			PdfPCell addressCell=null;
			PdfPTable nameTable=new PdfPTable(1);
			if(!rds.getCompanyName().equals("")){
				nameCell=new PdfPCell(new Phrase(rds.getCompanyName(),new Font(Font.getFamily(rds.getCompanyNameFontFace()), rds.getCompanyNameFontSize(),rds.getCompanyNameFontStyle(),new BaseColor(0, 0, 0))));
				nameCell.setBorder(Rectangle.NO_BORDER);
				nameCell.setHorizontalAlignment(2);
				nameCell.setBorder(0);
				nameTable.addCell(nameCell);
				if(!rds.getCompanyAddress().equals("")){
					addressCell=new PdfPCell(new Phrase(rds.getCompanyAddress(),new Font(Font.getFamily(rds.getCompanyAddressFontFace()), rds.getCompanyAddressFontSize(),rds.getCompanyAddressFontStyle(),new BaseColor(0, 0, 0))));
					addressCell.setBorder(Rectangle.NO_BORDER);
					addressCell.setHorizontalAlignment(2);
					addressCell.setBorder(0);
					nameTable.addCell(addressCell);
				}
			}else{
				nameCell=new PdfPCell(new Phrase(""));
				nameCell.setBorder(0);
				nameTable.addCell(nameCell);
			}
			//nameTable.
			//nameCell.setBorder(0);
			//PdfPCell addressCell=new PdfPCell(new Phrase(rds.getCompanyAddress()));
			PdfPCell nameAddressCell=new PdfPCell(nameTable);
			nameAddressCell.setBorder(Rectangle.NO_BORDER);
			nameAddressCell.setHorizontalAlignment(2);
			if(rds.getLogoAlign()=='C'){
				logoTable=new PdfPTable(1);
				logoTable.addCell(logoCell);
				
				logoTable.addCell(nameAddressCell);
				//logoTable.addCell(addressCell);
			}else if(rds.getLogoAlign()=='R'){
				logoTable=new PdfPTable(2);
				logoTable.addCell(nameAddressCell);
				logoTable.addCell(logoCell);
			}else{
				logoTable=new PdfPTable(2);
				logoTable.addCell(logoCell);
				logoTable.addCell(nameAddressCell);
			}
			PdfPCell logoTableCell=new PdfPCell(logoTable);
			logoTableCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(logoTableCell);
		}else{
			try{
			table.addCell(Image.getInstance(rds.getImageRealPath()+rds.getHeaderImagePath()));
			}catch (Exception e) {
				table.addCell("");
			}
		}
		
		PdfPCell repNameCell=new PdfPCell(new Phrase(rds.getReportName(), new Font(Font.getFamily(rds.getReportHeaderFontFace()), rds.getReportHeaderFontSize(),rds.getReportHeaderFontStyle(),new BaseColor(0, 0, 0))));
		repNameCell.setHorizontalAlignment(1);
		repNameCell.setBorder(Rectangle.TOP);
		table.addCell(repNameCell);
		//table=addLine(table, new BaseColor(218,118,18), "", Rectangle.TOP);
		//table=addLine(table, new BaseColor(218,118,18), "", Rectangle.TOP);
		
		/*if(!Utility.checkNull(rds.getFooterImagePath()).equals("")){
			table.addCell(Image.getInstance(rds.getImageRealPath()+rds.getFooterImagePath()));
		}else if(!Utility.checkNull(rds.getFooterText()).equals("")){
			table.addCell(new Phrase(rds.getFooterText(), new Font(Font.getFamily(rds.getTableBodyFontFace()), rds.getTableBodyFontSize(),rds.getTableBodyFontStyle(),new BaseColor(0, 0, 0))));
		}*/
		table.getDefaultCell().setBorder(0);
		PdfPCell innerCell=new PdfPCell(table);
		innerCell.setBorder(Rectangle.NO_BORDER);
		mainTable.addCell(innerCell);
		mainTable.writeSelectedRows(0, 10,document.leftMargin(), document.top(), writer.getDirectContent());
		PdfPTable firstTable=new PdfPTable(1);
		PdfPCell firstCell=new PdfPCell(new Phrase("", new Font(Font.getFamily(rds.getReportHeaderFontFace()), rds.getReportHeaderFontSize(),rds.getReportHeaderFontStyle(),new BaseColor(0, 0, 0))));
		firstCell.setFixedHeight(mainTable.getTotalHeight()+10);
		firstCell.setBorder(Rectangle.NO_BORDER);
		firstTable.addCell(firstCell);
		try {
			document.add(firstTable);
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
	}
	public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(2);
       // PdfPTable footerTable = new PdfPTable(1);
        try {
        	
        	 table.setWidths(new int[]{80,20});
        	 table.setTotalWidth(document.getPageSize().getRight()-(document.leftMargin()+document.rightMargin()));
             table.setLockedWidth(true);
            // table.getDefaultCell().setFixedHeight(20);
             table=addLine(table, new BaseColor(180,180,180), "", Rectangle.TOP);
             table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
             if(!Utility.checkNull(rds.getGeneratedByText()).equals("")){
            	 PdfPCell generatedCell=new PdfPCell(new Phrase(rds.getGeneratedByText(), new Font(Font
							.getFamily(rds.getTableBodyFontFace()), rds
							.getTableBodyFontSize(), rds
							.getTableBodyFontStyle(), new BaseColor(0, 0, 0))));
            	 generatedCell.setColspan(2);
            	 generatedCell.setBorder(Rectangle.NO_BORDER);
            	 table.addCell(generatedCell);
             }
             //table.addCell("");
             try {
				if (!Utility.checkNull(rds.getFooterImagePath()).equals("")) {
					table.addCell(Image.getInstance(rds.getImageRealPath()
							+ rds.getFooterImagePath()));
				} else if (!Utility.checkNull(rds.getFooterText()).equals("")) {
					table.addCell(new Phrase(rds.getFooterText(), new Font(Font
							.getFamily(rds.getTableBodyFontFace()), rds
							.getTableBodyFontSize(), rds
							.getTableBodyFontStyle(), new BaseColor(0, 0, 0))));
				}else{
					table.addCell("");
				}
			} catch (Exception e) {
				table.addCell("");
			}
		//	table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
             table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            if (showPage) {
				PdfPCell pageCell = new PdfPCell(new Phrase(String.format(
						"Page - %d ", writer.getPageNumber()), new Font(Font
						.getFamily(rds.getTableBodyFontFace()), rds
						.getTableBodyFontSize(), 0, new BaseColor(0, 0, 0))));
				pageCell.setBorder(0);
				pageCell.setHorizontalAlignment(2);
				table.addCell(pageCell);
				table.getDefaultCell().setBorder(0);
			}  else{
				table.addCell("");
			}
            table.writeSelectedRows(0, 500,document.leftMargin(), document.bottom(), writer.getDirectContent());
        	
        }
        catch(DocumentException de) {
            throw new ExceptionConverter(de);
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
				
					
					int[] alignment = tds.getCellAlignment();// alignment array
					int[] width = tds.getCellWidth();// width
					boolean [] bcellWrap = tds.getCellNoWrap(); //cell noWrap
					boolean [] headerWrap = tds.getHeaderNoWrap(); //header noWrap
					BaseColor clrBody = tds.getBodyBGColor();// background color for cell
					BaseColor clrHeader = tds.getHeaderBGColor();// background color for cell
					bodyFont = tds.getBodyFontDetails();// font 4 body of the table with
					// font,size style and color
					String headers[] = tds.getHeader();// table headers
					String headerData[][] = tds.getHeaderData();// table headers
					Boolean border = tds.getBorder();// border yes no
					int borderDetails = tds.getBorderDetail();// border 
					int bodyFontStyle =rds.getTableBodyFontStyle();
					int bodyFontStyleArray[] =tds.getBodyFontStyleArray();
					int headerColSpan[] =tds.getHeaderColSpan();
					float bodyFontSize =rds.getTableBodyFontSize();
					int sumFontStyle =rds.getTableBodyFontStyle();
					float sumFontSize =rds.getTableBodyFontSize();
					if(tds.getBodyFontStyle()!=-1){
						bodyFontStyle=tds.getBodyFontStyle();
					}
					if(tds.getBodyFontSize()!=-1){
						bodyFontSize=tds.getBodyFontSize();
					}
					if(tds.getSumFontStyle()!=-1){
						sumFontStyle=tds.getSumFontStyle();
					}
					if(tds.getSumFontSize()!=-1){
						sumFontSize=tds.getSumFontSize();
					}
					int headerBorderDetails = tds.getHeaderBorderDetail();// header border 
					Object[] columColorData = tds.getColumnColor();// if some colum required special color
					// column no which is to be colored
					int colNo = Integer.parseInt(String.valueOf(columColorData[0]));
					BaseColor colBackColor = (BaseColor) columColorData[1];// background color
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
					
					
	
					int[] rowsSum = tds.getRowSum();
					int[] rowsAvg = tds.getRowAvg();
					
					if(rowsSum != null && rowsAvg != null){
						tempOne = 1;
						tempTwo = 2;
					}
						
					double rowSumOut = 0.0;
					
					double rowAvgOut = 0.0;
					int tableLength=alignment.length;
					if (headerColSpan != null){
						tableLength=0;
						for (int i = 0; i < headerColSpan.length; i++) {
							tableLength+=headerColSpan[i];
						}
					}
					bodyTable = new PdfPTable(tableLength);
					// adjustment of table width
					/*if (headerColSpan != null){
						//bodyTable.setTotalWidth(www);
						bodyTable.setWidthPercentage(100.0f);
						//bodyTable.setWidths(width);
					}
					else*/ 
					if (!(width == null)) {
						float[] www = new float[width.length];
						for (int i = 0; i < www.length; i++) {
							www[i] = (float) width[i];
						}
						bodyTable.setTotalWidth(www);
						bodyTable.setWidthPercentage(100.0f);
						bodyTable.setWidths(width);
					}
					
					/*if (!(width == null)) {
						float perc=0;
						float[] www = new float[width.length];
						for (int i = 0; i < www.length; i++) {
							www[i] = (float) width[i];
							perc += (float) width[i];
						}
						if(perc>100)
							perc=100;
						bodyTable.setTotalWidth(www);
						bodyTable.setWidthPercentage(perc);
						bodyTable.setWidths(width);
					}*/
					// cell created.
					PdfPCell bcell = null;
					// table header or column names
					if (!(headers == null)) {
						if(tds.isHeaderLines()){
							bodyTable=addLine(bodyTable,new BaseColor(93,93,93),"",Rectangle.BOTTOM);
						}
						for (int i = 0; i < headers.length; i++) {
							
							// font mentioned or default fonnt for the table headings
							/*if ((headerFont.getFamily()!=null))
								bcell = new PdfPCell(new Phrase(headers[i], headerFont));
							else*/
							/*if(i==-1){
							PdfPTable subTable=new PdfPTable(2);
								bcell = new PdfPCell(new Phrase("Amount of Contribution payable", new Font(Font.getFamily(rds.getTableHeaderFontFace()), rds.getTableHeaderFontSize(), rds.getTableHeaderFontStyle(),new BaseColor(0, 0, 0))));
								bcell.setColspan(2);
								subTable.addCell(bcell);
								bcell = new PdfPCell(new Phrase("Worker's Share", new Font(Font.getFamily(rds.getTableHeaderFontFace()), rds.getTableHeaderFontSize(), rds.getTableHeaderFontStyle(),new BaseColor(0, 0, 0))));
								subTable.addCell(bcell);
								bcell = new PdfPCell(new Phrase("Employer's Share", new Font(Font.getFamily(rds.getTableHeaderFontFace()), rds.getTableHeaderFontSize(), rds.getTableHeaderFontStyle(),new BaseColor(0, 0, 0))));
								subTable.addCell(bcell);
								bcell=new PdfPCell(subTable);
							}else{
								bcell = new PdfPCell(new Phrase(headers[i], new Font(Font.getFamily(rds.getTableHeaderFontFace()), rds.getTableHeaderFontSize(), rds.getTableHeaderFontStyle(),new BaseColor(0, 0, 0))));
							}*/
							bcell = new PdfPCell(new Phrase(headers[i], new Font(Font.getFamily(rds.getTableHeaderFontFace()), rds.getTableHeaderFontSize(), rds.getTableHeaderFontStyle(),new BaseColor(0, 0, 0))));
							// default alignment for column is center
							bcell.setHorizontalAlignment(alignment[i]);
							/*if(tds.getHeaderCellpadding() > 0)
								bcell.setPadding(tds.getHeaderCellpadding());*/
													
							// color for the header cell
							if (clrHeader != null)
								/*bcell.setBackgroundColor(new BaseColor(255, 255, 255));
							else*/
								bcell.setBackgroundColor(clrHeader);
							else{
								bcell.setBackgroundColor(new BaseColor(180, 180, 180));
							}
							
							if (headerColSpan != null)
								bcell.setColspan(headerColSpan[i]);
							
							
							// border required or not
							/*if (!border)
								bcell.setBorder(Rectangle.NO_BORDER);*/
	
							//bcell.setBorder(getBorder(headerBorderDetails));
							
							if(!tds.isHeaderLines()){
								bcell.setBorder(getBorder(headerBorderDetails));
							}else{
								bcell.setBorder(Rectangle.NO_BORDER);
							}
							bcell.setPaddingLeft(2f);
							bcell.setPaddingBottom(3);
							if(headerWrap!=null)
								bcell.setNoWrap(headerWrap[i]);
							else{
								bcell.setNoWrap(false);
							}
							bodyTable.addCell(bcell);
							
						}
						if(tds.isHeaderLines()){
							bodyTable=addLine(bodyTable,new BaseColor(0,0,0),"",Rectangle.TOP);
						}
					}
					
					// table header or column names
					if (!(headerData == null)) {
						if(tds.isHeaderLines()){
							bodyTable=addLine(bodyTable,new BaseColor(93,93,93),"",Rectangle.BOTTOM);
						}
						for (int i = 0; i < headerData.length; i++) {
							for (int j = 0; j < headerData[0].length; j++) {
							// font mentioned or default fonnt for the table headings
							/*if ((headerFont.getFamily()!=null))
								bcell = new PdfPCell(new Phrase(headers[i], headerFont));
							else*/
							/*if(i==-1){
							PdfPTable subTable=new PdfPTable(2);
								bcell = new PdfPCell(new Phrase("Amount of Contribution payable", new Font(Font.getFamily(rds.getTableHeaderFontFace()), rds.getTableHeaderFontSize(), rds.getTableHeaderFontStyle(),new BaseColor(0, 0, 0))));
								bcell.setColspan(2);
								subTable.addCell(bcell);
								bcell = new PdfPCell(new Phrase("Worker's Share", new Font(Font.getFamily(rds.getTableHeaderFontFace()), rds.getTableHeaderFontSize(), rds.getTableHeaderFontStyle(),new BaseColor(0, 0, 0))));
								subTable.addCell(bcell);
								bcell = new PdfPCell(new Phrase("Employer's Share", new Font(Font.getFamily(rds.getTableHeaderFontFace()), rds.getTableHeaderFontSize(), rds.getTableHeaderFontStyle(),new BaseColor(0, 0, 0))));
								subTable.addCell(bcell);
								bcell=new PdfPCell(subTable);
							}else{
								bcell = new PdfPCell(new Phrase(headers[i], new Font(Font.getFamily(rds.getTableHeaderFontFace()), rds.getTableHeaderFontSize(), rds.getTableHeaderFontStyle(),new BaseColor(0, 0, 0))));
							}*/
							bcell = new PdfPCell(new Phrase(headerData[i][j], new Font(Font.getFamily(rds.getTableHeaderFontFace()), rds.getTableHeaderFontSize(), rds.getTableHeaderFontStyle(),new BaseColor(0, 0, 0))));
							// default alignment for column is center
							bcell.setHorizontalAlignment(alignment[j]);
							/*if(tds.getHeaderCellpadding() > 0)
								bcell.setPadding(tds.getHeaderCellpadding());*/
													
							// color for the header cell
							if (clrHeader != null)
								/*bcell.setBackgroundColor(new BaseColor(255, 255, 255));
							else*/
								bcell.setBackgroundColor(clrHeader);
							else{
								bcell.setBackgroundColor(new BaseColor(180, 180, 180));
							}
							
							if (headerColSpan != null)
								bcell.setColspan(headerColSpan[i]);
							
							
							// border required or not
							/*if (!border)
								bcell.setBorder(Rectangle.NO_BORDER);*/
	
							//bcell.setBorder(getBorder(headerBorderDetails));
							
							if(!tds.isHeaderLines()){
								bcell.setBorder(getBorder(headerBorderDetails));
							}else{
								bcell.setBorder(Rectangle.NO_BORDER);
							}
							bcell.setPaddingLeft(2f);
							bcell.setPaddingBottom(3);
							if(headerWrap!=null)
								bcell.setNoWrap(headerWrap[i]);
							else{
								bcell.setNoWrap(false);
							}
							bodyTable.addCell(bcell);
							
						}
						
					}
						if(tds.isHeaderLines()){
							bodyTable=addLine(bodyTable,new BaseColor(0,0,0),"",Rectangle.TOP);
						}
					}
					// The table containing the data for the report required.
					if(data != null && data.length > 0){
						int dataLength = data.length;
					for (int i = 0; i < dataLength; i++) {
						//logger.info("bodyTable.size=="+bodyTable.size());
						if(tds.isBorderLines()){
							bodyTable=addLine(bodyTable,new BaseColor(90,90,90),"",Rectangle.TOP);
						}
						//if(bodyTable.getTableEvent().equals(""))
						for (int j = 0; j < alignment.length; j++) {
							
							// adding the cell to bodytable
							if (!(rowsAvg == null) && j == alignment.length - tempOne) {
								
								
									bcell = new PdfPCell(new Phrase(String.valueOf(Utility.twoDecimals(rowAvgOut / rowsAvg.length)), new Font(Font.getFamily(rds.getTableBodyFontFace()),rds.getTableBodyFontSize(), 1, new BaseColor(0, 0, 0))));
								
								bcell.setHorizontalAlignment(alignment[j]);
								bcell.setBorder(0);
								
	
							}else if (!(rowsSum == null) && j == alignment.length - tempTwo) {
								
								/*if ((sumFont.getFamily()!=null))
									bcell = new PdfPCell(new Phrase(String.valueOf(rowSumOut),sumFont));
								else*/
									bcell = new PdfPCell(new Phrase(String.valueOf(rowSumOut), new Font(Font.getFamily(rds.getTableBodyFontFace()),rds.getTableBodyFontSize(), 1, new BaseColor(0, 0, 0))));
								
								bcell.setHorizontalAlignment(alignment[j]);
								bcell.setBorder(0);
	
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
									if ((bodyFont.getFamily()!=null)) {
										// font mentioned for table data else take
										// default font
										if(data[i][j] instanceof com.itextpdf.text.Image){
											bcell = new PdfPCell((Image)data[i][j]);
										} else {
											bcell = new PdfPCell(new Phrase(checkNull(String.valueOf(data[i][j])), bodyFont));
										}
										
									} else {
										// logger.info("font not found");
										try{
											int fontStyle=bodyFontStyle;
											
											if(bodyFontStyleArray!=null && bodyFontStyleArray.length>0){
												fontStyle =bodyFontStyleArray[j];
											}
										if(data[i][j] instanceof com.itextpdf.text.Image){
											bcell = new PdfPCell((Image)data[i][j]);
										} else {
											bcell = new PdfPCell(new Phrase(checkNull(String.valueOf(data[i][j])), new Font(Font.getFamily(rds.getTableBodyFontFace()), bodyFontSize, fontStyle,new BaseColor(0, 0, 0))));
										}
										}catch(Exception e){
											logger.info("error in setting image "+e);
										}
									}									
									 bcell.setBackgroundColor(clrBody);
									 bcell.setPaddingLeft(2);
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
								/*if (!border)
									bcell.setBorder(Rectangle.NO_BORDER);*/
								if(tds.isTotalCol()){
								if (!String.valueOf(data[i][j]).equals("")){
									bcell.setBorder(getBorder(borderDetails));
									bcell.setBorderColor(new BaseColor(93,93,93));
								}else{
									bcell.setBorder(Rectangle.NO_BORDER);
								}
								}else{
									bcell.setBorder(getBorder(borderDetails));
								}
								//bcell.setCellEvent(cellEvent)
								
							}
							if(bcellWrap!=null)
								bcell.setNoWrap(bcellWrap[j]);
							else{
								bcell.setNoWrap(false);
							}
							bcell.setPaddingLeft(2f);
							bodyTable.addCell(bcell);
						}
						
						
						rowSumOut = 0.00;
						rowAvgOut = 0.00;
						if (tds.isHeaderTable) 
							bodyTable.setHeaderRows(1);
							//bodyTable.setSplitLate(false);
							
						if(tds.isBorderLines()){
								bodyTable=addLine(bodyTable,new BaseColor(0,0,0),"",Rectangle.TOP);
						}	
					}
					//bodyTable.setSplitLate(true);
					//bodyTable.setSplitRows(true);
					if (!(colsSum == null)) {
						int k = 0;
						for (int i = 0; i < 1; i++) {
							bodyTable=addLine(bodyTable,new BaseColor(90,90,90),"",Rectangle.TOP);
							for (int j = 0; j < alignment.length; j++) {
								// logger.info("value of k "+sumOut.length);
								if (k < colsSum.length) {
									// logger.info("hi for chk k ="+k);
									if (j == colsSum[k]) {
										// logger.info("logger for j="+j);
										/*if ((sumFont.getFamily()!=null))
											bcell = new PdfPCell(new Phrase(String.valueOf(Utility.twoDecimals(sumOut[k])),sumFont));
										else{*/
											bcell = new PdfPCell(new Phrase(String.valueOf(Utility.twoDecimals(formatter.format(sumOut[k]))),
															new Font(Font.getFamily(rds.getTableBodyFontFace()), rds.getTableBodyFontSize(),1,new BaseColor(0, 0, 0))));
											//}
										k++;
									} else{
										String colValue="";
										if(j==colsSum[0]-1)
											colValue="Total";
										bcell = new PdfPCell(new Phrase(colValue, new Font(Font.getFamily(rds.getTableBodyFontFace()), rds.getTableBodyFontSize(),1,new BaseColor(0, 0, 0))));
										//bcell.setBorder(getBorder(borderDetails));
									}
								} else
									bcell = new PdfPCell(new Phrase(String.valueOf(""),new Font(Font.getFamily(rds.getTableBodyFontFace()), rds.getTableBodyFontSize(),1,new BaseColor(0, 0, 0))));
	
								bcell.setHorizontalAlignment(alignment[j]);
								//bcell.setBorder(getBorder(borderDetails));
								
								if(tds.getBodyCellpadding() > 0)
									bcell.setPadding(tds.getBodyCellpadding());
								bcell.setBorder(Rectangle.NO_BORDER);
								bodyTable.addCell(bcell);
								
							}
							bodyTable=addLine(bodyTable,new BaseColor(0,0,0),"",Rectangle.TOP);
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
										/*if ((sumFont.getFamily()!=null))
											bcell = new PdfPCell(new Phrase(String.valueOf(Utility.twoDecimals(avgOut[k]/colsAvg.length)),sumFont));
										else{*/
											bcell = new PdfPCell(new Phrase(String.valueOf(Utility.twoDecimals(avgOut[k]/colsAvg.length)),
															new Font(Font.getFamily(rds.getTableBodyFontFace()), rds.getTableBodyFontSize(),rds.getTableBodyFontStyle(),new BaseColor(0, 0, 0))));
											//}
										k++;
									} else
										bcell = new PdfPCell(new Phrase(String.valueOf(""), new Font(Font.getFamily(rds.getTableBodyFontFace()), rds.getTableBodyFontSize(),rds.getTableBodyFontStyle(), new BaseColor(0,0, 0))));
								} else
									bcell = new PdfPCell(new Phrase(String.valueOf(""),new Font(Font.getFamily(rds.getTableBodyFontFace()), rds.getTableBodyFontSize(),rds.getTableBodyFontStyle(),new BaseColor(0, 0, 0))));
	
								bcell.setHorizontalAlignment(alignment[j]);
								bcell.setBorder(0);
								if(tds.getBodyCellpadding() > 0)
									bcell.setPadding(tds.getBodyCellpadding());
								bodyTable.addCell(bcell);
							}
						}
					}
					//bodyTable.setSplitLate(tds.isSplitLate());
					//bodyTable.setSplitLate(true);
				}/*else
					bodyTable = new PdfPTable(1);*/
				
			} catch (Exception e) {
				logger.error("error in createtable"+e);
				e.printStackTrace();
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
			/*if(tds.getLineBelow() > 0) {
				
				PdfPTable tempTable = new PdfPTable(1);
				tempTable.setWidthPercentage(100.0f);
				PdfPCell bcell = new PdfPCell(new Phrase("                                "));
				bcell.setBorder(Rectangle.BOTTOM);
				for(int i=0; i<tds.getBlankRowsBelow(); i++)
					tempTable.addCell(bcell);
				
				bodyTable = joinTable(tempTable,bodyTable, false);
				
			}
			if(tds.getLineAbove() > 0) {
				
				PdfPTable tempTable = new PdfPTable(1);
				tempTable.setWidthPercentage(100.0f);
				PdfPCell bcell = new PdfPCell(new Phrase("                                "));
				bcell.setBorder(Rectangle.BOTTOM);
				for(int i=0; i<tds.getBlankRowsBelow(); i++)
					tempTable.addCell(bcell);
				
				bodyTable = joinTable( bodyTable,tempTable, false);
				
			}*/
			
			return bodyTable;
		}
		private int getBorder(int borderDetails) {
			switch (borderDetails) {
			case 1:
				return Rectangle.BOTTOM;
				
			case 2:
				return Rectangle.TOP;
				
			case 3:
				return Rectangle.BOX;
				

			case 0:
				return Rectangle.NO_BORDER;
				
			case -1:
				return Rectangle.NO_BORDER;
			default :
				break;
			}
		return 0;
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
				//ptable.setHeaderRows(1);
				//ptable.setFooterRows(1);
				//ptable.setSplitLate(false);
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
		
		public void process(HashMap<String,Object> map,Document doc,boolean splitLate){
			try {
				boolean isLast = false;
				PdfPTable table = new PdfPTable(1);
				table.setWidthPercentage(100.0f);
				table.getDefaultCell().setBorder(0);
				PdfPTable logoTable=null;
				/*if(rds.isReportHeaderRequired()){
				if(rds.isLogoRequired()){
					PdfPCell logoCell;
					try {
						Image newLogo = Image.getInstance(rds
								.getImageRealPath()
								+ rds.getLogoPath());
						newLogo.setScaleToFitLineWhenOverflow(false);
						newLogo.scaleAbsolute(150f, 40f);
						logoCell = new PdfPCell(newLogo);
						logoCell.setBorder(0);
					} catch (Exception e) {
						logoCell=new PdfPCell(new Phrase(""));
						logoCell.setBorder(0);
					}
					PdfPCell nameCell=null;
					PdfPCell addressCell=null;
					PdfPTable nameTable=new PdfPTable(1);
					if(!rds.getCompanyName().equals("")){
						nameCell=new PdfPCell(new Phrase(rds.getCompanyName(),new Font(Font.getFamily(rds.getCompanyNameFontFace()), rds.getCompanyNameFontSize(),rds.getCompanyNameFontStyle(),new BaseColor(0, 0, 0))));
						nameCell.setBorder(Rectangle.NO_BORDER);
						nameCell.setHorizontalAlignment(2);
						nameCell.setBorder(0);
						nameTable.addCell(nameCell);
						if(!rds.getCompanyAddress().equals("")){
							addressCell=new PdfPCell(new Phrase(rds.getCompanyAddress(),new Font(Font.getFamily(rds.getCompanyAddressFontFace()), rds.getCompanyAddressFontSize(),rds.getCompanyAddressFontStyle(),new BaseColor(0, 0, 0))));
							addressCell.setBorder(Rectangle.NO_BORDER);
							addressCell.setHorizontalAlignment(2);
							addressCell.setBorder(0);
							nameTable.addCell(addressCell);
						}
					}else{
						nameCell=new PdfPCell(new Phrase(""));
						nameCell.setBorder(0);
						nameTable.addCell(nameCell);
					}
					//nameTable.
					//nameCell.setBorder(0);
					//PdfPCell addressCell=new PdfPCell(new Phrase(rds.getCompanyAddress()));
					PdfPCell nameAddressCell=new PdfPCell(nameTable);
					nameAddressCell.setBorder(Rectangle.NO_BORDER);
					nameAddressCell.setHorizontalAlignment(2);
					if(rds.getLogoAlign()=='C'){
						logoTable=new PdfPTable(1);
						logoTable.addCell(logoCell);
						
						logoTable.addCell(nameAddressCell);
						//logoTable.addCell(addressCell);
					}else if(rds.getLogoAlign()=='R'){
						logoTable=new PdfPTable(2);
						logoTable.addCell(nameAddressCell);
						logoTable.addCell(logoCell);
					}else{
						logoTable=new PdfPTable(2);
						logoTable.addCell(logoCell);
						logoTable.addCell(nameAddressCell);
					}
					table.addCell(logoTable);
				}else{
					try{
					table.addCell(Image.getInstance(rds.getImageRealPath()+rds.getHeaderImagePath()));
					}catch (Exception e) {
						table.addCell("");
					}
				}
				
				PdfPCell repNameCell=new PdfPCell(new Phrase(rds.getReportName(), new Font(Font.getFamily(rds.getReportHeaderFontFace()), rds.getReportHeaderFontSize(),rds.getReportHeaderFontStyle(),new BaseColor(0, 0, 0))));
				repNameCell.setHorizontalAlignment(1);
				repNameCell.setBorder(Rectangle.TOP);
				table.addCell(repNameCell);
				//table=addLine(table, new BaseColor(218,118,18), "", Rectangle.TOP);
				table=addLine(table, new BaseColor(218,118,18), "", Rectangle.TOP);
				
				if(!Utility.checkNull(rds.getFooterImagePath()).equals("")){
					table.addCell(Image.getInstance(rds.getImageRealPath()+rds.getFooterImagePath()));
				}else if(!Utility.checkNull(rds.getFooterText()).equals("")){
					table.addCell(new Phrase(rds.getFooterText(), new Font(Font.getFamily(rds.getTableBodyFontFace()), rds.getTableBodyFontSize(),rds.getTableBodyFontStyle(),new BaseColor(0, 0, 0))));
				}
				table.getDefaultCell().setBorder(0);
				//table.addCell("Page No.");
				if(!rds.getFooterImagePath().equals("")|| !Utility.checkNull(rds.getFooterText()).equals("")){
				table.setHeaderRows(5);
		        // One of them is a footer
		        table.setFooterRows(2);
			}else{
				//table.setHeaderRows(3);
		        // One of them is a footer
		        //table.setFooterRows(1);
				}else{
					
				}*/
			/*}*/
				try {
					if(!doc.isOpen())
						doc.open();
				} catch(Exception de) {
					logger.error("error in opening document" + de);
				}
				
		        table.setSplitLate(splitLate);
				for(int i=0;i<map.size();i++){
					isLast = false;
					Object obj = map.get(String.valueOf(i));
					
					if(obj != null){
						
						if(obj instanceof TableDataSet){
							PdfPCell cell = generateCell((TableDataSet)obj);
							cell.setBorder(Rectangle.NO_BORDER);
							cell.setPadding(0f);
							//cell.setBackgroundColor(new BaseColor(125,125,125));
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
						}else if(obj instanceof Vector){
							
							PdfPCell cell = new PdfPCell();
							PdfPTable NewTable = new PdfPTable(1);
							cell.addElement(NewTable);
							Vector lineVector=(Vector)obj;
							for (int j = 0; j < lineVector.size(); j++) {
								Object tempObj = lineVector.get(j);
								if(tempObj instanceof BaseColor && tempObj!=null){
									cell.setBorderColor((BaseColor)tempObj);
								}
								else if(tempObj instanceof Integer && tempObj!=null){
									cell.setBorder((Integer)tempObj);
								}
							}
							//cell.setBorder(Rectangle.BOTTOM);
							cell.setPadding(0f);
							table.addCell(cell);					
					   }
						
					}
				}
				if(!isLast)
					logger.info("bodyTable size==="+table.size());
					doc.add(table);
				
			} catch (DocumentException e) {
				logger.error("error in process"+e);
				//e.printStackTrace();
			}catch(Exception e1){
				logger.error("error in process"+e1);
				e1.printStackTrace();
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
				headerTable.setHeaderRows(2);
				header = new Header("Header","Header");
				//header.(Rectangle.NO_BORDER);
								
			} catch (Exception e) {
				logger.error("error in genHeader"+e);
				//e.printStackTrace();
			}
			try {
				doc.add(headerTable);
				/*try {
					if(!doc.isOpen())
						doc.open();
				} catch(Exception de) {
					logger.error("error in opening document" + de);
				}*/
			} catch (Exception e) {
				// TODO: handle exception
			}
			
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
		public PdfPTable addLine(PdfPTable pdfPTable,BaseColor baseColor,String phrase,int border){
			pdfPTable.setWidthPercentage(100.0f);
			PdfPCell bcell = new PdfPCell(new Phrase(phrase));
			bcell.setColspan(pdfPTable.getNumberOfColumns());
			bcell.setBorder(border);
			if(baseColor!=null)
				bcell.setBorderColor(baseColor);
			else bcell.setBorderColor(new BaseColor(93,93,93));
			pdfPTable.addCell(bcell);
			return pdfPTable;
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
