<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ReimbursementConfiguaration" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="hiddenEmpId" />
	<s:hidden name="hiddenDivId" />
	<table class="formbg" width="100%">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reimbursement
					Configuration</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<tbody>
					<tr>
						<td width="70%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					</tr>
			</table>
		</tr>

		<!-- ############################################################ -->

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td class="formhead"><strong>Accountants </strong></td>
				</tr>
				<tr>
					<td><label class="set" id="accountEmpId1" name="accountEmpId"
						ondblclick="callShowDiv(this);"><%=label.get("accountName")%></label><font
						color="red">*</font>:</td>
					<td><s:hidden name="accountantEmpId" /><s:textfield
						name="accountantEmpToken" theme="simple"
						id="paraFrm_accountantEmpToken" size="20" readonly="true" /> 
						<s:textfield name="accountantEmpName" id="paraFrm_accountantEmpName" readonly="readonly" 
						maxlength="50" size="25" /> <img height="18" width="18"
						align="absmiddle"
						onclick="javascript:callsF9(500,325,'ReimbursementConfiguaration_f9Accountant.action');"
						class="iconImage" src="../pages/images/search2.gif" id="ctrlHide"></td>
				</tr>
				<tr>
					<td><label class="set" id="accountDivId1" name="accountDivId"
						ondblclick="callShowDiv(this);"><%=label.get("accountDivision")%></label><font
						color="red">*</font>:</td>
					<td><s:hidden name="accountantDivId" /> <s:textfield
						name="accountantDivName" id="paraFrm_accountantDivName"	readonly="readonly" /> 
						<img height="18" width="18" align="absmiddle"
						onclick="javascript:callsF9(500,325,'ReimbursementConfiguaration_f9Division.action');"
						class="iconImage" src="../pages/images/search2.gif" id="ctrlHide"></td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- ############################################################ -->
		<tr>
			<td width="100%">
			<table width="100%">
				<tbody>
					<tr>
						<td width="70%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					</tr>
			</table>
		</tr>
	</table>
</s:form>
<script>

	function saveFun() {
	
		if(!callValidate()){
			return false;
		}
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'ReimbursementConfiguaration_save.action';
			document.getElementById('paraFrm').submit();
	}
	
	
	function backFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ReimbursementConfiguaration_back.action';
		document.getElementById('paraFrm').submit();
	}
	
	function resetFun() {
	
  	 	document.getElementById('paraFrm').action = "ReimbursementConfiguaration_reset.action";
		document.getElementById('paraFrm').submit();
  	}
  	
  	function editFun() {
	
		document.getElementById('paraFrm').action = "ReimbursementConfiguaration_edit.action";
		document.getElementById('paraFrm').submit();
	}
  	
  	function deleteFun() {
		var conf=confirm("Are you sure you want to delete this record ?");
  			if(conf) {
		document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = "ReimbursementConfiguaration_delete.action";
		document.getElementById('paraFrm').submit();
		}
  	}
	
	function callValidate() {
	
		var empName = document.getElementById('paraFrm_accountantEmpName').value; 
		var empDiv = document.getElementById('paraFrm_accountantDivName').value; 
		if(empName == "") {
  			alert("Please select accountant name");
  			return false;
  		}
		if(empDiv == "") {
  			alert("Please select division");
  			return false;
  		}
  		return true;
	}
</script>
