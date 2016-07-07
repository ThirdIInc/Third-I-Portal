<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="CeoMessageReport" method="post" id="paraFrm"
	theme="simple">
	<s:hidden name="reportAction" value='CeoMessageReport_ceoReport.action' />
	<s:hidden name="report" />
	<table width="100%" border="0" class="formbg" align="right">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">CEO
					Message Report</strong></td>
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
								onclick="callReset();"> <img
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
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg" id="reportBodyTable">
						<tr>
							<td>
							<table width="100%" border="0" align="right" cellpadding="1"
								cellspacing="5">
								<tr>
									<td width="20%"><label class="set" name="division"
										id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
									<td colspan="1"><s:hidden name="divCode" theme="simple" /><s:textarea
										name="divsion" theme="simple" readonly="true" rows="1"
										cols="100"></s:textarea></td>
									<td width="35%" align="left"><img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callDropdown('paraFrm_divsion',350,250,'CeoMessageReport_f9MultiDiv.action',event,'false','no','right')"></td>
								</tr>
								<tr>
									<td width="20%"><label class="set" name="department"
										id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
									<td colspan="1"><s:hidden name="deptCode" theme="simple" /><s:textarea
										name="deptName" theme="simple" readonly="true" rows="1"
										cols="100"></s:textarea></td>
									<td width="35%" align="left"><img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callDropdown('paraFrm_deptName',350,250,'CeoMessageReport_f9MultiDept.action',event,'false','no','right')"></td>
								</tr>
								<tr>
									<td width="20%"><label class="set" name="branch"
										id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
									<td colspan="1"><s:hidden name="centerId" theme="simple" /><s:textarea
										name="centerName" theme="simple" readonly="true" rows="1"
										cols="100"></s:textarea></td>
									<td width="35%" align="left"><img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callDropdown('paraFrm_centerName',350,250,'CeoMessageReport_f9Brch.action',event,'false','no','right')"></td>
								</tr>

								<tr>
									<td width="25%" colspan="1"><label class="set"
										name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
									<td colspan="1"><s:textfield theme="simple" name="token"
										readonly="true" size="20"></s:textfield><s:hidden
										theme="simple" name="empid" /><s:textfield theme="simple"
										name="empName" readonly="true" size="71"></s:textfield></td>
									<td align="left"><img
										src="../pages/images/recruitment/search2.gif" height="18"  width="18"
										onclick="javascript:callsF9(500,325,'CeoMessageReport_f9action.action');"></td>
								</tr>
								<tr>
									<td><label name="applicationDate" id="applicationDate"
										ondblclick="callShowDiv(this);"> <%=label.get("application.date")%>:</label>
									</td>
									<td align="left">&nbsp;<label name="from.date" id="from.date"
										ondblclick="callShowDiv(this);">
									<%=label.get("from.date")%>:</label><s:textfield size="10" name="fromDate" maxlength="10"
											onkeypress="return numbersWithHiphen();" onblur="return validateDate('paraFrm_fromDate', 'from.date')"></s:textfield>
									<s:a href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
										<img class="iconImage" class="iconImage"
											src="../pages/images/recruitment/Date.gif" class="iconImage"
											height="16" width="16">
									</s:a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label name="to.date" id="to.date"
										ondblclick="callShowDiv(this);"><%=label.get("to.date") %></label>:<s:textfield size="10" name="toDate" maxlength="10"
											onkeypress="return numbersWithHiphen();" onblur="return validateDate('paraFrm_toDate', 'to.date')"></s:textfield><s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
										&nbsp;<img class="iconImage" class="iconImage"
											src="../pages/images/recruitment/Date.gif" class="iconImage"
											height="16" width="16" >
									</s:a>
									
									</td>
								</tr>
								
								<tr>
								<td width="70%">
								<label class="set" name="anonymous.employees" id="anonymous.employees"
										ondblclick="callShowDiv(this);" ><%=label.get("anonymous.employees")%></label>
									:</td><td><s:checkbox name="hiddenIdentity" id="hiddenIdentity" />
								</td>
								</tr>
								<tr>

									<td><label name="status" id="status"
										ondblclick="callShowDiv(this);"><%=label.get("application.status")%></label>:</td>
									<td><s:select name="status" headerKey="-1"
										headerValue="---Select----"
										list="#{'A':'Approved','R':'Rejected','P':'Pending'}"></s:select></td>
									

								</tr>
								<tr>
									<td><label name="empStatus" id="empStatus"
										ondblclick="callShowDiv(this);"><%=label.get("employee.status") %></label></td>
									<td colspan="2"><s:select name="empStatus" headerKey="-1"
										headerValue="--Select--"
										list="#{'S':'Service','R':'Retired','N':'Resigned','E':'Terminated','A':'Absconding'}"></s:select></td>
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

	function callMisReport(){
  	document.getElementById('paraFrm').target='_blank';
	document.getElementById('paraFrm').action='CeoMessageReport_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
	}
	function callReset(){
  	document.getElementById('paraFrm').target='_self';
	document.getElementById('paraFrm').action='CeoMessageReport_clear.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="_self";
	}
	
	function callReport(type){
	document.getElementById('paraFrm_report').value=type;
	var fromdate=document.getElementById('paraFrm_fromDate').value;
	var todate=document.getElementById('paraFrm_toDate').value
	if(fromdate!=="" && todate!=="")
	{
	if(!(dateDifferenceEqual(fromdate ,todate ,'paraFrm_toDate','from.date','to.date')))
	return false;
	}
	callReportCommon(type);		
 	}
 	
 	function mailReportFun(type)
 	{
  		document.getElementById('paraFrm_report').value=type;	
  		
  		var fromdate=document.getElementById('paraFrm_fromDate').value;
	var todate=document.getElementById('paraFrm_toDate').value
	if(fromdate!=="" && todate!=="")
	{
	if(!(dateDifferenceEqual(fromdate ,todate ,'paraFrm_toDate','from.date','to.date')))
	return false;
	}	
		//callDropdown(obj.name,200,250,'MonthlyEDSummaryReport_mailReport.action','false');
		document.getElementById('paraFrm').action='CeoMessageReport_mailReport.action';
		document.getElementById('paraFrm').submit();
		//return true;
	}

</script>

<script language="JavaScript">
  var obj= document.getElementById("topButtonTable").cloneNode(true);
  document.getElementById("topButtonTable").appendChild(obj);
</script>




