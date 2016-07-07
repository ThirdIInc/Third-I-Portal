package org.paradyne.bean.exam;

import java.io.Serializable;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author Prasad and Pradeep
 */

public class ExamMaster extends BeanBase implements Serializable{
	private static final long serialVersionUID = 1L;
	private String subjectCode;
	private String subjectName;
	private String subjectAbbr;
	private String subjectDesc;
	private ArrayList<Object> subjectList=null;
	private String subjectStatus;
	private String totalPage="";
	private String pageNoField="";
	private String hdeleteCatId="";
	private String enableSection="";
	private String content = "";
	private String moduleNmLen= "";
	//varibales for Category Table
	
	private String categoryName;
	private String subjectCategoryCode;
	private String categoryCode;
	private ArrayList<Object> categoryList;
	private String subjectCategoryNameItr;
	private String categoryCodeItr;
	private String hEditCatCode = "";
	private String hdeleteCatCode;
	private String categoryStatus;
	private String categoryOrder = "";
	
	private String hdomainCode;
	private String confChk;
	private String hdeleteCode;
	
	private String confChkCat;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddencode;
	private String checkEdit;
	private String SrNo;
	private String paraId;
	private String chkAll="";
	private String viewStatus="";
	private String status="";
	private String categoryListlength;
	private String sectionNmLen ="";
	//private String flagView="false";
	private String subjectCat="";
	/**
	 * Flags Required  for Subject Master
	 */
	private boolean onLoadFlag=false;
	private boolean saveFlag=false;
	private boolean flag=false;
	private String editFlag="false";
	private boolean pageFlag=false;
	private boolean dblFlag=false;
	
	/**
	 * Flags For Cancel Button
	 */
	
	private boolean loadFlag;
	private boolean addFlag;
	private boolean modFlag;
	private boolean dbFlag;
	private String dataLength="";
	private boolean addLoadFlag=false;
	private boolean catList=false;
	private boolean saveModFlag=false;
	private boolean removeFlag=false;
	private boolean viewModeFlag=false;
	private boolean catData=false;
	private String updateCatFlag="false";
	private String ctgryCode="";	
	private String flagShow="false";
	
	private String htmlcontent="";
	private String catStatus="";
	
	private String contentType="";
	private String contentTitle="";
	private String contentId="";
	private String contentUrl="";
	private String contentOrder="";
	private ArrayList<Object> contentList=null;
	
	
	
	public void setCatStatus(String catStatus) {
		this.catStatus = catStatus;
	}
	
	public String getCtgryCode() {
		return ctgryCode;
	}
	public void setCtgryCode(String ctgryCode) {
		this.ctgryCode = ctgryCode;
	}
	/**
	 * 
	 *  Setter And Getter Methods for All Fields and Flags
	 */
	
	
	
	public String getHdomainCode() {
		return hdomainCode;
	}
	public void setHdomainCode(String hdomainCode) {
		this.hdomainCode = hdomainCode;
	}	
	public String getConfChk() {
		return confChk;
	}
	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getSelectname() {
		return selectname;
	}
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public boolean getOnLoadFlag() {
		return onLoadFlag;
	}
	public void setOnLoadFlag(boolean onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}
	public boolean getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(boolean saveFlag) {
		this.saveFlag = saveFlag;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	public boolean getPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}
	public boolean getDblFlag() {
		return dblFlag;
	}
	public void setDblFlag(boolean dblFlag) {
		this.dblFlag = dblFlag;
	}
	public boolean getLoadFlag() {
		return loadFlag;
	}
	public void setLoadFlag(boolean loadFlag) {
		this.loadFlag = loadFlag;
	}
	public boolean getAddFlag() {
		return addFlag;
	}
	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}
	public boolean getModFlag() {
		return modFlag;
	}
	public void setModFlag(boolean modFlag) {
		this.modFlag = modFlag;
	}
	public boolean getDbFlag() {
		return dbFlag;
	}
	public void setDbFlag(boolean dbFlag) {
		this.dbFlag = dbFlag;
	}
	
	public String getSubjectCategoryCode() {
		return subjectCategoryCode;
	}
	public void setSubjectCategoryCode(String subjectCategoryCode) {
		this.subjectCategoryCode = subjectCategoryCode;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getSrNo() {
		return SrNo;
	}
	public void setSrNo(String srNo) {
		this.SrNo = srNo;
	}
	public String getSubjectCategoryNameItr() {
		return subjectCategoryNameItr;
	}
	public void setSubjectCategoryNameItr(String subjectCategoryNameItr) {
		this.subjectCategoryNameItr = subjectCategoryNameItr;
	}
	public String getCategoryCodeItr() {
		return categoryCodeItr;
	}
	public void setCategoryCodeItr(String categoryCodeItr) {
		this.categoryCodeItr = categoryCodeItr;
	}
	public boolean isAddLoadFlag() {
		return addLoadFlag;
	}
	public void setAddLoadFlag(boolean addLoadFlag) {
		this.addLoadFlag = addLoadFlag;
	}
	public String getCheckEdit() {
		return checkEdit;
	}
	public void setCheckEdit(String checkEdit) {
		this.checkEdit = checkEdit;
	}
	public String getCategoryListlength() {
		return categoryListlength;
	}
	public void setCategoryListlength(String categoryListlength) {
		this.categoryListlength = categoryListlength;
	}
	public String getHdeleteCatCode() {
		return hdeleteCatCode;
	}
	public void setHdeleteCatCode(String hdeleteCatCode) {
		this.hdeleteCatCode = hdeleteCatCode;
	}
	public boolean isCatList() {
		return catList;
	}
	public void setCatList(boolean catList) {
		this.catList = catList;
	}
	public boolean isSaveModFlag() {
		return saveModFlag;
	}
	public void setSaveModFlag(boolean saveModFlag) {
		this.saveModFlag = saveModFlag;
	}
	public String getConfChkCat() {
		return confChkCat;
	}
	public void setConfChkCat(String confChkCat) {
		this.confChkCat = confChkCat;
	}
	public boolean isRemoveFlag() {
		return removeFlag;
	}
	public void setRemoveFlag(boolean removeFlag) {
		this.removeFlag = removeFlag;
	}
	public boolean isViewModeFlag() {
		return viewModeFlag;
	}
	public void setViewModeFlag(boolean viewModeFlag) {
		this.viewModeFlag = viewModeFlag;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public boolean isCatData() {
		return catData;
	}
	public void setCatData(boolean catData) {
		this.catData = catData;
	}
	public String getChkAll() {
		return chkAll;
	}
	public void setChkAll(String chkAll) {
		this.chkAll = chkAll;
	}
	public String getViewStatus() {
		return viewStatus;
	}
	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}
/*	
	public String getFlagView() {
		return flagView;
	}
	public void setFlagView(String flagView) {
		this.flagView = flagView;
	}
*/	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubjectCat() {
		return subjectCat;
	}
	public void setSubjectCat(String subjectCat) {
		this.subjectCat = subjectCat;
	}
	public String getUpdateCatFlag() {
		return updateCatFlag;
	}
	public void setUpdateCatFlag(String updateCatFlag) {
		this.updateCatFlag = updateCatFlag;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getPageNoField() {
		return pageNoField;
	}
	public void setPageNoField(String pageNoField) {
		this.pageNoField = pageNoField;
	}
	public String getHdeleteCatId() {
		return hdeleteCatId;
	}
	public void setHdeleteCatId(String hdeleteCatId) {
		this.hdeleteCatId = hdeleteCatId;
	}
	public String getFlagShow() {
		return flagShow;
	}
	public void setFlagShow(String flagShow) {
		this.flagShow = flagShow;
	}
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	public String getHtmlcontent() {
		return htmlcontent;
	}
	public void setHtmlcontent(String htmlcontent) {
		this.htmlcontent = htmlcontent;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectAbbr() {
		return subjectAbbr;
	}
	public void setSubjectAbbr(String subjectAbbr) {
		this.subjectAbbr = subjectAbbr;
	}
	public String getSubjectDesc() {
		return subjectDesc;
	}
	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}
	
	public String getSubjectStatus() {
		return subjectStatus;
	}
	
	public void setSubjectStatus(String subjectStatus) {
		this.subjectStatus = subjectStatus;
	}
	public String getCatStatus() {
		return catStatus;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getHEditCatCode() {
		return hEditCatCode;
	}
	public void setHEditCatCode(String editCatCode) {
		hEditCatCode = editCatCode;
	}
	public String getCategoryStatus() {
		return categoryStatus;
	}
	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	public String getEnableSection() {
		return enableSection;
	}
	public void setEnableSection(String enableSection) {
		this.enableSection = enableSection;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategoryOrder() {
		return categoryOrder;
	}
	public ArrayList<Object> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(ArrayList<Object> subjectList) {
		this.subjectList = subjectList;
	}

	public ArrayList<Object> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Object> categoryList) {
		this.categoryList = categoryList;
	}

	public void setCategoryOrder(String categoryOrder) {
		this.categoryOrder = categoryOrder;
	}

	public String getModuleNmLen() {
		return moduleNmLen;
	}

	public void setModuleNmLen(String moduleNmLen) {
		this.moduleNmLen = moduleNmLen;
	}

	public String getSectionNmLen() {
		return sectionNmLen;
	}

	public void setSectionNmLen(String sectionNmLen) {
		this.sectionNmLen = sectionNmLen;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	
	public String getContentOrder() {
		return contentOrder;
	}

	public void setContentOrder(String contentOrder) {
		this.contentOrder = contentOrder;
	}

	public ArrayList<Object> getContentList() {
		return contentList;
	}

	public void setContentList(ArrayList<Object> contentList) {
		this.contentList = contentList;
	}

	
}
