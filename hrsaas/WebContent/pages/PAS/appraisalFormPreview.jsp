<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="AppraisalFormPreview" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="reportAction" value='AppraisalFormPreview_genReport.action'/>
<s:hidden name="report" />
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Appraisal Form Preview Report
					  </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		 <tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<s:hidden name="startDate" />
					<s:hidden name="endDate" />
					<s:hidden name="empDiv" />
					<s:hidden name="empBrn" />
					<s:hidden name="empDept" />
					<s:hidden name="empDesg" />
					<s:hidden name="empToken" />
					<td colspan="1" width="15%"><label name="appr.code"
						id="appr.code" ondblclick="callShowDiv(this);"><%=label.get("appr.code")%></label><font
						color="red">*</font> :</td>
					<td colspan="1" width="85%"><s:textfield size="40"
						name="apprName" readonly="true"/> <s:hidden name="apprCode" />
						<img src="../pages/images/recruitment/search2.gif" width="16"
								class="iconImage" height="15"
								onclick="javascript:callsF9(500,325,'AppraisalFormPreview_f9AppraisalCode.action');" />
						</td>
				</tr>
				<tr>
					<td colspan="1" width="15%"><label name="temp.code"
						id="temp.code" ondblclick="callShowDiv(this);"><%=label.get("temp.code")%></label><font
						color="red">*</font> :</td>
					<td colspan="1" width="85%"><s:textfield size="40"
						name="tempName" readonly="true"/> <s:hidden name="tempCode" />
						<img src="../pages/images/recruitment/search2.gif" width="16"
								class="iconImage" height="15"
								onclick="javascript:validateTemplate();" />
						</td>
				</tr>
				
				<tr>
					<td colspan="1" width="15%"><label name="group"
						id="group" ondblclick="callShowDiv(this);"><%=label.get("group")%></label> :</td>
					<td colspan="1" width="85%"><s:textfield size="40" readonly="true"
						name="groupName" /> <s:hidden name="groupCode" />
						<img src="../pages/images/recruitment/search2.gif" width="16"
								class="iconImage" height="15"
								onclick="javascript:validateGroup();" />
						</td>
				</tr>
				
				<tr>
					<td colspan="1" width="15%"><label name="employee"
						id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :</td>
					<td colspan="1" width="85%"><s:textfield size="40" readonly="true"
						name="empName" /> <s:hidden name="empCode" />
						<img src="../pages/images/recruitment/search2.gif" width="16"
								class="iconImage" height="15"
								onclick="javascript:validateEmp();" />
						</td>
				</tr>
			</table>
			</td>
		</tr>
		 <tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		
		</tr>
		<tr>
			<td>
		<div name="htmlReport" id='reportDiv'
			style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
		<iframe id="reportFrame" frameborder="0" onload=alertsize();
			style="vertical-align: top; float: left; border: 0px solid;"
			allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
			marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
			width="100%" height="200"></iframe> </div>
		</td>
		</tr>
	</table>
</s:form>
<script>
function validateTemplate()
{
	if(document.getElementById('paraFrm_apprCode').value=="")
	{
		alert("Please select "+document.getElementById('appr.code').innerHTML.toLowerCase());
		return false;
	}
	callsF9(500,325,'AppraisalFormPreview_f9TemplateCode.action');
}

function validateGroup()
{
	if(document.getElementById('paraFrm_tempCode').value=="")
	{
		alert("Please select "+document.getElementById('temp.code').innerHTML.toLowerCase());
		return false;
	}
	callsF9(500,325,'AppraisalFormPreview_f9Group.action');
}

function validateEmp()
{
	if(document.getElementById('paraFrm_groupCode').value=="")
	{
		alert("Please select "+document.getElementById('group').innerHTML.toLowerCase());
		return false;
	}
	callsF9(500,325,'AppraisalFormPreview_f9Employee.action');
}

function validateReport()
{
	if(document.getElementById('paraFrm_apprCode').value=="")
	{
		alert("Please select "+document.getElementById('appr.code').innerHTML.toLowerCase());
		return false;
	}
	if(document.getElementById('paraFrm_tempCode').value=="")
	{
		alert("Please select "+document.getElementById('temp.code').innerHTML.toLowerCase());
		return false;
	}
	callReport("AppraisalFormPreview_previewReport.action");
	
	/* Reset the data after report generate */
	resetScreen()
	
	return true;
}

function resetScreen()
{
	document.getElementById('paraFrm_apprCode').value="";
	document.getElementById('paraFrm_apprName').value="";
	document.getElementById('paraFrm_tempCode').value="";
	document.getElementById('paraFrm_tempName').value="";
	document.getElementById('paraFrm_groupCode').value="";
	document.getElementById('paraFrm_groupName').value="";
	document.getElementById('paraFrm_empCode').value="";
	document.getElementById('paraFrm_empName').value="";
}

function callReport(type){

	if(document.getElementById('paraFrm_apprCode').value=="")
	{
		alert("Please select "+document.getElementById('appr.code').innerHTML.toLowerCase());
		return false;
	}
	if(document.getElementById('paraFrm_tempCode').value=="")
	{
		alert("Please select "+document.getElementById('temp.code').innerHTML.toLowerCase());
		return false;
	}
		document.getElementById('paraFrm_report').value=type; 
		callReportCommon(type);		
}

function mailReportFun(type){
if(document.getElementById('paraFrm_apprCode').value=="")
	{
		alert("Please select "+document.getElementById('appr.code').innerHTML.toLowerCase());
		return false;
	}
	if(document.getElementById('paraFrm_tempCode').value=="")
	{
		alert("Please select "+document.getElementById('temp.code').innerHTML.toLowerCase());
		return false;
	}
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='AppraisalFormPreview_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
	
function callReset(){
	
	document.getElementById('paraFrm_apprCode').value="";
	document.getElementById('paraFrm_apprName').value="";
	document.getElementById('paraFrm_tempCode').value="";
	document.getElementById('paraFrm_tempName').value="";
	document.getElementById('paraFrm_groupCode').value="";
	document.getElementById('paraFrm_groupName').value="";
	document.getElementById('paraFrm_empCode').value="";
	document.getElementById('paraFrm_empName').value="";
	
}
</script>