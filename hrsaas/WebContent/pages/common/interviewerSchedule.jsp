<%@ taglib prefix="s" uri="/struts-tags"%>

<table width="100%">
	<tr>
		<td colspan="2" width="100%" valign="top">
		<div >
		<table width="100%" class="sortable">
			        <tr>
			        		  <td width="3%"  valign="top" class="formth" nowrap="nowrap"><b>Sr No.</b></td>
	                          <td width="6%"  valign="top" class="formth"><b>Candidate Name</b></td>
	                          <td width="13%" valign="top" class="formth"><b>Position</b></td>
	                          <td width="13%" valign="top" class="formth"><b>Interview Date</b></td>
	                          <td width="15%" valign="top" class="formth"><b>Interview Time</b></td>
	                          
	                        </tr>
	                        
	                      <%
	                      int i=0;
	                      %>
	                   
		                        
		                 <s:iterator value="intrvwrList">
								<tr>
									 <td width="3%" class="sortabletd" align="center"><%=++i %></td>						
									<td width="13%" class="sortabletd"><s:hidden name="intvwrReqCode"/>
									<s:hidden name="intrvwrCandCode"/>
									<s:hidden name="intvwCode"/>
									<s:property value ="intrvwrCandName"/>&nbsp;</td>
									<td width="13%" class="sortabletd"><s:property value ="intrvwrPos"/>&nbsp;</td>
									<td width="15%" class="sortabletd" align="center"><s:property value="intrvwrDt"/>&nbsp;</td>
									<td width="15%" class="sortabletd" align="center"><s:property value="intvwrTime"/>&nbsp;</td>
									
									
							       
							  </tr>
							
							</s:iterator>
		


		</table>
		</div>
		</td>
	</tr>

</table>
