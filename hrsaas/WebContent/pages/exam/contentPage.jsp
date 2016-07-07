 
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

int contentCount = request.getAttribute("contentCount") == null ? 0
		: Integer.parseInt(request.getAttribute("contentCount").toString());
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
		<s:hidden name="contentId"/>
		
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
					<td>
						<input type="button" class="token" value="Save" onclick="save()"
						theme="simple" />
						<input type="button" class="token" onclick="backFun()"
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
					<td valign="top" width="100%"  colspan="4">Program name&nbsp;:			
					&nbsp; <s:property value="programeName"/>
					</td>					
				<%} else { %>
					<td valign="top" width="20%">Module name&nbsp;:	</td>
					<td valign="top" width="20%">
						<s:property value="subjectName"/>
					</td>
					<s:if test="%{categoryCode == ''}">
						<td>&nbsp; </td>
						<td>&nbsp; </td>
					</s:if>
					<s:else>
						<td valign="top" width="20%">Section name &nbsp; :</td>
						<td valign="top"><s:property	value="categoryName" /> </td>
					</s:else>
				<%} %>
				</tr>
				<!--<tr>
					<td valign="top" colspan="2"><%=labelName%>&nbsp;:&nbsp;<font color="red">*</font></td>					
				</tr>
				-->
				
				<tr>
					<td> <%=label.get("contenttypelabel")%>: </td>
					<td>
						<s:select theme="simple" name="contentType" value="%{contentType}" 
									list="#{'':'--Select--','text':'TEXT','pdf':'PDF','video':'VIDEO','ppt':'PPT'}" onchange="changeContentTytpe();" />
					</td>
					<td>&nbsp; </td>
					<td>&nbsp; </td>
				</tr>
				<tr>
					<td valign="top" ><%=label.get("contenttitlelabel")%>:<font color="red">*</font></td>
					<td>
						<s:textfield name="contentTitle" /> 
					</td>
					<td>&nbsp; </td>
					<td>&nbsp; </td>
				</tr>
				<tr id="otherContentTr">
					<td valign="top" ><%=label.get("contenturllabel")%>:<font color="red">*</font></td>
					<td>
						<s:textfield name="contentUrl" size="35" /> 
					</td>
					<td>&nbsp; </td>
					<td>&nbsp; </td>
				</tr>
				<tr id="textContentTr">
					<td valign="top" colspan="4"><textarea
						cssStyle="overflow:hidden" name="description" cols="125" rows="4">
						<s:property	value="content" />
						</textarea></td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		
		<tr>
			<td colspan="4">
				<%
				try {
				%>
				<%
					int totalPage = request.getAttribute("abc") == null ? 0	: (Integer) request.getAttribute("abc");
					int pageNo = request.getAttribute("xyz") == null ? 0 : (Integer) request.getAttribute("xyz");
				%>
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="formbg">
					<tr>
						<td class="formtext">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr>
								<td colspan="6" width="100%">
								<table width="100%">
									<tr>
										<td width="85%" align="left"><strong class="text_head">Content List : </strong></td>										
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<s:hidden name="myPage" id="myPage" />
								<td class="formth" align="center" nowrap="nowrap"><label
									class="set" name="serial.no" id="serial.no"
									ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
								</td>
								<td class="formth" align="center"><label class="set"
									name="contenttypelabel" id="contenttypelabel"
									ondblclick="callShowDiv(this);"><%=label.get("contenttypelabel")%></label>
								</td>
								
								<td class="formth" align="center"><label class="set"
									name="contenttitlelabel" id="contenttitlelabel"
									ondblclick="callShowDiv(this);"><%=label.get("contenttitlelabel")%></label>
								</td>
								
								<!--<td class="formth" align="center"><label class="set"
									name="contenturllabel" id="contenturllabel"
									ondblclick="callShowDiv(this);"><%=label.get("contenturllabel")%></label>
								</td>-->
								
								<td class="formth" align="center"><label class="set"
									name="contentorderlabel" id="contentorderlabel"
									ondblclick="callShowDiv(this);"><%=label.get("contentorderlabel")%></label>
								</td>
								
								<td class="formth" align="center"><label class="set"
									name="contentdeletelabel" id="contentdeletelabel"
									ondblclick="callShowDiv(this);"><%=label.get("contentdeletelabel")%></label></td>
							</tr>
								<%
										int count = 0;
										int cn = pageNo * 20 - 20;
								%>
								<%!int d = 0;%>
								<%
								int i = 0, counter = 0;
								%>
							<s:if test="%{contentList == null}">
								<tr>
									<td width="100%" align="center" colspan="5"><font
										color="red">There is no data to display.</font></td>
								</tr>
							</s:if>
							<s:else>
								<s:iterator value="contentList">

							<tr <%if(count%2==0){
								%> class="tableCell1"
								<%}else{%> class="tableCell2" <%	}count++; %>
											onmouseover="javascript:newRowColor(this);"
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">
								<td width="5%" align="center" class="sortableTD">
								<%
								++i;
								%><%=++cn%></td>
								
								<td align="center"><s:property value="contentType"/></td>
											
								<td align="left">
									<s:if test="%{contentType=='TEXT'}" >
											<s:property value="contentTitle"/>
									</s:if>
									<s:else>
										<a 	class="contlink" href="<s:property value="contentUrl"/>" target="_blank" 
											title="Click here to view">	<s:property value="contentTitle"/> </a>
									</s:else>
								</td>
								
								<!--<td align="center"><s:property value="contentUrl"/></td>-->
								
								<td align="center">
									<table width="100%">
										<tr width="100%">
											
											<td align="right" width="50%">
											<% if(counter != 0){ %>
												<a href="#" onclick="orderup('<s:property value="contentId"/>')" >
													<img  border="0" src="../pages/common/css/default/images/up.GIF"
														width="10" height="10"> 
												</a>
											<% } %> 
											</td>
											
											<td align="left" width="50%">
												<%
													if (counter != contentCount-1) {
												%>
												
												<a href="#" onclick="orderdown('<s:property value="contentId"/>')">
													<img border="0" src="../pages/common/css/default/images/down.GIF"
														width="10" height="10"> 
												</a> 
												<%	} %>
											</td>
										</tr>
									</table>
								</td>
								
								
								<td align="center">
									
									<a href="#" onclick="javascript:callForEdit(<s:property value="contentId"/>);" >
											<img  border="0" style="cursor: pointer;"
												src="../pages/mypage/images/icons/edit.png"
												title="Click here to edit"> 
									</a>
									&nbsp;
									<a href="#" onclick="javascript:callForContentDelete(<s:property value="contentId"/>);">
											<img  border="0" style="cursor: pointer;"
												src="../pages/mypage/images/icons/delete.png"
												title="Delete"> 
									</a>
									
								</td>
							</tr>
							<%
							++counter;
							%>
						</s:iterator>
					</s:else>
					<%
					d = i;
					%>
			</table>
			</td>
			</tr>
			</table>

			<%
				} catch (Exception e) {
				}
			%>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<!--<input type="button" class="token" value="Save" onclick="save()"
						theme="simple" /> -->
						<input type="button" class="token" onclick="backFun()"
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

function callForEdit(contentId){
	
	var calledAction = "ExamMaster_editContent.action";
	document.getElementById("paraFrm_contentId").value = contentId;
	document.getElementById("paraFrm").target="main";
	document.getElementById('paraFrm').action=calledAction;
	document.getElementById('paraFrm').submit();
}

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

function changeContentTytpe(){
	var contentType=document.getElementById('paraFrm_contentType').value;
	if(contentType=="text"){
		document.getElementById("textContentTr").style.display="";			
		document.getElementById("otherContentTr").style.display="none";			
	}else{
		document.getElementById("textContentTr").style.display="none";			
		document.getElementById("otherContentTr").style.display="";
	}
}


function validateContent(){

	var oEditor = CKEDITOR.instances.description;
	var desc=document.getElementById('paraFrm_content').value=oEditor.getData();
	var contentType=document.getElementById('paraFrm_contentType').value;
	var title = document.getElementById('paraFrm_contentTitle').value;
	var contentUrl = document.getElementById('paraFrm_contentUrl').value;
	if(title==""){
		alert("Please enter <%=label.get("contenttitlelabel")%>");
		return false;
	}
	if(contentType=="text"){
		if( desc == ""){
	  		alert("Please enter content");
	  		document.getElementById('paraFrm_description').focus();
	  		return false;
		}				
	}else{
		if(contentUrl==""){
			alert("Please enter <%=label.get("contenturllabel")%>");
			return false;
		}		
	}
	
	return true;
}

function callForContentDelete(contentId){
	var calledAction = "ExamMaster_deleteContent.action";
	document.getElementById("paraFrm_contentId").value = contentId;
	document.getElementById("paraFrm").target="main";
	document.getElementById('paraFrm').action=calledAction;
	document.getElementById('paraFrm').submit();
}

changeContentTytpe();

function orderup(contentId){
	var calledAction = "ExamMaster_contentOrderUp.action";
	document.getElementById("paraFrm_contentId").value = contentId;
	document.getElementById("paraFrm").target="main";
	document.getElementById('paraFrm').action=calledAction;
	document.getElementById('paraFrm').submit();
}

function orderdown(contentId){
	var calledAction = "ExamMaster_contentOrderDown.action";
	document.getElementById("paraFrm_contentId").value = contentId;
	document.getElementById("paraFrm").target="main";
	document.getElementById('paraFrm').action=calledAction;
	document.getElementById('paraFrm').submit();
}

</script>