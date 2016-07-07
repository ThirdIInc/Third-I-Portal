<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="ReportingStr" validate="true" id="paraFrm"
	theme="simple">
	<table class="formbg" width="100%">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reporting
					Structure </strong></td>
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
					<td width="78%" colspan="2">&nbsp;</td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="3" class="formhead"><strong
						class="forminnerhead">Reporting Structure</strong></td>
				</tr>

				<tr>
					<td width="20%" colspan="1"><label class="set"
						id="select.reporting" name="select.reporting"
						ondblclick="callShowDiv(this);"><%=label.get("select.reporting")%></label>
					<font color="red">* </font>:</td>
					<td width="15%" colspan="1"><!--
							 <s:select name="ReportingStr.Structure" list="#{'':'Select', 'Leave':'Leave', 'Cash':'Cash Voucher', 
								'Train':'Training', 'Tran':'Transfer', 'Sugg':'Suggestion', 'Help':'Help Desk', 'TYD':'Travel', 
								'Appra': 'Appraisal', 'Confere':'Conference', 'Loan':'Loan', 'Asset':'Asset', 'Purchase':'Purchase',
								'Recruitment':'Recruitment', 'ExtraDayBenefit':'Extra Day Benefit', 'Outdoor':'Outdoor Visit','Resign':'Resignation'}">
							</s:select>
						 --> <s:select name="ReportingStr.Structure" headerKey=""
						headerValue="Select" cssStyle="width:150" size="1" list="tmap"
						theme="simple" /></td>
					<!--						, 'Requi':'Requisition', 'LTC':'LTC Advance', 'Festi':'Festival Advance',
								'Other':'Other Advance', 'Deput':'Deputation', 'HBA':'HBA', 'GPF':'GPF', 'CTF':'Children Tution Fee',
								'Medi':'Medical Advance', 'Cash Process':'Cash Process'-->
					<td width="40%">&nbsp;</td>
				</tr>

				<tr>
					<td width="20%" colspan="1">&nbsp;</td>
					<td width="15%" colspan="1" align="center"><input
						type="button" class="token" theme="simple" value="   Next   "
						onclick="return checkGo();" /></td>
					<td width="40%">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">

function checkGo(){
	reportType = document.getElementById('paraFrm_ReportingStr_Structure').value

	var link = document.getElementById('paraFrm_ReportingStr_Structure').value;
	
	if(reportType ==""){
		//alert('Please Select Reporting Structure Type');
		alert("Please select the "+document.getElementById("select.reporting").innerHTML.toLowerCase());
		//document.getElementById('paraFrm_ReportingStr_Structure').focus();
		return false;
	}
	document.getElementById('paraFrm').action = "ReportingStr_forwardToDefaultPage.action?structureKey="+reportType;
	document.getElementById('paraFrm').submit();
	
}

</script>

