<%@ taglib prefix="s" uri="/struts-tags" %>
 
 <s:form action="CentralGovEmpInsSch" id="paraFrm" >

<table width="100%" border="0">




<tr>
<tr>
 <td width="100%" colspan="4" class="pageHeader" align="center" >Central Government Employees Insurance Scheme</td>
 </tr>
<tr> 	  
<td width="32%" align="right">Select the Employee :
	<s:hidden name="empId" /></td>

	<td><s:textfield name="empToken" theme="simple"  size="10" readonly="true"/><s:textfield name="empName" theme="simple"  size="40" readonly="true"/>
	<s:if test="cg.viewFlag"><img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'CentralGovEmpInsSch_f9type.action');">	
</s:if>
</td>
</tr>



<tr>
	<td width="36%" align="right"> Report Type : </td>
		<td>
		<s:select  name="repType"  list="#{'Pdf':'PDF','Txt':'Txt'}"  theme="simple"/>		
	</td>
</tr>

 <tr>
  <td width="45%">&nbsp;</td>
  <td><s:if test="cg.viewFlag">
   <input type="button" class="pagebutton" theme="simple"  value='View Report'  onclick="call()" /> </s:if>
  </td>
 </tr>
 
</table>

</s:form>

<script >
 function call(){   
 			if(document.getElementById('empName').value=="") { 
 			alert("Please select the Employee"); 
 } else {
 			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action='CentralGovEmpInsSch_report.action';	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";
			}
			 
  	//alert(document.getElementById('othrs.centerNo').value);
  	//document.getElementById("paraFrm").action="LoanInstallment_edit.action";
  	//document.getElementById('othrs.centerNo').value=document.getElementById('othrs.centerNo').value;
  	
  	}
</script>