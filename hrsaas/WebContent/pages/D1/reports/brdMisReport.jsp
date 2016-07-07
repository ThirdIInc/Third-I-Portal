<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="BRDMisReport" validate="true"
	id="paraFrm" theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">BRD MIS Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr valign="middle">
						<td nowrap="nowrap" valign="middle" width="10%">
							<a href="#" onclick="callResetFun();">
								<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" title="Reset"> 
									Reset 
							</a>
						</td>
						
						<td nowrap="nowrap" width="20%">
							<img src="../pages/images/buttonIcons/file-xls.png" class="iconImage"
								align="absmiddle" style="padding-right: 5px;">
							<a href="#" onclick="callReport('Xls', 'MIS');" title="Click here to generate MIS reoprt"><u>Generate MIS Report</u></a>
						</td>
						
						<td width="20%">	
							<img src="../pages/images/buttonIcons/file-xls.png" class="iconImage"
								align="absmiddle" style="padding-right: 5px;">
							<a href="#" onclick="callReport('Xls', 'Activity'); " title="Click here to generate activity logs"><u>Generate Activity Logs</u></a>							
						</td>
						
						<td width="50%">	
							<img src="../pages/images/buttonIcons/file-xls.png" class="iconImage"
								align="absmiddle" style="padding-right: 5px;">
							<a href="#" onclick="callReport('Xls', 'Snapshot'); " title="Click here to generate BRD snapshot report"><u>BRD snapshot report</u></a>							
						</td>
					</tr>
				</table>
			</td>
		</tr>

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5"><b><label name="selectFilters"
						id="selectFilters" ondblclick="callShowDiv(this);"><%=label.get("selectFilters")%></label></b>:
					</td>
				</tr>
				
				<tr>
					<td width="15%">
						<label name="fromDateLabel" id="fromDateLabel"
						ondblclick="callShowDiv(this);"><%=label.get("fromDateLabel")%></label> : 
					</td>
					<td width="35%">
						<s:textfield size="25" name="fromDate" onkeypress="return numbersWithHiphen();" /> 
						<s:a href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
							<img src="../pages/images/Date.gif" id="ctrlHide"
							class="iconImage" height="16" align="absmiddle" width="16">
						</s:a>
					</td>
					<td width="15%">
						<label name="toDateLabel" id="toDateLabel"
						ondblclick="callShowDiv(this);"><%=label.get("toDateLabel")%></label> :
					</td>
					<td width="35%">
						<s:textfield size="25" name="toDate" onkeypress="return numbersWithHiphen();" /> 
						<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
							<img src="../pages/images/Date.gif" id="ctrlHide"
							class="iconImage" height="16" align="absmiddle" width="16">
						</s:a>
					</td>
				</tr>
				
				 
				<tr>
					<td width="15%"><label class="set" id="trackingNoLabel"
						name="trackingNoLabel"><%=label.get("trackingNoLabel")%></label>:</td>
					<td width="35%">
						<s:textfield name="ticketNumber" readonly="true" size="25" cssStyle="background-color: #F2F2F2;"/>
						<img src="../pages/images/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'BRDMisReport_f9TicketNumber.action');">
					</td>
					<td width="15%"><label class="set" id="statusLabel"
						name="statusLabel"><%=label.get("statusLabel")%></label>:</td>
					<td width="35%">
						<s:select headerKey="-1" headerValue="------------Select--------------" name="applicationStatus" 
							list="#{'D':'Draft', 'F':'Forwarded', 'C':'Closed'}">
						</s:select>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr valign="middle">
						<td nowrap="nowrap" valign="middle" width="10%">
							<a href="#" onclick="callResetFun();">
								<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" title="Reset"> 
									Reset 
							</a>
						</td>
						
						<td nowrap="nowrap" width="20%">
							<img src="../pages/images/buttonIcons/file-xls.png" class="iconImage"
								align="absmiddle" style="padding-right: 5px;">
							<a href="#" onclick="callReport('Xls', 'MIS');" title="Click here to generate MIS reoprt"><u>Generate MIS Report</u></a>
						</td>
						
						<td width="20%">	
							<img src="../pages/images/buttonIcons/file-xls.png" class="iconImage"
								align="absmiddle" style="padding-right: 5px;">
							<a href="#" onclick="callReport('Xls', 'Activity'); " title="Click here to generate activity logs"><u>Generate Activity Logs</u></a>							
						</td>
						
						<td width="50%">	
							<img src="../pages/images/buttonIcons/file-xls.png" class="iconImage"
								align="absmiddle" style="padding-right: 5px;">
							<a href="#" onclick="callReport('Xls', 'Snapshot'); " title="Click here to generate BRD snapshot report"><u>BRD snapshot report</u></a>							
						</td>
					</tr>
				</table>
			</td>
		</tr>

		<s:hidden name="reportType" />
	</table>
</s:form>
<script>
function callReport(type, reportName){
	try {
		var fromDate = trim(document.getElementById('paraFrm_fromDate').value);
		var toDate = trim(document.getElementById('paraFrm_toDate').value);
		if(fromDate != "") {
			if(!validateDate('paraFrm_fromDate','fromDateLabel')){
				return false;
			}
			
			if(toDate == "") {
				alert("Please select/enter "+ document.getElementById('toDateLabel').innerHTML.toLowerCase());
				document.getElementById('paraFrm_toDate').focus();
				return false;
			}
		}
		
		if(toDate != "") {
			if(!validateDate('paraFrm_toDate','toDateLabel')){
				return false;
			}
			
			if(fromDate == "") {
				alert("Please select/enter "+ document.getElementById('fromDateLabel').innerHTML.toLowerCase());
				document.getElementById('paraFrm_fromDate').focus();
				return false;
			}
		}
		
		if(fromDate!="" && toDate!="") {
			if(!dateDifferenceEqual(fromDate,toDate,'paraFrm_toDate','fromDateLabel','toDateLabel')) {
				return false;
			}
		}
		
		document.getElementById('paraFrm_reportType').value=type;
		callReportCommon(type, reportName);
	} catch(e) {
		alert(e);
	}	
}
 
function callReportCommon(type, reportName){
	document.getElementById('paraFrm').target = 'main';
	if(reportName == 'MIS') {
		document.getElementById('paraFrm').action = 'BRDMisReport_getBRDReport.action';
	} else if(reportName == 'Snapshot') { 
		document.getElementById('paraFrm').action = 'BRDMisReport_getBRDSnapshotReport.action';
	} else {
		document.getElementById('paraFrm').action = 'BRDMisReport_getBRDActivityLogsReport.action';
	}
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = '';
}

function callResetFun() {
	document.getElementById('paraFrm').target = 'main';
	document.getElementById('paraFrm').action='BRDMisReport_reset.action';
	document.getElementById('paraFrm').submit();
}
</script>