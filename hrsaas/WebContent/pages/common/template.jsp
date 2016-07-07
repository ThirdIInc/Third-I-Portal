
<%@ taglib prefix="s" uri="/struts-tags"%>

<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>

<script type="text/javascript"
	src="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/fckeditor.js"></script>

<s:form id="paraFrm" action="TemplateAction" name="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="100%" colspan="4">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">


				<tr>
					<td colspan="4" width="100%" valign="bottom"
						background="../pages/common/css/default/images/lines.gif"
						class="txt"></td>

				</tr>

				<tr>
					<td colspan="3" width="100%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" /></strong> <strong
						class="formhead">Template </strong></td>

					<td colspan="1" width="22%">
					<div align="right"><font color="red">*</font></span> Indicates
					Required</div>
					</td>

				</tr>



				<tr>
					<td colspan="4" width="100%" valign="bottom" class="txt"><img
						src="../pages/common/css/default/images/space.gif" height="5" /></td>
				</tr>


				<tr>
					<td height="5" colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="7" /></td>
				</tr>


				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">

						<tr>
							<s:hidden name="tempId" value="%{tempId}" />
							<s:hidden name="tempContent" value="%{tempContent}" />
							<s:hidden name="htmlcode" />
							<td colspan="4" width="100%"><s:submit name="Submit"
								value="   Submit" action="TemplateAction_Save" cssClass="save"
								theme="simple" onclick="return callSave();" /> <s:submit
								name="reset" value="   Reset" cssClass="Reset" theme="simple"
								action="TemplateAction_reset" /> <s:submit name="delete"
								cssClass="delete" value="   Delete"
								action="TemplateAction_delete" theme="simple"
								onclick="return callDelete();" /> <input type="button"
								class="search" value="    Search"
								onclick="javascript:callsF9(500,325,'TemplateAction_f9tempId.action'); " />
							<!-- 

							<input type="button" class="token"
								action="TemplateAction_wordReport" value="  Report " /></td>
 --></td>
						</tr>

						<tr>
							<td height="5" colspan="4"><img
								src="../pages/common/css/default/images/space.gif" width="5" /></td>
						</tr>

					</table>

					</td>
				</tr>




				<tr>
					<td colspan="4"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>


				<tr>
					<td colspan="4">Template Name <font color="red">*</font>: <s:textfield
						name="tempName" /></td>
				</tr>


				<tr>
					<td colspan="4">Select Query :&nbsp; &nbsp; &nbsp;&nbsp; <s:textfield
						name="qName" readonly="true" /> <img
						src="../pages/common/css/default/images/search2.gif" width="16"
						height="15" onclick="javascript:callPopup();"> <s:hidden
						name="qId" /></td>
				<tr>
				<tr>
					<td colspan="4"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>

				<tr>
					<td colspan="4"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>

				<tr>
					<td colspan="4"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>

				<tr>

					<td colspan="3" width="100%">


					<table width="100%" cellspacing="0" cellpadding="0" border="0">

						<tr>


							<td><textarea style="" id="MyTextarea" name="MyTextarea"></textarea></td>
						</tr>
					</table>

					</td>

					<td valign="middle" colspan="1" width="30%">
					<table width="100%" border="0">
						<s:iterator value="iterate">
							<tr>
								<td colspan="1" width="30%"><%!int i = 0;%> <a href="#"
									onclick="editArea(<%=i%>);"><s:property value="field" /></a> <input
									type="hidden" value='<s:property value="field"/>'
									id="field<%=i%>" /> <%
 i++;
 %>
								</td>
							</tr>
						</s:iterator>
					</table>
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

setMess();
oFCKeditor = new FCKeditor( 'MyTextarea' ) ;
oFCKeditor.BasePath = 'http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/';

oFCKeditor.ReplaceTextarea() ;


}
function setMess()
{


var ms=document.getElementById('paraFrm_htmlcode').value;
document.getElementById('MyTextarea').value=ms;

}


function setMessage()
{
var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
var ms=document.getElementById('paraFrm_htmlcode').value=oEditor.GetHTML();


}



</script>

<script>
function callPopup(){
setMessage();
//document.getElementById("htmlcode").value=document.getElementById("HTMLBOX").innerText;

callsF9(500,325,'TemplateAction_f9query.action');

}
</script>



<script>


function editArea(id)
{
var a= 'field'+id

var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
var var1=oEditor.GetHTML();
var ms='<#'+ document.getElementById(a).value+'#>' ;
oEditor.InsertHtml(ms);
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
var svar=document.getElementById('paraFrm_tempName').value;
alert(svar);
if(svar=="")
{
alert("Please Enter the Template Name !");
return false;
}
setMessage();
return true;
}
</script>

