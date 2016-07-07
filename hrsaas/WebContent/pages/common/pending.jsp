<%@ taglib prefix="s" uri="/struts-tags"%>

	<table width="100%">
					<tr>
						<td width="50%">
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
								<tr>
									<td width="25%" class="formth"><b>Name</b></td>
									<td width="25%" class="formth"><b>Applied Date</b></td>
									<td width="25%" class="formth"><b>From </b></td>
									<td width="25%" class="formth"><b>To</b></td>
								</tr>
									<s:iterator value="list" status="stat">  
					           <tr>
						
					
											<td width="20%" class="sortabletd"><s:property value="empName"/></td>
											<td width="20%" class="sortabletd"><s:property value="appDate"/></td>
											<td width="20%" class="sortabletd"><s:property value="frmDate"/></td>
											<td width="20%" class="sortabletd"><s:property value="tdate"/></td>
											
					          </tr>
									</s:iterator>
								
								
							</table>
							
						</td>
					</tr>				
				</table>				



