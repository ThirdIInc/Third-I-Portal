	<%@ taglib prefix="s" uri="/struts-tags"%>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%@page import="com.crystaldecisions.reports.queryengine.collections.Properties"%>
	<%@page import="org.paradyne.bean.CR.DashBoardConfiguration"%>
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	</head>
	<script>
	
	
	
	function displayResult()
	{
	document.getElementById("cols1").colSpan="2";
	}
	
	
	function save(){
	alert("save function is called");
	}
	function back(){
	alert("back function is called");
	}
	function addReportList(){
	var repList=document.getElementById("reportList").value;
	document.getElementById("paraFrm").action='DashBoardConfiguration_addReportList.action?report='+repList; 
	document.getElementById("paraFrm").submit();
	document.getElementById("paraFrm").target="main";
	
	
	}
	function addDocumentList(){
	var docList=document.getElementById("documentList").value;
	document.getElementById("paraFrm").action='DashBoardConfiguration_addDocumentList.action?docList='+docList; 
	document.getElementById("paraFrm").submit();
	document.getElementById("paraFrm").target="main";
	}
	
	 
	
	
	
	</script>
	<s:form action="DashBoardConfiguration" id="paraFrm" validate="true"
			theme="simple" target="main">
	<s:hidden value="row" />	
	<s:hidden value="col" />	
			
			
	<table width="100%" align="right" class="formbg">
	<tr>
				<td valign="bottom" class="txt">
				<table width="100%" align="right" class="formbg">
					<tr>
						<td><strong class="text_head"> <img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /> </strong></td>
						<td width="93%" class="txt"><strong class="text_head">DashBoard Manage Accounts</strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"><img
							src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table> 
				</td>
			</tr>
	<tr>
	<td>
	<table width="100%" align="right" class="formbg">
	<tr>
			<td align="left"><input type="button" class="save"
				value="Save" onclick="return save();" />
			<input type="button" class="back"
				value="  Back " onclick="return backFun();" /></td>
		</tr>
	<tr>
	<td>
	Dashboard Name : &nbsp;
	
	<s:textfield  theme="simple"  size="25" >	</s:textfield>
	</td>
	</tr>
	</table>
	</td>
	</tr>
	
	<tr>
	<td>
	<strong class="text_head">CRM Dashborad View</strong>
	</td>
	</tr>
	
	<tr>
	<td>
	<br/>
	<table width="100%" border="1">
	<tr>
	
	<td width="25%" valign="top">
	<table >
	<tr>
	<td>
	<s:if test="reportList!=null">
		
		<s:select headerKey="-1" headerValue="select Report" list="reportList" id='reportList' onchange="addReportList()" >
		</s:select>
	</s:if>


		<table id="docTab">
		<s:if test="reportlistPara!=null">
		<s:iterator value="reportlistPara">
		<tr>
		<td>
		
		<s:property value="reportName"/>
		</td>
		</tr>
		</s:iterator>
		</s:if>
		</table>
		</td>
		</tr>
		
		<tr>
		<td>
	<s:if test="documentList!=null">
		<s:select  headerKey="-1" headerValue="select Documents" id="documentList" list="documentList" onchange="addDocumentList()" >
		</s:select>
		
	</s:if>
		<table>
		<s:if test="documentListPara!=null">
		<s:iterator value="documentListPara">
		<tr>
		<td>
		<s:property value="documentName"/>
		</td>
		</tr>
		
		</s:iterator>
		</s:if>
		</table>
		
	</table>	
		</td>
		
	<td>
	<table width="100%" border="0" >
	<tr>
	<td>
	<table width="100%" border="0" >
	<tr>
	<td width="24%">Define Dashboard Matrix :</td>
	<td width="5%"><s:textfield  theme="simple" id="row" size="5" value="%{row}" ></s:textfield> </td>
	<td width="5%">Row</td>
	<td width="5%"><s:textfield  theme="simple" id="col" size="5" value="%{col}" ></s:textfield></td>
	<td width="5%">Cols</td>
	<td></td>
	<td>
	<input type="button" value="OK" onclick="getDashBoardValue()">
	</td>
	</tr> 
	</table>
	</td>
	</tr>
	<tr>
	<td>Configure DashBoard
	<table width="100%" border="0">
	
	<%
	String row="";
	row=String.valueOf(request.getParameter("row"));
	String col=String.valueOf(request.getParameter("col"));
	System.out.println("Number of Rows - "+row+" Number of Cols - "+col);
	//For number of Rows
	int irow=0;
	if(row!=null && !row.equals("null")){
	 irow=Integer.parseInt(row);}
	int icol=0;
	if(col!=null&&!col.equals("null")){
	icol=Integer.parseInt(col);}
	int count=0;
	for(int i=0; i < irow ;i++){
	%>
	<tr>
	<%
	for(int j=0; j<icol; j++){
		%>
		<td>
		<table>
		<tr>
		<td width="25%">
		<s:select label="select Component" id="<%="Componentcount"+String.valueOf(count)%>"
		headerKey="-1" headerValue="select Component" list="componentList" name="list" value="list1"></s:select>
		<%count++; %>
		</td>
		<td>
		Show as :
		<input type="radio" name="r<%=count%>" >Graph
		</td>
		<td>
		<input type="radio" name="r<%=count%>" >Data
		</td>
		</tr>
		</table>
		</td>
		
		<% 
	}
	%>
	</tr>
	<%
	
	}//end of For  
	
	%>
	
	</table>
	</td>
	</tr>
	
	</table>
	</tr>
	</table>
	</td>
	</tr>
	<tr>
			<td align="left"><input type="button" class="save"
				value="Save" onclick="return save();" />
			<input type="button" class="back"
				value="  Back " onclick="return backFun();" /></td>
		</tr>

	</table>
	
	
			
	</s:form>	
	<SCRIPT type="text/javascript">
	
	//hideTab();
	//function hideTab(){
	//alert(document.getElementById("docTab").value);
	//deleteRow('docTab');
	//document.getElementById("docTab").style.display="none";
	//}
	
	function getDashBoardValue()
	{

	var row=document.getElementById("row").value;
	var col=document.getElementById("col").value;
	if(row=="" || col==""){
	alert("plz fill number of rows and  columns");
	return false;
	}
	document.getElementById("paraFrm").action='DashBoardConfiguration_setTableData.action?row='+row+'&col='+col; 
		  		document.getElementById("paraFrm").submit();
		  		document.getElementById("paraFrm").target="main";
	
	}
	
	
	
	</SCRIPT>
		
	
	</html>