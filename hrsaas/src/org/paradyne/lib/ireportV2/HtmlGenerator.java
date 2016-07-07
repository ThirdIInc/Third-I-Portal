package org.paradyne.lib.ireportV2;

import java.util.HashMap;
import java.util.Vector;

import org.paradyne.lib.Utility;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
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
	ReportDataSet rds;
	public HtmlGenerator(ReportDataSet rdsDoc) {
		this.rds=rdsDoc;
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
		createTableStrBuffer.append("<html><head>");		
		createTableStrBuffer.append("<style>.break { page-break-before: always; }");
		createTableStrBuffer.append(".tableBody{padding-left:5px;padding-right:5px;font-family: "+rds.getTableBodyFontFace()+";font-size: "+rds.getTableBodyFontSize()+"pt;	font-weight: "+getStyleString(rds.getTableBodyFontStyle())+";}");
		createTableStrBuffer.append(".tableHeader{padding-left:5px;padding-right:5px;font-family: "+rds.getTableHeaderFontFace()+";font-size: "+rds.getTableHeaderFontSize()+"pt;	font-weight: "+getStyleString(rds.getTableHeaderFontStyle())+";}");
		createTableStrBuffer.append(".totalColumn{padding-left:5px;padding-right:5px;border-top:0.5pt solid #5D5D5D;font-family: "+rds.getTableBodyFontFace()+";font-size: "+rds.getTableBodyFontSize()+"pt;	font-weight: "+getStyleString(rds.getTableBodyFontStyle())+";}");
		createTableStrBuffer.append(".line{padding-left:5px;border-top:0.5pt solid red;border-bottom:0.5pt solid red;border-left:0px solid red;border-right:0px solid red;font-family: "+rds.getTableHeaderFontFace()+";font-size: "+rds.getTableHeaderFontSize()+"pt;	font-weight: "+getStyleString(rds.getTableHeaderFontStyle())+";}");
		createTableStrBuffer.append(".rowSum{padding-left:5px;padding-right:5px;font-family: "+rds.getTableHeaderFontFace()+";font-size: "+rds.getTableHeaderFontSize()+"pt;	font-weight: "+getStyleString(rds.getTableHeaderFontStyle())+";}");
		createTableStrBuffer.append(".colSum{padding-left:5px;padding-right:5px;font-family: "+rds.getTableHeaderFontFace()+";font-size: "+rds.getTableHeaderFontSize()+"pt;	font-weight: "+getStyleString(rds.getTableHeaderFontStyle())+";}");
		createTableStrBuffer.append(".reportHeader{border-top:0.5pt solid #dbdbdb;border-bottom:0px solid black;border-left:0px solid black;border-right:0px solid black;font-family: "+rds.getReportHeaderFontFace()+";font-size: "+rds.getReportHeaderFontSize()+"pt;	font-weight: "+getStyleString(rds.getReportHeaderFontStyle())+"; text-align: center;vertical-align: middle;}");
		createTableStrBuffer.append(".companyName{font-family: "+rds.getCompanyNameFontFace()+";font-size: "+rds.getCompanyNameFontSize()+"pt;	font-weight: "+getStyleString(rds.getCompanyNameFontStyle())+";text-align: right; }");
		createTableStrBuffer.append("</style></head>");
		createTableStrBuffer.append("<body onload='parent.alertsize(document.body.scrollHeight);' style='page-size:" + pageSize+ ";page-orientation:" + pageOrientation + "';  leftmargin='"+leftMargin + "' topmargin='" + topMargin + "' rightmargin='"
				+ rightMargin + "' bottommargin='" + bottomMargin + "'>");		
		createTableStrBuffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
		createTableStrBuffer.append("<tr>");
		createTableStrBuffer.append("<td>");
		/*createTableStrBuffer.append("<thead>");
		createTableStrBuffer.append("<tr><td>");*/
		
		if(rds.isReportHeaderRequired()){
		if(rds.isLogoRequired()&& rds.isCompanyNameRequired()){
			
			createTableStrBuffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='1'>");
			//createTableStrBuffer.append("<tr>");
			String logoTD="";
			logoTD="<td width='50%' colspan='2'> <img src='"+rds.getImageHttpPath()+rds.getLogoPath()+"'></td>";
			String companyTD="";
			companyTD="<td width='50%' rowspan='3' colspan="+(rds.getTotalColumns()-2)+" align='right'><span class='companyName'>"+replaceNewLineCharWithBr(checkNull(rds.getCompanyName())+"</span>");
			if(rds.isCompanyAddressRequired())
				companyTD+="<span class='tableBody'>"+replaceNewLineCharWithBr(checkNull(rds.getCompanyAddress()))+"</span>";
			companyTD+="</td>";
			
			if (rds.getLogoAlign() == 'L') {
				if (rds.getCompanyNameAlign() == 'R') {
					createTableStrBuffer.append("<tr>");
					createTableStrBuffer.append(logoTD);
					createTableStrBuffer.append(companyTD);
					createTableStrBuffer.append("</tr>");
				}else if (rds.getCompanyNameAlign() == 'C') {
					createTableStrBuffer.append("<tr>");
					createTableStrBuffer.append(logoTD);
					createTableStrBuffer.append(companyTD);
					createTableStrBuffer.append("<td></td>");
					createTableStrBuffer.append("</tr>");
				}
			} else if (rds.getLogoAlign() == 'R') {
				if (rds.getCompanyNameAlign() == 'L') {
					createTableStrBuffer.append("<tr>");
					createTableStrBuffer.append(companyTD);
					createTableStrBuffer.append(logoTD);
					createTableStrBuffer.append("</tr>");
				}else if (rds.getCompanyNameAlign() == 'C') {
					createTableStrBuffer.append("<tr>");
					createTableStrBuffer.append("<td></td>");
					createTableStrBuffer.append(companyTD);
					createTableStrBuffer.append(logoTD);
					createTableStrBuffer.append("</tr>");
				}
			} else if (rds.getLogoAlign() == 'C') {
				if (rds.getCompanyNameAlign() == 'L') {
					createTableStrBuffer.append("<tr>");
					createTableStrBuffer.append(companyTD);
					createTableStrBuffer.append("<td></td>");
					createTableStrBuffer.append(logoTD);
					createTableStrBuffer.append("</tr>");
				}else if (rds.getCompanyNameAlign() == 'R') {
					createTableStrBuffer.append("<tr>");
					createTableStrBuffer.append("<td></td>");
					createTableStrBuffer.append(logoTD);
					createTableStrBuffer.append(companyTD);
					createTableStrBuffer.append("</tr>");
				}
				else if (rds.getCompanyNameAlign() == 'C') {
					createTableStrBuffer.append("<tr>");
					createTableStrBuffer.append("<td align='center' width='50%'> <img src='"+rds.getImageHttpPath()+rds.getLogoPath()+"'></td>");
					createTableStrBuffer.append("</tr>");
					createTableStrBuffer.append("<tr>");
					createTableStrBuffer.append("<td align='center' width='50%' ><span class='companyName'>"+rds.getCompanyName()+"</span>");
					if(rds.isCompanyAddressRequired())
					createTableStrBuffer.append("<span class='tableBody'>"+rds.getCompanyAddress()+"</span>");
					
					createTableStrBuffer.append("</td>");
					createTableStrBuffer.append("</tr>");
				}
			}
			
			createTableStrBuffer.append("</table>");
		}else if(rds.isLogoRequired()){
			createTableStrBuffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
			createTableStrBuffer.append("<tr>");
			createTableStrBuffer.append("<td align='"+getAlignmentString(rds.getLogoAlign())+"' width='100%'>");
			createTableStrBuffer.append("<img src='"+rds.getImageHttpPath()+rds.getLogoPath()+"'>");
			createTableStrBuffer.append("</td>");
			createTableStrBuffer.append("</tr>");
			createTableStrBuffer.append("</table>");
		}else if (rds.isCompanyNameRequired()){
			createTableStrBuffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
			createTableStrBuffer.append("<tr>");
			createTableStrBuffer.append("<td align='"+getAlignmentString(rds.getCompanyNameAlign())+"' width='100%'>");
			createTableStrBuffer.append("<td><span class='companyName'>"+rds.getCompanyName()+"</span>");
			if(rds.isCompanyAddressRequired())
				createTableStrBuffer.append("<span class='tableBody'>"+rds.getCompanyAddress()+"</span>");
			createTableStrBuffer.append("</td>");
			createTableStrBuffer.append("</tr>");
			createTableStrBuffer.append("</table>");
		}else{
			createTableStrBuffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
			createTableStrBuffer.append("<tr>");
			createTableStrBuffer.append("<td width='100%'>");
			createTableStrBuffer.append("<img src='"+rds.getImageHttpPath()+rds.getHeaderImagePath()+"'>");
			createTableStrBuffer.append("</td>");
			createTableStrBuffer.append("</tr>");
			createTableStrBuffer.append("</table>");
		}
		
		if(!rds.getReportName().equals("")){
			createTableStrBuffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
			createTableStrBuffer.append("<tr>");
			createTableStrBuffer.append("<td colspan='"+rds.getTotalColumns()+"' class='reportHeader' height='15' width='100%'><u>"+rds.getReportName()+"</u>");
			createTableStrBuffer.append("</td>");
			createTableStrBuffer.append("</tr>");
			createTableStrBuffer.append("</table>");
			createTableStrBuffer.append("</td>");
			createTableStrBuffer.append("</tr>");
		}
	}
		
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


	/**
	 * THIS METHOD IS USED FOR CREATING HTML TABLE AND RETURN STRING BUFFER OBJECT
	 * @param tds
	 * TableDataSet as parameter
	 * @return StringBuffer Object
	 */

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
				createHtmlTableStrBuffer.append("<table border='"+headerBorder+"'"
				+ " cellspacing='0' style='border-collapse:collapse;border-color: #cccccc' cellpadding='0' width='100%'>");
		if (header != null && header.length > 0) {
			createHtmlTableStrBuffer.append("<tr>");
			//createHtmlTableStrBuffer.append("<tr>");
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
						+ " align='"+cellAlign[i]+"' bgcolor='#B4B4B4'  width='"+ width[i]+ "%' style='border: 0.5pt solid #000;'>" 
						//+ "'><font size='"+headerFontSize+"' face='"+rds.getTableHeaderFontFace()+"' color='#" + header_Font + "'><b>" + header[i]
						+ "" + header[i]
						+ "</b></td>");

			}
//			createHtmlTableStrBuffer.append("</tr></table></td></tr>");
			createHtmlTableStrBuffer.append("</tr>");
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
	 		
	 		if(tds.isBorderLines()){
				createHtmlTableStrBuffer.append(addLine("#5A5A5A","border-bottom"));
				}
	 		//createHtmlTableStrBuffer.append("<tr><td><table cellspacing='0' style='border-collapse:collapse;border-color: #cccccc' border='"+bodyBorder+"'  cellpadding='0' width='100%'>");
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
						createHtmlTableStrBuffer.append("<td align='"
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
								+ checkNull(String.valueOf(rowSumOut))
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
								createHtmlTableStrBuffer.append("<td   "+colspan+" width='"
										+ width[j]
										+ "%' align='"
										+ cellAlign[j]
										//+ "' > <font size='"+bodyFontSize+"' face='"+bodyFontFace+"'  color='#"
										+ "' "
										+" style='"+format+" padding-left:5px;padding-right:5px;font-family: "+rds.getTableBodyFontFace()+";font-size: "+rds.getTableBodyFontSize()+"pt;	font-weight: "+getStyleString(bodyFontStyle)+";  "+columnBackColor+"' >"
										//+ body_fontColor
										//+ "'>"
										+ replaceNewLineCharWithBr(checkNull(String.valueOf(data[i][j])))
										+ "</td>");
							 
								
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
											.twoDecimals(sumOut[k])))
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
											.twoDecimals(avgOut[k]
													/ colsAvg.length)))
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
//		createHtmlTableStrBuffer.append("</table></td></tr></table>");
		createHtmlTableStrBuffer.append("</table>");
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
						joinTableStrBuffer.append("<td valign='top'>");
					}
					joinTableStrBuffer.append(createHtmlTable((TableDataSet)objTableDataSet1));

					if(isHorizontal) {
						joinTableStrBuffer.append("</td>");
					}
				} else {
					
					joinTableStrBuffer.append(joinTable((HashMap<String, Object>)objTableDataSet1));
					/*if(isHorizontal) {
						joinTableStrBuffer.append("</td>");
					}*/
				}

				if(objTableDataSet2 instanceof TableDataSet) {
					if(isHorizontal) {
						joinTableStrBuffer.append("<td valign='top'>");
					}
					
					joinTableStrBuffer.append(createHtmlTable((TableDataSet)objTableDataSet2));
					
					if(isHorizontal) {
						joinTableStrBuffer.append("</td>");
					}
				} else {
					/*if(isHorizontal) {
						joinTableStrBuffer.append("<td valign='top' width='" + width[0] + "%'>");
					}*/
					joinTableStrBuffer.append(joinTable((HashMap<String, Object>)objTableDataSet2));
					/*if(isHorizontal) {
						joinTableStrBuffer.append("</td>");
					}*/
				}
				
				if(isHorizontal) {
					if(horzCount > 0) 
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
						createTableStrBuffer.append(createHtmlTable(tds));
					} else if (obj instanceof HashMap) {
						System.out.println("calling join table");
						createTableStrBuffer
								.append(joinTable((HashMap<String, Object>) obj));
					} /*else if (obj.equals("PAGE_BREAK")) {
						createTableStrBuffer.append("<p class='break'></P>");
					}*/else if (obj instanceof Vector) {
						System.out.println("calling join table");
						createTableStrBuffer
								.append(addLine((Vector) obj));
					}
					
				}
			}
			if(!rds.getGeneratedByText().equals("")){
				
				createTableStrBuffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
				createTableStrBuffer.append("<tr><tdcolspan="+rds.getTotalColumns()+" width='100%'>&nbsp;</td></tr><tr>");
				createTableStrBuffer.append("<td class='tableBody' colspan="+rds.getTotalColumns()+" width='100%' style='border-top: 0.5pt solid #000000;border-left: 0;border-right: 0;border-bottom: 0;'>");
				createTableStrBuffer.append(rds.getGeneratedByText());
				createTableStrBuffer.append("</td>");
				createTableStrBuffer.append("</tr>");
				createTableStrBuffer.append("</table>");
			}
			if(rds.isReportHeaderRequired()){
				if(!rds.getFooterImagePath().equals("")){
					
					createTableStrBuffer.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
					createTableStrBuffer.append("<tr>");
					createTableStrBuffer.append("<td width='100%'>");
					createTableStrBuffer.append("<img src='"+rds.getImageHttpPath()+rds.getFooterImagePath()+"'>");
					createTableStrBuffer.append("</td>");
					createTableStrBuffer.append("</tr>");
					createTableStrBuffer.append("</table>");
				}
			}
			
			createTableStrBuffer.append("</td>");
			createTableStrBuffer.append("</tr>");
			createTableStrBuffer.append("</table>");
			createTableStrBuffer.append("</body>");
			createTableStrBuffer.append("</html>");
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

			//createTableStrBuffer.append(str);
			logger.info("createTableStrBuffer------------ >>\n"+createTableStrBuffer);
		} catch (Exception e) {
			logger.error("Exception in process:");
			e.printStackTrace();
		}
	}

	private StringBuffer addLine(String bgcolor, String border) {

		StringBuffer lineStrBuffer = new StringBuffer();

		/*
		 * String color=""; for (int j = 0; j < obj.size(); j++) { Object
		 * tempObj = obj.get(j); if(tempObj instanceof BaseColor &&
		 * tempObj!=null){ color=RGBtoHex((BaseColor)tempObj); } }
		 */
		lineStrBuffer.append("<tr><td colspan='" + rds.getTotalColumns()
				+ "' style='width: 100%; height:1pt;" + border
				+ ": 0.5pt solid " + bgcolor + "'>&nbsp;</td></tr>");
		return lineStrBuffer;
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
	private String RGBToHex(BaseColor color) {
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
		return "#"+hexColor;
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
