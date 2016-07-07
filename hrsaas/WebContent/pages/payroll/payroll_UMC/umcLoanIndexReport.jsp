<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="UmcLoanIndexReport" method="post" id="paraFrm">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Loan
					Index Report </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="70%" colspan="3"><input type="button"
								class="token" onclick="return callReport();"
								value="    Show Report" /> <input type="button" class="reset"
								onclick="return callReset();" theme="simple" value="    Reset"
								 /></td>
							<td width="30%">
							<div align="right"><span class="style2"></span></div>
							</td>
						</tr>
					</table>
					</td>
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
									<td width="25%" colspan="1" class="formtext"><label
										class="set" id="employee" name="employee"
										ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
									:<s:hidden name="empCode" value="%{empCode}" />
									<s:hidden name="emiAmt" />
									<s:hidden name="pendingAmt" />
									<s:hidden name="lastPaidMonth" />
									<s:hidden name="lastPaidYear" />
									</td>
									<td width="75%" colspan="3"><s:textfield name="empToken"
										size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
										name="empname" size="50" value="%{empname}" theme="simple"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'UmcLoanIndexReport_f9actionLoanAppl.action');">
									</td>
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
<script type="text/javascript">

	function callReport(){
		var employee=document.getElementById('employee').innerHTML.toLowerCase();
		var emp=document.getElementById("paraFrm_empname").value
  		if(emp==""){
  			alert("Please select "+employee);
  			return false;
  		}
  	
  		document.getElementById('paraFrm').target = "_blank";
  		document.getElementById('paraFrm').action = "UmcLoanIndexReport_report.action";
  		document.getElementById('paraFrm').submit();
  		document.getElementById('paraFrm').target = "main";
   	
	
	}
	
	function callReset(){
		document.getElementById("paraFrm_empToken").value="";
		document.getElementById("paraFrm_empname").value="";
		document.getElementById("paraFrm_empCode").value="";
	}
  	
</script>