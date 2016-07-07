<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="InterviewAssessMasterAction" validate="true" id="paraFrm" validate="true" theme="simple">
	<s:hidden name="groupCode" />
	<table width="100%" align="right" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" align="right" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Interview Assessment Group</strong></td>
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
				<table width="100%" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="20%" height="22"><label class="set"
							name="groupNameLabel" id="groupNameLabel" ondblclick="callShowDiv(this);"><%=label.get("groupNameLabel")%></label>
							<font color="red">*</font> :
						</td>
						<td width="25%" height="22">
							<s:textfield size="25" maxlength="100" name="groupName"
								onkeypress="return allCharacters();" />
						</td>
						
						<td width="20%" height="22"><label class="set"
							name="groupAbbreviationLabel" id="groupAbbreviationLabel" ondblclick="callShowDiv(this);"><%=label.get("groupAbbreviationLabel")%></label>
							<font color="red">*</font> :
						</td>
						<td width="25%" height="22">
							<s:textfield size="25" maxlength="10" name="groupAbbreviation"
								onkeypress="return allCharacters();" />
						</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
					</tr>
					
					<tr>
						<td>
							<label name="descriptionLabel" class="set" id="descriptionLabel"
								ondblclick="callShowDiv(this);"><%=label.get("descriptionLabel")%>
							</label>:
						</td>
						<td colspan="3">
							<s:textarea name="groupDescription"
								cols="68" rows="3" onkeyup="callLength();" /><img
								src="../pages/images/zoomin.gif" height="12" align="absmiddle"
								id='ctrlHide' width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_groupDescription','descriptionLabel','','500','500');">
						</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
					</tr>

					<tr>
						<td>
							<label class="set" name="is.active"
								id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label> :
						</td>
						<td>
							<s:select cssStyle="width:170" list=" #{'Y':'Yes','N':'No'}" name="isGroupActive" />
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

<script type="text/javascript">
function saveFun() {
	var groupName = trim(document.getElementById('paraFrm_groupName').value); 
    if(groupName == ""){
	   alert("Please enter "+document.getElementById('groupNameLabel').innerHTML.toLowerCase());
	   document.getElementById('paraFrm_groupName').focus();
	   return false;
	}
	
	var groupAbbr = trim(document.getElementById('paraFrm_groupAbbreviation').value); 
    if(groupAbbr == ""){
	   alert("Please enter "+document.getElementById('groupAbbreviationLabel').innerHTML.toLowerCase());
	   document.getElementById('paraFrm_groupAbbreviation').focus();
	   return false;
	}
	
	var groupDescription = trim(document.getElementById('paraFrm_groupDescription').value);
	if (groupDescription.length > 500) {
		alert("Only 1000 characters are allowed.");
		document.getElementById('paraFrm_groupDescription').focus();
		return false;
	}
	
 	document.getElementById('paraFrm').target = "_self";
 	document.getElementById('paraFrm').action = 'InterviewAssessMasterAction_saveGroup.action';
	document.getElementById('paraFrm').submit();
}

function callLength(){ 
	var groupDesc = document.getElementById('paraFrm_groupDescription').value;
	var remain = 500 - eval(groupDesc.length);
	if(eval(remain) < 0) {
		document.getElementById('paraFrm_groupDescription').style.background = '#FFFF99';
	} else { 
		document.getElementById('paraFrm_groupDescription').style.background = '#FFFFFF';
	}	
}


function editFun() {
	document.getElementById('paraFrm').target = "_self";
 	document.getElementById('paraFrm').action = 'InterviewAssessMasterAction_editGroup.action';
	document.getElementById('paraFrm').submit();
} 	


function deleteFun() {
 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	document.getElementById('paraFrm').target = "_self";
 	document.getElementById('paraFrm').action = 'InterviewAssessMasterAction_deleteGroup.action';
	document.getElementById('paraFrm').submit();
	}
}

function resetFun() {
	document.getElementById('paraFrm').target = "_self";
 	document.getElementById('paraFrm').action = 'InterviewAssessMasterAction_resetGroup.action';
	document.getElementById('paraFrm').submit();
}

function backFun() {
	document.getElementById('paraFrm').target = "_self";
 	document.getElementById('paraFrm').action = 'InterviewAssessMasterAction_backToGroupList.action';
	document.getElementById('paraFrm').submit();
}
</script>
