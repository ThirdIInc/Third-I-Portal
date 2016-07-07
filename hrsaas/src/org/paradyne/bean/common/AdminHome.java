package org.paradyne.bean.common;
import java.util.*;


import org.paradyne.lib.BeanBase;
/*
 * author:Pradeep K
 * Date:29-01-2008
 */

public class AdminHome extends BeanBase {
	
	
	private ArrayList confList=null;
	private ArrayList vchList=null;
	private TreeMap assetList=null;
	String confDate="";
	String confTime="";
	String confToTime="";
	String confBy="";
	String confPlace="";
	String name="";
	String vchDate="";
	String amt="";
	String assetName="";
	private String assetCategory = "";
	private String assetType = "";
	TreeMap assetmap;
	TreeMap assetmap1;
	String confStatus="";
	
	public String getConfStatus() {
		return confStatus;
	}
	public void setConfStatus(String confStatus) {
		this.confStatus = confStatus;
	}
	public ArrayList getConfList() {
		return confList;
	}
	public void setConfList(ArrayList confList) {
		this.confList = confList;
	}
	public String getConfDate() {
		return confDate;
	}
	public void setConfDate(String confDate) {
		this.confDate = confDate;
	}
	public String getConfTime() {
		return confTime;
	}
	public void setConfTime(String confTime) {
		this.confTime = confTime;
	}
	public String getConfBy() {
		return confBy;
	}
	public void setConfBy(String confBy) {
		this.confBy = confBy;
	}
	public String getConfPlace() {
		return confPlace;
	}
	public void setConfPlace(String confPlace) {
		this.confPlace = confPlace;
	}
	public ArrayList getVchList() {
		return vchList;
	}
	public void setVchList(ArrayList vchList) {
		this.vchList = vchList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVchDate() {
		return vchDate;
	}
	public void setVchDate(String vchDate) {
		this.vchDate = vchDate;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public TreeMap getAssetList() {
		return assetList;
	}
	public void setAssetList(TreeMap assetList) {
		this.assetList = assetList;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetCategory() {
		return assetCategory;
	}
	public void setAssetCategory(String assetCategory) {
		this.assetCategory = assetCategory;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public TreeMap getAssetmap() {
		return assetmap;
	}
	public void setAssetmap(TreeMap assetmap) {
		this.assetmap = assetmap;
	}
	public TreeMap getAssetmap1() {
		return assetmap1;
	}
	public void setAssetmap1(TreeMap assetmap1) {
		this.assetmap1 = assetmap1;
	}
	public String getConfToTime() {
		return confToTime;
	}
	public void setConfToTime(String confToTime) {
		this.confToTime = confToTime;
	}
	
	
	
		
	
	

}
