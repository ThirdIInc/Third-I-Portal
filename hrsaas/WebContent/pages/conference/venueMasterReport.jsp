 <%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="4" align="center"><b>Conference Room Report</b></td>
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
		<td class="bodyCell" width="25%"><b>Conference Room Code </td>
		<td class="bodyCell" width="75%"><b>Conference Room Name</td>
	 
		
	</tr>
	<s:iterator value="venueMaster.venueList" status="stat">
		<tr>
		<%System.out.println("------------------------------"); %>
			<td class="bodyCell" width="25%"><s:property value="venueCode" /></td>
			<td class="bodyCell" width="75%"><s:property value="venueName" /></td>
			 
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>

	
</table>

<tr>
    	<td width="100%" colspan="4" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
 