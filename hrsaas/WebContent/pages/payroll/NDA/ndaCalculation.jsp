<%@ taglib prefix="s" uri="/struts-tags"%>


<s:form action="NdaCalc" id="paraFrm" validate="true" target="main"  theme="simple">
	<table  width="100%">
		<tr>
			<td width="100%" colspan="2" class="pageHeader">
			<p align="center">Night Duty Allowance Calculation
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td  align="RIGHT">Month <font color="red">*</font>:</td>
			
			<td colspan="1"><s:select name="month"
				list="#{'0':'Select','1':'JAN','2':'FEB','3':'MAR','4':'APR','5':'MAY','6':'JUN','7':'JUL','8':'AUG','9':'SEP','10':'OCT','11':'NOV','12':'DEC'}" />
			</td>
			
			<!--<s:textfield label="%{getText('month')}" maxLength="2"
				theme="simple" name="month" onkeypress="return numbersonly(this);"/></td>-->
				<td></td>
		</tr>
		<tr>
			<td  align="RIGHT" >Year<font color="red">*</font>:</td>
			<td colspan="1"><s:textfield label="%{getText('Year')}" maxLength="4"
				theme="simple" name="Year" onkeypress="return numbersonly(this);" /></td>
		</tr>

		<tr>
			<td  align="RIGHT"> Employee Type <font color="red">*</font>:</td>
			<td colspan="1"><s:textfield name="empType"
				theme="simple"  readonly="true" />
				<s:hidden  theme="simple" name="srtEmp" />	
				 <img
				src="../pages/images/search.gif" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,325,'NdaCalc_f9Emp.action');">
			</td>
		</tr>
		<tr>
			<td  align="RIGHT"> Paybill Group<font color="red">*</font>:</td>
				<s:hidden  theme="simple" name="pbId"  />
			<td colspan="1"><s:textfield label="%{getText('pbId')}"
				theme="simple" name="pbgrp" readonly="true" /><img
				src="../pages/images/search.gif" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,325,'NdaCalc_f9Group.action');">
			</td>

		</tr>

		<tr>
				<td align="center" colspan="4">
				<s:submit cssClass="pagebutton" action="NdaCalc_getRecord" theme="simple" onclick=" return validate()"
				value=" View " />
							<s:if test="flag">		
				<s:if test="insertFlag">
				<s:submit cssClass="pagebutton" action="NdaCalc_view" theme="simple" onclick=" return validate()"
				value="Calculate" />
				
				<s:if test="viewFlag">
				<s:submit cssClass="pagebutton" action="NdaCalc_save" theme="simple" onclick=" return validate()"
				value=" Save " />
				</s:if>
				</s:if>
				<input type ="button"  onclick="callreport('NdaCalc_report.action')" onkeydown="return validate()"	value="Generate Report" />
				</s:if>
				<s:submit cssClass="pagebutton" action="NdaCalc_reset" theme="simple"  value=" Reset " />
				</td>
				 
				
		</tr>

	</table>

	<table width="100%">
	<s:if test="flag">
		<tr>
			<td class="headerCell">Sr No</td>
			<td class="headerCell">Emp Token</td>
			<td class="headerCell">Emp Name</td>
			<td class="headerCell">Nda hrs</td>
			<td class="headerCell">Amount</td>

		</tr>
</s:if>
		<%
		int i = 1;
		%>

		<s:iterator value="att">

			<tr>
				<td><%=i%>
				<s:hidden name="empid"  />
				</td>
				<td><s:label value="%{empToken}" theme="simple" /></td>

				<td><s:label value="%{empName}"  theme="simple" /></td>

				<td><s:textfield name="ndaHrs"  theme="simple"  readonly="true"/></td>

				<td>
				<s:textfield theme="simple" name="ndaAmount"  readonly="true"></s:textfield>
				</td>


			</tr>

			<%
			i++;
			%>
		</s:iterator>

	</table>

</s:form>
<script type="text/javascript">
function callreport(name)
{
				document.getElementById('paraFrm').target="main";
				document.getElementById('paraFrm').action=name;	
				document.getElementById('paraFrm').submit();	
		//		document.getElementById('paraFrm').target="main";
}
function validate()
{
var mon=document.getElementById('month').value;
var yr=document.getElementById('Year').value;
var emptyp=document.getElementById('empType').value;
var pbgrp=document.getElementById('pbgrp').value;

if(eval(mon)==0)
{
alert('Please Select Month !')
return false
}
if(yr=="")
{
alert('Please Enter Year !')
return false
}
if(emptyp=="")
{
alert('Please Select Employee Type!')
return false
}
if(pbgrp=="")
{
alert('Please Select Paybill Group !')
return false
}
}

function numbersonly(myfield)
	{
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else
			return true;
		
		keychar = String.fromCharCode(key);	
		if ((("0123456789").indexOf(keychar) > -1))
			return true;	
		else {
			myfield.focus();
			return false;
		}
	}

</script>
