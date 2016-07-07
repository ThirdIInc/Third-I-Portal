/**
 * @author lakkichand
 *  @date 25 Apr 2007
 */

package org.paradyne.bean.admin.master;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class EmpTypeMaster extends BeanBase{
	private String typeID;
	private String typeName;
	private String typeAbbr;
	private String authority;
	private String authDate;	
	private String esiZone;
	private String ptZone;
	private String pfZone;
	private ArrayList typeList;
	private String myPage;
	private String show;
	private String  hiddencode;
	private String hdeleteCode;
	private String isActive;
	private int totalRecords = 0;
	private boolean recordsAvailable = false;
	
	private String report="";
	private String HiddenChechfrmId="";
	
	public String getHiddenChechfrmId() {
		return HiddenChechfrmId;
	}
	public void setHiddenChechfrmId(String hiddenChechfrmId) {
		HiddenChechfrmId = hiddenChechfrmId;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getTypeID() {
		return typeID;
	}
	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeAbbr() {
		return typeAbbr;
	}
	public void setTypeAbbr(String typeAbbr) {
		this.typeAbbr = typeAbbr;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getAuthDate() {
		return authDate;
	}
	public void setAuthDate(String authDate) {
		this.authDate = authDate;
	}
	public String getEsiZone() {
		return esiZone;
	}
	public void setEsiZone(String esiZone) {
		this.esiZone = esiZone;
	}
	public String getPtZone() {
		return ptZone;
	}
	public void setPtZone(String ptZone) {
		this.ptZone = ptZone;
	}
	public String getPfZone() {
		return pfZone;
	}
	public void setPfZone(String pfZone) {
		this.pfZone = pfZone;
	}
	public ArrayList getTypeList() {
		return typeList;
	}
	public void setTypeList(ArrayList typeList) {
		this.typeList = typeList;
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
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public boolean isRecordsAvailable() {
		return recordsAvailable;
	}
	public void setRecordsAvailable(boolean recordsAvailable) {
		this.recordsAvailable = recordsAvailable;

	}
   public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
	
	
	
	

}