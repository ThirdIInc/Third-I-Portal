<!-- Bhushan Dasare -->
<!-- Mar 9, 2011 -->
<!-- Changes in List by Nilesh D on 24th May -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="ApplicationSecurityRequestApproval"
	name="ApplicationSecurityRequestApproval" id="paraFrm" validate="true"
	target="main" theme="simple">
	<s:hidden name="listType" />
   <s:hidden name="hiddenSearchId" /> 
   	<s:hidden name="appSecStatus"/>	


	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="92%" class="txt"><strong class="text_head">Application 
					Security Request Approval </strong></td>
					<td width="4%" valign="middle" align="right" class="txt"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%"><a href="#" onclick="callPending();">Pending
			Applications</a> | <a href="#" onclick="callApproved();">Approved
			Applications</a> | <a href="#" onclick="callRejected();">Rejected
			Applications</a></td>
		</tr>
		<tr id="pending">
			<td>
			<table width="100%">
				<tr>
					<td width="100%">
					<table width="100%" class="formbg" border="0" cellpadding="1" cellspacing="">
						
						<!-- Added by Nilesh Dhandare on 31st May 2011 for Pending Application List Paging -->
						<tr>
							<td width="60%" class="formtxt"><strong>Pending
							Applications</strong> </td>
							
							<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForPending" id="pageForPending" />
									
									<s:hidden name="trackingNumIterator" /> <s:hidden name="managerName" /><s:hidden name="requesterTokenIterator" />
									<s:hidden name="requesterNameIterator" /><s:hidden name="applicationDateIterator" /> 
								
									<%
										int totalPageForPending = 0;
										int pageNoForPending = 0;
										try {
											totalPageForPending = (Integer) request
											.getAttribute("totalPageForPending");
											pageNoForPending = (Integer) request
											.getAttribute("pageNoForPending");
										} catch (Exception e) {
										}
									%>
									<%
									if (totalPageForPending > 0) {
									%>
									
										
									<td width="10%"><input type="button" class="search" onclick="searchFun('P');" theme="simple"
										value="  Search" /></td>
											
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<a href="#"
										onclick="callPagePending('1', 'F', '<%=totalPageForPending%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPagePending('P', 'P', '<%=totalPageForPending%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNoForPending%>"
										maxlength="10"
										onkeypress="callPendingPageText(event, '<%=totalPageForPending%>', 
														'ApplicationSecurityRequestApproval_callPage.action');
														return numbersOnly();" />
									of <%=totalPageForPending%> <a href="#"
										onclick="callPagePending('N', 'N', '<%=totalPageForPending%>', 
														'ApplicationSecurityRequestApproval_callPage.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPagePending('<%=totalPageForPending%>', 'L', '<%=totalPageForPending%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
									<%
									}
									%>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="2">
							<table width="100%" class="sortable">
								<tr class="td_bottom_border">
									<td width="5%" valign="top" class="formth"><label
										id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
									</td>
									<td width="25%" valign="top" class="formth"><label
										id="tracking.no" name="tracking.no"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="15%" valign="top" class="formth"><label
										id="manager.f9.name" name="manager.f9.name"
										ondblclick="callShowDiv(this);"><%=label.get("manager.f9.name")%></label>
									</td>
                                       <td width="10%" valign="top" class="formth"><label
										id="token.name" name="token.name"
										ondblclick="callShowDiv(this);"><%=label.get("token.name")%></label>
									</td>
                                       
									<td width="15%" valign="top" class="formth"><label
										id="initiator.employee" name="initiator.employee"
										ondblclick="callShowDiv(this);"><%=label.get("initiator.employee")%></label>
									</td>
									
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="requested.type" name="requested.type"
										ondblclick="callShowDiv(this);"><%=label.get("requested.type")%></label>
									</td>
									
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="requested.date" name="requested.date"
										ondblclick="callShowDiv(this);"><%=label.get("requested.date")%></label>
									</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="details" name="details"
										ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
									</td>
								</tr>
								<s:if test="pendingListLength">
								
								<%
										int count4 = 0;
									%>
									<%!int d4 = 0;%>
									<%
										int i4 = pageNoForPending * 20 - 20;									
									%>
								<s:iterator value="pendingList">
									<tr>
										<td class="sortableTD"><%=++i4%></td>
										<td class="sortableTD"><s:hidden name="applnSecReqId" /><s:hidden name ="applicationStatus" />
										<s:property value="trackingNo" /></td>
										<td class="sortableTD"><s:property value="mgrName" /></td>
										<td class="sortableTD"><s:property value="empToken" />
										</td>
										<td class="sortableTD"><s:property value="empIniName" />
										</td>
										<td class="sortableTD"><s:property value="requestTypeItr" /> </td>
										<td class="sortableTD" align="center"><s:property
											value="requestDate" /></td>
										<td id="ctrlShow" class="sortableTD" align="center"><input
											type="button" class="token"
											value="View / Approve Application"
											onclick="viewDetails('<s:property value="applnSecReqId"/>', 'P')" />
										</td>
									</tr>
								</s:iterator>
								<%
										d4 = i4;
									%>
								</s:if>
								<s:else>
									<td align="center" colspan="8" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
								</s:else>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			<!-- Pending Appl Completed -->
			
			
				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="60%" class="formtxt"><strong>Pending
							Cancellation Applications</strong></td>
							<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForPendingCancel" id="pageForPendingCancel" />
																
									<s:hidden name="trackingNumIterator" /> <s:hidden name="managerName" /><s:hidden name="requesterTokenIterator" />
									<s:hidden name="requesterNameIterator" /><s:hidden name="applicationDateIterator" />
									<%
										int totalPageForPendingCancel = 0;
										int pageNoForPendingCancel = 0;
										try {
											totalPageForPendingCancel = (Integer) request
											.getAttribute("totalPageForPendingCancel");
											pageNoForPendingCancel = (Integer) request
											.getAttribute("pageNoForPendingCancel");
										} catch (Exception e) {
										}
									%>
									<%
									if (totalPageForPendingCancel > 0) {
									%>
									<td width="10%"><input type="button" class="search" onclick="searchFun('C');" theme="simple"
										value="  Search" /></td>
									
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<a href="#"
										onclick="callPagePendingCancel('1', 'F', '<%=totalPageForPendingCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPagePendingCancel('P', 'P', '<%=totalPageForPendingCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoFieldPendingCancel" id="pageNoFieldPendingCancel" size="3"
										value="<%=pageNoForPendingCancel%>" maxlength="10"
										onkeypress="callPagePendingCancelText(event, '<%=totalPageForPendingCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action');
														return numbersOnly();" />
									of <%=totalPageForPendingCancel%> <a href="#"
										onclick="callPagePendingCancel('N', 'N', '<%=totalPageForPendingCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPagePendingCancel('<%=totalPageForPendingCancel%>', 'L', '<%=totalPageForPendingCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
									<%
									}
									%>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="2">
							<table width="100%" class="sortable">
								<tr class="td_bottom_border">
									<td width="5%" valign="top" class="formth"><label
										id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
									</td>
									<td width="25%" valign="top" class="formth"><label
										id="tracking.no" name="tracking.no"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="15%" valign="top" class="formth"><label
										id="manager.f9.name" name="manager.f9.name"
										ondblclick="callShowDiv(this);"><%=label.get("manager.f9.name")%></label>
									</td>
									 <td width="10%" valign="top" class="formth"><label
										id="token.name" name="token.name"
										ondblclick="callShowDiv(this);"><%=label.get("token.name")%></label>
									</td>

									<td width="15%" valign="top" class="formth"><label
										id="initiator.employee" name="initiator.employee"
										ondblclick="callShowDiv(this);"><%=label.get("initiator.employee")%></label>
									</td>
									
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="requested.type" name="requested.type"
										ondblclick="callShowDiv(this);"><%=label.get("requested.type")%></label>
									</td>
									
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="requested.date" name="requested.date"
										ondblclick="callShowDiv(this);"><%=label.get("requested.date")%></label>
									</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="details" name="details"
										ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
									</td>
								</tr>
								<s:if test="pendingCancelListLength">
								
								<%
										int count5 = 0;
									%>
									<%!int d5 = 0;%>
									<%
										int i5 = pageNoForPendingCancel * 20 - 20;									
									%>
								<s:iterator value="pendingCancelList">
									<tr>
										<td class="sortableTD"><%=++i5%></td>
										<td class="sortableTD"><s:hidden name="applnSecReqId" /><s:hidden name ="applicationStatus" />
										<s:property value="trackingNo" /></td>
										<td class="sortableTD"><s:property value="mgrName" /></td>
										<td class="sortableTD"><s:property value="empToken" />
										</td>
										<td class="sortableTD"><s:property value="empIniName" />
										</td>
										<td class="sortableTD"><s:property value="requestTypeItr" /> </td>
										<td class="sortableTD" align="center"><s:property
											value="requestDate" /></td>
										<td id="ctrlShow" class="sortableTD" align="center"><input
											type="button" class="token"
											value="View / Approve Application"
											onclick="viewDetails('<s:property value="applnSecReqId"/>', 'C')" />
										</td>
									</tr>
								</s:iterator>
								<%
										d5 = i5;
									%>
								</s:if>
								<s:else>
									<td align="center" colspan="8" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
								</s:else>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		<!-- Pending Cancel Application Completed -->	
			
		</tr>
		<tr id="approved">
			<td>
			<table width="100%">
				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="60%" class="formtxt"><strong>Approved
							Applications </strong></td>
							<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForApprove" id="pageForApprove" />
									
									<s:hidden name="trackingNumIterator" /> <s:hidden name="managerName" /><s:hidden name="requesterTokenIterator" />
									<s:hidden name="requesterNameIterator" /><s:hidden name="applicationDateIterator" /> 
									
									
									<%
										int totalPageForApprove = 0;
										int pageNoForApprove = 0;
										try {
											totalPageForApprove = (Integer) request
											.getAttribute("totalPageForApprove");
											pageNoForApprove = (Integer) request
											.getAttribute("pageNoForApprove");
										} catch (Exception e) {
										}
									%>
									<%
									if (totalPageForApprove > 0) {
									%>
									<td width="10%"><input type="button" class="search" onclick="searchFun('A');" theme="simple"
										value="  Search" /></td>
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<a href="#"
										onclick="callPageApproved('1', 'F', '<%=totalPageForApprove%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageApproved('P', 'P', '<%=totalPageForApprove%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoFieldApprove" id="pageNoFieldApprove" size="3"
										value="<%=pageNoForApprove%>" maxlength="10"
										onkeypress="callPageApprovedText(event, '<%=totalPageForApprove%>', 
														'ApplicationSecurityRequestApproval_callPage.action');
														return numbersOnly();" />
									of <%=totalPageForApprove%> <a href="#"
										onclick="callPageApproved('N', 'N', '<%=totalPageForApprove%>', 
														'ApplicationSecurityRequestApproval_callPage.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageApproved('<%=totalPageForApprove%>', 'L', '<%=totalPageForApprove%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
									<%
									}
									%>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="2">
							<table width="100%" class="sortable">
								<tr class="td_bottom_border">
									<td width="5%" valign="top" class="formth"><label
										id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
									</td>
									<td width="25%" valign="top" class="formth"><label
										id="tracking.no" name="tracking.no"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="15%" valign="top" class="formth"><label
										id="manager.f9.name" name="manager.f9.name"
										ondblclick="callShowDiv(this);"><%=label.get("manager.f9.name")%></label>
									</td>
									 <td width="10%" valign="top" class="formth"><label
										id="token.name" name="token.name"
										ondblclick="callShowDiv(this);"><%=label.get("token.name")%></label>
									</td>

									<td width="15%" valign="top" class="formth"><label
										id="initiator.employee" name="initiator.employee"
										ondblclick="callShowDiv(this);"><%=label.get("initiator.employee")%></label>
									</td>
									
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="requested.type" name="requested.type"
										ondblclick="callShowDiv(this);"><%=label.get("requested.type")%></label>
									</td>
									
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="requested.date" name="requested.date"
										ondblclick="callShowDiv(this);"><%=label.get("requested.date")%></label>
									</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="details" name="details"
										ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
									</td>
								</tr>
								<s:if test="approvedListLength">
								
								<%
										int count6 = 0;
									%>
									<%!int d6 = 0;%>
									<%
										int i6 = pageNoForApprove * 20 - 20;									
									%>
								<s:iterator value="approveList">
									<tr>
										<td class="sortableTD"><%=++i6%></td>
										<td class="sortableTD"><s:hidden name="applnSecReqId" /><s:hidden name ="applicationStatus" />
										<s:property value="trackingNo" /></td>
										<td class="sortableTD"><s:property value="mgrName" /></td>
										<td class="sortableTD"><s:property value="empToken" />
										</td>
										<td class="sortableTD"><s:property value="empIniName" />
										</td>
										<td class="sortableTD"><s:property value="requestTypeItr" /> </td>
										<td class="sortableTD" align="center"><s:property
											value="requestDate" /></td>
										<td id="ctrlShow" class="sortableTD" align="center"><input
											type="button" class="token" value="View Application"
											onclick="viewDetails('<s:property value="applnSecReqId"/>', 'A')" />
										</td>
									</tr>
								</s:iterator>
								<%
										d6 = i6;
									%>
								</s:if>
								<s:else>
									<td align="center" colspan="8" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
								</s:else>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
<!-- Paging for Approved Application Completed -->


				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="60%" class="formtxt"><strong>Cancellation
							Approved Applications</strong></td>
							<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForApproveCancel" id="pageForApproveCancel" />
									
									<s:hidden name="trackingNumIterator" /> <s:hidden name="managerName" /><s:hidden name="requesterTokenIterator" />
									<s:hidden name="requesterNameIterator" /><s:hidden name="applicationDateIterator" /> 
									<%
										int totalPageForApproveCancel = 0;
										int pageNoForApproveCancel = 0;
										try {
											totalPageForApproveCancel = (Integer) request
											.getAttribute("totalPageForApproveCancel");
											pageNoForApproveCancel = (Integer) request
											.getAttribute("pageNoForApproveCancel");
										} catch (Exception e) {
										}
									%>
									<%
									if (totalPageForApproveCancel > 0) {
									%>
									<td width="10%"><input type="button" class="search" onclick="searchFun('X');" theme="simple"
										value="  Search" /></td>
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<a href="#"
										onclick="callApproveCancelPage('1', 'F', '<%=totalPageForApproveCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callApproveCancelPage('P', 'P', '<%=totalPageForApproveCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoFieldApproveCancel" id="pageNoFieldApproveCancel" size="3"
										value="<%=pageNoForApproveCancel%>" maxlength="10"
										onkeypress="callApproveCancelPageText(event, '<%=totalPageForApproveCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action');
														return numbersOnly();" />
									of <%=totalPageForApproveCancel%> <a href="#"
										onclick="callApproveCancelPage('N', 'N', '<%=totalPageForApproveCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callApproveCancelPage('<%=totalPageForApproveCancel%>', 'L', '<%=totalPageForApproveCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
									<%
									}
									%>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="2">
							<table width="100%" class="sortable">
								<tr class="td_bottom_border">
									<td width="5%" valign="top" class="formth"><label
										id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
									</td>
									<td width="25%" valign="top" class="formth"><label
										id="tracking.no" name="tracking.no"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="15%" valign="top" class="formth"><label
										id="manager.f9.name" name="manager.f9.name"
										ondblclick="callShowDiv(this);"><%=label.get("manager.f9.name")%></label>
									</td>
									<td width="10%" valign="top" class="formth"><label
										id="token.name" name="token.name"
										ondblclick="callShowDiv(this);"><%=label.get("token.name")%></label>
									</td>

									<td width="15%" valign="top" class="formth"><label
										id="initiator.employee" name="initiator.employee"
										ondblclick="callShowDiv(this);"><%=label.get("initiator.employee")%></label>
									</td>
									
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="requested.type" name="requested.type"
										ondblclick="callShowDiv(this);"><%=label.get("requested.type")%></label>
									</td>
									
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="requested.date" name="requested.date"
										ondblclick="callShowDiv(this);"><%=label.get("requested.date")%></label>
									</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="details" name="details"
										ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
									</td>
								</tr>
								<s:if test="approvedCancelListLength">
								
								<%
										int count7 = 0;
									%>
									<%!int d7 = 0;%>
									<%
										int i7 = pageNoForApproveCancel * 20 - 20;									
									%>
								<s:iterator value="approveCancelList">
									<tr>
										<td class="sortableTD"><%=++i7%></td>
										<td class="sortableTD"><s:hidden name="applnSecReqId" /><s:hidden name ="applicationStatus" />
										<s:property value="trackingNo" /></td>
										<td class="sortableTD"><s:property value="mgrName" /></td>
										<td class="sortableTD"><s:property value="empToken" />
										</td>
										<td class="sortableTD"><s:property value="empIniName" />
										</td>
										<td class="sortableTD"><s:property value="requestTypeItr" /> </td>
										<td class="sortableTD" align="center"><s:property
											value="requestDate" /></td>
										<td id="ctrlShow" class="sortableTD" align="center"><input
											type="button" class="token" value="View Application"
											onclick="viewDetails('<s:property value="applnSecReqId"/>', 'X')" />
										</td>
									</tr>
								</s:iterator>
								<%
										d7 = i7;
									%>
								</s:if>
								<s:else>
									<td align="center" colspan="8" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
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
	<!-- Approved Cancel Application Completed -->	
		
		<tr id="rejected">
			<td>
			<table width="100%">
				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="60%" class="formtxt"><strong>Rejected
							Applications</strong></td>
							<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForRejected" id="pageForRejected" />
								
									<s:hidden name="trackingNumIterator" /> <s:hidden name="managerName" /><s:hidden name="requesterTokenIterator" />
									<s:hidden name="requesterNameIterator" /><s:hidden name="applicationDateIterator" /> 
									
									<%
										int totalPageForRejected = 0;
										int pageNoForRejected = 0;
										try {
											totalPageForRejected = (Integer) request
											.getAttribute("totalPageForRejected");
											pageNoForRejected = (Integer) request
											.getAttribute("pageNoForRejected");
										} catch (Exception e) {
										}
									%>
									<%
									if (totalPageForRejected > 0) {
									%>
									<td width="10%"><input type="button" class="search" onclick="searchFun('R');" theme="simple"
										value="  Search" /></td>
									
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<a href="#"
										onclick="callRejectedPage('1', 'F', '<%=totalPageForRejected%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callRejectedPage('P', 'P', '<%=totalPageForRejected%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoFieldRejected" id="pageNoFieldRejected" size="3"
										value="<%=pageNoForRejected%>" maxlength="10"
										onkeypress="callRejectedPageText(event, '<%=totalPageForRejected%>', 
														'ApplicationSecurityRequestApproval_callPage.action');
														return numbersOnly();" />
									of <%=totalPageForRejected%> <a href="#"
										onclick="callRejectedPage('N', 'N', '<%=totalPageForRejected%>', 
														'ApplicationSecurityRequestApproval_callPage.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callRejectedPage('<%=totalPageForRejected%>', 'L', '<%=totalPageForRejected%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
									<%
									}
									%>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="2">
							<table width="100%" class="sortable">
								<tr class="td_bottom_border">
									<td width="5%" valign="top" class="formth"><label
										id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
									</td>
									<td width="25%" valign="top" class="formth"><label
										id="tracking.no" name="tracking.no"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="15%" valign="top" class="formth"><label
										id="manager.f9.name" name="manager.f9.name"
										ondblclick="callShowDiv(this);"><%=label.get("manager.f9.name")%></label>
									</td>
									 <td width="10%" valign="top" class="formth"><label
										id="token.name" name="token.name"
										ondblclick="callShowDiv(this);"><%=label.get("token.name")%></label>
									</td>

									<td width="15%" valign="top" class="formth"><label
										id="initiator.employee" name="initiator.employee"
										ondblclick="callShowDiv(this);"><%=label.get("initiator.employee")%></label>
									</td>
									
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="requested.type" name="requested.type"
										ondblclick="callShowDiv(this);"><%=label.get("requested.type")%></label>
									</td>
									
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="requested.date" name="requested.date"
										ondblclick="callShowDiv(this);"><%=label.get("requested.date")%></label>
									</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="details" name="details"
										ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
									</td>
								</tr>
								<s:if test="rejectedListLength">
								
								<%
										int count8 = 0;
									%>
									<%!int d8 = 0;%>
									<%
										int i8= pageNoForRejected * 20 - 20;									
									%>
								<s:iterator value="rejectedList">
									<tr>
										<td class="sortableTD"><%=++i8%></td>
										<td class="sortableTD"><s:hidden name="applnSecReqId" /><s:hidden name ="applicationStatus" />
										<s:property value="trackingNo" /></td>
										<td class="sortableTD"><s:property value="mgrName" /></td>
										<td class="sortableTD"><s:property value="empToken" />
										</td>
										<td class="sortableTD"><s:property value="empIniName" />
										</td>
										<td class="sortableTD"><s:property value="requestTypeItr" /> </td>
										<td class="sortableTD" align="center"><s:property
											value="requestDate" /></td>
										<td id="ctrlShow" class="sortableTD" align="center"><input
											type="button" class="token" value="View Application"
											onclick="viewDetails('<s:property value="applnSecReqId"/>', 'R')" />
										</td>
									</tr>
								</s:iterator>
								<%
										d8 = i8;
									%>
								</s:if>
								<s:else>
									<td align="center" colspan="8" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
								</s:else>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			<!-- Rejected Application Paging Completed -->	
				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="60%" class="formtxt"><strong>Cancellation
							Rejected Applications</strong></td>
							<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForRejectedCancel"
										id="pageForRejectedCancel" />
									
									
									<s:hidden name="trackingNumIterator" /> <s:hidden name="managerName" /><s:hidden name="requesterTokenIterator" />
									<s:hidden name="requesterNameIterator" /><s:hidden name="applicationDateIterator" /> 
									
									<%
										int totalPageForRejectedCancel = 0;
										int pageNoForRejectedCancel = 0;
										try {
											totalPageForRejectedCancel = (Integer) request
											.getAttribute("totalPageForRejectedCancel");
											pageNoForRejectedCancel = (Integer) request
											.getAttribute("pageNoForRejectedCancel");
										} catch (Exception e) {
										}
									%>
									<%
									if (totalPageForRejectedCancel > 0) {
									%>
									<td width="10%"><input type="button" class="search" onclick="searchFun('Z');" theme="simple"
										value="  Search" /></td>
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<a href="#"
										onclick="callRejectedCancelPage('1', 'F', '<%=totalPageForRejectedCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callRejectedCancelPage('P', 'P', '<%=totalPageForRejectedCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoFieldRejectedCancel" id="pageNoFieldRejectedCancel" size="3"
										value="<%=pageNoForRejectedCancel%>" maxlength="10"
										onkeypress="callRejectedCancelPageText(event, '<%=totalPageForRejectedCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action');
														return numbersOnly();" />
									of <%=totalPageForRejectedCancel%> <a href="#"
										onclick="callRejectedCancelPage('N', 'N', '<%=totalPageForRejectedCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callRejectedCancelPage('<%=totalPageForRejectedCancel%>', 'L', '<%=totalPageForRejectedCancel%>', 
														'ApplicationSecurityRequestApproval_callPage.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
									<%
									}
									%>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="2">
							<table width="100%" class="sortable">
								<tr class="td_bottom_border">
									<td width="5%" valign="top" class="formth"><label
										id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
									</td>
									<td width="25%" valign="top" class="formth"><label
										id="tracking.no" name="tracking.no"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="15%" valign="top" class="formth"><label
										id="manager.f9.name" name="manager.f9.name"
										ondblclick="callShowDiv(this);"><%=label.get("manager.f9.name")%></label>
									</td>
									<td width="10%" valign="top" class="formth"><label
										id="token.name" name="token.name"
										ondblclick="callShowDiv(this);"><%=label.get("token.name")%></label>
									</td>

									<td width="15%" valign="top" class="formth"><label
										id="initiator.employee" name="initiator.employee"
										ondblclick="callShowDiv(this);"><%=label.get("initiator.employee")%></label>
									</td>
									
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="requested.type" name="requested.type"
										ondblclick="callShowDiv(this);"><%=label.get("requested.type")%></label>
									</td>
									
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="requested.date" name="requested.date"
										ondblclick="callShowDiv(this);"><%=label.get("requested.date")%></label>
									</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<label id="details" name="details"
										ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
									</td>
								</tr>
								<s:if test="rejectedCancelListLength">
								
								<%
										int count9 = 0;
									%>
									<%!int d9 = 0;%>
									<%
										int i9= pageNoForRejectedCancel * 20 - 20;									
									%>
								
								<s:iterator value="rejectedCancelList">
									<tr>
										<td class="sortableTD"><%=++i9%></td>
										<td class="sortableTD"><s:hidden name="applnSecReqId" /><s:hidden name ="applicationStatus" />
										<s:property value="trackingNo" /></td>
										<td class="sortableTD"><s:property value="mgrName" /></td>
										<td class="sortableTD"><s:property value="empToken" />
										</td>
										<td class="sortableTD"><s:property value="empIniName" />
										</td>
										<td class="sortableTD"><s:property value="requestTypeItr" /> </td>
										<td class="sortableTD" align="center"><s:property
											value="requestDate" /></td>
										<td id="ctrlShow" class="sortableTD" align="center"><input
											type="button" class="token" value="View Application"
											onclick="viewDetails('<s:property value="applnSecReqId"/>', 'Z')" />
										</td>
									</tr>
								</s:iterator>
								<%
										d9 = i9;
									%>
								</s:if>
								<s:else>
									<td align="center" colspan="8" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
								</s:else>
							</table>
							</td>
						</tr>
			<!-- Rejected Cancel Application Paging Completed -->			
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	loadList();
	
	function loadList() {
		document.getElementById('pending').style.display = 'none';
		document.getElementById('approved').style.display = 'none';
		document.getElementById('rejected').style.display = 'none';
		
		var listType = document.getElementById('paraFrm_listType').value;
		document.getElementById(listType).style.display = '';
	}

	function callPending() {
		document.getElementById('pageForPending').value = '1';
		document.getElementById('pageForPendingCancel').value = '1';
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequestApproval_getPendingList.action';
		document.getElementById('paraFrm').submit();
	}
	
	function callApproved() {
		document.getElementById('pageForApprove').value = '1';
		document.getElementById('pageForApproveCancel').value = '1';
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequestApproval_getApprovedList.action';
		document.getElementById('paraFrm').submit();
	}

	function callRejected() {
		document.getElementById('pageForRejected').value = '1';
		document.getElementById('pageForRejectedCancel').value = '1';
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequestApproval_getRejectedList.action';
		document.getElementById('paraFrm').submit();
	}
	function searchFun(status) {
		var searchStatus = status;
		///alert("searchStatus  "+ searchStatus);
		window.open('','new','top=50,left=300,width=650,height=500,scrollbars=no,status=no,resizable=no');
		document.getElementById("paraFrm").target="new";
		var searchStatus = status;
		document.getElementById('paraFrm_appSecStatus').value=searchStatus;
		document.getElementById("paraFrm").action ='ApplicationSecurityRequestApproval_f9action.action';
		document.getElementById("paraFrm").submit();
		
	}
	
	
/*	Pending Applications Paging*/
		function callPagePending(id, pageImg, totalPageHid, action) {		
		
	
		
		try
		{
		pageNo = document.getElementById('pageNoField').value;	
		actPage = document.getElementById('pageForPending').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField').value = actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField').value=actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) + 1;
		}
		document.getElementById('pageForPending').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	///Paging Text for Pending Applications
	function callPendingPageText(id, totalPage, action){   
		try{
		
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;
		 	var actPage = document.getElementById('pageForPending').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('pageForPending').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	/*End of	Pending Applications Paging*/
	
	/*Pending Cancelation Application*/

function callPagePendingCancel(id, pageImg, totalPageHid, action) {		
		
		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldPendingCancel').value;	
		actPage = document.getElementById('pageForPendingCancel').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldPendingCancel').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldPendingCancel').value = actPage;
			    document.getElementById('pageNoFieldPendingCancel').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldPendingCancel').value=actPage;
			    document.getElementById('pageNoFieldPendingCancel').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldPendingCancel').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldPendingCancel').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldPendingCancel').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldPendingCancel').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldPendingCancel').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldPendingCancel').value;
			id = eval(p) + 1;
		}
		document.getElementById('pageForPendingCancel').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	///Paging Text for Pending Applications
	function callPagePendingCancelText(id, totalPage, action){   
		try{
		
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldPendingCancel').value;
		 	var actPage = document.getElementById('pageForPendingCancel').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldPendingCancel').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldPendingCancel').focus();
		     document.getElementById('pageNoFieldPendingCancel').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldPendingCancel').focus();
		     document.getElementById('pageNoFieldPendingCancel').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldPendingCancel').focus();
		      return false;
	        }
	        
	         document.getElementById('pageForPendingCancel').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}	
	
	/*End of Pending Cancelation Application*/
	
	
	/*Approved Applications Paging*/
	function callPageApproved(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldApprove').value;	
		actPage = document.getElementById('pageForApprove').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldApprove').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldApprove').value = actPage;
			    document.getElementById('pageNoFieldApprove').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldApprove').value=actPage;
			    document.getElementById('pageNoFieldApprove').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldApprove').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldApprove').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldApprove').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldApprove').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldApprove').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldApprove').value;
			id = eval(p) + 1;
		}
		document.getElementById('pageForApprove').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	///Paging Text for Approved Applications
	function callPageApprovedText(id, totalPage, action){   
		try{
		
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldApprove').value;
		 	var actPage = document.getElementById('pageForApprove').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldApprove').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldApprove').focus();
		     document.getElementById('pageNoFieldApprove').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldApprove').focus();
		     document.getElementById('pageNoFieldApprove').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldApprove').focus();
		      return false;
	        }
	        
	         document.getElementById('pageForApprove').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}	
	
	/*End of Approved Application Paging*/
	
	/*Approved Cancel Application Paging*/
	
	function callApproveCancelPage(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldApproveCancel').value;	
		actPage = document.getElementById('pageForApproveCancel').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldApproveCancel').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldApproveCancel').value = actPage;
			    document.getElementById('pageNoFieldApproveCancel').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldApproveCancel').value=actPage;
			    document.getElementById('pageNoFieldApproveCancel').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldApproveCancel').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldApproveCancel').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldApproveCancel').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldApproveCancel').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldApproveCancel').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldApproveCancel').value;
			id = eval(p) + 1;
		}
		document.getElementById('pageForApproveCancel').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	///Paging Text for Approved Applications
	function callApproveCancelPageText(id, totalPage, action){   
		try{
		
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldApproveCancel').value;
		 	var actPage = document.getElementById('pageForApproveCancel').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldApproveCancel').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldApproveCancel').focus();
		     document.getElementById('pageNoFieldApproveCancel').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldApproveCancel').focus();
		     document.getElementById('pageNoFieldApproveCancel').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldApproveCancel').focus();
		      return false;
	        }
	        
	         document.getElementById('pageForApproveCancel').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}	
	
	/*End Of Approved Cancel Application Paging*/
	
	
	/*Rejected Application Paging*/
		function callRejectedPage(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldRejected').value;	
		actPage = document.getElementById('pageForRejected').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldRejected').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldRejected').value = actPage;
			    document.getElementById('pageNoFieldRejected').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldRejected').value=actPage;
			    document.getElementById('pageNoFieldRejected').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldRejected').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldRejected').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldRejected').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldRejected').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldRejected').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldRejected').value;
			id = eval(p) + 1;
		}
		document.getElementById('pageForRejected').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	///Paging Text for Rejected Applications
	function callRejectedPageText(id, totalPage, action){   
		try{
		
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldRejected').value;
		 	var actPage = document.getElementById('pageForRejected').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldRejected').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldRejected').focus();
		     document.getElementById('pageNoFieldRejected').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldRejected').focus();
		     document.getElementById('pageNoFieldRejected').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldRejected').focus();
		      return false;
	        }
	        
	         document.getElementById('pageForRejected').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}	
	
	/*End Of Rejected Application Paging*/
	
	/*Rejected Cancel Application Paging*/
	function callRejectedCancelPage(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldRejectedCancel').value;	
		actPage = document.getElementById('pageForRejectedCancel').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldRejectedCancel').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldRejectedCancel').value = actPage;
			    document.getElementById('pageNoFieldRejectedCancel').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldRejectedCancel').value=actPage;
			    document.getElementById('pageNoFieldRejectedCancel').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldRejectedCancel').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldRejectedCancel').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldRejectedCancel').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldRejectedCancel').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldRejectedCancel').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldRejectedCancel').value;
			id = eval(p) + 1;
		}
		document.getElementById('pageForRejectedCancel').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	///Paging Text for Rejected Applications
	function callRejectedCancelPageText(id, totalPage, action){   
		try{
		
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldRejectedCancel').value;
		 	var actPage = document.getElementById('pageForRejectedCancel').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldRejectedCancel').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldRejectedCancel').focus();
		     document.getElementById('pageNoFieldRejectedCancel').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldRejectedCancel').focus();
		     document.getElementById('pageNoFieldRejectedCancel').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldRejectedCancel').focus();
		      return false;
	        }
	        
	         document.getElementById('pageForRejectedCancel').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}	
	
	/*End Of Rejected Cancel Application Paging*/
	
	
	/*function callASRPage(id, pageImg, totalPageHid, action, fldPageNo, hdnMyPage) {
	
	alert(id);
	alert(pageImg);
	alert(totalPageHid);
	alert(fldPageNo);
	alert(hdnMyPage);
	
	
		var pageNo = document.getElementById('fldPageNoForPending').value;	
		alert("11111");
		var actPage = document.getElementById('pageForPending').value; 
		alert("22222");
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById(fldPageNo).focus();
				return false;
			}

		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById(fldPageNo).value = actPage;
			    document.getElementById(fldPageNo).focus();
				return false;
			}
			
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById(fldPageNo).value=actPage;
			    document.getElementById(fldPageNo).focus();
				return false;
			}
		}
		
       	if(pageImg == "F") {
			if(pageNo == "1") {
				alert("This is first page.");
				document.getElementById(fldPageNo).focus();
				return false;
			}
		}
		
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById(fldPageNo).focus();
	         	return false;
	        }
       	}
       	
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById(fldPageNo).focus();
	         	return false;
	        }
		}
       	
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById(fldPageNo).focus();
	         	return false;
			}
		}
       	
       	if(id == 'P') {
			var p = document.getElementById(fldPageNo).value;
			id = eval(p) - 1;
		}
		
		if(id == 'N') {
			var p = document.getElementById(fldPageNo).value;
			id = eval(p) + 1;
		}
		
		document.getElementById(hdnMyPage).value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
	}
	
	function callPageText(id, totalPage, action, fldPageNo, hdnMyPage) {
		alert("id"+id);
		
		var key //= (window.event) ? event.keyCode : e.which;

		if (window.event)
			key = event.keyCode
		else
			key = id.which
		clear();
					
		if(key == 13) {
			var pageNo = document.getElementById(fldPageNo).value;
			var actPage = document.getElementById(hdnMyPage).value;   

			if(pageNo == "") {
				alert("Please Enter Page Number.");
				document.getElementById(fldPageNo).focus();
				return false;
			}
			
			if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
				document.getElementById(fldPageNo).focus();
				document.getElementById(fldPageNo).value=actPage;
				return false;
			}
		    
		    if(Number(totalPage) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPage + ".");
				document.getElementById(fldPageNo).focus();
				document.getElementById(fldPageNo).value = actPage;  
				return false;
			}
		    
		    if(pageNo == actPage) {
				document.getElementById(fldPageNo).focus();
				return false;
			}
	        
	        document.getElementById(hdnMyPage).value = pageNo;
			document.getElementById('paraFrm').action = action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
	}*/
	
	function viewDetails(applnSecReqId, status) {
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequestApproval_viewDetails.action?applnSecReqId=' + applnSecReqId + 
		'&status=' + status;
		document.getElementById('paraFrm').submit();
	}
</script>