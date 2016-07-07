<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="SalaryJV" validate="true" id="paraFrm"
	theme="simple">
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
					<td width="93%" class="txt"><strong class="text_head">Salary JV Report
					  </strong></td>
				</tr>
			</table>
			</td>
		</tr>
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
				cellspacing="2" class="formbg">
				<tr>
					<s:hidden name="startDate" />
					<s:hidden name="endDate" />
					<td colspan="1" width="15%"><label name="division"
						id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
						color="red">*</font> :</td>
					<td colspan="3" width="100%"><s:textfield size="25" readonly="true"
						name="divName" /> <s:hidden name="divCode" />
						<img src="../pages/images/recruitment/search2.gif" width="16"
								class="iconImage" height="15"
								onclick="javascript:callsF9(500,325,'SalaryJV_f9Div.action');" />
					</td>
				</tr>
				<tr>
							<td colspan="1" width="15%"><label  class = "set"  id="from.month" name="from.month" ondblclick="callShowDiv(this);"><%=label.get("from.month")%></label> :</td>
							<td colspan="1" width="15%"><s:select
								name="fromMonth" theme="simple" list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" onchange="setYear();"/> 
							</td>
							<td colspan="1" width="15%"><label  class = "set"  id="from.year" name="from.year" ondblclick="callShowDiv(this);"><%=label.get("from.year")%></label> :</td>
							<td colspan="1" width="40%"> <s:textfield maxlength="4"
								name="fromYear" theme="simple" size="12" onkeypress="return numbersOnly();" onkeyup="setYear();"/> 
							</td> 
				</tr>
				<tr>
							<td colspan="1" width="15%"><label  class = "set"  id="to.month" name="to.month" ondblclick="callShowDiv(this);"><%=label.get("to.month")%></label> :</td>
							<td width="15%"><s:select
								name="toMonth" theme="simple" list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" onchange="setYear();"/></td>
							<td colspan="1" width="10%"><label  class = "set"  id="to.year" name="to.year" ondblclick="callShowDiv(this);"><%=label.get("to.year")%></label> :</td>
							<td colspan="1" width="30%"> <s:textfield maxlength="4"
								name="toYear" theme="simple" size="12" onkeypress="return numbersOnly();" readonly="true"/> 
							</td>
				</tr>
				<tr>
							<td colspan="1" width="15%"><label  class = "set"  id="include" name="include" ondblclick="callShowDiv(this);"><%=label.get("include")%></label> :</td>
							<td colspan="3" width="100%" nowrap="nowrap"><s:hidden name="allFlag"/>
							<!-- <input type="checkbox" name="allFlag" id="paraFrm_allFlag" checked="checked" onclick="chkOption('0');"/>All &nbsp;&nbsp;--><s:checkbox name="brnFlag" onclick="chkOption('1');"/>
							<label name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							&nbsp;&nbsp;<s:checkbox name="deptFlag" onclick="chkOption('1');"/>
							<label name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							&nbsp;&nbsp;<s:checkbox name="empTypeFlag" onclick="chkOption('1');"/>
							<label name="employee.type" id="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
							&nbsp;&nbsp;<s:checkbox name="costCenterFlag" onclick="chkOption('1');"/>
							<label name="costCent" id="costCent" ondblclick="callShowDiv(this);"><%=label.get("costCent")%></label>
							&nbsp;&nbsp;<s:checkbox name="tradeFlag" onclick="chkOption('1');"/>
							<label name="trade" id="trade" ondblclick="callShowDiv(this);"><%=label.get("trade")%></label>
							&nbsp;&nbsp;<s:checkbox name="employeeFlag" onclick="chkOption('1');"/>
							<label name="emp" id="emp" ondblclick="callShowDiv(this);"><%=label.get("emp")%></label>
							</td>
				</tr>
				<tr>
							<td colspan="1" width="25%"><label  class = "set"  id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> :</td>
							<td colspan="1" width="25%"> <s:select
								name="reportType" theme="simple" list="#{'':'--Select--','Pdf':'PDF','Xls':'XLS'}"/>
							</td>
							<td colspan="1" width="25%"><label  class = "set"  id="onHoldLable" name="onHoldLable" ondblclick="callShowDiv(this);"><%=label.get("onHoldLable")%></label> :</td>
							<td colspan="1" width="25%"> <s:select
								name="onHold" theme="simple" list="#{'A':'--All--','Y':'Yes','N':'No'}"/>
							</td>
				</tr>
				
				<tr>
							<td colspan="1" width="15%"><label  class = "set"  id="credit" name="credit" ondblclick="callShowDiv(this);"><%=label.get("credit")%></label> :</td>
							<td width="15%" nowrap="nowrap"><s:textfield size="25" readonly="true"
							name="creditHead" /><s:hidden name="creditCode" /><s:hidden name="creditAbbr" />
							<img src="../pages/images/recruitment/search2.gif" width="16" class="iconImage" height="15"
								onclick="javascript:callsF9(500,325,'SalaryJV_f9Credit.action');" /></td>
							<td colspan="1" width="10%"><label  class = "set"  id="debit" name="debit" ondblclick="callShowDiv(this);"><%=label.get("debit")%></label> :</td>
							<td colspan="1" width="30%" nowrap="nowrap"><s:textfield size="25" readonly="true"
							name="debitHead" /><s:hidden name="debitCode" /><s:hidden name="debitAbbr" />
							<img src="../pages/images/recruitment/search2.gif" width="16" class="iconImage" height="15"
								onclick="javascript:callsF9(500,325,'SalaryJV_f9Debit.action');" /> 
							</td>
				</tr>
				
				
			</table>
			</td>
		</tr>
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
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	setCurrentYear('paraFrm_fromYear');
	setCurrentYear('paraFrm_toYear');
	function setYear()
	{
		if(document.getElementById('paraFrm_fromYear').value == "" && document.getElementById('paraFrm_fromYear').value.length < 4)
			return false;
		var fromMonth = eval(document.getElementById('paraFrm_fromMonth').value);
		var toMonth = eval(document.getElementById('paraFrm_toMonth').value);
		if(fromMonth <= toMonth)
			document.getElementById('paraFrm_toYear').value = document.getElementById('paraFrm_fromYear').value;
		else
			document.getElementById('paraFrm_toYear').value = eval(eval(document.getElementById('paraFrm_fromYear').value)+1);
	}

	function resetScreen()
	{
			document.getElementById('paraFrm_divCode').value="";
			document.getElementById('paraFrm_divName').value="";
			document.getElementById('paraFrm_reportType').value="";
			document.getElementById('paraFrm_fromYear').value="";
			document.getElementById('paraFrm_fromMonth').value="1";
			document.getElementById('paraFrm_toYear').value="";
			document.getElementById('paraFrm_toMonth').value="1";
			document.getElementById('paraFrm_brnFlag').checked="";
			document.getElementById('paraFrm_deptFlag').checked="";
			document.getElementById('paraFrm_empTypeFlag').checked="";
			document.getElementById('paraFrm_costCenterFlag').checked="";
			document.getElementById('paraFrm_tradeFlag').checked="";
			document.getElementById('paraFrm_employeeFlag').checked="";
			document.getElementById('paraFrm_creditCode').value="";
			document.getElementById('paraFrm_debitCode').value="";
			document.getElementById('paraFrm_creditAbbr').value="";
			document.getElementById('paraFrm_debitAbbr').value="";
			document.getElementById('paraFrm_creditHead').value="";
			document.getElementById('paraFrm_debitHead').value="";
			document.getElementById('paraFrm_onHold').value="A";
			
	}
	
	function validateReport()
	{
		if(document.getElementById('paraFrm_divCode').value == "")
		{
			alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_reportType').value == "")
		{
			alert("Please select "+document.getElementById('report.type').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_fromYear').value=="")
		{
			alert("Please enter "+document.getElementById('from.year').innerHTML.toLowerCase());
			return false;
		}
		if(eval(document.getElementById('paraFrm_fromYear').value) == 0 || document.getElementById('paraFrm_fromYear').value.length < 4)
		{
			alert("Please enter "+document.getElementById('from.year').innerHTML.toLowerCase());
			return false;
		}
		var todayDate = new Date();
		var month	  = todayDate.getMonth();
		var year 	  = todayDate.getFullYear();
		if(eval(document.getElementById('paraFrm_fromMonth').value) > 	eval(document.getElementById('paraFrm_toMonth').value) 
			&& eval(document.getElementById('paraFrm_fromYear').value) >	eval(document.getElementById('paraFrm_toYear').value))
		{
			alert("From month and year can't be greater than to month and year. ");
			return false;
		}
		
		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action="SalaryJV_generateReport.action";
		document.getElementById('paraFrm').submit();
	}
	
function chkOption(type)
{
	if(type==0)
	{
		if(document.getElementById('paraFrm_allFlag').checked)
		{
			document.getElementById('paraFrm_brnFlag').checked=false;
			document.getElementById('paraFrm_deptFlag').checked=false;
			document.getElementById('paraFrm_empTypeFlag').checked=false;
			document.getElementById('paraFrm_employeeFlag').checked=false;
			document.getElementById('paraFrm_costCenterFlag').checked=false;
			document.getElementById('paraFrm_tradeFlag').checked=false;
			
		}
		else
			document.getElementById('paraFrm_brnFlag').checked=true;
	}
	else
	{
		if(!document.getElementById('paraFrm_brnFlag').checked 
		   && !document.getElementById('paraFrm_deptFlag').checked 
		   && !document.getElementById('paraFrm_empTypeFlag').checked
		   && !document.getElementById('paraFrm_employeeFlag').checked
		   && !document.getElementById('paraFrm_costCenterFlag').checked
		   && !document.getElementById('paraFrm_tradeFlag').checked)
		   
		{
			document.getElementById('paraFrm_allFlag').checked=true;
		}
		else
		{
			document.getElementById('paraFrm_allFlag').checked=false;
		}
	}
}
</script> 