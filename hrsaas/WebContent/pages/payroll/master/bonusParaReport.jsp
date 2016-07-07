<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>CCA Parameter Report</b></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>

	<tr>
		<td class="bodyCell" width="10%"><b>Bonus Code</td>
		<td class="bodyCell" width="10%"><b>Bonus Type</td>
		<td class="bodyCell" width="10%"><b>Bonus Days Declared </td>
		<td class="bodyCell" width="20%"><b>Bonus Period From</td>
		<td class="bodyCell" width="20%"><b>Bonus Period To</td>
		<td class="bodyCell" width="20%"><b>Bonus Employee Type</td>
		<td class="bodyCell" width="10%"><b>Bonus Formula</td>
	
	</tr>
	<s:iterator value="bonusArray" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="bonusCode" /></td>
			<td class="bodyCell" width="10%"><s:property value="bonusType" /></td>
			<td class="bodyCell" width="10%"><s:property value="bonDaysDec" /></td>
			<td class="bodyCell" width="20%"><s:property value="bonPrdFrom" /></td>
			<td class="bodyCell" width="20%"><s:property value="bonPrdTo" /></td>
			<td class="bodyCell" width="20%"><s:property value="bonEmpType" /></td>
			<td class="bodyCell" width="10%"><s:property value="bonFormula" /></td>
			
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>
<!--<tr>
    	<td width="100%" colspan="4" align="center"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>

	
--></table>

