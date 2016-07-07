<%@ taglib prefix="s" uri="/struts-tags"%>

<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />
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
			<td width="100%" colspan="6">&nbsp;</td>
		</tr>
		
		<tr>
			<td width="15%" colspan="1">&nbsp;</td>
			<td width="25%" colspan="1">Select Reporting Structure For <font color="red">* </font>:</td>
			<td width="50%" colspan="1"><s:select name="ReportingStr.Structure" list="#{'':'Select','Leave':'Leave', 'Requi':'Requisition', 'Cash':'Cash Voucher', 'Train':'Training',
					'Tran':'Transfer', 'Sugg':'Suggestion', 'Help':'Help Desk', 'LTC':'LTC Advance', 'Festi':'Festival Advance',
					'Other':'Other Advance', 'Deput':'Deputation', 'HBA':'HBA', 'GPF':'GPF', 'CTF':'Children Tution Fee',
					'Medi':'Medical Advance', 'TYD':'TYDuty'}"></s:select>
				
			</td>
		</tr>
		
		<tr>
			<td>&nbsp;<s:hidden name="reportingType"/></td>
		</tr>
		
		<tr>
			<td width="15%" colspan="1">&nbsp;</td>
			<td width="70%" colspan="2"><s:submit cssClass="pagebutton"  theme="simple"  
				value="   Report   " onclick="return checkGo();"/></td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">
function checkGo(){

	var link = document.getElementById('paraFrm_ReportingStr_Structure').value;
	
	if(link==""){
		alert('Please Select Reporting Structure Type');
		//document.getElementById('paraFrm_ReportingStr_Structure').focus();
		return false;
	}
	else{
	document.getElementById('paraFrm').target="_blank";
	document.getElementById('paraFrm').action="ReportingStr_report.action?reportType=S";
	//document.getElementById('paraFrm').target="main";
	}
}

</script>

