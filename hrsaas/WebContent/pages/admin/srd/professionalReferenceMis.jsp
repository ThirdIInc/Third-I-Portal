<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>


<s:form action="ProfessionalReferenceMis" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Professional
					References MIS </strong></td>
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
								class="token" onclick="return callMisReport();"
								value="  View Report"  /> <s:submit
								cssClass="reset" action="ProfessionalReferenceMis_reset" theme="simple"
								value="    Reset" /></td>
						</tr>
					</table>
					<label></label></td>
				</tr>



				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2" >


								<tr>
									<td colspan="1" width="20%"><label class="set"
										name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
									:</td>
									<td colspan="3" width="80%"><s:textfield theme="simple"
										name="empToken" size="25" readonly="true" /><s:textfield
										theme="simple" name="empName" size="75"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'ProfessionalReferenceMis_f9action.action');">
									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
									:</td>
									<td colspan="1" width="30%"><s:hidden name="divCode" /> <s:textfield
										name="divisionName" theme="simple" size="25" readonly="true" />
									<img src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'ProfessionalReferenceMis_f9div.action');">
									</td>
									<td colspan="1" width="20%"><label class="set"
										name="department" id="department"
										ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
									:</td>
									<td colspan="1" width="30%"><s:hidden
										name="deptCode" /> <s:textfield
										name="deptName" theme="simple" size="25"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'ProfessionalReferenceMis_f9dept.action');">
									</td>

								</tr>

								<tr>
									<td colspan="1" width="20%"><label class="set"
										name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
									:</td>
									<td colspan="1" width="30%"><s:hidden
										name="centerNo" /> <s:textfield
										name="centerName" theme="simple" size="25"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'ProfessionalReferenceMis_f9center.action');">

									</td>
									<td colspan="1" width="20%"><label class="set"
										name="designation" id="designation"
										ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
									:</td>
									<td colspan="1" width="30%"><s:hidden
										name="desgCode" /> <s:textfield
										name="desgName" theme="simple" size="25"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'ProfessionalReferenceMis_f9desg.action');">
									</td>

								</tr>

								<tr>
									<td colspan="1" width="20%"><label class="set"
										name="empStatus" id="empStatus"
										ondblclick="callShowDiv(this);"><%=label.get("empStatus")%></label>
									:</td>
									<td colspan="1" width="30%"><s:select name="employeeStatus"
								list="#{'-1':'Select','S':'Service','R':'Retired','N':'Resigned','E':'Terminated'}" />
								</td>
										<td colspan="1" width="20%"> </td>
									<td colspan="1" width="30%">
									
								 
									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										name="report.type" id="report.type"
										ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
									:</td>
									<td colspan="3" width="80%"><s:select theme="simple"
										name="reportType" headerKey="Pdf"
										headerValue="Pdf" list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>
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

	<s:hidden name="employeeId" />
</s:form>
<script type="text/javascript">

	function callMisReport(){
  	document.getElementById('paraFrm').target='_blank';
	document.getElementById('paraFrm').action='ProfessionalReferenceMis_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
	}
	

</script>
