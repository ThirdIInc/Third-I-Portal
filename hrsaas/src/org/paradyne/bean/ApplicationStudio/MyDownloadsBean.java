package org.paradyne.bean.ApplicationStudio;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.paradyne.lib.BeanBase;

public class MyDownloadsBean extends BeanBase {

	private LinkedHashMap<String, String> tmap =null ;
	private LinkedHashMap<String, String> subcategorymap =null ;	
	
	private String categoryNameDropdown="";
	private String  subCategoryNameDropDown="";
	
	
	private String categoryName="";
	private String subCategoryName="";
	private String linkName="";
	private String chk="";
	private String  hiddenCode="";
	private String  checkFlag="";
	private String  dataPath="";
	private String  subCatName="";
	private String uploadDocument="";
	private String link="";
	private String downloadDivCode="";
	private String downloadDivName="";
	private String checkActive="";
	private ArrayList<Object> listLink;
	
	private String linkCode="";
	private String linkFile="";
	private String linkActive="";
	
	private String catName="";
	private String dLinkFile="";
	private String dLinkName="";
	private String dLinkActive="";
	private String divCodeItt="";
	private String divNameItt="";
	
	
	public String getLinkActive() {
		return linkActive;
	}
	public void setLinkActive(String linkActive) {
		this.linkActive = linkActive;
	}
	public String getLinkFile() {
		return linkFile;
	}
	public void setLinkFile(String linkFile) {
		this.linkFile = linkFile;
	}
	public String getLinkCode() {
		return linkCode;
	}
	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}
	
	public String getCheckActive() {
		return checkActive;
	}
	public void setCheckActive(String checkActive) {
		this.checkActive = checkActive;
	}
	public String getDownloadDivCode() {
		return downloadDivCode;
	}
	public void setDownloadDivCode(String downloadDivCode) {
		this.downloadDivCode = downloadDivCode;
	}
	public String getDownloadDivName() {
		return downloadDivName;
	}
	public void setDownloadDivName(String downloadDivName) {
		this.downloadDivName = downloadDivName;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}	 
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getDLinkFile() {
		return dLinkFile;
	}
	public void setDLinkFile(String linkFile) {
		dLinkFile = linkFile;
	}
	public String getDLinkName() {
		return dLinkName;
	}
	public void setDLinkName(String linkName) {
		dLinkName = linkName;
	}
	public String getDLinkActive() {
		return dLinkActive;
	}
	public void setDLinkActive(String linkActive) {
		dLinkActive = linkActive;
	}
	public ArrayList<Object> getListLink() {
		return listLink;
	}
	public void setListLink(ArrayList<Object> listLink) {
		this.listLink = listLink;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getUploadDocument() {
		return uploadDocument;
	}
	public void setUploadDocument(String uploadDocument) {
		this.uploadDocument = uploadDocument;
	}
	public String getSubCatName() {
		return subCatName;
	}
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}
	public LinkedHashMap<String, String> getTmap() {
		return tmap;
	}
	public void setTmap(LinkedHashMap<String, String> tmap) {
		this.tmap = tmap;
	}
	public LinkedHashMap<String, String> getSubcategorymap() {
		return subcategorymap;
	}
	public void setSubcategorymap(LinkedHashMap<String, String> subcategorymap) {
		this.subcategorymap = subcategorymap;
	}
	public String getCategoryNameDropdown() {
		return categoryNameDropdown;
	}
	public void setCategoryNameDropdown(String categoryNameDropdown) {
		this.categoryNameDropdown = categoryNameDropdown;
	}
	public String getSubCategoryNameDropDown() {
		return subCategoryNameDropDown;
	}
	public void setSubCategoryNameDropDown(String subCategoryNameDropDown) {
		this.subCategoryNameDropDown = subCategoryNameDropDown;
	}
	public String getDivCodeItt() {
		return divCodeItt;
	}
	public void setDivCodeItt(String divCodeItt) {
		this.divCodeItt = divCodeItt;
	}
	public String getDivNameItt() {
		return divNameItt;
	}
	public void setDivNameItt(String divNameItt) {
		this.divNameItt = divNameItt;
	}
	
}
