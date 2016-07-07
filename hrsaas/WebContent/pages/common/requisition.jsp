<%@ taglib prefix="s" uri="/struts-tags"%>

<table width="100%">
	<tr>
		<td colspan="2" width="100%" valign="top">
			<table width="100%" class="sortable">
				<tr>
					<td width="25%" class="formth"><b>Requisition Code</b></td>
					<!--<td width="5%" class="whitetable"><b>View Requisition</b></td>
					--><td width="30%" class="formth"><b>Position</b></td>
					<td width="15%" class="formth"><b>Required by Date</b></td>
					<td width="10%" class="formth"><b>No. of Openings</b></td>
					<td width="5%" class="formth"><b>Candidate Search</b></td>
					<td width="5%" class="formth"><b>View Requisition</b></td>
				</tr>
				
				<%!int i = 0;%>
				<%
					int k = 1;
				%>
					<s:iterator value="list">
						<tr>
							<td class="sortabletd"  width="30%" nowrap="nowrap"><s:property
											value="requisitionName" /></td>
							<!--<td class="whitetable1" width="5%"><s:hidden name="requisitionCode"/><input
											type="button" name="view" class="token"  onclick="viewRequisition('<s:property value="requisitionCode" />')"
											value="View"  /></td>
							--><td class="sortabletd" width="30%"><s:hidden name="vacanDtlCode"/>
											<s:hidden name="positionId"/><s:property
											value="position" /></td>
							<td class="sortabletd" width="15%" align="center"><s:property
											value="requiredDate" />&nbsp;</td>	
							<td class="sortabletd" width="10%" align="right"><s:property
											value="noOfVacancies" />&nbsp;</td>
							<td class="sortabletd" width="5%"><s:hidden name="requisitionCode"/><input
											type="button" name="view" class="token"  onclick="searchCandidate('<s:property value="requisitionCode" />',
											'<s:property value="requisitionName" />','<s:property value="positionId" />','<s:property value="position" />')"
											value="Search  "  /></td>	
											
											<td class="sortabletd" align="center" width="5%">
											<input
											type="button" name="view" class="token"  onclick="showRequisition('<s:property value="requisitionCode" />');"
											value="View  "  />																					
											</td>			
						</tr>
						<%
						k++;
						%>	
					</s:iterator>
						<%
						i = k;
						k = 1;
						%>
			</table>
		</td>
	</tr>

</table>


 