<!-- Bhushan Dasare -->
<!-- Feb 28, 2011 -->
<!-- Changes in List by -Nilesh D on 24th May -->
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="ApplicationSecurityRequest"
	name="ApplicationSecurityRequest" id="paraFrm" validate="true"
	target="main" theme="simple">
	<s:hidden name="listType" />

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
					Security Request</strong></td>
					<td width="4%" valign="middle" align="right" class="txt"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<tr>
			<td width="100%"><a href="#" onclick="callPending();">Pending
			Applications</a> | <a href="#" onclick="callApproved();">Approved
			Applications</a> | <a href="#" onclick="callCancelled();">Cancelled
			Applications</a> | <a href="#" onclick="callRejected();">Rejected
			Applications</a></td>
		</tr>
		<tr id="pending">
			<td>
			<table width="100%">
				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="50%" class="formtxt"><strong>Draft
							Applications</strong></td>
							<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForDraft" id="pageForDraft" />
									<%
										int totalPageForDraft = 0;
										int pageNoForDraft = 0;
										try {
											totalPageForDraft = (Integer) request
											.getAttribute("totalPageForDraft");
											pageNoForDraft = (Integer) request
											.getAttribute("pageNoForDraft");
										} catch (Exception e) {
										}
									%>
									<%
									if (totalPageForDraft > 0) {
									%>
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<a href="#"
										onclick="callASRPage('1', 'F', '<%=totalPageForDraft%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForDraft', 'pageForDraft');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callASRPage('P', 'P', '<%=totalPageForDraft%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForDraft', 'pageForDraft');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="fldPageNoForDraft" id="fldPageNoForDraft" size="3" value="<%=pageNoForDraft%>"
										maxlength="10"
										onkeypress="callPageText(event, '<%=totalPageForDraft%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForDraft', 'pageForDraft');
														return numbersOnly();" />
									of <%=totalPageForDraft%> <a href="#"
										onclick="callASRPage('N', 'N', '<%=totalPageForDraft%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForDraft', 'pageForDraft')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callASRPage('<%=totalPageForDraft%>', 'L', '<%=totalPageForDraft%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForDraft', 'pageForDraft');">
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
									<td width="30%" valign="top" class="formth"><label
										id="tracking.no" name="tracking.no"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="25%" valign="top" class="formth"><label
										id="manager.f9.name" name="manager.f9.name"
										ondblclick="callShowDiv(this);"><%=label.get("manager.f9.name")%></label>
									</td>

									<td width="10%" valign="top" class="formth"><label
										id="token.name" name="token.name"
										ondblclick="callShowDiv(this);"><%=label.get("token.name")%></label>
									</td>
									
									
									<td width="25%" valign="top" class="formth"><label
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
								<%
								int draftCount = 0;
								%>
								<s:iterator value="draftList">
									<tr>
										<td class="sortableTD"><%=++draftCount%></td>
										<td class="sortableTD"><s:hidden name="applnSecReqId" />
										<s:property value="trackingNo" /></td>
										<td class="sortableTD"><s:property value="mgrName" /></td>
                                         <td class="sortableTD"><s:property value="empToken" />
										</td>
										<td class="sortableTD"><s:property value="empIniName" /> </td>
										<td class="sortableTD"><s:property value="requestTypeItr" /> </td>
										<td class="sortableTD" align="center"><s:property
											value="requestDate" /></td>
										<td id="ctrlShow" class="sortableTD" align="center"><input
											type="button" class="edit" value="Edit Application"
											onclick="editDetails('<s:property value="applnSecReqId"/>')" />
										</td>
									</tr>
								</s:iterator>
								<%
								if (draftCount == 0) {
								%>
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
								<%
								}
								%>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="50%" class="formtxt"><strong>Application
							In Process Applications</strong></td>
							<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForPending" id="pageForPending" />
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
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<a href="#"
										onclick="callASRPage('1', 'F', '<%=totalPageForPending%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForPending', 'pageForPending');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callASRPage('P', 'P', '<%=totalPageForPending%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForPending', 'pageForPending');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="fldPageNoForPending" id="fldPageNoForPending" size="3"
										value="<%=pageNoForPending%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPageForPending%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForPending', 'pageForPending');
														return numbersOnly();" />
									of <%=totalPageForPending%> <a href="#"
										onclick="callASRPage('N', 'N', '<%=totalPageForPending%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForPending', 'pageForPending')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callASRPage('<%=totalPageForPending%>', 'L', '<%=totalPageForPending%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForPending', 'pageForPending');">
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
									<td width="30%" valign="top" class="formth"><label
										id="tracking.no" name="tracking.no"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="25%" valign="top" class="formth"><label
										id="manager.f9.name" name="manager.f9.name"
										ondblclick="callShowDiv(this);"><%=label.get("manager.f9.name")%></label>
									</td>
                                    <td width="10%" valign="top" class="formth"><label
										id="token.name" name="token.name"
										ondblclick="callShowDiv(this);"><%=label.get("token.name")%></label>
									</td>

									<td width="25%" valign="top" class="formth"><label
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
								<%
								int pendingCount = 0;
								%>
								<s:iterator value="pendingList">
									<tr>
										<td class="sortableTD"><%=++pendingCount%></td>
										<td class="sortableTD"><s:hidden name="applnSecReqId" />
										<s:property value="trackingNo" /></td>
										<td class="sortableTD"><s:property value="mgrName" /></td>
										<td class="sortableTD"><s:property value="empToken" />
										</td>
										<td class="sortableTD"><s:property value="empIniName" /> </td>
										<td class="sortableTD"><s:property value="requestTypeItr" /> </td>
										<td class="sortableTD" align="center"><s:property value="requestDate" /></td>
										<td id="ctrlShow" class="sortableTD" align="center"><input
											type="button" class="token" value="View Application"
											onclick="viewDetails('<s:property value="applnSecReqId"/>', 'P')" />
										</td>
									</tr>
								</s:iterator>
								<%
								if (pendingCount == 0) {
								%>
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
								<%
								}
								%>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="50%" class="formtxt"><strong>Sent Back
							Applications</strong></td>
							<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForSendBack" id="pageForSendBack" />
									<%
										int totalPageForSendBack = 0;
										int pageNoForSendBack = 0;
										try {
											totalPageForSendBack = (Integer) request
											.getAttribute("totalPageForSendBack");
											pageNoForSendBack = (Integer) request
											.getAttribute("pageNoForSendBack");
										} catch (Exception e) {
										}
									%>
									<%
									if (totalPageForSendBack > 0) {
									%>
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<a href="#"
										onclick="callASRPage('1', 'F', '<%=totalPageForSendBack%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForSendBack', 'pageForSendBack');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callASRPage('P', 'P', '<%=totalPageForSendBack%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForSendBack', 'pageForSendBack');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="fldPageNoForSendBack" id="fldPageNoForSendBack" size="3"
										value="<%=pageNoForSendBack%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPageForSendBack%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForSendBack', 'pageForSendBack');
														return numbersOnly();" />
									of <%=totalPageForSendBack%> <a href="#"
										onclick="callASRPage('N', 'N', '<%=totalPageForSendBack%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForSendBack', 'pageForSendBack')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callASRPage('<%=totalPageForSendBack%>', 'L', '<%=totalPageForSendBack%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForSendBack', 'pageForSendBack');">
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
									<td width="30%" valign="top" class="formth"><label
										id="tracking.no" name="tracking.no"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="25%" valign="top" class="formth"><label
										id="manager.f9.name" name="manager.f9.name"
										ondblclick="callShowDiv(this);"><%=label.get("manager.f9.name")%></label>
									</td>

                                    <td width="10%" valign="top" class="formth"><label
										id="token.name" name="token.name"
										ondblclick="callShowDiv(this);"><%=label.get("token.name")%></label>
									</td>
									
									<td width="25%" valign="top" class="formth"><label
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
								<%
								int sendBackCount = 0;
								%>
								<s:iterator value="sendBackList">
									<tr>
										<td class="sortableTD"><%=++sendBackCount%></td>
										<td class="sortableTD"><s:hidden name="applnSecReqId" />
										<s:property value="trackingNo" /></td>
										<td class="sortableTD"><s:property value="mgrName" /></td>
										<td class="sortableTD"><s:property value="empToken" /> </td>
										<td class="sortableTD"><s:property value="empIniName" /> </td>
										<td class="sortableTD"><s:property value="requestTypeItr" /> </td>
										<td class="sortableTD" align="center"><s:property value="requestDate" /></td>
										<td id="ctrlShow" class="sortableTD" align="center"><input
											type="button" class="edit" value="Edit Application"
											onclick="editDetails('<s:property value="applnSecReqId"/>')" />
										</td>
									</tr>
								</s:iterator>
								<%
								if (sendBackCount == 0) {
								%>
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
								<%
								}
								%>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr id="approved">
			<td>
			<table width="100%">
				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="50%" class="formtxt"><strong>Approved
							Applications</strong></td>
							<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForApprove" id="pageForApprove" />
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
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<a href="#"
										onclick="callASRPage('1', 'F', '<%=totalPageForApprove%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForApprove', 'pageForApprove');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callASRPage('P', 'P', '<%=totalPageForApprove%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForApprove', 'pageForApprove');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="fldPageNoForApprove" id="fldPageNoForApprove" size="3"
										value="<%=pageNoForApprove%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPageForApprove%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForApprove', 'pageForApprove');
														return numbersOnly();" />
									of <%=totalPageForApprove%> <a href="#"
										onclick="callASRPage('N', 'N', '<%=totalPageForApprove%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForApprove', 'pageForApprove')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callASRPage('<%=totalPageForApprove%>', 'L', '<%=totalPageForApprove%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForApprove', 'pageForApprove');">
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
									<td width="30%" valign="top" class="formth"><label
										id="tracking.no" name="tracking.no"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="25%" valign="top" class="formth"><label
										id="manager.f9.name" name="manager.f9.name"
										ondblclick="callShowDiv(this);"><%=label.get("manager.f9.name")%></label>
									</td>
									
									<td width="10%" valign="top" class="formth"><label
										id="token.name" name="token.name"
										ondblclick="callShowDiv(this);"><%=label.get("token.name")%></label>
									</td>

									<td width="25%" valign="top" class="formth"><label
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
								<%
								int approvedCount = 0;
								%>
								<s:iterator value="approveList">
									<tr>
										<td class="sortableTD"><%=++approvedCount%></td>
										<td class="sortableTD"><s:hidden name="persDataChangeId" />
										<s:property value="trackingNo" /></td>
										<td class="sortableTD"><s:property value="mgrName" /></td>
										<td class="sortableTD"><s:property value="empToken" /> </td>
										<td class="sortableTD"><s:property value="empIniName" /> </td>
										<td class="sortableTD"><s:property value="requestTypeItr" /> </td>
										<td class="sortableTD" align="center"><s:property value="requestDate" /></td>
										<td id="ctrlShow" class="sortableTD" align="center"><input
											type="button" name="view_Details" class="token"
											value="View/Cancel Application"
											onclick="viewDetails('<s:property value="applnSecReqId"/>')" />
										</td>
									</tr>
								</s:iterator>
								<%
								if (approvedCount == 0) {
								%>
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
								<%
								}
								%>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="50%" class="formtxt"><strong>Approved
							Cancellation Applications</strong></td>
							<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForApproveCancel" id="pageForApproveCancel" />
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
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<a href="#"
										onclick="callASRPage('1', 'F', '<%=totalPageForApproveCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForApproveCancel', 'pageForApproveCancel');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callASRPage('P', 'P', '<%=totalPageForApproveCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForApproveCancel', 'pageForApproveCancel');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="fldPageNoForApproveCancel" id="fldPageNoForApproveCancel" size="3"
										value="<%=pageNoForApproveCancel%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPageForApproveCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForApproveCancel', 'pageForApproveCancel');
														return numbersOnly();" />
									of <%=totalPageForApproveCancel%> <a href="#"
										onclick="callASRPage('N', 'N', '<%=totalPageForApproveCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForApproveCancel', 'pageForApproveCancel')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callASRPage('<%=totalPageForApproveCancel%>', 'L', '<%=totalPageForApproveCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForApproveCancel', 'pageForApproveCancel');">
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
									<td width="30%" valign="top" class="formth"><label
										id="tracking.no" name="tracking.no"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="25%" valign="top" class="formth"><label
										id="manager.f9.name" name="manager.f9.name"
										ondblclick="callShowDiv(this);"><%=label.get("manager.f9.name")%></label>
									</td>
                                   
                                    <td width="10%" valign="top" class="formth"><label
										id="token.name" name="token.name"
										ondblclick="callShowDiv(this);"><%=label.get("token.name")%></label>
									</td>
									
									<td width="25%" valign="top" class="formth"><label
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
								<%
								int approvedCancelCount = 0;
								%>
								<s:iterator value="approveCancelList">
									<tr>
										<td class="sortableTD"><%=++approvedCancelCount%></td>
										<td class="sortableTD"><s:hidden name="applnSecReqId" />
										<s:property value="trackingNo" /></td>
										<td class="sortableTD"><s:property value="mgrName" /></td>
										<td class="sortableTD"><s:property value="empToken" /> </td>
										<td class="sortableTD"><s:property value="empIniName" /> </td>
										<td class="sortableTD"><s:property value="requestTypeItr" /> </td>
										<td class="sortableTD" align="center"><s:property value="requestDate" /></td>
										<td id="ctrlShow" class="sortableTD" align="center"><input
											type="button" name="view_Details" class="token"
											value="View Application"
											onclick="viewDetails('<s:property value="applnSecReqId"/>')" />
										</td>
									</tr>
								</s:iterator>
								<%
								if (approvedCancelCount == 0) {
								%>
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
								<%
								}
								%>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr id="cancelled">
			<td>
			<table width="100%">
				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="50%" class="formtxt"><strong>Cancelled
							Applications</strong></td>
							<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForPendingCancel" id="pageForPendingCancel" />
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
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<a href="#"
										onclick="callASRPage('1', 'F', '<%=totalPageForPendingCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForPendingCancel', 'pageForPendingCancel');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callASRPage('P', 'P', '<%=totalPageForPendingCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForPendingCancel', 'pageForPendingCancel');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="fldPageNoForPendingCancel" id="fldPageNoForPendingCancel" size="3"
										value="<%=pageNoForPendingCancel%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPageForPendingCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForPendingCancel', 'pageForPendingCancel');
														return numbersOnly();" />
									of <%=totalPageForPendingCancel%> <a href="#"
										onclick="callASRPage('N', 'N', '<%=totalPageForPendingCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForPendingCancel', 'pageForPendingCancel')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callASRPage('<%=totalPageForPendingCancel%>', 'L', '<%=totalPageForPendingCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForPendingCancel', 'pageForPendingCancel');">
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
									<td width="30%" valign="top" class="formth"><label
										id="tracking.no" name="tracking.no"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="25%" valign="top" class="formth"><label
										id="manager.f9.name" name="manager.f9.name"
										ondblclick="callShowDiv(this);"><%=label.get("manager.f9.name")%></label>
									</td>

                                   <td width="10%" valign="top" class="formth"><label
										id="token.name" name="token.name"
										ondblclick="callShowDiv(this);"><%=label.get("token.name")%></label>
									</td>
                                  
									<td width="25%" valign="top" class="formth"><label
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
								<%
								int pendingCancelCount = 0;
								%>
								<s:iterator value="pendingCancelList">
									<tr>
										<td class="sortableTD"><%=++pendingCancelCount%></td>
										<td class="sortableTD"><s:hidden name="applnSecReqId" />
										<s:property value="trackingNo" /></td>
										<td class="sortableTD"><s:property value="mgrName" /></td>
										<td class="sortableTD"><s:property value="empToken" /></td>
										<td class="sortableTD"><s:property value="empIniName" /></td>
										<td class="sortableTD"><s:property value="requestTypeItr" /> </td>
										<td class="sortableTD" align="center"><s:property value="requestDate" /></td>
										<td id="ctrlShow" class="sortableTD" align="center"><input
											type="button" class="token" value="View Application"
											onclick="viewDetails('<s:property value="applnSecReqId"/>', 'C')" />
										</td>
									</tr>
								</s:iterator>
								<%
								if (pendingCancelCount == 0) {
								%>
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
								<%
								}
								%>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr id="rejected">
			<td>
			<table width="100%">
				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="50%" class="formtxt"><strong>Rejected
							Applications</strong></td>
							<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForRejected" id="pageForRejected" />
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
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<a href="#"
										onclick="callASRPage('1', 'F', '<%=totalPageForRejected%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForRejected', 'pageForRejected');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callASRPage('P', 'P', '<%=totalPageForRejected%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForRejected', 'pageForRejected');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="fldPageNoForRejected" id="fldPageNoForRejected" size="3"
										value="<%=pageNoForRejected%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPageForRejected%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForReject', 'pageForRejected');
														return numbersOnly();" />
									of <%=totalPageForRejected%> <a href="#"
										onclick="callASRPage('N', 'N', '<%=totalPageForRejected%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForRejected', 'pageForRejected')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callASRPage('<%=totalPageForRejected%>', 'L', '<%=totalPageForRejected%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForRejected', 'pageForRejected');">
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
									<td width="30%" valign="top" class="formth"><label
										id="tracking.no" name="tracking.no"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="25%" valign="top" class="formth"><label
										id="manager.f9.name" name="manager.f9.name"
										ondblclick="callShowDiv(this);"><%=label.get("manager.f9.name")%></label>
									</td>
									<td width="15%" valign="top" class="formth"><label
										id="token.name" name="token.name"
										ondblclick="callShowDiv(this);"><%=label.get("token.name")%></label>
									</td>

									<td width="25%" valign="top" class="formth"><label
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
								<%
								int rejectedCount = 0;
								%>
								<s:iterator value="rejectedList">
									<tr>
										<td class="sortableTD"><%=++rejectedCount%></td>
										<td class="sortableTD"><s:hidden name="applnSecReqId" />
										<s:property value="trackingNo" /></td>
										<td class="sortableTD"><s:property value="mgrName" /></td>
										<td class="sortableTD"><s:property value="empToken" /></td>
										<td class="sortableTD"><s:property value="empIniName" /></td>
										<td class="sortableTD"><s:property value="requestTypeItr" /> </td>
										<td class="sortableTD" align="center"><s:property value="requestDate" /></td>
										<td id="ctrlShow" class="sortableTD" align="center"><input
											type="button" name="view_Details" class="token"
											value="View Application"
											onclick="viewDetails('<s:property value="applnSecReqId"/>', 'R')" />
										</td>
									</tr>
								</s:iterator>
								<%
								if (rejectedCount == 0) {
								%>
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
								<%
								}
								%>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="50%" class="formtxt"><strong>Rejected
							Cancellation Applications</strong></td>
							<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForRejectedCancel"
										id="pageForRejectedCancel" />
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
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<a href="#"
										onclick="callASRPage('1', 'F', '<%=totalPageForRejectedCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForRejectedCancel', 'pageForRejectedCancel');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callASRPage('P', 'P', '<%=totalPageForRejectedCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForRejectedCancel', 'pageForRejectedCancel');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="fldPageNoForRejectedCancel" id="fldPageNoForRejectedCancel" size="3"
										value="<%=pageNoForRejectedCancel%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPageForRejectedCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForReject', 'pageForRejectedCancel');
														return numbersOnly();" />
									of <%=totalPageForRejectedCancel%> <a href="#"
										onclick="callASRPage('N', 'N', '<%=totalPageForRejectedCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForRejectedCancel', 'pageForRejectedCancel')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callASRPage('<%=totalPageForRejectedCancel%>', 'L', '<%=totalPageForRejectedCancel%>', 
														'ApplicationSecurityRequest_callPage.action', 'fldPageNoForRejectedCancel', 'pageForRejectedCancel');">
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
									<td width="10%" valign="top" class="formth"><label
										id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
									</td>
									<td width="30%" valign="top" class="formth"><label
										id="tracking.no" name="tracking.no"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="25%" valign="top" class="formth"><label
										id="manager.f9.name" name="manager.f9.name"
										ondblclick="callShowDiv(this);"><%=label.get("manager.f9.name")%></label>
									</td>
                                        
                                    <td width="15%" valign="top" class="formth"><label
										id="token.name" name="token.name"
										ondblclick="callShowDiv(this);"><%=label.get("token.name")%></label>
									</td>
									
									<td width="25%" valign="top" class="formth"><label
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
								<%
								int rejectedCancelCount = 0;
								%>
								<s:iterator value="rejectedCancelList">
									<tr>
										<td class="sortableTD"><%=++rejectedCancelCount%></td>
										<td class="sortableTD"><s:hidden name="applnSecReqId" />
										<s:property value="trackingNo" /></td>
										<td class="sortableTD"><s:property value="mgrName" /></td>
										<td class="sortableTD"><s:property value="empToken" />
										<td class="sortableTD"><s:property value="empIniName" /> </td>
										<td class="sortableTD"><s:property value="requestTypeItr" /> </td>
										<td class="sortableTD" align="center"><s:property value="requestDate" /></td>
										<td id="ctrlShow" class="sortableTD" align="center"><input
											type="button" name="view_Details" class="token"
											value="View Application"
											onclick="viewDetails('<s:property value="applnSecReqId"/>', 'Z')" />
										</td>
									</tr>
								</s:iterator>
								<%
								if (rejectedCancelCount == 0) {
								%>
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
								<%
								}
								%>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">

/* Added by Nilesh on 5th Dec 2011 for Search Button*/

function searchFun() {
		
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='ApplicationSecurityRequest_f9empSearch.action';
		document.getElementById("paraFrm").submit();
	}





	function addapplicationFun() {
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_addApplication.action';
		document.getElementById('paraFrm').submit();
	}
	
	loadList();
	
	function loadList() {
		document.getElementById('pending').style.display = 'none';
		document.getElementById('approved').style.display = 'none';
		document.getElementById('cancelled').style.display = 'none';
		document.getElementById('rejected').style.display = 'none';
		
		var listType = document.getElementById('paraFrm_listType').value;
		document.getElementById(listType).style.display = '';
	}

	function callPending() {
		document.getElementById('pageForDraft').value = '1';
		document.getElementById('pageForPending').value = '1';
		document.getElementById('pageForSendBack').value = '1';
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_getPendingList.action';
		document.getElementById('paraFrm').submit();
	}
	
	function callApproved() {
		document.getElementById('pageForApprove').value = '1';
		document.getElementById('pageForApproveCancel').value = '1';
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_getApprovedList.action';
		document.getElementById('paraFrm').submit();
	}
 
	function callCancelled() {
		document.getElementById('pageForPendingCancel').value = '1';
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_getCancelledList.action';
		document.getElementById('paraFrm').submit();
	}

	function callRejected() {
		document.getElementById('pageForRejected').value = '1';
		document.getElementById('pageForRejectedCancel').value = '1';
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_getRejectedList.action';
		document.getElementById('paraFrm').submit();
	}
	
	function editDetails(applnSecReqId) {
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_editDetails.action?applnSecReqId=' + applnSecReqId;
		document.getElementById('paraFrm').submit();
	}
	
	function viewDetails(applnSecReqId) {
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_viewDetails.action?applnSecReqId=' + applnSecReqId;
		document.getElementById('paraFrm').submit();
	}
	
	function callASRPage(id, pageImg, totalPageHid, action, fldPageNo, hdnMyPage) {
		var pageNo = document.getElementById(fldPageNo).value;	
		var actPage = document.getElementById(hdnMyPage).value; 
		
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
	}
</script>