<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="include/javascript/sorttable.js"></script>

<s:form action="LeaveApproval" validate="true" id="paraFrm"
	validate="true" theme="simple">



	<s:hidden name="myPageApproved"  id="myPageApproved"/>
	<s:hidden name="myPageRejected"  id="myPageRejected"/>
	<s:hidden name="myPagePending" />
	<s:hidden name="myPageApprovedPending" id="myPageApprovedPending" />
	<s:hidden name="myPage" id="myPage" />

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave
					Approval </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td colspan="8">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td colspan="8" class="text_head"><strong
						class="forminnerhead">Select Filters </strong></td>
				</tr>
				<tr>
					 
					<td colspan="1" width="14%" nowrap="nowrap"><label
						name="employee" id="employee1"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
				  :</td>
					<td colspan="1" width="19%" nowrap="nowrap"><s:hidden name="searchEmpCode"
						value="%{searchEmpCode}" theme="simple" /> 
						<s:textfield
						name="searchEmpToken" theme="simple" size="10" readonly="true" />
						<s:textfield
						name="searchEmpName" theme="simple" size="35" readonly="true" />
				  </td>
					<td colspan="1" width="5%"><img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'LeaveApproval_f9employee.action');">					</td>

					 
					<td colspan="1" width="62%" nowrap="nowrap"><a href="#"
						onclick="callsearch();">Search</a>  |  <a href="#"
						onclick="callReset();">Reset Filter</a> </td>

				</tr>
				 
			</table>
			</td>
		</tr>


		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<script>
		    function setAction(listType){
		    
		     if(listType=="p"){
		     
		      	document.getElementById('paraFrm').action='LeaveApproval_callstatus.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }if(listType=="a"){
		     
		      	document.getElementById('paraFrm').action='LeaveApproval_getApprovedList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		   if(listType=="r"){
		     
		      	document.getElementById('paraFrm').action='LeaveApproval_getRejectedList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('p')">Pending
					Application</a> | <a href="#" onclick="setAction('a')">Approved
					List</a> | <a href="#" onclick="setAction('r')">Rejected List</a></td>
				</tr>
			</table>
			</td>
		</tr>

		<!--------- PENDING APPLICATION SECTION BEGINS - displaying the pending leave applications ------->

		<s:if test="%{listType == 'pending'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Pending Leave Application</b></td>
					</tr>

					<tr>
						<td>
						<table width="100%">
							<%
								int totalPagePending = 0;
								int PageNoPending = 0;
							%>
								<s:if test="pendingLength">
							<tr>
								<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 			totalPagePending = (Integer) request
 			.getAttribute("totalPage");
 	PageNoPending = (Integer) request.getAttribute("PageNo");
 %> <a href="#"
									onclick="callPage('1', 'F', '<%=totalPagePending%>', 'LeaveApproval_callstatus.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('P', 'P', '<%=totalPagePending%>', 'LeaveApproval_callstatus.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField"
									id="pageNoField" size="3" value="<%=PageNoPending%>"
									maxlength="10"
									onkeypress="callPageText(event, '<%=totalPagePending%>', 'LeaveApproval_callstatus.action');return numbersOnly();" />
								of <%=totalPagePending%> <a href="#"
									onclick="callPage('N', 'N', '<%=totalPagePending%>', 'LeaveApproval_callstatus.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('<%=totalPagePending%>', 'L', '<%=totalPagePending%>', 'LeaveApproval_callstatus.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
							</tr>
					</s:if>
							<tr>
								<td>
								<table width="100%" class="sorttable" border="0">
									<tr>
										<td width="5%" valign="top" class="formth" nowrap="nowrap"><label
											name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
										<td width="15%" valign="top" class="formth"><label
											name="employee.id" id="employee.id"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
										<td width="40%" valign="top" class="formth"><label
											name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
										<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
											name="appdate" id="appdate" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></td>
										<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
											name="details" id="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>

									</tr>
									<s:if test="pendingLength">
									<%
										 
										int cn = PageNoPending * 20 - 20;
									%>

									<s:iterator value="pendingList">

										<tr class="sortableTD">
											<td width="5%" align="left" class="sortableTD"><%=++cn%></td>
											<td class="sortableTD" width="15%"><s:property
												value="tokenNo" /><s:hidden name="leaveAppNo" /><s:hidden
												name="level" /><s:hidden name="empCode" /><s:hidden
												name="pendingStatus" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="empName" /></td>
											<td class="sortableTD" width="20%" nowrap="nowrap"
												align="center"><s:property value="date" /><s:hidden
												name="date" /></td>
											<td class="sortableTD" width="20%" nowrap="nowrap"><input
												type="button" name="view_Details" class="token"
												value=" View / Approve "
												onclick="viewDetails('<s:property value="leaveAppNo"/>','<s:property value="pendingStatus"/>')" />&nbsp;<input
												type="button" name="view_Details" class="token"
												value=" Leave History "
												onclick="viewHistory('<s:property value="empCode"/>')" /></td>


										</tr>
										 
									</s:iterator>
									</s:if>

									 <s:if test="pendingLength"></s:if>
									 <s:else>
									<tr align="center">
										<td colspan="6" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
								 </s:else>

								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

			<!--------- PENDING APPLICATION SECTION ENDS - displaying the pending leave applications ------->

			<!--------- Pending Leave Cancellation Application STARTS - displaying the Pending Leave Cancellation Application ------->

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Pending Leave Cancellation Application</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" border="0">


							<%
								int totalPageAppPending = 0;
								int PageNoAppPending = 0;
							%>
							<s:if test="approvedPendingLength">
							<tr>
								<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 			totalPageAppPending = (Integer) request
 			.getAttribute("totalPage1");
 	PageNoAppPending = (Integer) request
 			.getAttribute("PageNo1");
 %> <a href="#"
									onclick="callPageApproveCancel('1', 'F', '<%=totalPageAppPending%>', 'LeaveApproval_callstatus.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageApproveCancel('P', 'P', '<%=totalPageAppPending%>', 'LeaveApproval_callstatus.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField1"
									id="pageNoField1" size="3" value="<%=PageNoAppPending%>"
									maxlength="10"
									onkeypress="callPageTextApproveCancel(event, '<%=totalPageAppPending%>', 'LeaveApproval_callstatus.action');return numbersOnly();" />
								of <%=totalPageAppPending%> <a href="#"
									onclick="callPageApproveCancel('N', 'N', '<%=totalPageAppPending%>', 'LeaveApproval_callstatus.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageApproveCancel('<%=totalPageAppPending%>', 'L', '<%=totalPageAppPending%>', 'LeaveApproval_callstatus.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
							</tr>
</s:if>
							<tr>
								<td>
								<table width="100%" class="sorttable" border="0">
									<tr>

										<td width="5%" valign="top" class="formth" nowrap="nowrap"><label
											name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
										<td width="15%" valign="top" class="formth"><label
											name="employee.id" id="employee.id"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
										<td width="40%" valign="top" class="formth"><label
											name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
										<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
											name="appdate" id="appdate" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></td>
										<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
											name="details" id="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>

									</tr>

										<s:if test="approvedPendingLength"> 
										<%
										 
										int cn1 = PageNoAppPending * 20 - 20;
									%>

									<s:iterator value="penAppCanList">
										<tr>
											<td class="sortableTD" width="5%" align="center"><%=++cn1%></td>
											<td class="sortableTD" width="15%"><s:hidden
												name="penAppCanEmpId" /><s:hidden name="penAppCanLevAppNo" />
											<s:hidden name="penAppCanLevel" /> <s:hidden
												name="penAppCanStatus" /> <s:property
												value="penAppCanEmpToken" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="penAppCanEmpName" /></td>
											<td class="sortableTD" width="20%" align="center"><s:property
												value="penAppCanAppDate" /></td>
											<td class="sortableTD" width="20%" nowrap="nowrap"><input
												type="button" name="view_Details" class="token"
												value=" View / Approve "
												onclick="viewDetails('<s:property value="penAppCanLevAppNo"/>','<s:property value="penAppCanStatus"/>')" />&nbsp;
											<input type="button" name="view_Details" class="token"
												value=" Leave History "
												onclick="viewHistory('<s:property value="penAppCanEmpId"/>')" /></td>
										</tr>
										 
									</s:iterator>

									</s:if>

								 	<s:if test="approvedPendingLength"></s:if>
								 	<s:else>
									<tr align="center">
										<td colspan="6" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
									  </s:else>
									
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		
		<!--------- Pending Leave Cancellation Application ENDS  ------->

		<!--------- APPROVED LEAVE APPLICATION SECTION STARTS  ------->

		<s:if test="%{listType == 'approved'}">

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Approved Applications List</b></td>
					</tr>
						<%
								int totalPageApproved = 0;
								int PageNoApproved = 0;
							%>
								<s:if test="approvedLength">
							<tr>
								<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
								totalPageApproved = (Integer) request
 			.getAttribute("totalPageApproved");
								PageNoApproved = (Integer) request.getAttribute("PageNoApproved");
 %> <a href="#"
									onclick="callPageApproved('1', 'F', '<%=totalPageApproved%>', 'LeaveApproval_getApprovedList.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageApproved('P', 'P', '<%=totalPageApproved%>', 'LeaveApproval_getApprovedList.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField2"
									id="pageNoField2" size="3" value="<%=PageNoApproved%>"
									maxlength="10"
									onkeypress="callPageTextApprove(event, '<%=totalPageApproved%>', 'LeaveApproval_getApprovedList.action');return numbersOnly();" />
								of <%=totalPageApproved%> <a href="#"
									onclick="callPageApproved('N', 'N', '<%=totalPageApproved%>', 'LeaveApproval_getApprovedList.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageApproved('<%=totalPageApproved%>', 'L', '<%=totalPageApproved%>', 'LeaveApproval_getApprovedList.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
							</tr>
							</s:if>
					<tr>
						<td>
						<table width="100%">
							
							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno4" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id4"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee.name" id="employee.name4"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate4" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>
									<s:if test="approvedLength">
 								 	<%
									 
										int cn2 = PageNoApproved * 20 - 20;
									%>

									<s:iterator value="appList">
										<tr>
											<td class="sortableTD" width="5%" align="center"><%=++cn2%></td>
											<td class="sortableTD" width="15%"><s:hidden
												name="appEmpId" /><s:hidden name="appLeaveAppNo" /><s:hidden
												name="appLevel" /> <s:hidden name="appStatus" /> <s:property
												value="appEmpToken" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="appEmpName" /></td>
											<td class="sortableTD" width="20%" align="center"><s:property
												value="applevAppDate" /></td>
											<td class="sortableTD" width="20%" nowrap="nowrap">
											
											<input
												type="button" name="view_Details" class="token"
												value=" Details "
												onclick="viewDetails('<s:property value="appLeaveAppNo"/>','<s:property value="appStatus"/>')" />&nbsp;<input
												type="button" name="view_Details" class="token"
												value=" Leave History "
												onclick="viewHistory('<s:property value="appEmpId"/>')" />
											
											</td>
										</tr>
									 
									</s:iterator>
									 </s:if>
								 <s:if test="approvedLength"></s:if>
								 <s:else>
									<tr align="center">
										<td colspan="6" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
								</s:else>


								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

		</s:if>

		<!--------- APPROVED LEAVE APPLICATION SECTION ENDS  ------->

		<!--------- REJECTED LEAVE APPLICATION SECTION STARTS  ------->
		<s:if test="%{listType == 'rejected'}">
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Rejected Applications List</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%">
						<% int totalPageRejected = 0; int PageNoRejected = 0; %>
						 <s:if test="rejectedLength">
						<tr>
					<td id="ctrlShow" width="100%" align="right">
						<b>Page:</b>
							<%	 totalPageRejected = (Integer) request.getAttribute("totalPageRejected");
							PageNoRejected = (Integer) request.getAttribute("PageNoRejected");
						%>
						<a href="#" onclick="callPageRejected('1', 'F', '<%=totalPageRejected%>', 'LeaveApplication_getRejectedList.action');">
							<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
						</a>&nbsp;
						<a href="#" onclick="callPageRejected('P', 'P', '<%=totalPageRejected%>', 'LeaveApplication_getRejectedList.action');" >
							<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
						</a> 
						<input type="text" name="pageNoField3" id="pageNoField3" size="3" value="<%=PageNoRejected%>" maxlength="10"
						onkeypress="callPageTextReject(event, '<%=totalPageRejected%>', 'LeaveApplication_getRejectedList.action');return numbersOnly();" /> of <%=totalPageRejected%>
						<a href="#" onclick="callPageRejected('N', 'N', '<%=totalPageRejected%>', 'LeaveApplication_getRejectedList.action')" >
							<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
						</a>&nbsp;
						<a href="#" onclick="callPageRejected('<%=totalPageRejected%>', 'L', '<%=totalPageRejected%>', 'LeaveApplication_getRejectedList.action');" >
							<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
						</a>
					
					</td>
					</tr></s:if>

							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno6" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id6"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee.name" id="employee.name6"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate6" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>
							<s:if test="rejectedLength">
										<%
										 
										int cn3 = PageNoRejected * 20 - 20;
									%>
									 
									<s:iterator value="rejList">
										<tr>
											<td class="sortableTD" width="5%" align="center"><%=++cn3%></td>
											<td class="sortableTD" width="15%"><s:hidden
												name="rejEmpId" /><s:hidden name="rejLeaveAppNo" /><s:hidden
												name="rejLevel" /> <s:hidden name="rejStatus" /> <s:property
												value="rejEmpToken" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="rejEmpName" /></td>
											<td class="sortableTD" width="20%" align="center"><s:property
												value="rejAppDate" /></td>
											<td class="sortableTD" width="20%" nowrap="nowrap">
											
											
											<input
												type="button" name="view_Details" class="token"
												value=" Details "
												onclick="viewDetails('<s:property value="rejLeaveAppNo"/>','<s:property value="rejStatus"/>')" />&nbsp;<input
												type="button" name="view_Details" class="token"
												value=" Leave History "
												onclick="viewHistory('<s:property value="rejEmpId"/>')" />
											
											
											</td>
										</tr>
										 
									</s:iterator>
									 
									 </s:if>

								<s:if test="rejectedLength"></s:if>
								<s:else>
									<tr align="center">
										<td colspan="6" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
								</s:else>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

		</s:if>
	</table>

<s:hidden name="eId" />
		<s:hidden name="leaveApplication.isApprovalClick" />
		
		<s:hidden name="searchStatus" />

</s:form>


<script>

 function callsearch() 
 {
  		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'LeaveApproval_search.action';
		document.getElementById('paraFrm').submit();
 }
     
     function callReset()
     {
     		document.getElementById('paraFrm_searchEmpCode').value='';
     		document.getElementById('paraFrm_searchEmpName').value='';
     		document.getElementById('paraFrm_searchEmpToken').value='';
     		
     		document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'LeaveApproval_search.action';
			document.getElementById('paraFrm').submit();
     }
		    
		 function viewDetails(leaveAppNo,status)
		 {
		 
		// alert("leaveAppNo"+leaveAppNo);
		// alert("status"+status);
		 
	    //var wind = window.open('','wind','width=800,height=500,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		//document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = 'LeaveApplication_retriveForApproval.action?leaveApplicationNo='+leaveAppNo+'&applicationStatus='+status;
		document.getElementById('paraFrm_leaveApplication_isApprovalClick').value ='true';
		document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target = "_self";
		}
	
	 function viewHistory(empCode){
		var wind = window.open('','wind','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
	   document.getElementById('paraFrm').action = 'LeaveApplication_retriveForHistory.action?employeeNo='+empCode;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "_self";
	}
	
	
	 
function callPageApproveCancel(id, pageImg, totalPageHid, action) {		
		
		pageNo = document.getElementById('pageNoField1').value;	
		actPage = document.getElementById('myPageApprovedPending').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField1').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField1').value = actPage;
			    document.getElementById('pageNoField1').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField1').value=actPage;
			    document.getElementById('pageNoField1').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField1').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField1').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField1').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageApprovedPending').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 
	}
	

	 
function callPageTextApproveCancel(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField1').value;
		 	var actPage = document.getElementById('myPageApprovedPending').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField1').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField1').focus();
		     document.getElementById('pageNoField1').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField1').focus();
		     document.getElementById('pageNoField1').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField1').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageApprovedPending').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}
	
	// FOR APPROVED APPLICATIONS LIST
	
	function callPageApproved(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoField2').value;	
		actPage = document.getElementById('myPageApproved').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField2').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField2').value = actPage;
			    document.getElementById('pageNoField2').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField2').value=actPage;
			    document.getElementById('pageNoField2').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField2').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField2').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField2').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageApproved').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	
	function callPageTextApprove(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField2').value;
		 	var actPage = document.getElementById('myPageApproved').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField2').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField2').focus();
		     document.getElementById('pageNoField2').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField2').focus();
		     document.getElementById('pageNoField2').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField2').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageApproved').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
		    // REJECTED APPLICATIONS LIST
	
	function callPageRejected(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoField3').value;	
		actPage = document.getElementById('myPageRejected').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField3').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField3').value = actPage;
			    document.getElementById('pageNoField3').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField3').value=actPage;
			    document.getElementById('pageNoField3').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField3').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField3').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField3').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageRejected').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	
	
	function callPageTextReject(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField3').value;
		 	var actPage = document.getElementById('myPageRejected').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField3').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField3').focus();
		     document.getElementById('pageNoField3').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField3').focus();
		     document.getElementById('pageNoField3').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField3').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageRejected').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
		    
</script>










		