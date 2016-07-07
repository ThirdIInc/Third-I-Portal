/*
 * Code Written By Sreedhar K
 *
 *Dt:21-08-2004
 *
 */


package org.paradyne.lib.report;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;


// A class which  generates the report in pdf format

public class PDFGenerator
{
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PDFGenerator.class); 

/**
 * Function which generates a PDF header object and returns it.
 * @param img
 * @return
 */

	public HeaderFooter genHeader(String img)
	{
//		    int[] colwidth={40,20,50};
			int[] colwidth={30,50,20};
			HeaderFooter header=null;
		try
		{

/**
 * Creating a Table for adding the company name ,date,report name and the logo
   of the company.
 */		
		
			Table headerTable=new Table(3);
			headerTable.setBorder(Rectangle.NO_BORDER);
			headerTable.setWidths(colwidth);
			headerTable.setPadding(1.5f);
			headerTable.setWidth(90.0f);
//			headerTable.setBackgroundColor(new java.awt.Color(0,0,0));

		// Creating a cell for adding the logo of the company.
			Image image=Image.getInstance(img);
//			image.scaleToFit(55,71);
			Cell hcell=new Cell();
			hcell.add(image);
			hcell.setBorder(Rectangle.NO_BORDER);
//			hcell.setRowspan(2);
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hcell.setVerticalAlignment(Element.ALIGN_TOP);
//			hcell.setBackgroundColor(new java.awt.Color(0,255,0));
			headerTable.addCell(hcell);

//			hcell=new Cell(new Phrase(" "));
//			hcell.setBorder(Rectangle.NO_BORDER);
//			hcell.setRowspan(2);
//			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//			headerTable.addCell(hcell);

		// Creating a cell for adding the name of the company.

//			hcell=new Cell(new Phrase(coname,FontLab.getCompanyNameFont()));
//			hcell.setBorder(Rectangle.NO_BORDER);
//			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
//			hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//			headerTable.addCell(hcell);
//
//		// Creating a cell for adding the Address of the company.
//
//			hcell=new Cell(new Phrase(address,FontLab.getAddressFont()));
//			hcell.setBorder(Rectangle.NO_BORDER);
//			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
//			hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//			headerTable.addCell(hcell);

		// Creating a cell for adding the report name.
			//hcell=new Cell(new Phrase(repname,FontLab.getReportNameFont()));
			//hcell.setBorder(Rectangle.NO_BORDER);
//			hcell.setColspan(3);
//			hcell.setColspan(2);
			//hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			//hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//			hcell.setBackgroundColor(new java.awt.Color(255,0,123));
			//headerTable.addCell(hcell);

		// Creating a cell for adding the date of generation of report.
			//hcell=new Cell(new Phrase("Date : "+today,FontLab.getBodyFont()));
			//hcell.setBorder(Rectangle.NO_BORDER);
//			hcell.setColspan(3);
//			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			//hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			//hcell.setVerticalAlignment(Element.ALIGN_TOP);
//			hcell.setBackgroundColor(new java.awt.Color(123,0,255));
			//headerTable.addCell(hcell);

		// Creating a paragraph object for adding the above generated table to the header.

			Phrase para=new Phrase();
			para.setLeading(0);
			para.add(headerTable);

		
			/**
			 * Creating a header using the paragraph created above,the boolean value does
			 * not show the page number in the header.
			 */
			
			header=new HeaderFooter(para,false);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return header;
	}

	/**
	 * 
	 * @param coname
	 * @param today
	 * @param repname
	 * @param img
	 * @param address
	 * @return
	 */
	public HeaderFooter genHeader(String coname,String today,String repname,String img,String address)
	{
//		int[] colwidth={40,20,50};
		int[] colwidth={30,50,20};
		HeaderFooter header=null;
		try
		{

		/*Creating a Table for adding the company name ,date,report name and the logo
		 *of the company.  */
			Table headerTable=new Table(3);
			headerTable.setBorder(Rectangle.NO_BORDER);
			headerTable.setWidths(colwidth);
			headerTable.setPadding(1.5f);
			headerTable.setWidth(90.0f);
//			headerTable.setBackgroundColor(new java.awt.Color(0,0,0));

		// Creating a cell for adding the logo of the company.
			Image image=Image.getInstance(img);
//			image.scaleToFit(55,71);
			Cell hcell=new Cell();
			hcell.add(image);
			hcell.setBorder(Rectangle.NO_BORDER);
//			hcell.setRowspan(3);
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hcell.setVerticalAlignment(Element.ALIGN_TOP);
//			hcell.setBackgroundColor(new java.awt.Color(0,255,0));
			headerTable.addCell(hcell);

//			hcell=new Cell(new Phrase(" "));
//			hcell.setBorder(Rectangle.NO_BORDER);
//			hcell.setRowspan(2);
//			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//			headerTable.addCell(hcell);

		// Creating a cell for adding the name of the company.

//			hcell=new Cell(new Phrase(coname,FontLab.getCompanyNameFont()));
//			hcell.setBorder(Rectangle.NO_BORDER);
//			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
//			hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//			headerTable.addCell(hcell);
//
//		// Creating a cell for adding the Address of the company.
//
//			hcell=new Cell(new Phrase(address,FontLab.getAddressFont()));
//			hcell.setBorder(Rectangle.NO_BORDER);
//			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
//			hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//			headerTable.addCell(hcell);

		// Creating a cell for adding the report name.
			hcell=new Cell(new Phrase(repname,FontLab.getReportNameFont()));
			hcell.setBorder(Rectangle.NO_BORDER);
//			hcell.setColspan(3);
//			hcell.setColspan(2);
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//			hcell.setBackgroundColor(new java.awt.Color(255,0,123));
			headerTable.addCell(hcell);

		// Creating a cell for adding the date of generation of report.
			hcell=new Cell(new Phrase("Date : "+today,FontLab.getBodyFont()));
			hcell.setBorder(Rectangle.NO_BORDER);
//			hcell.setColspan(3);
//			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			hcell.setVerticalAlignment(Element.ALIGN_TOP);
//			hcell.setBackgroundColor(new java.awt.Color(123,0,255));
			headerTable.addCell(hcell);

		// Creating a paragraph object for adding the above generated table to the header.

			Phrase para=new Phrase();
			para.setLeading(0);
			para.add(headerTable);

		/* Creating a header using the paragraph created above,the boolean value does
		 *not show the page number in the header.  */
			header=new HeaderFooter(para,false);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		    return header;
	}
	/**
	 * following function is called to display the photo of an employee in the report
	 * @param data
	 * @param img
	 * @return
	 */
	public Table genPhoto(String data,String img)
	{
//		int[] colwidth={40,20,50};
		int[] colwidth={30,50,20};
		HeaderFooter header=null;
		Table headerTable =null ;
		try
		{
		/*Creating a Table for adding the company name ,date,report name and the logo
		 *of the company.  */
			headerTable=new Table(3);
			
			headerTable.setBorder(Rectangle.NO_BORDER);
			headerTable.setWidths(colwidth);
			headerTable.setPadding(1.5f);
			headerTable.setWidth(90.0f);
//			headerTable.setBackgroundColor(new java.awt.Color(0,0,0));

		// Creating a cell for adding the logo of the company.
			Cell hcell=new Cell();
			try{
			Image image=Image.getInstance(img);
			image.scaleToFit(80,80);
			
			hcell.add(image);
			hcell.setBorder(Rectangle.NO_BORDER);
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerTable.addCell(hcell);
			}catch (Exception e) {
				// TODO: handle exception
			}
		// Creating a cell for adding the report name.
			hcell=new Cell(new Phrase(data,FontLab.getLetterFont()));
			hcell.setBorder(Rectangle.NO_BORDER);
//			hcell.setColspan(3);
//			hcell.setColspan(2);
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerTable.addCell(hcell);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return headerTable;
	}
/**
 * Following function generates a PDF footer object and returns it.
 * @return
 */

	public HeaderFooter genFooter() {
	/* Creating a footer,the boolean value does shows the page number in the footer.   */
		Phrase ph = new Phrase("");
		ph.setLeading(4);
		HeaderFooter footer=new HeaderFooter(ph,false);
		footer.setBorder(Rectangle.NO_BORDER);
		return footer;
	}
	/**
	 * following function generates the image in the report
	 * @param imageName
	 * @return
	 */
	public String generateImage(String imageName){
		 
		try {
			Document document = new Document();
			// PdfWriter.getInstance(document,new FileOutputStream("imagesPDF.pdf"));
			document.open();
			Image image = Image.getInstance(imageName);
			logger.info("Image>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+image.hashCode());
			//document.add(new Paragraph("Roseindia"));
			int a=image.alignment();
			logger.info("Value of alignment"+a);
			document.add(image);
			//document.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return imageName;
	}

	/**
	 * Function which generates a PDF table object and returns it and also populates it
     * with the data provided.  
	 * @param colNames
	 * @param data
	 * @param widths
	 * @param alignment
	 * @return
	 */


	
	public PdfPTable tableBody(String colNames[], String data[][],int widths[],int alignment[])
	{
		PdfPTable bodyTable=null;
		try
		{
		//table created.
			bodyTable=new PdfPTable(colNames.length);
//        	bodyTable.setBorder(Rectangle.NO_BORDER);
//           	bodyTable.setPadding(1.0f);
//           	bodyTable.setCellspacing(1);

			float[] www  = new float[widths.length];
			for(int i =0;i<www.length; i++) {
				www[i] = (float)widths[i];
			}
        	bodyTable.setTotalWidth(www);
        	bodyTable.setWidthPercentage(100.0f);
        //cell created.
        	PdfPCell  bcell=null;

		//The first row of the table containing the column names of the report required.
			for(int i=0;i<colNames.length;i++)
    	    {
        	   	bcell=new PdfPCell(new Phrase(colNames[i],FontLab.getBodyHeaderFont()));
//           	bcell.setBorder(Rectangle.NO_BORDER);
				bcell.setBorderColor(FontLab.getHeaderBorderColor());
           		bcell.setHorizontalAlignment(1);
           		bcell.setBackgroundColor(FontLab.getHeaderBackColor());
           		bodyTable.addCell(bcell);
           	}

        //The table containing the data for the report required.
        	for (int i=0;i<data.length;i++)
           	{
           		for(int j=0;j<colNames.length;j++)
        		{
           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
           			
        			bcell=new PdfPCell(new Phrase(data[i][j],FontLab.getBodyFont()));

//        			bcell.setBorder(Rectangle.NO_BORDER);
        			bcell.setBorderColor(FontLab.getBorderColor());
//        			bcell.setBorder(Rectangle.BOTTOM);
        			bcell.setHorizontalAlignment(alignment[j]);
        			bcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        			bodyTable.addCell(bcell);
           		}
        	}
        }
        catch(Exception e)
        {
        	logger.error(e.getMessage());
        }
        bodyTable.setHeaderRows(1);
		return bodyTable;
	}
	public Table tableBodyBold(String colNames[], String data[][],int widths[],int alignment[],int fontSize)
	{
		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(colNames.length);
			bodyTable.setBorder(Rectangle.NO_BORDER);
//        	bodyTable.setBorder(Rectangle.NO_BORDER);
//           	bodyTable.setPadding(1.0f);
//           	bodyTable.setCellspacing(1);

			float[] www  = new float[widths.length];
			for(int i =0;i<www.length; i++) {
				www[i] = (float)widths[i];
			}
        	//bodyTable.setTotalWidth(www);
        	//bodyTable.setWidthPercentage(100.0f);
        	
        	bodyTable.setPadding(1.0f);
        	bodyTable.setWidths(widths);
        	bodyTable.setWidth(100.0f);
        	
        //cell created.
        	Cell  bcell=null;

		//The first row of the table containing the column names of the report required.
			for(int i=0;i<colNames.length;i++)
    	    {
        	   	bcell=new Cell(new Phrase(colNames[i],FontLab.getBodyHeaderFont(fontSize)));
        	  //bcell.setBorder(Rectangle.NO_BORDER);
				bcell.setBorderColor(FontLab.getHeaderBorderColor());
           		bcell.setHorizontalAlignment(alignment[i]);
           		bcell.setVerticalAlignment(Element.ALIGN_TOP);
           		bcell.setBackgroundColor(FontLab.getHeaderBackColor());
           		bodyTable.addCell(bcell);
           	}

        //The table containing the data for the report required.
        	for (int i=0;i<data.length;i++)
           	{
           		for(int j=0;j<colNames.length;j++)
        		{
           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyHeaderFont(fontSize)));

//        			bcell.setBorder(Rectangle.NO_BORDER);
        			bcell.setBorderColor(FontLab.getBorderColor());
//        			bcell.setBorder(Rectangle.BOTTOM);
        			bcell.setHorizontalAlignment(alignment[j]);
        			bcell.setVerticalAlignment(Element.ALIGN_TOP);
        			bodyTable.addCell(bcell);
           		}
        	}
        }
        catch(Exception e)
        {
        	logger.error(e.getMessage());
        }
       // bodyTable.setHeaderRows(1);
		return bodyTable;
	}
	/**
	 * following function is called to generate the table with data without having the cell border
	 * @param data
	 * @param widths
	 * @param alignment
	 * @param fontStyle
	 * @return
	 */
	public Table tableBodyNoCellBorder(String data[][],int widths[],int alignment[], int fontStyle)
	{
		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(alignment.length);
        	bodyTable.setBorder(Rectangle.BOX);
        	bodyTable.setBorderColor(FontLab.getBorderColor());
           	bodyTable.setPadding(1.0f);
        	bodyTable.setWidths(widths);
        	bodyTable.setWidth(100.0f);
        //cell created.
        	Cell bcell=new Cell();

        //The table containing the data for the report required.
        	for (int i=0;i<data.length;i++)
           	{
           		for(int j=0;j<alignment.length;j++)
        		{
           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
           			
           			if(fontStyle==1)
           				bcell=new Cell(new Phrase(data[i][j],FontLab.getBoldFont()));
           				else
           					bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFont()));
        			bcell.setBorder(Rectangle.NO_BORDER);
   		
//        			bcell.setBorderColor(FontLab.getBorderColor());
//        			bcell.setBorder(Rectangle.BOTTOM);
        			bcell.setHorizontalAlignment(alignment[j]);
        			bodyTable.addCell(bcell);
           		}
        	}

        }
        catch(Exception e)
        {
        	logger.error(e.getMessage());
        }

		return bodyTable;
	}	
	/**
	 * 
	 * @param colNames
	 * @param data
	 * @param widths
	 * @param alignment
	 * @return
	 */
	public Table tableBodyPercent(String colNames[], String data[][],int widths[],int alignment[])
	{
		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(colNames.length);
        	bodyTable.setBorder(Rectangle.NO_BORDER);
           	bodyTable.setPadding(1.0f);
           //	bodyTable.setCellspacing(1);
        	bodyTable.setWidths(widths);
        	bodyTable.setWidth(50.0f);
        //cell created.
        	Cell bcell=new Cell();

		//The first row of the table containing the column names of the report required.
			for(int i=0;i<colNames.length;i++)
    	    {
        	   	bcell=new Cell(new Phrase(colNames[i],FontLab.getBodyHeaderFont()));
//           	bcell.setBorder(Rectangle.NO_BORDER);
				bcell.setBorderColor(FontLab.getHeaderBorderColor());
           		bcell.setHorizontalAlignment(alignment[i]);
           		bcell.setBackgroundColor(FontLab.getHeaderBackColor());
           		bodyTable.addCell(bcell);
           	}

        //The table containing the data for the report required.
        	for (int i=0;i<data.length;i++)
           	{
           		for(int j=0;j<colNames.length;j++)
        		{
           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFont()));

//        			bcell.setBorder(Rectangle.NO_BORDER);
        			bcell.setBorderColor(FontLab.getBorderColor());
//        			bcell.setBorder(Rectangle.BOTTOM);
        			bcell.setHorizontalAlignment(alignment[j]);
        			bodyTable.addCell(bcell);
           		}
        	}

        }
        catch(Exception e)
        {
        	logger.error(e.getMessage());
        }
//        bodyTable.setHeaderRows(1);

		return bodyTable;
	}
/**
 * 
 * @param data
 * @param widths
 * @param alignment
 * @return
 */
	public Table tableBodyPercent(String data[][],int widths[],int alignment[])
	{
		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(alignment.length);
        	bodyTable.setBorder(Rectangle.NO_BORDER);
           	bodyTable.setPadding(1.0f);
        	bodyTable.setWidths(widths);
        	bodyTable.setWidth(50.0f);
        //cell created.
        	Cell bcell=new Cell();

        //The table containing the data for the report required.
        	for (int i=0;i<data.length;i++)
           	{
           		for(int j=0;j<alignment.length;j++)
        		{
           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
           			
        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFont()));
//        			bcell.setBorder(Rectangle.NO_BORDER);
        			bcell.setBorderColor(FontLab.getBorderColor());
//        			bcell.setBorder(Rectangle.BOTTOM);
        			bcell.setHorizontalAlignment(alignment[j]);
        			bodyTable.addCell(bcell);
           		}
        	}

        }
        catch(Exception e)
        {
        	logger.error(e.getMessage());
        }

		return bodyTable;
	}

	/**
	 * following function is called to populate the table with data without column heading 
	 * @param data
	 * @param widths
	 * @param alignment
	 * @return
	 */
		public Table tableBody(String data[][],int widths[],int alignment[])
	{
		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(alignment.length);
        	bodyTable.setBorder(Rectangle.NO_BORDER);
           	bodyTable.setPadding(1.0f);
        	bodyTable.setWidths(widths);
        	bodyTable.setWidth(100.0f);
        //cell created.
        	Cell bcell=new Cell();

        //The table containing the data for the report required.
        	for (int i=0;i<data.length;i++)
           	{
           		for(int j=0;j<alignment.length;j++)
        		{
           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFont()));
//        			bcell.setBorder(Rectangle.NO_BORDER);
        			bcell.setBorderColor(FontLab.getBorderColor());
//        			bcell.setBorder(Rectangle.BOTTOM);
        			bcell.setHorizontalAlignment(alignment[j]);
        			bodyTable.addCell(bcell);
           		}
        	}

        }
        catch(Exception e)
        {
        	logger.error(e.getMessage());
        }

		return bodyTable;
	}
		/**
		 * following function is called to populate the table with data without column heading 
		 * and accepts user defined fontSize.
		 * @param data
		 * @param widths
		 * @param alignment
		 * @return
		 */
		
		public Table tableBody(String data[][],int widths[],int alignment[], int fontSize,int flag)
		{
			Table bodyTable=null;
			try
			{
			//table created.
				bodyTable=new Table(alignment.length);
	        	bodyTable.setBorder(Rectangle.NO_BORDER);
	           	bodyTable.setPadding(1.0f);
	        	bodyTable.setWidths(widths);
	        	bodyTable.setWidth(100.0f);
	        //cell created.
	        	Cell bcell=new Cell();

	        //The table containing the data for the report required.
	        	for (int i=0;i<data.length;i++)
	           	{
	           		for(int j=0;j<alignment.length;j++)
	        		{
	           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
	           			
	        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFontNormal(fontSize)));
//	        			bcell.setBorder(Rectangle.NO_BORDER);
	        			bcell.setBorderColor(FontLab.getBorderColor());
//	        			bcell.setBorder(Rectangle.BOTTOM);
	        			bcell.setHorizontalAlignment(alignment[j]);
	        			bodyTable.addCell(bcell);
	           		}
	        	}

	        }
	        catch(Exception e)
	        {
	        	logger.error(e.getMessage());
	        }

			return bodyTable;
		}

		/**
		 * following function is called to populate the table with data without having any cell border or bouter border
		 * @param data
		 * @param widths
		 * @param alignment
		 * @return
		 */
		
	public PdfPTable tableBodyNoBorder(String data[][],int widths[],int alignment[]) {
		PdfPTable bodyTable=null;
		try {
		//table created.
			bodyTable=new PdfPTable(alignment.length);
//        	bodyTable.setBorder(Rectangle.NO_BORDER);
//        	bodyTable.setWidths(widths);
//        	bodyTable.setWidth(100.0f);

			float[] www  = new float[widths.length];
			for(int i =0;i<www.length; i++) {
				www[i] = (float)widths[i];
			}
        	bodyTable.setTotalWidth(www);
        	bodyTable.setWidthPercentage(100.0f);


        //cell created.
        	PdfPCell bcell=null;

        //The table containing the data for the report required.
        	for (int i=0;i<data.length;i++) {
           		for(int j=0;j<alignment.length;j++) {
           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
        			bcell=new PdfPCell(new Phrase(data[i][j],FontLab.getBodyFont()));
        			bcell.setBorder(Rectangle.NO_BORDER);
//        			bcell.setBorderColor(FontLab.getBorderColor());
//        			bcell.setBorder(Rectangle.BOTTOM);
        			bcell.setHorizontalAlignment(alignment[j]);
        			bcell.setPadding(2.5f);
        			bcell.setExtraParagraphSpace(6.5f);
        			bodyTable.addCell(bcell);
           		}
        	}
        } catch(Exception e) {
        	logger.error(e.getMessage());
        }
		return bodyTable;
	}
	
	/**
	 * following function is called to populate the table with data  without column name
	 * @param data
	 * @param widths
	 * @param alignment
	 * @param bold
	 * @param fontsize
	 * @return PdfPTable
	 */
	public PdfPTable tableBodyNoBorder(String data[][],int widths[],int alignment[],int [] bold,int [] fontsize) {
		PdfPTable bodyTable=null;
		Table table = null;
		try {
		//table created.
			bodyTable=new PdfPTable(alignment.length);
//        	bodyTable.setBorder(Rectangle.NO_BORDER);
//        	bodyTable.setWidths(widths);
//        	bodyTable.setWidth(100.0f);

			float[] www  = new float[widths.length];
			for(int i =0;i<www.length; i++) {
				www[i] = (float)widths[i];
			}
        	bodyTable.setTotalWidth(www);
        	bodyTable.setWidthPercentage(100.0f);


        //cell created.
        	PdfPCell bcell=null;
        //The table containing the data for the report required.
        	for (int i=0;i<data.length;i++) {
           		for(int j=0;j<alignment.length;j++) {
           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
           			
           			if(bold[j]==1)
           				bcell=new PdfPCell(new Phrase(data[i][j],FontLab.getBodyFontBold(fontsize[j])));
           			else 
           				bcell=new PdfPCell(new Phrase(data[i][j],FontLab.getBodyFontNormal(fontsize[j])));
        			bcell.setBorder(Rectangle.NO_BORDER);
//        			bcell.setBorderColor(FontLab.getBorderColor());
//        			bcell.setBorder(Rectangle.BOTTOM);
        			bcell.setHorizontalAlignment(alignment[j]);
        			bcell.setPadding(2.5f);
        			bcell.setExtraParagraphSpace(6.5f);
        			bodyTable.addCell(bcell);
        			
           		}
        	}
        } catch(Exception e) {
        	logger.error(e.getMessage());
        }
		return bodyTable;
	}
/**
 * following function is called to populate the table with data and to make it bold without column name
 * @param data
 * @param widths
 * @param alignment
 * @return
 */
	public PdfPTable tableBodyBold(String data[][],int widths[],int alignment[]) {
		PdfPTable bodyTable=null;
		try {
		//table created.
			bodyTable=new PdfPTable(alignment.length);
//        	bodyTable.setBorder(Rectangle.NO_BORDER);
//        	bodyTable.setWidths(widths);
//        	bodyTable.setWidth(100.0f);

			float[] www  = new float[widths.length];
			for(int i =0;i<www.length; i++) {
				www[i] = (float)widths[i];
			}
        	bodyTable.setTotalWidth(www);
        	bodyTable.setWidthPercentage(100.0f);


        //cell created.
        	PdfPCell bcell=null;

        //The table containing the data for the report required.
        	for (int i=0;i<data.length;i++) {
           		for(int j=0;j<alignment.length;j++) {
           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
        			bcell=new PdfPCell(new Phrase(data[i][j],FontLab.getBoldUnderlineFont()));
        			bcell.setBorder(Rectangle.NO_BORDER);
//        			bcell.setBorderColor(FontLab.getBorderColor());
//        			bcell.setBorder(Rectangle.BOTTOM);
        			bcell.setHorizontalAlignment(alignment[j]);
        			bcell.setPadding(2.5f);
        			bcell.setExtraParagraphSpace(6.5f);
        			bodyTable.addCell(bcell);
           		}
        	}
        } catch(Exception e) {
        	logger.error(e.getMessage());
        }
		return bodyTable;
	}
//	public Table tableBodyNoBorder(String data[][],int widths[],int alignment[]) {
//	Table bodyTable=null;
//	try {
//	//table created.
//		bodyTable=new Table(alignment.length);
//    	bodyTable.setBorder(Rectangle.NO_BORDER);
//       	bodyTable.setPadding(1.0f);
//    	bodyTable.setWidths(widths);
//    	bodyTable.setWidth(100.0f);
//    //cell created.
//    	Cell bcell=new Cell();
//
//    //The table containing the data for the report required.
//    	for (int i=0;i<data.length;i++) {
//       		for(int j=0;j<alignment.length;j++) {
//    			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFont()));
//    			bcell.setBorder(Rectangle.NO_BORDER);
////    			bcell.setBorderColor(FontLab.getBorderColor());
////    			bcell.setBorder(Rectangle.BOTTOM);
//    			bcell.setHorizontalAlignment(alignment[j]);
//    			bodyTable.addCell(bcell);
//       		}
//    	}
//    } catch(Exception e) {
//    	logger.info("in PDF generator tablebody"+e);
//    }
//
//	return bodyTable;
//}


	/**
	 * following function is called to populate the table with data and make it compress 
	 * @param colNames
	 * @param data
	 * @param widths
	 * @param alignment
	 * @param widthSize
	 * @return
	 */

		public Table tableBody(String colNames[], String data[][],int widths[],int alignment[],int widthSize)
	{
		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(colNames.length);
        	bodyTable.setBorder(Rectangle.NO_BORDER);
           	bodyTable.setPadding(1.0f);
           	//bodyTable.setCellspacing(1);
        	bodyTable.setWidths(widths);
        	bodyTable.setWidth((float)widthSize);
        //cell created.
        	Cell bcell=new Cell();

		//The first row of the table containing the column names of the report required.
			for(int i=0;i<colNames.length;i++)
    	    {
        	   	bcell=new Cell(new Phrase(colNames[i],FontLab.getBodyHeaderFont()));
//           	bcell.setBorder(Rectangle.NO_BORDER);
				bcell.setBorderColor(FontLab.getHeaderBorderColor());
           		bcell.setHorizontalAlignment(alignment[i]);
           		bcell.setBackgroundColor(FontLab.getHeaderBackColor());
           		bodyTable.addCell(bcell);
           	}

        //The table containing the data for the report required.
        	for (int i=0;i<data.length;i++)
           	{
           		for(int j=0;j<colNames.length;j++)
        		{
           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
           			
        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFont()));

//        			bcell.setBorder(Rectangle.NO_BORDER);
        			bcell.setBorderColor(FontLab.getBorderColor());
//        			bcell.setBorder(Rectangle.BOTTOM);
        			bcell.setHorizontalAlignment(alignment[j]);
        			bodyTable.addCell(bcell);
           		}
        	}

        }
        catch(Exception e)
        {
        	logger.error(e.getMessage());
        }

		return bodyTable;
	}

		public Table tableBodyForPaybill(String colNames[], String data[][],int widths[],int alignment[],int fontSize,Object[][]otherData)
		{
			Table bodyTable=null;
			try
			{
			//table created.
				bodyTable=new Table(colNames.length);
				bodyTable.setBorder(Rectangle.NO_BORDER);

				float[] www  = new float[widths.length];
				for(int i =0;i<www.length; i++) {
					www[i] = (float)widths[i];
				}
	        	
	        	bodyTable.setPadding(1.0f);
	        	bodyTable.setWidths(widths);
	        	bodyTable.setWidth(100.0f);
	        	
	        //cell created.
	        	Cell  bcell=null;

			//The first row of the table containing the column names of the report required.
				for(int i=0;i<colNames.length;i++)
	    	    {
	        	   	bcell=new Cell(new Phrase(colNames[i],FontLab.getBodyHeaderFont(fontSize)));
					bcell.setBorderColor(FontLab.getHeaderBorderColor());
	           		bcell.setHorizontalAlignment(alignment[i]);
	           		bcell.setVerticalAlignment(Element.ALIGN_TOP);
	           		bcell.setBackgroundColor(FontLab.getHeaderBackColor());
	           		bodyTable.addCell(bcell);
	           	}

	        //The table containing the data for the report required.
	        	for (int i=0;i<data.length;i++)
	           	{
	           		for(int j=0;j<colNames.length;j++)
	        		{
	           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
	        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyHeaderFont(fontSize)));

	        			bcell.setBorderColor(new java.awt.Color(60,60,60));
	        			bcell.setBorder(Rectangle.NO_BORDER);
	        			bcell.setHorizontalAlignment(alignment[j]);
	        			bcell.setVerticalAlignment(Element.ALIGN_TOP);
	        			bodyTable.addCell(bcell);
	        			
	           		}
	           		if(otherData!=null && otherData.length>0){
	           			bcell=new Cell(new Phrase(String.valueOf(otherData[i][0]),FontLab.getBoldFont(fontSize)));
	           		        		bcell.setColspan(3);
	           		bcell.setBorder(Rectangle.BOTTOM);
	           		bcell.setBorderColor(new java.awt.Color(60,60,60));
	           		bcell.setHorizontalAlignment(0);
	    			bcell.setVerticalAlignment(Element.ALIGN_TOP);
	           		bodyTable.addCell(bcell);
	           		
	           		bcell=new Cell(new Phrase(String.valueOf(otherData[i][1]),FontLab.getBoldFont(fontSize)));
	           		
	           		//bcell=new Cell(new Phrase("Some Text Some TextSome TextSome TextSome TextSome Text",FontLab.getBodyFontNormal(fontSize)));
	           		bcell.setBorder(Rectangle.BOTTOM);
	           		
	           		bcell.setBorderColor(new java.awt.Color(60,60,60));
	           		bcell.setHorizontalAlignment(0);
	    			bcell.setVerticalAlignment(Element.ALIGN_TOP);
	           		bcell.setColspan(colNames.length-3);
	           		bodyTable.addCell(bcell);
	           		}
	        	}
	        }
	        catch(Exception e)
	        {
	        	logger.error(e.getMessage());
	        }
	       // bodyTable.setHeaderRows(1);
			return bodyTable;
		}
		
		/**
		 * following function is called to populate the table with data and make it compress without column heading
		 * @param data
		 * @param widths
		 * @param alignment
		 * @param widthSize
		 * @return
		 */	
		
		public Table tableBody(String data[][],int widths[],int alignment[],int widthSize)
	{
		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(alignment.length);
        	bodyTable.setBorder(Rectangle.NO_BORDER);
           	bodyTable.setPadding(1.0f);
        	bodyTable.setWidths(widths);
        	bodyTable.setWidth((float)widthSize);
        //cell created.
        	Cell bcell=new Cell();

        //The table containing the data for the report required.
        	for (int i=0;i<data.length;i++)
           	{
           		for(int j=0;j<alignment.length;j++)
        		{
           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
           			
        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFont()));
//        			bcell.setBorder(Rectangle.NO_BORDER);
        			bcell.setBorderColor(FontLab.getBorderColor());
//        			bcell.setBorder(Rectangle.BOTTOM);
        			bcell.setHorizontalAlignment(alignment[j]);
        			bodyTable.addCell(bcell);
           		}
        	}

        }
        catch(Exception e)
        {
        	logger.error(e.getMessage());
        }
		return bodyTable;
	}
/**
 * 
 * @param data
 * @param widths
 * @param alignment
 * @param colors
 * @return
 */
		public Table tableBodyLeft( String data[][],int widths[],int alignment[],int colors[]) {
			Table bodyTable=null;
			try {
			//table created.
				bodyTable=new Table(widths.length);
	        	bodyTable.setBorder(Rectangle.NO_BORDER);
	           	bodyTable.setPadding(1.0f);
	        	bodyTable.setWidths(widths);
	        	bodyTable.setWidth(40.0f);
	        	bodyTable.setAlignment(2);
	        //cell created.
	        	Cell bcell=new Cell();
	        	for (int i=0;i<data.length;i++) {
	           		for(int j=0;j<widths.length;j++) {
	           			
	           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
	           			
	        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFont1()));
//	        			bcell.setBorder(Rectangle.NO_BORDER);
//	        			bcell.setBorder(Rectangle.BOTTOM);
//		       			bcell.setBorder(Rectangle.RIGHT);
						bcell.setBorderColor(FontLab.getBorderColor());
	        			bcell.setHorizontalAlignment(alignment[j]);
	        			bodyTable.addCell(bcell);
	        			if(colors[j]==1) {
							bcell.setBackgroundColor(FontLab.getHeaderBackColor());
							bcell.setBorderColor(FontLab.getHeaderBorderColor());
	        			}
	           		}
	        	}
	        } catch(Exception e) {
	        	logger.error(e.getMessage());
	        }
			return bodyTable;
		}


	/**
	 * 
	 * @param data
	 * @param widths
	 * @param alignment
	 * @return
	 */
		public Table tableBodyAnnex(String data[][],int widths[],int alignment[])
		{
			Table bodyTable=null;
			try
			{
			//table created.
				bodyTable=new Table(alignment.length);
	        	bodyTable.setBorder(Rectangle.NO_BORDER);
	           	bodyTable.setPadding(1.0f);
	        	bodyTable.setWidths(widths);
	        	bodyTable.setWidth(80.0f);
	        //cell created.
	        	Cell bcell=new Cell();

	        	//The table containing the data for the report required.
	        	for (int i=0;i<data.length;i++)
	           	{
	           		for(int j=0;j<alignment.length;j++)
	        		{
	           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
	           			
	        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyHeaderFont()));
//	        			bcell.setBorder(Rectangle.NO_BORDER);
	        			bcell.setBorderColor(FontLab.getBorderColor());
//	        			bcell.setBorder(Rectangle.BOTTOM);
	        			bcell.setBackgroundColor(FontLab.getHeaderBackColor());
	        			bcell.setHorizontalAlignment(1);
	        			bodyTable.addCell(bcell);
	           		}
	        	}

	        }catch(Exception e)
	        {
	        	logger.error(e.getMessage());
	        }

			return bodyTable;
		}

		/**
		 * following function is strictly used for Promotion module.
		 * @param data
		 * @param widths
		 * @param alignment
		 * @return
		 */
		
		
		public PdfPTable tableBodyNoBorderPr(String data[][],int widths[],int alignment[]) {
			PdfPTable bodyTable=null;
			try {
			//table created.
				bodyTable=new PdfPTable(alignment.length);
//	        	bodyTable.setBorder(Rectangle.NO_BORDER);
//	        	bodyTable.setWidths(widths);
//	        	bodyTable.setWidth(100.0f);

				float[] www  = new float[widths.length];
				for(int i =0;i<www.length; i++) {
					www[i] = (float)widths[i];
				}
	        	bodyTable.setTotalWidth(www);
	        	bodyTable.setWidthPercentage(95.0f);


	        //cell created.
	        	PdfPCell bcell=null;

	        //The table containing the data for the report required.
	        	for (int i=0;i<data.length;i++) {
	           		for(int j=0;j<alignment.length;j++) {
	           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
	           			
	        			bcell=new PdfPCell(new Phrase(data[i][j],FontLab.getBodyFontPromo()));
	        			bcell.setBorder(Rectangle.NO_BORDER);
//	        			bcell.setBorderColor(FontLab.getBorderColor());
//	        			bcell.setBorder(Rectangle.BOTTOM);
	        			bcell.setHorizontalAlignment(alignment[j]);
	        			bcell.setPadding(2.5f);
	        			bcell.setExtraParagraphSpace(6.5f);
	        			bodyTable.addCell(bcell);
	           		}
	        	}
	        } catch(Exception e) {
	        	logger.error(e.getMessage());
	        }
			return bodyTable;
		}
	
		/**
		 * following table is strictly used for Promotion module.
		 * @param colNames
		 * @param data
		 * @param widths
		 * @param alignment
		 * @return
		 */
		public PdfPTable tableBodyPr(String colNames[], String data[][],int widths[],int alignment[])
		{
			PdfPTable bodyTable=null;
			try
			{
			//table created.
				bodyTable=new PdfPTable(colNames.length);
//	        	bodyTable.setBorder(Rectangle.NO_BORDER);
//	           	bodyTable.setPadding(1.0f);
//	           	bodyTable.setCellspacing(1);

				float[] www  = new float[widths.length];
				for(int i =0;i<www.length; i++) {
					www[i] = (float)widths[i];
				}
	        	bodyTable.setTotalWidth(www);
	        	bodyTable.setWidthPercentage(98.0f);
	        //cell created.
	        	PdfPCell  bcell=null;

			//The first row of the table containing the column names of the report required.
				for(int i=0;i<colNames.length;i++)
	    	    {
					bcell=new PdfPCell(new Phrase(colNames[i],FontLab.getBodyFontPr()));
//	           	bcell.setBorder(Rectangle.NO_BORDER);
					bcell.setBorderColor(FontLab.getHeaderBorderColorPro());
	           		bcell.setHorizontalAlignment(alignment[i]);
	           		bcell.setBackgroundColor(FontLab.getHeaderBackColor());
	           		bodyTable.addCell(bcell);
	        		
	           	}

	        //The table containing the data for the report required.
	        	for (int i=0;i<data.length;i++)
	           	{   
	           		for(int j=0;j<colNames.length;j++)
	        		{
	           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
	           			
	        			bcell=new PdfPCell(new Phrase(data[i][j],FontLab.getBodyFontPr()));

//	        			bcell.setBorder(Rectangle.NO_BORDER);
	        			bcell.setBorderColor(FontLab.getHeaderBorderColorPro());
//	        			bcell.setBorder(Rectangle.BOTTOM);
	        			bcell.setHorizontalAlignment(alignment[j]);
	        			bcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        			bodyTable.addCell(bcell);
	           		}
	        	}
	        }
	        catch(Exception e)
	        {
	        	logger.error(e.getMessage());
	        }
	        bodyTable.setHeaderRows(1);
			return bodyTable;
		}
/**
 * following function is strictly called for Promotion Module.
 * @param data
 * @param widths
 * @param alignment
 * @return
 */
		public PdfPTable tableBodyNoBorderPrSmall(String data[][],int widths[],int alignment[]) {
			PdfPTable bodyTable=null;
			try {
			//table created.
				bodyTable=new PdfPTable(alignment.length);
//	        	bodyTable.setBorder(Rectangle.NO_BORDER);
//	        	bodyTable.setWidths(widths);
//	        	bodyTable.setWidth(100.0f);

				float[] www  = new float[widths.length];
				for(int i =0;i<www.length; i++) {
					www[i] = (float)widths[i];
				}
	        	bodyTable.setTotalWidth(www);
	        	bodyTable.setWidthPercentage(95.0f);


	        //cell created.
	        	PdfPCell bcell=null;

	        //The table containing the data for the report required.
	        	for (int i=0;i<data.length;i++) {
	           		for(int j=0;j<alignment.length;j++) {
	           			
	           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
	           			
	        			bcell=new PdfPCell(new Phrase(data[i][j],FontLab.getBodyFontPromoSmall()));
	        			bcell.setBorder(Rectangle.NO_BORDER);
//	        			bcell.setBorderColor(FontLab.getBorderColor());
//	        			bcell.setBorder(Rectangle.BOTTOM);
	        			bcell.setHorizontalAlignment(alignment[j]);
	        			bcell.setPadding(2.5f);
	        			bcell.setExtraParagraphSpace(6.5f);
	        			bodyTable.addCell(bcell);
	           		}
	        	}
	        } catch(Exception e) {
	        	logger.error(e.getMessage());
	        }
			return bodyTable;
		}
/**
 * 
 * @param colNames
 * @param widths
 * @param alignment
 * @param widthSize
 * @return
 */
		public Table tableBodyBG(String colNames[],int widths[],int alignment[],int widthSize)
		{
			Table bodyTable=null;
			try
			{
			//table created.
				bodyTable=new Table(colNames.length);
	        	bodyTable.setBorder(Rectangle.NO_BORDER);
	           	bodyTable.setPadding(1.0f);
	           	bodyTable.setWidths(widths);
	        	bodyTable.setWidth((float)widthSize);
	        //cell created.
	        	Cell bcell=new Cell();

			//The first row of the table containing the column names of the report required.
				for(int i=0;i<colNames.length;i++)
	    	    {
	        	   	bcell=new Cell(new Phrase(colNames[i],FontLab.getBodyHeaderFont()));
//	           	bcell.setBorder(Rectangle.NO_BORDER);
					bcell.setBorderColor(FontLab.getHeaderBorderColorPro());
	           		bcell.setHorizontalAlignment(alignment[i]);
	           		bcell.setBackgroundColor(FontLab.getHeaderBackColor());
	           		bodyTable.addCell(bcell);
	           	}

	        //The table containing the data for the report required.
	        	

	        }
	        catch(Exception e)
	        {
	        	logger.error(e.getMessage());
	        }

			return bodyTable;
		}
/**
 * 
 * @param data
 * @param widths
 * @param alignment
 * @return
 */
		public Table tableBody1(String data[][],int widths[],int alignment[])
		{
			Table bodyTable=null;
			try
			{
			//table created.
				bodyTable=new Table(alignment.length);
	        	bodyTable.setBorder(Rectangle.NO_BORDER);
	           	bodyTable.setPadding(1.0f);
	        	bodyTable.setWidths(widths);
	        	bodyTable.setWidth(98.0f);
	        //cell created.
	        	Cell bcell=new Cell();

	        //The table containing the data for the report required.
	        	for (int i=0;i<data.length;i++)
	           	{
	           		for(int j=0;j<alignment.length;j++)
	        		{
	           			data[i][j] = String.valueOf(data[i][j]).replace('¿','`');
	           			
	        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFontPr()));
//	        			bcell.setBorder(Rectangle.NO_BORDER);
	        			bcell.setBorderColor(FontLab.getBorderColor1());
//	        			bcell.setBorder(Rectangle.BOTTOM);
	        			bcell.setHorizontalAlignment(alignment[j]);
	        			bodyTable.addCell(bcell);
	           		}
	        	}

	        }
	        catch(Exception e)
	        {
	        	logger.error(e.getMessage());
	        }

			return bodyTable;
		}
		
	
}