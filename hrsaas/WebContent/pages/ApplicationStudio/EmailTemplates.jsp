<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="net.fckeditor.*"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%
		FCKeditor fckEditor = 	new FCKeditor( request, "EditorDefault");
		fckEditor.setHeight("500");
		//fckEditor.setToolbarSet("PDToolbar");
		String defaultBodyText="";
		try{
		 	defaultBodyText=(String)request.getAttribute("defaultBodyText");
		 	if(defaultBodyText.equals("null")){
		 		defaultBodyText="Insert text here";
		  	}
		 }
		 catch(Exception e){
			 defaultBodyText="";
		 }		
	%>

<style>
.textAreaButton {
	background-color: #EFEFDE;
	border: 1px outset #CCCCCC;
	font: Arial 11px;
	font-size: bold;
}
</style>

<%@include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript"
	src="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/fckeditor.js"></script>

<s:form action="EmailTemplate" method="post" name="EmailTemplate"
	validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="text_head"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>

					<td width="100%" class="txt"><strong class="formhead">Email
					Templates </strong></td>
					<td width="3%" valign="top" class="txt" width="100%">
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
					<s:hidden name="tempContent" value="%{tempContent}" /> <s:hidden
						name="htmlcode" /> <input type="button" class="search"
						value=" Search" onclick="javascript:callPopup();" /> <s:submit
						cssClass="edit" action="EmailTemplate_update" theme="simple"
						value=" Save" onclick="return callSave(); " /><s:submit
						cssClass="reset" action="EmailTemplate_reset" theme="simple"
						value=" Reset" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="20%"><label name="template.name" id="template.name"
						ondblclick="callShowDiv(this);"><%=label.get("template.name") %></label>
					<font color="red">*</font>:</td>
					<td width="80%"><s:textfield name="tempName" size="60" /></td>
				</tr>
				<tr>
					<td width="25%" colspan="1" height="22"><label class="set"
						name="modName" id="modName" ondblclick="callShowDiv(this);"><%=label.get("modName")%></label>
					:<font color="red">*</font></td>
					<td width="25%" colspan="1" height="22"><s:hidden
						name="moduleCode" /> <s:textfield theme="simple"
						name="moduleName" size="30" readonly="true" /> <img
						src="../pages/images/search2.gif" height="16" align="absmiddle"
						width="16" theme="simple"
						onclick="javascript:callsF9(500,325,'EmailTemplate_f9ModuleName.action')">
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="5%" colspan="1" height="22" nowrap="nowrap"><label
						name="query" id="query" ondblclick="callShowDiv(this);"><%=label.get("query") %></label>
					: <font color="red">*</font></td>
					<td width="75%" colspan="1" height="22"><s:textarea
						name="qName" rows="10" cols="100" /><s:hidden name="hiddenEdit" /></td>
				</tr>
				<tr>
					<td width="15%" colspan="1" height="22"><label
						name="select.list1" id="select.list1"
						ondblclick="callShowDiv(this);"><%=label.get("select.list1") %></label>
					<font color="red">*</font></td>
					<td width="85%" colspan="1" height="22"><s:select
						name="querytype" headerKey="1" headerValue="--Select--"
						list="#{'F':'From Mail Id','T':'To Mail Id','D':'Table Data',
								'O':'Others'}" />
					</td>
				</tr>
				<tr>
					<td width="15%" colspan="1" height="22"><label
						name="noqueryparameter" id="noqueryparameter"
						ondblclick="callShowDiv(this);"><%=label.get("noqueryparameter") %></label></td>
					<td width="85%" colspan="1" height="22"><s:textfield
						name="noqueryparameter" size="12" maxLength="2"
						onkeypress="return numbersOnly();" /></td>
				</tr>
				<tr>
					<td width="15%" colspan="1" height="22"><label
						name="queryparameter" id="queryparameter"
						ondblclick="callShowDiv(this);"><%=label.get("queryparameter") %></label></td>
					<td width="85%" colspan="1" height="22"><s:textfield
						name="queryparameter" size="60" maxlength="1000" /></td>
				</tr>
				<tr>
					<td colspan="2"><br>
					<b> Note:-If No of Query Parameter more than one then put Query
					Parameter value seperated by (,)</b> <br>
					<b> e.g. P1-Q1-C0,P2-Q1-C1 </b> <br>
					<b> where P1-indicates parameter 1 </b> <br>
					<b> Q1-indicates Query 1 </b> <br>
					<b> C0-Indicates column no 0 of query 1. </b> <br>
					<br>
					</td>
				</tr>
				<tr>
					<td width="20%" colspan="1">&nbsp;</td>
					<td width="80%" colspan="2" height="22" valign="middle"><s:submit
						cssClass="add" action="EmailTemplate_add" theme="simple"
						value="    Add   " onclick="return callAdd()" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table class="sortable" width="100%">
				<tr class="td_bottom_border">
					<td align="center" class="formth" width="5%"><label
						name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no") %></label></td>
					<td align="center" class="formth" width="65%"><label
						name="query" id="query1" ondblclick="callShowDiv(this);"><%=label.get("query") %></label></td>
					<td align="center" class="formth" width="20%"><label
						name="select.list1" id="select.list2"
						ondblclick="callShowDiv(this);"><%=label.get("select.list1") %></label></td>
					<td align="center" class="formth" width="20%"><label
						name="noqueryparameter" id="noqueryparameter1"
						ondblclick="callShowDiv(this);"><%=label.get("noqueryparameter") %></label></td>
					<td align="center" class="formth" width="20%"><label
						name="queryparameter" id="queryparameter1"
						ondblclick="callShowDiv(this);"><%=label.get("queryparameter") %></label></td>
					<td class="formth" nowrap="nowrap" width="10%">Edit | Delete</td>

					<%
					int count1 = 0;
					%>
					<%!int d1 = 0;%>
					<%
					int ii = 0;
					%>
					<s:iterator value="list">
						<tr>
							<td width="5%" class="sortableTD"><%=++ii%><input
								type="hidden" name="srNo" value="<%=ii%>" /></td>

							<td width="65%" class="sortableTD"><s:property
								value="queryName" /><s:hidden name="queryName" /></td>
							<td width="20%" class="sortableTD"><s:property
								value="qtype1" /><s:hidden name="qtype" /></td>
							<td width="20%" class="sortableTD"><s:property
								value="ittrnoqueryparameter" /><s:hidden
								name="ittrnoqueryparameter" /></td>
							<td width="20%" class="sortableTD"><s:property
								value="ittrqueryparameter" /><s:hidden
								name="ittrqueryparameter" /></td>
							<input type="hidden" name="hdeleteOp" id="hdeleteOp<%=ii%>" />
							<td width="10%" align="center" class="sortableTD"><input
								type="button" class="rowEdit" onclick="callForEdit('<%=ii %>')" />
							<input type="button" class="rowDelete"
								onclick="callForDelete('<%=ii %>')" /></td>
						</tr>
					</s:iterator>
					<%
					d1 = ii;
					%>
				</tr>
				<s:hidden name="subcode" />
				<s:hidden name="tableLength" />
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="1" width="25%"><label name="from.mailids"
						id="from.mailids" ondblclick="callShowDiv(this);"><%=label.get("from.mailids") %></label><font
						color="red">*</font>:
					<td colspan="1"><s:textfield name="fromMailId" size="60"
						readonly="true" onclick="onClickFocus('fromMailId');"
						onkeyup="range=document.selection.createRange();"
						onfocus="range=document.selection.createRange();" /></td>
					<td width="100%"><s:iterator value="fromiterate">
						<%!int k = 0;%>
						<input type="button" class="textAreaButton"
							value='<s:property value="field" />'
							onclick="editAreaFrom(<%=k%>);" />
						<input type="hidden" name="field"
							value='<s:property value="field"/>' id="field1<%=k%>" />
						<%
						k++;
						%>
					</s:iterator> <s:submit cssClass="token" action="EmailTemplate_add"
						theme="simple" value="    Clear   " onclick="return fromClear()" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="to.mailids"
						id="to.mailids" ondblclick="callShowDiv(this);"><%=label.get("to.mailids") %></label><font
						color="red">*</font>:
					<td colspan="1"><s:textfield name="toMailId" size="60"
						readonly="true" /></td>
					<td width="100%"><s:iterator value="toiterate">
						<%!int m = 0;%>
						<input type="button" class="textAreaButton"
							value='<s:property value="field" />'
							onclick="editAreaTo('<%=m%>');" />
						<input type="hidden" name="field"
							value='<s:property value="field"/>' id="field2<%=m%>" />
						<%
						m++;
						%>
					</s:iterator> <input type="button" theme="simple" class="token"
						value="    Clear   " onclick="return cleartoken()" /></td>
				</tr>
				<tr>
					<td colspan="1" width="15%"><label name="subject" id="subject"
						ondblclick="callShowDiv(this);"><%=label.get("subject") %></label>:<font
						color="red">*</font>:
					<td colspan="3"><s:textfield name="subject" size="60"
						onkeyup="range=document.selection.createRange();"
						onfocus="range=document.selection.createRange();"
						onclick="onClickFocus('subject');" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td valign="middle" colspan="1" width="100%">
			<table width="100%" border="1" bgcolor="#EFEFDE" cellpadding="2"
				cellspacing="2">
				<tr bgcolor="#EFEFDE">
					<input type="checkbox" id="insertEditor"
						onclick="onClickFocus('MyTextarea');">
					INSERT IN EDITOR
					<td width="100%"><s:iterator value="iterate">
						<%!int i = 0;%>
						<input type="button" class="textAreaButton"
							value='<s:property value="field" />' onclick="editArea(<%=i%>);" />
						<input type="hidden" name="field"
							value='<s:property value="field"/>' id="field<%=i%>" />
						<%
						i++;
						%>
					</s:iterator></td>
				</tr>
			</table>
			</td>
		</tr>
		<%int x=0; %>
		<s:iterator value="ittTable">
			<tr>
				<td colspan="4">
				<table border="0" width="100%">
					<tr>
						<td width="25%" class="formtext" colspan="1" nowrap="nowrap"><s:property
							value="srNoOther" /></td>
						<td width="25%" colspan="2" nowrap="nowrap"><s:optiontransferselect
							size="8" doubleSize="8" doubleId="<%="selDivId"+x%>"
							id="<%="availDivId"+x%>" label="Employee Table"
							rightTitle="Selected Table" leftTitle="Available Table"
							addToLeftLabel="<< Remove" addToRightLabel="Add >>"
							addAllToLeftLabel="Remove All" addAllToRightLabel="Add All"
							selectAllLabel="Select All" cssStyle="width:100px"
							doubleCssStyle="width:100px" name="availTable" multiple="true"
							buttonCssClass="token" list="%{hashmapTable}"
							doubleName="selTable" doubleList="%{hashmapTableSel}" /></td>
						<td width="25%" class="formtext" colspan="1"><input
							type="button" class="token" value="Process"
							onclick="onProcess(<%=x%>,'<s:property value="srNoOther"/>');" /></td>
					</tr>
				</table>

				</td>
			</tr>
			<%x++; %>
		</s:iterator>
		<tr>
			<td colspan="4" width="100%" valign="top">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td>
					<%
						System.out.println("========bodyText======"+defaultBodyText);
						fckEditor.setValue(""+defaultBodyText);
						out.println(fckEditor);
					%> <s:hidden id="MyTextarea" name="MyTextarea" /></td>
					<!--  <textarea style="" id="MyTextarea" name="MyTextarea"
							onfocus="oFCKeditor.onClickFocus('MyTextarea');"
							onblur="onClickFocus('MyTextarea');"></textarea>-->
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><input type="button" class="search"
						value=" Search" onclick="javascript:callPopup();" /> <s:submit
						cssClass="edit" action="EmailTemplate_update" theme="simple"
						value=" Save" onclick="return callSave(); " /><s:submit
						cssClass="reset" action="EmailTemplate_reset" theme="simple"
						value=" Reset" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> &nbsp;</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
	function onProcess(id,srNo){
		var values=document.getElementById('selDivId'+id);
		var data='$'+(srNo)+'';
		var endData='$';
		for(i=0;i<values.options.length;i++){
			values.options[i].selected=true;
			var ms='<#'+ values.options[i].value+'#>' ;
					data=data+ms;
		}
		data=data+endData;
		var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		var var1=oEditor.GetHTML();
		oEditor.insertHtml(data);
		return false;
	}
	
	var oFCKeditor;
	/*window.onload = function(){
		setMess();
		oFCKeditor = new FCKeditor( 'MyTextarea' ) ;
		oFCKeditor.BasePath = 'http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/';
		oFCKeditor.ReplaceTextarea();
	}*/
	
	function setMess(){
		var ms=document.getElementById('paraFrm_htmlcode').value;		
		document.getElementById('MyTextarea').value=ms;
	}
	
	function setMessage(){
		var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		var ms=document.getElementById('paraFrm_htmlcode').value=oEditor.GetHTML();
	}
	
		
	function callSave(){
		var total='<%=d1%>';
		var query=document.getElementById('paraFrm_qName').value;
		var svar=document.getElementById('paraFrm_tempName').value;
		var fromMailId=document.getElementById('paraFrm_fromMailId').value;
		var toMailId=document.getElementById('paraFrm_toMailId').value;
		var subject=document.getElementById('paraFrm_subject').value;
		var moduleName=document.getElementById('paraFrm_moduleName').value;
		var fieldName = ["paraFrm_tempName"];
		var lableName = ["template.name"];
		var flag = ["enter"];
		setMessage();		
		if(svar==""){
			alert("Please enter "+document.getElementById('template.name').innerHTML.toLowerCase());
			return false;
		}
		if(moduleName==""){
			alert("Please "+document.getElementById('modName').innerHTML.toLowerCase());
			return false;
		}
		if(!(validateBlank(fieldName,lableName,flag))){
			return false;
        }
      	if(!f9specialchars(fieldName)){
           	return false;
   		}
   		if(total=='0'){
   			alert("Please add atleast one query");
   			return false;
   		}
   		if(fromMailId==""){
			alert("Please enter "+document.getElementById('from.mailids').innerHTML.toLowerCase());
			return false;
		}
		if(toMailId==""){
		alert("Please enter "+document.getElementById('to.mailids').innerHTML.toLowerCase());
			return false;
		}
		if(subject==""){
			alert("Please enter "+document.getElementById('subject').innerHTML.toLowerCase());
			return false;
		}
		return true;
	}

	
	function callPopup(){
		callsF9(500,325,'EmailTemplate_f9action.action');
		//setMessage();
	}

	function callForEdit(id){
   		document.getElementById('paraFrm_hiddenEdit').value=id;
   		document.getElementById("paraFrm").action="EmailTemplate_editQuery.action";
		document.getElementById("paraFrm").submit();
	   	return false;
  	}

	function callForDelete(id){
	 	var con=confirm('Do you really want to  delete the record ?');
 		if(con){
	 		document.getElementById('paraFrm_hiddenEdit').value=id;
   			document.getElementById("paraFrm").action="EmailTemplate_deleteQuery.action";
			document.getElementById("paraFrm").submit();
		}
    	else{
    		 return false;
    	}	   
   	}
	
 	function chkDelete(){
	 	if(chk()){
	 		var con=confirm('Do you really want to  delete the record ?');
 			if(con){
	   			document.getElementById('paraFrm').action="EmailTemplate_deleteDtl.action";
	    		document.getElementById('paraFrm').submit();
    		}
    		else{
	    		var flag='<%=d1 %>';
	  			for(var a=1;a<=flag;a++){	
	  			document.getElementById('confChkop'+a).checked=false;
	   			document.getElementById('confChkop'+a).checked="";
	   		 	document.getElementById('hdeleteOp'+a).value="";
	    	}
	     return false;
		 }
	 	}
	 	else {
	 	alert('Please select atleast one record to delete.');
	 	return false;
	 	}
	}
	
	function chk(){
	 	var flag='<%=d1 %>';
	  	for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChkop'+a).checked == true){	
	 	    return true;
	   }	   
	  }
	  	return false;
	}	
	var focusID;
	function editArea(id){
		var a= 'field'+id
		var ms='<#'+ document.getElementById(a).value+'#>' ;

		if(document.getElementById('insertEditor').checked==false){
			return false;
		}
		else{
			if(focusID!='MyTextarea'&& focusID!='toMailId' && focusID!='fromMailId'){	
				insert(ms);
			}   
			else{
				var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
				var var1=oEditor.GetHTML();
				oEditor.insertHtml(ms);
			}
		}
		return true;     
	}

	function editAreaTo(id){
		var a= 'field2'+id
		var ms='<#'+ document.getElementById(a).value+'#>' ;
		var type=document.getElementById("paraFrm_toMailId").value;	
		if(type==''){
		document.getElementById("paraFrm_toMailId").value=ms;
		}
		else{
		ms=';'+'<#'+ document.getElementById(a).value+'#>';
		document.getElementById("paraFrm_toMailId").value=document.getElementById("paraFrm_toMailId").value+ms;
		}
	}


	function callClear(){
		var str=document.getElementById("paraFrm_toMailId").value;
		str=str.substring(0,str.length-1);
		document.getElementById("paraFrm_toMailId").value=str;
	} 
	
	function editAreaFrom(id){
		var type=document.getElementById("paraFrm_fromMailId").value;
		var a= 'field1'+id
		var ms='<#'+ document.getElementById(a).value+'#>' ;
		if(type==''){
			document.getElementById("paraFrm_fromMailId").value=ms;
		}
		else{
			alert('you cannot enter more than one in FromMailId Field');
			return false;	
			//document.getElementById("paraFrm_fromMailId").value=document.getElementById("paraFrm_fromMailId").value+ms;
		}
	}

	function onClickFocus(id) {
	 focusID=id;
	 if(focusID!='MyTextarea'){
	  document.getElementById('insertEditor').checked=false;
	 }
	}


	function callAdd(){
	try {
	  //setMessage();
	  var type=document.getElementById("paraFrm_querytype").value;
	  var type1=document.getElementById('select.list1').innerHTML.toLowerCase(); 	  
	  var fields=["paraFrm_qName"];
      var labels=["query"];
      var flag = ["enter","select"];
 	  if(!(validateBlank(fields,labels,flag))){
     	return false;
      }
  	  if(type=='1'){
  		alert('please select any one of option from  '+type1);
  		return false;
  	  }
	 return true;
	}
	catch(e){
		alert(e);
	}
  }

	function fromClear(){
		var type=document.getElementById("paraFrm_fromMailId").value;
		if(type!=''){
			document.getElementById("paraFrm_fromMailId").value='';
			return false
		}
		else if(type==''){
			return false;
		}
		return true;
	}
	
/*function toClear(){
 alert('x');
 alert('focusID'+focusID);
var type=document.getElementById("paraFrm_fromMailId").value;
alert('type---'+type);
if(type!='')
{
document.getElementById("paraFrm_fromMailId").value='';
return false
}
return true;
}*/

	function cleartoken(){
		var s=document.getElementById("paraFrm_toMailId").value;
		var sarray=s.split(";");
		if(sarray.length>1){
 			var afterclear=" ";
   			for(var ii=0;ii<sarray.length-1;ii++){  				
   				afterclear+=sarray[ii]+ ";" ; 
  			}
			afterclear=afterclear.substring(0,afterclear.length-1);
			document.getElementById('paraFrm_toMailId').value=afterclear;
		}
		else{
			document.getElementById('paraFrm_toMailId').value="";
		}
		return;	
	}
</script>
<SCRIPT LANGUAGE="JavaScript">
	var range=null;
	function insert(char){
		if(range){range.text=char;
	}
  }
</SCRIPT>

