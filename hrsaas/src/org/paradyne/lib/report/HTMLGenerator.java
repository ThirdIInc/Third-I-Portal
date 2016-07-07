/*
 * Code Written By Sreedhar K
 *
 *Dt:21-08-2004
 *
 */

package org.paradyne.lib.report;

import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.html.HtmlWriter;


import org.paradyne.lib.*;
import org.paradyne.lib.report.*;

// A class which  generates the report in html format

public class HTMLGenerator
{
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HTMLGenerator.class); 

//Function which generates a HTML header object and returns it.

	public HeaderFooter genHeader(String coname,String today,String repname,String img,String address)
	{
		HeaderFooter header=null;
		try
		{

		/*Creating a Table for adding the company name ,date,report name and the logo
		 *of the company.  */
			Table headerTable=new Table(3);
			headerTable.setBorderWidth(0);
//			headerTable.setWidth(100.0f);

		// Creating a cell for adding the logo of the company.
//			Image image=Image.getInstance(img);
//			image.scaleToFit(200,200);
			Cell hcell=new Cell();
//			hcell.add(image);
			hcell.setBorderWidth(0);
			hcell.setRowspan(2);
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hcell.setVerticalAlignment(Element.ALIGN_TOP);
			headerTable.addCell(hcell);

			hcell=new Cell(new Phrase(" "));
			hcell.setBorderWidth(0);
			hcell.setRowspan(2);
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerTable.addCell(hcell);

		// Creating a cell for adding the name of the company.
			hcell=new Cell(new Phrase(coname,FontLab.getCompanyNameFont()));
			hcell.setBorderWidth(0);
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerTable.addCell(hcell);

			hcell=new Cell(new Phrase(address,FontLab.getAddressFont()));
			hcell.setBorderWidth(0);
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerTable.addCell(hcell);


		// Creating a cell for adding the report name.
			hcell=new Cell(new Phrase(repname,FontLab.getReportNameFont()));
			hcell.setBorderWidth(0);
			hcell.setColspan(3);
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerTable.addCell(hcell);

		// Creating a cell for adding the date of generation of report.
			hcell=new Cell(new Phrase("Date : "+today,FontLab.getBodyFont()));
			hcell.setBorderWidth(0);
			hcell.setColspan(3);
			hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			hcell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			headerTable.addCell(hcell);

		// Creating a paragraph object for adding the above generated table to the header.
			Paragraph para=new Paragraph();
			para.add(headerTable);

		/* Creating a header using the paragraph created above,the boolean value does
		 *not show the page number in the header.  */
			header=new HeaderFooter(para,false);

		}
		catch(Exception e)
		{
			logger.info("in HTML generator header"+e);
		}
		return header;
	}

//Function which generates a HTML footer object and returns it.

	public HeaderFooter genFooter()
	{
	/* Creating a footer,the boolean value does shows the page number in the footer.   */

		HeaderFooter footer=new HeaderFooter(new Phrase(""),true);
		footer.setBorder(Rectangle.NO_BORDER);
		return footer;
	}

/*Function which generates a HTML table object and returns it and also populates it
 *with the data provided.   */

	public Table tableBody(String colNames[], String data[][],int widths[],int alignment[])
	{

	/*Converting the integer array of widts to a String array so that it can be passed to the
	 * setWidth() methgod */

		String widthOfCell[]=new String[widths.length];
		for(int a=0;a<widths.length;a++)
		{
			widthOfCell[a]=(new Integer(widths[a])).toString();
		}

		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(colNames.length);
			bodyTable.setPadding(2.0f);
        	bodyTable.setBorderWidth(0);
//        	bodyTable.setWidth(100.0f);
        //cell created.
        	Cell bcell=new Cell();

		//The first row of the table containing the column names of the report required.
			for(int i=0;i<colNames.length;i++)
    	    {
        	   	bcell=new Cell(new Phrase(colNames[i],FontLab.getBodyHeaderFont()));
           		bcell.setBackgroundColor(FontLab.getHeaderBackColor());
//           		bcell.setBorderWidth(0);
				bcell.setWidth(widthOfCell[i]);
				bcell.setBorderColor(FontLab.getHeaderBorderColor());
				bcell.setHorizontalAlignment(alignment[i]);
				bodyTable.addCell(bcell);
           	}

        //The table containing the data for the report required
        	for (int i=0;i<data.length;i++)
        	{
        		for(int j=0;j<colNames.length;j++)
        		{
        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFont()));
           			bcell.setBorderWidth(0);
//           			bcell.setBorder(Rectangle.BOTTOM);
				    bcell.setBorderColor(FontLab.getBorderColor());
           			bcell.setHorizontalAlignment(alignment[j]);
           			bodyTable.addCell(bcell);
           		}
        	}

        }
        catch(Exception e)
        {
        	logger.info("in HTML generator tablebody"+e);
        }

		return bodyTable;
	}
	
	public Table tableBody(String data[][],int widths[],int alignment[])
	{

	/*Converting the integer array of widts to a String array so that it can be passed to the
	 * setWidth() methgod */

		String widthOfCell[]=new String[widths.length];
		for(int a=0;a<widths.length;a++)
		{
			widthOfCell[a]=(new Integer(widths[a])).toString();
		}

		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(alignment.length);
			bodyTable.setPadding(2.0f);
        	bodyTable.setBorderWidth(0);
        	bodyTable.setWidth(100.0f);
        //cell created.
        	Cell bcell=new Cell();


        //The table containing the data for the report required
        	for (int i=0;i<data.length;i++)
        	{
        		for(int j=0;j<alignment.length;j++)
        		{
        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFont()));
           			bcell.setBorderWidth(0);
//           			bcell.setBorder(Rectangle.BOTTOM);
				    bcell.setBorderColor(FontLab.getBorderColor());
           			bcell.setHorizontalAlignment(alignment[j]);
           			bodyTable.addCell(bcell);
           		}
        	}

        }
        catch(Exception e)
        {
        	logger.info("in HTML generator tablebody"+e);
        }

		return bodyTable;
	}
	
	public Table tableBodyNoBorder(String data[][],int widths[],int alignment[])
	{

	/*Converting the integer array of widts to a String array so that it can be passed to the
	 * setWidth() methgod */

		String widthOfCell[]=new String[widths.length];
		for(int a=0;a<widths.length;a++)
		{
			widthOfCell[a]=(new Integer(widths[a])).toString();
		}

		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(alignment.length);
			bodyTable.setPadding(2.0f);
        	bodyTable.setBorderWidth(0);
        	bodyTable.setWidth(100.0f);
        //cell created.
        	Cell bcell=new Cell();


        //The table containing the data for the report required
        	for (int i=0;i<data.length;i++)
        	{
        		for(int j=0;j<alignment.length;j++)
        		{
        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFont()));
           			bcell.setBorderWidth(0);
//           			bcell.setBorder(Rectangle.BOTTOM);
//				    bcell.setBorderColor(FontLab.getBorderColor());
           			bcell.setHorizontalAlignment(alignment[j]);
           			bodyTable.addCell(bcell);
           		}
        	}

        }
        catch(Exception e)
        {
        	logger.info("in HTML generator tablebody"+e);
        }

		return bodyTable;
	}
	
}