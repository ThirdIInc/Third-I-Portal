<!-- Nilesh D -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<s:form action="ApprovalSettings" validate="true" id="paraFrm"
	validate="true" theme="simple">

<s:hidden name="divisionCode" />


	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Approval Settings
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
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%" colspan="1"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%" colspan="1">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">


				<tr>
					<td colspan="1" width="20%"><label class="set" name="division"
						id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					<font color ="red">*</font>:</td>
					<td colspan="1" width="80%"><s:hidden name="divId" /><s:textfield
						size="25" theme="simple" maxlength="30" name="approvalDivision"
						readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ApprovalSettings_f9division.action');"
						id="ctrlHide"></td>

				</tr>
			</table>
			</td>
		</tr>

	</table>
</s:form>

<script>
    function saveFun()
{
        document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'ApprovalSettings_save.action';
		document.getElementById('paraFrm').submit();
 			
  }	 
	
function resetFun() {
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'ApprovalSettings_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	    
  	}
	
	function cancelFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ApprovalSettings_cancel.action';
		document.getElementById('paraFrm').submit();
	
	}
	
	function backFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ApprovalSettings_input.action';
		document.getElementById('paraFrm').submit();
	
	}
	
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ApprovalSettings_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'ApprovalSettings_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}	
		
		
		function editFun() {
		
		return true;
		
	}		
	
	function nextFun()
	{
	var approvalDivision = document.getElementById('paraFrm_approvalDivision').value;
 if(approvalDivision=="")
 {
 alert("Please Select Division");
 return false;
 
 }
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'ApprovalSettings_saveNext.action';
	document.getElementById('paraFrm').submit();
	
	}
	
	
</script>	
