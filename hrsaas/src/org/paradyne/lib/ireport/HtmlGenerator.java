package org.paradyne.lib.ireport;

import java.awt.Color;
import java.util.HashMap;
import org.paradyne.lib.Utility;
import com.lowagie.text.Font;
/**
 * 
 * @author VISHWAMBHAR DESHPANDE
 * 
 */
public class HtmlGenerator {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HtmlGenerator.class);
	
	StringBuffer createTableStrBuffer = new StringBuffer();
	boolean htmlHorizantalFlag = false;
	boolean htmlVerticalFlag = false;
	
	public HtmlGenerator() {
		ReportDataSet rds = new ReportDataSet();
		float leftMargin = 0.0f;
		float rightMargin = 0.0f;
		float topMargin = 0.0f;
		float bottomMargin = 0.0f;
		leftMargin = rds.getMarginLeft();
		rightMargin = rds.getMarginRight();
		topMargin = rds.getMarginTop();
		bottomMargin = rds.getMarginBottom();
		String pageSize = rds.getPageSize();
		String pageOrientation = rds.getPageOrientation();
		createTableStrBuffer.append("<style>.break { page-break-before: always; }</style>");
		createTableStrBuffer.append("<html>");
		createTableStrBuffer.append("<body  style='page-size:" + pageSize+ ";page-orientation:" + pageOrientation + "';  leftmargin='"+leftMargin + "' topmargin='" + topMargin + "' rightmargin='"
				+ rightMargin + "' bottommargin='" + bottomMargin + "'>");
		createTableStrBuffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
		createTableStrBuffer.append("<tr>");
		createTableStrBuffer.append("<td>");
	}
	
	/**
	 * THIS METHOD IS USED FOR CREATING HTML TABLE AND RETURN STRING BUFFER OBJECT
	 * @param tds
	 * TableDataSet as parameter
	 * @return StringBuffer Object
	 */

	public StringBuffer createHtmlTable(TableDataSet tds) {
		StringBuffer createHtmlTableStrBuffer = new StringBuffer();
		if (tds.getBlankRowsAbove() > 0) {
			for (int i = 0; i < tds.getBlankRowsAbove(); i++) {
				createHtmlTableStrBuffer.append("<table border='0'  cellspacing='0'  cellpadding='0' width='100%'>"
				+ "<tr><td height='20'></td></tr></table>");
			}
		}
		Font bodyFont = null;
		Font headerFont = null;
		Font sumFont = null;
		Object[][] data = tds.getData();
		if(data != null && data.length > 0)
		{
			int[] width = tds.getCellWidth();// width
			if (width != null) {
				for (int i = 0; i < width.length; i++) {
					width[i] = width[i];
				}
			}
			int[] alignDigit = tds.getCellAlignment();
			String[] cellAlign = null;
			if (width != null) {
				cellAlign = new String[width.length];
			} else {
				cellAlign = new String[data[0].length];
			}
			if (width != null && width.length > 0) // if specific alignment is given
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
				if (data != null && data.length > 0) // if alignment is not given put data left
				{
					for (int i = 0; i < data[0].length; i++) {
						cellAlign[i] = "left";
					}
				}

			}
			Color clrBody = tds.getBodyBGColor();// background color for cell
			Color clrHeader = tds.getHeaderBGColor();// background color for cell
			bodyFont = tds.getBodyFontDetails();// font 4 body of the table with
			// font,size style and color
			String header[] = tds.getHeader();// table headers
			Boolean border = tds.getBorder();// border yes no
			int borderValue = 0;
			if (border) {
				borderValue = 1;
			}
			Object[] columColorData = tds.getColumnColor();// if some colum required special color
			// column no which is to be colored
			int colNo = Integer.parseInt(String.valueOf(columColorData[0]));
			Color colBackColor = (Color) columColorData[1];// background color
			Font colFontColor = (Font) columColorData[2];// fontcolor of that// column
			logger.info("colFontColor $$$$$$$$$$$$$$$$     "+colFontColor);
			headerFont = tds.getHeaderFontDetails();
			sumFont = tds.getSumFontDetails();
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
			int[] rowSum = tds.getRowSum();
			int[] rowsAvg = tds.getRowAvg();
			if(rowSum != null && rowsAvg != null){
				tempOne = 1;
				tempTwo = 2;
			}
			double rowSumOut = 0.0;
			double rowAvgOut = 0.0;
			int headerFontSize=1;

		createHtmlTableStrBuffer.append("<table border='" + borderValue
				+ "' cellspacing='0'  cellpadding='0' width='100%'>");
		if (header != null && header.length > 0) {
			createHtmlTableStrBuffer.append("<tr>");
			for (int i = 0; i < header.length; i++) {
				String header_Font = "";
				String header_color = "";
				// font mentioned or default font for the table headings
				if(tds.getHeaderFontSize()!=-1)
				{
					headerFontSize=tds.getHeaderFont();
				}
				if (!(headerFont.family() == -1)) {
					header_Font = RGBtoHex(headerFont.color());
				} else {
					header_Font = "000000";
				}
				if (clrHeader == null) {
					header_color = "FFFFFF";
				} else {
					header_color = RGBtoHex(clrHeader);
				}
				createHtmlTableStrBuffer.append("<td width='" + width[i]
						+ "%' align='center' style='BACKGROUND-COLOR:#" + header_color
						+ "'><font size='"+headerFontSize+"' face='HELVETICA' color='#" + header_Font + "'><b>" + header[i]
						+ "</b></font></td>");

			}
			createHtmlTableStrBuffer.append("</tr>");
		}
	 	if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				String column_fontcolor = "000000";
		 		String sumfontcolor = "000000";
				String columnBackColor="FFFFFF";
				createHtmlTableStrBuffer.append("<tr>");
				for (int j = 0; j < cellAlign.length; j++) {
					if (!(rowsAvg == null) && j == cellAlign.length - tempOne)
					{
						if (!(sumFont.family() == -1))
						{
							sumfontcolor=RGBtoHex(sumFont.color());
						}
						createHtmlTableStrBuffer.append("<td width='"
								+ width[j]
								+ "%' align='"
								+ cellAlign[j]
								+ "'><font color='#"
								+ sumfontcolor
								+ "'>"
								+ checkNull(String.valueOf(Utility
										.twoDecimals(rowAvgOut
												/ rowsAvg.length)))
								+ "&nbsp;</font></td>");
						
					}
					else if (!(rowSum == null) && j == cellAlign.length - tempTwo) {
						if (!(sumFont.family() == -1))
						{
							sumfontcolor=RGBtoHex(sumFont.color());
							
						}
						createHtmlTableStrBuffer.append("<td width='"
								+ width[j]
								+ "%' align='"
								+ cellAlign[j]
								+ "'><font color='#"
								+ sumfontcolor
								+ "'>"
								+ checkNull(String.valueOf(rowSumOut))
								+ "&nbsp;</font></td>");
					}
					
					else {
						if ((!(colNo == -1)) && j == colNo) {
							columnBackColor=RGBtoHex(colBackColor);
							createHtmlTableStrBuffer.append("<td width='"
									+ width[j]
									+ "%' align='"
									+ cellAlign[j]
									+ "' style='BACKGROUND-COLOR: #"+columnBackColor+"'>"
									+ replaceNewLineCharWithBr(checkNull(String.valueOf(data[i][j])))
									+ "&nbsp;</td>");
						}
						else {
							if (!(bodyFont.family() == -1)) {
								// font mentioned for table data else take default font
								createHtmlTableStrBuffer.append("<td width='"
										+ width[j]
										+ "%' align='"
										+ cellAlign[j]
										+ "' style='BACKGROUND-COLOR: #FFFFFF'>"
										+ replaceNewLineCharWithBr(checkNull(String.valueOf(data[i][j])))
										+ "&nbsp;</td>");
							} 
							else {
								// logger.info("font not found");
								try{
									createHtmlTableStrBuffer.append("<td width='"
											+ width[j]
											+ "%' align='"
											+ cellAlign[j]
											+ "' style='BACKGROUND-COLOR: #FFFFFF'>"
											+ replaceNewLineCharWithBr(checkNull(String.valueOf(data[i][j])))
											+ "&nbsp;</td>");
								}catch(Exception e){
									logger.info("error in setting data "+e);
								}
							}	
						}
						if (!(rowSum == null)) {
							for (int l = 0; l < rowSum.length; l++) {
								if (j == rowSum[l]) {
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
			 		}
				}
				rowSumOut = 0.00;
				rowAvgOut = 0.00;
				createHtmlTableStrBuffer.append("</tr>");
			}
			
		}
		if (!(colsSum == null)) {
			int k = 0;
			String sumFontColor = "000000";
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < cellAlign.length; j++) {
					if (k < colsSum.length) {
						if (j == colsSum[k]) {
							if (!(sumFont.family() == -1)) {
								sumFontColor = RGBtoHex(sumFont.color());
							}
							createHtmlTableStrBuffer.append("<td width='"
									+ width[j]
									+ "%' align='"
									+ cellAlign[j]
									+ "'><font color='#"
									+ sumFontColor
									+ "'>"
									+ checkNull(String.valueOf(Utility 
											.twoDecimals(sumOut[k])))
									+ "&nbsp;</font></td>");
							k++;
						} else {
							createHtmlTableStrBuffer.append("<td width='"
									+ width[j] + "%' align='" + cellAlign[j]
									+ "'><font color='#"+sumFontColor+"'>"
									+ checkNull(String.valueOf(""))
									+ "&nbsp;</font></td>");
						}
					} else {
						createHtmlTableStrBuffer.append("<td width='"
								+ width[j] + "%' align='" + cellAlign[j]
								+ "'><font color='#"+sumFontColor+"'>"
								+ checkNull(String.valueOf(""))
								+ "&nbsp;</font></td>");
					}
				}
			}
		}
		if (!(colsAvg == null)) {
			int k = 0;
			String colAvgFontColor="000000";
			createHtmlTableStrBuffer.append("<tr>");
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < cellAlign.length; j++) {
					if (k < colsAvg.length) {
						if (j == colsAvg[k]) {
							if (!(sumFont.family() == -1)) {
								colAvgFontColor=RGBtoHex(sumFont.color());
							} 
							createHtmlTableStrBuffer.append("<td width='"
									+ width[j]
									+ "%' align='"
									+ cellAlign[j]
									+ "'><font color='#"+colAvgFontColor+"'>"
									+ checkNull(String.valueOf(Utility
											.twoDecimals(avgOut[k]
													/ colsAvg.length)))
									+ "&nbsp;</font></td>");
							k++;
						} else {
							createHtmlTableStrBuffer.append("<td width='"
									+ width[j]
									+ "%' align='"
									+ cellAlign[j]
									+ "'><font color='#"+colAvgFontColor+"'>"
									+ checkNull(String.valueOf(""))
									+ "&nbsp;</font></td>");
						}
					} else
					{
						createHtmlTableStrBuffer.append("<td width='"
								+ width[j]
								+ "%' align='"
								+ cellAlign[j]
								+ "'><font color='#"+colAvgFontColor+"'>"
								+ checkNull(String.valueOf(""))
								+ "&nbsp;</font></td>");
					}
				}
			}
			createHtmlTableStrBuffer.append("</tr>");
		}
		createHtmlTableStrBuffer.append("</table>");
		if (tds.getBlankRowsBelow() > 0) {
			for (int i = 0; i < tds.getBlankRowsBelow(); i++) {
				createHtmlTableStrBuffer
						.append("<table border='0'   cellspacing='0'  cellpadding='0' width='100%'>"
								+ "<tr><td height='20'></td></tr></table>");
			}
		}
	}	
		return createHtmlTableStrBuffer;
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

	private StringBuffer joinTable(HashMap<String, Object> map) {
		StringBuffer joinTableStrBuffer = new StringBuffer();
		int[] width = new int[2];
		if (map.get("width") != null) {
			width[0] = Integer.parseInt(String.valueOf(map.get("width")));
			width[1] = (100) - Integer.parseInt(String
					.valueOf(map.get("width")));
		} else {
			width[0] = 50;
			width[1] = 50;
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
				if (isHorizontal && htmlHorizantalFlag == false) {
					horzCount = 1;
					htmlHorizantalFlag = true;
					joinTableStrBuffer.append("<table border='0' width='100%'>"
							+ "<tr>");
				}
				if (objTableDataSet1 instanceof TableDataSet) {
					if (isHorizontal) {
						joinTableStrBuffer.append("<td valign='top' width='"
								+ width[0] + "%'>");
					}
					joinTableStrBuffer
							.append(createHtmlTable((TableDataSet) objTableDataSet1));

					if (isHorizontal) {
						joinTableStrBuffer.append("</td>");
					}
				} else {

					joinTableStrBuffer
							.append(joinTable((HashMap<String, Object>) objTableDataSet1));
				}

				if (objTableDataSet2 instanceof TableDataSet) {
					if (isHorizontal) {
						joinTableStrBuffer.append("<td valign='top' width='"
								+ width[1] + "%'>");
					}
					joinTableStrBuffer
							.append(createHtmlTable((TableDataSet) objTableDataSet2));
					if (isHorizontal) {
						joinTableStrBuffer.append("</td>");
					}
				} else {
					joinTableStrBuffer
							.append(joinTable((HashMap<String, Object>) objTableDataSet2));
				}
				if (isHorizontal) {
					joinTableStrBuffer.append("</tr></table>");
				}
				isHorizontal = false;
			} else {
				if (horzCount > 0) {
					joinTableStrBuffer.append("</tr></table>");
				}
				if (objTableDataSet1 instanceof TableDataSet) {
					joinTableStrBuffer
							.append(createHtmlTable((TableDataSet) objTableDataSet1));

				} else {
					joinTableStrBuffer
							.append(joinTable((HashMap<String, Object>) objTableDataSet1));
				}

				if (objTableDataSet2 instanceof TableDataSet) {
					joinTableStrBuffer
							.append(createHtmlTable((TableDataSet) objTableDataSet2));
				} else {
					joinTableStrBuffer
							.append(joinTable((HashMap<String, Object>) objTableDataSet2));
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getTableDataSet:" + e);
			e.printStackTrace();

		}
		htmlHorizantalFlag = false;
		htmlVerticalFlag = false;
		return joinTableStrBuffer;
	}
	public void process(HashMap<String, Object> dataMap) {
		try {
			if (dataMap != null && dataMap.size() > 0) {
				for (int i = 0; i < dataMap.size(); i++) {
					Object obj = dataMap.get(String.valueOf(i));

					if (obj instanceof TableDataSet) {
						TableDataSet tds = (TableDataSet) obj;
						createTableStrBuffer.append(createHtmlTable(tds));
					} else if (obj instanceof HashMap) {
						System.out.println("calling join table");
						createTableStrBuffer
								.append(joinTable((HashMap<String, Object>) obj));
					} else if (obj.equals("PAGE_BREAK")) {
						createTableStrBuffer.append("<p class='break'></P>");
					}
				}
			}
			createTableStrBuffer.append("</td>");
			createTableStrBuffer.append("</tr>");
			createTableStrBuffer.append("</table>");
			createTableStrBuffer.append("</body>");
			createTableStrBuffer.append("</html>");
			String str = " <script> "
					+ "	printpreview(); "
					+ "function printpreview() "
					+ "{ "
					+ "   var OLECMDID = 7; "
					+ " /* OLECMDID values: "
					+ "  * 6 - print "
					+ "* 7 - print preview "
					+ " * 1 - open window "
					+ " * 4 - Save As "
					+ " */ "
					+ " var PROMPT = 1;"
					+ " var WebBrowser = '<OBJECT ID=\"WebBrowser1\" WIDTH=\"0\" HEIGHT=\"0\" CLASSID=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></OBJECT>'; "
					+ "  document.body.insertAdjacentHTML('beforeEnd', WebBrowser); "
					+ " WebBrowser1.ExecWB(OLECMDID, PROMPT); "
					+ " WebBrowser1.outerHTML = \"\"; " + " } </script> ";

			createTableStrBuffer.append(str);
			logger.info("createTableStrBuffer------------ >>\n"+createTableStrBuffer);
		} catch (Exception e) {
			logger.error("Exception in process:");
			e.printStackTrace();
		}
	}

	public void genHeader(Object obj) {
		StringBuffer headerStrBuffer = new StringBuffer();
		headerStrBuffer.append("<thead>");
		headerStrBuffer.append("<tr><td>");
		try {
			if (obj instanceof TableDataSet) {
				logger.info("In TableDataSet__________________");
				headerStrBuffer.append(createHtmlTable((TableDataSet) obj));
			} else if (obj instanceof HashMap) {
				logger.info("In HashMap");
				headerStrBuffer
						.append(joinTable((HashMap<String, Object>) obj));
			}
		} catch (Exception e) {
			logger.error("error in genHeader--------------------------" + e);
			e.printStackTrace();
		}
		headerStrBuffer.append("</td></tr>  ");
		headerStrBuffer.append("</thead> ");
		createTableStrBuffer.append(headerStrBuffer);
	}
	public void genFooter(Object obj) {
		logger.info("inside genFooter--------------");
		StringBuffer footerStrBuffer = new StringBuffer();
		try {
			footerStrBuffer.append("<tfoot>");
			if (obj instanceof TableDataSet)
				footerStrBuffer.append(createHtmlTable((TableDataSet) obj));
			else if (obj instanceof HashMap)
				footerStrBuffer
						.append(joinTable((HashMap<String, Object>) obj));
		} catch (Exception e) {
			logger.error("error in genHeader" + e);
			e.printStackTrace();
		}
		footerStrBuffer.append("</tfoot> ");
		createTableStrBuffer.append(footerStrBuffer);
	}
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

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
	private String RGBtoHex(Color color) {
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
	
	private String replaceNewLineCharWithBr(String result)
	{
		if(result.contains("\n"))
		{
			result=result.replaceAll("\n","<br>");
		}
		return result;
	}
}
