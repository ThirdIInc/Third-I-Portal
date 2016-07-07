package org.paradyne.bean.CR;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;


/**
 * @author vivek.wadhwani
 * @Date Jan 18, 2013 : 6:49:04 PM
 */
public class DataReconcile  extends BeanBase{

	
	private String dataSource;
	private String sourceId;
	private String sourceName;
	private String fromDate = "";
	private String toDate = "";
	private String report = "";
	  
	private String modeLength="false";
	private String totalRecords="";
	private String myPage;
	private String myPageInProcess = "";
	private String show;
	private String  hiddencode;
	private String hdeleteCode;
	ArrayList<DataReconcile> columnList = null;
	HashMap filterMap = null;
	ArrayList filterList = null;
	ArrayList filterSetList = null;
	ArrayList dataReconcile = null;
	ArrayList checkBoxList = null;
	ArrayList filterPageList = null;
	private String columnId;
	private String columnName;
	
	private String tableFlag = "";
	private String tableFlagHidden = "";
	private String otherLengthVar = "";

	private boolean tableFlagCheckedCount= false;
	private boolean filters = false;
	private boolean filtersDefault = false;
	private String filterTables="";
	private String conditions="";
	private String inputCondition="";
	private boolean dataReconcileFlag = false;
	private String queryParameter ="";
	private String noOfColumns = "";
	private String primaryFlag="";
	private String primaryQuery="";
	private String filterQuery="";
	private String paginationQuery="";
	private int primaryFlagColumnNo;
	private String checkBox;

	

	public int getPrimaryFlagColumnNo() {
		return primaryFlagColumnNo;
	}

	public void setPrimaryFlagColumnNo(int primaryFlagColumnNo) {
		this.primaryFlagColumnNo = primaryFlagColumnNo;
	}

	public String getPaginationQuery() {
		return paginationQuery;
	}

	public void setPaginationQuery(String paginationQuery) {
		this.paginationQuery = paginationQuery;
	}

	public String getFilterQuery() {
		return filterQuery;
	}

	public void setFilterQuery(String filterQuery) {
		this.filterQuery = filterQuery;
	}

	
	

	/**
	 * @return
	 */
	public String getPrimaryQuery() {
		return primaryQuery;
	}

	/**
	 * @param primaryQuery
	 */
	public void setPrimaryQuery(String primaryQuery) {
		this.primaryQuery = primaryQuery;
	}

	/**
	 * @return
	 */
	public String getPrimaryFlag() {
		return primaryFlag;
	}

	/**
	 * @param primaryFlag
	 */
	public void setPrimaryFlag(String primaryFlag) {
		this.primaryFlag = primaryFlag;
	}

	/**
	 * @return
	 */
	public String getNoOfColumns() {
		return noOfColumns;
	}

	/**
	 * @param noOfColumns
	 */
	public void setNoOfColumns(String noOfColumns) {
		this.noOfColumns = noOfColumns;
	}

	/**
	 * @return
	 */
	public String getQueryParameter() {
		return queryParameter;
	}

	/**
	 * @param queryParameter
	 */
	public void setQueryParameter(String queryParameter) {
		this.queryParameter = queryParameter;
	}

	/** Get dataSource
	 * @return
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
	/** Source Id which will be unique
	 * @return
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/** Source Name
	 * @return
	 */
	public String getSourceName() {
		return sourceName;
	}

	/**
	 * @param sourceName
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	/**
	 * @return
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return
	 */
	public String getReport() {
		return report;
	}

	/**
	 * @param report
	 */
	public void setReport(String report) {
		this.report = report;
	}

	/**
	 * @return
	 */
	public String getModeLength() {
		return modeLength;
	}

	/**
	 * @param modeLength
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	/**
	 * @return
	 */
	public String getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return
	 */
	public String getMyPage() {
		return myPage;
	}

	/**
	 * @param myPage
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return
	 */
	public String getShow() {
		return show;
	}

	/**
	 * @param show
	 */
	public void setShow(String show) {
		this.show = show;
	}

	/**
	 * @return
	 */
	public String getHiddencode() {
		return hiddencode;
	}

	/**
	 * @param hiddencode
	 */
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	/**
	 * @return
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}

	/**
	 * @param hdeleteCode
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	
	/**
	 * @return
	 */
	public String getColumnId() {
		return columnId;
	}

	/**
	 * @param columnId
	 */
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	/**
	 * @return
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return
	 */
	public String getMyPageInProcess() {
		return myPageInProcess;
	}

	/**
	 * @param myPageInProcess
	 */
	public void setMyPageInProcess(String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}

	/**
	 * @return
	 */
	public String getTableFlag() {
		return tableFlag;
	}

	/**
	 * @param tableFlag
	 */
	public void setTableFlag(String tableFlag) {
		this.tableFlag = tableFlag;
	}

	/**
	 * @return
	 */
	public String getTableFlagHidden() {
		return tableFlagHidden;
	}

	/**
	 * @param tableFlagHidden
	 */
	public void setTableFlagHidden(String tableFlagHidden) {
		this.tableFlagHidden = tableFlagHidden;
	}
	
	/**
	 * @return
	 */
	public String getOtherLengthVar() {
		return otherLengthVar;
	}

	/**
	 * @param otherLengthVar
	 */
	public void setOtherLengthVar(String otherLengthVar) {
		this.otherLengthVar = otherLengthVar;

	}

	/**
	 * @return
	 */
	public boolean isTableFlagCheckedCount() {
		return tableFlagCheckedCount;
	}

	/**
	 * @param tableFlagCheckedCount
	 */
	public void setTableFlagCheckedCount(boolean tableFlagCheckedCount) {
		this.tableFlagCheckedCount = tableFlagCheckedCount;
	}
	

	/**
	 * @return
	 */
	public boolean isFilters() {
		return filters;
	}

	/**
	 * @param filters
	 */
	public void setFilters(boolean filters) {
		this.filters = filters;
	}
	
	/**
	 * @return
	 */
	public String getFilterTables() {
		return filterTables;
	}

	/**
	 * @param filterTables
	 */
	public void setFilterTables(String filterTables) {
		this.filterTables = filterTables;
	}

	/**
	 * @return
	 */
	public HashMap getFilterMap() {
		return filterMap;
	}

	/**
	 * @param filterMap
	 */
	public void setFilterMap(HashMap filterMap) {
		this.filterMap = filterMap;
	}

	/**
	 * @return
	 */
	public String getConditions() {
		return conditions;
	}

	/**
	 * @param conditions
	 */
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	/**
	 * @return
	 */
	public String getInputCondition() {
		return inputCondition;
	}

	/**
	 * @param inputCondition
	 */
	public void setInputCondition(String inputCondition) {
		this.inputCondition = inputCondition;
	}
	
	/**
	 * @return
	 */
	public boolean isDataReconcileFlag() {
		return dataReconcileFlag;
	}

	/**
	 * @param dataReconcileFlag
	 */
	public void setDataReconcileFlag(boolean dataReconcileFlag) {
		this.dataReconcileFlag = dataReconcileFlag;
	}

	/**
	 * @return
	 */
	public ArrayList getFilterList() {
		return filterList;
	}

	/**
	 * @param filterList
	 */
	public void setFilterList(ArrayList filterList) {
		this.filterList = filterList;
	}

	/**
	 * @return
	 */
	public ArrayList getDataReconcile() {
		return dataReconcile;
	}

	/**
	 * @param dataReconcile
	 */
	public void setDataReconcile(ArrayList dataReconcile) {
		this.dataReconcile = dataReconcile;
	}

	/**
	 * @return
	 */
	public ArrayList<DataReconcile> getColumnList() {
		return columnList;
	}

	/**
	 * @param columnList
	 */
	public void setColumnList(ArrayList<DataReconcile> columnList) {
		this.columnList = columnList;
	}

	public String getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}

	public ArrayList getCheckBoxList() {
		return checkBoxList;
	}

	public void setCheckBoxList(ArrayList checkBoxList) {
		this.checkBoxList = checkBoxList;
	}

	public ArrayList getFilterPageList() {
		return filterPageList;
	}

	public void setFilterPageList(ArrayList filterPageList) {
		this.filterPageList = filterPageList;
	}

	public boolean isFiltersDefault() {
		return filtersDefault;
	}

	public void setFiltersDefault(boolean filtersDefault) {
		this.filtersDefault = filtersDefault;
	}

	public ArrayList getFilterSetList() {
		return filterSetList;
	}

	public void setFilterSetList(ArrayList filterSetList) {
		this.filterSetList = filterSetList;
	}


	

	

	
}
