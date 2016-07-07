/*
 * 	Coded  By 	:  Balaji Mane & Modified By Vishwambhar Deshpande
 *	Date    	:  20-11-2009					
 *
 */
package org.paradyne.lib.ireportV2;

import java.util.HashMap;
import org.paradyne.lib.Utility;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

public class TXTGenerator {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PDFGenerator.class);
	String elementTag = "";
	boolean txtHorizantalFlag = false;
	boolean txtVerticalFlag = false;

	/**
	 * In constructor creating the word document and including the xml name
	 * spaces for different tags.
	 */
	public TXTGenerator(ReportDataSet rdsDoc) {
		if (elementTag.equals("")) {
			elementTag = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><?mso-application progid='Word.Document'?>"
					+ "<w:wordDocument "
					+ "xmlns:w='http://schemas.microsoft.com/office/word/2003/wordml' "
					+ "xmlns:v='urn:schemas-microsoft-com:vml' "
					+ "xmlns:w10='urn:schemas-microsoft-com:office:word' "
					+ "xmlns:sl='http://schemas.microsoft.com/schemaLibrary/2003/core' "
					+ "xmlns:aml='http://schemas.microsoft.com/aml/2001/core' "
					+ "xmlns:wx='http://schemas.microsoft.com/office/word/2003/auxHint' "
					+ "xmlns:o='urn:schemas-microsoft-com:office:office' "
					+ "xmlns:dt='uuid:C2F41010-65B3-11d1-A29F-00AA00C14882' "
					+ "w:macrosPresent='no' w:embeddedObjPresent='no' w:ocxPresent='no' "
					+ "xml:space='preserve'> ";
		}
	}

	/**
	 * In this method creating the table . according to the setting values in
	 * TableDataset for header and data will display like font size ,font style
	 * ,Background color,font color,adding the blank row , row sum , column sum
	 * etc features added.
	 * 
	 * @param tds
	 * @return String
	 */
	public String createDocTable(org.paradyne.lib.ireportV2.TableDataSet tds) {
		String elementTableTag = "";
		// for above blank row
		String blankRowAbove = " <w:tbl>";
		if (tds.getBlankRowsAbove() > 0) {
			for (int i = 0; i < tds.getBlankRowsAbove(); i++) {
				blankRowAbove += "<w:tr>";

				for (int j = 0; j < tds.getData()[0].length; j++) {
					blankRowAbove += "<w:tc> <w:p> <w:r> <w:t></w:t> </w:r> </w:p> </w:tc>";
				}
				blankRowAbove += "</w:tr>";
			}
			blankRowAbove += "</w:tbl>  ";

			elementTableTag += blankRowAbove;
		}

		elementTableTag += " <w:tbl> ";
		if (tds.getBorder() == true) {
			if (tds.getTablewidth() < 100) {
				elementTableTag += "<w:tblPr><w:tblW w:w='3500' w:type='pct' /> ";
			} else {
				elementTableTag += "<w:tblPr><w:tblW w:w='0' w:type='auto' /> ";
			}
			elementTableTag += "<w:jc w:val='center'/>";
			elementTableTag += "<w:tblBorders><w:top    	w:val='single' w:sz='8' wx:bdrwidth='20' w:space='0' "
					+ "				w:color='auto' /><w:left w:val='single' "
					+ "				w:sz='8' wx:bdrwidth='20' w:space='0' w:color='auto' /><w:bottom "
					+ "				w:val='single' w:sz='8' wx:bdrwidth='20' w:space='0' "
					+ "				w:color='auto' /><w:right w:val='single' "
					+ "				w:sz='8' wx:bdrwidth='20' w:space='0' w:color='auto' /><w:insideH "
					+ "				w:val='single' w:sz='8' wx:bdrwidth='20' w:space='0' "
					+ "				w:color='auto' /><w:insideV w:val='single' "
					+ "				w:sz='8' wx:bdrwidth='20' w:space='0' w:color='auto' /> "
					+ "</w:tblBorders><w:tblCellMar>  "
					+ "<w:left w:w='10' w:type='dxa' /><w:right w:w='10' w:type='dxa' /> "
					+ "</w:tblCellMar> "
					+ "<w:tblLook w:val='01E0' /> "
					+ "</w:tblPr>";
		}

		String[] header = tds.getHeader();
		Object[][] data = tds.getData();

		int[] width = tds.getCellWidth();
		if (width != null) {
			for (int i = 0; i < width.length; i++) {
				width[i] = width[i] * 150;
			}
		}

		int[] alignDigit = tds.getCellAlignment();
		String[] cellAlign = null;
		if (width != null) {
			cellAlign = new String[width.length];
		} else {
			cellAlign = new String[data[0].length];
		}

		if (width != null && width.length > 0) // if specific alignment is
												// given
		{
			for (int i = 0; i < cellAlign.length; i++) {
				if (alignDigit[i] == 0)
					cellAlign[i] = "left";
				if (alignDigit[i] == 1)
					cellAlign[i] = "center";
				if (alignDigit[i] == 2)
					cellAlign[i] = "right";
			}
		} else {
			if (data != null && data.length > 0) // if alignment is not given
													// put data left
			{
				for (int i = 0; i < data[0].length; i++) {
					cellAlign[i] = "left";
				}
			}

		}

		// for setting the Header Value table
		// int headerBodyFont =tds.getHeaderFont();
		int headerBodyFontSize = 16;
		if ((int) tds.getHeaderFontSize() != -1) {
			headerBodyFontSize = (((int) tds.getHeaderFontSize()) * 2);
		}
		String headerBodyFontStyle = "";
		if ((int) tds.getHeaderFontStyle() != -1) {
			if ((int) tds.getHeaderFontStyle() == 1)
				headerBodyFontStyle = "<w:b/>";
			if ((int) tds.getHeaderFontStyle() == 2)
				headerBodyFontStyle = "<w:i/>";
			if ((int) tds.getHeaderFontStyle() == 3)
				headerBodyFontStyle = "<w:b/><w:u/>";
			if ((int) tds.getHeaderFontStyle() == 3)
				headerBodyFontStyle = "<w:u/>";
		}
		// Color headerBGcolor =null;
		BaseColor headerFontColor = null;
		if (tds.getHeaderFontColor() == null) {
			headerFontColor = new BaseColor(0, 0, 0);
		} else {
			headerFontColor = tds.getHeaderFontColor();
		}

		int tempOne = 1;
		int tempTwo = 1;
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

		if (rowsSum != null && rowsAvg != null) {
			tempOne = 1;
			tempTwo = 2;
		}
		double rowSumOut = 0.0;
		double rowAvgOut = 0.0;

		int headerFontSize = 16;
		System.out.println("headerFontSize  " + headerFontSize);
		BaseColor clrHeader = tds.getHeaderBGColor();// background color for cell

		Font headerFont = tds.getHeaderFontDetails();
		int headerFontStyle = tds.getHeaderFontStyle();

		if (header != null && header.length > 0) {
			String header_Font = "";
			String header_color = "";
			// font mentioned or default font for the table headings
			if (tds.getHeaderFontSize() != -1) {
				headerFontSize = tds.getHeaderFont();
			}
			if (!(headerFont.getFamily()==null)) {
				header_Font = RGBtoHex(headerFont.getColor());
			} else {
				header_Font = "000000";
			}
			if (clrHeader == null) {
				header_color = "FFFFFF";
			} else {
				header_color = RGBtoHex(clrHeader);
			}
			elementTableTag += "<w:tr>";
			for (int i = 0; i < header.length; i++) {
				elementTableTag += "<w:tc> " + "<w:tcPr>" + "<w:tcW w:w='"
						+ width[i] + "' w:type='dxa' /> "
						+ "<w:vAlign w:val='center' />"
						+ " <w:shd w:val='clear' w:color='auto' w:fill='#"
						+ header_color + "' />" + "</w:tcPr>" + "<w:p>"
						+ "<w:pPr>" + "<w:jc w:val='center'/>" + "</w:pPr>"
						+ "<w:r>" + "<w:rPr><w:color w:val='" + header_Font
						+ "'/><w:b/></w:rPr>" + "<w:t><w:rPr><w:sz w:val='"
						+ headerBodyFontSize + "'/><w:sz-cs w:val='"
						+ headerFontSize + "'/> " + headerBodyFontStyle
						+ "</w:rPr>" + header[i] + "</w:t> " + " </w:r> "
						+ "</w:p> " + " </w:tc>";
			}
			elementTableTag += "</w:tr> ";
		}

		// for setting the data value of table
		// int dataBodyFont =tds.getBodyFont();
		int dataBodyFontSize = 16;
		if ((int) tds.getBodyFontSize() != -1) {
			dataBodyFontSize = (((int) tds.getBodyFontSize()) * 2);
		}
		String dataBodyFontStyle = "";
		if ((int) tds.getBodyFontStyle() != -1) {
			if ((int) tds.getBodyFontStyle() == 1)
				dataBodyFontStyle = "<w:b/>";
			if ((int) tds.getBodyFontStyle() == 2)
				dataBodyFontStyle = "<w:i/>";
			if ((int) tds.getBodyFontStyle() == 3)
				dataBodyFontStyle = "<w:b/><w:u/>";
			if ((int) tds.getBodyFontStyle() == 3)
				dataBodyFontStyle = "<w:u/>";
		}
		BaseColor dataBGcolor = null;
		BaseColor dataFontColor = null;
		if (tds.getBodyFontColor() == null) {
			dataFontColor = new BaseColor(0, 0, 0);
		} else {
			dataFontColor = tds.getBodyFontColor();
		}

		if (tds.getBodyBGColor() == null) {
			dataBGcolor = new BaseColor(255, 255, 255);
		} else {
			dataBGcolor = tds.getBodyBGColor();
		}

		BaseColor clrBody = tds.getBodyBGColor();// background color for cell
		String body_color = "";
		if (clrBody == null) {
			body_color = "FFFFFF";
		} else {
			body_color = RGBtoHex(clrBody);
		}

		Object[] columColorData = tds.getColumnColor();// if some colum
														// required special
														// color
		// column no which is to be colored
		Font bodyFont = null;
		bodyFont = tds.getBodyFontDetails();// font 4 body of the table with

		Font sumFont = tds.getSumFontDetails();

		int colNo = Integer.parseInt(String.valueOf(columColorData[0]));
		BaseColor colBackColor = (BaseColor) columColorData[1];// background color
		Font colFontColor = (Font) columColorData[2];// fontcolor of that//
														// column

		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				String column_fontcolor = "";
				String sumfontcolor = "000000";
				String columnBackColor = "FFFFFF";
				elementTableTag += "<w:tr>";
				for (int j = 0; j < cellAlign.length; j++) {
					if (!(rowsAvg == null) && j == cellAlign.length - tempOne) {

						if (!(sumFont.getFamily()==null)) {
							sumfontcolor = RGBtoHex(sumFont.getColor());
						}
						elementTableTag += "<w:tc> " + "<w:tcPr>"
								+ "<w:tcW w:w='"
								+ width[j]
								+ "' w:type='dxa' /> "
								+ "<w:vAlign w:val='center' />"
								+ " <w:shd w:val='clear' w:color='auto' w:fill='#"
								+ Integer
										.toHexString(dataBGcolor.getRGB() & 0x00ffffff)
								+ "' />"
								+ "</w:tcPr>"
								+ "<w:p>"
								+ "<w:pPr>"
								+ "<w:jc w:val='"
								+ cellAlign[j]
								+ "'/>"
								+ "</w:pPr>"
								+ "<w:r>"
								+ "<w:rPr><w:color w:val='"
								+ sumfontcolor
								+ "'/></w:rPr>"
								+ "<w:t><w:rPr><w:sz w:val='"
								+ dataBodyFontSize
								+ "'/><w:sz-cs w:val='"
								+ dataBodyFontSize
								+ "'/> "
								+ dataBodyFontStyle
								+ "</w:rPr>"
								+ checkNull(String
										.valueOf(Utility.twoDecimals(rowAvgOut
												/ rowsAvg.length)))
								+ "</w:t> "
								+ " </w:r> " + "</w:p> " + " </w:tc> ";

					} else if (!(rowsSum == null)
							&& j == cellAlign.length - tempTwo) {

						if (!(sumFont.getFamily()==null)) {
							sumfontcolor = RGBtoHex(sumFont.getColor());

						}
						elementTableTag += "<w:tc> " + "<w:tcPr>"
								+ "<w:tcW w:w='"
								+ width[j]
								+ "' w:type='dxa' /> "
								+ "<w:vAlign w:val='center' />"
								+ " <w:shd w:val='clear' w:color='auto' w:fill='#"
								+ Integer
										.toHexString(dataBGcolor.getRGB() & 0x00ffffff)
								+ "' />"
								+ "</w:tcPr>"
								+ "<w:p>"
								+ "<w:pPr>"
								+ "<w:jc w:val='"
								+ cellAlign[j]
								+ "'/>"
								+ "</w:pPr>"
								+ "<w:r>"
								+ "<w:rPr><w:color w:val='"
								+ Integer
										.toHexString(dataFontColor.getRGB() & 0x00ffffff)
								+ "'/></w:rPr>"
								+ "<w:t><w:rPr><w:sz w:val='"
								+ dataBodyFontSize
								+ "'/><w:sz-cs w:val='"
								+ dataBodyFontSize
								+ "'/> "
								+ dataBodyFontStyle
								+ "</w:rPr>"
								+ checkNull(String.valueOf(rowSumOut))
								+ "</w:t> "
								+ " </w:r> "
								+ "</w:p> "
								+ " </w:tc> ";

					} else {

						if ((!(colNo == -1)) && j == colNo) {

							column_fontcolor = RGBtoHex(colFontColor.getColor());
							columnBackColor = RGBtoHex(colBackColor);
							/*
							 * createHtmlTableStrBuffer.append("<td width='" +
							 * width[j] + "%' align='" + cellAlign[j] + "'
							 * style='BACKGROUND-COLOR: #"+columnBackColor+"'>" +"<font
							 * color='#" + sumfontcolor + "'>" +
							 * replaceNewLineCharWithBr(checkNull(String.valueOf(data[i][j]))) +
							 * "&nbsp;</font>" + "&nbsp;</td>");
							 */
							/*
							 * if(data[i][j] instanceof Image){ bcell = new
							 * PdfPCell((Image)data[i][j]); } else { bcell = new
							 * PdfPCell(new
							 * Phrase(checkNull(String.valueOf(data[i][j])),
							 * colFontColor)); }
							 * bcell.setBackgroundColor(colBackColor);
							 */

							elementTableTag += "<w:tc> " + "<w:tcPr>"
									+ "<w:tcW w:w='"
									+ width[j]
									+ "' w:type='dxa' /> "
									+ "<w:vAlign w:val='center' />"
									+ " <w:shd w:val='clear' w:color='auto' w:fill='#"
									+ columnBackColor
									+ "' />"
									+ "</w:tcPr>"
									+ "<w:p>"
									+ "<w:pPr>"
									+ "<w:jc w:val='"
									+ cellAlign[j]
									+ "'/>"
									+ "</w:pPr>"
									+ "<w:r>"
									+ "<w:rPr><w:color w:val='"
									+ column_fontcolor
									+ "'/></w:rPr>"
									+ "<w:t><w:rPr><w:sz w:val='"
									+ dataBodyFontSize
									+ "'/><w:sz-cs w:val='"
									+ dataBodyFontSize
									+ "'/> "
									+ dataBodyFontStyle
									+ "</w:rPr>"
									+ checkNull(String.valueOf(data[i][j]))
									+ "</w:t> "
									+ " </w:r> "
									+ "</w:p> "
									+ " </w:tc> ";

						}

						else {

							if (!(bodyFont.getFamily() ==null)) {
								// font mentioned for table data else take
								/*
								 * // default font if(data[i][j] instanceof
								 * com.lowagie.text.Image){ bcell = new
								 * PdfPCell((Image)data[i][j]); } else { bcell =
								 * new PdfPCell(new
								 * Phrase(checkNull(String.valueOf(data[i][j])),
								 * bodyFont)); }
								 */

								elementTableTag += "<w:tc> " + "<w:tcPr>"
										+ "<w:tcW w:w='"
										+ width[j]
										+ "' w:type='dxa' /> "
										+ "<w:vAlign w:val='center' />"
										+ " <w:shd w:val='clear' w:color='auto' w:fill='#"
										+ Integer.toHexString(dataBGcolor
												.getRGB() & 0x00ffffff)
										+ "' />"
										+ "</w:tcPr>"
										+ "<w:p>"
										+ "<w:pPr>"
										+ "<w:jc w:val='"
										+ cellAlign[j]
										+ "'/>"
										+ "</w:pPr>"
										+ "<w:r>"
										+ "<w:rPr><w:shd w:val='clear' w:color='auto' w:fill='#"+ body_color+ "'/></w:rPr>"
										+ "<w:t><w:rPr><w:sz w:val='"
										+ dataBodyFontSize
										+ "'/><w:sz-cs w:val='"
										+ dataBodyFontSize
										+ "'/> "
										+ dataBodyFontStyle
										+ "</w:rPr>"
										+ checkNull(String.valueOf(data[i][j]))
										+ "</w:t> "
										+ " </w:r> "
										+ "</w:p> "
										+ " </w:tc> ";

							} else {
								// logger.info("font not found");
								try {

									// bcell = new PdfPCell(new
									// Phrase(checkNull(String.valueOf(data[i][j])),
									// new Font(Font.HELVETICA, 8,
									// Font.NORMAL,new java.awt.Color(0, 0,
									// 0))));
									elementTableTag += "<w:tc> " + "<w:tcPr>"
											+ "<w:tcW w:w='"
											+ width[j]
											+ "' w:type='dxa' /> "
											+ "<w:vAlign w:val='center' />"
											+ " <w:shd w:val='clear' w:color='auto' w:fill='#"
											+ Integer.toHexString(dataBGcolor
													.getRGB() & 0x00ffffff)
											+ "' />"
											+ "</w:tcPr>"
											+ "<w:p>"
											+ "<w:pPr>"
											+ "<w:jc w:val='"
											+ cellAlign[j]
											+ "'/>"
											+ "</w:pPr>"
											+ "<w:r>"
											+ "<w:rPr><w:color w:val='"
											+ Integer.toHexString(dataFontColor
													.getRGB() & 0x00ffffff)
											+ "'/></w:rPr>"
											+ "<w:t><w:rPr><w:sz w:val='"
											+ dataBodyFontSize
											+ "'/><w:sz-cs w:val='"
											+ dataBodyFontSize
											+ "'/> "
											+ dataBodyFontStyle
											+ "</w:rPr>"
											+ checkNull(String
													.valueOf(data[i][j]))
											+ "</w:t> "
											+ " </w:r> "
											+ "</w:p> " + " </w:tc> ";

								} catch (Exception e) {
									logger.info("error in setting image " + e);
								}
							}
						}
						// rowwise sum for mentioned rows
						if (!(rowsSum == null)) {
							for (int l = 0; l < rowsSum.length; l++) {

								if (j == rowsSum[l]) {
									if (!String.valueOf(data[i][j]).equals(""))
										rowSumOut = rowSumOut
												+ Double
														.parseDouble(checkZero(String
																.valueOf(data[i][j])));
								}
							}
						}
						if (!(rowsAvg == null)) {
							for (int l = 0; l < rowsAvg.length; l++) {

								if (j == rowsAvg[l]) {
									if (!String.valueOf(data[i][j]).equals(""))
										rowAvgOut = rowAvgOut
												+ Double
														.parseDouble(checkZero(String
																.valueOf(data[i][j])));
								}
							}
						}
						// columwise sum for mentioned columns
						if (!(colsSum == null)) {
							for (int k = 0; k < colsSum.length; k++) {

								if (j == colsSum[k]) {
									if (!String.valueOf(data[i][j]).equals(""))
										sumOut[k] = sumOut[k]
												+ Double
														.parseDouble(checkZero(String
																.valueOf(data[i][j])));
								}
							}

						}
						// columwise average for mentioned columns
						if (!(colsAvg == null)) {
							for (int k = 0; k < colsAvg.length; k++) {

								if (j == colsAvg[k]) {
									if (!String.valueOf(data[i][j]).equals(""))
										avgOut[k] = avgOut[k]
												+ Double
														.parseDouble(checkZero(String
																.valueOf(data[i][j])));
								}
							}

						}
					}

				}
				rowSumOut = 0.00;
				rowAvgOut = 0.00;
				elementTableTag += "</w:tr>";
			} // end of row
			// elementTableTag+="</w:tr>";
		}
		if (!(colsSum == null)) {
			int k = 0;
			elementTableTag += "<w:tr>";
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < cellAlign.length; j++) {
					if (k < colsSum.length) {
						if (j == colsSum[k]) {
							/*
							 * if (!(sumFont.family() == -1)) {
							 * colAvgFontColor=RGBtoHex(sumFont.color()); } else {
							 * colAvgFontColor="000000"; }
							 */
							elementTableTag += "<w:tc> " + "<w:tcPr>"
									+ "<w:tcW w:w='"
									+ width[j]
									+ "' w:type='dxa' /> "
									+ "<w:vAlign w:val='center' />"
									+ " <w:shd w:val='clear' w:color='auto' w:fill='#"
									+ Integer
											.toHexString(dataBGcolor.getRGB() & 0x00ffffff)
									+ "' />"
									+ "</w:tcPr>"
									+ "<w:p>"
									+ "<w:pPr>"
									+ "<w:jc w:val='"
									+ cellAlign[j]
									+ "'/>"
									+ "</w:pPr>"
									+ "<w:r>"
									+ "<w:rPr><w:color w:val='"
									+ Integer.toHexString(dataFontColor
											.getRGB() & 0x00ffffff)
									+ "'/></w:rPr>"
									+ "<w:t><w:rPr><w:sz w:val='"
									+ dataBodyFontSize
									+ "'/><w:sz-cs w:val='"
									+ dataBodyFontSize
									+ "'/> "
									+ dataBodyFontStyle
									+ "</w:rPr>"
									+ checkNull(String.valueOf(Utility
											.twoDecimals(sumOut[k])))
									+ "</w:t> "
									+ " </w:r> "
									+ "</w:p> "
									+ " </w:tc> ";
							k++;
						} else {
							elementTableTag += "<w:tc> " + "<w:tcPr>"
									+ "<w:tcW w:w='"
									+ width[j]
									+ "' w:type='dxa' /> "
									+ "<w:vAlign w:val='center' />"
									+ " <w:shd w:val='clear' w:color='auto' w:fill='#"
									+ Integer
											.toHexString(dataBGcolor.getRGB() & 0x00ffffff)
									+ "' />"
									+ "</w:tcPr>"
									+ "<w:p>"
									+ "<w:pPr>"
									+ "<w:jc w:val='"
									+ cellAlign[j]
									+ "'/>"
									+ "</w:pPr>"
									+ "<w:r>"
									+ "<w:rPr><w:color w:val='"
									+ Integer.toHexString(dataFontColor
											.getRGB() & 0x00ffffff)
									+ "'/></w:rPr>"
									+ "<w:t><w:rPr><w:sz w:val='"
									+ dataBodyFontSize
									+ "'/><w:sz-cs w:val='"
									+ dataBodyFontSize
									+ "'/> "
									+ dataBodyFontStyle
									+ "</w:rPr>"
									+ checkNull(String.valueOf(""))
									+ "</w:t> "
									+ " </w:r> " + "</w:p> " + " </w:tc> ";

						}
					} else {
						elementTableTag += "<w:tc> " + "<w:tcPr>"
								+ "<w:tcW w:w='"
								+ width[j]
								+ "' w:type='dxa' /> "
								+ "<w:vAlign w:val='center' />"
								+ " <w:shd w:val='clear' w:color='auto' w:fill='#"
								+ Integer
										.toHexString(dataBGcolor.getRGB() & 0x00ffffff)
								+ "' />"
								+ "</w:tcPr>"
								+ "<w:p>"
								+ "<w:pPr>"
								+ "<w:jc w:val='"
								+ cellAlign[j]
								+ "'/>"
								+ "</w:pPr>"
								+ "<w:r>"
								+ "<w:rPr><w:color w:val='"
								+ Integer
										.toHexString(dataFontColor.getRGB() & 0x00ffffff)
								+ "'/></w:rPr>"
								+ "<w:t><w:rPr><w:sz w:val='"
								+ dataBodyFontSize
								+ "'/><w:sz-cs w:val='"
								+ dataBodyFontSize
								+ "'/> "
								+ dataBodyFontStyle
								+ "</w:rPr>"
								+ checkNull(String.valueOf(""))
								+ "</w:t> "
								+ " </w:r> "
								+ "</w:p> "
								+ " </w:tc> ";

					}
				}
			}
			elementTableTag += "</w:tr>";
		}

		if (!(colsAvg == null)) {
			int k = 0;
			elementTableTag += "<w:tr>";
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < cellAlign.length; j++) {
					if (k < colsAvg.length) {
						if (j == colsAvg[k]) {
							/*
							 * if (!(sumFont.family() == -1)) {
							 * colAvgFontColor=RGBtoHex(sumFont.color()); } else {
							 * colAvgFontColor="000000"; }
							 */
							elementTableTag += "<w:tc> " + "<w:tcPr>"
									+ "<w:tcW w:w='"
									+ width[j]
									+ "' w:type='dxa' /> "
									+ "<w:vAlign w:val='center' />"
									+ " <w:shd w:val='clear' w:color='auto' w:fill='#"
									+ Integer
											.toHexString(dataBGcolor.getRGB() & 0x00ffffff)
									+ "' />"
									+ "</w:tcPr>"
									+ "<w:p>"
									+ "<w:pPr>"
									+ "<w:jc w:val='"
									+ cellAlign[j]
									+ "'/>"
									+ "</w:pPr>"
									+ "<w:r>"
									+ "<w:rPr><w:color w:val='"
									+ Integer.toHexString(dataFontColor
											.getRGB() & 0x00ffffff)
									+ "'/></w:rPr>"
									+ "<w:t><w:rPr><w:sz w:val='"
									+ dataBodyFontSize
									+ "'/><w:sz-cs w:val='"
									+ dataBodyFontSize
									+ "'/> "
									+ dataBodyFontStyle
									+ "</w:rPr>"
									+ checkNull(String.valueOf(Utility
											.twoDecimals(avgOut[k]
													/ colsAvg.length)))
									+ "</w:t> "
									+ " </w:r> "
									+ "</w:p> "
									+ " </w:tc> ";
							k++;
						} else {
							elementTableTag += "<w:tc> " + "<w:tcPr>"
									+ "<w:tcW w:w='"
									+ width[j]
									+ "' w:type='dxa' /> "
									+ "<w:vAlign w:val='center' />"
									+ " <w:shd w:val='clear' w:color='auto' w:fill='#"
									+ Integer
											.toHexString(dataBGcolor.getRGB() & 0x00ffffff)
									+ "' />"
									+ "</w:tcPr>"
									+ "<w:p>"
									+ "<w:pPr>"
									+ "<w:jc w:val='"
									+ cellAlign[j]
									+ "'/>"
									+ "</w:pPr>"
									+ "<w:r>"
									+ "<w:rPr><w:color w:val='"
									+ Integer.toHexString(dataFontColor
											.getRGB() & 0x00ffffff)
									+ "'/></w:rPr>"
									+ "<w:t><w:rPr><w:sz w:val='"
									+ dataBodyFontSize
									+ "'/><w:sz-cs w:val='"
									+ dataBodyFontSize
									+ "'/> "
									+ dataBodyFontStyle
									+ "</w:rPr>"
									+ checkNull(String.valueOf(""))
									+ "</w:t> "
									+ " </w:r> " + "</w:p> " + " </w:tc> ";

						}
					} else {
						elementTableTag += "<w:tc> " + "<w:tcPr>"
								+ "<w:tcW w:w='"
								+ width[j]
								+ "' w:type='dxa' /> "
								+ "<w:vAlign w:val='center' />"
								+ " <w:shd w:val='clear' w:color='auto' w:fill='#"
								+ Integer
										.toHexString(dataBGcolor.getRGB() & 0x00ffffff)
								+ "' />"
								+ "</w:tcPr>"
								+ "<w:p>"
								+ "<w:pPr>"
								+ "<w:jc w:val='"
								+ cellAlign[j]
								+ "'/>"
								+ "</w:pPr>"
								+ "<w:r>"
								+ "<w:rPr><w:color w:val='"
								+ Integer
										.toHexString(dataFontColor.getRGB() & 0x00ffffff)
								+ "'/></w:rPr>"
								+ "<w:t><w:rPr><w:sz w:val='"
								+ dataBodyFontSize
								+ "'/><w:sz-cs w:val='"
								+ dataBodyFontSize
								+ "'/> "
								+ dataBodyFontStyle
								+ "</w:rPr>"
								+ checkNull(String.valueOf(""))
								+ "</w:t> "
								+ " </w:r> "
								+ "</w:p> "
								+ " </w:tc> ";

					}
				}
			}
			elementTableTag += "</w:tr>";
		}

		elementTableTag += "</w:tbl> <w:p />";
		String blankRowBelow = "";
		if (tds.getBlankRowsBelow() > 0) {
			for (int i = 0; i < tds.getBlankRowsBelow(); i++) {
				blankRowBelow += " <w:tbl> <w:tr> <w:tc> <w:p> <w:r> <w:t></w:t> </w:r> </w:p> </w:tc> </w:tr> </w:tbl>  ";
			}
		}
		elementTableTag += blankRowBelow;
		return elementTableTag;
	}

	/**
	 * In this method joining the two table.first checking that it is table or
	 * HashMap. If HashMap is present then it will move in the same loop
	 * whenever not found the table. If table is found then we can set the table
	 * space to left table and right table other wise space for two table are
	 * equal.
	 * 
	 * @param map
	 * @return String
	 */

	private String joinTableDtl1(HashMap<String, Object> map) {
		String joinTableEment = "";
		try {
			int[] width = new int[2];
			if (map.get("width") != null) {
				width[0] = Integer.parseInt(String.valueOf(map.get("width"))) * 150;
				width[1] = (100 * 150)
						- Integer.parseInt(String.valueOf(map.get("width")))
						* 150;
			} else {
				width[0] = 50 * 150;
				width[1] = 50 * 150;
			}
			boolean isHorizontal = false;
			Object objDirection = (Object) map.get("Direction");
			if (objDirection instanceof String) {
				isHorizontal = objDirection.equals("H") ? true : false;
			}

			Object objTableDataSet1 = (Object) map.get("1");
			Object objTableDataSet2 = (Object) map.get("2");
			if (isHorizontal) {

				if (objTableDataSet1 instanceof TableDataSet) {
					if (isHorizontal) {
						joinTableEment += "<w:tc>" + "<w:tcPr>"
								+ "<w:tcW w:w='" + width[0]
								+ "' w:type='dxa' /> "
								+ "<w:vAlign w:val='top' />" + "</w:tcPr>";

					}
					joinTableEment += createDocTable((TableDataSet) objTableDataSet1);
					if (isHorizontal) {
						joinTableEment += "<w:p> <wx:allowEmptyCollapse /> </w:p> </w:tc>";
					}
				} else {

					joinTableEment += joinTable((HashMap<String, Object>) objTableDataSet1);
				}

				if (objTableDataSet2 instanceof TableDataSet) {
					if (isHorizontal) {
						joinTableEment += "<w:tc>" + "<w:tcPr>"
								+ "<w:tcW w:w='" + width[1]
								+ "' w:type='dxa' /> "
								+ "<w:vAlign w:val='center' />" + "</w:tcPr>";
					}
					joinTableEment += createDocTable((TableDataSet) objTableDataSet2);
					if (isHorizontal) {
						joinTableEment += "<w:p> <wx:allowEmptyCollapse /> </w:p> </w:tc>";
					}
				} else {
					joinTableEment += joinTable((HashMap<String, Object>) objTableDataSet2);
				}
			} else {
				if (objTableDataSet1 instanceof TableDataSet) {
					joinTableEment += createDocTable((TableDataSet) objTableDataSet1);
				} else {
					joinTableEment += joinTable((HashMap<String, Object>) objTableDataSet1);
				}

				if (objTableDataSet2 instanceof TableDataSet) {
					joinTableEment += createDocTable((TableDataSet) objTableDataSet2);
				} else {
					joinTableEment += joinTable((HashMap<String, Object>) objTableDataSet2);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return joinTableEment;
	}

	/**
	 * In this method joining the two table.first checking that it is table or
	 * HashMap. If HashMap is present then it will move in the same loop
	 * whenever not found the table. If table is found then we can set the table
	 * space to left table and right table other wise space for two table are
	 * equal.
	 * 
	 * @param map
	 * @return String
	 */
	private String joinTable(HashMap<String, Object> map) {
		String joinTableEment = "";
		/*
		 * int[] width = new int[2]; if (map.get("width") != null) { width[0] =
		 * Integer.parseInt(String.valueOf(map.get("width"))); width[1] = (100) -
		 * Integer.parseInt(String .valueOf(map.get("width"))); } else {
		 * width[0] = 50; width[1] = 50; }
		 */
		int[] width = new int[2];
		if (map.get("width") != null) {
			width[0] = Integer.parseInt(String.valueOf(map.get("width"))) * 150;
			width[1] = (100 * 150)
					- Integer.parseInt(String.valueOf(map.get("width"))) * 150;
		} else {
			width[0] = 50 * 150;
			width[1] = 50 * 150;
		}
		try {
			boolean isHorizontal = false;
			Object objDirection = (Object) map.get("Direction");
			int horzCount = 0;
			if (objDirection instanceof String) {
				isHorizontal = objDirection.equals("H") ? true : false;
			}
			Object objTableDataSet1 = (Object) map.get("1");
			Object objTableDataSet2 = (Object) map.get("2");
			if (isHorizontal) {
				if (isHorizontal && txtHorizantalFlag == false) {
					horzCount = 1;
					txtHorizantalFlag = true;
					/*
					 * joinTableEment +="<w:tbl> <w:tblPr> <w:tblStyle
					 * w:val='TableGrid' /> <w:tblW w:w='8' w:type='auto' /> " +"
					 * <w:tblLook w:val='01E0' /> </w:tblPr> <w:tr> ";
					 */
					joinTableEment += " <w:tbl> ";
					joinTableEment += "<w:tblPr><w:tblW w:w='1' w:type='auto' /> "
							+ "<w:tblLook w:val='01E0' /> " + "</w:tblPr>";
				}
				if (objTableDataSet1 instanceof TableDataSet) {
					if (isHorizontal) {
						logger.info("width [0]   " + width[0]);
						joinTableEment += "<w:tc>" + "<w:tcPr>"
								+ "<w:tcW w:w='" + width[0]
								+ "' w:type='dxa' /> "
								+ "<w:vAlign w:val='top' />" + "</w:tcPr>";
					}
					joinTableEment += createDocTable((TableDataSet) objTableDataSet1);

					if (isHorizontal) {
						joinTableEment += "<w:p> <wx:allowEmptyCollapse /> </w:p> </w:tc>";
					}
				} else {

					joinTableEment += joinTable((HashMap<String, Object>) objTableDataSet1);
				}

				if (objTableDataSet2 instanceof TableDataSet) {
					if (isHorizontal) {
						logger.info("width [1]   " + width[1]);
						joinTableEment += "<w:tc>" + "<w:tcPr>"
								+ "<w:tcW w:w='" + width[1]
								+ "' w:type='dxa' /> "
								+ "<w:vAlign w:val='center' />" + "</w:tcPr>";
					}
					joinTableEment += createDocTable((TableDataSet) objTableDataSet2);
					if (isHorizontal) {
						joinTableEment += "<w:p> <wx:allowEmptyCollapse /> </w:p> </w:tc>";
					}
				} else {
					joinTableEment += joinTable((HashMap<String, Object>) objTableDataSet2);
				}
				if (isHorizontal) {
					joinTableEment += "</w:tr> </w:tbl> ";
				}
				isHorizontal = false;
			} else {
				if (horzCount > 0) {
					joinTableEment += "</w:tr> </w:tbl> ";
				}
				if (objTableDataSet1 instanceof TableDataSet) {
					joinTableEment += createDocTable((TableDataSet) objTableDataSet1);
				} else {
					joinTableEment += joinTable((HashMap<String, Object>) objTableDataSet1);
				}

				if (objTableDataSet2 instanceof TableDataSet) {
					joinTableEment += createDocTable((TableDataSet) objTableDataSet2);
				} else {
					joinTableEment += joinTable((HashMap<String, Object>) objTableDataSet2);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getTableDataSet:" + e);
			e.printStackTrace();

		}
		txtHorizantalFlag = false;
		txtHorizantalFlag = false;
		return joinTableEment;
	}

	public void genHeader(Object obj) {
		String headerElement = "<w:hdr w:type='odd'>";
		;
		try {
			if (obj instanceof TableDataSet) {
				logger.info("In TableDataSet__________________");
				headerElement += createDocTable((TableDataSet) obj);
			} else if (obj instanceof HashMap) {
				logger.info("In HashMap");
				headerElement += joinTable((HashMap<String, Object>) obj);
			}
		} catch (Exception e) {
			logger.error("error in genHeader--------------------------" + e);
			e.printStackTrace();
		}
		headerElement += "<w:p> <w:pPr> <w:pStyle w:val='Header' /> </w:pPr> </w:p> </w:hdr>";
		elementTag += headerElement;
	}

	/**
	 * for setting the Header according to the parameter like page number etc.
	 * 
	 * @param obj
	 */
	public void genHeader_1(Object obj) {
		/*
		 * String headerElement ="<w:hdr w:type='odd'> <w:p> <w:r> <w:fldChar
		 * w:fldCharType='separate' /></w:r> " +"<w:r> <w:rPr> <w:noProof />
		 * </w:rPr> <w:t>1</w:t> </w:r> <w:r> <w:fldChar w:fldCharType='end' />
		 * </w:r> </w:p> " +"</w:hdr>";
		 */
		String headerElement = "<w:hdr w:type='odd'>";
		try {
			if (obj instanceof TableDataSet)
				headerElement += createDocTable((TableDataSet) obj);
			else if (obj instanceof HashMap)
				headerElement += joinTable((HashMap<String, Object>) obj);
		} catch (Exception e) {
			logger.error("error in genHeader" + e);
		}
		headerElement += "<w:p> <w:pPr> <w:pStyle w:val='Header' /> </w:pPr> </w:p> </w:hdr>";
		elementTag += headerElement;
	}

	/**
	 * for setting the Footer according to the parameter like page number etc.
	 * 
	 * @param obj
	 */

	public void genFooter(Object obj) {
		String footerElement = "<w:ftr w:type='odd'>";
		try {
			if (obj instanceof TableDataSet)
				footerElement += createDocTable((TableDataSet) obj);
			else if (obj instanceof HashMap)
				footerElement += joinTable((HashMap<String, Object>) obj);
		} catch (Exception e) {
			logger.error("error in genHeader" + e);
			// e.printStackTrace();
		}
		footerElement += "</w:ftr>";
		elementTag += footerElement;
	}

	/**
	 * In this method concat all the String .Close the body ,document and return
	 * the string for writing to the file.
	 * 
	 * @param dataMap
	 */
	public void process(HashMap<String, Object> dataMap) {
		elementTag += "<w:body> ";
		try {
			if (dataMap != null && dataMap.size() > 0) {
				for (int i = 0; i < dataMap.size(); i++) {
					Object obj = dataMap.get(String.valueOf(i));

					if (obj instanceof TableDataSet) {
						TableDataSet tds = (TableDataSet) obj;
						elementTag += createDocTable(tds);
					} else if (obj instanceof HashMap) {
						System.out.println("calling join table");
						elementTag += joinTable((HashMap<String, Object>) obj);
					} else if (obj.equals("PAGE_BREAK")) {
						elementTag += "<w:p> <w:r>  <w:br w:type='page' /> <w:t></w:t> </w:r> </w:p>";
					}
				}
			}
			elementTag += "</w:body> " + "</w:wordDocument> ";
			System.out.println("elementTag------------ >>" + elementTag);
		} catch (Exception e) {
			logger.error("Exception in process:");
			e.printStackTrace();
		}
	}

	public void process_1(HashMap<String, Object> dataMap) {
		elementTag += "<w:body> ";
		try {
			if (dataMap != null && dataMap.size() > 0) {
				for (int i = 0; i < dataMap.size(); i++) {
					Object obj = dataMap.get(String.valueOf(i));

					if (obj instanceof TableDataSet) {
						TableDataSet tds = (TableDataSet) obj;
						elementTag += createDocTable(tds);
					} else if (obj instanceof HashMap) {
						System.out.println("calling join table");
						elementTag += joinTable((HashMap<String, Object>) obj);
					} else if (obj.equals("PAGE_BREAK")) {
						System.out.println("In brak------------ >>");
						elementTag += "<w:p> <w:r>  <w:br w:type='page' /> <w:t></w:t> </w:r> </w:p>";
					}
				}
			}
			elementTag += "</w:body> " + "</w:wordDocument> ";
			System.out.println("elementTag------------ >>" + elementTag);
		} catch (Exception e) {
			logger.error("Exception in process:");
			e.printStackTrace();
		}
	}

	/**
	 * return the blank when value is null
	 * 
	 * @param result
	 * @return
	 */

	public String checkNull(String result) {

		if (result == null || result.equals("null") || result.trim().equals("")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * return the zero when value is null
	 * 
	 * @param result
	 * @return
	 */

	public String checkZero(String result) {

		if (result == null || result.equals("null") || result.trim().equals("")) {
			return "0";
		} else {
			try {

				Double.parseDouble(result);

			} catch (Exception e) {
				result = "0";
			}
			return result;
		}
	}

	private String RGBtoHex(BaseColor color) {
		String hexColor = "";
		try {
			if (color != null) {
				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();
				hexColor += toHex(red) + toHex(green) + toHex(blue);
			}
		} catch (Exception e) {
			logger.error("Exception in RGBtoHex:" + e);
		}
		return hexColor;
	}

	private String toHex(int num) {
		String colorToHex = "";
		try {
			if (num == 0) {
				return "00";
			}
			num = Math.max(0, num);
			num = Math.min(num, 255);
			num = Math.round(num);
			String colorToHex1 = String.valueOf("0123456789ABCDEF"
					.charAt((num - num % 16) / 16));
			String colorToHex2 = String.valueOf("0123456789ABCDEF"
					.charAt(num % 16));
			colorToHex = colorToHex1 + colorToHex2;
		} catch (Exception e) {
			logger.error("Exception in toHex:" + e);
		}
		return colorToHex;
	}

}
