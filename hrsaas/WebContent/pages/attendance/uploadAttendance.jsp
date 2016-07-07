<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<%@ page import="java.util.HashMap;"%>

<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />

<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="UploadAttendance" validate="true" id="paraFrm" target="main" theme="simple">
	<s:hidden name="empIdFlag" /><s:hidden name="empNameFlag" /><s:hidden name="dateFlag" /><s:hidden name="inTimeFlag" />
	<s:hidden name="outTimeFlag" /><s:hidden name="oneTimeFlag" /><s:hidden name="workHrsFlag" /><s:hidden name="ioFlag" />
	<s:hidden name="uploadAttCode" />

	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td valign="bottom" class="txt">
							<strong class="text_head"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Upload Attendance</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr>
						<td width="22%">
							<div align="right"><font color="red">*</font> Indicates Required</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td colspan="5" class="formhead">
							<strong class="forminnerhead">
								<label id="upAttend" name="upAttend" ondblclick="callShowDiv(this);"><%=label.get("upAttend")%></label>
							</strong>
						</td>
					</tr>
					<tr>
						<td width="15%">
							<label class="set" id="frmdate" name="frmdate" ondblclick="callShowDiv(this);"><%=label.get("frmdate")%></label> :<font color="red" size="2">*</font>
						</td>
						<td width="25%">
							<s:textfield size="12" name="fromDate" onkeypress="return numbersWithHiphen();" maxlength="10" theme="simple" />
							
							<s:a href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16">
							</s:a>
						</td>
						<td width="10%"></td>
						<td width="15%">
							<label class="set" id="toDate" name="toDate" ondblclick="callShowDiv(this);"><%=label.get("toDate")%></label> :<font color="red" size="2">*</font>
						</td>
						<td width="25%">
							<s:textfield size="12" name="toDate" onkeypress="return numbersWithHiphen();" maxlength="10" theme="simple" />
							
							<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16">
							</s:a>
						</td>
						<td></td>
					</tr>
					<tr>
						<td width="15%">
							<label class="set" id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font color="red" size="2">*</font>
						</td>
						<td width="25%">
							<s:hidden name="divCode" />
							
							<s:textfield size="40" name="divName" readonly="true" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">	
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="18" theme="simple" 
							onclick="javascript:callsF9(500,325,'UploadAttendance_f9DivisionAction.action'); ">
						</td>
						<td width="15%">
							<label class="set" id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :<font color="red" size="2">*</font>
						</td>
						<td width="25%">
							<s:hidden name="branchCode" />
							
							<s:textfield size="40" name="branchName" readonly="true" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td>	
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="18" theme="simple" 
							onclick="javascript:callsF9(500,325,'UploadAttendance_f9BranchAction.action'); ">
						</td>
					</tr>
					<tr>
						<td width="15%">
							<label class="set" id="file" name="file" ondblclick="callShowDiv(this);"><%=label.get("file")%></label> :
						</td>
						<td colspan="6" nowrap="nowrap">
							<s:textfield name="uploadFileName" size="50" readonly="true" cssStyle="background-color: #F2F2F2;" />&nbsp;&nbsp;&nbsp;
							
							<input type="button" class="token" theme="simple" value="Select File" onclick="uploadFile('uploadFileName');" />
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="6" align="center">
							<s:submit cssClass="token" action="UploadAttendance_uploadDataManually" theme="simple" value="Upload Data" 
							onclick="return uploadDataManually();" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td colspan="5" class="formhead">
							<strong class="forminnerhead">
								<label id="autoUpAttend" name="autoUpAttend" ondblclick="callShowDiv(this);"><%=label.get("autoUpAttend")%></label>
							</strong>
						</td>
					</tr>
					<tr>
						<td width="15%">
							<label class="set" id="frmdate" name="frmdate" ondblclick="callShowDiv(this);"><%=label.get("frmdate")%></label> :<font color="red" size="2">*</font>
						</td>
						<td width="35%">
							<s:textfield size="12" name="fromDateAuto" onkeypress="return numbersWithHiphen();" maxlength="10" theme="simple" />
							
							<s:a href="javascript:NewCal('paraFrm_fromDateAuto','DDMMYYYY');">
								<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16">
							</s:a>
						</td>
						<td width="15%">
							<label class="set" id="toDate" name="toDate" ondblclick="callShowDiv(this);"><%=label.get("toDate")%></label> :<font color="red" size="2">*</font>
						</td>
						<td>
							<s:textfield size="12" name="toDateAuto" onkeypress="return numbersWithHiphen();" maxlength="10" theme="simple" />
							
							<s:a href="javascript:NewCal('paraFrm_toDateAuto','DDMMYYYY');">
								<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16">
							</s:a>
						</td>
					</tr>
					<tr>
						<td width="15%">
							<label id="lbServer" name="lbServer" ondblclick="callShowDiv(this);"><%=label.get("lbServer")%></label> :<font color="red" size="2">*</font>
						</td>
						<td width="25%">
							<s:select name="server" headerKey="" headerValue="--Select--" list="serverList" />
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="4" align="center">
							<s:submit cssClass="token" action="UploadAttendance_uploadDataAutomatically" theme="simple" value="Fetch Data" 
							onclick="return uploadDataAutomatically();" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>
	function uploadFile(fieldName) {
		var path = "images/<%=session.getAttribute("session_pool")%>/attendance";
		window.open('<%=request.getContextPath()%>/pages/common/uploadFile.jsp?path=' + path + '&field=' + fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	} 
	
	function uploadDataManually() {
		var fieldNames = ["paraFrm_fromDate", "paraFrm_toDate", "paraFrm_divName", "paraFrm_branchName", "paraFrm_uploadFileName"];
		var labelNames = ["frmdate", "toDate", "division", "branch", "file"];
		var flag = ["enter", "enter", "select", "select", "select"];

		var fromDate = document.getElementById('paraFrm_fromDate').value;
		var toDate = document.getElementById('paraFrm_toDate').value;
			
		if(!validateBlank(fieldNames, labelNames, flag)) return false;
		if(!validateDate("paraFrm_fromDate", 'frmdate')) return false;
		if(!validateDate("paraFrm_toDate", 'toDate')) return false;
		if(!dateDifferenceEqual(fromDate, toDate, "paraFrm_toDate", 'frmdate', 'toDate')) return false;
	}
	
	function uploadDataAutomatically() {
		var fieldNames = ['paraFrm_fromDateAuto', 'paraFrm_toDateAuto', 'paraFrm_server'];
		var labelNames = ["frmdate", "toDate", "lbServer"];
		var flag = ["enter", "enter", "select"];

		var fromDate = document.getElementById('paraFrm_fromDateAuto').value;
		var toDate = document.getElementById('paraFrm_toDateAuto').value;
				
		if(!validateBlank(fieldNames, labelNames, flag)) return false;
		if(!validateDate("paraFrm_fromDateAuto", 'frmdate')) return false;
		if(!validateDate("paraFrm_toDateAuto", 'toDate')) return false;
		if(!dateDifferenceEqual(fromDate, toDate, "paraFrm_toDateAuto", 'frmdate', 'toDate')) return false;
	}
</script>