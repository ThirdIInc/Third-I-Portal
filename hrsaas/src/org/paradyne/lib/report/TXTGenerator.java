

package org.paradyne.lib.report;




import org.paradyne.lib.*;

import org.paradyne.lib.report.*;



import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.rtf.RtfWriter;
import com.lowagie.text.rtf.RtfHeaderFooters;

import com.lowagie.text.rtf.RtfTable;
import com.lowagie.text.rtf.RtfTableCell;


/*
 * Code Written By Sreedhar K
 *
 *Dt:21-08-2004
 *
 */
// A class which  generates the report in word format

public class TXTGenerator
{
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TXTGenerator.class); 
/**
 * Function which generates a TEXT header object and returns it.
 * @param coname
 * @param today
 * @param repname
 * @param img
 * @param address
 * @param width
 * @return
 */
	public RtfHeaderFooters genHeader(String coname,String today,String repname,String img,String address,int width)
	{
		String str="";
		for(int i=0;i<(width/8);i++)
		str=str+" ";

		RtfHeaderFooters rtfHeader=null;
		try
		{

		// Creating phrases which contain the company name,report name and the date.
			Phrase ph1=new Phrase(coname+"\n",FontLab.getCompanyNameFont());
//			Phrase ph2=new Phrase(address+"\n",FontLab.getAddressFont());
			Phrase phr=new Phrase(repname+"\n",FontLab.getReportNameFont());
			Phrase ph3=new Phrase(str);
			ph3.add(phr);
			Phrase ph4=new Phrase("Date :"+today,FontLab.getAddressFont());

			Paragraph para=new Paragraph();
			para.add(ph1);
//			para.add(ph2);
			para.add(ph3);
			para.add(ph4);


		/* Creating a header using the paragraph created above,the boolean value does
		 *not show the page number in the header.  */


			rtfHeader=new RtfHeaderFooters(para,false);
			rtfHeader.set(RtfHeaderFooters.ALL_PAGES,rtfHeader);
		}
		catch(Exception e)
		{
			logger.info("in Txt generator header"+e);
		}
		return rtfHeader;
	}

/**
 * Function which generates a TEXT footer object and returns it.
 * @return headerfooter object
 */

	public HeaderFooter genFooter()
	{
	/* Creating a footer,the boolean value does shows the page number in the footer.   */
		HeaderFooter footer=new HeaderFooter(new Phrase("Page No : "),true);
//		RtfHeaderFooters footer=new RtfHeaderFooters(new Phrase(""),true);
		footer.setAlignment(1);
		footer.setBorder(Rectangle.NO_BORDER);
		return footer;
	}



/**
 * Function which generates a TEXT table object and returns it and also populates it
 * with data provided
 * @param colNames
 * @param data
 * @param widths
 * @param alignment
 * @return
 */
	public Table tableBody(String colNames[], String data[][], int widths[],int alignment[])
	{
		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(colNames.length);
        	bodyTable.setBorder(Rectangle.NO_BORDER);
        	bodyTable.setPadding(2.0f);
        	bodyTable.setWidths(widths);
//        	bodyTable.setWidth(92.0f);

        //cell created.
        	Cell bcell=new Cell();

		//The first row of the table containing the column names of the report required.
			for(int i=0;i<colNames.length;i++)
    	    {
        	   	//bcell=new Cell(new Phrase(colNames[i],FontLab.getBodyHeaderFont()));
        	   	bcell=new Cell(new Phrase(colNames[i],FontLab.getBookmanFont(8,1,false)));
//           		bcell.setBorder(Rectangle.NO_BORDER);
				bcell.setBorderColor(FontLab.getHeaderBorderColor());
           		bcell.setHorizontalAlignment(alignment[i]);
           		bcell.setBackgroundColor(FontLab.getHeaderBackColor());
           		bodyTable.addCell(bcell);
           	}

        //The table containing the data for the report required
        	for (int i=0;i<data.length;i++)
           	{
           		for(int j=0;j<colNames.length;j++)
        		{
        			//bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFont()));
        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBookmanFont(8,0,false)));
//           			bcell.setBorder(Rectangle.NO_BORDER);
//           			bcell.setBorder(Rectangle.BOTTOM);
				 	bcell.setBorderColor(FontLab.getBorderColor());
           			bcell.setHorizontalAlignment(alignment[j]);
           		
           			bodyTable.addCell(bcell);
           			
           		}
        	}
        }
        catch(Exception e)
        {
        	logger.info("in Txt generator tablebody "+e);
        }

		return bodyTable;
	}
	/**
	 * Function which generates a TEXT table object and returns it and also populates it
	 * @param colNames
	 * @param data
	 * @param widths
	 * @param alignment
	 * @param rg
	 * @return
	 */
	public Table tableBody(String colNames[], String data[][], int widths[],int alignment[],ReportGenerator rg)
	{
		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(colNames.length);
		
        	bodyTable.setBorder(Rectangle.NO_BORDER);
        	bodyTable.setPadding(2.0f);
        	bodyTable.setWidths(widths);
        	bodyTable.setWidth(100.00f);
//        	bodyTable.setWidth(92.0f);

        //cell created.
        	Cell bcell=new Cell();

		//The first row of the table containing the column names of the report required.
        	for(int i=0;i<colNames.length;i++)
	    	    {
	        	   	//bcell=new Cell(new Phrase(colNames[i],FontLab.getBodyHeaderFont()));
	        	   	bcell=new Cell(new Phrase(colNames[i],FontLab.getBookmanFont(9,1,false)));
	//           		bcell.setBorder(Rectangle.NO_BORDER);
					bcell.setBorderColor(FontLab.getHeaderBorderColor());
	           		bcell.setHorizontalAlignment(alignment[i]);
	           		bcell.setBorderColor(FontLab.getBorderColor());
	           		bodyTable.addCell(bcell);
	           	}
		

        //The table containing the data for the report required
        	for (int i=0;i<data.length;i++)
           	{
           		for(int j=0;j<colNames.length;j++)
        		{
        			//bcell=new Cell(new Phrase(data[i][j],FontLab.getBodyFont()));
        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBookmanFont(9,0,false)));
//           			bcell.setBorder(Rectangle.NO_BORDER);
//           			bcell.setBorder(Rectangle.BOTTOM);
				 	bcell.setBorderColor(FontLab.getBorderColor());
           			bcell.setHorizontalAlignment(alignment[j]);
           		
           			bodyTable.addCell(bcell);
           			
           		}
        	}
        }
        catch(Exception e)
        {
        	logger.info("in Txt generator tablebody "+e);
        }

		return bodyTable;
	}

	/**
	 * Function which generates a TEXT table object and returns it and also populates it
	 * @param data
	 * @param widths
	 * @param alignment
	 * @return
	 */
	public Table tableBody( String data[][], int widths[],int alignment[])
	{
		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(alignment.length);
        	bodyTable.setBorder(Rectangle.NO_BORDER);
        	bodyTable.setPadding(2.0f);
        	bodyTable.setWidths(widths);
//        	bodyTable.setWidth(92.0f);

        //cell created.
        	Cell bcell=new Cell();

        //The table containing the data for the report required
        	for (int i=0;i<data.length;i++)
           	{
           		for(int j=0;j<alignment.length;j++)
        		{
        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBookmanFont(9,0,false)));
//           			bcell.setBorder(Rectangle.NO_BORDER);
//           			bcell.setBorder(Rectangle.BOTTOM);
				 	bcell.setBorderColor(FontLab.getBorderColor());
           			bcell.setHorizontalAlignment(alignment[j]);
           			bodyTable.addCell(bcell);
           		}
        	}
        }
        catch(Exception e) {
        	logger.info("in Txt generator tablebody "+e);
        }
		return bodyTable;
	}
	/**
	 * Function which generates a TEXT table object and returns it and also populates it
	 * @param data
	 * @param widths
	 * @param alignment
	 * @param rg
	 * @return
	 */
	public Table tableBody( String data[][], int widths[],int alignment[],ReportGenerator rg)
	{
		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(alignment.length);
        	bodyTable.setBorder(Rectangle.NO_BORDER);
        	bodyTable.setPadding(2.0f);
        	bodyTable.setWidths(widths);
        	bodyTable.setWidth(100.00f);

        //cell created.
        	Cell bcell=new Cell();

        //The table containing the data for the report required
        	for (int i=0;i<data.length;i++)
           	{
           		for(int j=0;j<alignment.length;j++)
        		{
        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBookmanFont(9,0,false)));
//           			bcell.setBorder(Rectangle.NO_BORDER);
//           			bcell.setBorder(Rectangle.BOTTOM);
				 	bcell.setBorderColor(FontLab.getBorderColor());
           			bcell.setHorizontalAlignment(alignment[j]);
           			bodyTable.addCell(bcell);
           		}
        	}
        }
        catch(Exception e) {
        	logger.info("in Txt generator tablebody "+e);
        }
		return bodyTable;
	}

/**
 * Function which generates a TEXT table object and returns it and also populates it and wihout border
 * @param data
 * @param widths
 * @param alignment
 * @return
 */
	public Table tableBodyNoBorder(String data[][], int widths[],int alignment[])
	{
		Table bodyTable=null;
		try
		{
		//table created.
			bodyTable=new Table(alignment.length);
        	bodyTable.setBorder(Rectangle.NO_BORDER);
        	bodyTable.setPadding(2.0f);
        	bodyTable.setWidths(widths);
//        	bodyTable.setWidth(92.0f);

        //cell created.
        	Cell bcell=new Cell();

        //The table containing the data for the report required
        	for (int i=0;i<data.length;i++)
           	{
           		for(int j=0;j<alignment.length;j++)
        		{
        			bcell=new Cell(new Phrase(data[i][j],FontLab.getBookmanFont(9,0,false)));
           			bcell.setBorder(Rectangle.NO_BORDER);
//           			bcell.setBorder(Rectangle.BOTTOM);
//				 	bcell.setBorderColor(FontLab.getBorderColor());
           			bcell.setHorizontalAlignment(alignment[j]);
           			bodyTable.addCell(bcell);
           		}
        	}
        }
        catch(Exception e)
        {
        	logger.info("in Txt generator tablebody "+e);
        }
		return bodyTable;
	}
}