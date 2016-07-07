package org.paradyne.bean.Recruitment;

import java.awt.List;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class EntranceTestMaster extends BeanBase {
	private String examCode;
	private String examName;
	private String totalMarks;
	private String passMarks;
	private String myPage;
	private String show;
	private String  hiddencode;
	private ArrayList distList;
	public void setExamCode(String examcode)
	{
		this.examCode = examcode;
	}
	public String getExamCode()
	{
		return examCode;
	}
	public void setExamName(String examName)
	{
		this.examName = examName;
	}
	public String getExamName()
	{
		return examName;
	}
	public void setTotalMarks(String totalMarks)
	{
		this.totalMarks = totalMarks;
	}
	public String getTotalMarks()
	{
		return totalMarks;
	}
	public void setPassMarks(String passMarks)
	{
		this.passMarks = passMarks;
	}
	public String getPassMarks()
	{
		return passMarks;
	}
	public ArrayList getDistList() {
		return distList;
	}
	public void setDistList(ArrayList distList) {
		this.distList = distList;
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
}
