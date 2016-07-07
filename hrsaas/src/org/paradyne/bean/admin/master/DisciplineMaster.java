package org.paradyne.bean.admin.master;
/**
 * author muzaffar
 */
import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class DisciplineMaster extends BeanBase implements Serializable {
	
	String disciplineID = "";
	String disciplineName = "";
	String disciplineDesc = "";
	String disciplineAbbr = "";
	String disciplineParID = "";
	String disciplineParName = "";
	private String myPage;
	private String show;
	private String  hiddencode;
	ArrayList dispArray=null;
	
	public DisciplineMaster()
	{}
	public DisciplineMaster(String disciplineID, String disciplineName, String disciplineDesc, String disciplineAbbr, String disciplineParID) {
		this.disciplineID =  disciplineID;
		this.disciplineName = disciplineName;
		this.disciplineDesc = disciplineDesc;
		this.disciplineAbbr = disciplineAbbr;
		this.disciplineParID = disciplineParID;
	}
	
	public String getDisciplineAbbr() {
		return disciplineAbbr;
	}
	public void setDisciplineAbbr(String disciplineAbbr) {
		this.disciplineAbbr = disciplineAbbr;
	}
	public String getDisciplineDesc() {
		return disciplineDesc;
	}
	public void setDisciplineDesc(String disciplineDesc) {
		this.disciplineDesc = disciplineDesc;
	}
	public String getDisciplineID() {
		return disciplineID;
	}
	public void setDisciplineID(String disciplineID) {
		this.disciplineID = disciplineID;
	}
	public String getDisciplineName() {
		return disciplineName;
	}
	public void setDisciplineName(String disciplineName) {
		this.disciplineName = disciplineName;
	}
	public String getDisciplineParID() {
		return disciplineParID;
	}
	public void setDisciplineParID(String disciplineParID) {
		this.disciplineParID = disciplineParID;
	}
	public ArrayList getDispArray() {
		return dispArray;
	}
	public void setDispArray(ArrayList dispArray) {
		this.dispArray = dispArray;
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
	public String getDisciplineParName() {
		return disciplineParName;
	}
	public void setDisciplineParName(String disciplineParName) {
		this.disciplineParName = disciplineParName;
	}

}
