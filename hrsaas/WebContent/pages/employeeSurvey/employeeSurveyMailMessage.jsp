<%@ taglib uri="/struts-tags" prefix="s"%>

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



<s:form action="EmployeeSurveyConfiguration" method="post"
	name="EmailTemplate" validate="true" id="paraFrm" theme="simple">

	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					Survey Configuration </strong></td>
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
					<td colspan="4"><s:hidden name="htmlcode" /> <input
						type="button" class="token" value="    Send Mail    "
						onclick="callOk();"> <input type="button" class="cancel"
						value="Cancel" onclick="callCancel();"></td>

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
					<td colspan="1" width="15%" nowrap="nowrap"><label
						name="survey.mailsubject" id="survey.mailsubject"
						ondblclick="callShowDiv(this);"><%=label.get("survey.mailsubject")%></label>
					:<font color="red">*</font>
					<td colspan="3"><s:textfield name="surveyMailSubject"
						size="98" /></td>
				</tr>

			</table>
			</td>
		</tr>



		<tr>

			<td colspan="4" width="100%" valign="top">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td><textarea style="" id="MyTextarea" name="MyTextarea"
						onfocus="oFCKeditor.onClickFocus('MyTextarea');"
						onblur="onClickFocus('MyTextarea');"></textarea></td>
				</tr>
			</table>

			</td>


		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">


				<tr>
					<td colspan="4"><input
						type="button" class="token" value="    Send Mail    "
						onclick="callOk();"> <input type="button" class="cancel"
						value="Cancel" onclick="callCancel();"></td>

					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>


	</table>

<s:hidden name="surveyCode" /> 
</s:form>

<script>

 
	var oFCKeditor;
	window.onload = function()
	{
		setMess();
		oFCKeditor = new FCKeditor( 'MyTextarea' ) ;
		oFCKeditor.BasePath = 'http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/';
		
		oFCKeditor.ReplaceTextarea();
	}
	function setMess()
	{
		var ms=document.getElementById('paraFrm_htmlcode').value;
		
		//alert(ms);
		document.getElementById('MyTextarea').value=ms;
	}
	
	
	
	
		function setMessage()
	{
	
	var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
	var ms=document.getElementById('paraFrm_htmlcode').value=oEditor.GetHTML();
	
	
	
	}
	
	
		function callCancel()
{  
 window.close();
}
 function callOk()
	{  
		try{
		
		setMessage();
				var fieldName = ["paraFrm_surveyMailSubject"];
				var lableName = ["survey.mailsubject"];
				var flag = ["enter"];
				
				var surveyMailSubject = document.getElementById('paraFrm_surveyMailSubject').value;
				var surveyMailMessage = document.getElementById('paraFrm_htmlcode').value;
				var surveyCode =opener.document.getElementById('paraFrm_surveyCode').value ;
				 
					if(!(validateBlank(fieldName,lableName,flag)))
					{
							return false;
        			}
			              
					  document.getElementById('paraFrm').action='EmployeeSurveyConfiguration_sendMailForSurvey.action';
			  		    document.getElementById('paraFrm').submit();
			  		      
			}
			catch(e)
			{ 
				alert("value of e-------------"+e);
			}
	}
		
 


</script>
