<%@ taglib prefix="s" uri="/struts-tags" %> 

<s:form action="BioData" id="paraFrm" >
<table width="100%" border="0">
<tr>
 <td width="100%" colspan="4" class="pageHeader" align="center" >Employee Biodata</td>
</tr>
<tr> 	  
<td width="32%" align="right">Employee Name:
	<s:hidden name="empId" />
</td>
<td>
	<s:textfield name="empName" theme="simple"  maxlength="50" size="40" readonly="true"/>
	<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'BioData_f9action.action');">	
</td>
</tr>

<tr>
	<td width="36%" align="right"> Report Type : </td>
		<td>
		<s:select  name="repType"  list="#{'Pdf':'PDF','Xls':'XLS'}"  theme="simple"/>		
	</td>
</tr>

 <tr>
  <td width="45%">&nbsp;</td>
  <td>
   <s:submit cssClass="pagebutton" cssClass="button" theme="simple"  value='Report' action="BioData_report" />
  </td>
 </tr>
 
</table>

</s:form>

<script >
 function call(){   
 			if(document.getElementById('empId').value > 0) {  
 
 			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action='CentralGovEmpInsSch_report.action';	
			document.getElementById('paraFrm').submit();	
			} else {
			alert("Please select an employee.")
			 
  	//alert(document.getElementById('othrs.centerNo').value);
  	//document.getElementById("paraFrm").action="LoanInstallment_edit.action";
  	//document.getElementById('othrs.centerNo').value=document.getElementById('othrs.centerNo').value;
  	}
  	}
</script>