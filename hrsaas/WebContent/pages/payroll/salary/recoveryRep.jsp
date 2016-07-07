<%@ taglib prefix="s" uri="/struts-tags" %>
<s:form  action="RecoveryReport" theme="simple" id="paraFrm" target="main" >
	<table width="100%">
	
	
<tr>
  			<td width="100%" colspan="4" class="pageHeader" align="center" >Recovery Report</td>
</tr>


<tr><td >&nbsp;</td></tr>

<tr>
<td align="center">Month<font color="red">*</font> :</td>
    <td>&nbsp;</td>
	<td ><s:select name="month" id="month"  list="#{'':'-------Select----------','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"  />
	
</td>
</tr>


<tr>
<td align="center">Year<font color="red">*</font> :</td>
    <td>&nbsp;</td>
	<td><s:textfield name="year" id="year" maxlength="4" onkeypress="onlyNumber();"  />
	
</td>
</tr>


<tr>
<td align="center">Debit Head<font color="red">*</font> :</td>
    <td>&nbsp;</td>
	<td><s:textfield theme="simple" readonly="true" size="50" id="debName" name="debName" /><s:hidden name="debId" />
	<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'RecoveryReport_f9deb.action');">	
</td>
</tr>

<tr>
<td align="center">Paybill Group<font color="red">*</font> :</td>
    <td>&nbsp;</td>
	<td><s:textfield theme="simple" readonly="true" size="50" id="payName" name="payName" /><s:hidden name="payId" />
	<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'RecoveryReport_f9paybill.action');">	
</td>
</tr>


<tr><td >&nbsp;</td></tr>
<tr><td >&nbsp;</td></tr>

<tr>
								<td colspan="3" align="center"><CENTER><input type="button" class="pagebutton" value=' Submit ' onclick="callReport()" /></CENTER></td>
</tr>
		
</table>
</s:form>
<script>
function callReport(){

				if(document.getElementById('month').value=="") {
				alert("Please select Month");
				return false;
				
				}
				if(document.getElementById('year').value=="") {
				alert("Please enter the Year");
				return false;
				
				}
				if(document.getElementById('debName').value=="") { 
				alert("Please select the Debit Head");
				return false;
				}
				if(document.getElementById('payName').value=="") { 
				alert("Please select the Paybill Group");
				return false;
				} else {
						
				document.getElementById('paraFrm').target="_blank";
				document.getElementById('paraFrm').action="RecoveryReport_report.action";	
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";
				
				}
}


function onlyNumber() {
	if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;	
}

</script>
