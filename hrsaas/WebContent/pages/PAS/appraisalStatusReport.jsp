<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="AppraisalStatusReport" validate="true" id="paraFrm"
	theme="simple">
 <s:hidden name="report" />
 <s:hidden name="reportAction" value='AppraisalStatusReport_getReport.action' />
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" >
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Appraisal Phase-wise Status Report
					  </strong></td>
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
					<td nowrap="nowrap"><a href="#" onclick="resetScreen();">
					<img src="../pages/images/buttonIcons/Refresh.png"
						class="iconImage" align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>
				</tr>
			</table>
			</td>
	</tr>
	<tr>
	 <td>		
        <div name="htmlReport" id='reportDiv'
			style="background-color: #FFFFFF; height: 400; width: 100%; display: none; border-top: 1px #cccccc solid;">
		<iframe id="reportFrame" frameborder="0" onload=alertsize();
			style="vertical-align: top; float: left; border: 0px solid;"
			allowtransparency="yes" src="../pages/common/loading.jsp"
			scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
			name="htmlReport" width="100%" height="200"></iframe></div>
	 </td>
	</tr>
	
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr> 
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg" id="reportBodyTable">
				<tr>
					<s:hidden name="startDate" />
					<s:hidden name="endDate" />
					<td colspan="1" width="15%"><label name="appr.code"
						id="appr.code" ondblclick="callShowDiv(this);"><%=label.get("appr.code")%></label><font
						color="red">*</font> :</td>
					<td colspan="1" width="30%"><s:textfield size="30"
						name="apprName" /> <s:hidden name="apprCode" />
						<img src="../pages/images/recruitment/search2.gif" width="16"
								class="iconImage" height="15"
								onclick="javascript:callsF9(500,325,'AppraisalStatusReport_f9AppraisalCode.action');" />
					</td>
					
					<td colspan="1" width="15%">
						<label name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :
					</td>
					<td colspan="1" width="40%">
						<s:textfield name="empCode" size="10" readonly="true" /><s:textfield name="empName" readonly="true" size="25" /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="validateF9('f9Employee');">
						<s:hidden name="empId" />
					</td>
				</tr>
				<tr>
							<td colspan="1" width="15%"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :</td>
							<td colspan="1" width="30%"><s:hidden
								name="divCode" /> <s:textfield
								name="divName" theme="simple" readonly="true"
								maxlength="50" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:validateF9('f9Div');">
							</td>

							<td colspan="1" width="15%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td width="40%"><s:hidden name="branchCode" /> <s:textfield
								name="branchName" theme="simple" readonly="true"
								maxlength="50" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:validateF9('f9Branch');">
							</td>
				</tr>
				<tr>
							<td colspan="1" width="15%"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
							<td colspan="1" width="30%"><s:hidden
								name="deptCode" /> <s:textfield
								name="deptName" theme="simple" readonly="true"
								maxlength="50" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:validateF9('f9Dept');">
							</td>
							<td colspan="1" width="15%"><label  class = "set"  id="designation" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> 
							:</td>
							<td colspan="1" width="40%"><s:hidden name="desgCode" />
									<s:textfield
											name="desgName" theme="simple" readonly="true"
											maxlength="50" size="30" /> <img
											src="../pages/images/recruitment/search2.gif" class="iconImage"
											height="18" align="absmiddle" width="18"
											onclick="javascript:validateF9('f9Desg');"></td>
				</tr>
				<tr>
							<td colspan="1" width="15%"><label  class = "set"  id="phase" name="phase" ondblclick="callShowDiv(this);"><%=label.get("phase")%></label><font
							color="red">*</font> :</td>
							<td colspan="1" width="30%"><s:if test="phaseList"><s:select name="phaseType" list="phaseList" headerKey="" headerValue="--Select--"
				 					size="1" /></s:if><s:else><s:select name="phaseType" list="#{'0':'--Select--'}" 
				 					size="1" /></s:else>
							</td>
							<td colspan="1" width="15%"> <label  class = "set"  id="phase.status" name="phase.status" ondblclick="callShowDiv(this);"><%=label.get("phase.status")%></label> : </td>
							<td colspan="1" width="40%">
							<s:select name="phaseStatus" list="#{'pending':'Pending','completed':'Completed'}" headerKey="all" headerValue="--All--" />
							</select>
							</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- 
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="token"
				 		theme="simple"	value=" Report"
						onclick="return validateReport();" />
						<input type="button" class="reset" theme="simple"
						value=" Reset"
						onclick="return resetScreen();" />
					</td>
				</tr>
			</table>
			</td>
		</tr> -->
	
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td nowrap="nowrap"><a href="#" onclick="resetScreen();">
					<img src="../pages/images/buttonIcons/Refresh.png"
						class="iconImage" align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<div id='bottomButtonTable'></div>
			</td>
		</tr>
	</table>
	</td>
	</tr>
	</table>
</s:form>
<SCRIPT LANGUAGE="JavaScript">
var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);
</SCRIPT>
<script type="text/javascript">
	function validateReport()
	{
		if(document.getElementById('paraFrm_apprCode').value=="")
		{
			alert("Please select "+document.getElementById('appr.code').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_phaseType').value=="")
		{
			alert("Please select "+document.getElementById('phase').innerHTML.toLowerCase());
			return false;
		}
		callReport("AppraisalStatusReport_generateReport.action");
		resetScreen();
		return true;
	}

	function validateF9(action)
	{
		if(document.getElementById("paraFrm_apprCode").value=="")
		{
			alert("Please select "+document.getElementById("appr.code").innerHTML.toLowerCase());
			document.getElementById("paraFrm_apprCode").focus();
			return false;
		}
				
		javascript:callsF9(500,325,'AppraisalStatusReport_'+action+'.action'); 
	}

	function resetScreen()
	{
		document.getElementById('paraFrm_apprCode').value="";
		document.getElementById('paraFrm_apprName').value="";
		document.getElementById('paraFrm_divCode').value="";
		document.getElementById('paraFrm_divName').value="";
		document.getElementById('paraFrm_branchCode').value="";
		document.getElementById('paraFrm_branchName').value="";
		document.getElementById('paraFrm_deptCode').value="";
		document.getElementById('paraFrm_deptName').value="";
		document.getElementById('paraFrm_desgCode').value="";
		document.getElementById('paraFrm_desgName').value="";
		document.getElementById('paraFrm_phaseType').value="";
		document.getElementById('paraFrm_empCode').value="";
		document.getElementById('paraFrm_empName').value="";
		document.getElementById('paraFrm_empId').value="";
		document.getElementById('paraFrm_phaseStatus').value="all";
	}
	
	//Added by Tinshuk Banafar ...Begin....
	
	function callReport(type){	
	try{
		if(document.getElementById('paraFrm_apprCode').value=="")
		{
			alert("Please select "+document.getElementById('appr.code').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_phaseType').value=="")
		{
			alert("Please select "+document.getElementById('phase').innerHTML.toLowerCase());
			return false;
		}
		
	    document.getElementById('paraFrm_report').value=type;
		callReportCommon(type);
		 }
		 catch (e){
	 	alert(e);
	 	}	
	 }
	 
	 function mailReportFun(type){
	 
	 if(document.getElementById('paraFrm_apprCode').value=="")
		{
			alert("Please select "+document.getElementById('appr.code').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_phaseType').value=="")
		{
			alert("Please select "+document.getElementById('phase').innerHTML.toLowerCase());
			return false;
		}
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='AppraisalStatusReport_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
	
	//Added by Tinshuk Banafar ...End....
</script>