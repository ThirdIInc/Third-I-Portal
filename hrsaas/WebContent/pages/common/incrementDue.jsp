<%@ taglib prefix="s" uri="/struts-tags"%>

<table width="100%">
	<tr>
		<td colspan="2" width="100%" valign="top">
		<div >
		<table width="100%">
			<tr>
				<td width="25%" class="whitetable"><b>Name</b></td>
				<td width="25%" class="whitetable"><b>Branch</b></td>
				<td width="25%" class="whitetable"><b>Due Date</b></td>
				<td width="25%" class="whitetable"><b>Pending Since Days</b></td>
			</tr>
		<s:iterator value="incrList" status="stat">  
			<tr>
				<td width="25%" class="whitetable1"><a href="#"><s:property value="empName"/></a></td>
				<td width="25%" class="whitetable1"><s:property value="branch"/></td>
				<td width="25%" class="whitetable1"><s:property value="dueDate"/></td>
				<td width="25%" class="whitetable1" align="center"><s:property value="pendingDays"/></td>
			
			</tr>
		</s:iterator>	
			


		</table>
		</div>
		</td>
	</tr>

</table>
 