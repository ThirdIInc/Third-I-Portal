 <%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>  City Report </b></td>
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
		<td class="bodyCell" width="10%"><b>City Code</td>
		<td class="bodyCell" width="30%"><b>City Name</td>
		<td class="bodyCell" width="20%"><b>City Class</td>
		<td class="bodyCell" width="20%"><b>City Abbr</td>
		<td class="bodyCell" width="10%"><b>City Parent Code</td>
		<td class="bodyCell" width="10%"><b>City Level Code</td>
	

	</tr>
	 <s:iterator value="ctyMaster.cityList" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="cityID" /></td>
			<td class="bodyCell" width="30%"><s:property value="cityName" /></td>
			<td class="bodyCell" width="20%"><s:property value="cityClass" /></td>
			<td class="bodyCell" width="20%"><s:property value="cityAbbr" /></td>
			<td class="bodyCell" width="10%"><s:property value="cityParID" /></td>
			<td class="bodyCell" width="10%"><s:property value="cityLevCode" /></td>

		</tr>
	</s:iterator>
	 
	 
	 
 
	<tr>
		<td>&nbsp;</td>
	</tr>
	
</table>

 