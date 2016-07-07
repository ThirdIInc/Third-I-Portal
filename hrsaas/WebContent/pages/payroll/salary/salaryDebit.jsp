<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="java.util.ArrayList,org.paradyne.bean.payroll.salary.*"%>
<s:form action="Debit" id="paraFrm" validate="true" target="_top">

	<table class="tableBody" width="100%" colspan="2">
		<tr>
			<td width="25%" colspan="5" class="pageHeader">
			<p align="center">Debit Employee Configuration
			</td>
		</tr>
		<tr>
			<td width="25%" colspan="3"></td>
		</tr>

		<!--      	
		<tr><td>Debit Code</td>
     
		<td  width="25%" colspan="3">
  		<s:textfield label="%{getText('debitCode')}"   theme="simple"  name="debit.debitCode"/>
  		
  		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle" width="18"  theme="simple"   onclick="javascript:callsF9(500,400,'Debit_f9Action.action');">
  		</td>
  		</tr>
  	 -->
		<tr>
			<td width="25%">Employee ID</td>
			<td width="25%"><s:textfield
				label="%{getText('empId')}" theme="simple" name="debit.empId" /> <img
				src="../pages/images/search.gif" height="18" align="absmiddle"
				width="18" theme="simple"
				onclick="javascript:callsF9(600,400,'Debit_f9actionEmpId.action'); ">

			</td>
		</tr>

		<tr>
			<td width="25%">Employee Name</td>
			<td width="25%"><s:textfield label="%{getText('empName')}"
				theme="simple" name="debit.empName" /></td>
		</tr>

		<tr>
			<td width="25%">Department</td>
			<td width="25%"><s:textfield label="%{getText('department')}"
				theme="simple" name="debit.department" /></td>
		</tr>

		<tr>
			<td width="25%">Center</td>
			<td width="25%"><s:textfield label="%{getText('center')}"
				theme="simple" name="debit.center" /></td>
		</tr>

		<s:hidden label="%{getText('rank')}" theme="simple" name="debit.rank" />

		<s:hidden label="%{getText('trade')}" theme="simple"
			name="debit.trade" />
		
		

		<tr align="center">

			<td align="center" colspan="3"><s:submit cssClass="pagebutton" action="Debit_save"
				theme="simple" value="   Save   " />&nbsp; <s:submit cssClass="pagebutton"
				action="Debit_update" theme="simple" value="   Update   " />&nbsp;
			<s:submit cssClass="pagebutton" action="Debit_reset" theme="simple" value="  Reset  " />&nbsp;
			<s:submit cssClass="pagebutton" action="Debit_delete" theme="simple" value="  Delete  " />&nbsp;
			<input type="button" class="pagebutton" onclick="call('Debit_report.action')"
				theme="simple" value="  Report " /></td>
		</tr>
	</table>

	<table>

		<tr>
			<td class="headercell" width="30%">Serial No.</td>

			<td class="headercell" width="30%">Header Name</td>

			<td class="headercell" width="30%">Amount Text</td>

			<td class="headercell" width="30%">Select</td>

		</tr>


		 
		<s:iterator value="arrayList">

			<tr>
				 

				<td><s:label value="%{headName}" theme="simple" /></td>

				<td><s:textfield theme="simple" value="%{getText(amountText)}"
					name="amountText" /></td>

				<td><s:checkbox name="chkBox" onclick="" value="false"
					theme="simple" /></td>

				<td><s:hidden name="debitHdrId" value="%{headerId}"
					theme="simple" /></td>
			</tr>

			 
		</s:iterator>

	</table>

</s:form>
<script>
  	function call(name){
  	
	  	document.getElementById('paraFrm').target="_blank";
	  	document.getElementById('paraFrm').action=name;
	  	document.getElementById('paraFrm').submit();
  	}
  	</script>
