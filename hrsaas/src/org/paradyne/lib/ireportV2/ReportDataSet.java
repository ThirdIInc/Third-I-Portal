/* Prakash Shetkar Nov 20, 2009 */

package org.paradyne.lib.ireportV2;

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

	private boolean showPageNo = true;
	private int pageNoHPosition = ReportDataSet.CENTER;
	private int pageNoVPosition = ReportDataSet.BOTTOM;
	
	private int divisionId=-1;
	private String userEmpId="";
	private String generatedByText="";
	private int totalColumns=5;
	
	private boolean reportHeaderRequired=true;
	private boolean isLogoRequired;
	private String logoPath;
	private String headerImagePath="";
	private String footerImagePath="";
	private String footerText="";
	private String imageRealPath="";
	private String imageHttpPath="";
	private String companyName;
	private String companyAddress;
	private boolean isLetterHeadRequired;
	private char logoAlign;
	private boolean isCompanyNameRequired;
	private int companyNameFontStyle;
	private String companyNameFontFace;
	private int companyNameFontSize;
	private char companyNameAlign;
	private boolean isCompanyAddressRequired;
	private int companyAddressFontStyle;
	private String companyAddressFontFace;
	private int companyAddressFontSize;
	private int reportHeaderFontStyle;
	private String reportHeaderFontFace;
	private int reportHeaderFontSize;
	private int tableHeaderFontSize;
	private int tableHeaderFontStyle;
	private String tableHeaderFontFace;
	private int tableBodyFontSize;
	private int tableBodyFontStyle;
	private String tableBodyFontFace;
	

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

	public int getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(int divisionId) {
		this.divisionId = divisionId;
	}

	public boolean isLogoRequired() {
		return isLogoRequired;
	}

	public void setLogoRequired(boolean isLogoRequired) {
		this.isLogoRequired = isLogoRequired;
	}

	public char getLogoAlign() {
		return logoAlign;
	}

	public void setLogoAlign(char logoAlign) {
		this.logoAlign = logoAlign;
	}

	public boolean isCompanyNameRequired() {
		return isCompanyNameRequired;
	}

	public void setCompanyNameRequired(boolean isCompanyNameRequired) {
		this.isCompanyNameRequired = isCompanyNameRequired;
	}

	public int getCompanyNameFontStyle() {
		return companyNameFontStyle;
	}

	public void setCompanyNameFontStyle(int companyNameFontStyle) {
		this.companyNameFontStyle = companyNameFontStyle;
	}

	public String getCompanyNameFontFace() {
		return companyNameFontFace;
	}

	public void setCompanyNameFontFace(String companyNameFontFace) {
		this.companyNameFontFace = companyNameFontFace;
	}

	public int getCompanyNameFontSize() {
		return companyNameFontSize;
	}

	public void setCompanyNameFontSize(int companyNameFontSize) {
		this.companyNameFontSize = companyNameFontSize;
	}

	public char getCompanyNameAlign() {
		return companyNameAlign;
	}

	public void setCompanyNameAlign(char companyNameAlign) {
		this.companyNameAlign = companyNameAlign;
	}


	public boolean isCompanyAddressRequired() {
		return isCompanyAddressRequired;
	}

	public void setCompanyAddressRequired(boolean isCompanyAddressRequired) {
		this.isCompanyAddressRequired = isCompanyAddressRequired;
	}

	public int getCompanyAddressFontStyle() {
		return companyAddressFontStyle;
	}

	public void setCompanyAddressFontStyle(int companyAddressFontStyle) {
		this.companyAddressFontStyle = companyAddressFontStyle;
	}

	public String getCompanyAddressFontFace() {
		return companyAddressFontFace;
	}

	public void setCompanyAddressFontFace(String companyAddressFontFace) {
		this.companyAddressFontFace = companyAddressFontFace;
	}

	public int getCompanyAddressFontSize() {
		return companyAddressFontSize;
	}

	public void setCompanyAddressFontSize(int companyAddressFontSize) {
		this.companyAddressFontSize = companyAddressFontSize;
	}

	public int getReportHeaderFontStyle() {
		return reportHeaderFontStyle;
	}

	public void setReportHeaderFontStyle(int reportHeaderFontStyle) {
		this.reportHeaderFontStyle = reportHeaderFontStyle;
	}


	public String getReportHeaderFontFace() {
		return reportHeaderFontFace;
	}

	public void setReportHeaderFontFace(String reportHeaderFontFace) {
		this.reportHeaderFontFace = reportHeaderFontFace;
	}

	public int getReportHeaderFontSize() {
		return reportHeaderFontSize;
	}

	public void setReportHeaderFontSize(int reportHeaderFontSize) {
		this.reportHeaderFontSize = reportHeaderFontSize;
	}

	public int getTableHeaderFontSize() {
		return tableHeaderFontSize;
	}

	public void setTableHeaderFontSize(int tableHeaderFontSize) {
		this.tableHeaderFontSize = tableHeaderFontSize;
	}

	public int getTableHeaderFontStyle() {
		return tableHeaderFontStyle;
	}

	public void setTableHeaderFontStyle(int tableHeaderFontStyle) {
		this.tableHeaderFontStyle = tableHeaderFontStyle;
	}

	public String getTableHeaderFontFace() {
		return tableHeaderFontFace;
	}

	public void setTableHeaderFontFace(String tableHeaderFontFace) {
		this.tableHeaderFontFace = tableHeaderFontFace;
	}

	public int getTableBodyFontSize() {
		return tableBodyFontSize;
	}

	public void setTableBodyFontSize(int tableBodyFontSize) {
		this.tableBodyFontSize = tableBodyFontSize;
	}

	public int getTableBodyFontStyle() {
		return tableBodyFontStyle;
	}

	public void setTableBodyFontStyle(int tableBodyFontStyle) {
		this.tableBodyFontStyle = tableBodyFontStyle;
	}

	public String getTableBodyFontFace() {
		return tableBodyFontFace;
	}

	public void setTableBodyFontFace(String tableBodyFontFace) {
		this.tableBodyFontFace = tableBodyFontFace;
	}

	public boolean isLetterHeadRequired() {
		return isLetterHeadRequired;
	}

	public void setLetterHeadRequired(boolean isLetterHeadRequired) {
		this.isLetterHeadRequired = isLetterHeadRequired;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getHeaderImagePath() {
		return headerImagePath;
	}

	public void setHeaderImagePath(String headerImagePath) {
		this.headerImagePath = headerImagePath;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getFooterImagePath() {
		return footerImagePath;
	}

	public void setFooterImagePath(String footerImagePath) {
		this.footerImagePath = footerImagePath;
	}

	public String getImageRealPath() {
		return imageRealPath;
	}

	public void setImageRealPath(String imageRealPath) {
		this.imageRealPath = imageRealPath;
	}

	public String getImageHttpPath() {
		return imageHttpPath;
	}

	public void setImageHttpPath(String imageHttpPath) {
		this.imageHttpPath = imageHttpPath;
	}

	public String getFooterText() {
		return footerText;
	}

	public void setFooterText(String footerText) {
		this.footerText = footerText;
	}

	public int getTotalColumns() {
		return totalColumns;
	}

	public void setTotalColumns(int totalColumns) {
		this.totalColumns = totalColumns;
	}

	
	public boolean isReportHeaderRequired() {
		return reportHeaderRequired;
	}

	public void setReportHeaderRequired(boolean reportHeaderRequired) {
		this.reportHeaderRequired = reportHeaderRequired;
	}


	public String getUserEmpId() {
		return userEmpId;
	}

	public void setUserEmpId(String userEmpId) {
		this.userEmpId = userEmpId;
	}

	public String getGeneratedByText() {
		return generatedByText;
	}

	public void setGeneratedByText(String generatedByText) {
		this.generatedByText = generatedByText;
	}
}