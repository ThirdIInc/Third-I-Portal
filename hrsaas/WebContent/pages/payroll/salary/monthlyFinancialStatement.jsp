<!--Bhushan Dasare--><!--June 6, 2009-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp" %>

<s:form action="MonthlyFinancialStatement" name="MonthlyFinancialStatement" id="paraFrm" validate="true" target="main" theme="simple">	
    <s:hidden name="recoveryFlag" /> 
	 <s:hidden name="resignAcceptDate" /> 
	  <s:hidden name="resignSeparationDate" /> 
	
	<s:hidden name="divisionCode" /> 
	<s:hidden name="branchCode" /> 
	<s:hidden name="fromdate" /> 
	<s:hidden name="todate" /> 
	<s:hidden name="branchName" /> 
	
	
	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">Monthly Financial Statement  </strong>
						<s:hidden name="hBranchCode" /><s:hidden name="statisticsCenter" />
						<s:hidden name="hEmpType" /><s:hidden name="statisticsName" /><s:hidden name="statisticsCode" />
						</td>
						<td width="4%" valign="middle" class="txt" align="right">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr>
				   		<td> <input type="button" name="report" value="Report" class="token" onclick="return showReport();" />
							<s:submit value=" Reset" cssClass="reset" action="MonthlyFinancialStatement_reset" title="Clear the fields" /> 
							
						</td>
						<td align="right"><font color="red">*</font> Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td width="15%">
							<label id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> :<font color="red">*</font>
						</td>
						<td width="20%">
							<s:select name="month" headerKey="0" headerValue="--Select--" title="Select the month"
							list="#{'01':'January','02':'Febuary','03':'March','04':'April','05':'May','06':'June','07':'July',
							'08':'August','09':'September','10':'October','11':'November','12':'December'}" /> 
						 </td>
						
						<td width="10%">
							<label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> :<font color="red">*</font>
						</td>
						<td width="35%">
							<s:textfield name="year" size="5" maxlength="4" cssStyle="text-align: right" title="Enter the year"
							onkeypress="return numbersOnly(event);" onblur="return checkYear('paraFrm_year', 'year');" />
						</td>
					</tr> 
					<tr>
						<td width="15%">
							<s:hidden name="divisionId" /><s:hidden name="divisionFlag" /> 
							<label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font color="red">*</font>
						</td>
						<td width="20%" >
							<s:textfield name="divisionName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />								
						</td>
						<td  colspan="3" align="left"><s:a href="#" >
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the division" onclick="callsF9(500,325,'MonthlyFinancialStatement_f9Division.action');">
						</s:a>
						</td>
						
					</tr> 
					   

					
				</table>
			</td>
		</tr>
			
	</table>
</s:form>

<script>
function showReport() {
	if(document.getElementById('paraFrm_month').value == "0") {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase()); 
			document.getElementById('paraFrm_month').focus(); 
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		var checkYearData = document.getElementById('paraFrm_year').value;
		if(checkYearData.length < 4) {
			alert("year should have atleast 4 digits");
			document.getElementById('paraFrm_divisionId').focus();
			return false;
		}
	callReport('MonthlyFinancialStatement_report.action');
	
	}

</script>