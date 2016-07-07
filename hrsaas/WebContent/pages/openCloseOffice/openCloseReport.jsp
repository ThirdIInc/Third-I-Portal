<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>

<s:form action="OpenCloseReport" validate="true" id="paraFrm" target="main"
	theme="simple">
	
	<table width="100%" border="0" align="right" cellpadding="1"
		cellspacing="2" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="93%" class="txt"><strong
							class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong><strong class="text_head">Open Close Office Report</strong></td>
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
					<table width="100%" border="0" cellpadding="0" cellspacing="0"><!-- table 2 -->
					<tr>
						<td>
			  				<s:submit cssClass="token" action="OpenCloseReport_report"
						theme="simple" value=" Generate Report" onclick="return validateReport();" />
						</td>
						<td>
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
						</td>
					</tr>	
					</table><!--end of table 2  -->
				</td>
			</tr>
				<tr>
					<td colspan="3">
						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="2" class="formbg">
							<tr>
								<td width="10%" colspan="1" class="formtext"><label  class = "set"  id="branch" 
								name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label><font color="red">*</font> :</td>
									<s:hidden name="branchId"></s:hidden>
								<td width="90%" colspan="3"><s:textfield
									name="branchName" size="35" value="%{branchName}" theme="simple"
									readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="17"
									onclick="javascript:callsF9(500,325,'OpenCloseReport_f9branch.action');">
								</td>
							</tr>
							
							<tr>
									<td colspan="1" width="10%"><label class="set"
										id="fDate" name="fDate"
										ondblclick="callShowDiv(this);"><%=label.get("fDate")%></label></label><font
										color="red">*</font>:</td>
									<td width="30%"><s:textfield name="fromDate" size="25"
											onblur="validateDate('paraFrm_fromDate','Signature Date')" />
										<s:a href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
											<img src="../pages/images/Date.gif"
												class="iconImage" height="16" align="absmiddle" width="16">
										</s:a>
									</td>
									<td colspan="1" width="10%"><label class="set"
										id="tDate" name="tDate"
										ondblclick="callShowDiv(this);"><%=label.get("tDate")%></label><font
										color="red">*</font>:</td>
									<td><s:textfield name="toDate" size="25"
											onblur="validateDate('paraFrm_toDate','Signature Date')" />
										<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
											<img src="../pages/images/Date.gif"
												class="iconImage" height="16" align="absmiddle" width="16">
										</s:a>
									</td>
								</tr>
							
							<tr>
								<td colspan="4"><label class="set"
										id="exceptionLabel" 
										ondblclick="callShowDiv(this);">Show exception where office is :</label> <s:select name="openCloseFilter" list="#{'O':'Opened','C':'Closed'}" />
								<s:select name="beforeAfterFilter" list="#{'B':'Before','A':'After'}" />
								<s:textfield name="exceptionTime" size="3" value="%{exceptionTime}" theme="simple"/>(HH24:MI) 
								</td>
							</tr>	
							
							<tr>
								<td width="15%"><label id="report.type" name="report.type"
									ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> :</td>
								<td><s:select name="reportType" list="#{'Pdf':'Pdf'}" /></td>
							</tr>
							
						</table>
					</td>
				</tr>		
	</table>
</s:form>	
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
	function validateReport(){
	
	var frmDate = document.getElementById('paraFrm_fromDate').value;
	var toDate = document.getElementById('paraFrm_toDate').value;
	
	if(document.getElementById('paraFrm_branchId').value == ""){
		alert("Please select branch");
		return false;
	}
	if(document.getElementById('paraFrm_fromDate').value == ""){
		alert("Please select from from date");
		return false;
	}
	if(document.getElementById('paraFrm_toDate').value == ""){
		alert("Please select to date");
		return false;
	}
	/*
	if(!dateDifference(frmDate, toDate, "paraFrm_toDate", "fDate", "tDate")){
		return false;
	}
	*/
	
	if(!validateTime("paraFrm_exceptionTime", "exceptionLabel")){
		return false;
	}
	
	return true;
	}
</script>