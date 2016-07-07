<%@ taglib prefix="s" uri="/struts-tags" %>
 
 <s:form action="leaveBalanceReport" id="paraFrm">

<table width="100%" border="0">

<tr>
  			<td width="100%" colspan="4" class="pageHeader" align="center" >Leave Balance Report</td>
</tr>


<tr>


<td width="32%" align="right">Select the Employee :
	<s:hidden name="empId" /></td>
	
	
			<s:if test="lbr.generalFlag"></s:if>
	
	
	<td><s:textfield name="empToken" theme="simple"  size="10" readonly="true"/><s:textfield name="empName" theme="simple"  size="40" readonly="true"/>
	<s:else><img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'leaveBalanceReport_f9type.action');">	
	</s:else></td>
<!--<td width="32%" align="right">Select the Employee :</td>
	<s:hidden name="empId" />
	<td><s:textfield name="empName" theme="simple"  maxlength="50" size="40" readonly="true"/>
	<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'leaveBalanceReport_f9type.action');">	
</td>
--></tr>



<tr>
	<td width="36%" align="right"> Report Type : </td>
		<td>
		<s:select  name="repType"  list="#{'Pdf':'PDF','Txt':'Text'}"  theme="simple"/>		
	</td>
</tr>

 <tr>
  <td width="45%">&nbsp;</td>
  <td>
  <s:if test="lbr.viewFlag">
  <input type="button" class="pagebutton" theme="simple"  value='View Report'  onclick="call()" />
  </s:if>
  </td>
 </tr>
 
</table>

</s:form>

<script >
 function call(){ 
 if(document.getElementById('empName').value=="") {  
 alert("Please Select the Employee");
 		
  
  	} else {
	
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action='leaveBalanceReport_report.action';	
			document.getElementById('paraFrm').submit();	 
			document.getElementById('paraFrm').target="main";
	
	}
	}
</script>