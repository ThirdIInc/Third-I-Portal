<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>Ot Parameter Report</b></td>
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
		 
		<td class="bodyCell" width="20%"><b>Employee Type</b> </td>
		<td class="bodyCell" width="20%"><b>Normal Calc Single</b></td>
		<td class="bodyCell" width="20%"><b>Normal Calc Double</b></td> 
		<td class="bodyCell" width="20%"><b>Holiday Calc Single</b></td>
		<td class="bodyCell" width="20%"><b>Holiday Calc Double</b></td></tr>
	<s:iterator value="otParaArray" status="stat">
		<tr>
			<td class="bodyCell" width="20%"><s:property value="typeName" /></td>
			<td class="bodyCell" width="20%"><s:property value="normalCalSingle" /></td>
			<td class="bodyCell" width="20%"><s:property value="normalCalDouble" /></td> 
			<td class="bodyCell" width="20%"><s:property value="holiCalSingle" /></td>
			<td class="bodyCell" width="20%"><s:property value="holiCalDouble" /></td>

		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>
<!--<tr>
    	<td width="100%" colspan="4" align="center"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>

	
--></table>

