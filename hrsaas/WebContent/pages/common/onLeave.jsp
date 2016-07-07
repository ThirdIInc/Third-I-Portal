<%@ taglib prefix="s" uri="/struts-tags"%>

<table width="100%">
<tr>
			<td colspan="2" width="100%" valign="top">
				
				<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
					<tr>
						
					
											<td width="20%" class="formth"><b>Name</b></td>
											<td width="20%" class="formth"><b>From</b></td>
											<td width="20%" class="formth"><b>To</b></td>
											<td width="20%" class="formth"><b>Reason</b></td>
											<td width="20%" class="formth"><b>Contact Info</b></td>
					</tr>
					<s:iterator value="list1" status="stat">  
					<tr>
						
					
											<td width="20%" class="sortabletd"><s:property value="name"/></td>
											<td width="20%" class="sortabletd"><s:property value="fromDt"/></td>
											<td width="20%" class="sortabletd"><s:property value="toDt"/></td>
											<td width="20%" class="sortabletd"><s:property value="reason"/></td>
											<td width="20%" class="sortabletd"><s:property value="contactinfo"/></td>
					</tr>
					</s:iterator>
					
		
							
				</table>
			
			</td>
		</tr>
	</table>
	


