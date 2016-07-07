package org.paradyne.bean.TravelManagement;
import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/*
 * @author SaiPavanKumar 
 *  Date:17-09-2008
 */
public class TravelAdminMaster extends BeanBase {
	private String Branchname; 
	private String Branchcode;
	private String  Employeename;
	private String empid;
	private String  travelmastercode;
	
	
	private String myPage ;
	private String hiddencode;
	private String show;
	
	ArrayList iteratorlist;
	
	private String hdeleteCode;
	
	public String getBranchname() {
		return Branchname;
	}
	public void setBranchname(String branchname) {
		Branchname = branchname;
	}
	public String getBranchcode() {
		return Branchcode;
	}
	public void setBranchcode(String branchcode) {
		Branchcode = branchcode;
	}
	public String getEmployeename() {
		return Employeename;
	}
	public void setEmployeename(String employeename) {
		Employeename = employeename;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getTravelmastercode() {
		return travelmastercode;
	}
	public void setTravelmastercode(String travelmastercode) {
		this.travelmastercode = travelmastercode;
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
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

}
