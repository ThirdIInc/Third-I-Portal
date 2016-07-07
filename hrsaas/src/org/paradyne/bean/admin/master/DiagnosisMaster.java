package org.paradyne.bean.admin.master;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/*
 * Pradeep Kumar Sahoo
 * Date:04.07.2007
 */

public class DiagnosisMaster extends BeanBase{
	
	String dCode="";
	String dName="";
	String dMCode="";
	
	ArrayList diagnList;
	private String myPage ;
	private String hiddencode;
	private String show;
	
	ArrayList iteratorlist;
	
	private String hdeleteCode;


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
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
    
	public DiagnosisMaster() { }
	public DiagnosisMaster(String dCode, String dName,String dMCode) {
		super();
		this.dCode = dCode;
		this.dName = dName;
		this.dMCode= dMCode;
	}
	public String getDCode() {
		return dCode;
	}
	public void setDCode(String code) {
		dCode = code;
	}
	public String getDName() {
		return dName;
	}
	public void setDName(String name) {
		dName = name;
	}
	public String getDMCode() {
		return dMCode;
	}
	public void setDMCode(String code) {
		dMCode = code;
	}
	public ArrayList getDiagnList() {
		return diagnList;
	}
	public void setDiagnList(ArrayList diagnList) {
		this.diagnList = diagnList;
	}
	
	
	
	
	

}

