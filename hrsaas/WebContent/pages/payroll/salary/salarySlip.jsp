<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="SalarySlip" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Employee Salary Slip</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				<td>
				<s:if test="%{salarySlip.viewFlag}">
				<input type="button" class="token" value=" Submit" onclick="callReport()" />
										</s:if>
				</td>
		<td>
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
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
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"></strong></td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:<font color="red">*</font></td>
							<td colspan="3" width="80%">
							<s:hidden name="salarySlip.salaryEmpCode" />
							<s:textfield theme="simple" readonly="true" name="salarySlip.salaryEmpToken" size="20"/>
							<s:textfield theme="simple" readonly="true" size="50" name="salarySlip.salaryEmpName" />
							<s:if test="salarySlip.generalFlag">
									</s:if>
									<s:else>
										<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'SalarySlip_f9employee.action');">	
									</s:else>
							</td>
						</tr>


						<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label>  <font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:select name="salarySlip.salaryMonth" cssStyle="width:130" list="#{'0':'Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"  /></td>
						</tr>
						
						<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> <font color="red">*</font>:</td>
							<td colspan="1" width="30%">
							<s:textfield theme="simple" name="salarySlip.salaryYear" maxlength="4" onkeypress="return numbersOnly();" />
							</td>
							
							<tr>
							<td>&nbsp;</td>	<td>&nbsp;</td>
						</tr>
						
						<tr>
						<td colspan="1" width="20%"><label  class = "set"  id="cons.arrears" name="cons.arrears" ondblclick="callShowDiv(this);"><%=label.get("cons.arrears")%></label>:</td> 
						<td colspan="1" width="30%"><input type="checkbox" name="checkFlag"  id="checkFlag"
						 onclick="callCheck();" /></td>
						</tr>
						
						</tr>
							
							
							<tr>
							<td colspan="3" >
									<CENTER>
										
										
									</CENTER>
									</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>
		
		</table>
		</td></tr></table>
		</s:form>



<script type="text/javascript">
setCurrentYear("paraFrm_salarySlip_salaryYear");
 function callCheck(){
 var checkDefault = document.getElementById('checkFlag').checked;
 
 	if(checkDefault){
 			document.getElementById('checkFlag').value="Y";
 	
 	}
 	
 
 }

function callReport(){
		var empName = document.getElementById('paraFrm_salarySlip_salaryEmpToken').value;
		var year = document.getElementById('paraFrm_salarySlip_salaryYear').value;
		var month = document.getElementById('paraFrm_salarySlip_salaryMonth').value;
		
		if(empName=="")
		{
		alert("Please Select "+document.getElementById('employee').innerHTML.toLowerCase());
		return false;
		}
		if(year=='')
		{
		alert("Please Enter "+document.getElementById('year').innerHTML.toLowerCase());
		return false;
		}
		if(month==0)
		{
		alert("Please Select "+document.getElementById('month').innerHTML.toLowerCase());
		return false;
		}

				document.getElementById('paraFrm').target="_blank";
				document.getElementById('paraFrm').action="SalarySlip_report.action";	
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";
}

</script>
