<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TaskReport" method="post" id="paraFrm">
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="formhead">Task Report</strong></td>
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
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%" colspan="1"><input type="button"
								class="token" value=" Report " onclick="return callReport();"
								 /> <s:submit cssClass="reset"
								action="TaskReport_clear" theme="simple" value="    Reset" /></td>

						</tr>
					</table></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<td colspan="1" width="15%"><label  class = "set"  id="task.type" name="task.type" ondblclick="callShowDiv(this);"><%=label.get("task.type")%></label>:</td>
									<td colspan="1" width="25%" nowrap="nowrap"><s:select
										name="taskType" cssStyle="width:200" headerKey=""
										headerValue="--- Select Rating---" size="1" list="tmap"
										theme="simple" /></td>
									<td colspan="1" width="15%"><label  class = "set"  id="task.project" name="task.project" ondblclick="callShowDiv(this);"><%=label.get("task.project")%></label> :</td>
									<td colspan="1" width="25%" nowrap="nowrap"><s:select
										name="taskProject" cssStyle="width:200" headerKey=""
										headerValue="--- Select Rating---" size="1" list="tmap1"
										theme="simple" /></td>
								</tr>
								<tr>
									<td colspan="1" width="15%"><label  class = "set"  id="fromDate" name="fromDate" ondblclick="callShowDiv(this);"><%=label.get("fromDate")%></label> :</td>
									<td colspan="1" width="25%"><s:textfield theme="simple"
										name="appFromDate" maxlength="10" size="25" onkeypress="numbersWithHiphen();" 
										onblur="return validateDate('paraFrm_appFromDate', 'fromDate')"/> <s:a
										href="javascript:NewCal('paraFrm_appFromDate','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" height="16" align="absmiddle" width="16">
									</s:a></td>
									<td colspan="1" width="15%"><label  class = "set"  id="toDate" name="toDate" ondblclick="callShowDiv(this);"><%=label.get("toDate")%></label> :</td>
									<td colspan="1" width="25%"><s:textfield theme="simple"
										name="appToDate" maxlength="10" size="25" onkeypress="numbersWithHiphen();" 
										onblur="return validateDate('paraFrm_appToDate', 'toDate')"/> <s:a
										href="javascript:NewCal('paraFrm_appToDate','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" height="16" align="absmiddle" width="16">
									</s:a></td>
								</tr>
								<tr>
									<td colspan="1" width="15%"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :</td>
									<s:hidden name="taskReport.empToken" />
									<s:hidden name="taskReport.empCode" />
									<td colspan="1" width="25%"><s:textfield theme="simple"
										name="taskReport.empName" size="25" readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'TaskReport_f9action.action');">
									</td>
								</tr>
								<tr>
									<td colspan="1" width="15%"><label  class = "set"  id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> :</td>
									<td><s:select theme="simple" name="reportType"
										headerKey="Xls" headerValue="Xls"
										list="#{'Pdf':'Pdf', 'Txt':'Text'}" /></td>
								</tr>
							
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</td>
</tr>
</table>
</s:form>

<script>

	function callReport(){
	var appFromDate= document.getElementById('paraFrm_appFromDate').value
	var appToDate= document.getElementById('paraFrm_appToDate').value
	if(appFromDate!==""){
			if(!(validateDate('paraFrm_appFromDate', 'fromDate'))){
				return false;
			}
			}
			
     if(appToDate!==""){
			if(!(validateDate('paraFrm_appToDate', 'toDate'))){
				return false;
			}
			}
			
			
			if(appFromDate!=="" && appToDate!="" ){
			if (!dateDifferenceEqual(appFromDate, appToDate , 'paraFrm_appToDate','paraFrm_appFromDate','toDate' )){
				return false;
			}
		} 	
  	document.getElementById('paraFrm').target='_blank';	 
	document.getElementById('paraFrm').action='TaskReport_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
	} 
</script>
