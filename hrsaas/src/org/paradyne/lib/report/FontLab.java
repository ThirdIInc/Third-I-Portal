/*
 * Code Written By Sreedhar K
 *
 *Dt:21-08-2004
 *
 */


package org.paradyne.lib.report;


import org.paradyne.lib.ModelBase;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;

/**
 *  A class for setting various fonts whenever and whereever required
 */

public class FontLab {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FontLab.class); 
/**
 * 
 * @param size
 * @param style
 * @param underline
 * @return
 */	
	
		public static Font getTimesFont(int size,int style, boolean underline) {
		/**
		 *		bold - 		1
		 *		bolditalic-	3
		 *		italic-		2
		 *		normal-		0
		 *		underline	4
		 */
		Font font = null;
		try{
			BaseFont bfComic = BaseFont.createFont("paralib/report/times.ttf", BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	        if(underline){
	        	font= new Font(bfComic, size, style|4 , new java.awt.Color(0, 0, 0));
	        } else {
	        	font= new Font(bfComic, size, style , new java.awt.Color(0, 0, 0));
	        }
	        return font;
		} catch(Exception e) {
			logger.error("bookman font not found");
			if(underline) {
				font = new Font(Font.TIMES_ROMAN , size, style|4 , new java.awt.Color(0, 0, 0));
			} else {
				font = new Font(Font.TIMES_ROMAN , size, style , new java.awt.Color(0, 0, 0));
			}
		}
		return font;
	}

	/**
	 * 
	 * @param size
	 * @param style
	 * @param underline
	 * @return
	 */	
	public static Font getBookmanFont(int size,int style, boolean underline) {
		/**
		 *		bold - 		1
		 *		bolditalic-	3
		 *		italic-		2
		 *		normal-		0
		 *		underline	4
		 */
		Font font = null;
		try{
			BaseFont bfComic = BaseFont.createFont("org/paradyne/lib/report/BOOKOS.TTF", BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	        if(underline){
	        	font= new Font(bfComic, size, style|4 , new java.awt.Color(0, 0, 0));
	        } else {
	        	font= new Font(bfComic, size, style , new java.awt.Color(0, 0, 0));
	        }
	        return font;
		} catch(Exception e) {
			logger.error("Bookman font not found");
			if(underline) {
				font = new Font(Font.HELVETICA, size, style|4 , new java.awt.Color(0, 0, 0));
			} else {
				font = new Font(Font.HELVETICA, size, style , new java.awt.Color(0, 0, 0));
			}
		}
		return font;
	}
/**
 * 
 * @return
 */
	public static Font getBodyFont() {
		Font font = new Font(Font.HELVETICA, 8, Font.NORMAL, new java.awt.Color(0, 0, 0));
		return font;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Font getBodyFont1() {
		Font font = new Font(Font.HELVETICA, 10, Font.BOLD, new java.awt.Color(0, 0, 0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getMiddleFont() {
		Font font = new Font(Font.HELVETICA, 8 , Font.NORMAL, new java.awt.Color(0, 0, 0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getBodyHeaderFont() {
		Font font = new Font(Font.HELVETICA, 10, Font.BOLD, new java.awt.Color(0,0,0));
		return font;
	}
	
	public static Font getBodyHeaderFont(int fontSize) {
		Font font = new Font(Font.HELVETICA, fontSize, Font.NORMAL, new java.awt.Color(0,0,0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getCompanyNameFont() {
		Font font = new Font(Font.HELVETICA, 18, Font.BOLDITALIC, new java.awt.Color(29,155, 165));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getReportNameFont() {
		Font font = new Font(Font.HELVETICA, 16, Font.BOLDITALIC, new java.awt.Color(0, 0, 0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getTotalFont() {
		Font font=new Font(Font.HELVETICA,18,Font.BOLD,new java.awt.Color(255,170,85));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getAddressFont() {
		Font font=new Font(Font.HELVETICA,8,Font.NORMAL,new java.awt.Color(0,0,0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getMatterFont() {
		Font font=new Font(Font.HELVETICA,10,Font.NORMAL,new java.awt.Color(0,0,0));
		return font;
	}
	
	/**
	 * 
	 * @return
	 */

	public static Font getItalicFont() {
		Font font=new Font(Font.HELVETICA,10,Font.ITALIC,new java.awt.Color(0,0,0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getLetterFont() {
		Font font=new Font(Font.HELVETICA,10,Font.BOLD,new java.awt.Color(0,0,0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getBoldFont() {
		Font font=new Font(Font.HELVETICA,10,Font.BOLD,new java.awt.Color(0,0,0));
		return font;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Font getBoldFont(int fontSize) {
		if(fontSize < 8)
			fontSize = 8;
		Font font=new Font(Font.HELVETICA,fontSize,Font.BOLD,new java.awt.Color(0,0,0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getBoldItalicFont() {
		Font font=new Font(Font.HELVETICA,10,Font.BOLDITALIC,new java.awt.Color(0,0,0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getNormalUnderlineFont() {
		Font font=new Font(Font.HELVETICA,10,Font.NORMAL|Font.UNDERLINE,new java.awt.Color(0,0,0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getBoldUnderlineFont() {
		Font font=new Font(Font.HELVETICA,10,Font.BOLD|Font.UNDERLINE,new java.awt.Color(0,0,0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getBoldUnderlineFont(int fontSize) {
		if(fontSize < 8) 
			fontSize=8;
		Font font=new Font(Font.HELVETICA,10,Font.BOLD|Font.UNDERLINE,new java.awt.Color(0,0,0));
		return font;
	}
/**
 * 
 * @param size
 * @param style
 * @param underline
 * @return
 */
	public static Font getWingdingFont(int size,int style, boolean underline) {
		Font font = null;
		try{
			BaseFont bfComic = BaseFont.createFont("org/paradyne/lib/report/wingding.ttf", BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	        font= new Font(bfComic, size, style , new java.awt.Color(0, 0, 0));        
	        return font;
		} catch(Exception e) {
			
			logger.error(e.getMessage());
		}
		return font;
	}
	
	
/**
 * A function for setting the background colour of the header of the table
 * @return
 */

	public static java.awt.Color getHeaderBackColor() {
		return new java.awt.Color(210,210,210);
	}
	
	public static java.awt.Color getBorderColor() {
		return new java.awt.Color(209,209,209);
	}

	public static java.awt.Color getHeaderBorderColor() {
		return new java.awt.Color(255,255,255);
	}
	/**
	 * //--------------------newly added for promotion by prakash---------------------------------------------------
	 * @return
	 */
	
	public static java.awt.Color getHeaderBorderColorPro() {
		return new java.awt.Color(0,0,0);
	}
	/**
	 * 
	 * @return
	 */
	public static java.awt.Color getBorderColor1() {
		return new java.awt.Color(0,0,0);
	}
	
	
	/**
	 * //------------------newly added for promotion by prakash---------------------------------
	 * @return
	 */
	public static Font getMatterFontPromo() {
		Font font=new Font(Font.HELVETICA,11,Font.NORMAL,new java.awt.Color(0,0,0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getBodyFontPromoSmall() {
		Font font = new Font(Font.HELVETICA, 11, Font.NORMAL, new java.awt.Color(0, 0, 0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getBoldaddText() {
		Font font=new Font(Font.HELVETICA,11,Font.BOLD,new java.awt.Color(0,0,0));
		return font;
	}
	
	/**
	 * 
	 */
	public static Font getBoldaddText(int fontSize) {
		Font font=new Font(Font.HELVETICA,fontSize,Font.BOLD,new java.awt.Color(0,0,0));
		return font;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Font getBodyHeaderFontPr() {
		Font font = new Font(Font.HELVETICA, 11, Font.BOLD, new java.awt.Color(0,0,0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getBodyFontPr() {
		Font font = new Font(Font.HELVETICA, 11, Font.NORMAL, new java.awt.Color(0, 0, 0));
		return font;
	}
	/**
	 * 
	 * @return
	 */
	public static Font getBodyFontPromo() {
		Font font = new Font(Font.HELVETICA, 11, Font.BOLD, new java.awt.Color(0, 0, 0));
		return font;
	}
	
	public static Font getBodyFontNormal(int size) {
		Font font = new Font(Font.HELVETICA, size, Font.NORMAL, new java.awt.Color(0, 0, 0));
		return font;
	}
	public static Font getBodyFontBold(int size) {
		Font font = new Font(Font.HELVETICA, size, Font.BOLD, new java.awt.Color(0, 0, 0));
		return font;
	}
}