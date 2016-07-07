<!--Shashikant DOke--><!--May 11, 2010-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>
<s:form action="PayBillwiseBankReport" name="PayBillwiseBankReport" validate="true" id="paraFrm" target="main" theme="simple">

	<table width="100%" class="formbg" align="right">
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">Paybill wise Bank Summary Report</strong></td>
						<td width="4%" valign="middle" align="right" class="txt">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>							
							<s:submit action="PayBillwiseBankReport_report" value="Report" cssClass="token" title="Generate Report" onclick="return callReportFun();"/>
							<s:submit action="PayBillwiseBankReport_reset" value="Reset" cssClass="reset" 
								title="Clear the fields" />
						</td>
						<td align="right"><font color="red">*</font> Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		

		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
							<s:hidden name="onHoldEmp"/>							
							<s:hidden name="statusEmpCode"/>
							<s:hidden name="statusEmpName"/>
							
						<td width="100%">
							<table width="100%">
								<tr>
									<td width="300" align="right">
										<label id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> :<font color="red">*</font>
									</td>
									<td width="50">
										<s:select name="month" headerKey="0" headerValue="--Select--" title="Select a month"
										list="#{'0':'--Select--','1':'January', '2':'Febuary', '3':'March', '4':'April', '5':'May', '6':'June', '7':'July', 
										'8':'August', '9':'September', '10':'October', '11':'November', '12':'December'}" />
									</td>
									<td width="60" align="right">
										<label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> :<font color="red">*</font>
									</td>
									<td colspan="1">
									<s:textfield name="year" size="5" maxlength="4" cssStyle="text-align: right" title="Enter the year"
										onkeypress="return numbersOnly(event);" onblur="return checkYear('paraFrm_year', 'year');" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%">
							<table width="100%">							
									<tr>
										<td width="300" align="right">
											<label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font color="red">*</font>
										</td>										
										<td width="20">
											<s:hidden name="divisionId" />
											<s:textfield name="divisionName" readonly="true" size="35" 
											cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the division" 
											onclick="callsF9(500,325,'PayBillwiseBankReport_f9Division.action');">
										</td>									
									</tr>										
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>							
							<s:submit action="PayBillwiseBankReport_report"  onclick="return callReportFun();" value="Report" cssClass="token" title="Generate Report" />
							<s:submit action="PayBillwiseBankReport_reset" value="Reset" cssClass="reset" 
								title="Clear the fields"  />
						</td>
						<td align="right"></td>
					</tr>
				</table>
			</td>
		</tr>

</table>
</s:form>
		
<script>
function callReportFun(){
		if(document.getElementById('paraFrm_month').value=='0'){
		alert('Please select month');
		return false;
		}
		if(document.getElementById('paraFrm_year').value==''){
		alert('Please enter year');
		return false;
		}
	if(document.getElementById('paraFrm_divisionId').value==''){
		alert('Please select division');
		return false;
		}
		//divisionId
}
</script>		