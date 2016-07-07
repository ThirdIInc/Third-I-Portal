package org.paradyne.bean.CR;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class DashBoardConfiguration extends BeanBase{
	private String row="";
	private String col="";
	private String list="";
	private String list1="";
	ArrayList componentList = null;
	ArrayList reportList=null;//dropDown List
	ArrayList reportlistPara=null;//for display selected report
	private String reportName="";
	private String documentName="";
	ArrayList documentList=null;
	ArrayList documentListPara=null;
	public ArrayList getDocumentListPara() {
		return documentListPara;
	}
	public void setDocumentListPara(ArrayList documentListPara) {
		this.documentListPara = documentListPara;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public ArrayList getReportList() {
		return reportList;
	}
	public void setReportList(ArrayList reportList) {
		this.reportList = reportList;
	}
	
	public String getCol() {
		return col;
	}
	public void setCol(String col) {
		this.col = col;
	}
	public ArrayList getComponentList() {
		return componentList;
	}
	public void setComponentList(ArrayList componentList) {
		this.componentList = componentList;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public String getList1() {
		return list1;
	}
	public void setList1(String list1) {
		this.list1 = list1;
	}
	public ArrayList getDocumentList() {
		return documentList;
	}
	public void setDocumentList(ArrayList documentList) {
		this.documentList = documentList;
	}
	public ArrayList getReportlistPara() {
		return reportlistPara;
	}
	public void setReportlistPara(ArrayList reportlistPara) {
		this.reportlistPara = reportlistPara;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	

}
