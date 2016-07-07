<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="UnblockTravelClaim" validate="true" id="paraFrm" theme="simple">



<s:hidden name="appCode" />
	
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">

		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt" colspan="2"><strong
						class="text_head">Unblock Travel Applications </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td>
				<table width="100%" class="formbg">					
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="5%"><b><label class="set"
									name="srNo" id="srNo"
									ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></b>
								</td>								
								
								<td class="formth" width="8%"><b><label class="set"
									name="travelID" id="travelID"
									ondblclick="callShowDiv(this);"><%=label.get("travelID")%></label></b>
								</td>
								
								<td class="formth" width="8%"><b><label class="set"
									name="traReqname" id="traReqname"
									ondblclick="callShowDiv(this);"><%=label.get("traReqname")%></label></b>
								</td>
								
								<td class="formth" width="10%"><b><label class="set"
									name="empguest" id="empguest"
									ondblclick="callShowDiv(this);"><%=label.get("empguest")%></label></b>
								</td>
								
								<td class="formth" width="10%"><b><label class="set"
									name="Traenddate" id="Traenddate"
									ondblclick="callShowDiv(this);"><%=label.get("Traenddate")%></label></b>
								</td>
								
								<td class="formth" width="10%"><b><label class="set"
									name="initiator" id="initiator"
									ondblclick="callShowDiv(this);"><%=label.get("initiator")%></label></b>
								</td>
								
								<td class="formth" width="6%"><b><label class="set"
									name="claimDueDays" id="claimDueDays"
									ondblclick="callShowDiv(this);"><%=label.get("claimDueDays")%></label></b>
								</td>
																
								<td class="formth" width="10%"><b><label class="set"
									name="status" id="status"
									ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
								</td>
								
								<td class="formth" width="20%"><b>Action</b></td>
							</tr>
							
							
							
							<!-- blocked List Iterator Begins -->
							<%
							int i = 1;
							%>
							<%
							int row = 0, count = 0;
							%>
							<s:iterator value="blockedAppList">																
								<s:hidden name="appFor" />
								<s:hidden name="initId" />
								<s:hidden name="empId" />
								<s:hidden name="applnStatus" />							 
								<s:hidden name="appId" />
								<s:hidden name="appCode" />																					
								
								<tr <%if(count%2==0){
						%> class="tableCell1" <%}else{ %>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
								onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									>
									
									<td class="sortableTD"><%=++row%></td>
									<td class="sortableTD"><s:property value="trvlId" />&nbsp;</td>
									<td class="sortableTD"><s:property value="trvlReqName" />&nbsp;</td>
									<td class="sortableTD"><s:property value="empName" />&nbsp;</td>
									<td class="sortableTD" align="center" >
										<s:property	value="appEndDate" />&nbsp;
									</td>
									<td class="sortableTD"><s:property value="initName" />&nbsp;</td>
									<td class="sortableTD" align="center" >
										<s:property	value="claimDueDays" />&nbsp;
									</td>									
									
									<s:if test="%{applnStatus=='_Z'}">
									
									<!-- waiting for unblock request -->
									<td class="sortableTD" align="center" >Pending for UnBlock Approval</td>
									<td class="sortableTD" align="center">
										<input type="button" value=" Approve " class="token" align="left"
										onclick="sendUnblockApprove('<s:property value="appId" />','<s:property value="appCode" />');">										
										<input type="button" value=" Reject " class="token" align="right"
										onclick="sendUnblockReject('<s:property value="appId" />','<s:property value="appCode" />');">
										
										<!--<input type="button" value=" View " class="token" align="right"
										onclick="viewApplication('<s:property value="appId" />','<s:property value="appCode" />');">
									-->
									</s:if>								 
									</td>
								</tr>
								<%
								i++;
								%>
							</s:iterator>
							
							
							<%
							if (i == 1) {
							%>
							<tr align="center">
								<td colspan="9" class="sortableTD" width="100%"><font
									color="red">No Data to display</font></td>
							</tr>
							<%
							}
							%>
							<tr>
								<td align="right" colspan="5"></td>
							</tr>
							<!-- blocked List Iterator End -->
						</table> <!-- End of header table -->							
					   </td>
					</tr>
				</table>
			</td>
		</tr>				
		
		
	</table>	
</s:form>



<script>
	function sendUnblockApprove(applicationid, applicationCode) {
	//alert("applicationid :  "+applicationid);
	//alert("applicationid :  "+applicationCode);      	  
      	  document.getElementById('paraFrm').action='UnblockTravelClaim_approve.action?applicationid='+applicationid+'&applicationCode='+applicationCode;
		  document.getElementById('paraFrm').submit();  
    } 
    
    function sendUnblockReject(applicationid, applicationCode) {      	  
      	  document.getElementById('paraFrm').action='UnblockTravelClaim_reject.action?applicationid='+applicationid+'&applicationCode='+applicationCode;
		  document.getElementById('paraFrm').submit();  
    }   
    
    /*
    function viewApplication( applicationId ){
		alert("applicationId : "+applicationId);
		document.getElementById('paraFrm').action="TravelApplication_viewApplications.action?applicationId="+applicationId;
		document.getElementById('paraFrm').submit();		
	} 
    */
    
    /*
    function viewApplication( applicationId,applicationCode ){
		alert("applicationId : "+applicationId);
		document.getElementById('paraFrm').action="UnblockTravelClaim_viewBlockedApplication.action?applicationId="+applicationId+'&applicationCode='+applicationCode;
		document.getElementById('paraFrm').submit();		
	} 
	*/
	
</script>





