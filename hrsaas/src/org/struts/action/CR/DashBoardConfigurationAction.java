package org.struts.action.CR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.paradyne.bean.CR.DashBoardConfiguration;
import org.paradyne.bean.CR.ManageClientDashBoard;
import org.struts.lib.ParaActionSupport;

public class DashBoardConfigurationAction extends ParaActionSupport {

	DashBoardConfiguration bean;
	public DashBoardConfiguration getBean()
	  {
	    return this.bean;
	  }
	
	public Object getModel()
	  {
	    return this.bean;
	  }
	
	public void prepare_local() throws Exception {
		    this.bean = new DashBoardConfiguration();
		    this.bean.setMenuCode(5053);
		  }
	public String input(){
		System.out.println("input called");
		reportAndDocumentList();
		
		return INPUT;
	}
	public String setTableData(){
		String row=request.getParameter("row");
		String col=request.getParameter("col");
		bean.setRow(row);
		bean.setCol(col);
		ArrayList lt=new ArrayList();
		lt.add("Open Call");
		lt.add("Closed call");
		bean.setComponentList(lt);
		reportAndDocumentList();
		
		
		return "input";
	}
	
	public void reportAndDocumentList(){
		ArrayList reportlist=new ArrayList();
		reportlist.add("Open Calls UAL - NetWork");
		reportlist.add("Open Calls UAL -Houston");
		bean.setReportList(reportlist);
		ArrayList docList=new ArrayList();
		docList.add("Document 1");
		docList.add("Document 2");
		bean.setDocumentList(docList);
	}
	
	public String addReportList(){
		String reportPara=request.getParameter("report");
		ArrayList reportList=new ArrayList();
		bean.setReportName(reportPara);
		reportList.add(reportPara);
		bean.setReportlistPara(reportList);
		reportAndDocumentList();
		return INPUT;
		
	}
	
	public String addDocumentList(){
		String DocumentPara=request.getParameter("docList");
		ArrayList docuementList=new ArrayList();
		bean.setDocumentName(DocumentPara);
		docuementList.add(DocumentPara);
		bean.setDocumentListPara(docuementList);
		reportAndDocumentList();
	return INPUT;
	}
	
	
	
}
