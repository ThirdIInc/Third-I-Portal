
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="LetterTypeMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="center" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Letter
					Type Master </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:submit cssClass="add"  action="LetterTypeMaster_save"
						onclick="return callForsave();" value=" Save" /> <s:submit action="LetterTypeMaster_delete"
						cssClass="add" value=" Delete"  onclick="return callFordelete();"  /> <s:submit cssClass="reset"  action="LetterTypeMaster_reset"
						value=" Reset" /> <input type="button" class="search" value=" Search"
						onclick="javascript:callsF9(500,325,'LetterTypeMaster_f9action.action')" />
					</td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		<tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td width="20%" colspan="1">Letter Type<font color="red">*</font> :</td>
					<td width="80%" colspan="2"><s:textfield name="letterTypeName" size="25" maxlength="50"/>
					<s:hidden name="letterTypeId" value="%{letterTypeId}" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
function callForsave()
{
var letterType=document.getElementById('paraFrm_letterTypeName').value;
 
	var fields=["paraFrm_letterTypeName"];
    var labels=["letter type"];
    var flag = ["enter"];
 	 if(!checkMandatory(fields,labels,flag))
 	 {
     	return false;
     }	
 
 if(letterType=="")
 {
 	alert("please enter letter type");
 	return false;
 }
return true;
}

function callFordelete()
{
var letterTypeId=document.getElementById('paraFrm_letterTypeId').value;
if(letterTypeId=="")
 {
 	alert("please select letter type");
 	return false;
 }
return true;
}

</script>