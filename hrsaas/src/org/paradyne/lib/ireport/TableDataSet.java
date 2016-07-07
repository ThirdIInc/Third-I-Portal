/* Prakash Shetkar Nov 20 2009 */

package org.paradyne.lib.ireport;

import java.awt.Color;
import com.lowagie.text.Font;

public class TableDataSet {
	public static final int LEFT = 0;
	public static final int CENTER = 1;
	public static final int RIGHT = 2;

	private int cellAlignment[] = null;
	private int cellWidth[] = null;
	private int cellColSpan[] = null;

	private int bodyFont = -1;// = Font.HELVETICA;
	private float bodyFontSize = -1;// 4;
	private int bodyFontStyle = -1;// Font.NORMAL;
	private Color bodyFontColor = null;

	private int headerFont = -1;// = Font.HELVETICA;
	private float headerFontSize = -1;// 4;
	private int headerFontStyle = -1;// Font.NORMAL;
	private Color headerFontColor = null;
	private Color headerBGColor = null;

	private int sumFont = -1;// = Font.HELVETICA;
	private float sumFontSize = -1;// 4;
	private int sumFontStyle = -1;// Font.NORMAL;
	private Color sumFontColor = null;

	private Color bodyBGColor = null;
	private Boolean border = false;
	private Object data[][] = null;
	private String header[] = null;
	
	//ADDED BY REEBA BEGINS
	private Object headerObj[] = null;
	public boolean isHeaderTable = true;
	//ADDED BY REEBA ENDS

	private int columnNo = -1;
	private Font columnFont = null;
	private Color columnBackColor = null;

	private int columnSum[] = null;
	private int rowSum[] = null;

	private int columnAvg[] = null;
	private int rowAvg[] = null;

	private int blankRowsAbove;
	private int blankRowsBelow;

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

	public void setColumnColor(int no, Color backColor, Font font) {
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

	public void setBodyFontDetails(int font1, float size1, int style1, Color clr1) {
		this.bodyFont = font1;
		this.bodyFontSize = size1;
		this.bodyFontStyle = style1;
		this.bodyFontColor = clr1;
	}

	public void setHeaderFontDetails(int font1, float size1, int style1, Color clr1) {
		this.headerFont = font1;
		this.headerFontSize = size1;
		this.headerFontStyle = style1;
		this.headerFontColor = clr1;
	}

	public void setSumFontDetails(int font1, float size1, int style1, Color clr1) {
		this.sumFont = font1;
		this.sumFontSize = size1;
		this.sumFontStyle = style1;
		this.sumFontColor = clr1;
	}

	public Font getBodyFontDetails() {
		return new Font(bodyFont, bodyFontSize, bodyFontStyle, bodyFontColor);
	}

	public Font getHeaderFontDetails() {
		return new Font(headerFont, headerFontSize, headerFontStyle, headerFontColor);
	}

	public Font getSumFontDetails() {
		return new Font(sumFont, sumFontSize, sumFontStyle, sumFontColor);
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

	public Color getHeaderBGColor() {
		return headerBGColor;
	}

	public void setHeaderBGColor(Color headerBGColor) {
		this.headerBGColor = headerBGColor;
	}

	public Color getBodyBGColor() {
		return bodyBGColor;
	}

	public void setBodyBGColor(Color bodyBGColor) {
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

	public Color getBodyFontColor() {
		return bodyFontColor;
	}

	public void setBodyFontColor(Color bodyFontColor) {
		this.bodyFontColor = bodyFontColor;
	}

	public int getHeaderFont() {
		return headerFont;
	}

	public void setHeaderFont(int headerFont) {
		this.headerFont = headerFont;
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

	public Color getHeaderFontColor() {
		return headerFontColor;
	}

	public void setHeaderFontColor(Color headerFontColor) {
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
}