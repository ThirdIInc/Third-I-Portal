<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<s:form action="BRDClassification" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="classificationCode" />
	<table width="100%" align="right" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" align="right" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">BRD Classification</strong></td>
					<td width="3%" valign="top" class="txt">
						<div align="right">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="2">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					<td width="20%">
						<div align="right"><font color="red">*</font> Indicates
						Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="2">
			<table width="100%" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td width="15%"><label class="set"
						name="classificationNameLabel" id="classificationNameLabel"
						ondblclick="callShowDiv(this);"><%=label.get("classificationNameLabel")%></label>
						<font color="red">*</font>:
					</td>
					<td width="85%">
						<s:textfield size="25" theme="simple" maxlength="50" name="classificationName" />
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

	</table>

</s:form>
<script>
function saveFun() {
	var classification = trim(document.getElementById('paraFrm_classificationName').value);
	if(classification == "") {
		alert("Please enter "+document.getElementById('classificationNameLabel').innerHTML.toLowerCase());
		document.getElementById('paraFrm_classificationName').focus();
		return false;
	}
    document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'BRDClassification_saveData.action';
	document.getElementById('paraFrm').submit();
}	 
	
function resetFun() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'BRDClassification_reset.action';
   	document.getElementById('paraFrm').submit(); 
}
	
function cancelFun() {
	document.getElementById('paraFrm').target = "_self";
   	document.getElementById('paraFrm').action = 'BRDClassification_cancel.action';
	document.getElementById('paraFrm').submit();
}
	
function backtolistFun() {
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'BRDClassification_cancel.action';
	document.getElementById('paraFrm').submit();
}
	
	
function deleteFun() {
var con=confirm('Do you want to delete the record(s) ?');
	if(con){
		document.getElementById('paraFrm').target = "_self";
	   	document.getElementById('paraFrm').action = 'BRDClassification_delete.action';
		document.getElementById('paraFrm').submit();
	}
}
		
function editFun() {
	return true;
}		
</script>
