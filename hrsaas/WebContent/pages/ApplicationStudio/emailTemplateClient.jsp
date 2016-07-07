<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp" %>
<style>
.textAreaButton{
	background-color: #EFEFDE;
	border: 1px outset  #CCCCCC;
	font: Arial 11px;
	font-size: bold;
}
</style>
<%--
<script type="text/javascript"
	src="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/fckeditor.js"></script>

--%>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
	<script src="../ckeditor/sample.js" type="text/javascript"></script>
	<link href="../ckeditor/sample.css" rel="stylesheet" type="text/css" />

<s:form action="EmailTemplateClient" method="post" name="EmailTemplate"
	validate="true" id="paraFrm" theme="simple">

	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">


		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="100%" class="txt"><strong class="text_head">Email
					Templates </strong></td>
					<td width="3%" valign="top" class="txt" width="100%">
					<div align="right"><img
						src="../pages/common/css/default/images/help.gif" width="16"
						height="16" /></div>
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
							cssClass="edit" action="EmailTemplateClient_update" theme="simple"
							value=" Update" onclick="return callSave(); " /><s:submit
							cssClass="reset" action="EmailTemplateClient_restoreDefault"
							theme="simple" value=" Restore Default"
							onclick="return setMessage(); " />
							<s:submit
							cssClass="reset" action="EmailTemplateClient_reset"
							theme="simple" value=" Reset" />
							
	
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
				<table width="100%" class="formbg">
	
					<tr>
						<td colspan="1" width="13%">
							<label name="template.name" id="template.name" ondblclick="callShowDiv(this);"><%=label.get("template.name") %></label> <font color="red">*</font>:
						<td>
						<td colspan="2"> 
							<s:property value="tempName" /><s:hidden name="tempName" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td colspan="1" width="20%">
							<label name="from.mailids" id="from.mailids" ondblclick="callShowDiv(this);"><%=label.get("from.mailids") %></label><font color="red">*</font>:
						
						<td colspan="3"><s:textfield name="fromMailId" size="60" readonly="true" /></td>
							
							
							
							<td width="100%">
						<s:iterator value="fromiterate">
	
							
							<%!int k = 0;%>
							<input type="button" class="textAreaButton" value='<s:property value="field" />'
								onclick="editAreaFrom(<%=k%>);" />
							<input type="hidden" name="field"
								value='<s:property value="field"/>' id="field1<%=k%>" />
							<%
							k++;
							%>
	
	
						</s:iterator>
											<s:submit
							cssClass="token" action="EmailTemplateClient_add" theme="simple"
							value="    Clear   " onclick="return fromClear()" /></td>
					</tr>
					<tr>
						<td colspan="1" width="20%"><label name="to.mailids" id="to.mailids" ondblclick="callShowDiv(this);"><%=label.get("to.mailids") %></label><font color="red">*</font>:
	
						
						<td colspan="3"><s:textfield name="toMailId" size="60" readonly="true"
							 /></td>
							 
						<td width="100%">
						<s:iterator value="toiterate">
	
							
							<%!int m = 0;%>
							<input type="button" class="textAreaButton" value='<s:property value="field" />'
								onclick="editAreaTo('<%=m%>');" />
							<input type="hidden" name="field"
								value='<s:property value="field"/>' id="field2<%=m%>" />
							<%
							m++;
							%>
	
	
						</s:iterator>	<input type="button"
							 theme="simple" class="token"
							value="    Clear   " onclick="return cleartoken()" /></td>					 
							 
							 
					</tr>	
					<tr>
						<td colspan="1" width="20%"><label name="subject" id="subject" ondblclick="callShowDiv(this);"><%=label.get("subject") %></label>:<font color="red">*</font>:
	
						
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
			<table width="100%" border="1" bgcolor="#EFEFDE" cellpadding="2" cellspacing="2" >
				<tr bgcolor="#EFEFDE">
					<input type="checkbox" id="insertEditor"
					 onclick="onClickFocus('MyTextarea');">INSERT IN EDITOR
					<td width="100%">
					<s:iterator value="iterate">

						
						<%!int i = 0;%>
						<input type="button" class="textAreaButton" value='<s:property value="field" />'
							onclick="editArea(<%=i%>);" />
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
               					<td width="25%" class="formtext" colspan="1" nowrap="nowrap"><s:property value="srNoOther"/></td>
               					<td width="25%" colspan="2" nowrap="nowrap"><s:optiontransferselect size="8" doubleSize="8" doubleId="<%="selDivId"+x%>"  id="<%="availDivId"+x%>"   
								label="Employee Table" rightTitle="Selected Table" leftTitle="Available Table" 
								addToLeftLabel="<< Remove" addToRightLabel="Add >>" addAllToLeftLabel="Remove All"
								addAllToRightLabel="Add All" selectAllLabel="Select All" cssStyle="width:100px"
								doubleCssStyle="width:100px" name="availTable" multiple="true"
								buttonCssClass="token" list="%{hashmapTable}"
								doubleName="selTable" doubleList="%{hashmapTableSel}" />
							
								
								
								</td>
								<td width="25%" class="formtext" colspan="1"><input type="button" class="token" value="Process"
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
					<td><textarea style="" id="MyTextarea" name="MyTextarea"
						onfocus="oFCKeditor.onClickFocus('MyTextarea');" onblur="onClickFocus('MyTextarea');"></textarea></td>
				</tr>
			</table>

			</td>


		</tr>
	</table>


</s:form>
<script type="text/javascript">
			//<![CDATA[
CKEDITOR.replace( 'MyTextarea');
				

			//]]>
</script>

<script>

			function onProcess(id,srNo){
				var values=document.getElementById('selDivId'+id);
				var data='$'+(srNo)+'';
				var endData='$';
				for(i=0;i<values.options.length;i++)
				{values.options[i].selected=true;
				
				var ms='<#'+ values.options[i].value+'#>' ;
				
					data=data+ms;
				}
				
				data=data+endData;
				//alert('data  :'+data);
				//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
				var oEditor = CKEDITOR.instances.MyTextarea;
				//var var1=oEditor.GetHTML();
				var var1=oEditor.getData();
				oEditor.insertHtml(data);
				
				return false;
				}


/*
	var oFCKeditor;
	window.onload = function()
	{
		
		oFCKeditor = new FCKeditor( 'MyTextarea' ) ;
		oFCKeditor.BasePath = 'http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/';
		
		oFCKeditor.ReplaceTextarea();
	}
	*/
	
	
	function setMess()
	{
		var ms=document.getElementById('paraFrm_htmlcode').value;
		document.getElementById('MyTextarea').value=ms;
	}
	setMess();
	
	function setMessage()
	{
		//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		var oEditor = CKEDITOR.instances.MyTextarea;
		//var ms=document.getElementById('paraFrm_htmlcode').value=oEditor.GetHTML();
		var ms=document.getElementById('paraFrm_htmlcode').value=oEditor.getData();
	}
		function callSave()
	{
		var svar=document.getElementById('paraFrm_tempCode').value;
			var fromMailId=document.getElementById('paraFrm_fromMailId').value;
		   var toMailId=document.getElementById('paraFrm_toMailId').value;
		  var subject=document.getElementById('paraFrm_subject').value;
		  
		  var fieldName = ["paraFrm_tempName"];
		 var lableName = ["template.name"];
		 var flag = ["enter"];
		
		if(svar=="")
		{
			alert("Please select "+document.getElementById('template.name').innerHTML.toLowerCase());
			return false;
		}
		if(!(validateBlank(fieldName,lableName,flag))){
			return false;
        }
        
      	if(!f9specialchars(fieldName)){
           	return false;
   		}
		if(fromMailId=="")
		{
			alert("Please enter "+document.getElementById('from.mailids').innerHTML.toLowerCase());
			return false;
		}
		
		if(toMailId=="")
		{
			alert("Please enter "+document.getElementById('to.mailids').innerHTML.toLowerCase());
			return false;
		}
		
		if(subject=="")
		{
			alert("Please enter "+document.getElementById('subject').innerHTML.toLowerCase());
			return false;
		}
		setMessage();
		return true;
	}

	
	function callPopup(){
		callsF9(500,325,'EmailTemplateClient_f9action.action');
		setMessage();
	
	}




</script>
<script type="text/javascript">
var focusID;
function editArea(id)
{
	//alert('focusID'+focusID);
	var a= 'field'+id
	var ms='<#'+ document.getElementById(a).value+'#>' ;
	if(focusID!='MyTextarea'){
	insert(ms);
	
}
else{
	//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
	var oEditor = CKEDITOR.instances.MyTextarea;
	//var var1=oEditor.GetHTML();
	var var1=oEditor.getData();
	oEditor.insertHtml(ms);
}
return true;    
 
}

function editAreaFrom(id)
{
  // alert('inner');
	//alert('focusID'+focusID);
	var type=document.getElementById("paraFrm_fromMailId").value;
	var a= 'field1'+id
	//alert('a--'+a);
	var ms='<#'+ document.getElementById(a).value+'#>' ;
	//alert('ms in edit area is'+ms);
	if(type=='')
	{
	//alert('if');
	document.getElementById("paraFrm_fromMailId").value=ms;
	
	}
	else
	{
	alert('you cannot enter more than one in FromMailId field');
	//document.getElementById("paraFrm_fromMailId").value=document.getElementById("paraFrm_fromMailId").value+ms;
	return false;
	}
}


function editAreaTo(id)
{
    
	
	var a= 'field2'+id
	//alert('a--'+document.getElementById(a).value);
	var ms='<#'+ document.getElementById(a).value+'#>' ;
	 //alert('ms in edit area is'+ms);
	var type=document.getElementById("paraFrm_toMailId").value;
	
	
	if(type=='')
	{
	
	document.getElementById("paraFrm_toMailId").value=ms;

	}
	

	else{
	
	ms=';'+'<#'+ document.getElementById(a).value+'#>';
	document.getElementById("paraFrm_toMailId").value=document.getElementById("paraFrm_toMailId").value+ms;
	}
}

function onClickFocus(id) {
//alert(1);
	focusID=id;
	if(focusID!='MyTextarea'){
	document.getElementById('insertEditor').checked=false;
	}
}

function setMessage()
{
//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
var oEditor = CKEDITOR.instances.MyTextarea;
//var ms=document.getElementById('paraFrm_htmlcode').value=oEditor.GetHTML();
var ms=document.getElementById('paraFrm_htmlcode').value=oEditor.getData();

}

function fromClear(){

 
 //alert('focusID'+focusID);
var type=document.getElementById("paraFrm_fromMailId").value;
//alert('type---'+type);
if(type!='')
{
document.getElementById("paraFrm_fromMailId").value='';
return false
}
else if(type=='')
{

return false;
}
return true;
}

function cleartoken()
{
//alert('x');
var s=document.getElementById("paraFrm_toMailId").value;
//alert('s---'+s);
var sarray=s.split(";");
if(sarray.length>1)
{
//alert('if--');
//alert('sarray.length'+sarray.length);
 var afterclear=" ";
   for(var ii=0;ii<sarray.length-1;ii++)
  {
  //alert('sarray--'+sarray[ii]);
   afterclear+=sarray[ii]+ ";" ; 
   //alert('afterclear--'+afterclear);
  }
//alert('out for'+afterclear.substring(0,afterclear.length-1));
afterclear=afterclear.substring(0,afterclear.length-1);
document.getElementById('paraFrm_toMailId').value=afterclear;
}else
{
//alert('else');
document.getElementById('paraFrm_toMailId').value="";
}
return;	
}


function callAdd(){
setMessage();
var fields=["paraFrm_qName"];
    var labels=["query"];
    var flag = ["enter"];
 	 if(!checkMandatory(fields,labels,flag))
     return false;

}
</script>
<SCRIPT LANGUAGE="JavaScript">
<!--
var range=null;
function insert(char){if(range){range.text=char;}}
//-->
</SCRIPT>

