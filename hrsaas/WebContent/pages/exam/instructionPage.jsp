 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<%
String labelName ="";
String from ="";
	try{
		labelName= (String) request.getAttribute("labelName");
		  from = (String) request.getAttribute("from");
	}
catch(Exception e)
{
	labelName ="";
	from ="";
}
%>
<s:form action="" validate="true" id="paraFrm"
	theme="simple">

	
	<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
	<script src="../ckeditor/sample.js" type="text/javascript"></script>
	<link href="../ckeditor/sample.css" rel="stylesheet" type="text/css" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">

		<s:hidden name="content" />
		<s:hidden name="categoryCode"/>
		<s:hidden name="subjectCode"/>
		<s:hidden name="programeId"/>
		<s:hidden name="from" value="<%=from %>"/>
		<tr>
			<td colspan="4" width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					<% if(from.equals("programedit")|| from.equals("programlist")){ %> 
						Program Master
					<%} else { %>
						Module
					<%} %>
					</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><input type="button" class="token" value="Save" onclick="save()"
						theme="simple" /> <input type="button" class="token" onclick="backFun()"
						value="Back" theme="simple" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" class="formbg" border="0">
				<tr>
				<% if(from.equals("programedit")||from.equals("programlist")){ %> 
					<td valign="top" width="20%">Program name&nbsp;:			
					&nbsp; <s:property value="programeName"/>
					</td>					
				<%} else { %>
					<td valign="top" width="20%">Module name&nbsp;:			
					&nbsp; <s:property value="subjectName"/>
					</td>
					<s:if test="%{categoryCode == ''}">
					</s:if>
					<s:else>
					<td valign="top">Section name &nbsp; : 
					&nbsp; <s:property	value="categoryName" />
					</td>
					</s:else>
				<%} %>
				</tr>
				<!--<tr>
					<td valign="top" colspan="2"><%=labelName%>&nbsp;:&nbsp;<font color="red">*</font></td>					
				</tr>
				--><tr>
					<td valign="top" colspan="2"><textarea
						cssStyle="overflow:hidden" name="description" cols="125" rows="4">
						<s:property	value="content" />
						</textarea></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><input type="button" class="token" value="Save" onclick="save()"
						theme="simple" /> <input type="button" class="token" onclick="backFun()"
						value="Back" theme="simple" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
			//<![CDATA[
//CKEDITOR.replace( 'description');
			//]]>
			
			var editor = CKEDITOR.replace('description',
          {


            toolbar :
                [
                   { name: 'document',    items : [ 'Source','-','DocProps','Preview','Print','-' ] },
				    { name: 'clipboard',   items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
				    { name: 'editing',     items : [ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ] },
				    { name: 'forms',       items : [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField' ] },
				    '/',
				    { name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
				    { name: 'paragraph',   items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
				    { name: 'links',       items : [ 'Link','Unlink','Anchor' ] },
				    { name: 'insert',      items : [ 'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak' ] },
				  
				    { name: 'styles',      items : [ 'Styles','Format','Font','FontSize' ] },
				    { name: 'colors',      items : [ 'TextColor','BGColor' ] },
				    { name: 'tools',       items : [ 'Maximize', 'ShowBlocks','-','About' ] }


                ]
               	 
                

            });


CKEDITOR.instances.editor.resize(500, 400);
	
	
			</script>
<script>
function backFun(){
	var from = "<%=from %>";
	var calledAction = "";
	if(from == 'programedit'){
		calledAction = "ProgrammeMaster_callForEdit.action";
	} 
	else if(from=='programlist')
	{
	calledAction = "ProgrammeMaster_input.action";
	}
	else {
		if(from == 'edit' || from == 'catedit'){
			calledAction = "ExamMaster_calforedit.action";
		} else {
			calledAction = "ExamMaster_cancelSecond.action";
		}
	}
	document.getElementById("paraFrm").target="main";
	document.getElementById('paraFrm').action=calledAction;
	document.getElementById('paraFrm').submit();
}
 
function save(){
	var from = "<%=from %>";
	if(!validateContent()){
			return false;
	} 
	
	else {
		var update = "<%=labelName %>";
		if(from == 'programedit'){
			document.getElementById('paraFrm').action="<%=request.getContextPath()%>/WBT/ProgrammeMaster_updateInstruction.action";
		}else if(from=='programlist'){
			document.getElementById('paraFrm').action="<%=request.getContextPath()%>/WBT/ProgrammeMaster_updateInstruction.action";
		}
		 else {
			if(update=="Instructions") {
				document.getElementById('paraFrm').action="<%=request.getContextPath()%>/master/ExamMaster_updateInstruction.action";
			} else {
				document.getElementById('paraFrm').action="<%=request.getContextPath()%>/master/ExamMaster_updateContent.action";
			}
		}	
		document.getElementById('paraFrm').target = "main";	
		document.getElementById('paraFrm').submit();
	}
}

function validateContent(){

	var oEditor = CKEDITOR.instances.description;
	var desc=document.getElementById('paraFrm_content').value=oEditor.getData();
	/*	
	if( desc == ""){
  		alert("Please enter <%=labelName %>");
  		document.getElementById('paraFrm_description').focus();
  		return false;
	}
	*/
	return true;
}
</script>