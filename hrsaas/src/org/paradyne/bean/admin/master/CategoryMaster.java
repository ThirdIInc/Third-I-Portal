package org.paradyne.bean.admin.master;
import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author hemant 
 * Date 26-04-07
 */
public class CategoryMaster extends BeanBase implements Serializable {
	private String categoryDesc="";
	String catgID="";
	String catgName="";	
	String catgDesc="";
	String auth="";
	String catgAge="";
	String authDt="";
	
	String isActive=""; // Status of CategoryMaster
	
	private String myPage;
	private String show;
	private String  hiddencode;
	private String  modeLength="false";
	private String totalRecords="";
	private String hdeleteCode;
	
	
	ArrayList catg=null;
	public CategoryMaster(){		
	}
	public CategoryMaster(String catgID, String catgName, String catgDesc, String auth, String catgAge, String authDt, ArrayList catg) {
		super();
		this.catgID = catgID;
		this.catgName = catgName;
		this.catgDesc = catgDesc;
		this.auth = auth;
		this.catgAge = catgAge;
		this.authDt = authDt;
		this.catg = catg;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getAuthDt() {
		return authDt;
	}
	public void setAuthDt(String authDt) {
		this.authDt = authDt;
	}
	public ArrayList getCatg() {
		return catg;
	}
	public void setCatg(ArrayList catg) {
		this.catg = catg;
	}
	public String getCatgAge() {
		return catgAge;
	}
	public void setCatgAge(String catgAge) {
		this.catgAge = catgAge;
	}
	public String getCatgDesc() {
		return catgDesc;
	}
	public void setCatgDesc(String catgDesc) {
		this.catgDesc = catgDesc;
	}
	public String getCatgID() {
		return catgID;
	}
	public void setCatgID(String catgID) {
		this.catgID = catgID;
	}
	public String getCatgName() {
		return catgName;
	}
	public void setCatgName(String catgName) {
		this.catgName = catgName;
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
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
		
}