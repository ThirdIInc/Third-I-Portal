/* Bhushan Dasare Nov 27, 2009 */

package org.paradyne.lib.ireportV2;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

import org.paradyne.lib.Utility;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class XLSGenerate {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(XLSGenerate.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	private boolean showPageNo = false;
	private int pageNoHPosition, pageNoVPosition;
	TreeMap<Integer, String> mapTable = new TreeMap<Integer, String>();
	public StringBuffer strExcelReport = new StringBuffer();
	private StringBuffer headerStrBuffer = new StringBuffer();
	private StringBuffer footerStrBuffer = new StringBuffer();
	boolean htmlHorizantalFlag = false;
	boolean htmlVerticalFlag = false;
	ReportDataSet rds;
	public XLSGenerate(ReportDataSet rdsDoc) {
		try {
			this.rds=rdsDoc;
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
			int bodyFontStyle =rds.getTableBodyFontStyle();
			float bodyFontSize =rds.getTableBodyFontSize();
			//strExcelReport.append("<style>.break { page-break-before: always; }</style>");
			strExcelReport.append("<html><head>");		
			strExcelReport.append("<style>.break { page-break-before: always; }");
			strExcelReport.append(".tableBody{padding-left:5px;padding-right:5px;font-family: "+rds.getTableBodyFontFace()+";font-size: "+rds.getTableBodyFontSize()+"pt;	font-weight: "+getStyleString(rds.getTableBodyFontStyle())+";}");
			strExcelReport.append(".tableHeader{padding-left:5px;padding-right:5px;border-top:0pt solid red;border-bottom:0pt solid red;border-left:0px solid red;border-right:0px solid red;font-family: "+rds.getTableHeaderFontFace()+";font-size: "+rds.getTableHeaderFontSize()+"pt;	font-weight: "+getStyleString(rds.getTableHeaderFontStyle())+";}");
			strExcelReport.append(".totalColumn{border-top:0.5pt solid #5D5D5D;padding-left:5px;padding-right:5px;font-family: "+rds.getTableBodyFontFace()+";font-size: "+rds.getTableBodyFontSize()+"pt;	font-weight: "+getStyleString(rds.getTableBodyFontStyle())+";}");
			strExcelReport.append(".line{padding-left:5px;border-top:0.5pt solid red;border-bottom:0.5pt solid red;border-left:0px solid red;border-right:0px solid red;font-family: "+rds.getTableHeaderFontFace()+";font-size: "+rds.getTableHeaderFontSize()+"pt;	font-weight: "+getStyleString(rds.getTableHeaderFontStyle())+";}");
			strExcelReport.append(".rowSum{padding-left:5px;padding-right:5px;font-family: "+rds.getTableHeaderFontFace()+";font-size: "+rds.getTableHeaderFontSize()+"pt;	font-weight: "+getStyleString(rds.getTableHeaderFontStyle())+";}");
			strExcelReport.append(".colSum{padding-left:5px;padding-right:5px;font-family: "+rds.getTableHeaderFontFace()+";font-size: "+rds.getTableHeaderFontSize()+"pt;	font-weight: "+getStyleString(rds.getTableHeaderFontStyle())+";}");
			strExcelReport.append(".reportHeader{border-top:0.5pt solid #dbdbdb;border-bottom:0px solid black;border-left:0px solid black;border-right:0px solid black;font-family: "+rds.getReportHeaderFontFace()+";font-size: "+rds.getReportHeaderFontSize()+"pt;	font-weight: "+getStyleString(rds.getReportHeaderFontStyle())+"; text-align: center;vertical-align: middle;height: 25;}");
			strExcelReport.append(".companyName{font-family: "+rds.getCompanyNameFontFace()+";font-size: "+rds.getCompanyNameFontSize()+"pt;	font-weight: "+getStyleString(rds.getCompanyNameFontStyle())+";text-align: right; }");
			strExcelReport.append("</style></head>");
			strExcelReport.append("<body onload='parent.alertsize(document.body.scrollHeight);' style='page-size:" + pageSize+ ";page-orientation:" + pageOrientation + "';  leftmargin='"+leftMargin + "' topmargin='" + topMargin + "' rightmargin='"
					+ rightMargin + "' bottommargin='" + bottomMargin + "'>");		
			strExcelReport.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
			strExcelReport.append("<tr>");
			strExcelReport.append("<td>");
			/*strExcelReport.append("<thead>");
			strExcelReport.append("<tr><td>");*/
			
			if(rds.isReportHeaderRequired()){
			if(rds.isLogoRequired()&& rds.isCompanyNameRequired()){
				
				strExcelReport.append("<table width='100%' border='0' cellspacing='0' cellpadding='1'>");
				//strExcelReport.append("<tr>");
				String logoTD="";
				logoTD="<td width='50%' colspan='2'> <img src='"+rds.getImageHttpPath()+rds.getLogoPath()+"'></td>";
				String companyTD="";
				companyTD="<td width='50%' rowspan='3' colspan="+(rds.getTotalColumns()-2)+" align='right'><span class='companyName'>"+replaceNewLineCharWithBr(checkNull(rds.getCompanyName())+"</span><br>");
				if(rds.isCompanyAddressRequired())
					companyTD+="<span class='tableBody'>"+replaceNewLineCharWithBr(checkNull(rds.getCompanyAddress()))+"</span>";
				companyTD+="</td>";
				
				if (rds.getLogoAlign() == 'L') {
					if (rds.getCompanyNameAlign() == 'R') {
						strExcelReport.append("<tr>");
						strExcelReport.append(logoTD);
						strExcelReport.append(companyTD);
						strExcelReport.append("</tr>");
					}else if (rds.getCompanyNameAlign() == 'C') {
						strExcelReport.append("<tr>");
						strExcelReport.append(logoTD);
						strExcelReport.append(companyTD);
						strExcelReport.append("<td></td>");
						strExcelReport.append("</tr>");
					}
				} else if (rds.getLogoAlign() == 'R') {
					if (rds.getCompanyNameAlign() == 'L') {
						strExcelReport.append("<tr>");
						strExcelReport.append(companyTD);
						strExcelReport.append(logoTD);
						strExcelReport.append("</tr>");
					}else if (rds.getCompanyNameAlign() == 'C') {
						strExcelReport.append("<tr>");
						strExcelReport.append("<td></td>");
						strExcelReport.append(companyTD);
						strExcelReport.append(logoTD);
						strExcelReport.append("</tr>");
					}
				} else if (rds.getLogoAlign() == 'C') {
					if (rds.getCompanyNameAlign() == 'L') {
						strExcelReport.append("<tr>");
						strExcelReport.append(companyTD);
						strExcelReport.append("<td></td>");
						strExcelReport.append(logoTD);
						strExcelReport.append("</tr>");
					}else if (rds.getCompanyNameAlign() == 'R') {
						strExcelReport.append("<tr>");
						strExcelReport.append("<td></td>");
						strExcelReport.append(logoTD);
						strExcelReport.append(companyTD);
						strExcelReport.append("</tr>");
					}
					else if (rds.getCompanyNameAlign() == 'C') {
						strExcelReport.append("<tr>");
						strExcelReport.append("<td align='center' width='50%'> <img src='"+rds.getImageHttpPath()+rds.getLogoPath()+"'></td>");
						strExcelReport.append("</tr>");
						strExcelReport.append("<tr>");
						strExcelReport.append("<td align='center' width='50%' class='companyName'>"+rds.getCompanyName());
						if(rds.isCompanyAddressRequired())
						strExcelReport.append(rds.getCompanyAddress());
						
						strExcelReport.append("</td>");
						strExcelReport.append("</tr>");
					}
					
				}
				
				strExcelReport.append("</table>");
			}else if(rds.isLogoRequired()){
				strExcelReport.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
				strExcelReport.append("<tr>");
				strExcelReport.append("<td align='"+getAlignmentString(rds.getLogoAlign())+"' width='100%'>");
				strExcelReport.append("<img src='"+rds.getImageHttpPath()+rds.getLogoPath()+"'>");
				strExcelReport.append("</td>");
				strExcelReport.append("</tr>");
				strExcelReport.append("</table>");
			}else if (rds.isCompanyNameRequired()){
				strExcelReport.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
				strExcelReport.append("<tr>");
				strExcelReport.append("<td align='"+getAlignmentString(rds.getCompanyNameAlign())+"' width='100%'>");
				strExcelReport.append("<td class='companyName'>"+rds.getCompanyName());
				if(rds.isCompanyAddressRequired())
					strExcelReport.append(rds.getCompanyAddress());
				
				strExcelReport.append("</td>");
				strExcelReport.append("</tr>");
				strExcelReport.append("</table>");
			}else{
				strExcelReport.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
				strExcelReport.append("<tr>");
				strExcelReport.append("<td width='100%'>");
				strExcelReport.append("<img src='"+rds.getImageHttpPath()+rds.getHeaderImagePath()+"'>");
				strExcelReport.append("</td>");
				strExcelReport.append("</tr>");
				strExcelReport.append("</table>");
			}
			
			if(!rds.getReportName().equals("")){
				strExcelReport.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
				strExcelReport.append("<tr>");
				strExcelReport.append("<td colspan='"+rds.getTotalColumns()+"' class='reportHeader' height='15' width='100%'><u>"+rds.getReportName()+"</u>");
				strExcelReport.append("</td>");
				strExcelReport.append("</tr>");
				strExcelReport.append("</table>");
				strExcelReport.append("</td>");
				strExcelReport.append("</tr>");
			}
		}
		}catch (Exception e) {
			// TODO: handle exception
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

	public StringBuffer createHtmlTable(org.paradyne.lib.ireportV2.TableDataSet tds) {
		StringBuffer createHtmlTableStrBuffer = new StringBuffer();
		if (tds.getBlankRowsAbove() > 0) {
			for (int i = 0; i < tds.getBlankRowsAbove(); i++) {
				createHtmlTableStrBuffer.append("<table border='0'  cellspacing='0'  cellpadding='1' width='100%'>"
				+ "<tr><td height='20'></td></tr></table>");
			}
		}
		//Font bodyFont = null;
		//Font headerFont = null;
		Font sumFont = null;
		Object[][] data = tds.getData();
		int headerColSpan[] =tds.getHeaderColSpan();
		
			int[] width = tds.getCellWidth();// width
			if (width != null) {
				for (int i = 0; i < width.length; i++) {
					width[i] = width[i];
				}
			}
			int[] alignDigit = tds.getCellAlignment();
			String[] cellAlign = null;
			if (width != null) {
				cellAlign = new String[alignDigit.length];
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
			BaseColor clrBody = tds.getBodyBGColor();// background color for cell
			
			BaseColor clrHeader = tds.getHeaderBGColor();// background color for cell
			//bodyFont = tds.getBodyFontDetails();// font 4 body of the table with
			// font,size style and color
			String header[] = tds.getHeader();// table headers
			String headerData[][] = tds.getHeaderData();// table headers
			int headerBorder = tds.getHeaderBorderDetail();// table headers border
			if(headerBorder>0){
				headerBorder=1;
			}
			int bodyBorder = tds.getBorderDetail();// table headers border
			if(bodyBorder>0){
				bodyBorder=1;
			}
			//Boolean border = tds.getBorder();// border yes no
			//int borderValue = 0;
			/*if (border) {
				borderValue = 1;
			}*/
			//String body_fontColor="FFFFFF";
			Object[] columColorData = tds.getColumnColor();// if some colum required special color
			// column no which is to be colored
			int colNo = Integer.parseInt(String.valueOf(columColorData[0]));
			BaseColor colBackColor = (BaseColor) columColorData[1];// background color
			//Font colFontColor = (Font) columColorData[2];// fontcolor of that// column
			//logger.info("colFontColor $$$$$$$$$$$$$$$$     "+colFontColor);
			//headerFont = tds.getHeaderFontDetails();
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
			/*String headerFontSize="9pt";
			String bodyFontSize="8pt";*/

		//createHtmlTableStrBuffer.append("<table border='" + borderValue
				createHtmlTableStrBuffer.append("<table border='0'"
				+ " cellspacing='0'  cellpadding='0' width='100%'>");
		if (header != null && header.length > 0) {
			createHtmlTableStrBuffer.append("<tr><td><table cellspacing='0' border='1' cellpadding='0' width='100%'><tr>");
			for (int i = 0; i < header.length; i++) {
				/*String header_Font = "";*/
				String header_color = "";
				// font mentioned or default font for the table headings
				/*if(tds.getHeaderFontSize()!=-1)
				{
					headerFontSize=String.valueOf(tds.getHeaderFontSize())+"pt";
				}else if(rds.getTableHeaderFontSize()!=-1){
					headerFontSize=String.valueOf(rds.getTableHeaderFontSize())+"pt";
				}*/
				/*if (!(headerFont.getFamily() ==null)) {
					header_Font = RGBtoHex(headerFont.getColor());
				} else {
					header_Font = "000000";
				}*/
				/*if (!(bodyFont.getFamily() ==null)) {
					body_fontColor = RGBtoHex(bodyFont.getColor());
				} else {
					body_fontColor = "000000";
				}*/
				if (clrHeader == null) {
					header_color = "FFFFFF";
				} else {
					header_color = RGBToHex(clrHeader);
				}
				String colspan="";
				if (headerColSpan != null)
					colspan="colspan='"+(headerColSpan[i])+"'";
				if(Utility.checkNull(header[i]).equals("")){
					header[i]="&nbsp;";
				}
				createHtmlTableStrBuffer.append("<td class='tableHeader' "+colspan
						+ " align='"+cellAlign[i]+"' bgcolor='#B4B4B4' style='border: 1px solid #000000;'>" 
						//+ "'><font size='"+headerFontSize+"' face='"+rds.getTableHeaderFontFace()+"' color='#" + header_Font + "'><b>" + header[i]
						+ "" + header[i]
						+ "</b></td>");

			}
			createHtmlTableStrBuffer.append("</tr></table></td></tr>");
		}
		if (headerData != null && headerData.length > 0) {
			
			for (int i = 0; i < headerData.length; i++) { 
				createHtmlTableStrBuffer.append("<tr><td><table cellspacing='0' border='"+headerBorder+"'  cellpadding='0' width='100%'><tr>");
				for (int j = 0; j < headerData[0].length; j++) { 
				/*String header_Font = "";*/
				String header_color = "";
				// font mentioned or default font for the table headings
				/*if(tds.getHeaderFontSize()!=-1)
				{
					headerFontSize=String.valueOf(tds.getHeaderFontSize())+"pt";
				}else if(rds.getTableHeaderFontSize()!=-1){
					headerFontSize=String.valueOf(rds.getTableHeaderFontSize())+"pt";
				}*/
				/*if (!(headerFont.getFamily() ==null)) {
					header_Font = RGBtoHex(headerFont.getColor());
				} else {
					header_Font = "000000";
				}*/
				/*if (!(bodyFont.getFamily() ==null)) {
					body_fontColor = RGBtoHex(bodyFont.getColor());
				} else {
					body_fontColor = "000000";
				}*/
				if (clrHeader == null) {
					header_color = "FFFFFF";
				} else {
					header_color = RGBToHex(clrHeader);
				}
				String colspan="";
				if (headerColSpan != null)
					colspan="colspan='"+(headerColSpan[i])+"'";
				if(Utility.checkNull(headerData[i][j]).equals("")){
					headerData[i][j]="&nbsp;";
				}
				createHtmlTableStrBuffer.append("<td class='tableHeader' "+colspan
						+ " align='"+cellAlign[i]+"' bgcolor='#B4B4B4' style='border: 1px solid #000000;'>" 
						//+ "'><font size='"+headerFontSize+"' face='"+rds.getTableHeaderFontFace()+"' color='#" + header_Font + "'><b>" + header[i]
						+ "" + headerData[i][j]
						+ "</b></td>");

			}
				createHtmlTableStrBuffer.append("</tr></table></td></tr>");
			}
			
		}
		String column_fontcolor = "FFFFFF";
		int bodyFontStyle =rds.getTableBodyFontStyle();
		
		if(tds.getBodyFontStyle()!=-1){
			bodyFontStyle=tds.getBodyFontStyle();
		}
		
		/*String bodyFontFace="HELVETICA";
		
 		String sumfontcolor = "000000";
		*/
		String columnBackColor="FFFFFF";
	 	if (data != null && data.length > 0) {
	 		
	 		if(tds.isBorderLines()) {
				createHtmlTableStrBuffer.append(addLine("#5A5A5A","border-bottom"));
				}
	 		createHtmlTableStrBuffer.append("<tr><td colspan='"+rds.getTotalColumns()+"'><table cellspacing='0' border='"+bodyBorder+"'  cellpadding='0' width='100%'>");
			for (int i = 0; i < data.length; i++) {
				
				/*if(tds.getBodyFontDetails().getSize()!=-1){
					bodyFontSize=String.valueOf(tds.getBodyFontDetails().getSize())+"pt";
				}else if(rds.getTableBodyFontSize()!=-1){
					bodyFontSize=rds.getTableBodyFontSize()+"pt";
				}*/
				/*if(tds.getBodyFontDetails().getBaseFont()!=null){
					bodyFontFace=tds.getBodyFontDetails().getFamilyname();
				}else if(rds.getTableBodyFontFace()!=null){
					bodyFontFace=rds.getTableBodyFontFace();
				}*/
				
				
				createHtmlTableStrBuffer.append("<tr>");
				for (int j = 0; j < cellAlign.length; j++) {
					if (!(rowsAvg == null) && j == cellAlign.length - tempOne)
					{
						/*if (!(sumFont.getFamily() ==null))
						{
							sumfontcolor=RGBtoHex(sumFont.getColor());
						}*/
						createHtmlTableStrBuffer.append("<td width='"
								+ width[j]
								+ "%' align='"
								+ cellAlign[j]
								//+ "'><font size='"+bodyFontSize+"' face='"+bodyFontFace+"' color='#"
								+ "' class='tableBody' "
								//+ sumfontcolor
								+ "> "
								+ checkNull(String.valueOf(Utility
										.twoDecimals(rowAvgOut
												/ rowsAvg.length)))
								+ "</td>");
						
					}
					else if (!(rowSum == null) && j == cellAlign.length - tempTwo) {
						/*if (!(sumFont.getFamily() ==null))
						{
							sumfontcolor=RGBtoHex(sumFont.getColor());
							
						}*/
						createHtmlTableStrBuffer.append("<td width='"
								+ width[j]
								+ "%' align='"
								+ cellAlign[j]
								+ "' class='rowSum'"
								//+ "'><font size='"+bodyFontSize+"' face='"+bodyFontFace+"'  color='#"
								//+ sumfontcolor
								+ ">"
								+ checkNull(String.valueOf(formatter.format(rowSumOut)))
								+ "</td>");
					}
					
					else {
						if ((!(colNo == -1)) && j == colNo) {
							
							createHtmlTableStrBuffer.append("<td  class='tableHeader' width='"
									+ width[j]
									+ "%' align='"
									+ cellAlign[j]
									//+ "' bgcolor='#"+columnBackColor+"'>"
									            //+ "' bgcolor='#"+columnBackColor+"'>"
									+ replaceNewLineCharWithBr(checkNull(String.valueOf(data[i][j])))
									+ "</td>");
						}
						else {
							
								// font mentioned for table data else take default font
							String colspan="";
						//	logger.info("columnBackColor=="+columnBackColor);
							columnBackColor=RGBToHex(clrBody);
							if(!columnBackColor.equals("")){
								columnBackColor="background-color:#"+columnBackColor+"";
							}
							if(tds.getCellColSpan()!=null){
								colspan ="colspan='"+tds.getCellColSpan()[j]+"'";
							}
							String format="";
							if(cellAlign[j].equals("right")){
								format=" mso-number-format:0\\.00; ";
							}
							try{
								createHtmlTableStrBuffer.append("<td   "+colspan+" width='"
										+ width[j]
										+ "%' align='"
										+ cellAlign[j]
										//+ "' > <font size='"+bodyFontSize+"' face='"+bodyFontFace+"'  color='#"
										+ "' "
										+" style='"+format+" padding-left:5px;font-family: "+rds.getTableBodyFontFace()+";font-size: "+rds.getTableBodyFontSize()+"pt;	font-weight: "+getStyleString(bodyFontStyle)+";  "+columnBackColor+"' >"
										//+ body_fontColor
										//+ "'>"
										+ replaceNewLineCharWithBr(checkNull(String.valueOf(data[i][j])))
										+ "</td>");
							}catch (Exception e) {
								// TODO: handle exception
							}
								
						}
						if (!(rowSum == null)) {
							for (int l = 0; l < rowSum.length; l++) {
								if (j == rowSum[l]) {
									if (!String.valueOf(data[i][j]).equals(""))
										rowSumOut = Double.parseDouble(formatter.format(rowSumOut+ Double.parseDouble(checkZero(String.valueOf(data[i][j])))));
								}
							}
						}
						// rowwise average for mentioned rows
						if (!(rowsAvg == null)) {
							for (int l = 0; l < rowsAvg.length; l++) {
								if (j == rowsAvg[l]) {
									if (!String.valueOf(data[i][j]).equals(""))
										rowAvgOut = Double.parseDouble(formatter.format(rowAvgOut+ Double.parseDouble(checkZero(String.valueOf(data[i][j])))));
								}
							}
						}
						// columwise sum for mentioned columns
						if (!(colsSum == null)) {
							for (int k = 0; k < colsSum.length; k++) {
								if (j == colsSum[k]) {
									if (!String.valueOf(data[i][j]).equals(""))
										sumOut[k] = Double.parseDouble(formatter.format(sumOut[k]+ Double.parseDouble(checkZero(String.valueOf(data[i][j])))));
								}
							}
						}
						// columwise average for mentioned columns
						if (!(colsAvg == null)) {
							for (int k = 0; k < colsAvg.length; k++) {
								if (j == colsAvg[k]) {
									if (!String.valueOf(data[i][j]).equals(""))
										avgOut[k] = Double.parseDouble(formatter.format(avgOut[k]+ Double.parseDouble(checkZero(String.valueOf(data[i][j])))));
								}
							}
						}
			 		}
					
				}
				rowSumOut = 0.00;
				rowAvgOut = 0.00;
				
				createHtmlTableStrBuffer.append("</tr>");
				
			}
			if(tds.isBorderLines()){
				createHtmlTableStrBuffer.append(addLine("#000000","border-top"));
				}
			
		
		if (!(colsSum == null)) {
			int k = 0;
			//String sumFontColor = "000000";
			createHtmlTableStrBuffer.append("<tr>");
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < cellAlign.length; j++) {
					if (k < colsSum.length) {
						if (j == colsSum[k]) {
							/*if (!(sumFont.getFamily() ==null)) {
								sumFontColor = RGBtoHex(sumFont.getColor());
							}*/
							createHtmlTableStrBuffer.append("<td  class='colSum' width='"
									+ width[j]
									+ "%' align='"
									+ cellAlign[j]
									//+ "'><font size='"+bodyFontSize+"' face='"+bodyFontFace+"' color='#"
									+ "'>"
									//+ sumFontColor
									//+ "'>"
									+ checkNull(String.valueOf(Utility 
											.twoDecimals(formatter.format(sumOut[k]))))
									+ "</td>");
							k++;
						} else {
							String colValue="";
							if(j==colsSum[0]-1)
								colValue="Total";
							createHtmlTableStrBuffer.append("<td class='colSum' width='"
									+ width[j] + "%' align='" + cellAlign[j]+"'>"
									//+ "'><font color='#"+sumFontColor+"'>"
									+ checkNull(colValue)
									+ "</td>");
						}
					} else {
						createHtmlTableStrBuffer.append("<td  class='colSum' width='"
								+ width[j] + "%' align='" + cellAlign[j]+"'>"
								//+ "'><font color='#"+sumFontColor+"'>"
								+ checkNull(String.valueOf(""))
								+ "</td>");
					}
				}
			}
			createHtmlTableStrBuffer.append("</tr>");
		}
		if (!(colsAvg == null)) {
			int k = 0;
			//String colAvgFontColor="000000";
			createHtmlTableStrBuffer.append("<tr>");
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < cellAlign.length; j++) {
					if (k < colsAvg.length) {
						if (j == colsAvg[k]) {
							/*if (!(sumFont.getFamily() ==null)) {
								//colAvgFontColor=RGBtoHex(sumFont.getColor());
							}*/ 
							createHtmlTableStrBuffer.append("<td class='sumFont' width='"
									+ width[j]
									+ "%' align='"
									+ cellAlign[j]+ "'>"
									//+ "'><font color='#"+colAvgFontColor+"'>"
									+ checkNull(String.valueOf(Utility
											.twoDecimals(formatter.format(avgOut[k]
													/ colsAvg.length))))
									+ "</td>");
							k++;
						} else {
							createHtmlTableStrBuffer.append("<td class='sumFont' width='"
									+ width[j]
									+ "%' align='"
									+ cellAlign[j]+ "'>"
									//+ "'><font color='#"+colAvgFontColor+"'>"
									+ checkNull(String.valueOf(""))
									+ "</td>");
						}
					} else
					{
						createHtmlTableStrBuffer.append("<td class='sumFont' width='"
								+ width[j]
								+ "%' align='"
								+ cellAlign[j]+"'>"
								//+ "'><font color='#"+colAvgFontColor+"'>"
								+ checkNull(String.valueOf(""))
								+ "</td>");
					}
				}
			}
			createHtmlTableStrBuffer.append("</tr>");
		}
		createHtmlTableStrBuffer.append("</table></td></tr></table>");
	}	
	 	else	createHtmlTableStrBuffer.append("</table>");
		if (tds.getBlankRowsBelow() > 0) {
			for (int i = 0; i < tds.getBlankRowsBelow(); i++) {
				createHtmlTableStrBuffer
						.append("<table border='0'   cellspacing='0'  cellpadding='0' width='100%'>"
								+ "<tr><td height='20'></td></tr></table>");
			}
		}
		return createHtmlTableStrBuffer;
	}

	public void genFooter(Object obj) {
		try {
			if(obj instanceof TableDataSet) {
				footerStrBuffer.append(createHtmlTable((TableDataSet)obj));
			} else if(obj instanceof HashMap) {
				footerStrBuffer.append(joinTable((HashMap<String, Object>)obj));
			}else if(obj instanceof Vector){
				
							
		   }
		} catch(Exception e) {
			logger.error("Exception in genFooter:" + e);
		}
	}

	public void genHeader(Object obj) {
		try {
			if(obj instanceof TableDataSet) {
				headerStrBuffer.append(createHtmlTable((TableDataSet)obj));
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
					joinTableStrBuffer.append(createHtmlTable((TableDataSet)objTableDataSet1));

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
					
					joinTableStrBuffer.append(createHtmlTable((TableDataSet)objTableDataSet2));
					
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
					joinTableStrBuffer.append(createHtmlTable((TableDataSet)objTableDataSet1));
				} else {
					joinTableStrBuffer.append(joinTable((HashMap<String, Object>)objTableDataSet1));
				}

				if(objTableDataSet2 instanceof TableDataSet) {
					joinTableStrBuffer.append(createHtmlTable((TableDataSet)objTableDataSet2));
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
			if (dataMap != null && dataMap.size() > 0) {
				for (int i = 0; i < dataMap.size(); i++) {
					Object obj = dataMap.get(String.valueOf(i));

					if (obj instanceof TableDataSet) {
						TableDataSet tds = (TableDataSet) obj;
						strExcelReport.append(createHtmlTable(tds));
					} else if (obj instanceof HashMap) {
						System.out.println("calling join table");
						strExcelReport
								.append(joinTable((HashMap<String, Object>) obj));
					} /*else if (obj.equals("PAGE_BREAK")) {
						strExcelReport.append("<p class='break'></P>");
					}*/
					else if (obj instanceof Vector) {
						System.out.println("calling join table");
						strExcelReport
								.append(addLine((Vector) obj));
					}
					
				}
			}
			if(!rds.getGeneratedByText().equals("")){
				
				strExcelReport.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
				strExcelReport.append("<tr><td class='tableBody' colspan="+rds.getTotalColumns()+" width='100%'>&nbsp;</td></tr><tr>");
				strExcelReport.append("<td class='tableBody' colspan="+rds.getTotalColumns()+" width='100%' style='border-top: 0.5pt solid #000000;border-left: 0;border-right: 0;border-bottom: 0;'>");
				strExcelReport.append(rds.getGeneratedByText());
				strExcelReport.append("</td>");
				strExcelReport.append("</tr>");
				strExcelReport.append("</table>");
			}
			if(!rds.getFooterImagePath().equals("")){
				
				strExcelReport.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
				strExcelReport.append("<tr>");
				strExcelReport.append("<td width='100%'>");
				strExcelReport.append("<img src='"+rds.getImageHttpPath()+rds.getFooterImagePath()+"'>");
				strExcelReport.append("</td>");
				strExcelReport.append("</tr>");
				strExcelReport.append("</table>");
			}
			
			strExcelReport.append("</td>");
			strExcelReport.append("</tr>");
			strExcelReport.append("</table>");
			strExcelReport.append("</body>");
			strExcelReport.append("</html>");
			/*String str = " <script> "
					+ "	 printpreview();"
					+ "function printpreview() "
					+ "{ "
					+ "   var OLECMDID = 7; "
					+ " /* OLECMDID values: "
					+ "  * 6 - print "
					+ "* 7 - print preview "
					+ " * 1 - open window "
					+ " * 4 - Save As "
					+ "  "
					+ " var PROMPT = 1;"
					+ " var WebBrowser = '<OBJECT ID=\"WebBrowser1\" WIDTH=\"0\" HEIGHT=\"0\" CLASSID=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></OBJECT>'; "
					+ "  document.body.insertAdjacentHTML('beforeEnd', WebBrowser); "
					+ " WebBrowser1.ExecWB(OLECMDID, PROMPT); "
					+ " WebBrowser1.outerHTML = \"\"; " + " } </script> ";*/

			//strExcelReport.append(str);
			//logger.info("strExcelReport------------ >>\n"+strExcelReport);
		} catch (Exception e) {
			logger.error("Exception in process:");
			e.printStackTrace();
		}
	}
	private StringBuffer addLine(Vector obj) {

		StringBuffer lineStrBuffer = new StringBuffer();
		
		
	/*	String color="";
	  for (int j = 0; j < obj.size(); j++) {
			Object tempObj = obj.get(j);
			if(tempObj instanceof BaseColor && tempObj!=null){
				 color=RGBtoHex((BaseColor)tempObj);
			}
		}*/
		lineStrBuffer.append("<table width='100%'><tr><td colspan='"+rds.getTotalColumns()+"' style='width: 100%; height:1pt;border-top: 0.5pt solid #cccccc'>&nbsp;</td></tr></table>");
		return lineStrBuffer;
	}
	private StringBuffer addLine(String bgcolor,String border) {
		
		StringBuffer lineStrBuffer = new StringBuffer();
		
		
		/*	String color="";
	  for (int j = 0; j < obj.size(); j++) {
			Object tempObj = obj.get(j);
			if(tempObj instanceof BaseColor && tempObj!=null){
				 color=RGBtoHex((BaseColor)tempObj);
			}
		}*/
		lineStrBuffer.append("<tr><td colspan='"+rds.getTotalColumns()+"' style='width: 100%; height:1pt;"+border+": 0.5pt solid "+bgcolor+"'>&nbsp;</td></tr>");
		return lineStrBuffer;
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

	private String RGBToHex(BaseColor color) {
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
	private String getAlignmentString(char logoAlign) {
		switch (logoAlign) {
		
		case 'C':
			
			return "center";
		case 'R':
			
			return "right";
		case 'L':
			
			return "left";

		}
		
		return null;
	}
	private String getAlignmentString(int align) {
		switch (align) {
		
		case 1:
			
			return "center";
		case 2:
			
			return "right";
		case 0:
			
			return "left";
			
		}
		
		return null;
	}
	private String getStyleString(int style) {
		switch (style) {
		
		case 0:
			return "NORMAL";
		case 1:
			return "BOLD";
		case 2:
			return "ITALIC";
		case 3:
			return "BOLDITALIC";
		case 4:
			return "UNDERLINE";
		case 5:
			return "BOLD";
		case 8:
			return "STRIKETHRU";
			
		}
		
		return null;
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