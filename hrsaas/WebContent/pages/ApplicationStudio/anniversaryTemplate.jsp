<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="net.fckeditor.*" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>

<%
	//new FCKeditor(request, "EditorDefault");
		FCKeditor fckEditor = 	new FCKeditor( request, "EditorDefault");
		fckEditor.setHeight("500");
		fckEditor.setToolbarSet("PDToolbar");
		String bodyText="";
		try{
		 bodyText=(String)request.getAttribute("bodyText");
		 if(bodyText.equals("null")){
			 bodyText="Insert text here";
		 }
		 }
		 catch(Exception e){
			 bodyText="";
		 }
		
	%>




<s:form action="AnniversaryTemplate" method="post" name=""
	validate="true" id="paraFrm" theme="simple">

	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Anniversary
					Templates </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><s:hidden name="tempCode" value="%{tempCode}" />
					<s:hidden name="tempDate" /> <s:hidden name="tempContent"
						value="%{tempContent}" /> <s:hidden name="htmlcode" /> <input
						type="button" class="search" value=" Search"
						onclick="javascript:callPopup();" /> <s:submit cssClass="save"
						action="AnniversaryTemplate_save" theme="simple" value=" Save"
						onclick="return callSave(); " /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="20%" colspan="1" height="22"><label name="tempName"
						id="tempName" ondblclick="callShowDiv(this);"><%=label.get("tempName")%></label>
					<font color="red">*</font>:</td>
					<td width="80%" colspan="2" height="22"><s:textfield
						name="tempName" size="35" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td valign="middle" colspan="1" width="100%"><%!int i = 0;%><input
				type="button" value="Employee Name"
				onclick="editArea('<#EMPLOYEE_NAME#>');" class="token" /> <input
				type="button" value="Location Name"
				onclick="editArea('<#LOCATION_NAME#>');" class="token" /> <input
				type="button" value="Company Name"
				onclick="editArea('<#COMPANY_NAME#>');" class="token" /> <input
				type="button" value="Anniversary Date"
				onclick="editArea('<#ANNIVERSARY_DATE#>');" class="token" /> <input
				type="hidden" name="field" value="Name" id="field" /></td>
		</tr>
		<tr>
			<td colspan="2" width="100%">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td>
					<%
					System.out.println("========bodyText======"+bodyText);
			fckEditor.setValue(""+bodyText);
			out.println(fckEditor);
		
			
		%>
					<s:hidden  id="MyTextarea" name="MyTextarea" />
					
					
					
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
	var oFCKeditor;
	window.onload = function()
	{
		//setMess();
		//oFCKeditor = new FCKeditor( 'MyTextarea' ) ;
		//oFCKeditor.BasePath = 'http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/';
		//oFCKeditor.ReplaceTextarea() ;
	}
	function setMess()
	{
		var ms=document.getElementById('paraFrm_htmlcode').value;
		document.getElementById('MyTextarea').value=ms;
		//document.getElementById('MyTextarea').focus();
	}

	function setMessage()
	{
		var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		alert('oEditor   '+oEditor);
		document.getElementById('paraFrm_htmlcode').value=oEditor.GetHTML();
	}
</script>

<script>
	function callAdd(){
		setMessage();
		var fields=["paraFrm_qName"];
		var labels=["Query"];
		var flag = ["enter"];
		if(!checkMandatory(fields,labels,flag))
		   return false;
	}

	function newRowColor(cell)
   	{
		cell.className='onOverCell';
	}
		
	function oldRowColor(cell,val) {
	if(val=='1')
	 cell.className='tableCell2';
	else 
		cell.className='tableCell1';
	}
</script>

<script>
	function editArea(value)
	{
		//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		//var var1=oEditor.GetHTML();
		//oEditor.InsertHtml(value);
		//return true; 
		
		var oEditor = FCKeditorAPI.GetInstance('EditorDefault') ;
		var var1=oEditor.GetHTML();
		oEditor.InsertHtml(value);
		return true;  
		
		       
	}

	function setMessage()
	{
		var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		var ms=document.getElementById('paraFrm_htmlcode').value=oEditor.GetHTML();
	}
</script>

<script>
	function callSave()
	{
	
	try
	{
	var fieldName = ["paraFrm_tempName"];
	var lableName = ["tempName"];
	var flag = ["enter"];
		var svar=document.getElementById('paraFrm_tempName').value;
		if(svar=="")
		{
			alert("Please enter "+document.getElementById('tempName').innerHTML.toLowerCase());
			return false;
		}
		if(!(validateBlank(fieldName,lableName,flag))){
			return false;
        }
        
      	if(!f9specialchars(fieldName)){
           	return false;
   		}
		setMessage();
		return true;
		}
		catch(e)
		{
		
		//alert(e);
		}
	}
	
	function callPopup()
	{
	    try
		{		setMessage();
		}
		catch(e)
		{		
		}
		
		callsF9(500,325,'AnniversaryTemplate_f9action.action');
	}	
</script>