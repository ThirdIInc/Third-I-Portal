	<%@ taglib prefix="s" uri="/struts-tags"%>

	<table width="100%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			
	
			<td width="100%" valign="top">
				
				<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
					<tr>
						
					
											<td width="15%" class="formth"><b>Date</b></td>
											<td width="15%" class="formth"><b>From Time</b></td>
											<td width="15%" class="formth"><b>To Time</b></td>
											<td width="25%" class="formth"><b>Booked By</b></td>
											<td width="30%" class="formth"><b>Conference Hall</b></td>
										
					</tr>
			<%try { %>
					<s:iterator value="confList" status="stat">  
					<tr>
						
					
											<td width="15%" align="center" class="whitetable1"><s:property value="confDate"/></td>
											<td width="15%" align="center" class="whitetable1"><s:property value="confTime"/></td>
											<td width="15%" align="center" class="whitetable1"><s:property value="confToTime"/></td>
											<td width="25%" class="whitetable1"><s:property value="confBy"/></td>
											<td width="25%" class="whitetable1"><s:property value="confPlace"/></td>
										
					</tr>
					</s:iterator>
					
					<%
		}catch(Exception e){
			
		}
			%>
					
							
				</table>
				
	  </td>
   </tr>
</table>
		
		
		
	

