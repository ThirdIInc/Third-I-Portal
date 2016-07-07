<!-- @author: Reeba Joseph :: @date:10-04-2010 -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="HelpDeskDeptMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="editFlag" />
	<s:hidden name="cancelFlag" />

	<table class="formbg" align="right" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Configure
					Department</strong></td>
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
					<td width="75%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="22%" height="22" class="formtext"><label
						name="department" class="set" id="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:<font color="red">*</font></td>
					<td width="17%" height="22"><s:hidden name="deptCode" /> <s:textfield
						name="deptName" theme="simple" size="25" readonly="true" /></td>
					<td width="27%" height="22" class="formtext"><img
						id='ctrlHide' align="absmiddle"
						src="../pages/common/css/default/images/search2.gif"
						onclick="callSearch('f9deptaction');"></td>
					<td width="18%" height="22">&nbsp;</td>
				</tr>
				<tr>
					<td width="22%" height="22" class="formtext"><label
						name="dept.helpdesk" class="set" id="dept.helpdesk"
						ondblclick="callShowDiv(this);"><%=label.get("dept.helpdesk")%></label>
					:<font color="red">*</font></td>
					<td width="17%" height="22"><s:textfield name="deptHelpDesk"
						theme="simple" size="25" maxlength="50"
						onkeypress="return allCharacters();" /></td>
					<td width="27%" height="22" class="formtext">&nbsp;</td>
					<td width="18%" height="22">&nbsp;</td>
				</tr>
				<tr>
					<td width="20%" height="22" class="formtext"><label
						name="dept.head" class="set" id="dept.head"
						ondblclick="callShowDiv(this);"><%=label.get("dept.head")%></label>
					:<font color="red">*</font></td>
					<td><s:hidden name="deptHeadId" /> <s:textfield
						name="deptHead" theme="simple" size="25" readonly="true" /></td>
					<td height="22" class="formtext"><img id='ctrlHide'
						align="absmiddle"
						src="../pages/common/css/default/images/search2.gif"
						onclick="callSearch('f9Selectemp');"></td>
					<td height="22"><s:hidden name="deptHeaDToken" /></td>
				</tr>
				<tr>
					<td width="22%" height="22" class="formtext"><label
						name="active" class="set" id="active"
						ondblclick="callShowDiv(this);"><%=label.get("active")%></label>
					:</td>
					<td><s:checkbox name="isActive" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="75%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
	<s:hidden name="editHidcode" />
	<s:hidden name="hidDeptCode" />
</s:form>

<script>




// For Save Button

function saveFun(){	
	var val=trim(document.getElementById('paraFrm_deptCode').value);
	if(val==""){
		alert("Please select "+document.getElementById('department').innerHTML.toLowerCase());
		document.getElementById('paraFrm_deptName').focus();
		return false;
	}
	var abbr=trim(document.getElementById('paraFrm_deptHelpDesk').value);
	if(abbr==""){
		alert("Please enter "+document.getElementById('dept.helpdesk').innerHTML.toLowerCase());
		document.getElementById('paraFrm_deptHelpDesk').focus();
		return false;
	}
	var head=trim(document.getElementById('paraFrm_deptHeadId').value);
	if(head==""){
		alert("Please select "+document.getElementById('dept.head').innerHTML.toLowerCase());
		document.getElementById('paraFrm_deptHelpDesk').focus();
		return false;
	}
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'HelpDeskDeptMaster_save.action';
	document.getElementById('paraFrm').submit();
  	return true;
}  

	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'HelpDeskDeptMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HelpDeskDeptMaster_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HelpDeskDeptMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'HelpDeskDeptMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun() {
		document.getElementById('paraFrm_editHidcode').value = '1';
		return true;
	}
</script>
