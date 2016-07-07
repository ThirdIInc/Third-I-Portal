<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="AwardMisReport" method="post" id="paraFrm"
	theme="simple">
	<s:hidden name="reportAction"
		value='AwardMisReport_awardReport.action' />
	<s:hidden name="report" />
	<table width="100%" border="0" class="formbg" align="right">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" class="formbg" cellpadding="0"
				cellspacing="0" align="right">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Award
					MIS Report </strong></td>
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
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td nowrap="nowrap" width="5%"><a href="#"
								onclick="callReset()();"> <img
								src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
								align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
							<td width="100%" colspan="6"><%@ include
								file="/pages/common/reportButtonPanel.jsp"%></td>
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
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="2"
						class="formbg" id="reportBodyTable">
						<tr>
							<td>
							<table width="100%" border="0" align="left" cellpadding="1"
								cellspacing="1">
								<tr>
									<td width="20%"><label class="set" name="division" id="division"
										ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
									<td colspan="2"><s:hidden name="divCode"
										theme="simple" /><s:textarea name="division" theme="simple"
										readonly="true" rows="1" cols="100"></s:textarea></td>
									<td width="35%"><img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="18"
										onclick="javascript:callDropdown('paraFrm_division',350,250,'AwardMisReport_f9MultiDiv.action',event,'false','no','right')"></td>
								</tr>
								<tr>
									<td width="20%"><label class="set" name="department" id="department"
										ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
									<td colspan="2"><s:hidden name="deptCode"
										theme="simple" /><s:textarea name="deptName" theme="simple"
										readonly="true" rows="1" cols="100"></s:textarea></td>
										<td width="35%"><img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="18"
										onclick="javascript:callDropdown('paraFrm_deptName',350,250,'AwardMisReport_f9MultiDept.action',event,'false','no','right')"></td>
								</tr>
								<tr>
									<td width="20%"><label class="set" name="branch" id="branch"
										ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
									<td colspan="2"><s:hidden name="centerId"
										theme="simple" /><s:textarea name="centerName" theme="simple"
										readonly="true" rows="1" cols="100"></s:textarea></td>
										<td width="35%"><img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="18"
										onclick="javascript:callDropdown('paraFrm_centerName',350,250,'AwardMisReport_f9Brch.action',event,'false','no','right')"></td>
								</tr>
								<tr>
									<td width="20%"><label class="set" name="designation" id="designation"
										ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
									<td colspan="2"><s:hidden name="desgCode"
										theme="simple" /><s:textarea name="desgName" theme="simple"
										readonly="true" rows="1" cols="100"></s:textarea></td>
									<td width="35%"><img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="18"
										onclick="javascript:callDropdown('paraFrm_desgName',350,250,'AwardMisReport_f9MultiRank.action',event,'false','no','right')"></td>
								</tr>
								<tr>
									<td width="20%" colspan="1"><label class="set"
										name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
									<td colspan="3"><s:textfield theme="simple"
										name="token" readonly="true" size="22"></s:textfield><s:hidden
										theme="simple" name="empid" /><s:textfield theme="simple"
										name="empName" readonly="true" size="70"></s:textfield>
										<img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'AwardMisReport_f9action.action');"></td>
								</tr>
								<tr>
									<td width="20%"><label name="status" id="status"
										ondblclick="callShowDiv(this);"><%=label.get("status")%></label>:</td>
									<td colspan="3"><s:select name="status" headerKey="-1"
										headerValue="--Select--"
										list="#{'S':'Service','R':'Retired','N':'Resigned','E':'Terminated'}"></s:select></td>
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
		
		<tr>
			<td>
			<div id="bottomButtonTable"></div>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td nowrap="nowrap" width="4%"><a href="#"
						onclick="callReset();"> <img
						src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
						align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
					<td width="100%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">

 function callReset(){
  	document.getElementById('paraFrm').target='_self';
	document.getElementById('paraFrm').action='AwardMisReport_clear.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="_self";
	}
	function callReport(type){
	document.getElementById('paraFrm_report').value=type;
	callReportCommon(type);		
 	}
 	
 	function mailReportFun(type)
 	{
  		document.getElementById('paraFrm_report').value=type;		
		//callDropdown(obj.name,200,250,'MonthlyEDSummaryReport_mailReport.action','false');
		document.getElementById('paraFrm').action='AwardMisReport_mailReport.action';
		document.getElementById('paraFrm').submit();
		//return true;
	}
</script>
<script language="javascript">
  var obj= document.getElementById("topButtonTable").cloneNode(true);
  document.getElementById("topButtonTable").appendChild(obj);
</script>
