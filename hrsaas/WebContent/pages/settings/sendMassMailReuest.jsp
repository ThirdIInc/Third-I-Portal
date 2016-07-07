<!-- Nilesh Dhandare  16th Jan 2011-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<style>
.textAreaButton {
	background-color: #EFEFDE;
	border: 1px outset #CCCCCC;
	font: Arial 11px;
	font-size: bold;
}
</style>
<script type="text/javascript"
	src="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/fckeditor.js"></script>
<s:form action="TipsSetting" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="htmlcode" />
	<tr>
		<td colspan="2" valign="top">
		<table width="100%" cellspacing="1" cellpadding="1" border="0">
			<tr>
				<td colspan="1" width="100%" align="center"><font name=verdena size = "5">Tips Information </font></td>
			</tr>
		</table>
		</td>
	</tr>

            <tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>

	<tr>
		<td colspan="2" valign="top">
		<table width="100%" cellspacing="1" cellpadding="1" border="0">

			<tr>
				<td colspan="1" width="10%"><font name=verdena size = "2"> Subject : </Strong></td>
				<td colspan="1" width="90%"><s:textfield name="subject" size="70" /></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td colspan="2" valign="top">
		<table width="100%" cellspacing="1" cellpadding="1" border="0">
			<tr>
				<td colspan="1" width="100%"><textarea style="" id="myTextarea" 
					name="myTextarea" onfocus="oFCKeditor.onClickFocus('myTextarea');"
					onblur="onClickFocus('myTextarea');"></textarea></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td colspan="4" valign="top">
		<table width="100%" cellspacing="1" cellpadding="1" border="0">

			<tr>
				<td align="center"><input type="button" name="Send Mail"
					value="Send Mail" onclick="return sendMail();" /></td>
			</tr>
		</table>
		</td>
	</tr>

</s:form>
<script>
	var oFCKeditor;
	window.onload = function()
	{
		setMess();
		oFCKeditor = new FCKeditor( 'myTextarea' ) ;
		oFCKeditor.BasePath = 'http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/';
		
		oFCKeditor.ReplaceTextarea();
	}
	
function sendMail(){

	if(!callCheck()){
			return false;
	} else {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").action='<%=request.getContextPath()%>/setting/TipsSetting_sendData.action';
	   document.getElementById("paraFrm").submit();
	   window.close();
		
	}
}






  function callCheck()
 {
	var subject = document.getElementById('paraFrm_subject').value;
	if(subject=="")
	{
	alert("Please enter the Subject");
	return false;
	}
	var oEditor = FCKeditorAPI.GetInstance('myTextarea');
	var emailMesg =oEditor.GetHTML();
	
	
	if( emailMesg == ""){
  		alert("Please Enter Email Body");
  		document.getElementById('paraFrm_myTextarea').focus();
  		return false;
	}
	return true;
 }

function setMess()
	{
		var ms=document.getElementById('paraFrm_htmlcode').value;
		///alert(ms);
		document.getElementById('myTextarea').value=ms;
	}
	
	
	
	
		function setMessage()
	{
	
	var oEditor = FCKeditorAPI.GetInstance('myTextarea') ;
	var ms=document.getElementById('paraFrm_htmlcode').value=oEditor.GetHTML();
	
	
	
	}
	
	
	
	
	
	
</script>


