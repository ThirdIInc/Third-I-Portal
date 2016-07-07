/* Bhushan Dasare Nov 27, 2009 */

package org.paradyne.lib.ireport;

import java.awt.Color;
import java.util.HashMap;
import java.util.TreeMap;
import org.paradyne.lib.Utility;
import com.lowagie.text.Font;

public class XLSGenerate {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(XLSGenerate.class);
	private boolean showPageNo = false;
	private int pageNoHPosition, pageNoVPosition;
	TreeMap<Integer, String> mapTable = new TreeMap<Integer, String>();
	public StringBuffer strExcelReport = new StringBuffer();
	private StringBuffer headerStrBuffer = new StringBuffer();
	private StringBuffer footerStrBuffer = new StringBuffer();
	boolean htmlHorizantalFlag = false;
	boolean htmlVerticalFlag = false;

	public XLSGenerate(ReportDataSet rds) {
		try {
			showPageNo = rds.isShowPageNo();
			pageNoHPosition = rds.getPageNoHPosition();
			pageNoVPosition = rds.getPageNoVPosition();

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
			
			strExcelReport.append("<style>.break { page-break-before: always; }</style>");
			strExcelReport.append("<html>");
			strExcelReport.append("<body style='page-size:" + pageSize + "; page-orientation:" + pageOrientation + "'; leftmargin='" + leftMargin + "' " +
				"topmargin='" + topMargin + "' rightmargin='" + rightMargin + "' bottommargin='" + bottomMargin + "'>");
			strExcelReport.append("<table width='100%'><tr><td>");
		} catch(Exception e) {
			logger.error("Exception in XLSGenerate:" + e);
		}
	}

	private String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	private String checkZero(String result) {
		if(result == null || result.equals("null") || result.trim().equals("")) {
			return "0";
		} else {
			try {
				Double.parseDouble(result);
			} catch(Exception e) {
				result = "0";
			}
			return result;
		}
	}

	public StringBuffer createHtmlTable(TableDataSet tds, boolean isHeaderandFooterAdded) {
		StringBuffer createHtmlTableStrBuffer = new StringBuffer();
		
		try {
			if(tds.getBlankRowsAbove() > 0) {
				for(int i = 0; i < tds.getBlankRowsAbove(); i++) {
					createHtmlTableStrBuffer.append("<table width='100%'><tr><td></td></tr></table>");
				}
			}
			
			Font bodyFont = null;
			Font headerFont = null;
			Font sumFont = null;
			
			Object[][] data = tds.getData();
			
			if(data != null && data.length > 0) {
				int[] cellWidth = tds.getCellWidth();			
				int[] cellAlignment = tds.getCellAlignment();
				int[] cellColSpan = tds.getCellColSpan();
				
				/**
				 * Set width
				 */
				String[] width = new String[data[0].length];
				if(cellWidth != null && cellWidth.length > 0) {
					for(int i = 0; i < data[0].length; i++) {
						try {
							String strWidth = String.valueOf(cellWidth[i]);
							
							if(!(strWidth.equals("null") || strWidth.equals(null) || strWidth == null || strWidth.equals(""))) {
								width[i] = strWidth;
							} else {
								width[i] = "10";
							}
						} catch(Exception e) {
							width[i] = "10";
						}
					}
				} else {
					for(int i = 0; i < data[0].length; i++) {
						width[i] = "10";
					}
				}
				
				/**
				 * Set alignment
				 */
				String[] align = new String[data[0].length];
				
				if(cellAlignment != null && cellAlignment.length > 0) {
					for(int i = 0; i < data[0].length; i++) {
						try {
							int intAlignment = cellAlignment[i];
							
							if(intAlignment == 0) { align[i] = "left"; }
							if(intAlignment == 1) { align[i] = "center"; }
							if(intAlignment == 2) { align[i] = "right"; }
						} catch(Exception e) {
							align[i] = "left";
						}
					}
				} else {
					for(int i = 0; i < data[0].length; i++) {
						align[i] = "left";
					}
				}
				
				/**
				 * Set colspan
				 */
				String[] colspan = new String[data[0].length];
				
				if(cellColSpan != null && cellColSpan.length > 0) {
					for(int i = 0; i < data[0].length; i++) {
						try {
							int intColspan = cellColSpan[i];
							
							if(intColspan == 0) {
								colspan[i] = "1";
							} else {
								colspan[i] = String.valueOf(intColspan);
							}
						} catch(Exception e) {
							colspan[i] = "1";
						}
					}
				} else {
					for(int i = 0; i < data[0].length; i++) {
						colspan[i] = "1";
					}
				}
				
				Color clrHeader = tds.getHeaderBGColor(); // background color for cell
				bodyFont = tds.getBodyFontDetails(); // font 4 body of the table with font,size style and color
				String header[] = tds.getHeader(); // table headers
				Boolean border = tds.getBorder(); // border yes no
				int borderValue = 0;
				
				if(border) { borderValue = 1; }
				
				/**
				 * Get column details
				 */
				// if some colum required special color column no. which is to be colored
				Object[] columColorData = tds.getColumnColor();
				// column no.
				int colNo = Integer.parseInt(String.valueOf(columColorData[0]));
				// background color
				Color colBackColor = (Color)columColorData[1];
				
				headerFont = tds.getHeaderFontDetails();
				sumFont = tds.getSumFontDetails();
				int tempOne = 1;
				int tempTwo = 1;
				int[] colsSum = tds.getColumnSum();
				int[] colsAvg = tds.getColumnAvg();
				double[] sumOut = null;
				double[] avgOut = null;
				
				if(!(colsSum == null)) { sumOut = new double[colsSum.length]; }
				
				if(!(colsAvg == null)) { avgOut = new double[colsAvg.length]; }
				
				int[] rowSum = tds.getRowSum();
				int[] rowsAvg = tds.getRowAvg();
				
				if(rowSum != null && rowsAvg != null) {
					tempOne = 1;
					tempTwo = 2;
				}
				
				double rowSumOut = 0.0;
				double rowAvgOut = 0.0;
				
				if(!isHeaderandFooterAdded) {
					createHtmlTableStrBuffer.append("<table border='" + borderValue + "' width='100%'>");
				}
				
				
				if(header != null && header.length > 0) {
					createHtmlTableStrBuffer.append("<tr>");
					
					for(int i = 0; i < header.length; i++) {
						int headerFontSize = 10;
						String headerFontColor = "000000";
						String headerBkcolor = "C0C0C0";
						
						if(tds.getHeaderFontSize() != -1) { headerFontSize = (int) tds.getHeaderFontSize(); }
						
						if(!(headerFont.family() == -1)) { headerFontColor = RGBToHex(headerFont.color()); }
						
						if(clrHeader != null) { headerBkcolor = RGBToHex(clrHeader); }
						
						createHtmlTableStrBuffer.append("<td colspan='" + colspan[i] + "' valign='middle' width='" + width[i] + "%' align='center' " +
							"style='BACKGROUND-COLOR:#" + headerBkcolor + "; font-size:" + headerFontSize + "pt;'>" +
							"<font face='HELVETICA' color='#" + headerFontColor + "'><b>" + header[i] + "</b></font></td>");
					}
					
					createHtmlTableStrBuffer.append("</tr>");
				}
				
				/**
				 * Set font properties for row and column average, row and column sum
				 */
				String sumFontColor = "000000";
				String sumFontFace = "Arial";
				int sumFontSize = 10;
				
				String sumStyleStart = "", sumStyleEnd = "";
				
				if(sumFont.family() != -1) {
					sumFontColor = RGBToHex(sumFont.color());
					sumFontFace = sumFont.getFamilyname();
					sumFontSize = (int) sumFont.size();
					
					if(sumFont.style() == Font.BOLD) { sumStyleStart = "<b>"; sumStyleEnd = "</b>"; }
					else if(sumFont.style() == Font.ITALIC) { sumStyleStart = "<i>"; sumStyleEnd = "</i>"; }
					else if(sumFont.style() == Font.UNDERLINE) { sumStyleStart = "<u>"; sumStyleEnd = "</u>"; }
					else if(sumFont.style() == Font.BOLDITALIC) { sumStyleStart = "<b><i>"; sumStyleEnd = "</i></b>"; }
				}
				
				
				for(int i = 0; i < data.length; i++) {
					createHtmlTableStrBuffer.append("<tr>");
					
					for(int j = 0; j < data[0].length; j++) {
						String strData = String.valueOf(data[i][j]);
						if(!strData.equals("IMG")) {
							/**
							 * Row Average
							 */
							if(!(rowsAvg == null) && j == align.length - tempOne) {
								createHtmlTableStrBuffer.append("<td colspan='" + colspan[j] + "' valign='top' width='" + width[j] + "%' " +
									"align='" + align[j] + "' style='font-size:" + sumFontSize + "pt;'>" +
									"<font face='" + sumFontFace + "' color='#" + sumFontColor + "'>" + 
									checkNull(String.valueOf(Utility.twoDecimals(rowAvgOut / rowsAvg.length))) + "</font></td>");
							}
							
							/**
							 * Row sum
							 */
							else if(!(rowSum == null) && j == align.length - tempTwo) {
								createHtmlTableStrBuffer.append("<td colspan='" + colspan[j] + "' valign='top' width='" + width[j] + "%' " +
									"align='" + align[j] + "' style='font-size:" + sumFontSize + "pt;'>" +
									"<font face='" + sumFontFace + "' color='#" + sumFontColor + "'>" + 
									checkNull(String.valueOf(rowSumOut)) + "</font></td>");
							}
							
							/**
							 * Data
							 */
							else {
								/**
								 * Column color
								 */
								if((!(colNo == -1)) && j == colNo) {
									String columnBackColor = RGBToHex(colBackColor);
									
									createHtmlTableStrBuffer.append("<td colspan='" + colspan[j] + "' valign='top' width='" + width[j] + "%' " +
										"align='" + align[j] + "' style='BACKGROUND-COLOR: #" + columnBackColor + "'>" +
										replaceNewLineCharWithBr(checkNull(strData)) + "</font></td>");
								}
								
								/**
								 * Remaining data
								 */
								else {
									/**
									 * Is font family exists
									 */
									if(bodyFont.family() != -1) {
										String bodyFontColor = RGBToHex(bodyFont.color());
										String bodyFontFace = bodyFont.getFamilyname();
										int bodyFontSize = (int) bodyFont.size();
										
										String bodyStyleStart = "", bodyStyleEnd = "";
										
										if(bodyFont.style() == Font.BOLD) { bodyStyleStart = "<b>"; bodyStyleEnd = "</b>"; }
										else if(bodyFont.style() == Font.ITALIC) { bodyStyleStart = "<i>"; bodyStyleEnd = "</i>"; }
										else if(bodyFont.style() == Font.UNDERLINE) { bodyStyleStart = "<u>"; bodyStyleEnd = "</u>"; }
										else if(bodyFont.style() == Font.BOLDITALIC) { bodyStyleStart = "<b><i>"; bodyStyleEnd = "</i></b>"; }
										
										createHtmlTableStrBuffer.append("<td colspan='" + colspan[j] + "' valign='top' width='" + width[j] + "%' " +
											"align='" + align[j] + "' style='BACKGROUND-COLOR: #FFFFFF; font-size:" + bodyFontSize + "pt;'>" +
											"<font face='" + bodyFontFace + "' color='#" + bodyFontColor + "'>" +
											bodyStyleStart + replaceNewLineCharWithBr(checkNull(strData)) + bodyStyleEnd + 
											"</font></td>");
									}
									
									/**
									 * Font family not exists
									 */
									else {
										try {
											createHtmlTableStrBuffer.append("<td colspan='" + colspan[j] + "' valign='top' width='" + width[j] + "%' " +
												"align='" + align[j] + "' style='BACKGROUND-COLOR: #FFFFFF'>" +
												replaceNewLineCharWithBr(checkNull(strData)) + "</td>");
										} catch(Exception e) {
											logger.error("error in setting data:" + e);
										}
									}
								}
								
								/**
								 * Calculate row sum
								 */
								if(!(rowSum == null)) {
									for(int l = 0; l < rowSum.length; l++) {
										if(j == rowSum[l]) {
											if(!strData.equals("")) {
												rowSumOut = rowSumOut + Double.parseDouble(checkZero(strData));
											}
										}
									}
								}
								
								/**
								 * Calculate row average
								 */
								if(!(rowsAvg == null)) {
									for(int l = 0; l < rowsAvg.length; l++) {
										if(j == rowsAvg[l]) {
											if(!strData.equals("")) {
												rowAvgOut = rowAvgOut + Double.parseDouble(checkZero(strData));
											}
										}
									}
								}
								
								/**
								 * Calculate column sum
								 */
								if(!(colsSum == null)) {
									for(int k = 0; k < colsSum.length; k++) {
										if(j == colsSum[k]) {
											if(!strData.equals("")) {
												sumOut[k] = sumOut[k] + Double.parseDouble(checkZero(strData));
											}
										}
									}
								}
								
								/**
								 * Calculate column average
								 */
								if(!(colsAvg == null)) {
									for(int k = 0; k < colsAvg.length; k++) {
										if(j == colsAvg[k]) {
											if(!strData.equals("")) {
												avgOut[k] = avgOut[k] + Double.parseDouble(checkZero(strData));
											}
										}
									}
								}
							}
						}
					}
					
					rowSumOut = 0.00;
					rowAvgOut = 0.00;
					createHtmlTableStrBuffer.append("</tr>");
				}
				
				/**
				 * Column average
				 */
				if(!(colsAvg == null)) {
					int k = 0;
					
					createHtmlTableStrBuffer.append("<tr>");
					
					for(int i = 0; i < 1; i++) {
						for(int j = 0; j < align.length; j++) {
							if(k < colsAvg.length) {
								if(j == colsAvg[k]) {
									createHtmlTableStrBuffer.append("<td colspan='" + colspan[j] + "' valign='top' width='" + width[j] + "%' " +
										"align='" + align[j] + "' style='font-size:" + sumFontSize + "pt;'>" +
										"<font face='" + sumFontFace + "' color='#" + sumFontColor + "'>" + 
										checkNull(String.valueOf(Utility.twoDecimals(avgOut[k] / colsAvg.length))) + "</font></td>");
									k++;
								} else {
									createHtmlTableStrBuffer.append("<td colspan='" + colspan[j] + "' valign='top' width='" + width[j] + "%' " +
										"align='" + align[j] + "' style='font-size:" + sumFontSize + "pt;'>" +
										"<font face='" + sumFontFace + "' color='#" + sumFontColor + "'>" + 
										checkNull(String.valueOf("")) + "</font></td>");
								}
							} else {
								createHtmlTableStrBuffer.append("<td colspan='" + colspan[j] + "' valign='top' width='" + width[j] + "%' " +
									"align='" + align[j] + "' style='font-size:" + sumFontSize + "pt;'>" +
									"<font face='" + sumFontFace + "' color='#" + sumFontColor + "'>" + 
									checkNull(String.valueOf("")) + "</font></td>");
							}
						}
					}
					
					createHtmlTableStrBuffer.append("</tr>");
				}
				
				/**
				 * Column sum
				 */
				if(!(colsSum == null)) {
					int k = 0;
					
					for(int i = 0; i < 1; i++) {
						for(int j = 0; j < align.length; j++) {
							if(k < colsSum.length) {
								if(j == colsSum[k]) {
									if(!(sumFont.family() == -1)) {
										sumFontColor = RGBToHex(sumFont.color());
									}
									createHtmlTableStrBuffer.append("<td colspan='" + colspan[j] + "' valign='top' width='" + width[j] + "%' " +
										"align='" + align[j] + "' style='font-size:" + sumFontSize + "pt;'>" +
										"<font face='" + sumFontFace + "' color='#" + sumFontColor + "'>" + 
										checkNull(String.valueOf(Utility.twoDecimals(sumOut[k]))) + "</font></td>");
									k++;
								} else {
									createHtmlTableStrBuffer.append("<td colspan='" + colspan[j] + "' valign='top' width='" + width[j] + "%' " +
										"align='" + align[j] + "' style='font-size:" + sumFontSize + "pt;'>" +
										"<font face='" + sumFontFace + "' color='#" + sumFontColor + "'>" + 
										checkNull(String.valueOf("")) + "</font></td>");
								}
							} else {
								createHtmlTableStrBuffer.append("<td colspan='" + colspan[j] + "' valign='top' width='" + width[j] + "%' " +
									"align='" + align[j] + "' style='font-size:" + sumFontSize + "pt;'>" +
									"<font face='" + sumFontFace + "' color='#" + sumFontColor + "'>" + 
									checkNull(String.valueOf("")) + "</font></td>");
							}
						}
					}
				}
				
				if(!isHeaderandFooterAdded) {
					createHtmlTableStrBuffer.append("</table>");
				}			
				
				if(tds.getBlankRowsBelow() > 0) {
					for(int i = 0; i < tds.getBlankRowsBelow(); i++) {
						createHtmlTableStrBuffer.append("<table width='100%'><tr><td></td></tr></table>");
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in createHtmlTable:" + e);
		}
		
		return createHtmlTableStrBuffer;
	}

	public void genFooter(Object obj) {
		try {
			if(obj instanceof TableDataSet) {
				footerStrBuffer.append(createHtmlTable((TableDataSet)obj, true));
			} else if(obj instanceof HashMap) {
				footerStrBuffer.append(joinTable((HashMap<String, Object>)obj));
			}
		} catch(Exception e) {
			logger.error("Exception in genFooter:" + e);
		}
	}

	public void genHeader(Object obj) {
		try {
			if(obj instanceof TableDataSet) {
				headerStrBuffer.append(createHtmlTable((TableDataSet)obj, true));
			} else if(obj instanceof HashMap) {
				headerStrBuffer.append(joinTable((HashMap<String, Object>)obj));
			}
		} catch(Exception e) {
			logger.error("Exception in genHeader:" + e);
		}
	}

	private StringBuffer joinTable(HashMap<String, Object> map) {
		StringBuffer joinTableStrBuffer = new StringBuffer();
		int[] width = new int[2];
		
		if(map.get("width") != null) {
			width[0] = Integer.parseInt(String.valueOf(map.get("width")));
			width[1] = (100) - Integer.parseInt(String.valueOf(map.get("width")));
		} else {
			width[0] = 50;
			width[1] = 50;
		}
		
		try {
			boolean isHorizontal = false;
			Object objDirection = (Object)map.get("Direction");
			int horzCount = 0;
			
			if(objDirection instanceof String) {
				isHorizontal = objDirection.equals("H") ? true : false;
			}
			
			Object objTableDataSet1 = (Object)map.get("1");
			Object objTableDataSet2 = (Object)map.get("2");
			
			if(isHorizontal) {
				if(isHorizontal && htmlHorizantalFlag == false) {
					horzCount = 1;
					htmlHorizantalFlag = true;
					joinTableStrBuffer.append("<table width='100%'><tr>");
				}
				
				if(objTableDataSet1 instanceof TableDataSet) {
					if(isHorizontal) {
						joinTableStrBuffer.append("<td valign='top' width='" + width[0] + "%'>");
					}
					joinTableStrBuffer.append(createHtmlTable((TableDataSet)objTableDataSet1, false));

					if(isHorizontal) {
						joinTableStrBuffer.append("</td>");
					}
				} else {
					joinTableStrBuffer.append(joinTable((HashMap<String, Object>)objTableDataSet1));
				}

				if(objTableDataSet2 instanceof TableDataSet) {
					if(isHorizontal) {
						joinTableStrBuffer.append("<td valign='top' width='" + width[1] + "%'>");
					}
					
					joinTableStrBuffer.append(createHtmlTable((TableDataSet)objTableDataSet2, false));
					
					if(isHorizontal) {
						joinTableStrBuffer.append("</td>");
					}
				} else {
					joinTableStrBuffer.append(joinTable((HashMap<String, Object>)objTableDataSet2));
				}
				
				if(isHorizontal) {
					joinTableStrBuffer.append("</tr></table>");
				}
				isHorizontal = false;
			} else {
				if(horzCount > 0) {
					joinTableStrBuffer.append("</tr></table>");
				}
				
				if(objTableDataSet1 instanceof TableDataSet) {
					joinTableStrBuffer.append(createHtmlTable((TableDataSet)objTableDataSet1, false));
				} else {
					joinTableStrBuffer.append(joinTable((HashMap<String, Object>)objTableDataSet1));
				}

				if(objTableDataSet2 instanceof TableDataSet) {
					joinTableStrBuffer.append(createHtmlTable((TableDataSet)objTableDataSet2, false));
				} else {
					joinTableStrBuffer.append(joinTable((HashMap<String, Object>)objTableDataSet2));
				}
			}
		} catch(Exception e) {
			logger.error("Exception in joinTable:" + e);
		}
		
		htmlHorizantalFlag = false;
		htmlVerticalFlag = false;
		
		return joinTableStrBuffer;
	}

	public void process(HashMap<String, Object> dataMap) {
		try {
			if(dataMap != null && dataMap.size() > 0) {
				strExcelReport.append(headerStrBuffer);
				
				for(int i = 0; i < dataMap.size(); i++) {
					Object obj = dataMap.get(String.valueOf(i));

					if(obj instanceof TableDataSet) {
						TableDataSet tds = (TableDataSet)obj;
						strExcelReport.append(createHtmlTable(tds, false));
					} else if(obj instanceof HashMap) {
						strExcelReport.append(joinTable((HashMap<String, Object>)obj));
					} else if(obj.equals("PAGE_BREAK")) {
						strExcelReport.append("<p class='break'></P>");
					}
				}
				
				strExcelReport.append(footerStrBuffer + "</td></tr></table></body></html>");
			}
			
			logger.info("strExcelReport:" + strExcelReport);
		} catch(Exception e) {
			logger.error("Exception in process:" + e);
		}
	}

	private String replaceNewLineCharWithBr(String result) {
		try {
			if(result.contains("\n")) {
				result = result.replaceAll("\n", "<br>");
			}
		} catch(Exception e) {
			logger.error("Exception in replaceNewLineCharWithBr:" + e);
		}
		
		return result;
	}

	private String RGBToHex(Color color) {
		String hexColor = "";
		try {
			if(color != null) {
				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();
				hexColor += toHex(red) + toHex(green) + toHex(blue);
			}
		} catch(Exception e) {
			logger.error("Exception in RGBToHex:" + e);
		}
		return hexColor;
	}

	private String toHex(int num) {
		String colorToHex = "";
		try {
			if(num == 0) { return "00"; }
			
			num = Math.max(0, num);
			num = Math.min(num, 255);
			num = Math.round(num);
			
			String colorToHex1 = String.valueOf("0123456789ABCDEF".charAt((num - num % 16) / 16));
			String colorToHex2 = String.valueOf("0123456789ABCDEF".charAt(num % 16));
			
			colorToHex = colorToHex1 + colorToHex2;
		} catch(Exception e) {
			logger.error("Exception in toHex:" + e);
		}
		return colorToHex;
	}
}