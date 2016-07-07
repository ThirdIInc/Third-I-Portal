<%@ taglib prefix="s" uri="/struts-tags"%>

<table width="100%">
	<tr>
		<td colspan="2" width="100%" valign="top">
		<div >
		<table width="100%" class="sortable">
			        <tr>
			        		  <td width="3%"  valign="top" class="formth"><b>Sr No.</b></td>
	                          <td width="13%"  valign="top" class="formth"><b>Requisition Code</b></td>
	                          <td width="13%" valign="top" class="formth"><b>Position</b></td>
	                          <td width="5%" valign="top" class="formth"><b>No.of Openings</b></td>
	                         
	                          <td width="8%"  valign="top" class="formth"><b>View Requisition</b></td>
	                           <td width="15%" valign="top" class="formth"><b>Referral </b></td>
	                        </tr>
	                        
	                      <%
	                      int i=0;
	                      %>
	                 
		                
		                 <s:iterator value="referalList">
								<tr> 
									 <td width="3%" class="sortabletd" align="center"><%=++i %></td>						
									<td width="13%" class="sortabletd"><s:hidden name="requisitionCode"/>
																	<s:property value="requisitionName" /><s:hidden name="vacanDtlCode"/>
								    </td>
									<td width="13%" class="sortabletd"><s:hidden name="positionId"/><s:property value="position" />&nbsp;</td>
									<td width="8%" class="sortabletd" align="right" ><s:property value="noOfVacancies" /> &nbsp;</td>
									
									<td class="sortabletd" width="5%" align="center" >
											<input
											type="button" name="view" class="token"  onclick="window.open('<%=request.getContextPath()%>'+'/recruit/EmployeeRequi_dashLetviewReqDetails1.action?reqCode='+'<s:property value="requisitionCode" />')" 
											value=" View "  />
											
											
										</td>		
										
										<td width="15%" class="sortabletd" align="center" > 
											
							<input type="button" name="view" class="token"  onclick="referCandidate('<s:property value="requisitionCode" />');"
											value="Refer Employee"  />
											</td>
											
							</s:iterator>
		
      				

		</table>
		</div>
		</td>
	</tr>

</table>
