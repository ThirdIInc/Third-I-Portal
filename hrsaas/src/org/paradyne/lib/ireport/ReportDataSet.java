/* Prakash Shetkar Nov 20, 2009 */

package org.paradyne.lib.ireport;

public class ReportDataSet {
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int CENTER = 2;
	public static final int TOP = 0;
	public static final int BOTTOM = 1;

	private String reportType = "pdf";
	private String fileName = "";
	private String reportName = "";

	private String pageSize = "A4"; // A3, A4, A5
	private String pageOrientation = "portrait"; // portrait, landscape
	private float marginLeft = 50;
	private float marginRight = 50;
	private float marginTop = 50;
	private float marginBottom = 50;

	private boolean showPageNo = false;
	private int pageNoHPosition = ReportDataSet.CENTER;
	private int pageNoVPosition = ReportDataSet.BOTTOM;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getPageOrientation() {
		return pageOrientation;
	}

	public void setPageOrientation(String pageOrientation) {
		this.pageOrientation = pageOrientation;
	}

	public float getMarginLeft() {
		return marginLeft;
	}

	public void setMarginLeft(float marginLeft) {
		this.marginLeft = marginLeft;
	}

	public float getMarginRight() {
		return marginRight;
	}

	public void setMarginRight(float marginRight) {
		this.marginRight = marginRight;
	}

	public float getMarginTop() {
		return marginTop;
	}

	public void setMarginTop(float marginTop) {
		this.marginTop = marginTop;
	}

	public float getMarginBottom() {
		return marginBottom;
	}

	public void setMarginBottom(float marginBottom) {
		this.marginBottom = marginBottom;
	}

	public boolean isShowPageNo() {
		return showPageNo;
	}

	public void setShowPageNo(boolean showPageNo) {
		this.showPageNo = showPageNo;
	}

	public int getPageNoHPosition() {
		return pageNoHPosition;
	}

	public void setPageNoHPosition(int pageNoHPosition) {
		this.pageNoHPosition = pageNoHPosition;
	}

	public int getPageNoVPosition() {
		return pageNoVPosition;
	}

	public void setPageNoVPosition(int pageNoVPosition) {
		this.pageNoVPosition = pageNoVPosition;
	}
}