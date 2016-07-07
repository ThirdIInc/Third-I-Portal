<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%--
<script type="text/javascript"
	src="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/fckeditor.js"></script>
--%>

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
	<script src="../ckeditor/sample.js" type="text/javascript"></script>
	<link href="../ckeditor/sample.css" rel="stylesheet" type="text/css" />
	
<s:form action="LetterTemplateClient" method="post"
	name="LetterTemplateClient" validate="true" id="paraFrm" theme="simple">

	<table width="100%" border="0" cellpadding="1" cellspacing="1"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Letter
					Template </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><s:hidden name="tempCode" value="%{tempCode}" />
					<s:hidden name="tempContent" value="%{tempContent}" /> <s:hidden
						name="hiddenName" /> <s:hidden name="htmlcode" /> <input
						type="button" class="search" value=" Search"
						onclick="javascript:callPopup();" /> <s:submit cssClass="save"
						action="LetterTemplateClient_save" theme="simple" value="Update"
						onclick="return callCheck();" /> <s:submit cssClass="reset"
						action="LetterTemplateClient_reset" value="Reset"
						onclick="return callReset();"></s:submit> <s:submit
						cssClass="duplicate" action="LetterTemplateClient_duplicate"
						theme="simple" cssClass="save" value=" Duplicate"
						onclick="return callSave();"></s:submit></td>


					<td width="30%">
					<div align="right"><span class="style2"></span> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4" width="100%">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td><label name="template.name" id="template.name"
						ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label>:</td>
					<td height="20"><s:textfield name="tempName"
						size="30" /></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><label name="duplicatetemplate.name"
						id="duplicatetemplate.name" ondblclick="callShowDiv(this);"><%=label.get("duplicatetemplate.name")%></label>:<font
						color="red">*</font>:</td>
					<td height="20"><s:textfield name="dupTempName"
						size="30" /></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><label class="set" name="templateType"
						id="templateType" ondblclick="callShowDiv(this);"><%=label.get("templateType")%></label>:

					
					<td height="20"><s:select
						label="%{getText('status')}" theme="simple" name="templateType"
						list="statMap" /></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="templateOptFlag">

			<tr>
				<td>
				<table border="0" cellpadding="1" cellspacing="1" class="formbg"
					width="100%">

					<tr>
						<td><label name="variablename" id="variablename"
							ondblclick="callShowDiv(this);"><%=label.get("variablename")%></label>
						<font color="red">*</font>:</td>
						<td colspan="3"><s:textfield name="variableName"
							id="paraFrm_variableName" size="30" /></td>
					</tr>

					<tr>
						<td><label name="variablevalue"
							id="variablevalue" ondblclick="callShowDiv(this);"><%=label.get("variablevalue")%></label>
						<font color="red">*</font>:</td>
						<td colspan="3"><s:textfield name="variableValue"
							id="paraFrm_variableValue" size="112" /> <input type="button" value='Add'
							class="add" onclick="callAddVariable();" /></td>
					</tr>

					<tr>
						<td><label name="variablelist" id="variablelist"
							ondblclick="callShowDiv(this);"><%=label.get("variablelist")%></label></td>
						<td><s:select name="letterTempVar"
							list="letterTempMap" /></td>
						<td><input type="button" value='Add To Template'
							class="add" onclick="editLetterTempVar();" /></td>
						<td><input type="button" value='Remove'
								class="delete" onclick="removeLetterTempVar();" /></td>
					</tr>

					<tr>
						<td>Place Holder</td>
						<td><s:select name="templateOpt"
							list="templateOptMap" /></td>
						<td><input type="button" value='Add To Template'
							class="add" onclick="editAreaOpt();" /></td>
						<td><input type="button" value='Add To Variable'
							class="add" onclick="callAddToVariable();" /></td>
					</tr>

					<% int sal = 1; %>
					<s:iterator value="salaryItt">
						<tr>
							<td><s:property value="queryNameItt" /></td>
							<td><s:select name="templateOptSalary"
								id="<%="paraFrm_templateOptSalary"+sal%>"
								list="templateOptSalaryMap" /></td>
							<td><input type="button" value='Add To Template'
								class="add" onclick="editAreaOptSalary('<%=sal%>');" /></td>
							<td><input type="button" value='Add To Variable'
								class="add" onclick="callAddToVariableFromMap('<%=sal%>');" /></td>
						</tr>
						<% sal++; %>
					</s:iterator>

					<tr>
						<% int k = 0; %>
						<td colspan="4"><s:iterator value="iterate">
							<input type="hidden" name="field"
								value='<s:property value="field"/>' id="field<%=k%>" />
							<% k++; %>
						</s:iterator> <% k = 0; %>
						</td>
					</tr>

				</table>
				</td>
			</tr>

		</s:if>

		<tr>
			<td colspan="4" width="100%">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td><textarea style="" id="MyTextarea" name="MyTextarea"
						id="MyTextarea"></textarea></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="variablePriority" />
	</table>


</s:form>

<script type="text/javascript">
			//<![CDATA[
CKEDITOR.replace( 'MyTextarea');
				

			//]]>
</script>
<script>
		function callReset(){
		
		document.getElementById('paraFrm_htmlcode').value='';
		
		}

/*
var oFCKeditor;
window.onload = function()
{


oFCKeditor = new FCKeditor( 'MyTextarea' ) ;
oFCKeditor.BasePath = 'http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/';

oFCKeditor.ReplaceTextarea() ;


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
</script>
<script>
function editArea(id)
{
var a= 'field'+id

//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
var oEditor = CKEDITOR.instances.MyTextarea;
//var var1=oEditor.GetHTML();
var var1=oEditor.getData();
var ms='<#'+ document.getElementById(a).value+'#>' ;
oEditor.insertHtml(ms);
 return true;        
 
}

 

</script>

<script>
function callSave()
{

var tvar=trim(document.getElementById('paraFrm_tempName').value); 
var dvar=trim(document.getElementById('paraFrm_dupTempName').value);
var tempcode=document.getElementById('paraFrm_tempCode').value
if(tempcode=="")
{
alert("Please First Select Template Name to Duplicate by Searching");
return false;
}
if(dvar=="")
{
alert("Please enter Duplicate Template Name");
return false;

}
if(dvar == tvar){
alert("This Template Name already exist please enter different name");
return false;}

 else{
return true;
}

//setMessage();
return true;
}
	
function callCheck()
{

var tvar1=document.getElementById('paraFrm_tempName').value; 
var dvar1=document.getElementById('paraFrm_dupTempName').value;
var tempcode=document.getElementById('paraFrm_tempCode').value
if(tempcode=="")
{
alert("Please First Select Template Name for Updation by Searching");
return false;
}

if(tvar1=="")
{
alert("Please First Select Template Name for Updation by Searching");
return false;

}	
}	
	
	function callPopup(){
		setMessage();
		callsF9(500,325,'LetterTemplateClient_f9action.action');
	
	}
	
	function editLetterTempVar(id){
		
		//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		var oEditor = CKEDITOR.instances.MyTextarea;
		//var var1 = oEditor.GetHTML();
		var var1 = oEditor.getData();
		var ms = '<#'+ document.getElementById("paraFrm_letterTempVar").value+'#>' ;
		oEditor.insertHtml(ms);
		return true; 
		}
	
	function callAddVariable(){
	try{
	
	var fieldName = ["paraFrm_variableName","paraFrm_variableValue"];
	var lableName = ["variablename","variablevalue"];
	var flag = ["enter","enter"];
	
	if(!validateBlank(fieldName,lableName,flag)){
			return false;
        } else {
        document.getElementById("paraFrm").action="LetterTemplateClient_addVariable.action";
		document.getElementById("paraFrm").submit();
        }
	}catch(e){alert(e);}
	
	}
	
	function callAddToVariable(){
		try {
			var placeHolder = '<#'+document.getElementById('paraFrm_templateOpt').value+'#>';
			document.getElementById('paraFrm_variableValue').value += placeHolder;
			
		}catch(e){alert(e);}
	}
	
	function callAddToVariableFromMap(id){
		try {
			var newVals = '<#'+document.getElementById("paraFrm_templateOptSalary"+id).value+'#>';
			document.getElementById('paraFrm_variableValue').value += newVals;
		}catch(e){alert(e);}
	}
	function editAreaOpt(id)
		{
		
		//var a= 'field'+id
		//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		var oEditor = CKEDITOR.instances.MyTextarea;
		//var var1=oEditor.GetHTML();
		var var1=oEditor.getData();
		var ms='<#'+ document.getElementById("paraFrm_templateOpt").value+'#>' ;
		oEditor.insertHtml(ms);
		 return true;        
		}
		
	function editAreaOptSalary(id)
		{
		//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		var oEditor = CKEDITOR.instances.MyTextarea;
		//var var1=oEditor.GetHTML();
		var var1=oEditor.getData();
		var ms='<#'+ document.getElementById("paraFrm_templateOptSalary"+id).value+'#>' ;
		oEditor.insertHtml(ms);
		 return true;        
		}
	function removeLetterTempVar(){
				
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
	    		document.getElementById("paraFrm").action="LetterTemplateClient_deleteVariableLetterTemplate.action";
				document.getElementById("paraFrm").submit();
		}
	}
	
	
</script>