 <%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="4" align="center"><b>Conference Accessories Report</b></td>
	</tr>
	<tr>
		<td width="100%" colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="4">&nbsp;</td>
	</tr>

	<tr>
		<td class="bodyCell" width="20%"><b>Accessory Code </td>
		<td class="bodyCell" width="30%"><b>Accessory Name</td>
		<td class="bodyCell" width="50%"><b>Responsible person Name</td>
	 
		
	</tr>
	<s:iterator value="confMaster.ConfList" status="stat">
		<tr>
		<%System.out.println("------------------------------"); %>
			<td class="bodyCell" width="20%"><s:property value="accessCode" /></td>
			<td class="bodyCell" width="30%"><s:property value="accessoryName" /></td>
			<td class="bodyCell" width="50%"><s:property value="resPersonName" /></td>
			 
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>

	
</table>

<tr>
    	<td width="100%" colspan="4" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
 