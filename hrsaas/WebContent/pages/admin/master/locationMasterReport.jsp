 <%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="4" align="center"><b>Location Master Report</b></td>
	</tr>
	<tr>
		<td width="100%" colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="4">&nbsp;</td>
	</tr>

	<tr>
		<td class="bodyCell" width="20%"><b>Location Code </td>
		<td class="bodyCell" width="30%"><b>Location Name</td>
		<td class="bodyCell" width="50%"><b>Location Type</td>
	 
		
	</tr>
	<s:iterator value="locationMaster.locationList" status="stat">
		<tr>
		<%System.out.println("------------------------------"); %>
			<td class="bodyCell" width="20%"><s:property value="locationCode" /></td>
			<td class="bodyCell" width="30%"><s:property value="locationName" /></td>
			<td class="bodyCell" width="50%"><s:property value="locationType" /></td>
			 
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>

	
</table>

<tr>
    	<td width="100%" colspan="4" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
 