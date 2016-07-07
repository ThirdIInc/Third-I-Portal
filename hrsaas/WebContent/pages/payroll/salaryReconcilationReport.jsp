<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="SalaryJV" validate="true" id="paraFrm" theme="simple">
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
					<td width="93%" class="txt"><strong class="text_head">Salary
					Reconciliation Report </strong></td>
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
						theme="simple" value=" Report" onclick="return validateReport();" />
					<input type="button" class="reset" theme="simple" value=" Reset"
						onclick="return resetScreen();" /></td>
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
					<td colspan="1" width="15%"><label name="division"
						id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
						color="red">*</font> :</td>
					<td colspan="1" width="30%"><s:textfield size="25" readonly="true"
						name="divName" /> <s:hidden name="divCode" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						class="iconImage" height="15"
						onclick="javascript:callsF9(500,325,'SalaryReconcilation_f9Div.action');" />
					</td>
				</tr>
				<tr>
					<td colspan="1" width="15%"><label class="set" id="from.month"
						name="from.month" ondblclick="callShowDiv(this);"><%=label.get("from.month")%></label>
					:</td>
					<td colspan="1" width="15%"><s:select name="monthFor"
						theme="simple"
						list="#{'':'--Select--','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}"
						onchange="setYearMonth();" /></td>
					<td colspan="1" width="15%"><label class="set" id="from.year"
						name="from.year" ondblclick="callShowDiv(this);"><%=label.get("from.year")%></label>
					:</td>
					<td colspan="1" width="40%"><s:textfield maxlength="4"
						name="yearFor" theme="simple" size="12"
						onkeypress="return numbersOnly();" onkeyup="setYearMonth();"/></td>
				</tr>
				<tr>
					<td colspan="1" width="15%"><label class="set" id="to.month"
						name="to.month" ondblclick="callShowDiv(this);"><%=label.get("to.month")%></label>
					:</td>
					<td width="15%"><s:select name="monthTo" theme="simple" onchange="setYearMonth();"
						list="#{'':'--Select--','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
					<td colspan="1" width="10%"><label class="set" id="to.year"
						name="to.year" ondblclick="callShowDiv(this);"><%=label.get("to.year")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield maxlength="4"
						name="yearTo" theme="simple" size="12"
						onkeypress="return numbersOnly();" /></td>
				</tr>
				<tr>
					<td colspan="1" width="15%"><label class="set"
						id="report.type" name="report.type"
						ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
					:</td>
					<td colspan="3" width="100%"><s:select name="reportType"
						theme="simple" list="#{'Pdf':'PDF'}" />
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
						theme="simple" value=" Report" onclick="return validateReport();" />
					<input type="button" class="reset" theme="simple" value=" Reset"
						onclick="return resetScreen();" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	setCurrentYear('parFrm_yearFor');
	setCurrentYear('parFrm_yearTo');
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
			document.getElementById('paraFrm_yearFor').value="";
			document.getElementById('paraFrm_monthFor').value="";
			document.getElementById('paraFrm_yearTo').value="";
			document.getElementById('paraFrm_monthTo').value="";
			document.getElementById('paraFrm_yearTo').value="";
	}
	
	function validateReport()
	{
		try{
		if(document.getElementById('paraFrm_divCode').value=='')
		{
			alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_monthFor').value=='')
		{
			alert("Please select "+document.getElementById('from.month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_monthFor').focus();
			return false;
		}
		if(document.getElementById('paraFrm_yearFor').value=='')
		{
			alert("Please enter "+document.getElementById('from.year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_yearFor').focus();
			return false;
		}
		if(document.getElementById('paraFrm_monthTo').value=='')
		{
			alert("Please select "+document.getElementById('to.month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_monthTo').focus();
			return false;
		}
		if(eval(document.getElementById('paraFrm_monthFor').value)==
			eval(document.getElementById('paraFrm_monthTo').value))
		{
			alert("Please select different months. "+document.getElementById('to.month').innerHTML.toLowerCase()
			   +" and "+document.getElementById('from.month').innerHTML.toLowerCase()+" cannot be same");
			   return false;
		}
			
		if(document.getElementById('paraFrm_yearTo').value=='')
		{
			alert("Please enter "+document.getElementById('to.year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_yearTo').focus();
			return false;
		}
		if(document.getElementById('paraFrm_yearFor').value.length<4 
			|| eval(document.getElementById('paraFrm_yearFor').value)==0)
		{
			alert("Please enter the "+document.getElementById('from.year').innerHTML.toLowerCase()+" properly");
			document.getElementById('paraFrm_yearFor').focus();
			return false;
		}
		if(document.getElementById('paraFrm_yearTo').value.length<4 
			|| eval(document.getElementById('paraFrm_yearTo').value)==0)
		{
			alert("Please enter the "+document.getElementById('to.year').innerHTML.toLowerCase()+" properly");
			document.getElementById('paraFrm_yearFor').focus();
			return false;
		}
		if(document.getElementById('paraFrm_reportType').value=='')
		{
			alert("Please select the "+document.getElementById('report.type').innerHTML.toLowerCase()+"");
			return false;
		}
		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action="SalaryReconcilation_generateReport.action";
		document.getElementById('paraFrm').submit();
		}catch(e)
		{
			// alert(e);
		}
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
		}
		else
			document.getElementById('paraFrm_brnFlag').checked=true;
	}
	else
	{
		if(!document.getElementById('paraFrm_brnFlag').checked 
		   && !document.getElementById('paraFrm_deptFlag').checked 
		   && !document.getElementById('paraFrm_empTypeFlag').checked)
		{
			document.getElementById('paraFrm_allFlag').checked=true;
		}
		else
		{
			document.getElementById('paraFrm_allFlag').checked=false;
		}
	}
}

function setYearMonth()
{
	try{
	var monthFor=document.getElementById('paraFrm_monthFor').value;
	var monthTo=document.getElementById('paraFrm_monthTo').value;
	var yearFor=document.getElementById('paraFrm_yearFor').value;
	if(monthFor==''||monthTo==''||yearFor=='')
		return false;
	if(eval(monthFor) <= eval(monthTo))
	{
		document.getElementById('paraFrm_yearTo').value=yearFor;
	}
	else if(eval(monthFor) > eval(monthTo))
	{
		document.getElementById('paraFrm_yearTo').value=eval(eval(yearFor)+1);
	}
	}catch(e)
	{
		// alert(e);
	}
}
</script>
