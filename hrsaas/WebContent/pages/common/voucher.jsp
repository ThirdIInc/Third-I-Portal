<%@ taglib prefix="s" uri="/struts-tags"%>

<table width="100%">
<tr>
			<td colspan="2" width="100%" valign="top">
				
				<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
					<tr>
						
					
											<td width="36" class="whitetable"><b>Name</b></td>
											<td width="32%" class="whitetable"><b>Date</b></td>
											<td width="32%" class="whitetable"><b>Amount</b></td>
											
					</tr>
					<s:iterator value="vchList" status="stat">  
					<tr>
						
					
											<td width="36%" class="whitetable1"><s:property value="name"/></td>
											<td width="32%" class="whitetable1"><s:property value="vchDate"/></td>
											<td width="32%" class="whitetable1"><s:property value="amt"/></td>
											
					</tr>
					</s:iterator>
					
		
							
				</table>
			
			</td>
		</tr>
	</table>
	


