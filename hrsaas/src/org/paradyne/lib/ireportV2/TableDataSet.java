/* Prakash Shetkar Nov 20 2009 */

package org.paradyne.lib.ireportV2;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;

public class TableDataSet {
	public static final int LEFT = 0;
	public static final int CENTER = 1;
	public static final int RIGHT = 2;

	private int cellAlignment[] = null;
	private int cellWidth[] = null;
	private int cellColSpan[] = null;
	private int headerColSpan[] = null;
	private boolean cellNoWrap[] = null;
	private boolean headerNoWrap[] = null;

	private int bodyFont = -1;// = Font.HELVETICA;
	private FontFamily bodyFontFamily = null;
	private FontFamily headerFontFamily = null;
	private BaseFont baseFont=null ;
	private float bodyFontSize = -1;// 4;
	private int bodyFontStyle = -1;// Font.NORMAL;
	private int[] bodyFontStyleArray = null;// Font.NORMAL;
	private BaseColor bodyFontColor = null;
	private BaseColor baseColor = null;

	private int headerFont = -1;// = Font.HELVETICA;
	private float headerFontSize = -1;// 4;
	private int headerFontStyle = -1;// Font.NORMAL;
	private BaseColor headerFontColor = null;
	private BaseColor headerBGColor = null;
	private BaseColor headerBorderColor = null;
	
	private int borderDetail=0;
	private int headerBorderDetail=0;

	private int sumFont = -1;// = Font.HELVETICA;
	private float sumFontSize = -1;// 4;
	private int sumFontStyle = -1;// Font.NORMAL;
	private BaseColor sumFontColor = null;

	private BaseColor bodyBGColor = null;
	private Boolean border = false;
	private Object data[][] = null;
	private String header[] = null;
	private String headerData[][] = null;
	
	//ADDED BY REEBA BEGINS
	private Object headerObj[] = null;
	public boolean isHeaderTable = false;
	//ADDED BY REEBA ENDS

	private int columnNo = -1;
	private Font columnFont = null;
	private BaseColor columnBackColor = null;

	private int columnSum[] = null;
	private int rowSum[] = null;

	private int columnAvg[] = null;
	private int rowAvg[] = null;

	private int blankRowsAbove;
	private int blankRowsBelow;
	
	private int lineAbove;
	private int lineBelow;
	private boolean headerLines;
	private boolean borderLines;
	private boolean isTotalCol;

	private float headerCellpadding;
	private float bodyCellpadding;
	
	private int tablewidth = 100;
	private String tableAlignment = "center";

	public int getTablewidth() {
		return tablewidth;
	}

	public void setTablewidth(int tablewidth) {
		this.tablewidth = tablewidth;
	}

	public String getTableAlignment() {
		return tableAlignment;
	}

	public void setTableAlignment(String tableAlignment) {
		this.tableAlignment = tableAlignment;
	}

	public int getBlankRowsAbove() {
		return blankRowsAbove;
	}

	public void setBlankRowsAbove(int blankRowsAbove) {
		this.blankRowsAbove = blankRowsAbove;
	}

	public int getBlankRowsBelow() {
		return blankRowsBelow;
	}

	public void setBlankRowsBelow(int blankRowsBelow) {
		this.blankRowsBelow = blankRowsBelow;
	}

	public void setColumnSum(int[] colSum) {
		this.columnSum = colSum;
	}

	public int[] getColumnSum() {
		return columnSum;
	}

	public void setRowSum(int[] roSum) {
		this.rowSum = roSum;
	}

	public int[] getRowSum() {
		return rowSum;
	}

	public void setColumnColor(int no, BaseColor backColor, Font font) {
		this.columnNo = no;
		this.columnBackColor = backColor;
		this.columnFont = font;
	}

	public Object[] getColumnColor() {
		return new Object[]{columnNo, columnBackColor, columnFont};
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public void setBodyFontDetails(FontFamily bodyFontFamily, float size1, int style1, BaseColor clr1) {
		this.bodyFontFamily = bodyFontFamily;
		this.bodyFontSize = size1;
		this.bodyFontStyle = style1;
		this.baseColor = clr1;
	}

	public void setHeaderFontDetails(FontFamily headerFontFamily, float size1, int style1, BaseColor clr1) {
		this.headerFontFamily = headerFontFamily;
		this.headerFontSize = size1;
		this.headerFontStyle = style1;
		this.headerFontColor = clr1;
	}
	
	public void setHeaderFontDetails(int font1, float size1, int style1, BaseColor clr1) {
		this.headerFont = font1;
		this.headerFontSize = size1;
		this.headerFontStyle = style1;
		this.headerFontColor = clr1;
	}

	public void setSumFontDetails(int font1, float size1, int style1, BaseColor clr1) {
		this.sumFont = font1;
		this.sumFontSize = size1;
		this.sumFontStyle = style1;
		this.sumFontColor = clr1;
	}

	public Font getBodyFontDetails() {
		//return new Font(bodyFont, bodyFontSize, bodyFontStyle, bodyFontColor);
		return new Font(bodyFontFamily,bodyFontSize,bodyFontStyle,baseColor);
	}

	public Font getHeaderFontDetails() {
		return new Font(headerFontFamily,headerFontSize,bodyFontStyle,baseColor);
	}

	public Font getSumFontDetails() {
		return new Font(bodyFontFamily,bodyFontSize,bodyFontStyle,baseColor);
	}

	public int[] getCellAlignment() {
		return cellAlignment;
	}

	public void setCellAlignment(int[] cellAlignment) {
		this.cellAlignment = cellAlignment;
	}

	public int[] getCellWidth() {
		return cellWidth;
	}

	public void setCellWidth(int[] cellWidth) {
		this.cellWidth = cellWidth;
	}
	
	public int[] getCellColSpan() {
		return this.cellColSpan;
	}

	public void setCellColSpan(int[] cellColSpan) {
		this.cellColSpan = cellColSpan;
	}

	public boolean getBorder() {
		return border;
	}

	public void setBorder(Boolean bord) {
		this.border = bord;
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

	public float getHeaderCellpadding() {
		return headerCellpadding;
	}

	public void setHeaderCellpadding(float headerCellpadding) {
		this.headerCellpadding = headerCellpadding;
	}

	public float getBodyCellpadding() {
		return bodyCellpadding;
	}

	public void setBodyCellpadding(float bodyCellpadding) {
		this.bodyCellpadding = bodyCellpadding;
	}

	public BaseColor getHeaderBGColor() {
		return headerBGColor;
	}

	public void setHeaderBGColor(BaseColor headerBGColor) {
		this.headerBGColor = headerBGColor;
	}

	public BaseColor getBodyBGColor() {
		return bodyBGColor;
	}

	public void setBodyBGColor(BaseColor bodyBGColor) {
		this.bodyBGColor = bodyBGColor;
	}

	public int getBodyFont() {
		return bodyFont;
	}

	public void setBodyFont(int bodyFont) {
		this.bodyFont = bodyFont;
	}

	public float getBodyFontSize() {
		return bodyFontSize;
	}

	public void setBodyFontSize(float bodyFontSize) {
		this.bodyFontSize = bodyFontSize;
	}

	public int getBodyFontStyle() {
		return bodyFontStyle;
	}

	public void setBodyFontStyle(int bodyFontStyle) {
		this.bodyFontStyle = bodyFontStyle;
	}

	public BaseColor getBodyFontColor() {
		return bodyFontColor;
	}

	public void setBodyFontColor(BaseColor bodyFontColor) {
		this.bodyFontColor = bodyFontColor;
	}

	

	public float getHeaderFontSize() {
		return headerFontSize;
	}

	public void setHeaderFontSize(float headerFontSize) {
		this.headerFontSize = headerFontSize;
	}

	public int getHeaderFontStyle() {
		return headerFontStyle;
	}

	public void setHeaderFontStyle(int headerFontStyle) {
		this.headerFontStyle = headerFontStyle;
	}

	public BaseColor getHeaderFontColor() {
		return headerFontColor;
	}

	public void setHeaderFontColor(BaseColor headerFontColor) {
		this.headerFontColor = headerFontColor;
	}

	public int[] getColumnAvg() {
		return columnAvg;
	}

	public void setColumnAvg(int[] columnAvg) {
		this.columnAvg = columnAvg;
	}

	public int[] getRowAvg() {
		return rowAvg;
	}

	public void setRowAvg(int[] rowAvg) {
		this.rowAvg = rowAvg;
	}

	//UPDATED BY REEBA BEGINS
	public Object[] getHeaderObj() {
		return headerObj;
	}

	public void setHeaderObj(Object[] headerObj) {
		this.headerObj = headerObj;
		String[] header = new String[headerObj.length];
		for (int i = 0; i < header.length; i++) {
			header[i] = String.valueOf(headerObj[i]);
		}
		setHeader(header);
	}
	
	public boolean isHeaderTable() {
		return isHeaderTable;
	}

	public void setHeaderTable(boolean isHeaderTable) {
		this.isHeaderTable = isHeaderTable;
	}
	//UPDATED BY REEBA ENDS

	public int getBorderDetail() {
		return borderDetail;
	}

	public void setBorderDetail(int borderDetail) {
		this.borderDetail = borderDetail;
	}

	public int getHeaderBorderDetail() {
		return headerBorderDetail;
	}

	public void setHeaderBorderDetail(int headerBorderDetail) {
		this.headerBorderDetail = headerBorderDetail;
	}

	public int getLineAbove() {
		return lineAbove;
	}

	public void setLineAbove(int lineAbove) {
		this.lineAbove = lineAbove;
	}

	public int getLineBelow() {
		return lineBelow;
	}

	public void setLineBelow(int lineBelow) {
		this.lineBelow = lineBelow;
	}

	public boolean isHeaderLines() {
		return headerLines;
	}

	public void setHeaderLines(boolean headerLines) {
		this.headerLines = headerLines;
	}

	public BaseColor getHeaderBorderColor() {
		return headerBorderColor;
	}

	public void setHeaderBorderColor(BaseColor headerBorderColor) {
		this.headerBorderColor = headerBorderColor;
	}

	public FontFamily getHeaderFontFamily() {
		return headerFontFamily;
	}

	public void setHeaderFontFamily(FontFamily headerFontFamily) {
		this.headerFontFamily = headerFontFamily;
	}

	public int getHeaderFont() {
		return headerFont;
	}

	public void setHeaderFont(int headerFont) {
		this.headerFont = headerFont;
	}

	public boolean isTotalCol() {
		return isTotalCol;
	}

	public void setTotalCol(boolean isTotalCol) {
		this.isTotalCol = isTotalCol;
	}

	public boolean[] getCellNoWrap() {
		return cellNoWrap;
	}

	public void setCellNoWrap(boolean[] cellNoWrap) {
		this.cellNoWrap = cellNoWrap;
	}

	public float getSumFontSize() {
		return sumFontSize;
	}

	public void setSumFontSize(float sumFontSize) {
		this.sumFontSize = sumFontSize;
	}

	public int getSumFontStyle() {
		return sumFontStyle;
	}

	public void setSumFontStyle(int sumFontStyle) {
		this.sumFontStyle = sumFontStyle;
	}

	public boolean isBorderLines() {
		return borderLines;
	}

	public void setBorderLines(boolean borderLines) {
		this.borderLines = borderLines;
	}

	
	public boolean[] getHeaderNoWrap() {
		return headerNoWrap;
	}

	public void setHeaderNoWrap(boolean[] headerNoWrap) {
		this.headerNoWrap = headerNoWrap;
	}

	public int[] getBodyFontStyleArray() {
		return bodyFontStyleArray;
	}

	public void setBodyFontStyleArray(int[] bodyFontStyleArray) {
		this.bodyFontStyleArray = bodyFontStyleArray;
	}

	public int[] getHeaderColSpan() {
		return headerColSpan;
	}

	public void setHeaderColSpan(int[] headerColSpan) {
		this.headerColSpan = headerColSpan;
	}

	/**
	 * @return the headerData
	 */
	public String[][] getHeaderData() {
		return headerData;
	}

	/**
	 * @param headerData the headerData to set
	 */
	public void setHeaderData(String[][] headerData) {
		this.headerData = headerData;
	}

}