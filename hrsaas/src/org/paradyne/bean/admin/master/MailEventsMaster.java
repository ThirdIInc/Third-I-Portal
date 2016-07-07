package org.paradyne.bean.admin.master;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class MailEventsMaster extends BeanBase {

	
	private String moduleCode;
	
	private String myPage ;
	private String hiddencode;
	private String show;
	
	ArrayList iteratorlist;
	
	private String hdeleteCode;		
	private String status;
	private String selectname="";
		
	private String mailEventcode;
	private String mailEventName;
	private String mailEventdesc;
	private String moduleName;
	private String dupModulecode="";
	
		TreeMap map=null;
	
	
	public TreeMap getMap() {
		return map;
	}
	public void setMap(TreeMap map) {
		this.map = map;
	}
	
	
	public String getMailEventcode() {
		return mailEventcode;
	}
	public void setMailEventcode(String mailEventcode) {
		this.mailEventcode = mailEventcode;
	}
	public String getMailEventName() {
		return mailEventName;
	}
	public void setMailEventName(String mailEventName) {
		this.mailEventName = mailEventName;
	}
	public String getMailEventdesc() {
		return mailEventdesc;
	}
	public void setMailEventdesc(String mailEventdesc) {
		this.mailEventdesc = mailEventdesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	public String getDupModulecode() {
		return dupModulecode;
	}
	public void setDupModulecode(String dupModulecode) {
		this.dupModulecode = dupModulecode;
	}
	
}
